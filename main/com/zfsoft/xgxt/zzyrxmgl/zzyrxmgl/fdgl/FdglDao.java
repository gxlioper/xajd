package com.zfsoft.xgxt.zzyrxmgl.zzyrxmgl.fdgl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.base.DealString;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.zzyrxmgl.zzyrxmgl.ZzyrxmglActionForm;

/** 
 * @功能描述：资助育人项目管理-辅导管理dao
 * @author：Lu.Yao 【1271】
 * @date：2017-10-20 上午11:16:08 
 */
public class FdglDao extends SuperDAOImpl<ZzyrxmglActionForm> {

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		super.setTableName("XG_ZZYR_FDFBB");
		super.setKey("fdfbid");
	}
	
	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(ZzyrxmglActionForm t)
			throws Exception {
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(ZzyrxmglActionForm t, User user)
			throws Exception {
		// 生成高级查询相关条件、条件值
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");		
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.* from ( ");
		sql.append(" select t1.fdfbid,t1.fbrxh,t1.fbrxh xh,t1.fdkm,t1.fdsj,t1.fbbz,");
		sql.append(" (select count(*) from xg_zzyr_fdxxb a where a.fdfbid=t1.fdfbid)||'/'||t1.xdrs||'/'||(select count(*) from xg_zzyr_fdxxb a where a.fdfbid=t1.fdfbid and a.xscshzt='1') xdrs, ");
		sql.append(" t2.xm fbrxm,t2.xymc fbrxymc");
		sql.append(" from xg_zzyr_fdfbb t1 ");
		sql.append(" left join view_xsxxb t2 on t1.fbrxh=t2.xh where t1.fblx='0'");
		sql.append(" ) a where 1 = 1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		if("stu".equalsIgnoreCase(user.getUserStatus())){
			ArrayList<String> inputVs = new ArrayList<String>();
			if (StringUtils.isNotNull(t.getFdkm())) {
				sql.append(" and fdkm like ?");
				inputVs.add("%"+t.getFdkm()+"%");
			}
			return getPageList(t, sql.toString(), inputVs.toArray(new String[] {}));
		}else{
			String[] inputV = SearchService.getTjInput(t.getSearchModel());
			return getPageList(t, sql.toString(), inputV);
		}
	}

	/** 
	 * @description：获取我的辅导页面数据
	 * @dept:学工2部
	 * @author：Lu.Yao 【1271】
	 * @date：2017-10-17 上午09:28:40 
	 * @param model
	 * @param user
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getPageList2(ZzyrxmglActionForm t,
			User user) throws Exception {
		// 生成高级查询相关条件、条件值
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.* from ( ");
		sql.append(" select t1.fdxxid,t1.fdfbid,t1.fdrxh,t1.fdrxh xh,t4.fdkm, ");
		sql.append(" t2.xm fdrxm,t2.xymc fdrxymc,t1.bfdrxh,t3.xm bfdrxm,t3.xymc bfdrxymc");
		sql.append(" from xg_zzyr_fdxxb t1 ");
		sql.append(" left join view_xsxxb t2 on t1.fdrxh=t2.xh ");
		sql.append(" left join view_xsxxb t3 on t1.bfdrxh=t3.xh ");
		sql.append(" left join xg_zzyr_fdfbb t4 on t1.fdfbid = t4.fdfbid where t1.xscshzt='1'");
		sql.append(" ) a where 1 = 1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		
		return getPageList(t, sql.toString(), inputV);
	}

	/** 
	 * @description：增加关联开放学院
	 * @dept:学工2部
	 * @author：Lu.Yao 【1271】
	 * @date：2017-10-17 下午03:11:02 
	 * @param kfxydm
	 * @param fdfbid
	 * void 返回类型 
	 * @throws 
	 */
	public boolean insertKfxydm(String kfxydm, String fdfbid) throws Exception{
		return dao.runInsert("xg_zzyr_fdfbxyglb", new String[]{"kfxydm","fdfbid"}, new String[]{kfxydm,fdfbid});
	}

	/** 
	 * @description：查看
	 * @dept:学工2部
	 * @author：Lu.Yao 【1271】
	 * @date：2017-10-17 下午03:42:42 
	 * @param t
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws 
	 */
	public HashMap<String, String> getModelMap(ZzyrxmglActionForm t) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" select t1.fdfbid,t1.fbrxh,t1.fbrxh xh,t1.fdkm,t1.fdsj,t1.fbbz,t1.xdrs, ");
		sql.append(" t2.xm fbrxm,t2.xymc fbrxymc");
		sql.append(" from xg_zzyr_fdfbb t1 ");
		sql.append(" left join view_xsxxb t2 on t1.fbrxh=t2.xh ");
		sql.append(" where t1.fdfbid = ? ");
		return dao.getMapNotOut(sql.toString(), new String[] { t.getFdfbid() });
	}

	/** 
	 * @description：查询关联开放学院list
	 * @dept:学工2部
	 * @author：Lu.Yao 【1271】
	 * @date：2017-10-17 下午04:04:36 
	 * @param t
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getKfxydmByid(ZzyrxmglActionForm t) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select t1.fdfbid,t1.kfxydm,t2.bmmc kfxymc ");
		sql.append(" from xg_zzyr_fdfbxyglb t1 left join ZXBZ_XXBMDM t2 on t1.kfxydm = t2.bmdm");
		sql.append(" where t1.fdfbid = ? ");
		return dao.getListNotOut(sql.toString(), new String[] { t.getFdfbid() });
	}

	/** 
	 * @description：删除关联开放学院代码
	 * @dept:学工2部
	 * @author：Lu.Yao 【1271】
	 * @date：2017-10-17 下午04:18:04 
	 * @param model
	 * void 返回类型 
	 * @throws 
	 */
	public int deleteKfxydm(ZzyrxmglActionForm model) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("delete from xg_zzyr_fdfbxyglb where fdfbid = ? ");
		return dao.runDelete(sql.toString(), new String[] { model.getFdfbid() });
	}

	/** 
	 * @description：获取报名人员信息list
	 * @dept:学工2部
	 * @author：Lu.Yao 【1271】
	 * @date：2017-10-18 上午11:28:00 
	 * @param model
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getBmrsList(ZzyrxmglActionForm t) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select t1.fdxxid,t2.xh,t2.xm,t2.xymc,t2.zymc,t2.bjmc,t2.sjhm lxdh,t1.shzt dqshzt,");
		sql.append(" case when t1.xscshzt = '1' and t1.fdyshzt='1' and t1.bfdyshzt='1' then  '1'");
		sql.append(" when t1.xscshzt = '0' and t1.fdyshzt is not null and t1.bfdyshzt is not null then '0'");
		sql.append(" when t1.shzt is null then '2'");
		sql.append(" when t1.shzt ='1' and ( t1.fdyshzt is  null and t1.bfdyshzt is  null and t1.xscshzt is  null) then '3'");
		sql.append(" when t1.shzt ='0' and ( t1.fdyshzt is  null and t1.bfdyshzt is  null and t1.xscshzt is  null) then '4' else '5' end shzt");
		sql.append(" from xg_zzyr_fdxxb t1 left join view_xsxxb t2 on t1.bfdrxh = t2.xh where t1.fdfbid = ?");
		return dao.getListNotOut(sql.toString(), new String[] { t.getFdfbid() });
	}

	/**
	 * @throws Exception  
	 * @description：辅导审核
	 * @dept:学工2部
	 * @author：Lu.Yao 【1271】
	 * @date：2017-10-18 下午02:00:30 
	 * @param model
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean updateShzt(ZzyrxmglActionForm model) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("update xg_zzyr_fdxxb set shzt=?,tysj=? where fdxxid = ? ");
		return dao.runUpdate(sql.toString(), new String[]{model.getShzt(),DealString.getDateTime(),model.getFdxxid()});
	}

	/** 
	 * @description：查看
	 * @dept:学工2部
	 * @author：Lu.Yao 【1271】
	 * @date：2017-10-18 下午03:47:55 
	 * @param model
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws 
	 */
	public HashMap<String, String> getModelMap2(ZzyrxmglActionForm model) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select t1.fdxxid,t1.fdrxh,t1.bfdrxh,t4.fdkm,t1.xspjjg,t1.lspjjg, ");
		sql.append(" t2.xm fdrxm,t2.xymc fdrxymc,t2.sjhm fdrlxdh, ");
		sql.append(" t3.xm bfdrxm,t3.xymc bfdrxymc,t3.sjhm bfdrlxdh ");
		sql.append(" from xg_zzyr_fdxxb t1 ");
		sql.append(" left join view_xsxxb t2 on t1.fdrxh=t2.xh ");
		sql.append(" left join view_xsxxb t3 on t1.bfdrxh=t3.xh ");
		sql.append(" left join xg_zzyr_fdfbb t4 on t1.fdfbid=t4.fdfbid ");
		sql.append(" where t1.fdxxid = ? ");
		return dao.getMapNotOut(sql.toString(), new String[] { model.getFdxxid() });
	}

	/**
	 * @throws Exception  
	 * @description：填写辅导记录
	 * @dept:学工2部
	 * @author：Lu.Yao 【1271】
	 * @date：2017-10-19 上午08:53:53 
	 * @param zmodel
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean insertFdjl(ZzyrxmglActionForm zmodel) throws Exception {
		return dao.runInsert("xg_zzyr_fdjlb", new String[]{"fdxxid","fdrq","fdnr","fdbz","fdlx","gs","fddd","fdpj","fdjssj"}, 
				new String[]{zmodel.getFdxxid(),zmodel.getFdrq(),zmodel.getFdnr(),zmodel.getFdbz(),zmodel.getFdlx(),zmodel.getGs(),zmodel.getFddd(),zmodel.getFdpj(),zmodel.getFdjssj()});
	}

	/** 
	 * @description：获取辅导记录list
	 * @dept:学工2部
	 * @author：Lu.Yao 【1271】
	 * @date：2017-10-19 上午08:58:17 
	 * @param model
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getFdjlList(ZzyrxmglActionForm model) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select t1.id,t1.fdxxid,t1.fdrq,t1.fdnr,t1.fdbz,t1.fdjssj,t1.gs,t1.fddd,t1.fdpj ");
		sql.append(" from xg_zzyr_fdjlb t1 where t1.fdxxid = ? and t1.fdlx = ? order by t1.fdrq");
		return dao.getListNotOut(sql.toString(), new String[] { model.getFdxxid(),model.getFdlx() });
	}

	/**
	 * @throws Exception 
	 * @return  
	 * @description：删除辅导记录
	 * @dept:学工2部
	 * @author：Lu.Yao 【1271】
	 * @date：2017-10-19 上午09:27:42 
	 * @param model
	 * void 返回类型 
	 * @throws 
	 */
	public int deleteFdjlPl(ZzyrxmglActionForm model) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("delete from xg_zzyr_fdjlb where fdxxid = ? and fdlx = ?");
		return dao.runDelete(sql.toString(), new String[] { model.getFdxxid(),model.getFdlx() });
	}

	/**
	 * @throws Exception  
	 * @description：删除辅导记录
	 * @dept:学工2部
	 * @author：Lu.Yao 【1271】
	 * @date：2017-10-19 上午09:40:29 
	 * @param model
	 * @return
	 * int 返回类型 
	 * @throws 
	 */
	public int deleteFdjl(ZzyrxmglActionForm model) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("delete from xg_zzyr_fdjlb where id = ? ");
		return dao.runDelete(sql.toString(), new String[] { model.getId() });
	}

	/** 
	 * @description：判断能否取消辅导
	 * @dept:学工2部
	 * @author：Lu.Yao 【1271】
	 * @date：2017-10-19 下午04:40:20 
	 * @param values
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean checkCandel(String values) {
		String[] id = values.split(",");
		StringBuilder sql = new StringBuilder();
		sql.append("select count(*) num from xg_zzyr_fdxxb where fdfbid in (");
		for(int i = 0;i < id.length;i++){
			sql.append("'"+id[i]+"',");
		}
		return "0".equals(dao.getOneRs(sql.substring(0, sql.length()-1).toString()+")", new String[]{}, "num"));
	}

	/** 
	 * @description：判断已同意辅导人数
	 * @dept:学工2部
	 * @author：Lu.Yao 【1271】
	 * @date：2017-10-19 下午04:58:18 
	 * @param model
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean checkYtyfdrs(ZzyrxmglActionForm model) {
		String sql1 = "select count(*) num from xg_zzyr_fdxxb where fdfbid in (select t1.fdfbid from " +
				"xg_zzyr_fdxxb t1 left join xg_zzyr_fdfbb t2 on t1.fdfbid = t2.fdfbid where t1.fdxxid = ?) and shzt = '1'";
		String sql2 = "select nvl(xdrs,0) xdrs from xg_zzyr_fdfbb t1 left join xg_zzyr_fdxxb t2 " +
				"on t1.fdfbid = t2.fdfbid where t2.fdxxid=?";
		String ytyrs = dao.getOneRs(sql1, new String[]{model.getFdxxid()}, "num");
		String xdrs = dao.getOneRs(sql2, new String[]{model.getFdxxid()}, "xdrs");
		return Integer.parseInt(ytyrs) < Integer.parseInt(xdrs);
	}

	/** 
	 * @description：判断能否不同意辅导
	 * @dept:学工2部
	 * @author：Lu.Yao 【1271】
	 * @date：2017-10-23 下午02:50:08 
	 * @param model
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean checkCancancel(ZzyrxmglActionForm model) {
		String sql = "select count(*) num from xg_zzyr_fdjlb where fdxxid = ?";
		return "0".equals(dao.getOneRs(sql, new String[]{model.getFdxxid()}, "num"));
	}

}
