/**
 * @部门:学工产品事业部
 * @日期：2016-8-18 上午10:35:18 
 */  
package com.zfsoft.xgxt.xsxx.xsxxgl.bddc;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zfsoft.xgxt.szdw.fdyxx.FdyxxService;
import net.sf.json.JSONObject;
import net.sf.json.JSONString;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import sun.misc.BASE64Decoder;

import xgxt.action.Base;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;
import xsgzgl.qgzx.kycxgl.kyxmgl.KyxmglService;
import xsgzgl.xsxx.general.xsxxgl.XsxxtyglService;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Cell;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Row;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.rtf.RtfWriter2;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.ZipUtil;
import com.zfsoft.xgxt.comm.zdybd.dao.ZdybdBaseDao;
import com.zfsoft.xgxt.dtjs.dtxxgl.dtxxjg.DtxxjgService;
import com.zfsoft.xgxt.gygl.qsdsgl.QsdswhService;
import com.zfsoft.xgxt.rcsw.cwsjcx.CwsjService;
import com.zfsoft.xgxt.rcsw.rcxwwh.rcxwjg.RcxwjgService;
import com.zfsoft.xgxt.xpjpy.wdpj.pjjg.PjjgService;
import com.zfsoft.xgxt.xpjpy.zhcp.zcfs.ZcfsService;
import com.zfsoft.xgxt.xstgl.sthdgl.sthdjg.SthdjgService;
import com.zfsoft.xgxt.xsxx.xjyd.xjydjg.XjydjgService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxForm;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import com.zfsoft.xgxt.xsxx.xsxxgl.xxgl.XsxxglService;
import com.zfsoft.xgxt.xszz.knsjg.KnsjgService;
import com.zfsoft.xgxt.xszz.zzxmjg.ZzxmjgService;
import com.zfsoft.xgxt.zxdk.dkbc.bcjg.BcjgService;
import com.zfsoft.xgxt.zxdk.rwfbybc.dcjg.RwfbydcjgService;
import com.zfsoft.xgxt.zxdk.syddk.SyddkService;
import com.zfsoft.xgxt.zxdk.xyddk.DkjgService;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： yxy[工号:1206]
 * @时间： 2016-8-18 上午10:35:18 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ExportService extends SuperServiceImpl<ExportModel, ExportDao> {
	/**
	 * 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：yxy[工号：1206]
	 * @日期：2016-8-23 上午08:44:17
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getGnmkList(ExportModel exportmodel,User user,String type){
		String zgh = user.getUserName();
		String[] selectzd   = exportmodel.getSelectCol();
		List<HashMap<String, String>> gndmlist = null;
		if(null != selectzd && selectzd.length > 0){
			
		}else{
			gndmlist = dao.getGnmkInSzb(zgh,type);
			if(null != gndmlist && gndmlist.size() > 0){
				return gndmlist;
			}else{
				gndmlist = dao.getGnmkInSzb("public",type);
				if(null != gndmlist && gndmlist.size() > 0){
					return gndmlist;
				}else{
					gndmlist = dao.getGnmkMrList();
					
				}
			}
		}
		return gndmlist;
		
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述: 保存设置并导出
	 * @作者：yxy[工号：1206]
	 * @日期：2016-8-23 上午10:57:47
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean saveBcszDc(ExportModel model) throws Exception{
		//selectCol 
		String[] selectCol = model.getSelectCol();
		//选中列的长度
		int sellen = selectCol.length;
		if(sellen == 1 && StringUtils.isNull(selectCol[0]) ){
			sellen = 0;
		}
		//unselectCol
		//保存之前先删除设置表中原有的数据
		dao.delGndm(model.getZgh());
		//未选中列的长度
		String[] unselectCol = model.getUnselectCol();
		int unsellen = unselectCol.length;
		if(unsellen == 1 && StringUtils.isNull(unselectCol[0]) ){
			unsellen = 0;
		}
		if(0 == sellen && 0 == unsellen){
			return false;
		}
		return dao.saveBcszDc(selectCol, unselectCol, sellen, unsellen, model.getZgh());
	}
	
	/**
	 * @throws DocumentException 
	 * @throws IOException 
	 * 
	 * @描述: 生成单个word文件
	 * @作者：yxy[工号：1206]
	 * @日期：2016-8-23 下午05:42:43
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * File 返回类型 
	 * @throws
	 */
	public File createWord(ExportModel model) throws Exception{
		String xh = model.getXh();
		String filepath = model.getFilepath();
		//先去检验该用户文件夹存不存在
		File filefolder = new File(filepath);
		if(!filefolder.exists()){
			filefolder.mkdirs();
		}
		String path = model.getFilepath()+xh+"学生信息导出.doc";
		//File file = new File();
		File file = new File(path);
		if(file.exists()){
			file.delete();
		}
		file.createNewFile();
		
		Document document = new Document(PageSize.A4);  
		RtfWriter2.getInstance(document,new FileOutputStream(file));
		document.open();
		 //设置中文字体  
        BaseFont bfChinese = BaseFont.createFont("STSongStd-Light","UniGB-UCS2-H",BaseFont.NOT_EMBEDDED);  
        //标题字体风格  
        Font titleFont = new Font(bfChinese,16,Font.BOLD);  
        //正文字体风格  
        Font contextFont = new Font(bfChinese,13,Font.NORMAL);  
        Paragraph title = new Paragraph("学生信息导出",titleFont); 
    	title.setAlignment(Element.ALIGN_CENTER);
		title.setIndentationLeft(5);
		title.setIndentationRight(5);
		document.add(title);
		//获取被选中的列
	    String[] selectCol = model.getSelectCol();
	    List<String> selectColArr = new ArrayList<String>();
	    List<String> selectColNameArr = new ArrayList<String>();
        HashMap<String, String> zdyMap = new XsxxglService().getXsxxByXh(xh);
	    for (int i = 0; i < selectCol.length; i++) {
			String[] lsArr = selectCol[i].split("@");
			selectColArr.add(lsArr[0]);
			selectColNameArr.add(lsArr[1]);
		} 
	    List<HashMap<String, String>> selectColListInFlszb = dao.getSelectMkinFlszb(selectColArr.toArray(new String[]{}));
	    //按续画出各个模块表单
	    for (int i = 0; i < selectCol.length; i++) {
	    	HashMap<String, String>lsMap = null;
	    	for (int j = 0; j < selectColListInFlszb.size(); j++) {
				if(selectColArr.get(i).equals(selectColListInFlszb.get(j).get("flszid")) ){
					lsMap = selectColListInFlszb.get(j);
					break;
				}
			}
	    	if(null == lsMap){
	    		continue;
	    	}
	    	Table table = this.writeTable(lsMap, selectColNameArr.get(i),xh,zdyMap,model);
	    	if(null != table){
	    		document.add(table);
	    	}
	    	
		}
		document.close();
		return file;
	}
	//参考学生信息改的，一些变量没改名字，如xh应该是zgh
	public File createWordForTea(ExportModel model) throws Exception{
		String xh = model.getXh();
		String filepath = model.getFilepath();
		//先去检验该用户文件夹存不存在
		File filefolder = new File(filepath);
		if(!filefolder.exists()){
			filefolder.mkdirs();
		}
		String path = model.getFilepath()+xh+"教师信息导出.doc";
		//File file = new File();
		File file = new File(path);
		if(file.exists()){
			file.delete();
		}
		file.createNewFile();

		Document document = new Document(PageSize.A4);
		RtfWriter2.getInstance(document,new FileOutputStream(file));
		document.open();
		//设置中文字体
		BaseFont bfChinese = BaseFont.createFont("STSongStd-Light","UniGB-UCS2-H",BaseFont.NOT_EMBEDDED);
		//标题字体风格
		Font titleFont = new Font(bfChinese,16,Font.BOLD);
		//正文字体风格
		Font contextFont = new Font(bfChinese,13,Font.NORMAL);
		Paragraph title = new Paragraph("教师信息导出",titleFont);
		title.setAlignment(Element.ALIGN_CENTER);
		title.setIndentationLeft(5);
		title.setIndentationRight(5);
		document.add(title);
		//获取被选中的列
		String[] selectCol = model.getSelectCol();
		List<String> selectColArr = new ArrayList<String>();
		List<String> selectColNameArr = new ArrayList<String>();
		HashMap<String, String> zdyMap = new FdyxxService().getFdyInfo(xh);
		for (int i = 0; i < selectCol.length; i++) {
			String[] lsArr = selectCol[i].split("@");
			selectColArr.add(lsArr[0]);
			selectColNameArr.add(lsArr[1]);
		}
		List<HashMap<String, String>> selectColListInFlszb = dao.getSelectMkinFlszb(selectColArr.toArray(new String[]{}));
		//按续画出各个模块表单
		for (int i = 0; i < selectCol.length; i++) {
			HashMap<String, String>lsMap = null;
			for (int j = 0; j < selectColListInFlszb.size(); j++) {
				if(selectColArr.get(i).equals(selectColListInFlszb.get(j).get("flszid")) ){
					lsMap = selectColListInFlszb.get(j);
					break;
				}
			}
			if(null == lsMap){
				continue;
			}
			Table table = this.writeTableForTea(lsMap, selectColNameArr.get(i),xh,zdyMap,model);
			if(null != table){
				document.add(table);
			}

		}
		document.close();
		return file;
	}
	/**
	 * @throws Exception 
	 * 
	 * @描述: 生成word压缩包文件
	 * @作者：yxy[工号：1206]
	 * @日期：2016-8-23 下午05:42:48
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * File 返回类型 
	 * @throws
	 */
	public File createZipWord(ExportModel model) throws Exception{
		String[] xhArr = model.getXhArr();
		List<File> files = new ArrayList<File>();
		for (int i = 0; i < xhArr.length; i++) {
			model.setXh(xhArr[i]);
			File file = this.createWord(model);
			files.add(file);
		}
		File zipFile = ZipUtil.zip(files.toArray(new File[]{}));
		return zipFile;
	}
	public File createZipWordForTea(ExportModel model) throws Exception{
		String[] xhArr = model.getXhArr();
		List<File> files = new ArrayList<File>();
		for (int i = 0; i < xhArr.length; i++) {
			model.setXh(xhArr[i]);
			File file = this.createWordForTea(model);
			files.add(file);
		}
		File zipFile = ZipUtil.zip(files.toArray(new File[]{}));
		return zipFile;
	}

	
	/**
	 * 
	 * @描述: 获取中文格式
	 * @作者：yxy[工号：1206]
	 * @日期：2016-8-26 上午09:58:24
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * Font 返回类型 
	 * @throws
	 */
	public Font getFontChinese(){
		try {
		    //设置PDF支持中文
		    BaseFont bfChinese = BaseFont.createFont("STSongStd-Light","UniGB-UCS2-H", false);  
	        Font bold_fontChinese = new Font(bfChinese, 12, Font.BOLD,  Color.BLACK);
	        return bold_fontChinese;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 
	 * @描述: 获取中文格式正文格式
	 * @作者：yxy[工号：1206]
	 * @日期：2016-8-26 上午09:58:24
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * Font 返回类型 
	 * @throws
	 */
	public Font getFontChineseNormal(){
		try {
		    //设置PDF支持中文
		    BaseFont bfChinese = BaseFont.createFont("STSongStd-Light","UniGB-UCS2-H", false);  
	        Font bold_fontChinese = new Font(bfChinese, 11, Font.NORMAL,  Color.BLACK);
	        return bold_fontChinese;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	
	/**
	 * @throws Exception 
	 * 
	 * @描述: 根据业务模块画table
	 * @作者：yxy[工号：1206]
	 * @日期：2016-8-29 上午10:12:20
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param lsMap
	 * @return
	 * Table 返回类型 
	 * @throws
	 */
	public Table writeTable(HashMap<String, String> lsMap,String name,String xh, HashMap<String, String> zdyMap,ExportModel model) throws Exception{
		String bdszz = lsMap.get("bdszz");
		Table table = null;
		if(StringUtils.isNull(bdszz)){
			if("xsxx_query_xsxx_jbxx".equalsIgnoreCase(lsMap.get("flszid"))){
				table = this.writeJbxx(name, xh, lsMap, zdyMap,model.getDefaultimagepath());
			}else{
				table = this.writeZddyzdTable(name, xh, lsMap.get("flszid"), zdyMap);
			}
		}else{
			if("thtdkd:'110px'".equals(bdszz)){
				return null;
			}else if("rcxwHidDiv".equals(bdszz)){
				table = this.writeTableRcxwjl(xh, name);
			}else{//自定义表单列表模块
				JSONObject jsonParaSzz = JSONObject.fromString("{"+bdszz+"}");
			
				//列表字段
				String[] zdArr = jsonParaSzz.getString("zd").split(",");
				//列表字段名称
				String[] zdmcArr = jsonParaSzz.getString("zdmc").split(",");
				String dataSrc =  jsonParaSzz.getString("src");
				 table = this.writeListTable(zdArr, zdmcArr, dataSrc, name,xh);
			}
		}
//		Font bold_fontChinese = this.getFontChinese();
//		 //设置表头
//	    Table table = this.getTableHeader(bold_fontChinese,5);
//       
//       //设置表主体
//	    table = this.getTableBody(table, bold_fontChinese);
//       
//       //设置表尾
//         table = this.getTableBottom(table, bold_fontChinese);
         return table;
	}
	public Table writeTableForTea(HashMap<String, String> lsMap,String name,String xh, HashMap<String, String> zdyMap,ExportModel model) throws Exception{
		String bdszz = lsMap.get("bdszz");
		Table table = null;
		if(StringUtils.isNull(bdszz)){
			if("szdw_fdyxx_query_jbxx".equalsIgnoreCase(lsMap.get("flszid"))){
				table = this.writeJbxx(name, xh, lsMap, zdyMap,model.getDefaultimagepath());
			}else{
				table = this.writeZddyzdTable(name, xh, lsMap.get("flszid"), zdyMap);
			}
		}else{
			if("thtdkd:'110px'".equals(bdszz)){
				return null;
			}else if("rcxwHidDiv".equals(bdszz)){
				table = this.writeTableRcxwjl(xh, name);
			}else {//自定义表单列表模块
				JSONObject jsonParaSzz = JSONObject.fromString("{"+bdszz+"}");

				//列表字段
				String[] zdArr = jsonParaSzz.getString("zd").split(",");
				//列表字段名称
				String[] zdmcArr = jsonParaSzz.getString("zdmc").split(",");
				String dataSrc =  jsonParaSzz.getString("src");
				table = this.writeListTableForTea(zdArr, zdmcArr, dataSrc, name,xh);
			}
		}
//		Font bold_fontChinese = this.getFontChinese();
//		 //设置表头
//	    Table table = this.getTableHeader(bold_fontChinese,5);
//
//       //设置表主体
//	    table = this.getTableBody(table, bold_fontChinese);
//
//       //设置表尾
//         table = this.getTableBottom(table, bold_fontChinese);
		return table;
	}
	/**
	 * @throws Exception 
	 * @描述: 画列表展示模块
	 * @作者：yxy[工号：1206]
	 * @日期：2016-8-29 上午11:04:09
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zdArr
	 * @param zdmcArr
	 * @param dataSrc
	 * @param name
	 * @return
	 * Table 返回类型 
	 * @throws
	 */
	public Table writeListTable(String[] zdArr,String[] zdmcArr,String dataSrc,String name,String xh) throws Exception{
		Font bold_fontChinese = this.getFontChinese();
		Table table = new Table(zdArr.length);//创建一个5列的表格
        table.setAlignment(Element.ALIGN_CENTER);//居中   
        table.setAlignment(Element.ALIGN_MIDDLE);//垂直居中   
        table.setAutoFillEmptyCells(true);//自动填满
        table.setBorder(0);//设置边框
        table.setPadding(1f);//设置间距
        table.setWidth(101f);//设置表格宽度

        //表名/表注释
        Cell tableName = new Cell(new Paragraph(name,bold_fontChinese));
        tableName.setUseAscender(true);
        tableName.setUseDescender(true);
        tableName.setHorizontalAlignment(Element.ALIGN_LEFT);//水平居中
        tableName.setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中
      //  tableName.setBackgroundColor(Color.decode("#D9D9D9"));//背景颜色
        tableName.setColspan(zdArr.length);//合并单元格
        table.addCell(tableName);
        //表头
        for (int i = 0; i < zdmcArr.length; i++) {
        	  Cell columnName = new Cell(new Paragraph(zdmcArr[i],bold_fontChinese));
              columnName.setUseAscender(true);
              columnName.setUseDescender(true);
              columnName.setHorizontalAlignment(Element.ALIGN_CENTER);//水平居中
              columnName.setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中
              //columnName.setBackgroundColor(Color.decode("#D9D9D9"));//背景颜色
              table.addCell(columnName);
		}
        //表体
        //取下表体数据
        List<HashMap<String, String>> listData = this.getXsxxCklist(xh, dataSrc);
        for (int i = 0; i < listData.size(); i++) {
			for (int j = 0; j < zdArr.length; j++) {
				  Cell columnName = new Cell(new Paragraph(listData.get(i).get(zdArr[j]),bold_fontChinese));
	              columnName.setUseAscender(true);
	              columnName.setUseDescender(true);
	              columnName.setHorizontalAlignment(Element.ALIGN_CENTER);//水平居中
	              columnName.setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中
	              //columnName.setBackgroundColor(Color.decode("#D9D9D9"));//背景颜色
	              table.addCell(columnName);
			}
		}
        return table;
	}
	public Table writeListTableForTea(String[] zdArr,String[] zdmcArr,String dataSrc,String name,String xh) throws Exception{
		Font bold_fontChinese = this.getFontChinese();
		Table table = new Table(zdArr.length);//创建一个5列的表格
		table.setAlignment(Element.ALIGN_CENTER);//居中
		table.setAlignment(Element.ALIGN_MIDDLE);//垂直居中
		table.setAutoFillEmptyCells(true);//自动填满
		table.setBorder(0);//设置边框
		table.setPadding(1f);//设置间距
		table.setWidth(101f);//设置表格宽度

		//表名/表注释
		Cell tableName = new Cell(new Paragraph(name,bold_fontChinese));
		tableName.setUseAscender(true);
		tableName.setUseDescender(true);
		tableName.setHorizontalAlignment(Element.ALIGN_LEFT);//水平居中
		tableName.setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中
		//  tableName.setBackgroundColor(Color.decode("#D9D9D9"));//背景颜色
		tableName.setColspan(zdArr.length);//合并单元格
		table.addCell(tableName);
		//表头
		for (int i = 0; i < zdmcArr.length; i++) {
			Cell columnName = new Cell(new Paragraph(zdmcArr[i],bold_fontChinese));
			columnName.setUseAscender(true);
			columnName.setUseDescender(true);
			columnName.setHorizontalAlignment(Element.ALIGN_CENTER);//水平居中
			columnName.setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中
			//columnName.setBackgroundColor(Color.decode("#D9D9D9"));//背景颜色
			table.addCell(columnName);
		}
		//表体
		//取下表体数据
		List<HashMap<String, String>> listData = this.getJsxxList(xh, dataSrc);
		for (int i = 0; i < listData.size(); i++) {
			for (int j = 0; j < zdArr.length; j++) {
				Cell columnName = new Cell(new Paragraph(listData.get(i).get(zdArr[j]),bold_fontChinese));
				columnName.setUseAscender(true);
				columnName.setUseDescender(true);
				columnName.setHorizontalAlignment(Element.ALIGN_CENTER);//水平居中
				columnName.setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中
				//columnName.setBackgroundColor(Color.decode("#D9D9D9"));//背景颜色
				table.addCell(columnName);
			}
		}
		return table;
	}
	public List<HashMap<String, String>> getJsxxList(String zgh,String dataSrc) throws Exception {
		FdyxxService service = new FdyxxService();
		List<HashMap<String, String>> data ;
		if("jtcyList".equals(dataSrc)){
			return service.getJtcyxx(zgh);
		}
		if("gwydList".equals(dataSrc)){
			return service.getGwydxx(zgh);
		}
		if("jtjlList".equals(dataSrc)){
			return service.getJtjlxx(zgh);
		}
		if("zyjszwList".equals(dataSrc)){
			return service.getZyjsgwxx(zgh);
		}
		if("xxjlList".equals(dataSrc)){
			return service.getXxjlxx(zgh);
		}
		if("gzjlList".equals(dataSrc)){
			return service.getGzjlxx(zgh);
		}
		return new ArrayList<HashMap<String, String>>();
	}
	/**
	 * @throws Exception 
	 * 
	 * @描述:此方法复制于学生信息查看功能（XsxxglAction-xsxxglCk）
	 * 因为命名不规范且取值方法存在不同的类中，无法采取反射方式取值
	 * @作者：yxy[工号：1206]
	 * @日期：2016-8-29 上午11:46:00
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getXsxxCklist(String xh,String dataSrc) throws Exception{
		XsxxglService service = new XsxxglService();
		if (("stuCjList").equals(dataSrc)) {
			List<HashMap<String, String>> stuCjList = service
					.getStuCjList(xh);// 课程考试成绩
			return stuCjList;
		}
		if (("stuDjcjList").equals(dataSrc)) {
			List<HashMap<String, String>> stuDjcjList = service
					.getStuDjcjList(xh);// 等级考试成绩
			return stuDjcjList;
		}
		if (("stuQgzxXsgwxxList").equals(dataSrc)) {
			List<HashMap<String, String>> stuQgzxXsgwxxList = service
					.getStuQgzxXsgwxxList(xh);// 岗位录用情况
			return stuQgzxXsgwxxList;
		}
		if (("stuQgzxCjffList").equals(dataSrc)) {
			List<HashMap<String, String>> stuQgzxCjffList = service
					.getStuQgzxCjffList(xh);// 酬金发放情况
			return stuQgzxCjffList;
		}
		if (("stuGyxxList").equals(dataSrc)) {
			List<HashMap<String, String>> stuGyxxList = service
					.getStuGyxxList(xh);// 公寓信息
			return stuGyxxList;
		}
		if (("qswpList").equals(dataSrc)) {
			List<HashMap<String, String>> qswpList = service
					.getQswpList(xh);// 获取寝室物品列表
			return qswpList;
		}

		// 寝室导师信息列表1036
		if (("qsdsList").equals(dataSrc)) {
			QsdswhService qsdsService = new QsdswhService();
	        return qsdsService.getQsdsxxListByXh(xh);
		}
		// 公寓管理员信息（浙江机电职业技术学院）
		if (("stuGyglyxxList").equals(dataSrc)) {
			 return service.getGyglyxx(xh);
		}
		// 公寓辅导员信息（浙江机电职业技术学院）
		if (("stuGyfdyxxList").equals(dataSrc)) { 
			return service.getGyfdyxx(xh);
		}

		if (("knsInfoList").equals(dataSrc)) {
			List<HashMap<String, String>> knsInfoList = new KnsjgService()
					.getKnsInfoList(xh);// 按学号查询全部困难生信息
			return knsInfoList;
		}
		if (("zzxmjgInfoList").equals(dataSrc)) {
			List<HashMap<String, String>> zzxmjgInfoList = new ZzxmjgService()
					.getZzxmjgInfoList(xh);// 通过学号查询资助项目结果
			return zzxmjgInfoList;
		}
		if (("hjqkList").equals(dataSrc)) {
			List<HashMap<String, String>> hjqkList = new PjjgService()
					.getHjqkList(xh);// 根据学号查询获奖情况
			return hjqkList;
		}
		if (("zcfsList").equals(dataSrc)) {
			List<HashMap<String, String>> zcfsList = new ZcfsService()
					.getZcfsList(xh);// 通过学号查询终测分数
			return zcfsList;
		}
		if (("zcfsListold").equals(dataSrc)) {
			List<HashMap<String, String>> zcfsListold = new ZcfsService()
					.getZcfsListOld(xh);// 通过学号查询终老版本的测分数
		   return zcfsListold;
		}
		if (("cwsjList").equals(dataSrc)) {
			List<HashMap<String, String>> cwsjList = new CwsjService()
					.getCwsjList(xh);// 根据学号查询学生财务数据
			return cwsjList;
		}
		if (("wjcfList").equals(dataSrc)) {
			List<HashMap<String, String>> wjcfList = service
					.getWjcfList(xh);// 根据学号查询违纪处分列表
			return wjcfList;
		}
		if (("xjydList").equals(dataSrc)) {
			// List<HashMap<String, String>> xjydList = service
			// .getXjydList(xh);// 根据学号查询学籍异动列表
			List<HashMap<String, String>> xjydList = new XjydjgService()
					.getXsydList(xh);// 根据学号查询学籍异动列表
            return xjydList;
		}
		if (("jtcyxxList").equals(dataSrc)) {
			// 家庭成员信息
			List<HashMap<String, String>> jtcyxxList = service
					.getJtcyxxXsList(xh);
			return jtcyxxList;
		}
		// 学历社会经历信息
		// 1036 修改 2014-01-23
		if (("xlshjlxxList").equals(dataSrc)) {
			List<HashMap<String, String>> xlshjlxxList = service
					.getXlshjlList(xh);
			return xlshjlxxList;
		}

		// 培训信息
		if (("pxxxList").equals(dataSrc)) {
			List<HashMap<String, String>> pxxxList = service
					.getPxxxList(xh);
			return pxxxList;
		}

		// 获奖情况
		if (("rxqhjqkList").equals(dataSrc)) {
			List<HashMap<String, String>> rxqhjqkList = service
					.getHjqkList(xh);
			return rxqhjqkList;
		}
		
		// （新）获奖情况
		if (("hjqkxxNewList").equals(dataSrc)) {
			List<HashMap<String, String>> hjqkxxNewList = service
			.getHjqkNewList(xh);
			return hjqkxxNewList;
		}

		/* 思政老师列表*/
		// 辅导员老师信息列表
		if (("fdyList").equals(dataSrc)) {
			XsxxtyglService xsxxtyglService = new XsxxtyglService();
			List<HashMap<String, String>> szxxList = xsxxtyglService
					.getFdyBzrListByBjdm(dao.getBjdm(xh));
			 return xsxxtyglService.getSzxxList("fdy",szxxList);
		}
		// 班主任老师信息列表
		if (("bzrList").equals(dataSrc)) {
			XsxxtyglService xsxxtyglService = new XsxxtyglService();
			List<HashMap<String, String>> szxxList = xsxxtyglService
					.getFdyBzrListByBjdm(dao.getBjdm(xh));
			return xsxxtyglService.getSzxxList("bzr",szxxList);
		}

		// 公寓纪律处理信息列表
		if (("gyjlclxxList").equals(dataSrc)) {
		    return service.getGyjlclxxList(xh);
		}
		// 公寓异动信息列表
		if (("gyygxxList").equals(dataSrc)) {
			return service.getGyydxxList(xh);
		}

		// 公寓评优信息列表
		if (("gypyxxList").equals(dataSrc)) {
			return service.getGypyxxList(xh);
		}

		// 学生干部信息

		if (("xsgbxxList").equals(dataSrc)) {
			return service.getXsgbxxList(xh);
		}

		// 请假信息

		if (("qjxxList").equals(dataSrc)) {
			return  service.getQjjgxxList(xh);
		}

		// 假期留校信息

		if (("jqlxxxList").equals(dataSrc)) {
		    return service.getJqlxxxList(xh);
		}

		// 证件补办信息

		if (("zjbbxxList").equals(dataSrc)) {
			 return service.getZjbbxxList(xh);
		}
		

		// 公寓卫生分

		if (("gywsflist").equals(dataSrc)) {
			 return service.getGywsfList(xh);
		}
		
		// 公寓卫生分（所有记录）
		if (("gywsfAllList").equals(dataSrc)) {
			 return service.getGywsfAllList(xh);
		}

		// 火车优惠卡

		if (("hcyhkxxList").equals(dataSrc)) {
			return service.getHcyhkxxList(xh);
		}

		// 报到注册

		if (("xqzcbdxxList").equals(dataSrc)) {
			return service.getXqbdzcxxList(xh);
		}

		// 公寓违纪

		if (("gywjxxList").equals(dataSrc)) {
		    return service.getGywjxxList(xh);
		}

		// 费用发放

		if (("fyffxxList").equals(dataSrc)) {
			return service.getFyffxxList(xh);
		}
		
		//公寓卫生分 
		
		if(("gywsflist").equals(dataSrc)){
			return service.getGywsfList(xh);
		}
		

		// 获取最后一周公寓评优并且在提交检查日程的最后一周（浙江理工个性化）

		if (("lgGypyxxList").equals(dataSrc)) {
			return service.getLgGypyxxList(xh);
		}
		
		//考勤信息
		if(("kqxxList").equals(dataSrc)){
			return service.getKqxxList(xh);
		}
		
		//早晚自习考勤信息
		if(("zwzxkqxxList").equals(dataSrc)){
			return service.getZwzxKqxxList(xh);
		}
		
		//爱心超市物品申请信息
		if(("wpsqjgList").equals(dataSrc)){
			return service.getWpsqjgList(xh);
		}
		//绿色通道申请信息
		if(("lstdList").equals(dataSrc)){
		    return service.getLstdList(xh);
		}
		
		
		if(("grjdxxList").equals(dataSrc)){
			//党团信息
			DtxxjgService dtxxjgService = new DtxxjgService();
			return dtxxjgService.getGrJdxx(xh);
		}
		
		//助学贷款(生源地贷款)
		if (("syddkList").equals(dataSrc)){
			SyddkService sydService = new SyddkService();
			return sydService.getSydkList(xh);
		}
		
		//助学贷款(国家助学贷款)
		if (("gjdkList").equals(dataSrc)){
			DkjgService dkjgService = new DkjgService();
			return dkjgService.getGjdkList(xh);
		}
		
		//陕西师范基层就业补偿结果
		if (("JcjyBcjglist").equals(dataSrc)){
			BcjgService bcjg = new BcjgService();
			return bcjg.getJcjyBcjglist(xh);
		}
		
		//陕西师范入伍兵役代偿结果
		if (("Rwdcjglist").equals(dataSrc)){
			RwfbydcjgService rwdc = new RwfbydcjgService();
		    return rwdc.getRwdcjglist(xh);
		}
		
		//陕西师范志愿者结果
		if (("Sthdlist").equals(dataSrc)){
			SthdjgService stjg = new SthdjgService();
			return stjg.getSthdlist(xh);
		}
		//科研创新
		if (("Kycxlist").equals(dataSrc)){
			KyxmglService kycx = new KyxmglService();
			return kycx.getKycxList(xh);
		}
		//获奖情况OLD
		if (("hjqkListOld").equals(dataSrc)) {
			List<HashMap<String, String>> hjqkListOld = new PjjgService()
					.getHjqkListOld(xh);// 根据学号查询获奖情况
			return hjqkListOld;
		}
		
		//总学分平均绩点（浙大）
		if("10335".equals(Base.xxdm)) {
			if(("xfjdList").equals(dataSrc)) {		
		     return  service.getXfjdList(xh,service.getXsnj(xh));
			}
		}
		
		if(Base.xxdm.equalsIgnoreCase("12871")) {
			//德育等第
			if(("dyddList").equals(dataSrc)) {
				List<HashMap<String, String>> dyddList = new PjjgService()
				.getDyddList(xh);// 根据学号查询获奖情况
				return dyddList;		
			}
		}
		// 吉林工业职业技术学院
		if(Base.xxdm.equalsIgnoreCase("12903")) {
			// 跟岗实习信息
			if (("ggsxjlList").equals(dataSrc)) {
				List<HashMap<String, String>> ggsxjlList = service
				.getGgsxjlList(xh);
				 return ggsxjlList;
			}
			// 顶岗实习信息
			if (("dgsxjlList").equals(dataSrc)) {
				List<HashMap<String, String>> dgsxjlList = service
				.getDgsxjlList(xh);
				return dgsxjlList;
			}
		}
		
		//山西财经
		if("10125".equalsIgnoreCase(Base.xxdm)) {
			//科学研究
			if(("kxyjList").equals(dataSrc)) {
				return service.getKxyjList(xh);
			}
			//课题研究
			if(("ktyjList").equals(dataSrc)) {
				return service.getKtyjList(xh);
			}
			//创新创业项目
			if(("cxcyxmList").equals(dataSrc)) {
				return service.getCxcyxmList(xh);
			}
			//学科竞赛
			if(("xkjsList").equals(dataSrc)) {
				return service.getXkjsList(xh);
			}
			//技能证书
			if(("jnzsList").equals(dataSrc)) {
				return service.getJnzsList(xh);
			}
		}
		
		//咸宁职业
		if("13265".equalsIgnoreCase(Base.xxdm)) {
			//学生缴费
			if(("xsjfList").equals(dataSrc)) {
				return service.getJnzsList(xh);
			}
		}
		
		// 华中师范大学
		if("10511".equalsIgnoreCase(Base.xxdm)) {
			// 个人简历
			if (("grjlList").equals(dataSrc)) {
				List<HashMap<String, String>> grjlList = service
				.getXsGrjlList(xh);
				return grjlList;
			}
			// 工作简历
			if (("gzjlList").equals(dataSrc)) {
				List<HashMap<String, String>> gzjlList = service
				.getXsGzjlList(xh);
				return gzjlList;
			}
			
		}
		
		// 梧州学院
		if("11354".equalsIgnoreCase(Base.xxdm)) {
			// 学生银行信息
			if(("xsyhxxList").equals(dataSrc)) {
				List<HashMap<String, String>> xsyhxxList = service
				.getXsyhxxList(xh);
				return xsyhxxList;
			}
		}
		
		// 北京中医药大学
		if("10026".equalsIgnoreCase(Base.xxdm)) {
			// 个人奖励信息
			if(("grhjxxList").equals(dataSrc)) {
				List<HashMap<String, String>> grhjxxList = service
				.getGrhjxxList(xh);
				return grhjxxList;
			}				
		}
		
		if("10335".equalsIgnoreCase(Base.xxdm)) {
			// 浙大学籍异动
			if(("zdxjydList").equals(dataSrc)) {
				List<HashMap<String, String>> zdxjydList = service
				.getZdxjydList(xh);
				return zdxjydList;
			}		
		}
		return new ArrayList<HashMap<String,String>>();
		
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述: 联系方式，其他信息等模块画table
	 * @作者：yxy[工号：1206]
	 * @日期：2016-8-29 下午04:01:06
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * Table 返回类型 
	 * @throws
	 */
	public Table writeZddyzdTable(String name,String xh,String flszid,HashMap<String, String> zdyMap) throws Exception{
		Font bold_fontChinese = this.getFontChinese();
		Table table = new Table(4);//创建一个4列的表格
        table.setAlignment(Element.ALIGN_CENTER);//居中   
        table.setAlignment(Element.ALIGN_MIDDLE);//垂直居中   
        table.setAutoFillEmptyCells(true);//自动填满
        table.setBorder(0);//设置边框
        table.setPadding(1f);//设置间距
        table.setWidth(101f);//设置表格宽度

        //表名/表注释
        Cell tableName = new Cell(new Paragraph(name,bold_fontChinese));
        tableName.setUseAscender(true);
        tableName.setUseDescender(true);
        tableName.setHorizontalAlignment(Element.ALIGN_LEFT);//水平居中
        tableName.setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中
      //  tableName.setBackgroundColor(Color.decode("#D9D9D9"));//背景颜色
        tableName.setColspan(4);//合并单元格
        table.addCell(tableName);
        //取自定义表单字段信息
        List<HashMap<String, String>> zdyxx = dao.getZdyxxList(flszid);
        int index = 0;
        for (int i = 0; i < zdyxx.size(); i++) {
			String szls = zdyxx.get(i).get("szls");
			if(!"2".equals(szls)){
				index = index + 1;
				  Cell columnName = new Cell(new Paragraph(zdyxx.get(i).get("bdmc"),bold_fontChinese));
	              columnName.setUseAscender(true);
	              columnName.setUseDescender(true);
	              columnName.setHorizontalAlignment(Element.ALIGN_CENTER);//水平居中
	              columnName.setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中
	              //columnName.setBackgroundColor(Color.decode("#D9D9D9"));//背景颜色
	              table.addCell(columnName);
	              Cell columnZdContent = new Cell(new Paragraph(zdyMap.get(zdyxx.get(i).get("zd")) ,bold_fontChinese));
	              columnZdContent.setUseAscender(true);
	              columnZdContent.setUseDescender(true);
	              columnZdContent.setHorizontalAlignment(Element.ALIGN_CENTER);//水平居中
	              columnZdContent.setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中
	              //columnName.setBackgroundColor(Color.decode("#D9D9D9"));//背景颜色
	              table.addCell(columnZdContent);
			}else{
				if(index%2 != 0){
					index = index + 3 ;
					Cell columnName = new Cell(new Paragraph("",bold_fontChinese));
					columnName.setUseAscender(true);
					columnName.setUseDescender(true);
					columnName.setHorizontalAlignment(Element.ALIGN_CENTER);//水平居中
					columnName.setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中
					//columnName.setBackgroundColor(Color.decode("#D9D9D9"));//背景颜色
					table.addCell(columnName);
					Cell columnZdContent = new Cell(new Paragraph("",bold_fontChinese));
					columnZdContent.setUseAscender(true);
					columnZdContent.setUseDescender(true);
					columnZdContent.setHorizontalAlignment(Element.ALIGN_CENTER);//水平居中
					columnZdContent.setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中
					//columnName.setBackgroundColor(Color.decode("#D9D9D9"));//背景颜色
					table.addCell(columnZdContent);
				}else{
					index = index + 2 ;
				}
				  /*if(i-1 >= 0 && (Integer.parseInt(zdyxx.get(i-1).get("rn")))%2 != 0 && !"2".equals(zdyxx.get(i-1).get("szls")) ){
					  Cell columnName = new Cell(new Paragraph("",bold_fontChinese));
		              columnName.setUseAscender(true);
		              columnName.setUseDescender(true);
		              columnName.setHorizontalAlignment(Element.ALIGN_CENTER);//水平居中
		              columnName.setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中
		              //columnName.setBackgroundColor(Color.decode("#D9D9D9"));//背景颜色
		              table.addCell(columnName);
		              Cell columnZdContent = new Cell(new Paragraph("",bold_fontChinese));
		              columnZdContent.setUseAscender(true);
		              columnZdContent.setUseDescender(true);
		              columnZdContent.setHorizontalAlignment(Element.ALIGN_CENTER);//水平居中
		              columnZdContent.setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中
		              //columnName.setBackgroundColor(Color.decode("#D9D9D9"));//背景颜色
		              table.addCell(columnZdContent);
				  }*/
				  Cell columnName = new Cell(new Paragraph(zdyxx.get(i).get("bdmc"),bold_fontChinese));
	              columnName.setUseAscender(true);
	              columnName.setUseDescender(true);
	              columnName.setHorizontalAlignment(Element.ALIGN_CENTER);//水平居中
	              columnName.setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中
	              //columnName.setBackgroundColor(Color.decode("#D9D9D9"));//背景颜色
	              table.addCell(columnName);
	              Cell columnZdContent = new Cell(new Paragraph(zdyMap.get(zdyxx.get(i).get("zd")),bold_fontChinese));
	              columnZdContent.setUseAscender(true);
	              columnZdContent.setUseDescender(true);
	              columnZdContent.setHorizontalAlignment(Element.ALIGN_CENTER);//水平居中
	              columnZdContent.setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中
	              //columnName.setBackgroundColor(Color.decode("#D9D9D9"));//背景颜色
	              columnZdContent.setColspan(3);
	              table.addCell(columnZdContent);
			}
		}
        return table;
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述: 日常事务-日常行为记录
	 * @作者：yxy[工号：1206]
	 * @日期：2016-8-29 下午05:52:23
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param name
	 * @return
	 * Table 返回类型 
	 * @throws
	 */
	public Table writeTableRcxwjl(String xh,String name) throws Exception{
		//日常行为参数配置
		HashMap<String,String> cspzMap = new RcxwjgService().getCspz();
		//1：日常行为NEW 2：日常行为
		List<HashMap<String, String>> rcswList = new ArrayList<HashMap<String, String>>();
		Table table = null;
		if("1".equals(cspzMap.get("mklb"))){
			rcswList=new com.zfsoft.xgxt.rcsw.rcxwwhnew.rcxwjg.RcxwjgService().getrcxwFzxxList(xh);//根据学号查询日常事务
			Font bold_fontChinese = this.getFontChinese();
		
			if("0".equals(cspzMap.get("zq"))){
				table = new Table(5);
			}else{
				table = new Table(4);
			}
	        table.setAlignment(Element.ALIGN_CENTER);//居中   
	        table.setAlignment(Element.ALIGN_MIDDLE);//垂直居中   
	        table.setAutoFillEmptyCells(true);//自动填满
	        table.setBorder(0);//设置边框
	        table.setPadding(1f);//设置间距
	        table.setWidth(101f);//设置表格宽度
	        if("0".equals(cspzMap.get("zq"))){
	        	 //表名/表注释
	            Cell tableName = new Cell(new Paragraph(name,bold_fontChinese));
	            tableName.setUseAscender(true);
	            tableName.setUseDescender(true);
	            tableName.setHorizontalAlignment(Element.ALIGN_LEFT);//水平居中
	            tableName.setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中
	          //  tableName.setBackgroundColor(Color.decode("#D9D9D9"));//背景颜色
	            tableName.setColspan(5);//合并单元格
	            table.addCell(tableName);
	            String[] zdmcArr = new String[]{"行为记录学年","行为记录学期","行为记录类别","行为记录总分","学分"};
	            for (int i = 0; i < zdmcArr.length; i++) {
	            	  Cell columnName = new Cell(new Paragraph(zdmcArr[i],bold_fontChinese));
		              columnName.setUseAscender(true);
		              columnName.setUseDescender(true);
		              columnName.setHorizontalAlignment(Element.ALIGN_CENTER);//水平居中
		              columnName.setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中
		              //columnName.setBackgroundColor(Color.decode("#D9D9D9"));//背景颜色
		              table.addCell(columnName);
				}
	            String[] zdArr = new String[]{"rdnd","xqmc","rcxwlbmc","fz","xf"};
	            for (int i = 0; i < rcswList.size(); i++) {
	            	for (int j = 0; j < zdArr.length; j++) {
	            		  Cell columnName = new Cell(new Paragraph(rcswList.get(i).get(zdArr[j]),bold_fontChinese));
			              columnName.setUseAscender(true);
			              columnName.setUseDescender(true);
			              columnName.setHorizontalAlignment(Element.ALIGN_CENTER);//水平居中
			              columnName.setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中
			              //columnName.setBackgroundColor(Color.decode("#D9D9D9"));//背景颜色
			              table.addCell(columnName);
					}
	            	
				}
	              
			}else{
				 Cell tableName = new Cell(new Paragraph(name,bold_fontChinese));
	             tableName.setUseAscender(true);
	             tableName.setUseDescender(true);
	             tableName.setHorizontalAlignment(Element.ALIGN_LEFT);//水平居中
	             tableName.setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中
	          //  tableName.setBackgroundColor(Color.decode("#D9D9D9"));//背景颜色
	             tableName.setColspan(4);//合并单元格
	              table.addCell(tableName);
	              String[] zdmcArr = new String[]{"行为记录学年","行为记录类别","行为记录总分","学分"};
	              for (int i = 0; i < zdmcArr.length; i++) {
	            	  Cell columnName = new Cell(new Paragraph(zdmcArr[i],bold_fontChinese));
		              columnName.setUseAscender(true);
		              columnName.setUseDescender(true);
		              columnName.setHorizontalAlignment(Element.ALIGN_CENTER);//水平居中
		              columnName.setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中
		              //columnName.setBackgroundColor(Color.decode("#D9D9D9"));//背景颜色
		              table.addCell(columnName);
				}
	              String[] zdArr = new String[]{"rdnd","rcxwlbmc","fz","xf"};
		            for (int i = 0; i < rcswList.size(); i++) {
		            	for (int j = 0; j < zdArr.length; j++) {
		            		  Cell columnName = new Cell(new Paragraph(rcswList.get(i).get(zdArr[j]),bold_fontChinese));
				              columnName.setUseAscender(true);
				              columnName.setUseDescender(true);
				              columnName.setHorizontalAlignment(Element.ALIGN_CENTER);//水平居中
				              columnName.setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中
				              //columnName.setBackgroundColor(Color.decode("#D9D9D9"));//背景颜色
				              table.addCell(columnName);
						}
		            	
					}
			}
			
		}
		else{
			rcswList = new RcxwjgService().getRcswList(xh);// 根据学号查询日常事务
			Font bold_fontChinese = this.getFontChinese();
			
			if("0".equals(cspzMap.get("zq"))){
				table = new Table(6);
			}else{
				table = new Table(5);
			}
	        table.setAlignment(Element.ALIGN_CENTER);//居中   
	        table.setAlignment(Element.ALIGN_MIDDLE);//垂直居中   
	        table.setAutoFillEmptyCells(true);//自动填满
	        table.setBorder(0);//设置边框
	        table.setPadding(1f);//设置间距
	        table.setWidth(101f);//设置表格宽度
	        if("0".equals(cspzMap.get("zq"))){
	        	 //表名/表注释
	            Cell tableName = new Cell(new Paragraph(name,bold_fontChinese));
	            tableName.setUseAscender(true);
	            tableName.setUseDescender(true);
	            tableName.setHorizontalAlignment(Element.ALIGN_LEFT);//水平居中
	            tableName.setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中
	          //  tableName.setBackgroundColor(Color.decode("#D9D9D9"));//背景颜色
	            tableName.setColspan(6);//合并单元格
	            table.addCell(tableName);
	            String[] zdmcArr = new String[]{"行为记录学年","行为记录学期","行为记录大类","行为记录类别","行为记录时间","行为记录分值"};
	            for (int i = 0; i < zdmcArr.length; i++) {
	            	  Cell columnName = new Cell(new Paragraph(zdmcArr[i],bold_fontChinese));
		              columnName.setUseAscender(true);
		              columnName.setUseDescender(true);
		              columnName.setHorizontalAlignment(Element.ALIGN_CENTER);//水平居中
		              columnName.setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中
		              //columnName.setBackgroundColor(Color.decode("#D9D9D9"));//背景颜色
		              table.addCell(columnName);
				}
	            String[] zdArr = new String[]{"xn","xqmc","rcxwlbdlmc","rcxwlbmc","rcxwjlsj","fz"};
	            for (int i = 0; i < rcswList.size(); i++) {
	            	for (int j = 0; j < zdArr.length; j++) {
	            		  Cell columnName = new Cell(new Paragraph(rcswList.get(i).get(zdArr[j]),bold_fontChinese));
			              columnName.setUseAscender(true);
			              columnName.setUseDescender(true);
			              columnName.setHorizontalAlignment(Element.ALIGN_CENTER);//水平居中
			              columnName.setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中
			              //columnName.setBackgroundColor(Color.decode("#D9D9D9"));//背景颜色
			              table.addCell(columnName);
					}
	            	
				}
	              
			}else{
				 Cell tableName = new Cell(new Paragraph(name,bold_fontChinese));
	             tableName.setUseAscender(true);
	             tableName.setUseDescender(true);
	             tableName.setHorizontalAlignment(Element.ALIGN_LEFT);//水平居中
	             tableName.setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中
	          //  tableName.setBackgroundColor(Color.decode("#D9D9D9"));//背景颜色
	             tableName.setColspan(5);//合并单元格
	              table.addCell(tableName);
	              String[] zdmcArr = new String[]{"行为记录学年","行为记录大类","行为记录类别","行为记录时间","行为记录分值"};;
	              for (int i = 0; i < zdmcArr.length; i++) {
	            	  Cell columnName = new Cell(new Paragraph(zdmcArr[i],bold_fontChinese));
		              columnName.setUseAscender(true);
		              columnName.setUseDescender(true);
		              columnName.setHorizontalAlignment(Element.ALIGN_CENTER);//水平居中
		              columnName.setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中
		              //columnName.setBackgroundColor(Color.decode("#D9D9D9"));//背景颜色
		              table.addCell(columnName);
				}
	              String[] zdArr = new String[]{"xn","rcxwlbdlmc","rcxwlbmc","rcxwjlsj","fz"};
		            for (int i = 0; i < rcswList.size(); i++) {
		            	for (int j = 0; j < zdArr.length; j++) {
		            		  Cell columnName = new Cell(new Paragraph(rcswList.get(i).get(zdArr[j]),bold_fontChinese));
				              columnName.setUseAscender(true);
				              columnName.setUseDescender(true);
				              columnName.setHorizontalAlignment(Element.ALIGN_CENTER);//水平居中
				              columnName.setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中
				              //columnName.setBackgroundColor(Color.decode("#D9D9D9"));//背景颜色
				              table.addCell(columnName);
						}
		            	
					}
			}
		}
		return table;
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述: 学生信息-基本信息画table
	 * @作者：yxy[工号：1206]
	 * @日期：2016-8-30 上午11:10:23
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param name
	 * @param xh
	 * @param lsmap
	 * @return
	 * Table 返回类型 
	 * @throws
	 */
	public Table writeJbxx(String name,String xh,HashMap<String, String> lsmap,HashMap<String, String> xsxx,String defaultimagepath) throws Exception{
		List<HashMap<String, String>> zdyzdList = dao.getZdyxxList(lsmap.get("flszid"));
		ZdybdBaseDao zdydao = new ZdybdBaseDao();
		Font bold_fontChinese = this.getFontChinese();
		Table table = new Table(5);//创建一个5列的表格
        table.setAlignment(Element.ALIGN_CENTER);//居中   
        table.setAlignment(Element.ALIGN_MIDDLE);//垂直居中   
        table.setAutoFillEmptyCells(true);//自动填满
        table.setBorder(0);//设置边框
        table.setPadding(1f);//设置间距
        table.setWidth(100f);//设置表格宽度
        //设置列宽
        table.setWidths(new int[]{15,35,15,35,20});
        List<HashMap<String, String>> shList = zdydao.getShList();
		List<HashMap<String, String>> qxList = zdydao.getQxList();

        //表名/表注释
        Cell tableName = new Cell(new Paragraph(name,bold_fontChinese));
        tableName.setUseAscender(true);
        tableName.setUseDescender(true);
        tableName.setHorizontalAlignment(Element.ALIGN_LEFT);//水平居中
        tableName.setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中
      //  tableName.setBackgroundColor(Color.decode("#D9D9D9"));//背景颜色
        tableName.setColspan(5);//合并单元格
        table.addCell(tableName);
        //如果自定义表单基本信息配置字段为空或个数小于10，直接返回空
        if(null == zdyzdList || zdyzdList.size() < 10){
        	return null;
        }
        for (int i = 0; i < 10; i++) {
        	Cell columnName = new Cell(new Paragraph(zdyzdList.get(i).get("bdmc"),bold_fontChinese));
            columnName.setUseAscender(true);
            columnName.setUseDescender(true);
            columnName.setHorizontalAlignment(Element.ALIGN_CENTER);//水平居中
            columnName.setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中
            //columnName.setBackgroundColor(Color.decode("#D9D9D9"));//背景颜色
            table.addCell(columnName);
            Cell columnZdContent = new Cell(new Paragraph(xsxx.get(zdyzdList.get(i).get("zd")) ,bold_fontChinese));
            columnZdContent.setUseAscender(true);
            columnZdContent.setUseDescender(true);
            columnZdContent.setHorizontalAlignment(Element.ALIGN_CENTER);//水平居中
            columnZdContent.setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中
            //columnName.setBackgroundColor(Color.decode("#D9D9D9"));//背景颜色
            table.addCell(columnZdContent);
            Cell cell = null;
			if( (i+1)  == 2){
				 
				 cell = new Cell(new Paragraph("",bold_fontChinese));
				cell.setUseAscender(true);
				cell.setUseDescender(true);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);//水平居中
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中
	            //columnName.setBackgroundColor(Color.decode("#D9D9D9"));//背景颜色
				cell.setRowspan(5);
			    byte[] byteArr = this.showPhoto(xh, defaultimagepath); 
			    //用字节流获取图片API可插入对象
			    Image image = Image.getInstance(byteArr);
//			    image.setWidthPercentage(cell.width());
			    image.scaleAbsoluteWidth(85f);
			    image.scaleAbsoluteHeight(85f);
			    //将对象放入表格指定位置
			    cell.add(image);
				table.addCell(cell);
				
			}
		}
        for (int i = 10; i < zdyzdList.size(); i++) {
        	String szls = zdyzdList.get(i).get("szls");
			if(!"2".equals(szls)){
				  Cell columnName = new Cell(new Paragraph(zdyzdList.get(i).get("bdmc"),bold_fontChinese));
	              columnName.setUseAscender(true);
	              columnName.setUseDescender(true);
	              columnName.setHorizontalAlignment(Element.ALIGN_CENTER);//水平居中
	              columnName.setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中
	              //columnName.setBackgroundColor(Color.decode("#D9D9D9"));//背景颜色
	              table.addCell(columnName);
	              Cell columnZdContent = new Cell(new Paragraph(xsxx.get(zdyzdList.get(i).get("zd")) ,bold_fontChinese));
	              if("22".equals(zdyzdList.get(i).get("zdlx"))){
	            	  String zdvlaue = xsxx.get(zdyzdList.get(i).get("zd"));
	            	  String ssxmc = "";
                      if(StringUtils.isNull(zdvlaue)){
                    	  ssxmc = "";
                    	  columnZdContent = new Cell(new Paragraph(ssxmc,bold_fontChinese));
	            	  }else{
	            		  ssxmc = this.getSsxMc(shList, qxList,zdvlaue);
	            		  columnZdContent = new Cell(new Paragraph(ssxmc,bold_fontChinese));
	            	  }
	            	   
	              }
	              columnZdContent.setUseAscender(true);
	              columnZdContent.setUseDescender(true);
	              columnZdContent.setHorizontalAlignment(Element.ALIGN_CENTER);//水平居中
	              columnZdContent.setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中
	              //columnName.setBackgroundColor(Color.decode("#D9D9D9"));//背景颜色
	              if((i+1)%2 == 0){
	            	  columnZdContent.setColspan(2);
	              }
	              table.addCell(columnZdContent);
			}else{
				  if(i-1 >= 0 && Integer.parseInt(zdyzdList.get(i-1).get("rn"))%2 != 0  &&  !"2".equals(zdyzdList.get(i-1).get("szls"))){
					  Cell columnName = new Cell(new Paragraph("",bold_fontChinese));
		              columnName.setUseAscender(true);
		              columnName.setUseDescender(true);
		              columnName.setHorizontalAlignment(Element.ALIGN_CENTER);//水平居中
		              columnName.setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中
		              //columnName.setBackgroundColor(Color.decode("#D9D9D9"));//背景颜色
		              table.addCell(columnName);
		              Cell columnZdContent = new Cell(new Paragraph("",bold_fontChinese));
		              columnZdContent.setUseAscender(true);
		              columnZdContent.setUseDescender(true);
		              columnZdContent.setHorizontalAlignment(Element.ALIGN_CENTER);//水平居中
		              columnZdContent.setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中
		              //columnName.setBackgroundColor(Color.decode("#D9D9D9"));//背景颜色
		              columnZdContent.setColspan(2);
		              table.addCell(columnZdContent);
				  }
				  Cell columnName = new Cell(new Paragraph(zdyzdList.get(i).get("bdmc"),bold_fontChinese));
	              columnName.setUseAscender(true);
	              columnName.setUseDescender(true);
	              columnName.setHorizontalAlignment(Element.ALIGN_CENTER);//水平居中
	              columnName.setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中
	              //columnName.setBackgroundColor(Color.decode("#D9D9D9"));//背景颜色
	              table.addCell(columnName);
	              Cell columnZdContent = new Cell(new Paragraph(xsxx.get(zdyzdList.get(i).get("zd")),bold_fontChinese));
	              if("22".equals(zdyzdList.get(i).get("zdlx"))){
	            	  String zdvlaue = xsxx.get(zdyzdList.get(i).get("zd"));
	            	  String ssxmc = "";
                      if(StringUtils.isNull(zdvlaue)){
                    	  ssxmc = "";
                    	  columnZdContent = new Cell(new Paragraph(ssxmc,bold_fontChinese));
	            	  }else{
	            		  ssxmc = this.getSsxMc(shList, qxList,zdvlaue);
	            		  columnZdContent = new Cell(new Paragraph(ssxmc,bold_fontChinese));
	            	  }
	            	   
	              }
	              columnZdContent.setUseAscender(true);
	              columnZdContent.setUseDescender(true);
	              columnZdContent.setHorizontalAlignment(Element.ALIGN_CENTER);//水平居中
	              columnZdContent.setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中
	              //columnName.setBackgroundColor(Color.decode("#D9D9D9"));//背景颜色
	              columnZdContent.setColspan(4);
	              table.addCell(columnZdContent);
			}
		}
		return table;
	}
	
 
    /**
     * 	
     * @描述:用于照片显示
     * @作者：yxy[工号：1206]
     * @日期：2016-8-31 上午11:25:53
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param xh
     * @param defaultpath
     * @return
     * @throws Exception
     * byte[] 返回类型 
     * @throws
     */
	public byte[] showPhoto(String xh,String defaultpath)
			throws Exception {

		XsxxService service = new XsxxService();

		if (null == xh || "".equals(xh)) {
			InputStream is = new FileInputStream(defaultpath);
			byte[] photoByteArr = FileUtil.inputTobyte(is);
			return photoByteArr;
		} else {
			if("10335".equals(Base.xxdm)){
				String zjdxZpurl = "http://api.zju.edu.cn:8094/message/openapi/api.do?apiKey=91bd53f871464366f92c6964f98d5aa9&appName=getGrzp&returnType=xml&xgh=" + xh;
				
				URL localURL = new URL(zjdxZpurl);
		        URLConnection connection = localURL.openConnection();
		        HttpURLConnection httpURLConnection = (HttpURLConnection)connection;
		        httpURLConnection.setRequestProperty("Accept-Charset", "utf-8");
		        httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded"); 
		        InputStream inputStream = null;
		        InputStreamReader inputStreamReader = null;
		        BufferedReader reader = null;
		        StringBuffer resultBuffer = new StringBuffer();
		        String tempLine = null;
		        try {
					if (httpURLConnection.getResponseCode() >= 300) {
					    throw new Exception("HTTP Request is not success, Response code is " + httpURLConnection.getResponseCode());
					}
				} catch (Exception e) {
					// TODO 自动生成 catch 块
					e.printStackTrace();
					InputStream is = new FileInputStream(defaultpath);
					byte[] photoByteArr = FileUtil.inputTobyte(is);
					return photoByteArr;
				}
		        try {
		            inputStream = httpURLConnection.getInputStream();
		            inputStreamReader = new InputStreamReader(inputStream);
		            reader = new BufferedReader(inputStreamReader);
		            while ((tempLine = reader.readLine()) != null) {
		                resultBuffer.append(tempLine);
		            }
		        } finally {
		            if (reader != null) {
		                reader.close();
		            }
		            if (inputStreamReader != null) {
		                inputStreamReader.close();
		            }
		            if (inputStream != null) {
		                inputStream.close();
		            }
		        }	
				
		        String[] zps = org.apache.commons.lang.StringUtils.substringsBetween(resultBuffer.toString(), "<ZP>", "</ZP>");
		        
		        byte[] bs = new BASE64Decoder().decodeBuffer(zps[0]);
		        return bs;
			}else{
				InputStream is = service.getPhotoStream(xh);

				if (is == null) {
						is = new FileInputStream(new File(defaultpath));
				}
				byte[] photoByteArr = FileUtil.inputTobyte(is);
				return photoByteArr;
				
			}
		}
	}
	
	/**
	 * 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：yxy[工号：1206]
	 * @日期：2016-9-28 下午03:25:27
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getSsxMc(List<HashMap<String, String>> shlist,List<HashMap<String, String>> qxlist,String zdvalue){
		String result = "";
		if("0000".equals(zdvalue.substring(2, 6))){
			for (int i = 0; i < shlist.size(); i++) {
				if(shlist.get(i).get("dm").equals(zdvalue)){
					result = shlist.get(i).get("mc");
				    break;
				}
			}
		}else if("00".equals(zdvalue.substring(4, 6))){
			String shen = "";
			String shi = "";
			for (int i = 0; i < qxlist.size(); i++) {
				if(qxlist.get(i).get("dm").equals(zdvalue.substring(0, 2)+"0000")){
					shen = qxlist.get(i).get("mc");
				    
				}
				if(qxlist.get(i).get("dm").equals(zdvalue.substring(0, 4)+"00")){
					shi = qxlist.get(i).get("mc");
				    
				}
				if(StringUtils.isNotNull(shen)&&StringUtils.isNotNull(shi)){
					result = shen + shi;
					break;
				}
			}
		}else{
			String shen = "";
			String shi = "";
			String xian = "";
			for (int i = 0; i < qxlist.size(); i++) {
				if(qxlist.get(i).get("dm").equals(zdvalue.substring(0, 2)+"0000")){
					shen = qxlist.get(i).get("mc");
				    
				}
				if(qxlist.get(i).get("dm").equals(zdvalue.substring(0, 4)+"00")){
					shi = qxlist.get(i).get("mc");
				    
				}
				if(qxlist.get(i).get("dm").equals(zdvalue)){
					xian = qxlist.get(i).get("mc");
				    
				}
				if(StringUtils.isNotNull(shen)&&StringUtils.isNotNull(shi)&&StringUtils.isNotNull(xian)){
					result = shen + shi + xian;
					break;
				}
			}
		}
		return result;
	}

}
