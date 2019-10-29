package cn.itcast.core.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.common.utils.Page;
import cn.itcast.core.bean.Customer;
import cn.itcast.core.bean.LinkMan;
import cn.itcast.core.dao.LinkManDao;
import cn.itcast.core.service.LinkManService;
@Service
@Transactional
public class LinkManServiceImpl implements LinkManService{
	
	@Autowired
	private LinkManDao linkManDao;
	
	@Override
	public Page<LinkMan> findLinkManList(Integer page, Integer rows,
			String linkName) {
		LinkMan linkMan = new LinkMan();
		if(StringUtils.isNotBlank(linkName)){
			linkMan.setLink_name(linkName);
		}
		linkMan.setStart((page-1) * rows);
		linkMan.setRows(rows);
		List<LinkMan> linkManlist = linkManDao.selectLinkManList(linkMan);
		Integer count = linkManDao.selectLinkManListCount(linkMan);
		Page<LinkMan> result = new Page<>();
		result.setPage(page);
		result.setRows(linkManlist);
		result.setSize(rows);
		result.setTotal(count);
		return result;
	}

	@Override
	public LinkMan getLinkManById(Integer id) {
		LinkMan linkMan = linkManDao.selectByLinkManId(id);	
		return linkMan;
	}

	@Override
	public void updateLinkMan(LinkMan linkMan) {
		linkManDao.update(linkMan);
	}

	@Override
	public void deleteLinkMan(Integer[] ids) {
		linkManDao.delete(ids);
	}

	@Override
	public void insert(LinkMan linkMan) {
		linkManDao.insert(linkMan);
	}

	@Override
	public List<LinkMan> getLinkManForExcel() {
		List<LinkMan> linkManList = linkManDao.getLinkManForExcel();
		return linkManList;
	}

	@Override
	public void importLinkManForExcel(List<LinkMan> linkManList) {
		linkManDao.importLinkManForExcel(linkManList);
	}

}
