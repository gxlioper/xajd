package xgxt.xtwh.sysz;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.form.User;
import xgxt.pjpy.comm.pjpy.PjxtszModel;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.MakeQuery;
import xgxt.xtwh.XtwhDAO;

import common.Globals;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 系统维护_首页设置_DAO类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author 伟大的骆
 * @version 1.0
 */

public class SyszDAO extends XtwhDAO {

	/**
	 * 获得调查id
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public String getDcid(SyszForm model, User user) {

		DAO dao = DAO.getInstance();

		String sql = "select dcid from (select t.dcid from "
				+ "xg_xtwh_sydcb t order by t.dcsj desc) where rownum=1 ";

		String dcid = dao.getOneRs(sql, new String[] {}, "dcid");

		return dcid;

	}

	/**
	 * 设置其他调查为关闭
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public Boolean setOtherDcClose(SyszForm model, User user) throws Exception {

		DAO dao = DAO.getInstance();

		// 调查ID
		String dcid = model.getDcid();

		String sql = "update xg_xtwh_sydcb set sfqy = '否' where dcid <> ? ";

		boolean flag = dao.runUpdate(sql, new String[] { dcid });

		return flag;

	}
	
	/**
	 * 保存首页调查是否启用
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public Boolean saveSydcSfqy(SyszForm model, User user) throws Exception {

		DAO dao = DAO.getInstance();

		// 调查ID
		String dcid = model.getDcid();

		String[] sql = new String[2];
		
		sql[0] = "update xg_xtwh_sydcb set sfqy = '否' ";
		sql[1] = "update xg_xtwh_sydcb set sfqy = '是' where dcid = '"+dcid+"' ";
		
		boolean flag = dao.saveArrDate(sql, user);

		return flag;

	}

	/**
	 * 获得首页调查统计列表
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getSydcTjList(SyszForm model, User user) {

		DAO dao = DAO.getInstance();

		// 调查ID
		String dcid = model.getDcid();

		StringBuilder sql = new StringBuilder();
		sql.append(" select a.xxid,");
		sql.append(" (substr(a.xxnr,0,10)||case when length(a.xxnr)>10 then '...' end) xxinfo, ");
		sql.append(" a.xxnr, nvl(b.num, 0) num ");
		sql.append(" from (select xxid, xxnr from xg_xtwh_sydcxxb a ");
		sql.append(" where dcid = ? order by xxid) a ");
		sql.append(" left join (select xxid, count(1) num ");
		sql.append(" from xg_xtwh_sydcjgb b where b.dcid = ? ");
		sql.append(" group by xxid) b on a.xxid = b.xxid ");

		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] { dcid, dcid }, new String[] { "xxid", "xxnr","xxinfo",
						"num" });

		return list;
	}

	// =======================以上made by 伟大的骆=====================
	
	// =======================以下made by 潇洒的裘=====================
	
	/**
	 * 获取学生申请信息
	 * 根据MKLX区分
	 * author qlj
	 */
	public List<String[]> getXssqInfo(SyszForm myForm) throws Exception {
		
		DzdxSyszDAO dzdxDAO=new DzdxSyszDAO();
		
		List<String[]>dbsxList=new ArrayList<String[]>();
		User user=new User();
		user.setUserName(myForm.getUserName());
		user.setUserType(myForm.getUserType());
		user.setUserDep(myForm.getUserDep());
		user.setFdyQx(((Boolean)myForm.getFdyQx()).toString());
		user.setBzrQx(((Boolean)myForm.getBzrQx()).toString());
		
		String xxdm=Base.xxdm;
		
		//地大单独区分
		if("学生资助".equalsIgnoreCase(myForm.getMklx())
				&& Globals.XXDM_ZGDZDX.equalsIgnoreCase(xxdm)){
			//中国地质大学 资助
			myForm.setGnmklx("zz");
			dbsxList.addAll(dzdxDAO.getXszzsqInfo(myForm));
		}else if("评奖评优".equalsIgnoreCase(myForm.getMklx())
				&& Globals.XXDM_ZGDZDX.equalsIgnoreCase(xxdm)){
			//中国地质大学 评奖
			myForm.setGnmklx("pj");
			dbsxList.addAll(dzdxDAO.getXszzsqInfo(myForm));
		
		}else if("学生资助".equalsIgnoreCase(myForm.getMklx())){
			//学生资助 学生申请(通用)
			dbsxList.addAll(getXszzsqInfo(myForm));
		}else if("评奖评优".equalsIgnoreCase(myForm.getMklx())){
			//学生资助 学生申请(通用)
			dbsxList.addAll(getXspjsqInfo(myForm));
		}else if("违纪处分".equalsIgnoreCase(myForm.getMklx())){
			//违纪信息 学生(通用)
			dbsxList.addAll(getXswjxxInfo(myForm));
		}
		
		
		return dbsxList;
		
	}
	
	
	/**
	 * 获取学生申请信息
	 * (暂时只有学生资助信息,其他模块等待添加)
	 * author 潇洒的裘
	 * @param myForm
	 * @return List<String[]>
	 * @throws Exception
	 */
	public List<String[]> getXszzsqInfo(SyszForm myForm) throws Exception {

		StringBuilder sb = new StringBuilder();

		// ===========================学生资助 申请结果====================================
		sb.append(" select * from ( select rownum r, b.xmdm,('【学生资助】'||b.xmmc)xmmc, ");
		
		// ===========================申请时间==========================
		sb.append("(case when xn is null and xq is null and nd is null then sqsj");
		sb.append(" when xn is null and xq is null and nd is not null then nd||'年度'");
		sb.append(" when nd is null and xq is null and xn is not null then xn||'学年'");
		sb.append(" when nd is null and xq is not null and xn is not null then xn||'学年,");
		sb.append(" '||(select xqmc from xqdzb where xqdm=xq)||'学期' end )sqzqxx,");
		
		// ===========================申请结果===========================
		sb.append(" (case when shjb='三级审核' and  shzt3='通过' then '三级审核通过' ");
		sb.append(" when shjb='三级审核' and shzt3='不通过' then '三级审核不通过' ");
		sb.append(" when shjb='三级审核' and shzt2='通过' then '两级审核通过' ");
		sb.append(" when shjb='三级审核' and shzt2='不通过' then '两级审核通过' ");
		sb.append(" when shjb='三级审核' and shzt1='通过' then '一级审核通过' ");
		sb.append(" when shjb='三级审核' and shzt1='不通过' then '一级审核不通过' ");
		sb.append(" when shjb='三级审核' and shzt1='未审核' then '未审核'     ");

		sb.append(" when shjb = '两级审核' and shzt2 = '通过' then '两级审核通过' ");
		sb.append(" when shjb = '两级审核' and shzt2 = '不通过' then '两级审核通过' ");
		sb.append(" when shjb = '两级审核' and shzt1 = '通过' then '一级审核通过' ");
		sb.append(" when shjb = '两级审核' and shzt1 = '不通过' then  '一级审核不通过' ");
		sb.append(" when shjb = '两级审核' and shzt1 = '未审核' then '未审核' ");

		sb.append(" when shjb = '一级审核' and shzt1 = '通过' then  '一级审核通过' ");
		sb.append(" when shjb = '一级审核' and shzt1 = '不通过' then  '一级审核不通过' ");
		sb.append(" when shjb = '一级审核' and shzt1 = '未审核' then  '未审核' ");
		sb.append(" when shjb = '无需审核' then '已申请' end)shjg");
		sb.append(" from xszz_shztb a,xszz_zzxmb b where a.xmdm=b.xmdm and  xh='"
						+ myForm.getUserName() + "' ");
		if (!"".equalsIgnoreCase(myForm.getXn()) && null != myForm.getXn()) {
			sb.append(" and xn='" + myForm.getXn() + "' ");
		}
		if (!"".equalsIgnoreCase(myForm.getXq()) && null != myForm.getXq()) {
			sb.append(" and xq='" + myForm.getXq() + "'");
		}
		if (!"".equalsIgnoreCase(myForm.getNd()) && null != myForm.getNd()) {
			sb.append(" and nd='" + myForm.getNd() + "'");
		}
		if (!"".equalsIgnoreCase(myForm.getXmdm()) && null != myForm.getXmdm()) {
			sb.append(" and a.xmdm='" + myForm.getXmdm() + "'");
		}
		sb.append(" )order by xmdm ");
		// ===========================学生资助 申请结果end====================================
	
		return CommonQueryDAO.commonQuery(sb.toString(), "", new String[] {},
				new String[] { "xmmc", "sqzqxx", "shjg" }, myForm);
	}

