package com.smbms.service;

import java.util.List;

import com.smbms.entity.Provider;

public interface ProviderService {
	List<Provider> findAll(String proCode,String proName,int start,int size);
	int findProCount(String proCode,String proName);
	boolean addProvider(Provider provider);
	Provider viewProvider(Integer id);
	boolean modifyProvider(Provider provider);
	int findCount(Integer id);
	boolean deletePro(Integer id);
}
