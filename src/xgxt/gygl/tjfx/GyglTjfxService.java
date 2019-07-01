package xgxt.gygl.tjfx;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.util.MessageResources;

import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import xgxt.action.Base;
import xgxt.DAO.ExcelMB;
import xgxt.gygl.GyglCommService;
import xgxt.utils.ExcelMethods;
import xgxt.utils.GetTime;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 公寓管理_统计分析_Service类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author zhanghui
 * @version 1.0
 */

public class GyglTjfxService extends GyglCommService {

	GyglTjfxDAO dao = new GyglTjfxDAO();
	

	//==========================start=====*寝室床位统计*=====start==============================//
	/**
	 * 获取寝室床位统计列表
	 * @param myForm
	 * @throws Exception
	 */
	public List<String[]> getQscwtjList(GyglTjfxForm myForm,HttpServletRequest request) throws Exception {
		List<String[]> rsList = new ArrayList<String[]>();		
		
		List<HashMap<String,String>> list = dao.getQscwtjListByld(myForm);
		List<HashMap<String,String>> ldlist = dao.getLdlist(myForm);
		List<HashMap<String,String>> xylist = dao.getXylist(myForm,request);
				
		if(ldlist != null && ldlist.size()>0){	
			//第一层循环：楼栋
			for(int i=0; i<ldlist.size();i++){
				List<String> rs = new ArrayList<String>();
				HashMap<String,String> ld = ldlist.get(i);
				String lddm = ld.get("lddm");
				rs.add(ld.get("xqmc"));
				rs.add(ld.get("yqmc"));
				rs.add(ld.get("ldmc"));
				rs.add(ld.get("cwnum"));	
				rs.add(ld.get("qsnum"));
				int wfpcwnum = Integer.parseInt(ld.get("cwnum")) - Integer.parseInt(ld.get("yfpcwnum"));
				int wfpqsnum = Integer.parseInt(ld.get("qsnum")) - Integer.parseInt(ld.get("yfpqsnum"));
				rs.add(String.valueOf(wfpcwnum));	//未分配寝室的床位总数
				rs.add(String.valueOf(wfpqsnum));	//未分配寝室数
				int xyyfp = 0;
				int xywfp = 0;
				int xybkfp = 0;
				int xycw  = 0;
				int xyqs  = 0;
				
				if(xylist != null && xylist.size()>0){
					//第二层循环：学院
					for(int k=0;k<xylist.size();k++){
						HashMap<String,String> xy = xylist.get(k);
						
						boolean a =true;	//标记学院是否有该楼栋的床位
						if(list != null && list.size()>0){	
							//第三层循环：查询结果集中楼栋、学院对应的房间数
							for(int j=0; j<list.size();j++){
								HashMap<String,String> tj = list.get(j);
								if(xy.get("xydm").equalsIgnoreCase(tj.get("xydm"))
										&& lddm.equalsIgnoreCase(tj.get("lddm"))){
									if(!"yes".equalsIgnoreCase(tj.get("yy"))){
										rs.add(tj.get("yfp"));
										rs.add(tj.get("wfp"));
										rs.add(tj.get("bkfp"));
										rs.add(tj.get("cwnum"));
										rs.add(tj.get("qsnum"));
										//楼栋中学院各数值的总计
										xyyfp += Integer.parseInt(tj.get("yfp"));
										xywfp += Integer.parseInt(tj.get("wfp"));
										xybkfp += Integer.parseInt(tj.get("bkfp"));
										xycw += Integer.parseInt(tj.get("cwnum"));
										xyqs += Integer.parseInt(tj.get("qsnum"));
																	
										tj.put("yy", "yes");
										a =false;
										break;
									}
								}						
							}
						}
						if(a){
							rs.add("0");
							rs.add("0");
							rs.add("0");
							rs.add("0");
							rs.add("0");
						}
						
					}
				}
				rs.add(String.valueOf(xyyfp));
				rs.add(String.valueOf(xywfp));
				rs.add(String.valueOf(xybkfp));
				rs.add(String.valueOf(xycw));
				rs.add(String.valueOf(xyqs));
				rsList.add(rs.toArray(new String[]{}));
			}	
		}				
		
		return rsList;
	}
	
