/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-10-29 ����01:56:23 
 */  
package com.zfsoft.xgxt.wjcf.cfsssq;

import java.util.HashMap;
import xgxt.utils.String.StringUtils;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.rcsw.qjgl.BaseDbcz;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: Υ�͹���ģ��
 * @�๦������: (������������) 
 * @���ߣ� ������[����:913]
 * @ʱ�䣺 2013-10-29 ����01:46:50 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class CfsssqService extends SuperServiceImpl<CfsssqForm, CfsssqDao> {
	private CfsssqDao dao=new CfsssqDao();
	private ShlcInterface shlc = new CommShlcImpl();
	public static final String SUBMIT = "submit";
	public static final String SAVE = "save";
	
	public CfsssqService(){
		this.setDao(dao);
	}

	/** 
	 * @����:(��ȡ���߽����������)
	 * @���ߣ�������[���ţ�913]
	 * @���ڣ�2013-10-29 ����04:54:39
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws 
	 */
	public HashMap<String, String> getSsjcsplc() {
		//��ȡ����
		HashMap<String, String> ssjcsplc=dao.getSsjcsplc();
		return ssjcsplc;
	}
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
	public String save(CfsssqForm model) throws Exception {
		
		String ssid = "";
		ssid = UniqID.getInstance().getUniqIDHash();
		model.setSsid(ssid);
		//��ȡ���߽������
		HashMap<String, String> ssjcsplc=dao.getSsjcsplc();
		String splcid=ssjcsplc.get("ssspl");
		//��������Ϊ�գ���ʾ��ʾ������������
		if (null == splcid||"".equalsIgnoreCase(splcid)) {
			return MessageKey.SYS_SELECT_SHLC_FAIL;
		}
		model.setSplcid(splcid);
		//���ó�ʼ���״ֵ̬
		if(splcid!=null&&!"".equalsIgnoreCase(splcid)){
			//model.setSsjg(Constants.WSH);  
			if(SAVE.equalsIgnoreCase(model.getType())){
				model.setSsjg(Constants.WSH);  
			}else{
				model.setSsjg(Constants.SHZ); 
			}
			
		}
		boolean result = false;
		if(!StringUtils.isNull(model.getSqly())){
			model.setSqly(model.getSqly().replaceAll("\\n", ""));
		}
	    result = super.runInsert(model);
		
		if(result && SUBMIT.equalsIgnoreCase(model.getType())){
			result = shlc.runSubmit(ssid, splcid,model.getXh(),"wjcf_cfsssh.do?method=cxCfssshList","wjcf_cfsssq.do?method=cxCfsssqList");
		}
		return String.valueOf(result);
	}

	
	@Override
	public int runDelete(String[] values) throws Exception {
		String[] ids=getCancelIds(values);
		if(ids.length==0)
			return 0;
		int result=super.runDelete(ids);
		for (String id : ids) {
			if(shlc.deleteShjl(id)){
				BaseDbcz dbcz=new BaseDbcz();
				dbcz.setGnmkMc("�����������");
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
	 * @����:TODO(ȡ��ʱ���ж��Ƿ��ǵ�һ�����)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2013-12-7 ����02:59:09
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
	 * @����:TODO(ɾ����¼)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2013-12-7 ����02:59:32
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ids
	 * @return
	 * @throws Exception
	 * int �������� 
	 * @throws
	 */
	public int deleteRecord(String[] ids) throws Exception{
		return dao.runDelete(ids);
	}
	
	/**
	 * 
	 * @����:TODO(���ļ�¼)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2013-12-7 ����02:59:43
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean updateRecord(CfsssqForm model) throws Exception{
		return  dao.updateBackRecord(model);
	}
	
	/**
	 * 
	 * @����:TODO(�ύ����)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2013-12-7 ����02:58:37
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean submitCfsssq(CfsssqForm model) throws Exception{
		boolean resultCfss = dao.updateBackRecord(model);
		boolean result = false;
		if(resultCfss ){
			//�����������
			result = shlc.runSubmit(model.getCfid(), model.getSplcid(),model.getXh(),"wjcf_cfsssh.do?method=cxCfssshList","wjcf_cfsssq.do?method=cxCfsssqList");
		}
		return result;
	}
	
	/**
	 * 
	 * @����:TODO(ȡ������)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2013-12-7 ����02:58:50
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * String �������� 
	 * @throws
	 */
	public String cfsssqUpdateSave(CfsssqForm model) throws Exception {
		String ssid = "";
		ssid = model.getSsid();

		//��ȡ���߽������
		String splcid = "";
		CfsssqForm myModel = getModel(ssid);
		// ���˻�
		if(Constants.YW_YTH.equals(myModel.getSsjg())){
			splcid = myModel.getSplcid();
		}else{
			HashMap<String, String> ssjcsplc=dao.getSsjcsplc();
			splcid = ssjcsplc.get("ssspl");
		}		
		
		//��������Ϊ�գ���ʾ��ʾ������������
		if (null == splcid||"".equalsIgnoreCase(splcid)) {
			return MessageKey.SYS_SELECT_SHLC_FAIL;
		}
		model.setSplcid(splcid);
		//���ó�ʼ���״ֵ̬
		if(splcid!=null&&!"".equalsIgnoreCase(splcid)){
				model.setSsjg(Constants.SHZ); 
		}
		boolean result = false;
		result = super.runUpdate(model);
		if(result && SUBMIT.equalsIgnoreCase(model.getType())){
			result = shlc.runSubmit(ssid, splcid,model.getXh(),"wjcf_cfsssh.do?method=cxCfssshList","wjcf_cfsssq.do?method=cxCfsssqList");
		}
		return String.valueOf(result);
	}


	
}
