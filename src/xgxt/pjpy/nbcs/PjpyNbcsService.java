package xgxt.pjpy.nbcs;

import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.util.MessageResources;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import common.GlobalsVariable;

import xgxt.DAO.ExcelMB;
import xgxt.action.Base;
import xgxt.form.RequestForm;
import xgxt.form.SaveForm;
import xgxt.pjpy.PjpyTyForm;
import xgxt.pjpy.PjpyTyService;
import xgxt.pjpy.zjcm.ZjcmPjpyModel;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.ExcelMethods;
import xgxt.utils.MakeQuery;
import xgxt.utils.date.MoneyFormat;

public class PjpyNbcsService extends PjpyTyService {

	MessageResources message = MessageResources
					.getMessageResources("config.ApplicationResources");
			
	PjpyNbcsDAO dao = new PjpyNbcsDAO();
	
	/**
	 * 保存问卷信息
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public Boolean saveWjInfo(PjpyTyForm model, String table,
			HttpServletRequest request) throws Exception {

		String[] onezd = new String[] { "xn", "xq", "nd", "wjmc", "jlsj","sfkq", "bz" };

		String pk = "id";

		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(table);
		saveForm.setOnezd(onezd);
		saveForm.setPk(pk);
		saveForm.setPkValue(new String[] { model.getId() });

		return savePjpy(saveForm, model, request);
	}
	
	/**
	 * 更新问卷信息
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public Boolean updateWjInfo(PjpyTyForm model, String table,
			HttpServletRequest request) throws Exception {

		String[] onezd = new String[] { "wjmc", "sfkq", "bz" };

		String pk = "id";

		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(table);
		saveForm.setOnezd(onezd);
		saveForm.setPk(pk);
		saveForm.setPkValue(new String[] { model.getId() });

		return updatePjpy(saveForm, model);
	}

	/**
	 * 获得最大试题编号
	 * 
	 * @author luojw
	 */
	public String getStbh() {
		return dao.getStbh();
	}
	
