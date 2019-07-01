package xsgzgl.jxgl.hzsf.dmwh;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.comm.MessageInfo;
import xgxt.comm.SearchRsModel;
import xgxt.form.User;
import xsgzgl.comm.BasicService;

/**
 * 军训管理-基础设置-代码维护
 * @author yeyipin
 * @since 2012.7.16
 */
public class JxglDmwhService extends BasicService{
	JxglDmwhDAO dao = new JxglDmwhDAO();
	/**
	 * 获得表头
	 * @param type
	 * @return
	 */
	public List<HashMap<String, String>> getTopTr(String type) {
		DAO dao = DAO.getInstance();
		String[] en = new String[] { "", "r", "grrydm","grrymc" };
		String[] cn = new String[] { "", "行号", "个人荣誉代码", "个人荣誉名称" };
		if("tdry".equalsIgnoreCase(type)){
			en = new String[] { "", "r", "tdrydm","tdrymc" };
			cn = new String[] { "", "行号", "团队荣誉代码", "团队荣誉名称" };
		}
		return dao.arrayToList(en, cn);
	}
	/**
	 * 提供其他程序使用的接口方法获得个人荣誉List
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	public List<HashMap<String, String>> getGrrydmList() throws Exception{
		return dao.getGrrydmList();
	}
	/**
	 * 提供其他程序使用的接口方法获得团队荣誉List
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	public List<HashMap<String, String>> getTdrydmList() throws Exception{
		return dao.getTdrydmList();
	}
	/**
	 * 获得个人荣誉List
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	public ArrayList<String[]> getGrryList(JxglDmwhForm model) throws Exception {
		return dao.getGrryList(model);
	}
	/**
	 * 获得团队荣誉List
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	public ArrayList<String[]> getTdryList(JxglDmwhForm model) throws Exception {
		return dao.getTdryList(model);
	}
	/**
	 * 个人荣誉保存
	 * @param model
	 * @param doType
	 * @return
	 * @throws Exception 
	 */
	public String grryBc(JxglDmwhForm model, String doType) throws Exception {
		if("add".equalsIgnoreCase(doType)){
			model.setGrrydm(changeGrrydm());
		}
		if(isGrryExist(model,doType)){
			return "该个人荣誉名称已存在";
		}
		return dao.grryBc(model,doType)?MessageInfo.MESSAGE_SAVE_SUCCESS : MessageInfo.MESSAGE_SAVE_FALSE;
	}
	/**
	 * 验证个人荣誉名称是否已被使用
	 * @param grrymc
	 * @return
	 */
	private boolean isGrryExist(JxglDmwhForm model,String doType) {
		return dao.isGrryExist(model,doType);
	}
	/**
	 * 获得个人荣誉代码
	 * @return
	 */
	private String changeGrrydm() {
		String max = dao.getMaxGrrydm();
		if(max==null){
			return "001";
		}else{
			max = String.valueOf((Integer.parseInt(max)+1));
			String pre = "";
			for(int i = 0; i < 3-max.length();i ++){
				pre+="0";
			}
			return pre+max;
		}
	}
	/**
	 * 团队荣誉保存
	 * @param model
	 * @param doType
	 * @return
	 * @throws Exception 
	 */
	public String tdryBc(JxglDmwhForm model, String doType) throws Exception {
		if("add".equalsIgnoreCase(doType)){
			model.setTdrydm(changeTdrydm());
		}
		if(isTdryExist(model,doType)){
			return "该团队荣誉名称已存在";
		}
		return dao.tdryBc(model,doType)?MessageInfo.MESSAGE_SAVE_SUCCESS : MessageInfo.MESSAGE_SAVE_FALSE;
	}
	/**
	 * 验证团队荣誉名称是否已被使用
	 * @param grrymc
	 * @return
	 */
	private boolean isTdryExist(JxglDmwhForm model,String doType) {
		return dao.isTdryExist(model,doType);
	}
	/**
	 * 获得团队荣誉代码
	 * @return
	 */
	private String changeTdrydm() {
		String max = dao.getMaxTdrydm();
		if(max==null){
			return "001";
		}else{
			max = String.valueOf((Integer.parseInt(max)+1));
			String pre = "";
			for(int i = 0; i < 3-max.length();i ++){
				pre+="0";
			}
			return pre+max;
		}
	}
	/**
	 * 个人荣誉删除
	 * @param model
	 * @return
	 * @throws SQLException
	 */
	public String grrySc(JxglDmwhForm model) throws SQLException {
		String[] pkValue = model.getPkValue().split("!!@@!!");
		List<String[]>params = new ArrayList<String[]>();
		for(int i = 0;i < pkValue.length;i++){
			params.add(new String[]{pkValue[i]});
		}
		return dao.grrySc(params)?MessageInfo.MESSAGE_DEL_SUCCESS : MessageInfo.MESSAGE_DEL_FALSE;
	}
	/**
	 * 团队人员删除
	 * @param model
	 * @return
	 * @throws SQLException
	 */
	public String tdrySc(JxglDmwhForm model) throws SQLException {
		String[] pkValue = model.getPkValue().split("!!@@!!");
		List<String[]>params = new ArrayList<String[]>();
		for(int i = 0;i < pkValue.length;i++){
			params.add(new String[]{pkValue[i]});
		}
		return dao.tdrySc(params)?MessageInfo.MESSAGE_DEL_SUCCESS : MessageInfo.MESSAGE_DEL_FALSE;
	}
	/**
	 * 验证个人荣誉信息
	 * @param model
	 * @return
	 */
	public String checkGrry(JxglDmwhForm model) {
		String message = "true";
		String[] pkValue = model.getPkValue().split("!!@@!!");
		for(int i = 0;i < pkValue.length;i++){
			//判断是否已被使用
			if(isGrryUsed(pkValue[i])){
				return "个人荣誉已被使用,不能被修改或删除";
			}
		}
		return message;
	}
	/**
	 * 判断个人荣誉是否已被使用
	 * @param pkValue
	 * @return
	 */
	private boolean isGrryUsed(String pkValue) {
		return dao.isGrryUsed(pkValue);
	}
	/**
	 * 验证团队荣誉信息
	 * @param model
	 * @return
	 */
	public String checkTdry(JxglDmwhForm model) {
		String message = "true";
		String[] pkValue = model.getPkValue().split("!!@@!!");
		for(int i = 0;i < pkValue.length;i++){
			//判断是否已被使用
			if(isTdryUsed(pkValue[i])){
				return "团队荣誉已被使用,不能被修改或删除";
			}
		}
		return message;
	}
	/**
	 * 判断团队荣誉是否已被使用
	 * @param pkValue
	 * @return
	 */
	private boolean isTdryUsed(String pkValue) {
		return dao.isTdryUsed(pkValue);
	}
	/**
	 * 创建页面
	 * @param rsModel
	 * @param rsArrList
	 * @param user
	 * @return
	 */
	public String createSearchHTML(SearchRsModel rsModel,
			ArrayList<String[]> rsArrList, User user) {
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
				html.append("<td align=\"left\" nowrap=\"nowrap\" width=\"10px\" ");
				// IE8
				if ("5.8".equalsIgnoreCase(ie)) {
					html.append("height=\"28.5px\"");
				} else {
					html.append("height=\"29px\"");
				}
				html.append(">");
				html.append(replaceHtml(rs[1]));
				html.append("</td>");
				// --------------------构建HTML扩展字段与分数除外------------------------
				for (int j = 2; j < rs.length; j++) {
					html.append("<td align=\"left\" nowrap=\"nowrap\" width=\"" + 100 / (rs.length - 2) + "%\" ");
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


}
