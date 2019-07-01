/**
 * @����:ѧ����Ʒ(1)��
 * @���ڣ�2017-5-12 ����04:59:56 
 */  
package com.zfsoft.xgxt.xpjpy.zjdxwdpj.xmsq;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.check.CheckCondition;
import com.zfsoft.xgxt.base.check.impl.CheckStudentCondition;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.xpjpy.zjdxjbsz.cssz.CsszDao;
import com.zfsoft.xgxt.xpjpy.zjdxjbsz.cssz.CsszForm;
import com.zfsoft.xgxt.xpjpy.zjdxjbsz.xmsz.tjsz.TjszService;
import com.zfsoft.xgxt.xpjpy.zjdxjbsz.xmsz.xmwh.XmwhDao;
import com.zfsoft.xgxt.xpjpy.zjdxjbsz.xmsz.xmwh.XmwhForm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xgxt.utils.String.StringUtils;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �������Ź���ģ��
 * @�๦������: �ҵ�����_��������
 * @���ߣ� Meng.Wei[����:1186]
 * @ʱ�䣺 2017-5-12 ����05:00:34 
 * @�汾�� V5.18.26
 * @�޸ļ�¼: 2018-01-04
 */

public class XmsqService extends SuperServiceImpl<XmsqForm, XmsqDao>{
	private XmsqDao dao = new XmsqDao();
	private ShlcInterface shlc = new CommShlcImpl();
	
	public XmsqService() {
		super.setDao(dao);
	}
	
	@Override
	public XmsqForm getModel(XmsqForm t) throws Exception {
		// TODO �Զ����ɷ������
		return super.getModel(t);
	}
	
	/**
	 * @����: ��Ŀ������Ϣ�������롢�����룩
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ�2017-5-16 ����09:03:53
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * @throws Exception
	 * Map<String,Object> �������� 
	 * @throws
	 */
	public Map<String, Object> getXmsqInfoList(String xh) throws Exception {
		/*����������û��������Ľ���*/
		List<HashMap<String, String>> wsqList = dao.getKsqInfoList(xh);
		/*��ǰѧ�ꡢѧ��������Ľ���*/
		List<HashMap<String, String>> ysqList = dao.getYsqInfoList(xh);
		/*���ؽ��*/
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		/*��֤����*/
		TjszService tjszServcie = new TjszService();
		for (HashMap<String, String> pjxm : wsqList) {
			List<HashMap<String, String>> conditions = tjszServcie.getTjszGl(pjxm.get("xmdm"), xh);
			// У�����������ز�������������Ŀ���ơ�
			CheckCondition check = new CheckStudentCondition();
			// У����
			List<HashMap<String, String>> results = check.checkCondition(xh,conditions);
			resultMap.put(pjxm.get("xmdm"), results);
		}
		resultMap.put("wsqList", wsqList);
		resultMap.put("ysqList", ysqList);
		return resultMap;
	}
	
	/**
	 * @����: ������Ŀ���룬���ز��ɼ��������Ľ��
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ�2017-5-16 ����11:22:31
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xmdm
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getBjdxm(String xmdm) throws Exception{
		return dao.getBjdxm(xmdm);
	}
	
	/**
	 * @����: ������ȡ������Ϣ��ѧ����Ϣ
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ�2017-5-16 ����04:05:05
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return
	 * @throws Exception
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	/*��ѯѧ��ѧ�ź�ѧԺ����*/
	public HashMap<String,String> getPjxmXsxxMap(XmsqForm t) throws Exception{
		return dao.getPjxmXsxxMap(t);
	}
	/*��ѯ��ѧ�������ޣ����ű���÷���ȷ��*/
	public List<String> getXzjx() throws Exception{
		return dao.getXzjx();
	}
	/*����������*/
	public String getYsqXs(String xmxx) throws Exception{
		return dao.getYsqXs(xmxx);
	}
	/*��Ŀ�������ñ����������*/
	public String getPjxmRsxxsx(String xmxx) throws Exception{
		return dao.getPjxmRsxxsx(xmxx);
	}
	/*���������Ŀ��Ϣ*/
	public List<HashMap<String,String>> getPjxmXsxxList(String[] sqidArr) throws Exception{
		return dao.getPjxmXsxxList(sqidArr);
	}
	/*�ύ��Ŀ������Ϣ*/
	public List<HashMap<String,String>> getPjxmRsxx(String[] sqidArr) throws Exception{
		return dao.getPjxmRsxx(sqidArr);
	}
	
