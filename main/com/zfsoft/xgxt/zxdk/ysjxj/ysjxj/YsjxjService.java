/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-7-28 ����09:13:42 
 */  
package com.zfsoft.xgxt.zxdk.ysjxj.ysjxj;

import java.util.HashMap;
import java.util.List;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ��ѧ����-Ժ�轱ѧ��
 * @�๦������: TODO(Ժ�轱ѧ��) 
 * @���ߣ� MengWei[����:1186]
 * @ʱ�䣺 2016-7-28 ����09:13:42 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class YsjxjService extends SuperServiceImpl<YsjxjForm,YsjxjDao>{
	
	/**
	 * @����:TODO(��ȡѧ������)
	 * @���ߣ�����[���ţ�1186]
	 * @���ڣ�2016-7-29 ����03:11:16
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xqdm
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getXqmc(String xqdm){
		return dao.getXqmc(xqdm);
	}
	
	/**
	 * @����:TODO(��ȡ�ʽ���Դ����)
	 * @���ߣ�����[���ţ�1186]
	 * @���ڣ�2016-7-29 ����03:11:33
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param zjly
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getZjlymc(String zjly){
		return dao.getZjlymc(zjly);
	}
	
	/**
	 * @����:TODO(��ȡ�ʽ���Դ�б�)
	 * @���ߣ�����[���ţ�1186]
	 * @���ڣ�2016-7-29 ����10:43:02
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getZjlyList() {
		return dao.getZjlyList();
	}
	
	/**
	 * @����:TODO(�ж�Ψһ��)
	 * @���ߣ�����[���ţ�1186]
	 * @���ڣ�2016-7-29 ����03:13:10
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param form
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean isHaveRecord(String xh,String xn,String xq,String xmmc){
		return dao.isHaveRecord(xh,xn,xq,xmmc);
	}
	/**
	 * @����:TODO(�޸��ж�Ψһ��)
	 * @���ߣ�����[���ţ�1186]
	 * @���ڣ�2016-8-1 ����03:06:10
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param xn
	 * @param xq
	 * @param xmmc
	 * @param juid
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean updateCheck(String xh,String xn,String xq,String xmmc,String juid){
		return dao.updateCheck(xh,xn,xq,xmmc,juid);
	}
}
