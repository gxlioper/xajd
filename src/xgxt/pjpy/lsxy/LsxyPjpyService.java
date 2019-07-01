package xgxt.pjpy.lsxy;

import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import common.Globals;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import xgxt.action.Base;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.pjpy.guizhdx.GuizhdxDAO;
import xgxt.pjpy.ntzydx.PjpyNtzydxDAO;
import xgxt.pjpy.tginterface.PjpyCommonInterface;
import xgxt.pjpy.zjjd.JxjpdxxModel;
import xgxt.pjpy.zjjd.PjpyZjjdDAO;
import xgxt.pjpy.zjkjxy.PjpyZjkjxyDAO;
import xgxt.studentInfo.service.XsxxglService;
import xgxt.utils.ExcelMethods;
import xgxt.utils.String.StringUtils;

public class LsxyPjpyService {
	
	/**
	 * 打印综合素质测评汇总表
	 * @param os
	 * @param model
	 * */
	public void getZhszcphzb(OutputStream os,PjpyLsxyForm model){
		LsxyPjpyDAO dao = new LsxyPjpyDAO();
		WritableWorkbook wwb = null;
		WritableSheet ws = null;
		String bnjzyrs = dao.getBnjzyrs(model.getBjdm());
		
		try {			
			//查询学生综合素质测评汇总信息
			List<HashMap<String, String>> list = dao.selectXszhszcphzxx(model);			
			
			wwb = Workbook.createWorkbook(os);
			ws = wwb.createSheet("数据导出", 0);
			Label titleCell = new Label(0, 0, StandardOperation.getXxmc()+ "学生综合素质测评成绩汇总表");
			WritableCellFormat wcFormat = new WritableCellFormat();
			WritableFont font = new WritableFont(WritableFont.ARIAL, 18);
			font.setBoldStyle(WritableFont.BOLD);
//			font.setUnderlineStyle(UnderlineStyle.SINGLE);
			wcFormat.setFont(font);
			wcFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
			wcFormat.setAlignment(Alignment.CENTRE);
			wcFormat.setBackground(Colour.WHITE);
			
			//表头
			ws.mergeCells(0, 0, 12, 0);
			titleCell.setCellFormat(wcFormat);
			ws.addCell(titleCell);
			//副表头
			wcFormat = new WritableCellFormat();
			font = new WritableFont(WritableFont.ARIAL, 12);
			font.setBoldStyle(WritableFont.BOLD);
			wcFormat.setFont(font);
			wcFormat.setAlignment(Alignment.CENTRE);
			wcFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
			wcFormat.setBackground(Colour.WHITE);
			
			ws.addCell(new Label(0, 1, "",wcFormat));
			ws.addCell(new Label(1, 1, "",wcFormat));
			ws.addCell(new Label(2, 1, "",wcFormat));
			ws.addCell(new Label(3, 1, model.getXn() + "学年",wcFormat));
			ws.addCell(new Label(4, 1, "第",wcFormat));
			ws.addCell(new Label(5, 1, model.getXqmc(),wcFormat));
			ws.addCell(new Label(6, 1, "学期",wcFormat));
			ws.addCell(new Label(7, 1, "",wcFormat));
			ws.addCell(new Label(8, 1, "",wcFormat));
			ws.addCell(new Label(9, 1, "",wcFormat));
			ws.addCell(new Label(10, 1, "",wcFormat));
			ws.addCell(new Label(11, 1, "",wcFormat));
			ws.addCell(new Label(12, 1, "",wcFormat));
			
			//部门
			wcFormat = new WritableCellFormat();
			font = new WritableFont(WritableFont.ARIAL, 12);
			wcFormat.setFont(font);
			wcFormat.setBorder(Border.NONE, BorderLineStyle.NONE,Colour.WHITE);
			wcFormat.setAlignment(Alignment.CENTRE);
			wcFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
			wcFormat.setBackground(Colour.WHITE);
			
			ws.mergeCells(0, 2, 2, 2);
			ws.addCell(new Label(0, 2, "二级学院（盖章）",wcFormat));
			ws.addCell(new Label(3, 2, "",wcFormat));
			ws.addCell(new Label(4, 2, "",wcFormat));
			ws.addCell(new Label(5, 2, "班级：",wcFormat));
			ws.mergeCells(6, 2, 7, 2);
			ws.addCell(new Label(6, 2, model.getBjmc(),wcFormat));
			ws.addCell(new Label(7, 2, "",wcFormat));
			ws.addCell(new Label(8, 2, "",wcFormat));
			ws.addCell(new Label(9, 2,"同年级同专业人数：",wcFormat));
			ws.addCell(new Label(10, 2, "",wcFormat));
			ws.addCell(new Label(11, 2, "",wcFormat));
			ws.addCell(new Label(12, 2,bnjzyrs,wcFormat));
			//表格
			wcFormat = new WritableCellFormat();
			font = new WritableFont(WritableFont.ARIAL, 10);
			wcFormat.setFont(font);
			wcFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
			wcFormat.setAlignment(Alignment.CENTRE);
			wcFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
			
			ws.mergeCells(0, 3, 0, 4);
			ws.addCell(new Label(0, 3,"学号",wcFormat));
			ws.mergeCells(1, 3, 1, 4);
			ws.addCell(new Label(1, 3,"姓名",wcFormat));
			ws.mergeCells(2, 3, 5, 3);
			ws.addCell(new Label(2, 3,"测评内容及比值",wcFormat));
			ws.addCell(new Label(2, 4,"德 20%",wcFormat));
			ws.addCell(new Label(3, 4,"智 65%",wcFormat));
			ws.addCell(new Label(4, 4,"体 5%",wcFormat));
			ws.addCell(new Label(5, 4,"能 10%",wcFormat));
			ws.mergeCells(6, 3, 6, 4);
			ws.addCell(new Label(6, 3,"特别奖励分",wcFormat));
			ws.mergeCells(7, 3, 9, 3);
			ws.addCell(new Label(7, 3,"综合素质名次",wcFormat));
			ws.addCell(new Label(7, 4,"总成绩",wcFormat));
			ws.addCell(new Label(8, 4,"班级",wcFormat));
			ws.addCell(new Label(9, 4,"同年级同专业",wcFormat));
			ws.mergeCells(10, 3, 12, 3);
			ws.addCell(new Label(10, 3,"学习成绩名次",wcFormat));
			ws.addCell(new Label(10, 4,"总成绩",wcFormat));
			ws.addCell(new Label(11, 4,"班级",wcFormat));
			ws.addCell(new Label(12, 4,"同年级同专业",wcFormat));
			
			int row = 5;
			for(int i=0 ; i<list.size(); i++){
				HashMap<String, String> map = list.get(i);
				ws.addCell(new Label(0, row,map.get("xh"),wcFormat));
				ws.addCell(new Label(1, row,map.get("xm"),wcFormat));
				ws.addCell(new Label(2, row,map.get("dcj"),wcFormat));
				ws.addCell(new Label(3, row,map.get("zcj"),wcFormat));
				ws.addCell(new Label(4, row,map.get("tcj"),wcFormat));
				ws.addCell(new Label(5, row,map.get("nlf"),wcFormat));
				ws.addCell(new Label(6, row,map.get("tbjf"),wcFormat));
				ws.addCell(new Label(7, row,map.get("zf"),wcFormat));
				ws.addCell(new Label(8, row,map.get("zfpm"),wcFormat));
				ws.addCell(new Label(9, row,map.get("zyzfpm"),wcFormat));
				ws.addCell(new Label(10, row,map.get("cj"),wcFormat));
				ws.addCell(new Label(11, row,map.get("cjpm"),wcFormat));
				ws.addCell(new Label(12, row,map.get("zycjpm"),wcFormat));
				row++;
			}
			//页脚
			wcFormat = new WritableCellFormat();
			font = new WritableFont(WritableFont.ARIAL, 12);
			wcFormat.setFont(font);
			wcFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
			wcFormat.setAlignment(Alignment.CENTRE);
			wcFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
			wcFormat.setBorder(Border.NONE, BorderLineStyle.NONE,Colour.WHITE);
			wcFormat.setBackground(Colour.WHITE);
			
			ws.mergeCells(0, row, 2, row);
			ws.addCell(new Label(0, row,"班主任：",wcFormat));
			ws.addCell(new Label(3, row,"本表一式三份, (班主任、院系、学生处各一份）",wcFormat));
			ws.mergeCells(3, row, 8, row);
			ws.mergeCells(9, row, 12, row);			
			ws.addCell(new Label(9, row,"学生工作部（处）",wcFormat));			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("数据导出失败!");
		}
		ExcelMethods.submitWritableWorkbookOperations(wwb);// 输出到客户端
	}
	
