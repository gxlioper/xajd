package xgxt.zxdk.xnmz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;

import com.zfsoft.utils.StringUtil;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.comm.search.SearchService;
import xgxt.form.SaveForm;
import xgxt.form.User;
import xgxt.rcsw.comm.swbl.RcswSwblForm;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.GetTime;

public class ZxdkService extends CommService {

	ZxdkDAO dao = new ZxdkDAO();

	/**
	 * ��ȡ��������б�
	 * 
	 * @return List<HashMap<String,String>>
	 */
	public List<HashMap<String, String>> getShlcList() {

		return dao.getShlcList();
	}

	/**
	 * ��ȡ������Ϣ
	 */
	public HashMap<String, String> getSzMap() {

		return dao.getSzMap();
	}
	

	/**
	 * �ж��Ƿ�������ʱ����
	 * @return
	 */
	public boolean checkZxdkSqsj(){

		String num=dao.checkZxdkSqsj();
		if("0".equalsIgnoreCase(num)){
			return false;
		}
		return true;
	}
	

	/**
	 * ������ѧ����������Ϣ
	 * 
	 * @throws Exception
	 */
	public boolean saveZxdkSz(ZxdkForm form, HttpServletRequest request)
			throws Exception {

		ZxdkModel model = new ZxdkModel();

		String realTable = "xg_zxdk_szb";

		String sqkssj = form.getSqkssj();
		String sqjssj = form.getSqjssj();
		String shkssj = form.getShkssj();
		String shjssj = form.getShjssj();
		String splc = form.getSplc();

		String[] onezd = new String[] { "sqkssj", "sqjssj", "shkssj", "shjssj",
				"splc" };

		String pk = "1";
		String[] pkValue = { "1" };

		model.setSqkssj(sqkssj);
		model.setSqjssj(sqjssj);
		model.setShkssj(shkssj);
		model.setShjssj(shjssj);
		model.setSplc(splc);

		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(realTable);
		saveForm.setPk(pk);
		saveForm.setPkValue(pkValue);
		saveForm.setOnezd(onezd);

		// ������Ϣ(����PK��ɾ������)
		return saveInfoToDb(saveForm, model, request);
	}

	/**
	 * ������ѧ����������Ϣ
	 * 
	 * @throws Exception
	 */
	public boolean saveZxdkxx(ZxdkForm form, HttpServletRequest request)
			throws Exception {

		ZxdkModel model = new ZxdkModel();

		String realTable = "xg_zxdk_xssq";

		String[] onezd = new String[] {"guid", "xh", "lxrxm", "lxrjtzz", "lxrgzdw",
				"lxrgddh", "lxryddh", "jzrxm", "jzrzjh", "jzrzjlxdm", "jzrdz",
				"jzrgx", "jzryb", "jzrdh",
				"jzryj", "jtysr", "grwzdz", "dkkssj", "dknx", "dkzje",
				"onexnxfje", "onexnqsfje","onexnshf", "onexnzje", "twoxnxfje",
				"twoxnqsfje", "twoxnzje","twoxnshf", "threexnxfje", "threexnqsfje",
				"threexnzje","threexnshf", "fourxnxfje", "fourxnqsfje","fourxnshf", "fourxnzje",
				"fivexnxfje", "fivexnqsfje","fivexnshf", "fivexnzje", "sqsj", "fqxm",
				"fqsfzh", "fqgzdw", "fqlxfs", "mqxm", "mqsfzh", "mqgzdw",
				"mqlxfs","shzt" };

		String pk = "1";
		String[] pkValue = {"2"};
		if (StringUtil.isNull(form.getGuid()))
			form.setGuid(dao.getGuid());
		BeanUtils.copyProperties(model, form);

		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(realTable);
		saveForm.setPk(pk);
		saveForm.setPkValue(pkValue);
		saveForm.setOnezd(onezd);

		// ������Ϣ(����PK��ɾ������)
		DAO mydao = DAO.getInstance();
		mydao.runUpdate("delete from xg_zxdk_xssq where guid =?", new String[]{form.getGuid()});
		return saveInfoToDb(saveForm, model, request);
	}

	/**
	 * ��ȡѧ��������Ϣ
	 */
	public HashMap<String, String> getStuInfo(ZxdkForm model) {

		return dao.getStuInfo(model);
	}

	public List<String[]> getZxdkInfo(HttpServletRequest request,
			String[] colList, ZxdkForm model) throws Exception {

		SearchService searchService = new SearchService();
		// �߼���ѯ����
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		// �߼���ѯ����ֵ
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		// Ȩ�޿���
		String searchTjByUser = searchService.getSearchTjByUser(request, "a",
				"xydm", "bjdm");
		searchTj += searchTjByUser;

		return dao.getZxdkInfo(model, model.getUser(), colList, searchTj,
				inputV);
	}
	
