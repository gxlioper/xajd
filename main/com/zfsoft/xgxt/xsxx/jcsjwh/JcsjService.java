package com.zfsoft.xgxt.xsxx.jcsjwh;

import java.util.HashMap;
import java.util.List;

import xgxt.action.Base;
import xgxt.form.User;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @�๦������:��������ά�����꼶ѧԺרҵ�༶��
 * @���ߣ� Qilm[����:964]
 * @ʱ�䣺 2013-12-5 ����03:31:54 
 * @�汾�� V1.0
 */
public class JcsjService extends SuperServiceImpl<JcsjForm, JcsjDAO> {
	
	private JcsjDAO dao = new JcsjDAO();
	
	public JcsjService() {
		super.setDao(dao);
		
	}
//
//	/** 
//	 * @����: ��ѯȡ����������
//	 * @���ߣ�Qilm[���ţ�964]
//	 * @���ڣ�2013-12-6 ����09:02:53
//	 * @param myForm
//	 * @param user
//	 * @return
//	 * int �������� 
//	 * @throws 
//	 */
//	public int getCounts(JcsjForm myForm, User user) throws Exception {
//		return dao.getCounts(myForm, user);
//	}
//	
//	/**
//	 * 
//	 * @����: ���±�ҵ����
//	 * @���ߣ�Qilm[���ţ�964]
//	 * @���ڣ�2013-12-6 ����10:30:31
//	 * @param model
//	 * @param user
//	 * @return
//	 * @throws Exception
//	 * boolean �������� 
//	 * @throws
//	 */
//	public boolean runUpdate(JcsjForm model, User user) throws Exception{
//		return dao.runUpdate(model, user);
//	}
//
//	/** 
//	 * @����: ȡ����ҵ����
//	 * @���ߣ�Qilm[���ţ�964]
//	 * @���ڣ�2013-12-6 ����01:36:02
//	 * @param myForm
//	 * @param user
//	 * @return
//	 * boolean �������� 
//	 * @throws 
//	 */
//	public boolean runDelete(JcsjForm model, User user) throws Exception{
//		return dao.runDelete(model, user);
//	}

	/** 
	 * @����: ���ӻ�������
	 * @���ߣ�Qilm[���ţ�964]
	 * @���ڣ�2013-12-6 ����04:32:43
	 * @param myForm
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public String saveJcsj(JcsjForm myForm) throws Exception{
		
		// �ж��Ƿ��Ѵ���		
		if(!dao.ishasExist(myForm)){
			boolean bolFlg = dao.saveJcsj(myForm);
			if(bolFlg){
				new Thread(new Base.initialBj()).start();
			}
			return bolFlg ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		}else{
			return MessageKey.SYS_SAVE_DM_REPEAT;
		}
	}
	

	
	/**
	 * 
	 * @����: ��ȡרҵ�б�
	 * @���ߣ�Qilm[���ţ�964]
	 * @���ڣ�2013-12-9 ����09:12:26
	 * @param xydm
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getZyList(String xydm) {
		return dao.getZyList(xydm);
	}

	/** 
	 * @����: ���»�������
	 * @���ߣ�Qilm[���ţ�964]
	 * @���ڣ�2013-12-9 ����11:38:23
	 * @param myForm
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public String updJcsj(JcsjForm myForm) throws Exception{

		// �ж��Ƿ��Ѵ���		
		if(!dao.ishasExist(myForm)){
			boolean bolFlg = dao.updJcsj(myForm);
			if(bolFlg){
				new Thread(new Base.initialBj()).start();
			}
			return bolFlg ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		}else{
			return MessageKey.SYS_SAVE_DM_REPEAT;
		}
	}

	/** 
	 * @����: ɾ����������
	 * @���ߣ�Qilm[���ţ�964]
	 * @���ڣ�2013-12-9 ����03:29:43
	 * @param xzflg
	 * @param split
	 * @return
	 * int �������� 
	 * @throws 
	 */
	public int runDelete(String xzflg, String[] split) throws Exception{
		int count =  dao.runDelete(xzflg, split);
		if(count > 0){
			new Thread(new Base.initialBj()).start();
		}
		return count;
	}

	/** 
	 * @����: ��������б�
	 * @���ߣ�Qilm[���ţ�964]
	 * @���ڣ�2013-12-19 ����05:21:31
	 * @return
	 * Object �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getBmlbList() {
		return dao.getBmlbList();
	}

	/** 
	 * @����: ��ȡѧԺ�б�
	 * @���ߣ�Qilm[���ţ�964]
	 * @���ڣ�2013-12-25 ����02:30:33
	 * @param model
	 * @param user
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getAllList(JcsjForm model, User user) {

		return dao.getAllList(model, user);
	}
	
	/**
	 * @����: ��ȡѧԺ�б�
	 * @���ߣ�Qilm[���ţ�964]
	 * @���ڣ�2013-12-9 ����09:08:01
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getXyList() {
		return dao.getXyList();
	}

	/**
	 * @throws Exception  
	 * @����: �������ݵ���
	 * @���ߣ�Qilm[���ţ�964]
	 * @���ڣ�2014-1-3 ����03:58:16
	 * @param model
	 * @param user
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getAllListGet(JcsjForm model, User user) throws Exception {
		Pages pages = model.getPages();
		pages.setPageSize(Integer.MAX_VALUE);
		model.setPages(pages);
		return dao.getPageList(model, user);
		
	}
}
