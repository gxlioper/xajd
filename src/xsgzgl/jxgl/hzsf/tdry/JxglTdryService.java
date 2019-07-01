package xsgzgl.jxgl.hzsf.tdry;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.comm.MessageInfo;
import xgxt.comm.SearchRsModel;
import xgxt.form.User;
import xsgzgl.comm.BasicService;

public class JxglTdryService extends BasicService{
	JxglTdryDAO dao = new JxglTdryDAO();
	/**
	 * 获得表头
	 * @return
	 */
	public List<HashMap<String, String>> getTopTr() {
		DAO dao = DAO.getInstance();
		String[] en = new String[] { "", "xn", "bzjbmc","bzmc","grrymc" };
		String[] cn = new String[] { "", "学年", "编制级别", "编制名称", "团队荣誉名称" };
		return dao.arrayToList(en, cn);
	}
	/**
	 * 团队荣誉查询
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	public ArrayList<String[]> tdryCx(JxglTdryForm model) throws Exception {
		StringBuilder SearchTj = new StringBuilder();
		String[] query = model.getQuery().split("!!splitOne!!");
		String xn = query[0];
		String bzjbdm = query[1];
		String tdrydm = query[2];
		String bzdm = query[3];
		
		if(xn!=null&&!"".equalsIgnoreCase(xn)){
			SearchTj.append(" and xn = '");
			SearchTj.append(xn);
			SearchTj.append("' ");
		}
		if(bzjbdm!=null&&!"".equalsIgnoreCase(bzjbdm)){
			SearchTj.append(" and bzjbdm = '");
			SearchTj.append(bzjbdm);
			SearchTj.append("' ");
		}
		if(tdrydm!=null&&!"".equalsIgnoreCase(tdrydm)){
			SearchTj.append(" and tdrydm = '");
			SearchTj.append(tdrydm);
			SearchTj.append("' ");
		}
		if(bzdm!=null&&!"".equalsIgnoreCase(bzdm)){
			SearchTj.append(" and bzdm = '");
			SearchTj.append(bzdm);
			SearchTj.append("' ");
		}
		return dao.tdryCx(model,SearchTj.toString());
	}
	/**
	 * 获得团队荣誉map
	 * @param model
	 * @return
	 */
	public HashMap<String, String> getTdryMap(JxglTdryForm model) {
		return dao.getTdryMap(model);
	}
	/**
	 * 团队荣誉保存
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	public String tdryBc(JxglTdryForm model) throws Exception {
		String bzjbdm = model.getBzjbdm();
		if("1".equalsIgnoreCase(bzjbdm)){
			bzjbdm = "tj";
		}else if("2".equalsIgnoreCase(bzjbdm)){
			bzjbdm = "yj";
		}else if("3".equalsIgnoreCase(bzjbdm)){
			bzjbdm = "lj";
		}
		model.setBzjbdm(bzjbdm);
		return dao.tdryBc(model)?MessageInfo.MESSAGE_SAVE_SUCCESS : MessageInfo.MESSAGE_SAVE_FALSE;
	}
	/**
	 * 团队荣誉修改保存
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	public String tdryXg(JxglTdryForm model) throws Exception {
		return dao.tdryXg(model)?MessageInfo.MESSAGE_SAVE_SUCCESS : MessageInfo.MESSAGE_SAVE_FALSE;
	}
	/**
	 * 团队荣誉获奖删除
	 * @param model
	 * @return
	 * @throws SQLException 
	 */
	public String tdrySc(JxglTdryForm model) throws SQLException {
		String[] pkValue = model.getPkValue().split("!!splitOne!!");
		List<String[]> params = new ArrayList<String[]>();
		for(int i = 0; i < pkValue.length; i++){
			String[] pkV = pkValue[i].split("!!@@!!");
			params.add(pkV);
		}
		return dao.tdrySc(params)?MessageInfo.MESSAGE_DEL_SUCCESS : MessageInfo.MESSAGE_DEL_FALSE;
	}
	/**
	 * 创建HTML查询页面
	 * @param rsModel
	 * @param rsArrList
	 * @param user
	 * @return
	 */
	public String createSearchHTML(SearchRsModel rsModel, ArrayList<String[]> rsArrList, User user) {
		StringBuilder html = new StringBuilder();
		String ie = rsModel.getIe();
		if (rsArrList != null && rsArrList.size() > 0) {
			for (int i = 0; i < rsArrList.size(); i++) {
				String[] rs = rsArrList.get(i);
				html.append("<tr onclick=\"rowOnClick(this);\" ondblclick=\"\">");
				html.append("<td style=\"width:5px\">");
				html.append("<input type='checkbox' name='div_pkValue'  ");
				html.append("  id='pkValue_" + i + "' ");
				html.append(" value='" + replaceHtml(rs[0]) + "'/> ");
				html.append("</td>");
				// --------------------构建HTML扩展字段与分数除外------------------------
				for (int j = 1; j < rs.length; j++) {
					html.append("<td align=\"left\" nowrap=\"nowrap\" width=\"" + 100 / (rs.length - 1) + "%\" ");
					// IE8
					if ("5.8".equalsIgnoreCase(ie)) {
						html.append("height=\"28.5px\"");
					} else {
						html.append("height=\"29px\"");
					}
					html.append(">");
					html.append(replaceHtml(rs[j]));
					html.append("</td>");
				}
				html.append("</tr>");
			}
		}
		return html.toString();
	}
	/**
	 * 获得团营连列表
	 * @param type
	 * @param currXn
	 * @return
	 */
	public List<HashMap<String,String>> getBzdmList(JxglTdryForm model) {
		return dao.getBzdmList(model);
	}
	/**
	 * 获得编制级别代码列表
	 * @param model
	 * @return
	 */
	public List<HashMap<String,String>> getZjList(JxglTdryForm model) {
		return dao.getZjList(model);
	}
	/**
	 * 验证保存信息
	 * @param model
	 * @return
	 */
	public String checkSaveInfo(JxglTdryForm model) {
		return dao.isExist(model)?"该学生团队荣誉已存在":"true";
	}

}
