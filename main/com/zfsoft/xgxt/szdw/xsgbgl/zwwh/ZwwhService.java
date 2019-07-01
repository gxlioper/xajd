/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-8-7 ����10:28:41 
 */  
package com.zfsoft.xgxt.szdw.xsgbgl.zwwh;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ˼���������ģ��
 * @�๦������:ѧ���ɲ�ְ��Service
 * @���ߣ� zhangjw
 * @ʱ�䣺 2013-8-7 ����10:28:41 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ZwwhService extends SuperServiceImpl<ZwwhForm, ZwwhDAO> {

	private ZwwhDAO dao = new ZwwhDAO();

	public ZwwhService() {
		super.setDao(dao);
	}
	/**
	 * @����:��ȡְ���б�
	 * @���ߣ�zhangjw
	 * @���ڣ�2013-8-8 ����3:30:06
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> ��������
	 */
	public List<HashMap<String,String>> getZwList(String zwid){
		return dao.getZwList(zwid);
	}
	
	/**
	 * @����:����ְ��������ְ֤������
	 * @���ߣ�zhangjw
	 * @���ڣ�2013-8-13 ����9:20:12
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param lxdms
	 * @return
	 * @throws SQLException
	 * boolean ��������
	 */
	public int yzZwCount(String[] lxdms) throws SQLException{
		StringBuffer sql = new StringBuffer();
		if(lxdms.length>0){
			String tt= "";
			for (int i = 0; i < lxdms.length; i++) {
				tt = "'"+lxdms[i]+"',";
			}
			int m = tt.lastIndexOf(",");
			tt = tt.substring(0,m);
			sql.append(tt);
		}else{
			sql.append("''");
		}
		return dao.getZwCount(sql.toString());
	}
}
