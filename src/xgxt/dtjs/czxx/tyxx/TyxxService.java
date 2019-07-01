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
	 * ��Ա��Ϣ��ѯ
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
	 * ��ȡ������Ա��Ϣ
	 * @param pkValue
	 * @return
	 */
	public Map<String, String> getTyxxOne(String pkValue){
		String[] colList = new String[]{"xh", "xm", "xb", "nj", "xymc", "zymc", "bjmc", "rtrq",
				"rtdd", "bz"};
		return CommonQueryDAO.commonQueryOne("view_czxx_tyxx", colList, "pk", pkValue);
	}
	
	/**
	 * ��Ա��Ϣ��ѯ����
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
	 * ������Ա��Ϣ
	 * @param SaveForm saveForm
	 * @param TyxxbcModel tyxxbc
	 * @throws Exception 
	 * */
	public boolean saveTyxx(SaveForm saveForm,TyxxbcModel tyxxbc) throws Exception{
		DAO dao = DAO.getInstance();
		return dao.saveData(saveForm, tyxxbc);
	}
	
	/**
	 * ���ݱ��������ȡ��Ӧ��ע��
	 * @param String[] colList
	 * @param String tableName
	 * @return String[]
	 * */
	public String[] getColumnNameCN(String[] colList, String tableName){
		DAO dao=DAO.getInstance();
		return dao.getColumnNameCN(colList, tableName);
	}
	
	/**
	 * ����Ա��ѵ��Ϣ��ѯ
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
	 * ����������ѯ����Ա��ѵ��Ϣ 
	 * @param tableName(����)
	 * @param pk(����)
	 * @param pkValue(����ֵ)
	 * @param colList(���ֵ)
	 * @return HashMap<String, String>
	 */
	public HashMap<String, String> queryFtypxInfo(String tableName, String pk,
			String pkValue, String[] colList) {
		return dao.selectFtypxInfo(tableName, pk, pkValue, colList);
	}
	
	
	
	/**
	 * �������Ա��ѵ��Ϣ�����Ϣ
	 * @param tableName(����)
	 * @param pk(����)
	 * @param pkValue(����ֵ)
	 * @param onezd(��һ�ֶ�)
	 * @return boolean
	 * @throws Exception
	 */
	public boolean saveFtypxxx(SaveForm form, CzxxDtjsForm model,
			HttpServletRequest request) throws Exception {
		DAO dao = DAO.getInstance();
		return dao.submitData(form, model, request);
	}	
	
	/**
	 * ��ѯ����Ա��ѵ��Ŀ�������Ϣ
	 * @return List<HashMap<String, String>>
	 * */
	public List<HashMap<String, String>> queryFtypxxmList(){
		return dao.selectFtypxxmdmb();
	}
	
	/**
	 * ��ѯ��Ա��ѵ��Ŀ�������Ϣ
	 * @return List<HashMap<String, String>>
	 * */
	public List<HashMap<String, String>> queryTypxxmList(){
		return dao.selectTypxxmdmb();
	}
	
	
	/**
	 * ɾ������Ա��ѵ��Ϣ
	 * @param SaveForm form
	 * @param CzxxDtjsForm model
	 * @throws Exception
	 */
	public boolean delFtypxxx(SaveForm form, CzxxDtjsForm model) throws Exception {
		DAO dao = DAO.getInstance();
		return dao.delDate(form, model);
	}
	
	/**
	 *������Ա��Ϣ���
	 * @param SaveForm form
	 * @param CzxxDtjsForm model
	 * @throws Exception
	 */
	public boolean auditingTnty(SaveForm form, CzxxDtjsForm model) throws Exception {
		DAO dao = DAO.getInstance();
		return dao.updateData(form, model);
	}
	 
	
	/**
	 * ���ѧ���Ƿ�����Ա
	 * @param String[] xhArr
	 * @return String
	 * */
	public String checkTy(String[] xhArr){
		return dao.checkSfsty(xhArr);
	}
	
	/**
	 * ɾ����Աע����Ϣ(2011.11.8 qlj)
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
		String[] cn = new String[] { "ѧ��", "ѧ��", "����", "�꼶", Base.YXPZXY_KEY, "רҵ", "�༶","ע��ʱ��" };

		rForm.setColList(en);
		return dao.arrayToList(en, cn);
	}
	
	/**
	 * ��Աע����Ϣ�б�(2011.11.8 qlj)
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
	 * ������Աע��(2011.11.9 qlj)
	 * 
	 * @param myForm
	 * @return boolean
	 * @throws Exception
	 */
	public boolean saveTyzc(CzxxDtjsForm myForm) throws Exception {

		return dao.saveTyzc(myForm);
	}
	
	/**
	 * �޸���Դ�ش�����Ϣ(2011.11.8 qlj)
	 * 
	 * @param myForm
	 * @return boolean
	 * @throws Exception
	 */
	public boolean updateTyzc(CzxxDtjsForm myForm) throws Exception {

		return dao.updateTyzc(myForm);
	}
	
	/**
	 * ��ȡ��Աע����ϸ��Ϣ
	 * @param myForm
	 * @return
	 */
	public HashMap<String, String> getOneTyzc(CzxxDtjsForm myForm) {
		return dao.getOneTyzc(myForm);
	}
}
