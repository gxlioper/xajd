/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-3-8 ����10:58:38 
 */  
package com.zfsoft.xgxt.xsxx.wbdzc;
import java.util.HashMap;
import java.util.List;
import xgxt.action.Base;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
	/**
	 * @ϵͳ����: ѧ����������ϵͳ
	 * @ģ������: ѧ����Ϣ����ģ��
	 * @�๦������:  
	 * @���ߣ� ����[����:1186]
	 * @ʱ�䣺 2016-3-16 ����09:38:51 
	 * @�汾�� V1.0
	 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
	 */

	public class WbdzclbService extends SuperServiceImpl<WbdzclbForm,WbdzclbDao>{
		private WbdzclbDao dao = new WbdzclbDao();
		public static String _BCZSCID="-1";
		
		public WbdzclbService() {
			super.setDao(dao);
		}
		
		
		/**
		 * ���δ����ע������list
		 * ������1186��
		 */
		public List<HashMap<String, String>>  getPageList(WbdzclbForm model) throws Exception{
			return dao.getPageList(model);
		}
		
		
		/**
		 * @����:	��ȡ������ʹ���
		 * @���ߣ�	����[���ţ�1186]
		 * @���ڣ�	2016-3-16 ����09:40:08
		 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
		 * @return
		 * String �������� 
		 * @throws
		 */
		public String changeWbdlbdm() {
			String max = dao.getWbdlbdm();
			if(Base.isNull(max)){
				return "001";
			}else{
				max = String.valueOf((Integer.parseInt(max)+1));
				String pre = "";
				for(int i = 0; i < 3-max.length();i ++){
					pre+="0";
				}
				return pre+max;
			}
		}
		
		
		/**
		 * @����:	���Ӻ��޸�����
		 * @���ߣ�	����[���ţ�1186]
		 * @���ڣ�	2016-3-16 ����09:40:48
		 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
		 * @param model
		 * @param type
		 * @return
		 * @throws Exception
		 * boolean �������� 
		 * @throws
		 */
			public boolean saveLxInfo(WbdzclbForm model,String type) throws Exception{
				boolean result = false;
				if ("add".equals(type)) {
					String wbdlbdm = changeWbdlbdm();
					model.setWbdlbdm(wbdlbdm);
					return dao.addLxInfo(model);
				} else if ("update".equals(type)) {
					return dao.updateLxInfo(model);
				}
				return result;
		}
			
			
		/**
		 * �ж��Ƿ���ڽ��Ӧ��
		 */
		public String pdsfsy(String value) throws Exception{
			String resultWbdlbmc = "";
			String[] wbdlbdm = dao.pdsfsy(value);
			for(int i=0; i<wbdlbdm.length; i++){
				 if(i==wbdlbdm.length-1){
					 resultWbdlbmc+=wbdlbdm[i];
				 }else{
					 resultWbdlbmc+=wbdlbdm[i];
				 }
			 }
			 return resultWbdlbmc;
		}
		
		
		/**
		 * @����:	�ж�δ������������Ƿ����
		 * @���ߣ�	����[���ţ�1186]
		 * @���ڣ�	2016-3-16 ����09:42:00
		 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
		 * @param model
		 * @param type
		 * @return
		 * @throws Exception
		 * boolean �������� 
		 * @throws
		 */
		public boolean isExistByWbdlbdm(WbdzclbForm model, String type)throws Exception{
		     boolean flag = false;
			if("save".equalsIgnoreCase(type)){
				String num=dao.checkExistForSave(model);
				if(!"0".equalsIgnoreCase(num)){
					flag = true;
				}
			}else if("update".equalsIgnoreCase(type)){
				String num=dao.checkExistForUpdate(model);
				if(!"0".equalsIgnoreCase(num)){
					flag = true;
						}	
					}	    
					return  flag;   		
				}		
		}
