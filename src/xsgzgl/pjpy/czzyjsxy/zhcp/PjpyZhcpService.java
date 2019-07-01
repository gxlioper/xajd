package xsgzgl.pjpy.czzyjsxy.zhcp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.SearchRsModel;
import xgxt.form.User;
import xsgzgl.comm.BasicService;
import xsgzgl.pjpy.general.PjpyGeneralForm;
import xsgzgl.pjpy.general.zhcp.PjpyZhcpModel;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 评奖评优_综合测评_池州职业技术学院_Service类
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

public class PjpyZhcpService extends xsgzgl.pjpy.general.zhcp.PjpyZhcpService {

	PjpyZhcpDAO dao = new PjpyZhcpDAO();

	/**
	 * 综合分及排名计算
	 * 
	 * @data 2012-12-10
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public boolean account(PjpyGeneralForm myForm, User user) throws Exception {

		boolean flag = false;

		// 综测分数计算(综测分、以及系项分计算)
		flag = zcxmjs(myForm, user);

		if (flag) {
			// 综测总分排名
			flag = getPlace(myForm, user);
		}

		if (flag) {
			// 智育分排名计算
			flag = getZyPlace(myForm, user);
		}

		return flag;
	}

	// =====================综测分begin===========================

	/**
	 * 计算综测排名
	 * 
	 * @data 2012-12-10
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public boolean getPlace(PjpyGeneralForm model, User user) throws Exception {

		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();

		// 根据所选择的排名计算方式计算排名
		String zcpm = jbszModel.getZcpm();

		boolean blog = false;

		// 参评组排名
		if ("0".equalsIgnoreCase(zcpm)) {
			blog = cpzpmjs(model, user);
		} else {

			// 年级学院排名
			blog = xypmjs(model, user);

			// 年级专业排名
			blog = zypmjs(model, user);

			// 班级排名
			blog = bjpmjs(model, user);

			// 综测(上)班级排名
			zcsBjpm(model, user);
			// 综测(下)班级排名
			zcxBjpm(model, user);
		}

		return blog;
	}

	/**
	 * 参评组排名计算
	 * 
	 * @data 2012-12-10
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public boolean cpzpmjs(PjpyGeneralForm model, User user) throws Exception {

		// 参评组排名计算
		boolean flag = dao.cpzpmjs(model, user);
		if (flag) {
			flag = dao.updateCpzpm(model, user);
		}
		return flag;
	}

	/**
	 * 年级学院排名计算
	 * 
	 * @data 2012-12-10
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public boolean xypmjs(PjpyGeneralForm model, User user) throws Exception {

		// 将年级学院排名差入临时表
		boolean flag = dao.njxypmjs(model, user);
		if (flag) {
			flag = dao.updateXypm(model, user);
		}
		return flag;
	}

	/**
	 * 年级专业排名计算
	 * 
	 * @data 2012-12-10
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public boolean zypmjs(PjpyGeneralForm model, User user) throws Exception {

		// 将年级专业排名差入临时表
		boolean flag = dao.njzypmjs(model, user);
		if (flag) {
			flag = dao.updateZypm(model, user);
		}
		return flag;
	}

	/**
	 * 班级排名计算
	 * 
	 * @data 2012-12-10
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public boolean bjpmjs(PjpyGeneralForm model, User user) throws Exception {

		// 将班级排名差入临时表
		boolean flag = dao.bjpmjs(model, user);
		if (flag) {
			flag = dao.updateBjpm(model, user);
		}
		return flag;
	}

	/**
	 * 综测(上)班级排名计算
	 * 
	 * @data 2012-12-10
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public boolean zcsBjpm(PjpyGeneralForm model, User user) throws Exception {

		// 将班级排名差入临时表
		boolean flag = dao.zcsBjpm(model, user);
		if (flag) {
			flag = dao.updateZcspm(model, user);
		}
		return flag;
	}

	/**
	 * 综测(下)班级排名计算
	 * 
	 * @data 2012-12-10
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public boolean zcxBjpm(PjpyGeneralForm model, User user) throws Exception {

		// 将班级排名差入临时表
		boolean flag = dao.zcxBjpm(model, user);
		if (flag) {
			flag = dao.updateZcxpm(model, user);
		}
		return flag;
	}

	// =====================综测分end===========================

	// =====================智育分begin===========================

	/**
	 * 计算智育排名
	 * 
	 * @data 2012-12-10
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public boolean getZyPlace(PjpyGeneralForm model, User user)
			throws Exception {

		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();

		// 根据所选择的排名计算方式计算排名
		String zypm = jbszModel.getZypm();

		boolean blog = false;

		if ("0".equalsIgnoreCase(zypm)) {
			blog = cpzZypmjs(model, user);
		} else {

			// 年级学院排名
			blog = xyZypmjs(model, user);

			// 年级专业排名
			blog = zyZypmjs(model, user);

			// 班级排名
			blog = bjZypmjs(model, user);

			// 智育(上)班级排名
			zysBjpm(model, user);
			// 智育(下)班级排名
			zyxBjpm(model, user);
		}

		return blog;

	}

	/**
	 * 参评组排名计算【智育分】
	 * 
	 * @data 2012-12-10
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public boolean cpzZypmjs(PjpyGeneralForm model, User user) throws Exception {

		// 参评组排名计算
		boolean flag = dao.cpzZypmjs(model, user);
		if (flag) {
			flag = dao.updateCpzZypm(model, user);
		}
		return flag;
	}

	/**
	 * 年级学院排名计算【智育分】
	 * 
	 * @data 2012-12-10
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public boolean xyZypmjs(PjpyGeneralForm model, User user) throws Exception {

		// 将班级排名差入临时表
		boolean flag = dao.njxyZypmjs(model, user);
		if (flag) {
			flag = dao.updateNjXyZypm(model, user);
		}
		return flag;
	}

	/**
	 * 年级专业排名计算【智育分】
	 * 
	 * @data 2012-12-10
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public boolean zyZypmjs(PjpyGeneralForm model, User user) throws Exception {

		// 将班级排名差入临时表
		boolean flag = dao.njzyZypmjs(model, user);
		if (flag) {
			flag = dao.updateNjZyZypm(model, user);
		}
		return flag;
	}

	/**
	 * 班级排名计算【智育分】
	 * 
	 * @data 2012-12-10
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public boolean bjZypmjs(PjpyGeneralForm model, User user) throws Exception {

		// 将班级排名差入临时表
		boolean flag = dao.bjZypmjs(model, user);
		if (flag) {
			flag = dao.updateBjZypm(model, user);
		}
		return flag;
	}

	/**
	 * 班级排名计算【智育分上】
	 * 
	 * @data 2012-12-10
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public boolean zysBjpm(PjpyGeneralForm model, User user) throws Exception {

		// 将班级排名差入临时表
		boolean flag = dao.zysBjpm(model, user);
		if (flag) {
			flag = dao.updateZyspm(model, user);
		}
		return flag;
	}

	/**
	 * 班级排名计算【智育分下】
	 * 
	 * @data 2012-12-10
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public boolean zyxBjpm(PjpyGeneralForm model, User user) throws Exception {

		// 将班级排名差入临时表
		boolean flag = dao.zyxBjpm(model, user);
		if (flag) {
			flag = dao.updateZyxpm(model, user);
		}
		return flag;
	}

	// =====================智育分end===========================

	// =====================综合分结果begin===========================

	/**
	 * 获取表头显示字段
	 * 
	 * @data 2012-12-10
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getZhcpResultTop(
			PjpyGeneralForm model, User user) {

		DAO dao = DAO.getInstance();

		String[] en = new String[] { "xh", "xm", "nj", "xymc", "zymc", "bjmc",
				"zd4", "zd5", "zd6", "zd7", "zd2", "zcspm", "zyspm", "zd28",
				"zd30", "zd8", "zd9", "zd10", "zd11", "zd3", "zcxpm", "zyxpm",
				"zd27", "zd29" };
		String[] cn = new String[] { "学号", "姓名", "年级", "院系", "专业", "班级",
				"德育分(上)", "智育分(上)", "体育分(上)", "能力分(上)", "综测分(上)", "综测排名(上)",
				"智育排名(上)", "等级(上)", "备注(上)", "德育分(下)", "智育分(下)", "体育分(下)",
				"能力分(下)", "综测分(下)", "综测排名(下)", "智育排名(下)", "等级(下)", "备注(下)" };

		List<HashMap<String, String>> topTr = dao.arrayToList(en, cn);

		return topTr;
	}

	/**
	 * 获取结果集
	 * 
	 * @data 2012-12-10
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public ArrayList<String[]> getZhcpResultList(PjpyGeneralForm myForm,
			PjpyZhcpModel model, User user) throws Exception {

		return (ArrayList<String[]>) dao.getZhcpResultList(myForm, user);
	}

	/**
	 * 构建结果集
	 * 
	 * @data 2012-12-10
	 * @author 伟大的骆
	 */
	public String createZhcpResultHTML(SearchRsModel rsModel,
			PjpyZhcpModel model, ArrayList<String[]> rsArrList, User user) {

		BasicService basicService = new BasicService();

		// IE版本
		String ie = rsModel.getIe();

		StringBuilder html = new StringBuilder();

		if (rsArrList != null && rsArrList.size() > 0) {

			for (int i = 0; i < rsArrList.size(); i++) {

				String[] rs = rsArrList.get(i);

				html
						.append("<tr onclick=\"rowOnClick(this);\" ondblclick=\"\">");

				// html.append("<td  align=\"left\" nowrap=\"nowrap\" style=\"width:8px\">");
				//					
				// html.append("<input type='hidden' name='pkValue'   ");
				// html.append("  id='pkValue_"+i+"' ");
				// html.append(" value='" + rs[0] + "'/> ");
				//					
				// html.append("</td>");

				// --------------------构建HTML扩展字段与分数除外------------------------
				for (int j = 0; j < rs.length; j++) {
					html
							.append("<td align=\"left\" nowrap=\"nowrap\" style=\"\" ");
					// IE8
					if ("5.8".equalsIgnoreCase(ie)) {
						html.append("height=\"28.5px\"");
					} else {
						html.append("height=\"29px\"");
					}
					html.append(">");
					html.append(basicService.replaceHtml(rs[j]));
					html.append("</td>");
				}

				html.append("</tr>");
			}
		}

		return html.toString();
	}
	// =====================综合分结果end===========================
}
