package xgxt.xszz.xcxy;

import java.io.OutputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.struts.action.ActionForm;

import xgxt.DAO.DAO;
import xgxt.DAO.ExcelMB;
import xgxt.action.Base;
import xgxt.pjpy.shcbys.jxj.JxjDAO;
import xgxt.utils.ExcelMethods;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;

public class XcxyXszzService {
	/*
	 * 获得显示成绩的xn或nd
	 */
	public String getViewCjXnOrNd(){
		if(Base.currXn.indexOf(Base.currNd) > 4){
			return String.valueOf((Integer.parseInt(Base.currNd)-1));
		}else{
			return String.valueOf((Integer.parseInt(Base.currNd)-1))+"-"+Base.currNd;
		}
	}
	/*
	 * 通过数据库中的字段备注获得表头中文显示
	 */
	public String[] getColumnNameCN_ser(String[] colList,String tableName){
		DAO dao = DAO.getInstance();
		return dao.getColumnNameCN(colList, tableName);
	}
	
	/*
	 * 获得表头的list
	 */
	public List<HashMap<String,String>> arrayToList_ser(String[] colList,String[] colListCN){
		DAO dao = DAO.getInstance();
		return dao.arrayToList(colList, colListCN);
	}
	
	/*
	 * 获得资助项目list
	 */
	public List<String[]> getZzxm_ser(String[] colList,XcxyXszzForm form){
		XcxyXszzDao dao = new XcxyXszzDao();
		return dao.getZzxm_db(colList,form);
	}
	
	/*
	 * 获得指定xmdm的项目
	 */
	public HashMap<String, String> getOneZzxm_ser(String pk,String[] colList){
		XcxyXszzDao dao = new XcxyXszzDao();
		HashMap<String, String> map = new HashMap<String, String>();
		String[] array = dao.getOneZzxm_db(pk, colList);
		if(array != null){
			for(int i = 0; i < array.length; i++){
				map.put(colList[i], array[i]);
			}
		}
		return map;
	}
	
	/*
	 * 检查唯一性
	 */
	public String checkOnlyOne_ser(String pk,String pkValue,String tableName){	
		XcxyXszzDao dao = new XcxyXszzDao();
		return dao.checkOnlyOne_db(pk, pkValue, tableName);
	}
	
	/*
	 * 资助项目增加
	 */
	public String add_ser(XcxyXszzForm form){
		XcxyXszzDao dao = new XcxyXszzDao();
		return dao.add_db(form);
	}
	
	/*
	 * 资助项目修改
	 */
	public String modify_ser(XcxyXszzForm form){
		XcxyXszzDao dao = new XcxyXszzDao();
		return dao.modify_db(form);
	}
	
	/*
	 * 资助项目删除
	 */
	public String delete_ser(String pkStr){
		XcxyXszzDao dao = new XcxyXszzDao();
		try{
			dao.delete_db(pkStr);
		}catch(Exception e){
			e.printStackTrace();
			return "no";
		}
		return "yes";
	}
	
	/*
	 * 获得所有资助项目list
	 */
	public List<HashMap<String,String>> getAllZzxm_ser(){
		XcxyXszzDao dao = new XcxyXszzDao();
		return dao.getAllZzxm_db();
	}
	/*
	 * 查询资助项目金额
	 */
	public List<String[]> queryZzxmJe_ser(String[] colList,XcxyXszzForm form){
		XcxyXszzDao dao = new XcxyXszzDao();
		return dao.queryZzxmJe_db(colList,form);
	}
	
	/*
	 * 资助项目金额增加
	 */
	public String addZzje_ser(XcxyXszzForm form){
		XcxyXszzDao dao = new XcxyXszzDao();
		return dao.addZzje_db(form);
	}
	
	/*
	 * 资助项目金额修改
	 */
	public String modifyZzje_ser(XcxyXszzForm form){
		XcxyXszzDao dao = new XcxyXszzDao();
		return dao.modifyZzje_db(form);
	}
	
	/*
	 * 资助项目金额删除
	 */
	public String deleteZzje_ser(String pkStr){
		XcxyXszzDao dao = new XcxyXszzDao();
		try{
			dao.deleteZzje_db(pkStr);
		}catch(Exception e){
			e.printStackTrace();
			return "no";
		}
		return "yes";
	}
	
	/*
	 * 获得指定xmdm||zzje的项目金额
	 */
	public HashMap<String, String> getOneZzje_ser(String pk,String[] colList){
		XcxyXszzDao dao = new XcxyXszzDao();
		HashMap<String, String> map = new HashMap<String, String>();
		String[] array = dao.getOneZzje_db(pk, colList);
		if(array != null){
			for(int i = 0; i < array.length; i++){
				map.put(colList[i], array[i]);
			}
		}
		return map;
	}
	
	/*
	 * 获得指定资助项目的金额
	 */
	public List<HashMap<String,String>> getZzxmJe_ser(String xmdm){
		XcxyXszzDao dao = new XcxyXszzDao();
		return dao.getZzxmJe_db(xmdm);
	}
	
	/*
	 * 查询资助项目时间
	 */
	public List<String[]> queryZzxmSj_ser(String[] colList,XcxyXszzForm form){
		XcxyXszzDao dao = new XcxyXszzDao();
		return dao.queryZzxmSj_db(colList,form);
	}
	
	/*
	 * 获得指定xmdm的项目时间
	 */
	public HashMap<String, String> getOneZzsj_ser(String pk,String[] colList){
		XcxyXszzDao dao = new XcxyXszzDao();
		HashMap<String, String> map = new HashMap<String, String>();
		String[] array = dao.getOneZzsj_db(pk, colList);
		if(array != null){
			for(int i = 0; i < array.length; i++){
				map.put(colList[i], array[i]);
			}
		}
		return map;
	}
	
	/*
	 * 单个资助时间设定
	 */
	public String setZzsj_ser(XcxyXszzForm form){
		XcxyXszzDao dao = new XcxyXszzDao();
		return dao.setZzsj_db(form);
	}
	
	/*
	 * 初始化资助时间
	 */
	public String clearZzsj_ser(){
		XcxyXszzDao dao = new XcxyXszzDao();
		try{
			dao.clearZzsj_db();
		}catch(Exception e){
			e.printStackTrace();
			return "no";
		}
		return "yes";
	}
	
	/*
	 * 批量设置资助时间
	 */
	public String saveManyZzsj_ser(String str,XcxyXszzForm form){
		XcxyXszzDao dao = new XcxyXszzDao();
		try{
			dao.saveManyZzsj_db(str,form);
		}catch(Exception e){
			e.printStackTrace();
			return "no";
		}
		return "yes";
	}
	
	/*
	 * 校验是否在项目申请时间范围内
	 */
	public String checkSfksq_ser(HashMap<String,String> map){
		XcxyXszzDao dao = new XcxyXszzDao();
		if(map != null && !Base.isNull(map.get("zzkssj"))){
			return dao.checkSfksq_db(map);
		}else{
			return "no";
		}
	}
	
	/*
	 * 获得国家奖学金学生的基本信息
	 */
	public HashMap<String,String> getJxjXsxx_ser(String xxmc,XcxyXszzForm form){
		XcxyXszzDao dao = new XcxyXszzDao();
		return dao.getJxjXsxx_db(xxmc,form);
	}
	
	/*
	 * 获得助学金学生的基本信息
	 */
	public HashMap<String,String> getZxjXsxx_ser(XcxyXszzForm form){
		XcxyXszzDao dao = new XcxyXszzDao();	
		return dao.getZxjXsxx_db(form);
	}
	
	/*
	 * 获得国家励志奖学金学生的基本信息
	 */
	public HashMap<String,String> getLzjxjXsxx_ser(XcxyXszzForm form){
		XcxyXszzDao dao = new XcxyXszzDao();	
		return dao.getLzjxjXsxx_db(form);
	}
	
