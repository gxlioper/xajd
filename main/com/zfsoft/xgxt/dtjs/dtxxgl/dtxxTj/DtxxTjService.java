/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-3-30 ����10:16:13 
 */  
package com.zfsoft.xgxt.dtjs.dtxxgl.dtxxTj;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ���Ž���
 * @�๦������: ͳ�Ʋ�ѯ����  
 * @���ߣ� ������[���ţ�1123]
 * @ʱ�䣺 2016-3-30 ����10:16:13 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class DtxxTjService extends SuperServiceImpl<DtxxTjForm, DtxxTjDao> {
	
	public DtxxTjService(){
		setDao(new DtxxTjDao());
	}
	
	/**
	 * 
	 * @����: ѧ�����ű���¼��Ϣ
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-3-30 ����10:13:47
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xn
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getDtxxList(DtxxTjForm myForm,HttpServletRequest request) throws Exception{
		return dao.getDtxxList(myForm,request);
	}
	
	/**
	 * 
	 * @����: excelѧ����չ����¼��ӡ
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-3-31 ����03:33:56
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param nd
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getDtxxExcList(String nd) throws Exception{
		return dao.getDtxxExcList(nd);
	}
}
