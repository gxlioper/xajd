package xgxt.xszz.tjgy;

import javax.servlet.http.HttpServletRequest;

import xgxt.form.SaveForm;
import xgxt.form.User;

public class XxcjService {
	
	private XxcjDAO dao = new XxcjDAO();
	
	/**
	 * 保存数据
	 * @param form
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean saveCjxx(SaveForm form, XxcjForm model,
			HttpServletRequest request) throws Exception {
		
		return dao.submitData(form, model, request);
	}
	
	/**
	 * 修改数据
	 * @param form
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public boolean updateCjxx(SaveForm form, XxcjForm model,User user) throws Exception {
		
		return dao.updateData(form, model, user);
	}
	
	
	/**
	 * 下拉列表
	 * @param request
	 */
	public void setList(HttpServletRequest request){
		
		
		request.setAttribute("shztList",dao.getChkList(32));
		request.setAttribute("shjgList",dao.getChkList(27));
	}
	
	
	protected void setCustomAudiColumn(HttpServletRequest request, String flg){
		StringBuilder sb = new StringBuilder();
		
		if ("xysh".equals(flg)) {
			sb.append(" (case when xxsh='通过' or xxsh='不通过' then 'disabled' else '' end) disabled, ");
			sb.append(" (case when xysh='通过' or xysh='不通过' then 'disabled'  when xysh='退回' then 'th' else '' end) isdis, ");
		} else if ("xxsh".equals(flg) || "adminsh".equals(flg)) {
			sb.append("'' disabled,");
			sb.append(" (case when xxsh='通过' or xxsh='不通过' then 'disabled' when xxsh='退回' then 'th' else '' end) isdis, ");
		} else if ("jgcx".equals(flg)) {
			sb.append(" (case when xysh='通过' or xysh='不通过' then 'disabled' when xysh='退回' then 'th' else '' end) disabled, ");
		}
		
		request.setAttribute("clientColumns", sb.toString());
	}
	
}
