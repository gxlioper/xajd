/**
 * <p>Coyright (R) 2014 ��������ɷ����޹�˾��<p>
 */
package com.zfsoft.xgxt.hdgl.hdblsq;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.hdgl.hdbljg.HdbljgDao;
import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.form.User;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.transaction.TransactionControl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;

/**
 * @className	�� HdblsqshService
 * @description	�� TODO(��������������)
 * @author 		��������1282��
 * @date		�� 2018-1-16 ����05:20:17
 * @version 	V1.0 
 */

public class HdblsqshService extends SuperServiceImpl<HdblsqshForm, HdblsqshDao> {
	
	private ShlcInterface shlc = new CommShlcImpl();
	/**
	 * @description	�� ��ȡ������б�
	 * @author 		�� ������1282��
	 * @date 		��2018-1-17 ����03:15:20
	 * @return
	 */
	public List<HashMap<String,String>> getHdlxList(){
		return dao.getHdlxList();
	}
	
	/**
	 * @description	�� ��ȡ��ǰѧ��
	 * @author 		�� ������1282��
	 * @date 		��2018-1-18 ����02:04:45
	 * @return
	 */
	public String getCurrXq(){
		DAO dao = DAO.getInstance();
		return dao.getXqmcForXqdm(Base.currXq);
	}
	
	/**
	 * @description	�� ��������
	 * @author 		�� ������1282��
	 * @date 		��2018-1-18 ����11:16:52
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 */
	@TransactionControl
	public boolean saveSq(HdblsqshForm model, User user) throws Exception {
		String sqid = UniqID.getInstance().getUniqIDHash();
		model.setSqid(sqid);
		String splc = model.getSplc();
		if(model.getType().equals("save")){
			model.setShzt(Constants.YW_WTJ);
		}else{
			model.setShzt(Constants.YW_SHZ);
		}
		boolean flag = dao.runInsert(model);
		String[] hdbqs = model.getHdbqs();/*��ȡ���ǩ*/
		String[] nlbqs = model.getNlbqs();/*��ȡ������ǩ*/

		if(flag){
			/*������ǩ*/
			if(null != hdbqs && hdbqs.length > 0){
				flag = new HdbljgDao().BatchInsertHdbqx(sqid, hdbqs);
			}
			/*����������ǩ*/
			if(null != nlbqs && nlbqs.length > 0){
				flag = new HdbljgDao().BatchInsertNlbqx(sqid, nlbqs);
			}
		}
		if(model.getType().equals("submit")){
			if (flag) {
				flag = shlc.runSubmit(sqid, splc, model.getXh(), "hdgl_hdbl_hdblsh.do", "hdgl_hdbl_hdblsq.do");
			}
		}
		return flag;
	}
	
	/**
	 * @description	���޸ı���
	 * @author 		�� ������1282��
	 * @date 		��2018-1-18 ����11:16:26
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception 
	 */
	@TransactionControl
	public boolean saveSqUpdate(HdblsqshForm model, User user) throws Exception{
		boolean result = false;
		HdbljgDao jgDao = new HdbljgDao();
		String sqid = model.getSqid();
		/*ɾ�����ǩ������*/
		result = jgDao.deleteHdbq(new String[]{sqid});
			/*ɾ��������ǩ������*/
		result = jgDao.deleteNlbq(new String[]{sqid});

		if(result){
			String[] hdbqs = model.getHdbqs();/*��ȡ���ǩ*/
			String[] nlbqs = model.getNlbqs();/*��ȡ������ǩ*/
			if(hdbqs != null && hdbqs.length>0){
				/*������ǩ*/
				result = jgDao.BatchInsertHdbqx(sqid, hdbqs);
			}
			if(nlbqs != null && nlbqs.length>0){
				/*����������ǩ*/
				result = jgDao.BatchInsertNlbqx(sqid, nlbqs);
			}
		}
		if(result){
			// ����ύ���������״̬
			if ("updatesubmit".equalsIgnoreCase(model.getType())) {
				model.setShzt(Constants.YW_SHZ);// �����
				result = dao.runUpdate(model);
				ShlcInterface shlc = new CommShlcImpl();
				if (result) {
					result = shlc.runSubmit(model.getSqid(), model.getSplc(), model.getXh(), "hdgl_hdbl_hdblsh.do", "hdgl_hdbl_hdblsq.do");
				}
			} else {
				result = runUpdate(model);
			}
		}
		return result;
	}

	/**
	 * @description	�� ����
	 * @author 		�� ������1282��
	 * @date 		��2018-1-18 ����11:42:56
	 * @param ywid
	 * @param lcid
	 * @return
	 * @throws Exception
	 */
	public boolean cancelRecord(String ywid, String lcid) throws Exception {
		return shlc.firstStepCancle(ywid, lcid);
	}
	
	/**
	 * @description	�� �ύ
	 * @author 		�� ������1282��
	 * @date 		��2018-1-18 ����11:54:28
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean submitHdblsq(HdblsqshForm model) throws Exception {
		boolean result = false;
		model.setShzt(Constants.YW_SHZ);// �����
		result = runUpdate(model);
		ShlcInterface shlc = new CommShlcImpl();
		if (result) {
			result = shlc.runSubmit(model.getSqid(), model.getSplc(), model.getXh(), "hdgl_hdbl_hdblsh.do", "hdgl_hdbl_hdblsq.do");
		}
		return result;
	}

	public int runDelete(String[] values) throws Exception {
		int num = dao.runDelete(values);
		if(num>0){
			HdbljgDao jgDao = new HdbljgDao();
			jgDao.deleteHdbq(values);
			jgDao.deleteNlbq(values);
		}
		return num;
	}

	/**
	 * @description	�� ��ȡ���Ϣ
	 * @author 		�� ������1282��
	 * @date 		��2018-1-18 ����02:18:44
	 * @param model
	 * @return
	 */
	public HashMap<String,String> gethdblInfo(HdblsqshForm model){
		return dao.gethdblInfo(model);
	}
	
	/**
	 * @description	�� ��ȡ���ǩlist
	 * @author 		�� ������1282��
	 * @date 		��2018-1-19 ����05:26:30
	 * @return
	 */
	public List<HashMap<String,String>> getActivityLabelList(){
		List<HashMap<String, String>> arrayList = new ArrayList<HashMap<String, String>>();
		HashMap<String,String> bookMap = new HashMap<String, String>();
		bookMap.put("dm", "100����");
		bookMap.put("mc", "100����");
		HashMap<String,String> teacherMap = new HashMap<String, String>();
		teacherMap.put("dm", "100����ʦ");
		teacherMap.put("mc", "100����ʦ");
		HashMap<String,String> activityMap = new HashMap<String, String>();
		activityMap.put("dm", "100���");
		activityMap.put("mc", "100���");
		HashMap<String,String> lectureMap = new HashMap<String, String>();
		lectureMap.put("dm", "100������");
		lectureMap.put("mc", "100������");
		arrayList.add(bookMap);
		arrayList.add(teacherMap);
		arrayList.add(activityMap);
		arrayList.add(lectureMap);
		return arrayList;
	}

	/**
	 * @����:������ְ��
	 * @���ߣ�lgx [���ţ�1553]
	 * @���ڣ�2019/3/21 16:13
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param: []
	 * @return: java.util.List<java.util.HashMap<java.lang.String,java.lang.String>>
	 */
    public List<HashMap<String,String>> getZjrzcList() {
		return dao.getZjrzcList();
    }
}
