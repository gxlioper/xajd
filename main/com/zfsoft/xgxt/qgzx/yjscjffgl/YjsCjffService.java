package com.zfsoft.xgxt.qgzx.yjscjffgl;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import xgxt.utils.String.StringUtils;
import xsgzgl.qgzx.cjgl.QgzxCjglForm;
import xsgzgl.qgzx.cjgl.QgzxCjglService;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �ڹ���ѧ����ģ��
 * @�๦������: ���ѳ����� �о�����𷢷�ҵ����
 * @���ߣ� xiaxia
 * @ʱ�䣺2016-05-04 ����09:46:37
 * @�汾�� V5.1.75
 * @�޸ļ�¼:
 */
public class YjsCjffService extends SuperServiceImpl<YjsCjffForm, YjsCjffDAO> {

	private YjsCjffDAO dao = new YjsCjffDAO();

	public YjsCjffService() {
		super.setDao(dao);
	}
	
	/**
	 *  �Ƿ���ڷ�����Ϣ
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2016-05-04 ����02:33:23
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param xn
	 * @param yrdwdm
	 * @param gwdm
	 * @return
	 * boolean �������� 
	 */
	public boolean isHaveFfxx(String guid,String xh,String xn,String yrdwdm,String gwmc,String ffny){
		return dao.isHaveFfxx(guid, xh, xn, yrdwdm, gwmc,ffny);
	}
	
	/**
	 * ��ѯ�����б�
	 * 
	 * @param request
	 * @return
	 */
	public HashMap<String, String> getFfmrCs(HttpServletRequest request,
			YjsCjffForm myForm) {
		QgzxCjglService qservice = new QgzxCjglService();
		QgzxCjglForm model = new QgzxCjglForm();
		/**
		 * ��ȡ��ѯ���� ��ΪQgzxCjglService��д��Ϊ�˱����ظ������������ת����ȡ
		 */
		model.setXh(myForm.getXh());
		model.setDoType(myForm.getType());
		model.setGs(myForm.getGs());
		model.setJe(myForm.getJe());
		model.setXn(myForm.getXn());
		model.setYrbm(myForm.getYrbm());
		// TODO ����������滻���ϸ�ֵ BeanUtils.copyProperties(model, myForm);
		HashMap<String, String> rs = qservice.setFfmrCs(request, model);
		return rs;
	}

	
	

}
