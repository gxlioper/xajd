/**
 * @部门:学工产品事业部
 * @日期：2015-9-23 上午11:05:13 
 */  
package com.zfsoft.xgxt.base.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zfsoft.license.LicenseOps;
import com.zfsoft.license.LicenseOpsException;
import com.zfsoft.license.Status;
import com.zfsoft.xgxt.base.util.DateTranCnDate;
import com.zfsoft.xgxt.base.util.DateTranCnDate.FomartDateType;

/**
 * 
 * @系统名称: 软件授权过滤器
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： xiaxia[工号:1104]
 * @时间： 2016-8-3 下午03:56:49 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class SystemAuthFilter implements Filter {
	private String redirectUrl = null;
	private String invalUrl = null;
	public void destroy() {
	}
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		HttpSession session = request.getSession();
		ServletContext sc = session.getServletContext();
		Integer licenseStatus=(Integer)sc.getAttribute("SERVLET_CONTEXT_LICENSE_CHECK_STATUS");
		request.setAttribute("licenseStatus", licenseStatus);
		try {
			String startDate=DateTranCnDate.fomartDateToCn(LicenseOps.getInstance().getStartDate(),FomartDateType.day);
			String expireDate=DateTranCnDate.fomartDateToCn(LicenseOps.getInstance().getExpireDate(),FomartDateType.day);
			request.setAttribute("expireDate", expireDate);
			request.setAttribute("startDate", startDate);
			//软件使用过期
			if (!"1".equals(LicenseOps.getInstance().getDevMode())&&Status.EXPIRED==licenseStatus){
				request.getRequestDispatcher(redirectUrl).forward(request, response);
			} else if(!"1".equals(LicenseOps.getInstance().getDevMode())&&(Status.NO_LICENSE_FILE==licenseStatus||Status.INVALID==licenseStatus)){
				request.getRequestDispatcher(invalUrl).forward(request, response);
			}
			else{
			chain.doFilter(req, res);			}
		} 
		catch (LicenseOpsException e) {
			e.printStackTrace();
		}

	}
	public void init(FilterConfig config) throws ServletException {
		this.redirectUrl = config.getInitParameter("redirectUrl");
		this.invalUrl = config.getInitParameter("invalUrl");
	}

}
