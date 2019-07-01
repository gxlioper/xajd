package xgxt.base;

import common.Globals;
import xgxt.action.Base;
import xgxt.utils.String.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Enumeration;

public class CheckSessionOutTimeFilter implements Filter {
	// 控制当session超时时，转接到的默认页面
	private String logoutUrl = "http://127.0.0.1:7070/xgxt/login.jsp";

	private String jyweblogoutUrl = "http://127.0.0.1:7070/xgxt/index.do"; // 就业网

	private String sessionOutUrl = "/xgxt/sessionForward.jsp";

	private String echoLogin = "/xgxt/echoLoginForward.jsp";

	public void destroy() {

	}

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain fc)
			throws IOException, ServletException {
		// TODO 判断session超时
		if ((req instanceof HttpServletRequest)
				&& (res instanceof HttpServletResponse)) {
			HttpServletRequest request = (HttpServletRequest) req;
			HttpServletResponse response = (HttpServletResponse) res;
			HttpSession session = request.getSession();
			Enumeration sessionElem = session.getAttributeNames();
			String srcUrl = request.getRequestURL().toString();
			String sPath = request.getServletPath().replace("/", "");
			

			String qStr = request.getQueryString();
			sPath = StringUtils.isNotNull(qStr) ? sPath + "?" + qStr : sPath;
		
			String jyweb = (String) session.getAttribute("jyweb");

			boolean isJyweb = !Base.isNull(jyweb) && "yes".equals(jyweb) ? false
					: true;// 就业网
			boolean jywebFlag = (request.getParameter("jytype") != null)
					&& request.getParameter("jytype").equalsIgnoreCase("jyweb") ? false
					: true;
			boolean yxxtFlag = (srcUrl.contains("viewSsInfo") || srcUrl
					.contains("getSsinfo")) ? true : false;
			// 北京联合外网访问新闻
			boolean bjlhWsNews = (srcUrl.contains("bjlhWwnews") || srcUrl
					.contains("newsContent"))
					&& Base.xxdm.equalsIgnoreCase(Globals.XXDM_BJLHDX) ? true
					: false;

			// 浙江传媒服务网页面显示绕过session检测
			boolean zjcmFwwFlag = (request.getParameter("fwwFlag") != null)
					&& request.getParameter("fwwFlag").equalsIgnoreCase("true") ? false
					: true;
			//免单点的url参数。
			boolean aotoSSOFlag = (request.getParameter("direct") != null) && request.getParameter("direct").equalsIgnoreCase("1") ? true : false;
			
//			System.out.println(srcUrl);
			//int i = 0;
			//while (sessionElem.hasMoreElements()) {
			//	sessionElem.nextElement();
			//	i++;
			//}

			if ((session.getAttribute("userName") == null) && !jywebFlag && yxxtFlag && !zjcmFwwFlag && !isJyweb
					&& !isJyweb) {
				System.out.println("session out of time,and direct to "
						+ jyweblogoutUrl);
				String encodeUrl = response.encodeRedirectURL(jyweblogoutUrl);
				response.sendRedirect(encodeUrl);
				request.setAttribute("timeover", "timeover");

			}
			if ((session.getAttribute("userName") == null) && (!(srcUrl.contains("chkXmlgLoginDbCenter"))
					&& !(srcUrl.contains("chkLogin"))
					&& !(srcUrl.contains("getPublicKey.do"))
					&& !(srcUrl.contains("index.do"))
					&& !(srcUrl.contains("chkLogout.do"))
					&& !(srcUrl.contains("mmzhgl_mmzh.do"))
					&& !(srcUrl.contains("xhsfLogin.do")) && jywebFlag
					&& !yxxtFlag && !bjlhWsNews
					&& !(srcUrl.contains("analyse.do"))
					&& !(srcUrl.contains("hnsfSSOLogin.do")) && zjcmFwwFlag && !aotoSSOFlag
					&& !(srcUrl.contains("zfxg_casLogin.do"))
					&& !(srcUrl.contains("hljKjxySSOLogin.do"))
					&& !(srcUrl.contains("thauthallSSOLogin.do"))
					&& !(srcUrl.contains("xsDaxxCx.do"))
					&& !(srcUrl.contains("queryXsDaxx.do")))) {//
				System.out.println("session out of time,and direct to "
						+ logoutUrl);
				// String encodeUrl = response.encodeRedirectURL(logoutUrl);
				// response.sendRedirect(encodeUrl);
				String sessionForward = Base.initProperties
						.get("sessionForward");
				session.setAttribute("sessionForward", sessionForward);
				response.sendRedirect(sessionOutUrl);
				return;
			}
			// if(!(srcUrl.contains("chkXmlgLoginDbCenter"))
			// && !(srcUrl.contains("chkLogin"))
			// && !(srcUrl.contains("index.do"))
			// && !(srcUrl.contains("xhsfLogin.do"))
			// && jywebFlag
			// && !yxxtFlag
			// && !(srcUrl.contains("analyse.do"))
			// && !(srcUrl.contains("yhInfo.do"))
			// && !(srcUrl.contains("hnsfSSOLogin.do"))
			// && zjcmFwwFlag){//
			// String sql = "select count(1) sum from gnmkdmb a where dyym = ?
			// and not exists " +
			// "(select 1 from yhqxb b where a.gnmkdm= b.gnmkdm and yhm = ?)";
			// DAO daoBase = DAO.getInstance();
			// String sum = daoBase.getOneRs(sql,new String[]{sPath,userName},
			// "sum");
			// if(sum!=null&&Integer.parseInt(sum)>0){
			// request.setAttribute("yhInfo", "没有权限访问该模块");
			// request.getRequestDispatcher("/yhInfo.do").forward(request,
			// response);
			// // response.sendRedirect("/xgxt/yhInfo.do");
			// return;
			// }
			// }

			String istysfrz = (String) session.getAttribute("istysfrz");
//			if (!"yes".equalsIgnoreCase(istysfrz)) {
//				DAO dao = DAO.getInstance();
//				String loginTime = (String) session.getAttribute("loginTime");
//				String userName = (String) session.getAttribute("userName");
//				String userType = (String) session.getAttribute("userOnLine");
//				if (!"chkLogin.do".equalsIgnoreCase(sPath)) {
//					if (i >= 2 && jywebFlag && !yxxtFlag && zjcmFwwFlag
//							&& isJyweb) {
//						String checkTime = " select count(1)num from xg_xtwh_yhdlb where yhm=? and yhlx=? and dlsj=? ";
//
//						String[] inputV = { userName, userType, loginTime };
//						String num = dao.getOneRs(checkTime.toString(), inputV,
//								"num");
//						if ("0".equalsIgnoreCase(num)) {
//							response.sendRedirect(echoLogin);
//							request.setAttribute("userName", null);
//							return;
//						}
//					}
//				}
//			}
		}

		fc.doFilter(req, res);

	}

	public void init(FilterConfig fconf) throws ServletException {
		// TODO 获得相应的跳出配置，控制当session超时时，转接到的页面
		String redirectUrl = fconf.getInitParameter("redirectUrl");
		String redirectjywebUrl = fconf.getInitParameter("redirectjywebUrl");
		String sessionOutUrl = fconf.getInitParameter("sessionForward");
		if (redirectUrl != null && !("".equals(redirectUrl))) {
			this.logoutUrl = redirectUrl;
		}
		if (redirectjywebUrl != null && !("".equals(redirectjywebUrl))) {
			this.jyweblogoutUrl = redirectjywebUrl;
		}
		if (sessionOutUrl != null && !("".equals(sessionOutUrl))) {
			this.sessionOutUrl = sessionOutUrl;
		}
	}

}
