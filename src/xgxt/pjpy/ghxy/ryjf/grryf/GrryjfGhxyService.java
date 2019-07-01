package xgxt.pjpy.ghxy.ryjf.grryf;

import java.util.List;
import java.util.Map;

import xgxt.DAO.DAO;
import xgxt.action.base.BaseDAO;
import xgxt.form.SaveForm;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.String.StringUtils;

/**
 * Title: ѧ����������ϵͳ Description:���ѧԺ���������ӷ�Action Copyright: Copyright (c) 2010
 * Company: zfsoft Author: sjf Version: 1.0 Time: 2010-8-05
 */
public class GrryjfGhxyService {

	/**
	 * ����ѧ�Ż��ѧ����Ϣ
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
	 * ����ѧ�Ż�ø��˼ӷ���Ϣ
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
	 * ���������Ϣ
	 * 
	 * @param form
	 * @return
	 * @throws Exception
	 */
	public boolean insertRecord(RyjfModel model) {
		return insertGrjfsqb(model) && insertGrjfb(model);
	}

	/**
	 * ���¼�¼
	 * 
	 * @param model
	 * @return
	 */
	public boolean updateRecord(RyjfModel model) {
		return insertGrjfsqb(model) && updateGrjfb(model);
	}

	/**
	 * ɾ����¼
	 * 
	 * @param model
	 * @return
	 */
	public boolean delRecord(String[] pkValue) {
		return delGrjfsqb(pkValue) && delGrjfb(pkValue);
	}

	/**
	 * ������˼ӷ������
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
	 * ������˼ӷֱ�
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
			// ��������ݿ��д��ڣ�ִ��update���������ӷ�
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
	 * ����ɾ�����˼ӷ�������е���Ϣ
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
	 * ���¸��˼ӷֱ�
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
	 * ɾ�����˼ӷֱ��е���Ϣ
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
	 * ��֤����ѧ���Ƿ������
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

		if (map != null && "ͨ��".equalsIgnoreCase(map.get("fdysh"))) {
			flag = true;
		}

		return flag;
	}

	/**
	 * ��������ӷ�
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
