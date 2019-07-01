package com.zfsoft.xgxt.xszz.knsrdnew.knsrdzb;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.beanutils.BeanUtils;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.base.util.ListUtils;

/**
 * 
 * @系统名称: 学生工作管理系统
 * @模块名称: 学生资助管理模块
 * @类功能描述: 学生资助2013版--困难生认定 
 * @作者： Penghui.Qu 
 * @时间： 2013-4-22 上午09:03:12 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class KnsrdzbDao extends SuperDAOImpl<KnsrdzbForm> {

	

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(KnsrdzbForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(KnsrdzbForm t, User user)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		super.setTableName("xg_xszz_knsrd_knzbb");
		super.setKey("zbid");
		
	}
	
	/**
	 * 
	 * @描述:TODO(获取困难生指标集合)
	 * @作者：Dlq[工号：995]
	 * @日期：2014-1-23 上午09:55:38
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getKnszbPageList(KnsrdzbForm model)throws Exception {
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		sql.append(" select zbid,zbmc,  ");
		sql.append(" ( case when qyzt=0  then '停用' else '启用' end ) qyztmc,qyzt from xg_xszz_knsrd_knzbb a where 1 = 1 ");
		return getPageList(model, sql.toString(),params.toArray(new String[] {}));
	}
	/**
	 * 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：张昌路[工号：982]
	 * @日期：2014-3-15 下午01:18:56
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zbdm
	 * @param zbmc
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean checkZbmc(String zbdm,String zbmc){
		StringBuilder sql = new StringBuilder();
		List<String> param=new ArrayList<String>();
		sql.append("select * from xg_xszz_knsrd_knzbb where zbmc=? ");
		param.add(zbmc);
		if(StringUtils.isNotNull(zbdm)){
			sql.append("and zbid <> ?");
			param.add(zbdm);
		}
		return ListUtils.getListSize(dao.getListNotOut(sql.toString(),param.toArray(new String[]{})))<=0;
	}
	/**
	 * 
	 * @描述:TODO(增加困难指标表)
	 * @作者：Dlq[工号：995]
	 * @日期：2014-1-21 下午01:27:36
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param form
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public boolean addKnsrdzb(KnsrdzbForm form){
		String sql = "insert into xg_xszz_knsrd_knzbb(zbid,zbmc,qyzt) values(?,?,?)";
		boolean flag = false;
		try {
			flag = dao.runUpdate(sql, new String[]{form.getZbid(),form.getZbmc(),form.getQyzt()});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	/**
	 * 
	 * @描述:TODO(增加指标属性表)
	 * @作者：Dlq[工号：995]
	 * @日期：2014-1-21 下午01:27:40
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param form
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public boolean addKnsrdzbsx(KnsrdzbsxForm form){
		boolean flag = false;
		String sql = "insert into xg_xszz_knsrd_zbsxb(sxid,zbid,sxmc,qzbl,xssx) values(?,?,?,?,?)";
		try {
			flag = dao.runUpdate(sql, new String[]{form.getSxid(),form.getZbid(),form.getSxmc(),form.getQzbl(),form.getXssx()});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 * 
	 * @描述:TODO(增加指标内容表)
	 * @作者：Dlq[工号：995]
	 * @日期：2014-1-21 下午01:27:45
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param form
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public boolean addKnsrdzbnr(KnsrdzbnrForm form){
		String sql = "insert into xg_xszz_knsrd_zbnrb(sxid,nrmc,fzlx,fz,xssx) values(?,?,?,?,?)";
		boolean flag = false;
		try {
			flag = dao.runUpdate(sql, new String[]{form.getSxid(),form.getNrmc(),form.getFzlx(),form.getFz(),form.getXssx()});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag ;
	}
	
	/**
	 * 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：Dlq[工号：995]
	 * @日期：2014-1-21 下午05:09:58
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getKnsrdzbsxList(String zbid)throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" select sxid,zbid,");
		sql.append(" sxmc,qzbl,xssx from xg_xszz_knsrd_zbsxb where zbid = ? order by xssx ");
		return dao.getListNotOut(sql.toString(), new String[] { zbid});
	}
	
	/**
	 * 
	 * @描述:TODO(获取困难生认定指标内容集合)
	 * @作者：Dlq[工号：995]
	 * @日期：2014-1-23 上午09:55:03
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param sxid
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getKnsrdzbnrList(String sxid)throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" select nrid,sxid,nrmc,fzlx,fz,xssx from xg_xszz_knsrd_zbnrb where sxid = ? order by xssx ");
		//sql.append(" select a.nrid,sxid,nrmc,fzlx,fz,xssx,b.shfz from xg_xszz_knsrd_zbnrb a  ");
		//sql.append(" left join xg_xszz_knsrd_zbsqnrb b on a.nrid = b.nrid where a.sxid = ? order by a.xssx ");
		return dao.getListNotOut(sql.toString(), new String[] {sxid});
	}
	
	
	/**
	 * 
	 * @描述:TODO(更新困难指标表)
	 * @作者：Dlq[工号：995]
	 * @日期：2014-1-21 下午01:27:36
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param form
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public boolean updateKnsrdzb(KnsrdzbForm form){
		String sql = " update xg_xszz_knsrd_knzbb set zbmc=? where zbid = ? ";
		boolean flag = false;
		try {
			flag = dao.runUpdate(sql, new String[]{form.getZbmc(),form.getZbid()});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	/**
	 * 
	 * @描述:TODO(更新指标属性表)
	 * @作者：Dlq[工号：995]
	 * @日期：2014-1-21 下午01:27:40
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param form
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public boolean updateKnsrdzbsx(KnsrdzbsxForm form){
		String sql = " update xg_xszz_knsrd_zbsxb set zbid=?,sxmc=?,qzbl=?,xssx=? where sxid=? ";
		boolean flag = false;
		try {
			flag = dao.runUpdate(sql, new String[]{form.getZbid(),form.getSxmc(),form.getQzbl(),form.getXssx(),form.getSxid()});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 * 
	 * @描述:TODO(更新指标内容表)
	 * @作者：Dlq[工号：995]
	 * @日期：2014-1-21 下午01:27:45
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param form
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public boolean updateKnsrdzbnr(KnsrdzbnrForm form){
		String sql = " update xg_xszz_knsrd_zbnrb set sxid=?,nrmc=?,fzlx=?,fz=?,xssx=? where nrid=? ";
		boolean flag = false;
		try {
			flag = dao.runUpdate(sql, new String[]{form.getSxid(),form.getNrmc(),form.getFzlx(),form.getFz(),form.getXssx(),form.getNrid()});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 * 
	 * @描述:TODO(删除困难生指标属性)
	 * @作者：Dlq[工号：995]
	 * @日期：2014-1-23 下午04:28:05
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zbid
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean deleteKnsrdzbsx(String zbid){
		boolean flag = false;
		try {
			flag = dao.runUpdate(" delete from xg_xszz_knsrd_zbsxb where zbid = ? ", new String[]{zbid} );
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	/**
	 * 
	 * @描述:TODO(删除困难生指标)
	 * @作者：Dlq[工号：995]
	 * @日期：2014-1-23 下午04:29:34
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zbid
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean deleteKnzb(String zbid){
		boolean flag = false;
		try {
			flag = dao.runUpdate(" delete from xg_xszz_knsrd_knzbb where zbid = ? ", new String[]{zbid} );
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	
	/**
	 * 
	 * @描述:TODO(删除困难生指标内容)
	 * @作者：Dlq[工号：995]
	 * @日期：2014-1-23 上午09:52:41
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param values
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean  deleteKnsrdzbnr(String[] values) throws Exception {
		StringBuilder sql = new StringBuilder();
		List<String> params = new ArrayList<String>();
		boolean flag = false;
		sql.append(" delete from xg_xszz_knsrd_zbnrb  where sxid in  ");
		sql.append(" ( ");
		for (int i = 0 , n = values.length ; i < n ; i++){
			sql.append("?");
			params.add(values[i]);
			if(i != n-1){
				sql.append(",");
			}
		}
		sql.append(" ) ");
		flag = dao.runUpdate(sql.toString(), params.toArray(new String[]{}) );
		return flag ;
		
	}
	/**
	 * 
	 * @描述:TODO(获取困难生认定属性id集合)
	 * @作者：Dlq[工号：995]
	 * @日期：2014-1-23 上午09:39:07
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zbid
	 * @return
	 * @throws Exception
	 * String[] 返回类型 
	 * @throws
	 */
	public String[] getKnsrdzbnrsxidList(String zbid) throws Exception {
		StringBuilder sql = new StringBuilder(
				" select sxid,zbid from xg_xszz_knsrd_zbsxb where zbid = ? "  
					);
		String[] sxids = dao.getRs(sql.toString(), new String[]{zbid},"sxid");
		return sxids;
	}
	
	/**
	 * 
	 * @描述:TODO(获取困难生认定指标id集合)
	 * @作者：Dlq[工号：995]
	 * @日期：2014-1-23 上午09:39:32
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zbid
	 * @return
	 * @throws Exception
	 * String[] 返回类型 
	 * @throws
	 */
	public String[] getKnsrdzbidsList(String zbid) throws Exception {
		StringBuilder sql = new StringBuilder(
				" select zbid from xg_xszz_knsrd_knzbb where zbid <> ? "  
					);
		String[] zbids = dao.getRs(sql.toString(), new String[]{zbid},"zbid");
		return zbids;
	}
	
	/**
	 * 
	 * @描述:TODO(启用困难生认定指标)
	 * @作者：Dlq[工号：995]
	 * @日期：2014-1-23 上午09:51:03
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param values
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean tyKnsrdzb(String[] values ) throws Exception {
		
		StringBuilder sql = new StringBuilder();
		List<String> params = new ArrayList<String>();
		boolean flag = false;
		sql.append(" update  xg_xszz_knsrd_knzbb set qyzt = 0 where zbid in  ");
		sql.append(" ( ");
		for (int i = 0 , n = values.length ; i < n ; i++){
			sql.append("?");
			params.add(values[i]);
			if(i != n-1){
				sql.append(",");
			}
		}
		sql.append(" ) ");
		flag = dao.runUpdate(sql.toString(), params.toArray(new String[]{}) );
		return flag ;
		
	}
	
	/**
	 * 
	 * @描述:TODO(启用困难生认定指标)
	 * @作者：Dlq[工号：995]
	 * @日期：2014-1-23 下午02:47:31
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zbid
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean qyKnsrdzb(String zbid ) throws Exception {
		
		StringBuilder sql = new StringBuilder();
		boolean flag = false;
		sql.append(" update  xg_xszz_knsrd_knzbb set qyzt = 1 where zbid = ? ");
		flag = dao.runUpdate(sql.toString(), new String[]{zbid} );
		return flag ;
		
	}
	
	/**
	 * 
	 * @描述:TODO(得到困难生认定属性Form)
	 * @作者：Dlq[工号：995]
	 * @日期：2014-1-23 下午03:04:18
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return
	 * @throws Exception
	 * KnsrdzbsxForm 返回类型 
	 * @throws
	 */
	public KnsrdzbsxForm getKnsrdzbsxModel(String sxid) throws Exception {
		StringBuffer sql= new StringBuffer();
		sql.append(" select sxmc,qzbl,xssx from xg_xszz_knsrd_zbsxb where sxid = ? ");
		KnsrdzbsxForm model=new KnsrdzbsxForm();
		HashMap<String, String> map=dao.getMapNotOut(sql.toString(), new String[]{sxid});
		BeanUtils.copyProperties(model, map);
		return model;
	}
	
	
	public KnsrdzbnrForm getKnsrdzbnrModel(String nrid) throws Exception {
		StringBuffer sql= new StringBuffer();
		sql.append(" select nrmc,fzlx,fz,xssx from xg_xszz_knsrd_zbnrb where nrid = ? ");
		KnsrdzbnrForm model = new KnsrdzbnrForm();
		HashMap<String, String> map=dao.getMapNotOut(sql.toString(), new String[]{nrid});
		BeanUtils.copyProperties(model, map);
		return model;
	}
	
	
	/**
	 * 
	 * @描述:TODO(获取指标内容内容ids)
	 * @作者：Dlq[工号：995]
	 * @日期：2014-1-23 下午03:26:15
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param values
	 * @return
	 * @throws Exception
	 * String[] 返回类型 
	 * @throws
	 */
	public String[] getKnsrdzbnridList(String sxid) throws Exception {
		
		/*StringBuilder sql = new StringBuilder();
		List<String> params = new ArrayList<String>();
		sql.append(" select * from xg_xszz_knsrd_zbnrb  where sxid in  ");
		sql.append(" ( ");
		for (int i = 0 , n = values.length ; i < n ; i++){
			sql.append("?");
			params.add(values[i]);
			if(i != n-1){
				sql.append(",");
			}
		}
		sql.append(" ) ");*/
		StringBuilder sql = new StringBuilder(" select nrid from xg_xszz_knsrd_zbnrb  where sxid = ? " );
		String[] nrids = dao.getRs(sql.toString(), new String[]{sxid},"nrid");
		return nrids;
	}
	
	/**
	 * 
	 * @描述:TODO(判断是否能删除困难生内容指标)
	 * @作者：Dlq[工号：995]
	 * @日期：2014-1-23 下午04:11:16
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ccqjtxid
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean isCanDel(String zbid){
		StringBuffer sql = new StringBuffer();
		sql.append("  select distinct a.zbmc from xg_xszz_knsrd_knzbb a left join xg_xszz_knsrd_zbsqb b ");
		sql.append(" on a.zbid = b.zbid where a.zbid = ?  and b.zbid is not null ");
		
		Map<String,String> map= dao.getMapNotOut(sql.toString(),new String[]{zbid});
		String zbmc = map.get("zbmc");
		//如果未提交才可以提交
		return zbmc==null?true:false;
		//return true;
	}
	
	public HashMap<String, String> getKnsrdzb(String zbid){
		StringBuffer sb=new StringBuffer();
		sb.append(" select zbmc from xg_xszz_knsrd_knzbb where zbid = ?  ");
		//sb.append("  ");
		return dao.getMapNotOut(sb.toString(),new String[]{zbid});
	}
	
	/**
	 * 
	 * @描述:TODO(根据指标Id判断是否更新)
	 * @作者：Dlq[工号：995]
	 * @日期：2014-2-11 上午10:38:29
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zbid
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String checkExistForUpdate(String zbid) {
		
		StringBuilder sql = new StringBuilder(
				" select count(1) num from xg_xszz_knsrd_zbsqb where zbid=? ");
		String num = dao.getOneRs(sql.toString(), new String[] {zbid}, "num");
		return num;

	}
	
}
