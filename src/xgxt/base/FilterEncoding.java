/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: LP</p>
 * <p>Version: 1.0</p>
 * <p>Time:2009-12-2 下午03:35:20</p>
 */
package xgxt.base;
import java.io.IOException;

import javax.servlet.Filter;

import javax.servlet.FilterChain;

import javax.servlet.FilterConfig;

import javax.servlet.ServletException;

import javax.servlet.ServletRequest;

import javax.servlet.ServletResponse;

public class FilterEncoding implements Filter {

	@SuppressWarnings("unused")
	private FilterConfig filterConfig=null;
	private String encoding=null;


	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig=filterConfig;
		this.encoding=filterConfig.getInitParameter("encoding");
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		if(request.getCharacterEncoding() == null){
			request.setCharacterEncoding(encoding);   
		}
		response.setContentType("text/html;charset="+encoding);
		chain.doFilter(request, response);
	}

	public void destroy() {
		this.encoding=null;
		this.filterConfig=null;
	}
}


