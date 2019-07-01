package xsgzgl.gygl.xxtj;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.junit.Test;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.FreeMarkerUtil;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

import sun.util.logging.resources.logging;

import common.Globals;

import jxl.CellView;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.Colour;
import jxl.format.PageOrientation;
import jxl.format.PaperSize;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import xgxt.DAO.DAO;
import xgxt.DAO.ExcelMB;
import xgxt.action.Base;
import xgxt.form.User;
import xgxt.utils.ExcelMethods;
import xgxt.utils.String.StringUtils;
import xsgzgl.gygl.comm.GyglNewService;
import xsgzgl.gygl.ldgl.LdglModel;
import xsgzgl.gygl.ldgl.LdglServices;

public class XxtjService extends GyglNewService{
	private XxtjDAO xxtjDao = new XxtjDAO();
	
	/**
	 * ¥�����Ҵ�λͳ��
	 * @param model
	 * @return
	 */
	public List<HashMap<String, String>> getLdqscwTj(XxtjModel model){
		return xxtjDao.getLdqscwTj_new(model);
	}
	
	/**
	 * ��ȡĳ��¥��������ϸͳ����Ϣ
	 * @param lddm
	 * @return
	 */
	public LdModel getLdDetailTj(String lddm,User user){
		String[] output = new String[]{"lddm", "ldmc", "qsh","qsxb", "ch", "xydm", "xymc", "nj", "cws", "yzr", "zszt", "sfhz","sfbl","ckqs"}; 
		
		
		List<HashMap<String, String>> rs = xxtjDao.getLdxxOne(lddm, output,user);
		
		// ¥��model
		LdModel ldModel = new LdModel();
		
		// ��ȡ¥����Ϣ
		LdglServices ldglService = new LdglServices();
		LdglModel ldglModel = new LdglModel();
		ldglModel.setLddm(lddm);
		
		Map<String, String> ldMap = ldglService.getLdxxOne(ldglModel);
		
		ldModel.setLddm(lddm);
		ldModel.setLdmc(ldMap.get("ldmc"));
		ldModel.setLdxb(ldMap.get("ldxb"));
		
		// ����map�����Ϊkey�����Ҽ���ListΪvalue
		Map<Integer, List<QsModel>> csMap = ldModel.getCsMap();
		
		for(Map<String, String> qsMap : rs){
			// ����
			Integer ch = -Integer.parseInt(qsMap.get("ch"));//�ô��Ӹ�����Ҫ��Ϊ�˽��з������򣬶�Ӧ��ҳ�滹Ҫ�Ӹ��Ž��лָ�
			// ����������¥��ȡ���ڵģ��������½�¥��List
			List<QsModel> csList = csMap.containsKey(ch) ? csMap.get(ch) : new ArrayList<QsModel>();
			// ����ʵ��Model
			QsModel qsModel = new QsModel(qsMap);
			csList.add(qsModel);
			csMap.put(ch, csList);
		}
		
		return ldModel;
	}
	
	/**
	 * ��Ϣͳ�Ƶ������� 
	 * @param lddm
	 * @param qsh
	 * @return
	 */
	public List<HashMap<String, String>> xxtjForQs(String lddm, String qsh){
		
		return xxtjDao.xxtjForQs(lddm, qsh);
	}
	
