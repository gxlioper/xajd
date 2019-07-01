package xgxt.xtwh.xtwhCriterion.jsgl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.utils.CommonQueryDAO;
import xgxt.utils.CommonUpdata;
import xgxt.xtwh.xtwhCriterion.CriterionService;
import xgxt.xtwh.xtwhCriterion.RsModel;
import xgxt.xtwh.xtwhCriterion.yhgl.GnmkModel;

public class JsglService extends CriterionService{
	
	/**
	  * @author luning
	 * @param model 
	  * @describe 处理查询角色相关所需要的属性
	  * @param requestForm
	  * @return
	  */
	
	public JsglRequestModel setJsModel(JsglRequestModel model) {
		// 定义值
		String tableName = "xg_view_xtwh_jswh";
		
		String realTable  = "xg_xtwh_jswhb";
		
		String[] colList = new String[] { "jsdm","jsmc","jslxmc","jscmmc","jssm","sfqy","xyyy","glqx"};
		
		model.setColList(colList);
		
		String pk  = "jsdm";
		
		model.setTableName(tableName);
		
		model.setRealTable(realTable);
		
		model.setPk(pk);
		
		return model;
	}
	
	/**
	  * @author luning
	 * @param model 
	  * @describe 角色查询子页面显示的查询内容
	  * @param requestForm
	  * @return
	  */
	
	public JsglRequestModel setJsQueryModel(JsglRequestModel model) {
		// 定义值
		String tableName = "xg_view_xtwh_jswh";
		
		String realTable  = "xg_xtwh_jswhb";
		
		String[] colList = new String[] { "jsdm","jsmc","jslxmc","jscmmc"};
		
		model.setColList(colList);
		
		model.setTableName(tableName);
		
		model.setRealTable(realTable);
		
		return model;
	}

	/**
	 * @author luning
	 * @describe 角色获取查询角色结果集
	 * @param requestForm
	 * @return
	 */
	public RsModel getJsList(JsglRequestModel requestModel) {
		String go = requestModel.getJsglQueryModel().getGo();
		RsModel rsModel = new RsModel();
		if(go!=null&&go.equalsIgnoreCase(go)){
			String tableName = requestModel.getTableName();
			String[] colList = requestModel.getColList();
			JsglQueryModel queryModel = (JsglQueryModel)requestModel.getJsglQueryModel();
			JsglDAO myDAO = new JsglDAO();
			
			try {
				rsModel.setRsList(myDAO.getJsQueryList(tableName,queryModel,colList));
			} catch (Exception e) {
				// TODO 自动生成 catch 块
				e.printStackTrace();
			}
		}
		return rsModel;
	}
	
	/**
	 * @author luning
	 * @describe 角色维护单个更新
	 * @return
	 */
	public void jswhUpdate(JsglRequestModel model, HttpServletRequest request) {
		String pk = model.getPk();
		String doType = model.getDoType();
		String realTable = model.getRealTable();
		String pkValue = model.getPkValue();
		Boolean update = null;
		
		String userName = (String) request.getSession().getAttribute("userName"); 
		String [] colList;
		model.setCjr(userName);
		if(pkValue!=null&&!pkValue.equalsIgnoreCase("")){
			colList = new String []{"jscmdm","jsdm","jslxdm","jsmc","jssm","sfqy"};
		}else{
			colList = new String []{"jscmdm","jsdm","jslxdm","jsmc","jssm","sfqy","cjr"};
		}
		if(doType!=null){
			try {
				update = CommonUpdata.commonUpdata(colList,model,realTable,pk,pkValue,request);
			} catch (Exception e) {
				e.printStackTrace();
			}
			setMessage(request, update,"save");
		}
	}
	

	
	private Boolean jsToUser(String jsmc, String userName) {
		JsglDAO jsglDAO = new JsglDAO();
		return jsglDAO.jsToUser(jsmc,userName);
	}

	/**
	 * @author luning
	 * @describe 角色维护单个信息查看
	 * @return
	 */
	public HashMap<String, String> getJswh(JsglRequestModel model) {
		String pk = model.getPk();
		String pkValue = model.getPkValue();
		String tableName = model.getTableName();
		String [] colList = new String []{"glqx","jscmdm","jscmmc","jsdm","jslxdm","jslxmc","jsmc","jssm","sfqy","xyyy"};
		HashMap<String, String> rs = CommonQueryDAO.commonQueryOne(tableName, colList, pk, pkValue);
		if(null==pkValue){
			rs.put("xyyy", "0");
		}
		return rs;
	}

	/**
	 * 单个角色删除
	 * @author luning
	 * @param model
	 * @param request 
	 * @return
	 */
	public Boolean delJswh(JsglRequestModel model, HttpServletRequest request) {
		String pk = model.getPk();
		String[] pkValue = model.getCheckPkValue();
		String realTable = model.getRealTable();
		Boolean del = false;
		JsglDAO jsglDAO = new JsglDAO();
		del = jsglDAO.BatchDel(realTable, pkValue, pk);
		return del;
	}

	/**
	 * 获取角色拥有的用户
	 * @author luning
	 * @param model
	 * @param request 
	 * @return
	 */
	public List<HashMap<String,String>> getYhForJs(String jsdm,String type) {
		JsglDAO jsglDAO = new JsglDAO();
		List<HashMap<String,String>> rs = jsglDAO.getYhForJs(jsdm,type);
		return rs;
	}
	
