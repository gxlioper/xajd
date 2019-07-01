/**
 * @部门:学工产品事业部
 * @日期：2013-4-24 下午01:45:28 
 */  
package com.zfsoft.xgxt.xszz.zzxmjg;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import org.apache.commons.beanutils.BeanUtils;

import xgxt.action.Base;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.form.User;
import xgxt.utils.ExcelMethods;
import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.FreeMarkerUtil;
import common.exception.SystemException;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 学生资助管理模块
 * @类功能描述: service 
 * @作者： maxh 
 * @时间： 2013-4-24 下午01:45:28 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */
public class ZzxmjgService extends SuperServiceImpl<ZzxmjgForm, ZzxmjgDao>{

    private ZzxmjgDao dao = new ZzxmjgDao();
	
	public ZzxmjgService(){
		super.setDao(dao);
	}
	/**
	 * 
	 * @描述:唯一性判断（学号，学年，学期，项目名称）
	 * @作者：maxh
	 * @日期：2013-4-24 下午04:47:00
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param type
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean isExistByZzxmjg(ZzxmjgForm model, String type)throws Exception{
		if("save".equalsIgnoreCase(type)){
			String num=dao.checkExistForSave(model);
			return Integer.valueOf(num) > 0;
		} else {
			String num=dao.checkExistForUpdate(model);
			return Integer.valueOf(num) > 0;
		}
	    		
	}
	/**
	 * @throws Exception 
	 * 
	 * @描述:查看单个资助项目结果信息
	 * @作者：maxh
	 * @日期：2013-4-25 下午06:39:01
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param guid
	 * @return
	 * Map<String,String> 返回类型 
	 * @throws
	 */
	public Map<String, String> getOneZzxmjgList(String  guid) {
		
		
		return dao.getOneZzxmjgList(guid);
	}
	
	/**
	 * 导出获奖名单列表
	 */
	public void exportHjmdtj(ZzxmjgExportModel model , OutputStream os, User user) throws Exception{
		//学年
		String xn = model.getXn();
		//学期
		String xq = model.getXq();
		//学期名称
		List<HashMap<String,String>> xqList = Base.getXqList();
		String xqmc = null;
		for (HashMap<String,String> map : xqList){
			if (map.get("xqdm").equals(model.getXq())){
				xqmc = map.get("xqmc");
				break;
			}
		}
		//项目类别
		String[] xmlb = model.getXmlb();
		//学院代码
//		String[] xydm = model.getXydm();
		//项目名称
		String[] xmmc = model.getXmmc();
		
		WritableWorkbook wwb = Workbook.createWorkbook(os);
		WritableSheet ws = wwb.createSheet("获奖名单", 0);
		
		// 学院列表
		List<String[]> xymcList = dao.getXyList(xn, xq, xmlb, xmmc,user);
		// 各奖学金获奖人数
		List<String[]> jxjdmList = dao.getXmleList(xn, xq, xmlb, xmmc, user);
		// 获奖姓名
		List<String[]> xmList = dao.getHjmdList(xn, xq, xmlb, xmmc);
		
		//项目类别
		List<String> xmlbmc = dao.getXmlbmc(xmlb);
		String lbmc = null;
		for (int i = 0; i < xmlbmc.size(); i++) {
			if(i==0){
				lbmc=xmlbmc.get(i);
			}else{
				lbmc=lbmc+","+xmlbmc.get(i);
			}
		}
		
		//奖学金名称样式
		WritableCellFormat jxjFormat = new WritableCellFormat();
		WritableFont jxjFont = new WritableFont(WritableFont.ARIAL,13);
		jxjFont.setBoldStyle(WritableFont.BOLD);
		jxjFormat.setFont(jxjFont);
		jxjFormat.setAlignment(Alignment.LEFT);
		
		String title = StandardOperation.getXxmc() + xn + "学年";
		if(!StringUtils.isNull(xq)){ 	
			title += xqmc;
		}
		title += lbmc + "获得者名单";
		ws.addCell(new Label(0, 0, title, jxjFormat));
		ws.mergeCells(0, 0,10, 0);
		
		//xmList 获奖名单 
		//jxjdmList 项目获奖人数
		//xymcList 学院获奖人数
		//输出第一级奖学金名称和获奖人数
		if (!xmList.isEmpty()) {
			JxjExportProperties properties = new JxjExportProperties();
			for (int i=0;i<jxjdmList.size();i++) {//一级开始---------------------------------------------------------
				//将奖学金名称写入到EXCEL中
				String[] jxjdmArr = jxjdmList.get(i);
				String jxjmc = jxjdmArr[0];
				Label resultCell = new Label(0,properties.row, jxjmc + " (共" +jxjdmArr[1] +"人)");
				resultCell.setCellFormat(jxjFormat);
				ws.addCell(resultCell);
				ws.mergeCells(0, properties.row, 4, properties.row);
				
				//二级 开始 输出第二级将学院名称写到EXCEL中---------------------------------------------------------
				for (int index = 0; index < xymcList.size(); index++) {
					String[] xymcArr = xymcList.get(index);
					if (jxjmc.equalsIgnoreCase(xymcArr[1])) {						
						String xy = xymcArr != null && xymcArr.length >= 3 ? xymcArr[0] : "";
						List<String> xmByXyList = new ArrayList<String>();//存放每个奖学金下面的每个学院的获奖名单
						for (String[] xmArr : xmList) {//三级 开始 ---------------------------------------------------------
							if (xmArr != null && xmArr.length >= 3
									&& xy.equalsIgnoreCase(xmArr[0])
									&& jxjmc.equalsIgnoreCase(xmArr[1])) {
								xmByXyList.add(xmArr[2]);
							}
						}
						if (xmByXyList == null || xmByXyList.size() <= 0) {
							continue;
						}
						//输出学院名称
						++properties.row;
						Label xymcCell = new Label(1,properties.row,xy + " (" +xymcArr[2] + "人)");
						xymcCell.setCellFormat(jxjFormat);
						ws.addCell(xymcCell);
						ws.mergeCells(1,properties.row, 7, properties.row);
						//输出第三级将学院下面的获奖名单输出
						writeHjmdhzExcel(ws, properties, xmByXyList,0);//将获奖名单写入到EXCEL中
					}
				}//二级 结束---------------------------------------------------------
			}//一级结束始---------------------------------------------------------
		}
		
		
		ExcelMethods.submitWritableWorkbookOperations(wwb);//向客户端输出
	}

