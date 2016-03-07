package com.alex.onlinetest.util;

public class Constant {
	
	//分页查询，默认页面大小
	public static final int PAGESIZE = 25;
	//题目类型
	public static enum QUESTION_TYPE {
		SINGLE,
		MUL,
		YESNO,
		OPENQUESTION;
	};
	//问题难易程度
	public static enum QUESTION_LEAVEL {
		EASY,
		MIDDLE,
		DIFFICULT;
	};
	//问题状态
	public static enum QUESTION_STATUS {
		DRAFT,
		APPROVED,
		REJECTED,
		CANCELED;
	};
	//PAPER状态
	public static enum PAPER_STATUS {
		DRAFT,
		APPROVED,
		REJECTED,
		CANCELED;
	};
	//考试排期状态
	public static enum SCHEDULE_STATUS {
		ACTIVE,
		PUBLISHED,
		NONACTIVE;
	};
	//任务状态
	public static enum TASK_STATUS {
		CANCELED,
		NEW,
		DONE,
		ONGOING,
		PUBLISHED;
	};
}
