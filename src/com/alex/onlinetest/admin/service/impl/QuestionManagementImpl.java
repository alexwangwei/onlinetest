package com.alex.onlinetest.admin.service.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.alex.onlinetest.admin.service.QuestionManagement;
import com.alex.onlinetest.dao.QuestionDao;
import com.alex.onlinetest.dao.UserGroupDao;
import com.alex.onlinetest.dao.impl.DynamicResult;
import com.alex.onlinetest.dao.impl.PageInfo;
import com.alex.onlinetest.hbm.Question;

public class QuestionManagementImpl implements QuestionManagement {
	
	private QuestionDao questionDao;

	public void setQuestionDao(QuestionDao questionDao) {
		this.questionDao = questionDao;
	}

	@Override
	public PageInfo getResultByPage(int offset, int pageSize) {
		return questionDao.getResultByPage(offset, pageSize);
	}

	@Override
	public List<Question> getAllQuestion() {
		return questionDao.findAllQuestion();
	}

	@Override
	public boolean addQuestion(Question question) {
		if (question == null) {
			return false;
		} else {
			if (questionDao.save(question) > 0) {
				return true;
			} else {
				return false;
			}
		}
	}

	@Override
	public Question getById(int id) {
		return questionDao.get(id);
	}

	@Override
	public boolean deleteById(int id) {
		try {
			questionDao.delete(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public List<Question> getAllQuestionByAndFilter(
			HashMap<String, String> filter) {
		return this.questionDao.findAllQuestionByFilter(filter).getResult();
	}


	
	

}
