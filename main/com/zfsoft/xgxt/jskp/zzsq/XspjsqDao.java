/**
 * @部门:学工产品(1)部
 * @日期：2018-4-11 上午09:09:46 
 */  
package com.zfsoft.xgxt.jskp.zzsq;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 学习评价管理模块
 * @类功能描述: 自主申请
 * @作者： Meng.Wei[工号:1186]
 * @时间： 2018-4-11 上午09:09:20 
 * @版本： V5.18.26
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XspjsqDao extends SuperDAOImpl<XspjsqForm>{

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(XspjsqForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(XspjsqForm t, User user)
			throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (");
		sql.append("select a.sqid,");
		sql.append(" a.xh,b.xm,b.xb,b.nj,b.xydm,b.xymc,b.zydm,b.zymc,b.bjdm,b.bjmc,");
		sql.append(" a.xn,a.fz,a.dxqdm,c.dxqmc,a.xmmc,a.zdbmdm,d.bmmc zdbmmc,a.xmlbdm,e.xmlbmc,");
		sql.append(" a.cysj,a.fzrxm,a.fzrlxfs,a.zdlsxm,a.zdlslxfs,a.fjid,a.sqly,");
		sql.append(" a.shzt,decode(a.shzt,'0','未提交','1','通过','2','不通过','3','已退回','5','审核中',a.shzt) shztmc,");
		sql.append(" a.sjlrr,f.xm sjlrrxm,a.sjlrsj,a.splcid ");
		sql.append("from xg_xspj_new_zzsqb a ");
		sql.append("left join view_xg_xspj_xsxx b on a.xh = b.xh ");
		sql.append("left join xg_jskp_dxq c on a.dxqdm = c.dxqdm ");
		sql.append("left join zxbz_xxbmdm d on a.zdbmdm = d.bmdm ");
		sql.append("left join xg_jskp_xmlbb e on a.xmlbdm = e.xmlbdm ");
		sql.append("left join (select yhm,xm from yhb union select xh yhm,xm from xsxxb) f on a.sjlrr = f.yhm ");
		sql.append(")t where 1 = 1");
		sql.append(searchTj);
		sql.append(searchTjByUser);
		return getPageList(t, sql.toString(), inputV);
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		// TODO 自动生成方法存根
		this.setClass(XspjsqForm.class);
		this.setKey("sqid");
		this.setTableName("xg_xspj_new_zzsqb");
	}
	
	/**
	 * @描述: 判断学生的年级是否大于等于2017
	 * @作者： Meng.Wei[工号：1186]
	 * @日期：2018-4-11 上午10:58:25
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param userName
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean getCheckStuNj(String userName) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append("select count(*)cnt from view_xg_xspj_xsxx where nj >= 2017 and xh = ? ");
		String rs = dao.getOneRs(sql.toString(), new String[]{userName}, "cnt");
		return "1".equals(rs) ? true : false;
	}
	
	/**
	 * @描述: 获取审批流程，用上报的就可以了
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2018-4-11 上午11:09:27
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param sb
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getSplcForParam(String sb){
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from xg_jskp_csszb where lx = ?");
		return dao.getMapNotOut(sql.toString(),new String[]{sb});
	}
	
	/**
	 * @描述: 获取短学期信息List
	 * @作者： Meng.Wei[工号：1186]
	 * @日期：2018-4-12 下午07:25:47
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getDxqInfoList(){
		StringBuilder sql = new StringBuilder();
		sql.append(" select dxqdm,dxqmc from xg_jskp_dxq order by cast(dxqdm as int)");
		return dao.getListNotOut(sql.toString(), new String[]{});
	}
	
	/**
	 * @描述: 判断唯一键，学号(xh),项目名称(xmmc),参与时间(cysj)
	 * @作者： Meng.Wei[工号：1186]
	 * @日期：2018-4-12 下午06:01:12
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean checkIsNotRepeat(XspjsqForm model){
		StringBuilder sql = new StringBuilder();
		List<String> paraList = new ArrayList<String>();
		sql.append("select count(*)cnt from xg_xspj_new_zzsqb where xh = ? and xmmc = ? and cysj = ? ");
		paraList.add(model.getXh());
		paraList.add(model.getXmmc());
		paraList.add(model.getCysj());
		if(StringUtils.isNotNull(model.getSqid())){
			sql.append(" and sqid <> ? ");
			paraList.add(model.getSqid());
		}
		String cnt = dao.getOneRs(sql.toString(), paraList.toArray(new String[]{}), "cnt");
		/*存在数据时返回false，不存在时返回true*/
		return "0".equals(cnt) ? true : false;
	}
	
	/**
	 * @描述: 判断学号是否存在xsxxb
	 * @作者： Meng.Wei[工号:1186]
	 * @日期：2018-5-30 下午05:28:45
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean checkXh(String xh){
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(*)cnt from view_xg_xspj_xsxx where xh = ?");
		String cnt = dao.getOneRs(sql.toString(), new String[]{xh}, "cnt");
		return "0".equals(cnt) ? false : true;
	}
	
	/**
	 * @描述: 删除时清理审核状态表中的垃圾数据
	 * @作者： Meng.Wei[工号：1186]
	 * @日期：2018-4-13 上午10:39:26
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param sqids
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean delShztbData(String[] sqids) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" delete from xg_xtwh_shztb where ywid in ( ");
		for (int i = 0; i < sqids.length; i++) {
			sql.append("?");
			if(i != sqids.length-1){
				sql.append(",");
			}
		}
		sql.append(")");
		return dao.runUpdate(sql.toString(), sqids);
	}
	
	/**
	 * @描述: 根据申请id获取相关信息
	 * @作者： Meng.Wei[工号：1186]
	 * @日期：2018-4-13 下午02:05:38
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param sqidArr
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getInfoBySqid(String[] sqidArr) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append("select * from xg_xspj_new_zzsqb where");
		for(int i = 0; i < sqidArr.length; i++){
			sql.append(" sqid = ? ");
			if(i < sqidArr.length - 1){
				sql.append(" or ");
			}
		}
		return dao.getListNotOut(sql.toString(), sqidArr);
	}
	
	/**
	 * @描述: 根据指导部门代码取部门名称
	 * @作者： Meng.Wei[工号：1186]
	 * @日期：2018-4-13 下午04:52:11
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zdbmdm
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getBmmcByZdbmdm(String zdbmdm){
		String sql = "select bmmc from zxbz_xxbmdm where bmdm = ? ";
		return dao.getOneRs(sql, new String[]{zdbmdm}, "bmmc");
	}
	
	/**
	 * @描述: 根据申请ID获得学生申请信息
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2018-4-16 下午03:37:27
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param sqid
	 * @return
	 * @throws Exception
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String,String> getInfoBySqid(String sqid)throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (");
		sql.append("select a.sqid,");
		sql.append(" a.xh,b.xm,b.xb,b.nj,b.xydm,b.xymc,b.zydm,b.zymc,b.bjdm,b.bjmc,");
		sql.append(" a.xn,a.fz,a.dxqdm,c.dxqmc,a.xmmc,a.zdbmdm,d.bmmc zdbmmc,a.xmlbdm,e.xmlbmc,");
		sql.append(" a.cysj,a.fzrxm,a.fzrlxfs,a.zdlsxm,a.zdlslxfs,a.fjid,a.sqly,");
		sql.append(" a.shzt,decode(a.shzt,'0','未提交','1','通过','2','不通过','3','已退回','5','审核中',a.shzt) shztmc,");
		sql.append(" a.sjlrr,f.xm sjlrrxm,a.sjlrsj,a.splcid ");
		sql.append("from xg_xspj_new_zzsqb a ");
		sql.append("left join view_xg_xspj_xsxx b on a.xh = b.xh ");
		sql.append("left join xg_jskp_dxq c on a.dxqdm = c.dxqdm ");
		sql.append("left join zxbz_xxbmdm d on a.zdbmdm = d.bmdm ");
		sql.append("left join xg_jskp_xmlbb e on a.xmlbdm = e.xmlbdm ");
		sql.append("left join (select yhm,xm from yhb union select xh yhm,xm from xsxxb) f on a.sjlrr = f.yhm ");
		sql.append(")t where t.sqid = ?");
		return dao.getMapNotOut(sql.toString(), new String[]{sqid});
	}
	
	/**
	 * @描述: 指导部门List
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2018-5-22 上午09:02:07
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getZdbmList()throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append("select bmdm zdbm,bmmc zdbmmc from zxbz_xxbmdm");
		return dao.getListNotOut(sql.toString(), new String[]{});
	}
	
	/**
	 * @描述: 短学期List
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2018-5-22 上午09:01:53
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getDxqList()throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append("select dxqdm,dxqmc from xg_jskp_dxq");
		return dao.getListNotOut(sql.toString(), new String[]{});
	}
	
	/**
	 * @描述: 插入结果表
	 * @作者： Meng.Wei[工号:1186]
	 * @日期：2018-5-22 上午09:48:48
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param paralist
	 * @return
	 * @throws Exception
	 * int[] 返回类型 
	 * @throws
	 */
	public int[] saveDrDataIntoDb(List<String[]> paralist) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" insert into xg_xspj_new_zzsqb( ");
		sql.append("sqid,xh,xn,fz,dxqdm,xmmc,zdbmdm,xmlbdm,cysj,fzrxm,fzrlxfs,");
		sql.append("zdlsxm,zdlslxfs,sqly,sjlrr,shzt,splcid,sjlrsj");
		sql.append(" )values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
 		return dao.runBatch(sql.toString(), paralist);
	}
	
	/**
	 * @描述: 验证学号是否存在学生信息表
	 * @作者： Meng.Wei[工号:1186]
	 * @日期： 2018-4-20 下午03:13:34
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean checkXhisTrue(String xh){
		StringBuilder sql = new StringBuilder();
		sql.append("select xm from view_xg_xspj_xsxx where xh = ?");
		String xm = dao.getOneRs(sql.toString(), new String[]{xh}, "xm");
		if(StringUtils.isNull(xm)){
			return false;
		}else{
			return true;
		}
	}
	
	/**
	 * @描述: 验证指导部门
	 * @作者： Meng.Wei[工号:1186]
	 * @日期： 2018-4-20 下午03:23:58
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param yrdwmc
	 * @param user
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String checkZdbm(String zdmbmc){
		StringBuffer sql = new StringBuffer();
		sql.append(" select bmdm from ZXBZ_XXBMDM where bmmc = ? ");
		return dao.getOneRs(sql.toString(), new String[]{zdmbmc}, "bmdm");
	}
	
	/**
	 * @描述: 验证项目类别是否存在
	 * @作者： Meng.Wei[工号:1186]
	 * @日期： 2018-4-20 下午03:28:20
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zdmbmc
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String checkXmlb(String xmlbmc){
		StringBuffer sql = new StringBuffer();
		sql.append(" select xmlbdm from xg_jskp_xmlbb where xmlbmc = ? ");
		return dao.getOneRs(sql.toString(), new String[]{xmlbmc}, "xmlbdm");
	}
	
	/**
	 * @描述: 验证短学期是否存在
	 * @作者： Meng.Wei[工号:1186]
	 * @日期： 2018-4-20 下午03:51:35
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xmlbmc
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String checkDxq(String dxqmc){
		StringBuffer sql = new StringBuffer();
		sql.append(" select dxqdm from xg_jskp_dxq where dxqmc = ?");
		return dao.getOneRs(sql.toString(), new String[]{dxqmc}, "dxqdm");
	}
	
	/**
	 * @描述: 唯一主键验证
	 * @作者： Meng.Wei[工号:1186]
	 * @日期： 2018-4-20 下午03:59:07
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xmmc
	 * @param cysj
	 * @param xh
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean checkIsNotExists(String xmmc,String cysj,String xh,String id){
		StringBuffer sql = new StringBuffer();
		List<String> paraList = new ArrayList<String>();
		sql.append("select count(1) num from xg_xspj_new_zzsqb where xmmc = ? and cysj = ? ");
		sql.append(" and xh = ?");
		paraList.add(xmmc);
		paraList.add(cysj);
		paraList.add(xh);
		if(StringUtils.isNotNull(id)){
			sql.append(" and juid != ?");
			paraList.add(id);
		}
		String num = dao.getOneRs(sql.toString(), paraList.toArray(new String[]{}), "num");
		return (num).equals("0") ? true : false;
	}
}