	public List<HashMap<String,String>> getXmzzqkhz(ZzxmjgForm model, User user) throws Exception {
		return dao.getXmzzqkhz(model,user);
	}

	public List<HashMap<String,String>> getZzxmInfoByXhXn(String xh, String xn, String xq) {
		return dao.getZzxmjgByXhXnList(xh,xn,xq);
	}

    public List<HashMap<String,String>> getJgExportList(ZzxmjgForm model, User user) throws Exception {
		return dao.getJgExportList(model,user);
    }

    private class JxjExportProperties{
		 int x_axis = 0;//X座标
		 int row = 1;//行标
		 int rowCellCount = 1;//每行的cell个数计数
		 int[] maxLength = {7,7,7,7,7,7,7};//默认的列宽
		public int getX_axis() {
			return x_axis;
		}
		@SuppressWarnings("unused")
		public void setX_axis(int x_axis) {
			this.x_axis = x_axis;
		}
		public int getRow() {
			return row;
		}
		public void setRow(int row) {
			this.row = row;
		}
		public int getRowCellCount() {
			return rowCellCount;
		}
		public void setRowCellCount(int rowCellCount) {
			this.rowCellCount = rowCellCount;
		}
		public int[] getMaxLength() {
			return maxLength;
		}
		public void setMaxLength(int[] maxLength) {
			this.maxLength = maxLength;
		}
	}
	
	private void writeHjmdhzExcel(WritableSheet ws,
			JxjExportProperties properties, List<String> xmList,
			int rowCellCount) throws Exception {
		properties.rowCellCount = 1;
		properties.row++;// 开始写姓名行
		properties.x_axis = rowCellCount;// 开始新奖学金的姓名
		if (xmList != null) {
			for (int index = 1; index < xmList.size() + 1; index++) {
				String cellContent = xmList.get(index - 1);
				if (properties.rowCellCount == 8) {
					properties.row++;// 满10个就换行
					properties.x_axis = 0;
					properties.rowCellCount = 1;// 重新每行计数
				}
				// 判断姓名长度,然后根据长度控制合并单元格，及超出长度的情况下进行换行
//				if (cellContent.length() > 3) {
//					int xmLength = cellContent.length() / 3
//							+ (cellContent.length() % 3 == 0 ? 0 : 1);
//					int pre_x_axis = properties.x_axis;// 合并前的x轴值
//					if (pre_x_axis + xmLength > 10) {
//						properties.row++;// 姓名过长，换行
//						properties.x_axis = rowCellCount;
//						properties.rowCellCount = 1;// 重新每行计数
//						pre_x_axis = properties.x_axis;
//					}
//					Label cell = new Label(++properties.x_axis, properties.row,
//							cellContent);
//					ws.addCell(cell);
//					ws.setColumnView(properties.x_axis, 7);
//					pre_x_axis++;
//					ws.mergeCells(pre_x_axis, properties.row, pre_x_axis
//							+ xmLength - 1, properties.row);
//					properties.x_axis = pre_x_axis + xmLength - 1;
//					properties.rowCellCount++;
//				} else {
					Label cell = new Label(++properties.x_axis, properties.row,
							cellContent);
					ws.addCell(cell);
					ws.setColumnView(properties.x_axis, 7);
					properties.rowCellCount++;
//				}
			}
		}
		properties.row += 1;// 新奖学金换行
	}

	/**
	 * 
	 * @描述:根据项目名称，按学年、学期统计资助人数
	 * @作者：ligl
	 * @日期：2013-5-28 下午02:48:35
	 * @修改记录:
	 * @param xmdm
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> tjrs(String xmmc) throws Exception {
		if(xmmc == null){
			logger.debug("参数不能为空！");
			throw new SystemException("参数不能为空！");
		}else{
			xmmc = xmmc.trim();
		}
		List<HashMap<String, String>> result = dao.tjrs(xmmc);
		return result;
	}
	/**
	 * 
	 * @描述:华中师范 大学个性化证书编码获取(证书编码规则：年度+项目代码+流水号：默认从0001开始(按年级、学院))
	 * @作者：夏夏[工号：1104]
	 * @日期：2014-7-4下午01:38:58
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return String 返回类型
	 * @throws
	 */
	public String getZsbm(ZzxmjgForm model) {
		StringBuffer zsbm = new StringBuffer();
		List<HashMap<String, String>> zsbmList = dao.getZsbm(model);
		if (zsbmList.size() > 0) {
			int zsbh=0;
			zsbh = Integer.parseInt(zsbmList.get(0).get("zsbm"))+1;
			StringBuffer zsbhstr=new StringBuffer();
			if(zsbh>99&&zsbh<1000){
				zsbhstr.append("0").append(zsbh);
			}
			else if(zsbh>9&&zsbh<100){
				zsbhstr.append("00").append(zsbh);
			}
			else{
				zsbhstr.append("000").append(zsbh);
			}
			zsbm.append(model.getPdxn()).append(model.getXmdm()).append(zsbhstr);
		} else {
			zsbm.append(model.getPdxn()).append(model.getXmdm()).append("0001");
		}
		return zsbm.toString();
	}
	/**
	 * 
	 * @描述:根据项目名称获取项目代码
	 * @作者：夏夏[工号：1104]
	 * @日期：2014-7-34下午03:22:20
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * string 返回类型 
	 * @throws
	 */
	public String getXmdm(ZzxmjgForm model){
		return dao.getXmdm(model);
	}
	
