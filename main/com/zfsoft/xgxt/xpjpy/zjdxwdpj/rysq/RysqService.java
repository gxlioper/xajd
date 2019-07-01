/**
 * @����: ѧ����Ʒ(1)��
 * @���ڣ� 2018-7-3 ����05:01:55 
 */  
package com.zfsoft.xgxt.xpjpy.zjdxwdpj.rysq;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �������Ź���ģ��
 * @�๦������: �ҵ�����_��������
 * @���ߣ� Meng.Wei[����:1186]
 * @ʱ�䣺 2018-7-3 ����05:01:26 
 * @�汾�� V5.18.26
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class RysqService extends SuperServiceImpl<RysqForm,RysqDao>{
	private RysqDao dao = new RysqDao();
	private ShlcInterface shlc = new CommShlcImpl();
	
	public RysqService(){
		super.setDao(dao);
	}
	
	@Override
	public RysqForm getModel(RysqForm t)throws Exception {
		return super.getModel(t);
	}
	
	/**
	 * @����: ȡѧ�����һ��������Ϣ����Ҫ��ȡ
	 *������ˮƽ������绰��������Ṥ��ְ�񡢸���ѧϰ�������μӿ�����������轱��λ����ʶ��
	 * @���ߣ� MengWei[���ţ�1186]
	 * @���ڣ� 2018-7-4 ����09:44:58
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String,String> getLatestSqxx(String xh) throws Exception{
		return dao.getLatestSqxx(xh);
	}
	
	/**
	 * @����: ����������û��������Ľ���
	 * @���ߣ� MengWei[���ţ�1186]
	 * @���ڣ� 2018-7-4 ����02:19:41
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * @throws Exception
	 * Map<String,Object> �������� 
	 * @throws
	 */
	public Map<String, Object> getRysqInfoList(String xh) throws Exception {
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
	 * @����: ��ѯѧ��ѧ�ź�ѧԺ����
	 * @���ߣ� MengWei[���ţ�1186]
	 * @���ڣ� 2018-7-4 ����04:28:28
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return
	 * @throws Exception
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String,String> getPjxmXsxxMap(RysqForm t) throws Exception{
		return dao.getPjxmXsxxMap(t);
	}
	
	/**
	 * @����: ����������������
	 * @���ߣ� MengWei[���ţ�1186]
	 * @���ڣ� 2018-7-4 ����04:38:18
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xmdm
	 * @param t
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean saveRysq(String[] xmdm, RysqForm t) throws Exception {
		if (xmdm == null || xmdm.length == 0) {
			return false;
		}
		for (String pjxm : xmdm) {
			RysqForm model = new RysqForm();
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
	 * @���ߣ� MengWei[���ţ�1186]
	 * @���ڣ� 2018-7-4 ����04:41:32
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param userName
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean saveJxsq(RysqForm model, String userName) throws Exception {
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
	 * @����: �������볷��
	 * @���ߣ� MengWei[���ţ�1186]
	 * @���ڣ� 2018-7-10 ����10:42:21
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
	 * @����: ��ѯ�����������������ƺ���Ŀ
	 * @���ߣ� MengWei[���ţ�1186]
	 * @���ڣ� 2018-8-8 ����08:05:02
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * List<String> �������� 
	 * @throws
	 */
	public List<String> getRychList(RysqForm model) throws Exception{
		return dao.getRychList(model);
	}
}