	/**
	 * 获取个人奖学金评定信息
	 * @param jxjpdModel
	 * @return HashMap<String, String>
	 * @throws Exception
	 */
	public HashMap<String, String> getJxjpdxx(JxjpdxxModel jxjpdModel){
		XsxxglService xsxx = new XsxxglService();
		HashMap<String, String> map = new HashMap<String, String>();
		//学生个人信息
		map.putAll(xsxx.selectStuinfo(jxjpdModel.getXh()));
		
		if(Globals.XXDM_LSXY.equalsIgnoreCase(Base.xxdm)){			
			//丽水学院
			LsxyPjpyDAO dao = new LsxyPjpyDAO();
			map = dao.getJxjpdxx(jxjpdModel,map);
		}else if(Globals.XXDM_ZJKJXY.equalsIgnoreCase(Base.xxdm)){
			//浙江科技学院
			PjpyZjkjxyDAO zjkjDao = new PjpyZjkjxyDAO();
			map = zjkjDao.getJxjpdxx(jxjpdModel,map);
		}else if(Globals.XXDM_NTZYDX.equalsIgnoreCase(Base.xxdm)){
			//南通职业大学
			PjpyNtzydxDAO dao = new PjpyNtzydxDAO(); 			
			map = dao.getJxjpdxx(jxjpdModel,map);
		}		
		
		map.put("save_xh", map.get("xh"));
		
		return map;
	}
	
