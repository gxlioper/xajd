/**
 * @部门:学工产品事业部
 * @日期：2013-4-25 下午02:25:32 
 */  
package com.zfsoft.xgxt.xszz.sqsh;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xgxt.action.Base;
import xgxt.comm.search.SearchModel;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.xpjpy.wdpj.sqsh.SqshDao;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述:学生资助2013版申请审核 
 * @作者： Penghui.Qu 
 * @时间： 2013-4-25 下午02:25:32 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */
public class XszzSqshDao extends SuperDAOImpl<XszzSqshForm> {
	// 是否班级评议.1：是，0：否
	private static final boolean SFBJPY_Y = "1".equals(MessageUtil.getText("xszz.sfbjpy"));
	private static final String DSH = "dsh";
	private static final String YSH = "ysh";
	
	
	public List<HashMap<String, String>> getPageList(XszzSqshForm t)
			throws Exception {
		return null;
	}

	
	
	public List<HashMap<String, String>> getPageList(XszzSqshForm t, User user)
			throws Exception {
		
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t1", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		
		StringBuilder sql = new StringBuilder();
		/*sql.append("select t1.* from (");
		sql.append("select t1.guid,t1.xmdm,t1.xh,t2.xm,t2.xb,t2.nj,t2.xymc,");
		sql.append("t2.zymc,t2.bjmc,t2.zzmmmc,t2.mzmc,t2.yhmc,t2.yhkh,");
		sql.append("t2.xydm,t2.zydm,t2.bjdm,t1.xq,t2.sfzh,t1.xn,t3.xqmc,");
		sql.append("t4.xmmc,t4.je, t1.sqsj,t1.sqly,t1.shzt,");
		sql.append("decode(t1.shzt,'0','未审核','1','通过','2','不通过',");
		sql.append("'3','退回','4','需重审','5','审核中',t1.shzt) shztmc ");
		sql.append(" from xg_xszz_new_zzxmsqb t1 ");
		sql.append(" left join view_xsxxb t2 on t1.xh=t2.xh ");
		sql.append(" left join xqdzb t3 on t1.xq=t3.xqdm ");
		sql.append(" left join xg_xszz_new_zzxmdmb t4 on t1.xmdm=t4.xmdm ");
		sql.append(" ) t1 where 1=1 ");*/
		
		sql.append("select * from (select t1.*,t2.sydm,t3.symc,case when t2.sqkg=1 and sysdate between to_date(nvl(t2.sqkssj,19900101),'yyyymmdd') ");
		sql.append("and to_date(nvl(t2.sqjssj,20200101),'yyyymmdd')+1 then 'true' else 'false' end isopen, ");
		sql.append("nvl2(t3.rddc, '是', '否') sfkns ");
		sql.append(" from VIEW_NEW_DC_XSZZ_SQSH t1 ");
		sql.append(" left join XG_XTWH_SYBJGLB t2 on t2.bjdm=t1.bjdm ");
		sql.append(" left join XG_XTWH_SYDMB t3 on t3.sydm = t2.sydm ");
		sql.append(" left join xg_xszz_new_zzxmdmb t2 on t1.xmdm =t2.xmdm ");
		sql.append(" left join xg_xszz_new_knsjgb t3 on t1.xh = t3.xh and (t1.xn = t3.xn and decode(t3.xq,'on',nvl(t1.xq,1),t3.xq) = nvl(t1.xq,1)) ");

		sql.append(" ) t1 where 1=1 ");
		
		sql.append(searchTjByUser);
		sql.append(searchTj);
		sql.append(" order by xn desc,xq,sqsj desc");
		return getPageList(t, sql.toString(), inputV);
	}

	protected void setTableInfo() {
		super.setKey("guid");
		super.setTableName("xg_xszz_new_zzxmsqb");
		super.setClass(XszzSqshForm.class);
	}

	
	
	/**
	 * 
	 * @描述:按学生查询已申请的资助项目
	 * @作者：Penghui.Qu
	 * @日期：2013-5-6 下午01:56:18
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getXmsqInfoList(String xh){
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select a.*,b.sqsj,a.pdxn || ' ' || (select xqmc from xqdzb a where a.xqdm = a.pdxq) pdzq from xg_xszz_new_zzxmdmb a left join (select * from ( ");
		sql.append("select t2.*,row_number()over(partition by xmdm order by sqsj desc ) rn ");
		sql.append("from xg_xszz_new_zzxmsqb t2 where t2.xn||t2.xq = (select dqxn || dqxq from xtszb) ");
		sql.append("and xh = ? ) where rn =1) b on a.xmdm=b.xmdm where a.sqxn||a.sqxq = (select dqxn || dqxq from xtszb) ");
		sql.append("and a.sqkg = '1' and (sysdate between to_date(nvl(a.sqkssj, '19900101'), 'yyyymmdd') ");
		sql.append("and to_date(nvl(a.sqjssj, '20200101'), 'yyyymmdd') + 1) ");
		sql.append("and (( a.jgsqzq='0' and a.sqxn<>b.xn ) ");
		sql.append("or (a.jgsqzq='1' and a.sqxn||a.sqxq<>b.xn||xq ) ");
		sql.append("or (a.jgsqzq='2' and to_char(to_date(b.sqsj,'yyyy-mm-dd hh24:mi:ss'),'yyyymm') <> to_char(sysdate,'YYYYMM')) ");
		sql.append("or (a.jgsqzq='3' and to_char(to_date(b.sqsj,'yyyy-mm-dd hh24:mi:ss'),'yyyymmdd') <> to_char(sysdate,'YYYYMMDD')) ");
		sql.append("or b.xh is null ) order by xmmc ");
		
		return dao.getListNotOut(sql.toString(), new String[]{xh});
	}
	
	/**
	 * 
	 * @描述:按学生查询已申请和未申请的资助项目 分页查询
	 * @作者：张小彬[工号:1036]
	 * @日期：2014-1-6 上午11:42:53
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String , String>> getXmsqInfoList(XszzSqshForm model , String type) throws Exception{
		SearchModel searchModel = model.getSearchModel();
		String searchTj = SearchService.getSearchTj(searchModel);
		String[] inputValue = SearchService.getTjInput(searchModel);

		StringBuilder sql = new StringBuilder();
		List<HashMap<String , String>> result = null;
		List<String> params = new ArrayList<String>();
//		params.add(model.getXh());
		
		if(StringUtils.isEqual("ysq", model.getSqType())){
			
			sql.append("select b.*, a.shzt,a.xn,(select xqmc from xqdzb a where a.xqdm = a.xq) xqmc,a.sqsj, ");
			sql.append("a.guid,a.shlc,c.lbmc, ");
			sql.append("decode(a.shzt,'0','未提交','1','通过','2','不通过','3','已退回', ");
			sql.append("'5','审核中','6','班级评议中','7','班级评议不通过','未申请') shztmc ");
			sql.append("from xg_xszz_new_zzxmsqb a left join xg_xszz_new_zzxmdmb b on a.xmdm=b.xmdm ");
			sql.append("left join xg_xszz_new_zzxmlbb c on b.lbdm = c.lbdm ");
			sql.append("where a.xh = '"+model.getXh()+"' and a.xn = (select dqxn from xtszb) and a.xq = (select dqxq from xtszb) ");
			
			if (!StringUtil.isNull(model.getLbmc())) {
//				params.add(model.getLbmc());
				sql.append(" and c.lbmc like '%'||'"+model.getLbmc()+"'||'%' ");
			}
			sql.append(searchTj);
			sql.append(" order by a.sqsj desc ");
		} else{
			sql.append("select * from (select a.*,decode(a.shzt,'0','未提交','1','通过','2','不通过','3','已退回','5','审核中','6','班级评议中','7','班级评议不通过','未申请') shztmc ");
			sql.append("from (select conditioncheckresult,xmdm,xmmc,je,jesfxssq,xmsm,lbmc,decode(zq,'0','',xh)xh,decode(zq,'0','',xn)xn, ");
			sql.append("decode(zq,'0','',xq)xq,decode(zq,'0','',sqsj)sqsj, ");
			
			
			sql.append("decode(zq,'0','',xqmc)xqmc,decode(zq,'0','',shzt)shzt,decode(zq,'0','',guid)guid, ");
			sql.append("decode(zq,'0','',shlc)shlc,sqkssj,sqjssj,sqkg,kgzt ");
			sql.append("from (select '' conditioncheckresult,t1.xmdm,t1.xmmc,t1.je,t1.jesfxssq,t1.xmsm, ");
			sql.append("(select lbmc from xg_xszz_new_zzxmlbb b where b.lbdm = t1.lbdm) lbmc, ");
			sql.append("t3.xh,t3.xn,t3.xq,t3.sqsj,(select xqmc from xqdzb a where a.xqdm = t3.xq) xqmc,t3.shzt, ");
			sql.append("(case when t1.jgsqzq='0' and t1.sqxn<>t3.xn then '0' ");
			sql.append("when t1.jgsqzq='2' and to_char(to_date(t3.sqsj,'yyyy-mm-dd hh24:mi:ss'),'yyyymm') <> to_char(sysdate,'YYYYMM') then '0' ");
			sql.append("when t1.jgsqzq='3' and to_char(to_date(t3.sqsj,'yyyy-mm-dd hh24:mi:ss'),'yyyymmdd') <> to_char(sysdate,'YYYYMMDD') then '0' else '1' end) zq, ");
			sql.append("t3.guid,t3.shlc,sqkssj,sqjssj,sqkg,case when sqkg = '1' and (sysdate between to_date(nvl(sqkssj, '19900101'), 'yyyymmdd') and ");
			sql.append("to_date(nvl(sqjssj, '20200101'), 'yyyymmdd') + 1) then '开放申请' else '关闭申请' end kgzt ");
			sql.append("from xg_xszz_new_zzxmdmb t1 left join (select * from (select t2.*,row_number() over(partition by xmdm order by sqsj desc) rn ");
			sql.append("from xg_xszz_new_zzxmsqb t2 where t2.xn || t2.xq = (select dqxn || dqxq from xtszb) and xh = '"+model.getXh()+"' ) where rn = 1) t3 on t1.xmdm = t3.xmdm ");

			sql.append("where (t1.sqxn , t1.sqxq) = (select dqxn,dqxq from xtszb)) where kgzt ='开放申请' ) a where 1=1 ");

			if (!StringUtil.isNull(model.getLbmc())) {
//				params.add(model.getLbmc());
				sql.append("and lbmc like '%'||'"+model.getLbmc()+"'||'%' ");
			}
			sql.append(searchTj);
			sql.append(" and nvl(shzt,'0') in ('0', '3'))" );
			
			sql.append("order by sqkg desc,xmmc ");
			
		}
		
		return getPageList(model, sql.toString(), inputValue);
	}
	
	/**
	 * 
	 * @描述:按学生查询已申请和未申请的资助项目 分页查询
	 * @作者：张小彬[工号:1036]
	 * @日期：2014-1-6 上午11:42:53
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String , String>> getAllXmsqInfoList(XszzSqshForm model) throws Exception{
		StringBuilder sql = new StringBuilder();
		
		sql.append("select t1.xmdm,t1.xmmc,t1.je ,t1.jesfxssq, ");
		sql.append(" (select lbmc from xg_xszz_new_zzxmlbb b where b.lbdm = t1.lbdm) lbmc, ");
		sql.append("case when exists (");
		sql.append("select 1 from xg_xszz_new_zzxmsqb t2 where ");
		sql.append("t1.xmdm=t2.xmdm and t2.xn=(select dqxn from xtszb where rownum=1) ");
		sql.append("and t2.xq=(select dqxq from xtszb where rownum=1) ");
		sql.append("and t2.xh=? ) then '1' else '0' end sfsq ");
		sql.append("from xg_xszz_new_zzxmdmb t1 where sqkg='1' and  ");
		sql.append("(sysdate between to_date(sqkssj,'yyyymmdd') and to_date(sqjssj,'yyyymmdd')+1)");
		
		model.getPages().setPageSize(Integer.MAX_VALUE);
		
		return getPageList(model, sql.toString(), new String[]{model.getXh()});
		
	}
	
	/**
	 * 
	 * @描述:批量插入审核状态
	 * @作者：Penghui.Qu
	 * @日期：2013-4-25 下午07:24:59
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param splc
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	@Deprecated
	public boolean insertShzt(XszzSqshForm t ,String splc) throws Exception{
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("insert into xg_xszz_new_zzxmshb(xh,xn,xq,xmdm,xtgwid,shzt,guid) ");
		sql.append("select ?,?,?,?,spgw,?,? from xg_xtwh_spbz where splc=? ");
		
		return dao.runUpdate(sql.toString(), new String[]{t.getXh(),t.getXn(),t.getXq(),t.getXmdm(),t.getShzt(),t.getGuid(),splc});
	}
	
	
	
	/**
	 * 
	 * @描述:按学号、申请时间查询学生申请的项目
	 * @作者：Penghui.Qu
	 * @日期：2013-4-25 下午07:44:14
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return
	 * @throws Exception
	 * XszzSqshForm 返回类型 
	 * @throws
	 */
	public XszzSqshForm getModelByXhSqsj(XszzSqshForm t) throws Exception{
		
		String sql = "select * from xg_xszz_new_zzxmsqb where xh=? and sqsj=? and xmdm=?";
		
		return super.getModel(t, sql, new String[]{t.getXh(),t.getSqsj(),t.getXmdm()});
	}
	
	
	
