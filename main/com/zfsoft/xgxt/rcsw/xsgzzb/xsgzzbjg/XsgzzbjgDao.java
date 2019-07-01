package com.zfsoft.xgxt.rcsw.xsgzzb.xsgzzbjg;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import org.apache.commons.beanutils.BeanUtils;
import xgxt.comm.search.SearchService;
import xgxt.form.User;

import java.util.HashMap;
import java.util.List;

public class XsgzzbjgDao extends SuperDAOImpl<XsgzzbjgForm> {

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
//		super.setTableName("xg_rcsw_xsgzzb_xsgzzbjgb"); // 因为表是动态的，此处不设置TableName
		super.setKey("jgid");
	}
	
	/**
	 * 动态取表
	 */
	public void setTableInfo(XsgzzbjgForm t) {
		String table = "xg_rcsw_xsgzzb_xsgzzbjgb";
		if("bj".equals(t.getGzzblx())){
			table = "xg_rcsw_bjgzzb_bjgzzbjgb";
		}
		super.setTableName(table);
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(XsgzzbjgForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(XsgzzbjgForm t, User user)
			throws Exception {
		// 生成高级查询相关条件、条件值
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.* from (select t1.sqid,t1.jgid,t1.xn,t1.xq,t1.bmdm,t1.bmdm xydm,t1.zc,t1.yzzygz,t1.xzzygz,t1.yj,t1.lrsj,t1.lrr,t1.sjly,t1.zcksjsrq,t1.qtgz, ");
		sql.append(" '第'||t1.zc||'周' zcmc,t5.xqmc,t2.bmmc bmdmmc,t2.bmmc xymc,t3.xm lrrxm ");
		sql.append(" from xg_rcsw_xsgzzb_xsgzzbjgb t1 ");
		sql.append(" left join zxbz_xxbmdm t2 on t1.bmdm = t2.bmdm ");
		sql.append(" left join fdyxxb t3 on t1.lrr = t3.zgh ");
		sql.append(" left join xqdzb t5 on t1.xq = t5.xqdm ");
		sql.append(" ) a where 1 = 1 ");
		sql.append(searchTj);
		String userStatus = user.getUserStatus();
		String userDep = user.getUserDep();
		String userName = user.getUserName();
		if ("xy".equalsIgnoreCase(userStatus)) {
			sql.append(" and a.bmdm='" + userDep + "' ");
		}else if ("bzr".equalsIgnoreCase(userStatus) ||
				  "fdy".equalsIgnoreCase(userStatus) || 
				  "jd".equalsIgnoreCase(userStatus)){
			sql.append(" and a.lrr='" + userName + "' ");
		}
		return getPageList(t, sql.toString(), inputV);
	}
	/**
	 * 班级周报
	 */
	public List<HashMap<String, String>> getPageListBj(XsgzzbjgForm t, User user)
		throws Exception {
		// 生成高级查询相关条件、条件值
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.* from (select t1.sqid,t1.xn,t1.xq,t1.zc,t1.ydrs,t1.sdrs,t1.qjrs,t1.wdrs,t1.zynr,t1.zywt,t1.jjdc,t1.filepath,t1.lrsj,t1.lrr,t1.jgid,t1.sjly,t1.zcksjsrq, ");
		sql.append(" '第'||t1.zc||'周' zcmc,t5.xqmc,t3.xm lrrxm,t1.ydrs||'/'||t1.sdrs||'/'||t1.qjrs||'/'||t1.wdrs rstj, ");
		sql.append(" t2.*,t2.xydm bmdm,c.dbfdy ");
		sql.append(" from xg_rcsw_bjgzzb_bjgzzbjgb t1 ");
		sql.append(" left join view_njxyzybj_all t2 on t1.bjdm = t2.bjdm ");
		sql.append(" left join fdyxxb t3 on t1.lrr = t3.zgh ");
		sql.append(" left join xqdzb t5 on t1.xq = t5.xqdm ");
		sql.append(" LEFT JOIN (SELECT bjdm,WM_CONCAT(t2.XM) dbfdy FROM FDYBJB t1 LEFT JOIN FDYXXB t2 ON t1.ZGH = t2.ZGH  GROUP BY BJDM) c ON t1.BJDM = c.BJDM ");
		sql.append(" ) a where 1 = 1 ");
		sql.append(searchTj);
		String userStatus = user.getUserStatus();
		String userDep = user.getUserDep();
		String userName = user.getUserName();
		if ("xy".equalsIgnoreCase(userStatus)) {
			sql.append(" and a.bmdm='" + userDep + "' ");
		}else if ("bzr".equalsIgnoreCase(userStatus) ||
				  "fdy".equalsIgnoreCase(userStatus) || 
				  "jd".equalsIgnoreCase(userStatus)){
			sql.append(" and a.lrr='" + userName + "' ");
		}
		return getPageList(t, sql.toString(), inputV);
	}
	
	@Override
	public XsgzzbjgForm getModel(XsgzzbjgForm t) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" select t1.sqid,t1.jgid,t1.xn,t1.xq,t1.bmdm,t1.bmdm xydm,t1.zc,t1.yzzygz,t1.xzzygz,t1.yj,t1.lrsj,t1.lrr,t1.sjly,t1.zcksjsrq,t1.qtgz, ");
		sql.append(" '第'||t1.zc||'周' zcmc,t5.xqmc,t2.bmmc bmdmmc,t2.bmmc xymc,t3.xm lrrxm ");
		sql.append(" from xg_rcsw_xsgzzb_xsgzzbjgb t1 ");
		sql.append(" left join zxbz_xxbmdm t2 on t1.bmdm = t2.bmdm ");
		sql.append(" left join fdyxxb t3 on t1.lrr = t3.zgh ");
		sql.append(" left join xqdzb t5 on t1.xq = t5.xqdm ");
		sql.append(" where t1.jgid = ? ");
		HashMap<String,String> map = dao.getMapNotOut(sql.toString(), new String[] { t.getJgid() });
		XsgzzbjgForm model=new XsgzzbjgForm();
		BeanUtils.copyProperties(model, map);
		return model;
	}
	/**
	 * 班级周报
	 */
	public XsgzzbjgForm getModelBj(XsgzzbjgForm t) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" select t1.sqid,t1.xn,t1.xq,t1.zc,t1.ydrs,t1.sdrs,t1.qjrs,t1.wdrs,t1.zynr,t1.zywt,t1.jjdc,t1.filepath,t1.lrsj,t1.lrr,t1.jgid,t1.sjly,t1.zcksjsrq, ");
		sql.append(" '第'||t1.zc||'周' zcmc,t5.xqmc,t3.xm lrrxm,t1.ydrs||'/'||t1.sdrs||'/'||t1.qjrs||'/'||t1.wdrs rstj, ");
		sql.append(" t2.*,t2.xydm bmdm,c.dbfdy ");
		sql.append(" from xg_rcsw_bjgzzb_bjgzzbjgb t1 ");
		sql.append(" left join view_njxyzybj_all t2 on t1.bjdm = t2.bjdm ");
		sql.append(" left join fdyxxb t3 on t1.lrr = t3.zgh ");
		sql.append(" left join xqdzb t5 on t1.xq = t5.xqdm ");
		sql.append(" LEFT JOIN (SELECT bjdm,WM_CONCAT(t2.XM) dbfdy FROM FDYBJB t1 LEFT JOIN FDYXXB t2 ON t1.ZGH = t2.ZGH  GROUP BY BJDM) c ON t1.BJDM = c.BJDM ");
		sql.append(" where t1.jgid = ? ");
		HashMap<String,String> map = dao.getMapNotOut(sql.toString(), new String[] { t.getJgid() });
		XsgzzbjgForm model=new XsgzzbjgForm();
		BeanUtils.copyProperties(model, map);
		return model;
	}
	
}
