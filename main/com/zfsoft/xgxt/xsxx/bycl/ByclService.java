package com.zfsoft.xgxt.xsxx.bycl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jxl.CellView;
import jxl.Sheet;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.NumberFormats;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import xgxt.action.Base;
import xgxt.form.User;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ѧ����Ϣ
 * @�๦������:��ҵ����
 * @���ߣ� Qilm[����:964]
 * @ʱ�䣺 2013-12-5 ����03:31:54 
 * @�汾�� V1.0
 */
public class ByclService extends SuperServiceImpl<ByclForm, ByclDAO> {
	
	private ByclDAO dao = new ByclDAO();
	private static final String DATE_FORMAT = "yyyy-MM-dd";
	
	public ByclService() {
		super.setDao(dao);
		
	}

	/** 
	 * @����: ��ѯȡ����������
	 * @���ߣ�Qilm[���ţ�964]
	 * @���ڣ�2013-12-6 ����09:02:53
	 * @param myForm
	 * @param user
	 * @return
	 * int �������� 
	 * @throws 
	 */
	public int getCounts(ByclForm myForm, User user) throws Exception {
		return dao.getCounts(myForm, user);
	}
	
	/**
	 * 
	 * @����: ���±�ҵ����
	 * @���ߣ�Qilm[���ţ�964]
	 * @���ڣ�2013-12-6 ����10:30:31
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean runUpdate(ByclForm model, User user) throws Exception{
		
		boolean bolFlg = dao.runUpdate(model, user);
		if(bolFlg){
			new Thread(new Base.initialBj()).start();
		}
		return bolFlg;
	}

	/** 
	 * @����: ȡ����ҵ����
	 * @���ߣ�Qilm[���ţ�964]
	 * @���ڣ�2013-12-6 ����01:36:02
	 * @param myForm
	 * @param user
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean runDelete(ByclForm model, User user) throws Exception{

		boolean bolFlg = dao.runDelete(model, user);
		if(bolFlg){
			new Thread(new Base.initialBj()).start();
		}
		return bolFlg;
	}
	/**
	 * �յ�list
	 * @param size
	 * @return
	 */
	public List<HashMap<String,String>> getBlankList(int size){
		
		List<HashMap<String,String>> list = new ArrayList<HashMap<String,String>>(size);
		
		for (int i = 0 ; i < size ; i++){
			list.add(new HashMap<String, String>());
		}
		
		return list;
	}
	
	/**
	 * @����: ����ģ���ҵѧ����Ϣ����
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2017-10-10 ����03:33:34
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param os
	 * @throws Exception
	 * void �������� 
	 * @throws
	 */
	public void createWwb(OutputStream os) throws Exception{
		/*�õ��Ѵ���Excel���Ĺ�����*/
		WritableWorkbook wwb = Workbook.createWorkbook(os);
		WritableCellFormat titlefont =  this.getFontStyle("title");
		/*Sheet1����*/
		WritableSheet ws = wwb.createSheet("���ݵ���ģ��", 0);
		Label row1col1= new Label(0, 0, "ѧ��",titlefont);
		ws.addCell(row1col1);
		WritableCellFormat wcfF = new WritableCellFormat(NumberFormats.TEXT); 
		/*����һ������ʾ��ʽ*/
	    CellView cv = new CellView();
	    /*�Ѷ���ĵ�Ԫ���ʽ��ʼ����ȥ*/
	    cv.setFormat(wcfF);
	    /*�����п�ȣ������õĻ���0��������ʾ��*/
	    cv.setSize(20*265);
	    for (int i = 0; i < 10; i++) {
	    	ws.setColumnView(i, cv);
		}
	    try{
		 wwb.write();
		 wwb.close();
		}catch(Exception e){
				
		}
	}
	
