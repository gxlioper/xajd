/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-8-20 ����09:01:41 
 */  
package xsgzgl.gygl.lfryxxgl;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.gygl.qsdsgl.QsdswhDao;
import com.zfsoft.xgxt.gygl.qsdsgl.QsdswhForm;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ��Ԣ����
 * @�๦������: ������Ա�Ǽǹ��� 
 * @���ߣ�������[����:1123]
 * @ʱ�䣺 2014-8-20 ����09:01:41 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class LfrydjService extends SuperServiceImpl<LfrydjForm, LfrydjDao> {
	
	public LfrydjService(){
		setDao(new LfrydjDao());
	}
	
	/**
	 * 
	 * @����: ��ȡѧ�����õǼ���Ϣ
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2014-8-25 ����11:34:10
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param lfrydjid
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String,String> getLfrydjxx(String lfrdjid){
		
		return dao.getLfrydjxx(lfrdjid);
	}
	
	/**
	 * 
	 * @����: ɾ����Ϣ
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2014-8-21 ����10:20:09
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param lfrdjid
	 * @return
	 * @throws Exception
	 * int �������� 
	 * @throws
	 */
	public int deleteLfrydjxx(String lfrdjid) throws Exception{
		return dao.deleteLfrydjxx(lfrdjid);
	}
	
	
	/**
	 * 
	 * @����: ɾ����Ϣ����
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2014-8-21 ����10:41:29
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param pks
	 * @return
	 * @throws Exception
	 * int[] �������� 
	 * @throws
	 */
	public int[] deleteLfrydjxxPl(List<String[]> pks) throws Exception{	
		return dao.deleteLfrydjxxPl(pks);	
	}
	
	/**
	 * 
	 * @����: ��ȡ����ѧ����Ϣ
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2014-8-26 ����09:28:53
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String,String> getXsxxOne(String xh){
		return dao.getXsxxOne(xh);
	}

	/** 
	 * @����:��ѯ���������б�
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��3��17�� ����9:00:08
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getLfsyList() {
		return dao.getLfsyList();
	}
}
