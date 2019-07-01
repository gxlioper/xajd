package com.zfsoft.xgxt.xlzx.xlzxnew.xlybgl.ybsb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.action.Base;
import xgxt.form.User;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.xlzx.xlzxnew.zqsz.jcsz.XlzxSbService;

public class YbsbService extends SuperServiceImpl<YbsbForm, YbsbDao> {
	
	private ShlcInterface shlc = new CommShlcImpl();
	public boolean saveYbsb(YbsbForm ybsb) throws Exception{
		boolean rs = true;
		String[] xhArray = ybsb.getXhArray();
		String[] ybwtmsArray = ybsb.getYbwtmsArray();
		String[] ybgycsArray = ybsb.getYbgycsArray();
		String[] ybgyhjgArray = ybsb.getYbgyhjgArray();
		String[] wtfsrqArray = ybsb.getWtfsrqArray();
		ybsb.setTxrq(GetTime.getTimeByFormat("yyyy-mm-dd"));
		ybsb.setSplcid(new XlzxSbService().getModel("yb").getSplc());
		if("submit".equals(ybsb.getSaveFlag())){
			ybsb.setShzt(Constants.YW_SHZ);
		}else{
			ybsb.setShzt(Constants.YW_WTJ);
		}
		if(StringUtils.isNotNull(ybsb.getSbid())){
			rs = dao.delWtb(ybsb.getSbid());
			if(!rs){
				throw new SystemException(MessageKey.SYS_SAVE_FAIL);
			}
			rs = dao.runUpdate(ybsb);
			if(!rs){
				throw new SystemException(MessageKey.SYS_SAVE_FAIL);
			}
		}else{
			if(!dao.checkIsNotExist(ybsb.getXydm(),ybsb.getXn(),ybsb.getYf())){
				throw new SystemException(MessageKey.XG_XLZX_SB_REPEAT);
			}
			ybsb.setSbid(UniqID.getInstance().getUniqIDHash().toUpperCase());
			rs = dao.runInsert(ybsb);
			if(!rs){
				throw new SystemException(MessageKey.SYS_SAVE_FAIL);
			}
		}
		List<String[]> paraList = new ArrayList<String[]>();
		if(xhArray != null && xhArray.length > 0){
			for (int i = 0; i < ybwtmsArray.length; i++) {
				if("10704".equals(Base.xxdm)){
					paraList.add(new String[]{"yb",ybsb.getSbid(),xhArray[i],ybwtmsArray[i],null,ybgyhjgArray[i],wtfsrqArray[i]});
				}else{
					paraList.add(new String[]{"yb",ybsb.getSbid(),xhArray[i],ybwtmsArray[i],ybgycsArray[i],ybgyhjgArray[i],null});
				}
				
			}
			rs = dao.saveDataIntoWtb(paraList);
			if(!rs){
				throw new SystemException(MessageKey.SYS_SAVE_FAIL);
			}
		}
		if("submit".equals(ybsb.getSaveFlag())){
			rs = shlc.runSubmit(ybsb.getSbid(),ybsb.getSplcid(),ybsb.getTxr(), "xg_xlzxnew_ybsh.do", "xg_xlzxnew_ybsb.do");
			if(!rs){
				throw new SystemException(MessageKey.SYS_SAVE_FAIL);
			}
		}
		return rs;
	}
	
	/**
	 * 
	 * @����: ��ȡ�ܱ�������Ա��Ϣ
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-8-29 ����04:26:17
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param zbsqid
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getYbWtryInfo(String ybsqid){
		return dao.getYbWtryInfo(ybsqid);
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @����: ɾ���±����
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-9-2 ����03:07:07
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean delSbjg(String[] sbsqids) throws Exception{
		return dao.delSbjg(sbsqids);
	}
	
	/**
	 * 
	 * @����: �ύ
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-8-30 ����10:16:17
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean submitBusi(YbsbForm model, User user)  throws Exception {
		String splc = new XlzxSbService().getModel("yb").getSplc();
        if(Constants.YW_YTH.equals(model.getShzt())){
        	splc = model.getSplcid();
		}
        model.setShzt(Constants.YW_SHZ);
		model.setSplcid(splc);
		boolean flag = dao.runUpdate(model);
		if(flag){
			 shlc.runSubmit(model.getSbid(), splc, model.getTxr(), "xg_xlzxnew_ybsh.do", "xg_xlzxnew_ybsb.do");
		}
		return flag;
	}
	
	//����
	public boolean cancelRecord(String ywid, String lcid) throws Exception {
		return shlc.firstStepCancle(ywid, lcid);
	}

	public boolean update(YbsbForm myForm) throws Exception {
		boolean rs = true;
		myForm.setSplcid(new XlzxSbService().getModel("yb").getSplc());
	
		rs =  dao.delWtb(myForm.getSbid());
		if(!rs){
			throw new SystemException(MessageKey.SYS_SAVE_FAIL);
		}
		if("submit".equals(myForm.getSaveFlag())){
			myForm.setShzt(Constants.YW_SHZ);
		}else{
			myForm.setShzt(Constants.YW_WTJ);
		}
		rs = dao.update(myForm);
		if(!rs){
			throw new SystemException(MessageKey.SYS_SAVE_FAIL);
		}
		if("submit".equals( myForm.getSaveFlag())){
			myForm.setShzt(Constants.YW_SHZ);
			rs = shlc.runSubmit( myForm.getSbid(), myForm.getSplcid(), myForm.getTxr(), "xg_xlzxnew_ybsh.do", "xg_xlzxnew_ybsb.do");
			if(!rs){
				throw new SystemException(MessageKey.SYS_SAVE_FAIL);
			}
		}
		return rs;
	}
}
