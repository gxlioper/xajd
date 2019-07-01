/**
 * @部门:学工产品事业部
 * @日期：2016-6-22 上午10:42:00 
 */  
package com.zfsoft.xgxt.zjly.zhf.sh;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.zjly.zhf.sq.ZhfForm;
import com.zfsoft.xgxt.zjly.zhf.zhfdr.ZhfDrDao;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: 综合分审核(这里用一句话描述这个类的作用) 
 * @作者： 柳俊[工号:1282]
 * @时间： 2016-6-22 上午10:42:00 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ZhfshDao extends SuperDAOImpl<ZhfForm>{

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
			String sqll = "select distinct a.xmmkdm,b.xmmkmc from xg_zjly_zhszf_drjlb a left join xg_zjly_zhszf_mkxmb b on a.xmmkdm = b.xmmkdm order by a.xmmkdm asc";
			List<HashMap<String,String>> list = dao.getListNotOut(sqll, new String[]{} );//得到已存在项目模块代码
			
			String searchTj = SearchService.getSearchTj(t.getSearchModel());
			String[] inputV = SearchService.getTjInput(t.getSearchModel());
			String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
			StringBuilder sql = new StringBuilder();
			sql.append(" select t.*,to_number(nvl(t.fs0,0))+to_number(nvl(t.fs1,0))+to_number(nvl(t.fs2,0))+to_number(nvl(t.fs3,0))+to_number(nvl(t.fs4,0)) zf from (select a.*,case when to_number(a.shzt1)>0 then '未审定' else '已审定' end shztmc,case when to_number(a.shzt1) > 0 then '1' else '0' end shzt from (select a.xh,a.xm,a.xb,a.nj,a.xymc,a.xydm,a.zydm,a.zymc,a.bjdm,a.bjmc");
			if(list.size()>0){
				for(int i = 0;i<list.size();i++){
//					sql.append(",(select sum(c.fs) from xg_zjly_zhszf_drjlb b left join xg_zjly_zhszf_jfxmb c on b.jfxmdm = c.jfxmdm where a.xh=b.xh and b.xmmkdm='"+ list.get(i).get("xmmkdm")+ "' group by b.xmmkdm) fs"+i);
					sql.append(",( select  case when fs>k.xf then k.xf else fs end fs  from( select xh,to_number(xf) xf,sum(fs1) fs from  ( select xh,xf,case when  (to_number(z.fs)*z.cou) > to_number(z.xdfs) then to_number(z.xdfs) else (to_number(z.fs) * z.cou) end fs1 " +
							" from (select xh,b.jfxmdm,fs,xdfs,xf,count(*) cou from xg_zjly_zhszf_drjlb b left join xg_zjly_zhszf_jfxmb c " +
							" on b.jfxmdm = c.jfxmdm  left join XG_ZJLY_ZHSZF_MKXMB d on b.xmmkdm =d.xmmkdm where b.xmmkdm='"+ list.get(i).get("xmmkdm")+ "' and b.shzt='1' group by xh,b.jfxmdm,fs,xdfs,xf )z) group by xh,xf) k  where  a.xh=k.xh) fs"+i);
				}
			}
//			sql.append(",(select sum(c.fs) from xg_zjly_zhszf_drjlb b left join xg_zjly_zhszf_jfxmb c on b.jfxmdm = c.jfxmdm where a.xh=b.xh) zf, ");
			sql.append(", (select count(1) from xg_zjly_zhszf_drjlb b where b.xh = a.xh and b.shzt = '0') shzt1 ");
			sql.append(" from view_xsxxb a ");
			sql.append(" where exists (select 1 from xg_zjly_zhszf_drjlb d where a.xh = d.xh) and a.sfzx = '在校' ");
			sql.append(" )a) t ");
			sql.append(" where 1=1 ");
			if ("xg".equals(t.getLb())) {
				sql.append(" and shzt='0' ");	
			}
			sql.append(searchTj);
			sql.append(searchTjByUser);
			return getPageList(t, sql.toString(), inputV);
		}
	
	

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		super.setClass(ZhfForm.class);
		super.setKey("id");
		super.setTableName("xg_zjly_zhszf_drjlb");
		
	}
	
	/** 
	 * @描述:获取有学生申请的项目模块list(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-6-22 下午05:03:12
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String,String>> getXmmkList(){
		String sqll = "select distinct a.xmmkdm,b.xmmkmc from xg_zjly_zhszf_drjlb a left join xg_zjly_zhszf_mkxmb b on a.xmmkdm = b.xmmkdm order by a.xmmkdm asc";
		List<HashMap<String,String>> list = dao.getListNotOut(sqll, new String[]{} );
		return list;
	}
	
	/** 
	 * @描述:得到已审定项目(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-6-23 下午01:41:40
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String,String>> getJfxmYsdByXh(String xh){
		String sql = "select a.*,case when to_number(length(a.lrr))>10 then '学生申请' else (select bmmc||'导入' from view_fdyxx where zgh =a.lrr) end  lb,b.jfxmmc,b.fs,c.xmmkmc from xg_zjly_zhszf_drjlb a left join xg_zjly_zhszf_jfxmb b on a.jfxmdm = b.jfxmdm left join xg_zjly_zhszf_mkxmb c on a.xmmkdm = c.xmmkdm where xh = ? and shzt = '1' order by a.xmmkdm asc";
		return dao.getListNotOut(sql, new String[]{xh});
	}
	
	/** 
	 * @描述:得到未审定项目(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-6-23 下午01:41:57
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String,String>> getJfxmWsdByXh(String xh){
		String sql = "select a.*,case when to_number(length(a.lrr))>10 then '学生申请' else (select bmmc||'导入' from view_fdyxx where zgh =a.lrr) end  lb,b.jfxmmc,b.fs,c.xmmkmc from xg_zjly_zhszf_drjlb a left join xg_zjly_zhszf_jfxmb b on a.jfxmdm = b.jfxmdm left join xg_zjly_zhszf_mkxmb c on a.xmmkdm = c.xmmkdm where xh = ? and shzt = '0' order by a.xmmkdm asc";
		return dao.getListNotOut(sql, new String[]{xh});
	}
	
	/** 
	 * @描述:保存审定(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-6-23 下午01:55:11
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param list
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean saveSd(List<ZhfForm> list) throws Exception{
			StringBuilder sql = new StringBuilder();
			sql.append("insert into xg_zjly_zhszf_drjlb (xh,jfxmdm,xmmkdm,sxsm,cysj,shsj,shr,fj,fjmc,shzt)");
			if(null != list && list.size()>0 ){
				if(list.size() == 1){
					sql.append("values ('" + list.get(0).getXh() + "','" + list.get(0).getJfxmdm() + "','" + list.get(0).getXmmkdm() + "','" + list.get(0).getSxsm() + "','" + list.get(0).getCysj() + "','" + list.get(0).getShsj() + "','" + list.get(0).getShr() + "','" + list.get(0).getFj() + "','" +list.get(0).getFjmc() +"','"+ list.get(0).getShzt() + "')"); 
				}
				else{			
					for(int i = 0;i<list.size();i++){
						if(i>0){
							sql.append(" union all ");
						}
						sql.append(" select '" + list.get(i).getXh() + "','" + list.get(i).getJfxmdm() + "','" + list.get(i).getXmmkdm() + "','" + list.get(i).getSxsm() + "','" + list.get(i).getCysj() + "','" + list.get(0).getShsj() + "','" + list.get(0).getShr() + "','" + list.get(i).getFj() + "','" + list.get(i).getFjmc() + "','" + list.get(i).getShzt()+ "' from dual");
					}
				}
			}
			return dao.runUpdate(sql.toString(), new String[]{});
	}
	
	/**
	 * @throws Exception  
	 * @描述:根据选定的学号批量审定(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-6-23 下午01:55:28
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ids
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean saveSdByXh(String[] xhs,String shr,String shsj) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append("update xg_zjly_zhszf_drjlb set shzt = '1',shr = ?,shsj = ? where xh in ( ");
		for(int i = 0;i<xhs.length;i++){
			if(i!= xhs.length-1){
				sql.append("'"+xhs[i]+"',");
			}else{
				sql.append("'"+xhs[i]+"'");
			}			
		}
		sql.append(")");
		return dao.runUpdate(sql.toString(), new String[]{shr,shsj});
	}
	
	/** 
	 * @描述:根据id审核(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-6-23 下午03:30:42
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ids
	 * @param shr
	 * @param shsj
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean saveSdById(String[] ids,String shr,String shsj) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append("update xg_zjly_zhszf_drjlb set shzt = '1',shr = ?,shsj = ? where id in ( ");
		for(int i = 0;i<ids.length;i++){
			if(i!= ids.length-1){
				sql.append("'"+ids[i]+"',");
			}else{
				sql.append("'"+ids[i]+"'");
			}			
		}
		sql.append(")");
		return dao.runUpdate(sql.toString(), new String[]{shr,shsj});
	}

	/**
	 * @param thyj 
	 * @throws Exception  
	 * @描述:保存退回的计分项
	 * @作者：CP[工号：1352]
	 * @日期：2017-1-10 下午01:49:27
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param idsss
	 * @param shr
	 * @param shsj
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean saveBack(String[] ids, String shr, String shsj, String thyj) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("update xg_zjly_zhszf_drjlb set shzt = '2',shr = ?,shsj = ?,thyj=? where id in ( ");
		for(int i = 0;i<ids.length;i++){
			if(i!= ids.length-1){
				sql.append("'"+ids[i]+"',");
			}else{
				sql.append("'"+ids[i]+"'");
			}			
		}
		sql.append(")");
		return dao.runUpdate(sql.toString(), new String[]{shr,shsj,thyj});
	}

	/** 
	 * @描述:取退回的记录
	 * @作者：CP[工号：1352]
	 * @日期：2017-1-10 下午05:00:16
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getJfxmThByXh(String xh) {
		String sql = "select a.*,case when to_number(length(a.lrr))>10 then '学生申请' else (select bmmc||'导入' from view_fdyxx where zgh =a.lrr) end  lb,b.jfxmmc,b.fs,c.xmmkmc from xg_zjly_zhszf_drjlb a left join xg_zjly_zhszf_jfxmb b on a.jfxmdm = b.jfxmdm left join xg_zjly_zhszf_mkxmb c on a.xmmkdm = c.xmmkdm where xh = ? and shzt = '2' order by a.xmmkdm asc";
		return dao.getListNotOut(sql, new String[]{xh});
	}

	/**
	 * @throws Exception  
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：CP[工号：1352]
	 * @日期：2017-3-13 下午07:52:17
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param time
	 * @param user
	 * @param xh
	 * void 返回类型 
	 * @throws 
	 */
	public void addlog(String time, User user, String values) throws Exception {
		ZhfDrDao zhfDrDao = new ZhfDrDao();
		HashMap<String, String> map = zhfDrDao.getJfxmxx(values);
		zhfDrDao.saveSd(time,user,map);
	}

	/**
	 * @throws Exception  
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：张昌路[工号：982]
	 * @日期：2017-3-13 下午08:07:31
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param values
	 * void 返回类型 
	 * @throws 
	 */
	public void dellog(String values) throws Exception {
		String sql=" delete from xg_zjly_zhfsclog where logid =?";
		dao.runUpdate(sql.toString(), new String[]{values});
	}
}


