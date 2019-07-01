/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2017-8-9 ����03:42:45 
 */  
package com.zfsoft.xgxt.szdw.xfjs;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.axcs.wpsh.WpshForm;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.szdw.bfjs.bfjsgl.BfjsglForm;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������:ѧ�罨��ά��ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� �Ų�·[����:1352]
 * @ʱ�䣺 2017-8-9 ����03:42:45 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class xfjsService extends SuperServiceImpl<xfjsForm,xfjsDao>{

private xfjsDao dao = new xfjsDao();
	
	@SuppressWarnings("deprecation")
	public xfjsService(){
		super.setDao(dao);
	}

	/** 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�CP[���ţ�1352]
	 * @���ڣ�2017-8-10 ����10:04:32
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param myForm
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean isExist(xfjsForm myForm) {
		// TODO �Զ����ɷ������
		return dao.isExist(myForm);
	}

	/** 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�CP[���ţ�1352]
	 * @���ڣ�2017-8-10 ����10:04:55
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param myForm
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean saveDataXf(xfjsForm myForm) throws Exception {
		// TODO �Զ����ɷ������
		return dao.saveDataXf(myForm);
	}

	/**
	 * @throws Exception  
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�CP[���ţ�1352]
	 * @���ڣ�2017-8-10 ����03:36:30
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param myForm
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean updateData(xfjsForm myForm) throws Exception {
		// TODO �Զ����ɷ������
		return dao.updateData(myForm);
	}
	
	public xfjsForm getModel(xfjsForm form) throws Exception{
		xfjsForm model = dao.getModel(form);
		HashMap<String, String> xqxslb = dao.getxqxslbfdy(model.getBjdm());
		model.setXqmc(dao.getxqmc(model.getXq()));
		model.setBjmc(xqxslb.get("bjmc"));
		model.setPyccmc(xqxslb.get("pyccmc"));
		model.setYxmc(xqxslb.get("xqmc"));
		model.setFdy(xqxslb.get("fdy"));
		model.setXymc(xqxslb.get("xymc"));
		return model;
	}
	
	/**
	 * @throws Exception  
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�CP[���ţ�1352]
	 * @���ڣ�2017-8-11 ����09:45:50
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ids
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean delXf(String[] ids) throws Exception {
		// TODO �Զ����ɷ������
		return dao.delXf(ids);
	}

	/**
	 * @throws Exception  
	 * @����:��ȡ�༶�б�
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2017-8-11 ����04:01:38
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getBjList(xfjsForm model, User user) throws Exception {
		return dao.getBjList(model,user);
	}
}
