package xgxt.xsgygl.bjlh.sjwh;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.form.SaveForm;
import xgxt.xsgygl.bjlh.BjlhGyglDAO;
import xgxt.xsgygl.bjlh.BjlhGyglForm;
import xgxt.xsgygl.bjlh.fyk.FykService;

public class SjwhService {
	
	//	=======================万恶的分割线（以下luojw）===================================
	
	SjwhDAO dao = new SjwhDAO();

	public static final String TWDM = BjlhGyglDAO.TWDM;//团委部门代码
	public static final String TYDM = BjlhGyglDAO.TYDM;;//体育教学部部门代码
	public static final String KYDM = BjlhGyglDAO.KYDM;//科研部门代码
	public static final String CJDM = BjlhGyglDAO.CJDM;//成教部门代码
	
	/**
	 * 执行存储过程，更新床位信息
	 * 
	 * @author luojw
	 */
	public boolean createCwxx() {
		return dao.createCwxx();
	}
	
	/**
	 * 批量保存公寓管理相关信息
	 * 
	 * @author luojw
	 * 
	 * @param tableName(表名)
	 * @param pk(主键)
	 * @param pkValue(主键值)
	 * @param arrzd(批量字段)
	 * @param onezd(单一字段)
	 * @param notnull(非空字段)
	 * 
	 * @throws Exception
	 */
	public boolean saveGygl(SaveForm form, SjwhModel model) throws Exception {
		DAO dao = DAO.getInstance();
		return dao.saveData(form, model);
	}

	/**
	 * 保存公寓管理相关信息
	 * 
	 * @author luojw
	 * @param tableName(表名)
	 * @param pk(主键)
	 * @param pkValue(主键值)
	 * @param onezd(单一字段)
	 * 
	 * @throws Exception
	 */
	public boolean saveGygl(SaveForm form, SjwhModel model,
			HttpServletRequest request) throws Exception {
		DAO dao = DAO.getInstance();
		return dao.submitData(form, model, request);
	}

	/**
	 * 更新公寓管理相关信息
	 * 
	 * @author luojw
	 * @param tableName(表名)
	 * @param pk(主键)
	 * @param pkValue(主键值)
	 * @param onezd(单一字段)
	 * 
	 * @throws Exception
	 */
	public boolean updateGygl(SaveForm form, SjwhModel model) throws Exception {
		DAO dao = DAO.getInstance();
		return dao.updateData(form, model);
	}

	/**
	 * 删除公寓管理相关信息
	 * 
	 * @author luojw
	 * @param tableName(表名)
	 * @param pk(主键)
	 * @param pkValue(主键值)
	 * 
	 * @throws Exception
	 */
	public boolean delGygl(SaveForm form, SjwhModel model) throws Exception {
		DAO dao = DAO.getInstance();
		return dao.delDate(form, model);
	}

