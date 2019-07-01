package xgxt.pjpy.comm.pjpy.jbsz;

import xgxt.DAO.DAO;
import xgxt.comm.CommDAO;
import xgxt.gygl.gywh.DelDetectModel;
import xgxt.pjpy.comm.pjpy.PjxtszModel;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 评奖评优_基本设置_DAO类
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

public class PjpyJbszDAO extends CommDAO{

	/**
	 * 保存评奖评优条件库
	 * 
	 * @author 伟大的骆
	 */
	public Boolean savePjpyTjk(PjpyJbszForm model)
			throws Exception {

		// 条件代码
		String[] tjdm = model.getTjdm();
		// SQL
		String[] sql = (tjdm != null && tjdm.length > 0) ? new String[2]
				: new String[1];

		// 未启用的条件
		StringBuilder wqySql = new StringBuilder();
		wqySql.append(" update xg_pjpy_pjtjkb a set sfqy = 'no' ");

		if (tjdm != null && tjdm.length > 0) {

			wqySql.append(" where not exists ");
			wqySql.append(" ( select 1 from xg_pjpy_pjtjkb b ");
			wqySql.append(" where a.tjdm = b.tjdm and ( ");

			for (int i = 0; i < tjdm.length; i++) {
				if (i == 0) {
					wqySql.append(" tjdm = '" + tjdm[i] + "' ");
				} else {
					wqySql.append(" or tjdm = '" + tjdm[i] + "' ");
				}
			}

			wqySql.append(" )) ");
		}

		sql[0] = wqySql.toString();

		// 启用的条件
		if (tjdm != null && tjdm.length > 0) {

			StringBuilder qySql = new StringBuilder();
			qySql.append(" update xg_pjpy_pjtjkb set sfqy = 'yes' ");

			for (int i = 0; i < tjdm.length; i++) {
				if (i == 0) {
					qySql.append(" where tjdm = '" + tjdm[i] + "' ");
				} else {
					qySql.append(" or tjdm = '" + tjdm[i] + "' ");
				}
			}

			sql[1] = qySql.toString();
		}

		return saveArrDate(sql);
	}
	
	
	
	/**
	 * 查询评奖周期设置状态
	 * @return
	 */
	public String getPjpyPjzqSfsz(){
		DAO dao = DAO.getInstance();
		String sql = "select sfsz from xg_pjpy_pjzqztb where rownum=1";
		
		return dao.getOneRs(sql, new String[]{}, "sfsz");
	}
	
	
	/**
	 * 修改评奖周期设置状态
	 * @return
	 * @throws Exception
	 */
	public boolean updatePjzqSfsz(String sfsz) throws Exception{
		DAO dao = DAO.getInstance();
		String sql = "insert into xg_pjpy_pjzqztb (sfsz) values (?)";
		
		return dao.runUpdate("delete from xg_pjpy_pjzqztb", new String[]{}) && dao.runUpdate(sql, new String[]{sfsz});
	}
	
	
	
	/**
	 * 评奖评优-综测设置（当前周期的记录数）
	 * @param zczq
	 * @return
	 */
	public String getZczqSfsz(String zczq){
		
		String[] colList = null;
		PjxtszModel model = PjxtszModel.pjxtszModel;
		DAO dao = DAO.getInstance();
		
		StringBuilder sql = new StringBuilder();
		sql.append("select count(1) count from xg_pjpy_zcxmb where ");
		 
		if ("xn".equalsIgnoreCase(zczq)){
			sql.append("xn = ? ");
			colList = new String[]{model.getPjxn()};
		} else if ("xq".equalsIgnoreCase(zczq)) {
			sql.append("xn = ? ")
			   .append("and xq = ?");
			colList = new String[]{model.getPjxn(),model.getPjxq()};
		} else {
			sql.append("nd = ?");
			colList = new String[]{model.getPjnd()};
		}
		
		return dao.getOneRs(sql.toString(), colList, "count");
	}
	
	
	
	/**
	 * 当前评奖周期的评奖项目个数
	 * @return
	 */
	public String getPjxmSfwh(){
		DAO dao = DAO.getInstance();
		PjxtszModel model = PjxtszModel.pjxtszModel;
		
		String sql = "select count(1) count from xg_pjpy_pjxmwhb where pjxn=? and pjxq=? and pjnd=?";
		return dao.getOneRs(sql, new String[]{model.getPjxn(),model.getPjxq(),model.getPjnd()}, "count");
	}
	
	
	/**
	 * 评奖人员设置
	 * @return
	 */
	public String getPjrySfsz(){
		DAO dao = DAO.getInstance();
		PjxtszModel model = PjxtszModel.pjxtszModel;
		
		String sql = "select count(1) count from xg_pjpy_xsb where pjxn=? and pjxq=? and pjnd=?";
		return dao.getOneRs(sql, new String[]{model.getPjxn(),model.getPjxq(),model.getPjnd()}, "count");
	}
	
	
	/**
	 * 人数设置初始化
	 * @return
	 * @throws Exception 
	 */
	public boolean rsszCsh(){
		DAO dao = DAO.getInstance();
		String sql = "delete from xg_pjpy_rsszb";
		
		try {
			return dao.runUpdate(sql, new String[]{});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * 删除人数设置
	 * @return
	 * @throws Exception 
	 */
	public boolean delRssz(DelDetectModel model){
		
		PjxtszModel jbszModel = PjxtszModel.pjxtszModel;
		String pjxn = jbszModel.getPjxn();
		String pjxq = jbszModel.getPjxq();
		String pjnd = jbszModel.getPjnd();
		
		String[] xmdm = model.getPkValue();
		
		DAO dao = DAO.getInstance();
		StringBuilder sql = new StringBuilder();

		sql.append("delete from xg_pjpy_rsszb ");
		sql.append("where 1 = 1 ");

		if (xmdm != null && xmdm.length > 0) {
			sql.append("and ( ");
			for (int i = 0; i < xmdm.length; i++) {
				if (i != 0) {
					sql.append(" or ");
				}
				sql.append(" xmdm||'"+pjxn+"'||'"+pjxq+"'||'"+pjnd+"' = ? ");
			}
			sql.append(") ");
		}
		
		try {
			return dao.runUpdate(sql.toString(), xmdm);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
