/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-5-29 ����11:39:55 
 */  
package com.zfsoft.xgxt.xpjpy.zhcp.zdzc;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.SQLException;
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

import org.apache.commons.lang.StringUtils;
import org.apache.struts.upload.FormFile;

import xgxt.form.User;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.views.utils.ArrayUtil;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.xpjpy.cssz.CsszService;
import com.zfsoft.xgxt.xpjpy.zhcp.zcfs.ZcfsService;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� cq [����:785]
 * @ʱ�䣺 2015-5-29 ����11:39:55 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ZdzcService extends SuperServiceImpl<ZdzcForm, ZdzcDao> {
	
	public static final String tjzt_tj = "1"; //�������ţ��ύ״̬�ύ
	public static final String tjzt_qxtj = "2"; //�������ţ��ύ״̬Ϊȡ���ύ

	/**
	 * @throws Exception  
	 * @����:����۲�ά��
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2015-5-29 ����02:58:43
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param zdzcForm
	 * @param user
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getZcwhList(ZdzcForm zdzcForm,
			User user) throws Exception {

		//����۲����Ŀ
		List<HashMap<String, String>> zcxmList = dao.getzcxmList();
		
		return dao.getZcwhList(zdzcForm,zcxmList,user);
	}

	/** 
	 * @����:����ȡ������
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2015-6-1 ����11:27:24
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param values
	 * @param user
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean qxcp(String values, User user) throws Exception {

		if(values == null || "".equalsIgnoreCase(values)){
			logger.error("δѡ��ȡ��������Ա��");
			throw new NullPointerException();
		}
		
			boolean qxcp = false;
		
			//���������¼
			qxcp=dao.insertTzjl(values,user);
		
			if(!qxcp){
				return false;
			}
			
			//����������Ա��
			qxcp=dao.updateCpmd(values,user);
			
			//ѧ���۲⣬�Զ�����ѧ���۲�,�����ѧ���۲⣬�򲻸���
			if(CsszService.getZczq()){
				dao.updateXncpmd(values,user);
			}
		
			return qxcp;
	}
	
	
	
	/**
	 * 
	 * @����:��������ģ��
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2015-6-9 ����11:06:20
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * File �������� 
	 * @throws
	 */
	public File createImportTemplate(ZdzcForm model, User user) throws Exception{
		
		WritableWorkbook wwb = null;
		
		//�����ļ���� ����ʱĿ¼
		File tempDir = new File(System.getProperty("java.io.tmpdir"));
		if (!tempDir.exists()){
			tempDir.mkdir();
		}
		
		//���������ļ�
		File file = new File( tempDir.getPath() + "/" +"�۲�ֵ���ģ��.xls");
		file.setWritable(true);
		
		try{
			FileOutputStream stream = new FileOutputStream(file);
			//����excel������
			wwb = Workbook.createWorkbook(stream);
			
			WritableSheet ws = wwb.createSheet("�۲�ֵ���ģ��", 0);
			
			
			//����۲����Ŀ
			List<HashMap<String, String>> zcxmList = dao.getzcxmList();
			
			
			//�̶���ͷѧ�š�����
			ws.addCell(new Label(0, 0, "ѧ��"));
			ws.addCell(new Label(1, 0, "����"));
			ws.addCell(new Label(2, 0, "�꼶"));
			ws.addCell(new Label(3, 0, "ѧԺ"));
			ws.addCell(new Label(4, 0, "רҵ"));
			ws.addCell(new Label(5, 0, "�༶"));
			
			for (int i = 0 , j = zcxmList.size() ; i < j ; i++){
				Label label = new Label(2+4+i, 0, zcxmList.get(i).get("xmmc"));
				WritableCellFeatures wcfeatures = new WritableCellFeatures(); 
				//����Ŀ������Ϊע�ͣ�����ʶ����
		        wcfeatures.setComment(zcxmList.get(i).get("xmdm"));  
		        label.setCellFeatures(wcfeatures); 
		        ws.addCell(label);
			}
			
			//����ҳ
			model.getPages().setPageSize(Integer.MAX_VALUE);
			//��ѯѧ�����Ѿ�¼��ķ���
			List<HashMap<String,String>> zcfList = dao.getZcwhList(model,zcxmList,user);
			
			ZcfsService zdzcService = new ZcfsService();
			//�ȼ�����
			List<String> DjmcList= zdzcService.getDjmc();
			//�ȼ�list
			List<HashMap<String, String>> djList = zdzcService.getDj();
			
			
			for (int i = 0 , j = zcfList.size() ; i < j ; i++){
				ws.addCell(new Label(0, i+1, zcfList.get(i).get("xh")));
				ws.addCell(new Label(1, i+1, zcfList.get(i).get("xm")));
				ws.addCell(new Label(2, i+1, zcfList.get(i).get("nj")));
				ws.addCell(new Label(3, i+1, zcfList.get(i).get("xymc")));
				ws.addCell(new Label(4, i+1, zcfList.get(i).get("zymc")));
				ws.addCell(new Label(5, i+1, zcfList.get(i).get("bjmc")));
				
				for (int m = 0 , n = zcxmList.size() ; m < n ; m++){
					if("4".equals(zcxmList.get(m).get("xmlx"))){
						for (int k = 0; k < djList.size(); k++) {
							if(djList.get(k).get("xmmc").equals(zcxmList.get(m).get("xmmc"))&&djList.get(k).get("dm").equals(zcfList.get(i).get("fs"+m))){
								ws.addCell(new Label(m+2+4, i+1, djList.get(k).get("mc")));
								break;
							}
						}
					}else{
						ws.addCell(new Label(m+2+4, i+1, zcfList.get(i).get("fs"+m)));
					}
				}
			}
			
			WritableSheet ws1 = wwb.createSheet("��Ŀ�ȼ���", 1);
			
			
			if(!DjmcList.isEmpty()){
				WritableCellFormat wcf = new WritableCellFormat();
				WritableFont wf = new WritableFont(WritableFont.ARIAL);
				wf.setBoldStyle(WritableFont.BOLD);
				wf.setPointSize(10);
				wf.setColour(Colour.BLUE);
				wcf.setAlignment(Alignment.LEFT);
				wcf.setWrap(true);
				wcf.setFont(wf);
				
				for (int i = 0; i < DjmcList.size(); i++) {
					ws1.addCell(new Label(i, 0, DjmcList.get(i),wcf));
					
					int z=1;
					for (int j = 0; j < djList.size(); j++) {
						if(DjmcList.get(i).equals(djList.get(j).get("xmmc"))){
							ws1.addCell(new Label(i, z, djList.get(j).get("mc")));
							z++;
						}
						
					}
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
	 * @����: �۲⵼��
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2015-6-9 ����02:40:01
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * File �������� 
	 * @throws
	 */
	public File importZcfs(ZdzcForm model,User user) throws Exception{
		
		ZcfsService zcfsservoce = new ZcfsService();
		
		FormFile importFile = model.getImportFile();
		//��FormFile ת��ΪFile ����
		File file = FileUtil.conversionFormFile(importFile);
		
		//��ȡExcel������
		Workbook book = Workbook.getWorkbook(file);
		
		List<HashMap<String, String>> zcxmList = dao.getzcxmList();

		WritableWorkbook wwb  =  Workbook.createWorkbook(file, book);
		WritableSheet ws = wwb.getSheet(0);
		//���ô�����Ϣ��ʾ���п�
		ws.setColumnView(zcxmList.size()+2+4, 30);
		
		//�����༶ѧ��
		String[] stus = dao.getStuById(model,user);
		
		//����ģ�����۲�ṹ�Ƚ���֤
		for (int i = 0 , j = zcxmList.size() ; i < j ; i++){
			
			String xmdm = zcxmList.get(i).get("xmdm");
			String xmmc = zcxmList.get(i).get("xmmc");
			
			CellFeatures cellFeatures = ws.getCell(2+4+i, 0).getCellFeatures();
			
			if (cellFeatures == null){
				throw new SystemException(MessageKey.SYS_IMPORT_UNLAWFUL_TEMPLATE);
			}
			
			String cellComment = cellFeatures.getComment();
			String cellContent = ws.getCell(2+4+i, 0).getContents();
			
			//��֤�±�ͷ�Ƿ����۲�ṹ�ĵ���ģ��һ��
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
			
			String xh = ws.getCell(0, i).getContents();
			String xm = ws.getCell(1, i).getContents();
			
			if (StringUtil.isNull(xh) || StringUtil.isNull(xm)){
				//ѧ�ź�������ô����Ϊ���أ�
				errorMessage.append(MessageUtil.getText(MessageKey.PJPY_IMPORT_ZCFS_XHXM_NULL));
			}else if (!ArrayUtil.contains(stus, xh)){ //��֤ѧ����Ч��
				errorMessage.append(MessageUtil.getText(MessageKey.PJPY_IMPORT_ZCFS_FAIL));
			}else{
				for (int m = 0 ; m < zcxmList.size() ; m++){
					String xmfz = ws.getCell(m+2+4, i).getContents().trim();
					
					if (StringUtil.isNull(xmfz)){
						//��Ŀ��������Ϊ�� 
						errorMessage.append(MessageUtil.getText(MessageKey.PJPY_IMPORT_ZCFS_NULL));
						continue;
					}
					
					//�۲���Ŀ����  4 Ϊ�ȼ�������¼�������
					String xmlx = zcxmList.get(m).get("xmlx");
					
					//�۲���Ŀ����
					String drxmmc = zcxmList.get(m).get("xmmc");
					
					if(!"4".equals(xmlx)){
						
						//��֤��Ŀ������ ������Ч��
						Double xmf = 0.0;
						
						try {
							xmf = Double.valueOf(xmfz.trim());
						} catch (Exception e) {
							errorMessage.append(MessageUtil.getText(MessageKey.PJPY_IMPORT_ZCFS_NOTNUMBER,
									new Object[]{zcxmList.get(m).get("xmmc")}));
							continue;
						}
						
						//��֤����
						if (xmfz.length() > 10){
							errorMessage.append(MessageUtil.getText(MessageKey.PJPY_IMPORT_ZCFS_MORETHEN_MAXLENGTH));
							continue;
						}
						
						//��֤�����С����
						Double max = Double.valueOf(zcxmList.get(m).get("zdfs"));
						Double min = Double.valueOf(zcxmList.get(m).get("zxfs"));
						
						if (xmf > max || xmf < min){
							errorMessage.append(MessageUtil.getText(MessageKey.PJPY_IMPORT_ZCFS_MORETHEN_ZDZX,
									new Object[]{zcxmList.get(m).get("xmmc"),max,min}));
							continue;
						}
					}else{
						boolean checkFlag = false;
						//��֤¼��ĵȼ��Ƿ����Ҫ��
						List<HashMap<String, String>> djList = zcfsservoce.getDj();
						for (int j = 0; j < djList.size(); j++) {
							if(djList.get(j).get("mc").equals(xmfz)&&djList.get(j).get("xmmc").equals(drxmmc)){
								xmfz = djList.get(j).get("dm");
								checkFlag = true;
								continue;
							}
						}
						
						if(!checkFlag){
							errorMessage.append(MessageUtil.getText(MessageKey.PJPY_IMPORT_ZCFS_MORETHEN_ZDDJ,
									new Object[]{xmfz}));
							continue;
						}
					}
					
					//�ռ�����
					String[] param = new String[]{xh,zcxmList.get(m).get("xn"),
												  zcxmList.get(m).get("xq"),
												  zcxmList.get(m).get("xmdm"),
												  xmfz,user.getUserName(),
												  xh,zcxmList.get(m).get("xmdm")};
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
				ws.addCell(new Label(zcxmList.size()+2+4, i, errorMessage.toString(),wcf));
				checkResult = false;
			}
		}
		
		
		//��֤OK�ĵ��룬ʧ�ܵĵ���
		if(!params.isEmpty()){
			System.out.println("action========"+System.currentTimeMillis());
			dao.batchInsertZcfs(params);//��������
			System.out.println("end=========="+System.currentTimeMillis());
		}
			
			
		if (!checkResult){
			WritableSheet ws1 = wwb.createSheet("��������", 1);
			ws1.addCell(new Label(0,0,ws.getCell(0,0).getContents()));
			ws1.addCell(new Label(1,0,ws.getCell(1,0).getContents()));
			ws1.addCell(new Label(2,0,ws.getCell(2,0).getContents()));
			ws1.addCell(new Label(3,0,ws.getCell(3,0).getContents()));
			ws1.addCell(new Label(4,0,ws.getCell(4,0).getContents()));
			ws1.addCell(new Label(5,0,ws.getCell(5,0).getContents()));
			for (int i = 0 , j = zcxmList.size() ; i < j ; i++){
				Label label = new Label(2+i+4, 0, zcxmList.get(i).get("xmmc"));
				WritableCellFeatures wcfeatures = new WritableCellFeatures(); 
				//����Ŀ������Ϊע�ͣ�����ʶ����
		        wcfeatures.setComment(zcxmList.get(i).get("xmdm"));  
		        label.setCellFeatures(wcfeatures); 
		        ws1.addCell(label);
			}
			
			int z = 1;//�Ѵ�ӡ�к�
			for (int i = 0; i < rows; i++) {
				if(!StringUtils.isBlank((ws.getCell(zcxmList.size()+2+4,i).getContents()).trim())){
					ws1.addCell(new Label(0,z,ws.getCell(0,i).getContents()));
					ws1.addCell(new Label(1,z,ws.getCell(1,i).getContents()));
					ws1.addCell(new Label(2,z,ws.getCell(2,i).getContents()));
					ws1.addCell(new Label(3,z,ws.getCell(3,i).getContents()));
					ws1.addCell(new Label(4,z,ws.getCell(4,i).getContents()));
					ws1.addCell(new Label(5,z,ws.getCell(5,i).getContents()));
					for (int j = 0; j < zcxmList.size()+1; j++) {
						ws1.addCell(new Label(j+2+4,z,ws.getCell(j+2+4,i).getContents()));
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
	 * @throws Exception  
	 * @����:�ύ������Ա״̬
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2015-6-9 ����04:22:25
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param values
	 * @param user
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean tjCpxs(String values, String tjzt, ZdzcForm model, User user) throws Exception {
		
		if("tj".equals(tjzt)){
			tjzt=tjzt_tj;
		}else{
			tjzt=tjzt_qxtj;
		}
		
		return dao.tjCpxs(values,tjzt,model,user);
	}

	/**
	 * @throws Exception  
	 * @����:����Ƿ���ύ�۲��
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2015-6-10 ����05:19:54
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param values
	 * @param zdzcForm
	 * @param user
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean isCanSubmit(String values, ZdzcForm zdzcForm, User user) throws Exception {
		return dao.isCanSubmit(values,zdzcForm,user);
	}
	
	/**
	 * 
	 * @����:����¼���۲�ֵ��۲���Ŀ
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2015-6-18 ����09:19:24
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getAllowEditZcfxm(){
		return dao.getAllowEditZcfxm();
	}

	/**
	 * @throws SQLException  
	 * @����:ͨ��idȡ��ѧ���б�)
	 * @���ߣ��Ƴ���
	 * @���ڣ�2015-12-3 ����04:43:47
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param values
	 * @return
	 * String �������� 
	 * @throws 
	 */
	public String[] getXhArray(String values) throws SQLException {
		return dao.getXhArray(values);
	}

}
