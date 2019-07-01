/**
 * @部门:学工产品事业部
 * @日期：2015-6-12 上午11:10:54 
 */  
package com.zfsoft.extend.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.extend.model.ExtendStoreData;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张小彬[工号:1036]
 * @时间： 2015-6-12 上午11:10:54 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ExtendStoreDataDAO extends SuperDAOImpl<ExtendStoreData> {

	/**
	 * 
	 * @描述: 删除数据
	 * @作者：张小彬[工号:1036]
	 * @日期：2015-6-19 下午03:51:46
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param dataid
	 * @param mid
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean deleteData(String dataid , String mid) throws Exception{
		String del = "delete from ZFXG_EXTEND_DATA where id = ? and mid = ?";
		dao.runDelete(del, new String[]{dataid,mid});
		return Boolean.TRUE;
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述: 保存数据,先删除之前的数据(如果有),然后插入新数据
	 * @作者：张小彬[工号:1036]
	 * @日期：2015-6-16 下午03:45:47
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param data
	 * @param id
	 * @param mid
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean saveData(List<ExtendStoreData> data , String id , String mid) throws Exception{
		String del = "delete from ZFXG_EXTEND_DATA where id = ? and mid = ?";
		String sql = "insert into ZFXG_EXTEND_DATA (id,mid,meid,gid,zjz,zd,zdz,rn) values (?,?,?,?,?,?,?,?)";
		List<String[]> params = new ArrayList<String[]>();
		//删除老数据
		dao.runDelete(del, new String[]{id,mid});
		for (ExtendStoreData d : data) {
			String _id = d.getId();
			String _mid = d.getMid();
			String _meid = d.getMeid();
			String _gid = d.getGid();
			String _zjz = d.getZjz();
			String _zd = d.getZd();
			String _zdz = d.getZdz();
			String _rn = d.getRn();
			params.add(new String[]{_id,_mid,_meid,_gid,_zjz,_zd,_zdz,_rn});
		}
		dao.runBatch(sql, params);
		return Boolean.TRUE;
	}
	
	/**
	 * 
	 * @描述: 根据id获取相关表单数据
	 * @作者：张小彬[工号:1036]
	 * @日期：2015-6-12 上午11:12:51
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * @throws Exception
	 * List<ExtendStoreData> 返回类型 
	 * @throws
	 */
	public List<ExtendStoreData> getExtendStoreDataList(String id, String mid) throws Exception{
		String sql = "select * from ZFXG_EXTEND_DATA where id = ? and mid = ? ";
		List<HashMap<String, String>> listNotOut = dao.getListNotOut(sql, new String[]{id, mid});
		List<ExtendStoreData> dataList = new ArrayList<ExtendStoreData>();
		for (HashMap<String, String> i : listNotOut) {
			String _id = i.get("id");
			String zjz = i.get("zjz");
			String _mid = i.get("mid");
			String meid = i.get("meid");
			String gid = i.get("gid");
			String zd = i.get("zd");
			String zdz = i.get("zdz");
			String rn = i.get("rn");
			ExtendStoreData data = new ExtendStoreData(_id, zjz, _mid, meid, gid, zd, zdz, rn);
			dataList.add(data);
		}
		return dataList;
	}
	 
	@Override
	public List<HashMap<String, String>> getPageList(ExtendStoreData t)
			throws Exception {
		return null;
	}
	@Override
	public List<HashMap<String, String>> getPageList(ExtendStoreData t,
			User user) throws Exception {
		return null;
	}

	@Override
	protected void setTableInfo() {
		super.setClass(ExtendStoreData.class);
		super.setKey("ID");
		super.setTableName("ZFXG_EXTEND_DATA");
	}

}