	/**
	 * 
	 * @描述:通过学号查询资助项目结果
	 * @作者：ligl
	 * @日期：2013-11-30 下午03:07:04
	 * @修改记录: 
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getZzxmjgInfoList(String xh) {
		return dao.getZzxmjgInfoList(xh);
	}
	
	public List<HashMap<String, String>> getZzxmhzList(ZzxmjgForm model,User user)  throws Exception{
		return dao.getZzxmhzList(model,user);
	}
	
	//条件查询资助项目汇总信息
	public List<HashMap<String, String>> zzxmhzView(ZzxmjgForm model,User user,Boolean fyFlag) throws Exception{
		return dao.zzxmhzView(model, user,fyFlag);
	}
	
	//条件查询资助项目汇总信息（闽南师范学院个性化）
	public List<HashMap<String, String>> zzxmhzView_10402(ZzxmjgForm model,User user) throws Exception{
		return dao.zzxmhzView_10402(model, user);
	}
	
	//查询学生职务
	public String getZwForXh(String xh){
		return dao.getZwForXh(xh);
	}
	/**
	 * 
	 * @描述:筛选可复制的周期
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-4-18 下午03:21:07
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param knsjgzq
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getkfzZqList(String zzjgzq) {
		List<HashMap<String, String>> list = dao.getzzjgZqList();
		for (HashMap<String, String> hm : list) {
			if (hm.get("xn").equals(zzjgzq)) {
				list.remove(hm);
				break;
			}
		}
		return list;
	}
	/**
	 * 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-4-18 下午03:37:18
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param lyxn
	 * @param lyxq
	 * @param xn
	 * @param xq
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean copy(String lyxn, String mbxn)
			throws Exception {
		boolean result = true;
		List<HashMap<String, String>> list = dao.getZzxmjgOfXnList(lyxn,mbxn);
		ZzxmjgForm model = new ZzxmjgForm();
		for (HashMap<String, String> hm : list) {
			BeanUtils.copyProperties(model, hm);
			model.setGuid(null);
			model.setXn(mbxn);
			result = runInsert(model);
		}
		return result;
	} 
	public ZzxmjgForm getModel(String guid)
	throws Exception {
		return dao.getModel(guid);
	}
	
	public Map<String, String> getJzgInfo(User user){
		return dao.getJzgInfo(user);
	}
	
	public List<HashMap<String,String>> getZzxmjgHzList() {
		return dao.getZzxmjgHzList();
	}
	
	//山东畜牧兽医职业学院个性化打印(社会助学金汇总表)
	public File getXmGxhDy_12947_shzxjhzexcel(String parameters,String xn,String xq)
		throws Exception {
	Map<String,Object> data = new HashMap<String,Object>();
	List<HashMap<String, String>> xsxxlist = null;
    String[] xn1 = xn.trim().split(",");
    String[] xq1 = xq.trim().split(",");
    String[] value = parameters.split(",");
    String values = value[0];
    if(xn1.length>xq1.length){
    	for(int i=0;i<xn1.length;i++){
    		HashMap<String, String> xsxxmap = new HashMap<String, String>();
        	for(int j=0;j<xq1.length;j++){
        	 xsxxmap = dao.getShzxjhz(values, xn1[i], xq1[i]);
        	 continue;
        	}
        	 xsxxlist.add(xsxxmap);
    	}    
    }else if(xn1.length == xq1.length){
    	for(int i=0;i<xn1.length;i++){
    		HashMap<String, String> xsxxmap = new HashMap<String, String>();
        	for(int j=0;j<xq1.length;j++){
        	 xsxxmap = dao.getShzxjhz(values, xn1[i], xq1[j]);
        	}
        	xsxxlist.add(xsxxmap);
    	}    
    }else{
    	for(int i=0;i<xq1.length;i++){
    		HashMap<String, String> xsxxmap = new HashMap<String, String>();
        	for(int j=0;j<xn1.length;j++){
        	 xsxxmap = dao.getShzxjhz(values, xn1[j], xq1[i]);
        	 continue;
        	}
        	xsxxlist.add(xsxxmap);
    	}    
    }
	HashMap<String, String> totalxx = dao.getshzxjTotal(values);
	data.put("xsxxlist",xsxxlist);
	data.putAll(totalxx);
	if(xsxxlist != null){
		File excelFile = FreeMarkerUtil.getExcelFile(data,"classpath://templates//xszz//excel", "sdxmsy_12947_shzxjhzb.xml", "社会助学金学生汇总表");
		return excelFile;
	}
	
	return null;

    }
	
	/*山东畜牧兽医职业学院个性化打印(国家励志奖学金汇总表)
	public File getXmGxhDy_12947_gjlzjhzexcel(String[] parameters,User user)
		throws Exception {
		Map<String,Object> data = new HashMap<String,Object>();
		List<HashMap<String, String>> xsxxlist = dao.getShzxjHzbxxList(parameters);
		data.put("xsxxlist",xsxxlist);
		String jbr = user.getRealName();
		String username = user.getUserName();
		String lxdh = (dao.getlxfs(username)).get("lxdh");
	    data.put("jbr", jbr);
	    data.put("lxdh", lxdh);
	    data.put("xx",Base.xxmc);
	    SimpleDateFormat datenow = new SimpleDateFormat("yyyy-MM-dd");
	    String date = datenow.format(new Date());
	    String year = date.substring(0, 4);
	    String month = date.substring(5, 7);
	    String day = date.substring(8, 10);
	    data.put("year", year);
	    data.put("month", month);
	    data.put("day", day);
	    data.put("xn", Base.currXn);
		File excelFile = FreeMarkerUtil.getExcelFile(data,"classpath://templates//xszz//excel", "sdxmsy_12947_gjlzjhz.xml", "国家励志奖学金学生汇总表");
		return excelFile;

    }*/
	
	//山东畜牧兽医职业学院个性化打印(国家助学金备案汇总表)
	public List<HashMap<String, String>> getXmGxhDy_12947_gjzxjbahzexcel(String parameters,String xn,String xq)
		throws Exception {
		List<HashMap<String, String>> xsxxlist = dao.getShzxjHzbxxList(parameters,xn,xq);
		return xsxxlist;
    }
	
