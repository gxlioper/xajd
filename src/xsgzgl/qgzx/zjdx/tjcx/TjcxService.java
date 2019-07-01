/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-12-26 ����09:42:00 
 */  
package xsgzgl.qgzx.zjdx.tjcx;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import jxl.Workbook;
import jxl.format.UnderlineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� yxy[����:1206]
 * @ʱ�䣺 2016-12-26 ����09:42:00 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class TjcxService extends SuperServiceImpl<TjcxForm, TjcxDAO> {
	/**
	 * @throws Exception 
	 * 
	 * @����: У�����귢��ͳ�Ʋ�ѯ
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-12-26 ����09:54:44
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getXqbcFfTj(TjcxForm t) throws Exception{
		return dao.getXqbcFfTj(t);
	}
	
	/**
	 * 
	 * @����:���˵�λ����ͳ�Ʋ�ѯ��������չ����
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-12-26 ����04:44:31
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getYrdwFfTj(TjcxForm t) throws Exception{
		return dao.getYrdwFfTj(t);
	}
	
	/**
	 * 
	 * @����: ���˵�λ���һ�кϼ�
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-12-27 ����11:36:19
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public HashMap<String, String> getYrdwFfTjSum(TjcxForm t)throws Exception{
		return dao.getYrdwFfTjSum(t);
	}
	
	/**
	 * 
	 * @����: У������
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-12-28 ����10:26:24
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param os
	 * @throws Exception
	 * void �������� 
	 * @throws
	 */
	public  void createWwbXqDc(OutputStream os,TjcxForm model,List<HashMap<String, String>> dataList) throws Exception{
		WritableWorkbook wwb = Workbook.createWorkbook(os);
		WritableCellFormat titlefont =  this.getFontStyle("title");
		WritableCellFormat titlethead =  this.getFontStyle("titlethead");
		WritableCellFormat titlebody =  this.getFontStyle("titlebody");
		/**
		 * sheet1
		 */
		WritableSheet ws = wwb.createSheet("У������", 0);
		String titlename = "";
		if(StringUtils.isNotNull(model.getYf())){
			titlename = model.getNd()+"���"+model.getYf()+"��"+"��У�����귢��ͳ�Ƶ���";
		}else{
			titlename = model.getNd()+"���"+"��У�����귢��ͳ�Ƶ���";
		}
		Label TitleLabel = new Label(0, 0, titlename, titlefont);
		ws.addCell(TitleLabel);
		ws.mergeCells(0, 0, 2, 0);
		
		
	    Label row2col1= new Label(0, 1, "У��",titlethead);
	    Label row2col2= new Label(1, 1, "�ڹ���ѧ�˴�",titlethead);
	    Label row2col3= new Label(2, 1, "����ܼ�(Ԫ)",titlethead);
		 
		ws.addCell(row2col1);
		ws.addCell(row2col2);
		ws.addCell(row2col3);
		if(!dataList.isEmpty()){
			for (int i = 0; i < dataList.size(); i++) {
				Label temp1= new Label(0, 2+i, dataList.get(i).get("xqmc"),titlebody);
			    Label temp2= new Label(1, 2+i, dataList.get(i).get("rc"),titlebody);
			    Label temp3= new Label(2, 2+i, dataList.get(i).get("bcje"),titlebody);
			    ws.addCell(temp1);
				ws.addCell(temp2);
				ws.addCell(temp3);
			}
			int num = dataList.size();
			HashMap<String, String> dataMap = dao.getXqbcFfTjSum(model);
			Label temphj1= new Label(0, 2+num, "�ܼ�",titlebody);
		    Label temphj2= new Label(1, 2+num, dataMap.get("rc"),titlebody);
		    Label temphj3= new Label(2, 2+num, dataMap.get("bcje"),titlebody);
		    ws.addCell(temphj1);
			ws.addCell(temphj2);
			ws.addCell(temphj3);
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
			  WritableFont wf_table = new WritableFont(WritableFont.ARIAL, 14,  
		                WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE,  
		                jxl.format.Colour.BLACK);   
			  WritableCellFormat wcf_table = new WritableCellFormat(wf_table);   
//			  wcf_table.setShrinkToFit(true);
	          wcf_table.setAlignment(jxl.format.Alignment.CENTRE);
	          wcf_table.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
	          return wcf_table;
		}else if("titlethead".equals(paras)){
			WritableFont wf_table = new WritableFont(WritableFont.ARIAL, 12,  
	                WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE,  
	                jxl.format.Colour.BLACK);   
		  WritableCellFormat wcf_table = new WritableCellFormat(wf_table);   
//		  wcf_table.setShrinkToFit(true);
          wcf_table.setAlignment(jxl.format.Alignment.CENTRE);
          wcf_table.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
          return wcf_table;
		}else {
			WritableFont wf_table = new WritableFont(WritableFont.ARIAL, 12,  
	                WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE,  
	                jxl.format.Colour.BLACK);   
		  WritableCellFormat wcf_table = new WritableCellFormat(wf_table);   
//		  wcf_table.setShrinkToFit(true);
          wcf_table.setAlignment(jxl.format.Alignment.CENTRE);
          wcf_table.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
          return wcf_table;
		}
     
	}
    
	/**
	 * 
	 * @����: У��ͳ�����һ�кϼ�
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-12-28 ����11:17:22
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public HashMap<String, String> getXqbcFfTjSum(TjcxForm t){
		return dao.getXqbcFfTjSum(t);
	}
	
	/**
	 * 
	 * @����: ��ȡ���˲���List����
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-12-28 ����01:40:47
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
//	 */
	public List<HashMap<String, String>> getYrbmList(String bmlb){
		return dao.getYrbmList(bmlb);
	}
	
	/**
	 * 
	 * @����: У������
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-12-28 ����10:26:24
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param os
	 * @throws Exception
	 * void �������� 
	 * @throws
	 */
	public  void createWwbYrdwDc(OutputStream os,TjcxForm model,List<HashMap<String, String>> dataList) throws Exception{
		WritableWorkbook wwb = Workbook.createWorkbook(os);
		WritableCellFormat titlefont =  this.getFontStyle("title");
		WritableCellFormat titlethead =  this.getFontStyle("titlethead");
		WritableCellFormat titlebody =  this.getFontStyle("titlebody");
		
		HashMap<String, String> yfDzMap = new HashMap<String, String>();
		/**
		 * �·ݶ�Ӧ
		 */
		yfDzMap.put("01", "jan");
		yfDzMap.put("02", "feb");
		yfDzMap.put("03", "march");
		yfDzMap.put("04", "apr");
		yfDzMap.put("05", "may");
		yfDzMap.put("06", "jun");
		yfDzMap.put("07", "jul");
		yfDzMap.put("08", "aug");
		yfDzMap.put("09", "sep");
		yfDzMap.put("10", "oct");
		yfDzMap.put("11", "nov");
		yfDzMap.put("12", "decm");
		WritableSheet ws = wwb.createSheet("���˵�λ����", 0);
		String titlename = "";
		if(StringUtils.isNotNull(model.getYf())){
			titlename = model.getNd()+"���"+model.getYf()+"��"+"���˵�λ���귢��ͳ�Ƶ���";
		}else{
			titlename = model.getNd()+"���"+"���˵�λ���귢��ͳ�Ƶ���";
		}
		Label TitleLabel = new Label(0, 0, titlename, titlefont);
		ws.addCell(TitleLabel);
		if(StringUtils.isNotNull(model.getYf())){
			ws.mergeCells(0, 0, 2, 0);
		}else{
			ws.mergeCells(0, 0, 26, 0);
		}
		
		
		
	    Label row2col1= new Label(0, 1, "���˵�λ",titlethead);
	    ws.addCell(row2col1);
		ws.mergeCells(0, 1, 0, 2);
		if(StringUtils.isNotNull(model.getYf())){
			Label row2col2 = new Label(1, 1, model.getYf().replace("0", "")+"��", titlethead);
			ws.addCell(row2col2);
			ws.mergeCells(1, 1, 2, 1);
			Label row3col2 = new Label(1, 2, "�˴�", titlethead);
			ws.addCell(row3col2);
			Label row3col3 = new Label(2, 2, "���", titlethead);
			ws.addCell(row3col3);
		}else{
			for (int i = 1; i <= 24; i++) {
				if(i <= 12){
					Label tempyf = new Label(1+(i-1)*2, 1, i+"��", titlethead);
					ws.addCell(tempyf);
					ws.mergeCells(1+(i-1)*2, 1, 1+(i-1)*2+1, 1);
				}
				if(i%2 == 0){
					Label temprc = new Label(i, 2, "���", titlethead);
					ws.addCell(temprc);
				}else{
					Label temprc = new Label(i, 2, "�˴�", titlethead);
					ws.addCell(temprc);
				}
				
			}
			Label hj = new Label(25, 1, "�ϼ�", titlethead);
			ws.addCell(hj);
			ws.mergeCells(25, 1, 26, 1);
			Label hjrc = new Label(25, 2, "�˴�", titlethead);
			ws.addCell(hjrc);
			Label hjje = new Label(26, 2, "���", titlethead);
			ws.addCell(hjje);
		}
		 
		
		if(!dataList.isEmpty()){
			HashMap<String, String> dataMap = dao.getYrdwFfTjSum(model);
			if(StringUtils.isNull(model.getYf())){
				for (int i = 0; i < dataList.size(); i++) {
					Label labelbmmc = new Label(0, 3+i, dataList.get(i).get("bmmc"),titlebody);
					ws.addCell(labelbmmc);
					String key = "";
					for(int j=1;j<=24;j++){
						if(j%2 == 1){
							String jstr = (j+1)/2 < 10 ? "0"+(j+1)/2 : (j+1)/2+"";
							 key = yfDzMap.get(jstr);
						}else{
							String jstr = (j)/2 < 10 ? "0"+(j)/2 : (j)/2+"";
							 key = yfDzMap.get(jstr);
						}
						if(j%2 == 0){
							Label temp2= new Label(j, 3+i, dataList.get(i).get(key+"je"),titlebody);
							ws.addCell(temp2);
						}else{
							Label temp1= new Label(j, 3+i, dataList.get(i).get(key+"rc"),titlebody);
							ws.addCell(temp1);
						}
						
						
					}
					Label hjrc = new Label(25, 3+i, dataList.get(i).get("rowrc"),titlebody);
					Label hjje = new Label(26, 3+i, dataList.get(i).get("rowje"),titlebody);
					ws.addCell(hjrc);
					ws.addCell(hjje);
				}
				int num = dataList.size();
				Label labelhjmc = new Label(0, 3+num, "�ϼ�",titlebody);
				ws.addCell(labelhjmc);
				String key = "";
				for(int j=1;j<=24;j++){
					if(j%2 == 1){
						String jstr = (j+1)/2 < 10 ? "0"+(j+1)/2 : (j+1)/2+"";
						 key = yfDzMap.get(jstr);
					}else{
						String jstr = (j)/2 < 10 ? "0"+(j)/2 : (j)/2+"";
						 key = yfDzMap.get(jstr);
					}
					
					if(j%2 == 0){
						Label temp2= new Label(j, 3+num, dataMap.get(key+"je"),titlebody);
						ws.addCell(temp2);
					}else{
						Label temp1= new Label(j, 3+num, dataMap.get(key+"rc"),titlebody);
						ws.addCell(temp1);
					}
					
					
				}
				Label hjrc = new Label(25, 3+num, dataMap.get("rowrc"),titlebody);
				Label hjje = new Label(26, 3+num, dataMap.get("rowje"),titlebody);
				ws.addCell(hjrc);
				ws.addCell(hjje);
			}else{
				String key = yfDzMap.get(model.getYf());
				for (int i = 0; i < dataList.size(); i++) {
					Label labelbmmc = new Label(0, 3+i, dataList.get(i).get("bmmc"),titlebody);
					ws.addCell(labelbmmc);
					Label rcLabel = new Label(1, 3+i, dataList.get(i).get(key+"rc"), titlebody);
					Label jeLabel = new Label(2, 3+i, dataList.get(i).get(key+"je"), titlebody);
					ws.addCell(rcLabel);
					ws.addCell(jeLabel);
				}
				int num = dataList.size();
				Label labelhjmc = new Label(0, 3+num, "�ϼ�",titlebody);
				ws.addCell(labelhjmc);
				Label hjrc = new Label(1, 3+num, dataMap.get(key+"rc"), titlebody);
				Label hjje = new Label(2, 3+num, dataMap.get(key+"je"), titlebody);
				ws.addCell(hjrc);
				ws.addCell(hjje);
			}
			
		}
		
	  
	    try{
			 wwb.write();
			 wwb.close();
		 }catch(Exception e){
				
		 }
	}
	
	/**
	 * @throws Exception 
	 * @����: ѧ���ڹ���ϸͳ��
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-12-29 ����10:50:00
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param user
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getStudentQgDetailTjCx(TjcxForm t,User user) throws Exception{
	    return dao.getStudentQgDetailTjCx(t, user);
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @����: ѧ�������ڹ���ϸ�ϼ�
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-12-29 ����11:27:43
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String> getStudentQgDetailTjCxSum(TjcxForm t,User user) throws Exception{
		return dao.getStudentQgDetailTjCxSum(t, user);
	}
	
	/**
	 * @����: ���˱��귢��ͳ�Ʋ�ѯҳ��
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2017-1-18 ����11:02:03
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getStucjffgrtj(TjcxForm t,User user) throws Exception{
	    return dao.getStucjffgrtj(t, user);
	}
	
	/**
	 * @����: ���˱��귢��ͳ�Ƹ��ݸ߼���ѯ�������㱨�귢������
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2017-1-18 ����11:08:04
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward �������� 
	 * @throws
	 */
	public HashMap<String, String> getStucjffgrtjSum(TjcxForm t,User user) throws Exception{
		return dao.getStucjffgrtjSum(t, user);
	}
	
	/**
	 * @����: ���˱��귢��ͳ�Ƶ���
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2017-1-18 ����11:23:22
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> exportDataStucjffgrtj(TjcxForm t,User user) throws Exception{
	    return dao.exportDataStucjffgrtj(t, user);
	}
}
