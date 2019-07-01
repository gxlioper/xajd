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
	 * 楼栋寝室床位统计
	 * @param model
	 * @return
	 */
	public List<HashMap<String, String>> getLdqscwTj(XxtjModel model){
		return xxtjDao.getLdqscwTj_new(model);
	}
	
	/**
	 * 获取某个楼栋单独详细统计信息
	 * @param lddm
	 * @return
	 */
	public LdModel getLdDetailTj(String lddm,User user){
		String[] output = new String[]{"lddm", "ldmc", "qsh","qsxb", "ch", "xydm", "xymc", "nj", "cws", "yzr", "zszt", "sfhz","sfbl","ckqs"}; 
		
		
		List<HashMap<String, String>> rs = xxtjDao.getLdxxOne(lddm, output,user);
		
		// 楼栋model
		LdModel ldModel = new LdModel();
		
		// 获取楼栋信息
		LdglServices ldglService = new LdglServices();
		LdglModel ldglModel = new LdglModel();
		ldglModel.setLddm(lddm);
		
		Map<String, String> ldMap = ldglService.getLdxxOne(ldglModel);
		
		ldModel.setLddm(lddm);
		ldModel.setLdmc(ldMap.get("ldmc"));
		ldModel.setLdxb(ldMap.get("ldxb"));
		
		// 层数map：层号为key，寝室集合List为value
		Map<Integer, List<QsModel>> csMap = ldModel.getCsMap();
		
		for(Map<String, String> qsMap : rs){
			// 层数
			Integer ch = -Integer.parseInt(qsMap.get("ch"));//该处加负号主要是为了进行反向排序，对应的页面还要加负号进行恢复
			// 若存在寝室楼层取存在的，不存在新建楼层List
			List<QsModel> csList = csMap.containsKey(ch) ? csMap.get(ch) : new ArrayList<QsModel>();
			// 寝室实体Model
			QsModel qsModel = new QsModel(qsMap);
			csList.add(qsModel);
			csMap.put(ch, csList);
		}
		
		return ldModel;
	}
	
	/**
	 * 信息统计单个寝室 
	 * @param lddm
	 * @param qsh
	 * @return
	 */
	public List<HashMap<String, String>> xxtjForQs(String lddm, String qsh){
		
		return xxtjDao.xxtjForQs(lddm, qsh);
	}
	
	/**
	 * 导出住宿信息
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
		//广东建设职业技术学院
		if(Globals.XXDM_GDJSZYJSXY.equalsIgnoreCase(Base.xxdm)){
			xxtjDczsxx_gdjs(lddm, os);
			return;
		}else if(Globals.XXDM_ZJJSZYJSXY.equalsIgnoreCase(Base.xxdm)){
			xxtjDczsxx_zjjszyjsxy(lddm, os);
			return;
		}
		WritableWorkbook wwb = Workbook.createWorkbook(os);
		WritableSheet ws = wwb.createSheet("公寓住宿情况", 0);
		
		//最大床位数
		String num = xxtjDao.getMaxCws(lddm);
		//表格表头内容
		String[] bt = new String[Integer.valueOf(num)+2];
		bt[0] = "寝室号"; bt[1] = "寝室性别";
		for(int i=2;i<Integer.valueOf(num)+2;i++){
			bt[i] = String.valueOf(i-1)+"号床";
		}
		//楼栋信息
		HashMap<String,String> ldxx = xxtjDao.getLdxx(lddm);
		//寝室信息list
		List<HashMap<String,String>> qsList = xxtjDao.getQsList(lddm);
		//床位住宿信息list
		List<HashMap<String,String>> zsxxList = xxtjDao.getZsxxList(lddm);
		
		try {
			//设置打印方式，纸张大小，页边距
			ws.setPageSetup(PageOrientation.LANDSCAPE.LANDSCAPE,PaperSize.A4,0.5d,0.5d);
			WritableCellFormat wcFormat = new WritableCellFormat();
			WritableFont font = new WritableFont(WritableFont.ARIAL,16);
			font.setBoldStyle(WritableFont.BOLD);
			wcFormat.setFont(font);
			wcFormat.setAlignment(Alignment.CENTRE);
			wcFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
			Label titleCell;
			
			titleCell = new Label(0, 0, ldxx.get("ldmc")+"住宿情况汇总");
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
				//遍历寝室信息
				for(int i=0;i<qsList.size();i++){
					ws.mergeCells(0, i*x+2, 0, i*x+y);
					titleCell = new Label(0,i*x+2,qsList.get(i).get("qsh"));
					ws.addCell(titleCell);
					ws.mergeCells(1, i*x+2, 1, i*x+y);
					titleCell = new Label(1,i*x+2,qsList.get(i).get("qsxb"));
					ws.addCell(titleCell);
					if(zsxxList.size()>0){
						//遍历床位住宿信息
						String[] zghs=null;
						String[] xms=null;
						for(int j=0;j<zsxxList.size();j++){
							String bzr="";
							if(zsxxList.get(j).get("qsh").equalsIgnoreCase(qsList.get(i).get("qsh"))){
								int k =1+ Integer.valueOf(zsxxList.get(j).get("cwh"));
								titleCell = new Label(k,i*x+2,"学号："+zsxxList.get(j).get("xh"));
								ws.addCell(titleCell);
								titleCell = new Label(k,i*x+3,"姓名："+zsxxList.get(j).get("xm"));
								ws.addCell(titleCell);
								titleCell = new Label(k,i*x+4,"班级："+zsxxList.get(j).get("bjmc"));
								ws.addCell(titleCell);
								if(zsxxList.get(j).get("bzrzgh")!=null&&""!=zsxxList.get(j).get("bzrzgh")){
									zghs = zsxxList.get(j).get("bzrzgh").split(";");
									xms = zsxxList.get(j).get("bzrxm").split(";");
									for (int i1 = 0; i1 < zghs.length; i1++) {
										bzr+=xms[i1]+" ";
									}
								}
								titleCell = new Label(k,i*x+5,"班主任："+bzr);
								ws.addCell(titleCell);
								if("13798".equalsIgnoreCase(Base.xxdm)) {
									titleCell = new Label(k,i*x+6,"年级："+zsxxList.get(j).get("nj"));
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
		//表格表头内容
		//楼栋信息
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
		
		//楼层信息list
		List<HashMap<String,String>> lcList = xxtjDao.getLcxx(ldxxList.get(s).get("lddm"));
 		List<HashMap<String,String>> qsList = xxtjDao.getQsList(ldxxList.get(s).get("lddm"));
		//床位住宿信息list
		List<HashMap<String,String>> zsxxList = xxtjDao.getZsxxList(ldxxList.get(s).get("lddm"));
		
		try {
			//设置打印方式，纸张大小，页边距
			ws.setPageSetup(PageOrientation.LANDSCAPE.LANDSCAPE,PaperSize.A4,0.5d,0.5d);
			WritableCellFormat wcFormat = new WritableCellFormat();
			wcFormat=ExcelMethods.getWcf(WritableFont.ARIAL, 10, false,
					Alignment.CENTRE, VerticalAlignment.CENTRE, true, Border.ALL);
			ExcelMB ex = new ExcelMB();
			ws.mergeCells(0, 0, 7, 0);
			ex.printToOneCell_multy(ws, ldxx.get("ldmc")+"住宿情况汇总", 0, 0, 15, true,
					Alignment.CENTRE, VerticalAlignment.CENTRE, true, 700,
					Border.ALL);
			
			if(qsList.size()>0){
				//遍历寝室信息
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
								//遍历床位住宿信息
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
	 * 导出住宿信息_广东建设职业技术学院
	 * @param lddm
	 * @param os
	 * @throws Exception
	 */
	public void xxtjDczsxx_gdjs(String lddm,OutputStream os)throws Exception{
		WritableWorkbook wwb = Workbook.createWorkbook(os);
		WritableSheet ws = wwb.createSheet("公寓住宿情况", 0);
		
		//最大床位数
		String num = xxtjDao.getMaxCws(lddm);
		//表格表头内容
		String[] bt = new String[Integer.valueOf(num)+3];
		bt[0] = "寝室号"; bt[1] = "寝室性别";
		bt[2] = "班级";
		for(int i=3;i<Integer.valueOf(num)+3;i++){
			bt[i] = String.valueOf(i-2)+"号床";
		}
		//楼栋信息
		HashMap<String,String> ldxx = xxtjDao.getLdxx(lddm);
		//寝室信息list
		List<HashMap<String,String>> qsList = xxtjDao.getQsList_gdjs(lddm);
		//床位住宿信息list
		List<HashMap<String,String>> zsxxList = xxtjDao.getZsxxList(lddm);
		
		try {
			//设置打印方式，纸张大小，页边距
			ws.setPageSetup(PageOrientation.LANDSCAPE.LANDSCAPE,PaperSize.A4,0.5d,0.5d);
			WritableCellFormat wcFormat = new WritableCellFormat();
			WritableFont font = new WritableFont(WritableFont.ARIAL,16);
			font.setBoldStyle(WritableFont.BOLD);
			wcFormat.setFont(font);
			wcFormat.setAlignment(Alignment.CENTRE);
			wcFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
			Label titleCell;
			
			titleCell = new Label(0, 0, ldxx.get("ldmc")+"住宿情况汇总");
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
				//遍历寝室信息
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
						//遍历床位住宿信息
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
	 * 导出住宿信息_浙江建设职业技术学院
	 * @param lddm
	 * @param os
	 * @throws Exception
	 */
	public void xxtjDczsxx_zjjszyjsxy(String lddm,OutputStream os)throws Exception{
		WritableWorkbook wwb = Workbook.createWorkbook(os);
		WritableSheet ws = wwb.createSheet("公寓住宿情况", 0);
		
		//最大寝室数
		String maxQss = xxtjDao.getMaxQss_zjjszyjsxy(lddm);
		//楼栋信息
		HashMap<String,String> ldxx = xxtjDao.getLdxx(lddm);
		//寝室信息list
		List<HashMap<String,String>> qsList = xxtjDao.getQsList_zjjszyjsxy(lddm);
		//床位住宿信息list
		List<HashMap<String,String>> zsxxList = xxtjDao.getZsxxList_zjjszyjsxy(lddm);
		
		try {
			//设置打印方式，纸张大小，页边距
			ws.setPageSetup(PageOrientation.LANDSCAPE.LANDSCAPE,PaperSize.A4,0.5d,0.5d);
			WritableCellFormat wcFormat = new WritableCellFormat();
			WritableFont font = new WritableFont(WritableFont.ARIAL,16);
			font.setBoldStyle(WritableFont.BOLD);
			wcFormat.setFont(font);
			wcFormat.setAlignment(Alignment.CENTRE);
			wcFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
			Label titleCell;
			
			titleCell = new Label(0, 0, ldxx.get("ldmc")+"住宿情况汇总");
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
				//遍历寝室信息
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
					int chMaxcws = Integer.valueOf(qsMap.get("maxcws")); //楼层最大床位数
					if(!ch.equalsIgnoreCase(chNew)){
						ch = chNew;
						col = 0; // 重置
						if(i > 0){
							rowTemp = row;
						}
						row += 3 + chMaxcws; 
					}
					String qsMaxCwsTemp = xxtjDao.getMaxQsCws_zjjszyjsxy(lddm, ch, qsh);//寝室最大床位数
					if(qsMaxCwsTemp != null){
						qsMaxCws = Integer.valueOf(qsMaxCwsTemp);
					}
					if(zsxxList.size()>0){
						//遍历床位住宿信息
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
									bjmc = "混合寝室";
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
								qshFlag = false; // 重置
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
								bjmc = ""; // 重置
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
	 * @描述:验证寝室学生是否含用户所带学生
	 * @作者：夏夏[工号：1104]
	 * @日期：2015-6-8 上午10:56:19
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param lddm
	 * @param qsh
	 * @return
	 * boolean 返回类型 
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
	 * @描述: 获取班级列表(单独寝室)
	 * @作者：yxy[工号：1206]
	 * @日期：2016-12-16 上午10:02:23
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getBjListforDgList(String[] lddm){
		return new XxtjDAO().getBjListforDgList( lddm);
	}
	
	/**
	 * 
	 * @描述: 获取班级列表(混合寝室)
	 * @作者：yxy[工号：1206]
	 * @日期：2016-12-16 上午10:02:23
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getBjListforHhList(String[] lddm){
		return new XxtjDAO().getBjListforHhList( lddm);
	}
	
	/**
	 * 
	 * @描述: 获取楼栋信息
	 * @作者：yxy[工号：1206]
	 * @日期：2016-12-16 上午11:37:24
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param lddmf
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getLdmcXx(String[]lddm){
		return new XxtjDAO().getLdmcXx(lddm);
	}
	
	/**
	 * 
	 * @描述: 宁波技师,导出excel
	 * @作者：yxy[工号：1206]
	 * @日期：2016-12-19 上午09:58:01
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param param
	 * @param os
	 * @throws Exception
	 * void 返回类型 
	 * @throws
	 */
	public  void createWwb(String[] param,OutputStream os) throws Exception{
		WritableWorkbook wwb = Workbook.createWorkbook(os);
		/**
		 * 获取导出excel表格的后台数据
		 */
		//获取楼栋名称
		List<HashMap<String, String>> ldmcList = this.getLdmcXx(param);
		//获取单个寝室班级集合
		List<HashMap<String, String>> dgqsList = this.getBjListforDgList(param);
		//获取混合寝室班级集合
		List<HashMap<String, String>> hhqsList = this.getBjListforHhList(param);
		/**
		 * 因前台只支持单选，所以暂时不考虑多个文件导出，暂时后续代码都按单个导出处理，如以后前台支持多条记录选择，在原来基础上做判断即可
		 */
		/**
		 * 取出字体样式
		 */
		WritableCellFormat fonthead =  this.getFontStyle("head");
		WritableCellFormat fontbodycenter = this.getFontStyle("bodycenter");
		WritableCellFormat fonttitle = this.getFontStyle("title");
		 WritableSheet ws = wwb.createSheet("楼栋信息导出", 0);
		 
		 CellView cellView = new CellView();  
		 cellView.setAutosize(true); //设置自动大小  
		 ws.setColumnView(0, cellView);
		 /**
		  * 写入标题
		  */
		 String titilemc = "";
		 if(!ldmcList.isEmpty()){
			 titilemc = ldmcList.get(0).get("ldmc");
		 }
		 Label labelTitle= new Label(0, 0, titilemc,fonttitle);
		 //合并单元格列数为表格的总列数12列
		 ws.mergeCells(0, 0, 11, 0);
		 ws.addCell(labelTitle);
		 
		 /**
		  * 表头
		  */
		 /**
		  * 表头第一行固定的=直接加
		  */
		 
		 Label row2col1= new Label(0, 1, "班级",fonthead);
		 Label row2col2= new Label(1, 1, "寝室",fonthead);
		 Label row2col3= new Label(2, 1, "床1上",fonthead);
		 Label row2col4= new Label(3, 1, "床2上",fonthead);
		 Label row2col5= new Label(4, 1, "床3下",fonthead);
		 Label row2col6= new Label(5, 1, "床4上",fonthead);
		 Label row2col7= new Label(6, 1, "床5下",fonthead);
		 Label row2col8= new Label(7, 1, "床6上",fonthead);
		 Label row2col9= new Label(8, 1, "床7下",fonthead);
		 Label row2col10= new Label(9, 1, "床8上",fonthead);
		 Label row2col11= new Label(10, 1, "人数小计",fonthead);
		 Label row2col12= new Label(11, 1, "备注",fonthead);
		 
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
		 //先打印单独寝室
		 int rowNum = 2;//从第三行开始输出
		 for(int i = 0;i < dgqsList.size();i++){
			String[] qsh = dgqsList.get(i).get("qsh").split(";");
			String[] qshflag = dgqsList.get(i).get("qshflag").split(";");
			String bjdm = dgqsList.get(i).get("bjdm");
			String bjmc = dgqsList.get(i).get("bjmc");
//			String qsgs = dgqsList.get(i).get("qsgs");
			/**
			 * bzrxxList获取
			 */
			List<HashMap<String, String>> bzrxxList = this.getBzrxx(new String[]{bjdm});
			//单独寝室只有一个班级的学生 ，所以班主任信息是唯一的，单独取出
			if(bzrxxList.isEmpty()){
				continue;
			}
			HashMap<String, String> bzrxxMap = bzrxxList.get(0);
			 //输出班级列单元格
			 Label rowcol1= new Label(0,rowNum,bjmc+"\n"+bzrxxMap.get("xm")+"\n"+bzrxxMap.get("lxdh"),fontbodycenter); 
			 //合并单元格
			 ws.mergeCells(0, rowNum, 0, rowNum+qsh.length-1);
			 ws.addCell(rowcol1);
			 //输出该班级对应下的每个寝室以及寝室对应的床位
			 for (int j = 0; j < qsh.length; j++) {
				 //打印寝室
				 String thisQsh = qshflag[j];
				 Label labelQsh = new Label(1, rowNum+j,thisQsh , fontbodycenter);
				 ws.addCell(labelQsh);
				 /**
				  * 输出床位
				  */
				
				List<HashMap<String, String>> qsxxList = this.getqsxx(new String[]{qsh[j]});
				int rs = qsxxList.size();
			     for (int k = 0; k < rs; k++) {
			    	 Label labelCw = new Label(1+Integer.parseInt(qsxxList.get(k).get("cwh")), rowNum+j, qsxxList.get(k).get("xm"), fontbodycenter);
			    	 ws.addCell(labelCw);
				 }
			     //输出人数小计，位置是固定的
			     Label labelRsxj = new Label(10,rowNum+j,rs+"" , fontbodycenter);
				 ws.addCell(labelRsxj);
			 }
			 rowNum = rowNum+qsh.length;
		 }
		 //再打印混合寝室
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
				//输出班级列单元格
				 Label rowcol1= new Label(0,rowNum+i,bjTitle.toString(),fontbodycenter); 
				 //合并单元格
				 ws.addCell(rowcol1);
				 Label labelQsh = new Label(1, rowNum+i,qshflag , fontbodycenter);
				 ws.addCell(labelQsh);
				 
				 /**
				  * 输出床位
				  */
				
				List<HashMap<String, String>> qsxxList = this.getqsxx(new String[]{qsh});
				int rs = qsxxList.size();
			     for (int k = 0; k < rs; k++) {
			    	 Label labelCw = new Label(1+Integer.parseInt(qsxxList.get(k).get("cwh")), rowNum+i, qsxxList.get(k).get("xm"), fontbodycenter);
			    	 ws.addCell(labelCw);
				 }
			     //输出人数小计，位置是固定的
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
         * 定义单元格样式 
         */ 
		if("title".equals(paras)){
			 WritableFont wf_title = new WritableFont(WritableFont.ARIAL, 20,  
             WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE,  
             jxl.format.Colour.BLACK); // 定义格式 字体 下划线 斜体 粗体 颜色  
			 WritableCellFormat wcf_title = new WritableCellFormat(wf_title); // 单元格定义  
	         wcf_title.setAlignment(jxl.format.Alignment.CENTRE); // 设置对齐方式  
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
	 * @描述:获取床位信息
	 * @作者：yxy[工号：1206]
	 * @日期：2016-12-16 上午11:21:01
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param qsh
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getqsxx(String[] qsh){
		return new XxtjDAO().getqsxx(qsh);
	}
	
	/**
	 * 
	 * @描述: 获取班主任信息
	 * @作者：yxy[工号：1206]
	 * @日期：2016-12-19 上午11:10:20
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getBzrxx(String[] bjdm){
		return new XxtjDAO().getBzrxx(bjdm);
	}
	
	/**
	 * @描述：导出花名册
	 * @作者：卓耐[工号:1391]
	 * @日期：2017年3月7日 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param datalist
	 * @param fileName
	 * @param template
	 * @return
	 * @throws Exception
	 * File 返回类型
	 */
	public File getHmcFile(String lddm,String defaultPhotoPath) throws Exception{
		String template="gygl_hmc_"+Base.xxdm+".xml";
		XsxxService xsxxService =new XsxxService();
		Map<String, Object> data = new HashMap<String, Object>();
		List<HashMap<String,String>> cwxx=xxtjDao.getCwxx(lddm);
		List<Object> list=new LinkedList<Object>();  //所有住宿信息的list。遍历后得到chmap，即每层信息
		List<Object> rowlist=new LinkedList<Object>();  //行信息 的list，遍历该list时，得到每个行map，即该层的每行数据
		Map<String, Object> rowmap = new HashMap<String, Object>();  //行信息，map.get("1")为该行第一个信息，总共4个人的寝室信息
		Map<String, Object> chmap = new HashMap<String, Object>(); //整层信息，map.get("ch")为层号，map.get("rowlist")为 rowlist
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
				if(postionNum==1){  //同层换行时，将之前4个人的信息放进一个rowmap
					rowlist.add(rowmap);
					rowmap= new HashMap<String, Object>();
				}
				rowmap.put(map.get("position"), map);
			}else{
				//换层时，将该行空位置填充为空map
				switch(postionNum){
					case 1:rowmap.put("2", nullMap);
					case 2:rowmap.put("3", nullMap);
					case 3:rowmap.put("0", nullMap);break;
				}
				rowlist.add(rowmap);
				//换层时，rowlist已包含上一层所有rowmap，放入chmap
				chmap.put("listRow", rowlist);
				//换层时，上一层的层号
				chmap.put("ch", ch);
				//换层时将该层信息( chmap )放入住宿信息list
				list.add(chmap);
				//清空数据
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
		//未进入循环的情况下，即该楼栋无学生
		if("begin".equals(ch)){
			rowmap.put( "1", nullMap);
			ch=null;
		}
		rowlist.add(rowmap);
		//换层时，rowlist已包含上一层所有rowmap，放入chmap
		chmap.put("listRow", rowlist);
		//换层时，上一层的层号
		chmap.put("ch", ch);
		//换层时将该层信息( chmap )放入住宿信息list
		list.add(chmap);
		data.put("list", list);
		File file = FreeMarkerUtil.getWordFile(data, "classpath:templates/gygl", template, "公寓楼花名册_"+lddm);
		return file;
	}
	
	/**
	 * 获取校区列表
	 * @return
	 */
	public List<HashMap<String,String>>getXqxx(){
		return xxtjDao.getXqxx();
	}

}
