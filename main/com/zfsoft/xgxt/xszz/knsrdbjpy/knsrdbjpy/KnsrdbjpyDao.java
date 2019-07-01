package com.zfsoft.xgxt.xszz.knsrdbjpy.knsrdbjpy;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.xpjpy.wdpj.sqsh.SqshDao;

/**
 * 
 * @系统名称: 学生工作管理系统
 * @模块名称: 学生资助管理模块
 * @类功能描述: 学生资助2013版--困难生认定 
 * @作者： Penghui.Qu 
 * @时间： 2013-4-22 上午09:03:12 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class KnsrdbjpyDao extends SuperDAOImpl<KnsrdbjpyForm> {

	private static final String DSH = "dsh";
	private static final String YSH = "ysh";
	
	public List<HashMap<String, String>> getPageList(KnsrdbjpyForm t)
			throws Exception {
		return null;
	}
	public List<HashMap<String, String>> getPageList(KnsrdbjpyForm t, User user)
			throws Exception {
		
		//生成高级查询相关条件、条件值 
		if("xn".equals(MessageUtil.getText("xszz.knsrd.sqzq"))){
			t.getSearchModel().setSearch_tj_xq(null);
		}
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t1", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		
		StringBuilder sql = new StringBuilder();
		/*sql.append("select t1.* from (");
		sql.append("select t1.guid,t1.xh,t1.shlc,t2.xm,t2.xb,t2.nj,t2.xymc,t2.zymc,");
		sql.append("t2.bjmc,t2.zzmmmc,t2.mzmc,t2.yhmc,t2.yhkh,t2.sfzh,t1.xn,");
		sql.append("t3.xqmc,t1.sqsj,t4.dcmc,t1.sqly,");
		sql.append("t2.xydm,t2.zydm,t2.bjdm,t4.dcdm,t1.xq,t1.shzt,");
		sql.append("decode(t1.shzt,'0','未提交','1','通过','2','不通过',");
		sql.append("'3','退回','3','已退回','5','审核中',t1.shzt) shztmc ");
		sql.append(" from xg_xszz_new_knsrd_knssqb t1 ");
		sql.append(" left join view_xsxxb t2 on t1.xh=t2.xh ");
		sql.append(" left join xqdzb t3 on t1.xq=t3.xqdm ");
		sql.append(" left join xg_xszz_new_kndcdmb t4 on t1.rddc=t4.dcdm ");
		sql.append(" ) t1 where 1=1");*/
		
		//改用视图方式
		//视图：VIEW_NEW_DC_XSZZ_KNSRD_KNSRDSQ
		sql.append("select * from VIEW_NEW_DC_XSZZ_KNSRD_KNSRDSQ t1 where  1=1 ");
		
		sql.append(searchTjByUser);
		sql.append(searchTj);
		
		return getPageList(t, sql.toString(), inputV);
	}

	protected void setTableInfo() {
		super.setKey("guid");
		super.setTableName("xg_xszz_new_knsrd_knssqb");
	}

	
	
	public KnsrdbjpyForm getModel(KnsrdbjpyForm t ,String[] params) throws Exception {
		
		String sql = "select * from xg_xszz_new_knsrd_knssqb where xh=? and xn=? and xq=? ";
		
		return super.getModel(t, sql, params);
	}
	public KnsrdbjpyForm getModelOfXn(KnsrdbjpyForm t ,String[] params) throws Exception {
		
		String sql = "select * from xg_xszz_new_knsrd_knssqb where xh=? and xn=?";
		
		return super.getModel(t, sql, params);
	}
	@Override
	public KnsrdbjpyForm getModel(KnsrdbjpyForm t) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" select t1.*,t4.xqmc,  ");
		// =============== 班级评议 < =============
		sql.append(" decode(t3.shzt,'1', '同意申请', '0', '不同意申请','') bjpyjgshztmc,t3.pyhsj bjpyjgpyhsj,t3.pyhdd bjpyjgpyhdd,t3.rdly bjpyjgrdly, ");
		sql.append(" (case when t3.ylzd1 is null then '' else t3.ylzd1 end) bjpyxzcyxms, ");
		sql.append(" (case when t3.ylzd1 is null then '' else t3.ylzd2 end) bjpyxzdbxms, ");
		// =============== 班级评议 > =============
		sql.append(" (select dcmc from xg_xszz_new_kndcdmb t where t3.pddc=to_char(t.dcdm))pddcmc, ");
		sql.append(" (select dcmc from xg_xszz_new_kndcdmb t where t1.ylzd1=to_char(t.dcdm))sqdcmc ");
		sql.append(" from xg_xszz_new_knsrd_knssqb t1  ");
		// =============== 班级评议 < =============
		sql.append(" left join ( ");
		sql.append(" select * from ( ");
		sql.append(" select a.xn,a.xq,a.sqr,a.tjzt,count(1) over (partition by a.xn,a.xq,a.sqr order by a.sqr asc) bjpyxzrs, ");
		sql.append(" WM_CONCAT(b.xm) over (partition by a.xn,a.xq,a.sqr order by a.sqr asc) bjpyxzcyxms, ");
		sql.append(" row_number() over (partition by a.xn,a.xq,a.sqr order by a.sqr desc) rn ");
		sql.append(" from xg_xszz_new_knsrd_bjpyxzpy a ");
		sql.append(" left join xsxxb b on a.bjpyr=b.xh ");
		sql.append(" ) a where rn='1' ");
		sql.append(" ) t2 on (t1.xn=t2.xn and t1.xq=t2.xq and t1.xh=t2.sqr) ");
		sql.append(" left join (select * from xg_xszz_new_knsrd_bjpyxzpyjg where tjzt='1') t3 on t1.guid=t3.sqid ");
		// =============== 班级评议 > =============
		sql.append(" left join xqdzb t4 on t1.xq=t4.xqdm where t1.guid=? ");
		HashMap<String, String> map=dao.getMapNotOut(sql.toString(), new String[]{t.getGuid()});
		KnsrdbjpyForm model=new KnsrdbjpyForm();
		BeanUtils.copyProperties(model, map);
		return model;
	}

	
	
	/**
	 * 
	 * @描述:插入审核状态
	 * @作者：Penghui.Qu
	 * @日期：2013-4-22 上午09:59:26
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param splc
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean insertShzt(KnsrdbjpyForm t ,String splc) throws Exception{
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("insert into xg_xszz_new_knsshb(xh,xn,xq,xtgwid,shzt,guid) ");
		sql.append("select ?,?,?,spgw,?,? from xg_xtwh_spbz where splc=? ");
		
		return dao.runUpdate(sql.toString(), new String[]{t.getXh(),t.getXn(),t.getXq(),t.getShzt(),t.getGuid(),splc});
	}
	
	
	
	
	/**
	 * 
	 * @描述:删除困难生申请（只能删除未审核的记录）
	 * @作者：Penghui.Qu
	 * @日期：2013-4-24 下午06:41:37
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param values
	 * @return
	 * @throws Exception
	 * int 返回类型 
	 * @throws
	 */
	public int delKnssq(String[] values) throws Exception {
		
		if (values == null || values.length == 0){
			logger.error("删除操作不能进行!");
			throw new NullPointerException();
		}
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("delete from xg_xszz_new_knsrd_knssqb");
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
	 * @描述:删除审核状态（对应的申请记录只能是未审核的记录）
	 * @作者：Penghui.Qu
	 * @日期：2013-4-22 下午01:12:50
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param values
	 * @return
	 * @throws Exception
	 * int 返回类型 
	 * @throws
	 */
	public int delShzt(String[] values) throws Exception {
		
		if (values == null || values.length == 0){
			logger.error("删除操作不能进行!");
			throw new NullPointerException();
		}
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("delete from xg_xszz_new_knsshb t1");
		sql.append(" where exists (select 1 from xg_xszz_new_knsrd_knssqb t2 ");
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
	 * @描述:获取申请记录所对应的审核流程信息（流程跟踪）
	 * @作者：Penghui.Qu
	 * @日期：2013-4-22 下午01:50:37
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>>  getSplcInfo(KnsrdbjpyForm t){
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select t3.xh spxh,t1.xtgwid,t4.mc gwmc,t1.rddc,t6.dcmc,t5.yhm,t5.xm shr,");
		sql.append("decode(t1.shzt,'0','未审核','1','通过','2',");
		sql.append("'不通过','3','退回','4','需重审',t1.shzt) shzt,");
		sql.append("t1.shsj,t1.shyj,t1.xn from xg_xszz_new_knsshb t1 ");
		sql.append("left join xg_xszz_new_knsrd_knssqb t2 on t1.guid = t2.guid ");
		sql.append("left join xg_xtwh_spbz t3 on t2.shlc=t3.splc and t1.xtgwid=t3.spgw ");
		sql.append("left join xg_xtwh_spgw t4 on t3.spgw=t4.id ");
		sql.append("left join yhb t5 on t1.shr=t5.yhm  ");
		sql.append("left join xg_xszz_new_kndcdmb t6 on t1.rddc=t6.dcdm ");
		sql.append("where t1.guid=? order by spxh");
		
		return dao.getListNotOut(sql.toString(), new String[]{t.getGuid()});
	}
	
	
	
	/**
	 * 
	 * @描述:困难生认定--审核查询
	 * @作者：Penghui.Qu
	 * @日期：2013-4-22 下午04:18:10
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getShjlList(KnsrdbjpyForm t, User user)
			throws Exception {

		// 生成高级查询相关条件、条件值
		if("xn".equals(MessageUtil.getText("xszz.knsrd.sqzq"))){
			t.getSearchModel().setSearch_tj_xq(null);
		}
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t1", "xydm", "bjdm");
		String shgwzByUser = SearchService.getShgwzByUser(user, "t1","xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(t.getSearchModel());

		StringBuilder sql = new StringBuilder();

		sql.append(" select t1.*,case when t1.sjdcmctemp is null then (select dcmc from xg_xszz_new_kndcdmb kndcdmbtemp where t1.PDDC=to_char(kndcdmbtemp.dcdm)) else t1.sjdcmctemp end sjdcmc,case when t1.sjdctemp is null then t1.PDDC else t1.sjdctemp end sjdc from (  select d.xqmc,'[' || c.mc || ':' ||decode(b.shzt,'0','待审核','1','通过','2','不通过','3','退回','4','需重审','5','审核中',b.shzt) || ']' shztmc,c.gwz");
		sql.append(" ,(select dcmc from xg_xszz_new_kndcdmb kndcdmbtemp where a.ylzd1 = to_char(kndcdmbtemp.dcdm) )sqdcmc,a.PDDC,a.xh,a.ylzd2,a.xn,a.xq,a.sqsj,a.guid,b.zd6 dczzje,a.shlc,a.sqly,m.nj,x.mz,x.zd5,decode(x.xb,'1','男','2','女',x.xb) as xb, x.xm,m.xydm,m.zydm,m.bjdm,b.zd3 as dcmc,m.xymc,m.zymc,m.bjmc,b.shzt,b.shsj,b.gwid as xtgwid,b.zd2 as rddc,b.guid as shid, ");
		sql.append("lead(b.zd6, 1, null) over(partition by b.ywid order by b.ywid,b.shsj desc) as wczzje,");
		sql.append(" lead(b.zd3, 1, null) over(partition by b.ywid order by b.ywid,b.shsj desc) as sjdcmctemp,lead(b.zd2, 1, null) over(partition by b.ywid order by b.ywid,b.shsj desc) as sjdctemp, ");
		sql.append(" row_number() over(partition by a.guid order by b.shsj desc) rn ");
		if("10657".equals(Base.xxdm)){
			sql.append(",e.jtrjysr");
		}
		sql.append(" from xg_xszz_new_knsrd_knssqb a left join xsxxb x on a.xh=x.xh left join view_njxyzybj_all m on x.bjdm = m.bjdm ");
		sql.append(" left join xg_xtwh_shztb b on a.guid = b.ywid ");
		
		
		sql.append(" left join xg_xtwh_spgw c on b.gwid = c.id ");
		sql.append(" left join xqdzb d on a.xq = d.xqdm  ");
		sql.append(" left join xg_xszz_new_jtqkdcb e on a.xh=e.xh");
		
		if (YSH.equalsIgnoreCase(t.getShzt())){

			sql.append(" where b.gwid in (select spgw from xg_xtwh_spgwyh where spyh='"+user.getUserName()+"' )");
			sql.append("  and b.shzt not in ('0','4')");
			sql.append(" ) t1 where 1=1 and rn=1");
			
			
		}
		if (DSH.equalsIgnoreCase(t.getShzt())){
			sql.append(" ) t1 where 1=1 and rn=1");
			sql.append(" and xtgwid in (select spgw from xg_xtwh_spgwyh where spyh='"+user.getUserName()+"' )");
			sql.append(" and shzt in ('0','4') ");
		}
		
		
		sql.append(searchTjByUser);
		sql.append(searchTj);
		sql.append(shgwzByUser);
		
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

		return getPageList(t, sql.toString(), StringUtils.joinStrArr(inputV,new String[]{}));
	}
	
	
	
	/**
	 * 
	 * @描述:执行审核操作（限制条件：前一级 为“通过”，本级为“未审核” or “需重审”）
	 * @作者：Penghui.Qu
	 * @日期：2013-4-23 下午03:26:16
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return
	 * @throws Exception
	 * int 返回类型 
	 * @throws
	 */
	public int runAuditing(KnsrdbjpyForm t,User user) throws Exception{
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("update xg_xszz_new_knsshb t1 set shzt=?,shyj=?,shr=?,rddc=?,");
		sql.append("shsj=to_char(sysdate,'yyyy-mm-dd hh24:mi:ss') where guid=? ");
		sql.append("and xtgwid=? and (shzt='0' or shzt='4') and exists (");
		sql.append("select 1 from ( ");
		sql.append("select t1.*,t2.shzt bjzt,nvl(t3.shzt,'1') sjzt from (");
		sql.append("select t1.xh,t1.spgw,t1.splc,");
		sql.append("lag(t1.xh,1,0) over (order by t1.xh) as sjxh,");
		sql.append("lag(t1.spgw,1) over (order by t1.xh) as sjgw ");
		sql.append("from xg_xtwh_spbz t1 where splc=(");
		sql.append("select shlc from xg_xszz_new_knsrd_knssqb where guid=? ");
		sql.append(")) t1 left join xg_xszz_new_knsshb t2 on t1.spgw=t2.xtgwid and t2.guid=? ");
		sql.append("left join xg_xszz_new_knsshb t3 on t1.sjgw=t3.xtgwid and t3.guid=? ");
		sql.append(") t2 where t1.xtgwid=t2.spgw and t2.sjzt='1' and (bjzt='0' or bjzt='4') ");
		sql.append(")");
		
		
		String guid = t.getGuid();
		
		return dao.update(sql.toString(), new String[]{t.getShzt(),t.getShyj(),user.getUserName(),
													   t.getRddc(),guid,t.getXtgwid(),guid,guid,guid});
	}
	
	
	
	/**
	 * 
	 * @描述:撤消审核，限制条件（
	 * 			① 撤消本级的通过 ， 下一级“未审核” or “需重审”;
	 * 			② 撤消本级的不通过;
	 * 			③ 撤消本级的退回,前一级"需重审"
	 * ）。① 、 ② 、 ③ 或者关系
	 * @作者：Penghui.Qu
	 * @日期：2013-4-23 下午03:41:38
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return
	 * @throws Exception
	 * int 返回类型 
	 * @throws
	 */
	public int cancelAuditing(KnsrdbjpyForm t) throws Exception{
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("update xg_xszz_new_knsshb t1 set shzt='0',shsj='',shr='',shyj='',rddc='' ");
		sql.append("where guid=? and xtgwid=? and exists (select 1 from (");
		sql.append("select t1.xh spxh,t1.spgw,t4.shzt shzt,t1.xjxh,");
		sql.append("t1.xjgw,t2.shzt xjzt,t1.sjxh,t1.sjgw,t3.shzt sjzt from (");
		sql.append("select t1.xh,t1.spgw,");
		sql.append("lag(t1.xh,1,0) over (order by t1.xh) as sjxh,");
		sql.append("lag(t1.spgw,1) over (order by t1.xh) as sjgw,");
		sql.append("lead(t1.xh,1) over (order by t1.xh) as xjxh,");
		sql.append("lead(t1.spgw,1) over (order by t1.xh) as xjgw ");
		sql.append("from xg_xtwh_spbz t1 where splc=( ");
		sql.append("select shlc from xg_xszz_new_knsrd_knssqb where guid=? ");
		sql.append(")) t1 left join xg_xszz_new_knsshb t2 on t1.xjgw=t2.xtgwid and t2.guid=? ");
		sql.append("left join xg_xszz_new_knsshb t3 on t1.sjgw=t3.xtgwid and t3.guid=? ");
		sql.append("left join xg_xszz_new_knsshb t4 on t1.spgw=t4.xtgwid and t4.guid=? ");
		sql.append(") t2 where t1.xtgwid=t2.spgw and (t2.shzt='2' or (t2.shzt='3' and (sjzt='4' or sjzt is null)) ");
		sql.append("or (t2.shzt='1' and (xjzt='0' or xjzt='4' or xjzt is null)))) ");
		
		String guid = t.getGuid();
		
		return dao.update(sql.toString(), new String[]{guid,t.getXtgwid(),guid,guid,guid,guid});
	}
	
	
	
	/**
	 * 
	 * @描述:获取下一级审批信息
	 * @作者：Penghui.Qu
	 * @日期：2013-4-23 下午04:24:02
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public HashMap<String,String> getNextSpxx(KnsrdbjpyForm t){
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select * from ( ");
		sql.append("select t1.xh,t1.spgw,");
		sql.append("lead(t1.xh,1) over (order by t1.xh) as xjxh,");
		sql.append("lead(t1.spgw,1) over (order by t1.xh) as xjgw ");
		sql.append("from xg_xtwh_spbz t1 ");
		sql.append("where splc=(");
		sql.append("select shlc from xg_xszz_new_knsrd_knssqb where guid=? ");
		sql.append(") ) where spgw=? ");
		
		return dao.getMapNotOut(sql.toString(), new String[]{t.getGuid(),t.getXtgwid()});
	}
	
	
	
	/**
	 * 
	 * @描述:获取前一级审批信息
	 * @作者：Penghui.Qu
	 * @日期：2013-4-23 下午04:24:02
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public HashMap<String,String> getBeforeSpxx(KnsrdbjpyForm t){
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select * from ( ");
		sql.append("select t1.xh,t1.spgw,");
		sql.append("lag(t1.xh,1) over (order by t1.xh) as sjxh,");
		sql.append("lag(t1.spgw,1) over (order by t1.xh) as sjgw ");
		sql.append("from xg_xtwh_spbz t1 ");
		sql.append("where splc=(");
		sql.append("select shlc from xg_xszz_new_knsrd_knssqb where guid=? ");
		sql.append(") ) where spgw=? ");
		
		return dao.getMapNotOut(sql.toString(), new String[]{t.getGuid(),t.getXtgwid()});
	}
	
	
	
	/**
	 * 
	 * @描述:根据申请ID和审核岗位ID获取当前岗位的审核状态
	 * @作者：Penghui.Qu
	 * @日期：2013-4-23 下午05:00:55
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getCurrunShzt(KnsrdbjpyForm t){
		
		String sql = "select shzt from xg_xszz_new_knsshb where guid=? and xtgwid=?";
		
		return dao.getOneRs(sql, new String[]{t.getGuid(),t.getXtgwid()}, "shzt");
	}
	
	
	
	/**
	 * 
	 * @描述:按申请记录ID、审批岗位ID更新审核状态
	 * @作者：Penghui.Qu
	 * @日期：2013-4-24 上午08:43:56
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param guid
	 * @param gwid
	 * @param shzt
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean setShzt(String guid,String gwid,String shzt) throws Exception{
		
		String sql = "update xg_xszz_new_knsshb set shzt=? where guid=? and xtgwid=? ";
		
		return dao.runUpdate(sql, new String[]{shzt,guid,gwid});
	}
	
	
	
	/**
	 * 
	 * @描述:待审核的记录总数
	 * @作者：Penghui.Qu
	 * @日期：2013-4-24 下午04:02:35
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * @throws SQLException
	 * int 返回类型 
	 * @throws
	 */
	public int getDshCount() throws SQLException{
		
		String sql = "select count(1) num from xg_xszz_knsrdbjpy_zbsqb where shzt='5' ";
		
		return dao.getOneRsint(sql);
	}
	
	
	
	/**
	 * 
	 * @描述:从困难生结果级联删除困难生申请信息
	 * @作者：Penghui.Qu
	 * @日期：2013-5-6 下午01:42:18
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param values
	 * @return
	 * int 返回类型 
	 * @throws
	 */
	public int delKnssqFromKnsjgbjpy(String[] values) throws Exception{
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("delete from xg_xszz_new_knsrd_knssqb t1 where  ");
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
	 * @描述:从困难生结果级联删除困难生申请对应的审核记录
	 * @作者：Penghui.Qu
	 * @日期：2013-5-6 下午01:47:46
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param values
	 * @return
	 * @throws Exception
	 * int 返回类型 
	 * @throws
	 */
	public int delKnsshFromKnsjgbjpy(String[] values) throws Exception{
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("delete from xg_xszz_new_knsshb t1 where ");
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
	 * @描述:查询在申请表中不存在的ID
	 * @作者：Penghui.Qu
	 * @日期：2013-5-8 下午07:33:53
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ids
	 * @return
	 * @throws SQLException
	 * String[] 返回类型 
	 * @throws
	 */
	public String[] getExistsId(String[] ids) throws SQLException{
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select guid from xg_xszz_new_knsrd_knssqb where (");
		
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
	 * @描述: 审核情况统计
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-6-3 下午01:59:26
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param user
	 * @param xn
	 * @param xq
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getShqkTjxx(User user,String xn,String xq){
		
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t2", "xydm", "bjdm");		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select t1.xtgwid,t3.mc gwmc,t1.btg,t1.tg,t2.spxh,");
		sql.append("t1.dsh - (lag(t1.dsh,1,0) over (order by t2.spxh) + lag(t1.btg,1,0) over (order by t2.spxh)) dsh ");
		sql.append("from (select sum(t1.dsh) dsh, sum(t1.tg) tg,");
		sql.append("sum(t1.btg) btg, xtgwid  from (select t1.xh,t1.xn,t1.xq,");
		sql.append("case when t1.shzt = '0' or t1.shzt = '4' then  1  else 0 end dsh,");
		sql.append("case when t1.shzt = '1' then  1  else  0 end tg,");
		sql.append("case when t1.shzt = '2' then  1  else  0 end btg,");
		sql.append("t1.xtgwid from xg_xszz_new_knsshb t1 where t1.xn = ? and t1.xq = ? ");
		sql.append("and exists (select 1 from xsxxb t2 where t1.xh=t2.xh ");
		sql.append(searchTjByUser);
		sql.append(")) t1 group by t1.xtgwid ) t1 left join (select spgw,xh as spxh from xg_xtwh_spbz ");
		sql.append("where splc=(select splc from xg_xszz_new_knsrd_knsjbszb where rownum=1) ) t2 on t1.xtgwid=t2.spgw ");
		sql.append("left join xg_xtwh_spgw t3 on t1.xtgwid=t3.id  order by t2.spxh");
		
		return dao.getListNotOut(sql.toString(), new String[]{xn,xq});
	}





	/**
	 * 
	 * @描述: 困难生申请总人数
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-6-3 下午02:29:37
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param user
	 * @param xn
	 * @param xq
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getSqrs(User user,String xn,String xq){
		
		StringBuilder sql = new StringBuilder();
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t2", "xydm", "bjdm");		
		
		sql.append("select count(1) zrs from xg_xszz_new_knsrd_knssqb t1 where t1.xn=? and t1.xq=? ");
		sql.append("and exists (select 1 from xsxxb t2 where t1.xh=t2.xh ");
		sql.append(searchTjByUser);
		sql.append(")");
		
		return dao.getOneRs(sql.toString(), new String[]{xn,xq}, "zrs");
	}


	/**
	 * 
	 * @描述: 从审核统计查询具体学生
	 * @作者：Penghui.Qu [工号：445]
	 * @日期：2013-6-3 下午04:23:03
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getStudentsFromShtj(KnsrdbjpyForm t, User user) throws Exception {

		//生成高级查询相关条件、条件值 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t1", "xydm", "bjdm");		
		String[] input = SearchService.getTjInput(t.getSearchModel());
		
		StringBuilder sql = new StringBuilder();
		sql.append("select t1.* from (");
		sql.append("select t1.xh,t2.xm,t2.nj,t2.xymc,t2.xydm,t2.xb,");
		sql.append("t2.zymc,t2.zydm,t2.bjmc,t2.bjdm,");
		sql.append("nvl(t3.shsj,' ') shsj,nvl(t3.shyj,' ') shyj,nvl(t3.shr,' ') shr from xg_xszz_new_knsrd_knssqb t1 ");
		sql.append("left join view_xsjbxx t2 on t1.xh=t2.xh ");
		sql.append("left join (select * from xg_xszz_new_knsshb where xtgwid=?) t3 on t1.guid = t3.guid ");
		sql.append("where t1.xn=? and t1.xq=? and t1.guid in (select guid from (");
		
		sql.append("select t1.shzt,t1.xtgwid,t1.guid,t2.xh as spxh,");
		sql.append("case when t2.xh=1 then '1' else lag(t1.shzt, 1,1) over(order by t1.guid, t2.xh) end sjzt ");
		sql.append("from xg_xszz_new_knsshb t1 ");
		sql.append("left join xg_xtwh_spbz t2 on t1.xtgwid=t2.spgw ");
		sql.append("where t2.splc=(select splc from xg_xszz_new_knsrd_knsjbszb where rownum=1) ");
		
		sql.append(") t2 where (t2.shzt=?  ");
		if (Constants.YW_WTJ.equals(t.getShzt())){
			sql.append("or t2.shzt='4'");
		}
		sql.append(")");
		sql.append("and t2.xtgwid=? and t2.sjzt='1' ) ) t1 where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		
		return getPageList(t, sql.toString(), StringUtils.joinStrArr(new String[]{t.getXtgwid(),t.getXn(),t.getXq(),t.getShzt(),t.getXtgwid()},input));
	}
	
	
	/** 
	 * @描述:根据人数分配方式，统计对应单位的人数
	 * @作者：陈敏杰
	 * @日期：2013-12-10 下午02:20:36
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param rskzfw
	 * @param xn
	 * @param xq
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getTgrs(String rskzfw, String xn,
			String xq) {
		StringBuilder sql = new StringBuilder();
		if (Constants.RSKZFW_NJXY.equals(rskzfw)){//年级学院分配方式
			sql.append("select count(1) tgrs,nj,xydm from (");
			sql.append("select t1.xh,t1.shzt,t2.nj,t2.xydm,t2.zydm,t2.bjdm from xg_xszz_new_knsrd_knssqb t1 ");
			sql.append("left join xsxxb t2 on t1.xh=t2.xh ");
			sql.append(" where t1.xn=? and t1.xq=? and shzt='1' ");
			sql.append(") group by nj,xydm ");
		} else if (Constants.RSKZFW_NJZY.equals(rskzfw)){//年级专业分配方式
			sql.append("select count(1) tgrs,nj,zydm from (");
			sql.append("select t1.xh,t1.shzt,t2.nj,t2.xydm,t2.zydm,t2.bjdm from xg_xszz_new_knsrd_knssqb t1 ");
			sql.append("left join xsxxb t2 on t1.xh=t2.xh ");
			sql.append(" where t1.xn=? and t1.xq=? and shzt='1' ");
			sql.append(") group by nj,zydm ");
		} else if (Constants.RSKZFW_XY.equals(rskzfw)){//学院分配方式
			sql.append("select count(1) tgrs,xydm from (");
			sql.append("select t1.xh,t1.shzt,t2.nj,t2.xydm,t2.zydm,t2.bjdm from xg_xszz_new_knsrd_knssqb t1 ");
			sql.append("left join xsxxb t2 on t1.xh=t2.xh ");
			sql.append(" where t1.xn=? and t1.xq=? and shzt='1' ");
			sql.append(") group by xydm ");
		} else if (Constants.RSKZFW_ZY.equals(rskzfw)){//专业分配方式
			sql.append("select count(1) tgrs,zydm from (");
			sql.append("select t1.xh,t1.shzt,t2.nj,t2.xydm,t2.zydm,t2.bjdm from xg_xszz_new_knsrd_knssqb t1 ");
			sql.append("left join xsxxb t2 on t1.xh=t2.xh ");
			sql.append(" where t1.xn=? and t1.xq=? and shzt='1' ");
			sql.append(") group by zydm ");
		} else {//班级分配方式
			sql.append("select count(1) tgrs,bjdm from (");
			sql.append("select t1.xh,t1.shzt,t2.nj,t2.xydm,t2.zydm,t2.bjdm from xg_xszz_new_knsrd_knssqb t1 ");
			sql.append("left join xsxxb t2 on t1.xh=t2.xh ");
			sql.append(" where t1.xn=? and t1.xq=? and shzt='1' ");
			sql.append(") group by bjdm ");
		}
		return dao.getListNotOut(sql.toString(), new String[]{xn,xq});
	}
	/** 
	 * @描述:按年级、学院统计已通过人数
	 * @作者：cmj 
	 * @日期：2013-12-10 下午05:13:36
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param rsszMap
	 * @return
	 * String 返回类型 
	 * @throws 
	 */
	public String getTgrsByNjxy(KnsrdbjpyForm t, HashMap<String, String> rsszMap) {
		StringBuilder sql = new StringBuilder();
		
		sql.append("select count(1) tgrs from (select t1.guid, ");
		sql.append("  t2.shzt,t2.gwid,t1.xn,t1.xq,t3.xydm,t3.nj, ");
		sql.append("  row_number() over(partition by t1.guid, t2.gwid order by t2.shsj desc) lvl");
		sql.append("  from xg_xszz_new_knsrd_knssqb t1 left join xg_xtwh_shztb t2");
		sql.append("  on t1.guid = t2.ywid left join view_xsjbxx t3 on t1.xh=t3.xh )");
		sql.append(" where lvl = 1 and shzt = '1' and xn = ? and xq = ?");
		sql.append("   and gwid = ? and nj||xydm = (select nj||xydm from view_xsjbxx where xh = ?)");
		
		return dao.getOneRs(sql.toString(), new String[]{
			rsszMap.get("xn"),rsszMap.get("xq"),t.getXtgwid(),t.getXh()}, "tgrs");
	}
	/** 
	 * @描述:按年级、专业统计已通过人数
	 * @作者：陈敏杰
	 * @日期：2013-12-10 下午05:17:35
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param rsszMap
	 * @return
	 * String 返回类型 
	 * @throws 
	 */
	public String getTgrsByNjzy(KnsrdbjpyForm t, HashMap<String, String> rsszMap) {
		StringBuilder sql = new StringBuilder();
		
		sql.append("select count(1) tgrs from (select t1.guid, ");
		sql.append("  t2.shzt,t2.gwid,t1.xn,t1.xq,t3.zydm,t3.nj, ");
		sql.append("  row_number() over(partition by t1.guid, t2.gwid order by t2.shsj desc) lvl");
		sql.append("  from xg_xszz_new_knsrd_knssqb t1 left join xg_xtwh_shztb t2");
		sql.append("  on t1.guid = t2.ywid left join view_xsjbxx t3 on t1.xh=t3.xh )");
		sql.append(" where lvl = 1 and shzt = '1' and xn = ? and xq = ?");
		sql.append("   and gwid = ? and nj||zydm = (select nj||zydm from view_xsjbxx where xh = ?)");
		
		return dao.getOneRs(sql.toString(), new String[]{
			rsszMap.get("xn"),rsszMap.get("xq"),t.getXtgwid(),t.getXh()}, "tgrs");
	}
	/** 
	 * @描述:按学院统计已通过人数
	 * @作者：陈敏杰
	 * @日期：2013-12-10 下午05:18:26
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param rsszMap
	 * @return
	 * String 返回类型 
	 * @throws 
	 */
	public String getTgrsByXy(KnsrdbjpyForm t, HashMap<String, String> rsszMap) {
		StringBuilder sql = new StringBuilder();
		
		sql.append("select count(1) tgrs from (select t1.guid, ");
		sql.append("  t2.shzt,t2.gwid,t1.xn,t1.xq,t3.xydm, ");
		sql.append("  row_number() over(partition by t1.guid, t2.gwid order by t2.shsj desc) lvl");
		sql.append("  from xg_xszz_new_knsrd_knssqb t1 left join xg_xtwh_shztb t2");
		sql.append("  on t1.guid = t2.ywid left join view_xsjbxx t3 on t1.xh=t3.xh )");
		sql.append(" where lvl = 1 and shzt = '1' and xn = ? and xq = ?");
		sql.append("   and gwid = ? and xydm = (select xydm from view_xsjbxx where xh = ?)");
		
		return dao.getOneRs(sql.toString(), new String[]{
			rsszMap.get("xn"),rsszMap.get("xq"),t.getXtgwid(),t.getXh()}, "tgrs");
	}
	/** 
	 * @描述:按专业统计已通过人数
	 * @作者：陈敏杰
	 * @日期：2013-12-10 下午05:19:44
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param rsszMap
	 * @return
	 * String 返回类型 
	 * @throws 
	 */
	public String getTgrsByZy(KnsrdbjpyForm t, HashMap<String, String> rsszMap) {
		StringBuilder sql = new StringBuilder();
		
		sql.append("select count(1) tgrs from (select t1.guid, ");
		sql.append("  t2.shzt,t2.gwid,t1.xn,t1.xq,t3.zydm, ");
		sql.append("  row_number() over(partition by t1.guid, t2.gwid order by t2.shsj desc) lvl");
		sql.append("  from xg_xszz_new_knsrd_knssqb t1 left join xg_xtwh_shztb t2");
		sql.append("  on t1.guid = t2.ywid left join view_xsjbxx t3 on t1.xh=t3.xh )");
		sql.append(" where lvl = 1 and shzt = '1' and xn = ? and xq = ?");
		sql.append("   and gwid = ? and zydm = (select zydm from view_xsjbxx where xh = ?)");
		
		return dao.getOneRs(sql.toString(), new String[]{
			rsszMap.get("xn"),rsszMap.get("xq"),t.getXtgwid(),t.getXh()}, "tgrs");
	}
	/** 
	 * @描述:按班级统计已通过人数
	 * @作者：陈敏杰
	 * @日期：2013-12-10 下午05:20:54
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param rsszMap
	 * @return
	 * String 返回类型 
	 * @throws 
	 */
	public String getTgrsByBj(KnsrdbjpyForm t, HashMap<String, String> rsszMap) {
		StringBuilder sql = new StringBuilder();
		
		sql.append("select count(1) tgrs from (select t1.guid, ");
		sql.append("  t2.shzt,t2.gwid,t1.xn,t1.xq,t3.bjdm,t3.nj, ");
		sql.append("  row_number() over(partition by t1.guid, t2.gwid order by t2.shsj desc) lvl");
		sql.append("  from xg_xszz_new_knsrd_knssqb t1 left join xg_xtwh_shztb t2");
		sql.append("  on t1.guid = t2.ywid left join view_xsjbxx t3 on t1.xh=t3.xh )");
		sql.append(" where lvl = 1 and shzt = '1' and xn = ? and xq = ?");
		sql.append("   and gwid = ? and bjdm = (select bjdm from view_xsjbxx where xh = ?)");
		
		return dao.getOneRs(sql.toString(), new String[]{
			rsszMap.get("xn"),rsszMap.get("xq"),t.getXtgwid(),t.getXh()}, "tgrs");
	}
	
	
	/** 
	 * @描述:根据UGID查询困难生申请信息
	 * @作者：张昌路[工号：982]
	 * @日期：2014-1-27 下午04:02:18
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param guid
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws 
	 */
	public HashMap<String, String> getKnsqInfo(String guid) {
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select * from VIEW_NEW_DC_XSZZ_KNSRD_KNSRDSQ where guid = ?");
		
		return dao.getMapNotOut(sql.toString(), new String[]{guid});
	}
	
	/** 
	 * @描述:根据UGID查询困难生申请结果信息
	 * @作者：HongLin[工号：707]
	 * @日期：2014-2-18 下午04:16:37
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param guid
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws 
	 */
	public HashMap<String, String> getKnsqjgInfo(String guid) {
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select * from VIEW_NEW_DC_XSZZ_KNSRDCX where guid = ?");
		
		return dao.getMapNotOut(sql.toString(), new String[]{guid});
	}
	
	/**
	 * 
	 * @描述:获取审核信息
	 * @作者：夏夏[工号：1104]
	 * @日期：2015-6-16 下午03:00:47
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param guid
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getSpxxInfo(String guid) {
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.*, b.shyj ejshyj,b.shzt ejshzt,b.zd2 ejtjdcdm ,b.zd3 ertjdcmc, c.shyj sjshyj,c.shzt sjshzt,c.zd2 sjtjdcdm,c.zd3 sjtjdcmc from(select a.* from(select a.ywid,a.shyj yjshyj,a.shzt yjshzt,a.zd2 yjtjdc,row_number() over(partition by a.ywid order by a.shsj desc) rn from xg_xtwh_shztb a   where a.ywid=?)a where a.rn=3)a left join");
		sql.append("  (select b.* ,row_number() over(partition by b.ywid order by b.shsj desc) rn from xg_xtwh_shztb b  where b.ywid=?)b on a.ywid=b.ywid and b.rn=2");
		sql.append(" left join (select  c.*,row_number() over(partition by c.ywid order by c.shsj desc) rn from xg_xtwh_shztb c   where c.ywid=?)c on a.ywid=c.ywid and c.rn=1 ");
		return dao.getMapNotOut(sql.toString(), new String[]{guid,guid,guid});
	}
	
	/** 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：cq [工号：785]
	 * @日期：2014-3-18 下午03:27:01
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws 
	 */
	public HashMap<String,String> getNewKnsdcbjpy(KnsrdbjpyForm model){
		
		String sql = "select * from (select * from xg_xtwh_shztb where ywid = ? and zd2 is not null order by shsj desc) a where rownum = 1 ";
		
		return dao.getMapNotOut(sql, new String[]{model.getGuid()});
	}
	

}
