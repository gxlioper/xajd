/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-7-28 ����09:11:27 
 */  
package com.zfsoft.xgxt.zxdk.ysjxj.dmwh;

import java.sql.SQLException;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;


/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ��ѧ����-Ժ�轱ѧ���㽭��ѧ���Ի�����
 * @�๦������: TODO(Service) 
 * @���ߣ� MengWei[����:1186]
 * @ʱ�䣺 2016-7-28 ����09:11:27 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class DmwhService extends SuperServiceImpl<DmwhForm, DmwhDao>{
	private DmwhDao dao = new DmwhDao();
	public DmwhService(){
		super.setDao(dao);
	}
	
	/**
	 * @����:TODO(�ж�;�������Ƿ����)
	 * @���ߣ�����[���ţ�1186]
	 * @���ڣ�2016-7-28 ����02:45:57
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param form
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean isExistByZjlymc(DmwhForm form){
		 boolean flag = false;
		 String num = dao.fftjCkeckExist(form);
		 if(!"0".equalsIgnoreCase(num)){
			 flag = true;
		 }
		 return flag;
	 }
	
	/**
	 * @����:TODO(��ȡ��һ������;������)
	 * @���ߣ�����[���ţ�1186]
	 * @���ڣ�2016-7-28 ����02:47:34
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * @throws SQLException
	 * int �������� 
	 * @throws
	 */
	public int getNextZjlydm() throws SQLException{
		int maxZjlydm=0;
		maxZjlydm = dao.maxZjlydm()+1;
		return maxZjlydm;
	}
	
	/**
	 * @����:TODO(�ж��ʽ���Դ������Ժ�轱ѧ�����Ƿ�ʹ��)
	 * @���ߣ�����[���ţ�1186]
	 * @���ڣ�2016-7-28 ����03:55:01
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param value
	 * @return
	 * @throws Exception
	 * String �������� 
	 * @throws
	 */
	public String checkZjlymcForYsjxjjg(String value) throws Exception{
		String resultZjlymc = "";
		 String[] zjlydm = dao.tjcheckZjlymcForYsjxjjg(value);
		 for(int i=0; i<zjlydm.length; i++){
			 if(i==zjlydm.length-1){
				 resultZjlymc+=zjlydm[i];
			 }else{
				 resultZjlymc+=zjlydm[i];
			 }
		 }
		 return resultZjlymc;
	}
}
