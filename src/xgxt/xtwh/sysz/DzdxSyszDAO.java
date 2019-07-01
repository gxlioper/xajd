package xgxt.xtwh.sysz;

import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.comm.homepage.DzdxHomePageDAO;
import xgxt.utils.CommonQueryDAO;

public class DzdxSyszDAO {
	/**
	 * 学生资助待办事项信息(教师)
     * author qlj
	 * @param myForm
	 * @return List<String[]>
	 * @throws Exception
	 */
	public List<String[]> getZzpjDbsx(SyszForm myForm) throws Exception {
		
		
		DzdxHomePageDAO dzdxDAO=new DzdxHomePageDAO();
		
		String mklx=myForm.getGnmklx();
		
		StringBuilder xmlb=dzdxDAO.getXmlbQuery(mklx);
		
		String mkmc="";
		String ljdz="";
		if("zz".equalsIgnoreCase(mklx)){
			mkmc="学生资助";
			ljdz="commXszz.do?method=xmshManage&mklx=zz&widthType=dbsx&go=go&xmdm=";
		}else if("pj".equalsIgnoreCase(mklx)){
			mkmc="评奖评优";
			ljdz="commXszz.do?method=xmshManage&mklx=pj&widthType=dbsx&go=go&xmdm=";
		}
		
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
			sb.append(" (select xmdm from xszz_zzxmb where shjb='一级审核' and xysh='是' "+xmlb.toString()+") ");
			sb.append(" and xysh='未审核'  and c.xydm='"+bmdm+"' ");
			//两级审核
			sb.append(" union all ");
			sb.append(" select xmdm, xn,xq,nd,substr(sqsj,0,4)sqn from xszz_shztb a,view_xsjbxx c where xmdm in ");
			sb.append(" (select xmdm from xszz_zzxmb where shjb='两级审核' and xysh='是' "+xmlb.toString()+") ");
			sb.append(" and xysh='未审核'  and a.xh=c.xh  and (fdysh='通过' or bzrsh='通过') ");
			sb.append(" and c.xydm='"+bmdm+"' ");
			//两级审核
			sb.append(" union all ");
			sb.append(" select xmdm, xn,xq,nd,substr(sqsj,0,4)sqn from xszz_shztb a,view_xsjbxx c where xmdm in ");
			sb.append(" (select xmdm from xszz_zzxmb where shjb='三级审核' and xysh='是' "+xmlb.toString()+") ");
			sb.append(" and xysh='未审核'  and a.xh=c.xh  and (fdysh='通过' or bzrsh='通过') ");
			sb.append(" and c.xydm='"+bmdm+"' ");
			
		}
		
		if(fdyqx ){
			if(blog){
				sb.append("union all");
			}
			//一级审核
			sb.append(" select xmdm, xn,xq,nd,substr(sqsj,0,4)sqn from xszz_shztb a,view_xsjbxx c where xmdm in ");	
			sb.append(" (select xmdm from xszz_zzxmb where shjb='一级审核' and fdysh='是' "+xmlb.toString()+" ) ");
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
			sb.append(" (select xmdm from xszz_zzxmb where shjb='一级审核' and bzrsh='是' "+xmlb.toString()+" ) ");
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
			sb.append(" ( select xmdm from xszz_zzxmb where shjb='一级审核' and xxsh='是' "+xmlb.toString()+" ) ");
			sb.append("  and xxsh='未审核' ");
			sb.append("union all");
			//两级审核
			sb.append(" select xmdm, xn,xq,nd,substr(sqsj,0,4)sqn from xszz_shztb b where xmdm in ");	
			sb.append(" (select xmdm from xszz_zzxmb where shjb='两级审核' "+xmlb.toString()+" and (bzrsh='是' and b.bzrsh='通过' or xysh='是' and b.xysh='通过' or  fdysh='是' and b.fdysh='通过' ) )");
			sb.append("  and xxsh='未审核' ");
			sb.append("union all");
			//三级审核
			sb.append(" select xmdm, xn,xq,nd,substr(sqsj,0,4)sqn from xszz_shztb b where xmdm in ");	
			sb.append(" (select xmdm from xszz_zzxmb where shjb='三级审核' and xysh='是' and b.xysh='通过' "+xmlb.toString()+" ) ");
			sb.append("  and xxsh='未审核' ");
			
		}
		
		StringBuilder  sql=new StringBuilder();
		
