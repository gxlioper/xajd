/**
 * @部门:学工产品事业部
 * @日期：2016-6-22 下午05:06:46 
 */  
package com.zfsoft.xgxt.zjly.zhf.sh;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.commons.io.FileUtils;
import org.apache.struts.upload.FormFile;

import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.lowagie.text.Cell;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.rtf.RtfWriter2;
import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.ZipUtil;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.xsxx.xsxxgl.bddc.ExportService;
import com.zfsoft.xgxt.zjly.zhf.sq.ZhfForm;
import com.zfsoft.xgxt.zjly.zhf.sq.ZhfService;
import com.zfsoft.xgxt.zjly.zhf.zhfhz.ZhfhzDao;
import com.zfsoft.xgxt.zjly.zhf.zhfhz.ZhfhzService;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: 综合分审核(这里用一句话描述这个类的作用) 
 * @作者： 柳俊[工号:1282]
 * @时间： 2016-6-22 下午05:06:46 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ZhfshService extends SuperServiceImpl<ZhfForm, ZhfshDao>{
	private ZhfshDao dao = new ZhfshDao();
	private ResourceBundle resource = ResourceBundle.getBundle("config/fileUploadConfig");
	private static final String PRIFEX_ZF = "ZFXG_UPLOADFILES";
	private final String[] fileTypes = new String[]{"png","gif","jpg","jpeg",
			"zip","rar","pdf","txt","doc","docx","xls","xlsx"};
	
	private ZhfService zhfService = new ZhfService();
	ExportService exportService = new ExportService();
	/** 
	 * @描述:获取模块项目列表 (这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-6-23 上午10:06:42
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getXmmkList(){
		return dao.getXmmkList();
	}
	
	/** 
	 * @描述:获取已审定列表(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-6-23 上午10:07:18
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String,String>> getJfxmYsdByXh(String xh){
		return dao.getJfxmYsdByXh(xh);
	}
	
	/** 
	 * @描述:获取未审定列表(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-6-23 上午10:07:54
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String,String>> getJfxmWsdByXh(String xh){
		return dao.getJfxmWsdByXh(xh);
	}
	
	/** 
	 * @描述:批量保存(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-6-23 下午02:11:51
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param form
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean savePlSd(ZhfForm form) throws Exception{		
		return dao.saveSdByXh(form.getXhs(),form.getShr(),form.getShsj());		
	}
	
	/** 
	 * @描述:保存审定(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-6-23 下午02:19:16
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param list
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean saveSd(ZhfForm model)throws Exception{
		List<ZhfForm> list = new ArrayList<ZhfForm>();
		//附件
		Hashtable files = model.getMultipartRequestHandler().getFileElements();
		String[] jfxms = model.getJfxmdms();
		String[] xmdms = model.getXmmkdms();
		String[] sxsms = model.getSxsms();
		String[] cysjs = model.getCysjs();
		model.setShzt("1");
		for(int i = 0;i<jfxms.length;i++){
			ZhfForm form = new ZhfForm();
			form.setXh(model.getXh());
			form.setXmmkdm(xmdms[i]);
			form.setJfxmdm(jfxms[i]);
			form.setSxsm(sxsms[i]);
			form.setCysj(cysjs[i]);
			form.setShr(model.getShr());
			form.setShsj(model.getShsj());
			form.setShzt(model.getShzt());
			FormFile file = (FormFile) files.get("fj"+i);
			if (file != null && !StringUtil.isNull(file.getFileName())){
				String basePath = resource.getString("filesys.local.dir");
				//如果没有给定文件存储路径，就默认给系统用户目录
				if(StringUtils.isNull(basePath)){
					basePath = System.getProperty("user.home") +"/" + PRIFEX_ZF;
				}
				String prex = file.getFileName().substring(file.getFileName().lastIndexOf("."));
				
				if (StringUtils.getStrIndexInArray(prex.replace(".", ""), fileTypes) > -1 && file.getFileSize() <= 1024*1024*5){
					File srcFile = FileUtil.conversionFormFile(file);
					File destFile = new File(basePath+"/"+System.currentTimeMillis()+prex);
					FileUtils.copyFile(srcFile, destFile);
					form.setFj(destFile.getAbsolutePath());
					form.setFjmc(file.getFileName());
				}
			}
			else{
				form.setFj("");
				form.setFjmc("");
			}
			list.add(form);
		}
		if(zhfService.isExist(list)){
			return false;
		}else{
			dao.saveSdByXh(new String[]{model.getXh()}, model.getShr(), model.getShsj());
			return dao.saveSd(list);			
		}		
	} 
	
	/** 
	 * @描述:根据id保存审核(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-6-23 下午03:31:52
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ids
	 * @param shr
	 * @param shsj
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean saveSdById(String[] ids,String shr,String shsj) throws Exception{
		return dao.saveSdById(ids, shr, shsj);
	}
	
	
	/** 
	 * @描述:得到(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-6-24 上午10:59:00
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zcfsForm
	 * @param user
	 * @return
	 * @throws Exception
	 * File 返回类型 
	 * @throws 
	 */
	public File getZhfFile(ZhfForm form, User user) throws Exception{	
	 List<HashMap<String, String>> xmmkList = getXmmkList();
		//构建导出表头
		Map<String,String> map = new LinkedHashMap<String, String>();
		map.put("xh", "学号");
		map.put("xm", "姓名");
		map.put("nj", "年级");
		map.put("xymc", "系部");
		map.put("zymc", "专业");
		map.put("bjmc", "班级");
		
		for (int i = 0 , j = xmmkList.size() ; i < j ; i++){
			map.put("fs"+i, xmmkList.get(i).get("xmmkmc"));
		}
		map.put("zf","总分");
		map.put("shztmc", "审核状态");
		//导出数据
		form.getPages().setPageSize(Integer.MAX_VALUE);
		
		List<HashMap<String,String>> dataList = dao.getAllList(form, user);
		
		IExportService export = new ExportExcelImpl();
		return export.getExportFile(map, dataList);
	}

	/**
	 * @param thyj 
	 * @throws Exception  
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：CP[工号：1352]
	 * @日期：2017-1-10 下午01:48:33
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param idsss
	 * @param shr
	 * @param shsj
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean saveBack(String[] idsss, String shr, String shsj, String thyj) throws Exception {
		return dao.saveBack(idsss, shr, shsj,thyj);
	}

	/** 
	 * @作者：CP[工号：1352]
	 * @日期：2017-1-10 下午04:59:52
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getJfxmThByXh(String xh) {
		return dao.getJfxmThByXh(xh);
	}

	/**
	 * @throws Exception  
	 * @描述:编写单个word
	 * @作者：CP[工号：1352]
	 * @日期：2017-2-21 下午06:01:03
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * File 返回类型 
	 * @throws 
	 */
	public File createWord(ZhfForm model) throws Exception {
		String xh = model.getXh();
		String filepath = model.getFilepath();
		File filefolder = new File(filepath);
		if(!filefolder.exists()){
			filefolder.mkdir();
		}
		String path = model.getFilepath()+xh+"_学生综合分汇总表.doc";
		File file = new File(path);
		file.createNewFile();
		Document document = new Document(PageSize.A4);  
		RtfWriter2.getInstance(document,new FileOutputStream(file));
	     BaseFont bfChinese = BaseFont.createFont("STSongStd-Light","UniGB-UCS2-H",BaseFont.NOT_EMBEDDED);  
		   Font yourFont = new Font(bfChinese,12,Font.BOLD);  
		HeaderFooter footer = new HeaderFooter(new Phrase("页码：",yourFont), true);  
		footer.setBorder(Rectangle.NO_BORDER);  
		footer.setAlignment(Element.ALIGN_CENTER);//居中   
		document.setFooter(footer);  
		document.open();
		 //设置中文字体  
        //标题字体风格  
        Font titleFont = new Font(bfChinese,14,Font.BOLD);  
        //正文字体风格  
        Paragraph title = new Paragraph("浙江旅游职业学院学生综合素质学分汇总表",titleFont); 
    	title.setAlignment(Element.ALIGN_CENTER);
		title.setIndentationLeft(5);
		title.setIndentationRight(5);
		document.add(title);
	    //基本信息table
	    	Table table = this.writeJbxxTable(xh,model);
	    	if(null != table){
	    		document.add(table);
	    	}
	    //综合分信息table
	        ZhfhzDao zhfhzDao = new ZhfhzDao();
	    	 List<HashMap<String, String>> mkzflist = zhfhzDao.getmkzf(xh);
	          for (int i = 0; i < mkzflist.size(); i++) {
	        	    	Table mktable = this.writeSxszTable(xh,model,mkzflist.get(i).get("xmmkmc"),mkzflist.get(i).get("mkzf"),mkzflist.get(i).get("sfhg"));
	        	    	if(null != mktable){
	        	    		document.add(mktable);
	        	    	}
	          }
		document.close();
		return file;
	}

	/**
	 * @throws Exception  
	 * @描述:分模块画word
	 * @作者：CP[工号：1352]
	 * @日期：2017-3-9 上午10:43:07
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param model
	 * @return
	 * Table 返回类型 
	 * @throws 
	 */
	private Table writeSxszTable(String xh, ZhfForm model, String mkmc, String mkzf, String sfhg) throws Exception {
	     ZhfhzDao zhfhzDao = new ZhfhzDao();	
		Font bold_fontChinese = exportService.getFontChinese();
		Table table = new Table(6);//创建一个6列的表格
        table.setAlignment(Element.ALIGN_CENTER);//居中   
        table.setAlignment(Element.ALIGN_MIDDLE);//垂直居中   
        table.setAutoFillEmptyCells(true);//自动填满
        table.setBorder(0);//设置边框
        table.setPadding(1f);//设置间距
        table.setWidth(101f);//设置表格宽度
        table.setWidths(new int[]{20,6,15,20,8,12});
        //表名/表注释
        Cell tableName =new Cell();
        if ("个人荣誉".equals(mkmc)) {
        	 tableName = new Cell(new Paragraph(mkmc+"   "+mkzf+"分   ",bold_fontChinese));
		}else {
			 tableName = new Cell(new Paragraph(mkmc+"   "+mkzf+"分   "+sfhg,bold_fontChinese));
		}
        tableName.setUseAscender(true);
        tableName.setUseDescender(true);
        tableName.setHorizontalAlignment(Element.ALIGN_CENTER);//水平居中
        tableName.setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中
        tableName.setColspan(6);//合并单元格
        table.addCell(tableName);
        //表头
    	String[] zdmcArr ={"计分项目","得分","类别","事项名称","事项分","参与/获得时间"};
        for (int i = 0; i < zdmcArr.length; i++) {
        	  Cell columnName = new Cell(new Paragraph(zdmcArr[i],bold_fontChinese));
              columnName.setUseAscender(true);
              columnName.setUseDescender(true);
              columnName.setHorizontalAlignment(Element.ALIGN_CENTER);//水平居中
              columnName.setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中
              table.addCell(columnName);
		}
        //取下表体数据
        List<HashMap<String, String>> Xmlist = zhfhzDao.getsingleMkXmlist(xh,mkmc);
        String[] zdArr1 ={"lb","sxsm","sxf","cysj"};
        String[] zdArr2 = {"jfxmmc","xmzf"};//前两列准备合并
        String jfxmmc ="";//标记计分项目
        int sum=0;
         for (int i = 0; i < Xmlist.size(); i++) {
        	 String fsstr = Xmlist.get(i).get("fs");
				String[] ts =fsstr.split(";");
        	if (!Xmlist.get(i).get("jfxmmc").equals(jfxmmc)) {
 				for (int j = 0; j < zdArr2.length; j++) {
 					Cell cell = null;
 					cell = new Cell(new Paragraph(Xmlist.get(i).get(zdArr2[j]),bold_fontChinese));
 					cell.setUseAscender(true);
 					cell.setUseDescender(true);
 					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
 					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
 					if("jfxmmc".equals(zdArr2[j])){
 						if (ts.length>1) {
 							sum=ts.length;
						}else if (ts.length==1) {
							sum=1;
						}
 						jfxmmc=Xmlist.get(i).get(zdArr2[j]);
 					}
 					cell.setRowspan(sum);
 					table.addCell(cell);
 				}
    		}
			for (int j = 0; j < zdArr1.length; j++) {
  				  Cell columnName = new Cell(new Paragraph(Xmlist.get(i).get(zdArr1[j]),bold_fontChinese));
  	              columnName.setUseAscender(true);
  	              columnName.setUseDescender(true);
  	              columnName.setHorizontalAlignment(Element.ALIGN_CENTER);//水平居中
  	              columnName.setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中
  	              table.addCell(columnName);
        		}
         }
        return table;
	}

	/**
	 * @throws Exception  
	 * @描述:创建学生综合分汇总table  (20170309废弃掉，改为分模块显示)
	 * @作者：CP[工号：1352]
	 * @日期：2017-2-22 下午02:20:23
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param model
	 * @return
	 * Table 返回类型 
	 * @throws 
	 */
	@SuppressWarnings({ "unchecked", "unused" })
	private Table writeZhfxxTable(String xh, ZhfForm model) throws Exception {
		Font bold_fontChinese = exportService.getFontChinese();
		Table table = new Table(8);//创建一个5列的表格
        table.setAlignment(Element.ALIGN_CENTER);//居中   
        table.setAlignment(Element.ALIGN_MIDDLE);//垂直居中   
        table.setAutoFillEmptyCells(true);//自动填满
        table.setBorder(0);//设置边框
        table.setPadding(1f);//设置间距
        table.setWidth(101f);//设置表格宽度
       
        //表名/表注释
        Cell tableName = new Cell(new Paragraph("综合分信息",bold_fontChinese));
        tableName.setUseAscender(true);
        tableName.setUseDescender(true);
        tableName.setHorizontalAlignment(Element.ALIGN_LEFT);//水平居中
        tableName.setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中
        tableName.setColspan(8);//合并单元格
        table.addCell(tableName);
        //表头
    	String[] zdmcArr ={"模块","得分","状态","计分项目","得分","类别","事项名称","参与/获得时间"};
        for (int i = 0; i < zdmcArr.length; i++) {
        	  Cell columnName = new Cell(new Paragraph(zdmcArr[i],bold_fontChinese));
              columnName.setUseAscender(true);
              columnName.setUseDescender(true);
              columnName.setHorizontalAlignment(Element.ALIGN_CENTER);//水平居中
              columnName.setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中
              table.addCell(columnName);
		}
        //取下表体数据
        ZhfhzDao zhfhzDao = new ZhfhzDao();	
        List<HashMap<String, String>> mkxmlist = zhfhzDao.getMkxmListforword(xh);
        String[] zdArr1 ={"jfxmmc","jfxmzf","lb","sxsm","cysj"};
        String[] zdArr2 ={"xmmkmc","mkzf","hgf"};
        HashMap<String, String> map = new HashMap<String, String> ();
       int sx=0;
       int rw=0;
       int sx2=0;
       int zy=0;
       int gr=0;
        for (int i = 0; i < mkxmlist.size(); i++) {
        		if (mkxmlist.get(i).get("xmmkmc").equals("思想素质")) {
        			sx++;
        		}else if (mkxmlist.get(i).get("xmmkmc").equals("人文素质")) {
        			rw++;
				}else if (mkxmlist.get(i).get("xmmkmc").equals("身心素质")) {
        			sx2++;
				}else if (mkxmlist.get(i).get("xmmkmc").equals("职业素质")) {
        			zy++;
				}else if (mkxmlist.get(i).get("xmmkmc").equals("个人荣誉")) {
					gr++;
				}
        }
        	map.put("思想素质", String.valueOf(sx));
			map.put("人文素质", String.valueOf(rw));
			map.put("身心素质", String.valueOf(sx2));
			map.put("职业素质", String.valueOf(zy));
			map.put("个人荣誉", String.valueOf(gr));
        int sum =0;
        String xmmkmc ="";
        for (int i = 0; i < mkxmlist.size(); i++) {
        	if (mkxmlist.get(i).get("xmmkmc").equals(xmmkmc)) {
			}else {
				for (int j = 0; j < zdArr2.length; j++) {
					Cell cell = null;
					cell = new Cell(new Paragraph(mkxmlist.get(i).get(zdArr2[j]),bold_fontChinese));
					cell.setUseAscender(true);
					cell.setUseDescender(true);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);//水平居中
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中
					if("xmmkmc".equals(zdArr2[j])){
						Iterator it = map.keySet().iterator();  
						while (it.hasNext()) {  
							String key = (String) it.next();//当前key值
							if(key.equals(mkxmlist.get(i).get(zdArr2[j]))){//获取value 与 所知道的value比较
								sum = Integer.valueOf(map.get(key));  
								xmmkmc =mkxmlist.get(i).get(zdArr2[j]);//标记模块
							}
						}  
					}
					cell.setRowspan(sum);
					table.addCell(cell);
				}
			}
					for (int j = 0; j < zdArr1.length; j++) {
		  				  Cell columnName = new Cell(new Paragraph(mkxmlist.get(i).get(zdArr1[j]),bold_fontChinese));
		  	              columnName.setUseAscender(true);
		  	              columnName.setUseDescender(true);
		  	              columnName.setHorizontalAlignment(Element.ALIGN_CENTER);//水平居中
		  	              columnName.setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中
		  	              table.addCell(columnName);
		        		}
				}
        return table;
	}

	/**
	 * @throws Exception  
	 * @描述:学生基本信息
	 * @作者：CP[工号：1352]
	 * @日期：2017-2-21 下午06:06:42
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param model
	 * @return
	 * Table 返回类型 
	 * @throws 
	 */
	@SuppressWarnings("unchecked")
	private Table writeJbxxTable(String xh, ZhfForm model) throws Exception {
		ZhfhzService zhfhzservice = new ZhfhzService();	
		HashMap<String,String> xsjbxx = zhfhzservice.getXsxx(xh);
		 List listKey = new ArrayList();  
	       List listValue = new ArrayList();  
		 Iterator it = xsjbxx.keySet().iterator();  
	       while (it.hasNext()) {  
	           String key = it.next().toString();
	           if("xh".equals(key)){
	        	   listKey.add("学号");  
	           }else if ("xm".equals(key)) {
	        	   listKey.add("姓名"); 
	           }else if ("xymc".equals(key)) {
	        	   listKey.add("系部"); 
	           }else if ("bjmc".equals(key)) {
	        	   listKey.add("班级"); 
	           }else if ("sjhm".equals(key)) {
	        	   listKey.add("联系方式"); 
	           }else if ("xb".equals(key)) {
	        	   listKey.add("性别"); 
	           }else if ("zymc".equals(key)) {
	        	   listKey.add("专业名称"); 
	           }else{
	        	   listKey.add("学制"); 
	           }
	           listValue.add(xsjbxx.get(key));  
	       }  
		Font bold_fontChinese = exportService.getFontChinese();
		Table table = new Table(5);//创建一个5列的表格
        table.setAlignment(Element.ALIGN_CENTER);//居中   
        table.setAlignment(Element.ALIGN_MIDDLE);//垂直居中   
        table.setAutoFillEmptyCells(true);//自动填满
        table.setBorder(0);//设置边框
        table.setPadding(1f);//设置间距
        table.setWidth(101f);//设置表格宽度
        //设置列宽
        table.setWidths(new int[]{15,20,15,22,12});
        //表名/表注释
        Cell tableName = new Cell(new Paragraph("学生基本信息",bold_fontChinese));
        tableName.setUseAscender(true);
        tableName.setUseDescender(true);
        tableName.setHorizontalAlignment(Element.ALIGN_LEFT);//水平居中
        tableName.setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中
        tableName.setColspan(5);//合并单元格
        table.addCell(tableName);
        //如果自定义表单基本信息配置字段为空或个数小于10，直接返回空
        for (int i = 0; i < listKey.size(); i++) {
        	Cell columnName = new Cell(new Paragraph((String) listKey.get(i),bold_fontChinese));
            columnName.setUseAscender(true);
            columnName.setUseDescender(true);
            columnName.setHorizontalAlignment(Element.ALIGN_CENTER);//水平居中
            columnName.setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中
            table.addCell(columnName);
            Cell columnZdContent = new Cell(new Paragraph((String) listValue.get(i) ,bold_fontChinese));
            columnZdContent.setUseAscender(true);
            columnZdContent.setUseDescender(true);
            columnZdContent.setHorizontalAlignment(Element.ALIGN_CENTER);//水平居中
            columnZdContent.setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中
            table.addCell(columnZdContent);
            Cell cell = null;
			if((i+1)== 2){
				cell = new Cell(new Paragraph("",bold_fontChinese));
				cell.setUseAscender(true);
				cell.setUseDescender(true);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);//水平居中
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中
				cell.setRowspan(4);
			    byte[] byteArr = exportService.showPhoto(xh, model.getDefaultimagepath()); 
			    //用字节流获取图片API可插入对象
			    Image image = Image.getInstance(byteArr);
			    image.scaleAbsoluteWidth(70f);
			    image.scaleAbsoluteHeight(85f);
			    //将对象放入表格指定位置
			    cell.add(image);
				table.addCell(cell);
			}
		}
        ZhfhzService service = new ZhfhzService();	
		HashMap<String,String> zfMap = service.getZfs(xh);
		String zfs = zfMap.get("zfs");
		if(zfs != null&& zfs !="") {
			if(".".equalsIgnoreCase(zfs.substring(0, 1))) {
				zfs = "0"+zfs;
			}
		}
		if(zfs == null || zfs =="" || "0".equalsIgnoreCase(zfs)){		
			zfs = "";
		}	
        Cell columnName = new Cell(new Paragraph("总分",bold_fontChinese));
        columnName.setUseAscender(true);
        columnName.setUseDescender(true);
        columnName.setHorizontalAlignment(Element.ALIGN_CENTER);//水平居中
        columnName.setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中
        columnName.setColspan(1);
        columnName.setRowspan(5);
        Cell columnName1 = new Cell(new Paragraph(zfs,bold_fontChinese));
        columnName1.setUseAscender(true);
        columnName1.setUseDescender(true);
        columnName1.setHorizontalAlignment(Element.ALIGN_CENTER);//水平居中
        columnName1.setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中
        columnName1.setColspan(1);
        columnName1.setRowspan(5);
        Cell columnName2 = new Cell(new Paragraph("学工办意见",bold_fontChinese));
        columnName2.setUseAscender(true);
        columnName2.setUseDescender(true);
        columnName2.setHorizontalAlignment(Element.ALIGN_CENTER);//水平居中
        columnName2.setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中
        columnName2.setColspan(1);
        columnName2.setRowspan(5);
        Cell columnName3 = new Cell(new Paragraph("",bold_fontChinese));
        columnName3.setUseAscender(true);
        columnName3.setUseDescender(true);
        columnName3.setHorizontalAlignment(Element.ALIGN_CENTER);//水平居中
        columnName3.setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中
        columnName3.setColspan(2);
        columnName3.setRowspan(5);
        table.addCell(columnName);
        table.addCell(columnName1);
        table.addCell(columnName2);
        table.addCell(columnName3);
		return table;
	}
	

	/**
	 * @throws Exception  
	 * @描述:批量生成word
	 * @作者：CP[工号：1352]
	 * @日期：2017-2-22 下午03:23:52
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * File 返回类型 
	 * @throws 
	 */
	public File createZipWord(ZhfForm model) throws Exception {
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

	/**
	 * @throws Exception  
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：张昌路[工号：982]
	 * @日期：2017-3-13 下午07:51:25
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param time
	 * @param user
	 * @param values
	 * void 返回类型 
	 * @throws 
	 */
	public void addlog(String time, User user, String values) throws Exception {
		 dao.addlog(time,user,values);
	}

	/**
	 * @throws Exception  
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：张昌路[工号：982]
	 * @日期：2017-3-13 下午08:06:33
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param values
	 * void 返回类型 
	 * @throws 
	 */
	public void dellog(String values) throws Exception {
		// TODO 自动生成方法存根
		dao.dellog(values);
	}

	
}
