/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-4-23 ����01:35:39 
 */  
package com.zfsoft.xgxt.xpjpy.bfjs.fswh;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import jxl.CellFeatures;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Colour;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableCellFeatures;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.upload.FormFile;

import xgxt.action.Base;
import xgxt.form.User;
import xgxt.utils.GetTime;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.xpjpy.bfjs.cssz.BfjsCsszDao;
import com.zfsoft.xgxt.xpjpy.bfjs.cssz.BfjsCsszModel;
import com.zfsoft.xgxt.xpjpy.bfjs.jsxm.BfjsJsxmDao;
import com.zfsoft.xgxt.xpjpy.bfjs.jsxm.BfjsJsxmService;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �������Ź���ģ��
 * @�๦������: �������� 
 * @���ߣ� xiaxia [���ţ�1104]
 * @ʱ�䣺 2016-4-23 ����01:35:39 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class BfjsFswhService extends SuperServiceImpl<BfjsFswhModel, BfjsFswhDao> {
	private BfjsJsxmService jsxmService = new BfjsJsxmService();
	private BfjsJsxmDao jsxmDao = new BfjsJsxmDao();
	private BfjsCsszDao csszDao = new BfjsCsszDao();
	public static final String DEFAULT_TJ = "1"; //�ύ״̬�ύ
	public static final String DEFAULT_QXTJ = "2"; //�ύ״̬Ϊȡ���ύ
	
	public BfjsFswhService(){
		super.setDao(dao);
	}
	
	/**
	 * 
	 * @����: �����༶�ύ��ѯ
	 * @���ߣ�xiaxia [���ţ�1104]
	 * @���ڣ�2016-4-24 ����10:16:40
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> ��������
	 */
	public List<HashMap<String, String>> getJsbjList(BfjsFswhModel t, User user)
		throws Exception {
		//��þ�������Ŀ
		List<HashMap<String, String>> jsxmList = jsxmDao.getNoChildJsfxm();
		return dao.getJsbjList(t,jsxmList, user);
	}


	/**
	 * 
	 * @����: ���澺������
	 * @���ߣ�xiaxia [���ţ�1104]
	 * @���ڣ�2016-4-24 ����03:07:41
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * boolean ��������
	 */
	public boolean saveBfjsFswh(BfjsFswhModel t,User user) throws Exception{
		
		//�жϸ�ѧ�ꡢѧ�ڣ���ѧ���Ƿ��Ѿ�¼���˴���Ŀ�ķ���
		HashMap<String,String> BfjsFswhMap  = dao.getFsid(t);
		
		t.setLrr(user.getUserName());
		t.setLrsj(GetTime.getTimeByFormat("yyyy-MM-dd hh24:mi:ss"));
		
		//�ޣ����� �У�����
		if (StringUtil.isNull(BfjsFswhMap.get("id"))){
			return dao.runInsert(t);
		} else {
			t.setId(BfjsFswhMap.get("id"));
			return dao.runUpdate(t);
		}
	}
	
	
	/**
	 * 
	 * @����: ���༶�������Ƿ�����ύ
	 * @���ߣ�xiaxia [���ţ�1104]
	 * @���ڣ�2016-4-24 ����04:57:03
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * boolean ��������
	 * @throws Exception 
	 */
	public boolean isCanSubmit(String values, BfjsFswhModel model, User user) throws Exception{
		
		return dao.isCanSubmit(values,model,user);
	}
	
	
	/**
	 * 
	 * @����: �ύ������
	 * @���ߣ�xiaxia [���ţ�1104]
	 * @���ڣ�2016-4-24 ����04:58:02
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * boolean ��������
	 * @throws Exception 
	 */
	public boolean submitBfjsFswh(String values, String tjzt, BfjsFswhModel model , User user) throws Exception{
		boolean result =true;
		String[] ids = values.split(",");
		if("tj".equals(tjzt)){
			tjzt=DEFAULT_TJ;
		}else{
			tjzt=DEFAULT_QXTJ;
		}
		for (int i = 0; i < ids.length; i++) {
			try{
				result=dao.submitBfjsFswh(ids[i],tjzt,model,user);
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		List<HashMap<String,String>> xyList = getXyxxById(ids);
		for (int i = 0; i < xyList.size(); i++) {
			//�����۲��
			Thread thread = new Thread(new ComputeJspm(xyList.get(i).get("xn"), xyList.get(i).get("xq"), xyList.get(i).get("xydm")));
			thread.start();
		}
		return result;
		
	}


	/**
	 * 
	 * @����: ����ȡ�༶��Ϣ
	 * @���ߣ�xiaxia [���ţ�1104]
	 * @���ڣ�2016-4-26 ����01:51:20
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param id
	 * @return
	 * HashMap<String,String> ��������
	 */
	public List<HashMap<String,String>> getBjxxByIds(BfjsFswhModel model, User user) throws Exception{
		
		return dao.getBjxxByIds(model, user);
	}
	
	
	/**
	 * 
	 * @����: ��IDȡ�༶��Ϣ
	 * @���ߣ�xiaxia [���ţ�1104]
	 * @���ڣ�2016-4-26 ����01:51:20
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param id
	 * @return
	 * HashMap<String,String> ��������
	 */
	public List<HashMap<String,String>> getXyxxById(String[] ids){
		
		return dao.getXyxxById(ids);
	}
	
	
	/**
	 * 
	 * @����: ��������ģ��
	 * @���ߣ�xiaxia [���ţ�1104]
	 * @���ڣ�2016-4-29 ����08:51:31
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * File ��������
	 */
	public File createImportTemplate(BfjsFswhModel model, User user) throws Exception{
		
		WritableWorkbook wwb = null;
		
		//�����ļ���� ����ʱĿ¼
		File tempDir = new File(System.getProperty("java.io.tmpdir"));
		if (!tempDir.exists()){
			tempDir.mkdir();
		}
		
		List<HashMap<String,String>> bjxxMap = dao.getBjxxById(model,user);    
		//���������ļ�
		File file = new File( tempDir.getPath() + "/" +"�����ֵ���ģ��.xls");
		file.setWritable(true);
		
		try{
			FileOutputStream stream = new FileOutputStream(file);
			//����excel������
			wwb = Workbook.createWorkbook(stream);
			
			WritableSheet ws = wwb.createSheet("�����ֵ���ģ��", 0);
			
			BfjsJsxmDao bfjsJsxmDao = new BfjsJsxmDao();
			//�ɱ༭�����ֵľ�����Ŀ
		
			BfjsCsszModel csszModel = csszDao.getModel();
			List<HashMap<String,String>> jsxmList = null;
			jsxmList = bfjsJsxmDao.getAllowEditJsfxm();
			//�̶���ͷѧ�š�����
			ws.addCell(new Label(0, 0, "�༶����"));
			ws.addCell(new Label(1, 0, "�༶����"));
			
			for (int i = 0 , j = jsxmList.size() ; i < j ; i++){
				Label label = new Label(2+i, 0, jsxmList.get(i).get("xmmc"));
				WritableCellFeatures wcfeatures = new WritableCellFeatures(); 
				//����Ŀ������Ϊע�ͣ�����ʶ����
		        wcfeatures.setComment(jsxmList.get(i).get("xmdm"));  
		        label.setCellFeatures(wcfeatures); 
		        ws.addCell(label);
			}
			
			//����ҳ
			model.getPages().setPageSize(Integer.MAX_VALUE);
			//��ѯ�༶���Ѿ�¼��ķ���
			
			List<HashMap<String,String>> jsfList = new ArrayList<HashMap<String,String>>();
		
				 jsfList = dao.getPageList(model, jsxmList,user);
			
			
			for (int i = 0 , j = jsfList.size() ; i < j ; i++){
				ws.addCell(new Label(0, i+1, jsfList.get(i).get("bjdm")));
				ws.addCell(new Label(1, i+1, jsfList.get(i).get("bjmc")));
				for (int m = 0 , n = jsxmList.size() ; m < n ; m++){
					ws.addCell(new Label(m+2, i+1, jsfList.get(i).get("fs"+m)));
				}
			}
			wwb.write();
			wwb.close();
		}catch (Exception e) {
			throw new SystemException(MessageKey.SYS_CREATE_IMPORT_TEMPLATE_FAIL);
		}
		
		file.setWritable(true);
		return file;
	}
	
	
	/**
	 * 
	 * @����: ���뾺������
	 * @���ߣ�xiaxia [���ţ�1104]
	 * @���ڣ�2016-4-29 ����10:56:25
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @throws FileNotFoundException
	 * @throws IOException
	 * void ��������
	 * @throws BiffException 
	 */
	public File importBfjsFswh(BfjsFswhModel model,User user) throws Exception{
		
		FormFile importFile = model.getImportFile();
		//��FormFile ת��ΪFile ����
		File file = FileUtil.conversionFormFile(importFile);
		
		//��ȡExcel������
		Workbook book = Workbook.getWorkbook(file);
		//�༶��Ϣ
		List<HashMap<String,String>> bjxxMap = dao.getBjxxById(model,user);
		
		BfjsJsxmDao BfjsJsxmDao = new BfjsJsxmDao();
		//�ɱ༭�����ֵľ�����Ŀ
		BfjsCsszModel csszModel = csszDao.getModel();
		List<HashMap<String,String>> JsxmList = null;
		
			JsxmList = BfjsJsxmDao.getAllowEditJsfxm();
		

		WritableWorkbook wwb  =  Workbook.createWorkbook(file, book);
		WritableSheet ws = wwb.getSheet(0);
		//���ô�����Ϣ��ʾ���п�
		ws.setColumnView(JsxmList.size()+2, 30);
		
		
		//����ģ���뾺���ṹ�Ƚ���֤
		for (int i = 0 , j = JsxmList.size() ; i < j ; i++){
			
			String xmdm = JsxmList.get(i).get("xmdm");
			String xmmc = JsxmList.get(i).get("xmmc");
			
			CellFeatures cellFeatures = ws.getCell(2+i, 0).getCellFeatures();
			
			if (cellFeatures == null){
				throw new SystemException(MessageKey.SYS_IMPORT_UNLAWFUL_TEMPLATE);
			}
			
			String cellComment = cellFeatures.getComment();
			String cellContent = ws.getCell(2+i, 0).getContents();
			
			//��֤�±�ͷ�Ƿ��뾺���ṹ�ĵ���ģ��һ��
			if (!xmdm.equals(cellComment.trim()) || !xmmc.equals(cellContent.trim())){
				throw new SystemException(MessageKey.SYS_IMPORT_UNLAWFUL_TEMPLATE);
			}
		}
		
		/*
		 * ��⵼������
		 */
		int rows = ws.getRows();
		List<String[]> params = new ArrayList<String[]>();
		
		boolean checkResult = true;
		
		for (int i = 1 ; i < rows ; i++){
			StringBuilder errorMessage = new StringBuilder();
			
			String Bjdm = ws.getCell(0, i).getContents();
			
			if (StringUtil.isNull(Bjdm)){
				//�༶����Ϊ��
				errorMessage.append(MessageUtil.getText(MessageKey.PJPY_IMPORT_JSFS_BJDM_NULL));
			}else{
				for (int m = 0 ; m < JsxmList.size() ; m++){
					String xmfz = ws.getCell(m+2, i).getContents().trim();
					
					if (StringUtil.isNull(xmfz)){
						//��Ŀ��������Ϊ�� 
						errorMessage.append(MessageUtil.getText(MessageKey.PJPY_IMPORT_JSFS_NULL));
						break;
					}
				
						
						//��֤��Ŀ������ ������Ч��
						Double xmf = 0.0;
						
						try {
							xmf = Double.valueOf(xmfz.trim());
						} catch (Exception e) {
							errorMessage.append(MessageUtil.getText(MessageKey.PJPY_IMPORT_JSFS_NOTNUMBER,
									new Object[]{JsxmList.get(m).get("xmmc")}));
							break;
						}
						
						//��֤����
						if (xmfz.length() > 10){
							errorMessage.append(MessageUtil.getText(MessageKey.PJPY_IMPORT_JSFS_MORETHEN_MAXLENGTH));
							break;
						}
						
						//��֤�����С����
						Double max = Double.valueOf(JsxmList.get(m).get("zdfz"));
						Double min = Double.valueOf(JsxmList.get(m).get("zxfz"));
						
						if (xmf > max || xmf < min){
							errorMessage.append(MessageUtil.getText(MessageKey.PJPY_IMPORT_JSFS_MORETHEN_ZDZX,
									new Object[]{JsxmList.get(m).get("xmmc"),max,min}));
							break;
						}
					
					String[] param=null;
					
						param= new String[]{Bjdm,JsxmList.get(m).get("xn"),
								  JsxmList.get(m).get("xq"),
								  JsxmList.get(m).get("xmdm"),
								  xmfz,user.getUserName(),Bjdm,JsxmList.get(m).get("xmdm")};
					
					//�ռ�����
					 
					params.add(param);
				}
			}
			
			
			//������Ϣ׷��
			if (errorMessage.length() > 0){
				
				WritableCellFormat wcf = new WritableCellFormat();
				WritableFont wf = new WritableFont(WritableFont.ARIAL);
				wf.setColour(Colour.RED);
				wcf.setFont(wf);
				wcf.setAlignment(Alignment.CENTRE);
				ws.addCell(new Label(JsxmList.size()+2, i, errorMessage.toString(),wcf));
				checkResult = false;
			}
		}
		
		
		//��֤OK�ĵ��룬ʧ�ܵĵ���
		if(!params.isEmpty()){
			System.out.println("action========"+System.currentTimeMillis());
			
			dao.batchInsertBfjsFswh(params);//��������
			
			System.out.println("end=========="+System.currentTimeMillis());
		}
		
		//TODO
			
			
		if (!checkResult){
			WritableSheet ws1 = wwb.createSheet("��������", 1);
			ws1.addCell(new Label(0,0,ws.getCell(0,0).getContents()));
			ws1.addCell(new Label(1,0,ws.getCell(1,0).getContents()));
			for (int i = 0 , j = JsxmList.size() ; i < j ; i++){
				Label label = new Label(2+i, 0, JsxmList.get(i).get("xmmc"));
				WritableCellFeatures wcfeatures = new WritableCellFeatures(); 
				//����Ŀ������Ϊע�ͣ�����ʶ����
		        wcfeatures.setComment(JsxmList.get(i).get("xmdm"));  
		        label.setCellFeatures(wcfeatures); 
		        ws1.addCell(label);
			}
			
			int z = 1;//�Ѵ�ӡ�к�
			for (int i = 0; i < rows; i++) {
				if(!StringUtils.isBlank((ws.getCell(JsxmList.size()+2,i).getContents()).trim())){
					ws1.addCell(new Label(0,z,ws.getCell(0,i).getContents()));
					ws1.addCell(new Label(1,z,ws.getCell(1,i).getContents()));
					for (int j = 0; j < JsxmList.size()+1; j++) {
						ws1.addCell(new Label(j+2,z,ws.getCell(j+2,i).getContents()));
					}
					z++;
				}
			}
			
			wwb.removeSheet(0);
			wwb.write();
			wwb.close();
			return file;
		}
		return null;
	}
	
	
	
	
	/**
	 * 
	 * @����:������ȡ����ѯ
	 * @���ߣ�xiaxia [���ţ�1104]
	 * @���ڣ�2016-4-30 ����10:42:44
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getJsfqxList(BfjsFswhModel t, User user)
		throws Exception {

	return dao.getJsfqxList(t, user);
}

	

	/** 
	 * @����:���������ѯ
	 * @���ߣ�xiaxia [���ţ�1104]
	 * @���ڣ�2016-4-31 ����04:09:13
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param BfjsFswhForm
	 * @param user
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	
	public List<HashMap<String,String>> getJsfjgList(BfjsFswhModel t, User user) throws Exception{
		
		//ǰ����������Ŀ
		List<HashMap<String, String>> JsxmList = jsxmDao.getFirstAndSecondBfjsJsxm(t);
		
		return dao.getJsfjgList(t, user, JsxmList);
	}
	
	
	
	/**
	 * 
	 * @����: ��ȡ�༶�����ֵĵ����ļ�
	 * @���ߣ�xiaxia [���ţ�1104]
	 * @���ڣ�2016-4-6 ����09:08:47
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param BfjsFswhForm
	 * @param user
	 * @return
	 * @throws Exception
	 * File ��������
	 */
	public File getBjJsfFile(BfjsFswhModel BfjsFswhForm, User user) throws Exception{
		
		BfjsJsxmDao BfjsJsxmDao = new BfjsJsxmDao();
		
		List<HashMap<String,String>> JsxmList = BfjsJsxmDao.getNoChildJsfxm();
		//����������ͷ
		Map<String,String> map = new LinkedHashMap<String, String>();
		map.put("bjmc", "�༶");
		for (int i = 0 , j = JsxmList.size() ; i < j ; i++){
			map.put("fs"+i, JsxmList.get(i).get("xmmc"));
		}
		//��������
		BfjsFswhForm.getPages().setPageSize(Integer.MAX_VALUE);
		List<HashMap<String,String>> dataList = dao.getPageList(BfjsFswhForm,JsxmList,user);
		
		IExportService export = new ExportExcelImpl();
		return export.getExportFile(map, dataList);
	}
	
	
	/**
	 * 
	 * @����:�����ֽ������
	 * @���ߣ�xiaxia [���ţ�1104]
	 * @���ڣ�2016-4-6 ����11:34:28
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param BfjsFswhForm
	 * @return
	 * @throws Exception
	 * File �������� 
	 * @throws
	 */
	public File getJsfjgFile(BfjsFswhModel BfjsFswhForm, User user) throws Exception{
		
		BfjsJsxmDao BfjsJsxmDao = new BfjsJsxmDao();
		
		List<HashMap<String, String>> JsxmList = BfjsJsxmDao.getFirstAndSecondBfjsJsxm(BfjsFswhForm);
		
		//����������ͷ
		Map<String,String> map = new LinkedHashMap<String, String>();
		map.put("jszq", "��������");
		map.put("xymc", "ѧԺ");
		map.put("bjmc", "�༶");
		for (int i = 0 , j = JsxmList.size() ; i < j ; i++){
			map.put("fs"+i, JsxmList.get(i).get("xmmc"));
				map.put("pm"+i, "����");
		}
		//��������
		BfjsFswhForm.getPages().setPageSize(Integer.MAX_VALUE);
		
		List<HashMap<String,String>> dataList = dao.getJsfjgList(BfjsFswhForm, user, JsxmList);
		
		IExportService export = new ExportExcelImpl();
		return export.getExportFile(map, dataList);
	}
	public boolean isHaveSubmitClass(String xn ,String xq){
		
		return Integer.valueOf(dao.getYtjZcfNum(xn, xq)) > 0;
	}


	/**
	 * @throws Exception  
	 * @����:�鿴�Ƿ���δ�ύ��¼
	 * @���ߣ�xiaxia [���ţ�1104]
	 * @���ڣ�2016-4-9 ����03:55:46
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param BfjsFswhForm
	 * @param user
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean isSubmitInfo(BfjsFswhModel BfjsFswhForm, User user) throws Exception {
		
		String num = dao.isSubmitInfo(BfjsFswhForm, user);
		
		return Integer.valueOf(num) > 0;
		
	}

	public boolean cancelTj( User user,BfjsFswhModel bfjsFswhForm) throws Exception {
		String id = bfjsFswhForm.getId();	
		boolean cancelTj = false;
		//�����ύ��¼��
		cancelTj=dao.updateTjjL(id,user,DEFAULT_QXTJ);
		String[] ids = id.split(",");
		if(cancelTj){
			List<HashMap<String,String>> xyList = getXyxxById(ids);
			for (int i = 0; i < xyList.size(); i++) {
				//�����۲��
				Thread thread = new Thread(new ComputeJspm(xyList.get(i).get("xn"), xyList.get(i).get("xq"), xyList.get(i).get("xydm")));
				thread.start();
			}
			
		}
		return cancelTj;
	}
	}
