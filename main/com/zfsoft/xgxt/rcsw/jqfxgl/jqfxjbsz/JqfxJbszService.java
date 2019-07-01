/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2018-4-20 ����03:46:01 
 */  
package com.zfsoft.xgxt.rcsw.jqfxgl.jqfxjbsz;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ�lgx[����:1553]
 * @ʱ�䣺 2018-4-20 ����03:46:01 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */
@SuppressWarnings("unused")
public class JqfxJbszService  extends SuperServiceImpl<JqfxJbszForm,JqfxJbszDao>{
	
    private JqfxJbszDao dao=new JqfxJbszDao();
	
	@SuppressWarnings("deprecation")
	public JqfxJbszService() {
		super.setDao(dao);
	}
	
	
	/**
	 * ��ѯ����������Ϣ
	 */
	public JqfxJbszForm getModel(JqfxJbszForm t)throws Exception{
		
		return dao.getModel();
		
	}
	
	/**
	 * 
	 * @����:TODO(�ӷ�У����ά������ȡ�����������)
	 * @���ߣ�lgx[����:1553]
	 * @ʱ�䣺 2018-4-20 ����03:46:01 
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
	 * @���ߣ�lgx[����:1553]
	 * @ʱ�䣺 2018-4-20 ����03:46:01 
	  * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	  * @return
	  * @throws Exception
	  * JqfxJbszForm �������� 
	  * @throws
	  */
    public JqfxJbszForm getModel()throws Exception{
		
		return getModel(new JqfxJbszForm());
	}
	
   /**
    * 
    * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�lgx[����:1553]
	 * @ʱ�䣺 2018-4-20 ����03:46:01 
    * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
    * @param model
    * @return
    * @throws Exception
    * boolean �������� 
    * @throws
    */
	public boolean saveJbsz(JqfxJbszForm model) throws Exception {
		boolean flag = false;
		flag = dao.deleteXszbbJcsz(model);
		if(flag){
			flag=dao.runInsert(model);
		}
		return flag;		
	}

	 
}