	/**
	 * 奖学金申请条件判断
	 * @param jxjpdModel
	 * @param xmlx
	 * @return String
	 * */
	public HashMap<String, String> pdStuTjFlag(JxjpdxxModel jxjpdModel , String xmlx){
		LsxyPjpyDAO dao = new LsxyPjpyDAO();
		List<HashMap<String, String>> tjList = dao.getTjList(jxjpdModel,xmlx);
		HashMap<String, String> zcMap = getJxjpdxx(jxjpdModel);
		HashMap<String, String> argMap = new HashMap<String, String>();
		String data = "0"; //条件字段对应综测分里的数据
		String msg = "";//返回消息
		String value = "";
		boolean result = true;
		
		//根据条件结果循环判断是取相应数据		
		for (HashMap<String, String> temp : tjList) {		
			String tjzd = temp.get("tjzd");
			if ("wjcs".equals(tjzd)) {
				PjpyCommonInterface pjInterface = new PjpyCommonInterface();
				msg = "违纪次数不满足条件!";
				argMap.put("xh", jxjpdModel.getXh());
				argMap.put("xn", jxjpdModel.getXn());
				data = pjInterface.queryStuCfxx(argMap).size()+"";
				result = isFhtj(temp.get("tjlx"), temp.get("tjz"), data);//比较数据
			} else if ("dkkccj".equals(tjzd)) {
				msg = "单科成绩不满足条件!";
				argMap.put("xh", jxjpdModel.getXh());
				argMap.put("xn", jxjpdModel.getXn());				
				result = dao.getDkcj(argMap,temp,"");				
			} else if ("zhcpcj".equals(tjzd)) {
				msg = "综合测评成绩不满足条件!";
				data = zcMap.get("zf");
				result = isFhtj(temp.get("tjlx"), temp.get("tjz"), data);//比较数据
			} else if ("zhcppm".equals(tjzd)) {
				msg = "综合素质测评班级排名不满足条件!";
				jxjpdModel.setZfpmbl(temp.get("tjz"));
				zcMap = getJxjpdxx(jxjpdModel);
				data = zcMap.get("zfpm");
				value = zcMap.get("zfblmc");
				result = isFhtj(temp.get("tjlx"), value, data);//比较数据
			} else if ("zhcpzypm".equals(tjzd)) {
				msg = "综合素质测评专业排名不满足条件!!";
				jxjpdModel.setZyzfpmbl(temp.get("tjz"));
				zcMap = getJxjpdxx(jxjpdModel);
				data = zcMap.get("zyzfpm");
				value = zcMap.get("zyzfblmc");
				result = isFhtj(temp.get("tjlx"), value, data);//比较数据
			} else if ("cj".equals(tjzd)) {
				msg = "学习成绩不满足条件!";
				data = zcMap.get("cj");
				result = isFhtj(temp.get("tjlx"), temp.get("tjz"), data);//比较数据
			} else if ("cjpm".equals(tjzd)) {
				msg = "学习成绩班级排名不满足条件!";
				jxjpdModel.setCjpmbl(temp.get("tjz"));
				zcMap = getJxjpdxx(jxjpdModel);
				data = zcMap.get("cjpm");
				value = zcMap.get("cjblmc");
				result = isFhtj(temp.get("tjlx"), value, data);//比较数据
			} else if ("zycjpm".equals(tjzd)) {
				msg = "学习成绩专业排名不满足条件!";
				jxjpdModel.setZycjpmbl(temp.get("tjz"));
				zcMap = getJxjpdxx(jxjpdModel);
				data = zcMap.get("zycjpm");
				value = zcMap.get("zycjblmc");
				result = isFhtj(temp.get("tjlx"), value, data);//比较数据
			} else if("kckcj".equalsIgnoreCase(tjzd)){
				msg = "考查课成绩不满足条件!";
				result = dao.getDkcj(argMap,temp,"考查课");	
			} else if("pjxfjd".equalsIgnoreCase(tjzd)){
				msg = "平均学分绩点不满足条件!";
				data = zcMap.get("pjxfjd");
				result = isFhtj(temp.get("tjlx"), temp.get("tjz"), data);//比较数据
			}
			
			if (!result) {
				break;
			}
		}
		
		HashMap<String, String> tjgl = new HashMap<String, String>();
		
		tjgl.put("message", msg);
		tjgl.put("result", String.valueOf(result));
		
		return tjgl;
	}
	
