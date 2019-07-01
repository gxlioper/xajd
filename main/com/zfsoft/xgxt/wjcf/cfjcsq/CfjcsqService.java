/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-10-30 ����04:32:16 
 */  
package com.zfsoft.xgxt.wjcf.cfjcsq;

import java.util.HashMap;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.rcsw.qjgl.BaseDbcz;
import com.zfsoft.xgxt.wjcf.cfsssq.CfsssqDao;
import com.zfsoft.xgxt.wjcf.cfsssq.CfsssqForm;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: Υ�͹���ģ��
 * @�๦������: (������������) 
 * @���ߣ� ������[����:913]
 * @ʱ�䣺 2013-10-29 ����01:46:50 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class CfjcsqService extends SuperServiceImpl<CfjcsqForm, CfjcsqDao> {
	private CfjcsqDao dao=new CfjcsqDao();
	public static final String SUBMIT = "submit";
	public static final String SAVE = "save";
	public static final String BACK = "back";
	
	public CfjcsqService(){
		this.setDao(dao);
	}
	private ShlcInterface shlc = new CommShlcImpl();

	/**
	 * @throws Exception  
	 * @����:(������������)
	 * @���ߣ�������[���ţ�913]
	 * @���ڣ�2013-10-29 ����04:51:01
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * String �������� 
	 * @throws 
	 */
	public String save(CfjcsqForm model) throws Exception {
		
		
		String jcid = UniqID.getInstance().getUniqIDHash();
		model.setJcid(jcid);
		//��ȡ���߽������
		CfsssqDao sssqDao=new CfsssqDao();
		HashMap<String, String> ssjcsplc=sssqDao.getSsjcsplc();
		String splcid=ssjcsplc.get("jcspl");
		//��������Ϊ�գ���ʾ��ʾ������������
		if (null == splcid||"".equalsIgnoreCase(splcid)) {
			return MessageKey.SYS_SELECT_SHLC_FAIL;
		}
		model.setSplcid(splcid);
		//���ó�ʼ���״ֵ̬
		if(splcid!=null&&!"".equalsIgnoreCase(splcid)){
			model.setSqjg(Constants.WSH);
			if(SAVE.equalsIgnoreCase(model.getType())){
				model.setSqjg(Constants.WSH);  
			}else{
				model.setSqjg(Constants.SHZ); 
			}
		}
		boolean result=super.runInsert(model);
		if(result && SUBMIT.equalsIgnoreCase(model.getType())){
			result = shlc.runSubmit(jcid, splcid,model.getXh(),"wjcf_cfjcsh.do?method=cxCfjcshList","wjcf_cfjcsq.do?method=cxCfjcsqList");
			if (result) {
				//���ô�����Ϣ
				/*BaseDbcz dbcz=new BaseDbcz();
				dbcz.setShPath("wjcf_cfjcsh.do?method=cxCfjcshList");
				dbcz.setSqPath("wjcf_cfjcsq.do?method=cxCfjcsqList");
				dbcz.setGnmkMc("���ֽ�����");
				dbcz.setXmmc("������");
				dbcz.sqPush(jcid, model.getXh(), splcid);*/
			}
		}
		return String.valueOf(result);
	}
	
	@Override
	public int runDelete(String[] values) throws Exception {
		String[] ids=getCancelIds(values);
		if(ids.length==0)
			return 0;
		int result=dao.runDelete(ids);
		for (String id : ids) {
			if(shlc.deleteShjl(id)){
				BaseDbcz dbcz=new BaseDbcz();
				dbcz.setGnmkMc("���ֽ�����");
				dbcz.remove(new String[]{id});
			}
		}
		return result;
	}

	/**
	 * @throws Exception  
	 * @����:(��ȡ����ɾ����id)
	 * @���ߣ�������[���ţ�913]
	 * @���ڣ�2013-10-29 ����07:01:49
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param values
	 * @return
	 * String[] �������� 
	 * @throws 
	 */
	private String[] getCancelIds(String[] values) throws Exception {
		
		return dao.getCancelIds(values);
	}
	
	/**
	 * 
	 * @����:TODO(ȡ����¼��ʱ���жϵ�һ���Ƿ��Ѿ����)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2013-12-7 ����03:05:02
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
	 * @����:TODO(���¼�¼)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2013-12-7 ����03:05:22
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean updateRecord(CfjcsqForm model) throws Exception{
		return  dao.updateBackRecord(model);
	}
	
	/**
	 * 
	 * @����:TODO(�ύ����)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2013-12-7 ����03:05:37
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean submitCfjcsq(CfjcsqForm model) throws Exception{
		boolean resultCfsq = dao.updateBackRecord(model);
		boolean result = false;
		if(resultCfsq ){
			//�����������
			result = shlc.runSubmit(model.getJcid(), model.getSplcid(),model.getXh(),"wjcf_cfjcsh.do?method=cxCfjcshList","wjcf_cfjcsq.do?method=cxCfjcsqList");
			
			if (result) {
				//���ô�����Ϣ
				/*BaseDbcz dbcz=new BaseDbcz();
				dbcz.setShPath("wjcf_cfjcsh.do?method=cxCfjcshList");
				dbcz.setSqPath("wjcf_cfjcsq.do?method=cxCfjcsqList");
				dbcz.setGnmkMc("���ֽ�����");
				dbcz.setXmmc("������");
				dbcz.sqPush(model.getJcid(),model.getXh(), model.getSplcid());*/
			}
		}
		//return result;
		
			return result;
		
	}
	
	/**
	 * 
	 * @����:TODO(ȡ������)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2013-12-7 ����03:05:55
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * String �������� 
	 * @throws
	 */
	public String cfjcsqUpdateSave(CfjcsqForm model) throws Exception {
		String jcid = model.getJcid();
		//��ȡ���߽������
		CfsssqDao sssqDao=new CfsssqDao();
		
		//��ȡ���߽������
		String splcid = "";
		CfjcsqForm myModel = getModel(jcid);
		// ���˻�
		if(Constants.YW_YTH.equals(myModel.getSqjg())){
			splcid = myModel.getSplcid();
		}else{
			HashMap<String, String> ssjcsplc=sssqDao.getSsjcsplc();
			splcid = ssjcsplc.get("jcspl");
		}
		
		//��������Ϊ�գ���ʾ��ʾ������������
		if (null == splcid||"".equalsIgnoreCase(splcid)) {
			return MessageKey.SYS_SELECT_SHLC_FAIL;
		}
		
		model.setSplcid(splcid);
		//���ó�ʼ���״ֵ̬
		if(splcid!=null&&!"".equalsIgnoreCase(splcid)){
				model.setSqjg(Constants.SHZ); 
		}
		boolean result=runUpdate(model);
		if(result){
			result = shlc.runSubmit(jcid, splcid,model.getXh(),"wjcf_cfjcsh.do?method=cxCfjcshList","wjcf_cfjcsq.do?method=cxCfjcsqList");
			if (result) {
				//���ô�����Ϣ
				/*BaseDbcz dbcz=new BaseDbcz();
				dbcz.setShPath("wjcf_cfjcsh.do?method=cxCfjcshList");
				dbcz.setSqPath("wjcf_cfjcsq.do?method=cxCfjcsqList");
				dbcz.setGnmkMc("���ֽ�����");
				dbcz.setXmmc("������");
				dbcz.sqPush(jcid, model.getXh(), splcid);*/
			}
		}
		return String.valueOf(result);
	}
	
	
	
	/**
	 * 
	 * @����:TODO(��ѯ������ֵ�ѧ����Ϣ)
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2014-8-14 ����08:58:43
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param cfid
	 * @return
	 * @throws Exception
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String> jccfwjxx(String cfid) throws Exception{
		
		CfjcsqDao dao = new CfjcsqDao();
		
		return dao.jccfwjxx(cfid);
		
	}
	

}
