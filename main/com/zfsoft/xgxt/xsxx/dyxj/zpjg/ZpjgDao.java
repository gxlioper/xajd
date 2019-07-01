/**
 * @部门:学工产品事业部
 * @日期：2015-6-19 下午02:08:31 
 */  
package com.zfsoft.xgxt.xsxx.dyxj.zpjg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @类功能描述: 自评结果
 * @作者： 屈朋辉 [工号:445]
 * @时间： 2015-6-19 下午02:08:31 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ZpjgDao extends SuperDAOImpl<ZpjgModel> {

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		super.setClass(ZpjgModel.class);
		super.setTableName("xg_xsxx_dyxj_dypyjg");
		super.setKey("id");
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(ZpjgModel t)
			throws Exception {
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(ZpjgModel t, User user)
			throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");		
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select * from (");
		sql.append("select t1.*,t2.xm,t2.xydm,t2.xymc,t2.zydm,t5.xqmc,");
		sql.append("t2.zymc,t2.bjdm,t2.bjmc,t2.nj,t3.djmc ");
		sql.append("from xg_xsxx_dyxj_dypyjg t1 left join view_xsjbxx t2 on t1.xh = t2.xh ");
		sql.append("left join xg_xsxx_dyxj_djdmb t3 on t1.djdm = t3.djdm ");
		sql.append("left join xqdzb t5 on t1.xq=t5.xqdm ");
		sql.append(") t where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		
		return getPageList(t, sql.toString(), inputV);
	}

	
	public ZpjgModel getModel(ZpjgModel t) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("select t1.*,t3.djmc,t5.xqmc from xg_xsxx_dyxj_dypyjg t1 ");
		sql.append("left join xg_xsxx_dyxj_djdmb t3 on t1.djdm = t3.djdm ");
		sql.append("left join xqdzb t5 on t1.xq=t5.xqdm where t1.id=?");
		return super.getModel(sql.toString(), new String[]{t.getId()});
	}
	
	/**查询是否已申请**/
	public String getCount(ZpjgModel model){
		String sql = "select count(1) c from xg_xsxx_dyxj_dypyjg where xh=? and xn=? and xq=?";
		return dao.getOneRs(sql, new String[]{model.getXh(),model.getXn(),model.getXq()}, "c");
	}
	
	 //德育自评报表导出信息获取
    public HashMap<String, String> getDyzpbbxx(String id,String flag){
    	StringBuilder sql = new StringBuilder();
    	sql.append(" select t1.*,");
    	sql.append(" t2.djmc,");
    	sql.append(" t3.*");
    	sql.append(" from ");
    	if(flag.equalsIgnoreCase("jg")){
    		sql.append(" xg_xsxx_dyxj_dypyjg t1");
    	}else{
    		sql.append(" xg_xsxx_dyxj_dypysqb t1");
    	}
    	sql.append(" left join xg_xsxx_dyxj_djdmb t2");
    	sql.append(" on t1.djdm = t2.djdm");
    	sql.append(" left join VIEW_XSJBXX_DYZP t3");
    	sql.append(" on t1.xh = t3.xh");
    	sql.append(" where t1.id = ?");
    	return dao.getMapNotOut(sql.toString(), new String[]{id});
    }
    
    //德育自评等级查询
    public String getXsZpdj(String djdm){
    	StringBuilder sql = new StringBuilder();
    	sql.append(" select  t.djmc  from xg_xsxx_dyxj_djdmb t where t.djdm = ?");
    	return dao.getOneRs(sql.toString(), new String[]{djdm}, "djmc");
    }
    
    //德育自评班级汇总表
    public List<HashMap<String, String>> getDyzpHzlist(String bjdm,String xn,String xq){
    	StringBuilder sql = new StringBuilder();
    	sql.append(" select t.xh,");
    	sql.append(" t2.XM,");
    	sql.append(" t1.djmc,");
    	sql.append(" t2.XYMC,");
    	sql.append(" t2.BJMC,");
    	sql.append(" t.xn,");
    	sql.append(" decode(t.xq,'01','第一学期','02','第二学期') xq");
    	sql.append(" from xg_xsxx_dyxj_dypyjg t");
    	sql.append(" left join xg_xsxx_dyxj_djdmb t1");
    	sql.append(" on t.djdm = t1.djdm");
    	sql.append(" left join VIEW_XSJBXX_DYZP t2");
    	sql.append(" on t.xh = t2.XH");
    	sql.append(" where t2.bjdm = ?");
    	sql.append(" and t.xn = ?");
    	sql.append(" and t.xq = ?");
    	return dao.getListNotOut(sql.toString(), new String[]{bjdm,xn,xq});
    }
    
    //德育自评班级汇总表
    public List<HashMap<String, String>> getDyzpHztjlist(String bjdm,String xn,String xq){
    	StringBuilder sql = new StringBuilder();
    	sql.append(" select t.*,decode(instr(t.bfbs,'.'),1,'0' || t.bfbs,t.bfbs) bfb from ");
    	sql.append(" (select a.*, round(a.rs / a.bjzrs * 100, 2) || '%' bfbs");
    	sql.append(" from (select t1.djmc, count(1) rs, t3.bjzrs");
    	sql.append(" from xg_xsxx_dyxj_dypyjg t");
    	sql.append(" left join xg_xsxx_dyxj_djdmb t1");
    	sql.append(" on t.djdm = t1.djdm");
    	sql.append(" left join VIEW_XSJBXX_DYZP t2");
    	sql.append(" on t.xh = t2.XH");
    	sql.append(" left join (select bjdm, count(1) bjzrs");
    	sql.append(" from view_xsjbxx t");
    	sql.append(" where t.bjdm = ?  and t.sfzx = '在校'");
    	sql.append(" group by bjdm) t3");
    	sql.append(" on t2.bjdm = t3.bjdm");
    	sql.append(" where t2.bjdm = ?");
    	sql.append(" and t.xn = ?");
    	sql.append(" and t.xq = ?");
    	sql.append(" group by t1.djmc, t3.bjzrs) a ");
    	sql.append(") t");
    	return dao.getListNotOut(sql.toString(), new String[]{bjdm,bjdm,xn,xq});
    }
    
    //德育自评学院汇总表
    public List<HashMap<String, String>> getDyzpXyHzlist(String xydm,String xn,String xq){
    	StringBuilder sql = new StringBuilder();
    	sql.append(" select t.bjmc,");
    	sql.append(" t.bjrs,");
    	sql.append(" t.yxrs,");
    	sql.append(" decode(instr(t.yxbfb,'.'),1,'0' || t.yxbfb,t.yxbfb) || '%' yxbfb,");
    	sql.append(" t.lhrs,");
    	sql.append(" decode(instr(t.lhbfb,'.'),1,'0' || t.lhbfb,t.lhbfb) || '%' lhbfb,");
    	sql.append(" t.zdrs,");
    	sql.append(" decode(instr(t.zdbfb,'.'),1,'0' || t.zdbfb,t.zdbfb) || '%' zdbfb,");
    	sql.append(" t.hgrs,");
    	sql.append(" decode(instr(t.hgbfb,'.'),1,'0' || t.hgbfb,t.hgbfb) || '%' hgbfb");
    	sql.append(" from  view_dyzp_bjhz t");
    	sql.append(" where t.xydm =?  ");
    	sql.append(" and t.xn = ?");
    	sql.append(" and t.xq =?");
    	return dao.getListNotOut(sql.toString(), new String[]{xydm,xn,xq});
    }
    
    //德育自评学院汇总表最后求和
    public HashMap<String, String> getDyzpXyHzSumlist(String xydm,String xn,String xq){
    	StringBuilder sql = new StringBuilder();
    	sql.append(" select t.*, decode(instr(t.yxzbfbs,'.'),1,'0' || t.yxzbfbs,t.yxzbfbs) yxzbfb, " +
    			"decode(instr(t.lhzbfbs,'.'),1,'0' || t.lhzbfbs,t.lhzbfbs) lhzbfb," +
    			"decode(instr(t.zdzbfbs,'.'),1,'0' || t.zdzbfbs,t.zdzbfbs) zdzbfb," +
    			"decode(instr(t.hgzbfbs,'.'),1,'0' || t.hgzbfbs,t.hgzbfbs) hgzbfb" +
    			" from (");
    	sql.append(" select a.*, ");
    	sql.append(" round(yxzrs / bjzrs * 100, 2) || '%' yxzbfbs,");
    	sql.append(" round(lhzrs / bjzrs * 100, 2) || '%' lhzbfbs,");
    	sql.append(" round(zdzrs / bjzrs * 100, 2) || '%' zdzbfbs,");
    	sql.append(" round(hgzrs / bjzrs * 100, 2) || '%' hgzbfbs");
    	sql.append(" from (select sum(t.bjrs) bjzrs,");
    	sql.append(" sum(t.yxrs) yxzrs,");
    	//sql.append(" sum(t.yxbfb)|| '%' yxzbfb,");
    	sql.append(" sum(t.lhrs) lhzrs,");
    	//sql.append(" sum(t.lhbfb) || '%' lhzbfb,");
    	sql.append(" sum(t.zdrs)  zdzrs,");
    	//sql.append(" sum(t.zdbfb) || '%' zdzbfb,");
    	sql.append(" sum(t.hgrs) hgzrs");
    	//sql.append(" sum(t.hgbfb) || '%' hgzbfb");
    	sql.append(" from  view_dyzp_bjhz t ");
    	sql.append(" where t.xydm =?  ");
    	sql.append(" and t.xn = ?");
    	sql.append(" and t.xq =?) a");
    	sql.append(" ) t");
    	return dao.getMapNotOut(sql.toString(), new String[]{xydm,xn,xq});
    }
    
  //德育自评学校汇总表
    public List<HashMap<String, String>> getDyzpXxHzlist(String xn,String xq){
    	StringBuilder sql = new StringBuilder();
    	sql.append(" select t.xymc,");
    	sql.append(" t.xyrs,");
    	sql.append(" t.yxrs,");
    	sql.append(" decode(instr(t.yxbfb,'.'),1,'0' || t.yxbfb,t.yxbfb) || '%' yxbfb,");
    	sql.append(" t.lhrs,");
    	sql.append(" decode(instr(t.lhbfb,'.'),1,'0' || t.lhbfb,t.lhbfb) || '%' lhbfb,");
    	sql.append(" t.zdrs,");
    	sql.append(" decode(instr(t.zdbfb,'.'),1,'0' || t.zdbfb,t.zdbfb) || '%' zdbfb,");
    	sql.append(" t.hgrs,");
    	sql.append(" decode(instr(t.hgbfb,'.'),1,'0' || t.hgbfb,t.hgbfb) || '%' hgbfb");
    	sql.append(" from  view_dyzp_xyhz t");
    	sql.append(" where t.xn = ?");
    	sql.append(" and t.xq =?");
    	return dao.getListNotOut(sql.toString(), new String[]{xn,xq});
    }
    
    //德育自评学校汇总表最后求和
    public HashMap<String, String> getDyzpXxHzSumlist(String xn,String xq){
    	StringBuilder sql = new StringBuilder();
    	sql.append(" select t.*,decode(instr(t.yxzbfbs,'.'),1,'0' || t.yxzbfbs,t.yxzbfbs) yxzbfb," +
    			"decode(instr(t.lhzbfbs,'.'),1,'0' || t.lhzbfbs,t.lhzbfbs) lhzbfb," +
    			"decode(instr(t.zdzbfbs,'.'),1,'0' || t.zdzbfbs,t.zdzbfbs) zdzbfb," +
    			"decode(instr(t.hgzbfbs,'.'),1,'0' || t.hgzbfbs,t.hgzbfbs) hgzbfb" +
    			" from (");
    	sql.append(" select a.*, ");
    	sql.append(" round(yxzrs / xxzrs * 100, 2) || '%' yxzbfbs,");
    	sql.append(" round(lhzrs / xxzrs * 100, 2) || '%' lhzbfbs,");
    	sql.append(" round(zdzrs / xxzrs * 100, 2) || '%' zdzbfbs,");
    	sql.append(" round(hgzrs / xxzrs * 100, 2) || '%' hgzbfbs");
    	sql.append(" from (select sum(t.xyrs) xxzrs,");
    	sql.append(" sum(t.yxrs) yxzrs,");
    	sql.append(" sum(t.lhrs) lhzrs,");
    	sql.append(" sum(t.zdrs)  zdzrs,");
    	sql.append(" sum(t.hgrs) hgzrs");
    	sql.append(" from  view_dyzp_xyhz t ");
    	sql.append(" where t.xn = ?");
    	sql.append(" and t.xq =?) a");
    	sql.append(" ) t");
    	return dao.getMapNotOut(sql.toString(), new String[]{xn,xq});
    }
    
    /**
     * 
     * @描述: 德育学生明细汇总取学号List
     * @作者：yxy[工号：1206]
     * @日期：2016-10-20 下午06:43:10
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param ids
     * @return
     * List<HashMap<String,String>> 返回类型 
     * @throws
     */
    public List<HashMap<String, String>> getDistinctXh(String[] ids){
    	StringBuffer sql = new StringBuffer();
    	ArrayList<String> paraList = new ArrayList<String>();
    	sql.append(" select distinct  xh from xg_xsxx_dyxj_dypyjg where id in(");
    	for (int i = 0; i < ids.length; i++) {
			sql.append("?");
			paraList.add(ids[i]);
			if(i!=ids.length-1){
				sql.append(",");
			}
		}
    	sql.append(" ");
    	sql.append(" )");
    	return dao.getListNotOut(sql.toString(), paraList.toArray(new String[]{}));
    }
    
    /**
     *
     * @描述:德育自评明细汇总学生信息
     * @作者：yxy[工号：1206]
     * @日期：2016-10-20 下午06:52:29
     * @修改记录: 修改者名字-修改日期-修改内容
     * @return
     * HashMap<String,String> 返回类型 
     * @throws
     */
    public HashMap<String, String> getDyzpMxhzXsxx(String xh){
    	StringBuffer sql = new StringBuffer();
    	sql.append(" select * from view_xsjbxx_dyzp where xh = ?");
    	return dao.getMapNotOut(sql.toString(), new String[]{xh});
    }
    
    /**
     * 
     * @描述: 
     * @作者：yxy[工号：1206]
     * @日期：2016-10-20 下午07:04:56
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param xh
     * @return
     * List<HashMap<String,String>> 返回类型 
     * @throws
     */
    public List<HashMap<String, String>> getEveryXsDyMx(String xh){
    	StringBuffer sql = new StringBuffer();
    	sql.append(" select t.xh,");
    	sql.append(" t1.djmc,");
    	sql.append(" t.xn || '(' || replace(t.xq,'0','') || ')' xnxq");
    	//sql.append(" decode(t.xq,'01','第一学期','02','第二学期') xq");
    	sql.append(" from xg_xsxx_dyxj_dypyjg t");
    	sql.append(" left join xg_xsxx_dyxj_djdmb t1");
    	sql.append(" on t.djdm = t1.djdm");
    	sql.append(" where t.xh = ? order by t.xn,t.xq");
    	return dao.getListNotOut(sql.toString(), new String[]{xh});
    }
}
