/**
 * @部门:学工产品事业部
 * @日期：2014-7-7 上午10:19:15 
 */
package com.zfsoft.xgxt.xsxx.bysxxgl.bysxx;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xgxt.DAO.DAO;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.sql.SQL_Util;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用)
 * @作者： 夏夏[工号:1104]
 * @时间： 2014-7-7 上午10:19:15
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class BysXxDao extends SuperDAOImpl<BysXxForm> {

	/*
	 * 描述: @see
	 * com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(BysXxForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	 * 描述: @see
	 * com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object,
	 * xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(BysXxForm t, User user)
			throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "b",
				"xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(t.getSearchModel());

		StringBuffer sql = new StringBuffer();
		sql.append("select a.* from ( ");
		sql
				.append("select a.*,b.XJLBDM,b.ZZMM,b.xz,b.mz,b.JTDZ,b.JGMC,b.sydmc syd,b.hkszdmc hkszd,b.xm,b.xb,b.nj,b.xydm,b.xymc,b.zydm,b.zymc,b.byny,b.csrq,b.sfzh,b.bjdm,b.bjmc,b.pycc,(select pyccmc from xg_xsxx_pyccdmb c where b.pycc=c.pyccdm) pyccmc from XG_BYSXX_BYSXXB a ");
		sql.append("left join view_xsxxb b on b.xh=a.xh left join xsxxb c on c.xh=a.xh where a.bynd in(select bynd from XG_BYSXX_XXXGCSSZB) ");
//		sql.append("left join view_xsxxb b on b.xh=a.xh left join xsxxb c on c.xh=a.xh  ");
		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(") a where 1=1 ");
		sql.append(searchTj);
		return this.getPageList(t, sql.toString(), inputV);
	}

	/**
	 * 
	 * @描述:获取学生信息
	 * @作者：夏夏[工号：1104]
	 * @日期：2014-7-7 下午02:01:10
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getXsxxList(BysXxForm model, User user)
			throws Exception {
		// 生成高级查询相关条件、条件值
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(model.getSearchModel());

		StringBuilder sql = new StringBuilder(
				"select a.xh,a.xm,a.nj,a.xz,a.xb,a.xymc,a.zymc,");
		sql.append("a.bjmc,a.xydm,a.zydm,a.bjdm from view_xsjbxx a where a.xh not in(select xh from XG_BYSXX_BYSXXB)");
		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);

		return getPageList(model, sql.toString(), inputV);
	}
	/**
	 * 
	 * @描述:保存毕业生信息
	 * @作者：夏夏[工号：1104]
	 * @日期：2014-8-28 上午09:37:13
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean save(List<String[]> params) throws Exception{
		boolean flag = false;
		if(params == null || params.size() == 0 || params.get(0)== null || params.get(0).length == 0){
			return false;
	    }
	    StringBuilder sql = new StringBuilder();
	    int len = params.get(0).length;
	    sql.append("insert into XG_BYSXX_BYSXXB values(");
        for(int i = 0;i <len;i++ ){
        	sql.append("?");
        	if(i != len-1){
        		sql.append(" ,");
        	}
        }
    	sql.append(" )");
		int[] result = dao.runBatch(sql.toString(), params);
		flag = dao.checkBatchResult(result);
		return flag;
		
	}
	/**
	 * 
	 * @描述:修改毕业生信息
	 * @作者：夏夏[工号：1104]
	 * @日期：2014-7-8 上午10:08:39
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param valueMap
	 * @return
	 * @throws Exception boolean 返回类型
	 * @throws
	 */
	public boolean updateBysInfo(HashMap<String, String> valueMap)
			throws Exception {
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
		sql.append(" update XG_BYSXX_BYSXXB set ");
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
					inputV.add(paramValue);
					n++;
					// --------------修改值------------------
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
	 * 
	 * @描述:通过学号获取毕业生信息
	 * @作者：夏夏[工号：1104]
	 * @日期：2014-7-8 上午10:36:46
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return HashMap<String,String> 返回类型
	 * @throws
	 */
	public HashMap<String, String> getBysXx(String xh) {
		StringBuffer sb = new StringBuffer();
		sb.append("select ");
		sb.append("d.*,a.ah,a.bjdm,a.bjyjl,a.bxlx,a.bxxs,a.byny,a.bysj,a.byxx,a.byzffztdm,a.byzh,a.bz,a.ccqj,a.csd,a.csds,a.csdshi,a.csdxian,a.csrq,a.cym,a.dah,a.dasfyl,a.daylyy,a.dqszj,a.dxhwp,a.dybj,a.dzyx,a.fdyxm,a.fxzy,a.fxzyfx,a.gj,a.gkcj,a.grjl,a.gzbx,a.hkshen,a.hkshi,a.hkszd,a.hkxian,a.hkxz,a.jg,a.jgs,a.jgshi,a.jgx,a.jlcfjl,a.jljn,a.jrgcdsj,a.jrgqtsj,a.jrzzmmrq,a.jsjdj,a.jtcygc,a.jtdh,a.jtdz,a.jtdzs,a.jtdzx,a.jtjjqk,a.jtqkjj,a.jtyb,a.jypx,a.kh,a.ksh,a.kslb,a.lxdh,a.lxdz,a.mz,a.nfby,a.nj,a.pycc,a.pyfs,a.pyfx,a.qqhm,a.qsdh,a.rxfs,a.rxnj,a.rxqdw,a.rxqdwdh,a.rxqdwdz,a.rxqdwyb,a.rxqwhcd,a.rxrq,a.rzrq,a.sfbys,a.sfcj,a.sfdk,a.sfdl,a.sfgat,a.sfhq,a.sfjh,a.sfsf,a.sfssmz,a.sfyby,a.sfzc,a.sfzd,a.sfzfx,a.sfzh,a.sfzsb,a.sfzx,a.sfzz,a.sg,a.shbj,a.shgxdwdh1,a.shgxdwdh2,a.shgxgx1,a.shgxgx2,a.shgxgzdw1,a.shgxgzdw2,a.shgxsjhm1,a.shgxsjhm2,a.shgxxm1,a.shgxxm2,a.shgxzw1,a.shgxzw2,a.shzw,a.sjhm,a.ssbh,a.ssch,a.ssld,a.ssyq,a.sybz1,a.sybz2,a.sybz3,a.syd,a.syds,a.sydshi,a.sydx,a.tc,a.thbs,a.tz,a.whcd,a.wydj,a.xh,a.xjh,a.xjlb,a.xjlbdm,a.xjztm,a.xldm,a.xm,a.xmpy,a.xmsj,a.xslb,a.xslx,a.xsqrxxbz,a.xszp,a.xw,a.xwzsbh,a.xwzsxlh,a.xwzsxxdz,a.xx,a.xxfx,a.xxszd,a.xxxs,a.xz,a.xzxm,a.yhdm,a.yhkh,a.ykth,a.ylbxh,a.yxdm,a.yzbm,a.zcsxhm,a.zd1,a.zd2,a.zd3,a.zd4,a.zd5,a.zgzs,a.zjdm,a.zkzh,a.zsbh,a.zsjj,a.zsjzrq,a.zslb,a.zsxlh,a.zw,a.zxwyyzdm,a.zyfx,a.zyjb,a.zylb,a.tbsj,a.bxxz,a.sftb,a.sfyqrzs,a.qtyy,a.sfsfs,a.zzmm");
		sb.append(",b.lxdh1,b.lxdh2,b.jtcy1_xm,b.jtcy1_gx,b.jtcy1_gzdz,b.jtcy1_zw,b.jtcy1_lxdh1,b.jtcy1_lxdh2,b.jtcy2_xm,b.jtcy2_gx,b.jtcy2_gzdz,b.jtcy2_zw,b.jtcy2_lxdh1,b.jtcy2_lxdh2,b.jtcy3_xm,b.jtcy3_gx,b.jtcy3_gzdz,b.jtcy3_zw,b.jtcy3_lxdh1,b.jtcy3_lxdh2,b.email,b.jtszd");
		// ========= 籍贯 < =========
		sb.append(" ,(select c.qxmc ");
		sb.append(" from dmk_qx c ");
		sb.append(" where c.qxdm = substr(a.jg, 0, 2) || '0000') || ");
		sb.append(" (select d.qxmc ");
		sb.append(" from dmk_qx d ");
		sb.append(" where d.qxdm = substr(a.jg, 0, 4) || '00' ");
		sb.append(" and a.jg <> substr(a.jg, 0, 2) || '0000') || ");
		sb.append(" (select e.qxmc ");
		sb.append(" from dmk_qx e ");
		sb.append(" where e.qxdm = a.jg ");
		sb.append(" and a.jg <> substr(a.jg, 0, 2) || '0000' ");
		sb.append(" and a.jg <> substr(a.jg, 0, 4) || '00') jgmc ");
		// ========= 籍贯 > =========
		// ========= 民族 < =========
		sb.append(",(select mzmc from mzdmb where a.mz = mzdm) mzmc ");
		// ========= 民族 > =========
		// ========= 性别 < =========
		sb.append(",(case a.xb when '1' then '男' when '2' then '女' else a.xb end) xb ");
		// ========= 性别 > =========
		// ========= 健康状况 < =========
		sb.append(",a.jkzk jkzkdm, (select mc from xg_xsxx_jkztb s where a.jkzk=s.dm) jkzk ");
		// ========= 健康状况 > =========
		sb.append(",c.xymc xymc,c.xymc xy,c.zymc,c.bjmc,c.xydm,c.zydm,z8.mc,");
		sb.append(" z1.xwmlmc,");
		sb.append(" z2.gdlxmc,");
		sb.append(" z3.zyxwlbmc,");
		sb.append(" z4.zyxwlymc,");
		sb.append(" z5.jsxymc,");
		sb.append(" z6.flfxmc,");
		sb.append(" z7.xxfsmc");
		sb.append(" from XG_BYSXX_BYSXXB d ");
		sb.append(" left join xsxxb a ");
		sb.append("  on a.xh=d.xh ");
		sb.append(" left join xsfzxxb b ");
		sb.append(" on d.xh = b.xh ");
		sb.append(" left join view_njxyzybj c ");
		sb.append("  on a.bjdm=c.bjdm ");
	    sb.append(" left join ZXBZ_XWML z1");
	    sb.append(" on d.xwml = z1.xwmldm");
	    sb.append(" left join ZXBZ_GDLX z2");
	    sb.append(" on d.gdlx = z2.gdlxdm");
	    sb.append(" left join ZXBZ_ZYXWLB z3");
	    sb.append("  on d.zyxwlb = z3.zyxwlbdm");
	    sb.append(" left join ZXBZ_ZYXWLY z4");
	    sb.append(" on d.zyxwly = z4.zyxwlydm");
	    sb.append(" left join ZXBZ_JSXY z5");
	    sb.append(" on d.jsxy = z5.jxxydm");
	    sb.append(" left join ZXBZ_FLFX z6");
	    sb.append(" on d.flfx = z6.flfxdm");
	    sb.append(" left join dmk_xxfs z7");
	    sb.append(" on d.xxfs = z7.xxfsdm");
	    sb.append(" left join dmk_xwb z8");
	    sb.append(" on d.xws = z8.dm");
		sb.append(" where d.xh=? ");
		return dao.getMapNotOut(sb.toString(), new String[] { xh });

	}
	/**
	 * 通过学号获取申请信息
	 */
	public HashMap<String, String> getSqXx(String xh) {
		StringBuffer sb = new StringBuffer();
		sb.append("select * from XG_BYSXX_XXXGSQB ");
		sb.append(" where xh=? order by xgsj desc");
		return dao.getMapNotOut(sb.toString(), new String[] { xh });
	}

	/**
	 * 
	 * @描述:获取照片
	 * @作者：夏夏[工号：1104]
	 * @日期：2014-7-8 下午02:19:33
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param myForm
	 * @param column
	 * @return Blob 返回类型
	 * @throws
	 */
	public Blob getZpMap(BysXxForm myForm, String column) {
		// TODO 自动生成方法存根
		String sql = "select * from xszpb where xh = ?";
		DAO dao = DAO.getInstance();

		return dao.getOneBlob(sql, new String[] { myForm.getXh() }, column);
	}
	
	@Override
	protected void setTableInfo() {
		this.setKey("xh");
		this.setTableName("XG_BYSXX_BYSXXB");
		this.setClass(BysXxForm.class);
	}
	
	/**
	 * 
	 * @描述: 批量插入
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-5-20 上午10:43:07
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @param bynd
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean insertSelect(BysXxForm model, User user, String bynd)
			throws Exception {
		
		// 生成高级查询相关条件、条件值
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t",
				"xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		
		StringBuffer sql = new StringBuffer();
		sql.append(" insert into ");
		sql.append("  XG_BYSXX_BYSXXB (xh,bynd) ");
		sql.append(" select t.xh,t.bynd from ");
		sql.append(" (select t.* from ");
		sql.append(" (select a.xh, '" + bynd
				+ "' as bynd,a.nj,a.xm,a.bjdm,a.xb,a.xydm,a.xz,a.zydm from view_xsjbxx a where a.xh not in( ");
		sql.append(" select xh from XG_BYSXX_BYSXXB)) t ");
		sql.append(" where 1 = 1 ");
		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		sql.append(" ) t "); 

		return dao.runUpdate(sql.toString(), inputV);
	}

}
