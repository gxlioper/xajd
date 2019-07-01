/**
 * @部门:学工产品事业部
 * @日期：2018-4-10 下午04:54:42 
 */  
package xgxt.base;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张昌路[工号:982]
 * @时间： 2018-4-10 下午04:54:42 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class HostlFilter  implements Filter {

	/*
	      描述: @see javax.servlet.Filter#destroy()
	 */
	protected Log logger = LogFactory.getLog(HostlFilter.class);
	
	@Override
	public void destroy() {
		// TODO 自动生成方法存根
		
	}

	/*
	      描述: @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException,
    ServletException {
		
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		
		// 头攻击检测
		String requestHost = request.getHeader("host");
		logger.info("-----host:"+requestHost);
		if (requestHost != null && !ServerWhiteListUtil.isWhite(requestHost)) {
		    response.setStatus(403);
		    return;
		}
	 
		if (chain != null){
			chain.doFilter(request, response);
		}
	}

	/*
	      描述: @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO 自动生成方法存根
		
	}

}
