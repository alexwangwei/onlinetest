package com.alex.onlinetest.admin.service.impl;

import java.util.List;

import com.alex.onlinetest.admin.service.NavigatorManagement;
import com.alex.onlinetest.dao.NavigatorDao;
import com.alex.onlinetest.dao.UserDao;
import com.alex.onlinetest.hbm.Navigator;

public class NavigatorManagementImpl implements NavigatorManagement {

	private NavigatorDao navDao;
	
	//IoC Set方法	
	public void setNavDao(NavigatorDao navDao) {
		this.navDao = navDao;
	}

	@Override
	public List<Navigator> getAllNavigator() {
		return navDao.findAllNavigatorBySequence();
	}

}
