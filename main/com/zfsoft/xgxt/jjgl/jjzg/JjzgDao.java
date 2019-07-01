/**
 * @部门:学工产品事业部
 * @日期：2014-8-18 上午11:17:52 
 */  
package com.zfsoft.xgxt.jjgl.jjzg;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

/** 
 * @类功能描述: 家教资格
 * @作者： 屈朋辉 [工号:445]
 * @时间： 2014-8-18 上午11:17:52 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class JjzgDao extends SuperDAOImpl<JjzgForm> {

	private static final String DSH = "dsh";
	private static final String YSH = "ysh";
	
	@Override
	protected void setTableInfo() {
		super.setTableName("XSGGFW_JJFW_XSKGZGSQB");
		super.setKey("sqid");
		super.setClass(JjzgForm.class);
	}

	@Override
	public List<HashMap<String, String>> getPageList(JjzgForm t)
			throws Exception {
		return null;
	}

	@Override
	public List<HashMap<String, String>> getPageList(JjzgForm t, User user)
			throws Exception {
		
		//生成高级查询相关条件、条件值 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
 		String searchTjByUser = SearchService.getSearchTjByUser(user, "t2", "xydm", "bjdm");	
 		String shgwzByUser = SearchService.getShgwzByUser(user, "t2","xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		
		StringBuilder sql = new StringBuilder();
		sql.append("select t2.* from (select t1.*,");
		sql.append("row_number() over(partition by sqid order by shsj desc) rn ");
		sql.append("from (select b.*,'[' || c.mc || ':' || ");
		sql.append("decode(b.shzt,'0','待审核','1','通过','2','不通过','3','退回','4','需重审','5','审核中',b.shzt) || ']' shztmc,");
		sql.append("c.gwz from (select t1.sqid,t1.xh,t1.splcid,t1.jjnjdm,t1.jjkma,t1.jjkmb,");
		sql.append("t1.jjkmc,t1.lxdh,t1.sqsj,t1.rddj,t1.jjpxqk,m.nj,m.xb,m.xm,");
		sql.append("m.xydm,m.zydm,m.bjdm,b.zd3 as dcmc,m.xymc,m.zymc,m.bjmc,b.shzt,");
		sql.append("b.shsj,b.gwid as xtgwid,b.zd2 as rddc,b.guid as shid ");
		sql.append("from XSGGFW_JJFW_XSKGZGSQB t1 left join view_xsjbxx m on t1.xh = m.xh ");
		sql.append("left join xg_xtwh_shztb b on t1.sqid = b.ywid where 1 = 1) b ");
		sql.append("left join xg_xtwh_spgw c on b.xtgwid = c.id where b.xtgwid in");
		sql.append("(select spgw from xg_xtwh_spgwyh where spyh = '").append(user.getUserName()).append("')  ");
		
		if (DSH.equals(t.getShzt())){
			sql.append(" and b.shzt in ('0', '4')) t1) t2 where rn = 1 ");
		} else if (YSH.equals(t.getShzt())){
			sql.append("and b.shzt not in ('0', '4')) t1 ) t2 where rn = 1 ");
		}
		
		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		sql.append(shgwzByUser);
		
		return getPageList(t, sql.toString(), inputV);
	}
	
	

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#runInsert(java.lang.Object)
	 */
	
	@Override
	public boolean runInsert(JjzgForm t) throws Exception {
		t.setSqid(t.getId());
		return super.runInsert(t);
	}

	
	public JjzgForm getModel(String xh) throws Exception{
		
		StringBuilder sql = new StringBuilder();
		sql.append("select t1.*,t2.jjnjmc from ");
		sql.append("XG_JJGL_JJLSJGB t1 ,XSGGFW_JJFW_JJNJDMB t2 ");
		sql.append("where t1.jjnj = t2.jjnjdm ");
		sql.append("and t1.xh=? and rownum=1");
		
		return super.getModel(sql.toString(), new String[]{xh});
	}
	
	/**
	 * 
	 * @描述:查询家教老师信息
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-8-29 下午12:42:28
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * @throws Exception
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String , String> getModelMap(String xh) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append("select t1.*, t6.xm , t6.xydm , t6.xymc, t6.bjdm , t6.bjmc , t6.zymc , t6.zydm, t6.nj ,t6.xb, t2.jjnjmc from ");
		sql.append("XG_JJGL_JJLSJGB t1 ,XSGGFW_JJFW_JJNJDMB t2,");
		sql.append("view_xsjbxx t6 where t1.jjnj = t2.jjnjdm ");
		sql.append("and t1.xh = t6.xh ");
		sql.append("and t1.xh=? and rownum=1");
		
		return dao.getMapNotOut(sql.toString(), new String[]{xh});
		
	}
	
	/**
	 * 
	 * @描述:获取家教经历列表
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-9-2 下午03:01:51
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String , String>> getJJjlList(String xh) throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append(" 	select t1.* , t2.sqr , t3.jjxkmc , t4.jjnjmc ,  t5.pjzs , decode(t5.pjzs , '5','非常满意','4','较满意','3','满意','2','一般','1','差','') as pjzsmc from XSGGFW_JJFW_XSJJXQSQB t1 ");
		sql.append("	left join XSGGFW_JJFW_JZJJXQSQB t2 on t1.xqid = t2.xqid ");
		sql.append(" 	left join XSGGFW_JJFW_JJXKDMB t3 on t2.jjxkdm = t3.jjxkdm ");
		sql.append(" 	left join XSGGFW_JJFW_JJNJDMB t4 on t2.jjnjdm = t4.jjnjdm ");
		sql.append("	left join XSGGFW_JJFW_JZPJB t5 on t1.xqid = t5.xqid ");
		sql.append("	where t1.zt = '1' and t1.shzt = '1' and t2.jjzt = '4' and t1.xh = ? order by t1.sqsj desc");
		return dao.getListNotOut(sql.toString(), new String[]{xh});
	}
	
}
