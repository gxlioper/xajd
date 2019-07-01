/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2017-6-27 ����02:57:33 
 */  
package com.zfsoft.xgxt.xpjpy.zhcp.zhpxf;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
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
import xgxt.action.Base;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.rcsw.gjgl.gjjg.GjjgDao;
import com.zfsoft.xgxt.zjly.ylbx.YlbxDao;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� CP[����:1352]
 * @ʱ�䣺 2017-6-27 ����02:57:33 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class zhpxfService extends SuperServiceImpl<zhpxfForm,zhpxfDao>{
	private zhpxfDao dao= new zhpxfDao();
	public zhpxfService(){
		 setDao(dao);
	 }
	 
	/** 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�CP[���ţ�1352]
	 * @���ڣ�2017-6-28 ����04:10:14
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param inputStream
	 * @param model
	 * @return
	 * HashMap<String,Object> �������� 
	 * @throws 
	 */
		public HashMap<String, Object> saveDrExcelInfo(InputStream is,
			zhpxfForm model) {
		HashMap<String, Object> resultMap= null;
		try {
			 resultMap= this.DrExcelInfoCheck(is, model);
			//�ж�excel����Ƿ�Ϊ��
			if("null".equals(resultMap.get("result"))){
				return resultMap;
			}else if("true".equals(resultMap.get("result"))){//����޴��󣬲������ݿ�
				List<String[]> paralist = (List<String[]>) resultMap.get("drlist");
				if( paralist == null ||paralist.size() == 0 ){
					resultMap.put("result", "null");
					return resultMap;
				}
			  	 this.saveDrDataIntoDb(paralist);
			  	resultMap.put("zqts",paralist.size());//��ȷ����
			  	resultMap.put("cwts",0);//��������
			}else{//����д������ݣ���ļ�¼���ɴ����ļ��ṩ���أ���ȷ���ݲ������ݿ�
				List<String[]> zqlist = (List<String[]>) resultMap.get("drlist");
				List<String[]> gxlist = (List<String[]>) resultMap.get("gxlist");
				List<String[]> errorlist = (List<String[]>) resultMap.get("errorlist");
				String gid = this.uploadErrorExcel((List<String[]>) resultMap.get("errorlist"),model.getFilepath());
				resultMap.put("gid", gid);
				this.saveDrDataIntoDb(zqlist);
				this.saveGxDataIntoDb(gxlist);
				resultMap.put("zqts",zqlist.size());//��ȷ����
				resultMap.put("gxts",gxlist.size());//��������
			  	resultMap.put("cwts",errorlist.size());//��������
				logger.info("����ʧ�ܣ�");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultMap;
	}

		
		
	
		/** 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�CP[���ţ�1352]
	 * @���ڣ�2017-6-28 ����04:11:21
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param list
	 * @param filepath
	 * @return
	 * String �������� 
	 * @throws 
	 */
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
	            Label labelxn= new Label(0, 0, "ѧ��");
	            Label labelxh= new Label(1, 0, "ѧ��");
	            Label labelxm= new Label(2, 0, "����");
	            Label labelzhpxf= new Label(3, 0, "�ۺ�Ʒ�з�");
	            try {
	            	ws.addCell(labelxn);
					ws.addCell(labelxh);
		            ws.addCell(labelxm);
					ws.addCell(labelzhpxf);
				} catch (RowsExceededException e1) {
					e1.printStackTrace();
				} catch (WriteException e1) {
					e1.printStackTrace();
				}
	            
	            for (int i = 0; i < errorlist.size(); i++) {
	                 for(int j = 0;j<errorlist.get(i).length;j++){
	                	 Label labelId_i= null;
	                	 if(j<=3){
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




		/** 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�CP[���ţ�1352]
	 * @���ڣ�2017-6-28 ����04:11:17
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param paralist
	 * void �������� 
	 * @throws 
	 */
		private boolean saveDrDataIntoDb(List<String[]> paralist)throws Exception {
			if(paralist != null && paralist.size() > 0){
				return dao.saveDrDataIntoDb(paralist).length >0 ? true :false;
			}else{
				return false;
			}
			
		}
		/**
		 * @throws Exception  
		 * @����:TODO(������һ�仰�����������������)
		 * @���ߣ��Ų�·[���ţ�982]
		 * @���ڣ�2017-9-21 ����10:37:42
		 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
		 * @param gxlist
		 * void �������� 
		 * @throws 
		 */
		private boolean saveGxDataIntoDb(List<String[]> gxlist) throws Exception {
			if(gxlist != null && gxlist.size() > 0){
				return dao.saveGxDataIntoDb(gxlist).length >0 ? true :false;
			}else{
				return false;
			}
			
		}



		@SuppressWarnings("unchecked")
		private HashMap<String, Object> DrExcelInfoCheck(InputStream is,
				zhpxfForm model) {
			//�����¼����
			List<String[]> drlist = new ArrayList<String[]>();
			//���¼�¼����
			List<String[]> gxlist = new ArrayList<String[]>();
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
		        if(clos < 4  || rows < 2){
		        	resultMap.put("result", "null");
		        }else{
		        	  //�ж�excel�������ظ�����
		        	String [] flag = new String[rows];
		        	for (int i = 1; i < rows; i++) {
		        		//ȡ��ÿ����֤���ݣ�����lsmap
	        			HashMap<String, String>  lsmap = new HashMap<String, String>();
	        			String xn = rs.getCell(0, i).getContents();
	        			String xh = rs.getCell(1, i).getContents();
	        			String xm = rs.getCell(2, i).getContents();
	        			String zhpxf = rs.getCell(3, i).getContents();
	        			if(StringUtils.isNull(xh.trim()) && StringUtils.isNull(xm.trim()) && 
	        					StringUtils.isNull(zhpxf.trim())&& 
	        					StringUtils.isNull(xn.trim()) ){
	        				continue;
	        			}
	        			
	        			String one = xh+xn;
	        			int k = this.getIndex(flag,one);
	        			if (k==-1) {
	        				flag[i]=xh+xn;
						}else {//��������д��ڣ�˵���ظ�����ʱ��ȡ���ظ���ѧ����Ϣ�����д����������excel��
		        			lsmap.put("xn", xn);
		        			lsmap.put("xh", xh);
		        			lsmap.put("xm", xm);
		        			lsmap.put("zhpxf", zhpxf);
		        			ArrayList<String> cflist = new ArrayList<String>();
		        			cflist.add(lsmap.get("xn"));
		        			cflist.add(lsmap.get("xh"));
		        			cflist.add(lsmap.get("xm"));
		        			cflist.add(lsmap.get("zhpxf"));
		        			cflist.add("��ǰѧ�������ظ���");
		        			errorlist.add(cflist.toArray(new String[]{}));	
		        			resultMap.put("result", "sjcf");
						}
		        	}
		        	if(errorlist.size()==0){
		        		for (int i = 1; i < rows; i++) {
		        			//ȡ��ÿ����֤���ݣ�����lsmap
		        			HashMap<String, String>  lsmap = new HashMap<String, String>();
		        			String xn = rs.getCell(0, i).getContents();
		        			String xh = rs.getCell(1, i).getContents();
		        			String xm = rs.getCell(2, i).getContents();
		        			String zhpxf = rs.getCell(3, i).getContents();
		        			if(StringUtils.isNull(xh.trim()) && StringUtils.isNull(xm.trim()) && 
		        					StringUtils.isNull(zhpxf.trim())&& 
		        					StringUtils.isNull(xn.trim()) ){
		        				continue;
		        			}
		        			lsmap.put("xn", xn);
		        			lsmap.put("xh", xh);
		        			lsmap.put("xm", xm);
		        			lsmap.put("zhpxf", zhpxf);
		        			HashMap<String,Object> resultmap = this.checkEveryRowRecord(lsmap, model);//ѭ����֤���ݸ�ʽ
		        			ArrayList<String> paralist = new ArrayList<String>();
		        			if("false".equals(resultmap.get("result"))){
		        				paralist.add(lsmap.get("xn"));
		        				paralist.add(lsmap.get("xh"));
		        				paralist.add(lsmap.get("xm"));
		        				paralist.add(lsmap.get("zhpxf"));
		        				if(resultmap.get("resultmap") != null ){
		        					Map<String, String> map = (HashMap<String, String>) resultmap.get("resultmap");
		        					for (Map.Entry<String, String> entry : map.entrySet()){
		        						if(!entry.getKey().equals("xn") && !entry.getKey().equals("xh")
		        								&& !entry.getKey().equals("zhpxf") && !entry.getKey().equals("xm")	
		        								){
		        							paralist.add(entry.getValue());
		        						}
		        					}
		        				}
		        				errorlist.add(paralist.toArray(new String[]{}));
		        				resultMap.put("result", "false");
		        			}else if ("cfsj".equals(resultmap.get("result"))) {
		        				paralist.add(lsmap.get("xm"));
		        				paralist.add(lsmap.get("zhpxf"));
		        				paralist.add(lsmap.get("xn"));
		        				paralist.add(lsmap.get("xh"));
		        				gxlist.add(paralist.toArray(new String[]{}));
		        				resultMap.put("result", "false");
							}else{
		        				paralist.add(lsmap.get("xn"));
		        				paralist.add(lsmap.get("xh"));
		        				paralist.add(lsmap.get("xm"));
		        				paralist.add(lsmap.get("zhpxf"));
		        				drlist.add(paralist.toArray(new String[]{}));
		        			}
		        		}
		        	}
		        		resultMap.put("drlist", drlist);
		        		resultMap.put("gxlist", gxlist);
		        		resultMap.put("errorlist", errorlist);
		        }
		      
			} catch (BiffException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
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
		
		
		
		
		
		private HashMap<String, Object> checkEveryRowRecord(
				HashMap<String, String> lsmap, zhpxfForm model) {
			
			String message = "true";
			GjjgDao gjjgDao = new GjjgDao();
			zhpxfDao zhpxfDao = new zhpxfDao();
			//Ϊ����֤
			if(StringUtils.isNull(lsmap.get("xn")) || StringUtils.isNull(lsmap.get("xh"))
					|| StringUtils.isNull(lsmap.get("zhpxf")) || StringUtils.isNull(lsmap.get("xm")) ){
					message = "false";
					lsmap.put("kyz", "ѧ�꣬ѧ�ţ��������ۺ�Ʒ�зֲ���Ϊ�գ�");
			}else if(!Base.beforXn.equals(lsmap.get("xn"))){
				message = "false";
				lsmap.put("xycw", "ֻ�ܵ���"+Base.beforXn+"ѧ�������!");
			}else if(!StringUtils.isNumber(lsmap.get("zhpxf"))){
				message = "false";
				lsmap.put("pxfcw", "Ʒ�з�ֻ����������");
			}else if(!gjjgDao.checkXhXmIsExist(lsmap.get("xh"),lsmap.get("xm"))){
					message = "false";
					lsmap.put("xhxmlhyz", "ѧ��/��������Ӧ�򲻴���ѧ����Ϣ���У�");
			}else if(!zhpxfDao.checkJsDrSjfw(lsmap.get("xh"), model)){//�����¼��ʦ�� ���ݷ�Χ��֤
					message = "false";
					lsmap.put("sjfwyz", "û��Ȩ�޵����ѧ����");
			}else if(zhpxfDao.checkXhQjsjIsExist(lsmap.get("xn"),lsmap.get("xh"))){
					message = "cfsj";
					lsmap.put("cfdryz", "��ǰѧ��ѧ���ۺ�Ʒ�з������Ѵ��ڣ�");
				}
			HashMap<String, Object> resultMap = new HashMap<String, Object>();
			resultMap.put("result", message);
			resultMap.put("resultmap", lsmap);
			return resultMap;
		}

		/** 
		 * @����:TODO(������һ�仰�����������������)
		 * @���ߣ��Ų�·[���ţ�982]
		 * @���ڣ�2017-7-31 ����10:04:57
		 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
		 * @param myform
		 * @return
		 * zhpxfForm �������� 
		 * @throws 
		 */
		public HashMap<String, String>  getAllData(zhpxfForm myform) {
			// TODO �Զ����ɷ������
			return dao.getAllData(myform);
		}

		
		
		
		
		
		
		
}
