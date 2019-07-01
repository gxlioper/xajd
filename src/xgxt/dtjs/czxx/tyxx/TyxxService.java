package xgxt.dtjs.czxx.tyxx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.util.MessageResources;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommDAO;
import xgxt.dtjs.czxx.CzxxDtjsForm;
import xgxt.form.RequestForm;
import xgxt.form.SaveForm;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.MakeQuery;
import xgxt.xszz.zgmsxy.XszzZgmsxyActionForm;


public class TyxxService {

	MessageResources message = MessageResources
					.getMessageResources("config.ApplicationResources");
			
	TyxxDAO dao = new TyxxDAO();
	
	/**
	 * 团员信息查询
	 * @param String tableName
	 * @param TyxxModel model
	 * @param String[] colList
	 * @return ArrayList<String[]>
	 * @throws Exception
	 * */
	public ArrayList<String[]> queryTyxxList(String tableName,TyxxModel model,String[] colList) throws Exception{
		return dao.selectTyxxList(tableName, model, colList);
	}
	
	/**
	 * 获取单个团员信息
	 * @param pkValue
	 * @return
	 */
	public Map<String, String> getTyxxOne(String pkValue){
		String[] colList = new String[]{"xh", "xm", "xb", "nj", "xymc", "zymc", "bjmc", "rtrq",
				"rtdd", "bz"};
		return CommonQueryDAO.commonQueryOne("view_czxx_tyxx", colList, "pk", pkValue);
	}
	
	/**
	 * 团员信息查询导出
	 * @param String tableName
	 * @param TyxxModel model
	 * @param String[] colList
	 * @return ArrayList<String[]>
	 * @throws Exception
	 * */
	public ArrayList<String[]> queryTyxxForExport(String tableName,TyxxModel model,String[] colList) throws Exception{
		return dao.selectTyxxForExp(tableName, model, colList);
	}
	
	/**
	 * 保存团员信息
	 * @param SaveForm saveForm
	 * @param TyxxbcModel tyxxbc
	 * @throws Exception 
	 * */
	public boolean saveTyxx(SaveForm saveForm,TyxxbcModel tyxxbc) throws Exception{
		DAO dao = DAO.getInstance();
		return dao.saveData(saveForm, tyxxbc);
	}
	
	/**
	 * 根据表的列名获取相应的注释
	 * @param String[] colList
	 * @param String tableName
	 * @return String[]
	 * */
	public String[] getColumnNameCN(String[] colList, String tableName){
		DAO dao=DAO.getInstance();
		return dao.getColumnNameCN(colList, tableName);
	}
	
	/**
	 * 非团员培训信息查询
	 * @param String tableName
	 * @param TyxxModel model
	 * @param String[] colList
	 * @return ArrayList<String[]>
	 * @throws Exception
	 * */
	public ArrayList<String[]> queryFtypxxxList(String tableName,CzxxDtjsForm model,String[] colList) throws Exception{
		return dao.selectFtypxxxList(tableName, model, colList);
	}
	
	/**
	 * 根据主键查询非团员培训信息 
	 * @param tableName(表名)
	 * @param pk(主键)
	 * @param pkValue(主键值)
	 * @param colList(输出值)
	 * @return HashMap<String, String>
	 */
	public HashMap<String, String> queryFtypxInfo(String tableName, String pk,
			String pkValue, String[] colList) {
		return dao.selectFtypxInfo(tableName, pk, pkValue, colList);
	}
	
	
	
	/**
	 * 保存非团员培训信息相关信息
	 * @param tableName(表名)
	 * @param pk(主键)
	 * @param pkValue(主键值)
	 * @param onezd(单一字段)
	 * @return boolean
	 * @throws Exception
	 */
	public boolean saveFtypxxx(SaveForm form, CzxxDtjsForm model,
			HttpServletRequest request) throws Exception {
		DAO dao = DAO.getInstance();
		return dao.submitData(form, model, request);
	}	
	
	/**
	 * 查询非团员培训项目代码表信息
	 * @return List<HashMap<String, String>>
	 * */
	public List<HashMap<String, String>> queryFtypxxmList(){
		return dao.selectFtypxxmdmb();
	}
	
	/**
	 * 查询团员培训项目代码表信息
	 * @return List<HashMap<String, String>>
	 * */
	public List<HashMap<String, String>> queryTypxxmList(){
		return dao.selectTypxxmdmb();
	}
	
	
	/**
	 * 删除非团员培训信息
	 * @param SaveForm form
	 * @param CzxxDtjsForm model
	 * @throws Exception
	 */
	public boolean delFtypxxx(SaveForm form, CzxxDtjsForm model) throws Exception {
		DAO dao = DAO.getInstance();
		return dao.delDate(form, model);
	}
	
	/**
	 *优秀团员信息审核
	 * @param SaveForm form
	 * @param CzxxDtjsForm model
	 * @throws Exception
	 */
	public boolean auditingTnty(SaveForm form, CzxxDtjsForm model) throws Exception {
		DAO dao = DAO.getInstance();
		return dao.updateData(form, model);
	}
	 
	
	/**
	 * 检测学生是否是团员
	 * @param String[] xhArr
	 * @return String
	 * */
	public String checkTy(String[] xhArr){
		return dao.checkSfsty(xhArr);
	}
	
	/**
	 * 删除团员注册信息(2011.11.8 qlj)
	 * 
	 * @param myForm
	 * @return boolean
	 * @throws Exception
	 */
	public boolean delTyzc(CzxxDtjsForm myForm) throws Exception {

		return dao.delTyzc(myForm);
	}
	
	public List<HashMap<String, String>> getTopTr(RequestForm rForm) {

		DAO dao = DAO.getInstance();
		Base.YXPZXY_KEY = message.getMessage("lable.xb");
		String[] en = new String[] { "xn", "xh", "xm", "nj", "xymc", "zymc",
				"bjmc","zcsj" };
		String[] cn = new String[] { "学年", "学号", "姓名", "年级", Base.YXPZXY_KEY, "专业", "班级","注册时间" };

		rForm.setColList(en);
		return dao.arrayToList(en, cn);
	}
	
	/**
	 * 团员注册信息列表(2011.11.8 qlj)
	 * 
	 * @param myForm
	 * @return List<String[]>
	 * @throws Exception
	 */
	public List<String[]> getTyzcList(CzxxDtjsForm myForm)
			throws Exception {

		return dao.getTyzcList(myForm);
	}
	
	/**
	 * 保存团员注册(2011.11.9 qlj)
	 * 
	 * @param myForm
	 * @return boolean
	 * @throws Exception
	 */
	public boolean saveTyzc(CzxxDtjsForm myForm) throws Exception {

		return dao.saveTyzc(myForm);
	}
	
	/**
	 * 修改生源地贷款信息(2011.11.8 qlj)
	 * 
	 * @param myForm
	 * @return boolean
	 * @throws Exception
	 */
	public boolean updateTyzc(CzxxDtjsForm myForm) throws Exception {

		return dao.updateTyzc(myForm);
	}
	
	/**
	 * 获取团员注册详细信息
	 * @param myForm
	 * @return
	 */
	public HashMap<String, String> getOneTyzc(CzxxDtjsForm myForm) {
		return dao.getOneTyzc(myForm);
	}
}
