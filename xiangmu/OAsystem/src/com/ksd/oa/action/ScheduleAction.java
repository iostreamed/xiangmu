package com.ksd.oa.action;

import com.ksd.oa.entity.Schedule;
import com.ksd.oa.entity.User;
import com.ksd.oa.service.ScheduleService;
import com.ksd.oa.util.PageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class ScheduleAction extends ActionSupport  implements ModelDriven<Schedule>{
	private Schedule schedule=new Schedule();
	public Schedule getModel() {
		return schedule;
	}
	private ScheduleService scheduleService;
	public ScheduleService getScheduleService() {
		return scheduleService;
	}
	public void setScheduleService(ScheduleService scheduleService) {
		this.scheduleService = scheduleService;
	}
	
	private int page;
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	
	public String schedulelist(){
		User user = (User) ActionContext.getContext().getSession().get("user");
		PageBean<Schedule> schedulelist=scheduleService.findByName(user.getUserName(), page);
		ActionContext.getContext().getSession().put("schedulelist", schedulelist);		
		return "schedulelist";
	}
	
	public String addSchedule(){
		return "addSchedule";
	}
	
	public String saveSchedule(){
		User user = (User) ActionContext.getContext().getSession().get("user");
		Schedule schedule1 = new Schedule(null, user.getUserName(), schedule.getTime(), schedule.getPlan());
		scheduleService.addSchedule(schedule1);
		return "saveSchedule";
	}
	
	public String deleteSchedule(){
		Schedule schedule1=scheduleService.findById(schedule.getId());
		scheduleService.deleteSchedule(schedule1);
		return "deleteSchedule";
	}
	
	public String updateSchedule(){
		Schedule schedule1=scheduleService.findById(schedule.getId());
		ActionContext.getContext().getSession().put("schedule", schedule1);
		return "updateSchedule";
	}
	
	public String saveUpdate(){
		Schedule schedule1 = (Schedule) ActionContext.getContext().getSession().get("schedule");
		schedule1.setTime(schedule.getTime());
		schedule1.setPlan(schedule.getPlan());
		scheduleService.updateSchedule(schedule1);
		return "saveUpdate";
	}
}