	public File getXmGxhDy_12947_gjlzjhzmbexcel(String[] parameters)
		throws Exception {
		Map<String,Object> data = new HashMap<String,Object>();
		List<HashMap<String, String>> xsxxlist = dao.getLzjHzXsxx(parameters);
		data.put("xsxxlist",xsxxlist);
		File excelFile = FreeMarkerUtil.getExcelFile(data,"classpath://templates//xszz//excel", "sdxmsy_12947_gjlzjhzmb.xml", "国家励志奖学金汇总模板");
		return excelFile;
	}
	
	/**
	 * 
	 * @描述:获取勤工助学list
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2015-7-6 上午11:53:06
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getQgzxList(String xh){
		return dao.getQgzxList(xh);
	}
	
	/**
	 * 
	 * @描述:获取项目奖项list
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2015-7-6 下午03:11:56
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param xmmc
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getMjxList(String xh,String xmmc){
		return dao.getMjxList(xh, xmmc);
	}
	
	/**
	 * 
	 * @描述:是否办理助学贷款
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2015-7-7 下午02:15:06
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getSfzxDk(String xh){
		return dao.getSfzxDk(xh);
	}
	
	/**
	 * 
	 * @描述:判断是否是新生
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2015-7-7 下午03:44:53
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String, String>  getSfxs(){
		return dao.getSfxs();
	}
	
	/**
	 * 
	 * @描述:判断是否困难生
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2015-7-7 下午03:49:28
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param xn
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getSfkns(String xh,String xn){
		return dao.getSfkns(xh, xn);
	}
	
	/**
	 * 
	 * @描述:获取全拼拼音
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2015-7-8 上午09:55:04
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param str
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getPinyin(String str){
		return  dao.getPinyin(str);
	}
	
	/**
	 * 
	 * @描述: 重庆三峡医高专资助项目汇总
	 * @作者：沈晓波[工号：1123]
	 * @日期：2015-10-27 下午04:40:40
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getDxscbInfoList(ZzxmjgForm model,User user) throws Exception{
		return dao.getDxscbInfoList(model, user);
	}
	
	/**
	 * @描述:TODO(辽宁机电职业技术学院个性化)
	 * @作者：孟威[工号：1186]
	 * @日期：2016-8-5 下午02:53:00
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getLnjdzyjsxyList(ZzxmjgForm model,User user) throws Exception{
		return dao.getLnjdzyjsxyList(model, user);
	}
	
	/**
	 * 
	 * @描述: 上海体育资助项目汇总表总金额
	 * @作者：yxy[工号：1206]
	 * @日期：2016-10-14 下午05:14:26
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param param
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String[] getZje_shty(String[] param){
		return dao.getZje_shty(param);
	}
	
	/**
	 * 
	 * @描述:上海体育资助项目汇总表专业金额
	 * @作者：yxy[工号：1206]
	 * @日期：2016-10-14 下午05:34:06
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param param
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getzyhz_shty(String[] param){
		return dao.getzyhz_shty(param);
	}
	
	/**
	 * 
	 * @描述:上海体育资助项目汇总表年级金额
	 * @作者：yxy[工号：1206]
	 * @日期：2016-10-15 上午09:25:17
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param param
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getnjhz_shty(String[] param){
		return dao.getnjhz_shty(param);
	}
	/**
	 * 
	 * @描述: 上海体育学院专业列表
	 * @作者：yxy[工号：1206]
	 * @日期：2016-10-15 上午10:13:15
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getxyzyList_shty(){
		return dao.getxyzyList_shty();
	}
	
	/**
	 * 
	 * @描述:上海体育学院年级列表
	 * @作者：yxy[工号：1206]
	 * @日期：2016-10-15 上午10:18:48
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getnjList(){
		return dao.getnjList();
	}
	
	/**
	 * 
	 * @描述: 上海体育年级学院资助项目金额汇总主查询
	 * @作者：yxy[工号：1206]
	 * @日期：2016-10-15 上午10:21:47
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getnjxyzy_shty(String[] param){
		return dao.getnjxyzy_shty(param);
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述: 上海体育资助项目汇总统计查询导出excel
	 * @实现：一次性全部取出写excel需要的数据，避免循环中去调用底层查询数据
	 * 写excel采取嵌套迭代的方式
	 * @作者：yxy[工号：1206]
	 * @日期：2016-10-15 上午11:11:35
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param param
	 * @return
	 * WritableWorkbook 返回类型 
	 * @throws
	 */
	public  void createWwb(String[] param,OutputStream os) throws Exception{
		WritableWorkbook wwb = Workbook.createWorkbook(os);
		/**
		 * 获取导出excel表格的后台数据
		 */
		//年级List
		List<HashMap<String, String>> njList = this.getnjList();
		//专业List
		List<HashMap<String, String>> xyzyList = this.getxyzyList_shty();
		//主查询数据
		List<HashMap<String, String>> zcxtjList = this.getnjxyzy_shty(param);
		//年级汇总数据
		List<HashMap<String, String>> njjeList = this.getnjhz_shty(param);
		//专业汇总数据
		List<HashMap<String, String>> zyjeList = this.getzyhz_shty(param);
		//学院List
		List<HashMap<String, String>> xyList = this.getXyList();
		//总金额
		String[] zhsj = this.getZje_shty(param);
		int njNum = njList.size();
		int xyNum = xyList.size();
		int zyNum = xyzyList.size();
		int zcxNum = zcxtjList.size();
		int njjeNum = njjeList.size();
		/**
		 * 一个年级占两列，所以njNum*2
		 */
		int tableColumnNum = njNum*2+4;
		/**
		 * 取出字体样式
		 */
		WritableCellFormat fonthead =  this.getFontStyle("head");
		WritableCellFormat fontbodyright = this.getFontStyle("bodyright");
		WritableCellFormat fontbodyleft = this.getFontStyle("bodyleft");
		WritableCellFormat fonttitle = this.getFontStyle("title");
		 WritableSheet ws = wwb.createSheet("资助项目金额汇总数据", 0);
		 /**
		  * 写入标题
		  */
		 //如果只有单个项目，打印出该项目，否则填成资助项目
		 String titilemc = "资助项目";
		 if(param.length == 1){
			 titilemc = this.getZzxmMc(param[0]);
		 }
		 Label labelTitle= new Label(0, 0, "上海体育学院"+titilemc+"发放人数汇总表",fonttitle);
		 //合并单元格列数为表格的总列数s
		 ws.mergeCells(0, 0, tableColumnNum-1, 0);
		 ws.addCell(labelTitle);
		 
		 /**
		  * 表头
		  */
		 /**
		  * 表头前两列固定的=直接加
		  */
		 
		 Label row2col1= new Label(0, 1, "",fonthead);
		 Label row2col2= new Label(1, 1, "",fonthead);
		 Label row3col1= new Label(0, 2, "院系/部",fonthead);
		 Label row3col2= new Label(1, 2, "专业",fonthead);
		 ws.addCell(row2col1);
		 ws.addCell(row2col2);
		 ws.addCell(row3col1);
		 ws.addCell(row3col2);
		 /**
		  * 从第三列开始到倒数第三列为止，是不固定的，因为年级个数是动态的，不确定
		  */
		 for (int i = 2; i < 2+njNum*2; i=i+2) {
			 //年级那一行
			 if(i+1 % 2 !=0){
					 Label label = new Label(i,1,njList.get(i-2-(i-2)/2).get("nj"),fonthead);
					 ws.addCell(label);
			 }
//			 Label label = new Label(i,1,njList.get(i-2).get("nj"),fonthead);
			
			 ws.mergeCells(i, 1, i+1, 1);
			 for (int j = i; j < i+2; j++) {
				 if((j+1)%2 != 0){
					 Label labelZb = new Label(j,2,"人数",fonthead);
					 ws.addCell(labelZb);
				 }else{
					 Label labelZb = new Label(j,2,"金额",fonthead);
					 ws.addCell(labelZb);
				 }
				 
			 }
		 }
		 //表头最后两列
		 Label labelLastTwo = new Label(2+njNum*2,1,"人数小计", fonthead);
		 Label labelLastOne = new Label(2+njNum*2+1,1,"金额小计", fonthead);
		 ws.addCell(labelLastTwo);
		 ws.addCell(labelLastOne);
		 ws.mergeCells(2+njNum*2, 1, 2+njNum*2, 2);
		 ws.mergeCells(2+njNum*2+1, 1, 2+njNum*2+1, 2);
		 //行数初始值
		 int countRow = 3;
		 //一级循环，输出学院
		 for(int i=0;i<xyNum;i++){
			 HashMap<String, String> nowXy = xyList.get(i);
			 int zygs = Integer.parseInt(nowXy.get("zygs"));
			 Label labelXy = new Label(0, countRow, nowXy.get("xymc"), fontbodyleft);
			 ws.addCell(labelXy);
			 ws.mergeCells(0, countRow,0, countRow+zygs-1);
			 //保险起见，迭代专业List，取出该学院下的所有专业
			 List<HashMap<String, String>> tempZyList = new ArrayList<HashMap<String,String>>(); 
			 for (int zylen = 0; zylen < zyNum; zylen++) {
				if(xyzyList.get(zylen).get("xydm").equals(nowXy.get("xydm"))){
					tempZyList.add(xyzyList.get(zylen));
				}
			 }
			 //二级循环，输出专业
			 for (int j = 0; j < zygs; j++) {
				Label labelzy = new Label(1, countRow+j, tempZyList.get(j).get("zymc"),fontbodyleft);
				ws.addCell(labelzy);
				//三级循环迭代，输出每行专业后面 的人数，金额，（人数小计，金额小计，属于专业汇总数据）等数据
				//每个专业都有求不同年级的值，所以先循环年级
				for (int k = 0; k < njNum; k++) {
					HashMap<String, String> nowNj = njList.get(k);
					HashMap<String, String> comareObj = new HashMap<String, String>();
					String mhrs = "0";
					String mhje = "0.00";
					for (int k2 = 0; k2 < zcxNum; k2++) {
						 comareObj = zcxtjList.get(k2);
						 mhrs = comareObj.get("mhrs");
						 mhje = comareObj.get("mhje");
						boolean flag = comareObj.get("xydm").equals(nowXy.get("xydm"))&&
						comareObj.get("zydm").equals(tempZyList.get(j).get("zydm"))&&comareObj.get("nj").equals(nowNj.get("nj"));
					    if(flag){
						   break;
					    }
					}
					   Label labelrs = new Label(2+(k-0)*2, countRow+j, mhrs, fontbodyright);
					   ws.addCell(labelrs);
					   Label labelje = new Label(2+(k-0)*2+1, countRow+j, mhje, fontbodyright);
					   ws.addCell(labelje);
					//如果每行专业人数金额全部已经写完，再补上每行专业胡汇总数据（人数小计，金额小计）
					 if(k==njNum-1){
						String zyrs = "0";
						String zyje = "0.00";
						
						for (int l = 0; l < zyjeList.size(); l++) {
							if(zyjeList.get(l).get("zydm").equals(tempZyList.get(j).get("zydm"))){
								zyrs = zyjeList.get(l).get("zyrs");
								zyje =  zyjeList.get(l).get("zyje");
							}
						}
						Label labelzyhzrs = new Label(2+(k-0)*2+1+1, countRow+j, zyrs, fontbodyright);
						ws.addCell(labelzyhzrs);
						Label labelzyhzje = new Label(2+(k-0)*2+1+1+1, countRow+j, zyje, fontbodyright);
						ws.addCell(labelzyhzje);
					}
				}
			 }
			 countRow = countRow+zygs;
		 }
		 //年级汇总数据
		 for (int i = 0; i < njjeNum; i++) {
			HashMap<String, String> njjeMap = njjeList.get(i);
			Label njrs = new Label(2+(i-0)*2, zyNum+3, njjeMap.get("njrs"), fontbodyright);
			Label njje = new Label(2+(i-0)*2+1, zyNum+3, njjeMap.get("njje"), fontbodyright);
			ws.addCell(njrs);
			ws.addCell(njje);
		 }
		 //总和
		 Label sumrs = new Label(tableColumnNum-2, zyNum+3, zhsj[1], fontbodyright);
		 Label sumje = new Label(tableColumnNum-1, zyNum+3, zhsj[0], fontbodyright);
		 ws.addCell(sumrs);
		 ws.addCell(sumje);
		 WritableCellFormat hj = this.getFontStyle("bodyleft");
		 hj.setAlignment(jxl.format.Alignment.CENTRE);
		 ws.addCell(new Label(0,zyNum+3,"合  计", hj));
		 try{
			 wwb.write();
			 wwb.close();
		 }catch(Exception e){
				
		 }
	}
	
