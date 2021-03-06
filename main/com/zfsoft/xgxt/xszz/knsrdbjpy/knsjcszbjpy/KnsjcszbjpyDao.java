package com.zfsoft.xgxt.xszz.knsrdbjpy.knsjcszbjpy;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.xszz.knsrdbjpy.knsrdbjpy.KnsrdbjpyForm;


public class KnsjcszbjpyDao extends SuperDAOImpl<KnsjcszbjpyForm> {

	@Override
	public List<HashMap<String, String>> getPageList(KnsjcszbjpyForm model)
			throws Exception {
		List<String> params = new ArrayList<String>();
		StringBuilder sql = null;
		String rskzfw = model.getRskzfw();
		sql = new StringBuilder("SELECT * FROM (SELECT  M.*,N.BL,N.GUID,N.ZZRS,(CASE WHEN N.ZZRS IS NULL THEN '0' ELSE '1' END) SFYSZ");
		if (rskzfw != null) {
			if (rskzfw.equals(Constants.RSKZFW_BJ)) {// 班级
				sql.append(" FROM (SELECT DISTINCT A.*,B.XYMC,B.ZYMC,B.BJMC ");
				sql.append(" FROM (SELECT XYDM,NJ,ZYDM,BJDM,COUNT(*) ZRS ");
				sql.append(" FROM VIEW_XSJBXX  ");
				sql
						.append(" GROUP BY XYDM,NJ,ZYDM,BJDM  HAVING XYDM IS NOT NULL AND NJ IS NOT NULL AND ZYDM IS NOT NULL AND BJDM IS NOT NULL) A ");
				sql
						.append(" LEFT JOIN VIEW_NJXYZYBJ_ALL B ON A.BJDM=B.BJDM) M  ");
				sql
						.append(" LEFT JOIN XG_XSZZ_NEW_KNSRSSZB N ON M.BJDM=N.BMDM  AND M.NJ=N.NJ");
			}else if (rskzfw.equals(Constants.RSKZFW_NJXY)) {// 年级+学院
						sql
						.append("  FROM (SELECT DISTINCT A.*,B.XYMC FROM (SELECT XYDM,NJ,COUNT(*) ZRS FROM VIEW_XSJBXX  GROUP BY XYDM,NJ  HAVING XYDM IS NOT NULL AND NJ IS NOT NULL) A ");
				sql
						.append("LEFT JOIN VIEW_NJXYZYBJ_ALL B ON A.XYDM=B.XYDM ) M   ");
				sql
						.append("  LEFT JOIN XG_XSZZ_NEW_KNSRSSZB N ON M.XYDM=N.BMDM AND M.NJ=N.NJ");
		
			} else if (rskzfw.equals(Constants.RSKZFW_NJZY)) {// 年级 + 专业
				sql.append(" FROM (SELECT DISTINCT A.*,B.XYMC,B.ZYMC ");
				sql.append(" FROM (");
				sql.append(" SELECT XYDM,ZYDM,NJ,COUNT(*) ZRS ");
				sql.append(" FROM VIEW_XSJBXX  ");
				sql
						.append(" GROUP BY XYDM,ZYDM,NJ HAVING XYDM IS NOT NULL AND NJ IS NOT NULL AND ZYDM IS NOT NULL) A ");
				sql
						.append(" LEFT JOIN VIEW_NJXYZYBJ_ALL B ON  A.ZYDM=B.ZYDM ) M  ");
				sql
						.append("  LEFT JOIN XG_XSZZ_NEW_KNSRSSZB N ON M.ZYDM=N.BMDM  AND M.NJ=N.NJ");
			} else if (rskzfw.equals(Constants.RSKZFW_XY)){//学院
				sql
				.append("  FROM (SELECT DISTINCT A.*,B.XYMC FROM (SELECT XYDM,COUNT(*) ZRS FROM VIEW_XSJBXX ");

				String rskznj = getRskznj();
				if (rskznj != null && !rskznj.trim().equals("")) {
					sql.append(" where NJ in(");
					sql.append(setRskznj(rskznj));// 格式化rskznj，增加''，以便拼接sql语句
					sql.append(")");
				}
				sql.append("  GROUP BY XYDM  HAVING XYDM IS NOT NULL) A ");
				sql
						.append("LEFT JOIN VIEW_NJXYZYBJ_ALL B ON A.XYDM=B.XYDM ) M   ");
				sql
						.append("  LEFT JOIN XG_XSZZ_NEW_KNSRSSZB N ON M.XYDM=N.BMDM ");
			} else if (rskzfw.equals(Constants.RSKZFW_ZY)) {// 专业
				sql.append(" FROM (SELECT DISTINCT A.*,B.XYMC,B.ZYMC ");
				sql.append(" FROM (");
				sql.append(" SELECT XYDM,ZYDM,COUNT(*) ZRS ");
				sql.append(" FROM VIEW_XSJBXX  ");
				sql
						.append(" GROUP BY XYDM,ZYDM HAVING XYDM IS NOT NULL  AND ZYDM IS NOT NULL) A ");
				sql
						.append(" LEFT JOIN VIEW_NJXYZYBJ_ALL B ON  A.ZYDM=B.ZYDM ) M  ");
				sql
						.append("  LEFT JOIN XG_XSZZ_NEW_KNSRSSZB N ON M.ZYDM=N.BMDM ");
			}
		}
		sql.append(" AND N.XN='");
		sql.append(model.getXn());
		sql.append("' AND N.XQ='");
		sql.append(model.getXq());
		sql.append("'");
		sql.append(")");
		sql.append(" where 1=1 ");
		
		if (!StringUtil.isNull(model.getSfysz())){
			sql.append(" AND SFYSZ='"+model.getSfysz()+"'");
		}
		if (!StringUtil.isNull(model.getNjq())){
			sql.append(" AND NJ='"+model.getNjq()+"'");
		}
		if (!StringUtil.isNull(model.getXydm())){
			sql.append(" AND XYDM='"+model.getXydm()+"'");
		}
		if (!StringUtil.isNull(model.getZydm())){
			sql.append(" AND ZYDM='"+model.getZydm()+"'");
		}
		if (!StringUtil.isNull(model.getBjdm())){
			sql.append(" AND BJDM='"+model.getBjdm()+"'");
		}
		List<HashMap<String, String>> pageList = getPageList(model, sql
				.toString(), params.toArray(new String[] {}));
		return pageList;
	}

