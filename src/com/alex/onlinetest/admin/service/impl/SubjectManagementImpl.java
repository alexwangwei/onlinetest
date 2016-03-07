package com.alex.onlinetest.admin.service.impl;

import java.util.List;

import com.alex.onlinetest.admin.service.SubjectManagement;
import com.alex.onlinetest.dao.SubjectDao;
import com.alex.onlinetest.dao.UserGroupDao;
import com.alex.onlinetest.hbm.Subject;
import com.alex.onlinetest.hbm.UserGroup;

public class SubjectManagementImpl implements SubjectManagement {
	
	private SubjectDao subDao;

	public void setSubDao(SubjectDao subDao) {
		this.subDao = subDao;
	}

	@Override
	public List<Subject> getAllSubject() {
		return subDao.findAllSubject();
	}

	@Override
	public boolean addSubject(Subject subject) {
		if (subject == null) {
			return false;
		} else {
			if (subDao.save(subject) > 0) {
				return true;
			} else {
				return false;
			}
		}
	}

	@Override
	public boolean updateSubjectName(Subject sub, String newname) {
		if (sub == null || newname == null || newname.trim().equals("")) {
			return false;
		} else {
			sub.setSubjectName(newname);
			subDao.update(sub);
			return true;
		}
	}

	@Override
	public Subject getById(int id) {
		return subDao.get(id);
	}

	@Override
	public Subject getBySubjectName(String subjectName) {
		if (subjectName == null || subjectName.trim().equals("")) {
			return null;
		} else {
			return subDao.get(subjectName);
		}
	}

	@Override
	public boolean deleteById(int id) {
		try {
			subDao.delete(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean deleteBySubjectName(String subjectName) {
		try {
			Subject sub = getBySubjectName(subjectName);
			if (sub != null) {
				subDao.delete(sub);
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}

}
