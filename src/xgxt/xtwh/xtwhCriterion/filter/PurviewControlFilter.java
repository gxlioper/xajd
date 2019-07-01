package xgxt.xtwh.xtwhCriterion.filter;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import xgxt.DAO.DAO;
import xgxt.action.Base;

/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: ������</p>
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>Company: zfsoft</p>
 * <p>Author: LUNING</p>
 * <p>Version: 1.0</p>
 * <p>Time:2011-4-28</p>
 */

public class PurviewControlFilter implements Filter {
	
	private String logoutUrl = "http://127.0.0.1:7070/xgxt/login.jsp";
	private String sessionOutUrl = "/xgxt/sessionForward.jsp"; 
	public void destroy() {
		
	}
	
	/**
	 * 
	 * @param req
	 * @param res
	 * @param fc
	 * @throws IOException
	 * @throws ServletException
	 */
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain fc) throws IOException, ServletException {
		if((req instanceof HttpServletRequest) && (res instanceof HttpServletResponse)){ 
			HttpServletRequest request = (HttpServletRequest) req;
			String srcUrl = request.getRequestURL().toString();
			boolean jywebFlag = (request.getParameter("jytype")!=null) && request.getParameter("jytype").equalsIgnoreCase("jyweb")? false : true;
			boolean yxxtFlag = (srcUrl.contains("viewSsInfo") || srcUrl.contains("getSsinfo")) ? true : false;
			
		
			if((srcUrl.contains("chkXmlgLoginDbCenter.do"))
					  ||(srcUrl.contains("chkLogin")) 
					  ||(srcUrl.contains("xhsfLogin.do"))
					  ||(srcUrl.contains("initMenu.do"))
					  ||(srcUrl.contains("showNews.do"))
					  || !jywebFlag 
					  || yxxtFlag
					  ||(srcUrl.contains("analyse.do"))
					  ||(srcUrl.contains("hnsfSSOLogin.do"))){//
			}else{
			//ȡrequest
			
			
			
			//��request��ȡ����ز�������װ��model�
			FilterService myService = new FilterService();
			
			FilterModel filterModel = myService.setFilterAttribute(request);
			
			//�����һ�η��ʸ�ģ�飬�����û����ͷ���·���������û���ӵ�еİ�ťid�Ͳ�����Χ����������"!!"ƴ��
			if(filterModel.getPurview()==null){
				filterModel = myService.getUserPurview(filterModel);
			}
			request.setAttribute("purview", filterModel.getPurview());
			request.setAttribute("title", filterModel.getTitle());
			request.setAttribute("operateBound", filterModel.getOperateBound());
			}
		}
		fc.doFilter(req, res);
	}

	public void init(FilterConfig arg0) throws ServletException {
		
	}

}
