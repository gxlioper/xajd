/**
 * @部门:学工产品事业部
 * @日期：2013-9-9 下午12:07:04 
 */
package com.zfsoft.xgxt.rcsw.qjgl.qjsq;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.Arrays2;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.base.util.Constants;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用)
 * @作者： 张昌路[工号:982]
 * @时间： 2013-9-9 下午12:07:04
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class QjsqDao extends SuperDAOImpl<QjsqForm> {

	/*
	 * 描述: @see
	 * com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object,
	 * xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(QjsqForm t, User user)
			throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(t.getSearchModel());

		StringBuffer sql = new StringBuffer();
		//浙江经济职业个性化（判断请假开始时间是否超时）
		if("12866".equals(Base.xxdm)){
			sql.append("select a.*,case when a.shzt = '0' and to_char(sysdate, 'yyyy/mm/dd hh24:mi') > a.cssj then '1' when a.shzt = '5' and to_char(sysdate, 'yyyy/mm/dd hh24:mi') > a.cssj then '1' else '0' end sfcs from (");
		}else{			
			sql.append(" select a.* from (");
		}
		sql.append(" select a.*,(select xqmc from xqdzb where xqdm=a.xq) xqmc,c.xjzt,xsxx.xm,xsxx.xb,xsxx.nj,xsxx.xymc,xsxx.xydm,xsxx.zymc,xsxx.zydm,xsxx.bjmc,xsxx.bjdm,b.qjlxmc,decode(a.qjzt,'','草稿','0','草稿','1','已提交')qjztmc,");
		sql.append(" decode(a.shzt,'0','未提交','1','通过','2','不通过','3','已退回','5','审核中','','无需审核',a.shzt) shztmc,");
		sql.append(" decode(c.xjzt, '', '未销假', '0', '未销假', '1', '已销假') xjztmc");
		//湖南涉外经济学院
		if("12303".equals(Base.xxdm)){
			sql.append(" , cwxx.lddm,cwxx.ldmc,cwxx.qsh");
		}
		//浙江经济职业个性化（判断请假开始时间是否超时）
		if("12866".equals(Base.xxdm)){
			sql.append(", case when instr(a.kssj,':') = '0' then  to_char((to_date(a.kssj,'yyyy/mm/dd HH24:mi') + 1),'yyyy/mm/dd HH24:mi') else  to_char((to_date(a.kssj,'yyyy/mm/dd HH24:mi')+1 / 24 / 60),'yyyy/mm/dd HH24:mi') end cssj");			
		}
		//请假类型信息
		sql.append(" from xg_rcsw_qjgl_qjsq a left join xg_rcsw_qjgl_qjlx b on a.qjlxid=b.qjlxid ");
		//销假信息
		sql.append(" left join xg_rcsw_qjgl_qjjg c on a.qjsqid=c.qjsqid");
		//湖南涉外经济学院
		if("12303".equals(Base.xxdm)){
			sql.append(" left join view_xg_gygl_new_cwxx cwxx on a.xh = cwxx.xh");
		}
		//学生信息
		sql.append(" left join view_xsbfxx xsxx on a.xh=xsxx.xh ) a where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		return this.getPageList(t, sql.toString(), inputV);
	}
	/**
	 * @描述:获得未提交数
	 * @作者：张昌路[工号：982]
	 * @日期：2013-9-18 下午03:53:14
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return Integer 返回类型 
	 * @throws
	 */
	public Integer getWtjts(QjsqForm t, User user) throws Exception{
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		//String searchTjByUser = SearchService.getSearchTjByUser(user, "a","xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		
		StringBuffer sql = new StringBuffer();
		sql.append("select qjsqid from xg_rcsw_qjgl_qjsq a where 1=1 ");
		//sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		sql.append(" and a.qjzt=? ");
		
		List<HashMap<String, String>> list=dao.getListNotOut(sql.toString(), Arrays2.addObject(inputV,QjsqService._SQZT_CGZT));
		return null==list||list.size()<=0?0:list.size();
	}
	/**
	 * 
	 * @描述:是否可以删除
	 * @作者：张昌路[工号：982]
	 * @日期：2013-9-13 下午02:12:37
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param qjsqid
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean isCanDel(String qjsqid){
		StringBuffer sb=new StringBuffer();
		sb.append("select * from xg_rcsw_qjgl_qjsq where qjsqid=?");
		Map<String,String> map= dao.getMapNotOut(sb.toString(),new String[]{qjsqid});
		//String qjzt=map.get("qjzt");
		//如果未提交才可以提交
		//return null==qjzt||qjzt.equals("0")?true:false;
		String shzt=map.get("shzt");
		//为空则认为是无需审核 自己同步到结果库 不能进行删除
		if(StringUtils.isNull(shzt)){
			return false;
		}
		return shzt.equals(Constants.YW_WTJ)?true:false;
	}
	/*
	 * 描述: @see
	 * com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(QjsqForm t)
			throws Exception {
		return null;
	}
	/**
	 * 
	 * @描述:根据请假申请id删除对应请假结果数据
	 * @作者：张昌路[工号：982]
	 * @日期：2013-9-14 下午06:45:13
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param id
	 * @return
	 * @throws Exception
	 * int 返回类型 
	 * @throws
	 */
	public int deletQjjgForQjsqid(String id) throws Exception{
		StringBuffer sql=new StringBuffer();
		sql.append("delete xg_rcsw_qjgl_qjjg where qjsqid=?");
		return dao.runDelete(sql.toString(), new String[]{id});
	}
	/**
	 * 
	 * @描述:获得请假信息
	 * @作者：张昌路[工号：982]
	 * @日期：2013-9-14 下午04:36:50
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param qjjgid
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getQjsq(String qjsqid){
		StringBuffer sb=new StringBuffer();
		sb.append("select a.*,(select xqmc from xqdzb where xqdm=a.xq) xqmc,b.xm, b.bjmc, b.zymc, b.sjhm from xg_rcsw_qjgl_qjsq a");
		sb.append(",view_xsxxb b where a.xh=b.xh and a.qjsqid=?");
		return dao.getMapNotOut(sb.toString(),new String[]{qjsqid});
	}
	/**
	 * 
	 * @描述：获取下一个请假类型id
	 * @作者：张昌路[工号：982]
	 * @日期：2013-9-9 下午06:54:25
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return String 返回类型
	 * @throws
	 */
	public String getNextId() {
		String qjlxid = dao.getOneRs(
				"select max(qjlxid)qjlxid from xg_rcsw_qjgl_qjlx ",
				new String[] {}, "qjlxid");
		String newId = String.valueOf(Integer.parseInt(qjlxid) + 1);
		if (newId.length() == 1) {
			newId = "0" + newId;
		}
		return newId;
	}

	/**
	 * 
	 * @描述:验证请假类型，如果已经添加过不允再进行添加
	 * @作者：张昌路[工号：982]
	 * @日期：2013-9-9 下午07:18:11
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param qf
	 * @return boolean 返回类型
	 * @throws
	 */
	public boolean checkQjlx(QjsqForm qf) {
		// String
		// qjlxid=dao.getOneRs("select qjlxid from xg_rcsw_qjgl_qjgz where kssj<? and ?<=jssj",new
		// String[]{qf.getQjlxmc()},"qjlxid");
		// 如果获取到对应的id则不运行再添加
		return true;
	}
	public List<HashMap<String, String>> getQjlx(){
		StringBuffer sql=new StringBuffer();
		sql.append(" select qjlxid,qjlxmc from xg_rcsw_qjgl_qjlx where open = '1' order by qjlxid asc");
		return dao.getListNotOut(sql.toString(),new String[]{});
	}
	/**
	 * @throws Exception 
	 * 
	 * @描述:获取学生当前学年学期的课程)
	 * @作者：xiaxia [工号：1104]
	 * @日期：2014-11-18 下午02:51:21
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getQjkcList(QjsqForm model) throws Exception{
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		StringBuffer sql=new StringBuffer();
		sql.append(" select * from XG_RCSW_QJGL_QJKCB where xh='"+model.getXh()+"' and xn ='"+model.getXn()+"'and xq = '"+model.getXq()+"'");
		sql.append(" ");
		sql.append(searchTj);
		return this.getPageList(model, sql.toString(), inputV);
	}
	/**
	 * 
	 * @描述:获取请假课程信息
	 * @作者：xiaixa [工号：1104]
	 * @日期：2014-11-19 上午10:10:56
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getkcList(String[] kcbhs) throws Exception{
		
		StringBuffer sql=new StringBuffer();
		sql.append(" select distinct(kcbh) ,kcmc,rklsxm,rklslxfs from xg_rcsw_qjgl_qjkcb where kcbh");
		sql.append(" in(");
		for (int i = 0; i < kcbhs.length; i++) {
			if(i!=0){
				sql.append(","+"?");
			}
			else{
				sql.append("?");
			}
		}
		sql.append(")");
		return dao.getListNotOut(sql.toString(),kcbhs);
	}
	/*
	 * 描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	@Override
	protected void setTableInfo() {
		this.setKey("qjsqid");
		this.setTableName("xg_rcsw_qjgl_qjsq");
		this.setClass(QjsqForm.class);
	}
	
	
	/**
	 * 
	 * @描述: 删除请假明细
	 * @作者：屈朋辉[工号：445]
	 * @日期：2014-11-25 下午01:47:08
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param id
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean delQjmx(String id) throws Exception{
		String sql = "delete from XG_RCSW_QJGL_QJSQMXB where qjid=?";
		return dao.runUpdate(sql, new String[]{id});
	}

	
	/**
	 * 
	 * @描述: 保存请假明细
	 * @作者：屈朋辉[工号：445]
	 * @日期：2014-11-25 下午01:50:34
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param params
	 * @return
	 * @throws SQLException
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean saveQjmx(List<String[]> params) throws SQLException{
		
		String sql = "insert into XG_RCSW_QJGL_QJSQMXB(qjid,qjrq,qjmx) values (?,?,?)";
		int[] result = dao.runBatch(sql, params);
		return dao.checkBatchResult(result);
	}
	
	
	/**
	 * 
	 * @描述: 查询请假明细
	 * @作者：屈朋辉[工号：445]
	 * @日期：2014-11-25 下午03:03:26
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param id
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getQjmxList(String id){
		
		String sql = "select * from XG_RCSW_QJGL_QJSQMXB where qjid=?";
		return dao.getListNotOut(sql, new String[]{id});
	}
	
	/**
	 * 
	 * @描述:获取请假编号（华师大）
	 * @作者：xiaxia [工号：1104]
	 * @日期：2014-11-25 下午03:53:40
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getQjbh(String sqsj){
		StringBuffer sql=new StringBuffer();
		sql.append("select (case when length(qjbh) < 2 then '0' ||qjbh else to_char(qjbh)");
		sql.append(" end) qjbh from (select (to_number(nvl(max(qjbh),0))+1) qjbh");
		sql.append(" from (select  to_char(to_date(sqsj,'yyyy-MM-dd hh24:mi:ss'),'yyyymmdd') sqsj, qjbh");
		sql.append(" from XG_RCSW_QJGL_QJJG union all select to_char(to_date(sqsj,'yyyy-MM-dd hh24:mi:ss'),'yyyymmdd') sqsj, qjbh from XG_RCSW_QJGL_QJSQ)");
		sql.append(" where sqsj=to_char(to_date(?,'yyyy-MM-dd hh24:mi:ss'),'yyyymmdd')) ");
		return dao.getOneRs(sql.toString(), new String[]{sqsj}, "qjbh");     
	}
	
	/**
	 * 
	 * @描述:TODO(验证是否重复申请)
	 * @作者：陈青[工号：856]
	 * @日期：2015-5-25 下午07:23:42
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param kssj
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean getDupStatusDao(QjsqForm model){
		StringBuilder sql = new StringBuilder();
		sql.append("select count(1) num from xg_rcsw_qjgl_qjsq where xh= ?");
		sql.append(" and jssj > ? and kssj< ? and shzt not in ('0','3') ");
		String num=dao.getOneRs(sql.toString(), new String[]{model.getXh(),model.getKssj(),model.getJssj()}, "num");
		return  Integer.valueOf(num) > 0 ? true:false;
	}
	
	
	
	/* (non-Javadoc)
	 * @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getModel(java.lang.Object)
	 */
	@Override
	public QjsqForm getModel(QjsqForm t) throws Exception {
		String sql = "select t1.*,t2.qjlxmc,decode(t1.sflx,'1','是','0','否','') sflxmc from xg_rcsw_qjgl_qjsq t1 left join xg_rcsw_qjgl_qjlx t2 on t1.qjlxid=t2.qjlxid where t1.qjsqid=?";
		return super.getModel(sql, new String[]{t.getQjsqid()});
	}
	
	/**
	 * 
	 * @描述:有规则请假规则List
	 * @作者：yxy[工号：1206]
	 * @日期：2016-11-30 上午11:31:53
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getYlxQjgzList(){
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from xg_rcsw_qjgl_qjgz where qjlxid is not null and open = '1'");
		return dao.getListNotOut(sql.toString(), new String[]{});
	}
	
	/**
	 * 
	 * @描述:无规则请假规则List
	 * @作者：yxy[工号：1206]
	 * @日期：2016-11-30 上午11:36:44
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getWlxQjgzList(){
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from xg_rcsw_qjgl_qjgz where qjlxid is  null and open = '1'");
		return dao.getListNotOut(sql.toString(), new String[]{});
	}
	
	/**
	 * 
	 * @描述: 节假日
	 * @作者：yxy[工号：1206]
	 * @日期：2017-1-18 下午02:49:02
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public boolean checkJjr(String kssj,String jssj){
		kssj = kssj.substring(0,10);
		jssj = jssj.substring(0,10);
		StringBuffer sql = new StringBuffer();
		sql.append(" select count(1) num");
		sql.append(" from (select to_char(to_date(kssj, 'yyyy-mm-dd hh24:mi:ss'), 'yyyy-mm-dd') kssj,");
		sql.append(" to_char(to_date(jssj, 'yyyy-mm-dd hh24:mi:ss'), 'yyyy-mm-dd') jssj");
		sql.append(" from XG_RCSW_QJGL_JJRB)");
		sql.append(" where ? >= kssj");
		sql.append(" and jssj > = ? ");
		String num = dao.getOneRs(sql.toString(), new String[]{kssj,jssj}, "num");
		return "1".equals(num) ? true : false;
	}
	
	/**
	 * 
	 * @描述: 验证kssj和jssj是否都为周末
	 * @作者：yxy[工号：1206]
	 * @日期：2017-1-18 下午05:21:19
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param kssj
	 * @param jssj
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean checkWeekDay(String kssj,String jssj){
		StringBuffer sql = new StringBuffer();
		sql.append(" select to_char(to_date(?, 'yyyy-mm-dd hh24:mi:ss'), 'day') weekdaykssj,");
		sql.append(" to_char(to_date(?, 'yyyy-mm-dd hh24:mi:ss'), 'day') weekdayjssj");
		sql.append(" from dual");
		HashMap<String, String> dataMap = dao.getMapNotOut(sql.toString(), new String[]{kssj,jssj});
		String weekdaykssj = dataMap.get("weekdaykssj");
		String weekdayjssj = dataMap.get("weekdayjssj");
		HashMap<String, String> compareMap = new HashMap<String, String>();
		compareMap.put("星期五", "星期五");
		compareMap.put("星期六", "星期六");
		compareMap.put("星期日", "星期日");
		if((compareMap.containsKey(weekdaykssj)) && (compareMap.containsKey(weekdayjssj)) ){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 
	 * @描述: 判断周末假是否在开始时间和结束时间在同一周
	 * @作者：yxy[工号：1206]
	 * @日期：2017-1-22 上午10:10:36
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param kssj
	 * @param jssj
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean checkIsOneWeekend(String kssj,String jssj){
		StringBuffer sql = new StringBuffer();
		sql.append(" select (to_date(?,'yyyy-mm-dd hh24:mi:ss')-to_date(?,'yyyy-mm-dd hh24:mi:ss')) days from dual");
		String days = dao.getOneRs(sql.toString(), new String[]{jssj,kssj},"days" );
		return Float.parseFloat(days)< 3;
	}
	
	/** 
	 * @描述:获取交通工具名称(西安民族大学)
	 * @作者：lj[工号：1282]
	 * @日期：2017-7-7 上午11:58:25
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * String 返回类型 
	 * @throws 
	 */
	public String getJtgjmc(String dm){
		String sql = "select mc from xg_rcsw_lxqxdj_dmwhb where dm = ?";
		return dao.getOneRs(sql, new String[]{dm}, "mc");
	}
	
	/**
	 * 
	 * @描述: 徐州医药个性化判断
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2018-1-12 上午10:36:05
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public String checkIfOverTime(String startDate,String endDate){
		HashMap<String, String> xzmap = getSjxz();
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1) cnt from (");
		sql.append(" select to_char(to_date(?,'yyyy-mm-dd hh24:mi:ss'),'hh24:mi') kssj,");
		sql.append(" to_char(to_date(?,'yyyy-mm-dd hh24:mi:ss'),'hh24:mi') jssj from dual) where kssj >=? and jssj <= ?");
		String cnt = dao.getOneRs(sql.toString(), new String[]{startDate,endDate,xzmap.get("ksxz"),xzmap.get("jsxz")}, "cnt");
		return "0".equals(cnt)?"请假时段限制在"+xzmap.get("ksxz")+"至"+xzmap.get("jsxz")+"之间" : "";
	}
	
	/**
	 * 
	 * @描述: 获取时间验证
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2018-1-12 上午10:41:37
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public HashMap<String,String> getSjxz(){
		StringBuilder sql = new StringBuilder();
		sql.append(" select xzkssjh || ':' || xzkssjm ksxz, xzjssjh || ':' || xzjssjm jsxz from XG_RCSW_QJSDCSSZB t");
		return dao.getMapNotOut(sql.toString(), new String[]{});
	}
}
