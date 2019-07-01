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
	 * 获取审核流程列表
	 * 
	 * @return List<HashMap<String,String>>
	 */
	public List<HashMap<String, String>> getShlcList() {

		return dao.getShlcList();
	}

	/**
	 * 获取设置信息
	 */
	public HashMap<String, String> getSzMap() {

		return dao.getSzMap();
	}
	

	/**
	 * 判断是否在申请时间内
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
	 * 保存助学贷款设置信息
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

		// 保存信息(根据PK先删除后增)
		return saveInfoToDb(saveForm, model, request);
	}

	/**
	 * 保存助学贷款设置信息
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

		// 保存信息(根据PK先删除后增)
		DAO mydao = DAO.getInstance();
		mydao.runUpdate("delete from xg_zxdk_xssq where guid =?", new String[]{form.getGuid()});
		return saveInfoToDb(saveForm, model, request);
	}

	/**
	 * 获取学生基本信息
	 */
	public HashMap<String, String> getStuInfo(ZxdkForm model) {

		return dao.getStuInfo(model);
	}

	public List<String[]> getZxdkInfo(HttpServletRequest request,
			String[] colList, ZxdkForm model) throws Exception {

		SearchService searchService = new SearchService();
		// 高级查询条件
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		// 高级查询输入值
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		// 权限控制
		String searchTjByUser = searchService.getSearchTjByUser(request, "a",
				"xydm", "bjdm");
		searchTj += searchTjByUser;

		return dao.getZxdkInfo(model, model.getUser(), colList, searchTj,
				inputV);
	}
	
	/**
	 * 助学贷款审核
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
		// 高级查询条件
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		// 高级查询输入值
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		// 权限控制
		String searchTjByUser = searchService.getSearchTjByUser(request, "c",
				"xydm", "bjdm");
		searchTj += searchTjByUser;

		//增加其他条件值
		String[]otherInput={model.getShgw()};
		inputV=commDao.unionArray(otherInput, inputV);
		return dao.getZxdkSh(model, model.getUser(), colList, searchTj,
				inputV);
	}
	
	/**
	 * 保存助学贷款审核表初始信息
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
     * 根据用户名获取当前模块
     * 所涉及到的用户所在审批岗位
     * @param model
     * @return List<HashMap<String,String>>
     */
    public List<HashMap<String,String>>getUserSpgw(ZxdkForm model){

    	return dao.getUserSpgw(model);
    }
    
    /**
     * 审核岗位控制
     * @param request
     * @param model
     * @param spgwList
     */
    public void shgwKz(HttpServletRequest request, ZxdkForm model,
			List<HashMap<String, String>> spgwList) {
		
    	// 判断登陆用户在几个审核岗位中
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
			//多审核岗位
			request.setAttribute("spgwInfo", spgwList);
			request.setAttribute("dspgw", "dspgw");
		}
	}
    
    /**
	 * 获取学生助学贷款信息
	 */
	public HashMap<String,String>getZxdkInfo(ZxdkForm model){
		
		return dao.getZxdkInfo(model);
	}
	
	/**
     * 修改审核状态
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
	 * 获取证件类型
	 */
	public List<HashMap<String,String>>getZjlx(ZxdkForm model){
		
		return dao.getZjlx(model);
	}
	
	/**
	 * 助学贷款审核信息
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
