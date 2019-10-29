package cn.itcast.core.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.junit.runners.Parameterized.Parameters;

import cn.itcast.core.bean.Visit;

public interface VisitDao {
	List<Visit> selectVisitList(Visit visit);
	Integer selectVisitListCount(Visit visit);
	Visit getVisitById(Integer id);
	void delete(Integer id);
	void insert(Visit visit);
}
