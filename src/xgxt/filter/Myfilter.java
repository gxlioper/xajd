package xgxt.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class Myfilter implements Filter {

	public void destroy() {
		// TODO 自动生成方法存根
		
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO 自动生成方法存根
		request.setCharacterEncoding("gbk");
		response.setCharacterEncoding("gbk");
		chain.doFilter(request,response);
		
	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO 自动生成方法存根
		
	}

}