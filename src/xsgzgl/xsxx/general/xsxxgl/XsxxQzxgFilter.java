package xsgzgl.xsxx.general.xsxxgl;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import xgxt.DAO.DAO;
import xsgzgl.xsxx.general.qzxgmdsz.QzxgmdszService;

import com.zfsoft.utils.StringUtil;

/**
 * 
 * 
 *<p>
 * <b>学生信息强制修改过滤器<b><br>
 * 2013-1-30
 *<p>
 * 
 * @author Penghui.Qu
 * 
 */
public class XsxxQzxgFilter implements Filter {

	private String redirectUrl;

	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		HttpSession session = ((HttpServletRequest) request).getSession();
		String userName = (String) session.getAttribute("userName");
		String userType = (String) session.getAttribute("userType");
		
		//////////////////////////判断是否使用新版本的学生信息(自定义表单)//////////////////////////////
		try {
			String xsxxZdybd =  (String)session.getAttribute("XSXX_ZDYBD");
			if(xsxxZdybd == null){
				String sql = "select count(1) num from GNMKDMB where dyym like 'xsxx_xsxxgl_cxzxs.do'";
				String num = DAO.getInstance().getOneRs(sql, new String[]{},"num");
				if(num != null && num.trim().equals("1")){
					xsxxZdybd = "1";
					redirectUrl = "/xsxx_xsxxxg_xgsq.do";
				}else{
					xsxxZdybd = "0";
				}
				session.setAttribute("XSXX_ZDYBD", xsxxZdybd);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		/////////////////////////////////////////////////////////////////////////////////////////////
		
		QzxgmdszService qzxgService = new QzxgmdszService();
		boolean sfqz = false;
		String url = ((HttpServletRequest) request).getRequestURL().toString();
		if (!StringUtil.isNull(userName) && "stu".equals(userType)) {
			// 是否强制
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
