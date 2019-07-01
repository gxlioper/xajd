package xsgzgl.pjpy.szgyyq.mypj.pjxm;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.CommService;
import xgxt.comm.SearchRsModel;
import xgxt.form.SaveForm;
import xgxt.form.User;
import xgxt.utils.CommonQueryDAO;
import xsgzgl.pjpy.szgyyq.model.DshdModel;
import xsgzgl.pjpy.szgyyq.mypj.stu.PjpyStuDAO;
import xsgzgl.pjpy.szgyyq.mypj.stu.PjpyStuForm;
import xsgzgl.pjpy.szgyyq.mypj.tea.PjpyTeaForm;
import xsgzgl.pjpy.szgyyq.mypj.tea.PjpyTeaService;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 评奖评优_苏州工业园区_我的评奖(学生)_评奖项目_读书活动_Service类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author 伟大的骆
 * @version 1.0
 */

public class DshdService extends CommService {

	DshdDAO dao = new DshdDAO();

	/**
	 * 保存读书活动申请分
	 * 
	 * @author 伟大的骆
	 */
	public Boolean saveDshdSqf(PjpyStuForm model, User user) {

		DshdModel dshdModel = model.getDshdModel();

		// 学号
		String xh = dshdModel.getXh();
		// 学年
		String xn = dshdModel.getXn();
		// 学期
		String xq = dshdModel.getXq();
		// 读书名称
		String[] dsmc = dshdModel.getDsmc();
		// 读书日期
		String[] dsrq = dshdModel.getDsrq();
		// 读书心得
		String[] dsxd = dshdModel.getDsxd();
		// 是否获奖
		String[] sfhj = dshdModel.getSfhj();
		// 申请分
		String[] sqf = dshdModel.getSqf();

		String[] pkValue = null;

		// 构造参数
		List<String[]> params = new ArrayList<String[]>();
		if (dsmc != null && dsmc.length > 0) {

			pkValue = new String[dsmc.length];

			for (int i = 0; i < dsmc.length; i++) {

				String[] values = new String[] { xn, xq, xh,
						unicode2Gbk(dsmc[i]), dsrq[i], unicode2Gbk(dsxd[i]),
						unicode2Gbk(sfhj[i]), sqf[i] };

				params.add(values);
				pkValue[i] = xn + xq + xh + unicode2Gbk(dsmc[i]) + dsrq[i];
			}
		}

		boolean flag = dao.delDshdSqf(model, pkValue, user);

		if (flag) {
			flag = dao.saveDshdSqf(model, params, user);
		}

		return flag;
	}

	/**
	 * 删除读书活动申请分
	 * 
	 * @author 伟大的骆
	 */
	public Boolean delDshdSqf(PjpyStuForm model, User user) {

		DshdModel dshdModel = model.getDshdModel();

		// 学号
		String xh = dshdModel.getXh();
		// 学年
		String xn = dshdModel.getXn();
		// 学期
		String xq = dshdModel.getXq();
		// 读书名称
		String[] dsmc = dshdModel.getDsmc();
		// 读书日期
		String[] dsrq = dshdModel.getDsrq();

		String pkValue = xn + xq + xh + unicode2Gbk(dsmc[0]) + dsrq[0];

		boolean flag = dao.delDshdSqf(model, new String[] { pkValue }, user);

		return flag;
	}

	/**
	 * 获得申请信息列表
	 * 
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getDshdList(PjpyStuForm model) {
		return dao.getDshdList(model);
	}

	/**
	 * 获得特殊Html
	 * 
	 * @author 伟大的骆
	 */
	public String getDshdHtml(SearchRsModel rsModel, PjpyStuForm model,
			ArrayList<String[]> rsArrList, User user) {

		// 操作项目
		String czxm = model.getCzxm();
		// IE版本
		String ie = rsModel.getIe();
		// 学年
		String xn = model.getXn();
		// 学期
		String xq = model.getXq();
		// 项目
		String xmdm = model.getXmdm();
		// 用户类型
		String yhlx = model.getYhlx();

		StringBuilder spHtml = new StringBuilder();

		if (rsArrList != null && rsArrList.size() > 0) {

			for (int i = 0; i < rsArrList.size(); i++) {

				String[] rs = rsArrList.get(i);

				String xh = rs[0];

				String userName = user.getUserName();

				// 本人颜色
				String color = "";
				// 是否本人
				boolean flag = false;
				if (xh.equalsIgnoreCase(userName)) {
					flag = true;
					color = "pink";
				} else if ("bzr".equalsIgnoreCase(yhlx)
						|| "xy".equalsIgnoreCase(yhlx)
						|| "xx".equalsIgnoreCase(yhlx)) {
					flag = true;
				}

				spHtml.append("<tr onclick=\"rowOnClick(this);\" ondblclick=\"\">");

				for (int j = 0; j < rs.length - 6; j++) {
					spHtml
							.append("<td align=\"left\" nowrap=\"nowrap\" style=\"width:20%;background-color:"
									+ color + "\" ");

					// IE8
					if ("5.8".equalsIgnoreCase(ie)) {
						spHtml.append("height=\"28.5px\"");
					} else {
						spHtml.append("height=\"29px\"");
					}
					spHtml.append(">");

					spHtml.append(rs[j]);
					spHtml.append("</td>");
				}

				// 操作
				String cz = rs[rs.length - 1];
				// 投诉内容
				String tsnr = rs[rs.length - 2];
				// 处理意见
				String clyj = rs[rs.length - 3];
				// 处理人姓名
				String clrxm = rs[rs.length - 4];

				spHtml.append("<td align=\"left\" nowrap=\"nowrap\" style=\"width:20%;background-color:"
								+ color + "\" > ");

				if (flag) {
					spHtml.append("<a href\"#\" onclick=\"showSqxxDetail('"
									+ xn
									+ "','"
									+ xq
									+ "','"
									+ xh
									+ "','"
									+ xmdm
									+ "','view');return false;\" style=\"cursor:hand\">");
					spHtml.append("<font color=\"blue\">查看</font>");
					spHtml.append("</a>");
				} else {

					if ("bz".equalsIgnoreCase(yhlx)) {
						spHtml.append("不可查看");
					} else {
						spHtml.append("<a href\"#\" onclick=\"showXstsDiv('"
								+ cz + "','" + xh + "','" + tsnr + "','"
								+ clrxm + "','" + clyj
								+ "');return false;\" style=\"cursor:hand\">");
						spHtml.append("<font color=\"blue\">" + cz + "</font>");
						spHtml.append("</a>");
					}
				}
				spHtml.append("</td>");

				spHtml.append("</tr>");
			}
		}

		return spHtml.toString();
	}

