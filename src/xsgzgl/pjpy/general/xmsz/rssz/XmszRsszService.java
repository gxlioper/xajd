package xsgzgl.pjpy.general.xmsz.rssz;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.comm.CommService;
import xgxt.comm.SearchRsModel;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xsgzgl.pjpy.general.PjpyGeneralForm;
import xsgzgl.pjpy.general.inter.xmsz.XmszRsszInterface;
import xsgzgl.pjpy.general.pjsz.pjxm.PjszPjxmModel;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 评奖评优_项目设置_人数设置_通用_Service类
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

public class XmszRsszService extends CommService implements XmszRsszInterface {

	XmszRsszDAO dao = new XmszRsszDAO();

	/**
	 * 获得人数是否超过的信息
	 * 
	 * @author 伟大的骆
	 */
	public String getRsszMessage(XmszRsszModel model, User user) {
		// TODO 自动生成方法存根
		return null;
	}

	/**
	 * 获得表头文件(项目设置_人数设置)
	 * 
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getXmszRsszTop(XmszRsszModel model,
			User user) {
		
		PjszPjxmModel pjxmModel = PjpyGeneralForm.getPjxmModel();
		// 控制范围
		String kzfw = pjxmModel.getKzfw();
		
		DAO dao = DAO.getInstance();

		String[] en = null;
		String[] cn = null;
	
		if ("nj".equalsIgnoreCase(kzfw)) {// 控制年级人数
			en = new String[] { "pk", "nj", "bmrs", "szbl", "jsrs", "mrrs",
					"qdrs" };
			cn = new String[] { "", "年级", "参评人数", "设置比例", "计算人数", "默认人数",
					"最终人数" };
		} else if ("xy".equalsIgnoreCase(kzfw)) {// 控制学院人数
			en = new String[] { "pk", "xymc", "bmrs", "szbl", "jsrs", "mrrs",
					"qdrs" };
			cn = new String[] { "", "院系名称", "参评人数", "设置比例", "计算人数", "默认人数",
					"最终人数" };
		} else if ("njxy".equalsIgnoreCase(kzfw)) {// 控制年级学院人数
			en = new String[] { "pk", "nj", "xymc", "bmrs", "szbl", "jsrs",
					"mrrs", "qdrs" };
			cn = new String[] { "", "年级", "院系名称", "参评人数", "设置比例", "计算人数",
					"默认人数", "最终人数" };
		} else if ("njzy".equalsIgnoreCase(kzfw)) {// 控制年级专业 人数
			en = new String[] { "pk", "nj", "xymc", "zymc", "bmrs", "szbl",
					"jsrs", "mrrs", "qdrs" };
			cn = new String[] { "", "年级", "院系名称", "专业名称", "参评人数", "设置比例",
					"计算人数", "默认人数", "最终人数" };
		} else if ("bj".equalsIgnoreCase(kzfw)) {// 控制班级人数
			en = new String[] { "pk", "nj", "bjmc", "bmrs", "szbl", "jsrs",
					"mrrs", "qdrs" };
			cn = new String[] { "", "年级", "班级", "参评人数", "设置比例", "计算人数", "默认人数",
					"最终人数" };
		}else if ("cpz".equalsIgnoreCase(kzfw)) {// 控制班级人数
			en = new String[] { "pk","cpzmc", "bmrs", "szbl", "jsrs",
					"mrrs", "qdrs" };
			cn = new String[] { "", "参评组", "参评人数", "设置比例", "计算人数", "默认人数",
					"最终人数" };
		}
		
		List<HashMap<String, String>> topTr = dao.arrayToList(en, cn);
		
		return topTr;
	}

	/**
	 * 获得结果集(项目设置_人数设置)
	 * 
	 * @author 伟大的骆
	 */
	public ArrayList<String[]> getXmszRsszList(PjpyGeneralForm myForm,
			XmszRsszModel model, User user) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

		ArrayList<String[]> list = null;

		PjszPjxmModel pjxmModel = PjpyGeneralForm.getPjxmModel();
		// 项目代码
		String xmdm = pjxmModel.getXmdm();
		// 控制范围
		String kzfw = pjxmModel.getKzfw();

		if ("nj".equalsIgnoreCase(kzfw)) {// 控制年级人数
			list = dao.getXmszRsszListByNj(myForm, xmdm, user);
		} else if ("xy".equalsIgnoreCase(kzfw)) {// 控制学院人数
			list = dao.getXmszRsszListByXy(myForm, xmdm, user);
		} else if ("njxy".equalsIgnoreCase(kzfw)) {// 控制年级学院人数
			list = dao.getXmszRsszListByNjXy(myForm, xmdm, user);
		} else if ("njzy".equalsIgnoreCase(kzfw)) {// 控制年级专业人数
			list = dao.getXmszRsszListByNjZy(myForm, xmdm, user);
		} else if ("bj".equalsIgnoreCase(kzfw)) {// 控制班级人数
			list = dao.getXmszRsszListByBj(myForm, xmdm, user);
		} else if ("cpz".equalsIgnoreCase(kzfw)) {// 控制班级人数
			list = dao.getXmszRsszListByCpz(myForm, xmdm, user);
		}