	/**
	 * @throws WriteException 
	 * 
	 * @描述: 获取单元格字体样式
	 * @作者：yxy[工号：1206]
	 * @日期：2016-10-15 下午01:27:40
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param paras
	 * @return
	 * WritableFont 返回类型 
	 * @throws
	 */
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
		     WritableFont wf_head = new WritableFont(WritableFont.ARIAL, 10,  
		                WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE,  
		                jxl.format.Colour.BLACK); 
			  WritableCellFormat wcf_head = new WritableCellFormat(wf_head);   
		      wcf_head.setAlignment(jxl.format.Alignment.CENTRE);
		      wcf_head.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
		      return wcf_head;
		}else if("bodyright".equals(paras)){
			  WritableFont wf_table = new WritableFont(WritableFont.ARIAL, 8,  
		                WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE,  
		                jxl.format.Colour.BLACK); 
			  WritableCellFormat wcf_table = new WritableCellFormat(wf_table);   
	          //wcf_table.setBackground(jxl.format.Colour.BLACK);   
	          wcf_table.setAlignment(jxl.format.Alignment.RIGHT);  
	          return wcf_table;
		}else if("bodyleft".equals(paras)){
			  WritableFont wf_table = new WritableFont(WritableFont.ARIAL, 8,  
		                WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE,  
		                jxl.format.Colour.BLACK);   
			  WritableCellFormat wcf_table = new WritableCellFormat(wf_table);   
//			  wcf_table.setShrinkToFit(true);
	          wcf_table.setAlignment(jxl.format.Alignment.LEFT);
	          wcf_table.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
	          return wcf_table;
		}
     
        return null;
	}
	
	/**
	 * 
	 * @描述: 上海体育学校获取学院列表
	 * @作者：yxy[工号：1206]
	 * @日期：2016-10-15 下午02:20:52
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getXyList(){
	    return dao.getXyList();
	}
	
	/**
	 * 
	 * @描述: 资助项目名称
	 * @作者：yxy[工号：1206]
	 * @日期：2016-10-17 下午04:19:56
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param paras
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getZzxmMc(String paras){
		return dao.getZzxmMc(paras);
	}
	
	/** 
	 * @描述:导入插入(青岛酒店管理学院个性化)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-3-2 下午05:46:31
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param params
	 * @return
	 * @throws SQLException
	 * boolean 返回类型 
	 * @throws 
	 */
	@SuppressWarnings("unchecked")
	public HashMap<String,Object> saveDrForQdjd(ZzxmjgForm form) throws FileNotFoundException, IOException, SQLException, BiffException {
		InputStream is = form.getDrmb().getInputStream();
		Workbook wb = Workbook.getWorkbook(is);
		HashMap<String,Object> map = new HashMap<String, Object>();
		Sheet sheet = wb.getSheet(0);
		int cols = sheet.getColumns();
		int rows = sheet.getRows();
		if(cols < 9 && rows < 2){
			map.put("result", "null");
			return map;
		}else {
			List<ZzxmjgForm> list = new ArrayList<ZzxmjgForm>();
			int num = 0;
			for(int i = 1;i<rows;i++){
				ZzxmjgForm zf = new ZzxmjgForm();
				String xh = sheet.getCell(0, i).getContents();
				String sfzh = sheet.getCell(1, i).getContents();
				String xn = sheet.getCell(2, i).getContents();
				String xqmc = sheet.getCell(3, i).getContents();
				String pdxn = sheet.getCell(4, i).getContents();
				String pdxqmc = sheet.getCell(5, i).getContents();
				String lbmc = sheet.getCell(6, i).getContents();
				String xmmc = sheet.getCell(7, i).getContents();
				String je = sheet.getCell(8, i).getContents();
				if("".equalsIgnoreCase(xh) && "".equalsIgnoreCase(sfzh) && "".equalsIgnoreCase(xn) && "".equalsIgnoreCase(xqmc) && "".equalsIgnoreCase(pdxn) && "".equalsIgnoreCase(pdxqmc) && "".equalsIgnoreCase(lbmc) && "".equalsIgnoreCase(xmmc) && "".equalsIgnoreCase(je)){
					num++;
					continue;
				}
				zf.setXh(xh);
				zf.setSfzh(sfzh);
				zf.setXn(xn);
				zf.setXqmc(xqmc);
				zf.setPdxn(pdxn);
				zf.setPdxqmc(pdxqmc);
				zf.setLbmc(lbmc);
				zf.setXmmc(xmmc);
				zf.setJe(je);
				list.add(zf);				
			}
			if(num == rows-1) {//如果全为空行
				map.put("result", "null");
				return map;
			}else{
				HashMap<String,Object> resultMap = checkEveryRow(list);
				if(resultMap.get("errorList") != null){//如果有错误数据
					map.put("result", "false");
					List<ZzxmjgForm> errorList = (List<ZzxmjgForm>) resultMap.get("errorList");
					String gid = upLoadErrorXx(form.getFilepath(), errorList);//上传错误信息至excel文件
					map.put("gid", gid);//放置文件路径
					map.put("cws", String.valueOf(errorList.size()));//错误条数
				}else{
					map.put("result", "true");
				}
				List<ZzxmjgForm> trueList = (List<ZzxmjgForm>) resultMap.get("trueList");
				if(null != trueList && trueList.size()>0){//正确数据
					List<String[]> insertList = new ArrayList<String[]>();
					for(ZzxmjgForm model : trueList){
						String[] params = new String[8];
						params[0] = model.getXh();
						params[1] = model.getXn();
						params[2] = model.getXq();
						params[3] = model.getPdxn();
						params[4] = model.getPdxq();
						params[5] = model.getLbdm();
						params[6] = model.getXmmc();
						params[7] = model.getJe();
						insertList.add(params);
					}
					int[] p = dao.DrForInsert(insertList);//批量插入验证通过的数据
					map.put("cgs", String.valueOf(p.length));//导入成功条数
				}
			}
		}
		return map;		
	}
	
	/** 
	 * @描述:验证每条记录(青岛酒店管理学院个性化)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-3-2 下午05:49:21
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * Map<String,String> 返回类型 
	 * @throws 
	 */
	public HashMap<String,Object> checkEveryRow(List<ZzxmjgForm> list){
		HashMap<String,Object> map = new HashMap<String, Object>();
		List<ZzxmjgForm> trueList = new ArrayList<ZzxmjgForm>();//正确的数据
		List<ZzxmjgForm> errorList = new ArrayList<ZzxmjgForm>();//错误的数据
		for(ZzxmjgForm form : list){
			if(checkBtzdForEveryRow(form)){
				trueList.add(form);
				map.put("trueList", trueList);
			}else{
				errorList.add(form);
				map.put("errorList", errorList);
			}
		}		
		return map;
	}
	
	/** 
	 * @描述:验证每行的每个字段(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-3-3 上午10:58:25
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param form
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean checkBtzdForEveryRow(ZzxmjgForm form){
		boolean result = true;
		if("".equals(form.getXh()) && "".equals(form.getSfzh())){
			form.setYlzd4("请填写学号或身份证号");
			return false;
		}else if(!"".equalsIgnoreCase(form.getXh()) && "".equalsIgnoreCase(form.getSfzh())){
			result = dao.checkXhExist(form);
			if(!result){
				form.setYlzd4("学号不存在");
				return false;
			}
		}else if(!"".equalsIgnoreCase(form.getSfzh()) && "".equalsIgnoreCase(form.getXh())){
			result = dao.checkSfz(form);
			if(!result){
				form.setYlzd4("身份证号不存在");
				return false;
			}
		}else if(!"".equalsIgnoreCase(form.getSfzh()) && !"".equalsIgnoreCase(form.getXh())){
			result = dao.checkXhAndSfzh(form);
			if(!result){
				form.setYlzd4("学号与身份证号不符");
				return false;
			}
		}
		
			if("".equalsIgnoreCase(form.getXn())){
				form.setYlzd4("请填写申请学年");
				return false;
			}
			if("".equalsIgnoreCase(form.getXqmc())){
				form.setYlzd4("请填写申请学期");
				return false;
			}else{
				result = dao.checkXq(form,"xq");
				if(!result){
					form.setYlzd4("请输入正确的学期");
					return false;
				}
			}
			if("".equalsIgnoreCase(form.getPdxn())){
				form.setYlzd4("请填写评定学年");
				return false;
			}
			if("".equalsIgnoreCase(form.getPdxqmc())){
				form.setYlzd4("请填写评定学期");
				return false;
			}else{
				result = dao.checkXq(form,"pdxq");
				if(!result){
					form.setYlzd4("请输入正确的评定学期");
					return false;
				}
			}
			if("".equalsIgnoreCase(form.getLbmc())){
				form.setYlzd4("请填写类别名称");
				return false;
			}else{
				result = dao.checkXmlb(form);
				if(!result){
					form.setYlzd4("请输入正确的类别名称");
					return false;
				}
			}
			if("".equalsIgnoreCase(form.getXmmc())){
				form.setYlzd4("请填写项目名称");
				return false;
			}else{
				if(form.getXmmc().length()>50){
					form.setYlzd4("项目名称限制输入50个字");
					return false;
				}
			}
			if("".equalsIgnoreCase(form.getJe())){
				form.setYlzd4("请填写金额");
				return false;
			}else{
				if(!StringUtils.isNumeric(form.getJe())){
					form.setYlzd4("金额请输入数字");
					return false;
				}
			}				
		return true;
	}
	
	/**
	 * @throws IOException  
	 * @描述:上传错误文件信息(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-3-3 下午02:07:20
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param path
	 * @param errorList
	 * void 返回类型 
	 * @throws 
	 */
	public String upLoadErrorXx(String path,List<ZzxmjgForm> errorList) throws IOException{
		String fileName = System.currentTimeMillis() + ".xls";
		File file = new File(path+fileName);
		if(!file.exists()){
			file.createNewFile();
		}
		WritableWorkbook book = Workbook.createWorkbook(file);
		WritableSheet sheet = book.createSheet("错误信息", 0);
		 Label labelxh= new Label(0, 0, "学号");//表示第
         Label labelsfzh= new Label(1, 0, "身份证号");
         Label labelsqxn= new Label(2, 0, "申请学年");
         Label labelxqmc= new Label(3, 0, "申请学期名称");
         Label labelpdxn= new Label(4, 0, "项目评定学年");
         Label labelpdxq= new Label(5, 0, "项目评定学期名称");
         Label labellbmc= new Label(6, 0, "类别名称");
         Label labelxmmc= new Label(7, 0, "项目名称");
         Label labelje = new Label(8, 0, "金额");
         Label labelxx = new Label(9,0,"错误提示");
         
         try {
			 sheet.addCell(labelxh);
			 sheet.addCell(labelsfzh);
			 sheet.addCell(labelsqxn);
			 sheet.addCell(labelxqmc);
			 sheet.addCell(labelpdxn);
			 sheet.addCell(labelpdxq);
			 sheet.addCell(labellbmc);
			 sheet.addCell(labelxmmc);
			 sheet.addCell(labelje);
			 sheet.addCell(labelxx);
		} catch (RowsExceededException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		} catch (WriteException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		 try {
			 WritableCellFormat wcf = new WritableCellFormat();
			 WritableFont wf = new WritableFont(WritableFont.ARIAL);
			 wf.setColour(Colour.RED);
			 wcf.setFont(wf);
			 wcf.setAlignment(Alignment.CENTRE);
			for(int i = 0;i<errorList.size();i++){
				 Label labelxh1= new Label(0, i+1,errorList.get(i).getXh());//
			     Label labelsfzh1= new Label(1, i+1, errorList.get(i).getSfzh());
			     Label labelsqxn1= new Label(2, i+1, errorList.get(i).getXn());
			     Label labelxqmc1= new Label(3, i+1, errorList.get(i).getXqmc());
			     Label labelpdxn1= new Label(4, i+1, errorList.get(i).getPdxn());
			     Label labelpdxq1= new Label(5, i+1, errorList.get(i).getPdxqmc());
			     Label labellbmc1= new Label(6, i+1, errorList.get(i).getLbmc());
			     Label labelxmmc1= new Label(7, i+1, errorList.get(i).getXmmc());
			     Label labelje1 = new Label(8, i+1, errorList.get(i).getJe());
			     Label labelxx1 = new Label(9, i+1, errorList.get(i).getYlzd4(),wcf);//错误信息 
			     sheet.addCell(labelxh1);
				 sheet.addCell(labelsfzh1);
				 sheet.addCell(labelsqxn1);
				 sheet.addCell(labelxqmc1);
				 sheet.addCell(labelpdxn1);
				 sheet.addCell(labelpdxq1);
				 sheet.addCell(labellbmc1);
				 sheet.addCell(labelxmmc1);
				 sheet.addCell(labelje1);
				 sheet.addCell(labelxx1);
			}
		} catch (RowsExceededException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		} catch (WriteException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}finally{
			book.write();
			try {
				book.close();
			} catch (WriteException e) {
				// TODO 自动生成 catch 块
				e.printStackTrace();
			}
		}
		return fileName;
         
	}
	
	/**
	 * @描述：资助结果最新n条
	 * @作者：zhuon[工号:1391]
	 * @日期：2017年9月6日
	 * @修改记录: 修改者名字-修改日期-修改内容
	 */
	public List<HashMap<String,String>> getZzjgList(String xh,int n){
		if (StringUtil.isNull(xh)){
			logger.error("xh is null !");
			throw new NullPointerException();
		}
		List<HashMap<String, String>> list = dao.getZzjgList(xh,String.valueOf(n));
		int m=n-list.size();
		for (int i = 0; i <m; i++) {
			list.add(new HashMap<String, String>());
		}
		return list;
	}
	
	public HashMap<String, String> getZjlyAxjjMap(String xh, String xn) {
		return dao.getZjlyAxjjMap(xh, xn);
	}
	
	public HashMap<String, String> getZjlyGeaxMap(String xh, String xn) {
		return dao.getZjlyGeaxMap(xh,xn);
	}
	
	public HashMap<String, String> getZjlyXfjmMap(String xh, String xn) {
		return dao.getZjlyXfjmMap(xh,xn);
	}
	
	public HashMap<String, String> getZjlyShfjmMap(String xh, String xn) {
		return dao.getZjlyShfjmMap(xh,xn);
	}
	/** 
	 * 浙江旅游-商业保险补助
	 */
	public HashMap<String, String> getZjlySybxbzMap(String xh, String xn) {
		return dao.getZjlySybxbzMap(xh,xn);
	}
	
	public HashMap<String, String> getZjlyJtbzMap(String xh, String xn) {
		return dao.getZjlyJtbzMap(xh,xn);
	}
	
	public HashMap<String, String> getZjlyJnkzfbzmapMap(String xh, String xn) {
		return dao.getZjlyJnkzfbzmapMap(xh,xn);
	}
	
	public HashMap<String, String> getZjlyXfjmmapMap(String xh, String xn) {
		return dao.getZjlyXfjmmapMap(xh,xn);
	}
	public HashMap<String, String> getZjlyZxjmapMap(String xh, String xn) {
		return dao.getZjlyZxjmapMap(xh,xn);
	}
	public HashMap<String, String> getWzdxbxkms(String xh,String xn ) {
		return dao.getWzdxbxkms(xh,xn);
	}
	public List<HashMap<String, String>> getJxtczzxmList(ZzxmjgForm model,
			User user) {
		return dao.getJxtczzxmList(model, user);
	}
	/**
	 * 重庆工商个性化填充
	 */
	public HashMap<String, String> getQtzzje(String xh,String xn ) {
		return dao.getQtzzje(xh,xn);
	}public HashMap<String, String> getKncsAndJe(String xh,String xn ) {
		return dao.getKncsAndJe(xh,xn);
	}
	public List<HashMap<String, String>> getShyjList(String xh, String xn, String xq, String xmdm) {
		return dao.getShyjList(xh, xn, xq, xmdm);
	}
	//温大
	public List<HashMap<String, String>> getWzdxzzxmList(ZzxmjgForm model,
			User user) {
		return dao.getWzdxzzxmList(model, user);
	}
	
	/**
	 * @description	： 国家奖学金导出
	 * @author 		： lj（1282）
	 * @date 		：2018-4-19 上午08:36:49
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception 
	 */
	public List<HashMap<String,String>> getGjjxjdc(ZzxmjgForm t, User user) throws Exception {
		return dao.getGjjxjdc(t, user);
	}
	
	/**
	 * @description	： 国家助学金导出
	 * @author 		： lj（1282）
	 * @date 		：2018-4-19 下午01:57:55
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String,String>> getGjzxjdc(ZzxmjgForm t, User user) throws Exception{
		return dao.getGjzxjdc(t, user);
	}
	
	/**
	 * @description	： 国家励志奖学金导出
	 * @author 		： lj（1282）
	 * @date 		：2018-4-24 下午04:05:42
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String,String>> getGjlzjdc(ZzxmjgForm t, User user) throws Exception{
		return dao.getGjlzjdc(t, user);
	}
	
	
	/**
	 * 
	 * @描述:TODO(根据guid从xg_xszz_new_zzxmjgb取业务流程id,对应申请表xg_xszz_new_zzxmsqb里的guid)
	 * @作者：陈春雷[工号：1620]
	 * @日期：2018-9-5 下午03:48:56
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ywid
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public String getSqbIdByYwid(String ywid) {
		return dao.getSqbIdByYwid(ywid);
	}
}
