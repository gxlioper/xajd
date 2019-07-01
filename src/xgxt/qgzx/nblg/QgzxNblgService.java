package xgxt.qgzx.nblg;

import java.util.HashMap;

import xgxt.utils.GetTime;


/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 宁波理工勤工助学模块Service</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 李容</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2009-01-14</p>
 */
public class QgzxNblgService {
	QgzxNblgDAO dao = new QgzxNblgDAO();
	
	/**
	 * 查询学生岗位申请信息
	 * @param model
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getXsgwsqInfo(QgzxNblgForm model){
		HashMap<String, String> map = new HashMap<String, String>();
		map = dao.getStuInfo(model.getXh());
		map.put("gwmc", dao.getGwmc(model.getXmdm()));
		map.put("sfcjqggw", dao.checkOtherPost(model) == true ? "是" : "否");
		map.put("sfpkf", dao.isKns(model.getXh())==true ? "是" : "否");
		map.put("kcjqgzxsj", model.getKcjqgzxsj());
		map.put("bz", model.getBz());
		map.put("year", GetTime.getNowYear());
		map.put("month", GetTime.getNowMonth());
		map.put("day", GetTime.getNowDay());
		return map;
	}
	
	/**
	 * 查询岗位申请信息
	 * @param model
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getGwsqInfo(QgzxNblgForm model){
		HashMap<String, String> map = new HashMap<String, String>();
		map = dao.getStuInfo(model.getXh());
		map.put("bz", model.getBz());
		map.put("year", GetTime.getNowYear());
		map.put("month", GetTime.getNowMonth());
		map.put("day", GetTime.getNowDay());
		map.put("sqdw", model.getSqdw());
		map.put("lxdh", model.getLxdh());
		map.put("gznr", model.getGznr());
		map.put("gzsj", model.getGzsj());
		map.put("yxrs", model.getXyrs());
		map.put("sqsj", GetTime.getNowTime3());	
		map.put("gwdm", model.getGwdm());
		map.put("myqgzxsj", model.getMyqgzxsj());
		map.put("mssj", model.getMssj());
		map.put("tsyq", model.getTsyq());
		map.put("dwzp", model.getDwzp());
		map.put("xyrs", model.getXyrs());
		map.put("rylsqk", model.getRylsqk());
		map.put("bmmc", dao.getBmmc(model.getBmdm()));
		map.put("fzr", model.getFzr());
		return map;
	}
	
	/**
	 * 导出考勤表
	 * @param model
	 * */
	public void printYkqb_ser(QgzxNblgForm model){
		dao.printYkqb_db(model);
	}
	
	/**
	 * 人员汇总表
	 * @param model
	 * */
	public void printYyhz_ser(QgzxNblgForm model){
		dao.printYyhz_db(model);
	}
	
	/**
	 * 勤工助学月报表
	 * @param model
	 * */
	public void printYbb_ser(QgzxNblgForm model){
		dao.printYbb_db(model);
	}
	
}