	/**
	 * 待办事项信息(教师)
     * author 潇洒的裘
     * (暂时只有学生资助信息,其他模块等待添加)
	 * @param myForm
	 * @return List<String[]>
	 * @throws Exception
	 */
	public List<String[]> getDbsxInfo(SyszForm myForm) throws Exception {
		
		DzdxSyszDAO dzdxDAO=new DzdxSyszDAO();
		SyszService service=new SyszService();
		List<String[]>dbsxList=new ArrayList<String[]>();
		User user=new User();
		user.setUserName(myForm.getUserName());
		user.setUserType(myForm.getUserType());
		user.setUserDep(myForm.getUserDep());
		user.setFdyQx(((Boolean)myForm.getFdyQx()).toString());
		user.setBzrQx(((Boolean)myForm.getBzrQx()).toString());
		
		String xxdm=Base.xxdm;
		
		if("学生资助".equalsIgnoreCase(myForm.getMklx())
				&& Globals.XXDM_ZGDZDX.equalsIgnoreCase(xxdm)){
			//中国地质大学 资助
			myForm.setGnmklx("zz");
			dbsxList.addAll(dzdxDAO.getZzpjDbsx(myForm));
		}else if("评奖评优".equalsIgnoreCase(myForm.getMklx())
				&& Globals.XXDM_ZGDZDX.equalsIgnoreCase(xxdm)){
			//中国地质大学 评奖
			myForm.setGnmklx("pj");
			dbsxList.addAll(dzdxDAO.getZzpjDbsx(myForm));
		}else if("学生资助".equalsIgnoreCase(myForm.getMklx())){
			//学生资助 待办事项(通用)
			dbsxList.addAll(getXszzDbsx(myForm));
		}else if("文件管理".equalsIgnoreCase(myForm.getMklx())){
			//文件管理 待办事项(通用)
			dbsxList.addAll(getGwglDbsx(myForm,user));
		}else if("评奖评优".equalsIgnoreCase(myForm.getMklx())){
			//学生资助 待办事项(通用)
			dbsxList.addAll(service.getPjpyDbsx(myForm));
		}
						
		return dbsxList;
	}
	
