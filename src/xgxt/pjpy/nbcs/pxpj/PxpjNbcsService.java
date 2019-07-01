package xgxt.pjpy.nbcs.pxpj;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import common.GlobalsVariable;

import xgxt.action.Base;
import xgxt.form.RequestForm;
import xgxt.form.SaveForm;
import xgxt.pjpy.PjpyTyForm;
import xgxt.pjpy.nbcs.PjpyNbcsService;
import xgxt.utils.CommonQueryDAO;

public class PxpjNbcsService extends PjpyNbcsService {

	PxpjNbcsDAO dao = new PxpjNbcsDAO();

	/**
	 * 保存答卷人比例
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public Boolean saveDjrbl(PjpyTyForm model, String table,
			HttpServletRequest request) throws Exception {

		String[] onezd = new String[] { "xn", "bl", "bz" };

		String pk = "xn";

		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(table);
		saveForm.setOnezd(onezd);
		saveForm.setPk(pk);
		saveForm.setPkValue(new String[] { model.getXn() });

		return savePjpy(saveForm, model, request);
	}

	/**
	 * 保存答卷人
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public Boolean saveDjr(PjpyTyForm model, String tableName) throws Exception {

		String[] arrzd = new String[] { "djr" };
		String[] onezd = new String[] { "xn" };

		// 学年
		String xn = model.getXn();
		// 答卷人
		String[] djr = model.getDjr();

		String pk = "xn||djr";
		String[] pkValue = new String[djr.length];

		// 构建主键
		for (int i = 0; i < djr.length; i++) {
			pkValue[i] = xn + djr[i];
		}

		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(tableName);
		saveForm.setOnezd(onezd);
		saveForm.setArrzd(arrzd);
		saveForm.setPk(pk);
		saveForm.setPkValue(pkValue);

		return savePjpy(saveForm, model);
	}

	/**
	 * 保存问卷分配
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public Boolean saveWjfp(PjpyTyForm model) throws Exception {
		return dao.saveWjfp(model);
	}

	/**
	 * 获得问卷组卷信息
	 * 
	 * @author luojw
	 */
	public void setWjZjInfo(PjpyTyForm model, HttpServletRequest request) {

		// 问卷编号
		String id = model.getId();

		List<HashMap<String, String>> zjlxList = getWjstlxList(id);
		request.setAttribute("zjlxList", zjlxList);

		// 问卷回答情况
		List<HashMap<String, String>> wjhdList = setWjHdInfo(model);
		// 试题答案统计
		List<HashMap<String, String>> datjList = getDaTjList(model);
		// 单选题信息
		List<Object> oneChooseList = new ArrayList<Object>();

		// 设置相关信息
		setChooseList(model, zjlxList, oneChooseList, wjhdList, datjList);

		// 单选题数量
		if (oneChooseList != null && oneChooseList.size() > 0) {
			request.setAttribute("oneSs", oneChooseList.size());
		}

		request.setAttribute("oneChooseList", oneChooseList);

	}

	/**
	 * 设置选择题列表
	 * 
	 * @param zjlxList
	 *            组卷类型（单选，多选，问答等）
	 * @param oneChooseList
	 *            单选题
	 * @param wjhdList
	 *            回答
	 * @param datjList
	 *            答案统计
	 * @param bzdaList
	 *            标准答案
	 * @author luojw
	 */
	private void setChooseList(PjpyTyForm model,
			List<HashMap<String, String>> zjlxList, List<Object> oneChooseList,
			List<HashMap<String, String>> wjhdList,
			List<HashMap<String, String>> datjList) {

		// 问卷编号
		String id = model.getId();

		for (HashMap<String, String> map : zjlxList) {

			// 试题类型
			String stlx = map.get("lxdm");
			model.setStlx(stlx);

			// 试题名称
			List<HashMap<String, String>> mcList = getWjstmcList(id, stlx);
			// 试题答案
			List<HashMap<String, String>> daList = getWjstdaList(id, stlx);
			// 试题所属
			List<HashMap<String, String>> stssList = getStssList(model);
			// 序号
			int n = 1;

			for (HashMap<String, String> ssMap : stssList) {

				HashMap<String, Object> rsMap = new HashMap<String, Object>();

				rsMap.put("num", changNumLx(String.valueOf(n)));
				rsMap.put("stss", ssMap.get("stss"));
				rsMap.put("ssmc", ssMap.get("ssmc"));

				int stnum = 0;
				n++;

				List<HashMap<String, Object>> stList = new ArrayList<HashMap<String, Object>>();

				for (HashMap<String, String> mcMap : mcList) {

					if (rsMap.get("stss").equals(mcMap.get("stss"))) {

						stnum++;

						HashMap<String, Object> info = new HashMap<String, Object>();

						String stbh = mcMap.get("stbh");
						String stmc = mcMap.get("stmc");

						List<HashMap<String, String>> stdaList = new ArrayList<HashMap<String, String>>();

						for (HashMap<String, String> daMap : daList) {

							if (daMap.get("stbh").equalsIgnoreCase(stbh)) {

								daMap.put("isChecked", "false");

								// 判断该答案是否被选中
								for (HashMap<String, String> hdMap : wjhdList) {

									if (hdMap.get("fpbh")
											.equalsIgnoreCase(stbh)
											&& hdMap.get("dabh")
													.equalsIgnoreCase(
															daMap.get("dabh"))) {
										daMap.put("isChecked", "true");
										daMap.put("danr", hdMap.get("danr"));
									}
								}

								stdaList.add(daMap);
							}
						}

						// 答案数量
						String danum = (stdaList != null && stdaList.size() > 0) ? String
								.valueOf(stdaList.size())
								: "0";

						info.put("stbh", stbh);// 试题编号
						info.put("stmc", stmc);// 试题名称
						info.put("danum", danum);// 答案数量
						info.put("stda", stdaList);// 答案列表

						stList.add(info);
					}
				}

				rsMap.put("stnum", stnum);
				rsMap.put("stList", stList);

				oneChooseList.add(rsMap);

			}
		}
	}
	
