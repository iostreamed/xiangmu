package cn.itcast.core.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.itcast.core.bean.LinkMan;

public interface LinkManDao {
	List<LinkMan> selectLinkManList(LinkMan linkMan);
	Integer selectLinkManListCount(LinkMan linkMan);
	LinkMan selectByLinkManId(Integer id);
	void update(LinkMan linkMan);
	void delete(Integer[] ids);
	void insert(LinkMan linkMan);
	List<LinkMan> getLinkManForExcel();
	void importLinkManForExcel(@Param("list")List<LinkMan> linkManList);
}
