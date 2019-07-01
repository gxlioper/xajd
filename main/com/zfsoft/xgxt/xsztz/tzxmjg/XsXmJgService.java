/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-7-17 ����09:55:44 
 */  
package com.zfsoft.xgxt.xsztz.tzxmjg;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import xgxt.form.User;
import xgxt.utils.date.DateUtils;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.DateTranCnDate;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.FreeMarkerUtil;
import com.zfsoft.xgxt.base.util.DateTranCnDate.FomartDateType;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import common.newp.StringUtil;
/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����Դ[����:1206]
 * @ʱ�䣺 2015-7-17 ����09:55:44 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XsXmJgService extends SuperServiceImpl<XsXmJgForm, XsXmJgDao> {
	XsXmJgDao dao = new XsXmJgDao();
	private static final String DATE_FORMAT = "yyyy-MM-dd hh24:mi:ss";
	private ShlcInterface shlc = new CommShlcImpl();
	/**
	 * 
	 * @����:��ȡ������˿���
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2015-7-10 ����04:29:29
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * @throws Exception
	 * String[] �������� 
	 * @throws
	 */
//	public String[] getSqShKg() throws Exception{
//		return dao.getSqShKg();
//	}
	
	/**
	 * 
	 * @����:�ظ��ж�
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2015-7-10 ����04:37:04
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean checkExistForSave(XsXmJgForm model) {
		return dao.checkExistForSave(model);
	}
	
	
	public List<HashMap<String, String>> getXmSelectList(String xh) throws Exception {
		return dao.getXmlist(xh);
	}
	
	
	
	//���Ϣ
	public HashMap<String, String> getHdMap(String xmdm,String xn,String xq){
		return dao.getHdMap(xmdm,xn,xq);
	}
	/**
	 * @throws Exception 
	 * @throws SQLException 
	 * 
	 * @����:����䷢����
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2015-7-20 ����01:49:43
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param myForm
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean saveJxbf(XsXmJgForm myForm) throws Exception{
		boolean result = true;
		String[] sjlys = myForm.getSjlys().split(",");
		String[] xhs = myForm.getXhs().split(",");
		String[] ylzd1s= myForm.getYlzd1s().split(",");
		String[] ylzd2s= myForm.getYlzd2s().split(",");
		List<String[]> sqxsList = new ArrayList<String[]>();
		List<String[]> sqxslcList = new ArrayList<String[]>();//��������
		String[] sqxs= null;
		for (int i = 0; i < xhs.length; i++) {
			sqxs = new String[6];
			sqxs[0]="no".equals(ylzd1s[i])?"":ylzd1s[i];
			sqxs[1]=ylzd2s[i];
			sqxs[2]=myForm.getXmdm();
			sqxs[3]=myForm.getXn();
			sqxs[4]=myForm.getXq();
			sqxs[5]=xhs[i];
			
			if("1".equals(sjlys[i])){
				sqxslcList.add(sqxs);
			}else{
				sqxsList.add(sqxs);
			}
			
		}
		//�������ݸ��½���
		result=dao.updateJxxx(sqxslcList);
		//����ѧ����������
		dao.delXssqjg(myForm);
		result=dao.insertJxxx(sqxsList);
		
		return result;
	}
	
	public List<HashMap<String, String>> grxfcxList(XsXmJgForm myForm,User user) throws Exception{
		return dao.grxfcxList(myForm,user);
	}
	/**
	 * @����: ��ȡ��Ŀ������ѧ�֡��Ƿ�ϸ�ͳ���ж�
	 * @���ߣ� ����[���ţ�1186]
	 * @���ڣ�2015-11-24 ����05:23:40
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getSztzhdxfList(String xh) {
		return dao.getSztzhdxf(xh);
	}
	/**
	 * @����: ����List
	 * @���ߣ� ����[���ţ�1186]
	 * @���ڣ�2015-11-24 ����05:24:12
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param size
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getBlankList(int size){
		List<HashMap<String,String>> list = new ArrayList<HashMap<String,String>>(size);
		for (int i = 0 ; i < size ; i++){
			list.add(new HashMap<String, String>());
		}
		return list;
	}
	
	public File getXscjWord(String xh,HttpServletRequest request)
	throws Exception {
		XsxxService xsxxService = new XsxxService();
		Map<String, Object> data = new HashMap<String, Object>();
		ArrayList<HashMap<String, String>> aList = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> dyxqMap = new HashMap<String, String>();
		HashMap<String, String> dexqMap = new HashMap<String, String>();
		String xnxq = null;
		HashMap<String, String> xsxxMap = xsxxService.getXsjbxxMore(xh);
		if(null==xsxxMap.get("rxrq")||"".equals(xsxxMap.get("rxrq"))){
			return null ;
		}
		int rxnd = Integer.parseInt(xsxxMap.get("rxrq").substring(0, 4));
		int k=1;
		for (int i = rxnd; i < rxnd + 4; i++) {
			dyxqMap = new HashMap<String, String>();
			dexqMap = new HashMap<String, String>();
			xnxq = String.valueOf(i) + "-" + String.valueOf(i + 1);
			dyxqMap.put("xnxq", xnxq+"01");
			aList.add(dyxqMap);
			dexqMap.put("xnxq", xnxq+"02");
			aList.add(dexqMap);
			data.put("xnxq"+k, xnxq);
			k++;
		}
		data.putAll(xsxxMap);
		//ѧ����Ƭ
		InputStream is = xsxxService.getPhotoStream(xsxxMap.get("xh"));
		File photoFile = FileUtil.inputstreamToFile(is, "doc");
		String photo = FileUtil.getBASE64(photoFile);
		if (StringUtil.isNull(photo)){
			//ȡĬ����Ƭ 
			photo = xsxxService.getDefaultPhotoBase64(request);
		}
		if(photo == null){
			photo = "";
		}
		data.put("dyrq",DateTranCnDate.fomartDateToCn(DateUtils.getCurrDate(),FomartDateType.day));
		data.put("photo", photo);
		List<HashMap<String,String>> tzcjList = dao.getTzcjList(aList,xsxxMap.get("xh"));
		Double zcj1=0.0;
		Double zcj2=0.0;
		Double zcj3=0.0;
		Double zcj4=0.0;
		Double zcj5=0.0;
		Double zcj6=0.0;
		Double zcj7=0.0;
		Double zcj8=0.0;
		for (int i = 0; i < tzcjList.size(); i++) {
			zcj1+=Double.parseDouble(tzcjList.get(i).get("xmfs0"));
			zcj2+=Double.parseDouble(tzcjList.get(i).get("xmfs1"));
			zcj3+=Double.parseDouble(tzcjList.get(i).get("xmfs2"));
			zcj4+=Double.parseDouble(tzcjList.get(i).get("xmfs3"));
			zcj5+=Double.parseDouble(tzcjList.get(i).get("xmfs4"));
			zcj6+=Double.parseDouble(tzcjList.get(i).get("xmfs5"));
			zcj7+=Double.parseDouble(tzcjList.get(i).get("xmfs6"));
			zcj8+=Double.parseDouble(tzcjList.get(i).get("xmfs7"));
		}
		data.put("zcj1", zcj1);
		data.put("zcj2", zcj2);
		data.put("zcj3", zcj3);
		data.put("zcj4", zcj4);
		data.put("zcj5", zcj5);
		data.put("zcj6", zcj6);
		data.put("zcj7", zcj7);
		data.put("zcj8", zcj8);
		data.put("tzcjList", tzcjList);
		File file  = FreeMarkerUtil.getWordFile(data, "classpath://templates//sztz", "sztz_cjd.xml", xsxxMap.get("xh")
				+ "-" + xsxxMap.get("xm"));
		return file;
		
		}
	
	/**
	 * 
	 * @����: ��ȡ������ѧ�ֲ�ѯ
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-8-19 ����04:00:48
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getgrZxf(String xh){
		return dao.getgrZxf(xh);
	}
}