	/**
	 * @����: �����ҵѧ����Ϣ����ģ��Excleģ��
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2017-10-10 ����03:36:37
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param paras
	 * @return
	 * @throws WriteException
	 * WritableCellFormat �������� 
	 * @throws
	 */
	public WritableCellFormat getFontStyle(String paras) throws WriteException{
		/*���嵥Ԫ����ʽ*/ 
		if("title".equals(paras)){
			  WritableFont wf_table = new WritableFont(WritableFont.ARIAL, 10,  
		                WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE,  
		                jxl.format.Colour.BLACK);  
			  WritableCellFormat wcf_table = new WritableCellFormat(wf_table);  
	          wcf_table.setAlignment(jxl.format.Alignment.CENTRE);
	          wcf_table.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
	          return wcf_table;
		}else if("errorinfo".equals(paras)){
			WritableFont wf_table = new WritableFont(WritableFont.ARIAL, 10,  
	                WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE,  
	                jxl.format.Colour.RED);   
		  WritableCellFormat wcf_table = new WritableCellFormat(wf_table);   
          wcf_table.setAlignment(jxl.format.Alignment.CENTRE);
          wcf_table.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
          return wcf_table;
		}
        return null;
	}
	
	/**
	 * @����: ��Ϣ���뱣��
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2017-10-12 ����11:37:03
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param is
	 * @param byclForm
	 * @return
	 * HashMap<String,Object> �������� 
	 * @throws
	 */
	public HashMap<String, Object> saveDrExcelInfo(InputStream is,ByclForm byclForm){
		HashMap<String, Object> resultMap= null;
		try{
			resultMap= this.DrExcelInfoCheck(is, byclForm);
			/*�ж�excel����Ƿ�Ϊ��*/
			if("null".equals(resultMap.get("result")) || resultMap.isEmpty() || "hbyzsb".equals(resultMap.get("result")) || "excelrepeat".equals(resultMap.get("result"))){
				return resultMap;
			}else if("true".equals(resultMap.get("result"))){
				List<String[]> paralist = (List<String[]>) resultMap.get("drlist");
				if( paralist == null ||paralist.size() == 0 ){
					resultMap.put("result", "null");
					return resultMap;
				}
				 
			  	 dao.runUpdateForDr(paralist,byclForm);
			}else{
				String gid = this.uploadErrorExcel((List<String[]>) resultMap.get("errorlist"),byclForm.getFilepath());
				resultMap.put("gid", gid);
				List<String[]> paralist = (List<String[]>) resultMap.get("drlist");
				 dao.runUpdateForDr(paralist,byclForm);
				logger.info("����ʧ�ܣ�");
			}
		}catch (Exception e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}
		return resultMap;
	}
	
	/**
	 * @����: ������Ϣ���
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2017-10-12 ����11:37:31
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param is
	 * @param byclForm
	 * @return
	 * HashMap<String,Object> �������� 
	 * @throws
	 */
	public HashMap<String, Object> DrExcelInfoCheck(InputStream is,ByclForm byclForm){
		/*�����¼����*/
		List<String[]> drlist = new ArrayList<String[]>();
		/*�����¼����*/
		List<String[]> errorlist = new ArrayList<String[]>();
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("result", "true");
		Workbook rwb = null;
		try {
			rwb = Workbook.getWorkbook(is);
			Sheet rs=rwb.getSheet(0);
			/*�õ����е���*/
		    int clos=rs.getColumns();
		    /*�õ����е���*/
	        int rows=rs.getRows();
	        /*�ж�excel����Ƿ�Ϊ��*/
	        if(rows < 2){
	        	resultMap.put("result", "null");
	        }else if(!this.checkExcelRepeat(rs, clos, rows)){/*��֤�ظ����ݣ�ʵ��ֻҪ��֤ѧ�ż���*/
	        	resultMap.put("result", "excelrepeat");
	        }else{
//	          HashMap<String, String> hbMap = this.checkHbyz(rs, clos, rows, byclForm.getFilepath());
//	          if(!hbMap.get("message").equals("true")){
//	        	  resultMap.put("result", hbMap.get("message"));
//	        	  resultMap.put("gid", hbMap.get("gid"));
//	        	  return resultMap;
//	          }
	        	  for (int i = 1; i < rows; i++) {
	  	        		/*ȡ��ÿ����֤���ݣ�����lsmap*/
	  	        		HashMap<String, String>  lsmap = new HashMap<String, String>();
	  	        		String xh = rs.getCell(0, i).getContents();
	  	        		lsmap.put("xh",xh);
	  	        		/*�����ж�*/
	  	        	    if(this.checkNullRow(lsmap)){
	  	        	    	continue;
	  	        	    }
	  	        		
	  	        		HashMap<String,Object> resultmap = this.checkEveryRowRecord(lsmap, byclForm);
	  	        		ArrayList<String> paralist = new ArrayList<String>();
	  	        		if("false".equals(resultmap.get("result"))){
	  	        			paralist.add(lsmap.get("xh"));
	  	        			if(resultmap.get("resultmap") != null ){
	  	        				Map<String, String> map = (HashMap<String, String>) resultmap.get("resultmap");
	  	        				for (Map.Entry<String, String> entry : map.entrySet()){
	  	        					if(entry.getKey().indexOf("yz") != -1){
	  	        						paralist.add(entry.getValue());
	  	        					}
	  	        					
	  	        				}
	  	        			}
	  	        			errorlist.add(paralist.toArray(new String[]{}));
	  	        			resultMap.put("result", "false");
	  	        		}else{
	  	        			paralist.add(lsmap.get("xh"));
	  	        			paralist.add("0");
	  	        			drlist.add(paralist.toArray(new String[]{}));
	  	        		}
	  			}
	        	  resultMap.put("drlist", drlist);
	        	  resultMap.put("errorlist", errorlist);
	        }
		} catch (BiffException e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		} catch (IOException e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}
		
		return resultMap;
	}

