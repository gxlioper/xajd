/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-8-4 ����11:40:07 
 */
package com.zfsoft.xgxt.xstgl.stgl.stjg;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import xgxt.form.User;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.xstgl.rtgl.rtjg.RtjgForm;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXX����ģ��
 * @�๦������: TODO(������һ�仰��������������)
 * @���ߣ� xiaxia [����:1104]
 * @ʱ�䣺 2015-8-4 ����11:40:07
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class StjgService extends SuperServiceImpl<StjgForm, StjgDao> {
	//private static final String DATE_FORMAT = "yyyy-MM-dd hh24:mm:ss";
	private StjgDao dao = new StjgDao();

	public boolean isHaveSbjg(StjgForm model) {
		return dao.isHaveSbjg(model);
	}

	/**
	 * @throws Exception
	 * 
	 * @����:���Ž�����ӱ���
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-8-4 ����09:54:01
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return boolean ��������
	 * @throws
	 */
	public boolean editStjg(StjgForm model) throws Exception {
		boolean result = true;
		if ("save".equals(model.getType())) {
			String stid = UniqID.getInstance().getUniqIDHash();
			model.setStid(stid);
			result = dao.runInsert(model);
			//����ָ����ʦ��
			boolean aa =dao.saveZdls(stid, model.getStid(), model.getXhs());
		} else {
			result = dao.runUpdate(model);
			//����ָ����ʦ��
			boolean aa =dao.saveZdls(model.getStid(), model.getStid(), model.getXhs());
			
		}
		return result;
	}

	/**
	 * @throws Exception
	 * 
	 * @����:��ȡ���Ž��
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-8-4 ����04:40:36
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return HashMap<String,String> ��������
	 * @throws
	 */
	public HashMap<String,String> getStjg(StjgForm t) throws Exception {
		return dao.getStjg(t);
	}
	public boolean isHaveSqJl(String values) throws Exception {
		if(values != null){
			values = StringUtils.replace(values, ",", "','");
			values = "'" + values + "'";
		}
		return dao.isHaveSqJl(values);
	}
	
	//�����ۺ�ά��ά������״̬ʱ����cysl
	public boolean update_stcysl(String cysl,String stid) throws Exception{
		return dao.update_stcysl(cysl, stid);
	}
	public List<HashMap<String, String>> getStxxCylist(StjgForm stjg,User user){
		return dao.getStxxCylist(stjg, user);
	}
	public List<HashMap<String, String>> getBbdmlist(){
		return dao.getBbdmlist();
	}

	public List<HashMap<String,String>> getZdlsInfo(StjgForm myForm) {
		return dao.getZdlsInfo(myForm);
	}

    public List<HashMap<String,String>> getJgAll(StjgForm model, User user) throws Exception{
		return dao.getJgAll(model,user);
    }
}