	/**
	 * 判断现有数据是否满足条件
	 * @param tjlx
	 * @param tjz
	 * @param data
	 * @return
	 */
	public boolean isFhtj(String tjlx,String tjz,String data) {
		
		if (Base.isNull(data)) {
			return false;
		}
		
		double dtjz = Double.valueOf(tjz);
		double ddata = Double.valueOf(data);
		
		if (">".equals(tjlx)) {
			return ddata > dtjz;
		} else if(">=".equals(tjlx)) {
			return ddata >= dtjz;
		} else if("=".equals(tjlx)) {
			return ddata == dtjz;
		} else if("<".equals(tjlx)) {
			return ddata < dtjz;
		} else if("<=".equals(tjlx)) {
			return ddata <= dtjz;
		}
		
		return false;
	}
	
	/**
	 * 奖学金人数限制
	 * */
	public boolean checkJxjRsxz(HashMap<String, String> map){
		JxjpdxxModel jxjpdModel = new JxjpdxxModel();
		LsxyPjpyDAO dao = new LsxyPjpyDAO();
		jxjpdModel.setXh(map.get("xh"));
		jxjpdModel.setXn(map.get("xn"));
		jxjpdModel.setXydm(map.get("xydm"));
		jxjpdModel.setZydm(map.get("zydm"));
		jxjpdModel.setBjdm(map.get("bjdm"));
		jxjpdModel.setNj(map.get("nj"));
		jxjpdModel.setJxjdm(map.get("jxjdm"));
		
		//获取当前学院的人数分配方式
		String fpfs = dao.getJxjrsfpfs(jxjpdModel);
		int fprs = 0;
		int tgrs = 0;
		//查询分配人数
		fprs = dao.getJxjFprs(jxjpdModel, fpfs);
		//查询通过人数
		tgrs = dao.getJxjShtgrs(jxjpdModel,fpfs);
		return fprs>tgrs || fprs == -1;
	}
	
