package com.alex.onlinetest.admin.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.alex.onlinetest.admin.service.ResultReportManagement;
import com.alex.onlinetest.admin.service.TaskManagement;
import com.alex.onlinetest.dao.ItemDao;
import com.alex.onlinetest.dao.PaperDao;
import com.alex.onlinetest.dao.TaskDao;
import com.alex.onlinetest.dao.impl.PageInfo;
import com.alex.onlinetest.hbm.Item;
import com.alex.onlinetest.hbm.Paper;
import com.alex.onlinetest.hbm.Question;
import com.alex.onlinetest.hbm.ResultReport;
import com.alex.onlinetest.hbm.Schedule;
import com.alex.onlinetest.hbm.Task;
import com.alex.onlinetest.hbm.User;
import com.alex.onlinetest.util.Constant;
import com.alex.onlinetest.util.Constant.TASK_STATUS;
import com.alex.onlinetest.util.Tools;

public class TaskManagementImpl implements TaskManagement {
	
	private TaskDao taskDao;
	private ItemDao itemDao;
	private ResultReportManagement rrm;
	
		
	
	public void setRrm(ResultReportManagement rrm) {
		this.rrm = rrm;
	}

	public void setItemDao(ItemDao itemDao) {
		this.itemDao = itemDao;
	}

	public void setTaskDao(TaskDao taskDao) {
		this.taskDao = taskDao;
	}

	@Override
	public PageInfo getResultByPage(int offset, int pageSize) {
		return taskDao.getResultByPage(offset, pageSize);
	}

	@Override
	public List<Task> getAllTask() {
		return taskDao.findAllTask();
	}
	
	@Override
	public List<Task> getAllTaskByUser(User user) {
		return taskDao.findAllTaskByUser(user);
	}

	@Override
	public List<Task> getAllActiveTaskByUser(User user) {

		List<Task> tasks = getAllTaskByUser(user);
		List<Task> result = new ArrayList<Task>();
		for (int i=0; i<tasks.size(); i++) {
			if (tasks.get(i).getStatus()==Constant.TASK_STATUS.NEW) {
				result.add(tasks.get(i));
			}
		}
		
		return result;
	}

	@Override
	public boolean addTask(Task task) {
		if (task == null) {
			return false;
		} else {
			if (taskDao.save(task) > 0) {
				return true;
			} else {
				return false;
			}
		}
	}

	@Override
	public Task getById(int id) {
		return taskDao.get(id);
	}

