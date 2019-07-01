/**
 * @部门:学工产品事业部
 * @日期：2015-4-25 下午12:14:35 
 */  
package xsgzgl.xtwh.general.news;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import xgxt.form.User;

import java.util.HashMap;
import java.util.List;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： xiaxia[工号:1104]
 * @时间： 2015-4-25 下午12:14:35 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class YydmdDao extends SuperDAOImpl<NewsManageForm>{

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(NewsManageForm t)
			throws Exception {
		StringBuffer sql = new StringBuffer();
		if(null==t.getTablename()||"".equals(t.getTablename())){
			t.setTablename("xg_tzgglljlb_1");
		}
//		 sql.append("select a.*,(case when a.yhlx='stu' then '学生' else '老师' end)yhlxmc from "+t.getTablename()+" a where a.newsid=? ");

		sql.append("SELECT * FROM (");
		sql.append("SELECT t.*,z.BMMC FROM ");
		sql.append("(select a.*,(case when a.yhlx='stu' then '学生' else '老师' end) yhlxmc,");
		sql.append("CASE WHEN a.yhlx = 'stu' THEN (SELECT xm FROM XSXXB WHERE xh = a.YHM)");
		sql.append("ELSE (SELECT xm FROM YHB WHERE YHM = a.YHM) END xm,");
		sql.append("CASE WHEN a.yhlx = 'stu' THEN (SELECT XYDM FROM VIEW_XSJBXX WHERE xh = a.YHM) ");
		sql.append("ELSE (SELECT SZBM FROM YHB WHERE YHM = a.YHM) END bmdm ");
		sql.append("from ");
		sql.append(t.getTablename());
		sql.append(" a) t LEFT JOIN ZXBZ_XXBMDM z ON t.bmdm = z.BMDM ");
		sql.append("where t.newsid=?");
		sql.append(") t");

		return getPageList(t, sql.toString(), new String[]{t.getNewsid()});
		}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(NewsManageForm t, User user)
			throws Exception {
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		// TODO 自动生成方法存根
		
	}

}
