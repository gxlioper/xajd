package xsgzgl.customForm.gnmk;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.CommonQueryDAO;
import xsgzgl.pjpy.szgyyq.mypj.stu.PjpyStuForm;
import xsgzgl.pjpy.szgyyq.mypj.stu.PjpyStuService;
import xsgzgl.pjpy.szgyyq.mypj.tea.PjpyTeaForm;
import xsgzgl.pjpy.szgyyq.mypj.tea.PjpyTeaService;
import xsgzgl.rcsw.qjgl.RcswQjglForm;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 自定义表单_自定义功能_Init类
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

public class CustomGnmkInit {

	/**
	 * 自定义功能_初始化数据
	 * 
	 * @param request
	 * @author 伟大的骆
	 * 
	 */
	public void initCustomGnmk(RequestForm rForm, CustomGnmkForm model,
			User user, HttpServletRequest request) {

		CustomGnmkDAO dao = new CustomGnmkDAO();

		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 访问路径
		String path = "customGnmk.do?method=customGnmkManage";
		// 表头
		List<HashMap<String, String>> topTr = getDefaultValue(model, user);
		// ID
		String gnmkdm = request.getParameter("gnmkdm");
		gnmkdm = Base.isNull(gnmkdm) ? model.getGnmkdm() : gnmkdm;
		path += "&gnmkdm=" + gnmkdm;
		// 功能模块信息
		initCustomGnmkModel(model, user);
		// 操作项目表
		String xmb = model.getXmb();

		// 其他字段
		String[] qtzd = new String[] { "gnmkdm", "xmb" };
		// 其他字段值
		String[] qtzdz = new String[] { gnmkdm, xmb };

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);

		rForm.setDoType(doType);
		rForm.setPath(path);
		rForm.setTopTr(topTr);

		// 查询内容列表
		List<HashMap<String, String>> searchContentList = dao
				.getSearchContentList(model, user);

		model.setSearchContentList(searchContentList);
	}

	/**
	 * 初始化表单标题
	 * 
	 * @author 伟大的骆
	 */
	private List<HashMap<String, String>> getDefaultValue(CustomGnmkForm model,
			User user) {

		CustomGnmkDAO customGnmkDAO = new CustomGnmkDAO();

		DAO dao = DAO.getInstance();

		// 表头列表
		List<HashMap<String, String>> topList = customGnmkDAO.getGnmkTopList(
				model, user);

		List<String> enList = new ArrayList<String>();
		List<String> cnList = new ArrayList<String>();

		if (topList != null && topList.size() > 0) {
			for (int i = 0; i < topList.size(); i++) {
				HashMap<String, String> top = topList.get(i);
				enList.add(top.get("dm"));
				cnList.add(top.get("mc"));
			}
		}

		List<HashMap<String, String>> topTr = dao.arrayToList(enList
				.toArray(new String[] {}), cnList.toArray(new String[] {}));

		return topTr;
	}
	
	/**
	 * 获得功能模块信息
	 * 
	 * @author 伟大的骆
	 */
	private HashMap<String, String> getGnmkInfo(String gnmkdm) {

		String tableName = "xg_custom_gnmkb";
		String[] colList = new String[] { "id", "tablename" };
		String pk = "gnmkdm";
		String pkValue = gnmkdm;

		return CommonQueryDAO.commonQueryOne(tableName, colList, pk, pkValue);
	}
	
	/**
	 * 初始化表单标题
	 * 
	 * @author 伟大的骆
	 */
	private void initCustomGnmkModel(CustomGnmkForm model, User user) {
		//功能模块代码
		String gnmkdm = model.getGnmkdm();

		HashMap<String, String> gnmkInfo = getGnmkInfo(gnmkdm);
		
		String gnmk_id = gnmkInfo.get("id");
		String xmb = gnmkInfo.get("tablename");
		
		model.setXmb(xmb);

	}
}
