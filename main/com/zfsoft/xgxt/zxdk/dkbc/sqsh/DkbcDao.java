/**
 * @部门:学工产品事业部
 * @日期：2014-11-12 上午09:34:45 
 */  
package com.zfsoft.xgxt.zxdk.dkbc.sqsh;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;


/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 屈朋辉[工号:445]
 * @时间： 2014-11-12 上午09:34:45 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class DkbcDao extends SuperDAOImpl<DkbcModel> {

	private static final String YSH = "ysh";
	
	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		super.setTableName("xg_zxdk_dkbcsqb");
		super.setClass(DkbcModel.class);
		super.setKey("id");
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(DkbcModel t)
			throws Exception {
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(DkbcModel t, User user)
			throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");		
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select * from (");
		sql.append("select t1.*,t2.xm,t2.xydm,t2.xymc,t2.zydm,t2.zybj,t2.zybjmc,t2.sydm1 sydm,t2.symc1 symc,");
		sql.append("decode(t1.shzt,'0','未提交','1','通过','2','不通过','3','退回','5','审核中',t1.shzt) shztmc, ");
		sql.append("t2.zymc,t2.bjdm,t2.bjmc,t2.nj ");
		sql.append("from xg_zxdk_dkbcsqb t1 left join view_xsjbxx t2 on t1.xh = t2.xh ");
		sql.append(") t where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		
		return getPageList(t, sql.toString(), inputV);
	}
	
	
	
	public List<HashMap<String, String>> getAudingList(DkbcModel t, User user)
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
		sql.append("c.gwz from (select t1.*,t2.xm,t2.xydm,t2.xymc,t2.zydm,");
		sql.append("t2.zymc,t2.bjdm,t2.bjmc,t2.nj,t2.zybj,t2.zybjmc,t2.sydm1 sydm,t2.symc1 symc,b.shzt as lczt,");
		sql.append("b.shsj,b.gwid as xtgwid,b.zd2 as rddc,b.guid as shid ");
		sql.append("from xg_zxdk_dkbcsqb t1 ");
		sql.append("left join view_xsjbxx t2 on t1.xh = t2.xh ");
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
	
    

	@Override
	public DkbcModel getModel(String id) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (");
		sql.append("select t1.*,t3.mc yjxfmc,t4.mc dclbmc,t10.yhmc");
		sql.append(" from xg_zxdk_dkbcsqb t1 left join view_xsjbxx t2 on t1.xh = t2.xh ");
		sql.append(" left join xg_zxdk_xfdmb t3 on t1.yjxf=t3.dm ");
		sql.append(" left join dmk_yh t10 on t1.yhdm=t10.yhdm ");
		sql.append(" left join xg_zxdk_dclbdmb t4 on t1.dclb=t4.dm ) t where id=?");
		return getModel(sql.toString(),new String[]{id});
	}

	/**按学号学年查询是否有申请 */
	
	public String getCountByXhAndXn(DkbcModel djjdForm) {
		
		String sql = "select count(1) count from xg_zxdk_dkbcsqb where xh = ?";
		
		return dao.getOneRs(sql, new String[]{djjdForm.getXh()}, "count");
	}
    
	/**
	 * 
	 * @描述:获取银行List
	 * @作者 ChenQ[工号：856]
	 * @日期：2015-9-6 下午02:32:27
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getYhList(){
		StringBuilder sql = new StringBuilder();
		sql.append("select yhdm dm,yhmc mc from dmk_yh order by yhdm");
		return dao.getListNotOut(sql.toString(), new String[]{});
	}
	
	/**
	 * 
	 * @描述:获取代偿类别List
	 * @作者 ChenQ[工号：856]
	 * @日期：2015-9-6 下午02:32:27
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getDclbList(){
		StringBuilder sql = new StringBuilder();
		sql.append("select dm,mc from xg_zxdk_dclbdmb order by dm");
		return dao.getListNotOut(sql.toString(), new String[]{});
	}
	
	
	/**
	 * 
	 * @描述:获取学费List
	 * @作者 ChenQ[工号：856]
	 * @日期：2015-9-6 下午02:32:27
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getXfList(){
		StringBuilder sql = new StringBuilder();
		sql.append("select dm,mc from xg_zxdk_xfdmb order by dm");
		return dao.getListNotOut(sql.toString(), new String[]{});
	}
	
	//华中师范大学贷款类型联动查询助学贷款结果表或者生源地贷款结果表的数据
	public HashMap<String, String> getHzsfDxDklxChange(String xh,String dklx,String xn){
		StringBuilder sql = new StringBuilder();
		if(dklx.equals("生源地贷款")){
			sql.append(" select t.dkje, t.dkyh, t.htbh, t.dkkssj,t.dkqx,");
			sql.append(" to_char(to_date(t.dkkssj, 'yyyy-MM-dd hh24:mi:ss'), 'yyyy') n,");
			sql.append(" to_char(to_date(t.dkkssj, 'yyyy-MM-dd hh24:mi:ss'), 'mm') y,");
			sql.append("  to_char(to_date(t.dkkssj, 'yyyy-MM-dd hh24:mi:ss'), 'dd') d");
			sql.append(" from xg_zxdk_syddk t");
			sql.append(" where t.xn = ?");
			sql.append(" and t.xh = ?");
			sql.append(" ");
		}else if(dklx.equals("国家助学贷款")){
			sql.append(" select t.dkje,");
			sql.append(" t.yhmc dkyh,");
			sql.append(" t.htbh,");
			sql.append(" to_char(to_date(t.sqsj, 'yyyy-MM-dd hh24:mi:ss'), 'yyyy-mm-dd') dkkssj,");
			sql.append(" to_char(to_date(t.sqsj, 'yyyy-MM-dd hh24:mi:ss'), 'yyyy') n,");
			sql.append(" to_char(to_date(t.sqsj, 'yyyy-MM-dd hh24:mi:ss'), 'mm') y,");
			sql.append("  to_char(to_date(t.sqsj, 'yyyy-MM-dd hh24:mi:ss'), 'dd') d,");
			sql.append(" t.dkqx");
			sql.append(" from xg_zxdk_xydkjgb t");
			sql.append(" where t.xn = ?");
			sql.append(" and t.xh = ?");
		}else{
			return null;
		}
		return dao.getMapNotOut(sql.toString(), new String[]{xn,xh});
	}
	
	public String produceDkjssj(String dkkssj,String dkqx,String dklx){
		if(dklx.equals("国家助学贷款")){
			dkqx = Integer.parseInt(dkqx)*12+"";
		}
		StringBuilder sql = new StringBuilder();
		sql.append(" select to_char(add_months(to_date(?,'yyyy-MM-dd hh24:mi:ss'),?),'yyyy-mm-dd') dkjssj from dual ");
		return dao.getOneRs(sql.toString(), new String[]{dkkssj,dkqx}, "dkjssj");
	}
 	
}
