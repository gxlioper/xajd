/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-7-30 ����03:45:21 
 */  
package xsgzgl.gygl.gyyggl;

import org.apache.struts.action.ActionForm;

import xgxt.utils.Pages;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� xucy [���ţ�754]
 * @ʱ�䣺 2013-7-30 ����03:45:21 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class GyygzwdmglForm extends ActionForm{
	private static final long serialVersionUID = 1L;
	private String zwdm;//ְλ����
	private String zwmc;//ְλ����
	
	private String type;
	private Pages pages = new Pages();
	
	public String getZwdm() {
		return zwdm;
	}
	public void setZwdm(String zwdm) {
		this.zwdm = zwdm;
	}
	public String getZwmc() {
		return zwmc;
	}
	public void setZwmc(String zwmc) {
		this.zwmc = zwmc;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Pages getPages() {
		return pages;
	}
	public void setPages(Pages pages) {
		this.pages = pages;
	}
	
}
