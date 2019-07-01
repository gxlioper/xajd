/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-4-20 ����01:16:38 
 */  
package com.zfsoft.xgxt.xszz.knsjg;


import java.io.File;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.commons.beanutils.BeanUtils;

import xgxt.DAO.ExcelMB;
import xgxt.form.User;
import xgxt.utils.ExcelMethods;
import xgxt.utils.date.DateUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.extend.SuperServiceImplExtend;
import com.zfsoft.xgxt.base.util.FreeMarkerUtil;
import com.zfsoft.xgxt.xszz.knsdc.KnsdcDao;




/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ��������ѯ����ģ��
 * @�๦������: Service
 * @���ߣ� maxh
 * @ʱ�䣺 2013-4-20 ����01:16:38 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class KnsjgService extends SuperServiceImplExtend<KnsjgForm, KnsjgDao>{

     private KnsjgDao dao = new KnsjgDao();
	
	public KnsjgService(){
		super.setDao(dao);
	}
	/**
	 * 
	 * @����:Ψһ���жϣ�ѧ�ţ�ѧ�꣬ѧ�ڣ�
	 * @���ߣ�maxh
	 * @���ڣ�2013-4-23 ����09:16:02
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param type
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	 public boolean isExistByKnsjg(KnsjgForm model, String type)throws Exception{
		    boolean flag = false;
			if("save".equalsIgnoreCase(type)){
				String num=dao.checkExistForSave(model);
				if(!"0".equalsIgnoreCase(num)){
					flag = true;
				}
			}else if("update".equalsIgnoreCase(type)){
				String num=dao.checkExistForUpdate(model);
				if(!"0".equalsIgnoreCase(num)){
					flag = true;
				}	
			}
	    	return  flag;
	}
	 /**
	 * @throws Exception 
	  * 
	  * @����:��õ���ѧ���϶���Ϣ
	  * @���ߣ�maxh
	  * @���ڣ�2013-4-23 ����03:13:06
	  * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	  * @param xh
	  * @param wjsj
	  * @param request
	  * @return
	  * List<String[]> �������� 
	  * @throws
	  */
	public Map<String, String> getOneKnsjgList(String  guid) throws Exception {
		 
		return dao.getOneKnsjgList(guid);
	}
	 
	/**
	 *  
	 * @����:TODO(��ȡȡ���������ʸ�)
	 * @���ߣ�������[���ţ�995]
	 * @���ڣ�2016-4-28 ����11:08:41
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param guid
	 * @return
	 * @throws Exception
	 * Map<String,String> �������� 
	 * @throws
	 */
	public Map<String, String> getOneKnsqxjlList(String guid) throws Exception {
		 
		return dao.getOneKnsqxjlList(guid);
	}
	 
	 /**
	  * 
	  * @����:��ѧ�Ų�ѯȫ����������Ϣ
	  * @���ߣ�Penghui.Qu
	  * @���ڣ�2013-4-25 ����03:19:13
	  * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	  * @param xh
	  * @return
	  * List<HashMap<String,String>> �������� 
	  * @throws
	  */
	 public List<HashMap<String,String>> getKnsInfoList(String xh){
		 
		 if (StringUtil.isNull(xh)){
			 logger.error("xh can't null !");
			 throw new NullPointerException();
		 }
		 
		 return dao.getKnsInfoList(xh);
	 }
	 	 		
		
		/**
		 * 
		 * @����:�����������-ѧ��
		 * @���ߣ�ligl
		 * @���ڣ�2013-4-19 ����02:19:00
		 * @�޸ļ�¼:
		 * @return List<HashMap<String,String>> ��������
		 * @throws
		 */
		public List<HashMap<String, String>> getKnsjgXn() throws Exception {
			return dao.getKnsjgXn();
		}
		
		/**
		 * 
		 * @����:�����������-ѧ��
		 * @���ߣ�ligl
		 * @���ڣ�2013-4-19 ����02:19:00
		 * @�޸ļ�¼:
		 * @return List<HashMap<String,String>> ��������
		 * @throws
		 */
		public List<HashMap<String, String>> getKnsjgXq() throws Exception {
			List<HashMap<String, String>> knsjqXq2 = new ArrayList<HashMap<String,String>>();
			List<HashMap<String, String>> knsjqXq = dao.getKnsjgXq();
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("dm", "");
			map.put("mc", "");
	//		knsjqXq2.add(map);
			
			for (HashMap<String, String> hashMap : knsjqXq) {
				knsjqXq2.add(hashMap);
			}			
			return knsjqXq2;
		}
		
		/**
		 * 
		 * @����:�����������-����
		 * @���ߣ�cq [���ţ�785]
		 * @���ڣ�2013-10-15 ����11:52:44
		 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
		 * @return
		 * @throws Exception
		 * List<HashMap<String,String>> �������� 
		 * @throws
		 */
		public List<HashMap<String, String>> getKnsjgZq() throws Exception {
			return dao.getKnsjgZq();
		}
		
		/**
		 * 
		 * @����: ��ȡѧ�������������
		 * @���ߣ�honglin
		 * @���ڣ�2013-5-4
		 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
		 * @param t
		 * @return
		 * String �������� 
		 * @throws
		 */
		public HashMap<String,String> getXsknsjg (String xh ,String xn ,String xq){
			
			if (StringUtil.isNull(xh)){
				logger.error("ѧ�Ų���Ϊ�գ�");
				throw new NullPointerException();
			}
			return dao.getXsknsjg(xh,xn,xq);
		}
		
		/**
		 * 
		 * @����:��ȡ�����������-���ڣ�ȥ���ض�����
		 * @���ߣ��ո־� [1075]
		 * @���ڣ�2014-7-1 ����11:32:45
		 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
		 * @param knsjgzq
		 * @return
		 * List<HashMap<String,String>> �������� 
		 * @throws
		 */
		public List<HashMap<String, String>> getKnsjgZqListNotIsHave(String knsjgzq) {
			List<HashMap<String, String>> list = dao.getKnsjgZqList();
			for (HashMap<String, String> hm : list) {
				if (hm.get("zqdm").equals(knsjgzq)) {
					list.remove(hm);
					break;
				}
			}
			return list;
		}
		
		/**
		 * 
		 * @����:TODO(������һ�仰�����������������)
		 * @���ߣ��ո־� [1075]
		 * @���ڣ�2014-7-1 ����03:12:32
		 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
		 * @param lyxn
		 * @param lyxq
		 * @param xn
		 * @param xq
		 * @return
		 * @throws Exception
		 * boolean �������� 
		 * @throws
		 */
		public String copy(String lyxn, String lyxq, String xn, String xq)
				throws Exception {
			int okNum = 0; // �ɹ�����
			boolean result = true; // �������
			StringBuilder notOk = new StringBuilder(); // �ظ��ļ�¼
			List<HashMap<String, String>> list = dao.getKnsjgForXnXq(lyxn, lyxq);
			KnsjgForm model = new KnsjgForm();
			for (HashMap<String, String> hm : list) {
				BeanUtils.copyProperties(model, hm);
				model.setGuid(null);
				model.setXn(xn);
				model.setXq(xq);
				model.setSjly("0");
				// �Ƿ��ظ�
				String num=dao.checkExistForSave(model);
				if(!"0".equalsIgnoreCase(num)){
					notOk.append(model.getXh() + " " + model.getXm() + "��");
				}else{
					result = runInsert(model);
					if(result){
						okNum++;
					}
				}
			}
			String resultMsg = "";
			if(result){
				resultMsg = "���Ƴɹ�"+okNum+"����";
				if(list.size() - okNum > 0){
					resultMsg += "�ظ��ļ�¼��" + notOk.toString().substring(0, notOk.toString().length() - 1);
				}
			}else{
				resultMsg = "����ʧ�ܣ�";
			}
			return resultMsg;
		}
	/**
	 * 
	 * @����:������ͳ�Ƶ��������ݴ�ѧ��
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-4-20 ����03:15:24
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param os
	 * @param user
	 * @throws Exception
	 * void �������� 
	 * @throws
	 */
	public void knsrdTjExport(List<HashMap<String,String>> resultList, OutputStream os, User user) throws Exception {
		List<HashMap<String, String>> rddcList = new KnsdcDao().getKnsdcList();
		WritableWorkbook wwb = Workbook.createWorkbook(os);

		WritableSheet ws = wwb.createSheet("��ͥ��������ѧ���϶�ͳ�Ʊ�", 0);

		WritableCellFormat wcf2 = new WritableCellFormat(); // ���쵥Ԫ���ʽ
		wcf2 = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false, Alignment.CENTRE, VerticalAlignment.CENTRE, true, Border.ALL);

		// ������
		ExcelMB ex = new ExcelMB();
		ex.printToOneCell_multy(ws, "", 0, 0, 10, true,
				Alignment.LEFT, VerticalAlignment.CENTRE, true, 700,
				Border.NONE);
		ex.printToOneCell_multy(ws, "", 11, 0, 10,
				true, Alignment.CENTRE, VerticalAlignment.CENTRE, true, 700,
				Border.NONE);
		ws.mergeCells(0, 0, 10, 0);
		ws.mergeCells(11, 0, 19, 0);
		ws.mergeCells(13, 1, 13+rddcList.size()-1, 1);
		ws.mergeCells(0, 1, 0, 2);
		ws.mergeCells(1, 1, 1, 2);
		ws.mergeCells(2, 1, 2, 2);
		ws.mergeCells(3, 1, 3, 2);
		ws.mergeCells(4, 1, 4, 2);
		ws.mergeCells(5, 1, 5, 2);
		ws.mergeCells(6, 1, 6, 2);
		ws.mergeCells(7, 1, 7, 2);
		ws.mergeCells(8, 1, 8, 2);
		ws.mergeCells(9, 1, 9, 2);
		ws.mergeCells(10, 1, 10, 2);
		ws.mergeCells(11, 1, 11, 2);
		ws.mergeCells(12, 1, 12, 2);
		ex.printToOneCell_multy(ws, "���", 0, 1, 10, false, Alignment.CENTRE, VerticalAlignment.CENTRE, true, 700, Border.ALL);
		ws.addCell(new Label(1, 1, "����", wcf2));
		ws.addCell(new Label(2, 1, "�Ա�", wcf2));
		ws.addCell(new Label(3, 1, "ѧԺ", wcf2));
		ws.addCell(new Label(4, 1, "רҵ", wcf2));
		ws.addCell(new Label(5, 1, "ѧ��", wcf2));
		ws.addCell(new Label(6, 1, "���֤��", wcf2));
		ws.addCell(new Label(7, 1, "����һ��ͨ", wcf2));
		ws.addCell(new Label(8, 1, "��ϵ�绰", wcf2));
		ws.addCell(new Label(9, 1, "������ò", wcf2));
		ws.addCell(new Label(10, 1, "��������", wcf2));
		ws.addCell(new Label(11, 1, "����", wcf2));
		ws.addCell(new Label(12, 1, "��ͥ��ϸ��ַ", wcf2));
		ws.addCell(new Label(13, 1, "�϶�����", wcf2));
		for (int i = 0; i < rddcList.size(); i++) {
			ws.addCell(new Label(i+13, 2, rddcList.get(i).get("dcmc"), wcf2));	
		}
	
		if (resultList != null && resultList.size() > 0) {
			
			for (int i = 0; i < resultList.size(); i++) {
				HashMap<String, String> map = resultList.get(i);
				ws.setColumnView(0, 5);
				ws.setColumnView(1, 8);
				ws.setColumnView(2, 7);
				ws.setColumnView(3, 20);
				ws.setColumnView(4, 20);
				ws.setColumnView(5, 15);
				ws.setColumnView(6, 20);
				ws.setColumnView(7, 20);
				ws.setColumnView(8, 15);
				ws.setColumnView(9, 15);
				ws.setColumnView(10, 8);
				ws.setColumnView(11, 8);
				ws.setColumnView(12, 20);
				for (int j = 0; j < rddcList.size(); j++) {
					ws.setColumnView(j+13, 8);
				}
				ws.addCell(new Label(0, 3 + i, i+1+"", wcf2));// ���
				ws.addCell(new Label(1, 3 + i, map.get("xm"), wcf2));
				ws.addCell(new Label(2, 3 + i, map.get("xb"), wcf2));
				ws.addCell(new Label(3, 3 + i, map.get("xymc"), wcf2));
				ws.addCell(new Label(4, 3 + i, map.get("zymc"), wcf2));
				ws.addCell(new Label(5, 3 + i, map.get("xh"), wcf2));
				ws.addCell(new Label(6, 3 + i, map.get("sfzh"), wcf2));
				ws.addCell(new Label(7, 3 + i, map.get("yhkh"), wcf2));
				ws.addCell(new Label(8, 3 + i, map.get("sjhm"), wcf2));
				ws.addCell(new Label(9, 3 + i, map.get("zzmmmc"), wcf2));
				ws.addCell(new Label(10, 3 + i, map.get("zd5"), wcf2));
				ws.addCell(new Label(11, 3 + i, map.get("mzmc"), wcf2));
				ws.addCell(new Label(12, 3 + i, map.get("jtdz"), wcf2));
				for (int k = 0; k < rddcList.size(); k++) {
					if(map.get("dcmc").equals(rddcList.get(k).get("dcmc"))){
						ws.addCell(new Label(13+k, 3 + i, "��", wcf2));
					}else{
						ws.addCell(new Label(13+k, 3 + i, "", wcf2));
					}
					
				}
				
			}
		}
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	/**
	 * @������
	 * @���ߣ�׿��[����:1391]
	 * @���ڣ�2016��12��30�� 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param resultList
	 * @param os
	 * @param user
	 * @throws Exception
	 * void ��������
	 */
	public File exportDashk(List<HashMap<String,String>> resultList,File file, User user) throws Exception {
		WritableWorkbook wwb = Workbook.createWorkbook(file);
		WritableSheet ws = wwb.createSheet("�㽭��ѧ��������ѧ�����ܱ�", 0);
		WritableCellFormat wcf2 = new WritableCellFormat(); // ���쵥Ԫ���ʽ
		wcf2 = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false, Alignment.CENTRE, VerticalAlignment.CENTRE, false, Border.ALL);

		// ������
		ExcelMB ex = new ExcelMB();
		ws.mergeCells(0, 0, 10, 0);
		ws.mergeCells(0, 1, 10, 1);
		ex.printToOneCell_multy(ws, "�㽭��ѧ��������ѧ�����ܱ�", 0, 0, 15, true,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, 700,	Border.ALL);
		ex	.printToOneCell_multy(ws, "ѧԺ��ϵ����ѧ԰��                                                          �����ˣ����£�", 0, 1, 10, true,
				Alignment.LEFT, VerticalAlignment.CENTRE, true, 700,Border.ALL);
		ws.addCell(new Label(0, 2, "���", wcf2));
		ws.addCell(new Label(1, 2, "ѧԺ��ϵ����ѧ԰", wcf2));
		if (resultList != null && resultList.size() > 0) {
			for (int i = 0; i < resultList.size(); i++) {
				HashMap<String, String> map = resultList.get(i);
				ws.setColumnView(0, 5);
				ws.setColumnView(1, 20);
				ws.addCell(new Label(0, 3 + i, i+1+"", wcf2));// ���
				ws.addCell(new Label(1, 3 + i, map.get("xymc"), wcf2));
				ws.addCell(new Label(2, 3 + i, map.get("xh"), wcf2));
			}
		}
		ExcelMethods.submitWritableWorkbookOperations(wwb);
		return file;
	}
	
	public void knsExport(List<HashMap<String,String>> resultList, OutputStream os, User user) throws Exception {
		List<HashMap<String, String>> rddcList = new KnsdcDao().getKnsdcList();
		WritableWorkbook wwb = Workbook.createWorkbook(os);

		WritableSheet ws = wwb.createSheet("�㽭��ѧ��������ѧ�����ܱ�", 0);

		WritableCellFormat wcf2 = new WritableCellFormat(); // ���쵥Ԫ���ʽ
		wcf2 = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false, Alignment.CENTRE, VerticalAlignment.CENTRE, false, Border.ALL);

		// ������
		ExcelMB ex = new ExcelMB();
		ws.mergeCells(0, 0, 10, 0);
		ws.mergeCells(0, 1, 10, 1);
		ex.printToOneCell_multy(ws, "�㽭��ѧ��������ѧ�����ܱ�", 0, 0, 15, true,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, 700,
				Border.ALL);
		ex
				.printToOneCell_multy(ws, "ѧԺ��ϵ����ѧ԰��                                                          �����ˣ����£�", 0, 1, 10, true,
						Alignment.LEFT, VerticalAlignment.CENTRE, true, 700,
						Border.ALL);


		ws.addCell(new Label(0, 2, "���", wcf2));
		ws.addCell(new Label(1, 2, "ѧԺ��ϵ����ѧ԰", wcf2));
		ws.addCell(new Label(2, 2, "ѧ��", wcf2));
		ws.addCell(new Label(3, 2, "����", wcf2));
		ws.addCell(new Label(4, 2, "����", wcf2));
		ws.addCell(new Label(5, 2, "��Դ��", wcf2));
		ws.addCell(new Label(6, 2, "��ͥ�˾�������", wcf2));
		ws.addCell(new Label(7, 2, "��ͥ��Ů���ڶ����м����ϣ�����", wcf2));
		ws.addCell(new Label(8, 2, "ѧ�����", wcf2));
		ws.addCell(new Label(9, 2, "��ϵ�绰", wcf2));
		ws.addCell(new Label(10, 2, "���ѵȼ�", wcf2));
		
		if (resultList != null && resultList.size() > 0) {
			
			for (int i = 0; i < resultList.size(); i++) {
				HashMap<String, String> map = resultList.get(i);
				ws.setColumnView(0, 5);
				ws.setColumnView(1, 20);
				ws.setColumnView(2, 15);
				ws.setColumnView(3, 10);
				ws.setColumnView(4, 10);
				ws.setColumnView(5, 15);
				ws.setColumnView(6, 10);
				ws.setColumnView(7, 10);
				ws.setColumnView(8, 10);
				ws.setColumnView(9, 15);
				ws.setColumnView(10, 10);
				ws.addCell(new Label(0, 3 + i, i+1+"", wcf2));// ���
				ws.addCell(new Label(1, 3 + i, map.get("xymc"), wcf2));
				ws.addCell(new Label(2, 3 + i, map.get("xh"), wcf2));
				ws.addCell(new Label(3, 3 + i, map.get("xm"), wcf2));
				ws.addCell(new Label(4, 3 + i, map.get("mzmc"), wcf2));
				ws.addCell(new Label(5, 3 + i, map.get("sydss"), wcf2));
				ws.addCell(new Label(6, 3 + i, map.get("jtrjsr"), wcf2));
				ws.addCell(new Label(7, 3 + i, "", wcf2));
				ws.addCell(new Label(8, 3 + i, map.get("sqly"), wcf2));
				ws.addCell(new Label(9, 3 + i, map.get("sjhm"), wcf2));
				ws.addCell(new Label(10, 3 + i, map.get("dcmc"), wcf2));
				
			}
		}
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	 
	
	public List<HashMap<String,String>> getKnsdcList(){
		return  dao.getKnsdcList();
	}
	
	public HashMap<String, String> getKnspd(String xh,String xn){
		return dao.getKnspd(xh, xn);
	}
	
	public List<HashMap<String,String>> knstksPercent(KnsjgForm model) throws Exception{
		return dao.knstksPercent(model);
	}
	/**
	 * @����: �㽭��ѧѧ������������ȡ���϶�����������Ϣ
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ�2016-11-25 ����10:35:16
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String,String> getKnsxx (String xh){
		if (StringUtil.isNull(xh)){
			logger.error("ѧ�Ų���Ϊ�գ�");
			throw new NullPointerException();
		}
		return dao.getKnsxx(xh);
	}
	
	/**
	 * @��������ѯ�����µ�����ѧ����ѧԺ����������
	 * @���ߣ�׿��[����:1391]
	 * @���ڣ�2017��1��3�� 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> ��������
	 */
	public List<HashMap<String,String>> getKnsjgXy(KnsjgForm t, User user) throws Exception{
		return dao.getKnsjgXy(t,user);
	}
	
	/**
	 * @��������ѯ�����µĵ���ѧԺ������ѧ��
	 * @���ߣ�׿��[����:1391]
	 * @���ڣ�2017��1��3�� 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param user
	 * @param xydm
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> ��������
	 */
	public List<HashMap<String,String>> getKnsjgListByxy(KnsjgForm t, User user, String xydm) throws Exception{
		return dao.getKnsjgListByxy(t,user,xydm);
	}
	
	public List<HashMap<String,String>> getKnsjgBj(KnsjgForm t, User user) throws Exception{
		return dao.getKnsjgBj(t,user);
	}
	
	public List<HashMap<String,String>> getKnsjgListBybj(KnsjgForm t, User user, String bjdm) throws Exception{
		return dao.getKnsjgListBybj(t,user,bjdm);
	}
	
	/**
	 * @��������ӡ
	 * @���ߣ�׿��[����:1391]
	 * @���ڣ�2017��1��4�� 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param datalist
	 * @param fileName
	 * @param template
	 * @return
	 * @throws Exception
	 * File ��������
	 */
	public File printFile(List<HashMap<String, String>> datalist,String fileName,String template) throws Exception{
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("datalist", datalist);
		data.putAll(datalist.get(0));
		data.put("rs", datalist.size());
		data.put("sysdate", new SimpleDateFormat("yyyy��MM��dd��").format(new Date()));
		File file=null;
		if("knsrdpxb_12898.xml".equals(template)){
			int blanksize=(16 - datalist.size())<0?0:(16 - datalist.size());
			List<HashMap<String,String>> blanklist = new ArrayList<HashMap<String,String>>(blanksize);
			for (int i = 0 ; i < blanksize ; i++){
				blanklist.add(new HashMap<String, String>());
			}
			data.put("blankList", blanklist);
			file = FreeMarkerUtil.getWordFile(data, "classpath:templates/xszz", template, fileName);
		}else{
			file = FreeMarkerUtil.getExcelFile(data, "classpath:templates/xszz", template, fileName);
		}
		return file;
	}
	
	public void gwcjffDc(ServletOutputStream outputStream, KnsjgForm model) throws Exception {
		String title = "��ͥ�������ѣ���������ѧ�����ͳ�Ʊ�";

		List<HashMap<String,String>> dataList=dao.getKnstj_12688(model);
		List<HashMap<String,String>> dataList2=dao.getKnstj2_12688(model);
		WritableWorkbook wwb = Workbook.createWorkbook(outputStream);
		
		WritableSheet ws = wwb.createSheet(title, 0);
		
		WritableCellFormat wcf = new WritableCellFormat(); // ���쵥Ԫ���ʽ
		wcf = ExcelMethods.getWcf(WritableFont.ARIAL, 11, false,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true,Border.ALL);
		//ws.setRowView(3, 1500, false);  
		// ����ͷ
		ExcelMB excel = new ExcelMB();
		excel.printTitle(ws, title, 30, 800);// ����
		ws.mergeCells(0, 1, 0, 2);
		ws.addCell(new Label(0, 1, "�꼶/����/ϵ��", wcf));
		
		List<String>nj=dao.getNj();
		List<String>xy=dao.getXy();
		
		for(int j=0;j<nj.size();j++){
			ws.addCell(new Label(0, j+3, nj.get(j)+"��", wcf));
		}
		xy.add("�ϼ�");
		for(int i=0; i < xy.size(); i++){
			ws.mergeCells(i*4+1, 1, i*4+4, 1);
			ws.addCell(new Label(i*4+1, 1, xy.get(i), wcf));
			ws.addCell(new Label(i*4+1, 2, "ѧ����", wcf));
			ws.mergeCells(i*4+2, 2, i*4+3, 2);
			ws.addCell(new Label(i*4+2, 2, "ƶ������", wcf));
			ws.addCell(new Label(i*4+4, 2, "��������", wcf));
			ws.mergeCells(i*4+1, nj.size()+4, i*4+4, nj.size()+4);
			ws.mergeCells(i*4+1, nj.size()+5, i*4+4, nj.size()+5);
		}
		
		ws.addCell(new Label(0, nj.size()+3, "С��", wcf));
		ws.addCell(new Label(0, nj.size()+4, "��Ժϵ��ƶ������ռȫԺƶ������������", wcf));
		ws.addCell(new Label(0, nj.size()+5, "��Ժϵ����������ռȫԺ��������������", wcf));
		
		HashMap<String,String> temp=null;
		for(int j=0,count=0;j<nj.size()+1;j++){
			for(int i=0; i < xy.size(); i++,count++){
				temp=dataList.get(count);
				ws.addCell(new Label(i*4+1, j+3, temp.get("rs"), wcf));
				ws.addCell(new Label(i*4+2, j+3, temp.get("pks"), wcf));
				ws.addCell(new Label(i*4+3, j+3, temp.get("pksbfb"), wcf));
				ws.addCell(new Label(i*4+4, j+3, temp.get("tks"), wcf));
			}
		}
		//��Ժϵƶ����/������ռ��
		for(int i=0,count=0; i < xy.size()-1; i++,count++){
			temp=dataList2.get(count);
			ws.addCell(new Label(i*4+1, nj.size()+4, temp.get("pksbfb"), wcf));
			ws.addCell(new Label(i*4+1, nj.size()+5, temp.get("tksbfb"), wcf));
		}
		
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	
	}
	
	/**
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws SecurityException 
	 * @throws IllegalArgumentException 
	 * 
	 * @����: ��ȡ���ݹ��̵��������������
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-9-26 ����11:33:02
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getXzyyExportData(KnsjgForm t, User user) throws Exception{
		return dao.getXzyyExportData(t, user);
	}
	

	/**
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws SecurityException 
	 * @throws IllegalArgumentException 
	 * 
	 * @����: �㽭��ҽҩ���Ի�����
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-9-26 ����11:33:02
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getZjzyyExportData(KnsjgForm t, User user) throws Exception{
		return dao.getZjzyyExportData(t, user);
	}
	
	/** 
	 * @����:��ȡѧ�������϶�����(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-10-18 ����09:53:40
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws 
	 */
	public HashMap<String, String> getXsrddc(String xh){
		return dao.getXsrddc(xh);
	}
	/**
	 * @description	�����ɽ����մɻ��ܱ�
	 * @author 		�� CP��1352��
	 * @date 		��2017-12-1 ����02:25:15
	 * @param resultList
	 * @param os
	 * @param user
	 * @throws Exception
	 */
	public void rdhzbExport(List<HashMap<String, String>> resultList,
			OutputStream os, User user) throws Exception {
		WritableWorkbook wwb = Workbook.createWorkbook(os);
		WritableSheet ws = wwb.createSheet("��������ѧ�����ܱ�", 0);
		WritableCellFormat wcf2 = new WritableCellFormat(); // ���쵥Ԫ���ʽ
		wcf2 = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false, Alignment.CENTRE, VerticalAlignment.CENTRE, false, Border.ALL);
		// ������
		ExcelMB ex = new ExcelMB();
		ws.mergeCells(0, 0, 8, 0);
		ws.mergeCells(0, 1, 8, 1);
		ex.printToOneCell_multy(ws, "�����մɹ�������ְҵ����ѧԺ��������ѧ�����ܱ�", 0, 0, 15, true,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, 700,
				Border.ALL);
		ex.printToOneCell_multy(ws, "�����ˣ�                                                   ", 0, 1, 8, true,
						Alignment.LEFT, VerticalAlignment.CENTRE, true, 700,
						Border.ALL);

		ws.addCell(new Label(0, 2, "���", wcf2));
		ws.addCell(new Label(1, 2, "����", wcf2));
		ws.addCell(new Label(2, 2, "���֤������", wcf2));
		ws.addCell(new Label(3, 2, "���֤����", wcf2));
		ws.addCell(new Label(4, 2, "�϶����Ѽ�������", wcf2));
		ws.addCell(new Label(5, 2, "�϶�ʱ��", wcf2));
		ws.addCell(new Label(6, 2, "�϶�ԭ��", wcf2));
		ws.addCell(new Label(7, 2, "�༶�϶����", wcf2));
		ws.addCell(new Label(8, 2, "�꼶�϶����", wcf2));
		if (resultList != null && resultList.size() > 0) {
			for (int i = 0; i < resultList.size(); i++) {
				HashMap<String, String> map = resultList.get(i);
				ws.setColumnView(0, 5);
				ws.setColumnView(1, 10);
				ws.setColumnView(2, 15);
				ws.setColumnView(3, 20);
				ws.setColumnView(4, 15);
				ws.setColumnView(5, 15);
				ws.setColumnView(6, 15);
				ws.setColumnView(7, 25);
				ws.setColumnView(8, 25);
				ws.addCell(new Label(0, 3 + i, i+1+"", wcf2));// ���
				ws.addCell(new Label(1, 3 + i, map.get("xm"), wcf2));
				ws.addCell(new Label(2, 3 + i, "�������֤", wcf2));
				ws.addCell(new Label(3, 3 + i, map.get("sfzh"), wcf2));
				ws.addCell(new Label(4, 3 + i, map.get("dcmc"), wcf2));
				ws.addCell(new Label(5, 3 + i, DateUtils.getDayOfLongt(map.get("sqsj").substring(0,10)), wcf2));
				ws.addCell(new Label(6, 3 + i, map.get("ylzd9"), wcf2));
				ws.addCell(new Label(7, 3 + i, "��ͥ���������ʵ�������϶�", wcf2));
				ws.addCell(new Label(8, 3 + i, "��ͥ���������ʵ�������϶�", wcf2));
				
			}
		}
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	/**
	 * 
	 * @����: ��ȡѧԺ����
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-12-18 ����10:20:08
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xydm
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getXymc(String xydm){
		return dao.getXymc(xydm);
	}
	
	/**
	 * @description	�� �������
	 * @author 		�� CP��1352��
	 * @date 		��2018-1-15 ����01:50:42
	 * @param resultList
	 * @param outputStream
	 * @param user
	 * @throws Exception 
	 */
	public void rdhzbExport_12309(List<HashMap<String, String>> resultList,
			OutputStream os, User user) throws Exception {
		WritableWorkbook wwb = Workbook.createWorkbook(os);
		WritableSheet ws = wwb.createSheet("����ѧ���϶����ܱ�", 0);
		WritableCellFormat wcf2 = new WritableCellFormat(); // ���쵥Ԫ���ʽ
		wcf2 = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false, Alignment.CENTRE, VerticalAlignment.CENTRE, false, Border.ALL);
		// ������
		ExcelMB ex = new ExcelMB();
		ws.mergeCells(0, 0, 8, 0);
		ws.mergeCells(0, 1, 8, 1);
		ex.printToOneCell_multy(ws, "�������ѧԺ��������ѧ�����ܱ�", 0, 0, 15, true,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, 700,
				Border.ALL);
		ex.printToOneCell_multy(ws, "�����ˣ�                                                   ", 0, 1, 8, true,
						Alignment.LEFT, VerticalAlignment.CENTRE, true, 700,
						Border.ALL);

		ws.addCell(new Label(0, 2, "���", wcf2));
		ws.addCell(new Label(1, 2, "����", wcf2));
		ws.addCell(new Label(2, 2, "���֤������", wcf2));
		ws.addCell(new Label(3, 2, "���֤����", wcf2));
		ws.addCell(new Label(4, 2, "�϶����Ѽ�������", wcf2));
		ws.addCell(new Label(5, 2, "�϶�ʱ��", wcf2));
		ws.addCell(new Label(6, 2, "�϶�ԭ��", wcf2));
		ws.addCell(new Label(7, 2, "�༶�϶����", wcf2));
		ws.addCell(new Label(8, 2, "�꼶�϶����", wcf2));
		if (resultList != null && resultList.size() > 0) {
			for (int i = 0; i < resultList.size(); i++) {
				HashMap<String, String> map = resultList.get(i);
				ws.setColumnView(0, 5);
				ws.setColumnView(1, 10);
				ws.setColumnView(2, 15);
				ws.setColumnView(3, 20);
				ws.setColumnView(4, 15);
				ws.setColumnView(5, 15);
				ws.setColumnView(6, 15);
				ws.setColumnView(7, 25);
				ws.setColumnView(8, 25);
				ws.addCell(new Label(0, 3 + i, i+1+"", wcf2));// ���
				ws.addCell(new Label(1, 3 + i, map.get("xm"), wcf2));
				ws.addCell(new Label(2, 3 + i, map.get("ylzd8"), wcf2));
				ws.addCell(new Label(3, 3 + i, map.get("sfzh"), wcf2));
				ws.addCell(new Label(4, 3 + i, map.get("dcmc"), wcf2));
				ws.addCell(new Label(5, 3 + i, DateUtils.getDayOfLongt(map.get("sqsj").substring(0,10)), wcf2));
				ws.addCell(new Label(6, 3 + i, map.get("sqly"), wcf2));
				ws.addCell(new Label(7, 3 + i, "��ͥ���������ʵ�������϶�", wcf2));
				ws.addCell(new Label(8, 3 + i, "��ͥ���������ʵ�������϶�", wcf2));
			}
		}
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}

	/**
	 * @description	�� �ӱ�����ʦ��ѧԺ
	 * @author 		�� CP��1352��
	 * @date 		��2018-1-15 ����01:50:42
	 * @param resultList
	 * @param outputStream
	 * @param user
	 * @throws Exception
	 */
	public void rdhzbExport_10098(List<HashMap<String, String>> resultList,
								  OutputStream os, User user) throws Exception {
		WritableWorkbook wwb = Workbook.createWorkbook(os);
		WritableSheet ws = wwb.createSheet("����ѧ���϶����ܱ�", 0);
		WritableCellFormat wcf2 = new WritableCellFormat(); // ���쵥Ԫ���ʽ
		wcf2 = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false, Alignment.CENTRE, VerticalAlignment.CENTRE, false, Border.ALL);
		// ������
		ExcelMB ex = new ExcelMB();
		ws.mergeCells(0, 0, 8, 0);
		ex.printToOneCell_multy(ws, "�ӱ�����ʦ��ѧԺ��ͥ��������ѧ���϶����ܱ�", 0, 0, 15, true,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, 700,
				Border.ALL);
		ws.addCell(new Label(0, 1, "���", wcf2));
		ws.addCell(new Label(1, 1, "����", wcf2));
		ws.addCell(new Label(2, 1, "���֤������", wcf2));
		ws.addCell(new Label(3, 1, "���֤����", wcf2));
		ws.addCell(new Label(4, 1, "�϶����Ѽ�������", wcf2));
		ws.addCell(new Label(5, 1, "�϶�ʱ��", wcf2));
		ws.addCell(new Label(6, 1, "�϶�ԭ��", wcf2));
		ws.addCell(new Label(7, 1, "�༶�϶����", wcf2));
		ws.addCell(new Label(8, 1, "�꼶�϶����", wcf2));
		if (resultList != null && resultList.size() > 0) {
			for (int i = 0; i < resultList.size(); i++) {
				HashMap<String, String> map = resultList.get(i);
				ws.setColumnView(0, 5);
				ws.setColumnView(1, 10);
				ws.setColumnView(2, 15);
				ws.setColumnView(3, 20);
				ws.setColumnView(4, 15);
				ws.setColumnView(5, 15);
				ws.setColumnView(6, 15);
				ws.setColumnView(7, 25);
				ws.setColumnView(8, 25);
				ws.addCell(new Label(0, 2 + i, i+1+"", wcf2));// ���
				ws.addCell(new Label(1, 2 + i, map.get("xm"), wcf2));
				ws.addCell(new Label(2, 2 + i, map.get("ylzd8"), wcf2));
				ws.addCell(new Label(3, 2 + i, map.get("sfzh"), wcf2));
				ws.addCell(new Label(4, 2 + i, map.get("dcmc"), wcf2));
				ws.addCell(new Label(5, 2 + i, DateUtils.getDayOfLongt(map.get("sqsj").substring(0,10)), wcf2));
				ws.addCell(new Label(6, 2 + i, map.get("sqly"), wcf2));
				ws.addCell(new Label(7, 2 + i, "��ͥ���������ʵ�������϶�", wcf2));
				ws.addCell(new Label(8, 2 + i, "��ͥ���������ʵ�������϶�", wcf2));
			}
		}
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	/**
	 * @description	�� �������϶�����
	 * @author 		�� lj��1282��
	 * @date 		��2018-4-24 ����05:52:48
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception 
	 */
	public List<HashMap<String,String>> knsrddc(KnsjgForm t,User user) throws Exception{
		return dao.knsrddc(t,user);
	}
	
	/**
	 * @description	�� ��ͥ��Ϣ����
	 * @author 		�� lj��1282��
	 * @date 		��2018-4-25 ����11:02:00
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String,String>> jtxxdc(KnsjgForm t,User user) throws Exception{
		return dao.jtxxdc(t, user);
	}
	
	/**
	 * @description	�� ������������������
	 * @author 		�� lj��1282��
	 * @date 		��2018-4-25 ����03:07:03
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String,String>> knsjdlkdc(KnsjgForm t,User user) throws Exception{
		return dao.knsjdlkdc(t, user);
	}
	public HashMap<String, String> getKnsInfo(String xh,String xn){
		return dao.getKnsInfo(xh,xn);
	}
}
