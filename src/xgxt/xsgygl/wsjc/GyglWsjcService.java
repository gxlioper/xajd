package xgxt.xsgygl.wsjc;

import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCell;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import xgxt.DAO.DAO;
import xgxt.DAO.ExcelMB;
import xgxt.action.Base;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.dtjs.gdby.tygl.BasicExtendService;
import xgxt.form.SaveForm;
import xgxt.form.User;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.ExcelMethods;
import xgxt.utils.MakeQuery;
import xgxt.utils.Pages;
import xgxt.utils.String.StringUtils;
import xgxt.xsgygl.GyglTyForm;
import xgxt.xsgygl.GyglTyService;

public class GyglWsjcService extends GyglTyService {

	GyglWsjcDAO dao = new GyglWsjcDAO();

	/**
	 * 获取卫生检查对象
	 * 
	 * @param request
	 * @return User
	 */
	public GyglWsjcModel getWsjc() {

		GyglWsjcModel wsjc = new GyglWsjcModel();

		// 参数设置信息
		HashMap<String, String> map = getCsszInfo();
		// 等级列表
		String tableName = "gygl_wsjc_wsfdjb";
		String query = " order by to_number(djpx) ";
		String[] colList = new String[] { "wsfxx", "wsfsx", "wsfdj" };
		List<HashMap<String, String>> list = dao.getRsList(tableName, query,
				new String[] {}, colList, "");

		// 检查周期
		wsjc.setJczq(map.get("jczq"));
		// 录入形式
		wsjc.setLrxs(map.get("lrxs"));
		// 起始日期
		wsjc.setQsrq(map.get("qsrq"));
		// 总共周次
		wsjc.setZgzc(map.get("zgzc"));
		// 关联等级
		wsjc.setGldj(map.get("gldj"));
		// 关联分数
		wsjc.setGlfs(map.get("glfs"));
		// 基础分
		wsjc.setJcf(map.get("jcf"));
		// 等级列表
		wsjc.setWsdjList(list);

		return wsjc;
	}

	/**
	 * 获得参数设置相关内容
	 * 
	 * @author luojw
	 */
	public HashMap<String, String> getCsszInfo() {

		String tableName = "gygl_wsjc_csszb";
		// 主键
		String pk = "rownum";
		// 主键值得
		String pkValue = "1";
		// 输出字段
		String[] colList = new String[] { "gldj", "glfs", "jcf", "jczq",
				"lrxs", "qsrq", "zgzc" };

		// 项目相关信息
		HashMap<String, String> map = dao.getRsInfo(tableName, pk, pkValue,
				colList);

		setCsszDef(map);

		return map;
	}

	/**
	 * 设置参数设置默认值
	 * 
	 * @author luojw
	 */
	public void setCsszDef(HashMap<String, String> map) {

		// 检查周期
		String jczq = map.get("jczq");

		if (Base.isNull(jczq)) {
			map.put("jczq", "日");
			map.put("lrxs", "分数");
			map.put("gldj", "否");
			map.put("glfs", "否");
		}
	}

	/**
	 * 保存参数设置相关内容
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public Boolean saveCssz(GyglTyForm model, User user,
			HttpServletRequest request) throws Exception {

		DAO dao = DAO.getInstance();

		// 标明
		String tableName = "gygl_wsjc_csszb";
		// 单一字段
		String[] onezd = new String[] { "gldj", "glfs", "jcf", "jczq", "lrxs",
				"qsrq", "zgzc" };
		// 检查周期
		String jczq = model.getJczq();
		// 周期为日的情况，清空总周次和起始时间
		if ("日".equalsIgnoreCase(jczq)) {
			model.setZgzc("");
			model.setQsrq("");
		}
		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(tableName);
		saveForm.setPk("1");
		saveForm.setPkValue(new String[] { "1" });
		saveForm.setOnezd(onezd);

		boolean flag = dao.submitData(saveForm, model, request);

		if (flag) {
			flag = saveCsszFsDj(model, user);
		}
		return flag;
	}

	/**
	 * 保存卫生等级
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public Boolean saveCsszFsDj(GyglTyForm model, User user) throws Exception {

		// 标明
		String tableName = "gygl_wsjc_wsfdjb";
		// 批量字段
		String[] arrzd = new String[] { "djpx", "wsfsx", "wsfxx", "wsfdj" };

		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(tableName);
		saveForm.setPk("1");
		saveForm.setPkValue(new String[] { "1" });
		saveForm.setArrzd(arrzd);

		boolean flag = dao.saveData(saveForm, model, user);

		return flag;
	}

	/**
	 * 导出卫生检查空白报表
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public void expWsjcKbbb(GyglTyForm model, GyglWsjcModel wsjcModel,
			OutputStream os) throws Exception {

		String title = "卫生检查";

		// 周次
		String zc = model.getZc();
		// 检查周期
		String jczq = wsjcModel.getJczq();
		// 录入形式
		String lrxs = wsjcModel.getLrxs();
		// 总共周次
		String zgzc = wsjcModel.getZgzc();

		// 固定列
		String[] gdName = new String[] { "学年", "学期", "学期名称", "年度", Base.YXPZXY_KEY+"代码",
				Base.YXPZXY_KEY+"名称", "校区代码", "校区名称", "楼栋代码", "楼栋名称", "层数", "寝室号" };

		int count = "周".equalsIgnoreCase(jczq) ? gdName.length + 2
				: gdName.length + 2;
		// 全部列
		String[] topName = new String[count];

		for (int i = 0; i < count; i++) {
			if (i < gdName.length) {
				topName[i] = gdName[i];
			} else if ("周".equalsIgnoreCase(jczq) && i != count - 1) {
				topName[i] = "周次";
			} else if ("日".equalsIgnoreCase(jczq) && i != count - 1) {
				topName[i] = "日期";
			} else {
				topName[i] = "分数".equalsIgnoreCase(lrxs) ? "卫生分(限数字)" : "卫生等级";
			}
		}

		// 导出报表表头
		List<HashMap<String, String>> topTr = getTopList(topName);
		// 导出报表的固定数据
		ArrayList<String[]> gdlist = dao.getExpKbbbList(model, wsjcModel);
		// 最终导出数据
		ArrayList<String[]> list = new ArrayList<String[]>();

		if (gdlist != null && gdlist.size() > 0) {
			if ("周".equalsIgnoreCase(jczq)) {
				if (Base.isNull(zc) && !Base.isNull(zgzc)) {
					for (int i = 0; i < gdlist.size(); i++) {
						for (int j = 1; j <= Integer.parseInt(zgzc); j++) {
							String[] qsInfo = gdlist.get(i);
							ArrayList<String> info = new ArrayList<String>(
									Arrays.asList(qsInfo));
							info.add(String.valueOf(j));
							info.add("");
							list.add(info.toArray(new String[] {}));
						}
					}
				} else {
					for (int i = 0; i < gdlist.size(); i++) {
						String[] qsInfo = gdlist.get(i);
						ArrayList<String> info = new ArrayList<String>(Arrays
								.asList(qsInfo));
						info.add(zc);
						info.add("");
						list.add(info.toArray(new String[] {}));
					}
				}
			} else {
				list = gdlist;
			}
		}
		expToExcel(title, topTr, list, os);
	}

	/**
	 * 获得卫生分录入表头
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getWsflrTop(GyglWsjcModel wsjcModel) {
		// 检查周期
		String jczq = wsjcModel.getJczq();
		// 录入形式
		String lrxs = wsjcModel.getLrxs();
		// 固定列
		String[] gdName = new String[] { Base.YXPZXY_KEY+"名称", "校区名称", "楼栋名称", "层数", "寝室号" };

		int count = "周".equalsIgnoreCase(jczq) ? gdName.length + 2
				: gdName.length + 1;
		// 全部列
		String[] topName = new String[count];

		for (int i = 0; i < count; i++) {
			if (i < gdName.length) {
				topName[i] = gdName[i];
			} else if ("周".equalsIgnoreCase(jczq) && i != count - 1) {
				topName[i] = "周次";
			} else {
				topName[i] = "分数".equalsIgnoreCase(lrxs) ? "卫生分" : "卫生等级";
			}
		}

		return getTopList(topName);
	}
	
	/**
	 * 获得卫生分录入列表
	 * 
	 * @author luojw
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @throws Exception
	 */
	public ArrayList<String[]> getWsflrList(GyglTyForm model,
			GyglWsjcModel wsjcModel) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

		// 录入形式
		String lrxs = wsjcModel.getLrxs();
		// 宿舍信息
		ArrayList<String[]> ssxxList = dao.getSsldList(model, wsjcModel);
		// 卫生分信息
		ArrayList<String[]> wsfList = dao.getWsfxxList(model);