	/**
	 * 获得读书活动信息列表
	 * 
	 * @author 伟大的骆
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public ArrayList<String[]> getDshdList(PjpyStuForm model, User user)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		return dao.getDshdList(model, user);
	}

	/**
	 * 获得读书活动审核信息列表
	 * 
	 * @author 伟大的骆
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public ArrayList<String[]> getDshdShList(PjpyTeaForm model, User user)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		PjpyTeaService service = new PjpyTeaService();
		
		// 审核列表
		ArrayList<String[]> dshdList = dao.getDshdShList(model, user);
		// 审核状态列表
		List<HashMap<String, String>> shztList = dao.getDshdShList(dshdList,
				model, user);
		// 学生投诉信息
		List<HashMap<String, String>> xstsList = service.getXstsInfo(dshdList,
				model, user);
		
		ArrayList<String[]> list = new ArrayList<String[]>();

		String yhlx = model.getYhlx();
		String xmdm = model.getXmdm();

		if (dshdList != null && dshdList.size() > 0) {
			for (int i = 0; i < dshdList.size(); i++) {
				String[] rs = dshdList.get(i);
				String xh = rs[0];
				String xm = rs[1];
				String bjmc = rs[2];
				String sqf = fillZero(rs[3]);
				String bzrshf = fillZero(rs[4]);
				String xyshf = fillZero(rs[5]);
				String xxshf = fillZero(rs[6]);

				// 被投诉人
				String btsr = "";
				// 投诉人
				String tsr = "";
				// 投诉内容
				String tsnr = "";
				// 投诉时间
				String tssj = "";

				//处理投诉信息
				if (xstsList != null && xstsList.size() > 0) {
					for (int j = 0; j < xstsList.size(); j++) {
						HashMap<String, String> xstsInfo = xstsList.get(j);			
						if(xh.equalsIgnoreCase(xstsInfo.get("btsr"))){
							btsr = xstsInfo.get("btsr");
							tsr = xstsInfo.get("tsr");
							tsnr = xstsInfo.get("tsnr");
							tssj = xstsInfo.get("tssj");
						}
					}
				}
				
				// 班主任或班长
				if ("bz".equalsIgnoreCase(yhlx) || "bzr".equalsIgnoreCase(yhlx)) {

					// 审核状态
					String shzt = service.getShztInfo(shztList, xh, yhlx,xmdm);

					String[] value = new String[] { xh, xm, bjmc, sqf, bzrshf,
							shzt };
					list.add(value);
				} else if ("xy".equalsIgnoreCase(yhlx)) {// 学院
					// 审核状态
					String shzt =service.getShztInfo(shztList, xh, yhlx,xmdm);

					
					String[] value = new String[] { xh, xm, bjmc, sqf, bzrshf,
							xyshf, shzt, btsr, tsr, tsnr, tssj };
					
					list.add(value);
				} else if ("xx".equalsIgnoreCase(yhlx)) {// 学校
					// 审核状态
					String shzt = service.getShztInfo(shztList, xh, yhlx,xmdm);

					String[] value = new String[] { xh, xm, bjmc, sqf, bzrshf,
							xyshf, xxshf, shzt, btsr, tsr, tsnr, tssj };

					list.add(value);
				}
			}
		}

		return list;
	}

	/**
	 * 获得读书活动信息
	 * 
	 * @author 伟大的骆
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public List<HashMap<String, String>> getDshdInfo(PjpyStuForm model,
			User user) {

		String sqid = model.getSqid();

		String[] colList = new String[] { "dsmc", "dsrq", "dsxd", "sfhj", "sqf" };
		HashMap<String, String> map = CommonQueryDAO.commonQueryOne(
				"szyq_dshdjzb", colList, "id", sqid);

		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		list.add(map);

		return list;
	}
	
	/**
	 * 修改读书活动信息
	 * 
	 * @author 伟大的骆
	 */
	public Boolean editDshdInfo(PjpyStuForm model, User user) {
		
		boolean flag = false;
		
		try {
			flag = dao.editDshdInfo(model, user);
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		
		return flag;
	}
}