	    sql.append("select rownum r,xmdm,xsh,xn,xq,nd,sqn,xmmc,sqzq,case when sqn=dqnd then '"+ljdz+"'||xmdm else '#' end ljdz from (");
		sql.append("select a.*,b.dqnd from (select  a.xmdm, a.xsh,a.xn,a.xq,a.nd,a.sqn,('【"+mkmc+"】'||b.xmmc)xmmc,b.sqzq from( select xmdm,count(1) xsh, xn,xq,nd,sqn from ("+sb.toString()+") group by xmdm,xn,xq,nd,sqn)a,xszz_zzxmb b where a.xmdm=b.xmdm)a left join xtszb b on 1=1 )a");
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
	 * 学生资助评奖待办事项列表(教师)
     * author qlj
	 * @param myForm
	 * @return List<String[]>
	 * @throws Exception
	 */
	public List<HashMap<String,String>> getZzpjList(SyszForm myForm) throws Exception {
		
		
		DzdxHomePageDAO dzdxDAO=new DzdxHomePageDAO();
		
		DAO dao=DAO.getInstance();
		
		String mklx=myForm.getGnmklx();
		
		StringBuilder xmlb=dzdxDAO.getXmlbQuery(mklx);
		
		String mkmc="";
		
		String bmdm=myForm.getUserDep();
		
		String type=myForm.getUserType();
		
		String yhm=myForm.getUserName();
		
		boolean fdyQx=myForm.getFdyQx();
		
		boolean bzrQx=myForm.getBzrQx();
		
		if("zz".equalsIgnoreCase(mklx)){
			mkmc="学生资助";
		}else if("pj".equalsIgnoreCase(mklx)){
			mkmc="评奖评优";
		}
		

		boolean isXy = false;

		// =======================根据权限拼接SQL==========================
		if ("xy".equalsIgnoreCase(myForm.getUserType())) {
			isXy = true;
		}

		StringBuilder sb = new StringBuilder();
 
		boolean blog=false;
		if(isXy){
			blog=true;
			//一级审核
			sb.append(" select xmdm, xn,xq,nd,'"+mkmc+"' mklx from xszz_shztb a,view_xsjbxx c where xmdm in ");
			sb.append(" (select xmdm from xszz_zzxmb where shjb='一级审核' and xysh='是' "+xmlb.toString()+") ");
			sb.append(" and xysh='未审核'  and c.xydm='"+bmdm+"' ");
			//两级审核
			sb.append(" union all ");
			sb.append(" select xmdm, xn,xq,nd,'"+mkmc+"' mklx from xszz_shztb a,view_xsjbxx c where xmdm in ");
			sb.append(" (select xmdm from xszz_zzxmb where shjb='两级审核' and xysh='是' "+xmlb.toString()+") ");
			sb.append(" and xysh='未审核'  and a.xh=c.xh  and (fdysh='通过' or bzrsh='通过') ");
			sb.append(" and c.xydm='"+bmdm+"' ");
			//两级审核
			sb.append(" union all ");
			sb.append(" select xmdm, xn,xq,nd,'"+mkmc+"' mklx from xszz_shztb a,view_xsjbxx c where xmdm in ");
			sb.append(" (select xmdm from xszz_zzxmb where shjb='三级审核' and xysh='是' "+xmlb.toString()+") ");
			sb.append(" and xysh='未审核'  and a.xh=c.xh  and (fdysh='通过' or bzrsh='通过') ");
			sb.append(" and c.xydm='"+bmdm+"' ");
			
		}
		
		if(fdyQx){
			if(blog){
				sb.append("union all");
			}
			//一级审核
			sb.append(" select xmdm, xn,xq,nd,'"+mkmc+"' mklx from xszz_shztb a,view_xsjbxx c where xmdm in ");	
			sb.append(" (select xmdm from xszz_zzxmb where shjb='一级审核' and fdysh='是' "+xmlb.toString()+") ");
			sb.append(" and fdysh='未审核' and a.xh=c.xh ");
			sb.append(" and exists(select 1 from fdybjb b where c.bjdm=b.bjdm and b.zgh='" + yhm + "')");
			blog=true;
		}
		
		if(bzrQx){
			if(blog){
				sb.append("union all");
			}
			//一级审核
			sb.append(" select xmdm, xn,xq,nd,'"+mkmc+"' mklx from xszz_shztb a,view_xsjbxx c  where xmdm in ");	
			sb.append(" (select xmdm from xszz_zzxmb where shjb='一级审核' and bzrsh='是' "+xmlb.toString()+") ");
			sb.append(" and bzrsh='未审核' and a.xh=c.xh ");
			sb.append("  and exists(select 1 from  bzrbbb b where c.bjdm=b.bjdm and b.zgh='" + yhm + "') ");
			blog=true;
		}
		
		if("admin".equalsIgnoreCase(type) || "xx".equalsIgnoreCase(type)){
			if(blog){
				sb.append("union all");
			}
			//一级审核
			sb.append(" select xmdm, xn,xq,nd,'"+mkmc+"' mklx from xszz_shztb where xmdm in ");	
			sb.append(" ( select xmdm from xszz_zzxmb where shjb='一级审核' and xxsh='是' "+xmlb.toString()+" ) ");
			sb.append("  and xxsh='未审核' ");
			sb.append("union all");
			//两级审核
			sb.append(" select xmdm, xn,xq,nd,'"+mkmc+"' mklx from xszz_shztb b where xmdm in ");	
			sb.append(" (select xmdm from xszz_zzxmb where shjb='两级审核'  "+xmlb.toString()+" and  (bzrsh='是' and b.bzrsh='通过' or xysh='是' and b.xysh='通过' or  fdysh='是' and b.fdysh='通过' ) )");
			sb.append("  and xxsh='未审核' ");
			sb.append("union all");
			//三级审核
			sb.append(" select xmdm, xn,xq,nd,'"+mkmc+"' mklx from xszz_shztb b where xmdm in ");	
			sb.append(" (select xmdm from xszz_zzxmb where shjb='三级审核' and xysh='是' and b.xysh='通过'  "+xmlb.toString()+") ");
			sb.append("  and xxsh='未审核' ");
			
		}
		// =======================根据权限拼接SQL  END==========================
		
		StringBuilder sql=new StringBuilder();
		sql.append(" select distinct(a.xmdm) dm,b.xmmc mc,a.mklx lb from ("+sb.toString()+")a,xszz_zzxmb b where a.xmdm=b.xmdm ");
		return dao.getList(sql.toString(), new String[]{}, new String[]{"dm","mc","lb"});
	}