	/**
	 * 保存平行评价
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public Boolean savePxpj(PjpyTyForm model, RequestForm rForm)
			throws Exception {

		String[] onezd = new String[] { "xhzgh", "lx", "wjbh", "bpjr" };
		String[] arrzd = new String[] { "fpbh", "dabh" };

		// 表名
		String tableName = rForm.getRealTable();
		//主键字段
		String pk = "xhzgh||lx||wjbh||bpjr||fpbh||dabh";
		// 单选题编号
		String[] oneChooseBh = model.getOneChooseBh();
		// 单选题答案
		String[] oneChooseDa = model.getOneChooseDa();
		
		// 回答者学号（职工号）
		String xhzgh = rForm.getUserName();
		// 回答者类型
		String lx = rForm.getUserType();
		// 问卷编号
		String wjbh = model.getId();
		// 被评价人
		String bpjr = model.getBpjr();
		
		// 确定批量保存数据的数量
		int size = (oneChooseDa != null && oneChooseDa.length > 0) ? oneChooseDa.length
				: 0;	
		
		// 主键
		String[] pkValue = new String[size];
		// 试题编号
		String[] fpbh = new String[size];
		// 答案编号
		String[] dabh = new String[size];
		
		int n = 0;

		//单选题
		if(oneChooseDa != null && oneChooseDa.length>0){
			for(int i=0;i<oneChooseDa.length;i++){
				fpbh[n] = oneChooseBh[i];
				dabh[n] = oneChooseDa[i];
				pkValue[n] = xhzgh + lx + wjbh + bpjr + oneChooseBh[i]
						+ oneChooseDa[i];
				n++;
			}
		}	
		
		// 设置需要操作的值
		model.setXhzgh(xhzgh);
		model.setLx(lx);
		model.setWjbh(wjbh);
		model.setFpbh(fpbh);
		model.setDabh(dabh);
		
		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(tableName);
		saveForm.setOnezd(onezd);
		saveForm.setArrzd(arrzd);
		saveForm.setPk(pk);
		saveForm.setPkValue(pkValue);

		return savePjpy(saveForm, model);
	}
	
	/**
	 * 获得所回答答案列表
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> setWjHdInfo(PjpyTyForm model) {
		return dao.setWjHdInfo(model);
	}
	
	/**
	 * 判断是否答卷人
	 * 
	 * @author luojw
	 */
	public Boolean isDjr(PjpyTyForm model) {
		return dao.isDjr(model);
	}
	
