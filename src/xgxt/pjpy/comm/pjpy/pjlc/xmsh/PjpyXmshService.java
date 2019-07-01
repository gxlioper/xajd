package xgxt.pjpy.comm.pjpy.pjlc.xmsh;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.form.SaveForm;
import xgxt.form.User;
import xgxt.pjpy.comm.pjpy.PjxtszModel;
import xgxt.pjpy.comm.pjpy.model.PjpyXmszModel;
import xgxt.pjpy.comm.pjpy.pjlc.PjpyPjlcService;
import xgxt.pjpy.comm.pjpy.pjlc.xmsq.PjpyXmsqForm;
import xgxt.pjpy.comm.pjpy.pjlc.xmsq.PjpyXmsqService;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 评奖评优_评奖流程_项目审核_Service类
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

public class PjpyXmshService extends PjpyPjlcService {

	PjpyXmshDAO dao = new PjpyXmshDAO();

	/**
	 * 获得审核项目列表
	 * 
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getXhxmList(PjpyXmshForm model,
			User user) {

		List<HashMap<String, String>> xmList = dao.getXhxmList(model, user);
		ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		if (xmList != null && xmList.size() > 0) {
			for (int i = 0; i < xmList.size(); i++) {
				
				HashMap<String, String> map = xmList.get(i);
				// 项目代码
				String xmdm = map.get("xmdm");
				// 评奖学年
				String pjxn = model.getJbszModel().getPjxn();
				// 评奖学期
				String pjxq = model.getJbszModel().getPjxq();
				// 评奖年度
				String pjnd = model.getJbszModel().getPjnd();

				String pkValue = pjxn + pjxq + pjnd + xmdm;
				
				PjpyXmszModel xmszModel = getXmszForXmdm(pkValue);
				
				//流程id
				String lcid = xmszModel.getLcid();
				//登陆用户
				String userName = user.getUserName();
				
				boolean flag = false;
				
				if(!Base.isNull(lcid)){
					// 流程信息
					List<HashMap<String, String>> lcInfoList = dao
							.getLcInfo(lcid);
					// 岗位信息
					List<HashMap<String, String>> gwInfoList = dao
							.getGwInfo(userName,lcid);
					
					if (lcInfoList != null && lcInfoList.size() > 0) {
						for (int j = 0; j < lcInfoList.size(); j++) {
							String spgw = lcInfoList.get(j).get("spgw");
							if (gwInfoList != null && gwInfoList.size() > 0) {
								for (int k = 0; k < gwInfoList.size(); k++) {
									String yhgw = gwInfoList.get(k).get("spgw");
									if (spgw.equalsIgnoreCase(yhgw)) {
										flag = true;
										break;
									}
								}
							}
							if (flag) {
								break;
							}
						}
					}
				}
				
				if (flag) {
					list.add(map);
				}
			}
		}

		return list;
	}

	/**
	 * 获得项目审核学生列表
	 * 
	 * @author 伟大的骆
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @throws Exception
	 */
	public ArrayList<String[]> getXmshXsList(PjpyXmshForm model, User user,
			String[] colList, HttpServletRequest request)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		return dao.getXmshXsList(model, user, colList, request);

	}

	/**
	 * 保存审核状态
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public Boolean saveXmshzt(PjpyXmshForm model, User user) throws Exception {

		DAO dao = DAO.getInstance();

		// 评奖项目审核表
		String tableName = "xg_pjpy_pjxmshb";
		// 主键
		String pk = "xmdm||pjxn||pjxq||pjnd||xh||xtgwid";
		// 主键值
		String[] pkValue = model.getPrimarykey_checkVal();
		// 修改字段
		String[] onezd = new String[] { "shzt", "shsj", "shr","shyj" };
		// /审核时间
		String shsj = dao.getNowTime("YYYYMMDD");
		// 审核状态
		String shzt = model.getShzt();
		// 审核人
		String shr = user.getUserName();

		model.setShzt(shzt);
		model.setShr(shr);
		model.setShsj(shsj);
		if (Base.isNull(model.getShyj())) {
			model.setShyj("");
		}

		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(tableName);
		saveForm.setPk(pk);
		saveForm.setPkValue(pkValue);
		saveForm.setOnezd(onezd);

		return updateInfoInDb(saveForm, model, user);
	}
	
	/**
	 * 保存审核状态(需重审)
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public Boolean saveXmcszt(PjpyXmshForm model, User user) throws Exception {

		// 评奖项目审核表
		String tableName = "xg_pjpy_pjxmshb";
		// 主键
		String pk = "xmdm||pjxn||pjxq||pjnd||xh||xtgwid";
		// 主键值
		String[] pkValue = dao.getXmthPk(model);
		// 修改字段
		String[] onezd = new String[] { "shzt", "shsj", "shr" };
		// /审核时间
		DAO dao = DAO.getInstance();
		String shsj = dao.getNowTime("YYYYMMDD");
		// 审核人
		String shr = user.getUserName();

		model.setShzt("需重审");
		model.setShr(shr);
		model.setShsj(shsj);

		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(tableName);
		saveForm.setPk(pk);
		saveForm.setPkValue(pkValue);
		saveForm.setOnezd(onezd);

		return updateInfoInDb(saveForm, model, user);
	}
	
	/**
	 * 保存审核状态(已重审)
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public Boolean saveXmycszt(PjpyXmshForm model, User user) throws Exception {

		// 评奖项目审核表
		String tableName = "xg_pjpy_pjxmshb";
		// 主键
		String pk = "xmdm||pjxn||pjxq||pjnd||xh||xtgwid";
		// 主键值
		String[] pkValue = dao.getXmcsPk(model);
		// 修改字段
		String[] onezd = new String[] { "shzt", "shsj", "shr" };
		// /审核时间
		DAO dao = DAO.getInstance();
		String shsj = dao.getNowTime("YYYYMMDD");
		// 审核人
		String shr = user.getUserName();

		model.setShzt("未审核");
		model.setShr(shr);
		model.setShsj(shsj);

		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(tableName);
		saveForm.setPk(pk);
		saveForm.setPkValue(pkValue);
		saveForm.setOnezd(onezd);

		return updateInfoInDb(saveForm, model, user);
	}
	
	/**
	 * 获得项目审核级别（最终）
	 * 
	 * @author 伟大的骆
	 */
	public String getXmzzshjb(PjpyXmshForm model) {
		return dao.getXmzzshjb(model);
	}
	
	/**
	 * 获得项目限制情况
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public String getXmxzInfo(PjpyXmshForm model) throws Exception {

		// 项目设置对象
		PjpyXmszModel xmszModel = model.getXmszModel();
		// 控制范围
		String kzfw = xmszModel.getKzfw();
		// 被审核人
		List<HashMap<String, String>> bshrList = dao.getXmshBshr(model);
		// 被审核人部门代码
		String[] bmdm = dao.getBshrbm(bshrList, kzfw);
		// 项目人数情况列表
		List<HashMap<String, String>> bmrsList = dao.getXmshrs(model, bmdm);
		// 项目非兼得列表
		List<HashMap<String, String>> xmfjdList = dao.getXmjdList(model);
		// 被审核人已获得奖学金列表
		List<HashMap<String, String>> yhdjxjList = dao.getYhdjxjList(model,
				bshrList);

		// 提示信息
		String message = "";

		// 判断项目人数限制
		message = judgeRsxz(model, bmrsList, bshrList);

		if (!Base.isNull(message)) {
			return message;
		}

		// 判断项目兼得限制
		message = judgeJdxz(model, xmfjdList, yhdjxjList);

		if (!Base.isNull(message)) {
			return message;
		}

		return null;
	}

	/**
	 * 判断项目人数限制
	 * 
	 * @author 伟大的骆
	 */
	private String judgeRsxz(PjpyXmshForm model,List<HashMap<String, String>> bmrsList,
			List<HashMap<String, String>> bshrList) {

		// 项目设置对象
		PjpyXmszModel xmszModel = model.getXmszModel();
		// 控制范围
		String kzfw = xmszModel.getKzfw();
		// 人数设置
		String rssz = xmszModel.getRssz();

		if ("是".equalsIgnoreCase(rssz) && bmrsList != null
				&& bmrsList.size() > 0) {
			for (int i = 0; i < bmrsList.size(); i++) {
				HashMap<String, String> bmInfo = bmrsList.get(i);
				// 部门代码
				String dm = bmInfo.get("bmdm");
				// 部门被设置人数
				int bmrs = Integer.parseInt(bmInfo.get("bmrs"));
				// 已通过人数
				int ytgrs = Integer.parseInt(bmInfo.get("ytgrs"));

				// 人数限制
				if (bshrList != null && bshrList.size() > 0) {
					for (int j = 0; j < bshrList.size(); j++) {
						HashMap<String, String> bshrInfo = bshrList.get(j);
						// 年级
						String nj = bshrInfo.get("nj");
						// 学院代码
						String xydm = bshrInfo.get("xydm");
						// 专业代码
						String zydm = bshrInfo.get("zydm");
						// 班级代码
						String bjdm = bshrInfo.get("bjdm");
						// 学院名称
						String xymc = bshrInfo.get("xymc");
						// 专业名称
						String zymc = bshrInfo.get("zymc");
						// 班级名称
						String bjmc = bshrInfo.get("bjmc");
						// 所属部门
						String sybm = "";
						// 部门名称
						String bmmc = "";
						// 控制范围
						if ("nj".equalsIgnoreCase(kzfw)) {
							sybm = nj;
							bmmc = nj + "级";
						} else if ("xy".equalsIgnoreCase(kzfw)) {
							sybm = xydm;
							bmmc = xymc;
						} else if ("zy".equalsIgnoreCase(kzfw)) {
							sybm = nj + zydm;
							bmmc = nj + "级" + zymc;
						} else if ("bj".equalsIgnoreCase(kzfw)) {
							sybm = bjdm;
							bmmc = bjmc;
						}

						if (sybm.equalsIgnoreCase(dm)) {
							// 增加已通过人数
							ytgrs++;

							if (ytgrs > bmrs) {

								String xmmc = xmszModel.getXmmc();

								StringBuilder message = new StringBuilder();
								message.append(bmmc);
								message.append("《" + xmmc + "》");
								message.append("最多通过人数为");
								message.append(bmrs);
								message.append("人,\n");
								message.append("审核通过人数过多，请确认！");

								return message.toString();
							}
						}
					}
				}
			}
		}
		return null;
	}

	/**
	 * 判断项目兼得限制
	 * 
	 * @author 伟大的骆
	 */
	private String judgeJdxz(PjpyXmshForm model,
			List<HashMap<String, String>> xmfjdList,
			List<HashMap<String, String>> yhdjxjList) {

		// 项目代码
		String xmdm = model.getShxm();
		// 评奖学年
		String xn = PjxtszModel.pjxtszModel.getPjxn();
		// 评奖学期
		String xq = PjxtszModel.pjxtszModel.getPjxq();
		// 评奖年度
		String nd = PjxtszModel.pjxtszModel.getPjnd();
		
		if (yhdjxjList != null && yhdjxjList.size() > 0) {
			for (int i = 0; i < yhdjxjList.size(); i++) {
				HashMap<String, String> yhInfo = yhdjxjList.get(i);
				// 已获得项目代码
				String yhddm = yhInfo.get("xmdm");
				// 被审核人学号
				String xh = yhInfo.get("xh");
				// 被审核人姓名
				String xm = yhInfo.get("xm");			
				// 学年
				String pjxn = yhInfo.get("pjxn");
				// 学期
				String pjxq = yhInfo.get("pjxq");
				// 年度
				String pjnd = yhInfo.get("pjnd");
				// 申请周期
				String sqzq = yhInfo.get("sqzq");
		
				if (("xn".equalsIgnoreCase(sqzq) && xn.equalsIgnoreCase(pjxn))
					|| ("nd".equalsIgnoreCase(sqzq) && nd.equalsIgnoreCase(pjnd))
					|| ("xq".equalsIgnoreCase(sqzq) && xn.equalsIgnoreCase(pjxn) && xq.equalsIgnoreCase(pjxq))) {

					if (xmfjdList != null && xmfjdList.size() > 0) {
						for (int j = 0; j < xmfjdList.size(); j++) {
							HashMap<String, String> xmfjdInfo = xmfjdList
									.get(j);
							// 以获得项目代码
							String dm = xmfjdInfo.get("xmdm");
							// 非兼得代码
							String fjddm = xmfjdInfo.get("fjddm");
							// 项目名称
							String fjdmc = xmfjdInfo.get("fjdmc");

							if (xmdm.equalsIgnoreCase(dm)
									&& yhddm.equalsIgnoreCase(fjddm)) {

								StringBuilder message = new StringBuilder();
								message.append(xh);
								message.append("(" + xm + ")");
								message.append("已获得");
								message.append("《" + fjdmc + "》,\n");
								message.append("与该审核项目不可兼得");

								return message.toString();
							}
						}
					}
				}
			}
		}
		
		return null;
	}

	/**
	 * 获得项目审核信息列表
	 * 
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getXmshInfoList(PjpyXmshForm model) {

		List<HashMap<String, String>> infoList = dao.getXmshInfoList(model);

		return infoList;
	}
	
	/**
	 * 获得项目调整列表列表
	 * 
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getSyxmList(PjpyXmshForm model) {

		return dao.getSyxmList(model);
	}
	
	/**
	 * 可否顺延
	 * 
	 * @author 伟大的骆
	 */
	public Boolean getKfsy(PjpyXmshForm model) {
		
		// 项目代码
		String xmdm = model.getSyxm();
		// 评奖学年
		String pjxn = PjxtszModel.pjxtszModel.getPjxn();
		// 评奖学期
		String pjxq = PjxtszModel.pjxtszModel.getPjxq();
		// 评奖年度
		String pjnd = PjxtszModel.pjxtszModel.getPjnd();
		
		String pk = pjxn + pjxq + pjnd + xmdm;
		PjpyXmszModel xmszModel = getXmszForXmdm(pk);
		
		return dao.getKfsy(model, xmszModel);
	}
	
	/**
	 * 保存项目顺延
	 * 
	 * @author 伟大的骆
	 */
	public Boolean saveXmsy(PjpyXmshForm model, User user) {

		PjpyXmsqService xmsqService = new PjpyXmsqService();
		PjpyXmsqForm xmsqModel = new PjpyXmsqForm();

		// 项目代码
		String xmdm = model.getSyxm();
		// 项目名称
		String xmmc = model.getXmmc();
		// 学号
		String xh = model.getXh();
		// 评奖学年
		String pjxn = PjxtszModel.pjxtszModel.getPjxn();
		// 评奖学期
		String pjxq = PjxtszModel.pjxtszModel.getPjxq();
		// 评奖年度
		String pjnd = PjxtszModel.pjxtszModel.getPjnd();

		String pk = pjxn + pjxq + pjnd + xmdm;
		PjpyXmszModel xmszModel = getXmszForXmdm(pk);

		
		DAO dao = DAO.getInstance();
		
		String sqsj = dao.getNowTime("YYYYMMDD");
		xmsqModel.setXh(xh);
		xmsqModel.setSqly("根据《"+xmmc+"》的申请情况顺延到本项目。");
		xmsqModel.setSqsj(sqsj);
		xmsqModel.setTjr(user.getUserName());

		return xmsqService.saveXssq(xmszModel, xmsqModel);
	}
}