		ArrayList<String[]> list = new ArrayList<String[]>();

		if (ssxxList != null && ssxxList.size() > 0) {

			for (int i = 0; i < ssxxList.size(); i++) {

				ArrayList<String> ssxx = new ArrayList<String>(Arrays
						.asList(ssxxList.get(i)));

				String sspk = ssxxList.get(i)[0];

				if (wsfList != null && wsfList.size() > 0) {

					for (int j = 0; j < wsfList.size(); j++) {
						
						String[] wsfInfo = wsfList.get(j);

						if (wsfInfo[0].equalsIgnoreCase(sspk)) {
							if ("分数".equalsIgnoreCase(lrxs)) {
								ssxx.add(wsfInfo[2]);
								ssxx.add(wsfInfo[4]);// 部门名称
								ssxx.add(wsfInfo[5]);// 检查部门
								ssxx.add(wsfInfo[6]);// 备注
							} else if ("等级".equalsIgnoreCase(lrxs)) {
								ssxx.add(wsfInfo[3]);
								ssxx.add(wsfInfo[4]);// 部门名称
								ssxx.add(wsfInfo[5]);// 检查部门
								ssxx.add(wsfInfo[6]);// 备注
							}
						}
					}
				}

				list.add(ssxx.toArray((new String[] {})));
			}
		}