	/**
	 * 获得所回答答案列表
	 * 
	 * @author luojw
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public List<HashMap<String, String>> getTjjgList(PjpyTyForm model)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		
		//班级信息
		List<HashMap<String, String>> tjbjList =dao.getTjbjList(model);
		//学生信息
		List<HashMap<String, String>> tjxsList =dao.getTjxsList(model);
		//最终值
		List<HashMap<String, String>> list =new ArrayList<HashMap<String, String>>();
		
		if (tjbjList != null && tjbjList.size() > 0) {

			for (int i = 0; i < tjbjList.size(); i++) {
				
				HashMap<String, String> bjMap = tjbjList.get(i);
				
				// 问卷编号
				String id = bjMap.get("id");
				// 班级代码
				String bjdm = bjMap.get("bjdm");
				// 班级人数
				String bjrs = bjMap.get("bjrs");
				// A级数目
				int a_level_num = 0;
				// 拥有A级
				boolean a_level = false;
				// 拥有B级
				boolean b_level = false;
				// 拥有C级
				boolean c_level = false;
				
				if (tjxsList != null && tjxsList.size() > 0) {

					for (int j = 0; j < tjxsList.size(); j++) {
						
						HashMap<String, String> xsMap = tjxsList.get(j);
						
						// 问卷编号
						String wjbh = xsMap.get("wjbh");
						// 班级代码
						String xsbj = xsMap.get("bjdm");
						// 评价
						String pj = xsMap.get("pj");
						
						if (id.equalsIgnoreCase(wjbh)
								&& bjdm.equalsIgnoreCase(xsbj)) {

							if ("A".equalsIgnoreCase(pj)) {
								a_level_num++;
								a_level = true;
							} else if ("B".equalsIgnoreCase(pj)) {
								b_level = true;
							} else if ("C".equalsIgnoreCase(pj)) {
								c_level = true;
							}
						}
					}
				}
				
				// A级比例
				float a_level_bl = (Base.isNull(bjrs) || "0"
						.equalsIgnoreCase(bjrs)) ? 0 : a_level_num
						/ Float.parseFloat(bjrs) * 100;

				String msg = "通过";
				
				// 比例超过40%，全班分数无效，该分数目前不可设置
				if (a_level_bl > 40) {
					msg = "A级比例超过40%";
				}
				// ABC三个级别都要有，否则该分数无效
				if (!a_level || !b_level || !c_level) {
					msg = "没有同时拥有ABC三个级别的分数";
				}
				
				// 是否有效
				String sfyx = ("通过".equalsIgnoreCase(msg)) ? "是" : "否";

				bjMap.put("sfyx", sfyx);
				bjMap.put("msg", msg);

				list.add(bjMap);
			}

		}
		
		// 结果值
		List<HashMap<String, String>> resultList = new ArrayList<HashMap<String, String>>();
		// 是否有效
		String yx = model.getSfkq();

		if (list != null && list.size() > 0) {

			for (int i = 0; i < list.size(); i++) {
				HashMap<String, String> map = list.get(i);

				// 问卷编号
				String sfyx = map.get("sfyx");
				if (!Base.isNull(yx)) {
					if (sfyx.equalsIgnoreCase(yx)) {
						resultList.add(map);
					}
				} else {
					resultList.add(map);
				}
			}
		}

		return getResultList(resultList, model);
	}
	
	/**
	 * 获得学生评价列表
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getXsxxList(PjpyTyForm model) {
		return dao.getXsxxList(model);
	}
	
	/**
	 * 获得学生评价列表
	 * 
	 * @author luojw
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * 
	 * @ 注：需要在model里放xh,xn必须放，要速度快点可以把xydm，zydm，bjdm，nj也放进去
	 */
	public List<HashMap<String, String>> getPxpjInfo(PjpyTyForm model)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		
		// 学号
		String xh = model.getXh();

		// 结果值
		List<HashMap<String, String>> resultList = new ArrayList<HashMap<String, String>>();
		// 班级信息
		List<HashMap<String, String>> bjxxList = getTjjgList(model);
		// 统计列表
		List<HashMap<String, String>> xsxxList = dao.getTjxsList(model);

		if (xsxxList != null && xsxxList.size() > 0) {
			
			for (int i = 0; i < xsxxList.size(); i++) {
				
				HashMap<String, String> xsMap = xsxxList.get(i);
				// 班级代码
				String bjdm = xsMap.get("bjdm");
				// 被评价人
				String bpjr = xsMap.get("bpjr");
				
				boolean flag = false;
				
				if (bpjr.equalsIgnoreCase(xh)) {

					if (bjxxList != null && bjxxList.size() > 0) {

						for (int j = 0; j < bjxxList.size(); j++) {
							
							HashMap<String, String> bjMap = bjxxList.get(j);
							
							if ((bjdm.equalsIgnoreCase(bjMap.get("bjdm")))
									&& ("是".equalsIgnoreCase(bjMap.get("sfyx")))) {
								flag = true;
							}
						}
					}
				}
				
				if(flag){
					xsMap.put("pj", "无");
					xsMap.put("pjf", "0");
				}
				
				resultList.add(xsMap);
			}
		}
		
		return resultList;
	}
	
	/**
	 * 保存评价分
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public Boolean savePjf(PjpyTyForm model, String tableName) throws Exception {

		String[] arrzd = new String[] { "pjf", "fpxh" };
		String[] onezd = new String[] { "wjbh" };

		// 问卷编号
		String wjbh = model.getWjbh();
		// 评价分
		String[] fpxh = model.getFpxh();

		String pk = "wjbh||fpxh";
		String[] pkValue = new String[fpxh.length];

		// 构建主键
		for (int i = 0; i < fpxh.length; i++) {
			pkValue[i] = wjbh + fpxh[i];
		}

		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(tableName);
		saveForm.setOnezd(onezd);
		saveForm.setArrzd(arrzd);
		saveForm.setPk(pk);
		saveForm.setPkValue(pkValue);

		return savePjpy(saveForm, model);
	}
	
	
	/**
	 * 获得学生处分列表
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getXscfList(PjpyTyForm model) {
		return dao.getXscfList(model);
	}
}
