/**
 * @部门:学工产品事业部
 * @日期：2013-9-10 上午11:09:52 
 */  
package com.zfsoft.xgxt.xlzx.tsxsgl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import xgxt.action.Base;
import xgxt.comm.search.SearchModel;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.base.util.UniqID;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 心理咨询管理模块
 * @类功能描述: 特殊学生维护模块(这里用一句话描述这个类的作用) 
 * @作者： wanghj [工号：1004]
 * @时间： 2013-9-10 上午11:10:07 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class TsxsDao extends SuperDAOImpl<TsxsForm> {
	protected Log logger = LogFactory.getLog(TsxsDao.class);



	/**西安科技大学【一些个性化查询字段】，加在通用方法上，通用方法仍然可用 
	 * 特殊学生维护模块-西安科技大学  在预警程度中可以实现多项查询。
	 */
	public List<HashMap<String, String>> getPageList(TsxsForm model, User user)
			throws Exception {

		
		
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");	
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select t.*,rownum r  ");

		if("10704".equals(Base.xxdm)){
			sql.append(" ,c.dbfdy ");
		}

		sql.append("  from  VIEW_XLZX_TSXSXX  t ");

		if("10704".equals(Base.xxdm)){
			sql.append(" LEFT JOIN (SELECT bjdm,WM_CONCAT(t2.XM) dbfdy FROM FDYBJB t1 LEFT JOIN FDYXXB t2 ON t1.ZGH = t2.ZGH  GROUP BY BJDM) c ON t.BJDM = c.BJDM ");
		}

		sql.append(" where 1=1 ");

		// 关注状态不为空,西安科技大学，不需要关注状态字段
		if(!"10704".equals(Base.xxdm)){
			if(StringUtils.isNotNull(model.getGzzt())){
				sql.append(" and gzzt = '" + model.getGzzt() + "' ");	
			}
		}
		//西安科技大学过滤条件拼接
		if("10704".equals(Base.xxdm)){
			
			//生成高级查询相关条件、条件值 
			StringBuilder kntj = new StringBuilder();
			//西安科技大学高级查询的条件筛选（在预警程度中实现多项选择筛选方式）
			SearchModel sm = model.getSearchModel();
			if(null != sm.getSearch_tj_knlx()){
				String[] knVaule = sm.getSearch_tj_knlx();
				kntj.append("and 1=1 and (");
				for(int i = 0;i<knVaule.length;i++){
						kntj.append("knlxdm like '%"+knVaule[i]+"%'");
						if(i != knVaule.length - 1){
							kntj.append(" or ");
						}
				}
				kntj.append(" ) ");
				sm.setSearch_tj_knlx(null);
				
			}
			sql.append(kntj);
		}
		sql.append(searchTjByUser);
		
		sql.append(searchTj);
		
		sql.append(" order by t.xgsj desc ");
		
		return getPageList(model, sql.toString(), inputV);
		
	}
	/**
	 * 根据Id查询特殊学生信息
	 * @param model
	 * @return
	 */
	public HashMap<String,String> getTsxsDetailByXh(String xh){
		
		StringBuilder sql=new StringBuilder();
		
		sql.append(" select a.*  ");

		//辅导员姓名  西安科技大学
		if("10704".equals(Base.xxdm)){
			sql.append(",fdy.fdyxm fdyxmnew ");
		}

		sql.append(" from VIEW_XLZX_TSXSXX a ");

		//辅导员姓名  西安科技大学
		if("10704".equals(Base.xxdm)){
			sql.append(" left join (select t.bjdm,replace(wm_concat(t1.xm),';',',')  fdyxm from fdybjb t");
			sql.append(" left join fdyxxb t1 on t.zgh = t1.zgh group by t.bjdm) fdy on a.bjdm = fdy.bjdm");
		}
		
		sql.append(" where a.xh = ? ");
		return dao.getMapNotOut(sql.toString(), new String[]{xh});
	}
	
	/**
	 * 根据学号、周次查询特殊学生信息（湖南城市学院）
	 * @param model
	 * @return
	 */
	public HashMap<String,String> getTsxsDetailByXhZc(TsxsForm model){
		StringBuilder sql=new StringBuilder();
		sql.append(" select a.*  ");
		sql.append(" from VIEW_XLZX_TSXSXX a ");
		sql.append(" where a.xh = ? ");
		sql.append(" and a.zc = ? ");
		return dao.getMapNotOut(sql.toString(), new String[]{model.getXh(),model.getZc()});
	}
	
	/**
	 * 根据Id查询特殊学生信息
	 * @param model
	 * @return
	 */
	public HashMap<String,String> getTsxsInfoById(String id){
		
		StringBuilder sql=new StringBuilder();
		
		//西安科技大学查询方式个性化
		if("10704".equals(Base.xxdm)){
			sql.append(" select a.id,a.xh,a.knlxdm,a.gzzt,");
			sql.append(" (case a.gzzt when '1' then '关注' when '2' then '取消关注' else '' end) gzztmc,a.qksm,a.bz,a.cjsj,a.cjr,a.xgsj,a.xgr,a.sjzt,a.clcs,a.lrsj,a.jbqkms,a.gzsj,a.gznr,a.fjid");
			sql.append(" from xg_xlzx_tsxsxxb a where a.sjzt = '1' and a.id = ?");
		}else if("11527".equals(Base.xxdm)){
			sql.append(" select a.id,a.xh,a.knlxdm,a.gzzt,");
			sql.append(" (case a.gzzt  when '1' then '学校重点关注' when '2' then '学院重点关注' " +
					"when '3' then '学院预警对象'  when '0' then  '取消关注' else '' end) gzztmc," +
					"a.qksm,a.bz,a.cjsj,a.cjr,a.xgsj,a.xgr,a.sjzt,a.zc,a.yyms");
			sql.append(" from xg_xlzx_tsxsxxb a where a.sjzt = '1' and a.id = ?");
		}else{
			sql.append(" select a.id,a.xh,a.knlxdm,a.gzzt,");
			sql.append(" (case a.gzzt when '1' then '关注' when '2' then '取消关注' else '' end) gzztmc,a.qksm,a.bz,a.cjsj,a.cjr,a.xgsj,a.xgr,a.sjzt");
			sql.append(" from xg_xlzx_tsxsxxb a where a.sjzt = '1' and a.id = ?");
			
		}
		
		return dao.getMapNotOut(sql.toString(), new String[]{id});
	}
	
	/**
	 * 保存至特殊学生信息表
	 * 
	 * @author wanghj
	 * @throws Exception
	 */
	public boolean saveTsxsInfo(TsxsForm model)
			throws Exception {
		
		//西安科技大学增加学生信息
		if("10704".equals(Base.xxdm)){
			String[] input = {UniqID.getInstance().getUniqIDHash(),model.getXh(),model.getKnlxdm(),model.getQksm(),model.getBz(),model.getGzzt(),model.getClcs(),model.getLrsj(),model.getJbqkms(),model.getGzsj(),model.getGznr(),model.getFjid()};		
			
			boolean flag = false;
			String sql = " insert into xg_xlzx_tsxsxxb (id,xh,knlxdm,qksm,bz,cjsj,xgsj,gzzt,clcs,lrsj,jbqkms,gzsj,gznr,fjid) values(?,?,?,?,?,to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),?,?,?,?,?,?,?)";
			flag = dao.insert(sql, input);
			return flag;
		}else if("11527".equals(Base.xxdm)){
			String[] input = {UniqID.getInstance().getUniqIDHash(),model.getXh(),model.getKnlxdm(),model.getQksm(),model.getBz(),model.getGzzt(),model.getFjid(),model.getZc(),model.getYyms()};		
			boolean flag = false;
			String sql = " insert into xg_xlzx_tsxsxxb (id,xh,knlxdm,qksm,bz,cjsj,xgsj,gzzt,fjid,zc,yyms) values(?,?,?,?,?,to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),?,?,?,?)";
			flag = dao.insert(sql, input);
			return flag;
		}else{
			String[] input = {UniqID.getInstance().getUniqIDHash(),model.getXh(),model.getKnlxdm(),model.getQksm(),model.getBz(),model.getGzzt()};
		
			boolean flag = false;
			String sql = " insert into xg_xlzx_tsxsxxb (id,xh,knlxdm,qksm,bz,cjsj,xgsj,gzzt) values(?,?,?,?,?,to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),?)";
			flag = dao.insert(sql, input);
			return flag;
		}
	}
	
	
	/**
	 * 
	 * @描述:删除特殊学生信息
	 * @作者：1004
	 * @日期：2013-9-10 上午10:39:43
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param values
	 * @return
	 * @throws Exception
	 * int 返回类型 
	 * @throws
	 */
	public int delTsxsById(String[] id) throws Exception {
		if (id == null || id.length == 0){
			logger.error("删除特殊学生操作不能进行!");
			throw new NullPointerException();
		}
		StringBuilder sql = new StringBuilder();
		
		sql.append("delete from xg_xlzx_tsxsxxb");
		sql.append(" where  ");
		
		for (int i = 0 , n = id.length ; i < n ; i++){
			sql.append("id=?");
			
			if (i != n-1){
				sql.append(" or ");
			}
		}
		
		return dao.runDelete(sql.toString(), id);
	}
	

	/**
	 * 
	 * @描述:批量修改特殊学生关注状态
	 * @作者：1004
	 * @日期：2013-9-10 上午10:39:43
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param values
	 * @return
	 * @throws Exception
	 * int 返回类型 
	 * @throws
	 */
	public boolean updateBatchGzStatus(String[] id,String gzzt) throws Exception{
		
		StringBuffer sql = new StringBuffer();
		sql.append(" update xg_xlzx_tsxsxxb set ");
		
		if(!StringUtil.isNull(gzzt)){
			sql.append(" xgsj = to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'), gzzt = '" +gzzt+"' where ");
		}
		for (int i = 0 , n = id.length ; i < n ; i++){
			sql.append("id ='"+id[i]+"'");
			
			if (i != n-1){
				sql.append(" or ");
			}
		}
		boolean flag = dao.runUpdate(sql.toString(), new String []{});
		return  flag;
		
	}
	
	/**
	 * 
	 * @描述:修改特殊学生信息
	 * @作者：1004
	 * @日期：2013-9-10 上午10:39:43
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param values
	 * @return
	 * @throws Exception
	 * int 返回类型 
	 * @throws
	 */
	public boolean updateTsxsInfo(TsxsForm model) throws Exception{
		
		//西安科技大学【修改信息个性化】
		if("10704".equals(Base.xxdm)){
			HashMap<String,String>  tsxsList = this.getTsxsInfoById(model.getId());
			if(model.getId()==null || model.getId().equals("") || tsxsList==null || tsxsList.size()==0){
				return false;	
			}
			String[] inputValue = new String []{model.getKnlxdm(),model.getQksm(),model.getSjzt(),model.getGzzt(),model.getClcs(),model.getLrsj(),model.getJbqkms(),model.getGzsj(),model.getGznr(),model.getBz(),model.getFjid(), model.getId()};
			StringBuffer sql = new StringBuffer();
			sql.append(" update xg_xlzx_tsxsxxb set knlxdm=?,qksm=?,sjzt=?,gzzt=?,clcs=?,lrsj=?,jbqkms=?,gzsj=?,gznr=?,bz=?,fjid=?  where id = ? ");
			
			boolean flag = dao.runUpdate(sql.toString(), inputValue);
			return flag;
		}else{
			HashMap<String,String>  tsxsList = this.getTsxsInfoById(model.getId());
			if(model.getId()==null || model.getId().equals("") || tsxsList==null || tsxsList.size()==0){
				return false;	
			}
			boolean flag = false;
			StringBuffer sql = new StringBuffer();
			if("11527".equals(Base.xxdm)){
				String[] inputValue = new String []{model.getKnlxdm(),model.getQksm(),model.getSjzt(),model.getGzzt(),model.getYyms(),model.getFjid(), model.getId()};
				sql.append(" update xg_xlzx_tsxsxxb set knlxdm=?,qksm=?,sjzt=?,xgsj = to_char(sysdate,'yyyy-mm-dd hh24:mi:ss') ,gzzt = ?,yyms=?,fjid=? where id = ? ");
				flag = dao.runUpdate(sql.toString(), inputValue);
			}else{
				String[] inputValue = new String []{model.getKnlxdm(),model.getQksm(),model.getSjzt(),model.getGzzt(),model.getYyms(), model.getId()};
				sql.append(" update xg_xlzx_tsxsxxb set knlxdm=?,qksm=?,sjzt=?,xgsj = to_char(sysdate,'yyyy-mm-dd hh24:mi:ss') ,gzzt = ?,yyms=? where id = ? ");
				flag = dao.runUpdate(sql.toString(), inputValue);
			}
			
			return flag;
		}
	}
	
	public List<HashMap<String, String>> getKnlxList(){
		
		String sql = "select knlxdm,knlxmc from tsxs_knlxb";
		
		return dao.getListNotOut(sql, new String[]{});
		
	}
	
	public List<HashMap<String, String>> getKnlxList(String[] knlxdm){
		StringBuffer sql = new StringBuffer();
		sql.append(" select knlxdm,knlxmc from tsxs_knlxb where ");
		
		for (int i = 0 , n = knlxdm.length ; i < n ; i++){
			sql.append("  knlxdm =? ");
			
			if (i != n-1){
				sql.append(" or ");
			}
		}
		return dao.getListNotOut(sql.toString(), knlxdm);
		
	}
	
	protected void setTableInfo() {
		
	}
	

	public List<HashMap<String, String>> getPageList(TsxsForm t)
			throws Exception {
		return null;
	}
	/** 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：lgx[工号：1553]
	 * @日期：2018-4-8 下午05:00:16
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param myForm
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws 
	 */
	public HashMap<String, String> getZcTsxsInfo(TsxsForm model) {
		StringBuilder sql=new StringBuilder();
		sql.append(" select * from ( ");
		sql.append(" select a.*  ");
		sql.append(" from VIEW_XLZX_TSXSXX a ");
		sql.append(" where a.xh = ? ");
		sql.append(" order by a.xgsj desc  )");
		sql.append(" where rownum=1 ");
		return dao.getMapNotOut(sql.toString(), new String[]{model.getXh()});
	}
}
