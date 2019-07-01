/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-8-1 ����08:58:13 
 */  
package com.zfsoft.xgxt.xsztz.jdwhsz;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
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

import xgxt.comm.search.SearchService;
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
 * @ʱ�䣺 2016-8-1 ����08:58:13 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class JdwhSzService extends SuperServiceImpl<JdwhSzForm, JdwhSzDao> {
	//ʱ���ʽ
	private static final String DATE_FORMAT = "yyyy-MM-dd hh24:mi:ss";
	/**
	 * @throws Exception 
	 * 
	 * @����:��ȡ������Ŀ��ά����׶�ά���������
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-8-1 ����01:52:12
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param jdid
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getGrxmYwh(JdwhSzForm t, User user) throws Exception{
	    return dao.getGrxmYwh(t, user);
	}
	
	/**
	 * 
	 * @����: ��Ŀ�׶�ά����Ϣ
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-8-1 ����04:06:37
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param jdid
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String> getXmJdwhxx(String jdid){
		return dao.getXmJdwhxx(jdid);
	}
	
	/**
	 * 
	 * @����:������Ŀ�׶�ά��ѡ���Աҳ��Ĳ�ѯ
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-8-1 ����03:00:13
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getGrxmcyList(JdwhSzForm t, User user)
			throws Exception {
		return dao.getGrxmcyList(t, user);
	}
	
	/**
	 * 
	 * @����:��ȡѧ��ƴ���ַ���
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-8-1 ����04:57:07
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param jdid
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getXhs(String jdid){
		return dao.getXhs(jdid);
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @����:������ӽ׶����ó�Ա
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-8-2 ����09:50:20
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param jdwhszform
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean saveJdszCy(JdwhSzForm jdwhszform) throws Exception{
		String[] jdcys = jdwhszform.getJdcys();
		String xmdm = jdwhszform.getXmdm();
		String jdid = jdwhszform.getJdid();
		String jbf = jdwhszform.getJbf();
		String jdsj = GetTime.getTimeByFormat(DATE_FORMAT);
		List<String[]> param = new ArrayList<String[]>();
		for (int i = 0; i < jdcys.length; i++) {
			param.add(new String[]{xmdm,jdid,jdcys[i],jbf,jdsj});
		}
		return dao.saveJdszCy(param);
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @����:��ȡ������Ŀ��ά����׶�ά���������
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-8-1 ����01:52:12
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param jdid
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getTtxmYwh(JdwhSzForm t, User user) throws Exception{
	    return dao.getTtxmYwh(t, user);
	}
	
	/**
	 * 
	 * @����:������Ŀ�׶�ά��ѡ���Աҳ��Ĳ�ѯ
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-8-1 ����11:35:59
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getTtxmcyList(JdwhSzForm t, User user)
	throws Exception {
		// TODO �Զ����ɷ������
		return dao.getTtxmcyList(t, user);
	}
	
	/**
	 * 
	 * @����:��ȡѧ��ƴ���ַ���
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-8-1 ����04:57:07
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param jdid
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getDzXhs(String jdid){
		return dao.getDzXhs(jdid);
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @����: ��ȡ��Ŀ����list����
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-8-2 ����03:01:34
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getXmmcList(User user) throws Exception{
		return dao.getXmmcList(user);
	}
	
	/**
	 * 
	 * @����: ��ȡ��Ŀ����MAP����
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-8-2 ����03:01:34
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public HashMap<String,String> getXmmcMap(String xmdm){
		return dao.getXmmcMap(xmdm);
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
	public HashMap<String, Object> saveDrExcelInfo(InputStream is,JdwhSzForm zhfdrform){
		HashMap<String, Object> resultMap= null;
		try {
			 resultMap= this.DrExcelInfoCheck(is, zhfdrform);
			//�ж�excel����Ƿ�Ϊ��
			if("null".equals(resultMap.get("result")) || "excelrepeat".equals(resultMap.get("result"))){
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
	public HashMap<String, Object> DrExcelInfoCheck(InputStream is,JdwhSzForm zhfdrform){
		//�����¼����
		List<String[]> drlist = new ArrayList<String[]>();
		//�����¼����
		List<String[]> errorlist = new ArrayList<String[]>() ;
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("result", "true");
	    String jdsj = GetTime.getTimeByFormat(DATE_FORMAT);
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
	        	//��
	        	  for (int i = 1; i < rows; i++) {
	  	        	
	  	        		//ȡ��ÿ����֤���ݣ�����lsmap
	  	        		HashMap<String, String>  lsmap = new HashMap<String, String>();
	  	        		String jdmc = rs.getCell(0, i).getContents();
	  	        		String tdmc = rs.getCell(1, i).getContents();
	  	        		String jbf = rs.getCell(2, i).getContents();
	  	        		String hdsc = rs.getCell(3, i).getContents();
	  	        		String bz = rs.getCell(4, i).getContents();
	  	        	    if(StringUtils.isNull(jdmc.trim()) && StringUtils.isNull(tdmc.trim()) && 
	  	        	    		StringUtils.isNull(jbf.trim()) && StringUtils.isNull(hdsc.trim()) && 
	  	        	    		StringUtils.isNull(bz.trim())){
	  	        	    	continue;
	  	        	    }
	  	        		lsmap.put("jdmc", jdmc.trim());
	  	        		lsmap.put("tdmc", tdmc.trim());
	  	        		lsmap.put("jbf", jbf.trim());
	  	        		lsmap.put("hdsc", hdsc.trim());
	  	        		lsmap.put("bz", bz.trim());
	  	        		HashMap<String,Object> resultmap = this.checkEveryRowRecord(lsmap, zhfdrform);
	  	        		ArrayList<String> paralist = new ArrayList<String>();
	  	        		if("false".equals(resultmap.get("result"))){
	  	        			paralist.add(lsmap.get("jdmc"));
	  	        			paralist.add(lsmap.get("tdmc"));
	  	        			paralist.add(lsmap.get("jbf"));
	  	        			paralist.add(lsmap.get("hdsc"));
	  	        			paralist.add(lsmap.get("bz"));
	  	        			if(resultmap.get("resultmap") != null ){
	  	        				Map<String, String> map = (HashMap<String, String>) resultmap.get("resultmap");
	  	        				for (Map.Entry<String, String> entry : map.entrySet()){
	  	        					if(!entry.getKey().equals("jdmc") && !entry.getKey().equals("tdmc")
	  	        					&&	!entry.getKey().equals("jbf") && !entry.getKey().equals("hdsc") && !entry.getKey().equals("bz")	
	  	        					&& !entry.getKey().equals("jdf") && !entry.getKey().equals("jdid") && !entry.getKey().equals("jdcy")){
	  	        						paralist.add(entry.getValue());
	  	        					}
	  	        					
	  	        				}
	  	        			}
	  	        			errorlist.add(paralist.toArray(new String[]{}));
	  	        			resultMap.put("result", "false");
	  	        		}else{
	  	        			paralist.add(zhfdrform.getXmdm());
	  	        			paralist.add(lsmap.get("jdid"));
	  	        			paralist.add(lsmap.get("jdcy"));
	  	        			paralist.add(lsmap.get("jbf"));
	  	        			paralist.add(lsmap.get("hdsc"));
	  	        			paralist.add(lsmap.get("bz"));
	  	        			paralist.add(jdsj);
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
	 * 
	 * @����:ѭ����֤ÿ�еļ�¼
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-6-22 ����01:43:39
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * HashMap<String,Object> �������� 
	 * @throws
	 */
	public HashMap<String, Object> checkEveryRowRecord(HashMap<String, String> lsmap,JdwhSzForm zhfdrform){
		/**
		 * ��֤����
		 * 1.xh,xm������֤�Ƿ����xsxxb
		 * 2.xh,sxsm,cysj,jfxmdm������֤
		 * 3.�����¼��ʦ�����ݷ�Χ��֤
		 * 4.����˵�����ܳ���25���ַ�
		 * 5.cysj ��֤10λ��ʽ ������2015-08-08
		 * ��֤��ʽ��ȡ��������ȫ����֤�꣬������������lsmap��������뷵��map�����з���
		 * �����һ����֤�������㣬���뷵�ؽ��mapΪ�ַ���"false",���ȫ����ȷ��Ϊ�ַ���"true"
		 */
		String message = "true";
		List<HashMap<String, String>> jdyzlist = dao.getJdList(zhfdrform.getXmdm());
		HashMap<String, String> xmmcmap = dao.getXmmcMap(zhfdrform.getXmdm());
		String csms = xmmcmap.get("csms");
		HashMap<String, String> resultmap = new HashMap<String, String>();
		//��ȡ���������Ƿ��ڽ������
		if("1".equals(csms)){
			 resultmap = dao.checkGrIsExists(zhfdrform.getXmdm(),lsmap.get("tdmc"));
		}else{
			 resultmap = dao.checkTtIsExists(zhfdrform.getXmdm(),lsmap.get("tdmc"));
		}
		for (HashMap<String, String> hashMap : jdyzlist) {
			message = "false";
			if(lsmap.get("jdmc").equals(hashMap.get("jdmc"))){
				lsmap.put("jdid", hashMap.get("jdid"));
				lsmap.put("jdf", hashMap.get("jdf"));
				message = "true";
				break;
			}
		}
		if("false".equals(message)){
			lsmap.put("jdmcyz", "�׶����Ʋ����ڣ�");
		}
		
		//Ϊ����֤
		if(StringUtils.isNull(lsmap.get("jdmc")) || StringUtils.isNull(lsmap.get("tdmc")) || StringUtils.isNull(lsmap.get("jbf"))
				){
				message = "false";
				lsmap.put("kyz", "�׶����ƣ��Ŷ�����/��Աѧ��,�׶ηֲ���Ϊ�գ�");
		}else{
			//��֤����ĳ�Ա�Ƿ����
			
			if("false".equals(resultmap.get("flag"))){
				message = "false";
				lsmap.put("jdcyczyz", "���Ŷ�����/��Աѧ�Ų������ڽ�����У�");
			
			}else{
				lsmap.put("jdcy", resultmap.get("jdcy"));
			}
			
			
			if(StringUtils.isNull(lsmap.get("jdmcyz")) && StringUtils.isNull(lsmap.get("jdcyczyz"))){
				//jdcy,xmdm,jdid������֤
				if(!dao.checkZjIsRepeat(zhfdrform.getXmdm(),lsmap.get("jdid"), lsmap.get("jdcy"))){
					message = "false";
					lsmap.put("zjyz", "���׶����ƺ��Ŷ�����/��Աѧ�ţ�Ϊ���������𣬲����ظ���");
				}
				
			}
		
				
			
			//����Ľ׶η���֤
			if(StringUtils.isNotNull(lsmap.get("jbf"))){
				try {
					float num = Float.parseFloat(lsmap.get("jbf"));
					if(Float.isNaN(num)){
						message = "false";
						lsmap.put("jbfNaN", "�׶η�ֻ���������֣�");
					}else{
						if(StringUtils.isNotNull(lsmap.get("jdf")) && num > Float.parseFloat(lsmap.get("jdf"))){
							message = "false";
							lsmap.put("jbfsxyz", "�ý׶εĽ׶η�����Ϊ"+lsmap.get("jdf")+"��");
						}
					}
				} catch (NumberFormatException e) {
					// TODO �Զ����� catch ��
					message = "false";
					lsmap.put("jbfNaN", "�׶η�ֻ���������֣�");
					e.printStackTrace();
				
				}
				if(lsmap.get("jbf").length() > 3){
					message = "false";
					lsmap.put("jbflengthyz", "�׶η����𳬹�3���ַ���");
				}
				
				
			}
			//����Ļʱ���ַ�������֤
			if(StringUtils.isNotNull(lsmap.get("hdsc"))){
				if(lsmap.get("hdsc").length() > 25){
					message = "false";
					lsmap.put("hdsclengthyz", "�ʱ�����𳬹�25���ַ���");
				}
			}
			
			//����Ļʱ���ַ�������֤
			if(StringUtils.isNotNull(lsmap.get("bz"))){
				if(lsmap.get("bz").length() > 500){
					message = "false";
					lsmap.put("bzlengthyz", "��ע���𳬹�25���ַ���");
				}
			}
			
			
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
            Label labeljdmc= new Label(0, 0, "�׶�����");//��ʾ��
            Label labelName= new Label(1, 0, "�Ŷ�����/��Աѧ��");
            Label labelJdf= new Label(2, 0, "�׶η�");
            Label labelHdsc= new Label(3, 0, "�ʱ��");
            Label labelbz= new Label(4, 0, "��ע");
            
            
            try {
            	ws.addCell(labeljdmc);
				ws.addCell(labelName);
				ws.addCell(labelJdf);
	            ws.addCell(labelHdsc);
	            ws.addCell(labelbz);
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
                	 if(j<=4){
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
			String jdmc = rs.getCell(0, i).getContents();
			String tdmc = rs.getCell(1,i).getContents();
			String str = jdmc.trim() + tdmc.trim() ;
			if(StringUtils.isNull((str).trim())){
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
	 * @����:��֤��ϵ����ݲ������ݿ�
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-8-3 ����09:28:25
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
	 * @throws Exception 
	 * 
	 * @����:ѧ��������Ŀ��ѯ��ֻ��ѧ����
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-8-3 ����10:46:18
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param user
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getXsGrxmCx(JdwhSzForm t, User user) throws Exception{
		return dao.getXsGrxmCx(t, user);
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @����:ѧ��������Ŀ��ѯ��ֻ��ѧ����
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-8-3 ����10:48:19
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param user
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getXsTtxmCx(JdwhSzForm t, User user) throws Exception{
		return dao.getXsTtxmCx(t, user);
		
	}
}
