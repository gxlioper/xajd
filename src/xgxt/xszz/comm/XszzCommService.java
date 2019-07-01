package xgxt.xszz.comm;

import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import xgxt.DAO.DAO;
import xgxt.DAO.ExcelMB;
import xgxt.action.Base;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.form.SaveForm;
import xgxt.form.User;
import xgxt.pjpy.guizhdx.GuizhdxDAO;
import xgxt.studentInfo.service.XsxxglService;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.ExcelMethods;
import xgxt.utils.GetTime;
import xgxt.utils.MakeQuery;
import xgxt.utils.SearchUtils;
import xgxt.utils.String.StringUtils;
import xgxt.utils.date.MoneyFormat;
import xgxt.xszz.XszzDAO;
import xgxt.xszz.XszzService;
import xgxt.xszz.XszzTyForm;
import xgxt.xszz.hzny.XszzHznyService;
import xgxt.xszz.nnzy.XszzNnzyService;
import xgxt.xszz.tjgy.XszzPrintService;
import xgxt.xszz.xysf.XszzXysfService;
import xgxt.xszz.zjkj.XszzZjkjService;
import xgxt.xszz.zjyd.XszzZjydService;

import common.Globals;
import common.XszzXmdm;

public class XszzCommService extends XszzService {

	XszzCommDAO dao = new XszzCommDAO();

	/**
	 * 综测名称
	 * 
	 * @return
	 */
	private String[] getZcfTitle() {
		String[] title = null;
		if (Globals.XXDM_MJXY.equals(Base.xxdm)) {
			title = new String[] { "品德行为表现总评得分", "专业学习表现总评得分", "文体实践表现总评得分" };
		} else {
			title = new String[] { "德育测评分", "智育测评分", "文体测评分" };
		}
		return title;
	}

	// =======================以下made by 伟大的luo==========================

	/**
	 * 获得个性化表头
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getTopTr(String lx) {

		DAO dao = DAO.getInstance();

		String[] colListCN = null;
		String[] colListEN = null;

		String[] arr_lx = lx.split("!!@@!!");
		String xmdm = "";
		if (arr_lx != null && arr_lx.length == 2) {
			lx = arr_lx[0];
			xmdm = arr_lx[1];
		}
		// 人数设置_学院
		if ("rssz_xy".equalsIgnoreCase(lx)) {
			colListCN = new String[] { Base.YXPZXY_KEY+"名称", "设置人数" };
			colListEN = new String[] { "xymc", "szrs" };
		}
		// 人数设置_专业
		else if ("rssz_zy".equalsIgnoreCase(lx)) {

			colListCN = new String[] { Base.YXPZXY_KEY+"名称", "年级", "专业名称", "设置人数" };
			colListEN = new String[] { "xymc", "nj", "zymc", "szrs" };
		}
		// 人数设置_班级
		else if ("rssz_bj".equalsIgnoreCase(lx)) {

			colListCN = new String[] { Base.YXPZXY_KEY+"名称", "年级", "专业名称", "班级名称", "设置人数" };
			colListEN = new String[] { "xymc", "nj", "zymc", "班级名称", "szrs" };
		}
		// 项目申请
		else if ("xmsq".equalsIgnoreCase(lx)) {

			colListCN = new String[] { "项目名称", "项目类别", "审核级别", "控制级别", "设置人数",
					"人数上限", "目前审核状态", "操作" };
			colListEN = new String[] { "xmmc", "xmlb", "shjb", "kzjb", "szrs",
					"rssx", "shzt", "cz" };
		}
		// 项目审核
		else if ("xmsh".equalsIgnoreCase(lx)) {

			colListCN = new String[] { "项目名称", "项目类别", "项目申请人数", "需审核人数",
					"已审核人数" };
			colListEN = new String[] { "xmmc", "xmlb", "sqrs", "xshrs", "shrs" };
		}
		// 项目审核(学生)
		else if ("xmsh_xs".equalsIgnoreCase(lx)) {

			colListCN = new String[] { "学号", "姓名", "性别", "年级", Base.YXPZXY_KEY+"名称", "专业名称",
					"班级名称", "申请时间", "审核状态" };
			colListEN = new String[] { "xh", "xm", "xb", "nj", "xymc", "zymc",
					"bjmc", "sqsj", "shzt1" };
		}
		// 结果查询
		else if ("jgcx".equalsIgnoreCase(lx)) {

			// 申请周期
			String sqzq = "";
			// 是否分级
			String sffj = "";
			// 审核级别
			String shjb = "";
			// 是否涉及金额
			String sfje = "";

			XszzTyForm model = new XszzTyForm();
			model.setPkValue(xmdm);
			HashMap<String, String> map = getXmxgInfo(model);
			xmdm = map.get("xmdm");

			if (!Base.isNull(xmdm)) {
				sfje = map.get("sfje");
				sffj = map.get("sffj");
				shjb = map.get("shjb");
				sqzq = map.get("sqzq");
			}

			ArrayList<String> cnList = new ArrayList<String>();
			ArrayList<String> enList = new ArrayList<String>();
			cnList.add("学号");
			enList.add("xh");
			cnList.add("姓名");
			enList.add("xm");
			cnList.add("性别");
			enList.add("xb");
			cnList.add("年级");
			enList.add("nj");
			cnList.add(Base.YXPZXY_KEY+"名称");
			enList.add("xymc");
			cnList.add("专业名称");
			enList.add("zymc");
			cnList.add("班级名称");
			enList.add("bjmc");
			cnList.add("项目名称");
			if ("学年".equalsIgnoreCase(sqzq)) {
				cnList.add("学年");
				enList.add("xn");
			} else if ("学期".equalsIgnoreCase(sqzq)) {
				cnList.add("学年");
				enList.add("xn");
				cnList.add("学期");
				enList.add("xq");
			} else if ("年度".equalsIgnoreCase(sqzq)) {
				cnList.add("年度");
				enList.add("nd");
			}
			enList.add("xmmc");
			cnList.add("申请时间");
			enList.add("sqsj");

			String xxdm = Base.xxdm;
			//北京联合旅游
			if (Globals.XXDM_BJLHLYDX.equalsIgnoreCase(xxdm)) {
				// 绿色通道
				if (XszzXmdm.XSZZ_KNS.equalsIgnoreCase(xmdm)) {

					cnList.add("是否低保");
					enList.add("sfdb");

					cnList.add("是否低收入");
					enList.add("kzzd3");
					
					cnList.add("人均月收入");
					enList.add("jtrjsr");
				}
				
			}
			
			
			if ("分级".equalsIgnoreCase(sffj)) {
				// 困难生认定
				if (XszzXmdm.XSZZ_KNS.equalsIgnoreCase(xmdm)) {
					cnList.add("认定类别");
					enList.add("xmzzjb");
				} else {
					cnList.add("项目级别");
					enList.add("xmzzjb");
				}
			}
			
			if ("需要".equalsIgnoreCase(sfje)) {
				cnList.add("金额");
				enList.add("xmzzje");
			}
			
			// 信阳师范
			if (Globals.XXDM_XYSFXY.equalsIgnoreCase(xxdm)) {
				// 绿色通道
				if (XszzXmdm.XSZZ_LSTD.equalsIgnoreCase(xmdm)) {

					cnList.add("应缴金额");
					enList.add("yjje");

					cnList.add("实缴金额");
					enList.add("hjje");
				}
				
				// 国家助学贷款
				if (XszzXmdm.XSZZ_GJZXDK.equalsIgnoreCase(xmdm)) {

					cnList.add("贷款年度");
					enList.add("dknd");

					cnList.add("贷款金额");
					enList.add("dkje");
				}
			}
			
			if ("一级审核".equalsIgnoreCase(shjb)) {
				cnList.add("审核状态");
				enList.add("shzt");
			} else if ("两级审核".equalsIgnoreCase(shjb)) {
				cnList.add("一级审核");
				enList.add("shzt1");
				cnList.add("两级审核");
				enList.add("shzt2");
			} else if ("三级审核".equalsIgnoreCase(shjb)) {
				cnList.add("一级审核");
				enList.add("shzt1");
				cnList.add("两级审核");
				enList.add("shzt2");
				cnList.add("三级审核");
				enList.add("shzt3");
			} else {
				cnList.add("审核状态");
				enList.add("shzt");
			}

			colListCN = cnList.toArray(new String[] {});
			colListEN = enList.toArray(new String[] {});

		}
		// ------------2010.12.02 qph 资助续办-----------------------
		else if ("zzxb".equalsIgnoreCase(lx)) {

			XszzTyForm model = new XszzTyForm();
			model.setPkValue(xmdm);
			HashMap<String, String> map = getXmxgInfo(model);
			xmdm = map.get("xmdm");

			ArrayList<String> cnList = new ArrayList<String>();
			ArrayList<String> enList = new ArrayList<String>();
			cnList.add("学号");
			enList.add("xh");
			cnList.add("姓名");
			enList.add("xm");
			cnList.add("性别");
			enList.add("xb");
			cnList.add("年级");
			enList.add("nj");
			cnList.add(Base.YXPZXY_KEY+"名称");
			enList.add("xymc");
			cnList.add("专业名称");
			enList.add("zymc");
			cnList.add("班级名称");
			enList.add("bjmc");
			cnList.add("申请时间");
			enList.add("sqsj");
			cnList.add("贷款状态");
			enList.add("isxb");

			colListCN = cnList.toArray(new String[] {});
			colListEN = enList.toArray(new String[] {});
		}
		// -----------end 资助续办--------------------------

		return dao.arrayToList(colListEN, colListCN);
	}

	/**
	 * 获得最大编号
	 * 
	 * @author luojw
	 */
	public String getXmbh(String tableName, String zd) {
		return dao.getXmbh(tableName, zd);
	}

	/**
	 * 保存项目维护
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public Boolean saveXmwh(XszzTyForm model, HashMap<String, String> tjMap,
			HttpServletRequest request) throws Exception {

		// 保存项目相关信息
		boolean flag = saveXmxgInfo(model, request);

		if (flag) {
			// 保存项目级别相关信息
			flag = saveXmjbInfo(model);
		}
		if (flag) {
			// 保存项目条件相关信息
			flag = saveXmtjInfo(model, tjMap);
		}

		if (flag){
			//兼得设置信息
			flag = dao.saveBkjdxm(model);
		}
		
		return flag;
	}

	/**
	 * 保存项目相关
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public Boolean saveXmxgInfo(XszzTyForm model, HttpServletRequest request)
			throws Exception {

		String[] onezd = new String[] { "xmdm", "xmmc", "xmb", "mrxm", "xxdm",
				"xmsm", "sfje", "sffj", "jelx", "kgzt", "shjb", "bzrsh",
				"fdysh", "xysh", "xxsh", "rskz", "bzrkz", "fdykz", "xykz",
				"xxkz", "kzjb", "sqzq", "rssx", "xmlb", "pdsj", "xssx" };

		String tableName = "xszz_zzxmb";

		String pk = "xmdm";

		// 审核级别
		String shjb = model.getShjb();
		// 审核人
		String[] shr = model.getShr();
		// 老师审核
		String lssh = model.getLssh();
		// 设置审核级别
		if (shr != null && shr.length > 0 && !"无需审核".equalsIgnoreCase(shjb)) {
			for (int i = 0; i < shr.length; i++) {
				if ("学校审核".equalsIgnoreCase(shr[i])) {
					model.setXxsh("是");
				} else if ("学院审核".equalsIgnoreCase(shr[i])) {
					model.setXysh("是");
				} else {
					if ("班主任".equalsIgnoreCase(lssh)) {
						model.setBzrsh("是");
					} else {
						model.setFdysh("是");
					}
				}
			}
		}

		// 控制人
		String[] kzr = model.getKzr();
		// 人数控制
		String rskz = model.getRskz();
		// 设置控制级别
		if (kzr != null && kzr.length > 0 && "需要".equalsIgnoreCase(rskz)) {
			for (int i = 0; i < kzr.length; i++) {
				if ("学校审核".equalsIgnoreCase(kzr[i])) {
					model.setXxkz("是");
				} else if ("学院审核".equalsIgnoreCase(kzr[i])) {
					model.setXykz("是");
				} else {
					model.setBzrkz("是");
					model.setFdykz("是");
				}
			}
		}
		if ((kzr == null || kzr.length == 0) && "需要".equalsIgnoreCase(rskz)) {
			// 若未指明具体控制哪一级别，就控制学院审核
			model.setXykz("是");
		}

		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(tableName);
		saveForm.setOnezd(onezd);
		saveForm.setPk(pk);
		saveForm.setPkValue(new String[] { model.getXmdm() });

		return saveXszz(saveForm, model, request);
	}

	/**
	 * 保存项目级别信息
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public Boolean saveXmjbInfo(XszzTyForm model) throws Exception {

		String[] arrzd = new String[] { "fjmc", "fjxxje", "fjsxje", "fjqdje" };
		String[] onezd = new String[] { "xmdm" };

		String tableName = "xszz_xmfjqkb";

		String pk = "xmdm";

		// 设置model的值
		setXmjbModel(model);

		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(tableName);
		saveForm.setOnezd(onezd);
		saveForm.setArrzd(arrzd);
		saveForm.setPk(pk);
		saveForm.setPkValue(new String[] { model.getXmdm() });

		return saveXszz(saveForm, model);
	}

	/**
	 * 设置项目级别moel的值
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	private void setXmjbModel(XszzTyForm model) {
		// 是否需要金额
		String sfje = model.getSfje();
		// 是否需要分级
		String sffj = model.getSffj();
		// 金额是否需要区间
		String jelx = model.getJelx();

		String[] fjmc = null;
		// 分级下限金额
		String[] fjxxje = null;
		// 分级上限金额
		String[] fjsxje = null;
		// 分级确定金额
		String[] fjqdje = null;
		// 金额确定值
		String noInfo = "无";

		// 需要金额
		if ("需要".equalsIgnoreCase(sfje)) {
			// 不需要分级
			if ("不分级".equalsIgnoreCase(sffj)) {
				// 分级名称
				fjmc = new String[] { noInfo };
				// 分级下限金额
				fjxxje = new String[] { noInfo };
				// 分级上限金额
				fjsxje = new String[] { noInfo };
				// 分级确定金额
				fjqdje = new String[] { noInfo };

				if ("确定值".equalsIgnoreCase(jelx)) {
					// 确定金额
					String nofjje = model.getNofjje();
					fjqdje = new String[] { nofjje };
				}
				// 金额区间值
				else {
					// 确定上限金额
					String nofjsx = model.getNofjsx();
					fjsxje = new String[] { nofjsx };
					// 确定下限金额
					String nofjxx = model.getNofjxx();
					fjxxje = new String[] { nofjxx };

				}
			}
			// 分级
			else {
				// 确定值
				if ("确定值".equalsIgnoreCase(jelx)) {
					// 分级名称
					fjmc = model.getQdjemc();
					// 分级确定金额
					fjqdje = model.getFjqdje();
					if (fjmc != null && fjmc.length > 0) {
						// 分级下限金额
						fjxxje = new String[fjmc.length];
						// 分级上限金额
						fjsxje = new String[fjmc.length];

						for (int i = 0; i < fjmc.length; i++) {
							fjxxje[i] = noInfo;
							fjsxje[i] = noInfo;
						}
					}
				}
				// 区间
				else {
					// 分级名称
					fjmc = model.getQjjemc();
					// 分级下限金额
					fjxxje = model.getFjxxje();
					// 分级上限金额
					fjsxje = model.getFjsxje();
					if (fjmc != null && fjmc.length > 0) {
						// 分级确定
						fjqdje = new String[fjmc.length];

						for (int i = 0; i < fjmc.length; i++) {
							fjqdje[i] = noInfo;
						}
					}
				}
			}
		}
		// 不需要金额
		else {
			// 需要分级
			if ("分级".equalsIgnoreCase(sffj)) {
				// 分级名称
				fjmc = model.getNojemc();
				if (fjmc != null && fjmc.length > 0) {
					// 分级确定
					fjqdje = new String[fjmc.length];
					// 分级下限金额
					fjxxje = new String[fjmc.length];
					// 分级上限金额
					fjsxje = new String[fjmc.length];
					for (int i = 0; i < fjmc.length; i++) {
						fjqdje[i] = noInfo;
						fjxxje[i] = noInfo;
						fjsxje[i] = noInfo;
					}
				}
			}
		}

		model.setFjmc(fjmc);
		model.setFjxxje(fjxxje);
		model.setFjsxje(fjsxje);
		model.setFjqdje(fjqdje);
	}

	/**
	 * 保存项目条件
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public Boolean saveXmtjInfo(XszzTyForm model, HashMap<String, String> tjMap)
			throws Exception {

		String[] arrzd = new String[] { "xmtjb", "xmtj", "xmtjz" };
		String[] onezd = new String[] { "xmdm" };

		String tableName = "xszz_xmtjb";

		String pk = "xmdm";

		if (!tjMap.isEmpty()) {

			// 条件
			String[] xmtj = new String[tjMap.size()];
			// 条件值
			String[] xmtjz = new String[tjMap.size()];
			// 条件所在表
			String[] xmtjb = new String[tjMap.size()];
			String[] temp_xmtjb = model.getXmtjb();
			int n = 0;

			for (String key : tjMap.keySet()) {

				xmtj[n] = key;
				xmtjz[n] = tjMap.get(key);

				if (temp_xmtjb != null && temp_xmtjb.length > 0) {
					for (int j = 0; j < temp_xmtjb.length; j++) {
						String[] arr_table = temp_xmtjb[j].split("@");
						if (arr_table != null && arr_table.length > 1
								&& key.equalsIgnoreCase(arr_table[0])) {
							xmtjb[n] = arr_table[1];
							break;
						}
					}
				}
				n++;
			}
			model.setXmtjb(xmtjb);
			model.setXmtj(xmtj);
			model.setXmtjz(xmtjz);

		}

		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(tableName);
		saveForm.setOnezd(onezd);
		saveForm.setArrzd(arrzd);
		saveForm.setPk(pk);
		saveForm.setPkValue(new String[] { model.getXmdm() });

		return saveXszz(saveForm, model);
	}

	/**
	 * 获得项目维护信息
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public HashMap<String, String> getXmwhfo(XszzTyForm model, String tableName) {

		// 项目全部信息
		HashMap<String, String> map = new HashMap<String, String>();

		// 项目相关信息
		HashMap<String, String> xmMap = getXmxgInfo(model);

		if (!xmMap.isEmpty()) {

			map.putAll(xmMap);

			model.setXmdm(map.get("xmdm"));

			// 项目条件信息
			HashMap<String, String> tjMap = getXmtjInfo(model);

			if (!tjMap.isEmpty()) {
				map.putAll(tjMap);
			}

		} else {

			// 无相关信息，赋初始值

			// 项目代码
			String xmdm = model.getXmdm();
			// 学校代码
			String xxdm = Base.xxdm;
			// 项目表
			String xmb = "xszz_comm_zzwsb";
			// 默认项目
			String mrxm = "否";

			map.put("xmdm", xmdm);
			map.put("xxdm", xxdm);
			map.put("xmb", xmb);
			map.put("mrxm", mrxm);

			map.put("sfje", "不需要");
			map.put("sffj", "不分级");
			map.put("jelx", "确定值");
			map.put("kgzt", "开放申请");
			map.put("shjb", "学校审核");
			map.put("sqzq", "无周期");
		}

		return map;
	}

	/**
	 * 获得学生申请信息
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public HashMap<String, String> getXsSqInfo(XszzTyForm model,
			String tableName) {

		// 主键
		String pk = "xh||sqsj";
		// 主键值得
		String pkValue = model.getPkValue();
		// 输出字段
		String[] colList = new String[] { "xn", "xq", "nd", "sqsj", "xh",
				"bzrsh", "fdysh", "xysh", "xxsh" };

		// 项目相关信息
		HashMap<String, String> map = (Base.isNull(pkValue)) ? new HashMap<String, String>()
				: getXszzInfo(tableName, pk, pkValue, colList);

		return map;
	}

	/**
	 * 获得项目条件信息
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public HashMap<String, String> getXmtjInfo(XszzTyForm model) {

		String tableName = "xszz_xmtjb";
		String dm = "xmtj";
		String mc = "xmtjz";
		String pk = "xmdm";
		String pkValue = model.getXmdm();

		// 项目条件信息
		List<HashMap<String, String>> tjList = dao.getWhList(tableName, dm, mc,
				"", pk, pkValue);
		tjList.remove(0);

		HashMap<String, String> map = new HashMap<String, String>();

		if (tjList != null && tjList.size() > 0) {
			for (int i = 0; i < tjList.size(); i++) {

				HashMap<String, String> tj = tjList.get(i);

				String xmtj = tj.get("dm");
				String xmtjz = tj.get("mc");

				map.put("save_" + xmtj, xmtjz);

			}
		}

		return map;
	}

	/**
	 * 修改开关状态
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public Boolean updateKgzt(XszzTyForm model) throws Exception {
		return dao.updateKgzt(model);
	}

	/**
	 * 获得项目下拉列表（非项目关闭）
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getZzxmList() {
		return dao.getZzxmList();
	}

	/**
	 * 保存人数设置
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public Boolean saveRssz(XszzTyForm model) throws Exception {

		boolean flag = false;

		// 审核控制级别
		String kzjb = model.getKzjb();

		// 分配方式
		String fpfs = model.getFpfs();
		// 项目相关信息
		model.setPkValue(model.getXmdm());
		HashMap<String, String> map = getXmxgInfo(model);

		// 按比例分配
		if ("比例".equalsIgnoreCase(fpfs)) {
			// 学院
			if ("学院".equalsIgnoreCase(kzjb)) {
				flag = saveXyBlrs(model, map);
			}
			// 专业
			else if ("专业".equalsIgnoreCase(kzjb)) {
				flag = saveZyBlrs(model, map);
			}
			// 班级
			else if ("班级".equalsIgnoreCase(kzjb)) {
				flag = saveBjBlrs(model, map);
			}
		}
		// 按人数分配
		else {
			// 学院
			if ("学院".equalsIgnoreCase(kzjb)) {
				model.setBmjb("xy");
			}
			// 专业
			else if ("专业".equalsIgnoreCase(kzjb)) {
				model.setBmjb("zy");
			}
			// 班级
			else if ("班级".equalsIgnoreCase(kzjb)) {
				model.setBmjb("bj");
			}
			flag = saveRsszRsfp(model, map);
		}

		return flag;
	}

	/**
	 * 保存学院比例人数
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public Boolean saveXyBlrs(XszzTyForm model, HashMap<String, String> map)
			throws Exception {
		return dao.saveXyBlrs(model, map);
	}

	/**
	 * 保存专业比例人数
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public Boolean saveZyBlrs(XszzTyForm model, HashMap<String, String> map)
			throws Exception {
		return dao.saveZyBlrs(model, map);
	}

	/**
	 * 保存班级比例人数
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public Boolean saveBjBlrs(XszzTyForm model, HashMap<String, String> map)
			throws Exception {
		return dao.saveBjBlrs(model, map);
	}

	/**
	 * 保存人数设置（人数分配）
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public Boolean saveRsszRsfp(XszzTyForm model, HashMap<String, String> map)
			throws Exception {
		return dao.saveRsszRsfp(model, map);
	}

	/**
	 * 获得资助项目人数设置
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public ArrayList<String[]> getRsszList(XszzTyForm model) {

		// 审核控制级别
		String kzjb = model.getKzjb();
		// 项目相关信息
		model.setPkValue(model.getXmdm());
		HashMap<String, String> map = getXmxgInfo(model);

		// 学院
		if ("学院".equalsIgnoreCase(kzjb)) {
			model.setBmjb("xy");
		}
		// 专业
		else if ("专业".equalsIgnoreCase(kzjb)) {
			model.setBmjb("zy");
		}
		// 班级
		else if ("班级".equalsIgnoreCase(kzjb)) {
			model.setBmjb("bj");
		}

		ArrayList<String[]> list = dao.getRsszList(model, map);

		return getResultList(list, model);
	}

	/**
	 * 修改人数设置
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public Boolean updateRssz(XszzTyForm model) throws Exception {

		// 项目相关信息
		HashMap<String, String> map = getXmxgInfo(model);

		// 审核控制级别
		String kzjb = model.getKzjb();

		// 学院
		if ("学院".equalsIgnoreCase(kzjb)) {
			model.setBmjb("xy");
		}
		// 专业
		else if ("专业".equalsIgnoreCase(kzjb)) {
			model.setBmjb("zy");
		}
		// 班级
		else if ("班级".equalsIgnoreCase(kzjb)) {
			model.setBmjb("bj");
		}

		return dao.updateRssz(model, map);
	}

	/**
	 * 获得资助项目人数情况
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public HashMap<String, String> getXmrsInfo(XszzTyForm model) {
		// 项目相关信息
		model.setPkValue(model.getXmdm());
		HashMap<String, String> map = getXmxgInfo(model);

		return dao.getXmrsInfo(model, map);
	}

	/**
	 * 判断是否超过人数上限
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public String isCgRssx(XszzTyForm model) throws Exception {
		// 保存部门
		String[] bmdm = model.getPrimarykey_checkVal();
		// 设置人数
		String[] szrs = model.getSzrs();
		// 设置年级
		String[] sznj = model.getSznj();
		// 审核控制级别
		String kzjb = model.getKzjb();
		// /项目情况
		HashMap<String, String> xmrsInfo = getXmrsInfo(model);
		// 人数上限
		String rssx = xmrsInfo.get("rssx");
		// 已分配人数情况
		List<HashMap<String, String>> yfpList = getYfprsList(model);

		// 总人数
		float sumRs = 0;

		if (yfpList != null && yfpList.size() > 0) {

			for (int i = 0; i < yfpList.size(); i++) {
				HashMap<String, String> map = yfpList.get(i);
				// 部门设置人数
				String rs = map.get("szrs");
				// 部门级别+年级+代码
				String dm = map.get("bmjb") + map.get("nj") + map.get("bmdm");

				if (bmdm != null && bmdm.length > 0) {
					for (int j = 0; j < bmdm.length; j++) {
						// 部门级别+年级
						String jbnj = "";
						// 学院
						if ("学院".equalsIgnoreCase(kzjb)) {
							jbnj = "xy全部";
						}
						// 专业
						else if ("专业".equalsIgnoreCase(kzjb)) {
							jbnj = "zy" + sznj[j];
						}
						// 班级
						else {
							jbnj = "bj" + sznj[j];
						}
						if (dm.equalsIgnoreCase(jbnj + bmdm[j])) {
							rs = szrs[j];
							break;
						}
					}
				}
				sumRs += Float.parseFloat(rs);
			}
		} else {
			for (int j = 0; j < bmdm.length; j++) {
				sumRs += Float.parseFloat(szrs[j]);
			}
		}

		String message = "";

		if (!Base.isNull(rssx) && sumRs > Float.parseFloat(rssx)) {
			message = "该项目人数上限为" + rssx + "人，\n现在设置人数" + sumRs
					+ "人,\n已超过上限，请确认！";
		}
		return message;
	}

	/**
	 * 获得已分配人数
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getYfprsList(XszzTyForm model)
			throws Exception {
		// 项目相关信息
		model.setPkValue(model.getXmdm());
		HashMap<String, String> map = getXmxgInfo(model);

		return dao.getYfprsList(model, map);
	}

	/**
	 * 获得项目申请列表
	 * 
	 * @author luojw
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public List<HashMap<String, String>> getXmsqList(XszzTyForm model)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		// TODO
		XsxxglService stuService = new XsxxglService();

		// 学号
		String xh = model.getXh();

		// 学生基本信息
		HashMap<String, String> stuInfo = stuService.selectStuinfo(xh);

		// 所有项目列表
		List<HashMap<String, String>> allXmList = getAllXmList(model);

		// 获得学生所在学院（专业班级）已审核通过学生列表
		ArrayList<String[]> xmYtgList = getXmYtgList(allXmList, stuInfo, model);

		// 获得项目人数设置列表
		List<HashMap<String, String>> xmRsszList = getXmRsszList(allXmList,
				model);
		// 获得项目条件列表
		List<HashMap<String, String>> xmTjList = getXmTjList(model);

		// 学生已申请项目列表
		List<HashMap<String, String>> xsYsqXmList = getXsYsqXmList(allXmList,
				model);

		// 移除需要人数控制而人数设置未设置（或者为0）的项目
		List<HashMap<String, String>> xqxmlist = removeRsszList(model, stuInfo,
				allXmList, xmRsszList);

		// 移除项目达到上限的项目
		xqxmlist = removeXmSxList(model, xmYtgList, xqxmlist);

		// 移除不符合条件设置的项目
		xqxmlist = removeBfhTjXm(model, xmTjList, xqxmlist);

		// 设置项目其他信息
		xqxmlist = setXmOtherList(xqxmlist);

		// 设置项目审核相关信息
		xqxmlist = setYsqXm(model, xsYsqXmList, xqxmlist);

		
		if (Globals.XXDM_TJGYDX.equals(Base.xxdm)){
			setXmDisabled(xqxmlist);
		}
		
		
		
		return getResultList(xqxmlist, model);
	}

	
	private void setXmDisabled(List<HashMap<String, String>> xqxmList){
		
		boolean sfsq = false;
		
		for (int i = 0 ; i < xqxmList.size() ; i++){
			
			HashMap<String,String> map = xqxmList.get(i);
			
			if (map.get("xmdm").equals("5001")){
				
				if (!"yes".equals(map.get("ysq"))){
					sfsq = true;
				}
				
				break;
			} else {
				continue;
			}
		}
		
		for (int i = 0 ; i < xqxmList.size() ; i++){
			xqxmList.get(i).put("disabled", String.valueOf(sfsq));
		}
	}
	
	
	
	/**
	 * 设置项目其他信息
	 * 
	 * @param xqxmlist
	 */
	private List<HashMap<String, String>> setXmOtherList(
			List<HashMap<String, String>> xqxmlist) {
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		if (xqxmlist != null && xqxmlist.size() > 0) {

			for (int i = 0; i < xqxmlist.size(); i++) {

				HashMap<String, String> xmInfo = xqxmlist.get(i);

				// 人数控制
				String rskz = xmInfo.get("rskz");
				// 控制级别
				String kzjb = xmInfo.get("kzjb");
				// 设置人数
				String szrs = xmInfo.get("szrs");
				szrs = Base.isNull(szrs) ? "0" : szrs;
				// 已通过人数
				String rs = xmInfo.get("rs");
				rs = Base.isNull(rs) ? "0" : rs;

				String syrs = "0";

				if ("需要".equalsIgnoreCase(rskz)) {
					syrs = String.valueOf(Integer.parseInt(szrs)
							- Integer.parseInt(rs));
				} else {
					szrs = "无人数控制";
					szrs = "无人数控制";
					syrs = "无人数控制";
					kzjb = "无级别控制";
				}
				xmInfo.put("szrs", szrs);
				xmInfo.put("syrs", syrs);
				xmInfo.put("kzjb", kzjb);

				list.add(xmInfo);
			}
		}

		return list;
	}

