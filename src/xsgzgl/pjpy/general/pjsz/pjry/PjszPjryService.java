package xsgzgl.pjpy.general.pjsz.pjry;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.comm.CommService;
import xgxt.comm.SearchRsModel;
import xgxt.form.User;
import xsgzgl.pjpy.general.PjpyGeneralForm;
import xsgzgl.pjpy.general.inter.pjsz.PjszPjryInterface;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 评奖评优_评奖设置_评奖人员_Service类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author 伟大的骆
 * @version 1.0
 */

public class PjszPjryService extends CommService implements PjszPjryInterface {

	PjszPjryDAO dao = new PjszPjryDAO();

	/**
	 * 获得表头文件(评奖设置_评奖人员)
	 * 
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getPjszPjryTop(PjszPjryModel model,
			User user) {
		DAO dao = DAO.getInstance();

		String[] en = new String[] { "pk", "xh", "xm", "nj", "bjmc", "pjbjmc",
				"sfcp" };
		String[] cn = new String[] { "", "学号", "姓名", "年级", "班级名称", "评奖班级名称",
				"是否参评" };

		List<HashMap<String, String>> topTr = dao.arrayToList(en, cn);

		return topTr;
	}

	/**
	 * 获得结果集(评奖设置_评奖人员)
	 * 
	 * @author 伟大的骆
	 */
	public ArrayList<String[]> getPjszPjryList(PjpyGeneralForm myForm,
			PjszPjryModel model, User user) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

		return dao.getPjszPjryList(myForm, user);
	}

	/**
	 * 构建结果集(评奖设置_评奖人员)
	 * 
	 * @author 伟大的骆
	 */
	public String createPjszPjryHTML(SearchRsModel rsModel,
			PjszPjryModel model, ArrayList<String[]> rsArrList, User user) {
		
		// IE版本
		String ie = rsModel.getIe();

		StringBuilder html = new StringBuilder();

		if (rsArrList != null && rsArrList.size() > 0) {

			for (int i = 0; i < rsArrList.size(); i++) {

				String[] rs = rsArrList.get(i);

				String pk = rs[0];
				String pjbjmc = rs[5];//评奖班级名称
				
				html.append("<tr onclick=\"rowOnClick(this);\" ondblclick=\"\">");
				html.append("<td align=\"center\" width=\"5px\"");
				if(!"未调换".equalsIgnoreCase(pjbjmc)){
					html.append("bgcolor=\"#CCFFFF\"");
				}
				html.append(">");
				html.append("<input type=\"checkbox\" name=\"primarykey_checkVal\" ");
				html.append("value=\"" + pk + "\"/>");
				html.append("</td>");
				
				for (int j = 1; j < rs.length; j++) {
					html.append("<td align=\"left\" nowrap=\"nowrap\" style=\"\" ");
					
					if(!"未调换".equalsIgnoreCase(pjbjmc)){
						html.append("bgcolor=\"#CCFFFF\"");
					}
					
					// IE8
					if ("5.8".equalsIgnoreCase(ie)) {
						html.append("height=\"28.5px\"");
					} else {
						html.append("height=\"29px\"");
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
	 * 保存评奖班级调整
	 * 
	 * @author 伟大的骆
	 */
	public Boolean saveBjtz(PjszPjryModel model, User user) {

		// 学号
		String[] xh = model.getXh();
		// 班级代码
		String bjdm = model.getBjdm();
		// 班级名称
		String bjmc = model.getBjmc();

		boolean flag = false;

		try {
			//保存班级调整
			flag = dao.updatePjrykb(xh, bjdm, bjmc, user);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return flag;
	}

	/**
	 * 保存参评设置
	 * 
	 * @author 伟大的骆
	 */
	public Boolean saveSfcp(PjszPjryModel model, User user) {

		// 学号
		String[] xh = model.getXh();
		// 是否参评
		String sfcp = model.getSfcp();

		boolean flag = false;

		try {
			//保存参评设置
			flag = dao.updatePjrykb(xh, sfcp, user);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return flag;
	}
	
	/**
	 * 撤销评奖人员班级调整
	 * @author qlj 
	 */
	public boolean disfrockPjry(PjszPjryModel model,User user) throws Exception{
		
		String[]xh= model.getXh(); 
		
		boolean flag = false;

		try {
			// 撤销班级调整
			flag = dao.disfrockPjry(xh,user);
		
		} catch (Exception e) {
			e.printStackTrace();
		}

		return flag;
	}
}
