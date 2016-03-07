package com.alex.onlinetest.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class PrivilegeFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session = req.getSession();

		String url = req.getServletPath();
		if (url.contains(".js") || url.contains(".css") || url.contains("html") || url.contains(".jpg") || url.contains(".bmp") || url.contains("png") || url.contains("eot") || url.contains("woff")) {
			chain.doFilter(request, response);
		} else {
			if (session.getAttribute("userid") != null || url.contains("/login")) {
				chain.doFilter(request, response);
			} else {
				res.sendRedirect(req.getContextPath() + "/student/login");
			}
		}

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
