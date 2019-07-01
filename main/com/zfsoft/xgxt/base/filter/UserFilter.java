/**
 * @部门:学工产品事业部
 * @日期：2015-9-23 上午11:05:13 
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
import javax.servlet.http.HttpSession;

import com.zfsoft.utils.StringUtil;


public class UserFilter implements Filter {

	private String loginUrl = null;
	private String wechatUrl = null;
	private List<String> excludedUrls = new ArrayList<String>();
	
	/*
	      描述: @see javax.servlet.Filter#destroy()
	 */

	public void destroy() {
		
	}

	/*
	      描述: @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
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
			request.getRequestDispatcher(loginUrl).forward(request, response);
		}else if(path.equals("wechat.do")){
			System.out.println("url:"+url);
			String code =request.getParameter("code");//获取微信服务器跳转回调redirect_uri所传递参数code,如需了解，请自行查看微信公众平台开发者文档网页授权菜单
			System.out.println("code"+code);
			String logout = request.getParameter("logout");
			String newUrl=wechatUrl+"?code="+"041DyzPM0FZkUh2F3CSM0ivDPM0DyzPm"+"&logout="+logout;
			response.sendRedirect(newUrl);
		} else {
			chain.doFilter(req, res);
		}
	}

	/*
	      描述: @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
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