	/*
	 * 学生成绩
	 */
	public List<String[]> getCjList_ser(String xh,String xn,String xq) {
		JxjDAO jxjdao = new JxjDAO();
		XcxyXszzDao dao = new XcxyXszzDao();
		String jwflag = jxjdao.getJwFlag();
		jwflag = StringUtils.isNull(jwflag) ? "" : jwflag.trim();//1学分0过渡
		try{
			if ("1".equalsIgnoreCase(jwflag)) {
				return dao.getCjListByxf_db(xh,xn,xq);
			} else {
				return dao.getCjList_db(xh,xn,xq);
			}
		}catch(Exception ex){
			ex.printStackTrace();
			return null;
		}
	}
	
	/*
	 * 获得学生成绩的汇总
	 */
	public HashMap<String,String> getXscjHz_ser(String xh,String xn,String xq){
		XcxyXszzDao dao = new XcxyXszzDao();	
		return dao.getXscjHz_db(xh,xn,xq);
	}
	
	/*
	 * 保存学生资助申请
	 */
	public String saveJzxjsq_ser(XcxyXszzForm form){
		XcxyXszzDao dao = new XcxyXszzDao();
		try {
			return dao.saveJzxjsq_db(form);
		} catch (Exception e) {
			e.printStackTrace();
			return "no";
		} 
	}
	
	/*
	 * 审核查询
	 */
	public List<String[]> queryShInfo_ser(String[] cols,XcxyXszzForm form,String tableName,String userType){
		XcxyXszzDao dao = new XcxyXszzDao();
		return dao.queryShInfo_db(cols,form,tableName,userType);
	}
	
	/*
	 * 批量保存学生资助申请审核
	 */
	public String excutePlsh_ser(XcxyXszzForm form,String userType){
		XcxyXszzDao dao = new XcxyXszzDao();
		return dao.excutePlsh_db(form,userType);
	}
	
	/*
	 * 获得学生所有资助获奖信息
	 */
	public List<HashMap<String,String>> getXsAllPrise_ser(String xh,String xn){
		XcxyXszzDao dao = new XcxyXszzDao();
		return dao.getXsAllPrise_db(xh,xn);
	}
	
	/*
	 * 获得学生违纪处分情况
	 */
	public List<HashMap<String,String>> getXsAllWjcf_ser(String xh,String xn,String xq){	
		XcxyXszzDao dao = new XcxyXszzDao();
		return dao.getXsAllWjcf_db(xh, xn,xq);
	}
	
	/*
	 * 获得学生资助申请单个审核展现的信息
	 */
	public HashMap<String,String> getXssqInfo_ser(XcxyXszzForm form){
		XcxyXszzDao dao = new XcxyXszzDao();
		return dao.getXssqInfo_db(form);
	}
	
	/*
	 * 单个保存学生资助申请审核
	 */
	public String excuteDgsh_ser(XcxyXszzForm form,String userType){
		XcxyXszzDao dao = new XcxyXszzDao();
		return dao.excuteDgsh_db(form,userType);
	}
	
	/*
	 * 数据导出sql
	 */
	public String getExpSql_ser(XcxyXszzForm form,String tableName,String condsql){
		XcxyXszzDao dao = new XcxyXszzDao();
		return dao.getExpSql_db(form,tableName,condsql);
	}
	
	/*
	 * form中的值封装在HashMap中
	 */
	public HashMap<String,String> formToMap_ser(ActionForm form){
		Class cla = form.getClass();
		Method[] methods = cla.getMethods();
		HashMap<String,String> map = new HashMap<String,String>();
		if(methods != null && methods.length > 0){
			try{
				for(int i= 0;i<methods.length;i++){
					String mName = methods[i].getName();
					if(mName != null && mName.indexOf("get") ==0){
						if(methods[i].getReturnType()==String.class){
							map.put(mName.substring(3).toLowerCase(), (String)methods[i].invoke(form,(Object[]) null));
						}			
					}
				}
			}catch(Exception e){
				e.printStackTrace();
				return map;
			}			
		}
		return map;
	}
	
	/*
	 * 获得学生的基本信息
	 */
	public HashMap<String,String> getXsxx_ser(String xxmc,XcxyXszzForm form){
		XcxyXszzDao dao = new XcxyXszzDao();
		return dao.getXsxx_db(xxmc, form);
	}
	
	/*
	 * 获得学生成绩信息
	 */
	public HashMap<String,String> getXscj_ser(XcxyXszzForm form){
		XcxyXszzDao dao = new XcxyXszzDao();
		return dao.getXscj_db(form);
	}
	/*
	 * 获得学生的基本信息
	 */
	public List<HashMap<String,String>> getPrintList_ser(XcxyXszzForm form,String userType){
		List<HashMap<String,String>> list = new ArrayList<HashMap<String,String>>();
		HashMap<String,String> map = new HashMap<String,String>();
		String bbys = form.getBbys();
		String xmmc = form.getXmmc();
		if ("国家奖学金".equals(bbys) || "国家励志奖学金".equals(bbys)) {
			map.put("dm", "jxjffb");
			map.put("mc", xmmc + "发放表");
			list.add(map);
			map = new HashMap<String, String>();
			map.put("dm", "jxjcsb");
			map.put("mc", xmmc + "初审表");
			list.add(map);
		} else if ("国家助学金".equals(bbys)) {		
			if(!"xy".equals(userType)){
				map.put("dm", "zxjtjzb");
				map.put("mc", xmmc + "统计总表");
				list.add(map);			
			}
			map = new HashMap<String, String>();
			map.put("dm", "zxjffb");
			map.put("mc", xmmc + "发放表");
			list.add(map);
			map = new HashMap<String, String>();
			map.put("dm", "zxjsbb");
			map.put("mc", xmmc + "上报表");
			list.add(map);
		}else if ("广州助学金".equals(bbys)) {		
			map.put("dm", "gzzxjffb");
			map.put("mc", xmmc + "发放表");
			list.add(map);
			map = new HashMap<String, String>();
			map.put("dm", "gzzxjmdb");
			map.put("mc", xmmc + "名单表");
			list.add(map);
		}else if ("加拿大福慧基金会励志奖学金".equals(bbys)) {		
			map.put("dm", "jndlzjxjffb");
			map.put("mc", xmmc + "发放表");
			list.add(map);
			map = new HashMap<String, String>();
			map.put("dm", "jndlzjxjmdb");
			map.put("mc", xmmc + "名单表");
			list.add(map);
		}
		return list;
	}
	