	/**
	 * 获取寝室床位统计总计列表
	 * @param myForm
	 * @throws Exception
	 */
	public List<String[]> getQscwtjZjList(GyglTjfxForm myForm,HttpServletRequest request) throws Exception {
		List<String[]> rsList = new ArrayList<String[]>();

		List<HashMap<String,String>> list = dao.getQscwtjListByxb(myForm);
		List<HashMap<String,String>> xblist = dao.getXblist(myForm);
		List<HashMap<String,String>> xylist = dao.getXylist(myForm,request);
		
		if(xblist != null && xblist.size()>0){	
			//第一层循环：性别
			for(int i=0; i<xblist.size();i++){
				List<String> rs = new ArrayList<String>();
				HashMap<String,String> xb = xblist.get(i);
				String xbmc = xb.get("xb");
				rs.add("总计("+xb.get("xb")+")");
				rs.add(xb.get("cwnum"));	
				rs.add(xb.get("qsnum"));
				int wfpcwnum = Integer.parseInt(xb.get("cwnum")) - Integer.parseInt(xb.get("yfpcwnum"));
				int wfpqsnum = Integer.parseInt(xb.get("qsnum")) - Integer.parseInt(xb.get("yfpqsnum"));
				rs.add(String.valueOf(wfpcwnum));	//未分配寝室的床位总数
				rs.add(String.valueOf(wfpqsnum));	//未分配寝室数			
				int xyyfp = 0;
				int xywfp = 0;
				int xybkfp = 0;
				int xycw  = 0;
				int xyqs  = 0;
				
				if(xylist != null && xylist.size()>0){
					//第二层循环：学院
					for(int k=0;k<xylist.size();k++){
						HashMap<String,String> xy = xylist.get(k);
						
						boolean a =true;	//标记学院是否有该楼栋的床位
						if(list != null && list.size()>0){	
							//第三层循环：查询结果集中楼栋、学院对应的房间数
							for(int j=0; j<list.size();j++){
								HashMap<String,String> tj = list.get(j);
								if(xy.get("xydm").equalsIgnoreCase(tj.get("xydm"))
										&& xbmc.equalsIgnoreCase(tj.get("xb"))){
									if(!"yes".equalsIgnoreCase(tj.get("yy"))){
										rs.add(tj.get("yfp"));
										rs.add(tj.get("wfp"));
										rs.add(tj.get("bkfp"));
										rs.add(tj.get("cwnum"));
										rs.add(tj.get("qsnum"));
										
										//楼栋中学院各数值的总计
										xyyfp += Integer.parseInt(tj.get("yfp"));
										xywfp += Integer.parseInt(tj.get("wfp"));
										xybkfp += Integer.parseInt(tj.get("bkfp"));
										xycw += Integer.parseInt(tj.get("cwnum"));
										xyqs += Integer.parseInt(tj.get("qsnum"));
																	
										tj.put("yy", "yes");
										a =false;
										break;
									}
								}						
							}
						}
						if(a){
							rs.add("0");
							rs.add("0");
							rs.add("0");
							rs.add("0");
							rs.add("0");
						}
						
					}
				}
				rs.add(String.valueOf(xyyfp));
				rs.add(String.valueOf(xywfp));
				rs.add(String.valueOf(xybkfp));
				rs.add(String.valueOf(xycw));
				rs.add(String.valueOf(xyqs));
				rsList.add(rs.toArray(new String[]{}));
			}				
		}		

		if(rsList != null && rsList.size()>0){	
			List<String> rs = new ArrayList<String>();
			rs.add("总计");
			String[] xbtj1 = rsList.get(0);
			String[] xbtj2 = rsList.get(1);
			
			for(int i=1;i<xbtj1.length;i++){
				int zj = Integer.parseInt(xbtj1[i])+Integer.parseInt(xbtj2[i]);
				rs.add(String.valueOf(zj));
			}

			rsList.add(rs.toArray(new String[]{}));
		}
		return rsList;
	}
	
	/**
	 * 获取寝室床位统计表头部门列表
	 * @param myForm
	 * @throws Exception
	 */
	public List<HashMap<String,String>> getXylist(GyglTjfxForm myForm,
			HttpServletRequest request) throws Exception{
		return dao.getXylist(myForm,request);
	}
	
	/**
	 * 导出数据excel
	 * @param myForm
	 * @param message
	 * @param request
	 * @param response
	 */
	public void printQscwtj(GyglTjfxForm myForm,MessageResources message,
			HttpServletRequest request,HttpServletResponse response)
			throws Exception,IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		String xqdm = message.getMessage("lable.xiaoqu");
		String yqdm = message.getMessage("lable.yuanqu");
		String lddm = message.getMessage("lable.ld");
		String title = "楼栋寝室床位统计表";
		