	/**
	 * 学生资助待办事项信息(教师)
     * author 潇洒的裘
	 * @param myForm
	 * @return List<String[]>
	 * @throws Exception
	 */
	public List<String[]> getXszzDbsx(SyszForm myForm) throws Exception {
		StringBuilder sb = new StringBuilder();
		
		// ============登陆信息================
		String yhm = myForm.getUserName();
		String type = myForm.getUserType();
		String bmdm = myForm.getUserDep();
		
		boolean fdyqx = myForm.getFdyQx();
		boolean bzrqx = myForm.getBzrQx();

		boolean isXy = false;
		
		//======================权限控制============================
		if ("xy".equalsIgnoreCase(type)) {
			// 学院用户
			isXy = true;
		}
		
		boolean blog=false;
		if(isXy){
			blog=true;
			//一级审核
			sb.append(" select xmdm, xn,xq,nd,substr(sqsj,0,4)sqn from xszz_shztb a,view_xsjbxx c where xmdm in ");
			sb.append(" (select xmdm from xszz_zzxmb where shjb='一级审核' and xysh='是' ) ");
			sb.append(" and xysh='未审核'  and c.xydm='"+bmdm+"' ");
			//两级审核
			sb.append(" union all ");
			sb.append(" select xmdm, xn,xq,nd,substr(sqsj,0,4)sqn from xszz_shztb a,view_xsjbxx c where xmdm in ");
			sb.append(" (select xmdm from xszz_zzxmb where shjb='两级审核' and xysh='是' ) ");
			sb.append(" and xysh='未审核'  and a.xh=c.xh  and (fdysh='通过' or bzrsh='通过') ");
			sb.append(" and c.xydm='"+bmdm+"' ");
			//两级审核
			sb.append(" union all ");
			sb.append(" select xmdm, xn,xq,nd,substr(sqsj,0,4)sqn from xszz_shztb a,view_xsjbxx c where xmdm in ");
			sb.append(" (select xmdm from xszz_zzxmb where shjb='三级审核' and xysh='是' ) ");
			sb.append(" and xysh='未审核'  and a.xh=c.xh  and (fdysh='通过' or bzrsh='通过') ");
			sb.append(" and c.xydm='"+bmdm+"' ");
			
		}
		
		if(fdyqx ){
			if(blog){
				sb.append("union all");
			}
			//一级审核
			sb.append(" select xmdm, xn,xq,nd,substr(sqsj,0,4)sqn from xszz_shztb a,view_xsjbxx c where xmdm in ");	
			sb.append(" (select xmdm from xszz_zzxmb where shjb='一级审核' and fdysh='是' ) ");
			sb.append(" and fdysh='未审核' and a.xh=c.xh ");
			sb.append(" and exists(select 1 from fdybjb b where c.bjdm=b.bjdm and b.zgh='" + yhm + "')");
			blog=true;
		}
		
		if(bzrqx){
			if(blog){
				sb.append("union all");
			}
			//一级审核
			sb.append(" select xmdm, xn,xq,nd,substr(sqsj,0,4)sqn from xszz_shztb a,view_xsjbxx c  where xmdm in ");	
			sb.append(" (select xmdm from xszz_zzxmb where shjb='一级审核' and bzrsh='是' ) ");
			sb.append(" and bzrsh='未审核' and a.xh=c.xh ");
			sb.append("  and exists(select 1 from  bzrbbb b where c.bjdm=b.bjdm and b.zgh='" + yhm + "') ");
			blog=true;
		}
		
		if("admin".equalsIgnoreCase(type) || "xx".equalsIgnoreCase(type)){
			if(blog){
				sb.append("union all");
			}
			//一级审核
			sb.append(" select xmdm, xn,xq,nd,substr(sqsj,0,4)sqn from xszz_shztb where xmdm in ");	
			sb.append(" ( select xmdm from xszz_zzxmb where shjb='一级审核' and xxsh='是'  ) ");
			sb.append("  and xxsh='未审核' ");
			sb.append("union all");
			//两级审核
			sb.append(" select xmdm, xn,xq,nd,substr(sqsj,0,4)sqn from xszz_shztb b where xmdm in ");	
			sb.append(" (select xmdm from xszz_zzxmb where shjb='两级审核' and (bzrsh='是' and b.bzrsh='通过' or xysh='是' and b.xysh='通过' or  fdysh='是' and b.fdysh='通过' ) )");
			sb.append("  and xxsh='未审核' ");
			sb.append("union all");
			//三级审核
			sb.append(" select xmdm, xn,xq,nd,substr(sqsj,0,4)sqn from xszz_shztb b where xmdm in ");	
			sb.append(" (select xmdm from xszz_zzxmb where shjb='三级审核' and xysh='是' and b.xysh='通过'  ) ");
			sb.append("  and xxsh='未审核' ");
			
		}
		
		StringBuilder  sql=new StringBuilder();
		
	    sql.append("select r,xmdm,xsh,xn,xq,nd,sqn,xmmc,sqzq,case when sqn=dqnd then 'commXszz.do?method=xmshManage&widthType=dbsx&go=go&xmdm='||xmdm else '#' end ljdz from (");
		sql.append("select a.*,b.dqnd from (select rownum r, a.xmdm, a.xsh,a.xn,a.xq,a.nd,a.sqn,('【学生资助】'||b.xmmc)xmmc,b.sqzq from( select xmdm,count(1) xsh, xn,xq,nd,sqn from ("+sb.toString()+") group by xmdm,xn,xq,nd,sqn)a,xszz_zzxmb b where a.xmdm=b.xmdm)a left join xtszb b on 1=1 )a");
		sql.append(" where 1=1 ");
		if(!"".equalsIgnoreCase(myForm.getXn()) && null!=myForm.getXn()){
			sql.append(" and xn='"+myForm.getXn()+"' ");
		}
		if(!"".equalsIgnoreCase(myForm.getXq()) && null!=myForm.getXq()){
			sql.append(" and xq='"+myForm.getXq()+"'");
		}
		if(!"".equalsIgnoreCase(myForm.getNd()) && null!=myForm.getNd()){
			sql.append(" and nd='"+myForm.getNd()+"'");
		}
		if(!"".equalsIgnoreCase(myForm.getXmdm()) && null!=myForm.getXmdm()){
			sql.append(" and a.xmdm='"+myForm.getXmdm()+"'");
		}
		
		
		return CommonQueryDAO.commonQuery(sql.toString(), "", new String[] {},
				new String[] { "xmmc", "xsh","ljdz"}, myForm);
	}
	
