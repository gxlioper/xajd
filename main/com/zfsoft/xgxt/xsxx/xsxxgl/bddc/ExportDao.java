/**
 * @部门:学工产品事业部
 * @日期：2016-8-18 上午10:35:48 
 */  
package com.zfsoft.xgxt.xsxx.xsxxgl.bddc;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.action.Base;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： yxy[工号:1206]
 * @时间： 2016-8-18 上午10:35:48 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ExportDao extends SuperDAOImpl<ExportModel> {

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(ExportModel t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(ExportModel t, User user)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		// TODO 自动生成方法存根
		
	}
	
	/**
	 * 
	 * @描述: 取自定义表单xg_zdybd_flszb中的小项分类
	 * @作者：yxy[工号：1206]
	 * @日期：2016-8-18 上午11:33:15
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getGnmkMrList( ){
		StringBuilder sql = new StringBuilder();
		sql.append(" select c.*,");
		sql.append(" a.flmc || '-' || c.flmc zhmc,");
		sql.append(" c.xsxx zhsx,");
		sql.append(" b.bhmk,");
		sql.append(" b.gnlx,");
		sql.append(" '1' zt,");
		sql.append(" b.yzsz");
		sql.append(" from (select *");
		sql.append(" from xg_zdybd_flszb");
		sql.append(" where gndm = ?");
		sql.append(" and flflszid is null");
		sql.append(" and sfqy = '1' ");
		sql.append(" order by to_number(xsxx)) a");
		sql.append(" join xg_zdybd_gnszb b");
		sql.append(" on a.gndm = b.gndm");
		sql.append(" join (select *");
		sql.append(" from xg_zdybd_flszb");
		sql.append(" where gndm = ?");
		sql.append(" and flflszid is not null");
		sql.append(" and sfqy = '1' and (flszid != 'xsxx_query_xsxx_zwjd' and flszid != 'xsxx_query_xsxx_byxx')");
		sql.append(" order by flflszid, to_number(xsxx)) c");
		sql.append(" on a.flszid = c.flflszid");
		//屏蔽华中师范毕业生信息个性化显示而学生信息不显示的内容
		sql.append(" ");
		sql.append(" order by to_number(a.xsxx),a.flszid , to_number(c.xsxx)");
		return dao.getListNotOut(sql.toString(), new String[]{"xsxx_query","xsxx_query"});
	}
	
	/**
	 * 
	 * @描述: 取保存在设置表中的关联xg_zdybd_flszb中的小项分类
	 * @作者：yxy[工号：1206]
	 * @日期：2016-8-18 下午02:25:14
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zgh
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getGnmkInSzb(String zgh,String type){
		StringBuilder sql = new StringBuilder();
		sql.append(" select b.*,");
		sql.append(" a.xssx zhsx,a.zt,a.dcjs,");
		sql.append(" (select c.flmc from xg_zdybd_flszb c where b.flflszid = c.flszid) || '-' ||");
		sql.append(" b.flmc zhmc");
		sql.append(" from xg_xsxx_xxhzdc a");
		sql.append(" join xg_zdybd_flszb b");
		sql.append(" on a.flszid = b.flszid");
		sql.append(" where zgh = ?");
		sql.append(" and dcjs = '"+type+"'");
		//屏蔽华中师范毕业生信息个性化显示而学生信息不显示的内容
		sql.append(" and (a.flszid != 'xsxx_query_xsxx_zwjd' and a.flszid != 'xsxx_query_xsxx_byxx')");
		sql.append(" order by to_number(a.xssx)");
		return dao.getListNotOut(sql.toString(), new String[]{zgh});
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述: 删除设置表设置
	 * @作者：yxy[工号：1206]
	 * @日期：2016-8-23 上午11:15:13
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zgh
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public void delGndm(String zgh) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" delete from xg_xsxx_xxhzdc where zgh = ?");
		 dao.runUpdate(sql.toString(), new String[]{zgh});
	}
	
	/**
	 * @throws SQLException 
	 * 
	 * @描述:保存设置
	 * @作者：yxy[工号：1206]
	 * @日期：2016-8-23 上午11:33:28
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param selectCol
	 * @param unselectCol
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean saveBcszDc(String[] selectCol,String[] unselectCol,int sellen,int unsellen,String zgh) {
		ArrayList<String> sqllist = new ArrayList<String>();
		/*判断选中列个数是否大于0，大于0，进行插入sql拼接*/
		if(sellen > 0){
			for (int i = 0; i < sellen; i++) {
				StringBuilder sql = new StringBuilder();
				String[] flszjids = selectCol[i].split("@");
				String  flszid = null;
				if(null != flszjids && flszjids.length ==2){
					flszid = flszjids[0];
				}
				if(StringUtils.isNull(flszid)){
					return false;
				}
				sql.append(" insert into xg_xsxx_xxhzdc(flszid,zgh,zt,xssx)values('"+flszid+"','"+zgh+"','1','"+i+"')");
				sqllist.add(sql.toString());
			}
		
		}
		/*判断未选中列个数是否大于0，大于0，进行插入sql拼接*/
		if(unsellen > 0){
			
			int alllen = unsellen + sellen;
			for (int i = sellen; i < alllen; i++) {
				StringBuilder sql = new StringBuilder();
				String[] flszjids = unselectCol[i-sellen].split("@");
				String  flszid = null;
				if(null != flszjids && flszjids.length ==2){
					flszid = flszjids[0];
				}
				if(StringUtils.isNull(flszid)){
					return false;
				}
				sql.append(" insert into xg_xsxx_xxhzdc(flszid,zgh,zt,xssx)values('"+flszid+"','"+zgh+"','0','"+i+"')");
				sqllist.add(sql.toString());
			}
			
		}
		try {
			int[] len = dao.runBatch(sqllist.toArray(new String[] {}));
			return len.length > 0 ? true :false;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}
	
	/**
	 * 
	 * @描述: 获取选中的在自定义表单分类设置表中的模块
	 * @作者：yxy[工号：1206]
	 * @日期：2016-8-29 上午09:00:55
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param flszidArr
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getSelectMkinFlszb(String[] flszidArr){
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from xg_zdybd_flszb where flszid in( ");
		for (int i = 0; i < flszidArr.length; i++) {
			sql.append(" ? ");
			if(i != flszidArr.length-1){
				sql.append(" ,");
			}
		}
		sql.append(" ) ");
		return dao.getListNotOut(sql.toString(), flszidArr);
	}
	
	/**
	 * 
	 * @描述: 获取班级代码
	 * @作者：yxy[工号：1206]
	 * @日期：2016-8-29 下午02:43:22
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public  String getBjdm(String xh){
		StringBuilder sql = new StringBuilder();
		sql.append(" select bjdm from xsxxb where xh = ?");
		return dao.getOneRs(sql.toString(), new String[]{xh}, "bjdm");
	}
	
	/**
	 * 
	 * @描述: 基本信息，联系方式，其他信息取值
	 * @作者：yxy[工号：1206]
	 * @日期：2016-8-29 下午04:25:49
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param flszid
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getZdyxxList(String flszid){
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.*,rownum rn");
		sql.append(" from (select t.*, to_number(replace(zbwz, ',', '')) xssx");
		sql.append(" from xg_zdybd_zddyb t");
		sql.append(" where t.flszid = ?");
		sql.append(" and (xxdm = ? or xxdm = ?) and (szz != 'zpHidDiv' or szz is null)");
		sql.append(" order by xssx asc)t");
		sql.append(" ");
		sql.append(" ");
		return dao.getListNotOut(sql.toString(), new String[]{flszid,"public",Base.xxdm});
	}
	

}
