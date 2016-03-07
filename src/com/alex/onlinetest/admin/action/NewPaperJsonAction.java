package com.alex.onlinetest.admin.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.alex.onlinetest.admin.service.GroupManagement;
import com.alex.onlinetest.admin.service.NavigatorManagement;
import com.alex.onlinetest.admin.service.QuestionManagement;
import com.alex.onlinetest.admin.service.SubjectManagement;
import com.alex.onlinetest.admin.service.UserManagement;
import com.alex.onlinetest.dao.impl.PageInfo;
import com.alex.onlinetest.hbm.Question;
import com.alex.onlinetest.hbm.QuestionOption;
import com.alex.onlinetest.hbm.Subject;
import com.alex.onlinetest.hbm.User;
import com.alex.onlinetest.hbm.UserGroup;
import com.alex.onlinetest.util.Constant;
import com.alex.onlinetest.util.Tools;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Namespace("/admin")
@ParentPackage("struts-default")
public class NewPaperJsonAction extends ActionSupport {
	

	@Action(value="newpaperjson", className="ajaxnewpaperAction", results={
			@Result(name="success", location="/Admin/manual_new_paper_ajax.jsp")
	})
	public String newPaper() throws Exception {
				
		return SUCCESS;
	}
	

	
}