	/**
	 * 设置项目审核相关信息
	 * 
	 * @param xh
	 * @param xmTjList
	 * @param xqxmlist
	 */
	private List<HashMap<String, String>> setYsqXm(XszzTyForm model,
			List<HashMap<String, String>> xsYsqXmList,
			List<HashMap<String, String>> xqxmlist) {

		// 当前学年
		String xn = model.getXn();
		// 当前学期
		String xq = model.getXq();
		// 当前年度
		String nd = model.getNd();
		// 申请时间
		String nowsj = model.getSqsj();

		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		if (xqxmlist != null && xqxmlist.size() > 0) {

			for (int i = 0; i < xqxmlist.size(); i++) {

				HashMap<String, String> xmInfo = xqxmlist.get(i);
				// 项目代码
				String xmdm = xmInfo.get("xmdm");
				// 申请周期
				String sqzq = xmInfo.get("sqzq");
				// 审核级别
				String shjb = xmInfo.get("shjb");
				// 已申请
				String ysq = "no";
				// 已审核
				String ysh = "no";
				// 已申请项目申请时间
				String ysqsj = "";
				// 审核情况
				String shqk = !"无需审核".equalsIgnoreCase(shjb) ? "尚未审核" : "无需审核";
				// 审核状态
				String shzt = "";
				// 评定时间
				String pdsj = xmInfo.get("pdsj");
				// 评定时间为前次
				if ("前次".equalsIgnoreCase(pdsj)) {
					HashMap<String, String> befInfo = getBeforeXnXqNd(sqzq,
							pdsj, model);
					xn = befInfo.get("xn");
					xq = befInfo.get("xq");
					nd = befInfo.get("nd");
				}
				// --------2010.9.27 lr--------
				else {
					// 当前学年
					xn = model.getXn();
					// 当前学期
					xq = model.getXq();
					// 当前年度
					nd = model.getNd();
				}
				// -----------end 2010.9.27 lr------
				if (xsYsqXmList != null && xsYsqXmList.size() > 0) {

					for (int j = 0; j < xsYsqXmList.size(); j++) {

						HashMap<String, String> ysqInfo = xsYsqXmList.get(j);

						// 申请项目
						String sqxm = ysqInfo.get("xmdm");
						// 申请学年
						String sqxn = ysqInfo.get("xn");
						// 申请学期
						String sqxq = ysqInfo.get("xq");
						// 申请年度
						String sqnd = ysqInfo.get("nd");
						// 申请时间
						String sqsj = ysqInfo.get("sqsj");
						// 班主任审核
						String bzrsh = ysqInfo.get("bzrsh");
						// 辅导员审核
						String fdysh = ysqInfo.get("fdysh");
						// 学院审核
						String xysh = ysqInfo.get("xysh");
						// 学校审核
						String xxsh = ysqInfo.get("xxsh");

						shzt = ysqInfo.get("shzt");

						// 项目符合
						if (xmdm.equalsIgnoreCase(sqxm)) {
							// 申请周期符合且审核状态为通过
							if ((("学年".equalsIgnoreCase(sqzq) && xn
									.equalsIgnoreCase(sqxn))
									|| ("年度".equalsIgnoreCase(sqzq) && nd
											.equalsIgnoreCase(sqnd)) || ("学期"
									.equalsIgnoreCase(sqzq)
									&& xn.equalsIgnoreCase(sqxn) && xq
									.equalsIgnoreCase(sqxq)))
									|| ("无周期".equalsIgnoreCase(sqzq) && nowsj
											.equalsIgnoreCase(sqsj))
									|| ("仅一次".equalsIgnoreCase(sqzq))) {

								ysq = "yes";
								ysqsj = sqsj;

								if (!"无需审核".equalsIgnoreCase(shjb)) {
									if (!Base.isNull(bzrsh)
											&& !"未审核".equalsIgnoreCase(bzrsh)
											|| !Base.isNull(fdysh)
											&& !"未审核".equalsIgnoreCase(fdysh)
											|| !Base.isNull(xysh)
											&& !"未审核".equalsIgnoreCase(xysh)
											|| !Base.isNull(xxsh)
											&& !"未审核".equalsIgnoreCase(xxsh)) {

										ysh = "yes";

										shqk = (!Base.isNull(bzrsh) && !"未审核"
												.equalsIgnoreCase(bzrsh)) ? "班主任审核"
												+ bzrsh
												: shqk;
										shqk = (!Base.isNull(fdysh) && !"未审核"
												.equalsIgnoreCase(fdysh)) ? "辅导员审核"
												+ fdysh
												: shqk;
										shqk = (!Base.isNull(xysh) && !"未审核"
												.equalsIgnoreCase(xysh)) ? "院系审核"
												+ xysh
												: shqk;
										shqk = (!Base.isNull(xxsh) && !"未审核"
												.equalsIgnoreCase(xxsh)) ? "学校审核"
												+ xxsh
												: shqk;
									}
								} else {
									shqk = "无需审核";
								}
							}
						}
					}
				}

				xmInfo.put("ysq", ysq);
				xmInfo.put("ysh", ysh);
				xmInfo.put("shqk", shqk);
				xmInfo.put("shzt", shzt);
				xmInfo.put("ysqsj", ysqsj);
				list.add(xmInfo);

			}
		}

		return list;
	}

	/**
	 * 移除不符合条件设置的项目
	 * 
	 * @param xh
	 * @param xmTjList
	 * @param xqxmlist
	 */
	private List<HashMap<String, String>> removeBfhTjXm(XszzTyForm model,
			List<HashMap<String, String>> xmTjList,
			List<HashMap<String, String>> xqxmlist) {

		// 学号
		String xh = model.getXh();

		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		if (xqxmlist != null && xqxmlist.size() > 0) {

			for (int i = 0; i < xqxmlist.size(); i++) {

				HashMap<String, String> xmInfo = xqxmlist.get(i);

				// 项目代码
				String xmdm = xmInfo.get("xmdm");

				boolean flag = true;

				if (xmTjList != null && xmTjList.size() > 0) {

					for (int j = 0; j < xmTjList.size(); j++) {

						HashMap<String, String> tjInfo = xmTjList.get(j);

						// 条件项目
						String tjxm = tjInfo.get("xmdm");
						// 项目条件表
						String xmtjb = tjInfo.get("xmtjb");
						// 项目条件
						String xmtj = tjInfo.get("xmtj");
						// 条件值
						String xmtjz = tjInfo.get("xmtjz");

						// 项目符合
						if (tjxm.equalsIgnoreCase(xmdm)) {
							if("0014".equalsIgnoreCase(xmdm)){
								int a=0;
								a++;
							}
							if (!"无限制".equalsIgnoreCase(xmtjz)) {
								flag = dao.isFhXmTj(xh, xmtjb, xmtj, xmtjz,
										xmdm);

								if (!flag) {
									break;
								}
							}
						}

					}
				}
				if (flag) {
					list.add(xmInfo);
				}
			}
		}

		return list;
	}

	/**
	 * 移除项目达到上限的项目
	 * 
	 * @param model
	 * @param xh
	 * @param xmYtgList
	 * @param xqxmlist
	 */
	private List<HashMap<String, String>> removeXmSxList(XszzTyForm model,
			ArrayList<String[]> xmYtgList,
			List<HashMap<String, String>> xqxmlist) {

		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		if (xqxmlist != null && xqxmlist.size() > 0) {

			// 学号
			String xh = model.getXh();
			// 当前学年
			String xn = model.getXn();
			// 当前学期
			String xq = model.getXq();
			// 当前年度
			String nd = model.getNd();

			for (int i = 0; i < xqxmlist.size(); i++) {

				boolean flag = true;

				HashMap<String, String> xmInfo = xqxmlist.get(i);

				// 项目代码
				String xmdm = xmInfo.get("xmdm");
				// 申请周期
				String sqzq = xmInfo.get("sqzq");
				// 人数控制是否需要
				String rskz = xmInfo.get("rskz");
				// 审核级别
				String shjb = xmInfo.get("shjb");
				// 评定时间
				String pdsj = xmInfo.get("pdsj");
				// 评定时间为前次
				if ("前次".equalsIgnoreCase(pdsj)) {
					HashMap<String, String> befInfo = getBeforeXnXqNd(sqzq,
							pdsj, model);
					xn = befInfo.get("xn");
					xq = befInfo.get("xq");
					nd = befInfo.get("nd");
				}

				// 设置人数
				String szrs = xmInfo.get("szrs");
				int int_szrs = Base.isNull(szrs) ? 0 : Integer.parseInt(szrs);

				if ("需要".equalsIgnoreCase(rskz) && xmYtgList != null
						&& xmYtgList.size() > 0) {

					// 已通过人数
					int rs = 0;

					for (int j = 0; j < xmYtgList.size(); j++) {

						String[] ytgInfo = xmYtgList.get(j);
						// 申请学年
						String sqxn = ytgInfo[0];// ytgInfo.get("xn");
						// 申请学期
						String sqxq = ytgInfo[1];// ytgInfo.get("xq");
						// 申请年度
						String sqnd = ytgInfo[2];// ytgInfo.get("nd");
						// 申请项目
						String sqxm = ytgInfo[3];// ytgInfo.get("xmdm");
						// 项目通过人数
						String tgrs = ytgInfo[4];// ytgInfo.get("xmdm");
						// 申请学号
						// String sqxh = ytgInfo[4];//ytgInfo.get("xh");
						// 审核状态
						String shzd = "";
						// if("一级审核".equalsIgnoreCase(shjb)){
						// shzd = "shzt1";
						// }else if("两级审核".equalsIgnoreCase(shjb)){
						// shzd = "shzt2";
						// }else if("三级审核".equalsIgnoreCase(shjb)){
						// shzd = "shzt3";
						// }

						// String shzt = ytgInfo.get(shzd);

						// 项目符合
						if (xmdm.equalsIgnoreCase(sqxm)) {
							// 申请周期符合
							if ((("学年".equalsIgnoreCase(sqzq) && xn
									.equalsIgnoreCase(sqxn))
									|| ("年度".equalsIgnoreCase(sqzq) && nd
											.equalsIgnoreCase(sqnd)) || ("学期"
									.equalsIgnoreCase(sqzq)
									&& xn.equalsIgnoreCase(sqxn) && xq
									.equalsIgnoreCase(sqxq))) ) {
								rs = Integer.parseInt(tgrs);
							}
						}
					}
					// 人数是否达到上限
					if (rs >= int_szrs) {
						flag = false;
					}

					xmInfo.put("rs", String.valueOf(rs));
				}

				if (flag) {
					list.add(xmInfo);
				}
			}
		}

		return list;
	}

	/**
	 * 移除需要人数控制而人数设置未设置（或者为0）的项目
	 * 
	 * @author luojw
	 * @param model
	 * @param stuInfo
	 * @param allXmList
	 * @param xmRsszList
	 * @return
	 */
	private List<HashMap<String, String>> removeRsszList(XszzTyForm model,
			HashMap<String, String> stuInfo,
			List<HashMap<String, String>> allXmList,
			List<HashMap<String, String>> xmRsszList) {

		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		if (allXmList != null && allXmList.size() > 0) {

			// 当前学年
			String xn = model.getXn();
			// 当前学期
			String xq = model.getXq();
			// 当前年度
			String nd = model.getNd();
			// 学院
			String xydm = stuInfo.get("xydm");
			// 年级
			String nj = stuInfo.get("nj");
			// 专业
			String zydm = stuInfo.get("zydm");
			// 班级
			String bjdm = stuInfo.get("bjdm");

			for (int i = 0; i < allXmList.size(); i++) {

				boolean flag = true;

				HashMap<String, String> xmInfo = allXmList.get(i);
				// 项目代码
				String xmdm = xmInfo.get("xmdm");
				// 申请周期
				String sqzq = xmInfo.get("sqzq");
				// 人数控制是否需要
				String rskz = xmInfo.get("rskz");
				// 评定时间
				String pdsj = xmInfo.get("pdsj");
				// 评定时间为前次
				if ("前次".equalsIgnoreCase(pdsj)) {
					HashMap<String, String> befInfo = getBeforeXnXqNd(sqzq,
							pdsj, model);
					xn = befInfo.get("xn");
					xq = befInfo.get("xq");
					nd = befInfo.get("nd");
				}
				if ("需要".equalsIgnoreCase(rskz) && xmRsszList != null
						&& xmRsszList.size() > 0) {

					for (int j = 0; j < xmRsszList.size(); j++) {

						HashMap<String, String> rsszInfo = xmRsszList.get(j);

						// 设置学年
						String szxn = rsszInfo.get("xn");
						// 设置学期
						String szxq = rsszInfo.get("xq");
						// 设置年度
						String sznd = rsszInfo.get("nd");
						// 项目代码
						String szxm = rsszInfo.get("xmdm");
						// 部门级别
						String bmjb = rsszInfo.get("bmjb");
						// 设置年级
						String sznj = rsszInfo.get("nj");
						// 部门代码
						String bmdm = rsszInfo.get("bmdm");
						// 设置人数
						String szrs = rsszInfo.get("szrs");

						// 项目符合
						if (xmdm.equalsIgnoreCase(szxm)) {
							// 部门符合,申请周期符合
							if ((("xy".equalsIgnoreCase(bmjb) && xydm
									.equalsIgnoreCase(bmdm))
									|| ("zy".equalsIgnoreCase(bmjb)
											&& zydm.equalsIgnoreCase(bmdm) && nj
											.equalsIgnoreCase(sznj)) || ("bj"
									.equalsIgnoreCase(bmjb) && bjdm
									.equalsIgnoreCase(bmdm)))
									&& ((("学年".equalsIgnoreCase(sqzq) && xn
											.equalsIgnoreCase(szxn))
											|| ("年度".equalsIgnoreCase(sqzq) && nd
													.equalsIgnoreCase(sznd)) || ("学期"
											.equalsIgnoreCase(sqzq)
											&& xn.equalsIgnoreCase(szxn) && xq
											.equalsIgnoreCase(szxq)))
									|| ("无周期".equalsIgnoreCase(sqzq)))) {
								if (Base.isNull(szrs)
										|| "0".equalsIgnoreCase(szrs)) {
									flag = false;
								} else {
									xmInfo.put("szrs", szrs);
									flag = true;
									break;
								}
							} else {
								flag = false;
							}
						}
					}
				}

				if ("需要".equalsIgnoreCase(rskz)
						&& !(xmRsszList != null && xmRsszList.size() > 0)) {
					flag = false;
				}

				if (flag) {
					list.add(xmInfo);
				}
			}
		}
		return list;
	}

