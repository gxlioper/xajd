/**
 * @部门:学工产品事业部
 * @日期：2013-12-9 上午11:23:29 
 */  
package com.zfsoft.xgxt.xpjpy.wdpj.pjxmsq;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.action.Base;
import xgxt.comm.search.SearchModel;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.xpjpy.cssz.CsszModel;
import com.zfsoft.xgxt.xpjpy.cssz.CsszService;
import com.zfsoft.xgxt.xpjpy.wdpj.sqsh.SqshModel;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张小彬[工号:1036]
 * @时间： 2013-12-9 上午11:23:29 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class PjxmsqDao extends SuperDAOImpl<PjxmsqModel> {

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(PjxmsqModel t)
			throws Exception {

		SearchModel searchModel = t.getSearchModel();
		String searchTj = SearchService.getSearchTj(searchModel);
		String[] inputValue = SearchService.getTjInput(searchModel);

		StringBuilder sql = new StringBuilder();
		
		List<HashMap<String , String>> retData = null;
		
		String currXn = CsszService.getPjzq().get("xn");// 当前学年
		String currXq = CsszService.getPjzq().get("xq");// 当年学期

		//区分奖学金和表彰奖励
		if(!"2".equals(t.getXzdm())){
			t.setXzdm("1");
		}
		
		if(StringUtils.isEqual("wsq", t.getQueryType())){
			
			sql.append("select t.* ,''CONDITIONCHECKRESULT, t.sqkssj || nvl2(t.sqkssj || t.sqjssj , '~' ,'') || t.sqjssj as sqsjd , a.xmlxmc as xmlx , ").
			append("t3.shzt,t3.sqid,t3.splc,decode(t3.shzt,'0','未提交','1','通过','2','不通过','3','已退回','5','审核中','未申请') shztmc ").
			append("   from xg_pjpy_new_pjxmb t  left join  ");
			
			if("2".equals(t.getXzdm()))
			{//表彰奖励
				sql.append("xg_pjpy_new_xmlx a ");
			}
			else{//奖学金
				sql.append("xg_pjpy_new_jxjxmlx a ");
			}
			
			sql.append("  on t.lxdm = a.xmlxdm").
			append(" left join (select * from xg_pjpy_new_xmsq b where b.xh = '"+t.getXh()+"') t3 on t.xmdm = t3.xmdm ").
			append("  where not exists").
			append("  (select 1").
			append("           from xg_pjpy_new_xmsq t2").
			append("          where t.xmdm = t2.xmdm").
			append("            and t2.xh = '"+t.getXh()+"'  and t2.shzt not in ('0' , '3'))").
			append("    and 1=1").
			append(" and t.xn = '"+currXn+"'").
			append(" and t.xq = '"+currXq+"'");
			//湖南城市大学个性化
			if(Base.xxdm.equals("11527")){
				sql.append(" and t.sqkg = '1'");
				sql.append(" and sysdate between to_date(nvl(substr(t.sqkssj,1,10), '1990-01-01'),'yyyy-mm-dd')");
				sql.append(" and to_date(nvl(substr(t.sqjssj,1,10), '2020-01-01'),'yyyy-mm-dd')+1");
			}
			sql.append(" and t.xzdm = '" + t.getXzdm() + "' ");

			sql.append(searchTj);

			sql.append(" order by sqkg desc,to_number(t.xsxh) ");

			retData = getPageList(t, sql.toString(), inputValue);
			
		}else if(StringUtils.isEqual("ysq", t.getQueryType())){
			sql.append("select t.*,xn||xqmc pjzq ");
			
			if("2".equals(t.getXzdm()))
			{//表彰奖励
				sql.append("from VIEW_NEW_DC_PJPY_JXSQ t  ");
			}
			else{//奖学金
				sql.append("from VIEW_NEW_DC_PJPY_JXJSQ t  ");
			}
			
			sql.append("where t.xh='"+t.getXh()+"' ");

			//区分奖学金和表彰奖励
//			if(!"2".equals(t.getXzdm())){
//				t.setXzdm("1");
//			}
			sql.append(" and t.xzdm = '" + t.getXzdm() + "' ");
			sql.append(searchTj);
			sql.append(" and t.shzt not in ('0' , '3') order by sqsj desc");
			/*
			sql.append("select t.*,case when t.shzt in ('0', '3') or nvl(a.shzt, 0) in ('0') then 'yes' else 'no' end as ff,xn||xqmc pjzq ");
			sql.append("from VIEW_NEW_DC_PJPY_JXSQ t left join xg_xtwh_shztb a on t.sqid=a.ywid  left join XG_XTWH_SPBZ b ");
			sql.append("on a.lcid = b.splc and a.gwid = b.spgw where b.xh='1' and t.xh=?  order by to_number(t.xsxh) ");
			*/
			retData = getPageList(t, sql.toString(), inputValue);
		}
		
		return retData;
		
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(PjxmsqModel t, User user)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		super.setClass(PjxmsqModel.class);
		super.setTableName("xg_pjpy_new_xmsq");
		super.setKey("sqid");
	}

	/**
	 * 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：张小彬[工号:1036]
	 * @日期：2013-12-10 下午05:19:24
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param id
	 * @param jdid
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean insertDbjd(String id,String jdid) throws Exception{
		
		String sql = "insert into xg_pjpy_new_xmsh(sqid,shid) values (?,?)";
		
		return dao.runUpdate(sql, new String[]{id,jdid});
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getModel(java.lang.Object, java.lang.String, java.lang.String[])
	 */
	
	@Override
	protected PjxmsqModel getModel(PjxmsqModel t, String sql, String[] input)
			throws Exception {
		// TODO 自动生成方法存根
		return super.getModel(t, sql, input);
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getModel(java.lang.Object)
	 */
	
	@Override
	public PjxmsqModel getModel(PjxmsqModel t) throws Exception {
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select t1.*,t2.xmmc,t2.lxdm,t2.xzdm,t2.xmje,t3.xqmc from xg_pjpy_new_xmsq t1 ");
		sql.append(" left join xg_pjpy_new_pjxmb t2 on t1.xmdm=t2.xmdm");
		sql.append(" left join xqdzb t3 on t1.xq=t3.xqdm");
		sql.append(" where t1.sqid=?");
		return super.getModel(t, sql.toString(), new String[]{t.getSqid()});
	}

	
	/**
	 * 
	 * @描述: 查询申请记录所对应审核记录列表
	 * @作者：张小彬[工号:1036]
	 * @日期：2013-12-10 下午05:19:24
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param sqid
	 * @return
	 * List<HashMap<String,String>> 返回类型
	 */
	public List<HashMap<String,String>> getSpjlList(String sqid){
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select t1.id guid,t1.shid,t1.shzt,t1.shsj,t1.shr,t1.shyj,t2.xmdm,t2.xmmc,t3.xm,t4.mc gwmc,");
		sql.append(" decode(t1.shzt,'0','未审核','1','通过','2','不通过','3','退回','9','取消审核') shztmc ");
		sql.append(" from xg_pjpy_new_xmsh t1 left join xg_pjpy_new_pjxmb t2 on t1.pdjx=t2.xmdm ");
		sql.append(" left join yhb t3 on t1.shr=t3.yhm left join xg_xtwh_spgw t4 on t1.shid=t4.id ");
		sql.append(" where t1.sqid=?  order by shsj asc");
		
		return dao.getListNotOut(sql.toString(), new String[]{sqid});
	}
	
	/**
	 * 
	 * @描述: 查询申请记录所对应的待审核记录信息
	 * @作者：张小彬[工号:1036]
	 * @日期：2013-12-10 下午05:19:24
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param sqid
	 * @return
	 * HashMap<String,String> 返回类型
	 */
	public HashMap<String,String> getDshGwid(String sqid){
		
		String sql = "select * from xg_pjpy_new_xmsh where sqid=? and shzt=0";
		
		return dao.getMapNotOut(sql, new String[]{sqid});
	}
	
	/**
	 * 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：张小彬[工号:1036]
	 * @日期：2013-12-10 下午07:16:27
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param sqid
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getDsgw(String sqid){
		
		String sql = "select shid from xg_pjpy_new_xmsh where sqid=? and shzt='0'";
		
		return dao.getOneRs(sql, new String[]{sqid}, "shid");
	}

	/** 
	 * @描述:判断学生是否存在参评组中
	 * @作者：cq [工号：785]
	 * @日期：2014-5-16 下午05:22:11
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param csszModel
	 * @param user
	 * @return
	 * String 返回类型 
	 * @throws 
	 */
	public String isExistCpz(CsszModel csszModel, String xh) {
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1) count from xg_pjpy_new_cpmdb where xn = ? and xq = ? and xh = ? and bjdm is not null ");
		
		return dao.getOneRs(sql.toString(), new String[]{csszModel.getXn(),csszModel.getXq(),xh}, "count");
	}
	
	
	/**
	 * 
	 * @描述:得到某学生已申请项目的详细信息 (copy from mobile)
	 * @作者：ligl
	 * @日期：2014-3-26 下午04:28:45
	 * @修改记录: 
	 * @param xh
	 * @param xmdm
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String,String> getYsqxmDetail(String xh,String xmdm)throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("select t.*,case when t.shzt in ('0', '3') or nvl(a.shzt, 0) in ('0') then 'yes' else 'no' end as ff ");
		sql.append("from VIEW_NEW_DC_PJPY_JXSQ t left join xg_xtwh_shztb a on t.sqid=a.ywid  left join XG_XTWH_SPBZ b ");
		sql.append("on a.lcid = b.splc and a.gwid = b.spgw where b.xh='1' and t.xh=? and xmdm=?  order by to_number(t.xsxh) ");
		
		return dao.getMapNotOut(sql.toString(), new String[]{xh,xmdm});
		
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
	public List<HashMap<String, String>> getYsqxmAllYsh(SqshModel model,User user) throws Exception {
		
		String searchTjByUser = SearchService.getSearchTjByUser(user, "e", "xydm", "bjdm");		
		StringBuilder sb = new StringBuilder();
	
		sb.append("select xn,xq,dqxmdm,xmmc,count(1) sqrs from (");
		sb.append(" select a.xn, a.xq, a.dqxmdm, a.xh, b.shzt, c.xmmc,b.gwid,");
		sb.append(" row_number() over(partition by b.ywid order by b.shsj desc) as lvl");
		sb.append(" from xg_pjpy_new_xmsq a");
		sb.append(" left join (select * from (select t1.*,row_number() over(partition by t1.ywid order by t1.shsj desc) rn from xg_xtwh_shztb t1) where rn=1) b");
		sb.append(" on a.sqid = b.ywid");
		sb.append(" left join xg_pjpy_new_pjxmb c");
		sb.append(" on a.dqxmdm = c.xmdm");
		sb.append(" left join xg_pjpy_new_cpmdb d");
		sb.append(" on a.xh = d.xh");
		sb.append(" and a.xn = d.xn");
		sb.append(" and a.xq = d.xq");
		sb.append(" left join view_njxyzybj_all e");
		sb.append(" on d.bjdm = e.bjdm");
		sb.append(" left join xg_xtwh_spgwyh f");
		sb.append(" on b.gwid=f.spgw");
		sb.append(" where b.shzt not in ('0', '4')");
		sb.append(" and d.xh is not null");
		sb.append(" and spyh = '"+user.getUserName()+"'");
		sb.append(searchTjByUser);
		sb.append(" ) where lvl = '1'  group by xn,xq,dqxmdm,xmmc");
		String[] input = {};
		return dao.getListNotOut(sb.toString(), input);
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
	public List<HashMap<String, String>> getYsqxmAll(SqshModel model,User user) throws Exception {
		
		String searchTjByUser = SearchService.getSearchTjByUser(user, "e", "xydm", "bjdm");		
		StringBuilder sb = new StringBuilder();
	
		sb.append("select dqxmdm,xmmc,sum(dsh) dsh,count(1) sqrs from (");
		sb.append(" select a.xn, a.xq, a.dqxmdm, a.xh,c.xmmc,b.gwid,");
		sb.append(" case when b.shzt in ('0', '4') then 1 else 0 end dsh");
		sb.append(" from xg_pjpy_new_xmsq a");
		sb.append(" left join (select * from (select t1.*,row_number() over(partition by t1.ywid order by t1.shsj desc) rn from xg_xtwh_shztb t1) where rn=1) b on a.sqid = b.ywid");
		sb.append(" left join xg_pjpy_new_pjxmb c on a.dqxmdm = c.xmdm");
		sb.append(" left join xg_pjpy_new_cpmdb d on a.xh = d.xh and a.xn = d.xn and a.xq = d.xq");
		sb.append(" left join view_njxyzybj_all e on d.bjdm = e.bjdm");
		sb.append(" left join xg_xtwh_spgwyh f on b.gwid=f.spgw");
		sb.append(" where d.xh is not null");
		sb.append(" and spyh = '"+user.getUserName()+"'");
		sb.append(searchTjByUser);
		sb.append("  )   where xn||xq=(select xn||xq from xg_pjpy_new_csszb where rownum=1) ");
		sb.append(" group by dqxmdm,xmmc");
		String[] input = {};
		return dao.getListNotOut(sb.toString(), input);
	}
	
	
	/**
	 * 
	 * @描述: 所有审核级别审核意见汇总
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-3-16 上午08:36:57
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param xn
	 * @param xq
	 * @param xmmc
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getAllShyjList(String xh, String xn, String xq, String xmdm) {
		
		StringBuffer sql = new StringBuffer();	
		sql.append(" select t1.shyj from xg_xtwh_shztb t1 left join xg_pjpy_new_xmsq t2 on t1.ywid = t2.sqid ");
		sql.append(" where xh = ? and xn = ? and xq = ? and xmdm = ? order by shsj asc ");
		String[] inputValue = { xh,xn,xq,xmdm };
		return dao.getListNotOut(sql.toString(), inputValue);
		
	}
	
	/**
	 * @描述：获取 学年、第1学期、第2学期 绩点
	 * @作者：卓耐[工号:1391]
	 * @日期：2016年11月15日 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * HashMap<String,String> 返回类型
	 */
	public HashMap<String,String> getJd_10277(String xh,String xn){
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from (select m.*, n.jd xnjd from (select a.xh xh, substr(a.xq, 1, 9) xn, a.jd xqjd1, b.jd xqjd2 from ");
		sql.append(" (select * from XG_V_XQJD@dblink_jwtoxg where substr(xq, 10, 10) = '1') a left join (select * from XG_V_XQJD@dblink_jwtoxg where substr(xq, 10, 10) = '2')");
		sql.append(" b on a.xh = b.xh and substr(a.xq, 1, 9) = substr(b.xq, 1, 9)) m left join XG_V_XNJD@dblink_jwtoxg n on m.xh = n.xh and m.xn = n.xn where");
		sql.append(" m.xh = ? and m.xn = ? ) where rownum = '1'");
		return dao.getMapNotOut(sql.toString(), new String[]{xh,xn});
	}
	
	/**
	 * 
	 * @描述：获取最高和最低文化课成绩
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-11-29 下午05:03:31
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param xn
	 * @param xq
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public  HashMap<String,String> getMaxOrMinWfkCj(String xh,String xn ,String xq){
		StringBuilder sql = new StringBuilder();
		List<String> paraList = new ArrayList<String>();
		sql.append(" select max(cj) whkzgf,min(cj) whkzdf from cjb where xn = ? and xh = ?");
		paraList.add(xn);
		paraList.add(xh);
		if(StringUtils.isNotNull(xq)){
			sql.append(" and xq = ?"); 
			paraList.add(xq);
		}
		return dao.getMapNotOut(sql.toString(),paraList.toArray(new String[]{}));
	}
	
	/**
	 * 
	 * @描述: 返回何时毕业届数
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2018-4-28 下午02:32:37
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getHsbyjs(String xh){
		StringBuilder sql = new StringBuilder();
		sql.append(" select (nj+xz) js  from xsxxb where xh = ?");
		return dao.getOneRs(sql.toString(), new String[]{xh}, "js");
	}
	
}
