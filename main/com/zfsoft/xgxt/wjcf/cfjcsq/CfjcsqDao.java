/**
 * @部门:学工产品事业部
 * @日期：2013-10-30 下午04:32:49 
 */  
package com.zfsoft.xgxt.wjcf.cfjcsq;

import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.xpjpy.wdpj.sqsh.SqshDao;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 违纪管理模块
 * @类功能描述: (处分申诉申请) 
 * @作者： 陈敏杰[工号:913]
 * @时间： 2013-10-29 下午01:46:50 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class CfjcsqDao extends SuperDAOImpl<CfjcsqForm> {


	@Override
	public List<HashMap<String, String>> getPageList(CfjcsqForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}


	@Override
	public List<HashMap<String, String>> getPageList(CfjcsqForm model, User user)
			throws Exception {
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		String userType=user.getUserType();
		String whereSql="";
		if("stu".equalsIgnoreCase(userType)){
			whereSql=" and instr(t.sfksqjc,'xs')>0";
		}else{
			whereSql=" and instr(t.sfksqjc,'js')>0";
		}
		StringBuilder sql=new StringBuilder();
		sql.append("select a.*,b.jcspl splcidnew from (select *");
		sql.append("  from (select a.zzwh,a.zzsj,a.zzyj,a.sjly,a.cfsbid,a.cflbdm,a.cfyydm,a.cfyj,a.cflsh,a.ssfilepath,");
		sql.append("a.nd,a.filepath,a.cfid,a.xh,a.xn,a.xq,(case when a.cfggw is not null then a.cfggw else a.cflbmc end) cflbmc,");
		sql.append("a.cfyymc,a.cfsj,a.cfwh,a.wjsj,a.sbb,a.sbsj,a.wjssjg,a.bz,a.sfsc,a.sssj,a.sswh,a.ssjg,a.cfggw,a.ssyj,");
		sql.append("a.jcwh,a.jcsj,a.jcyj,a.fjmc,a.fj,e.xm,e.xydm,e.nj,e.zydm,e.bjdm,e.zybj,e.zybjmc,e.sydm1 sydm,e.symc1 symc,");
		sql.append("               (select xqmc from xqdzb t where a.xq = t.xqdm) xqmc,");
		sql.append("               (case");
		sql.append("                 when c.sqjg = '0' then");
		sql.append("                  '未提交'");
		sql.append("                 when c.sqjg = '1' then");
		sql.append("                  '通过'");
		sql.append("                 when c.sqjg = '2' then");
		sql.append("                  '不通过'");
		sql.append("                 when c.sqjg = '3' then");
		sql.append("                  '退回'");
		sql.append("                 when c.sqjg = '4' then");
		sql.append("                  '需重审'");
		sql.append("                 when c.sqjg = '5' then");
		sql.append("                  '审核中'");
		sql.append("                 else '未提交申请'");
		sql.append("               end) shztmc,c.sqjg shzt,c.jcid,c.splcid");
/*		sql.append("               b.cflbdm");
*/		sql.append("          from xg_wjcf_wjcfb a");
/*		sql.append("          left join xg_wjcf_wjcfsbb b");
		sql.append("            on a.cfid = b.cfid");*/
		sql.append("          left join view_xsjbxx e");
		sql.append("            on a.xh = e.xh");
		sql.append("          left join xg_wjcf_wjcfjcsqb c");
		sql.append("            on a.cfid = c.cfid) a");
		sql.append(" where exists (select 1");
		sql.append("          from xg_wjcf_cflbdmb t");
		sql.append("         where t.cflbdm = a.cflbdm");
		sql.append(whereSql);
		sql.append("           and to_number(to_date(to_char(sysdate,'yyyy-MM-dd'),'yyyy-MM-dd')-to_date(a.cfsj,'yyyy-MM-dd')) >= to_number(nvl(t.jsslqsr,0))))a, xg_wjcf_ssjcsplb b where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		
		return getPageList(model, sql.toString(), inputV);
	}


	@Override
	protected void setTableInfo() {
		this.setClass(CfjcsqForm.class);
		this.setKey("jcid");
		this.setTableName("xg_wjcf_wjcfjcsqb");

	}


	/**
	 * @throws Exception  
	 * @描述:(获取可以删除的id)
	 * @作者：陈敏杰[工号：913]
	 * @日期：2013-10-29 下午07:01:49
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param values
	 * @return
	 * String[] 返回类型 
	 * @throws 
	 */
	public String[] getCancelIds(String[] values) throws Exception{
		StringBuilder sql=new StringBuilder("select jcid from xg_wjcf_wjcfjcsqb where sqjg='0' and(jcid=? ");
		for (int i = 1; i < values.length; i++) {
			sql.append("or jcid=? ");
		}
		sql.append(")");
		return dao.getArray(sql.toString(), values, "jcid");
	}
	
	public boolean updateBackRecord(CfjcsqForm form) throws Exception {
		String sql="";
		sql="update xg_wjcf_wjcfjcsqb set sqjg=? ,splcid=? where jcid=?";
		return dao.runUpdate(sql, new String[]{form.getSqjg(),form.getSplcid(),form.getJcid()});
	}

	public int runDelete(String[] values) throws Exception {
		if (values == null || values.length == 0){
			logger.error("删除操作不能进行!");
			throw new NullPointerException();
		}
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("delete from xg_wjcf_wjcfjcsqb");
		sql.append(" where (sqjg='0' or sqjg='3') and ");
		sql.append("(");
		
		for (int i = 0 , n = values.length ; i < n ; i++){
			sql.append("jcid=?");
			
			if (i != n-1){
				sql.append(" or ");
			}
		}
		
		sql.append(")");
		int delNum = dao.runDelete(sql.toString(), values);
		if(0!=delNum){
			//删除审核记录
			SqshDao sqshDao = new SqshDao();
			sqshDao.delShzt(values);
		}
		return delNum;
	}
	
	
	
	/**
	 * 
	 * @描述:TODO(查询解除处分文件的学生信息)
	 * @作者：沈晓波[工号：1123]
	 * @日期：2014-8-14 上午08:51:51
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param cfid
	 * @return
	 * @throws Exception
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> jccfwjxx(String cfid) throws Exception{
		DAO dao = DAO.getInstance();
		StringBuffer sql = new StringBuffer();
		sql.append("select trunc(sysdate) dysj,b.jcsj,b.cflbmc,b.cfsj,b.cfyymc,b.xm,b.xb,b.xymc,b.bjmc,b.xh " +
				"from XG_VIEW_WJCF_WJCFB b where b.cfid=? ");
		
		return dao.getMap(sql.toString(), new String[]{cfid}, new String[]{"dysj","jcsj","xh","cflbmc","cfsj","cfyymc","xm","xb","xymc","bjmc"});
	}

}
