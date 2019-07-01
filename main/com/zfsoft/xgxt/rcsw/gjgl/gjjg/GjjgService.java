/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-11-29 ����04:18:53 
 */  
package com.zfsoft.xgxt.rcsw.gjgl.gjjg;

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

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @���ߣ� CP[����:1352]
 * @ʱ�䣺 2016-11-29 ����04:18:53 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class GjjgService extends SuperServiceImpl<GjjgForm, GjjgDao> {
	private GjjgDao rd = new GjjgDao();

	public GjjgService() {
		setDao(rd);
	}
	
	public HashMap<String, String> getFdyxx(String zgh){
		return dao.getFdyxx(zgh);
	}

	/** 
	 * @����:����
	 * @���ߣ�CP[���ţ�1352]
	 * @���ڣ�2017-1-12 ����03:15:50
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param inputStream
	 * @param model
	 * @return
	 * HashMap<String,Object> �������� 
	 * @throws 
	 */
	@SuppressWarnings("unchecked")
	public HashMap<String, Object> saveDrExcelInfo(InputStream is,
			GjjgForm gjjgform) {
		HashMap<String, Object> resultMap= null;
		try {
			 resultMap= this.DrExcelInfoCheck(is, gjjgform);
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
				List<String[]> errorlist = (List<String[]>) resultMap.get("errorlist");
				String gid = this.uploadErrorExcel((List<String[]>) resultMap.get("errorlist"),gjjgform.getFilepath());
				resultMap.put("gid", gid);
				this.saveDrDataIntoDb(zqlist);
				resultMap.put("zqts",zqlist.size());//��ȷ����
			  	resultMap.put("cwts",errorlist.size());//��������
				logger.info("����ʧ�ܣ�");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultMap;
	}

	/**
	 * @throws WriteException 
	 * @throws RowsExceededException 
	 * @throws IOException  
	 * @����:������excel���д�������
	 * @���ߣ�CP[���ţ�1352]
	 * @���ڣ�2017-1-12 ����03:18:19
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param list
	 * @param filepath
	 * @return
	 * String �������� 
	 * @throws 
	 */
	private String uploadErrorExcel(List<String[]> errorlist, String filepath) throws IOException, RowsExceededException, WriteException {
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
//            Label labelqsh= new Label(2, 0, "���Һ�");
            Label labelzbffzr= new Label(2, 0, "���췽������");
            Label labelsy= new Label(3, 0, "����");//��ʾ��
            Label labelqjkssj= new Label(4, 0, "��ٿ�ʼʱ��");
            Label labelqjjssj= new Label(5, 0, "��ٽ���ʱ��");
            Label labelqjjc= new Label(6, 0, "��ٽڴ�");
            Label labelsfgq= new Label(7, 0, "�Ƿ����");//��ʾ��
            Label labelbgqsj= new Label(8, 0, "������ʱ��");
            Label labelbz= new Label(9, 0, "��ע");
            try {
            	ws.addCell(labelxh);
				ws.addCell(labelxm);
//				ws.addCell(labelqsh);
	            ws.addCell(labelzbffzr);
	            ws.addCell(labelsy);
				ws.addCell(labelqjkssj);
				ws.addCell(labelqjjssj);
	            ws.addCell(labelqjjc);
	            ws.addCell(labelsfgq);
				ws.addCell(labelbgqsj);
				ws.addCell(labelbz);
			} catch (RowsExceededException e1) {
				e1.printStackTrace();
			} catch (WriteException e1) {
				e1.printStackTrace();
			}
            
            for (int i = 0; i < errorlist.size(); i++) {
                 for(int j = 0;j<errorlist.get(i).length;j++){
                	 Label labelId_i= null;
                	 if(j<=9){
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
	 * @throws Exception 
	 * @return  
	 * @����:��ȷ��¼д�����ݿ�
	 * @���ߣ�CP[���ţ�1352]
	 * @���ڣ�2017-1-12 ����03:18:15
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param paralist
	 * void �������� 
	 * @throws 
	 */
	private boolean saveDrDataIntoDb(List<String[]> paralist) throws Exception {
		if(paralist != null && paralist.size() > 0){
			return dao.saveDrDataIntoDb(paralist).length >0 ? true :false;
		}else{
			return false;
		}
		
	}

	
	
	
	 private int getIndex(String[] arr, String one) {  
	        for ( int i = 0; i < arr.length; i++) {  
	            if (one.equals(arr[i])) {  
	                return i;  
	            }  
	        }  
	        return -1;  //��������û���򷵻�-1  
	}  
	/** 
	 * @����:������Ϣ��֤
	 * @���ߣ�CP[���ţ�1352]
	 * @���ڣ�2017-1-12 ����03:18:08
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param is
	 * @param zhfdrform
	 * @return
	 * HashMap<String,Object> �������� 
	 * @throws 
	 */
	@SuppressWarnings("unchecked")
	private HashMap<String, Object> DrExcelInfoCheck(InputStream is,
			GjjgForm gjjgform) {
		//�����¼����
		List<String[]> drlist = new ArrayList<String[]>();
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
	        if(clos < 10  || rows < 2){
	        	resultMap.put("result", "null");
	        }else{
	        	  //�ж�excel�������ظ�����
	        	String [] flag = new String[rows];
	        	for (int i = 1; i < rows; i++) {
	        		//ȡ��ÿ����֤���ݣ�����lsmap
        			HashMap<String, String>  lsmap = new HashMap<String, String>();
        			String xh = rs.getCell(0, i).getContents();
        			String xm = rs.getCell(1, i).getContents();
        			String zbffzr = rs.getCell(2, i).getContents();
        			String sy = rs.getCell(3, i).getContents();
        			String qjkssj = rs.getCell(4, i).getContents();
        			String qjjssj = rs.getCell(5, i).getContents();
        			String qjjc = rs.getCell(6, i).getContents();
        			String sfgq = rs.getCell(7, i).getContents();
        			String bgqsj = rs.getCell(8, i).getContents();
        			String bz = rs.getCell(9, i).getContents();
        			if(StringUtils.isNull(xh.trim()) && StringUtils.isNull(xm.trim()) && 
        					StringUtils.isNull(zbffzr.trim())&& 
        					StringUtils.isNull(sy.trim()) && StringUtils.isNull(qjkssj.trim())&& 
        					StringUtils.isNull(qjjssj.trim()) && StringUtils.isNull(qjjc.trim())&& 
        					StringUtils.isNull(sfgq.trim())){
        				continue;
        			}
        			
        			String one = xh+qjkssj+qjjssj+qjjc;
        			int k = this.getIndex(flag,one);
        			if (k==-1) {
        				flag[i]=xh+qjkssj+qjjssj+qjjc;
					}else {//��������д��ڣ�˵���ظ�����ʱ��ȡ���ظ���ѧ����Ϣ�����д����������excel��
	        			lsmap.put("xh", xh);
	        			lsmap.put("xm", xm);
	        			lsmap.put("qsh", this.getQsh(xh));
	        			lsmap.put("zbffzr", zbffzr);
	        			lsmap.put("sy", sy);
	        			lsmap.put("qjkssj", qjkssj);
	        			lsmap.put("qjjssj", qjjssj);
	        			lsmap.put("qjjc", qjjc);
	        			lsmap.put("sfgq", sfgq);
	        			lsmap.put("bgqsj", bgqsj);
	        			lsmap.put("bz", bz);
	        			ArrayList<String> cflist = new ArrayList<String>();
	        			cflist.add(lsmap.get("xh"));
	        			cflist.add(lsmap.get("xm"));
	        			cflist.add(lsmap.get("zbffzr"));
	        			cflist.add(lsmap.get("sy"));
	        			cflist.add(lsmap.get("qjkssj"));
	        			cflist.add(lsmap.get("qjjssj"));
	        			cflist.add(lsmap.get("qjjc"));
	        			cflist.add(lsmap.get("sfgq"));
	        			cflist.add(lsmap.get("bgqsj"));
	        			cflist.add(lsmap.get("bz"));
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
	        			String zbffzr = rs.getCell(2, i).getContents();
	        			String sy = rs.getCell(3, i).getContents();
	        			String qjkssj = rs.getCell(4, i).getContents();
	        			String qjjssj = rs.getCell(5, i).getContents();
	        			String qjjc = rs.getCell(6, i).getContents();
	        			String sfgq = rs.getCell(7, i).getContents();
	        			String bgqsj = rs.getCell(8, i).getContents();
	        			String bz = rs.getCell(9, i).getContents();
	        			if(StringUtils.isNull(xh.trim()) && StringUtils.isNull(xm.trim()) && 
	        					StringUtils.isNull(zbffzr.trim())&& 
	        					StringUtils.isNull(sy.trim()) && StringUtils.isNull(qjkssj.trim())&& 
	        					StringUtils.isNull(qjjssj.trim()) && StringUtils.isNull(qjjc.trim())&& 
	        					StringUtils.isNull(sfgq.trim())){
	        				continue;
	        			}
	        			
	        			lsmap.put("xh", xh);
	        			lsmap.put("xm", xm);
	        			lsmap.put("qsh", this.getQsh(xh));
	        			lsmap.put("zbffzr", zbffzr);
	        			lsmap.put("sy", sy);
	        			lsmap.put("qjkssj", qjkssj);
	        			lsmap.put("qjjssj", qjjssj);
	        			lsmap.put("qjjc", qjjc);
	        			lsmap.put("sfgq", sfgq);
	        			lsmap.put("bgqsj", bgqsj);
	        			lsmap.put("bz", bz);
	        			HashMap<String,Object> resultmap = this.checkEveryRowRecord(lsmap, gjjgform);//ѭ����֤���ݸ�ʽ
	        			ArrayList<String> paralist = new ArrayList<String>();
	        			if("false".equals(resultmap.get("result"))){
	        				paralist.add(lsmap.get("xh"));
	        				paralist.add(lsmap.get("xm"));
	        				paralist.add(lsmap.get("zbffzr"));
	        				paralist.add(lsmap.get("sy"));
	        				paralist.add(lsmap.get("qjkssj"));
	        				paralist.add(lsmap.get("qjjssj"));
	        				paralist.add(lsmap.get("qjjc"));
	        				paralist.add(lsmap.get("sfgq"));
	        				paralist.add(lsmap.get("bgqsj"));
	        				paralist.add(lsmap.get("bz"));
	        				if(resultmap.get("resultmap") != null ){
	        					Map<String, String> map = (HashMap<String, String>) resultmap.get("resultmap");
	        					for (Map.Entry<String, String> entry : map.entrySet()){
	        						if(!entry.getKey().equals("xh") && !entry.getKey().equals("xm")
	        								&&	!entry.getKey().equals("qsh") 
	        								&& !entry.getKey().equals("zbffzr") && !entry.getKey().equals("sy")	
	        								&& !entry.getKey().equals("qjkssj") && !entry.getKey().equals("qjjssj")
	        								&& !entry.getKey().equals("qjjc") && !entry.getKey().equals("sfgq")
	        								&& !entry.getKey().equals("bgqsj") && !entry.getKey().equals("bz")){
	        							paralist.add(entry.getValue());
	        						}
	        					}
	        				}
	        				errorlist.add(paralist.toArray(new String[]{}));
	        				resultMap.put("result", "false");
	        			}else{
	        				paralist.add(lsmap.get("xh"));
	        				paralist.add(lsmap.get("xm"));
	        				paralist.add(lsmap.get("qsh"));
	        				paralist.add(lsmap.get("zbffzr"));
	        				paralist.add(lsmap.get("sy"));
	        				paralist.add(lsmap.get("qjkssj"));
	        				paralist.add(lsmap.get("qjjssj"));
	        				paralist.add(lsmap.get("qjjc"));
	        				paralist.add(lsmap.get("sfgq"));
	        				paralist.add(lsmap.get("bgqsj"));
	        				paralist.add(lsmap.get("bz"));
	        				drlist.add(paralist.toArray(new String[]{}));
	        			}
	        		}
	        	}
	        	
	        		resultMap.put("drlist", drlist);
	        		resultMap.put("errorlist", errorlist);
	        	
	        }
	      
		} catch (BiffException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return resultMap;
	}

	/** 
	 * @����:ȡ¥�����Һ�
	 * @throws 
	 */
	private String getQsh(String xh) {
		return dao.getQsh(xh);
	}

	/** 
	 * @����:ѭ����֤ÿ�еļ�¼
	 * @���ߣ�CP[���ţ�1352]
	 * @���ڣ�2017-1-12 ����03:39:30
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param lsmap
	 * @param gjjgform
	 * @return
	 * HashMap<String,Object> �������� 
	 * @throws 
	 */
	private HashMap<String, Object> checkEveryRowRecord(
			HashMap<String, String> lsmap, GjjgForm gjjgform) {
		/**
		 * ��֤����
		 * 1.xh,xm������֤�Ƿ����xsxxb
		 * 2.�����¼��ʦ�����ݷ�Χ��֤
		 * 3.���ʱ�� ��ֻ֤֧��yyyy-MM-dd��ʽ����
		 * 4.�Ƿ������������ �ǻ��
		 * 5.������ʱ��������ʱ�䲻��Ϊ��
		 * 6.�ظ�������֤
		 */
		String message = "true";
		GjjgDao gjjgDao = new GjjgDao();
		//Ϊ����֤
		if(StringUtils.isNull(lsmap.get("xh")) || StringUtils.isNull(lsmap.get("xm"))
				|| StringUtils.isNull(lsmap.get("zbffzr")) || StringUtils.isNull(lsmap.get("sy")) || StringUtils.isNull(lsmap.get("qjkssj"))
	|| StringUtils.isNull(lsmap.get("qjjssj")) || StringUtils.isNull(lsmap.get("qjjc")) || StringUtils.isNull(lsmap.get("sfgq"))){
				message = "false";
				lsmap.put("kyz", "ѧ�ţ����������췽�����ˣ����ɣ���ٿ�ʼʱ�䣬��ٽ���ʱ�䣬��ٽڴΣ��Ƿ���޲���Ϊ�գ�");
		}else{
			//xh,xm������֤�Ƿ����xsxxb
			if(!gjjgDao.checkXhXmIsExist(lsmap.get("xh"),lsmap.get("xm"))){
				message = "false";
				lsmap.put("xhxmlhyz", "ѧ��/��������Ӧ�򲻴���ѧ����Ϣ���У�");
			}
			//�ظ�������֤
			if(gjjgDao.checkXhQjsjIsExist(lsmap.get("xh"),lsmap.get("xm"),lsmap.get("qjkssj"),lsmap.get("qjjssj"),lsmap.get("qjjc"))){
				message = "false";
				lsmap.put("cfdryz", "�ظ����룡");
			}
			//�����¼��ʦ�� ���ݷ�Χ��֤
			if(!gjjgDao.checkJsDrSjfw(lsmap.get("xh"), gjjgform)){
				message = "false";
				lsmap.put("sjfwyz", "û��Ȩ�޵����ѧ����");
			}
			//�Ƿ���� ֻ����д �ǻ��
			if (!"��".equals(lsmap.get("sfgq"))&&!"��".equals(lsmap.get("sfgq"))) {
				message = "false";
				lsmap.put("sfgqyz", "�Ƿ����ֻ������/��");
			}
			//�Ƿ���� ��д�� �Ժ󣬲�����˵������Ϊ��
			if ("��".equals(lsmap.get("sfgq"))) {
				if (lsmap.get("bgqsj").length()==0) {
					message = "false";
					lsmap.put("bgqsjyz", "������ʱ��������ʱ�䲻��Ϊ�գ�");
				}
			}
			try {
				DateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");  
				Date date1 = (Date)formatter1.parse(lsmap.get("qjkssj"));
				if(!lsmap.get("qjkssj").equals(formatter1.format(date1))){
					message = "false";
					lsmap.put("qjkssjyz", "��ٿ�ʼʱ���ʽ����Ϊyyyy-MM-dd��");
				}
			} catch (ParseException e) {
				message = "false";
				lsmap.put("qjkssjyz", "��ٿ�ʼʱ���ʽ����Ϊyyyy-MM-dd��");
			}
			try {
				DateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");  
				Date date2 = (Date)formatter1.parse(lsmap.get("qjjssj"));
				if(!lsmap.get("qjjssj").equals(formatter1.format(date2))){
					message = "false";
					lsmap.put("qjjssjyz", "��ٽ���ʱ���ʽ����Ϊyyyy-MM-dd��");
				}
			} catch (ParseException e) {
				message = "false";
				lsmap.put("qjjssjyz", "��ٽ���ʱ���ʽ����Ϊyyyy-MM-dd��");
			}
		}
		
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("result", message);
		resultMap.put("resultmap", lsmap);
		return resultMap;
	}
	
	
	
}
