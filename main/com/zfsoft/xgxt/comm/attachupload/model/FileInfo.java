/**
 * @部门:学工产品事业部
 * @日期：2014-4-28 下午02:56:15 
 */  
package com.zfsoft.xgxt.comm.attachupload.model;

import java.io.Serializable;

/** 
 * 文件信息
 */

public class FileInfo implements Serializable{

	/** 
	 * @变量 serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	 */ 
	
	private static final long serialVersionUID = 1L;

	private String fid; //文件id
	
	private String generatename;//系统生成的文件名
	
	private String originalname;//原文件名
	
	private String filesize;//文件大小
	
	private String ext;//文件后缀
	
	private String uploadtime; //上传时间
	
	private String gid; //组id

	private boolean canpreview;	//是否可预览

	/**
	 * @return the fid
	 */
	public String getFid() {
		return fid;
	}

	/**
	 * @param fid要设置的 fid
	 */
	public void setFid(String fid) {
		this.fid = fid;
	}

	/**
	 * @return the generatename
	 */
	public String getGeneratename() {
		return generatename;
	}

	/**
	 * @param generatename要设置的 generatename
	 */
	public void setGeneratename(String generatename) {
		this.generatename = generatename;
	}

	/**
	 * @return the originalname
	 */
	public String getOriginalname() {
		return originalname;
	}

	/**
	 * @param originalname要设置的 originalname
	 */
	public void setOriginalname(String originalname) {
		this.originalname = originalname;
	}

	/**
	 * @return the filesize
	 */
	public String getFilesize() {
		return filesize;
	}

	/**
	 * @param filesize要设置的 filesize
	 */
	public void setFilesize(String filesize) {
		this.filesize = filesize;
	}

	/**
	 * @return the ext
	 */
	public String getExt() {
		return ext;
	}

	/**
	 * @param ext要设置的 ext
	 */
	public void setExt(String ext) {
		this.ext = ext;
	}

	/**
	 * @return the uploadtime
	 */
	public String getUploadtime() {
		return uploadtime;
	}

	/**
	 * @param uploadtime要设置的 uploadtime
	 */
	public void setUploadtime(String uploadtime) {
		this.uploadtime = uploadtime;
	}

	/**
	 * @return the gid
	 */
	public String getGid() {
		return gid;
	}

	/**
	 * @param gid要设置的 gid
	 */
	public void setGid(String gid) {
		this.gid = gid;
	}

	public boolean isCanpreview() {
		return canpreview;
	}

	public void setCanpreview(boolean canpreview) {
		this.canpreview = canpreview;
	}
}
