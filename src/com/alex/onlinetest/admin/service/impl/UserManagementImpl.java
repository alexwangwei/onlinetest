package com.alex.onlinetest.admin.service.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.alex.onlinetest.admin.service.UserManagement;
import com.alex.onlinetest.dao.RoleDao;
import com.alex.onlinetest.dao.UserDao;
import com.alex.onlinetest.dao.UserGroupDao;
import com.alex.onlinetest.dao.impl.PageInfo;
import com.alex.onlinetest.hbm.Role;
import com.alex.onlinetest.hbm.User;
import com.alex.onlinetest.hbm.UserGroup;

public class UserManagementImpl implements UserManagement {
	
	private UserDao userDao;
	private RoleDao roleDao;
	private UserGroupDao usergroupDao;
	
	//IoC Set方法
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	//IoC Set方法
	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}

	//IoC Set方法
	public void setUsergroupDao(UserGroupDao usergroupDao) {
		this.usergroupDao = usergroupDao;
	}

	@Override
	public boolean validUser(User user) {
		
		if (user.getLoginId() == null || user.getPassword() == null || user.getLoginId().trim().equals("") || user.getPassword().trim().equals("")) {
			return false;
		} else {
			User loginuser = userDao.findByLoginIdAndPassword(user.getLoginId(), user.getPassword());
			
			if (loginuser == null) {
				return false;
			} else {
				return true;
			}
		}
		
	}

	@Override
	public User getUserByLoginId(String loginId) {
		
		if (loginId == null || loginId.trim().equals("")) {
			return null;
		} else {
			return userDao.findByLoginId(loginId);
		}
	}

	@Override
	public boolean changePassword(User user, String newpwd) {
		
		if (user == null || newpwd == null) {
			return false;
		} else {
			user.setPassword(newpwd);
			userDao.update(user);
			return true;
		}
	}

	@Override
	public boolean changeDisplayName(User user, String newdisplayname) {
		
		if (user == null || newdisplayname == null) {
			return false;
		} else {
			user.setDisplayName(newdisplayname);
			userDao.update(user);
			return true;
		}
	}

	@Override
	public boolean addRole(User user, Role role) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeRole(User user, Role role) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * 给指定用户添加Group
	 */
	@Override
	public boolean addGroup(User user, UserGroup group) {		
		
		if (user == null || group == null) return false;
		
		//用户不拥有该group则添加
		if (!inUserGroup(user, group)) {
			if (!user.getGroup().add(group)) return false;
			userDao.update(user);
		}
		
		return true;
	}

	@Override
	public boolean removeGroup(User user, UserGroup group) {
		
		if (user == null || group == null) return false;
		
		//用户拥有该group
		if (inUserGroup(user, group)) {
			if (!user.getGroup().remove(group)) return false;
			userDao.update(user);
		}
		
		return true;
	}

	@Override
	public PageInfo getResultByPage(int offset, int pageSize) {
		return userDao.getResultByPage(offset, pageSize);
	}

	@Override
	public boolean addUser(User user) {
		
		if (user == null) {
			return false;
		} else {
			if (userDao.save(user) > 0) {
				return true;
			} else {
				return false;
			}
		}
	}

	@Override
	public boolean delUser(User user) {
		
		if (user == null) {
			return false;
		} else {
			userDao.delete(user);
			return true;
		}
	}

	@Override
	public boolean delUserById(Integer id) {
		
		if (id == null) {
			return false;
		} else {
			userDao.delete(id);
			return true;
		}
	}

	@Override
	public List<User> getAll() {
		return userDao.findAllUser();
	}

	@Override
	public boolean inUserGroup(User user, UserGroup ug) {
		
		if (user != null && ug != null) {
			
			Set<UserGroup> usersgroup = user.getGroup();
			if (usersgroup == null) {
				return false;
			} else {
				//逻辑判断
				Iterator<UserGroup> it = usersgroup.iterator();
				while (it.hasNext()) {
					UserGroup usrUg = it.next();
					if (usrUg.equals(ug)) {
						return true;
					}
				}
				return false;
			}
		} else {
			return false;
		}
		
	}

	@Override
	public void updateUserAndGroupMapping(UserGroup ug, List<User> users) {
		
		boolean flag=true;
		
		//清除所有
		if (ug!=null && users==null) {
			//移除所有Group
			List<User> all = getAll();
			for (int i=0; i<all.size(); i++) {
				removeGroup(all.get(i), ug);
			}
		}
		
		//按实际清除
		if (ug!=null && users!=null) {
			//移除所有Group
			List<User> all = getAll();
			for (int i=0; i<all.size(); i++) {
				flag = true;
				for (int m=0; m<users.size(); m++) {
					if (all.get(i).getLoginId().equals(users.get(m).getLoginId())) {
						flag = false;
					}
				}
				
				if (flag) {
					removeGroup(all.get(i), ug);
				}
			}
			//更新
			for (int n=0; n<users.size(); n++) {
				addGroup(users.get(n), ug);
			}
		}
		
	}

	
}
