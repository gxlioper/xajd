/**
 * @部门:学工产品事业部
 * @日期：2013-8-7 下午04:40:57 
 */  
package com.zfsoft.xgxt.rcsw.rcxwwh.rcxwjg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.search.SearchModel;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.CommonQueryDAO;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 日常行为管理模块
 * @类功能描述: 日常行为结果 
 * @作者：dlq [工号：995]
 * @时间： 2013-8-7 下午04:40:57 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class RcxwjgDao extends SuperDAOImpl<RcxwjgForm> {

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		// TODO 自动生成方法存根
		super.setTableName("xg_rcsw_rcxwjg");
		super.setKey("id");
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(RcxwjgForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(RcxwjgForm t, User user)
			throws Exception {
		// TODO 自动生成方法存根
		
		//生成高级查询相关条件、条件值 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t1", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		
		StringBuilder sql = new StringBuilder();
		/*sql.append("select t1.* from (select a.id,a.xh,a.xn,a.rcxwlbdldm,a.rcxwlbdm,a.rcxwjlsj, ");
		sql.append("a.bz,c.xm,c.xb,c.bjmc,c.bjdm,c.xydm,d.rcxwlbdlmc,e.rcxwlbfz,e.rcxwlbmc,f.xqmc xqmc,f.xqdm,a.xq, ");
		//sql.append("decode(a.xq,'01','春','02','秋',a.xq ) xq, ");
		sql.append(" c.lxdh,c.zydm,c.nj,");
		sql.append("decode(b.shzt,'0','未审核','1','通过','2','不通过','','无需审核',b.shzt) shztmc ");
		sql.append(" from xg_rcsw_rcxwjg a left join xg_rcsw_rcxwxxwh b on a.id = b.id ");
		sql.append(" left join view_xsxxb c on a.xh = c.xh ");
		sql.append(" left join  xg_rcsw_rcxwdbdlb d on a.rcxwlbdldm = d.rcxwlbdldm  ");
		sql.append(" left join xg_rcsw_rcxwlbdmb e on a.rcxwlbdm = e.rcxwlbdm ");
		sql.append(" left join xqdzb f on a.xq = f.xqdm ) t1 where 1=1 ");*/
		 // 
		
		sql.append("select * from VIEW_NEW_DC_RCSW_RCXWJG t1 where 1=1 ");
		
		
		sql.append(searchTjByUser);
		sql.append(searchTj);
		if(Base.xxdm.equalsIgnoreCase("12867")){
			StringBuilder sql1 = new StringBuilder();
			sql1.append(" update xg_rcsw_rcxwjg set rcxwlbdm = rcxwlbdldm where rcxwlbdm is null");
			dao.runUpdate(sql1.toString(), new String[]{});
		}
		return getPageList(t, sql.toString(), inputV);
	
	}
	
	public List<HashMap<String, String>> getXwdlfList(RcxwjgForm t, User user,List<HashMap<String,String>> xmdlList)
	throws Exception {
		// TODO 自动生成方法存根
		
		//生成高级查询相关条件、条件值 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t1", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from(select t.*, i.yqdm, c.xm, c.xb, c.bjmc, c.nj,c.zydm,c.zymc,c.bjdm, c.xydm, c.xymc from(select ");
		for (int i = 0 , j = xmdlList.size() ; i < j ; i++){
			sql.append("case when nvl(sum(fz").append(i).append(")+"+xmdlList.get(i).get("rcxwdljcf")+",0)>"+xmdlList.get(i).get("rcxwdlfssx")+" then "+xmdlList.get(i).get("rcxwdlfssx")+" else  ");
			sql.append("nvl(sum(fz").append(i).append(")+"+xmdlList.get(i).get("rcxwdljcf")+",0) end fz").append(i).append(",");
			
		}
		sql.append(" xh,xn from ( select");
		for (int i = 0 , j = xmdlList.size() ; i < j ; i++){
			sql.append(" case when t.rcxwlbdldm='").append(xmdlList.get(i).get("rcxwlbdldm"))
			   .append("' then t.fz else '' end fz")
			   .append(i).append(",");
		}
		sql.append(" xh,xn from(select a.xh,a.xn, b.rcxwlbdldm,b.rcxwlbdlmc, c.rcxwlbmc, ");
		sql.append("(case when c.rcxwfzlx='02' then'-'||a.fz when c.rcxwfzlx='01' then a.fz else '未知类型' end) fz");
		sql.append("  from xg_rcsw_rcxwjg a ");
		sql.append("  left join xg_rcsw_rcxwdbdlb b ");
		sql.append("   	on a.rcxwlbdldm = b.rcxwlbdldm ");
		sql.append("  left join xg_rcsw_rcxwlbdmb c ");
		sql.append("    on a.rcxwlbdm = c.rcxwlbdm)t) ");
		sql.append("  group by xh,xn)t left join view_xsbfxx c on t.xh = c.xh left join xg_gygl_new_cwxxb g on t.xh=g.xh");
        sql.append("   left join xg_gygl_new_ldxxb h on h.lddm=g.lddm left join zxbz_ssyqdm i on i.yqdm=h.yqdm)t1 where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);

}
	
	public List<HashMap<String, String>> getAllList(RcxwjgForm t, User user) throws Exception {
		
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		
		StringBuilder sql = new StringBuilder();
		
		
		
		if("10355".equals(Base.xxdm)){
			sql.append("select t.*, (case  when pdszdf != 0 then '审核'  else '未填写' end) zt1, ");
			sql.append("  (case  when zhnldf != 0 then  '审核' else  '未填写' end) zt2 ");
			sql.append("  from (select a.xh, a.xb, a.xm, a.xymc, a.zymc, a.bjmc, a.nj, b.pdszdf, b.zhnldf, b.xyspdf, b.stszdf ");
			sql.append("   from (  select * from (select sum(case when t.rcxwlbdlmc = '品德素质' then  to_number(t.fz) else 0 end) pdszdf, ");
			sql.append("   sum(case  when t.rcxwlbdlmc = '综合能力' then to_number(t.fz)  else  0 end) zhnldf, ");
			sql.append("   sum(case  when t.rcxwlbdlmc = '学业水平' then to_number(t.fz)  else  0 end) xyspdf, ");
			sql.append("   sum(case  when t.rcxwlbdlmc = '身体素质' then to_number(t.fz)  else  0 end) stszdf, ");
			sql.append("   t.xh,t.xn,t.xq,t.xb,t.xydm,t.xymc,t.bjdm,t.bjmc ");
			sql.append("   from view_new_dc_rcsw_rcxwjg t group by xh,xn,xq,xb,xydm,xymc,bjdm,bjmc ) t");
			sql.append("   where 1 = 1 ");
			sql.append(searchTj);
			sql.append("                      ) b ");
			sql.append("   left join view_xsjbxx a  on a.xh = b.xh) t where 1 = 1 ");
			sql.append(searchTjByUser);


		}else{
			sql.append("select * from (");
			sql.append("select a.*,b.fz zfz from VIEW_NEW_DC_RCSW_RCXWJG a left join");
			sql.append("(select xn,xh,xq,case when to_char(sum(fz)) like '.%'then '0' ");
			sql.append("|| to_char(sum(fz)) else to_char(sum(fz)) end fz from (select *from(select ");
			sql.append("(case when c.rcxwfzlx='02' then '-'||a.fz when c.rcxwfzlx='01' then a.fz else '未知类型' end) fz,xh,xq,xn from ");
			sql.append("xg_rcsw_rcxwjg a left join xg_rcsw_rcxwlbdmb c on a.rcxwlbdm = c.rcxwlbdm ))group by xn, xq,xh)b on");
			sql.append(" a.xh=b.xh and a.xn=b.xn and a.xq=b.xq ");
			sql.append(")t where 1=1 ");
			sql.append(searchTjByUser);
			sql.append(searchTj);
		}
		return dao.getListNotOut(sql.toString(),inputV);

	}
	
	/**
	 * 
	 * 获取行为大类集合
	 * @作者：dlq [工号：995]
	 * @日期：2013-8-9 下午03:57:03
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param request
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getXwdlList(HttpServletRequest request){	
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from ( ");
		sql.append(" select b.rcxwlbdldm,b.rcxwlbdlmc, ");
		sql.append(" case when b.sqkg=1 and sysdate between to_date(nvl(b.sqkssj,'1990-01-01'),'yyyy-mm-dd') and to_date(nvl(b.sqjssj,'2200-01-01'),'yyyy-mm-dd')+1 then 'true' else 'false' end isopen ");
		sql.append(" from xg_rcsw_rcxwdbdlb b  ");
		sql.append(" ) where isopen='true' order by rcxwlbdldm ");
		
		return dao.getList(sql.toString(), new String[]{},new String[]{"rcxwlbdldm","rcxwlbdlmc"});
	}
	
	/**
	 * 
	 * 获取行为类别集合
	 * @作者：dlq [工号：995]
	 * @日期：2013-8-9 下午03:57:25
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xwdldm
	 * @param request
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getXwlbList(String xwdldm, HttpServletRequest request){
		String sql = "select rcxwlbdm,rcxwlbmc from xg_rcsw_rcxwlbdmb where rcxwlbdldm=? and sfqy='1' order by rcxwlbdm ";		
		return dao.getList(sql, new String[]{xwdldm},new String[]{"rcxwlbdm","rcxwlbmc"});
	}
	
	/**
	 * 
	 * 查看行为结果
	 * @作者：dlq [工号：995]
	 * @日期：2013-8-9 上午10:34:23
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param guid
	 * @return
	 * Map<String,String> 返回类型 
	 * @throws
	 */
	public Map<String, String> getOneXwjgList(String  xwjgId) {
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select a.id,a.xn, g.xqmc,rcxwlbmc,rcxwlbdlmc,a.fjlj,a.fjmc, ");
		sql.append(" a.rcxwjlsj,a.gfly,a.bz,decode(f.shzt,'0','未审核','1','通过','2','不通过','','无需审核',f.shzt) shztmc,d.shyj,(case when c.rcxwfzlx='01' then '+'||a.fz when c.rcxwfzlx='02' then '-'||a.fz else '未知类型' end) fz,c.rcxwlbbz,a.fssj,(select xm from yhb y where y.yhm=a.jlr) jlrxm ");
		sql.append(" from xg_rcsw_rcxwjg a left join xg_rcsw_rcxwdbdlb b on a.rcxwlbdldm = b.rcxwlbdldm ");
		sql.append(" left join xg_rcsw_rcxwlbdmb c on a.rcxwlbdm = c.rcxwlbdm ");
		sql.append(" left join  xg_xtwh_shztb d on a.id = d.ywid ");
		sql.append(" left join xg_xtwh_spgw  e on d.gwid = e.id  left join xg_rcsw_rcxwxxwh f  on a.id = f.id ");
		sql.append(" left join  xqdzb g on a.xq = g.xqdm ");
		sql.append(" where a.id = ? ");
		
		return dao.getMapNotOut(sql.toString(),new String[]{xwjgId});
	}
	
	/**
	 * 
	 * 修改行为维护信息时判断修改的行为维护是不是已经存在行为结果中
	 * @作者：dlq [工号：995]
	 * @日期：2013-8-12 下午04:23:17
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xwjgId
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean checkXwwhForxwjg(String  xwjgId) {
		boolean flag = false;
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1) num from xg_rcsw_rcxwjg a ");
		sql.append(" where a.id = ? ");
		String num = dao.getOneRs(sql.toString(),new String[]{xwjgId},"num");
		if (!"0".equalsIgnoreCase(num)) {
			flag = true;
		}
		return  flag;
	}
	
	
	/**
	 * 
	 * 查看历史行为记录信息
	 * @作者：dlq [工号：995]
	 * @日期：2013-8-9 上午10:34:23
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param guid
	 * @return
	 * Map<String,String> 返回类型 
	 * @throws
	 */
	public ArrayList<String[]>  getMoreXwjgList(RcxwjgForm model,HashMap<String,String> cspzMap) throws Exception {
		StringBuilder sql = new StringBuilder();
		String[] outputValue = null;
		String[] inputValue = new String[]{model.getXh(),model.getXh(),model.getXh()};
		if("1".equals(cspzMap.get("zq"))){
			outputValue = new String[] { "xn","rcxwlbdlmc","rcxwlbmc","fssj","fz","jlrxm" };
			sql.append(" select xn,rcxwlbdlmc,rcxwlbmc,fssj, ");
			sql.append(" case when fz like '.%' then  '0' || fz ");
			sql.append(" when fz like '-.%' then substr(fz, 0, 1) || '0' || substr(fz, 2, length(fz)) ");
			sql.append(" else fz end fz,jlrxm,xssx from ( ");
			sql.append("select xn, rcxwlbdlmc, rcxwlbmc, fssj, fz,jlrxm,xssx from (");
			sql.append(" select a.xn,b.rcxwlbdlmc, c.rcxwlbmc,a.rcxwjlsj, ");
			sql.append("(case when c.rcxwfzlx='02' then'-'||a.fz when c.rcxwfzlx='01' then '+'||a.fz else '未知类型' end) fz ,a.fssj,(select xm from yhb y where y.yhm=a.jlr) jlrxm, '0' xssx ");
			sql.append(" from xg_rcsw_rcxwjg a left join xg_rcsw_rcxwdbdlb b on a.rcxwlbdldm = b.rcxwlbdldm ");
			sql.append(" left join xg_rcsw_rcxwlbdmb c on a.rcxwlbdm = c.rcxwlbdm ");
			sql.append(" left join  xqdzb e on a.xq = e.xqdm ");
			sql.append(" where a.xh = ?");
			sql.append(" order by xn desc) ");
			sql.append(" union all ");
			sql.append(" select xn,rcxwlbdlmc,rcxwlbmc,fssj,to_char((fz+"+cspzMap.get("rcxwjcf")+"))fz,jlrxm,xssx from(");
			sql.append(" select  xn,'总分:' rcxwlbdlmc,'' rcxwlbmc,'0' fssj,");
			sql.append(" case when to_char(sum(fz)+"+cspzMap.get("rcxwjcf")+") like '.%' then '0'||to_char(sum(fz))");
			sql.append(" when to_char(sum(fz)) like '-.%' then substr(to_char(sum(fz)),0,1)||'0'||substr(to_char(sum(fz)),2,length(to_char(sum(fz))))");
			sql.append("else to_char(sum(fz)) end fz, '' jlrxm, '1' xssx from ( ");
			sql.append("select a.xn,a.rcxwlbdldm,a.rcxwlbdlmc, case when a.fz+b.rcxwdljcf> b.rcxwdlfssx then b.rcxwdlfssx else to_char(a.fz+b.rcxwdljcf) end fz from (");
			sql.append(" select xn,rcxwlbdldm,rcxwlbdlmc,sum(fz) fz from(");
			sql.append(" select a.xh,a.xn,b.rcxwdljcf,b.rcxwdlfssx, b.rcxwlbdldm,b.rcxwlbdlmc, c.rcxwlbmc, ");
			sql.append("(case when c.rcxwfzlx='02' then'-'||a.fz when c.rcxwfzlx='01' then a.fz else '未知类型' end) fz");
			sql.append("  from xg_rcsw_rcxwjg a ");
			sql.append("  left join xg_rcsw_rcxwdbdlb b ");
			sql.append("   	on a.rcxwlbdldm = b.rcxwlbdldm ");
			sql.append("  left join xg_rcsw_rcxwlbdmb c ");
			sql.append("    on a.rcxwlbdm = c.rcxwlbdm ");
			sql.append(" where a.xh = ? ");
			sql.append(" order by xn desc) group by xn, rcxwlbdldm, rcxwlbdlmc, rcxwdljcf)a left join xg_rcsw_rcxwdbdlb b on a.rcxwlbdldm=b.rcxwlbdldm)group by xn) ");
			sql.append(" union all ");
			sql.append("select xn,rcxwlbdlmc,rcxwlbmc,fssj,case when fz+rcxwdljcf> rcxwdlfssx then rcxwdlfssx else to_char(fz+rcxwdljcf) end fz,jlrxm,xssx from(");
			sql.append(" select xn,rcxwlbdlmc, rcxwdlfssx,rcxwdljcf,'' rcxwlbmc,'9999999999' fssj,");
			sql.append(" case when to_char(sum(fz)) like '.%' then '0'||to_char(sum(fz))");
			sql.append(" when to_char(sum(fz)) like '-.%' then substr(to_char(sum(fz)),0,1)||'0'||substr(to_char(sum(fz)),2,length(to_char(sum(fz))))");
			sql.append("else to_char(sum(fz)) end fz, '' jlrxm, '0' xssx from ( ");
			sql.append(" select a.xh,a.xn, b.rcxwlbdlmc,b.rcxwdljcf,b.rcxwdlfssx, c.rcxwlbmc, ");
			sql.append("(case when c.rcxwfzlx='02' then'-'||a.fz when c.rcxwfzlx='01' then a.fz else '未知类型' end) fz");
			sql.append("  from xg_rcsw_rcxwjg a ");
			sql.append("  left join xg_rcsw_rcxwdbdlb b ");
			sql.append("   	on a.rcxwlbdldm = b.rcxwlbdldm ");
			sql.append("  left join xg_rcsw_rcxwlbdmb c ");
			sql.append("    on a.rcxwlbdm = c.rcxwlbdm ");
			sql.append(" where a.xh = ? ");
			sql.append(" order by xn desc) group by xn,rcxwlbdlmc,rcxwdlfssx, rcxwdljcf) ");
			sql.append(" ) order by xn desc,xssx desc,rcxwlbdlmc,fssj desc ");
		}else{
			outputValue = new String[] { "xn","xq","rcxwlbdlmc","rcxwlbmc","fssj","fz","jlrxm" };
			sql.append(" select xn,xq, rcxwlbdlmc, rcxwlbmc, fssj, ");
			sql.append(" case when fz like '.%' then  '0' || fz ");
			sql.append(" when fz like '-.%' then substr(fz, 0, 1) || '0' || substr(fz, 2, length(fz)) ");
			sql.append(" else fz end fz,jlrxm,xssx from ( ");
			sql.append("select xn,xq, rcxwlbdlmc, rcxwlbmc, fssj, fz,jlrxm,xssx from (");
			sql.append(" select a.xn,e.xqmc xq,b.rcxwlbdlmc, c.rcxwlbmc,a.rcxwjlsj, ");
			sql.append("(case when c.rcxwfzlx='02' then'-'||a.fz when c.rcxwfzlx='01' then '+'||a.fz else '未知类型' end) fz ,a.fssj,(select xm from yhb y where y.yhm=a.jlr) jlrxm, '0' xssx ");
			sql.append(" from xg_rcsw_rcxwjg a left join xg_rcsw_rcxwdbdlb b on a.rcxwlbdldm = b.rcxwlbdldm ");
			sql.append(" left join xg_rcsw_rcxwlbdmb c on a.rcxwlbdm = c.rcxwlbdm ");
			sql.append(" left join  xqdzb e on a.xq = e.xqdm ");
			sql.append(" where a.xh = ?");
			sql.append(" order by xn desc) ");
			sql.append(" union all ");
			sql.append(" select xn,xq,rcxwlbdlmc,rcxwlbmc,fssj,to_char((fz+"+cspzMap.get("rcxwjcf")+"))fz,jlrxm,xssx from(");
			sql.append(" select  xn, xqmc xq,'总分:' rcxwlbdlmc,'' rcxwlbmc,'0' fssj,");
			sql.append(" case when to_char(sum(fz)) like '.%' then '0'||to_char(sum(fz))");
			sql.append(" when to_char(sum(fz)) like '-.%' then substr(to_char(sum(fz)),0,1)||'0'||substr(to_char(sum(fz)),2,length(to_char(sum(fz))))");
			sql.append("else to_char(sum(fz)) end fz, '' jlrxm, '1' xssx from ( ");
			sql.append("select a.xn,xq,xqmc,a.rcxwlbdldm,a.rcxwlbdlmc, case when a.fz+a.rcxwdljcf> b.rcxwdlfssx then b.rcxwdlfssx else to_char(a.fz+a.rcxwdljcf) end fz from (");
			sql.append(" select xn,xq,xqmc,rcxwdljcf,rcxwlbdldm,rcxwlbdlmc,sum(fz) fz from(");
			sql.append(" select a.xh,a.xq,a.xn,");
			if("10355".equals(Base.xxdm)){
				  sql.append("'0' rcxwdljcf");	
				}
				else{
				  sql.append("b.rcxwdljcf");
				}
			sql.append(",b.rcxwdlfssx, b.rcxwlbdldm,e.xqmc, b.rcxwlbdlmc, c.rcxwlbmc, ");
			sql.append("(case when c.rcxwfzlx='02' then'-'||a.fz when c.rcxwfzlx='01' then a.fz else '未知类型' end) fz");
			sql.append("  from xg_rcsw_rcxwjg a ");
			sql.append("  left join xg_rcsw_rcxwdbdlb b ");
			sql.append("   	on a.rcxwlbdldm = b.rcxwlbdldm ");
			sql.append("  left join xg_rcsw_rcxwlbdmb c ");
			sql.append("    on a.rcxwlbdm = c.rcxwlbdm ");
			sql.append(" left join xqdzb e ");
			sql.append("on a.xq = e.xqdm ");
			sql.append(" where a.xh = ? ");
			sql.append(" order by xn desc) group by xn ,xq, xqmc, rcxwlbdldm, rcxwlbdlmc,rcxwdljcf)a left join xg_rcsw_rcxwdbdlb b on a.rcxwlbdldm=b.rcxwlbdldm)group by xn, xqmc) ");
			sql.append(" union all ");
			sql.append("select xn,xq,rcxwlbdlmc,rcxwlbmc,fssj,case when fz+rcxwdljcf> rcxwdlfssx then rcxwdlfssx else to_char(fz+rcxwdljcf) end fz,jlrxm,xssx from(");
			sql.append(" select  xn, xqmc xq,rcxwlbdlmc,rcxwdlfssx,"); 
			if("10355".equals(Base.xxdm)){
			  sql.append("'0' rcxwdljcf");	
			}
			else{
			  sql.append("rcxwdljcf");
			}
			
			sql.append(" ,'' rcxwlbmc,'9999999999' fssj,");
			sql.append(" case when to_char(sum(fz)) like '.%' then '0'||to_char(sum(fz))");
			sql.append(" when to_char(sum(fz)) like '-.%' then substr(to_char(sum(fz)),0,1)||'0'||substr(to_char(sum(fz)),2,length(to_char(sum(fz))))");
			sql.append("else to_char(sum(fz)) end fz, '' jlrxm, '0' xssx from ( ");
			sql.append(" select a.xh,a.xq,a.xn, e.xqmc, b.rcxwlbdlmc,b.rcxwdljcf,b.rcxwdlfssx, c.rcxwlbmc, ");
			sql.append("(case when c.rcxwfzlx='02' then'-'||a.fz when c.rcxwfzlx='01' then a.fz else '未知类型' end) fz");
			sql.append("  from xg_rcsw_rcxwjg a ");
			sql.append("  left join xg_rcsw_rcxwdbdlb b ");
			sql.append("   	on a.rcxwlbdldm = b.rcxwlbdldm ");
			sql.append("  left join xg_rcsw_rcxwlbdmb c ");
			sql.append("    on a.rcxwlbdm = c.rcxwlbdm ");
			sql.append(" left join xqdzb e ");
			sql.append("on a.xq = e.xqdm ");
			sql.append(" where a.xh = ? ");
			sql.append(" order by xn desc) group by xn,xqmc,rcxwlbdlmc,rcxwdlfssx, rcxwdljcf) ");
			sql.append(" ) order by xn, xq desc,xssx desc,rcxwlbdlmc,fssj desc ");
		}
		// ====================SQL拼装 end================================
		return CommonQueryDAO.commonQueryNotFy(sql.toString(), "", inputValue, outputValue, model);
	}
	/**
	 * 
	 * @描述:获取日行为结果参数配置
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-4-9 上午11:32:31
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String,String> getCspz(){ 
		String cszSql = "select * from xg_rcsw_cspzb where rownum=1";
		return dao.getMapNotOut(cszSql, new String[]{});
	}
	
	/**
	 * 
	 * 学生能够查看到自己的操行分分数，及相关记录
	 * @作者：Dlq[工号：995]
	 * @日期：2013-10-16 上午10:31:02
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * List<String[]> 返回类型 
	 * @throws
	 */
	public List<String[]> getStuRcswList(String xh) {
		//DAO dao = DAO.getInstance();
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select a.xn,e.xqmc xq ,b.rcxwlbdlmc, c.rcxwlbmc,a.rcxwjlsj,a.fz rcxwlbfz ");
		sql.append(" from xg_rcsw_rcxwjg a left join xg_rcsw_rcxwdbdlb b on a.rcxwlbdldm = b.rcxwlbdldm ");
		sql.append(" left join xg_rcsw_rcxwlbdmb c on a.rcxwlbdm = c.rcxwlbdm ");
		sql.append(" left join  xqdzb e on a.xq = e.xqdm ");
		sql.append(" where a.xh = ? ");
		sql.append(" order by xn ");
		
		
		return dao.rsToVator(sql.toString(),new String[]{xh}, new String[] { "xn","xq","rcxwlbdlmc","rcxwlbmc","rcxwjlsj","rcxwlbfz" });
	}
	
	
	/**
	 * 
	 * @描述:根据学号查询日常事务
	 * @作者：ligl
	 * @日期：2013-11-30 下午03:07:04
	 * @修改记录: 
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getRcswList(String xh,HashMap<String,String> cspzMap) {
		StringBuilder sql = new StringBuilder();
		if("1".equals(cspzMap.get("zq"))){
			sql.append(" select xn,rcxwlbdlmc,rcxwlbmc,rcxwjlsj, ");
			sql.append(" case when fz like '.%' then  '0' || fz ");
			sql.append(" when fz like '-.%' then substr(fz, 0, 1) || '0' || substr(fz, 2, length(fz)) ");
			sql.append(" else fz end fz,xssx from ( ");
			sql.append("select xn,rcxwlbdlmc,rcxwlbmc,rcxwjlsj,fz,xssx from ( ");
			sql.append("select a.xn,b.rcxwlbdlmc, c.rcxwlbmc, ");
			sql.append("(case when c.rcxwfzlx='02' then '-'||a.fz when c.rcxwfzlx='01' then '+'||a.fz else '未知类型' end) fz ,a.rcxwjlsj, '0' xssx");
			sql.append(" from xg_rcsw_rcxwjg a left join xg_rcsw_rcxwdbdlb b on a.rcxwlbdldm = b.rcxwlbdldm ");
			sql.append("  left join xg_rcsw_rcxwlbdmb c ");
			sql.append("     on a.rcxwlbdm = c.rcxwlbdm ");
			sql.append(" where a.xh = ? ");
			sql.append(" order by xn) ");
			sql.append(" union all ");
			sql.append("select xn,rcxwlbdlmc,rcxwlbmc,rcxwjlsj,to_char((fz+"+cspzMap.get("rcxwjcf")+"))fz,xssx from(");
			sql.append(" select  xn,'总分:' rcxwlbdlmc,'' rcxwlbmc,'0' rcxwjlsj, ");
			sql.append(" case when to_char(sum(fz)) like '.%' then '0'||to_char(sum(fz)) ");
			sql.append(" when to_char(sum(fz)) like '-.%' then substr(to_char(sum(fz)),0,1)||'0'||substr(to_char(sum(fz)),2,length(to_char(sum(fz))))");
			sql.append("else to_char(sum(fz)) end fz,'1' xssx from ( ");
			sql.append("select a.xn,a.rcxwlbdldm,a.rcxwlbdlmc, case when a.fz+b.rcxwdljcf> b.rcxwdlfssx then b.rcxwdlfssx else to_char(a.fz+b.rcxwdljcf) end fz from (");
			sql.append(" select xn,rcxwlbdldm,rcxwlbdlmc,sum(fz) fz from(");
			sql.append(" select a.xn, b.rcxwlbdlmc, c.rcxwlbmc,b.rcxwdljcf,  b.rcxwdlfssx, b.rcxwlbdldm,");
			sql.append("(case when c.rcxwfzlx='02' then '-'||a.fz when c.rcxwfzlx='01' then '+'||a.fz else '未知类型' end) fz ,a.rcxwjlsj, '1' xssx");
			sql.append("  from xg_rcsw_rcxwjg a ");
			sql.append("  left join xg_rcsw_rcxwdbdlb b ");
			sql.append("   	on a.rcxwlbdldm = b.rcxwlbdldm ");
			sql.append("  left join xg_rcsw_rcxwlbdmb c ");
			sql.append("    on a.rcxwlbdm = c.rcxwlbdm ");
			sql.append(" where a.xh = ? ");
			sql.append(" order by xn)  group by xn, rcxwlbdldm, rcxwlbdlmc) a left join xg_rcsw_rcxwdbdlb b on a.rcxwlbdldm = b.rcxwlbdldm) group by xn) ");
			sql.append(" union all ");
			sql.append("select xn,rcxwlbdlmc,rcxwlbmc,rcxwjlsj,case when fz+rcxwdljcf> rcxwdlfssx then rcxwdlfssx else to_char(fz+rcxwdljcf) end fz,xssx from(");
			sql.append(" select  xn,rcxwlbdlmc,rcxwdlfssx, rcxwdljcf,'' rcxwlbmc,'9999999999' rcxwjlsj,");
			sql.append(" case when to_char(sum(fz)) like '.%' then '0'||to_char(sum(fz)) " );
			sql.append(" when to_char(sum(fz)) like '-.%' then substr(to_char(sum(fz)),0,1)||'0'||substr(to_char(sum(fz)),2,length(to_char(sum(fz))))");
			sql.append("else to_char(sum(fz)) end fz,'0' xssx ");
			sql.append(" from (select a.xh,a.xn,b.rcxwlbdlmc,b.rcxwdljcf, b.rcxwdlfssx, c.rcxwlbmc, ");
			sql.append("(case when c.rcxwfzlx='02' then '-'||a.fz when c.rcxwfzlx='01' then a.fz else '未知类型' end) fz, '0' xssx");
			sql.append("  from xg_rcsw_rcxwjg a ");
			sql.append("  left join xg_rcsw_rcxwdbdlb b ");
			sql.append("   	on a.rcxwlbdldm = b.rcxwlbdldm ");
			sql.append("  left join xg_rcsw_rcxwlbdmb c ");
			sql.append("    on a.rcxwlbdm = c.rcxwlbdm ");
			sql.append(" where a.xh = ? ");
			sql.append(" order by xn)  group by xn, rcxwlbdlmc, rcxwdlfssx, rcxwdljcf) ");
			sql.append(" ) order by xn,xssx desc,rcxwlbdlmc,rcxwjlsj desc ");
			
		}else{
			sql.append(" select xn,xqmc,rcxwlbdlmc,rcxwlbmc,rcxwjlsj, ");
			sql.append(" case when fz like '.%' then  '0' || fz ");
			sql.append(" when fz like '-.%' then substr(fz, 0, 1) || '0' || substr(fz, 2, length(fz)) ");
			sql.append(" else fz end fz,xssx from ( ");
			sql.append("select xn,xqmc,rcxwlbdlmc,rcxwlbmc,rcxwjlsj,fz,xssx from ( ");
			sql.append("select a.xn, e.xqmc, b.rcxwlbdlmc, c.rcxwlbmc, ");
			sql.append("(case when c.rcxwfzlx='02' then '-'||a.fz when c.rcxwfzlx='01' then '+'||a.fz else '未知类型' end) fz ,a.rcxwjlsj, '0' xssx");
			sql.append(" from xg_rcsw_rcxwjg a left join xg_rcsw_rcxwdbdlb b on a.rcxwlbdldm = b.rcxwlbdldm ");
			sql.append("  left join xg_rcsw_rcxwlbdmb c ");
			sql.append("     on a.rcxwlbdm = c.rcxwlbdm ");
			sql.append("  left join xqdzb e ");
			sql.append("    on a.xq = e.xqdm ");
			sql.append(" where a.xh = ? ");
			sql.append(" order by xn) ");
			sql.append(" union all ");
			sql.append(" select xn,xqmc,rcxwlbdlmc,rcxwlbmc,rcxwjlsj,to_char((fz+"+cspzMap.get("rcxwjcf")+"))fz,xssx from(");
			sql.append(" select  xn, xqmc,'总分:' rcxwlbdlmc,'' rcxwlbmc,'0' rcxwjlsj, ");
			sql.append(" case when to_char(sum(fz)) like '.%' then '0'||to_char(sum(fz)) ");
			sql.append(" when to_char(sum(fz)) like '-.%' then substr(to_char(sum(fz)),0,1)||'0'||substr(to_char(sum(fz)),2,length(to_char(sum(fz))))");
			sql.append("else to_char(sum(fz)) end fz,'1' xssx from ( ");
			sql.append("select a.xn,a.xqmc,a.rcxwlbdldm,a.rcxwlbdlmc, case when a.fz+b.rcxwdljcf> b.rcxwdlfssx then b.rcxwdlfssx else to_char(a.fz+b.rcxwdljcf) end fz from (");
			sql.append(" select xn,xqmc,rcxwlbdldm,rcxwlbdlmc,sum(fz) fz from(");
			sql.append(" select a.xn, e.xqmc,");
			if("10355".equals(Base.xxdm)){
				  sql.append("'0' rcxwdljcf");	
				}
				else{
				  sql.append("b.rcxwdljcf");
				}
            sql.append(",b.rcxwdlfssx,b.rcxwlbdldm, b.rcxwlbdlmc, c.rcxwlbmc,");
			sql.append("(case when c.rcxwfzlx='02' then '-'||a.fz when c.rcxwfzlx='01' then '+'||a.fz else '未知类型' end) fz ,a.rcxwjlsj, '1' xssx");
			sql.append("  from xg_rcsw_rcxwjg a ");
			sql.append("  left join xg_rcsw_rcxwdbdlb b ");
			sql.append("   	on a.rcxwlbdldm = b.rcxwlbdldm ");
			sql.append("  left join xg_rcsw_rcxwlbdmb c ");
			sql.append("    on a.rcxwlbdm = c.rcxwlbdm ");
			sql.append(" left join xqdzb e ");
			sql.append("on a.xq = e.xqdm ");
			sql.append(" where a.xh = ? ");
			sql.append(" order by xn) group by xn , xqmc, rcxwlbdldm, rcxwlbdlmc) a left join xg_rcsw_rcxwdbdlb b on a.rcxwlbdldm = b.rcxwlbdldm) group by xn,xqmc)  ");
			sql.append(" union all ");
			sql.append("select xn,xqmc,rcxwlbdlmc,rcxwlbmc,rcxwjlsj,case when fz+rcxwdljcf> rcxwdlfssx then rcxwdlfssx else to_char(fz+rcxwdljcf) end fz,xssx from(");
			sql.append(" select  xn,xqmc,rcxwlbdlmc,rcxwdlfssx,");
			if("10355".equals(Base.xxdm)){
				  sql.append("'0' rcxwdljcf");	
				}
				else{
				  sql.append("rcxwdljcf");
				}
            sql.append(",'' rcxwlbmc,'9999999999' rcxwjlsj,");
			sql.append(" case when to_char(sum(fz)) like '.%' then '0'||to_char(sum(fz)) " );
			sql.append(" when to_char(sum(fz)) like '-.%' then substr(to_char(sum(fz)),0,1)||'0'||substr(to_char(sum(fz)),2,length(to_char(sum(fz))))");
			sql.append("else to_char(sum(fz)) end fz,'0' xssx ");
			sql.append(" from (select a.xh,a.xq,a.xn, e.xqmc, b.rcxwdlfssx, b.rcxwdljcf,b.rcxwlbdlmc, c.rcxwlbmc, ");
			sql.append("(case when c.rcxwfzlx='02' then '-'||a.fz when c.rcxwfzlx='01' then a.fz else '未知类型' end) fz, '0' xssx");
			sql.append("  from xg_rcsw_rcxwjg a ");
			sql.append("  left join xg_rcsw_rcxwdbdlb b ");
			sql.append("   	on a.rcxwlbdldm = b.rcxwlbdldm ");
			sql.append("  left join xg_rcsw_rcxwlbdmb c ");
			sql.append("    on a.rcxwlbdm = c.rcxwlbdm ");
			sql.append(" left join xqdzb e ");
			sql.append("on a.xq = e.xqdm ");
			sql.append(" where a.xh = ? ");
			sql.append(" order by xn) group by xn, xqmc, rcxwlbdlmc, rcxwdlfssx, rcxwdljcf) ");
			sql.append(" ) order by xn, xqmc, xssx desc, rcxwlbdlmc, rcxwjlsj desc ");
		}
		return dao.getListNotOut(sql.toString(), new String[] { xh,xh,xh });
	}
	
	
	
	@Override
	public RcxwjgForm getModel(RcxwjgForm t) throws Exception {
		StringBuffer sql= new StringBuffer();
		sql.append(" select a.*,b.rcxwlbzgfz,b.rcxwlbzdfz from xg_rcsw_rcxwjg a left join xg_rcsw_rcxwlbdmb b on a.rcxwlbdm=b.rcxwlbdm where a.id=?");
		RcxwjgForm model=new RcxwjgForm();
		HashMap<String, String> map=dao.getMapNotOut(sql.toString(), new String[]{t.getId()});
		BeanUtils.copyProperties(model, map);
		return model;
	}
	
	/** 
	 * @描述: 判断信息是否重复(学号、学期、学年、行为列表、发生时间)
	 * @作者：HongLin[工号：707]
	 * @日期：2014-2-24 下午05:47:55
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param request
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String,String>> getRcxwxxSfcf(HttpServletRequest request,String xh,String xn,String xq,String[] xwlbStr,String[] fssjStr){
		StringBuilder sql = new StringBuilder();
		sql.append("select a.*,b.rcxwlbmc from xg_rcsw_rcxwjg a left join XG_RCSW_RCXWLBDMB b on a.rcxwlbdm=b.rcxwlbdm where 1=1 ");
		if(xwlbStr!=null && xwlbStr.length>0){
			sql.append(" and ");
			for (int i=0;i<xwlbStr.length;i++){
				if(i==(xwlbStr.length-1)){
					sql.append("(a.xh='"+xh+"' and a.xn='"+xn+"' and a.xq='"+xq+"' and a.rcxwlbdm='"+xwlbStr[i]+"' and a.fssj='"+fssjStr[i]+"')");
				}else{
					sql.append("(a.xh='"+xh+"' and a.xn='"+xn+"' and a.xq='"+xq+"' and a.rcxwlbdm='"+xwlbStr[i]+"' and a.fssj='"+fssjStr[i]+"') or ");
				}
			}
		}
		return dao.getList(sql.toString(), new String[]{}, new String[]{"xh","xn","xq","rcxwlbmc","fssj"});
	}
	/**
	 * 
	 * @描述:获取楼栋
	 * @作者：xiaxia [工号：1104]
	 * @日期：2014-12-1 下午03:22:58
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param yqdm
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	
	public List<HashMap<String,String>> getLdxx(String yqdm){
		String sql = "select lddm,ldmc from xg_gygl_new_ldxxb where yqdm = ? ";
		return DAO.getInstance().getList(sql, new String[]{yqdm}, new String[]{"lddm","ldmc"});
	}
	/**
	 * 获取寝室list
	 * @param lddm
	 * @return
	 */
//	public List<HashMap<String,String>> getQsList(String yqdm){
//		String sql= "select ldmc,qsh,qsxb from view_xg_gygl_new_qsxx where lddm = ? order by ch,qsh";
//		return DAO.getInstance().getList(sql, new String[]{lddm}, new String[]{"ldmc","qsh","qsxb"});
//	}
	/**
	 * 获取楼栋寝室数量
	 * @param lddm
	 * @return
	 */
	public String getQsCount(String lddm){
		String sql= "select count(*) qss from view_xg_gygl_new_qsxx where lddm=?";
		return DAO.getInstance().getOneRs(sql, new String[]{lddm}, "qss");
	}
	/**
	 * 获取楼栋寝室住宿信息list
	 * @param lddm
	 * @return
	 */
	public List<HashMap<String,String>> getZsxxList(String yqdm){
		String sql= "select a.lddm,a.qsh,a.xh,b.xm,b.bjmc from (select ldmc,qsh,cwh,xh from view_xg_gygl_new_cwxx " +
				"where xh is not null and yqdm = ? order by qsh,cwh ) a left join view_xsjbxx b on a.xh = b.xh";
		return DAO.getInstance().getList(sql, new String[]{yqdm}, new String[]{"qsh","xh","xm","bjmc"});
	}
	/**
	 * 
	 * @描述:获取园区楼栋所住学生学年学期文明品行分
	 * @作者：xiaxia [工号：1104]
	 * @日期：2014-12-2 上午09:04:04
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param yqdm
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getpxfList(String yqdm,String xn,String xq){
		StringBuffer sql  = new StringBuffer();
		sql.append("select a.*,b.plusxm,b.cutxm,(zfs+to_number(nvl(b.fz,0))) sjfz from(select * from (select a.*,80+to_number(nvl(b.zfs,0)) zfs from (select a.*,b.xm,b.zzmmmc from (select* from view_xg_gygl_new_cwxx where yqdm=?) a left join view_xsbfxx b on a.xh=b.xh) a  left join");
		sql.append(" (  select sum(fs) zfs,xh from XG_GYGL_NEW_WSJC_XSFSB where xn=? and xq=? group by xh ");
		sql.append(" ) b on a.xh=b.xh )where xh is not null)a left join (select xh,xn,xq,sum(decode(rcxwfzlx, '01', fz)) plusxm,");
		sql.append(" sum(decode(rcxwfzlx, '02', fz)) cutxm, case when to_char(sum(fz)) like '.%' then '0' || to_char(sum(fz))");
		sql.append(" else to_char(sum(fz)) end fz from (select a.xh,a.xn,a.xq,c.rcxwfzlx,b.rcxwlbdlmc,c.rcxwlbmc,(case when c.rcxwfzlx = '02' then  '-' || a.fz");
		sql.append(" when c.rcxwfzlx = '01' then a.fz else  '未知类型' end) fz  from xg_rcsw_rcxwjg a left join xg_rcsw_rcxwdbdlb b on a.rcxwlbdldm = b.rcxwlbdldm");
		sql.append(" left join xg_rcsw_rcxwlbdmb c on a.rcxwlbdm = c.rcxwlbdm where xn=? and xq=? order by xn)group by xh,xn,xq)b on a.xh=b.xh order by lddm,qsh,cwh");
		return DAO.getInstance().getListNotOut(sql.toString(), new String[]{yqdm,xn,xq,xn,xq});
	}
	
	public String getXqmc(String xqdm){
		String sql ="select xqmc from xqdzb where xqdm =?";
		return DAO.getInstance().getOneRs(sql, new String[]{xqdm}, "xqmc");
		
	}
	public String getYqmc(String yqdm){
		String sql ="select yqmc from zxbz_ssyqdm where yqdm =?";
		return DAO.getInstance().getOneRs(sql, new String[]{yqdm}, "yqmc");
		
	}
	
	/**
	 * 
	 * @描述: 日常行为数据导出（2大类能力素质测评与思想道德素质测评-浙江医学个性化）
	 * @作者：沈晓波 [工号：1123]
	 * @日期：2015-1-20 下午02:57:24
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xn
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getrcxwfList(String xn){
		StringBuffer sql  = new StringBuffer();
		sql.append(" select xh, xm, xymc, zymc, bjmc, nlf, sxddf, (nlf + sxddf) zf, (case when nlf >= 70 and sxddf >= 70 then '2' when nlf < 70 and sxddf < 70 then '0' else '1' end) xf ");
		sql.append(" from (select a.xh, c.xm, c.xymc, c.zymc, c.bjmc, (60 + to_number(nvl(b.fz, 0))) nlf, z.sxddf ");
		sql.append(" from (select xh from xg_rcsw_rcxwjg a left join XG_RCSW_RCXWDBDLB b on a.rcxwlbdldm = b.rcxwlbdldm ");
        sql.append(" where rcxwlbdlmc like '%能力素质测评%' or rcxwlbdlmc like '%思想道德素质测评%' group by xh) a ");       
        sql.append(" left join view_xsbfxx c on a.xh = c.XH left join (select xh, xn, sum(decode(rcxwfzlx, '01', fz)) plusxm, sum(decode(rcxwfzlx, '02', fz)) cutxm, ");
        sql.append(" case when to_char(sum(fz)) like '.%' then '0' || to_char(sum(fz)) else to_char(sum(fz)) end fz ");                   
        sql.append(" from (select a.xh, a.xn, a.xq, c.rcxwfzlx, c.rcxwlbmc, (case when c.rcxwfzlx = '02' then '-' || a.fz when c.rcxwfzlx = '01' then a.fz else '未知类型' end) fz ");                     
        sql.append(" from xg_rcsw_rcxwjg a left join xg_rcsw_rcxwdbdlb b on a.rcxwlbdldm = b.rcxwlbdldm left join xg_rcsw_rcxwlbdmb c ");
        sql.append(" on a.rcxwlbdm = c.rcxwlbdm left join view_xsbfxx d on a.xh = d.xh where xn = ? and rcxwlbdlmc like '%能力素质测评%' ");
        sql.append(" order by xn) group by xh, xn) b on a.XH = b.xh ");
        sql.append(" left join (select (60 + to_number(nvl(b.fz, 0))) sxddf, xh ");
        sql.append(" from (select xh, xn, rcxwlbdlmc, sum(decode(rcxwfzlx, '01', fz)) plusxm, sum(decode(rcxwfzlx, '02', fz)) cutxm, ");
        sql.append(" case when to_char(sum(fz)) like '.%' then '0' || to_char(sum(fz)) else to_char(sum(fz)) end fz ");
        sql.append(" from (select a.xh, a.xn, a.xq, c.rcxwfzlx, b.rcxwlbdlmc, c.rcxwlbmc, (case when c.rcxwfzlx = '02' then '-' || a.fz when c.rcxwfzlx = '01' then a.fz else '未知类型' end) fz ");
        sql.append(" from xg_rcsw_rcxwjg a left join xg_rcsw_rcxwdbdlb b on a.rcxwlbdldm = b.rcxwlbdldm ");
        sql.append(" left join xg_rcsw_rcxwlbdmb c on a.rcxwlbdm = c.rcxwlbdm left join view_xsbfxx d ");
        sql.append(" on a.xh = d.xh where xn = ? and rcxwlbdlmc like '%思想道德素质测评%' order by xn) ");
        sql.append(" group by xh, xn, rcxwlbdlmc) b) z on z.xh = a.xh) ");
        
		return DAO.getInstance().getListNotOut(sql.toString(), new String[]{xn,xn});
	}
	/**
	 * 
	 * @描述:日常行为总分（天津体育）
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-4-13 上午10:57:37
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xn
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getrcxwzfList(HashMap<String,String> cspzMap,String xn){
		StringBuffer sql  = new StringBuffer();
		sql.append("select b.xm, b.xymc, b.zymc, b.bjmc,b.nj,a.xn,a.xh,to_char((a.fz+"+cspzMap.get("rcxwjcf")+"))fz from(");
		sql.append(" select  xh,xn,");
		sql.append(" case when to_char(sum(fz)) like '.%' then '0'||to_char(sum(fz)) ");
		sql.append(" when to_char(sum(fz)) like '-.%' then substr(to_char(sum(fz)),0,1)||'0'||substr(to_char(sum(fz)),2,length(to_char(sum(fz))))");
		sql.append("else to_char(sum(fz)) end fz from ( ");
		sql.append(" select a.xn,a.xh, b.rcxwlbdlmc, c.rcxwlbmc,");
		sql.append("(case when c.rcxwfzlx='02' then '-'||a.fz when c.rcxwfzlx='01' then '+'||a.fz else '未知类型' end) fz");
		sql.append("  from xg_rcsw_rcxwjg a ");
		sql.append("  left join xg_rcsw_rcxwdbdlb b ");
		sql.append("   	on a.rcxwlbdldm = b.rcxwlbdldm ");
		sql.append("  left join xg_rcsw_rcxwlbdmb c ");
		sql.append("    on a.rcxwlbdm = c.rcxwlbdm ");
	
		sql.append(" order by xh,xn) group by xh,xn) a  ");
		sql.append(" left join view_xsbfxx b on a.xh=b.xh  ");
		sql.append(" where a.xn=?");
		return DAO.getInstance().getListNotOut(sql.toString(), new String[]{xn});
	}
	/**
	 * 
	 * @描述:查询学生是否需要处罚分提示
	 * @作者：xiaxia[工号：1104]
	 * @日期：2016-8-10 下午01:47:24
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean getCffWarnStudent(String xh){
		StringBuffer sql  = new StringBuffer();
		sql.append("select count(1) num from (select xh, sum(fz) cff, fssj from (select a.xh,a.fz,to_char(to_date(a.fssj, 'yyyy-mm-dd'), 'yyyy-mm') fssj,");
		sql.append(" b.rcxwfzlx from xg_rcsw_rcxwjg a left join xg_rcsw_rcxwlbdmb b on a.rcxwlbdm = b.rcxwlbdm)");
		sql.append(" where rcxwfzlx = '02' and fssj=to_char(sysdate,'yyyy-mm') group by xh, fssj) where cff>=10 and xh=?");
		return Integer.parseInt(dao.getOneRs(sql.toString(), new String[]{xh}, "num"))>0;
		
		
	}

	/** 
	 * @描述:青岛滨海个性化：查询思想品德课成绩汇总表数据
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年3月31日 上午9:53:44
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getSxpdcjhzList(RcxwjgForm model, User user) throws Exception{
		
		SearchModel searchModel = model.getSearchModel();
		//清空无用条件
		searchModel.setSearch_tj_rcxwdl(null);//行为大类
		searchModel.setSearch_tj_rcxwlb(null);//行为类别
		searchModel.setSearch_tj_jssj(null);//时间查询：结束时间
		searchModel.setSearch_tj_kssj(null);//时间查询：开始时间
		
		//获得必要的学年学期（只允许传一个）
		String xn = searchModel.getSearch_tj_xn()[0];
		String xq = searchModel.getSearch_tj_xq()[0];
		
		//生成高级查询相关条件、条件值 
		String searchTj = SearchService.getSearchTj(searchModel);
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT * FROM (");
		sql.append("SELECT dense_rank() over ");
		sql.append("(partition by t2.bjdm order by (round(t2.llf*0.4+(t2.jcf+t2.qtf+t2.ygpf)*0.6,2)) desc) rank,");
		sql.append("(round(t2.llf*0.4+(t2.jcf+t2.qtf+t2.ygpf)*0.6,2)) zf,(round(t2.llf*0.4,2)) llf_p4,(t2.qtpf+t2.ygpf) pf,");
		sql.append("(round((t2.jcf+t2.qtf+t2.ygpf)*0.6,2)) tf_p6,t2.qtmf mf,t2.jcf+t2.qtf+t2.ygpf tf,t2.* FROM (");
		sql.append("SELECT vx.bjdm,vx.bjmc,vx.xh,vx.xm,vx.xb,vx.nj,vx.xydm,vx.zydm,");
		sql.append(" '");
		sql.append(xn);
		sql.append("' xn,");
		sql.append(" '");
		sql.append(xq);
		sql.append("' xq,");
		sql.append("q.xqmc,'75' jcf,nvl(t1.qtpf,0) qtpf,nvl(t1.qtmf,0) qtmf,nvl(l.llf,0) llf,");
		sql.append("nvl(CASE WHEN ");
		sql.append("(t1.qtpf-t1.qtmf) > 20 THEN 20 ELSE (t1.qtpf-t1.qtmf) END,0) qtf,");
		sql.append("nvl(CASE WHEN ");
		sql.append("t1.ygpf > 5 THEN 5 ELSE t1.ygpf END,0) ygpf ");
		sql.append("FROM view_xsbfxx vx LEFT JOIN (SELECT r1.xh,r1.xn,r1.xq,");
		sql.append("sum(to_number(decode(r2.rcxwfzlx,'01',(CASE r2.rcxwlbmc WHEN '义工活动' THEN ");
		sql.append("nvl(r1.fz,0) ELSE '0' END ),'02','0'))) ygpf,");
		sql.append("sum(to_number(decode(r2.rcxwfzlx,'01',(CASE r2.rcxwlbmc WHEN '义工活动' THEN ");
		sql.append("'0' ELSE nvl(r1.fz,0) END ),'02','0'))) qtpf,");
		sql.append("sum(to_number(decode(r2.rcxwfzlx,'01','0','02',nvl(r1.fz,0)))) qtmf ");
		sql.append("FROM XG_RCSW_RCXWJG r1 ");
		sql.append("LEFT JOIN xg_rcsw_rcxwlbdmb r2 ON r1.rcxwlbdm = r2.rcxwlbdm ");
		sql.append("WHERE xn = '");
		sql.append(xn);
		sql.append("' AND xq = '");
		sql.append(xq);
		sql.append("' ");
		sql.append("GROUP BY r1.xh,r1.xn,r1.xq) t1 ");
		sql.append(" ON t1.xh = vx.xh ");
		sql.append("LEFT JOIN xg_rcsw_rcxwjg_llfjgb l ON t1.xh = l.xh AND t1.xn = l.xn AND t1.xq = l.xq ");
		sql.append("LEFT JOIN xqdzb q ON q.xqdm = ");
		sql.append(" '");
		sql.append(xq);
		sql.append("') t2 ");
		sql.append(") t where 1=1 ");
		
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return dao.getListNotOut(sql.toString(), inputV);
	}
	
	/**
	 * @描述: 根据id获取信息
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2017-10-14 上午11:03:01
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param qjsqid
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getKptzsForId(String id){
		StringBuffer sql = new StringBuffer();
		sql.append("select * from VIEW_NEW_DC_RCSW_RCXWJG where id = ?");
		return dao.getMapNotOut(sql.toString(),new String[]{id});
	}
}
