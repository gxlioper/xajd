/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-4-20 ����06:27:43 
 */  
package com.zfsoft.xgxt.xszz.qxknszg;


import xgxt.form.User;
import xgxt.utils.GetTime;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;


/**
 * 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ������[����:995]
 * @ʱ�䣺 2016-4-26 ����10:13:40 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 * 
 */
public class QxknszgService extends SuperServiceImpl<QxknszgForm, QxknszgDao> {
	
	private QxknszgDao dao = new QxknszgDao();
	public static final String SAVE = "save";
	public static final String ONE = "one";
	public static final String MORE = "more";

	
	public QxknszgService(){
		super.setDao(dao);		
	}
	
	
	/**
	 * 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�������[���ţ�995]
	 * @���ڣ�2016-4-26 ����10:24:21
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean cancelKnsrdzg(QxknszgForm model,User user) throws Exception{
		 
		boolean flag = false;	
		model.setCzr(user.getUserName());
		model.setCzsj(GetTime.getTimeByFormat("yyyy-mm-dd hh24:mi:ss"));	
		if(ONE.equals(model.getQxtype())){			
			flag = dao.cancelOneKnsrdzg(model);			
		}else if(MORE.equals(model.getQxtype())){			
			flag = dao.cancelMoreKnsrdzg(model);			
		}else{
			flag = dao.cancelAllKnsrdzg(model,user);			
		}
		return flag;
		
	}
	
	/**
	 * 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�������[���ţ�995]
	 * @���ڣ�2016-4-26 ����10:24:27
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * String �������� 
	 * @throws
	 */
	public String getCountNum(QxknszgForm model,User user)throws Exception {
		
		return dao.getCountNum(model,user);	
		
	}
		
	
	/**
	 * 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�������[���ţ�995]
	 * @���ڣ�2016-4-27 ����09:25:16
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean saveKnsqxjlupdate(QxknszgForm model) throws Exception {	
		
		boolean flag=true;	
		if(SAVE.equals(model.getType())){			
			//���÷�У״̬Ϊ��У		
			boolean updateResult = super.runUpdate(model);
			flag = updateResult;
			
		}else{					
			boolean insertResult = super.runInsert(model);
			if(!insertResult){
				flag = insertResult;
			}				
		}		
		return flag;
		
	}
	
	

	/**
	 * 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�������[���ţ�995]
	 * @���ڣ�2016-4-27 ����09:25:24
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param type
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean isExistByKnsqxjl(QxknszgForm model,String type)
	throws Exception {
		
		boolean flag = false;
		if("save".equalsIgnoreCase(type)) {
			String num = dao.checkExistForKnsqxjlSave(model);
			if (!"0".equalsIgnoreCase(num)) {
				flag = true;
			}
		}else{
			String num = dao.checkExistForQxknszgUpdate(model);
			if (!"0".equalsIgnoreCase(num)) {
				flag = true;
			}
		}	
		return flag;
		
	}

}
