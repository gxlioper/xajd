/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-9-23 ����11:05:13 
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
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zfsoft.utils.StringUtil;
import xgxt.utils.String.StringUtils;


public class UserFilter implements Filter {

	private String loginUrl = null;
	private String wechatUrl = null;
	private List<String> excludedUrls = new ArrayList<String>();
	
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
		HttpSession session = request.getSession();
		
		String url = request.getRequestURL().toString();
		
		String userName = (String)session.getAttribute("userName");
		String[] urlArr = url.split("/");
		String tPath = urlArr[urlArr.length-1];
		String path = tPath.split(";")[0];
		if (StringUtil.isNull(userName) && !excludedUrls.contains(path)){
//			response.sendRedirect(request.getContextPath()+loginUrl);
			String jqueryStr = request.getQueryString();
			String method = request.getParameter("method");
			if(StringUtils.isNotNull(method)&&method.equals("getHdxx")){
				//�����ר��,ͳһ�����֤��¼�Ὣsession���
				Cookie userCookie=new Cookie(session.getId(),url+"?"+jqueryStr);
				userCookie.setMaxAge(3*60);   //�����Ϊ3���� 3*60
				userCookie.setPath(request.getContextPath());
				response.addCookie(userCookie);
			}
			request.getRequestDispatcher(loginUrl).forward(request, response);
		}else if(path.equals("wechat.do")){
			System.out.println("url:"+url);
			String code =request.getParameter("code");//��ȡ΢�ŷ�������ת�ص�redirect_uri�����ݲ���code,�����˽⣬�����в鿴΢�Ź���ƽ̨�������ĵ���ҳ��Ȩ�˵�
			System.out.println("code"+code);
			String logout = request.getParameter("logout");
			String newUrl=wechatUrl+"?code="+"041DyzPM0FZkUh2F3CSM0ivDPM0DyzPm"+"&logout="+logout;
			response.sendRedirect(newUrl);
		} else {
			chain.doFilter(req, res);
		}
	}

	/*
	      ����: @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */

	public void init(FilterConfig config) throws ServletException {
		this.wechatUrl=config.getInitParameter("wechatUrl");
		this.loginUrl = config.getInitParameter("loginUrl");
		String[] urls = config.getInitParameter("excludedUrls").split(",");
		
		for (String url : urls){
			excludedUrls.add(url.trim());
		}
	}

}