	/*
	 * 执行报表打印
	 */
	public void excutePrint_ser(XcxyXszzForm form,String userType,OutputStream os){
		String bbys = form.getBbys();
		String xmlc = form.getXmlc();
		String xmmc = form.getXmmc();
		String tableName="";
		if("国家奖学金".equalsIgnoreCase(xmlc)){
			tableName = "view_xszz_jxjsqb";
		}else if("国家助学金".equalsIgnoreCase(xmlc)){
			tableName = "view_xszz_zxjsqb";
		}else{
			tableName = "view_xszz_lzjxjsqb";
		}
		form.setTableName(tableName);
		try {
			if ("jxjffb".equals(bbys)) {
				if ("xy".equalsIgnoreCase(userType)) {
					printXxFfList(userType, tableName, form, os, xmmc + "发放");
				} else {
					printXxFfList(tableName, form, os, xmmc + "发放");
				}

			} else if ("jxjcsb".equals(bbys)) {

				if ("xy".equalsIgnoreCase(userType)) {
					printXxCsList(userType, tableName, "", form, os, xmmc
							+ "初审");
				} else {
					printXxCsList(userType, tableName, xmmc, form, os, xmmc
							+ "初审");
				}

				if ("xy".equalsIgnoreCase(userType)) {
					printXxCsList(userType, tableName, "", form, os, xmmc
							+ "初审");
				} else {
					printXxCsList(userType, tableName, xmmc, form, os, xmmc
							+ "初审");
				}				
			} else if ("zxjtjzb".equals(bbys)) {
				zxjtjzb_ser(form, userType, os);
			} else if ("zxjffb".equals(bbys)) {
				if ("xy".equalsIgnoreCase(userType)) {
					printXsZxjList_yx(form, userType, os);
				}else{
					printXscZxjList(userType, xmlc, form, os, xmmc+"发放表");
				}
			} else if ("zxjsbb".equals(bbys)) {
				zxjsbb_ser(form, userType, os);
			} else if ("gzzxjmdb".equals(bbys)) {
				gzzxjmdb_ser(form, userType, os);
			} else if ("gzzxjffb".equals(bbys)) {
				gzzxjffb_ser(form, userType, os);
			} else if ("jndlzjxjffb".equals(bbys)) {
				jndlzjxjffb_ser(form, userType, os);
			} else if ("jndlzjxjmdb".equals(bbys)) {
				jndlzjxjmdb_ser(form, userType, os);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * 助学金统计总表打印
	 */
	public void zxjtjzb_ser(XcxyXszzForm form,String userType,OutputStream os)throws Exception{
		String xmmc = form.getXmmc();
		XcxyXszzDao dao = new XcxyXszzDao();
		WritableWorkbook wwb = Workbook.createWorkbook(os);
		WritableSheet ws = wwb.createSheet(xmmc + "统计总表", 0);
		ExcelMB mb = new ExcelMB();
		WritableCellFormat wcf = new WritableCellFormat();
		wcf.setBorder(Border.ALL, BorderLineStyle.THIN);
		wcf.setAlignment(Alignment.CENTRE);
		ws.mergeCells(0, 0, 10, 1);
		mb.printToOneCell_multy(ws, form.getNd() + "年度四川省高校" + form.getXmmc()
				+ "受助学生汇总表", 0, 0, 18, true, Alignment.CENTRE,
				VerticalAlignment.CENTRE, true, 1000, Border.NONE);
		ws.mergeCells(0, 2, 5, 3);
		ws.mergeCells(6, 2, 10, 3);
		mb.printToOneCell_multy(ws, "填报单位：", 0, 2, 12, false,
				Alignment.CENTRE, VerticalAlignment.CENTRE, false, 300,
				Border.NONE);
		mb.printToOneCell_multy(ws, "填报时间：" + GetTime.getNowTime(), 6, 2, 12,
				false, Alignment.CENTRE, VerticalAlignment.CENTRE, false, 300,
				Border.NONE);
		ws.mergeCells(0, 4, 0, 5);
		mb.printToOneCell_multy(ws, "学校名称", 0, 4, 10, false, Alignment.CENTRE,
				VerticalAlignment.CENTRE, false, 300, Border.ALL);
		ws.mergeCells(1, 4, 1, 5);
		mb.printToOneCell_multy(ws, "在校生人数", 1, 4, 10, false, Alignment.CENTRE,
				VerticalAlignment.CENTRE, false, 300, Border.ALL);
		ws.mergeCells(2, 4, 5, 4);
		mb.printToOneCell_multy(ws, "受助学生数", 2, 4, 10, false, Alignment.CENTRE,
				VerticalAlignment.CENTRE, false, 300, Border.ALL);
		ws.addCell(new Label(2, 5, "合计",wcf));
		ws.addCell(new Label(3, 5, "一档",wcf));
		ws.addCell(new Label(4, 5, "二档",wcf));
		ws.addCell(new Label(5, 5, "三档",wcf));
		ws.mergeCells(6, 4, 6, 5);
		mb.printToOneCell_multy(ws, "资助金额", 6, 4, 10, false, Alignment.CENTRE,
				VerticalAlignment.CENTRE, false, 300, Border.ALL);
		ws.mergeCells(7, 4, 7, 5);
		mb.printToOneCell_multy(ws, "受助学生中本科生数", 7, 4, 10, false,
				Alignment.CENTRE, VerticalAlignment.CENTRE, false, 300,
				Border.ALL);
		ws.mergeCells(8, 4, 8, 5);
		mb.printToOneCell_multy(ws, "受助学生中少数民族数", 8, 4, 10, false,
				Alignment.CENTRE, VerticalAlignment.CENTRE, false, 300,
				Border.ALL);
		ws.mergeCells(9, 4, 9, 5);
		mb.printToOneCell_multy(ws, "受助学生中农村学生数", 9, 4, 10, false,
				Alignment.CENTRE, VerticalAlignment.CENTRE, false, 300,
				Border.ALL);
		ws.mergeCells(10, 4, 10, 5);
		mb.printToOneCell_multy(ws, "备注", 10, 4, 10, false, Alignment.CENTRE,
				VerticalAlignment.CENTRE, false, 300, Border.ALL);
		ws.addCell(new Label(0, 6, "西昌学院",wcf));
		String[] xsrs = dao.getXsrs_db(form.getNd(),form.getXmdm(),form.getTableName());
		ws.addCell(new Label(1, 6, xsrs[0],wcf));
		ws.addCell(new Label(7, 6, xsrs[1],wcf));
		ws.addCell(new Label(8, 6, xsrs[2],wcf));
		ws.addCell(new Label(9, 6, xsrs[3],wcf));
		List<String[]> rsje = dao.getZxjrsAndJe_db(form.getNd(),form.getXmdm(),form.getTableName());
		int zrs = 0;
		float zje = 0;
		for(int i=0;i<rsje.size();i++){
			String[] array = rsje.get(i);
			ws.addCell(new Label(3+i, 6, array[0],wcf));
			zrs += Integer.parseInt(array[0]);
			zje += Float.parseFloat(array[1]);
		}
		if(rsje.size() < 3){
			for(int j= rsje.size();j<3;j++){
				ws.addCell(new Label(3+j, 6, "0",wcf));
			}		
		}
		ws.addCell(new Label(2, 6, String.valueOf(zrs),wcf));
		ws.addCell(new Label(6, 6, String.valueOf(zje),wcf));
		ws.addCell(new Label(10, 6, "",wcf));
		wwb.write();
		wwb.close();
	}
	
	/*
	 * 助学金上报表打印
	 */
	public void zxjsbb_ser(XcxyXszzForm form,String userType,OutputStream os)throws Exception{
		String xmmc = form.getXmmc();
		XcxyXszzDao dao = new XcxyXszzDao();
		WritableWorkbook wwb = Workbook.createWorkbook(os);
		WritableSheet ws = wwb.createSheet(xmmc + "上报表", 0);
		ExcelMB mb = new ExcelMB();
		WritableCellFormat wcf = new WritableCellFormat();
		wcf.setBorder(Border.ALL, BorderLineStyle.THIN);
		wcf.setAlignment(Alignment.CENTRE);
		wcf.setWrap(true);
		ws.mergeCells(0, 0, 13, 1);
		String content = "";
		ws.mergeCells(0, 2, 5, 3);
		ws.mergeCells(6, 2, 13, 3);
		if("xy".equals(userType)){
			mb.printToOneCell_multy(ws, "系部名称："+form.getXymc(), 0, 2, 12, false,
					Alignment.CENTRE, VerticalAlignment.CENTRE, false, 300,
					Border.NONE);
			content = form.getNd() + "年度西昌学院"+xmmc+"受助学生名册";
		}else{
			mb.printToOneCell_multy(ws, "学校名称：西昌学院", 0, 2, 12, false,
					Alignment.CENTRE, VerticalAlignment.CENTRE, false, 300,
					Border.NONE);
			content = form.getNd() + "年度四川省高校"+xmmc+"受助学生名册";
		}
		mb.printToOneCell_multy(ws, content, 0, 0, 18, true, Alignment.CENTRE,
				VerticalAlignment.CENTRE, true, 1000, Border.NONE);
		mb.printToOneCell_multy(ws, "填报时间：" + GetTime.getNowTime(), 6, 2, 12,
				false, Alignment.CENTRE, VerticalAlignment.CENTRE, false, 300,
				Border.NONE);
		ws.mergeCells(0, 4, 0, 5);
		mb.printToOneCell_multy(ws, "序号", 0, 4, 10, false, Alignment.CENTRE,
				VerticalAlignment.CENTRE, false, 300, Border.ALL);
		ws.mergeCells(1, 4, 1, 5);
		mb.printToOneCell_multy(ws, "姓名", 1, 4, 10, false, Alignment.CENTRE,
				VerticalAlignment.CENTRE, false, 300, Border.ALL);
		ws.mergeCells(2, 4, 2, 5);
		mb.printToOneCell_multy(ws, "性别", 2, 4, 10, false, Alignment.CENTRE,
				VerticalAlignment.CENTRE, false, 300, Border.ALL);
		ws.mergeCells(3, 4, 3, 5);
		mb.printToOneCell_multy(ws, "身份证号", 3, 4, 10, false, Alignment.CENTRE,
				VerticalAlignment.CENTRE, false, 300, Border.ALL);
		ws.mergeCells(4, 4, 4, 5);
		mb.printToOneCell_multy(ws, "院系", 4, 4, 10, false, Alignment.CENTRE,
				VerticalAlignment.CENTRE, false, 300, Border.ALL);
		ws.mergeCells(5, 4, 5, 5);
		mb.printToOneCell_multy(ws, "专业", 5, 4, 10, false, Alignment.CENTRE,
				VerticalAlignment.CENTRE, false, 300, Border.ALL);
		ws.mergeCells(6, 4, 6, 5);
		mb.printToOneCell_multy(ws, "学号", 6, 4, 10, false, Alignment.CENTRE,
				VerticalAlignment.CENTRE, false, 300, Border.ALL);
		ws.mergeCells(7, 4, 7, 5);
		mb.printToOneCell_multy(ws, "少数民族", 7, 4, 10, false, Alignment.CENTRE,
				VerticalAlignment.CENTRE, false, 300, Border.ALL);
		ws.mergeCells(8, 4, 8, 5);
		mb.printToOneCell_multy(ws, "农村户口",8, 4, 10, false, Alignment.CENTRE,
				VerticalAlignment.CENTRE, false, 300, Border.ALL);
		ws.mergeCells(9, 4, 9, 5);
		mb.printToOneCell_multy(ws, "本科层次", 9, 4, 10, false, Alignment.CENTRE,
				VerticalAlignment.CENTRE, false, 300, Border.ALL);	
		ws.mergeCells(10, 4, 12, 4);
		mb.printToOneCell_multy(ws, "资助档次", 10, 4, 10, false, Alignment.CENTRE,
				VerticalAlignment.CENTRE, false, 300, Border.ALL);
		ws.addCell(new Label(10, 5, "一档",wcf));
		ws.addCell(new Label(11, 5, "二档",wcf));
		ws.addCell(new Label(12, 5, "三档",wcf));
		ws.mergeCells(13, 4, 13, 5);
		mb.printToOneCell_multy(ws, "资助金额(单位:元)", 13, 4, 10, false, Alignment.CENTRE,
				VerticalAlignment.CENTRE, false, 300, Border.ALL);
		
		List<HashMap<String,String>> list = dao.getZxjSb_db(form, userType);
		HashMap<String,String> map = new HashMap<String,String>();
		for(int i=0;i<list.size();i++){
			map = list.get(i);
			ws.addCell(new Label(0, 6+i, map.get("rownum"),wcf));
			ws.addCell(new Label(1, 6+i, map.get("xm"),wcf));
			ws.addCell(new Label(2, 6+i, map.get("xb"),wcf));
			ws.addCell(new Label(3, 6+i, map.get("sfzh"),wcf));
			ws.addCell(new Label(4, 6+i, map.get("xymc"),wcf));
			ws.addCell(new Label(5, 6+i, map.get("zymc"),wcf));
			ws.addCell(new Label(6, 6+i, map.get("xh"),wcf));
			ws.addCell(new Label(7, 6+i, map.get("mz"),wcf));
			ws.addCell(new Label(8, 6+i, map.get("hk"),wcf));
			ws.addCell(new Label(9, 6+i, map.get("xl"),wcf));
			String dc1 = "";
			String dc2 = "";
			String dc3 = "";
			if("1".equals(map.get("dc"))){
				dc1 = "1";
			}else if("2".equals(map.get("dc"))){
				dc2 = "1";
			}else{
				dc3 = "1";
			}
			ws.addCell(new Label(10, 6+i, dc1,wcf));
			ws.addCell(new Label(11, 6+i, dc2,wcf));
			ws.addCell(new Label(12, 6+i, dc3,wcf));
			ws.addCell(new Label(13, 6+i, map.get("zzje"),wcf));
		}
		wwb.write();
		wwb.close();
	}
	
	/*
	 * 执行院系国家奖学金/励志奖学金学院发放列表打印
	 */
	public void printXxFfList(String userType, String tableName,
			XcxyXszzForm form, OutputStream os, String title) throws Exception {

		String nd = form.getNd();
		String bmmc = form.getXymc();
		String userDep = form.getXydm();

		String content = nd + "年度西昌学院" + bmmc + "系发放表";

		WritableWorkbook wwb = Workbook.createWorkbook(os);
		XcxyXszzDao dao = new XcxyXszzDao();
		// 填充内容
		List<HashMap<String, String>> list = dao.getXxFfList(nd, tableName,
				userDep,form.getXmdm());

		WritableSheet ws = wwb.createSheet(title + "统计表", 0);

		WritableCellFormat wcf2 = new WritableCellFormat(); // 构造单元格格式
		wcf2 = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, Border.ALL);
		// 填充标题
		ExcelMB ex = new ExcelMB();
		ws.mergeCells(0, 0, 8, 0);
		ex.printToOneCell_multy(ws, content, 0, 0, 20, true, Alignment.CENTRE,
				VerticalAlignment.CENTRE, true, 650, Border.NONE);
		// 填充表头);
		ws.mergeCells(0, 1, 8, 2);
		String topInfo = "系部名称:                                填写日期："
				+ GetTime.getNowTime();
		ex.printToOneCell_multy(ws, topInfo, 0, 1, 10, true, Alignment.CENTRE,
				VerticalAlignment.CENTRE, false, 650, Border.NONE);

		ws.addCell(new Label(0, 3, "序号", wcf2)); // 添加有指定格式的单元格值
		ws.addCell(new Label(1, 3, "姓名", wcf2)); // 添加有指定格式的单元格值
		ws.addCell(new Label(2, 3, "性别", wcf2)); // 添加有指定格式的单元格值
		ws.addCell(new Label(3, 3, "年级专业", wcf2)); // 添加有指定格式的单元格值
		ws.addCell(new Label(4, 3, "身份证号码", wcf2)); // 添加有指定格式的单元格值
		ws.addCell(new Label(5, 3, "银行账号", wcf2)); // 添加有指定格式的单元格值
		ws.addCell(new Label(6, 3, "金额（元）", wcf2)); // 添加有指定格式的单元格值
		ws.addCell(new Label(7, 3, "学生签字", wcf2)); // 添加有指定格式的单元格值
		ws.addCell(new Label(8, 3, "备注", wcf2)); // 添加有指定格式的单元格值

		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				ws.addCell(new Label(0, 4 + i, String.valueOf(i + 1), wcf2)); // 添加有指定格式的单元格值
				ws.addCell(new Label(1, 4 + i, list.get(i).get("xm"), wcf2)); // 添加有指定格式的单元格值
				ws.addCell(new Label(2, 4 + i, list.get(i).get("xb"), wcf2)); // 添加有指定格式的单元格值
				ws.addCell(new Label(3, 4 + i, list.get(i).get("zymc"), wcf2)); // 添加有指定格式的单元格值
				ws.addCell(new Label(4, 4 + i, list.get(i).get("sfzh"), wcf2)); // 添加有指定格式的单元格值
				ws.addCell(new Label(5, 4 + i, list.get(i).get("yhkh"), wcf2)); // 添加有指定格式的单元格值
				ws.addCell(new Label(6, 4 + i, list.get(i).get("zzje"), wcf2)); // 添加有指定格式的单元格值
				ws.addCell(new Label(7, 4 + i, "", wcf2)); // 添加有指定格式的单元格值
				ws.addCell(new Label(8, 4 + i, "", wcf2)); // 添加有指定格式的单元格值
			}
		}
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}

