package com.alex.onlinetest.admin.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.alex.onlinetest.admin.service.GroupManagement;
import com.alex.onlinetest.admin.service.UserManagement;
import com.alex.onlinetest.dao.PageResult;
import com.alex.onlinetest.dao.UserDao;
import com.alex.onlinetest.dao.UserGroupDao;
import com.alex.onlinetest.dao.impl.PageInfo;
import com.alex.onlinetest.hbm.User;
import com.alex.onlinetest.hbm.UserGroup;

public class GroupManagementImpl implements GroupManagement {
	
	private UserGroupDao usrgrpDao;
	private UserManagement usrmgnt;
	
	//IoC Set方法	
	public void setUsrgrpDao(UserGroupDao usrgrpDao) {
		this.usrgrpDao = usrgrpDao;
	}
	



	public void setUsrmgnt(UserManagement usrmgnt) {
		this.usrmgnt = usrmgnt;
	}



	@Override
	public PageInfo getResultByPage(int offset, int pageSize) {
		return usrgrpDao.getResultByPage(offset, pageSize);
	}
	

	@Override
	public List<UserGroup> getAllUserGroup() {
		return usrgrpDao.findAll();
	}


	@Override
	public boolean addUserGroup(UserGroup usergroup) {
		
		if (usergroup == null) {
			return false;
		} else {
			if (usrgrpDao.save(usergroup) > 0) {
				return true;
			} else {
				return false;
			}
		}
	}

	@Override
	public boolean updateUserGroupName(UserGroup usergroup, String newgroupname) {
		
		if (usergroup == null || newgroupname == null || newgroupname.trim().equals("")) {
			return false;
		} else {
			usergroup.setGroupName(newgroupname);
			usrgrpDao.update(usergroup);
			return true;
		}
	}

	@Override
	public UserGroup getById(int id) {
		return usrgrpDao.get(id);
	}

	@Override
	public UserGroup getByGroupName(String groupname) {
		
		if (groupname == null || groupname.trim().equals("")) {
			return null;
		} else {
			return usrgrpDao.get(groupname);
		}
	}

	@Override
	public boolean deleteById(int id) {
		
		try {
			usrgrpDao.delete(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean deleteByGroupName(String groupname) {
		
		try {
			UserGroup ug = getByGroupName(groupname);
			if (ug != null) {
				usrgrpDao.delete(ug);
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}


	@Override
	public List<User> getAllUsersInGroup(UserGroup usergroup) {

		List<User> users = new ArrayList<User>();
		
		for (int i=0; i<usrmgnt.getAll().size(); i++) {
			if (usrmgnt.inUserGroup(usrmgnt.getAll().get(i), usergroup)) {
				users.add(usrmgnt.getAll().get(i));
			}
		}
		
		return users;
	}

	@Override
	public List<User> getAllDistinctUsersInGroup(Set<UserGroup> grouplist) {
		
		List<User> users = new ArrayList<User>();
		
		Iterator<UserGroup> it = grouplist.iterator();
		while(it.hasNext()) {
			UserGroup ug = it.next();
			for (int n=0; n<getAllUsersInGroup(ug).size(); n++) {
				if (!users.contains(getAllUsersInGroup(ug).get(n))) {
					users.add(getAllUsersInGroup(ug).get(n));
				}
			}
		}
		
		return users;
	}
	
	

}
