package cn.itcast.core.service;

import java.util.List;

import cn.itcast.common.utils.Page;
import cn.itcast.core.bean.LinkMan;

public interface LinkManService {
	public Page<LinkMan> findLinkManList(Integer page, Integer rows,String linkName);
	
	public LinkMan getLinkManById(Integer id);
	
	public void updateLinkMan(LinkMan linkMan);
	
	public void deleteLinkMan(Integer[] ids);
	
	public void insert(LinkMan linkMan);
	
	public List<LinkMan> getLinkManForExcel();
	
	public void importLinkManForExcel(List<LinkMan> linkManList);
}
