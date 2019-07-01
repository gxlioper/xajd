package xgxt.base;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import xgxt.xtwh.mmzh.MmZhService;


/**
 * 
 * 
 * 类名称：SimplePasswordFilter
 * 类描述：简单密码密码拦截<li>本拦截器请配置在权限拦截器之</li>
 * 创建人：Jiangdong.Yi
 * 创建时间�?013-10-08
 * 修改人：Jiangdong.Yi
 * 修改时间�?013-10-08
 * 修改备注�?
 * @version 
 *
 */
public class SimplePasswordFilter implements Filter {
	private String[] outPath=null;
	
	/*
	      描述: @see javax.servlet.Filter#destroy()
	 */
	
	public void destroy() {
		this.outPath=null;
	}

	/*
	      描述: @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	
	public void doFilter(ServletRequest servletrequest,
			ServletResponse servletresponse, FilterChain filterchain)
			throws IOException, ServletException {
		HttpServletRequest request=(HttpServletRequest)servletrequest;
		HttpSession session = request.getSession();
		String path=request.getServletPath();
		Object yhmmdj = session.getAttribute("yhmmdj");
		
		//密码强度验证  强度小于等于1,则为弱密,跳转密码修改页面
        if(yhmmdj != null && !"".equals(yhmmdj.toString()) && Integer.valueOf(yhmmdj.toString()) <= 1){
        	RequestDispatcher dispatcher = request.getRequestDispatcher("xgMmQz.jsp");
        	boolean isPass=false;
        	
        	if(this.outPath != null){
        		for (int i = 0; i < this.outPath.length; i++) {
    				if(path.indexOf(this.outPath[i]) > -1){
    					isPass=true;
    					break;
    				}
    			}
        	}
        	
    		if(!isPass){
    			MmZhService mmzh = new MmZhService();
    			String userName = session.getAttribute("userName").toString();
    			request.setAttribute("wtid", mmzh.getmbjl(userName).get("wtid"));
    			request.setAttribute("wtda", mmzh.getmbjl(userName).get("wtda"));
    			request.setAttribute("mbList", mmzh.getMbList());
    			dispatcher.forward(servletrequest, servletresponse);
    		}else{
    			filterchain.doFilter(servletrequest, servletresponse);
    		}
        }else{
        	filterchain.doFilter(servletrequest, servletresponse);
        }
	}

	/*
	    描述: @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	*/
	
	public void init(FilterConfig filterconfig) throws ServletException {
	if(filterconfig.getInitParameter("outPath") != null){
		this.outPath = filterconfig.getInitParameter("outPath").split(",");
	}
		
}
}
