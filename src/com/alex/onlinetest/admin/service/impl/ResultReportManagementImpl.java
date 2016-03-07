package com.alex.onlinetest.admin.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.alex.onlinetest.admin.service.ResultReportManagement;
import com.alex.onlinetest.dao.QuestionDao;
import com.alex.onlinetest.dao.ResultReportDao;
import com.alex.onlinetest.dao.impl.PageInfo;
import com.alex.onlinetest.hbm.ResultReport;
import com.alex.onlinetest.hbm.Schedule;
import com.alex.onlinetest.hbm.Task;
import com.alex.onlinetest.hbm.User;

public class ResultReportManagementImpl implements ResultReportManagement {
	
	private ResultReportDao rrDao;
	
	

	public void setRrDao(ResultReportDao rrDao) {
		this.rrDao = rrDao;
	}

	@Override
	public PageInfo getResultByPage(int offset, int pageSize) {
		return rrDao.getResultByPage(offset, pageSize);
	}

	@Override
	public List<ResultReport> getAllResultReport() {
		return rrDao.findAllResultReport();
	}

	@Override
	public List<ResultReport> getAllResultReportByUser(User user) {
		
		List<ResultReport> result = new ArrayList<ResultReport>();
		List<ResultReport> all = getAllResultReport();
		
		for (int i=0; i<all.size(); i++) {
			if (all.get(i).getTask().getOwner().toUpperCase().equals(user.getLoginId().toUpperCase())) {
				result.add(all.get(i));
			}
		}
		
		
		return result;
	}

	@Override
	public boolean addResultReport(ResultReport rr) {
		if (rr == null) {
			return false;
		} else {
			if (rrDao.save(rr) > 0) {
				return true;
			} else {
				return false;
			}
		}
	}

	@Override
	public ResultReport getById(int id) {
		return rrDao.get(id);
	}

	@Override
	public boolean deleteById(int id) {
		try {
			rrDao.delete(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public List<ResultReport> getAllResultReportBySchedule(Schedule schedule) {
		
		List<ResultReport> result = new ArrayList<ResultReport>();
		List<ResultReport> all = getAllResultReport();
		
		for (int i=0; i<all.size(); i++) {
			if (all.get(i).getTask().getSchedule().getId() == schedule.getId()) {
				result.add(all.get(i));
			}
		}
		
		
		return result;
	}

	
	
}
