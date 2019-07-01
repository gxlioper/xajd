package com.zfsoft.xgxt.jyglnew.jygl.cyyqgl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.commons.beanutils.BeanUtils;
import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

public class CyyqglDao extends SuperDAOImpl<CyyqglForm> {

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		super.setTableName("XG_JYGL_CYYGL");
		super.setKey("gsid");
	}
	
	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(CyyqglForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(CyyqglForm t, User user)
			throws Exception {
		// 生成高级查询相关条件、条件值
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.* from ( ");
		sql.append(" select t1.*,t2.lxmc sshymc,t3.lxmc zcsshymc,decode(t1.gslx,'st','实体','网店') gslxmc, ");
		sql.append(" decode(t1.sfzc,'1','是','否') sfzcmc, "); 
		sql.append(" (select xqmc from xqdzb b where t1.xq=b.xqdm) xqmc ");
		sql.append(" from XG_JYGL_CYYGL t1 ");
		sql.append(" left join xg_jygl_jygl_sshyb t2 on t1.sshy=t2.lxdm ");
		sql.append(" left join xg_jygl_jygl_sshyb t3 on t1.zcsshy=t3.lxdm ");
		sql.append(" ) a where 1 = 1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		
		return getPageList(t, sql.toString(), inputV);
	}
	/**
	 * 学生列表
	 */
	public List<HashMap<String, String>> getXsxxList(CyyqglForm model, User user) throws Exception {
		// 生成高级查询相关条件、条件值
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		String[] xhArr = new String[0];
		if(!"".equals(model.getXhs())){
			xhArr = model.getXhs().split(",");
		}
		StringBuilder sql = new StringBuilder("select a.* from (select a.xz,a.nj,a.xh,a.xm,a.xb,a.xymc,a.zymc,");
		sql.append("a.bjmc,a.xydm,a.zydm,a.bjdm,b.sjhm,b.qqhm from view_xsbfxx a left join (select sjhm,qqhm,xh from xsxxb) b on a.xh=b.xh ");
		if(xhArr.length > 0){
			sql.append(" where a.xh not in(");
			for (int i = 0; i < xhArr.length; i++) {
				sql.append("'"+xhArr[i]+"' ");
				if(i < xhArr.length - 1){
					sql.append(", ");
				}
			}
			sql.append(")");
		}
		sql.append(")a where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		return getPageList(model, sql.toString(), inputV);
	}
	/**
	 * 所属行业列表
	 */
	public List<HashMap<String, String>> getSshyList(){
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from xg_jygl_jygl_sshyb ");
		return dao.getListNotOut(sql.toString(), new String[]{ });
	}
	/**
	 * 团队成员
	 */
	public List<HashMap<String, String>> getTdmxList(CyyqglForm model, User user) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" select b.* ");
		sql.append(" from XG_JYGL_CYYGL_TDMX a ");
		sql.append(" left join ( ");
		sql.append(" select a.xz,a.nj,a.xh,a.xm,a.xb,a.xymc,a.zymc,");
		sql.append(" a.bjmc,a.xydm,a.zydm,a.bjdm,b.sjhm,b.qqhm from view_xsbfxx a left join (select sjhm,qqhm,xh from xsxxb) b on a.xh=b.xh ");
		sql.append(" ) b on a.xh=b.xh ");
		sql.append(" where a.GSID=? ");
		return dao.getListNotOut(sql.toString(), new String[]{ model.getGsid() });
	}
	/**
	 * 保存成员
	 */
	public boolean saveTdmx(CyyqglForm model) throws Exception {
		String sql = "delete from XG_JYGL_CYYGL_TDMX where GSID=?";
		boolean rs = dao.runUpdate(sql, new String[]{ model.getGsid() });
		if(rs){
			String[] xhArr = model.getXhArr();
			if(xhArr == null){
				return true;
			}
			String[] sjhmArr = model.getSjhmArr();
			String[] qqhmArr = model.getQqhmArr();
			List<String[]> params = new ArrayList<String[]>();
			List<String[]> xsxxParams = new ArrayList<String[]>();
			for (int i = 0; i < xhArr.length; i++) {
				params.add(new String[]{ model.getGsid(), xhArr[i] });
				xsxxParams.add(new String[]{ sjhmArr[i], qqhmArr[i], xhArr[i] });
			}
			sql = "insert into XG_JYGL_CYYGL_TDMX(GSID,xh) values(?,?)";
			int[] num = dao.runBatch(sql, params);
			rs = dao.checkBatchResult(num);
			if(rs){
				sql = "update xsxxb set sjhm=?,qqhm=? where xh=?";
				num = dao.runBatch(sql, xsxxParams);
				rs = dao.checkBatchResult(num);
			}
		}
		return rs;
	}
	/**
	 * 删除成员
	 */
	public boolean delTdmx(String[] arr) throws Exception {
		String sql = "delete from XG_JYGL_CYYGL_TDMX where GSID=?";
		List<String[]> params = new ArrayList<String[]>();
		for (int i = 0; i < arr.length; i++) {
			params.add(new String[]{ arr[i] });
		}
		int[] num = dao.runBatch(sql, params);
		return dao.checkBatchResult(num);
	}
	public HashMap<String, String> getModelMap(CyyqglForm t) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" select t1.*,t2.lxmc sshymc,t3.lxmc zcsshymc,decode(t1.gslx,'st','实体','网店') gslxmc, ");
		sql.append(" decode(t1.sfzc,'1','是','否') sfzcmc, "); 
		sql.append(" (select xqmc from xqdzb b where t1.xq=b.xqdm) xqmc ");
		sql.append(" from XG_JYGL_CYYGL t1 ");
		sql.append(" left join xg_jygl_jygl_sshyb t2 on t1.sshy=t2.lxdm ");
		sql.append(" left join xg_jygl_jygl_sshyb t3 on t1.zcsshy=t3.lxdm ");
		sql.append(" where t1.GSID = ? ");
		return dao.getMapNotOut(sql.toString(), new String[] { t.getGsid() });
	}
	/**
	 * 是否已存在
	 */
	public boolean checkExistSave(CyyqglForm model, User user) {
		String id = "-1";
		StringBuilder sql = new StringBuilder(" select count(1) num from XG_JYGL_CYYGL where (gsmc = ? or zcgsmc = ?) and gsid <> ? ");
		if(model.getGsid() != null){
			id = model.getGsid();
		}
		String num = dao.getOneRs(sql.toString(), new String[] { model.getGsmc(), model.getZcgsmc(), id }, "num");
		return "0".equals(num);
	}
}
