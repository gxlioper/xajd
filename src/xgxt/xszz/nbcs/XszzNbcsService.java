package xgxt.xszz.nbcs;

import javax.servlet.http.HttpServletRequest;

import xgxt.utils.FormModleCommon;

public class XszzNbcsService {
	
	XszzNbcsDAO dao = new XszzNbcsDAO();
	
	/**
	 * 发送列表
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
			sb.append(" (case when xxsh='未审核' then '' else 'disabled' end) disabled,");
			sb.append(" (case when nvl(xysh,'未审核') in ('通过') then '#FFFFFF' else '#CCCCCC' end) bgcolor, ");
		}else {
			sb.append(" '' disabled, ");
			sb.append(" (case when nvl(xxsh,'未审核') in ('通过') then '#FFFFFF' else '#CCCCCC' end) bgcolor, ");
		}
		
		request.setAttribute("clientColumns", sb.toString());
	}
	
	/**
	 * 获取学生是否有放款记录
	 * @param xh
	 * @return
	 */
	public boolean getIsFfdk(String xh) {
		
		return Boolean.valueOf(dao.getIsFfdk(xh));
	}
}
