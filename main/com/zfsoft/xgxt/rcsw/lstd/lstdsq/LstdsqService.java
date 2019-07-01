/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-11-25 ����09:41:06 
 */  
package com.zfsoft.xgxt.rcsw.lstd.lstdsq;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �ճ�����
 * @�๦������: ��ɫͨ��
 * @���ߣ� cq [����:785]
 * @ʱ�䣺 2014-11-25 ����09:41:06 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class LstdsqService extends SuperServiceImpl<LstdsqForm, LstdsqDao> {
	
	
	private LstdsqDao dao = new LstdsqDao();
	private ShlcInterface shlc = new CommShlcImpl();
	public static final String SUBMIT = "submit";
	public static final String SAVE = "save";
	public static String _BCZSCID="-1";

	public LstdsqService() {
		super.setDao(dao);
	}

	/**
	 * 
	 * @����:����ά������
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-11-26 ����01:37:19
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getLxwhList() {
		return dao.getLxwhList();
	}
	

	/**
	 * 
	 * @����:����
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-11-26 ����01:38:48
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean saveLstdsq(LstdsqForm model) throws Exception {
		
		if(model.getType().equals(SUBMIT)){
			model.setShzt(Constants.YW_SHZ);//�����
		}else{
			model.setShzt(Constants.YW_WTJ);//δ�ύ
		}
		// ��ȡ��������
		String splc = dao.getShlcID();
		model.setSplc(splc);
		boolean insertResult = super.runInsert(model);
		if( SAVE.equalsIgnoreCase(model.getType())){
			return insertResult;
		}
		boolean result = false;
		if (insertResult && SUBMIT.equalsIgnoreCase(model.getType())) {
			//�����������
			result = shlc.runSubmit(model.getSqid(), splc,model.getXh(),"rcsw_lstd_sh.do","rcsw_lstd_sq.do");
		}
		return result;
	}
	
	/**
	 * 
	 * @����:�޸�
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-11-26 ����01:41:47
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean updateLstdsq(LstdsqForm model) throws Exception {
		
		if(model.getType().equals(SUBMIT)&& !Constants.YW_YTH.equalsIgnoreCase(model.getShzt())){
			// ��ȡ��������
			String splc = dao.getShlcID();
			model.setSplc(splc);
		}
		
		if(model.getType().equals(SUBMIT)){
			model.setShzt(Constants.YW_SHZ);//�����
		}
		
		boolean insertResult = super.runUpdate(model);
		boolean result = false;
		if (insertResult) {
			shlc.deleteShjl(model.getSqid());
			if("update".equalsIgnoreCase(model.getType())){
				result = true;
			}else{
				result = shlc.runSubmit(model.getSqid(), model.getSplc(),model.getXh(),"rcsw_lstd_sh.do","rcsw_lstd_sq.do");
			}
		}
		return result;
	}
	
	/**
	 * 
	 * @����:ɾ��
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-11-26 ����01:44:21
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ids
	 * @return
	 * @throws Exception
	 * String[] �������� 
	 * @throws
	 */
	public String[] deleteLstdsq(String[] ids) throws Exception{
		List<String> delId=new ArrayList<String>();//��ɾ����id����
		StringBuffer noDel = new StringBuffer();
		boolean isHaveNoId = false;
		if(null==ids||ids.length<=0){
			return null;
		}
		for(String str:ids){
			if(dao.isCanDel(str)){
				delId.add(str);//��¼ɾ��id
			}else{
				HashMap<String, String> hm=dao.getLstdsq(str);
				noDel.append(hm.get("xh"));
				noDel.append("&nbsp;");
				noDel.append(hm.get("xm"));
				noDel.append(",</br>");
				isHaveNoId=true;
			}
		}
		int i=delId.size()>0?lstdDelete(delId.toArray(new String[]{})):0;
		String str=noDel.toString();
		//ȥ�������ය��
		str=isHaveNoId?str:_BCZSCID;
		return new String[]{String.valueOf(i),str};
	}
	
	/**
	 * 
	 * @����:ɾ��
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-11-26 ����01:49:41
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ids
	 * @return
	 * @throws Exception
	 * int �������� 
	 * @throws
	 */
	private int lstdDelete(String[] ids) throws Exception {
		for(String id:ids){
			shlc.deleteShjl(id);
		}
		return runDelete(ids);
	}
	
	
	/**
	 * 
	 * @����:�ύ
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-11-26 ����01:50:13
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean submitSq(LstdsqForm model) throws Exception {
		boolean resultSq = dao.updateSq(model);
		boolean result = false;
		if(resultSq){
			//�����������
			result = shlc.runSubmit(model.getSqid(), model.getSplc(),model.getXh(),"rcsw_lstd_sh.do","rcsw_lstd_sq.do");
		}
		return result;
	}
	
	/**
	 * 
	 * @����:��������
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-11-26 ����01:52:17
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean updateSq(LstdsqForm model) throws Exception {
			boolean resultBbsq = dao.updateSq(model);
			return resultBbsq;
	}
	
	/**
	 * 
	 * @����:�����鿴
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-11-26 ����01:53:07
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String> getLstdsqInfo(LstdsqForm model) {
		if (StringUtil.isNull(model.getSqid())) {
			logger.error("����ID����Ϊ�գ�");
			throw new NullPointerException();
		}
		return dao.getLstdsqInfo(model);
	}
	

	/**
	 * 
	 * @����:ֻ�и��ύ���ҵ�һ��δ��˵�ǰ���£������˿��Գ���
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-11-26 ����01:54:26
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ywid
	 * @param lcid
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean cancelRecord(String ywid,String lcid) throws Exception{
		return shlc.firstStepCancle(ywid,lcid);
	}
	
	/**
	 * 
	 * @����:����˵ļ�¼����
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-11-26 ����01:55:37
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean allowUpdateSetting() {

		try {
			return dao.getDshCount() == 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * 
	 * @����:��ѧ�š�ѧ���ѧ���ж������Ƿ����
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-11-26 ����01:55:49
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean isExistByLstdsq(LstdsqForm model)
	throws Exception {
		boolean flag = false;
		String num = dao.checkExistForSave(model);
		if (!"0".equalsIgnoreCase(num)) {
			flag = true;
		}
		return flag;
	}

}
