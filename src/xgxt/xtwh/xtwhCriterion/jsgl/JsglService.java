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
	  * @describe �����ѯ��ɫ�������Ҫ������
	  * @param requestForm
	  * @return
	  */
	
	public JsglRequestModel setJsModel(JsglRequestModel model) {
		// ����ֵ
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
	  * @describe ��ɫ��ѯ��ҳ����ʾ�Ĳ�ѯ����
	  * @param requestForm
	  * @return
	  */
	
	public JsglRequestModel setJsQueryModel(JsglRequestModel model) {
		// ����ֵ
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
	 * @describe ��ɫ��ȡ��ѯ��ɫ�����
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
				// TODO �Զ����� catch ��
				e.printStackTrace();
			}
		}
		return rsModel;
	}
	
	/**
	 * @author luning
	 * @describe ��ɫά����������
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
	 * @describe ��ɫά��������Ϣ�鿴
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
	 * ������ɫɾ��
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
	 * ��ȡ��ɫӵ�е��û�
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
	 * ��ȡ������ɫ����ģ��
	 * @param jsmc
	 * @return
	 */
	public List<HashMap<String, String>> getOneGnmkList() {
		return new JsglDAO().getGnmkList();
	}
	
	/**
	 * ��ȡ�������list
	 * @param YhglForm
	 * @param user
	 * @return
	 */
	public List<HashMap<String, Object>> getFpdxList() {
		JsglDAO jsglDAO = new JsglDAO();
		List<HashMap<String, String>> gnmkList = jsglDAO.getGnmkList();
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();

		// ����ģ��
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("Lv", "1");
		map.put("aName", "Open");
		map.put("gnmkList", gnmkList);
		list.add(map);

		return list;
	}
	
	/**
	 * dwr��ɫ�����ȡ
	 * @param jsmc
	 * @return
	 */
	public List<HashMap<String, String>> getGnmkList(String lv,String dm,String userName, String jsdm) {
		return new JsglDAO().getGnmkList(lv,dm,userName,jsdm);
	}
	
	/** ��ȡ���еĹ���ģ��
	 * @param userName
	 * @return
	 */
	public List<GnmkModel> getAllGnmkList(String jsdm){
		JsglDAO jsglDao = new JsglDAO();
		List<GnmkModel> list = new ArrayList<GnmkModel>();
		
		// ���й���ģ��
		List<HashMap<String, String>> gnmkAllList = jsglDao.getAllGnmkList();
		// һ���˵�
		List<HashMap<String, String>> gnmkListOne = new ArrayList<HashMap<String,String>>();
		// �����˵�yhglDao
		List<HashMap<String, String>> gnmkListTwo = new ArrayList<HashMap<String,String>>();
		// �����˵�
		List<HashMap<String, String>> gnmkListThr = new ArrayList<HashMap<String,String>>();
		// �ļ���ť
		List<HashMap<String, String>> gnmkListFou = jsglDao.getBtnList(jsdm);
		
		for(HashMap<String, String> map : gnmkAllList){
			switch (map.get("dm").length()) {
				case 3 : gnmkListOne.add(map);break;
				case 5 : gnmkListTwo.add(map);break;
				case 7 : gnmkListThr.add(map);break;
				default: break;
			}
		}
		
		// ��ȡ���еĹ���
		for(HashMap<String,String> mapOne : gnmkListOne){
			GnmkModel gnmkModelOne = new GnmkModel();
			String dmOne = mapOne.get("dm");
			gnmkModelOne.setGnmkdm(dmOne);
			gnmkModelOne.setGnmkmc(mapOne.get("mc"));
			
			List<GnmkModel> childListOne = setChildList(gnmkModelOne, gnmkListTwo);
			
			for(GnmkModel gnmkModelTwo : childListOne){
				// �����˵�
				List<GnmkModel> childListTwo = setChildList(gnmkModelTwo, gnmkListThr);
				
				for(GnmkModel gnmkModelThr : childListTwo){
					String dmThr = gnmkModelThr.getGnmkdm();
					// �����˵��µİ�ť
					List<HashMap<String, String>> btnList = new ArrayList<HashMap<String,String>>();
					
					for(HashMap<String, String> btn : gnmkListFou){
						if(btn.get("gnmkdm").equalsIgnoreCase(dmThr)){
							// �ж��ǽ�ɫӵ�л����û�ӵ��
							if("yes".equalsIgnoreCase(btn.get("jsyy"))){
								btn.put("checked", "yes");
							}
							
							btnList.add(btn);
						}
					}
					
					gnmkModelThr.setBtnList(btnList);
					// ɾ����֪��ť����Сѭ������
					gnmkListFou.removeAll(btnList);
				}
			}

			list.add(gnmkModelOne);
		}
		
		return list;
	}
	
	/**
	 * �����ӽڵ�
	 * @param model
	 * @param list
	 * @return
	 */
	private List<GnmkModel> setChildList(GnmkModel model, List<HashMap<String, String>> list){
		List<GnmkModel> childList = new ArrayList<GnmkModel>();
		
		// ��ȡ��gnmk�µ��ӹ���ģ��
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
	 * ��ȡJsList
	 * @param pkValues
	 * @return
	 */
	public List<HashMap<String, String>> getJsList(String[] pkValues){
		return new JsglDAO().getJsList(pkValues);		
	}
	
	/**
	 * ��ȡ��ɫ��ϸ��Ϣ
	 * @param jsdm
	 * @return
	 */
	public HashMap<String, String> getJsInfo(String jsdm){
		return new JsglDAO().getJsInfo(jsdm);
	}
	
	/**
	 * �����ɫȨ��
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
