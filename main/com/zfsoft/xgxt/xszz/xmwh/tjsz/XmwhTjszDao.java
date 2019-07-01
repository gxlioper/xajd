/**
 * @部门:学工产品事业部
 * @日期：2013-4-18 下午02:42:37 
 */
package com.zfsoft.xgxt.xszz.xmwh.tjsz;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.action.Base;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 学生资助
 * @类功能描述: 项目维护-条件设置
 * @作者： ligl
 * @时间： 2013-4-18 下午02:42:37
 * @版本： V1.0
 * @修改记录:
 */
public class XmwhTjszDao extends SuperDAOImpl<XmwhTjszForm> {
	private static String CHAR_DH = ",";

	/**
	 * 
	 * @描述:普通查询方法
	 * @作者：ligl
	 * @日期：2013-4-18 下午02:42:55
	 * @修改记录: void 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getPageList(XmwhTjszForm model)
			throws Exception {
		return null;
	}

	/**
	 * 
	 * @描述:高级查询方法
	 * @作者：ligl
	 * @日期：2013-4-18 下午02:42:55
	 * @修改记录: void 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getPageList(XmwhTjszForm model,
			User user) throws Exception {
		return null;
	}

	/**
	 * 
	 * @描述:查询所有条件
	 * @作者：ligl
	 * @日期：2013-4-18 下午02:42:55
	 * @修改记录: void 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getAllTj() throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("select * from xg_xszz_new_tjdmb a order by tjmc ");
		return dao.getListNotOut(sql.toString(), null);
	}

	/**
	 * 
	 * @描述:查询所有关系
	 * @作者：ligl
	 * @日期：2013-4-18 下午02:42:55
	 * @修改记录: void 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getAllGx() throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("select * from xg_xszz_new_gxdmb a  ");
		return dao.getListNotOut(sql.toString(), null);
	}

	/**
	 * 
	 * @描述:查询所有条件关系
	 * @作者：ligl
	 * @日期：2013-4-18 下午02:42:55
	 * @修改记录: void 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getAllTjGx() throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("select * from xg_xszz_new_tjgxdzb a  ");
		return dao.getListNotOut(sql.toString(), null);
	}

	/**
	 * 
	 * @描述:查询已设置条件值
	 * @作者：ligl
	 * @日期：2013-4-18 下午02:42:55
	 * @修改记录: void 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getTjsz(String xmdm) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("select * from xg_xszz_new_zzxmtjb a  ");
		sql.append(" where a.xmdm=? ");
		String[] inputValue = { xmdm };
		return dao.getListNotOut(sql.toString(), inputValue);
	}

	/**
	 * 
	 * @描述:查询已设置条件值,包含关联属性
	 * @作者：ligl
	 * @日期：2013-4-18 下午02:42:55
	 * @修改记录: void 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getTjszGl(String xmdm , String xh)
			throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.tjdm,a.sfqy,a.tjgx,a.tjz,a.xn,");
		sql.append(" a.xq,a.bjdl,a.xmdm,b.tjmc,b.ffm,");
		sql.append(" b.tjsm,b.sqts,b.zqlx,c.gxdm,c.gxmc,");
		sql.append(" d.gxlx,d.gxz,d.gxzgs ,a.xn ylzq ");
		sql.append(" from xg_xszz_new_zzxmtjb a,");
		sql.append(" xg_xszz_new_tjdmb b,xg_xszz_new_gxdmb c,xg_xszz_new_tjgxdzb d  ");
		sql.append(" where a.tjdm=b.tjdm and a.tjgx=c.gxdm and b.tjdm=d.tjdm and c.gxdm=d.gxdm ");
		sql.append(" and a.xmdm=?");
		
		//以下为条件范围
		sql.append(" and (a.bjdl = 'all' or exists (select 1 from xg_pjpy_new_tsxsb t1 ");
		sql.append(" where a.bjdl = t1.lxdm and t1.xh=? and t1.xn=? and t1.xq=?)");
		sql.append(" or exists (select 1 from xg_xtwh_bjlbb t2 ");
		sql.append(" where a.bjdl = t2.lbdm and t2.bjdm = (select bjdm from xsxxb where xh = ?)))");
		
		String[] inputValue = { xmdm,xh,Base.currXn,Base.currXq,xh };
		return dao.getListNotOut(sql.toString(), inputValue);
	}

	/**
	 * 
	 * @描述:设置启用/不启用状态
	 * @作者：ligl
	 * @日期：2013-4-27 上午09:32:08
	 * @修改记录:
	 * @param xmdm
	 * @param keys
	 * @param sfqy
	 * @return
	 * @throws Exception boolean 返回类型
	 * @throws
	 */
	public boolean qysz(String xmdm, String sfqy, String adds, String updates)
			throws Exception {
		int[] result = null;

		String[] add = adds.split(CHAR_DH);
		String[] update = updates.split(CHAR_DH);
		List<String> sqlList = new ArrayList<String>();
		for (int i = 0; i < add.length; i++) {
			if (add[i] == null || add[i].equals("")) {
				continue;
			}
			String sql = "insert into xg_xszz_new_zzxmtjb(tjdm,xmdm,sfqy)";
			sql += " values('" + add[i] + "','" + xmdm + "','" + sfqy + "')";
			sqlList.add(sql);
		}

		for (int i = 0; i < update.length; i++) {
			if (update[i] == null || update[i].equals("")) {
				continue;
			}
			String sql = "update xg_xszz_new_zzxmtjb ";
			sql += " set sfqy='" + sfqy + "'";
			sql += " where tjdm='" + update[i] + "' and xmdm='" + xmdm + "'";
			sqlList.add(sql);
		}

		String[] sqls = new String[sqlList.size()];
		for (int i = 0; i < sqlList.size(); i++) {
			sqls[i] = sqlList.get(i);
		}
		result = dao.runBatch(sqls);
		return dao.checkBatch(result);
	}