	/**
	 * 获取学生申请项目信息
	 * author 潇洒的裘
     * (暂时只有学生资助信息,其他模块等待添加) 
	 * @param myForm
	 * @return List<String[]>
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getSqxxList(SyszForm myForm)
			throws Exception {

		List<HashMap<String, String>>sqxxlist=new ArrayList<HashMap<String, String>>();
		DzdxSyszDAO dzdxDAO=new DzdxSyszDAO();
		
		String xxdm=Base.xxdm;
		//学生资助
		if(Globals.XXDM_ZGDZDX.equalsIgnoreCase(xxdm)){
			myForm.setGnmklx("zz");
			sqxxlist.addAll(dzdxDAO.getSqzzxxList(myForm));
			myForm.setGnmklx("pj");
			sqxxlist.addAll(dzdxDAO.getSqzzxxList(myForm));
		}else {
			sqxxlist.addAll(getSqzzxxList(myForm));
			sqxxlist.addAll(getSqpjxxList(myForm));
		}
		
		return sqxxlist;
	}
	
	/**
	 * 获取学生资助申请项目信息
	 * author 潇洒的裘
     * (暂时只有学生资助信息,其他模块等待添加) 
	 * @param myForm
	 * @return List<String[]>
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getSqzzxxList(SyszForm myForm)
			throws Exception {

		StringBuilder sb = new StringBuilder();
		DAO dao = DAO.getInstance();
		
		sb.append("  select * from ( select  distinct(b.xmdm)xmdm,b.xmmc ,'xszz' mklx ");
		sb.append(" from xszz_shztb a,xszz_zzxmb b where a.xmdm=b.xmdm and  xh='"
						+ myForm.getUserName() + "') ");
		sb.append("order by xmdm  ");
		
		return dao.getList(sb.toString(), new String[] {}, new String[] {
				"mklx", "xmdm", "xmmc" });
	}
	
	/**
	 * 获取学生评奖申请项目信息
	 * author qlj
	 * @param myForm
	 * @return List<String[]>
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getSqpjxxList(SyszForm myForm)
			throws Exception {

		StringBuilder sb = new StringBuilder();
		DAO dao = DAO.getInstance();
		
		sb.append("  select * from ( select  distinct(b.xmdm)xmdm,b.xmmc ,'xszz' mklx ");
		sb.append(" from xszz_shztb a,xszz_zzxmb b where a.xmdm=b.xmdm and  xh='"
						+ myForm.getUserName() + "') ");
		sb.append("order by xmdm  ");
		
		return dao.getList(sb.toString(), new String[] {}, new String[] {
				"mklx", "xmdm", "xmmc" });
	}
	
	
	/**
	 * 获取待办事项项目列表	dbsxInfo.jsp(DWR)
     * (暂时只有学生资助信息,其他模块等待添加)  
	 * author 潇洒的裘
	 * @param String yhm, String type,
			String bmdm, boolean fdyqx, boolean bzrqx, String mklx
	 * @return List<HashMap<String, String>>
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getDbsxXmList(SyszForm myForm)
			throws Exception {
		DzdxSyszDAO dzdxDAO=new DzdxSyszDAO();
		
		List<HashMap<String, String>>dbsxxmList=new ArrayList<HashMap<String, String>>();
		User user =new User();
		user.setUserName(myForm.getUserName());
		user.setUserType(myForm.getUserType());
		user.setUserDep(myForm.getUserDep());
		user.setFdyQx(((Boolean)myForm.getFdyQx()).toString());
		user.setBzrQx(((Boolean)myForm.getBzrQx()).toString());
		
		String xxdm=Base.xxdm;
		//学生资助待办事项项目列表
		if(Globals.XXDM_ZGDZDX.equalsIgnoreCase(xxdm)){
			//中国地质大学 资助
			myForm.setGnmklx("zz");
			dbsxxmList.addAll(dzdxDAO.getZzpjList(myForm));
			myForm.setGnmklx("pj");
			dbsxxmList.addAll(dzdxDAO.getZzpjList(myForm));
		}else {
			dbsxxmList.addAll(getXszzDbxm(myForm));
			dbsxxmList.addAll(getGwglDb());
			dbsxxmList.addAll(getPjpyDbxm(myForm));
		}
		return dbsxxmList;
	}
	
	/**
	 * 获取学生资助待办事项项目列表
     * (暂时只有学生资助信息,其他模块等待添加)  
	 * author 潇洒的裘
	 * @param String yhm, String type,
			String bmdm, boolean fdyqx, boolean bzrqx, String mklx
	 * @return List<HashMap<String, String>>
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getXszzDbxm(SyszForm myForm)
			throws Exception {

		DAO dao = DAO.getInstance();

		boolean isXy = false;
		
		String yhm=myForm.getUserName();
		
		String type=myForm.getUserType();
		
		String bmdm=myForm.getUserDep();
		
		boolean fdyqx=myForm.getFdyQx();
		
		boolean bzrqx=myForm.getBzrQx();
		// =======================根据权限拼接SQL==========================
		if ("xy".equalsIgnoreCase(type)) {
			isXy = true;
		}

		StringBuilder sb = new StringBuilder();
 
		boolean blog=false;
		if(isXy){
			blog=true;
			//一级审核
			sb.append(" select xmdm, xn,xq,nd,'学生资助' mklx from xszz_shztb a,view_xsjbxx c where xmdm in ");
			sb.append(" (select xmdm from xszz_zzxmb where shjb='一级审核' and xysh='是' ) ");
			sb.append(" and xysh='未审核'  and c.xydm='"+bmdm+"' ");
			//两级审核
			sb.append(" union all ");
			sb.append(" select xmdm, xn,xq,nd,'学生资助' mklx from xszz_shztb a,view_xsjbxx c where xmdm in ");
			sb.append(" (select xmdm from xszz_zzxmb where shjb='两级审核' and xysh='是' ) ");
			sb.append(" and xysh='未审核'  and a.xh=c.xh  and (fdysh='通过' or bzrsh='通过') ");
			sb.append(" and c.xydm='"+bmdm+"' ");
			//两级审核
			sb.append(" union all ");
			sb.append(" select xmdm, xn,xq,nd,'学生资助' mklx from xszz_shztb a,view_xsjbxx c where xmdm in ");
			sb.append(" (select xmdm from xszz_zzxmb where shjb='三级审核' and xysh='是' ) ");
			sb.append(" and xysh='未审核'  and a.xh=c.xh  and (fdysh='通过' or bzrsh='通过') ");
			sb.append(" and c.xydm='"+bmdm+"' ");
			
		}
		
		if(fdyqx ){
			if(blog){
				sb.append("union all");
			}
			//一级审核
			sb.append(" select xmdm, xn,xq,nd,'学生资助' mklx from xszz_shztb a,view_xsjbxx c where xmdm in ");	
			sb.append(" (select xmdm from xszz_zzxmb where shjb='一级审核' and fdysh='是' ) ");
			sb.append(" and fdysh='未审核' and a.xh=c.xh ");
			sb.append(" and exists(select 1 from fdybjb b where c.bjdm=b.bjdm and b.zgh='" + yhm + "')");
			blog=true;
		}
		
		if(bzrqx){
			if(blog){
				sb.append("union all");
			}
			//一级审核
			sb.append(" select xmdm, xn,xq,nd,'学生资助' mklx from xszz_shztb a,view_xsjbxx c  where xmdm in ");	
			sb.append(" (select xmdm from xszz_zzxmb where shjb='一级审核' and bzrsh='是' ) ");
			sb.append(" and bzrsh='未审核' and a.xh=c.xh ");
			sb.append("  and exists(select 1 from  bzrbbb b where c.bjdm=b.bjdm and b.zgh='" + yhm + "') ");
			blog=true;
		}
		
		if("admin".equalsIgnoreCase(type) || "xx".equalsIgnoreCase(type)){
			if(blog){
				sb.append("union all");
			}
			//一级审核
			sb.append(" select xmdm, xn,xq,nd,'学生资助' mklx from xszz_shztb where xmdm in ");	
			sb.append(" ( select xmdm from xszz_zzxmb where shjb='一级审核' and xxsh='是'  ) ");
			sb.append("  and xxsh='未审核' ");
			sb.append("union all");
			//两级审核
			sb.append(" select xmdm, xn,xq,nd,'学生资助' mklx from xszz_shztb b where xmdm in ");	
			sb.append(" (select xmdm from xszz_zzxmb where shjb='两级审核' and (bzrsh='是' and b.bzrsh='通过' or xysh='是' and b.xysh='通过' or  fdysh='是' and b.fdysh='通过' ) )");
			sb.append("  and xxsh='未审核' ");
			sb.append("union all");
			//三级审核
			sb.append(" select xmdm, xn,xq,nd,'学生资助' mklx from xszz_shztb b where xmdm in ");	
			sb.append(" (select xmdm from xszz_zzxmb where shjb='三级审核' and xysh='是' and b.xysh='通过'  ) ");
			sb.append("  and xxsh='未审核' ");
			
		}
		// =======================根据权限拼接SQL  END==========================
		
		StringBuilder sql=new StringBuilder();
		sql.append(" select distinct(a.xmdm) dm,b.xmmc mc,'学生资助'lb from ("+sb.toString()+")a,xszz_zzxmb b where a.xmdm=b.xmdm ");
		
		return  dao.getList(sql.toString(), new String[]{}, new String[]{"dm","mc","lb"});
	}
	
	/**
	 * 文件管理 获取接收人
	 * @return
	 */
	public List<HashMap<String, String>> getWjglSjr(User user){
		DAO dao=DAO.getInstance();
		StringBuilder sb=new StringBuilder();
		sb.append(" select wjh,wjm,jsr,wjsclj,wjffbm from wjsc_scwjxxb where wjjssj is  null and jsr like '%,"+user.getUserName()+"' ");
		sb.append(" or jsr like '"+user.getUserName()+",%' or jsr like '%,"+user.getUserName()+",%' ");
		return dao.getList(sb.toString(), new String[]{}, new String[]{"wjh","wjm","jsr","wjsclj","wjffbm"});
	}
	
