package com.zfsoft.xgxt.pjpy.xzhcp.sq;

import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONObject;
import xgxt.action.Base;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.pjpy.xzhcp.ZhcpService;
import com.zfsoft.xgxt.rcsw.qjgl.xjsqsh.XjsqcsszService;
import com.zfsoft.xgxt.rcsw.qjgl.xjsqsh.XjsqshForm;
import com.zfsoft.xgxt.xpjpy.cssz.CsszModel;
import com.zfsoft.xgxt.xpjpy.cssz.CsszService;

public class ZhcpDjService extends SuperServiceImpl<ZhcpDjForm,ZhcpDjDao> {
	private ShlcInterface shlc = new CommShlcImpl();
	private static final String MESSAGE_REPEAT = "��ѧ��ѧ�����м�¼�������ظ���д��";
	/**
	 * @throws Exception 
	 * 
	 * @����: �����ۺϲ���
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2018-1-30 ����10:45:16
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param zhcpForm
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean save(ZhcpDjForm zhcpForm) throws Exception{
		boolean rs = true;
		CsszModel cssz = new CsszService().getModel();
		if("submit".equals(zhcpForm.getType())){
			zhcpForm.setShzt(Constants.YW_SHZ);
		}else{
			zhcpForm.setShzt(Constants.YW_WTJ);
		}
		zhcpForm.setSplc(new ZhcpService().getModel().getSplc());
		if(StringUtils.isNotNull(zhcpForm.getSqid())){
			rs = dao.runUpdate(zhcpForm);
		}else{
			zhcpForm.setXn(cssz.getXn());
			if(!"10364".equals(Base.xxdm)){
				zhcpForm.setXq(cssz.getXq());
			}
			if(!dao.checkNotRepeat(zhcpForm)){
				JSONObject json = new JSONObject();
				json.put("message", MESSAGE_REPEAT);
				throw new SystemException(json);
			}
			zhcpForm.setSqid(UniqID.getInstance().getUniqIDHash().toUpperCase());
			rs = dao.runInsert(zhcpForm);
		}
		if("submit".equals(zhcpForm.getType())){
			if (rs) {
				rs = shlc.runSubmit(zhcpForm.getSqid(), zhcpForm.getSplc(),zhcpForm.getXh(), "pjpy_xzhcp_zcdj.do", "pjpy_xzhcp_zcsh.do");
			}
		}
		return rs;
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @����: �ύ
	 * @���ߣ�����Դ[���ţ�1206]06
	 * @�޸ļ�¼: �޸�������-�޸�����
	 * @���ڣ�2018-1-30 ����10:50:-�޸�����
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean submitBusiness(ZhcpDjForm model, User user) throws Exception{
		String splc = new ZhcpService().getModel().getSplc();
        if(Constants.YW_YTH.equals(model.getShzt())){
        	splc = model.getSplc();
		}
        model.setShzt(Constants.YW_SHZ);
		model.setSplc(splc);
		boolean flag = dao.runUpdate(model);
		if(flag){
			shlc.runSubmit(model.getSqid(), model.getSplc(),model.getXh(), "pjpy_xzhcp_zcdj.do", "pjpy_xzhcp_zcsh.do");
		}
		return flag;
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @����: ��������
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2018-1-30 ����11:24:07
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean cancelSq(String ywid,String lcid) throws Exception{
		return shlc.firstStepCancle(ywid, lcid);
	}
	
	/**
	 * 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2018-2-28 ����10:29:48
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param xn
	 * @param xq
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getZcfsForDjb(String xh,String xn,String xq){
		return dao.getZcfsForDjb(xh, xn, xq);
		
	}
	
	/**
	 * 
	 * @����: ��ȡ������ͷ�
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2018-2-28 ����02:23:32
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getDkZdf(String xh,String xn,String xq){
		return dao.getDkZdf(xh, xn, xq);
	}
	
	/**
	 * 
	 * @����: ��ȡѧ��������Ϣ
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2018-2-28 ����02:42:47
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param xn
	 * @param xq
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String,String> getXsjbxx(String xh){
		return dao.getXsjbxx(xh);
	}
	
	/**
	 * 
	 * @����: ��ȡ�༶����
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2018-2-28 ����03:17:53
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getBjrs(String bjdm){
		return dao.getBjrs(bjdm);
	}
	
	/**
	 * 
	 * @����: ��ȡ���������List
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2018-3-5 ����10:51:40
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param xn
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getListByHjjg(String xh,String xn){
		List<HashMap<String,String>> pjjgList = dao.getListByHjjg(xh, xn);
		for(int i=pjjgList.size();i<5;i++){
			pjjgList.add(new HashMap<String,String>());
		}
		return pjjgList;
	}
}
