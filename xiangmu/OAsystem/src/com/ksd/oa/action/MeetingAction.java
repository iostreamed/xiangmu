package com.ksd.oa.action;

import com.ksd.oa.entity.Meeting;
import com.ksd.oa.entity.User;
import com.ksd.oa.service.MeetingService;
import com.ksd.oa.util.PageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class MeetingAction extends ActionSupport implements ModelDriven<Meeting>{
	private Meeting meeting= new Meeting();

	public Meeting getModel() {
		return meeting;
	}
	
	private MeetingService meetingService;

	public MeetingService getMeetingService() {
		return meetingService;
	}

	public void setMeetingService(MeetingService meetingService) {
		this.meetingService = meetingService;
	}
	private int page;
	
	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public String meetinglist(){
		PageBean<Meeting> meetinglist = meetingService.findAll(meeting.getSender(), page);
		ActionContext.getContext().getSession().put("meetinglist", meetinglist);
		return "meetinglist";
	}
	
	public String addMeeting(){
		return "addMeeting";
	}
	
	public String saveMeeting(){
		User user = (User) ActionContext.getContext().getSession().get("user");
		Meeting meeting1 = new Meeting(null, user.getUserName(), meeting.getStartTime(), meeting.getEndTime(), meeting.getAddress(), meeting.getTitle(), meeting.getContent());
		meetingService.addMeeting(meeting1);
		return "saveMeeting";
	}
	
	public String deleteMeeting(){
		Meeting meeting1 = meetingService.findById(meeting.getId());
		meetingService.deleteMeeting(meeting1);
		return"deleteMeeting";
	}
	
	public String updateMeeting(){
		Meeting meeting1 = meetingService.findById(meeting.getId());
		ActionContext.getContext().getSession().put("meeting", meeting1);
		return "updateMeeting";
	}
	
	public String saveUpdate(){
		User user = (User) ActionContext.getContext().getSession().get("user");
		Meeting meeting1 = (Meeting) ActionContext.getContext().getSession().get("meeting");
		if (user.getUserName().equals(meeting.getSender())) {
			meeting1.setEndTime(meeting.getStartTime());
			meeting1.setEndTime(meeting.getEndTime());
			meeting1.setAddress(meeting.getAddress());
			meeting1.setTitle(meeting.getTitle());
			meeting1.setContent(meeting.getContent());
			meetingService.updateMeeting(meeting1);
			return "saveUpdate";
		}
		this.addActionError("本次会议非当前用户创建，无法修改");
		return "updateMeeting";
	}
}
