/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-9-7 ����05:14:06 
 */  
package com.zfsoft.xgxt.zxdk.rwfbybc.sqsh;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import xgxt.form.User;

import com.zfsoft.xgxt.base.service.SuperAuditService;
import com.zfsoft.xgxt.zxdk.rwfbybc.dcjg.RwfbydcjgDao;
import com.zfsoft.xgxt.zxdk.rwfbybc.dcjg.RwfbydcjgModel;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ChenQ[����:856]
 * @ʱ�䣺 2015-9-7 ����05:14:06 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class RwfbysqshService extends SuperAuditService<RwfbysqshModel, RwfbysqshDao> {
	private static final String SQSH = "1";
	
	@Override
	public boolean afterLastAudit(RwfbysqshModel model) {
		
		try {
			RwfbydcjgModel jgModel = new RwfbydcjgModel();
			BeanUtils.copyProperties(jgModel, model);
			RwfbydcjgDao jgDao = new RwfbydcjgDao();
			boolean flag = jgDao.isExists(jgModel);
			if(flag){
				jgDao.deleteByKey(jgModel.getXh());
			}
			dao.runUpdate(model);
			jgModel.setDclb(model.getZd2());
			jgModel.setDcje(model.getZd6());
			jgModel.setSjly(SQSH);
			return jgDao.runInsert(jgModel);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	
	@Override
	public boolean deleteResult(RwfbysqshModel model) {
		try {
			return new RwfbydcjgDao().runDelete(new String[]{model.getId()}) > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
    
	public List<HashMap<String, String>> getAudingList(RwfbysqshModel model,User user) throws Exception{
		return dao.getAudingList(model,user);
	}
	
	/**
	 * 
	 * @����:��֤�Ƿ�������
	 * @���ߣ�ChenQ[���ţ�856]
	 * @���ڣ�2015-9-7 ����06:16:47
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean isExists(RwfbysqshModel model) {	
		return dao.isExists(model);
	}


	/** 
	 * @����:��������ѧ��ѧ���ȡ��Դ�ش�����Ϣ(��������)
	 * @���ߣ�lgx[���ţ�1553]
	 * @���ڣ�2018-5-16 ����02:46:39
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param id
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws 
	 */
	public HashMap<String, String> getSyddkxx(String id) {
		return dao.getSyddkxx(id);
	}


	/** 
	 * @����:��������ѧ�ţ�ѧ���ȡУ�ڴ�����Ϣ(������һ�仰�����������������)
	 * @���ߣ�lgx[���ţ�1553]
	 * @���ڣ�2018-5-16 ����02:54:02
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param id
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws 
	 */
	public HashMap<String, String> getXyddkxx(String id) {
		return dao.getXyddkxx(id);
	}
}
