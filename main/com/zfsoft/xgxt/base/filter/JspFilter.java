/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-9-23 ����02:08:33 
 */  
package com.zfsoft.xgxt.base.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;


public class JspFilter implements Filter {

	private List<String> excludedFiles = new ArrayList<String>();
	private static final String error = "/message/prompt.jsp";
	
	/*
	      ����: @see javax.servlet.Filter#destroy()
	 */

	public void destroy() {

	}

	/*
	      ����: @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */

	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		String url = request.getRequestURL().toString();
		String[] urlArr = url.split("/");
		
		if (url.endsWith(".jsp") && !excludedFiles.contains(urlArr[urlArr.length-1])){
			request.setAttribute("message", MessageUtil.getText(MessageKey.SYS_AUTH_FAIL));
			request.getRequestDispatcher(error).forward(request, response);
		} else {
			chain.doFilter(req, res);
		}
	}

	/*
	      ����: @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */

	public void init(FilterConfig config) throws ServletException {
		
		String[] urls = config.getInitParameter("excludedFiles").split(",");
		
		for (String url : urls){
			excludedFiles.add(url.trim());
		}
	}

}
