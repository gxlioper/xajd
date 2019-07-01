/**
 * @部门:学工产品事业部
 * @日期：2016-12-22 上午11:50:57 
 */  
package com.zfsoft.xgxt.xlzx.zxzxjl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.xlzx.zxsgly.ZxsglyDao;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 柳俊[工号:1282]
 * @时间： 2016-12-22 上午11:50:57 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ZxzxjlxxDao extends SuperDAOImpl<ZxzxjlxxModel>{

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(ZxzxjlxxModel t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(ZxzxjlxxModel t, User user)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		super.setClass(ZxzxjlxxModel.class);
		super.setKey("xh");
		super.setTableName("xg_xljk_bjzyy_zxzxjbxxb");		
	}
	
	/**
	 * @throws SQLException  
	 * @描述:批量插入和批量更新在线咨询记录(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-12-23 上午10:05:54
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param list
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean plInsertOrUpdate(List<ZxzxjlxxModel> insertList,List<ZxzxjlxxModel> updateList,User user) throws SQLException{
		List<String[]> insertPara = new ArrayList<String[]>();
		List<String[]> updatePara = new ArrayList<String[]>();
		String[] insertArr = null;
		String[] updateArr = null;
		int[] insertNum = null;
		int[] updateNum = null;		
		if(null != insertList && insertList.size()>0){
			for(ZxzxjlxxModel model :insertList){
				insertArr = new String[10];
				String bh = model.getBh();
				String xh = model.getXh();
				String zxsxm = model.getZxsxm();
				String zxsj = model.getZxsj();
				String zxdd = model.getZxdd();
				String zxpg = model.getZxpg();
				String zxgc = model.getZxgc();
				String zxfk = model.getZxfk();
				String zxth = model.getZxth();
				
				insertArr[0] = bh;
				insertArr[1] = xh;
				insertArr[2] = zxsxm;
				insertArr[3] = zxsj;
				insertArr[4] = zxdd;
				insertArr[5] = zxpg;
				insertArr[6] = zxgc;
				insertArr[7] = zxfk;
				insertArr[8] = zxth;
				insertArr[9] = user.getUserName();
				insertPara.add(insertArr);
			}
			StringBuilder sb = new StringBuilder();
			sb.append("insert into xg_xljk_bjzyy_zxzxjlb(bh,xh,zxsxm,zxsj,zxdd,zxpg,zxgc,zxfk,zxth,txr) values (");
			sb.append(" ?,?,?,?,?,?,?,?,?,?) ");
			insertNum = dao.runBatch(sb.toString(),insertPara);
		}
		if(null != updateList && updateList.size()>0){			
			for(ZxzxjlxxModel model :updateList){
				updateArr = new String[9];
				String id = model.getId();
				String bh = model.getBh();
				//String xh = model.getXh();
				String zxsxm = model.getZxsxm();
				String zxsj = model.getZxsj();
				String zxdd = model.getZxdd();
				String zxpg = model.getZxpg();
				String zxgc = model.getZxgc();
				String zxfk = model.getZxfk();
				String zxth = model.getZxth();
				updateArr[0] = bh;
				updateArr[1] = zxsxm;
				updateArr[2] = zxsj;
				updateArr[3] = zxdd;
				updateArr[4] = zxpg;
				updateArr[5] = zxgc;
				updateArr[6] = zxfk;
				updateArr[7] = zxth;
				updateArr[8] = id;
				updatePara.add(updateArr);		
			}
			StringBuilder sb = new StringBuilder();
			sb.append("update xg_xljk_bjzyy_zxzxjlb set bh = ? ");
			sb.append(" ,zxsxm = ? ");
			sb.append(" ,zxsj = ? ");
			sb.append(" ,zxdd = ? ");
			sb.append(" ,zxpg = ? ");
			sb.append(" ,zxgc = ? ");
			sb.append(" ,zxfk= ? ");
			sb.append(" ,zxth = ? ");
			sb.append(" where id = ? ");
			updateNum = dao.runBatch(sb.toString(),updatePara);
		}
		if((null != insertList && insertList.size()>0) && (null != updateList && updateList.size()>0)){			
			return insertNum.length>0 || updateNum.length>0;
		}else if(null != insertList && insertList.size()>0){
			return insertNum.length>0;
		}else{
			return updateNum.length>0;
		}
	}
	
	/**
	 * @throws Exception  
	 * @描述:批量删除(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-12-23 上午11:07:46
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ids
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean plsc(String[] ids) throws Exception{
		StringBuilder sb = new StringBuilder();
		sb.append(" delete from xg_xljk_bjzyy_zxzxjlb where id in (");
		for(int i = 0;i<ids.length;i++){
			sb.append("?");
			if(i != ids.length-1){
				sb.append(",");
			}
		}
		sb.append(" ) ");
		return dao.runUpdate(sb.toString(), ids);
	}
	
	/** 
	 * @描述:获取咨询记录列表(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-12-23 下午02:04:11
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String,String>> getZxjlList(String xh,User user) throws Exception{
		StringBuilder sb = new StringBuilder();
		sb.append("select a.*,b.xm txrxm,c.xm xsxm from xg_xljk_bjzyy_zxzxjlb a left join xsxxb c on a.xh = c.xh left join yhb b on a.txr = b.yhm where a.xh = ? ");
		if(!new ZxsglyDao().isZxsGly(user.getUserName())){
			sb.append(" and a.txr= '");
			sb.append(user.getUserName());
			sb.append("'");
		}
		sb.append(" order by a.zxsj ");
		return dao.getListNotOut(sb.toString(), new String[]{xh});
	}

}