	/*
	 * 执行院系国家奖学金/励志奖学金学校发放列表打印
	 */
	public void printXxFfList(String tableName, XcxyXszzForm form,
			OutputStream os, String title) throws Exception {

		String nd = form.getNd();
		String content = "西昌学院"+nd + "年度普通高等学校" + form.getXmmc() + "发放表";
		int zje = 0;

		WritableWorkbook wwb = Workbook.createWorkbook(os);
		XcxyXszzDao dao = new XcxyXszzDao();
		// 填充内容
		List<HashMap<String, String>> list = dao.getXxFfList(nd, tableName,form.getXmdm());
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				String je = list.get(i).get("zje");
				if (!Base.isNull(je)) {
					zje += Integer.parseInt(je);
				}
			}
		}
		
		WritableSheet ws = wwb.createSheet(title + "统计表", 0);

		WritableCellFormat wcf2 = new WritableCellFormat(); // 构造单元格格式
		wcf2 = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, Border.ALL);
		// 填充标题
		ExcelMB ex = new ExcelMB();
		ws.mergeCells(0, 0, 4, 0);
		ex.printToOneCell_multy(ws, content, 0, 0, 20, true, Alignment.CENTRE,
				VerticalAlignment.CENTRE, true, 650, Border.NONE);
		// 填充表头);
		ws.mergeCells(0, 1, 4, 2);
		String topInfo = "部门:学生处                                单位：人,元";
		ex.printToOneCell_multy(ws, topInfo, 0, 1, 10, true, Alignment.CENTRE,
				VerticalAlignment.CENTRE, false, 650, Border.NONE);

		ws.addCell(new Label(0, 3, "系部", wcf2)); // 添加有指定格式的单元格值
		ws.addCell(new Label(1, 3, "金额", wcf2)); // 添加有指定格式的单元格值
		ws.addCell(new Label(2, 3, "人数", wcf2)); // 添加有指定格式的单元格值
		ws.addCell(new Label(3, 3, "金额小计", wcf2)); // 添加有指定格式的单元格值
		ws.addCell(new Label(4, 3, "领款人签字", wcf2)); // 添加有指定格式的单元格值

		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				ws.addCell(new Label(0, 4 + i, list.get(i).get("xymc"), wcf2)); // 添加有指定格式的单元格值
				ws.addCell(new Label(1, 4 + i, list.get(i).get("zzje"), wcf2)); // 添加有指定格式的单元格值
				ws.addCell(new Label(2, 4 + i, list.get(i).get("num"), wcf2)); // 添加有指定格式的单元格值
				ws.addCell(new Label(3, 4 + i, list.get(i).get("zje"), wcf2)); // 添加有指定格式的单元格值
				ws.addCell(new Label(4, 4 + i, "", wcf2)); // 添加有指定格式的单元格值
			}
			ws.addCell(new Label(0, 4 + list.size(), "合计", wcf2));
			ws.addCell(new Label(1, 4 + list.size(), String.valueOf(zje),
							wcf2));
			ws.mergeCells(1, 4 + list.size(), 4, 4 + list.size());
		}
		
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	/*
	 * 执行院系国家奖学金/励志奖学金初审列表打印
	 */
	public void printXxCsList(String userType, String tableName,String xmmc,
			XcxyXszzForm form, OutputStream os, String title) throws Exception {

		String nd = form.getNd();
		String bmmc = form.getXymc();
		String userDep=form.getXydm();
		
		String content = "";
		String topInfo="";
		if ("xy".equalsIgnoreCase(userType)) {
			content = nd + "年度西昌学院" + bmmc + "系初审名单表";
			topInfo = "系部名称:                                     填写日期："
					+ GetTime.getNowTime();
		}else{
			content = nd + "年度普通高等学校" + xmmc + "学生初审名单表";
			topInfo = "学校名称:                                     填写日期："
				+ GetTime.getNowTime();
			userDep = "%";
		}
		
		WritableWorkbook wwb = Workbook.createWorkbook(os);
		XcxyXszzDao dao = new XcxyXszzDao();
		// 填充内容
		List<HashMap<String, String>> list = dao.getXxCsList(nd, tableName,
				userDep,form.getXmdm());

		WritableSheet ws = wwb.createSheet(title + "统计表", 0);

		WritableCellFormat wcf2 = new WritableCellFormat(); // 构造单元格格式
		wcf2 = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, Border.ALL);
		// 填充标题
		ExcelMB ex = new ExcelMB();
		ws.mergeCells(0, 0, 8, 0);
		ex.printToOneCell_multy(ws, content, 0, 0, 20, true, Alignment.CENTRE,
				VerticalAlignment.CENTRE, true, 650, Border.NONE);
		// 填充表头);
		ws.mergeCells(0, 1, 8, 2);
		ex.printToOneCell_multy(ws, topInfo, 0, 1, 10, true, Alignment.CENTRE,
				VerticalAlignment.CENTRE, true, 650, Border.NONE);

		ws.addCell(new Label(0, 3, "序号", wcf2)); // 添加有指定格式的单元格值
		ws.addCell(new Label(1, 3, "姓名", wcf2)); // 添加有指定格式的单元格值
		ws.addCell(new Label(2, 3, "身份证号", wcf2)); // 添加有指定格式的单元格值
		ws.addCell(new Label(3, 3, "院系", wcf2)); // 添加有指定格式的单元格值
		ws.addCell(new Label(4, 3, "专业", wcf2)); // 添加有指定格式的单元格值
		ws.addCell(new Label(5, 3, "学号", wcf2)); // 添加有指定格式的单元格值
		ws.addCell(new Label(6, 3, "性别", wcf2)); // 添加有指定格式的单元格值
		ws.addCell(new Label(7, 3, "民族", wcf2)); // 添加有指定格式的单元格值
		ws.addCell(new Label(8, 3, "入学年月", wcf2)); // 添加有指定格式的单元格值

		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				ws.addCell(new Label(0, 4 + i, String.valueOf(i + 1), wcf2)); // 添加有指定格式的单元格值
				ws.addCell(new Label(1, 4 + i, list.get(i).get("xm"), wcf2)); // 添加有指定格式的单元格值
				ws.addCell(new Label(2, 4 + i, list.get(i).get("sfzh"), wcf2)); // 添加有指定格式的单元格值
				ws.addCell(new Label(3, 4 + i, list.get(i).get("xymc"), wcf2)); // 添加有指定格式的单元格值
				ws.addCell(new Label(4, 4 + i, list.get(i).get("zymc"), wcf2)); // 添加有指定格式的单元格值
				ws.addCell(new Label(5, 4 + i, list.get(i).get("xh"), wcf2)); // 添加有指定格式的单元格值
				ws.addCell(new Label(6, 4 + i, list.get(i).get("xb"), wcf2)); // 添加有指定格式的单元格值
				ws.addCell(new Label(7, 4 + i, list.get(i).get("mzmc"), wcf2)); // 添加有指定格式的单元格值
				ws.addCell(new Label(8, 4 + i, list.get(i).get("rxrq"), wcf2)); // 添加有指定格式的单元格值
			}
		}
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	/*
	 * 执行学生处助学金列表打印
	 */
	public void printXscZxjList(String userType,String xmlc,
			XcxyXszzForm form, OutputStream os, String title) throws Exception {

		String nd = form.getNd();
		
		String	content = "西昌学院"+nd + "年度"+form.getXmmc()+"发放表";
		String	topInfo = "填写部门:学生处                                     单位：人，万元";

		
		
		WritableWorkbook wwb = Workbook.createWorkbook(os);
		XcxyXszzDao dao = new XcxyXszzDao();
		// 填充内容
		List<HashMap<String, String>> list = dao.getXscZxjList(nd,form.getXmdm(),form.getTableName());

		WritableSheet ws = wwb.createSheet(title + "统计表", 0);

		WritableCellFormat wcf2 = new WritableCellFormat(); // 构造单元格格式
		wcf2 = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, Border.ALL);
		// 填充标题
		ExcelMB ex = new ExcelMB();
		ws.mergeCells(0, 0, 6, 0);
		ex.printToOneCell_multy(ws, content, 0, 0, 20, true, Alignment.CENTRE,
				VerticalAlignment.CENTRE, true, 650, Border.NONE);
		// 填充表头);
		ws.mergeCells(0, 1, 6, 2);
		ex.printToOneCell_multy(ws, topInfo, 0, 1, 10, true, Alignment.CENTRE,
				VerticalAlignment.CENTRE, false, 650, Border.NONE);

		ws.mergeCells(0, 3, 0, 4);
		ws.addCell(new Label(0, 3, "系别", wcf2)); // 添加有指定格式的单元格值
		ws.mergeCells(1, 3, 4, 3);
		ws.addCell(new Label(1, 3, "受助学生数", wcf2)); // 添加有指定格式的单元格值
		ws.addCell(new Label(1, 4, "合计", wcf2)); // 添加有指定格式的单元格值
		ws.addCell(new Label(2, 4, "一档", wcf2)); // 添加有指定格式的单元格值
		ws.addCell(new Label(3, 4, "二档", wcf2)); // 添加有指定格式的单元格值
		ws.addCell(new Label(4, 4, "三档", wcf2)); // 添加有指定格式的单元格值
		ws.mergeCells(5, 3, 5, 4);
		ws.addCell(new Label(5, 3, "资助金额", wcf2)); // 添加有指定格式的单元格值
		ws.mergeCells(6, 3, 6, 4);
		ws.addCell(new Label(6, 3, "备注", wcf2)); // 添加有指定格式的单元格值

		int zrs=0;
		int zone=0;
		int ztwo=0;
		int zthree=0;
		float zje=0;
		
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				ws.addCell(new Label(0, 5 + i, list.get(i).get("xymc"), wcf2)); // 添加有指定格式的单元格值
				ws.addCell(new Label(1, 5 + i, list.get(i).get("num"), wcf2)); // 添加有指定格式的单元格值
				if(!Base.isNull(list.get(i).get("num"))){
					zrs+=Integer.parseInt(list.get(i).get("num"));
				}
				ws.addCell(new Label(2, 5 + i, list.get(i).get("one"), wcf2)); // 添加有指定格式的单元格值
				if(!Base.isNull(list.get(i).get("one"))){
					zone+=Integer.parseInt(list.get(i).get("one"));
				}
				ws.addCell(new Label(3, 5 + i, list.get(i).get("two"), wcf2)); // 添加有指定格式的单元格值
				if(!Base.isNull(list.get(i).get("two"))){
					ztwo+=Integer.parseInt(list.get(i).get("two"));
				}
				ws.addCell(new Label(4, 5 + i, list.get(i).get("three"), wcf2)); // 添加有指定格式的单元格值
				if(!Base.isNull(list.get(i).get("three"))){
					zthree+=Integer.parseInt(list.get(i).get("three"));
				}
				ws.addCell(new Label(5, 5 + i, list.get(i).get("zje"), wcf2)); // 添加有指定格式的单元格值
				if(!Base.isNull(list.get(i).get("zje"))){
					zje+=Float.parseFloat((list.get(i).get("zje")));
				}
				ws.addCell(new Label(6, 5 + i, "", wcf2)); // 添加有指定格式的单元格值	
			}
			ws.addCell(new Label(0, 5 + list.size(),"合计", wcf2)); // 添加有指定格式的单元格值
			ws.addCell(new Label(1, 5 + list.size(), String.valueOf(zrs), wcf2)); // 添加有指定格式的单元格值
			ws.addCell(new Label(2, 5 + list.size(), String.valueOf(zone), wcf2)); // 添加有指定格式的单元格值
			ws.addCell(new Label(3, 5 + list.size(), String.valueOf(ztwo), wcf2)); // 添加有指定格式的单元格值
			ws.addCell(new Label(4, 5 + list.size(), String.valueOf(zthree), wcf2)); // 添加有指定格式的单元格值
			ws.addCell(new Label(5, 5 + list.size(), String.valueOf(zje), wcf2)); // 添加有指定格式的单元格值
			ws.addCell(new Label(6, 5 + list.size(), "", wcf2)); // 添加有指定格式的单元格值
		}
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	/*
	 * 广州助学金名单表
	 */
	public void gzzxjmdb_ser(XcxyXszzForm form,String userType,OutputStream os)throws Exception{
		XcxyXszzDao dao = new XcxyXszzDao();
		String xmmc = form.getXmmc();
		String nd   = form.getNd();
		String xmdm = form.getXmdm();
		WritableWorkbook wwb = Workbook.createWorkbook(os);
		WritableSheet ws = wwb.createSheet(xmmc + "名单表", 0);
		ExcelMB mb = new ExcelMB();
		WritableCellFormat wcf = new WritableCellFormat();
		wcf.setBorder(Border.ALL, BorderLineStyle.THIN);
		wcf.setAlignment(Alignment.CENTRE);
        wcf.setWrap(true);
		ws.mergeCells(0, 0, 9, 3);
		
		mb.printToOneCell_multy(ws, Base.xxmc+nd+ form.getXmmc()+ "受助贫困学生名单", 0, 0, 18, true, Alignment.CENTRE,
				VerticalAlignment.CENTRE, true, 1000, Border.NONE);
		ws.mergeCells(0, 4, 3, 4);		
		mb.printToOneCell_multy(ws, "部门：", 0, 4, 12, false,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, 400,
				Border.NONE);
		ws.mergeCells(4, 4, 9, 4);
		mb.printToOneCell_multy(ws, "制表时间：" + GetTime.getNowTime(), 4, 4, 12,
				false, Alignment.CENTRE, VerticalAlignment.CENTRE, true, 400,
				Border.NONE);
		ws.addCell(new Label(0, 5, "序号",wcf));
		ws.addCell(new Label(1, 5, "姓名",wcf));
		ws.addCell(new Label(2, 5, "性别",wcf));
		ws.addCell(new Label(3, 5, "民族",wcf));
		ws.addCell(new Label(4, 5, "家庭住址",wcf));
		ws.mergeCells(5, 5, 8, 5);
		ws.addCell(new Label(5, 5, "现在就读"+Base.YXPZXY_KEY+"、专业、班级",wcf));
		ws.addCell(new Label(9, 5, "备注",wcf));
		List<HashMap<String,String>> list = dao.getGzzxjmdbPtInfo(nd,xmdm,form.getTableName(),userType,form.getXydm());
		for(int i=0;i<list.size();i++){			
			mb.printToOneCell_multy(ws, list.get(i).get("r"),0,i+6,10,false,Alignment.CENTRE,VerticalAlignment.CENTRE,true,10,Border.ALL);
			mb.printToOneCell_multy(ws, list.get(i).get("xm"),1,i+6,10,false,Alignment.CENTRE,VerticalAlignment.CENTRE,true,10,Border.ALL);
			mb.printToOneCell_multy(ws, list.get(i).get("xb"),2,i+6,10,false,Alignment.CENTRE,VerticalAlignment.CENTRE,true,10,Border.ALL);
			mb.printToOneCell_multy(ws, list.get(i).get("mzmc"),3,i+6,10,false,Alignment.CENTRE,VerticalAlignment.CENTRE,true,10,Border.ALL);
			mb.printToOneCell_multy(ws, list.get(i).get("jtdz"),4,i+6,10,false,Alignment.CENTRE,VerticalAlignment.CENTRE,true,10,Border.ALL);
			ws.mergeCells(5, i+6, 8, i+6);
			mb.printToOneCell_multy(ws, list.get(i).get("jdqk"),5,i+6,10,false,Alignment.CENTRE,VerticalAlignment.CENTRE,true,10,Border.ALL);
			mb.printToOneCell_multy(ws, " ",9,i+6,10,false,Alignment.CENTRE,VerticalAlignment.CENTRE,true,10,Border.ALL);
		}	
		wwb.write();
		wwb.close();
	}
	/*
	 * 广州助学金发放表
	 */
	public void gzzxjffb_ser(XcxyXszzForm form,String userType,OutputStream os)throws Exception{
		XcxyXszzDao dao = new XcxyXszzDao();
		String xmmc = form.getXmmc();
		String nd   = form.getNd();
		String xmdm = form.getXmdm();
		WritableWorkbook wwb = Workbook.createWorkbook(os);
		WritableSheet ws = wwb.createSheet(xmmc + "发放表", 0);
		ExcelMB mb = new ExcelMB();
		WritableCellFormat wcf = new WritableCellFormat();
		wcf.setBorder(Border.ALL, BorderLineStyle.THIN);
		wcf.setAlignment(Alignment.CENTRE);
        wcf.setWrap(true);
		ws.mergeCells(0, 0, 8, 3);
		
		mb.printToOneCell_multy(ws, Base.xxmc+nd+ form.getXmmc()+ "发放表", 0, 0, 18, true, Alignment.CENTRE,
				VerticalAlignment.CENTRE, true, 1000, Border.NONE);
		ws.mergeCells(0, 4, 3, 4);		
		mb.printToOneCell_multy(ws, "部门：", 0, 4, 12, false,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, 400,
				Border.NONE);
		ws.mergeCells(4, 4, 8, 4);
		mb.printToOneCell_multy(ws, "制表时间：" + GetTime.getNowTime(), 4, 4, 12,
				false, Alignment.CENTRE, VerticalAlignment.CENTRE, true, 400,
				Border.NONE);
		ws.addCell(new Label(0, 5, "序号",wcf));
		ws.addCell(new Label(1, 5, "姓名",wcf));
		ws.addCell(new Label(2, 5, "系别",wcf));
		ws.addCell(new Label(3, 5, "年级专业",wcf));
		ws.mergeCells(3, 5, 5, 5);
		ws.addCell(new Label(6, 5, "金额",wcf));		
		ws.addCell(new Label(7, 5, "领款人签字",wcf));
		ws.addCell(new Label(8, 5, "备注",wcf));
		List<HashMap<String,String>> list = dao.getJxjxyffbPtInfo(nd,xmdm,form.getTableName(),userType,form.getXydm());
		for(int i=0;i<list.size();i++){			
			mb.printToOneCell_multy(ws, list.get(i).get("r"),0,i+6,10,false,Alignment.CENTRE,VerticalAlignment.CENTRE,true,10,Border.ALL);
			mb.printToOneCell_multy(ws, list.get(i).get("xm"),1,i+6,10,false,Alignment.CENTRE,VerticalAlignment.CENTRE,true,10,Border.ALL);
			mb.printToOneCell_multy(ws, list.get(i).get("xymc"),2,i+6,10,false,Alignment.CENTRE,VerticalAlignment.CENTRE,true,10,Border.ALL);
			ws.mergeCells(3, i+6, 5, i+6);
			mb.printToOneCell_multy(ws, list.get(i).get("njzy"),3,i+6,10,false,Alignment.CENTRE,VerticalAlignment.CENTRE,true,10,Border.ALL);			
			mb.printToOneCell_multy(ws, list.get(i).get("zzje"),6,i+6,10,false,Alignment.CENTRE,VerticalAlignment.CENTRE,true,10,Border.ALL);			;
			mb.printToOneCell_multy(ws, " ",7,i+6,10,false,Alignment.CENTRE,VerticalAlignment.CENTRE,true,10,Border.ALL);
			mb.printToOneCell_multy(ws, " ",8,i+6,10,false,Alignment.CENTRE,VerticalAlignment.CENTRE,true,10,Border.ALL);
		}	
		wwb.write();
		wwb.close();
	}
	/*
	 * 加拿大福慧基金会励志奖学金发放表
	 */
	public void jndlzjxjffb_ser(XcxyXszzForm form,String userType,OutputStream os)throws Exception{
		XcxyXszzDao dao = new XcxyXszzDao();
		String xmmc = form.getXmmc();
		String nd   = form.getNd();
		String xmdm = form.getXmdm();
		WritableWorkbook wwb = Workbook.createWorkbook(os);
		WritableSheet ws = wwb.createSheet(xmmc + "发放表", 0);
		ExcelMB mb = new ExcelMB();
		WritableCellFormat wcf = new WritableCellFormat();
		wcf.setBorder(Border.ALL, BorderLineStyle.THIN);
		wcf.setAlignment(Alignment.CENTRE);
        wcf.setWrap(true);
		ws.mergeCells(0, 0, 8, 3);		
		mb.printToOneCell_multy(ws, Base.xxmc+nd+ form.getXmmc()+ "励志奖学金发放表", 0, 0, 18, true, Alignment.CENTRE,
				VerticalAlignment.CENTRE, true, 1000, Border.NONE);
		ws.mergeCells(0, 4, 2, 4);		
		mb.printToOneCell_multy(ws, "部门：", 0, 4, 12, false,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, 300,
				Border.NONE);
		ws.mergeCells(3, 4, 5, 4);
		mb.printToOneCell_multy(ws, "制表时间：" + GetTime.getNowTime(), 3, 4, 12,
				false, Alignment.CENTRE, VerticalAlignment.CENTRE, true, 300,
				Border.NONE);
		ws.mergeCells(6, 4, 8, 4);
		mb.printToOneCell_multy(ws, "单位：  人,元" , 6, 4, 12,
				false, Alignment.CENTRE, VerticalAlignment.CENTRE, true, 300,
				Border.NONE);
		ws.addCell(new Label(0, 5, "序号",wcf));
		ws.addCell(new Label(1, 5, "姓名",wcf));
		ws.addCell(new Label(2, 5, "系别",wcf));
		ws.addCell(new Label(3, 5, "年级专业",wcf));
		ws.mergeCells(3, 5, 5, 5);
		ws.addCell(new Label(6, 5, "金额",wcf));		
		ws.addCell(new Label(7, 5, "领款人签字",wcf));
		ws.addCell(new Label(8, 5, "备注",wcf));
		List<HashMap<String,String>> list = dao.getJndlzjxjffbPtInfo(nd,xmdm,form.getTableName(),userType,form.getXydm());
		for(int i=0;i<list.size();i++){			
			mb.printToOneCell_multy(ws, list.get(i).get("r"),0,i+6,10,false,Alignment.CENTRE,VerticalAlignment.CENTRE,true,10,Border.ALL);
			mb.printToOneCell_multy(ws, list.get(i).get("xm"),1,i+6,10,false,Alignment.CENTRE,VerticalAlignment.CENTRE,true,10,Border.ALL);
			mb.printToOneCell_multy(ws, list.get(i).get("xymc"),2,i+6,10,false,Alignment.CENTRE,VerticalAlignment.CENTRE,true,10,Border.ALL);
			ws.mergeCells(3, i+6, 5, i+6);
			mb.printToOneCell_multy(ws, list.get(i).get("njzy"),3,i+6,10,false,Alignment.CENTRE,VerticalAlignment.CENTRE,true,10,Border.ALL);			
			mb.printToOneCell_multy(ws, list.get(i).get("zzje"),6,i+6,10,false,Alignment.CENTRE,VerticalAlignment.CENTRE,true,10,Border.ALL);			;
			mb.printToOneCell_multy(ws, " ",7,i+6,10,false,Alignment.CENTRE,VerticalAlignment.CENTRE,true,10,Border.ALL);
			mb.printToOneCell_multy(ws, " ",8,i+6,10,false,Alignment.CENTRE,VerticalAlignment.CENTRE,true,10,Border.ALL);
		}	
		wwb.write();
		wwb.close();
	}
	/*
	 * 加拿大福慧基金会励志奖学金名单表
	 */
	public void jndlzjxjmdb_ser(XcxyXszzForm form,String userType,OutputStream os)throws Exception{
		XcxyXszzDao dao = new XcxyXszzDao();
		String xmmc = form.getXmmc();
		String nd   = form.getNd();
		String xmdm = form.getXmdm();
		WritableWorkbook wwb = Workbook.createWorkbook(os);
		WritableSheet ws = wwb.createSheet(xmmc + "名单表", 0);
		ExcelMB mb = new ExcelMB();
		WritableCellFormat wcf = new WritableCellFormat();
		wcf.setBorder(Border.ALL, BorderLineStyle.THIN);
		wcf.setAlignment(Alignment.CENTRE);
        wcf.setWrap(true);
		ws.mergeCells(0, 0, 8, 3);
		
		mb.printToOneCell_multy(ws, Base.xxmc+nd+ form.getXmmc()+ "励志奖学金名单", 0, 0, 13, true, Alignment.CENTRE,
				VerticalAlignment.CENTRE, true, 1000, Border.NONE);
		ws.mergeCells(0, 4, 4, 4);		
		mb.printToOneCell_multy(ws, "部门：", 0, 4, 12, false,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, 400,
				Border.NONE);
		ws.mergeCells(5, 4, 8, 4);
		mb.printToOneCell_multy(ws, "制表时间：" + GetTime.getNowTime(), 5, 4, 12,
				false, Alignment.CENTRE, VerticalAlignment.CENTRE, true, 400,
				Border.NONE);		
		ws.addCell(new Label(0, 5, "序号",wcf));
		ws.addCell(new Label(1, 5, "姓名",wcf));
		ws.mergeCells(2, 5, 3, 5);
		ws.addCell(new Label(2, 5, "系别",wcf));
		ws.mergeCells(4, 5, 7, 5);
		ws.addCell(new Label(4, 5, "年级专业",wcf));
		ws.addCell(new Label(8, 5, "备注",wcf));
		List<HashMap<String,String>> list = dao.getJndlzjxjmdbPtInfo(nd,xmdm,form.getTableName(),userType,form.getXydm());
		for(int i=0;i<list.size();i++){			
			mb.printToOneCell_multy(ws, list.get(i).get("r"),0,i+6,10,false,Alignment.CENTRE,VerticalAlignment.CENTRE,true,10,Border.ALL);
			mb.printToOneCell_multy(ws, list.get(i).get("xm"),1,i+6,10,false,Alignment.CENTRE,VerticalAlignment.CENTRE,true,10,Border.ALL);
			ws.mergeCells(2, i+6, 3, i+6);
			mb.printToOneCell_multy(ws, list.get(i).get("xymc"),2,i+6,10,false,Alignment.CENTRE,VerticalAlignment.CENTRE,true,10,Border.ALL);
			ws.mergeCells(4, i+6, 7, i+6);
			mb.printToOneCell_multy(ws, list.get(i).get("njzy"),4,i+6,10,false,Alignment.CENTRE,VerticalAlignment.CENTRE,true,10,Border.ALL);
			mb.printToOneCell_multy(ws, " ",8,i+6,10,false,Alignment.CENTRE,VerticalAlignment.CENTRE,true,10,Border.ALL);
		}	
		wwb.write();
		wwb.close();
	}
	
	/*
	 * 国家助学金发放表打印(院系统计)
	 */
	public void printXsZxjList_yx(XcxyXszzForm form,String userType,OutputStream os)throws Exception{
		XcxyXszzDao dao = new XcxyXszzDao();
		String xmmc = form.getXmmc();
		String xymc = form.getXymc();
		String xmdm = form.getXmdm();
		String xydm = form.getXydm();
		String nd   = form.getNd();
		WritableWorkbook wwb = Workbook.createWorkbook(os);
		WritableSheet ws = wwb.createSheet(xmmc + "发放表", 0);
		ExcelMB mb = new ExcelMB();
		WritableCellFormat wcf = new WritableCellFormat();
		wcf.setBorder(Border.ALL, BorderLineStyle.THIN);
		wcf.setAlignment(Alignment.CENTRE);
		ws.mergeCells(0, 0, 10, 1);
		mb.printToOneCell_multy(ws, Base.xxmc+nd + xmmc+"发放表" , 0, 0, 18, true, Alignment.CENTRE,
				VerticalAlignment.CENTRE, false, 1000, Border.NONE);
		ws.mergeCells(0, 2, 5, 3);
		ws.mergeCells(6, 2, 10, 3);
		mb.printToOneCell_multy(ws, "系部名称"+xymc, 0, 2, 12, false,
				Alignment.CENTRE, VerticalAlignment.CENTRE, false, 300,
				Border.NONE);
		mb.printToOneCell_multy(ws, "填报时间" + GetTime.getNowTime(), 6, 2, 12,
				false, Alignment.CENTRE, VerticalAlignment.CENTRE, false, 300,
				Border.NONE);		
		ws.mergeCells(0, 4, 0, 5);
		mb.printToOneCell_multy(ws, "序号", 0, 4, 10, false, Alignment.CENTRE,
				VerticalAlignment.CENTRE, false, 300, Border.ALL);
		ws.mergeCells(1, 4, 1, 5);
		mb.printToOneCell_multy(ws, "姓名", 1, 4, 10, false, Alignment.CENTRE,
				VerticalAlignment.CENTRE, false, 300, Border.ALL);
		ws.mergeCells(2, 4, 2, 5);
		mb.printToOneCell_multy(ws, "专业", 2, 4, 10, false, Alignment.CENTRE,
				VerticalAlignment.CENTRE, false, 300, Border.ALL);
		ws.mergeCells(3, 4, 3, 5);
		mb.printToOneCell_multy(ws, "身份证号",3, 4, 10, false, Alignment.CENTRE,
				VerticalAlignment.CENTRE, false, 300, Border.ALL);
		ws.mergeCells(4, 4, 4, 5);
		mb.printToOneCell_multy(ws, "银行账号",4, 4, 10, false, Alignment.CENTRE,
				VerticalAlignment.CENTRE, false, 300, Border.ALL);
		
		ws.mergeCells(5, 4, 7, 4);
		mb.printToOneCell_multy(ws, "资助档次", 5, 4, 10, false, Alignment.CENTRE,
				VerticalAlignment.CENTRE, false, 300, Border.ALL);
		ws.addCell(new Label(5, 5, "一档",wcf));
		ws.addCell(new Label(6, 5, "二档",wcf));
		ws.addCell(new Label(7, 5, "三档",wcf));
		ws.mergeCells(8, 4, 8, 5);
		mb.printToOneCell_multy(ws, "资助金额", 8, 4, 10, false, Alignment.CENTRE,
				VerticalAlignment.CENTRE, false, 300, Border.ALL);
		ws.mergeCells(9, 4, 9, 5);
		mb.printToOneCell_multy(ws, "学生签字", 9, 4, 10, false,
				Alignment.CENTRE, VerticalAlignment.CENTRE, false, 300,
				Border.ALL);
		ws.mergeCells(10, 4, 10, 5);
		mb.printToOneCell_multy(ws, "备注", 10, 4, 10, false,
				Alignment.CENTRE, VerticalAlignment.CENTRE, false, 300,
				Border.ALL);	
		List<HashMap<String,String>> list = dao.getXsZxjList_yx(nd,xmdm,xydm,form.getTableName());
		HashMap<String,String> map = new HashMap<String,String>();
		for(int i=0;i<list.size();i++){
			map = list.get(i);
			ws.addCell(new Label(0, 6+i, map.get("rownum"),wcf));
			ws.addCell(new Label(1, 6+i, map.get("xm"),wcf));
			ws.addCell(new Label(2, 6+i, map.get("zymc"),wcf));
			ws.addCell(new Label(3, 6+i, map.get("sfzh"),wcf));
			ws.addCell(new Label(4, 6+i, map.get("yhkh"),wcf));		
			ws.addCell(new Label(8, 6+i, map.get("zzje"),wcf));
			
			ws.addCell(new Label(9, 6+i, " ",wcf));
			ws.addCell(new Label(10, 6+i, " ",wcf));
			String dc1 = "";
			String dc2 = "";
			String dc3 = "";
			if("1".equals(map.get("dc"))){
				dc1 = "1";
			}else if("2".equals(map.get("dc"))){
				dc2 = "1";
			}else{
				dc3 = "1";
			}
			ws.addCell(new Label(5, 6+i, dc1,wcf));
			ws.addCell(new Label(6, 6+i, dc2,wcf));
			ws.addCell(new Label(7, 6+i, dc3,wcf));			
		}

		wwb.write();
		wwb.close();
	}

}
