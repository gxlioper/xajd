/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-12-17 ����02:50:05
 */
package com.zfsoft.xgxt.rcsw.xszbb.xszbbsq;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.rcsw.xszbb.xszbblxwh.XszbblxwhDao;
import com.zfsoft.xgxt.rcsw.xszbb.xszbblxwh.XszbblxwhForm;


/**
 * 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ѧ��֤�����������ģ��
 * @�๦������: TODO(ѧ��֤��������) 
 * @���ߣ�Dlq[����:995]
 * @ʱ�䣺 2013-12-17 ����02:50:27 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class XszbbsqService extends
		SuperServiceImpl<XszbbsqForm, XszbbsqDao> {

	private XszbbsqDao dao = new XszbbsqDao();
	private ShlcInterface shlc = new CommShlcImpl();
	public static final String SUBMIT = "submit";
	public static final String SAVE = "save";
	public static String _BCZSCID="-1";

	public XszbbsqService() {
		super.setDao(dao);
	}

	/**
	 * 
	 * @����:TODO(��ȡ��������ά������)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2013-12-17 ����02:50:05
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getBblxwhList() {
		return dao.getBblxwhList();
	}
	

	/**
	 * 
	 * @����:TODO(����ѧ��֤��������)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2013-12-17 ����04:21:18
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean saveXszbbsq(XszbbsqForm model) throws Exception {
		
		if(model.getType().equals(SUBMIT)){
			model.setShzt(Constants.YW_SHZ);//�����
		}else{
			model.setShzt(Constants.YW_WTJ);//δ�ύ
		}
		// ��ȡ��������
		XszbblxwhForm bblx = new XszbblxwhForm();
		bblx.setXszbblxdm(model.getXszbblxdm());
		
		String splc = new XszbblxwhDao().getModel(bblx).getShlc();//dao.getShlcID();
		model.setSplc(splc);
		boolean insertResult = super.runInsert(model);
		if( SAVE.equalsIgnoreCase(model.getType())){
			return insertResult;
		}
		boolean result = false;
		if (insertResult && SUBMIT.equalsIgnoreCase(model.getType())) {
			//�����������
			result = shlc.runSubmit(model.getBbsqid(), splc,model.getXh(),"rcsw_xszbb_bbsh.do","rcsw_xszbb_bbsq.do");
		}
		return result;
	}
	
	/**
	 * 
	 * @����:TODO(�޸�ѧ��֤��������)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2013-12-17 ����04:21:40
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean updateXszbbsq(XszbbsqForm model) throws Exception {
		
		if(model.getType().equals(SUBMIT)&& !Constants.YW_YTH.equalsIgnoreCase(model.getShzt())){
			// ��ȡ��������
			XszbblxwhForm bblx = new XszbblxwhForm();
			bblx.setXszbblxdm(model.getXszbblxdm());
			String splc = new XszbblxwhDao().getModel(bblx).getShlc();//dao.getShlcID();
			model.setSplc(splc);
		}
		
		if(model.getType().equals(SUBMIT)){
			model.setShzt(Constants.YW_SHZ);//�����
		}
		
		boolean insertResult = super.runUpdate(model);
		boolean result = false;
		if (insertResult) {
			shlc.deleteShjl(model.getBbsqid());
			if("update".equalsIgnoreCase(model.getType())){
				result = true;
			}else{
				result = shlc.runSubmit(model.getBbsqid(), model.getSplc(),model.getXh(),"rcsw_xszbb_bbsh.do","rcsw_xszbb_bbsq.do");
			}
		}
		return result;
	}
	
	/**
	 * 
	 * @����:TODO(ɾ��ѧ��֤��������)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2013-12-17 ����06:11:45
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ids
	 * @return
	 * @throws Exception
	 * String[] �������� 
	 * @throws
	 */
	public String[] deleteXszbbsq(String[] ids) throws Exception{
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
				HashMap<String, String> hm=dao.getBbsq(str);
				noDel.append(hm.get("xh"));
				noDel.append("&nbsp;");
				noDel.append(hm.get("xm"));
				noDel.append(",</br>");
				isHaveNoId=true;
			}
		}
		int i=delId.size()>0?bbsqDelete(delId.toArray(new String[]{})):0;
		String str=noDel.toString();
		//ȥ�������ය��
		str=isHaveNoId?str:_BCZSCID;
		return new String[]{String.valueOf(i),str};
	}
	
	/**
	 * 
	 * @����:TODO(ɾ��ѧ��֤��������)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2013-12-26 ����09:32:01
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ids
	 * @return
	 * @throws Exception
	 * int �������� 
	 * @throws
	 */
	private int bbsqDelete(String[] ids) throws Exception {
		for(String id:ids){
			shlc.deleteShjl(id);
		}
		return runDelete(ids);
	}
	
	/**
	 * 
	 * @����:TODO(�ύѧ��֤��������)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2013-12-17 ����06:11:31
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean submitBbsq(XszbbsqForm model) throws Exception {
		boolean resultBbsq = dao.updateBbsq(model);
		boolean result = false;
		if(resultBbsq){
		//�����������
		result = shlc.runSubmit(model.getBbsqid(), model.getSplc(),model.getXh(),"rcsw_xszbb_bbsh.do","rcsw_xszbb_bbsq.do");
		}
		return result;
	}
	
	/**
	 * 
	 * @����:TODO(���²�������)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2013-12-17 ����06:27:24
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean updateBbsq(XszbbsqForm model) throws Exception {
			boolean resultBbsq = dao.updateBbsq(model);
			return resultBbsq;
	}
	
	/**
	 * 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2013-12-19 ����04:46:29
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String> getXszbbsqInfo(XszbbsqForm model) {
		if (StringUtil.isNull(model.getBbsqid())) {
			logger.error("����ID����Ϊ�գ�");
			throw new NullPointerException();
		}
		return dao.getXszbbsqInfo(model);
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
	 * @����:TODO(����ѧ��֤��������)
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
	 * @����:TODO(����ѧ���жϸ�ѧ��֤���������Ƿ��Ѿ�����)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2013-12-26 ����08:39:11
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean isExistByXszbbsq(XszbbsqForm model)
	throws Exception {
		boolean flag = false;
		String num = dao.checkExistForSave(model);
		if (!"0".equalsIgnoreCase(num)) {
			flag = true;
		}
		return flag;
	}
	
	/** 
	 * @����:��ȡ�𳵳˳���������(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-5-23 ����05:07:08
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws 
	 */
	public HashMap<String,String> getHcccqj(String xh){
		return dao.getHcccqj(xh);
	}
  
	
}
