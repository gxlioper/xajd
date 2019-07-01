/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2018-4-20 ����03:23:54 
 */  
package com.zfsoft.xgxt.rcsw.jqfxgl.jqfxdmwh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� lgx[����:1553]
 * @ʱ�䣺 2018-4-20 ����03:23:54 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class JqfxDmwhService  extends SuperServiceImpl<JqfxDmwhForm,JqfxDmwhDao>{
	
    private JqfxDmwhDao dao=new JqfxDmwhDao();
    public static String _BCZSCID="-1";
    
	@SuppressWarnings("deprecation")
	public JqfxDmwhService() {
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
	public boolean saveJqfxgldmwhInfo(JqfxDmwhForm model,String type) throws Exception{
		
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