	/**
	 * 保存试题信息
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public Boolean saveStInfo(PjpyTyForm model, String table,
			HttpServletRequest request) throws Exception {

		String[] onezd = new String[] { "stbh", "stlx", "stmc", "stss", "bz" };

		String pk = "stbh";

		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(table);
		saveForm.setOnezd(onezd);
		saveForm.setPk(pk);
		saveForm.setPkValue(new String[] { model.getStbh() });

		return savePjpy(saveForm, model, request);
	}
	
	/**
	 * 保存答案信息
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public Boolean saveDaInfo(PjpyTyForm model) throws Exception {

		String[] arrzd = new String[] { "dabh", "damc","bzda" };
		String[] onezd = new String[] { "stbh" };

		String tableName="wjdc_stdab";
		String stlx = model.getStlx();
		
		String[] bzda = null;
		String[] damc = null;
		String[] dabh = null;
		
		if("oneChoose".equalsIgnoreCase(stlx)){//单选题
			dabh = model.getDabhoneChoose();
			damc = model.getDamconeChoose();
			bzda = model.getBzdaoneChoose();
		}else if("allChoose".equalsIgnoreCase(stlx)){//多选题
			dabh = model.getDabhallChoose();
			damc = model.getDamcallChoose();
			bzda = model.getBzdaallChoose();
		}else if("questions".equalsIgnoreCase(stlx)){//问答题
			dabh = model.getDabh();
			damc = model.getDamc();
			bzda = model.getBzda();
		}
		
		// 标准答案赋值
		if (dabh != null && dabh.length > 0
				&& !"questions".equalsIgnoreCase(stlx)) {
			
			String[] arr_bzda = new String[dabh.length];
			
			for (int i = 0; i < dabh.length; i++) {
				
				arr_bzda[i] = "no";
				
				if (bzda != null && bzda.length > 0) {
					
					for (int j = 0; j < bzda.length; j++) {
						
						if (dabh[i].equalsIgnoreCase(bzda[j])) {
							
							arr_bzda[i] = "yes";
							
							break;
							
						}
					}
				}
			}
			model.setBzda(arr_bzda);
		}
		
		model.setDabh(dabh);
		model.setDamc(damc);
		
		String pk = "stbh";

		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(tableName);
		saveForm.setOnezd(onezd);
		saveForm.setArrzd(arrzd);
		saveForm.setPk(pk);
		saveForm.setPkValue(new String[] { model.getStbh() });

		return savePjpy(saveForm, model);
	}
	
	/**
	 * 删除试题信息
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public Boolean delStInfo(PjpyTyForm model) throws Exception {

		boolean result = false;

		String[] stbh = model.getPrimarykey_checkVal();
		String realTable = "wjdc_stxxb";

		if (stbh != null && stbh.length > 0) {
			String pk = "stbh";

			SaveForm saveForm = new SaveForm();
			saveForm.setTableName(realTable);
			saveForm.setPk(pk);
			saveForm.setPkValue(stbh);

			result = delPjpy(saveForm, model);
		}

		return result;
	}
	
	/**
	 * 删除答案信息
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public Boolean delDaInfo(PjpyTyForm model) throws Exception {
		
		boolean result = false;

		String[] stbh = model.getPrimarykey_checkVal();
		String realTable = "wjdc_stdab";

		if (stbh != null && stbh.length > 0) {
			String pk = "stbh";

			SaveForm saveForm = new SaveForm();
			saveForm.setTableName(realTable);
			saveForm.setPk(pk);
			saveForm.setPkValue(stbh);

			result = delPjpy(saveForm, model);
		}

		return result;
	}

	/**
	 * 保存组卷信息
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public Boolean saveZjInfo(PjpyTyForm model,String tableName) throws Exception {

		String[] arrzd = new String[] { "fpbh" };
		String[] onezd = new String[] { "id" };
		
		String pk = "id";

		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(tableName);
		saveForm.setOnezd(onezd);
		saveForm.setArrzd(arrzd);
		saveForm.setPk(pk);
		saveForm.setPkValue(new String[] { model.getId() });

		return savePjpy(saveForm, model);
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
		List<HashMap<String, String>> wjhdList = setWjHdList(model);
		// 试题答案统计
		List<HashMap<String, String>> datjList=getDaTjList(model);
		// 标准答案
		List<HashMap<String, String>> bzdaList = getBzdaList(model);
		// 单选题信息
		List<Object> oneChooseList = new ArrayList<Object>();
		// 多选题信息
		List<Object> allChooseList = new ArrayList<Object>();
		// 问答题信息
		List<Object> questionsList = new ArrayList<Object>();
		
		// 设置相关信息
		setChooseList(model, zjlxList, oneChooseList, allChooseList,
				questionsList, wjhdList, datjList, bzdaList);

		// 单选题数量
		if (oneChooseList != null && oneChooseList.size() > 0) {
			request.setAttribute("oneSs", oneChooseList.size());
		}
		
		// 多选题数量
		if (allChooseList != null && allChooseList.size() > 0) {
			request.setAttribute("allSs", allChooseList.size());
		}
		
		// 问答题数量
		if (questionsList != null && questionsList.size() > 0) {
			request.setAttribute("queSs", questionsList.size());
		}
		
		request.setAttribute("oneChooseList", oneChooseList);
		request.setAttribute("allChooseList", allChooseList);
		request.setAttribute("questionsList", questionsList);

	}

	/**
	 * 设置选择题列表
	 * 
	 * @param zjlxList
	 *            组卷类型（单选，多选，问答等）
	 * @param oneChooseList
	 *            单选题
	 * @param allChooseList
	 *            多选题
	 * @param questionsList
	 *            问答题
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
			List<Object> allChooseList, List<Object> questionsList,
			List<HashMap<String, String>> wjhdList,
			List<HashMap<String, String>> datjList,
			List<HashMap<String, String>> bzdaList) {
		
		// 问卷编号
		String id = model.getId();
		
		for (HashMap<String, String> map : zjlxList) {
			
			//试题类型
			String stlx = map.get("lxdm");
			model.setStlx(stlx);
			
			// 试题名称
			List<HashMap<String, String>> mcList = getWjstmcList(id, stlx);
			// 试题答案
			List<HashMap<String, String>> daList = getWjstdaList(id, stlx);
			// 回答答案
			List<HashMap<String, String>> hddaList = getHddaList(model);
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
				
				List<HashMap<String,Object>> stList = new ArrayList<HashMap<String,Object>>();
				
				for (HashMap<String, String> mcMap : mcList) {
		
					if (rsMap.get("stss").equals(mcMap.get("stss"))) {
						
						stnum++;
						
						HashMap<String, Object> info = new HashMap<String, Object>();

						String stbh = mcMap.get("stbh");
						String stmc = mcMap.get("stmc");

						// 标准答案
						String bzda = "";

						for (HashMap<String, String> bzdaMap : bzdaList) {
							if (bzdaMap.get("stbh").equalsIgnoreCase(stbh)) {
								bzda += bzdaMap.get("dabh") + ",";
							}
						}

						// 回答答案
						String hdda = "";

						for (HashMap<String, String> hddaMap : hddaList) {
							if (hddaMap.get("stbh").equalsIgnoreCase(stbh)) {
								hdda += hddaMap.get("dabh") + ",";
							}
						}

						List<HashMap<String, String>> stdaList = new ArrayList<HashMap<String, String>>();

						for (HashMap<String, String> daMap : daList) {

							if (daMap.get("stbh").equalsIgnoreCase(stbh)) {

								daMap.put("isChecked", "false");

								// 答案统计
								for (HashMap<String, String> tjMap : datjList) {
									if (tjMap.get("stbh")
											.equalsIgnoreCase(stbh)
											&& tjMap.get("dabh")
													.equalsIgnoreCase(
															daMap.get("dabh"))) {

										daMap.put("num", tjMap.get("num"));
										daMap.put("rs", tjMap.get("rs"));
									}
								}

								// 判断该答案是否被选中
								for (HashMap<String, String> hdMap : wjhdList) {
									
									if (hdMap.get("fpbh").equalsIgnoreCase(stbh)
											&& hdMap.get("dabh").equalsIgnoreCase(daMap.get("dabh"))) {
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

						bzda = Base.isNull(bzda) ? bzda : bzda.substring(0,
								bzda.length() - 1);
						hdda = Base.isNull(hdda) ? hdda : hdda.substring(0,
								hdda.length() - 1);
						// 答案是否正确
						String isTrue = (isPp(bzda, hdda)) ? "回答正确"
								: "回答错误";

						info.put("stbh", stbh);// 试题编号
						info.put("stmc", stmc);// 试题名称
						info.put("bzda", bzda);// 标准答案
						info.put("isTrue", isTrue);// 是否正确
						info.put("danum", danum);// 答案数量
						info.put("stda", stdaList);// 答案列表

						stList.add(info);
					}
				}
				
				rsMap.put("stnum", stnum);
				rsMap.put("stList", stList);
				
				if (GlobalsVariable.WJDC_ONECHOOSE.equalsIgnoreCase(stlx)) {// 单选题
					oneChooseList.add(rsMap);
				} else if (GlobalsVariable.WJDC_ALLCHOOSE.equalsIgnoreCase(stlx)) {// 多选题
					allChooseList.add(rsMap);
				}else if (GlobalsVariable.WJDC_QUESTIONS.equalsIgnoreCase(stlx)) {// 问答题
					questionsList.add(rsMap);
				}
			}
		}
	}
	
	/**
	 * 获得指定问卷的试题类型
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getWjstlxList(String id) {
		return dao.getWjstlxList(id);
	}

	/**
	 * 获得指定问卷的试题试题名称
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getWjstmcList(String id, String stlx) {
		return dao.getWjstmcList(id, stlx);
	}
	
	/**
	 * 获得试题答案信息
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getWjstdaList(String id, String stlx){
		return dao.getWjstdaList(id, stlx);
	}

	/**
	 * 保存回答者答案
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public Boolean saveHdzDa(PjpyTyForm model, RequestForm rForm)
			throws Exception {

		String[] onezd = new String[] { "xhzgh", "lx", "wjbh" };
		String[] arrzd = new String[] { "fpbh", "dabh", "danr" };

		// 表名
		String tableName = rForm.getRealTable();
		// 主键字段
		String pk = "xhzgh||lx||wjbh||fpbh";
		// 单选题编号
		String[] oneChooseBh = model.getOneChooseBh();
		// 单选题答案
		String[] oneChooseDa = model.getOneChooseDa();
		// 多选题编号
		String[] allChooseBh = model.getAllChooseBh();
		// 多选题答案
		String[] allChooseDa = model.getAllChooseDa();
		// 问答题编号
		String[] questionsBh = model.getQuestionsBh();
		// 问答题答案
		String[] questionsDa = model.getQuestionsDa();
		// 回答者学号（职工号）
		String xhzgh = rForm.getUserName();
		// 回答者类型
		String lx = rForm.getUserType();
		// 问卷编号
		String wjbh = model.getId();

		// 确定批量保存数据的数量
		int size = 0;

		if (allChooseDa != null && allChooseDa.length > 0) {
			for (String dxda : allChooseDa) {
				if (!Base.isNull(dxda)) {
					String[] arrDa = dxda.split("!!@@!!");
					size += (arrDa != null && arrDa.length > 0) ? arrDa.length
							: 0;
				}
			}
		}

		if (oneChooseDa != null && oneChooseDa.length > 0) {
			size += oneChooseDa.length;
		}

		if (questionsBh != null && questionsBh.length > 0) {
			size += questionsBh.length;
		}

		// 主键
		String[] pkValue = new String[size];
		// 试题编号
		String[] fpbh = new String[size];
		// 答案编号
		String[] dabh = new String[size];
		// 答案内容
		String[] danr = new String[size];

		int n = 0;

		// 单选题
		if (oneChooseDa != null && oneChooseDa.length > 0) {
			for (int i = 0; i < oneChooseDa.length; i++) {
				fpbh[n] = oneChooseBh[i];
				dabh[n] = oneChooseDa[i];
				danr[n] = "";
				pkValue[n] = xhzgh + lx + wjbh + oneChooseBh[i];
				n++;
			}
		}

		// 多选题
		if (allChooseDa != null && allChooseDa.length > 0) {

			for (int i = 0; i < allChooseDa.length; i++) {
				// 多选答案
				String dxda = allChooseDa[i];

				if (!Base.isNull(dxda)) {

					String[] arrDa = dxda.split("!!@@!!");

					for (String da : arrDa) {
						fpbh[n] = allChooseBh[i];
						dabh[n] = da;
						danr[n] = "";
						pkValue[n] = xhzgh + lx + wjbh + allChooseBh[i];
						n++;
					}

				}
			}
		}

		// 问答题
		if (questionsBh != null && questionsBh.length > 0) {
			for (int i = 0; i < questionsBh.length; i++) {
				fpbh[n] = questionsBh[i];
				dabh[n] = "answer";
				danr[n] = questionsDa[i];
				pkValue[n] = xhzgh + lx + wjbh + questionsBh[i];
				n++;
			}
		}

		// 设置需要操作的值
		model.setXhzgh(xhzgh);
		model.setLx(lx);
		model.setWjbh(wjbh);
		model.setFpbh(fpbh);
		model.setDabh(dabh);
		model.setDanr(danr);

		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(tableName);
		saveForm.setOnezd(onezd);
		saveForm.setArrzd(arrzd);
		saveForm.setPk(pk);
		saveForm.setPkValue(pkValue);

		return savePjpy(saveForm, model);
	}
	
	/**
	 * 获得问卷回答情况
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> setWjHdList(PjpyTyForm model) {
		return dao.setWjHdInfo(model);
	}
	
	/**
	 * 获得试题答案统计
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getDaTjList(PjpyTyForm model) {
		return dao.getDaTjList(model);
	}
	
	/**
	 * 获得试题标准答案
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getBzdaList(PjpyTyForm model) {
		return dao.getBzdaList(model);
	}
	
	/**
	 * 获得所回答答案列表
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getHddaList(PjpyTyForm model) {
		return dao.getHddaList(model);
	}
	
	/**
	 * 获得试题所属列表
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getStssList(PjpyTyForm model) {
		return dao.getStssList(model);
	}
	
	/**
	 * 删除组卷信息
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public Boolean delZjxx(PjpyTyForm model, String pk) throws Exception {

		String[] pkValue = model.getPrimarykey_checkVal();
		boolean flag = false;

		if (pkValue != null && pkValue.length > 0) {

			String tableName = "wjdc_zjb";

			SaveForm saveForm = new SaveForm();
			saveForm.setTableName(tableName);
			saveForm.setPk(pk);
			saveForm.setPkValue(pkValue);

			flag = delPjpy(saveForm, model);

		}

		return flag;
	}

	/**
	 * 删除回答信息
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public Boolean delHdxx(PjpyTyForm model, String pk) throws Exception {

		String[] pkValue = model.getPrimarykey_checkVal();
		boolean flag = false;

		if (pkValue != null && pkValue.length > 0) {

			String tableName = "wjdc_hdb";

			SaveForm saveForm = new SaveForm();
			saveForm.setTableName(tableName);
			saveForm.setPk(pk);
			saveForm.setPkValue(pkValue);

			flag = delPjpy(saveForm, model);

		}

		return flag;
	}
	
	/**
	 * 转换数字格式为中文数字
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public String changNumLx(String num) {	

		// 金额汉字大写
		String[] dx = { "零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖", "拾",
				"佰", "仟", "万", "亿" };

		// 金额汉字消协
		String[] xx = { "零", "一", "二", "三", "四", "五", "六", "七", "八", "九", "十",
				"百", "千", "万", "亿" };
		
		String chineseNum = MoneyFormat.format(String.valueOf(num));
		
		chineseNum = chineseNum.replace("元整", "");
		
		if(!Base.isNull(chineseNum)){
			
			char[] charNum =  new char[chineseNum.length()];
			String [] arrNum =  new String[chineseNum.length()];
			
			for (int i = 0; i < chineseNum.length(); i++) {
				charNum[i] = chineseNum.charAt(i);
			}
			
			for (int i = 0; i < charNum.length; i++) {

				for (int j = 0; j < dx.length; j++) {

					if (String.valueOf(charNum[i]).equalsIgnoreCase(dx[j])) {
						arrNum[i] = xx[j];
					}
				}
			}
			
			if (arrNum != null && arrNum.length > 0) {
				chineseNum = "";
				for (String number : arrNum) {
					chineseNum += number;
				}
			}
		}
		
		return chineseNum;
	}
	
	/**
	 * 确定标准答案与回答答案是否匹配
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public Boolean isPp(String bzda, String hdda) {

		boolean flag = false;

		if (!Base.isNull(bzda) && !Base.isNull(hdda)
				&& bzda.length() == hdda.length()) {

			// 标准答案
			String[] arr_bzda = bzda.split(",");
			// 回答答案
			String[] arr_hdda = hdda.split(",");
			// 是否匹配
			String[] pp = new String[arr_bzda.length];

			for (int i = 0; i < arr_hdda.length; i++) {
				pp[i] = "no";
				for (int j = 0; j < arr_bzda.length; j++) {
					if (arr_hdda[i].equalsIgnoreCase(arr_bzda[j])) {
						pp[i] = "yes";
						break;
					}
				}
			}

			flag = true;
			for (int i = 0; i < pp.length; i++) {
				if ("no".equalsIgnoreCase(pp[i])) {
					flag = false;
					;
				}
			}
		}
		return flag;
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
	 * 导出回收情况
	 * 
	 * @author luo
	 * @throws Exception
	 */
	public void hsqkToExcel(PjpyTyForm model, OutputStream os) throws Exception {

		// =====================获得初始值====================

		// 统计表
		String tableName = "view_wjdc_hsqktj";
		// 统计列表
		String[] colList = new String[] { "nj", "xydm", "xymc", "hsnum",
				"ffnum", "njnum" };

		String[] queryList = new String[] { "id" };

		String[] queryLikeList = new String[] {};

		MakeQuery myQuery = new MakeQuery();

		myQuery.makeQuery(queryList, queryLikeList, model);

		String query = myQuery.getQueryString();

		List<HashMap<String, String>> tjList = CommonQueryDAO
				.commonQueryforList(tableName, query, myQuery.getInputList(),
						colList, "");
		// 年级列表
		List<HashMap<String, String>> njList = getNewZdList(tjList, "nj");
		int njSize = (njList != null && njList.size() > 0) ? njList.size() : 0;
		// 总列数
		int size = 4 + njSize;
		// 问卷编号
		String id = model.getId();
		// 问卷名称
		String wjmc = getOneValue("wjdc_wjxxb", "wjmc", "id", id);
		// 标题
		String title = wjmc + "回收情况表";
		// =====================end====================

		// =====================初始化Excel格式====================
		WritableWorkbook wwb = Workbook.createWorkbook(os);

		WritableSheet ws = wwb.createSheet(title, 0);

		WritableCellFormat wcf = new WritableCellFormat(); // 构造单元格格式
		wcf = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, Border.ALL);
		// =====================end====================