		return list;
	}

	/**
	 * 构建结果集(项目设置_人数设置)
	 * 
	 * @author 伟大的骆
	 */
	public String createXmszRsszHTML(SearchRsModel rsModel,
			XmszRsszModel model, ArrayList<String[]> rsArrList, User user) {
		
		// IE版本
		String ie = rsModel.getIe();

		StringBuilder html = new StringBuilder();

		if (rsArrList != null && rsArrList.size() > 0) {

			for (int i = 0; i < rsArrList.size(); i++) {

				String[] rs = rsArrList.get(i);

				String pk = rs[0];//主键
//				String nj = rs[1];// 年级
//				String bjmc = rs[2];// 班级
//				String bmrs = rs[3];// 部门人数
//				String szbl = rs[4];// 比例
//				String jsrs = rs[5];// 计算人数
//				String mrrs = rs[6];// 默认人数
				String qdrs = rs[rs.length - 1];// 最终人数
				
				html.append("<tr onclick=\"rowOnClick(this);\" style=\"cursor:hand\" ondblclick=\"\">");
				html.append("<td align=\"center\" width=\"5px\"");
				html.append(">");
				html.append("<input type=\"checkbox\" name=\"primarykey_checkVal\" ");
				html.append("value=\"" + pk + "\"/>");
				html.append("</td>");
				
				for (int j = 1; j < rs.length - 1; j++) {
					html.append("<td align=\"left\" nowrap=\"nowrap\" style=\"\" ");
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

				html.append("<td align=\"left\" nowrap=\"nowrap\" style=\"\" ");
				html.append(">");
				html.append("<input type=\"text\" name=\"array_qdrs\" ");
				html.append("id=\"input_qdrs_" + pk + "\" ");
				html.append("onkeydown=\"return onlyNum(this,3)\" ");
				html.append("onmousedown=\"return onlyNum(this,3)\" ");
				html.append("maxlength=\"3\"  ");
				html.append("style=\"width:50px;ime-mode:disabled\" ");
				html.append("value=\""+qdrs+"\"/>");
				html.append("</td>");
				
				html.append("</tr>");
			}
		}
			
		return html.toString();
	}
	
	/**
	 * 初始化人数设置表
	 * 
	 * @author 伟大的骆
	 */
	public void initRsszb(String xmdm, String szfw, String tsrq, User user) {
		try {
			if ("bj".equalsIgnoreCase(szfw)) {// 控制班级人数
				dao.initRsszbByBj(xmdm, tsrq, user);
			} else if ("nj".equalsIgnoreCase(szfw)) {// 控制年级人数
				dao.initRsszbByNj(xmdm, tsrq, user);
			} else if ("xy".equalsIgnoreCase(szfw)) {// 控制学院人数
				dao.initRsszbByXy(xmdm, tsrq, user);
			} else if ("njxy".equalsIgnoreCase(szfw)) {// 控制年级学院人数
				dao.initRsszbByNjXy(xmdm, tsrq, user);
			} else if ("njzy".equalsIgnoreCase(szfw)) {// 控制年级专业人数
				dao.initRsszbByNjZy(xmdm, tsrq, user);
			} else if("cpz".equalsIgnoreCase(szfw)){// 控制班级人数
				dao.initRsszbByCpz(xmdm, tsrq, user);
			} else {// 控制班级人数
				dao.initRsszbByBj(xmdm, tsrq, user);
			}
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
	}

	/**
	 * 保存设置比例
	 * 
	 * @author 伟大的骆
	 */
	public Boolean saveSzbl(PjpyGeneralForm myForm, XmszRsszModel model,
			User user, String saveLx) {

		PjpyGeneralForm jbszModel=PjpyGeneralForm.getJbszModel();
		PjszPjxmModel pjxmModel = PjpyGeneralForm.getPjxmModel();
		SearchModel searchModel = myForm.getSearchModel();
		
		// 是否参评组
		String isCpz = jbszModel.getCpz();

		// 项目代码
		String xmdm = model.getXmdm();
		// 设置比例
		String szbl = model.getSzbl();
		// 控制范围
		String szfw = pjxmModel.getKzfw();
		// 主键
		String[] pkValue = model.getPkValue();
		
		// 年级
		String[] nj = searchModel.getSearch_tj_nj();
		// 学院
		String[] xy = searchModel.getSearch_tj_xy();
		// 专业
		String[] zy = searchModel.getSearch_tj_zy();
		// 班级
		String[] bj = searchModel.getSearch_tj_bj();

		boolean flag = false;

		try {
			if ("checked".equalsIgnoreCase(saveLx)) {// 保存设置比例（勾选）
					
				flag = dao.updateRsszb(pkValue, xmdm, szfw, szbl, user);
				
			} else if ("no_checked".equalsIgnoreCase(saveLx)) {// 保存设置比例（未勾选）
				
				if("yes".equalsIgnoreCase(isCpz)){
					flag = dao.updateRsszByCpz(nj, xy, zy, bj, xmdm, szfw, szbl, user);
				}else{
					flag = dao.updateRsszb(nj, xy, zy, bj, xmdm, szfw, szbl, user);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return flag;
	}
	
	/**
	 * 保存最终人数
	 * 
	 * @author 伟大的骆
	 */
	public Boolean saveQdrs(XmszRsszModel model, User user) {

		// 主键
		String[] pkValue = model.getPkValue();
		// 确定人数
		String[] qdrs = model.getQdrs();

		boolean flag = false;

		try {
			flag = dao.updateRsszbQdrs(pkValue, qdrs, user);
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}

		return flag;
	}
}
