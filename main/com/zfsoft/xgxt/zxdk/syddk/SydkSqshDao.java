/**
 * @部门:学工产品事业部
 * @日期：2015-7-1 下午04:39:09 
 */  
package com.zfsoft.xgxt.zxdk.syddk;

import java.util.HashMap;
import java.util.List;

import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： ChenQ[工号:856]
 * @时间： 2015-7-1 下午04:39:09 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class SydkSqshDao extends SuperDAOImpl<SydkSqshModel> {
	private static final String YSH = "ysh";
	
	@Override
	public List<HashMap<String, String>> getPageList(SydkSqshModel t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	
	
	public List<HashMap<String, String>> getPageList(SydkSqshModel t, User user)
			throws Exception {

		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t",
				"xydm", "bjdm");

		StringBuilder sql = new StringBuilder();

		sql.append("select * from (");
		sql.append("select t1.*,t2.xm,t2.xydm,t2.xymc,t2.zydm,t2.zymc,t2.bjdm,t2.bjmc,t2.nj,t3.yhmc,t2.sfzh,t2.xz,t2.sjhm,t2.xmsj sjdh,t2.dzyx,");
		sql.append(" t2.zybj,t2.zybjmc,x1.sydm,x1.symc,");
		//西安科技大学个性化
		if(Base.xxdm.equals("10704")){
			sql.append("t2.xb,t2.bysj,t2.jtdz,t2.qqhm,decode(t4.rddc,'1','特别困难','2','一般困难','') kncd,t5.cyxm,t5.cylxdh,t6.fdyxm,t6.fdylxdh,");
		}
		sql.append("decode(t1.shzt,'0','未提交','1','通过','2','不通过','3','退回','5','审核中',t1.shzt) shztmc ");
		//西安科技大学个性化
		if(Base.xxdm.equals("10704")){
			sql.append("from xg_zxdk_sydksqb t1 left join view_xsxxb t2 on t1.xh = t2.xh left join dmk_yh  ");
		}else{			
			sql.append("from xg_zxdk_sydksqb t1 left join view_xsbfxx t2 on t1.xh = t2.xh ");
		}
		sql.append(" left join XG_XTWH_SYBJGLB x on t2.bjdm = x.bjdm ");
		sql.append(" left join XG_XTWH_SYDMB x1 on x.sydm = x1.sydm ");
		sql.append(" left join dmk_yh t3 on t3.yhdm=t1.yhdm  ");
		//西安科技大学个性化
		if(Base.xxdm.equals("10704")){
			sql.append(" left join xg_xszz_new_knsjgb t4 on t1.xh = t4.xh");
			sql.append(" left join (select * from (select xh,cyxm,cylxdh,row_number() over(partition by xh order by cyxm asc) rn from xg_xszz_new_jtcyb group by xh,cyxm,cylxdh) where rn = 1) t5 on t1.xh = t5.xh");
			sql.append(" left join (select xh,bjdm,zgh,xm fdyxm,lxdh fdylxdh from (select a.xh, b.bjdm, c.zgh, d.xm, d.lxdh, row_number() over(partition by a.xh order by d.xm) rn from xg_zxdk_sydksqb a left join xsxxb b on a.xh = b.xh left join fdybjb c on b.bjdm = c.bjdm left join fdyxxb d  on c.zgh = d.zgh) where rn = 1) t6 on t1.xh = t6.xh");
		}
		sql.append(" ) t where 1=1");
		sql.append(searchTjByUser);
		sql.append(searchTj);

		return getPageList(t, sql.toString(), inputV);
	}
    
	
	@Override
	public SydkSqshModel getModel(String id) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("select a.*,b.yhmc from xg_zxdk_sydksqb a left join dmk_yh b  on a.yhdm=b.yhdm");
		sql.append(" where id=?");
		return getModel(sql.toString(),new String[]{id});
	}



	public List<HashMap<String, String>> getAudingList(SydkSqshModel t, User user)
			throws Exception {

		// 生成高级查询相关条件、条件值
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t2",
				"xydm", "bjdm");
		String shgwzByUser = SearchService.getShgwzByUser(user, "t2", "xydm",
				"bjdm");
		String[] inputV = SearchService.getTjInput(t.getSearchModel());

		StringBuilder sql = new StringBuilder();
		sql.append("select t2.* from (select t1.*,");
		sql.append("row_number() over(partition by id order by shsj desc) rn ");
		sql.append("from (select b.*,'[' || c.mc || ':' || ");
		sql.append("decode(b.lczt,'0','待审核','1','通过','2','不通过','3','退回','4','需重审','5','审核中',b.lczt) || ']' shztmc,");
		sql.append("c.gwz from (select t1.*,t2.xm,t2.xydm,t2.xymc,t2.zydm,t2.sydm1 sydm,t2.symc1 symc,");
		sql.append("t2.zymc,t2.bjdm,t2.bjmc,t2.nj,t2.zybj,t2.zybjmc,b.shzt as lczt,");
		sql.append("b.shsj,b.gwid as xtgwid,b.zd2 as rddc,b.guid as shid ");
		sql.append("from xg_zxdk_sydksqb t1 ");
		sql.append("left join view_xsjbxx t2 on t1.xh = t2.xh ");
		sql.append("left join xg_xtwh_shztb b on t1.id = b.ywid ");
		sql
				.append(") b left join xg_xtwh_spgw c on b.xtgwid = c.id where b.xtgwid in");
		sql.append("(select spgw from xg_xtwh_spgwyh where spyh = '").append(
				user.getUserName()).append("')  ");

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
	
	/**
	 * 
	 * @描述:银行List
	 * @作者：ChenQ[工号：856]
	 * @日期：2015-7-2 上午10:32:48
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getYhList(){
		StringBuilder sql = new StringBuilder();
		sql.append("select yhdm,yhmc from dmk_yh order by yhdm");
		return dao.getListNotOut(sql.toString(), new String[]{});
	}
	
	
	public boolean isExitsByXhAndXn(SydkSqshModel t){
		boolean flag = false;
		StringBuilder sql = new StringBuilder("select count(1) c from xg_zxdk_sydksqb where xh = ? and xn = ?");
		String[] params = null;
		params = new String[]{t.getXh(),t.getXn()};
		String sqnum= dao.getOneRs(sql.toString(), params, "c");
		flag = Integer.parseInt(sqnum)>0?true:false;
		if(flag){
			return flag;
		}else{
			String tsql = " select count(1) c from xg_zxdk_syddk where xh = ? and xn = ? ";
			String jgnum= dao.getOneRs(tsql, params, "c");
			flag = Integer.parseInt(jgnum)>0?true:false;
			return flag;
		}
	}
	
	public List<HashMap<String,String>> getShxx(SydkSqshModel model){
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (select a.*,b.xh,rank() over(partition by gwid order by shsj desc) rn ");
		sql.append(" from xg_xtwh_shztb a left join xg_xtwh_spbz b on a.lcid=b.splc and a.gwid=b.spgw ");
		sql.append("  where ywid = ? ) where rn = 1 order by to_number(xh) ");
		return dao.getListNotOut(sql.toString(), new String[]{model.getId()});
	}
	@Override
	protected void setTableInfo() {
		super.setKey("id");
		super.setTableName("xg_zxdk_sydksqb");
		super.setClass(SydkSqshModel.class);
	}
	
	
	 /**
	 * @throws Exception 
	  * 
	  * @描述:用于审核时删除已有的学年数据
	  * @作者：yxy[工号：1206]
	  * @日期：2016-10-18 上午11:05:12
	  * @修改记录: 修改者名字-修改日期-修改内容
	  * @param xn
	  * @param xh
	  * @return
	  * boolean 返回类型 
	  * @throws
	  */
	public boolean DelSydJgb(String xn,String xh) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" delete from xg_zxdk_syddk where xn = ? and xh = ?");
		return dao.runUpdate(sql.toString(), new String[]{xn,xh});
	}

}
