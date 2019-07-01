/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-9-9 ����12:06:43 
 */
package com.zfsoft.xgxt.rcsw.qjgl.qjlx;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������)
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2013-9-9 ����12:06:43
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class QjlxService extends SuperServiceImpl<QjlxForm, QjlxDao> {
	QjlxDao dao = new QjlxDao();

	/**
	 * �����ڲ���ɾ������
	 */
	public static String _BCZSCID="-1";
	public QjlxService() {
		this.setDao(dao);
	}

	/**
	 * @throws Exception
	 * 
	 * @����:�����������
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-9-9 ����06:46:35
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param qf
	 * @return boolean ��������
	 * @throws
	 */
	public boolean save(QjlxForm qf) throws Exception {
		qf.setQjlxid(dao.getNextId());
		return this.runInsert(qf);
	}
	/**
	 * 
	 * @����:�Ƿ���Խ������
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-9-9 ����07:19:19
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param qf
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean isCanAdd(QjlxForm qf) {
		return dao.checkQjlx(qf);
	}
	/**
	 * @throws Exception 
	 * 
	 * @����:ɾ��
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-9-13 ����02:31:26
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ids
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * String[] ��������   String���� 0Ϊ�ɹ�ɾ������Ϊ����ɾ����
	 * @throws
	 */
	public String[] delete(String[] ids) throws Exception{
		//StringBuffer del=new StringBuffer();
		List<String> delId=new ArrayList<String>();
		StringBuffer noDel=new StringBuffer();
		boolean isHaveNoId=false;
		if(null==ids||ids.length<=0){
			return null;
		}
		for(String str:ids){
			if(dao.isCanDel(str)){
				delId.add(str);//��¼ɾ��id
				//del.append(str);
				//del.append(",");
			}else{
				noDel.append(str);
				noDel.append(",");
				isHaveNoId=true;
			}
		}
		int i=delId.size()>0?runDelete(delId.toArray(new String[]{})):0;
		String str=noDel.toString();
		//ȥ�������ය��
		str=isHaveNoId?str.substring(0,str.length()-1):_BCZSCID;
		return new String[]{String.valueOf(i),str};
	}
}