	/**
	 * @����: ��������Ƿ����ظ����ݣ��ظ���ѧ�ţ�
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2017-10-12 ����11:39:39
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
			String xh = rs.getCell(0, i).getContents();
			String str = "";
			if(StringUtils.isNotNull(xh)){
				str = str + xh.trim();
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
	 * @����: ������֤
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2017-10-12 ����11:39:53
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param rs
	 * @param cols
	 * @param rows
	 * @param filepath
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String> checkHbyz(Sheet rs,int cols,int rows,String filepath){
		boolean result = true;
		List<String> containCfList = new ArrayList<String>();
		List<HashMap<String,String>> yzList = new ArrayList<HashMap<String,String>>();
		for (int i = 1; i < rows; i++) {
		if(!containCfList.contains(i+"")){
			String xh = rs.getCell(0, i).getContents();
			if(StringUtils.isNull(xh)){
				continue;
			}
				HashMap<String, String> dataMap = this.checkIsFhJfhbDr(xh);
				if(("false").equals(dataMap.get("rs"))){
					result = false;
					dataMap.put("xh",xh);
					yzList.add(dataMap);
				}
			}
		}
		String gid = "";
		String message = "true";
		if(!result){
			message = "hbyzsb";
			try {
				 gid = this.uploadErrorExcelHb(yzList, filepath);
			} catch (Exception e) {
				// TODO �Զ����� catch ��
				e.printStackTrace();
			}
		}
		HashMap<String, String> resultMap = new HashMap<String, String>();
		resultMap.put("gid", gid);
		resultMap.put("message", message);
		return resultMap;
	}
	
	/**
	 * @����: ������֤
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2017-10-11 ����10:21:01
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param yzMap
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
	
	public HashMap<String, String> checkIsFhJfhbDr(String xh){
		return dao.checkIsFhJfhbDr(xh);
	}
	
	/**
	 * @����: ÿ��������֤
	 * @���ߣ�Meng.Wei[���ţ�1186]
	 * @���ڣ�2017-10-11 ����11:54:02
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param lsmap
	 * @param byclForm
	 * @return
	 * HashMap<String,Object> �������� 
	 * @throws
	 */
	public HashMap<String, Object> checkEveryRowRecord(HashMap<String, String> lsmap,ByclForm byclForm){
		String message = "true";
		/*��֤ѧ���Ƿ����Ҫ��*/
		if(!this.checkNotNull(lsmap)){
			message = "false";
			lsmap.put("kyz", "ѧ��");
		}else{
			/*��֤ѧ���Ƿ����ѧ����Ϣ��*/
			String xhyz = dao.sfczXsxxb(lsmap.get("xh"));
			if(!"1".equals(xhyz)){
				message = "false";
				lsmap.put("xhbczXsxxbyz", "�����ڴ�ѧ�ţ�");//ѧ��������ѧ����Ϣ��
			}
			
			/*��֤ѧ���Ƿ�Ϊ�Ǳ�ҵ��*/
//			String xhfby = dao.xhfby(lsmap.get("xh"));
//			if(!"0".equals(xhfby)){
//				message = "false";
//				lsmap.put("xhsBysyz", "��ѧ���Ѿ��Ǳ�ҵ����");//ѧ���Ǳ�ҵ��
//			}
		}
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("result", message);
		resultMap.put("resultmap", lsmap);
		return resultMap;
	}
	
