
package xgxt.base.reqestuser;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import xgxt.daoActionLogic.StandardOperation;
import xgxt.utils.date.DateUtils;

public class UserInfoFilter implements Filter{

	public void destroy() {
		
	}

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain fc) throws IOException, ServletException {
		if(req instanceof HttpServletRequest && res instanceof HttpServletResponse){
			HttpServletRequest tempReq = (HttpServletRequest) req;
			HttpSession session = tempReq.getSession();
			UserInfo user = new UserInfo();
			user.setHost(tempReq.getRemoteHost());
			user.setIp(tempReq.getRemoteAddr());
			user.setTime(DateUtils.getTime());
			Object userObj = session.getAttribute("userName");
			if(userObj != null){
				user.setYhm(userObj.toString());				
			} else {
				user.setYhm("null");
			}
			StandardOperation.setUser(user);
		}		
		fc.doFilter(req, res);
	}

	public void init(FilterConfig arg0) throws ServletException {
		
	}

}
