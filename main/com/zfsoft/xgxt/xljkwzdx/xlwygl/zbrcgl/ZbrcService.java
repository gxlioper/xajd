/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-5-26 ����11:57:47 
 */  
package com.zfsoft.xgxt.xljkwzdx.xlwygl.zbrcgl;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ��С��[����:1036]
 * @ʱ�䣺 2014-5-26 ����11:57:47 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ZbrcService extends SuperServiceImpl<ZbrcForm, ZbrcDao> {

	public ZbrcService(){
		setDao(new ZbrcDao());
	}
	
	public HashMap<String, String> getZbrcxx(String zbid){
		return dao.getZbrcxx(zbid);
	}
	
	/**
	 * @����: δ/���ϱ��༶
	 * @���ߣ���ˮ��[���ţ�1150]
	 * @���ڣ�2014-11-24 ����11:17:38
	 * @param model
	 * @param user
	 * @param sblx
	 * @return
	 * @throws Exception
	 * List<HashMap<String, String>> ��������
	 */
	public List<HashMap<String, String>> cxSbbj(ZbrcForm model, User user, String sbbjlx)
			throws Exception {
		return dao.cxSbbj(model, user, sbbjlx);
	}
	/**
	 * 
	 * @����:
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-6-9 ����03:47:41
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param zblx
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String , String>> getZbrcListByLx(String zblx){
		return dao.getZbrcListByLx(zblx);
	}
	
	/**
	 * 
	 * @����: Ψһ���ж�
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-9-19 ����04:58:36
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param form
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean isHaveRecordForjg(ZbrcForm form) {
		return dao.isHaveRecordForjg(form);
	}
	
	/**
	 * 
	 * @����: Ψһ���ж�
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-9-19 ����04:58:36
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param form
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean isHaveRecordForjgU(ZbrcForm form) {
		return dao.isHaveRecordForjgU(form);
	}
	
}
