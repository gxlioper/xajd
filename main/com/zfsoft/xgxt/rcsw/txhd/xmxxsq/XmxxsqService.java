package com.zfsoft.xgxt.rcsw.txhd.xmxxsq;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;

public class XmxxsqService extends SuperServiceImpl<XmxxsqForm, XmxxsqDao> {

	private XmxxsqDao dao = new XmxxsqDao();
	private ShlcInterface shlc = new CommShlcImpl();
	public static final String SUBMIT = "submit";
	public static final String SAVE = "save";

	public XmxxsqService() {
		super.setDao(dao);
	}
	
	/**
	 * @����:������Ŀ����
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean saveXmxxsq(XmxxsqForm model) throws Exception {
		if(model.getType().equals(SUBMIT)){
			model.setShzt(Constants.YW_SHZ);//�����
		}else{
			model.setShzt(Constants.YW_WTJ);//δ�ύ
		}
		// ��ȡ��������
		String splc = dao.getShlcID();
		model.setSplc(splc);
		boolean insertResult = super.runInsert(model);
		if(SAVE.equalsIgnoreCase(model.getType())){
			return insertResult;
		}
		boolean result = false;
		if (insertResult && SUBMIT.equalsIgnoreCase(model.getType())) {
			//�����������
			result = shlc.runSubmit(model.getSqid(),splc,model.getSqr(),"rcsw_txhd_xmxxsh.do","rcsw_txhd_xmxxsq.do");
		}
		return result;
	}
	
	/**
	 * @����:�޸���Ŀ����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean updateXmxxsq(XmxxsqForm model) throws Exception {
		if(model.getType().equals(SUBMIT)&& !Constants.YW_YTH.equalsIgnoreCase(model.getShzt())){
			// ��ȡ����������
			model.setSplc(dao.getShlcID());
		}
		
		if(model.getType().equals(SUBMIT)){
			model.setShzt(Constants.YW_SHZ);//�����
		}
		
		boolean insertResult = super.runUpdate(model);
		boolean result = true;
		if (insertResult && SUBMIT.equals(model.getType())) {
			shlc.deleteShjl(model.getSqid());
			result = shlc.runSubmit(model.getSqid(), model.getSplc(),model.getSqr(),"rcsw_txhd_xmxxsh.do","rcsw_txhd_xmxxsq.do");
		}
		return insertResult && result;
	}
	
	/**
	 * @����:�ύ��Ŀ����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean submitXmxxsq(XmxxsqForm model) throws Exception {
		if(!Constants.YW_YTH.equalsIgnoreCase(model.getShzt())){
			// ��ȡ����������
			model.setSplc(dao.getShlcID());
		}
		
		model.setShzt(Constants.YW_SHZ);
		boolean resultXmxxsq = dao.updateXmxxsq(model);
		boolean result = false;
		if(resultXmxxsq){
			//�����������
			result = shlc.runSubmit(model.getSqid(), model.getSplc(),model.getSqr(),"rcsw_txhd_xmxxsh.do","rcsw_txhd_xmxxsq.do");
		}
		return result;
	}
	
	/**
	 * @����:������Ŀ����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean cancelXmxxsq(XmxxsqForm model) throws Exception {
		boolean resultXmxxsq = dao.updateXmxxsq(model);
		return resultXmxxsq;
	}
	
	/**
	 * @����:ֻ�и��ύ���ҵ�һ��δ��˵�ǰ���£������˿��Գ���
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
	 * @����:�Ƿ��Ѿ�����
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean isExist(XmxxsqForm model) throws Exception {
		boolean flag = false;
		String num = dao.checkExistForSave(model);
		if (!"0".equalsIgnoreCase(num)) {
			flag = true;
		}
		return flag;
	}
	
}
