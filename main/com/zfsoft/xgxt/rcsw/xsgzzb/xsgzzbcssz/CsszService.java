/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-11-10 ����04:56:36 
 */  
package com.zfsoft.xgxt.rcsw.xsgzzb.xsgzzbcssz;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� yxy[����:1206]
 * @ʱ�䣺 2015-11-10 ����04:56:36 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class CsszService extends SuperServiceImpl<CsszForm, CsszDao> {
	/**
	 * 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2015-11-11 ����09:34:49
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean saveWjlx(CsszForm t) throws Exception{
		return dao.saveWjlx(t);
	}
	
	//��֤�ļ����������Ƿ�����
	public boolean isExistsWjlxmc_user(String wjlxdm){
		return dao.isExistsWjlxmc_user(wjlxdm);
	}
	
	//��ȡ��Ա�������
	public String getWjlxmc(String wjlxdm){
		return dao.getWjlxmc(wjlxdm);
	}
	
	//��֤���ݿ����Ƿ���ͬ������Ա�������
	public boolean isExistsSameWjlxmc(String wjlxmc,String wjlxdm){
		return dao.isExistsSameWjlxmc(wjlxmc, wjlxdm);
	}
	
	//��ȡ�ļ���������list
	public List<HashMap<String,String>> getWjlxList(){
		return dao.getWjlxList();
	}
	
	//���ϴ�������Ϣlist
	public List<HashMap<String, String>> getYscfjList(String sqid){
		return dao.getYscfjList(sqid);
	}
}
