/**
 * @部门:学工产品事业部
 * @日期：2014-5-26 下午03:22:39 
 */  
package com.zfsoft.xgxt.xljkwzdx.xlwygl.xxsbgl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张小彬[工号:1036]
 * @时间： 2014-5-26 下午03:22:39 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XxsbDao extends SuperDAOImpl<XxsbForm>{
	
	public static final String YSH = "Y";
	
	public static final String DSH = "D";
	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(XxsbForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/**
	 * 
	 * @描述:获取上报信息
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-5-30 下午01:32:08
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param sbsqid
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String ,  String> getModelMap(String sbsqid){
		String sql = "select a.* , b.* , c.* , c.zbksrq||' ~ '||c.zbjsrq as zbqzrq , z.xqmc from XG_XLJK_XLWYGL_XSSBSQB a left join view_xsjbxx b on a.xh = b.xh left join XG_XLJK_XLWYGL_ZBRCXXB c on a.sbzbid = c.zbid left join xqdzb z on a.xq = z.xqdm where a.sbsqid = ? ";
		return dao.getMapNotOut(sql, new String[]{sbsqid});	
	}
	
	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(XxsbForm t, User user)
			throws Exception {
		List<String> params = new ArrayList<String>();
		StringBuffer sql = new StringBuffer();
		if("0".equals(t.getSblx())){
			sql.append("select '0' sblxx, t1.* , a.xqmc, t2.sbsqid,t2.xh,t2.sblx,t2.sbsj,t2.sbzbid,t2.ztqk,t2.xlxsxxqk,t2.bz,t2.splcid,t2.shzt,decode(t2.shzt,")
			.append("              '0',")
			.append("              '未提交',")
			.append("              '1',")
			.append("              '通过',")
			.append("              '2',")
			.append("              '不通过',")
			.append("              '3',")
			.append("              '已退回',")
			.append("              '5',")	
			.append("              '审核中',")
			.append("              '未上报') shztmc from (select b.* from XG_XLJK_XLWYGL_ZBRCXXB b where b.zblx = '0' ");
			if (!StringUtil.isNull(t.getXn())) {
				params.add(t.getXn());
				sql.append(" and b.xn=? ");
			}
			if (!StringUtil.isNull(t.getXq())) {
				params.add(t.getXq());
				sql.append(" and b.xq=? ");
			}
			sql.append(") t1   ")
			.append("  left join (select a.*")
			.append("               from XG_XLJK_XLWYGL_XSSBSQB a")
			.append("              where a.sblx = '0'")
			.append("                and a.xh = '" + user.getUserName() + "') t2")
			.append("    on t1.zbid = t2.sbzbid left join xqdzb a on t1.xq = a.xqdm");
		}else if("1".equals(t.getSblx())){
			sql.append("select '1' sblxx, t1.* , a.xqmc, t2.sbsqid,t2.xh,t2.sblx,t2.sbsj,t2.sbzbid,t2.ztqk,t2.xlxsxxqk,t2.bz,t2.splcid,t2.shzt,decode(t2.shzt,")
			.append("              '0',")
			.append("              '未提交',")
			.append("              '1',")
			.append("              '通过',")
			.append("              '2',")
			.append("              '不通过',")
			.append("              '3',")
			.append("              '已退回',")
			.append("              '5',")	
			.append("              '审核中',")
			.append("              '未上报') shztmc from (select b.* from XG_XLJK_XLWYGL_ZBRCXXB b where b.zblx = '1' ");
			if (!StringUtil.isNull(t.getXn())) {
				params.add(t.getXn());
				sql.append(" and b.xn=? ");
			}
			if (!StringUtil.isNull(t.getXq())) {
				params.add(t.getXq());
				sql.append(" and b.xq=? ");
			}
			sql.append(") t1   ")
			.append("  left join (select a.*")
			.append("               from XG_XLJK_XLWYGL_XSSBSQB a")
			.append("              where a.sblx = '1'")
			.append("                and a.xh = '" + user.getUserName() + "') t2")
			.append("    on t1.zbid = t2.sbzbid left join xqdzb a on t1.xq = a.xqdm");
		}else if("2".equals(t.getSblx())){
			sql.append("select '2' sblxx, a.*, z.xqmc " +
					",decode(a.shzt," +
					"              '0'," +
					"              '未提交'," +
					"              '1'," +
					"              '通过'," +
					"              '2'," +
					"              '不通过'," +
					"              '3'," +
					"              '已退回'," +
					"              '5'," +
					"			   '审核中'," +
					"              '未上报') shztmc, " + " b.xm " +
					"from XG_XLJK_XLWYGL_XSSBSQB a left join view_xsjbxx b on a.xh = b.xh " +
					"left join xqdzb z on a.xq = z.xqdm " +
					"where a.xh = '" + user.getUserName() + "' and a.sblx = '2'");
			if (!StringUtil.isNull(t.getXn())) {
				params.add(t.getXn());
				sql.append(" and a.xn=? ");
			}
			if (!StringUtil.isNull(t.getXq())) {
				params.add(t.getXq());
				sql.append(" and a.xq=? ");
			}
		}
		
		return getPageList(t, sql.toString(), params
				.toArray(new String[] {}));
	}

	
	//****************************************** 审核*************************************************/
	
	public List<HashMap<String, String>> getSHPageList(XxsbForm t, User user)
		throws Exception {

		//生成高级查询相关条件、条件值 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
			String searchTjByUser = SearchService.getSearchTjByUser(user, "t2", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuffer sql = new StringBuffer();
		sql.append("select t2.* from (select t1.*,row_number() over(partition by sbsqid order by shsj desc) rn  from ")
		.append("(select a.* , '[' || b.mc || ':' || decode(a.shzt,'0','待审核','1','通过','2','不通过','3','退回','4','需重审','5','审核中',a.shzt) || ']' shztmc from (select a.sbsqid,")
		.append("       a.xh,")
		.append("       a.sblx,")
		.append("       decode(a.sblx , '0' , '班级心理周报' , '1' , '公寓心理周报' , '2' , '平时情况上报' , '') sblxmc,")
		.append("       a.sbsj,")
		.append("       a.sbzbid,")
		.append("       a.splcid,")
		.append("       a.xn,")
		.append("       a.xq,")
		.append("       z.xqmc,")
		.append("       b.xm,")
		.append("       b.nj,")
		.append("       b.xydm,")
		.append("       b.xymc,")
		.append("       b.zydm,")
		.append("       b.zymc,")
		.append("       b.bjdm,")
		.append("       b.bjmc,")
		.append("       c.zblx,")
		.append("       c.zbzc,")
		.append("       c.zbksrq,")
		.append("       c.zbjsrq,")
		.append("		d.gwid as xtgwid,")
		.append("       d.shzt,")
		.append("       d.shsj,")
		.append("       d.guid as shid ")
		.append("  from XG_XLJK_XLWYGL_XSSBSQB a")
		.append("  left join view_xsjbxx b")
		.append("    on a.xh = b.xh")
		.append("  left join xqdzb z on a.xq = z.xqdm ")
		.append("  left join XG_XLJK_XLWYGL_ZBRCXXB c")
		.append("    on a.sbzbid = c.zbid ")
		.append("  left join xg_xtwh_shztb d on a.sbsqid = d.ywid")
		.append(" ) a  left join xg_xtwh_spgw b on a.xtgwid = b.id ")
		.append(" where a.xtgwid in ")
		.append(" (select spgw from xg_xtwh_spgwyh where spyh = '")
		.append(user.getUserName());
		
		if(DSH.equals(t.getType()))
			sql.append("') and a.shzt in ('0', '4')) t1) t2 where rn = 1  ");
		else if(YSH.equals(t.getType()))
			sql.append("') and a.shzt not in ('0', '4')) t1) t2 where rn = 1  ");
		
		sql.append(searchTjByUser)
		.append(" ")
		.append(searchTj);
		
		return  getPageList(t, sql.toString(), inputV);
		}
	
	
	
	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		setClass(XxsbForm.class);
		setKey("sbsqid");
		setTableName("XG_XLJK_XLWYGL_XSSBSQB");
	}
	
	
	public HashMap<String,String> getXnXqmc(String zbid){
		StringBuffer sql = new StringBuffer();
		sql.append(" select a.*,b.xqmc from XG_XLJK_XLWYGL_ZBRCXXB a left join xqdzb b on a.xq = b.xqdm where zbid = ? ");
		
		return dao.getMapNotOut(sql.toString(), new String[]{zbid});
	}

}
