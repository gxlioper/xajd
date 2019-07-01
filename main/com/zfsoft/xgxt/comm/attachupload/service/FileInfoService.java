/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-5-5 ����11:54:22 
 */  
package com.zfsoft.xgxt.comm.attachupload.service;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.comm.attachupload.model.FileInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

/** 

 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class FileInfoService extends SuperServiceImpl<FileInfo, FileInfoDao> {

	
	
	/**
	 * @���� ��TODO�����µ�ǰ���췽��
	 */
	public FileInfoService() {
		setDao(new FileInfoDao());
	}

	/**
	 * 
	 * @����:����gid��ȡȫ���ļ���Ϣ
	 */
	public List<FileInfo> getFileInfoList(String gid){
		
		List<HashMap<String, String>> infoList = dao.getFileInfoList(gid);
		
		List<FileInfo> dataList = new ArrayList<FileInfo>();
		
		for (HashMap<String, String> hashMap : infoList) {
			
			FileInfo info = new FileInfo();
			info.setFid(hashMap.get("fid"));
			info.setExt(hashMap.get("ext"));
			info.setFilesize(hashMap.get("filesize"));
			info.setGeneratename(hashMap.get("generatename"));
			info.setGid(hashMap.get("gid"));
			info.setOriginalname(hashMap.get("originalname"));
			info.setUploadtime(hashMap.get("uploadtime"));
			
			dataList.add(info);
		}
		
		
		return dataList;
	}

	/**
	 *  ����gid��ȡȫ���ļ���Ϣ.
	 *  <p>�����Ƿ��Ԥ����Ϣ</p>
	 *
	 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
	 * @date 2018-01-22 10:30
	 * @param gid
	 * @param resource
	 * @return java.util.List<com.zfsoft.xgxt.comm.attachupload.model.FileInfo>
	 * @throw
	 */
	public List<FileInfo> getFileInfoList(String gid,ResourceBundle resource){

		List<HashMap<String, String>> infoList = dao.getFileInfoList(gid);

		List<FileInfo> dataList = new ArrayList<FileInfo>();

		for (HashMap<String, String> hashMap : infoList) {

			FileInfo info = new FileInfo();
			info.setFid(hashMap.get("fid"));
			info.setExt(hashMap.get("ext"));
			info.setFilesize(hashMap.get("filesize"));
			info.setGeneratename(hashMap.get("generatename"));
			info.setGid(hashMap.get("gid"));
			info.setOriginalname(hashMap.get("originalname"));
			info.setUploadtime(hashMap.get("uploadtime"));
			info.setCanpreview(resource.containsKey(hashMap.get("ext")));

			dataList.add(info);
		}

		return dataList;
	}

	/**
	 * ��ȡ�����ļ�
	 * @param fid
	 * @return
	 */
	public HashMap<String,String> getFileInfo(String fid){
		
		return dao.getFileInfo(fid);
	
	
	}
	
}
