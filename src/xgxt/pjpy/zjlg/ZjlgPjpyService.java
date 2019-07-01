package xgxt.pjpy.zjlg;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.utils.ExcelMethods;

public class ZjlgPjpyService {

	ZjlgPjpyDAO dao = new ZjlgPjpyDAO();

	/**
	 * @author luo
	 * @describe 获得表头
	 */
	public List<HashMap<String, String>> getTopTr(String tableName,
			String[] colList) {
		DAO dao = DAO.getInstance();
		String[] colListCN = dao.getColumnNameCN(colList, tableName);
		List<HashMap<String, String>> topTr = dao.arrayToList(colList,
				colListCN);
		return topTr;
	}

	/**
	 * @author luo
	 * @describe 取得数据库系统时间
	 */
	public String getSysTime() throws SQLException {		
		return dao.getSysTime();
	}
	/**
	 * @author luo
	 * @describe 是否班主任
	 */
	public boolean isBzrBj(String zgh) throws SQLException {
		return dao.isBzrBj(zgh);
	}

	/**
	 * @author luo
	 * @describe 德育分是否设置比例
	 */
	public boolean isDySz(String xn) {
		return dao.isDySz(xn);
	}

	/**
	 * @author luo
	 * @describe 获得条件字段列表
	 */
	public List<HashMap<String, String>> getZdList(String szlx) throws SQLException {
		return dao.getZdList(szlx);
	}

	/**
	 * @author luo
	 * @describe 获得奖学金类别列表
	 */
	public List<HashMap<String, String>> getJxjlbList() throws SQLException {
		return dao.getJxjlbList();
	}

	/**
	 * @author luo
	 * @describe 获得德育分组成各项最高分条件
	 */
	public HashMap<String, String> getDyZgf(String xn) throws SQLException {
		return dao.getDyZgf(xn);
	}

	/**
	 * @author luo
	 * @describe 获得条件
	 */
	public List<HashMap<String, String>> getTjList(ZjlgPjpyModel model,
			String szlx, String[] colList) throws SQLException {
		return dao.getTjList(model, szlx, colList);
	}

	/**
	 * @author luo
	 * @throws Exception
	 * @describe 删除条件
	 */
	public boolean delTj(String pk, HttpServletRequest request)
	throws Exception {
		return dao.delTj(pk, request);
	}
	/**
	 * @author luo
	 * @describe 本学年是否已经进行过德育测评分的设置
	 */
	public boolean isDySz(String zgh, String xn) throws SQLException {
		return dao.isDySz(zgh, xn);
	}

	/**
	 * @author luo
	 * @describe 获得班级考勤分
	 */
	public List<HashMap<String, String>> getKqfList(ZjlgPjpyModel model,ZjlgPjpyForm myForm,
			String zgh, String[] colList, String userType) throws SQLException {
		return dao.getKqfList(model, myForm,zgh, colList, userType);
	}

	/**
	 * @author luo
	 * @throws Exception
	 * @describe 批量保存考勤分
	 */
	public boolean saveKqf(ZjlgPjpyModel model) throws Exception {
		return dao.saveKqf(model);
	}

	/**
	 * @author luo
	 * @throws Exception
	 * @describe 保存平时分比例设置
	 */
	public boolean saveBlSz(String xn, String zgh, ZjlgPjpyModel model,
			HttpServletRequest request) throws Exception {
		return dao.saveBlSz(xn, zgh, model, request);
	}

	/**
	 * @author luo
	 * @throws Exception
	 * @describe 保存德育比例设置
	 */
	public boolean saveDyBlSz(String xn, ZjlgPjpyModel model,
			HttpServletRequest request) throws Exception {
		return dao.saveDyBlSz(xn, model, request);
	}
	/**
	 * @author luo
	 * @throws Exception
	 * @describe 获取平时分比例设置
	 */
	public HashMap<String, String> getBlSz(String xn, String zgh)
	throws Exception {
		return dao.getBlSz(xn, zgh);
	}

	/**
	 * @author luo
	 * @throws Exception
	 * @describe 获取德育分比例设置
	 */
	public HashMap<String, String> getDyBlSz(String xn) throws Exception {
		return dao.getDyBlSz(xn);
	}

	/**
	 * @author luo
	 * @throws Exception
	 * @describe 获取综合素质比例设置
	 */
	public HashMap<String, String> getZhBlSz(String xn) throws Exception {
		return dao.getZhBlSz(xn);
	}

	/**
	 * @author luo
	 * @throws Exception
	 * @describe 保存综合素质比例设置
	 */
	public boolean saveZhBlSz(String xn, ZjlgPjpyModel model,
			HttpServletRequest request) throws Exception {
		return dao.saveZhBlSz(xn, model, request);
	}
	/**
	 * @author luo
	 * @describe 获得班级考勤分
	 */
	public List<HashMap<String, String>> getPsfList(ZjlgPjpyModel model,
			ZjlgPjpyForm myForm, String zgh, String[] colList, String userType)
			throws SQLException {
		return dao.getPsfList(model, myForm, zgh, colList, userType);
	}

	/**
	 * @author luo
	 * @throws Exception
	 * @describe 批量保存平时分
	 */
	public boolean savePsf(ZjlgPjpyModel model,String userType) throws Exception {
		return dao.savePsf(model,userType);
	}

	/**
	 * @author luo
	 * @throws Exception
	 * @describe 批量保存平时分(学院附加分)
	 */
	public boolean savePsfXyfjf(ZjlgPjpyModel model) throws Exception {
		return dao.savePsfXyfjf(model);
	}

	/**
	 * @author luning
	 * @param myForm 
	 * @throws Exception
	 * @describe 奖学金评奖名额列表
	 */
	public ArrayList<String[]> getJxjRsList(ZjlgPjpyModel myModel, ZjlgPjpyForm myForm) throws Exception {
		return dao.getJxjRsList(myModel,myForm);
	}

	/**
	 * @author luning
	 * @param bmlb 
	 * @throws Exception
	 * @describe 奖学金评奖名额表头
	 */
	public List<HashMap<String, String>> getJxjRsTopTr(String bmlb) {

		DAO dao = DAO.getInstance();
		String[] colList = null;
		String[] colListCN = null;

		//根据显示方式返回表头
		if(bmlb.equalsIgnoreCase("xydm")){
			colList = new String[]{"pk","xymc","cprs","jxjmc","jxjbl","jxjrs"}; 
			colListCN = new String[]{"pk","学院名称","参评人数","奖学金名称","奖学金比例","奖学金人数"}; 
		}else if ((bmlb.equalsIgnoreCase("bjdm"))){
			colList = new String[]{"pk","bjmc","cprs","jxjmc","jxjbl","jxjrs","cpzmc","cpzjxjrs"}; 
			colListCN = new String[]{"pk","班级名称","参评人数","奖学金名称","奖学金比例","奖学金人数","参评组名称","参评组奖学金人数"}; 
		}else if ((bmlb.equalsIgnoreCase("cpzdm"))){
			colList = new String[]{"pk","cpzmc","xymc","jxjmc","cprs","jxjbl","jxjrs"}; 
			colListCN = new String[]{"pk","参评组名称","学院名称","奖学金名称","参评人数","奖学金比例","奖学金人数"}; 
		}

		List<HashMap<String, String>> topTr = dao.arrayToList(colList,
				colListCN);

		return topTr;
	}