	/**
	 * 获取单个角色功能模块
	 * @param jsmc
	 * @return
	 */
	public List<HashMap<String, String>> getOneGnmkList() {
		return new JsglDAO().getGnmkList();
	}
	
	/**
	 * 获取分配对象list
	 * @param YhglForm
	 * @param user
	 * @return
	 */
	public List<HashMap<String, Object>> getFpdxList() {
		JsglDAO jsglDAO = new JsglDAO();
		List<HashMap<String, String>> gnmkList = jsglDAO.getGnmkList();
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();

		// 功能模块
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("Lv", "1");
		map.put("aName", "Open");
		map.put("gnmkList", gnmkList);
		list.add(map);

		return list;
	}
	
	/**
	 * dwr角色管理获取
	 * @param jsmc
	 * @return
	 */
	public List<HashMap<String, String>> getGnmkList(String lv,String dm,String userName, String jsdm) {
		return new JsglDAO().getGnmkList(lv,dm,userName,jsdm);
	}
	
	/** 获取所有的功能模块
	 * @param userName
	 * @return
	 */
	public List<GnmkModel> getAllGnmkList(String jsdm){
		JsglDAO jsglDao = new JsglDAO();
		List<GnmkModel> list = new ArrayList<GnmkModel>();
		
		// 所有功能模块
		List<HashMap<String, String>> gnmkAllList = jsglDao.getAllGnmkList();
		// 一级菜单
		List<HashMap<String, String>> gnmkListOne = new ArrayList<HashMap<String,String>>();
		// 二级菜单yhglDao
		List<HashMap<String, String>> gnmkListTwo = new ArrayList<HashMap<String,String>>();
		// 三级菜单
		List<HashMap<String, String>> gnmkListThr = new ArrayList<HashMap<String,String>>();
		// 四级按钮
		List<HashMap<String, String>> gnmkListFou = jsglDao.getBtnList(jsdm);
		
		for(HashMap<String, String> map : gnmkAllList){
			switch (map.get("dm").length()) {
				case 3 : gnmkListOne.add(map);break;
				case 5 : gnmkListTwo.add(map);break;
				case 7 : gnmkListThr.add(map);break;
				default: break;
			}
		}
		
		// 获取所有的功能
		for(HashMap<String,String> mapOne : gnmkListOne){
			GnmkModel gnmkModelOne = new GnmkModel();
			String dmOne = mapOne.get("dm");
			gnmkModelOne.setGnmkdm(dmOne);
			gnmkModelOne.setGnmkmc(mapOne.get("mc"));
			
			List<GnmkModel> childListOne = setChildList(gnmkModelOne, gnmkListTwo);
			
			for(GnmkModel gnmkModelTwo : childListOne){
				// 三级菜单
				List<GnmkModel> childListTwo = setChildList(gnmkModelTwo, gnmkListThr);
				
				for(GnmkModel gnmkModelThr : childListTwo){
					String dmThr = gnmkModelThr.getGnmkdm();
					// 三级菜单下的按钮
					List<HashMap<String, String>> btnList = new ArrayList<HashMap<String,String>>();
					
					for(HashMap<String, String> btn : gnmkListFou){
						if(btn.get("gnmkdm").equalsIgnoreCase(dmThr)){
							// 判断是角色拥有还是用户拥有
							if("yes".equalsIgnoreCase(btn.get("jsyy"))){
								btn.put("checked", "yes");
							}
							
							btnList.add(btn);
						}
					}
					
					gnmkModelThr.setBtnList(btnList);
					// 删除已知按钮，减小循环次数
					gnmkListFou.removeAll(btnList);
				}
			}

			list.add(gnmkModelOne);
		}
		
		return list;
	}
	
	/**
	 * 设置子节点
	 * @param model
	 * @param list
	 * @return
	 */
	private List<GnmkModel> setChildList(GnmkModel model, List<HashMap<String, String>> list){
		List<GnmkModel> childList = new ArrayList<GnmkModel>();
		
		// 获取该gnmk下的子功能模块
		for (HashMap<String, String> map : list){
			String dm = map.get("dm");
			if(dm.substring(0, dm.length()-2).equalsIgnoreCase(model.getGnmkdm())){
				GnmkModel gnmkModel = new GnmkModel();
				
				gnmkModel.setGnmkdm(dm);
				gnmkModel.setGnmkmc(map.get("mc"));
	
				childList.add(gnmkModel);
			}
		}
		
		list.removeAll(childList);
		
		model.setChildList(childList);
		return childList;
	}
	
	/**
	 * 获取JsList
	 * @param pkValues
	 * @return
	 */
	public List<HashMap<String, String>> getJsList(String[] pkValues){
		return new JsglDAO().getJsList(pkValues);		
	}
	
	/**
	 * 获取角色详细信息
	 * @param jsdm
	 * @return
	 */
	public HashMap<String, String> getJsInfo(String jsdm){
		return new JsglDAO().getJsInfo(jsdm);
	}
	
	/**
	 * 保存角色权限
	 * @return
	 */
	public boolean saveJsqx(String jsdm, String[] btns){
		JsglDAO jsglDao = new JsglDAO();
		List<String[]> list = new ArrayList<String[]>();
		
		for(int i=0; i<btns.length; i++){	
			String[] btn = btns[i].split("_");
			list.add(new String[]{jsdm,btn[0],btn[1]}); 
		}
		
		boolean flag = jsglDao.delJsqx(jsdm);
		
		if(flag){
			flag = jsglDao.saveJsqx(list);
		}
		
		return flag;
	}
}
