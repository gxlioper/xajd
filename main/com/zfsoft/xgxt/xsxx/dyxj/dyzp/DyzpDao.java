/**
 * @部门:学工产品事业部
 * @日期：2015-6-19 上午11:09:07 
 */  
package com.zfsoft.xgxt.xsxx.dyxj.dyzp;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.xsxx.dyxj.zpjg.ZpjgModel;

/** 
 * @类功能描述: 德育自评
 * @作者： 屈朋辉 [工号:445]
 * @时间： 2015-6-19 上午11:09:07 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class DyzpDao extends SuperDAOImpl<DyzpModel> {

	private static final String YSH = "ysh";
	
	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		super.setClass(DyzpModel.class);
		super.setTableName("xg_xsxx_dyxj_dypysqb");
		super.setKey("id");
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(DyzpModel t)
			throws Exception {
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(DyzpModel t, User user)
			throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");		
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select * from (");
		sql.append("select t1.*,t2.xm,t2.xydm,t2.xymc,t2.zydm,t5.xqmc,");
		sql.append("decode(t1.shzt,'0','未提交','1','通过','2','不通过','3','退回','5','审核中',t1.shzt) shztmc, ");
		sql.append("t2.zymc,t2.bjdm,t2.bjmc,t2.nj,t3.djmc ");
		sql.append("from xg_xsxx_dyxj_dypysqb t1 left join view_xsjbxx t2 on t1.xh = t2.xh ");
		sql.append("left join xg_xsxx_dyxj_djdmb t3 on t1.djdm = t3.djdm ");
		sql.append("left join xqdzb t5 on t1.xq=t5.xqdm ");
		sql.append(") t where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		
		return getPageList(t, sql.toString(), inputV);
	}
	
	public List<HashMap<String, String>> getAudingList(DyzpModel t, User user)
			throws Exception {
		
		// 生成高级查询相关条件、条件值
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t2","xydm", "bjdm");
		String shgwzByUser = SearchService.getShgwzByUser(user, "t2", "xydm","bjdm");
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		
		StringBuilder sql = new StringBuilder();
		sql.append("select t2.* from (select t1.*,");
		sql.append(" t1.sjdj sjdjmc, ");
		sql.append("row_number() over(partition by id order by shsj desc) rn ");
		sql.append("from (select b.*,'[' || c.mc || ':' || ");
		sql.append("decode(b.lczt,'0','待审核','1','通过','2','不通过','3','退回','4','需重审','5','审核中',b.lczt) || ']' shztmc,");
		sql.append("c.gwz from (select t1.*,t2.xm,t2.xydm,t2.xymc,t2.zydm,t3.djmc,");
		sql.append("t2.zymc,t2.bjdm,t2.bjmc,t2.nj,t4.xqmc,b.shzt as lczt,");
		sql.append("b.shsj,b.gwid as xtgwid,b.zd2 as rddc,b.guid as shid, ");	
		sql.append("  t5.djmc sjdj ");	
		sql.append("from xg_xsxx_dyxj_dypysqb t1 ");
		sql.append("left join view_xsjbxx t2 on t1.xh = t2.xh ");
		sql.append("left join xg_xsxx_dyxj_djdmb t3 on t1.djdm = t3.djdm ");
		sql.append("left join xqdzb t4 on t1.xq = t4.xqdm ");
		sql.append(" left join xg_xsxx_dyxj_djdmb t5 ");
		sql.append(" on t1.pddjdm = t5.djdm ");
		sql.append(" ");
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
		//sql.append(shgwzByUser);
		
		return super.getPageList(t, sql.toString(), inputV);
	}
	
	
	public DyzpModel getModel(String keyValue) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("select t1.*,t3.djmc,t5.xqmc from xg_xsxx_dyxj_dypysqb t1 ");
		sql.append("left join xg_xsxx_dyxj_djdmb t3 on t1.djdm = t3.djdm ");
		sql.append("left join xqdzb t5 on t1.xq=t5.xqdm where t1.id=?");
		return super.getModel(sql.toString(), new String[]{keyValue});
	}
	
	
	/**查询是否已申请**/
	public String getCount(DyzpModel model){
		String sql = "select count(1) c from xg_xsxx_dyxj_dypysqb where xh=? and xn=? and xq=?";
		return dao.getOneRs(sql, new String[]{model.getXh(),model.getXn(),model.getXq()}, "c");
	}
	
	/**
	 * 德育自评审核时判断是否优秀人数大于30%
	 * 改by yxy 优秀审核人数由30%改成50%
	 */
	public boolean isOverLimit(String xn,String xq,String xh,String rs){
		StringBuilder sql = new StringBuilder();
		boolean flag = false;
		sql.append(" select");
		sql.append(" round(");
		sql.append(" ((select count(1)");
		sql.append(" from xg_xsxx_dyxj_dypyjg t");
		sql.append(" left join xg_xsxx_dyxj_djdmb t1");
		sql.append(" on t.djdm = t1.djdm");
		sql.append(" where t1.djmc = '优'");
		sql.append(" and t.xn = ?");
		sql.append(" and t.xq = ? and t.xh in (select xh from view_xsjbxx where bjdm =(select bjdm from view_xsjbxx where xh = ?)))"+"+"+Integer.parseInt(rs)+")/(select count(1) from view_xsjbxx where bjdm = (select bjdm from view_xsjbxx where xh = ?)),2) bfb from dual");
		float result  = Float.parseFloat(dao.getOneRs(sql.toString(), new String[]{xn,xq,xh,xh}, "bfb"));
		if(result >= (float)0.5){
			flag = true;
		}else{
			flag = false;
		}
		return flag;
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述: 审核通过之后删除原来结果库的冗余数据
	 * @作者：yxy[工号：1206]
	 * @日期：2016-10-20 上午09:55:20
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zpjgModel
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean delZpjg(ZpjgModel zpjgModel) throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append(" delete from xg_xsxx_dyxj_dypyjg where xn=? and xq=? and xh=?");
		return dao.runUpdate(sql.toString(), new String[]{zpjgModel.getXn(),zpjgModel.getXq(),zpjgModel.getXh()});
	}

}
