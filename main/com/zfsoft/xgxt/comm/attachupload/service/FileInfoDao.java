/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-5-5 ����11:50:21 
 */  
package com.zfsoft.xgxt.comm.attachupload.service;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.comm.attachupload.model.FileInfo;

/** 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class FileInfoDao extends SuperDAOImpl<FileInfo> {
	
	/**
	 * 
	 * @����:����gid��ȡȫ���ļ���Ϣ
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
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(FileInfo t)
			throws Exception {
		
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(FileInfo t, User user)
			throws Exception {
		
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		super.setClass(FileInfo.class);
		super.setKey("fid");
		super.setTableName("xg_comm_fileupload_data");
	}

}