	public List<String[]>getGwglDbsx(SyszForm myForm,User user) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		//获取接收人
		List<HashMap<String, String>> wjglList=getWjglSjr(user);
		List<HashMap<String,String>>wjList=new ArrayList<HashMap<String,String>>();
		MakeQuery mQuery=new MakeQuery();
		String[]colList={};
		String[]colLikeList={"wjh","wjm"};
		mQuery.makeQuery(colList, colLikeList, myForm);
		for(int i=0;i<wjglList.size();i++){
			//
			HashMap<String,String>wjjsrMap=wjglList.get(i);
			String[]jsr=wjjsrMap.get("jsr").split(",");
			for(int j=0;j<jsr.length;j++){
				if(user.getUserName().equalsIgnoreCase(jsr[j])
						&& myForm.getXmdm().equalsIgnoreCase(wjjsrMap.get("wjffbm"))){
					HashMap<String,String>wjMap=new HashMap<String,String>();
					wjMap.put("wjh", wjjsrMap.get("wjh"));
					wjList.add(wjMap);
					break;
				}
			}
		}
		
		StringBuilder sql=new StringBuilder();
		sql.append(" select rownum r,wjh,wjm,wjffsj from  wjsc_scwjxxb " );
		sql.append(mQuery.getQueryString());
		sql.append(" and  wjffbm='"+myForm.getXmdm()+"' ");
		for(int i=0;i<wjList.size();i++){
			HashMap<String,String>wjMap=wjList.get(i);
			if(i==0 && i==wjList.size()-1){
				sql.append(" and (wjh='"+wjMap.get("wjh")+"') ") ;
			}else if(i==0){
				sql.append(" and (wjh='"+wjMap.get("wjh")+"' ") ;
			}else if(i==wjList.size()-1){
				sql.append(" or wjh='"+wjMap.get("wjh")+"' )");
			}else {
				sql.append(" or wjh='"+wjMap.get("wjh")+"' ") ;
			}
			
		}
	