		List<String[]> rs= getQscwtjList(myForm,request);
		List<String[]> zj=  getQscwtjZjList(myForm,request);
		List<HashMap<String,String>> xylist= getXylist(myForm,request);
		
		
		int xynum = xylist.size();
		int ldnum = Integer.parseInt(request.getAttribute("ldnum").toString());
		String czyq = request.getAttribute("czyq").toString();
		String czxq = request.getAttribute("czxq").toString();
		
		
		WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(response.getOutputStream());
		WritableCellFormat wcfTytle = new WritableCellFormat();
		WritableSheet ws = wwb.createSheet(title+"("+GetTime.getSystemTime()+")", 0);
		try {			
			
			//标题
			wcfTytle = ExcelMB.getWritableCellFormat(16,true, Alignment.CENTRE, VerticalAlignment.CENTRE,Border.ALL);// 单元格样式
			ws.mergeCells(0, 0,(xynum+1)*5+ldnum-1, 0);
			ws.addCell(new Label(0,0,title,wcfTytle));
			
			//表头
			wcfTytle = ExcelMB.getWritableCellFormat(10,true, Alignment.CENTRE, VerticalAlignment.CENTRE,Border.ALL);// 单元格样式
			//第一列
			ws.mergeCells(0, 1,ldnum-1, 1);
			ws.addCell(new Label(0,1,lddm,wcfTytle));
			for(int i = 0;i<xynum;i++){
				ws.mergeCells(i*5+ldnum,1,i*5+ldnum+5-1, 1);
				ws.addCell(new Label(i*5+ldnum,1,xylist.get(i).get("xymc"),wcfTytle));
			}
			ws.mergeCells(ldnum+xynum*5,1,ldnum+5+xynum*5-1, 1);
			ws.addCell(new Label(ldnum+xynum*5,1,Base.YXPZXY_KEY+"总计",wcfTytle));			
			//第二列
			if(ldnum == 7){
				ws.addCell(new Label(0,2,xqdm,wcfTytle));
				ws.addCell(new Label(1,2,yqdm,wcfTytle));
				ws.addCell(new Label(2,2,lddm,wcfTytle));
				ws.addCell(new Label(3,2,"总床位数",wcfTytle));
				ws.addCell(new Label(4,2,"总寝室数",wcfTytle));
				ws.addCell(new Label(5,2,"未分配寝室床位数",wcfTytle));
				ws.addCell(new Label(6,2,"未分配寝室数",wcfTytle));
			} else if(ldnum == 6){
				if("是".equalsIgnoreCase(czxq)){
					ws.addCell(new Label(0,2,xqdm,wcfTytle));
				}
				if("是".equalsIgnoreCase(czyq)){
					ws.addCell(new Label(0,2,yqdm,wcfTytle));
				}
				ws.addCell(new Label(1,2,lddm,wcfTytle));
				ws.addCell(new Label(2,2,"总床位数",wcfTytle));
				ws.addCell(new Label(3,2,"总寝室数",wcfTytle));
				ws.addCell(new Label(4,2,"未分配寝室床位数",wcfTytle));
				ws.addCell(new Label(5,2,"未分配寝室数",wcfTytle));
			}else{
				ws.addCell(new Label(0,2,lddm,wcfTytle));
				ws.addCell(new Label(1,2,"总床位数",wcfTytle));
				ws.addCell(new Label(2,2,"总寝室数",wcfTytle));
				ws.addCell(new Label(3,2,"未分配寝室床位数",wcfTytle));
				ws.addCell(new Label(4,2,"未分配寝室数",wcfTytle));
			}
			
			for(int i = 0;i<=xynum;i++){
				ws.addCell(new Label(i*5+ldnum,2,"已用床位数",wcfTytle));
				ws.addCell(new Label(i*5+ldnum+1,2,"空闲床位数",wcfTytle));
				ws.addCell(new Label(i*5+ldnum+2,2,"其他床位数",wcfTytle));
				ws.addCell(new Label(i*5+ldnum+3,2,"总床位数",wcfTytle));
				ws.addCell(new Label(i*5+ldnum+4,2,"总寝室数",wcfTytle));
			}
			
			//统计数据集
			for(int i =0;i<rs.size();i++){
				wcfTytle = ExcelMB.getWritableCellFormat(10,true, Alignment.CENTRE, VerticalAlignment.CENTRE,Border.ALL);// 单元格样式
				String[] tj = rs.get(i);
				if(ldnum == 7){
					ws.addCell(new Label(0,i+3,tj[0],wcfTytle));
					ws.addCell(new Label(1,i+3,tj[1],wcfTytle));
				}else{
					if("是".equalsIgnoreCase(czxq)){
						ws.addCell(new Label(0,i+3,tj[0],wcfTytle));
					}
					if("是".equalsIgnoreCase(czyq)){
						ws.addCell(new Label(0,i+3,tj[1],wcfTytle));
					}
				}
				ws.addCell(new Label(ldnum-5,i+3,tj[2],wcfTytle));
				wcfTytle = ExcelMB.getWritableCellFormat(10,false, Alignment.CENTRE, VerticalAlignment.CENTRE,Border.ALL);// 数据单元格样式
				for(int j=3;j<tj.length;j++){
					ws.addCell(new Label(j+ldnum-7,i+3,tj[j],wcfTytle));
				}
			}	
			//总计
			for(int i =0;i<zj.size();i++){
				wcfTytle = ExcelMB.getWritableCellFormat(10,true, Alignment.CENTRE, VerticalAlignment.CENTRE,Border.ALL);// 单元格样式
				String[] tj = zj.get(i);
				ws.mergeCells(0, i+rs.size()+3,ldnum-5, i+rs.size()+3);
				ws.addCell(new Label(0,i+rs.size()+3,tj[0],wcfTytle));
				wcfTytle = ExcelMB.getWritableCellFormat(10,false, Alignment.CENTRE, VerticalAlignment.CENTRE,Border.ALL);// 数据单元格样式
				for(int j=1;j<tj.length;j++){
					ws.addCell(new Label(ldnum-5+j,i+rs.size()+3,tj[j],wcfTytle));
				}
			}
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}