	/** 获取学生资助申请项目信息
	 * author qlj
     * (暂时只有学生资助信息,其他模块等待添加) 
	 * @param myForm
	 * @return List<String[]>
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getSqzzxxList(SyszForm myForm)
			throws Exception {

		StringBuilder sb = new StringBuilder();
		DzdxHomePageDAO dzdxDAO=new DzdxHomePageDAO();
		StringBuilder xmlb=dzdxDAO.getXmlbQuery(myForm.getGnmklx());
		DAO dao = DAO.getInstance();
		String mkmc="";
		String mklx=myForm.getGnmklx();
		if("zz".equalsIgnoreCase(mklx)){
			mkmc="学生资助";
		}else if("pj".equalsIgnoreCase(mklx)){
			mkmc="评奖评优";
		}
		sb.append("  select dm,mc,lb,xmlb from ( select  distinct(b.xmdm)dm,b.xmmc mc ,'"+mkmc+"' lb,xmlb ");
		sb.append(" from xszz_shztb a,xszz_zzxmb b where a.xmdm=b.xmdm and  xh='"
						+ myForm.getUserName() + "' )where 1=1 "+xmlb);
		sb.append("order by dm  ");
		return dao.getList(sb.toString(), new String[] {}, new String[] {
				"lb", "dm", "mc" });
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
		
		DzdxHomePageDAO dzdxDAO=new DzdxHomePageDAO();
		StringBuilder sb = new StringBuilder();
		String mkmc="";
		
		StringBuilder xmlb=dzdxDAO.getXmlbQuery(myForm.getGnmklx());
		String mklx=myForm.getGnmklx();
		if("zz".equalsIgnoreCase(mklx)){
			mkmc="学生资助";
		}else if("pj".equalsIgnoreCase(mklx)){
			mkmc="评奖评优";
		}
		
		// ===========================学生资助 申请结果====================================
		sb.append(" select * from ( select rownum r, b.xmdm,b.xmlb,('【"+mkmc+"】'||b.xmmc)xmmc, ");
		
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
		sb.append(" ) where 1=1 "+xmlb+" order by xmdm ");
		// ===========================学生资助 申请结果end====================================
		System.out.println(sb);
		return CommonQueryDAO.commonQuery(sb.toString(), "", new String[] {},
				new String[] { "xmmc", "sqzqxx", "shjg" }, myForm);
	}
}
