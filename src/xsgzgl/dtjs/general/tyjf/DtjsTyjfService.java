package xsgzgl.dtjs.general.tyjf;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.comm.CommService;
import xgxt.comm.SearchRsModel;
import xgxt.form.User;
import xsgzgl.dtjs.general.DtjsGeneralForm;
import xsgzgl.dtjs.general.inter.DtjsTyjfInterface;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 党团建设_团员缴费_通用_Service类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author 伟大的骆
 * @version 1.0
 */

public class DtjsTyjfService extends CommService implements DtjsTyjfInterface {

	DtjsTyjfDAO dao = new DtjsTyjfDAO();

	/**
	 * 获得表头文件【团员缴费】
	 * 
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getDtjsTyjfTop(DtjsTyjfModel model,
			User user) {

		String[] en = new String[] {"pk" ,"xn","xh","xm", "nj", "bjmc","yjtf","sjtf","qf" };
		String[] cn = new String[] { "","学年", "学号", "姓名", "年级","班级","应缴团费","已缴团费","欠费" };
		DAO dao = DAO.getInstance();
		List<HashMap<String, String>> topTr = dao.arrayToList(en, cn);
		return topTr;
	}

	/**
	 * 获得结果集【团员缴费】
	 * 
	 * @author 伟大的骆
	 */
	public ArrayList<String[]> getDtjsTyjfList(DtjsGeneralForm myForm,
			DtjsTyjfModel model, User user) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		// TODO 自动生成方法存根
		return dao.getTyjfList(myForm, model);
	}

	/**
	 * 构建结果集【团员缴费】
	 * 
	 * @author 伟大的骆
	 */
	public String createDtjsTyjfHTML(SearchRsModel rsModel,
			DtjsTyjfModel model, ArrayList<String[]> rsArrList, User user) {

		// IE版本
		String ie = rsModel.getIe();

		StringBuilder html = new StringBuilder();

		if (rsArrList != null && rsArrList.size() > 0) {

			for (int i = 0; i < rsArrList.size(); i++) {

				String[] rs = rsArrList.get(i);

				html.append("<tr onclick=\"rowOnClick(this);\" ondblclick=\"\">");

				html.append("<td  align=\"left\" nowrap=\"nowrap\" style=\"width:8px\">");

				html.append("<input type='checkbox' name='primarykey_checkVal'   ");
				html.append("  id='pkValue_" + i + "' ");
				html.append(" value='" + rs[0] + "'/> ");

				html.append("</td>");

				// --------------------构建HTML扩展字段与分数除外------------------------
				for (int j = 1; j < rs.length; j++) {
					html.append("<td align=\"left\" nowrap=\"nowrap\" ");
					// IE8
					if ("5.8".equalsIgnoreCase(ie)) {
						html.append("height=\"28.5px\"");
					} else {
						html.append("height=\"29px\"");
					}
					if(j==1){
						html.append(" style=\"width:10%\"");
					}
					if(j==2){
						html.append(" style=\"width:10%\"");
					}
					if(j==3){
						html.append(" style=\"width:10%\"");
					}
					if(j==4){
						html.append(" style=\"width:10%\"");
					}
					if(j==5){
						html.append(" style=\"width:20%\"");
					}
					
					html.append(">");
					
						html.append(rs[j]);
						
					html.append("</td>");
				}

				html.append("</tr>");
			}
		}

		return html.toString();
	}
	
	/**
	 * 构建结果集【团员缴费】
	 * 
	 * @author 伟大的骆
	 */
	public String createDtjsTyjfHTMLofEdit(SearchRsModel rsModel,
			DtjsTyjfModel model, ArrayList<String[]> rsArrList, User user) {

		// IE版本
		String ie = rsModel.getIe();

		StringBuilder html = new StringBuilder();

		if (rsArrList != null && rsArrList.size() > 0) {

			for (int i = 0; i < rsArrList.size(); i++) {

				String[] rs = rsArrList.get(i);

				html.append("<tr onclick=\"rowOnClick(this);\" ondblclick=\"\">");

				html.append("<td  align=\"left\" nowrap=\"nowrap\" style=\"width:8px\">");

				html.append("<input type='checkbox' name='primarykey_checkVal'   ");
				html.append("  id='pkValue_" + i + "' ");
				html.append(" value='" + rs[0] + "'/> ");

				html.append("</td>");

				// --------------------构建HTML扩展字段与分数除外------------------------
				for (int j = 1; j < rs.length; j++) {
					html.append("<td align=\"left\" nowrap=\"nowrap\" ");
					// IE8
					if ("5.8".equalsIgnoreCase(ie)) {
						html.append("height=\"28.5px\"");
					} else {
						html.append("height=\"29px\"");
					}
					if(j==1){
						html.append(" style=\"width:10%\"");
					}
					if(j==2){
						html.append(" style=\"width:10%\"");
					}
					if(j==3){
						html.append(" style=\"width:10%\"");
					}
					if(j==4){
						html.append(" style=\"width:10%\"");
					}
					if(j==5){
						html.append(" style=\"width:20%\"");
					}
					html.append(">");
					
					if(j==6){
						html.append("<input id='"+rs[0]+"_yjtf' style=\"width:60px\" name='yjtf' onkeyup=\"changeIsEdit()\" onblur='addYjtf(this);checkInputNum(this)' value="+rs[j]+">");
					}else if(j==7){
						html.append("<input id='"+rs[0]+"_sjtf' style=\"width:60px\" name='sjtf' onkeyup=\"changeIsEdit()\" onblur='addSjtf(this);checkInputNum(this)' value="+rs[j]+">");
					}else if(j==8){
						html.append("<input id='"+rs[0]+"_qf' disabled=\"disabled\"  style=\"width:60px\" name='qf'  value="+rs[j]+">");
					}else{
						html.append(rs[j]);
					}
					html.append("</td>");
				}

				html.append("</tr>");
			}
		}

		return html.toString();
	}

	/**
	 * 保存团员缴费
	 * 
	 * @author 伟大的骆
	 * @throws Exception 
	 */
	public boolean saveTyjf(DtjsTyjfModel model, User user) throws Exception {
		// TODO 自动生成方法存根
		String xh = model.getXh();
		boolean flag = false;
		if(null!=xh&&xh.length()>0){
			String xhs[] = xh.split(",");
			if(null!=xhs&&xhs.length>0){
				for(int i = 0;i<xhs.length;i++){
					model.setXh(xhs[i]);
					flag =  dao.saveTyjf(model);
				}
			}
		}
		return flag;
	}
	

	/**
	 * 编辑保存团员缴费
	 * 
	 * @author 伟大的骆
	 * @throws Exception 
	 */
	public boolean saveBjTyjf(DtjsTyjfModel model, User user) throws Exception {
		// TODO 自动生成方法存根
		String xh = model.getXh();
		String yjtf = model.getYjtf();
		String sjtf = model.getSjtf();
		boolean flag = false;
		if(null!=xh&&xh.length()>0){
			String xhs[] = xh.split(",");
			String yjtfs[] = yjtf.split(",");
			String sjtfs[] = sjtf.split(",");
			if(null!=xhs&&xhs.length>0){
				for(int i = 0;i<xhs.length;i++){
					model.setXh(xhs[i]);
					model.setYjtf(yjtfs[i]);
					model.setSjtf(sjtfs[i]);
					flag =  dao.saveTyjf(model);
				}
			}
		}
		return flag;
	}
}
