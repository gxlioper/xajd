/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-11-12 ����09:35:25 
 */  
package com.zfsoft.xgxt.zxdk.dkbc.sqsh;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.service.SuperAuditService;
import com.zfsoft.xgxt.zxdk.dkbc.bcjg.BcjgDao;
import com.zfsoft.xgxt.zxdk.dkbc.bcjg.BcjgModel;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: �����ȼ�����
 * @���ߣ� �����[����:445]
 * @ʱ�䣺 2014-11-12 ����09:35:25 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class DkbcService extends SuperAuditService<DkbcModel, DkbcDao> {

	private static final String SQSH = "1";
	
	/*
	      ����: @see com.zfsoft.xgxt.base.service.SuperAuditService#afterLastAudit(com.zfsoft.xgxt.base.model.SuperAuditModel)
	 */
	
	@Override
	public boolean afterLastAudit(DkbcModel model) {
		
		BcjgModel jdqk = new BcjgModel();
		
		try {
			dao.runUpdate(model);
			
			BeanUtils.copyProperties(jdqk, model);
			BcjgDao bcjgDao = new BcjgDao();
			String count = bcjgDao.getCountByXhAndXn(jdqk);
			if(Integer.parseInt(count)>0){
				bcjgDao.deleteByKey(jdqk.getXh());
			}
			jdqk.setDclb(model.getZd2());
			jdqk.setDcje(model.getZd6());
			jdqk.setSjly(SQSH);
			return bcjgDao.runInsert(jdqk);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return false;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.service.SuperAuditService#deleteResult(com.zfsoft.xgxt.base.model.SuperAuditModel)
	 */
	
	@Override
	public boolean deleteResult(DkbcModel model) {
		try {
			return new BcjgDao().runDelete(new String[]{model.getId()}) > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
	public List<HashMap<String, String>> getAudingList(DkbcModel t, User user)
			throws Exception {
		
		return dao.getAudingList(t, user);
	}

	/**��ѧ��ѧ���ѯ�Ƿ������� */
	public String getCountByXhAndXn(DkbcModel djjdForm) {
		
		return dao.getCountByXhAndXn(djjdForm);
	}
	
	/**
	 * 
	 * @����:��ȡ����List
	 * @���� ChenQ[���ţ�856]
	 * @���ڣ�2015-9-6 ����02:32:27
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getYhList(){
		return dao.getYhList();
	}
	
	/**
	 * 
	 * @����:��ȡ�������List
	 * @���� ChenQ[���ţ�856]
	 * @���ڣ�2015-9-6 ����02:32:27
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getDclbList(){
		return dao.getDclbList();
	}
	

	/**
	 * 
	 * @����:��ȡѧ��List
	 * @���� ChenQ[���ţ�856]
	 * @���ڣ�2015-9-6 ����02:32:27
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getXfList(){
		return dao.getXfList();
	}
	
	//����ʦ����ѧ��������������ѯ��ѧ�������������Դ�ش������������
	public HashMap<String, String> getHzsfDxDklxChange(String xh,String dklx,String xn){
		return dao.getHzsfDxDklxChange(xh, dklx, xn);
	}
	
	public HashMap<String, String> produceDataMap(String xh,String dklx,String xn){
		HashMap<String, String> datamap = this.getHzsfDxDklxChange(xh, dklx, xn);
		if(datamap != null){
			String dkkssj = datamap.get("dkkssj");
			String dkjssj = "";
			String dkqx = datamap.get("dkqx");
			String n = datamap.get("n");
			String y = datamap.get("y");
			String d = datamap.get("d");
			if(StringUtils.isNotNull(dkkssj) && StringUtils.isNotNull(dkqx) && dkkssj.length() == 10 && dkkssj.indexOf("-")!=-1){
				dkjssj = this.produceDkjssj(dkkssj, dkqx, dklx);
			}else{
				datamap.put("dkjssj", "");
				datamap.put("dkkssj", "");
			}
		}
		
		return datamap;
	}
	
	public String produceDkjssj(String dkkssj,String dkqx,String dklx){
		
		return dao.produceDkjssj(dkkssj, dkqx, dklx);
	}
}
