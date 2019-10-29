package com.smbms.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.smbms.dao.ProviderMapper;
import com.smbms.entity.Provider;
@Service
@Transactional
public class ProviderServiceImpl implements ProviderService{

	@Resource
	private ProviderMapper pm;
	@Override
	@Transactional(propagation=Propagation.SUPPORTS)
	public List<Provider> findAll(String proCode, String proName,int start,int size) {
		start=(start-1)*size;
		List<Provider> list = pm.findAll(proCode, proName,start,size);
		return list;
	}
	@Override
	public boolean addProvider(Provider provider) {
		boolean flag = false;
		int i = pm.addProvider(provider);
		if(i>0){
			flag = true;
		}
		return flag;
	}
	@Override
	public Provider viewProvider(Integer id) {
		Provider provider = pm.viewProvider(id);
		return provider;
	}
	@Override
	public boolean modifyProvider(Provider provider) {
		boolean flag = false;
		int i = pm.modifyProvider(provider);
		if(i>0){
			flag = true;
		}
		return flag;
	}
	@Override
	public boolean deletePro(Integer id) {
		boolean flag = false;
		int i = pm.deletePro(id);
		if(i>0){
			flag = true;
		}
		return flag;
	}
	@Override
	public int findCount(Integer id) {
		return pm.findCount(id);
	}
	@Override
	public int findProCount(String proCode, String proName) {
		return pm.findProCount(proCode, proName);
	}
}