	/**
	 * 获得公寓管理相关列表
	 * 
	 * @author luojw
	 */
	public ArrayList<String[]> getGyglList(String tableName, SjwhModel model,
			String[] colList) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		
		String query = "";
		if ("view_bjlh_fpjg".equalsIgnoreCase(tableName)) {
			String lx = model.getLx();

			if ("团委生".equalsIgnoreCase(lx)) {
				model.setXydm(FykService.TWDM);
			} else if ("体优生".equalsIgnoreCase(lx)) {
				model.setXydm(FykService.TYDM);
			} else if ("研究生".equalsIgnoreCase(lx)) {
				model.setXydm(FykService.KYDM);
			} else if ("成教生".equalsIgnoreCase(lx)) {
				model.setXydm(FykService.CJDM);
			} else {
				query = " and xydm not in ('" + TWDM + "','" + TYDM + "','"
						+ KYDM + "','" + CJDM + "')";
			}
			model.setLx("");
		}
		return dao.getGyglList(tableName, model, colList,query);
	}

	/**
	 * 获得无需维护下拉框值
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getSelectList(String lx) {
		return dao.getSelectList(lx);
	}

	/**
	 * 获得需维护下拉框值
	 * 
	 * @param tableName(表名)
	 * @param dm(代码)
	 * @param mc(名称)
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getSelectList(String tableName,
			String dm, String mc) {
		return dao.getSelectList(tableName, dm, mc);
	}

	/**
	 * 得到部门代码
	 * 
	 * @author luojw
	 */
	public String getBmdm(String bmmc) {
		return dao.getBmdm(bmmc);
	}

	/**
	 * 获得非全日制学院专业班级列表
	 * 
	 * @param bmlx(部门类型:xy,zy,bj)
	 * @param xslx(学生类型:研究生，成教生)
	 * @param nj(年级)
	 * @param xydm(学院代码)
	 * @param zydm(专业代码)
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getXyZyBjList(String bmlx,
			String xslx, String nj, String xydm, String zydm) {
		return dao.getXyZyBjList(bmlx, xslx, nj, xydm, zydm);
	}
	
	/**
	 * 获得非全日制年级列表
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getNjList(String lx) {
		return dao.getNjList(lx);
	}

	/**
	 * 获得公寓管理相关信息
	 * 
	 * @param tableName(表名)
	 * @param pk(主键)
	 * @param pkValue(主键值)
	 * @param colList(输出值)
	 * 
	 * @author luojw
	 */
	public HashMap<String, String> getGyglInfo(String tableName, String pk,
			String pkValue, String[] colList) {
		return dao.getGyglInfo(tableName, pk, pkValue, colList);
	}
	
	/**
	 * 获得楼栋层数列表
	 * @param lddm(楼栋代码)
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getCsList(String lddm) {
		return dao.getCsList(lddm);
	}
	
	/**
	 * 获得楼栋寝室列表
	 * 
	 * @param lddm(楼栋代码)
	 * @param cs(层数)
	 * @author luojw
	 */
	public List<HashMap<String, String>> getQsList(String lddm, String cs,
			String fplx, String xydm) {
		return dao.getQsList(lddm, cs, fplx, xydm, "");
	}
	/**
	 * 获得寝室床位列表
	 * @param lddm(楼栋代码)
	 * @param cs(层数)
	 * @param qsh(寝室号)
	 * 
	 * @author luojw
	 * @throws Exception 
	 */
	public List<HashMap<String, String>> getCwList(String lddm, String cs,
			String qsh, String xh) throws Exception {
		return dao.getCwList(lddm, cs, qsh, xh);
	}
	
	/**
	 * 获得学生住宿信息ID
	 * 
	 * @author luojw
	 */
	public String getId(String xh, String lx){
		return dao.getId(xh, lx);
	}

	/**
	 * 修改退房信息
	 * 
	 * @author luojw
	 * @throws Exception 
	 */
	public Boolean updateTfxx(SjwhModel model) throws Exception{
		return dao.updateTfxx(model);
	}
	
	/**
	 * 执行整体退房
	 * 
	 * @author luojw
	 * @throws Exception 
	 */
	public Boolean excuteZttf(SjwhModel model) throws Exception{
		return dao.excuteZttf(model);
	}
	
	/**
	 * 设置所需列表
	 * 
	 * @param form
	 * @param request
	 * @author luojw
	 * @throws Exception 
	 */
	public  void setList(BjlhGyglForm myForm,HttpServletRequest request) throws Exception {
		dao.setList(myForm, request);
	}
	
	//	=======================万恶的分割线（以上luojw）===================================
	
	/**
	 * 分配标记列表
	 * 
	 * @return
	 */
	public List<HashMap<String, String>> getFpbjList() {
		String[] en = { "qrz", TWDM, TYDM, KYDM, CJDM };
		String[] cn = { "全日制", "团委", "体育教学部", "科研处", "成人教育处" };
		return dao.getList(en, cn);
	}
	
	/**
	 * 统计类型列表
	 * @return
	 */
	public List<HashMap<String, String>> getTjlxList() {
		String[] en = {"", "满的", "空的", "闲的", "保留的"};
		String[] cn = {"----请选择----", "满的",  "空的", "闲的", "保留的"};
		return dao.getList(en, cn);
	}
}
