/**
 * @部门:学工产品事业部
 * @日期：2013-7-30 下午04:46:37 
 */  
package xsgzgl.gygl.gyyggl;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： xucy [工号：754]
 * @时间： 2013-7-30 下午04:46:37 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class GyygxxglDao  extends SuperDAOImpl<GyygxxglForm>{

	
	public List<HashMap<String, String>> getPageList(GyygxxglForm t)
			throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();

		/*sql.append(" select * from ( select a.*,decode(a.zgzt,'','否','0','否','1','是')zgztmc,( select b.xb from dmk_xb b where b.xbdm = a.xb) xbmc,(select b.zwmc from XG_GYGL_NEW_GYYGZWDMB b where b.zwdm = a.zwdm) zwmc" +
				" from XG_GYGL_NEW_GYYGXXB a) a where 1=1 ");*/

		sql.append("select * from VIEW_NEW_DC_GYGL_YGGL where 1=1 ");
		
		sql.append(searchTj);
		return getPageList(t, sql.toString(), StringUtils.joinStrArr(inputV,
				new String[] {}));
	}
	
	public List<HashMap<String, String>> getZwdmList()
	throws Exception {
		String sql = "select zwdm,zwmc from XG_GYGL_NEW_GYYGZWDMB";
		return dao.getList(sql, new String[]{}, new String[]{"zwdm","zwmc"});
	}
	
	
	public HashMap<String, String> getYgxxmap (String ygbh){
		String sql = " select a.*,decode(a.zgzt,'','否','0','否','1','是')zgztmc,( select b.xb from dmk_xb b where b.xbdm = a.xb) xbmc,(select b.zwmc from XG_GYGL_NEW_GYYGZWDMB b where b.zwdm = a.zwdm) zwmc " +
				"from XG_GYGL_NEW_GYYGXXB a where a.ygbh=?";
		return dao.getMapNotOut(sql, new String[]{ygbh});
	}
	
	public List<HashMap<String, String>> getPageList(GyygxxglForm t, User user)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}
	
	public String checkYgbh(String ygbh){
		String sql="select count(1) num from XG_GYGL_NEW_GYYGXXB where ygbh=?";
		String num=dao.getOneRs(sql, new String[]{ygbh}, "num");
		return "0".equals(num)?"true":"false";
	}

	protected void setTableInfo() {
		super.setTableName("XG_GYGL_NEW_GYYGXXB");
		super.setKey("ygbh");// 主键一定要和FORM中的set方法的属性一致,即大小写
		super.setClass(GyygxxglForm.class);
	}

}
