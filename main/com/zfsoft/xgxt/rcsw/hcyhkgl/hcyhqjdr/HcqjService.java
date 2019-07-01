/**
 * <p>Coyright (R) 2014 ��������ɷ����޹�˾��<p>
 */
package com.zfsoft.xgxt.rcsw.hcyhkgl.hcyhqjdr;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jxl.Sheet;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Colour;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.rcsw.gjgl.gjjg.GjjgDao;

public class HcqjService extends SuperServiceImpl<HcqjForm,HcqjDao>{

	public HashMap<String, Object> saveDrExcelInfo(InputStream is,
			HcqjForm hcqjForm) {
		HashMap<String, Object> resultMap= null;
		try {
			 resultMap= this.DrExcelInfoCheck(is, hcqjForm);
			//�ж�excel����Ƿ�Ϊ��
			if("null".equals(resultMap.get("result"))){
				return resultMap;
			}else if("true".equals(resultMap.get("result"))){//����޴��󣬲������ݿ�
				List<String[]> paralist = (List<String[]>) resultMap.get("drlist");
				List<String[]> bplist = (List<String[]>) resultMap.get("bplist");
				if( paralist == null ||paralist.size() == 0 ){
					resultMap.put("result", "null");
					return resultMap;
				}
			  	 this.saveDrDataIntoDb(paralist,bplist);
			  	resultMap.put("zqts",paralist.size());//��ȷ����
			  	resultMap.put("cwts",0);//��������
			}else{//����д������ݣ���ļ�¼���ɴ����ļ��ṩ���أ���ȷ���ݲ������ݿ�
				List<String[]> zqlist = (List<String[]>) resultMap.get("drlist");
				List<String[]> bplist = (List<String[]>) resultMap.get("bplist");
				List<String[]> errorlist = (List<String[]>) resultMap.get("errorlist");
				String gid = this.uploadErrorExcel((List<String[]>) resultMap.get("errorlist"),hcqjForm.getFilepath());
				resultMap.put("gid", gid);
				this.saveDrDataIntoDb(zqlist,bplist);
				resultMap.put("zqts",zqlist.size());//��ȷ����
			  	resultMap.put("cwts",errorlist.size());//��������
				logger.info("����ʧ�ܣ�");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultMap;
	}

	private String uploadErrorExcel(List<String[]> errorlist, String filepath) throws Exception {
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
            Label labelxh= new Label(0, 0, "ѧ��");//��ʾ��
            Label labelxm= new Label(1, 0, "����");
            Label labelhcyhk= new Label(2, 0, "���Ż�����");
            try {
            	ws.addCell(labelxh);
				ws.addCell(labelxm);
	            ws.addCell(labelhcyhk);
			} catch (RowsExceededException e1) {
				e1.printStackTrace();
			} catch (WriteException e1) {
				e1.printStackTrace();
			}
            
            for (int i = 0; i < errorlist.size(); i++) {
                 for(int j = 0;j<errorlist.get(i).length;j++){
                	 Label labelId_i= null;
                	 if(j<=2){
                		  labelId_i= new Label(j, i+1, errorlist.get(i)[j]+"");
                		  ws.addCell(labelId_i);
                	 }else{
                		 WritableCellFormat wcf = new WritableCellFormat();
         				WritableFont wf = new WritableFont(WritableFont.ARIAL);
         				try {
							wf.setColour(Colour.RED);
							wcf.setFont(wf);
							wcf.setAlignment(Alignment.CENTRE);
							labelId_i = new Label(j, i+1, errorlist.get(i)[j],wcf);
							ws.addCell(labelId_i);
						} catch (RowsExceededException e) {
							e.printStackTrace();
						} catch (WriteException e) {
							e.printStackTrace();
						}
         				
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

	private boolean saveDrDataIntoDb(List<String[]> paralist, List<String[]> bplist) throws Exception {
		boolean flag=false;
		if(paralist != null && paralist.size() > 0){
			flag =  dao.saveDrDataIntoDb(paralist).length >0 ? true :false;
			if (flag) {
				//����bpmx sys_xsxxb
				flag=dao.updateBpmx(bplist);
			}
		}
		return flag;
		
	}

	private HashMap<String, Object> DrExcelInfoCheck(InputStream is,
			HcqjForm hcqjForm) {
		//�����¼����
		List<String[]> drlist = new ArrayList<String[]>();
		//����bpmx��
		List<String[]> bplist = new ArrayList<String[]>();
		//�����¼����
		List<String[]> errorlist = new ArrayList<String[]>() ;
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("result", "true");
		Workbook rwb = null;
		try {
			rwb = Workbook.getWorkbook(is);
			Sheet rs=rwb.getSheet(0);//ȡ��һ��������
		    int clos=rs.getColumns();//�õ����е���
	        int rows=rs.getRows();//�õ����е���
	        //�ж�excel����Ƿ�Ϊ��
	        if(clos < 3  || rows < 2){
	        	resultMap.put("result", "null");
	        }else{
	        	  //�ж�excel�������ظ�����
	        	String [] flag = new String[rows];
	        	for (int i = 1; i < rows; i++) {
	        		//ȡ��ÿ����֤���ݣ�����lsmap
        			HashMap<String, String>  lsmap = new HashMap<String, String>();
        			String xh = rs.getCell(0, i).getContents();
        			String xm = rs.getCell(1, i).getContents();
        			String hcyhk = rs.getCell(2, i).getContents();
        			if(StringUtils.isNull(xh.trim()) && StringUtils.isNull(xm.trim()) && 
        					StringUtils.isNull(hcyhk.trim())){
        				continue;
        			}
        			
        			String one = xh+hcyhk+xm;
        			int k = this.getIndex(flag,one);
        			if (k==-1) {
        				flag[i]=xh+hcyhk+xm;
					}else {//��������д��ڣ�˵���ظ�����ʱ��ȡ���ظ���ѧ����Ϣ�����д����������excel��
	        			lsmap.put("xh", xh);
	        			lsmap.put("xm", xm);
	        			lsmap.put("hcyhk", hcyhk);
	        			ArrayList<String> cflist = new ArrayList<String>();
	        			cflist.add(lsmap.get("xh"));
	        			cflist.add(lsmap.get("xm"));
	        			cflist.add(lsmap.get("hcyhk"));
	        			cflist.add("��ǰѧ�������ظ���");
	        			errorlist.add(cflist.toArray(new String[]{}));	
	        			resultMap.put("result", "sjcf");
					}
	        	}
	        	if(errorlist.size()==0){
	        		for (int i = 1; i < rows; i++) {
	        			//ȡ��ÿ����֤���ݣ�����lsmap
	        			HashMap<String, String>  lsmap = new HashMap<String, String>();
	        			String xh = rs.getCell(0, i).getContents();
	        			String xm = rs.getCell(1, i).getContents();
	        			String hcyhk = rs.getCell(2, i).getContents();
	        			if(StringUtils.isNull(xh.trim()) && StringUtils.isNull(xm.trim()) && 
	        					StringUtils.isNull(hcyhk.trim())){
	        				continue;
	        			}
	        			
	        			lsmap.put("xh", xh);
	        			lsmap.put("xm", xm);
	        			lsmap.put("hcyhk", hcyhk);
	        			HashMap<String,Object> resultmap = this.checkEveryRowRecord(lsmap, hcqjForm);//ѭ����֤���ݸ�ʽ
	        			ArrayList<String> paralist = new ArrayList<String>();
	        			ArrayList<String> bpmlist = new ArrayList<String>();
	        			if("false".equals(resultmap.get("result"))){
	        				paralist.add(lsmap.get("xh"));
	        				paralist.add(lsmap.get("xm"));
	        				paralist.add(lsmap.get("hcyhk"));
	        				
	        				bpmlist.add(lsmap.get("hcyhk"));
	        				bpmlist.add(lsmap.get("xh"));
	        				if(resultmap.get("resultmap") != null ){
	        					Map<String, String> map = (HashMap<String, String>) resultmap.get("resultmap");
	        					for (Map.Entry<String, String> entry : map.entrySet()){
	        						if(!entry.getKey().equals("xh") && !entry.getKey().equals("xm")
	        								&& !entry.getKey().equals("hcyhk")){
	        							paralist.add(entry.getValue());
	        						}
	        					}
	        				}
	        				errorlist.add(paralist.toArray(new String[]{}));
	        				resultMap.put("result", "false");
	        			}else{
	        				paralist.add(lsmap.get("xh"));
	        				paralist.add(lsmap.get("xm"));
	        				paralist.add(lsmap.get("hcyhk"));
	        				
	        				bpmlist.add(lsmap.get("hcyhk"));
	        				bpmlist.add(lsmap.get("xh"));
	        				
	        				drlist.add(paralist.toArray(new String[]{}));
	        				bplist.add(bpmlist.toArray(new String[]{}));
	        			}
	        		}
	        	}
	        	
	        		resultMap.put("drlist", drlist);
	        		resultMap.put("bplist", bplist);
	        		resultMap.put("errorlist", errorlist);
	        	
	        }
	      
		} catch (BiffException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return resultMap;
	}

	private HashMap<String, Object> checkEveryRowRecord(
			HashMap<String, String> lsmap, HcqjForm hcqjForm) {
		/**
		 * ��֤����
		 * 1.xh,xm������֤�Ƿ����xsxxb
		 * 2.�����¼��ʦ�����ݷ�Χ��֤
		 */
		String message = "true";
		GjjgDao gjjgDao = new GjjgDao();
		HcqjDao hcqjDao = new HcqjDao();
		//Ϊ����֤
		if(StringUtils.isNull(lsmap.get("xh")) || StringUtils.isNull(lsmap.get("xm"))
				|| StringUtils.isNull(lsmap.get("hcyhk"))){
				message = "false";
				lsmap.put("kyz", "ѧ�ţ����������Ż����䲻��Ϊ�գ�");
		}else{
			//xh,xm������֤�Ƿ����xsxxb
			if(!gjjgDao.checkXhXmIsExist(lsmap.get("xh"),lsmap.get("xm"))){
				message = "false";
				lsmap.put("xhxmlhyz", "ѧ��/��������Ӧ�򲻴���ѧ����Ϣ���У�");
			}
			//�����¼��ʦ�� ���ݷ�Χ��֤
			if(!hcqjDao.checkJsDrSjfw(lsmap.get("xh"), hcqjForm)){
				message = "false";
				lsmap.put("sjfwyz", "û��Ȩ�޵����ѧ����");
			}
			//�ظ�������֤
			if(hcqjDao.checkXhYhkIsExist(lsmap.get("xh"),lsmap.get("xm"),lsmap.get("hcyhk"))){
				message = "false";
				lsmap.put("cfdryz", "�ظ����룡");
			}
		}
		
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("result", message);
		resultMap.put("resultmap", lsmap);
		return resultMap;
	}

	private int getIndex(String[] arr, String one) {  
        for ( int i = 0; i < arr.length; i++) {  
            if (one.equals(arr[i])) {  
                return i;  
            }  
        }  
        return -1;  //��������û���򷵻�-1  
}

	public boolean clearBpmx(String[] x) throws Exception {
		// TODO Auto-generated method stub
		return dao.claerBpmx(x);
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
