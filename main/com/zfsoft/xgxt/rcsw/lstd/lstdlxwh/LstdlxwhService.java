/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-11-25 ����09:36:52 
 */  
package com.zfsoft.xgxt.rcsw.lstd.lstdlxwh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.action.Base;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �ճ�����
 * @�๦������: ��ɫͨ��
 * @���ߣ� cq [����:785]
 * @ʱ�䣺 2014-11-25 ����09:36:52 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class LstdlxwhService extends SuperServiceImpl<LstdlxwhForm, LstdlxwhDao> {
	

		private LstdlxwhDao dao = new LstdlxwhDao();
		public static String _BCZSCID="-1";
		
		public LstdlxwhService() {
			super.setDao(dao);
		}
		
		/**
		 * 
		 * @����:��ȡ��ɫͨ������list
		 * @���ߣ�cq [���ţ�785]
		 * @���ڣ�2014-11-25 ����05:26:56
		 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
		 * @param model
		 * @return
		 * @throws Exception
		 * List<HashMap<String,String>> �������� 
		 * @throws
		 */
		public List<HashMap<String, String>>  getPageList(LstdlxwhForm model) throws Exception{
			return dao.getPageList(model);
		}
		
		/**
		 * 
		 * @����:��ȡ������ʹ���
		 * @���ߣ�cq [���ţ�785]
		 * @���ڣ�2014-11-25 ����05:30:52
		 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
		 * @return
		 * String �������� 
		 * @throws
		 */
		public String changeLxdm() {
			String max = dao.getMaxLxdm();
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
		 * 
		 * @����:���Ӻ��޸�����
		 * @���ߣ�cq [���ţ�785]
		 * @���ڣ�2014-11-25 ����05:31:49
		 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
		 * @param model
		 * @param type
		 * @return
		 * @throws Exception
		 * boolean �������� 
		 * @throws
		 */
		public boolean saveLxInfo(LstdlxwhForm model,String type) throws Exception{
			boolean result = false;
			if ("add".equals(type)) {
				String lxdm = changeLxdm();
				model.setLxdm(lxdm);
				return dao.addLxInfo(model);
			} else if ("update".equals(type)) {
				return dao.updateLxInfo(model);
			}
			return result;
		}

		/**
		 * 
		 * @����:�޸����� ������ѯ
		 * @���ߣ�cq [���ţ�785]
		 * @���ڣ�2014-11-25 ����05:33:51
		 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
		 * @param t
		 * @param xszbblxdm
		 * @return
		 * @throws Exception
		 * XszbblxwhForm �������� 
		 * @throws
		 */
		public LstdlxwhForm getLxwhForm(LstdlxwhForm t ,String lxdm) throws Exception{
			return dao.getLxwhForm(t,lxdm);
		}
		

		
		/**
		 * 
		 * @����:�ж������Ƿ��Ѿ����������� �� ������Ӧ��
		 * @���ߣ�cq [���ţ�785]
		 * @���ڣ�2014-11-25 ����05:35:27
		 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
		 * @param values
		 * @return
		 * @throws Exception
		 * String �������� 
		 * @throws
		 */
		public String checkLxdmForsqjg(String values)throws Exception{
	    	String resultLxmc="";
	    	String[] rcswLxmc=dao.checkLxdmForsqjg(values);
	    	for(int i=0;i<rcswLxmc.length;i++){
				if(i==rcswLxmc.length-1){
					resultLxmc+=rcswLxmc[i];
				}else{
					resultLxmc+=rcswLxmc[i]+",";
				}
				
			}
			return resultLxmc;
		}
		
		/**
		 * 
		 * @����:ɾ������
		 * @���ߣ�cq [���ţ�785]
		 * @���ڣ�2014-11-25 ����05:53:55
		 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
		 * @param ids
		 * @return
		 * @throws Exception
		 * String[] �������� 
		 * @throws
		 */
		public String[] deleteLxwhInfo(String[] ids) throws Exception{
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
					HashMap<String, String> hm=dao.getLxsq(str);
					noDel.append(hm.get("lxmc"));
					noDel.append(",</br>");
					isHaveNoId=true;
				}
			}
			int i=delId.size()>0?lstdsqDelete(delId.toArray(new String[]{})):0;
			String str=noDel.toString();
			//ȥ�������ය��
			str=isHaveNoId?str:_BCZSCID;
			
			return new String[]{String.valueOf(i),str};
		}


		/**
		 * 
		 * @����:ɾ����ɫͨ������ά��
		 * @���ߣ�cq [���ţ�785]
		 * @���ڣ�2014-11-26 ����08:42:45
		 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
		 * @param lxdm
		 * @return
		 * @throws Exception
		 * int �������� 
		 * @throws
		 */
		private int lstdsqDelete(String[] lxdm) throws Exception {
			
			return runDelete(lxdm);
		}

}