	/** 
	 * @描述:获取人数控制年级
	 * @作者：张昌路[工号：982]
	 * @日期：2013-12-10 上午11:20:54
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * String 返回类型 
	 * @throws 
	 */
	private String getRskznj() {
		String sql="select rskznj from xg_xszz_new_knsrd_knsjbszb";
		return dao.getOneRs(sql, new String[]{}, "rskznj");
	}

	@Override
	public List<HashMap<String, String>> getPageList(KnsjcszbjpyForm t, User user)
			throws Exception {
		
		return null;
	}

	@Override
	protected void setTableInfo() {
		super.setTableName("xg_xszz_new_knsrd_knsjbszb");
		super.setClass(KnsjcszbjpyForm.class);
	}
	
	/**
	 * 查询基础设置信息
	 */
	public KnsjcszbjpyForm getModel() throws Exception {
	
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.*, ");
		sql.append(" case when sqkg=1 and sysdate between to_date(nvl(sqkssj,'1990-01-01'),'yyyy-mm-dd') ");
		sql.append(" and to_date(nvl(sqjssj,'2020-01-01'),'yyyy-mm-dd')+1 then 'true' else 'false' end isopen, ");
		sql.append(" case when bjpykg=1 and sysdate between to_date(nvl(bjpykssj,'1990-01-01'),'yyyy-mm-dd') ");
		sql.append(" and to_date(nvl(bjpyjssj,'2020-01-01'),'yyyy-mm-dd')+1 then 'true' else 'false' end bjpyisopen ");
		sql.append(" from xg_xszz_new_knsrd_knsjbszb a ");
		return super.getModel(sql.toString(), new String[]{});
	}
	
	/**
	 * 删除参数设置信息
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean deleteJcsz(KnsjcszbjpyForm model) throws Exception {
		boolean flag = false;
		String sql = "delete from xg_xszz_new_knsrd_knsjbszb";
		flag = dao.runUpdate(sql, new String[]{});
		return flag;
	}
	/**
	 * 只更新开关
	 */
	public boolean updateJcszSqkg(KnsjcszbjpyForm model) throws Exception {
		String sql = " update xg_xszz_new_knsrd_knsjbszb set sqkg=?,bjpykg=?,bjpykssj=?,bjpyjssj=?,xzrsxx=? where rownum=1 ";
		return dao.runUpdate(sql, new String[]{model.getSqkg(),model.getBjpykg(),model.getBjpykssj(),model.getBjpyjssj(),model.getXzrsxx()});
	}
	
