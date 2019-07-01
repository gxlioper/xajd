/**
 * @部门:学工产品事业部
 * @日期：2013-8-19 下午04:44:15 
 */  
package com.zfsoft.xgxt.xlzx.zxswh;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.base.util.UniqID;
/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 心理咨询管理模块
 * @类功能描述: 心理咨询-咨询师排班管理(这里用一句话描述这个类的作用) 
 * @作者： wanghj [工号：1004]
 * @时间： 2013-8-19 下午04:35:22 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ZxspbDao extends SuperDAOImpl<ZxspbForm> {
	
	/**
	 * 根据职工号查询咨询师排班信息
	 * @param model
	 * @return
	 */
	public List<HashMap<String, String>> getZxspbInfoByZgh(String zgh){
		
		StringBuilder sql=new StringBuilder();
		
		sql.append(" select * from xg_xlzx_zxspbb t where t.datazt='1' and t.zgh  like '%'||?||'%' ");
		return dao.getListNotOut(sql.toString(), new String[]{zgh});
	}
	
	/**
	 * 根据排班编号查询详情
	 * @param model
	 * @return
	 */
	public HashMap<String,String> getPbInfoById(String id){
		
		StringBuilder sql=new StringBuilder();
		
		sql.append("  select t.id,t.pbtype,t.pbdate,t.zgh,to_char(to_date(t.pbdate, 'yyyy-MM-dd'), 'day') weekday,t.createsj,t.bz from xg_xlzx_zxspbb t");
		sql.append(" where t.datazt = '1'  and t.id = ? ");
		return dao.getMapNotOut(sql.toString(), new String[]{id});
	}
	
	/**
	 * 根据日期查询排班情况
	 * @param model
	 * @return
	 */
	public HashMap<String,String> getPbInfoByDate(String date){
		
		StringBuilder sql=new StringBuilder();
		
		sql.append("  select t.id,t.pbtype,t.pbdate,t.zgh,to_char(to_date(t.pbdate, 'yyyy-MM-dd'), 'day') weekday,t.createsj,t.bz from xg_xlzx_zxspbb t");
		sql.append(" where t.datazt = '1'  and t.pbdate = ? ");
		return dao.getMapNotOut(sql.toString(), new String[]{date});
	}
	/**
	 * 根据日期获取星期
	 * @param model
	 * @return
	 */
	public HashMap<String,String> getWeekdayByDate(String date){
		
		StringBuilder sql=new StringBuilder();
		
		sql.append("  select  to_char(to_date(?, 'yyyy-MM-dd'), 'day', 'nls_date_language=''simplified chinese''') weekday from dual ");
		return dao.getMapNotOut(sql.toString(), new String[]{date});
	}
	
	
	
	/**
	 * 根据职工号查询咨询师排班信息
	 * @param model
	 * @return
	 */
	public HashMap<String,String> getZxspbById(String id){
		
		StringBuilder sql=new StringBuilder();
		
		sql.append(" select * from xg_xlzx_zxspbb where  id = ? ");
		return dao.getMapNotOut(sql.toString(), new String[]{id});
	}
	

	public List<HashMap<String, String>> getZxspbAsRl(String queryDate) throws Exception{
		
		StringBuilder sql=new StringBuilder();
		sql.append(" select to_char(a.rq, 'yyyy-MM-dd') rq,a.weekday,case when c.id is not null then 'arrangement' else 'close' end color,");
		sql.append(" c.id pbid,c.zgh,c.bz,c.pbtype,c.pbdate");
		sql.append(" from (select rq, to_char(rq, 'day') weekday from (select trunc(to_date(?, 'yyyy-mm-dd'), 'MM') + rownum - 1 rq from dual  connect by rownum <= to_number(to_char(last_day(to_date(?,'yyyy-mm-dd')), 'dd')))) a");
		sql.append(" left join  xg_xlzx_zxspbb  c on a.rq = to_date(c.pbdate, 'yyyy-MM-dd') ");
		
		sql.append(" order by a.rq asc");
		return dao.getListNotOut(sql.toString(), new String[]{queryDate,queryDate});
		
	}
	
	
	public List<HashMap<String, String>> getZxspbAsRlForXs(String xh,String queryDate) throws Exception{
		
		String[] inputValue ={queryDate,queryDate,xh}; 
		StringBuilder sql=new StringBuilder();
		sql.append(" select to_char(a.rq, 'yyyy-MM-dd') rq,a.weekday, ");
		sql.append(" case when c.id is not null and  (select instr(c.zgh ,d.zgh,1,1) from dual)>0  then");
		sql.append(" 'open' when c.id is not null and c.zgh is not null and d.id is null then 'arrangement' else 'close' end color,");
		sql.append(" c.id pbid,c.zgh,c.bz,c.pbtype,c.pbdate,d.xh,d.zgh,(case when (select instr(c.zgh ,d.zgh,1,1)  from dual)>0 then 'Y' else '' end ) sfyy");
		sql.append(" from (select rq, to_char(rq, 'day') weekday from (select trunc(to_date(?, 'yyyy-mm-dd'), 'MM') + rownum - 1 rq from dual  connect by rownum <= to_number(to_char(last_day(to_date(?,'yyyy-mm-dd')), 'dd')))) a");
		sql.append(" left join  xg_xlzx_zxspbb  c on a.rq = to_date(c.pbdate, 'yyyy-MM-dd') left join  xg_xlzx_yysqb d on a.rq = to_date(d.yyzxrq, 'yyyy-MM-dd')" );
		sql.append(" and d.status in ('1','2') ");
		sql.append(" and xh=? ");
		
		sql.append(" order by a.rq asc");
		return dao.getListNotOut(sql.toString(), inputValue);
		
	}
	
	
	/**
	 * 保存至咨询师排班表
	 * 
	 * @author wanghj
	 * @throws Exception
	 */
	public boolean saveZxspbInfo(ZxspbForm model)
			throws Exception {
	
		String[] input = {UniqID.getInstance().getUniqIDHash(),model.getPbdate(),model.getZgh(),model.getBz()};
		
		boolean flag = false;
		String sql = " insert into xg_xlzx_zxspbb (id,pbdate,zgh,createsj,bz) values(?,?,?,to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),?)";
		flag = dao.insert(sql, input);
		return flag;
	}
	
	/**
	 * 
	 * @描述：批量排班
	 * @作者：1004
	 * @日期：2013-8-13 上午10:39:43
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param values
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean saveBatchZxspbInfo(String[] pbdate,String zgh,String bz)
			throws Exception {
		String[] input = null;
		
		List<String[]> params = new ArrayList<String[]>();
		for(int i=0;i<pbdate.length;i++){
			input = new String[4];
			input[0]=UniqID.getInstance().getUniqIDHash();
			input[1] = pbdate[i];
			input[2]=zgh;
			input[3]=bz;
			params.add(input);
		}
		String sql = " insert into xg_xlzx_zxspbb (id,pbdate,zgh,createsj,bz) values(?,?,?,to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),?)";
		int[] count = dao.runBatch(sql, params);
		return 	dao.checkBatchResult(count);
	}
	
	
	/**
	 * 
	 * @描述：获取时间段内的星期M的日期
	 * @作者：1004
	 * @日期：2013-8-13 上午10:39:43
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param values
	 * @return
	 * @throws Exception
	 * int 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getDateByWeekBetweenDate(String startDate,String endDate) throws Exception{
		String[] inputValue={startDate,endDate,startDate,startDate};//week:1代表星期日,2为星期一...
		String[] outputValue={"tdate","weekday"};
		StringBuilder sql=new StringBuilder();
		sql.append(" SELECT to_char(tdate, 'yyyy-mm-dd') tdate,to_char(tdate, 'day') weekday");
		sql.append(" FROM (SELECT to_date(?, 'yyyy-mm-dd') + rownum - 1 tdate ");
		sql.append(" FROM dual CONNECT BY rownum <=to_date(?, 'yyyy-mm-dd') -to_date(?, 'yyyy-mm-dd') + 1)");
		sql.append("  WHERE to_char(tdate, 'D') =  to_char(to_date(?, 'yyyy-mm-dd'),'d')" );
		return dao.getList(sql.toString(), inputValue, outputValue);
		
	}
	
	
	/**
	 * 
	 * @描述:删除咨询师排班信息(按主键）
	 * @作者：1004
	 * @日期：2013-10-31 上午10:39:43
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param values
	 * @return
	 * @throws Exception
	 * int 返回类型 
	 * @throws
	 */
	public int delZxspbById(String id) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("delete from xg_xlzx_zxspbb where id=? ");
		return dao.runDelete(sql.toString(), new String[]{id});
	}
	
	/**
	 * 
	 * @描述:删除咨询师排班信息(按咨询师删除)
	 * @作者：1004
	 * @日期：2013-8-13 上午10:39:43
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param values
	 * @return
	 * @throws Exception
	 * int 返回类型 
	 * @throws
	 */
	public int delZxspbByZgh(String[] zgh) throws Exception {
		if (zgh == null || zgh.length == 0){
			throw new NullPointerException();
		}
		StringBuilder sql = new StringBuilder();
		sql.append("delete from xg_xlzx_zxspbb");
		sql.append(" where  ");
		
		for (int i = 0 , n = zgh.length ; i < n ; i++){
			sql.append("zgh=?");
			
			if (i != n-1){
				sql.append(" or ");
			}
		}
		return dao.runDelete(sql.toString(), zgh);
	}

	
	public boolean updateZxspbInfo(ZxspbForm model) throws Exception{
		String[] inputValue = {model.getPbdate(),model.getZgh(),model.getBz(),model.getDatazt(),model.getId()};
		
		HashMap<String,String>  zxspbList = this.getZxspbById(model.getId());
		if(model.getId()==null || model.getId().equals("") || zxspbList==null || zxspbList.size()==0){
			return false;	
		}
		String sql = " update xg_xlzx_zxspbb set pbdate=?,zgh=?,bz=?,datazt=? where id = ?";
		boolean flag = dao.runUpdate(sql, inputValue);
		return flag;
	}
	
	
	public List<HashMap<String, String>> getPageList(ZxspbForm t)
			throws Exception {
		return null;
	}

	protected void setTableInfo() {
		
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(ZxspbForm t, User user)
			throws Exception {
		return null;
	}
	
	/**
	 * 
	 * @描述:获取排班方式
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-3-20 上午10:37:37
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getPbfs(){
		StringBuilder sql = new StringBuilder();
		sql.append(" select pbfs from xg_xlzx_csszb");
		return dao.getOneRs(sql.toString(), new String[]{}, "pbfs");
	}
	
	/**
	 * 
	 * @描述: 获取按时间段排序老师基本信息
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-3-20 下午02:11:11
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param pbdate
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getZxspbList(String pbdate){
		StringBuilder sql = new StringBuilder();
		sql.append(" select distinct t.*, t1.pbid, t1.xqdm,t2.xm,t2.bmmc,t3.xqmc");
		sql.append(" from xg_xlzx_zxsxxb t");
		sql.append(" left join ( select * from xg_xlzx_zxspbsjb where pbid in (select id from xg_xlzx_zxspbb where pbdate = ?)) t1");
		sql.append(" on t.zgh = t1.zxs");
		sql.append(" left join view_fdyxx t2");
		sql.append(" on t.zgh = t2.zgh");
		sql.append(" left join dm_zju_xq t3");
		sql.append(" on t1.xqdm = t3.dm"); 
		sql.append(" where t.datazt='1' and t.status='1' ");
		sql.append(" order by t1.pbid,t.zgh ");
		return dao.getListNotOut(sql.toString(), new String[]{pbdate});
	}
	
	/**
	 * 
	 * @描述: 已排班时间段
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-3-20 下午02:33:43
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param pbdate
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getZxspbListSjd(String pbdate){
		StringBuilder sql = new StringBuilder();
		sql.append(" select t1.zxs zgh,t1.pbid,t1.sjddm,decode(nvl(p.num,0),0,'no'||t1.sjddm,'yes'||t1.sjddm) sfyy");
		sql.append(" from xg_xlzx_zxspbsjb t1");
		sql.append(" left join xg_xlzx_zxspbb x");
		sql.append(" on t1.pbid = x.id");
		sql.append(" left join (");
		sql.append(" select yyzxrq,sjddm,zgh,count(1) num from (");
		sql.append(" select t.ID,");
		sql.append(" t.XH,");
		sql.append(" decode(nvl(t.ZXRQ, '0'), '0', t.YYZXRQ, t.ZXRQ) yyzxrq,");
		sql.append(" decode(nvl(t.ZXRQ, '0'), '0', t.sjddm, t.sjddmzx) sjddm,");
		sql.append(" decode(nvl(t.ZXRQ,'0'), '0', t.ZGH, t.APZXS)zgh");
		sql.append(" from view_new_dc_xlzx_yysq t");
		sql.append(" where status in (1, 2)) group by yyzxrq,sjddm,zgh");
		sql.append(" ) p");
		sql.append(" on  x.pbdate = p.yyzxrq and t1.sjddm = p.sjddm and t1.zxs = p.zgh");
		sql.append(" where (x.pbdate = ?)");
		sql.append(" order by t1.zxs asc");
		return dao.getListNotOut(sql.toString(), new String[]{pbdate});
	}
	
	/**
	 * 
	 * @描述: 获取时间段List
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-3-20 下午05:21:50
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getSjdList(){
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from xg_xlzx_sjdb order by sjddm");
		return dao.getListNotOut(sql.toString(), new String[]{});
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述:删除咨询师排班信息
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-3-21 下午05:06:06
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param paramter
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean delPbxx(List<String[]> sjdDelList,List<String[]> pbDelList) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" delete from xg_xlzx_zxspbb where pbdate = ?");
		boolean rs = dao.runBatchNotCommit(sql.toString(), pbDelList);
		if(!rs){
			return rs;
		}
		sql = new StringBuilder();
		sql.append(" delete from xg_xlzx_zxspbsjb where zxs = ? and pbid = ?");
		rs = dao.runBatchNotCommit(sql.toString(), sjdDelList);
		return rs;
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述: 保存咨询师
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-3-22 上午11:52:21
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param sjdDelList
	 * @param pbDelList
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean saveZxsPb(List<String[]> sjdSaveList,List<String[]> pbSaveList) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" insert into xg_xlzx_zxspbb(id,pbdate,zgh,createsj,bz)values(?,?,?,?,?)");
		boolean rs = dao.runBatchNotCommit(sql.toString(), pbSaveList);
		if(!rs){
			return rs;
		}
		sql = new StringBuilder();
		sql.append("  insert into xg_xlzx_zxspbsjb(pbid,zxs,xqdm,sjddm)values(?,?,?,?)");
		rs = dao.runBatchNotCommit(sql.toString(), sjdSaveList);
		return rs;
	}
	
	/**
	 * 
	 * @描述: 增加排班时验证是否已有操作人先一步保存数据
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-3-22 下午03:52:58
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param pbdate
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean checkIsNotExistsInPbb(List<HashMap<String, String>> pbdateList,String id){
		StringBuilder sql = new StringBuilder();
		List<String> paraList = new ArrayList<String>();
		sql.append(" select count(1) cnt from xg_xlzx_zxspbb where pbdate in(");
	    for (int i = 0; i < pbdateList.size(); i++) {
			sql.append("?");
			if(i != pbdateList.size()-1){
				sql.append(",");
			}
			paraList.add(pbdateList.get(i).get("tdate"));
		}
		sql.append(")  and id != ? ");
		paraList.add(id);
		return "0".equals(dao.getOneRs(sql.toString(), paraList.toArray(new String[]{}), "cnt")) ? true :false;
	}
	
	/**
	 * 
	 * @描述: 学生社区预约查询(按时间段)
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-3-24 下午03:57:06
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param queryDate
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
    public List<HashMap<String, String>> getZxspbAsRlForXsSjd(String xh,String queryDate) throws Exception{
		
		String[] inputValue ={queryDate,queryDate,xh}; 
		StringBuilder sql=new StringBuilder();
		sql.append(" select to_char(a.rq, 'yyyy-MM-dd') rq,");
		sql.append(" a.weekday,");
		sql.append(" case");
		sql.append(" when c.id is not null and");
		sql.append(" (select instr(e.zgh, d.zgh, 1, 1) from dual) > 0 then");
		sql.append(" 'open'");
		sql.append(" when c.id is not null and e.zgh is not null and d.id is null then");
		sql.append(" 'arrangement'");
		sql.append(" else");
		sql.append(" 'close'");
		sql.append(" end color,");
		sql.append(" c.id pbid,");
		sql.append(" c.zgh,");
		sql.append(" c.bz,");
		sql.append(" c.pbtype,");
		sql.append(" c.pbdate,");
		sql.append(" d.xh,");
		sql.append(" d.zgh,");
		sql.append(" (case");
		sql.append(" when (select instr(e.zgh, d.zgh, 1, 1) from dual) > 0 then");
		sql.append(" 'Y'");
		sql.append(" else");
		sql.append(" ''");
		sql.append(" end) sfyy");
		sql.append(" from (select rq, to_char(rq, 'day') weekday");
		sql.append(" from (select trunc(to_date(?, 'yyyy-mm-dd'), 'MM') + rownum - 1 rq");
		sql.append(" from dual");
		sql.append(" connect by rownum <=");
		sql.append(" to_number(to_char(last_day(to_date(?, 'yyyy-mm-dd')),");
		sql.append(" 'dd')))) a");
		sql.append(" left join xg_xlzx_zxspbb c");
		sql.append(" on a.rq = to_date(c.pbdate, 'yyyy-MM-dd')");
		sql.append(" left join (select replace(wm_concat(zxs),';',',') zgh,pbid from xg_xlzx_zxspbsjb   group by pbid )e");
		sql.append(" on c.id = e.pbid");
		sql.append(" left join  (");
		sql.append(" select t.ID,");
		sql.append(" t.XH,");
		sql.append(" decode(nvl(t.ZXRQ, '0'), '0', t.YYZXRQ, t.ZXRQ) yyzxrq,");
		sql.append(" decode(nvl(t.ZXRQ, '0'), '0', t.sjddm, t.sjddmzx) sjddm,");
		sql.append(" decode(nvl(t.ZXRQ,'0'), '0', t.ZGH, t.APZXS)zgh");
		sql.append(" from view_new_dc_xlzx_yysq t");
		sql.append(" where status in (1, 2)");
		sql.append(" )d");
		sql.append(" on a.rq = to_date(d.yyzxrq, 'yyyy-MM-dd')");
		sql.append(" and xh = ?");
		sql.append(" order by a.rq asc");
		return dao.getListNotOut(sql.toString(), inputValue);
		
	}
    
    /**
     * 
     * @描述: 时间段排班教师基本信息
     * @作者：喻鑫源[工号：1206]
     * @日期：2017-3-27 上午10:07:49
     * @修改记录: 修改者名字-修改日期-修改内容
     * @return
     * List<HashMap<String,String>> 返回类型 
     * @throws
     */
    public List<HashMap<String, String>> getZxsjbxxForSjdPb(String pbdate){
    	StringBuilder sql = new StringBuilder();
    	sql.append(" select distinct t2.* from xg_xlzx_zxspbsjb t");
    	sql.append(" left join xg_xlzx_zxspbb t1");
    	sql.append(" on t.pbid = t1.id");
    	sql.append(" left join xg_view_xlzx_zxsxx t2");
    	sql.append(" on t.zxs = t2.zgh");
    	sql.append(" where t1.pbdate = ?");
    	sql.append(" ");
    	return dao.getListNotOut(sql.toString(), new String[]{pbdate});
    }
    
    
    /**
	 * 根据日期查询排班情况[时间段预约]
	 * @param model
	 * @return
	 */
	public HashMap<String,String> getPbInfoByDateForsjd(String date){
		
		StringBuilder sql=new StringBuilder();
		sql.append(" select t.id,");
		sql.append(" t.pbtype,");
		sql.append(" t.pbdate,");
		sql.append(" t1.zgh,");
		sql.append(" to_char(to_date(t.pbdate, 'yyyy-MM-dd'), 'day') weekday,");
		sql.append(" t.createsj,");
		sql.append(" t.bz");
		sql.append(" from xg_xlzx_zxspbb t");
		sql.append(" join (select replace(wm_concat(zxs),';',',')zgh,pbid from xg_xlzx_zxspbsjb group by pbid) t1");
		sql.append(" on t.id = t1.pbid");
		sql.append(" where t.datazt = '1'  and t.pbdate = ?");
		return dao.getMapNotOut(sql.toString(), new String[]{date});
	}
	
	/**
	 * 
	 * @描述:获取校区
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-3-31 下午07:06:10
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getXqmc(String date,String zgh){
		StringBuilder sql = new StringBuilder();
		sql.append(" select distinct t2.xqmc xqmc");
		sql.append(" from xg_xlzx_zxspbsjb t");
		sql.append(" left join xg_xlzx_zxspbb t1");
		sql.append(" on t.pbid = t1.id");
		sql.append(" left join dm_zju_xq t2");
		sql.append(" on t.xqdm = t2.dm");
		sql.append(" where t.zxs = ?");
		sql.append("  and t1.pbdate = ?");
		return dao.getOneRs(sql.toString(), new String[]{zgh,date}, "xqmc");
	}
}
