/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-8-7 ����04:42:04 
 */
package com.zfsoft.xgxt.rcsw.rcxwwh.rcxwjg;

import java.io.File;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.commons.io.FileUtils;
import org.apache.struts.upload.FormFile;

import xgxt.DAO.ExcelMB;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.ExcelMethods;
import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.rcsw.rcxwwh.rcxwdmwh.RcxwdmwhDao;
import com.zfsoft.xgxt.rcsw.rcxwwhnew.rcxwjg.OtmMapping;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �ճ���Ϊ����ģ��
 * @�๦������: �ճ���Ϊ���
 * @���ߣ� dlq [���ţ�995]
 * @ʱ�䣺 2013-8-7 ����04:42:04
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class RcxwjgService extends SuperServiceImpl<RcxwjgForm, RcxwjgDao> {

	private RcxwjgDao dao = new RcxwjgDao();
	private ResourceBundle resource = ResourceBundle.getBundle("config/fileUploadConfig");
	private static final String PRIFEX_ZF = "ZFXG_UPLOADFILES";
	private final String[] fileTypes = new String[]{"png","gif","jpg","jpeg",
			"zip","rar","pdf","txt","doc","docx","xls","xlsx"};

	public RcxwjgService() {
		super.setDao(dao);
	}
	
	/**
	 * 
	 * ��ȡ��Ϊ���༯��
	 * @���ߣ�dlq [���ţ�995]
	 * @���ڣ�2013-8-9 ����03:58:50
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param request
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getXwdlList(HttpServletRequest request) {
		return dao.getXwdlList(request);
	}
	
	/**
	 * 
	 * ������Ϊ���
	 * @���ߣ�Dlq [���ţ�995]
	 * @���ڣ�2013-8-13 ����04:31:51
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean saveXwjg(RcxwjgForm model) throws Exception {
		String[] xwlbdmArr=model.getXwlbdmArr();
		String[] fzArray=model.getFzArray();
		String[] xwdldmArr=model.getXwdldmArr();
		String[] fssjArr=model.getFssjArr();
		String[] gflyArr=model.getGflyArr();
		//����
		Hashtable files = model.getMultipartRequestHandler().getFileElements();
		
		boolean flag=true;
		for(int i=0;i<xwlbdmArr.length;i++){
			model.setRcxwlbdm(xwlbdmArr[i]);
			model.setRcxwlbdldm(xwdldmArr[i]);
			model.setFz(fzArray[i]);
			model.setFssj(fssjArr[i]);
			model.setGfly(gflyArr[i]);
			
			//������
			FormFile file = (FormFile) files.get("lbfj"+i);
			
			if (file != null && !StringUtil.isNull(file.getFileName())){
				String basePath = resource.getString("filesys.local.dir");
				//���û�и����ļ��洢·������Ĭ�ϸ�ϵͳ�û�Ŀ¼
				if(StringUtils.isNull(basePath)){
					basePath = System.getProperty("user.home") +"/" + PRIFEX_ZF;
				}
				String prex = file.getFileName().substring(file.getFileName().lastIndexOf("."));
				
				if (StringUtils.getStrIndexInArray(prex.replace(".", ""), fileTypes) > -1 && file.getFileSize() <= 1024*1024*5){
					File srcFile = FileUtil.conversionFormFile(file);
					File destFile = new File(basePath+"/"+System.currentTimeMillis()+prex);
					FileUtils.copyFile(srcFile, destFile);
					model.setFjlj(destFile.getAbsolutePath());
					model.setFjmc(file.getFileName());
				}
			}
			
			boolean insertResult = super.runInsert(model);
			if(!insertResult){
				flag=insertResult;
			}
		}
		return flag;
	}
	
	/**
	 * 
	 * @����:��ȡ��Ϊ��𼯺�
	 * @���ߣ�Dlq [���ţ�995]
	 * @���ڣ�2013-8-13 ����04:47:22
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param jldldm
	 * @param request
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getXwlbList(String jldldm,
			HttpServletRequest request) {
		return dao.getXwlbList(jldldm, request);
	}
	
	/**
	 * 
	 * @����:������Ϊ���
	 * @���ߣ�tgj [���ţ�1075]
	 * @���ڣ�2014-12-13 ����12:05:44
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean insertXwjg(RcxwjgForm model) throws Exception {
		
		Hashtable files = model.getMultipartRequestHandler().getFileElements();
		//������
		FormFile file = (FormFile) files.get("lbfj");
		
		if (file != null && !StringUtil.isNull(file.getFileName())){
			String basePath = resource.getString("filesys.local.dir");
			//���û�и����ļ��洢·������Ĭ�ϸ�ϵͳ�û�Ŀ¼
			if(StringUtils.isNull(basePath)){
				basePath = System.getProperty("user.home") +"/" + PRIFEX_ZF;
			}
			String prex = file.getFileName().substring(file.getFileName().lastIndexOf("."));
			
			if (StringUtils.getStrIndexInArray(prex.replace(".", ""), fileTypes) > -1 && file.getFileSize() <= 1024*1024*5){
				File srcFile = FileUtil.conversionFormFile(file);
				File destFile = new File(basePath+"/"+System.currentTimeMillis()+prex);
				FileUtils.copyFile(srcFile, destFile);
				model.setFjlj(destFile.getAbsolutePath());
			}
		} else if (file != null) {
			model.setFjlj("");
		}
		
		boolean insertResult = super.runInsert(model);
		return insertResult;
	}
	
	/**
	 * 
	 * @����:������Ϊ���
	 * @���ߣ�Dlq [���ţ�995]
	 * @���ڣ�2013-8-13 ����04:31:06
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean updateXwjg(RcxwjgForm model) throws Exception {
		
		Hashtable files = model.getMultipartRequestHandler().getFileElements();
		//������
		FormFile file = (FormFile) files.get("lbfj");
		
		if (file != null && !StringUtil.isNull(file.getFileName())){
			String basePath = resource.getString("filesys.local.dir");
			//���û�и����ļ��洢·������Ĭ�ϸ�ϵͳ�û�Ŀ¼
			if(StringUtils.isNull(basePath)){
				basePath = System.getProperty("user.home") +"/" + PRIFEX_ZF;
			}
			String prex = file.getFileName().substring(file.getFileName().lastIndexOf("."));
			
			if (StringUtils.getStrIndexInArray(prex.replace(".", ""), fileTypes) > -1 && file.getFileSize() <= 1024*1024*5){
				File srcFile = FileUtil.conversionFormFile(file);
				File destFile = new File(basePath+"/"+System.currentTimeMillis()+prex);
				FileUtils.copyFile(srcFile, destFile);
				model.setFjlj(destFile.getAbsolutePath());
				model.setFjmc(file.getFileName());
			}
		} else if (file != null) {
			model.setFjlj("");
		}
		
		boolean updateResult = super.runUpdate(model);
		return updateResult;
	}

	/**
	 * 
	 * �鿴��Ϊ���
	 * 
	 * @���ߣ�dlq [���ţ�995]
	 * @���ڣ�2013-8-9 ����10:33:25
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param guid
	 * @return
	 * @throws Exception
	 *             Map<String,String> ��������
	 * @throws
	 */
	public Map<String, String> getOneXwjgList(String xwjgId) throws Exception {
		return dao.getOneXwjgList(xwjgId);
	}
	/**
	 * 
	 * @����:��ȡ����Ϊ�����������
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-4-9 ����11:30:33
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String,String> getCspz(){
		return dao.getCspz();
	}
	
	
	/**
	 * 
	 * �鿴�ѻ���Ϊ��¼��Ϣ
	 * @���ߣ�dlq [���ţ�995]
	 * @���ڣ�2013-8-9 ����10:34:23
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param guid
	 * @return
	 * Map<String,String> �������� 
	 * @throws
	 */
	public ArrayList<String[]>  getMoreXwjgList(RcxwjgForm model,HashMap<String,String> cspzMap)throws Exception {
		return dao.getMoreXwjgList(model,cspzMap);
	}
	
	/**
	 * �жϸ��ĺ����Ϊ��Ϣ�Ƿ������Ϊ������� 
	 * @���ߣ�dlq [���ţ�995]
	 * @���ڣ�2013-8-12 ����05:12:09
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xwjgId
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean checkXwwhForxwjg(String xwjgId) throws Exception {
		boolean updateResult = dao.checkXwwhForxwjg(xwjgId);
		return updateResult;
	}
	
	/**
	 * 
	 * ѧ���ܹ��鿴���Լ��Ĳ��зַ���������ؼ�¼
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2013-10-16 ����10:31:14
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * @throws Exception
	 * List<String[]> �������� 
	 * @throws
	 */
	public List<String[]> getStuRcswList(String xh) throws Exception {
		return dao.getStuRcswList(xh);
	}
	
	
	/**
	 * 
	 * @����:����ѧ�Ų�ѯ�ճ�����
	 * @���ߣ�ligl
	 * @���ڣ�2013-11-30 ����03:07:04
	 * @�޸ļ�¼: 
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getRcswList(String xh) {
		HashMap<String,String> csszMap = dao.getCspz();
		return dao.getRcswList(xh,csszMap);
	}

	/** 
	 * @����: �ж���Ϣ�Ƿ��ظ�(ѧ�š�ѧ�ڡ�ѧ�ꡢ��Ϊ�б�����ʱ��)
	 * @���ߣ�HongLin[���ţ�707]
	 * @���ڣ�2014-2-24 ����05:47:55
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param request
	 * @return
	 * String �������� 
	 * @throws 
	 */
	public String getRcxwxxSfcf(HttpServletRequest request,String xh,String xn,String xq,String xwlbStr,String fssjStr){
		String [] xwlbArr = xwlbStr.split("!!");
		String [] fssjArr = fssjStr.split("!!");
		List<HashMap<String,String>> list = dao.getRcxwxxSfcf(request,xh,xn,xq,xwlbArr,fssjArr);
		String message = "��";
		if(list!=null && list.size()>0){
			message = "��Ϊ��¼��Ϣ���ظ���¼��<br/>";
			for (int i=0;i<list.size();i++){
				if(i==(list.size()-1)){
					message+="����Ϊ���"+list.get(i).get("rcxwlbmc")+"������ʱ�䣺"+list.get(i).get("fssj")+"��";	
				}else{
					message+="����Ϊ���"+list.get(i).get("rcxwlbmc")+"������ʱ�䣺"+list.get(i).get("fssj")+"����";
				}
				
			}
			message+="����ȷ�ϣ�";
		}
		return message;
	}

	/**
	 * 
	 * @����:�´�����Ʒ��ʵ������������
	 * @���ߣ�xiaxia [���ţ�1104]
	 * @���ڣ�2014-12-1 ����03:11:03
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param lddm
	 * @param os
	 * @throws Exception
	 * void �������� 
	 * @throws
	 */
	public void xsPxsjDc(RcxwjgForm myForm,OutputStream os, User user)throws Exception{
		// �߼���ѯMODEL
		SearchModel searchModel = myForm.getSearchModel();
		String yqdm = searchModel.getSearch_tj_yqdm()[0];
		String xn = searchModel.getSearch_tj_xn()[0];
		String xq = searchModel.getSearch_tj_xq()[0];
		List<HashMap<String,String>> resultList = dao.getpxfList(yqdm, xn, xq);
		String xqmc = dao.getXqmc(xq);
		String yqmc = dao.getYqmc(yqdm);
		// ���ñ���
		StringBuffer title = new StringBuffer();
		title.append(xn+xqmc+"���ݴ�ѧ"+yqmc+"����Ʒ��ʵ��������");

		WritableWorkbook wwb = Workbook.createWorkbook(os);

		WritableSheet ws = wwb.createSheet("����Ʒ��ʵ��������", 0);

		WritableCellFormat wcf2 = new WritableCellFormat(); // ���쵥Ԫ���ʽ
		wcf2 = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, Border.ALL);

		// ������
		ExcelMB ex = new ExcelMB();
		ws.mergeCells(0, 0, 8, 1);
		ex.printToOneCell_multy(ws, title.toString(), 0, 0, 17, true,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, 700,
				Border.NONE);			
			ws.addCell(new Label(0, 2, "¥��", wcf2));
			ws.addCell(new Label(1, 2, "���Һ�", wcf2));
			ws.addCell(new Label(2, 2, "����", wcf2));
			ws.addCell(new Label(3, 2, "�༶", wcf2));
			ws.addCell(new Label(4, 2, "������ò", wcf2));
			ws.addCell(new Label(5, 2, "Ʒ�л�����80��", wcf2));
			ws.addCell(new Label(6, 2, "Ʒ��ʵ���ܷ�", wcf2));
			ws.addCell(new Label(7, 2, "�ӷ���Ŀ", wcf2));
			ws.addCell(new Label(8, 2, "������Ŀ", wcf2));
			if (resultList != null && resultList.size() > 0) {
				for (int i = 0; i < resultList.size(); i++) {
					HashMap<String, String> map = resultList.get(i);
					ws.setColumnView(0, 10);
					ws.setColumnView(1, 10);
					ws.setColumnView(2, 10);
					ws.setColumnView(3, 20);
					ws.setColumnView(4, 20);
					ws.setColumnView(5, 10);
					ws.setColumnView(6, 10);
					ws.setColumnView(7, 30);
					ws.setColumnView(8, 30);
					ws.addCell(new Label(0, 3 + i, map.get("ldmc"), wcf2));
					ws.addCell(new Label(1, 3 + i, map.get("qsh"), wcf2));
					ws.addCell(new Label(2, 3 + i, map.get("xm"), wcf2));
					ws.addCell(new Label(3, 3 + i, map.get("bjmc"), wcf2));
					ws.addCell(new Label(4, 3 + i, map.get("zzmmmc"), wcf2));
					ws.addCell(new Label(5, 3 + i, map.get("zfs"), wcf2));
					ws.addCell(new Label(6, 3 + i, map.get("sjfz"), wcf2));
					ws.addCell(new Label(7, 3 + i, map.get("plusxm"), wcf2));
					ws.addCell(new Label(8, 3 + i, map.get("cutxm"), wcf2));
		}
		}
			ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	/**
	 * 
	 * @����: �ճ���Ϊ���ݵ�����2�����������ʲ�����˼��������ʲ���-�㽭ҽѧ���Ի���
	 * @���ߣ������� [���ţ�1123]
	 * @���ڣ�2015-1-20 ����05:01:20
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param myForm
	 * @param os
	 * @param user
	 * @throws Exception
	 * void �������� 
	 * @throws
	 */
	public void rcxwsjDc(RcxwjgForm myForm,OutputStream os, User user)throws Exception{
		// �߼���ѯMODEL
		SearchModel searchModel = myForm.getSearchModel();
		String xn = searchModel.getSearch_tj_xn()[0];
		List<HashMap<String,String>> resultList = dao.getrcxwfList(xn);
		
		WritableWorkbook wwb = Workbook.createWorkbook(os);

		WritableSheet ws = wwb.createSheet("���ʲ����ֵ���", 0);

		WritableCellFormat wcf2 = new WritableCellFormat(); // ���쵥Ԫ���ʽ
		wcf2 = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, Border.ALL);
		
		// ���	
			ws.addCell(new Label(0, 0, "ϵ", wcf2));
			ws.addCell(new Label(1, 0, "רҵ", wcf2));
			ws.addCell(new Label(2, 0, "�༶", wcf2));
			ws.addCell(new Label(3, 0, "ѧ��", wcf2));
			ws.addCell(new Label(4, 0, "����", wcf2));
			ws.addCell(new Label(5, 0, "�����֣�����60�֣�", wcf2));
			ws.addCell(new Label(6, 0, "˼����·֣�����60�֣�", wcf2));
			ws.addCell(new Label(7, 0, "�ܷ�", wcf2));
			ws.addCell(new Label(8, 0, "���ѧ��", wcf2));
			ws.addCell(new Label(9, 0, "���˽��", wcf2));
			ws.addCell(new Label(10, 0, "��ע", wcf2));
			if (resultList != null && resultList.size() > 0) {
				for (int i = 0; i < resultList.size(); i++) {
					HashMap<String, String> map = resultList.get(i);
					ws.setColumnView(0, 20);
					ws.setColumnView(1, 30);
					ws.setColumnView(2, 30);
					ws.setColumnView(3, 15);
					ws.setColumnView(4, 15);
					ws.setColumnView(5, 15);
					ws.setColumnView(6, 15);
					ws.setColumnView(7, 10);
					ws.setColumnView(8, 10);
					ws.setColumnView(9, 20);
					ws.setColumnView(10, 50);
					ws.addCell(new Label(0, 1+i, map.get("xymc"), wcf2));
					ws.addCell(new Label(1, 1+i, map.get("zymc"), wcf2));
					ws.addCell(new Label(2, 1+i, map.get("bjmc"), wcf2));
					ws.addCell(new Label(3, 1+i, map.get("xh"), wcf2));
					ws.addCell(new Label(4, 1+i, map.get("xm"), wcf2));
					ws.addCell(new Label(5, 1+i, map.get("nlf"), wcf2));
					ws.addCell(new Label(6, 1+i, map.get("sxddf"), wcf2));
					ws.addCell(new Label(7, 1+i, map.get("zf"), wcf2));
					ws.addCell(new Label(8, 1+i, map.get("xf"), wcf2));
					ws.addCell(new Label(9, 1+i, map.get("khjg"), wcf2));
					ws.addCell(new Label(10, 1+i, map.get("bz"), wcf2));
				}
			}
			ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	public void rcxwtjbDc(RcxwjgForm myForm,OutputStream os, User user)throws Exception{
		SearchModel searchModel = myForm.getSearchModel();
		String xn = searchModel.getSearch_tj_xn()[0];
		HashMap<String,String> cspzMap = dao.getCspz();
		List<HashMap<String,String>> resultList = dao.getrcxwzfList(cspzMap,xn);
		
		WritableWorkbook wwb = Workbook.createWorkbook(os);

		WritableSheet ws = wwb.createSheet("�ճ���Ϊ��ͳ�Ƶ���", 0);

		WritableCellFormat wcf2 = new WritableCellFormat(); // ���쵥Ԫ���ʽ
		wcf2 = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, Border.ALL);
		
		// ���	
			ws.addCell(new Label(0, 0, "ѧ��", wcf2));
			ws.addCell(new Label(1, 0, "����", wcf2));
			ws.addCell(new Label(2, 0, "�꼶", wcf2));
			ws.addCell(new Label(3, 0, "ѧԺ", wcf2));
			ws.addCell(new Label(4, 0, "רҵ", wcf2));
			ws.addCell(new Label(5, 0, "�༶", wcf2));
			ws.addCell(new Label(6, 0, "ѧ��", wcf2));
			ws.addCell(new Label(7, 0, "�ճ���Ϊ�ܷ�", wcf2));
			if (resultList != null && resultList.size() > 0) {
				for (int i = 0; i < resultList.size(); i++) {
					HashMap<String, String> map = resultList.get(i);
					ws.setColumnView(0, 10);
					ws.setColumnView(1, 10);
					ws.setColumnView(2, 10);
					ws.setColumnView(3, 15);
					ws.setColumnView(4, 15);
					ws.setColumnView(5, 15);
					ws.setColumnView(6, 15);
					ws.setColumnView(7, 15);
					ws.addCell(new Label(0, 1+i, map.get("xh"), wcf2));
					ws.addCell(new Label(1, 1+i, map.get("xm"), wcf2));
					ws.addCell(new Label(2, 1+i, map.get("nj"), wcf2));
					ws.addCell(new Label(3, 1+i, map.get("xymc"), wcf2));
					ws.addCell(new Label(4, 1+i, map.get("zymc"), wcf2));
					ws.addCell(new Label(5, 1+i, map.get("bjmc"), wcf2));
					ws.addCell(new Label(6, 1+i, map.get("xn"), wcf2));
					ws.addCell(new Label(7, 1+i, map.get("fz"), wcf2));
				}
			}
			ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	/**
	 * 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-4-14 ����11:34:42
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param myForm
	 * @param os
	 * @param user
	 * @throws Exception
	 * void �������� 
	 * @throws
	 */
	public void rcxwdlfDc(RcxwjgForm myForm,OutputStream os, User user)throws Exception{
		List<HashMap<String,String>> xwdlList = new RcxwdmwhDao().getRcxwdlList();
		myForm.getPages().setPageSize(Integer.MAX_VALUE);
		List<HashMap<String,String>> resultList = dao.getXwdlfList(myForm,user,xwdlList);
		
		WritableWorkbook wwb = Workbook.createWorkbook(os);

		WritableSheet ws = wwb.createSheet("�ճ���Ϊ�����ͳ�Ƶ���", 0);

		WritableCellFormat wcf2 = new WritableCellFormat(); // ���쵥Ԫ���ʽ
		wcf2 = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, Border.ALL);
		
		// ���	
			ws.addCell(new Label(0, 0, "ѧ��", wcf2));
			ws.addCell(new Label(1, 0, "����", wcf2));
			ws.addCell(new Label(2, 0, "�꼶", wcf2));
			ws.addCell(new Label(3, 0, "ѧԺ", wcf2));
			ws.addCell(new Label(4, 0, "רҵ", wcf2));
			ws.addCell(new Label(5, 0, "�༶", wcf2));
			ws.addCell(new Label(6, 0, "ѧ��", wcf2));
			for (int i = 0; i < xwdlList.size(); i++) {
				ws.addCell(new Label(i+7, 0,xwdlList.get(i).get("rcxwlbdlmc") , wcf2));
			}
			if (resultList != null && resultList.size() > 0) {
				for (int i = 0; i < resultList.size(); i++) {
					HashMap<String, String> map = resultList.get(i);
					ws.setColumnView(0, 10);
					ws.setColumnView(1, 10);
					ws.setColumnView(2, 10);
					ws.setColumnView(3, 15);
					ws.setColumnView(4, 15);
					ws.setColumnView(5, 15);
					ws.setColumnView(6, 15);
					ws.addCell(new Label(0, 1+i, map.get("xh"), wcf2));
					ws.addCell(new Label(1, 1+i, map.get("xm"), wcf2));
					ws.addCell(new Label(2, 1+i, map.get("nj"), wcf2));
					ws.addCell(new Label(3, 1+i, map.get("xymc"), wcf2));
					ws.addCell(new Label(4, 1+i, map.get("zymc"), wcf2));
					ws.addCell(new Label(5, 1+i, map.get("bjmc"), wcf2));
					ws.addCell(new Label(6, 1+i, map.get("xn"), wcf2));
					for (int j = 0; j < xwdlList.size(); j++) {
						ws.setColumnView(j+7, 15);
						ws.addCell(new Label(j+7, 1+i, map.get("fz"+j), wcf2));
					}
				}
			}
			ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	/**
	 * @throws Exception 
	 * 
	 * @����:�ճ���Ϊ����ֻ���
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-4-13 ����03:37:03
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getXwdlfList(RcxwjgForm model,User user) throws Exception{
		RcxwdmwhDao rcxwdmwhDao = new RcxwdmwhDao();
		List<HashMap<String,String>> xmdlList = rcxwdmwhDao.getRcxwdlList();
		return dao.getXwdlfList(model,user,xmdlList);
		
	}
	
	public boolean getCffWarnStudent(String xh){
		return dao.getCffWarnStudent(xh);
	}

	/**
	 * @����:�ൺ�������Ի�����ѯ˼��Ʒ�¿γɼ����ܱ�����
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��3��31�� ����9:49:17
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getSxpdcjhzList(RcxwjgForm model, User user) throws Exception {
		return dao.getSxpdcjhzList(model, user);
	}

	/**
	 * @����:�ൺ�������Ի�������˼��Ʒ�¿γɼ����ܱ�
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��3��31�� ����9:02:41
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param resultList
	 * @return
	 * File �������� 
	 * @throws Exception
	 */
	public File getSxpdcjhzFile(List<HashMap<String,String>> resultList) throws Exception {
		
		Map<String,List<Map<String,String>>> classMap = new OtmMapping().setResultMap("bjdm", resultList).getResultMap();
		
		//��Excel
		String fileName = System.currentTimeMillis() + ".xls";
		File file = new File(System.getProperty("java.io.tmpdir"),fileName);
		
		if(!file.exists()){
			file.createNewFile();
		}	
		
		//����������
		WritableWorkbook  wwb = Workbook.createWorkbook(file);
		
		//������ظ�ʽ
		WritableFont tileFont = new WritableFont(WritableFont.ARIAL, 14, WritableFont.BOLD, false,UnderlineStyle.NO_UNDERLINE);//���ñ�������
		WritableFont headFont = new WritableFont(WritableFont.ARIAL, 12, WritableFont.BOLD, false,UnderlineStyle.NO_UNDERLINE);//���ñ�ͷ����
		WritableFont bodyFont = new WritableFont(WritableFont.ARIAL, 10,WritableFont.NO_BOLD,false,UnderlineStyle.NO_UNDERLINE);//������������
		
		WritableCellFormat title_cf = new WritableCellFormat(tileFont);//���ñ��ⵥԪ������
		WritableCellFormat head_cf = new WritableCellFormat(headFont);//�������ı�ͷ����
		WritableCellFormat body_cf = new WritableCellFormat(bodyFont);//�������ĵ�Ԫ������
		WritableCellFormat body_bz_cf = new WritableCellFormat(bodyFont);//�������ĵ�Ԫ������
		
		title_cf.setAlignment(jxl.format.Alignment.CENTRE);//���ñ��ⵥԪ�����
		title_cf.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK);//���ñ��ⵥԪ��߿�
//		title_cf.setBackground(Colour.YELLOW);	//���ñ��ⱳ��ɫ
		
		head_cf.setAlignment(jxl.format.Alignment.CENTRE);
		head_cf.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);//���ñ�ͷˮƽ����
		head_cf.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK);
		
		body_cf.setAlignment(jxl.format.Alignment.CENTRE);//�������ĵ�Ԫ�����
		body_cf.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK);//�������ĵ�Ԫ��߿�
		body_bz_cf.setAlignment(jxl.format.Alignment.LEFT);
		body_bz_cf.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK);
		 
		Collection<List<Map<String,String>>> classCollection = classMap.values(); //���ѧ���б��ϣ��԰༶Ϊ��λ
		Iterator<List<Map<String,String>>> iterator = classCollection.iterator();
		
		int i = 0;
		while(iterator.hasNext()){
			List<Map<String,String>> list = iterator.next();	//�༶��Ӧ�����ѧ���б�
			String bjmc = list.get(0).get("bjmc");	//�༶����
			String xn = list.get(0).get("xn");		//ѧ��
			String xqmc = list.get(0).get("xqmc");	//ѧ������
			String title = "�ൺ����ѧԺѧ��˼��Ʒ�¿γɼ����ܱ�";	//����
			String title_tip = bjmc+" / "+xn+"ѧ��� / "+xqmc; //����˵��
			
			if(StringUtils.isNull(bjmc)) bjmc = "δ֪�༶";
			
			//����������
			WritableSheet sheet = wwb.createSheet(bjmc, i);
			
			//���ø����п�
			sheet.setColumnView(0, 10);
			sheet.setColumnView(1, 14);
			sheet.setColumnView(2, 10);
			sheet.setColumnView(3, 10);
			sheet.setColumnView(4, 10);
			sheet.setColumnView(5, 10);
			sheet.setColumnView(6, 10);
			sheet.setColumnView(7, 10);
			sheet.setColumnView(8, 10);
			sheet.setColumnView(9, 10); 
			sheet.setColumnView(10, 10);
			sheet.setColumnView(11, 14);
			
			//�ϲ���Ԫ��
			sheet.mergeCells(0, 0, 11, 0);	//�ൺ����ѧԺѧ��˼��Ʒ�¿γɼ����ܱ�
			sheet.mergeCells(0, 1, 11, 1);	//xx�༶  xxѧ��  xxѧ��
			sheet.mergeCells(0, 2, 0, 3);	//����
			sheet.mergeCells(1, 2, 1, 3);	//ѧ��
			sheet.mergeCells(2, 2, 2, 3);	//����
			sheet.mergeCells(3, 2, 7, 2);	//�ճ���Ϊ���ַ�
			sheet.mergeCells(8, 2, 9, 2);	//���ۿγɼ�
			sheet.mergeCells(10, 2, 10, 3);	//�ܷ�
			sheet.mergeCells(11, 2, 11, 3);	//��ע
			
			//�������⼰��ͷ
			Label t_0_0 = new Label(0, 0,title,title_cf);
			Label h_0_1 = new Label(0,1,title_tip,head_cf);
			Label h_0_2 = new Label(0,2,"����",head_cf);
			Label h_1_2 = new Label(1,2,"ѧ��",head_cf);
			Label h_2_2 = new Label(2,2,"����",head_cf);
			Label h_3_2 = new Label(3,2,"�ճ���Ϊ���ַ�",head_cf);
			Label h_3_3 = new Label(3,3,"������",head_cf);
			Label h_4_3 = new Label(4,3,"�ӷ�",head_cf);
			Label h_5_3 = new Label(5,3,"����",head_cf);
			Label h_6_3 = new Label(6,3,"�ܷ�",head_cf);
			Label h_7_3 = new Label(7,3,"60%",head_cf);
			Label h_8_2 = new Label(8,2,"���ۿγɼ�",head_cf);
			Label h_8_3 = new Label(8,3,"�ܷ�",head_cf);
			Label h_9_3 = new Label(9,3,"40%",head_cf);
			Label h_10_2 = new Label(10,2,"�ܷ�",head_cf);
			Label h_11_2 = new Label(11,2,"��ע",head_cf);
			
			sheet.addCell(t_0_0);
			sheet.addCell(h_0_1);
			sheet.addCell(h_0_2);
			sheet.addCell(h_1_2);
			sheet.addCell(h_2_2);
			sheet.addCell(h_3_2);
			sheet.addCell(h_3_3);
			sheet.addCell(h_4_3);
			sheet.addCell(h_5_3);
			sheet.addCell(h_6_3);
			sheet.addCell(h_7_3);
			sheet.addCell(h_8_2);
			sheet.addCell(h_8_3);
			sheet.addCell(h_9_3);
			sheet.addCell(h_10_2);
			sheet.addCell(h_11_2);
			
			//����������Ԫ��
			if(list.size()>0){
				for(int j=0;j<list.size();j++){
					Map<String,String> map = list.get(j);
					Label rank = new Label(0, j+4, map.get("rank"), body_cf);	//����
					Label xh = new Label(1, j+4, map.get("xh"), body_cf);		//ѧ��
					Label xm = new Label(2, j+4, map.get("xm"), body_cf);		//����
					Label jcf = new Label(3, j+4, map.get("jcf"), body_cf);		//������
					Label pf = new Label(4, j+4, map.get("pf"), body_cf);		//�ӷ�
					Label mf = new Label(5, j+4, map.get("mf"), body_cf);		//����
					Label tf = new Label(6, j+4, map.get("tf"), body_cf);		//�����ܷ�
					Label tf_p6 = new Label(7, j+4, map.get("tf_p6"), body_cf);	//�����ܷ�60%
					Label llf = new Label(8, j+4, map.get("llf"), body_cf);		//���۷��ܷ�
					Label llf_p4 = new Label(9, j+4, map.get("llf_p4"), body_cf);		//���۷��ܷ�40%
					Label zf = new Label(10, j+4, map.get("zf"), body_cf);		//�ܷ�
					Label bz = new Label(11, j+4, "", body_cf);		//��ע
					
					sheet.addCell(rank);
					sheet.addCell(xh);
					sheet.addCell(xm);
					sheet.addCell(jcf);
					sheet.addCell(pf);
					sheet.addCell(mf);
					sheet.addCell(tf);
					sheet.addCell(tf_p6);
					sheet.addCell(llf);
					sheet.addCell(llf_p4);
					sheet.addCell(zf);
					sheet.addCell(bz);
				}
			}
			i++;
		}
		
		//�������Ϊ��
		if(classCollection==null||classCollection.size()==0){
			//����������
			WritableSheet sheet = wwb.createSheet("���ε�������Ϊ��", 0);
			sheet.setColumnView(0, 15);
			Label msg = new Label(0, 0,"�������ݣ�");
			sheet.addCell(msg);
		}
		 
		wwb.write();
		wwb.close();
			
		return file;
	}
	
	/**
	 * @����: ���ݻ�ȡ��id��ѯ�����Ϣ
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2017-10-14 ����11:01:51
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param qjsqid
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String> getKptzsForId(String id){
		return dao.getKptzsForId(id);
	}
}
