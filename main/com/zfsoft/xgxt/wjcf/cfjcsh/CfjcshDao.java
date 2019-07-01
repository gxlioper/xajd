/**
 * @部门:学工产品事业部
 * @日期：2013-10-30 下午06:38:32 
 */  
package com.zfsoft.xgxt.wjcf.cfjcsh;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.wjcf.cfjcsq.CfjcsqDao;
import org.apache.commons.beanutils.BeanUtils;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.wjcf.cfjg.CfjgForm;
import com.zfsoft.xgxt.wjcf.cfsb.CfsbglForm;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 违纪管理模块
 * @类功能描述: (处分解除审核) 
 * @作者： 陈敏杰[工号:913]
 * @时间： 2013-10-30 下午06:36:19 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class CfjcshDao extends SuperDAOImpl<CfjcshForm> {

	@Override
	public List<HashMap<String, String>> getPageList(CfjcshForm t)
			throws Exception {
		return null;
	}


	@Override
	public List<HashMap<String, String>> getPageList(CfjcshForm t, User user)
			throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");	
		String shgwzByUser = SearchService.getShgwzByUser(user, "a", "xydm", "bjdm");	
		StringBuilder sql=new StringBuilder();
		sql.append("select * from (select * ");
		sql.append("          from (select e.nj,e.xymc,");
		sql.append("                       e.xydm,");
		sql.append("                       e.zydm,");
		sql.append("                       e.bjdm,");
		sql.append("                       e.zymc,e.sydm1 sydm,e.symc1 symc,");
		sql.append("                       e.bjmc, e.zybj, e.zybjmc,");
		sql.append("                       e.xh,");
		sql.append("                       e.xm,");
		sql.append("                       e.xb,");
		sql.append("                       f.xn,");
		sql.append("                       f.xq,");
		sql.append("                       a.cfid,");
		sql.append("                       (select xqmc from xqdzb t1 where t1.xqdm = f.xq) xqmc,");
		sql.append("                       (case when f.cfggw is not null then f.cfggw else (select cflbmc from xg_wjcf_cflbdmb t2 where t2.cflbdm = f.cflbdm) end) cflbmc,");
		sql.append("                       f.cflbdm,");
		sql.append("                       (select cfyymc from xg_wjcf_cfyydmb t2 where t2.cfyydm = f.cfyydm) cfyymc,");
		sql.append("                       f.cfyydm,");
		sql.append("                       c.mc || '[' || decode(b.shzt,0,'待审核',1,'通过',2,'不通过',3,'退回','4','需重审',5,'审核中','其它') || ']' shzt,");
		sql.append("                       c.gwz,");
		sql.append("                       row_number() over(partition by a.cfid order by b.shsj desc) rn,");
		sql.append("                       a.jcid ywid,");
		sql.append("                       a.splcid,");
		sql.append("                       b.gwid,");
		sql.append("                       b.guid shid,b.shsj");
		sql.append("                  from xg_wjcf_wjcfjcsqb a");
		sql.append("                  left join xg_xtwh_shztb b");
		sql.append("                    on a.jcid = b.ywid");
		sql.append("                  left join xg_wjcf_wjcfb f");
		sql.append("                    on a.cfid=f.cfid");
		sql.append("                  left join xg_xtwh_spgw c");
		sql.append("                    on b.gwid = c.id");
		sql.append("                  left join xg_xtwh_spgwyh d");
		sql.append("                    on c.id = d.spgw");
		sql.append("                  left join view_xsjbxx e");
		sql.append("                    on f.xh = e.xh");
		sql.append("                 where d.spyh = '"+user.getUserName()+"'  and b.shzt<>9 ");
		String shlx = t.getShlx();
		if(!shlx.equals("dsh")){
			sql.append(" and (b.shzt<>0 and b.shzt<>4 )  ");
		}else{
			sql.append(" and ( b.shzt=0 or b.shzt=4 )  ");
		}
		sql.append(") a  where rn = 1) a where 1=1  ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		sql.append(shgwzByUser);
		return getPageList(t, sql.toString(), inputV);
	}
	
	
	@Override
	public CfjcshForm getModel(CfjcshForm t) throws Exception {
		String sql="select sqsj,sqly,jdxx,filepath,filepath2 from xg_wjcf_wjcfjcsqb where jcid=? ";
		HashMap<String, String> map=dao.getMapNotOut(sql, new String[]{t.getYwid()});
		CfjcshForm model=new CfjcshForm();
		BeanUtils.copyProperties(model, map);
		return model;
	}

	@Override
	protected void setTableInfo() {

		this.setClass(CfjcshForm.class);
		this.setKey("jcid");
		this.setTableName("xg_wjcf_wjcfjcsqb");
	}


	/** 
	 * @描述:(解除通过进入结果库)
	 * @作者：陈敏杰[工号：913]
	 * @日期：2013-10-30 下午03:11:35
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param form
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean insertWjjgk(CfjcshForm form) throws Exception{
		String sql="update xg_wjcf_wjcfb set jcwh=?,jcsj=?,filepath5=?,filepath6=? where cfid=?";
		
		return dao.runUpdateNotCommit(sql, new String[]{form.getJcwh(),form.getJcsj(),form.getFilepath(),form.getFilepath2(),form.getCfid()});
	}


	/**
	 * @throws Exception  
	 * @描述:(回滚违纪结果库的解除审核记录)
	 * @作者：陈敏杰[工号：913]
	 * @日期：2013-10-30 下午03:52:47
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param cfid
	 * @return
	 * int 返回类型 
	 * @throws 
	 */
	public int udateJgk(String cfid) throws Exception {
		String sql="update xg_wjcf_wjcfb set jcwh='',jcsj='' where cfid=?";
		return dao.runUpdate(sql, new String[]{cfid}, "xg_wjcf_wjcfb");
	}
	/**
	 * 
	 * @描述:解除处分流水号
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-4-29 下午07:31:55
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getJcLsh(CfjcshForm model){
		StringBuffer sql= new StringBuffer();
		sql.append("select (case when length(jclsh)<2 then '0'|| jclsh else jclsh end)  jclsh");
		sql.append("  from (select to_char((to_number(count(1))+1)) jclsh from(select a.*,b.xn,b.xq from xg_wjcf_wjcfjcsqb a left join xg_wjcf_wjcfb b on a.cfid=b.cfid");
		sql.append(" )where xn=? and xq=? and sqjg='1')  ");
		return dao.getOneRs(sql.toString(), new String[]{model.getXn(),model.getXq()},"jclsh");
	}
	/**
	 * 
	 * @描述:验证解除文号是否已存在
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-4-30 上午09:43:55
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param myForm
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String checkExistJcwh(CfjcshForm myForm) {
		StringBuilder sql = new StringBuilder(" select count(1) num from xg_wjcf_wjcfb a ");
		sql.append(" where a.jcwh=?  ");
		
		// 处分ID存在则除去该处分
		if(StringUtils.isNotNull(myForm.getCfid())){
			sql.append(" and a.cfid != '" + myForm.getCfid() + "' ");
		}
		String num = dao.getOneRs(sql.toString(), new String[] { myForm.getJcwh()}, "num");
		
		return num;
	}

}
