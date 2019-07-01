package xgxt.pjpy.zjcm.cssz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
import xgxt.pjpy.guizdx.PjpyGuizdxService;
import xgxt.pjpy.guizhdx.GuizhdxDAO;
import xgxt.pjpy.tyb.zhszcp.service.PjpyZhcpjxjService;
import xgxt.utils.Arrays2;
import xgxt.utils.ExcelMethods;
import xgxt.utils.String.StringUtils;

import common.Globals;
import common.GlobalsVariable;

public class PjpyZjcmCsszService {

	PjpyZjcmCsszDAO dao = new PjpyZjcmCsszDAO();
	
	/**
	 * 传入英,中文数组返回列表
	 * @param en
	 * @param cn
	 * @return
	 */
	public List<HashMap<String, String>> getList(String[] en, String[] cn) {
		if (en != null && cn != null && en.length == cn.length) {
			List<HashMap<String, String>> rs = new ArrayList<HashMap<String, String>>();
			for (int i = 0; i < en.length; i++) {
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("dm", en[i]);
				map.put("mc", cn[i]);
				rs.add(map);
			}
			return rs;
		} else {
			return null;
		}
	}
	
	/**
	 * 参评范围列表
	 * hasNullOption 是否有空选项
	 * @return
	 */
	public List<HashMap<String, String>> getCpfwList(boolean hasNullOption) {
		String[] en = {"xy", "zy", "bj"};
		String[] cn = {"学院", "专业", "班级"};
		if(Globals.XXDM_NTZYDX.equalsIgnoreCase(Base.xxdm)){
			en = new String[]{"xy"};
			cn = new String[]{"学院"};
		}
		List<HashMap<String, String>> rs = getNullOptionList(hasNullOption);
		rs.addAll(getList(en, cn));
		return rs;
	}
	
	/**
	 * 奖学金,荣誉称号列表
	 * @return
	 */
	public List<HashMap<String, String>> getJxjRychlbList(boolean hasNullOption) {
		String[] en = { GlobalsVariable.PJPY_JXJ, GlobalsVariable.PJPY_RYCH };
		String[] cn = { "奖学金", "荣誉称号" };
		List<HashMap<String, String>> rs = getNullOptionList(hasNullOption);
		rs.addAll(getList(en, cn));
		return rs;
	}
	
	/**
	 * 空选项列表
	 * @param hasNullOption
	 * @return
	 */
	public List<HashMap<String, String>> getNullOptionList(boolean hasNullOption) {
		List<HashMap<String, String>> rs = new ArrayList<HashMap<String, String>>();
		if (hasNullOption) {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("dm", "");
			map.put("mc", "");
			rs.add(map);
		}
		return rs;
	}
	
	/**
	 * 奖学金,荣誉称号列表
	 * 
	 * @param key
	 * @param jxjlb
	 * @return
	 */
	public List<HashMap<String, String>> getDmList(String key, String jxjlb) {		
		if (GlobalsVariable.PJPY_JXJ.equalsIgnoreCase(key)) {
			return getJxjList(true, jxjlb);
		}else if(GlobalsVariable.PJPY_ZHCPJXJ.equalsIgnoreCase(key)){
			//综合测评奖学金
			DAO dao = DAO.getInstance();
			return dao.getWhList("zhcpjxjdmb", "jxjdm", "jxjmc", "", "", "");
		}else {
			return getRychList(true);
		}
	}
	
	/**
	 * 奖学金类别列表
	 * @return
	 */
	public List<HashMap<String, String>> getJxjlbList(boolean hasNullOption) {
		List<HashMap<String, String>> rs = getNullOptionList(hasNullOption);
		rs.addAll(dao.getJxjlbList());
		return rs;
	}
	
	/**
	 * 通过类别查询奖学金代码列表
	 * @param hasNullOption
	 * @param jxjlb
	 * @return
	 */
	public List<HashMap<String, String>> getJxjList(boolean hasNullOption, String jxjlb) {
		List<HashMap<String, String>> rs = getNullOptionList(hasNullOption);
		rs.addAll(dao.getJxjList(jxjlb));
		return rs;
	}
	
	/**
	 * 查询荣誉称号列表
	 * @param hasNullOption
	 * @return
	 */
	public List<HashMap<String, String>> getRychList(boolean hasNullOption) {
		List<HashMap<String, String>> rs = getNullOptionList(hasNullOption);
		rs.addAll(dao.getRychList());
		return rs;
	}
	
