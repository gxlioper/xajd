package com.zfsoft.xgxt.xlzx.xlwjga.xlwjgasb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/**
 * 心理危机个案
 */
public class XlwjgasbDao extends SuperDAOImpl<XlwjgasbForm>{

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(XlwjgasbForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(XlwjgasbForm t, User user)
			throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
//		String searchTjByUser = SearchService.getSearchTjByUser(user, "t1", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from ( ");
		sql.append(" select a.*, ");
		sql.append(" b.xm,b.xb,b.nj,b.xymc,b.bjmc,b.zymc,b.zydm,b.bjdm,b.xydm,b.lxdh, ");
		sql.append(" decode(a.zzfk,'1','已反馈','未反馈') zzfkmc, ");
		sql.append(" decode(a.wjgabz,'1','危机个案','已解除') wjgabzmc, ");
		sql.append(" decode(a.ywzyls,'1','有','无') ywzylsmc, ");
		sql.append(" c.lxmc wjcdmc, ");
		sql.append(" d.xm sbrxm ");
		sql.append(" from view_xg_xljk_xlwjga_dgxs a ");
		sql.append(" left join view_xsbfxx b on a.xh=b.xh ");
		sql.append(" left join TSXS_WJCDB c on a.wjcd=c.lxdm ");
		sql.append(" left join fdyxxb d on a.sbr=d.zgh ");
		sql.append(" ) t1 where 1=1 ");
	
//		sql.append(searchTjByUser);
		sql.append(searchTj);
		String userType = user.getUserType();
		String userName = user.getUserName();
		if ("xy".equalsIgnoreCase(userType)) {
			sql.append(" and t1.sbr='" + userName + "' ");
		}
		return getPageList(t, sql.toString(), inputV);
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() { 
		super.setTableName("XG_XLJK_XLWJGA");
		super.setKey("id");
		super.setClass(XlwjgasbForm.class);
	}
	/**
	 * 查询危机程度
	 */
	public List<HashMap<String,String>> getWjcdList() throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("select * from TSXS_WJCDB order by lxdm asc ");
		return dao.getListNotOut(sql.toString(), new String[]{ });
	}
	/**
	 * 根据学号查询最新记录
	 */
	public XlwjgasbForm getModelByXh(String xh) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.*, ");
		sql.append(" b.xm,b.xb,b.nj,b.xymc,b.bjmc,b.zymc,b.zydm,b.bjdm,b.xydm,b.lxdh, ");
		sql.append(" decode(a.zzfk,'1','已反馈','未反馈') zzfkmc, ");
		sql.append(" decode(a.wjgabz,'1','危机个案','已解除') wjgabzmc, ");
		sql.append(" decode(a.ywzyls,'1','有','无') ywzylsmc, ");
		sql.append(" c.lxmc wjcdmc, ");
		sql.append(" d.xm sbrxm ");
		sql.append(" from view_xg_xljk_xlwjga_dgxs a ");
		sql.append(" left join view_xsbfxx b on a.xh=b.xh ");
		sql.append(" left join TSXS_WJCDB c on a.wjcd=c.lxdm ");
		sql.append(" left join fdyxxb d on a.sbr=d.zgh ");
		sql.append(" where a.xh=? ");
		
		return super.getModel(sql.toString(), new String[]{ xh });
	}
	/**
	 * 根据学号查询所有记录
	 */
	public List<HashMap<String,String>> getModelListByXh(String xh) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.*, ");
		sql.append(" b.xm,b.xb,b.nj,b.xymc,b.bjmc,b.zymc,b.zydm,b.bjdm,b.xydm,b.lxdh, ");
		sql.append(" decode(a.zzfk,'1','已反馈','未反馈') zzfkmc, ");
		sql.append(" decode(a.wjgabz,'1','危机个案','已解除') wjgabzmc, ");
		sql.append(" decode(a.ywzyls,'1','有','无') ywzylsmc, ");
		sql.append(" c.lxmc wjcdmc, ");
		sql.append(" d.xm sbrxm ");
		sql.append(" from XG_XLJK_XLWJGA a ");
		sql.append(" left join view_xsbfxx b on a.xh=b.xh ");
		sql.append(" left join TSXS_WJCDB c on a.wjcd=c.lxdm ");
		sql.append(" left join fdyxxb d on a.sbr=d.zgh ");
		sql.append(" where a.xh=? order by a.sbsj desc  ");
		
		return dao.getListNotOut(sql.toString(), new String[]{ xh });
	}
	/**
	 * 解除 心理危机个案 
	 */
	public boolean cancelXlwjgasb(String[] idArr) throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append(" update XG_XLJK_XLWJGA set wjgabz='0' where id=? ");
		List<String[]> params = new ArrayList<String[]>();
		for (int i = 0; i < idArr.length; i++) {
			params.add(new String[]{ idArr[i] });
		}
		int[] num = dao.runBatch(sql.toString(), params);
		return dao.checkBatchResult(num);
	}
}
