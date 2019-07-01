/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-3-8 ����02:26:08 
 */  
package xsgzgl.szdw.jtff.jtff;

import java.util.HashMap;
import java.util.List;

import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����Դ[����:1206]
 * @ʱ�䣺 2016-3-8 ����02:26:08 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class JtffService extends SuperServiceImpl<JtffForm, JtffDao> {
	/**
	 * ��ȡ�������������
	 */
	public List<HashMap<String, String>> getLastThreeMonth(){
		return dao.getLastThreeMonth();
	}
	
	/**
	 * 
	 * @����:�����ɽ��������·ݽ������ݲ�ѯ
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2016-3-11 ����02:45:11
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ffny
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getJtfflist(String ffny){
		return dao.getJtfflist(ffny);
	}
	
	/**
	 * 
	 * @����:�����ɽ��������·ݽ������ݲ�ѯ
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2016-3-11 ����02:45:11
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ffny
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getWscJtfflist(String ffny){
		return dao.getWscJtfflist(ffny);
	}
	
	/**
	 *��֤�Ƿ�������
	 */
	public boolean isExist(String ffny){
		return dao.isExist(ffny);
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @����:����ǰ��ɾ��
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2016-3-11 ����04:52:50
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ffny
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean deleteRows(String ffny) throws Exception{
		return dao.deleteRows(ffny);
	}
	
	public String calculateZje(List<HashMap<String, String>> obj){
		double zje = (double)0.00;
		for (HashMap<String, String> hashMap : obj) {
			String lsje = hashMap.get("ffje");
			if(StringUtils.isNotNull(lsje)){
				zje += Double.parseDouble(lsje);
			}
		}
		return zje+"";
	}
	
}
