/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-8-26 ����10:20:27 
 */  
package com.zfsoft.xgxt.comm.browser;

import org.apache.struts.action.ActionForm;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2013-8-26 ����10:20:27 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class BrowserForm extends ActionForm{
	private static final long serialVersionUID = 1L;
	private String content;
	private String param;
	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param contentҪ���õ� content
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return the param
	 */
	public String getParam() {
		return param;
	}

	/**
	 * @param paramҪ���õ� param
	 */
	public void setParam(String param) {
		this.param = param;
	}
}
