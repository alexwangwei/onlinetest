package com.alex.onlinetest.admin.service.impl;

import java.util.List;

import com.alex.onlinetest.admin.service.ScheduleManagement;
import com.alex.onlinetest.dao.PaperDao;
import com.alex.onlinetest.dao.ScheduleDao;
import com.alex.onlinetest.dao.impl.PageInfo;
import com.alex.onlinetest.hbm.Schedule;

public class ScheduleManagementImpl implements ScheduleManagement {

	private ScheduleDao scheduleDao;
	

	public void setScheduleDao(ScheduleDao scheduleDao) {
		this.scheduleDao = scheduleDao;
	}

	@Override
	public PageInfo getResultByPage(int offset, int pageSize) {
		return scheduleDao.getResultByPage(offset, pageSize);
	}

	@Override
	public List<Schedule> getAllSchedule() {
		return scheduleDao.findAllSchedule();
	}

	@Override
	public boolean addSchedule(Schedule schedule) {
		if (schedule == null) {
			return false;
		} else {
			if (scheduleDao.save(schedule) > 0) {
				return true;
			} else {
				return false;
			}
		}
	}

	@Override
	public Schedule getById(int id) {
		return scheduleDao.get(id);
	}

	@Override
	public boolean deleteById(int id) {
		try {
			scheduleDao.delete(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean updateScheduleStatus(Schedule schedule) {
		scheduleDao.update(schedule);
		return true;
	}
	

}