	//===========================end======*寝室床位统计*======end===============================//
	
	
	//==========================start=====*寝室人员统计*=====start==============================//
	/**
	 * 获取寝室人员统计列表
	 * @param myForm
	 * @throws Exception
	 */
	public List<String[]> getQsrytjList(GyglTjfxForm myForm,HttpServletRequest request) throws Exception {
		List<String[]> rsList = new ArrayList<String[]>();		
		
		List<HashMap<String,String>> list = dao.getQsrytjListByld(myForm);
		List<HashMap<String,String>> ldlist = dao.getLdlist(myForm);
		int maxcs = getMaxCs(myForm);
				
		if(ldlist != null && ldlist.size()>0){	
			//第一层循环：楼栋
			for(int i=0; i<ldlist.size();i++){
				List<String> rs = new ArrayList<String>();
				HashMap<String,String> ld = ldlist.get(i);
				String lddm = ld.get("lddm");
				rs.add(ld.get("xqmc"));
				rs.add(ld.get("yqmc"));
				rs.add(ld.get("ldmc"));			
				int bys = 0;
				int zs = 0;
				
				if(maxcs > 0 ){
					//第二层循环：楼层
					for(int k=1;k<=maxcs;k++){						
						boolean a =true;	//标记学院是否有该楼栋的床位
						if(list != null && list.size()>0){	
							//第三层循环：查询结果集中楼栋、学院对应的房间数
							for(int j=0; j<list.size();j++){
								HashMap<String,String> tj = list.get(j);
								if(String.valueOf(k).equalsIgnoreCase(tj.get("cs"))
										&& lddm.equalsIgnoreCase(tj.get("lddm"))){
									if(!"yes".equalsIgnoreCase(tj.get("yy"))){
										rs.add(tj.get("qsnum")+"/"+tj.get("num"));
										
										//楼栋中学院各数值的总计
										bys += Integer.parseInt(tj.get("zxs"));
										zs += Integer.parseInt(tj.get("num"));
																	
										tj.put("yy", "yes");
										a =false;
										break;
									}
								}						
							}
						}
						if(a && k <= Integer.valueOf(ld.get("cs"))){
							rs.add("0/0");
						}
						if(a && k > Integer.valueOf(ld.get("cs"))){
							rs.add("无");
						}
					}
				}

				rs.add(String.valueOf(zs-bys));
				rs.add(String.valueOf(bys));
				rs.add(String.valueOf(zs));
				rsList.add(rs.toArray(new String[]{}));
			}	
		}				
		
		return rsList;
	}
	
