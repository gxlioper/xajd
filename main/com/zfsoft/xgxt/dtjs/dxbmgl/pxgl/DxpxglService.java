package com.zfsoft.xgxt.dtjs.dxbmgl.pxgl;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @������������У��ѵ����service
 * @author������ ��1346��
 * @date��2017-11-1 ����02:55:21 
 */
public class DxpxglService extends SuperServiceImpl<DxpxglForm, DxpxglDao> {
	/** 
	 * @����������������ѵ��Ϣ
	 * @author������ ��1346��
	 * @date��2017-11-6 ����09:10:29 
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws 
	 */
	public boolean save(DxpxglForm model) throws Exception {
		return dao.save(model);
	}
	/** 
	 * @����������������ѵ��Ϣ
	 * @author������ ��1346��
	 * @date��2017-11-6 ����09:13:00 
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws 
	 */
	public boolean update(DxpxglForm model) throws Exception {
		return dao.update(model);
	}
	/** 
	 * @�������������°ٷֱ�
	 * @author������ ��1346��
	 * @date��2017-11-6 ����09:13:35 
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws 
	 */
	public boolean updatebfb(DxpxglForm model) throws Exception {
		return dao.updatebfb(model);
	}
	/** 
	 * @����������ɾ����ѵ��Ϣ �ų�����ѧ��ѡ�����
	 * @author������ ��1346��
	 * @date��2017-11-6 ����09:14:50 
	 * @param ids
	 * @return
	 * @throws Exception
	 * int �������� 
	 * @throws 
	 */
	public int del(String[] ids) throws Exception{
		StringBuffer sql=new StringBuffer();
		if(ids.length>0){//��ֹΪ�ձ���
			sql.append("delete from xg_dtjs_dxpxb where id in (");
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
