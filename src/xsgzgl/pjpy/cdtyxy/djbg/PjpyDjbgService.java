package xsgzgl.pjpy.cdtyxy.djbg;

import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.studentInfo.service.StudentInfoService;
import xgxt.studentInfo.service.XsxxglService;
import xsgzgl.pjpy.general.djbg.PjpyDjbgModel;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 评奖评优_登记表格_广东建设职业技术学院_Service类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author qlj
 * @version 1.0
 */

public class PjpyDjbgService extends xsgzgl.pjpy.general.djbg.PjpyDjbgService {

	PjpyDjbgDAO dao = new PjpyDjbgDAO();

	/**
	 * 登记表格内容
	 * 
	 * @author qlj
	 */
	public HashMap<String, Object> getDjbgInfo(PjpyDjbgModel model) {

		// 学号
		String xh = model.getXh();
		// 项目名称
		String xmmc = model.getXmmc();
		// 学年
		String xn = model.getXn();
		// 学期
		String xq = model.getXq();

		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("xh", xh);
		map.put("xn", xn);
		map.put("xmmc", xmmc);

		// 设置学生信息
		setXsxxInfo(map);
		// 学生详细信息
		getXsxxInfo(map);
		
		setXszcInfo(map);
		
		getZdcjInfo(map);
		// 获取困难生级别
		HashMap<String,String>knsjbMap=getKnsjb(xh, xn);
		map.putAll(knsjbMap);
		
		HashMap<String,String>xspjInfo=getXspjInfo(xh, xn, xmmc);
		map.putAll(xspjInfo);
		
		HashMap<String,String>xspjcjList=getXspjcjInfo(xh, xn);
		map.putAll(xspjcjList);
		
		String xmjb="";
		if("学院家庭经济困难学生一等奖".equalsIgnoreCase(xmmc) 
				|| "学院家庭经济困难学生二等奖".equalsIgnoreCase(xmmc) 
				|| "学院家庭经济困难学生三等奖".equalsIgnoreCase(xmmc)){
			xmjb=xmmc.substring(10,13);
		}else if("学院学生一等奖学金".equalsIgnoreCase(xmmc) 
				|| "学院学生二等奖学金".equalsIgnoreCase(xmmc) 
				|| "学院学生三等奖学金".equalsIgnoreCase(xmmc)){
			xmjb=xmmc.substring(4,7);
		}
		map.put("sqdj", xmjb);
		
		return map;
	}
	
	/**
	 * 获取打印路径
	 * @author qlj
	 */
	public String getPrintJspForward(PjpyDjbgModel model) throws Exception {
		
		String xmmc=model.getXmmc();
		
		String url ="";
		
		
		if("学院家庭经济困难学生一等奖".equalsIgnoreCase(xmmc) 
				|| "学院家庭经济困难学生二等奖".equalsIgnoreCase(xmmc) 
				|| "学院家庭经济困难学生三等奖".equalsIgnoreCase(xmmc)){
			url="/xsgzgl/pjpy/gdjszyjsxy/knsjxj/knsjxj.jsp";
		}
		
		return url;
	}

	/**
	 * 学生信息
	 * 
	 * @author qlj
	 */
	private void setXsxxInfo(HashMap<String, Object> map) {

		// 学号
		String xh = (String) map.get("xh");
		// 学年
		String xn = (String) map.get("xn");

		HashMap<String, String> xsxx = dao.setXsxxInfo(xh, xn);

		map.putAll(xsxx);
	}
	
	/**
	 * 查询学生详细信息
	 * @param map
	 * @return
	 */
	private void getXsxxInfo(HashMap<String, Object> map){

		XsxxglService xsxxglService=new XsxxglService(); 
		//学号
		String xh = (String) map.get("xh");
		
		// 学生详细信息
		HashMap<String,String>xsxxInfo= xsxxglService.selectStuinfo(xh);
		
		map.putAll(xsxxInfo);
		
	}
	
	/**
	 * 查询学生评奖信息
	 * @param map
	 * @return
	 */
	private HashMap<String,String> getXspjInfo(String xh,String xn,String xmmc){

		// 学生详细信息
		HashMap<String,String>xspjInfo= dao.getXspjInfo(xh,xn,xmmc);
		
		return xspjInfo;
		
	}
	
	/**
	 * 获取学生困难生级别
	 * 
	 * @author qlj
	 */
	public HashMap<String, String> getKnsjb(String xh, String xn ) {
		
		HashMap<String,String>knsjbMap=dao.getKnsjb(xh, xn);

		return knsjbMap;
	}
	// =============================成绩信息 begin ============================
	/**
	 * 学生平均成绩
	 * 
	 * @author qlj
	 */
	public HashMap<String, String> getXspjcjInfo(String xh, String xn ) {
		
		// 学生平均成绩
		HashMap<String,String>xspjInfo= dao.getXspjcjInfo(xh,xn);
		
		return xspjInfo;
		
	}
	

	/**
	 * 获取学生综测信息
	 * 
	 * @author qlj
	 */
	private void setXszcInfo(HashMap<String, Object> map) {

		// 学号
		String xh = (String) map.get("xh");
		// 学年
		String xn = (String) map.get("xn");

		HashMap<String, String> xsxx = dao.getXszcInfo(xh, xn);

		map.putAll(xsxx);
	}
	
	/**
	 * 最低成绩
	 * 
	 * @author qlj
	 */
	public void getZdcjInfo(HashMap<String, Object> map) {
		
		// 学号
		String xh = (String) map.get("xh");
		// 学年
		String xn = (String) map.get("xn");

		HashMap<String, String> zdcj = dao.getZdcjInfo(xh, xn);

		map.putAll(zdcj);
	}
	// =============================成绩信息 end ============================
	
	
}
