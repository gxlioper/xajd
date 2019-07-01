/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-7-22 ����10:43:02 
 */  
package com.zfsoft.xgxt.xsztz.tttzxm.sq;

import org.apache.poi.sl.draw.geom.Guide;

import xgxt.action.Base;
import xgxt.form.User;
import xgxt.utils.GetTime;
import xsgzgl.gygl.xyzsgl.sq.XyzsSqForm;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.xsztz.tttzxm.comm.CommTtxmService;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� yxy[����:1206]
 * @ʱ�䣺 2016-7-22 ����10:43:02 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class TttzxmService extends SuperServiceImpl<TttzxmForm, TttzxmDao> {
	private static final String DATE_FORMAT = "yyyy-MM-dd hh24:mi:ss";
	private ShlcInterface shlc = new CommShlcImpl();
	private CommTtxmService commService = new CommTtxmService();

	/**
	 * 
	 * @����:�����������루���ӣ�
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-7-26 ����03:59:55
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean saveTtsq(TttzxmForm model, User user) throws Exception {
		String splc = dao.getShlcID(model.getXmdm());
		model.setSplc(splc);
		model.setSqsj(GetTime.getTimeByFormat(DATE_FORMAT));
		if(model.getType().equals("save")){
			model.setShzt(Constants.YW_WTJ);
		}else{
			model.setShzt(Constants.YW_SHZ);
		}
		String ttsqid = UniqID.getInstance().getUniqIDHash().toUpperCase();
		model.setTtsqid(ttsqid);
		boolean flag = commService.saveTtcy(ttsqid, model.getXmdm(), model.getXhArr());
		if(flag){
			flag = dao.runInsert(model);
		}
		
		if(model.getType().equals("submit")){
			if (flag) {
				flag = shlc.runSubmit(ttsqid, splc, model.getSqr(), "sztz_ttxm_sh.do", "sztz_ttxm_sq.do");
			}
		}
		return flag;
	}
	
	/**
	 * 
	 * @����:�����������루�޸ģ�
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-7-26 ����04:00:01
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean saveTtsqUpdate(TttzxmForm model, User user) throws Exception {
		String splc = dao.getShlcID(model.getXmdm());
		model.setSplc(splc);
		if(model.getType().equals("update")){
			model.setShzt(Constants.YW_WTJ);
		}else{
			model.setShzt(Constants.YW_SHZ);
		}
		boolean flag = commService.saveTtcy(model.getTtsqid(), model.getXmdm(), model.getXhArr());
		if(flag){
			flag = dao.runUpdate(model);
		}
		if(model.getType().equals("updatesubmit")){
			if(flag){
				flag = shlc.runSubmit(model.getTtsqid(), model.getSplc(),model.getSqr(), "sztz_ttxm_sh.do", "sztz_ttxm_sq.do");
			}
		}
		return flag;
	}
	
	/**
	 * 
	 * @����:�ύ
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-7-26 ����04:32:43
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean submitBusi(TttzxmForm model, User user)  throws Exception {
		model.setShzt(Constants.YW_SHZ);
		boolean flag = dao.runUpdate(model);
		String splc =  dao.getShlcID(model.getXmdm());
		model.setSplc(splc);
		if(flag){
			 shlc.runSubmit(model.getTtsqid(), model.getSplc(), model.getSqr(), "sztz_ttxm_sh.do", "sztz_ttxm_sq.do");
		}
		return flag;
	}
	
	/**
	 * 
	 * @����: ����
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-7-26 ����04:35:29
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ywid
	 * @param lcid
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean cancelRecord(String ywid, String lcid) throws Exception {
		return shlc.firstStepCancle(ywid, lcid);
	}
}