	/**
	 * 查询奖学金人数表头信息
	 * @return
	 */
	public List<HashMap<String, String>> queryJxjrsTitle(String lb) {
		String[] en = {"pk", "r", "xn", "nd", "xq", "bmmc",
				"nj", "jxjlbmc", "mc", "cprs", "jxjbl", "jsrs","jxjrs"};
		String[] cn = {"pk", "行号", "学年", "年度", "学期", "部门名称",
				"年级", "奖学金类别", "奖学金", "参评人数", "比例(%)", "计算人数","调整人数"};
		if (GlobalsVariable.PJPY_RYCH.equalsIgnoreCase(lb)) {
			en = new String[]{"pk", "r", "xn", "nd", "xq", "bmmc",
					"nj", "mc", "cprs", "jxjbl", "jsrs","jxjrs"};
			cn = new String[]{"pk", "行号", "学年", "年度", "学期", "部门名称",
					"年级", "荣誉称号", "参评人数", "比例(%)", "计算人数","调整人数"};
		}else if (GlobalsVariable.PJPY_ZHCPJXJ.equalsIgnoreCase(lb)) {
			en = new String[]{"pk", "r", "xn", "nd", "xq", "bmmc",
					"nj", "mc", "cprs", "jxjbl", "jsrs","jxjrs"};
			cn = new String[]{"pk", "行号", "学年", "年度", "学期", "部门名称",
					"年级", "奖学金", "参评人数", "比例(%)", "计算人数","调整人数"};
		}
		DAO dao = DAO.getInstance();
		return dao.arrayToList(en, cn);
	}
	
	/**
	 * 查询奖学金人数查询结果
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> queryJxjrsResult(PjpyZjcmCsszModel model, String lb)
			throws Exception {
		String[] colList = new String[] { "pk", "r", "xn", "nd", "xqmc", "bmmc",
				"nj", "jxjlbmc", "mc", "cprs", "jxjbl", "jsrs","jxjrs" };
		if (GlobalsVariable.PJPY_RYCH.equalsIgnoreCase(lb)) {
			colList = new String[] { "pk", "r", "xn", "nd", "xqmc", "bmmc",
					"nj", "mc", "cprs", "jxjbl", "jsrs","jxjrs" };
		}
		if(GlobalsVariable.PJPY_ZHCPJXJ.equalsIgnoreCase(lb)){
			colList = new String[] { "pk", "r", "xn", "nd", "xqmc", "bmmc",
					"nj", "mc", "cprs", "jxjbl", "jsrs","jxjrs" };
		}
		return dao.queryJxjrsResult(model,colList);
	}
	
	/**
	 * 初始化基础数据
	 * @param lb
	 * @return
	 * @throws Exception
	 */
	public boolean baseDataInit(String lb) throws Exception{
		boolean flag = false;
		if(Globals.XXDM_NTZYDX.equalsIgnoreCase(Base.xxdm)){
			flag = dao.baseZhcpjxjDataInit(lb);
		}else{
			flag = dao.baseDataInit(lb);
		}
		return flag; 
	}
	
	/**
	 * 奖学金人数比例批量设置
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean updateJxjrs(PjpyZjcmCsszModel model) throws Exception{
		return dao.updateJxjrs(model);
	}
	
	/**
	 * 查询单个奖学金人数信息
	 * @param pkValue
	 * @return
	 */
	public HashMap<String, String> queryDgJxjrsxx(String pkValue) {
		return dao.queryDgJxjrsxx(pkValue);
	}
	
	/**
	 * 修改奖学金单个人数调整信息
	 * @param pkValue
	 * @param jxjrs
	 * @return
	 * @throws Exception
	 */
	public boolean updateJxjDgtzrs(String pkValue, String jxjrs) throws Exception{
		return dao.updateJxjDgtzrs(pkValue, jxjrs);
	}
	
