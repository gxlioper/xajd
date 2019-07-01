
package com.zfsoft.xgxt.rcsw.rcxwwhnew.rcxwjg;

import java.io.File;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.struts.upload.FormFile;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.rcsw.rcxwwhnew.rcxwdmwh.RcxwdmwhDao;
import com.zfsoft.xgxt.rcsw.rcxwwhnew.rcxwdmwh.RcxwdmwhForm;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import xgxt.DAO.ExcelMB;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.ExcelMethods;
import xgxt.utils.String.StringUtils;

/**
 * �ճ���Ϊ���
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
	 * ��ȡ��Ϊ���༯��
	 */
	public List<HashMap<String, String>> getXwdlList(HttpServletRequest request) {
		return dao.getXwdlList(request);
	}
	
	/**
	 * ������Ϊ���
	 */
	public boolean saveXwjg(RcxwjgForm model) throws Exception {
		String[] xwlbdmArr=model.getXwlbdmArr();
		String[] fzArray=model.getFzArray();
		String[] xwdldmArr=model.getXwdldmArr();
		String[] xwxldmArr=model.getXwxldmArr();
		String[] fssjArr=model.getFssjArr();
		String[] gflyArr=model.getGflyArr();
		//����
		Hashtable files = model.getMultipartRequestHandler().getFileElements();
		
		boolean flag=true;
		for(int i=0;i<xwlbdmArr.length;i++){
			model.setRcxwlbdm(xwlbdmArr[i]);
			model.setRcxwlbdldm(xwdldmArr[i]);
			model.setRcxwlbxldm(xwxldmArr[i]);
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
	 * ��ȡ��Ϊ��𼯺�
	 */
	public List<HashMap<String, String>> getXwlbList(String jldldm,
			HttpServletRequest request) {
		return dao.getXwlbList(jldldm, request);
	}
	
	/**
	 * ������Ϊ���
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
	 * ������Ϊ���
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
	 * �鿴��Ϊ���
	 */
	public Map<String, String> getOneXwjgList(String xwjgId) throws Exception {
		return dao.getOneXwjgList(xwjgId);
	}
	/**
	 * ��ȡ����Ϊ�����������
	 */
	public HashMap<String,String> getCspz(){
		return dao.getCspz();
	}
	
	/**
	 * �鿴�ѻ���Ϊ��¼��Ϣ
	 */
	public ArrayList<String[]>  getMoreXwjgList(RcxwjgForm model,HashMap<String,String> cspzMap)throws Exception {
		return dao.getMoreXwjgList(model,cspzMap);
	}
	
	/**
	 * �жϸ��ĺ����Ϊ��Ϣ�Ƿ������Ϊ�������
	 */
	public boolean checkXwwhForxwjg(String xwjgId) throws Exception {
		boolean updateResult = dao.checkXwwhForxwjg(xwjgId);
		return updateResult;
	}
	
	/**
	 * ѧ���ܹ��鿴���Լ��Ĳ��зַ���������ؼ�¼
	 */
	public List<String[]> getStuRcswList(String xh) throws Exception {
		return dao.getStuRcswList(xh);
	}
	/**
	 * ����ѧ�Ų�ѯ�ճ�����
	 */
	public List<HashMap<String,String>> getRcswList(String xh) {
		HashMap<String,String> csszMap = dao.getCspz();
		return dao.getRcswList(xh,csszMap);
	}

	/** 
	 * �ж���Ϣ�Ƿ��ظ�(ѧ�š�ѧ�ڡ�ѧ�ꡢ��Ϊ�б�����ʱ��)
	 */
	public String getRcxwxxSfcf(HttpServletRequest request,String xh,String xn,String xq,String xwxlStr,String fssjStr){
		String [] xwxlArr = xwxlStr.split("!!");
		String [] fssjArr = fssjStr.split("!!");
		List<HashMap<String,String>> list = dao.getRcxwxxSfcf(request,xh,xn,xq,xwxlArr,fssjArr);
		String message = "��";
		if(list!=null && list.size()>0){
			message = "��Ϊ��¼��Ϣ���ظ���¼��<br/>";
			for (int i=0;i<list.size();i++){
				if(i==(list.size()-1)){
					message+="����ΪС�ࣺ"+list.get(i).get("rcxwlbxlmc")+"������ʱ�䣺"+list.get(i).get("fssj")+"��";	
				}else{
					message+="����ΪС�ࣺ"+list.get(i).get("rcxwlbxlmc")+"������ʱ�䣺"+list.get(i).get("fssj")+"����";
				}
				
			}
			message+="����ȷ�ϣ�";
		}
		return message;
	}

	/**
	 * �´�����Ʒ��ʵ������������
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
	 * �ճ���Ϊ���ݵ�����2�����������ʲ�����˼��������ʲ���-�㽭ҽѧ���Ի���
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
	public void rcxwdlfDc(RcxwjgForm myForm,OutputStream os, User user)throws Exception{
		List<HashMap<String,String>> xwlbList = new RcxwdmwhDao().getRcxwlbList(new RcxwdmwhForm());
		myForm.getPages().setPageSize(Integer.MAX_VALUE);
		List<HashMap<String,String>> resultList = dao.getXwdlfList(myForm,user,xwlbList);
		
		WritableWorkbook wwb = Workbook.createWorkbook(os);

		WritableSheet ws = wwb.createSheet("�ճ���Ϊ����ͳ�Ƶ���", 0);

		WritableCellFormat wcf2 = new WritableCellFormat(); // ���쵥Ԫ���ʽ
		wcf2 = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, Border.ALL);
		
		// ���	
			ws.addCell(new Label(0, 0, "ѧ��", wcf2));
			ws.addCell(new Label(1, 0, "ѧ��", wcf2));
			ws.addCell(new Label(2, 0, "����", wcf2));
			ws.addCell(new Label(3, 0, "�Ա�", wcf2));
			ws.addCell(new Label(4, 0, "�꼶", wcf2));
			ws.addCell(new Label(5, 0, "ѧԺ", wcf2));
			ws.addCell(new Label(6, 0, "רҵ", wcf2));
			ws.addCell(new Label(7, 0, "�༶", wcf2));
			for (int i = 0; i < xwlbList.size(); i++) {
				ws.addCell(new Label(i+8, 0,xwlbList.get(i).get("rcxwlbmc") , wcf2));
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
					ws.setColumnView(7, 15);
					ws.addCell(new Label(0, 1+i, map.get("xn"), wcf2));
					ws.addCell(new Label(1, 1+i, map.get("xh"), wcf2));
					ws.addCell(new Label(2, 1+i, map.get("xm"), wcf2));
					ws.addCell(new Label(3, 1+i, map.get("xb"), wcf2));
					ws.addCell(new Label(4, 1+i, map.get("nj"), wcf2));
					ws.addCell(new Label(5, 1+i, map.get("xymc"), wcf2));
					ws.addCell(new Label(6, 1+i, map.get("zymc"), wcf2));
					ws.addCell(new Label(7, 1+i, map.get("bjmc"), wcf2));
					for (int j = 0; j < xwlbList.size(); j++) {
						ws.setColumnView(j+8, 15);
						ws.addCell(new Label(j+8, 1+i, map.get("fz"+j), wcf2));
					}
				}
			}
			ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	/**
	 * �ճ���Ϊ���ֻ���
	 */
	public List<HashMap<String,String>> getXwdlfList(RcxwjgForm model,User user) throws Exception{
		RcxwdmwhDao rcxwdmwhDao = new RcxwdmwhDao();
		List<HashMap<String,String>> xmlbList = rcxwdmwhDao.getRcxwlbList(new RcxwdmwhForm());
		return dao.getXwdlfList(model,user,xmlbList);
	}
	
	/**
	 * �ճ���Ϊ�ܷ֣�����ҽ��ר��
	 */
	public List<HashMap<String,String>> getrcxwFzxxList(String xh) {
		
		return dao.getrcxwFzxxList(xh);
	}
	
	public List<HashMap<String,String>> getrcxwFzxxListForPrint(String xh,String rcxwlbdm) {
		
		return dao.getrcxwFzxxListForPrint(xh,rcxwlbdm);
	}
	
	public String chkxwdlExists(String xh, String xn, String xq, String rcxwlbmc) {
		return dao.chkxwdlExists(xh, xn, xq, rcxwlbmc);
	}
	/**
	 * 
	 * @����:��ϸ�ֲ�ѯ
	 * @���ߣ�xiaxia
	 * @���ڣ�2015-9-23 ����04:01:17
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getRcxwMx(RcxwjgForm model) {
		
		return dao.getRcxwMx(model);
	}
	
}
