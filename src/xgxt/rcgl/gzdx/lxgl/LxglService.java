package xgxt.rcgl.gzdx.lxgl;

import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import xgxt.action.Base;
import xgxt.form.User;
import xgxt.utils.ExcelMethods;
import xgxt.utils.String.StringUtils;

public class LxglService {
	private LxglDAO lxglDao = new LxglDAO();
	
	/**
	 * 保存留校申请
	 * @param model
	 * @return
	 */
	public boolean saveLxsq(LxglForm model){
		return lxglDao.saveLxsq(model);
	}
	
	/**
	 * 获取学生信息
	 * @param xh
	 * @return
	 */
	public Map<String, String> getStuInfo(String xh){
		return lxglDao.getStuInfo(xh);
	}
	
	public Map<String, Object> getTopMap(String mk){
		String[] outputs = null;
		String[] topTr = null;
		
		if("sh".equalsIgnoreCase(mk)){
			outputs = new String[]{"pkValue", "xh", "xm", "bjmc", "lxsj", "sqsj", "ts", "sh1", "sh2"};
			topTr = new String[]{"主键", "学号", "姓名", "班级", "留校时间", "申请时间", "天数", "学院审核", "学校审核"};
		}else if("cx".equalsIgnoreCase(mk)){
			outputs = new String[]{"pkValue", "xh", "xm", "bjmc", "lxsj", "sqsj", "ts", "sh1", "sh2"};
			topTr = new String[]{"主键", "学号", "姓名", "班级", "留校时间", "申请时间", "天数", "学院审核", "学校审核"};
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("outputs", outputs);
		map.put("topTr", topTr);
		
		return map;
	}
	
	/**
	 * 获取审核信息
	 * @param model
	 * @param userStatus
	 * @return
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws SecurityException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalArgumentException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	public List<String[]> getShList(LxglForm model, User user, String[] outPutList) throws Exception {
		return lxglDao.getShList(model, user, outPutList);
	}
	
	/**
	 * 获取审核信息
	 * @param model
	 * @param userStatus
	 * @return
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws SecurityException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalArgumentException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	public List<String[]> getCxList(LxglForm model, User user, String[] outPutList) throws Exception {
		return lxglDao.getCxList(model, user, outPutList);
	}
	
	/**
	 * 批量审核
	 * @param pkValues
	 * @param user
	 * @param shjg
	 * @return
	 */
	public boolean batchSh(String[] pkValues, User user, String shjg, String yj){
		return lxglDao.batchSh(pkValues, user, shjg, yj);
	}
	
	/**
	 * 获取留校Info
	 * @param pkValue
	 * @return
	 */
	public Map<String, String> getLxInfo(String pkValue){
		return lxglDao.getLxInfo(pkValue);
	}
	
	/**
	 * 批量删除
	 * @param pkValues
	 * @return
	 */
	public boolean batchDel(String[] pkValues){
		return lxglDao.batchDel(pkValues);
	}
	
	/**
	 * 留校修改
	 * @param model
	 * @param pkValue
	 * @return
	 */
	public boolean updateLxgl(LxglForm model,String pkValue){
		return lxglDao.updateLxgl(model, pkValue);
	}
	
	/**
	 * 留校名单导出
	 * @param model
	 * @param os
	 * @throws Exception 
	 */
	public void lxgldc(LxglForm model,User user,OutputStream os) throws Exception{
		String title =Base.currNd+"年寒假留校学生登记表";
		
		List<HashMap<String, String>> list = lxglDao.getExpList(model, user);
		
		String[] colNameCN = new String[]{"学号","姓名","性别","学院","班级","家庭所在地","宿舍","联系方式",
											"是否贫（特）困生","是否吃年夜饭","留校天数","留校开始时间","留校结束时间","离校时间"};

		WritableWorkbook wwb = Workbook.createWorkbook(os);
		WritableSheet ws = wwb.createSheet("留校登记", 0);
		
		WritableCellFormat titleFormat = ExcelMethods.getWcf(WritableFont.ARIAL, 14, false,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, Border.NONE); // 构造单元格格式
		
		WritableCellFormat cellFormat = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, Border.ALL); // 构造单元格格式
		
//		WritableCellFormat headFormat = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false,
//				Alignment.LEFT, VerticalAlignment.CENTRE, true, Border.NONE); // 构造单元格格式
		
		ws.mergeCells(0, 0, colNameCN.length-1, 0);
		ws.addCell(new Label(0,0,title, titleFormat));
		
		try {
			for (int m = 0; m < colNameCN.length; m++) {
				ws.addCell(new Label(m, 1, colNameCN[m], cellFormat));
			}
			
			for (int i = 0; i < list.size(); i++) {
				Map<String, String> map = list.get(i);
				ws.addCell(new Label(0, i + 2, map.get("xh"), cellFormat));
				ws.addCell(new Label(1, i + 2, map.get("xm"), cellFormat));
				ws.addCell(new Label(2, i + 2, map.get("xb"), cellFormat));
				ws.addCell(new Label(3, i + 2, map.get("xymc"), cellFormat));
				ws.addCell(new Label(4, i + 2, map.get("bjmc"), cellFormat));
				ws.addCell(new Label(5, i + 2, map.get("jtszd"), cellFormat));
				ws.addCell(new Label(6, i + 2, map.get("qsh"), cellFormat));
				
				String lxdh = map.get("lxdh");
				String sjhm = map.get("sjhm");
				
				String lxfs = "";
				if(StringUtils.isNotNull(lxdh) && StringUtils.isNotNull(sjhm)){
					lxfs += lxdh + "/" + sjhm;
				}else{
					lxfs = StringUtils.isNotNull(sjhm) ? sjhm : lxdh;
				}
				
				ws.addCell(new Label(7, i + 2, lxfs, cellFormat));
				ws.addCell(new Label(8, i + 2, "", cellFormat));
				ws.addCell(new Label(9, i + 2, map.get("sfnyf"), cellFormat));
				ws.addCell(new Label(10, i + 2, map.get("ts"), cellFormat));
				ws.addCell(new Label(11, i + 2, map.get("kssj"), cellFormat));
				ws.addCell(new Label(12, i + 2, map.get("jssj"), cellFormat));
				ws.addCell(new Label(13, i + 2, "", cellFormat));
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("数据导出失败!");
		} finally {
			wwb.write();
			wwb.close();
		}
	}
	
	/**
	 * 获取学生留校申请信息
	 * @param xh
	 * @return
	 * @author honglin
	 * @date 2013-01-24
	 */
	public Map<String, String> getXslxInfo(String xh){
		return lxglDao.getXslxInfo(xh);
	}
	
	/**
	 * 修改请假申请管理
	 * @param model
	 * @param pkValue
	 * @return
	 * @author honglin
	 * @date 2013-01-25
	 */
	public boolean updateXslxgl(LxglForm model,String pkValue){
		return lxglDao.updateXslxgl(model, pkValue);
	}
}
