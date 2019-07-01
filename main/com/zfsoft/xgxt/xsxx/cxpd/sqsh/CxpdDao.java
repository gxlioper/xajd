/**
 * @部门:学工产品事业部
 * @日期：2014-11-12 上午09:34:45 
 */  
package com.zfsoft.xgxt.xsxx.cxpd.sqsh;

import java.util.HashMap;
import java.util.List;

import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/**
 * 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: 操行评定-申请审核 
 * @作者： 屈朋辉[工号:445]
 * @时间： 2015-1-14 下午03:55:33 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class CxpdDao extends SuperDAOImpl<CxpdModel> {

	private static final String YSH = "ysh";
	
	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		super.setTableName("xg_xsxx_cxpdsqb");
		super.setClass(CxpdModel.class);
		super.setKey("id");
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(CxpdModel t)
			throws Exception {
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(CxpdModel t, User user)
			throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");		
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select * from (");
		sql.append("select t1.*,t2.xm,t2.xydm,t2.xymc,t2.zydm,t5.xqmc,");
		sql.append("decode(t1.shzt,'0','未提交','1','通过','2','不通过','3','退回','5','审核中',t1.shzt) shztmc, ");
		sql.append("t2.zymc,t2.bjdm,t2.bjmc,t2.nj,t3.cxdjmc ");
		sql.append("from xg_xsxx_cxpdsqb t1 left join view_xsjbxx t2 on t1.xh = t2.xh ");
		sql.append("left join xsxx_cxdjdmb t3 on t1.cxdj = t3.cxdjdm ");
		sql.append("left join xqdzb t5 on t1.xq=t5.xqdm ");
		sql.append(") t where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		
		return getPageList(t, sql.toString(), inputV);
	}
	
	
	
	public List<HashMap<String, String>> getAudingList(CxpdModel t, User user)
			throws Exception {

		// 生成高级查询相关条件、条件值
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t2","xydm", "bjdm");
		String shgwzByUser = SearchService.getShgwzByUser(user, "t2", "xydm","bjdm");
		String[] inputV = SearchService.getTjInput(t.getSearchModel());

		StringBuilder sql = new StringBuilder();
		sql.append("select t2.* from (select t1.*,");
		sql.append("row_number() over(partition by id order by shsj desc) rn ");
		sql.append("from (select b.*,'[' || c.mc || ':' || ");
		sql.append("decode(b.lczt,'0','待审核','1','通过','2','不通过','3','退回','4','需重审','5','审核中',b.lczt) || ']' shztmc,");
		sql.append("c.gwz from (select t1.*,t2.xm,t2.xydm,t2.xymc,t2.zydm,t3.cxdjmc,t5.xqmc,");
		sql.append("t2.zymc,t2.bjdm,t2.bjmc,t2.nj,b.shzt as lczt,");
		sql.append("b.shsj,b.gwid as xtgwid,b.zd2 as rddc,b.guid as shid ");
		sql.append("from xg_xsxx_cxpdsqb t1 ");
		sql.append("left join view_xsjbxx t2 on t1.xh = t2.xh ");
		sql.append("left join xsxx_cxdjdmb t3 on t1.cxdj = t3.cxdjdm ");
		sql.append("left join xqdzb t5 on t1.xq=t5.xqdm ");
		sql.append("left join xg_xtwh_shztb b on t1.id = b.ywid ");
		sql.append(") b left join xg_xtwh_spgw c on b.xtgwid = c.id where b.xtgwid in");
		sql.append("(select spgw from xg_xtwh_spgwyh where spyh = '")
		   .append(user.getUserName()).append("')  ");

		if (YSH.equals(t.getShzt())) {
			sql.append("and b.lczt not in ('0', '4')) t1 ) t2 where rn = 1 ");
		} else {
			sql.append(" and b.lczt in ('0', '4')) t1) t2 where rn = 1 ");
		}

		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		sql.append(shgwzByUser);

		return super.getPageList(t, sql.toString(), inputV);
	}

	public CxpdModel getModel(String keyValue) throws Exception {
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select t1.*,t3.cxdjmc,t5.xqmc from xg_xsxx_cxpdsqb t1 ");
		sql.append("left join xsxx_cxdjdmb t3 on t1.cxdj = t3.cxdjdm ");
		sql.append("left join xqdzb t5 on t1.xq=t5.xqdm where t1.id=?");
		
		return super.getModel(sql.toString(), new String[]{keyValue});
	}
	
	
	public String getCount(CxpdModel t){
		
		String[] params = null;
		StringBuilder sql = null;
		
		if("13943".equalsIgnoreCase(Base.xxdm)) {
			sql = new StringBuilder("select count(1) c from xg_xsxx_cxpdsqb where xh = ? and xn=?");
			
			if (!StringUtil.isNull(t.getId())){
				sql.append(" and id <> ?");
				params = new String[]{t.getXh(),t.getXn(),t.getId()};
			} else {
				params = new String[]{t.getXh(),t.getXn()};
			}
		}else {
			sql = new StringBuilder("select count(1) c from xg_xsxx_cxpdsqb where xh = ? and xn=? and xq=?");
			
			if (!StringUtil.isNull(t.getId())){
				sql.append(" and id <> ?");
				params = new String[]{t.getXh(),t.getXn(),t.getXq(),t.getId()};
			} else {
				params = new String[]{t.getXh(),t.getXn(),t.getXq()};
			}
		}
		
		return dao.getOneRs(sql.toString(), params , "c");
	}
}
