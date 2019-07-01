package xsgzgl.xsxx.general.xjyd;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.comm.CommService;
import xgxt.comm.SearchRsModel;
import xgxt.form.User;
import xsgzgl.xsxx.general.XsxxGeneralForm;
import xsgzgl.xsxx.general.inter.XsxxXjydInterface;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 学生信息_学籍异动_Service类
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

public class XsxxXjydService extends CommService implements XsxxXjydInterface {

	XsxxXjydDAO dao = new XsxxXjydDAO();

	/**
	 * 获得表头文件【学籍异动】
	 * 
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getXsxxXjydTop(XsxxXjydModel model,
			User user) {

		String[] en = new String[] {"pk" ,"xn","xq","xh", "xm", "ydq","ydh","ydlx","xjzt","sfzx","sfby" };
		String[] cn = new String[] { "","学年", "学期", "学号","姓名", "异动前","异动后","异动类型","学籍状态","是否在校","是否已毕业" };
		DAO dao = DAO.getInstance();
		List<HashMap<String, String>> topTr = dao.arrayToList(en, cn);
		return topTr;
	}

	/**
	 * 获得结果集【学籍异动】
	 * 
	 * @author 伟大的骆
	 */
	public ArrayList<String[]> getXsxxXjydList(XsxxGeneralForm myForm,
			XsxxXjydModel model, User user) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

		return dao.getXsxxXjydList(myForm, model);
	}

	/**
	 * 构建结果集【学籍异动】
	 * 
	 * @author 伟大的骆
	 */
	public String createXsxxXjydHTML(SearchRsModel rsModel,
			XsxxXjydModel model, ArrayList<String[]> rsArrList, User user) {


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
				for (int j = 1; j < rs.length-4; j++) {
					html.append("<td align=\"left\" nowrap=\"nowrap\"  ");
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
						html.append(" style=\"width:5%\"");
					}
					if(j==3){
						html.append(" style=\"width:10%\"");
					}
					if(j==4){
						html.append(" style=\"width:10%\"");
					}
					if(j==5){
						html.append("title="+rs[11]+","+rs[12]+" style=\"width:10%\"");
					}
					if(j==6){
						html.append("title="+rs[13]+","+rs[14]+" style=\"width:10%\"");
					}
					if(j==7){
						html.append(" style=\"width:5%\"");
					}
					if(j==8){
						html.append(" style=\"width:5%\"");
					}
					if(j==9){
						html.append(" style=\"width:5%\"");
					}
					if(j==10){
						html.append(" style=\"width:5%\"");
					}
					if(j==10){
						html.append(" style=\"width:5%\"");
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
	 * 提交学籍异动信息【学籍异动】
	 * 
	 * @author 伟大的骆
	 * @throws Exception 
	 */
	public boolean submitXjyd(XsxxXjydModel model, User user) throws Exception {
		String xh = model.getXh();
		boolean flag = false;
		if(null!=xh&&xh.length()>0){
			String xhs[] = xh.split(",");
			String sql="";
			if(null!=xhs&&xhs.length>0){
				for(int i = 0;i<xhs.length;i++){
					if(i==0){
						sql=" a.xh="+xhs[i];
					}else{
						sql +=" or a.xh="+xhs[i];
					}
				}
				flag =  dao.saveXsxx(sql);
				flag = dao.updateXsxx(sql);
			}
		}
		return flag;
	}
}