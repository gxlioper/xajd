package com.zfsoft.xgxt.dtjs.dxbmgl.pxbm;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @功能描述：党校培训报名dao
 * @author：杨珩 【1346】
 * @date：2017-11-1 下午03:12:34 
 */
public class DxpxbmDao extends SuperDAOImpl<DxpxbmForm> {

	@Override
	public List<HashMap<String, String>> getPageList(DxpxbmForm t) throws Exception {
		return null;
	}

	@Override
	public List<HashMap<String, String>> getPageList(DxpxbmForm t, User user) throws Exception {
		//String searchTj = SearchService.getSearchTj(t.getSearchModel());
		//String searchTjByUser = SearchService.getSearchTjByUser(user, "a","xydm", "bjdm");
		//String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuffer sql = new StringBuffer("select * from (select x.*,y.xm fbrxm,y.lxdh,z.*,case when z.xh is not null then '1' else '0' end sfbm,");
		sql.append(" case when z.xh is not null then '已报名' else '未报名' end sfbmmc,decode(z.shzt,'0','未审核','1','通过','2','不通过','3','退回','4','需重审','5','审核中','未审核') shztmc ");
		sql.append(" from xg_dtjs_dxpxb x left join (select a.yhm,a.xm,b.lxdh from yhb a left join fdyxxb b on a.yhm=b.zgh) y on x.fbr=y.yhm ");
		sql.append(" left join (select * from xg_dtjs_dxbmsqb where xh=?) z on x.id=z.pxid) a where 1=1 ");
		//sql.append(searchTjByUser);
		sql.append(" ");
		//sql.append(searchTj);
		return this.getPageList(t, sql.toString(), new String[]{user.getUserName()});
	}
	
	/** 
	 * @功能描述：更新百分比
	 * @author：杨珩 【1346】
	 * @date：2017-11-6 下午07:09:59 
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean updateBm(DxpxbmForm t) throws Exception {
		StringBuffer sb=new StringBuffer();
		sb.append("insert into xg_dtjs_dxbmsqb(sqid,pxid,xh,splc,shzt,sqsj) values (?,?,?,?,'5',to_char(sysdate,'yyyy-mm-dd'))");
		return dao.runUpdate(sb.toString(), new String[]{t.getSqid(),t.getPxid(),t.getXh(),t.getSplc()});
	}
	/** 
	 * @功能描述：获取审批流程
	 * @author：杨珩 【1346】
	 * @date：2017-11-7 上午09:53:02 
	 * @return
	 * @throws Exception
	 * String 返回类型 
	 * @throws 
	 */
	public String getSplc() throws Exception {
		StringBuffer sb=new StringBuffer();
		sb.append("select shl from XG_DTJS_DXBMSHLB");
		return dao.getOneRs(sb.toString(), new String[]{}, "shl");
	}
	@Override
	protected void setTableInfo() {
		this.setKey("sqid");
		this.setTableName("xg_dtjs_dxbmsqb");
		this.setClass(DxpxbmForm.class);
	}

}
