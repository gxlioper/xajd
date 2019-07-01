/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-6-1 ����11:14:56 
 */  
package com.zfsoft.xgxt.qgzx.xsgwnew.sq;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import xgxt.action.Base;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �ڹ���ѧ
 * @�๦������: ѧ����λ���� 
 * @���ߣ� ������[����:1123]
 * @ʱ�䣺 2016-6-1 ����11:14:56 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XsgwsqService extends SuperServiceImpl<XsgwsqForm, XsgwsqDao>{
	
	private ShlcInterface shlc = new CommShlcImpl();
	
	@Override
	public XsgwsqForm getModel(XsgwsqForm t) throws Exception {
		return dao.getModel(t);	
	}
	
	/**
	 * 
	 * @����: �ڹ���ϸ
	 * @���ߣ�������[����:1123]
	 * @���ڣ�2016-6-2 ����09:53:54
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param id
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getQgmxList(String id){
		
		List<HashMap<String,String>> qgmxList = dao.getQgmxList(id);
		
		return qgmxList;
	}
	
	/**
	 * 
	 * @����: ����
	 * @���ߣ�������[����:1123]
	 * @���ڣ�2016-6-2 ����10:03:35
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean saveSq(XsgwsqForm model) throws Exception {
		
		String guid = UniqID.getInstance().getUniqIDHash();
		model.setSqbh(guid);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		model.setSqsj(sdf.format(new Date()));
		model.setShzt(Constants.YW_WTJ);
		
		//�ж�ѧ�ź͸�λID�Ƿ���ڣ�������ھͲ��������С���ע���ڹ������˸����⣬�����۲����ǣ����������ع�
		if(dao.getExist(model)){
			throw new SystemException(MessageKey.SYS_AUD_DOUBLE);
		}
		
		// ��ȡ��������
		String splc = dao.getShlcID();
		model.setSplc(splc);
		boolean insertResult = super.runInsert(model);
		return insertResult;
		
	}
	
	public String saveSq(XsgwsqForm model, String type) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		model.setSqsj(sdf.format(new Date()));
		model.setXn(Base.currXn);
		model.setShzt(Constants.YW_WTJ);
		// ��ȡ��������
		String splc = dao.getShlcID();
		model.setSplc(splc);
		// ��������������趨��ʼֵ
		if (splc != null && !"".equals(splc)) {
			model.setShzt(Constants.YW_SHZ);// �����
		}
		boolean insertResult=false;
		String guid = UniqID.getInstance().getUniqIDHash();
		if(!StringUtil.isNull(model.getSqbh())){
			guid=model.getSqbh();
			insertResult = super.runUpdate(model);
		}else{
			model.setSqbh(guid);
			insertResult = super.runInsert(model);
		}
		if (insertResult) {
			insertResult = shlc.runSubmit(guid, splc, model.getXh(),
					"qgzx_xsgwshnew_sh.do",
					"qgzx_xsgwsqnew_sq.do");
		}
		
		return String.valueOf(insertResult);
	}
	
	/**
	 * 
	 * @����: �����ڹ���ϸ
	 * @���ߣ�������[����:1123]
	 * @���ڣ�2016-6-2 ����10:24:11
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param mxrq
	 * @param mxxmList
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean saveQgmx(XsgwsqForm model,String[] mxrq,List<String[]> mxxmList) throws Exception{
		
		List<String[]> params = new ArrayList<String[]>();
		
		for (int i = 0 ; i < mxrq.length ; i++){
			String[] param = new String[]{model.getXh(),mxrq[i],StringUtils.joinArr(mxxmList.get(i)),i+""};
			params.add(param);
		}
		
		dao.delQgmx(model.getXh());
		return dao.saveQgmx(params);
	}
	
	/**
	 * 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�������[����:1123]
	 * @���ڣ�2016-6-2 ����03:41:03
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param pkValue
	 * @param lcid
	 * @param xh
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean submitRecord(String pkValue, String lcid, String xh)
			throws Exception {
		boolean result = false;
		result = shlc.runSubmit(pkValue, lcid, xh,
				"qgzx_xsgwshnew_sh.do",
				"qgzx_xsgwsqnew_sq.do");
		return result;
	}
	
	public boolean updateModel(XsgwsqForm model) throws Exception {		
		return super.runUpdate(model);
	}
	
	public boolean cancleRecord(String ywid, String lcid) throws Exception {
		return shlc.firstStepCancle(ywid, lcid);
	}
	
	public List<HashMap<String, String>> getGwPageList(XsgwsqForm model, User user) throws Exception {
		return dao.getGwPageList(model, user);
	}
	
	public int getXszggwsl(XsgwsqForm model) throws SQLException {
		return dao.getXszggwsl(model.getGwdm(), model.getXh());
	}
	
	public int getXssqsl(XsgwsqForm model) throws SQLException {
		return dao.getXssqsl(model.getGwdm(), model.getXh());
	}
	
	public HashMap<String, String> getCsszb(){
		return dao.getCsszb();
	}
	
	public boolean isCheck(String splc, String yzgw, String shgw) {
		if (StringUtils.isNull(splc) || StringUtils.isNull(yzgw)
				|| StringUtils.isNull(shgw)) {
			return false;
		}
		//��������� ��˸�λ����Ϊ -1 ʱ Ϊ�˻ظ�������
		if("-1".equals(shgw)){
			return false;
		}
		String yzxh = dao.getGwSpXh(splc, yzgw);
		String shxh = dao.getGwSpXh(splc, shgw);
		if (Integer.parseInt(yzxh) <= Integer.parseInt(shxh)) {
			return true;
		}
		return false;
	}
	
	public String yzgwxx(String gwdm, String xh) throws Exception {
		String message = "";
		HashMap<String, String> gwxx = dao.getGwxx(gwdm);
		String xqrs = gwxx.get("xqrs");// ����������
		String knsrs = gwxx.get("knsrs");// ������������
		String gwrs = gwxx.get("gwrs");// �ڸ�������
		String zgknss = gwxx.get("zgknss");// �ڸ������� ��
		int xqfkns = Integer.parseInt(xqrs) - Integer.parseInt(knsrs);
		int zgfkns = Integer.parseInt(gwrs) - Integer.parseInt(zgknss);

		int iskns = dao.isKns(gwdm, xh);
/*		if (Integer.parseInt(gwrs) >= Integer.parseInt(xqrs)) {
			message = "��λ��Ա����";
		} else */
		if (xqfkns - zgfkns <= 0 && iskns == 0){
			message = "�ø�λ�ķ������������Ѵﵽ���ޣ�������������λ��"; 
		}else if (Integer.parseInt(gwrs) >= Integer.parseInt(xqrs) && iskns != 0) {
			message = "��λ�����Ѵﵽ���ޣ�������������λ��";
		} else {
			message = "true";
		}
		return message;
	}
	
	public String yzjb(String xh,String splc,String gwdm, String yzgw, String shgw,boolean isTj)
			throws SQLException {
		String message = "true";
		// ��ǰ�û�����֤��λ �򲻽�����֤�� ������ύʹ���������֤
		if (isCheck(splc, yzgw, shgw)||isTj) {
			if(gwdm == null) {
				return "LastLc";
			}
			HashMap<String, String> gwxx = dao.getGwxx(gwdm);
			String sfsgwsqsxz = gwxx.get("sfsgwsqsxz");
			String xqrs = gwxx.get("xqrs");// ����������
			Integer gwshtgrsI=dao.getGwShtgRs(splc,shgw,gwdm);//��λ���ͨ������
			if(gwshtgrsI>=Integer.parseInt(xqrs)&&!"0".equals(xqrs)){
				message = "�ѳ�����ǰ��˸�λ����������";
			}else{
				HashMap<String, String> map = getCsszb();
				String xsgws = map.get("xsgws");//ѧ���ɻ�ø�λ��
				Integer xshqGws=dao.getTgRs(splc,xh,shgw);//ѧ����ȡ��λ��
				if ("1".equals(sfsgwsqsxz) && xshqGws >= Integer.parseInt(xsgws)&&!"0".equals(xsgws)) {
					message = "��ѧ���Ѿ���" + xshqGws + "����λ������ѧ������λ��";
				}
			}
		}
		return message;
	}
	
	public boolean isHaveRecordForSq(String xh, String xn) {
		return dao.isHaveRecordForSq(xh, xn);
	}
}
