package com.alex.onlinetest.admin.service.impl;

import java.util.List;
import java.util.Map;

import com.alex.onlinetest.admin.service.PaperManagement;
import com.alex.onlinetest.dao.PaperDao;
import com.alex.onlinetest.dao.QuestionDao;
import com.alex.onlinetest.dao.impl.PageInfo;
import com.alex.onlinetest.hbm.Paper;
import com.alex.onlinetest.hbm.Question;
import com.alex.onlinetest.util.Constant;

public class PaperManagementImpl implements PaperManagement {
	
	private PaperDao paperDao;

	public void setPaperDao(PaperDao paperDao) {
		this.paperDao = paperDao;
	}

	@Override
	public PageInfo getResultByPage(int offset, int pageSize) {
		return paperDao.getResultByPage(offset, pageSize);
	}

	@Override
	public List<Paper> getAllPaper() {
		return paperDao.findAllPaper();
	}

	@Override
	public boolean addPaper(Paper paper) {
		if (paper == null) {
			return false;
		} else {
			if (paperDao.save(paper) > 0) {
				return true;
			} else {
				return false;
			}
		}
	}

	@Override
	public Paper getById(int id) {
		return paperDao.get(id);
	}

	@Override
	public boolean deleteById(int id) {
		try {
			paperDao.delete(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean isAllObjective(int id) {
		boolean allIsObjective = true;
		Paper paper = paperDao.get(id);
		
		Map<Integer, Question> questionmap = paper.getQuestions();
		for (Question value : questionmap.values()) {
			if (value.getQuestionType().equals(Constant.QUESTION_TYPE.OPENQUESTION)) {
				allIsObjective = false;
			}
		}
		return allIsObjective;
	}
	
	

}
