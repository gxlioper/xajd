package com.zfsoft.xgxt.dtjs.dxbmgl.pxgl;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @功能描述：党校培训管理dao
 * @author：杨珩 【1346】
 * @date：2017-11-1 下午02:53:51 
 */
public class DxpxglDao extends SuperDAOImpl<DxpxglForm> {

	@Override
	public List<HashMap<String, String>> getPageList(DxpxglForm t) throws Exception {
		return null;
	}

	/*
	 * 描述:获取查询列表
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception 
	 * @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User) 
	 */
	@Override
	public List<HashMap<String, String>> getPageList(DxpxglForm t, User user) throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a","xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuffer sql = new StringBuffer("select * from (select x.*,y.xm fbrxm,y.lxdh,case when z.sqid is not null then '1' else '0' end sjyl from xg_dtjs_dxpxb x");
		sql.append(" left join (select a.yhm,a.xm,b.lxdh from yhb a left join fdyxxb b on a.yhm=b.zgh) y on x.fbr=y.yhm");
		sql.append(" left join xg_dtjs_dxbmsqb z on x.id=z.pxid) a where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		return this.getPageList(t, sql.toString(), inputV);
	}

	/** 
	 * @功能描述：保存培训数据
	 * @author：杨珩 【1346】
	 * @date：2017-11-4 下午02:17:51 
	 * @param t
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean save(DxpxglForm t) throws Exception {
		StringBuffer sb=new StringBuffer();
		sb.append("insert into xg_dtjs_dxpxb(pxqc,pxsj,bmkssj,bmjssj,pxnr,fbr)values(?,?,?,?,?,?)");
		return dao.insert(sb.toString(), new String[]{t.getPxqc(),t.getPxsj(),t.getBmkssj(),t.getBmjssj(),t.getPxnr(),t.getFbr()});
	}
	
	/** 
	 * @功能描述：更新培训数据
	 * @author：杨珩 【1346】
	 * @date：2017-11-4 下午02:24:17 
	 * @param t
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean update(DxpxglForm t) throws Exception {
		StringBuffer sb=new StringBuffer();
		sb.append("update xg_dtjs_dxpxb set pxqc=?,pxsj=?,bmkssj=?,bmjssj=?,pxnr=? where pxqc=?");
		return dao.runUpdate(sb.toString(), new String[]{t.getPxqc(),t.getPxsj(),t.getBmkssj(),t.getBmjssj(),t.getPxnr(),t.getPxqc()});
	}
	
	/** 
	 * @功能描述：设置分数百分比
	 * @author：杨珩 【1346】
	 * @date：2017-11-4 下午02:26:34 
	 * @param t
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean updatebfb(DxpxglForm t) throws Exception {
		StringBuffer sb=new StringBuffer();
		sb.append("update xg_dtjs_dxpxb set kqcjbfb=?,sjcjbfb =?,bjcjbfb=?,kscjbfb=? where pxqc=?");
		return dao.runUpdate(sb.toString(), new String[]{t.getKqcjbfb(),t.getSjcjbfb(),t.getBjcjbfb(),t.getKscjbfb(),t.getPxqc()});
	}
	
	/** 
	 * @功能描述：删除培训数据,已有学生报名的排除
	 * @author：杨珩 【1346】
	 * @date：2017-11-4 下午02:30:20 
	 * @param t
	 * @return
	 * @throws Exception
	 * int 返回类型 
	 * @throws 
	 */
	public int del(String sql,String[] values) throws Exception {
		return dao.runDelete(sql, values);
	}
	
	@Override
	protected void setTableInfo() {
		this.setKey("id");
		this.setTableName("xg_dtjs_dxpxb");
		this.setClass(DxpxglForm.class);
	}

}