	/**
	 * 
	 * @描述:设置
	 * @作者：ligl
	 * @日期：2013-4-22 下午07:20:02
	 * @修改记录:
	 * @param model
	 * @param key
	 * @return
	 * @throws Exception boolean 返回类型
	 * @throws
	 */
	public boolean runDeal(String xmdm, List<XmwhTjszForm> list)
			throws Exception {
		int[] result = null;

		List<String> sqlList = new ArrayList<String>();
		String sql = null;
		sql = "delete from xg_xszz_new_zzxmtjb ";
		sql += " where xmdm='" + xmdm + "'";
		sqlList.add(sql);
		for (XmwhTjszForm form : list) {
			String tjz = URLDecoder.decode(URLDecoder.decode(form.getTjz(),"UTF-8"),"UTF-8");
			if (tjz != null) {
				tjz = tjz.trim();
			} else {
				tjz = "";
			}
			sql = "insert into xg_xszz_new_zzxmtjb";
			sql += "(bjdl,sfqy,tjdm,tjgx,tjz,xmdm,xn,xq)";
			sql += " values('" + form.getYyfw() + "','" + form.getSfqy()
					+ "','" + URLDecoder.decode(URLDecoder.decode(form.getTjdm(),"UTF-8"),"UTF-8") + "','" + form.getTjgx() + "','"
					+ tjz + "','" + xmdm + "','" + form.getXn() + "','')";
			sqlList.add(sql);
		}
		String[] sqls = new String[sqlList.size()];
		for (int i = 0; i < sqlList.size(); i++) {
			sqls[i] = sqlList.get(i);
		}
		result = dao.runBatch(sqls);
		return dao.checkBatch(result);
	}

	/**
	 * 
	 * @描述:删除
	 * @作者：ligl
	 * @日期：2013-4-27 上午09:32:08
	 * @修改记录:
	 * @param xmdm
	 * @param keys
	 * @param sfqy
	 * @return
	 * @throws Exception boolean 返回类型
	 * @throws
	 */
	public boolean delDeal(String xmdm, String tjdm) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("delete from xg_xszz_new_zzxmtjb a  ");
		sql.append(" where a.xmdm=? and a.tjdm=? ");
		String[] input = { xmdm, tjdm };
		return dao.runUpdate(sql.toString(), input);
	}

	/**
	 * 
	 * @描述:查询等级考试类别
	 * @作者：ligl
	 * @日期：2013-4-18 下午02:42:55
	 * @修改记录: void 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getXsdjks() throws Exception {
		StringBuilder sql = new StringBuilder();
		sql
				.append("select distinct t.djksmc value from xsdjksb t order by t.djksmc ");
		return dao.getListNotOut(sql.toString(), null);
	}

	/**
	 * 
	 * @描述:获取代码、名称列表
	 * @作者：ligl
	 * @日期：2013-7-12 上午11:51:52
	 * @修改记录: 
	 * @param sql
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getDmMc(String bm,String dm,String mc) throws Exception {
		String sql = "select distinct " + dm + "," + mc + " from " + bm + " order by " + dm;
		return dao.getListNotOut(sql, null);
	}

	protected void setTableInfo() {
		super.setTableName("xg_xszz_new_zzxmrsszb");
		super.setKey("guid");
	}
}
