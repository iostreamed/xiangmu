package com.smbms.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class SysInterceptor extends HandlerInterceptorAdapter{

		@Override
		public boolean preHandle(HttpServletRequest request,
				HttpServletResponse response, Object handler) throws Exception {
			HttpSession session = request.getSession();
			Object obj = session.getAttribute("user");
			if(obj == null){
				String path = request.getContextPath();
				response.sendRedirect(path+"/401.jsp");
				return false;
			}
			return true;
		}
}
