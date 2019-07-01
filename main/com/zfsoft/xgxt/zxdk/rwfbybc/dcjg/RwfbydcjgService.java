/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-9-10 ����08:46:48 
 */  
package com.zfsoft.xgxt.zxdk.rwfbybc.dcjg;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

import jxl.Sheet;
import jxl.Workbook;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableCellFeatures;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import xgxt.form.User;

public class RwfbydcjgService extends SuperServiceImpl<RwfbydcjgModel, RwfbydcjgDao> {
  
	/**
	 * 
	 * @����:��֤�Ƿ�������
	 * @���ߣ�ChenQ[���ţ�856]
	 * @���ڣ�2015-9-7 ����06:16:47
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean isExists(RwfbydcjgModel model) {	
		return dao.isExists(model);
	}
	
	/**
	 *
	 * @����:����ʦ����ѧ���۴������listѧ����Ϣ�鿴�Զ����������
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2015-12-7 ����09:14:33
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getRwdcjglist(String xh){
		return dao.getRwdcjglist(xh);
	}
	
	/**
	 * @throws SQLException 
	 * @����:�������������Ŵ����������Ϣ
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��1��13�� ����3:43:55
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ffcsList
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean saveDcjgffcs(String xh,String xn,List<DcjgffcsModel> ffcsList) throws SQLException{
		return dao.saveDcjgffsc(xh,xn,ffcsList);
	}
	
	/**
	 * @throws Exception 
	 * @����:����ѧ�š�ѧ��ļ�������ɾ������������Ŵ����������Ϣ
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��1��16�� ����10:04:18
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xhxnList
	 * @return
	 * @throws SQLException
	 * boolean �������� 
	 * @throws
	 */
	public boolean deleteDcjgffcs(List<HashMap<String,String>> xhxnList) throws Exception{
		return dao.deleteDcjgffsc(xhxnList);
	}

	/** 
	 * @����:����ѧ�š�ѧ���ѯ���������Ӧ�ķ��Ŵ�����Ϣ
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��1��16�� ����8:47:41
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String,String>> getDcjgffcsList(String xh,String xn) {
		return dao.getDcjgffcsList(xh,xn);
	}
	
	/**
	 * @throws Exception  
	 * @����:���´���������Ŵ����������Ϣ����ɾ������
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��1��16�� ����9:43:12
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param xn
	 * @param ffcsList
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean updateDcjgffcs(String xh, String xn, List<DcjgffcsModel> ffcsList) throws Exception {
		HashMap<String,String> map = new HashMap<String,String> ();
		map.put("xh", xh);
		map.put("xn", xn);
		List<HashMap<String,String>> xhxnList = new ArrayList<HashMap<String,String>>();
		xhxnList.add(map);
		boolean result = dao.deleteDcjgffsc(xhxnList);
		if(result){
			result = dao.saveDcjgffsc(xh, xn, ffcsList);
		}
		return result;
	}

	/** 
	 * @����:���ݴ������id���ֲ�ѯ���ѧ�ţ�ѧ�꼯��
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��1��16�� ����12:37:20
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param idArr
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getXhxnList(String[] idArr) {
		return dao.getXhxnList(idArr);
	}
	
	
	//=================���Ի����뵼�����Զ�����=====================
	/** 
	 * @����:��ѯ����ģ����Ϣ
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��1��18�� ����9:59:34
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param drmkdm
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws 
	 */
	public HashMap<String, String> getDrmbxx(String drmkdm) {
		HashMap<String,String> drmbxx = dao.getDrmbxx(drmkdm);
		return drmbxx;
	}

