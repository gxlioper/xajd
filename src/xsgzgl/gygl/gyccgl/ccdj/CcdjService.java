package xsgzgl.gygl.gyccgl.ccdj;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jxl.CellView;
import jxl.Sheet;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.write.Label;
import jxl.write.NumberFormats;
import jxl.write.WritableCellFeatures;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import xgxt.action.Base;
import xgxt.form.User;
import xgxt.utils.Pages;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.transaction.TransactionControl;
import com.zfsoft.xgxt.base.util.UniqID;

public class CcdjService extends SuperServiceImpl<CcdjForm, CcdjDao> {
	/**
	 * 
	 * @����: ɾ������
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-3-7 ����05:37:51
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ids
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	@TransactionControl
	public boolean delResult(String[] ids) throws Exception{
		boolean result = dao.delCcdjbJg(ids);
		if(result){
			result = dao.delQswpshbJg(ids);
		}
		return result;
	}
	
	/**
	 * 
	 * @����: ��ȡ¥������List
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-12-9 ����04:02:11
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getLddmList(CcdjForm ccdj){
		return dao.getLddmList(ccdj);
	}
	
	/**
	 * 
	 * @����: ��ȡ����List
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-12-9 ����04:04:33
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param lddm
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getQshList(String lddm,String ch){
		return dao.getQshList(lddm, ch);
	}
	
	/**
	 * 
	 * @����: ��ȡ���List
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-12-9 ����04:36:44
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param lddm
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getChList(String lddm){
		return dao.getChList(lddm);
	}
	
	/**
	 * 
	 * @����: ��ȡ��ƷList
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-3-7 ����02:15:30
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getWpList(){
		return dao.getWpList();
	}
	
	/**
	 * 
	 * @����:��ȡ��Ʒ�𻵳̶�List
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-3-8 ����10:20:19
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getShcdList(){
		return dao.getShcdList();
	}
	
	/**
	 * 
	 * @����: ��ȡ�Ʋ��Ǽ���Ϣmap
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-3-8 ����10:40:02
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param id
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String> getCcdjMap(String id){
		return dao.getCcdjMap(id);
	}
	
	/**
	 * 
	 * @����: ��ȡ������Ʒά��List
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-3-8 ����11:23:17
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getQswpshbList(String id){
		return dao.getQswpshbList(id);
		
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @����: ����������
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-3-8 ����11:42:08
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	@TransactionControl
	public boolean saveForm(CcdjForm ccdjform) throws Exception{
		boolean rs = true;
		String id = ccdjform.getId();
		/**
		 * ����id�Ƿ�����ж��ǲ���������Ǹ��²���
		 */
		if(StringUtils.isNull(id)){
			if(!dao.checkIsExistNotInCcdjb(ccdjform)){
				throw new SystemException(MessageKey.SYS_AUD_DOUBLE);
			}
			id = UniqID.getInstance().getUniqIDHash().toUpperCase();
			//��������
			ccdjform.setId(id);
			rs = dao.runInsertNotCommit(ccdjform);
		}else{
			 dao.delQswpshbJg(new String[]{id});
			//��������
			 rs = dao.runUpdateNotCommit(ccdjform);
		}
		/**
		 * ͨ���ֶ����쳣���׳���Ϣ��
		 */
		if(!rs){
			throw new SystemException(MessageKey.SYS_SAVE_FAIL);
		}
		/**
		 * �������ص�int����jdbc�����ǽ����ж�
		 */
		 rs = dao.runBatchQswpshb(ccdjform);
		/**
		 * ͨ���ֶ����쳣���׳���Ϣ��
		 */
		if(!rs){
			throw new SystemException(MessageKey.SYS_SAVE_FAIL);
		}
		return rs;
	}
	
	/**
	 * 
	 * @����: ��ȡ�鿴ʱ��Ҫ��ѯ���𻵳̶�List
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-3-8 ����03:16:44
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getViewWpShcdList(String id){
		return dao.getViewWpShcdList(id);
	}
	
	/**
	 * 
	 * @����: ���Һţ�ѧ�꣬ѧ������ʱ��ȡ����
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-3-9 ����10:10:38
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ccdjform
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getViewWpList(CcdjForm ccdjform){
		return dao.getViewWpList(ccdjform);
	}
	
	/**
	 * 
	 * @����: ��֤�Ƿ��ڲƲ��ǼǱ��д����ظ�����
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-3-8 ����05:59:42
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ccdjform
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean checkIsExistNotInCcdjb(CcdjForm ccdjform){
		return dao.checkIsExistNotInCcdjb(ccdjform);
	}
	
	/**
	 * 
	 * @����: ���ص���ģ��
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-3-13 ����09:46:39
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param os
	 * @throws Exception
	 * void �������� 
	 * @throws
	 */
	public  void createWwb(OutputStream os,CcdjForm ccdj) throws Exception{
		WritableWorkbook wwb = Workbook.createWorkbook(os);
		WritableCellFormat titlefont =  this.getFontStyle("title");
		WritableCellFormat titlebody =  this.getFontStyle("titlebody");
		/**
		 * sheet1
		 */
		WritableSheet ws = wwb.createSheet("���ݵ���ģ��", 0);
	    Label row1col1= new Label(0, 0, "ѧ��",titlefont);
	    Label row1col2= new Label(1, 0, "ѧ��",titlefont);
	    Label row1col3= new Label(2, 0, "¥��",titlefont);
	    Label row1col4= new Label(3, 0, "���Һ�",titlefont);
	    ws.addCell(row1col1);
		ws.addCell(row1col2);
		ws.addCell(row1col3);
		ws.addCell(row1col4);
	    /**
	     * ���±�ͷ���̶�����xg_gygl_new_ssccgl_wpwhb(��Ʒά����)dm˳�����
	     */
	    List<HashMap<String, String>> wpList = dao.getWpList();
	    int size = wpList.size();
	    for (int i = 4; i < 4+wpList.size(); i++) {
			HashMap<String, String> lsMap = wpList.get(i-4);
			Label rowcol = new Label(i, 0, lsMap.get("mc"), titlefont);
			WritableCellFeatures cellFeatures = new WritableCellFeatures();  
			cellFeatures.setComment(lsMap.get("dm"));  
			rowcol.setCellFeatures(cellFeatures);
			ws.addCell(rowcol);
		}
	    
		Label row1col6= new Label(4+wpList.size(), 0, "��ע",titlefont);
		Label row1col7= new Label(4+wpList.size()+1, 0, "�����ܼ�(Ԫ)",titlefont);
		 
		
		ws.addCell(row1col6);
		ws.addCell(row1col7);
	    WritableCellFormat wcfF = new WritableCellFormat(
	    	      NumberFormats.TEXT); //����ģ�嵥Ԫ���ʽ����Ϊ�ı���ʽ
	    CellView cv = new CellView(); //����һ������ʾ��ʽ 
	    cv.setFormat(wcfF);//�Ѷ���ĵ�Ԫ���ʽ��ʼ����ȥ
	    cv.setSize(10*265);//�����п�ȣ������õĻ���0��������ʾ��
	    for (int i = 0; i < 6+size; i++) {
	    	ws.setColumnView(i, cv);
		}
	    /**
	     * sheet2�𻵳̶�ά����
	     */
	    WritableSheet ws1 = wwb.createSheet("˳���̶�ά����", 1);
	    List<HashMap<String, String>> shcdList = dao.getShcdList();
	    Label ws1row1col1= new Label(0, 0, "�𻵳̶�����",titlefont);
	    ws1.addCell(ws1row1col1);
	    for (int i = 0; i < shcdList.size(); i++) {
	    	Label tempLabel = new Label(0, i+1, shcdList.get(i).get("shcdmc"),titlebody);
	    	 ws1.addCell(tempLabel);
		}
	    
	    /**
		  * sheet3ѧ��
		  */
		 WritableSheet ws2 = wwb.createSheet("ѧ�ڱ�", 2);
		 List<HashMap<String, String>> xqList = Base.getXqList();
		 Label ws2row1col1= new Label(0, 0, "ѧ��",titlefont);
		 ws2.addCell(ws2row1col1);
		 for (int i = 0; i < xqList.size(); i++) {
			 Label tempLabel = new Label(0, i+1, xqList.get(i).get("xqmc"),titlebody);
	    	 ws2.addCell(tempLabel);
		 }
		 
		 /**
		  * sheet4
		  */
		 WritableSheet ws3 = wwb.createSheet("¥����", 2);
		 List<HashMap<String, String>> lddmList = dao.getLddmList(ccdj);
		 Label ws3row1col1= new Label(0, 0, "¥��",titlefont);
		 ws3.addCell(ws3row1col1);
		 for (int i = 0; i < lddmList.size(); i++) {
			 Label tempLabel = new Label(0, i+1, lddmList.get(i).get("ldmc"),titlebody);
			 ws3.addCell(tempLabel);
		 }
		 wwb.write();
		 wwb.close();
		 
	}
	
	/**
	 * 
	 * @����: excel����
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-3-13 ����09:51:34
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param paras
	 * @return
	 * @throws WriteException
	 * WritableCellFormat �������� 
	 * @throws
	 */
	 public WritableCellFormat getFontStyle(String paras) throws WriteException{
			
			/** 
	         * ���嵥Ԫ����ʽ 
	         */ 
		    if("titlehead".equals(paras)){
				  WritableFont wf_table = new WritableFont(WritableFont.ARIAL, 14,  
			                WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE,  
			                jxl.format.Colour.BLACK);   
				  WritableCellFormat wcf_table = new WritableCellFormat(wf_table);   
//				  wcf_table.setShrinkToFit(true);
		          wcf_table.setAlignment(jxl.format.Alignment.CENTRE);
		          wcf_table.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
		          return wcf_table;
			}else if("title".equals(paras)){
				  WritableFont wf_table = new WritableFont(WritableFont.ARIAL, 12,  
			                WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE,  
			                jxl.format.Colour.BLACK);  
				  WritableCellFormat wcf_table = new WritableCellFormat(wf_table);  
//				  wcf_table.setShrinkToFit(true);
		          wcf_table.setAlignment(jxl.format.Alignment.CENTRE);
		          wcf_table.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
		          return wcf_table;
			}else if("titlebody".equals(paras)){
				  WritableFont wf_table = new WritableFont(WritableFont.ARIAL, 10,  
			                WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE,  
			                jxl.format.Colour.BLACK);  
				  WritableCellFormat wcf_table = new WritableCellFormat(wf_table);  
//				  wcf_table.setShrinkToFit(true);
		          wcf_table.setAlignment(jxl.format.Alignment.CENTRE);
		          wcf_table.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
		          return wcf_table;
			}else if("errorinfo".equals(paras)){
				WritableFont wf_table = new WritableFont(WritableFont.ARIAL, 10,  
		                WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE,  
		                jxl.format.Colour.RED);   
			  WritableCellFormat wcf_table = new WritableCellFormat(wf_table);   
//			  wcf_table.setShrinkToFit(true);
	          wcf_table.setAlignment(jxl.format.Alignment.CENTRE);
	          wcf_table.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
	          return wcf_table;
			}
	     
	        return null;
		}
	 
		/**
		 * 
		 * @����: ������Ϣ����
		 * @���ߣ�yxy[���ţ�1206]
		 * @���ڣ�2016-12-23 ����11:17:13
		 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
		 * @param is
		 * @param zhfdrform
		 * @return
		 * HashMap<String,Object> �������� 
		 * @throws
		 */
	 	@SuppressWarnings("unchecked")
		@TransactionControl
		public HashMap<String, Object> saveDrExcelInfo(InputStream is,CcdjForm ccdjForm) throws Exception {
			HashMap<String, Object> resultMap= null;
				 resultMap= this.DrExcelInfoCheck(is, ccdjForm);
				//�ж�excel����Ƿ�Ϊ��
				if( resultMap.isEmpty()){
					throw new SystemException(MessageKey.SYS_DR_KBG);
				}else if("true".equals(resultMap.get("result"))){
					List<String[]> paralist = (List<String[]>) resultMap.get("drlist");
					List<String[]> paralistsc = (List<String[]>) resultMap.get("drlistsc");
					List<String[]> paralistfb = (List<String[]>) resultMap.get("drlistfb");
					if( paralist == null ||paralist.size() == 0 ){
						throw new SystemException(MessageKey.SYS_DR_KBG);
					}
				  	 this.saveDrDataIntoDb(paralist, paralistsc, paralistfb);
				  	 resultMap.put("message", MessageKey.SYS_DR_DRCGBZ);
				}else{
					String gid = this.uploadErrorExcel((List<String[]>) resultMap.get("errorlist"),ccdjForm.getFilepath());
					resultMap.put("gid", gid);
					resultMap.put("message", MessageKey.SYS_DR_DRBFSB);
					List<String[]> paralist = (List<String[]>) resultMap.get("drlist");
					List<String[]> paralistsc = (List<String[]>) resultMap.get("drlistsc");
					List<String[]> paralistfb = (List<String[]>) resultMap.get("drlistfb");
					if( paralist == null ||paralist.size() == 0 ){
						resultMap.put("result", "false");
						resultMap.put("message", MessageKey.SYS_DR_DRSBBZ);
						return resultMap;
					}
				  	 this.saveDrDataIntoDb(paralist, paralistsc, paralistfb);
					 logger.info("����ʧ�ܣ�");
				}
			return resultMap;
		}
		
		/**
		 * 
		 * @����:������Ϣ��֤
		 * @���ߣ�yxy[���ţ�1206]
		 * @���ڣ�2016-6-20 ����05:41:00
		 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
		 * @param is
		 * @param cjffForm
		 * @return
		 * HashMap<String,Object> �������� 
		 * @throws
		 */
		@SuppressWarnings("unchecked")
		public HashMap<String, Object> DrExcelInfoCheck(InputStream is,CcdjForm ccdjForm) throws Exception{
			//�����¼����1 ��������
			List<String[]> drlist = new ArrayList<String[]>();
			//�����¼����2 �𺦳̶�ά����
			List<String[]> drlistfb = new ArrayList<String[]>();
			//�����¼����3 ɾ������
			List<String[]> drlistsc = new ArrayList<String[]>();
			//�����¼����
			List<String[]> errorlist = new ArrayList<String[]>();
			HashMap<String, Object> resultMap = new HashMap<String, Object>();
			resultMap.put("result", "true");
			Workbook rwb = null;
			List<HashMap<String, String>> wpdmList =  dao.getWpList();
			int num = (wpdmList != null && !wpdmList.isEmpty()) ? wpdmList.size() : 0;
			rwb = Workbook.getWorkbook(is);
			Sheet rs=rwb.getSheet(0);
		    int clos=rs.getColumns();//�õ����е���
	        int rows=rs.getRows();//�õ����е���
	        //�ж�excel����Ƿ�Ϊ��
	        if(rows < 2){
	        	throw new SystemException(MessageKey.SYS_DR_KBG);
	        }else if(6+num < clos){
	        	//δ��ģ���ʽ����
	        	throw new SystemException(MessageKey.SYS_DR_FORMATER);
	        }else if(!this.checkExcelRepeat(rs, clos, rows)){
	        	throw new SystemException(MessageKey.SYS_DR_EXCELREPEAT);
	        }else{
//	        	  HashMap<String, String> dataMap = new HashMap<String, String>();
//	        	  for (int i = 4; i < clos-2; i++) {
//	        		  dataMap.put("shcdmc"+i,  rs.getCell(i,0).getContents());
//				  }
	        	  for (int i = 1; i < rows; i++) {
	  	        	
	  	        		//ȡ��ÿ����֤���ݣ�����lsmap
	  	        		HashMap<String, String>  lsmap = new HashMap<String, String>();
	  	        		String xn = rs.getCell(0, i).getContents();
	  	        		String xq = rs.getCell(1, i).getContents();
	  	        		String ldmc = rs.getCell(2, i).getContents();
	  	        		String qsh = rs.getCell(3, i).getContents();
	  	        		String bz = rs.getCell(clos-2, i).getContents();
	  	        		String zje = rs.getCell(clos-1, i).getContents();
	  	        		lsmap.put("xn", xn);
	  	        		lsmap.put("xqmc", xq);
	  	        		lsmap.put("ldmc", ldmc);
	  	        		lsmap.put("qsh", qsh);
	  	        		lsmap.put("bz", bz);
	  	        		lsmap.put("zje",zje);
	  	        		/**
	  	        		 * �������е��𺦳̶ȷ���lsmap
	  	        		 */
	  	        	    for (int k = 4; k < clos-2; k++) {
	  	        	    	lsmap.put("shcdmc"+k,  rs.getCell(k,i).getContents());
	  	        	    	lsmap.put("wpdm"+k,  rs.getCell(k,0).getCellFeatures().getComment());
					    }
	  	        		//�����ж�
	  	        	    if(this.checkNullRow(lsmap)){
	  	        	    	continue;
	  	        	    }
	  	        		
	  	        		HashMap<String,Object> resultmap = this.checkEveryRowRecord(lsmap, ccdjForm);
	  	        		ArrayList<String> paralist = new ArrayList<String>();
	  	        		lsmap = (HashMap<String, String>) resultmap.get("resultmap");
	  	        		if("false".equals(resultmap.get("result"))){
	  	        			paralist.add(lsmap.get("xn"));
	  	        			paralist.add(lsmap.get("xqmc"));
	  	        			paralist.add(lsmap.get("ldmc"));
	  	        			paralist.add(lsmap.get("qsh"));
	  	        			for (int k = 4; k < clos-2; k++) {
	  	        				 paralist.add(lsmap.get("shcdmc"+k));
	  	        			}
	  	        			paralist.add(lsmap.get("bz"));
	  	        			paralist.add(lsmap.get("zje"));
	  	        			if(lsmap != null ){
	  	        				for (Map.Entry<String, String> entry : lsmap.entrySet()){
	  	        					if(entry.getKey().indexOf("yz") != -1){
	  	        						paralist.add(entry.getValue());
	  	        					}
	  	        					
	  	        				}
	  	        			}
	  	        			errorlist.add(paralist.toArray(new String[]{}));
	  	        			resultMap.put("result", "false");
	  	        		}else{
	  	        			String id = UniqID.getInstance().getUniqIDHash().toUpperCase();
	  	        			paralist.add(id);
	  	        			paralist.add(lsmap.get("xn"));
	  	        			paralist.add(lsmap.get("xq"));
	  	        			paralist.add(lsmap.get("lddm"));
	  	        			paralist.add(lsmap.get("qsh"));
	  	        			paralist.add(lsmap.get("zje"));
	  	        			paralist.add(lsmap.get("bz"));
	  	        			for (String key : lsmap.keySet()) {
		  	  					if(key.indexOf("shcddm") != -1){
		  	        			  ArrayList<String> paralist1 = new ArrayList<String>();
		  	  					  paralist1.add(id);
		  	  					  paralist1.add(lsmap.get("xn"));
		  	  					  paralist1.add(lsmap.get("xq"));
		  	  					  paralist1.add(lsmap.get("lddm"));
		  	  					  paralist1.add(lsmap.get("qsh"));
		  	  					  paralist1.add(lsmap.get("wpdm"+key.substring(key.length()-1, key.length())));
		  	  					  paralist1.add(lsmap.get(key));
		  	  					  drlistfb.add(paralist1.toArray(new String[]{}));
		  	  					}
	  	  				    }
	  	        			ArrayList<String> paralist2 = new ArrayList<String>();
	  	        			paralist2.add(lsmap.get("xn"));
	  	        			paralist2.add(lsmap.get("xq"));
	  	        			paralist2.add(lsmap.get("lddm"));
	  	        			paralist2.add(lsmap.get("qsh"));
	  	        		    drlistsc.add(paralist2.toArray(new String[]{}));
	  	        			drlist.add(paralist.toArray(new String[]{}));
	  	        		}
	  			}
	        	  
	        	  resultMap.put("drlist", drlist);
	        	  resultMap.put("drlistsc", drlistsc);
	        	  resultMap.put("drlistfb", drlistfb);
	        	  resultMap.put("errorlist", errorlist);
	        }
	      
		
		    return resultMap;
		}
		
		/**
		 * 
		 * @����: ��֤ÿ�еļ�¼
		 * @���ߣ�yxy[���ţ�1206]
		 * @���ڣ�2016-12-23 ����02:11:13
		 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
		 * @param lsmap
		 * @param cjffForm
		 * @return
		 * HashMap<String,Object> �������� 
		 * @throws
		 */
		public HashMap<String, Object> checkEveryRowRecord(HashMap<String, String> lsmap,CcdjForm ccdjForm){
			String message = "true";
			//Ϊ����֤
			if(!this.checkNotNull(lsmap)){
					message = "false";
					lsmap.put("kyz", "ѧ�꣬ѧ�ڣ�¥�������Һţ��ܽ���Ϊ�գ�������Ʒ�𺦳̶�������дһ�");
			}else{
				//ѧ����֤
				String[] xns = lsmap.get("xn").split("-");
				if(xns.length != 2){
					message = "false";
					lsmap.put("xnyz", "ѧ���ʽ����ȷ��������(xxxx-xxxx):��2016-2017��");
				}else if(lsmap.get("xn").length() > 9){
					message = "false";
					lsmap.put("xnyz", "ѧ���ʽ����ȷ��������(xxxx-xxxx):��2016-2017��");
				}else{
					try {
						int xn1 = Integer.parseInt(xns[0]);
						int xn2 = Integer.parseInt(xns[1]);
						if(xn2 != xn1+1){
							message = "false";
							lsmap.put("xnyz", "ѧ���ʽ����ȷ��������(xxxx-xxxx):��2016-2017��");
						}
					} catch (NumberFormatException e) {
						message = "false";
						lsmap.put("xnyz", "ѧ���ʽ����ȷ��������(xxxx-xxxx):��2016-2017��");
					}
				}
				//ѧ����֤
				List<HashMap<String,String>> xqList = ccdjForm.getXqList();
				String xq = "";
				for (HashMap<String, String> hashMap : xqList) {
					if(hashMap.get("xqmc").equals(lsmap.get("xqmc"))){
						xq = hashMap.get("xqdm");
						lsmap.put("xq", xq);
						break;
					}
				}
				if(StringUtils.isNull(xq)){
					message = "false";
					lsmap.put("xqyz", "ѧ�����Ʋ����ڣ���ȷ�ϣ�");
				}
				
				//¥�����ƺ����Һ��Ƿ���Ե���
				String lddm = dao.checkLdmcDryz(ccdjForm, lsmap.get("ldmc"), lsmap.get("qsh"));
				if(StringUtils.isNull(lddm)){
					message = "false";
					lsmap.put("ldmcqshlhyz", "¥�������ҺŲ����ڻ���û��Ȩ�޽��и�¥�����ݵĵ��룡");
					
				}
				
				if("true".equals(message)){
					lsmap.put("lddm", lddm);
				}
				
				//��ע��֤
				if(StringUtils.isNotNull(lsmap.get("bz")) && lsmap.get("bz").length() > 1000){
					message = "false";
					lsmap.put("bzyz", "��ע�ֶβ��ܳ���1000�֣�");
					
				}
				
				//�����֤
				if(lsmap.get("zje").length() > 8){
					message = "false";
					lsmap.put("jecdyz", "�����ܼƲ��ܳ���8λ��");
				}
				try {
					Float.parseFloat(lsmap.get("zje"));
				} catch (NumberFormatException e1) {
					message = "false";
					lsmap.put("jeyz", "�����ܼ�������������һλС����");
				}
				
			     //�������𺦳̶���֤
				List<HashMap<String, String>> shcdList = ccdjForm.getShcdList();
				HashMap<String, String> tempMap = new HashMap<String, String>();
				for (String key:lsmap.keySet()) {
					String shcdmc = lsmap.get(key);
					if(StringUtils.isNotNull(shcdmc) && key.indexOf("shcdmc") != -1){
						boolean flag = false;
						for (HashMap<String, String> hashMap : shcdList) {
							if(hashMap.get("shcdmc").equals(shcdmc)){
								flag = true;
								tempMap.put("shcddm"+key.substring(key.length()-1, key.length()), hashMap.get("shcddm"));
							}
						}
						if(!flag){
							message = "false";
							tempMap.put("shcdyz", "�𺦳̶Ȳ����ڣ���ȷ�ϣ�");
							break;
						}
					}
				}
				lsmap.putAll(tempMap);
			
					
			}
			
			
			HashMap<String, Object> resultMap = new HashMap<String, Object>();
			resultMap.put("result", message);
			resultMap.put("resultmap", lsmap);
			return resultMap;
		}
		
		/**
		 * @throws WriteException 
		 * 
		 * @����:������excel���д�������
		 * @���ߣ�yxy[���ţ�1206]
		 * @���ڣ�2016-6-22 ����05:30:28
		 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
		 * @param errorlist
		 * @return
		 * @throws IOException
		 * String �������� 
		 * @throws
		 */
		public String uploadErrorExcel(List<String[]> errorlist,String filepath) throws Exception{
			 String fileName = System.currentTimeMillis()+"error.xls";
			 String path = filepath+fileName;
	         File file=new File(path);
	         if (!file.exists()) {
	             file.createNewFile();
	         }
	         WritableWorkbook  wwb = Workbook.createWorkbook(file);
			try {
				
				  // ����������
	            WritableSheet ws = wwb.createSheet("��������", 0);
	        	WritableCellFormat titlefont =  this.getFontStyle("title");
	    		/**
	    		 * sheet1
	    		 */
	    	    Label row1col1= new Label(0, 0, "ѧ��",titlefont);
	    	    Label row1col2= new Label(1, 0, "ѧ��",titlefont);
	    	    Label row1col3= new Label(2, 0, "¥��",titlefont);
	    	    Label row1col5= new Label(3, 0, "���Һ�",titlefont);
	    		 
	    	    List<HashMap<String, String>> wpList = dao.getWpList();
        	    int size = wpList.size();
	            try {
	            	ws.addCell(row1col1);
	        		ws.addCell(row1col2);
	        		ws.addCell(row1col3);
	        		ws.addCell(row1col5);
	        	    /**
	        	     * ���±�ͷ���̶�����xg_gygl_new_ssccgl_wpwhb(��Ʒά����)dm˳�����
	        	     */
	        	    for (int i = 4; i < 4+wpList.size(); i++) {
	        			HashMap<String, String> lsMap = wpList.get(i-4);
	        			Label rowcol = new Label(i, 0, lsMap.get("mc"), titlefont);
	        			WritableCellFeatures cellFeatures = new WritableCellFeatures();  
	        			cellFeatures.setComment(lsMap.get("dm"));  
	        			rowcol.setCellFeatures(cellFeatures);
	        			ws.addCell(rowcol);
	        		}
	        	    
	        		Label row1col6= new Label(4+wpList.size(), 0, "��ע",titlefont);
	        		Label row1col7= new Label(4+wpList.size()+1, 0, "�����ܼ�(Ԫ)",titlefont);
	        		 
	        		
	        		ws.addCell(row1col6);
	        		ws.addCell(row1col7);
				} catch (RowsExceededException e1) {
					e1.printStackTrace();
				} catch (WriteException e1) {
					e1.printStackTrace();
				}
	            for (int i = 0; i < errorlist.size(); i++) {
	                 for(int j = 0;j<errorlist.get(i).length;j++){
	                	 Label labelId_i= null;
	                	 if(j<6+size){
	                		  labelId_i= new Label(j, i+1, errorlist.get(i)[j]+"");
	                	 }else{
	                		 WritableCellFormat wcf = new WritableCellFormat();
	         				WritableFont wf = new WritableFont(WritableFont.ARIAL);
	         				try {
								wf.setColour(Colour.RED);
								wcf.setFont(wf);
								wcf.setAlignment(Alignment.CENTRE);
								labelId_i = new Label(j, i+1, errorlist.get(i)[j],wcf);
							} catch (RowsExceededException e) {
								e.printStackTrace();
							} catch (WriteException e) {
								e.printStackTrace();
							}
	         				
	                	 }
	                		try {
								ws.addCell(labelId_i);
							} catch (RowsExceededException e) {
								e.printStackTrace();
							} catch (WriteException e) {
								e.printStackTrace();
							}
	                	
	                 }
	            }
	            
			}finally{
				 wwb.write();
				 try {
					wwb.close();
				} catch (WriteException e) {
					e.printStackTrace();
				}
			}
			return fileName;
		}
		
		/**
		 * 
		 * @����: �������浼������
		 * @���ߣ�yxy[���ţ�1206]
		 * @���ڣ�2016-12-23 ����04:49:29
		 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
		 * @param paralist
		 * @return
		 * @throws Exception
		 * boolean �������� 
		 * @throws
		 */
		public boolean saveDrDataIntoDb(List<String[]> paralist,List<String[]> paralistsc,List<String[]> paralistfb) throws Exception{
			boolean rs = dao.delCcdjb(paralistsc);
			if(!rs){
				throw new SystemException(MessageKey.SYS_DR_FAIL);
			}
			rs = dao.delQswpshb(paralistsc);
			if(!rs){
				throw new SystemException(MessageKey.SYS_DR_FAIL);
			}
			rs = dao.saveDrDataIntoZb(paralist);
			if(!rs){
				throw new SystemException(MessageKey.SYS_DR_FAIL);
			}
			rs = dao.saveDrDataIntoFb(paralistfb);
			if(!rs){
				throw new SystemException(MessageKey.SYS_DR_FAIL);
			}
			return rs;
		}
		
		/**
		 * 
		 * @����:excel������Ƿ����ظ�����
		 * @���ߣ�yxy[���ţ�1206]
		 * @���ڣ�2016-8-2 ����04:25:09
		 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
		 * @param rs
		 * @param cols
		 * @param rows
		 * @return
		 * boolean �������� 
		 * @throws
		 */
		public boolean checkExcelRepeat(Sheet rs,int cols,int rows){
			ArrayList<String> compareList = new ArrayList<String>();
			boolean flag = true;
			for (int i = 0; i < rows; i++) {
				String xn = rs.getCell(0, i).getContents();
				String xq = rs.getCell(1,i).getContents();
				String ldmc = rs.getCell(2,i).getContents();
				String qsh = rs.getCell(3,i).getContents();
				String str = "";
				if(StringUtils.isNotNull(xn)){
					str = str + xn.trim();
				}
				if(StringUtils.isNotNull(xq)){
					str = str + xq.trim();
				}
				if(StringUtils.isNotNull(ldmc)){
					str = str + ldmc.trim();
				}
				if(StringUtils.isNotNull(qsh)){
					str = str + qsh.trim();
				}
				if(StringUtils.isNull(str)){
					continue;
				}
				compareList.add(str);
			}
		    for (int i = 0; i < compareList.size(); i++) {
				for (int j = i+1; j < compareList.size(); j++) {
					if(compareList.get(i).equals(compareList.get(j))){
						flag = false;
						break;
					}
				}
				if(!flag){
					break;
				}
			}
			return flag;
		}
		
		/**
		 * 
		 * @����: ������
		 * @���ߣ�yxy[���ţ�1206]
		 * @���ڣ�2016-12-23 ����01:59:20
		 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
		 * @return
		 * boolean �������� 
		 * @throws
		 */
		public boolean checkNullRow(HashMap<String, String> yzMap){
			boolean rs = true;
			for (String key : yzMap.keySet()) {
				if(StringUtils.isNotNull(yzMap.get(key))){
					rs = false;
					break;
				}
			}
			return rs;
		}
		
		/**
		 * 
		 * @����: ��֤�ǿ���
		 * @���ߣ�yxy[���ţ�1206]
		 * @���ڣ�2016-12-23 ����02:17:09
		 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
		 * @return
		 * boolean �������� 
		 * @throws
		 */
		public boolean checkNotNull(HashMap<String, String> yzMap){
			boolean rs = true;
			for (String key : yzMap.keySet()) {
				if(StringUtils.isNull(yzMap.get(key)) && key.indexOf("shcdmc") == -1  && !("bz").equals(key)){
					rs = false;
					break;
				}
			}
			if(!rs){
				return rs;
			}
			 rs = false;
			 for (String key : yzMap.keySet()) {
					if(StringUtils.isNotNull(yzMap.get(key)) && key.indexOf("shcdmc") != -1){
						rs = true;
						break;
					}
				}
			return rs;
		}
		
		 /**
	     * 
	     * @����: ��ȡ������Ҫ�ķ�������
	     * @���ߣ�����Դ[���ţ�1206]
	     * @���ڣ�2017-3-14 ����01:56:17
	     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	     * @param t
	     * @param user
	     * @return
	     * @throws Exception
	     * List<HashMap<String,String>> �������� 
	     * @throws
	     */
		public List<HashMap<String, String>> getGroupLddmList(CcdjForm t)
		throws Exception {
			return dao.getGroupLddmList(t);
	    }
		
		/**
		 * @throws Exception 
		 * 
		 * @����: �����ļ�
		 * @���ߣ�����Դ[���ţ�1206]
		 * @���ڣ�2017-3-14 ����03:03:42
		 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
		 * @param ccdj
		 * @return
		 * File �������� 
		 * @throws
		 */
		public File createDcFile(List<HashMap<String, String>> dataList,String ldmc,String username,List<HashMap<String, String>> wpList) throws Exception{
			File tempFile = new File(System.getProperty("java.io.tmpdir")+"/"+Calendar.getInstance().getTimeInMillis()+username+"-"+ldmc+".xls");
			if(!tempFile.exists()){
				tempFile.createNewFile();
			}
			WritableWorkbook wwb = Workbook.createWorkbook(tempFile);
			WritableSheet ws = wwb.createSheet("�Ʋ��Ǽ����", 0);
			String title =  ldmc+"��¥����Ʋ�����";
			WritableCellFormat titlethead =  this.getFontStyle("titlehead");//��ͷ��ʽ
			WritableCellFormat titlefont =  this.getFontStyle("title");//ָ����
			WritableCellFormat titlebody =  this.getFontStyle("titlebody");//����
			Label TitleLabel = new Label(0, 0, title, titlethead);
			ws.addCell(TitleLabel);
			ws.mergeCells(0, 0, 6+wpList.size()-1, 0);
			Label row2col1 = new Label(0, 1, "ѧ��", titlefont);
			Label row2col2 = new Label(1, 1, "ѧ��", titlefont);
			Label row2col3 = new Label(2, 1, "¥��", titlefont);
			Label row2col4 = new Label(3, 1, "���Һ�", titlefont);
			ws.addCell(row2col1);
			ws.addCell(row2col2);
			ws.addCell(row2col3);
			ws.addCell(row2col4);
			int i = 1;
			for (HashMap<String, String> hashMap : wpList) {
				Label tempLabel = new Label(3+i, 1, hashMap.get("wpmc"), titlefont);
				ws.addCell(tempLabel);
				i++;
			}
			int wpnum = wpList.size();
			Label row2colz = new Label(4+wpnum, 1, "���ΰ༶", titlefont);
			Label row2colx = new Label(5+wpnum, 1, "��ע", titlefont);
			Label row2coly = new Label(6+wpnum, 1, "�����ܼ�(Ԫ)", titlefont);
			ws.addCell(row2colz);
			ws.addCell(row2colx);
			ws.addCell(row2coly);
			if(dataList != null && dataList.size() > 0){
				for (int j = 0; j < dataList.size(); j++) {
					Label rowjcol1 = new Label(0, j+2, dataList.get(j).get("xn"), titlebody);
					Label rowjcol2 = new Label(1, j+2, dataList.get(j).get("xqmc"), titlebody);
					Label rowjcol3 = new Label(2, j+2, dataList.get(j).get("ldmc"), titlebody);
					Label rowjcol4 = new Label(3, j+2, dataList.get(j).get("qsh"), titlebody);
					ws.addCell(rowjcol1);
					ws.addCell(rowjcol2);
					ws.addCell(rowjcol3);
					ws.addCell(rowjcol4);
					for (int k = 0; k < wpnum; k++) {
						Label tempLabel = new Label(4+k, j+2, dataList.get(j).get("shcd"+k), titlebody);
						ws.addCell(tempLabel);
					}
					Label rowhcolz = new Label(4+wpnum, j+2, dataList.get(j).get("bjmc"), titlebody);
					Label rowjcolx =  new Label(5+wpnum, j+2, dataList.get(j).get("bz"), titlebody);
					//Label rowjcoly =  new Label(6+wpnum, j+2, dataList.get(j).get("zje"), titlebody);
					Label rowjcoly =  new Label(6+wpnum, j+2, dataList.get(j).get("avgje"), titlebody);
					ws.addCell(rowhcolz);
					ws.addCell(rowjcolx);
					ws.addCell(rowjcoly);
				}
			}
			wwb.write();
			wwb.close();
			return tempFile;
		}
		
		/**
		 * @throws Exception 
		 * 
		 * @����: ��ȡFile����List
		 * @���ߣ�����Դ[���ţ�1206]
		 * @���ڣ�2017-3-16 ����11:13:03
		 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
		 * @param groupList
		 * @param dataList
		 * @return
		 * List<File> �������� 
		 * @throws
		 */
		public List<File> getFileArryList(List<HashMap<String, String>> groupList,List<HashMap<String, String>> dataList,List<HashMap<String, String>> wpList,String username) throws Exception{
			List<File> files = new ArrayList<File>();
			for (int i = 0; i < groupList.size(); i++) {
				List<HashMap<String, String>> paralist = new ArrayList<HashMap<String,String>>();
				String lddm = groupList.get(i).get("lddm");
				String ldmc = groupList.get(i).get("ldmc");
				for (int j = 0; j < dataList.size(); j++) {
					if(lddm.equals(dataList.get(j).get("lddm"))){
						paralist.add(dataList.get(j));
					}
				}
				File file = this.createDcFile(paralist, ldmc, username, wpList);
				files.add(file);
			}
			return files;
		}
		
		
		/** 
		 * @����:�Ʋ��Ǽǵ���(��Ҫ�ְ༶�Լ����ֿ�����)
		 * @���ߣ�����[���ţ�1282]
		 * @���ڣ�2017-5-26 ����10:39:41
		 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
		 * @param form
		 * @param user
		 * @return
		 * @throws Exception
		 * List<HashMap<String,String>> �������� 
		 * @throws 
		 */
		public List<HashMap<String, String>> getAllListForDc(CcdjForm form,User user) throws Exception{
			Pages pages = (Pages) form.getClass().getMethod("getPages").invoke(form);
			pages.setPageSize(Integer.MAX_VALUE);
			
			form.getClass().getMethod("setPages",Pages.class).invoke(form, pages);
			
			return dao.getPageListForDc(form, user);
		}
		
}
