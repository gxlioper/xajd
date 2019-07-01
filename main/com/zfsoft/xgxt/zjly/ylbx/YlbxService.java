/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-4-19 ����10:04:07 
 */  
package com.zfsoft.xgxt.zjly.ylbx;

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

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2016-4-19 ����10:04:07 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class YlbxService extends SuperServiceImpl<YlbxForm,YlbxDao> {
	private YlbxDao dao= new YlbxDao();
	
	public YlbxService(){
		 setDao(dao);
	 }
	 
	public boolean isExistSame(YlbxForm form) throws Exception {
		String num = dao.checkExist(form);
		return Integer.valueOf(num) > 0;
	}

	/** 
	 * @��������ȡ��ѧ��������Ϣ�б�
	 * @���ߣ�׿��[����:1391]
	 * @���ڣ�2016��10��17�� ����3:08:16
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> �������� 
	 */
	public List<HashMap<String, String>> getXbxxList(String xh) {
		return dao.getXbxxList(xh);
	}
	public Object getFdyxx(String zgh) {
		// TODO �Զ����ɷ������
		return dao.getFdyxx(zgh);
	}

	/** 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�CP[���ţ�1352]
	 * @���ڣ�2017-5-3 ����10:09:45
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param inputStream
	 * @param model
	 * @return
	 * HashMap<String,Object> �������� 
	 * @throws 
	 */
	@SuppressWarnings("unchecked")
	public HashMap<String, Object> saveDrExcelInfo(InputStream is,
			YlbxForm model) {
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
				List<String[]> errorlist = (List<String[]>) resultMap.get("errorlist");
				String gid = this.uploadErrorExcel((List<String[]>) resultMap.get("errorlist"),model.getFilepath());
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
	 * @throws Exception  
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�CP[���ţ�1352]
	 * @���ڣ�2017-5-3 ����10:28:13
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
            Label labelsfzh= new Label(2, 0, "���֤��");
            Label labelxm= new Label(3, 0, "����");
            Label labelxb= new Label(4, 0, "�Ա�");
            Label labelcsrq= new Label(5, 0, "��������");
            Label labelrxsj= new Label(6, 0, "��ѧʱ��");
            Label labelzlbh= new Label(7, 0, "֤������");
            Label labelcblx= new Label(8, 0, "�α�����");
            try {
            	ws.addCell(labelxn);
				ws.addCell(labelxh);
	            ws.addCell(labelsfzh);
	            ws.addCell(labelxm);
				ws.addCell(labelxb);
				ws.addCell(labelcsrq);
	            ws.addCell(labelrxsj);
	            ws.addCell(labelzlbh);
				ws.addCell(labelcblx);
			} catch (RowsExceededException e1) {
				e1.printStackTrace();
			} catch (WriteException e1) {
				e1.printStackTrace();
			}
            
            for (int i = 0; i < errorlist.size(); i++) {
                 for(int j = 0;j<errorlist.get(i).length;j++){
                	 Label labelId_i= null;
                	 if(j<=8){
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
	 * @���ڣ�2017-5-3 ����10:10:57
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param is
	 * @param model
	 * @return
	 * HashMap<String,Object> �������� 
	 * @throws 
	 */
	private HashMap<String, Object> DrExcelInfoCheck(InputStream is,
			YlbxForm model) {
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
	        if(clos < 9  || rows < 2){
	        	resultMap.put("result", "null");
	        }else{
	        	  //�ж�excel�������ظ�����
	        	String [] flag = new String[rows];
	        	for (int i = 1; i < rows; i++) {
	        		//ȡ��ÿ����֤���ݣ�����lsmap
        			HashMap<String, String>  lsmap = new HashMap<String, String>();
        			String xn = rs.getCell(0, i).getContents();
        			String xh = rs.getCell(1, i).getContents();
        			String sfzh = rs.getCell(2, i).getContents();
        			String xm = rs.getCell(3, i).getContents();
        			String xb = rs.getCell(4, i).getContents();
        			String csrq = rs.getCell(5, i).getContents();
        			String rxsj = rs.getCell(6, i).getContents();
        			String zlbh = rs.getCell(7, i).getContents();
        			String cblb = rs.getCell(8, i).getContents();
        			if(StringUtils.isNull(xh.trim()) && StringUtils.isNull(xm.trim()) && 
        					StringUtils.isNull(sfzh.trim())&& 
        					StringUtils.isNull(xn.trim()) && StringUtils.isNull(rxsj.trim())&& 
        					StringUtils.isNull(csrq.trim()) && StringUtils.isNull(zlbh.trim())&& 
        					StringUtils.isNull(cblb.trim())){
        				continue;
        			}
        			
        			String one = xh+xn+zlbh+cblb;
        			int k = this.getIndex(flag,one);
        			if (k==-1) {
        				flag[i]=xh+xn+zlbh+cblb;
					}else {//��������д��ڣ�˵���ظ�����ʱ��ȡ���ظ���ѧ����Ϣ�����д����������excel��
	        			lsmap.put("xn", xn);
	        			lsmap.put("xh", xh);
	        			lsmap.put("sfzh", sfzh);
	        			lsmap.put("xm", xm);
	        			lsmap.put("xb", xb);
	        			lsmap.put("csrq", csrq);
	        			lsmap.put("rxsj", rxsj);
	        			lsmap.put("zlbh", zlbh);
	        			lsmap.put("cblb", cblb);
	        			ArrayList<String> cflist = new ArrayList<String>();
	        			cflist.add(lsmap.get("xn"));
	        			cflist.add(lsmap.get("xh"));
	        			cflist.add(lsmap.get("sfzh"));
	        			cflist.add(lsmap.get("xm"));
	        			cflist.add(lsmap.get("xb"));
	        			cflist.add(lsmap.get("csrq"));
	        			cflist.add(lsmap.get("rxsj"));
	        			cflist.add(lsmap.get("zlbh"));
	        			cflist.add(lsmap.get("cblb"));
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
	        			String sfzh = rs.getCell(2, i).getContents();
	        			String xm = rs.getCell(3, i).getContents();
	        			String xb = rs.getCell(4, i).getContents();
	        			String csrq = rs.getCell(5, i).getContents();
	        			String rxsj = rs.getCell(6, i).getContents();
	        			String zlbh = rs.getCell(7, i).getContents();
	        			String cblb = rs.getCell(8, i).getContents();
	        			if(StringUtils.isNull(xh.trim()) && StringUtils.isNull(xm.trim()) && 
	        					StringUtils.isNull(sfzh.trim())&& 
	        					StringUtils.isNull(xn.trim()) && StringUtils.isNull(rxsj.trim())&& 
	        					StringUtils.isNull(csrq.trim()) && StringUtils.isNull(zlbh.trim())&& 
	        					StringUtils.isNull(cblb.trim())){
	        				continue;
	        			}
	        			lsmap.put("xn", xn);
	        			lsmap.put("xh", xh);
	        			lsmap.put("sfzh", sfzh);
	        			lsmap.put("xm", xm);
	        			lsmap.put("xb", xb);
	        			lsmap.put("csrq", csrq);
	        			lsmap.put("rxsj", rxsj);
	        			lsmap.put("zlbh", zlbh);
	        			lsmap.put("cblb", cblb);
	        			HashMap<String,Object> resultmap = this.checkEveryRowRecord(lsmap, model);//ѭ����֤���ݸ�ʽ
	        			ArrayList<String> paralist = new ArrayList<String>();
	        			if("false".equals(resultmap.get("result"))){
	        				paralist.add(lsmap.get("xn"));
	        				paralist.add(lsmap.get("xh"));
	        				paralist.add(lsmap.get("sfzh"));
	        				paralist.add(lsmap.get("xm"));
	        				paralist.add(lsmap.get("xb"));
	        				paralist.add(lsmap.get("csrq"));
	        				paralist.add(lsmap.get("rxsj"));
	        				paralist.add(lsmap.get("zlbh"));
	        				paralist.add(lsmap.get("cblb"));
	        				if(resultmap.get("resultmap") != null ){
	        					Map<String, String> map = (HashMap<String, String>) resultmap.get("resultmap");
	        					for (Map.Entry<String, String> entry : map.entrySet()){
	        						if(!entry.getKey().equals("xn") && !entry.getKey().equals("xh")
	        								&& !entry.getKey().equals("sfzh") && !entry.getKey().equals("xm")	
	        								&& !entry.getKey().equals("xb") && !entry.getKey().equals("csrq")
	        								&& !entry.getKey().equals("rxsj") && !entry.getKey().equals("zlbh")
	        								&& !entry.getKey().equals("cblb")){
	        							paralist.add(entry.getValue());
	        						}
	        					}
	        				}
	        				errorlist.add(paralist.toArray(new String[]{}));
	        				resultMap.put("result", "false");
	        			}else{
	        				paralist.add(lsmap.get("xn"));
	        				paralist.add(lsmap.get("xh"));
	        				paralist.add(lsmap.get("sfzh"));
	        				paralist.add(lsmap.get("xm"));
	        				paralist.add(lsmap.get("xb"));
	        				paralist.add(lsmap.get("csrq"));
	        				paralist.add(lsmap.get("rxsj"));
	        				paralist.add(lsmap.get("zlbh"));
	        				paralist.add(lsmap.get("cblb"));
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
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�CP[���ţ�1352]
	 * @���ڣ�2017-5-3 ����10:30:12
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param lsmap
	 * @param model
	 * @return
	 * HashMap<String,Object> �������� 
	 * @throws 
	 */
	private HashMap<String, Object> checkEveryRowRecord(
			HashMap<String, String> lsmap, YlbxForm model) {
		
		String message = "true";
		GjjgDao gjjgDao = new GjjgDao();
		YlbxDao ylbxDao = new YlbxDao();
		//Ϊ����֤
		if(StringUtils.isNull(lsmap.get("xn")) || StringUtils.isNull(lsmap.get("xh"))
				|| StringUtils.isNull(lsmap.get("sfzh")) || StringUtils.isNull(lsmap.get("xm")) || StringUtils.isNull(lsmap.get("xb"))
	|| StringUtils.isNull(lsmap.get("csrq")) || StringUtils.isNull(lsmap.get("rxsj")) || StringUtils.isNull(lsmap.get("zlbh"))|| StringUtils.isNull(lsmap.get("cblb"))){
				message = "false";
				lsmap.put("kyz", "ѧ�꣬ѧ�ţ����֤�ţ��������Ա𣬳������ڣ���ѧʱ�䣬֤�����ţ��α���𲻿�Ϊ�գ�");
		}else{
			//xh,xm������֤�Ƿ����xsxxb
			if(!gjjgDao.checkXhXmIsExist(lsmap.get("xh"),lsmap.get("xm"))){
				message = "false";
				lsmap.put("xhxmlhyz", "ѧ��/��������Ӧ�򲻴���ѧ����Ϣ���У�");
			}
			//�ظ�������֤
			if(ylbxDao.checkXhQjsjIsExist(lsmap.get("xn"),lsmap.get("xh"))){
				message = "false";
				lsmap.put("cfdryz", "��ǰѧ��ѧ��ҽ�Ʊ��������Ѵ��ڣ�");
			}
			//�����¼��ʦ�� ���ݷ�Χ��֤
			if(!ylbxDao.checkJsDrSjfw(lsmap.get("xh"), model)){
				message = "false";
				lsmap.put("sjfwyz", "û��Ȩ�޵����ѧ����");
			}
			if (!"��".equals(lsmap.get("xb"))&&!"Ů".equals(lsmap.get("xb"))) {
				message = "false";
				lsmap.put("xbyz", "�Ա�ֻ����д��/Ů��");
			}
			if (!"�²α�".equals(lsmap.get("cblb"))&&!"����".equals(lsmap.get("cblb"))) {
				message = "false";
				lsmap.put("cblbyz", "�α����ֻ����д�²α�/������");
			}
//			try {
//				DateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");  
//				Date date1 = (Date)formatter1.parse(lsmap.get("rxsj"));
//				if(!lsmap.get("rxsj").equals(formatter1.format(date1))){
//					message = "false";
//					lsmap.put("rxsjyz", "��ѧʱ���ʽ����Ϊyyyy-MM-dd��");
//				}
//			} catch (ParseException e) {
//				message = "false";
//				lsmap.put("rxsjyz", "��ѧʱ���ʽ����Ϊyyyy-MM-dd��");
//			}
//			try {
//				DateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");  
//				Date date2 = (Date)formatter1.parse(lsmap.get("csrq"));
//				if(!lsmap.get("csrq").equals(formatter1.format(date2))){
//					message = "false";
//					lsmap.put("csrqyz", "�������ڸ�ʽ����Ϊyyyy-MM-dd��");
//				}
//			} catch (ParseException e) {
//				message = "false";
//				lsmap.put("csrqyz", "�������ڸ�ʽ����Ϊyyyy-MM-dd��");
//			}
		}
		
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("result", message);
		resultMap.put("resultmap", lsmap);
		return resultMap;
	}

	/** 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�CP[���ţ�1352]
	 * @���ڣ�2017-5-3 ����10:12:49
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param flag
	 * @param one
	 * @return
	 * int �������� 
	 * @throws 
	 * 
	 */
	private int getIndex(String[] arr, String one) {  
        for ( int i = 0; i < arr.length; i++) {  
            if (one.equals(arr[i])) {  
                return i;  
            }  
        }  
        return -1;  //��������û���򷵻�-1  
}

	/** 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�CP[���ţ�1352]
	 * @���ڣ�2017-5-3 ����10:10:49
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

	
} 