	 	return CommonQueryDAO.commonQuery(sql.toString(), "", mQuery.getInputList(),
				new String[] {"wjh" , "wjm", "wjffsj"}, myForm);
	}
	
	public List<HashMap<String,String>>getWjmcList(User user){
		//获取接收人
		List<HashMap<String, String>> wjglList=getWjglSjr(user);
		List<HashMap<String,String>>wjList=new ArrayList<HashMap<String,String>>();
		for(int i=0;i<wjglList.size();i++){
			//
			HashMap<String,String>wjjsrMap=wjglList.get(i);
			String[]jsr=wjjsrMap.get("jsr").split(",");
			for(int j=0;j<jsr.length;j++){
				if(user.getUserName().equalsIgnoreCase(jsr[j])){
					HashMap<String,String>wjMap=new HashMap<String,String>();
					wjMap.put("wjh", wjjsrMap.get("wjh"));
					wjMap.put("xmmc", wjjsrMap.get("wjm"));
					wjMap.put("wjsclj", wjjsrMap.get("wjsclj"));
					wjMap.put("dblx", "未接收");
					wjMap.put("mklx", "文件管理");
					wjList.add(wjMap);
					break;
				}
			}
		}
		return wjList;
	}
	
	
	public List<HashMap<String,String>>getGwglDb(){
		DAO dao=DAO.getInstance();
		String sql=" select a.bmdm dm,a.bmmc mc,'文件管理'lb from zxbz_xxbmdm  a where a.bmlb='5' and a.bmfdm is null union";
		sql+=" select a.bmdm dm,a.bmmc mc,'文件管理'lb from zxbz_xxbmdm a ,yhb b  where a.bmdm=b.szbm ";
		return dao.getList(sql, new String[]{}, new String[]{"dm","mc","lb"});

	}
	
	/**
	 * 学生申请项目列表
	 * (暂时只有学生资助信息,其他模块等待添加)  
	 * author 潇洒的裘
	 * @param mklx
	 * @param userName
	 * @return List<HashMap<String, String>> 
	 */
	public List<HashMap<String, String>> getXmList(String mklx, String userName) {

		List<HashMap<String, String>> xmList = new ArrayList<HashMap<String, String>>();
		if ("学生资助".equalsIgnoreCase(mklx)) {
			xmList.addAll(getZzXmList(mklx, userName));
		}
		
		return xmList;
	}
	
	/**
	 * 学生资助 学生申请项目列表
	 * (暂时只有学生资助信息,其他模块等待添加)  
	 * author 潇洒的裘
	 * @param mklx
	 * @param userName
	 * @return List<HashMap<String, String>> 
	 */
	public List<HashMap<String, String>> getZzXmList(String mklx, String userName) {

		StringBuilder sb = new StringBuilder();
		DAO dao = DAO.getInstance();

		sb.append("select xmdm dm,xmmc mc,mklx from ( select * from ( select  distinct(b.xmdm)xmdm,b.xmmc ,'学生资助' mklx ");
		sb.append(" from xszz_shztb a,xszz_zzxmb b where a.xmdm=b.xmdm and  xh='"
						+ userName + "' ");
		sb.append(" )order by xmdm ) ");
		
		//判断模块类型（资助、评奖等）
		if (!"".equalsIgnoreCase(mklx)) {
			sb.append(" where  mklx='" + mklx + "'  ");
		}
	
		return dao.getList(sb.toString(), new String[] {}, new String[] {
				"mklx", "dm", "mc" });
	}
	
	/**
	 * 显示项目中是否显示开关 批量保存功能 
	 * author 潇洒的裘
	 * @param myForm
	 * @return boolean
	 * @throws Exception
	 */
	public boolean updateSfxs(SyszForm myForm) throws Exception {

		DAO dao = DAO.getInstance();

		boolean flag = false;
		// 项目代码
		String[] xmdm = myForm.getXmdmArr();
		// 显示开关
		String[] sfxs = myForm.getSfxsArr();

		StringBuffer sql = new StringBuffer();

		// 判断是否有项目 需要保存
		if (xmdm != null && xmdm.length > 0) {

			String[] arr_sql = new String[xmdm.length];

			for (int i = 0; i < xmdm.length; i++) {
				sql = new StringBuffer();
				// 首页设置 显示项目表
				sql.append("update xg_sysz_xsxmb set ");
				sql.append("sfxs = '" + sfxs[i] + "' ");
				sql.append("where xmdm = '" + xmdm[i] + "' ");

				arr_sql[i] = sql.toString();
			}

			flag = dao.saveArrDate(arr_sql);
		}

		return flag;
	}

	/**
	 * 获取模块类别
	 * @param user
	 * @return List<HashMap<String,String>>
	 */
	public List<HashMap<String,String>>getMklb(String userName){
		DAO dao=DAO.getInstance();
		StringBuilder sb=new StringBuilder();
		sb.append(" select mkmc dm,mkmc mc from (select * from yhqxb b ");
		sb.append(" where yhm = ? and exists (select gnmkdm, dyym  from (select gnmkdm, dyym ");
		sb.append("  from gnmkdmb b where exists  (select 1 from xg_sysz_dbsxnrb a where a.dyym = b.dyym))a ");
		sb.append(" where a.gnmkdm=b.gnmkdm))a,xg_sysz_dbsxnrb b where a.gnmkdm=b.mkdm order by xssx ");
		return dao.getList(sb.toString(), new String[]{userName}, new String[]{"dm","mc"});
	}
	
	/** 获取模块类别
	 * @param user
	 * @return List<HashMap<String,String>>
	 */
	public List<HashMap<String,String>>getXssqMklb(){
		DAO dao=DAO.getInstance();
		StringBuilder sb=new StringBuilder();
		sb.append(" select mkmc dm,mkmc mc from (select * from yhzqxb b  where zdm='6727'  ");
		sb.append("  and exists (select gnmkdm, dyym  from (select gnmkdm, dyym ");
		sb.append("  from gnmkdmb b where exists  (select 1 from xg_sysz_wdsqnrb a where a.dyym = b.dyym))a ");
		sb.append(" where a.gnmkdm=b.gnmkdm))a,xg_sysz_wdsqnrb b where a.gnmkdm=b.mkdm order by xssx ");
		return dao.getList(sb.toString(), new String[]{}, new String[]{"dm","mc"});
	}
	
	public boolean deleteFile(String filePath) throws Exception{
		DAO dao=DAO.getInstance();
		String sql=" delete from xg_xtwh_xzzqb where filepath=? ";
		return dao.runUpdate(sql,new String[]{filePath});
	}
	
	/**
	 * 获取 评奖评优待办事项(通用)
	 * @param user 
	 * @return  List<HashMap<String,String>>
	 * @author qlj
	 */
	public List<HashMap<String,String>>getPjpyDbxm(SyszForm myForm){
		
		DAO dao = DAO.getInstance();
		//用户名
		StringBuilder sql=new StringBuilder();
		
//		 评奖学年
		String pjxn = PjxtszModel.pjxtszModel.getPjxn();
		// 评奖学期
		String pjxq = PjxtszModel.pjxtszModel.getPjxq();
		// 评奖年度
		String pjnd = PjxtszModel.pjxtszModel.getPjnd();
		// 用户名
		String userName = myForm.getUserName();

		sql.append(" select a.xmdm dm,xmmc mc,'评奖评优' lb ");
		sql.append(" from xg_pjpy_pjxmshb a ");
		sql.append(" left join xg_xtwh_spgw b on a.xtgwid = b.id ");
		sql.append(" left join xg_pjpy_pjxmwhb c on a.xmdm=c.xmdm ");
		sql.append(" left join xg_xtwh_spgwyh d on b.id=d.spgw ");
		sql.append(" where shzt = '未审核' and d.spyh='"+userName+"' group by a.xmdm,c.xmmc ");

		return  dao.getList(sql.toString(),new String[]{},new String[]{"lb","dm","mc"});
	}
	
	/**
	 * 获取 评奖评优待办事项(通用)
	 * @param user 
	 * @return  List<HashMap<String,String>>
	 * @author qlj
	 * @throws Exception 
	 */
	public List<String[]>getPjpyDbsx(SyszForm myForm) throws Exception{
		
		DAO dao = DAO.getInstance();

		StringBuilder sql=new StringBuilder();
		
//		 评奖学年
		String pjxn = PjxtszModel.pjxtszModel.getPjxn();
		// 评奖学期
		String pjxq = PjxtszModel.pjxtszModel.getPjxq();
		// 评奖年度
		String pjnd = PjxtszModel.pjxtszModel.getPjnd();
		// 用户名
		String userName = myForm.getUserName();

		//sql.append("( ");
		sql.append(" select  rownum r ,a.* from (select '评奖评优' mklx, '未审核' dblx,xmdm,xmmc,");
		sql.append(" to_char(WM_CONCAT(gwmc||'('||xsh||')')) shxx,to_char(WM_CONCAT(xtgwid)) gwxx,to_char(WM_CONCAT(gwjb)) jbxx from( ");
		sql.append(" select xmdm,xmmc,pjxn,pjxq,pjnd,xsh,xtgwid,gwmc,xh gwjb from ");
		sql.append(" ( select b.xmdm,b.xmmc, pjxn, pjxq, pjnd,count(1)xsh,xtgwid,gwmc,lcid ");
		sql.append(" from (select * from xg_xtwh_spgwyh where spyh = '"+userName+"') a, ");
		sql.append(" (select a.*, b.mc gwmc,c.xmmc,c.lcid ");
		sql.append(" from xg_pjpy_pjxmshb a ");
		sql.append(" left join xg_xtwh_spgw b on a.xtgwid = b.id ");
		sql.append(" left join xg_pjpy_pjxmwhb c on a.xmdm=c.xmdm ");
		sql.append(" where shzt = '未审核') b ");
		sql.append(" where a.spgw = b.xtgwid  ");
		sql.append(" group by xmdm,xmmc, pjxn, pjxq, pjnd,xtgwid,gwmc,lcid)a ,");
		sql.append(" xg_xtwh_spbz b where a.xtgwid= b.spgw and a.lcid=b.splc)where 1=1  ");
		if(!Base.isNull(myForm.getXn())){
			sql.append(" and pjxn='"+myForm.getXn()+"' ");
		}
		if(!Base.isNull(myForm.getXq())){
			sql.append(" and pjxq='"+myForm.getXq()+"'");
		}
		if(!Base.isNull(myForm.getNd())){
			sql.append(" and pjnd='"+myForm.getNd()+"'");
		}
		if(!Base.isNull(myForm.getXmdm())){
			sql.append(" and xmdm='"+myForm.getXmdm()+"'");
		}
		sql.append(" group by xmdm,xmmc, pjxn, pjxq, pjnd )a ");

		return CommonQueryDAO.commonQuery(sql.toString(), "", new String[] {},
				new String[] { "mklx", "xmdm", "xmmc", "dblx", "shxx", "gwxx",
			"jbxx"  }, myForm);
		
	}
	
	/**
	 * 获取学生评奖申请信息
	 * author 潇洒的裘
	 * @param myForm
	 * @return List<String[]>
	 * @throws Exception
	 */
	public List<String[]> getXspjsqInfo(SyszForm myForm) throws Exception {

		StringBuilder sql = new StringBuilder();
		String xh=myForm.getUserName();
		// 评奖学年
		String pjxn = PjxtszModel.pjxtszModel.getPjxn();
		// 评奖学期
		String pjxq = PjxtszModel.pjxtszModel.getPjxq();
		// 评奖年度
		String pjnd = PjxtszModel.pjxtszModel.getPjnd();

		// ===========================评奖 申请结果====================================
		sql.append(" select rownum r,a.*,shzt shjg from ( ");
		sql.append(" select sqzqxx,a.xmdm,a.xmmc,a.lcid,max(shjb) shjb, ");
		sql.append(" to_char(WM_CONCAT(a.gwmc || '(' || a.shzt || ')')) shzt  ");
		sql.append(" from (select a.xnxx||a.xqxx||a.ndxx sqzqxx,a.* from( ");
		sql.append(" select ");
		sql.append(" case when a.pjxn<>'无' then a.pjxn||'学年' else '' end xnxx, ");
		sql.append(" case when a.pjxq<>'无' then a.pjxq||'学期' else '' end xqxx, ");
		sql.append(" case when a.pjnd<>'无' then a.pjnd||'年度' else '' end ndxx, ");
		sql.append(" a.xmdm, a.xmmc, a.shjb, a.mc gwmc, a.shzt,a.lcid from xg_view_pjpy_xmsh a  ");
		sql.append(" where xh = '"+xh+"' and (a.pjxn = '"+pjxn+"' or  ");
		sql.append(" (a.pjxn = '"+pjxn+"' and a.pjxq = '"+pjxq+"') or a.pjnd = '"+pjnd+"') ");
		sql.append(" order by xmdm, to_number(shjb)) a ");
		sql.append(" ) a group by a.sqzqxx,a.xmdm,a.xmmc,a.lcid) a ");
		// ===========================评奖 申请结果end====================================
		
		return CommonQueryDAO.commonQuery(sql.toString(), "", new String[] {},
				new String[] { "xmmc", "sqzqxx", "shjg" }, myForm);
	}
	
	/**
	 * 获取学生违纪信息
	 * author 潇洒的裘
	 * @param myForm
	 * @return List<String[]>
	 * @throws Exception
	 */
	public List<String[]> getXswjxxInfo(SyszForm myForm) throws Exception {

		StringBuilder sql = new StringBuilder();
		String xh=myForm.getUserName();

		// ===========================评奖 申请结果====================================
		sql.append(" select rownum r,xmmc,sqzqxx, shjg,pkValue from (  ");
		sql.append(" select '违纪处分' mklx,  ");
		sql.append(" '违纪信息' xmmc, ");
		sql.append(" xh||xn||nd||sbsj pkValue, ");
		sql.append(" to_char(to_date(wjsj, 'yyyymmdd'), 'yyyy') || '年' ||  ");
		sql.append(" to_char(to_date(wjsj, 'yyyymmdd'), 'mm') ||'月' ||  ");
		sql.append(" to_char(to_date(wjsj, 'yyyymmdd'), 'dd') || '日' sqzqxx,  ");
		sql.append(" '由于'||cfyymc||'受到'||cflbmc||'处分' shjg ");
		sql.append("  from view_wjcf a   where xh = '"+xh+"'  ");
		//sql.append(" and xn = (select dqxn from xtszb where rownum = 1) ");
		sql.append("  and sfqs = '否'  ) a ");
		// ===========================评奖 申请结果end====================================
		
		return CommonQueryDAO.commonQuery(sql.toString(), "", new String[] {},
				new String[] { "xmmc", "sqzqxx", "shjg","pkValue" }, myForm);
	}
	
	/**
	 * 根据首页调查ID获取调查内容
	 * @param myForm
	 * @return
	 * @throws Exception
	 */
	public HashMap<String,String>getSydcInfo(SyszForm myForm) throws Exception {
		DAO dao=DAO.getInstance();
		StringBuilder sql = new StringBuilder();
		sql.append(" select dcnr,(substr(a.dcnr,0,25)|| ");
		sql.append(" case when length(a.dcnr)>25 then '...' end) ");
		sql.append(" dcinfo from xg_xtwh_sydcb a where dcid=? ");
		return dao.getMap(sql.toString(), new String[]{myForm.getDcid()}, new String[]{"dcnr","dcinfo"});
	}
	

	// =======================以上made by 潇洒的裘=====================
}
