package com.zfsoft.xgxt.base.filter;



import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

/**
 * @author zhangxiaobin
 * 
 * 拦截XSS攻击
 *
 */
public class XSSFilter implements Filter {

	private FilterConfig filterConfig;
	
	private String policyPath;
	
	public String[] exclusionPatterns;
	
	public static final String POLICY_PATH_NAME = "policyPath";

	public static final String EXCLUSION_PATTERN = "exclusionPattern";
	
	public void destroy() {}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest hReq = (HttpServletRequest)request;
		String requestURI = hReq.getRequestURI();
		if(StringUtils.endsWith(requestURI, "out_login.html")){
			chain.doFilter(request, response);
		}else{
			chain.doFilter(new XSSWrapServletRequest(hReq, filterConfig), response);
		}
	}
	
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
		policyPath = filterConfig.getInitParameter(POLICY_PATH_NAME);
		String initParameters = filterConfig.getInitParameter(EXCLUSION_PATTERN);
		if(StringUtils.isNotBlank(initParameters)){
			exclusionPatterns = StringUtils.split(initParameters, ",");
		}
	}

	public FilterConfig getFilterConfig() {
		return filterConfig;
	}

	public void setFilterConfig(FilterConfig filterConfig) {
		this.filterConfig = filterConfig;
	}

	public String getPolicyPath() {
		return policyPath;
	}

	public void setPolicyPath(String policyPath) {
		this.policyPath = policyPath;
	}

}
