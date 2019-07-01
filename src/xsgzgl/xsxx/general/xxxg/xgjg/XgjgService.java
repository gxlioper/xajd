package xsgzgl.xsxx.general.xxxg.xgjg;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import xgxt.DAO.DAO;
import xgxt.comm.SearchRsModel;
import xgxt.form.User;
import xsgzgl.xsxx.general.XsxxGeneralForm;
import xsgzgl.xsxx.general.XsxxGeneralService;
import xsgzgl.xsxx.general.inter.xxxg.XxxgXgjgInterface;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 學生信息_信息修改_修改結果_通用_Service类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author 伟大的骆
 * @version 1.0
 */

public class XgjgService extends XsxxGeneralService implements
		XxxgXgjgInterface {

	XgjgDAO dao = new XgjgDAO();

	/**
	 * 获得表头文件【修改審核】
	 * 
	 * @date 2013-01-24
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getXgjgTop(XgjgModel model, User user) {

		DAO dao = DAO.getInstance();

		String[] en = new String[] { "pk", "xh", "xm", "xb", "nj", "bjdm",
				"sqsj", "shzt" };
		String[] cn = new String[] { "", "学号", "姓名", "性别", "年级", "班级",
				"申请修改时间", "审核状态" };

		List<HashMap<String, String>> topTr = dao.arrayToList(en, cn);

		return topTr;
	}

	/**
	 * 获得结果集【修改審核】
	 * 
	 * @date 2013-01-24
	 * @author 伟大的骆
	 */
	public ArrayList<String[]> getXgjgList(XsxxGeneralForm myForm,
			XgjgModel model, User user) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

		return dao.getXgjgList(myForm, model, user);
	}

	/**
	 * 构建结果集【修改審核】
	 * 
	 * @date 2013-01-24
	 * @author 伟大的骆
	 */
	public String createXgjgHTML(SearchRsModel rsModel, XgjgModel model,
			ArrayList<String[]> rsArrList, User user) {
		return null;
	}

	/**
	 * 初始化功能参数
	 * 
	 * @date 2013-01-28
	 * @author 伟大的骆
	 */
	public void initParameter() {

		String path = "xsxx_general_xxxg_xgjg.do";

		// 高级查询是否配置
		boolean isSearch = isExists("xg_search_szb", "path", path);

		if (!isSearch) {
			initSearch();
		}
	}

	/**
	 * 初始化查询条件
	 * 
	 * @date 2013-01-28
	 * @author 伟大的骆
	 */
	private void initSearch() {

		List<String[]> params = new ArrayList<String[]>();

		String path = "xsxx_general_xxxg_xgjg.do";
		String[] tj = new String[] { "xh", "xm", "nj", "xy", "zy", "bj", "shztOne" };
		String[] mc = new String[] { "学号", "姓名", "年级", "院系", "专业", "班级", "审核状态" };
		String[] lx = new String[] { "mhcx", "mhcx", "djcx", "djcx", "djcx",
				"djcx", "djcx" };
		String[] zd = new String[] { "xh", "xm", "nj", "xydm", "zydm", "bjdm",
				"shzt" };
		String[] ssmk = new String[] { "xsxx", "xsxx", "xsxx", "xsxx", "xsxx",
				"xsxx", "xsxx" };
		String[] xssx = new String[] { "1", "2", "1", "2", "3", "4", "5" };

		for (int i = 0; i < tj.length; i++) {
			String[] value = new String[] { path, tj[i], mc[i], lx[i], zd[i],
					ssmk[i], xssx[i] };
			params.add(value);
		}

		try {
			dao.initSearch(params);
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
	}
}