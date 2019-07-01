/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-12-17 ����02:50:05
 */
package com.zfsoft.xgxt.rcsw.dxsylbx.ylbxsq;

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
 * @ģ������: ѧ��֤�����������ģ��
 * @�๦������: (ѧ��֤��������) 
 * @���ߣ�Dlq[����:995]
 * @ʱ�䣺 2013-12-17 ����02:50:27 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class YlbxsqService extends
		SuperServiceImpl<YlbxsqForm, YlbxsqDao> {

	private YlbxsqDao dao = new YlbxsqDao();
	private ShlcInterface shlc = new CommShlcImpl();
	public static final String SUBMIT = "submit";
	public static final String SAVE = "save";
	public static String _BCZSCID="-1";

	public YlbxsqService() {
		super.setDao(dao);
	}

	/**
	 * 
	 * @����:(��ȡ��������ά������)
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
	 * @����:(����ҽ�Ʊ�������)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2014-1-8 ����03:46:54
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean saveYlbxsq(YlbxsqForm model) throws Exception {
		
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
			result = shlc.runSubmit(model.getYlsqid(),splc,model.getXh(),"rcsw_dxsylbx_ylbxsh.do","rcsw_dxsylbx_ylbxsq.do");
		}
		return result;
	}
	
	/**
	 * 
	 * @����:(������һ�仰�����������������)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2014-1-8 ����08:35:00
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param request
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getCzqebzryList() {
		return dao.getCzqebzryList();
	}
	
	/**
	 * 
	 * @����:(������һ�仰�����������������)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2014-1-8 ����08:35:04
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param request
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getCbzkList() {
		return dao.getCbzkList();
	}
	
	/**
	 * 
	 * @����:(��ǰѧ������)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2014-1-8 ����03:17:45
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * String �������� 
	 * @throws
	 */
	public String getCurrentXqmc(YlbxsqForm model)throws Exception {
		String xqmc = dao.getCurrentXqmc(model);
		return xqmc;
	}
	
	
	/**
	 * 
	 * @����:(�޸�ҽ�Ʊ�������)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2014-1-8 ����03:47:46
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean updateYlbxsq(YlbxsqForm model) throws Exception {
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
			shlc.deleteShjl(model.getYlsqid());
			result = shlc.runSubmit(model.getYlsqid(), model.getSplc(),model.getXh(),"rcsw_dxsylbx_ylbxsh.do","rcsw_dxsylbx_ylbxsq.do");
		}
		return insertResult && result;
	}
	
	
	
	
	/**
	 * 
	 * @����:(ɾ��ҽ�Ʊ�������)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2014-1-14 ����08:30:02
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ids
	 * @return
	 * @throws Exception
	 * String[] �������� 
	 * @throws
	 */
	public String[] delYlbxsq(String[] ids) throws Exception{
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
				HashMap<String, String> hm=dao.getYlbxsq(str);
				noDel.append(hm.get("xh"));
				noDel.append("&nbsp;");
				noDel.append(hm.get("xm"));
				noDel.append(",</br>");
				isHaveNoId=true;
			}
		}
		int i=delId.size()>0?ylbxsqDelete(delId.toArray(new String[]{})):0;
		String str=noDel.toString();
		//ȥ�������ය��
		str=isHaveNoId?str:_BCZSCID;
		return new String[]{String.valueOf(i),str};
	}
	
	
	/**
	 * 
	 * @����:(ɾ��ҽ�Ʊ�������)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2014-1-14 ����08:30:21
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ids
	 * @return
	 * @throws Exception
	 * int �������� 
	 * @throws
	 */
	private int ylbxsqDelete(String[] ids) throws Exception {
		for(String id:ids){
			shlc.deleteShjl(id);
		}
		return runDelete(ids);
	}
	
	/**
	 * 
	 * @����:(�ύҽ�Ʊ�������)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2014-1-14 ����08:30:47
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean submitYlbxsq(YlbxsqForm model) throws Exception {
		
		if(!Constants.YW_YTH.equalsIgnoreCase(model.getShzt())){
			// ��ȡ����������
			model.setSplc(dao.getShlcID());
		}
		
		model.setShzt(Constants.YW_SHZ);
		boolean resultYlbxsq = dao.updateYlbxsq(model);
		boolean result = false;
		if(resultYlbxsq){
		//�����������
		result = shlc.runSubmit(model.getYlsqid(), model.getSplc(),model.getXh(),"rcsw_dxsylbx_ylbxsh.do","rcsw_dxsylbx_ylbxsq.do");
		}
		return result;
	}
	
	/**
	 * 
	 * @����:(����ҽ�Ʊ�������)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2014-1-14 ����08:31:12
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean cancelYlbxsq(YlbxsqForm model) throws Exception {
			boolean resultYlbxsq = dao.updateYlbxsq(model);
			return resultYlbxsq;
	}
	
	/**
	 * 
	 * @����:(��ȡҽ�Ʊ���������Ϣ)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2014-1-13 ����04:36:47
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String> getYlbxsqInfo(YlbxsqForm model) {
		if (StringUtil.isNull(model.getYlsqid())) {
			logger.error("����ID����Ϊ�գ�");
			throw new NullPointerException();
		}
		return dao.getYlbxsqInfo(model); 
	}
	

	/**
	 * 
	 * @����:(ֻ�и��ύ���ҵ�һ��δ��˵�ǰ���£������˿��Գ���)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2014-1-14 ����08:31:53
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
	 * @����:(����ҽ�Ʊ���������Ϣ)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2014-1-14 ����08:32:28
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
	 * @����:(����ѧ���жϸ�ѧ����ѧ��ҽ�Ʊ��������Ƿ��Ѿ�����)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2014-1-14 ����08:35:42
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean isExistByXszbbsq(YlbxsqForm model)
	throws Exception {
		boolean flag = false;
		String num = dao.checkExistForSave(model);
		if (!"0".equalsIgnoreCase(num)) {
			flag = true;
		}
		return flag;
	}
  
	
}
