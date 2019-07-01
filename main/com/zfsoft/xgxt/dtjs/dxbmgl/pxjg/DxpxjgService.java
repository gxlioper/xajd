package com.zfsoft.xgxt.dtjs.dxbmgl.pxjg;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @������������У��ѵ���service
 * @author������ ��1346��
 * @date��2017-11-1 ����03:49:44 
 */
public class DxpxjgService extends SuperServiceImpl<DxpxjgForm, DxpxjgDao> {
	/** 
	 * @����������������ѵ���
	 * @author������ ��1346��
	 * @date��2017-11-10 ����05:44:48 
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws 
	 */
	public boolean save(DxpxjgForm model) throws Exception {
		return dao.save(model);
	}
	/** 
	 * @������������ȡѧ����ѵ��Ϣ
	 * @author������ ��1346��
	 * @date��2017-11-11 ����09:57:55 
	 * @param jgid
	 * @return
	 * @throws Exception
	 * HashMap<String,String> �������� 
	 * @throws 
	 */
	public HashMap<String, String> getXspx(String jgid) throws Exception {
		return dao.getXspx(jgid);
	}
	/** 
	 * @��������������ѧ�Ż�ȡ��ѵ�б�
	 * @author������ ��1346��
	 * @date��2017-11-11 ����11:52:11 
	 * @param t
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getPageListOfPx(DxpxjgForm t) throws Exception {
		return dao.getPageListOfPx(t);
	}
	/** 
	 * @��������������ѧ�ź���ѵid��ȡ��ѵ��Ϣ
	 * @author������ ��1346��
	 * @date��2017-11-11 ����03:20:29 
	 * @param jgid
	 * @return
	 * @throws Exception
	 * HashMap<String,String> �������� 
	 * @throws 
	 */
	public HashMap<String, String> getXspxByxhpxid(String xh,String pxid) throws Exception {
		return dao.getXspxByxhpxid(xh,pxid);
	}
	/** 
	 * @������������������
	 * @author������ ��1346��
	 * @date��2017-11-11 ����04:47:49 
	 * @param t
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws 
	 */
	public boolean updatePf(DxpxjgForm t) throws Exception {
		return dao.updatePf(t);
	}
	/** 
	 * @��������������ɾ��
	 * @author������ ��1346��
	 * @date��2017-11-11 ����04:59:01 
	 * @param ids
	 * @return
	 * @throws Exception
	 * int �������� 
	 * @throws 
	 */
	public int del(String[] ids) throws Exception{
		StringBuffer sql=new StringBuffer();
		if(ids.length>0){//��ֹΪ�ձ���
			sql.append("delete from XG_DTJS_DXBMJGB where jgid in (");
			for (int i = 0; i < ids.length; i++) {
				if (i==ids.length-1) {
					sql.append("?)");
				}else{
					sql.append("?,");
				}
			}
		}
		return dao.del(sql.toString(), ids);
	}
}