	/**
	 * 
	 * @描述:审核查询
	 * @作者：Penghui.Qu
	 * @日期：2013-4-26 上午10:28:56
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>>  getShjlList(XszzSqshForm t, User user) throws Exception{

		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t1", "xydm", "bjdm");
		String shgwzByUser = SearchService.getShgwzByUser(user, "t1","xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(t.getSearchModel());

		StringBuilder sql = new StringBuilder();
		sql.append("select * from (select ");
		sql.append("               a.xn||' '||d.xqmc sqzq,d.xqmc,");
		if("12930".equals(Base.xxdm)){
			sql.append(" m.xjztm, ");
		}
		// 审核查询效率问题 暂时关闭该字段 湘潭大学
		if(!"10530".equals(Base.xxdm)){
			sql.append(" pdshztb2.zd5 tzxmje,pdshztb2.zd3 tzxmmc, ");
		}
		if("11799".equals(Base.xxdm)){
			sql.append(" substr(b.shsj,6,2) shyf, ");
		}
		//重庆邮电大学
		if("13627".equals(Base.xxdm)){
			sql.append(" fdy.xm fdyxm,");
		}
		//南京高等职业技术学校(是否有违纪、成绩是否合格)
		if("10874".equals(Base.xxdm)){
			sql.append(" (case when t7.xh is null then '否' else '是' end) sfwj,(case when t8.xh is null then '是' else '否' end) sfhg,t6.ylzd20 jtknlx, ");
		}
		sql.append("               '[' || c.mc || ':' || decode(b.shzt,'0','待审核','1','通过','2','不通过','3','退回','4','需重审','5','审核中',b.shzt) || ']' shztmc ,c.gwz ,c.mc gwmc" );
		sql.append("          ,a.ylzd1, a.xh, a.xn, a.xq, a.sqsj, a.sqly, a.guid, a.shlc, m.ssyq, m.nj, m.xb, m.xm, m.xydm, m.zydm, m.bjdm, m.zybj, m.zybjmc,t2.sydm,t3.symc, m.yhkh, m.xymc, m.zymc, m.bjmc,m.mz,m.mzmc,m.zzmmmc, m.sfzh,a.pdxn, d.xqmc pdxqmc, t1.lbmc, m.RXRQ, m.zd5, m.jtyb, m.jtdzxx, (select dcmc from xg_xszz_new_kndcdmb t6 where t5.rddc = t6.dcdm) kndj, m.sfsfs, x.pyccmc, b.shzt, b.shsj,b.gwid as xtgwid,b.guid as shid,decode(b.zd5,'',a.ylzd1,b.zd5) sqshje,f.xmmc,f.xmdm,z.rddc,row_number() over(partition by a.guid order by b.shsj desc) rn, nvl2(t5.rddc, '是', '否') sfkns ");
		sql.append("          ,(select CYXM from XG_XSZZ_NEW_JTCYB where CYGX='001' and xh =a.xh and rownum=1) jtcy1_xm ");
		sql.append("          ,(select CYXXDW from XG_XSZZ_NEW_JTCYB where CYGX='001' and xh =a.xh and rownum=1) jtcy1_gzdz ");
		sql.append("          ,(select CYLXDH from XG_XSZZ_NEW_JTCYB where CYGX='001' and xh =a.xh and rownum=1) jtcy1_lxdh1 ");
		sql.append("          ,(select CYXM from XG_XSZZ_NEW_JTCYB where CYGX='002' and xh =a.xh and rownum=1) jtcy2_xm ");
		sql.append("          ,(select CYXXDW from XG_XSZZ_NEW_JTCYB where CYGX='002' and xh =a.xh and rownum=1) jtcy2_gzdz ");
		sql.append("          ,(select CYLXDH from XG_XSZZ_NEW_JTCYB where CYGX='002' and xh =a.xh and rownum=1) jtcy2_lxdh1 ");
		sql.append("                  from xg_xszz_new_zzxmsqb a");
		sql.append("                  left join view_xsbfxx m");
		sql.append("                    on a.xh = m.xh");
		sql.append(" left join XG_XTWH_SYBJGLB t2 on t2.bjdm=m.bjdm ");
		sql.append(" left join XG_XTWH_SYDMB t3 on t3.sydm = t2.sydm ");
		sql.append("				  left join view_new_dc_xszz_knsrdcx z");
		sql.append("	                on m.xh = z.xh");
		sql.append("  				  left join XG_XSXX_PYCCDMB x");
        sql.append("           			on m.pycc = x.pyccdm");
		sql.append("                  left join xg_xtwh_shztb b");
		sql.append("                    on a.guid = b.ywid");
		sql.append("                  left join xg_xszz_new_zzxmdmb f");
		sql.append("                    on a.xmdm = f.xmdm ");
		sql.append(" 				  left join xg_xszz_new_zzxmlbb t1 on f.lbdm = t1.lbdm left join xg_xszz_new_knsjgb t5 on a.xh = t5.xh and (a.xn = t5.xn and decode(t5.xq,'on',nvl(a.xq,1),t5.xq) = nvl(a.xq,1)) ");
		if(!"10530".equals(Base.xxdm)){
			sql.append("                  left join (select * from (select pdshztb.*,row_number() over(partition by pdshztb.ywid order by pdshztb.shsj desc) pdrn from xg_xtwh_shztb pdshztb where pdshztb.shsj is not null) where pdrn=1) pdshztb2 on a.guid = pdshztb2.ywid ");
		}
		//重庆邮电大学
		if("13627".equals(Base.xxdm)){
			sql.append(" left join view_xg_fdyxx fdy on m.bjdm = fdy.bjdm ");
		}
		sql.append("          left join xg_xtwh_spgw c");
		sql.append("            on b.gwid = c.id");
		sql.append("          left join xqdzb d");
		sql.append("            on a.xq = d.xqdm");
		//南京高等职业技术学校
		if("10874".equals(Base.xxdm)){
			sql.append(" left join (select distinct(xh) from xg_wjcf_wjcfb) t7 on a.xh = t7.xh ");
			sql.append(" left join (select distinct(xh) from cjb where cj < 60) t8 on a.xh = t8.xh ");
			sql.append(" left join xg_xszz_new_jtqkdcb t6 on a.xh = t6.xh");
		}
		if (YSH.equalsIgnoreCase(t.getShzt())){
			sql.append(" where b.shzt not in ('0','4') ");
		}
		if (DSH.equalsIgnoreCase(t.getShzt())){
			if("14008".equals(Base.xxdm)) {
				sql.append(" where f.shkg='1' and (sysdate between to_date(nvl(f.shkssj,'1990-01-01 00:00'),'yyyy-mm-dd hh24:mi')");
				sql.append(" and to_date(nvl(f.shjssj,'2020-01-01 00:00'),'yyyy-mm-dd hh24:mi')) and ");
				sql.append(" b.shzt in ('0','4') ");
			}else {
				sql.append(" where b.shzt in ('0','4') ");
			}
		}
		if("fdy".equals(user.getUserStatus()) || "sy".equals(user.getUserStatus())){
			sql.append(" and (c.yhjs='"+user.getUserStatus()+"' or c.yhjs is null)");
		}
		sql.append("           and b.gwid in");
//		if("xx".equals(user.getUserStatus())){
			sql.append("               (select spgw from xg_xtwh_spgwyh where spyh = ?)) t1 where 1=1 and rn=1");
//		} else {
//			sql.append("               (select spgw from xg_xtwh_spgwyh where yhjs='"+user.getUserStatus()+"' and spyh = ?)) t1 where 1=1 and rn=1");
//		}

		sql.append(searchTjByUser);
		sql.append(searchTj);
		sql.append(shgwzByUser);
//		sql.append("select * from (select t1.*,t2.xqmc,t3.xmmc,");
//		sql.append("'['||t1.mc||':'||decode(t1.shzt,'0','未审核','1','通过','2','不通过',");
//		sql.append("'3','退回','4','需重审','5','审核中',t1.shzt)||']' shztmc,");
//		sql.append("row_number () over (partition by t1.guid order by t1.guid,t1.spxh) as dqxh, ");
//		sql.append("lag(t1.shzt,1,1) over (order by t1.guid, t1.spxh) as sjzt ");
//		sql.append("from (select a.*,b.xh spxh,c.mc ");
//		sql.append("from (select t1.*,t2.sqsj,t2.shlc,t3.xm,t3.xb,t3.nj,t3.xydm,");
//		sql.append("t3.zydm,t3.bjdm,t3.xymc,t3.zymc,t3.bjmc ");
//		sql.append("from xg_xszz_new_zzxmshb t1 ");
//		sql.append("left join xg_xszz_new_zzxmsqb t2 on t1.guid=t2.guid ");
//		sql.append("left join view_xsjbxx t3 on t1.xh=t3.xh ");
//		sql.append(") a left join xg_xtwh_spbz b on a.xtgwid = b.spgw and a.shlc = b.splc ");
//		sql.append("left join xg_xtwh_spgw c on a.xtgwid = c.id ");
//		sql.append(") t1 left join xqdzb t2 on t1.xq=t2.xqdm ");
//		sql.append("left join xg_xszz_new_zzxmdmb t3 on t1.xmdm = t3.xmdm ");
//		sql.append("where 1=1 ");
//		sql.append(searchTjByUser);
//		sql.append(searchTj);
//		sql.append(") t1 where 1=1 ");
//
//		if (DSH.equalsIgnoreCase(t.getShzt())){
//			sql.append("and (t1.spxh='1' or (t1.spxh <> '1' and t1.sjzt='1')) and (t1.shzt='0' or t1.shzt='4') ");
//		} else {
//			sql.append("and (t1.shzt='1' or t1.shzt='2' or t1.shzt='3') ");
//			sql.append("and t1.shr='");
//			sql.append(user.getUserName());
//			sql.append("' ");
//		}
//
//		sql.append("and exists ( select 1 from (select * from xg_xtwh_spgwyh where spyh=?) t4 where t1.xtgwid=t4.spgw");
//		sql.append(") ");

		return getPageList(t, sql.toString(), StringUtils.joinStrArr(new String[]{user.getUserName()},inputV));
	}



	/**
	 * 
	 * @描述:获取上级审批岗位信息
	 * @作者：Penghui.Qu
	 * @日期：2013-4-26 上午10:51:13
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @deprecated
	 * @throws
	 */
	public HashMap<String,String> getBeforeSpxx(XszzSqshForm t){
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select * from ( ");
		sql.append("select t1.xh,t1.spgw,");
		sql.append("lag(t1.xh,1) over (order by t1.xh) as sjxh,");
		sql.append("lag(t1.spgw,1) over (order by t1.xh) as sjgw,");
		sql.append("nvl(lag(t2.shxmdm,1) over (order by t1.xh),t2.xmdm) as shxm ");
		sql.append("from xg_xtwh_spbz t1 ");
		sql.append("left join xg_xszz_new_zzxmshb t2 on t1.spgw=t2.xtgwid and t2.guid=?");
		sql.append("where splc=(");
		sql.append("select shlc from xg_xszz_new_zzxmsqb where guid=? ");
		sql.append(") ) where spgw=? ");
		
		return dao.getMapNotOut(sql.toString(), new String[]{t.getGuid(),t.getGuid(),t.getXtgwid()});
	}
	
	
	
	/**
	 * 
	 * @描述:执行审核操作（限制条件：前一级 为“通过”，本级为“未审核” or “需重审”）
	 * @作者：Penghui.Qu
	 * @日期：2013-4-26 上午10:56:23
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * int 返回类型 
	 * @deprecated
	 * @throws
	 */
	public int runAuditing(XszzSqshForm t,User user) throws Exception{
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("update xg_xszz_new_zzxmshb t1 set shzt=?,shyj=?,shr=?,shxmdm=?,");
		sql.append("shsj=to_char(sysdate,'yyyy-mm-dd hh24:mi:ss') where guid=? ");
		sql.append("and xtgwid=? and (shzt='0' or shzt='4') and exists (");
		sql.append("select 1 from ( ");
		sql.append("select t1.*,t2.shzt bjzt,nvl(t3.shzt,'1') sjzt from (");
		sql.append("select t1.xh,t1.spgw,t1.splc,");
		sql.append("lag(t1.xh,1,0) over (order by t1.xh) as sjxh,");
		sql.append("lag(t1.spgw,1) over (order by t1.xh) as sjgw ");
		sql.append("from xg_xtwh_spbz t1 where splc=(");
		sql.append("select shlc from xg_xszz_new_zzxmsqb where guid=? ");
		sql.append(")) t1 left join xg_xszz_new_zzxmshb t2 on t1.spgw=t2.xtgwid and t2.guid=? ");
		sql.append("left join xg_xszz_new_zzxmshb t3 on t1.sjgw=t3.xtgwid and t3.guid=? ");
		sql.append(") t2 where t1.xtgwid=t2.spgw and t2.sjzt='1' and (bjzt='0' or bjzt='4') ");
		sql.append(")");
		String guid = t.getGuid();
		
		return dao.update(sql.toString(), new String[]{t.getShzt(),t.getShyj(),user.getUserName(),
													   t.getShxmdm(),guid,t.getXtgwid(),guid,guid,guid});
	}

	
	
	/**
	 * 
	 * @描述:获取下一级审批岗位信息
	 * @作者：Penghui.Qu
	 * @日期：2013-4-26 上午10:59:11
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String,String> getNextSpxx(XszzSqshForm t){
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select * from ( ");
		sql.append("select t1.xh,t1.spgw,");
		sql.append("lead(t1.xh,1) over (order by t1.xh) as xjxh,");
		sql.append("lead(t1.spgw,1) over (order by t1.xh) as xjgw ");
		sql.append("from xg_xtwh_spbz t1 ");
		sql.append("where splc=(");
		sql.append("select shlc from xg_xszz_new_zzxmsqb where guid=? ");
		sql.append(") ) where spgw=? ");
		
		return dao.getMapNotOut(sql.toString(), new String[]{t.getGuid(),t.getXtgwid()});
	}
	
	
	
	
	/**
	 * 
	 * @描述:设置审核状态 
	 * @作者：Penghui.Qu
	 * @日期：2013-4-26 上午11:02:22
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param guid
	 * @param gwid
	 * @param shzt
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @deprecated
	 * @throws
	 */
	public boolean setShzt(String guid,String gwid,String shzt) throws Exception{
		
		String sql = "update xg_xszz_new_zzxmshb set shzt=? where guid=? and xtgwid=? ";
		
		return dao.runUpdate(sql, new String[]{shzt,guid,gwid});
	}

	

	/**
	 * 
	 * @描述:查询当前审批岗位 状态
	 * @作者：Penghui.Qu
	 * @日期：2013-4-26 上午11:07:49
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return
	 * String 返回类型 
	 * @deprecated
	 * @throws
	 */
	public String getCurrunShzt(XszzSqshForm t){
		
		String sql = "select shzt from xg_xszz_new_zzxmshb where guid=? and xtgwid=?";
		
		return dao.getOneRs(sql, new String[]{t.getGuid(),t.getXtgwid()}, "shzt");
	}

	

	/**
	 * 
	 * @描述:撤消审核操作
	 * @作者：Penghui.Qu
	 * @日期：2013-4-26 上午11:09:18
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return
	 * @throws Exception
	 * int 返回类型 
	 * @deprecated
	 * @throws
	 */
	public int cancelAuditing(XszzSqshForm t) throws Exception{
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("update xg_xszz_new_zzxmshb t1 set shzt='0',shsj='',shr='',shyj='',shxmdm='' ");
		sql.append("where guid=? and xtgwid=? and exists (select 1 from (");
		sql.append("select t1.xh spxh,t1.spgw,t4.shzt shzt,t1.xjxh,");
		sql.append("t1.xjgw,t2.shzt xjzt,t1.sjxh,t1.sjgw,t3.shzt sjzt from (");
		sql.append("select t1.xh,t1.spgw,");
		sql.append("lag(t1.xh,1,0) over (order by t1.xh) as sjxh,");
		sql.append("lag(t1.spgw,1) over (order by t1.xh) as sjgw,");
		sql.append("lead(t1.xh,1) over (order by t1.xh) as xjxh,");
		sql.append("lead(t1.spgw,1) over (order by t1.xh) as xjgw ");
		sql.append("from xg_xtwh_spbz t1 where splc=( ");
		sql.append("select shlc from xg_xszz_new_zzxmsqb where guid=? ");
		sql.append(")) t1 left join xg_xszz_new_zzxmshb t2 on t1.xjgw=t2.xtgwid and t2.guid=? ");
		sql.append("left join xg_xszz_new_zzxmshb t3 on t1.sjgw=t3.xtgwid and t3.guid=? ");
		sql.append("left join xg_xszz_new_zzxmshb t4 on t1.spgw=t4.xtgwid and t4.guid=? ");
		sql.append(") t2 where t1.xtgwid=t2.spgw and (t2.shzt='2' or (t2.shzt='3' and (sjzt='4' or sjzt is null)) ");
		sql.append("or (t2.shzt='1' and (xjzt='0' or xjzt='4' or xjzt is null)))) ");
		
		String guid = t.getGuid();
		
		return dao.update(sql.toString(), new String[]{guid,t.getXtgwid(),guid,guid,guid,guid});
	}
	

	
	/**
	 * 
	 * @描述:审核流程信息
	 * @作者：Penghui.Qu
	 * @日期：2013-4-26 下午01:44:27
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	@Deprecated
	public List<HashMap<String,String>>  getSplcInfo(XszzSqshForm t){
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select t3.xh spxh,t4.mc gwmc,t5.yhm,t5.xm shr,t6.xmmc,t1.shxmdm,");
		sql.append("decode(t1.shzt,'0','未审核','1','通过','2',");
		sql.append("'不通过','3','退回','4','需重审',t1.shzt) shzt,");
		sql.append("t1.shsj,t1.shyj from xg_xszz_new_zzxmshb t1 ");
		sql.append("left join xg_xszz_new_zzxmsqb t2 on t1.guid = t2.guid ");
		sql.append("left join xg_xtwh_spbz t3 on t2.shlc=t3.splc and t1.xtgwid=t3.spgw ");
		sql.append("left join xg_xtwh_spgw t4 on t3.spgw=t4.id ");
		sql.append("left join yhb t5 on t1.shr=t5.yhm  ");
		sql.append("left join xg_xszz_new_zzxmdmb t6 on t1.shxmdm=t6.xmdm ");
		sql.append("where t1.guid=? order by spxh");
		
		return dao.getListNotOut(sql.toString(), new String[]{t.getGuid()});
	}

	
	
	//单个查询
	public XszzSqshForm getModel(XszzSqshForm t) throws Exception {
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select t1.*  ");
		if(SFBJPY_Y){
			// =============== 班级评议 < =============
			sql.append(" ,decode(t3.shzt,'1', '同意申请', '0', '不同意申请','') bjpyjgshztmc,t3.pyhsj bjpyjgpyhsj,t3.pyhdd bjpyjgpyhdd,t3.pyyj bjpyjgpyyj, ");
			sql.append(" (case when t3.ylzd1 is null then '' else t3.ylzd1 end) bjpyxzcyxms, ");
			sql.append(" (case when t3.ylzd1 is null then '' else t3.ylzd2 end) bjpyxzdbxms ");
			// =============== 班级评议 > =============
		}
		sql.append(" from ( ");
		sql.append("select t1.*,(select xqmc from xqdzb b where t1.pdxq=b.xqdm) pdxqmc, ");
		sql.append("t2.xmmc,t2.je,t3.xqmc from xg_xszz_new_zzxmsqb t1 ");
		sql.append("left join xg_xszz_new_zzxmdmb t2 on t1.xmdm = t2.xmdm ");
		sql.append("left join xqdzb t3 on t1.xq=t3.xqdm ");
		sql.append("where t1.guid=?");
		sql.append(") t1 ");
		if(SFBJPY_Y){
			// =============== 班级评议 < =============
			sql.append(" left join ( ");
			sql.append(" select * from ( ");
			sql.append(" select a.xn,a.xq,a.xmdm,a.sqr,a.tjzt,count(1) over (partition by a.xn,a.xq,a.xmdm,a.sqr order by a.sqr asc) bjpyxzrs, ");
			sql.append(" WM_CONCAT(b.xm) over (partition by a.xn,a.xq,a.xmdm,a.sqr order by a.sqr asc) bjpyxzcyxms, ");
			sql.append(" row_number() over (partition by a.xn,a.xq,a.xmdm,a.sqr order by a.sqr desc) rn ");
			sql.append(" from xg_xszz_new_xszz_bjpyxzpy a ");
			sql.append(" left join xsxxb b on a.bjpyr=b.xh ");
			sql.append(" ) a where rn='1' ");
			sql.append(" ) t2 on (t1.xn=t2.xn and t1.xq=t2.xq and t1.xmdm=t2.xmdm and t1.xh=t2.sqr) ");
			sql.append(" left join (select * from xg_xszz_new_xszz_bjpyxzpyjg where tjzt='1') t3 on t1.guid=t3.sqid ");
			// =============== 班级评议 > =============
		}
		
		return super.getModel(t, sql.toString(), new String[]{t.getGuid()});
	}



	/**
	 * 
	 * @描述:删除项目申请
	 * @作者：Penghui.Qu
	 * @日期：2013-4-26 下午03:37:03
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param values
	 * @return
	 * @throws Exception
	 * int 返回类型 
	 * @throws
	 */
	public int delXmsq(String[] values) throws Exception {
		
		if (values == null || values.length == 0){
			logger.error("删除操作不能进行!");
			throw new NullPointerException();
		}
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("delete from xg_xszz_new_zzxmsqb");
		sql.append(" where (shzt='0' or shzt='3') and ");
		sql.append("(");
		
		for (int i = 0 , n = values.length ; i < n ; i++){
			sql.append("guid=?");
			
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
	 * @描述:删除审核状态
	 * @作者：Penghui.Qu
	 * @日期：2013-4-26 下午03:38:03
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param values
	 * @return
	 * @throws Exception
	 * int 返回类型 
	 * @throws
	 */
	@Deprecated
	public int delShzt(String[] values) throws Exception {
		
		if (values == null || values.length == 0){
			logger.error("删除操作不能进行!");
			throw new NullPointerException();
		}
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("delete from xg_xszz_new_zzxmshb t1");
		sql.append(" where exists (select 1 from xg_xszz_new_zzxmsqb t2 ");
		sql.append(" where t1.guid=t2.guid and t2.shzt='0') and (");
		
		for (int i = 0 , n = values.length ; i < n ; i++){
			sql.append("guid=?");
			
			if (i != n-1){
				sql.append(" or ");
			}
		}
		
		sql.append(")");
		return dao.runDelete(sql.toString(), values);
	}

	
	
	/**
	 * 
	 * @描述:从资助结果级联删除申请信息
	 * @作者：Penghui.Qu
	 * @日期：2013-5-6 下午01:58:57
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param values
	 * @return
	 * @throws Exception
	 * int 返回类型 
	 * @throws
	 */
	public int delXmsqFromZzjg(String[] values) throws Exception{
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("delete from xg_xszz_new_zzxmsqb t1 where ");
		sql.append(" (");

		for (int i = 0 , n = values.length ; i < n ; i++){
			sql.append("guid=?");
			
			if (i != n -1){
				sql.append(" or ");
			}
		}
		
		sql.append(")");
		
		return dao.runDelete(sql.toString(), values);
	}
	
	
	
	/**
	 * 
	 * @描述:从资助结果级联删除审核信息
	 * @作者：Penghui.Qu
	 * @日期：2013-5-6 下午01:59:15
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param values
	 * @return
	 * @throws Exception
	 * int 返回类型 
	 * @throws
	 */
	@Deprecated
	public int delXmshFromZzjg(String[] values) throws Exception{
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("delete from xg_xszz_new_zzxmshb t1 where ");
		sql.append(" (");

		for (int i = 0 , n = values.length ; i < n ; i++){
			sql.append("guid=?");
			
			if (i != n -1){
				sql.append(" or ");
			}
		}
		
		sql.append(")");
		
		return dao.runDelete(sql.toString(), values);
	}
	


	/**
	 * 
	 * @描述:按班级统计出已经审核通过的人数
	 * @作者：Penghui.Qu
	 * @日期：2013-5-7 下午07:31:59
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param rsszMap
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getTgrsByBj(XszzSqshForm t,Map<String,String> rsszMap){
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select count(distinct(ywid)) tgrs from (select a.*,row_number() over(partition by ywid order by shsj desc) lvl from xg_xtwh_shztb a ");
		sql.append(" where not exists (select 1 from ( select row_number()over(partition by ywid order by shsj desc) rn,a.* from xg_xtwh_shztb a");
		sql.append(" where shzt='3' order by shsj desc) b where rn =1 and (a.ywid = b.ywid and a.shsj <= b.shsj)) and gwid=?) t0 ");
		sql.append(" where shzt = '1' and exists (select 1 ");
		sql.append("from(select * from xg_xszz_new_zzxmsqb t1 where (dqxmdm = ? or dqxmdm is null) and shzt in('1','5') and xn=? and xq=? and xh !=?");
		sql.append("and exists (select 1 from view_xsbfxx t2 ");
		sql.append("where t1.xh = t2.xh and t2.bjdm = (select bjdm from view_xsbfxx where xh = ?)))t1 ");
		sql.append("where t0.ywid=t1.guid) and t0.gwid = ? and t0.lvl='1' ");
		return dao.getOneRs(sql.toString(), new String[]{t.getXtgwid(),t.getShxmdm(),
			rsszMap.get("xn"),rsszMap.get("xq"),t.getXh(),t.getXh(),t.getXtgwid()}, "tgrs");
	}


	
	/**
	 * 
	 * @描述: 按学院查询通过人数
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-6-8 下午02:17:14
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xmdm
	 * @param xn
	 * @param xq
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getTgrsByXy(String xmdm,String xn,String xq){
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select count(1) tgrs,xydm from (");
		sql.append("select t1.xh,t1.shzt,t2.nj,t2.xydm,t2.zydm,t2.bjdm from xg_xszz_new_zzxmsqb t1 ");
		sql.append("left join xsxxb t2 on t1.xh=t2.xh ");
		sql.append(" where t1.xmdm=? and t1.xn=? and t1.xq=? and shzt='1' ");
		sql.append(") group by xydm ");
		
		return dao.getListNotOut(sql.toString(), new String[]{xmdm,xn,xq});
	}
	
	public List<HashMap<String,String>> getTgrsBySy(String xmdm,String xn,String xq){
		StringBuilder sql = new StringBuilder();
		sql.append("select count(1) tgrs,xydm from (");
		sql.append("select t1.xh,t1.shzt,t3.sydm xydm from xg_xszz_new_zzxmsqb t1 ");
		sql.append("left join xsxxb t2 on t1.xh=t2.xh ");
		sql.append("left join XG_XTWH_SYBJGLB t3 on t2.bjdm = t3.bjdm");
		sql.append(" where t1.xmdm=? and t1.xn=? and t1.xq=? and shzt='1' ");
		sql.append(") group by xydm ");

		return dao.getListNotOut(sql.toString(), new String[]{xmdm,xn,xq});
	}
	/**
	 * 
	 * @描述: 按年级、学院查询通过人数
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-6-8 下午02:17:56
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xmdm
	 * @param xn
	 * @param xq
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getTgrsByNjxy(String xmdm,String xn,String xq){
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select count(1) tgrs,nj,xydm from (");
		sql.append("select t1.xh,t1.shzt,t2.nj,t2.xydm,t2.zydm,t2.bjdm from xg_xszz_new_zzxmsqb t1 ");
		sql.append("left join xsxxb t2 on t1.xh=t2.xh ");
		sql.append(" where t1.xmdm=? and t1.xn=? and t1.xq=? and shzt='1' ");
		sql.append(") group by nj,xydm ");
		
		return dao.getListNotOut(sql.toString(), new String[]{xmdm,xn,xq});
	}
	
	
	/**
	 * 
	 * @描述: 按年级、专业查询通过人数
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-6-8 下午02:18:12
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xmdm
	 * @param xn
	 * @param xq
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getTgrsByNjzy(String xmdm,String xn,String xq){
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select count(1) tgrs,nj,zydm from (");
		sql.append("select t1.xh,t1.shzt,t2.nj,t2.xydm,t2.zydm,t2.bjdm from xg_xszz_new_zzxmsqb t1 ");
		sql.append("left join xsxxb t2 on t1.xh=t2.xh ");
		sql.append(" where t1.xmdm=? and t1.xn=? and t1.xq=? and shzt='1' ");
		sql.append(") group by nj,zydm ");
		
		return dao.getListNotOut(sql.toString(), new String[]{xmdm,xn,xq});
	}
	
	
	/**
	 * 
	 * @描述: 按班级查询通过人数
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-6-8 下午02:18:29
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xmdm
	 * @param xn
	 * @param xq
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getTgrsByBj(String xmdm,String xn,String xq){
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select count(1) tgrs,bjdm from (");
		sql.append("select t1.xh,t1.shzt,t2.nj,t2.xydm,t2.zydm,t2.bjdm from xg_xszz_new_zzxmsqb t1 ");
		sql.append("left join xsxxb t2 on t1.xh=t2.xh ");
		sql.append(" where t1.xmdm=? and t1.xn=? and t1.xq=? and shzt='1' ");
		sql.append(") group by bjdm ");
		
		return dao.getListNotOut(sql.toString(), new String[]{xmdm,xn,xq});
	}
	
	
	
	/**
	 * 
	 * @描述: 按学院统计已审核通过的人数
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-5-31 上午11:59:19
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param rsszMap
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getTgrsByXy(XszzSqshForm t,Map<String,String> rsszMap){
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select count(distinct(ywid)) tgrs from (select a.*,row_number() over(partition by ywid order by shsj desc) lvl from xg_xtwh_shztb a");
		sql.append(" where not exists (select 1 from ( select row_number()over(partition by ywid order by shsj desc) rn,a.* from xg_xtwh_shztb a");
		sql.append(" where shzt='3' order by shsj desc) b where rn =1 and (a.ywid=b.ywid and a.shsj<= b.shsj)) and gwid=?) t0 ");
		sql.append(" where shzt = '1' and exists (select 1 ");
		sql.append("from (select * from xg_xszz_new_zzxmsqb t1 where (dqxmdm = ? or dqxmdm is null) and shzt in ('1', '5') and xn=? and xq=? and xh !=? ");
		sql.append("and exists (select 1 from view_xsbfxx t2 ");
		sql.append("where t1.xh = t2.xh and t2.xydm=(select xydm from view_xsbfxx where xh = ?)))t1 ");
		sql.append("where t0.ywid=t1.guid) and t0.gwid = ? and t0.lvl='1'");
		return dao.getOneRs(sql.toString(), new String[]{t.getXtgwid(),t.getShxmdm(),
			rsszMap.get("xn"),rsszMap.get("xq"),t.getXh(),t.getXh(),t.getXtgwid()}, "tgrs");
		
	}
	
	/**
	 * @描述:按书院统计
	 * @作者：张昌路[工号：982]
	 * @日期：2018-9-29 下午03:43:05
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param rsszMap
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getTgrsBySy(XszzSqshForm t,Map<String,String> rsszMap){
		StringBuilder sql = new StringBuilder();
		sql.append("select count(distinct(ywid)) tgrs from (select a.*,row_number() over(partition by ywid order by shsj desc) lvl from xg_xtwh_shztb a");
		sql.append(" where not exists (select 1 from ( select row_number()over(partition by ywid order by shsj desc) rn,a.* from xg_xtwh_shztb a");
		sql.append(" where shzt = '3' order by shsj desc) b where rn = 1 and (a.ywid = b.ywid and a.shsj <= b.shsj)) and gwid = ?) t0 ");
		sql.append(" where shzt = '1' and exists (select 1 ");
		sql.append("from (select * from xg_xszz_new_zzxmsqb t1 where (dqxmdm = ? or dqxmdm is null) and shzt in ('1', '5') and xn = ? and xq = ? and xh != ? ");
		sql.append("and exists (select 1 from view_xsjbxx t2 ");
		sql.append("where t1.xh = t2.xh and t2.sydm1 = (select sydm1 from view_xsjbxx where xh = ?)))t1 ");
		sql.append("where t0.ywid = t1.guid) and t0.gwid = ? and t0.lvl = '1'");
		return dao.getOneRs(sql.toString(), new String[]{t.getXtgwid(),t.getShxmdm(),rsszMap.get("xn"),rsszMap.get("xq"),t.getXh(),t.getXh(),t.getXtgwid()}, "tgrs");
	}
	
	/**
	 * 
	 * @描述:按年级、学院统计已通过人数
	 * @作者：Penghui.Qu
	 * @日期：2013-5-7 下午07:36:04
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param rsszMap
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getTgrsByNjxy(XszzSqshForm t,Map<String,String> rsszMap){
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select count(distinct(ywid)) tgrs  from (select a.*,row_number() over(partition by ywid order by shsj desc) lvl from xg_xtwh_shztb a");
		sql.append(" where not exists (select 1 from ( select row_number()over(partition by ywid order by shsj desc) rn,a.* from xg_xtwh_shztb a");
		sql.append("  where shzt='3' order by shsj desc) b where rn =1 and (a.ywid=b.ywid and a.shsj <= b.shsj)) and gwid=?) t0 ");
		sql.append(" where shzt = '1' and exists (select 1 ");
		sql.append("from (select * from xg_xszz_new_zzxmsqb t1 where (dqxmdm = ? or dqxmdm is null) and  shzt in ('1','5') and xn=? and xq=? and xh !=? ");
		sql.append("and exists (select 1 from view_xsbfxx t2 ");
		sql.append("where t1.xh = t2.xh and t2.nj=(select nj from view_xsbfxx where xh = ?)  ");
		sql.append("and t2.xydm=(select xydm from view_xsbfxx where xh = ?)))t1 ");
		sql.append("where t0.ywid=t1.guid) and t0.gwid= ? and t0.lvl='1' ");
		
		return dao.getOneRs(sql.toString(), new String[]{t.getXtgwid(),t.getShxmdm(),
			rsszMap.get("xn"),rsszMap.get("xq"),t.getXh(),t.getXh(),t.getXh(),t.getXtgwid()}, "tgrs");
	}


	/**
	 * 
	 * @描述:按年级、专业 统计已通过人数
	 * @作者：Penghui.Qu
	 * @日期：2013-5-7 下午07:36:04
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param rsszMap
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getTgrsByNjzy(XszzSqshForm t,Map<String,String> rsszMap){
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select  count(distinct(ywid)) tgrs from (select a.*,row_number() over(partition by ywid order by shsj desc) lvl from xg_xtwh_shztb a");
		sql.append(" where not exists (select 1 from ( select row_number()over(partition by ywid order by shsj desc) rn,a.* from xg_xtwh_shztb a");
		sql.append(" where shzt='3' order by shsj desc) b where rn =1 and (a.ywid=b.ywid and a.shsj <= b.shsj)) and gwid=?) t0 ");
		sql.append(" where shzt = '1' and exists (select 1 ");
		sql.append("from (select * from xg_xszz_new_zzxmsqb t1 where (dqxmdm = ? or dqxmdm is null) and shzt in ('1', '5') and xn=? and xq=? and xh !=? ");
		sql.append("and exists (select 1 from view_xsbfxx t2 ");
		sql.append("where t1.xh = t2.xh and t2.nj=(select nj from view_xsbfxx where xh = ?)  ");
		sql.append("and t2.zydm=(select zydm from view_xsbfxx where xh = ?)))t1 ");
		sql.append("where t0.ywid=t1.guid)  and t0.gwid=? and t0.lvl='1' ");
		return dao.getOneRs(sql.toString(), new String[]{t.getXtgwid(),t.getShxmdm(),rsszMap.get("xn"),rsszMap.get("xq"),t.getXh(),t.getXh(),t.getXh(),t.getXtgwid()}, "tgrs");
	}



	/**
	 * 
	 * @描述:查询在申请表中不存在的ID
	 * @作者：Penghui.Qu
	 * @日期：2013-5-8 下午07:36:09
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ids
	 * @return
	 * @throws SQLException
	 * String[] 返回类型 
	 * @throws
	 */
	public String[] getExistsId(String[] ids) throws SQLException{
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select guid from xg_xszz_new_zzxmsqb where (");
		
		for (int i = 0 , n = ids.length ; i < n ; i++){
			sql.append("guid=?");
			if (i != n-1){
				sql.append(" or ");
			}
		}
		
		sql.append(")");
		
		return dao.getArray(sql.toString(), ids, "guid");
	}


	
	/**
	 * 
	 * @描述:查询是否存在审核流程中的记录
	 * @作者：ligl
	 * @日期：2013-4-18 下午02:42:55
	 * @修改记录: void 返回类型
	 * @throws
	 */
	public int getRsszCount(String xmdm)
			throws Exception {
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select count(1) num from xg_xszz_new_zzxmsqb a ");
		sql.append("where xmdm=? and (shzt='0' or shzt='5') and ");
		sql.append("xn=(select dqxn from xtszb where rownum=1) and ");
		sql.append("xq=(select dqxq from xtszb where rownum=1)");
		sql.append("  and exists(select 1 from xg_xtwh_shztb t where a.guid=t.ywid)");
		
		return Integer.valueOf(dao.getOneRs(sql.toString(),new String[]{xmdm},"num"));
	}
	
	


	/**
	 * 
	 * @描述: 审核情况统计
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-5-22 下午01:43:12
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xn
	 * @param xq
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @deprecated
	 */
	public List<HashMap<String,String>> getShqkTjxx(String xn,String xq,String xmdm){
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select sum(dsh) dsh,sum(tg) tg,sum(btg) btg,xtgwid,xydm from ( ");
		sql.append("select t1.xh,t1.xn,t1.xq,");
		sql.append("case when t1.shzt='0' or t1.shzt='4' then 1 else 0 end dsh,");
		sql.append("case when t1.shzt='1' then 1 else 0 end tg,");
		sql.append("case when t1.shzt='2' then 1 else 0 end btg,");
		sql.append("t1.xtgwid,t2.xydm from xg_xszz_new_zzxmshb t1 ");
		sql.append("left join xsxxb t2 on t1.xh=t2.xh where t1.xn=? and t1.xq=? and xmdm=?) ");
		sql.append("group by xtgwid,xydm order by xydm ");
		
		return dao.getListNotOut(sql.toString(), new String[]{xn,xq,xmdm});
	}

	
	
	/**
	 * 
	 * @描述: 各学院总人数统计
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-5-22 下午01:47:26
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @deprecated
	 */
	public List<HashMap<String,String>> getXyrsList(){
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select t1.bmdm,t1.bmmc,t2.zrs from zxbz_xxbmdm t1 ");
		sql.append("left join (select count(1) zrs,xydm from ");
		sql.append("view_xsjbxx group by xydm) t2 on t1.bmdm=t2.xydm ");
		sql.append("where t1.bmlb='5' ");
		
		return dao.getListNotOut(sql.toString(), new String[]{});
	}



	/**
	 * 
	 * @描述: 按学院统计各统计人数
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-5-23 上午09:18:03
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param bmdm
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @deprecated
	 */
	public List<HashMap<String,String>> getBjrsList(String bmdm){
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select count(1) zrs,t1.bjdm,t1.bjmc bmmc from view_xsjbxx t1 ");
		sql.append("where t1.xydm=? group by t1.bjdm,t1.bjmc ");
		
		return dao.getListNotOut(sql.toString(), new String[]{bmdm});
	}


	
	/**
	 * 
	 * @描述: 审核情况统计
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-5-23 上午09:25:44
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xn
	 * @param xq
	 * @param xydm
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @deprecated
	 */
	public List<HashMap<String,String>> getShqkTjxx(String xn,String xq,String xydm ,String xmdm){
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select sum(dsh) dsh,sum(tg) tg,sum(btg) btg,xtgwid,bjdm from ( ");
		sql.append("select t1.xh,t1.xn,t1.xq,");
		sql.append("case when t1.shzt='0' or t1.shzt='4' then 1 else 0 end dsh,");
		sql.append("case when t1.shzt='1' then 1 else 0 end tg,");
		sql.append("case when t1.shzt='2' then 1 else 0 end btg,");
		sql.append("t1.xtgwid,t2.bjdm from xg_xszz_new_zzxmshb t1 ");
		sql.append("left join xsxxb t2 on t1.xh=t2.xh where t1.xn=? and t1.xq=? and t1.xmdm=? and t2.xydm=?) ");
		sql.append("group by xtgwid,bjdm order by bjdm ");
		
		return dao.getListNotOut(sql.toString(), new String[]{xn,xq,xmdm,xydm});
	}



	
	
	public List<HashMap<String,String>> getShqkTjxx(User user,String xn,String xq,String xmdm){
		
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t2", "xydm", "bjdm");		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select t1.xtgwid,t3.mc gwmc,t1.dcl,t1.ycl,t2.spxh");
		sql.append(" from (select sum(t1.dcl) dcl, sum(t1.ycl) ycl, xtgwid");
		sql.append(" from (select t1.xh,t1.xn,t1.xq,case when t1.shzt in ( '0','4') then 1 else 0 end dcl,");
		sql.append(" case when t1.shzt not in ( '0','4') then 1 else 0 end ycl,");
		sql.append(" t1.xtgwid, t3.nj,t3.xydm,t3.zydm,t3.bjdm");
		sql.append(" from (select a.xh,a.xn,a.xq,b.shzt,b.gwid xtgwid,");
		sql.append(" case when b.shzt = '4' then a.xmdm else lag(b.zd2,1,a.xmdm)over(partition by a.guid order by b.shsj) end xmdm,");
		sql.append(" row_number() over(partition by b.ywid,b.gwid order by shsj desc) lvl ");
		sql.append(" from xg_xszz_new_zzxmsqb a left join xg_xtwh_shztb b on a.guid=b.ywid where b.gwid is not null) t1");
		sql.append(" left join xsxxb t2 on t1.xh=t2.xh left join view_njxyzybj_all t3 on t2.bjdm=t3.bjdm");
		sql.append(" where t1.lvl = '1' and t1.xn = ? and t1.xq = ? and t1.xmdm = ? and exists ");
		sql.append(" (select 1 from xsxxb t2 where t1.xh = t2.xh ");
		sql.append(searchTjByUser);
		sql.append(" )) t1 group by t1.xtgwid) t1");
		sql.append(" left join (select spgw, xh as spxh from xg_xtwh_spbz where");
		sql.append(" splc = (select splc from xg_xszz_new_zzxmdmb where xmdm = ?)) t2");
		sql.append(" on t1.xtgwid = t2.spgw left join xg_xtwh_spgw t3 on t1.xtgwid = t3.id order by t2.spxh");
		
		return dao.getListNotOut(sql.toString(), new String[]{xn,xq,xmdm,xmdm});
	}
	
	
	
	
	
	
	/**
	 * 
	 * @描述: 查询项目的申请人数
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-6-4 上午08:44:24
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param user
	 * @param xn
	 * @param xq
	 * @param xmdm
	 * @return
	 * String 返回类型 
	 */
	public String getSqrs(User user,String xn,String xq,String xmdm){
		
		StringBuilder sql = new StringBuilder();
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t2", "xydm", "bjdm");		
		
		sql.append("select count(1) zrs from xg_xszz_new_zzxmsqb t1 ");
		sql.append("where t1.xn=? and t1.xq=? and t1.xmdm=? ");
		sql.append("and exists (select 1 from xsxxb t2 where t1.xh=t2.xh ");
		sql.append(searchTjByUser);
		sql.append(")");
		
		return dao.getOneRs(sql.toString(), new String[]{xn,xq,xmdm}, "zrs");
	}
	
	
	/**
	 * 
	 * @描述: 查询审核统计中具体学生
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-6-4 上午08:54:20
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getStudentsFromShtj(XszzSqshForm t, User user) throws Exception {

		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t1", "xydm", "bjdm");		
		String[] inputValue = SearchService.getTjInput(t.getSearchModel());
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from (");
		sql.append(" select t1.sqid,nvl(t1.shr,' ') shr,nvl(t1.shsj,' ') shsj,nvl(t1.shyj,' ') shyj,t1.shid,");
		sql.append(" t2.xh,t6.xm,t2.xmdm,t3.xmmc,t4.mc gwmc,t5.xh spxh,");
		sql.append(" (case t6.xb when '1' then  '男' when '2' then '女' else t6.xb end) xb,");
		sql.append(" t7.nj,t7.xymc,t7.xydm,t7.zymc,t7.zydm,t7.bjmc,t7.bjdm,t1.shzt,decode(t1.shzt,'0','待审核', '1','通过','2','不通过','3','退回','4','需重审')shztmc");
		sql.append(" from xg_xszz_new_zzxmsqb t2 left join (select ywid sqid,gwid shid,shzt,shsj,shyj,shr,row_number()");
		sql.append(" over(partition by ywid, gwid order by shsj desc) as lvl from xg_xtwh_shztb) t1");
		sql.append(" on t1.sqid=t2.guid");
		sql.append(" left join xg_xszz_new_zzxmdmb t3 on t2.xmdm=t3.xmdm ");
		sql.append(" left join xg_xtwh_spgw t4 on t1.shid=t4.id");
		sql.append(" left join xg_xtwh_spbz t5 on t5.splc=t3.splc and t5.spgw=t1.shid");
		sql.append(" left join xsxxb t6 on t2.xh=t6.xh ");
		sql.append(" left join view_njxyzybj t7 on t6.bjdm=t7.bjdm");
		sql.append(" where t1.lvl=1 and t2.xn=? and t2.xq=?");
		if (Constants.WSH.equals(t.getShzt())){
			sql.append(" and t1.shzt in ('0','4') ");
		}else{
			sql.append(" and t1.shzt not in ('0','4') "); 
		}
		sql.append(" and t2.dqxmdm=? and shid=?) t1 where 1=1");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return super.getPageList(t, sql.toString(),StringUtils.joinStrArr(new String[]{t.getXn(),t.getXq(),t.getXmdm(),t.getShid()},inputValue));
	}



	/** 
	 * @描述:获取人数控制基别
	 * @作者：陈敏杰
	 * @日期：2013-12-19 下午01:44:21
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param shxmdm
	 * @return
	 * String 返回类型 
	 * @throws 
	 */
	public String getRskzXh(String shxmdm) {
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select xh from xg_xszz_new_zzxmdmb a left join XG_XTWH_SPBZ b on a.splc=b.splc and a.rskzjb=b.spgw where xmdm = ? ");
		
		return dao.getOneRs(sql.toString(), new String[]{shxmdm}, "xh");
	}
	
	
	/** 
	 * @描述:获取最后一级审核岗位
	 * @作者：xiaxia
	 * @日期：2014-11-21上午08:44:21
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param shxmdm
	 * @return
	 * String 返回类型 
	 * @throws 
	 */
	public String getLastGwXh(String shxmdm) {
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select max(xh) lastGw from xg_xszz_new_zzxmdmb a left join XG_XTWH_SPBZ b on a.splc=b.splc where xmdm= ? ");
		
		return dao.getOneRs(sql.toString(), new String[]{shxmdm}, "lastGw");
	}



	/**
	 * 
	 * @描述:更新申请记录
	 * @作者：cmj
	 * @日期：2013-12-13 下午05:31:22
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param sqid
	 * @param shid
	 * @param shzt
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean updateSqjl(String ywid, String tzhxmdm, String shzt,String dqxmdm) throws Exception{
		String sql = "update xg_xszz_new_zzxmsqb set shzt=? ,tzhxmdm = ?,dqxmdm=? where guid = ?";
		
		return dao.runUpdate(sql, new String[]{shzt,tzhxmdm,dqxmdm,ywid});
		
	}
	/**
	 * 
	 * @描述:获取学生申请项目的项目代码
	 * @作者：xiaxia[工号：1104]
	 * @日期：2014-10-13 下午04:57:19
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ywid
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getXmdm(String ywid){
		String sql ="select xmdm from xg_xszz_new_zzxmsqb where guid = ?";
		return dao.getOneRs(sql, new String[]{ywid}, "xmdm");
	}
	
	
	/**
	 * 
	 * @描述:提交申请的人数按项目、岗位、班级(其他)分组
	 * @作者：hj[945]
	 * @日期：2013-12-19 下午03:02:14
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param sqids
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getXzdrs(String sqids){
		StringBuilder sql = new StringBuilder();
		sql.append(" select c.rskzfw,g.xh as kzjb,decode(a.tzhxmdm,null,a.dqxmdm,a.tzhxmdm) as xmdm, ");
		sql.append(" decode(c.rskzfw,'bj',d.bjdm,'cpz',e.cpzdm,'xx','xx','xy',d.xydm,'njxy',d.nj||d.xydm,'njzy',d.nj||d.zydm) as cpbm, ");
		sql.append(" i.xh as dqjb,count(*) as xzrs from XG_XSZZ_NEW_ZZXMSQB a ");
		sql.append(" left join ( select * from ( ");
		sql.append(" select c.*,row_number() over(partition by c.ywid order by c.shsj desc) as rn  from xg_xtwh_shztb c "); 
		sql.append(" where c.shzt in ('0','4') ) where rn =1 ) b on a.guid=b.ywid ");
		sql.append(" left join XG_XSZZ_NEW_ZZXMDMB c on decode(a.tzhxmdm,null,a.dqxmdm,a.tzhxmdm)=c.xmdm ");
		sql.append(" left join view_xsjbxx d on a.xh=d.xh ");
		sql.append(" left join XG_ZHCP_FSTJJLB e on d.bjdm=e.bjdm and a.xn=e.xn and a.xq=e.xq ");
		sql.append(" left join xg_xtwh_splc f on c.splc=f.id ");
		sql.append(" left join xg_xtwh_spbz g on f.id=g.splc and c.rskzjb=g.spgw ");
		sql.append(" left join xg_xtwh_splc h on c.splc=h.id ");
		sql.append(" left join xg_xtwh_spbz i on h.id=i.splc and b.gwid=i.spgw ");
		sql.append(" where a.guid in ("+sqids+")  ");
		sql.append(" group by c.rskzfw,g.xh,decode(a.tzhxmdm,null,a.dqxmdm,a.tzhxmdm), "); 
		sql.append(" decode(c.rskzfw,'bj',d.bjdm,'cpz',e.cpzdm,'xx','xx','xy',d.xydm,'njxy',d.nj||d.xydm,'njzy',d.nj||d.zydm),i.xh ");
		return dao.getListNotOut(sql.toString(), new String[]{});
	}
	
	/**
	 * 
	 * @描述:通过项目代码、控制级别、参评部门(可以班级、年级等)得到该条件下控制的人数以及控制级别之后（含）通过的人数
	 * @作者：hj[945]
	 * @日期：2013-12-19 下午02:50:15
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xmdm
	 * @param kzjb
	 * @param cpbm
	 * @return
	 * @throws Exception
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getKzrsTgrsByXmdm(String xmdm,String kzjb,String cpbm,String kzfs) {		StringBuilder sb = new StringBuilder();
		sb.append(" select a.xmdm,a.zzrs as zzme,nvl(b.ytggs,0) as ytggs from ( ");
		sb.append(" select a.* from XG_XSZZ_NEW_ZZXMRSSZB a left join xg_xszz_new_zzxmdmb b on a.xmdm=b.xmdm and a.xn=b.sqxn and a.xq=b.sqxq ");
		sb.append(" where zzrs is not null and b.xmdm=?  ");
		
		if (Constants.RSKZFW_NJXY.equals(kzfs)){ 			//年级学院
			sb.append(" and a.nj||a.bmdm=? ) a ");
		} else if (Constants.RSKZFW_NJZY.equals(kzfs)){	//年级专业
			sb.append(" and a.nj||a.bmdm=? ) a ");
		} else if (Constants.RSKZFW_XY.equals(kzfs)){		//学院
			sb.append(" and a.bmdm=? ) a ");
		} else if (Constants.RSKZFW_BJ.equals(kzfs)) {	//班级
			sb.append(" and a.bmdm=? ) a ");
		} else {								//学校
			sb.append(" and a.bmdm=? ) a ");
		}
		
		sb.append(" left join ( select ? as xmdm,count(1) ytggs from ( ");
		sb.append(" select t3.xh,t2.shzt,row_number()over(partition by t1.guid order by t2.shsj desc) rn ");
		sb.append(" from xg_xszz_new_zzxmsqb t1 ");
		sb.append(" left join xg_xtwh_shztb t2 on t1.guid=t2.ywid ");
		sb.append(" left join xg_xtwh_spbz t3 on t2.gwid=t3.spgw and t2.lcid=t3.splc ");
		sb.append(" left join view_xsbfxx t4 on t1.xh=t4.xh ");
		sb.append(" where t1.shzt in (?,?) and t1.dqxmdm = ? ");
		if (Constants.RSKZFW_NJXY.equals(kzfs)){ 			//年级学院
			sb.append(" and t4.nj||t4.xydm=? ");
		} else if (Constants.RSKZFW_NJZY.equals(kzfs)){	//年级专业
			sb.append(" and t4.nj||t4.zydm=? ");
		} else if (Constants.RSKZFW_XY.equals(kzfs)){		//学院
			sb.append(" and t4.xydm=?  ");
		} else if (Constants.RSKZFW_BJ.equals(kzfs)) {	//班级
			sb.append(" and t4.bjdm=?  ");
		} else {								//学校
			sb.append(" ");
		}
		
		sb.append(" ) where rn =1 and ((shzt <> ? and to_number(xh) = to_number(?)) or to_number(xh) > to_number(?)) ) b on a.xmdm=b.xmdm ");
		String[] inputValue = { xmdm,cpbm,xmdm,Constants.YW_TG,Constants.YW_SHZ,xmdm,cpbm,Constants.SH_DSH,kzjb,kzjb};
		return dao.getMapNotOut(sb.toString(), inputValue);
	}
	
	/**
	 * 
	 * @描述:返回在资助结果中某个项目的申请成功记录数
	 * @作者：王志刚
	 * @日期：2014-5-4 下午03:16:42
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xmmc 项目名称
	 * @param xh 学号
	 * @return String 返回类型
	 * @throws
	 */
	public String getTheSameZzxmNumber(String xmmc, String xh) {
		String num = "";
		StringBuilder sql = new StringBuilder(
				"select count(guid) num from VIEW_NEW_DC_XSZZ_ZZMXJG t where t.XMMC = ? and t.xh = ? ");
		
		num = dao.getOneRs(sql.toString(), new String[] { xmmc, xh }, "num");
		return num;
	}
	
	/**
	 * 
	 * @描述:大学期间同时享受其他奖(助)学金
	 * @作者：王志刚
	 * @日期：2014-5-4 下午03:57:42
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xmmc 项目名称
	 * @param xh 学号
	 * @return
	 * @throws
	 */
	public List<HashMap<String,String>> getYwqtjxList(String xmmc, String xh){
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select t.* from VIEW_NEW_DC_XSZZ_ZZMXJG t where t.XMMC<>? and t.XH=? ");
		
		return dao.getListNotOut(sql.toString(), new String[]{xmmc, xh});
	}
	/**
	 * 批量审核时，判断学生申请总金额是否超过隶属学院的总金额
	 */
	public boolean checkPlshJe(String sqids, String sqshXmdms){
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1) num from ( ");
		sql.append(" select xmdm,xydm, ");
		sql.append(" case when xyje = 0 then 'T' else( ");
		sql.append("  case when ytgje + sqshje > xyje then 'F' else 'T' end ");
		sql.append(" ) end rs ");
		sql.append(" from ( ");
		sql.append(" select a.xmdm,a.xydm,nvl(a.sqshje,0) sqshje,nvl(b.ytgje,0) ytgje,nvl(c.xyje,0) xyje from ( ");
		// ============ 审核时，勾选的申请总金额 begin ===========
		sql.append(" select * from ( ");
		sql.append(" select xmdm,xydm,sum(sqshje) over (partition by xmdm,xydm order by xmdm) sqshje, ");
		sql.append(" row_number() over (partition by xmdm,xydm order by xmdm) rn from ( ");
		sql.append(" select a.xmdm,c.xydm,decode(b.zd5,'',a.ylzd1,b.zd5) sqshje from XG_XSZZ_NEW_ZZXMSQB a ");
		sql.append(" left join ( ");
		sql.append(" select * from ( ");
		sql.append(" select c.*,row_number() over(partition by c.ywid order by c.shsj desc) as rn  from xg_xtwh_shztb c ");
		sql.append(" where c.shsj is not null ");
		sql.append(" ) where rn =1 ");
		sql.append(" ) b on a.guid=b.ywid ");
		sql.append(" left join view_xsjbxx c on a.xh=c.xh ");
		sql.append(" where a.guid in ("+sqids+") ");
		sql.append(" ) a ");
		sql.append(" ) a where a.rn=1 ");
		// ============ 审核时，勾选的申请总金额 end ===========
		sql.append(" ) a left join ( ");
		// ============ 已通过的总金额 begin ============
		sql.append(" select * from ( ");
		sql.append(" select xmdm,xydm,sum(je) over (partition by xmdm,xydm order by xmdm) ytgje, ");
		sql.append(" row_number() over (partition by xmdm,xydm order by xmdm) rn from ( ");
		sql.append(" select a.je,b.xmdm,c.xydm from xg_xszz_new_zzxmjgb a ");
		sql.append(" left join xg_xszz_new_zzxmsqb b on a.lylcywid=b.GUID ");
		sql.append(" left join view_xsjbxx c on a.xh=c.xh ");
		sql.append(" where b.xmdm in ("+sqshXmdms+") ");
		sql.append(" ) a ");
		sql.append(" ) a where a.rn=1 ");
		// ============ 已通过的总金额 end ==========
		sql.append(" ) b on a.xmdm=b.xmdm and a.xydm=b.xydm ");
		sql.append(" left join ( ");
		// ============ 学院最大金额 begin ===========
		sql.append(" select a.xydm,a.zdm,a.je xyje,b.xmdm from xg_xszz_new_xmjf a ");
		sql.append(" left join xg_xszz_new_xmfz b on a.zdm=b.zdm ");
		sql.append(" where b.xmdm in ("+sqshXmdms+") ");
		// ============ 学院最大金额 begin ===========
		sql.append(" ) c on a.xmdm=c.xmdm and a.xydm=c.xydm ");
		sql.append(" ) a ");
		sql.append(" ) a where a.rs='F' ");
		String num = dao.getOneRs(sql.toString(), new String[] { }, "num");
		if("0".equals(num) || "".equals(num)){
			return true;
		}
		return false;
	}
	
	/**
	 * 
	 * @描述:返回班级人数
	 * @作者：王志刚
	 * @日期：2014-5-29 下午06:57:42
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh 学号
	 * @return int 返回类型
	 * @throws
	 */
	public String countBjRs(String xh) {
		String num = "";
		StringBuilder sql = new StringBuilder(
				"select count(a.xh) num from VIEW_XSJBXX a where a.bjdm = (select t.bjdm from VIEW_XSJBXX t where t.xh=?)");
		num = dao.getOneRs(sql.toString(), new String[] { xh }, "num");
		return num;
	}

	
	/**
	 * 
	 * @描述: 查询已通过的额度
	 * @作者：屈朋辉[工号：445]
	 * @日期：2014-10-23 下午02:08:14
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param xmdm
	 * @param gwid
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getYtgJe(String xh,String xmdm,String gwid){
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select nvl(sum(nvl(t3.zd5,t4.je)),0) je from xg_xszz_new_zzxmsqb t1 ");
		sql.append(" left join xg_xtwh_shztb t3 on t1.guid = t3.ywid");
		sql.append(" left join xg_xszz_new_zzxmdmb t4 on t1.dqxmdm = t4.xmdm");
		sql.append(" where exists (select 1 from xg_xszz_new_xmfz t2 where t1.dqxmdm=t2.xmdm ");
		sql.append(" and t2.zdm=(select zdm from xg_xszz_new_xmfz where xmdm=?))");
		sql.append(" and t3.shzt='1' and t1.shzt in ('1','5') and t3.gwid=?  and exists (");
		sql.append(" select 1 from view_xsjbxx t5 where t1.xh = t5.xh ");
		sql.append(" and t5.xydm=(select xydm from view_xsjbxx where xh = ?))");
		
		return dao.getOneRs(sql.toString(), new String[]{xmdm,gwid,xh}, "je");
	}
	
	/**
	 * 
	 * @描述: 查询学院的项目金额
	 * @作者：屈朋辉[工号：445]
	 * @日期：2014-10-23 下午02:13:38
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param xmdm
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getXmzJe(String xh ,String xmdm){
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select je from xg_xszz_new_xmjf where xydm=(");
		sql.append(" select xydm from view_xsjbxx where xh = ?");
		sql.append(" ) and zdm = (select zdm from xg_xszz_new_xmfz where xmdm=?)");
		return dao.getOneRs(sql.toString(), new String[]{xh,xmdm}, "je");
	}
	
	
	/**
	 * 
	 * @描述:查询已申请的所有项目
	 * @作者：ligl
	 * @日期：2014-3-26 下午03:33:46
	 * @修改记录: 
	 * @param xh
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */	
	public List<HashMap<String, String>> getYsqxmAll(XszzSqshForm model,User user) throws Exception {
		
		String searchTjByUser = SearchService.getSearchTjByUser(user, "e", "xydm", "bjdm");		
		StringBuilder sb = new StringBuilder();
	
		sb.append("select dqxmdm,xmmc,count(1) sqrs,sum(dsh) dsh from (");
		sb.append(" select a.xn, a.xq, a.dqxmdm, a.xh, case when b.shzt in ('0', '4') then 1 else 0 end dsh, c.xmmc,b.gwid");
		sb.append(" from xg_xszz_new_zzxmsqb a");
		sb.append(" left join xg_xtwh_shztb b on a.guid = b.ywid");
		sb.append(" left join xg_xszz_new_zzxmdmb c on a.dqxmdm = c.xmdm");
		sb.append(" left join view_xsbfxx e on a.xh=e.xh");
		sb.append(" left join xg_xtwh_spgwyh f on b.gwid=f.spgw");
		sb.append(" where a.xn||a.xq=(select dqxn||dqxq from xtszb) and spyh = '"+user.getUserName()+"'");
		sb.append(searchTjByUser);
		sb.append(" ) group by dqxmdm,xmmc");
		String[] input = {};
		return dao.getListNotOut(sb.toString(), input);
	}
	
	/**
	 * 
	 * @描述:查询已申请的所有项目(已审核)
	 * @作者：ligl
	 * @日期：2014-3-28 下午02:12:56
	 * @修改记录: 
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getYsqxmAllYsh(XszzSqshForm model,User user) throws Exception {
		
		String searchTjByUser = SearchService.getSearchTjByUser(user, "e", "xydm", "bjdm");		
		StringBuilder sb = new StringBuilder();
	
		sb.append("select xn,xq,dqxmdm,xmmc,count(1) sqrs from (");
		sb.append(" select a.xn, a.xq, a.dqxmdm, a.xh, b.shzt, c.xmmc,b.gwid,");
		sb.append(" row_number() over(partition by b.ywid order by b.shsj desc) as lvl");
		sb.append(" from xg_xszz_new_zzxmsqb a");
		sb.append(" left join xg_xtwh_shztb b");
		sb.append(" on a.guid = b.ywid");
		sb.append(" left join xg_xszz_new_zzxmdmb c");
		sb.append(" on a.dqxmdm = c.xmdm");	
		sb.append(" left join VIEW_XSBFXX e");
		sb.append(" on a.xh=e.xh");
		sb.append(" left join xg_xtwh_spgwyh f");
		sb.append(" on b.gwid=f.spgw");
		sb.append(" where b.shzt not in ('0', '4')");
		sb.append(" and spyh = '"+user.getUserName()+"'");
		sb.append(searchTjByUser);
		sb.append(" ) where lvl = '1'  group by xn,xq,dqxmdm,xmmc");
		
		String[] input = {};
		return dao.getListNotOut(sb.toString(), input);
	}



	/** 
	 * @描述:查询未申请项目
	 * @作者：cq [工号：785]
	 * @日期：2015-10-8 上午10:11:18
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getWsqList(String xh) {

		StringBuilder sql = new StringBuilder();
		
		sql.append("select b.*, a.sqsj,b.pdxn || ' ' || (select xqmc from xqdzb a where a.xqdm = b.pdxq) pdzq ");
		sql.append("from xg_xszz_new_zzxmsqb a left join xg_xszz_new_zzxmdmb b on a.xmdm=b.xmdm ");
		sql.append("where a.xh = ? and a.xn||a.xq = (select dqxn || dqxq from xtszb) ");
		
		return dao.getListNotOut(sql.toString(), new String[]{xh});
	}



	/** 
	 * @描述:根据guid 查询jgsqzq
	 * @作者：cq [工号：785]
	 * @日期：2015-10-9 下午05:28:32
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param values
	 * @return
	 * String 返回类型 
	 * @throws 
	 */
	public String getJgsqzq(String values) {
		
		String sql = "select b.jgsqzq from xg_xszz_new_zzxmsqb a left join xg_xszz_new_zzxmdmb b on a.xmdm=b.xmdm where a.guid = ?";
		
		return dao.getOneRs(sql, new String[]{values}, "jgsqzq");
	}
	
	
	/**
	 * 返回不可提交的项目
	 */
	
	public HashMap<String, String> getFalseXmdm(String values){
		
		StringBuffer sql = new StringBuffer();
		
		sql.append("select b.xmdm,'当前项目只允许'||decode(b.jgsqzq,'0','一学年','1','一学期','2','一月','3','一日')||'申请一次'jgsqzq from (select * from ( ");
		sql.append("select row_number()over(partition by xh,xmdm order by sqsj desc) rn, b.* from xg_xszz_new_zzxmsqb b ");
		sql.append("where xh||xmdm = (select xh||xmdm from xg_xszz_new_zzxmsqb where guid = ? ");
		sql.append("and shzt not in ('0','3'))) where rn = 1) c left join xg_xszz_new_zzxmdmb b on c.xmdm=b.xmdm ");
		sql.append("where (b.jgsqzq='0' and b.sqxn=c.xn ) ");
		sql.append("or (b.jgsqzq='1' and b.sqxn||b.sqxq=c.xn||xq ) ");
		sql.append("or (b.jgsqzq='2' and to_char(to_date(c.sqsj,'yyyy-mm-dd hh24:mi:ss'),'yyyymm') = to_char(sysdate,'YYYYMM')) ");
		sql.append("or (b.jgsqzq='3' and to_char(to_date(c.sqsj,'yyyy-mm-dd hh24:mi:ss'),'yyyymmdd') = to_char(sysdate,'YYYYMMDD'))");
		
		return dao.getMapNotOut(sql.toString(), new String[]{values});
	}
	
	/**
	 * 
	 * @描述:获取项目类别
	 * @作者：Huang Chenji
	 * @日期：2015-10-27 上午11:26:33
	 * @修改记录:
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getXmlb() throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append(" select lbmc mc, lbdm dm");
		sb.append(" from  xg_xszz_new_zzxmlbb ");
		sb.append(" order by mc");
		String[] input = {};
		return dao.getListNotOut(sb.toString(), input);
	}



	/** 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：黄辰霁
	 * @日期：2015-12-8 下午12:29:56
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws 
	 */
	public HashMap<String, String> getGyxx(String xh) {
		String sql="select ldmc,qsh,cwh,qsdh from view_xg_gygl_new_cwxx where xh=?";
		return dao.getMapNotOut(sql, new String[] { xh });
	}
	
	/**
	 * @描述: 老师审核通过的最终金额
	 * @作者： Meng.Wei[工号：1186]
	 * @日期：2016-11-25 上午11:36:13
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param xn
	 * @param xq
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String zzxmshtgJe(String xh,String xn,String xq) {
		String zzxmshje = "";
		StringBuilder sql = new StringBuilder(
				" select je zzxmshje from xg_xszz_new_zzxmjgb where xh = ? and xn = ?  and xq = ?");
		zzxmshje = dao.getOneRs(sql.toString(), new String[] { xh,xn,xq}, "zzxmshje");
		return zzxmshje;
	}


	/**
	 *  获取异常数据列表.
	 *<p>申请表中审核状态为5，但未进入审核状态表。</p>
	 *
	 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
	 * @date 2017-11-17 17:59
	 * @param
	 * @return java.util.List<java.util.HashMap<java.lang.String,java.lang.String>>
	 * @throw
	 */
	public List<HashMap<String,String>> getExceptionDataList() {

		String sql = "SELECT * FROM xg_xszz_new_zzxmsqb t WHERE SHZT = '5' "+
				"AND NOT exists(SELECT 1 FROM XG_XTWH_SHZTB WHERE ywid = t.GUID)";
		return  dao.getListNotOut(sql,new String[]{});
	}
	
	
	/**
	 * 
	 * @描述:获取学生助学贷款信息
	 * @作者：Penghui.Qu
	 * @日期：2013-4-26 上午10:51:13
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @deprecated
	 * @throws
	 */
    public  String getXsDkxx(String xn,String xh){
    	StringBuilder sql = new StringBuilder();
		sql.append("select count(1) as count from ");
		sql.append(" (select a.xn ,a.xh from xg_zxdk_syddk a where xh=? "); 
		sql.append(" union "); 
		sql.append(" select b.xn ,b.xh from xg_zxdk_xydkjgb b where xh=? ) where  xn like ?  ");
    	return dao.getOneRs(sql.toString(),new String[]{xh,xh,"%"+xn+"%"},"count");
    }

	/**
	 * @描述:河北建材国家励志奖学金成绩为优秀的课程数
	 * @作者：lgx [工号：1553]
	 * @日期：2018/9/4 17:49
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param: [xh]
	 * @return: java.util.HashMap
	 */
	public String getYxksByXh_12389(String xh,String xn,String kcxz) {
		StringBuffer sql = new StringBuffer();
		sql.append("select count(1) yxkcs from CJB where cj >= (select dycj from cjdzb where dydj like '%优%') ");
		sql.append(" and xh=? and xn=? and kcxz like '%"+kcxz+"%'");
		return dao.getOneRs(sql.toString(),new String[]{xh,xn},"yxkcs");
	}

	/**
	 * @描述:河北建材国家励志奖学金成绩为良的课程数
	 * @作者：lgx [工号：1553]
	 * @日期：2018/9/4 17:49
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param: [xh]
	 * @return: java.util.HashMap
	 */
	public String getLkcsByXh_12389(String xh,String xn,String kcxz) {
		StringBuffer sql = new StringBuffer();
		sql.append("select count(1) yxkcs from CJB where cj<(select dycj from cjdzb where dydj like '%优%') ");
		sql.append("  and cj>=(select dycj from cjdzb where dydj like '%良%') ");
		sql.append(" and xh=? and xn=? and kcxz like '%"+kcxz+"%'");
		return dao.getOneRs(sql.toString(),new String[]{xh,xn},"yxkcs");
	}

	/**
	 * @描述:获取学生成绩专业排名
	 * @作者：lgx [工号：1553]
	 * @日期：2018/9/4 19:31
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param: [xn, xh, bjmc]
	 * @return: java.lang.String
	 */
	public  String getCjpmXy(String xn,String xh,String zydm){
		StringBuilder sql = new StringBuilder();
		sql.append("select b.mc from");
		sql.append("(select a.* ,ROWNUM as mc from");
		sql.append("(select t.* from (SELECT t.xn,t.xh,t1.zydm,sum(t.cj) as cj FROM CJB t ");
		sql.append("left join view_xsjbxx t1 ");
		sql.append(" on t.xh=t1.xh ");
		sql.append(" GROUP BY t.xn,t.xh,t1.zydm ");
		sql.append(" order by t.xn,cj  ) t ");
		sql.append(" where xn like ? and zydm=? order by cj desc) a ) b where b.xh=?");
		return dao.getOneRs(sql.toString(),new String[]{"%"+xn+"%",zydm,xh},"mc");
	}

	/**
	 * @描述:专业总人数
	 * @作者：lgx [工号：1553]
	 * @日期：2018/9/4 19:47
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param: [zydm]
	 * @return: java.lang.String
	 */
	public String getZyzrs(String zydm) {
		StringBuilder sql = new StringBuilder();
		sql.append("select count(0) zyzrs from view_xsjbxx where zydm=?");
		return  dao.getOneRs(sql.toString(),new String[]{zydm},"zyzrs");
	}

    public List<HashMap<String,String>> getSqExportList(XszzSqshForm t, User user) throws Exception {

		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t1", "xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(t.getSearchModel());

		StringBuilder sql = new StringBuilder();

		sql.append(" select rownum rn,t.* from (");

		sql.append("select * from (select t1.*,t2.sydm,t3.symc,case when t2.sqkg=1 and sysdate between to_date(nvl(t2.sqkssj,19900101),'yyyymmdd') ");
		sql.append("and to_date(nvl(t2.sqjssj,20200101),'yyyymmdd')+1 then 'true' else 'false' end isopen, ");
		sql.append("nvl2(t3.rddc, '是', '否') sfkns ");
		sql.append(" from VIEW_NEW_DC_XSZZ_SQSH t1 ");
		sql.append(" left join XG_XTWH_SYBJGLB t2 on t2.bjdm=t1.bjdm ");
		sql.append(" left join XG_XTWH_SYDMB t3 on t3.sydm = t2.sydm ");
		sql.append(" left join xg_xszz_new_zzxmdmb t2 on t1.xmdm =t2.xmdm ");
		sql.append(" left join xg_xszz_new_knsjgb t3 on t1.xh = t3.xh and (t1.xn = t3.xn and decode(t3.xq,'on',nvl(t1.xq,1),t3.xq) = nvl(t1.xq,1)) ");

		sql.append(" ) t1 where 1=1 ");

		sql.append(searchTjByUser);
		sql.append(searchTj);
		sql.append(" order by symc,xmmc,xh");

		sql.append(") t");
		return getPageList(t, sql.toString(), inputV);
    }

	public List<HashMap<String,String>> getShExportList(XszzSqshForm t, User user) throws Exception {

		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t1", "xydm", "bjdm");
		String shgwzByUser = SearchService.getShgwzByUser(user, "t1","xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(t.getSearchModel());

		StringBuilder sql = new StringBuilder();

		sql.append(" select rownum rn,t.* from (");

		sql.append("select * from (select ");
		sql.append("               a.xn||' '||d.xqmc sqzq,d.xqmc,");

		// 审核查询效率问题 暂时关闭该字段 湘潭大学
		if(!"10530".equals(Base.xxdm)){
			sql.append(" pdshztb2.zd5 tzxmje,pdshztb2.zd3 tzxmmc, ");
		}

		sql.append("               '[' || c.mc || ':' || decode(b.shzt,'0','待审核','1','通过','2','不通过','3','退回','4','需重审','5','审核中',b.shzt) || ']' shztmc ,c.gwz ,c.mc gwmc" );
		sql.append("          ,a.ylzd1, a.xh, a.xn, a.xq, a.sqsj, a.sqly, a.guid, a.shlc, m.ssyq, m.nj, m.xb, m.xm, m.xydm, m.zydm, m.bjdm, m.zybj, m.zybjmc,t2.sydm,t3.symc, m.yhkh, m.xymc, m.zymc, m.bjmc,m.mz,m.mzmc,m.zzmmmc, m.sfzh,a.pdxn, d.xqmc pdxqmc, t1.lbmc, m.RXRQ, m.zd5, m.jtyb, m.jtdzxx, (select dcmc from xg_xszz_new_kndcdmb t6 where t5.rddc = t6.dcdm) kndj, m.sfsfs, x.pyccmc, b.shzt, b.shsj,b.gwid as xtgwid,b.guid as shid,decode(b.zd5,'',a.ylzd1,b.zd5) sqshje,f.xmmc,f.xmdm,z.rddc,row_number() over(partition by a.guid order by b.shsj desc) rn, nvl2(t5.rddc, '是', '否') sfkns ");
		sql.append("          ,(select CYXM from XG_XSZZ_NEW_JTCYB where CYGX='001' and xh =a.xh and rownum=1) jtcy1_xm ");
		sql.append("          ,(select CYXXDW from XG_XSZZ_NEW_JTCYB where CYGX='001' and xh =a.xh and rownum=1) jtcy1_gzdz ");
		sql.append("          ,(select CYLXDH from XG_XSZZ_NEW_JTCYB where CYGX='001' and xh =a.xh and rownum=1) jtcy1_lxdh1 ");
		sql.append("          ,(select CYXM from XG_XSZZ_NEW_JTCYB where CYGX='002' and xh =a.xh and rownum=1) jtcy2_xm ");
		sql.append("          ,(select CYXXDW from XG_XSZZ_NEW_JTCYB where CYGX='002' and xh =a.xh and rownum=1) jtcy2_gzdz ");
		sql.append("          ,(select CYLXDH from XG_XSZZ_NEW_JTCYB where CYGX='002' and xh =a.xh and rownum=1) jtcy2_lxdh1 ");
		sql.append("                  from xg_xszz_new_zzxmsqb a");
		sql.append("                  left join view_xsbfxx m");
		sql.append("                    on a.xh = m.xh");
		sql.append(" left join XG_XTWH_SYBJGLB t2 on t2.bjdm=m.bjdm ");
		sql.append(" left join XG_XTWH_SYDMB t3 on t3.sydm = t2.sydm ");
		sql.append("				  left join view_new_dc_xszz_knsrdcx z");
		sql.append("	                on m.xh = z.xh");
		sql.append("  				  left join XG_XSXX_PYCCDMB x");
		sql.append("           			on m.pycc = x.pyccdm");
		sql.append("                  left join xg_xtwh_shztb b");
		sql.append("                    on a.guid = b.ywid");
		sql.append("                  left join xg_xszz_new_zzxmdmb f");
		sql.append("                    on a.xmdm = f.xmdm ");
		sql.append(" 				  left join xg_xszz_new_zzxmlbb t1 on f.lbdm = t1.lbdm left join xg_xszz_new_knsjgb t5 on a.xh = t5.xh and (a.xn = t5.xn and decode(t5.xq,'on',nvl(a.xq,1),t5.xq) = nvl(a.xq,1)) ");
		if(!"10530".equals(Base.xxdm)){
			sql.append("                  left join (select * from (select pdshztb.*,row_number() over(partition by pdshztb.ywid order by pdshztb.shsj desc) pdrn from xg_xtwh_shztb pdshztb where pdshztb.shsj is not null) where pdrn=1) pdshztb2 on a.guid = pdshztb2.ywid ");
		}

		sql.append("          left join xg_xtwh_spgw c");
		sql.append("            on b.gwid = c.id");
		sql.append("          left join xqdzb d");
		sql.append("            on a.xq = d.xqdm");

		if (YSH.equalsIgnoreCase(t.getShzt())){
			sql.append(" where b.shzt not in ('0','4') ");
		}
		if (DSH.equalsIgnoreCase(t.getShzt())){

			sql.append(" where b.shzt in ('0','4') ");
		}
		if("fdy".equals(user.getUserStatus()) || "sy".equals(user.getUserStatus())){
			sql.append(" and (c.yhjs='"+user.getUserStatus()+"' or c.yhjs is null)");
		}
		sql.append("           and b.gwid in");
		sql.append("               (select spgw from xg_xtwh_spgwyh where spyh = ?)) t1 where 1=1 and rn=1");

		sql.append(searchTjByUser);
		sql.append(searchTj);
		sql.append(shgwzByUser);

		sql.append(" ) t");

		return dao.getListNotOut(sql.toString(),StringUtils.joinStrArr(new String[]{user.getUserName()},inputV));
	}
}
