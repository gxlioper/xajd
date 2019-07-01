/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-3-24 ����08:52:36 
 */  
package com.zfsoft.xgxt.rcsw.xqybgl.bjxqybgl.bjxqyb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import xgxt.form.User;
import xgxt.utils.GetTime;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;


/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ������ҽҩ_ѧ���±�����ģ��
 * @�๦������: TODO(������ҽҩ_ѧ���±�_�༶�±�����) 
 * @���ߣ� ������[����:995]
 * @ʱ�䣺 2016-3-24 ����08:52:36 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */
public class BjxqybsqService extends SuperServiceImpl<BjxqybsqForm, BjxqybsqDao> {
	
	private BjxqybsqDao dao = new BjxqybsqDao();
	public static final String SUBMIT = "submit";
	public static final String SAVE = "save";
	public static String _BCZSCID="-1";
	private ShlcInterface shlc = new CommShlcImpl();
	
	@SuppressWarnings("deprecation")
	public BjxqybsqService(){
		super.setDao(dao);
	}
	
	/**
	 * 
	 * @����:TODO(�༶ѧ���±�����-�༶�±�����-��������)
	 * @���ߣ�������[���ţ�995]
	 * @���ڣ�2016-4-12 ����11:31:43
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean saveBjxqybsq(BjxqybsqForm model,User user) throws Exception {			
		model.setTxsj(GetTime.getTimeByFormat("yyyy-mm-dd hh24:mi:ss"));
		
		if(model.getType().equals(SUBMIT)){
			model.setShzt(Constants.YW_SHZ);//�����
		}else{
			model.setShzt(Constants.YW_WTJ);//δ�ύ
		}		
		String splc = dao.getShlcID();
		model.setSplc(splc);			
		boolean insertResult = super.runInsert(model);
		if(SAVE.equalsIgnoreCase(model.getType())){
			return insertResult;
		}	
		boolean result = false;
		if (insertResult && SUBMIT.equalsIgnoreCase(model.getType())){	
			String sqURL = "rcsw_xqybgl_bjxqybgl_bjxqyb.do";
			String shURL = "rcsw_xqybgl_bjxqybgl_bjxqybsh.do";
			result = shlc.runSubmit(model.getSqid(),splc,model.getBjdm(),shURL,sqURL);
		}		
		return result;
	}
	
	
	/**
	 * 
	 * @����:TODO(�༶ѧ���±�����-�༶�±�����-�жϰ༶ѧ���±������Ƿ��Ѿ�����)
	 * @���ߣ�������[���ţ�995]
	 * @���ڣ�2016-4-8 ����10:53:05
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param type
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean isExistByBjxqybsq(BjxqybsqForm model,String type)
	throws Exception {
		boolean flag = false;
		if("save".equalsIgnoreCase(type)) {
			String num = dao.checkExistForBjxqybsqSave(model);
			if (!"0".equalsIgnoreCase(num)) {
				flag = true;
			}
		}else{
			String num = dao.checkExistForBjxqybsqUpdate(model);
			if (!"0".equalsIgnoreCase(num)) {
				flag = true;
			}
		}	
		return flag;
	}
	
	/**
	 * 
	 * @����:TODO(�༶ѧ���±�����-�༶�±�����-���°༶�±�����)
	 * @���ߣ�������[���ţ�995]
	 * @���ڣ�2016-4-12 ����11:33:23
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean updateBjxqybsq(BjxqybsqForm model) throws Exception {
		
		if(model.getType().equals(SUBMIT)&&!Constants.YW_YTH.equals(model.getShzt())){
			// ��ȡ��������
			model.setSplc(dao.getShlcID());
		}
		
		if(model.getType().equals(SUBMIT)){
			model.setShzt(Constants.YW_SHZ);//�����
		}else{
			//model.setShzt(Constants.YW_WTJ);//δ�ύ
		}
		
		boolean insertResult = super.runUpdate(model);
		boolean result = false;
		if (insertResult && SUBMIT.equalsIgnoreCase(model.getType())) {
			//�����������
			result = shlc.runSubmit(model.getSqid(), model.getSplc(),model.getBjdm(),
					"rcsw_xqybgl_bjxqybgl_bjxqybsh.do","rcsw_xqybgl_bjxqybgl_bjxqybsq.do");
			return result;
		}else{
			return insertResult;
		}
		
	}
	
	/**
	 * 
	 * @����:TODO(�༶ѧ���±�����-�༶�±�����-�ύ����)
	 * @���ߣ�������[���ţ�995]
	 * @���ڣ�2016-4-7 ����03:24:08
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean submitBjxqybsq(BjxqybsqForm model) throws Exception {
		
		if(!Constants.YW_YTH.equalsIgnoreCase(model.getShzt())){
			// ��ȡ��������
			model.setSplc(dao.getShlcID());
		}
		
		model.setShzt(Constants.YW_SHZ);
		boolean resultHcccqjtx = dao.updateBjxqybsq(model);
		boolean result = false;
		if(resultHcccqjtx){
			//�����������
			result = shlc.runSubmit(model.getSqid(),model.getSplc(),model.getBjdm(),"rcsw_xqybgl_bjxqybgl_bjxqybsh.do","rcsw_xqybgl_bjxqybgl_bjxqyb.do");
		}
		return result;
	}
	
	/**
	 * 
	 * @����:TODO(�༶ѧ���±�����-�༶�±�����-������ID��ȡ������ϸ��Ϣ)
	 * @���ߣ�������[���ţ�995]
	 * @���ڣ�2016-4-7 ����03:23:58
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String,String> getBjxqybsqInfo(BjxqybsqForm model){	
		return dao.getBjxqybsqInfo(model);
	}
	
	/**
	 * 
	 * @����:TODO(�༶ѧ���±�����-�༶�±�����-����-ֻ�и��ύ���ҵ�һ��δ��˵�ǰ���£������˿��Գ���)
	 * @���ߣ�������[���ţ�995]
	 * @���ڣ�2016-4-8 ����09:39:28
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
	 * @����:TODO(�༶ѧ���±�����-�༶�±�����-ȡ���±�����)
	 * @���ߣ�������[���ţ�995]
	 * @���ڣ�2016-4-8 ����09:42:46
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean cancelBjxqybsq(BjxqybsqForm model) throws Exception {
		boolean resultBjxqybsq = dao.cancelBjxqybsq(model);
		return resultBjxqybsq;
	}
	
	/**
	 * 
	 * @����:TODO(�༶ѧ���±�����-�༶�±�����-ɾ���±�����)
	 * @���ߣ�������[���ţ�995]
	 * @���ڣ�2016-4-8 ����01:55:40
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ids
	 * @return
	 * @throws Exception
	 * String[] �������� 
	 * @throws
	 */
	public String[] deleteBjxqybsq(String[] ids) throws Exception{
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
				HashMap<String, String> hm=dao.getBjxqybsq(str);
				noDel.append(hm.get("bjdm"));
				noDel.append("&nbsp;");
				noDel.append(hm.get("bjmc"));				
				noDel.append(",</br>");
				isHaveNoId=true;
			}
		}
		int i=delId.size()>0?BjxqybsqDelete(delId.toArray(new String[]{})):0;
		String str=noDel.toString();
		//ȥ�������ය��
		str=isHaveNoId?str:_BCZSCID;
		return new String[]{String.valueOf(i),str};
	}
	
	/**
	 * 
	 * @����:TODO(�༶ѧ���±�����-�༶�±�����-��IDɾ��ϵͳά��-���״̬���ж�Ӧ��ҵ��ID����)
	 * @���ߣ�������[���ţ�995]
	 * @���ڣ�2016-4-12 ����11:38:28
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ids
	 * @return
	 * @throws Exception
	 * int �������� 
	 * @throws
	 */
	private int BjxqybsqDelete(String[] ids) throws Exception {
		for(String id:ids){
			shlc.deleteShjl(id);
		}
		return runDelete(ids);
	}
	
	/**
	 * 
	 * @����:TODO(�༶ѧ���±�����-��ѯ�༶)
	 * @���ߣ�������[���ţ�995]
	 * @���ڣ�2016-4-13 ����03:16:04
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getBjList(BjxqybsqForm t, User user)
		throws Exception {
		return dao.getBjList(t, user);
	}
	

}
