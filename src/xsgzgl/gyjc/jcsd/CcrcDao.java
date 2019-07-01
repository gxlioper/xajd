/**
 * @部门:学工产品事业部
 * @日期：2018年5月22日 下午5:55:37 
 */  
package xsgzgl.gyjc.jcsd;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

import common.newp.ArrayUtil;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张昌路[工号:982]
 * @时间： 2018年5月22日 下午5:55:37 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class CcrcDao extends SuperDAOImpl<CcrcForm>{

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		// TODO 自动生成方法存根
		this.setClass(CcrcForm.class);
		this.setKey("ccid");
		this.setTableName("xg_gygl_wsjc_ccrcszb");
		
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(CcrcForm t) throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(CcrcForm t, User user) throws Exception {

		//生成高级查询相关条件、条件值
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(t.getSearchModel());

		StringBuilder sql = new StringBuilder("select * from (select t.ccid, t.jcrq,t.xn,t.xqdm xq,count(f.qsh) as jcqs,");
		sql.append("COUNT(distinct  xydm) as jcxy,(count(f.ccid)-count(b.ccid)) as wtjs ,");
		sql.append(" count(b.ccid) as ytjs  from xg_gygl_wsjc_ccrcszb t   ");
		sql.append(" left join xg_gygl_wsjc_ccryssfpb f on t.ccid=f.ccid   ");
		sql.append("  left join xg_gygl_new_qsxxb q on f.ldh=q.lddm and f.qsh = q.qsh ");
		sql.append(" left join xg_gygl_wsjc_wsccjgb  b on f.ccid=b.ccid and f.ldh = b.lddm and f.qsh = b.qsh  ");
		sql.append(" group by t.ccid, t.jcrq,t.xn,t.xqdm) t where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);

		return getPageList(t, sql.toString(), inputV);
	}
	
	
	
	public List<HashMap<String, String>> getList(CcrcForm t, User user) throws Exception {

		//生成高级查询相关条件、条件值
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUserOnlyXxXy(user, "t", "xydm");
		String[] inputV = SearchService.getTjInput(t.getSearchModel());

		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder("select * from (select q.lddm,ldmc,q.xydm,bmmc,ch,qsh,mod(substr(qsh, -1),2) as dsh  ");
		  sql.append(" from xg_gygl_new_qsxxb q ");
		  sql.append("left join xg_gygl_new_ldxxb l on q.lddm=l.lddm ");
		  sql.append("left join ZXBZ_XXBMDM v on q.xydm=v.bmdm  ");
		  sql.append("where not exists (select ldh,qsh from xg_gygl_wsjc_ccryssfpb b  ");
		  if(StringUtils.isNotNull(t.getCcid())){
				sql.append(" where ccid = ? ");
				params.add(t.getCcid());
			}
		  sql.append("and q.lddm=b.ldh and q.qsh=b.qsh)) t where 1=1 ");

		sql.append(searchTjByUser);
		sql.append(searchTj);

		for(String s:inputV){
			params.add(s);
		}

		return getPageList(t, sql.toString(), params.toArray(new String[]{}));
	}

	/** 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：张昌路[工号：982]
	 * @日期：2018年5月23日 下午2:28:44
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param jcrq
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean checkIfRqIntersection(String jcrq) {
		StringBuilder sql = new StringBuilder();
		List<String> paraList = new ArrayList<String>();
		sql.append(" select count(1) cnt from xg_gygl_wsjc_ccrcszb where 1= 1 ");
		if(StringUtils.isNotNull(jcrq)){
			sql.append(" and jcrq = ? ");
			paraList.add(jcrq);
		}
		String cnt = dao.getOneRs(sql.toString(), paraList.toArray(new String[]{}), "cnt");
		return "0".equals(cnt) ? true :false;
	}

	/**
	 * @throws Exception  
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：张昌路[工号：982]
	 * @日期：2018年5月24日 上午11:13:42
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getRyfpList(CcrcForm t, User user) throws Exception {
		StringBuilder sql = new StringBuilder();
		List<String> paraList = new ArrayList<String>();

		//生成高级查询相关条件、条件值
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUserOnlyXxXy(user, "t", "bmdm");
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		sql.append(" select *");
		sql.append(" from view_fdyxx t where 1=1 ");

		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}

	/** 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：张昌路[工号：982]
	 * @日期：2018年5月24日 下午3:12:48
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param paraList
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean saveFpry(List<String[]> paraList) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" insert into xg_gygl_wsjc_ccryssfpb(ccid,ldh,qsh,zgh)values(?,?,?,?)");
		return dao.runBatchNotCommit(sql.toString(), paraList);
	}

	/**
	 * @throws Exception  
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：张昌路[工号：982]
	 * @日期：2018年5月24日 下午5:23:03
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> gettodayCcList(CcrcForm model, User user) throws Exception {
		List<String> params = new ArrayList<String>();		
		StringBuilder sql = new StringBuilder("select  row_number() over(ORDER BY t1.zgh) as xh ,t1.zgh,xm,t1.ccid, count(qsh) as qsnum   ");
                                  sql.append(" from ");
                                  sql.append("(select t.zgh,t.qsh,xm,ccid from xg_gygl_wsjc_ccryssfpb t   ");
                                  sql.append("left join view_fdyxx a on t.zgh =a.zgh");
                                  if(StringUtils.isNotNull(model.getCcid())){
                          			sql.append(" where t.ccid = ? ");
                          			params.add(model.getCcid());
                          		}
                                  sql.append(")t1  ");
                                  sql.append(" group by t1.zgh,xm,t1.ccid");
		return getPageList(model, sql.toString(), params.toArray(new String[]{}));
	}

	/**
	 * @throws Exception 
	 * @throws SQLException  
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：张昌路[工号：982]
	 * @日期：2018年5月25日 下午2:21:54
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean updateRyfp(CcrcForm model) throws Exception {
		// TODO 自动生成方法存根

		StringBuilder sql = new StringBuilder("update xg_gygl_wsjc_ccryssfpb  ");
                                  sql.append("  set zgh = ? ");
                                  sql.append("where ccid= ?   ");
                                  sql.append("and zgh =? ");
                                  return dao.runUpdate(sql.toString(), new String[]{model.getZgh(),model.getCcid(),model.getXgzgh()});
	}

	/**
	 * @throws Exception  
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：张昌路[工号：982]
	 * @日期：2018年5月25日 下午4:35:07
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getqsfpList(CcrcForm model, User user) throws Exception {
		// TODO 自动生成方法存根
		List<String> params = new ArrayList<String>();		
		StringBuilder sql = new StringBuilder("select q.lddm,ldmc,bmmc,ch,qsh  ");
                                  sql.append(" from xg_gygl_new_qsxxb q ");
                                  sql.append("left join xg_gygl_new_ldxxb l on q.lddm=l.lddm ");
                                  sql.append("left join ZXBZ_XXBMDM v on q.xydm=v.bmdm  ");
                                  sql.append("where  exists (select * from xg_gygl_wsjc_ccryssfpb a  ");
                                  if(StringUtils.isNotNull(model.getCcid())){
                            			sql.append(" where a.ccid= ? ");
                            			params.add(model.getCcid());
                            		}
                                  if(StringUtils.isNotNull(model.getZgh())){
                          			sql.append(" and a.zgh= ? ");
                          			params.add(model.getZgh());
                          		}
                                  
								  sql.append("and q.lddm=a.ldh and q.qsh=a.qsh)");
		return getPageList(model, sql.toString(), params.toArray(new String[]{}));
	}

	/**
	 * @throws Exception  
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：张昌路[工号：982]
	 * @日期：2018年5月25日 下午7:15:21
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param userStatus
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean cancelFp(CcrcForm model, String userStatus) throws Exception {
		
			StringBuilder sql = new StringBuilder();
			List<String[]> paraList = new ArrayList<String[]>();
			String[] lddm = model.getLds();
			String[] qsh = model.getQss();
			String ccid = model.getCcid();
			sql.append(" delete from xg_gygl_wsjc_ccryssfpb where ccid = ? and ldh = ? and qsh = ? ");
		
			for (int i = 0; i < lddm.length; i++) {
			 
			  paraList.add(new String[]{ccid,lddm[i],qsh[i]});
			  
			}
			return dao.runBatchNotCommit(sql.toString(), paraList);
		
	}

	/** 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：张昌路[工号：982]
	 * @日期：2018年5月28日 上午9:49:30
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param rcid
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean checkIfExistTj(String rcid) {
		StringBuilder sql = new StringBuilder();
		sql.append("  select count(1) cnt from xg_gygl_wsjc_ccryssfpb where ccid = ? ");
		String cnt = dao.getOneRs(sql.toString(),new String[]{rcid}, "cnt");
		return "0".equals(cnt) ? true : false;
	}


	/**
	 * @throws Exception  
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：张昌路[工号：982]
	 * @日期：2018年5月28日 上午10:23:10
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param strings
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean delCcrc(String[] ids) throws Exception {
		StringBuilder sql = new StringBuilder();
		List<String> paraList = new ArrayList<String>();
		sql.append(" delete from xg_gygl_wsjc_ccrcszb where ccid in(");
		for (int i = 0; i < ids.length; i++) {
			sql.append("?");
			if(i != ids.length-1){
				sql.append(",");
			}
			paraList.add(ids[i]);
		}
		sql.append(" )");
		return dao.runUpdate(sql.toString(),paraList.toArray(new String[]{}));
	}



}
