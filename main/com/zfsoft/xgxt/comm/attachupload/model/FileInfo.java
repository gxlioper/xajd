/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-4-28 ����02:56:15 
 */  
package com.zfsoft.xgxt.comm.attachupload.model;

import java.io.Serializable;

/** 
 * �ļ���Ϣ
 */

public class FileInfo implements Serializable{

	/** 
	 * @���� serialVersionUID : TODO(��һ�仰�������������ʾʲô) 
	 */ 
	
	private static final long serialVersionUID = 1L;

	private String fid; //�ļ�id
	
	private String generatename;//ϵͳ���ɵ��ļ���
	
	private String originalname;//ԭ�ļ���
	
	private String filesize;//�ļ���С
	
	private String ext;//�ļ���׺
	
	private String uploadtime; //�ϴ�ʱ��
	
	private String gid; //��id

	private boolean canpreview;	//�Ƿ��Ԥ��

	/**
	 * @return the fid
	 */
	public String getFid() {
		return fid;
	}

	/**
	 * @param fidҪ���õ� fid
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
	 * @param generatenameҪ���õ� generatename
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
	 * @param originalnameҪ���õ� originalname
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
	 * @param filesizeҪ���õ� filesize
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
	 * @param extҪ���õ� ext
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
	 * @param uploadtimeҪ���õ� uploadtime
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
	 * @param gidҪ���õ� gid
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
