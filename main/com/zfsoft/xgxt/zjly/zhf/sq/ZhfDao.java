/**
 * @部门:学工产品事业部
 * @日期：2016-6-17 下午03:02:21 
 */  
package com.zfsoft.xgxt.zjly.zhf.sq;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: 综合分(这里用一句话描述这个类的作用) 
 * @作者： 柳俊[工号:1282]
 * @时间： 2016-6-17 下午03:02:21 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ZhfDao extends SuperDAOImpl<ZhfForm>{

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(ZhfForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(ZhfForm t, User user)
			throws Exception {
		//String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");	
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from (select a.*,e.xm,b.xydm,b.xymc,b.zydm,b.zymc,b.bjdm,b.bjmc,b.nj,c.xmmkmc,d.fs,d.jfxmmc, ");
		sql.append(" decode(a.shzt,'1','已审定','0','待审定','2','退回') shztmc ");
		sql.append(" from xg_zjly_zhszf_drjlb a ");
		sql.append(" left join view_xsxxb e on a.xh = e.xh ");
		sql.append(" left join view_njxyzybj b on b.bjdm = e.bjdm ");
		sql.append(" left join xg_zjly_zhszf_mkxmb c on a.xmmkdm = c.xmmkdm ");
		sql.append(" left join xg_zjly_zhszf_jfxmb d on a.jfxmdm = d.jfxmdm ");
		sql.append(" )t where 1= 1  ");
		sql.append(searchTjByUser);
		if(!StringUtil.isNull(t.getCxzd())) {
			sql.append(" and t.jfxmmc like '%'||?||'%' ");
			return getPageList(t, sql.toString(), new String[]{t.getCxzd()});
		}
		return getPageList(t, sql.toString(), inputV);
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		this.setKey("id");
		this.setTableName("xg_zjly_zhszf_drjlb");
		this.setClass(ZhfForm.class);		
	}
	
	/** 
	 * @描述:批量插入(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-6-20 上午09:53:39
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param list
	 * void 返回类型 
	 * @throws 
	 */
	public boolean plSave(List<ZhfForm> list) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append("insert into xg_zjly_zhszf_drjlb (xh,jfxmdm,xmmkdm,sxsm,cysj,lrsj,lrr,fj,fjmc)");
		if(null != list && list.size()>0 ){
			if(list.size() == 1){
				sql.append("values ('" + list.get(0).getXh() + "','" + list.get(0).getJfxmdm() + "','" + list.get(0).getXmmkdm() + "','" + list.get(0).getSxsm() + "','" + list.get(0).getCysj() + "','" + list.get(0).getLrsj() + "','" + list.get(0).getLrr() + "','" + list.get(0).getFj() + "','" +list.get(0).getFjmc() +"')"); 
			}
			else{			
				for(int i = 0;i<list.size();i++){
					if(i>0){
						sql.append(" union all ");
					}
					sql.append(" select '" + list.get(i).getXh() + "','" + list.get(i).getJfxmdm() + "','" + list.get(i).getXmmkdm() + "','" + list.get(i).getSxsm() + "','" + list.get(i).getCysj() + "','" + list.get(i).getLrsj() + "','" + list.get(i).getLrr() + "','" + list.get(i).getFj() + "','" + list.get(i).getFjmc() + "' from dual");
				}
			}
		}
		return dao.runUpdate(sql.toString(), new String[]{});
	}
	
	/** 
	 * @描述:得到项目模块列表(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-6-20 上午09:53:57
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getXmmkList(){
		String sql = "select * from xg_zjly_zhszf_mkxmb order by xmmkdm asc";
		return dao.getListNotOut(sql, new String[]{});
	}
	
	/** 
	 * @描述:根据模块代码得到计分项目列表(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-6-20 下午01:32:12
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param id
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getJfxmListByMkid(String id){
		String sql = "select * from xg_zjly_zhszf_jfxmb where xmmkdm = ? and sfxssq='0' order by jfxmdm asc";
		return dao.getListNotOut(sql, new String[]{id});
	}
	
	/** 
	 * @描述:统计(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-6-20 下午01:25:11
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param list
	 * @return
	 * int 返回类型 
	 * @throws 
	 */
	public int countZhf(List<ZhfForm> list){
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1) num from xg_zjly_zhszf_drjlb where ");
		if(list.size()>1){
			for(int i = 0;i<list.size();i++){
				if(i!=list.size()-1){
					sql.append(" (xh = '"+ list.get(i).getXh() + "' and jfxmdm = '" + list.get(i).getJfxmdm() + "' and cysj = '" + list.get(i).getCysj() +"' and sxsm ='" + list.get(i).getSxsm() + "') or");
				}else{
					sql.append(" (xh = '"+ list.get(i).getXh() + "' and jfxmdm = '" + list.get(i).getJfxmdm() + "' and cysj = '" + list.get(i).getCysj() +"' and sxsm ='" + list.get(i).getSxsm() + "')");
				}
				
			}
		}else{
			sql.append(" (xh = '"+ list.get(0).getXh() + "' and jfxmdm = '" + list.get(0).getJfxmdm() + "' and cysj = '" + list.get(0).getCysj() +"' and sxsm ='" +list.get(0).getSxsm()+"')");
		}
		String num = dao.getOneRs(sql.toString(), new String[]{}, "num");
		return Integer.valueOf(num);
					
	}
	
	/** 
	 * @描述:获取分数(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-6-21 上午11:47:03
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mkdm
	 * @return
	 * String 返回类型 
	 * @throws 
	 */
	public String getFs(String id){
		String sql = "select fs from xg_zjly_zhszf_jfxmb where jfxmdm = ?";
		return dao.getOneRs(sql, new String[]{id}, "fs");
	}
	
	/** 
	 * @描述:查询结果这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-6-21 上午11:47:15
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param id
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws 
	 */
	public HashMap<String,String> getResult(String id){
		String sql = "select a.*,b.jfxmmc,b.fs,b.khyd,c.xmmkmc from xg_zjly_zhszf_drjlb a left join xg_zjly_zhszf_jfxmb b on a.jfxmdm = b.jfxmdm left join xg_zjly_zhszf_mkxmb c on a.xmmkdm = c.xmmkdm where a.id = ? ";
		return dao.getMapNotOut(sql, new String[]{id});
	}
	
	/** 
	 * @描述:获取考核要点(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-6-21 下午03:16:36
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param id
	 * @return
	 * String 返回类型 
	 * @throws 
	 */
	public String getKhyd(String id){
		String sql = "select khyd from xg_zjly_zhszf_jfxmb where jfxmdm = ?";
		return dao.getOneRs(sql, new String[]{id}, "khyd"); 
	}
	
	public String countZhfSq(ZhfForm form){
		String sql = "select count(1) num from xg_zjly_zhszf_drjlb where xh = ? and jfxmdm = ? and sxsm = ? and cysj = ? and id <> ?";
		return dao.getOneRs(sql, new String[]{form.getXh(),form.getJfxmdm(),form.getXmmkdm(),form.getCysj(),form.getId()}, "num");
		
	}
	
	
}
