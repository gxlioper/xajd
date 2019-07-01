/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2018-4-10 ����04:54:42 
 */  
package xgxt.base;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2018-4-10 ����04:54:42 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class HostlFilter  implements Filter {

	/*
	      ����: @see javax.servlet.Filter#destroy()
	 */
	protected Log logger = LogFactory.getLog(HostlFilter.class);
	
	@Override
	public void destroy() {
		// TODO �Զ����ɷ������
		
	}

	/*
	      ����: @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException,
    ServletException {
		
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		
		// ͷ�������
		String requestHost = request.getHeader("host");
		logger.info("-----host:"+requestHost);
		if (requestHost != null && !ServerWhiteListUtil.isWhite(requestHost)) {
		    response.setStatus(403);
		    return;
		}
	 
		if (chain != null){
			chain.doFilter(request, response);
		}
	}

	/*
	      ����: @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO �Զ����ɷ������
		
	}

}
