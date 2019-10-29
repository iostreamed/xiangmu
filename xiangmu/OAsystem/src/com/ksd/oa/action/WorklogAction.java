package com.ksd.oa.action;

import java.util.Date;

import com.ksd.oa.entity.User;
import com.ksd.oa.entity.Worklog;
import com.ksd.oa.service.WorklogService;
import com.ksd.oa.util.PageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class WorklogAction extends ActionSupport implements ModelDriven<Worklog>{
	private Worklog worklog = new Worklog();
	@Override
	public Worklog getModel() {
		return worklog;
	}
	
	private WorklogService worklogService;
	public WorklogService getWorklogService() {
		return worklogService;
	}

	public void setWorklogService(WorklogService worklogService) {
		this.worklogService = worklogService;
	}
	
	private int page;
	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}
	public String workloglist(){
		User user = (User) ActionContext.getContext().getSession().get("user");
		PageBean<Worklog> workloglist = worklogService.findByUserName(user.getUserName(), page);
		ActionContext.getContext().getSession().put("workloglist", workloglist);
		return "workloglist";
	}
	
	public String addWorklog(){
		return "addWorklog";
	}
	
	public String saveWorklog(){
		User user = (User) ActionContext.getContext().getSession().get("user");
		Worklog worklog1 = new Worklog(null, user.getUserName(), worklog.getTitle(), worklog.getDescription(), new Date());
		worklogService.addWorklog(worklog1);
		return "saveWorklog";
	}
	
	public String updateWorklog(){
		Worklog worklog1 = worklogService.findById(worklog.getId());
		ActionContext.getContext().getSession().put("worklog", worklog1);
		return "updateWorklog";
	}
	
	public String saveUpdate(){
		Worklog worklog1 = (Worklog) ActionContext.getContext().getSession().get("worklog");
		if(worklog1 !=null){
			worklog1.setTitle(worklog.getTitle());
			worklog1.setDescription(worklog.getDescription());
			worklogService.updateWorklog(worklog1);
			return "saveUpdate";
		}
		this.addActionError("修改失败");
		return "updateWorklog";
	}
	
	public String deleteWorklog(){
		Worklog worklog1 = worklogService.findById(worklog.getId());
		worklogService.deleteWorklog(worklog1);
		return "deleteWorklog";
	}
}
