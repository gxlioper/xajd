/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-6-20 ����10:14:29 
 */  
package com.zfsoft.xgxt.zjly.zhf.zhfdr;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

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

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� yxy[����:1206]
 * @ʱ�䣺 2016-6-20 ����10:14:29 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ZhfDrService extends SuperServiceImpl<ZhfDrForm, ZhfDrDao> {
	private ResourceBundle resource = ResourceBundle.getBundle("config/fileUploadConfig");
	private static final String DATE_FORMAT = "yyyy-MM-dd";
	
	/**
	 * 
	 * @����:��ȡ����Ա��Ϣ
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-6-20 ����02:57:27
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param zgh
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String> getFdyxx(String zgh){
		return dao.getFdyxx(zgh);
	}
	
	/**
	 * 
	 * @����:����excel��Ϣ ����
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-6-20 ����05:13:47
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * HashMap<String,Object> �������� 
	 * @throws
	 */
	public HashMap<String, Object> saveDrExcelInfo(InputStream is,ZhfDrForm zhfdrform){
		HashMap<String, Object> resultMap= null;
		try {
			 resultMap= this.DrExcelInfoCheck(is, zhfdrform);
			//�ж�excel����Ƿ�Ϊ��
			if("null".equals(resultMap.get("result"))){
				return resultMap;
			}else if("true".equals(resultMap.get("result"))){
				List<String[]> paralist = (List<String[]>) resultMap.get("drlist");
				if( paralist == null ||paralist.size() == 0 ){
					resultMap.put("result", "null");
					return resultMap;
				}
				 
			  	 this.saveDrDataIntoDb(paralist);
			}else{
				String gid = this.uploadErrorExcel((List<String[]>) resultMap.get("errorlist"),zhfdrform.getFilepath());
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
	 * @param zhfdrform
	 * @return
	 * HashMap<String,Object> �������� 
	 * @throws
	 */
	public HashMap<String, Object> DrExcelInfoCheck(InputStream is,ZhfDrForm zhfdrform){
		//�����¼����
		List<String[]> drlist = new ArrayList<String[]>();
		//�����¼����
		List<String[]> errorlist = new ArrayList<String[]>() ;
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
	        if(clos < 4 || rows < 2){
	        	resultMap.put("result", "null");
	        }else{
	        	//��
	        	  for (int i = 1; i < rows; i++) {
	  	        	
	  	        		//ȡ��ÿ����֤���ݣ�����lsmap
	  	        		HashMap<String, String>  lsmap = new HashMap<String, String>();
	  	        		String xh = rs.getCell(0, i).getContents();
	  	        		String xm = rs.getCell(1, i).getContents();
	  	        		String sxsm = rs.getCell(2, i).getContents();
	  	        		String cysj = rs.getCell(3, i).getContents();
	  	        	    if(StringUtils.isNull(xh.trim()) && StringUtils.isNull(xm.trim()) && 
	  	        	    		StringUtils.isNull(sxsm.trim()) && StringUtils.isNull(cysj.trim())){
	  	        	    	continue;
	  	        	    }
	  	        		lsmap.put("xh", xh);
	  	        		lsmap.put("xm", xm);
	  	        		lsmap.put("sxsm", sxsm);
	  	        		lsmap.put("cysj", cysj);
	  	        		lsmap.put("xmmkdm", zhfdrform.getXmmkdm());
	  	        		lsmap.put("jfxmdm", zhfdrform.getJfxmdm());
	  	        		HashMap<String,Object> resultmap = this.checkEveryRowRecord(lsmap, zhfdrform);
	  	        		ArrayList<String> paralist = new ArrayList<String>();
	  	        		if("false".equals(resultmap.get("result"))){
	  	        			paralist.add(lsmap.get("xh"));
	  	        			paralist.add(lsmap.get("xm"));
	  	        			paralist.add(lsmap.get("sxsm"));
	  	        			paralist.add(lsmap.get("cysj"));
	  	        			if(resultmap.get("resultmap") != null ){
	  	        				Map<String, String> map = (HashMap<String, String>) resultmap.get("resultmap");
	  	        				for (Map.Entry<String, String> entry : map.entrySet()){
	  	        					if(!entry.getKey().equals("xh") && !entry.getKey().equals("xm")
		  	        					&&	!entry.getKey().equals("sxsm") && !entry.getKey().equals("cysj") && !entry.getKey().equals("cysj")	
		  	        					&& !entry.getKey().equals("jfxmdm") && !entry.getKey().equals("xmmkdm")){
	  	        						paralist.add(entry.getValue());
	  	        					}
	  	        					
	  	        				}
	  	        			}
	  	        			errorlist.add(paralist.toArray(new String[]{}));
	  	        			resultMap.put("result", "false");
	  	        		}else{
	  	        			paralist.add(lsmap.get("xh"));
	  	        			paralist.add(lsmap.get("sxsm"));
	  	        			paralist.add(lsmap.get("cysj"));
	  	        			paralist.add(lrsj);
	  	        			paralist.add(zhfdrform.getUser().getUserName());
	  	        			paralist.add(lsmap.get("xmmkdm"));
	  	        			paralist.add(lsmap.get("jfxmdm"));
	  	        			drlist.add(paralist.toArray(new String[]{}));
	  	        		}
	  			}
	        	  resultMap.put("drlist", drlist);
	        	  resultMap.put("errorlist", errorlist);
	        }
	      
//	      
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
	 * @����:ѭ����֤ÿ�еļ�¼
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-6-22 ����01:43:39
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * HashMap<String,Object> �������� 
	 * @throws
	 */
	public HashMap<String, Object> checkEveryRowRecord(HashMap<String, String> lsmap,ZhfDrForm zhfdrform){
		/**
		 * ��֤����
		 * 1.xh,xm������֤�Ƿ����xsxxb
		 * 2.xh,sxsm,cysj,jfxmdm������֤
		 * 3.�����¼��ʦ�����ݷ�Χ��֤
		 * 4.����˵�����ܳ���25���ַ�
		 * 5.cysj ��֤10λ��ʽ ������2015-08-08(2017/1/9�գ���Ϊ֧��yyyy-mm-dd,yyyy/MM/dd,yyyy��MM��dd�����ָ�ʽ���룬cp)
		 * ��֤��ʽ��ȡ��������ȫ����֤�꣬������������lsmap��������뷵��map�����з���
		 * �����һ����֤�������㣬���뷵�ؽ��mapΪ�ַ���"false",���ȫ����ȷ��Ϊ�ַ���"true"
		 */
		String message = "true";
		ZhfDrDao zhfdrdao = new ZhfDrDao();
		//Ϊ����֤
		if(StringUtils.isNull(lsmap.get("xh")) || StringUtils.isNull(lsmap.get("xm")) || StringUtils.isNull(lsmap.get("sxsm"))
				|| StringUtils.isNull(lsmap.get("jfxmdm")) || StringUtils.isNull(lsmap.get("xmmkdm")) || StringUtils.isNull(lsmap.get("cysj"))){
				message = "false";
				lsmap.put("kyz", "ѧ�ţ�����,����˵�����Ʒ���Ŀ����Ŀģ�飬����ʱ�䲻��Ϊ�գ�");
		}else{
			//xh,xm������֤�Ƿ����xsxxb
			if(!zhfdrdao.checkXhXmIsExist(lsmap.get("xh"),lsmap.get("xm"))){
				message = "false";
				lsmap.put("xhxmlhyz", "ѧ��/��������Ӧ�򲻴���ѧ����Ϣ���У�");
			}
			//xh,sxsm,cysj,jfxmdm������֤
			if(!zhfdrdao.checkXhXmSxsmJfxmdmCf(lsmap.get("xh"), lsmap.get("cysj"), lsmap.get("jfxmdm"),lsmap.get("sxsm") )){
				message = "false";
				lsmap.put("zjyz", "ѧ�ţ�����˵��������ʱ�䣬�Ʒ���Ŀ���벻���ظ���");
			}
			//�����¼��ʦ�����ݷ�Χ��֤
			if(!zhfdrdao.checkJsDrSjfw(lsmap.get("xh"), zhfdrform)){
				message = "false";
				lsmap.put("sjfwyz", "û��Ȩ�޵����ѧ����");
			}
			//����˵�����ܳ���25���ַ�
			if(lsmap.get("sxsm").length() > 25){
				message = "false";
				lsmap.put("sxsmyz", "����˵�����𳬹�25���֣�");
			}
			//cysj ��֤10λ��ʽ ������2015-08-08
//			if(StringUtils.isNull(lsmap.get("cysj")) || lsmap.get("cysj").length() != 10){
//				message = "false";
//				lsmap.put("cysjyz", "1����ʱ���ʽ����Ϊyyyy-mm-dd��");
//			} else{
				try {
						DateFormat formatter1 = new SimpleDateFormat("yyyy��MM��dd��");  
						 try {
							Date date1 = (Date)formatter1.parse(lsmap.get("cysj"));
							if(!lsmap.get("cysj").equals(formatter1.format(date1))){
								message = "false";
								lsmap.put("cysjyz", "����ʱ���ʽ����Ϊyyyy��MM��dd�գ�");
							}
						} catch (ParseException e) {
							DateFormat formatter2 = new SimpleDateFormat("yyyy/MM/dd");  
							 try {
									Date date2 = (Date)formatter2.parse(lsmap.get("cysj"));
									if(!lsmap.get("cysj").equals(formatter2.format(date2))){
										message = "false";
										lsmap.put("cysjyz", "����ʱ���ʽ����Ϊyyyy/MM/dd��");
									}
								} catch (ParseException e1) {
									DateFormat formatter3 = new SimpleDateFormat("yyyy-MM-dd");  
									 try {
											Date date3 = (Date)formatter3.parse(lsmap.get("cysj"));
											if(!lsmap.get("cysj").equals(formatter3.format(date3))){
												message = "false";
												lsmap.put("cysjyz", "����ʱ���ʽ����Ϊyyyy-mm-dd��");
											}
										} catch (ParseException e2) {
											message = "false";
											lsmap.put("cysjyz", "����ʱ���ʽ����Ϊyyyy-mm-dd,yyyy/MM/dd,yyyy��MM��dd��֮�е�һ�֣�");
										}  
								}  
						}  
				} catch (Exception e) {
					// TODO �Զ����� catch ��
					message = "false";
					lsmap.put("cysjyz", "����ʱ���ʽ����Ϊyyyy-mm-dd,yyyy/MM/dd,yyyy��MM��dd��֮�е�һ�֣�");
				}
//			}
				
		}
		
		
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("result", message);
		resultMap.put("resultmap", lsmap);
		return resultMap;
	}
	
	/**
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
	public String uploadErrorExcel(List<String[]> errorlist,String filepath) throws IOException{
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
            Label labelId= new Label(0, 0, "ѧ��");//��ʾ��
            Label labelName= new Label(1, 0, "����");
            Label labelSex= new Label(2, 0, "����˵��");
            Label labelNum= new Label(3, 0, "����/���ʱ��");
            
            
            try {
            	ws.addCell(labelId);
				ws.addCell(labelName);
				ws.addCell(labelSex);
	            ws.addCell(labelNum);
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
                	 if(j<=3){
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
	
	public boolean saveDrDataIntoDb(List<String[]> paralist) throws Exception{
		if(paralist != null && paralist.size() > 0){
			return dao.saveDrDataIntoDb(paralist).length >0 ? true :false;
		}else{
			return false;
		}
		
	}
	
	public boolean checkNotExists(ZhfDrForm t ){
		return dao.checkNotExists(t);
	}
	
	/**
	 * 
	 * @����:�鿴
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-6-23 ����04:50:09
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param id
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String> viewJg(String id){
		return dao.viewJg(id);
	}
	
	/**
	 * 
	 * @����:������������ݲ�����ʱ��
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-6-24 ����04:16:42
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param paralist
	 * @return
	 * @throws Exception
	 * int[] �������� 
	 * @throws
	 */
	public int[] saveDrDataIntoDblsb(List<String[]> paralist) throws Exception{
 		return dao.saveDrDataIntoDblsb(paralist);
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @����:ɾ����ʱ������
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-6-24 ����04:17:54
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean dellsbsj() throws Exception{
		return dao.dellsbsj();
	}
	
	/**
	 * @throws SQLException 
	 * 
	 * @����: �����������
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-6-24 ����04:21:47
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * String[] �������� 
	 * @throws
	 */
	public ArrayList<HashMap<String, String>> getLastDatasj(String username,String lrsj,String xmmkdm) throws Exception{
	    return dao.getLastDatasj(username, lrsj, xmmkdm);
	}
	
	/**
	 * @throws SQLException 
	 * 
	 * @����: �����������
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-6-24 ����04:21:47
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * String[] �������� 
	 * @throws
	 */
	public ArrayList<HashMap<String, String>> getLastDatasjerror(String username,String lrsj,String xmmkdm) throws Exception{
	    return dao.getLastDatasjerror(username, lrsj, xmmkdm);
	}

	public void addlog(String time, User user, String[] ids) throws Exception {
		 dao.addlog(time,user,ids);
	}
	public void dellog(String[] ids) throws Exception {
		dao.dellog(ids);
	}

	/**
	 * @throws Exception  
	 * @����:������ͷ����������
	 * @���ߣ�CP[���ţ�1352]
	 * @���ڣ�2017-2-27 ����05:54:25
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * File �������� 
	 * @throws 
	 */
	public File getZhfFile(ZhfDrForm form, User user) throws Exception {	
			//����������ͷ
			Map<String,String> map = new LinkedHashMap<String, String>();
			map.put("xh", "ѧ��");
			map.put("xm", "����");
			map.put("nj", "�꼶");
			map.put("xymc", "ϵ��");
			map.put("zymc", "רҵ");
			map.put("bjmc", "�༶");
			map.put("xmmkmc", "ģ��");
			map.put("jfxmmc", "�Ʒ���Ŀ");
			map.put("sxsm", "����");
			map.put("fs", "����");
			map.put("cysj", "����ʱ��");
			map.put("lb", "���");
			map.put("shrxm", "�����");
			map.put("shsj", "���ʱ��");
			map.put("shztmc", "���״̬");
			//��������
			form.getPages().setPageSize(Integer.MAX_VALUE);
			List<HashMap<String,String>> dataList = dao.getAllList(form, user);
			IExportService export = new ExportExcelImpl();
			return export.getExportFile(map, dataList);
		}

	/**
	 * @throws Exception  
	 * @����:��ϸ����list
	 * @���ߣ�CP[���ţ�1352]
	 * @���ڣ�2017-3-13 ����09:48:56
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getXXsxList(ZhfDrForm t, User user) throws Exception {
		return dao.getXXsxList(t, user);
	}

	/**
	 * @throws Exception 
	 * @param user 
	 * @param t 
	 * @throws WriteException 
	 * @throws IOException  
	 * @����:������ϸ����excel
	 * @���ߣ�CP[���ţ�1352]
	 * @���ڣ�2017-3-13 ����10:00:41
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param resultList
	 * @return
	 * File �������� 
	 * @throws 
	 */
	@SuppressWarnings("unchecked")
	public File getZhfXxsx(List<HashMap<String, String>> list, ZhfDrForm t, User user) throws Exception {
		File file = new File(System.getProperty("java.io.tmpdir"),System.currentTimeMillis()+".xls");
		if(!file.exists()){
			file.createNewFile();
		}
		if(null != list && list.size()>0){	
			WritableWorkbook wwb = Workbook.createWorkbook(file);
			WritableSheet sheet = wwb.createSheet("sheet1", 0);
			WritableFont headFont = new WritableFont(WritableFont.ARIAL, 12, WritableFont.BOLD, false,UnderlineStyle.NO_UNDERLINE);
			WritableFont bodyFont = new WritableFont(WritableFont.ARIAL, 10, WritableFont.NO_BOLD, false,UnderlineStyle.NO_UNDERLINE);
			WritableCellFormat head_cf = new WritableCellFormat(headFont);//�������ı�ͷ����
			WritableCellFormat body_cf = new WritableCellFormat(bodyFont);//�������ĵ�Ԫ������
			head_cf.setAlignment(jxl.format.Alignment.CENTRE);
			head_cf.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
			body_cf.setAlignment(jxl.format.Alignment.LEFT);
			body_cf.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
			body_cf.setWrap(true);
			head_cf.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK);
			body_cf.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK);
			//��ͷ
			sheet.setColumnView(0, 14);
			sheet.setColumnView(1, 10);
			sheet.setColumnView(2, 8);
			sheet.setColumnView(3, 14);
			sheet.setColumnView(4, 16);
			sheet.setColumnView(5, 16);
			sheet.setColumnView(6, 14);
			sheet.setColumnView(7, 30);
			sheet.setColumnView(8, 30);
			sheet.setColumnView(9, 8);
			sheet.setColumnView(10, 12);
			sheet.setColumnView(11, 12);
			sheet.setColumnView(12, 12);
			sheet.setColumnView(13, 12);
			sheet.setColumnView(14, 12);
			Label label_title1 = new Label(0, 0,"ѧ��",head_cf);
			Label label_title2 = new Label(1, 0,"����",head_cf);
			Label label_title3 = new Label(2, 0,"�꼶",head_cf);
			Label label_title4 = new Label(3, 0,"ѧԺ",head_cf);
			Label label_title5 = new Label(4, 0,"רҵ",head_cf);
			Label label_title6 = new Label(5, 0,"�༶",head_cf);
			Label label_title7 = new Label(6, 0,"ģ��",head_cf);
			Label label_title8 = new Label(7, 0,"�Ʒ���Ŀ",head_cf);
			Label label_title9 = new Label(8, 0,"����",head_cf);
			Label label_title10 = new Label(9, 0,"����",head_cf);
			Label label_title11 = new Label(10, 0,"����ʱ��",head_cf);
			Label label_title12 = new Label(11, 0,"���",head_cf);
			Label label_title13 = new Label(12, 0,"�����",head_cf);
			Label label_title14 = new Label(13, 0,"���ʱ��",head_cf);
			Label label_title15 = new Label(14, 0,"���״̬",head_cf);
			sheet.addCell(label_title1);
			sheet.addCell(label_title2);
			sheet.addCell(label_title3);
			sheet.addCell(label_title4);
			sheet.addCell(label_title5);
			sheet.addCell(label_title6);
			sheet.addCell(label_title7);
			sheet.addCell(label_title8);
			sheet.addCell(label_title9);
			sheet.addCell(label_title10);
			sheet.addCell(label_title11);
			sheet.addCell(label_title12);
			sheet.addCell(label_title13);
			sheet.addCell(label_title14);
			sheet.addCell(label_title15);
		       //ȡ�� xh :mkxmmc ��Ӧ����
		       List<HashMap<String,String>> mktslist = dao.getMktsList(t, user);
		       HashMap<String, String> mkmap = new HashMap<String, String> ();
		       for (int i = 0; i < mktslist.size(); i++) {
		    	   mkmap.put(mktslist.get(i).get("xh"), mktslist.get(i).get("mkts"));
		       }
		       //ȡ�� xh :jfxmmc ��Ӧ����
		       List<HashMap<String,String>> xmtslist = dao.getXmtsList(t, user);
		       HashMap<String, String> xmmap = new HashMap<String, String> ();
		       for (int i = 0; i < xmtslist.size(); i++) {
		    	   xmmap.put(xmtslist.get(i).get("xh"), xmtslist.get(i).get("xmts"));
		       }
		       
		        String bjxh ="";//���ѧ�ţ�����ͬһѧ�Ŷ��ȥ�ϲ�ǰ�����ֶ�
		        String bjmk ="";//���ģ�飬����ͬһѧ�Ŷ��ȥ�ϲ�ǰ�����ֶ�
		        String bjxm = "";//�����Ŀ������ͬһѧ�Ŷ��ȥ�ϲ�ǰ�����ֶ�
		        HashMap<String, String> mkmaps = new HashMap<String, String>();
		        HashMap<String, String> xmmaps = new HashMap<String, String>();
			for(int i = 0;i<list.size();i++){
				  Label xh = new Label(0, 1+i, list.get(i).get("xh"), body_cf);
				  Label xm = new Label(1, 1+i, list.get(i).get("xm"), body_cf);
				  Label nj = new Label(2, 1+i, list.get(i).get("nj"), body_cf);
				  Label xymc = new Label(3, 1+i, list.get(i).get("xymc"), body_cf);
				  Label zymc = new Label(4, 1+i, list.get(i).get("zymc"), body_cf);
				  Label bjmc = new Label(5, 1+i, list.get(i).get("bjmc"), body_cf);
				  Label xmmkmc = new Label(6, 1+i, list.get(i).get("xmmkmc"), body_cf);
				  Label jfxmmc = new Label(7, 1+i, list.get(i).get("jfxmmc"), body_cf);
				  Label sxsm = new Label(8, 1+i, list.get(i).get("sxsm"), body_cf);
				  Label fs = new Label(9, 1+i, list.get(i).get("fs"), body_cf);
				  Label cysj = new Label(10, 1+i, list.get(i).get("cysj"), body_cf);
				  Label lb = new Label(11, 1+i, list.get(i).get("lb"), body_cf);
				  Label shr = new Label(12, 1+i, list.get(i).get("shrxm"), body_cf);
				  Label shsj = new Label(13, 1+i, list.get(i).get("shsj"), body_cf);
				  Label shztmc = new Label(14, 1+i, list.get(i).get("shztmc"), body_cf);
				  
				String dqxh = list.get(i).get("xh");//ȡ����ǰ1����ѧ��
				String dqmk = list.get(i).get("xmmkmc");//ȡ����ǰ1����ģ��
				String dqxm = list.get(i).get("jfxmmc");//ȡ����ǰ1����ģ��
				if (!bjxh.equals(dqxh)) {
					 mkmaps = new HashMap<String, String>();
				      xmmaps = new HashMap<String, String>();
				       bjxh ="";//���ѧ�ţ�����ͬһѧ�Ŷ��ȥ�ϲ�ǰ�����ֶ�
				       bjmk ="";//���ģ��
				       bjxm = "";//�����Ŀ
				}
 				if (dqxh.equals(bjxh)&&dqmk.equals(bjmk)&&dqxm.equals(bjxm)) {//�����ǰѧ�ŵ�ǰģ�鵱ǰ��Ŀ�����ǵ�һ�ν���ѭ����ֱ�Ӳ���
				 	sheet.addCell(xh);
					sheet.addCell(xm);
					sheet.addCell(nj);
					sheet.addCell(xymc);
					sheet.addCell(zymc);
					sheet.addCell(bjmc);
					sheet.addCell(xmmkmc);
					sheet.addCell(jfxmmc);
					sheet.addCell(sxsm);
					sheet.addCell(fs);
					sheet.addCell(cysj);
					sheet.addCell(lb);
					sheet.addCell(shr);
					sheet.addCell(shsj);
					sheet.addCell(shztmc);
					  bjxh =dqxh;//���ѧ��
					  bjmk = dqmk;//���ģ��
					  bjxm =dqxm;//���ģ��
				}else {
					 int a =0;//aΪģ������ҲΪxh���� ����¼ǰ6�е� �����ۼ�
					 int b =0; //��¼��7�� ģ���е��ۼ�
					 int c =0;//��¼��8�� ��Ŀ�е��ۼ�
					if (!dqxh.equals(bjxh)) { //��ʱ����ǰѧ�ŵ�һ�γ��֣���ǰģ�顢��Ŀ�϶�Ҳ�ǵ�һ�γ��֣���ʱ��Ҫ�ϲ�ǰ6�л�����Ϣ��ģ���У���Ŀ��
						//����ģ��map��1��ȡѧ��Ҫ�ϲ���������2��ȡ�� {xmmkmc: ����}��mkmaps
						Iterator it = mkmap.keySet().iterator();  
						while (it.hasNext()) { 
							String key = it.next().toString();
							if (dqxh.equals(key)) {//��ǰxh��map���ҵ���Ӧģ��
								String []mkxx = mkmap.get(key).split(";");
								if (mkxx.length==1) {//ֻ��һ��ģ�飬ȡ��������   ˼������&3
									a = Integer.parseInt(mkxx[0].substring(mkxx[0].indexOf("&")+1,mkxx[0].length()));
									mkmaps.put(mkxx[0].substring(0,4),mkxx[0].substring(mkxx[0].lastIndexOf("&")+1,mkxx[0].length()));
								}else {//���ģ��  ��������&1;��������&1;˼������&3 substring(5,)
									for (int j = 0; j < mkxx.length; j++) {
										a += Integer.parseInt(mkxx[j].substring(mkxx[j].lastIndexOf("&")+1,mkxx[j].length()));
										mkmaps.put(mkxx[j].substring(0,4), mkxx[j].substring(mkxx[j].lastIndexOf("&")+1,mkxx[j].length()));
									}
								}
								break;
							}
						}
						//������Ŀmap ȡ�� {jfxmmc: ����}��xmmaps
						Iterator it1 = xmmap.keySet().iterator();  
						while (it1.hasNext()) { 
							String key = it1.next().toString();
							if (dqxh.equals(key)) {//��ǰxh��map���ҵ���Ӧģ��
								String []xmxx = xmmap.get(key).split(";");
									for (int j = 0; j < xmxx.length; j++) {
										xmmaps.put(xmxx[j].substring(0,xmxx[j].lastIndexOf("&")), xmxx[j].substring(xmxx[j].length()-1));
									}
								break;
							}
						}
						//ȡ��a��ǰ6�кϲ�
					  	sheet.addCell(xh);
						sheet.addCell(xm);
						sheet.addCell(nj);
						sheet.addCell(xymc);
						sheet.addCell(zymc);
						sheet.addCell(bjmc);
						sheet.mergeCells(0,1+i,0,a+i);
						sheet.mergeCells(1,1+i,1,a+i);
						sheet.mergeCells(2,1+i,2,a+i);
						sheet.mergeCells(3,1+i,3,a+i);
						sheet.mergeCells(4,1+i,4,a+i);
						sheet.mergeCells(5,1+i,5,a+i);
						//��7��ģ���кϲ�������ȡ
						 Iterator it2 = mkmaps.keySet().iterator();  
						  while (it2.hasNext()) {
							  String key = it2.next().toString();
							  if (list.get(i).get("xmmkmc").equals(key)) {//���ݵ�ǰ�������ݵ���Ŀģ��
								  b=Integer.parseInt(mkmaps.get(key));
								  break;
							  }
						  }
						sheet.addCell(xmmkmc);
						sheet.mergeCells(6,1+i,6,b+i);
						/**
						 * mergeCells�ϲ���Ԫ��
						 * ��һ��������Ҫ�ϲ��ĵ�Ԫ�������Ͻǵ��к�
						 * �ڶ���������Ҫ�ϲ��ĵ�Ԫ�������Ͻǵ��к�
						 * ������������Ҫ�ϲ��ĵ�Ԫ�����ҽǵ��к�
						 * ���ĸ�������Ҫ�ϲ��ĵ�Ԫ�������½ǵ��к�
						 */
						//��8����Ŀ�кϲ�������ȡ
						 Iterator it3 = xmmaps.keySet().iterator();  
						  while (it3.hasNext()) {
							  String key = it3.next().toString();
							  if (list.get(i).get("jfxmmc").equals(key)) {//���ݵ�ǰ�������ݵ���Ŀģ��
								  c=Integer.parseInt(xmmaps.get(key));
								  break;
							  }
						  }
						
						sheet.addCell(jfxmmc);
						sheet.mergeCells(7,1+i,7,c+i); 
						
						sheet.addCell(sxsm);
						sheet.addCell(fs);
						sheet.addCell(cysj);
						sheet.addCell(lb);
						sheet.addCell(shr);
						sheet.addCell(shsj);
						sheet.addCell(shztmc);
					}else {//��ʱ����ѧ�Ų��ǵ�һ�γ���
						if (!dqmk.equals(bjmk)){//�����ǰģ���ǵ�һ�Σ�ģ�����µ���Ŀ�϶�Ҳ�ǵ�һ�Σ���Ҫȡ��ģ��ϲ���������Ŀ�ϲ�������
							sheet.addCell(xh);
							sheet.addCell(xm);
							sheet.addCell(nj);
							sheet.addCell(xymc);
							sheet.addCell(zymc);
							sheet.addCell(bjmc);
							//��7��ģ���кϲ�������ȡ
							 Iterator it1 = mkmaps.keySet().iterator();  
							  while (it1.hasNext()) {
								  String key = it1.next().toString();
								  if (list.get(i).get("xmmkmc").equals(key)) {
									  b=Integer.parseInt(mkmaps.get(key));
									  break;
								  }
								  
							  }
								sheet.addCell(xmmkmc);
								sheet.mergeCells(6,1+i,6,b+i); 
								//��8����Ŀ�кϲ�������ȡ
							 Iterator it2 = xmmaps.keySet().iterator();  
							  while (it2.hasNext()) {
								  String key = it2.next().toString();
								  if (list.get(i).get("jfxmmc").equals(key)) {
									  c=Integer.parseInt(xmmaps.get(key));
								  }
								  
							  }
								sheet.addCell(jfxmmc);
								sheet.mergeCells(7,1+i,7,c+i); 
								sheet.addCell(sxsm);
								sheet.addCell(fs);
								sheet.addCell(cysj);
								sheet.addCell(lb);
								sheet.addCell(shr);
								sheet.addCell(shsj);
								sheet.addCell(shztmc);
						}else { //�����ǰģ�鲻�ǵ�һ��,��
							if (!dqxm.equals(bjxm)) { //�����Ŀ�ǵ�һ�γ��֣���ʱҪ��ȡ��Ŀ�ϲ�����
								sheet.addCell(xh);
								sheet.addCell(xm);
								sheet.addCell(nj);
								sheet.addCell(xymc);
								sheet.addCell(zymc);
								sheet.addCell(bjmc);
								sheet.addCell(xmmkmc);
								//��8����Ŀ�кϲ�������ȡ
								 Iterator it2 = xmmaps.keySet().iterator();  
								  while (it2.hasNext()) {
									  String key = it2.next().toString();
									  if (list.get(i).get("jfxmmc").equals(key)) {
										  c=Integer.parseInt(xmmaps.get(key));
									  }
								  }
								    sheet.addCell(jfxmmc);
									sheet.mergeCells(7,1+i,7,c+i); 
									sheet.addCell(sxsm);
									sheet.addCell(fs);
									sheet.addCell(cysj);
									sheet.addCell(lb);
									sheet.addCell(shr);
									sheet.addCell(shsj);
									sheet.addCell(shztmc);
							}
						}
						
					}
					  
					  bjxh =dqxh;//���ѧ��
					  bjmk = dqmk;//���ģ��
					  bjxm =dqxm;//���ģ��
				}
			}
			wwb.write();
			wwb.close();
		}
		return file;
	}

	/**
	 * @throws Exception  
	 * @����:���ɻ���������Ϣ��excel
	 * @���ߣ�CP[���ţ�1352]
	 * @���ڣ�2017-3-16 ����02:55:33
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param resultList
	 * @param model
	 * @param user
	 * @return
	 * File �������� 
	 * @throws 
	 */
	public File getZhfHzsx(List<HashMap<String, String>> list,
			ZhfDrForm t, User user) throws Exception {
		File file = new File(System.getProperty("java.io.tmpdir"),System.currentTimeMillis()+".xls");
		if(!file.exists()){
			file.createNewFile();
		}
		if(null != list && list.size()>0){	
			WritableWorkbook wwb = Workbook.createWorkbook(file);
			WritableSheet sheet = wwb.createSheet("sheet1", 0);
			WritableFont headFont = new WritableFont(WritableFont.ARIAL, 12, WritableFont.BOLD, false,UnderlineStyle.NO_UNDERLINE);
			WritableFont bodyFont = new WritableFont(WritableFont.ARIAL, 10, WritableFont.NO_BOLD, false,UnderlineStyle.NO_UNDERLINE);
			WritableCellFormat head_cf = new WritableCellFormat(headFont);//�������ı�ͷ����
			WritableCellFormat body_cf = new WritableCellFormat(bodyFont);//�������ĵ�Ԫ������
			head_cf.setAlignment(jxl.format.Alignment.CENTRE);
			head_cf.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
			body_cf.setAlignment(jxl.format.Alignment.LEFT);
			body_cf.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
			body_cf.setWrap(true);
			head_cf.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK);
			body_cf.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK);
			//��ͷ
			sheet.setColumnView(0, 14);
			sheet.setColumnView(1, 10);
			sheet.setColumnView(2, 8);
			sheet.setColumnView(3, 14);
			sheet.setColumnView(4, 16);
			sheet.setColumnView(5, 16);
			sheet.setColumnView(6, 14);
			sheet.setColumnView(7, 30);
			sheet.setColumnView(8, 30);
			sheet.setColumnView(9, 8);
			sheet.setColumnView(10, 12);
			sheet.setColumnView(11, 12);
			sheet.setColumnView(12, 12);
			sheet.setColumnView(13, 12);
			sheet.setColumnView(14, 12);
			sheet.setColumnView(15, 10);
			sheet.setColumnView(16, 8);
			sheet.setColumnView(14, 15);
			Label label_title1 = new Label(0, 0,"ѧ��",head_cf);
			Label label_title2 = new Label(1, 0,"����",head_cf);
			Label label_title3 = new Label(2, 0,"�꼶",head_cf);
			Label label_title4 = new Label(3, 0,"ѧԺ",head_cf);
			Label label_title5 = new Label(4, 0,"רҵ",head_cf);
			Label label_title6 = new Label(5, 0,"�༶",head_cf);
			Label label_title7 = new Label(6, 0,"ģ��",head_cf);
			Label label_title8 = new Label(7, 0,"�Ʒ���Ŀ",head_cf);
			Label label_title9 = new Label(8, 0,"����",head_cf);
			Label label_title10 = new Label(9, 0,"�����",head_cf);
			Label label_title11 = new Label(10, 0,"����ʱ��",head_cf);
			Label label_title12 = new Label(11, 0,"���",head_cf);
			Label label_title13 = new Label(12, 0,"���������",head_cf);
			Label label_title14 = new Label(13, 0,"���ʱ��",head_cf);
			Label label_title15 = new Label(14, 0,"�������״̬",head_cf);
			Label label_title16 = new Label(15, 0,"ģ��÷�",head_cf);
			Label label_title17 = new Label(16, 0,"�ܷ�",head_cf);
			Label label_title18 = new Label(17, 0,"�����״̬",head_cf);
			
			
			sheet.addCell(label_title1);
			sheet.addCell(label_title2);
			sheet.addCell(label_title3);
			sheet.addCell(label_title4);
			sheet.addCell(label_title5);
			sheet.addCell(label_title6);
			sheet.addCell(label_title7);
			sheet.addCell(label_title8);
			sheet.addCell(label_title9);
			sheet.addCell(label_title10);
			sheet.addCell(label_title11);
			sheet.addCell(label_title12);
			sheet.addCell(label_title13);
			sheet.addCell(label_title14);
			sheet.addCell(label_title15);
			sheet.addCell(label_title16);
			sheet.addCell(label_title17);
			sheet.addCell(label_title18);
		
		     //ȡ�� xh :mkxmmc ��Ӧ����
		       List<HashMap<String,String>> mktslist = dao.getMktsList(t, user);
		       HashMap<String, String> mkmap = new HashMap<String, String> ();
		       for (int i = 0; i < mktslist.size(); i++) {
		    	   mkmap.put(mktslist.get(i).get("xh"), mktslist.get(i).get("mkts"));
		       }
		       //ȡ�� xh :jfxmmc ��Ӧ����
		       List<HashMap<String,String>> xmtslist = dao.getXmtsList(t, user);
		       HashMap<String, String> xmmap = new HashMap<String, String> ();
		       for (int i = 0; i < xmtslist.size(); i++) {
		    	   xmmap.put(xmtslist.get(i).get("xh"), xmtslist.get(i).get("xmts"));
		       }
		       
		       //ȡ�� xh :����ģ�����
		       List<HashMap<String,String>> mkfslist= dao.getMkfsList(t, user);
		       HashMap<String, String> mkfsmap = new HashMap<String, String> ();
		       for (int i = 0; i < mkfslist.size(); i++) {
		    	   mkfsmap.put(mkfslist.get(i).get("xh"), mkfslist.get(i).get("mkfs"));
		       }
			
		     //ȡ�� xh :�����״̬
		       List<HashMap<String,String>> zshztlist= dao.getZshztList(t, user);
		       HashMap<String, String> zshztmap = new HashMap<String, String> ();
		       for (int i = 0; i < zshztlist.size(); i++) {
		    	   zshztmap.put(zshztlist.get(i).get("xh"), zshztlist.get(i).get("shztmc"));
		       }
			
		       
		       String bjxh ="";//���ѧ�ţ�����ͬһѧ�Ŷ��ȥ�ϲ�ǰ�����ֶ�
		        String bjmk ="";//���ģ�飬����ͬһѧ�Ŷ��ȥ�ϲ�ǰ�����ֶ�
		        String bjxm = "";//�����Ŀ������ͬһѧ�Ŷ��ȥ�ϲ�ǰ�����ֶ�
		        HashMap<String, String> mkmaps = new HashMap<String, String>();
		        HashMap<String, String> xmmaps = new HashMap<String, String>();
		        HashMap<String, String> fsmaps = new HashMap<String, String>();
			for(int i = 0;i<list.size();i++){
				  //ȡ��ǰ����������label
				  Label xh = new Label(0, 1+i, list.get(i).get("xh"), body_cf);
				  Label xm = new Label(1, 1+i, list.get(i).get("xm"), body_cf);
				  Label nj = new Label(2, 1+i, list.get(i).get("nj"), body_cf);
				  Label xymc = new Label(3, 1+i, list.get(i).get("xymc"), body_cf);
				  Label zymc = new Label(4, 1+i, list.get(i).get("zymc"), body_cf);
				  Label bjmc = new Label(5, 1+i, list.get(i).get("bjmc"), body_cf);
				  Label xmmkmc = new Label(6, 1+i, list.get(i).get("xmmkmc"), body_cf);
				  Label jfxmmc = new Label(7, 1+i, list.get(i).get("jfxmmc"), body_cf);
				  Label sxsm = new Label(8, 1+i, list.get(i).get("sxsm"), body_cf);
				  Label fs = new Label(9, 1+i, list.get(i).get("fs"), body_cf);
				  Label cysj = new Label(10, 1+i, list.get(i).get("cysj"), body_cf);
				  Label lb = new Label(11, 1+i, list.get(i).get("lb"), body_cf);
				  Label shr = new Label(12, 1+i, list.get(i).get("shrxm"), body_cf);
				  Label shsj = new Label(13, 1+i, list.get(i).get("shsj"), body_cf);
				  Label shztmc = new Label(14, 1+i, list.get(i).get("shztmc"), body_cf);
				  
				String dqxh = list.get(i).get("xh");//ȡ����ǰ1����ѧ��
				String dqmk = list.get(i).get("xmmkmc");//ȡ����ǰ1����ģ��
				String dqxm = list.get(i).get("jfxmmc");//ȡ����ǰ1����ģ��
				if (!bjxh.equals(dqxh)) {
					 mkmaps = new HashMap<String, String>();
				      xmmaps = new HashMap<String, String>();
				      fsmaps = new HashMap<String, String>();
				       bjxh ="";//���ѧ�ţ�����ͬһѧ�Ŷ��ȥ�ϲ�ǰ�����ֶ�
				       bjmk ="";//���ģ��
				       bjxm = "";
				}
				if (dqxh.equals(bjxh)&&dqmk.equals(bjmk)&&dqxm.equals(bjxm)) {//�����ǰѧ�ŵ�ǰģ�鵱ǰ��Ŀ�����ǵ�һ�ν���ѭ����ֱ�Ӳ���
				 	sheet.addCell(xh);
					sheet.addCell(xm);
					sheet.addCell(nj);
					sheet.addCell(xymc);
					sheet.addCell(zymc);
					sheet.addCell(bjmc);
					sheet.addCell(xmmkmc);
					sheet.addCell(jfxmmc);
					sheet.addCell(sxsm);
					sheet.addCell(fs);
					sheet.addCell(cysj);
					sheet.addCell(lb);
					sheet.addCell(shr);
					sheet.addCell(shsj);
					sheet.addCell(shztmc);
					  bjxh =dqxh;//���ѧ��
					  bjmk = dqmk;//���ģ��
					  bjxm =dqxm;//���ģ��
				}else {
					 int a =0;//aΪģ������ҲΪxh���� ����¼ǰ6�е� �����ۼ�
					 int b =0; //��¼��7�� ģ���е��ۼ�
					 int c =0;//��¼��8�� ��Ŀ�е��ۼ�
					 double d =0;//�ܷ���
					 float e =0;//ģ�����
					if (!dqxh.equals(bjxh)) { //��ʱ����ǰѧ�ŵ�һ�γ��֣���ǰģ�顢��Ŀ�϶�Ҳ�ǵ�һ�γ��֣���ʱ��Ҫ�ϲ�ǰ6�л�����Ϣ��ģ���У���Ŀ��
						//����ģ��map��1��ȡѧ��Ҫ�ϲ���������2��ȡ�� {xmmkmc: ����}��mkmaps
						Iterator it = mkmap.keySet().iterator();  
						while (it.hasNext()) { 
							String key = it.next().toString();
							if (dqxh.equals(key)) {//��ǰxh��map���ҵ���Ӧģ��
								String []mkxx = mkmap.get(key).split(";");
								if (mkxx.length==1) {//ֻ��һ��ģ�飬ȡ��������   ˼������&3
									a = Integer.parseInt(mkxx[0].substring(mkxx[0].indexOf("&")+1,mkxx[0].length()));
									mkmaps.put(mkxx[0].substring(0,4),mkxx[0].substring(mkxx[0].lastIndexOf("&")+1,mkxx[0].length()));
								}else {//���ģ��  ��������&1;��������&1;˼������&3 
									for (int j = 0; j < mkxx.length; j++) {
										a += Integer.parseInt(mkxx[j].substring(mkxx[j].lastIndexOf("&")+1,mkxx[j].length()));
										mkmaps.put(mkxx[j].substring(0,4), mkxx[j].substring(mkxx[j].lastIndexOf("&")+1,mkxx[j].length()));
									}
								}
								break;
							}
						}
						//������Ŀmap ȡ�� {jfxmmc: ����}��xmmaps
						Iterator it1 = xmmap.keySet().iterator();  
						while (it1.hasNext()) { 
							String key = it1.next().toString();
							if (dqxh.equals(key)) {//��ǰxh��map���ҵ���Ӧģ��
								String []xmxx = xmmap.get(key).split(";");
									for (int j = 0; j < xmxx.length; j++) {
										xmmaps.put(xmxx[j].substring(0,xmxx[j].lastIndexOf("&")), xmxx[j].substring(xmxx[j].length()-1));
									}
								break;
							}
						}
						Iterator mkf = mkfsmap.keySet().iterator();  
						while (mkf.hasNext()) { 
							String key = mkf.next().toString();
							if (dqxh.equals(key)) {//��ǰxh��map���ҵ���Ӧģ��
								String []mkxx = mkfsmap.get(key).split(";");
								if (mkxx.length==1) {//ֻ��һ��ģ�飬˼������$0.4 
									d=Double.parseDouble(mkxx[0].substring(mkxx[0].lastIndexOf("$")+1,mkxx[0].length()));//�ܷ�
									fsmaps.put(mkxx[0].substring(0,4),mkxx[0].substring(mkxx[0].lastIndexOf("$")+1,mkxx[0].length()));
								}else {//���ģ��  ��������&0.1;��������&0.11;˼������&3 substring(5,)
									for (int j = 0; j < mkxx.length; j++) {
										double k = Double.parseDouble(mkxx[j].substring(mkxx[j].lastIndexOf("$")+1,mkxx[j].length()));
										BigDecimal b1=new BigDecimal(Double.toString(d));  
										 BigDecimal b2=new BigDecimal(Double.toString(k)); 
										 d=b1.add(b2).doubleValue();  
										fsmaps.put(mkxx[j].substring(0,4), mkxx[j].substring(mkxx[j].lastIndexOf("$")+1,mkxx[j].length()));
									}
									
								}
								break;
							}
						}
						//ȡ��a��ǰ6�кϲ�
					  	sheet.addCell(xh);
						sheet.addCell(xm);
						sheet.addCell(nj);
						sheet.addCell(xymc);
						sheet.addCell(zymc);
						sheet.addCell(bjmc);
						sheet.mergeCells(0,1+i,0,a+i);
						sheet.mergeCells(1,1+i,1,a+i);
						sheet.mergeCells(2,1+i,2,a+i);
						sheet.mergeCells(3,1+i,3,a+i);
						sheet.mergeCells(4,1+i,4,a+i);
						sheet.mergeCells(5,1+i,5,a+i);
						//��7��ģ���кϲ�������ȡ
						 Iterator it2 = mkmaps.keySet().iterator();  
						  while (it2.hasNext()) {
							  String key = it2.next().toString();
							  if (list.get(i).get("xmmkmc").equals(key)) {//���ݵ�ǰ�������ݵ���Ŀģ��
								  b=Integer.parseInt(mkmaps.get(key));
								  break;
							  }
						  }
						sheet.addCell(xmmkmc);
						sheet.mergeCells(6,1+i,6,b+i);
						/**
						 * mergeCells�ϲ���Ԫ��
						 * ��һ��������Ҫ�ϲ��ĵ�Ԫ�������Ͻǵ��к�
						 * �ڶ���������Ҫ�ϲ��ĵ�Ԫ�������Ͻǵ��к�
						 * ������������Ҫ�ϲ��ĵ�Ԫ�����ҽǵ��к�
						 * ���ĸ�������Ҫ�ϲ��ĵ�Ԫ�������½ǵ��к�
						 */
						//��8����Ŀ�кϲ�������ȡ
						 Iterator it3 = xmmaps.keySet().iterator();  
						  while (it3.hasNext()) {
							  String key = it3.next().toString();
							  if (list.get(i).get("jfxmmc").equals(key)) {//���ݵ�ǰ�������ݵ���Ŀģ��
								  c=Integer.parseInt(xmmaps.get(key));
								  break;
							  }
						  }
						
						sheet.addCell(jfxmmc);
						sheet.mergeCells(7,1+i,7,c+i); 
						
						sheet.addCell(sxsm);
						sheet.addCell(fs);
						sheet.addCell(cysj);
						sheet.addCell(lb);
						sheet.addCell(shr);
						sheet.addCell(shsj);
						sheet.addCell(shztmc);
						
						 Iterator it4 = fsmaps.keySet().iterator();  
						  while (it4.hasNext()) {
							  String key = it4.next().toString();
							  if (list.get(i).get("xmmkmc").equals(key)) {//���ݵ�ǰ�������ݵ���Ŀģ��
								  e=Float.parseFloat(fsmaps.get(key));
								  break;
							  }
						  }
						  Label mkfs = new Label(15, 1+i, String.valueOf(e), body_cf);
						  Label zf = new Label(16, 1+i, String.valueOf(d), body_cf);
						  
						sheet.addCell(mkfs);//ģ��÷�
						sheet.mergeCells(15,1+i,15,b+i); 
						sheet.addCell(zf);//�÷�
						sheet.mergeCells(16,1+i,16,a+i);
						
						Iterator it5 = zshztmap.keySet().iterator();  
						  while (it5.hasNext()) {
							  String key = it5.next().toString();
							  if (list.get(i).get("xh").equals(key)) {//���ݵ�ǰ�������ݵ���Ŀģ��
								 String zt =zshztmap.get(key);
								  Label zshzt = new Label(17, 1+i, zt, body_cf);
								  sheet.addCell(zshzt);//�÷�
									sheet.mergeCells(17,1+i,17,a+i);
								  break;
							  }
						  }
					}else {//��ʱ����ѧ�Ų��ǵ�һ�γ���
						if (!dqmk.equals(bjmk)){//�����ǰģ���ǵ�һ�Σ�ģ�����µ���Ŀ�϶�Ҳ�ǵ�һ�Σ���Ҫȡ��ģ��ϲ���������Ŀ�ϲ�������
							sheet.addCell(xh);
							sheet.addCell(xm);
							sheet.addCell(nj);
							sheet.addCell(xymc);
							sheet.addCell(zymc);
							sheet.addCell(bjmc);
							//��7��ģ���кϲ�������ȡ
							 Iterator it1 = mkmaps.keySet().iterator();  
							  while (it1.hasNext()) {
								  String key = it1.next().toString();
								  if (list.get(i).get("xmmkmc").equals(key)) {
									  b=Integer.parseInt(mkmaps.get(key));
									  break;
								  }
								  
							  }
								sheet.addCell(xmmkmc);
								sheet.mergeCells(6,1+i,6,b+i); 
								//��8����Ŀ�кϲ�������ȡ
							 Iterator it2 = xmmaps.keySet().iterator();  
							  while (it2.hasNext()) {
								  String key = it2.next().toString();
								  if (list.get(i).get("jfxmmc").equals(key)) {
									  c=Integer.parseInt(xmmaps.get(key));
								  }
								  
							  }
								sheet.addCell(jfxmmc);
								sheet.mergeCells(7,1+i,7,c+i); 
								sheet.addCell(sxsm);
								sheet.addCell(fs);
								sheet.addCell(cysj);
								sheet.addCell(lb);
								sheet.addCell(shr);
								sheet.addCell(shsj);
								sheet.addCell(shztmc);
								Iterator it4 = fsmaps.keySet().iterator();  
								  while (it4.hasNext()) {
									  String key = it4.next().toString();
									  if (list.get(i).get("xmmkmc").equals(key)) {//���ݵ�ǰ�������ݵ���Ŀģ��
										  e=Float.parseFloat(fsmaps.get(key));
										  break;
									  }
								  }
								  Label mkfs = new Label(15, 1+i, String.valueOf(e), body_cf);
								  
								sheet.addCell(mkfs);//ģ��÷�
								sheet.mergeCells(15,1+i,15,b+i); 
								
						}else { //�����ǰģ�鲻�ǵ�һ��,��
							if (!dqxm.equals(bjxm)) { //�����Ŀ�ǵ�һ�γ��֣���ʱҪ��ȡ��Ŀ�ϲ�����
								sheet.addCell(xh);
								sheet.addCell(xm);
								sheet.addCell(nj);
								sheet.addCell(xymc);
								sheet.addCell(zymc);
								sheet.addCell(bjmc);
								sheet.addCell(xmmkmc);
								//��8����Ŀ�кϲ�������ȡ
								 Iterator it2 = xmmaps.keySet().iterator();  
								  while (it2.hasNext()) {
									  String key = it2.next().toString();
									  if (list.get(i).get("jfxmmc").equals(key)) {
										  c=Integer.parseInt(xmmaps.get(key));
									  }
									  
								  }
								    sheet.addCell(jfxmmc);
									sheet.mergeCells(7,1+i,7,c+i); 
									sheet.addCell(sxsm);
									sheet.addCell(fs);
									sheet.addCell(cysj);
									sheet.addCell(lb);
									sheet.addCell(shr);
									sheet.addCell(shsj);
									sheet.addCell(shztmc);
							}
						}
					}
					  bjxh =dqxh;//���ѧ��
					  bjmk = dqmk;//���ģ��
					  bjxm =dqxm;//���ģ��
				}
			}
			wwb.write();
			wwb.close();
		}
		return file;
	}
}