	/** 
	 * @����:��ѯ��������б���Ϣ
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��1��18�� ����9:59:39
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param drmkdm
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getDrgzxxList(String drmkdm) {
		List<HashMap<String,String>> drgzxxList = dao.getDrgzxxList(drmkdm);
		List<HashMap<String,String>> zdExtraDrgzxxList = this.getZDExtraDrgzxxList();
		drgzxxList.addAll(zdExtraDrgzxxList);
		return drgzxxList;
	}
	
	/**
	 * @����:��ȡ�����Ի������ֶε�������б�
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��1��18�� ����11:09:40
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getZDExtraDrgzxxList(){
		List<HashMap<String,String>> zdExtraDrgzxxList = new ArrayList<HashMap<String,String>>();
		HashMap<String,String> ffsj = new HashMap<String,String> ();
		HashMap<String,String> ffje = new HashMap<String,String> ();
		HashMap<String,String> ffnr = new HashMap<String,String> ();
		
		//drl,drlmc,lsjgsh,gshxx,sfzj,sfbt,zdcd,sfwy
		ffsj.put("drl", "ffsj");
		ffsj.put("drlmc", "����ʱ��");
		ffsj.put("lsjgsh", "field:Datetime(yyyy-MM-dd)");
		ffsj.put("gshxx", "yyyy-MM-dd������ж������|����");
		ffsj.put("sfzj", "0");
		ffsj.put("sfbt", "0");
		ffsj.put("zdcd", "10");
		ffsj.put("sfwy", "0");
		
		ffje.put("drl", "ffje");
		ffje.put("drlmc", "���Ž��");
		ffje.put("lsjgsh", "field:String");
		ffje.put("gshxx", "����ж������|����");
		ffje.put("sfzj", "0");
		ffje.put("sfbt", "0");
		ffje.put("zdcd", "10");
		ffje.put("sfwy", "0");
		
		ffnr.put("drl", "ffnr");
		ffnr.put("drlmc", "��������");
		ffnr.put("lsjgsh", "field:String");
		ffnr.put("gshxx", "����ж������|������ע�ⲻҪ����Ӣ�ķֺ�");
		ffnr.put("sfzj", "0");
		ffnr.put("sfbt", "0");
		ffnr.put("zdcd", "100");
		ffnr.put("sfwy", "0");
		
		zdExtraDrgzxxList.add(ffsj);
		zdExtraDrgzxxList.add(ffje);
		zdExtraDrgzxxList.add(ffnr);
		return zdExtraDrgzxxList;
	}

	/**
	 * @throws Exception 
	 * @����:���ɸ��Ի�����ʱ��excelģ��
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��1��18�� ����3:28:32
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param drmkdm
	 * @return
	 * File �������� 
	 * @throws 
	 */
	public File getExcelTemplate(String path,String drmkdm){
		File file = new File(path,drmkdm+".xls");
		
		// ���ļ�
		WritableWorkbook book = null;
		try {
			//��ȡ�������д��excel��������ע
			List<HashMap<String,String>> drgzxxList = this.getDrgzxxList(drmkdm);
			
			 book = Workbook.createWorkbook(file);
			// ���ɵ��������0��ʾsheet1��imporΪ������
			WritableSheet sheet1 = book.createSheet("import", 0);
			
			//��䵼���м���ע��ʾ�������
			WritableFont font1 = new WritableFont(WritableFont.ARIAL,10,WritableFont.BOLD,false,UnderlineStyle.NO_UNDERLINE,Colour.WHITE);
			WritableCellFormat cellFormat1 = new WritableCellFormat(font1);  
		    //���ñ�����ɫ;  
		    cellFormat1.setBackground(Colour.GREEN);
			for(int i=0;i<drgzxxList.size();i++){
				//���Ǹ�����������Ӧ��ȣ���δʵ��...
				
				Label label = new Label(i,0, drgzxxList.get(i).get("drlmc"),cellFormat1);
//				sheet1.setColumnView(1, drgzxxList.get(i).get("drlmc").length());  
				
				/*
				 * ������Ψһ(sfzj/sfwy)�������ظ�
				 * ����(sfbt)������Ϊ��
				 * ��󳤶�(zdcd)����󳤶�Ϊ?
				 */
				List<String> pznrList = new ArrayList<String>();
				if("1".equals(drgzxxList.get(i).get("sfwy"))){
					pznrList.add("�����ظ�");
				}
				if("1".equals(drgzxxList.get(i).get("sfbt"))){
					pznrList.add("����Ϊ��");
				}
				if(!StringUtil.isNull(drgzxxList.get(i).get("zdcd"))){
					pznrList.add("��󳤶�Ϊ��"+drgzxxList.get(i).get("zdcd"));
				}
				
				String pznr = "";
				for(int j=0;j<pznrList.size();j++){
					pznr = pznr+(j+1)+"."+pznrList.get(j);
					if(j!=pznrList.size()-1) pznr+="\r\n";
				}
				
				WritableCellFeatures cellFeatures = new WritableCellFeatures();  
				cellFeatures.setComment(pznr);  
				label.setCellFeatures(cellFeatures);  
				
				sheet1.addCell(label);
			}
			
			//��ȡ��������Ϣ�����ɲ���丨����
			List<HashMap<String,Object>> drfzxxAndFzdmxxList = this.getDrfzxxAndFzdmxxList(drmkdm);
			
			//ѭ�����ɸ�����sheet
			for(int k=0;k<drfzxxAndFzdmxxList.size();k++){
				HashMap<String,Object> drfzxxAndFzdmxx = drfzxxAndFzdmxxList.get(k);
				String dm = (String)drfzxxAndFzdmxx.get("fzdmxx_dm");
				String mc = (String)drfzxxAndFzdmxx.get("fzdmxx_mc");
				WritableSheet sheet = book.createSheet((String) drfzxxAndFzdmxx.get("fzmc"),k+1);
				List<HashMap<String,String>> fzdmxxList = (List<HashMap<String,String>>)drfzxxAndFzdmxx.get("fzdmxxList");
				for(int x=0;x<fzdmxxList.size();x++){
					Label label1 = new Label(0, x, fzdmxxList.get(x).get(dm));
					Label label2 = new Label(1, x, fzdmxxList.get(x).get(mc));
					sheet.addCell(label1);
					sheet.addCell(label2);
				}
			}
		} catch (RowsExceededException e) {
			e.printStackTrace();
		} catch (WriteException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			// д�����ݲ��ر��ļ�
			try {
				book.write();
				book.close();
			} catch (WriteException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
			
		return file;
	}
	
	
	/**
	 * @����:��ȡ���븨�������븨�����и��Ƶĸ����������Ϣ
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��1��18�� ����6:30:29
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param drmkdm
	 * @return
	 * List<HashMap<String,Object>> �������� 
	 * @throws
	 */
	public List<HashMap<String,Object>> getDrfzxxAndFzdmxxList(String drmkdm){
		List<HashMap<String,Object>> drfzxxAndFzdmxxList = new ArrayList<HashMap<String,Object>>();
		List<HashMap<String,String>> drfzxxList = dao.getDrfzxxList(drmkdm);
		for(HashMap<String,String> drfzxx:drfzxxList){
			String fzmc = drfzxx.get("fzmc");
			String pz = drfzxx.get("pz");
			
			String[] pzArr = pz.split("\\|");
			String tableName = pzArr[0].substring(pzArr[0].indexOf(":")+1);
			String [] outputValue = {pzArr[1],pzArr[2]};
			
			List<HashMap<String,String>> fzdmxxList = dao.getFzdmxxList(tableName,outputValue,outputValue[0]);
			HashMap<String,Object> drfzxxAndFzdmxx = new HashMap<String,Object>();
			drfzxxAndFzdmxx.put("fzmc", fzmc);
			drfzxxAndFzdmxx.put("fzdmxx_dm", pzArr[1]);
			drfzxxAndFzdmxx.put("fzdmxx_mc", pzArr[2]);
			drfzxxAndFzdmxx.put("fzdmxxList", fzdmxxList);
			
			drfzxxAndFzdmxxList.add(drfzxxAndFzdmxx);
			
		}
		return drfzxxAndFzdmxxList;
	}

	/**
	 * @throws SQLException  
	 * @����:���浼����Ϣ
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��1��19�� ����6:59:59
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param inputStream
	 * @param path
	 * @return
	 * HashMap<String,Object> �������� 
	 * @throws 
	 */
	public HashMap<String, Object> saveImport(InputStream inputStream, String path,String drmkdm) throws SQLException {
		HashMap<String, Object> resultMap = null;
		//��ȡ�����й���
		List<HashMap<String,String>> drgzxxList = this.getDrgzxxList(drmkdm);
		
		Workbook wb = null;
		Sheet sheet = null;
		try {
			wb = Workbook.getWorkbook(inputStream);
			sheet = wb.getSheet(0);
			//��֤ģ��ͷ����  error:01
			resultMap = this.checkImportHeader(sheet, drgzxxList);
			if((Boolean) resultMap.get("result")){
				//ģ��������������Ĳ���
				//��ȡ�����д���List<HashMap<String,String>>  ÿ�ж�Ӧһ��HashMap
				List<HashMap<String,String>> excelDataList = this.getExcelDataList(sheet,drgzxxList);
				
				//����ǰ������������֤ error:02
				resultMap = checkExcelDataList(excelDataList,drgzxxList);
				if((Boolean) resultMap.get("result")){
					//��֤ͨ��������Ҫ�ж�excel�����б����Ƿ����ظ�
					resultMap = checkExcelDataRepeat(excelDataList);
					if((Boolean) resultMap.get("result")){
						//�������ظ���������Ĳ������ݵ����ݿ�Ĳ���
						boolean insertResult = insertDataIntoDB(excelDataList);
						if(insertResult){
							resultMap.put("totalCount", excelDataList.size());
						}
					}else{
						//�����ظ������ݴ���������ʾ����excel�ļ���������error:03
						String errorTipsExcelName = this.createErrorTipsExcel(excelDataList,path,drmkdm,drgzxxList);
						resultMap.put("errorTipsExcelName", errorTipsExcelName);
					}
				}else{
					//��֤��ͨ�������ݴ���������ʾ����excel�ļ���������
					String errorTipsExcelName = this.createErrorTipsExcel(excelDataList,path,drmkdm,drgzxxList);
					resultMap.put("errorTipsExcelName", errorTipsExcelName);
				}
			}
			
		} catch (BiffException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return resultMap;
	}
	
	/** 
	 * @����:ѭ�����ݵ����������������֤excel����
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��1��20�� ����11:58:02
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param excelDataList
	 * @param drgzxxList
	 * @return
	 * HashMap<String,Object> �������� 
	 * @throws 
	 */
	private HashMap<String, Object> checkExcelDataList(List<HashMap<String, String>> excelDataList,
			List<HashMap<String, String>> drgzxxList) {
		//excelDataList���������ݣ�������֤������У������˸��������д�����ʾ��Ϊ���һ��
		HashMap<String,Object> resultMap = new HashMap<String,Object>();
		resultMap.put("result", true);
		Integer errorCount = 0;
		for(HashMap<String,String>excelData:excelDataList){
			boolean rowResult = true;
			StringBuilder rowError = new StringBuilder();
			for(HashMap<String,String>drgzxx:drgzxxList){
				String sfbt = drgzxx.get("sfbt");
				String sfwy = drgzxx.get("sfwy");
				Integer zdcd = Integer.parseInt(drgzxx.get("zdcd"));
				String lsjgsh = drgzxx.get("lsjgsh");
				String gshxx = drgzxx.get("gshxx");
				String drlmc = drgzxx.get("drlmc");
				String drl = drgzxx.get("drl");
				String cellContents = excelData.get(drl); 
				
				if("1".equals(sfbt)){
					//��֤����Ϊ��
					if(StringUtil.isNull(cellContents)){
						resultMap.put("result", false);
						rowResult = false;
						rowError.append("["+drlmc+"]����Ϊ�գ� ");
						continue;
					}
				}
				
				if(!StringUtil.isNull(cellContents)){
					//��Ԫ�����ݲ�Ϊ��ʱ���������֤
					if("1".equals(sfwy)){
						//��֤�����ظ�(�Դ��������)
						if(dao.isRepeatForDr(drl,cellContents)){
							resultMap.put("result", false);
							rowResult = false;
							rowError.append("["+drlmc+"]�����ظ��� ");
							continue;
						}
					}
					//��֤��󳤶�
					if(!checkLength(cellContents,zdcd)){
						resultMap.put("result", false);
						rowResult = false;
						rowError.append("["+drlmc+"]��󳤶�Ϊ:"+zdcd+"�� ");
						continue;
					}
					//��֤���ݸ�ʽ(��Դ�ڵ������ĸ�ʽ����)
					if(lsjgsh.startsWith("field")){
						if(lsjgsh.contains("Datetime")){
							String fmt = lsjgsh.substring(lsjgsh.indexOf("(")+1,lsjgsh.indexOf(")"));
							SimpleDateFormat sdf = new SimpleDateFormat(fmt);
							try {
								this.checkDateFormat(sdf,cellContents);
							} catch (ParseException e) {
								resultMap.put("result", false);
								rowResult = false;
								rowError.append("["+drlmc+"]��ʽΪ:"+fmt+"�� ");
								continue;
							}
						}
					}
					if(lsjgsh.startsWith("json")){
						//���ｫ���ݸ���json�滻
						HashMap<String,String> jsonMap = new HashMap<String,String>();
						String jsonString = lsjgsh.substring(lsjgsh.indexOf("{")+1,lsjgsh.indexOf("}"));
						String[] jsonArr = jsonString.split(",");
						for(String json:jsonArr){
							String [] keyValue = json.split(":");
							String key = keyValue[0].replaceAll("\"", "");
							String value = keyValue[1].replaceAll("\"", "");
							jsonMap.put(key, value);
						}
						if(jsonMap.keySet().contains(cellContents)){
							excelData.put(drl, jsonMap.get(cellContents));
						}else{
							resultMap.put("result", false);
							rowResult = false;
							rowError.append("["+drlmc+"]"+gshxx+"�� ");
							continue;
						}
						
					}
					if(lsjgsh.startsWith("sql")){
						String sql = lsjgsh.substring(lsjgsh.indexOf(":")+1);
						String data = dao.changeCellData(sql,cellContents,drl);
						if(StringUtil.isNull(data)){
							resultMap.put("result", false);
							rowResult = false;
							rowError.append("["+drlmc+"]"+gshxx+"�� ");
							continue;
						}else{
							excelData.put(drl, data);
						}
					}
				}
				
			}
			
			//��֤����ʱ�䡢���Ž��������ݴ����Ƿ�һ��
			String ffsj = (String)excelData.get("ffsj");
			String ffje = (String)excelData.get("ffje");
			String ffnr = (String)excelData.get("ffnr");
			if(!(ffsj.split("\\|").length==ffje.split("\\|").length&&ffje.split("\\|").length==ffnr.split("\\|").length)){
				resultMap.put("result", false);
				rowResult = false;
				rowError.append("[���Ŵ���]����ʱ�䡢���Ž��������ݴ�����һ�£� ");
			}
			excelData.put("rowError", rowError.toString());
			if(!rowResult){
				errorCount++;
			}
		}
		
		if(!(Boolean)resultMap.get("result")){
			resultMap.put("error", "02");
			//resultMap.put("excelDataList", excelDataList);
			resultMap.put("errorCount", errorCount);
			resultMap.put("totalCount", excelDataList.size());
		}
		return resultMap;
	}

	/**
	 * @throws ParseException  
	 * @����:��֤ʱ���ʽ��������ȡ��������Ϊ�˼��ݷ���ʱ����ֶεĿ�����|�ָ��Ĳ��̶���Ŀ
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��1��23�� ����5:20:03
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param sdf
	 * void �������� 
	 * @throws 
	 */
	private void checkDateFormat(SimpleDateFormat sdf,String cellContents) throws ParseException {
		if(cellContents.contains("|")){
			String [] cellContentsArr = cellContents.split("\\|");
			for(String str:cellContentsArr){
				sdf.parse(str);
			}
		}else{
			sdf.parse(cellContents);
		}
		
	}

	/** 
	 * @����:��֤�ֶγ��ȣ�������ȡ��������Ϊ�˼��ݷ���ʱ����ֶεĿ�����|�ָ��Ĳ��̶���Ŀ
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��1��23�� ����4:59:49
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param cellContents
	 * @param zdcd
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	private boolean checkLength(String cellContents, Integer zdcd) {
		boolean result = true;
		if(cellContents.contains("|")){
			String [] cellContentsArr = cellContents.split("\\|");
			for(String str:cellContentsArr){
				if(str.length()>zdcd){
					result = false;
					break;
				}
			}
		}else{
			if(cellContents.length()>zdcd){
				result = false;
			}
		}
		return result;
	}

	/** 
	 * @����:��ȡexcel�����ļ��е����ݵ�list
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��1��20�� ����11:33:14
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param sheet
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	private List<HashMap<String, String>> getExcelDataList(Sheet sheet,List<HashMap<String,String>> drgzxxList) {
		List<HashMap<String,String>> excelDataList = new ArrayList<HashMap<String,String>>();
		int rows = sheet.getRows();
		int cols = sheet.getColumns();
		for(int i=1;i<rows;i++){
			HashMap<String,String> excelRow = new HashMap<String,String>();
			for(int j=0;j<cols;j++){
				excelRow.put(drgzxxList.get(j).get("drl"), sheet.getCell(j, i).getContents().trim());
			}
			excelDataList.add(excelRow);
		}
		return excelDataList;
	}

	/**
	 * @����:��֤�����ļ���ʽ�Ƿ���ȷ���Ƿ�˳����������У�
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��1��20�� ����11:18:54
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param sheet
	 * @param drgzxxList
	 * @return
	 * HashMap<String,Object> �������� 
	 * @throws
	 */
	private HashMap<String,Object> checkImportHeader(Sheet sheet,List<HashMap<String,String>> drgzxxList){
		HashMap<String,Object> resultMap = new HashMap<String,Object>();
		Integer rows = sheet.getRows();
		Integer cols = sheet.getColumns();
		
		List<String> excelHeaderList = new ArrayList<String>();
		for(int i=0;i<cols;i++){
			excelHeaderList.add(sheet.getCell(i, 0).getContents());
		}
		if(drgzxxList.size()!=cols){
			resultMap.put("result", false);
			resultMap.put("error", "01");
			resultMap.put("message", "�����ļ��е����в�����ģ��Ҫ��");
			return resultMap;
		}else{
			for(int j=0;j<drgzxxList.size();j++){
				String drlmc = drgzxxList.get(j).get("drlmc");
				String excelHeaderCol = excelHeaderList.get(j);
				if(!drlmc.equals(excelHeaderCol)){
					resultMap.put("result", false);
					resultMap.put("error", "01");
					resultMap.put("message", "�����ļ��е����в�����ģ��Ҫ��");
					return resultMap;
				}
			}
			resultMap.put("result", true);
			return resultMap;
		}
	}
	
	/** 
	 * @����:����Excel������ʾ�ļ���������
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��1��22�� ����9:56:00
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param excelDataList
	 * @param path
	 * void �������� 
	 * @throws 
	 */
	private String createErrorTipsExcel(List<HashMap<String, String>> excelDataList, String path,
										String drmkdm,List<HashMap<String,String>> drgzxxList) {
		File file = new File(path,drmkdm+"_error.xls");
		
		// ���ļ�
		WritableWorkbook book = null;
		try {
			 book = Workbook.createWorkbook(file);
			 WritableSheet sheet1 = book.createSheet("error", 0);
			
			//���ͷ��
			WritableFont font1 = new WritableFont(WritableFont.ARIAL,10,WritableFont.BOLD,false,UnderlineStyle.NO_UNDERLINE,Colour.WHITE);
			WritableCellFormat cellFormat1 = new WritableCellFormat(font1);  
			cellFormat1.setBackground(Colour.GREEN);
			
			WritableFont font2 = new WritableFont(WritableFont.ARIAL,10,WritableFont.NO_BOLD,false,UnderlineStyle.NO_UNDERLINE,Colour.BLACK);
			WritableCellFormat cellFormat2 = new WritableCellFormat(font2);  
			cellFormat2.setBackground(Colour.YELLOW);
			
			WritableCellFormat cellFormat3 = new WritableCellFormat(font1);  
			cellFormat3.setBackground(Colour.RED);
			
			for(int i=0;i<=drgzxxList.size();i++){
			    Label label = null;
			    if(drgzxxList.size()==i){
			    	label = new Label(i,0,"������Ϣ" ,cellFormat3);
			    }else{
			    	label = new Label(i,0,drgzxxList.get(i).get("drlmc"),cellFormat1);
			    }
				sheet1.addCell(label);
			}
			
			//�������
			for(int i=0;i<excelDataList.size();i++){
				for(int j=0;j<=drgzxxList.size();j++){
				    Label label = null;
				    if(drgzxxList.size()==j){
				    	String errorTip = excelDataList.get(i).get("rowError");
				    	if(StringUtil.isNull(errorTip)){
				    		//������ʾΪ�գ��ޱ���ɫ
				    		label = new Label(j,i+1,errorTip);
				    	}else{
				    		//������ʾ��Ϊ�գ���ɫ����ɫ
				    		label = new Label(j,i+1,errorTip,cellFormat2);
				    	}
				    }else{
				    	label = new Label(j,i+1,excelDataList.get(i).get(drgzxxList.get(j).get("drl")));
				    }
					sheet1.addCell(label);
				}
			}
			
		} catch (RowsExceededException e) {
			e.printStackTrace();
		} catch (WriteException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			// д�����ݲ��ر��ļ�
			try {
				book.write();
				book.close();
			} catch (WriteException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return file.getName();
	}
	
	/** 
	 * @����:��֤excel�ļ��е����ݱ����Ƿ����ظ�������ֻ����֤ѧ�ţ�����javaѭ��
	 * 		  �����֤�ֶν϶�ϸ��ӿ��Կ������ݿ���ʱ��
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��1��22�� ����2:53:08
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param excelDataList
	 * @return
	 * HashMap<String,Object> �������� 
	 * @throws 
	 */
	private HashMap<String, Object> checkExcelDataRepeat(List<HashMap<String, String>> excelDataList) {
		HashMap<String,Object> resultMap = new HashMap<String,Object>();
		boolean result = true;
		int errorCount = 0;
		for(int i=0;i<excelDataList.size()-1;i++){
			for(int j=i+1;j<excelDataList.size();j++){
				HashMap<String,String> excelDataI = excelDataList.get(i);
				HashMap<String,String> excelDataJ = excelDataList.get(j);
				if(excelDataI.get("XH").equals(excelDataJ.get("XH"))){
					excelDataI.put("rowError", "[ѧ��]excel��ѧ�Ŵ����ظ��� ");
					excelDataJ.put("rowError", "[ѧ��]excel��ѧ�Ŵ����ظ��� ");
					result = false;
					errorCount++;
				}
			}
		}
		if(!result){
			resultMap.put("error", "03");
			resultMap.put("errorCount", errorCount+1);
			resultMap.put("totalCount", excelDataList.size());
		}
		resultMap.put("result", result);
		return resultMap;
	}
	
	/**
	 * @throws SQLException  
	 * @����:��������֤ͨ�������������������ݿ�
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��1��22�� ����4:14:28
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param excelDataList
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	private boolean insertDataIntoDB(List<HashMap<String, String>> excelDataList) throws SQLException {
		List<String[]> dcjgParaList = new ArrayList<String[]> ();
		List<String[]> dcjg_ffcsParaList = new ArrayList<String[]> ();
		
		for(HashMap<String, String>excelData:excelDataList){
			String xh = excelData.get("XH");
			String xn = excelData.get("XN");
			String rwqsfsqdc = excelData.get("rwqsfsqdc");
			String xfje = excelData.get("XFJE");
			String dkbj = excelData.get("DKBJ");
			String yhdm = excelData.get("YHDM");
			String dkhth = excelData.get("DKHTH");
			String dkkssj = excelData.get("DKKSSJ");
			String dkjssj = excelData.get("DKJSSJ");
			String dclb = excelData.get("DCLB");
			String rwnf = excelData.get("rwnf");
			String twnf = excelData.get("twnf");
			String ffsj = excelData.get("ffsj");
			String ffje = excelData.get("ffje");
			String ffnr = excelData.get("ffnr");
			
			String [] ffsjArr = {};
			if(!StringUtil.isNull(ffsj)){
				ffsjArr = ffsj.split("\\|");
			}
			
			String [] ffjeArr = {};
			if(!StringUtil.isNull(ffje)){
				ffjeArr = ffje.split("\\|");
			}
			
			String [] ffnrArr = {};
			if(!StringUtil.isNull(ffnr)){
				ffnrArr = ffnr.split("\\|");
			}
			
			for(int i=0;i<ffsjArr.length;i++){
				String [] dcjg_ffcsPara = {xh,xn,ffsjArr[i],ffjeArr[i],ffnrArr[i]};
				dcjg_ffcsParaList.add(dcjg_ffcsPara);
			}
			
			String[] dcjgPara = {xh,xn,rwqsfsqdc,xfje,dkbj,yhdm,dkhth,dkkssj,dkjssj,dclb,rwnf,twnf};
			dcjgParaList.add(dcjgPara);
		}
		
		//����������������Ϣ
		int [] dcjgResult = {};
		if(dcjgParaList.size()>0){
			dao.insertDcjgDataIntoDB(dcjgParaList);
		}
		//�����������������Ŵ�����Ϣ
		int [] dcjg_ffcsResult = {};
		if(dcjg_ffcsParaList.size()>0){
			dao.insertDcjgFfcsDataIntoDB(dcjg_ffcsParaList);
		}
		
		return dcjgResult.length>=0&&dcjg_ffcsResult.length>=0;
	}

	/**
	 * @throws Exception  
	 * @����:����ʱ��ѯ���м�¼���������Ŵ�����Ϣ�������
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��1��24�� ����10:49:58
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getDcListForZD(RwfbydcjgModel model, User user) throws Exception {
		return dao.getDcListForZD(model,user);
	}
	
	
}
