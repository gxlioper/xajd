/**
 * @部门:学工产品事业部
 * @日期：2013-12-31 上午11:02:56 
 */
package com.zfsoft.xgxt.base.export.business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xgxt.DAO.DAO;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.Arrays2;
import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.export.model.ImportModel;
import com.zfsoft.xgxt.xpjpy.wdpj.sqsh.SqshService;
import com.zfsoft.xgxt.xpjpy.xmsz.jdsz.JdszService;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 业务扩展
 * @类功能描述: 常用业务扩展方法
 * @作者： 张昌路[工号:982]
 * @时间： 2013-12-31 上午11:02:56
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public abstract class BusinessExtend implements IBaseBusiness {
	// 成功数据
	private List<List<ImportModel>> success;
	// 验证通过原始数据列表（用于BusinessExtend验证不通过时,生成错误文件）
	private List<String[]> succeedExcelDataList;
	// 错误数据
	private List<String[]> error;
	// 数据源
	private List<String[]> dataSource;
	// 返回数据
	private HashMap<String, Object> excelObject;
	// 带删除数据
	private List<Integer> deleteIndex = new ArrayList<Integer>();
	// 导入模块代码
	private String drmkdm;
	// 扩展代码
	private String kzdm;
	// 验证通过
	public final String _YZTG = "true";

	public BusinessExtend(String drmkdm, String kzdm) {
		this.drmkdm = drmkdm;
		this.kzdm = kzdm;
	}
	/**
	 * 
	 * @描述: 是否成功
	 * @作者：张昌路[工号：982]
	 * @日期：2014-1-23 下午05:28:45
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param message
	 * @return
	 * boolean 返回类型 
	 */
	protected boolean isSuccess(String message){
		if(StringUtils.isNotNull(message)&&_YZTG.equals(message)){
			return true;
		}
		return false;
	}
	/**
	 * 
	 * @描述: 格式化数据(受子类重写影响)
	 * @作者：张昌路[工号：982]
	 * @日期：2013-12-31 上午11:13:05
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param excelObject
	 *            excel数据源
	 * @return HashMap<String,Object> 返回类型
	 */
	public HashMap<String, Object> formartData(List<List<ImportModel>> success, List<String[]> succeedExcelDataList,
			List<String[]> error) {
		return formartDataUtil(success, succeedExcelDataList, error);
	}
	/**
	 * 格式化数据(不受子类重写影响)
	 */
	private HashMap<String, Object> formartDataUtil(List<List<ImportModel>> success, List<String[]> succeedExcelDataList,
			List<String[]> error) {
		// 去掉存在业务数据错误的数据
		List<List<ImportModel>> successNew = new ArrayList<List<ImportModel>>();
		for (int i = 0; i < success.size(); i++) {
			if(!deleteIndex.contains(i)){
				successNew.add(success.get(i));
			}
		}
		List<String[]> succeedExcelDataListNew = new ArrayList<String[]>();
		for (int i = 0; i < succeedExcelDataList.size(); i++) {
			if(!deleteIndex.contains(i)){
				succeedExcelDataListNew.add(succeedExcelDataList.get(i));
			}
		}
		this.success = successNew;
		this.succeedExcelDataList = succeedExcelDataListNew;
		this.error = error;
		excelObject.put("succeedList", successNew);
		excelObject.put("succeedExcelDataList", succeedExcelDataListNew);
		excelObject.put("errorList", error);
		this.deleteIndex = new ArrayList<Integer>();
		return excelObject;
	}

	/**
	 * 
	 * @描述: 获取对应字段验证信息
	 * @作者：张昌路[工号：982]
	 * @日期：2013-12-31 上午11:30:44
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param key
	 * @param zdm
	 * @param xh
	 * @return String 返回类型
	 */
	public HashMap<String, String> getParamForKey(String ywkzkey, String drmkdm) {
		try {
			StringBuffer sb = new StringBuffer();
			sb
					.append("select * from ZFXG_DR_DRBMPZB where dryzbh=? and drmkdm=?");
			return DAO.getInstance().getMapNotOut(sb.toString(),
					new String[] { ywkzkey, drmkdm });
		} catch (Exception e) {
			throw new RuntimeException("扩展业务验证key必须唯一！");
		}
	}

	/**
	 * 
	 * @描述: 获取配置提示信息
	 * @作者：张昌路[工号：982]
	 * @日期：2014-1-2 下午04:08:17
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ywkzkey
	 * @param drmkdm
	 * @return String 返回类型
	 */
	public String getParamMessage(String ywkzkey, String drmkdm) {
		return getParamForKey(ywkzkey, drmkdm).get("drmkyzsm");
	}

	/**
	 * 
	 * @描述: 获取实际值
	 * @作者：张昌路[工号：982]
	 * @日期：2013-12-31 下午03:48:17
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param excelData
	 * @param object
	 * @return String 返回类型
	 */
	public String getValue(Map<String, String> excelData, String object) {
		return excelData.get(object.toUpperCase());
	}

	/**
	 * 
	 * @描述: 编译返回信息（编译#{str}为实际值）
	 * @作者：张昌路[工号：982]
	 * @日期：2014-1-2 下午02:12:50
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param excelData
	 *            行数据
	 * @param object
	 *            原信息
	 * @return String 返回类型
	 */
	public String compileMessage(Map<String, String> excelData, String object) {
		StringBuffer sb = new StringBuffer();
		String[] objects = object.split("#");
		String v;
		int start;
		int end;
		for (String p : objects) {
			start = p.indexOf("{");
			end = p.lastIndexOf("}");
			if (start >= 0 && end >= 0) {
				v = p.substring(start + 1, end);
				sb.append(getValue(excelData, v));
				sb.append(p.substring(end + 1, p.length()));
			} else {
				sb.append(p);
			}
		}
		return sb.toString();
	}

	/**
	 * 
	 * @描述: 获取设置参数excel行数据中对应的值
	 * @作者：张昌路[工号：982]
	 * @日期：2013-12-31 上午11:49:30
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param data
	 *            map(参数名称,对应excle当前行中值)
	 * @return Map<String,String> 返回类型
	 */
	public Map<String, String> getParamExcleValue(List<ImportModel> data) {
		Map<String, String> map = new HashMap<String, String>();
		for (ImportModel im : data) {
			map.put(im.getZdm(), im.getValue().toString());
		}
		return map;
	}

	/**
	 * 
	 * @描述: 添加错误信息
	 * @作者：张昌路[工号：982]
	 * @日期：2013-12-31 下午04:39:15
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param i
	 *            错误数据下标
	 * @param message
	 *            错误信息 void 返回类型
	 */
	public void addError(int i, String message) {
		List<String[]> er = getError();
		String[] mes;
		if (null != er && er.size() > 0) {
			mes = er.get(i);
			mes[mes.length - 1] = mes[mes.length - 1] + "\n" + message;
			er.set(i, mes);
		} else {
			mes = dataSource.get(i);
			mes = Arrays2.addObject(mes, message);
			er.add(mes);
		}
		// this.success.remove(i);
		// 记录存在业务问题的数据 index，不能直接删除，循环尚未结束
		deleteIndex.add(i);
		this.error = er;
	}
	public void addErrorData(int i, String message, List<String[]> succeedExcelDataList) {
		if(deleteIndex.contains(i)){
			return;
		}
		List<String[]> er = getError();
		String[] mes = succeedExcelDataList.get(i);
		mes = Arrays2.addObject(mes, message);
		er.add(mes);
		// 记录存在业务问题的数据 index，不能直接删除，循环尚未结束
		deleteIndex.add(i);
		this.error = er;
	}

	/**
	 * @return the success
	 */
	public List<List<ImportModel>> getSuccess() {
		return success;
	}

	/**
	 * @return the error
	 */
	public List<String[]> getError() {
		return error;
	}

	/**
	 * @return the succeedExcelDataList
	 */
	public List<String[]> getSucceedExcelDataList() {
		return succeedExcelDataList;
	}
	/**
	 * @return the dataSource
	 */
	public List<String[]> getDataSource() {
		return dataSource;
	}

	/**
	 * 
	 * @描述: 初始化验证数据
	 * @作者：张昌路[工号：982]
	 * @日期：2014-1-2 上午11:59:06
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param excelObject
	 */
	private void init(HashMap<String, Object> excelObject) {
		if (null == excelObject) {
			throw new RuntimeException("excel数据为空，请检查!");
		}
		this.success = (List<List<ImportModel>>) excelObject.get("succeedList");
		this.succeedExcelDataList = (List<String[]>) excelObject.get("succeedExcelDataList");
		this.error = (List<String[]>) excelObject.get("errorList");
		this.dataSource = (List<String[]>) excelObject.get("dataSource");
		this.excelObject = excelObject;
	}

	// 扩展方法
	public List<List<ImportModel>> businessExcute(
			HashMap<String, Object> excelObject) {
		init(excelObject);
		List<List<ImportModel>> su = getSuccess();
		List<String[]> succeedExcelDataList = getSucceedExcelDataList();
		// 所有导入数据一起遍历验证
		checkAll(su, succeedExcelDataList);
		List<String[]> er = getError();
		su = getSuccess();
		succeedExcelDataList = getSucceedExcelDataList();
		formartDataUtil(su, succeedExcelDataList, er);
		// 下一步验证前获取最新数据
		su = getSuccess();
		succeedExcelDataList = getSucceedExcelDataList();
		
		String message = _YZTG;
		// 处理业务格式化
		// 验证导入金额，如果导入金额大于发放金额则加入错误提示
		// 获取金额配置导入参数
		String param = getParamMessage(this.getKzdm(), this.getDrmkdm());
		int i = 0;
		for (List<ImportModel> data : su) {
			message = check(param, data);
			// 验证没有通过添加错误信息
			if (!_YZTG.equals(message)) {
				addErrorData(i, message, succeedExcelDataList);
			}
			i++;
		}
		er = getError();
		su = getSuccess();
		succeedExcelDataList = getSucceedExcelDataList();
		formartData(su, succeedExcelDataList, er);
		excelObject = this.excelObject;
		return getSuccess();
	}
	/** 
	 * 学生数据过滤（评奖）
	 */
	public List<List<ImportModel>> xhCheck(HashMap<String, Object> excelObject, User user) {
		init(excelObject);
		List<List<ImportModel>> su = getSuccess();
		List<String[]> succeedExcelDataList = getSucceedExcelDataList();
		int i = 0;
		for (List<ImportModel> data : su) {
			String message = _YZTG;
			Map<String, String> map = getParamExcleValue(data);
			String xh = map.get("XH");
			if(!StringUtil.isNull(xh)){
				// 权限过滤
				String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
				StringBuffer sb = new StringBuffer();
				sb.append(" select count(1) num from ( ");
				sb.append(" select a.xh,a.xn,c.bmdm xydm,b.bjdm from XG_PJPY_NEW_CPMDB a left join (select zydm,bjdm from bks_bjdm) b on a.bjdm=b.bjdm left join (select zydm,bmdm from bks_zydm) c on b.zydm=c.zydm ");
				sb.append(" ) t where t.xh=? and exists (select 1 from xg_pjpy_new_csszb where rownum =1 and xn=t.xn) ");
				sb.append(searchTjByUser);
				String num = DAO.getInstance().getOneRs(sb.toString(), new String[] { xh }, "num");
				if ("0".equals(num)) {
					message = "该学生属于其他学院，不能导入！";
				}
			}
			// 验证没有通过添加错误信息
			if (!_YZTG.equals(message)) {
				addErrorData(i, message, succeedExcelDataList);
			}
			i++;
		}
		List<String[]> er = getError();
		su = getSuccess();
		succeedExcelDataList = getSucceedExcelDataList();
		formartDataUtil(su, succeedExcelDataList, er);
		excelObject = this.excelObject;
		return getSuccess();
	}

	public List<ImportModel> businessInsertData(ImportModel model,
			List<ImportModel> importColumnList) {
				return importColumnList;
		//model.getDrbm();表名称
		// 默认不更改插入相关信息
	}

	/**
	 * 
	 * @描述: 扩展验证方法
	 * @作者：张昌路[工号：982]
	 * @日期：2014-1-2 上午11:49:25
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param message
	 *            提示信息
	 * @param data
	 *            验证行数据
	 * @return （true为通过，否则为返回的提示信息） String 返回类型
	 */
	protected abstract String check(String message, List<ImportModel> data);
	/**
	 * 扩展验证方法（所有导入数据一起遍历验证）
	 * 默认不做任何实现,子类根据需要重写
	 */
	protected void checkAll(List<List<ImportModel>> succeedList, List<String[]> succeedExcelDataList){
	}

	/**
	 * @return the drmkdm
	 */
	public String getDrmkdm() {
		return drmkdm;
	}

	/**
	 * @param drmkdm要设置的
	 *            drmkdm
	 */
	public void setDrmkdm(String drmkdm) {
		this.drmkdm = drmkdm;
	}

	/**
	 * @return the kzdm
	 */
	public String getKzdm() {
		return kzdm;
	}

	/**
	 * @param kzdm要设置的
	 *            kzdm
	 */
	public void setKzdm(String kzdm) {
		this.kzdm = kzdm;
	}
}
