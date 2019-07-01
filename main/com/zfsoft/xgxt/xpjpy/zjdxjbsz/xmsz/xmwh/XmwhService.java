/**
 * @����:ѧ����Ʒ1��
 * @���ڣ�2017-4-7 ����10:59:15 
 */  
package com.zfsoft.xgxt.xpjpy.zjdxjbsz.xmsz.xmwh;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.xpjpy.zjdxjbsz.cssz.CsszDao;
import com.zfsoft.xgxt.xpjpy.zjdxjbsz.cssz.CsszForm;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �������Ź���ģ��
 * @�๦������: ��������_��Ŀ����_��Ŀά��
 * @���ߣ� Meng.Wei[����:1186]
 * @ʱ�䣺 2017-4-7 ����10:57:56 
 * @�汾�� V5.18.26
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XmwhService extends SuperServiceImpl<XmwhForm,XmwhDao>{
	
	private XmwhDao dao = new XmwhDao();
	
	public XmwhService(){
		super.setDao(dao);
	}
	
	/**
	 * @����: ��ȡ����������Ϣ
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2017-4-10 ����10:19:35
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String> getCsszMap(){
		return dao.getCsszMap();
	}
	
	/**
	 * @����: ��ȡ��Ŀ����list
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ�2017-4-10 ����10:20:10
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getXmlx() throws Exception {
		return dao.getXmlx();
	}
	
	/**
	 * @����: ��ȡϵͳ��ǰʱ��
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ�2017-4-10 ����05:19:10
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param dateFormat
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getCurrTime(String dateFormat){
		DateFormat df = new SimpleDateFormat(dateFormat);
		return df.format(new Date());
	}
	
	/**
	 * @����: ��ȡ��������list
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ�2017-4-10 ����10:20:54
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getXmxz() throws Exception {
		return dao.getXmxz();
	}
	
	/**
	 * @����: ��֤ͬһ��ѧ���Ƿ�����ʾ˳���ظ�
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ�2017-4-10 ����02:12:07
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean isExistXssx(XmwhForm model) throws Exception {
		String num = dao.checkExistXssx(model);
		return Integer.valueOf(num) > 0;
	}
	
	/**
	 * @����: ��֤ͬһ��ѧ���Ƿ�����Ŀ�����ظ�
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ�2017-4-10 ����02:28:21
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	 public boolean isExistXmmc(XmwhForm model) throws Exception {
		return dao.isExistXmmc(model);
	 }
	 
	 /**
	  * @����: �ж���ѡɾ������Ŀ �Ƿ�����Ŀ����ʱ��ʹ��
	  * @���ߣ� Meng.Wei[���ţ�1186]
	  * @���ڣ�2017-4-11 ����04:08:28
	  * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	  * @param values
	  * @return
	  * @throws Exception
	  * boolean �������� 
	  * @throws
	  */
	 public boolean checkForXmsq(String values) throws Exception {
		boolean flag = false;
		// �Ƿ���ѧ��������Ŀ
		if (values != null) {
			String[] xmdms = values.split(",");
			for (int i = 0; i < xmdms.length; i++) {
				flag = isExistsFlowData(xmdms[i]);
				if (flag) {
					break;
				}
			}
		}
		return flag;
	 }
	 
	 /*�ж���ѡɾ������Ŀ �Ƿ�����Ŀ����ʱ��ʹ��__����ֵ*/
	 public boolean isExistsFlowData(String xmdm) {
		return Integer.valueOf(dao.getFlowData(xmdm)) > 0;
	 }
	 
	 /**
	  * @����: ������Ҫɾ����Ŀ �Ĺ�����
	  * @���ߣ� Meng.Wei[���ţ�1186]
	  * @���ڣ�2017-4-11 ����04:21:32
	  * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	  * @param values
	  * @return
	  * @throws Exception
	  * boolean �������� 
	  * @throws
	  */
	 public boolean deleteRelationalTable(String values) throws Exception {
		return dao.deleteRelationalTable(values);
	}
	 
	 /**
	  * @����: ������Ŀ��������Ŀ����
	  * @���ߣ� Meng.Wei[���ţ�1186]
	  * @���ڣ�2017-4-12 ����10:00:39
	  * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	  * @param xmdm
	  * @return
	  * @throws Exception
	  * String �������� 
	  * @throws
	  */
	 public String getNameById(String xmdm) throws Exception {
			return dao.getNameById(xmdm);
	}
	 
	 /**
	  * @����: �����ȡ�ǵ�ǰ����ѧ���ѧ���б�
	  * @���ߣ� Meng.Wei[���ţ�1186]
	  * @���ڣ�2017-4-12 ����03:55:14
	  * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	  * @return
	  * @throws Exception
	  * List<HashMap<String,String>> �������� 
	  * @throws
	  */
	 public List<HashMap<String, String>> getJxfz() throws Exception {
		List<HashMap<String, String>> jxfzList = new ArrayList<HashMap<String, String>>();
		CsszDao csszDao = new CsszDao();
		CsszForm csszXx = csszDao.getModel();
		/*ȡ��ǰ����ѧ��*/
		String currXn = csszXx.getXn();
		/*ȡ������Ŀ�������е�xn�б�*/
		List<HashMap<String, String>> xnList = dao.getJxfzXn();
		String xn = null;
		HashMap<String, String> jxfzMap = null;
		if (xnList != null) {
			for (HashMap<String, String> map : xnList) {
				xn = map.get("xn");
				/*ֻȡ�ǵ�ǰ����ѧ�������*/
				if (!xn.equals(currXn)) {
					jxfzMap = new HashMap<String, String>();
					jxfzMap.put("dm", xn);
					jxfzMap.put("mc", xn);
					jxfzList.add(jxfzMap);
				}
			}
		}
		return jxfzList;
	}
	 
	 public boolean runJxfz(String jxfznd) throws Exception {
		 boolean flag = false;
		 //XmwhService service = new XmwhService();
		 CsszDao csszDao = new CsszDao();
		 CsszForm csszForm = csszDao.getModel();
		 /*��ǰ����ѧ��*/
		 String currXn = csszForm.getXn();
		 /*����Ŀ��ѧ��*/
		 String fzXn = jxfznd.split(";")[0];
		 /*��ѯ������ѧ���뱾ѧ�겻ͬ��Ŀ���Ƶ�����*/
		 List<HashMap<String, String>> jxfzList = dao.getJxfz(fzXn,currXn);
		 if (jxfzList != null && jxfzList.size() > 0) {
				for (int i = 0; i < jxfzList.size(); i++) {
					/*�Զ�����GUID*/
					String guid = UniqID.getInstance().getUniqIDHash();
					guid = guid.toUpperCase();
					/*��guid�����Ϊ��Ŀ���룬׼������������Ŀ����*/
					jxfzList.get(i).put("xmdm", guid);
				}
				/*�������*/
				flag = dao.saveJxfzData(jxfzList, currXn);
				/*���ƽ�����*/
				List<XmwhForm> list = null;
				/*��jxfzMap������Ŀ��ѧ���뵱ǰ�겻ͬ��Ŀ���Ƶ�����*/
				for (HashMap<String, String> jxfzMap : jxfzList) {
					list=new ArrayList<XmwhForm>();
					/*����xmdm��ѯ���õļ�¼*/
					List<HashMap<String, String>> jdList = getByXmdm(jxfzMap.get("xmdm"),currXn);
					StringBuffer jdXmdms = new StringBuffer();
					for (int i = 0; i < jdList.size(); i++) {
						if(i<jdList.size()-1){
							jdXmdms.append(jdList.get(i).get("fzbjdxmdm"));
							jdXmdms.append(",");
						}else{
							jdXmdms.append(jdList.get(i).get("fzbjdxmdm"));
						}
					}
					/*�������*/
					flag =jdsz(jxfzMap.get("xmdm"), jdXmdms.toString());
				}
		 }
		 return flag;
	 }
	 
	 /**
	  * @����: ����xmdm��ѯ���õļ�¼
	  * @���ߣ� Meng.Wei[���ţ�1186]
	  * @���ڣ�2017-4-18 ����08:45:29
	  * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	  * @param xmdm
	  * @param currXn
	  * @return
	  * @throws Exception
	  * List<HashMap<String,String>> �������� 
	  * @throws
	  */
	 public List<HashMap<String, String>> getByXmdm(String xmdm,String currXn) throws Exception{
		return dao.getByXmdm(xmdm,currXn);		
	 }
	 
	 /**
	  * @����: �������
	  * @���ߣ� Meng.Wei[���ţ�1186]
	  * @���ڣ�2017-4-18 ����08:46:23
	  * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	  * @param xmdm
	  * @param xmdms
	  * @return
	  * @throws Exception
	  * boolean �������� 
	  * @throws
	  */
	 public boolean jdsz(String xmdm,String xmdms) throws Exception{
		return dao.runJdsz(xmdm,xmdms);
	 }
	 
	 /**
	  * @����: ������Ŀ�����ѯ��¼
	  * @���ߣ� Meng.Wei[���ţ�1186]
	  * @���ڣ�2017-5-16 ����03:54:56
	  * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	  * @param xmdm
	  * @return
	  * @throws Exception
	  * HashMap<String,String> �������� 
	  * @throws
	  */
	 public HashMap<String, String> getDataById(String xmdm) throws Exception {
			return dao.getDataById(xmdm);
		}
	 
	 /**
	  * @����: ͨ����Ŀ�����ȡ�Ѿ����õ��꼶,�꼶�Զ��ŷָ�
	  * @���ߣ� Meng.Wei[���ţ�1186]
	  * @���ڣ�2017-6-1 ����01:45:04
	  * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	  * @param xmdm
	  * @return
	  * @throws Exception
	  * String �������� 
	  * @throws
	  */
	public String getRsfpnj(String xmdm) throws Exception {
		return dao.getRsfpnj(xmdm);
	}
	
	/**
	 * @����: ����ѧ�ꡢ��Ŀ���Ʋ�ѯ��Ŀ��Ӧ����
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2017-12-14 ����11:16:16
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xmmc
	 * @param xn
	 * @return
	 * @throws Exception
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String> getDataByName(String xmmc, String xn) throws Exception {
		return dao.getDataByName(xmmc, xn);
	}
}
