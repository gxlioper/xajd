package xgxt.base;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;

import com.zfsoft.zfca.tp.cas.client.ZfssoConfig;
import com.zfsoft.zfca.tp.cas.util.Tool;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.io.IOException;

/** 
 * 如果用户已登录，并且业务系统通过认证中心认证，则用户访问目标页面时，到认证中心验证用户的功能模块访问
 * 
 */
public class CenterAuthorityFilter implements Filter {
	protected FilterConfig filterConfig = null;
	
	private String identificationnumber;

	private List<String> notCheckURLList = new ArrayList<String>();

	public void doFilter(ServletRequest servletRequest,
			ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		String dqdz=request.getRequestURI().substring(request.getContextPath().length()+1);
		if(!Tool.isNull(request.getQueryString())){
			dqdz=dqdz+"?"+request.getQueryString();
		}

		HttpSession session = request.getSession();
		if ((!checkRequestURIIntNotFilterList(request))&& session.getAttribute("userName") == null) {
			response.sendRedirect(ZfssoConfig.casurl+ "/filtererr.jsp");
			return;
		} else if (!checkRequestURIIntNotFilterList(request)) {
			String userName =  session.getAttribute("userName").toString();//取用户名改一下就好了
			if (!userName.equalsIgnoreCase("admin")) {
				List list=null;
				try {
					list = GetAuthorityList(userName,identificationnumber);
				} catch (Exception e) {				
					e.printStackTrace();
				}
				if(list!=null){			
				for (int i=0;i<list.size();i++) {
					HashMap map = (HashMap) list.get(i);
					if(dqdz.contains(map.get("fwdz").toString())){
						response.sendRedirect(ZfssoConfig.casurl+"/qxmessage.jsp");
						return;
					}
				}
				}
			}
		}
		filterChain.doFilter(servletRequest, servletResponse);
	}
	
	public List GetAuthorityList(String userName,String ywxtdm) {
		List list = new ArrayList();
		List a = new ArrayList();
		try {			
			Service service = new Service();
			Call call = (Call) service.createCall();
			String endpoint = ZfssoConfig.casurl + "/axis/RzzxConfManage?wsdl";	
			call.setTargetEndpointAddress(new java.net.URL(endpoint));
			call.addParameter( "arg1",  org.apache.axis.encoding.XMLType.XSD_STRING , ParameterMode.IN );
			call.addParameter( "arg2",  org.apache.axis.encoding.XMLType.XSD_STRING , ParameterMode.IN );
			QName qn = new QName("urn:BeanService", "List");
			call.registerTypeMapping(List.class, qn, 
					new org.apache.axis.encoding.ser.ArraySerializerFactory(List.class, qn), 
					new org.apache.axis.encoding.ser.ArrayDeserializerFactory(qn));
			call.setOperationName(new QName(endpoint, "getUserRights"));
			call.getMessageContext().setUsername("zfim");
			call.getMessageContext().setPassword("zfim");
			call.setReturnType(qn,ArrayList.class);
			list =(List)call.invoke(new Object[] {userName,ywxtdm});
			a=(List)list.get(0);
			list=null;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return a;
    }
	
	public void destroy() {
		notCheckURLList.clear();
	}

	private boolean checkRequestURIIntNotFilterList(HttpServletRequest request) {
		String uri = request.getServletPath()
				+ (request.getPathInfo() == null ? "" : request.getPathInfo());
		return notCheckURLList.contains(uri);
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
		this.identificationnumber=filterConfig.getInitParameter("identificationnumber");
		String notCheckURLListStr = filterConfig.getInitParameter("notCheckURLList");
		if (notCheckURLListStr != null) {
			StringTokenizer st = new StringTokenizer(notCheckURLListStr, ";");
			notCheckURLList.clear();
			while (st.hasMoreTokens()) {
				notCheckURLList.add(st.nextToken().trim());
			}
		}
	}
}
