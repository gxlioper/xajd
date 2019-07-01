package xsgzgl.xsxx.cssz.grxx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommDAO;
import xgxt.form.User;
import xsgzgl.xsxx.cssz.XsxxCsszForm;
import xsgzgl.xsxx.model.ZdqxModel;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 学生信息_参数设这_个人信息_DAO类
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

public class XsxxCsszDAO extends CommDAO {
	
	/**
	 * 获得字段设置列表
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getZdszList(String zd) {

		StringBuilder sql = new StringBuilder();
		sql.append("select zd,zdm,sslx,zdlx,checked,");
		sql.append("source_table,select_dm,select_mc,xssx, ");
		sql.append("xsqx,lsqx,bm ");
		sql.append("from xg_xsxx_grxx_zdszb ");
		sql.append(Base.isNull(zd) ? "" : "where zd='" + zd + "' ");
		sql.append("order by sslx,to_number(xssx)");

		DAO dao = DAO.getInstance();
		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] {}, new String[] { "zd", "zdm", "sslx", "zdlx",
						"checked", "source_table", "select_dm", "select_mc",
						"xsqx", "lsqx", "bm" });

		return list;
	}
	
	/**
	 * 获得所属类型列表
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getSslxList() {

		StringBuilder sql = new StringBuilder();
		sql.append("select distinct sslx ");
		sql.append("from xg_xsxx_grxx_zdszb ");

		DAO dao = DAO.getInstance();
		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] {}, new String[] { "sslx" });

		return list;
	}
	
	/**
	 * 初始化字段权限
	 * 
	 * @author luojw
	 */
	public boolean initZdqx(XsxxCsszForm model, User user) {
		
		DAO dao = DAO.getInstance();
		
		boolean flag = false;
		
		//用户类型
		String yhlx = model.getYhlx();
		
		StringBuilder sql = new StringBuilder();
		sql.append("update xg_xsxx_grxx_zdszb set ");
		sql.append(yhlx+"='0' ");
		
		try {
			flag = dao.runUpdate(sql.toString(), new String[]{});
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		
		return flag;
	}
	
	/**
	 * 保存字段权限
	 * 
	 * @author luojw
	 */
	public boolean saveZdqx(XsxxCsszForm model, User user) {

		boolean flag = false;

		// 字段权限Model
		ZdqxModel zdqxModel = model.getZdqxModel();
		// 用户类型
		String yhlx = model.getYhlx();
		// 字段
		String[] zd = zdqxModel.getZd();

		StringBuilder sql = new StringBuilder();
		sql.append("update xg_xsxx_grxx_zdszb set ");
		sql.append(yhlx + "='1' ");
		sql.append("where zd=? ");

		if (zd != null && zd.length > 0) {

			List<String[]> params = new ArrayList<String[]>();

			for (int i = 0; i < zd.length; i++) {
				String[] value = new String[] { zd[i] };
				params.add(value);
			}

			try {
				flag = saveArrDate(sql.toString(), params,
						"xg_xsxx_grxx_zdszb", user);
			} catch (Exception e) {
				// TODO 自动生成 catch 块
				e.printStackTrace();
			}
		}

		return flag;
	}
	
	/**
	 * 保存是否设置完毕
	 * 
	 * @author luojw
	 */
	public boolean saveOver(XsxxCsszForm model, User user) {
		
		DAO dao = DAO.getInstance();
		
		boolean flag = false;

		StringBuilder sql = new StringBuilder();
		sql.append("update xg_xsxx_grxx_szb set ");
		sql.append("over='yes' ");
		
		try {
			flag = dao.runUpdate(sql.toString(), new String[]{});
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		
		return flag;
	}
	
	/**
	 * 获得个人信息参数设置
	 * 
	 * @author 伟大的骆
	 * 
	 */
	public HashMap<String, String> getGrxxCssz() {

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();
		sql.append("select sfsh,lcid,sqkssj,sqjssj ");
		sql.append(",shkssj,shjssj ");
		sql.append("from xg_xsxx_grxx_szb ");
		sql.append("where over='yes' ");
		sql.append("and rownum='1' ");

		return dao.getMap(sql.toString(), new String[] {}, new String[] {
				"sfsh", "lcid", "sqkssj", "sqjssj", "shkssj", "shjssj" });
	}
}
