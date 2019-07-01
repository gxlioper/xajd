/**
s * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-9-9 ����12:06:43 
 */
package com.zfsoft.xgxt.gygl.ssyd.ydsq;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.action.Base;
import xgxt.action.base.BaseDAO;
import xgxt.form.User;
import xgxt.studentInfo.dao.XsxxglDAO;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;
import xsgzgl.gygl.cwgl.CwglService;

import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.gygl.ssyd.shlc.ShlcForm;
import com.zfsoft.xgxt.gygl.ssyd.shlc.ShlcService;
import com.zfsoft.xgxt.rcsw.qjgl.BaseDbcz;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ��ٹ���ģ��
 * @�๦������: �������service
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2013-9-9 ����12:06:43
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class YdsqService extends SuperServiceImpl<YdsqForm, YdsqDao> {
	/**
	 * �����ڲ���ɾ������
	 */
	public static String _BCZSCID = "-1";
	// 00�����޲�����01����������02ʵϰ���ޣ����ݹ�ҵְҵ����ѧԺ����03��ס����
	// ����
	public static String _YDLX_TS = "00";
	// �������
	public static String _YDLX_SSTZ = "01";
	// ��ס
	public static String _YDLX_RZ = "03";

	YdsqDao dao = new YdsqDao();
	XsxxglDAO xxdao = new XsxxglDAO();
	private ShlcInterface shlc = new CommShlcImpl();

	public YdsqService() {
		this.setDao(dao);
	}

	/**
	 * 
	 * @����: �����{��ԭ��
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-9-25 ����11:47:59
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return List<HashMap<String,String>> ��������
	 */
	public List<HashMap<String, String>> getTstzyy() {
		return dao.getTstzyy();
	}
	/**
	 * �����{��ԭ��
	 */
	public List<HashMap<String, String>> getTzyy() {
		return dao.getTzyy();
	}

	@Override
	public YdsqForm getModel(YdsqForm t) throws Exception {
		YdsqForm yf = super.getModel(t);
		String ssydlx = yf.getSsydlx();
		String ssydlxmc = "";
		if(ssydlx.equals(_YDLX_TS)){
			ssydlxmc = "����";
		}else if(ssydlx.equals(_YDLX_SSTZ)){
			ssydlxmc = "�������";
		}else if(ssydlx.equals(_YDLX_RZ)){
			ssydlxmc = "��ס";
		}
		yf.setSsydlxmc(ssydlxmc);
		
		if(ssydlx.equals(_YDLX_RZ)){//��סԭ��
			CwglService cwglService = new CwglService();
			List<HashMap<String, String>> rzyyList = cwglService.getRzyyList();
			//����ԭ������Ӧ����
			for(HashMap<String, String> hm:rzyyList){
				if(hm.get("rzyydm").equals(yf.getTstzyy())){
					yf.setTstzyymc(hm.get("rzyymc"));
				}
			}
		}else if(ssydlx.equals(_YDLX_SSTZ)){//����ԭ��
			//����ԭ������Ӧ����
			for(HashMap<String, String> hm:getTzyy()){
				if(hm.get("tzyydm").equals(yf.getTstzyy())){
					yf.setTstzyymc(hm.get("tzyymc"));
				}
			}
		}else{
			//����ԭ������Ӧ����
			for(HashMap<String, String> hm:getTstzyy()){
				if(hm.get("tsyydm").equals(yf.getTstzyy())){
					yf.setTstzyymc(hm.get("tsyymc"));
				}
			}
		}
		//���ѧ������
		yf.setXqmc(BaseDAO.getInstance().getXqmcForXqdm(yf.getXq()));
		//��ʵ��ʱ�������ͬ�����������ͺ�ʱ�䣬����ҳ����ʾ
		yf.setTzsssj(yf.getTstzsj());
		yf.setTzssyy(yf.getTstzyy());
		//��ʵ��ʱ�������ͬ������ס���ͺ�ʱ�䣬����ҳ����ʾ
		yf.setRzsssj(yf.getTstzsj());
		yf.setRzssyy(yf.getTstzyy());
		return yf;
	}
	
	public boolean submitRecord(String ydlx,String pkValue,String lcid,String xh) throws Exception {
		
		boolean result =false;
		
		result = shlc.runSubmit(pkValue, lcid,xh,"ydshbase.do","ydsqbase.do");
		
//		if(ydlx.equals(_YDLX_SSTZ)){
//			if(result){
//				//���ô�����Ϣ
//				BaseDbcz dbcz=new BaseDbcz();
//				dbcz.setShPath("ydshbase.do");
//				dbcz.setSqPath("ydsqbase.do");
//				dbcz.setGnmkMc("�����춯���");
//				dbcz.setXmmc("�������");
//				dbcz.sqPush(pkValue, xh, lcid);
//			}
//		}else{
//			if (result) {
//				//���ô�����Ϣ
//				BaseDbcz dbcz=new BaseDbcz();
//				dbcz.setShPath("ydshbase.do");
//				dbcz.setSqPath("ydsqbase.do");
//				dbcz.setGnmkMc("�����춯���");
//				dbcz.setXmmc("����");
//				dbcz.sqPush(pkValue, xh,lcid);
//			}
//		}
		return result;
	}
	
	
	/**
	 * 
	 * @����:����
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-9-9 ����06:46:35
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param qf
	 * @return boolean ��������
	 * @throws Exception
	 */
	public String save(YdsqForm model,String aType) throws Exception {

		HashMap<String, String> cwxxForXh = getCwxxForXh(model.getXh());
		//����ǰ��λ��Ϣ
		model.setTzqlddm(cwxxForXh.get("lddm"));
		model.setTzqldmc(cwxxForXh.get("ldmc"));
		model.setTzqqsh(cwxxForXh.get("qsh"));
		model.setTzqcwh(cwxxForXh.get("cwh"));
		
		//���������������õ�������
		if(model.getSsydlx().equals(_YDLX_SSTZ)){
			return saveTzss(model,aType);
		}else if(model.getSsydlx().equals(_YDLX_RZ)){//��ס����
			return saveRzss(model,aType);
		}
		String guid = UniqID.getInstance().getUniqIDHash();
		model.setSsydsqid(guid);
		
		
		model.setSqsj(GetTime.getTimeByFormat("yyyy-mm-dd hh24:mi:ss"));
		ShlcForm sf = getShlcForm(model.getSsydlx());
		//ShlcForm sf = getShlcForm();
		if (null == sf) {
			return MessageKey.SYS_SELECT_SHLC_FAIL;
		}
		model.setSplcid(sf.getSplcid());
		// ��������������趨��ʼֵ
		if(sf.getSplcid()!=null&& !"".equals(sf.getSplcid())){
			if(aType.equals("submit")){
				model.setShzt(Constants.YW_SHZ);//�����
			}else{
				model.setShzt(Constants.YW_WTJ);//δ�ύ
			}
		}
		boolean result = super.runInsert(model);
		if(aType.equals("submit")){
			if(result){
				result = shlc.runSubmit(guid, sf.getSplcid(),model.getXh(),"ydshbase.do","ydsqbase.do");
//				if (result) {
//					//���ô�����Ϣ
//					BaseDbcz dbcz=new BaseDbcz();
//					dbcz.setShPath("ydshbase.do");
//					dbcz.setSqPath("ydsqbase.do");
//					dbcz.setGnmkMc("�����춯���");
//					dbcz.setXmmc("����");
//					dbcz.sqPush(model.getSsydsqid(), model.getXh(), sf.getSplcid());
//				}
			}
		}
		return String.valueOf(result);
	}
	/**
	 * 
	 * @����:�������ᱣ��
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-9-9 ����06:46:35
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param qf
	 * @return boolean ��������
	 * @throws Exception
	 */
	public String saveTzss(YdsqForm model,String aType) throws Exception {

		String guid = UniqID.getInstance().getUniqIDHash();
		model.setSsydsqid(guid);
		
		model.setSqsj(GetTime.getTimeByFormat("yyyy-mm-dd hh24:mi:ss"));
		ShlcForm sf = getShlcForm(model.getSsydlx());
		//ShlcForm sf = getShlcForm();
		if (null == sf) {
			return MessageKey.SYS_SELECT_SHLC_FAIL;
		}
		model.setSplcid(sf.getSplcid());
		
		// ��������������趨��ʼֵ
		if(sf.getSplcid()!=null&& !"".equals(sf.getSplcid())){
			if(aType.equals("submit")){
				model.setShzt(Constants.YW_SHZ);//�����
			}else{
				model.setShzt(Constants.YW_WTJ);//δ�ύ
			}
		}
		//�ѵ������ͺ�ʱ��ͬ�������ݿ��ֶ�
		model.setTstzyy(model.getTzssyy());
		model.setTstzsj(model.getTzsssj());
		
		//������λ��Ϣ
		String ydhcwxx = model.getCwxx();
		
		if(ydhcwxx!=null && !"".equals(ydhcwxx)){
			String[] cwxx = ydhcwxx.split("_");
			// ��ѯ
			HashMap<String, String> cwxxMap = getCwxx(cwxx[0], cwxx[1], cwxx[2]);
			//������λ��Ϣ
			model.setTzhlddm(cwxxMap.get("lddm"));
			model.setTzhldmc(cwxxMap.get("ldmc"));
			model.setTzhqsh(cwxxMap.get("qsh"));
			model.setTzhcwh(cwxxMap.get("cwh"));
		}
		
		boolean result = super.runInsert(model);
		if(aType.equals("submit")){
			if(result){
				result = shlc.runSubmit(guid, sf.getSplcid(),model.getXh(),"ydshbase.do","ydsqbase.do");
//				if(result){
//					//���ô�����Ϣ
//					BaseDbcz dbcz=new BaseDbcz();
//					dbcz.setShPath("ydshbase.do");
//					dbcz.setSqPath("ydsqbase.do");
//					dbcz.setGnmkMc("�����춯���");
//					dbcz.setXmmc("�������");
//					dbcz.sqPush(model.getSsydsqid(), model.getXh(), sf.getSplcid());
//				}
			}
		}
		return String.valueOf(result);
	}
	/**
	 * 
	 * @����:��ס���ᱣ��
	 * @���ߣ���ˮ��[���ţ�1150]
	 * @���ڣ�2014-11-14 ����10:49:23
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param qf
	 * @return boolean ��������
	 * @throws Exception
	 */
	public String saveRzss(YdsqForm model,String aType) throws Exception {
		
		String guid = UniqID.getInstance().getUniqIDHash();
		model.setSsydsqid(guid);
		
		model.setSqsj(GetTime.getTimeByFormat("yyyy-mm-dd hh24:mi:ss"));
		ShlcForm sf = getShlcForm(model.getSsydlx());
		//ShlcForm sf = getShlcForm();
		if (null == sf) {
			return MessageKey.SYS_SELECT_SHLC_FAIL;
		}
		model.setSplcid(sf.getSplcid());
		
		// ��������������趨��ʼֵ
		if(sf.getSplcid()!=null&& !"".equals(sf.getSplcid())){
			if(aType.equals("submit")){
				model.setShzt(Constants.YW_SHZ);//�����
			}else{
				model.setShzt(Constants.YW_WTJ);//δ�ύ
			}
		}
		//����ס���ͺ�ʱ��ͬ�������ݿ��ֶ�
		model.setTstzyy(model.getRzssyy());
		model.setTstzsj(model.getRzsssj());
		
		//��ס��λ��Ϣ
		String ydhcwxx = model.getRzcwxx();
		
		if(ydhcwxx!=null && !"".equals(ydhcwxx)){
			String[] cwxx = ydhcwxx.split("_");
			// ��ѯ
			HashMap<String, String> cwxxMap = getCwxx(cwxx[0], cwxx[1], cwxx[2]);
			//����ǰ��λ��Ϣ
			model.setTzqlddm(cwxxMap.get("lddm"));
			model.setTzqldmc(cwxxMap.get("ldmc"));
			model.setTzqqsh(cwxxMap.get("qsh"));
			model.setTzqcwh(cwxxMap.get("cwh"));

			//����ũҵ��ѧ
			if("10466".equals(Base.xxdm)){
				
				String sXh = "";
				if("submit".equals(aType)){
					sXh = model.getXh();
				}
				
				boolean result = dao.checkExistForRzcwxx(cwxxMap.get("lddm"),cwxxMap.get("qsh"),cwxxMap.get("cwh"),sXh);
				if(result){
					return "exist_fail";
				}
			}
		}
		
		boolean result = super.runInsert(model);
		if(aType.equals("submit")){
			if(result){
				result = shlc.runSubmit(guid, sf.getSplcid(),model.getXh(),"ydshbase.do","ydsqbase.do");
//				if(result){
//					//���ô�����Ϣ
//					BaseDbcz dbcz=new BaseDbcz();
//					dbcz.setShPath("ydshbase.do");
//					dbcz.setSqPath("ydsqbase.do");
//					dbcz.setGnmkMc("�����춯���");
//					dbcz.setXmmc("������ס");
//					dbcz.sqPush(model.getSsydsqid(), model.getXh(), sf.getSplcid());
//				}
			}
		}
		return String.valueOf(result);
	}
	/**
	 * 
	 * @����:��ȡ��ǰʹ�õ��������
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-9-25 ����12:00:44
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * @throws Exception
	 *             ShlcForm ��������
	 * @throws
	 */
	public ShlcForm getShlcForm(String ssydlx) throws Exception {
		ShlcService ss = new ShlcService();
		return ss.getNowShlc(ssydlx);
	}
	public ShlcForm getShlcForm() throws Exception {
		ShlcService ss = new ShlcService();
		return ss.getNowShlc();
	}
	/**
	 * @����:ɾ��
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-9-13 ����02:31:26
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ids
	 * @return String[] �������� String����[0]Ϊ�ɹ�ɾ������ [1]Ϊ����ɾ����ʾ��Ϣ
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws Exception
	 */
	public String[] delete(String[] ids) throws Exception {
		return delete(ids, false);
	}
	
	public boolean cancleRecord(String ywid,String lcid) throws Exception{
		return shlc.firstStepCancle(ywid,lcid);
	}

	/**
	 * 
	 * @����:ɾ��
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-9-16 ����07:40:16
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ids
	 *            ɾ��id����
	 * @param isQjjg
	 *            �Ƿ��ǽ�������
	 * @return String[] ��������
	 * @throws Exception
	 */
	public String[] delete(String[] ids, boolean isQjjg) throws Exception {
		List<String> delId = new ArrayList<String>();// ��ɾ����id����

		StringBuffer noDel = new StringBuffer();
		boolean isHaveNoId = false;
		if (null == ids || ids.length <= 0) {
			return null;
		}
		for (String str : ids) {
			if (isCanDel(str) || isQjjg) {
				delId.add(str);// ��¼ɾ��id
			} else {
				HashMap<String, String> hm = dao.getYdsq(str);
				noDel.append(hm.get("xymc"));
				noDel.append("&nbsp;");
				noDel.append(hm.get("xm"));
				noDel.append("&nbsp;");
				noDel.append(hm.get("ydlxmc"));
				noDel.append("</br>");
				isHaveNoId = true;
			}
		}
		int i = delId.size() > 0 ? runDelete(delId.toArray(new String[] {}))
				: 0;

		if(delId.size()>0){
			
			//�h��������������
			for(String id:delId){
				shlc.deleteShjl(id);
			}
			//�h��������Ϣ
			BaseDbcz dbcz=new BaseDbcz();
			dbcz.setGnmkMc("�����춯���");
			dbcz.remove(delId.toArray(new String[] {}));
		}
		String str = noDel.toString();
		// ȥ�������ය��
		str = isHaveNoId ? str : _BCZSCID;
		return new String[] { String.valueOf(i), str };
	}

	/**
	 * 
	 * @����:�Ƿ����ɾ��
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-9-25 ����03:09:07
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param id
	 * @return boolean ��������
	 * @throws Exception
	 */
	public boolean isCanDel(String id) throws Exception {
		YdsqForm yf = getModel(id);
		// ��Ϊ���״̬������ɾ��
		if (null != yf && !Constants.YW_WTJ.equals(yf.getShzt()) && !Constants.YW_YTH.equals(yf.getShzt())  ) {
			return false;
		}
		return true;
	}
	/**
	 * 
	 * @����:����ѧ�Ż�ȡ���˴�λ��Ϣ
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-9-25 ����05:29:07
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * HashMap<String,String> �������� 
	 */
	public HashMap<String, String> getCwxxForXh(String xh) {
		return dao.getCwxxForXh(xh);
	}


	/**
	 * 
	 * @����:����ѧ�Ż�ȡ���˴�λ��Ϣ
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-9-25 ����05:29:07
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * HashMap<String,String> �������� 
	 */
	public HashMap<String, String> getCwxx(String lddm,String qsh,String cwh) {
		return dao.getCwxx(lddm,qsh,cwh);
	}
	
	/** 
	 * ��ѯ������
	 */
	public HashMap<String, String> getCwxxYdjg(String xh, String ssydsqid) {
		return dao.getCwxxYdjg(xh, ssydsqid);
	}
	
	public boolean updateModel(YdsqForm model) throws Exception {
		return super.runUpdate(model);
	}
	
	/**
	 * 
	 * @����:��������޸�
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-9-10 ����04:53:59
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception boolean ��������
	 */
	public String update(YdsqForm model,String aType) throws Exception {
		
		HashMap<String, String> cwxxForXh = getCwxxForXh(model.getXh());
		//����ǰ��λ��Ϣ
		model.setTzqlddm(cwxxForXh.get("lddm"));
		model.setTzqldmc(cwxxForXh.get("ldmc"));
		model.setTzqqsh(cwxxForXh.get("qsh"));
		model.setTzqcwh(cwxxForXh.get("cwh"));
		
		model.setSqsj(GetTime.getTimeByFormat("yyyy-mm-dd hh24:mi:ss"));		
		
		String lcid = "";
		YdsqForm myModel = getModel(model.getSsydsqid());
		
		// ���˻ص�״̬,ȡ�ϵ������
		if(Constants.YW_YTH.equals(myModel.getShzt())){
			lcid = myModel.getSplcid();				
		}else{
			//ȡ���µ��������
			ShlcForm sf = getShlcForm(model.getSsydlx());
			//ShlcForm sf = getShlcForm();
			if (null == sf) {
				lcid = "";
			}else{
				lcid = sf.getSplcid();
			}
		}

		// ����IDΪ������ʾ
		if (StringUtils.isNull(lcid)) {
			return MessageKey.SYS_SELECT_SHLC_FAIL;
		}
		model.setSplcid(lcid);
		
		if(aType.equals("submit")){
			model.setShzt(Constants.YW_SHZ);//�����
			
		}else if(Constants.YW_YTH.equals(myModel.getShzt()) && aType.equals("save")){
			// ���˻�
			model.setShzt(Constants.YW_YTH);
		}else{
			model.setShzt(Constants.YW_WTJ);//δ�ύ
		}
		
//		// ��������������趨��ʼֵ
//		if(StringUtils.isNotNull(model.getSplcid())){
//			if(aType.equals("submit")){
//				ShlcForm sf = getShlcForm();
//				if (null == sf) {
//					return MessageKey.SYS_SELECT_SHLC_FAIL;
//				}
//				model.setSplcid(sf.getSplcid());
//				model.setShzt(Constants.YW_SHZ);//�����
//			}else{
//				model.setShzt(Constants.YW_WTJ);//δ�ύ
//			}
//		}
		boolean result = true;
		if(aType.equals("submit")){
			result = shlc.runSubmit(model.getSsydsqid(), model.getSplcid(),model.getXh(),"ydshbase.do","ydsqbase.do");
		}
		//���������������õ�������
		if(model.getSsydlx().equals(_YDLX_SSTZ)){
			return String.valueOf(updateTzss(model));
		}else if(model.getSsydlx().equals(_YDLX_RZ)){//��ס����
			return updateRzss(model);
		}
		result = super.runUpdate(model);
		return String.valueOf(result);
	}
	public boolean updateTzss(YdsqForm model) throws Exception {
		//�ѵ������ͺ�ʱ��ͬ�������ݿ��ֶ�
		model.setTstzyy(model.getTzssyy());
		model.setTstzsj(model.getTzsssj());

		//������λ��Ϣ
		String ydhcwxx = model.getCwxx();
		
		if(ydhcwxx!=null && !"".equals(ydhcwxx)){
			String[] cwxx = ydhcwxx.split("_");
			
			// ��ѯ
			HashMap<String, String> cwxxMap = getCwxx(cwxx[0], cwxx[1], cwxx[2]);
			
			//������λ��Ϣ
			model.setTzhlddm(cwxxMap.get("lddm"));
			model.setTzhldmc(cwxxMap.get("ldmc"));
			model.setTzhqsh(cwxxMap.get("qsh"));
			model.setTzhcwh(cwxxMap.get("cwh"));
		}
		
		return super.runUpdate(model);
	}
	public String updateRzss(YdsqForm model) throws Exception {
		//�ѵ������ͺ�ʱ��ͬ�������ݿ��ֶ�
		model.setTstzyy(model.getRzssyy());
		model.setTstzsj(model.getRzsssj());
		
		//������λ��Ϣ
		String ydhcwxx = model.getRzcwxx();
		
		if(ydhcwxx!=null && !"".equals(ydhcwxx)){
			String[] cwxx = ydhcwxx.split("_");
			
			// ��ѯ
			HashMap<String, String> cwxxMap = getCwxx(cwxx[0], cwxx[1], cwxx[2]);
			
			//������λ��Ϣ
			model.setTzqlddm(cwxxMap.get("lddm"));
			model.setTzqldmc(cwxxMap.get("ldmc"));
			model.setTzqqsh(cwxxMap.get("qsh"));
			model.setTzqcwh(cwxxMap.get("cwh"));
			
			//����ũҵ��ѧ
			if("10466".equals(Base.xxdm)){
				boolean result = dao.checkExistForRzcwxx(cwxxMap.get("lddm"),cwxxMap.get("qsh"),cwxxMap.get("cwh"),model.getXh());
				if(result){
					return "exist_fail";
				}
			}
		}
		
		return String.valueOf(super.runUpdate(model));
	}
	/**
	 * 
	 * @����:
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-9-10 ����03:49:26
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param list
	 * @return List<HashMap<String,String>> ��������
	 */
	public List<HashMap<String, String>> getShxxAndXjxx(
			List<HashMap<String, String>> list) {
		return list;
	}

	/**
	 * ��ѯѧ��������Ϣ
	 * 
	 * @param String
	 *            xh
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> selectStuinfo(String xh) {
		return xxdao.selectStuDetail(xh);
	}

	/** 
	 * @����:��λ�б�������
	 * @���ߣ�qilm
	 * @���ڣ�2013-10-9 ����02:14:55
	 * @param myForm
	 * @param user
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getCwxxList(YdsqForm myForm, User user) throws Exception{
		return dao.getCwxxList(myForm,user);
	}
	
	/** 
	 * @����:��λ�б���ס��
	 * @���ߣ���ˮ��[���ţ�1150]
	 * @���ڣ�2014-11-14 ����10:49:23
	 * @param myForm
	 * @param user
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getRzcwxxList(YdsqForm myForm, User user) throws Exception{
		return dao.getRzcwxxList(myForm,user);
	}

	/** 
	 * @����:�Ƿ�������
	 * @���ߣ�qilm
	 * @���ڣ�2013-10-22 ����02:08:47
	 * @param xh
	 * @return
	 * boolean ����true:��������δ������˵� false:û��
	 * @throws 
	 */
	public boolean getSfysq(String xh) {
		
		return dao.getSfysq(xh);
	}
	
	
	public boolean getInShz(String qsmc) {
		
		return dao.getInShz(qsmc);
	}
	
	public boolean getSfjl(String qsmc) {
		
		return dao.getSfjl(qsmc);
	}
	
	/**
	 * 
	 * @����:��ȡ�����춯������Ϣ
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2015-5-5 ����02:13:36
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ssydsqid
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String,String> getYdsqxx(String ssydsqid){
		return dao.getYdsqxx(ssydsqid);
		
	}
	
	/**
	 * ��ȡ
	 * @param paraList
	 * @return
	 */
	public List<HashMap<String, Object>> setData(List<HashMap<String, String>> list){
		List<HashMap<String, Object>> totalList = new ArrayList<HashMap<String,Object>>();
		List<HashMap<String, Object>> secondList = new ArrayList<HashMap<String,Object>>();
		List<HashMap<String, String>> lastList = new ArrayList<HashMap<String,String>>();
		
		HashMap<String, Object> firstMap = new HashMap<String, Object>(); //��һ��ѡ��
		HashMap<String, Object> secondMap = new HashMap<String, Object>(); //�ڶ���ѡ��
		
		HashMap firstM = new HashMap();
		HashMap secondM = new HashMap();
		
		for (HashMap<String, String> hashMap : list) {
			secondM.put(hashMap.get("qsh"), hashMap.get("qsh"));
			firstM.put(hashMap.get("lddm"), hashMap.get("lddm"));
			
			//�ж����һ���Ƿ���Ҫ��ʼ��
			if(secondM.size()!=1){
				secondMap = new HashMap<String, Object>();
				lastList = new ArrayList<HashMap<String,String>>();
				secondM.clear();
			}
			
			//�жϵڶ����Ƿ���Ҫ��ʼ��
			if(firstM.size()!=1){
				firstMap = new HashMap<String, Object>();
				secondList = new ArrayList<HashMap<String,Object>>();
				firstM.clear();
			}
			
			//���һ�� ѡ��
			HashMap<String, String> lastMap = new HashMap<String, String>();
			lastMap.put("value", hashMap.get("cwh"));
			String qshText = StringUtils.isNotNull(hashMap.get("xm")) ? hashMap.get("cwh")+"�Ŵ�("+hashMap.get("xm")+")" : hashMap.get("cwh")+"�Ŵ�";
			lastMap.put("text", qshText);
			lastMap.put("xh", hashMap.get("xh"));
			lastList.add(lastMap);
			
			//�ڶ���
			secondMap.put("value", hashMap.get("qsh"));	
			secondMap.put("text", hashMap.get("qsh")+"��");
			secondMap.put("children", lastList);
			
			if(!secondList.contains(secondMap)){
				secondList.add(secondMap);
			}
			
			//��һ��
			firstMap.put("value", hashMap.get("lddm"));	
			firstMap.put("text", hashMap.get("ldmc"));
			firstMap.put("children", secondList);
			
			if(!totalList.contains(firstMap)){
				totalList.add(firstMap);
			}
			
		}
		
		return totalList;
	}
	
	/**
	 * ��ס�춯����������ϢList
	 * @param t
	 * @param user
	 * @return
	 */
	public List<HashMap<String, String>> getRzYdLxQsxxList(YdsqForm t, User user){
		return dao.getRzYdLxQsxxList(t, user);
	}
	
	/**
	 * @����: ���ᴲλ����
	 * @���ߣ�qilm
	 * @���ڣ�2013-10-9 ����02:14:55
	 * @param myForm
	 * @param user
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws  Exception 
	 */
	public List<HashMap<String, String>> getTCwxxList(YdsqForm t, User user) throws Exception {
		return dao.getTCwxxList(t, user);
	}
	
}
