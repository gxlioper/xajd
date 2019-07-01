package com.zfsoft.xgxt.xsxx.xjyd.xjydsq;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.xsxx.xjyd.XjydForm;
import com.zfsoft.xgxt.xsxx.xjyd.XjydService;

public class XjydsqService extends SuperServiceImpl<XjydsqForm, XjydsqDao> {

	private XjydsqDao dao = new XjydsqDao();
	private ShlcInterface shlc = new CommShlcImpl();
	public XjydsqService(){
		super.setDao(dao);
	}

	
	/**
	 * @����:�����ύ/ȡ���ύѧ���춯����
	 * @���ߣ�Qilm[���ţ�964]
	 * @���ڣ�2013-11-30 ����11:56:05
	 * @param values
	 * @param tjzt
	 * @return
	 * boolean �������� 
	 * @throws Exception  
	 */
	public boolean pltjXjydsq(String values, String tjzt) throws Exception {
		
		boolean bolFlg = true;
		
		// ȡ���ύ
		if(Constants.YW_WTJ.equals(tjzt)){
			bolFlg = dao.pltjXjydsq(values, tjzt);
			// �ύ
		}else{
			
			// �˻��ύ��ȡ���µ��������ID
			XjydsqForm model = dao.getModel(values);
			String shlcidnew = "";
			
			// �����˻صļ�¼������ȡ�������ID
			if(!Constants.YW_YTH.equals(model.getShzt())){
				
				// ȡ���µ��������ID
				XjydForm xjydlb = new XjydForm();
				XjydService xjydlbService = new XjydService();
				xjydlb.setXjlbdm(model.getYdlbdm());
				xjydlb = xjydlbService.getModelShpz(xjydlb);
				shlcidnew = xjydlb.getShlcid();
				//���˻صļ�¼ȡ�ϵ��������ID
			}else{
				shlcidnew = model.getSplcid();
			}
			
			bolFlg = dao.pltjXjydsq(values, tjzt, shlcidnew);
		}
		
		return bolFlg;
	}

	/**
	 * 
	 * @����: �Ƿ�������жϣ���ѧ����δ������ѧ���춯�����������룩
	 * @���ߣ�Qilm[���ţ�964]
	 * @���ڣ�2013-12-12 ����02:56:41
	 * @param myForm true:�м�¼
	 * @return
	 * boolean �������� true:�м�¼
	 * @throws
	 */
	public boolean sfKsq(XjydsqForm myForm){
		
		return dao.sfKsq(myForm.getXh());
	}
	
	/**
	 * ���������
	 */
	@Override
	public boolean runInsert(XjydsqForm myForm)throws Exception {
		boolean bolFlg = false;

		String sqid = UniqID.getInstance().getUniqIDHash();
		myForm.setXjydsqid(sqid);
		
		bolFlg = dao.runInsert(myForm);
		
		// ���ύ��Ӧ׷�������
		if(myForm.getShzt().equals(Constants.YW_SHZ) && bolFlg){
			bolFlg = shlc.runSubmit(sqid, myForm.getSplcid(),myForm.getXh(),"xjyd_xjydsh.do","xjyd_xjydsq.do");
		}
		
		return bolFlg;
	}
	
	/**
	 * ���������
	 */
	@Override
	public boolean runUpdate(XjydsqForm myForm)throws Exception {
		boolean bolFlg = false;
		
		// ����ID
		String sqid = myForm.getXjydsqid();		
		bolFlg = dao.runUpdate(myForm);
		
		// ���ύ��Ӧ׷�������
		if(myForm.getShzt().equals(Constants.YW_SHZ) && bolFlg){
			if(bolFlg){
				// ɾ��ԭ�����
				shlc.deleteShjl(sqid);
				// ׷�������
				bolFlg = shlc.runSubmit(sqid, myForm.getSplcid(),myForm.getXh(),"xjyd_xjydsh.do","xjyd_xjydsq.do");
			}
		}
		return bolFlg;
	}
	
	/**
	 * ɾ�������
	 */
	@Override
	public int runDelete(String[] values)throws Exception {
		
		// ɾ����¼
		int count = dao.runDelete(values);
		
		if (count >0){
			// ɾ�������
			for(String sqid: values){
				// ɾ��ԭ�����
				shlc.deleteShjl(sqid);
			}
		}
		return count;
	}

	/**
	 * 
	 * @����:��ȡmap
	 * @���ߣ���С��[����:1036]
	 * @���ڣ�2013-12-31 ����02:08:03
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param keyValue
	 * @return
	 * @throws Exception
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String , String> getModelMap(String keyValue)throws Exception{
		return dao.getModelMap(keyValue);
	}
	
	/**
	 * @����:�����ύ/ȡ���ύѧ���춯����
	 * @���ߣ�Qilm[���ţ�964]
	 * @���ڣ�2013-12-2 ����02:29:46
	 * @param values
	 * @param shzt x
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean submitRecord(String values, String shzt) throws Exception {

		boolean result =false;
		// ȡ���ύ
		if(Constants.YW_WTJ.equals(shzt)){
			// �����˵ĳ���
			for (String pkValue: values.split(",")){
				
				XjydsqForm model = dao.getModel(pkValue);
				result = shlc.firstStepCancle(pkValue, model.getSplcid());
			}
		}else if(Constants.YW_SHZ.equals(shzt)){
			// �ύ
			String[] v = values.split(",");
			for (String pkValue: v){
				XjydsqForm model = dao.getModel(pkValue);
				
				// ���˻صļ�¼ȡ�ϵ��������ID
				if(!Constants.YW_YTH.equals(model.getShzt())){
					XjydForm xjydlb = new XjydForm();
					XjydService xjydlbService = new XjydService();
					xjydlb.setXjlbdm(model.getYdlbdm());
					xjydlb = xjydlbService.getModelShpz(xjydlb);
					
					// ȡ���µ��������ID
					model.setSplcid(xjydlb.getShlcid());
				}
				result = shlc.runSubmit(pkValue, model.getSplcid(),model.getXh(),"xjyd_xjydsh.do","xjyd_xjydsq.do");
			}
		}
		return result;
	}


	/** 
	 * @����:��֤�Ƿ���ύ
	 * @���ߣ�qlm
	 * @���ڣ�2014-5-26 ����11:18:56
	 * @param model
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean checkSfktj(XjydsqForm model) {

		String num = dao.checkSfktj(model.getXjlbdm());
		return Integer.valueOf(num) > 0;
	}
	
	/**
	 * 
	 * @����:��ȡ�춯ԭ���б�
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-4-12 ����11:07:48
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getYdyyList() {
		return dao.getYdyyList();
	}
	
	/**
	 * 
	 * @����:��ȡ��Դȥ��ѧУ�б�
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-4-12 ����11:07:48
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getLyqxxxList() {	
		return dao.getLyqxxxList();
	}
	
	/**
	 * 
	 * @����: ��ǰ״̬�б�
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-4-22 ����02:37:58
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getDqztList() {
		return dao.getDqztList();		
	}
	
}
