/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-3-18 ����02:16:06 
 */  
package com.zfsoft.xgxt.rcsw.tsqktbgl.jg;

import java.util.Map;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����[����:1282]
 * @ʱ�䣺 2016-3-18 ����02:16:06 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class TsqkjgService extends SuperServiceImpl<TsqkjgForm, TsqkjgDao>{
	private TsqkjgDao dao = new TsqkjgDao();
	
	/** 
	 * @����:�жϽ�������Ƿ�������
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-3-18 ����02:24:10
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param form
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean isHaveRecordForjg(TsqkjgForm form){
		return dao.isHaveRecordForjg(form);
	}
	
	/** 
	 * @����:ɾ��������е�����(��������ͨ��)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-3-18 ����02:24:46
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param form
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws 
	 */
	public boolean deleteForSq(TsqkjgForm form) throws Exception{
		return dao.deleteForSq(form);
	}
	
	
	/** 
	 * @����:������Դ����idɾ�����
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-3-21 ����01:39:10
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param lclyywid
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws 
	 */
	public boolean delByLclyywid(String lclyywid) throws Exception{
		return dao.delByLclyywid(lclyywid);
	}
	
	/** 
	 * @����:����
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-3-22 ����09:50:12
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws 
	 */
	public boolean saveTsqkjg(TsqkjgForm model) throws Exception {
		boolean result = false;
		if ("save".equals(model.getType())) {
			result = dao.runInsert(model);
		} else {
			result = dao.runUpdate(model);
		}
		return result;
	}
	
	/** 
	 * @����:��ȡ�����Ϣ
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-3-22 ����09:50:52
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param form
	 * @return
	 * Map<String,String> �������� 
	 * @throws 
	 */
	public Map<String, String> getTbjgxx(TsqkjgForm form){
		return dao.getTbjgxx(form);
	}
}
