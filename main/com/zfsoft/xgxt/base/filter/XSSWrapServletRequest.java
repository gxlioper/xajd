package com.zfsoft.xgxt.base.filter;


import java.util.Iterator;
import java.util.Map;

import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.owasp.validator.html.AntiSamy;
import org.owasp.validator.html.CleanResults;
import org.owasp.validator.html.Policy;
import org.springframework.core.io.Resource;
import org.springframework.web.context.support.ServletContextResourceLoader;

/**
 * 
 * 包装HttpServletRequestWrapper
 * 
 * @author zhangxiaobin
 *
 */
public class XSSWrapServletRequest extends HttpServletRequestWrapper {
	
	private static Log logger = LogFactory.getLog(XSSWrapServletRequest.class);
	
	private Policy xssPolicy; 
	
	private ServletContext xssServletContext;
	
	private String policyPath;

	
	
	@Override
	public String[] getParameterValues(String name) {
		String[] parameterValues = super.getParameterValues(name);
		if(parameterValues != null){
			for (int i = 0; i < parameterValues.length; i++) {
				parameterValues[i] = xssClean(parameterValues[i]);
			}
		}
		return parameterValues;
	}

	@Override
	public String getParameter(String name) {
		if(super.getParameter(name) != null){
			return xssClean(super.getParameter(name));
		}
		return super.getParameter(name);
	}

	@Override
	public Map<String, String[]> getParameterMap() {
		Map<String,String[]> request_map = super.getParameterMap();
        Iterator iterator = request_map.entrySet().iterator();
        while(iterator.hasNext()){
            Map.Entry me = (Map.Entry)iterator.next();
            String[] values = (String[])me.getValue();
            for(int i = 0 ; i < values.length ; i++){
                values[i] = xssClean(values[i]);
            }
        }
        return request_map;
	}
	
	private String xssClean(String value) {
        AntiSamy antiSamy = new AntiSamy();
        try {
        	//初始化配置
    		Resource resource = new ServletContextResourceLoader(xssServletContext).getResource(policyPath);
    		try {
    			xssPolicy = Policy.getInstance(resource.getFile());
    		} catch (Exception e) {
    			e.printStackTrace();
    		} 
        	
            final CleanResults cr = antiSamy.scan(value, xssPolicy);
            //安全的HTML输出
//            String cleanHTML = StringUtils.trim(cr.getCleanHTML());
            String cleanHTML = cr.getCleanHTML();
            String escapeHtml = StringEscapeUtils.unescapeHtml(cleanHTML);
            String replaceAll = escapeHtml.replaceAll((antiSamy.scan("&nbsp;", xssPolicy)).getCleanHTML(), "&nbsp;");
            if(logger.isDebugEnabled()){
            	logger.debug("["+value+"]--["+replaceAll+"]");
            }
            return replaceAll;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }
	
	
	
	public XSSWrapServletRequest(HttpServletRequest request, FilterConfig filterConfig) {
		super(request);
		xssServletContext = filterConfig.getServletContext();
		policyPath = filterConfig.getInitParameter(XSSFilter.POLICY_PATH_NAME);
	}

	public XSSWrapServletRequest(HttpServletRequest request) {
		super(request);
	}
	
}