	/**
	 * 获得所有项目列表
	 * 
	 * @author luojw
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public List<HashMap<String, String>> getAllXmList(XszzTyForm model)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		String tableName = "view_xszz_comm_xmwh";

		String[] colList = new String[] { "pk", "xmdm", "xmmc", "sqzq", "xmsm",
				"xmb", "rskz", "kzjb", "shjb", "bzrsh", "fdysh", "xysh",
				"xxsh", "rssx", "xmlb", "pdsj" };
		String[] queryList = new String[] { "kgzt", "xmdm", "xmlb" };
		String[] queryLikeList = new String[] {};

		model.setKgzt("开放申请");

		MakeQuery myQuery = new MakeQuery();
		myQuery.makeQuery(queryList, queryLikeList, model);

		// 查询条件
		StringBuilder query = new StringBuilder();
		query.append(myQuery.getQueryString());
		query.append(getFlowControlSql(model));//困难生独立模块过滤
		
		List<HashMap<String, String>> allXmList = CommonQueryDAO
				.commonQueryforList(tableName, query.toString(), myQuery
						.getInputList(), colList, "");

		return allXmList;
	}

	/**
	 * 获得学生已申请项目列表
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getXsYsqXmList(
			List<HashMap<String, String>> allXmList, XszzTyForm model) {

		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		if (allXmList != null && allXmList.size() > 0) {
			// 所有项目表
			String[] xmb = new String[allXmList.size()];

			for (int i = 0; i < allXmList.size(); i++) {
				HashMap<String, String> xmInfo = allXmList.get(i);
				xmb[i] = xmInfo.get("xmb");
			}

			list = dao.getXsYsqXmList(model, xmb);
		}

		return list;
	}

	/**
	 * 获得项目人数设置列表
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getXmRsszList(
			List<HashMap<String, String>> allXmList, XszzTyForm model) {

		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		if (allXmList != null && allXmList.size() > 0) {
			// 所有项目代码
			String[] xmdm = new String[allXmList.size()];

			for (int i = 0; i < allXmList.size(); i++) {
				HashMap<String, String> xmInfo = allXmList.get(i);
				xmdm[i] = xmInfo.get("xmdm");
			}

			list = dao.getXmRsszList(model, xmdm);
		}

		return list;
	}

	/**
	 * 获得学生所在学院（专业班级）已审核通过学生列表
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public ArrayList<String[]> getXmYtgList(
			List<HashMap<String, String>> allXmList,
			HashMap<String, String> stuInfo, XszzTyForm model) {

		ArrayList<String[]> list = new ArrayList<String[]>();

		if (allXmList != null && allXmList.size() > 0) {
			// 所有项目代码
			String[] xmdm = new String[allXmList.size()];
			// 所有项目表
			String[] xmb = new String[allXmList.size()];
			// 所有项目的控制级别
			String[] kzjb = new String[allXmList.size()];

			for (int i = 0; i < allXmList.size(); i++) {
				HashMap<String, String> xmInfo = allXmList.get(i);
				xmdm[i] = xmInfo.get("xmdm");
				xmb[i] = xmInfo.get("xmb");
				kzjb[i] = xmInfo.get("kzjb");
			}

			list = dao.getXmYtgList(model, stuInfo, xmb, xmdm, kzjb);
		}

		return list;
	}

	/**
	 * 获得项目条件列表
	 * 
	 * @author luojw
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getXmTjList(XszzTyForm model)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		String tableName = "xszz_xmtjb";

		String[] colList = new String[] { "xmdm", "xmtjb", "xmtj", "xmtjz" };
		String[] queryList = new String[] {};
		String[] queryLikeList = new String[] {};

		MakeQuery myQuery = new MakeQuery();
		myQuery.makeQuery(queryList, queryLikeList, model);

		// 查询条件
		StringBuilder query = new StringBuilder();
		query.append(myQuery.getQueryString());
		query.append(" order by xmdm");

		// 项目条件
		List<HashMap<String, String>> xmTjList = CommonQueryDAO
				.commonQueryforList(tableName, query.toString(), myQuery
						.getInputList(), colList, "");

		return xmTjList;
	}

	/**
	 * 获得项目申请内容列表
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public List<HashMap<String, Object>> getXmSqNrList(XszzTyForm model)
			throws Exception {
		// TODO
		// 学校代码
		String xxdm = Base.xxdm;
		// 项目表
		String xmb = model.getXmb();

		// 字段列表
		List<HashMap<String, Object>> resultList = new ArrayList<HashMap<String, Object>>();

		// 字段列表
		List<HashMap<String, String>> zdList = setXmzdList(model);

		// 设置学生基本信息
		setXsInfo(model, resultList, zdList);

		// 设置家庭基本情况
		setJtjbInfo(model, resultList, zdList);

		if (Globals.XXDM_HAINDX.equalsIgnoreCase(xxdm)
				|| Globals.XXDM_MJXY.equalsIgnoreCase(xxdm)) {// 闽江，海南
			setZcfInfo(model, resultList, zdList);
		}

		// 困难信息
		setKnInfo(model, resultList, zdList);

//		if (Globals.XXDM_ZGDZDX.equalsIgnoreCase(xxdm)||Globals.XXDM_TJGYDX.equalsIgnoreCase(xxdm)) {// 地大
			// 设置其他信息
			setQtInfo(model, resultList, zdList);
//		}

		if (!"jtqkdcb".equalsIgnoreCase(xmb)) {
			// 设置项目基本情况
			setXmzdInfo(model, resultList, zdList);
		}

		// // 附件相关
		// setFileInfo(model, resultList, zdList);

		return resultList;
	}

	/**
	 * 设置项目展示字段
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> setXmzdList(XszzTyForm model)
			throws Exception {

		// 项目代码
		String xmdm = model.getXmdm();
		// 字段列表
		List<HashMap<String, String>> zdList = null;

		// 家庭情况
		if (XszzXmdm.XSZZ_JTQKDC.equalsIgnoreCase(xmdm)) {

			if (!model.isFlg()) {
				zdList = XszzXmdm.XSZZ_JTQK_LIST;
			}
			if (zdList == null || zdList.size() == 0) {
				zdList = getXmZdList(model);
				XszzXmdm.XSZZ_JTQK_LIST = zdList;
			}
		}
		// 困难生
		else if (XszzXmdm.XSZZ_KNS.equalsIgnoreCase(xmdm)) {

			if (!model.isFlg()) {
				zdList = XszzXmdm.XSZZ_KNS_LIST;
			}

			if (zdList == null || zdList.size() == 0) {
				zdList = getXmZdList(model);
				XszzXmdm.XSZZ_KNS_LIST = zdList;
			}
		}
		// 国家助学贷款
		else if (XszzXmdm.XSZZ_GJZXDK.equalsIgnoreCase(xmdm)) {

			if (!model.isFlg()) {
				zdList = XszzXmdm.XSZZ_GJZXDK_LIST;
			}

			if (zdList == null || zdList.size() == 0) {
				zdList = getXmZdList(model);
				XszzXmdm.XSZZ_GJZXDK_LIST = zdList;
			}
		}
		// 国家奖学金
		else if (XszzXmdm.XSZZ_GJJXJ.equalsIgnoreCase(xmdm)) {

			if (!model.isFlg()) {
				zdList = XszzXmdm.XSZZ_GJJXJ_LIST;
			}

			if (zdList == null || zdList.size() == 0) {
				zdList = getXmZdList(model);
				XszzXmdm.XSZZ_GJJXJ_LIST = zdList;
			}
		}
		// 国家励志奖学金
		else if (XszzXmdm.XSZZ_GJLZJXJ.equalsIgnoreCase(xmdm)) {

			if (!model.isFlg()) {
				zdList = XszzXmdm.XSZZ_GJLZJXJ_LIST;
			}

			if (zdList == null || zdList.size() == 0) {
				zdList = getXmZdList(model);
				XszzXmdm.XSZZ_GJLZJXJ_LIST = zdList;
			}
		}
		// 国家助学金
		else if (XszzXmdm.XSZZ_GJZXJ.equalsIgnoreCase(xmdm)) {

			if (!model.isFlg()) {
				zdList = XszzXmdm.XSZZ_GJZXJ_LIST;
			}

			if (zdList == null || zdList.size() == 0) {
				zdList = getXmZdList(model);
				XszzXmdm.XSZZ_GJZXJ_LIST = zdList;
			}
		}
		// 国家助学金（一等）
		else if (XszzXmdm.XSZZ_GJZXJ_LV1.equalsIgnoreCase(xmdm)) {

			if (!model.isFlg()) {
				zdList = XszzXmdm.XSZZ_ZXJLV1_LIST;
			}

			if (zdList == null || zdList.size() == 0) {
				zdList = getXmZdList(model);
				XszzXmdm.XSZZ_ZXJLV1_LIST = zdList;
			}
		}
		// 国家助学金（二等）
		else if (XszzXmdm.XSZZ_GJZXJ_LV2.equalsIgnoreCase(xmdm)) {

			if (!model.isFlg()) {
				zdList = XszzXmdm.XSZZ_ZXJLV2_LIST;
			}

			if (zdList == null || zdList.size() == 0) {
				zdList = getXmZdList(model);
				XszzXmdm.XSZZ_ZXJLV2_LIST = zdList;
			}
		}
		// 国家助学金（三等）
		else if (XszzXmdm.XSZZ_GJZXJ_LV3.equalsIgnoreCase(xmdm)) {

			if (!model.isFlg()) {
				zdList = XszzXmdm.XSZZ_ZXJLV3_LIST;
			}

			if (zdList == null || zdList.size() == 0) {
				zdList = getXmZdList(model);
				XszzXmdm.XSZZ_ZXJLV3_LIST = zdList;
			}
		}
		// 学生缓交学费
		else if (XszzXmdm.XSZZ_XFHJ.equalsIgnoreCase(xmdm)) {

			if (!model.isFlg()) {
				zdList = XszzXmdm.XSZZ_XFHJ_LIST;
			}

			if (zdList == null || zdList.size() == 0) {
				zdList = getXmZdList(model);
				XszzXmdm.XSZZ_XFHJ_LIST = zdList;
			}
		}
		// 绿色通道
		else if (XszzXmdm.XSZZ_LSTD.equalsIgnoreCase(xmdm)) {

			if (!model.isFlg()) {
				zdList = XszzXmdm.XSZZ_LSTD_LIST;
			}

			if (zdList == null || zdList.size() == 0) {
				zdList = getXmZdList(model);
				XszzXmdm.XSZZ_LSTD_LIST = zdList;
			}
		}
		// 校内助学借款
		else if (XszzXmdm.XSZZ_XNZXJK.equalsIgnoreCase(xmdm)) {

			if (!model.isFlg()) {
				zdList = XszzXmdm.XSZZ_XNZXJK_LIST;
			}

			if (zdList == null || zdList.size() == 0) {
				zdList = getXmZdList(model);
				XszzXmdm.XSZZ_XNZXJK_LIST = zdList;
			}
		}
		// 临时困难补助
		else if (XszzXmdm.XSZZ_LSKNBZ.equalsIgnoreCase(xmdm)) {

			if (!model.isFlg()) {
				zdList = XszzXmdm.XSZZ_LSKNBZ_LIST;
			}

			if (zdList == null || zdList.size() == 0) {
				zdList = getXmZdList(model);
				XszzXmdm.XSZZ_LSKNBZ_LIST = zdList;
			}
		}
		// 学费减免
		else if (XszzXmdm.XSZZ_XFJM.equalsIgnoreCase(xmdm)) {

			if (!model.isFlg()) {
				zdList = XszzXmdm.XSZZ_XFJM_LIST;
			}

			if (zdList == null || zdList.size() == 0) {
				zdList = getXmZdList(model);
				XszzXmdm.XSZZ_XFJM_LIST = zdList;
			}
		}
		// 奖助学金申请相关信息
		else if (XszzXmdm.XSZZ_QTXX.equalsIgnoreCase(xmdm)) {

			if (!model.isFlg()) {
				zdList = XszzXmdm.XSZZ_QTXX_LIST;
			}

			if (zdList == null || zdList.size() == 0) {
				zdList = getXmZdList(model);
				XszzXmdm.XSZZ_QTXX_LIST = zdList;
			}
		}
		// 海南大学何康奖学金
		else if (XszzXmdm.XSZZ_HKJXJ.equalsIgnoreCase(xmdm)) {

			if (!model.isFlg()) {
				zdList = XszzXmdm.XSZZ_HKJXJ_LIST;
			}

			if (zdList == null || zdList.size() == 0) {
				zdList = getXmZdList(model);
				XszzXmdm.XSZZ_HKJXJ_LIST = zdList;
			}
		}
		// 海南大学教育发展基金
		else if (XszzXmdm.XSZZ_JXFZJJ.equalsIgnoreCase(xmdm)) {

			if (!model.isFlg()) {
				zdList = XszzXmdm.XSZZ_JXFZJJ_LIST;
			}

			if (zdList == null || zdList.size() == 0) {
				zdList = getXmZdList(model);
				XszzXmdm.XSZZ_JXFZJJ_LIST = zdList;
			}
		}
		// 海南大学中海油田
		else if (XszzXmdm.XSZZ_ZHYD.equalsIgnoreCase(xmdm)) {

			if (!model.isFlg()) {
				zdList = XszzXmdm.XSZZ_ZHYD_LIST;
			}

			if (zdList == null || zdList.size() == 0) {
				zdList = getXmZdList(model);
				XszzXmdm.XSZZ_ZHYD_LIST = zdList;
			}
		}
		// 海南省高校优秀贫困生奖学金
		else if (XszzXmdm.XSZZ_HNSGXYXPKSJXJ.equalsIgnoreCase(xmdm)) {

			if (!model.isFlg()) {
				zdList = XszzXmdm.XSZZ_HNSGXYXPKSJXJ_LIST;
			}

			if (zdList == null || zdList.size() == 0) {
				zdList = getXmZdList(model);
				XszzXmdm.XSZZ_HNSGXYXPKSJXJ_LIST = zdList;
			}
		}
		// 真维斯助学金
		else if (XszzXmdm.XSZZ_ZWSZXJ.equalsIgnoreCase(xmdm)) {

			if (!model.isFlg()) {
				zdList = XszzXmdm.XSZZ_ZWSZXJ_LIST;
			}

			if (zdList == null || zdList.size() == 0) {
				zdList = getXmZdList(model);
				XszzXmdm.XSZZ_ZWSZXJ_LIST = zdList;
			}
		}

		if (zdList == null || zdList.size() == 0) {
			zdList = getXmZdList(model);
		}

		return zdList;
	}

	/**
	 * 综合素质测评
	 * 
	 * @param model
	 * @param resultList
	 * @param zdList
	 */
	private void setZcfInfo(XszzTyForm model,
			List<HashMap<String, Object>> resultList,
			List<HashMap<String, String>> zdList) {
		GuizhdxDAO dao = new GuizhdxDAO();
		String xxdm = Base.xxdm;// 学校代码
		// 获得学生综测信息
		HashMap<String, String> zcMap = new HashMap<String, String>();
		String mk = "zcf";
		if (Globals.XXDM_HAINDX.equalsIgnoreCase(xxdm)) {
			// 海南大学
			zcMap = getXsZhcpInfo(model);// 获取综测测评信息
		} else if (Globals.XXDM_MJXY.equalsIgnoreCase(xxdm)) {
			// 闽江学院
			zcMap.put("xh", model.getXh());
			zcMap.put("xn", Base.currXn);

			List<HashMap<String, String>> zcfList = dao.getZcf(zcMap);
			String zyrs = dao.getZyrs(model.getXh());

			zcMap.put("zyzrs", zyrs);// 同年级、专业总人数
			zcMap.put("cjpm", getCjpm(model.getXh()));// 当年成绩排名
			if (!"0".equals(zyrs)) {
				zcMap.put("cjpmbl", String.valueOf(Math.rint(Double
						.parseDouble(getCjpm(model.getXh()))
						/ Double.parseDouble(zyrs)))
						+ "%");
				for (HashMap<String, String> temp : zcfList) {
					if ("总分".equals(temp.get("mc"))
							&& !Base.isNull(temp.get("pm"))) {
						zcMap.put("zjfpm", temp.get("pm"));
						zcMap.put("zjfpmBl", String.valueOf(Math.rint(Double
								.parseDouble(temp.get("pm"))
								/ Double.parseDouble(zyrs) * 100)
								+ "%"));
					} else if (getZcfTitle()[0].equals(temp.get("mc"))
							&& !Base.isNull(temp.get("pm"))) {
						zcMap.put("dypmBl", String.valueOf(Math.rint(Double
								.parseDouble(temp.get("pm"))
								/ Double.parseDouble(zyrs) * 100)
								+ "%"));
					} else if (getZcfTitle()[1].equals(temp.get("mc"))
							&& !Base.isNull(temp.get("pm"))) {
						zcMap.put("zypmBl", String.valueOf(Math.rint(Double
								.parseDouble(temp.get("pm"))
								/ Double.parseDouble(zyrs) * 100)
								+ "%"));
					} else if (getZcfTitle()[2].equals(temp.get("mc"))
							&& !Base.isNull(temp.get("pm"))) {
						zcMap.put("wtpmBl", String.valueOf(Math.rint(Double
								.parseDouble(temp.get("pm"))
								/ Double.parseDouble(zyrs) * 100)
								+ "%"));
					}
				}
			} else {
				zcMap.put("cjpmbl", "0.0%");
				zcMap.put("zjfpm", "0");
				zcMap.put("zjfpmBl", "0.0%");
				zcMap.put("dypmBl", "0.0%");
				zcMap.put("wtpmBl", "0.0%");
			}
		}

		// 学生相关信息列表
		List<HashMap<String, Object>> xsInfolist = new ArrayList<HashMap<String, Object>>();
		// 占一个单元格的字段
		List<HashMap<String, String>> shortList = new ArrayList<HashMap<String, String>>();
		// 占多个单元格的字段
		List<HashMap<String, String>> longList = new ArrayList<HashMap<String, String>>();

		if (zdList != null && zdList.size() > 0) {

			for (int i = 0; i < zdList.size(); i++) {

				HashMap<String, String> map = zdList.get(i);

				// 来源表
				String lyb = map.get("lyb");
				// 字段
				String zd = map.get("zd");
				// 字段类型
				String zdlx = map.get("zdlx");

				// 学生信息展现内容
				if (mk.equalsIgnoreCase(lyb)) {

					// 字段值
					String zdz = zcMap.get(zd);

					map.put("zdz", zdz);

					// 一个单元格
					if ("short".equalsIgnoreCase(zdlx)) {
						shortList.add(map);
					}
					// 多个单元格
					else if ("long".equalsIgnoreCase(zdlx)) {
						longList.add(map);
					}
				}
			}
		}
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("zdlx", "long");
		map.put("zdList", longList);
		xsInfolist.add(map);

		// 标记最后一个字段的位置
		if (shortList != null && shortList.size() > 0) {

			shortList.get(shortList.size() - 1).put("lastZd", "yes");

			map = new HashMap<String, Object>();
			map.put("zdlx", "short");
			map.put("zdList", shortList);
			xsInfolist.add(map);
		}

		map = new HashMap<String, Object>();
		map.put("mk", mk);
		map.put("nrList", xsInfolist);

		if ((shortList != null && shortList.size() > 0)
				|| (longList != null && longList.size() > 0)) {
			resultList.add(map);
		}
	}

	/**
	 * 困难生信息
	 * 
	 * @param model
	 * @param resultList
	 * @param zdList
	 */
	private void setKnInfo(XszzTyForm model,
			List<HashMap<String, Object>> resultList,
			List<HashMap<String, String>> zdList) {
		// 获得学生困难信息
		HashMap<String, String> knMap = new HashMap<String, String>();
		String mk = "xszz_knsb";
		knMap = getXsKnInfo(model);// 获取困难信息

		// 学生相关信息列表
		List<HashMap<String, Object>> xsInfolist = new ArrayList<HashMap<String, Object>>();
		// 占一个单元格的字段
		List<HashMap<String, String>> shortList = new ArrayList<HashMap<String, String>>();
		// 占多个单元格的字段
		List<HashMap<String, String>> longList = new ArrayList<HashMap<String, String>>();

		if (zdList != null && zdList.size() > 0) {

			for (int i = 0; i < zdList.size(); i++) {

				HashMap<String, String> map = zdList.get(i);

				// 来源表
				String lyb = map.get("lyb");
				// 字段
				String zd = map.get("zd");
				// 字段类型
				String zdlx = map.get("zdlx");

				// 学生信息展现内容
				if (mk.equalsIgnoreCase(lyb)) {

					// 字段值
					String zdz = knMap.get(zd);

					map.put("zdz", zdz);

					// 一个单元格
					if ("short".equalsIgnoreCase(zdlx)) {
						shortList.add(map);
					}
					// 多个单元格
					else if ("long".equalsIgnoreCase(zdlx)) {
						longList.add(map);
					}
				}
			}
		}
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("zdlx", "long");
		map.put("zdList", longList);
		xsInfolist.add(map);

		// 标记最后一个字段的位置
		if (shortList != null && shortList.size() > 0) {

			shortList.get(shortList.size() - 1).put("lastZd", "yes");

			map = new HashMap<String, Object>();
			map.put("zdlx", "short");
			map.put("zdList", shortList);
			xsInfolist.add(map);
		}

		map = new HashMap<String, Object>();
		map.put("mk", mk);
		map.put("nrList", xsInfolist);

		if ((shortList != null && shortList.size() > 0)
				|| (longList != null && longList.size() > 0)) {
			resultList.add(map);
		}
	}

