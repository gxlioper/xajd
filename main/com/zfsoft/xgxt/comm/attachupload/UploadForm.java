/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-4-30 ����02:53:17 
 */  
package com.zfsoft.xgxt.comm.attachupload;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

/** 
 * @author 1036 
 * @����form
 */

public class UploadForm extends ActionForm{

	
	private static final long serialVersionUID = 1L;

	/**
	 * ����
	 */
	private FormFile file;
	
	private FormFile uploadFile;

	/**
	 * ����
	 */
	private String gid;
	
	/**
	 * �ļ�����
	 */
	private String accept;
	
	/**
	 * �ļ�����
	 */
	private String maxcount;
	
	/**
	 * �ļ���С
	 */
	private String maxsize;
	
	/**
	 * @return the uploadFile
	 */
	public FormFile getUploadFile() {
		return uploadFile;
	}

	/**
	 * @param uploadFileҪ���õ� uploadFile
	 */
	public void setUploadFile(FormFile uploadFile) {
		this.uploadFile = uploadFile;
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

	/**
	 * @return the accept
	 */
	public String getAccept() {
		return accept;
	}

	/**
	 * @param acceptҪ���õ� accept
	 */
	public void setAccept(String accept) {
		this.accept = accept;
	}

	

	/**
	 * @return the maxcount
	 */
	public String getMaxcount() {
		return maxcount;
	}

	/**
	 * @param maxcountҪ���õ� maxcount
	 */
	public void setMaxcount(String maxcount) {
		this.maxcount = maxcount;
	}

	/**
	 * @return the maxsize
	 */
	public String getMaxsize() {
		return maxsize;
	}

	/**
	 * @param maxsizeҪ���õ� maxsize
	 */
	public void setMaxsize(String maxsize) {
		this.maxsize = maxsize;
	}

	/**
	 * @return the file
	 */
	public FormFile getFile() {
		return file;
	}

	/**
	 * @param fileҪ���õ� file
	 */
	public void setFile(FormFile file) {
		this.file = file;
	}
	
	
}