	/**
	 * 导出奖学金比例分配表数据
	 * @param wwb
	 * @param model
	 */
	public void exportJxjblFpb(WritableWorkbook wwb, PjpyZjcmCsszModel model)
			throws Exception {
		List<String[]> rs = dao.queryJxjRychBlRs(model);
		WritableSheet ws = wwb.createSheet(model.getXymc() + "奖学金获得者比例", 0);
		
		//获取表头样式
		WritableCellFormat titleFormat = ExcelMB.getWritableCellFormat(15, true,
				Alignment.CENTRE, VerticalAlignment.CENTRE, Border.ALL);
		
		WritableCellFormat ejtitleFormat = ExcelMB.getWritableCellFormat(12, true,
				Alignment.CENTRE, VerticalAlignment.CENTRE, Border.ALL);
		

		//输出第一级表头
		ExcelMB.writeTitleToCell(ws, getJxjblFpbTitle(model), 0, 0, 9, 2, titleFormat);
		
		//输出第二级表头
		ExcelMB.writeEjTitleToCell(ws, getJxjblFpbEjTitle(model), 0, 3,
				ejtitleFormat);
		
		//写入数据到单元格
		writeResultDataToCell(rs, ws, ExcelMB.getWritableCellFormat(10, false,
				Alignment.CENTRE, VerticalAlignment.CENTRE, Border.ALL));
		
		//输出学院名称
		ws.addCell(new Label(0, 4, model.getXymc(),ejtitleFormat));
		
		//单独设置第1，2，3列单元格列表宽度
		ws.setColumnView(0, 15);
		ws.setColumnView(1, 20);
		ws.setColumnView(2, 10);
		
		//合并学院，优干，优干学长单元格
		int height = rs.isEmpty() ? 4 : rs.size();
		ws.mergeCells(0, 4, 0, 4 + height);
		ws.mergeCells(8, 4, 8, 4 + height);
		ws.mergeCells(9, 4, 9, 4 + height);
		
		ws.addCell(new Label(1,4 + height, "合计", ejtitleFormat));
		
		//优干人数输出
		String yhrs = dao.queryYgblRs(model);
		ws.addCell(new Label(8, 4, yhrs, ejtitleFormat));
		
		//输出合计总人数
		ExcelMB.writeDataToCellByIterator(ws, countFpbzrs(rs), 2, 4+height, ejtitleFormat);
		
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}

	//奖学金比例分配表一级表头
	private String getJxjblFpbTitle(PjpyZjcmCsszModel model) {
		StringBuffer title = new StringBuffer(Base.xxmc);
		title.append(model.getXn());
		title.append("学年第");
		title.append(model.getXq());
		title.append("学期全校各班奖学金获得者比例");
		return title.toString();
	}
	
	/**
	 * 循环写入数据到单元格中
	 * @param rs
	 * @param ws
	 * @param format
	 */
	private void writeResultDataToCell(List<String[]> rs, WritableSheet ws,
			WritableCellFormat format) {
		if (!rs.isEmpty()) {
			try {
				ExcelMB.writeDataToCellByIterator(ws, rs, 1, 4, format);
			} catch (Exception e) {
				// TODO 自动生成 catch 块
				e.printStackTrace();
			}
		}
	}
	
	//奖学金比例分配表二级表头
	private String[] getJxjblFpbEjTitle(PjpyZjcmCsszModel model) {
		String[] titleArray = new String[] { "学院", "班级", "人数", "一等奖", "二等奖",
				"三等奖", "社会工作优秀奖", "三好学生", "优秀学生干部", "优秀学生干部(优秀学长)25%" };
		String[] rs = dao.getJxjRychBlTitle(model);
		if (rs != null && rs.length > 0) {
			titleArray[3] += StringUtils.isNotNull(rs[0]) ? rs[0] : "";
			titleArray[4] += StringUtils.isNotNull(rs[1]) ? rs[1] : "";
			titleArray[5] += StringUtils.isNotNull(rs[2]) ? rs[2] : "";
			titleArray[6] += StringUtils.isNotNull(rs[3]) ? rs[3] : "";
			titleArray[7] += StringUtils.isNotNull(rs[4]) ? rs[4] : "";
			titleArray[8] += StringUtils.isNotNull(rs[5]) ? rs[5] : "";
		}
		return titleArray;
	}
	
	/**
	 * 合计分配表总人数
	 * @param rs
	 * @return
	 */
	public List<String[]> countFpbzrs(List<String[]> rs) {
		int[] data = {0, 0, 0, 0, 0, 0};
		if (!rs.isEmpty()) {
			for (int i=0;i<rs.size();i++) {
				String[] oneData = rs.get(i);
				if (oneData != null && oneData.length == 7) {
					for (int k=0;k<data.length;k++) {
						data[k] += StringUtils.isNull(oneData[k+1]) ? 0 : Integer
								.parseInt(oneData[k+1]);
					}
				}
			}
		}
		List<String[]> result = new ArrayList<String[]>();
		result.add(Arrays2.intArrToStrArr(data));
		return result;
	}
}
