package xgxt.xszz.nbcs;

import javax.servlet.http.HttpServletRequest;

import xgxt.utils.FormModleCommon;

public class XszzNbcsService {
	
	XszzNbcsDAO dao = new XszzNbcsDAO();
	
	/**
	 * �����б�
	 * @param request
	 * @param flg
	 */
	public void setList(HttpServletRequest request,String flg) {
		if ("".equals(flg)) {
			
		}
		
		request.setAttribute("shztList",dao.getChkList(3));
		
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
	}
	
	public void getCustomAudiColumn(HttpServletRequest request, String yhlx) {
		
		StringBuilder sb = new StringBuilder();
		
		if (yhlx.equalsIgnoreCase("xy")) {
			sb.append(" (case when xxsh='δ���' then '' else 'disabled' end) disabled,");
			sb.append(" (case when nvl(xysh,'δ���') in ('ͨ��') then '#FFFFFF' else '#CCCCCC' end) bgcolor, ");
		}else {
			sb.append(" '' disabled, ");
			sb.append(" (case when nvl(xxsh,'δ���') in ('ͨ��') then '#FFFFFF' else '#CCCCCC' end) bgcolor, ");
		}
		
		request.setAttribute("clientColumns", sb.toString());
	}
	
	/**
	 * ��ȡѧ���Ƿ��зſ��¼
	 * @param xh
	 * @return
	 */
	public boolean getIsFfdk(String xh) {
		
		return Boolean.valueOf(dao.getIsFfdk(xh));
	}
}