	/**
	 * 获取寝室人员总计列表
	 * @param myForm
	 * @throws Exception
	 */
	public List<String[]> getQsrytjZjList(GyglTjfxForm myForm,HttpServletRequest request) throws Exception {
		List<String[]> rsList = new ArrayList<String[]>();

		List<HashMap<String,String>> list = dao.getQsrytjListByxb(myForm);
		List<HashMap<String,String>> xblist = dao.getXblist(myForm);
		int maxcs = getMaxCs(myForm);
		
		if(xblist != null && xblist.size()>0){	
			//第一层循环：楼栋
			for(int i=0; i<xblist.size();i++){
				List<String> rs = new ArrayList<String>();
				HashMap<String,String> xb = xblist.get(i);
				String xbmc = xb.get("xb");
				rs.add("总计("+xb.get("xb")+")");
				int bys = 0;
				int zs = 0;
				
				if(maxcs > 0 ){
					//第二层循环：楼层
					for(int k=1;k<=maxcs;k++){							
						boolean a =true;	//标记学院是否有该楼栋的床位
						if(list != null && list.size()>0){	
							//第三层循环：查询结果集中楼栋、学院对应的房间数
							for(int j=0; j<list.size();j++){
								HashMap<String,String> tj = list.get(j);
								if(String.valueOf(k).equalsIgnoreCase(tj.get("cs"))
										&& xbmc.equalsIgnoreCase(tj.get("xb"))){
									if(!"yes".equalsIgnoreCase(tj.get("yy"))){
										rs.add(tj.get("qsnum")+"/"+tj.get("num"));
										
										//楼栋中学院各数值的总计
										bys += Integer.parseInt(tj.get("zxs"));
										zs += Integer.parseInt(tj.get("num"));
																	
										tj.put("yy", "yes");
										a =false;
										break;
									}
								}						
							}
						}
						if(a){
							rs.add("0/0");
						}						
					}
				}
				rs.add(String.valueOf(zs-bys));
				rs.add(String.valueOf(bys));
				rs.add(String.valueOf(zs));
				rsList.add(rs.toArray(new String[]{}));
			}				
		}		

		if(rsList != null && rsList.size()>0){	
			List<String> rs = new ArrayList<String>();
			rs.add("总计");
			String[] xbtj1 = rsList.get(0);
			String[] xbtj2 = rsList.get(1);
			
			for(int i=1;i<xbtj1.length-3;i++){
				String[] z1 =  xbtj1[i].split("/");
				String[] z2 =  xbtj2[i].split("/");
				
				int zj1 = Integer.parseInt(z1[0])+Integer.parseInt(z2[0]);
				int zj2 = Integer.parseInt(z1[1])+Integer.parseInt(z2[1]);
				rs.add(String.valueOf(zj1)+"/"+String.valueOf(zj2));
			}
			for(int i =xbtj1.length-3;i<xbtj1.length;i++){
				int zj = Integer.parseInt(xbtj1[i])+Integer.parseInt(xbtj2[i]);
				rs.add(String.valueOf(zj));
			}

			rsList.add(rs.toArray(new String[]{}));
		}
		return rsList;
	}
	
	/**
	 * 导出数据excel
	 * @param myForm
	 * @param message
	 * @param request
	 * @param response
	 */
	public void printQsrytj(GyglTjfxForm myForm,MessageResources message,
			HttpServletRequest request,HttpServletResponse response)
			throws Exception,IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		String xqdm = message.getMessage("lable.xiaoqu");
		String yqdm = message.getMessage("lable.yuanqu");
		String lddm = message.getMessage("lable.ld");
		String title = "楼栋寝室入住人员统计表";
		
		List<String[]> rs= getQsrytjList(myForm,request);
		List<String[]> zj=  getQsrytjZjList(myForm,request);
		int maxcs = getMaxCs(myForm);
				
