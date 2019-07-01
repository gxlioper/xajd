package xgxt.pjpy.zjlg.dycj;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.utils.StringUtil;

import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import xgxt.DAO.DAO;
import xgxt.DAO.ExcelMB;
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.utils.ExcelMethods;
import xgxt.utils.UserTypePd;
import xgxt.utils.date.DateUtils;


public class DycjService {

	DycjDAO dao = new DycjDAO();
	
	/**
	 * 平时分查询结果
	 * @param model
	 * @param whereSql
	 * @return
	 * @throws Exception
	 */
	public List<String[]> queryPsfResult(DycjModel model,
			String isFdy, String userName) throws Exception {
		String fdySql = getFdySql(isFdy, userName);
		return dao.queryPsfResult(model, fdySql);
	}
	
	/**
	 * 卫生分查询结果
	 * @param model
	 * @param isFdy
	 * @param userName
	 * @return
	 * @throws Exception
	 */
	public List<String[]> queryWsfResult(DycjModel model,
			String isFdy, String userName) throws Exception {
		String fdySql = getFdySql(isFdy, userName);
		return dao.queryWsfResult(model, fdySql);
	}
	
	/**
	 * 考勤分查询结果
	 * @param model
	 * @param isFdy
	 * @param userName
	 * @return
	 * @throws Exception
	 */
	public List<String[]> queryKqfResult(DycjModel model,
			String isFdy, String userName) throws Exception {
		String fdySql = getFdySql(isFdy, userName);
		return dao.queryKqfResult(model, fdySql);
	}
	
	/**
	 * 德育总分查询结果
	 * @param model
	 * @param isFdy
	 * @param userName
	 * @return
	 * @throws Exception
	 */
	public List<String[]> queryDyzfResult(DycjModel model,
			String isFdy, String userName) throws Exception {
		String fdySql = getFdySql(isFdy, userName);
		return dao.queryDyfResult(model, fdySql);
	}
	
	/**
	 * 德育总分查询结果
	 * @param model
	 * @param isFdy
	 * @param userName
	 * @return
	 * @throws Exception
	 */
	public List<String[]> queryZjlgDyzfResult(DycjModel model,
			String isFdy, String userName) throws Exception {
		String fdySql = getFdySql(isFdy, userName);
		return dao.queryZjlgDyfResult(model, fdySql);
	}

	private String getFdySql(String isFdy, String userName) {
		String fdySql = UserTypePd.isFdy(isFdy) ? " and exists(select 1 from fdybjb c "
				+ "where a.bjdm=c.bjdm and c.zgh='"
				+ userName
				+ "')"
				: "" ;
		return fdySql;
	}
	
	/**
	 * 平时分查询表头
	 * @return
	 */
	public List<HashMap<String, String>> queryPsfTitle() {
		String[] en = { "pk","dis",
				"r", "xh", "xm", "bjmc", "xn", "zwpyf", "bjpyf", "xyfjf",
				"xysh"};
		String[] cn = { "pk","dis",
				"行号", "学号", "姓名", "班级", "学年", "自我评议分", "班级评议分", "学院附加分",
				"学院审核"};
		DAO dao = DAO.getInstance();
		return dao.arrayToList(en, cn);
	}
	
	/**
	 * 卫生分查询表头
	 * @return
	 */
	public List<HashMap<String, String>> queryWsfTitle() {
		String[] en = {  "pk","dis",
				"r", "xh", "xm", "bjmc", "xn", "qsf", "iszds", "xyfjf",
				"xysh" };
		String[] cn = { "pk","dis",
				"行号", "学号", "姓名", "班级", "学年", "寝室平时分", "是否走读生", "学院附加分",
				"学院审核"};
		DAO dao = DAO.getInstance();
		return dao.arrayToList(en, cn);
	}
	
	/**
	 * 考勤分查询表头
	 * @return
	 */
	public List<HashMap<String, String>> queryKqfTitle() {
		String[] en = {  "pk",
				"r", "xh", "xm", "bjmc", "xn", "kqf"};
		String[] cn = { "pk",
				"行号", "学号", "姓名", "班级", "学年", "考勤分"};
		DAO dao = DAO.getInstance();
		return dao.arrayToList(en, cn);
	}
	
	/**
	 * 德育总分查询表头
	 * @return
	 */
	public List<HashMap<String, String>> queryDyzfTitle() {
		String[] en = {   "pk",
				"r", "xh", "xm", "bjmc", "xn", "psfzf", "wsfzf", "iszds", "kqzpf", "zf"};
		String[] cn = {  "pk",
				"行号", "学号", "姓名", "班级", "学年", "平时分总分", "卫生分总分", "是否走读生", "考勤分总分", "德育总分"};
		DAO dao = DAO.getInstance();
		return dao.arrayToList(en, cn);
	}
	/**
	 * 浙江理工德育总分查询表头
	 * @return
	 */
	public List<HashMap<String, String>> queryZjlgDyzfTitle() {
		String[] en = {   "pk",
				"r", "xh", "xm", "bjmc", "xn","psfzf", "wsfzf", "iszds", "kqzpf","zpf"};
		String[] cn = {  "pk",
				"行号", "学号", "姓名", "班级", "学年", "平时分总分", "卫生分总分", "是否走读生", "考勤分总分", "德育总分"};
		DAO dao = DAO.getInstance();
		return dao.arrayToList(en, cn);
	}
	/**
	 * 保存平时分比例设置信息
	 * @param userName
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean savePsfblxx(String userName, DycjModel model)
			throws Exception {
		return dao.savePsfblxx(userName, model);
	}
	
	/**
	 * 计算综合素质测评总分
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean countZhszcpf(DycjModel model) throws Exception {
		if(StringUtil.isNull(model.getQueryequals_nj())){
			model.setQueryequals_nj("all");
		}
		if(StringUtil.isNull(model.getQueryequals_xydm())){
			model.setQueryequals_xydm("all");
		}
		if(StringUtil.isNull(model.getQueryequals_zydm())){
			model.setQueryequals_zydm("all");
		}
		if(StringUtil.isNull(model.getQueryequals_bjdm())){
			model.setQueryequals_bjdm("all");
		}
		
		return dao.countZhszcpf(model);
	}
	
	/**
	 * 导出综测排名信息
	 * @param wwb
	 * @param model
	 * @param isFdy
	 * @param userName
	 * @throws Exception
	 */
	public void exprotZhszcpinfo(WritableWorkbook wwb, DycjModel model,
			String isFdy, String userName) throws Exception {
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

		ws.addCell(new Label(0, 0, Base.xxmc  + model.getXn() + "学年综合测评汇总表",
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

		ws.addCell(new Label(0, 2, DateUtils.getLyr(),
				wcfTytle));
		
		/* 查询排名结果信息 */
		List<String[]> rs = queryZhszcppmxx(model, isFdy, userName);
		
		/* 输出查询结果信息 */
		if (!rs.isEmpty()) {
			WritableCellFormat format = ExcelMB.getWritableCellFormat(11,
					false, Alignment.CENTRE, VerticalAlignment.CENTRE,
					Border.ALL);
			ExcelMB.writeDataToCellByIterator(ws, rs, 0, 5, format);
		}
		
		//向客户端输出
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	/**
	 * 查询综合排名信息
	 * @param model
	 * @param whereSql
	 * @return
	 * @throws Exception
	 */
	public List<String[]> queryZhszcppmxx(DycjModel model, String isFdy, String userName) throws Exception {
		String fdySql = getFdySql(isFdy, userName);
		return dao.queryZhszcppmxx(model, fdySql);
	}
}
