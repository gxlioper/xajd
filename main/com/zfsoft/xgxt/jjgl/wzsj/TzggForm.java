/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-9-11 ����11:15:23 
 */  
package com.zfsoft.xgxt.jjgl.wzsj;

import org.apache.struts.action.ActionForm;

import xgxt.utils.Pages;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ��С��[����:1036]
 * @ʱ�䣺 2014-9-11 ����11:15:23 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class TzggForm extends ActionForm {

	private Pages pages = new Pages();
	
	private static final long serialVersionUID = 1L;
	
	private String type;
	
	//���
	private String sid;
	//����
	private String title;
	//����
	private String contents;
	//����ʱ��
	private String publishdate;
	//���ȼ�
	private String priority;
	//�Ƿ񷢲�
	private String sffb;
	//������
	private String zgh;
	/**
	 * @return the pages
	 */
	public Pages getPages() {
		return pages;
	}
	/**
	 * @param pagesҪ���õ� pages
	 */
	public void setPages(Pages pages) {
		this.pages = pages;
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param typeҪ���õ� type
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @return the sid
	 */
	public String getSid() {
		return sid;
	}
	/**
	 * @param sidҪ���õ� sid
	 */
	public void setSid(String sid) {
		this.sid = sid;
	}
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param titleҪ���õ� title
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the contents
	 */
	public String getContents() {
		return contents;
	}
	/**
	 * @param contentsҪ���õ� contents
	 */
	public void setContents(String contents) {
		this.contents = contents;
	}
	/**
	 * @return the publishdate
	 */
	public String getPublishdate() {
		return publishdate;
	}
	/**
	 * @param publishdateҪ���õ� publishdate
	 */
	public void setPublishdate(String publishdate) {
		this.publishdate = publishdate;
	}
	/**
	 * @return the priority
	 */
	public String getPriority() {
		return priority;
	}
	/**
	 * @param priorityҪ���õ� priority
	 */
	public void setPriority(String priority) {
		this.priority = priority;
	}
	/**
	 * @return the sffb
	 */
	public String getSffb() {
		return sffb;
	}
	/**
	 * @param sffbҪ���õ� sffb
	 */
	public void setSffb(String sffb) {
		this.sffb = sffb;
	}
	/**
	 * @return the zgh
	 */
	public String getZgh() {
		return zgh;
	}
	/**
	 * @param zghҪ���õ� zgh
	 */
	public void setZgh(String zgh) {
		this.zgh = zgh;
	}

	
	
	
}
