/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-12-9 ����02:26:12 
 */  
package xsgzgl.gygl.sdftj;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� yxy[����:1206]
 * @ʱ�䣺 2016-12-9 ����02:26:12 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class sdfTjService extends SuperServiceImpl<sdfTjForm, sdfTjDao> {
	/**
	 * 
	 * @����: ��ȡ¥������List
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-12-9 ����04:02:11
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getLddmList(){
		return dao.getLddmList();
	}
	
	/**
	 * 
	 * @����: ��ȡ����List
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-12-9 ����04:04:33
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param lddm
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getQshList(String lddm,String ch){
		return dao.getQshList(lddm, ch);
	}
	
	/**
	 * 
	 * @����: ��ȡ���List
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-12-9 ����04:36:44
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param lddm
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getChList(String lddm){
		return dao.getChList(lddm);
	}
	
	/**
	 * 
	 * @����: ��ȡ���
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-12-9 ����04:36:44
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param lddm
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public String getCh(String qsh){
		return dao.getCh(qsh);
	}
	
	/**
	 * 
	 * @����: ��ȡ¥��
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-12-9 ����04:36:44
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param lddm
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public String getLdmc(String lddm){
		return dao.getLdmc(lddm);
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @����:��������
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-12-9 ����05:22:49
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param form
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String saveData(sdfTjForm form) throws Exception{
		boolean rs = true;
		if("save".equals(form.getType())){
			if(!dao.checkIsNotExists(form.getNd(),form.getJd(),form.getLddm(),form.getQsh())){
				return form.getNd()+"���"+form.getJd()+"�����Ѵ��ڣ�";
			}
			rs = dao.runInsert(form);
			return rs ? "����ɹ���" :"����ʧ�ܣ�";
			
		}else{
			rs = dao.runUpdate(form);
			return rs ? "����ɹ���" :"����ʧ�ܣ�";
		}
	}

}
