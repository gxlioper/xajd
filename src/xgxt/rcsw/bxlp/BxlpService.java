package xgxt.rcsw.bxlp;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.form.User;
import xgxt.rcsw.zzlgdx.RcswZzlgdxService;
import xgxt.szdw.utils.ModelToStrings;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.MakeQuery;

/**
 * <p>
 * Title: 学生工作管理系统
 * </p>
 * <p>
 * Description: 保险、理赔Service
 * </p>
 * <p>
 * Copyright: Copyright (c) 2010
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * <p>
 * Author: 屈朋辉
 * </p>
 * <p>
 * Version: 1.0
 * </p>
 * <p>
 * Time: 2010-06-03
 * </p>
 */
public class BxlpService extends RcswZzlgdxService {

	BxlpDAO dao = new BxlpDAO();

	/**
	 * 获取当前系统时间yyyymmdd
	 * 
	 * @return
	 */
	public String getNow() {

		return dao.getNow();
	}

	/**
	 * 保存数据含自定义字段数据
	 * 
	 * @return
	 */
	public boolean saveData(String realTable, String[] colList, String pkV,
			BxlpModel model, HttpServletRequest request) throws Exception {

		String[] inputList = ModelToStrings.modelToStrings(colList, model);
		boolean updata = StandardOperation.insertNoLog(realTable, colList,
				inputList);

		if (updata) {
			updata = dao.updataZdyDdData(realTable, pkV, request);
		}
		return updata;
	}

	/**
	 * 查询含自定义字段数据
	 * 
	 * @param tableName
	 * @param model
	 * @param colList
	 * @param zdyCol
	 * @param realTable
	 * @param pk
	 * @return
	 * @throws IllegalArgumentException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	public ArrayList<String[]> getDataList(String tableName, BxlpModel model,
			String[] colList, String[] zdyCol, String realTable, String[] pk)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		return dao.getZdyQueryList(tableName, model, colList, zdyCol,
				realTable, pk);
	}

	/**
	 * 更新含自定义字段数据
	 * 
	 * @param realTable
	 * @param pk
	 * @param model
	 * @param pkValue
	 * @param colList
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean updateData(String realTable, String pk, BxlpModel model,
			String pkValue, String[] colList, HttpServletRequest request)
			throws Exception {

		String[] inputList = ModelToStrings.modelToStrings(colList, model);
		boolean updata = StandardOperation.updateNolog(realTable, colList,
				inputList, pk, pkValue);

		if (updata) {
			updata = dao.updataZdyDdData(realTable, pkValue, request);
		}
		return updata;
	}

	/**
	 * 获取保险、理赔列表
	 * 
	 * @param tableName
	 * @param model
	 * @param colList
	 * @return
	 * @throws IllegalArgumentException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	public ArrayList<String[]> getBxlpList(String tableName, BxlpModel model,
			String[] colList) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		String[] queryList = new String[] { "xb", "nj", "xydm", "zydm", "bjdm",
				"nd" };
		String[] queryLikeList = new String[] { "xh", "xm", "sfzh" };
		MakeQuery myQuery = new MakeQuery();
		myQuery.makeQuery(queryList, queryLikeList, model);
		String query = myQuery.getQueryString();

		return CommonQueryDAO.commonQuery(tableName, query, myQuery
				.getInputList(), colList, "", model);
	}

	/**
	 * 获取单条信息
	 * 
	 * @param tableName
	 * @param pk
	 * @param pkValue
	 * @param colList
	 * @return
	 */
	public HashMap<String, String> getInfo(String tableName, String pk,
			String pkValue, String[] colList) {
		return dao.getInfo(tableName, pk, pkValue, colList);
	}

	/**
	 * 下拉列表
	 * 
	 * @param flg
	 * @return
	 */
	public List<HashMap<String, String>> getList(String flg) {

		return dao.getSelectList(flg);
	}

	/**
	 * 获取保险审核
	 * 
	 * @param tableName
	 * @param model
	 * @param colList
	 * @return
	 * @throws IllegalArgumentException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	public ArrayList<String[]> getBxshList(BxlpForm model, User user)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		String userType = user.getUserType();
		String isFdy = user.getFdyQx();
		String[] colList = null;
		if ("true".equalsIgnoreCase(isFdy)) {

			colList = new String[] { "pkValue","dis", "xh", "xm", "xb", "sfzh", "xn",
					"nd", "nj", "xymc", "zymc", "bjmc", "rq", "sfby", "je",
					"nx", "fdysh", "xysh", "xxsh" };

		} else if ("xy".equalsIgnoreCase(userType)) {

			colList = new String[] { "pkValue","dis", "xh", "xm", "xb", "sfzh", "xn",
					"nd", "nj", "xymc", "zymc", "bjmc", "rq", "sfby", "je",
					"nx", "xysh", "xxsh" };
		} else if ("xx".equalsIgnoreCase(userType)
				|| "admin".equalsIgnoreCase(userType)) {

			colList = new String[] { "pkValue","dis", "xh", "xm", "xb", "sfzh", "xn",
					"nd", "nj", "xymc", "zymc", "bjmc", "rq", "sfby", "je",
					"nx", "xxsh" };
		}

		return dao.getBxshList(model, user, colList);
	}

	public List<HashMap<String,String>> getTopTr(User user)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		String userType = user.getUserType();
		String isFdy = user.getFdyQx();
		String[] en = null;
		String[] cn = null;
		if ("true".equalsIgnoreCase(isFdy)) {

			en = new String[] { "pkValue", "xh", "xm", "xb", "sfzh", "xn",
					"nd", "nj", "xymc", "zymc", "bjmc", "rq", "sfby", "je",
					"nx", "fdysh", "xysh", "xxsh" };
			cn = new String[] { "主键", "学号", "姓名", "性别", "身份证号", "学年",
					"年度", "年级", "学院", "专业", "班级", "日期", "是否毕业", "金额",
					"年限", "辅导员审核", "学院审核", "学校审核" };
		} else if ("xy".equalsIgnoreCase(userType)) {

			en = new String[] { "pkValue", "xh", "xm", "xb", "sfzh", "xn",
					"nd", "nj", "xymc", "zymc", "bjmc", "rq", "sfby", "je",
					"nx", "xysh", "xxsh" };
			cn = new String[] { "主键", "学号", "姓名", "性别", "身份证号", "学年",
					"年度", "年级", "学院", "专业", "班级", "日期", "是否毕业", "金额",
					"年限", "学院审核", "学校审核" };
		} else if ("xx".equalsIgnoreCase(userType)
				|| "admin".equalsIgnoreCase(userType)) {

			en = new String[] { "pkValue", "xh", "xm", "xb", "sfzh", "xn",
					"nd", "nj", "xymc", "zymc", "bjmc", "rq", "sfby", "je",
					"nx","xxsh" };
			cn = new String[] { "主键", "学号", "姓名", "性别", "身份证号", "学年",
					"年度", "年级", "学院", "专业", "班级", "日期", "是否毕业", "金额",
					"年限", "学校审核" };
		}
		DAO dao=DAO.getInstance();
		return dao.arrayToList(en, cn);
	}
	
	public boolean savePlsh(BxlpForm myForm,User user) throws Exception{
		
		return dao.savePlsh(myForm, user);
	}

}
