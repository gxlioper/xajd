/**
 * @部门:学工产品事业部
 * @日期：2013-8-2 上午09:20:12 
 */
package com.zfsoft.xgxt.rcsw.rcxwwh.rcxwxxwh;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 日常行为管理模块
 * @类功能描述: 日常行为信息维护 
 * @作者：dlq [工号：995]
 * @时间： 2013-8-2 上午09:20:12
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class RcxwxxwhDao extends SuperDAOImpl<RcxwxxwhForm> {

	/*
	 * 描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		super.setKey("id");
		super.setTableName("xg_rcsw_rcxwxxwh");
		super.setClass(RcxwxxwhForm.class);
	}

	/*
	 * 描述: @see
	 * com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(RcxwxxwhForm t)
			throws Exception {
		return null;
	}

	/**
	 * 取model
	 * @throws Exception 
	 */
	@Override
	public RcxwxxwhForm getModel(RcxwxxwhForm model) throws Exception{		
		StringBuffer sql = new StringBuffer();		
		sql.append(" select a.*,b.rcxwlbdldm ");
		sql.append(" from XG_RCSW_RCXWXXWH a ");
		sql.append(" left join XG_RCSW_RCXWLBDMB b   ");
		sql.append(" on a.rcxwlbdm = b.rcxwlbdm ");
		sql.append(" left join XG_RCSW_RCXWDBDLB c   ");
		sql.append(" on b.rcxwlbdldm = c.rcxwlbdldm ");		
		sql.append(" where a.id = ? ");
		return getModel(sql.toString(),new String[]{model.getId()});
	}
		/*
	 * 描述: @see
	 * com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object,
	 * xgxt.form.User)
	 */
	@Override
	public List<HashMap<String, String>> getPageList(RcxwxxwhForm t, User user)
			throws Exception {

		// 生成高级查询相关条件、条件值
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t5", "xydm", "bjdm");	
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		/*sql.append("select  t5.id,t5.xh,t5.xm,t5.xb,t5.bjmc,t5.nj,t5.bjdm,t5.xydm,t5.zydm,t5.rcxwlbdlmc,rcxwlbmc,t5.rcxwjlsj,t5.shztmc, t5.rcxwlbdldm,t5.xn,");
		sql.append(" (select xqmc from xqdzb b where t5.xq=b.xqdm) xq,t5.bz,t5.rcxwlbdm from (");
		sql.append(" select t1.id,t1.xh,t2.xm,t2.xb,t1.bz,t2.bjmc,");
		sql.append("t2.lxdh,t1.xn,t2.xydm,t2.zydm,t2.bjdm,t2.nj,");
		//sql.append("decode(t1.xq,'01','春','02','秋',t1.xq ) xq, ");
		sql.append("t1.rcxwjlsj,t3.rcxwlbmc,t3.rcxwlbdlmc,t1.shzt,t3.rcxwlbdldm,t3.rcxwlbdm,");
		sql.append("decode(t1.shzt,'0','未审核','1','通过','2','不通过',");
		sql.append("'3','退回','4','需重审','5','审核中','','无需审核',t1.shzt) shztmc, t4.xqdm xq ");
		sql.append(" from xg_rcsw_rcxwxxwh t1 ");
		sql.append(" left join view_xsxxb t2 on t1.xh=t2.xh ");
		sql.append(" left join (select *  from (select a.*,b.rcxwlbdlmc  from xg_rcsw_rcxwlbdmb a left join ");
		sql.append(" xg_rcsw_rcxwdbdlb b on a.rcxwlbdldm = b.rcxwlbdldm )) t3  on t1.rcxwlbdm =t3.rcxwlbdm ");
		sql.append(" left join  xqdzb t4 on t1.xq = t4.xqdm ) t5 where 1=1 ");*/
		
		sql.append("select * from VIEW_NEW_DC_RCSW_RCSWXXWH t5 where 1=1 ");
		
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}
	
	/**
	 * 
	 * @描述:获取行为类别集合
	 * @作者：Dlq [工号：995]
	 * @日期：2013-8-13 下午05:22:14
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xwdldm
	 * @param request
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getXwlbList(String xwdldm,
			HttpServletRequest request) {
		String sql = "select rcxwlbdm,rcxwlbmc from xg_rcsw_rcxwlbdmb where rcxwlbdldm=? and sfqy='1' order by rcxwlbdm ";
		return dao.getList(sql, new String[] { xwdldm }, new String[] {
				"rcxwlbdm", "rcxwlbmc" });
	}
	/**
	 * 
	 * @描述:获取行为大类集合
	 * @作者：Dlq [工号：995]
	 * @日期：2013-8-13 下午05:21:55
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param request
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getXwdlList(HttpServletRequest request) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from ( ");
		sql.append(" select b.rcxwlbdldm,b.rcxwlbdlmc, ");
		sql.append(" case when b.sqkg=1 and sysdate between to_date(nvl(b.sqkssj,'1990-01-01'),'yyyy-mm-dd') and to_date(nvl(b.sqjssj,'2200-01-01'),'yyyy-mm-dd')+1 then 'true' else 'false' end isopen ");
		sql.append(" from xg_rcsw_rcxwdbdlb b  ");
		sql.append(" ) where isopen='true' order by rcxwlbdldm ");
		
		return dao.getList(sql.toString(), new String[] {}, new String[] { "rcxwlbdldm",
				"rcxwlbdlmc" });
	}
	
	/**
	 * 
	 * @描述:按照学号，学年，学期查询该日常行为信息 在表中有几条数据
	 * @作者：Dlq [工号：995]
	 * @日期：2013-8-13 下午05:20:42
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String checkExistForSave(RcxwxxwhForm model) {
		StringBuilder sql = new StringBuilder(
				"select count(1) num from xg_rcsw_rcxwxxwh where xh=? and xn=? and xq=? ");
		String num = dao.getOneRs(sql.toString(), new String[] { model.getXh(),
				model.getXn(), model.getXq() }, "num");
		return num;

	}
	
	/**
	 * 
	 * @描述:获取审核状态
	 * @作者：Dlq [工号：995]
	 * @日期：2013-8-13 下午05:19:59
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String checkExistForUpdate(RcxwxxwhForm model) {
		String sql = "select shzt from xg_rcsw_rcxwxxwh where id=? ";
		String shzt = dao.getOneRs(sql.toString(), new String[] { model.getId()}, "shzt");
		return shzt;

	}

	/**
	 * 
	 * 获取审批流程ID
	 * 
	 * @作者：dlq [工号：995]
	 * @日期：2013-8-5 下午03:09:39
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return String 返回类型
	 * @throws
	 */
	public String getShlcID(String Rcxwlbdldm) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select splc from xg_rcsw_rcxwdbdlb  where rcxwlbdldm = ? ");

		return dao
				.getOneRs(sql.toString(), new String[] { Rcxwlbdldm }, "splc");
	}
	
	/**
	 * 
	 * @描述:在删除日常行为结果同时删除日常行为维护
	 * @作者：Dlq [工号：995]
	 * @日期：2013-8-13 下午05:19:06
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param values
	 * @return
	 * @throws Exception
	 * int 返回类型 
	 * @throws
	 */
	public int delRcxwwhFromRcxwjg(String[] values) throws Exception {

		StringBuilder sql = new StringBuilder();
		sql.append("delete from xg_rcsw_rcxwxxwh t1 where  ");
		sql.append(" (");
		for (int i = 0, n = values.length; i < n; i++) {
			sql.append("id=?");

			if (i != n - 1) {
				sql.append(" or ");
			}
		}
		sql.append(")");
		return dao.runDelete(sql.toString(), values);
	}
	
	/**
	 * 
	 * @描述:判断该行为大类是否有审核流程
	 * @作者：Dlq [工号：995]
	 * @日期：2013-8-13 下午05:18:16
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param rcxwlbdldm
	 * @return
	 * @throws Exception
	 * String 返回类型 
	 * @throws
	 */
	public String checkForSplc(String rcxwlbdldm)throws Exception{
		
		StringBuffer sql = new StringBuffer();
		sql.append(" select splc from xg_rcsw_rcxwdbdlb  where rcxwlbdldm = ? ");
		return dao.getOneRs(sql.toString(), new String[] { rcxwlbdldm }, "splc");
		
	}
	
	/**
	 * 
	 * @描述:删除日常行为信息
	 * @作者：Dlq [工号：995]
	 * @日期：2013-8-13 下午05:17:56
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param values
	 * @return
	 * @throws Exception
	 * int 返回类型 
	 * @throws
	 */
	public int delXwxx(String[] values) throws Exception {
		
		if (values == null || values.length == 0){
			logger.error("删除操作不能进行!");
			throw new NullPointerException();
		}
		StringBuilder sql = new StringBuilder();
		sql.append(" delete from xg_rcsw_rcxwxxwh ");
		sql.append(" where (shzt is null or shzt='0' or shzt='3') and ");
		sql.append("(");
		
		for (int i = 0 , n = values.length ; i < n ; i++){
			sql.append("id=?");
			
			if (i != n-1){
				sql.append(" or ");
			}
		}
		sql.append(")");
		return dao.runDelete(sql.toString(), values);
	}
	
	/**
	 * 
	 * 查看单条日常行为信息
	 * @作者：dlq [工号：995]
	 * @日期：2013-8-9 下午02:38:08
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xwjgId
	 * @return
	 * Map<String,String> 返回类型 
	 * @throws
	 */
	public Map<String, String> getOneXwxxList(String  xwjgId) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select id,xn, xqmc , rcxwlbmc,rcxwlbdlmc,fjlj,fjmc, ");
		sql.append(" rcxwjlsj,gfly,bz,(case when rcxwfzlx='01' then '+'||fz when rcxwfzlx='02' then '-'||fz else '未知类型' end) fz, decode(a.shzt,'0','未提交','1','通过','2','不通过','3','已退回','4','需重审','5','审核中','','无需审核',a.shzt) shztmc,fssj,c.rcxwlbbz,jlr,(select xm from yhb y where y.yhm=a.jlr) jlrxm ");
		sql.append(" from xg_rcsw_rcxwxxwh a left join (select *  from (select a.*,b.rcxwlbdlmc  from xg_rcsw_rcxwlbdmb a ");
		sql.append(" left join xg_rcsw_rcxwdbdlb b on a.rcxwlbdldm = b.rcxwlbdldm )) c ");
		sql.append(" on a.rcxwlbdm = c.rcxwlbdm ");
		sql.append(" left join  xqdzb d on a.xq = d.xqdm where a.id = ?");
		return dao.getMapNotOut(sql.toString(),new String[]{xwjgId});
	}
	

	public boolean updateRcxwxxwh(String id,String splc,String shzt) throws Exception{
		String[] inputV = new String[3];
		StringBuilder sql = new StringBuilder();
		sql.append(" UPDATE xg_rcsw_rcxwxxwh ");
		sql.append(" set ");
		sql.append(" shzt = ?,");
		sql.append(" splc = ? ");
		sql.append(" where id = ?");
		inputV[0] = shzt;
		inputV[1] = splc;
		inputV[2] = id;
		return dao.update(sql.toString(),inputV)>0 ? true:false;
	}
	
	/** 
	 * @描述: 查询获取行为类别信息
	 * @作者：HongLin [工号：707]
	 * @日期：2014-2-21 上午10:51:46
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param request
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String,String>> getXwlbxx(HttpServletRequest request,String lbdm) {
		String sql = "select a.rcxwlbdm,a.rcxwlbmc,a.rcxwlbdldm,a.rcxwfzlx,(case when a.rcxwfzlx='01' then '加分' when a.rcxwfzlx='02' then '减分' else '未知类型' end) rcxwfzlxmc,"
					+"(case when a.rcxwlbzdfz is not null and a.rcxwlbzgfz is not null then a.rcxwlbzdfz||'-'||a.rcxwlbzgfz when a.rcxwlbzdfz is not null and a.rcxwlbzgfz is null then a.rcxwlbzdfz when a.rcxwlbzdfz is null and a.rcxwlbzgfz is not null then a.rcxwlbzgfz else '' end) fzqj,"
					+"a.rcxwlbbz,(case when a.rcxwlbbz is null then '' when length(a.rcxwlbbz) <=10 then a.rcxwlbbz else substr(a.rcxwlbbz,0,10)||'......' end) rcxwlbbzsj,a.rcxwlbfz,(case when a.rcxwlbzdfz is not null and a.rcxwlbzgfz is not null then 'zdy' else 'gd' end) fzsfgd,a.rcxwlbzgfz,a.rcxwlbzdfz "
					+" from xg_rcsw_rcxwlbdmb a where rcxwlbdm=? and rownum=1";
		return dao.getList(sql, new String[] {lbdm}, new String[] { "rcxwlbdm","rcxwlbmc","rcxwlbdldm","rcxwfzlx","rcxwfzlxmc","fzqj","rcxwlbbz","rcxwlbfz","fzsfgd","rcxwlbzgfz","rcxwlbzdfz","rcxwlbbzsj" });
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
		sql.append("select a.*,b.rcxwlbmc from XG_RCSW_RCXWXXWH a left join XG_RCSW_RCXWLBDMB b on a.rcxwlbdm=b.rcxwlbdm where 1=1 ");
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
	 * @描述:验证是否可提交
	 * @作者：qlm
	 * @日期：2014-5-26 上午11:25:35
	 * @param jddm
	 * @return
	 * String 返回类型 
	 * @throws 
	 */
	public String checkSfktj(String rcxwlbdm) {
		
		StringBuilder sql = new StringBuilder();
		String num = "";
		sql.append(" select count(1) num   ");
		sql.append("  from XG_XLJK_CDGL_CDXXB t2 ");
		sql.append("  where t2.sfkfsq = '1'       ");
		sql.append("    and t2.cdid = ? ");
		
		num = dao.getOneRs(sql.toString(), new String[] { rcxwlbdm }, "num");
		return num;
	}
}
