/**
 * @部门:学工产品事业部
 * @日期：2013-8-22 上午08:54:38 
 */  
package com.zfsoft.xgxt.xlzx.yysq;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.xlzx.zxswh.ZxspbDao;
import com.zfsoft.xgxt.xlzx.zxswh.ZxspbService;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 心理咨询管理模块
 * @类功能描述: 预约申请
 * @作者： wanghj [工号：1004]
 * @时间： 2013-8-22 上午08:54:38 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class YysqDao extends SuperDAOImpl<YysqForm> {
	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		super.setClass(YysqForm.class);
		super.setKey("id");
		super.setTableName("XG_XLZX_YYSQB");
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(YysqForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}
	
	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(YysqForm t, User user)
			throws Exception {
		
		
		// TODO 自动生成方法存根
		return null;
	}
	
	
	//条件过滤预约咨询信息查询页面（分页）
	public List<HashMap<String, String>> queryYyzxInfoList(YysqForm model,User user)
	throws Exception {

		//生成高级查询相关条件、条件值 
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		//String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		StringBuilder sql = new StringBuilder();
		
		/*sql.append(" select t.*,rownum r from (select a.id,a.xh,a.xsxb,a.xsxm,a.xsage,a.nj,a.xymc,a.zymc,a.bjmc,a.zgh,a.zxsxm,a.zxsxb,a.zxsage,a.zxszg,a.zxsjj,a.kjdrs,a.bmdm,a.bmmc,a.lxdh,a.address,a.yystatus,");
		sql.append(" a.status,a.statusmc,a.yysbyy,a.yyzxrq,a.yyzxzt,a.yyzxxq,a.qssj yyqssj,a.jssj yyjssj,a.xstell,a.bz yybz,b.zxrq,b.qssj zxqssj,b.jssj zxjssj,b.zxdz,b.xspjzt,b.xspj,b.zxsfk,b.zxtell,b.bz zxbz,");
		sql.append(" (case  b.xspjzt when  '1' then '待评价' when '2' then '已评价' else '' end) pjztmc,b.status  zxzt,");
		sql.append(" (case  b.status when  '1' then '待咨询' when '2' then '已咨询' when '3' then '未咨询' else '' end) zxztmc");
		sql.append(" from xg_view_xlzx_yysq a left join xg_xlzx_xlzxb b on a.id = b.yyid ) t where 1=1 ");*/
		
		sql.append("select a.*,b.sjdmc yysjdmc from VIEW_NEW_DC_XLZX_YYSQ a " +
				"left join XG_XLZX_SJDB b on a.sjddm=b.sjddm " +
				"where 1=1 ");
		
		if(user.getUserStatus().equals("stu")){
			sql.append(" and xh='"+user.getUserName()+"'");
		}else if(!user.getUserStatus().equals("stu")){
			sql.append(" and zgh='"+user.getUserName()+"'");
		}
		//sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		return getPageList(model, sql.toString(), inputV);
	}
	
	//根据预约编号查询预约咨询详情
	public HashMap<String,String> getYyzxDetail(String id)
	throws Exception {
		StringBuilder sql = new StringBuilder();
		String pbfs = new ZxspbService().getPbfs();
		sql.append(" select t.*,rownum r from (select a.id,a.xh,a.xsxb,a.xsxm,a.xsage,a.nj,a.xymc,a.zymc,a.bjmc,a.zgh,a.zxsxm,a.zxsxb,a.zxsage,a.zxszg,a.zxsjj,a.kjdrs,a.bmdm,a.bmmc,a.lxdh,a.address,a.yystatus,");
		sql.append(" a.status,a.statusmc,a.yysbyy,a.yyzxrq,a.yyzxzt,a.yyzxxq,a.qssj yyqssj,a.jssj yyjssj,a.xstell,a.bz yybz,b.zxrq,b.qssj zxqssj,b.jssj zxjssj,b.zxdz,b.xspjzt,b.xspj,b.zxsfk,b.zxtell,b.bz zxbz,");
		sql.append(" b.id zxid, ");
		sql.append(" (case  b.xspjzt when  '1' then '待评价' when '2' then '已评价' else '' end) pjztmc,b.status  zxzt,");
		sql.append(" (case  b.status when  '1' then '待咨询' when '2' then '已咨询' when '3' then '未咨询' else '' end) zxztmc");
		sql.append(" ,a.xn ");
		sql.append(" ,a.xqmc ");
		sql.append(" ,d.xqmc xxxq ");
		if(StringUtils.isNotNull(pbfs) && "2".equals(pbfs)){
			sql.append(" ,x.sjdmc,a.sjddm,b.sjddm sjddmzx,y.sjdmc sjdmczx ");
		}
		sql.append(" from xg_view_xlzx_yysq a left join xg_xlzx_xlzxb b on a.id = b.yyid left join xg_xlzx_zxsxxb c on a.zgh = c.zgh left join dm_zju_xq d on c.xq = d.dm");
		if(StringUtils.isNotNull(pbfs) && "2".equals(pbfs)){
			sql.append(" left join xg_xlzx_sjdb x on a.sjddm = x.sjddm");
			sql.append(" left join xg_xlzx_sjdb y on b.sjddm = y.sjddm");
		}
		
		sql.append(" ) t where 1=1 and id = ?");
		return dao.getMapNotOut(sql.toString(), new String[]{id});
	}
	
	
	//预约申请信息详情
	public HashMap<String,String> getYysqInfoById(String id)
	throws Exception {

		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select * ");
		sql.append(" from xg_view_xlzx_yysq t where 1=1 and t.id = ? ");
		
		return dao.getMapNotOut(sql.toString(), new String[]{id});
	}
	/**
	 * 根据ID查询预约申请基本信息
	 * @return
	 */
	public HashMap<String,String> getYysqById(String id){
		
		StringBuilder sql=new StringBuilder();
		
		sql.append(" select * from xg_xlzx_yysqb t where datazt='1' and id=? ");
		return dao.getMapNotOut(sql.toString(), new String[]{id});
	}
	
	/**
	 * 查询预约申请基本信息
	 * @return
	 */
	public HashMap<String,String> getYysqByCn(String xh,String zgh,String yyzxrq){
		StringBuilder inputSbu=new StringBuilder();
		StringBuilder sql=new StringBuilder();
		sql.append(" select * from xg_xlzx_yysqb t where datazt='1' and t.status in ('1','2') ");
		if(!StringUtil.isNull(xh)){
			sql.append(" and xh=?");
			inputSbu.append(xh).append(",");
		}
		if(!StringUtil.isNull(zgh)){
			sql.append(" and zgh=?");
			inputSbu.append(zgh).append(",");
		}
		if(!StringUtil.isNull(yyzxrq)){
			sql.append(" and yyzxrq=?");
			inputSbu.append(yyzxrq).append(",");
		}
		//什么也没修改，则return
		if(inputSbu.lastIndexOf(",")==-1){
			return null;
		}
		//去掉最后一个","
		String inputStr = inputSbu.substring(0, inputSbu.lastIndexOf(","));
		String[] inputValue = inputStr.split(",");
		return dao.getMapNotOut(sql.toString(), inputValue);
	}
	
	/**
	 * 根据学号查询预约申请基本信息
	 * @return
	 */
	public HashMap<String,String> getYysqByXh(String xh){
		
		StringBuilder sql=new StringBuilder();
		
		sql.append(" select * from xg_xlzx_yysqb t where datazt='1' ");
		if(!StringUtil.isNull(xh)){
			sql.append(" and xh=?");
		}
		return dao.getMapNotOut(sql.toString(), new String[]{xh});
	}
	
	/**
	 * 根据学号日期查询预约申请基本信息
	 * @return
	 */
	public HashMap<String,String> getYysqByXhAnddDate(String yyzxrq,String xh){
		String[] inputValue = new String[]{yyzxrq,xh};
		StringBuilder sql=new StringBuilder();
		String pbfs = new ZxspbService().getPbfs();
		if(StringUtils.isNull(pbfs) || "1".equals(pbfs)){
			sql.append(" select * from xg_xlzx_yysqb t where datazt='1' and t.status in ('1','2') and yyzxrq=? and xh=? ");
		}else{
			sql.append(" select t.* from ( ");
			sql.append(" select t.id,t.xh,t.xstell,t.yyzxzt,t.yyzxxq,t.status,t.createsj,t.bz,t.datazt,t.qssj,t.jssj,t.xn,t.xq,t.yysbyy,t1.sjdmc,");
			sql.append(" decode(nvl(t2.ZXRQ, '0'), '0', t.YYZXRQ, t2.ZXRQ) yyzxrq,");
			sql.append(" decode(nvl(t2.ZXRQ, '0'), '0', t.sjddm, t2.sjddm) sjddm,");
			sql.append(" decode(nvl(t2.ZXRQ,'0'), '0', t.ZGH, t2.APZXS) zgh ");
			sql.append(" from xg_xlzx_yysqb t");
			sql.append(" left join xg_xlzx_xlzxb t2");
			sql.append(" on t.id = t2.yyid");
			sql.append(" left join xg_xlzx_sjdb t1");
			sql.append(" on t.sjddm = t1.sjddm ) t ");
			sql.append(" where t.datazt = '1'");
			sql.append(" and t.status in ('1', '2')");
			sql.append(" and t.yyzxrq = ?");
			sql.append(" and t.xh = ?");
			sql.append(" ");
		}
		return dao.getMapNotOut(sql.toString(), inputValue);
	}
	
	/**
	 * 根据咨询师编号日期查询预约申请基本信息
	 * @return
	 */
	public List getYysqByZghAnddDate(String yyzxrq,String zgh){
		String[] inputValue = {yyzxrq,zgh};
		String[] outValue = {"xh","zgh","status","yyzxrq"};
		String pbfs = new ZxspbDao().getPbfs();
		StringBuilder sql=new StringBuilder();
		StringBuilder sqlzcx = new StringBuilder();
		if(StringUtils.isNotNull(pbfs) && ("2").equals(pbfs)){
			sqlzcx.append(" (select t.ID,");
			sqlzcx.append(" t.XH,");
			sqlzcx.append(" decode(nvl(t.ZXRQ, '0'), '0', t.YYZXRQ, t.ZXRQ) yyzxrq,");
			sqlzcx.append(" decode(nvl(t.ZXRQ, '0'), '0', t.sjddm, t.sjddmzx) sjddm,");
			sqlzcx.append(" decode(nvl(t.ZXRQ,'0'), '0', t.ZGH, t.APZXS)zgh,");
			sqlzcx.append(" t.STATUS");
			sqlzcx.append(" from view_new_dc_xlzx_yysq t)");
			sqlzcx.append(" ");
		}else{
			sqlzcx.append(" xg_xlzx_yysqb");
		}
		sql.append(" select xh,zgh,status,yyzxrq from "+sqlzcx.toString()+" t where 1= 1   ");
		if(StringUtils.isNull(pbfs) || ("1").equals(pbfs)){
			sql.append(" and datazt='1' ");
		}
		sql.append(" and (status='2' ");
		if(StringUtils.isNotNull(pbfs) && ("2").equals(pbfs)){
			sql.append("or status = '1'");
		}
		sql.append(") and yyzxrq=? and zgh=? ");
		return dao.getList(sql.toString(),  inputValue, outValue);
	}
	
	/**
	 * 根据学号查询学生基本信息
	 * @return
	 */
	public HashMap<String,String> getStuInfoByXh(String xh){
		
		StringBuilder sql=new StringBuilder();
		
		sql.append(" select t.xh,t.xm,(case t.xb when '1' then'男' when '2' then '女' else  t.xb end) xb,t.nj,t.jg,");
		sql.append(" (select c.qxmc from dmk_qx c where c.qxdm = substr(t.jg, 0, 2)||'0000') ||");
		sql.append(" (select d.qxmc from dmk_qx d where d.qxdm = substr(t.jg, 0, 4)||'00' and t.jg <> substr(t.jg, 0, 2)||'0000') ||");
		sql.append(" (select e.qxmc from dmk_qx e where e.qxdm = t.jg and t.jg <> substr(t.jg, 0, 2)||'0000' and t.jg <> substr(t.jg, 0, 4)||'00') jgmc,");
		sql.append(" (to_char(sysdate, 'yyyy') - substr(substr(t.sfzh,7,8), 0, 4)) age,t.xy,t.zymc,t.bjmc,t.bjdm,t.zydm,t.xydm,t.mz,(select mzmc from mzdmb c where t.mz = c.mzdm) mzmc,t.sjhm from view_xsjbxx t where t.xh=? ");
		return dao.getMapNotOut(sql.toString(), new String[]{xh});
	}
	
	
	/**
	 * 保存至预约申请表
	 * 
	 * @author wanghj
	 * @throws Exception
	 */
	public boolean saveYysqInfo(YysqForm model)
			throws Exception {
		
		StringBuffer sql = new StringBuffer();
		boolean flag = false;
		String[] input = {model.getId(),model.getXh(),model.getXstell(),model.getZgh(),model.getYyzxzt(),
				model.getYyzxxq(),model.getStatus(),model.getYysbyy(),model.getYyzxrq(),model.getBz(),
				model.getQssj(),model.getJssj(),Base.currXn,Base.currXq,model.getSjddm(),model.getQxztzt(),
		 		model.getQxztjl(),model.getQxztyy(),model.getSczxhgb(),model.getZjzt(),model.getBczxwt(),
				model.getZxhzt(),model.getYyfs()};
		sql.append(" insert into xg_xlzx_yysqb ");
		sql.append("(id,xh,xstell,zgh,yyzxzt,yyzxxq,status,yysbyy,yyzxrq,createsj,bz,qssj,jssj,xn,xq,sjddm,");
		sql.append(" qxztzt,qxztjl,qxztyy,sczxhgb,zjzt,bczxwt,zxhzt,yyfs) ");
		sql.append("values(?,?,?,?,?,?,?,?,?,to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
		flag = dao.insert(sql.toString(), input);
		return flag;
	}
	
	/**
	 * 
	 * @描述:删除预约申请信息
	 * @作者：1004
	 * @日期：2013-9-2 上午10:39:43
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * @throws Exception
	 * int 返回类型 
	 * @throws
	 */
	public int delYysqByZgh(String[] zgh) throws Exception {
		if (zgh == null || zgh.length == 0){
			throw new NullPointerException();
		}
		StringBuilder sql = new StringBuilder();
		sql.append("delete from xg_xlzx_yysqb");
		sql.append(" where  ");
		for (int i = 0 , n = zgh.length ; i < n ; i++){
			sql.append("zgh=?");
			
			if (i != n-1){
				sql.append(" or ");
			}
		}
		return dao.runDelete(sql.toString(), zgh);
	}
	
	public boolean  updateYysqStatus(YysqForm model) throws Exception{
		
		HashMap<String,String>  yysqInfoList = this.getYysqById(model.getId());
		if(model.getId()==null || model.getId().equals("") || yysqInfoList==null || yysqInfoList.size()==0){
			return false;	
		}
		StringBuffer sql = new StringBuffer();
		sql.append(" update xg_xlzx_yysqb set status=? where id=?");
		//去掉最后一个","
		boolean flag = dao.runUpdate(sql.toString(), new String []{model.getStatus(),model.getId()});
		return flag;
	}
	
	/** 
	 * @描述:修改预约申请表
	 * @作者：wanghj [工号：1004]
	 * @日期：2013-9-15 上午08:55:33
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * void 返回类型
	 * @throws 
	 */
	public boolean updateYysqInfo(YysqForm model) throws Exception{
		StringBuffer inputS = new StringBuffer();
		HashMap<String,String>  yysqInfoList = this.getYysqById(model.getId());
		if(model.getId()==null || model.getId().equals("") || yysqInfoList==null || yysqInfoList.size()==0){
			return false;	
		}
		StringBuffer sql = new StringBuffer();
		sql.append(" update xg_xlzx_yysqb set ");
		if(!StringUtil.isNull(model.getXstell())){
			sql.append(" xstell = ? ,");
			inputS.append(model.getXstell()+",");
		}
		if(!StringUtil.isNull(model.getYyzxzt())){
			sql.append(" yyzxzt = ? ,");
			inputS.append(model.getYyzxzt()+",");
		}
		if(!StringUtil.isNull(model.getYyzxxq())){
			sql.append(" yyzxxq = ? ,");
			inputS.append(model.getYyzxxq()+",");
		}
		if(!StringUtil.isNull(model.getYysbyy())){
			sql.append(" yysbyy =? ,");
			inputS.append(model.getYysbyy()+",");
		}
		if(!StringUtil.isNull(model.getYyzxrq())){
			sql.append(" yyzxrq = ? ,");
			inputS.append(model.getYyzxrq()+",");
		}
		if(!StringUtil.isNull(model.getQssj())){
			sql.append(" qssj = ? ,");
			inputS.append(model.getQssj()+",");
		}
		if(!StringUtil.isNull(model.getJssj())){
			sql.append(" jssj =? ,");
			inputS.append(model.getJssj()+",");
		}
		if(!StringUtil.isNull(model.getStatus())){
			sql.append(" status = ? ,");
			inputS.append(model.getStatus()+",");
		}
		if(!StringUtil.isNull(model.getBz())){
			sql.append(" bz = ? ,");
			inputS.append(model.getBz()+",");
		}
		if(!StringUtil.isNull(model.getDatazt())){
			sql.append(" datazt = ? ,");
			inputS.append(model.getDatazt()+",");
		}
		if(!StringUtils.isNull(model.getSjddm())){
			sql.append(" sjddm = ? ,");
			inputS.append(model.getSjddm()+",");
		}

		if(!StringUtils.isNull(model.getQxztzt())){
			sql.append(" qxztzt = ? ,");
			inputS.append(model.getQxztzt()+",");
		}
		if(!StringUtils.isNull(model.getQxztjl())){
			sql.append(" qxztjl = ? ,");
			inputS.append(model.getQxztjl()+",");
		}
		if(!StringUtils.isNull(model.getQxztyy())){
			sql.append(" qxztyy = ? ,");
			inputS.append(model.getQxztyy()+",");
		}
		if(!StringUtils.isNull(model.getSczxhgb())){
			sql.append(" sczxhgb = ? ,");
			inputS.append(model.getSczxhgb()+",");
		}
		if(!StringUtils.isNull(model.getZjzt())){
			sql.append(" zjzt = ? ,");
			inputS.append(model.getZjzt()+",");
		}
		if(!StringUtils.isNull(model.getBczxwt())){
			sql.append(" bczxwt = ? ,");
			inputS.append(model.getBczxwt()+",");
		}
		if(!StringUtils.isNull(model.getZxhzt())){
			sql.append(" zxhzt = ? ,");
			inputS.append(model.getZxhzt()+",");
		}

		//什么也没修改，则return
		if(sql.lastIndexOf(",")==-1){
			return false;
		}
		//去掉最后一个","
		String sqls = sql.substring(0, sql.lastIndexOf(","))+" where id = ?";
		String inputValues = inputS.toString()+model.getId();
		String[] inputValue = inputValues.split(",");
		boolean flag = dao.runUpdate(sqls, inputValue);
		return flag;
	}

	/**
	 * @throws Exception  
	 * @描述:删除预约（老师删除）
	 * @作者：Qilm[工号：964]
	 * @日期：2013-10-28 下午02:33:22
	 * @return
	 * int 返回类型 
	 * @throws 
	 */
	public int delYyzxInfo(String[] values) throws Exception {
		if (values == null || values.length == 0){
			throw new NullPointerException();
		}
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("delete from ");
		sql.append(" xg_xlzx_yysqb ");
		sql.append(" where ");
		sql.append("(");
		
		for (int i = 0 , n = values.length ; i < n ; i++){
			
			sql.append(" id = ? ");
			
			if (i != n-1){
				sql.append(" or ");
			}
		}
		
		sql.append(") ");
		
		return dao.runDelete(sql.toString(), values);
	}

	/**
	 * @throws Exception 
	 * @return  
	 * @描述:删除咨询 记录表
	 * @作者：Qilm[工号：964]
	 * @日期：2013-10-28 下午03:24:48
	 * void 返回类型
	 * @throws 
	 */
	public int delXlzxInfo(String[] values) throws Exception {
		if (values == null || values.length == 0){
			throw new NullPointerException();
		}
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("delete from ");
		sql.append(" xg_xlzx_xlzxb ");
		sql.append(" where ");
		sql.append("(");
		
		for (int i = 0 , n = values.length ; i < n ; i++){
			
			sql.append(" yyid = ? ");
			
			if (i != n-1){
				sql.append(" or ");
			}
		}
		
		sql.append(") ");
		
		return dao.runDelete(sql.toString(), values);
	}
	
	/**
	 * 
	 * @描述: 计算按时间段先预约当日人数上限
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-3-27 下午03:02:08
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param pbdate
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getSjdYySx(String pbdate){
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1) cnt from xg_xlzx_zxspbsjb t");
		sql.append(" left join xg_xlzx_zxspbb t1");
		sql.append(" on t.pbid = t1.id");
		sql.append(" where t1.pbdate = ?");
		return dao.getOneRs(sql.toString(), new String[]{pbdate}, "cnt");
	}
	
	/**
	 * 
	 * @描述: 学生预约时间段(被预约或预约成功的进行过滤)
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-3-28 上午09:12:50
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param yyzxrq
	 * @param zgh
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getXsYySjd(String yyzxrq,String zgh,String xh){
		StringBuilder sql = new StringBuilder();
		List<String> paraList = new ArrayList<String>();
		sql.append(" select distinct t.sjddm,t3.sjdmc");
		sql.append(" from xg_xlzx_zxspbsjb t");
		sql.append(" left join (select count(1) cnt,sjddm from (  ");
		sql.append(" select t.ID,");
		sql.append(" t.XH,");
		sql.append(" decode(nvl(t.ZXRQ, '0'), '0', t.YYZXRQ, t.ZXRQ) yyzxrq,");
		sql.append(" decode(nvl(t.ZXRQ, '0'), '0', t.sjddm, t.sjddmzx) sjddm,");
		sql.append(" decode(nvl(t.ZXRQ, '0'), '0', t.ZGH, t.APZXS) zgh,");
		sql.append(" t.STATUS");
		sql.append(" from view_new_dc_xlzx_yysq t");
		sql.append(")");
		sql.append(" where yyzxrq = ? and status in('1','2') and zgh = ?");
		paraList.add(yyzxrq);
		paraList.add(zgh);
		if(StringUtils.isNotNull(xh)){
			sql.append(" and xh != ?");
			paraList.add(xh);
		}
		sql.append(" group by sjddm) t2");
		sql.append(" on t.sjddm = t2.sjddm");
		sql.append(" left join xg_xlzx_sjdb t3");
		sql.append(" on t.sjddm = t3.sjddm");
		sql.append(" where exists (select 1");
		sql.append(" from xg_xlzx_zxspbb t1");
		sql.append(" where t.pbid = t1.id");
		sql.append(" and t1.pbdate = ?) and nvl(t2.cnt,0) = 0 and t.zxs = ?");
		int hour = 0;
		boolean flag = false;
		if("10026".equals(Base.xxdm)){
			Calendar c = Calendar.getInstance();  
	        c.setTime(new Date());  
	        c.add(Calendar.DAY_OF_MONTH, 1);
	        Date next = c.getTime();
	        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd"); 
	        String nextStr = sdf.format(next);
			if(nextStr.equals(yyzxrq)){
				flag = true;
				hour = c.get(Calendar.HOUR_OF_DAY);
				sql.append("  and t3.qsd > to_number(?)");
			}
			
		}
		sql.append("  order by sjddm ");
		paraList.add(yyzxrq);
		paraList.add(zgh);
		if("10026".equals(Base.xxdm) && flag){
			paraList.add(String.valueOf(hour));
		}
		return dao.getListNotOut(sql.toString(),paraList.toArray(new String[]{}));
	}


	public String getRecord(String xh) {
		StringBuilder sb = new StringBuilder();
		sb.append(" select count(1) num from XG_XLZX_XSXX where xh = ?");
		return dao.getOneRs(sb.toString(), new String[]{xh}, "num");
	}

    public String getCountJg(String xh) {
		StringBuilder sb = new StringBuilder();
		sb.append(" select count(1) num from xg_xlzx_xlzxb a left join xg_xlzx_yysqb b on b.id=a.yyid where b.xh = ?");
		return dao.getOneRs(sb.toString(), new String[]{xh}, "num");
    }
}
