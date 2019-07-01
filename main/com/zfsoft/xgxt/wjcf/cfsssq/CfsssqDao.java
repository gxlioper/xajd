/**
 * @部门:学工产品事业部
 * @日期：2013-10-29 下午01:49:12 
 */  
package com.zfsoft.xgxt.wjcf.cfsssq;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

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

public class CfsssqDao extends SuperDAOImpl<CfsssqForm> {


	@Override
	public List<HashMap<String, String>> getPageList(CfsssqForm t)
			throws Exception {
		return null;
	}


	@Override
	public List<HashMap<String, String>> getPageList(CfsssqForm model, User user)
			throws Exception {
		//生成高级查询相关条件、条件值 
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		String userType=user.getUserType();
		String whereSql="";
		if("stu".equalsIgnoreCase(userType)){
			whereSql=" and instr(t.sfkss,'xs')>0";
		}else{
			whereSql=" and instr(t.sfkss,'js')>0";
		}
		StringBuilder sql=new StringBuilder();
		sql.append("select a.*, b.ssspl splcidnew from (select *");
		sql.append("  from (select a.zzwh,a.zzsj,a.zzyj,a.sjly,a.cfsbid,a.cflbdm,a.cfyydm,a.cfyj,a.cflsh,a.ssfilepath,");
		sql.append("a.nd,a.filepath,a.cfid,a.xh,a.xn,a.xq,(case when a.cfggw is not null then a.cfggw else a.cflbmc end) cflbmc,");
		sql.append("a.cfyymc,a.cfsj,a.cfwh,a.wjsj,a.sbb,a.sbsj,a.wjssjg,a.bz,a.sfsc,a.sssj,a.sswh,a.ssjg,a.cfggw,a.ssyj,");
		sql.append("a.jcwh,a.jcsj,a.jcyj,a.fjmc,a.fj,e.xm,e.xydm,e.nj,e.zydm,e.bjdm,e.zybj,e.zybjmc,e.sydm1 sydm,e.symc1 symc,");
		sql.append("               (select xqmc from xqdzb t where a.xq = t.xqdm) xqmc,");
		sql.append("               (case");
		sql.append("                 when c.ssjg = '0' then");
		sql.append("                  '未提交'");
		sql.append("                 when c.ssjg = '1' then");
		sql.append("                  '通过'");
		sql.append("                 when c.ssjg = '2' then");
		sql.append("                  '不通过'");
		sql.append("                 when c.ssjg = '3' then");
		sql.append("                  '退回'");
		sql.append("                 when c.ssjg = '4' then");
		sql.append("                  '需重审'");
		sql.append("                 when c.ssjg = '5' then");
		sql.append("                  '审核中'");
		sql.append("                 else '未提交申请'");
		sql.append("               end) shztmc,c.ssjg shzt,c.splcid,c.ssid");
		/*sql.append("               b.cflbdm,b.cfyydm");*/
		sql.append("          from xg_wjcf_wjcfb a");
/*		sql.append("          left join xg_wjcf_wjcfsbb b");
		sql.append("            on a.cfid = b.cfid");*/
		sql.append("          left join view_xsjbxx e");
		sql.append("            on a.xh = e.xh");
		sql.append("          left join xg_wjcf_wjcfssb c");
		sql.append("            on a.cfid = c.cfid) a");
		sql.append(" where exists (select 1");
		sql.append("          from xg_wjcf_cflbdmb t");
		sql.append("         where t.cflbdm = a.cflbdm");
		sql.append(whereSql);//处分申诉申请列表显示：在截止日期内处分记录或已经申请的处分记录；申诉截止日为null则没有截止日
		sql.append("           and (to_number(to_date(to_char(sysdate,'yyyy-MM-dd'),'yyyy-MM-dd')-to_date(a.cfsj,'yyyy-MM-dd')) <=t.ssslgzr or t.ssslgzr is null)) or a.shzt is not null)a, xg_wjcf_ssjcsplb b where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		
		return getPageList(model, sql.toString(), inputV);
		
	}


	@Override
	protected void setTableInfo() {
		this.setClass(CfsssqForm.class);
		this.setKey("ssid");
		this.setTableName("xg_wjcf_wjcfssb");

	}


	/** 
	 * @描述:(获取申诉解除审批流程)
	 * @作者：陈敏杰[工号：913]
	 * @日期：2013-10-29 下午04:54:39
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws 
	 */
	public HashMap<String, String> getSsjcsplc() {
		String sql="select * from xg_wjcf_ssjcsplb";
		HashMap<String, String> map=new HashMap<String, String>();
		map=dao.getMapNotOut(sql, new String[]{});
		return map;
	}
	
	
	@Override
	public boolean runInsert(CfsssqForm form) throws Exception {
		String sql = "insert into xg_wjcf_wjcfssb(ssfilepath,ssid,cfid,sqsj,sqly,ssjg,splcid) " +
	     "values(?,?,?,?,?,?,?)" ;
		return dao.runUpdate(sql, new String[]{form.getSsfilepath(),form.getSsid(),form.getCfid(),form.getSqsj(),form.getSqly(),form.getSsjg(),form.getSplcid()});
	}
	

	@Override
	public CfsssqForm getModel(CfsssqForm form) throws Exception {
		String	sql="select ssid,cfid,sqsj,sqly,ssjg,splcid from xg_wjcf_wjcfssb where ssid=?";
		HashMap<String, String> map=dao.getMapNotOut(sql, new String[]{form.getSsid()});
		BeanUtils.copyProperties(form, map);
		return form;
	}
	
	
	@Override
	public boolean runUpdate(CfsssqForm form) throws Exception {
		String sql="";
		if(StringUtils.isNotNull(form.getFjmc())){
			InputStream instream = form.getFj().getInputStream();
			sql="update xg_wjcf_wjcfssb set fj=?,fjmc=?,sqly=?,ssjg=?,splcid=?  where ssid=?";
			return dao.updateBlob(sql, new String[]{form.getFjmc(),form.getSqly(),form.getSsjg(),form.getSplcid(),form.getSsid()}, instream.available(), instream);
		}else{
			sql="update xg_wjcf_wjcfssb set sqly=?,ssjg=?,splcid=?,ssfilepath=?  where ssid=? ";
			return dao.runUpdate(sql, new String[]{form.getSqly(),form.getSsjg(),form.getSplcid(),form.getSsfilepath(),form.getSsid()});
		}
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
	public String[] getCancelIds(String[] values) throws Exception {
		StringBuilder sql=new StringBuilder("select ssid from xg_wjcf_wjcfssb where ssjg='5' and(ssid=? ");
		for (int i = 1; i < values.length; i++) {
			sql.append("or ssid=? ");
		}
		sql.append(")");
		return dao.getArray(sql.toString(), values, "ssid");
	}
	
	
	public boolean updateBackRecord(CfsssqForm form) throws Exception {
		String sql="";
		sql="update xg_wjcf_wjcfssb set ssjg=? , splcid = ? where ssid=?";
		return dao.runUpdate(sql, new String[]{form.getSsjg(),form.getSplcid(),form.getCfid()});
	}
	
	/**
	 * 
	 * @描述:TODO(删除记录)
	 * @作者：HongLin
	 * @日期：2014-1-17
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ids
	 * @return
	 * @throws Exception
	 * int 返回类型 
	 * @throws
	 */
	public int deleteRecord(String[] values) throws Exception{
		if (values == null || values.length == 0){
			logger.error("删除操作不能进行!");
			throw new NullPointerException();
		}
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("delete from xg_wjcf_wjcfssb");
		sql.append(" where (ssjg='0' or ssjg='3') and ");
		sql.append("(");
		
		for (int i = 0 , n = values.length ; i < n ; i++){
			sql.append("ssid=?");
			
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

}
