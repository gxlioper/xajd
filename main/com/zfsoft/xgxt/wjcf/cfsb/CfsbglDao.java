/**
 * @部门:学工产品事业部
 * @日期：2013-10-22 上午11:46:30 
 */  
package com.zfsoft.xgxt.wjcf.cfsb;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.xpjpy.wdpj.sqsh.SqshDao;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 违纪处分管理模块
 * @类功能描述: (处分上报管理) 
 * @作者：陈敏杰[工号:913]
 * @时间： 2013-10-22 上午11:46:30 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class CfsbglDao extends SuperDAOImpl<CfsbglForm> {


	@Override
	public List<HashMap<String, String>> getPageList(CfsbglForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}


	@Override
	public List<HashMap<String, String>> getPageList(CfsbglForm model, User user)
			throws Exception {
		//生成高级查询相关条件、条件值 
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		
		StringBuilder sql = new StringBuilder("select rownum r,a.*    ");
		//江西航空职业技术学院
		if("13871".equals(Base.xxdm)){
			sql.append(" ,bzr.lxdh bzrlxdh,bzr.zgh bzrzgh,bzr.xm bzrxm ");
		}
		sql.append(" from (view_wjcf_cfsb) a ");
		//江西航空职业技术学院
		if("13871".equals(Base.xxdm)){
			sql.append(" left join view_xg_bzrxx bzr on a.bjdm = bzr.bjdm");
		}
		sql.append(" where 1 = 1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		
		return getPageList(model, sql.toString(), inputV);
	}


	@Override
	protected void setTableInfo() {
		this.setKey("cfid");
		this.setTableName("xg_wjcf_wjcfsbb");
		this.setClass(CfsbglForm.class);

	}


	/** 
	 * @描述:(获取审批流程id)
	 * @作者：陈敏杰[工号：913]
	 * @日期：2013-10-22 下午04:18:10
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * String 返回类型 
	 * @throws 
	 */
	public String getSplcid(CfsbglForm model) {
		// TODO 自动生成方法存根
		String sql="select spl from xg_wjcf_cflbdmb where cflbdm=?";
		return dao.getOneRs(sql, new String[]{model.getCflbdm()}, "spl");
	}
	
	/**
	 * 
	 * @描述:(处分上报增加)
	 * @作者：cmj[工号：913]
	 * @日期：2013-10-22 下午03:40:47
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
	 * @throws
	 */
//	@Override
//	public boolean runInsert(CfsbglForm form) throws Exception{
//		String sql = "insert into xg_wjcf_wjcfsbb(fj,cfid,xh,xn,xq,cflbdm,cfyydm,wjsj,cfyj,sbb,sbsj,wjssjg,bz,sbjg,fjmc,splcid,filepath) " +
//	     "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)" ;
//		InputStream instream = form.getFj().getInputStream();
//		return dao.updateBlob(sql, new String[]{form.getCfid(),form.getXh(),form.getXn(),form.getXq(),form.getCflbdm(),
//				form.getCfyydm(),form.getWjsj(),form.getCfyj(),form.getSbb(),form.getSbsj(),form.getWjssjg(),form.getBz(),
//				form.getSbjg(),form.getFjmc(),form.getSplcid(),form.getFilepath()},instream.available(), instream);
//	}
	/**
	 * 
	 * @描述:(获取处分上报)
	 * @作者：cmj[工号：913]
	 * @日期：2013-10-22 下午03:40:47
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
	 * @throws
	 */
	@Override
	public CfsbglForm getModel(CfsbglForm t) throws Exception{
		CfsbglForm model=new CfsbglForm();
		StringBuilder sql = new StringBuilder();
		sql.append("select a.*,(select xqmc from xqdzb t where a.xq=t.xqdm) xqmc,(case when b.cfggw is not null then b.cfggw else (select cflbmc from xg_wjcf_cflbdmb t where t.cflbdm=a.cflbdm) end)cflbmc,(select cfyymc from xg_wjcf_cfyydmb t where t.cfyydm=a.cfyydm)cfyymc,(select xm from fdyxxb t where t.zgh=a.sbb) sbbxm ");
		sql.append("from xg_wjcf_wjcfsbb a ");
		sql.append("left join xg_view_wjcf_wjcfb b on a.cfid=b.cfid ");
		sql.append("where a.cfid=? ");
		HashMap<String, String> map=dao.getMapNotOut(sql.toString(), new String[]{t.getCfid()});
		BeanUtils.copyProperties(model, map);
		return model;
	}
	/**
	 * 获取处分信息（打印表专用）
	 */
	public HashMap<String, String> getDjbModel(String cfid) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.*,t1.cfqx,t1.sfszcfqx from(select e.bmmc sbbmmc,a.*,(select xqmc from XQDZB where xqdm = a.xq) xqmc,b.sqly cfjc_sqly,c.sqly cfss_sqly  from ( ");
		sql.append(" (select ");
		sql.append(" cfid,xh,'' cfsj,cfyj,'' cfwh,wjsj,sbb,'' jcwh,'' jcsj,'' sswh,'' sssj, ");
		sql.append(" (select cflbmc from xg_wjcf_cflbdmb t where t.cflbdm=a.cflbdm)cflbmc, ");
		sql.append(" (select cfyymc from xg_wjcf_cfyydmb t where t.cfyydm=a.cfyydm)cfyymc, ");
		sql.append(" sbjg cfsb_sbjg, xn, xq, wjssjg ");
		sql.append(" from xg_wjcf_wjcfsbb a where not exists (select 1 from xg_wjcf_wjcfb b where a.cfid=b.cfid) ");
		sql.append(" ) union all ( ");
		sql.append(" select ");
		sql.append(" cfid,xh,cfsj,cfyj,cfwh,wjsj,sbb,jcwh,jcsj,sswh,sssj, ");
		sql.append(" cflbmc, ");
		sql.append(" cfyymc, ");
		sql.append(" '1' cfsb_sbjg, xn, xq, wjssjg ");
		sql.append(" from xg_wjcf_wjcfb a  ");
		sql.append(" )) a left join xg_wjcf_wjcfjcsqb b on (a.cfid=b.cfid) ");
		sql.append(" left join xg_wjcf_wjcfssb c on (a.cfid=c.cfid) ");
		sql.append(" left join fdyxxb d on a.sbb=d.zgh ");
		sql.append(" left join zxbz_xxbmdm e on d.bmdm=e.bmdm ");
		sql.append(" where a.cfid=? ) t left join xg_wjcf_cflbdmb t1 on t.cflbmc=t1.cflbmc ");
		return dao.getMapNotOut(sql.toString(), new String[]{ cfid });
	}
	/**
	 * 
	 * @描述:(处分上报修改)
	 * @作者：cmj[工号：913]
	 * @日期：2013-10-22 下午03:40:47
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
	 * @throws
	 */
	@Override
	public boolean runUpdate(CfsbglForm form) throws Exception{
		String sql="";
		if(StringUtils.isNotNull(form.getFjmc())){
			InputStream instream = form.getFj().getInputStream();
			sql="update xg_wjcf_wjcfsbb set kzzd4=?, xn=?,xq=?,fj=?,cfyydm =?,cflbdm=?,wjsj=?,cfyj=?,bz=? ,fjmc=?,splcid=?,sbjg=?,wjssjg=?,filepath=?  where cfid=?";
			return dao.updateBlob(sql, new String[]{form.getKzzd4(),form.getXn(),form.getXq(),form.getCfyydm(),form.getCflbdm(),form.getWjsj(),form.getCfyj(),form.getBz(),
					form.getFjmc(),form.getSplcid(),form.getSbjg(),form.getWjssjg(),form.getFilepath(),form.getCfid()}, instream.available(), instream);
		}else{
			sql="update xg_wjcf_wjcfsbb set kzzd4=?, xn=?,xq=?,cfyydm =?,cflbdm=?,wjsj=?,cfyj=?,bz=?,splcid=?,sbjg=?,wjssjg=?,filepath=?   where cfid=?";
			return dao.runUpdate(sql, new String[]{form.getKzzd4(),form.getXn(),form.getXq(),form.getCfyydm(),form.getCflbdm(),form.getWjsj(),form.getCfyj(),form.getBz(),form.getSplcid(),form.getSbjg(),form.getWjssjg(),form.getFilepath(),form.getCfid()});
		}
		
	}


	/**
	 * @throws Exception 
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
		StringBuilder sql=new StringBuilder("select cfid from xg_wjcf_wjcfsbb where sbjg='0' and(cfid=? ");
		for (int i = 1; i < values.length; i++) {
			sql.append("or cfid=? ");
		}
		sql.append(")");
		return dao.getArray(sql.toString(), values, "cfid");
		
		
	}
	
	public boolean updateCfsbsbjg(CfsbglForm model) throws Exception{
		String[] inputV = new String[3];
		StringBuilder sql = new StringBuilder();
		sql.append(" UPDATE xg_wjcf_wjcfsbb ");
		sql.append(" set ");
		sql.append(" sbjg = ?,");
		sql.append(" splcid = ? ");
		sql.append(" where cfid = ?");
		inputV[0] = model.getSbjg();
		inputV[1] = model.getSplcid();
		inputV[2] = model.getCfid();
		return dao.update(sql.toString(),inputV)>0 ? true:false;
	}
	
	/**
	 * 
	 * @描述:(处分上报删除)
	 * @作者：HongLin
	 * @日期：2014-1-17
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param values
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int runDelete(String[] values) throws Exception {
		if (values == null || values.length == 0){
			logger.error("删除操作不能进行!");
			throw new NullPointerException();
		}
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("delete from xg_wjcf_wjcfsbb");
		sql.append(" where (sbjg='0' or sbjg='3') and ");
		sql.append("(");
		
		for (int i = 0 , n = values.length ; i < n ; i++){
			sql.append("cfid=?");
			
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
	 * @描述:验证处分在结果库当中是否存在 （验证条件：学号、处分类别、处分时间
	 * @作者：cq [工号：785]
	 * @日期：2014-5-14 下午05:29:02
	 * @param myForm
	 * @return
	 * String 返回类型 
	 * @throws 
	 */
	public String checkExistCfjg(CfsbglForm myForm) {
		StringBuilder sql = new StringBuilder(" select count(1) num from xg_wjcf_wjcfb a ");
		sql.append(" where cfwh=?  ");
		
		// 处分ID存在则除去该处分
		if(StringUtils.isNotNull(myForm.getCfid())){
			sql.append(" and a.cfid != '" + myForm.getCfid() + "' ");
		}
		String num = dao.getOneRs(sql.toString(), new String[] {myForm.getCfwh()}, "num");
		
		return num;
	}
	
	public String checkExistCfwh(CfsbglForm myForm) {
		StringBuilder sql = new StringBuilder(" select count(1) num from xg_wjcf_wjcfb a ");
		sql.append(" where a.cfwh=? ");
		
		// 处分ID存在则除去该处分
		if(StringUtils.isNotNull(myForm.getCfid())){
			sql.append(" and a.cfid != '" + myForm.getCfid() + "' ");
		}
		String num = dao.getOneRs(sql.toString(), new String[] { myForm.getCfwh() }, "num");
		
		return num;
	}
	

	/** 
	 * @描述:验证处分在上报和结果库当中是否存在 （验证条件：学号、处分类别、处分时间
	 * @作者：cq [工号：785]
	 * @日期：2014-5-14 下午05:29:02
	 * @param myForm
	 * @return
	 * String 返回类型 
	 * @throws 
	 */
	public String checkExistCfsbjg(CfsbglForm myForm) {
		StringBuilder sql = new StringBuilder();
		String num = "";
		sql.append(" select count(1) num from  ");
		sql.append(" (select xh, cflbdm, wjsj ");
		sql.append("  from XG_WJCF_WJCFB ");
		sql.append("  union ");
		sql.append("  select xh, cflbdm, wjsj from XG_WJCF_WJCFSBB");
		
		// 处分ID存在则除去该处分
		if(StringUtils.isNotNull(myForm.getCfid())){
			sql.append(" where cfid != '" + myForm.getCfid() + "' ");
		}
		
		sql.append("  ) a  ");
		sql.append(" where xh = ? and cflbdm = ? and wjsj = ? ");
		num = dao.getOneRs(sql.toString(), new String[] { myForm.getXh(), myForm.getCflbdm(), myForm.getWjsj() }, "num");
	
		return num;
	}
	
	/**
	 * @描述: 
	 * @作者：Meng.Wei[工号：1186]
	 * @日期：2017-8-17 下午03:55:17
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param cfids
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getCfjdXxByIds(String[] ids) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.*,t1.cfqx,t1.sfszcfqx,t2.xm,t2.xb,t2.xymc,t2.zymc,t2.bjmc,t2.nj from(select e.bmmc sbbmmc,a.*,(select xqmc from XQDZB where xqdm = a.xq) xqmc,b.sqly cfjc_sqly,c.sqly cfss_sqly  from ( ");
		sql.append(" (select ");
		sql.append(" cfid,xh,'' cfsj,cfyj,'' cfwh,wjsj,sbb,'' jcwh,'' jcsj,'' sswh,'' sssj, ");
		sql.append(" (select cflbmc from xg_wjcf_cflbdmb t where t.cflbdm=a.cflbdm)cflbmc, ");
		sql.append(" (select cfyymc from xg_wjcf_cfyydmb t where t.cfyydm=a.cfyydm)cfyymc, ");
		sql.append(" sbjg cfsb_sbjg, xn, xq, wjssjg ");
		sql.append(" from xg_wjcf_wjcfsbb a where not exists (select 1 from xg_wjcf_wjcfb b where a.cfid=b.cfid) ");
		sql.append(" ) union all ( ");
		sql.append(" select ");
		sql.append(" cfid,xh,cfsj,cfyj,cfwh,wjsj,sbb,jcwh,jcsj,sswh,sssj, ");
		sql.append(" cflbmc, ");
		sql.append(" cfyymc, ");
		sql.append(" '1' cfsb_sbjg, xn, xq, wjssjg ");
		sql.append(" from xg_wjcf_wjcfb a  ");
		sql.append(" )) a left join xg_wjcf_wjcfjcsqb b on (a.cfid=b.cfid) ");
		sql.append(" left join xg_wjcf_wjcfssb c on (a.cfid=c.cfid) ");
		sql.append(" left join fdyxxb d on a.sbb=d.zgh ");
		sql.append(" left join zxbz_xxbmdm e on d.bmdm=e.bmdm ");
		sql.append(" where a.cfid in (");
		
		for(int i=0;i<ids.length;i++){
			sql.append("?");
			if(i != ids.length - 1){
				sql.append(",");
			}
		}
		sql.append(")");
		sql.append(" ) t left join xg_wjcf_cflbdmb t1 on t.cflbmc=t1.cflbmc ");
		sql.append("left join view_xsbfxx t2 on t.xh = t2.xh");
		return dao.getListNotOut(sql.toString(), ids);
	}

    public HashMap<String,String> getjcid(String cfid) {
		String sql = " select a.jcid from xg_wjcf_wjcfjcsqb a where a.cfid= ?";
		return dao.getMapNotOut(sql, new String[]{cfid});
    }
}
