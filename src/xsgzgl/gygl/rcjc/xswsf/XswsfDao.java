/**
 * @部门:学工产品事业部
 * @日期：2018-5-8 下午02:18:44 
 */
package xsgzgl.gygl.rcjc.xswsf;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.comm.search.SearchModel;
import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 公寓管理-卫生检查-学生卫生分（苏州卫生职业技术学院）
 * @类功能描述: (这里用一句话描述这个类的作用)
 * @作者：lgx[工号:1553]
 * @时间： 2018-5-8 下午02:18:44
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class XswsfDao extends SuperDAOImpl<XswsfForm> {
	/*
	 * 描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		super.setTableName("xg_gygl_new_wsjc_12688_xsfsb");
		super.setKey("guid");
		super.setClass(XswsfForm.class);

	}

	/*
	 * 描述: @see
	 * com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(XswsfForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	 * 描述: @see
	 * com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object,
	 * xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(XswsfForm model, User user)
			throws Exception {
		SearchModel searchmodel = model.getSearchModel();
		String searchTj = SearchService.getSearchTj(searchmodel);
		String[] inputV = SearchService.getTjInput(searchmodel);
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "");
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from ( ");
		sql.append("select a.*,b.xn,b.xq,b.mc,decode(b.pfjb,'xj','校级','yj','院级') pfjbmc, ");
		sql.append(" b.xn || nvl(d.xqmc,b.xq) || ' ' ||  b.mc || '（' || decode(b.pfjb,'xj','校级','yj','院级') || '）'  jcrcxx,");
		sql.append(" c.xydm,c.xymc,c.nj,c.zydm,c.zymc,c.xm ,c.bjmc,c.bjdm");
		sql.append(" from xg_gygl_new_wsjc_12688_xsfsb  a ");
		sql.append(" left join XG_GYGL_NEW_WSJC_JCRCB b on a.rcid=b.guid "); 
		sql.append(" left join view_xsjbxx c on a.xh=c.xh ");
		sql.append(" left join xqdzb d on b.xq=d.xqdm ) t where 1=1 "); 
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(model, sql.toString(), inputV);
	}

	/** 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：张昌路[工号：982]
	 * @日期：2018-5-9 上午08:55:51
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param myForm
	 * @return
	 * XswsfForm 返回类型 
	 * @throws 
	 */
	public HashMap<String, String> getXswsfById(XswsfForm myForm) {
		StringBuilder sql = new StringBuilder();
		sql.append("select a.*,b.xn,nvl(d.xqmc,b.xq) xqmc,b.mc,");
		sql.append(" b.xn || nvl(d.xqmc,b.xq) || ' ' ||  b.mc || '（' || decode(b.pfjb,'xj','校级','yj','院级') || '）'  jcrcxx ");
		sql.append(" from xg_gygl_new_wsjc_12688_xsfsb  a ");
		sql.append(" left join XG_GYGL_NEW_WSJC_JCRCB b on a.rcid=b.guid "); 
		sql.append(" left join xqdzb d on b.xq=d.xqdm ");
		sql.append(" where a.guid=? "); 
		return dao.getMapNotOut(sql.toString(), new String[]{myForm.getGuid()});
		
	}


	/** 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：lgx[工号：1553]
	 * @日期：2018-5-9 下午04:41:06
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getRcXnList() {
		String sql = "select xn from XG_GYGL_NEW_WSJC_JCRCB group by xn  order by  xn";
		return dao.getListNotOut(sql, new String[]{});
	}

	/** 
	 * @描述:根据学年学期获取文明寝室学生符合条件的名单(这里用一句话描述这个方法的作用)
	 * @作者：lgx[工号：1553]
	 * @日期：2018-5-10 上午11:43:16
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xn
	 * @param xq
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getWmqsxsMd(String xn, String xq) {
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (select t.*,");
		sql.append(" ( select round(avg(a.fs),2)  from xg_gygl_new_wsjc_12688_xsfsb a ");
		sql.append(" left join XG_GYGL_NEW_WSJC_JCRCB b on a.rcid = b.guid ");
		sql.append(" where xn = ? and xq = ? and b.pfjb='yj'  and a.xh=t.xh) yjpjf,");
		sql.append(" ( select round(avg(a.fs),2)  from xg_gygl_new_wsjc_12688_xsfsb a ");
		sql.append(" left join XG_GYGL_NEW_WSJC_JCRCB b on a.rcid = b.guid ");
		sql.append(" where xn = ? and xq = ?  " +
				" and b.pfjb='xj' and a.xh=t.xh) xjpjf, "); 
		sql.append(" (select count(0) from  XG_WJCF_WJCFB a where a.xh=t.xh) wjcfcs "); 
		sql.append(" from  ( select xh,avg(fs) pjfs,xn,xq from ( select xh, sum(fs) fs, mc, xn, xq  from "); 
		sql.append(" (select a.xh, a.fs, b.mc, b.xn, b.xq from xg_gygl_new_wsjc_12688_xsfsb a "); 
		sql.append(" left join XG_GYGL_NEW_WSJC_JCRCB b on a.rcid = b.guid" +
				" where xn = ? and xq = ? ) "); 
		sql.append(" group by xh, mc, xn, xq ) a group by xh,xn,xq) t ");
		sql.append("  where t.pjfs >= 90) where wjcfcs=0 ");
		return dao.getListNotOut(sql.toString(), new String[]{xn,xq,xn,xq,xn,xq});
	}

	/**
	 * @throws Exception  
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：lgx[工号：1553]
	 * @日期：2018-5-10 下午01:49:02
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xsmcList
	 * @param user
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean insertXsmc(List<HashMap<String, String>> xsmcList, User user) throws Exception {
		List<String> sqlArr =null;
		DAO dao = DAO.getInstance();
		boolean flag = false;
		sqlArr = new ArrayList<String>();
		for (HashMap<String, String> map : xsmcList) {
			String sql = "delete from  xg_gygl_new_wmqsxsmdb where  ";
			//sql += " xh='" + map.get("xh") + "'";
			sql += " xn='" + map.get("xn") + "'";
			sql += " and xq='" + map.get("xq") + "'";
			sqlArr.add(sql);
			break;
		}
		flag = dao.checkBatch(dao.runBatch(sqlArr != null ? sqlArr.toArray(new String[0]) : new String[] {}));
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		String lrsj = df.format(new Date());
		if( flag){
			sqlArr = new ArrayList<String>();
			for (HashMap<String, String> map : xsmcList) {
				String sql = "insert into  xg_gygl_new_wmqsxsmdb (xh,xn,xq,sjly,xjfs,yjfs,lrr,lrsj) values ( ";
				sql += " '" + map.get("xh") + "'";
				sql += " , '" + map.get("xn") + "'";
				sql += " , '" + map.get("xq") + "'";
				sql += " , '" + "2" + "'";
				sql += " , '" + map.get("xjpjf") + "'";
				sql += " , '" + map.get("yjpjf") + "'";
				sql += " , '" + user.getUserName() + "'";
				sql += " , '" + lrsj + "'";
				sql += ")";
				sqlArr.add(sql);
			}
			flag = dao.checkBatch(dao.runBatch(sqlArr != null ? sqlArr.toArray(new String[0]) : new String[] {}));
		}
		
		return flag;
	}

}
