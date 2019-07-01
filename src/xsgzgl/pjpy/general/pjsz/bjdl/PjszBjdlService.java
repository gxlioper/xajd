package xsgzgl.pjpy.general.pjsz.bjdl;

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
import xsgzgl.pjpy.general.inter.pjsz.PjszBjdlInterface;

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

public class PjszBjdlService extends CommService implements PjszBjdlInterface {

	PjszBjdlDAO dao = new PjszBjdlDAO();

	/**
	 * 获得表头文件(评奖设置_班级大类)
	 * 
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getPjszBjdlTop(PjszBjdlModel model,
			User user) {

		DAO dao = DAO.getInstance();

		String[] en = new String[] { "pk", "nj", "xymc", "zymc", "bjmc",
				"bjdlmc" };
		String[] cn = new String[] { "", "年级", Base.YXPZXY_KEY, "专业", "班级",
				"班级大类" };

		List<HashMap<String, String>> topTr = dao.arrayToList(en, cn);

		return topTr;
	}

	/**
	 * 获得结果集(评奖设置_班级大类)
	 * 
	 * @author 伟大的骆
	 */
	public ArrayList<String[]> getPjszBjdlList(PjpyGeneralForm myForm,
			PjszBjdlModel model, User user) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

		return dao.getPjszBjdlList(myForm, user);
	}

	/**
	 * 构建结果集(评奖设置_班级大类)
	 * 
	 * @author 伟大的骆
	 */
	public String createPjszBjdlHTML(SearchRsModel rsModel,
			PjszBjdlModel model, ArrayList<String[]> rsArrList, User user) {
		// TODO 自动生成方法存根
		return null;
	}

	/**
	 * 保存班级大类
	 * 
	 * @author 伟大的骆
	 */
	public Boolean saveBjdl(PjpyGeneralForm myForm, PjszBjdlModel model,
			User user, String saveLx) {

		SearchModel searchModel = myForm.getSearchModel();

		// 班级大类名称
		String bjdlmc = model.getBjdlmc();

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
			if ("checked".equalsIgnoreCase(saveLx)) {// 保存班级大类设置（勾选）
				flag = dao.deleteBjdlb(bjdm, user);
				if (flag) {
					flag = dao.insertBjdlb(bjdm, bjdlmc, user);
				}
			} else if ("no_checked".equalsIgnoreCase(saveLx)) {// 保存班级大类设置（未勾选）
				flag = dao.deleteBjdlb(nj, xy, zy, bj, user);
				if (flag) {
					flag = dao.insertBjdlb(nj, xy, zy, bj, bjdlmc, user);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return flag;
	}

	/**
	 * 取消班级大类
	 * 
	 * @author 伟大的骆
	 */
	public Boolean deleteBjdl(PjpyGeneralForm myForm, PjszBjdlModel model,
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
			if ("checked".equalsIgnoreCase(saveLx)) {// 取消班级大类设置（勾选）
				flag = dao.deleteBjdlb(bjdm, user);
			} else if ("no_checked".equalsIgnoreCase(saveLx)) {// 取消班级大类设置（未勾选）
				flag = dao.deleteBjdlb(nj, xy, zy, bj, user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return flag;
	}
	
	/**
	 * 查询班级大类列表
	 * 
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getBjdlList() {
		return dao.getBjdlList();
	}
	
	/**
	 * 查询某学生的班级大类
	 * 
	 * @author 伟大的骆
	 */
	public String getBjdl(String xh) {
		return dao.getBjdl(xh);
	}
}