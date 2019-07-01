package xgxt.pjpy.ghxy.ryjf.grryf;

import java.util.List;
import java.util.Map;

import xgxt.DAO.DAO;
import xgxt.action.base.BaseDAO;
import xgxt.form.SaveForm;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.String.StringUtils;

/**
 * Title: 学生工作管理系统 Description:硅湖学院个人荣誉加分Action Copyright: Copyright (c) 2010
 * Company: zfsoft Author: sjf Version: 1.0 Time: 2010-8-05
 */
public class GrryjfGhxyService {

	/**
	 * 根据学号获得学生信息
	 * 
	 * @param xh
	 * @return
	 */
	public Map<String, String> getStuInfo(String xh) {
		String[] colList = new String[] { "xh", "xm", "xb", "xymc", "zymc",
				"bjmc", "nj", "lxdh" };

		Map<String, String> map = CommonQueryDAO.commonQueryOne("view_xsjbxx",
				colList, "xh", xh);
		return map;
	}

	/**
	 * 根据学号获得个人加分信息
	 * 
	 * @param xh
	 * @param xn
	 * @return
	 */
	public Map<String, String> getGrjfInfo(String pkValue) {
		String[] output = new String[] { "xh", "xn", "xq","xqmc", "fdysh", "njbzrsh",
				"xxsh", "fdyshsj", "njbzrshsj", "xxshsj" };
		String tableName = "view_ghxy_grjf";
		String pk = "xh||xn||xq";

		Map<String, String> map = CommonQueryDAO.commonQueryOne(tableName,
				output, pk, pkValue);

		return map;
	}

	public List<String[]> getGrjfInfoForStu(String xh, String[] output) {
		String query = " where xh=?";
		return CommonQueryDAO.commonQueryNotFy("view_ghxy_grjf", query,
				new String[] { xh }, output, "");
	}

	/**
	 * 添加申请信息
	 * 
	 * @param form
	 * @return
	 * @throws Exception
	 */
	public boolean insertRecord(RyjfModel model) {
		return insertGrjfsqb(model) && insertGrjfb(model);
	}

	/**
	 * 更新记录
	 * 
	 * @param model
	 * @return
	 */
	public boolean updateRecord(RyjfModel model) {
		return insertGrjfsqb(model) && updateGrjfb(model);
	}

	/**
	 * 删除记录
	 * 
	 * @param model
	 * @return
	 */
	public boolean delRecord(String[] pkValue) {
		return delGrjfsqb(pkValue) && delGrjfb(pkValue);
	}

	/**
	 * 插入个人加分申请表
	 * 
	 * @param form
	 * @return
	 * @throws Exception
	 */
	public boolean insertGrjfsqb(RyjfModel model) {
		boolean flag = false;
		SaveForm saveForm = new SaveForm();

		String pk = "xh||xn||xq";

		String pkValue = new StringBuilder().append(model.getXh()).append(
				model.getXn()).append(model.getXq()).toString();

		String[] pkValues = new String[] { pkValue };
		String[] onezd = new String[] { "xh", "xn", "xq" };
		String[] arrzd = new String[] { "hjsj", "hjmc", "hjsy", "grhjf" };

		saveForm.setPk(pk);
		saveForm.setPkValue(pkValues);
		saveForm.setTableName("ghxy_grjfsqb");
		saveForm.setOnezd(onezd);
		saveForm.setArrzd(arrzd);

		DAO dao = DAO.getInstance();
		try {
			dao.saveData(saveForm, model);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return flag;
	}

	/**
	 * 插入个人加分表
	 * 
	 * @param form
	 * @return
	 * @throws Exception
	 */
	public boolean insertGrjfb(RyjfModel model) {
		boolean flag = false;

		DAO dao = DAO.getInstance();

		String insertSql = "insert into ghxy_grjfb(ryjf,xh,xn,xq) values(?,?,?,?)";
		String updateSql = "update ghxy_grjfb set ryjf=? where xh=? and xn=? and xq=?";

		String[] grhjf = model.getGrhjf();
		int ryjf = getRyjf(grhjf);

		String[] input = new String[] { String.valueOf(ryjf), model.getXh(),
				model.getXn(), model.getXq() };

		BaseDAO baseDao = new BaseDAO();
		String pkValue = new StringBuilder().append(model.getXh()).append(
				model.getXn()).append(model.getXq()).toString();

		try {
			// 如果在数据库中存在，执行update更新荣誉加分
			if (baseDao.checkExists("ghxy_grjfb", "xh||xn||xq", pkValue)) {
				dao.runUpdate(updateSql, input);
				flag = true;
			} else {
				dao.insert(insertSql, input);
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 * 批量删除个人加分申请表中的信息
	 * 
	 * @param pkValue
	 * @return
	 */
	public boolean delGrjfsqb(String[] pkValue) {
		SaveForm saveForm = new SaveForm();
		saveForm.setPk("xh||xn||xq");
		saveForm.setPkValue(pkValue);
		saveForm.setTableName("ghxy_grjfsqb");
		DAO dao = new DAO();
		boolean flag = false;

		try {
			dao.delDate(saveForm, null);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return flag;
	}

	/**
	 * 更新个人加分表
	 * 
	 * @param model
	 * @return
	 */
	public boolean updateGrjfb(RyjfModel model) {
		boolean flag = false;
		DAO dao = DAO.getInstance();
		String updateSql = "update ghxy_grjfb set fdysh=?,njbzrsh=?,xxsh=?,fdyshsj=?,njbzrshsj=?,"
				+ "xxshsj=?,ryjf=? where xh=? and xn=? and xq=?";

		String[] grhjf = model.getGrhjf();
		int ryjf = getRyjf(grhjf);

		String[] input = new String[] { model.getFdysh(), model.getNjbzrsh(),
				model.getXxsh(), model.getFdyshsj(), model.getNjbzrshsj(),
				model.getXxshsj(), String.valueOf(ryjf), model.getXh(),
				model.getXn(), model.getXq() };
		try {
			dao.runUpdate(updateSql, input);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return flag;
	}

	/**
	 * 删除个人加分表中的信息
	 * 
	 * @param pkValue
	 * @return
	 */
	public boolean delGrjfb(String[] pkValue) {
		SaveForm saveForm = new SaveForm();
		saveForm.setPk("xh||xn||xq");
		saveForm.setPkValue(pkValue);
		saveForm.setTableName("ghxy_grjfb");
		DAO dao = new DAO();
		boolean flag = false;

		try {
			dao.delDate(saveForm, null);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return flag;
	}

	/**
	 * 验证申请学生是否在审核
	 * 
	 * @param xh
	 * @param xn
	 * @param xq
	 * @return
	 */
	public boolean isAuditing(String xh, String xn, String xq) {
		boolean flag = false;
		StringBuilder builder = new StringBuilder();
		builder.append(xh).append(xn).append(xq);

		Map<String, String> map = CommonQueryDAO.commonQueryOne("GHXY_GRJFB",
				new String[] { "fdysh" }, "xh||xn||xq", builder.toString());

		if (map != null && "通过".equalsIgnoreCase(map.get("fdysh"))) {
			flag = true;
		}

		return flag;
	}

	/**
	 * 获得荣誉加分
	 * 
	 * @return
	 */
	private int getRyjf(String[] grhjf) {
		int ryjf = 0;
		for (int i = 0; i < grhjf.length; i++) {
			if (StringUtils.isNotNull(grhjf[i])) {
				ryjf += Integer.parseInt(grhjf[i]);
			}
		}

		ryjf = ryjf > 100 ? 100 : ryjf;

		return ryjf;

	}
}
