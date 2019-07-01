package xgxt.xszz.comm.xmtj;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.xszz.XszzTyForm;
import xgxt.xszz.comm.XszzCommService;

public class XszzXmtjService extends XszzCommService {

	XszzXmtjDAO dao = new XszzXmtjDAO();

	/**
	 * 获得导出数据表头
	 * 
	 * @param xh
	 * @return
	 */
	public List<HashMap<String, String>> getTopTr(String xxdm, String lx) {
		DAO dao = DAO.getInstance();

		String[] colListCN = null;
		String[] colListEN = null;

		if ("zzhz".equalsIgnoreCase(lx)) {// 中国地大
			colListCN = new String[] { "学号", "姓名", "年级", Base.YXPZXY_KEY+"名称", "专业名称",
					"班级名称", "项目数", "总金额", "统计开始时间", "统计截止时间" };
			colListEN = new String[] { "xh", "xm", "nj", "xymc", "zymc",
					"bjmc", "xmNum", "zje", "kssj", "jssj" };
		}

		return dao.arrayToList(colListEN, colListCN);
	}

	/**
	 * 获得资助汇总列表
	 * 
	 * @author luo
	 * @throws Exception
	 */
	public ArrayList<String[]> getZzhzList(XszzTyForm model) throws Exception {
		return getResultList(dao.getZzhzList(model), model);
	}

	/**
	 * 获得资助汇总(学生个人)
	 * 
	 * @param xh
	 * @return
	 */
	public List<HashMap<String, String>> getXsZzhzList(XszzTyForm model) {

		List<HashMap<String, String>> list = dao.getXsZzhzList(model);

		if (list != null && list.size() > 0) {

			HashMap<String, String> map = new HashMap<String, String>();

			// 总金额
			float zje = 0;

			for (int i = 0; i < list.size(); i++) {
				HashMap<String, String> info = list.get(i);
				String je = info.get("xmzzje");
				zje += Float.parseFloat(je);
			}

			map.put("zje", String.valueOf(zje));

			list.add(map);
		}

		return list;
	}

	/**
	 * 导出数据
	 * 
	 * @author luo
	 * @throws Exception
	 */
	public void expZzhzInfo(XszzTyForm model, OutputStream os) throws Exception {

		// 学校代码
		String xxdm = Base.xxdm;
		// 项目名称
		String title = "资助汇总";

		List<HashMap<String, String>> topTr = getTopTr(xxdm, "zzhz");
		ArrayList<String[]> list = dao.getZzhzList(model);

		expToExcel(title, topTr, list, os);
	}

	/**
	 * 判断是否超过资助上限
	 * 
	 * @author luojw
	 */
	public String isCgZzsx(HashMap<String, String> xmInfo, XszzTyForm model) {

		String message = "";
		// 项目代码
		String xmdm = xmInfo.get("xmdm");
		// 年度
		String nd = Base.currNd;
		// 获得上限
		String hdsx = getOneValue("xszz_xmtjb", "xmtjz", "xmdm||xmtj", xmdm
				+ "hdsx");

		XszzTyForm tjModel = new XszzTyForm();

		if (!Base.isNull(hdsx)) {
			// 申请时间
			String sqsj = xmInfo.get("sqsj");
			// 项目资助金额
			// String xmzzje = xmInfo.get("xmzzje");
			// 学号
			tjModel.setXh(model.getXh());
			// 开始时间
			String kssj = nd + "0101";
			tjModel.setKssj(kssj);
			// 结束时间
			String jssj = nd + "1231";
			tjModel.setJssj(jssj);

			List<HashMap<String, String>> list = dao.getXsZzhzList(tjModel);

			if (list != null && list.size() > 0) {
				// 总金额
				float zje = 0;

				for (int i = 0; i < list.size(); i++) {
					HashMap<String, String> map = list.get(i);
					// 资助金额
					String je = map.get("xmzzje");
					// 申请时间
					String sj = map.get("sqsj");
					// 项目代码
					String dm = map.get("xmdm");

					if (!dm.equalsIgnoreCase(xmdm)) {
						zje += Float.parseFloat(je);
					} else if (dm.equalsIgnoreCase(xmdm)
							&& !sj.equalsIgnoreCase(sqsj)) {
						zje += Float.parseFloat(je);
					}
				}

				if (zje > Float.parseFloat(hdsx)) {
					message = "申请人于" + nd + "年度已经获得资助" + zje + "（元）\n"
							+ "超过本项目的限制，无法审核通过，请确认!";
				}
			}
		}

		return message;
	}

	/**
	 * 判断是否超过资助上限(批量)
	 * 
	 * @author luojw
	 */
	public String isCgZzsxPl(HashMap<String, String> xmInfo, XszzTyForm model) {

		String message = "";
		// 项目代码
		String xmdm = xmInfo.get("xmdm");
		// 年度
		String nd = Base.currNd;
		// 获得上限
		String hdsx = getOneValue("xszz_xmtjb", "xmtjz", "xmdm||xmtj", xmdm
				+ "hdsx");

		// 学号+申请时间
		String shpk = model.getShpk();

		String[] pkValue = null;

		if (!Base.isNull(shpk)) {

			pkValue = new String[shpk.length()];

			String[] arr_shpk = shpk.split("!!@@!!");

			if (arr_shpk != null && arr_shpk.length > 0) {

				pkValue = new String[arr_shpk.length];

				for (int i = 0; i < arr_shpk.length; i++) {
					pkValue[i] = arr_shpk[i];
				}
			}

		}

		XszzTyForm tjModel = new XszzTyForm();
		tjModel.setCheckVal(pkValue);

		if (!Base.isNull(hdsx)) {
			// 开始时间
			String kssj = nd + "0101";
			tjModel.setKssj(kssj);
			// 结束时间
			String jssj = nd + "1231";
			tjModel.setJssj(jssj);

			ArrayList<String[]> list = dao.getZzhzList(tjModel);

			if (list != null && list.size() > 0) {

				for (int i = 0; i < list.size(); i++) {
					String[] rs = list.get(i);
					// 学号
					String xh = rs[0];
					// 姓名
					String xm = rs[1];
					// 除本项目的资助金额汇总
					String je = rs[7];

					if (Float.parseFloat(je) > Float.parseFloat(hdsx)) {
						message = xm + "(" + xh + ")\n于" + nd + "年度已经获得资助" + je
								+ "（元）\n" + "超过本项目的限制，无法审核通过，请确认!";
					}
				}
			}
		}

		return message;
	}
}