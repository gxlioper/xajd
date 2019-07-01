/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-8-5 ����11:41:11 
 */  
package com.zfsoft.xgxt.wjcf.cfjg;

import java.io.File;
import java.io.InputStream;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;
import xgxt.utils.date.DateUtils;

import com.ctc.wstx.util.DataUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.FreeMarkerUtil;
import com.zfsoft.xgxt.base.util.HtmlUtil;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;

import common.GlobalsVariable;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����[����:1104]
 * @ʱ�䣺 2014-8-5 ����11:41:11 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class CfjgService extends SuperServiceImpl<CfjgForm, CfjgDao>{
	
	@Override
	public List<HashMap<String, String>> getPageList(CfjgForm t, User user)
			throws Exception {
		return getCfjgList(super.getPageList(t, user));
	}
	public List<HashMap<String, String>> getCfjgList(
			List<HashMap<String, String>> list) {
		return list;
	}
	/**
	 * ��������
	 * @param form
	 * @return
	 * @throws Exception 
	 * @throws Exception
	 */
	public boolean saveCfsb(CfjgForm form) throws Exception{
		
		form.setCflbmc(dao.cflbmc(form.getCflbdm()));
		form.setCfyymc(dao.cfyymc(form.getCfyydm()));
		return dao.saveCfsj(form);
	}
	/**
	 * 
	 * @����:�Ϻ����ȴ���֪ͨ����ˮ�Ż�ȡ
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2014-8-26 ����10:13:29
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getLsh(CfjgForm model) {
		StringBuffer lshstr=new StringBuffer();
		List<HashMap<String, String>> lshList = dao.getLsh(model);
		if (lshList.size() > 0) {
			int lsh=0;
			lsh = Integer.parseInt(Base.isNull(lshList.get(0).get("cflsh"))?"0":lshList.get(0).get("cflsh"))+1;
			if(lsh>9&&lsh<100){
				lshstr.append("0").append(lsh);
			}
			else if(lsh>99){
				lshstr.append(lsh);
			}
			else{
				lshstr.append("00").append(lsh);
			}
		} else {
			lshstr.append("001");
		}
		return lshstr.toString();
	}
	
	public String getLsh2(CfjgForm model) {
		return dao.getLsh2(model);
	}
	
	/**
	 * �鿴������Ϣ
	 * @param cfid
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> cfsjwhCk(String cfid) throws Exception{
		CfjgDao dao = new CfjgDao();
		return dao.cfsjwhCk(cfid);
	}
	/**
	 * 
	 * @����:��ѯ�Ƿ���Ա��ְ��
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2014-8-1 ����11:09:51
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param cfid
	 * @return
	 * @throws Exception
	 * String �������� 
	 * @throws
	 */
	public HashMap<String,String> getZwAndZzmm(String xh) throws Exception{
		CfjgDao dao = new CfjgDao();
		return dao.getZwAndZzmm(xh);
	}
	
	/**
	 * ɾ��������Ϣ
	 * @param cfid
	 * @return
	 * @throws Exception
	 */
	public boolean cfsjwhSc(String[] cfid) throws Exception {
		List<String[]> params = new ArrayList<String[]>();		
		for(String str : cfid){
			if (StringUtils.isNull(str)) continue;
			params.add(new String[]{str});
		}
		CfjgDao dao = new CfjgDao();
		boolean f =dao.cfsjwhSc(params); 
		f = dao.cfSbSsJcSc(cfid);
		return f;
	}
	
	/**
	 * �޸Ĵ�����Ϣ
	 * @param cfid
	 * @return
	 * @throws Exception
	 */
	public boolean cfsjwhXg(CfjgForm form) throws Exception {
		CfjgDao dao = new CfjgDao();
		form.setCflbmc(dao.cflbmc(form.getCflbdm()));
	//	form.setCfyymc(dao.cfyymc(form.getCfyydm()));
		return dao.cfsjwhXg(form);
	}
	
	/**
	 * ���洦��������Ϣ
	 * @param form
	 * @return
	 * @throws Exception
	 */
	public boolean cfssjgBc(CfjgForm form) throws Exception {
		CfjgDao dao = new CfjgDao();
		return dao.cfssjgBc(form);
	}
	
	/**
	 * ���洦�ֽ����Ϣ
	 * @param form
	 * @return
	 * @throws Exception
	 */
	public boolean cfjcjgBc(CfjgForm form) throws Exception {
		CfjgDao dao = new CfjgDao();
		return dao.cfjcjgBc(form);
	}
	
	/**
	 * ���洦����ֹ��Ϣ
	 * @param form
	 * @return
	 * @throws Exception
	 */
	public boolean cfzzjgBc(CfjgForm form) throws Exception {
		CfjgDao dao = new CfjgDao();
		return dao.cfzzjgBc(form);
	}

	/**
	 * ��ѯ������Ϣ
	 * @param form
	 * @return
	 */
	public InputStream fjCx(CfjgForm form) throws Exception {
		CfjgDao dao = new CfjgDao();
		Blob blob = dao.fjCx("select fj from xg_wjcf_wjcfb where cfid = ?", new String[]{form.getCfid()}, "fj");
		return blob.getBinaryStream();
	}
	
	/**
	 * ͨ��ѧ�Ų�ѯѧ�������б�
	 * @param xh
	 * @return
	 */
	public List<HashMap<String, Object>> getStuWjcfList(String xh) {
		List<HashMap<String, Object>> rs = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put(GlobalsVariable.XSXX_KTEYS_GNMK, "Υ�ʹ���");
		map.put(GlobalsVariable.XSXX_KTEYS_CXJG, getStuWjcfAllList(xh));
		rs.add(map);
		return rs;
	}
	
	/**
	 * ͨ��ѧ�Ų�ѯΥ�ʹ����б�
	 * @param xh
	 * @return
	 */
	public List<String[]> getStuWjcfAllList(String xh) {
		CfjgDao dao = new CfjgDao();
		String[] title = {"ѧ��", "ѧ��", "�������", "����ԭ��", "����ʱ��", "�����ĺ�", "���ս��"};
		List<String[]> rs = new ArrayList<String[]>();
		rs.addAll(dao.getStuWjcfList(xh));
		rs.add(0, title);
		return rs;
	}
	/**
	 * ����ά���Զ��嵼��
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getCfjgExportList(CfjgForm model,User user) throws Exception {
		CfjgDao dao = new CfjgDao();
		return dao.getCfjgExportList(model,user);
	}
	/**
	 * 
	 * @����:��ȡѧ���б�
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2015-5-4 ����03:19:13
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public  List<HashMap<String, String>> getXnndList(){
		DAO dao = DAO.getInstance();
		return dao.getXnndList();
		
	}
	
	public String getWjxxByXhXnXq(String xh,String xn,String xq) {
		return dao.getWjxxByXhXnXq(xh, xn, xq);
	}
	/** 
	 * @����:��������̵Ĵ��ֽ���޸�
	 * @���ߣ�CP[���ţ�1352]
	 * @���ڣ�2017-2-16 ����11:24:04
	 * @param myForm
	 */
	public boolean cfsjshlcXg(CfjgForm myForm) throws Exception {
		CfjgDao dao = new CfjgDao();
		return dao.cfsjshlcXg(myForm);
	}
	/** 
	 * @����:�ൺ�Ƶ����ְҵ����ѧԺ����Ĭ�ϴ����ĺţ����+4λ˳���
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��4��27�� ����2:37:07
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param cfjgForm
	 * @return
	 * String �������� 
	 * @throws 
	 */
	public String getDefaultCfwhFor13011() {
		String year = DateUtils.getYear();
		//'^2017[0-9]{4}$'
		String regexp = "^"+year+"[0-9]{4}$";
		String cfwhseed = dao.getDefaultCfwhFor13011(regexp);
		
		String defaultCfwh = year+"000".substring(0,4-cfwhseed.length())+cfwhseed;
		return defaultCfwh;
	}
	
	/** 
	 * @����:���ݴ���id���飬��ѯ���ֽ����Ϣ���б�
	 * ���ں�ְҵ����ѧԺ�����־��������أ�
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��5��15�� ����10:21:49
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getCfjgList(String [] cfids) {
		
		return dao.getCfjgList(cfids);
	}
	
	/** 
	 * @����:���ݴ���id���飬��ѯ���ֽ����Ϣ�����������Ρ�������ò����Ϣ���б�
	 * ���ں�ְҵ����ѧԺ���������������أ�
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��5��16�� ����2:36:05
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param cfids
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getCfjgMoreList(String[] cfids) {
		
		return dao.getCfjgMoreList(cfids);
	}
	
	/** 
	 * @����:���ݴ������ʹ����ĺźϲ����ֽ����¼�����������ʹ����ĺ���ͬ����ͬһList��
	 * 		  ��List�Դ����ĺź����Ϊkey����Map
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��5��15�� ����10:33:29
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * Map<String,List<HashMap<String,String>>> �������� 
	 * @throws 
	 */
	public Map<String, List<HashMap<String, String>>> getCfjgListMap(List<HashMap<String,String>> cfjgList) {
		
		Map<String, List<HashMap<String, String>>> cfjgListMap = new HashMap<String, List<HashMap<String, String>>>();
		
		for(HashMap<String,String> cfjg:cfjgList){
			String key = cfjg.get("cflbdm")+cfjg.get("cfwh");
			List<HashMap<String,String>> list = cfjgListMap.get(key);
			if(list==null){
				list = new ArrayList<HashMap<String, String>>();
				cfjgListMap.put(key, list);
			}
			list.add(cfjg);
		}
		
		return cfjgListMap;
	}
	/** 
	 * @����:���ݺϲ���¼���map������word�ļ����飺���־�����
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��5��15�� ����10:42:28
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param cfjgListMap
	 * @return
	 * File [] �������� 
	 * @throws 
	 */
	public File[] getCfjdsFiles(Map<String, List<HashMap<String, String>>> cfjgListMap) {
		
		List<File> fileList = new ArrayList<File>();
		
		Collection<List<HashMap<String, String>>> collection = cfjgListMap.values();
		for(List<HashMap<String, String>> list:collection){
			HashMap<String,Object> data = new HashMap<String,Object>();
			//�����б�
			List<String> nameList = new LinkedList<String>();
			//�ֱ�Υ���ڼ�����һ������ͬ��
			Set<String> djtSet = new LinkedHashSet<String>();
			//�ı�ת��
			for(int i=0;i<list.size();i++){
				HashMap<String, String> map = list.get(i);
				map.put("cflbmc", HtmlUtil.xmlZy(map.get("cflbmc")));
				map.put("cfwh", HtmlUtil.xmlZy(map.get("cfwh")));
				map.put("wjssjg", HtmlUtil.xmlZy(map.get("wjssjg")));
				map.put("cfyymc", HtmlUtil.xmlZy(map.get("cfyymc")));
				
				if(StringUtils.isNotNull(map.get("xm"))){
					nameList.add(map.get("xm"));
				}
				if(StringUtils.isNotNull(map.get("djt"))){
					djtSet.add(map.get("djt"));
				}
			}
			//��ǰʱ���д
			String zhDate = DateUtils.getZHDate();
			//�����˰���������
			int count = list.size();
			//�����˴�д
			String zhNumber = StringUtils.formatChNum(String.valueOf(count));
			zhNumber = "��".equals(zhNumber)?"��":zhNumber;
			//�����������
			String cflbmc = list.get(0).get("cflbmc");
			//�����ĺ�
			String cfwh = list.get(0).get("cfwh");
			//�ڼ���
			//String djt = list.get(0).get("djt");
			String djt = org.apache.commons.lang.StringUtils.join(djtSet, '��');
			//����
			String names = org.apache.commons.lang.StringUtils.join(nameList, '��');
			
			data.put("names", names);
			data.put("cfjgList", list);
			data.put("zhDate", zhDate);
			data.put("count", count);
			data.put("zhNumber", zhNumber);
			data.put("cflbmc", cflbmc);
			data.put("cfwh", cfwh);
			data.put("djt",djt );
			
			String mbmc = "wjcftzs_" + Base.xxdm + ".xml";
			if(cflbmc.contains("��У�쿴")){
				mbmc = "HD_" + mbmc;	//��ͷ�ļ�
			}
			String fileName = "ѧ��Υ�ʹ��־�����_"+System.currentTimeMillis();
			File wordFile = FreeMarkerUtil.getWordFile(data, "classpath://templates//wjcf", mbmc, fileName);
			fileList.add(wordFile);
			
		}
		
		return fileList.toArray(new File[]{});
	}
	
	/** 
	 * @����:����word�ļ����飺����������
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��5��16�� ����12:42:46
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param cfjgList
	 * @return
	 * File [] �������� 
	 * @throws 
	 */
	public File[] getCfspbFiles(List<HashMap<String, String>> cfjgList) {
		
		List<File> fileList = new ArrayList<File>();
		
		HashMap<String,Object> data = new HashMap<String,Object>();
		for(int i=0;i<cfjgList.size();i++){
			HashMap<String, String> map = cfjgList.get(i);
			map.put("cflbmc", HtmlUtil.xmlZy(map.get("cflbmc")));
			map.put("bz", HtmlUtil.xmlZy(map.get("bz")));
			map.put("cfyj", HtmlUtil.xmlZy(map.get("cfyj")));
			
			//��ǰʱ��
			String curDate = DateUtils.getLyr();
			//�����������Ϣ
			List<HashMap<String,String>> list = new CommShlcImpl().getShyjListByYwid(map.get("cfid"));
			
			data.put("curDate", curDate);
			data.put("cfjg", map);
			data.put("yjsh", list.get(0));
			data.put("ejsh", list.get(1));
			data.put("sjsh", list.get(2));
			
			String mbmc = "wjcfsq_" + Base.xxdm + ".xml";
			String fileName = "ѧ��Υ�ʹ���������_"+System.currentTimeMillis();
			File wordFile = FreeMarkerUtil.getWordFile(data, "classpath://templates//wjcf", mbmc, fileName);
			fileList.add(wordFile);
		}
		
		return fileList.toArray(new File[]{});
	}
	
	/**
	 * @����: ����cfidȡ��¼
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2017-8-2 ����11:57:05
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param cfid
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String> getCfxxByCfid(String cfid){
		return dao.getCfxxByCfid(cfid);
	}
	
	/**
	 * 
	 * @����: ��֤�Ƿ��ظ�
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-9-26 ����07:42:55
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean checkIsNotRepeat(CfjgForm form){
		return dao.checkIsNotRepeat(form);
	}
}