	@Override
	public boolean deleteById(int id) {
		try {
			taskDao.delete(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public List<Integer> getSortedQuestionId(Task task) {
		
		List<Integer> sortedQuestionIds = new ArrayList<Integer>();
		
		Schedule schedule = task.getSchedule();
		Paper paper = schedule.getPaper();
		Map<Integer, Question> questionsmap = paper.getQuestions();
		
		for (Question value:questionsmap.values()) {
			sortedQuestionIds.add(value.getId());
		}
		
		return sortedQuestionIds;
	}

	@Override
	public boolean updateTaskActualStart(Integer tid, Date date) {
		Task task = getById(tid);
		task.setActualStart(date);
		taskDao.update(task);
		return true;
	}

	@Override
	public boolean updateTaskActualFinish(Integer tid, Date date) {
		Task task = getById(tid);
		task.setActualFinish(date);
		taskDao.update(task);
		return true;
	}

	@Override
	public boolean updateTaskDynamic(Integer tid, Date date) {
		Task task = getById(tid);
		task.setDynamic(date);
		taskDao.update(task);
		return true;
	}

	@Override
	public Integer updatePercentage(Integer tid) {
		Task task = getById(tid);
		int total=0;
		int finish=0;
		
		Set<Item> items = task.getItems();
		Iterator<Item> it = items.iterator();
		while(it.hasNext()) {
			total = total + 1;
			Item item = it.next();
			String answer = item.getAnswer();
			if (answer!=null && !answer.trim().equals("")) {
				finish = finish + 1;
			}
		}
		
		if (total!=0){
			task.setPercentage(Math.round(finish/total)*100);
		}
		
		taskDao.update(task);
		return Math.round(finish/total);
	}

	@Override
	public boolean updateTaskStatus(Integer tid, TASK_STATUS status) {
		Task task = getById(tid);
		task.setStatus(status);
		taskDao.update(task);
		return true;
	}

	@Override
	public boolean updateTaskItem(Integer tid, Integer qid, String answer, Integer elapsed) {
		Task task = getById(tid);
		
		Set<Item> items = task.getItems();
		Iterator<Item> it = items.iterator();
		while(it.hasNext()) {			
			Item item = it.next();
			if (item.getQuestion().getId()==qid) {
				item.setAnswer(answer);
				item.setElapsedSeconds(elapsed);
				itemDao.update(item);
				return true;
			}
		}
		
		return false;
	}

	@Override
	public Item getAnswerOfUser(Integer tid, Integer qid) {
		Task task = getById(tid);
		
		Set<Item> items = task.getItems();
		Iterator<Item> it = items.iterator();
		while(it.hasNext()) {			
			Item item = it.next();
			if (item.getQuestion().getId()==qid) {
				return item;
			}
		}
		
		
		return null;
	}

	@Override
	public Integer getAvailableSeconds(Integer tid) {
		
		Task task = getById(tid);	//获取对应的Task
		Date actualStart = task.getActualStart();
		Date dynamic = task.getDynamic();
		Integer total = task.getSchedule().getDuration();	//单位是秒
		
		if (actualStart == null ) return total;
		if (dynamic == null) return total;
		
		return total-Tools.differenceOfDate(actualStart, dynamic);
	}

	@Override
	public ResultReport reviewTask(Integer tid) {

		ResultReport rr = new ResultReport();
		
		Task task = getById(tid);
		int right=0;
		int percentage=0;
		int acvalue=0;
		int totalamount=0;
		
		
		//设置RR的Task属性
		rr.setTask(task);
		//设置RR的完成时间属性
		rr.setFinishDate(task.getActualFinish());
		//设置RR的题目总数属性
		totalamount = task.getSchedule().getPaper().getAmountOfQuestion();
		rr.setAmountOfQuestion(totalamount);
		//设置RR的总分属性
		rr.setTotalValue(task.getSchedule().getPaper().getTotalValue());
		
		Set<Item> items=task.getItems();
		Iterator<Item> it = items.iterator();
		while(it.hasNext()) {
			Item item = it.next();
			if (item.getQuestion().getQuestionType().equals(Constant.QUESTION_TYPE.MUL)) {
				//多选题
				if (item.getQuestion().getRefAnswer().equals(item.getAnswer())) {
					//回答正确
					item.setMark(true);
					acvalue = item.getQuestion().getValue();
					item.setActualValue(acvalue);
					right = right + 1;
				} else {
					//回答错误
					item.setMark(false);
					item.setActualValue(0);
				}
			} else if (item.getQuestion().getQuestionType().equals(Constant.QUESTION_TYPE.SINGLE)||item.getQuestion().getQuestionType().equals(Constant.QUESTION_TYPE.YESNO)) {
				//单选，判断
				if (item.getQuestion().getRefAnswer().toUpperCase().equals(item.getAnswer().toUpperCase())) {
					//回答正确
					item.setMark(true);
					acvalue = item.getQuestion().getValue();
					item.setActualValue(acvalue);
					right = right + 1;
				} else {
					//回答错误
					item.setMark(false);
					item.setActualValue(0);
				}
			}
			itemDao.update(item);
		}
		
		//设置RR的正确题目数
		rr.setAmountOfRight(right);
		//设置RR的实际得分
		rr.setActualValue(acvalue);
		//设置RR的正确率
		percentage = Math.round(((float)right/(float)totalamount)*100);
		rr.setPercentageOfPass(percentage);
		
		this.rrm.addResultReport(rr);
		
		return rr;
		
	}
	

}