	/**
	 * 
	 * @描述:获取所有包含学生的年级
	 * @作者：cmj
	 * @日期：2013-7-5 上午08:37:45
	 * @修改记录:
	 * @return
	 * @throws Exception
	 * @throws
	 */
	public List<String> getNj() throws Exception {
		List<String> result = null;
		StringBuilder sql = new StringBuilder();
		sql
				.append("select distinct nj from VIEW_XSJBXX where nj is not null order by nj ");
		String[] inputValue = {};
		result = dao.getList(sql.toString(), inputValue, "nj");
		return result;
	}

	/** 
	 * @描述:保存所有人数比例设置
	 * @作者：陈敏杰[工号：913]
	 * @日期：2013-12-9 下午06:57:26
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param myForm
	 * @param allList
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean runBlszAll(KnsjcszbjpyForm model,
			List<HashMap<String, String>> list) throws Exception{
		int[] result = null;
		StringBuffer sb = null;
		List<String[]> paramList = new ArrayList<String[]>();
		sb = new StringBuffer();
		sb.append("delete from XG_XSZZ_NEW_KNSRSSZB ");
		dao.runDelete(sb.toString(), new String[]{});// 删除原记录

		sb = new StringBuffer();
		sb.append("insert into XG_XSZZ_NEW_KNSRSSZB(bl,bmdm,nj,xn,xq,zzrs) ");
		sb.append(" values(?,?,?,?,?,?)");
		
		for (HashMap<String, String> map : list) {
			String[] param = { model.getBl(), map.get("bmdm"), map.get("nj"),
					 model.getXn(), model.getXq(),
					map.get("zzrs") };
			paramList.add(param);
		}
		result = dao.runBatch(sb.toString(), paramList);
		return dao.checkBatch(result);
	}

	/** 
	 * @描述:根据勾选记录设置对应人数
	 * @作者：陈敏杰[工号：913]
	 * @日期：2013-12-10 上午09:07:48
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param myForm
	 * @param list
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean runBlsz(KnsjcszbjpyForm model, List<KnsjcszbjpyForm> list) throws Exception{
		int[] result = null;

		List<String> sqlList = new ArrayList<String>();
		StringBuffer sb = null;
		String guid = null;
		sb = new StringBuffer();
		for (KnsjcszbjpyForm model1 : list) {
			guid = model1.getGuid();
			if (guid != null && !guid.trim().equals("")
					&& !guid.trim().equals("null")) {
				sb = new StringBuffer();
				sb.append("update XG_XSZZ_NEW_KNSRSSZB set bl='");
				sb.append(model.getBl());
				sb.append("',zzrs=");
				sb.append(model1.getZzrs());
				sb.append(" where guid='");
				sb.append(guid);
				sb.append("'");
				sqlList.add(sb.toString());
			} else {
				sb = new StringBuffer();
				sb
						.append("insert into XG_XSZZ_NEW_KNSRSSZB(bl,bmdm,nj,xn,xq,zzrs) ");
				sb.append(" values('");
				sb.append(model.getBl());
				sb.append("','");
				sb.append(model1.getBmdm());
				sb.append("','");
				sb.append(model1.getNj());
				sb.append("','");
				sb.append(model.getXn());
				sb.append("','");
				sb.append(model.getXq());
				sb.append("',");
				sb.append(model1.getZzrs());
				sb.append(")");
				sqlList.add(sb.toString());
			}
		}
		if (sqlList != null && sqlList.size() > 0) {
			String[] sqls = new String[sqlList.size()];
			for (int i = 0; i < sqlList.size(); i++) {
				sqls[i] = sqlList.get(i);
			}
			result = dao.runBatch(sqls);
		}

		return dao.checkBatch(result);
	}

	/**
	 * 
	 * @描述:获取总人数
	 * @作者：cmj
	 * @日期：2013-5-29 上午10:55:23
	 * @修改记录:
	 * @return
	 * @throws Exception int 返回类型
	 * @throws
	 */
	public int getZrs(KnsjcszbjpyForm model, String rskznj) throws Exception {
		String rskzfw = model.getRskzfw();

		int num = 0;
		StringBuilder sql = new StringBuilder();
		sql
				.append("select count(*) num from view_xsjbxx where xydm is not null ");
		if (rskzfw.equals(Constants.RSKZFW_XY)) {// 学院
			if (rskznj != null && !rskznj.trim().equals("")) {
				sql.append(" and NJ in(");
				sql.append(setRskznj(rskznj));// 格式化rskznj，增加''，以便拼接sql语句
				sql.append(")");
			}
		}

		String[] input = {};
		String[] output = { "num" };
		String[] oneRs = dao.getOneRs(sql.toString(), input, output);
		if (oneRs != null && oneRs.length > 0) {
			if (oneRs[0] != null) {
				try {
					num = Integer.parseInt(oneRs[0]);
				} catch (Exception e) {
				}
			}
		}
		return num;
	}
	
