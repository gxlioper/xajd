/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-11-18 ����08:53:03 
 */  
package com.zfsoft.xgxt.xpjpy.zyzgk;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����[����:1282]
 * @ʱ�䣺 2016-11-18 ����08:53:03 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����[����:1282]
 * @ʱ�䣺 2016-11-18 ����10:03:11 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */
public class ZyzgkService extends SuperServiceImpl<ZyzgkModel, ZyzgkDao>{
	private ZyzgkDao dao = new ZyzgkDao();
	
	/**
	 * @throws Exception  
	 * @����:����רҵ���ɿ�(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-11-18 ����09:01:07
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean saveZgk(ZyzgkModel model,String zyCond) throws Exception{
		dao.deleteZyzgk(model.getPjxn(),model.getXydm(),zyCond);
		return dao.insertZgk(model);
	}
	
	/** 
	 * @����:��ѧ���ȡרҵ���ɿ�����(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-11-18 ����09:56:49
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String,String>> getZyzgkszList(ZyzgkModel t){
		return dao.getZyzgkszList(t);
	}
	
	/** 
	 * @����:����ѧ�갴רҵ��ȡ���ɿγ��б�(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-11-18 ����10:27:38
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String,String>> getzgkListByXnZy(ZyzgkModel t){
		return dao.getzgkListByXnZy(t);
	}
	
	/** 
	 * @����:����ѧ���ȡ���������ɿ�(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-11-18 ����05:27:46
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xn
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String,String>> getYszZgkModel(String xn){
		return dao.getYszZgkModel(xn);
	}
	
	public List<HashMap<String,String>> getzgkList(String xn,String zydm,String xydm){
		return dao.getzgkList(xn, zydm, xydm);
	}
	
	public List<HashMap<String,String>> getXyList(){
		return dao.getXyList();
	}
	
	public List<HashMap<String,String>> getZyList(String xydm){
		return dao.getZyList(xydm);
	}
}
