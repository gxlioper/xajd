/**
 * @部门:学工产品事业部
 * @日期：2014-2-13 下午05:25:11 
 */  
package com.zfsoft.xgxt.dagl.qdmb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 档案管理模块
 * @类功能描述: (这里用一句话描述这个类的作用) 
 * @作者：  wanghj [工号：1004]
 * @时间： 2014-2-13 下午05:25:11 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class DaqdmbDao extends SuperDAOImpl<DaqdmbForm> {

	protected void setTableInfo() {
		super.setTableName("xg_xsxx_dagl_daqdmbb");
		super.setKey("daqdmb_id");
		super.setClass(DaqdmbForm.class);
	}

	public List<HashMap<String, String>> getPageList(DaqdmbForm t)
			throws Exception {
		
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select * from (");
		sql.append(" select t.daqdmb_id,t.daqdmb_mc,t.qyzt,(case qyzt when '1' then '启用' when '0' then '停用' else '' end) qyztmc,");
		sql.append("(select count(*) from XG_XSXX_DAGL_DAMBCLBDB where daqdmb_id=t.daqdmb_id) ybdcls," );
		sql.append("(select count(*) from XG_XSXX_DAGL_DAXXB where daqdmb_id=t.daqdmb_id) ybdxss " );
		sql.append(" from xg_xsxx_dagl_daqdmbb t) where 1=1 ");
		sql.append(searchTj);
		
		return super.getPageList(t, sql.toString(), inputV);
	}

	public List<HashMap<String, String>> getPageList(DaqdmbForm t, User user) throws Exception {
		return null;
	}
	
	public HashMap<String, String> getDaqdmbById(String daqdmb_id) throws Exception {
		
		String sql = " select t.daqdmb_id,t.daqdmb_mc,t.qyzt,(case qyzt when '1' then '启用'  when '0' then '停用' else '' end) qyztmc from xg_xsxx_dagl_daqdmbb t where daqdmb_id = ?";
		
		return dao.getMapNotOut(sql, new String[]{daqdmb_id});
	}
	
	public HashMap<String, String> getDaqdmbByName(String daqdmb_mc) throws Exception {
		
		String sql = " select * from xg_xsxx_dagl_daqdmbb where daqdmb_mc = ?";
		
		return dao.getMapNotOut(sql, new String[]{daqdmb_mc});
		
	}
	
	public List<HashMap<String, String>> getDaqdmbList() throws Exception{
		String sql = " select daqdmb_id,daqdmb_mc from xg_xsxx_dagl_daqdmbb where qyzt='1' ";
		return dao.getListNotOut(sql, new String[]{});
	} 
	
	/**
	 * 
	 * @描述:查询清单材料详细
	 * @日期：2014-4-25 下午04:57:44
	 * @param daqdmb_id
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getDaqdclListByMbid(String daqdmb_id) throws Exception {

		StringBuilder sql = new StringBuilder();
		sql.append(" select t.daqdcl_id, t.daqdcl_mc, ");
		sql.append("   (case when (select count(*) ");
		sql.append("               from XG_XSXX_DAGL_DAMBCLBDB ");
		sql.append("               where daqdcl_id = t.daqdcl_id ");
		sql.append("                 and daqdmb_id = ? ) > 0 then ");
		sql.append("    '1'  else '0' end) zt, ");
		sql.append("   (select sx ");
		sql.append("      from XG_XSXX_DAGL_DAMBCLBDB ");
		sql.append("      where daqdcl_id = t.daqdcl_id ");
		sql.append("      and daqdmb_id = ? ) sx ");
		sql.append("   from XG_XSXX_DAGL_DAQDCLB t ");
		sql.append("   order by zt, sx ");

		return dao.getListNotOut(sql.toString(), new String[]{daqdmb_id, daqdmb_id});
	}
	
	public boolean updateQdmbQyzt(String qyzt,String[] daqdmb_id) throws Exception{
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("update xg_xsxx_dagl_daqdmbb set qyzt="+qyzt+" where ");
		for(int i=0;i<daqdmb_id.length;i++){
			if(i==daqdmb_id.length-1){
				sql.append(" daqdmb_id=? ");
			}else{
				sql.append(" daqdmb_id=? or ");
			}
		}
		return dao.runUpdate(sql.toString(), daqdmb_id);
	}
	public boolean addMbclInfo(String daqdmb_id, String[] daqdcl) throws Exception{
		
		String sql = " insert into XG_XSXX_DAGL_DAMBCLBDB(daqdmb_id,daqdcl_id,sx) values(?,?,?) ";
		List<String[]> params = new ArrayList<String[]>();
		for(int i=0;i<daqdcl.length;i++){
			String[] values = {daqdmb_id,daqdcl[i],String.valueOf(i+1)};
			params.add(values);
		}
		int[] count = dao.runBatch(sql, params);
		if(count!=null && count.length>0){
			for(int j=0;j<count.length;j++){
				if(count[j]!=-2){
					return false;
				}
			}
		}
		return true;
	}
	
	public int delMbclInfo(String daqdmb_id) throws Exception{
		String sql = " delete XG_XSXX_DAGL_DAMBCLBDB where daqdmb_id = ?";
		return dao.runDelete(sql, new String[]{daqdmb_id});
	}
	
	public int delBatchMbcl(String[] daqdmb_id) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" delete XG_XSXX_DAGL_DAMBCLBDB where ");
		for(int i=0;i<daqdmb_id.length;i++){
			if(i==daqdmb_id.length-1){
				sql.append(" daqdmb_id=? ");
			}else{
				sql.append(" daqdmb_id=? or ");
			}
		}
		return dao.runDelete(sql.toString(), daqdmb_id);
	}
	
	/*public boolean updateDaqdmbInfo(DaqdmbForm model) throws Exception{
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("update XG_XSXX_DAGL_DAqdmbB set ");
		sql.append(model.getDaqdmb_mc());
		sql.append("=? where daqdmb_id=? ");
		
		return dao.runUpdate(sql.toString(), new String[]{model.getDaqdmb_mc(),model.getDaqdmb_id()});
	}
	
	public boolean saveDaqdmbInfo(DaqdmbForm model) throws Exception{
		
		String sql = "insert into XG_XSXX_DAGL_DAqdmbB(daqdmb_id,daqdmb_mc) values(sys_guid(),?)";
		
		return dao.runUpdate(sql, new String[]{model.getDaqdmb_mc()});
	}
	
	public boolean deleteDaqdmbInfo(DaqdmbForm model) throws Exception{
		
		String sql = "delete XG_XSXX_DAGL_DAqdmbB where daqdmb_id=?)";
		
		return dao.runUpdate(sql, new String[]{model.getDaqdmb_id()});
	}*/
}
	