	/**
	 * @author luning
	 * @param myForm 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws SecurityException 
	 * @throws IllegalArgumentException 
	 * @throws Exception
	 * @describe 荣誉称号名额列表
	 */
	public ArrayList<String[]> getRychRsList(ZjlgPjpyModel myModel, ZjlgPjpyForm myForm) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		return dao.getRychRsList(myModel,myForm);
	}

	/**
	 * @author luning
	 * @param bmlb 
	 * @throws Exception
	 * @describe 荣誉称号名额表头
	 */
	public List<HashMap<String, String>> getRychRsTopTr(String bmlb) {
		DAO dao = DAO.getInstance();
		String[] colList = new String[]{"pk","bmmc","rychmc","cprs","rychbl","rychrs"}; 
		String[] colListCN = null;

		//根据显示方式返回表头
		if(bmlb.equalsIgnoreCase("xydm")){
			colListCN = new String[]{"pk","学院名称","荣誉称号名称","参评人数","荣誉称号比例","荣誉称号人数"}; 
		}else if ((bmlb.equalsIgnoreCase("bjdm"))){
			colListCN = new String[]{"pk","班级名称","荣誉称号名称","参评人数","荣誉称号比例","荣誉称号人数"}; 
		}else if ((bmlb.equalsIgnoreCase("zydm"))){
			colListCN = new String[]{"pk","专业名称","荣誉称号名称","参评人数","荣誉称号比例","荣誉称号人数"}; 
		}

		List<HashMap<String, String>> topTr = dao.arrayToList(colList,
				colListCN);

		return topTr;
	}

	/**
	 * @author luning
	 * @param myForm 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws SecurityException 
	 * @throws IllegalArgumentException 
	 * @throws Exception
	 * @describe 先进班级名额列表
	 */
	public ArrayList<String[]> getXjbjRsList(ZjlgPjpyModel myModel, ZjlgPjpyForm myForm) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		return dao.getXjbjRsList(myModel,myForm);
	}

	/**
	 * @author luning
	 * @throws Exception
	 * @describe 先进班级名额表头
	 */
	public List<HashMap<String, String>> getXjbjRsTopTr() {
		DAO dao = DAO.getInstance();
		String[] colList = null;
		String[] colListCN = null;

		//根据显示方式返回表头
		colList = new String[]{"pk","xn","xymc","bjgs","szbl","me"}; 
		colListCN = new String[]{"pk","学年","学院名称","班级数量","设置比例","名额"}; 
		List<HashMap<String, String>> topTr = dao.arrayToList(colList,
				colListCN);
		return topTr;
	}

	public List<HashMap<String, String>> getCpzBjList(ZjlgPjpyModel myModel) {
		String xn = Base.getJxjsqxn();
		String xydm = DealString.toGBK(myModel.getXydm());
		String cpzdm = DealString.toGBK(myModel.getCpzdm());
		return dao.getCpzBjList(xn,xydm,cpzdm);
	}

	public List<HashMap<String, String>> getCpzList(ZjlgPjpyModel myModel) throws Exception {
		String xn = myModel.getXn();
		String xydm = DealString.toGBK(myModel.getXydm());
		return dao.getCpzList(xn,xydm);
	}

	/**
	 * @author luning
	 * @throws Exception
	 * @describe 增加参评组
	 */
	public boolean addCpz(HttpServletRequest request) {
		String tableName = "zjlg_cpzszb";
		String cpzdm = DealString.toGBK(request.getParameter("zjcpzdm"));
		String cpzmc = DealString.toGBK(request.getParameter("cpzmc"));
		String cpzxydm = request.getParameter("cpzxydm");
		HttpSession session =request.getSession();
		String userType = (String)session.getAttribute("userType");
		String userDep = (String)session.getAttribute("userDep");
		if (userType.equalsIgnoreCase("xy")
				&& (cpzxydm == null || cpzxydm.equalsIgnoreCase(""))) {
			cpzxydm = userDep;
		}
		String xn = Base.getJxjsqxn();
		boolean update = StandardOperation.insert(tableName, new String[]{"xydm","cpzdm","xn","cpzmc"}, new String[]{cpzxydm,cpzxydm+cpzdm,xn,cpzmc}, request);
		return update;
	}

	public List<HashMap<String, String>> getWfpBjList(ZjlgPjpyModel myModel) {
		// TODO 自动生成方法存根
		String xydm = myModel.getXydm();
		String zydm = myModel.getZydm();
		String nj   = myModel.getNj();
		String xn   = Base.getJxjsqxn();
		return dao.getWfpBjList(xydm,zydm,nj,xn);
	}

	public boolean saveCpzFp(HttpServletRequest request) throws Exception {
		// TODO 自动生成方法存根
		String xn = Base.getJxjsqxn();
		String xydm = request.getParameter("xydm");
		HttpSession session =request.getSession();
		String userType = (String)session.getAttribute("userType");
		String userDep = (String)session.getAttribute("userDep");
		if (userType.equalsIgnoreCase("xy")
				&& (xydm == null || xydm.equalsIgnoreCase(""))) {
			xydm = userDep;
		}
		String cpzdm = DealString.toGBK(request.getParameter("cpzdm"));
		String bjdms = DealString.toGBK(request.getParameter("bjdms"));
		return dao.saveCpzFp(xn,cpzdm,bjdms,xydm,request);
	}

	public boolean jxjcsh() throws Exception {
		// TODO 自动生成方法存根
		return dao.jxjcsh();
	}

	public boolean plszSave(ZjlgPjpyModel myModel, String userType) throws Exception {
		//批量设置保存
		return dao.plszSave(myModel,userType);
	}

	public boolean saveJxjRs(String pk, ZjlgPjpyModel myModel, HttpServletRequest request) throws Exception {
		// TODO 自动生成方法存根
		return dao.saveJxjRs(pk,myModel,request);
	}

	public boolean saveJxjXn(String jxjxn, HttpServletRequest request) throws Exception {
		// TODO 自动生成方法存根

		boolean updated = dao.saveJxjXn(jxjxn,request);
		if(updated){
			Base.setJxjsqxn(jxjxn);
		}
		return updated;
	}


	/**
	 * @author luo
	 * @describe 获得班级卫生分
	 */
	public List<HashMap<String, String>> getWsfList(ZjlgPjpyModel model,
			ZjlgPjpyForm myForm, String zgh, String[] colList, String userType)
			throws SQLException {
		return dao.getWsfList(model, myForm, zgh, colList, userType);
	}

	/**
	 * @author luo
	 * @throws Exception
	 * @describe 批量保存卫生分
	 */
	public boolean saveWsf(ZjlgPjpyModel model,String userType) throws Exception {
		return dao.saveWsf(model,userType);
	}

	/**
	 * @author luo
	 * @throws Exception
	 * @describe 批量保存卫生分(学院附加分)
	 */
	public boolean saveWsfXyfjf(ZjlgPjpyModel model) throws Exception {
		return dao.saveWsfXyfjf(model);
	}

	/**
	 * @author luo
	 * @throws Exception
	 * @describe 判断是否设置过德育测评分最高分
	 */
	public boolean isMaxDy(String xn) {
		return dao.isMaxDy(xn);
	}
	/**
	 * @author luo
	 * @describe 获得德育测评总分
	 */
	public List<HashMap<String, String>> getZfList(ZjlgPjpyModel model,
			ZjlgPjpyForm myForm, String zgh, String[] colList, String userType,
			String userDep) throws SQLException {
		return dao.getZfList(model, myForm, zgh, colList, userType, userDep);
	}

	/**
	 * @author luo
	 * @describe 获得综合素质测评分
	 */
	public List<HashMap<String, String>>  getZhszcpfList(ZjlgPjpyModel model,ZjlgPjpyForm myForm,
			String zgh, String[] colList,String userType, String isBzr) throws SQLException {
		return dao.getZhszcpfList(model,myForm, zgh, colList, userType, isBzr);
	}

	/**
	 * @author luo
	 * @throws Exception
	 * @describe 计算综合素质测评分
	 */
	public boolean saveZhszcpf(ZjlgPjpyModel model) throws Exception {
		return dao.saveZhszcpf(model);
	}

	/**
	 * @author luo
	 * @describe 获得综合素质测评打印列表
	 */
	public List<HashMap<String, String>> getZhszfList(ZjlgPjpyModel model)
	throws SQLException {
		return dao.getZhczPrintList(model);
	}

	/**
	 * @author luo
	 * @throws SQLException 
	 * @describe 综合素质测评报表打印
	 */
	public void printZhszcp(WritableWorkbook wwb, String xn,String zydm,String bjdm) throws SQLException {

		String xxmc = StandardOperation.getXxmc();
		ZjlgPjpyModel myModel = new ZjlgPjpyModel();
		myModel.setXn(xn);
		myModel.setZydm(zydm);
		myModel.setBjdm(bjdm);

		List<HashMap<String, String>> list = dao.getZhczPrintList(myModel);
		String systime = getSysTime();
		try {
			WritableSheet ws = wwb.getSheet(0);
			WritableCellFormat wcfTytle = new WritableCellFormat();
			Alignment alignMent = Alignment.CENTRE;
			VerticalAlignment vag = VerticalAlignment.CENTRE;

			wcfTytle.setVerticalAlignment(vag);
			wcfTytle.setAlignment(alignMent);
			wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);

			WritableFont wfTytle = new WritableFont(WritableFont.ARIAL);
			wfTytle.setBoldStyle(WritableFont.BOLD);
			wfTytle.setPointSize(14);
			wcfTytle.setFont(wfTytle);

			ws.addCell(new Label(0, 0, xxmc  + xn + "学年综合测评汇总表",
					wcfTytle));

			wcfTytle = new WritableCellFormat();
			alignMent = Alignment.RIGHT;
			vag = VerticalAlignment.CENTRE;

			wcfTytle.setVerticalAlignment(vag);
			wcfTytle.setAlignment(alignMent);
			wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);

			wfTytle = new WritableFont(WritableFont.ARIAL);
			wfTytle.setBoldStyle(WritableFont.BOLD);
			wfTytle.setPointSize(12);
			wcfTytle.setFont(wfTytle);

			wcfTytle = new WritableCellFormat();
			alignMent = Alignment.RIGHT;
			vag = VerticalAlignment.CENTRE;

			wcfTytle.setVerticalAlignment(vag);
			wcfTytle.setAlignment(alignMent);
			wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);

			wfTytle = new WritableFont(WritableFont.ARIAL);
			wfTytle.setPointSize(10);
			wcfTytle.setFont(wfTytle);

			ws.addCell(new Label(0, 2, systime,
					wcfTytle));


			wcfTytle = new WritableCellFormat();
			alignMent = Alignment.LEFT;
			vag = VerticalAlignment.CENTRE;

			wcfTytle.setVerticalAlignment(vag);
			wcfTytle.setAlignment(alignMent);
			wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);

			wfTytle = new WritableFont(WritableFont.ARIAL);
			wfTytle.setBoldStyle(WritableFont.BOLD);
			wfTytle.setPointSize(12);
			wcfTytle.setFont(wfTytle);

			wcfTytle = new WritableCellFormat();
			alignMent = Alignment.CENTRE;
			vag = VerticalAlignment.CENTRE;

			wcfTytle.setVerticalAlignment(vag);
			wcfTytle.setAlignment(alignMent);
			wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);

			wfTytle = new WritableFont(WritableFont.ARIAL);
			wfTytle.setPointSize(10);
			wcfTytle.setFont(wfTytle);

			int rownum = 0;

			for (int i = 0; i < list.size(); i++) {

				HashMap<String, String> map = new HashMap<String, String>();
				map = (HashMap<String, String>) list.get(i);

				ws.addCell(new Label(0, 5 + rownum, map.get("pm"), wcfTytle));
				ws.addCell(new Label(1, 5 + rownum, map.get("xh"), wcfTytle));
				ws.addCell(new Label(2, 5 + rownum, map.get("bjmc"), wcfTytle));
				ws.addCell(new Label(3, 5 + rownum, map.get("xm"), wcfTytle));
				ws.addCell(new Label(4, 5 + rownum, map.get("dycpf"), wcfTytle));
				ws.addCell(new Label(5, 5 + rownum, map.get("dypm"), wcfTytle));
				ws.addCell(new Label(6, 5 + rownum, map.get("zycpf"), wcfTytle));
				ws.addCell(new Label(7, 5 + rownum, map.get("zypm"), wcfTytle));
				ws.addCell(new Label(8, 5 + rownum, map.get("bjg"), wcfTytle));
				ws.addCell(new Label(9, 5 + rownum, map.get("zhcpf"), wcfTytle));

				rownum++;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		// 向客户端输出
		ExcelMethods.submitWritableWorkbookOperations(wwb);

	}

	/**
	 * @author luo
	 * @throws Exception
	 * @describe 保存条件设置
	 */
	public boolean saveTjsz(String xn,String szlx,String jxjdm,String zdm,String tj,String tjlx,String tjz,
			HttpServletRequest request) throws Exception {
		return dao.saveTjsz(xn, szlx, jxjdm, zdm,tj,tjlx,tjz, request);
	}

	/**
	 * @author luo
	 * @describe 德育分条件 
	 * @ xn(学年) 
	 * @ szlx(荣誉称号：rych；先进班级：xjbj；奖学金：jxj；)
	 * @ jxjdm(奖学金代码，若设置类型非奖学金，为空)
	 * @ dyf(德育测评分)
	 */
	public boolean dyfTj(String xn, String szlx,String jxjdm, String dyf) {
		if(Base.isNull(dyf)){
			dyf="0";
		}
		return dao.dyfTj(xn, szlx, jxjdm,dyf);
	}

	/**
	 * @author luo
	 * @describe 智育育分条件 
	 * @ xn(学年) 
	 * @ szlx(荣誉称号：rych；先进班级：xjbj；奖学金：jxj；)
	 * @ jxjdm(奖学金代码，若设置类型非奖学金，为空)
	 * @ zyf(智育测评分)
	 */
	public boolean zyfTj(String xn, String szlx,String jxjdm, String zyf) {
		if(Base.isNull(zyf)){
			zyf="0";
		}
		return dao.zyfTj(xn, szlx, jxjdm,zyf);
	}

	/**
	 * @author luo
	 * @describe 综合素质分条件
	 * @ xn(学年) 
	 * @ szlx(荣誉称号：rych；先进班级：xjbj；奖学金：jxj；)
	 * @ jxjdm(奖学金代码，若设置类型非奖学金，为空)
	 * @ zhszf(综合素质测评分)
	 */
	public boolean zhszfTj(String xn, String szlx,String jxjdm, String zhszf) {
		if(Base.isNull(zhszf)){
			zhszf="0";
		}
		return dao.zhszfTj(xn, szlx, jxjdm, zhszf);
	}

	/**
	 * @author luo
	 * @throws SQLException 
	 * @describe 德育成绩排名条件
	 * @ xh(学号)
	 * @ xn(学年) 
	 * @ szlx(荣誉称号：rych；先进班级：xjbj；奖学金：jxj；)
	 * @ jxjdm(奖学金代码，若设置类型非奖学金，为空)
	 * @ cpzdm(参评组代码)
	 */
	public boolean dyfpmTj(String xh, String xn, String szlx, String jxjdm,
			String cpzdm) throws SQLException {
		return dao.dyfpmTj(xh, xn, szlx, jxjdm, cpzdm);
	}

	/**
	 * @author luo
	 * @throws SQLException 
	 * @describe 智育成绩排名条件
	 * @ xh(学号)
	 * @ xn(学年) 
	 * @ szlx(荣誉称号：rych；先进班级：xjbj；奖学金：jxj；)
	 * @ jxjdm(奖学金代码，若设置类型非奖学金，为空)
	 * @ cpzdm(参评组代码)
	 */
	public boolean zyfpmTj(String xh, String xn, String szlx, String jxjdm,
			String cpzdm) throws SQLException {
		return dao.zyfpmTj(xh, xn, szlx, jxjdm, cpzdm);
	}

	/**
	 * @author luo
	 * @throws SQLException 
	 * @describe 综合素质测评成绩排名条件
	 * @ xh(学号)
	 * @ xn(学年) 
	 * @ szlx(荣誉称号：rych；先进班级：xjbj；奖学金：jxj；)
	 * @ jxjdm(奖学金代码，若设置类型非奖学金，为空)
	 * @ cpzdm(参评组代码)
	 */
	public boolean zhpmTj(String xh, String xn, String szlx, String jxjdm,
			String cpzdm) throws SQLException {
		return dao.zhpmTj(xh, xn, szlx, jxjdm, cpzdm);
	}

	/**
	 * @author luo
	 * @describe 班级平均分条件
	 * @ xn(学年) 
	 * @ szlx(荣誉称号：rych；先进班级：xjbj；奖学金：jxj；)
	 * @ jxjdm(奖学金代码，若设置类型非奖学金，为空)
	 * @ bjdm(班级代码，该班级的平均分)
	 */
	public boolean bjpjfTj(String xn, String szlx, String jxjdm,
			String bjdm) {
		return dao.bjpjfTj(xn, szlx, jxjdm, bjdm);
	}

	/**
	 * @author luo
	 * @describe 不及格率条件
	 * @ xn(学年) 
	 * @ szlx(荣誉称号：rych；先进班级：xjbj；奖学金：jxj；)
	 * @ jxjdm(奖学金代码，若设置类型非奖学金，为空)
	 * @ bjdm(班级代码，该班级的不及格率)
	 */
	public boolean bjglTj(String xn, String szlx, String jxjdm,
			String bjdm) {
		return dao.bjglTj(xn, szlx, jxjdm, bjdm);
	}

	/**
	 * @author luo
	 * @describe 文明寝室个数条件
	 * @ xn(学年) 
	 * @ szlx(荣誉称号：rych；先进班级：xjbj；奖学金：jxj；)
	 * @ jxjdm(奖学金代码，若设置类型非奖学金，为空)
	 * @ wmqsgs(文明寝室数量)
	 */
	public boolean wmqsTj(String xn, String szlx,String jxjdm, String wmqsgs) {
		return dao.wmqsTj(xn, szlx, jxjdm, wmqsgs);
	}

	/**
	 * @author luo
	 * @describe A级文明寝室个数条件
	 * @ xn(学年) 
	 * @ szlx(荣誉称号：rych；先进班级：xjbj；奖学金：jxj；)
	 * @ jxjdm(奖学金代码，若设置类型非奖学金，为空)
	 * @ ajqsgs(A级文明寝室数量)
	 */
	public boolean ajqsgsTj(String xn, String szlx,String jxjdm, String ajqsgs) {
		return dao.ajqsgsTj(xn, szlx, jxjdm, ajqsgs);
	}

	/**
	 * @author luo
	 * @describe 智育成绩递增条件
	 * @ xn(学年) 
	 * @ szlx(荣誉称号：rych；先进班级：xjbj；奖学金：jxj；)
	 * @ jxjdm(奖学金代码，若设置类型非奖学金，为空)
	 * @ bfb(去年专业排名百分比)
	 * @ qbfb(千年专业排名百分比)
	 */
	public boolean zypmdzTj(String xn, String szlx,String jxjdm, String bfb,String qbfb) {
		return dao.zypmdzTj(xn, szlx, jxjdm, bfb, qbfb);
	}

	/**
	 * @author luo
	 * @describe 外语成绩条件
	 * @ xn(学年) 
	 * @ szlx(荣誉称号：rych；先进班级：xjbj；奖学金：jxj；)
	 * @ jxjdm(奖学金代码，若设置类型非奖学金，为空)
	 * @ wycj(外语成绩)
	 */
	public boolean wycjTj(String xn, String szlx,String jxjdm, String wycj) {
		return dao.wycjTj(xn, szlx, jxjdm, wycj);
	}

	/**
	 * @author luo
	 * @describe 是否优秀班级条件
	 * @ xn(学年) 
	 * @ szlx(荣誉称号：rych；先进班级：xjbj；奖学金：jxj；)
	 * @ jxjdm(奖学金代码，若设置类型非奖学金，为空)
	 * @ isorno("是"or"否")
	 */
	public boolean sfyxbjTj(String xn, String szlx,String jxjdm, String isorno) {
		return dao.sfyxbjTj(xn, szlx, jxjdm, isorno);
	}
	/**
	 * @author luo
	 * @describe 获得该年度排名百分比
	 */
	public String getZybfb(String xn,String xh,String cpzdm) throws SQLException {
		return dao.getZybfb(xn, xh, cpzdm);
	}
	/**
	 * @author luo
	 * @describe 是否存在智育递增条件
	 */
	public boolean iszydztj(String xn, String jxjdm) throws SQLException {
		return dao.iszydztj(xn, jxjdm);
	}

	/**
	 * 获取荣誉称号列表
	 * @return
	 */
	public List<HashMap<String,String>>serv_getRychList(){
		return dao.getRychList();
	}
	/**
	 * 获取学生相关信息
	 */
	public HashMap<String,String> serv_getXsInfo(String xh){
		String querry = " where xh='"+xh+"' ";
		return dao.dao_getInfo("view_xsxxb", querry);
	}
	/**
	 * jxjdm获取路径
	 */
	public HashMap<String,String> getJxjlj(String jxjdm){
		return dao.dao_getJxjlj(jxjdm);
	}
	/**
	 * 判断该生所申请荣誉称号是否重复且是否通过审核
	 */
	public HashMap<String,String> serv_rychSqPd(RychModel model){
		String pk = "xh||xn||rychdm";
		String querry = " where  "+pk+"='"+model.getXh()+Base.getJxjsqxn()+model.getRychdm()+"' and xysh='通过' ";
		return dao.dao_getInfo("zjlg_xsrychb", querry);
	}
	/**
	 *荣誉称号申请信息保存 
	 * @throws Exception 
	 */
	public boolean serv_rychSave(RychModel model) throws Exception{
		return dao.dao_rychSave(model);
	}
	/**
	 * 荣誉称号申请信息查询表头
	 */
	public ArrayList<HashMap<String, String>> serv_getRychQerrTitle() {
		String[] colListCN = new String[]{"主键","学年","学号","姓名","荣誉称号","申请时间","院系审核","学校审核"};
		return dao.dao_getSearchTitle(colListCN);
	}
	/**
	 * 荣誉称号申请信息查询
	 */
	public ArrayList<String[]> serv_rychDefault(RychModel model){
		return dao.dao_rychDefault(model);
	}
	/**
	 *学生荣誉称号信息添加保存 
	 */
	public boolean serv_rychAddSave(RychModel model) throws Exception{
		return dao.dao_rychAddSave(model);
	}
	/**
	 * 获取学生相关信息
	 */
	public HashMap<String,String> serv_getRychxxForXh(String pkValue){
		String querry = " where xh||xn||rychdm='"+pkValue+"' ";
		return dao.dao_getInfo("view_zjlg_xsrychxx", querry);
	}
	/**
	 *学生荣誉称号信息 修改保存 
	 */
	public boolean serv_rychModi(RychModel model,String pkValue) throws Exception{
		return dao.dao_rychAddModi(model,pkValue);
	}
	/**
	 *学生荣誉称号信息 删除
	 */
	public boolean serv_rychDelete(String delPk) throws Exception{
		return dao.dao_rychDelete(delPk);
	}
	/**
	 * 获取审核列表
	 */
	public List<HashMap<String, String>> serv_getChkList() {
		return dao.getChkList();
	}
	/**
	 * 荣誉称号审核信息查询
	 */
	public ArrayList<String[]> serv_rychCkDefault(RychModel model,String userType){
		return dao.dao_rychCkDefault(model, userType);
	}
	/**
	 * 荣誉称号申请信息查询表头
	 */
	public ArrayList<HashMap<String, String>> serv_getRychCkQerrTitle() {
		//String[] colList = new String[]{"pk","xn","xh","xm","rychmc","sqsj","xysh","xxsh","zycpf","dycpf","zhcpf","xypm","zypm","bjpm"};
		String[] colListCN = new String[]{"主键","学年","学号","姓名","荣誉称号","申请时间","院系审核","学校审核","智育分","德育分","综合测评分","所在参评组排名"};
		return dao.dao_getSearchTitle(colListCN);
	}
	/**
	 * 荣誉称号审核
	 */
	public boolean serv_rychCk(String pkVStr,String userType,String check) throws Exception{
		return dao.dao_rychCk(pkVStr, userType, check);
	}
	/**
	 * 奖学金列表
	 * @param jxjlbdm 
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getJxjList(String jxjlbdm) throws Exception {
		dao = new ZjlgPjpyDAO();
		return dao.getJxjList(jxjlbdm);
	}
	/**
	 * 奖学金列表
	 * @param jxjlbdm 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> gettycj(String xh,String xn) throws Exception {
		return dao.gettycj(xh,xn);
	}
	/**
	*学生奖学金添加保存 
	*/
		public boolean dao_jxjAddSave(ZjlgPjpyModel model) throws Exception{
			return dao.dao_jxjAddSave(model);
		}
     /**
	*学生奖学金是否重复提交 
	*/
		public boolean dao_jxjiscftj(ZjlgPjpyModel model) throws Exception{
			return dao.dao_jxjiscftj(model);
		}
	/**
	 * 奖学金查询表头查询表头
	 */
	public ArrayList<HashMap<String, String>> getjxjTitle() {
		String[] colListCN = new String[]{"主键","学年","学号","姓名","奖学金项目","奖学金类别","申请时间","院系审核","学校审核"};
		return dao.dao_getSearchTitle(colListCN);
	}
	/**
	 * 流程追踪查询表头
	 */
	public ArrayList<HashMap<String, String>> getWorkFlowTitle() {
		String[] colListCN = new String[]{"流程名称","审核人","审核时间","流程状态","意见"};
		return dao.dao_getSearchTitle(colListCN);
	}

	/**
	* 奖学金查询表头查询表头
	*/
	public ArrayList<HashMap<String, String>> getjxjshTitle(String userType) {
		String[] colListCN;
		if("xy".equals(userType)){
			colListCN = new String[]{"主键","学号","姓名","奖学金类别","申请时间","班级名称","院系审核","德育成绩","体育成绩","智育成绩","综合测评排名"};
		}else{
			colListCN = new String[]{"主键","学号","姓名","奖学金类别","申请时间","班级名称","院系审核","学校审核","德育成绩","体育成绩","智育成绩","综合测评排名"};
		}
		return dao.dao_getSearchTitle(colListCN);
	}
			
	/**
	* 奖学金发放表头查询表头
	*/
	public ArrayList<HashMap<String, String>> getjxjffTitle(String userType) {
		String[] colListCN;
		if("xy".equals(userType)){
			colListCN = new String[]{"学号","姓名","奖学金名称","奖励金额","学院名称","班级名称"};
		}else{
			colListCN = new String[]{"学号","姓名","奖学金名称","奖励金额","学院名称","班级名称"};
		}
		return dao.dao_getSearchTitle(colListCN);
	}
	/**
	 * 奖学金查询
	 */
	public ArrayList<String[]> jxj_query(ZjlgPjpyForm myForm,ZjlgPjpyModel model,String userType){
		return dao.jxj_query(myForm,model, userType);
	}
	/**
	 * 奖学金记录查询
	 */
	public String jxj_queryrsunm(ZjlgPjpyForm myForm,ZjlgPjpyModel model,String userType){
		return dao.jxj_queryrsunm(myForm,model, userType);
	}
	/**
	 * 奖学金查询view
	 */
	public HashMap<String, String> jxj_view(String pk,String jxjcxzj){
		return dao.jxj_view(pk,jxjcxzj);
	}
	/**
	 * 奖学金审核查询
	 */
	public ArrayList<String[]> jxj_shquery(ZjlgPjpyForm myForm,ZjlgPjpyModel model,String userType){
		return dao.jxj_shquery(myForm,model, userType);
	}
	/**
	 * 奖学金发放查询
	 */
	public ArrayList<String[]> jxj_ffquery(ZjlgPjpyForm myForm,ZjlgPjpyModel model,String userType){
		return dao.jxj_ffquery(myForm,model, userType);
	}
	/**
	 * 奖学金导出
	 * @throws IOException 
	 * @throws WriteException 
	 */
	public void jxj_DataExport(ZjlgPjpyForm myForm,ZjlgPjpyModel model,String jxjdm,HttpServletResponse response) throws WriteException, IOException{
		dao.jxj_DataExport(myForm,model,jxjdm,response);
	}
	/**
	 * 获取班级基本信息
	 */
	public HashMap<String,String> serv_getBjxx(String pkValue){
		String querry = " where bjdm='"+pkValue+"' ";
		return dao.dao_getInfo("view_njxyzybj", querry);
	}
	/**
	 * 获取班级辅导员
	 */
	public String serv_getFdyxxForBj(String pkValue) throws Exception{
		return dao.dao_getFdyForBjdm(pkValue);
	}
	/**
	 * 统计班级人数
	 */
	public String serv_getXhNum(String bjdm){
		return dao.dao_getXhNum(bjdm);
	}
	/**
	 * 先进班级申请保存
	 */
	public boolean serv_xjbjsqSave(XjbjModel model) throws Exception{
		return dao.dao_xjbjsqSave(model);
	}
	/** 
	 * Method 批量删除
	 * @param form 对象
	 * @return void
	 * @throws Exception 
	 */
	public String getAllDelList(String pks) throws Exception {
		String whichpk = dao.getAllDelList(pks);
		return whichpk;
	}

	public boolean xjbjPlszSave(ZjlgPjpyModel myModel) throws Exception {
		//先进班级批量设置保存
		return dao.xjbjPlszSave(myModel);
	}

	public boolean saveXjbjMe(String pk, ZjlgPjpyModel myModel, HttpServletRequest request) throws Exception {
		// 先进班级单个调整保存
		String me = myModel.getMe();
		return dao.saveXjbjMe(pk,me,request);
	}

	public String getXjbjMe(String xn,String xydm) {
		// 所属学院先进班级名额
		return dao.getXjbjMe(xn,xydm);
	}	
	/**
	 * 先进班级申请信息查询表头
	 */
	public ArrayList<HashMap<String, String>> serv_getXjbjQerrTitle() {
		String[] colListCN = new String[]{"主键","学年","班级","年级","院系","申请时间","院系审核","学校审核"};
		return dao.dao_getSearchTitle(colListCN);
	}

	/**
	 * 先进班级申请信息查询表头
	 */
	public ArrayList<String[]> serv_xjbjDefault(XjbjModel model) {
		return dao.dao_xjbjDefault(model);
	}
	/**
	 * 先进班级信息添加
	 */
	public boolean serv_xjbjAddSave(XjbjModel model) throws Exception{
		return dao.dao_xjbjAddSave(model);
	}
	/**
	 * 获取先进班级相关信息
	 */
	public HashMap<String,String> serv_getXjbjxxForBj(String pkValue){
		String querry = " where bjdm||xn='"+pkValue+"' ";
		return dao.dao_getInfo("view_zjlg_xjbjxx", querry);
	}
	/**
	 * 先进班级信息修改结果保存
	 */
	public boolean serv_xjbjModiSave(XjbjModel  model,String pkValue) throws Exception{
		return dao.dao_xjbjModiSave(model, pkValue);
	}
	/**
	 *先进班级信息 删除
	 */
	public boolean serv_xjbjDelete(String delPk) throws Exception{
		return dao.dao_xjbjDelete(delPk);
	}
	/**
	 * 先进班级审核信息查询
	 */
	public ArrayList<String[]> serv_xjbjCkDefault(XjbjModel model,String userType){
		return dao.dao_xjbjCkDefault(model, userType);
	}
	/**
	 * 先进班级审核
	 */
	public boolean serv_xjbjCk(String pkVStr,String userType,String check) throws Exception{
		return dao.dao_xjbjCk(pkVStr, userType, check);
	}
	/**
	 * 根据学号获取相关成绩
	 */
	public HashMap<String,String> getStuCjForXh(ZjlgPjpyModel model,String xn,String xh) throws SQLException{
		HashMap<String,String> map = new HashMap<String,String>();		
		model.setXn(xn);
		model.setXh(xh);
		List<HashMap<String,String>> zhcj = dao.getZhczPrintList(model);
		if(zhcj != null){
			if (zhcj.size()>0 ){
				map = zhcj.get(0);
			}
		}
		return map;
	}
	/**
	 * 根据学号获取参评组代码
	 */
	public String getCpzdmForXh(String xh,String xn) throws SQLException{		
		return dao.dao_getCpzdmForXh(xh,xn);
	}
	/**
	 * 奖学金申请审核
	 */
	public boolean serv_JxjSqCk(String pkVStr,String userType,String check,String shyj,String workFlowName,String checkName) throws Exception{
		return dao.dao_jxjSqCk(pkVStr, userType, check,shyj,workFlowName,checkName);
	}
	/**
	 * 审核
	 */
	public boolean serv_audit(String pkVStr,String userType,String check,String shyj,String workFlowName,String checkName,String tableName) throws Exception{
		return dao.dao_audit(pkVStr, userType, check,shyj,workFlowName,checkName,tableName);
	}
	/**
	 * 奖学金申请审核--退回
	 * author lyl
	 */
	public boolean serv_JxjBack(String pkVStr,String userType,String check,String thly,String workFlowName,String checkName,String tableName) throws Exception{
		return dao.dao_jxjBack(pkVStr, userType, check,thly,workFlowName,checkName,tableName);
	}
	/**
	 * 流程追踪
	 * author lyl
	 */
	public ArrayList<String[]> workFlowQuery(String pkVStr,String tableName) throws Exception{
		return dao.dao_workFlowQuery(pkVStr,tableName);
	}
	
	/**
	 * 根据奖学金主键获取xh，xn,jxjdm
	 */
	public HashMap<String, String> jxj_getxhxnjxjdm(String pkVStr) throws Exception{
		return dao.jxj_getxhxnjxjdm(pkVStr);
	}

	public boolean rychcsh() throws Exception {
		// 荣誉称号初始化
		return dao.rychcsh();
	}

	public boolean rychPlszSave(ZjlgPjpyModel myModel) throws Exception {
		// TODO 自动生成方法存根
		return dao.rychPlszSave(myModel);
	}

	public boolean saveRychRs(String pk, ZjlgPjpyModel myModel, HttpServletRequest request) throws Exception {
		// TODO 自动生成方法存根
		return dao.saveRychRS(pk,myModel,request);
	}

	public String getRychMe (String bmlb,String bmdm,String rychdm,String xn) throws Exception {
		//获取荣誉称号名额 bmlb为学院为"xydm",专业为"zydm",班级为"bjdm"
		return dao.getRychMe(bmlb, bmdm, rychdm, xn);
	}

	public boolean delCpz(HttpServletRequest request) throws Exception {
		// TODO 自动生成方法存根
		return dao.delCpz(request);
	}
	public String getYycj (String xh,String xn) throws Exception {
		// 奖学金名额 bmlb为学院为"xydm",参评组为"cpzdm",班级为"bjdm"
		return dao.getYycj(xh, xn);
	}
		
	public boolean getJxjMe (String bmlb,String bmdm,String jxjdm,String xn) throws Exception {
		// 奖学金名额 bmlb为学院为"xydm",参评组为"cpzdm",班级为"bjdm"
		return dao.getJxjMe(bmlb, bmdm, jxjdm, xn);
	}
	
	public String getIsbys (String xh) throws Exception {
		// 是否毕业生
		return dao.getIsbys(xh);
	}
	public boolean getIsjxjjd (String xh,String xn,String jxjdm,String usertype) throws Exception {
		// 奖学金间的判断
		return dao.getIsjxjjd(xh,xn,jxjdm,usertype);
	}
	public ArrayList<HashMap<String, String>> getCfqk (String xh,String xn,String xq) throws Exception {
		// 处罚
		return dao.getCfqk(xh,xn,xq);
	}
	/**
	 * 荣誉称号申请条件判断
	 *  @param rychdm 荣誉称号代码
	 * @param xh 学号
	 * @param xn 学年
	 * @param zyfwdm 资源范围代码（参评组）
	 * @param dycj 德育成绩
	 * @param zycj 智育成绩
	 * @param zhcj 综合测评成绩
	 * @return
	 * @throws SQLException
	 */
	public boolean rychSqTjPd(String rychdm,String xh,String xn,String zyfwdm,String dycj,String zycj,String zhcj,String tycj) throws SQLException{	
		//其中有一项不满足条件即返回false
		String szlx = "rych";
		if(!dao.dyfTj(xn, szlx, rychdm, dycj)){// 德育分
			return false;
		}else if(!dao.zyfTj(xn,szlx,rychdm,zycj)){//智育分
			return false;
		}else if(!dao.zhszfTj(xn,szlx,rychdm,zhcj)){//综合素质测评分
			return false;
		}else if(!dao.dyfpmTj(xh, xn,szlx, rychdm,zyfwdm)){//德育成绩排名
			return false;
		}else if(!dao.zyfpmTj(xh, xn,szlx,rychdm,zyfwdm)){//智育分排名
			return false;
		}else if(!dao.zhpmTj(xh, xn, szlx,rychdm,zyfwdm)){//综合素质测评分排名
			return false;
		}
		else if(!dao.tyfTj(xn, szlx, rychdm, tycj)){
			return false;
		}
		else {
			return true;
		}		
	}
	/**
	 * 先进班级申请条件判断
	 * @param bjdm 班级代码
	 * @param xn 学年
	 * @param wmqsgs 文明寝室个数
	 * @param ajqsgs A级寝室个数
	 * @return
	 */
	public boolean xjbjSqTjPd(String bjdm,String xn,String wmqsgs,String ajqsgs,String sfyxbj ){
//		其中有一项不满足条件即返回false
		String szlx = "xjbj";
		if(!dao.bjpjfTj(xn, szlx,"", bjdm)){//班级平均成绩
			return false;
		}else if(!dao.bjglTj(xn,szlx,"", bjdm)){//班级不及格率
			return false;
		}else if(!dao.wmqsTj(xn,szlx, "",wmqsgs)){ //文明寝室个数条件
			return false;
		}else if(!dao.ajqsgsTj(xn,szlx,"", ajqsgs)){//A级寝室个数
			return false;
		}else if(!dao.sfyxbjTj(xn, szlx, "", sfyxbj)){//
		    return false;
		}else{
			return true;
		}
	}