	/**
	 * 设置学生基本信息
	 * 
	 * @author luojw
	 * @param zdList
	 */
	private void setXsInfo(XszzTyForm model,
			List<HashMap<String, Object>> resultList,
			List<HashMap<String, String>> zdList) {

		XsxxglService stuService = new XsxxglService();

		// 模块
		String mk = "view_xsjbxx";
		// 学号
		String xh = model.getXh();
		// 学生基本信息
		HashMap<String, String> stuInfo = stuService.selectStuinfo(xh);
		// 学生家庭信息
		HashMap<String, String> jtInfo = stuService.getStuJtxx(xh);
		// 获得学生住宿信息
		HashMap<String, String> zsInfo = getXsZxInfo(model);

		// 学生相关信息列表
		List<HashMap<String, Object>> xsInfolist = new ArrayList<HashMap<String, Object>>();

		// 占一个单元格的字段
		List<HashMap<String, String>> shortList = new ArrayList<HashMap<String, String>>();
		// 占多个单元格的字段
		List<HashMap<String, String>> longList = new ArrayList<HashMap<String, String>>();

		if (zdList == null || zdList.size() == 0) {
			model.setXmdm("无");
			try {
				zdList = getXmZdList(model);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		for (int i = 0; i < zdList.size(); i++) {

			HashMap<String, String> map = zdList.get(i);

			// 来源表
			String lyb = map.get("lyb");
			// 字段
			String zd = map.get("zd");
			// 字段类型
			String zdlx = map.get("zdlx");

			// 学生信息展现内容
			if (mk.equalsIgnoreCase(lyb)) {

				// 字段值
				String zdz = stuInfo.get(zd);
				zdz = Base.isNull(zdz) ? jtInfo.get(zd) : zdz;
				zdz = Base.isNull(zdz) ? zsInfo.get(zd) : zdz;

				map.put("zdz", zdz);

				// 一个单元格
				if ("short".equalsIgnoreCase(zdlx)) {
					shortList.add(map);
				}
				// 多个单元格
				else if ("long".equalsIgnoreCase(zdlx)) {
					longList.add(map);
				}
			}
		}

		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("zdlx", "long");
		map.put("zdList", longList);
		xsInfolist.add(map);

		// 标记最后一个字段的位置
		if (shortList != null && shortList.size() > 0) {

			for (int i = 0; i < shortList.size(); i++) {
				if (i == shortList.size() - 1) {
					shortList.get(i).put("lastZd", "yes");
				}
			}

			map = new HashMap<String, Object>();
			map.put("zdlx", "short");
			map.put("zdList", shortList);
			xsInfolist.add(map);
		}

		map = new HashMap<String, Object>();
		map.put("mk", mk);
		map.put("nrList", xsInfolist);

		resultList.add(map);
	}

	/**
	 * 设置项目字段信息
	 * 
	 * @author luojw
	 * @param zdList
	 * @throws Exception
	 */
	private void setXmzdInfo(XszzTyForm model,
			List<HashMap<String, Object>> resultList,
			List<HashMap<String, String>> zdList) throws Exception {

		// 模块
		String mk = "xmxgInfo";
		// 项目信息
		HashMap<String, String> wsInfo = setXmzdInfo(model);

		// 家庭相关信息列表
		List<HashMap<String, Object>> wsInfolist = new ArrayList<HashMap<String, Object>>();

		// 占一个单元格的字段
		List<HashMap<String, String>> shortList = new ArrayList<HashMap<String, String>>();

		// 占多个单元格的字段
		List<HashMap<String, String>> longList = new ArrayList<HashMap<String, String>>();

		// 附件
		List<HashMap<String, String>> fileList = new ArrayList<HashMap<String, String>>();

		String xmdm = model.getXmdm();

		boolean flag = true;

		if (zdList != null && zdList.size() > 0) {

			for (int i = 0; i < zdList.size(); i++) {

				HashMap<String, String> map = zdList.get(i);

				// 来源表
				String lyb = map.get("lyb");
				// 字段
				String zd = map.get("zd");
				// 字段类型
				String zdlx = map.get("zdlx");

				// 家庭基本信息展现内容
				if (!"jtqkdcb".equalsIgnoreCase(lyb)
						&& !"view_xsjbxx".equalsIgnoreCase(lyb)
						&& !"zcf".equals(lyb)
						&& !"zzxb".equals(lyb)
						&& !"xsqtxxb".equals(lyb)
						&& (XszzXmdm.XSZZ_KNS.equalsIgnoreCase(xmdm) || !"xszz_knsb"
								.equals(lyb))) {

					// 字段值
					String zdz = wsInfo.get(zd);

					map.put("zdz", zdz);
					map.put("kjlx", zdlx);

					// 一个单元格
					if ("text".equalsIgnoreCase(zdlx)
							|| "select".equalsIgnoreCase(zdlx)) {
						shortList.add(map);
					}// 附件
					else if ("file".equalsIgnoreCase(zdlx) && flag) {
						map = new HashMap<String, String>();
						map.put("filename", wsInfo.get("filename"));
						map.put("filepath", wsInfo.get("filepath"));
						map.put("zdlx", "file");
						fileList.add(map);
						flag = false;
					}
					// 多个单元格
					else if ("textarea".equalsIgnoreCase(zdlx)
							|| "long".equalsIgnoreCase(zdlx)) {
						longList.add(map);
					}
				}
			}
		}

		HashMap<String, Object> map = new HashMap<String, Object>();

		map.put("zdlx", "long");
		map.put("zdList", longList);
		wsInfolist.add(map);

		map = new HashMap<String, Object>();
		map.put("zdlx", "file");
		map.put("zdList", fileList);
		wsInfolist.add(map);

		// 标记最后一个字段的位置
		if (shortList != null && shortList.size() > 0) {

			for (int i = 0; i < shortList.size(); i++) {
				if (i == shortList.size() - 1) {
					shortList.get(i).put("lastZd", "yes");
				}
			}

			map = new HashMap<String, Object>();
			map.put("zdlx", "short");
			map.put("zdList", shortList);
			wsInfolist.add(map);
		}

		map = new HashMap<String, Object>();
		map.put("mk", mk);
		map.put("nrList", wsInfolist);

		resultList.add(map);
	}

	/**
	 * 浙江科技助学贷款
	 * 
	 * @param model
	 * @param resultList
	 * @param zdList
	 * @throws Exception
	 */
	private void setZxdkInfo(XszzTyForm model,
			List<HashMap<String, Object>> resultList,
			List<HashMap<String, String>> zdList) throws Exception {

		// 模块
		String mk = "zzxb";
		HashMap<String, String> zzxbMap = dao.getZxdkInfo(model);
		// 学生相关信息列表
		List<HashMap<String, Object>> xsInfolist = new ArrayList<HashMap<String, Object>>();
		// 占一个单元格的字段
		List<HashMap<String, String>> shortList = new ArrayList<HashMap<String, String>>();
		// 占多个单元格的字段
		List<HashMap<String, String>> longList = new ArrayList<HashMap<String, String>>();

		if (zdList != null && zdList.size() > 0) {

			for (int i = 0; i < zdList.size(); i++) {

				HashMap<String, String> map = zdList.get(i);

				// 来源表
				String lyb = map.get("lyb");
				// 字段
				String zd = map.get("zd");
				// 字段类型
				String zdlx = map.get("zdlx");

				if (mk.equalsIgnoreCase(lyb)) {

					// 字段值
					String zdz = zzxbMap.get(zd);
					map.put("zdz", zdz);
					map.put("kjlx", "text");
					// 一个单元格
					if ("text".equalsIgnoreCase(zdlx)) {
						shortList.add(map);
					}
				}
			}
		}
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("zdlx", "long");
		map.put("zdList", longList);
		xsInfolist.add(map);

		// 标记最后一个字段的位置
		if (shortList != null && shortList.size() > 0) {

			shortList.get(shortList.size() - 1).put("lastZd", "yes");

			map = new HashMap<String, Object>();
			map.put("zdlx", "short");
			map.put("zdList", shortList);
			xsInfolist.add(map);
		}

		map = new HashMap<String, Object>();
		map.put("mk", mk);
		map.put("nrList", xsInfolist);

		if ((shortList != null && shortList.size() > 0)
				|| (longList != null && longList.size() > 0)) {
			resultList.add(map);
		}
	}

	/**
	 * 设置家庭基本情况
	 * 
	 * @author luojw
	 * @param zdList
	 * @throws Exception
	 */
	private void setJtjbInfo(XszzTyForm model,
			List<HashMap<String, Object>> resultList,
			List<HashMap<String, String>> zdList) throws Exception {

		// 模块
//		String mk = "xszz_knsb";
		String mk = "jtqkdcb";

		// 家庭信息
		HashMap<String, String> jtInfo = getJtqkInfo(model);

		// 家庭相关信息列表
		List<HashMap<String, Object>> jtInfolist = new ArrayList<HashMap<String, Object>>();

		// 占一个单元格的字段
		List<HashMap<String, String>> shortList = new ArrayList<HashMap<String, String>>();

		// 占多个单元格的字段
		List<HashMap<String, String>> longList = new ArrayList<HashMap<String, String>>();

		if (zdList == null || zdList.size() == 0) {
			model.setXmdm("无");
			try {
				zdList = getXmZdList(model);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		for (int i = 0; i < zdList.size(); i++) {

			HashMap<String, String> map = zdList.get(i);

			// 来源表
			String lyb = map.get("lyb");
			// 字段
			String zd = map.get("zd");
			// 字段类型
			String zdlx = map.get("zdlx");

			// 家庭基本信息展现内容
			if (mk.equalsIgnoreCase(lyb)) {

				// 字段值
				String zdz = jtInfo.get(zd);

				map.put("zdz", zdz);
				map.put("kjlx", zdlx);

				// 一个单元格
				if ("text".equalsIgnoreCase(zdlx)
						|| "select".equalsIgnoreCase(zdlx)) {
					shortList.add(map);
				}
				// 多个单元格
				else if ("textarea".equalsIgnoreCase(zdlx)
						|| "long".equalsIgnoreCase(zdlx)
						|| "file".equalsIgnoreCase(zdlx)) {
					longList.add(map);
				}
			}
		}

		HashMap<String, Object> map = new HashMap<String, Object>();

		if (longList != null && longList.size() > 0) {
			map.put("zdlx", "long");
			map.put("zdList", longList);
			jtInfolist.add(map);
		}

		// 标记最后一个字段的位置
		if (shortList != null && shortList.size() > 0) {

			for (int i = 0; i < shortList.size(); i++) {
				if (i == shortList.size() - 1) {
					shortList.get(i).put("lastZd", "yes");
				}
			}

			map = new HashMap<String, Object>();
			map.put("zdlx", "short");
			map.put("zdList", shortList);
			jtInfolist.add(map);
		}

		if (jtInfolist != null && jtInfolist.size() > 0) {
			map = new HashMap<String, Object>();
			map.put("mk", mk);
			map.put("nrList", jtInfolist);
		}

		resultList.add(map);
	}

	/**
	 * 设置项目附件情况
	 * 
	 * @author luojw
	 * @param zdList
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	private void setFileInfo(XszzTyForm model,
			List<HashMap<String, Object>> resultList,
			List<HashMap<String, String>> zdList) throws Exception {

		// 模块
		String mk = "file";

		// 家庭相关信息列表
		List<HashMap<String, Object>> fileInfolist = new ArrayList<HashMap<String, Object>>();
		// 占多个单元格的字段
		List<HashMap<String, String>> fileList = new ArrayList<HashMap<String, String>>();

		if (resultList != null && resultList.size() > 0) {

			for (HashMap<String, Object> resMap : resultList) {

				List<HashMap<String, Object>> infoList = (List<HashMap<String, Object>>) resMap
						.get("nrList");

				if (infoList != null && infoList.size() > 0) {

					for (HashMap<String, Object> infoMap : infoList) {

						String zdlx = (String) infoMap.get("zdlx");

						if ("long".equalsIgnoreCase(zdlx)) {

							List<HashMap<String, String>> list = (List<HashMap<String, String>>) infoMap
									.get("zdList");

							if (list != null && list.size() > 0) {

								for (HashMap<String, String> map : list) {

									String lx = map.get("zdlx");

									if ("file".equalsIgnoreCase(lx)) {
										fileList.add(map);
									}
								}
							}
						}
					}
				}
			}
		}

		HashMap<String, Object> map = new HashMap<String, Object>();

		if (fileList != null && fileList.size() > 0) {
			map.put("zdlx", "file");
			map.put("zdList", fileList);
			fileInfolist.add(map);

			map = new HashMap<String, Object>();
			map.put("mk", mk);
			map.put("nrList", fileInfolist);

			resultList.add(map);
		}

	}

	/**
	 * 设置其他信息
	 * 
	 * @author luojw
	 * @param zdList
	 * @throws Exception
	 */
	private void setQtInfo(XszzTyForm model,
			List<HashMap<String, Object>> resultList,
			List<HashMap<String, String>> zdList) throws Exception {

		// 模块
		String mk = "xsqtxxb";

		// 家庭信息
		HashMap<String, String> qtInfo = getQtInfo(model);

		// 家庭相关信息列表
		List<HashMap<String, Object>> qtInfolist = new ArrayList<HashMap<String, Object>>();

		// 占一个单元格的字段
		List<HashMap<String, String>> shortList = new ArrayList<HashMap<String, String>>();

		// 占多个单元格的字段
		List<HashMap<String, String>> longList = new ArrayList<HashMap<String, String>>();

		if (zdList == null || zdList.size() == 0) {
			model.setXmdm("无");
			try {
				zdList = getXmZdList(model);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		for (int i = 0; i < zdList.size(); i++) {

			HashMap<String, String> map = zdList.get(i);

			// 来源表
			String lyb = map.get("lyb");
			// 字段
			String zd = map.get("zd");
			// 字段类型
			String zdlx = map.get("zdlx");

			// 家庭基本信息展现内容
			if (mk.equalsIgnoreCase(lyb)) {

				// 字段值
				String zdz = qtInfo.get(zd);

				map.put("zdz", zdz);
				map.put("kjlx", zdlx);

				// 一个单元格
				if ("text".equalsIgnoreCase(zdlx)
						|| "select".equalsIgnoreCase(zdlx)) {
					shortList.add(map);
				}
				// 多个单元格
				else if ("textarea".equalsIgnoreCase(zdlx)
						|| "long".equalsIgnoreCase(zdlx)) {
					longList.add(map);
				}
			}
		}

		HashMap<String, Object> map = new HashMap<String, Object>();

		if (longList != null && longList.size() > 0) {
			map.put("zdlx", "long");
			map.put("zdList", longList);
			qtInfolist.add(map);
		}

		// 标记最后一个字段的位置
		if (shortList != null && shortList.size() > 0) {

			for (int i = 0; i < shortList.size(); i++) {
				if (i == shortList.size() - 1) {
					shortList.get(i).put("lastZd", "yes");
				}
			}

			map = new HashMap<String, Object>();
			map.put("zdlx", "short");
			map.put("zdList", shortList);
			qtInfolist.add(map);
		}

		if (qtInfolist != null && qtInfolist.size() > 0) {
			map = new HashMap<String, Object>();
			map.put("mk", mk);
			map.put("nrList", qtInfolist);
		}

		resultList.add(map);
	}

	/**
	 * 获得项目申请字段列表
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getXmZdList(XszzTyForm model)
			throws Exception {

		String tableName = "xszz_xmnrzdb";
		String xmdm = model.getXmdm();
		String mrxm = model.getMrxm();

		String[] colList = getTableZd(tableName);
		String[] queryList = new String[] { "xmdm" };
		String[] queryLikeList = new String[] {};

		if ("否".equalsIgnoreCase(mrxm)) {
			model.setXmdm("无");
		}

		MakeQuery myQuery = new MakeQuery();
		myQuery.makeQuery(queryList, queryLikeList, model);

		// 查询条件
		StringBuilder query = new StringBuilder();
		query.append(myQuery.getQueryString());
		query.append(" order by lyb,zdlx,to_number(zdsx)");

		List<HashMap<String, String>> zdList = CommonQueryDAO
				.commonQueryforList(tableName, query.toString(), myQuery
						.getInputList(), colList, "");

		model.setXmdm(xmdm);

		return zdList;
	}

	/**
	 * 
	 * 获得学生住宿信息
	 * 
	 * @author luojw
	 */
	public HashMap<String, String> getXsZxInfo(XszzTyForm model) {

		String tableName = "view_xszsxx";

		String[] colList = new String[] { "xqmc", "ssbh", "qsdh" };

		HashMap<String, String> map = CommonQueryDAO.commonQueryOne(tableName,
				colList, "xh", model.getXh());

		return map;
	}

	/**
	 * 
	 * 获得综合测评信息
	 * 
	 * @author lr
	 */
	public HashMap<String, String> getXsZhcpInfo(XszzTyForm model) {
		HashMap<String, String> map = new HashMap<String, String>();
		String tableName = "view_hndx_zhcp";

		String[] colList = new String[] { "zhcppm", "fsbjpm", "fsnjpm",
				"fsnjzypm" };
		model.setXn(StringUtils.isNull(model.getXn()) ? Base.currXn : model
				.getXn());
		map = CommonQueryDAO.commonQueryOne(tableName, colList, "xh||xn", model
				.getXh()
				+ model.getXn());
		return map;
	}

	/**
	 * 
	 * 获得困难生信息
	 * 
	 * @author lr
	 */
	public HashMap<String, String> getXsKnInfo(XszzTyForm model) {
		DAO dao = DAO.getInstance();
		HashMap<String, String> map = new HashMap<String, String>();
		String tableName = "xszz_knsb";
		String pk = "xh";
		String pkValue = model.getXh();
		String[] colList = new String[] { "xmzzjb" };

		// 项目申请周期
		String xmsqzq = dao.getOneValue("xszz_zzxmb", "sqzq", "xmdm",
				XszzXmdm.XSZZ_KNS);
		if ("年度".equalsIgnoreCase(xmsqzq)) {
			pk += "||nd";
			model.setNd(StringUtils.isNull(model.getNd()) ? Base.currNd : model
					.getNd());
			pkValue += model.getNd();
		} else if ("学年".equalsIgnoreCase(xmsqzq)) {
			pk += "||xn";
			model.setXn(StringUtils.isNull(model.getXn()) ? Base.currXn : model
					.getXn());
			pkValue += model.getXn();
		} else if ("学期".equalsIgnoreCase(xmsqzq)) {
			pk += "||xn||xq";
			model.setXn(StringUtils.isNull(model.getXn()) ? Base.currXn : model
					.getXn());
			model.setXq(StringUtils.isNull(model.getXq()) ? Base.currXq : model
					.getXq());
			pkValue += model.getXn() + model.getXq();
		}

		String query = getShtgtj(model.getXmdm()).toString();
		query += " order by sqsj ";

		map = CommonQueryDAO.commonQueryOne2(tableName, colList, pk, pkValue,
				query);
		return map;
	}

	/**
	 * 获取审核通过条件
	 * 
	 * @param xmdm
	 * @return StringBuilder
	 */
	private StringBuilder getShtgtj(String xmdm) {
		DAO dao = DAO.getInstance();
		StringBuilder sb = new StringBuilder();
		String shjb = dao.getOneValue("xszz_zzxmb", "shjb", "xmdm", xmdm);

		if ("一级审核".equalsIgnoreCase(shjb)) {
			sb.append(" and shzt1='通过'");
		} else if ("二级审核".equalsIgnoreCase(shjb)) {
			sb.append(" and shzt2='通过'");
		} else if ("三级审核".equalsIgnoreCase(shjb)) {
			sb.append(" and shzt3='通过'");
		}
		return sb;
	}

	/**
	 * 获得学生家庭情况调查信息
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public HashMap<String, String> getJtqkInfo(XszzTyForm model)
			throws Exception {

		DAO dao = DAO.getInstance();

		String tableName = "jtqkdcb";
		String xmdm = model.getXmdm();
		String xh = model.getXh();
		String[] colList = dao.getRs(
				"select * from xszz_xmnrzdb where xmdm = ? and lyb = ?",
				new String[] { xmdm, tableName }, "zd");// getTableZd(tableName);
		String query = " and sqsj = (select max(sqsj) from " + tableName
				+ " where xh = '" + xh + "')";

		HashMap<String, String> map = new HashMap<String, String>();
		 if(colList.length>0){
			map = CommonQueryDAO.commonQueryOne2(tableName, colList, "xh", xh,
					query);
		 }else{
			 xmdm="无";
			 
			 colList = dao.getRs(
						"select * from xszz_xmnrzdb where xmdm = ? and lyb = ?",
						new String[] { xmdm, tableName }, "zd");// getTableZd(tableName);
			 map = CommonQueryDAO.commonQueryOne2(tableName, colList, "xh", xh,
						query);
		 }
		 //湖州师范学院
		 if(Base.xxdm.equals(Globals.XXDM_HZSFXY)){
			 //民政部门通讯地址
			if(null!=map.get("kzzd2") && !"".equals(map.get("kzzd2"))){
				map.put("mzbmtxdz", map.get("kzzd2"));
			}
			//民政部门邮政编码
			if(null!=map.get("kzzd3") && !"".equals(map.get("kzzd3"))){
				map.put("mzbmyzbm", map.get("kzzd3"));
			}
			//民政部门联系电话
			if(null!=map.get("kzzd4") && !"".equals(map.get("kzzd4"))){
				map.put("mzbmlxdh", map.get("kzzd4"));
			}
			//上年家庭收入
			if(null!=map.get("kzzd5") && !"".equals(map.get("kzzd5"))){
				map.put("snjtsr", map.get("kzzd5"));
			}
		 }
		return map;
	}

	/**
	 * 获得学生其他信息
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public HashMap<String, String> getQtInfo(XszzTyForm model) throws Exception {
		
		DAO dao = DAO.getInstance();
		String tableName = "xsqtxxb";
		String xmdm = model.getXmdm();
		String xh = model.getXh();
		String[] colList =  dao.getRs(
				"select * from xszz_xmnrzdb where (xmdm = ? or xmdm='无') and lyb = ?",
				new String[] { xmdm, tableName }, "zd");//getTableZd(tableName);
		String query = " and sqsj = (select max(sqsj) from " + tableName
				+ " where xh = '" + xh + "')";

		HashMap<String, String> map = new HashMap<String,String>();
		if (colList != null && colList.length > 0) {
			map = CommonQueryDAO.commonQueryOne2(tableName, colList, "xh", xh,
					query);
		}

		return map;
	}

	/**
	 * 获得困难生基本信息
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public HashMap<String, String> getKnsInfo(XszzTyForm model)
			throws Exception {

		String tableName = "xszz_knsb";
		String xh = model.getXh();
		String[] colList = getTableZd(tableName);
		String query = " and sqsj = (select max(sqsj) from " + tableName
				+ " where xh = '" + xh + "')";

		HashMap<String, String> map = CommonQueryDAO.commonQueryOne2(tableName,
				colList, "xh", xh, query);

		return map;
	}

	/**
	 * 获得项目基本信息
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public HashMap<String, String> getXmInfo(XszzTyForm model) throws Exception {

		String tableName = model.getXmb();

		// 项目代码
		String xmdm = model.getXmdm();
		// 学号
		String xh = model.getXh();
		// 申请时间
		String sqsj = model.getSqsj();

		String[] colList = getTableZd(tableName);
		String query = "";

		HashMap<String, String> map = CommonQueryDAO.commonQueryOne2(tableName,
				colList, "xmdm||xh||sqsj", xmdm + xh + sqsj, query);

		return map;
	}

	/**
	 * 获得项目字段信息
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public HashMap<String, String> setXmzdInfo(XszzTyForm model)
			throws Exception {

		String tableName = model.getXmb();
		// 项目代码
		String xmdm = model.getXmdm();
		// 学号
		String xh = model.getXh();
		// 申请时间
		String sqsj = model.getSqsj();

		String[] colList = getTableZd(tableName);

		HashMap<String, String> map = CommonQueryDAO.commonQueryOne(tableName,
				colList, "xmdm||xh||sqsj", xmdm + xh + sqsj);

		return map;
	}

	/**
	 * 获得项目分级情况列表
	 * 
	 * @author luojw
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public List<HashMap<String, String>> getXmfjqkList(XszzTyForm model)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		String tableName = "xszz_xmfjqkb";

		String[] colList = new String[] { "fjmc", "fjxxje", "fjsxje", "fjqdje" };
		String[] queryList = new String[] { "xmdm" };
		String[] queryLikeList = new String[] {};

		MakeQuery myQuery = new MakeQuery();
		myQuery.makeQuery(queryList, queryLikeList, model);

		// 查询条件
		StringBuilder query = new StringBuilder();
		query.append(myQuery.getQueryString());
		query.append(" order by fjxxje, fjsxje, fjqdje");

		List<HashMap<String, String>> list = CommonQueryDAO.commonQueryforList(
				tableName, query.toString(), myQuery.getInputList(), colList,
				"");

		return list;
	}
	
	/**
	 * 获得附件的路劲
	 * 
	 * @author luojw
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public String getFile(XszzTyForm model)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		DAO dao = DAO.getInstance();
		String sql = "select scdz from xszz_knsb where xh= ? and sqsj = ?";
		String  filePath = dao.getOneRs(sql, new String[]{model.getXh(),model.getSqsj()}, "scdz");
		return filePath;
	}

	/**
	 * 获得项目审核列表
	 * 
	 * @author luojw
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public List<HashMap<String, String>> getXmshList(XszzTyForm model)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		// 所有项目列表
		List<HashMap<String, String>> xmList = getShXmList(model);

		// 移除不需要审核的项目
		List<HashMap<String, String>> xhxmlist = removeShtjXm(model, xmList);

		xhxmlist = getXmxgRsList(xhxmlist, model);

		return xhxmlist;
	}

	/**
	 * 获得所有项目列表
	 * 
	 * @author luojw
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public List<HashMap<String, String>> getShXmList(XszzTyForm model)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		String tableName = "view_xszz_comm_xmwh";
		String mklx = model.getMklx();

		if (!Base.isNull(mklx)) {
			tableName = "pj".equalsIgnoreCase(mklx) ? "xg_view_xszz_pjpy_xmwh"
					: "xg_view_xszz_xmwh";
		}

		String[] colList = new String[] { "pk", "xmdm", "xmmc", "sqzq", "xmsm",
				"xmb", "rskz", "kzjb", "shjb", "bzrsh", "fdysh", "xysh",
				"xxsh", "rssx", "kgzt", "xmlb", "pdsj" };
		String[] queryList = new String[] { "xmlb" };
		String[] queryLikeList = new String[] { "xmmc" };

		MakeQuery myQuery = new MakeQuery();
		myQuery.makeQuery(queryList, queryLikeList, model);

		// 查询条件
		StringBuilder query = new StringBuilder();

		query.append(" where kgzt <> '项目关闭'");
		query.append(getFlowControlSql(model));//困难生认定作为独立模块过滤
		// -----edit quph 2010.9.19--begin--------
		// 宁波城市国家助学贷款（审核、结果查询有单独模块！）
		if (Globals.XXDM_NBCSZYJSXY.equals(Base.xxdm)) {
			query.append(" and xmdm <> '");
			query.append(XszzXmdm.XSZZ_GJZXDK);
			query.append("' ");
		}
		
		if(!Base.isNull(model.getXmlb())){
			query.append(" and xmlb = '");
			query.append(model.getXmlb());
			query.append("' ");
		}
		// -----------end---------
		query.append(" order by mrxm desc,to_number(xssx)");
		List<HashMap<String, String>> allXmList = CommonQueryDAO
				.commonQueryforList(tableName, query.toString(),
						new String[] {}, colList, "");

		return allXmList;
	}

	/**
	 * 移除不满足审核条件的项目
	 * 
	 * @param xh
	 * @param xmTjList
	 * @param xqxmlist
	 */
	private List<HashMap<String, String>> removeShtjXm(XszzTyForm model,
			List<HashMap<String, String>> xmList) {

		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		// 登陆用户身份
		String lx = model.getLx();

		if (xmList != null && xmList.size() > 0) {

			for (int i = 0; i < xmList.size(); i++) {

				HashMap<String, String> xmInfo = xmList.get(i);

				// 开关状态
				String xmdm = xmInfo.get("xmdm");
				// 开关状态
				String kgzt = xmInfo.get("kgzt");
				// 审核级别
				String shjb = xmInfo.get("shjb");
				// 用户类型
				xmInfo.put("lx", lx);

				// 不可审核项目关闭的项目
				if ("项目关闭".equalsIgnoreCase(kgzt)) {
					continue;
				}
				// 不可审核无需审核的项目
				if ("无需审核".equalsIgnoreCase(shjb)) {
					continue;
				}
				String[] jb = new String[3];
				String[] jbmc = new String[3];
				getXmShjb(xmInfo, jb, jbmc);

				// 判断用户是否有资格审核
				boolean flag = false;

				if (jb != null && jb.length > 0) {

					for (int j = 0; j < jb.length; j++) {

						String[] arr_jb = jb[j].split("-");

						if (arr_jb != null && arr_jb.length > 0) {

							for (int k = 0; k < arr_jb.length; k++) {

								if ((lx + "sh").equalsIgnoreCase(arr_jb[k])) {
									flag = true;
								} else if ("jd".equalsIgnoreCase(lx)
										&& ("bzrsh".equalsIgnoreCase(arr_jb[k]) || "fdysh"
												.equalsIgnoreCase(arr_jb[k]))) {
									flag = true;
								}
							}
						}

					}
				}

				if (flag) {
					xmInfo.put("tjjbOne", jb[0]);
					xmInfo.put("tjjbTwo", jb[1]);
					xmInfo.put("tjjbThr", jb[2]);
					list.add(xmInfo);
				}
			}
		}

		return list;
	}

	/**
	 * 获得项目审核级别
	 * 
	 * @param xh
	 * @param xmTjList
	 * @param xqxmlist
	 */
	public String[] getXmShjb(HashMap<String, String> map, String[] jb,
			String[] jbmc) {

		// 审核级别
		String shjb = map.get("shjb");
		// 班主任审核
		String bzrsh = map.get("bzrsh");
		// 辅导员审核
		String fdysh = map.get("fdysh");
		// 学院审核
		String xysh = map.get("xysh");
		// 学校审核
		String xxsh = map.get("xxsh");
		// 用户类型
		String lx = map.get("lx");

		boolean flag = false;

		int n = 0;

		// 统计级别1
		String tjjbOne = "";
		String mcOne = "";
		// 统计级别2
		String tjjbTwo = "";
		String mcTwo = "";
		// 统计级别3
		String tjjbThr = "";
		String mcThr = "";

		// 判断用户是否有审核权限
		if ("是".equalsIgnoreCase(xxsh)) {
			n++;
			if (!flag) {
				flag = "xx".equalsIgnoreCase(lx) ? true : false;
			}
			if (flag) {
				tjjbOne = "xxsh";
			}
		}
		if ("是".equalsIgnoreCase(xysh)) {
			n++;
			if (!flag) {
				flag = "xy".equalsIgnoreCase(lx) ? true : false;
			}
			if (flag) {
				tjjbOne = "xysh";
			}
		}

		if ("是".equalsIgnoreCase(fdysh)) {
			n++;
			if (!flag) {
				flag = ("fdy".equalsIgnoreCase(lx) || "jd".equalsIgnoreCase(lx)) ? true
						: false;
			}
			if (flag) {
				tjjbOne = "fdysh";
			}
		}
		if ("是".equalsIgnoreCase(bzrsh)) {
			n++;
			if (!flag) {
				flag = ("bzr".equalsIgnoreCase(lx) || "jd".equalsIgnoreCase(lx)) ? true
						: false;
			}
			if (flag) {
				tjjbOne = "bzrsh";
			}
		}

		// 一级审核的情况
		if ("一级审核".equalsIgnoreCase(shjb)) {
			flag = true;
			// 未定义哪级来审核，默认 谁都可以
			if (Base.isNull(bzrsh) && Base.isNull(fdysh) && Base.isNull(xysh)
					&& Base.isNull(xxsh)) {
				tjjbOne = "bzrsh-fdysh-xysh-xxsh";
			} else {

			}
			mcOne = "";
		}
		// 两级审核的情况
		if ("两级审核".equalsIgnoreCase(shjb)) {
			// 未具体定义两级
			if (n != 2) {
				// 未定义哪两级来审核，默认 学院 --> 学校
				if (Base.isNull(bzrsh) && Base.isNull(fdysh)
						&& Base.isNull(xysh) && Base.isNull(xxsh)) {
					if (!flag) {
						flag = ("xx".equalsIgnoreCase(lx) || "xy"
								.equalsIgnoreCase(lx)) ? true : false;
					}
					if (flag) {
						tjjbOne = "xysh";
						mcOne = "学院";
						tjjbTwo = "xxsh";
						mcTwo = "学校";

					}
				}
				// 只定义了学院审核，第二级默认为学校
				if ("是".equalsIgnoreCase(xysh) && Base.isNull(xxsh)) {
					if (!flag) {
						flag = "xx".equalsIgnoreCase(lx) ? true : false;
					}
					if (flag) {
						tjjbOne = "xysh";
						mcOne = "学院";
						tjjbTwo = "xxsh";
						mcTwo = "学校";
					}
				}
				// 只定义了班级审核，第二级学院或者学校
				if (("是".equalsIgnoreCase(bzrsh) || "是".equalsIgnoreCase(fdysh))
						&& Base.isNull(xysh) && Base.isNull(xxsh)) {
					if (!flag) {
						flag = ("xx".equalsIgnoreCase(lx) || "xy"
								.equalsIgnoreCase(lx)) ? true : false;
					}
					if (flag) {
						tjjbOne = "bzrsh-fdysh";
						mcOne = "班主任，辅导员";
						tjjbTwo = "xysh-xxsh";
						mcTwo = "学院，学校";
					}
				}
				// 只定义了学校审核，第一级学院或者班主任，辅导员皆可
				if ("是".equalsIgnoreCase(xxsh) && Base.isNull(bzrsh)
						&& Base.isNull(fdysh) && Base.isNull(xysh)) {
					if (!flag) {
						flag = ("xy".equalsIgnoreCase(lx)
								|| "fdy".equalsIgnoreCase(lx)
								|| "bzr".equalsIgnoreCase(lx) || "jd"
								.equalsIgnoreCase(lx)) ? true : false;
					}
					if (flag) {
						tjjbOne = "bzrsh-fdysh-xysh";
						mcOne = "班主任，辅导员, 学院";
						tjjbTwo = "xxsh";
						mcTwo = "学校";
					}
				}
			} else {// 具体定义了哪两级
				// 定义了班级审核
				if ("是".equalsIgnoreCase(bzrsh) || "是".equalsIgnoreCase(fdysh)) {

					tjjbOne = "bzrsh-fdysh";
					mcOne = "班主任，辅导员";
					if ("是".equalsIgnoreCase(xysh)) {
						tjjbTwo = "xysh";
						mcTwo = "学院";
					} else {
						tjjbTwo = "xxsh";
						mcTwo = "学校";
					}
				} else {
					tjjbOne = "xysh";
					mcOne = "学院";
					tjjbTwo = "xxsh";
					mcTwo = "学校";
				}
			}
		}

		// 三级审核的情况
		if ("三级审核".equalsIgnoreCase(shjb)) {

			if ("是".equalsIgnoreCase(bzrsh)) {
				tjjbOne = "bzrsh";
				mcOne = "班主任";
			} else {
				tjjbOne = "fdysh";
				mcOne = "辅导员";
			}

			tjjbTwo = "xysh";
			mcTwo = "学院";
			tjjbThr = "xxsh";
			mcThr = "学校";
		}

		jb[0] = tjjbOne;
		jb[1] = tjjbTwo;
		jb[2] = tjjbThr;

		jbmc[0] = mcOne;
		jbmc[1] = mcTwo;
		jbmc[2] = mcThr;

		return jb;
	}

	/**
	 * 获得项目相关人数
	 * 
	 * @param xh
	 * @param xmTjList
	 * @param xqxmlist
	 */
	public List<HashMap<String, String>> getXmxgRsList(
			List<HashMap<String, String>> xhxmlist, XszzTyForm model) {

		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		if (xhxmlist != null && xhxmlist.size() > 0) {

			// 项目代码
			String[] xmdm = new String[xhxmlist.size()];
			// 项目类别
			String[] xmlb = new String[xhxmlist.size()];
			// 项目名称
			String[] xmmc = new String[xhxmlist.size()];
			// 所有项目表
			String[] xmb = new String[xhxmlist.size()];
			// 统计级别1
			String[] tjjbOne = new String[xhxmlist.size()];
			// 统计级别2
			String[] tjjbTwo = new String[xhxmlist.size()];
			// 统计级别3
			String[] tjjbThr = new String[xhxmlist.size()];
			// 审核级别
			String[] shjb = new String[xhxmlist.size()];
			// 申请周期
			String[] sqzq = new String[xhxmlist.size()];
			// 评定时间
			String[] pdsj = new String[xhxmlist.size()];

			for (int i = 0; i < xhxmlist.size(); i++) {

				HashMap<String, String> xmInfo = xhxmlist.get(i);

				xmdm[i] = xmInfo.get("xmdm");
				xmlb[i] = xmInfo.get("xmlb");
				xmmc[i] = xmInfo.get("xmmc");
				xmb[i] = xmInfo.get("xmb");
				sqzq[i] = xmInfo.get("sqzq");
				shjb[i] = xmInfo.get("shjb");
				pdsj[i] = xmInfo.get("pdsj");
				tjjbOne[i] = xmInfo.get("tjjbOne");
				tjjbTwo[i] = xmInfo.get("tjjbTwo");
				tjjbThr[i] = xmInfo.get("tjjbThr");
			}

			list = dao.getXmSqShList(model, xmdm, xmlb, xmmc, xmb, sqzq, shjb,
					pdsj, tjjbOne, tjjbTwo, tjjbThr);
		}

		return list;
	}

	/**
	 * 获得登陆用户有资格审核的学生列表
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getXsShList(XszzTyForm model,
			HashMap<String, String> map) {

		// 用户类型
		String lx = model.getLx();
		map.put("lx", lx);
		// 审核级别
		String[] jb = new String[3];
		String[] jbmc = new String[3];
		getXmShjb(map, jb, jbmc);
		model.setJb(jb);
		model.setJbmc(jbmc);

		List<HashMap<String, String>> list = null;

		if (jb != null && jb.length == 3) {
			list = dao.getXsShList(model, map);
		}

		return getResultList(list, model);
	}

	/**
	 * 获得项目申请内容列表
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public List<HashMap<String, Object>> getXmShNrList(XszzTyForm model,
			HashMap<String, String> xmInfo) throws Exception {

		String mklx = model.getMklx();

		String xxdm = Base.xxdm;
		// 项目代码
		String xmdm = model.getXmdm();
		// 项目表
		String xmb = model.getXmb();

		// 字段列表
		List<HashMap<String, Object>> resultList = new ArrayList<HashMap<String, Object>>();
		// 字段列表
		List<HashMap<String, String>> zdList = getXmZdList(model);
		// 设置学生基本信息
		setXsInfo(model, resultList, zdList);
		// 设置家庭基本情况
		setJtjbInfo(model, resultList, zdList);
		// 设置综测信息
		setZcfInfo(model, resultList, zdList);
		// 困难信息
		setKnInfo(model, resultList, zdList);

		//if (Globals.XXDM_ZGDZDX.equalsIgnoreCase(xxdm)||Globals.XXDM_TJGYDX.equalsIgnoreCase(xxdm)) {
			// 设置其他信息
			setQtInfo(model, resultList, zdList);
		//}

		if (!"jtqkdcb".equalsIgnoreCase(xmb)) {
			// 设置项目基本情况
			setXmzdInfo(model, resultList, zdList);
		}

		// 浙江科技助学贷款
		if (Globals.XXDM_ZJKJXY.equalsIgnoreCase(xxdm)
				&& "gjzxdkb".equalsIgnoreCase(xmb)) {
			setZxdkInfo(model, resultList, zdList);
		}

		model.setXmdm(xmdm);
		// 设置审核基本情况
		if ("jg".equalsIgnoreCase(mklx)) {
			setJgInfo(model, resultList, xmInfo);
		} else {
			setShInfo(model, resultList, xmInfo);
		}

		return resultList;
	}

	/**
	 * 设置审核基本情况
	 * 
	 * @author luojw
	 * @param zdList
	 */
	private void setShInfo(XszzTyForm model,
			List<HashMap<String, Object>> resultList,
			HashMap<String, String> xmInfo) {

		// 模块
		String mk = "shInfo";
		// 用户类型
		String lx = model.getLx();
		xmInfo.put("lx", lx);
		// 审核级别
		String[] jb = new String[3];
		String[] jbmc = new String[3];
		getXmShjb(xmInfo, jb, jbmc);
		model.setJb(jb);
		model.setJbmc(jbmc);

		HashMap<String, Object> map = new HashMap<String, Object>();

		List<HashMap<String, String>> shInfoList = dao.getShInfoList(model,
				xmInfo);

		if (shInfoList != null && shInfoList.size() > 0) {
			map = new HashMap<String, Object>();
			map.put("mk", mk);
			map.put("nrList", shInfoList);
		}

		resultList.add(map);
	}

	/**
	 * 设置审核基本情况
	 * 
	 * @author luojw
	 * @param zdList
	 */
	private void setJgInfo(XszzTyForm model,
			List<HashMap<String, Object>> resultList,
			HashMap<String, String> xmInfo) {

		// 模块
		String mk = "shInfo";

		HashMap<String, Object> map = new HashMap<String, Object>();

		List<HashMap<String, String>> shInfoList = dao.getJgInfoList(model,
				xmInfo);

		if (shInfoList != null && shInfoList.size() > 0) {
			map = new HashMap<String, Object>();
			map.put("mk", mk);
			map.put("nrList", shInfoList);
		}

		resultList.add(map);
	}

	/**
	 * 批量审核
	 * 
	 * @author luojw
	 * @param zdList
	 * @throws Exception
	 */
	public boolean savePlsh(XszzTyForm model, String tableName,
			HashMap<String, String> map) throws Exception {

		String[] zd = null;

		if (!map.isEmpty()) {

			zd = new String[map.size()];

			int n = 0;

			for (String key : map.keySet()) {

				if (!Base.isNull(map.get(key)) && !"sqsj".equalsIgnoreCase(key)) {

					zd[n] = key;

					n++;
				}

			}
		}

		int n = 0;

		for (int i = 0; i < zd.length; i++) {
			if (!Base.isNull(zd[i])) {
				n++;
			}
		}

		String[] onezd = new String[n];

		for (int i = 0; i < onezd.length; i++) {

			onezd[i] = zd[i];
			if ("xmzzje".equalsIgnoreCase(onezd[i])) {
				model.setXmzzje(map.get("xmzzje"));
			}
			if ("xmzzjb".equalsIgnoreCase(onezd[i])) {
				model.setXmzzjb(map.get("xmzzjb"));
			}
			if ("bzrsh".equalsIgnoreCase(onezd[i])) {
				model.setBzrsh(map.get("bzrsh"));
			}
			if ("fdysh".equalsIgnoreCase(onezd[i])) {
				model.setFdysh(map.get("fdysh"));
			}
			if ("xysh".equalsIgnoreCase(onezd[i])) {
				model.setXysh(map.get("xysh"));
			}
			if ("xxsh".equalsIgnoreCase(onezd[i])) {
				model.setXxsh(map.get("xxsh"));
			}
			if ("shzt1".equalsIgnoreCase(onezd[i])) {
				model.setShzt1(map.get("shzt1"));
			}
			if ("shzt1yj".equalsIgnoreCase(onezd[i])) {
				model.setShzt1yj(map.get("shzt1yj"));
			}
			if ("shzt2".equalsIgnoreCase(onezd[i])) {
				model.setShzt2(map.get("shzt2"));
			}
			if ("shzt2yj".equalsIgnoreCase(onezd[i])) {
				model.setShzt2yj(map.get("shzt2yj"));
			}
			if ("shzt3".equalsIgnoreCase(onezd[i])) {
				model.setShzt3(map.get("shzt3"));
			}
			if ("shzt3yj".equalsIgnoreCase(onezd[i])) {
				model.setShzt3yj(map.get("shzt3yj"));
			}
			
			if ("shsj1".equalsIgnoreCase(onezd[i])) {
				model.setShsj1(map.get("shsj1"));
			}
			if ("shsj2".equalsIgnoreCase(onezd[i])) {
				model.setShsj2(map.get("shsj2"));
			}
			if ("shsj3".equalsIgnoreCase(onezd[i])) {
				model.setShsj3(map.get("shsj3"));
			}
		}

		String pk = "xh||sqsj||xmdm";
		String[] pkValue = null;
		String shpk = model.getShpk();

		if (!Base.isNull(shpk)) {

			pkValue = new String[shpk.length()];

			String[] arr_shpk = shpk.split("!!@@!!");

			for (int i = 0; i < arr_shpk.length; i++) {
				pkValue[i] = arr_shpk[i];
			}
		}

		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(tableName);
		saveForm.setPk(pk);
		saveForm.setPkValue(pkValue);
		saveForm.setOnezd(onezd);

		return updateXszz(saveForm, model);
	}

	/**
	 * 获得结果查询列表
	 * 
	 * @author luojw
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public List<HashMap<String, String>> getJgcxList(XszzTyForm model)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		// 所有项目列表
		List<HashMap<String, String>> allXmList = getShXmList(model);

		String[] xmb = null;

		if (allXmList != null && allXmList.size() > 0) {

			xmb = new String[allXmList.size()];

			for (int i = 0; i < allXmList.size(); i++) {
				xmb[i] = allXmList.get(i).get("xmb");
			}
		}

		// 申请情况列表
		List<HashMap<String, String>> jgList = dao.getXmshJgList(model, xmb);

		if (allXmList != null && allXmList.size() > 0) {

			for (int i = 0; i < allXmList.size(); i++) {

				HashMap<String, String> xmInfo = allXmList.get(i);

				String shjb = xmInfo.get("shjb");

				if (jgList != null && jgList.size() > 0) {

					for (int j = 0; j < jgList.size(); j++) {

						HashMap<String, String> jgInfo = jgList.get(j);

						String shzt1 = jgInfo.get("shzt1");
						String shzt2 = jgInfo.get("shzt2");
						String shzt3 = jgInfo.get("shzt3");

						if (xmInfo.get("xmdm").equalsIgnoreCase(
								jgInfo.get("xmdm"))) {

							jgInfo.put("shjb", shjb);

							if ("未审核".equalsIgnoreCase(shzt1)
									&& "未审核".equalsIgnoreCase(shzt2)
									&& "未审核".equalsIgnoreCase(shzt3)) {
								jgInfo.put("delete", "yes");
							} else {
								jgInfo.put("delete", "no");
							}

							// list.add(jgInfo);
						}
					}

				}
			}
		}
		return jgList;
	}
	
	

	/**
	 * 资助续办查询列表
	 * 
	 * @author qph
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public List<HashMap<String, String>> getZzxbList(XszzTyForm model)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		return dao.getZzxmList(model);
	}

	/**
	 * 保存家庭成员
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public String saveJtcy(XszzTyForm model) throws Exception {

		String tableName = "xszz_jtcyb";

		String pk = "xh";

		String[] zd = getTableZd(tableName);

		int n = 0;

		// 构建需要保存的数组
		for (int i = 0; i < zd.length; i++) {
			if (!"id".equalsIgnoreCase(zd[i]) && !"xh".equalsIgnoreCase(zd[i])) {
				String methodName = "get" + zd[i].substring(0, 1).toUpperCase()
						+ zd[i].substring(1);

				String[] arr = (String[]) model.getClass().getMethod(
						methodName, (Class[]) null).invoke(model,
						(Object[]) null);
				if (arr != null && arr.length > 0) {
					n++;
				}
			}
		}

		String[] arrzd = new String[n];
		int m = 0;
		for (int i = 0; i < zd.length; i++) {
			if (!"id".equalsIgnoreCase(zd[i]) && !"xh".equalsIgnoreCase(zd[i])) {
				String methodName = "get" + zd[i].substring(0, 1).toUpperCase()
						+ zd[i].substring(1);

				String[] arr = (String[]) model.getClass().getMethod(
						methodName, (Class[]) null).invoke(model,
						(Object[]) null);
				if (arr != null && arr.length > 0) {
					arrzd[m] = zd[i];
					m++;
				}
			}
		}

		String[] onezd = new String[] { "xh" };

		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(tableName);
		saveForm.setOnezd(onezd);
		saveForm.setArrzd(arrzd);
		saveForm.setPk(pk);
		saveForm.setPkValue(new String[] { model.getXh() });

		boolean flag = saveXszz(saveForm, model);

		String msg = flag ? "" : "家庭成员情况保存失败";

		return msg;
	}

	/**
	 * 获得家庭关系列表
	 * 
	 * @author luojw
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public List<HashMap<String, String>> getJtgxList(XszzTyForm model)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		String tableName = "view_xszz_jtgx";

		String[] colList = new String[] { "cydh", "cygx", "cygzdw", "cyjkzk",
				"cynl", "cynsr", "cynzc", "cyxm", "cyzy", "id", "mc", "xh",
				"cyyb", "cyysr" };
		String[] queryList = new String[] { "xh" };
		String[] queryLikeList = new String[] {};

		MakeQuery myQuery = new MakeQuery();

		myQuery.makeQuery(queryList, queryLikeList, model);

		// 查询条件
		StringBuilder query = new StringBuilder();
		query.append(myQuery.getQueryString());
		query.append(" order by cygx");

		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		/*
		 * 避免管理员点击增加按钮出现所有的xszz_jtgx表中所有数据 * @sjf
		 */
		if (StringUtils.isNotNull(model.getXh())) {
			list = CommonQueryDAO.commonQueryforList(tableName, query
					.toString(), myQuery.getInputList(), colList, "");
		}

		return list;
	}

	/**
	 * 获得学生信息列表
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getXsxxList(XszzTyForm model)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		String tableName = "view_xsjbxx";
		String lx = model.getLx();
		String zgh = model.getZgh();

		String[] colList = new String[] { "xh", "xm", "xb", "nj", "xydm",
				"xymc", "zymc", "zydm", "bjmc", "bjdm" };
		String[] queryList = new String[] { "nj", "xydm", "zydm", "bjdm" };
		String[] queryLikeList = new String[] { "xh", "xm" };

		MakeQuery myQuery = new MakeQuery();
		myQuery.makeQuery(queryList, queryLikeList, model);

		// 查询条件
		StringBuilder query = new StringBuilder();
		query.append(myQuery.getQueryString());

		// TODO
		if ("fdy".equalsIgnoreCase(lx)) {
			query.append(" and exists(select 1 from fdybjb a, view_fdyxx b ");
			query.append(" where a.bjdm = view_xsjbxx.bjdm ");
			query.append(" and a.zgh = b.zgh ");
			query.append(" and b.zgh = '" + zgh + "')");
		} else if ("bzr".equalsIgnoreCase(lx)) {
			query.append(" and exists(select 1 from bzrbbb a, view_fdyxx b ");
			query.append(" where a.bjdm = view_xsjbxx.bjdm");
			query.append(" and a.zgh = b.zgh ");
			query.append(" and b.zgh = '" + zgh + "')");
		} else if ("jd".equalsIgnoreCase(lx)) {
			query.append(" and (");
			query.append(" exists(select 1 from fdybjb a, view_fdyxx b ");
			query.append(" where a.bjdm = view_xsjbxx.bjdm");
			query.append(" and a.zgh = b.zgh ");
			query.append(" and b.zgh = '" + zgh + "')");

			query.append(" or exists(select 1 from bzrbbb a, view_fdyxx b ");
			query.append(" where a.bjdm = view_xsjbxx.bjdm");
			query.append(" and a.zgh = b.zgh ");
			query.append(" and b.zgh = '" + zgh + "')");

			query.append(" )");
		}

		// 学生列表
		List<HashMap<String, String>> xsList = CommonQueryDAO
				.commonQueryforList(tableName, query.toString(), myQuery
						.getInputList(), colList, "");

		// 项目列表
		List<HashMap<String, String>> allXmList = getAllXmList(model);
		//		
		// // 获得学生所在学院（专业班级）已审核通过学生列表
		// List<HashMap<String, String>> xmYtgList = getXmYtgList(allXmList,
		// stuInfo, model);
		//
		// // 获得项目人数设置列表
		// List<HashMap<String, String>> xmRsszList = getXmRsszList(allXmList,
		// model);
		// // 获得项目条件列表
		// List<HashMap<String, String>> xmTjList = getXmTjList(model);
		//
		// // 学生已申请项目列表
		// List<HashMap<String, String>> xsYsqXmList = getXsYsqXmList(allXmList,
		// model);

		for (int i = 0; i < allXmList.size(); i++) {

			HashMap<String, String> xmInfo = allXmList.get(i);

			// 当前学年
			String xn = model.getXn();
			// 当前学期
			String xq = model.getXq();
			// 当前年度
			String nd = model.getNd();
			// 项目代码
			String xmdm = xmInfo.get("xmdm");
			// 申请周期
			String sqzq = xmInfo.get("sqzq");
			// 人数控制是否需要
			String rskz = xmInfo.get("rskz");

			for (int j = 0; j < xsList.size(); j++) {

				HashMap<String, String> stuInfo = xsList.get(j);

				// 学院
				String xydm = stuInfo.get("xydm");
				// 年级
				String nj = stuInfo.get("nj");
				// 专业
				String zydm = stuInfo.get("zydm");
				// 班级
				String bjdm = stuInfo.get("bjdm");
			}

			// 移除需要人数控制而人数设置未设置（或者为0）的项目
		}
		// if (xsList != null && xsList.size() > 0) {
		//
		// for (int i = 0; i < xsList.size(); i++) {
		//
		// HashMap<String, String> stuInfo = xsList.get(i);
		//
		// if (xsList != null && xsList.size() > 0) {
		//
		// for (int j = 0; j < allXmList.size(); j++) {
		//
		// HashMap<String, String> xmInfo = allXmList.get(j);
		//
		// // 移除项目达到上限的项目
		// xqxmlist = removeXmSxList(model, xmYtgList, xqxmlist);
		//
		// // 移除不符合条件设置的项目
		// xqxmlist = removeBfhTjXm(model, xmTjList, xqxmlist);
		//
		// // 设置项目其他信息
		// xqxmlist = setXmOtherList(xqxmlist);
		//
		// // 设置项目审核相关信息
		// xqxmlist = setYsqXm(model, xsYsqXmList, xqxmlist);
		// }
		// }
		// }
		// }

		list = xsList;
		return getResultList(list, model);
	}

	/**
	 * 判断是否超过人数上限
	 * 
	 * @author luojw
	 */
	public String isCgrssx(HashMap<String, String> map, XszzTyForm model) {

		XsxxglService stuService = new XsxxglService();

		String msg = "";

		// 学号
		String xh = model.getXh();

		// 学生基本信息
		HashMap<String, String> stuInfo = stuService.selectStuinfo(xh);
		// 人数控制
		String rskz = map.get("rskz");
		map.putAll(stuInfo);

		if ("需要".equalsIgnoreCase(rskz)) {

			// 设置人数
			String szrs = dao.getXmszRs(model, map);
			// 已通过人数
			String rs = dao.getXmshRs(model, map);

			if (Base.isNull(szrs) || "0".equalsIgnoreCase(szrs)) {
				msg = "尚未设置人数或者设置人数为0，请确定！";
			} else if (!Base.isNull(rs)) {
				if (Integer.parseInt(rs) >= Integer.parseInt(szrs)) {
					msg = "该项目审核通过人数已经达到上限，请确定！";
				}
			}
		}

		return msg;
	}

	/**
	 * 判断是否超过人数上限(批量)
	 * 
	 * @author luojw
	 * @throws SQLException
	 */
	public String isCgrssxPl(HashMap<String, String> map, XszzTyForm model)
			throws SQLException {

		String msg = "";

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

		model.setCheckVal(pkValue);

		// 控制级别
		String kzjb = map.get("kzjb");
		// 人数控制
		String rskz = map.get("rskz");

		if ("需要".equalsIgnoreCase(rskz)) {

			// 设置人数
			List<HashMap<String, String>> szrsList = dao.getXmszRsList(model,
					map);
			// 已通过人数
			List<HashMap<String, String>> rsList = dao
					.getXmshRsList(model, map);
			// 审核人数
			List<HashMap<String, String>> shList = dao.getShRsList(model, map);

			if (szrsList == null || szrsList.size() == 0) {
				msg = "尚未设置人数或者设置人数为0，请确定！";
			} else {

				boolean flag = false;

				for (int i = 0; i < szrsList.size(); i++) {

					HashMap<String, String> szrsInfo = szrsList.get(i);

					// String bmjb = szrsInfo.get("bmjb");
					String nj = szrsInfo.get("nj");
					String bmdm = szrsInfo.get("bmdm");
					String num = szrsInfo.get("num");

					if (rsList != null && rsList.size() > 0) {

						for (int j = 0; j < rsList.size(); j++) {

							HashMap<String, String> rs = rsList.get(j);

							String rsnj = rs.get("nj");
							String xydm = rs.get("xydm");
							String zydm = rs.get("zydm");
							String bjdm = rs.get("bjdm");
							String szrs = rs.get("num");

							if (shList != null && shList.size() > 0) {

								for (int k = 0; k < shList.size(); k++) {
									HashMap<String, String> shInfo = shList
											.get(k);

									String shnj = shInfo.get("nj");
									String shxy = shInfo.get("xydm");
									String shzy = shInfo.get("zydm");
									String shbj = shInfo.get("bjdm");
									String shnum = shInfo.get("num");

									if ("学院".equalsIgnoreCase(kzjb)) {
										if (bmdm.equalsIgnoreCase(xydm)
												&& bmdm.equalsIgnoreCase(shxy)) {
											if (Integer.parseInt(shnum)
													+ Integer.parseInt(szrs) > Integer
													.parseInt(num)) {

												String bmmc = getOneValue(
														"view_njxyzybj",
														"xymc", "xydm", xydm);

												msg = bmmc
														+ "该项目审核通过人数已经达到上限，请确定！";

												flag = true;

												break;
											}
										}
									}

									if ("专业".equalsIgnoreCase(kzjb)) {
										if (bmdm.equalsIgnoreCase(zydm)
												&& nj.equalsIgnoreCase(rsnj)
												&& bmdm.equalsIgnoreCase(shzy)
												&& nj.equalsIgnoreCase(shnj)) {
											if (Integer.parseInt(shnum)
													+ Integer.parseInt(szrs) > Integer
													.parseInt(num)) {

												String bmmc = getOneValue(
														"view_njxyzybj",
														"zymc", "zydm", zydm);

												msg = nj
														+ "年级"
														+ bmmc
														+ "该项目审核通过人数已经达到上限，请确定！";

												flag = true;

												break;
											}
										}
									}

									if ("班级".equalsIgnoreCase(kzjb)) {
										if (bmdm.equalsIgnoreCase(bjdm)
												&& bmdm.equalsIgnoreCase(shbj)) {
											if (Integer.parseInt(shnum)
													+ Integer.parseInt(szrs) > Integer
													.parseInt(num)) {

												String bmmc = getOneValue(
														"view_njxyzybj",
														"bjmc", "bjdm", bjdm);

												msg = bmmc
														+ "该项目审核通过人数已经达到上限，请确定！";

												flag = true;

												break;
											}
										}
									}
								}
							}

							if (flag) {
								break;
							}
						}
					} else {
						if (shList != null && shList.size() > 0) {

							for (int k = 0; k < shList.size(); k++) {
								HashMap<String, String> shInfo = shList.get(k);

								String shnj = shInfo.get("nj");
								String shxy = shInfo.get("xydm");
								String shzy = shInfo.get("zydm");
								String shbj = shInfo.get("bjdm");
								String shnum = shInfo.get("num");

								if ("学院".equalsIgnoreCase(kzjb)) {
									if (bmdm.equalsIgnoreCase(shxy)) {
										if (Integer.parseInt(shnum) > Integer
												.parseInt(num)) {

											String bmmc = getOneValue(
													"view_njxyzybj", "xymc",
													"xydm", shxy);

											msg = bmmc + "该项目审核通过人数已经达到上限，请确定！";

											flag = true;

											break;
										}
									}
								}

								if ("专业".equalsIgnoreCase(kzjb)) {
									if (bmdm.equalsIgnoreCase(shzy)
											&& nj.equalsIgnoreCase(shnj)) {
										if (Integer.parseInt(shnum) > Integer
												.parseInt(num)) {

											String bmmc = getOneValue(
													"view_njxyzybj", "zymc",
													"zydm", shzy);

											msg = nj + "年级" + bmmc
													+ "该项目审核通过人数已经达到上限，请确定！";

											flag = true;

											break;
										}
									}
								}

								if ("班级".equalsIgnoreCase(kzjb)) {
									if (bmdm.equalsIgnoreCase(shbj)) {
										if (Integer.parseInt(shnum) > Integer
												.parseInt(num)) {

											String bmmc = getOneValue(
													"view_njxyzybj", "bjmc",
													"bjdm", shbj);

											msg = bmmc + "该项目审核通过人数已经达到上限，请确定！";

											flag = true;

											break;
										}
									}
								}
							}
						}
					}

					if (flag) {
						break;
					}
				}
			}
		}

		return msg;
	}

	/**
	 * 删除项目申请信息
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public boolean delXmsqInfo(XszzTyForm model) throws Exception {

		return dao.delXmsqInfo(model);
	}

	/**
	 * 增加状态信息
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public void addZtInfo(HashMap<String, String> map) {

		String tableName = "xszz_shztb";
		String xh = map.get("xh");
		String xmdm = map.get("xmdm");
		String sqsj = map.get("sqsj");
		String xn = map.get("xn");
		String xq = map.get("xq");
		String nd = map.get("nd");

		String[] columns = new String[] { "xh", "xmdm", "sqsj", "xn", "xq",
				"nd" };
		String[] values = new String[] { xh, xmdm, sqsj, xn, xq, nd };

		try {
			StandardOperation.insertNoLog(tableName, columns, values);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 * 修改状态信息
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public void updateZtInfo(HashMap<String, String> map) {

		ArrayList<String> columns = new ArrayList<String>();
		ArrayList<String> values = new ArrayList<String>();

		String xh = map.get("xh");
		String xmdm = map.get("xmdm");
		String sqsj = map.get("sqsj");

		setZtzdInfo(map, "xmzzjb", columns, values);
		setZtzdInfo(map, "xmzzje", columns, values);

		setZtzdInfo(map, "bzrsh", columns, values);
		setZtzdInfo(map, "fdysh", columns, values);
		setZtzdInfo(map, "xysh", columns, values);
		setZtzdInfo(map, "xxsh", columns, values);

		setZtzdInfo(map, "shzt1", columns, values);
		setZtzdInfo(map, "shzt2", columns, values);
		setZtzdInfo(map, "shzt3", columns, values);

		String tableName = "xszz_shztb";
		String primaryKey = "xh||sqsj||xmdm";
		String pkValue = xh + sqsj + xmdm;

		try {
			StandardOperation.updateNolog(tableName, columns
					.toArray(new String[] {}), values.toArray(new String[] {}),
					primaryKey, pkValue);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 * 修改状态信息(批量)
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public void updateZtInfoPl(HashMap<String, String> map, XszzTyForm model) {

		ArrayList<String> onezd = new ArrayList<String>();

		if (!Base.isNull(map.get("xmzzjb"))) {
			onezd.add("xmzzjb");
			model.setXmzzjb(map.get("xmzzjb"));
		}
		if (!Base.isNull(map.get("xmzzje"))) {
			onezd.add("xmzzje");
			model.setXmzzje(map.get("xmzzje"));
		}

		if (!Base.isNull(map.get("bzrsh"))) {
			onezd.add("bzrsh");
			model.setBzrsh(map.get("bzrsh"));
		}
		if (!Base.isNull(map.get("fdysh"))) {
			onezd.add("fdysh");
			model.setFdysh(map.get("fdysh"));
		}
		if (!Base.isNull(map.get("xysh"))) {
			onezd.add("xysh");
			model.setXysh(map.get("xysh"));
		}
		if (!Base.isNull(map.get("xxsh"))) {
			onezd.add("xxsh");
			model.setXxsh(map.get("xxsh"));
		}

		if (!Base.isNull(map.get("shzt1"))) {
			onezd.add("shzt1");
			model.setShzt1(map.get("shzt1"));
		}
		if (!Base.isNull(map.get("shzt2"))) {
			onezd.add("shzt2");
			model.setShzt2(map.get("shzt2"));
		}
		if (!Base.isNull(map.get("shzt3"))) {
			onezd.add("shzt3");
			model.setShzt3(map.get("shzt3"));
		}

		String tableName = "xszz_shztb";
		String pk = "xh||sqsj||xmdm";
		String[] pkValue = null;
		String shpk = model.getShpk();

		if (!Base.isNull(shpk)) {

			pkValue = new String[shpk.length()];

			String[] arr_shpk = shpk.split("!!@@!!");

			for (int i = 0; i < arr_shpk.length; i++) {
				pkValue[i] = arr_shpk[i];
			}
		}

		try {
			SaveForm saveForm = new SaveForm();
			saveForm.setTableName(tableName);
			saveForm.setPk(pk);
			saveForm.setPkValue(pkValue);
			saveForm.setOnezd(onezd.toArray(new String[] {}));

			updateXszz(saveForm, model);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 * @param map
	 * @param columns
	 * @param values
	 */
	private void setZtzdInfo(HashMap<String, String> map, String zd,
			ArrayList<String> columns, ArrayList<String> values) {
		String zdValue = map.get(zd);
		if (!Base.isNull(zdValue)) {
			columns.add(zd);
			values.add(zdValue);
		}
	}

	/**
	 * 获得上学年学期年度
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public HashMap<String, String> getBeforeXnXqNd(String sqzq, String pdsj,
			XszzTyForm model) {
		return dao.getBeforeXnXqNd(sqzq, pdsj, model);
	}

	/**
	 * 删除项目相关信息
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public boolean delXmxgInfo(XszzTyForm model) throws Exception {
		return dao.delXmxgInfo(model);
	}

	/**
	 * 同步状态信息
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public boolean tbZtInfo() throws Exception {
		return dao.tbZtInfo();
	}

	/**
	 * 获得个性化表头
	 * 
	 * @author luojw
	 */
	public String getUserLx(User user) {
		// 用户类型
		String userType = user.getUserType();
		// 辅导员权限
		boolean fdyQx = Boolean.parseBoolean(user.getFdyQx());
		// 班主任权限
		boolean bzrQx = Boolean.parseBoolean(user.getBzrQx());

		String lx = "";

		if (bzrQx && fdyQx) {// 班主任兼辅导员
			lx = "jd";
		} else if (fdyQx) {// 辅导员
			lx = "fdy";
		} else if (bzrQx) {// 班主任
			lx = "bzr";
		} else if ("xy".equalsIgnoreCase(userType)) {// 学院
			lx = "xy";
		} else if ("xx".equalsIgnoreCase(userType)
				|| "admin".equalsIgnoreCase(userType)) {// 学校用户（管理员）
			lx = "xx";
		} else {
			lx = "stu";
		}

		return lx;
	}

	// =======================以上made by 伟大的luo==========================

	/**
	 * 闽江学院综测分打印数据
	 * 
	 * @param xh
	 * @param xn
	 * @return
	 */
	public HashMap<String, String> getMjxyZcf(String xh, String xn) {
		GuizhdxDAO dao = new GuizhdxDAO();
		HashMap<String, String> zcMap = new HashMap<String, String>();
		zcMap.put("xh", xh);
		zcMap.put("xn", xn);

		List<HashMap<String, String>> zcfList = dao.getZcf(zcMap);
		String zyrs = dao.getZyrs(xh);
		zcMap.put("zyzrs", zyrs);

		zcMap.put("cjpm", getCjpm(xh));// 当年成绩排名
		zcMap.put("cjpmbl", String.valueOf(Math.rint(Double
				.parseDouble(getCjpm(xh))
				/ Double.parseDouble(zyrs))));

		for (HashMap<String, String> temp : zcfList) {
			if ("总分".equals(temp.get("mc")) && !Base.isNull(temp.get("pm"))) {
				zcMap.put("zjfpm", temp.get("pm"));
				zcMap.put("zjfpmBl", String.valueOf(Math.rint(Double
						.parseDouble(temp.get("pm"))
						/ Double.parseDouble(zyrs) * 100)));
			} else if (getZcfTitle()[0].equals(temp.get("mc"))
					&& !Base.isNull(temp.get("pm"))) {
				zcMap.put("dypmBl", String.valueOf(Math.rint(Double
						.parseDouble(temp.get("pm"))
						/ Double.parseDouble(zyrs) * 100)));
			} else if (getZcfTitle()[1].equals(temp.get("mc"))
					&& !Base.isNull(temp.get("pm"))) {
				zcMap.put("zypmBl", String.valueOf(Math.rint(Double
						.parseDouble(temp.get("pm"))
						/ Double.parseDouble(zyrs) * 100)));
			} else if (getZcfTitle()[2].equals(temp.get("mc"))
					&& !Base.isNull(temp.get("pm"))) {
				zcMap.put("wtpmBl", String.valueOf(Math.rint(Double
						.parseDouble(temp.get("pm"))
						/ Double.parseDouble(zyrs) * 100)));
			}
		}
		return zcMap;
	}

	/**
	 * 当年学习成绩排名
	 * 
	 * @param xh
	 * @return
	 */
	public String getCjpm(String xh) {
		String pm = dao.getCjpm(xh);

		if (Base.isNull(pm) || Base.isNull(xh)) {
			return "0";
		} else {
			return pm;
		}
	}

	/**
	 * 资助－－统计－－困难生
	 * 
	 * @param model
	 * @param os
	 */
	public void printKns(XszzTyForm model, OutputStream os) {
		String xmdm = model.getXmdm();
		String xydm = model.getXydm();
		String title = "闽江学院家庭经济困难学生信息汇总表V1.2";

		ExcelMB excel = new ExcelMB();
		WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(os);
		WritableSheet ws = wwb.createSheet("家庭经济困难学生", 0);

		try {
			excel.printTitle(ws, title, 26, 800);// 标题

			WritableCellFormat wcfTytle = ExcelMB.getWritableCellFormat(14,
					false, Alignment.CENTRE, VerticalAlignment.CENTRE,
					Border.ALL);// 单元格样式

			// 表头
			ws.addCell(new Label(0, 1, "序号", wcfTytle));
			ws.addCell(new Label(1, 1, "学号", wcfTytle));
			ws.addCell(new Label(2, 1, "姓名	", wcfTytle));
			ws.addCell(new Label(3, 1, "性别", wcfTytle));
			ws.addCell(new Label(4, 1, "民族	", wcfTytle));
			ws.addCell(new Label(5, 1, "年级", wcfTytle));
			ws.addCell(new Label(6, 1, "毕业时间", wcfTytle));
			ws.addCell(new Label(7, 1, "专业", wcfTytle));
			ws.addCell(new Label(8, 1, "籍贯", wcfTytle));
			ws.addCell(new Label(9, 1, "党团员", wcfTytle));
			ws.addCell(new Label(10, 1, "家庭简况", wcfTytle));
			ws.addCell(new Label(11, 1, "补考情况", wcfTytle));
			ws.addCell(new Label(12, 1, "获奖情况", wcfTytle));
			ws.addCell(new Label(13, 1, "违纪情况	", wcfTytle));
			ws.addCell(new Label(14, 1, "曾受资助情况	", wcfTytle));
			ws.addCell(new Label(15, 1, "困难程度	", wcfTytle));
			ws.addCell(new Label(16, 1, "资助金额(元)	", wcfTytle));
			ws.addCell(new Label(17, 1, "宿舍", wcfTytle));
			ws.addCell(new Label(18, 1, "本人联系电话	", wcfTytle));
			ws.addCell(new Label(19, 1, "工行卡号	", wcfTytle));
			ws.addCell(new Label(20, 1, "身份证号	", wcfTytle));
			ws.addCell(new Label(21, 1, "家庭地址邮编	", wcfTytle));
			ws.addCell(new Label(22, 1, "家庭地址	", wcfTytle));
			ws.addCell(new Label(23, 1, "家庭固定电话	", wcfTytle));
			ws.addCell(new Label(24, 1, "备  注	", wcfTytle));
			ws.addCell(new Label(25, 1, "学生核对签名", wcfTytle));

			List<HashMap<String, String>> xzData = new ArrayList<HashMap<String, String>>();
			List<HashMap<String, String>> tzData = new ArrayList<HashMap<String, String>>();
			List<HashMap<String, String>> scData = new ArrayList<HashMap<String, String>>();
			// 编辑数据
			editKnsData(xmdm, xzData, tzData, scData, xydm);
			// 把数据放在一个集合里
			List<HashMap<String, String>> data = new ArrayList<HashMap<String, String>>();
			data.addAll(xzData);
			data.addAll(tzData);
			data.addAll(scData);
			for (int i = 0; i < data.size(); i++) {

				ws
						.addCell(new Label(0, 2 + i, String.valueOf(i + 1),
								wcfTytle));
				ws
						.addCell(new Label(1, 2 + i, data.get(i).get("xh"),
								wcfTytle));
				ws
						.addCell(new Label(2, 2 + i, data.get(i).get("xm"),
								wcfTytle));
				ws
						.addCell(new Label(3, 2 + i, data.get(i).get("xb"),
								wcfTytle));
				ws.addCell(new Label(4, 2 + i, data.get(i).get("mzmc"),
						wcfTytle));
				ws
						.addCell(new Label(5, 2 + i, data.get(i).get("nj"),
								wcfTytle));
				ws.addCell(new Label(6, 2 + i, data.get(i).get("bysj"),
						wcfTytle));
				ws.addCell(new Label(7, 2 + i, data.get(i).get("zymc"),
						wcfTytle));
				ws
						.addCell(new Label(8, 2 + i, data.get(i).get("jg"),
								wcfTytle));
				ws.addCell(new Label(9, 2 + i, data.get(i).get("zzmmmc"),
						wcfTytle));
				ws.addCell(new Label(10, 2 + i, data.get(i).get("jtqkjj"),
						wcfTytle));
				ws.addCell(new Label(11, 2 + i, data.get(i).get("bkqk"),
						wcfTytle));
				ws.addCell(new Label(12, 2 + i, data.get(i).get("hjqk"),
						wcfTytle));
				ws.addCell(new Label(13, 2 + i, data.get(i).get("wjqk"),
						wcfTytle));
				ws.addCell(new Label(14, 2 + i, data.get(i).get("szzqk"),
						wcfTytle));
				ws.addCell(new Label(15, 2 + i, data.get(i).get("xmzzjb"),
						wcfTytle));
				ws.addCell(new Label(16, 2 + i, data.get(i).get("xmzzje"),
						wcfTytle));
				ws.addCell(new Label(17, 2 + i, data.get(i).get("ssbh"),
						wcfTytle));
				ws.addCell(new Label(18, 2 + i, data.get(i).get("lxdh"),
						wcfTytle));
				ws.addCell(new Label(19, 2 + i, data.get(i).get("yhkh"),
						wcfTytle));
				ws.addCell(new Label(20, 2 + i, data.get(i).get("sfzh"),
						wcfTytle));
				ws.addCell(new Label(21, 2 + i, data.get(i).get("jtyb"),
						wcfTytle));
				ws.addCell(new Label(22, 2 + i, data.get(i).get("jtdz"),
						wcfTytle));
				ws.addCell(new Label(23, 2 + i, data.get(i).get("jtdh"),
						wcfTytle));

				if (i < xzData.size()) {
					ws.addCell(new Label(24, 2 + i, "新增", wcfTytle));
				} else if (i < xzData.size() + tzData.size()) {
					ws.addCell(new Label(24, 2 + i, "调整", wcfTytle));
				} else {
					ws.addCell(new Label(24, 2 + i, "删除", wcfTytle));
				}
				ws.addCell(new Label(25, 2 + i, "", wcfTytle));
			}

			wcfTytle = ExcelMB.getWritableCellFormat(14, false, Alignment.LEFT,
					VerticalAlignment.CENTRE, Border.ALL);// 单元格样式
			ws.mergeCells(0, data.size() + 2, 25, data.size() + 2);
			ws.addCell(new Label(0, data.size() + 2,
					"备注栏必须注明调整情况,已删除学生信息排在最后。", wcfTytle));
			ws.mergeCells(0, data.size() + 3, 25, data.size() + 4);
			// 上学期各级困难生人数统计
			List<HashMap<String, String>> sxqData = dao.getSxqTj(xmdm, "sxq",
					xydm);
			int count = 0;
			StringBuilder str = new StringBuilder();
			for (HashMap<String, String> map : sxqData) {
				str.append(map.get("fjmc"));
				str.append(map.get("count"));
				str.append("人");
				str.append(",");
				count += Integer.parseInt(map.get("count"));
			}
			str.deleteCharAt(str.length() - 1);// 删除最后一个逗号
			// 本学期各级困难生人数统计
			List<HashMap<String, String>> bxqData = dao.getSxqTj(xmdm, "bxq",
					xydm);
			int bxqCount = 0;
			StringBuilder bxqStr = new StringBuilder();
			for (HashMap<String, String> map : bxqData) {
				bxqStr.append(map.get("fjmc"));
				bxqStr.append("学生 ");
				bxqStr.append(map.get("count"));
				bxqStr.append(" 人");
				bxqStr.append(",");
				bxqCount += Integer.parseInt(map.get("count"));
			}

			bxqStr.deleteCharAt(str.length() - 1);// 删除最后一个逗号
			// 统计部分字符串拼接
			StringBuilder temp = new StringBuilder();
			temp.append("我系上学期( ");
			if ("01".equals(Base.currXq)) {
				temp.append(Base.currNd);
				temp.append(" 春");
			} else {
				temp.append(Integer.parseInt(Base.currNd) - 1);
				temp.append(" 秋");
			}
			temp.append("季)家庭经济困难学生共");
			temp.append(count);
			temp.append(" 人,其中");
			temp.append(str);
			temp.append(" 。经调整等级 ");
			temp.append(tzData.size());
			temp.append(" 人,新增 ");
			temp.append(xzData.size());
			temp.append(" 人，删除 ");
			temp.append(scData.size());
			temp.append(" 人，本学期家庭经济困难生共 ");
			temp.append(xzData.size() + tzData.size());
			temp.append(" 人,其中");
			temp.append(bxqStr);
			temp.append(" 。");
			ws
					.addCell(new Label(0, data.size() + 3, temp.toString(),
							wcfTytle));
			ws.mergeCells(0, data.size() + 5, 25, data.size() + 5);// 最后一行合并单元格
			ws
					.addCell(new Label(
							0,
							data.size() + 5,
							"经办人：_____________________   "
									+ "院（系）学生资助工作组（负责人签名加盖院<系>章）：_______________________       "
									+ GetTime.getNowTime(), wcfTytle));

		} catch (Exception e) {
			e.printStackTrace();
		}

		// 向客户端输出
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}

	/**
	 * 资助－－统计－－绿色通道
	 * 
	 * @param model
	 * @param os
	 */
	public void printLstd(XszzTyForm model, OutputStream os) {
		String xmdm = model.getXmdm();
		int hjje = 0;
		String title = "闽江学院新生入学“绿色通道”申请登记表V1";
		List<String[]> data = dao.getLstdData(xmdm, model.getXydm());
		String xymc = dao.getOneValue("zxbz_xxbmdm", "bmmc", "bmdm", model
				.getXydm());
		;

		ExcelMB excel = new ExcelMB();
		WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(os);
		WritableSheet ws = wwb.createSheet("绿色通道", 0);

		try {
			excel.printTitle(ws, title, 9, 800);// 标题
			ws.mergeCells(0, 1, 4, 1);// 第二行的合并元格
			ws.mergeCells(5, 1, 8, 1);// 第二行的合并元格
			WritableCellFormat wcfTytle = ExcelMB.getWritableCellFormat(8,
					false, Alignment.CENTRE, VerticalAlignment.CENTRE,
					Border.ALL);// 单元格样式
			ws.addCell(new Label(0, 1, xymc + " 院系", wcfTytle));
			ws.addCell(new Label(5, 1, "填表时间： " + GetTime.getNowTime(),
					wcfTytle));
			// 表头
			ws.addCell(new Label(0, 2, "序  号", wcfTytle));
			ws.addCell(new Label(1, 2, "姓  名", wcfTytle));
			ws.addCell(new Label(2, 2, "性  别", wcfTytle));
			ws.addCell(new Label(3, 2, "系  别", wcfTytle));
			ws.addCell(new Label(4, 2, "班  级", wcfTytle));
			ws.addCell(new Label(5, 2, "籍  贯", wcfTytle));
			ws.addCell(new Label(6, 2, "家庭简况", wcfTytle));
			ws.addCell(new Label(7, 2, "缓交金额", wcfTytle));
			ws.addCell(new Label(8, 2, "家庭成员情况", wcfTytle));

			// 循环输出数据
			for (int i = 0; i < data.size(); i++) {
				for (int j = 0; j < data.get(i).length - 1; j++) {
					ws
							.addCell(new Label(j, 3 + i, data.get(i)[j + 1],
									wcfTytle));
				}
				// 缓交金额统计
				hjje += Integer.parseInt(data.get(i)[data.get(i).length - 1]);
				/*
				 * 家庭成员情况
				 */
				model.setXh(data.get(i)[0]);
				List<HashMap<String, String>> cyList = getJtgxList(model);
				StringBuilder cyString = new StringBuilder();
				for (HashMap<String, String> map : cyList) {
					cyString.append("姓名：");
					cyString.append(Base.isNull(map.get("cyxm")) ? " " : map
							.get("cyxm"));
					cyString.append("，关系：");
					cyString.append(Base.isNull(map.get("cygx")) ? " " : map
							.get("cygx"));
					cyString.append(",电话：");
					cyString.append(Base.isNull(map.get("cydh")) ? " " : map
							.get("cydh"));
					cyString.append("，单位：");
					cyString.append(Base.isNull(map.get("cygzdw")) ? " " : map
							.get("cygzdw"));
					cyString
							.append(";                                                                            ");
				}
				WritableCellFormat leftStyle = ExcelMB.getWritableCellFormat(
						14, false, Alignment.LEFT, VerticalAlignment.CENTRE,
						Border.ALL);// 单元格样式
				ws.addCell(new Label(8, 3 + i, cyString.toString(), leftStyle));

			}
			/*
			 * int 列起，int 行起，int 列结束，int 行结束 备注部分合并
			 */
			ws.mergeCells(0, data.size() + 3, 0, data.size() + 4);
			ws.mergeCells(1, data.size() + 3, 8, data.size() + 3);
			ws.mergeCells(1, data.size() + 4, 8, data.size() + 4);

			// 备注部分
			String bz1 = "1、各院（系）应在认真查看有关证明材料，了解核实有关情况后，再审批缓交额度。";
			String bz2 = "2、“缓交金额”一栏应写明已交费用（包含教材代办费、住宿费、学费）以及申请缓交金额。";
			ws.addCell(new Label(0, data.size() + 3, "备注", wcfTytle));

			wcfTytle = ExcelMB.getWritableCellFormat(14, false, Alignment.LEFT,
					VerticalAlignment.CENTRE, Border.ALL);// 单元格样式

			ws.addCell(new Label(1, data.size() + 3, bz1, wcfTytle));
			ws.addCell(new Label(1, data.size() + 4, bz2, wcfTytle));

			wcfTytle = ExcelMB.getWritableCellFormat(14, false, Alignment.LEFT,
					VerticalAlignment.CENTRE, Border.NONE);// 单元格样式
			ws.mergeCells(0, data.size() + 5, 8, data.size() + 7);
			StringBuilder tjnr = new StringBuilder();

			tjnr.append("我院（系）总计申请绿色通道");
			tjnr.append(data.size());
			tjnr.append("人，缓交金额(大写)");
			tjnr.append(0 == hjje ? "零元" : MoneyFormat.format(String
					.valueOf(hjje)));
			tjnr.append(",小写");
			tjnr.append(hjje);
			tjnr.append("元");

			ws
					.addCell(new Label(0, data.size() + 5, tjnr.toString(),
							wcfTytle));

			ws.mergeCells(0, data.size() + 8, 8, data.size() + 10);
			tjnr = new StringBuilder();

			tjnr.append("经办：_______________");
			tjnr.append("            ");
			tjnr.append(" 院系资助工作组（负责人签名加盖系章）：");
			tjnr.append("_________________");

			ws
					.addCell(new Label(0, data.size() + 8, tjnr.toString(),
							wcfTytle));

			// ws.mergeCells(0, data.size()+11, 8, data.size()+13);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 向客户端输出
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}

	/**
	 * 资助－－统计－－国家励志奖学金
	 * 
	 * @param model
	 * @param os
	 */
	public void printLzjxj(XszzTyForm model, OutputStream os) {
		String xmdm = model.getXmdm();
		List<String[]> data = dao.getLzjxjData(xmdm, model.getXn(),
				getZcfTitle());

		StringBuilder title = new StringBuilder();
		title.append("闽江学院");
		title.append(model.getXn().substring(0, 4));
		title.append("～");
		title.append(model.getXn().substring(5, 9));
		title.append("学年度国家励志奖学金详表");

		ExcelMB excel = new ExcelMB();
		WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(os);
		WritableSheet ws = wwb.createSheet("国家励志奖学金", 0);

		try {
			excel.printTitle(ws, title.toString(), 19, 800);// 标题
			ws.mergeCells(0, 1, 9, 1);// 第二行的合并元格
			ws.mergeCells(10, 1, 18, 1);// 第二行的合并元格
			WritableCellFormat leftStyle = ExcelMB.getWritableCellFormat(14,
					false, Alignment.LEFT, VerticalAlignment.CENTRE,
					Border.NONE);// 单元格样式
			WritableCellFormat rightStyle = ExcelMB.getWritableCellFormat(14,
					false, Alignment.RIGHT, VerticalAlignment.CENTRE,
					Border.NONE);// 单元格样式

			ws.addCell(new Label(0, 1, "学校：闽江学院（公章）", leftStyle));
			ws.addCell(new Label(10, 1, "填表日期：" + GetTime.getNowTime(),
					rightStyle));

			/*
			 * 数据输出
			 */
			WritableCellFormat wcfTytle = ExcelMB.getWritableCellFormat(14,
					false, Alignment.CENTRE, VerticalAlignment.CENTRE,
					Border.ALL);// 单元格样式
			ws.addCell(new Label(0, 2, "序号", wcfTytle));
			ws.addCell(new Label(1, 2, "姓名", wcfTytle));
			ws.addCell(new Label(2, 2, "身份证号", wcfTytle));
			ws.addCell(new Label(3, 2, "院系", wcfTytle));
			ws.addCell(new Label(4, 2, "专业", wcfTytle));
			ws.addCell(new Label(5, 2, "学号", wcfTytle));
			ws.addCell(new Label(6, 2, "性别", wcfTytle));
			ws.addCell(new Label(7, 2, "民族", wcfTytle));
			ws.addCell(new Label(8, 2, "入学年月", wcfTytle));
			ws.addCell(new Label(9, 2, "职务", wcfTytle));
			ws.addCell(new Label(10, 2, "工行卡号", wcfTytle));
			ws.addCell(new Label(11, 2, "困难程度", wcfTytle));
			ws.addCell(new Label(12, 2, "所在专业人数", wcfTytle));
			ws.addCell(new Label(13, 2, "综合测评名次", wcfTytle));
			ws.addCell(new Label(14, 2, "综合测评排名", wcfTytle));
			ws.addCell(new Label(15, 2, "德育排名", wcfTytle));
			ws.addCell(new Label(16, 2, "智育排名", wcfTytle));
			ws.addCell(new Label(17, 2, "文体排名", wcfTytle));
			ws.addCell(new Label(18, 2, "获奖情况", wcfTytle));

			for (int i = 0; i < data.size(); i++) {
				for (int j = 0; j < data.get(i).length; j++) {
					ws.addCell(new Label(j, 3 + i, data.get(i)[j], wcfTytle));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 向客户端输出
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}

	/**
	 * 资助－－统计－－学生缓交学费
	 * 
	 * @param model
	 * @param os
	 */
	public void printHjxf(XszzTyForm model, OutputStream os) {
		String xmdm = model.getXmdm();
		String title = "闽江学院学生缓交学费情况统计表V1";
		List<String[]> data = dao.getHjxfData(xmdm);
		int dkrs = 0;// 贷款人数
		int jmrs = 0;// 减免人数
		int hjrs = 0;// 缓交人数
		int dkje = 0;// 贷款金额
		int hjje = 0;// 缓交金额
		int jmje = 0;// 减免金额

		ExcelMB excel = new ExcelMB();
		WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(os);
		WritableSheet ws = wwb.createSheet("学生缓交学费", 0);

		try {
			excel.printTitle(ws, title.toString(), 12, 800);
			WritableCellFormat wcfTytle = ExcelMB.getWritableCellFormat(14,
					false, Alignment.CENTRE, VerticalAlignment.CENTRE,
					Border.ALL);// 单元格样式
			/*
			 * 第一、二行合并单元格
			 */
			for (int i = 0; i < 9; i++) {
				ws.mergeCells(i, 1, i, 2);
			}
			ws.mergeCells(9, 1, 11, 1);

			/*
			 * 表头
			 */
			ws.addCell(new Label(0, 1, "院系", wcfTytle));
			ws.addCell(new Label(1, 1, "姓名", wcfTytle));
			ws.addCell(new Label(2, 1, "性别", wcfTytle));
			ws.addCell(new Label(3, 1, "年级专业班级", wcfTytle));
			ws.addCell(new Label(4, 1, "籍贯生源", wcfTytle));
			ws.addCell(new Label(5, 1, "当前累计欠费（元）", wcfTytle));
			ws.addCell(new Label(6, 1, "家庭简况", wcfTytle));
			ws.addCell(new Label(7, 1, "宿舍", wcfTytle));
			ws.addCell(new Label(8, 1, "本人联系电话", wcfTytle));
			ws.addCell(new Label(9, 1, "享受类别及金额", wcfTytle));
			ws.addCell(new Label(9, 2, "贷款借款金额（元）", wcfTytle));
			ws.addCell(new Label(10, 2, "学费减免金额（元）", wcfTytle));
			ws.addCell(new Label(11, 2, "学费缓交金额（元）", wcfTytle));

			/*
			 * 循环输出数据
			 */
			for (int i = 0; i < data.size(); i++) {
				for (int j = 0; j < data.get(i).length; j++) {
					ws.addCell(new Label(j, 3 + i, data.get(i)[j], wcfTytle));
				}

				if (Integer.parseInt(data.get(i)[9]) > 0) {
					dkrs++;
					dkje += Integer.parseInt(data.get(i)[9]);
				}
				if (Integer.parseInt(data.get(i)[10]) > 0) {
					jmrs++;
					jmje += Integer.parseInt(data.get(i)[10]);
				}
				if (Integer.parseInt(data.get(i)[11]) > 0) {
					hjrs++;
					hjje += Integer.parseInt(data.get(i)[11]);
				}
			}

			/*
			 * 统计部分合并单元格
			 */
			for (int i = 0; i < 4; i++) {
				ws.mergeCells(0, data.size() + 3 + i, 2, data.size() + 3 + i);
				ws.mergeCells(3, data.size() + 3 + i, 5, data.size() + 3 + i);
				ws.mergeCells(7, data.size() + 3 + i, 11, data.size() + 3 + i);
			}
			/*
			 * 统计数据
			 */
			ws.addCell(new Label(0, data.size() + 3, "贷款人数（人）", wcfTytle));
			ws.addCell(new Label(3, data.size() + 3, String.valueOf(dkrs),
					wcfTytle));
			ws.addCell(new Label(6, data.size() + 3, "贷款金额（元）", wcfTytle));
			ws.addCell(new Label(7, data.size() + 3, String.valueOf(dkje),
					wcfTytle));

			ws.addCell(new Label(0, data.size() + 4, "减免人数（人)", wcfTytle));
			ws.addCell(new Label(3, data.size() + 4, String.valueOf(jmrs),
					wcfTytle));
			ws.addCell(new Label(6, data.size() + 4, "减免金额（元）", wcfTytle));
			ws.addCell(new Label(7, data.size() + 4, String.valueOf(jmje),
					wcfTytle));

			ws.addCell(new Label(0, data.size() + 5, "缓交人数（人）", wcfTytle));
			ws.addCell(new Label(3, data.size() + 5, String.valueOf(hjrs),
					wcfTytle));
			ws.addCell(new Label(6, data.size() + 5, "缓交金额（元）", wcfTytle));
			ws.addCell(new Label(7, data.size() + 5, String.valueOf(hjje),
					wcfTytle));

			ws.addCell(new Label(0, data.size() + 6, "总人数（人）", wcfTytle));
			ws.addCell(new Label(3, data.size() + 6, String
					.valueOf(data.size()), wcfTytle));
			ws.addCell(new Label(6, data.size() + 6, "总金额（元）", wcfTytle));
			ws.addCell(new Label(7, data.size() + 6, String.valueOf(dkje + jmje
					+ hjje), wcfTytle));

			/*
			 * 结尾
			 */
			ws.mergeCells(0, data.size() + 8, 2, data.size() + 8);
			ws.mergeCells(5, data.size() + 8, 6, data.size() + 8);
			ws.mergeCells(8, data.size() + 8, 11, data.size() + 8);
			ws.addCell(new Label(0, data.size() + 8, "经办人：", wcfTytle));
			ws.addCell(new Label(5, data.size() + 8, "院系资助工作组（负责人签名加盖系章）：",
					wcfTytle));
			ws.addCell(new Label(8, data.size() + 8, GetTime.getNowTime(),
					wcfTytle));

		} catch (Exception e) {
			e.printStackTrace();
		}
		// 向客户端输出
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}

	/**
	 * 资助--统计--国家助学金
	 * 
	 * @param model
	 * @param os
	 */
	public void printGjzxj(XszzTyForm model, OutputStream os) {
		String xmdm = model.getXmdm();
		String xn = model.getXn();
		String xq = model.getXq();
		String xqmc = DAO.getInstance()
				.getOneValue("xqdzb", "xqmc", "xqdm", xq);
		String title = "高校国家助学金申请汇总表";
		String[] knjb = dao.getKnjbData();
		List<String[]> data = dao.getGjzxjData(xmdm, xn, xq);

		ExcelMB excel = new ExcelMB();
		WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(os);
		WritableSheet ws = wwb.createSheet("国家助学金", 0);

		StringBuilder sb = new StringBuilder();// 二级表头
		sb.append(xn);
		sb.append("          学年           ");
		sb.append(xqmc);
		sb.append("          学期           ");
		sb.append("           单位：人");

		try {
			excel.printTitle(ws, title,
					null == knjb ? 6 : 8 + 12 * knjb.length, 800);
			ws.mergeCells(0, 1, null == knjb ? 5 : 7 + 12 * knjb.length, 1);// 第二行合并单元格
			WritableCellFormat wcfTytle = ExcelMB.getWritableCellFormat(14,
					false, Alignment.CENTRE, VerticalAlignment.CENTRE,
					Border.ALL);// 单元格样式

			ws.mergeCells(0, 2, 0, 4);// 院系合并
			ws.mergeCells(1, 2, 1, 4);// 类别合并
			ws.mergeCells(5, 2, 5, 4);// 申请总人数合并
			ws.mergeCells(2, 3, 2, 4);// 小计合并
			ws.mergeCells(3, 3, 3, 4);// 本科合并
			ws.mergeCells(4, 3, 4, 4);// 专科合并
			ws.mergeCells(2, 2, 4, 2);// 本专科学生数合并

			ws.addCell(new Label(0, 1, sb.toString(), wcfTytle));
			ws.addCell(new Label(0, 2, "院（系）", wcfTytle));
			ws.addCell(new Label(1, 2, "类别", wcfTytle));
			ws.addCell(new Label(2, 2, "学校全日制本、专科学生数", wcfTytle));
			ws.addCell(new Label(2, 3, "小计", wcfTytle));
			ws.addCell(new Label(3, 3, "本科", wcfTytle));
			ws.addCell(new Label(4, 3, "专科", wcfTytle));
			ws.addCell(new Label(5, 2, "申请总人数", wcfTytle));

			if (null != knjb && knjb.length > 0) {

				for (int i = 0; i < knjb.length; i++) {
					int n = i == 0 ? 0 : 12;

					ws.mergeCells(6 + n * i, 2, 17 + n * i, 2);
					ws.addCell(new Label(6 + n * i, 2, knjb[i] + "学生人数",
							wcfTytle));
					// 合计部分
					ws.mergeCells(6 + n * i, 3, 6 + n * i, 4);
					ws.addCell(new Label(6 + n * i, 3, "合计", wcfTytle));
					// 占学生比例部分
					ws.mergeCells(7 + n * i, 3, 7 + n * i, 4);
					ws.addCell(new Label(7 + n * i, 3,
							knjb[i] + "学生占在校本专科生比例%", wcfTytle));
					// 本科部分
					ws.mergeCells(8 + n * i, 3, 13 + n * i, 3);
					ws.addCell(new Label(8 + n * i, 3, "本科", wcfTytle));
					// 专科部分
					ws.mergeCells(14 + n * i, 3, 17 + n * i, 3);
					ws.addCell(new Label(14 + n * i, 3, "专科", wcfTytle));

					ws.addCell(new Label(8 + n * i, 4, "小计", wcfTytle));
					ws.addCell(new Label(9 + n * i, 4, "一年级", wcfTytle));
					ws.addCell(new Label(10 + n * i, 4, "二年级", wcfTytle));
					ws.addCell(new Label(11 + n * i, 4, "三年级", wcfTytle));
					ws.addCell(new Label(12 + n * i, 4, "四年级", wcfTytle));
					ws.addCell(new Label(13 + n * i, 4, "", wcfTytle));
					ws.addCell(new Label(14 + n * i, 4, "小计", wcfTytle));
					ws.addCell(new Label(15 + n * i, 4, "一年级", wcfTytle));
					ws.addCell(new Label(16 + n * i, 4, "二年级", wcfTytle));
					ws.addCell(new Label(17 + n * i, 4, "三年级", wcfTytle));

				}

				ws.mergeCells(6 + 12 * knjb.length, 2, 6 + 12 * knjb.length, 4);
				ws.mergeCells(7 + 12 * knjb.length, 2, 7 + 12 * knjb.length, 4);

				ws.addCell(new Label(6 + 12 * knjb.length, 2, "总计", wcfTytle));
				ws.addCell(new Label(7 + 12 * knjb.length, 2, "申请学生是否经公示",
						wcfTytle));

				int row = 6;
				for (int i = 0; i < data.size(); i++) {
					ws.mergeCells(0, 5 + i * 3, 0, 7 + i * 3);// 学院合并单元格
					ws
							.addCell(new Label(0, 5 + i * 3, data.get(i)[0],
									wcfTytle));
					ws.addCell(new Label(1, 5 + i * 3, "合计", wcfTytle));

					HashMap<String, String> xsData = dao.getLsXs(
							data.get(i)[0], "xs");
					HashMap<String, String> lsData = dao.getLsXs(
							data.get(i)[0], "ls");
					ws.addCell(new Label(1, 6 + i * 3, "老生", wcfTytle));
					ws.addCell(new Label(1, 7 + i * 3, "新生", wcfTytle));

					ws.addCell(new Label(2, 6 + i * 3, lsData.get("xj"),
							wcfTytle));
					ws.addCell(new Label(3, 6 + i * 3, lsData.get("bks"),
							wcfTytle));
					ws.addCell(new Label(4, 6 + i * 3, lsData.get("zks"),
							wcfTytle));
					ws.addCell(new Label(5, 6 + i * 3, lsData.get("sqzxj"),
							wcfTytle));

					ws.addCell(new Label(2, 7 + i * 3, xsData.get("xj"),
							wcfTytle));
					ws.addCell(new Label(3, 7 + i * 3, xsData.get("bks"),
							wcfTytle));
					ws.addCell(new Label(4, 7 + i * 3, xsData.get("zks"),
							wcfTytle));
					ws.addCell(new Label(5, 7 + i * 3, xsData.get("sqzxj"),
							wcfTytle));

					for (int j = 1; j < 5; j++) {
						ws.addCell(new Label(j + 1, 5 + i * 3, data.get(i)[j],
								wcfTytle));
					}

					int lszj = 0;
					int xszj = 0;

					for (int d = 0; d < knjb.length; d++) {

						int n = d == 0 ? 0 : 12;
						int e = d == 0 ? 0 : 11;

						int bkxj = Integer.valueOf(data.get(i)[9 + e])
								+ Integer.valueOf(data.get(i)[10 + e])
								+ Integer.valueOf(data.get(i)[11 + e]);
						int zkxj = Integer.valueOf(data.get(i)[14 + e])
								+ Integer.valueOf(data.get(i)[15 + e]);
						String lsbl = "0".equals(lsData.get("xj")) ? "0"
								: String.valueOf((bkxj + zkxj)
										/ Integer.parseInt(lsData.get("xj"))
										* 100);
						String xsbl = "0".equals(xsData.get("xj")) ? "0"
								: String.valueOf((Integer
										.valueOf(data.get(i)[8 + e]) + Integer
										.valueOf(data.get(i)[13 + e]))
										/ Integer.parseInt(xsData.get("xj"))
										* 100);

						ws.addCell(new Label(6 + n * d, 5 + i * 3,
								data.get(i)[5 + e], wcfTytle));// 合计-合计
						ws.addCell(new Label(7 + n * d, 5 + i * 3,
								data.get(i)[6 + e], wcfTytle));// 比例
						ws.addCell(new Label(7 + n * d, 6 + i * 3, lsbl,
								wcfTytle));// 老生比例
						ws.addCell(new Label(7 + n * d, 7 + i * 3, xsbl,
								wcfTytle));// 新生比例

						ws.addCell(new Label(6 + n * d, 6 + i * 3, String
								.valueOf(bkxj + zkxj), wcfTytle));// 老生合计
						ws
								.addCell(new Label(
										6 + n * d,
										7 + i * 3,
										String
												.valueOf(Integer.valueOf(data
														.get(i)[8 + e])
														+ Integer
																.valueOf(data
																		.get(i)[13 + e])),
										wcfTytle));// 新生合计

						lszj += bkxj + zkxj;
						xszj += Integer.valueOf(data.get(i)[8 + e])
								+ Integer.valueOf(data.get(i)[13 + e]);

						// 本科新生
						ws.addCell(new Label(8 + n * d, 5 + i * 3,
								data.get(i)[7 + e], wcfTytle));
						ws.addCell(new Label(8 + n * d, 7 + i * 3,
								data.get(i)[8 + e], wcfTytle));// 本科新生小计
						ws.addCell(new Label(9 + n * d, 5 + i * 3,
								data.get(i)[8 + e], wcfTytle));// 本科一年级
						ws.addCell(new Label(9 + n * d, 7 + i * 3,
								data.get(i)[8 + e], wcfTytle));// 本科新生一年级
						// 本科老生
						ws.addCell(new Label(8 + n * d, 6 + i * 3, String
								.valueOf(bkxj), wcfTytle));// 本科老生小计

						ws.addCell(new Label(10 + n * d, 5 + i * 3,
								data.get(i)[9 + e], wcfTytle));
						ws.addCell(new Label(11 + n * d, 5 + i * 3,
								data.get(i)[10 + e], wcfTytle));
						ws.addCell(new Label(12 + n * d, 5 + i * 3,
								data.get(i)[11 + e], wcfTytle));
						ws.addCell(new Label(10 + n * d, 6 + i * 3,
								data.get(i)[9 + e], wcfTytle));
						ws.addCell(new Label(11 + n * d, 6 + i * 3,
								data.get(i)[10 + e], wcfTytle));
						ws.addCell(new Label(12 + n * d, 6 + i * 3,
								data.get(i)[11 + e], wcfTytle));

						ws.addCell(new Label(13 + n * d, 5 + i * 3, "",
								wcfTytle));// 空白单元格

						// 专科新生
						ws.addCell(new Label(14 + n * d, 5 + i * 3,
								data.get(i)[12 + e], wcfTytle));
						ws.addCell(new Label(14 + n * d, 7 + i * 3,
								data.get(i)[13 + e], wcfTytle));// 专科新生小计
						ws.addCell(new Label(15 + n * d, 5 + i * 3,
								data.get(i)[13 + e], wcfTytle));// 专科一年级
						ws.addCell(new Label(15 + n * d, 7 + i * 3,
								data.get(i)[13 + e], wcfTytle));// 专科新生一年级

						// 专科老生
						ws.addCell(new Label(14 + n * d, 6 + i * 3, String
								.valueOf(zkxj), wcfTytle));

						ws.addCell(new Label(16 + n * d, 5 + i * 3,
								data.get(i)[14 + e], wcfTytle));
						ws.addCell(new Label(17 + n * d, 5 + i * 3,
								data.get(i)[15 + e], wcfTytle));
						ws.addCell(new Label(16 + n * d, 6 + i * 3,
								data.get(i)[14 + e], wcfTytle));
						ws.addCell(new Label(17 + n * d, 6 + i * 3,
								data.get(i)[15 + e], wcfTytle));

					}
					ws.addCell(new Label(knjb.length * 12 + 6, 5 + i * 3, data
							.get(i)[data.get(i).length - 1], wcfTytle));
					ws.addCell(new Label(knjb.length * 12 + 6, 6 + i * 3,
							String.valueOf(lszj), wcfTytle));
					ws.addCell(new Label(knjb.length * 12 + 6, 7 + i * 3,
							String.valueOf(xszj), wcfTytle));

					ws.addCell(new Label(knjb.length * 12 + 7, 5 + i * 3, "",
							wcfTytle));
					ws.addCell(new Label(knjb.length * 12 + 7, 6 + i * 3, "",
							wcfTytle));
					ws.addCell(new Label(knjb.length * 12 + 7, 7 + i * 3, "",
							wcfTytle));

					row = 7 + i * 3;

				}

				ws.mergeCells(0, row + 1, 7 + 12 * knjb.length, row + 1);
				ws.mergeCells(0, row + 2, 7 + 12 * knjb.length, row + 2);
				ws.mergeCells(0, row + 3, 7 + 12 * knjb.length, row + 3);

				wcfTytle = ExcelMB.getWritableCellFormat(14, false,
						Alignment.RIGHT, VerticalAlignment.CENTRE, Border.ALL);// 单元格样式

				ws.addCell(new Label(0, row + 1, "经办人：						", wcfTytle));
				ws.addCell(new Label(0, row + 2, "院系签章：						", wcfTytle));
				ws.addCell(new Label(0, row + 3,
						"填报时间：" + GetTime.getNowTime(), wcfTytle));
			} else {
				throw new Exception("请在困难生处维护困难级别！！！");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		// 向客户端输出
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}

	/**
	 * 导出数据
	 * 
	 * @author luo
	 * @throws Exception
	 */
	public void expInfo(XszzTyForm model, OutputStream os) throws Exception {

		// 学校代码
		String xxdm = Base.xxdm;
		// 项目代码
		String xmdm = model.getXmdm();
		model.setPkValue(xmdm);
		HashMap<String, String> xmInfo = getXmxgInfo(model);
		// 项目表
		String xmb = xmInfo.get("xmb");
		model.setXmb(xmb);
		// 项目名称
		String title = xmInfo.get("xmmc");
		// 导出字段
		String[] outValue = getOutValue(getTableZd(xmb));

		// 中文表述
		List<HashMap<String, String>> topTr = SearchUtils.getTopTr(xmb,
				outValue, null);

		// 学号位置
		int xhNun = 0;

		if (outValue != null && outValue.length > 0) {
			for (int i = 0; i < outValue.length; i++) {
				if ("xh".equalsIgnoreCase(outValue[i])) {
					xhNun = i;
					break;
				}
			}
		}
		// 添加格外字段
		HashMap<String, String> map = new HashMap<String, String>();

		map.put("en", "xmmc");
		map.put("cn", "项目名称");
		topTr.add(map);

		map = new HashMap<String, String>();
		map.put("en", "xm");
		map.put("cn", "姓名");
		topTr.add(map);

		map = new HashMap<String, String>();

		map.put("en", "xb");
		map.put("cn", "性别");
		topTr.add(map);

		map = new HashMap<String, String>();

		map.put("en", "nj");
		map.put("cn", "年级");
		topTr.add(map);

		map = new HashMap<String, String>();

		map.put("en", "xymc");
		map.put("cn", "院系名称");
		topTr.add(map);

		map = new HashMap<String, String>();

		map.put("en", "zymc");
		map.put("cn", "专业名称");
		topTr.add(map);

		map = new HashMap<String, String>();

		map.put("en", "bjmc");
		map.put("cn", "班级名称");
		topTr.add(map);

		if (Globals.XXDM_ZGDZDX.equalsIgnoreCase(xxdm)) {
			map = new HashMap<String, String>();
			map.put("en", "bjpm");
			map.put("cn", "班级排名");
			topTr.add(map);
		}

		// 导出基本内容
		ArrayList<String[]> list = getExpList(model, outValue);

		List<String> xhList = new ArrayList<String>();

		if (list != null && list.size() > 0) {

			for (int i = 0; i < list.size(); i++) {

				// 基础数据
				String[] jcData = list.get(i);

				// 学号
				String xh = jcData[xhNun];

				xhList.add(xh);
			}
		}

		List<HashMap<String, String>> expXsList = dao
				.getExpXsList(xmdm, xhList);

		// 导出内容
		ArrayList<String[]> expList = new ArrayList<String[]>();

		if (list != null && list.size() > 0) {

			for (int i = 0; i < list.size(); i++) {

				// 基础数据
				String[] jcData = list.get(i);

				// 学号
				String xh = jcData[xhNun];

				// 导出数据
				String[] expData = new String[jcData.length + 7];
				if (Globals.XXDM_ZGDZDX.equalsIgnoreCase(xxdm)) {
					expData = new String[jcData.length + 8];
				}
				if (jcData != null && jcData.length > 0) {
					for (int j = 0; j < jcData.length; j++) {
						expData[j] = jcData[j];
					}
				}

				if (expXsList != null && expXsList.size() > 0) {
					for (int j = 0; j < expXsList.size(); j++) {
						HashMap<String, String> xsInfo = expXsList.get(j);
						if (xh.equalsIgnoreCase(xsInfo.get("xh"))) {
							expData[jcData.length] = title;
							expData[jcData.length + 1] = expXsList.get(j).get(
									"xm");
							expData[jcData.length + 2] = expXsList.get(j).get(
									"xb");
							expData[jcData.length + 3] = expXsList.get(j).get(
									"nj");
							expData[jcData.length + 4] = expXsList.get(j).get(
									"xymc");
							expData[jcData.length + 5] = expXsList.get(j).get(
									"zymc");
							expData[jcData.length + 6] = expXsList.get(j).get(
									"bjmc");
							if (Globals.XXDM_ZGDZDX.equalsIgnoreCase(xxdm)) {
								expData[jcData.length + 7] = expXsList.get(j)
										.get("zcbjpm");
							}
							expList.add(expData);

							break;
						}
					}
				}
			}
		}

		expXszzData(title, topTr, expList, os);
	}

	/**
	 * 获得导出数据列表
	 * 
	 * @param xh
	 * @return
	 */
	public ArrayList<String[]> getExpList(XszzTyForm model, String[] outValue) {
		return dao.getExpList(model, outValue);
	}

	/**
	 * 是否可修改
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public Boolean canEdit(XszzTyForm model) {

		boolean flag = false;

		// 项目代码
		String xmdm = model.getXmdm();
		// 审核级别
		String shjb = model.getShjb();
		// 学号
		String xh = model.getXh();
		// 申请时间
		String sqsj = model.getSqsj();
		// 项目表
		String xmb = model.getXmb();

		// 主键
		String pk = "xh||sqsj||xmdm";
		// 主键值得
		String pkValue = xh + sqsj + xmdm;
		// 输出字段
		String[] colList = new String[] { "shzt1", "shzt2", "shzt3" };

		// 项目相关信息
		HashMap<String, String> map = getXszzInfo(xmb, pk, pkValue, colList);

		String shzt1 = map.get("shzt1");
		String shzt2 = map.get("shzt2");
		String shzt3 = map.get("shzt3");

		if ("无需审核".equalsIgnoreCase(shjb)) {
			flag = true;
		} else if ("一级审核".equalsIgnoreCase(shjb)) {
			if ("未审核".equalsIgnoreCase(shzt1)) {
				flag = true;
			}
		} else if ("两级审核".equalsIgnoreCase(shjb)) {
			if ("未审核".equalsIgnoreCase(shzt1) && "未审核".equalsIgnoreCase(shzt2)) {
				flag = true;
			}
		} else if ("三级审核".equalsIgnoreCase(shjb)) {
			if ("未审核".equalsIgnoreCase(shzt1) && "未审核".equalsIgnoreCase(shzt2)
					&& "未审核".equalsIgnoreCase(shzt3)) {
				flag = true;
			}
		}

		return flag;
	}

	/**
	 * @param outValue
	 * @return
	 */
	public String[] getOutValue(String[] outValue) {

		String[] outZd = null;

		if (outValue != null && outValue.length > 0) {

			int n = 0;

			for (int i = 0; i < outValue.length; i++) {
				if (!outValue[i].contains("sys_nc")) {
					n++;
				}
			}

			outZd = new String[n];

			int m = 0;
			for (int i = 0; i < outValue.length; i++) {
				if (!outValue[i].contains("sys_nc")) {
					outZd[m] = outValue[i];
					m++;
				}
			}
		}
		return outZd;
	}

	/**
	 * 返回该生的困难级别
	 * 
	 * @param shjb
	 * @param sqzq
	 * @param xn
	 * @param xh
	 * @param xq
	 * @return
	 */

	public String getKnjbForXh(String shjb, String sqzq, String xn, String xh,
			String xq) {

		return dao.getKnjbForXh(shjb, sqzq, xn, xh, xq);

	}

	/**
	 * 闽江困难生统计
	 * 
	 * @param xmdm
	 * @param xzData
	 * @param tzData
	 * @param scData
	 * @param xydm
	 */
	private void editKnsData(String xmdm, List<HashMap<String, String>> xzData,
			List<HashMap<String, String>> tzData,
			List<HashMap<String, String>> scData, String xydm) {
		List<HashMap<String, String>> data = dao.getKnsData(xmdm, xydm);
		String sqzq = dao.getOneValue("xszz_zzxmb", "sqzq", "xmdm", xmdm);
		List<HashMap<String, String>> sxqData = new ArrayList<HashMap<String, String>>();
		List<HashMap<String, String>> bxqData = new ArrayList<HashMap<String, String>>();

		String xn = Base.currXn;
		String xq = Base.currXq;

		HashMap<String, String> xnxqMap = new HashMap<String, String>();
		xnxqMap.put("xn", xn);
		xnxqMap.put("xq", xq);
		xnxqMap = DAO.getInstance().getBeforeXq(xnxqMap);

		String beforeXq = xnxqMap.get("xq");
		String beforeXn = xnxqMap.get("xn");

		// 申请周期为学期
		if ("学期".equals(sqzq)) {
			for (HashMap<String, String> map : data) {
				if (xn.equals(map.get("xn")) && xq.equals(map.get("xq"))) {
					bxqData.add(map);// 添加到本学期数据集
				}

				if (beforeXq.equals(map.get("xq"))
						&& beforeXn.equals(map.get("xn"))) {
					sxqData.add(map);// 上学期数据集
				}
			}
		} else {// 申请周期为学年
			beforeXn = Base.beforXn;
			for (HashMap<String, String> map : data) {
				if (xn.equals(map.get("xn"))) {
					bxqData.add(map);// 添加到本学期数据集
				}

				if (beforeXn.equals(map.get("xn"))) {
					sxqData.add(map);// 上学期数据集
				}
			}
		}

		data.removeAll(bxqData);// 从data数据集删除本学期数据

		// 本学期数据，若不在data学生集即新增，否则为调整
		for (HashMap<String, String> map : bxqData) {
			String xh = map.get("xh");
			if (!isExists(data, xh, "xh")) {
				xzData.add(map);
			} else {
				tzData.add(map);
			}

		}

		// data数据集里的学生不在本学期数据集即删除
		for (HashMap<String, String> map : data) {
			String xh = map.get("xh");
			if (!isExists(bxqData, xh, "xh")) {
				scData.add(map);
			}

		}
	}

	/**
	 * 判断集合中是否存在某个值
	 * 
	 * @param data
	 * @param value
	 * @param key
	 * @return
	 */
	public boolean isExists(List<HashMap<String, String>> data, String value,
			String key) {
		for (HashMap<String, String> map : data) {
			if (value.equals(map.get(key))) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 就读期间累计不计格必修课目数
	 * 
	 * @param xh
	 * @return
	 */
	public String getBjgs(String xh) {

		return dao.getBjgs(xh);
	}

	/**
	 * 是否违纪
	 * 
	 * @param model
	 * @return
	 */
	public String isWj(XszzTyForm model) {
		// 项目相关信息
		model.setPkValue(model.getXmdm());
		HashMap<String, String> map = getXmxgInfo(model);

		return dao.isWj(model, map);
	}

	/**
	 * 宁波城市上半年德育测评分
	 * 
	 * @param model
	 * @return
	 */
	public String sxnDyf(XszzTyForm model) {
		return dao.sxnDyf(model);
	}

	/**
	 * 查询学生成绩信息
	 */
	public List<HashMap<String, String>> getXscjList(String xxdm, String xmb,
			XszzTyForm myform) {
		List<HashMap<String, String>> list = null;
		if (Globals.XXDM_HAINDX.equalsIgnoreCase(xxdm)) {
			// 海南大学国家励志奖学金
			list = dao.getXscjList(myform);
		}

		return list;
	}

	/**
	 * 获得区县
	 * 
	 * @param dm
	 * @param ids
	 * @return
	 */
	public List<HashMap<String, String>> getQxmc(String[] dm, String[] ids) {
		StringBuilder sql = new StringBuilder();
		for (int i = 0; i < dm.length; i++) {
			sql.append("select qxmc,'").append(ids[i]).append(
					"' id from dmk_qx where qxdm=?");
			if (i != dm.length - 1) {
				sql.append(" union all ");
			}
		}

		return CommonQueryDAO.commonQueryforList("", "", dm, new String[] {
				"qxmc", "id" }, sql.toString());
	}

	public HashMap<String, String> getXxcjZcxx(String xxdm, String xmb,
			HashMap<String, String> map) {
		if (Globals.XXDM_HAINDX.equalsIgnoreCase(xxdm)) {// 海南大学
			if ("xszz_gjjxjb".equalsIgnoreCase(xmb)) {// 国家奖学金
				// 本学年必修课程数
				// 优秀数
				// 良好数
				map.putAll(dao.getXnbxkxx(map));
				// 专业成绩排名
				// 暂时不知道如何取

				// 综合考评成绩
				// 综合考评成绩排名
				map.putAll(dao.getZhszcpxx(map));
			}
		}
		if (Globals.XXDM_HZSFXY.equalsIgnoreCase(xxdm)) {// 湖州师范
			if ("xszz_gjjxjb".equalsIgnoreCase(xmb) || "gjlzjxj".equalsIgnoreCase(xmb) ) {// 国家奖学金和国家励志奖学金
				// 本学年必修课程数
				// 优秀数
				// 良好数
				map.putAll(dao.getXnbxkxx(map));
				// 专业成绩排名
				// 暂时不知道如何取

				// 综合考评成绩
				// 综合考评成绩排名
				map.putAll(dao.getZhszcpxx(map));
			}
		}
		//
		return map;
	}

	/**
	 * 打印资助统计报表
	 * 
	 * @param form
	 * @param os
	 */
	public void printZztjbb(XszzTyForm form, OutputStream os) {
		String xxdm = StandardOperation.getXxdm();// 学校代码
		XszzCommTjbbService service = null;
//		String className="";
//		
//		Class clazz = Class.forName(className);
		
		if (Globals.XXDM_HAINDX.equalsIgnoreCase(xxdm)) {
			// 海南大学
			//service = new aaa.xxx.xxdm.XszzHndxService();
		}// end 海南大学
		// 其它学校请new 一个新的service,在service中实现相应的方法

		if (Globals.XXDM_ZJKJXY.equalsIgnoreCase(xxdm)) {
			service = new XszzZjkjService();
		}

		// 中国地大
		if (Globals.XXDM_ZGDZDX.equalsIgnoreCase(xxdm)) {
			service = new XszzZjkjService();
		}

		// 南宁职业
		if (Globals.XXDM_NNZYJSXY.equalsIgnoreCase(xxdm)) {
			service = new XszzNnzyService();
		}

		// 信阳师范
		if (Globals.XXDM_XYSFXY.equalsIgnoreCase(xxdm)) {
			service = new XszzXysfService();
		}

		// 浙江邮电
		if (Globals.XXDM_ZJYDZYJSXY.equalsIgnoreCase(xxdm)) {
			service = new XszzZjydService();
		}

		//天津工业大学
		if (Globals.XXDM_TJGYDX.equals(xxdm)){
			service = new XszzPrintService();
		}
		
		//华中农业大学
		if (Globals.XXDM_HZNYDX.equals(xxdm)){
			service = new XszzHznyService();
		}
		
		if (service != null) {
			service.printZztjbb(form, os);
		}
	}

	/**
	 * 删除项目审核状态
	 * 
	 * @param pkValue
	 * @param xmb
	 * @return
	 * @throws Exception
	 */
	public boolean delXmshZt(String[] pkValue, String xmb) throws Exception {

		if (null != pkValue && pkValue.length > 0) {
			return dao.delXmshZt(pkValue, xmb);
		} else {
			return false;
		}
	}

	/**
	 * 根据项目代码获取该项目的详细信息
	 * 
	 * @author qph
	 * @param xmdm
	 * @return
	 */
	public HashMap<String, String> getXmIfno(String xmdm) {

		return new XszzDAO().getXszzInfoByXmdm(xmdm);
	}

	/**
	 * 资助项目批量续办
	 * 
	 * @param pkValues
	 * @param model
	 * @return
	 */
	public boolean saveZzxb(String[] pkValues, XszzTyForm model) {

		if (null != pkValues && pkValues.length > 0) {
			try {
				return dao.saveZzxb(pkValues, model);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return false;
	}

	/**
	 * 根据学期代码获取学期名称
	 * 
	 * @param xqdm
	 * @return
	 */
	public String getXqMc(String xqdm) {
		return XszzCommDAO.getXqMc(xqdm);
	}
	
	/**
	 * 删除jtqkdcb表中不存在
	 * 的学生的家庭成员记录
	 * @return boolean
	 * @throws Exception
	 * author qlj
	 */
	public boolean delXsJtcy() throws Exception{
		
		return dao.delXsJtcy();
	}
	
	
	/**
	 * 若困难生认定作为单独模块，并且困难生认定包含了家庭情况调查时作数据同步
	 * @return
	 */
	public boolean saveJtqkdcFromKns(String xh,String sqsj){
		
		try {
			
			if (StringUtils.isNotNull(xh) && StringUtils.isNotNull(sqsj)){
				dao.delJtqkdc(xh, sqsj);
			} else {
				dao.delAllJtqkdc();
			}
			
			return dao.saveJtqkdcDataFromKns() && dao.saveJtqkdcShztFromKns();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
	/**
	 * 按学年、学号查询不及格成绩
	 * @param xn
	 * @return
	 */
	public List<HashMap<String,String>> getBjgcjByXn(String xn,String xh){
		return dao.getBjgcjByXn(xn, xh);
	}
	
	
	/**
	 * 根据学号查询困难生信息
	 * @param xh
	 * @return
	 */
	public List<HashMap<String,String>> getKnsInfo(String xh){
		
		return dao.getKnsInfo(xh);
	}
	
	public List<HashMap<String,String>>getPjpyInfo(String xmlx,String xh){
		
		List<HashMap<String,String>>pjpyList=dao.getPjpyInfo(xh);
		List<HashMap<String,String>>pjList=new ArrayList<HashMap<String,String>>();
		for(int i=0;i<pjpyList.size();i++){
			HashMap<String,String>jxjMap=pjpyList.get(i);
			if(xmlx.equalsIgnoreCase(jxjMap.get("xmlx"))){
				pjList.add(jxjMap);
			}
		}
		return pjList;
	}
	
	
	/**
	 * 获取不可兼得项目
	 * @param xmdm
	 * @return
	 */
	public String[] getBkjdxm(String xmdm){
		
		try {
			return dao.getBkjdxm(xmdm);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	/**
	 * 查找已经存在的不可兼得项目
	 */
	public Map<String,String> getExistsBkjdxm(String xh , String xmdm){
		return dao.getExistsBkjdxm(xh, xmdm);
	}
	
	/**
	 * 判断项目是否是再次申请
	 * @param xh
	 * @param xmdm
	 * @return
	 * @author honglin
	 * @date 2013-3-12
	 */
	public List<HashMap<String,String>> getSfccsq(String xh , String xmdm){
		return dao.getSfccsq(xh,xmdm);
	}

}