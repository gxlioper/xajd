/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-2-19 ����10:13:17 
 */  
package com.zfsoft.xgxt.gygl.jlkjfxgl.jqfxdmwh;




import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;


/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� Dulq[����:995]
 * @ʱ�䣺 2016-2-19 ����10:13:17 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */
public class JqfxdmwhService extends SuperServiceImpl<JqfxdmwhForm,JqfxdmwhDao>{
	
    private JqfxdmwhDao dao=new JqfxdmwhDao();
    public static String _BCZSCID="-1";
    
	@SuppressWarnings("deprecation")
	public JqfxdmwhService() {
		super.setDao(dao);
	}
	
	
	/**
	 * 
	 * @����:TODO(���Ӽ��ڷ�У���ά��)
	 * @���ߣ�������[���ţ�995]
	 * @���ڣ�2016-3-11 ����09:21:07
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param type
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean saveJqfxgldmwhInfo(JqfxdmwhForm model,String type) throws Exception{
		
		boolean result = false;
		model.setFxdm(model.getFxdm().trim());
		model.setFxmc(model.getFxmc().trim());
		if ("add".equals(type)) {
			return dao.addFxgldmInfo(model);
		} else if ("update".equals(type)) {
			return dao.updateFxgldmInfo(model);
		}
		return result;
		
	}
		
	/**
	 * 
	 * @����:TODO(ɾ����У����ά��)
	 * @���ߣ�������[���ţ�995]
	 * @���ڣ�2016-3-16 ����02:19:00
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ids
	 * @return
	 * @throws Exception
	 * String[] �������� 
	 * @throws
	 */
	public String[] deletFxlbdmwhInfo(String[] ids) throws Exception{
		List<String> delId=new ArrayList<String>();//��ɾ����id����
		StringBuffer noDel = new StringBuffer();
		boolean isHaveNoId = false;
		if(null==ids||ids.length<=0){
			return null;
		}
		for(String str:ids){
			if(dao.isCanDel(str)){
				delId.add(str);//��¼ɾ��id
			}else{
				HashMap<String, String> hm=dao.getFxmc(str);
				noDel.append(hm.get("fxmc"));
				noDel.append(",</br>");
				isHaveNoId=true;
			}
		}
		int i=delId.size()>0?fxlbdmwhDelete(delId.toArray(new String[]{})):0;
		String str=noDel.toString();
		//ȥ�������ය��
		str=isHaveNoId?str:_BCZSCID;		
		return new String[]{String.valueOf(i),str};
	}
	
	/**
	 * 
	 * @����:TODO(ɾ����У����ά��)
	 * @���ߣ�������[���ţ�995]
	 * @���ڣ�2016-3-16 ����02:18:21
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param fxdm
	 * @return
	 * @throws Exception
	 * int �������� 
	 * @throws
	 */
	private int fxlbdmwhDelete(String[] fxdm) throws Exception {		
		return runDelete(fxdm);
	}
	 
}
