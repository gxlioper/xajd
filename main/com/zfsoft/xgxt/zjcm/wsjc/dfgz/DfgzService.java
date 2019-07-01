/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-3-2 ����09:00:15 
 */  
package com.zfsoft.xgxt.zjcm.wsjc.dfgz;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

import xgxt.form.User;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.zjcm.wsjc.pfz.PfzDao;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� cq [����:785]
 * @ʱ�䣺 2016-3-2 ����09:00:15 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class DfgzService extends SuperServiceImpl<DfgzForm, DfgzDao> {
	
	private PfzDao pfzDao = new PfzDao();
	
	/**
	 * @throws SQLException  
	 * @����:����ϵͳʱ��ȡ�·�
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-3-7 ����11:01:10
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xn
	 * @return
	 * List<String> �������� 
	 * @throws 
	 */
	public List<String> getYueFenByXn(String xn) throws SQLException{
		return dao.getYueFenByXn();
	}
	
	public String getXpmc(String xqdm){
		return dao.getXqmc(xqdm);
	}
	
	public List<HashMap<String, String>> getPfzList(){
		return dao.getPfzList();
	}

	/**
	 * @throws Exception  
	 * @����:��ѯ���м�¼������json��ʽ
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2016-3-11 ����09:10:39
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ccny
	 * @return
	 * DfgzForm �������� 
	 * @throws 
	 */
	public DfgzForm getAll(DfgzForm model) throws Exception {
		
		List<HashMap<String, String>> pfzszList = null;
		
		List<HashMap<String, String>> pfzList = dao.getPfzList();
		//����ѧ�ꡢѧ�ڡ�������²�ѯ�������
		DfgzForm myForm = dao.getDfszInfo(model);
		DfgzForm viewForm = new DfgzForm();
		if(null!=myForm&&!StringUtils.isBlank(myForm.getDfszid())){
			BeanUtils.copyProperties(viewForm, myForm);
			pfzszList = pfzszList(myForm.getDfszid());
		}
		
		viewForm.setPfzList(pfzList);
		viewForm.setPfzszList(pfzszList);
		
		return viewForm;
	}
	
	
	public List<HashMap<String, String>> pfzszList(String dfszid){
		return dao.pfzszList(dfszid);
	}
	

	/**
	 * @throws Exception  
	 * @����:��ѯ�Ƿ��Ѵ���
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2016-3-11 ����03:36:52
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param myForm
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean isExist(DfgzForm myForm) throws Exception {
		
		myForm = dao.getDfszInfo(myForm);
		
		if(null==myForm){
			return false;
		}
		return true;
	}

	/**
	 * @throws Exception  
	 * @����:�����ֹ���
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2016-3-12 ����11:47:05
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param myForm
	 * @param pfzList
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean saveDfgzSz(DfgzForm myForm, List<DfgzForm> pfzList) throws Exception {
		
		//ɾ��֮ǰ������������
		 delPfzSz(new String[]{myForm.getDfszid()});
		//ɾ��֮ǰ������
		 delPfzQs(new String[]{myForm.getDfszid()});
		//���ɳ������
		 boolean bl = true;
		 
		 for (int i = 0; i < pfzList.size(); i++) {
			 if(!"0".equals(pfzList.get(i).getCcbl())){
				 bl = dao.randomQs(myForm,pfzList.get(i));
				 if(!bl){
					 break;
				 }
			 }
		}
		 
		 if(bl){
			 //��������������
			 savePfzSz(myForm,pfzList);
		 }
		 
		return bl;
	}
	
	
	//ɾ��֮ǰ������������
	public boolean delPfzSz(String[] dfszid) throws Exception{
		
		boolean flg = true;
		
		for (int i = 0; i < dfszid.length; i++) {
			flg = dao.delPfzSz(dfszid[i]);
			if(!flg){
				break;
			}
		}
		return flg;
	}
	
	//ɾ��֮ǰ������
	public boolean delPfzQs(String[] dfszid) throws Exception{
		
		boolean flg = true;
		
		for (int i = 0; i < dfszid.length; i++) {
			flg = dao.delPfzQs(dfszid[i]);
			if(!flg){
				break;
			}
		}
		return flg;
	}
	
	
	/**
	 * @throws SQLException  
	 * @����:��������������
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2016-3-12 ����02:31:12
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param myForm
	 * @param pfzList
	 * void �������� 
	 * @throws 
	 */
	public boolean savePfzSz(DfgzForm myForm, List<DfgzForm> pfzList) throws SQLException {
		
		List<String[]> pfzxxList = new ArrayList<String[]>();
		String[] pfdx = null;
		for (DfgzForm model : pfzList) {
			pfdx = new String[4];
			pfdx[0] = myForm.getDfszid();
			pfdx[1] = model.getPfzid();
			pfdx[2] = model.getCcbl();
			pfdx[3] = model.getBhbyb();
			pfzxxList.add(pfdx);
		}
		return dao.pfzSzPlbc(pfzxxList);
		
	}

	/** 
	 * @����:�Ƿ����ύ��������
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2016-3-12 ����02:44:51
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param dfszid
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean wsfTj(String dfszid) {
		return dao.getWsfTj(dfszid);
	}

	/** 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2016-3-14 ����08:59:30
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean editGz(DfgzForm model) throws Exception {
		boolean result = true;
		if ("save".equals(model.getType())) {
			String xmid = UniqID.getInstance().getUniqIDHash();
			model.setDfszid(xmid);
			result = dao.runInsert(model);
		} else {
			result = dao.runUpdate(model);
		}
		return result;
	}

	/** 
	 * @����:�ж��Ƿ��г������
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2016-3-14 ����06:17:46
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param myForm
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean getExistCcsj(DfgzForm myForm) {
		return dao.getExistCcsj(myForm.getDfszid());
	}
	
	
	/**
	 * 
	 * @����:������Ҳ�ѯ
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2016-3-15 ����02:39:38
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getCcqsList(DfgzForm t, User user) throws Exception{
		return dao.getCcqsList(t, user);
	
	}

}