	/**
	 * @����: �ж��Ƿ񲻿ɼ��
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ�2017-5-16 ����05:37:00
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xmdm
	 * @param xn
	 * @param xh
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean checkIsNotbkjd(String xmdm,String xn,String xh){
		return dao.checkIsNotbkjd(xmdm, xn, xh);
	}
	
	/**
	 * @����: ��ȡ���ɼ����Ŀ����
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ�2017-5-16 ����05:38:08
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xmdm
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getbkjdMc(String xmdm){
		return dao.getbkjdMc(xmdm);
	}
	
	/**
	 * @����: �������潱������
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ�2017-5-16 ����06:06:14
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xmdm
	 * @param t
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean saveXmsq(String[] xmdm, XmsqForm t) throws Exception {
		if (xmdm == null || xmdm.length == 0) {
			return false;
		}
		for (String pjxm : xmdm) {
			XmsqForm model = new XmsqForm();
			model.setXh(t.getXh());
			model.setSqly(t.getSqly());
			model.setXmdm(pjxm);
			model.setType(t.getType());
			model.setId(t.getId());
			model.setFjxx(t.getFjxx());
			
			model.setWysp(t.getWysp());/*����ˮƽ*/
			model.setSsdh(t.getSsdh());/*����绰*/
			model.setGzzw(t.getGzzw());/*������Ṥ��ְ��*/
			model.setGrxxjl(t.getGrxxjl());	//����ѧϰ����
			model.setCjkyqk(t.getCjkyqk());/*�μӿ������*/
			model.setDwrs(t.getDwrs());/*���轱��λ����ʶ*/
			saveJxsq(model, t.getSqr());
		}
		return true;
	}
	
	/**
	 * @����: �������
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ�2017-5-19 ����08:43:56
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param userName
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean saveJxsq(XmsqForm model, String userName) throws Exception {
		String sqid = UniqID.getInstance().getUniqIDHash();
		/*��Ŀ��Ϣ*/
		XmwhDao xmmwDao = new XmwhDao();
		XmwhForm xmwhModel = xmmwDao.getModel(model.getXmdm());
		/*�������*/
		String splc = xmwhModel.getShlc();
		/*����������Ϣ*/
		CsszDao csszdao = new CsszDao();
		CsszForm csszModel = csszdao.getModel();
		
		model.setSqr(userName);
		model.setXn(csszModel.getXn());
		model.setId(sqid);
		model.setSplc(splc);
		/*��������������趨��ʼֵ*/
		if("submit".equals(model.getType())){
			/*��� �ύ���� ��ť���״̬Ϊ����С�5��*/
			model.setShzt(Constants.YW_SHZ);
		}else{
			/*��� ����ݸ� ��ť���״̬Ϊδ�ύ��0��*/
			model.setShzt(Constants.YW_WTJ);// δ�ύ
		}
		/*����������Ϣ*/
		boolean result = dao.runInsert(model);
		if (!"save".equals(model.getType())) {
			result = shlc.runSubmit(sqid, splc, model.getXh(),"xpjpy_wdpj_pjsh.do","xpjpy_wdpj_pjsq.do");
		}
		return result;
	}
	
	/**
	 * @����: ������Ϣ������ѯ
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ�2017-5-19 ����08:44:58
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param xn
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String> getPjInfo(String xh,String xn) {
		/*�ж�ѧ��Ϊ��*/
		if (StringUtil.isNull(xh)) {
			logger.error("xh is null !");
			throw new NullPointerException();
		}
		return dao.getPjInfo(xh, xn);
	}
	
	/**
	 * @����: ����ѧ�Ų�ѯѧ������������Ϣ
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ�2017-5-19 ����09:08:41
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param xn
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String> getCpbjListByXh(String xh,String xn){
		return dao.getCpbjListByXh(xh,xn);
	}
	
	/**
	 * @����: ��˼�¼
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ�2017-5-19 ����09:55:00
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param id
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getSpjlList(String id) {
		return dao.getSpjlList(id);
	}
	
	/**
	 * @����: ����
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ�2017-5-19 ����04:49:29
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ywid
	 * @param lcid
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean cancleRecord(String ywid, String lcid) throws Exception {
		return shlc.firstStepCancle(ywid, lcid);
	}
	
	/**
	 * @����: ���ָ����Ŀ�Ƿ���������˼�¼
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ�2017-6-1 ����04:42:09
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xmdm
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean isExistsFlowData(String xmdm) {
		return Integer.valueOf(dao.getFlowData(xmdm)) > 0;
	}

	/**
	 * @����: ȡѧ�����һ��������Ϣ����Ҫ��ȡ����ˮƽ������绰��������Ṥ��ְ�񡢸���ѧϰ�������μӿ�����������轱��λ����ʶ
	 * @���ߣ� MengWei[���ţ�1186]
	 * @���ڣ� 2018-7-12 ����07:44:31
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String,String> getLatestSqxx(String xh) {
		return dao.getLatestSqxx(xh);
	}

	/**
	 *  �����꼶��רҵ�����ѯ�꼶רҵ����.
	 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
	 * @date 2017-12-28 19:48
	 * @param nj
	 * @param zydm
	 * @return java.lang.String
	 * @throw
	 */
    public String getNjzyrs(String nj, String zydm) {
		return dao.getNjzyrs(nj,zydm);
    }
    
    /**
     * @����: ��༶����
     * @���ߣ� Meng.Wei[���ţ�1186]
     * @���ڣ� 2018-1-4 ����09:32:58
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     * @param bjdm
     * @param xn
     * @param xq
     * @return
     * String �������� 
     * @throws
     */
    public String getBjrs(String bjdm, String xn){
		return dao.getBjrs(bjdm, xn);
	}
    
    /**
     * @����: ȥ�����Ϣ
     * @���ߣ� Meng.Wei[���ţ�1186]
     * @���ڣ� 2018-1-4 ����09:49:04
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     * @param guid
     * @return
     * HashMap<String,String> �������� 
     * @throws
     */
    public HashMap<String, String> getSpxxInfo(String guid){
		if(StringUtils.isNull(guid)){
			return null;
		}
		return dao.getSpxxInfo(guid);
	}
}
