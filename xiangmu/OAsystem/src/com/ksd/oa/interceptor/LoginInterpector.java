package com.ksd.oa.interceptor;

import com.ksd.oa.entity.User;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

public class LoginInterpector extends MethodFilterInterceptor{

	@Override
	public String doIntercept(ActionInvocation invocation) throws Exception {
		User user = (User) invocation.getInvocationContext().getSession().get("user");
		if(user == null){
			return "input";
		}
		return invocation.invoke();
	}

	
}