		return list;
	}

	/**
	 * 保存卫生分
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public Boolean saveWsf(GyglTyForm model, GyglWsjcModel wsjcModel, User user)
			throws Exception {

		// 表名
		String tableName = "gygl_wsjc_wsfwhb";
		// 检查周期
		String jczq = wsjcModel.getJczq();
		// 录入形式
		String lrxs = wsjcModel.getLrxs();
		// 关联等级
		String gldj = wsjcModel.getGldj();
		// 非空字段
		String[] notnull = null;
		
		// 批量字段
		ArrayList<String> arrList = new ArrayList<String>();
		arrList.add("jcld");
		arrList.add("jccs");
		arrList.add("jcqs");
		
		if ("分数".equalsIgnoreCase(lrxs)) {
			arrList.add("wsffs");
			if ("是".equalsIgnoreCase(gldj)) {
				arrList.add("wsfdj");
			}
		} else {
			arrList.add("wsfdj");
		}

		String[] arrzd = arrList.toArray(new String[] {});

		// 单一字段
		ArrayList<String> oneList = new ArrayList<String>();
		oneList.add("lrr");
		oneList.add("lrrlx");
		oneList.add("lrsj");
		oneList.add("xn");
		oneList.add("xq");
		oneList.add("nd");
		oneList.add("jcsj");
		if ("周".equalsIgnoreCase(jczq)) {
			oneList.add("jczc");
		}
		String[] onezd = oneList.toArray(new String[] {});

		String pk = "jcld||jccs||jcqs";
		if ("周".equalsIgnoreCase(jczq)) {
			pk += "||jczc";
		} else {
			pk += "||jcsj";
		}
		// 检查楼栋
		String[] jcld = model.getJcld();
		// 检查层数
		String[] jccs = model.getJccs();
		// 检查寝室
		String[] jcqs = model.getJcqs();
		// 检查时间
		String jcsj = model.getJcsj();
		// 检查周次
		String jczc = model.getJczc();
		// 构建主键
		String[] pkValue = new String[jcld.length];
		for (int i = 0; i < jcld.length; i++) {
			if ("周".equalsIgnoreCase(jczq)) {
				pkValue[i] = jcld[i] + jccs[i] + jcqs[i] + jczc;
			} else {
				pkValue[i] = jcld[i] + jccs[i] + jcqs[i] + jcsj;
			}
		}

		model.setLrr(user.getUserName());
		model.setLrrlx(user.getUserType());
		model.setLrsj(getNowTime("YYYYMMDD"));
		
		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(tableName);
		saveForm.setPk(pk);
		saveForm.setPkValue(pkValue);
		saveForm.setArrzd(arrzd);
		saveForm.setOnezd(onezd);

		boolean flag = dao.saveData(saveForm, model, user);

		if (flag) {

			flag = dao.saveJcbm(model, saveForm);
		}

		return flag;
	}
	
	/**
	 * 修改卫生分
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public Boolean updateWsf(GyglTyForm model, GyglWsjcModel wsjcModel, User user)
			throws Exception {

		// 表名
		String tableName = "gygl_wsjc_wsfwhb";
		// 检查周期
		String jczq = wsjcModel.getJczq();

		// 单一字段
		String[] onezd =new String[]{"jcbm","bz"};

		String pk = "jcld||jccs||jcqs";
		if ("周".equalsIgnoreCase(jczq)) {
			pk += "||jczc";
		} else {
			pk += "||jcsj";
		}
		// 主键
		String[] ssbh = model.getPrimarykey_checkVal();
		// 检查时间
		String jcsj = model.getJcsj();
		// 检查周次
		String jczc = model.getJczc();
		// 构建主键
		String[] pkValue = new String[ssbh.length];
		for (int i = 0; i < ssbh.length; i++) {
			if ("周".equalsIgnoreCase(jczq)) {
				pkValue[i] = ssbh[i] + jczc;
			} else {
				pkValue[i] = ssbh[i] + jcsj;
			}
		}

		model.setJcbm(model.getBmdm());
		
		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(tableName);
		saveForm.setPk(pk);
		saveForm.setPkValue(pkValue);
		saveForm.setOnezd(onezd);

		boolean flag = dao.updateData(saveForm, model, user);

		return flag;
	}
	
	/**
	 * 是否设置参数设置
	 * 
	 * @author luojw
	 * 
	 */
	public Boolean isSz() {

		String jczq = dao.getOneValue("gygl_wsjc_csszb", "jczq", "1", "1");

		boolean flag = Base.isNull(jczq) ? false : true;

		return flag;
	}
	
	/**
	 * 获取系统时间所属周次
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public String getNowZc(String nowTime, GyglWsjcModel wsjcModel)
			throws Exception {

		// 起始日期
		String qsrq = wsjcModel.getQsrq();
		// 间隔天数
		String days = dao.getBetweenDate(nowTime, qsrq);

		String zc = String.valueOf(1 + Integer.parseInt(days) / 7);

		return zc;
	}
	
	/**
	 * 获得寝室卫生分情况
	 * 
	 * @author luojw
	 */
	public HashMap<String, String> getQsWsfInfo(GyglTyForm model,
			GyglWsjcModel wsjcModel) {
		return dao.getQsWsfInfo(model, wsjcModel);
	}
	
	/**
	 * 获得寝室入住情况
	 * 
	 * @author luojw
	 */
	public ArrayList<String[]> getQsrzInfo(GyglTyForm model) {
		return dao.getQsrzInfo(model);
	}

	/**
	 * 获得卫生分结果列表
	 * 
	 * @author luojw
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @throws Exception
	 */
	public ArrayList<String[]> getWsjcJgList(GyglTyForm model,
			GyglWsjcModel wsjcModel) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {	
		
		ArrayList<String[]> list = new ArrayList<String[]>();
		List<HashMap<String, String>> djList = wsjcModel.getWsdjList();
		ArrayList<String[]> rsList = new ArrayList<String[]>();

		// 类型
		String lx = model.getLx();
		// 查询等级
		String cxdj = model.getDj();
		// 查询分数下限
		String cxxx = model.getFsxx();
		// 查询分数上限
		String cxsx = model.getFssx();
		// 录入形式
		String lrxs = wsjcModel.getLrxs();
		// 是否关联等级
		String gldj = wsjcModel.getGldj();
		// 是否关联分数
		String glfs = wsjcModel.getGlfs();
		
		if ("qs".equalsIgnoreCase(lx)) {//寝室
			
			list = dao.getQsWsjcInfoList(model, wsjcModel);
			
			if (list != null && list.size() > 0) {

				for (int i = 0; i < list.size(); i++) {

					ArrayList<String> arrList = new ArrayList<String>(Arrays
							.asList(list.get(i)));

					boolean flag = true;

					if ("分数".equalsIgnoreCase(lrxs)
							&& "是".equalsIgnoreCase(gldj)) {
						String fs = list.get(i)[10];

						//获取卫生分所关联的等级
						for (int j = 0; j < djList.size(); j++) {

							String xx = djList.get(j).get("wsfxx");
							String sx = djList.get(j).get("wsfsx");
							String dj = djList.get(j).get("wsfdj");

							if (Float.parseFloat(fs) >= Float.parseFloat(xx)
									&& Float.parseFloat(fs) <= Float
											.parseFloat(sx)) {
								arrList.add(dj);
								break;
							}
						}

						//判断是否需要根据所关联的等级进行查询
						if (!Base.isNull(cxdj)
								&& !cxdj.equalsIgnoreCase(arrList.get(arrList
										.size() - 1))) {
							flag = false;
						}
					}

					if ("等级".equalsIgnoreCase(lrxs)
							&& "是".equalsIgnoreCase(glfs)) {
						
						String wsdj = list.get(i)[11];

						//获取卫等级所关联的分数
						for (int j = 0; j < djList.size(); j++) {

							String sx = djList.get(j).get("wsfsx");
							String dj = djList.get(j).get("wsfdj");

							if (dj.equalsIgnoreCase(wsdj)) {
								arrList.add(sx);
								break;
							}
						}
						
						// 判断是否需要根据所关联的分数进行查询
						String fs = arrList.get(arrList.size() - 1);

						if (!Base.isNull(cxxx)
								&& Float.parseFloat(fs) < Float
										.parseFloat(cxxx)) {
							flag = false;
						}

						if (!Base.isNull(cxsx)
								&& Float.parseFloat(fs) > Float
										.parseFloat(cxsx)) {
							flag = false;
						}
					}
					
					if (flag) {
						rsList.add(arrList.toArray(new String[] {}));
					}
				}
			}
			
		}else{//学生
			list = dao.getXsWsjcInfoList(model, wsjcModel);
			
			for (int i = 0; i < list.size(); i++) {

				ArrayList<String> arrList = new ArrayList<String>(Arrays
						.asList(list.get(i)));

				boolean flag = true;
				
				if ("分数".equalsIgnoreCase(lrxs)
						&& "是".equalsIgnoreCase(gldj)) {
					String fs = list.get(i)[12];

					//获取卫生分所关联的等级
					for (int j = 0; j < djList.size(); j++) {

						String xx = djList.get(j).get("wsfxx");
						String sx = djList.get(j).get("wsfsx");
						String dj = djList.get(j).get("wsfdj");

						if (Float.parseFloat(fs) >= Float.parseFloat(xx)
								&& Float.parseFloat(fs) <= Float
										.parseFloat(sx)) {
							arrList.add(dj);
							break;
						}
					}

					//判断是否需要根据所关联的等级进行查询
					if (!Base.isNull(cxdj)
							&& !cxdj.equalsIgnoreCase(arrList.get(arrList
									.size() - 1))) {
						flag = false;
					}
				}
				
				if ("等级".equalsIgnoreCase(lrxs)
						&& "是".equalsIgnoreCase(glfs)) {
					
					String wsdj = list.get(i)[13];

					//获取卫等级所关联的分数
					for (int j = 0; j < djList.size(); j++) {

						String sx = djList.get(j).get("wsfsx");
						String dj = djList.get(j).get("wsfdj");

						if (dj.equalsIgnoreCase(wsdj)) {
							arrList.add(sx);
							break;
						}
					}
					
					// 判断是否需要根据所关联的分数进行查询
					String fs = arrList.get(arrList.size() - 1);

					if (!Base.isNull(cxxx)
							&& Float.parseFloat(fs) < Float
									.parseFloat(cxxx)) {
						flag = false;
					}

					if (!Base.isNull(cxsx)
							&& Float.parseFloat(fs) > Float
									.parseFloat(cxsx)) {
						flag = false;
					}
				}
				
				if (flag) {
					rsList.add(arrList.toArray(new String[] {}));
				}
			}
		}
		
		return rsList;
	}
	
	/**
	 * 获得寝室卫生分情况(结果查询)
	 * 
	 * @author luojw
	 */
	public HashMap<String, String> getWsfjgInfo(GyglTyForm model,
			GyglWsjcModel wsjcModel) {

		String lx = model.getLx();

		HashMap<String, String> map = new HashMap<String, String>();

		if ("qs".equalsIgnoreCase(lx)) {
			map = dao.getQsWsfjg(model, wsjcModel);
		} else {
			map = dao.getXsWsfjg(model, wsjcModel);
		}

		return map;
	}
	
	/**
	 * 修改卫生分
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public Boolean editWsf(GyglTyForm model, HttpServletRequest request)
			throws Exception {

		String tableName = "gygl_wsjc_wsfwhb";

		boolean flag = StandardOperation.update(tableName, new String[] {
				"wsffs", "bz" },
				new String[] { model.getFssx(), model.getBz() },
				"jcld||jccs||jcqs||jcsj||jczc", model.getPkValue(), request);

		return flag;
	}
	
	/**
	 * 删除卫生分
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public Boolean delWsf(GyglTyForm model, User user) throws Exception {

		String tableName = "gygl_wsjc_wsfwhb";
		String pk = "jcld||jccs||jcqs||jcsj||jczc";
		String[] pkValue = model.getPrimarykey_checkVal();

		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(tableName);
		saveForm.setPk(pk);
		saveForm.setPkValue(pkValue);

		boolean flag = dao.delDate(saveForm, model, user);

		return flag;
	}
	
	/**
	 * 获得卫生分结果列表
	 * 
	 * @author luojw
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @throws Exception
	 */
	public ArrayList<String[]> getWsfTjList(GyglTyForm model,
			GyglWsjcModel wsjcModel, List<HashMap<String, String>> topTr,
			HttpServletRequest request) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {	
		
		ArrayList<String[]> rsList = new ArrayList<String[]>();

		// 统计方式
		String tjfs = model.getTjfs();

		if ("qs".equalsIgnoreCase(tjfs)) {// 寝室
			
			ArrayList<String[]> zqsList = dao.getQszsTjList(model, wsjcModel);
			ArrayList<String[]> pmList = dao.getQspmTjList(model, wsjcModel);
			
			if (zqsList != null && zqsList.size() > 0) {

				for (int i = 0; i < zqsList.size(); i++) {
					
					boolean flag = true;
					
					if (pmList != null && pmList.size() > 0) {
						
						ArrayList<String> arrList = new ArrayList<String>(Arrays
								.asList(zqsList.get(i)));

						String[] qsInfo = zqsList.get(i);
						
						for (int j = 0; j < pmList.size(); j++) {
							
							String[] pmInfo = pmList.get(j);
							
							if (qsInfo[0].equalsIgnoreCase(pmInfo[0])) {
								
								//总寝室数
								String zqss = qsInfo[qsInfo.length-1];
								
								for (int k = 0; k < pmInfo.length; k++) {
									
									arrList.add(pmInfo[k]);
									
									if (k != 0 && k % 2 == 0) {
										// 等级所占比例
										String bl = Math.round(Float
												.parseFloat(pmInfo[k])
												/ Float.parseFloat(zqss) * 100)+"";
										if(!"0".equalsIgnoreCase(bl)){
											bl+="%";
										}
										
										arrList.add(bl);
									}
								}
								
								flag = false;
								
								break;
							}
						}
						
						int num = (pmList.get(0).length - 1)
								+ (pmList.get(0).length - 1) / 2;

						if (flag) {

							for (int k = 0; k <= num; k++) {
								arrList.add("无");
							}
						}

						request.setAttribute("num", arrList.size() - num);
						
						rsList.add(arrList.toArray(new String[] {}));
					}
				}
			}
		} else {// 学生
			ArrayList<String[]> zrsList = dao.getXszsTjList(model, wsjcModel);
			ArrayList<String[]> pmList = dao.getXspmTjList(model, wsjcModel);
			
			if (zrsList != null && zrsList.size() > 0) {

				for (int i = 0; i < zrsList.size(); i++) {
					
					boolean flag = true;
					
					ArrayList<String> arrList = new ArrayList<String>(Arrays
							.asList(zrsList.get(i)));
					
					if (pmList != null && pmList.size() > 0) {

						String[] xsInfo = zrsList.get(i);
									
						for (int j = 0; j < pmList.size(); j++) {
							
							String[] pmInfo = pmList.get(j);
							
							if (xsInfo[0].equalsIgnoreCase(pmInfo[0])) {
								
								//总人数
								String zrs = xsInfo[xsInfo.length-1];
								
								for (int k = 0; k < pmInfo.length; k++) {
									arrList.add(pmInfo[k]);
									if (k != 0 && k % 2 == 0) {
										// 等级所占比例
										String bl = Math.round(Float
												.parseFloat(pmInfo[k])
												/ Float.parseFloat(zrs) * 100)
												+ "";
										if (!"0".equalsIgnoreCase(bl)) {
											bl += "%";
										}
										arrList.add(bl);
									}
								}
								
								flag = false;
								
								break;
							}
						}
						
						int num = (pmList.get(0).length - 1)
								+ (pmList.get(0).length - 1) / 2;

						if (flag) {

							for (int k = 0; k <= num; k++) {
								arrList.add("无");
							}
						}

						request.setAttribute("num", arrList.size() - num);
						
						rsList.add(arrList.toArray(new String[] {}));
					}
				}
			}
		}
		
		return rsList;
	}
	
	/**
	 * 获得统计特殊表头
	 * 
	 * @author luo
	 */
	public List<HashMap<String, String>> getWsfTjTop(GyglTyForm model,GyglWsjcModel wsjcModel) {

		DAO dao = DAO.getInstance();

		ArrayList<String> colListCN = new ArrayList<String>();
		ArrayList<String> colListEN = new ArrayList<String>();

		// 统计方式
		String tjfs = model.getTjfs();
		// 统计范围
		String tjfw = model.getTjfw();
		// 年级
		String nj = model.getNj();
		// 学院
		String xydm = model.getXydm();
		// 专业
		String zydm = model.getZydm();
		// 班级
		String bjdm = model.getBjdm();
		// 校区
		String xqdm = model.getXqdm();
		// 楼栋
		String lddm = model.getLddm();
		// 层数
		String cs = model.getCs();
		// 寝室
		String qsh = model.getQsh();

		if ("nj".equalsIgnoreCase(tjfw)) {// 统计范围（年级）
			colListCN.add("年级");
			colListEN.add("nj");
		} else if ("xy".equalsIgnoreCase(tjfw)) {// 统计范围（学院）
			colListCN.add("院系");
			colListEN.add("xymc");
		} else if ("zy".equalsIgnoreCase(tjfw)) {// 统计范围（专业）
			colListCN.add("专业");
			colListEN.add("zymc");
		} else if ("bj".equalsIgnoreCase(tjfw)) {// 统计范围（班级）
			colListCN.add("班级");
			colListEN.add("bjmc");
		}
		
		if (!Base.isNull(nj) && !"nj".equalsIgnoreCase(tjfw)) {
			colListCN.add("年级");
			colListEN.add("nj");
		}

		if (!Base.isNull(xydm) && !"xy".equalsIgnoreCase(tjfw)) {
			colListCN.add("院系名称");
			colListEN.add("xymc");
		}

		if (!Base.isNull(zydm) && !"zy".equalsIgnoreCase(tjfw)) {
			colListCN.add("专业名称");
			colListEN.add("zymc");
		}

		if (!Base.isNull(bjdm) && !"bj".equalsIgnoreCase(tjfw)) {
			colListCN.add("班级名称");
			colListEN.add("bjmc");
		}

		if ("qs".equalsIgnoreCase(tjfs)) {
			colListCN.add("总寝室");
			colListEN.add("zqs");
		} else {
			colListCN.add("总人数");
			colListEN.add("zrs");
		}
		

		String dj = model.getDj();
		
		if (Base.isNull(dj)) {
			// 等级列表
			List<HashMap<String, String>> wsdjList = wsjcModel.getWsdjList();

			if (wsdjList != null && wsdjList.size() > 0) {
				for (int i = 0; i < wsdjList.size(); i++) {
					
					String wsfdj = wsdjList.get(i).get("wsfdj");

					if ("qs".equalsIgnoreCase(tjfs)) {
						colListCN.add(wsfdj + "寝室数");
						colListEN.add("djqss");
					} else {
						colListCN.add(wsfdj + "人数");
						colListEN.add("djqss");
					}

					if ("qs".equalsIgnoreCase(tjfs)) {// 寝室
						colListCN.add(wsfdj + "寝室数排名");
						colListEN.add("djpm");
					} else {
						colListCN.add(wsfdj + "人数数排名");
						colListEN.add("djpm");
					}
					
					colListCN.add(wsfdj + "率");
					colListEN.add("djqsl");
				}
			}
		} else {

			if ("qs".equalsIgnoreCase(tjfs)) {
				colListCN.add(dj + "寝室数");
				colListEN.add("djqss");
			} else {
				colListCN.add(dj + "人数");
				colListEN.add("djqss");
			}

			if ("qs".equalsIgnoreCase(tjfs)) {// 寝室
				colListCN.add(dj + "寝室数排名");
				colListEN.add("djpm");
			} else {
				colListCN.add(dj + "人数排名");
				colListEN.add("djpm");
			}

			colListCN.add(dj + "率");
			colListEN.add("djqsl");

		}
		
		return dao.arrayToList(colListEN.toArray(new String[] {}), colListCN
				.toArray(new String[] {}));
	}
	
	/**
	 * 导出卫生检查信息
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public void wsjcExp(GyglTyForm model, GyglWsjcModel wsjcModel,
			OutputStream os) throws Exception {
		
		String lx = model.getLx();

		if ("qs".equalsIgnoreCase(lx)) {
			wsjcQsExp(model, wsjcModel, os);
		}
	}
	
	/**
	 * 导出卫生检查信息
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public void wsjcQsExp(GyglTyForm model, GyglWsjcModel wsjcModel,
			OutputStream os) throws Exception {

		String title = "卫生检查";

		// 类型
		String lx = model.getLx();
		// 录入形式
		String lrxs = wsjcModel.getLrxs();
		// 关联等级
		String gldj = wsjcModel.getGldj();
		// 关联分数
		String glfs = wsjcModel.getGlfs();

		ArrayList<String> topName = new ArrayList<String>();
		
		//topName.add("主键");
		if ("xs".equalsIgnoreCase(lx)) {
			topName.add("学号");
			topName.add("姓名");
		}
		topName.add("学年");
		topName.add("学期");
		topName.add("学期名称");
		topName.add("年度");
		topName.add("校区代码");
		topName.add("校区名称");
		topName.add("楼栋代码");
		topName.add("楼栋名称");
		topName.add("层数");
		topName.add("寝室号");
		topName.add("检查周次");
		topName.add("检查时间");
		topName.add("时间名称");
		topName.add("检查部门");
		topName.add("部门名称");
		if ("分数".equalsIgnoreCase(lrxs)) {
			topName.add("卫生分");
			if ("是".equalsIgnoreCase(gldj)) {
				topName.add("卫生等级");
			}
		}
		if ("等级".equalsIgnoreCase(lrxs)) {
			topName.add("卫生等级");
			if ("是".equalsIgnoreCase(glfs)) {
				topName.add("卫生分");
			}
		}
		
		// 导出报表表头
		List<HashMap<String, String>> topTr = getTopList(topName.toArray(new String[]{}));
		// 导出数据
		ArrayList<String[]> list = getWsjcExpList(model, wsjcModel);

		expToExcel(title, topTr, list, os);
	}
	
	/**
	 * 获得卫生分导出列表
	 * 
	 * @author luojw
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @throws Exception
	 */
	private ArrayList<String[]> getWsjcExpList(GyglTyForm model,
			GyglWsjcModel wsjcModel) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {	
		
		ArrayList<String[]> list = new ArrayList<String[]>();
		List<HashMap<String, String>> djList = wsjcModel.getWsdjList();
		ArrayList<String[]> rsList = new ArrayList<String[]>();

		// 类型
		String lx = model.getLx();
		// 查询等级
		String cxdj = model.getDj();
		// 查询分数下限
		String cxxx = model.getFsxx();
		// 查询分数上限
		String cxsx = model.getFssx();
		// 录入形式
		String lrxs = wsjcModel.getLrxs();
		// 是否关联等级
		String gldj = wsjcModel.getGldj();
		// 是否关联分数
		String glfs = wsjcModel.getGlfs();
		
		if ("qs".equalsIgnoreCase(lx)) {//寝室
			
			list = dao.getQsWsjcExpList(model, wsjcModel);
			
			if (list != null && list.size() > 0) {

				for (int i = 0; i < list.size(); i++) {

					ArrayList<String> arrList = new ArrayList<String>(Arrays
							.asList(list.get(i)));

					boolean flag = true;

					if ("分数".equalsIgnoreCase(lrxs)) {
						
						arrList.remove(arrList.size() - 1);
						
						if ("是".equalsIgnoreCase(gldj)) {

							String fs = list.get(i)[15];

							boolean isDj = false;
							// 获取卫生分所关联的等级
							for (int j = 0; j < djList.size(); j++) {

								String xx = djList.get(j).get("wsfxx");
								String sx = djList.get(j).get("wsfsx");
								String dj = djList.get(j).get("wsfdj");

								if (Float.parseFloat(fs) >= Float
										.parseFloat(xx)
										&& Float.parseFloat(fs) <= Integer
												.parseInt(sx)) {
									arrList.add(dj);
									isDj = true;
									break;
								}
							}

							if(!isDj){
								arrList.add("");
							}
							
							// 判断是否需要根据所关联的等级进行查询
							if (!Base.isNull(cxdj)
									&& !cxdj.equalsIgnoreCase(arrList
											.get(arrList.size() - 1))) {
								flag = false;
							}
						}
					}

					if ("等级".equalsIgnoreCase(lrxs)) {
						
						arrList.remove(arrList.size() - 2);
						
						if ("是".equalsIgnoreCase(glfs)) {
							String wsdj = list.get(i)[16];

							// 获取卫等级所关联的分数
							for (int j = 0; j < djList.size(); j++) {

								String sx = djList.get(j).get("wsfsx");
								String dj = djList.get(j).get("wsfdj");

								if (dj.equalsIgnoreCase(wsdj)) {
									arrList.add(sx);
									break;
								}
							}

							// 判断是否需要根据所关联的分数进行查询
							String fs = arrList.get(arrList.size() - 1);

							if (!Base.isNull(cxxx)
									&& Float.parseFloat(fs) < Integer
											.parseInt(cxxx)) {
								flag = false;
							}

							if (!Base.isNull(cxsx)
									&& Float.parseFloat(fs) > Integer
											.parseInt(cxsx)) {
								flag = false;
							}
						}
					}
					
					if (flag) {
						rsList.add(arrList.toArray(new String[] {}));
					}
				}
			}
			
		}else{//学生
			list = dao.getXsWsjcExpList(model, wsjcModel);
			
			for (int i = 0; i < list.size(); i++) {

				ArrayList<String> arrList = new ArrayList<String>(Arrays
						.asList(list.get(i)));

				boolean flag = true;
				
				if ("分数".equalsIgnoreCase(lrxs)) {

					arrList.remove(arrList.size() - 1);

					if ("是".equalsIgnoreCase(gldj)) {

						String fs = list.get(i)[17];

						boolean isDj = false;

						// 获取卫生分所关联的等级
						for (int j = 0; j < djList.size(); j++) {

							String xx = djList.get(j).get("wsfxx");
							String sx = djList.get(j).get("wsfsx");
							String dj = djList.get(j).get("wsfdj");

							if (Float.parseFloat(fs) >= Float.parseFloat(xx)
									&& Float.parseFloat(fs) <= Integer
											.parseInt(sx)) {
								arrList.add(dj);
								isDj = true;
								break;
							}
						}

						if (!isDj) {
							arrList.add("");
						}

						// 判断是否需要根据所关联的等级进行查询
						if (!Base.isNull(cxdj)
								&& !cxdj.equalsIgnoreCase(arrList.get(arrList
										.size() - 1))) {
							flag = false;
						}
					}
				}
				
				if ("等级".equalsIgnoreCase(lrxs)) {
					
					arrList.remove(arrList.size() - 2);
					
					if ("是".equalsIgnoreCase(glfs)) {
						
						String wsdj = list.get(i)[18];

						// 获取卫等级所关联的分数
						for (int j = 0; j < djList.size(); j++) {

							String sx = djList.get(j).get("wsfsx");
							String dj = djList.get(j).get("wsfdj");

							if (dj.equalsIgnoreCase(wsdj)) {
								arrList.add(sx);
								break;
							}
						}

						// 判断是否需要根据所关联的分数进行查询
						String fs = arrList.get(arrList.size() - 1);

						if (!Base.isNull(cxxx)
								&& Float.parseFloat(fs) < Integer
										.parseInt(cxxx)) {
							flag = false;
						}

						if (!Base.isNull(cxsx)
								&& Float.parseFloat(fs) > Integer
										.parseInt(cxsx)) {
							flag = false;
						}
					}
				}
				
				if (flag) {
					rsList.add(arrList.toArray(new String[] {}));
				}
			}
		}
		
		return rsList;
	}
	
	/**
	 * 导出卫生检查统计信息
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public void wsjcTjExp(GyglTyForm model, GyglWsjcModel wsjcModel,
			OutputStream os, List<HashMap<String, String>> topTr,
			HttpServletRequest request) throws Exception {

		String title = "卫生检查统计";

		// 导出报表表头
		topTr = getWsfTjTop(model, wsjcModel);
		// 导出数据
		ArrayList<String[]> list = getWsfTjExpList(model, wsjcModel, topTr,
				request);

		expToExcel(title, topTr, list, os);
	}
	
	/**
	 * 获得卫生分结果列表
	 * 
	 * @author luojw
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @throws Exception
	 */
	public ArrayList<String[]> getWsfTjExpList(GyglTyForm model,
			GyglWsjcModel wsjcModel, List<HashMap<String, String>> topTr,
			HttpServletRequest request) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {	
		
		ArrayList<String[]> rsList = new ArrayList<String[]>();

		// 统计方式
		String tjfs = model.getTjfs();

		if ("qs".equalsIgnoreCase(tjfs)) {// 寝室
			
			ArrayList<String[]> zqsList = dao.getQszsTjList(model, wsjcModel);
			ArrayList<String[]> pmList = dao.getQspmTjList(model, wsjcModel);
			
			if (zqsList != null && zqsList.size() > 0) {

				for (int i = 0; i < zqsList.size(); i++) {
					
					boolean flag = true;
					
					if (pmList != null && pmList.size() > 0) {
						
						String[] qsInfo = zqsList.get(i);
						
						ArrayList<String> arrList = new ArrayList<String>(Arrays
								.asList(zqsList.get(i)));
		
						arrList.remove(0);
						
						for (int j = 0; j < pmList.size(); j++) {
							
							String[] pmInfo = pmList.get(j);
							
							if (qsInfo[0].equalsIgnoreCase(pmInfo[0])) {
								
								//总寝室数
								String zqss = qsInfo[qsInfo.length-1];
								
								for (int k = 0; k < pmInfo.length; k++) {
									if (k != 0) {
										arrList.add(pmInfo[k]);
									}
									if (k % 2 == 1) {
										// 等级所占比例
										String bl = Math.round(Float
												.parseFloat(pmInfo[k])
												/ Float.parseFloat(zqss) * 100)
												+ "%";
										arrList.add(bl);
									}
								}
								
								flag = false;
								
								break;
							}
						}
						
						int num = (pmList.get(0).length - 1)
								+ (pmList.get(0).length - 1) / 2;

						if (flag) {

							for (int k = 0; k <= num; k++) {
								if (k != 0) {
									arrList.add("无");
								}
							}
						}

						request.setAttribute("num", arrList.size() - num);
						
						rsList.add(arrList.toArray(new String[] {}));
					}
				}
			}
		} else {// 学生
			ArrayList<String[]> zrsList = dao.getXszsTjList(model, wsjcModel);
			ArrayList<String[]> pmList = dao.getXspmTjList(model, wsjcModel);
			
			if (zrsList != null && zrsList.size() > 0) {

				for (int i = 0; i < zrsList.size(); i++) {
					
					boolean flag = true;
					
					ArrayList<String> arrList = new ArrayList<String>(Arrays
							.asList(zrsList.get(i)));
					
					arrList.remove(0);
					
					if (pmList != null && pmList.size() > 0) {

						String[] xsInfo = zrsList.get(i);
									
						for (int j = 0; j < pmList.size(); j++) {
							
							String[] pmInfo = pmList.get(j);
							
							if (xsInfo[0].equalsIgnoreCase(pmInfo[0])) {
								
								//总人数
								String zrs = xsInfo[xsInfo.length-1];
								
								for (int k = 0; k < pmInfo.length; k++) {
									if (k != 0) {
										arrList.add(pmInfo[k]);
									}
									if (k % 2 == 1) {
										// 等级所占比例
										String bl = Math.round(Float
												.parseFloat(pmInfo[k])
												/ Float.parseFloat(zrs) * 100)
												+ "%";
										arrList.add(bl);
									}
								}
								
								flag = false;
								
								break;
							}
						}
						
						int num = (pmList.get(0).length - 1)
								+ (pmList.get(0).length - 1) / 2;

						if (flag) {

							for (int k = 0; k <= num; k++) {
								if (k != 0) {
									arrList.add("0");
								}
							}
						}

						request.setAttribute("num", arrList.size() - num);
						
						rsList.add(arrList.toArray(new String[] {}));
					}
				}
			}
		}
		
		return rsList;
	}
	

	/**
	 * 同步卫生分以及等级
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public void tbWsfAndDj(GyglTyForm model, GyglWsjcModel wsjcModel)
			throws Exception {

		// 录入形式
		String lrxs = wsjcModel.getLrxs();
		// 关联等级
		String gldj = wsjcModel.getGldj();
		// 卫生分等级
		List<HashMap<String, String>> djList = wsjcModel.getWsdjList();
		
		if ("分数".equalsIgnoreCase(lrxs) && "是".equalsIgnoreCase(gldj)) {
			ArrayList<String[]> list = dao.getWsfbPk(model, wsjcModel);

			if (list != null && list.size() > 0) {

				String[] sql = new String[list.size()];
				
				for (int i = 0; i < list.size(); i++) {
					
					String pk = list.get(i)[0];
					String fs = list.get(i)[1];

					String wsfdj = "";

					// 获取卫生分所关联的等级
					for (int j = 0; j < djList.size(); j++) {

						String xx = djList.get(j).get("wsfxx");
						String sx = djList.get(j).get("wsfsx");
						String dj = djList.get(j).get("wsfdj");

						if (Float.parseFloat(fs) >= Float.parseFloat(xx)
								&& Float.parseFloat(fs) <= Float.parseFloat(sx)) {
							wsfdj = dj;
							break;
						}
					}

					sql[i] = "update gygl_wsjc_wsfwhb set wsfdj = '" + wsfdj
							+ "' where jcld||jccs||jcqs||jcsj||jczc = '" + pk
							+ "'";
				}
				
				dao.saveArrDate(sql);
			}
		}

	}
	
	/**
	 * 获得学生卫生分录入表头
	 * 
	 */
	public List<HashMap<String, String>> getXslrfTop(GyglWsjcModel wsjcModel, String mk) {
		// 检查周期
//		String jczq = wsjcModel.getJczq();
		// 录入形式
//		String lrxs = wsjcModel.getLrxs();
		// 固定列
		String[] gdName = null;
		if("lr".equalsIgnoreCase(mk)){
			gdName = new String[] { "学号", "姓名", Base.YXPZXY_KEY+"名称", "寝室号", "床位号", "个人加分", "个人减分"};
		}else if("ck".equalsIgnoreCase(mk)) {
			gdName = new String[] {"学号", "姓名", "学年", "学期", Base.YXPZXY_KEY+"名称", "寝室号", "床位号", "基础分", "个人加分", "个人减分", "学期得分"};
		}
		return getTopList(gdName);
	}
	
	/**
	 * 获取住宿学生寝室基本录入分
	 * @param model
	 * @return
	 * @throws IllegalArgumentException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	public ArrayList<String[]> getXslrfInfo(GyglTyForm model) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		DAO dao = DAO.getInstance();
		Pages pages = model.getPages();
		
		
		String[] queryList = new String[] { "xydm", "xqdm", "lddm", "cs", "qsh"};
		String[] queryLikeList = new String[] {"xh", "xm"};
		
		MakeQuery myQuery = new MakeQuery();
		myQuery.makeQuery(queryList, queryLikeList, model);
		
		String queryString = myQuery.queryStr();
		String[] inputs = myQuery.getInputList();
		
		String[] outputs = "周".equalsIgnoreCase(model.getJczq()) ?
							new String[]{"xh", "xm", "xymc", "qsh","cwh", "grjf", "grkf"} :
							new String[]{"xh", "xm", "xymc", "qsh","cwh", "grjf", "grkf"};
	
		StringBuilder sql = new StringBuilder();
		
		sql.append("select a.* from (");
		sql.append("select rownum r, a.xh,a.xm,a.xydm,a.xqdm,a.lddm,a.cs,");
		sql.append("a.qsh,a.cwh,a.xymc,a.qswsf,a.jczc,a.jcsj,");
		sql.append("(case when instr(to_char(a.grjf),'.',1,1)=1 then '0'||to_char(a.grjf) else to_char(a.grjf) end) grjf,");
		sql.append("(case when instr(to_char(a.grkf),'.',1,1)=1 then '0'||to_char(a.grkf) else to_char(a.grkf) end) grkf ");
		sql.append("from (");
		sql.append(getXslrfSql(model, queryString));
		sql.append(") a) a where r>=");
		sql.append(pages.getStart());
		sql.append(" and r<");
		sql.append(pages.getStart() + pages.getPageSize());
		
		StringBuilder countSql = new StringBuilder();
		countSql.append("select count(*) num from (");
		countSql.append(getXslrfSql(model, queryString));
		countSql.append(")");
		
		String count = dao.rsToVator(countSql.toString(), inputs, new String[]{"num"}).get(0)[0];
		pages.setMaxRecord(Integer.parseInt(count));
		
		return dao.rsToVator(sql.toString(), inputs, outputs);
	}
	
	
	/**
	 * 获取学生录入分sql语句
	 * @param model
	 * @return
	 * @throws IllegalArgumentException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	public String getXslrfSql(GyglTyForm model, String queryString) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		StringBuilder sql = new StringBuilder();
		
		// 个人加分
		sql.append("select a.*,nvl((select sum(fs) from xg_view_gygl_xsfslr b ");
		sql.append("where b.xh=a.xh and b.xmxz='加分' and b.xn='");
		sql.append(model.getXn());
		sql.append("' and b.xq='");
		sql.append(model.getXq());
		sql.append("' and b.nd='");
		sql.append(model.getNd());
		sql.append("' ");
		sql.append(getCondition(model));
		sql.append(" group by xh");
		sql.append("),0) grjf,");
		
		// 个人减分
		sql.append("nvl((select sum(fs) from xg_view_gygl_xsfslr b ");
		sql.append("where b.xh=a.xh and b.xmxz='减分' and b.xn='");
		sql.append(model.getXn());
		sql.append("' and b.xq='");
		sql.append(model.getXq());
		sql.append("' and b.nd='");
		sql.append(model.getNd());
		sql.append("' ");
		sql.append(getCondition(model));
		sql.append(" group by xh");
		sql.append("),0) grkf ");
		
		sql.append("from (");
		sql.append("select a.xh,a.xm,a.xydm,a.xqdm,a.lddm,a.cs,a.qsh,a.cwh,a.xymc,nvl(b.wsffs,0) qswsf,b.jczc,b.jcsj ");
		sql.append("from view_xszsxx a left join gygl_wsjc_wsfwhb b on ");
		sql.append("a.qsh=b.jcqs and b.xn='");
		sql.append(model.getXn());
		sql.append("' and b.xq='");
		sql.append(model.getXq());
		sql.append("' and b.nd='");
		sql.append(model.getNd());
		sql.append("' ");
		sql.append(getCondition(model));
		
		sql.append(" order by lddm,qsh,cwh");
		sql.append(") a ");
		
		sql.append(queryString);
		sql.append(dao.getGyfdyQuery(model, "a"));
		
		return sql.toString();
	}
	
	
	/**
	 * 获取个性化条件
	 * @param model
	 * @return
	 * @arthor sjf
	 */
	private String getCondition(GyglTyForm model){
		StringBuilder sql = new StringBuilder();
		String lx = model.getJczq();
		
		if("周".equalsIgnoreCase(lx)){
			sql.append("and b.jczc='");
			sql.append(model.getJczc());
		} else{
			sql.append("and b.jcsj='");
			sql.append(model.getJcsj());
			
			sql.append("' and b.jczc='无");
		}
		
		sql.append("'");
		
		return sql.toString();
	}
	
	/**
	 * 获得学生卫生检查分项目信息
	 * @param xmxz
	 * @return
	 */
	public List<HashMap<String, String>> getWsjcInfo(String xmxz){
		return dao.getWsjcInfo(xmxz);
	}
	
	/**
	 * 检查学生录入分
	 * @param model
	 * @return
	 */
	public boolean saveXslrf(GyglWsjcXslrfModel model, String jczq){
		boolean flag = false;
		DAO dao = DAO.getInstance();
		
		String tableName = "XG_GYGL_XSFSLRB";
		String pk = null;
		String pkValue[] = null;
		
		// 根据检查周期决定删除的主键
		if("周".equalsIgnoreCase(jczq)){
			pk = "xh||xn||xq||nd||jczc";
			pkValue = new String[]{model.getXh() + model.getXn() + model.getXq() + model.getNd() + model.getJczc()};
		}else {
			pk = "xh||xn||xq||nd||jczc||jcsj";
			pkValue = new String[]{model.getXh() + model.getXn() + model.getXq() + model.getNd() + "无" + model.getJcsj()};
		}
		
		String[] arrzd = new String[]{"fs", "xmdm"};
		
		String[] onezd = "周".equalsIgnoreCase(jczq) ?
						 new String[]{"xh", "xn", "xq", "nd", "jczc", "lrr", "lrsj"} :
						 new String[]{"xh", "xn", "xq", "nd", "jcsj", "lrr", "lrsj"};
		
		SaveForm saveForm = new SaveForm();
		saveForm.setArrzd(arrzd);
		saveForm.setOnezd(onezd);
		saveForm.setPk(pk);
		saveForm.setPkValue(pkValue);
		saveForm.setTableName(tableName);
		
		try {
			dao.saveData(saveForm, model);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	/**
	 * 获取住宿学生寝室基本录入分
	 * @param model
	 * @return
	 * @throws IllegalArgumentException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	public ArrayList<String[]> getXsxqlrfInfo(GyglTyForm model) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		DAO dao = DAO.getInstance();
		Pages pages = model.getPages();
		
		String[] queryList = new String[] { "xydm", "xqdm", "lddm", "cs", "qsh","xn","xq"};
		String[] queryLikeList = new String[] {"xh", "xm"};
		
		MakeQuery myQuery = new MakeQuery();
		myQuery.makeQuery(queryList, queryLikeList, model);
		
		String queryStr = myQuery.queryStr();
		String[] inputs = myQuery.getInputList();
		
		String[] outputs = "周".equalsIgnoreCase(model.getJczq()) ?
							new String[]{"xh", "xm", "xn", "xqm", "xq", "xymc", "qsh", "cwh", "jcf","jffs", "kffs", "grdf"} :
							new String[]{"xh", "xm", "xn", "xqm", "xq", "xymc", "qsh", "cwh", "jcf","jffs", "kffs", "grdf"};
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select a.* from (");
		sql.append("select rownum r, a.* from (");
		sql.append(getXsxqlrfSql(model, queryStr));
		sql.append(") a) a where r>=");
		sql.append(pages.getStart());
		sql.append(" and r<");
		sql.append(pages.getStart() + pages.getPageSize());
		
		StringBuilder countSql = new StringBuilder();
		countSql.append("select count(*) num from (");
		countSql.append(getXsxqlrfSql(model, queryStr));
		countSql.append(")");
		
		String count = dao.rsToVator(countSql.toString(), inputs, new String[]{"num"}).get(0)[0];
		pages.setMaxRecord(Integer.parseInt(count));
		
		return dao.rsToVator(sql.toString(), inputs, outputs);
	}
	
	/**
	 * 获取住宿学生每学期寝室分sql
	 * @param model
	 * @return
	 * @throws IllegalArgumentException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	public String getXsxqlrfSql(GyglTyForm model, String queryString) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		StringBuilder sql = new StringBuilder();
		
//		select a.xm,a.xb,a.nj,a.xydm,a.xymc,a.zydm,a.zymc,a.bjdm,a.bjmc,a.xqdm,a.xqmc,a.yqdm,a.yqmc,a.lddm,a.ldmc,a.cs,a.ssbh,a.qsh,a.cwh,b.*
//		from view_xszsxx a left join
//		(select xh,
//					 xn,
//					 xq,
//					 (select sum(fs)
//							from xg_view_gygl_xsfslr b
//						 where a.xh = b.xh
//							 and a.xq = b.xq
//							 and a.xn = b.xn
//							 and b.xmxz = '加分') jffs,
//					 (select sum(fs)
//							from xg_view_gygl_xsfslr b
//						 where a.xh = b.xh
//							 and a.xq = b.xq
//							 and a.xn = b.xn
//							 and b.xmxz = '减分') kffs
//			from (select xh, xn, xq from xg_view_gygl_xsfslr group by xh, xn, xq)a) b on a.xh=b.xh
		
		// 具体是哪种设置
		String condition = "";
		
		if("周".equalsIgnoreCase(model.getJczq())){
			condition = "and b.jczc<>'无' ";
		}else{
			condition = "and b.jczc='无' ";
		}
		
		sql.append("select a.*,(select xqmc from xqdzb where xqdm=a.xq) xqm,(a.jcf+a.jffs-a.kffs) grdf from ");
		sql.append("(select a.xh,a.xm,a.xb,a.nj,a.xydm,a.xymc,a.zydm,a.zymc,a.bjdm,a.bjmc,a.xqdm,a.xqmc,a.yqdm,a.yqmc,a.lddm,a.ldmc,");
		sql.append("a.cs,a.ssbh,a.qsh,a.cwh,b.xn,b.xq,nvl((select jcf from gygl_wsjc_csszb),0) jcf,");
		sql.append("nvl((case when instr(to_char(b.jffs),'.',1,1)=1 then '0'||to_char(b.jffs) else to_char(b.jffs) end),0) jffs,");
		sql.append("nvl((case when instr(to_char(b.kffs),'.',1,1)=1 then '0'||to_char(b.kffs) else to_char(b.kffs) end),0) kffs ");
		sql.append("from view_xszsxx a,");
		sql.append("(select xh,xn,xq,");
		sql.append("(select sum(fs) from xg_view_gygl_xsfslr b where a.xh = b.xh and a.xq = b.xq and a.xn = b.xn and b.xmxz = '加分' ");
		sql.append(condition);
		sql.append(") jffs,");
		
		sql.append("(select sum(fs) from xg_view_gygl_xsfslr b where a.xh = b.xh and a.xq = b.xq and a.xn = b.xn and b.xmxz = '减分' ");
		sql.append(condition);
		sql.append(") kffs ");
		sql.append("from (select xh, xn, xq from xg_view_gygl_xsfslr b where 1=1 " ); 
		sql.append(condition);
		sql.append("group by xh, xn, xq)a");
		
		sql.append(")b where a.xh=b.xh)a "); 
		sql.append(queryString);
		sql.append(dao.getGyfdyQuery(model, "a"));
		
  		return sql.toString();
	}
	
	/**   
	 * 获取某个学生某一学期的所有减分情况
	 * @param xh
	 * @param xn
	 * @param xq
	 * @return
	 */
	public List<HashMap<String, String>> getXsfsInfo(String xh, String xn, String xq, String jczq){
		String tableName = "xg_view_gygl_xsfslr";
		
		String[] output = null;
		StringBuilder query = new StringBuilder();
		
		if("周".equalsIgnoreCase(jczq)){
			output = new String[]{"jczc", "xmmc", "xmxz", "fs", "lrr"};
			query.append(" where jczc<>'无'");
			query.append(" and xh=? and xn=? and xq=?");
			query.append(" order by jczc");
		}else {
			output = new String[]{"jcsj", "xmmc", "xmxz", "fs", "lrr"};
			query.append(" where jczc = '无'");
			query.append(" and xh=? and xn=? and xq=?");
			query.append(" order by jcsj");
		}
		
		
		return CommonQueryDAO.commonQueryforList(tableName, query.toString(), new String[]{xh,xn,xq}, output, "");
		
	}
	
	
	/**
	 * 学生卫生分统计
	 * @param os
	 * @param nj
	 * @throws Exception 
	 */
	public void xswsfTj(GyglTyForm model, OutputStream os) throws Exception {
		String bjmc = model.getBjmc();
		String xn = model.getXn();
		String xqdm = model.getXq();
		
		GyglWsjcModel wsjcModel = getWsjc();
		int zgzc = Integer.parseInt(wsjcModel.getZgzc());
		
		int cols = 8 + zgzc;
		
		// 创建excel对象
		ExcelMB excel = new ExcelMB();
		WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(os);
		WritableSheet ws = wwb.createSheet(bjmc, 0);
		
		// 获取学生统计分数数据
		List<HashMap<String, String>> list = getXsfstjData(model);
		Map<String, String> rsTj = getRstjData(model);
		
		try{
			WritableCellFormat wcfTytle = ExcelMB.getWritableCellFormat(8,
					false, Alignment.CENTRE, VerticalAlignment.CENTRE,
					Border.ALL);// 单元格样式
			
			String xqmc = BasicExtendService.xqMap.get(xqdm);
			if(xqmc != null && xqmc.length()>2){
				xqmc = xqmc.substring(0,xqmc.indexOf("学期"));
			}
			// 标题
			excel.printTitle(ws, bjmc + "(" + xn + "年" + xqmc + "学期宿舍得分)", 23, 400);
			
			// 各列名
			ws.addCell(new Label(0, 1, "室号", wcfTytle));
			ws.addCell(new Label(1, 1, "床号", wcfTytle));
			ws.addCell(new Label(2, 1, "姓名", wcfTytle));
			ws.mergeCells(3, 1, 4, 1);
			ws.addCell(new Label(3, 1, "得分", wcfTytle));
			ws.addCell(new Label(5, 1, "总分", wcfTytle));
			for(int i=1; i<=zgzc; i++){
				ws.addCell(new Label(5+i, 1, i+"", wcfTytle));
			}
			ws.addCell(new Label(6+zgzc, 1, "总扣分", wcfTytle));

			ws.addCell(new Label(7+zgzc, 1, "优率", wcfTytle));
			
			int xqIndx = "01".equalsIgnoreCase(xqdm) ? 3 : 4;
			
			// 数据将要插入的行号
			int row = 2;
			for(int i =0; i<list.size(); i++){
				Map<String, String> currObj = list.get(i);
				
				// 若是第一条数据，直接插入excel当中去
				if(i==0){
					insertExcel(zgzc, ws, wcfTytle, xqIndx, row, currObj);
				}else{
					// 获取上一个对象，如果与当前对象的学号相同，则该条数据只是保存了不同周次的分数，
					// 扩展到同学号的那条数据当中去，否则添加一条新的数据。
					Map<String, String> befObj = list.get(i-1);
					if(currObj.get("xh").equalsIgnoreCase(befObj.get("xh"))){
						if(StringUtils.isNotNull(currObj.get("jczc"))){
							int zc = Integer.parseInt(currObj.get("jczc"));
							ws.addCell(new Label(5+zc, row, currObj.get("fs"), wcfTytle));
						}
					}else{
						row++;
						insertExcel(zgzc, ws, wcfTytle, xqIndx, row, currObj);
					}
				}
			}
			coalitionCols(ws, wcfTytle, 2, 2, cols-1, row+3);
			ExcelMB.mergeCells(ws, row-1, 0, 3);
			
			ws.addCell(new Label(0, row+1, "住宿", wcfTytle));
			ws.addCell(new Label(1, row+1, rsTj.get("ns"), wcfTytle));
			ws.addCell(new Label(2, row+1, rsTj.get("vs"), wcfTytle));
			ws.addCell(new Label(3, row+1, "上", wcfTytle));
			ws.addCell(new Label(4, row+1, "下", wcfTytle));
	
			ws.addCell(new Label(0, row+2, "合计", wcfTytle));
			ws.addCell(new Label(1, row+2, rsTj.get("zs"), wcfTytle));
			ws.addCell(new Label(0, row+3, "走读", wcfTytle));
			ws.addCell(new Label(1, row+3, rsTj.get("wz"), wcfTytle));
		} catch(Exception e){
			e.printStackTrace();
		}
		
		
		//向客户端输出
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	/**
	 * 插入Excel一条记录
	 * @param zgzc
	 * @param ws
	 * @param wcfTytle
	 * @param xqIndx
	 * @param row
	 * @param currObj
	 * @throws WriteException
	 * @throws RowsExceededException
	 */
	private void insertExcel(int zgzc, WritableSheet ws,
			WritableCellFormat wcfTytle, int xqIndx, int row,
			Map<String, String> currObj) throws WriteException,
			RowsExceededException {
		ws.addCell(new Label(0, row, currObj.get("qsh"), wcfTytle));
		ws.addCell(new Label(1, row, currObj.get("cwh"), wcfTytle));
		ws.addCell(new Label(2, row, currObj.get("xm"), wcfTytle));
		ws.addCell(new Label(5, row, currObj.get("jcf"), wcfTytle));
		
		int df = 100;
		if(StringUtils.isNotNull(currObj.get("zkf")) && StringUtils.isNotNull(currObj.get("jcf"))){
			df = Integer.parseInt(currObj.get("jcf")) + Integer.parseInt(currObj.get("zkf")) ;
		}

		ws.addCell(new Label(xqIndx, row, df+"", wcfTytle));
		ws.addCell(new Label(6+zgzc, row, currObj.get("zkf"), wcfTytle));
		
		if(StringUtils.isNotNull(currObj.get("jczc"))){
			int zc = Integer.parseInt(currObj.get("jczc"));
			ws.addCell(new Label(5+zc, row, currObj.get("fs"), wcfTytle));
		}
	}
	
	
	public void coalitionCols(WritableSheet ws, WritableCellFormat wcfTytle, int x, int y, int x1, int y1) throws RowsExceededException, WriteException{
		// 行数
		int rows = y1 - y + 1;
		
		// 列数
		int cols = x1 - x + 1;
		
		for(int i=0; i<rows; i++){
			for(int j=0; j<cols; j++){
				int currX = x + j;
				int currY = y + i;
				
				WritableCell a1 = ws.getWritableCell(currX, currY);
				if(StringUtils.isNull(a1.getContents())){
					ws.addCell(new Label(currX, currY, "", wcfTytle));
				}
			}
		}
	}
	
	private Map<String, String> getRstjData(GyglTyForm model){
//		select (select count(*) from view_xsjbxx where bjdm='306302702') zs,
//		(select '男' || count(*) from view_xszsxx where bjdm='306302702' and xb='男') ns,
//		(select '女' || count(*) from view_xszsxx where bjdm='306302702' and xb='女') vs,
//		(select count(*) num from view_xsjbxx a where bjdm='306302702' and not exists (select 1 from view_xszsxx b where b.xh=a.xh)) wz from dual;
		
		StringBuilder sql = new StringBuilder();
		String bjCondition = "bjdm='" + model.getBjdm() + "'";
		
		sql.append("select (select count(*) from view_xsjbxx where ");
		sql.append(bjCondition);
		sql.append(") zs,");
		sql.append("(select '男' || count(*) from view_xszsxx where xb='男' and ");
		sql.append(bjCondition);
		sql.append(") ns,");
		sql.append("(select '女' || count(*) from view_xszsxx where xb='女' and ");
		sql.append(bjCondition);
		sql.append(") vs,");
		sql.append("(select count(*) num from view_xsjbxx a where ");
		sql.append(bjCondition);
		sql.append(" and not exists (select 1 from view_xszsxx b where b.xh=a.xh)) wz from dual");
		
		return DAO.getInstance().getMapNotOut(sql.toString(), new String[]{});
	}
	
	/**
	 * 获得统计数据
	 * @param model
	 * @return
	 */
	private List<HashMap<String, String>> getXsfstjData(GyglTyForm model) throws Exception{
//		select a.xh,a.xm,a.bjmc,a.qsh,a.cwh,a.zkf,b.jczc,b.fs from 
//		(select a.qsh, a.cwh, a.xh, a.xm, a.bjmc, a.bjdm,
//		(select sum((case xmxz when '减分' then '-'||fs else fs end))
//		from xg_view_gygl_xsfslr where jczc<>'无' and xn='2010-2011' and xh=a.xh) zkf
//			from view_xszsxx a
//		 where a.bjdm = '306302702')a left join
//		(select a.xh,a.jczc,sum(fs)fs from
//		(select xh,jczc,xn,xq,(case xmxz when '减分' then '-'||fs else fs end)fs
//		from xg_view_gygl_xsfslr where jczc<>'无' and xn='2010-2011')a group by xh,jczc)b on a.xh=b.xh order by qsh,cwh;
		String[] colList = new String[]{"xh", "qsh", "cwh", "xm", "zkf", "jczc", "fs", "jcf"};
		StringBuilder sql = new StringBuilder();
		
		sql.append("select a.xh,a.xm,a.bjmc,a.qsh,a.cwh,nvl(a.zkf,'') zkf,nvl(b.jczc,'') jczc,nvl(b.fs,'') fs,");
		sql.append("(select jcf from gygl_wsjc_csszb) jcf from ");
		sql.append("(select a.qsh, a.cwh, a.xh, a.xm, a.bjmc, a.bjdm,");
		sql.append("(select sum((case xmxz when '减分' then '-'||fs else fs end))");
		sql.append("from xg_view_gygl_xsfslr where jczc<>'无' and ");
		sql.append("xn='");
		sql.append(model.getXn());
		sql.append("' and xh=a.xh) zkf ");
		sql.append("from view_xszsxx a ");
		sql.append("where a.bjdm='");
		sql.append(model.getBjdm());
		sql.append("')a left join ");
		sql.append("(select a.xh,a.jczc,sum(fs)fs from ");
		sql.append("(select xh,jczc,xn,xq,(case xmxz when '减分' then '-'||fs else fs end)fs ");
		sql.append("from xg_view_gygl_xsfslr where jczc<>'无' and xn='");
		sql.append(model.getXn());
		sql.append("')a group by xh,jczc)b on a.xh=b.xh order by qsh,cwh");
		
		return CommonQueryDAO.commonQueryforList("", "", new String[]{}, colList, sql.toString());
	}
}
