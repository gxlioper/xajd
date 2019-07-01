/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-2-22 ����01:45:55 
 */  
package com.zfsoft.xgxt.xpjpy.cpfwh;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.upload.FormFile;

import jxl.CellFeatures;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Colour;
import jxl.write.Label;
import jxl.write.WritableCellFeatures;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import xgxt.form.User;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.views.utils.ArrayUtil;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.xpjpy.cssz.CsszDao;
import com.zfsoft.xgxt.xpjpy.cssz.CsszModel;
import com.zfsoft.xgxt.xpjpy.zhcp.zcfs.ZcfsModel;
import com.zfsoft.xgxt.xpjpy.zhcp.zcxm.ZcxmDao;
import com.zfsoft.xgxt.xpjpy.zhcp.zcxm.ZcxmService;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����[����:1282]
 * @ʱ�䣺 2016-2-22 ����01:45:55 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class CpfwhService extends SuperServiceImpl<CpfwhForm, CpfwhDao>{
	CpfwhDao dao = new CpfwhDao();
	
	/** 
	 * @����:��֤����
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-2-22 ����01:53:35
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param form
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean isHaveRecord(CpfwhForm form){
		return dao.isHaveRecord(form);
	}
	
	public String getXqmc(String xqdm){
		return dao.getXqmc(xqdm);
	}
	
	
//	/** 
//	 * @����:��������ģ��
//	 * @���ߣ�����[���ţ�1282]
//	 * @���ڣ�2016-2-22 ����05:31:34
//	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
//	 * @param model
//	 * @param user
//	 * @return
//	 * @throws Exception
//	 * File �������� 
//	 * @throws 
//	 */
//	public File createImportTemplate(CpfwhForm model, User user) throws Exception{
//		
//		WritableWorkbook wwb = null;
//		
//		//�����ļ���� ����ʱĿ¼
//		File tempDir = new File(System.getProperty("java.io.tmpdir"));
//		if (!tempDir.exists()){
//			tempDir.mkdir();
//		}
//		
//		//List<HashMap<String,String>> bjxxMap = dao.getBjxxById(model,user);    
//		//���������ļ�
//		File file = new File( tempDir.getPath() + "/" +"���ֵܷ���ģ��.xls");
//		file.setWritable(true);
//		
//		try{
//			FileOutputStream stream = new FileOutputStream(file);
//			//����excel������
//			wwb = Workbook.createWorkbook(stream);
//			
//			WritableSheet ws = wwb.createSheet("���ֵܷ���ģ��", 0);
//			
//			//�̶���ͷѧ�š�����
//			ws.addCell(new Label(0, 0, "ѧ��"));
//			ws.addCell(new Label(1, 0, "ѧ��"));
//			ws.addCell(new Label(2, 0, "ѧ��"));
//			ws.addCell(new Label(3, 0, "����"));//TODO
//			
//			WritableSheet ws1 = wwb.createSheet("ѧ�ڶ�����", 1);
//			
//			List<HashMap<String, String>> xqList = getXqList();
//			
//			if(!xqList.isEmpty()){
//				WritableCellFormat wcf = new WritableCellFormat();
//				WritableFont wf = new WritableFont(WritableFont.ARIAL);
//				wf.setBoldStyle(WritableFont.BOLD);
//				wf.setPointSize(10);
//				wf.setColour(Colour.BLUE);
//				wcf.setAlignment(Alignment.LEFT);
//				wcf.setWrap(true);
//				wcf.setFont(wf);
//				
//				ws1.addCell(new Label(0, 0, "ѧ�ڴ���"));
//				ws1.addCell(new Label(1, 0, "ѧ������"));
//				for (int i = 1; i <= xqList.size(); i++) {						
//					ws1.addCell(new Label(0, i,xqList.get(i-1).get("xqdm")));
//					ws1.addCell(new Label(1, i,xqList.get(i-1).get("xqmc")));
//				}
//			}
//			wwb.write();
//			wwb.close();
//		}catch (Exception e) {
//			throw new SystemException(MessageKey.SYS_CREATE_IMPORT_TEMPLATE_FAIL);
//		}
//		
//		file.setWritable(true);
//		return file;
//	}
//	
//	public File importCpfwh(CpfwhForm model,User user) throws Exception{
//		FormFile importFile = model.getImportFile();
//		//��FormFile ת��ΪFile ����
//		File file = FileUtil.conversionFormFile(importFile);
//		
//		//��ȡExcel������
//		Workbook book = Workbook.getWorkbook(file);
//
//		WritableWorkbook wwb  =  Workbook.createWorkbook(file, book);
//		WritableSheet ws = wwb.getSheet(0);
////		//���ô�����Ϣ��ʾ���п�
////		ws.setColumnView(zcxmList.size()+2, 30);
////		
////		//�����༶ѧ��
////		String[] stus = dao.getStuById(model,user);
////		
////		//����ģ�����۲�ṹ�Ƚ���֤
////		for (int i = 0 , j = zcxmList.size() ; i < j ; i++){
////			
////			String xmdm = zcxmList.get(i).get("xmdm");
////			String xmmc = zcxmList.get(i).get("xmmc");
////			
////			CellFeatures cellFeatures = ws.getCell(2+i, 0).getCellFeatures();
////			
////			if (cellFeatures == null){
////				throw new SystemException(MessageKey.SYS_IMPORT_UNLAWFUL_TEMPLATE);
////			}
////			
////			String cellComment = cellFeatures.getComment();
////			String cellContent = ws.getCell(2+i, 0).getContents();
////			
////			//��֤�±�ͷ�Ƿ����۲�ṹ�ĵ���ģ��һ��
////			if (!xmdm.equals(cellComment.trim()) || !xmmc.equals(cellContent.trim())){
////				throw new SystemException(MessageKey.SYS_IMPORT_UNLAWFUL_TEMPLATE);
////			}
////		}
//		
//		/*
//		 * ��⵼������
//		 */
//		int rows = ws.getRows();
//		List<String[]> params = new ArrayList<String[]>();
//		
//		boolean checkResult = true;
//		CpfwhForm form = new CpfwhForm();
//		for (int i = 1 ; i < rows ; i++){
//			StringBuilder errorMessage = new StringBuilder();
//			String xn = ws.getCell(0, i).getContents();
//			String xq = ws.getCell(1, i).getContents();
//			String xh = ws.getCell(2, i).getContents();
//			String fs = ws.getCell(3, i).getContents();
//			
//			if (StringUtil.isNull(xn) || StringUtil.isNull(xq) || StringUtil.isNull(xn) || StringUtil.isNull(xn)){
//				//ѧ�ź�������ô����Ϊ���أ�
//				errorMessage.append(MessageUtil.getText(MessageKey.PJPY_CPFWH_YANZHEN));
//			}else{				
//				boolean checkFlag = true;
//				String bjdm = dao.getBjdmForStu(xh);
//				List<String> bjdmList = dao.getBjdmForTeacher(user);
//				if(!bjdmList.contains(bjdm)){
//					checkFlag = false;
//					errorMessage.append(MessageUtil.getText(MessageKey.PJPY_CPFWH_XHNULL));
//				}else{
//					form.setXh(xh);
//					form.setXn(xn);
//					form.setXq(xq);
//					if(dao.isHaveRecord(form)){
//						checkFlag = false;
//						errorMessage.append(MessageUtil.getText(MessageKey.PJPY_CPFWH_REPEAT));
//					}
//				}
//				if(!checkFlag){
//					errorMessage.append(MessageUtil.getText(MessageKey.PJPY_IMPORT_ZCFS_MORETHEN_ZDDJ,
//							new Object[]{xmfz}));
//					break;
//				}
//			
//					//�ռ�����
//					String[] param = new String[]{xh,zcxmList.get(m).get("xn"),
//												  zcxmList.get(m).get("xq"),
//												  zcxmList.get(m).get("xmdm"),
//												  xmfz,user.getUserName(),
//												  xh,zcxmList.get(m).get("xmdm")};
//					params.add(param);
//				}
//			}
//			
//			
//			//������Ϣ׷��
//			if (errorMessage.length() > 0){
//				
//				WritableCellFormat wcf = new WritableCellFormat();
//				WritableFont wf = new WritableFont(WritableFont.ARIAL);
//				wf.setColour(Colour.RED);
//				wcf.setFont(wf);
//				wcf.setAlignment(Alignment.CENTRE);
//				ws.addCell(new Label(zcxmList.size()+2, i, errorMessage.toString(),wcf));
//				checkResult = false;
//			}
//		}
//		
//		
//		//��֤OK�ĵ��룬ʧ�ܵĵ���
//		if(!params.isEmpty()){
//			System.out.println("action========"+System.currentTimeMillis());
//			dao.batchInsertZcfs(params);//��������
//			System.out.println("end=========="+System.currentTimeMillis());
//		}
//		
//		//TODO
//			
//			
//		if (!checkResult){
//			WritableSheet ws1 = wwb.createSheet("��������", 1);
//			ws1.addCell(new Label(0,0,ws.getCell(0,0).getContents()));
//			ws1.addCell(new Label(1,0,ws.getCell(1,0).getContents()));
//			for (int i = 0 , j = zcxmList.size() ; i < j ; i++){
//				Label label = new Label(2+i, 0, zcxmList.get(i).get("xmmc"));
//				WritableCellFeatures wcfeatures = new WritableCellFeatures(); 
//				//����Ŀ������Ϊע�ͣ�����ʶ����
//		        wcfeatures.setComment(zcxmList.get(i).get("xmdm"));  
//		        label.setCellFeatures(wcfeatures); 
//		        ws1.addCell(label);
//			}
//			
//			int z = 1;//�Ѵ�ӡ�к�
//			for (int i = 0; i < rows; i++) {
//				if(!StringUtils.isBlank((ws.getCell(zcxmList.size()+2,i).getContents()).trim())){
//					ws1.addCell(new Label(0,z,ws.getCell(0,i).getContents()));
//					ws1.addCell(new Label(1,z,ws.getCell(1,i).getContents()));
//					for (int j = 0; j < zcxmList.size()+1; j++) {
//						ws1.addCell(new Label(j+2,z,ws.getCell(j+2,i).getContents()));
//					}
//					z++;
//				}
//			}
//			
//			wwb.removeSheet(0);
//			wwb.write();
//			wwb.close();
//			return file;
//		}
//		return null;
//	}
	
	public List<HashMap<String, String>> getXqList(){
		return dao.getXqList();
	}
}
