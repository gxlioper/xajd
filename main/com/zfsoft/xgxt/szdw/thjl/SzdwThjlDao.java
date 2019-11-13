/**
 * @部门:学工产品事业部
 * @日期：2014-7-17 上午10:06:44 
 */ 
package com.zfsoft.xgxt.szdw.thjl;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.base.util.UniqID;
import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xgxt.form.User;

import java.util.HashMap;
import java.util.List;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 思政队伍管理模块
 * @类功能描述: 谈话记录维护
 * @作者： cq [工号:785]
 * @时间： 2014-7-17 上午10:06:44 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class SzdwThjlDao extends SuperDAOImpl<SzdwThjlForm> {

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(SzdwThjlForm t)
			throws Exception {
		//  自动生成方法存根
		return null;
	}

	/**
	 * 查询
	 */
	public List<HashMap<String, String>> getPageList(SzdwThjlForm t, User user)
			throws Exception {
		String userStatus = user.getUserStatus();

		//生成高级查询相关条件、条件值
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = "";
		if("bzr".equalsIgnoreCase(userStatus) || "fdy".equalsIgnoreCase(userStatus)
				|| "jd".equalsIgnoreCase(userStatus) || "xx".equalsIgnoreCase(userStatus)
				|| "stu".equalsIgnoreCase(userStatus)){
			searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		}else{
			searchTjByUser = " and zgh='"+user.getUserName()+"' ";
		}
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select t.*,t2.sydm,t3.symc from   ");
		sql.append(" (select t.*,row_number() over(partition by t.xh order by t.thsj desc) rn ");
		sql.append(" from xg_view_szdw_thjl t where t.thsj is not null) t ");
		sql.append(" left join XG_XTWH_SYBJGLB t2 on t2.bjdm = t.bjdm ");
		sql.append(" left join XG_XTWH_SYDMB t3 on t2.sydm = t3.sydm ");
		sql.append(" where 1=1 and t.rn = 1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}

	
	/**
	 * 
	 * @描述:根据ID查询谈话记录
	 * @作者：cq [工号：785]
	 * @日期：2014-7-17 上午10:35:18
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param id
	 * @return
	 * @throws Exception
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String,String> getThjlListById(String id) throws Exception {

		StringBuilder sql = new StringBuilder();

		sql.append(" select t.*,rownum r from ( select a.id,a.xh,a.zgh,substr(a.thsj,0,10) thsj,a.thnr,a.yyfx,a.gjcs,a.qtjy,a.sfsdkt,a.sdktsj,a.filepath,a.bz,a.cjsj,a.kssj,a.jssj,a.thsc,a.thlx,(select lxmc from XG_SZDW_THJL_THLX where lxdm=a.thlx) thlxmc,a.khhwt,a.mtyd,decode(a.sfzdgz,1,'是','否') sfzdgzmc,decode(a.sfsdkt,1,'是','否') sfsdktmc,a.sfzdgz,a.gzdj,");
		sql.append(" b.xb xsxb,b.xm xsxm,b.nj,b.xydm,b.xymc,b.zydm,b.zymc,b.bjdm,b.bjmc,c.xb jsxb,c.xm jsxm,(select bmmc from ZXBZ_XXBMDM where bmdm=c.bmdm) jsbmmc");
		sql.append(" from xg_szdw_thjl a left join view_xsbfxx b on a.xh = b.xh left join VIEW_FDYBBQK c on a.zgh = c.zgh");
		sql.append(" where a.sjzt='1') t where 1=1 and t.id=?");
		return	dao.getMapNotOut(sql.toString(), new String[]{id});
	}

	/**
	 * 获取申请信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public HashMap<String,String> getSqMap(String id)throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.*,rownum r from (select a.sqid id,a.splc,a.xh,a.zgh,substr(a.thsj,0,10) thsj,a.thnr,a.yyfx,a.gjcs,a.qtjy,a.sfsdkt,a.sdktsj,a.filepath,a.bz,a.cjsj,a.kssj,a.jssj,a.thsc,a.thlx,(select lxmc from XG_SZDW_THJL_THLX where lxdm=a.thlx) thlxmc,a.khhwt,a.mtyd,decode(a.sfzdgz,1,'是','否') sfzdgzmc,decode(a.sfsdkt,1,'是','否') sfsdktmc,a.sfzdgz,a.gzdj,");
		sql.append(" b.xb xsxb,b.xm xsxm,b.nj,b.xydm,b.xymc,b.zydm,b.zymc,b.bjdm,b.bjmc,c.xb jsxb,c.xm jsxm,(select bmmc from ZXBZ_XXBMDM where bmdm=c.bmdm) jsbmmc");
		sql.append(" from xg_szdw_thjlsqb a left join view_xsbfxx b on a.xh = b.xh left join VIEW_FDYBBQK c on a.zgh = c.zgh");
		sql.append(" ) t where 1=1 and t.id=?");
		return dao.getMapNotOut(sql.toString(), new String[]{id});
	}
	/** 
	 * @描述:根据ID查询谈话记录(温州大学)
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-2-16 下午04:55:22
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param id
	 * @return
	 * @throws Exception
	 * HashMap<String,String> 返回类型 
	 * @throws 
	 */
	public HashMap<String,String> getThjlListByIdForWzdx(String id) throws Exception {

		StringBuilder sql = new StringBuilder();

		sql.append(" select t.*,rownum r from ( select a.id,a.xh,a.zgh,substr(a.thsj,0,10) thsj,a.thnr,a.bz,a.cjsj," );
		sql.append(" a.kssj,a.jssj,a.thsc,a.thlx,(select lxmc from XG_SZDW_THJL_THLX where lxdm=a.thlx) thlxmc,a.khhwt,");
		sql.append(" a.mtyd,decode(a.sfzdgz,1,'是','否') sfzdgzmc,a.sfzdgz,a.gzdj,a.ythrgx,a.gzqx,d.gxmc,");
		sql.append(" b.xb xsxb,b.xm xsxm,b.nj,b.xydm,b.xymc,b.zydm,b.zymc,b.bjdm,b.bjmc,c.xb jsxb,c.xm jsxm,");
		sql.append(" a.bzjg,e.lxmc khhwtmc,a.wtms,f.lxmc wtmsmc,a.tgbz1,g1.lxmc tgbz1mc,a.tgbz3,g3.lxmc tgbz3mc,a.tgbz2,g2.lxmc tgbz2mc,");
		sql.append(" (select bmmc from ZXBZ_XXBMDM where bmdm=c.bmdm) jsbmmc,e.lxmc");
		sql.append(" from xg_szdw_thjl a left join view_xsbfxx b on a.xh = b.xh ");
		sql.append(" left join VIEW_FDYBBQK c on a.zgh = c.zgh " );
		sql.append(" left join XG_SZDW_THJL_THRGX d on a.ythrgx=d.gxdm " );
		sql.append(" left join XG_SZDW_THJL_KHWTLX e on a.khhwt = e.lxdm");
		sql.append(" left join XG_SZDW_THJL_KHWT f on a.wtms = f.lxdm");
		sql.append(" left join XG_SZDW_THJL_TGBZ g1 on a.tgbz1 = g1.lxdm");
		sql.append(" left join XG_SZDW_THJL_TGBZ g2 on a.tgbz2 = g2.lxdm");
		sql.append(" left join XG_SZDW_THJL_TGBZ g3 on a.tgbz3 = g3.lxdm");
		sql.append(" where a.sjzt='1') t where 1=1 and t.id=?");
		return	dao.getMapNotOut(sql.toString(), new String[]{id});
	}
	
	
	/**
	 * 
	 * @描述:根据学号查询谈话记录信息
	 * @作者：cq [工号：785]
	 * @日期：2014-7-17 上午10:47:59
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getThjlListByXh(String xh) throws Exception {

		StringBuilder sql = new StringBuilder();
		sql.append(" select t.*,rownum r from ( select a.id,a.xh,a.zgh,substr(a.thsj,0,10) thsj,a.thnr,a.yyfx,a.gjcs,a.qtjy,a.sfsdkt,a.sdktsj,a.filepath,a.bz,a.cjsj,a.kssj,a.jssj,a.thsc,a.thlx,(select lxmc from XG_SZDW_THJL_THLX where lxdm=a.thlx) thlxmc,a.khhwt,a.mtyd,decode(a.sfzdgz,1,'是','否') sfzdgzmc,decode(a.sfsdkt,1,'是','否') sfsdktmc,a.sfzdgz,a.gzdj,");
		sql.append(" b.xb xsxb,b.xm xsxm,b.nj,b.xydm,b.xymc,b.zydm,b.zymc,b.bjdm,b.bjmc,c.xb jsxb,c.xm jsxm,(select bmmc from ZXBZ_XXBMDM where bmdm=c.bmdm) jsbmmc");
		sql.append(" from xg_szdw_thjl a left join view_xsbfxx b on a.xh = b.xh left join VIEW_FDYBBQK c on a.zgh = c.zgh");
		sql.append(" ) t where 1=1 and t.xh=? order by thsj desc ");
		return	dao.getListNotOut(sql.toString(), new String[]{xh});
	}
	
	
	/** 
	 * @描述:根据学号查询谈话记录信息（温州大学）
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-2-19 下午04:01:29
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String,String>> getThjlListByXhForWzdx(String xh) throws Exception {

		StringBuilder sql = new StringBuilder();
		sql.append(" select t.*,rownum r from ( select a.id,a.xh,a.zgh,substr(a.thsj,0,10) thsj,a.thnr,a.bz,a.cjsj,a.kssj," );
		sql.append(" a.bzjg,e.lxmc khhwtmc,a.wtms,f.lxmc wtmsmc,a.tgbz1,g1.lxmc tgbz1mc," );
		sql.append(" a.tgbz3,g3.lxmc tgbz3mc,a.tgbz2,g2.lxmc tgbz2mc,");
		sql.append(" a.jssj,a.thsc,a.thlx,a.ythrgx,(select lxmc from XG_SZDW_THJL_THLX where lxdm=a.thlx) thlxmc,");
		sql.append(" a.khhwt,a.mtyd,decode(a.sfzdgz,1,'是','否') sfzdgzmc,a.sfzdgz,a.gzdj,a.gzqx,");
		sql.append(" b.xb xsxb,b.xm xsxm,b.nj,b.xydm,b.xymc,b.zydm,b.zymc,b.bjdm,b.bjmc,c.xb jsxb,c.xm jsxm,");
		sql.append(" (select bmmc from ZXBZ_XXBMDM where bmdm=c.bmdm) jsbmmc,d.gxmc,e.lxmc");
		sql.append(" from xg_szdw_thjl a left join view_xsbfxx b on a.xh = b.xh " );
		sql.append(" left join VIEW_FDYBBQK c on a.zgh = c.zgh");
		sql.append(" left join XG_SZDW_THJL_THRGX d on a.ythrgx=d.gxdm " );
		sql.append(" left join XG_SZDW_THJL_KHWTLX e on a.khhwt = e.lxdm");
		sql.append(" left join XG_SZDW_THJL_KHWT f on a.wtms = f.lxdm");
		sql.append(" left join XG_SZDW_THJL_TGBZ g1 on a.tgbz1 = g1.lxdm");
		sql.append(" left join XG_SZDW_THJL_TGBZ g2 on a.tgbz2 = g2.lxdm");
		sql.append(" left join XG_SZDW_THJL_TGBZ g3 on a.tgbz3 = g3.lxdm");
		sql.append(" ) t where 1=1 and t.xh=? order by thsj desc ");
		return	dao.getListNotOut(sql.toString(), new String[]{xh});
	}
	
	
	/**
	 * 保存至谈话记录信息表
	 * 
	 * @author wanghj
	 * @throws Exception
	 */
	public boolean saveThjlInfo(SzdwThjlForm model)
			throws Exception {
	
		String[] input = {UniqID.getInstance().getUniqIDHash(),model.getXh(),
				model.getZgh(),model.getThsj(),model.getThnr(),model.getBz(),
				model.getKssj(),model.getJssj(),model.getThsc(),model.getThlx(),
				model.getKhhwt(),model.getMtyd(),model.getSfzdgz(),model.getGzdj(),
				model.getYyfx(),model.getGjcs(),model.getQtjy(),model.getFilepath(),
				model.getSfsdkt(),model.getSdktsj()};
		
		boolean flag = false;
		String sql = " insert into xg_szdw_thjl (id,xh,zgh,thsj,thnr,bz,cjsj,sjzt,kssj,jssj,thsc,thlx,khhwt,mtyd,sfzdgz,gzdj,yyfx,gjcs,qtjy,filepath,sfsdkt,sdktsj) " +
				"values(?,?,?,?,?,?,to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),1,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		flag = dao.insert(sql, input);
		return flag;
	}
	
	/** 
	 * @描述:温州大学保存
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-2-16 下午04:05:30
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean saveThjlInfoForWzdx(SzdwThjlForm model)
			throws Exception {

		String[] input = {UniqID.getInstance().getUniqIDHash(),model.getXh(),model.getZgh(),model.getThsj(),
				model.getThnr(),model.getBz(),model.getKssj(),model.getJssj(),model.getThsc(),model.getThlx(),
				model.getKhhwt(),model.getMtyd(),model.getSfzdgz(),model.getGzdj(),model.getYthrgx(),
				model.getGzqx(),model.getWtms(),model.getTgbz1(),model.getTgbz2(),model.getTgbz3(),model.getBzjg()};
		
		boolean flag = false;
		String sql = " insert into xg_szdw_thjl " +
				"(id,xh,zgh,thsj,thnr,bz,cjsj,sjzt,kssj,jssj,thsc,thlx,khhwt,mtyd,sfzdgz,gzdj,ythrgx,gzqx,wtms,tgbz1,tgbz2,tgbz3,bzjg) " +
				"values(?,?,?,?,?,?,to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),1,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		flag = dao.insert(sql, input);
		return flag;
}
	
	
	
	/**
	 * 
	 * @描述:删除谈话记录
	 * @作者：cq [工号：785]
	 * @日期：2014-7-17 上午10:51:12
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param id
	 * @return
	 * @throws Exception
	 * int 返回类型 
	 * @throws
	 */
	public int delThjlById(String[] id) throws Exception {
		if (id == null || id.length == 0){
			throw new NullPointerException();
		}
		StringBuilder sql = new StringBuilder();
		
		sql.append("delete from xg_szdw_thjl");
		sql.append(" where  ");
		
		for (int i = 0 , n = id.length ; i < n ; i++){
			sql.append("id=?");
			
			if (i != n-1){
				sql.append(" or ");
			}
		}
		
		return dao.runDelete(sql.toString(), id);
	}
	
	/**
	 * 
	 * @描述:删除谈话记录信息
	 * @作者：cq [工号：785]
	 * @日期：2014-7-17 上午10:51:51
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zgh
	 * @return
	 * @throws Exception
	 * int 返回类型 
	 * @throws
	 */
	public int delThjlByZgh(String[] zgh) throws Exception {
		if (zgh == null || zgh.length == 0){
			throw new NullPointerException();
		}
		StringBuilder sql = new StringBuilder();
		
		sql.append("delete from xg_szdw_thjl");
		sql.append(" where  ");
		
		for (int i = 0 , n = zgh.length ; i < n ; i++){
			sql.append("zgh=?");
			
			if (i != n-1){
				sql.append(" or ");
			}
		}
		
		return dao.runDelete(sql.toString(), zgh);
	}
	
	
	/**
	 * 
	 * @描述:根据ID查询谈话记录信息
	 * @作者：1004
	 * @日期：2013-9-11 上午10:39:43
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param values
	 * @return
	 * @throws Exception
	 * int 返回类型 
	 * @throws
	 */
	public HashMap<String,String> getThjlInfoById(String id) throws Exception {
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select * from xg_szdw_thjl");
		sql.append(" where  sjzt='1' and id = ?");

		return dao.getMapNotOut(sql.toString(), new String[]{id});
	}

	
	/**
	 * 
	 * @描述:根据ID修改谈话记录信息
	 * @作者：1004
	 * @日期：2013-9-11 上午10:39:43
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param values
	 * @return
	 * @throws Exception
	 * int 返回类型 
	 * @throws
	 */
	
	public boolean updateThjlInfo(SzdwThjlForm model) throws Exception{
		HashMap<String,String>  thjlList = null;
		//温州大学个性化需求
		if("10351".equals(Base.xxdm)){
			 thjlList = this.getThjlListByIdForWzdx(model.getId());
		}else{
			 thjlList = this.getThjlInfoById(model.getId());
		}		
		if(model.getId()==null || model.getId().equals("") || thjlList==null || thjlList.size()==0){
			return false;	
		}
		StringBuffer sql = new StringBuffer();
		sql.append(" update xg_szdw_thjl set ");
		
		if(!StringUtil.isNull(model.getThsj()) && !model.getThsj().equals(thjlList.get("thsj"))){
			sql.append(" thsj = '" + model.getThsj()+"',");
		}
		if(!StringUtil.isNull(model.getKssj()) && !model.getKssj().equals(thjlList.get("kssj"))){
			sql.append(" kssj = '" + model.getKssj()+"',");
		}
		if(!StringUtil.isNull(model.getJssj()) && !model.getJssj().equals(thjlList.get("jssj"))){
			sql.append(" jssj = '" + model.getJssj()+"',");
		}
		if(!StringUtil.isNull(model.getThsc()) && !model.getThsc().equals(thjlList.get("thsc"))){
			sql.append(" thsc = '" + model.getThsc()+"',");
		}
		if(!StringUtil.isNull(model.getThlx()) && !model.getThlx().equals(thjlList.get("thlx"))){
			sql.append(" thlx = '" + model.getThlx()+"',");
		}
		if(!StringUtil.isNull(model.getKhhwt()) && !model.getKhhwt().equals(thjlList.get("khhwt"))){
			sql.append(" khhwt = '" + model.getKhhwt()+"',");
		}
		if(!StringUtil.isNull(model.getMtyd()) && !model.getMtyd().equals(thjlList.get("mtyd"))){
			sql.append(" mtyd = '" + model.getMtyd()+"',");
		}
		if(!StringUtil.isNull( model.getThnr()) && !model.getThnr().equals(thjlList.get("thnr"))){
			sql.append(" thnr = '" + model.getThnr()+"',");
		}
		if(!StringUtil.isNull(model.getSjzt()) && !model.getSjzt().equals(thjlList.get("sjzt"))){
			sql.append(" sjzt = '" + model.getSjzt()+"',");
		}
		if(!StringUtil.isNull(model.getSfzdgz()) && !model.getSfzdgz().equals(thjlList.get("sfzdgz"))){
			sql.append(" sfzdgz = '" + model.getSfzdgz()+"',");
		}
		if(!StringUtil.isNull(model.getYyfx()) && !model.getYyfx().equals(thjlList.get("yyfx"))){
			sql.append(" yyfx = '" + model.getYyfx()+"',");
		}
		if(!StringUtil.isNull(model.getGjcs()) && !model.getGjcs().equals(thjlList.get("gjcs"))){
			sql.append(" gjcs = '" + model.getGjcs()+"',");
		}
		if(!StringUtil.isNull(model.getQtjy()) && !model.getQtjy().equals(thjlList.get("qtjy"))){
			sql.append(" qtjy = '" + model.getQtjy()+"',");
		}
		if(!StringUtil.isNull(model.getSfsdkt()) && !model.getSfsdkt().equals(thjlList.get("sfsdkt"))){
			sql.append(" sfsdkt = '" + model.getSfsdkt()+"',");
		}
		if(!StringUtil.isNull(model.getSdktsj()) && !model.getSdktsj().equals(thjlList.get("sdktsj"))){
			sql.append(" sdktsj = '" + model.getSdktsj()+"',");
		}
		if(!StringUtil.isNull(model.getFilepath()) && !model.getFilepath().equals(thjlList.get("filepath"))){
			sql.append(" filepath = '" + model.getFilepath()+"',");
		}
		if("0".equals(model.getSfzdgz())){
			model.setGzdj("");
		}
		sql.append(" gzdj = '" + model.getGzdj()+"',");
		//什么也没修改，则return
		if(sql.lastIndexOf(",")==-1){
			return false;
		}
		//去掉最后一个","
		String sqls = sql.substring(0, sql.lastIndexOf(","))+" where id = ?";
		
		boolean flag = dao.runUpdate(sqls, new String []{model.getId()});
		return flag;
	}

	public boolean update(SzdwThjlForm model) throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append("update xg_szdw_thjlsqb set shzt = ? where sqid = ?");
		return dao.runUpdate(sql.toString(),new String[]{model.getShzt(),model.getSqid()});
	}
	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		super.setClass(SzdwThjlForm.class);
		super.setKey("id");
		super.setTableName("xg_szdw_thjl");
	}


	/** 
	 * @描述:通过职工号取得教师信息
	 * @作者：Qilm[工号：964]
	 * @日期：2013-10-24 上午10:50:41
	 * @param zgh
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws 
	 */
	public HashMap<String, String> getInfoByZgh(String zgh) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select t.*, ");
		sql.append("        (select bmmc from ZXBZ_XXBMDM where bmdm=t.bmdm) bmmc  ");
		sql.append(" from VIEW_FDYBBQK t ");
		sql.append(" where zgh = ? ");		
		return dao.getMapNotOut(sql.toString(), new String[]{ zgh });
	}


	/** 
	 * @描述:取得教师列表
	 * @作者：Qilm[工号：964]
	 * @日期：2013-10-24 上午11:50:32
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getJsInfoList(SzdwThjlForm myForm) {

		StringBuffer sql = new StringBuffer();
		sql.append(" select t.*, ");
		sql.append("        (select bmmc from ZXBZ_XXBMDM where bmdm=t.bmdm) bmmc  ");
		sql.append(" from VIEW_FDYBBQK t ");
		
		try {
			return getPageList(myForm, sql.toString(), new String[]{});
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/** 
	 * @描述:取得困惑和问题类型
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-2-16 下午02:08:13
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getKhhwtList(){
		String sql = "select * from XG_SZDW_THJL_KHWT";
		return dao.getListNotOut(sql, new String[] {});
	}
	
	
	/** 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-2-16 下午02:09:30
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getYthrgxList(){
		String sql = "select * from XG_SZDW_THJL_THRGX";
		return dao.getListNotOut(sql, new String[] {});
	}

	public List<HashMap<String,String>> getAllXjydList(String xh){
		StringBuilder sql = new StringBuilder();
		sql.append("select xn,xq");
		sql.append(",(select xjlbmc from DM_XJLB where xjlbdm = a.ydlbdm ) ydlbmc");
		sql.append(",a.ydqnj");
		sql.append(",(select bmmc from ZXBZ_XXBMDM where bmdm = a.ydqxydm) ydqxymc");
		sql.append(",(select bjmc from bks_bjdm where bjdm = a.ydqbjdm) ydqbjmc");
		sql.append(",(select zymc from bks_zydm where zydm = a.ydqzydm) ydqzymc");
		sql.append(",a.xjydsj,a.xjydwh");
		sql.append(" from XG_XSXX_XJYDJGB a ");
		sql.append(" where a.xh = ?");
		return dao.getListNotOut(sql.toString(),new String[]{xh});
	}
	//获取学生不及格成绩
	public List<HashMap<String,String>> getBjgcj(String xh){
		StringBuilder sql = new StringBuilder();
		sql.append("select kcmc,cj,nvl(cxbj,0) cxbj from CJB where cj < 60 and XQ = ? and xh = ?");
		return dao.getListNotOut(sql.toString(),new String[]{Base.currXq,xh});
	}
	/**
	 * 获取审批流程
	 * @return
	 */
	public String getSplc(){
		StringBuilder sql = new StringBuilder();
		sql.append("select splc from szdw_thjl_jcsz where rownum = 1");
		return dao.getOneRs(sql.toString(),new String[]{},"splc");
	}

	public boolean save(SzdwThjlForm model) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("insert into xg_szdw_thjlsqb");
		sql.append("(sqid,xh,zgh,thsj,thnr,bz,cjsj,sjzt,kssj,jssj,thsc,thlx,khhwt,mtyd,sfzdgz,gzdj,ythrgx,yyfx,gjcs,qtjy,filepath,sfsdkt,sdktsj,sqsj,shzt,splc)");
		sql.append("values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
		String[] input = {model.getSqid(),model.getXh(),model.getZgh(),model.getThsj(),model.getThnr()
				,model.getBz(),model.getCjsj(),model.getSjzt(),model.getKssj(),model.getJssj()
				,model.getThsc(),model.getThlx(),model.getKhhwt(),model.getMtyd(),model.getSfzdgz()
				,model.getGzdj(),model.getYthrgx(),model.getYyfx(),model.getGjcs(),model.getQtjy()
				,model.getFilepath(),model.getSfsdkt(),model.getSdktsj(),model.getSqsj(),model.getShzt(),model.getSplc()};
		return dao.runUpdate(sql.toString(),input);
	}
	public boolean updateSq(SzdwThjlForm model) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("update xg_szdw_thjlsqb ");
		sql.append("set thsj = ?,thnr = ?,bz = ?,cjsj = ?,sjzt = ?,kssj = ?,jssj=?,thsc=?,thlx=?,khhwt=?,mtyd=?,sfzdgz=?");
		sql.append(" ,gzdj=?,ythrgx=?,yyfx=?,gjcs=?,qtjy=?,filepath=?,sfsdkt=?,sdktsj=?,sqsj=?,shzt=? ");
		sql.append(" where sqid = ?");
		String[] input = {model.getThsj(),model.getThnr()
				,model.getBz(),model.getCjsj(),model.getSjzt(),model.getKssj(),model.getJssj()
				,model.getThsc(),model.getThlx(),model.getKhhwt(),model.getMtyd(),model.getSfzdgz()
				,model.getGzdj(),model.getYthrgx(),model.getYyfx(),model.getGjcs(),model.getQtjy()
				,model.getFilepath(),model.getSfsdkt(),model.getSdktsj(),model.getSqsj(),model.getShzt(),model.getSqid()};
		return dao.runUpdate(sql.toString(),input);
	}
	public boolean del(String[] ids) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("delete from xg_szdw_thjlsqb where ");
		for(int i=0;i<ids.length;i++){
			sql.append(" sqid = '"+ids[i]+"' ");
			if(i<ids.length-1){
				sql.append(" or ");
			}
		}
		return dao.runUpdate(sql.toString(),new String[]{});
	}
	/**
	 * 获取申请list
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String,String>> getSqList(SzdwThjlForm t, User user) throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append("select a.* ");
		sql.append(" from (");
		sql.append(" select a.*,b.xm,b.xb,b.nj,b.xymc,b.xydm,b.symc1 symc,b.sydm1 sydm,b.zydm,b.zymc,b.zybjmc,b.zybj,b.bjmc,b.bjdm ");
		sql.append(",decode(a.shzt, '0', '未提交', '1','通过','2', '不通过', '3', '退回', '4', '需重审', '5','审核中','', '无需审核',a.shzt) shztmc");
		sql.append(" ,c.xm jsxm");
		sql.append(" from xg_szdw_thjlsqb a ");
		sql.append(" left join view_xsjbxx b on a.xh = b.xh ");
		sql.append(" left join FDYXXB c on a.zgh = c.zgh ");
		sql.append(" ) a ");
		sql.append(" where 1=1 ");
		return getPageList(t,sql.toString(),inputV);
	}
}
