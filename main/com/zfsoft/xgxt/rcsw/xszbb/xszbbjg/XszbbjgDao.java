/**
 * @部门:学工产品事业部
 * @日期：2013-8-7 下午04:40:57 
 */  
package com.zfsoft.xgxt.rcsw.xszbb.xszbbjg;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;


/**
 * 
 * @系统名称: 学生工作管理系统
 * @模块名称: 学生证补办管理模块
 * @类功能描述: TODO(学生证补办结果) 
 * @作者：Dlq[工号:995]
 * @时间： 2013-12-18 下午04:24:54 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class XszbbjgDao extends SuperDAOImpl<XszbbjgForm> {

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		// TODO 自动生成方法存根
		super.setTableName("xg_rcsw_xszbb_xszbbjgb");
		super.setKey("bbjgid");
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(XszbbjgForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(XszbbjgForm t, User user)
			throws Exception {
		// TODO 自动生成方法存根
		
		//生成高级查询相关条件、条件值 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		if("11400".equals(Base.xxdm)){
			sql.append(" select a.* from (select t1.bbjgid,t1.xh,t1.xszbblxdm,t1.sjly,t1.sqsj,decode(t1.sflq, '1', '是', '0','否',sflq) sflq,t1.lqsj,t1.ffyh, ");
			sql.append(" t2.xm, t2.xb,t2.xymc,t2.zymc,t2.bjmc,t2.lxdh, t2.xydm,t2.zydm, t2.bjdm, t2.nj,t4.fdyxm,t4.ylbxh, ");
			sql.append(" decode(t1.sfbbhcyhk, 'y','是', 'n', '否', t1.sfbbhcyhk) sfbbhcyhk,t3.xszbblxmc ");
			sql.append(" from xg_rcsw_xszbb_xszbbjgb  t1 left join view_xsbfxx t2  on t1.xh = t2.xh ");
			sql.append(" left join xg_rcsw_xszbb_bblxwhb t3 on t1.xszbblxdm = t3.xszbblxdm " +
					" left join xsxxb t4 on t1.xh = t4.xh " +
					" where t1.xszbblxdm is not null ");
			sql.append(" ) a where 1=1   ");
		}else{
			sql.append(" select a.* from (select t1.dd,t1.sj,t1.bbjgid,t1.xh,t1.xszbblxdm,t1.sjly,t1.sqsj,decode(t1.sflq, '1', '是', '0','否',sflq) sflq,t1.lqsj,t1.ffyh, ");
			sql.append(" t2.xm, t2.xb,t2.xymc,t2.zymc,t2.bjmc,t2.lxdh, t2.xydm,t2.zydm,t2.zybj,t2.zybjmc, t2.bjdm, t2.nj,t2.xz,t2.rxrq,t2.sjhm,t2.jtdzxx,t2.sfzh, ");
			if(Base.xxdm.equals("13011")){//青岛酒店个性化
				sql.append(" t1.shwcsj,t2.jgmc,");
			}
			if("13871".equals(Base.xxdm)){
				sql.append(" bzr.lxdh bzrlxdh,bzr.zgh bzrzgh,bzr.xm bzrxm,");
			}
			sql.append(" decode(t1.sfbbhcyhk, 'y','是', 'n', '否', t1.sfbbhcyhk) sfbbhcyhk,t3.xszbblxmc,t1.ccqdz,t1.cczdz,(t1.ccqdz || '-' ||t1.cczdz) hcccqjmc ");
			sql.append(" from xg_rcsw_xszbb_xszbbjgb  t1 ");
			if(Base.xxdm.equals("13011")){//青岛酒店个性化
				sql.append(" left join view_xsxxb t2 on t1.xh = t2.xh");
			} else {
				sql.append(" left join view_xsbfxx t2  on t1.xh = t2.xh ");
			}
			sql.append(" left join xg_rcsw_xszbb_bblxwhb t3 on t1.xszbblxdm = t3.xszbblxdm ");	
			//江西航空职业技术学院
			if("13871".equals(Base.xxdm)){
				sql.append(" left join view_xg_bzrxx bzr on t2.bjdm = bzr.bjdm");
			}
			sql.append("  where t1.xszbblxdm is not null ");
			sql.append(" ) a where 1=1   ");
		}
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	
	}
	
	/**
	 * 
	 * @描述:TODO(判断学生证补办结果表中是否已经存在该学生)
	 * @作者：Dlq[工号：995]
	 * @日期：2013-12-19 上午08:39:35
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String checkExistForSave(XszbbjgForm model) {
		StringBuilder sql = new StringBuilder(
				"select count(1) num from xg_rcsw_xszbb_xszbbjgb where xh=?  ");
		String num = dao.getOneRs(sql.toString(), new String[] { model.getXh()}, "num");
		return num;

	}
	
	
	
	public boolean isCanDel(String bbjgid){
		StringBuffer sb=new StringBuffer();
		sb.append("select sjly from xg_rcsw_xszbb_xszbbjgb where bbjgid=? ");
		Map<String,String> map= dao.getMapNotOut(sb.toString(),new String[]{bbjgid});
		String sjly=map.get("sjly");
		//如果未提交才可以提交
		return sjly.equals("0")?true:false;
	}
	
	public HashMap<String, String> getBbjg(String bbjgid){
		StringBuffer sb=new StringBuffer();
		sb.append("select a.xh xh, xsxx.xm xm from xg_rcsw_xszbb_xszbbjgb a");
		sb.append(",view_xsxxb xsxx where a.xh=xsxx.xh and a.bbjgid=?");
		return dao.getMapNotOut(sb.toString(),new String[]{bbjgid});
	}
	
	/**
	 * 
	 * @描述:TODO(查看学生证补办结果)
	 * @作者：Dlq[工号：995]
	 * @日期：2013-12-19 上午10:49:56
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xwjgId
	 * @return
	 * Map<String,String> 返回类型 
	 * @throws
	 */
	public Map<String, String> viewOneXszbbjgList(String  xszbbjgId) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select t1.dd,t1.sj,t1.filepath,t1.bbjgid,t1.xh,t1.xszbblxdm,t1.sqly,t1.sqsj,t1.bz, ");
		if(Base.xxdm.equals("13011") || Base.xxdm.equals("10695")){//青岛酒店个性化
			sql.append("t1.ccqdz,t1.cczdz,t1.sfbbhcyhk sfbbhcyhkmc,");
		}
		sql.append(" decode(t1.sfbbhcyhk, 'y','是', 'n', '否', t1.sfbbhcyhk) sfbbhcyhk, ");
		sql.append(" decode(t1.sflq,'0','否','1','是',t1.sflq) sflq,t1.lqsj,t2.xszbblxmc ");
		sql.append(" from xg_rcsw_xszbb_xszbbjgb  t1 left join xg_rcsw_xszbb_bblxwhb t2  on t1.xszbblxdm = t2.xszbblxdm ");
		sql.append(" where t1.bbjgid = ? ");
		return dao.getMapNotOut(sql.toString(),new String[]{xszbbjgId});
	}
	
	/** 
	 * @描述:删除火车乘车区间(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-5-23 下午06:51:25
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param xn
	 * @param xq
	 * @throws Exception
	 * void 返回类型 
	 * @throws 
	 */
	public void delHcccqj(String xh,String xn,String xq) throws Exception{
		String sql = "delete from xg_rcsw_hcyhk_hcccqjjgb where xh = ? and xn = ? and xq = ?";
		dao.runDelete(sql, new String[]{xh,xn,xq});
	}
	
	
}
