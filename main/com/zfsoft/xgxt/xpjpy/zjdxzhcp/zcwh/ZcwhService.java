/**
 * @����:ѧ����Ʒ(1)��
 * @���ڣ�2017-6-22 ����09:41:25 
 */  
package com.zfsoft.xgxt.xpjpy.zjdxzhcp.zcwh;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import xgxt.utils.GetTime;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.views.utils.ArrayUtil;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.xpjpy.zjdxjbsz.cssz.CsszDao;
import com.zfsoft.xgxt.xpjpy.zjdxjbsz.cssz.CsszForm;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �������Ź���ģ��
 * @�๦������: �۲��ά��
 * @���ߣ� Meng.Wei[����:1186]
 * @ʱ�䣺 2017-6-22 ����09:41:52 
 * @�汾�� V5.18.26
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ZcwhService extends SuperServiceImpl<ZcwhForm,ZcwhDao>{
	private ZcwhDao dao = new ZcwhDao();
	public static final String tjzt_tj = "1"; //�������ţ��ύ״̬�ύ
	public static final String tjzt_qxtj = "2"; //�������ţ��ύ״̬Ϊȡ���ύ
	
	public ZcwhService(){
		super.setDao(dao);
	}
	
	/**
	 * @����: �鿴�Ƿ���δ�ύ��¼
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ�2017-6-23 ����11:37:56
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean isSubmitInfo(ZcwhForm t, User user) throws Exception {
		String num = dao.isSubmitInfo(t, user);
		return Integer.valueOf(num) > 0;
	}
	
	/**
	 * @����: ��õȼ�
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2017-6-28 ����09:25:52
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getDj() {
		return dao.getDj();
	}
	
	/**
	 * @����: �۲��ά��
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ�2017-6-28 ����09:41:14
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param zcwhForm
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getZcwhList (ZcwhForm zcwhForm,User user,String zcxmdmForTop)
		throws Exception {
		/*����۲����Ŀ,fjdm��Ϊtop������*/
		List<HashMap<String,String>> zcxmList = dao.getZcxmListByFjdmisTop(zcxmdmForTop);
		/*�۲�ά��*/
		return dao.getZcwhList(zcwhForm,zcxmList,user,zcxmdmForTop);
	}
	
	/**
	 * @����: ����¼���۲�ֵ��۲���Ŀ
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ�2017-6-28 ����11:34:03
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getAllowEditZcfxm(){
		return dao.getZcxmList();
	}
	
	/**
	 * @����: ʵʱ�����۲��
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ�2017-6-28 ����05:43:14
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean saveZcfs(ZcwhForm t,User user) throws Exception{
		/*�жϸ�ѧ�ꡢѧ�ڣ���ѧ���Ƿ��Ѿ�¼���˴���Ŀ�ķ���*/
		HashMap<String,String> zcfsMap  = dao.getFsid(t);
		t.setLrr(user.getUserName());
		t.setLrsj(GetTime.getTimeByFormat("yyyy-MM-dd hh24:mi:ss"));
		/*�ޣ����� �У�����*/
		if (StringUtil.isNull(zcfsMap.get("id"))){
			return dao.runInsert(t);
		} else {
			t.setId(zcfsMap.get("id"));
			return dao.runUpdate(t);
		}
	}
	
	/**
	 * @����: ����ȡ������
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ�2017-6-28 ����05:47:04
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param values
	 * @param user
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean qxcp(String values, User user) throws Exception {
		if(values == null || "".equalsIgnoreCase(values)){
			logger.error("δѡ��ȡ��������Ա��");
			throw new NullPointerException();
		}
		boolean qxcp = false;
		/*���������¼*/
		qxcp=dao.insertTzjl(values,user);
		if(!qxcp){
			return false;
		}
		/*����������Ա��*/
		qxcp=dao.updateCpmd(values,user);
		
		/*ʵ���Ҹ�����Ϊ��ȡ�����ѧ���Ĳ����ʸ�Ӧ��ɾ��������¼���е����ݣ�
		 * �����ɾ���Ļ�����ô�����༶��ʱ�����ݻ����ȥ*/
		return qxcp;
	}
	
	/**
	 * @����: ��ѯ���Ӳ���ѧ���б�
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ�2017-7-5 ����09:39:01
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getAddCpxsList(ZcwhForm model) throws Exception{
		return dao.getAddCpxsList(model);
	}
	
	/**
	 * @����: ��������ѧ��
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ�2017-7-5 ����02:50:51
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param tzhbjdm
	 * @param user
	 * @param ids
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean bjtzs(String tzhbjdm, User user,String ids) throws Exception {
			
		String [] id = ids.split(",");
		//û�е���д������ѭ����ǰ��
		for (int i = 0; i < id.length; i++) {
			bjtz(tzhbjdm, user, id[i]);
		}
		return true;
	}
	
	
	/**
	 * @����: ��ѧ����һ���༶��������һ���༶
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ�2017-7-5 ����02:52:41
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param tzhbjdm
	 * @param user
	 * @param xh
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean bjtz(String tzhbjdm, User user,String xh) throws Exception {
		
		CsszDao csszDao = new CsszDao();
		CsszForm csszModel = csszDao.getModel();
		String xn = csszModel.getXn();
		/*�ж�ID�Ƿ��ڵ�ǰ��������������*/
		boolean isHavePjry = dao.isHavePjry(xh,xn);
		/*����༶������¼*/
		if(isHavePjry){
			 /*��һ���༶����һ���༶*/
			 dao.insertbjTzjl(xh,xn,tzhbjdm,user);
		}else{
			 /*��δ��������һ���༶*/
			 dao.insertbjTzjl1(xn,xh,tzhbjdm,user);
		}
		 
		/*���°༶����������Ա��*/
		 if(isHavePjry){
			/*��һ���༶��������һ���༶*/
			dao.updateBjtzCpmd(xh,xn,tzhbjdm);
		 }else{
			//�Ӳ���������������
			dao.insertBjtzCpmd(xn,xh,tzhbjdm);
		 }
