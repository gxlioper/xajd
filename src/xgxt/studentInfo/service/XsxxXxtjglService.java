package xgxt.studentInfo.service;

import java.util.HashMap;
import java.util.List;

import xgxt.action.Base;
import xgxt.studentInfo.dao.XsxxXxtjglDAO;

public class XsxxXxtjglService {
	XsxxXxtjglDAO dao = new XsxxXxtjglDAO();
	
	private static String[] XYXXSXXTJEN = {"xymc","zrs","boyrs","girlrs","zxrs","zxboyrs","zxgirlrs"};
	private static String[] XYXXSXXTJCN = {Base.YXPZXY_KEY+"名称",Base.YXPZXY_KEY+"总人数",Base.YXPZXY_KEY+"男生数",Base.YXPZXY_KEY+"女生数",Base.YXPZXY_KEY+"在校人数",Base.YXPZXY_KEY+"在校男生数",Base.YXPZXY_KEY+"在校女生数"};
	
	/**
	 * 获取学院学生信息统计表头
	 * @return List<HashMap<String, String>> 
	 * */
	public List<HashMap<String, String>> getXyXsxxtjTopTr(){		
		return dao.arrayToList(XYXXSXXTJEN, XYXXSXXTJCN);
	}
	
	/**
	 * 学院学生信息统计查询
	 * @return List<String[]>
	 * */
	public List<String[]> queryXyXsxxtj(){		
		return dao.selectXyXsxxtj(XYXXSXXTJEN,5);
	}
	
	/**
	 * 全部学院学生信息统计查询
	 * */
	public List<String[]> queryAllXyXsxxtj(){		
		return dao.selectXyXsxxtj(XYXXSXXTJEN,-1);
	}
}
