/**
 * @部门:学工产品事业部
 * @日期：2013-7-30 下午04:02:40 
 */  
package com.zfsoft.xgxt.xsxx.xsxxcj;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.util.MessageResources;


import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.demo.SimpleForm;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(学生信息采集--天津职业大学) 
 * @作者： cmj [工号：913]
 * @时间： 2013-7-30 下午04:02:40 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XsxxcjDao extends SuperDAOImpl<XsxxcjForm> {
	MessageResources message = MessageResources
	.getMessageResources("config.ApplicationResources");
	
	
	@Override
	public List<HashMap<String, String>> getPageList(XsxxcjForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}


	
	@Override
	public List<HashMap<String, String>> getPageList(XsxxcjForm model, User user)
			throws Exception {
		//生成高级查询相关条件、条件值 
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		
		StringBuilder sql = new StringBuilder("select * from (select a.*,");
		sql.append("(select qxmc from dmk_qx e where e.qxdm=a.hkszdshen)||(select qxmc from dmk_qx e where e.qxdm=a.hkszdshi)||(select qxmc from dmk_qx e where e.qxdm=a.hkszdxian)||hkszdz hkszdmc,");
		sql.append("(select qxmc from dmk_qx e where e.qxdm=a.jtdzshen)||(select qxmc from dmk_qx e where e.qxdm=a.jtdzshi)||(select qxmc from dmk_qx e where e.qxdm=a.jtdzxian)||jtdzz jtdzmc,");
		sql.append("(select qxmc from dmk_qx e where e.qxdm=a.sydshen)||(select qxmc from dmk_qx e where e.qxdm=a.sydshi)||(select qxmc from dmk_qx e where e.qxdm=a.sydxian) sydmc,");
		sql.append("(select qxmc from dmk_qx e where e.qxdm=a.jgshen)||(select qxmc from dmk_qx e where e.qxdm=a.jgshi)||(select qxmc from dmk_qx e where e.qxdm=a.jgxian) jgmc,");
		sql.append("(select mzmc from mzdmb e where e.mzdm=a.ssmz) mzmc,");
		sql.append("b.xm,b.xb,b.nj,b.xydm,b.xymc,b.zydm,b.zymc,b.bjdm,b.bjmc from xsxx_xscjxxb a left join view_xsjbxx b on a.xh=b.xh) a where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		
		return getPageList(model, sql.toString(), inputV);
	}
	
	public XsxxcjForm getModel(XsxxcjForm t) throws Exception{
		XsxxcjForm model=new XsxxcjForm();
		StringBuilder sql = new StringBuilder("select * from (select a.*,");
		sql.append("(select qxmc from dmk_qx e where e.qxdm=a.hkszdshen)||(select qxmc from dmk_qx e where e.qxdm=a.hkszdshi)||(select qxmc from dmk_qx e where e.qxdm=a.hkszdxian)||hkszdz hkszdmc,");
		sql.append("(select qxmc from dmk_qx e where e.qxdm=a.jtdzshen)||(select qxmc from dmk_qx e where e.qxdm=a.jtdzshi)||(select qxmc from dmk_qx e where e.qxdm=a.jtdzxian)||jtdzz jtdzmc,");
		sql.append("(select qxmc from dmk_qx e where e.qxdm=a.sydshen)||(select qxmc from dmk_qx e where e.qxdm=a.sydshi)||(select qxmc from dmk_qx e where e.qxdm=a.sydxian) sydmc,");
		sql.append("(select qxmc from dmk_qx e where e.qxdm=a.jgshen)||(select qxmc from dmk_qx e where e.qxdm=a.jgshi)||(select qxmc from dmk_qx e where e.qxdm=a.jgxian) jgmc,");
		sql.append("(select mzmc from mzdmb e where e.mzdm=a.ssmz) mzmc,");
		sql.append("b.xm,b.xb,b.nj,b.xydm,b.xymc,b.zydm,b.zymc,b.bjdm,b.bjmc from xsxx_xscjxxb a left join view_xsjbxx b on a.xh=b.xh) where 1=1 ");
		sql.append(" and xh=?");
		HashMap<String, String> map=dao.getMapNotOut(sql.toString(), new String[]{t.getXh()});
		BeanUtils.copyProperties(model, map);
		return model;
	};


	
	@Override
	protected void setTableInfo() {
		// TODO 自动生成方法存根
		super.setTableName("xsxx_xscjxxb");
		super.setKey("xh");
		super.setClass(XsxxcjForm.class);
		
	}



	/** 
	 * @描述:TODO(获取学生列表)
	 * @作者：cmj [工号：913]
	 * @日期：2013-8-1 下午05:55:53
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getXsPageList(XsxxcjForm model,
			User user) throws Exception{
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.*, rownum r  ");
		sql.append(" from (select a.* from view_xsjbxx a where not exists (select 1 from xsxx_xscjxxb c where a.xh = c.xh  ) ");
		sql.append("order by a.nj, a.xydm, a.zydm, a.bjdm, a.xh) a where 1 = 1");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		
		return getPageList(model, sql.toString(), inputV);
	}



	/**
	 * @param user 
	 * @param model  
	 * @描述:TODO(查询学生基本信息统计“台账”)
	 * @作者：cmj [工号：913]
	 * @日期：2013-8-2 上午10:36:36
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<String[]> getXsjbxxtzList(XsxxcjForm model, User user) throws Exception{
		Base.YXPZXY_KEY = message.getMessage("lable.xb");
		// TODO 自动生成方法存根
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select nvl(xymc, '  合计') xymc,nvl(to_char(nj), '合计') nj, ");
		sql.append(" nvl(sum(decode(xb, '1', 1,'男',1)),0) nanrs, ");
		sql.append(" nvl(sum(decode(xb, '2', 1,'女',1)),0) nvrs, ");
		sql.append(" count(xh) zrs, ");
		sql.append(" nvl(sum(decode(sfzx || xb, '在校1', 1, '在校男', 1,'1',1,'男',1)),0) zxnanrs, ");
		sql.append(" nvl(sum(decode(sfzx || xb, '在校2', 1, '在校女', 1,'2',1,'女',1)),0) zxnvrs, ");
		sql.append(" nvl(sum(decode(sfzx, '在校', 1,'',1)),0) zxzrs, ");
		sql.append(" nvl(sum(decode(sfdgsx || xb, '是1', 1, '是男', 1)),0) dgsxnanrs, ");
		sql.append(" nvl(sum(decode(sfdgsx || xb, '是2', 1, '是女', 1)),0) dgsxnvrs, ");
		sql.append(" nvl(sum(decode(sfdgsx, '是', 1)),0) dgsxzrs, ");
		sql.append(" nvl(sum(decode(sfzs || xb, '是1', 1, '是男', 1)),0) zsnanrs, ");
		sql.append(" nvl(sum(decode(sfzs || xb, '是2', 1, '是女', 1)),0) zsnvrs, ");
		sql.append(" nvl(sum(decode(sfzs, '是', 1)),0) zszrs, ");
		sql.append(" nvl(sum(decode(sfsqrd || xb, '是1', 1, '是男', 1)),0) sqrdnanrs, ");
		sql.append(" nvl(sum(decode(sfsqrd || xb, '是2', 1, '是女', 1)),0) sqrdnvrs, ");
		sql.append(" nvl(sum(decode(sfsqrd, '是', 1)),0) sqrdzrs, ");
		sql.append(" nvl(sum(decode(sfssmz || xb, '是1', 1, '是男', 1)),0) ssmznanrs,");
		sql.append(" nvl(sum(decode(sfssmz || xb, '是2', 1, '是女', 1)),0) ssmznvrs, ");
		sql.append(" nvl(sum(decode(sfssmz, '是', 1)),0) ssmzzrs ");
		sql.append(" from (select * from (select a.xh,a.xb,c.xydm,nvl(c.xymc, '未知'||'"+Base.YXPZXY_KEY+"') xymc, ");
		sql.append(" c.zydm,c.zymc,c.bjdm,c.bjmc,nvl(to_char(c.nj), '未知年级') nj,a.sfzx, ");
		sql.append(" b.sfdgsx,b.sfzx sfzs,b.sfsqrd,b.hksfjrxx,b.sfssmz,b.sflspx,b.sfzjxy, ");
		sql.append(" b.sfjjkn,b.stsfcj,b.sfxxkn,b.sfxlkr,b.sfjtkr,b.sfyqtkr from xsxxb a ");
		sql.append(" left join xsxx_xscjxxb b on a.xh = b.xh left join view_njxyzybj_all c ");
		sql.append(" on a.bjdm = c.bjdm) a where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		sql.append(") group by rollup(xymc, nj) order by xymc desc,nj");
		
		return dao.rsToVatorNotOut(sql.toString(), inputV);
	}



	/** 
	 * @描述:TODO(查询学生困难信息统计“台账”)
	 * @作者：cmj [工号：913]
	 * @日期：2013-8-2 下午02:05:14
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * List<String[]> 返回类型 
	 * @throws 
	 */
	public List<String[]> exportXsknxxtz(XsxxcjForm model, User user) throws Exception{
		Base.YXPZXY_KEY = message.getMessage("lable.xb");
		// TODO 自动生成方法存根
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append("select nvl(xymc,'  合计')xymc, nvl(to_char(nj),'合计')nj, ");
		sql.append(" count(decode(sfjjkn || xb, '是1', 1,'是男',1)) jjknnanrs,count(decode(sfjjkn || xb, '是2', 1,'是女',1)) jjknnvrs,count(decode(sfjjkn, '是', 1)) jjknzrs,");
		sql.append(" count(decode(sfxlkr || xb, '是1', 1,'是男',1)) xlkrnanrs,count(decode(sfxlkr || xb, '是2', 1,'是女',1)) xlkrnvrs,count(decode(sfxlkr, '是', 1)) xlkrzrs,");
		sql.append(" count(decode(sfjjkn || xb, '是1', 1,'是男',1)) jjknnanrs,count(decode(sfjjkn || xb, '是2', 1,'是女',1)) jjknnvrs,count(decode(sfjjkn, '是', 1)) jjknzrs,");
		sql.append(" count(decode(sfxxkn || xb, '是1', 1,'是男',1)) xxknnanrs,count(decode(sfxxkn || xb, '是2', 1,'是女',1)) xxknnvrs,count(decode(sfxxkn, '是', 1)) xxknzrs,");
		sql.append(" count(decode(stsfcj || xb, '是1', 1,'是男',1)) stcjnanrs,count(decode(stsfcj || xb, '是2', 1,'是女',1)) stcjnvrs,count(decode(stsfcj, '是', 1)) stcjzrs,");
		sql.append(" count(decode(sfyqtkr || xb, '是1', 1,'是男',1)) qtkrnanrs,count(decode(sfyqtkr || xb, '是2', 1,'是女',1)) qtkrnvrs,count(decode(sfyqtkr, '是', 1)) qtkrzrs,");
		sql.append(" count(decode(sfzjxy || xb, '是1', 1,'是男',1)) zjxynanrs,count(decode(sfzjxy || xb, '是2', 1,'是女',1)) zjxynvrs,count(decode(sfzjxy, '是', 1)) zjxyzrs");
		sql.append(" from (select* from(select a.xh, a.xb, c.xydm, nvl(c.xymc,'未知'||'"+Base.YXPZXY_KEY+"') xymc, c.zydm, c.zymc, c.bjdm, c.bjmc, nvl(to_char(c.nj),'未知年级') nj, a.sfzx, b.sfdgsx,b.sfzx sfzs,b.sfsqrd");
		sql.append(" ,b.hksfjrxx,b.sfssmz,b.sflspx,b.sfzjxy,b.sfjjkn,b.stsfcj,b.sfxxkn,b.sfxlkr,b.sfjtkr,b.sfyqtkr");
		sql.append(" from xsxxb a left join xsxx_xscjxxb b on a.xh = b.xh left join view_njxyzybj_all c on a.bjdm=c.bjdm ) a where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		sql.append(") group by rollup(xymc, nj) order by xymc desc,nj");
		
		return dao.rsToVatorNotOut(sql.toString(), inputV);
	}

	

}
