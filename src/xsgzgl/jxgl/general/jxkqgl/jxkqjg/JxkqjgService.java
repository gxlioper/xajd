/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-7-7 ����07:29:38 
 */  
package xsgzgl.jxgl.general.jxkqgl.jxkqjg;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxForm;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����[����:1104]
 * @ʱ�䣺 2015-7-7 ����07:29:38 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class JxkqjgService extends SuperServiceImpl<JxkqjgForm, JxkqjgDao> {
	/**
	 * 
	 * @����:��ȡ���������б�
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2015-7-6 ����10:23:19
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getKqlx() {
		return dao.getKqlx();
	}
	/**
	 * 
	 * @����:��ȡ��������б�
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2015-7-8 ����10:49:31
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getKqlb() {
		return dao.getKqlb();
	}
	/**
	 * 
	 * @����:��ȡ������Ϣ
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2015-7-8 ����11:39:44
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param kqid
	 * @return
	 * @throws Exception
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String> getKqxx(String kqid)
	throws Exception {
	return dao.getKqxx(kqid);
	}
	
	public List<HashMap<String, String>> getJxxsList(JxkqjgForm model,User user) throws Exception {
		return dao.getJxxsList(model,user);
	}

}
