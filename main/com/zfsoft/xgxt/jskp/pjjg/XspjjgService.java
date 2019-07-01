/**
 * @����:ѧ����Ʒ(1)��
 * @���ڣ�2018-4-18 ����11:04:21 
 */  
package com.zfsoft.xgxt.jskp.pjjg;

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

import jxl.Sheet;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import xgxt.form.User;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.UniqID;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ѧ�����۹���ģ��
 * @�๦������: ѧ�����۽��
 * @���ߣ� Meng.Wei[����:1186]
 * @ʱ�䣺 2018-4-18 ����11:04:21 
 * @�汾�� V5.18.26
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XspjjgService extends SuperServiceImpl<XspjjgForm,XspjjgDao>{
	
	/**
	 * @����: ���ص���ģ��,����excel��
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ� 2018-4-20 ����08:56:58
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param os
	 * @throws Exception
	 * void �������� 
	 * @throws
	 */
	public void createWwb(OutputStream os) throws Exception{
		
		WritableWorkbook wwb = Workbook.createWorkbook(os);
		
		/**sheet1*/
		WritableSheet ws = wwb.createSheet("���ݵ���ģ��", 0);
		
		/**���ñ��̧ͷ�ĸ�ʽ*/
		WritableFont bodyFont = new WritableFont(WritableFont.createFont("����"),10,WritableFont.NO_BOLD );
		/**��Ԫ�������ʽд��*/
		WritableCellFormat body = new WritableCellFormat(bodyFont);
		/**���õ�Ԫ�����*/
		/*ˮƽ����*/
		body.setAlignment(jxl.format.Alignment.CENTRE);
		/*��ֱ����*/
		body.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
		
		/**�����п�*/
		ws.setColumnView(0, 16);/*��Ŀ����*/
		ws.setColumnView(1, 16);/*ָ������*/
		ws.setColumnView(2, 16);/*��Ŀ���*/
		ws.setColumnView(3, 34);/*����ʱ��*/
		ws.setColumnView(4, 14);/*ѧ��*/
		ws.setColumnView(5, 28);/*ѧ��*/
		ws.setColumnView(6, 24);/*��ѧ��*/
		ws.setColumnView(7, 12);/*��ֵ*/
		ws.setColumnView(8, 12);/*������*/
		ws.setColumnView(9, 18);/*��������ϵ��ʽ*/
		ws.setColumnView(10, 14);/*ָ����ʦ*/
		ws.setColumnView(11, 20);/*ָ����ʦ��ϵ��ʽ*/
		ws.setColumnView(12, 24);/*��������*/
		
		
		/*��һ��̧ͷ*/
		Label row1col1= new Label(0, 0, "��Ŀ����(����)",body);
		Label row1col2= new Label(1, 0, "ָ������(����)",body);
		Label row1col3= new Label(2, 0, "��Ŀ���(����)",body);
		Label row1col4= new Label(3, 0, "����ʱ��(����),���磺2018-01-01",body);
		Label row1col5= new Label(4, 0, "ѧ��(����)",body);
		Label row1col6= new Label(5, 0, "ѧ��(����),���磺2017-2018",body);
		Label row1col7= new Label(6, 0, "��ѧ��(����),���磺��",body);
		Label row1col8= new Label(7, 0, "��ֵ(����)",body);
		Label row1col9= new Label(8, 0, "������",body);
		Label row1col10= new Label(9, 0, "��������ϵ��ʽ",body);
		Label row1col11= new Label(10, 0, "ָ����ʦ",body);
		Label row1col12= new Label(11, 0, "ָ����ʦ��ϵ��ʽ",body);
		Label row1col13= new Label(12, 0, "��������",body);
	    ws.addCell(row1col1);
	    ws.addCell(row1col2);
	    ws.addCell(row1col3);
	    ws.addCell(row1col4);
	    ws.addCell(row1col5);
	    ws.addCell(row1col6);
	    ws.addCell(row1col7);
	    ws.addCell(row1col8);
	    ws.addCell(row1col9);
	    ws.addCell(row1col10);
	    ws.addCell(row1col11);
	    ws.addCell(row1col12);
	    ws.addCell(row1col13);
	    
	    /**sheet2����Ŀ�����ձ�*/
	    WritableSheet ws1 = wwb.createSheet("��Ŀ�����ձ�", 1);
	    List<HashMap<String, String>> xmlbList = dao.getXmlbList();
	    Label ws1row1col1= new Label(0, 0, "��Ŀ�������",body);
	    ws1.addCell(ws1row1col1);
	    for (int i = 0; i < xmlbList.size(); i++) {
	    	Label tempLabel = new Label(0, i+1, xmlbList.get(i).get("xmlbmc"),body);
	    	 ws1.addCell(tempLabel);
		}
	    
	    /**sheet3��ָ�����Ŷ��ձ�*/
	    WritableSheet ws2 = wwb.createSheet("ָ�����Ŷ��ձ�", 2);
	    List<HashMap<String, String>> xqList = dao.getZdbmList();
	    Label ws2row1col1= new Label(0, 0, "ָ������",body);
	    ws2.addCell(ws2row1col1);
	    for (int i = 0; i < xqList.size(); i++) {
	    	Label tempLabel = new Label(0, i+1, xqList.get(i).get("zdbmmc"),body);
	    	 ws2.addCell(tempLabel);
		}
	    
	    /**sheet4����ѧ�ڴ����*/
	    WritableSheet ws3 = wwb.createSheet("��ѧ�ڶ��ձ�", 3);
	    List<HashMap<String, String>> dxqList = dao.getDxqList();
	    Label ws3row1col1= new Label(0, 0, "��ѧ��",body);
	    ws3.addCell(ws3row1col1);
	    for (int i = 0; i < dxqList.size(); i++) {
	    	Label tempLabel = new Label(0, i+1, dxqList.get(i).get("dxqmc"),body);
	    	ws3.addCell(tempLabel);
		}
		try{
			 wwb.write();
			 wwb.close();
		 }catch(Exception e){
			 
		 }
	}
	
	/**
	 * @����: ������Ϣ����
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ� 2018-4-20 ����02:23:55
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param is
	 * @param xspjjgForm
	 * @return
	 * HashMap<String,Object> �������� 
	 * @throws
	 */
	public HashMap<String, Object> saveDrExcelInfo(InputStream is,XspjjgForm xspjjgForm,User user){
		HashMap<String, Object> resultMap= null;
		try {
			 resultMap = this.DrExcelInfoCheck(is, xspjjgForm,user);
			//�ж�excel����Ƿ�Ϊ��
			if("null".equals(resultMap.get("result")) || resultMap.isEmpty() || "hbyzsb".equals(resultMap.get("result")) || "excelrepeat".equals(resultMap.get("result"))){
				return resultMap;
			}else if("true".equals(resultMap.get("result"))){
				List<String[]> paralist = (List<String[]>) resultMap.get("drlist");
				if( paralist == null ||paralist.size() == 0 ){
					resultMap.put("result", "null");
					return resultMap;
				}
				this.saveDrDataIntoDb(paralist);
			}else{
				String gid = this.uploadErrorExcel((List<String[]>) resultMap.get("errorlist"),xspjjgForm.getFilepath());
				resultMap.put("gid", gid);
				this.saveDrDataIntoDb((List<String[]>) resultMap.get("drlist"));
				logger.info("����ʧ�ܣ�");
			}
		} catch (Exception e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}
		return resultMap;
	}
	
	/**
	 * @����: ������Ϣ��֤
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ� 2018-4-20 ����02:27:20
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param is
	 * @param xspjjgForm
	 * @return
	 * HashMap<String,Object> �������� 
	 * @throws
	 */
	public HashMap<String, Object> DrExcelInfoCheck(InputStream is,XspjjgForm xspjjgForm,User user){
		
		//�����¼����
		List<String[]> drlist = new ArrayList<String[]>();
		//�����¼����
		List<String[]> errorlist = new ArrayList<String[]>();
		
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("result", "true");
		
		Workbook rwb = null;
		try{
			rwb = Workbook.getWorkbook(is);
			Sheet rs = rwb.getSheet(0);
			/*�õ����е���*/
		    int clos = rs.getColumns();
		    /*�õ����е���*/
	        int rows = rs.getRows();
	        if(rows < 2){
	        	resultMap.put("result", "null");
	        }else if(!this.checkExcelRepeat(rs, clos, rows)){//��֤������Ƿ����ظ�����(��Ŀ���ơ�����ʱ�䡢ѧ�Ų����ظ�)
	        	resultMap.put("result", "excelrepeat");
	        }else{
	        	for(int i = 1; i < rows; i++){
	        		/*ȡ��ÿ����֤���ݣ�����lsmap*/
	        		HashMap<String,String> lsmap = new HashMap<String,String>();
	        		String xmmc = rs.getCell(0, i).getContents();
	        		String zdbmmc = rs.getCell(1, i).getContents();
	        		String xmlbmc = rs.getCell(2, i).getContents();
	        		String cysj = rs.getCell(3, i).getContents();
	        		String xh = rs.getCell(4, i).getContents();
	        		String xn = rs.getCell(5, i).getContents();
	        		String dxqmc = rs.getCell(6, i).getContents();
	        		String fz = rs.getCell(7, i).getContents();
	        		String fzrxm = rs.getCell(8, i).getContents();
	        		String fzrlxfs = rs.getCell(9, i).getContents();
	        		String zdlsxm = rs.getCell(10, i).getContents();
	        		String zdlslxfs = rs.getCell(11, i).getContents();
	        		String sqly = rs.getCell(12, i).getContents();
	        		lsmap.put("xmmc", xmmc);
	        		lsmap.put("zdbmmc", zdbmmc);
	        		lsmap.put("xmlbmc", xmlbmc);
	        		lsmap.put("cysj", cysj);
	        		lsmap.put("xh", xh);
	        		lsmap.put("xn", xn);
	        		lsmap.put("dxqmc", dxqmc);
	        		lsmap.put("fz", fz);
	        		lsmap.put("fzrxm", fzrxm);
	        		lsmap.put("fzrlxfs", fzrlxfs);
	        		lsmap.put("zdlsxm", zdlsxm);
	        		lsmap.put("zdlslxfs", zdlslxfs);
	        		lsmap.put("sqly", sqly);
	        		//�����ж�
  	        	    if(this.checkNullRow(lsmap)){
  	        	    	continue;
  	        	    }
  	        	    HashMap<String,Object> resultmap = this.checkEveryRowRecord(lsmap,xspjjgForm,user);
  	        	    ArrayList<String> paralist = new ArrayList<String>();
  	        	    if("false".equals(resultmap.get("result"))){
  	        	    	paralist.add(lsmap.get("xmmc"));
  	        	    	paralist.add(lsmap.get("zdbmmc"));
  	        	    	paralist.add(lsmap.get("xmlbmc"));
  	        	    	paralist.add(lsmap.get("cysj"));
  	        	    	paralist.add(lsmap.get("xh"));
  	        	    	paralist.add(lsmap.get("xn"));
  	        	    	paralist.add(lsmap.get("dxqmc"));
  	        	    	paralist.add(lsmap.get("fz"));
  	        	    	paralist.add(lsmap.get("fzrxm"));
  	        	    	paralist.add(lsmap.get("fzrlxfs"));
  	        	    	paralist.add(lsmap.get("zdlsxm"));
  	        	    	paralist.add(lsmap.get("zdlslxfs"));
  	        	    	paralist.add(lsmap.get("sqly"));
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
  	        	    	paralist.add(lsmap.get("xn"));
  	        	    	paralist.add(lsmap.get("fz"));
  	        	    	paralist.add(lsmap.get("dxqdm"));
  	        	    	paralist.add(lsmap.get("xmmc"));
  	        	    	paralist.add(lsmap.get("zdbmdm"));
  	        	    	paralist.add(lsmap.get("xmlbdm"));
  	        	    	paralist.add(lsmap.get("cysj"));
  	        	    	paralist.add(lsmap.get("fzrxm"));
  	        	    	paralist.add(lsmap.get("fzrlxfs"));
  	        	    	paralist.add(lsmap.get("zdlsxm"));
  	        	    	paralist.add(lsmap.get("zdlslxfs"));
  	        	    	paralist.add(lsmap.get("sqly"));
  	        	    	paralist.add(xspjjgForm.getSjlrr());
  	        	    	paralist.add(xspjjgForm.getSjly());
  	        	    	paralist.add(xspjjgForm.getSjlrsj());
  	        	    	drlist.add(paralist.toArray(new String[]{}));
  	        	    }
	        	}
	          resultMap.put("drlist", drlist);
        	  resultMap.put("errorlist", errorlist);
	        }
			
		}catch (BiffException e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}catch (IOException e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}
		return resultMap;
	}
	
	/**
	 * @����: ��֤������Ƿ�����ظ�����(��Ŀ���ơ�����ʱ�䡢ѧ�Ų����ظ�)
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ�2018-4-20 ����02:33:45
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
			String xmmc = rs.getCell(0,i).getContents();
			String cysj = rs.getCell(3,i).getContents();
			String xh = rs.getCell(4, i).getContents();
			String str = "";
			if(StringUtils.isNotNull(xmmc)){
				str = str + xmmc.trim();
			}
			if(StringUtils.isNotNull(cysj)){
				str = str + cysj.trim();
			}
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
	 * @����: �жϿ���
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ� 2018-4-20 ����02:52:48
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
	
	/**
	 * @����: ��֤ÿ�м�¼
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ� 2018-4-20 ����02:55:31
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param lsmap
	 * @param xspjjgForm
	 * @return
	 * HashMap<String,Object> �������� 
	 * @throws
	 */
	public HashMap<String, Object> checkEveryRowRecord(HashMap<String, String> lsmap, XspjjgForm xspjjgForm,User user){
		/**
		 * ��֤����
		 * 1����Ŀ���ơ�ָ�����š���Ŀ��𡢲���ʱ�䡢ѧ�š�ѧ�ꡢ��ѧ�ڡ���ֵ����Ϊ��
		 * 2����ָ֤�������Ƿ����
		 * 3����֤��Ŀ����Ƿ����
		 * 4����֤����ʱ���ʽ�Ƿ���ȷ
		 * 5��ѧ���Ƿ����ѧ����Ϣ��
		 * 6����֤��ѧ���Ƿ����
		 * 7��Ψһ����֤
		 */
		String message = "true";
		if(!this.checkNotNull(lsmap)){
			message = "false";
			lsmap.put("kyz", "��Ŀ���ơ�ָ�����š���Ŀ��𡢲���ʱ�䡢ѧ�š�ѧ�ꡢ��ѧ�ڡ���ֵ����Ϊ�գ�");
		}else{
			//��֤֪�������Ƿ����
			String zdbmdm = dao.checkZdbm(lsmap.get("zdbmmc"));
			if(StringUtils.isNull(zdbmdm)){
				message = "false";
				lsmap.put("zdbmyz", "ָ�����Ų����ڣ�");
			}
			
			String xsbmmc = dao.getXsbmmc(lsmap.get("xh"));
			//��֤��ѧԺֻ�ܵ�����ѧԺ����
			if(!xsbmmc.equals(user.getUserDepName())){
				message = "false";
				lsmap.put("zdbmyz", "ֻ�ܵ��뱾ѧԺ(԰)���ݣ�");
			}
			
			//��֤��Ŀ����Ƿ����
			String xmlbdm = dao.checkXmlb(lsmap.get("xmlbmc"));
			if(StringUtils.isNull(xmlbdm)){
				message = "false";
				lsmap.put("xmlbyz", "��Ŀ��𲻴��ڣ�");
			}
			
			//��֤��Ŀ���Ϊ�������������ݲ��ܵ���
			/*2018-07-09,��ſ��������Ȩ�ޣ�������������������Ҳ�ǿ��Ե����*/
//			String xmlbdmForNlsy = dao.checkNlsy(lsmap.get("xmlbmc"));
//			if(StringUtils.isNull(xmlbdmForNlsy)){
//				message = "false";
//				lsmap.put("xmlbNlsyyz", "��Ŀ���Ϊ�������������ݲ��ܵ��룡");
//			}
			
			//��֤����ʱ���ʽ�Ƿ���ȷ
			DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			try{
				Date date = (Date)formatter.parse(lsmap.get("cysj"));
				if(!lsmap.get("cysj").equals(formatter.format(date))){
					message = "false";
					lsmap.put("cysjgsyz", "����ʱ���ʽ����Ϊyyyy-MM-dd��");
				}
			}catch (ParseException e){
				// TODO �Զ����� catch ��
				e.printStackTrace();
			}
			
			//��֤ѧ���Ƿ����ѧ����Ϣ��
			if(!dao.checkXhisTrue(lsmap.get("xh"))){
				message = "false";
				lsmap.put("xsxhyz", "ѧ�Ų����ڻ��пո�");
			}
			
			//��֤��ѧ���Ƿ����
			String dxqdm = dao.checkDxq(lsmap.get("dxqmc"));
			if(StringUtils.isNull(dxqdm)){
				message = "false";
				lsmap.put("dxqyz", "��ѧ�ڲ����ڣ�");
			}
			
			//������֤
			if(!dao.checkIsNotExists(lsmap.get("xmmc"), lsmap.get("cysj"), lsmap.get("xh"), null)){
				message = "false";
				lsmap.put("zjyz", "ͬһ��ѧ����ͬһʱ���걨������Ŀ����ȷ�ϣ�");
			}
			
			//��ע��֤
			if(lsmap.get("sqly").trim().length() > 200){
				message = "false";
				lsmap.put("sqlyyz", "��ע���ܳ���200�֣�");
			}
			
			if("true".equals(message)){
				lsmap.put("zdbmdm", zdbmdm);
				lsmap.put("xmlbdm", xmlbdm);
				lsmap.put("dxqdm", dxqdm);
			}
		}
		
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("result", message);
		resultMap.put("resultmap", lsmap);
		return resultMap;
	}
	
	/**
	 * @����: ��֤�ǿ���
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ� 2018-4-20 ����03:05:06
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param yzMap
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean checkNotNull(HashMap<String, String> yzMap){
		boolean rs = true;
		for (String key : yzMap.keySet()) {
			if(StringUtils.isNull(yzMap.get(key)) && !("fzrxm").equals(key) && !("fzrlxfs").equals(key) && !("zdlsxm").equals(key) && !("zdlslxfs").equals(key) && !("sqly").equals(key)){
				rs = false;
				break;
			}
		}
		return rs;
	}
	
	/**
	 * @����: ��������
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ�2018-4-20 ����04:32:01
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param paralist
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean saveDrDataIntoDb(List<String[]> paralist) throws Exception{
		if(paralist != null && paralist.size() > 0){
			return dao.saveDrDataIntoDb(paralist).length >0 ? true :false;
		}else{
			return false;
		}
	}
	
	/**
	 * @����: ������excel���д�������
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ�2018-4-20 ����04:53:44
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
		 String path = filepath + fileName;
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
	   	    Label row1col1= new Label(0, 0, "��Ŀ����",titlefont);
	   	    Label row1col2= new Label(1, 0, "ָ������",titlefont);
	   	    Label row1col3= new Label(2, 0, "��Ŀ���",titlefont);
	   	    Label row1col4= new Label(3, 0, "����ʱ��",titlefont);
	   		Label row1col5= new Label(4, 0, "ѧ��",titlefont);
	   		Label row1col6= new Label(5, 0, "ѧ��",titlefont);
	   		Label row1col7= new Label(6, 0, "��ѧ��",titlefont);
	   		Label row1col8= new Label(7, 0, "��ֵ",titlefont);
	   		Label row1col9= new Label(8, 0, "������",titlefont);
	   		Label row1col10= new Label(9, 0, "��������ϵ��ʽ",titlefont);
	   		Label row1col11= new Label(10, 0, "ָ����ʦ",titlefont);
	   		Label row1col12= new Label(11, 0, "ָ����ʦ��ϵ��ʽ",titlefont);
	   		Label row1col13= new Label(12, 0, "��������",titlefont);
            try {
	           	ws.addCell(row1col1);
	       		ws.addCell(row1col2);
	       		ws.addCell(row1col3);
	       		ws.addCell(row1col4);
	       		ws.addCell(row1col5);
	       		ws.addCell(row1col6);
	       		ws.addCell(row1col7);
	       		ws.addCell(row1col8);
	       		ws.addCell(row1col9);
	       		ws.addCell(row1col10);
	       	    ws.addCell(row1col11);
	       	    ws.addCell(row1col12);
	       	    ws.addCell(row1col13);
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
	               	 if(j<=12){
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
	           		try{
						ws.addCell(labelId_i);
					}catch(RowsExceededException e) {
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
	
	/**
	 * @����: excel����
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ� 2018-4-20 ����04:54:53
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
	 * @����: ��������
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ� 2018-4-24 ����09:49:51
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean saveFormXspjjg(XspjjgForm model,User user) throws Exception{
		boolean rs = true;
		
		/*����Ψһ��ʶ��*/
		String guid = UniqID.getInstance().getUniqIDHash();
		
		/*�ж�Ψһ����ѧ��(xh),��Ŀ����(xmmc),����ʱ��(cysj)*/
		if(!this.checkIsNotRepeat(model)){
			throw new SystemException(MessageKey.SZTZ_XMSB_REPEAT);
		}
		
		/*�жϲ�����(ѧ��)�Ƿ����xsxxb*/
		if(!dao.checkXh(model.getXh())){
			throw new SystemException(MessageKey.PJPY_CPFWH_XHNULL);
		}
		
		/*��ǰ������Ա�û�������*/
		model.setSjlrr(user.getUserName());
		
		/*��ȡ��ǰ����ʱ��������У���ϲ����˹��ŷ�ֹ��ʦˣ��*/
		String DATE_FORMAT = "yyyy-MM-dd hh24:mi:ss";
		model.setSjlrsj(GetTime.getTimeByFormat(DATE_FORMAT));
		
		/*�жϸ������Ƿ�Ϊ�޸�����*/
		if(StringUtils.isNotNull(model.getGuid())){
			rs = dao.runUpdate(model);
			if(!rs){
				throw new SystemException(MessageKey.SYS_SAVE_FAIL);
			}
		}else{
			/*����ΨһID*/
			model.setGuid(guid);
			/*1:������ˡ�2:������ӡ�3:����*/
			model.setSjly("2");
			rs = dao.runInsert(model);
			if(!rs){
				throw new SystemException(MessageKey.SYS_SAVE_FAIL);
			}
		}
		return rs;
	}
	
	/**
	 * @����: �ж�Ψһ����ѧ��(xh),��Ŀ����(xmmc),����ʱ��(cysj)
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ�2018-4-24 ����09:55:12
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean checkIsNotRepeat(XspjjgForm model){
		return dao.checkIsNotRepeat(model);
	}
	
	/**
	 * @����: ����guid�鿴������Ϣ
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ� 2018-4-25 ����09:04:27
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param guid
	 * @return
	 * @throws Exception
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String,String> getInfoByGuid(String guid) throws Exception{
		return dao.getInfoByGuid(guid);
	}
	
	/** 
	 * @����: ��ȡ�����˵�ѧ�����
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ�2017-8-23 ����02:32:16
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getSingleSummary(String xh) {
		return dao.getSingleSummary(xh);
	}
	
	/**
	 * @����: ���۽������ͳ��
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ� 2018-4-25 ����03:19:09
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @param SearchXn
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getDateForSearchXn (XspjjgForm model, User user, String SearchXn) throws Exception{
		return dao.getDateForSearchXn(model,user,SearchXn);
	}
	
	/**
	 * @����: ���۽������ͳ��Excle
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ� 2018-4-25 ����05:24:05
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param resultList
	 * @param xn
	 * @return
	 * @throws Exception
	 * File �������� 
	 * @throws
	 */
	public File getDataStatisticsFile(List<HashMap<String,String>> resultList,String xn) throws Exception {
		
		/**�ļ���*/
		String fileName = "ѧ�����۽������ͳ��.xls";
		File file = new File(System.getProperty("java.io.tmpdir"),fileName);
		
		/**�ж�ָ����·������ָ����Ŀ¼�ļ��Ƿ��Ѿ�����*/
		if(!file.exists()){
			file.createNewFile();
		}
		
		/**����������*/
		WritableWorkbook wwb = Workbook.createWorkbook(file);
		
		/**����������sheet1��*/
		WritableSheet sheet = wwb.createSheet("ѧ�����۽������ͳ��", 0);
		
		/**���ñ��̧ͷ�ĸ�ʽ============begin============*/
		WritableFont titleFont = new WritableFont(WritableFont.ARIAL, 26, WritableFont.BOLD, false,UnderlineStyle.NO_UNDERLINE);
		WritableFont headOneFont = new WritableFont(WritableFont.ARIAL, 16, WritableFont.BOLD, false,UnderlineStyle.NO_UNDERLINE);
		WritableFont headTwoFont = new WritableFont(WritableFont.ARIAL, 11, WritableFont.NO_BOLD, false,UnderlineStyle.NO_UNDERLINE);
		WritableFont bodyFont = new WritableFont(WritableFont.createFont("����"),9,WritableFont.NO_BOLD );
		
		/**��Ԫ�������ʽд��============begin============*/
		WritableCellFormat title = new WritableCellFormat(titleFont);
		WritableCellFormat headOne = new WritableCellFormat(headOneFont);
		WritableCellFormat headTwo = new WritableCellFormat(headTwoFont);
		WritableCellFormat body = new WritableCellFormat(bodyFont);
		
		/**���õ�Ԫ�����============begin============*/
		/*ˮƽ����*/
		title.setAlignment(jxl.format.Alignment.CENTRE);
		headOne.setAlignment(jxl.format.Alignment.CENTRE);
		headTwo.setAlignment(jxl.format.Alignment.CENTRE);
		body.setAlignment(jxl.format.Alignment.CENTRE);
		/*��ֱ����*/
		title.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
		headOne.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
		headTwo.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
		body.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
		
		/**���õ�Ԫ��߿�============begin============*/
		title.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK);
		headOne.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK);
		headTwo.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK);
		body.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK);
		
		/**�ϲ���Ԫ��============begin============*/
		sheet.mergeCells(0, 0, 30, 0);/*����*/
		
		/**�����п�============begin============*/
		sheet.setColumnView(0, 14);
		sheet.setColumnView(1, 20);
		sheet.setColumnView(2, 8);
		sheet.setColumnView(3, 28);
		sheet.setColumnView(4, 28);
		sheet.setColumnView(5, 28);
		sheet.setColumnView(6, 14);
		for(int i = 7; i <= 30; i++){
			sheet.setColumnView(i, 18);
		}
		
		/**�����ʼ============begin============*/
		Label col1row1 = new Label(0, 0,xn+"ѧ��ѧ�������ܷ�ͳ��",title);
		Label col1row2 = new Label(0, 1,"ѧ��",headOne);
		Label col2row2 = new Label(1, 1,"����",headOne);
		Label col3row2 = new Label(2, 1,"�꼶",headOne);
		Label col4row2 = new Label(3, 1,"ѧԺ����",headOne);
		Label col5row2 = new Label(4, 1,"רҵ����",headOne);
		Label col6row2 = new Label(5, 1,"�༶����",headOne);
		Label col7row2 = new Label(6, 1,"�ܷ�",headOne);
		Label col8row2 = new Label(7, 1,"���ʵ�ܷ�",headTwo);
		Label col9row2 = new Label(8, 1,"���ʵѧԺ����",headTwo);
		Label col10row2 = new Label(9, 1,"���ʵרҵ����",headTwo);
		Label col11row2 = new Label(10, 1,"���ʵ�༶����",headTwo);
		Label col12row2 = new Label(11, 1,"���´�ҵ�ܷ�",headTwo);
		Label col13row2 = new Label(12, 1,"���´�ҵѧԺ����",headTwo);
		Label col14row2 = new Label(13, 1,"���´�ҵרҵ����",headTwo);
		Label col15row2 = new Label(14, 1,"���´�ҵ�༶����",headTwo);
		Label col16row2 = new Label(15, 1,"���⽻���ܷ�",headTwo);
		Label col17row2 = new Label(16, 1,"���⽻��ѧԺ����",headTwo);
		Label col18row2 = new Label(17, 1,"���⽻��רҵ����",headTwo);
		Label col19row2 = new Label(18, 1,"���⽻���༶����",headTwo);
		Label col20row2 = new Label(19, 1,"��������ܷ�",headTwo);
		Label col21row2 = new Label(20, 1,"�������ѧԺ����",headTwo);
		Label col22row2 = new Label(21, 1,"�������רҵ����",headTwo);
		Label col23row2 = new Label(22, 1,"�������༶����",headTwo);
		Label col24row2 = new Label(23, 1,"��Ṥ���ܷ�",headTwo);
		Label col25row2 = new Label(24, 1,"��Ṥ��ѧԺ����",headTwo);
		Label col26row2 = new Label(25, 1,"��Ṥ��רҵ����",headTwo);
		Label col27row2 = new Label(26, 1,"��Ṥ���༶����",headTwo);
		Label col28row2 = new Label(27, 1,"�����ܷ�",headTwo);
		Label col29row2 = new Label(28, 1,"����ѧԺ����",headTwo);
		Label col30row2 = new Label(29, 1,"����רҵ����",headTwo);
		Label col31row2 = new Label(30, 1,"�����༶����",headTwo);
		sheet.addCell(col1row1);
		sheet.addCell(col1row2);
		sheet.addCell(col2row2);
		sheet.addCell(col3row2);
		sheet.addCell(col4row2);
		sheet.addCell(col5row2);
		sheet.addCell(col6row2);
		sheet.addCell(col7row2);
		sheet.addCell(col8row2);
		sheet.addCell(col9row2);
		sheet.addCell(col10row2);
		sheet.addCell(col11row2);
		sheet.addCell(col12row2);
		sheet.addCell(col13row2);
		sheet.addCell(col14row2);
		sheet.addCell(col15row2);
		sheet.addCell(col16row2);
		sheet.addCell(col17row2);
		sheet.addCell(col18row2);
		sheet.addCell(col19row2);
		sheet.addCell(col20row2);
		sheet.addCell(col21row2);
		sheet.addCell(col22row2);
		sheet.addCell(col23row2);
		sheet.addCell(col24row2);
		sheet.addCell(col25row2);
		sheet.addCell(col26row2);
		sheet.addCell(col27row2);
		sheet.addCell(col28row2);
		sheet.addCell(col29row2);
		sheet.addCell(col30row2);
		sheet.addCell(col31row2);
		
		if(resultList.size()>0){
			for(int j = 0; j < resultList.size(); j++){
				Map<String,String> map = resultList.get(j);
				Label date_xh = new Label(0, 2+j, map.get("xh"), body);
				Label date_xm = new Label(1, 2+j, map.get("xm"), body);
				Label date_nj = new Label(2, 2+j, map.get("nj"), body);
				Label date_xymc = new Label(3, 2+j, map.get("xymc"), body);
				Label date_zymc = new Label(4, 2+j, map.get("zymc"), body);
				Label date_bjmc = new Label(5, 2+j, map.get("bjmc"), body);
				Label date_zf = new Label(6, 2+j, map.get("zf"), body);
				Label date_hdjszf = new Label(7, 2+j, map.get("hdjszf"), body);
				Label date_hdjsxypm = new Label(8, 2+j, map.get("hdjsxypm"), body);
				Label date_hdjszypm = new Label(9, 2+j, map.get("hdjszypm"), body);
				Label date_hdjsbjpm = new Label(10, 2+j, map.get("hdjsbjpm"), body);
				Label date_cxcyzf = new Label(11, 2+j, map.get("cxcyzf"), body);
				Label date_cxcyxypm = new Label(12, 2+j, map.get("cxcyxypm"), body);
				Label date_cxcyzypm = new Label(13, 2+j, map.get("cxcyzypm"), body);
				Label date_cxcybjpm = new Label(14, 2+j, map.get("cxcybjpm"), body);
				Label date_dwjlzf = new Label(15, 2+j, map.get("dwjlzf"), body);
				Label date_dwjlxypm = new Label(16, 2+j, map.get("dwjlxypm"), body);
				Label date_dwjlzypm = new Label(17, 2+j, map.get("dwjlzypm"), body);
				Label date_dwjlbjpm = new Label(18, 2+j, map.get("dwjlbjpm"), body);
				Label date_gyfwzf = new Label(19, 2+j, map.get("gyfwzf"), body);
				Label date_gyfwxypm = new Label(20, 2+j, map.get("gyfwxypm"), body);
				Label date_gyfwzypm = new Label(21, 2+j, map.get("gyfwzypm"), body);
				Label date_gyfwbjpm = new Label(22, 2+j, map.get("gyfwbjpm"), body);
				Label date_shgzzf = new Label(23, 2+j, map.get("shgzzf"), body);
				Label date_shgzxypm = new Label(24, 2+j, map.get("shgzxypm"), body);
				Label date_shgzzypm = new Label(25, 2+j, map.get("shgzzypm"), body);
				Label date_shgzbjpm = new Label(26, 2+j, map.get("shgzbjpm"), body);
				Label date_wthdzf = new Label(27, 2+j, map.get("wthdzf"), body);
				Label date_wthdxypm = new Label(28, 2+j, map.get("wthdxypm"), body);
				Label date_wthdzypm = new Label(29, 2+j, map.get("wthdzypm"), body);
				Label date_wthdbjpm = new Label(30, 2+j, map.get("wthdbjpm"), body);
				
				sheet.addCell(date_xh);
				sheet.addCell(date_xm);
				sheet.addCell(date_nj);
				sheet.addCell(date_xymc);
				sheet.addCell(date_zymc);
				sheet.addCell(date_bjmc);
				sheet.addCell(date_zf);
				sheet.addCell(date_hdjszf);
				sheet.addCell(date_hdjsxypm);
				sheet.addCell(date_hdjszypm);
				sheet.addCell(date_hdjsbjpm);
				sheet.addCell(date_cxcyzf);
				sheet.addCell(date_cxcyxypm);
				sheet.addCell(date_cxcyzypm);
				sheet.addCell(date_cxcybjpm);
				sheet.addCell(date_dwjlzf);
				sheet.addCell(date_dwjlxypm);
				sheet.addCell(date_dwjlzypm);
				sheet.addCell(date_dwjlbjpm);
				sheet.addCell(date_gyfwzf);
				sheet.addCell(date_gyfwxypm);
				sheet.addCell(date_gyfwzypm);
				sheet.addCell(date_gyfwbjpm);
				sheet.addCell(date_shgzzf);
				sheet.addCell(date_shgzxypm);
				sheet.addCell(date_shgzzypm);
				sheet.addCell(date_shgzbjpm);
				sheet.addCell(date_wthdzf);
				sheet.addCell(date_wthdxypm);
				sheet.addCell(date_wthdzypm);
				sheet.addCell(date_wthdbjpm);
			}
		}
		
		/**д��Excel�����*/
		wwb.write();
		/**�ر���*/
		wwb.close();
		return file;
	}
}
