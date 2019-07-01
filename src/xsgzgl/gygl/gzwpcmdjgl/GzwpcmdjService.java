/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-8-27 ����04:31:08 
 */  
package xsgzgl.gygl.gzwpcmdjgl;


import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ��Ԣ����
 * @�๦������: ������Ʒ���ŵǼǹ��� 
 * @���ߣ� ������[����:1123]
 * @ʱ�䣺 2014-8-27 ����04:31:08 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class GzwpcmdjService extends SuperServiceImpl<GzwpcmdjForm, GzwpcmdjDao> {
	
	public GzwpcmdjService(){
		setDao(new GzwpcmdjDao());
	}
	
	/**
	 * 
	 * @����: ��ȡ������Ʒ���ŵǼ���Ϣ
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2014-8-28 ����10:57:05
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param gzwpdjid
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String,String> getGzwpcmdjxx(String gzwpdjid) {
		
		return dao.getGzwpcmdjxx(gzwpdjid);
	}
	
	/**
	 * 
	 * @����: �����������������Ʒ���ŵǼ���Ϣ
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2014-8-28 ����11:05:37
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean saveGzwpcmdjxx(GzwpcmdjForm model) throws Exception{
		return dao.saveGzwpcmdjxx(model);
	}
	
	/**
	 * 
	 * @����: ɾ����Ϣ
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2014-8-28 ����11:10:02
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param gzwpdjid
	 * @return
	 * @throws Exception
	 * int �������� 
	 * @throws
	 */
	public int deleteGzwpcmdjxx(String gzwpdjid) throws Exception{
		return dao.deleteGzwpcmdjxx(gzwpdjid);
	}
	
	
	/**
	 * 
	 * @����: ɾ����Ϣ����
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2014-8-28 ����11:11:21
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param pks
	 * @return
	 * @throws Exception
	 * int[] �������� 
	 * @throws
	 */
	public int[] deleteGzwpcmdjxxPl(List<String[]> pks) throws Exception{
		return dao.deleteGzwpcmdjxxPl(pks);
	}
	
	/**
	 * 
	 * @����: ��ȡ����ѧ����Ϣ
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2014-8-28 ����11:13:20
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String,String> getXsxxOne(String xh){
		return dao.getXsxxOne(xh);
	}
}
