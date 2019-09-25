/**
 * @部门:学工产品事业部
 * @日期：2015-9-23 下午02:08:33 
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

import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import xgxt.utils.String.StringUtils;


public class JspFilter implements Filter {

	private List<String> excludedFiles = new ArrayList<String>();
	private static final String error = "/message/prompt.jsp";
	
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
		String url = request.getRequestURL().toString();
		String[] urlArr = url.split("/");
		HttpSession session = request.getSession();
		if (url.endsWith(".jsp") && !excludedFiles.contains(urlArr[urlArr.length-1])){
			request.setAttribute("message", MessageUtil.getText(MessageKey.SYS_AUTH_FAIL));
			request.getRequestDispatcher(error).forward(request, response);
		} else {
			String userType = (String) session.getAttribute("userType");
			if(urlArr[urlArr.length-1].equals("stuPage.jsp")&&"stu".equalsIgnoreCase(userType)){
				//用于首页拦截，如果有指定跳转页面则跳往指定页面（统一身份认证登录无法跳往指定页面）
				//=====================================开始===============
				String tourl = "";
				Cookie[] cookies = request.getCookies();
				for(Cookie cookie : cookies){
					if(cookie.getName().equals(session.getId())){
						tourl = cookie.getValue();
						cookie.setValue(null);
						cookie.setMaxAge(0);//生存时间为0
						cookie.setPath(request.getContextPath());  // 相同路径
						response.addCookie(cookie);
						break;
					}
				}
				if(StringUtils.isNotNull(tourl)){
					response.sendRedirect(tourl);
					return;
				}else {
					chain.doFilter(req, res);
				}
				//======================================结束============
			}else {
				chain.doFilter(req, res);
			}
		}
	}

	/*
	      描述: @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */

	public void init(FilterConfig config) throws ServletException {
		
		String[] urls = config.getInitParameter("excludedFiles").split(",");
		
		for (String url : urls){
			excludedFiles.add(url.trim());
		}
	}

}
