/**
 * @部门:学工产品事业部
 * @日期：2014-5-5 上午11:50:21 
 */  
package com.zfsoft.xgxt.comm.attachupload.service;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.comm.attachupload.model.FileInfo;

/** 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class FileInfoDao extends SuperDAOImpl<FileInfo> {
	
	/**
	 * 
	 * @描述:根据gid获取全部文件信息
	 */
	public List<HashMap<String , String>> getFileInfoList(String gid){
		
		String sql = "SELECT * FROM xg_comm_fileupload_data a WHERE a.gid = ? ";
		
		return dao.getListNotOut(sql, new String[]{gid});
	}
	
	public HashMap<String , String> getFileInfo(String fid){
		
		String sql = "SELECT * FROM xg_comm_fileupload_data a WHERE a.fid = ? ";
		
		return dao.getMapNotOut(sql, new String[]{fid});
	}
	
	
	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(FileInfo t)
			throws Exception {
		
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(FileInfo t, User user)
			throws Exception {
		
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		super.setClass(FileInfo.class);
		super.setKey("fid");
		super.setTableName("xg_comm_fileupload_data");
	}

}
