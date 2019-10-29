package cn.itcast.core.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.common.utils.Page;
import cn.itcast.core.bean.LinkMan;
import cn.itcast.core.bean.Visit;
import cn.itcast.core.dao.VisitDao;
import cn.itcast.core.service.VisitService;
@Service
@Transactional
public class VisitServiceImpl implements VisitService{
	@Autowired
	private VisitDao visitDao;
	@Override
	public Page<Visit> selectVisitList(Integer page, Integer rows,
			String visit_interviewer) {
		Visit visit = new Visit();
		if(StringUtils.isNoneBlank(visit_interviewer)){
			visit.setVisit_interviewer(visit_interviewer);
		}
		visit.setStart((page-1) * rows);
		visit.setRows(rows);
		List<Visit> visitList = visitDao.selectVisitList(visit);
		Integer count = visitDao.selectVisitListCount(visit);
		Page<Visit> result = new Page<>();
		result.setPage(page);
		result.setRows(visitList);
		result.setSize(rows);
		result.setTotal(count);
		return result;
	}

	@Override
	public Visit getVisitById(Integer id) {
		Visit visit = visitDao.getVisitById(id);
		return visit;
	}

	@Override
	public void deleteVisit(Integer id) {
		visitDao.delete(id);
	}

	@Override
	public void insertVisit(Visit visit) {
		visitDao.insert(visit);
	}

}
