/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-2-19 ����10:13:17 
 */  
package com.zfsoft.xgxt.gygl.jlkjfxgl.jqfxjcsz;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ��Ԣ������ڷ�У�������ù���ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� Dulq[����:995]
 * @ʱ�䣺 2016-2-19 ����10:13:17 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

@SuppressWarnings("unused")
public class JqfxjcszService extends SuperServiceImpl<JqfxjcszForm,JqfxjcszDao>{
	
    private JqfxjcszDao dao=new JqfxjcszDao();
	
	@SuppressWarnings("deprecation")
	public JqfxjcszService() {
		super.setDao(dao);
	}
	
	
	/**
	 * ��ѯ����������Ϣ
	 */
	public JqfxjcszForm getModel(JqfxjcszForm t)throws Exception{
		
		return dao.getModel();
		
	}
	
	/**
	 * 
	 * @����:TODO(�ӷ�У����ά������ȡ�����������)
	 * @���ߣ�������[���ţ�995]
	 * @���ڣ�2016-2-25 ����02:52:08
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getFxmcList(){
		
		List<HashMap<String,String>> list =  dao.getfxmcList();		
		return list;	
		
	}
	
	 /**
	  * 
	  * @����:TODO(������һ�仰�����������������)
	  * @���ߣ�������[���ţ�995]
	  * @���ڣ�2016-3-11 ����09:32:45
	  * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	  * @return
	  * @throws Exception
	  * JqfxjcszForm �������� 
	  * @throws
	  */
    public JqfxjcszForm getModel()throws Exception{
		
		return getModel(new JqfxjcszForm());
	}
	
   /**
    * 
    * @����:TODO(������һ�仰�����������������)
    * @���ߣ�������[���ţ�995]
    * @���ڣ�2016-3-11 ����09:32:56
    * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
    * @param model
    * @return
    * @throws Exception
    * boolean �������� 
    * @throws
    */
	public boolean saveJcsz(JqfxjcszForm model) throws Exception {
		boolean flag = false;
		flag = dao.deleteXszbbJcsz(model);
		if(flag){
			flag=dao.runInsert(model);
		}
		return flag;		
	}

	 
}
