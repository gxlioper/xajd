/**
 * @部门:学工产品事业部
 * @日期：2014-2-17 上午09:43:56 
 */
package com.zfsoft.xgxt.xsxx.fbgl.bbgl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.extend.SuperDAOImplExtend;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.xsxx.fbgl.generate.FbCheck;
import com.zfsoft.xgxt.xsxx.fbgl.xsxx.FbglXsxxDao;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 编班管理
 * @类功能描述: TODO(这里用一句话描述这个类的作用)
 * @作者： 张昌路[工号:982]
 * @时间： 2014-2-17 上午09:43:56
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class FbglBbglDao extends SuperDAOImplExtend<FbglBbglForm> {
	private FbCheck fbCheck = new FbCheck();

	@Override
	public List<HashMap<String, String>> getPageList(FbglBbglForm t)
			throws Exception {
		return null;
	}

	/**
	 * 
	 * @描述: 批量保存
	 * @作者：张昌路[工号：982]
	 * @日期：2014-5-8 下午04:49:09
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param sql
	 * @param params
	 * @return
	 * @throws SQLException boolean 返回类型
	 */
	public boolean batchInsert(List<String[]> params) throws SQLException {
		StringBuffer sb = new StringBuffer();
		sb.append("insert into ");
		sb.append(this.getTableName());
		sb.append("(pzgzid,bjbh,bjdm,bjmc,bjrssx,nj,bmdm,pycc,xz,zydm)");
		sb.append(" values(?,?,?,?,?,?,?,?,?,?)");
		return dao.runBatch(sb.toString(), params).length == params.size();
	}

	/**
	 * 
	 * @描述: 检测编班数据
	 * @作者：张昌路[工号：982]
	 * @日期：2014-4-11 下午05:04:14
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param fbf
	 * @return String 返回类型 提示信息key
	 */
	public String checkBb(FbglBbglForm fbf) {
		if (!fbCheck.checkUniqeKey(getTableName(), "bjdm", fbf.getBjdm())) {
			System.out.println(fbf.getBjdm());
			return MessageKey.FBGL_CHECK_BJDM_UNIQUE;

		} else if (!fbCheck.checkTableColumLength(getTableName(), "bjmc", fbf
				.getBjmc())) {
			return MessageKey.FBGL_CHECK_BJMC;
		} else if (!fbCheck.checkTableColumLength(getTableName(), "bjdm", fbf
				.getBjdm())) {
			return MessageKey.FBGL_CHECK_BJDM;
		}
		return MessageKey.FBGL_CHECK_OK;
	}

	@Override
	public List<HashMap<String, String>> getPageList(FbglBbglForm t, User user)
			throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());

		StringBuffer sql = new StringBuffer();
		sql.append("select a.*,t1.pyccmc,a.nss||'/'||a.nvs xbsl from (");
		sql
				.append("select t.nj,t.xydm,t.xydm bmdm,t.zydm,t.pycc,t.xz,count(t.nj) rs,");
		sql
				.append("t.nj || '-' || t.xydm || '-' || t.zydm || '-' || t.PYCC || '-' || t.xz pk,");
		sql
				.append(" (select count(xb) from  XG_XSXX_FBGL_XSXX_LSB n where n.nj || '-' || n.xydm || '-' || n.zydm || '-' || n.PYCC || '-' || n.xz=t.nj || '-' || t.xydm || '-' || t.zydm || '-' || t.PYCC || '-' || t.xz");
		sql.append(" and xb='男' and n.tjzt='0') nss,");
		sql
				.append(" (select count(xb) from  XG_XSXX_FBGL_XSXX_LSB n where n.nj || '-' || n.xydm || '-' || n.zydm || '-' || n.PYCC || '-' || n.xz=t.nj || '-' || t.xydm || '-' || t.zydm || '-' || t.PYCC || '-' || t.xz");
		sql.append(" and xb='女' and n.tjzt='0') nvs,");
		sql.append(" (select zymc");
		sql.append(" from BKS_ZYDM a");
		sql.append(" where a.zydm = t.zydm");
		sql.append(" and rownum = 1) zymc,");
		sql.append(" (select bmmc");
		sql.append("  from ZXBZ_XXBMDM a");
		sql.append("  where a.bmdm = t.xydm");
		sql.append("  and rownum = 1) xy");
		sql.append("  from XG_XSXX_FBGL_XSXX_LSB t");
		sql.append("  where t.bjdm is null and t.tjzt='0'");
		sql.append(" group by t.nj, t.xydm, t.zydm, t.PYCC, t.xz");
		sql.append(" order by t.nj ) a ");
		sql.append(" left join XG_XSXX_PYCCDMB t1 on a.pycc=t1.pyccdm  ");
		sql.append(" where 1=1 ");
		sql
				.append(" and pk not in(select b.nj || '-' || b.bmdm || '-' || b.zydm || '-' || b.PYCC || '-' || b.xz pk from XG_XSXX_FBBXHGL_BJDM_LSB b where b.sfytj='0')");
		sql.append(" ");
		sql.append(searchTj);
		sql.append("   order by nj,xy,zydm desc");
		return getPageList(t, sql.toString(), inputV);
	}

	/**
	 * 
	 * @描述: 删除对应分班信息
	 * @作者：张昌路[工号：982]
	 * @日期：2014-3-6 上午09:47:43
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param pk
	 *            void 返回类型
	 */
	public void delAllFbxx(String pk) {
		StringBuffer sb = new StringBuffer();
		sb
				.append("delete xg_xsxx_fbbxhgl_bjdm_lsb b where b.nj||'-'||b.bmdm||'-'||b.zydm||'-'||b.PYCC||'-'||b.xz=?");
		try {
			dao.runDelete(sb.toString(), new String[] { pk });
		} catch (Exception e) {
			throw new RuntimeException("删除分班信息错误！" + e.getMessage());
		}
	}

	/**
	 * 
	 * @描述:删除所有分班信息
	 * @作者：张昌路[工号：982]
	 * @日期：2014-4-8 上午10:03:15
	 * @修改记录: 修改者名字-修改日期-修改内容 void 返回类型
	 */
	public int delFbxx() {
		StringBuffer sb = new StringBuffer();
		sb
				.append("update XG_XSXX_FBGL_XSXX_LSB b set bjdm='',bjmc='',fbgz='' where bjdm is not null");
		try {
			return dao.update(sb.toString(), new String[] {});
		} catch (Exception e) {
			throw new RuntimeException("删除分班信息失败！");
		}
	}

	/**
	 * 
	 * @描述: 已编班信息
	 * @作者：张昌路[工号：982]
	 * @日期：2014-3-3 下午03:10:14
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> 返回类型
	 */
	public List<HashMap<String, String>> getPageListForYbb(FbglBbglForm t,
			User user) throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());

		StringBuffer sql = new StringBuffer();
		sql
				.append("select a.*,t1.pyccmc,(select count(xydm) from XG_XSXX_FBGL_XSXX_LSB b where a.pk= b.nj||'-'||b.xydm||'-'||b.zydm||'-'||b.PYCC||'-'||b.xz) rs ");
		sql
				.append(",(select count(xydm) from XG_XSXX_FBGL_XSXX_LSB b where a.BJDM =b.bjdm) xss ");
		sql.append(" from VIEW_FBGL_YBBXX a ");
		sql.append(" left join XG_XSXX_PYCCDMB t1 on a.pycc=t1.pyccdm  ");
		sql.append(" where 1=1 ");
		sql.append(searchTj);
		sql.append("   order by nj,xy,zydm,bjdm desc");
		return getPageList(t, sql.toString(), inputV);
	}

	/**
	 * 
	 * @描述: 未编学号列表
	 * @作者：张昌路[工号：982]
	 * @日期：2014-3-11 上午10:14:07
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> 返回类型
	 */
	public List<HashMap<String, String>> getPageListForBxh(FbglBbglForm t,
			User user) throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		/*
		 * String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
		 * "xydm", "bjdm");
		 */
		String[] inputV = SearchService.getTjInput(t.getSearchModel());

		StringBuffer sql = new StringBuffer();
		sql
				.append("select a.*,t1.pyccmc,a.wfb||'/'||a.yfb fbqk,a.wbxh||'/'||a.ybxh bxhqk from (");
		// 分班情况
		sql
				.append("select t.*,(select count(*) from  XG_XSXX_FBGL_XSXX_LSB n where n.nj || '-' || n.xydm || '-' || n.zydm || '-' || n.PYCC || '-' || n.xz=t.nj || '-' || t.xydm || '-' || t.zydm || '-' || t.PYCC || '-' || t.xz");
		sql.append(" and bjdm is null and n.tjzt='0') wfb,");
		sql
				.append(" (select count(*) from  XG_XSXX_FBGL_XSXX_LSB n where n.nj || '-' || n.xydm || '-' || n.zydm || '-' || n.PYCC || '-' || n.xz=t.nj || '-' || t.xydm || '-' || t.zydm || '-' || t.PYCC || '-' || t.xz");
		sql.append(" and bjdm is not null and n.tjzt='0') yfb");

		// 编学号情况
		sql
				.append(",(select count(*) from  XG_XSXX_FBGL_XSXX_LSB n where n.nj || '-' || n.xydm || '-' || n.zydm || '-' || n.PYCC || '-' || n.xz=t.nj || '-' || t.xydm || '-' || t.zydm || '-' || t.PYCC || '-' || t.xz");
		sql.append(" and xh is not null and n.tjzt='0') ybxh,");
		sql
				.append(" (select count(*) from  XG_XSXX_FBGL_XSXX_LSB n where n.nj || '-' || n.xydm || '-' || n.zydm || '-' || n.PYCC || '-' || n.xz=t.nj || '-' || t.xydm || '-' || t.zydm || '-' || t.PYCC || '-' || t.xz");
		sql.append(" and xh is null and n.tjzt='0') wbxh");
		sql.append(" from VIEW_FBGL_BBGL t) a ");
		sql.append(" left join XG_XSXX_PYCCDMB t1 on a.pycc=t1.pyccdm  ");
		sql.append(" where 1=1 ");
		// sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}

	/**
	 * 
	 * @描述: 已编学号
	 * @作者：张昌路[工号：982]
	 * @日期：2014-3-11 上午10:14:33
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> 返回类型
	 */
	public List<HashMap<String, String>> getPageListForYbxh(FbglBbglForm t,
			User user) throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(t.getSearchModel());

		StringBuffer sql = new StringBuffer();
		sql.append("select * from (");
		sql
				.append("select t.*,t1.pyccmc,t0.pzgzmc bxhgzmc,t1.zymc zymc1,t2.bmmc xy1,");
		sql.append("   (select d.qxmc from dmk_qx d where d.qxdm = substr(t.SYD, 0, 4) || '00' and t.SYD <> substr(t.SYD, 0, 2) || '0000') ||");
		sql.append(" (select e.qxmc from dmk_qx e where e.qxdm = t.SYD and t.SYD <> substr(t.SYD, 0, 2) || '0000'");
        sql.append("  and t.SYD <> substr(t.SYD, 0, 4) || '00') sydmc,");
		// 编学号情况
		sql.append(" (case");
		sql.append(" when t.xh is not null then");
		sql.append(" '0'");
		sql.append(" else");
		sql.append(" '1'");
		sql.append(" end) xhqk,");
		sql.append(" (case");
		sql.append(" when t.xh is not null then");
		sql.append(" '未编学号'");
		sql.append(" else");
		sql.append(" '已编学号'");
		sql.append(" end) xhqkmc,");
		sql.append("t.nj||'" + FbglXsxxDao._NJ_KSH_FGF
				+ "'||t.ksh pk from xg_xsxx_fbgl_xsxx_lsb t");
		sql.append(" left join XG_XSXX_PYCCDMB t1 on t.pycc=t1.pyccdm  ");
		sql.append(" left join xg_xsxx_fbbxhgl_tjgzpzb t0");
		sql.append(" on t.bxhgz= t0.pzgzid");
		sql.append(" left join BKS_ZYDM t1 on t.zydm=t1.zydm");
		sql.append(" left join ZXBZ_XXBMDM t2 on t.xydm=t2.bmdm)a");
		// sql.append(" where t.xh is not null and t.bjdm is not null");
		sql.append(" where 1=1 and tjzt='0'");
		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		sql.append("order by a.xhqk desc,a.xydm,a.zydm,a.bjdm ");
		return getPageList(t, sql.toString(), inputV);
	}

	/**
	 * 
	 * @描述: 分班信息
	 * @作者：张昌路[工号：982]
	 * @日期：2014-3-6 下午03:21:20
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getPageListForFb(FbglBbglForm t)
			throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuffer sql = new StringBuffer();
		sql
				.append("select a.*,t1.pyccmc,nss||'/'||nvs nvqk,wfb||'/'||yfb fbqk from (");
		sql.append("select t.*");
		// 男女情况
		sql
				.append(",(select count(xb) from  XG_XSXX_FBGL_XSXX_LSB n where n.nj || '-' || n.xydm || '-' || n.zydm || '-' || n.PYCC || '-' || n.xz=t.pk");
		sql.append(" and xb='男' and n.tjzt='0') nss,");
		sql
				.append(" (select count(xb) from  XG_XSXX_FBGL_XSXX_LSB n where n.nj || '-' || n.xydm || '-' || n.zydm || '-' || n.PYCC || '-' || n.xz=t.pk");
		sql.append(" and xb='女' and n.tjzt='0') nvs");
		// 分班情况
		sql
				.append(",(select count(*) from  XG_XSXX_FBGL_XSXX_LSB n where n.nj || '-' || n.xydm || '-' || n.zydm || '-' || n.PYCC || '-' || n.xz=t.pk");
		sql.append(" and bjdm is null and n.tjzt='0') wfb,");
		sql
				.append(" (select count(*) from  XG_XSXX_FBGL_XSXX_LSB n where n.nj || '-' || n.xydm || '-' || n.zydm || '-' || n.PYCC || '-' || n.xz=t.pk");
		sql.append(" and bjdm is not null and n.tjzt='0' ) yfb");
		sql.append(" from VIEW_FBGL_BBGL t ) a");
		sql.append(" left join XG_XSXX_PYCCDMB t1 on a.pycc=t1.pyccdm  ");
		sql.append(" where 1=1 ");
		sql.append(" ");
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}

	/**
	 * 
	 * @描述:获取分班信息
	 * @作者：张昌路[工号：982]
	 * @日期：2014-4-3 下午03:07:42
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return List<HashMap<String,String>> 返回类型
	 */
	public List<HashMap<String, String>> getFbList() {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from VIEW_FBGL_BBGL");
		return dao.getListNotOut(sql.toString(), new String[] {});
	}

	/**
	 * 
	 * @描述: 已分班信息
	 * @作者：张昌路[工号：982]
	 * @日期：2014-3-7 下午04:26:28
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> 返回类型
	 */
	public List<HashMap<String, String>> getPageListForYfb(FbglBbglForm t,
			User user) throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		/*
		 * String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
		 * "xydm", "bjdm");
		 */
		String[] inputV = SearchService.getTjInput(t.getSearchModel());

		StringBuffer sql = new StringBuffer();
		sql.append("select * from(");
		sql
				.append("select t.XH,t.BJMC,t.BJDM,t.ZYDM,t.tjzt,t.XYDM bmdm,t.BZ,t.XM,t.XMPY,t.NJ,t.SYD,t.CSRQ,t.SFZH,t.XB,t.MZ,t.ZZMM,t.LXDH,t.DZYX,t.CYM,t.SG,t.TZ,t.TC,t.KSLB,t.RXFS,t.PYFS,t.PYCC,t.XJLB,t.XSZP,t.XJZTM,t.XZ,t.WHCD,t.RXQDW,t.JTDH,t.JRGQTSJ,t.JRGCDSJ,t.JTCYGC,t.JLCFJL,t.JKZK,t.JTDZ,t.JTYB,t.JG,t.XX,t.AH,t.SFDK,t.SHGXXM1,t.SHGXGX1,t.SHGXGZDW1,t.SHGXZW1,t.SHGXDWDH1,t.SHGXSJHM1,t.SHGXXM2,t.SHGXGX2,t.SHGXGZDW2,t.SHGXZW2,t.SHGXDWDH2,t.SHGXSJHM2,t.JTQKJJ,t.JTJJQK,t.SJHM,t.BYXX,t.KH,t.RXRQ,t.FDYXM,t.GKCJ,t.QQHM,t.HKXZ,t.ZYJB,t.HKSZD,t.SSYQ,t.SSLD,t.JTDZS,t.JTDZX,t.SFZSB,t.SFZFX,t.ZJDM,t.SFYBY,t.BYNY,t.SFZX,t.ZW,t.THBS,t.DYBJ,t.SHBJ,t.XWZSXLH,t.XWZSBH,t.XW,t.XZXM,t.ZSXLH,t.ZSBH,t.BJYJL,t.CSD,t.ZSJJ,t.XXXS,t.BXLX,t.BXXS,t.FXZYFX,t.FXZY,t.ZYLB,t.DQSZJ,t.PYFX,t.ZYFX,t.XXSZD,t.KSH,t.XXFX,t.ZSLB,t.GJ,t.SFJH,t.CCQJ,t.BYZFFZTDM,t.XWZSXXDZ,t.JGS,t.JGSHI,t.JGX,t.SSBH,t.RXNJ,t.NFBY,t.SFZC,t.DASFYL,t.DAYLYY,t.YXDM,t.SFZZ,t.SFSF,t.SFDL,t.DXHWP,t.BYSJ,t.ZXWYYZDM,t.WYDJ,t.JSJDJ,t.LXDZ,t.YZBM,t.SHZW,t.JYPX,t.XMSJ,t.ZGZS,t.JLJN,t.SYBZ1,t.SYBZ2,t.SYBZ3,t.XLDM,t.ZKZH,t.GRJL,t.SFCJ,t.SSCH,t.RZRQ,t.ZSJZRQ,t.QSDH,t.YKTH,t.YHKH,t.XSLB,t.XSLX,t.SFBYS,t.YHDM,t.HKSHEN,t.HKSHI,t.HKXIAN,t.ZCSXHM,t.RXQWHCD,t.XSQRXXBZ,t.DAH,t.YLBXH,t.RXQDWDZ,t.RXQDWYB,t.RXQDWDH,t.GZBX,t.SFGAT,t.SFSSMZ,t.SFZD,t.SYDS,t.SYDSHI,t.SYDX,t.BYZH,t.XJH,t.JRZZMMRQ,t.SFHQ,t.CSDS,t.CSDSHI,t.CSDXIAN,t.ZD1,t.ZD2,t.ZD3,t.ZD4,t.ZD5,t.XJLBDM,t.BXHGZ,t.FBGZ,t.TDCJ,t.LSH,t.PZGZID,t1.pyccmc,t0.pzgzmc fbgzmc,t0.gzdm gzlx,");
		 sql.append("(select c.qxmc from dmk_qx c where c.qxdm = substr(t.jg, 0, 2) || '0000') ||");
			sql.append("   (select d.qxmc from dmk_qx d where d.qxdm = substr(t.SYD, 0, 4) || '00' and t.SYD <> substr(t.SYD, 0, 2) || '0000') ||");
			sql.append(" (select e.qxmc from dmk_qx e where e.qxdm = t.SYD and t.SYD <> substr(t.SYD, 0, 2) || '0000'");
	        sql.append("  and t.SYD <> substr(t.SYD, 0, 4) || '00') sydmc,");
		sql.append("(case");
		sql.append("	when t.bjdm is not null then");
		sql.append(" '1'");
		sql.append(" else");
		sql.append(" '0'");
		sql.append("	end) fbqk,");
		sql.append(" (case");
		sql.append(" when t.bjdm is not null then");
		sql.append(" '已编班'");
		sql.append(" else");
		sql.append(" '未编班'");
		sql.append(" end) fbqkmc,");
		sql.append(" (case");
		sql.append(" when t.bjdm is not null then");
		sql.append(" '是'");
		sql.append(" else");
		sql.append(" '否'");
		sql.append(" end) sfyfb,");
		sql.append(" (select zymc");
		sql.append(" from BKS_ZYDM a");
		sql.append(" where a.zydm = t.zydm");
		sql.append(" and rownum = 1) zymc,");
		sql.append(" (select bmmc");
		sql.append(" from ZXBZ_XXBMDM a");
		sql.append(" where a.bmdm = t.xydm");
		sql.append(" and rownum = 1) bmmc,");
		sql.append("t.nj||'" + FbglXsxxDao._NJ_KSH_FGF
				+ "'||t.ksh pk from XG_XSXX_FBGL_XSXX_LSB t");
		sql.append(" left join XG_XSXX_FBBXHGL_TJGZPZB t0");
		sql.append(" on t.fbgz = t0.pzgzid ");
		sql.append(" left join XG_XSXX_PYCCDMB t1 on t.pycc=t1.pyccdm  ");

		// sql.append(" where bjdm is not null");
		sql.append(" order by t.fbgz,t.xydm,t.zydm,t.bjdm ) where 1=1 and tjzt='0' ");
		// sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}

	/**
	 * 
	 * @描述: 获取编班信息
	 * @作者：张昌路[工号：982]
	 * @日期：2014-3-4 上午10:22:58
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getBbxx(FbglBbglForm t) {
		try {
			StringBuffer sql = new StringBuffer();
			sql.append("select t0.*, t1.*, t2.*,t3.pyccmc from VIEW_FBGL_BBGL t0 ");
			sql.append(" left join  ZXBZ_XXBMDM t1");
			sql.append(" on t0.xydm = t1.bmdm");
			sql.append(" left join bks_zydm t2 ");
			sql.append(" on t1.bmdm = t2.zydm left join xg_xsxx_pyccdmb t3 on t0.pycc=t3.pyccdm where 1=1 and t0.pk not in(select pk from VIEW_FBGL_YBBXX)");
			// 如果不为空，获取具体pk的数据
			if (StringUtils.isNotNull(t.getPk())) {
				sql.append("  and t0.pk in(");
				String[] ids = t.getPk().split(",");
				int i = 0;
				for (String id : ids) {
					sql.append("'");
					sql.append(id);
					sql.append("'");
					if (i + 1 < ids.length) {
						sql.append(",");
					}
					i++;
				}
				sql.append(")");
			}
			
			return dao.getListNotOut(sql.toString(), new String[] {});
		} catch (Exception e) {
			throw new RuntimeException("获取编班列表错误");
		}
	}

	/**
	 * 
	 * @描述: 獲取編班具体信息
	 * @作者：张昌路[工号：982]
	 * @日期：2014-2-26 下午05:28:18
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param pk
	 * @return HashMap<String,String> 返回类型
	 */
	public HashMap<String, String> getBbxx(String pk) {
		StringBuffer sb = new StringBuffer();
		sb.append("select * from view_fbgl_bbgl where pk=?");
		return dao.getMapNotOut(sb.toString(), new String[] { pk });
	}

	/**
	 * 
	 * @描述: 获取调班信息
	 * @作者：张昌路[工号：982]
	 * @日期：2014-3-4 上午10:23:45
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param pk
	 * @return HashMap<String,String> 返回类型
	 * @throws
	 */
	public HashMap<String, String> getTbxx(String pk) {
		StringBuffer sb = new StringBuffer();
		sb
				.append("select a.*,t1.pyccmc,(select count(xydm) from XG_XSXX_FBGL_XSXX_LSB b where a.pk= b.nj||'-'||b.xydm||'-'||b.zydm||'-'||b.PYCC||'-'||b.xz) rs");
		sb.append("  from view_fbgl_bbgl a ");
		sb.append(" left join XG_XSXX_PYCCDMB t1 on a.pycc=t1.pyccdm  ");
		sb.append("  where pk=?");
		return dao.getMapNotOut(sb.toString(), new String[] { pk });
	}

	/**
	 * 
	 * @描述: 获取具体需调整班级
	 * @作者：张昌路[工号：982]
	 * @日期：2014-3-4 上午10:29:12
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param pk
	 * @return List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getTbJtBj(String pk) {
		StringBuffer sb = new StringBuffer();
		sb
				.append("select a.*,(select count(xydm) from XG_XSXX_FBGL_XSXX_LSB b where a.bjdm=b.bjdm) xss");
		sb
				.append(" from view_fbgl_ybbxx a where a.pk=? order by to_number(a.bjbh)");
		return dao.getListNotOut(sb.toString(), new String[] { pk });
	}

	/**
	 * 
	 * @描述: pk对应的班级个数
	 * @作者：张昌路[工号：982]
	 * @日期：2014-3-6 下午05:27:25
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param pk
	 * @return String 返回类型
	 */
	public List<HashMap<String, String>> getSelectZy(String[] pk) {
		StringBuffer sb = new StringBuffer();
		sb.append(" select * from (");
		sb.append("select distinct zydm from view_fbgl_bbgl where pk in(");
		for (String str : pk) {
			sb.append("'");
			sb.append(str);
			sb.append("',");
		}
		sb.append("'-1'))");
		return dao.getListNotOut(sb.toString(), new String[] {});
	}

	/**
	 * 
	 * @描述: 获pk下的所有班级
	 * @作者：张昌路[工号：982]
	 * @日期：2014-3-7 下午03:10:06
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zydm
	 * @return List<HashMap<String,String>> 返回类型
	 */
	public List<HashMap<String, String>> getPkBj(String pk) {
		StringBuffer sb = new StringBuffer();
		sb
				.append("select * from XG_XSXX_FBBXHGL_BJDM_LSB t where t.nj || '-' || t.bmdm || '-' || t.zydm || '-' || t.PYCC || '-' || t.xz=? order by to_number(t.bjbh) asc");
		return dao.getListNotOut(sb.toString(), new String[] { pk });
	}

	/**
	 * 
	 * @描述: 获取某年级专业的所有班级
	 * @作者：张昌路[工号：982]
	 * @日期：2014-3-7 下午03:10:06
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zydm
	 * @return List<HashMap<String,String>> 返回类型
	 */
	public List<HashMap<String, String>> getPkBj(String pk, String nj) {
		StringBuffer sb = new StringBuffer();
		sb
				.append("select * from XG_XSXX_FBBXHGL_BJDM_LSB t where  t.nj || '-' || t.bmdm || '-' || t.zydm || '-' || t.PYCC || '-' || t.xz=? and nj=? order by to_number(bjbh) asc");
		return dao.getListNotOut(sb.toString(), new String[] { pk, nj });
	}

	/**
	 * 
	 * @描述: 获取此专业的年级
	 * @作者：张昌路[工号：982]
	 * @日期：2014-4-4 上午11:48:14
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zydm
	 * @return List<HashMap<String,String>> 返回类型
	 */
	public List<HashMap<String, String>> getNj() {
		StringBuffer sb = new StringBuffer();
		sb.append("select  distinct nj  from XG_XSXX_FBBXHGL_BJDM_LSB");
		return dao.getListNotOut(sb.toString(), new String[] {});
	}

	/**
	 * 
	 * @描述: 获取当前班级列表
	 * @作者：张昌路[工号：982]
	 * @日期：2014-3-10 下午03:09:01
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return List<HashMap<String,String>> 返回类型
	 */
	public List<HashMap<String, String>> getDqBjList(FbglBbglForm myForm) {
		StringBuffer sb = new StringBuffer();
		sb.append("select bjdm,bjmc from XG_XSXX_FBBXHGL_BJDM_LSB where zydm=?");
		return dao.getListNotOut(sb.toString(), new String[] {myForm.getZydm()});
	}

	/**
	 * 
	 * @描述: 获取分班信息条数
	 * @作者：张昌路[工号：982]
	 * @日期：2014-3-6 下午05:41:45
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param sfyfb
	 *            true 获取已分班条数，false获取未分班条数
	 * @param pk
	 *            选中的主键key数组
	 * @return String 返回类型
	 */
	public String getFbxx(boolean sfyfb, String[] pk) {
		StringBuffer sb = new StringBuffer();
		// 原专业
		/*
		 * sb.append("select b.*from (");
		 * sb.append("select zydm from view_fbgl_bbgl where pk in("); for
		 * (String str : pk) { sb.append("'"); sb.append(str); sb.append("',");
		 * } sb.append("'-1')group by zydm)a"); sb.append(
		 * " left join XG_XSXX_FBGL_XSXX_LSB b on a.zydm=b.zydm where b.bjdm");
		 */
		sb.append("select * from (");
		sb
				.append("select t.*,t.nj || '-' || t.xydm || '-' || t.zydm || '-' || t.PYCC || '-' || t.xz pk");
		sb.append(" from XG_XSXX_FBGL_XSXX_LSB t)");
		sb.append(" where pk in (");
		for (String str : pk) {
			sb.append("'");
			sb.append(str);
			sb.append("',");
		}
		sb.append("'-1')");
		sb.append(" and bjdm ");
		sb.append(sfyfb ? " is not null" : " is null");
		List<HashMap<String, String>> list = dao.getListNotOut(sb.toString(),
				new String[] {});
		return null == list || list.size() <= 0 ? "0" : String.valueOf(list
				.size());
	}

	/**
	 * 
	 * @描述: 根据规则获取编班信息
	 * @作者：张昌路[工号：982]
	 * @日期：2014-4-1 上午10:00:51
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param pzgzid
	 * @return List<HashMap<String,String>> 返回类型
	 */
	public List<HashMap<String, String>> getBbxxForPzgz(String pzgzid) {
		StringBuffer sb = new StringBuffer();
		sb.append("select * from XG_XSXX_FBBXHGL_BJDM_LSB where pzgzid=?");
		return dao.getListNotOut(sb.toString(), new String[] { pzgzid });
	}

	@Override
	protected void setTableInfo() {
		this.setKey("bjdm");
		this.setTableName("xg_xsxx_fbbxhgl_bjdm_lsb");
		this.setClass(FbglBbglForm.class);
	}
}