	/**
	 * @描述:格式化rskznj，增加''，以便拼接sql语句
	 * @作者：cmj
	 * @日期：2013-7-5 上午09:26:19
	 * @修改记录:
	 * @param rskznj
	 * @return String 返回类型
	 * @throws
	 */
	private String setRskznj(String rskznj) {
		if (rskznj != null) {
			rskznj = rskznj.replaceAll(",", "','");
			rskznj = "'" + rskznj + "'";
		}
		return rskznj;
	}

	/** 
	 * @描述:设置人数设置参数
	 * @作者：陈敏杰[工号：913]
	 * @日期：2013-12-10 上午11:02:27
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param string
	 * @param rskznj
	 * @param myForm
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean setRsszcs(String zme, String rskznj, KnsjcszbjpyForm myForm) throws Exception{
		String sql=" update xg_xszz_new_knsrd_knsjbszb set zme=?,rskznj=?,rskzfw=?";
		
		return dao.runUpdate(sql, new String[]{zme,rskznj,myForm.getRskzfw()});
	}

	/** 
	 * @描述:根据学号，获取响应单位困难生人数设置信息
	 * @作者：陈敏杰
	 * @日期：2013-12-10 下午04:39:47
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param rskzfw
	 * @param xh
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws 
	 */
	public HashMap<String, String> getRsszOne(String rskzfw, String xh, KnsrdbjpyForm knsrdbjpyForm) {
		StringBuilder sb = new StringBuilder();
		sb
				.append(" select a.zzrs,a.xn,a.xq  from xg_xszz_new_knsrsszb a,view_xsjbxx b  ");
		sb.append(" where 1=1  ");
		if (rskzfw.equals(Constants.RSKZFW_BJ)) {// 班级
			sb.append("  and a.bmdm=b.bjdm ");
		} else if (rskzfw.equals(Constants.RSKZFW_NJZY)) {// 年级专业
			sb.append("  and a.bmdm=b.zydm ");
			sb.append(" and a.nj=b.nj ");
		} else if (rskzfw.equals(Constants.RSKZFW_NJXY)) {// 年级学院
			sb.append("  and a.bmdm=b.xydm ");
			sb.append(" and a.nj=b.nj ");
		} else if (rskzfw.equals(Constants.RSKZFW_XY)) {// 学院
			sb.append("  and a.bmdm=b.xydm ");
		} else if (rskzfw.equals(Constants.RSKZFW_ZY)) {// 专业
			sb.append("  and a.bmdm=b.zydm ");
		}
		sb.append("  and b.xh=? ");
		sb.append(" and a.xn=? ");
		sb.append(" and a.xq=? ");
		String[] inputValue = new String[] {  xh,knsrdbjpyForm.getXn(),knsrdbjpyForm.getXq() };
		String[] outputValue = new String[] { "zzrs","xn","xq" };
		return dao.getMap(sb.toString(), inputValue, outputValue);
	}

	/** 
	 * @描述:修改困难生人数设置范围
	 * @作者：陈敏杰
	 * @日期：2013-12-11 下午01:40:20
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param myForm
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean updateRskzfw(KnsjcszbjpyForm myForm) throws Exception{
		String sql="update xg_xszz_new_knsrd_knsjbszb set rskzfw=?";
		
		return dao.runUpdate(sql, new String[]{myForm.getRskzfw()});
	}

	/** 
	 * @描述:删除人数设置
	 * @作者：cmj
	 * @日期：2013-12-11 下午02:21:32
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean deleteRssz() throws Exception{
		String sql="delete xg_xszz_new_knsrsszb";
		return dao.runDelete(sql, new String[]{})>0;
	}

	/** 
	 * @描述:获取已设置人数
	 * @作者：陈敏杰
	 * @日期：2013-12-11 下午02:58:30
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param myForm
	 * @return
	 * String 返回类型 
	 * @throws 
	 */
	public String getYszrs(KnsjcszbjpyForm myForm) {
		String sql="select sum(zzrs) num from XG_XSZZ_NEW_KNSRSSZB where xn=? and xq=?";
		return dao.getMapNotOut(sql, new String[]{myForm.getXn(),myForm.getXq()}).get("num");
	}

