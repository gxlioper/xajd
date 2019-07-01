package xsgzgl.gygl.gyccgl.dmwh;

import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;


public class GyccDmwhService extends SuperServiceImpl<GyccDmwhForm, GyccDmwhDao> {
	/**
	 * 
	 * @����: �𻵳̶Ȼ�ȡmodel
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-3-6 ����11:46:18
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param key
	 * @return
	 * @throws Exception
	 * GyccDmwhForm �������� 
	 * @throws
	 */
	public GyccDmwhForm getBscdForm(String key) throws Exception{
		return dao.getBscdForm(key);
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @����:������Ʒ
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-3-6 ����04:26:39
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param form
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean saveWpForm(GyccDmwhForm form) throws Exception{
		boolean flag = true;
		/**
		 *���ӡ��޸Ĳ��������ж�
		 */
		if("add".equals(form.getType())){
			if(!dao.checkIsNotExistWpdm(form.getDm(), null, form.getType())){
				throw new SystemException(MessageKey.SYS_DMWH_DM_EXISTS);//�����ظ�
			}
			if(!dao.checkIsNotExistWpdm(null, form.getMc(), form.getType())){
				throw new SystemException(MessageKey.SYS_DMWH_MC_EXISTS);//�����ظ�
			}
			flag = dao.runInsert(form);
		}else{
			if(!dao.checkIsNotExistWpdm(form.getDm(), form.getMc(), form.getType())){
				throw new SystemException(MessageKey.SYS_DMWH_MC_EXISTS);//�����ظ�
			}
			flag = dao.runUpdate(form);
		}
		return flag;
	}
	
	/**
	 * 
	 * @����: �����𺦳̶�
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-3-6 ����05:13:36
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward �������� 
	 * @throws
	 */
	public boolean saveShcdForm(GyccDmwhForm form) throws Exception{
		boolean flag = true;
		/**
		 *���ӡ��޸Ĳ��������ж�
		 */
		if("add".equals(form.getType())){
			if(!dao.checkIsNotExistShcd(form.getShcddm(), null, form.getType())){
				throw new SystemException(MessageKey.SYS_DMWH_DM_EXISTS);//�����ظ�
			}
			if(!dao.checkIsNotExistShcd(null, form.getShcdmc(), form.getType())){
				throw new SystemException(MessageKey.SYS_DMWH_MC_EXISTS);//�����ظ�
			}
		
		}else{
			if(!dao.checkIsNotExistShcd(form.getShcddm(), form.getShcdmc(), form.getType())){
				throw new SystemException(MessageKey.SYS_DMWH_MC_EXISTS);//�����ظ�
			}
			
		}
		flag = dao.saveShcd(form, form.getType());
		return flag;
	}
	
	/**
	 * 
	 * @����:ɾ���𺦳̶�
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-3-6 ����05:50:27
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param shcddm
	 * @return
	 * @throws Exception
	 * int �������� 
	 * @throws
	 */
	public int DelShcd(String[] shcddm) throws Exception{
		return dao.DelShcd(shcddm);
	}
	
	/**
	 * 
	 * @����: ��Ʒ�����Ƿ��ѱ�ʹ��
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-3-7 ����09:59:17
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * String[] �������� 
	 * @throws
	 */
	public boolean isWpdmNotUserd(String[] dms){
		return dao.isWpdmNotUserd(dms);
	}
	
	/**
	 * 
	 * @����: �𺦳̶��Ƿ��ѱ�ʹ��
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-3-7 ����09:59:17
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * String[] �������� 
	 * @throws
	 */
	public boolean isShcdNotUserd(String[] dms){
		return dao.isShcdNotUserd(dms);
	}
	
}
