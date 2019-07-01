package xgxt.wjcf.zjcm;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;

import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import xgxt.DAO.ExcelMB;
import xgxt.action.Base;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.ExcelMethods;
import xgxt.utils.MakeQuery;
import xgxt.utils.String.StringUtils;
import xgxt.xszz.XszzTyForm;

public class WjcfZjcmService {

	WjcfZjcmDAO dao = new WjcfZjcmDAO();
	
	
	
	/**
	 * 增加留校察看时间
	 * @param sj
	 * @return
	 * @throws Exception
	 */
	public boolean addSj(String sj) throws Exception{
		return dao.deleteSj() ? dao.addSj(sj) : false;
	}
	
	/**
	 * 查询留校察看时间
	 * @return
	 */
	public String getSj() {
		return dao.getSj();
	}
	
	/**
	 * 查询留校察看待解除信息记录数
	 * @param sj
	 * @param userType
	 * @param userDep
	 * @param isFdy
	 * @param userName
	 * @return
	 */
	public String getLxckjcDjcxx(String userType, String userDep,
			String isFdy, String userName) {
		String count = dao.queryUserJclxqx(userName);
		//如果该用户没有赋这个功能菜单权限，那么就不用提示待解除的留校察看的信息
		if ("0".equalsIgnoreCase(count) || StringUtils.isNull(count)) {
			return "";
		}
		
		String sj = dao.getSj();
		String xydm = "xy".equalsIgnoreCase(userType)
				|| "true".equalsIgnoreCase(isFdy) ? userDep : "";
		String num = dao.getLxckjcDjcxx(sj, xydm, isFdy, userName); 
		return StringUtils.isNull(num) || "0".equalsIgnoreCase(num) ? "" : sj;
	}
	
	public HashMap<String, String> getCfbPrintxx(String cfpk) {
		return dao.getCfbPrintxx(cfpk);
	}
	
	public HashMap<String, String> queryCfbPrintxx(String cfpk) {
		return dao.queryCfbPrintxx(cfpk);
	}
	
	/**
	 * 各学院学生处分信息统计汇总
	 * @param form
	 * @param wwb
	 */
	public void exportCfhzb(WjcfZjcmModel model, WritableWorkbook wwb)
			throws Exception {
		List<String[]> data = dao.queryXycfhzxx(model);
		WritableSheet ws = wwb.getSheet(0);
		
		WritableCellFormat wcfStyle = new WritableCellFormat();
 	    WritableCellFormat style = new WritableCellFormat();
 	    WritableFont font = new WritableFont(WritableFont.ARIAL,18);
 	    
	    Alignment alignMent = Alignment.CENTRE;
	    wcfStyle.setAlignment(alignMent);
	    style.setAlignment(alignMent);
	    wcfStyle.setBorder(Border.ALL, BorderLineStyle.THIN);
	    style.setBorder(Border.ALL, BorderLineStyle.THIN);
	    
	    style.setFont(font);
		if (!data.isEmpty()) {
			String title = model.getXn() +
			" 学年第 " + model.getXq() + " 学期各"+Base.YXPZXY_KEY+"学生处分统计";
			ws.addCell(new Label(0, 0, title, style));
			//循环输出每行数据
			WritableCellFormat wf = ExcelMB.getWritableCellFormat(10, false,
					alignMent, VerticalAlignment.CENTRE, Border.ALL);
			ExcelMB.writeDataToCellByIterator(ws, data, 2, wf);
		}
		ExcelMethods.submitWritableWorkbookOperations(wwb);//输出到客户端
	}

	/**
	 * 查询解除留校察看打印报表信息
	 * @param pkValue
	 * @return
	 */
	public HashMap<String, String> queryJclxbbxx(String pkValue) {
		return dao.queryJclxbbxx(pkValue);
	}
	
	public List<String[]> queryStuCfxx(String xh) {
		return dao.queryStuCfxx(xh);
	}
	
	public HashMap<String, String> queryStuLxclxx(String pkValue) {
		return dao.queryStuLxclxx(pkValue);
	}
	
	/**
	 * 获得历年违纪情况
	 * 
	 * @author luojw
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getLnwjList(WjcfZjcmActionForm model)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		String tableName = "view_wjcf";

		String[] colList = new String[] { "xn", "nd", "cflbmc", "cfyymc" };
		String[] queryList = new String[] {"xh"};
		String[] queryLikeList = new String[] {};

		MakeQuery myQuery = new MakeQuery();
		myQuery.makeQuery(queryList, queryLikeList, model);

		// 查询条件
		StringBuilder query = new StringBuilder();
		query.append(myQuery.getQueryString());
		query.append(" and ((shbm = '校审' and xxsh = '通过') ");
		query.append(" or (shbm = '院审' and xysh = '通过')) ");
		query.append(" order by xn,nd");

		// 项目条件
		List<HashMap<String, String>> xmTjList = CommonQueryDAO
				.commonQueryforList(tableName, query.toString(), myQuery
						.getInputList(), colList, "");

		return xmTjList;
	}
	
	/**
	 * 查询学生处分信息 - 成都体育
	 * @param cfpk
	 * @return
	 */
	public HashMap<String, String> queryXsCfxx(String cfpk) {
		return dao.queryXsCfxx(cfpk);
	}
	
	/**
	 * 成都体育违纪信息(xh||xn||sbsj)
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> printwjInfo(String pkValue) throws Exception {
		return dao.printwjInfo(pkValue);
	}
}