	/**
	 * ��ѧ�������
	 * @param model
	 * @param user
	 * @param colList
	 * @param query
	 * @param inPutList
	 * @return List<String[]>
	 */
	public List<String[]>getZxdkSh(HttpServletRequest request,
			String[] colList, ZxdkForm model)
			throws Exception{
		DAO commDao=DAO.getInstance();
		SearchService searchService = new SearchService();
		// �߼���ѯ����
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		// �߼���ѯ����ֵ
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		// Ȩ�޿���
		String searchTjByUser = searchService.getSearchTjByUser(request, "c",
				"xydm", "bjdm");
		searchTj += searchTjByUser;

		//������������ֵ
		String[]otherInput={model.getShgw()};
		inputV=commDao.unionArray(otherInput, inputV);
		return dao.getZxdkSh(model, model.getUser(), colList, searchTj,
				inputV);
	}
	
	/**
	 * ������ѧ������˱��ʼ��Ϣ
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean saveZxdkShInfo(ZxdkForm model) throws Exception{

		return dao.saveZxdkShInfo(model);
	}
	
	public boolean delZxdkInfo(ZxdkForm model) throws Exception{
		
		String[]checkVal=model.getCheckVal();
		int length=checkVal.length;
		String [] sql=new String[length*2];
		int n=0;
		for (int i=0;i<length;i++){
			String xh=checkVal[i].split("!!@@!!")[1];
			String guid=checkVal[i].split("!!@@!!")[0];
			sql[n]=" delete from xg_zxdk_xssq where guid='"+guid+"' ";
			sql[++n]=" delete from xg_zxdk_zxdkshb where guid='"+guid+"' ";
		}
		
		return saveArrDate(sql);
		
	}
	
	public List<HashMap<String, String>> zxdkLcgz(ZxdkForm model) {

		return dao.zxdkLcgz(model);
	}
	
	/**
     * �����û�����ȡ��ǰģ��
     * ���漰�����û�����������λ
     * @param model
     * @return List<HashMap<String,String>>
     */
    public List<HashMap<String,String>>getUserSpgw(ZxdkForm model){

    	return dao.getUserSpgw(model);
    }
    
    /**
     * ��˸�λ����
     * @param request
     * @param model
     * @param spgwList
     */
    public void shgwKz(HttpServletRequest request, ZxdkForm model,
			List<HashMap<String, String>> spgwList) {
		
    	// �жϵ�½�û��ڼ�����˸�λ��
		if (spgwList.size() > 1 && Base.isNull(model.getShgw())) {

			request.setAttribute("spgwList", spgwList);
			

		} else  if (spgwList.size() == 1){
			
			for (int i = 0; i < spgwList.size(); i++) {
				HashMap<String, String> spgwMap = spgwList.get(i);
				
					model.setShjb(spgwMap.get("xh"));
					model.setShgw(spgwMap.get("id"));
					break;
				
			}
			
		} else  if (!Base.isNull(model.getShgw())){
			
			for (int i = 0; i < spgwList.size(); i++) {
				HashMap<String, String> spgwMap = spgwList.get(i);
				if (model.getShgw().equalsIgnoreCase(spgwMap.get("id"))) {
					model.setShjb(spgwMap.get("xh"));
					request.setAttribute("spgw", model.getShgw());
					break;
				}
			}
			
		}
		
		
		if(spgwList.size() > 1){
			//����˸�λ
			request.setAttribute("spgwInfo", spgwList);
			request.setAttribute("dspgw", "dspgw");
		}
	}
    
    /**
	 * ��ȡѧ����ѧ������Ϣ
	 */
	public HashMap<String,String>getZxdkInfo(ZxdkForm model){
		
		return dao.getZxdkInfo(model);
	}
	
	/**
     * �޸����״̬
     * @param model
     * @return
     * @throws Exception
     */
    public boolean updateShzt(ZxdkForm model) throws Exception {

    	boolean blog= dao.updateShzt(model);
    	if(blog){
    		
    		blog=dao.updateJzryj(model);
    	}
    	return blog;
	}
    
    public boolean  checkZxdkShsj(){
    	
    	String num = dao.checkZxdkShsj();
    	
		if ("0".equalsIgnoreCase(num)) {
			
			return false;
			
		}
		
		return true;
	}
    
    /**
	 * ��ȡ֤������
	 */
	public List<HashMap<String,String>>getZjlx(ZxdkForm model){
		
		return dao.getZjlx(model);
	}
	
	/**
	 * ��ѧ���������Ϣ
	 * @param model
	 * @return
	 */
	public List<HashMap<String, String>> getShList(ZxdkForm model) {

		List<HashMap<String,String>>shxxList=dao.getShList(model);
		List<HashMap<String,String>>gwxxList=dao.getGwxxList(model);
		for(int i=0;i<shxxList.size();i++){
			HashMap<String,String>shxxMap=shxxList.get(i);
			for(int j=0;j<gwxxList.size();j++){
				HashMap<String,String>gwxxMap=gwxxList.get(j);
				if(shxxMap.get("xtgwid").equalsIgnoreCase(gwxxMap.get("spgw"))){
					gwxxMap.putAll(shxxMap);
					break;
				}
			}
		}
		return gwxxList;
	}
}