		// =====================填充标题====================

		ExcelMB ex = new ExcelMB();
		ws.mergeCells(0, 0, size, 1);
		ex.printToOneCell_multy(ws, title, 0, 0, 12, true, Alignment.CENTRE,
				VerticalAlignment.CENTRE, true, 650, Border.NONE);
		// =====================end====================

		// =====================填充表头====================
		Base.YXPZXY_KEY = message.getMessage("lable.xb");
		ws.addCell(new Label(0, 2, "序号", wcf));
		ws.addCell(new Label(1, 2, Base.YXPZXY_KEY, wcf));

		if (njSize > 0) {// 循环输出年级
			for (int i = 0; i < njList.size(); i++) {

				HashMap<String, String> niMap = njList.get(i);

				ws.addCell(new Label(2 + i, 2, niMap.get("njdm") + "级", wcf));
			}
		}

		ws.addCell(new Label(2 + njSize, 2, "回收合计", wcf));
		ws.addCell(new Label(3 + njSize, 2, "发放问卷", wcf));
		ws.addCell(new Label(4 + njSize, 2, "回收率", wcf));

		// =====================end====================

		// =====================填充统计内容====================

		ws.addCell(new Label(0, 2, "序号", wcf));
		ws.addCell(new Label(1, 2,Base.YXPZXY_KEY, wcf));