//	public boolean rychCkRsPd(String xh,String){

//	}
	/**
	 * 先进班级审核班级数限定
	 * @param xn
	 * @param xydm
	 * @return
	 */
	public boolean xjbjChBjsPd(String xn,String xydm,String pkVStr){
		return dao.xjbjBjsXd(xn, xydm,pkVStr);
	}
	/**获取班级平均分
	 * @author luo
	 */
	public String serv_getbjpjf(String xn,String bjdm ){
		return dao.getbjpjf(xn, bjdm);
	}
	/**获取班级不及格率
	 * @author luo
	 */
	public String serv_getbjbjdl(String xn,String bjdm ){
		return dao.getbjbjdl(xn, bjdm);
	}
	public boolean serv_rychCkRsXd(String rychdm,String xn,String xydm,String shfs,String zydm,String bjdm,String pkVStr) throws Exception{
		return dao.rychCkRsXd(rychdm, xn, xydm, shfs, zydm, bjdm, pkVStr);
	}
	/**
	 * 获取荣誉称号报表路径
	 */
	public HashMap<String,String> getRychBb(String rychdm){
		return dao.dao_getRychBb(rychdm);
	}
	/**
	 * 是否存在综合测评分
	 */
	public boolean dao_jxjiscftj(String rychdm){
		return dao.dao_getiszhcpf(rychdm);
	}
	/**
	 * @author luo
	 * @throws Exception 
	 * @describe 奖学金汇总报表打印
	 */
	public void jxjhzPrint(WritableWorkbook wwb, String xn,String zydm,String bjdm,String xnIn, String xyIn,
			String zyIn, String bjIn,String cpzdm,String xh,String xm,String jxjdm) throws Exception {

		String xxmc = StandardOperation.getXxmc();
		ZjlgPjpyModel myModel = new ZjlgPjpyModel();
		myModel.setXn(xn);
		myModel.setZydm(zydm);
		myModel.setBjdm(bjdm);
		String xsxn = (Base.isNull(xnIn))?"%":xnIn;
		String xy = (Base.isNull(xyIn))?"%":xyIn;
		String zy = (Base.isNull(zyIn))?"%":zyIn;
		String bj = (Base.isNull(bjIn))?"%":bjIn;
		String cpz = (Base.isNull(cpzdm)?"%":cpzdm);
//		xnIn = (Base.isNull(xnIn))?"'%'":xnIn;
//		xyIn = (Base.isNull(xyIn))?"'%'":xyIn;
//		zyIn = (Base.isNull(zyIn))?"'%'":zyIn;
//		bjIn = (Base.isNull(bjIn))?"'%'":bjIn;
		List<HashMap<String, String>> list = dao.getBbdyList(xsxn,xy,zy,bj,cpz,xh,xm,jxjdm);
		//boolean bool = dao.createZXFdytjb(xnIn, xyIn, zyIn, bjIn);
		String systime = getSysTime();
		try {
			WritableSheet ws = wwb.getSheet(0);
			WritableCellFormat wcfTytle = new WritableCellFormat();
			Alignment alignMent = Alignment.CENTRE;
			VerticalAlignment vag = VerticalAlignment.CENTRE;

			wcfTytle.setVerticalAlignment(vag);
			wcfTytle.setAlignment(alignMent);
			wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);

			WritableFont wfTytle = new WritableFont(WritableFont.ARIAL);
			wfTytle.setBoldStyle(WritableFont.BOLD);
			wfTytle.setPointSize(14);
			wcfTytle.setFont(wfTytle);

			ws.addCell(new Label(0, 0, xxmc  + xn + "学年综合测评汇总表",
					wcfTytle));

			wcfTytle = new WritableCellFormat();
			alignMent = Alignment.RIGHT;
			vag = VerticalAlignment.CENTRE;

			wcfTytle.setVerticalAlignment(vag);
			wcfTytle.setAlignment(alignMent);
			wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);

			wfTytle = new WritableFont(WritableFont.ARIAL);
			wfTytle.setBoldStyle(WritableFont.BOLD);
			wfTytle.setPointSize(12);
			wcfTytle.setFont(wfTytle);

			wcfTytle = new WritableCellFormat();
			alignMent = Alignment.RIGHT;
			vag = VerticalAlignment.CENTRE;

			wcfTytle.setVerticalAlignment(vag);
			wcfTytle.setAlignment(alignMent);
			wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);

			wfTytle = new WritableFont(WritableFont.ARIAL);
			wfTytle.setPointSize(10);
			wcfTytle.setFont(wfTytle);
			
			ws.addCell(new Label(0, 2, systime,
					wcfTytle));

			
			wcfTytle = new WritableCellFormat();
			alignMent = Alignment.LEFT;
			vag = VerticalAlignment.CENTRE;

			wcfTytle.setVerticalAlignment(vag);
			wcfTytle.setAlignment(alignMent);
			wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);

			wfTytle = new WritableFont(WritableFont.ARIAL);
			wfTytle.setBoldStyle(WritableFont.BOLD);
			wfTytle.setPointSize(12);
			wcfTytle.setFont(wfTytle);

			wcfTytle = new WritableCellFormat();
			alignMent = Alignment.CENTRE;
			vag = VerticalAlignment.CENTRE;

			wcfTytle.setVerticalAlignment(vag);
			wcfTytle.setAlignment(alignMent);
			wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);

			wfTytle = new WritableFont(WritableFont.ARIAL);
			wfTytle.setPointSize(10);
			wcfTytle.setFont(wfTytle);

			int rownum = 0;

			for (int i = 0; i < list.size(); i++) {

				HashMap<String, String> map = new HashMap<String, String>();
				map = (HashMap<String, String>) list.get(i);

				ws.addCell(new Label(0, 5 + rownum, map.get("pm"), wcfTytle));
				ws.addCell(new Label(1, 5 + rownum, map.get("xh"), wcfTytle));
				ws.addCell(new Label(2, 5 + rownum, map.get("bjmc"), wcfTytle));
				ws.addCell(new Label(3, 5 + rownum, map.get("xm"), wcfTytle));
				ws.addCell(new Label(4, 5 + rownum, map.get("dycpf"), wcfTytle));
				ws.addCell(new Label(5, 5 + rownum, map.get("dypm"), wcfTytle));
				ws.addCell(new Label(6, 5 + rownum, map.get("zycpf"), wcfTytle));
				ws.addCell(new Label(7, 5 + rownum, map.get("zypm"), wcfTytle));
				ws.addCell(new Label(8, 5 + rownum, map.get("bjg"), wcfTytle));
				ws.addCell(new Label(9, 5 + rownum, map.get("zhcpf"), wcfTytle));
				ws.addCell(new Label(10, 5 + rownum, map.get("jxjmc"), wcfTytle));
				ws.addCell(new Label(11, 5 + rownum, map.get("jlje"), wcfTytle));
				ws.addCell(new Label(12, 5 + rownum, map.get("rych"), wcfTytle));
				ws.addCell(new Label(13, 5 + rownum, map.get("tycj"), wcfTytle));
				ws.addCell(new Label(14, 5 + rownum, map.get("yycj"), wcfTytle));
				ws.addCell(new Label(15, 5 + rownum, map.get("jsjcj"), wcfTytle));
				ws.addCell(new Label(16, 5 + rownum, map.get("wjcf"), wcfTytle));
				ws.addCell(new Label(17, 5 + rownum, map.get(""), wcfTytle));
				
				rownum++;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		// 向客户端输出
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	/**
	 * 获取某生所若奖学金列表
	 * @param xh
	 * @return
	 */
	public List<HashMap<String,String>> serv_getRhJxjList(String xh){
		return dao.getRhJxjList(xh);
	} 
	/**
	 * 获取荣誉称号已通过人数
	 */
	public String serv_getRychPssRs(String rychdm,String xn,String xydm,String zydm,String bjdm){		
		return dao.getRychPssRs(rychdm,xn,xydm,zydm,bjdm);
	}
	
	/**
	 * 评奖人数超额验证(批量设置)
	 * @param myModel 
	 */
	public String getJxjRstxPlsz(ZjlgPjpyModel myModel){
		String xydm = myModel.getXydm();
		String cpzdm = myModel.getCpzdm();
		String bjdm = myModel.getBjdm();
		String jxjdm = myModel.getJxjdm();
		String xn =  Base.getJxjsqxn();
		if(bjdm!=null&&!bjdm.equalsIgnoreCase("")){
			return dao.getJxjRstxForXydm(xydm, xn, jxjdm, "bjdm");
		}else if(cpzdm!=null&&!cpzdm.equalsIgnoreCase("")){
			return dao.getJxjRstxForXydm(xydm, xn, jxjdm, "cpzdm");
		}else{
			return null;
		}
	}
	
	/**
	 * 评奖人数超额验证(单个保存)
	 * @param myModel 
	 */
	public String getJxjRstxOne(ZjlgPjpyModel myModel){		
		String xydm = myModel.getXydm();
		String bmlb = myModel.getBmlb();
		String jxjdm = myModel.getJxjdm();
		String xn =  Base.getJxjsqxn();
		if(!bmlb.equalsIgnoreCase("xydm")){
			return dao.getJxjRstxForXydm(xydm, xn, jxjdm, bmlb);
		}else{
			return null;
		}
	}

	
	public List<HashMap<String, String>> getPjfsList() {
		return dao.getPjfsList();
	}

	public boolean updatePjfs(ZjlgPjpyModel myModel) throws Exception {
		return dao.updatePjfs(myModel);
	}
	 /**
     * 通过学号、学年取相关体育成绩
     * @param 
     * @return
     */
	public String serv_getTyCj(String xh,String xn){
		return dao.dao_getTyCj(xh, xn);
	}
	public String isExistDj(List<HashMap<String,String>> listMap,String str){
		  for (HashMap<String, String> hashMap : listMap) {
			       if(hashMap.get("djksmc").equals(str)){
			    	   return hashMap.get("cj");
			       }
		   }
		  return null;
	}
	/*
	 * 根据学号获取 计算机成绩和英语等级成绩
	 */
	public HashMap<String,String> getStuDjForXh(String xn,String xh) throws SQLException{
		HashMap<String,String> map = new HashMap<String,String>();		
		List<HashMap<String,String>> JsjYyDjCj = dao.getJsjYyDjList(xn,xh);
		if(isExistDj(JsjYyDjCj, "CET6")!=null){
			  map.put("yydjcj6", ("六级"+isExistDj(JsjYyDjCj, "CET6")));
		}
		else if(isExistDj(JsjYyDjCj, "CET4")!=null){
			  map.put("yydjcj4", ("六级"+isExistDj(JsjYyDjCj, "CET6")));
		}
		if(isExistDj(JsjYyDjCj, "四级")!=null){
			map.put("jsj4", ("四级"+isExistDj(JsjYyDjCj, "四级")));
		}
		else if(isExistDj(JsjYyDjCj, "三级")!=null){
			map.put("jsj3", ("三级"+isExistDj(JsjYyDjCj, "三级")));
		}
		else if(isExistDj(JsjYyDjCj, "二级")!=null){
			map.put("jsj2", ("二级"+isExistDj(JsjYyDjCj, "二级")));
		}
		else if(isExistDj(JsjYyDjCj, "一级")!=null){
			map.put("jsj1", ("一级"+isExistDj(JsjYyDjCj, "一级")));
		}
		if(map.get("yydjcj6")!=null){
			map.put("yydjcj", map.get("yydjcj6"));
		}else{
			map.put("yydjcj", map.get("yydjcj4"));
		}
		if(map.get("jsj4")!=null){
			map.put("jsjdjcj", map.get("jsj4"));
		}
		else if(map.get("jsj3")!=null){
			map.put("jsjdjcj", map.get("jsj3"));
		}
		else if(map.get("jsj2")!=null){
			map.put("jsjdjcj", map.get("jsj2"));
		}
		else if(map.get("jsj1")!=null){
			map.put("jsjdjcj", map.get("jsj1"));
		}
		return map;
	}
	/**浙江理工德育取zjlg_zpf
     * 通过学号、学年取相关德育成绩
     * @param 
     * @return
     */
	public String serv_getZjlgDyCj(String xh,String xn){
		return dao.dao_getZjlgDyCj(xh, xn);
	}
	
	/**
     * 获取学院、参评组申请奖学金人数
     * @param 
     * @return
     */
	public HashMap<String,String> getJxjSqQk(String bmlb,String xydm,String bmdm,String jxjdm,String xn){
		List<HashMap<String,String>>jxjSqqkList=dao.getJxjSqQk(bmlb, xydm, bmdm, jxjdm, xn);
		
		HashMap<String,String>sqrsMap=new HashMap<String,String>();
		for(int i=0;i<jxjSqqkList.size();i++){
			HashMap<String,String>jxjSqMap=jxjSqqkList.get(i);
			if(i==0){
				sqrsMap.put("xyjxjrs", jxjSqMap.get("jxjrs"));
				sqrsMap.put("xysqrs", jxjSqMap.get("sqrs"));
				sqrsMap.put("xyksqrs", jxjSqMap.get("ksqrs"));
			}else{
				sqrsMap.put("cpzjxjrs", jxjSqMap.get("jxjrs"));
				sqrsMap.put("cpzsqrs", jxjSqMap.get("sqrs"));
				sqrsMap.put("cpzksqrs", jxjSqMap.get("ksqrs"));
			}
		}
		return sqrsMap;
	}
}
