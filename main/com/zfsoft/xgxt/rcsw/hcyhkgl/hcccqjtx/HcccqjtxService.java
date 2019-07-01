/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-12-17 ����02:50:05
 */
package com.zfsoft.xgxt.rcsw.hcyhkgl.hcccqjtx;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;


/**
 * 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �𳵳˳�������д����ģ��
 * @�๦������: TODO(�𳵳˳�������д) 
 * @���ߣ�Dlq[����:995]
 * @ʱ�䣺 2013-12-26 ����09:42:49 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class HcccqjtxService extends
		SuperServiceImpl<HcccqjtxForm, HcccqjtxDao> {

	private HcccqjtxDao dao = new HcccqjtxDao();
	private ShlcInterface shlc = new CommShlcImpl();
	public static final String SUBMIT = "submit";
	public static final String SAVE = "save";
	public static String _BCZSCID="-1";

	public HcccqjtxService() {
		super.setDao(dao);
	}

	/**
	 * 
	 * @����:TODO(���ӻ𳵳˳�������д)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2013-12-17 ����04:21:18
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean saveHcccqjtx(HcccqjtxForm model) throws Exception {
		
		if(model.getType().equals(SUBMIT)){
			model.setShzt(Constants.YW_SHZ);//�����
		}else{
			model.setShzt(Constants.YW_WTJ);//δ�ύ
		}
		// ��ȡ��������
		String splc = dao.getShlcID();
		model.setSplc(splc);
		boolean insertResult = super.runInsert(model);
		if( SAVE.equalsIgnoreCase(model.getType())){
			return insertResult;
		}
		boolean result = false;
		if (insertResult && SUBMIT.equalsIgnoreCase(model.getType())) {
			//�����������
			result = shlc.runSubmit(model.getCcqjtxid(),splc,model.getXh(),"rcsw_hcyhk_hcccqjsh.do","rcsw_hcyhk_hcccqjtx.do");
		}
		return result;
	}
	
	/**
	 * 
	 * @����:TODO(�޸Ļ𳵳˳�������д)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2013-12-24 ����04:13:21
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean updateHcccqjtx(HcccqjtxForm model) throws Exception {
		
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
			result = shlc.runSubmit(model.getCcqjtxid(), model.getSplc(),model.getXh(),"rcsw_hcyhk_hcccqjsh.do","rcsw_hcyhk_hcccqjtx.do");
			return result;
		}else{
			return insertResult;
		}
		
	}
	
	/**
	 * 
	 * @����:TODO(ɾ���𳵳˳�������д)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2013-12-24 ����04:21:56
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ids
	 * @return
	 * @throws Exception
	 * String[] �������� 
	 * @throws
	 */
	public String[] deleteHcccqjtx(String[] ids) throws Exception{
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
				HashMap<String, String> hm=dao.getHcccqjtx(str);
				noDel.append(hm.get("xh"));
				noDel.append("&nbsp;");
				noDel.append(hm.get("xm"));
				noDel.append(",</br>");
				isHaveNoId=true;
			}
		}
		int i=delId.size()>0?hcccqjtxDelete(delId.toArray(new String[]{})):0;
		String str=noDel.toString();
		//ȥ�������ය��
		str=isHaveNoId?str:_BCZSCID;
		return new String[]{String.valueOf(i),str};
	}
	
	private int hcccqjtxDelete(String[] ids) throws Exception {
		for(String id:ids){
			shlc.deleteShjl(id);
		}
		return runDelete(ids);
	}
	
	/**
	 * 
	 * @����:TODO(�ύ�𳵳���������д)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2013-12-17 ����06:11:31
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean submitHcccqjtx(HcccqjtxForm model) throws Exception {
		
		if(!Constants.YW_YTH.equalsIgnoreCase(model.getShzt())){
			// ��ȡ��������
			model.setSplc(dao.getShlcID());
		}
		
		model.setShzt(Constants.YW_SHZ);
		boolean resultHcccqjtx = dao.updateHcccqjtx(model);
		boolean result = false;
		if(resultHcccqjtx){
			//�����������
			result = shlc.runSubmit(model.getCcqjtxid(), model.getSplc(),model.getXh(),"rcsw_hcyhk_hcccqjsh.do","rcsw_hcyhk_hcccqjtx.do");
		}
		return result;
	}
	
	/**
	 * 
	 * @����:TODO(���»𳵳˳�������д)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2013-12-17 ����06:27:24
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean updateHcccqjtxzt(HcccqjtxForm model) throws Exception {
			boolean resultBbsq = dao.updateHcccqjtx(model);
			return resultBbsq;
	}
	
	/**
	 * 
	 * @����:TODO(��ȡ�𳵳˳�������д��ϸ��Ϣ)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2013-12-19 ����04:46:29
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String> getHcccqjtxInfo(HcccqjtxForm model) {
		if (StringUtil.isNull(model.getCcqjtxid())) {
			logger.error("����ID����Ϊ�գ�");
			throw new NullPointerException();
		}
		return dao.getHcccqjtxInfo(model);
	}
	

	/**
	 * 
	 * @����:ֻ�и��ύ���ҵ�һ��δ��˵�ǰ���£������˿��Գ���
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2013-12-3 ����09:18:51
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
	 * @����:TODO(����ѧ��, ѧ��,ѧ���жϻ𳵳˳�������д�����Ƿ��Ѿ����ڸ�ѧ��)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2014-2-13 ����05:23:02
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean isExistByHcccqjtx(HcccqjtxForm model,String type)
	throws Exception {
		boolean flag = false;
		if("save".equalsIgnoreCase(type)) {
			String num = dao.checkExistForHcccqjtxSave(model);
			if (!"0".equalsIgnoreCase(num)) {
				flag = true;
			}
		}else{
			String num = dao.checkExistForHcccqjtxUpdate(model);
			if (!"0".equalsIgnoreCase(num)) {
				flag = true;
			}
		}
	
		return flag;
	}
	
	/**
	 * 
	 * @����:TODO(��ѯ�𳵳���)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2013-12-26 ����09:30:55
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean allowUpdateSetting() {
		try {
			return dao.getDshCount() == 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * 
	 * @����:TODO(��ȡ��������Ĭ�ϵĳ˳����վ)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2014-2-19 ����11:29:30
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getXxccqdz() {
		return dao.getXxccqdz();
	}
	
	
	/**
	 * 
	 * @����:����ѧ�Ż�ȡ���³˳�������
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-8-28 ����11:58:19
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String>getHcccqj(String xh){
		return dao.getHcccqj(xh);
	}
	
	/*
	 * ȡѧ������
	 */
	public String getXqmc(String xq){
		return dao.getXqmc(xq);
	}
}
