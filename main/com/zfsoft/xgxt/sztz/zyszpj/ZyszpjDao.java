/**
 * @部门:学工产品事业部
 * @日期：2013-6-6 下午04:59:29 
 */
package com.zfsoft.xgxt.sztz.zyszpj;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.base.util.UniqID;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 职业素质评价
 * @类功能描述: TODO(这里用一句话描述这个类的作用)
 * @作者： 张昌路 [工号：982]
 * @时间： 2013-6-6 下午04:59:29
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class ZyszpjDao extends SuperDAOImpl<ZyszpjForm> {
	DAO baseDao = DAO.getInstance();
	public final static String _INSERT_FAIL = "-1";// 插入失败
	public final static String _MY_SEQUENCE = "SEQUENCE_ZYSZ";

	public int getSequencesNextValue(String SequnencesName) throws SQLException {
		StringBuffer sql = new StringBuffer();
		sql.append("select ");
		sql.append(SequnencesName);
		sql.append(".nextval");
		sql.append(" from dual");
		return dao.getOneRsint(sql.toString());
	}

	/**
	 * @描述:获取类别名称
	 * @作者：张昌路 [工号：982]
	 * @日期：2013-6-18 下午02:53:41
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xmid
	 *            类别id
	 * @return String 返回类型
	 * @throws
	 */
	public String getXmlbMc(String xmid) {
		StringBuffer sql = new StringBuffer();
		sql.append("select mc from xg_zysz_xmlb where xmlbid=?");
		return dao.getOneRs(sql.toString(), new String[] { xmid }, "mc");
	}

	/**
	 * 
	 * @描述:获取职业素质下的所有项目类别
	 * @作者：张昌路 [工号：982]
	 * @日期：2013-6-13 下午06:02:27
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zyszid
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getXmlbForZysz(String zyszid)
			throws Exception {
		StringBuffer sql = new StringBuffer();
		sql
				.append("select a.xmlbid,b.mc from xg_zysz_zxm a,xg_zysz_xmlb b where a.zyszid=? and a.xmlbid=b.xmlbid group by a.xmlbid,b.mc");
		return dao.getList(sql.toString(), new String[] { zyszid },
				new String[] { "xmlbid", "mc" });
	}

	/**
	 * 
	 * @描述:获取职业素质下此项目类别的所有活动项目信息
	 * @作者：张昌路 [工号：982]
	 * @日期：2013-6-13 下午06:20:48
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zyszId
	 * @param xmlbId
	 * @return HashMap<String,String> 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getZxmDetailForXmlbAndZyszid(
			String zyszId, String xmlbId) {
		StringBuffer sql = new StringBuffer();
		sql
				.append("select b.xmlbid,b.mc,a.sj,a.dd,a.hdnr,a.cyjhjqk from xg_zysz_zxm a,xg_zysz_xmlb b where a.xmlbid=b.xmlbid and a.zyszid=? and b.xmlbid=?");
		return dao.getList(sql.toString(), new String[] { zyszId, xmlbId },
				new String[] { "xmlbid", "mc", "sj", "dd", "hdnr", "cyjhjqk" });
	}

	/*
	 * 描述: @see
	 * com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(ZyszpjForm t)
			throws Exception {
		StringBuffer sb = new StringBuffer();
		List<String> param = new ArrayList<String>();
		sb
				.append(" select a.*,b.xm,b.xymc,b.bjmc from (select a.zyszid,a.xh,a.txsj,(case when a.hprid is null then '尚未互评' else '已互评' end)hpzt,(case when a.sprid is null then '尚未师评' else '已师评' end)spzt,a.xn,a.xq from xg_zysz_zyszpj a where 1=1");
		if (!StringUtil.isNull(t.getXh())) {
			sb.append(" and a.xh='");
			sb.append(t.getXh());
			sb.append("'");
		}
		if (!StringUtil.isNull(t.getXm())) {
			sb.append(" and b.xm like '%");
			sb.append(t.getXm());
			sb.append("%'");
		}
		sb.append(")a left join view_xsxxb b on a.xh=b.xh");

		/*
		 * sb.append(" where 1=1"); if (!StringUtil.isNull(t.)) {
		 * sb.append(" and tbgxxslbdm ="); param.add(t.getTbgxxslbdm()); }if
		 * (!StringUtil.isNull(t.getTbgxxslbmc())) {
		 * sb.append(" and tbgxxslbmc like '%'||?||'%'");
		 * 
		 * param.add(t.getTbgxxslbmc()); }
		 */
		return this.getPageList(t, sb.toString(), param
				.toArray(new String[] {}));
	}

	/**
	 * 
	 * @描述: 获取项目类别列表
	 * @作者：张昌路[工号：982]
	 * @日期：2013-6-18 下午02:54:42
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getXmlbList() throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from xg_zysz_xmlb");
		return dao.getList(sql.toString(), new String[] {}, new String[] {
				"xmlbid", "mc" });
	}

	/**
	 * 
	 * @描述:删除职业素质信息
	 * @作者：张昌路 [工号：982]
	 * @日期：2013-6-7 下午07:16:11
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zyszId
	 * @return
	 * @throws Exception boolean 返回类型
	 * @throws
	 */
	public boolean deleteZysz(String zyszId) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("delete xg_zysz_zyszpj where zyszid=?");
		int i = baseDao.runDelete(sql.toString(), new String[] { zyszId });
		return (i == Statement.EXECUTE_FAILED) ? false : true;
	}

	/**
	 * 
	 * @描述:更新职业素质信息
	 * @作者：张昌路 [工号：982]
	 * @日期：2013-6-7 下午07:16:11
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zyszId
	 * @return
	 * @throws Exception boolean 返回类型
	 * @throws
	 */
	public boolean updateZysz(ZyszpjForm form) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql
				.append("update xg_zysz_zyszpj set XH=?,ZPXX=?,HPXX=?,HPRID=?,SPXX=?,SPRID=?,TXSJ=?,XN=?,XQ=?,PJDJ=? where zyszid=?");
		return baseDao.runUpdate(sql.toString(), new String[] { form.getXh(),
				form.getZpxx(), form.getHpxx(), form.getHprid(),
				form.getSpxx(), form.getSprid(), form.getTxsj(), form.getXn(),
				form.getXq(), form.getPjdj(), form.getZyszid() });
	}

	/**
	 * 
	 * @描述:TODO(删除对应素质活动信息)
	 * @作者：张昌路 [工号：982]
	 * @日期：2013-6-7 下午07:16:11
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zyszId
	 * @return
	 * @throws Exception boolean 返回类型
	 * @throws
	 */
	public boolean deleteZxm(String zyszId) throws Exception {
		StringBuffer zxm = new StringBuffer();
		zxm.append("delete XG_ZYSZ_ZXM where zyszid=?");
		int i = baseDao.runDelete(zxm.toString(), new String[] { zyszId });
		return (i == Statement.EXECUTE_FAILED) ? false : true;
	}

	/**
	 * 
	 * @描述:保存职业素质评价信息
	 * @作者：zcl [工号：982]
	 * @日期：2013-6-17 下午05:37:14
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param form
	 * @return
	 * @throws SQLException boolean 返回类型
	 * @throws
	 */
	public boolean saveZysz(ZyszpjForm form) throws SQLException {
		//form.setZyszid(String.valueOf(getSequencesNextValue(_MY_SEQUENCE)));
		form.setZyszid(UniqID.getInstance().getUniqIDHash());
		StringBuffer sql = new StringBuffer();
		sql
				.append("insert into xg_zysz_zyszpj(zyszid,XH,ZPXX,HPXX,HPRID,SPXX,SPRID,TXSJ,XN,XQ,PJDJ)values(?,?,?,?,?,?,?,?,?,?,?)");
		return baseDao.insert(sql.toString(), new String[] { form.getZyszid(),
				form.getXh(), form.getZpxx(), form.getHpxx(), form.getHprid(),
				form.getSpxx(), form.getSprid(), form.getTxsj(), form.getXn(),
				form.getXq(), form.getPjdj() });
	}

	/**
	 * 
	 * @描述:TODO(获得所属子项目)
	 * @作者：张昌路 [工号：982]
	 * @日期：2013-6-7 下午05:27:38
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zyszId
	 * @return List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getXmList(String zyszId) {
		StringBuffer sql = new StringBuffer();
		sql.append("select b.*,row_number() over(order by b.xmlbid asc) rowIndex from XG_ZYSZ_ZXM b where b.zyszid=?");
		return dao.getListNotOut(sql.toString(), new String[] { zyszId });
	}

	/**
	 * 
	 * @描述:添加子项目信息
	 * @作者：张昌路 [工号：982]
	 * @日期：2013-6-18 下午02:59:52
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param list
	 * @param zyszId
	 * @return
	 * @throws SQLException boolean 返回类型
	 * @throws
	 */
	public boolean addZxm(List<ZxmForm> list, String zyszId)
			throws SQLException {
		List<String[]> param = new ArrayList<String[]>();
		for (ZxmForm zf : list) {
			String[] p = new String[] { zf.getXmlbId(), zf.getSj(), zf.getDd(),
					zf.getHdnr(), zf.getCyjhjqk(), zyszId };
			param.add(p);
		}
		StringBuffer zxmSql = new StringBuffer();
		zxmSql
				.append("insert into XG_ZYSZ_ZXM(xmlbid,sj,dd,hdnr,cyjhjqk,zyszid)values(?,?,?,?,?,?)");
		return baseDao.checkBatchResult(baseDao.runBatch(zxmSql.toString(),
				param));
	}

	/**
	 * 
	 * @描述:获取当前学号，学年、学期的素质评价信息
	 * @作者：张昌路
	 * @日期：2013-6-17 下午01:54:46
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> isCanAddZysz(ZyszpjForm t)
			throws Exception {
		StringBuffer sql = new StringBuffer();
		sql
				.append("select * from xg_zysz_zyszpj t where t.xh=? and t.xn=? and t.xq=?");
		return this.getPageList(t, sql.toString(), new String[] { t.getXh(),
				t.getXn(), t.getXq() });
	}
	/**
	 * 
	 * @描述:根据学期代码获取名称 
	 * @作者：张昌路[工号：982]
	 * @日期：2013-6-24 下午02:18:48
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xqdm
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getXqmc(String xqdm){
		String sql="select xqmc from xqdzb where xqdm=?";
		return dao.getOneRs(sql, new String[]{xqdm}, "xqmc");
	}
	/*
	 * 描述: @see
	 * com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object,
	 * xgxt.form.User)
	 */
	@Override
	public List<HashMap<String, String>> getPageList(ZyszpjForm t, User user)
			throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(t.getSearchModel());

		StringBuffer sb = new StringBuffer();

		sb
				.append(" select * from (select a.*,b.xm,b.xydm,b.xymc,b.bjmc,b.bjdm,b.nj,b.zydm from (select a.zyszid,a.xh,a.txsj,(case when a.hprid is null then '尚未互评' else '已互评' end)hpzt,(case when a.sprid is null then '尚未师评' else '已师评' end)spzt,a.xn,a.xq,(select xqmc from xqdzb where xqdm=xq) xqmc from xg_zysz_zyszpj a where 1=1");
		sb.append(")a left join view_xsxxb b on a.xh=b.xh) a where 1=1");
		if (!t.getDqqx().equals(ZyszpjService._TX_QUERY)) {// 如果不是互评查询
			sb.append(searchTjByUser);
		} else {
			String bjdm = dao.getOneRs(
					"select b.bjdm from view_xsxxb b where b.xh=?",
					new String[] { t.getXh() }, "bjdm");
			sb.append(" and a.bjdm ='");
			sb.append(bjdm);
			sb.append("'");
			/*sb.append(" and a.xh <>");
			sb.append(t.getXh());*/
		}
		sb.append(" ");
		sb.append(searchTj);
		return this.getPageList(t, sb.toString(), inputV);
	}

	/**
	 * 
	 * @描述:获得用户名称
	 * @作者：张昌路[工号：982]
	 * @日期：2013-6-18 下午03:02:22
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param userId 用户id
	 * @return String 返回类型
	 * @throws
	 */
	public String getName(String userId) {
		StringBuffer sb = new StringBuffer();
		sb.append("select b.xm from view_xsxxb b where xh=?");
		return dao.getOneRs(sb.toString(), new String[] { userId }, "xm");
	}

	/**
	 * 
	 * @描述:获取老师姓名
	 * @作者：张昌路 [工号：982]
	 * @日期：2013-6-18 下午03:00:48
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param userId 老师id
	 * @return String 返回类型
	 * @throws
	 */
	public String getNameForTeac(String userId) {
		StringBuffer sb = new StringBuffer();
		sb.append("select b.xm from yhb b where yhm=?");
		return dao.getOneRs(sb.toString(), new String[] { userId }, "xm");
	}

	/**
	 * 
	 * @描述:根据学号获得班级
	 * @作者：张昌路 [工号：982]
	 * @日期：2013-6-14 下午06:25:42
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return String 返回类型
	 * @throws
	 */
	public String getBj(String xh) {
		StringBuffer sb = new StringBuffer();
		sb.append("select b.BJDM from view_xsxxb b where xh=?");
		return dao.getOneRs(sb.toString(), new String[] { xh }, "BJDM");
	}

	/*
	 * 描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		super.setTableName("xg_zysz_zyszpj");
		super.setKey("zyszid");
		super.setClass(ZyszpjForm.class);
	}

}
