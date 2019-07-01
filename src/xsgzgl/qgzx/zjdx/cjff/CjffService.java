/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-12-20 ����03:15:35 
 */  
package xsgzgl.qgzx.zjdx.cjff;

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

import xgxt.form.User;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.zjly.zhf.zhfdr.ZhfDrDao;
import com.zfsoft.xgxt.zjly.zhf.zhfdr.ZhfDrForm;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� yxy[����:1206]
 * @ʱ�䣺 2016-12-20 ����03:15:35 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class CjffService extends SuperServiceImpl<CjffForm, CjffDao> {
	private static final String DATE_FORMAT = "yyyy-MM-dd";
	/**
	 * 
	 * @����: ���˵�λList
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-12-21 ����10:11:08
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getYrdwList(){
		return dao.getYrdwList();
	}
	
	/**
	 * 
	 * @����: ���˵�λListȨ�޿���
	 * @���ߣ�Meng.Wei[���ţ�1186]
	 * @���ڣ�2016-12-21 ����10:11:08
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getYrdwyList(User user){
		return dao.getYrdwyList(user);
	}
	
	/**
	 * 
	 * @����:��λ����List
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-12-21 ����10:14:27
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getGwxzList(){
		return dao.getGwxzList();
	}
	
	/**
	 * 
	 * @����: ��λ���List
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-12-21 ����10:15:33
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getGwlbList(){
		return dao.getGwlbList();
	}
	
	/**
	 *
	 * @����:У��List
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-12-21 ����10:16:16
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getXqList(){
		return dao.getXqList();
	}
	
	/**
	 * 
	 * @����: ����������Ϣ
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-12-21 ����10:47:16
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String> getCsszMap(){
		return dao.getCsszMap();
	}
	
	/**
	 * 
	 * @����: ���췢���·�List
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-12-21 ����11:12:27
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> createList(String ksyf,String jsyf){

		List<HashMap<String, String>> yfList = new ArrayList<HashMap<String,String>>();
		//�Ƚ��п��ж�
		if(StringUtils.isNull(ksyf) && StringUtils.isNull(jsyf)){
			return null;
		}else if(StringUtils.isNull(ksyf) || StringUtils.isNull(jsyf)){
			String yfz =  StringUtils.isNull(ksyf) ? jsyf :ksyf;
			
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("yf", yfz);
			yfList.add(map);
			return yfList;
		}else {
			yfList = dao.getYfList(ksyf, jsyf);
		}
		return yfList;
	}
	
	/**
	 * 
	 * @����: ��֤�Ƿ��ڳ�𷢷ſ���ʱ�����
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-12-21 ����02:22:20
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean checkIsInKfsjd(int kssj,int jssj){
		return dao.checkIsInKfsjd(kssj, jssj);
	}
	
	/**
	 * 
	 * @����: ��֤�����Ƿ����ظ���Ψһ��(xh,ffndyf,yrdwdm)
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-12-21 ����04:21:59
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param ffndyf
	 * @param yrdwdm
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean checkIsNotExists(String xh,String ffndyf,String yrdwdm,String id){
		return dao.checkIsNotExists(xh, ffndyf, yrdwdm, id);
	}
	
	/**
	 * 
	 * @����: ��ȡѧ��������Ϣ
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-12-21 ����04:56:55
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String> getXsxxck(String id){
		return dao.getXsxxck(id);
	}
	
	/**
	 * 
	 * @����: ҵ�����Ϣ�鿴
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-12-21 ����05:44:41
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param id
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String> getYwbdxxCk(String id){
		return dao.getYwbdxxCk(id);
	}
	
	/**
	 * 
	 * @����: ��֤ѧ�ţ������Ƿ���ȷ
	 * ���ѧ�Ŵ���view_xsxxb�У�������֤view_xsxxb�е������������xm�Ƿ����;��������ڣ����õ���xmΪ�գ�ֱ�ӷ���true
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-12-21 ����04:43:33
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean checkXhXmIsTrue(String xh,String xm){
		return dao.checkXhXmIsTrue(xh, xm);
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @����: �����
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-12-22 ����09:09:00
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean saveForm(CjffForm model) throws Exception{
		String type = model.getType();
		boolean result = true;
		if(type.indexOf("submit") != -1){
			model.setSftj("1");
		}
		if(StringUtils.isNull(model.getId())){
			model.setLrsj(GetTime.getTimeByFormat(DATE_FORMAT));
			result = dao.runInsert(model);
		}else{
			result = dao.runUpdate(model);
		}
		return result;
	}
	
	/**
	 * @throws Exception 
	 *
	 * @����: �ύ
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-12-22 ����09:27:11
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param id
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean submit(String[] id) throws Exception{
		return dao.submit(id);
	}
	
	/**
	 * 
	 * @����:���ص���ģ��,����excel��
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-12-23 ����09:30:12
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param os
	 * @throws Exception
	 * void �������� 
	 * @throws
	 */
	public  void createWwb(OutputStream os) throws Exception{
		WritableWorkbook wwb = Workbook.createWorkbook(os);
		WritableCellFormat titlefont =  this.getFontStyle("title");
		/**
		 * sheet1
		 */
		WritableSheet ws = wwb.createSheet("���ݵ���ģ��", 0);
	    Label row1col1= new Label(0, 0, "ѧ��",titlefont);
	    Label row1col2= new Label(1, 0, "����",titlefont);
	    Label row1col3= new Label(2, 0, "��������·�(�磺2017-01)",titlefont);
//	    Label row1col4= new Label(3, 0, "�����·�",titlefont);
	    Label row1col5= new Label(3, 0, "���˵�λ",titlefont);
		Label row1col6= new Label(4, 0, "У��",titlefont);
		Label row1col7= new Label(5, 0, "��λ���",titlefont);
		Label row1col8= new Label(6, 0, "��λ����",titlefont);
		Label row1col9= new Label(7, 0, "���(Ԫ)",titlefont);
		Label row1col10= new Label(8, 0, "��������",titlefont);
		Label row1col11= new Label(9, 0, "��ע",titlefont);
		 
		ws.addCell(row1col1);
		ws.addCell(row1col2);
		ws.addCell(row1col3);
//		ws.addCell(row1col4);
		ws.addCell(row1col5);
		ws.addCell(row1col6);
		ws.addCell(row1col7);
		ws.addCell(row1col8);
		ws.addCell(row1col9);
		ws.addCell(row1col10);
	    ws.addCell(row1col11);
	    WritableCellFormat wcfF = new WritableCellFormat(
	    	      NumberFormats.TEXT); //
	    CellView cv = new CellView(); //����һ������ʾ��ʽ 
	    cv.setFormat(wcfF);//�Ѷ���ĵ�Ԫ���ʽ��ʼ����ȥ
	    cv.setSize(10*265);//�����п�ȣ������õĻ���0��������ʾ��
	    for (int i = 0; i < 10; i++) {
	    	ws.setColumnView(i, cv);
		}
	    //���ù������е�n�е���ʽ
	    /**
	     * sheet2 ���˵�λ���ձ�
	     */
	    WritableSheet ws1 = wwb.createSheet("���˵�λ���ձ�", 1);
	    List<HashMap<String, String>> yrdwList = dao.getYrdwList();
	    Label ws1row1col1= new Label(0, 0, "���˵�λ����",titlefont);
//	    Label ws1row1col2= new Label(1, 0, "���˵�λ����",titlefont);
	    ws1.addCell(ws1row1col1);
//	    ws1.addCell(ws1row1col2);
	    for (int i = 0; i < yrdwList.size(); i++) {
	    	Label tempLabel = new Label(0, i+1, yrdwList.get(i).get("yrdwmc"),titlefont);
//	    	Label tempLabe2 = new Label(1, i+1, yrdwList.get(i).get("yrdwmc"),titlefont);
	    	 ws1.addCell(tempLabel);
//	 	     ws1.addCell(tempLabe2);
		}
	    /**
	     * sheet3 У�����ձ�
	     */
	    WritableSheet ws2 = wwb.createSheet("У�����ձ�", 2);
	    List<HashMap<String, String>> xqList = dao.getXqList();
	    Label ws2row1col1= new Label(0, 0, "У������",titlefont);
//	    Label ws2row1col2= new Label(1, 0, "У������",titlefont);
	    ws2.addCell(ws2row1col1);
//	    ws2.addCell(ws2row1col2);
	    for (int i = 0; i < xqList.size(); i++) {
	    	Label tempLabel = new Label(0, i+1, xqList.get(i).get("xqmc"),titlefont);
//	    	Label tempLabe2 = new Label(1, i+1, xqList.get(i).get("xqmc"),titlefont);
	    	 ws2.addCell(tempLabel);
//	 	     ws2.addCell(tempLabe2);
		}
	    /**
	     * shee4 ��λ�����ձ�
	     */
	    WritableSheet ws3 = wwb.createSheet("��λ�����ձ�",3);
	    List<HashMap<String, String>> gwlbList = dao.getGwlbList();
	    Label ws3row1col1= new Label(0, 0, "��λ�������",titlefont);
//	    Label ws3row1col2= new Label(1, 0, "��λ�������",titlefont);
	    ws3.addCell(ws3row1col1);
//	    ws3.addCell(ws3row1col2);
	    for (int i = 0; i < gwlbList.size(); i++) {
	    	Label tempLabel = new Label(0, i+1, gwlbList.get(i).get("gwlbmc"),titlefont);
//	    	Label tempLabe2 = new Label(1, i+1, gwlbList.get(i).get("gwlbmc"),titlefont);
	    	 ws3.addCell(tempLabel);
//	 	     ws3.addCell(tempLabe2);
		}
	    /**
	     * shee5 ��λ���ʶ��ձ�
	     */
	    WritableSheet ws4 = wwb.createSheet("��λ���ʶ��ձ�",4);
	    List<HashMap<String, String>> gwxzList = dao.getGwxzList();
	    Label ws4row1col1= new Label(0, 0, "��λ��������",titlefont);
//	    Label ws4row1col2= new Label(1, 0, "��λ��������",titlefont);
	    ws4.addCell(ws4row1col1);
//	    ws4.addCell(ws4row1col2);
	    for (int i = 0; i < gwxzList.size(); i++) {
	    	Label tempLabel = new Label(0, i+1, gwxzList.get(i).get("gwxzmc"),titlefont);
//	    	Label tempLabe2 = new Label(1, i+1, gwxzList.get(i).get("gwxzmc"),titlefont);
	    	ws4.addCell(tempLabel);
//	 	    ws4.addCell(tempLabe2);
		}
	    try{
			 wwb.write();
			 wwb.close();
		 }catch(Exception e){
				
		 }
	}
	
	/**
	 * 
	 * @����: excel����
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-12-23 ����09:47:25
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
//			  wcf_table.setShrinkToFit(true);
	          wcf_table.setAlignment(jxl.format.Alignment.CENTRE);
	          wcf_table.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
	          return wcf_table;
		}else if("errorinfo".equals(paras)){
			WritableFont wf_table = new WritableFont(WritableFont.ARIAL, 10,  
	                WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE,  
	                jxl.format.Colour.RED);   
		  WritableCellFormat wcf_table = new WritableCellFormat(wf_table);   
//		  wcf_table.setShrinkToFit(true);
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
	public HashMap<String, Object> saveDrExcelInfo(InputStream is,CjffForm cjffForm){
		HashMap<String, Object> resultMap= null;
		try {
			 resultMap= this.DrExcelInfoCheck(is, cjffForm);
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
				
			
				String gid = this.uploadErrorExcel((List<String[]>) resultMap.get("errorlist"),cjffForm.getFilepath());
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
	public HashMap<String, Object> DrExcelInfoCheck(InputStream is,CjffForm cjffForm){
		//�����¼����
		List<String[]> drlist = new ArrayList<String[]>();
		//�����¼����
		List<String[]> errorlist = new ArrayList<String[]>();
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("result", "true");
	    String lrsj = GetTime.getTimeByFormat(DATE_FORMAT);
		Workbook rwb = null;
		try {
			rwb = Workbook.getWorkbook(is);
			Sheet rs=rwb.getSheet(0);
		    int clos=rs.getColumns();//�õ����е���
	        int rows=rs.getRows();//�õ����е���
//	        System.out.println(clos+" rows:"+rows);
//	        logger.info(clos+" rows:"+rows);
//	        logger.info(rs.getCell(0, 0).getContents());
//	        logger.info(rs.getCell(0,1).getContents());
//	        logger.info(rs.getCell(3,1).getContents());
//	        logger.info(rs.getCell(3,3).getContents());
//	        logger.info(rs.getCell(3,3).getContents());
	        //��
	        //�ж�excel����Ƿ�Ϊ��
	        if(rows < 2){
	        	resultMap.put("result", "null");
	        }else if(!this.checkExcelRepeat(rs, clos, rows)){
	        	resultMap.put("result", "excelrepeat");
	        }else{
	          HashMap<String, String> hbMap = this.checkHbyz(rs, clos, rows, cjffForm.getFilepath());
	          if(!hbMap.get("message").equals("true")){
	        	  resultMap.put("result", hbMap.get("message"));
	        	  resultMap.put("gid", hbMap.get("gid"));
	        	  return resultMap;
	          }
	        	//��
	        	  for (int i = 1; i < rows; i++) {
	  	        	
	  	        		//ȡ��ÿ����֤���ݣ�����lsmap
	  	        		HashMap<String, String>  lsmap = new HashMap<String, String>();
	  	        		String xh = rs.getCell(0, i).getContents();
	  	        		String xm = rs.getCell(1, i).getContents();
	  	        		String ffndyf = rs.getCell(2, i).getContents();
	  	        		String yrdwmc = rs.getCell(3, i).getContents();
	  	        		String xqmc = rs.getCell(4, i).getContents();
	  	        		String gwlbmc = rs.getCell(5, i).getContents();
	  	        		String gwxzmc = rs.getCell(6, i).getContents();
	  	        		String bcje = rs.getCell(7, i).getContents();
	  	        		String gznr = rs.getCell(8, i).getContents();
	  	        		String bz = rs.getCell(9, i).getContents();
	  	        		lsmap.put("xh", xh);
	  	        		lsmap.put("xm", xm);
	  	        		lsmap.put("ffndyf", ffndyf);
	  	        		lsmap.put("yrdwmc", yrdwmc);
	  	        		lsmap.put("xqmc", xqmc);
	  	        		lsmap.put("gwlbmc",gwlbmc);
	  	        		lsmap.put("gwxzmc",gwxzmc);
	  	        		lsmap.put("bcje", bcje);
	  	        		lsmap.put("gznr", gznr);
	  	        		lsmap.put("bz", bz);
	  	        		//�����ж�
	  	        	    if(this.checkNullRow(lsmap)){
	  	        	    	continue;
	  	        	    }
	  	        		
	  	        		HashMap<String,Object> resultmap = this.checkEveryRowRecord(lsmap, cjffForm);
	  	        		ArrayList<String> paralist = new ArrayList<String>();
	  	        		if("false".equals(resultmap.get("result"))){
	  	        			paralist.add(lsmap.get("xh"));
	  	        			paralist.add(lsmap.get("xm"));
	  	        			paralist.add(lsmap.get("ffndyf"));
	  	        			paralist.add(lsmap.get("yrdwmc"));
	  	        			paralist.add(lsmap.get("xqmc"));
	  	        			paralist.add(lsmap.get("gwlbmc"));
	  	        			paralist.add(lsmap.get("gwxzmc"));
	  	        			paralist.add(lsmap.get("bcje"));
	  	        			paralist.add(lsmap.get("gznr"));
	  	        			paralist.add(lsmap.get("bz"));
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
	  	        			paralist.add(lsmap.get("xm"));
	  	        			paralist.add(lsmap.get("ffndyf"));
	  	        			paralist.add(lsmap.get("yrdwdm"));
	  	        			paralist.add(lsmap.get("xqdm"));
	  	        			paralist.add(lsmap.get("gwlbdm"));
	  	        			paralist.add(lsmap.get("gwxzdm"));
	  	        			paralist.add(lsmap.get("gss"));
	  	        			paralist.add(lsmap.get("bcje"));
	  	        			paralist.add(lsmap.get("gznr"));
	  	        			paralist.add(lsmap.get("bz"));
	  	        			paralist.add("0");
	  	        			paralist.add(cjffForm.getLrr());
	  	        			paralist.add(lrsj);
	  	        			drlist.add(paralist.toArray(new String[]{}));
	  	        		}
	  			}
//	        	  if(null == drlist && null == errorlist ){
//	        		  
//	        		  return resultMap;
//	        	  }
	        	  
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
	public HashMap<String, Object> checkEveryRowRecord(HashMap<String, String> lsmap,CjffForm cjffForm){
		/**
		 * ��֤����
		 * 1.xh���ڵ�����£���֤ѧ�ź������Ƿ�ƥ��
		 * 2.xh,ffndyf,yrdwdm������֤
		 * 3.��𳬳�����Ҫ��������뱸ע
		 * ��֤��ʽ��ȡ��������ȫ����֤�꣬������������lsmap��������뷵��map�����з���
		 * �����һ����֤�������㣬���뷵�ؽ��mapΪ�ַ���"false",���ȫ����ȷ��Ϊ�ַ���"true"
		 */
		String message = "true";
		//Ϊ����֤
		if(!this.checkNotNull(lsmap)){
				message = "false";
				lsmap.put("kyz", "ѧ�š�������������ȡ������·ݡ����˵�λ��У������λ��𡢸�λ���ʡ�������������ݲ���Ϊ�գ�");
		}else{
			//xh,xm������֤�Ƿ���ȷ��ѧ�Ŵ��ڣ���֤�����Ƿ��뵼�������һ�£������������֤ͨ��
			if(!dao.checkXhXmIsTrueDr(lsmap.get("xh"),lsmap.get("xm"))){
				message = "false";
				lsmap.put("xhxmlhyz", "ѧ�š���������Ӧ���пո�");
			}
			//��������·ݸ�ʽ��飬Ҫ����xxxx-xx ����2016-12
			try {
				{
					DateFormat formatter = new SimpleDateFormat("yyyy-MM");  
					 try {
						Date date = (Date)formatter.parse(lsmap.get("ffndyf"));
						if(!lsmap.get("ffndyf").equals(formatter.format(date))){
							message = "false";
							lsmap.put("ffndyfyz", "��������·ݸ�ʽ����Ϊyyyy-mm��");
						}
					} catch (ParseException e) {
						// TODO �Զ����� catch ��
						message = "false";
						lsmap.put("ffndyfyz", "��������·ݸ�ʽ����Ϊyyyy-mm��");
					}  
				}
			} catch (Exception e) {
				// TODO �Զ����� catch ��
				message = "false";
				lsmap.put("cysjyz", "��������·ݸ�ʽ����Ϊyyyy-mm��");
			}
			List<HashMap<String, String>> yfList = cjffForm.getYfList();
			boolean flag = false;
			for (HashMap<String, String> hashMap : yfList) {
				if(lsmap.get("ffndyf").trim().equals(hashMap.get("yf"))){
					flag = true;
				}
			}
			if(!flag){
				message = "false";
				lsmap.put("yfhfxyz", "��������·ݲ��Ϸ�������ղ������ã�");
			}
			//���˵�λ�Ƿ���� 
			String yrdwdm = dao.getYrdwdm(lsmap.get("yrdwmc"),null);
			if(StringUtils.isNull(yrdwdm)){
				message = "false";
				lsmap.put("yrdwyz", "���˵�λ�����ڣ�");
			}else{
				//�Ƿ���Ȩ�޵�������˵�λ���ڹ�����
				yrdwdm = dao.getYrdwdm(lsmap.get("yrdwmc"),cjffForm.getUser());
				if(StringUtils.isNull(yrdwdm)){
					message = "false";
					lsmap.put("yrdwyz", "ֻ�ܵ��뱾���ų�𷢷����ݣ�");
				}
			}
			
			//У���Ƿ����
			String xqdm = dao.getXqdm(lsmap.get("xqmc"));
			if(StringUtils.isNull(xqdm)){
				message = "false";
				lsmap.put("xqdmyz", "У�������ڣ�");
			}
			//��λ����Ƿ����
			String gwlbdm = dao.getGwlbdm(lsmap.get("gwlbmc"));
			if(StringUtils.isNull(gwlbdm)){
				message = "false";
				lsmap.put("gwlbyz", "��λ��𲻴��ڣ�");
			}
			//��λ�����Ƿ����
			String gwxzdm = dao.getGwxzdm(lsmap.get("gwxzmc"));
			if(StringUtils.isNull(gwxzdm)){
				message = "false";
				lsmap.put("gwxzyz","��λ���ʲ����ڣ�");
			}
			//xh,ffndyf,yrdwdm������֤
			if(!dao.checkIsNotExists(lsmap.get("xh"), lsmap.get("ffndyf"), yrdwdm, null)){
				message = "false";
				lsmap.put("zjyz", "������ͬѧ����ͬ���˵�λ��ͬ�������µļ�¼����ȷ�ϣ�");
			}
			
			//����˵�����ܳ���25���ַ�
			float bcje = 0;
			try {
				 bcje = Float.parseFloat(lsmap.get("bcje"));
				if(lsmap.get("bcje").length() > 6){
					message = "false";
					lsmap.put("bcjeyz", "������𳬹�6λ���֣�");
				}else{
					String[] bcjeArr = (bcje +"").split("\\.");
					if(bcjeArr.length == 2 && bcjeArr[1].length() > 1 ){
						message = "false";
						lsmap.put("bcjeyz", "���������������һλС����");
					}
				}
				
			} catch (NumberFormatException e) {
				// TODO �Զ����� catch ��
				message = "false";
				lsmap.put("bcjeyz", "���������������һλС����");
			}
			
			//��������������֤
			if(lsmap.get("gznr").trim().length() > 500){
				message = "false";
				lsmap.put("gznryz", "�������ݲ��ܳ���500�֣�");
			}
			HashMap<String, String> csszMap = dao.getCsszMap();
			
			/*���������Ƿ����������������֤========begin========*/
			/*��ȡ����������Ƿ�������������ޡ���ֵ*/
			String sfyxcgcjsx = csszMap.get("sfyxcgcjsx");
			float sxsz = Float.parseFloat(csszMap.get("sxsz"));
			if("��".equals(sfyxcgcjsx)){
				if(bcje > sxsz){
					message = "false";
					lsmap.put("bzyz", "���·��Ž��ܴ��ڳ�����ޣ�");
				}
			}else{
				if((bcje > sxsz) && ("".equals(lsmap.get("bz"))|| StringUtils.isNull(lsmap.get("bz")))){
					message = "false";
					lsmap.put("bzyz", "���·��Ž����ڳ�����ޣ�������д��ע��");
				}
			}
			/*���������Ƿ����������������֤========end========*/
			
			//��ע��֤
			if(lsmap.get("bz").trim().length() > 500){
				message = "false";
				lsmap.put("bzyz", "��ע���ܳ���500�֣�");
			}
			String gss = "";
			if("true".equals(message)){
				double jsjg = Double.parseDouble(lsmap.get("bcje"))/Double.parseDouble(csszMap.get("cjbz"));
				java.text.DecimalFormat df =new java.text.DecimalFormat("#.0");
				gss = df.format(jsjg);
				if(gss.indexOf(".") == 0){
					gss = "0"+gss;				
				}
				lsmap.put("gss", gss);
				lsmap.put("yrdwdm", yrdwdm);
				lsmap.put("xqdm", xqdm);
				lsmap.put("gwlbdm", gwlbdm);
				lsmap.put("gwxzdm", gwxzdm);
				lsmap.put("bcje", lsmap.get("bcje"));
			}
				
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
    	    Label row1col2= new Label(1, 0, "����",titlefont);
    	    Label row1col3= new Label(2, 0, "��������·�",titlefont);
//    	    Label row1col4= new Label(3, 0, "�����·�",titlefont);
    	    Label row1col5= new Label(3, 0, "���˵�λ",titlefont);
    		Label row1col6= new Label(4, 0, "У��",titlefont);
    		Label row1col7= new Label(5, 0, "��λ���",titlefont);
    		Label row1col8= new Label(6, 0, "��λ����",titlefont);
    		Label row1col9= new Label(7, 0, "���(Ԫ)",titlefont);
    		Label row1col10= new Label(8, 0, "��������",titlefont);
    		Label row1col11= new Label(9, 0, "��ע",titlefont);
    		 
    		
            
            try {
            	ws.addCell(row1col1);
        		ws.addCell(row1col2);
        		ws.addCell(row1col3);
//        		ws.addCell(row1col4);
        		ws.addCell(row1col5);
        		ws.addCell(row1col6);
        		ws.addCell(row1col7);
        		ws.addCell(row1col8);
        		ws.addCell(row1col9);
        		ws.addCell(row1col10);
        	    ws.addCell(row1col11);
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
                	 if(j<=9){
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
	public boolean saveDrDataIntoDb(List<String[]> paralist) throws Exception{
		if(paralist != null && paralist.size() > 0){
			return dao.saveDrDataIntoDb(paralist).length >0 ? true :false;
		}else{
			return false;
		}
		
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
			String xh = rs.getCell(0, i).getContents();
			String ffndyf = rs.getCell(2,i).getContents();
			String yrdwdm = rs.getCell(3,i).getContents();
			String str = "";
			if(StringUtils.isNotNull(xh)){
				str = str + xh.trim();
			}
			if(StringUtils.isNotNull(ffndyf)){
				str = str + ffndyf.trim();
			}
			if(StringUtils.isNotNull(yrdwdm)){
				str = str + yrdwdm.trim();
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
			if(StringUtils.isNull(yzMap.get(key)) && !("bz").equals(key)){
				rs = false;
				break;
			}
		}
		return rs;
	}
	
	/**
	 * 
	 * @����: ���ѻ�������֤
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2017-1-6 ����10:13:36
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param nd
	 * @param yrdwdm
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public HashMap<String, String> checkIsFhJfhb(String nd,String yrdwdm,String bcje,String id){
		return dao.checkIsFhJfhb(nd, yrdwdm, bcje,id);
	}
	
	/**
	 * 
	 * @����: �ϲ���֤
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2017-1-6 ����11:54:39
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param paraList
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
			String ffndyf = rs.getCell(2, i).getContents();
			String yrdwdm = rs.getCell(3,i).getContents();
			String bcje = rs.getCell(7, i).getContents();
			if(StringUtils.isNull(ffndyf) || ffndyf.length() <4 || StringUtils.isNull(yrdwdm) || StringUtils.isNull(bcje)){
				continue;
			}
			float sumje = 0;
			try {
				 sumje = Float.parseFloat(bcje);
			} catch (Exception e) {
				// TODO: handle exception
				continue;
			}
			String ffnd = ffndyf.substring(0, 4);
			
				for (int j = i+1; j < rows; j++) {
					String ffndyfcompare = rs.getCell(2,j).getContents();
					String yrdwdmcompare = rs.getCell(3,j).getContents();
					String bcjecompare =  rs.getCell(7,j).getContents();
					if(StringUtils.isNull(ffndyfcompare) || ffndyfcompare.length() <4  || StringUtils.isNull(yrdwdmcompare) || StringUtils.isNull(bcjecompare)){
						continue;
					}
					String ffndcompare = ffndyfcompare.substring(0, 4);
					if(ffnd.trim().equals(ffndcompare.trim()) && yrdwdm.trim().equals(yrdwdmcompare.trim())){
						containCfList.add(j+"");
						float ljje = 0;
					    try {
							 ljje = Float.parseFloat(bcjecompare);
							 sumje = sumje + ljje;
						} catch (Exception e) {
							// TODO: handle exception
							continue;
						}
					}
				}
				HashMap<String, String> dataMap = this.checkIsFhJfhbDr(ffnd, yrdwdm, sumje+"");
				if(("false").equals(dataMap.get("rs"))){
					result = false;
					dataMap.put("ffnd",ffnd);
					dataMap.put("yrdwmc",yrdwdm);
					dataMap.put("bcje",sumje+"");
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
	 * 
	 * @����: ���ѻ�������֤����
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2017-1-6 ����10:13:36
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param nd
	 * @param yrdwdm
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public HashMap<String, String> checkIsFhJfhbDr(String nd,String yrdwmc,String bcje){
		return dao.checkIsFhJfhbDr(nd, yrdwmc, bcje);
	}
	
	/**
	 * 
	 * @����: �ϴ��ϲ���֤����excel
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2017-1-6 ����03:17:15
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
			
			  // ����������
           WritableSheet ws = wwb.createSheet("��������", 0);
       	WritableCellFormat titlefont =  this.getFontStyle("title");
   		/**
   		 * sheet1
   		 */
   	    Label row1col1= new Label(0, 0, "���",titlefont);
   	    Label row1col2= new Label(1, 0, "���˵�λ",titlefont);
   	    Label row1col3= new Label(2, 0, "���˵�λʣ���",titlefont);
//   	    Label row1col4= new Label(3, 0, "�����·�",titlefont);
   	    Label row1col5= new Label(3, 0, "�����ܽ��",titlefont);
   		 
   		
           
           try {
           	ws.addCell(row1col1);
       		ws.addCell(row1col2);
       		ws.addCell(row1col3);
//       		ws.addCell(row1col4);
       		ws.addCell(row1col5);
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
    				Label labelId_0 = new Label(0, i+1,errorlist.get(i).get("ffnd") ,wcf);
    				Label labelId_1 = new Label(1, i+1,errorlist.get(i).get("yrdwmc") ,wcf);
    				Label labelId_2 = new Label(2, i+1,errorlist.get(i).get("syje") ,wcf);
    				Label labelId_3 = new Label(3, i+1,errorlist.get(i).get("bcje") ,wcf);
               		try {
							ws.addCell(labelId_0);
							ws.addCell(labelId_1);
							ws.addCell(labelId_2);
							ws.addCell(labelId_3);
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
	 * @throws Exception 
	 * 
	 * @����: ȡ���ύ��¼
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2017-1-6 ����04:54:25
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ids
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean cancelTjjl(String[] ids) throws Exception{
		return dao.cancelTjjl(ids);
	}
	
	/**
	 * 
	 * @����: ��𷢷���ʾ��Ϣ
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2017-1-9 ����09:21:51
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param nd
	 * @param sftj
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String> cjffCxTitleXx(String nd,	User user){
		HashMap<String, String> dataMap = new HashMap<String, String>();
		String sumhbje = dao.getSumHbje(nd,user);
		if("0".equals(sumhbje)){
			dataMap.put("hbje", sumhbje);
			dataMap.put("syje", "0");
			dataMap.put("tjje", "0");
			dataMap.put("wtjje", "0");
		}else{
			String sumytjje = dao.getYtjWtjje(nd, "1",user);
			String sumwtjje = dao.getYtjWtjje(nd, "0",user);
			float syje = Float.parseFloat(sumhbje)- Float.parseFloat(sumytjje);
			dataMap.put("hbje", sumhbje);
			dataMap.put("syje", syje+"");
			dataMap.put("tjje", sumytjje);
			dataMap.put("wtjje", sumwtjje);
		}
		return dataMap;
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @����: ����δ�ύ����
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2017-1-13 ����05:02:43
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean updateWtjsj() throws Exception{
		return dao.updateWtjsj();
	}
 }
