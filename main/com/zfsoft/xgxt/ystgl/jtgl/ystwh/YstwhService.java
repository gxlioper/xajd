/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-8-4 ����11:40:07 
 */
package com.zfsoft.xgxt.ystgl.jtgl.ystwh;

import java.util.HashMap;

import org.apache.commons.lang.StringUtils;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.UniqID;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXX����ģ��
 * @�๦������: TODO(������һ�仰��������������)
 * @���ߣ� xiaxia [����:1104]
 * @ʱ�䣺 2015-8-4 ����11:40:07
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class YstwhService extends SuperServiceImpl<YstwhForm, YstwhDao> {
	//private static final String DATE_FORMAT = "yyyy-MM-dd hh24:mm:ss";
	private YstwhDao dao = new YstwhDao();

	public boolean isHaveYstxx(YstwhForm model) {
		return dao.isHaveYstxx(model);
	}

	/**
	 * @throws Exception
	 * 
	 * @����:�����Ž�����ӱ���
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-8-4 ����09:54:01
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return boolean ��������
	 * @throws
	 */
	public boolean editYstwh(YstwhForm model) throws Exception {
		boolean result = true;
		if ("save".equals(model.getType())) {
			String ystid = UniqID.getInstance().getUniqIDHash();
			model.setYstid(ystid);
			result = dao.runInsert(model);
		} else {
			result = dao.runUpdate(model);
			
		}
		return result;
	}

	/**
	 * @throws Exception
	 * 
	 * @����:��ȡ�����Ž��
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-8-4 ����04:40:36
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return HashMap<String,String> ��������
	 * @throws
	 */
	public HashMap<String,String> getYstwh(YstwhForm t) throws Exception {
		return dao.getYstwh(t);
	}
	public boolean isHaveSqJl(String values) throws Exception {
		if(values != null){
			values = StringUtils.replace(values, ",", "','");
			values = "'" + values + "'";
		}
		return dao.isHaveSqJl(values);
	}
	
	//�������ۺ�ά��ά������״̬ʱ����cysl
	public boolean update_stcysl(String cysl,String stid) throws Exception{
		return dao.update_stcysl(cysl, stid);
	}
	
	
	

}
