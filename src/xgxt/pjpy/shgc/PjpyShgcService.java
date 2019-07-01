package xgxt.pjpy.shgc;

import javax.servlet.http.HttpServletRequest;

/**
 * Title: 学生工作管理系统
 * Description:上海工程技术大学评奖评优Service
 * Copyright: Copyright (c) 2009
 * Company: zfsoft
 * Author: 周觅
 * Version: 1.0
 * Time: 2009-04-03
 */
public class PjpyShgcService {

	PjpyShgcDAO dao = null;// 数据操作DAO

	/**
	 * @author ZhouMi
	 * @describe 保存奖学金学院人数
	 */
	public void saveJxjxyrs(String[] pks, String[] szrs,
			HttpServletRequest request) throws Exception {
		dao = new PjpyShgcDAO();
		dao.saveJxjxyrs(pks, szrs, request);
	}
	
	/**
	 * @author ZhouMi
	 * @describe 保存奖学金专业人数
	 */
	public void saveJxjzyrs(String[] pks, String[] szrs,
			HttpServletRequest request) throws Exception {
		dao = new PjpyShgcDAO();
		dao.saveJxjzyrs(pks, szrs, request);
	}
	
	/**
	 * @author ZhouMi
	 * @describe 保存奖学金班级人数
	 */
	public void saveJxjbjrs(String[] pks, String[] szrs,
			HttpServletRequest request) throws Exception {
		dao = new PjpyShgcDAO();
		dao.saveJxjbjrs(pks, szrs, request);
	}
}
