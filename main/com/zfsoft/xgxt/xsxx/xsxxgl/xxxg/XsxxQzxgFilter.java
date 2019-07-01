package com.zfsoft.xgxt.xsxx.xsxxgl.xxxg;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import xsgzgl.xsxx.general.qzxgmdsz.QzxgmdszService;

import com.zfsoft.utils.StringUtil;

public class XsxxQzxgFilter implements Filter {

	private String redirectUrl;

	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		HttpSession session = ((HttpServletRequest) request).getSession();
		String userName = (String) session.getAttribute("userName");
		String userType = (String) session.getAttribute("userType");
		QzxgmdszService qzxgService = new QzxgmdszService();
		boolean sfqz = false;
		String url = ((HttpServletRequest) request).getRequestURL().toString();
		if (!StringUtil.isNull(userName) && "stu".equals(userType)) {
			//  «∑Ò«ø÷∆
			sfqz = qzxgService.Sfqzxg(userName);
		}
		sfqz = url.contains("stuPic.jsp") ? false : sfqz;
		if (sfqz) {
			request.setAttribute("fromQzxg", "yes");
			request.getRequestDispatcher(redirectUrl)
					.forward(request, response);
		} else {
			chain.doFilter(request, response);
		}

	}

	public void init(FilterConfig config) throws ServletException {
		this.redirectUrl = config.getInitParameter("redirectUrl");
	}

}
