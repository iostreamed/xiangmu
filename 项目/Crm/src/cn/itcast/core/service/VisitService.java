package cn.itcast.core.service;

import cn.itcast.common.utils.Page;
import cn.itcast.core.bean.Visit;

public interface VisitService {
	
	public Page<Visit> selectVisitList(Integer page, Integer rows,String visit_interviewer);
	public Visit getVisitById(Integer id);
	public void deleteVisit(Integer id);
	public void insertVisit(Visit visit);
	
}
