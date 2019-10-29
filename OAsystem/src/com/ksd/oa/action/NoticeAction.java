package com.ksd.oa.action;

import java.util.Date;

import com.ksd.oa.entity.Notice;
import com.ksd.oa.entity.User;
import com.ksd.oa.service.NoticeService;
import com.ksd.oa.util.PageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class NoticeAction extends ActionSupport implements ModelDriven<Notice> {
	private Notice notice = new Notice();
	@Override
	public Notice getModel() {
		return notice;
	}
	
	private NoticeService noticeService;
	public NoticeService getNoticeService() {
		return noticeService;
	}

	public void setNoticeService(NoticeService noticeService) {
		this.noticeService = noticeService;
	}
	
	private int page;
	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}
	
	public String noticelist(){
		PageBean<Notice> noticelist = noticeService.findAll(page);
		ActionContext.getContext().getSession().put("noticelist", noticelist);	
		return "noticelist";
	}
	
	public String addNotice(){
		return "addNotice";
	}
	
	public String saveNotice(){
		User user=(User) ActionContext.getContext().getSession().get("user");
		Notice notice1 = new Notice(null, user.getUserName(), notice.getTitle(), notice.getContent(), new Date());
		noticeService.addNotice(notice1);
		findByLeast();
		return "saveNotice";
	}
	
	public String deleteNotice(){
		Notice notice1 = noticeService.findById(notice.getId());
		if(notice1 != null){
			noticeService.deleteNotice(notice1);
		}
		return "deleteNotice";
	}
	
	public String updateNotice(){
		Notice notice1 = noticeService.findById(notice.getId());
		ActionContext.getContext().getSession().put("notice", notice1);
		return "updateNotice";
	}
	
	public String saveUpdate(){
		Notice notice1 = (Notice) ActionContext.getContext().getSession().get("notice");
		if(notice1 != null){
			notice1.setTitle(notice.getTitle());
			notice1.setContent(notice.getContent());
			noticeService.updateNotice(notice1);
		}
		return "saveUpdate";
	}
	
	public String findByLeast(){
		Notice notice1 = noticeService.findByLeast();
		ActionContext.getContext().getSession().put("notice1", notice1);
		return "findByLeast";
	}
}
