package xsgzgl.pjpy.bjlhdx.wdpj;

import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.Pages;
import xsgzgl.pjpy.general.PjpyGeneralForm;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 评奖评优_我的评奖_北京联合大学_DAO类
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

public class PjpyWdpjDAO extends xsgzgl.pjpy.general.wdpj.PjpyWdpjDAO {

	// ==================执行查询操作 begin ===========================

	/**
	 * 获得项目信息列表
	 * 
	 * @date 2013-02-04
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getXmxxList(PjpyGeneralForm myForm,
			String pjxn, String pjxq, String pjnd, String zgh) {

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();
		sql.append("select a.* from( ");
		sql.append("select a.*,rownum r from( ");
		sql.append("select a.xmdm,a.xmmc,a.lcid, ");
		sql.append("decode(a.xmlx, '01', '奖学金', '02', '荣誉称号') xmlx ");
		sql.append("from xg_pjpy_pjxmwhb a ");
		sql.append("where 1 = 1 ");
		sql.append("and a.pjxn = ? ");
		sql.append("and a.pjxq = ? ");
		sql.append("and a.pjnd = ? ");
		sql.append("and exists (select 1 ");
		sql.append("from xg_xtwh_spbz b, xg_xtwh_spgw c, xg_xtwh_spgwyh d ");
		sql.append("where 1 = 1 ");
		sql.append("and a.lcid = b.splc ");
		sql.append("and b.spgw = c.id ");
		sql.append("and c.id = d.spgw ");
		sql.append("and d.spyh = ?) ");
		sql.append("order by a.xmdm ");
		sql.append(") a ");
		sql.append(") a ");

		Pages pages = myForm.getPages();
		sql.append("where r > ");
		sql.append(pages.getStart());
		sql.append(" and r <= ");
		sql.append((pages.getStart() + pages.getPageSize()));
		
		return dao.getList(sql.toString(),
				new String[] { pjxn, pjxq, pjnd, zgh }, new String[] { "xmdm",
						"xmmc", "xmlx" });
	}

	/**
	 * 获得审核岗位信息列表
	 * 
	 * @date 2013-02-04
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getShgwxxList(
			List<HashMap<String, String>> xmxxList, User user) {

		DAO dao = DAO.getInstance();
		
		StringBuilder sql = new StringBuilder();

		sql.append("select a.xmdm, b.splc, c.id gwdm, c.mc gwmc,b.xh lv ");
		sql.append("from xg_pjpy_pjxmwhb a,xg_xtwh_spbz b, xg_xtwh_spgw c, xg_xtwh_spgwyh d ");
		sql.append("where 1 = 1 ");
		sql.append("and a.lcid = b.splc ");
		sql.append("and b.spgw = c.id ");
		sql.append("and c.id = d.spgw ");
		sql.append("and d.spyh = ? ");
		
		if (xmxxList.size() > 0){
			sql.append("and a.xmdm in (");
			for (int i = 0; i < xmxxList.size(); i++) {
				if (i != 0) {
					sql.append(",");
				}
				sql.append("'" + xmxxList.get(i).get("xmdm") + "'");
			}
			sql.append(")");
		}
		
		sql.append("order by b.splc,to_number(b.xh) ");

		return dao.getList(sql.toString(),
				new String[] {user.getUserName()}, new String[] { "xmdm",
						"gwdm", "gwmc" });
	}
	
	/**
	 * 获得项目需审核信息列表
	 * 
	 * @date 2013-02-04
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getXshxxList(
			List<HashMap<String, String>> xmxxList, User user) {

		DAO dao = DAO.getInstance();

		// 权限过滤
		String searchTjByUser = SearchService.getSearchTjByUser(user, "f",
				"xydm", "bjdm");
		
		StringBuilder sql = new StringBuilder();

		sql.append("select a.xmdm, c.id gwdm, b.xh, nvl(count(1), 0) xshrs ");
		sql.append("from xg_pjpy_pjxmwhb    a, ");
		sql.append("xg_xtwh_spbz       b, ");
		sql.append("xg_xtwh_spgw       c, ");
		sql.append("xg_xtwh_spgwyh     d, ");
		sql.append("xg_pjpy_pjxmshb    e, ");
		sql.append("xg_view_pjpy_pjryk f ");
		sql.append("where a.lcid = b.splc ");
		sql.append("and b.spgw = c.id ");
		sql.append("and c.id = d.spgw ");
		sql.append("and d.spyh = ? ");
		sql.append("and c.id = e.xtgwid ");
		sql.append("and a.pjxn = e.pjxn ");
		sql.append("and e.xh = f.xh ");
		sql.append("and a.pjxq = e.pjxq ");
		sql.append("and a.pjnd = e.pjnd ");
		sql.append("and a.xmdm = e.xmdm ");
		sql.append("and (e.shzt = 'wsh' or e.shzt = 'xcs') ");
		sql.append(searchTjByUser);	
		
		if (xmxxList.size() > 0){
			sql.append("and a.xmdm in (");
			for (int i = 0; i < xmxxList.size(); i++) {
				if (i != 0) {
					sql.append(",");
				}
				sql.append("'" + xmxxList.get(i).get("xmdm") + "'");
			}
			sql.append(")");
		}
		
		
		sql.append("and (exists (select 1 ");
		sql.append("from (select c.id, c.mc, b.xh plxh, e.* ");
		sql.append("from xg_pjpy_pjxmwhb a, ");
		sql.append("xg_xtwh_spbz    b, ");
		sql.append("xg_xtwh_spgw    c, ");
		sql.append("xg_xtwh_spgwyh  d, ");
		sql.append("xg_pjpy_pjxmshb e ");
		sql.append("where a.lcid = b.splc ");
		sql.append("and b.spgw = c.id ");
		sql.append("and c.id = d.spgw ");
		sql.append("and c.id = e.xtgwid ");
		sql.append("and a.pjxn = e.pjxn ");
		sql.append("and a.pjxq = e.pjxq ");
		sql.append("and a.pjnd = e.pjnd ");
		sql.append("and a.xmdm = e.xmdm) f ");
		sql.append("where b.xh = f.plxh + 1 ");
		sql.append("and f.shzt = 'tg' ");
		sql.append("and e.xmdm = f.xmdm ");
		sql.append("and e.pjxn = f.pjxn ");
		sql.append("and e.pjxq = f.pjxq ");
		sql.append("and e.pjnd = f.pjnd ");
		sql.append("and e.xh = f.xh) or b.xh = 1) ");
		sql.append("group by a.xmdm, c.id, c.mc, b.xh ");

		return dao.getList(sql.toString(),
				new String[] {user.getUserName()}, new String[] { "xmdm",
						"gwdm", "xshrs" });
	}
	
	/**
	 * 获得项目已审核信息列表
	 * 
	 * @date 2013-02-04
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getYshxxList(
			List<HashMap<String, String>> xmxxList, User user) {

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();
		
		sql.append("select a.xmdm, a.xmmc, c.id gwdm, c.mc, b.xh, count(1) yshrs ");
		sql.append("from xg_pjpy_pjxmwhb a, ");
		sql.append("xg_xtwh_spbz    b, ");
		sql.append("xg_xtwh_spgw    c, ");
		sql.append("xg_xtwh_spgwyh  d, ");
		sql.append("xg_pjpy_pjxmshb e ");
		sql.append("where a.lcid = b.splc ");
		sql.append("and b.spgw = c.id ");
		sql.append("and c.id = d.spgw ");
		sql.append("and e.xmdm = a.xmdm ");
		sql.append("and e.xtgwid = c.id ");
		
		if (xmxxList.size() > 0){
			sql.append("and a.xmdm in (");
			for (int i = 0; i < xmxxList.size(); i++) {
				if (i != 0) {
					sql.append(",");
				}
				sql.append("'" + xmxxList.get(i).get("xmdm") + "'");
			}
			sql.append(")");
		}
		
		
		sql.append("and e.shr = ? ");
		sql.append("and d.spyh = ? ");
		sql.append("group by a.xmdm, a.xmmc, c.id, c.mc, b.xh ");

		return dao.getList(sql.toString(), new String[] { user.getUserName(),
				user.getUserName() }, new String[] { "xmdm", "gwdm", "yshrs" });
	}
	
	/**
	 * 获得我的审核信息列表
	 * 
	 * @date 2013-02-04
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getWdshList(String xmdm, String gwid,
			User user) {

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();

		sql.append("select a.xh, b.xm, b.bjmc,a.shsj, ");
		sql.append("decode(a.shzt,'tg','通过','btg','不通过','th','退回','xcs','需重审') shzt ");
		sql.append("from xg_pjpy_pjxmshb a, xg_view_pjpy_pjryk b ");
		sql.append("where a.xh = b.xh ");
		sql.append("and a.xmdm=? ");
		sql.append("and a.xtgwid=? ");
		sql.append("and a.shr=? ");
		sql.append("order by shsj desc");

		return dao.getList(sql.toString(), new String[] { xmdm, gwid,
				user.getUserName() }, new String[] { "xh", "xm", "bjmc",
				"shsj", "shzt" });
	}
	
	// ==================执行查询操作 end =============================
}
