/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description:安徽建筑工业学院公寓管理Servieces </p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: LP</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-7-1 下午01:20:32</p>
 */
package xgxt.xsgygl.zjlgdx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.utils.GetTime;

public class GyglZjlgService {
	
	/**
	 * 获得最近一次申请A级寝室情况
	 */
	public HashMap<String,String> getAjqssqqk_ser(String xh){
		GyglZjlgDAO dao = new GyglZjlgDAO();
		return dao.getAjqssqqk_db(xh);
	}
	/**
	 * 保存A级寝室
	 */
	public boolean saveAjqs_ser(GyglZjlgModel model){
		GyglZjlgDAO dao = new GyglZjlgDAO();
		try {
			return dao.saveAjqs_db(model);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	/**
	 * 查询当前是否A级寝室
	 * @throws Exception 
	 */
	public boolean isAjqs_ser(String xh) {
		GyglZjlgDAO dao = new GyglZjlgDAO();
		return dao.isAjqs_db(xh);
	}
	
	/**
	 * 查询是否符合必须有过A级寝室的条件
	 * @throws Exception 
	 */
	public boolean isAjqsCondition_ser(String xh,String lb) {
		GyglZjlgDAO dao = new GyglZjlgDAO();
		return dao.isAjqsCondition_db(xh,lb);
	}
	//	组合表头
	public List<HashMap<String,String>> getTopName(String lb){
		String[] colEn = null;
		String[] colCn = null;
		if("ajqssh".equals(lb)){
			colEn = new String[] { "pk", "xn", "xq","ssbh","ldmc","lc","qsh","lxdh","sqsj","xxsh","shsj","sfcx","cxsj" };
			colCn = new String[] { "pk", "学年", "学期",
						"宿舍编号", "楼栋名称", "楼层","寝室号","联系电话","申请时间", "学校审核","审核时间", "是否撤消","撤消时间" };
		}
		if("wmqssh".equals(lb)){
			colEn = new String[] { "pk", "xn", "xq","ssbh","ldmc","lc","qsh","lxdh","sqsj","xysh","xxsh" };
			colCn = new String[] { "pk", "学年", "学期",
						"宿舍编号", "楼栋名称", "楼层","寝室号","联系电话","申请时间", "学院审核", "学校审核" };
		}
		if("tsqssh".equals(lb)){
			colEn = new String[] { "pk", "xn","ssbh","ldmc","lc","qsh","lxdh","sqsj","xysh","xxsh" };
			colCn = new String[] { "pk", "学年", 
						"宿舍编号", "楼栋名称", "楼层","寝室号","联系电话","申请时间", "学院审核", "学校审核" };
		}
		
		return getTabName_ser(colEn, colCn);
	}
	//组合表头
	public List<HashMap<String, String>> getTabName_ser(String[] en,String[] cn){
		DAO comdao = DAO.getInstance();
		return comdao.arrayToList(en, cn);
	}
	
	/**
	 * 审核A级寝室查询
	 */
	public List<HashMap<String,String>> queryAjqsSh_ser(GyglZjlgForm form,GyglZjlgModel model){
		GyglZjlgDAO dao = new GyglZjlgDAO();
		try {
			return dao.queryAjqsSh_db(form, model);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 审核A级寝室及维护
	 */
	public boolean operation_ser(GyglZjlgModel model,String doType,String pks){
		GyglZjlgDAO dao = new GyglZjlgDAO();
		boolean flag = false;
		try {
			if("pass".equals(doType) || "nopass".equals(doType)){
				flag = dao.ajqssh_db(model, doType, pks);
			}else if("cx".equals(doType)|| "jccx".equals(doType)){
				flag = dao.ajqscx_db(pks,doType);
			}else if("autocx".equals(doType)){
				flag = dao.ajqscxAuto_db();
			}else if("dele".equals(doType)){
				flag = dao.ajqsdele_db(pks);
			}
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}
	
	/**
	 * 查看Ａ级寝室申请信息
	 */
	public HashMap<String,Object> viewSqxx_ser(String pkValue,String xn,String xq){
		GyglZjlgDAO dao = new GyglZjlgDAO();
		String ssbh = "";
		String sqsj = "";
		HashMap<String,Object> map = new HashMap<String,Object>();
		if(!Base.isNull(pkValue)){
			ssbh = pkValue.substring(0,pkValue.length()-8);
			sqsj = pkValue.substring(pkValue.length()-8);
		}
		map.putAll(dao.getQssqxx_db(pkValue)==null ? new HashMap<String,String>() 
				: dao.getQssqxx_db(pkValue));
		map.put("qscy", dao.getQscy_db(ssbh));
		map.put("qscj", dao.getTwoWeekWscj_db(ssbh, sqsj, xn, xq));
		return map;
	}
	
	/**
	 * A级寝室审核结果查询
	 */
	public List<HashMap<String,String>> queryAjqsShcx_ser(GyglZjlgForm form,GyglZjlgModel model){
		GyglZjlgDAO dao = new GyglZjlgDAO();
		try {
			return dao.queryAjqsShcx_db(form, model);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * A级寝室审核结果查询(学生)
	 */
	public List<HashMap<String,String>> xsQueryAjqsShcx_ser(String xh){
		GyglZjlgDAO dao = new GyglZjlgDAO();
		try {
			return dao.xsQueryAjqsShcx_db(xh);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 获得寝室所属学院
	 */
	public String getQsxymc_ser(String ssbh){
		GyglZjlgDAO dao = new GyglZjlgDAO();
		return dao.getQsxymc_db(ssbh);
	}
	
	/**
	 * 获得最近一次申请文明寝室情况
	 */
	public HashMap<String,String> getWmqssqqk_ser(String xh){
		GyglZjlgDAO dao = new GyglZjlgDAO();
		return dao.getWmqssqqk_db(xh);
	}
	
	/**
	 * 审核文明，特色寝室及维护
	 */
	public boolean WmAndTsOperation_ser(String tableName,String doType,String pks,String userType){
		GyglZjlgDAO dao = new GyglZjlgDAO();
		boolean flag = false;
		try {
			if("pass".equals(doType) || "nopass".equals(doType)){
				flag = dao.wmtsqssh_db(tableName, doType, pks,userType);
			}else if("dele".equals(doType)){
				flag = dao.wmtsqsdele_db(pks,userType,tableName);
			}
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}
	
	/**
	 * 保存文明寝室
	 */
	public boolean saveWmqs_ser(GyglZjlgModel model){
		GyglZjlgDAO dao = new GyglZjlgDAO();
		try {
			return dao.saveWmqs_db(model);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	/**
	 * 审核文明寝室查询
	 */
	public List<HashMap<String,String>> queryWmqsSh_ser(GyglZjlgForm form,GyglZjlgModel model,String doType,String xydm){
		GyglZjlgDAO dao = new GyglZjlgDAO();
		try {
			return dao.queryWmqsSh_db(form, model,doType,xydm);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 查看文明寝室申请信息
	 * @throws Exception 
	 */
	public HashMap<String,Object> viewWmqsSqxx_ser(String pkValue,String xn,String xq) throws Exception{
		GyglZjlgDAO dao = new GyglZjlgDAO();
		String ssbh = "";
		String sqsj = "";
		HashMap<String,Object> map = new HashMap<String,Object>();
		HashMap<String,String> temmap = new HashMap<String,String>();
		if(!Base.isNull(pkValue)){
			ssbh = pkValue.substring(0,pkValue.length()-8);
			sqsj = pkValue.substring(pkValue.length()-8);
		}
		map.putAll(dao.getWmqssqxx_db(pkValue)==null ? new HashMap<String,String>() 
				: dao.getWmqssqxx_db(pkValue));
		map.put("qscy", dao.getQscy_db(ssbh));
		temmap = dao.getSqwmqsCondition_db(ssbh, sqsj, xn, xq);
		map.put("qstj",temmap );
		List<String[]> cjlist = dao.getQscycj_db(ssbh);
		if(temmap.get("shsj") !=null && temmap.get("shsj").length()==8){
			map.put("zs",GetTime.getDqzs(temmap.get("shsj")));
		}	
		if(cjlist != null && cjlist.size()>0){
			map.put("cj", cjlist);
		}
		return map;
	}
	
	/**
	 * 文明寝室审核结果查询
	 */
	public List<HashMap<String,String>> queryWmqsShcx_ser(GyglZjlgForm form,GyglZjlgModel model,String userType,String xydm){
		GyglZjlgDAO dao = new GyglZjlgDAO();
		try {
			return dao.queryWmqsShcx_db(form, model,userType,xydm);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 文明寝室审核结果查询(学生)
	 */
	public List<HashMap<String,String>> xsQueryWmqsShcx_ser(String xh){
		GyglZjlgDAO dao = new GyglZjlgDAO();
		try {
			return dao.xsQueryWmqsShcx_db(xh);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 特色寝室审核结果查询(学生)
	 */
	public List<HashMap<String,String>> xsQueryTsqsShcx_ser(String xh){
		GyglZjlgDAO dao = new GyglZjlgDAO();
		try {
			return dao.xsQueryTsqsShcx_db(xh);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 获得最近一次申请特色寝室情况
	 */
	public HashMap<String,String> getTsqssqqk_ser(String xh){
		GyglZjlgDAO dao = new GyglZjlgDAO();
		return dao.getTsqssqqk_db(xh);
	}
	
	/**
	 * 保存特色寝室
	 */
	public boolean saveTsqs_ser(GyglZjlgModel model){
		GyglZjlgDAO dao = new GyglZjlgDAO();
		try {
			return dao.saveTsqs_db(model);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * 审核特色寝室查询
	 */
	public List<HashMap<String,String>> queryTsqsSh_ser(GyglZjlgForm form,GyglZjlgModel model,String doType,String xydm){
		GyglZjlgDAO dao = new GyglZjlgDAO();
		try {
			return dao.queryTsqsSh_db(form, model,doType,xydm);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 查看特色寝室申请信息
	 * @throws Exception 
	 */
	public HashMap<String,Object> viewTsqsSqxx_ser(String pkValue,String xn) throws Exception{
		GyglZjlgDAO dao = new GyglZjlgDAO();
		String ssbh = "";
		String sqsj = "";
		HashMap<String,Object> map = new HashMap<String,Object>();
		HashMap<String,String> temmap = new HashMap<String,String>();
		if(!Base.isNull(pkValue)){
			ssbh = pkValue.substring(0,pkValue.length()-8);
			sqsj = pkValue.substring(pkValue.length()-8);
		}
		map.putAll(dao.getTsqssqxx_db(pkValue)==null ? new HashMap<String,String>() 
				: dao.getTsqssqxx_db(pkValue));
		map.put("qscy", dao.getQscy_db(ssbh));
		temmap = dao.getSqtsqsCondition_db(ssbh, sqsj, xn);
		map.put("qstj",temmap );
		if(temmap.get("sqsj") !=null && temmap.get("sqsj").length()==8){
			map.put("zs",GetTime.getDqzs(temmap.get("sqsj")));
		}	
		return map;
	}
	
	/**
	 * 特色寝室审核结果查询
	 */
	public List<HashMap<String,String>> queryTsqsShcx_ser(GyglZjlgForm form,GyglZjlgModel model,String userType,String xydm){
		GyglZjlgDAO dao = new GyglZjlgDAO();
		try {
			return dao.queryTsqsShcx_db(form, model,userType,xydm);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 楼层长队伍查询
	 */
	public ArrayList<String[]>ser_dormCadreStat(GyglZjlgModel model){
		GyglZjlgDAO dao = new GyglZjlgDAO();
		return dao.dao_dormCadreStat(model);
	}
	/**
	 * 楼层长队伍查询表头
	 */
	public List<HashMap<String,String>> getDormCadreStat(){
		String[] colEn = null;
		String[] colCn = null;
		colEn = new String[] { "r","xqmc","ldmc","cs","qsh","gyfdy","lz","cz","qsz" };
		colCn = new String[] { "行号","校区", "楼栋名称", "楼层","寝室号","公寓辅导员","楼长", "层长","寝室长"};
		return getTabName_ser(colEn, colCn);
	}
	/**
	 * 楼层长队伍查询导出
	 */
	public void ser_expDormCadreStat(WritableWorkbook wwb,GyglZjlgModel model) throws Exception {
		GyglZjlgDAO dao = new GyglZjlgDAO();
		ArrayList<String[]> vec = new ArrayList<String[]>();
		WritableSheet ws = wwb.createSheet("数据导出", 0);
		String[] ColumnName =  new String[] { "r","xqmc","ldmc","cs","qsh","gyfdy","lz","cz","qsz" };
		String[] ColumnNameCN  = new String[] { "行号","校区", "楼栋名称", "楼层","寝室号","公寓辅导员","楼长", "层长","寝室长"};
		for (int m = 0; m < ColumnNameCN.length; m++) {
			ws.addCell(new Label(m, 0, ColumnNameCN[m]));
		}
		vec = dao.dao_dormCadreStat(model);
		int k = ColumnName.length;
		for (int i = 0; i < vec.size(); i++) {
			String[] tmp = (String[]) vec.toArray()[i];
			for (int j = 0; j < k; j++) {
				ws.addCell(new Label(j, i + 1, tmp[j]));
			}
		}
	}
	
	
	public String getQszBySsbh(String ssbh){
		GyglZjlgDAO dao = new GyglZjlgDAO();
		
		return dao.getQszBySsbh(ssbh); 
	}
}