	/**
	 * @����: ��֤�ǿ���
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2017-10-11 ����11:56:22
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param yzMap
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean checkNotNull(HashMap<String, String> yzMap){
		boolean rs = true;
		for (String key : yzMap.keySet()) {
			if(StringUtils.isNull(yzMap.get(key))){
				rs = false;
				break;
			}
		}
		return rs;
	}
	
	/**
	 * @����: �ϲ���֤
	 * @���ߣ�Meng.Wei[���ţ�1186]
	 * @���ڣ�2017-10-12 ����11:41:04
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param errorlist
	 * @param filepath
	 * @return
	 * @throws Exception
	 * String �������� 
	 * @throws
	 */
	public String uploadErrorExcelHb(List<HashMap<String, String>> errorlist,String filepath) throws Exception{
		String fileName = System.currentTimeMillis()+"error.xls";
		String path = filepath+fileName;
		File file=new File(path);
		
        if (!file.exists()) {
           file.createNewFile();
        }
        
        WritableWorkbook  wwb = Workbook.createWorkbook(file);
		try {
		  /*����������*/
          WritableSheet ws = wwb.createSheet("��������", 0);
      	  WritableCellFormat titlefont =  this.getFontStyle("title");
      	  /*sheet1*/
      	  Label row1col1= new Label(0, 0, "ѧ��",titlefont);
          
          try {
          	ws.addCell(row1col1);
			} catch (RowsExceededException e1) {
				// TODO �Զ����� catch ��
				e1.printStackTrace();
			} catch (WriteException e1) {
				// TODO �Զ����� catch ��
				e1.printStackTrace();
			}
          
          for (int i = 0; i < errorlist.size(); i++) {
        	  WritableCellFormat wcf = new WritableCellFormat();
				WritableFont wf = new WritableFont(WritableFont.ARIAL);
				wf.setColour(Colour.RED);
				wcf.setFont(wf);
				wcf.setAlignment(Alignment.CENTRE);
				Label labelId_0 = new Label(0, i+1,errorlist.get(i).get("xh") ,wcf);
          		try {
						ws.addCell(labelId_0);
					} catch (RowsExceededException e) {
						// TODO �Զ����� catch ��
						e.printStackTrace();
					} catch (WriteException e) {
						// TODO �Զ����� catch ��
						e.printStackTrace();
					}
          }
		}finally{
			 wwb.write();
			 try {
				wwb.close();
			} catch (WriteException e) {
				// TODO �Զ����� catch ��
				e.printStackTrace();
			}
		}
		return fileName;
	}
	
	/**
	 * @����: ������ϢExcle
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2017-10-12 ����11:41:32
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param errorlist
	 * @param filepath
	 * @return
	 * @throws Exception
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
		  /*����������*/
          WritableSheet ws = wwb.createSheet("��������", 0);
          WritableCellFormat titlefont =  this.getFontStyle("title");
   		  /*sheet1*/
   	      Label row1col1= new Label(0, 0, "ѧ��",titlefont);
           try {
           	ws.addCell(row1col1);
			} catch (RowsExceededException e1) {
				// TODO �Զ����� catch ��
				e1.printStackTrace();
			} catch (WriteException e1) {
				// TODO �Զ����� catch ��
				e1.printStackTrace();
			}
           
           for (int i = 0; i < errorlist.size(); i++) {
                for(int j = 0;j<errorlist.get(i).length;j++){
               	 Label labelId_i= null;
               	 if(j<=0){
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
							// TODO �Զ����� catch ��
							e.printStackTrace();
						} catch (WriteException e) {
							// TODO �Զ����� catch ��
							e.printStackTrace();
						}
               	 }
               		try {
							ws.addCell(labelId_i);
						} catch (RowsExceededException e) {
							// TODO �Զ����� catch ��
							e.printStackTrace();
						} catch (WriteException e) {
							// TODO �Զ����� catch ��
							e.printStackTrace();
						}
                }
           }
		}finally{
			 wwb.write();
			 try {
				wwb.close();
			} catch (WriteException e) {
				// TODO �Զ����� catch ��
				e.printStackTrace();
			}
		}
		return fileName;
	}
}
