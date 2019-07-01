/**
 * @部门:学工产品事业部
 * @日期：2015-9-8 上午11:17:53 
 */  
package com.zfsoft.xgxt.xsxx.xygl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import xgxt.DAO.DAO;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 学生信息new
 * @类功能描述: 校友管理 
 * @作者： 沈晓波[工号:1123]
 * @时间： 2015-9-8 上午11:17:53 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XyglDao extends SuperDAOImpl<XyglForm> {
	
	/**
	 * 普通查询方法
	 */
	public List<HashMap<String, String>> getPageList(XyglForm model)
			throws Exception {
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(XyglForm model, User user)
			throws Exception {
		// ====================过滤条件===================================

		// 高级查询条件
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		// 权限过滤
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t",
				"xydm", "bjdm");
		// 高级查询输入值
		String[] inputV = SearchService.getTjInput(model.getSearchModel());

		// query += searchTjByUser;
		// ====================过滤条件 end================================
		// ====================SQL拼装================================
		StringBuilder sb = new StringBuilder();
		sb.append(" select * from (select a.xh,a.sfzx, ")
		  .append(" a.xm, ")
		  .append(" decode(a.xb, '1', '男', '2', '女', a.xb) xb, ")
		  .append(" a.nj, ")
		  .append(" a.xydm, ")
		  .append(" a.zydm, ")
		  .append(" a.bjdm, ")		
		  .append(" a.xymc, ")
		  .append(" a.zymc, ")
		  .append(" a.bjmc, ")
		  .append(" a.jgmc, ")
		  .append(" a.qqhm, ")
		  .append(" b.pyccmc, ")
		  .append(" a.SHGXGX2, ")
		  .append(" a.SHGXGZDW2, ")
		  .append(" a.SHGXZW2, ")
		  .append(" a.sjhm, ")
		  .append(" a.RXQDW, ")
		  .append(" a.GZBX ")
		  .append(" from view_xsxxb a ")
		  .append(" left join XG_XSXX_PYCCDMB b ")
		  .append(" on a.pycc = b.pyccdm ")
		  .append(" ) t where 1=1 ");
		
		if (model.getSfzx() != null && model.getSfzx().equals("0")) {// 非在校
			sb.append("  and sfzx='不在校'  ");
		} else {// 在校
			sb.append(" and (sfzx='在校' or sfzx is null) ");
		}
		sb.append(searchTjByUser);
		sb.append(" ");
		sb.append(searchTj);
		sb.append("");
		return getPageList(model, sb.toString(), inputV);
	}
	
	
	/**
	 * 保存进学生辅助信息表
	 */
	public boolean saveXsqtxx(XyglForm model) throws Exception {
		String[] sql = { "insert into xsfzxxb(xh) values ('" + model.getXh()
				+ "')" };
		int[] result = dao.runBatch(sql);
		boolean flag = dao.checkBatch(result);
		return flag;
	}
	
	
	/**
	 * 修改校友管理信息
	 */
	public boolean updateInfo(HashMap<String, String> valueMap)
			throws Exception {
		CommService service = new CommService();
		// 保存SQL
		StringBuilder sql = new StringBuilder();
		// 字段名
		StringBuilder comments = new StringBuilder();
		// 修改条件
		StringBuilder query = new StringBuilder();
		// 修改字段数值
		List<String> inputV = new ArrayList<String>();
		// 修改数据主键值
		List<String> queryV = new ArrayList<String>();
		// 主键字段
		String pk = "xh";
		sql.append(" update xsxxb set sfzx = '不在校', ");
		int n = 0;
		boolean flag = false;
		if (valueMap.get(pk) != null) {
			for (Map.Entry<String, String> entry : valueMap.entrySet()) {
				// 键(字段名)
				String paramName = entry.getKey();
				// 值(字段值)
				String paramValue = entry.getValue();
				// ------------------主键拼接 begin --------------------
				if (pk.equalsIgnoreCase(paramName)) {
					// ------------主键条件 begin------------
					query.append(" and ");
					query.append(pk);
					query.append("=?");
					// ------------主键条件 end------------
					queryV.add(paramValue);
				} else {
					if (n != 0) {
						comments.append(",");
					}
					// -------修改信息 begin----------
					comments.append(paramName);
					comments.append("=?");
					// -------修改信息 end ----------
					n++;
					// --------------修改值------------------
					inputV.add(service.unicode2Gbk(paramValue));
				}
				// ---------....主键字段是不需要修改的 end----------
			}
			// 插入的字段
			sql.append(comments);
			sql.append(" where 1=1  ");
			sql.append(query);
			inputV.addAll(queryV);
		
			flag = dao.runUpdate(sql.toString(), inputV
					.toArray(new String[] {}));
		}

		return flag;
	}
	
	
	/**
	 * 修改辅助信息表中同步信息
	 */
	public boolean updateInfoXsfzxx(HashMap<String, String> valueMap)
			throws Exception {
		//////////学生辅助信息表，若记录不存在，则新增/////////
		XyglForm model = new XyglForm();
		String xh = valueMap.get("xh");
		if(xh != null && !xh.equals("")){
			model.setXh(valueMap.get("xh"));			
			String sql = "select count(xh) cnt from xsfzxxb where xh=?";
			String oneRs = dao.getOneRs(sql, new String[] { xh }, "cnt");
			if(oneRs == null||oneRs.equals("0")){
				saveXsqtxx(model);
			}
		}
		///////////////////
		
		CommService service = new CommService();
		// 保存SQL
		StringBuilder sql = new StringBuilder();
		// 字段名
		StringBuilder comments = new StringBuilder();
		// 修改条件
		StringBuilder query = new StringBuilder();
		// 修改字段数值
		List<String> inputV = new ArrayList<String>();
		// 修改数据主键值
		List<String> queryV = new ArrayList<String>();
		// 主键字段
		String pk = "xh";
		sql.append(" update xsfzxxb set ");
		int n = 0;
		for (Map.Entry<String, String> entry : valueMap.entrySet()) {
			// 键(字段名)
			String paramName = entry.getKey();
			// 值(字段值)
			String paramValue = entry.getValue();
			// ------------------主键拼接 begin --------------------
			if (pk.equalsIgnoreCase(paramName)) {
				// ------------主键条件 begin------------
				query.append(" and ");
				query.append(pk);
				query.append("=?");
				// ------------主键条件 end------------
				queryV.add(paramValue);
			} else {
				if (n != 0) {
					comments.append(",");
				}
				// -------修改信息 begin----------
				comments.append(paramName);
				comments.append("=?");
				// -------修改信息 end ----------
				n++;
				// --------------修改值------------------
				inputV.add(service.unicode2Gbk(paramValue));
			}
			// ---------....主键字段是不需要修改的 end----------
		}
		// 插入的字段
		sql.append(comments);
		sql.append(" where 1=1  ");
		sql.append(query);
		boolean flag = false;
		inputV.addAll(queryV);
		flag = dao.runUpdate(sql.toString(), inputV.toArray(new String[] {}));
		return flag;
	}
	
	/**
	 * 删除
	 */
	public boolean delData(String keys) throws Exception {
		int[] result = null;
		List<String> sqlList = new ArrayList<String>();
		keys = StringUtils.replace(keys, ",", "','");
		keys = "'" + keys + "'";
		String sql = "delete from xsxxb  ";// 删除学生数据
		sql += " where xh in(" + keys + ")";
		sqlList.add(sql);

		sql = "delete from  xsfzxxb ";// 删除学生辅导信息
		sql += " where xh in(" + keys + ")";
		sqlList.add(sql);

		sql = "delete from  xsmmb ";// 删除学生密码
		sql += " where xh in(" + keys + ")";
		sqlList.add(sql);

		sql = "delete from  xszpb ";// 删除学生相片
		sql += " where xh in(" + keys + ")";
		sqlList.add(sql);

		String[] sqls = new String[sqlList.size()];
		for (int i = 0; i < sqlList.size(); i++) {
			sqls[i] = sqlList.get(i);
		}
		result = dao.runBatch(sqls);
		return dao.checkBatch(result);
	}
	
	/**
	 * 根据学号查询信息
	 */
	public HashMap<String, String> getXsxxByXhForUpdate(String xh) {
		StringBuffer sb = new StringBuffer();
		sb.append("select ");
		sb
				.append("a.sfxyqgzx,a.zd6,a.xgyx,a.ah,a.bjdm,a.bjyjl,a.bxlx,a.bxxs,a.byny,a.bysj,a.byxx,a.byzffztdm,a.byzh,a.bz,a.ccqj,a.csd,a.csds,a.csdshi,a.csdxian,a.csrq,a.cym,a.dah,a.dasfyl,a.daylyy,a.dqszj,a.dxhwp,a.dybj,a.dzyx,a.fdyxm,a.fxzy,a.fxzyfx,a.gj,a.gkcj,a.grjl,a.gzbx,a.hkshen,a.hkshi,a.hkszd,a.hkxian,a.hkxz,a.jg,a.jgs,a.jgshi,a.jgx,a.jkzk,a.jlcfjl,a.jljn,a.jrgcdsj,a.jrgqtsj,a.jrzzmmrq,a.jsjdj,a.jtcygc,a.jtdh,a.jtdz,a.jtdzs,a.jtdzx,a.jtjjqk,a.jtqkjj,a.jtyb,a.jypx,a.kh,a.ksh,a.kslb,a.lxdh,a.lxdz,a.mz,a.nfby,a.pycc,a.pyfs,a.pyfx,a.qqhm,a.qsdh,a.rxfs,a.rxnj,a.rxqdw,a.rxqdwdh,a.rxqdwdz,a.rxqdwyb,a.rxqwhcd,a.rxrq,a.rzrq,a.sfbys,a.sfcj,a.sfdk,a.sfdl,a.sfgat,a.sfhq,a.sfjh,a.sfsf,a.sfssmz,a.sfyby,a.sfzc,a.sfzd,a.sfzfx,a.sfzh,a.sfzsb,a.sfzx,a.sfzz,a.sg,a.shbj,a.shgxdwdh1,a.shgxdwdh2,a.shgxgx1,a.shgxgx2,a.shgxgzdw1,a.shgxgzdw2,a.shgxsjhm1,a.shgxsjhm2,a.shgxxm1,a.shgxxm2,a.shgxzw1,a.shgxzw2,a.shzw,a.sjhm,a.ssbh,a.ssch,a.ssld,a.ssyq,a.sybz1,a.sybz2,a.sybz3,a.syd,a.syds,a.sydshi,a.sydx,a.tc,a.thbs,a.tz,a.whcd,a.wydj,a.xb,a.xh,a.xjh,a.xjlb,a.xjlbdm,a.xjztm,a.xldm,a.xm,a.xmpy,a.xmsj,a.xslb,a.xslx,a.xsqrxxbz,a.xszp,a.xw,a.xwzsbh,a.xwzsxlh,a.xwzsxxdz,a.xx,a.xxfx,a.xxszd,a.xxxs,a.xz,a.xzxm,a.yhdm,a.yhkh,a.ykth,a.ylbxh,a.yxdm,a.yzbm,a.zcsxhm,a.zd1,a.zd2,a.zd3,a.zd4,a.zd5,a.zgzs,a.zjdm,a.zkzh,a.zsbh,a.zsjj,a.zsjzrq,a.zslb,a.zsxlh,a.zw,a.zxwyyzdm,a.zyfx,a.zyjb,a.zylb,a.tbsj,a.bxxz,a.sftb,a.sfyqrzs,a.qtyy,a.sfsfs,a.zzmm");
		sb
				.append(",b.lxdh1,b.lxdh2,b.jtcy1_xm,b.jtcy1_gx,b.jtcy1_gzdz,b.jtcy1_zw,b.jtcy1_lxdh1,b.jtcy1_lxdh2,b.jtcy2_xm,b.jtcy2_gx,b.jtcy2_gzdz,b.jtcy2_zw,b.jtcy2_lxdh1,b.jtcy2_lxdh2,b.jtcy3_xm,b.jtcy3_gx,b.jtcy3_gzdz,b.jtcy3_zw,b.jtcy3_lxdh1,b.jtcy3_lxdh2,b.email,b.jtszd");

		sb.append(",c.xymc xymc,c.xymc xy,c.zymc,c.bjmc,c.xydm,c.zydm,c.nj");
		sb.append(" from xsxxb a ");
		sb.append(" left join xsfzxxb b ");
		sb.append(" on a.xh = b.xh ");
		sb.append(" left join (select a.nj, ");
        sb.append(" case when a.tgxydm is null then b.bmdm else a.tgxydm end xydm, ");
        sb.append(" case when a.tgxydm is null then b.bmmc else a.tgxymc end xymc, ");
        sb.append(" b.zydm, b.zymc, a.bjdm, a.bjmc ");
        sb.append(" from (select a.*,(select bmmc from zxbz_xxbmdm where bmdm=a.tgxydm) tgxymc ");
        sb.append(" from bks_bjdm a ");
        sb.append(" where exists (select bjdm from ( ");									
        sb.append(" select bjdm, sfzx, sfyby from xsxxb) b ");												
        sb.append(" where (sfzx='不在校' or sfzx is null) and a.bjdm = b.bjdm)) a, ");
        sb.append(" (select a.zydm, a.zymc, b.bmdm, b.bmmc ");
        sb.append(" from bks_zydm a, zxbz_xxbmdm b where a.bmdm = b.bmdm) b ");
        sb.append(" where a.zydm = b.zydm) c ");
		sb.append(" on a.bjdm=c.bjdm ");
		sb.append(" where a.xh=? ");
		return dao.getMapNotOut(sb.toString(), new String[] { xh });
	}
	
	/**
	 * 查询所有校友管理信息（不分页）
	 */
	public List<HashMap<String, String>> getAllList(XyglForm model,
			User user) throws Exception {
		// ====================过滤条件===================================

		// 高级查询条件
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		// 权限过滤
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		// 高级查询输入值
		String[] inputV = SearchService.getTjInput(model.getSearchModel());

		// query += searchTjByUser;
		// ====================过滤条件 end================================
		// ====================SQL拼装================================
		StringBuilder sb = new StringBuilder();
		sb
		.append(" select * from (select a.xh,a.sfzx, ")
		.append(" a.xm, ")
		.append(" decode(a.xb, '1', '男', '2', '女', a.xb) xb, ")
		.append(" a.nj, ")
		.append(" a.xydm, ") // 校区
		.append(" a.zydm, ")
		.append(" a.bjdm, ")		
		.append(" a.xymc, ")
		.append(" a.zymc, ")
		.append(" a.bjmc, ")
		.append(" a.jgmc, ")
		.append(" a.qqhm, ")
		.append(" b.pyccmc, ")
		.append(" a.SHGXGX2, ")
		.append(" a.SHGXGZDW2, ")
		.append(" a.SHGXZW2, ")
		.append(" a.sjhm, ")
		.append(" a.RXQDW, ")
		.append(" a.GZBX ")
		.append(" from view_xsxxb a ")
		.append(" left join XG_XSXX_PYCCDMB b ")
		.append(" on a.pycc = b.pyccdm ")
		.append(" ) a where 1=1 ");
		
		sb.append("  and sfzx='不在校'  ");
		
		sb.append(searchTjByUser);
		sb.append(" ");
		sb.append(searchTj);
		sb.append(" order by nj,xydm,zydm,bjdm,xh");

		return dao.getListNotOut(sb.toString(), inputV);
	}
		
	/**
	 * 通过学号查询是否存在（全部）
	 * @param xh
	 * @return
	 */
	public String chkStuIsExistsXYGL(String xh) {
		DAO dao = DAO.getInstance();
		return dao.getOneRs("select count(xh) cnt from xsxxb where xh=?", new String[]{xh}, "cnt");
	}
	
	
	public boolean saveInfo(HashMap<String, String> valueMap) throws Exception {
		CommService service = new CommService();
		// 保存SQL
		StringBuilder sql = new StringBuilder();
		// 字段名
		StringBuilder comments = new StringBuilder();
		// 字段值
		StringBuilder commentsValue = new StringBuilder();
		// 值
		List<String> inputV = new ArrayList<String>();
		sql.append(" insert into xsxxb");
		sql.append(" (sfzx, ");

		commentsValue.append(" values('不在校', ");
		int n = 0;
		for (Map.Entry<String, String> entry : valueMap.entrySet()) {
			String paramName = entry.getKey();
			String paramValue = entry.getValue();
			if (n != 0) {
				comments.append(",");
				commentsValue.append(",");
			}
			comments.append(paramName);
			commentsValue.append("?");
			inputV.add(service.unicode2Gbk(paramValue));
			n++;
		}
		commentsValue.append(" ) ");
		// 插入的字段
		sql.append(comments);
		sql.append(") ");
		// 插入值
		sql.append(commentsValue);
		boolean flag = dao.runUpdate(sql.toString(), inputV
				.toArray(new String[] {}));
		return flag;
	}
	
	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		// TODO 自动生成方法存根
		super.setTableName("");
		super.setKey("");
	}
	
}
