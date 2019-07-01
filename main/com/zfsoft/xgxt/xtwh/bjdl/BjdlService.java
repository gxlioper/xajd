/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-8-27 ����09:07:40 
 */  
package com.zfsoft.xgxt.xtwh.bjdl;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.UniqID;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: �༶�������� 
 * @���ߣ� Penghui.Qu [���ţ�445]
 * @ʱ�䣺 2013-8-27 ����09:07:40 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class BjdlService extends SuperServiceImpl<BjdlModel, BjdlDao> {

	private BjdlDao dao = new BjdlDao();
	
	public BjdlService(){
		super.setDao(dao);
	}
	
	
	/**
	 * 
	 * @����: ���ð༶����
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2013-8-27 ����02:51:14
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * boolean �������� 
	 * @throws Exception 
	 */
	public boolean szBjdl(BjdlModel model,String[] bjdm) throws Exception{

		//��ʼ���༶
		dao.initBjdl();
		
		if (bjdm == null || bjdm.length == 0){
			throw new NullPointerException("bjdm is null !");
		}
		
		String lbdm = model.getLbdm();
		
		if (StringUtil.isNull(lbdm)){
			lbdm = UniqID.getInstance().getUniqIDHash();
			dao.insertBjdl(lbdm, model.getLbmc());
		}
		
		
		return dao.szBjdl(lbdm, bjdm);
	}
	
	
	
	/**
	 * 
	 * @����: ��ѯ�༶�����б�
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2013-8-27 ����02:59:31
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getBjdlList(){
		return dao.getBjdlList();
	}
	
	
	
	/**
	 * 
	 * @����: ��ѯ�༶��Ϣ
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2013-8-27 ����03:03:26
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getBjxxList(BjdlModel model){
		
		String bjdm = model.getBjdm();
		
		if (StringUtil.isNull(bjdm)){
			throw new NullPointerException("bjdm is null !");
		}
		
		String[] bjdmArray = bjdm.split(",");
		
		return dao.getBjxxList(bjdmArray);
	}
	
	
	/**
	 * 
	 * @����:ȡ���༶����
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-8-19 ����10:11:14
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param bjdm
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean qxBjdl(String[] bjdm, BjdlModel model) throws Exception{
		return dao.qxBjdl(bjdm, model);
	}
	
}
