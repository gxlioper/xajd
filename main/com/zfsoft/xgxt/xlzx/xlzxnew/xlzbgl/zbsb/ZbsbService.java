package com.zfsoft.xgxt.xlzx.xlzxnew.xlzbgl.zbsb;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.transaction.TransactionControl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.jskp.cssz.CsszService;
import com.zfsoft.xgxt.jskp.lxsq.LxsqForm;
import com.zfsoft.xgxt.xlzx.xlzxnew.zqsz.jcsz.XlzxSbService;

public class ZbsbService extends SuperServiceImpl<ZbsbForm, ZbsbDao> {
	private ShlcInterface shlc = new CommShlcImpl();
	/**
	 * 
	 * @����: ѧ����Ȩ��֤
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-8-28 ����01:49:01
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String xssqCheck(String xh){
		return dao.xssqCheck(xh);
	}
	
	/**
	 * 
	 * @����: ��ȡѧ����Ϣ
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-8-29 ����03:32:29
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String> getBjxx(String xh){
		return dao.getBjxx(xh);
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
	public List<HashMap<String,String>> getZbWtryInfo(String zbsqid){
		return dao.getZbWtryInfo(zbsqid);
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @����: 
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-8-29 ����05:13:48
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param zbsb
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	@TransactionControl
	public boolean saveZbsb(ZbsbForm zbsb) throws Exception{
		boolean rs = true;
		String[] xhArray = zbsb.getXhArray();
		String[] zbwtmsArray = zbsb.getZbwtmsArray();
		zbsb.setSplcid(new XlzxSbService().getModel("zb").getSplc());
		zbsb.setSbsj(GetTime.getTimeByFormat("yyyy-mm-dd hh24:mi:ss"));
		if("submit".equals(zbsb.getSaveFlag())){
			zbsb.setShzt(Constants.YW_SHZ);
		}else{
			zbsb.setShzt(Constants.YW_WTJ);
		}
		if(StringUtils.isNotNull(zbsb.getSbsqid())){
			rs = dao.delWtb(zbsb.getSbsqid());
			if(!rs){
				throw new SystemException(MessageKey.SYS_SAVE_FAIL);
			}
			rs = dao.runUpdateNotCommit(zbsb);
			if(!rs){
				throw new SystemException(MessageKey.SYS_SAVE_FAIL);
			}
		}else{
			if(!this.checkIsNotExist(zbsb.getBjdm(), zbsb.getSbzbid())){
				throw new SystemException(MessageKey.XG_XLZX_SB_REPEAT);
			}
			zbsb.setSbsqid(UniqID.getInstance().getUniqIDHash().toUpperCase());
			rs = dao.runInsertNotCommit(zbsb);
			if(!rs){
				throw new SystemException(MessageKey.SYS_SAVE_FAIL);
			}
		}
		List<String[]> paraList = new ArrayList<String[]>();
		if(xhArray != null && xhArray.length > 0){
			for (int i = 0; i < zbwtmsArray.length; i++) {
				paraList.add(new String[]{"zb",zbsb.getSbsqid(),xhArray[i],zbwtmsArray[i]});
			}
			rs = dao.saveDataIntoWtb(paraList);
			if(!rs){
				throw new SystemException(MessageKey.SYS_SAVE_FAIL);
			}
		}
		if("submit".equals(zbsb.getSaveFlag())){
			rs = shlc.runSubmit(zbsb.getSbsqid(),zbsb.getSplcid(),zbsb.getXh(), "xg_xlzxnew_zbsh.do", "xg_xlzxnew_zbsb.do");
			if(!rs){
				throw new SystemException(MessageKey.SYS_SAVE_FAIL);
			}
		}
		return rs;
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
	public boolean submitBusi(ZbsbForm model, User user)  throws Exception {
		String splc = new XlzxSbService().getModel("zb").getSplc();
        if(Constants.YW_YTH.equals(model.getShzt())){
        	splc = model.getSplcid();
		}
        model.setShzt(Constants.YW_SHZ);
		model.setSplcid(splc);
		boolean flag = dao.runUpdate(model);
		if(flag){
			 shlc.runSubmit(model.getSbsqid(), splc, model.getXh(), "xg_xlzxnew_zbsh.do", "xg_xlzxnew_zbsb.do");
		}
		return flag;
	}
	
	//����
	public boolean cancelRecord(String ywid, String lcid) throws Exception {
		return shlc.firstStepCancle(ywid, lcid);
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @����: ��ȡѧ����ѯ
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-8-30 ����01:45:44
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param user
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getStuCx(ZbsbForm t, User user,String xhs) throws Exception{
		return dao.getStuCx(t, user, xhs);
	}
	
	/**
	 * 
	 * @����: �����ְ����
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-9-1 ����10:32:19
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean checkRzrq(String xh){
		return dao.checkRzrq(xh);
	}
	
	/**
	 * 
	 * @����: ��֤�Ƿ�����д�ܱ�
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-9-1 ����11:33:20
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param bjdm
	 * @param sbzbid
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean checkIsNotExist(String bjdm,String sbzbid){
		return dao.checkIsNotExist(bjdm, sbzbid);
	}
}
