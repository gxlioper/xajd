package com.zfsoft.xgxt.gygl.jlkjfxgl.jqfxwh;



import java.util.HashMap;
import java.util.List;
import java.util.Map;
import xgxt.action.Base;
import xgxt.form.User;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.gygl.jlkjfxgl.jqfxjcsz.JqfxjcszForm;
import com.zfsoft.xgxt.gygl.jlkjfxgl.jqfxjcsz.JqfxjcszService;


/**
 * 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ���ڷ�У����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ������[����:995]
 * @ʱ�䣺 2016-3-14 ����02:39:00 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class JqfxwhService extends SuperServiceImpl<JqfxwhForm, JqfxwhDao> {
	public static String JQFXWWHZT = "0";
	public static String JQFXWHZT = "1";
	
	/**
	 * 
	 * @����:TODO(��У����)
	 * @���ߣ�������[���ţ�995]
	 * @���ڣ�2016-3-1 ����09:43:39
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getFxmc()throws Exception {
			return dao.getFxmc();
	}
		
	/**
	 * 
	 * @����:TODO(δ��У)
	 * @���ߣ�������[���ţ�995]
	 * @���ڣ�2016-3-2 ����11:09:06
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * Map<String,String> �������� 
	 * @throws
	 */
	public Map<String, String> getOneFxgljgList(String  xh) {
		    return dao.getOneFxgljgList(xh);
	}
	
	/**
	 * 
	 * @����:TODO(��ȡδ��У��Ϣ��¼)
	 * @���ߣ�������[���ţ�995]
	 * @���ڣ�2016-3-2 ����03:00:42
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * Map<String,String> �������� 
	 * @throws
	 */
	public Map<String, String> getOneWfxxwhjgList(String  xh) {	
		 Map<String, String> newMap =  dao.getOneWfxxwhjgList(xh);
		 
		 //�����ѧ�ŵ�ѧ��δ����
		 if(newMap.size() == 0){
			 newMap.put("fxztmc", "δ����");
		 }
		 if(newMap.get("wfxyy") == null){
			 newMap.put("wfxyy", "");		 	 
		 }
		  return newMap;
	}
	
	/**
	 * 
	 * @����:TODO(���淵Уά��)
	 * @���ߣ�������[���ţ�995]
	 * @���ڣ�2016-3-3 ����01:59:45
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean saveJqFxwh(JqfxwhForm model) throws Exception {	
		boolean flag=true;
		model.setXq("��".equals(model.getXq())?"01":"02");
		
		model.setFxdm(getFxdm());		
		if(JQFXWWHZT.equals(model.getFxzt())||JQFXWHZT.equals(model.getFxzt())){
			//���÷�У״̬Ϊ��У
			model.setFxzt(JQFXWHZT);
			model.setWfxyy("");//�ÿ�δ��Уԭ��
			//�޸ļ���δ��Уά��
			boolean updateResult = dao.runUpdate(model);
			flag = updateResult;
		}else {
			model.setFxzt(JQFXWHZT);	
			boolean insertResult = dao.runInsert(model);
			if(!insertResult){
				flag = insertResult;
			}
		}
		 
		/*if(JQFXWWHZT.equals(model.getFxzt())){
			//���÷�У״̬Ϊ��У
			model.setFxzt(JQFXWHZT);
			model.setWfxyy("");//�ÿ�δ��Уԭ��
			//�޸ļ���δ��Уά��
			boolean updateResult = dao.runUpdate(model);
			flag = updateResult;
		}else{
			model.setFxzt(JQFXWHZT);	
			boolean insertResult = dao.runInsert(model);
			if(!insertResult){
				flag = insertResult;
			}
		}	*/
		return flag;
	}

	

	/**
	 * 
	 * @����:TODO(�������δ��У)
	 * @���ߣ�������[���ţ�995]
	 * @���ڣ�2016-3-14 ����12:30:09
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean saveJqWfxwh(JqfxwhForm model) throws Exception {	
		model.setXq("��".equals(model.getXq())?"01":"02");	
		model.setFxdm(getFxdm());
		//���շ�У
		boolean flag=true;		
		if(JQFXWWHZT.equals(model.getFxzt())||JQFXWHZT.equals(model.getFxzt())){			
			if(JQFXWHZT.equals(model.getFxzt())){
				model.setFxsj("");
				model.setFxzt(JQFXWWHZT);
			}
			//�޸ļ���δ��Уά��
			boolean updateResult = super.runUpdate(model);
			flag = updateResult;
		}else{			
			//���÷�У״̬Ϊ��У
			model.setFxzt(JQFXWWHZT);	
			boolean insertResult = super.runInsert(model);
			if(!insertResult){
				flag = insertResult;
			}
		}		
		return flag;
	}
		
	/** 
	 * @����:TODO(��ȡ���ڷ�У���������еķ�У����)
	 * @���ߣ�������[���ţ�995]
	 * @���ڣ�2016-3-16 ����01:44:42
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * @throws Exception
	 * JqfxjcszForm �������� 
	 * @throws 
	 */
	public String getFxdm() throws Exception {
		JqfxjcszService jqfxjcszservice = new JqfxjcszService();
		JqfxjcszForm jcszModel = jqfxjcszservice.getModel();
		String fxdm = jcszModel.getFxdm();
		return fxdm;
	}
	
	/**
	 * 
	 * @����:TODO(������ȡ���ݿ������δ��У��¼)
	 * @���ߣ�������[���ţ�995]
	 * @���ڣ�2016-3-7 ����10:42:25
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * @throws Exception
	 * String �������� 
	 * @throws
	 */
	public String getCountNum(JqfxwhForm model,User user)throws Exception {
		return dao.getCountNum(model,user);
	}
	
	/**
	 * 
	 * @����:TODO(�����������ά��)
	 * @���ߣ�������[���ţ�995]
	 * @���ڣ�2016-3-11 ����09:59:38
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean plSaveJqwh(JqfxwhForm model,User user) throws Exception {					
		boolean flag = false;
		model.setFxdm(getFxdm());
		String currXn = Base.currXn; //��ǰѧ��	
		model.setXn(currXn);
		model.setXq("01".equals(Base.currXq)?"��":"��");	
		model.setFxzt(JQFXWHZT);
		flag = dao.plSaveJqwh(model,user);		
		return flag;
	}
	
	/**
	 * 
	 * @����:TODO(�������������ڷ�У)
	 * @���ߣ�������[���ţ�995]
	 * @���ڣ�2016-4-19 ����11:25:43
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean pldgSaveJqwh(JqfxwhForm model,User user) throws Exception{
		 
			boolean flag = false;
			model.setFxdm(getFxdm());
			String currXn = Base.currXn; //��ǰѧ��	
			model.setXn(currXn);
			model.setXq("01".equals(Base.currXq)?"��":"��");	
			model.setFxzt(JQFXWHZT);			
				
			flag = dao.pldgSaveJqwh(model,user);		
			return flag;
	 }
	
	/**
	 * 
	 * @����:TODO(��ȡ�������)
	 * @���ߣ�������[���ţ�995]
	 * @���ڣ�2016-3-14 ����06:32:14
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param user
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getCyyyList(User user) {
		return dao.getCyyyList(user);
	}
	
	/**
	 * 
	 * @����:TODO(����δ��У����ԭ��)
	 * @���ߣ�������[���ţ�995]
	 * @���ڣ�2016-3-15 ����11:20:45
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param user
	 * @param gnid
	 * @param shyj
	 * @return
	 * boolean �������� 
	 * @throws
	 */  
	public boolean saveCyyy(User user,String[] cyyy){			
			if (cyyy == null || cyyy.length == 0 )
				return false;						
			try {
				boolean b = dao.delCyyy(user);				
				if (b){
					return dao.saveCyyy(user,cyyy);
				}				
				return b;
			} catch (Exception e) {
				return false;
			}
		}
	
	/**
	 * 
	 * @����:TODO(����ѧ������δ��Уά��)
	 * @���ߣ�������[���ţ�995]
	 * @���ڣ�2016-4-18 ����05:09:35
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean saveplxsJqWfxwh(JqfxwhForm model) throws Exception {
		
		model.setXq("��".equals(model.getXq())?"01":"02");	
		model.setFxdm(getFxdm());		
		//���շ�У
		boolean flag=true;
			
		model.setFxdm(getFxdm());
		String currXn = Base.currXn; //��ǰѧ��	
		model.setXn(currXn);
		model.setXq("01".equals(Base.currXq)?"��":"��");	
		model.setFxzt(JQFXWWHZT);		
		dao.pldgSaveJqwfx(model);	
		return flag;
		
	}
}
