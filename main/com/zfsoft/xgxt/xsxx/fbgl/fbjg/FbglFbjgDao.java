/**
 * @部门:学工产品事业部
 * @日期：2014-3-25 上午09:33:48 
 */
package com.zfsoft.xgxt.xsxx.fbgl.fbjg;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.xsxx.fbgl.xsxx.FbglXsxxDao;
import com.zfsoft.xgxt.xsxx.fbgl.xsxx.FbglXsxxForm;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 分班管理
 * @类功能描述: TODO(这里用一句话描述这个类的作用)
 * @作者： 张昌路[工号:982]
 * @时间： 2014-3-25 上午09:33:48
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class FbglFbjgDao extends FbglXsxxDao {
	@Override
	public List<HashMap<String, String>> getPageList(FbglXsxxForm t, User user)
			throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
	//	String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
	//			"xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuffer sql = new StringBuffer();
		sql.append("select * from (");
		sql
				.append("select a.*,t1.pyccmc,nss||'/'||nvs nvqk,wfb||'/'||yfb fbqk,wbxh||'/'||ybxh bxhqk from (");
		sql.append("select t.* ");
		// 男女情况
		sql
				.append(",(select count(xb) from  XG_XSXX_FBGL_XSXX_LSB n where n.nj || '-' || n.xydm || '-' || n.zydm || '-' || n.PYCC || '-' || n.xz=t.pk");
		sql.append(" and xb='男' and tjzt='0') nss,");
		sql
				.append(" (select count(xb) from  XG_XSXX_FBGL_XSXX_LSB n where n.nj || '-' || n.xydm || '-' || n.zydm || '-' || n.PYCC || '-' || n.xz=t.pk");
		sql.append(" and xb='女' and tjzt='0') nvs");
		// 分班情况
		sql
				.append(",(select count(*) from  XG_XSXX_FBGL_XSXX_LSB n where n.nj || '-' || n.xydm || '-' || n.zydm || '-' || n.PYCC || '-' || n.xz=t.pk");
		sql.append(" and bjdm is null and tjzt='0') wfb,");
		sql
				.append(" (select count(*) from  XG_XSXX_FBGL_XSXX_LSB n where n.nj || '-' || n.xydm || '-' || n.zydm || '-' || n.PYCC || '-' || n.xz=t.pk");
		sql.append(" and bjdm is not null and tjzt='0') yfb");
		// 编学号情况
		sql
				.append(",(select count(*) from  XG_XSXX_FBGL_XSXX_LSB n where n.nj || '-' || n.xydm || '-' || n.zydm || '-' || n.PYCC || '-' || n.xz=t.nj || '-' || t.xydm || '-' || t.zydm || '-' || t.PYCC || '-' || t.xz");
		sql.append(" and xh is not null and tjzt='0') ybxh,");
		sql
				.append(" (select count(*) from  XG_XSXX_FBGL_XSXX_LSB n where n.nj || '-' || n.xydm || '-' || n.zydm || '-' || n.PYCC || '-' || n.xz=t.nj || '-' || t.xydm || '-' || t.zydm || '-' || t.PYCC || '-' || t.xz");
		sql.append(" and xh is null and tjzt='0') wbxh,");
		sql
				.append(" (select count(*) from XG_XSXX_FBBXHGL_BJDM_LSB bj where bj.nj||'_'||bj.zydm||'_'||bj.pycc||'_'||bj.xz=t.nj||'_'||t.zydm||'_'||t.pycc||'_'||t.xz) bjs");
		sql.append(" from VIEW_FBGL_BBGL t");
		sql.append(" )a ");
		sql.append(" left join XG_XSXX_PYCCDMB t1 on a.pycc=t1.pyccdm  ");
		sql.append(" ) where 1=1 ");
		//sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}

	/**
	 * 
	 * @描述: 获取已提交列表
	 * @作者：张昌路[工号：982]
	 * @日期：2014-3-31 上午09:13:06
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> 返回类型
	 */
	public List<HashMap<String, String>> getPageListYtj(FbglXsxxForm t,
			User user) throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		//String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
		//		"xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuffer sql = new StringBuffer();
		sql
				.append("select t.*,t1.pyccmc,nss||'/'||nvs nvqk from view_fbgl_xsxx_bak t ");
		sql.append(" left join XG_XSXX_PYCCDMB t1 on t.pycc=t1.pyccdm  ");
		sql.append(" where 1=1 ");
		//sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}

	/**
	 * 
	 * @描述: 已提交数据量
	 * @作者：张昌路[工号：982]
	 * @日期：2014-4-14 下午05:02:22
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param nj
	 * @return int 返回类型
	 */
	public int getWtj(String nj) throws SQLException {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from ");
		sql.append("XG_XSXX_FBGL_XSXX_LSB");
		sql.append(" where nj=?");
		List<HashMap<String, String>> list = dao.getListNotOut(sql.toString(),
				new String[] { nj });
		return null == list ? 0 : list.size();
	}

	/**
	 * 
	 * @描述: 未提交数量
	 * @作者：张昌路[工号：982]
	 * @日期：2014-4-14 下午05:02:28
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param nj
	 * @return
	 * @throws SQLException int 返回类型
	 */
	public int getYtj(String nj) throws SQLException {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from ");
		sql.append("XG_XSXX_FBGL_XSXX_LSB_BAK");
		sql.append(" where nj=?");
		List<HashMap<String, String>> list = dao.getListNotOut(sql.toString(),
				new String[] { nj });
		return null == list ? 0 : list.size();
	}

	/**
	 * 
	 * @描述: 获取年级list
	 * @作者：张昌路[工号：982]
	 * @日期：2014-3-31 上午09:13:32
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> 返回类型
	 */
	public List<HashMap<String, String>> getNjList() throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("select nj from XG_XSXX_FBGL_XSXX_LSB group by nj");
		return dao.getListNotOut(sql.toString(), new String[] {});
	}

	/**
	 * 
	 * @描述: 获取已提交年级列表
	 * @作者：张昌路[工号：982]
	 * @日期：2014-3-31 上午09:13:45
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> 返回类型
	 */
	public List<HashMap<String, String>> getYtjNjList() throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("select nj from Xg_Xsxx_Fbgl_Xsxx_Lsb_Bak group by nj");
		return dao.getListNotOut(sql.toString(), new String[] {});
	}

	/**
	 * 
	 * @描述: 根据年级获取临时表学生信息
	 * @作者：张昌路[工号：982]
	 * @日期：2014-3-26 下午02:00:52
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param nj
	 * @return List<HashMap<String,String>> 返回类型
	 */
	public List<HashMap<String, String>> getXsxxForNj(String nj) {
		StringBuffer sql = new StringBuffer();
		sql.append("select distinct a.xh,a.ksh,a.sfzx,a.tdcj,a.nj,a.xm,a.xb,b.xymc,b.zymc,a.bjdm,a.bjmc,a.xydm,a.zydm,");
		sql.append("a.syd,a.zzmm,a.sfzh,a.rxrq,a.jg,a.hkszd,a.sjhm,a.qqhm,");
		sql.append("a.pycc,a.xz,a.zjdm,a.mz,a.csrq from XG_XSXX_FBGL_XSXX_LSB a left join view_njxyzybj_all b on a.zydm=b.zydm ");
		sql.append(" where a.bjdm is not null and a.xh is not null and a.nj=? and a.tjzt='0'");
		return dao.getListNotOut(sql.toString(), new String[] { nj });
	}

	/**
	 * 
	 * @描述: 获取对应年级临时表插入的学生信息
	 * @作者：张昌路[工号：982]
	 * @日期：2014-3-31 上午09:14:09
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param nj
	 * @return List<HashMap<String,String>> 返回类型
	 */
	public List<HashMap<String, String>> getLsbXsxxInsertForNj(String nj) {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from XG_XSXX_FBGL_XSXX_LSB_BAK where nj=?");
		return dao.getListNotOut(sql.toString(), new String[] { nj });
	}

	/**
	 * 
	 * @描述: 根据年级获取临时表班级信息
	 * @作者：张昌路[工号：982]
	 * @日期：2014-3-26 下午02:00:16
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param nj
	 * @param sfytj
	 *            </br>标记查询 FbglBbglService._SFYTJ_YTJ 已提交
	 *            </br>FbglBbglService._SFYTJ_WTJ 未提交 </br> null 查询所有
	 * @return List<HashMap<String,String>> 返回类型
	 */
	public List<HashMap<String, String>> getBjxxForNj(String nj, String sfytj) {
		List<String> param = new ArrayList<String>();
		param.add(nj);
		StringBuffer sql = new StringBuffer();
		sql.append("select t.*");
		sql
				.append(",(select zymc from view_njxyzybj_all a where a.zydm = t.zydm and rownum = 1) zymc ");
		sql
				.append(",(select xymc from view_njxyzybj_all a where a.xydm = t.bmdm and rownum = 1) xymc ");
		sql
				.append(" from XG_XSXX_FBBXHGL_BJDM_LSB t where t.nj=? and bjdm is not null");
		if (StringUtils.isNotNull(sfytj)) {
			sql.append(" and t.sfytj=?");
			param.add(sfytj);
		}
		return dao.getListNotOut(sql.toString(), param.toArray(new String[] {}));
	}

	/**
	 * 
	 * @描述: 获取临时表某年级提交的班级代码
	 * @作者：张昌路[工号：982]
	 * @日期：2014-3-26 下午02:11:18
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @deprecated 废弃被getBjxxForNj方法替代，直接从班级临时表获取，
	 * @param nj
	 * @return List<HashMap<String,String>> 返回类型
	 */
	public List<HashMap<String, String>> getLsbInsertBjxxForNj(String nj) {
		StringBuffer sql = new StringBuffer();
		sql.append("select distinct bjdm from XG_XSXX_FBGL_XSXX_LSB_BAK");
		sql.append(" where nj=?");
		return dao.getListNotOut(sql.toString(), new String[] { nj });
	}

	/**
	 * 
	 * @描述: 获取班级信息
	 * @作者：张昌路[工号：982]
	 * @日期：2014-3-26 下午02:00:28
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param bjdm
	 * @return List<HashMap<String,String>> 返回类型
	 */
	public List<HashMap<String, String>> getBjxx(String bjdm) {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from bks_bjdm where bjdm=?");
		return dao.getListNotOut(sql.toString(), new String[] { bjdm });
	}

	/**
	 * 
	 * @描述: 提交班级信息
	 * @作者：张昌路[工号：982]
	 * @日期：2014-3-25 下午03:10:03
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param hm
	 * @return boolean 返回类型
	 */
	public boolean saveBjxx(HashMap<String, String> hm) {
		// insert into bks_bjdm (BJDM, JZGH, ZYDM, BMDM, BJMC, NJ, YWXTBH,
		// ZHGXSJ, BJJC, ZCRS)
		// values ('304181102', null, '1811', null, '临床医学（七年制）一系0402', 2004,
		// null, null, null, null);
		StringBuffer sql = new StringBuffer();
		sql
				.append("insert into bks_bjdm (BJDM, JZGH, ZYDM, BMDM, BJMC, NJ, YWXTBH, ZHGXSJ, BJJC, ZCRS) values (?,?,?,?,?,?,?,?,?,?)");
		try {
			return dao.insert(sql.toString(), new String[] { hm.get("bjdm"),
					null, hm.get("zydm"), null, hm.get("bjmc"), hm.get("nj"),
					null, null, null, null });
		} catch (Exception e) {
			throw new RuntimeException("插入班级信息失败!" + e.getMessage());
		}
	}

	/**
	 * 
	 * @描述: 删除班级
	 * @作者：张昌路[工号：982]
	 * @日期：2014-3-26 下午04:00:09
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param hm
	 * @return boolean 返回类型
	 */
	public boolean deleteBjxx(HashMap<String, String> hm) {
		StringBuffer sql = new StringBuffer();
		sql.append("delete bks_bjdm where bjdm=?");
		try {
			return dao.runDelete(sql.toString(),
					new String[] { hm.get("bjdm") }) == 1;
		} catch (Exception e) {
			throw new RuntimeException("删除班级信息失败!" + e.getMessage());
		}
	}

	/**
	 * 
	 * @描述: 删除学生密码
	 * @作者：张昌路[工号：982]
	 * @日期：2014-3-31 上午09:35:41
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return int 返回类型
	 */
	public int deleteXsmm(String xh) {
		StringBuffer sql = new StringBuffer();
		sql.append("delete xsmmb where xh=?");
		try {
			return dao.runDelete(sql.toString(), new String[] { xh });
		} catch (Exception e) {
			throw new RuntimeException("删除学生密码失败!" + e.getMessage());
		}
	}
}