//		 //����ѧ���۲�
//		 if(CsszService.getZczq()){
//			 dao.updateXnzc(xn,xh);
//		 }
		return true;
	}
	
	/**
	 * @����: ����Ƿ���ύ�۲��
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ�2017-7-5 ����05:04:28
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param zcwhForm
	 * @param user
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean isCanSubmit(String values, ZcwhForm zcwhForm, User user) throws Exception {
		return dao.isCanSubmit(values,zcwhForm,user);
	}
	
	/**
	 * @����: �ύ������Ա״̬
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ�2017-7-6 ����09:32:39
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param values
	 * @param tjzt
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean tjCpxs(String values, String tjzt,ZcwhForm model, User user) throws Exception {
		if("tj".equals(tjzt)){
			tjzt=tjzt_tj;
		}else{
			tjzt=tjzt_qxtj;
		}
		return dao.tjCpxs(values,tjzt,model,user);
	}
	
	/**
	 * @����: ͨ��ID��ȡѧ���б�
	 * @���ߣ�Meng.Wei[���ţ�1186]
	 * @���ڣ�2017-7-6 ����09:51:41
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param values
	 * @return
	 * @throws SQLException
	 * String[] �������� 
	 * @throws
	 */
	public String[] getXhArray(String values) throws SQLException {
		return dao.getXhArray(values);
	}
	
	/**
	 * @����: ȡ���ύ�۲���ύ
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ�2017-7-7 ����04:27:48
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getZcfqxList(ZcwhForm t, User user)
		throws Exception {
		
		return dao.getZcfqxList(t, user);
	}
	
	/**
	 * @����: ������������
	 * @���ߣ�Meng.Wei[���ţ�1186]
	 * @���ڣ�2017-7-10 ����02:54:52
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param zcfList
	 * @param zcxmList
	 * @param user
	 * @return
	 * @throws Exception
	 * File �������� 
	 * @throws
	 */
	public File createImportTemplateDc(List<HashMap<String,String>> zcfList,List<HashMap<String,String>> zcxmList, User user) 
		throws Exception{
		
		WritableWorkbook wwb = null;
		
		//�����ļ���� ����ʱĿ¼
		File tempDir = new File(System.getProperty("java.io.tmpdir"));
		if (!tempDir.exists()){
			tempDir.mkdir();
		}
		File file = new File( tempDir.getPath() + "/" +"�۲�ֵ���.xls");
		file.setWritable(true);
		
		try{
			FileOutputStream stream = new FileOutputStream(file);
			//����excel������
			wwb = Workbook.createWorkbook(stream);
			
			WritableSheet ws = wwb.createSheet("�۲�ֵ���", 0);
			
			//�ɱ༭�۲�ֵ��۲���Ŀ
			CsszDao csszDao = new CsszDao();
			CsszForm csszModel = csszDao.getModel();

			//�̶���ͷѧ�š�����
			ws.addCell(new Label(0, 0, "ѧ��"));
			ws.addCell(new Label(1, 0, "����"));//TODO
			ws.addCell(new Label(2, 0, "�༶����"));
			
			for (int i = 0 , j = zcxmList.size() ; i < j ; i++){
				Label label = new Label(3+i, 0, zcxmList.get(i).get("xmmc"));
				WritableCellFeatures wcfeatures = new WritableCellFeatures(); 
				//����Ŀ������Ϊע�ͣ�����ʶ����
		        wcfeatures.setComment(zcxmList.get(i).get("xmdm"));  
		        label.setCellFeatures(wcfeatures); 
		        ws.addCell(label);
			}
			
			for (int i = 0 , j = zcfList.size() ; i < j ; i++){
				ws.addCell(new Label(0, i+1, zcfList.get(i).get("xh")));
				ws.addCell(new Label(1, i+1, zcfList.get(i).get("xm")));
				ws.addCell(new Label(2, i+1, zcfList.get(i).get("bjmc")));
				for (int m = 0 , n = zcxmList.size() ; m < n ; m++){
					ws.addCell(new Label(m+3, i+1, zcfList.get(i).get("fs"+m)));
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
	 * @����: ������֤��������
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ�2017-7-11 ����09:19:15
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @param num
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean isCanDownload(ZcwhForm model,User user, String num,String zcxmdmForTop) throws Exception {
		
		List<HashMap<String,String>> zcxmList = dao.getZcxmListByFjdmisTop(zcxmdmForTop);
		
		//��ѯѧ�����Ѿ�¼��ķ���
		List<HashMap<String,String>> zcfList = dao.getCpxsExportList(model, zcxmList,user,zcxmdmForTop);
		boolean boo = false;
		if(zcfList!=null&&0!=zcfList.size()){
			boo = Integer.parseInt(zcfList.get(0).get("total"))> Integer.parseInt(num)? false : true;
		}
		return boo;
	}
	
	/**
	 * @����: ������������ģ��
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ�2017-7-11 ����01:54:57
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * File �������� 
	 * @throws
	 */
	public File createImportTemplate(ZcwhForm model,User user,String zcxmdmForTop) throws Exception{
		
		/*��������excel�ļ�*/
		WritableWorkbook wwb = null;
		
		/*�����ļ���ŵ���ʱĿ¼*/
		File tempDir = new File(System.getProperty("java.io.tmpdir"));
		if (!tempDir.exists()){
			tempDir.mkdir();
		}
		
		/*���������ļ�*/
		File file = new File(tempDir.getPath() + "/" + "�۲�ֵ���ģ��.xls");
		file.setWritable(true);
		
		try{
			FileOutputStream stream = new FileOutputStream(file);
			/*����excle������*/
			wwb = Workbook.createWorkbook(stream);
			
			WritableSheet ws = wwb.createSheet("�۲�ֵ���ģ��", 0);
			
			/*�۲���Ŀ����������Ŀ��*/
			List<HashMap<String,String>> zcxmList = dao.getZcxmListByFjdmisTop(zcxmdmForTop); 
			
			/*�̶���ͷѧ�š�����*/
			ws.addCell(new Label(0, 0, "ѧ��"));
			ws.addCell(new Label(1, 0, "����"));
			
			for(int i = 0, j = zcxmList.size(); i < j; i++){
				Label label = new Label(2+i, 0, zcxmList.get(i).get("xmmc"));
				WritableCellFeatures wcfeatures = new WritableCellFeatures();
				/*����Ŀ������Ϊע�ͣ�����ʶ����*/
				wcfeatures.setComment(zcxmList.get(i).get("xmdm"));
				label.setCellFeatures(wcfeatures);
				ws.addCell(label);
			}
			
			/*����ҳ*/
			model.getPages().setPageSize(Integer.MAX_VALUE);
			
			/*�۲��List*/
			List<HashMap<String,String>> zcfList = new ArrayList<HashMap<String,String>>();
			zcfList = dao.getCpxsExportList(model, zcxmList,user,zcxmdmForTop);
			
			for (int i = 0 , j = zcfList.size() ; i < j ; i++){
				ws.addCell(new Label(0, i+1, zcfList.get(i).get("xh")));
				ws.addCell(new Label(1, i+1, zcfList.get(i).get("xm")));
				
				for (int m = 0 , n = zcxmList.size() ; m < n ; m++){
					ws.addCell(new Label(m+2, i+1, zcfList.get(i).get("fs"+m)));
				}
			}
			WritableSheet ws1 = wwb.createSheet("��Ŀ�ȼ���", 1);
			
			/*�ȼ�����*/
			List<String> DjmcList = getDjmc();
			
			/*�ȼ�list*/
			List<HashMap<String, String>> djList = getDj();
			
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
			
		}catch (Exception e){
			throw new SystemException(MessageKey.SYS_CREATE_IMPORT_TEMPLATE_FAIL);
		}
		
		file.setWritable(true);
		return file;
	}
	
	/**
	 * @����: ��õȼ�����list
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ�2017-7-11 ����03:52:33
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * @throws SQLException
	 * List<String> �������� 
	 * @throws
	 */
	public List<String> getDjmc() throws SQLException{
		return dao.getDjmc();
	}
	
	/**
	 * �����۲����
	 */
	public File importZcfs(ZcwhForm model,User user,String zcxmdmForTop) throws Exception{
		
		FormFile importFile = model.getImportFile();
		/*��FormFile ת��ΪFile ����*/
		File file = FileUtil.conversionFormFile(importFile);
		
		/*��ȡExcle������*/
		Workbook book = Workbook.getWorkbook(file);
		
		List<HashMap<String,String>> zcxmList = null;
		zcxmList = dao.getZcxmListByFjdmisTop(zcxmdmForTop);
		
		WritableWorkbook wwb  =  Workbook.createWorkbook(file, book);
		WritableSheet ws = wwb.getSheet(0);
		/*���ô�����Ϣ��ʾ���п�*/
		ws.setColumnView(zcxmList.size()+2, 30);
		
		/*����ѧԺѧ����Ϣ*/
		String[] stus = dao.getStuById(model,user);
		
		/*����ģ�����۲�ṹ�Ƚ���֤*/
		for (int i = 0 , j = zcxmList.size() ; i < j ; i++){
			String xmdm = zcxmList.get(i).get("xmdm");
			String xmmc = zcxmList.get(i).get("xmmc");
			
			CellFeatures cellFeatures = ws.getCell(2+i, 0).getCellFeatures();
			
			if (cellFeatures == null){
				throw new SystemException(MessageKey.SYS_IMPORT_UNLAWFUL_TEMPLATE);
			}
			
			String cellComment = cellFeatures.getComment();
			String cellContent = ws.getCell(2+i, 0).getContents();
			
			/*��֤�±�ͷ�Ƿ����۲�ṹ�ĵ���ģ��һ��*/
			if (!xmdm.equals(cellComment.trim()) || !xmmc.equals(cellContent.trim())){
				throw new SystemException(MessageKey.SYS_IMPORT_UNLAWFUL_TEMPLATE);
			}
		}
		
		/*��⵼������*/
		int rows = ws.getRows();
		List<String[]> params = new ArrayList<String[]>();
		
		boolean checkResult = true;
		for (int i = 1 ; i < rows ; i++){
			StringBuilder errorMessage = new StringBuilder();
			
			String xh = ws.getCell(0, i).getContents();
			String xm = ws.getCell(1, i).getContents();
			String[] param = null;
			if (StringUtil.isNull(xh) || StringUtil.isNull(xm)){
				/*ѧ�ź�����Ϊ��*/
				errorMessage.append(MessageUtil.getText(MessageKey.PJPY_IMPORT_ZCFS_XHXM_NULL));
			}else if (!ArrayUtil.contains(stus, xh)){/*��֤ѧ����Ч��*/
				errorMessage.append(MessageUtil.getText(MessageKey.PJPY_IMPORT_ZCFS_FAIL));
			}else{
				
				for (int m = 0 ; m < zcxmList.size() ; m++){
					String xmfz = ws.getCell(m+2, i).getContents().trim();
					
					if (StringUtil.isNull(xmfz)){
						/*��Ŀ��������Ϊ�� */
						errorMessage.append(MessageUtil.getText(MessageKey.PJPY_IMPORT_ZCFS_NULL));
						break;
					}
					
					/*�۲���Ŀ����  ���ȼ��� Ϊ�ȼ�������¼�������*/
					String xmlx = zcxmList.get(m).get("xmlx");
					
					/*�۲���Ŀ����*/
					String drxmmc = zcxmList.get(m).get("xmmc");
					
					if(!"�ȼ�".equals(xmlx)){
						
						/*��֤��Ŀ������ ������Ч��*/
						Double xmf = 0.0;
						
						try {
							xmf = Double.valueOf(xmfz.trim());
						} catch (Exception e) {
							errorMessage.append(MessageUtil.getText(MessageKey.PJPY_IMPORT_ZCFS_NOTNUMBER,
									new Object[]{zcxmList.get(m).get("xmmc")}));
							break;
						}
						
						/*��֤����*/
						if (xmfz.length() > 10){
							errorMessage.append(MessageUtil.getText(MessageKey.PJPY_IMPORT_ZCFS_MORETHEN_MAXLENGTH));
							break;
						}
						
						/*��֤�����С����*/
						Double max = Double.valueOf(zcxmList.get(m).get("zdfz"));
						Double min = Double.valueOf(zcxmList.get(m).get("zxfz"));
						
						if (xmf > max || xmf < min){
							errorMessage.append(MessageUtil.getText(MessageKey.PJPY_IMPORT_ZCFS_MORETHEN_ZDZX,
									new Object[]{zcxmList.get(m).get("xmmc"),max,min}));
							break;
						}
					}else{
						boolean checkFlag = false;
						/*��֤¼��ĵȼ��Ƿ����Ҫ��*/
						List<HashMap<String, String>> djList = dao.getDj();
						for (int j = 0; j < djList.size(); j++) {
							if(djList.get(j).get("mc").equals(xmfz)&&djList.get(j).get("xmmc").equals(drxmmc)){
								xmfz = djList.get(j).get("dm");
								checkFlag = true;
								break;
							}
						}
						if(!checkFlag){
							errorMessage.append(MessageUtil.getText(MessageKey.PJPY_IMPORT_ZCFS_MORETHEN_ZDDJ,
									new Object[]{xmfz}));
							break;
						}
					}
					param=null;
					param= new String[]{xh,zcxmList.get(m).get("xn"),
//							  zcxmList.get(m).get("xq"),
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

					int y = 2;
					ws.addCell(new Label(zcxmList.size()+y, i, errorMessage.toString(),wcf));
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
			for (int i = 0 , j = zcxmList.size() ; i < j ; i++){
				Label label = new Label(2+i, 0, zcxmList.get(i).get("xmmc"));
				WritableCellFeatures wcfeatures = new WritableCellFeatures(); 
				//����Ŀ������Ϊע�ͣ�����ʶ����
		        wcfeatures.setComment(zcxmList.get(i).get("xmdm"));  
		        label.setCellFeatures(wcfeatures); 
		        ws1.addCell(label);
			}
			
			int z = 1;//�Ѵ�ӡ�к�
			int t = 2;
			int x = 1;
			for (int i = 0; i < rows; i++) {
				if(!StringUtils.isBlank((ws.getCell(zcxmList.size()+t,i).getContents()).trim())){
					ws1.addCell(new Label(0,z,ws.getCell(0,i).getContents()));
					ws1.addCell(new Label(1,z,ws.getCell(1,i).getContents()));
					for (int j = 0; j < zcxmList.size()+x; j++) {
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
	 * @����: �۲���ύ���
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ�2017-7-12 ����10:17:00
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean checkZcfSubmit(ZcwhForm model) throws Exception{
		
		boolean isCanSubmit = false;
		
		//��Ĭ����Ŀ �� ͬ���ӿ���Ŀ�ķ����ӵ�������¼��
		initDefaultZcfs(model.getXn(),model.getId(),model.getZcxmdmForTop());
		
		/*��¼��Ŀ�� * ѧ����  <= ������¼��*/
		isCanSubmit = Boolean.valueOf(dao.getSfyWlr(model));
		
		if (!isCanSubmit){
			return false;
		}
		
		/*����Ƿ���NULL��ֵ �ķ�����¼*/
		isCanSubmit = Integer.valueOf(dao.getNullZcf(model)) == 0;
		
		return isCanSubmit;
	}
	
	/**
	 * @����: ��ʼ��Ĭ�����������Ϊ֧��������д��
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ�2017-7-12 ����03:34:54
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xn
	 * @param id
	 * @throws Exception
	 * void �������� 
	 * @throws
	 */
	public void initDefaultZcfs(String xn,String id,String zcxmdmForTop) throws Exception{
		/*����ID��ȡѧԺ��Ϣ*/
		Map<String,String> map = getXyxxById(id);
		String xydm = map.get("xydm");
		
		/*����id��ѧԺ���룬�ڵ���ύ��ʱ�򣬰�ѡ���ѧԺ������Ա��Ϣ�嵽�۲�������У�����Ϊ��*/
		dao.insertDefaultZcxmf(xn,id,xydm,zcxmdmForTop);
	}
	
	/**
	 * @����: ����ID��ȡѧԺ��Ϣ
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ�2017-7-12 ����03:38:43
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param id
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String,String> getXyxxById(String id){
		return dao.getXyxxById(id);
	}
	
	/**
	 * @����: �ύ�۲��
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ�2017-7-13 ����09:06:04
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean submitZcfs(ZcwhForm model,User user){
		/*����ID��ȡѧԺ��Ϣ*/
		Map<String,String> map = getXyxxById(model.getId());
		/*ѧ��*/
		String xn = map.get("xn");
		/*ѧԺ����*/
		String xydm = map.get("xydm");
		 try{
			 /*ִ�д洢����*/
			 Thread thread = new Thread(new ComputeZcpm(xn,xydm));
			 thread.start();
			 
			 boolean result = dao.submitXyzcf(model.getId(), user.getUserName());
			 
			 return result;
		 }catch (Exception e) {
			e.printStackTrace();
		 }
		return false;
	}
	
	/**
	 * @����: ȡ���ύ�۲��¼
	 * @���ߣ� MengWei[���ţ�1186]
	 * @���ڣ� 2018-7-3 ����11:16:46
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean cancelSubmit(ZcwhForm model) throws Exception{
		boolean result = false;
		result = dao.getCancelSubmit(model.getId());
		return result;
	}
	
	/**
	 * @����: ȡ���ύ�۲��
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ�2017-7-13 ����10:45:09
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param user
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean cancelTj( User user,ZcwhForm model)throws Exception {
		
		/*���年ѡ��¼��ID*/
		String id = model.getId();
		boolean cancelTj = false;
		
		/*����������Ϣ*/
		CsszDao csszDao = new CsszDao();
		CsszForm csszForm = csszDao.getModel();
		/*��������ѧ��*/
		String xn = csszForm.getXn();
		
		//����ȡ����¼
		cancelTj =  dao.insertTzjl(id,user,model,xn);
		
		if(!cancelTj){
			return false;
		}
		
		/*����������Ա��*/
		cancelTj = dao.updateCpmd(id,user,tjzt_qxtj);
		
		if(cancelTj){
			Map<String,String> map = getXyxxById(id);
			String xydm = map.get("xydm");
			/*�����۲��*/
			Thread thread = new Thread(new ComputeZcpm(xn,xydm));
			thread.start();
		}
		
		return cancelTj;
	}
	
	/**
	 * @����: �۲�ά���鿴
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ�2017-7-13 ����04:25:43
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param zcxmList
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getZcwhView(ZcwhForm t,List<HashMap<String, String>> zcxmList, User user,String zcxmdmForTop)
		throws Exception {
	
		return dao.getZcwhView(t, zcxmList,user,zcxmdmForTop);
	}
	
	/**
	 * @����: ͬ���۲�֣���ʵ����������֣�
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2017-12-7 ����11:07:34
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param proName
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean getIntefaceData() throws Exception {
		
		boolean bool = false;
		String proName = "pro_pjpy_tbjskpzf";
		bool = dao.getIntefaceData(proName);
		return bool;
	}
	
	/**
	 * @����: �۲��ܷ�
	 * @���ڣ� 2018-1-3 ����03:38:05
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param xn
	 * @param xq
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String,String> getZczfByXh(String xh ,String xn){
		return dao.getZczfByXh(xh, xn);
	}
	
	/**
	 * @����: �����������۲�fjdmΪtop����Ŀ�����ȡ��ص��۲��������������
	 * @���ߣ� MengWei[���ţ�1186]
	 * @���ڣ� 2018-6-28 ����03:07:20
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param zcxmdmForTop
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getZcxmListByFjdmisTop(String zcxmdmForTop)throws Exception {
		return dao.getZcxmListByFjdmisTop(zcxmdmForTop);
	}
}
