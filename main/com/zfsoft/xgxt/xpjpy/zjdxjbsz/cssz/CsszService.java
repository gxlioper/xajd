/**
 * @����:ѧ����Ʒ1��
 * @���ڣ�2017-3-21 ����09:19:17 
 */  
package com.zfsoft.xgxt.xpjpy.zjdxjbsz.cssz;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import xgxt.action.Base;
import xgxt.form.User;
import xgxt.utils.GetTime;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.xpjpy.zjdxjbsz.cssz.zcxmdj.ZcxmdjDao;
import com.zfsoft.xgxt.xpjpy.zjdxjbsz.cssz.zcxmfz.ZcxmfzDao;


/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �������Ź���ģ��
 * @�๦������: ����������_��������_��������
 * @���ߣ� Meng.Wei[����:1186]
 * @ʱ�䣺 2017-3-21 ����09:19:17 
 * @�汾�� V5.18.26
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class CsszService extends SuperServiceImpl<CsszForm, CsszDao>{
	private static final String SplitChar = "#";
	public static final String OPEN = "1"; //����
	public static final String CLOSE = "0"; //�ر�
	private CsszDao dao = new CsszDao();

	public CsszService() {
		super.setDao(dao);
	}
	
	/**
	 * @����: ����ѧ����������ʾ
	 * @���ߣ�Meng.Wei[���ţ�1186]
	 * @���ڣ�2017-12-5 ����11:21:41
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getPjzqList(){
		//���������б�
		List<HashMap<String,String>> pjzqList = new ArrayList<HashMap<String,String>>();
		String xn = null;
		/*��2017-2018ѧ�꿪ʼ��ʽʹ��*/
		for (int i = 0; i < 6; i++){
			int xns = 2017;
			xn = String.valueOf(xns - 3 + i ) + "-" + String.valueOf(xns - 2 + i);
			HashMap<String,String> zqMap = new HashMap<String, String>();
			zqMap.put("pjzq", xn);
			pjzqList.add(zqMap);
		}
		return pjzqList;
	}
	
	public boolean updateCssz(String zdKey,String zdValue) throws Exception{
		if ("xn".equals(zdKey)){
			String[] zqArray = zdValue.split(SplitChar);
			return dao.updatePjzq(zqArray);
		} else {
			return dao.updateCssz(zdKey, zdValue);
		}
		
	}
	
	public CsszForm getCsszModel() throws Exception{
		CsszForm model = dao.getModel();
		if (model == null){
			//��ʼ����������
			model = new CsszForm();
			model.setPjkg(CLOSE);
			model.setXn(Base.currXn);
			boolean result = dao.runInsert(model);
		} else {
			String xn = model.getXn();
			//ƴװ��������
			if (!StringUtil.isNull(xn)){
				model.setXn(xn);
			}
		}
		boolean isOpen = isOpen(model);
		model.setKgzt(String.valueOf(isOpen));
		return model;
	}
	
	public boolean isOpen(CsszForm model) throws Exception{
		if (model == null){
			model = dao.getModel();
		}
		//�޲����������� ���߿���״̬Ϊ�ر� ��Ϊ�ر�״̬
		if (model == null || CLOSE.equals(model.getPjkg())){
			return false;
		}
		//��ʽ����ʼʱ�䡢����ʱ��
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date startTime = null;
		Date endTime = null;
		String nowTime = GetTime.getTimeByFormat("yyyy-MM-dd hh24:mi:ss");
		Date now = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(nowTime);
		
		if (StringUtil.isNull(model.getKssj()) && StringUtil.isNull(model.getJssj())){
			return OPEN.equals(model.getPjkg());
		} else if (!StringUtil.isNull(model.getKssj()) && !StringUtil.isNull(model.getJssj())){
			startTime=sdf.parse(model.getKssj()+":00");
			endTime=sdf.parse(model.getJssj()+":59");
			//��ǰʱ�����ڽ���ʱ�� && ���ڿ�ʼʱ��  && ����״̬Ϊ����
			return now.before(endTime) && now.after(startTime) && OPEN.equals(model.getPjkg());
		} else if (!StringUtil.isNull(model.getKssj())){
			startTime=sdf.parse(model.getKssj()+":00");
			return now.after(startTime);
		} else if (!StringUtil.isNull(model.getJssj())){
			endTime=sdf.parse(model.getJssj()+":59");
			return now.before(endTime);
		}
		return false;
	}
	
	/*
	      ���Ӳ���__����һ����ĿList
	 */
	public List<HashMap<String, String>> getYjxmlist(String xn){
		ZcxmfzDao zcxmfzDao = new ZcxmfzDao();
		return zcxmfzDao.getYjxmlist(xn);
	}
	
	/** 
	 * @����: �����Ŀ���͵ĵȼ���ť__�������ݱ���
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ�2017-3-28 ����02:24:19
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param jxxxList
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean saveAddXmlxDj(CsszForm model, List<CsszForm> xmjxList) throws Exception{
		ZcxmdjDao zcxmdjdao = new ZcxmdjDao();
		ZcxmfzDao zcxmfzDao = new ZcxmfzDao();
		boolean result = true;
		//��һ�����Ȳ����۲���Ŀ��������Ŀ����Ŀ���� ��xg_zjdx_pjpy_zcxmb��
		String xmdm = null;
		xmdm = UniqID.getInstance().getUniqIDHash();
		model.setXmdm(xmdm);
		model.setXn(dao.getModel().getXn());
		result = zcxmfzDao.runInsert(model);
		//�ڶ������ж�����Ϊ�ȼ����ֵ
		if(null==xmjxList){
			return true;
		}
		List<String[]> jxxxList = new ArrayList<String[]>();
		String[] jxxx = null;
		for (CsszForm csszForm : xmjxList) {
			jxxx = new String[3];
			jxxx[0] = model.getXmdm();
			jxxx[1] = csszForm.getMc();
			jxxx[2] = csszForm.getPx();
			jxxxList.add(jxxx);
		}
		return zcxmdjdao.zcxmDjPlbc(jxxxList);
	}
	/*
	 * ɾ���۲���Ŀ������
	 */
	public int numDj(String[] values) throws Exception {
		return dao.numDj(values);
	}
	
	/*
	 * ɾ���۲���Ŀѡ���
	 */
	public int numFz(String[] values) throws Exception {
		return dao.numFz(values);
	}
	
	/*
	 * ���ӱ�����֤�۲���Ŀ������ѧ�ꡢ��Ŀ���Ʋ����ظ�
	 */
	public boolean isExistByZcxm (CsszForm model) throws Exception {
		String num = dao.isExistByZcxm(model);
		return Integer.valueOf(num) > 0;
	}
	
	/*
	 * ���ӡ��޸ı�����֤checkBox��ֵ�Ƿ����ظ�
	 */
	public boolean checkLxcf(List<CsszForm> xmjxList) {
		if(null==xmjxList){
			return false;
		}
		HashSet hashSet = new HashSet();
		for (CsszForm csszForm : xmjxList) {
			hashSet.add(csszForm.getMc());
		}
		if(!(hashSet.size()==xmjxList.size())){
			return true;
		}else{
			return false;
		}
	}
	
	/*
	 * �޸ķ����۲���Ŀ����ȼ�checkBox����
	 */
	public List<HashMap<String, String>> getZcxmDjList(String xmdm) {
		return dao.getZcxmDjList(xmdm);
	}
	
	/*
	 * �޸ķ���ҳ��__�����۲���Ŀ�����ݣ����ص�model
	 */
	public HashMap<String, String> getZcxmDate(String xmdm) {
		return dao.getZcxmDate(xmdm);
	}
	
	/*
	 * �޸ı���ʱ�����ݴ�������ɾ���������
	 */
	public boolean saveUpdateXmlxDj(CsszForm model, List<CsszForm> xmjxList) throws Exception{
		ZcxmdjDao zcxmdjdao = new ZcxmdjDao();
		ZcxmfzDao zcxmfzDao = new ZcxmfzDao();
		boolean result = true;
		
		/*��1��������ɾ���۲���Ŀ�������*/
		result = zcxmfzDao.delZcxmfz(model.getXmdm());
		
		/*��2���������۲���Ŀ����۲���Ŀ��������Ŀ����Ŀ�����ֶ�*/
			/*1���������ȼ�����С��ֵ������ֵ��������Ϊ�ղ������*/
			/*2����������ֵ����С��ֵ������ֵ�������������*/
		result = zcxmfzDao.runInsert(model);
		
		/*��3������ɾ���۲���Ŀѡ��������*/
		result = zcxmdjdao.delZcxmdj(model.getXmdm());
		
		/*��4��������ȼ�����������۲���Ŀ�ȼ���checkBox��ֵ*/
		/*�ж�checkBox��ֵ�Ƿ�Ϊ��*/
		if(null==xmjxList){
			return true;
		}
		/*����checkBox��ֵ*/
		List<String[]> jxxxList = new ArrayList<String[]>();
		String[] jxxx = null;
		for (CsszForm csszForm : xmjxList) {
			jxxx = new String[3];
			jxxx[0] = model.getXmdm();
			jxxx[1] = csszForm.getMc();
			jxxx[2] = csszForm.getPx();
			jxxxList.add(jxxx);
		}
		return zcxmdjdao.zcxmDjPlbc(jxxxList);
	}
	
	/*
	 * ѡ������__�жϵ�ǰ�����Ƿ����۲��¼
	 */
	public boolean getSfcz(){
		return dao.getSfcz();
	}
	
	/*������Ա��ִ�г�ʼ������*/
	public void init() throws Exception {
		if(dao.initDel()){
			dao.init();
		}
	}
	
	/**
	 * @����: ��ʼ���۲���Ŀ�ṹ
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ�2017-5-10 ����10:56:35
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param pjzq
	 * @throws Exception
	 * void �������� 
	 * @throws
	 */
	public void initZcxmList(String pjzq) throws Exception{
		String xn = null;
		
		if (!StringUtil.isNull(pjzq)){
			String[] zqArray = pjzq.split("#");
			xn = zqArray[0];
		}
		
		/*�жϵ�ǰ�����Ƿ����۲�ṹ������� return;*/
		boolean isHave = !dao.getZcxmList(xn).isEmpty();
		if (isHave){
			return ;
		}
		
		/*�ж�ϵͳ���Ƿ��г�ʼ���۲���Ŀ*/
		boolean isHaveZcxm = dao.getZcxmCount() > 0;
		/*����У����������ڻ����*/
		if (isHaveZcxm){
			/*����������ڵ��۲�ṹ*/
			copyZcxm(xn);
		}
	}
	
	/**
	 * @����: �����۲���Ŀ
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ�2017-5-11 ����11:57:30
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xn
	 * @throws Exception
	 * void �������� 
	 * @throws
	 */
	private void copyZcxm(String xn) throws Exception{
		/*������ڵ��۲���Ŀ�ṹ*/
		List<HashMap<String,String>> zjzqZcxm = null;
		zjzqZcxm = dao.getZjzqZcxm();
		Map<String,List<HashMap<String,String>>>  resultMap = new HashMap<String, List<HashMap<String,String>>>();
		/*��������ڵ���Ŀ�ṹ ��װ�� key; ������Ŀ���� value������Ŀ��*/
		for (HashMap<String,String> map : zjzqZcxm){
			String key = map.get("fjdm");
			List<HashMap<String,String>> list = resultMap.get(key);
			if (list == null){
				list = new ArrayList<HashMap<String,String>>();
			}
			list.add(map);
			resultMap.put(key, list);
		}
		/*�����µ���Ŀ�ṹ*/
		UniqID uID = UniqID.getInstance();
		String zczfXmdm = uID.getUniqIDHash();
		HashMap<String,String> yjxm = resultMap.get("top").get(0);
		List<String[]> params = new ArrayList<String[]>();
		String[] zczf = new String[]{zczfXmdm,xn,yjxm.get("xmmc"),"top",yjxm.get("xmlx"),yjxm.get("px"),yjxm.get("zxfz"),yjxm.get("zdfz")};
		params.add(zczf);
		List<HashMap<String,String>> ejxmList = resultMap.get(yjxm.get("xmdm"));
		for (HashMap<String,String> ejxmMap : ejxmList){
			String ejxmdm = uID.getUniqIDHash();
			/*��Ŀ����*/
			String[] ejxm = new String[]{ejxmdm,
										 xn,
										 ejxmMap.get("xmmc"),
										 zczfXmdm,ejxmMap.get("xmlx"),
										 ejxmMap.get("px"),
										 ejxmMap.get("zxfz"),
										 ejxmMap.get("zdfz")
									};
			params.add(ejxm);
		}
		dao.initZcxmList(params);
		/*��ȡ����۲�����*/
		String maxZczq = dao.maxZczq();
		/*�����۲���Ŀѡ����ȼ��� */
		dao.initZcxmxxList(xn,maxZczq);
	}
	
	/**
	 * @����: ��ʼ��������
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ�2017-5-11 ����03:36:09
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param user
	 * @throws Exception
	 * void �������� 
	 * @throws
	 */
	public void initCpxz(User user) throws Exception{
		/*��ȡ����������Ϣ*/
		CsszDao cszzDao = new CsszDao();
		CsszForm csszForm = cszzDao.getModel();
		/*��ȡ�������ñ��еĵ�ǰ���ڣ�ѧ�꣩*/
		String xn = csszForm.getXn();
		/*��ǰ�����Ƿ��Ѿ��з����ύ��¼�����ݣ�����У�return, û�У���ʼ��*/
		boolean isHaveFstjjl = dao.isHaveFstjjl(xn);
		if (isHaveFstjjl){
			return ;
		}
		/*��ʼ�������ύ��¼��,����յ�ǰ���ڵ��������ݣ���ͳһ����*/
		/*������ݣ�ѧԺ�û�ֻ���� �û�����ѧԺ����*/
		dao.delFsTjjl(xn,user);
		/*��������*/
		dao.insertFstjjl(xn,user);
	}
	
	
	/**
	 * @����: ��ò�������
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ�2017-6-5 ����11:21:51
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param csdm
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getCsz(String csdm){
		return dao.getCsz(csdm);
	}
	
	/**
	 * @����: ����۲���Ŀ�Ƿ�ʹ��
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2017-7-24 ����11:19:22
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public String checkZcxmMade (String xn) throws Exception {
		return dao.checkZcxmMade(xn);
	}
	
	/**
	 * @����: ��ȡ����������Ϣ�����ݣ���ǰ���õķ����е�����
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2017-12-6 ����02:39:39
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * @throws Exception
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String,String> getCszzInfo() throws Exception{
		return dao.getCszzInfo();
	}
}
