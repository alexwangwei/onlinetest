package com.alex.onlinetest.admin.service;

import java.util.List;

import com.alex.onlinetest.hbm.Navigator;

public interface NavigatorManagement {
	
	/**
	 * 获取按顺序排列的导航信息
	 * @return 返回List<Navigator>
	 */
	public List<Navigator> getAllNavigator();
}