	/**
	 * ����ס����Ϣ
	 * @param lddm
	 * @param os
	 * @throws Exception
	 */
	public void xxtjDczsxx(String lddm,OutputStream os)throws Exception{
		int x;int y;
		if("13798".equalsIgnoreCase(Base.xxdm)) {
			x = 5;
			y = 6;
		}else {
			x = 4;
			y = 5;
		}
		//�㶫����ְҵ����ѧԺ
		if(Globals.XXDM_GDJSZYJSXY.equalsIgnoreCase(Base.xxdm)){
			xxtjDczsxx_gdjs(lddm, os);
			return;
		}else if(Globals.XXDM_ZJJSZYJSXY.equalsIgnoreCase(Base.xxdm)){
			xxtjDczsxx_zjjszyjsxy(lddm, os);
			return;
		}
		WritableWorkbook wwb = Workbook.createWorkbook(os);
		WritableSheet ws = wwb.createSheet("��Ԣס�����", 0);
		
		//���λ��
		String num = xxtjDao.getMaxCws(lddm);
		//����ͷ����
		String[] bt = new String[Integer.valueOf(num)+2];
		bt[0] = "���Һ�"; bt[1] = "�����Ա�";
		for(int i=2;i<Integer.valueOf(num)+2;i++){
			bt[i] = String.valueOf(i-1)+"�Ŵ�";
		}
		//¥����Ϣ
		HashMap<String,String> ldxx = xxtjDao.getLdxx(lddm);
		//������Ϣlist
		List<HashMap<String,String>> qsList = xxtjDao.getQsList(lddm);
		//��λס����Ϣlist
		List<HashMap<String,String>> zsxxList = xxtjDao.getZsxxList(lddm);
		
		try {
			//���ô�ӡ��ʽ��ֽ�Ŵ�С��ҳ�߾�
			ws.setPageSetup(PageOrientation.LANDSCAPE.LANDSCAPE,PaperSize.A4,0.5d,0.5d);
			WritableCellFormat wcFormat = new WritableCellFormat();
			WritableFont font = new WritableFont(WritableFont.ARIAL,16);
			font.setBoldStyle(WritableFont.BOLD);
			wcFormat.setFont(font);
			wcFormat.setAlignment(Alignment.CENTRE);
			wcFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
			Label titleCell;
			
			titleCell = new Label(0, 0, ldxx.get("ldmc")+"ס���������");
			titleCell.setCellFormat(wcFormat);
			ws.mergeCells(0, 0, bt.length-1, 0);
			ws.addCell(titleCell);
			
			wcFormat = new WritableCellFormat();
			font = new WritableFont(WritableFont.ARIAL,10);
			font.setBoldStyle(WritableFont.BOLD);
			wcFormat.setFont(font);
			wcFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
			for(int i = 0;i<bt.length;i++){
				titleCell = new Label(i, 1, bt[i]);
				titleCell.setCellFormat(wcFormat);
				ws.addCell(titleCell);
			}
			
			wcFormat = new WritableCellFormat();
			font = new WritableFont(WritableFont.ARIAL,10);
			wcFormat.setFont(font);
			wcFormat.setWrap(true);
			if(qsList.size()>0){
				//����������Ϣ
				for(int i=0;i<qsList.size();i++){
					ws.mergeCells(0, i*x+2, 0, i*x+y);
					titleCell = new Label(0,i*x+2,qsList.get(i).get("qsh"));
					ws.addCell(titleCell);
					ws.mergeCells(1, i*x+2, 1, i*x+y);
					titleCell = new Label(1,i*x+2,qsList.get(i).get("qsxb"));
					ws.addCell(titleCell);
					if(zsxxList.size()>0){
						//������λס����Ϣ
						String[] zghs=null;
						String[] xms=null;
						for(int j=0;j<zsxxList.size();j++){
							String bzr="";
							if(zsxxList.get(j).get("qsh").equalsIgnoreCase(qsList.get(i).get("qsh"))){
								int k =1+ Integer.valueOf(zsxxList.get(j).get("cwh"));
								titleCell = new Label(k,i*x+2,"ѧ�ţ�"+zsxxList.get(j).get("xh"));
								ws.addCell(titleCell);
								titleCell = new Label(k,i*x+3,"������"+zsxxList.get(j).get("xm"));
								ws.addCell(titleCell);
								titleCell = new Label(k,i*x+4,"�༶��"+zsxxList.get(j).get("bjmc"));
								ws.addCell(titleCell);
								if(zsxxList.get(j).get("bzrzgh")!=null&&""!=zsxxList.get(j).get("bzrzgh")){
									zghs = zsxxList.get(j).get("bzrzgh").split(";");
									xms = zsxxList.get(j).get("bzrxm").split(";");
									for (int i1 = 0; i1 < zghs.length; i1++) {
										bzr+=xms[i1]+" ";
									}
								}
								titleCell = new Label(k,i*x+5,"�����Σ�"+bzr);
								ws.addCell(titleCell);
								if("13798".equalsIgnoreCase(Base.xxdm)) {
									titleCell = new Label(k,i*x+6,"�꼶��"+zsxxList.get(j).get("nj"));
									ws.addCell(titleCell);
								}
							}
						}
					}
				}
				
				for(int i = 2;i<bt.length;i++){
					ws.setColumnView(i, 15);
				}
			}
			wwb.write();
			wwb.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public void ldzsxxDc(String lddm,OutputStream os)throws Exception{
		//����ͷ����
		//¥����Ϣ
		List<HashMap<String,String>> ldxxList =new ArrayList<HashMap<String,String>>();
		if(StringUtils.isNull(lddm)){
			ldxxList=xxtjDao.getAllLdxx();
		}else{
			HashMap<String,String> ldxxMap =new HashMap<String,String>();
			ldxxMap.put("lddm", lddm);
			ldxxList.add(ldxxMap);
		}
		WritableWorkbook wwb = Workbook.createWorkbook(os);
		for (int s = 0; s < ldxxList.size(); s++) {
			
		HashMap<String,String> ldxx = xxtjDao.getLdxx(ldxxList.get(s).get("lddm"));
		
		WritableSheet ws = wwb.createSheet(ldxx.get("ldmc"), s);
		
		//¥����Ϣlist
		List<HashMap<String,String>> lcList = xxtjDao.getLcxx(ldxxList.get(s).get("lddm"));
 		List<HashMap<String,String>> qsList = xxtjDao.getQsList(ldxxList.get(s).get("lddm"));
		//��λס����Ϣlist
		List<HashMap<String,String>> zsxxList = xxtjDao.getZsxxList(ldxxList.get(s).get("lddm"));
		
		try {
			//���ô�ӡ��ʽ��ֽ�Ŵ�С��ҳ�߾�
			ws.setPageSetup(PageOrientation.LANDSCAPE.LANDSCAPE,PaperSize.A4,0.5d,0.5d);
			WritableCellFormat wcFormat = new WritableCellFormat();
			wcFormat=ExcelMethods.getWcf(WritableFont.ARIAL, 10, false,
					Alignment.CENTRE, VerticalAlignment.CENTRE, true, Border.ALL);
			ExcelMB ex = new ExcelMB();
			ws.mergeCells(0, 0, 7, 0);
			ex.printToOneCell_multy(ws, ldxx.get("ldmc")+"ס���������", 0, 0, 15, true,
					Alignment.CENTRE, VerticalAlignment.CENTRE, true, 700,
					Border.ALL);
			
			if(qsList.size()>0){
				//����������Ϣ
				int index=1;
				int lcindex=0;
				for(int i=0;i<lcList.size();i++){
					int qsNum=0;
					if(qsList.size()>0){
						int cols=0;
						for(int m=0;m<qsList.size();m++){
						if(lcList.get(i).get("ch").equalsIgnoreCase(qsList.get(m).get("ch"))){
							if(qsNum%10==1&&qsNum!=1){
								index++;
								lcindex++;
								cols=0;
							}
//							if("1".equals(qsList.get(m).get("ct"))){
//							ws.addCell(new Label(cols,7*index+lcindex-6,qsList.get(m).get("bjmc"),wcFormat));
//							}
							ws.addCell(new Label(cols,7*index+lcindex,qsList.get(m).get("qsh"),wcFormat));
							if(zsxxList.size()>0){
								//������λס����Ϣ
								int k=5;
								for(int j=0;j<zsxxList.size();j++){
									if(zsxxList.get(j).get("qsh").equalsIgnoreCase(qsList.get(m).get("qsh"))){
//										if("1".equals(qsList.get(m).get("ct"))){
//										ws.addCell(new Label(cols,7*index+lcindex-k,zsxxList.get(j).get("xm"),wcFormat));
//										}else{
											ws.addCell(new Label(cols,7*index+lcindex-k,zsxxList.get(j).get("xm")+"("+zsxxList.get(j).get("bjmc")+")",wcFormat));	
//										}
										k--;
									}
								}
							}
							qsNum++;
							cols++;
						}
						}
						lcindex++;
						index++;
					}
				}
			}
		
			
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		}
		wwb.write();
		wwb.close();
	}
	
	/**
	 * ����ס����Ϣ_�㶫����ְҵ����ѧԺ
	 * @param lddm
	 * @param os
	 * @throws Exception
	 */
	public void xxtjDczsxx_gdjs(String lddm,OutputStream os)throws Exception{
		WritableWorkbook wwb = Workbook.createWorkbook(os);
		WritableSheet ws = wwb.createSheet("��Ԣס�����", 0);
		
		//���λ��
		String num = xxtjDao.getMaxCws(lddm);
		//����ͷ����
		String[] bt = new String[Integer.valueOf(num)+3];
		bt[0] = "���Һ�"; bt[1] = "�����Ա�";
		bt[2] = "�༶";
		for(int i=3;i<Integer.valueOf(num)+3;i++){
			bt[i] = String.valueOf(i-2)+"�Ŵ�";
		}
		//¥����Ϣ
		HashMap<String,String> ldxx = xxtjDao.getLdxx(lddm);
		//������Ϣlist
		List<HashMap<String,String>> qsList = xxtjDao.getQsList_gdjs(lddm);
		//��λס����Ϣlist
		List<HashMap<String,String>> zsxxList = xxtjDao.getZsxxList(lddm);
		
		try {
			//���ô�ӡ��ʽ��ֽ�Ŵ�С��ҳ�߾�
			ws.setPageSetup(PageOrientation.LANDSCAPE.LANDSCAPE,PaperSize.A4,0.5d,0.5d);
			WritableCellFormat wcFormat = new WritableCellFormat();
			WritableFont font = new WritableFont(WritableFont.ARIAL,16);
			font.setBoldStyle(WritableFont.BOLD);
			wcFormat.setFont(font);
			wcFormat.setAlignment(Alignment.CENTRE);
			wcFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
			Label titleCell;
			
			titleCell = new Label(0, 0, ldxx.get("ldmc")+"ס���������");
			titleCell.setCellFormat(wcFormat);
			ws.mergeCells(0, 0, bt.length-1, 0);
			ws.addCell(titleCell);
			
			wcFormat = new WritableCellFormat();
			font = new WritableFont(WritableFont.ARIAL,10);
			font.setBoldStyle(WritableFont.BOLD);
			wcFormat.setFont(font);
			wcFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
			for(int i = 0;i<bt.length;i++){
				titleCell = new Label(i, 1, bt[i]);
				titleCell.setCellFormat(wcFormat);
				ws.addCell(titleCell);
			}
			
			wcFormat = new WritableCellFormat();
			font = new WritableFont(WritableFont.ARIAL,10);
			wcFormat.setFont(font);
			wcFormat.setWrap(true);
			if(qsList.size()>0){
				//����������Ϣ
				for(int i=0;i<qsList.size();i++){
//					ws.mergeCells(0, i*3+2, 0, i*3+4);
					titleCell = new Label(0,i+2,qsList.get(i).get("qsh"));
					ws.addCell(titleCell);
//					ws.mergeCells(1, i*3+2, 1, i*3+4);
					titleCell = new Label(1,i+2,qsList.get(i).get("qsxb"));
					ws.addCell(titleCell);
					titleCell = new Label(2,i+2,qsList.get(i).get("qsbj"));
					ws.addCell(titleCell);
					if(zsxxList.size()>0){
						//������λס����Ϣ
						for(int j=0;j<zsxxList.size();j++){
							if(zsxxList.get(j).get("qsh").equalsIgnoreCase(qsList.get(i).get("qsh"))){
								int k =2+ Integer.valueOf(zsxxList.get(j).get("cwh"));
//								titleCell = new Label(k,i+2,zsxxList.get(j).get("xh"));
//								ws.addCell(titleCell);
								titleCell = new Label(k,i+2,zsxxList.get(j).get("xm"));
								ws.addCell(titleCell);
//								titleCell = new Label(k,i+4,zsxxList.get(j).get("bjmc"));
//								ws.addCell(titleCell);						
							}
						}
					}
				}
				
				for(int i = 3;i<bt.length;i++){
					ws.setColumnView(i, 15);
				}
			}
			wwb.write();
			wwb.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * ����ס����Ϣ_�㽭����ְҵ����ѧԺ
	 * @param lddm
	 * @param os
	 * @throws Exception
	 */
	public void xxtjDczsxx_zjjszyjsxy(String lddm,OutputStream os)throws Exception{
		WritableWorkbook wwb = Workbook.createWorkbook(os);
		WritableSheet ws = wwb.createSheet("��Ԣס�����", 0);
		
		//���������
		String maxQss = xxtjDao.getMaxQss_zjjszyjsxy(lddm);
		//¥����Ϣ
		HashMap<String,String> ldxx = xxtjDao.getLdxx(lddm);
		//������Ϣlist
		List<HashMap<String,String>> qsList = xxtjDao.getQsList_zjjszyjsxy(lddm);
		//��λס����Ϣlist
		List<HashMap<String,String>> zsxxList = xxtjDao.getZsxxList_zjjszyjsxy(lddm);
		
		try {
			//���ô�ӡ��ʽ��ֽ�Ŵ�С��ҳ�߾�
			ws.setPageSetup(PageOrientation.LANDSCAPE.LANDSCAPE,PaperSize.A4,0.5d,0.5d);
			WritableCellFormat wcFormat = new WritableCellFormat();
			WritableFont font = new WritableFont(WritableFont.ARIAL,16);
			font.setBoldStyle(WritableFont.BOLD);
			wcFormat.setFont(font);
			wcFormat.setAlignment(Alignment.CENTRE);
			wcFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
			Label titleCell;
			
			titleCell = new Label(0, 0, ldxx.get("ldmc")+"ס���������");
			titleCell.setCellFormat(wcFormat);
			ws.mergeCells(0, 0, Integer.parseInt(maxQss)-1, 0);
			ws.addCell(titleCell);
			
			wcFormat = new WritableCellFormat();
			font = new WritableFont(WritableFont.ARIAL,10);
			font.setBoldStyle(WritableFont.BOLD);
			wcFormat.setFont(font);
			wcFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
			wcFormat = new WritableCellFormat();
			font = new WritableFont(WritableFont.ARIAL,10);
			wcFormat.setFont(font);
			wcFormat.setWrap(true);
			WritableCellFormat greenFormat = new WritableCellFormat();
			WritableFont greenFont = new WritableFont(WritableFont.ARIAL,10);
			greenFormat.setFont(greenFont);
			greenFormat.setBackground(Colour.GREEN);
			WritableCellFormat greenCenterFormat = new WritableCellFormat();
			WritableFont greenCenterFont = new WritableFont(WritableFont.ARIAL,10);
			greenCenterFormat.setFont(greenCenterFont);
			greenCenterFormat.setBackground(Colour.GREEN);
			greenCenterFormat.setAlignment(Alignment.CENTRE);
			greenCenterFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
			WritableCellFormat blueFormat = new WritableCellFormat();
			WritableFont blueFont = new WritableFont(WritableFont.ARIAL,10);
			blueFont.setColour(Colour.BLUE);
			blueFormat.setFont(blueFont);
			if(qsList.size()>0){
				//����������Ϣ
				String bjmc = "";
				String bjmcTemp = "";
				String ch = "";
				boolean qshFlag = false;
				int col = 0;
				int row = 0;
				int rowTemp = 0;
				int qsMaxCws = 0;
				for(int i=0;i<qsList.size();i++){
					HashMap<String, String> qsMap = qsList.get(i);
					String qsh = qsMap.get("qsh");
					String chNew = qsMap.get("ch");
					String qsYllbmc = qsMap.get("yllbmc");
					boolean bjmcFlag = Integer.parseInt(qsMap.get("bjnum")) > 1;
					int ylnum = Integer.parseInt(qsMap.get("ylnum"));
					int chMaxcws = Integer.valueOf(qsMap.get("maxcws")); //¥�����λ��
					if(!ch.equalsIgnoreCase(chNew)){
						ch = chNew;
						col = 0; // ����
						if(i > 0){
							rowTemp = row;
						}
						row += 3 + chMaxcws; 
					}
					String qsMaxCwsTemp = xxtjDao.getMaxQsCws_zjjszyjsxy(lddm, ch, qsh);//�������λ��
					if(qsMaxCwsTemp != null){
						qsMaxCws = Integer.valueOf(qsMaxCwsTemp);
					}
					if(zsxxList.size()>0){
						//������λס����Ϣ
						int bjmcRowTemp = 0;
						for(int j=0;j<zsxxList.size();j++){
							HashMap<String, String> zsxxMap = zsxxList.get(j);
							int bjmcRow = rowTemp + Integer.valueOf(zsxxMap.get("cwh"));
							if(zsxxMap.get("qsh").equalsIgnoreCase(qsh)){
								bjmcTemp = zsxxMap.get("bjmc");
								String cellxm = zsxxMap.get("cellxm");
								if(bjmcTemp != null && !bjmcTemp.equals(bjmc)){
									bjmc = bjmcTemp;
								}
								if(bjmcFlag){
									bjmc = "�������";
									cellxm += " " + zsxxMap.get("bjmc");
								}
								if(!qshFlag){
									bjmcRowTemp = bjmcRow;
									int qshRow = 2 + rowTemp + chMaxcws;
									ws.addCell(new Label(col, qshRow, qsh));
									if(qsMaxCws != chMaxcws){
//										System.out.println("qsMaxcws:"+qsMaxCws+" chMaxCws:"+chMaxcws +" 1:"+(qshRow - 1 - (chMaxcws - qsMaxCws))+" 2:"+(qshRow - 1));
										ws.mergeCells(col, qshRow - 1 - (chMaxcws - qsMaxCws), col, qshRow - 1);
									}
									if(qsMaxCws == ylnum){
//										System.out.println("qsMaxcws:"+qsMaxCws+" ylnum:"+ylnum+" 1:"+(qshRow - 1 - ylnum)+" 2:"+(qshRow - 1));
										ws.mergeCells(col, qshRow - 1 - ylnum, col, qshRow - 1);
										bjmcRowTemp = qshRow - 1 - ylnum;
									}
//									System.out.println("ch: " + ch + " rowTemp:" + rowTemp + " row:" + row + " col:" + col + " rowNew:" + (qshRow) + " qsh:" + qsh);
								}
								qshFlag = true;
								Label labelXm = new Label(col, bjmcRow + 1, cellxm);
								if(zsxxMap.get("yllbmc") != null){
									labelXm.setString("");
									labelXm.setCellFormat(greenFormat);
								}
								ws.addCell(labelXm);
//								System.out.println("ch: " + ch + " rowTemp:" + rowTemp + " row:" + row + " col:" + col + " rowNew:" + (bjmcRow + 1) + " xm:" + zsxxMap.get("xm"));
							}else{
								qshFlag = false; // ����
							}
							if((j == zsxxList.size() - 1)){
								Label labelBjmc = new Label(col, bjmcRowTemp, bjmc);
								if(qsMaxCws == ylnum){
									labelBjmc.setString(qsYllbmc);
									labelBjmc.setCellFormat(greenCenterFormat);
								}else{
									labelBjmc.setCellFormat(blueFormat);
								}
								ws.addCell(labelBjmc);
//								System.out.println("ch: " + ch + " rowTemp:" + rowTemp + " row:" + row + " col:" + col + " rowNew:" + bjmcRowTemp + " bjmc:" + bjmc);
								bjmc = ""; // ����
							}
						}
					}
					col++;
				}
			}
			wwb.write();
			wwb.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * @throws SQLException 
	 * 
	 * @����:��֤����ѧ���Ƿ��û�����ѧ��
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2015-6-8 ����10:56:19
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param lddm
	 * @param qsh
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean CheckYhqx(List<HashMap<String, String>> qsxs, User user) throws SQLException{
		boolean yhqx = false;
		if(!"xx".equals(user.getUserStatus())){
			List<String> dbxx = xxtjDao.getDbxx(user.getUserName());
			for (HashMap qsxsMap : qsxs) {
				if(dbxx.contains(qsxsMap.get("bjdm"))){
					yhqx=true;
					break;
				}
			}
		 return yhqx;
		}else 
		{
			return true;
		}
	}
	
	/**
	 * 
	 * @����: ��ȡ�༶�б�(��������)
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-12-16 ����10:02:23
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getBjListforDgList(String[] lddm){
		return new XxtjDAO().getBjListforDgList( lddm);
	}
	
	/**
	 * 
	 * @����: ��ȡ�༶�б�(�������)
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-12-16 ����10:02:23
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getBjListforHhList(String[] lddm){
		return new XxtjDAO().getBjListforHhList( lddm);
	}
	
	/**
	 * 
	 * @����: ��ȡ¥����Ϣ
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-12-16 ����11:37:24
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param lddmf
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getLdmcXx(String[]lddm){
		return new XxtjDAO().getLdmcXx(lddm);
	}
	
	/**
	 * 
	 * @����: ������ʦ,����excel
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-12-19 ����09:58:01
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param param
	 * @param os
	 * @throws Exception
	 * void �������� 
	 * @throws
	 */
	public  void createWwb(String[] param,OutputStream os) throws Exception{
		WritableWorkbook wwb = Workbook.createWorkbook(os);
		/**
		 * ��ȡ����excel���ĺ�̨����
		 */
		//��ȡ¥������
		List<HashMap<String, String>> ldmcList = this.getLdmcXx(param);
		//��ȡ�������Ұ༶����
		List<HashMap<String, String>> dgqsList = this.getBjListforDgList(param);
		//��ȡ������Ұ༶����
		List<HashMap<String, String>> hhqsList = this.getBjListforHhList(param);
		/**
		 * ��ǰֻ̨֧�ֵ�ѡ��������ʱ�����Ƕ���ļ���������ʱ�������붼�����������������Ժ�ǰ̨֧�ֶ�����¼ѡ����ԭ�����������жϼ���
		 */
		/**
		 * ȡ��������ʽ
		 */
		WritableCellFormat fonthead =  this.getFontStyle("head");
		WritableCellFormat fontbodycenter = this.getFontStyle("bodycenter");
		WritableCellFormat fonttitle = this.getFontStyle("title");
		 WritableSheet ws = wwb.createSheet("¥����Ϣ����", 0);
		 
		 CellView cellView = new CellView();  
		 cellView.setAutosize(true); //�����Զ���С  
		 ws.setColumnView(0, cellView);
		 /**
		  * д�����
		  */
		 String titilemc = "";
		 if(!ldmcList.isEmpty()){
			 titilemc = ldmcList.get(0).get("ldmc");
		 }
		 Label labelTitle= new Label(0, 0, titilemc,fonttitle);
		 //�ϲ���Ԫ������Ϊ����������12��
		 ws.mergeCells(0, 0, 11, 0);
		 ws.addCell(labelTitle);
		 
		 /**
		  * ��ͷ
		  */
		 /**
		  * ��ͷ��һ�й̶���=ֱ�Ӽ�
		  */
		 
		 Label row2col1= new Label(0, 1, "�༶",fonthead);
		 Label row2col2= new Label(1, 1, "����",fonthead);
		 Label row2col3= new Label(2, 1, "��1��",fonthead);
		 Label row2col4= new Label(3, 1, "��2��",fonthead);
		 Label row2col5= new Label(4, 1, "��3��",fonthead);
		 Label row2col6= new Label(5, 1, "��4��",fonthead);
		 Label row2col7= new Label(6, 1, "��5��",fonthead);
		 Label row2col8= new Label(7, 1, "��6��",fonthead);
		 Label row2col9= new Label(8, 1, "��7��",fonthead);
		 Label row2col10= new Label(9, 1, "��8��",fonthead);
		 Label row2col11= new Label(10, 1, "����С��",fonthead);
		 Label row2col12= new Label(11, 1, "��ע",fonthead);
		 
		 ws.addCell(row2col1);
		 ws.addCell(row2col2);
		 ws.addCell(row2col3);
		 ws.addCell(row2col4);
		 ws.addCell(row2col5);
		 ws.addCell(row2col6);
		 ws.addCell(row2col7);
		 ws.addCell(row2col8);
		 ws.addCell(row2col9);
		 ws.addCell(row2col10);
		 ws.addCell(row2col11);
		 ws.addCell(row2col12);
		 //�ȴ�ӡ��������
		 int rowNum = 2;//�ӵ����п�ʼ���
		 for(int i = 0;i < dgqsList.size();i++){
			String[] qsh = dgqsList.get(i).get("qsh").split(";");
			String[] qshflag = dgqsList.get(i).get("qshflag").split(";");
			String bjdm = dgqsList.get(i).get("bjdm");
			String bjmc = dgqsList.get(i).get("bjmc");
//			String qsgs = dgqsList.get(i).get("qsgs");
			/**
			 * bzrxxList��ȡ
			 */
			List<HashMap<String, String>> bzrxxList = this.getBzrxx(new String[]{bjdm});
			//��������ֻ��һ���༶��ѧ�� �����԰�������Ϣ��Ψһ�ģ�����ȡ��
			if(bzrxxList.isEmpty()){
				continue;
			}
			HashMap<String, String> bzrxxMap = bzrxxList.get(0);
			 //����༶�е�Ԫ��
			 Label rowcol1= new Label(0,rowNum,bjmc+"\n"+bzrxxMap.get("xm")+"\n"+bzrxxMap.get("lxdh"),fontbodycenter); 
			 //�ϲ���Ԫ��
			 ws.mergeCells(0, rowNum, 0, rowNum+qsh.length-1);
			 ws.addCell(rowcol1);
			 //����ð༶��Ӧ�µ�ÿ�������Լ����Ҷ�Ӧ�Ĵ�λ
			 for (int j = 0; j < qsh.length; j++) {
				 //��ӡ����
				 String thisQsh = qshflag[j];
				 Label labelQsh = new Label(1, rowNum+j,thisQsh , fontbodycenter);
				 ws.addCell(labelQsh);
				 /**
				  * �����λ
				  */
				
				List<HashMap<String, String>> qsxxList = this.getqsxx(new String[]{qsh[j]});
				int rs = qsxxList.size();
			     for (int k = 0; k < rs; k++) {
			    	 Label labelCw = new Label(1+Integer.parseInt(qsxxList.get(k).get("cwh")), rowNum+j, qsxxList.get(k).get("xm"), fontbodycenter);
			    	 ws.addCell(labelCw);
				 }
			     //�������С�ƣ�λ���ǹ̶���
			     Label labelRsxj = new Label(10,rowNum+j,rs+"" , fontbodycenter);
				 ws.addCell(labelRsxj);
			 }
			 rowNum = rowNum+qsh.length;
		 }
		 //�ٴ�ӡ�������
		 for (int i = 0; i < hhqsList.size(); i++) {
				String qsh =  hhqsList.get(i).get("qsh");
				String qshflag =  hhqsList.get(i).get("qshflag");
				String[] bjdm = hhqsList.get(i).get("bjdm").split(";");
//				String[] bjmc = dgqsList.get(i).get("bjmc").split(";");
				List<HashMap<String, String>> bzrxxList = this.getBzrxx(bjdm);
				StringBuffer bjTitle = new StringBuffer();
				for (int j = 0; j < bzrxxList.size(); j++) {
					bjTitle.append(bzrxxList.get(j).get("bjmc")+"\n"+bzrxxList.get(j).get("xm")+"\n"+bzrxxList.get(j).get("lxdh"));
				}
				//����༶�е�Ԫ��
				 Label rowcol1= new Label(0,rowNum+i,bjTitle.toString(),fontbodycenter); 
				 //�ϲ���Ԫ��
				 ws.addCell(rowcol1);
				 Label labelQsh = new Label(1, rowNum+i,qshflag , fontbodycenter);
				 ws.addCell(labelQsh);
				 
				 /**
				  * �����λ
				  */
				
				List<HashMap<String, String>> qsxxList = this.getqsxx(new String[]{qsh});
				int rs = qsxxList.size();
			     for (int k = 0; k < rs; k++) {
			    	 Label labelCw = new Label(1+Integer.parseInt(qsxxList.get(k).get("cwh")), rowNum+i, qsxxList.get(k).get("xm"), fontbodycenter);
			    	 ws.addCell(labelCw);
				 }
			     //�������С�ƣ�λ���ǹ̶���
			     Label labelRsxj = new Label(10,rowNum+i,rs+"" , fontbodycenter);
				 ws.addCell(labelRsxj);
		 }
		 try{
			 wwb.write();
			 wwb.close();
		 }catch(Exception e){
				
		 }
	}
	
	
	public WritableCellFormat getFontStyle(String paras) throws WriteException{
		
		/** 
         * ���嵥Ԫ����ʽ 
         */ 
		if("title".equals(paras)){
			 WritableFont wf_title = new WritableFont(WritableFont.ARIAL, 20,  
             WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE,  
             jxl.format.Colour.BLACK); // �����ʽ ���� �»��� б�� ���� ��ɫ  
			 WritableCellFormat wcf_title = new WritableCellFormat(wf_title); // ��Ԫ����  
	         wcf_title.setAlignment(jxl.format.Alignment.CENTRE); // ���ö��뷽ʽ  
	         return wcf_title;
		}else if("head".equals(paras)){
		     WritableFont wf_head = new WritableFont(WritableFont.ARIAL, 16,  
		                WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE,  
		                jxl.format.Colour.BLACK); 
			  WritableCellFormat wcf_head = new WritableCellFormat(wf_head);   
		      wcf_head.setAlignment(jxl.format.Alignment.CENTRE);
		      wcf_head.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
		      return wcf_head;
		}else if("bodyright".equals(paras)){
			  WritableFont wf_table = new WritableFont(WritableFont.ARIAL, 14,  
		                WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE,  
		                jxl.format.Colour.BLACK); 
			  WritableCellFormat wcf_table = new WritableCellFormat(wf_table);   
	          //wcf_table.setBackground(jxl.format.Colour.BLACK);   
	          wcf_table.setAlignment(jxl.format.Alignment.RIGHT);  
	          return wcf_table;
		}else if("bodyleft".equals(paras)){
			  WritableFont wf_table = new WritableFont(WritableFont.ARIAL, 14,  
		                WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE,  
		                jxl.format.Colour.BLACK);   
			  WritableCellFormat wcf_table = new WritableCellFormat(wf_table);   
//			  wcf_table.setShrinkToFit(true);
	          wcf_table.setAlignment(jxl.format.Alignment.LEFT);
	          wcf_table.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
	          return wcf_table;
		}else if("bodycenter".equals(paras)){
			  WritableFont wf_table = new WritableFont(WritableFont.ARIAL, 14,  
		                WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE,  
		                jxl.format.Colour.BLACK);   
			  WritableCellFormat wcf_table = new WritableCellFormat(wf_table);   
//			  wcf_table.setShrinkToFit(true);
	          wcf_table.setAlignment(jxl.format.Alignment.CENTRE);
	          wcf_table.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
	          return wcf_table;
		}
     
        return null;
	}
	
	/**
	 * 
	 * @����:��ȡ��λ��Ϣ
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-12-16 ����11:21:01
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param qsh
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getqsxx(String[] qsh){
		return new XxtjDAO().getqsxx(qsh);
	}
	
	/**
	 * 
	 * @����: ��ȡ��������Ϣ
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-12-19 ����11:10:20
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getBzrxx(String[] bjdm){
		return new XxtjDAO().getBzrxx(bjdm);
	}
	
	/**
	 * @����������������
	 * @���ߣ�׿��[����:1391]
	 * @���ڣ�2017��3��7�� 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param datalist
	 * @param fileName
	 * @param template
	 * @return
	 * @throws Exception
	 * File ��������
	 */
	public File getHmcFile(String lddm,String defaultPhotoPath) throws Exception{
		String template="gygl_hmc_"+Base.xxdm+".xml";
		XsxxService xsxxService =new XsxxService();
		Map<String, Object> data = new HashMap<String, Object>();
		List<HashMap<String,String>> cwxx=xxtjDao.getCwxx(lddm);
		List<Object> list=new LinkedList<Object>();  //����ס����Ϣ��list��������õ�chmap����ÿ����Ϣ
		List<Object> rowlist=new LinkedList<Object>();  //����Ϣ ��list��������listʱ���õ�ÿ����map�����ò��ÿ������
		Map<String, Object> rowmap = new HashMap<String, Object>();  //����Ϣ��map.get("1")Ϊ���е�һ����Ϣ���ܹ�4���˵�������Ϣ
		Map<String, Object> chmap = new HashMap<String, Object>(); //������Ϣ��map.get("ch")Ϊ��ţ�map.get("rowlist")Ϊ rowlist
		HashMap<String, String> nullMap = new HashMap<String, String>();
		String ch="begin";
		int postionNum=1;
		for (HashMap<String,String>map:cwxx){
			map.put("photo", xsxxService.getPhotoBase64(map.get("xh"),defaultPhotoPath));
			if("begin".equals(ch)){
				postionNum=Integer.parseInt(map.get("position"));
				ch=map.get("ch");
				rowmap.put(map.get("position"), map);
			}else if(ch!=null&&ch.equals(map.get("ch"))){
				postionNum=Integer.parseInt(map.get("position"));
				if(postionNum==1){  //ͬ�㻻��ʱ����֮ǰ4���˵���Ϣ�Ž�һ��rowmap
					rowlist.add(rowmap);
					rowmap= new HashMap<String, Object>();
				}
				rowmap.put(map.get("position"), map);
			}else{
				//����ʱ�������п�λ�����Ϊ��map
				switch(postionNum){
					case 1:rowmap.put("2", nullMap);
					case 2:rowmap.put("3", nullMap);
					case 3:rowmap.put("0", nullMap);break;
				}
				rowlist.add(rowmap);
				//����ʱ��rowlist�Ѱ�����һ������rowmap������chmap
				chmap.put("listRow", rowlist);
				//����ʱ����һ��Ĳ��
				chmap.put("ch", ch);
				//����ʱ���ò���Ϣ( chmap )����ס����Ϣlist
				list.add(chmap);
				//�������
				rowmap= new HashMap<String, Object>();
				rowlist=new LinkedList<Object>();
				chmap= new HashMap<String, Object>(); 
				
				ch=map.get("ch");
				rowmap.put(map.get("position"), map);
				postionNum=Integer.parseInt(map.get("position"));
			}
		}
		switch(postionNum){
			case 1:rowmap.put( "2", nullMap);
			case 2:rowmap.put( "3", nullMap);
			case 3:rowmap.put( "0", nullMap);break;
		}
		//δ����ѭ��������£�����¥����ѧ��
		if("begin".equals(ch)){
			rowmap.put( "1", nullMap);
			ch=null;
		}
		rowlist.add(rowmap);
		//����ʱ��rowlist�Ѱ�����һ������rowmap������chmap
		chmap.put("listRow", rowlist);
		//����ʱ����һ��Ĳ��
		chmap.put("ch", ch);
		//����ʱ���ò���Ϣ( chmap )����ס����Ϣlist
		list.add(chmap);
		data.put("list", list);
		File file = FreeMarkerUtil.getWordFile(data, "classpath:templates/gygl", template, "��Ԣ¥������_"+lddm);
		return file;
	}
	
	/**
	 * ��ȡУ���б�
	 * @return
	 */
	public List<HashMap<String,String>>getXqxx(){
		return xxtjDao.getXqxx();
	}

}
