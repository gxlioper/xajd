/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-4-18 ����03:32:58 
 */  
package com.zfsoft.xgxt.rcsw.xxgl;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import xsgzgl.qgzx.cjgl.QgzxCjglForm;
import xsgzgl.qgzx.cjgl.QgzxCjglService;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �ճ��������ģ��
 * @�๦������: ѧ����Ѫ���� -ҵ������
 * @���ߣ� zhangjw 
 * @ʱ�䣺 2013-4-18 ����03:26:39 
 * @�汾�� V5.1.75
 */
public class XsxxglService extends SuperServiceImpl<XsxxglForm, XsxxglDAO>{

	private XsxxglDAO dao = new XsxxglDAO();
	
	public XsxxglService(){
		super.setDao(dao);
	}
	/**
	 * @����:��ѯ�����б�
	 * @���ߣ�zhangjw
	 * @���ڣ�2013-4-18 ����04:33:13
	 * @�޸ļ�¼: 
	 * @param request
	 * @param myForm
	 * @return  HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String,String> getFfmrCs(HttpServletRequest request,XsxxglForm myForm){
		QgzxCjglService qservice = new QgzxCjglService();
		QgzxCjglForm model = new QgzxCjglForm();
		/**
		 * ��ȡ��ѯ���� 
		 * ��ΪQgzxCjglService��д��Ϊ�˱����ظ������������ת����ȡ
		 */
		model.setXh(myForm.getXh());
		model.setDoType(myForm.getType());
		model.setBz(myForm.getBz());
		model.setXn(myForm.getXn());
		HashMap<String,String> rs = qservice.setFfmrCs(request,model);
		return rs;
	}
}
