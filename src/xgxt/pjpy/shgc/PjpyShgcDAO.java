package xgxt.pjpy.shgc;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.action.Base;

/**
 * Title: 学生工作管理系统 Description: 上海工程技术大学评奖评优DAO Copyright: Copyright (c) 2009
 * Company: zfsoft Author: 周觅 Version: 1.0 Time: 2009-04-03
 */
public class PjpyShgcDAO {
	DAO dao = DAO.getInstance();

	List<String> values = new ArrayList<String>();// 查询条件值

	/**
	 * @author ZhouMi
	 * @describe 保存奖学金学院人数
	 */
	public void saveJxjxyrs(String[] pks, String[] szrs,
			HttpServletRequest request) throws Exception {
		String[] sql = new String[pks.length];
		for (int i = 0; i<pks.length; i++){
			sql[i] = "update pjpy_shgc_xyrsb set szrs='"+szrs[i]+"' where bbdm||xydm='"+Base.chgNull(pks[i], "", 1)+"'";
		}
		dao.runBatch(sql);
	}
	
	/**
	 * @author ZhouMi
	 * @describe 保存奖学金专业人数
	 */
	public void saveJxjzyrs(String[] pks, String[] szrs,
			HttpServletRequest request) throws Exception {
		String[] sql = new String[pks.length];
		for (int i = 0; i<pks.length; i++){
			sql[i] = "update pjpy_shgc_zyrsb set szrs='"+szrs[i]+"' where bbdm||zydm='"+Base.chgNull(pks[i], "", 1)+"'";
		}
		dao.runBatch(sql);
	}
	
	/**
	 * @author ZhouMi
	 * @describe 保存奖学金班级人数
	 */
	public void saveJxjbjrs(String[] pks, String[] szrs,
			HttpServletRequest request) throws Exception {
		String[] sql = new String[pks.length];
		for (int i = 0; i<pks.length; i++){
			sql[i] = "update pjpy_shgc_bjrsb set szrs='"+szrs[i]+"' where bbdm||bjdm='"+Base.chgNull(pks[i], "", 1)+"'";
		}
		dao.runBatch(sql);
	}
}
