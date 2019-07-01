/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-6-4 ����10:17:54 
 */  
package com.zfsoft.xgxt.gygl.wsjc.wsf;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

import org.apache.struts.upload.FormFile;

import xgxt.form.User;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.gygl.wsjc.jcxm.JcxmDao;

/** 
 * @�๦������: ������¼��
 * @���ߣ� ����� [����:445]
 * @ʱ�䣺 2015-6-4 ����10:17:54 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class WsfService extends SuperServiceImpl<WsfModel, WsfDao> {

//	private static final String JCDX_QS = "0";//������¼�� 
	private static final String JCDX_CW = "1";//����λ¼��
	private static final String FSLX_FS = "0";//0:����  1:�ȼ�  2:�Ǽ�
	
	
	/***�������ճ�¼���б�***/
	public List<HashMap<String,String>> getRclrList(WsfModel t, User user) throws Exception{
		
		if (JCDX_CW.equals(t.getJcdx())){
			return dao.getCwlrList(t, user);
		} else {
			return dao.getQslrList(t, user);
		}
	}
	
	
	/***�����ַ���¼���б�***/
	public List<HashMap<String,String>> getFslrList(WsfModel t, User user) throws Exception{
		
		JcxmDao jcxmDao = new JcxmDao();
		List<HashMap<String,String>> rcxmList = jcxmDao.getRcxmList(t.getRcid(),t.getJcdx());
		
		if (JCDX_CW.equals(t.getJcdx())){
			return dao.getCwflrList(t, user, rcxmList);
		} else {
			return dao.getQsflrList(t, user, rcxmList);
		}
	}
	
	/***�����ֵȼ��б�***/
	public List<String> getWsfdjList() throws Exception{
		return dao.getFslrDj();
	}
	
	/***�������Ǽ��б�***/
	public List<String> getWsfxjList() throws Exception{
		return dao.getFslrXj();
	}


	/*
	      ����: @see com.zfsoft.xgxt.base.service.impl.SuperServiceImpl#runInsert(java.lang.Object)
	 */
	
	@Override
	public boolean runInsert(WsfModel t) throws Exception {
		
		if (JCDX_CW.equals(t.getJcdx())){
			dao.delWsfByCwh(t);
		} else {
			dao.delWsfByQsh(t);
		}
		
		return super.runInsert(t);
	}
	
	
	public File createImportTemplate(WsfModel model,User user) throws Exception{
		
		WritableWorkbook wwb = null;
		
		//�����ļ���� ����ʱĿ¼
		File tempDir = new File(System.getProperty("java.io.tmpdir"));
		if (!tempDir.exists()){
			tempDir.mkdir();
		}
		
		//���������ļ�
		File file = new File( tempDir.getPath() + "/" +"�����ֵ���ģ��.xls");
		file.setWritable(true);
		
		try{
			FileOutputStream stream = new FileOutputStream(file);
			//����excel������
			wwb = Workbook.createWorkbook(stream);
			WritableSheet ws = wwb.createSheet("�����ֵ���ģ��", 0);
			
			int start = 2;
			
			//�̶���ͷѧ�š�����
			ws.addCell(new Label(0, 0, "¥��"));
			ws.addCell(new Label(1, 0, "����"));
			String jcdx  = model.getJcdx();
			if (JCDX_CW.equals(jcdx)){
				ws.addCell(new Label(2, 0, "��λ"));
				start = 3;
			}
			
			JcxmDao jcxmDao = new JcxmDao();
			List<HashMap<String, String>>  rcxmList = jcxmDao.getRcxmList(model.getRcid(),jcdx);
			
			for (int i = 0 , j = rcxmList.size() ; i < j ; i++){
				Label label = new Label(start+i, 0, rcxmList.get(i).get("xmmc"));
				WritableCellFeatures wcfeatures = new WritableCellFeatures(); 
				//����Ŀ������Ϊע�ͣ�����ʶ����
		        wcfeatures.setComment(rcxmList.get(i).get("xmdm"));  
		        label.setCellFeatures(wcfeatures); 
		        ws.addCell(label);
			}
			
			//����ҳ
			model.getPages().setPageSize(Integer.MAX_VALUE);
			//��ѯģ�������б�
			List<HashMap<String,String>> resultList = null;
			
			if (JCDX_CW.equals(jcdx)){
				resultList = dao.getCwflrList(model, user, rcxmList);
			} else {
				resultList = dao.getQsflrList(model, user, rcxmList);
			}
			
			for (int i = 0 , j = resultList.size() ; i < j ; i++){
				Label label = new Label(0, i+1, resultList.get(i).get("ldmc"));
				WritableCellFeatures wcfeatures = new WritableCellFeatures(); 
				//��¥��������Ϊע�ͣ�����ʶ����
		        wcfeatures.setComment(resultList.get(i).get("lddm"));  
		        label.setCellFeatures(wcfeatures); 
				ws.addCell(label);
				
				ws.addCell(new Label(1, i+1, resultList.get(i).get("qsh")));
				
				if (JCDX_CW.equals(jcdx)){
					ws.addCell(new Label(2, i+1, resultList.get(i).get("cwh")));
				}
				
				for (int m = 0 , n = rcxmList.size() ; m < n ; m++){
					ws.addCell(new Label(m+start, i+1, resultList.get(i).get("fs"+m)));
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
	
	
	
	
	public File importWsf(WsfModel model,User user) throws Exception{
		
		FormFile importFile = model.getImportFile();
		//��FormFile ת��ΪFile ����
		File file = FileUtil.conversionFormFile(importFile);
		//��ȡExcel������
		Workbook book = Workbook.getWorkbook(file);

		WritableWorkbook wwb  =  Workbook.createWorkbook(file, book);
		WritableSheet ws = wwb.getSheet(0);
		
		JcxmDao jcxmDao = new JcxmDao();
		String rcid  = model.getRcid();
		String jcdx  = model.getJcdx();
		List<HashMap<String, String>>  rcxmList = jcxmDao.getRcxmList(rcid,jcdx);
		
		int start = 2;
		
		if (JCDX_CW.equals(jcdx)){
			start = 3;
		}
		
		//���ô�����Ϣ��ʾ���п�
		ws.setColumnView(rcxmList.size()+start, 30);
		
		//����ģ��Ƚ���֤
		for (int i = 0 , j = rcxmList.size() ; i < j ; i++){
			
			String xmdm = rcxmList.get(i).get("xmdm");
			String xmmc = rcxmList.get(i).get("xmmc");
			
			CellFeatures cellFeatures = ws.getCell(start+i, 0).getCellFeatures();
			
			if (cellFeatures == null){
				throw new SystemException(MessageKey.SYS_IMPORT_UNLAWFUL_TEMPLATE);
			}
			
			String cellComment = cellFeatures.getComment();
			String cellContent = ws.getCell(start+i, 0).getContents();
			
			//��֤�±�ͷ�Ƿ��뵼��ģ��һ��
			if (!xmdm.equals(cellComment.trim()) || !xmmc.equals(cellContent.trim())){
				throw new SystemException(MessageKey.SYS_IMPORT_UNLAWFUL_TEMPLATE);
			}
		}
		
		int rows = ws.getRows();
		List<String[]> params = new ArrayList<String[]>();
		List<String> param;
		List<String> paramXm;
		
		boolean checkResult = true;
		
		for (int i = 1 ; i < rows ; i++){
			StringBuilder errorMessage = new StringBuilder();
			String lddm = ws.getCell(0, i).getCellFeatures().getComment();
			String qsh = ws.getCell(1, i).getContents();
			
			if (StringUtil.isNull(lddm) || StringUtil.isNull(qsh)){
				errorMessage.append(MessageUtil.getText(MessageKey.SYS_IMPORT_UNLAWFUL_TEMPLATE));
			}
			
			param = new ArrayList<String>();
			param.add(rcid);
			param.add(lddm);
			param.add(qsh);
			
			if (JCDX_CW.equals(jcdx)){
				String cwh = ws.getCell(2, i).getContents();
				param.add(cwh);
				
				if (StringUtil.isNull(cwh)){
					errorMessage.append(MessageUtil.getText(MessageKey.SYS_IMPORT_UNLAWFUL_TEMPLATE));
				}
			}
			
			param.add(user.getUserName());
			
			for (int m = 0 ; m < rcxmList.size() ; m++){
				String wsf = ws.getCell(m+start, i).getContents();
				if (StringUtil.isNull(wsf)){
					errorMessage.append(MessageUtil.getText(MessageKey.GYGL_WSJC_IMPORT_NULL));
					break;
				}
				paramXm = new ArrayList<String>();
				paramXm.addAll(param);
				paramXm.add(rcxmList.get(m).get("xmdm"));
				paramXm.add(wsf);
				params.add(paramXm.toArray(new String[]{}));
			}			
			
			//������Ϣ׷��
			if (errorMessage.length() > 0){
				WritableCellFormat wcf = new WritableCellFormat();
				WritableFont wf = new WritableFont(WritableFont.ARIAL);
				wf.setColour(Colour.RED);
				wcf.setFont(wf);
				wcf.setAlignment(Alignment.CENTRE);
				ws.addCell(new Label(rcxmList.size()+start, i, errorMessage.toString(),wcf));
				checkResult = false;
			}
		}
		
		//��֤��ϣ����Կ�ʼ������
		if (checkResult){
			// ����
			if (JCDX_CW.equals(jcdx)){
				// ����
				if(FSLX_FS.equals(model.getFslx())){
					dao.saveWsf(params);
				} else {
					dao.saveWsdj(params);
				}
			} else {
				// ����
				if(FSLX_FS.equals(model.getFslx())){
					dao.saveQsWsf(params);
				} else {
					dao.saveQsWsdj(params);
				}
			}
			wwb.write();
			wwb.close();
			return null;
		} else {
			wwb.write();
			wwb.close();
			return file;
		}
	}


	/*
	      ����: @see com.zfsoft.xgxt.base.service.impl.SuperServiceImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(WsfModel t, User user)
			throws Exception {
		
		if (JCDX_CW.equals(t.getJcdx())){
			return dao.getFscxListByCw(t, user);
		} else {
			return dao.getFscxListByQs(t, user);
		}
		
	}
	
}
