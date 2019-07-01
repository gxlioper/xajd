/**
 * @部门:学工产品事业部
 * @日期：2016-5-5 上午09:59:59 
 */  
package xsgzgl.rcsw.wsbz.yy;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;
import xsgzgl.rcsw.wsbz.dmwh.WsbzDmwhDao;
import xsgzgl.rcsw.wsbz.dmwh.WsbzDmwhForm;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张昌路[工号:982]
 * @时间： 2016-5-5 上午09:59:59 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class WsbzYyDao extends SuperDAOImpl<WsbzYyForm> {

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(WsbzYyForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(WsbzYyForm t, User user)
			throws Exception {
		// TODO 自动生成方法存根
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (");
		sql.append(" select t.sqid,t.xh,t.fddm,decode(t2.hdpl,'1',t.yyrq,'2',t.yyzc) yyrq,t.sqsj,t.sqly,t.yyzc,t.yyrq yyrqday,  ");
		sql.append(" t1.xm, t1.bjdm, t1.bjmc, t1.xydm,t1.zydm,t1.zymc, t1.xymc, t1.nj, t1.xb,t1.sjhm,t2.fdmc,t2.hdpl,");
		sql.append(" decode(t2.hdpl,'1','天','2','周',t2.hdpl) hdplmc,t2.sj,t2.dd,t2.rs,t2.gzzz,t2.fwyq");
		sql.append(" from rcsw_wsbz_sq t");
		sql.append(" left join view_xsjbxx t1");
		sql.append(" on t.xh = t1.xh");
		sql.append(" left join rcsw_wsbz_dmwh t2");
		sql.append(" on t.fddm = t2.fddm");
		sql.append(" )t where 1= 1  ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		// TODO 自动生成方法存根
		this.setClass(WsbzYyForm.class);
		this.setTableName("rcsw_wsbz_sq");
		this.setKey("sqid");
	}
	
	public boolean AddUpdateYyTimeCheck(){
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1) num");
		sql.append(" from (select to_date(to_char(sysdate, 'yyyy-MM-dd hh24:mi:ss'),");
		sql.append(" 'yyyy-MM-dd hh24:mi:ss') nowtime");
		sql.append(" from dual) t,");
		sql.append(" (select to_date(to_char( trunc(sysdate, 'DD') - to_char(sysdate, 'D') + 4, 'yyyy-MM-dd') || '00:00:00',");
		sql.append(" 'yyyy-MM-dd hh24:mi:ss') beagintime");
		sql.append(" from dual)t1,");
		sql.append(" (select to_date(to_char( trunc(sysdate, 'DD') - to_char(sysdate, 'D') + 4, 'yyyy-MM-dd') || '12:00:00',");
		sql.append("  'yyyy-MM-dd hh24:mi:ss') endtime");
		sql.append("  from dual)t2");
		sql.append(" where t.nowtime between t1.beagintime and t2.endtime");
		sql.append(" ");
		sql.append(" ");
		sql.append(" ");
		String num =  dao.getOneRs(sql.toString(), new String[]{}, "num");
		if(num.equals("0")){
			return false;
		}else{
			return true;
		}
	}
	/**
	 * 
	 * @描述:判断按天为活动频率的活动申请是否可以修改
	 * 判断依据为预约日期前一天的12点之前
	 * @作者：yxy[工号：1206]
	 * @日期：2016-6-21 下午01:46:33
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param sqsj
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean updateYyTimeCheck(String sqsj){
		StringBuilder sql = new StringBuilder();
		if(StringUtils.isNull(sqsj)){
			return false;
		}
//		if(sqsj.indexOf("至") != -1){
//			String[] sqsjs=sqsj.split("至");
//			sqsj = this.getPriviousDay(sqsjs[0]);
//		}
		sql.append(" select count(1) num");
		sql.append(" from(select to_char(sysdate,'yyyy-MM-dd hh24:mi:ss') beagintime,");
		sql.append(" to_char(to_date(?,'yyyy-mm-dd')-(select jzts from rcsw_wsbz_qjcswh),'yyyy-mm-dd') || ' '||(select jzsj from rcsw_wsbz_qjcswh) endtime from dual )");
		sql.append(" where beagintime < endtime ");
		sql.append(" ");
		String num =  dao.getOneRs(sql.toString(), new String[]{sqsj}, "num");
		if(num.equals("0")){
			return false;
		}else{
			return true;
		}
	}
	
	public String produceHdplDay(){
		StringBuilder sql = new StringBuilder();
		//判断是否在预约前一天12点之前
		String flag = this.getCheckDaytwf();
		sql.append(" select to_char(yyrq, 'yyyy-mm-dd') yyrq");
		sql.append("  from (select case");
		sql.append("  when t.day in ('星期一', '星期二')   then");
		if("1".equals(flag)){
			sql.append("  trunc(sysdate, 'DD') - to_char(sysdate, 'D') + 4");
		}else{
			sql.append("    NEXT_DAY(sysdate+1, 4)");
		}
		sql.append("  else");
		sql.append(" NEXT_DAY(sysdate, 4)");
		sql.append(" end yyrq");
		sql.append(" from (select to_char(sysdate, 'day') day from dual) t) t1");
		return dao.getOneRs(sql.toString(), new String[]{}, "yyrq");
	}
	
	/**
	 * @描述: 当前日期所在月周次数，当前日期所在周，当前月
	 * @作者：yxy[工号：1206]
	 * @日期：2016-6-21 下午03:23:20
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> produceHdplfWeek(){
		StringBuilder sql = new StringBuilder();
		sql.append(" select");
		sql.append(" to_char(last_day(sysdate),'w') zcnum ,");
		sql.append(" TO_CHAR(SYSDATE,'WW') - TO_CHAR(TRUNC(SYSDATE,'MM'),'WW') + 1 AS weekofmon,");
		sql.append(" to_char(sysdate,'mm') dy");
		sql.append(" from dual");
		sql.append(" ");
		sql.append(" ");
		return dao.getMapNotOut(sql.toString(), new String[]{});
	}
	
	public boolean isNowDateHaveYyjl(String xh){
		StringBuilder sql = new StringBuilder();
		sql.append("  select count(1) num");
		sql.append("  from rcsw_wsbz_sq t");
		sql.append("  where to_char(to_date(t.sqsj,'yyyy-MM-dd hh24:mi:ss'),'yyyy-mm-dd') =");
		sql.append("  to_char(sysdate, 'yyyy-mm-dd') and xh = ?");
		String num  = dao.getOneRs(sql.toString(), new String[]{xh}, "num");
		if(num.equals("0")){
			return true;
		}else{
			return false;
		}
	}
	
	//验证报名次数
	public boolean isEveryXhTwoRecode(String xh){
		StringBuilder sql = new StringBuilder();
		StringBuilder sql1 = new StringBuilder();
		sql.append(" select count(1) num from rcsw_wsbz_sq t where t.xh = ?");
		sql1.append("select bmcs num from rcsw_wsbz_qjcswh");
		String num  = dao.getOneRs(sql.toString(), new String[]{xh}, "num");
		String bmcs  = dao.getOneRs(sql1.toString(), new String[]{}, "num");
		if(Integer.parseInt(num) >= Integer.parseInt(bmcs)){
			return false;
		}else{
			return true;
		}
	}
	
	public boolean isNotOverRssx(String fddm,String rs,String yyrq,String hdpl){
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1) num");
		sql.append(" from rcsw_wsbz_sq t");
		sql.append(" where fddm = ?");
		if("1".equals(hdpl)){
			sql.append(" and t.yyrq = ?");
		}else{
			sql.append(" and t.yyzc = ?");
		}
		
		String num = dao.getOneRs(sql.toString(), new String[]{fddm,yyrq}, "num");
		if(Integer.parseInt(num) >= Integer.parseInt(rs)){
			return false;
		}else{
			return true;
		}
	}
	
	public List<HashMap<String, String>> getFdmcList(){
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from rcsw_wsbz_dmwh");
		return dao.getListNotOut(sql.toString(), new String[]{} );
	}
	
	public HashMap<String,String> getFdmcInfo(String fddm){
		StringBuilder sql = new StringBuilder();
		sql.append("select * from rcsw_wsbz_dmwh where fddm = ?");
		return dao.getMapNotOut(sql.toString(), new String []{fddm});
	}
	
	public String getSyrs(String fddm,String yyrq) throws Exception{
		StringBuilder sql = new StringBuilder();
		WsbzDmwhForm dmwhform = new WsbzDmwhDao().getModel(fddm);
		String yyrqstr = "";
		if(dmwhform.getHdpl().equals("1")){
			yyrqstr = "yyrq";
		}else{
			yyrqstr = "yyzc";
		}
		sql.append(" select (t.rs-t1.num) num from");
		sql.append(" (select rs from rcsw_wsbz_dmwh where fddm = ?)t,");
		sql.append(" (select count(1) num");
		sql.append(" from rcsw_wsbz_sq t");
		sql.append(" where fddm = ?");
		sql.append(" and t."+yyrqstr +" = ?)t1");
		return dao.getOneRs(sql.toString(), new String[]{fddm,fddm,yyrq}, "num");
	}
	
//	public boolean isNotOverRssx(String fddm,String rs,String yyrq,String xh){
//		StringBuilder sql = new StringBuilder();
//		sql.append(" select count(1) num");
//		sql.append(" from rcsw_wsbz_sq t");
//		sql.append(" where fddm = ?");
//		sql.append(" and t.yyrq = ?");
//		sql.append(" and t.xh != ?");
//		String num = dao.getOneRs(sql.toString(), new String[]{fddm,yyrq,xh}, "num");
//		if(Integer.parseInt(num) >= Integer.parseInt(rs)){
//			return false;
//		}else{
//			return true;
//		}
//	}
	
	//同一个预约预约日期内，学生不可以申请两次
	public boolean isNotSameTwo(String fddm,String yyrq,String xh) throws Exception{
		StringBuilder sql = new StringBuilder();
		WsbzDmwhForm dmwhform = new WsbzDmwhDao().getModel(fddm);
		sql.append(" select count(1) num");
		sql.append(" from rcsw_wsbz_sq t");
		sql.append(" where fddm = ?");
		if("1".equals(dmwhform.getHdpl())){
			sql.append(" and t.yyrq = ?");
		}else{
			sql.append(" and t.yyzc = ?");
		}
		sql.append(" and t.xh = ?");
		String num = dao.getOneRs(sql.toString(), new String[]{fddm,yyrq,xh}, "num");
		if(Integer.parseInt(num) >= 1){
			return false;
		}else{
			return true;
		}
	}
	
	/**
	 * 
	 * @描述:
	 * @作者：yxy[工号：1206]
	 * @日期：2016-6-21 上午08:58:28
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param sqsj
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean updateZctimeCheck(String yyrq){
		StringBuilder sql = new StringBuilder();
		sql.append(" ");
		sql.append(" ");
		sql.append(" ");
		sql.append(" ");
		sql.append(" ");
		sql.append(" ");
		sql.append(" ");
		sql.append(" ");
//		String priday = this.getPriviousDay(yyrq);
//		StringBuilder sql = new StringBuilder();
//		sql.append(" select count(1) num");
//		sql.append(" from");
//		sql.append(" (select sysdate beagintime");
//		sql.append(" from dual)t1,");
//		sql.append(" (select to_date(? || '12:00:00',");
//		sql.append(" 'yyyy-MM-dd hh24:mi:ss') endtime");
//		sql.append(" from dual)t2");
//		sql.append(" where t1.beagintime < t2.endtime");
//		sql.append(" ");
//		sql.append(" ");
//		sql.append(" ");
//		String num =  dao.getOneRs(sql.toString(), new String[]{priday}, "num");
//		if(num.equals("0")){
//			return false;
//		}else{
//			return true;
//		}
		return true;
	}
	
	/**
	 * 
	 * @描述:获取当前日期所在月有多少周次,前日期所在周次
	 * @作者：yxy[工号：1206]
	 * @日期：2016-6-21 上午09:16:31
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getDqrqZc(){
		StringBuilder sql = new StringBuilder();
		sql.append(" select to_char(last_day(sysdate),'w') zcnum");
		sql.append(" ,TO_CHAR(SYSDATE,'WW') - TO_CHAR(TRUNC(SYSDATE,'MM'),'WW') + 1 AS weekofmon ");
		sql.append(" from dual");
		return dao.getMapNotOut(sql.toString(), new String[]{});
	}
	
	
	/**
	 * 
	 * @描述: 获取预约时间前一天
	 * @作者：yxy[工号：1206]
	 * @日期：2016-6-21 上午11:27:12
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getPriviousDay(String yyrq){
		StringBuilder sql = new StringBuilder();
		sql.append("  select to_char(to_date(?,'yyyy-mm-dd')-2,'yyyy-MM-dd') priday from dual");
		return dao.getOneRs(sql.toString(), new String[]{yyrq}, "priday");
	}
	
	/**
	 * @描述:判断是否在星期五之前
	 * @作者：yxy[工号：1206]
	 * @日期：2016-6-21 下午03:31:34
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean checkPriviousFriday(){
		StringBuilder sql = new StringBuilder();
		sql.append("  select case");
		sql.append("  when t.day in ('星期一', '星期二', '星期三','星期三','星期四','星期五') then");
		sql.append("  '0'");
		sql.append("  else");
		sql.append("  '1'");
		sql.append("  end sfxz");
		sql.append("  from (select to_char(sysdate, 'day') day from dual) t");
	    return ("1").equals(dao.getOneRs(sql.toString(), new String[]{}, "sfxz")) ? true : false;
	}
	
	/**
	 * 
	 * @描述:判断是否超过预约前一天12点
	 * @作者：yxy[工号：1206]
	 * @日期：2016-6-21 下午05:05:58
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getCheckDaytwf(){
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1) num from");
		sql.append(" (");
		sql.append(" select to_date(to_char(trunc(sysdate, 'DD') -");
		sql.append("  to_char(sysdate, 'D') + 3,");
		sql.append("  'yyyy-MM-dd') || '12:00:00',");
		sql.append("  'yyyy-MM-dd hh24:mi:ss') endtime,");
		sql.append("  to_date(to_char(sysdate, 'yyyy-MM-dd hh24:mi:ss'),");
		sql.append(" 'yyyy-MM-dd hh24:mi:ss') begintime");
		sql.append("  from dual t) where begintime < endtime");
		return dao.getOneRs(sql.toString(), new String[]{}, "num");
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述:更新yyrq字段为空
	 * @作者：yxy[工号：1206]
	 * @日期：2016-6-29 下午04:09:12
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param sqid
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean updateYyrqdaynull(String sqid) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" update rcsw_wsbz_sq set yyrq = '' where sqid = ?");
		return dao.runUpdate(sql.toString(), new String[]{sqid});
	}
	
	/**
	 * 
	 * @描述:更新yyzc字段为空
	 * @作者：yxy[工号：1206]
	 * @日期：2016-6-29 下午04:12:08
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param sqid
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean updateYyzcnull(String sqid) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" update rcsw_wsbz_sq set yyzc = '' where sqid = ?");
		return dao.runUpdate(sql.toString(), new String[]{sqid});
	}
	
	/**
	 * 
	 * @描述: 获取预约周次表List
	 * @作者：yxy[工号：1206]
	 * @日期：2016-10-27 下午06:47:14
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getYyzcb(){
		StringBuffer sql = new StringBuffer();
		sql.append(" select yyzc from yyzcb");
		return dao.getListNotOut(sql.toString(), new String[]{});
	}
	
	/**
	 * 
	 * @描述:获取预约日期表
	 * @作者：yxy[工号：1206]
	 * @日期：2016-10-27 下午06:52:16
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getYyrqb(){
		StringBuffer sql = new StringBuffer();
		sql.append(" select yyrq from yyrqb order by yyrq");
		return dao.getListNotOut(sql.toString(), new String[]{});
	}

	public boolean isExist(String yyrq) {
		String sql = "select count(1) num from yyrqb where yyrq = ?" ;
		String num = dao.getOneRs(sql, new String[]{yyrq}, "num");
		return Integer.valueOf(num)>0;
	}
	
}