	/**
	 * 荣誉称号人数限制
	 * */
	public boolean checkRychRsxz(HashMap<String, String> map){
		JxjpdxxModel jxjpdModel = new JxjpdxxModel();
		LsxyPjpyDAO dao = new LsxyPjpyDAO();
		jxjpdModel.setXh(map.get("xh"));
		jxjpdModel.setXn(map.get("xn"));
		jxjpdModel.setXydm(map.get("xydm"));
		jxjpdModel.setZydm(map.get("zydm"));
		jxjpdModel.setBjdm(map.get("bjdm"));
		jxjpdModel.setNj(map.get("nj"));
		jxjpdModel.setJxjdm(map.get("jxjdm"));
		
		//获取当前学院的人数分配方式
		String fpfs = dao.getJxjrsfpfs(jxjpdModel);
		int fprs = 0;
		int tgrs = 0;
		//查询分配人数
		fprs = dao.getJxjFprs(jxjpdModel, fpfs);
		//查询通过人数
		tgrs = dao.getRychShtgrs(jxjpdModel,fpfs);
		return fprs>tgrs || fprs == -1;
	}
	
	
	/**
	 * 获取学生奖学金信息表
	 * @param jxjpdModel
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getXsjxjb(JxjpdxxModel jxjpdModel){
		LsxyPjpyDAO dao = new LsxyPjpyDAO();
		return dao.selectXsjxjb(jxjpdModel);
	}
	
	/**
	 * 根据主键获取学生荣誉称号信息表
	 * @param pkValue
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getXsrychbByPk(String pkValue){
		LsxyPjpyDAO dao = new LsxyPjpyDAO();
		return dao.selectXsrychbByPk(pkValue);
	}
	
	
	/**
	 * 获取学生成绩信息表
	 * @param jxjpdModel
	 * @param kclx
	 * @return List<HashMap<String, String>>
	 * */
	public List<HashMap<String, String>> getXscjb(JxjpdxxModel jxjpdModel, String kclx){
		LsxyPjpyDAO dao = new LsxyPjpyDAO();
		return dao.selectXscjb(jxjpdModel, kclx);
	}
	
	/**
	 * 获取学生荣誉称号信息表
	 * @param jxjpdModel
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getXsrychb(JxjpdxxModel jxjpdModel){
		LsxyPjpyDAO dao = new LsxyPjpyDAO();
		return dao.selectXsrychb(jxjpdModel);
	}
	
	/**
	 * 判断是否获取了奖学金
	 * @param jxjpdModel
	 * @return boolean
	 * */
	public boolean getSfhdjxj(JxjpdxxModel jxjpdModel){
		LsxyPjpyDAO dao = new LsxyPjpyDAO();
		int count = dao.selectXsjxjbCount(jxjpdModel);
		return count > 0;
	}
	
	/**
	 * 成绩详细信息查询
	 * @param viewName
	 * @param outputColumn
	 * @param pkValue
	 * */
	public List<String[]> queryXscjOne(String viewName, 
										String[] outputColumn,
										String pkValue){
		LsxyPjpyDAO dao = new LsxyPjpyDAO();
		return dao.selectXscjbOne(viewName,outputColumn,pkValue);
	}
}