		if (njSize > 0) {// 循环输出年级

			// 序号
			int n = 0;
			// 回收合计
			float hshj = 0;
			// 发放合计
			float ffhj = 0;
			// 比较学院
			String bjxy = "";
			// 实时年级数目
			int nowNum = 0;
			
			// 各个年级总计
			int[] njzj = new int[njSize];
			// 回收总计
			float hszj = 0;
			// 发放总计
			float ffzj = 0;
			
			for (int i = 0; i < tjList.size(); i++) {

				HashMap<String, String> map = tjList.get(i);

				// 年级数目
				String njnum = map.get("njnum");
				// 年级
				String nj = map.get("nj");
				// 学院代码
				String xydm = map.get("xydm");
				// 学院名称
				String xymc = map.get("xymc");
				// 回收数目
				String hsnum = map.get("hsnum");
				// 发放数目
				String ffnum = map.get("ffnum");

				// 序号
				ws.addCell(new Label(0, 3 + n, String.valueOf(n + 1), wcf));
				// 学院名称
				ws.addCell(new Label(1, 3 + n, xymc, wcf));

				for (int j = 0; j < njList.size(); j++) {

					ws.addCell(new Label(2 + j, 3 + n, "0", wcf));// 回收数目
					
					if (nj.equalsIgnoreCase(njList.get(j).get("njdm"))) {

						hshj += Float.parseFloat(hsnum);// 累加回收
						ffhj += Float.parseFloat(ffnum);// 累加发放
						
						njzj[j] += Integer.parseInt(hsnum);
						
						ws.addCell(new Label(2 + j, 3 + n, hsnum, wcf));// 回收数目

					} 
				}						
				
				if(Base.isNull(bjxy)){
					bjxy = xydm;
					nowNum += Integer.parseInt(njnum);
				}
				
				if (i == nowNum - 1) {
					
					// 回收合
					ws.addCell(new Label(2 + njSize, 3 + n, String
							.valueOf(hshj), wcf));
					// 发放合计
					ws.addCell(new Label(3 + njSize, 3 + n, String
							.valueOf(ffhj), wcf));

					// 回收率
					String hsl = "0";
					if (ffhj != 0) {
						hsl = String.valueOf((hshj / ffhj) * 100);
						hsl = (hsl.length() > 5) ? hsl.substring(0, 5) : hsl;
					}
					ws.addCell(new Label(4 + njSize, 3 + n, hsl + "%", wcf));
					
					hszj += hshj;
					ffzj += ffhj;
					
					hshj = 0;
					ffhj = 0;
					bjxy = "";
					
					n++;
				}
			}
			
			// 最后一行合计
			ws.addCell(new Label(0, 3 + n, "合计", wcf));
			ws.mergeCells(0, 3 + n, 1, 3 + n);
			ws.addCell(new Label(2 + njSize, 3 + n, String.valueOf(hszj), wcf));
			ws.addCell(new Label(3 + njSize, 3 + n, String.valueOf(ffzj), wcf));
			
			// 回收率
			String hsl = "0";
			if (ffzj != 0) {
				hsl = String.valueOf((hszj / ffzj) * 100);
				hsl = (hsl.length() > 5) ? hsl.substring(0, 5) : hsl;
			}
			
			ws.addCell(new Label(4 + njSize, 3 + n, hsl + "%", wcf));
			
			for (int i = 0; i < njList.size(); i++) {
				ws.addCell(new Label(2 + i, 3 + n, String.valueOf(njzj[i]), wcf));
			}			
		}
		// =====================end====================	
		
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}	
	
	/**
	 * 导出结果统计
	 * 
	 * @author luo
	 * @throws Exception
	 */
	public void jgtjToExcel(PjpyTyForm model, OutputStream os,HttpServletRequest request) throws Exception {

		// =====================获得初始值====================
		// 问卷编号
		String id = model.getId();
		// 问卷名称
		String wjmc = getOneValue("wjdc_wjxxb", "wjmc", "id", id);
		// 标题
		String title = wjmc + "选项频数统计表";
		// 获得相关统计信息
		setWjZjInfo(model, request);
		// 单选题
		List<HashMap<String, Object>> oneChooseList = (List<HashMap<String, Object>>) request
				.getAttribute("oneChooseList");
		// 多选题
		List<HashMap<String, Object>> allChooseList = (List<HashMap<String, Object>>) request
				.getAttribute("allChooseList");
		// =====================end====================

		// =====================初始化Excel格式====================
		WritableWorkbook wwb = Workbook.createWorkbook(os);

		WritableSheet ws = wwb.createSheet(title, 0);

		WritableCellFormat wcf = new WritableCellFormat(); // 构造单元格格式
		wcf = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false,
				Alignment.CENTRE, VerticalAlignment.CENTRE, true, Border.ALL);
		
		WritableCellFormat nrWcf = new WritableCellFormat(); // 构造单元格格式
		nrWcf = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false,
				Alignment.LEFT, VerticalAlignment.CENTRE, true, Border.ALL);
		// =====================end====================

		// =====================填充标题====================

		ExcelMB ex = new ExcelMB();
		ws.mergeCells(0, 0, 7, 1);
		ex.printToOneCell_multy(ws, title, 0, 0, 12, true, Alignment.CENTRE,
				VerticalAlignment.CENTRE, true, 650, Border.NONE);
		// =====================end====================
		
		// =====================填充表头====================

		ws.addCell(new Label(0, 2, "序号", wcf));
		ws.addCell(new Label(1, 2, "题号", wcf));
		ws.addCell(new Label(2, 2, "试题类型", wcf));;
		ws.addCell(new Label(3, 2, "试题名称", wcf));
		ws.addCell(new Label(4, 2, "选项", wcf));
		ws.addCell(new Label(5, 2, "选择人数", wcf));
		ws.addCell(new Label(6, 2, "总人数", wcf));
		ws.addCell(new Label(7, 2, "所占比例", wcf));
		
		// =====================end====================
		
		// =====================填充统计内容====================
		// 记录数
		int n = 0;
		// 序号
		int xuh = 1;
		// 综合
		int zhb = 0; 
		
		//单选题
		if (oneChooseList != null && oneChooseList.size() > 0) {
			
			for (int i = 0; i < oneChooseList.size(); i++) {
				
				HashMap<String, Object> ssMap = oneChooseList.get(i);
				
				// 试题类型
				List<HashMap<String, Object>> stList = (List<HashMap<String, Object>>) ssMap
						.get("stList");
				
				if (stList != null && stList.size() > 0) {

					for (int j = 0; j < stList.size(); j++) {

						HashMap<String, Object> stMap = stList.get(j);

						// 试题编号
						String stbh = (String) stMap.get("stbh");
						// 试题名称
						String stmc = (String) stMap.get("stmc");
						// 答案数量
						String danum = (String) stMap.get("danum");
						// 试题答案
						List<HashMap<String, Object>> daList = (List<HashMap<String, Object>>) stMap
								.get("stda");					

						// 开始合并
						int hbnum =Base.isNull(danum) ? 0 : Integer
								.parseInt(danum);
						
						ws.mergeCells(0, 3 + zhb, 0, 2 + +zhb + hbnum);
						ws.mergeCells(1, 3 + zhb, 1, 2 + +zhb + hbnum);
						ws.mergeCells(2, 3 + zhb, 2, 2 + +zhb + hbnum);
						ws.mergeCells(3, 3 + zhb, 3, 2 + +zhb + hbnum);
						
						zhb += hbnum;
						
						if (daList != null && daList.size() > 0) {

							for (int k = 0; k < daList.size(); k++) {
								
								HashMap<String, Object> daMap = daList.get(k);
								
								// 答案编号
								String dabh = (String) daMap.get("dabh");
								// 选择人数
								String num = (String) daMap.get("num");
								// 总人数
								String rs = (String) daMap.get("rs");
								// 所占比例
								String bl = "";
								// 计算所占比例
								if(!Base.isNull(rs)&& !"0".equalsIgnoreCase(rs)){
									bl = String.valueOf((Float.parseFloat(num)/Float.parseFloat(rs)*100));
								}
														
								ws.addCell(new Label(0, 3 + n, String.valueOf(xuh),wcf));// 序号
								ws.addCell(new Label(1, 3 + n, stbh, nrWcf));// 试题编号
								ws.addCell(new Label(2, 3 + n, "单选题", nrWcf));// 试题类型
								ws.addCell(new Label(3, 3 + n, stmc, nrWcf));// 试题名称
								ws.addCell(new Label(4, 3 + n, dabh, nrWcf));// 答案编号
								ws.addCell(new Label(5, 3 + n, num, nrWcf));// 选择人数
								ws.addCell(new Label(6, 3 + n, rs, nrWcf));// 总人数
								ws.addCell(new Label(7, 3 + n, bl + "%", nrWcf));// 比例
								
								n++;
							}
							
							xuh ++;
						}	
					}
				}
			}
		}
		
		// 多选题
		if (allChooseList != null && allChooseList.size() > 0) {
			
			for (int i = 0; i < allChooseList.size(); i++) {
				
				HashMap<String, Object> ssMap = allChooseList.get(i);
				
				// 试题类型
				List<HashMap<String, Object>> stList = (List<HashMap<String, Object>>) ssMap
						.get("stList");
				
				if (stList != null && stList.size() > 0) {

					for (int j = 0; j < stList.size(); j++) {

						HashMap<String, Object> stMap = stList.get(j);

						// 试题编号
						String stbh = (String) stMap.get("stbh");
						// 试题名称
						String stmc = (String) stMap.get("stmc");
						// 答案数量
						String danum = (String) stMap.get("danum");
						// 试题答案
						List<HashMap<String, Object>> daList = (List<HashMap<String, Object>>) stMap
								.get("stda");					

						// 开始合并
						int hbnum =Base.isNull(danum) ? 0 : Integer
								.parseInt(danum);
						
						ws.mergeCells(0, 3 + zhb, 0, 2 + +zhb + hbnum);
						ws.mergeCells(1, 3 + zhb, 1, 2 + +zhb + hbnum);
						ws.mergeCells(2, 3 + zhb, 2, 2 + +zhb + hbnum);
						ws.mergeCells(3, 3 + zhb, 3, 2 + +zhb + hbnum);
						
						zhb += hbnum;
						
						if (daList != null && daList.size() > 0) {

							for (int k = 0; k < daList.size(); k++) {
								
								HashMap<String, Object> daMap = daList.get(k);
								
								// 答案编号
								String dabh = (String) daMap.get("dabh");
								// 选择人数
								String num = (String) daMap.get("num");
								// 总人数
								String rs = (String) daMap.get("rs");
								// 所占比例
								String bl = "";
								// 计算所占比例
								if(!Base.isNull(rs)&& !"0".equalsIgnoreCase(rs)){
									bl = String.valueOf((Float.parseFloat(num)/Float.parseFloat(rs)*100));
								}
														
								ws.addCell(new Label(0, 3 + n, String.valueOf(xuh),wcf));// 序号
								ws.addCell(new Label(1, 3 + n, stbh, nrWcf));// 试题编号
								ws.addCell(new Label(2, 3 + n, "多选题", nrWcf));// 试题类型
								ws.addCell(new Label(3, 3 + n, stmc, nrWcf));// 试题名称
								ws.addCell(new Label(4, 3 + n, dabh, nrWcf));// 答案编号
								ws.addCell(new Label(5, 3 + n, num, nrWcf));// 选择人数
								ws.addCell(new Label(6, 3 + n, rs, nrWcf));// 总人数
								ws.addCell(new Label(7, 3 + n, bl + "%", nrWcf));// 比例
								
								n++;
							}
							
							xuh ++;
						}	
					}
				}
			}
		}
		// =====================end====================
		
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	/**
	 * 根据条件组成新的列表
	 * 
	 * @author luo
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getNewZdList(
			List<HashMap<String, String>> list, String zd) {
		
		List<HashMap<String, String>> newList = new ArrayList<HashMap<String, String>>();

		if (list != null && list.size() > 0) {
			
			for (int i = 0; i < list.size(); i++) {
				
				HashMap<String, String> map = list.get(i);
				HashMap<String, String> newMap = new HashMap<String, String>();
				
				String newZd = map.get(zd);
				
				if (!Base.isNull(newZd)) {
					
					newMap.put(zd + "dm", newZd);
					newMap.put(zd + "mc", newZd);
					
					boolean flag = true;
					
					if (newList != null && newList.size() > 0) {
						for (int j = 0; j < newList.size(); j++) {
							if (newZd.equalsIgnoreCase(newList.get(j).get(
									zd + "dm"))) {
								flag = false;
							}
						}
					}
					
					if (flag) {
						newList.add(newMap);
					}
				}
			}
		}
		
		return newList;
	}
	
	/**
	 * 获得学生回答问卷列表
	 * 
	 * @author luojw
	 */
	public ArrayList<String[]> getWjhdList(PjpyTyForm model)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		ArrayList<String[]> list = dao.getWjhdList(model);
		return getResultList(list, model);
	}
}