		int ldnum = Integer.parseInt(request.getAttribute("ldnum").toString());
		String czyq = request.getAttribute("czyq").toString();
		String czxq = request.getAttribute("czxq").toString();
		
		
		WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(response.getOutputStream());
		WritableCellFormat wcfTytle = new WritableCellFormat();
		WritableSheet ws = wwb.createSheet(title+"("+GetTime.getSystemTime()+")", 0);
		try {			
			
			//标题
			wcfTytle = ExcelMB.getWritableCellFormat(16,true, Alignment.CENTRE, VerticalAlignment.CENTRE,Border.ALL);// 单元格样式
			ws.mergeCells(0, 0,maxcs+ldnum+3-1, 0);
			ws.addCell(new Label(0,0,title,wcfTytle));
			
			//表头
			wcfTytle = ExcelMB.getWritableCellFormat(10,true, Alignment.CENTRE, VerticalAlignment.CENTRE,Border.ALL);// 单元格样式
			//第一列
			ws.mergeCells(0, 1,ldnum-1, 1);
			ws.addCell(new Label(0,1,lddm,wcfTytle));
			ws.mergeCells(ldnum, 1,maxcs+ldnum-1, 1);
			ws.addCell(new Label(ldnum,1,"房间数/入住人数",wcfTytle));			
			ws.mergeCells(maxcs+ldnum,1,maxcs+ldnum+3-1, 1);
			ws.addCell(new Label(maxcs+ldnum,1,"总计",wcfTytle));			
			//第二列
			if(ldnum == 3){
				ws.addCell(new Label(0,2,xqdm,wcfTytle));
				ws.addCell(new Label(1,2,yqdm,wcfTytle));
				ws.addCell(new Label(2,2,lddm,wcfTytle));
			} else if(ldnum == 2){
				if("是".equalsIgnoreCase(czxq)){
					ws.addCell(new Label(0,2,xqdm,wcfTytle));
				}
				if("是".equalsIgnoreCase(czyq)){
					ws.addCell(new Label(0,2,yqdm,wcfTytle));
				}
				ws.addCell(new Label(1,2,lddm,wcfTytle));
			}
			
			for(int i = 1;i<=maxcs;i++){
				ws.addCell(new Label(i+ldnum-1,2,i+"层",wcfTytle));
			}
			ws.addCell(new Label(maxcs+ldnum,2,"非毕业生",wcfTytle));
			ws.addCell(new Label(maxcs+ldnum+1,2,"毕业生",wcfTytle));
			ws.addCell(new Label(maxcs+ldnum+2,2,"合计",wcfTytle));
			
			//统计数据集
			for(int i =0;i<rs.size();i++){
				wcfTytle = ExcelMB.getWritableCellFormat(10,true, Alignment.CENTRE, VerticalAlignment.CENTRE,Border.ALL);// 单元格样式
				String[] tj = rs.get(i);
				if(ldnum == 3){
					ws.addCell(new Label(0,i+3,tj[0],wcfTytle));
					ws.addCell(new Label(1,i+3,tj[1],wcfTytle));
				}else{
					if("是".equalsIgnoreCase(czxq)){
						ws.addCell(new Label(0,i+3,tj[0],wcfTytle));
					}
					if("是".equalsIgnoreCase(czyq)){
						ws.addCell(new Label(0,i+3,tj[1],wcfTytle));
					}
				}
				ws.addCell(new Label(ldnum-1,i+3,tj[2],wcfTytle));
				wcfTytle = ExcelMB.getWritableCellFormat(10,false, Alignment.CENTRE, VerticalAlignment.CENTRE,Border.ALL);// 单元格样式
				for(int j=3;j<tj.length;j++){
					ws.addCell(new Label(j+ldnum-3,i+3,tj[j],wcfTytle));
				}
			}	
			//总计
			for(int i =0;i<zj.size();i++){
				wcfTytle = ExcelMB.getWritableCellFormat(10,true, Alignment.CENTRE, VerticalAlignment.CENTRE,Border.ALL);// 单元格样式
				String[] tj = zj.get(i);
				ws.mergeCells(0, i+rs.size()+3,ldnum-1, i+rs.size()+3);
				ws.addCell(new Label(0,i+rs.size()+3,tj[0],wcfTytle));
				wcfTytle = ExcelMB.getWritableCellFormat(10,false, Alignment.CENTRE, VerticalAlignment.CENTRE,Border.ALL);// 单元格样式
				for(int j=1;j<tj.length;j++){
					ws.addCell(new Label(ldnum-1+j,i+rs.size()+3,tj[j],wcfTytle));
				}
			}
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	
	/**
	 * 获取全部楼栋层数最大值
	 * @param myForm
	 * @return
	 * @throws Exception
	 */
	public int getMaxCs(GyglTjfxForm myForm) throws Exception {
		return dao.getMaxCs(myForm);
	}
	
	

	//===========================end======*寝室人员统计*======end===============================//
	
	
// ======================年级分布统计 qlj=============================
	/**
	 * 
	 */
	public List<String[]> getNjfbtjBg(GyglTjfxForm myForm,HttpServletRequest request) throws Exception{
		//按年级学院统计床位分布信息
		List<String[]>njfbList=dao.getNjfbtjList(myForm,request);
		//各楼栋、年级入住情况总计
		List<String[]>njfbzjList=dao.getNjfbzjList(myForm,request);
		List<String[]>tjzjList=new ArrayList<String[]>();
 		List<String[]>tjxxList=new ArrayList<String[]>();

		List<HashMap<String,String>>njxxList=dao.getNjfbtjNjxx(myForm,request);
 		List<HashMap<String,String>>xyxxList=dao.getNjfbtjXyxx(myForm,request);
 	
 		int njlen=njxxList.size();
 		int xylen=xyxxList.size();
 		//总计
 		String[]sumArr=new String[4+(njlen+1)*(xylen+1)];
 		for(int i=0;i<njfbzjList.size();i++){
 			String[]njfbzjArr=njfbzjList.get(i);
 			String[]tjzjArr=new String[]{};
 			for(int j=0;j<njfbList.size();j++){
 				String[]njfbArr=njfbList.get(j);
 				if(njfbzjArr[2].equalsIgnoreCase(njfbArr[2])){
 					tjzjArr=getTjxxArr(njfbArr,njfbzjArr);
 					break;
 				}
 			}
 			tjzjList.add(tjzjArr);
 		}
 		
 		sumArr[0]="";
 		sumArr[1]="";
 		sumArr[2]="";
 		sumArr[3]="总计";
 		
 		
 		//分学院年级统计
		for(int i=0;i<tjzjList.size();i++){
			
			String[]tjzjArr=tjzjList.get(i);
			ArrayList<String>tjxxArr=new ArrayList<String>();

			int m=3;
			int sum=0;
			for(int j=0;j<tjzjArr.length;j++){
				if( j>3){
					tjzjArr[j]=Base.isNull(tjzjArr[j])? "0" : tjzjArr[j];
					int tjz=Integer.parseInt(tjzjArr[j]);
					sum+=tjz;
				
				}
				tjxxArr.add(tjzjArr[j]);
				if(j==m+njlen){
					m+=njlen;
					tjxxArr.add(String.valueOf(sum));
					sum=0;
				}
				
			}
			tjxxList.add(tjxxArr.toArray(new String[]{}));
		}
		
		
		for(int i=0;i<tjxxList.size();i++){
			String[]tjxxArr=tjxxList.get(i);
			for(int j=0;j<tjxxArr.length;j++){
					
				if( j>3){
				tjxxArr[j]=Base.isNull(tjxxArr[j])? "0" : tjxxArr[j];
				int tjz=Integer.parseInt(tjxxArr[j]);
				sumArr[j]=Base.isNull(sumArr[j])? "0" :sumArr[j];
				sumArr[j]=String.valueOf(Integer.parseInt(sumArr[j])+tjz);
			
				}
			}
		}
		tjxxList.add(sumArr);
 		request.setAttribute("xyxxList", xyxxList);
		request.setAttribute("njxxList", njxxList);
		request.setAttribute("njlen",njlen+1);
		request.setAttribute("xyCols", getXqYqLdCols(myForm)+1);
		
		return tjxxList;
	}
	
	public void printNjfbtj(GyglTjfxForm myForm,
			HttpServletRequest request, WritableWorkbook wwb)
			throws Exception {

		//按年级学院统计床位分布信息
		List<String[]>njfbList=getNjfbtjBg(myForm,request);
		List<HashMap<String,String>>njxxList=dao.getNjfbtjNjxx(myForm,request);
 		List<HashMap<String,String>>xyxxList=dao.getNjfbtjXyxx(myForm,request);
 		
 		int xyLen=xyxxList.size();
 		int njLen=njxxList.size();
 		int xyCols=getXqYqLdCols(myForm);
		
		try {
			// 创建xls中SHEET对象
			WritableSheet ws = wwb.getSheet(0);
			WritableCellFormat wcfTytle = new WritableCellFormat();
			// 设置对齐方式
			Alignment alignMent = Alignment.CENTRE;
			VerticalAlignment vag = VerticalAlignment.CENTRE;

			wcfTytle.setVerticalAlignment(vag);
			wcfTytle.setAlignment(alignMent);

			WritableFont wfTytle = new WritableFont(WritableFont.ARIAL);
			wfTytle.setBoldStyle(WritableFont.BOLD);
			wfTytle.setPointSize(16);
			wcfTytle.setFont(wfTytle);
			ws.mergeCells(0, 0,(xyCols+(xyLen+1)*(njLen+1)), 0);
			ws.addCell(new Label(0,0,"宿舍年级分布统计表",wcfTytle));
			
			wcfTytle = new WritableCellFormat();
			alignMent = Alignment.LEFT;
			vag = VerticalAlignment.CENTRE;

			wcfTytle.setVerticalAlignment(vag);
			wcfTytle.setAlignment(alignMent);
			 wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);

			wfTytle = new WritableFont(WritableFont.ARIAL);
			wfTytle.setBoldStyle(WritableFont.BOLD);
			wfTytle.setPointSize(12);
			wcfTytle.setFont(wfTytle);

			wcfTytle = new WritableCellFormat();
			alignMent = Alignment.CENTRE;
			vag = VerticalAlignment.CENTRE;

			wcfTytle.setVerticalAlignment(vag);
			wcfTytle.setAlignment(alignMent);
			wcfTytle.setWrap(true);
//			 设置表格边框
			 wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);
	

			wfTytle = new WritableFont(WritableFont.ARIAL);
			wfTytle.setPointSize(10);
			wcfTytle.setFont(wfTytle);
			
			
			ws.mergeCells(0, 1,xyCols, 1);
			ws.addCell(new Label(0,1,"楼栋",wcfTytle));
			ws.mergeCells(xyCols+1, 1,xyCols+1+njLen, 1);
			ws.addCell(new Label(xyCols+1,1,"总计",wcfTytle));
			for(int i=0;i<xyxxList.size();i++){
				HashMap<String,String>xyMap=xyxxList.get(i);
				ws.mergeCells(xyCols+2+njLen*(i+1)+i, 1,xyCols+2+njLen*(i+2)+i, 1);
				ws.addCell(new Label(xyCols+2+njLen*(i+1)+i,1,xyMap.get("xymc"),wcfTytle));
			}
			
			if("是".equalsIgnoreCase(myForm.getCzxq())){
				if(xyCols==1){
					ws.addCell(new Label(0,2,"校区",wcfTytle));
				}
			}
			//是否存在园区
			if("是".equalsIgnoreCase(myForm.getCzyq())){
				if(xyCols==1){
					ws.addCell(new Label(xyCols-1,2,"园区",wcfTytle));
				}else if(xyCols==2){
					ws.addCell(new Label(xyCols-1,2,"园区",wcfTytle));
				}
			}
			
			ws.addCell(new Label(xyCols,2,"楼栋名称",wcfTytle));
			
			for(int i=0;i<xyxxList.size()+1;i++){
				for(int j=0;j<njxxList.size();j++){
					HashMap<String,String>njMap=njxxList.get(j);
					ws.addCell(new Label(xyCols+1+njLen*i+i+j,2,njMap.get("nj"),wcfTytle));
				}
				ws.addCell(new Label(xyCols+1+njLen*(i+1)+i,2,"总计",wcfTytle));
			}
			
			for(int i=0;i<njfbList.size();i++){

				String[]njfbArr=njfbList.get(i);
				for(int j=0;j<njfbArr.length;j++){
					if("是".equalsIgnoreCase(myForm.getCzxq())){
						ws.addCell(new Label(0,3,njfbArr[j],wcfTytle));
					}
					//是否存在园区
					if("是".equalsIgnoreCase(myForm.getCzyq())){
						if(xyCols==1){
							ws.addCell(new Label(xyCols-1,i+3,"园区",wcfTytle));
						}else if(xyCols==2){
							ws.addCell(new Label(xyCols-1,i+3,"园区",wcfTytle));
						}
					}
					if(j>2 && xyCols==0){
						ws.addCell(new Label(xyCols+j-3,i+3,njfbArr[j],wcfTytle));
					}else if(j>2 && xyCols==1){
						ws.addCell(new Label(xyCols+j-2,i+3,njfbArr[j],wcfTytle));
					}else if(j>2 && xyCols==2){
						ws.addCell(new Label(xyCols+j-1,i+3,njfbArr[3+j],wcfTytle));
					}
					
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		// 向客户端输出
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	
	
	/**
	 * 获取表格总列数
	 * @param njList
	 * @param xyList
	 * @param myForm
	 * @return
	 */
	public int getXqYqLdCols(GyglTjfxForm myForm){
		int cols=0;
		//是否存在校区
		if("是".equalsIgnoreCase(myForm.getCzxq())){
			cols++;
		}
		//是否存在园区
		if("是".equalsIgnoreCase(myForm.getCzyq())){
			cols++;
		}
		return cols;
	}
	
	public String[] getTjxxArr(String[]tjxx,String[]zjxx){
		ArrayList<String>tjzjList=new ArrayList<String>();
		for(int i=0;i<zjxx.length;i++){
			tjzjList.add(zjxx[i]);
		}
		for(int j=4;j<tjxx.length;j++){
			tjzjList.add(tjxx[j]);
		}
		return tjzjList.toArray(new String[]{});
	}
	
	// ======================年级分布统计 end=============================
	
}