	/** 
	 * @描述:保存最终人数
	 * @作者：cmj
	 * @日期：2013-12-11 下午04:11:23
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean runZzrs(KnsjcszbjpyForm model) throws Exception{
		int[] result = null;

		StringBuffer sb = null;
		List<String> sqlList = new ArrayList<String>();
		String guid = null;
		String zzrs = null;
		String bl = null;
		String nj = "";
		String bmdm = null;
		String rskzfw = null;

		String[] guids = model.getGuids();
		String[] zzrss = model.getZzrss();
		String[] njs = model.getNjs();
		String[] xydms = model.getXydms();
		String[] bjdms = model.getBjdms();
		String[] zydms = model.getZydms();
		String[] bls = model.getBls();

		if (guids != null && guids.length > 0) {
			for (int i = 0; i < guids.length; i++) {
				guid = guids[i];
				if (!StringUtil.isNull(guid)) {
					sqlList
							.add("delete from XG_XSZZ_NEW_KNSRSSZB where guid='"
									+ guid + "'");
				}
			}
		}
		if (zzrss != null && zzrss.length > 0) {
			for (int i = 0; i < zzrss.length; i++) {
				zzrs = zzrss[i];
				if (zzrs != null) {
					zzrs = zzrs.trim();
				}
				int iZzrs = 0;
				try {
					iZzrs = Integer.parseInt(zzrs);
				} catch (Exception e) {
				}
				bl = bls[i];
				if (njs != null) {
					nj = njs[i];
				}
				rskzfw = model.getRskzfw();
				if (rskzfw != null) {
					if (rskzfw.equals(Constants.RSKZFW_BJ)) {
						bmdm = bjdms[i];
					} else if (rskzfw.equals(Constants.RSKZFW_NJZY)) {
						bmdm = zydms[i];
					} else if (rskzfw.equals(Constants.RSKZFW_NJXY)) {
						bmdm = xydms[i];
					} else if (rskzfw.equals(Constants.RSKZFW_XY)) {
						bmdm = xydms[i];
					} else if (rskzfw.equals(Constants.RSKZFW_ZY)) {
						bmdm = zydms[i];
					}
				}
				sb = new StringBuffer();
				sb
						.append("insert into XG_XSZZ_NEW_KNSRSSZB(bl,bmdm,nj,xn,xq,zzrs) ");
				sb.append(" values('" + bl + "','" + bmdm + "','" + nj + "'");
				sb.append(",'" + model.getXn()+ "','" + model.getXq() + "'," + iZzrs + ")");
				sqlList.add(sb.toString());
			}
		}

		if (sqlList != null && sqlList.size() > 0) {
			String[] sqls = new String[sqlList.size()];
			for (int i = 0; i < sqlList.size(); i++) {
				sqls[i] = sqlList.get(i);
			}
			result = dao.runBatch(sqls);
		}
		return dao.checkBatch(result);
	}

	/** 
	 * @描述:获取已设置人数的单位数
	 * @作者：张昌路[工号：982]
	 * @日期：2013-12-11 下午04:50:37
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param myForm
	 * @return
	 * String 返回类型 
	 * @throws 
	 */
	public String getYszrsNum(KnsjcszbjpyForm myForm) {
		StringBuilder sql=new StringBuilder();
		sql.append("select count(*) num from XG_XSZZ_NEW_KNSRSSZB t1 where exists(select 1 from xsxxb t0 where 1=1 ");
		String rskzfw = myForm.getRskzfw();
		if (rskzfw != null &&!"".equals(rskzfw)) {
			if (rskzfw.equals(Constants.RSKZFW_BJ)) {
				sql.append(" and t1.bmdm=t0.bjdm)");
			} else if (rskzfw.equals(Constants.RSKZFW_NJZY)) {
				sql.append(" and t1.bmdm=t0.zydm and t1.nj=t0.nj)");
			} else if (rskzfw.equals(Constants.RSKZFW_NJXY)) {
				sql.append(" and t1.bmdm=t0.xydm and t1.nj=t0.nj)");
			} else if (rskzfw.equals(Constants.RSKZFW_XY)) {
				sql.append(" and t1.bmdm=t0.xydm )");
			} else if (rskzfw.equals(Constants.RSKZFW_ZY)) {
				sql.append(" and t1.bmdm=t0.zydm )");
			}
			return dao.getMapNotOut(sql.toString(), new String[]{}).get("num");
		}else{
			return "0";
		}
	}

}
