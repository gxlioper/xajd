/**
 * @部门:学工产品事业部
 * @日期：2014-4-30 下午02:53:17 
 */  
package com.zfsoft.xgxt.comm.attachupload;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

/** 
 * @author 1036 
 * @附件form
 */

public class UploadForm extends ActionForm{

	
	private static final long serialVersionUID = 1L;

	/**
	 * 附件
	 */
	private FormFile file;
	
	private FormFile uploadFile;

	/**
	 * 组编号
	 */
	private String gid;
	
	/**
	 * 文件类型
	 */
	private String accept;
	
	/**
	 * 文件数量
	 */
	private String maxcount;
	
	/**
	 * 文件大小
	 */
	private String maxsize;
	
	/**
	 * @return the uploadFile
	 */
	public FormFile getUploadFile() {
		return uploadFile;
	}

	/**
	 * @param uploadFile要设置的 uploadFile
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
	 * @param gid要设置的 gid
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
	 * @param accept要设置的 accept
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
	 * @param maxcount要设置的 maxcount
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
	 * @param maxsize要设置的 maxsize
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
	 * @param file要设置的 file
	 */
	public void setFile(FormFile file) {
		this.file = file;
	}
	
	
}
