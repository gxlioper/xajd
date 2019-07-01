package xsgzgl.pjpy.general.pjsz.cpxz;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.SearchRsModel;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xsgzgl.pjpy.general.PjpyGeneralForm;
import xsgzgl.pjpy.general.inter.pjsz.PjszCpxzInterface;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 评奖评优_评奖设置_参评小组_Service类
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

public class PjszCpxzService extends CommService implements PjszCpxzInterface {

	PjszCpxzDAO dao = new PjszCpxzDAO();

	/**
	 * 获得表头文件(评奖设置_参评小组)
	 * 
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getPjszCpxzTop(PjszCpxzModel model,
			User user) {
		DAO dao = DAO.getInstance();

		String[] en = new String[] { "pk", "nj", "xymc", "zymc", "bjmc",
				"cpzmc" };
		String[] cn = new String[] { "", "年级", Base.YXPZXY_KEY, "专业", "班级",
				"参评组" };

		List<HashMap<String, String>> topTr = dao.arrayToList(en, cn);

		return topTr;
	}

	/**
	 * 获得结果集(评奖设置_参评小组)
	 * 
	 * @author 伟大的骆
	 */
	public ArrayList<String[]> getPjszCpxzList(PjpyGeneralForm myForm,
			PjszCpxzModel model, User user) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

		return dao.getPjszCpxzList(myForm, user);
	}

	/**
	 * 构建结果集(评奖设置_参评小组)
	 * 
	 * @author 伟大的骆
	 */
	public String createPjszCpxzHTML(SearchRsModel rsModel,
			PjszCpxzModel model, ArrayList<String[]> rsArrList, User user) {

		return null;
	}

	/**
	 * 保存参评小组
	 * 
	 * @author 伟大的骆
	 */
	public Boolean saveCpxz(PjpyGeneralForm myForm, PjszCpxzModel model,
			User user, String saveLx) {

		SearchModel searchModel =myForm.getSearchModel();
		
		// 参评组名称
		String cpzmc = model.getCpzmc();

		// 班级代码
		String[] bjdm = model.getBjdm();

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
			if ("checked".equalsIgnoreCase(saveLx)) {// 保存参评小组设置（勾选）
				flag = dao.deleteCpzb(bjdm, user);
				if (flag) {
					flag = dao.insertCpzb(bjdm, cpzmc, user);
				}
			} else if ("no_checked".equalsIgnoreCase(saveLx)) {// 保存参评小组设置（未勾选）
				flag = dao.deleteCpzb(nj, xy, zy, bj, user);
				if (flag) {
					flag = dao.insertCpzb(nj, xy, zy, bjdm, cpzmc, user);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return flag;
	}

	/**
	 * 取消参评小组
	 * 
	 * @author 伟大的骆
	 */
	public Boolean deleteCpxz(PjpyGeneralForm myForm, PjszCpxzModel model,
			User user, String saveLx) {

		SearchModel searchModel = myForm.getSearchModel();

		// 班级代码
		String[] bjdm = model.getBjdm();

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
			if ("checked".equalsIgnoreCase(saveLx)) {// 取消参评小组设置（勾选）
				flag = dao.deleteCpzb(bjdm, user);
			} else if ("no_checked".equalsIgnoreCase(saveLx)) {// 取消参评小组设置（未勾选）
				flag = dao.deleteCpzb(nj, xy, zy, bj, user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return flag;
	}

	/**
	 * 保存参评小组自动设置
	 * 
	 * @author 伟大的骆
	 */
	public boolean saveCpxzZdsz(PjszCpxzModel model, User user) {

		// 参评组规则
		String cpzgz = model.getCpzgz();
		// 参评组名称
		String cpzmc = "";

		if ("bj".equalsIgnoreCase(cpzgz)) {// 班级
			cpzmc = "bjmc";
		} else if ("njzy".equalsIgnoreCase(cpzgz)) {// 年级专业
			cpzmc = "nj||zymc";
		} else if ("xy".equalsIgnoreCase(cpzgz)) {// 学院
			cpzmc = "xymc";
		} else if ("njxy".equalsIgnoreCase(cpzgz)) {// 年级学院
			cpzmc = "nj||xymc";
		} else if ("nj".equalsIgnoreCase(cpzgz)) {// 年级
			cpzmc = "nj";
		}
		
		boolean flag = false;

		try {
			flag = dao.insertCpzb(cpzmc, user);
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}

		return flag;
	}

	/**
	 * 检测参评小组提交
	 * 
	 * @author 伟大的骆
	 */
	public String checkCpxzSubmit(PjszCpxzModel model, User user) {

		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();

		// 是否需要参评组
		String cpz = jbszModel.getCpz();

		String message = "";

		if ("yes".equalsIgnoreCase(cpz)) {
			// 获得未维护参评组的班级数
			String num = dao.getNoCpzNum(model, user);

			if (!Base.isNull(num) && !"0".equalsIgnoreCase(num)) {
				message = "尚有" + num + "个班级未设置参评组，请前往【参评小组设置】进行处理";
			}
		}

		return message;
	}
}