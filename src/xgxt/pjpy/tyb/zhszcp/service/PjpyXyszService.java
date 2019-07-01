package xgxt.pjpy.tyb.zhszcp.service;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.VerticalAlignment;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import xgxt.DAO.DAO;
import xgxt.DAO.ExcelMB;
import xgxt.form.User;
import xgxt.pjpy.tyb.zhszcp.action.PjpyXyszActionForm;
import xgxt.pjpy.tyb.zhszcp.dao.PjpyXyszDAO;
import xgxt.pjpy.tyb.zhszcp.dao.PjpyZctjszDAO;
import xgxt.pjpy.tyb.zhszcp.model.PjpyZctjszModel;
import xgxt.pjpy.tyb.zhszcp.model.PjpyZhszcpModel;
import xgxt.utils.ExcelMethods;
import xgxt.utils.String.StringUtils;

public class PjpyXyszService {
	PjpyXyszDAO dao = new PjpyXyszDAO();
	
	/**
	 * 获取奖学金金额设置表头
	 * @return List<HashMap<String, String>>
	 * */
	public List<HashMap<String, String>> queryJxjjeszTitle(){
		String[] en = {"pk","xn","xymc","nj","jxjje"};
		String[] cn = {"主键","学年","学院名称","年级","奖学金金额"};
		return dao.arrayToList(en, cn);
	}
	
	/**
	 * 查询奖学金金额设置信息
	 * @param model
	 * @param user
	 * @return List<String[]>
	 * */
	public List<String[]> queryJxjjesz(PjpyXyszActionForm model, User user){
		String[] output = {"pk","xn","xymc","nj","jxjje"};
		return dao.selectJxjjesz(model, output, user);
	}
	
	/**
	 * 奖学金金额设置信息保存
	 * @param model
	 * @param user
	 * @return boolean
	 * */
	public boolean saveJxjjesz(PjpyXyszActionForm model,User user){
		return dao.saveJxjjesz(model,user);
	}
}
