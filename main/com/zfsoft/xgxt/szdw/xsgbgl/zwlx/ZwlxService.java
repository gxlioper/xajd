/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-8-7 ����10:28:41 
 */  
package com.zfsoft.xgxt.szdw.xsgbgl.zwlx;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ˼���������ģ��
 * @�๦������:ѧ���ɲ�ְ������Service
 * @���ߣ� zhangjw
 * @ʱ�䣺 2013-8-7 ����10:28:41 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ZwlxService extends SuperServiceImpl<ZwlxForm, ZwlxDAO> {

	private ZwlxDAO dao = new ZwlxDAO();

	public ZwlxService() {
		super.setDao(dao);
	}
	
	public List<HashMap<String, String>> getList(){
		return dao.getList();
	}
	/**
	 * @����:�����������Ʋ�ѯ����
	 * @���ߣ�zhangjw
	 * @���ڣ�2013-8-28 ����6:01:58
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param lxname
	 * @return
	 * @throws SQLException
	 * int ��������
	 */
	public boolean yzLxName(String lxname) throws SQLException{
		
		int result = dao.getCountBylxName(lxname);
		if(result>0){
			return false;
		}else{
			return true;
		}
		
	}
	
	/**
	 * 
	 * @����: �����������Ʋ�ѯ
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-10-26 ����10:21:41
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param splc
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String>  getSplcMc(String splc){
		return dao.getSplcMc(splc);
	}
	
	
}
