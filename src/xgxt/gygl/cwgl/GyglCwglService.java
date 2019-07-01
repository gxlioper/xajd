package xgxt.gygl.cwgl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.search.SearchModel;
import xgxt.comm.search.SearchService;
import xgxt.form.RequestForm;
import xgxt.form.SaveForm;
import xgxt.form.User;
import xgxt.gygl.GyglCommService;
import xgxt.gygl.gywh.GyglGywhForm;
import xgxt.gygl.gywh.GyglGywhService;
import xgxt.gygl.qsgl.GyglQsglForm;
import xgxt.szdw.ghxy.njzrwh.GhxyNjzrwhService;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.GetTime;

public class GyglCwglService extends GyglCommService {
	
	private static List<HashMap<String,String>>fpcwxsList;
	
	GyglCwglDAO dao = new GyglCwglDAO();

	/**
	 * 获得自动分配部门列表
	 * 
	 * @author 伟大的骆
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @throws Exception
	 */
	public ArrayList<String[]> getZdfpBmList(GyglCwglForm model, User user,
			String[] colList, HttpServletRequest request)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		// 分配对象
		String fpdx = model.getFpdx();
		String path = model.getSearchModel().getPath() + "&searchType=" + fpdx;
		model.getSearchModel().setPath(path);

		ArrayList<String[]> list = null;

		SearchService searchService = new SearchService();

		// 高级查询条件
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		// 高级查询输入值
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		// 权限控制
		String searchTjByUser = searchService.getSearchTjByUser(request, "a",
				"xydm", "bjdm");
		searchTj += searchTjByUser;

		if ("xy".equalsIgnoreCase(fpdx)) {// 分配对象为学院

			list = dao.getZdfpXyList(model, user, colList, searchTj, inputV);

		} else if ("njxy".equalsIgnoreCase(fpdx)) {// 分配对象为年级+学院

			list = dao.getZdfpNjXyList(model, user, colList, searchTj, inputV);

		} else if ("njzy".equalsIgnoreCase(fpdx)) {// 分配对象为年级+专业

			list = dao.getZdfpNjZyList(model, user, colList, searchTj, inputV);

		} else {// 分配对象为班级

			list = dao.getZdfpBjList(model, user, colList, searchTj, inputV);

		}

		return list;
	}

	/**
	 * 获得自动分配部门列表
	 * 
	 * @author 伟大的骆
	 * @throws Exception 
	 * @throws Exception
	 */
	public List<String[]> getZdfpBmTjList(GyglCwglForm model, User user,
			String[] colList, HttpServletRequest request)
			throws Exception {

		// 分配对象
		String fpdx = model.getFpdx();
		String path = model.getSearchModel().getPath() + "&searchType=" + fpdx;
		model.getSearchModel().setPath(path);

		List<String[]> list = null;

		SearchService searchService = new SearchService();

		// 高级查询条件
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		// 高级查询输入值
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		// 权限控制
		HashMap<String, String> notCtrlStatus = new HashMap<String, String>();
		notCtrlStatus.put("gygly", "yes");
		user.setNotCtrlStatus(notCtrlStatus);
		String searchTjByUser = searchService.getSearchTjByUser("a", user);

		searchTj += searchTjByUser;
		
		if ("xy".equalsIgnoreCase(fpdx)) {// 分配对象为学院

			list = dao.getXyCwxx(model, user, colList, searchTj, inputV);

		} else if ("njxy".equalsIgnoreCase(fpdx)) {// 分配对象为年级+学院

			list = dao.getNjXyCwxx(model, user, colList, searchTj, inputV);

		} else if ("njzy".equalsIgnoreCase(fpdx)) {// 分配对象为年级+专业

			list = dao.getNjZyCwxx(model, user, colList, searchTj, inputV);

		} else {// 分配对象为班级

			list = dao.getNjBjCwxx(model, user, colList, searchTj, inputV);

		}

		return list;
	}
	
	/**
	 * 创建床位信息
	 * 
	 * @author qlj
	 * @throws Exception
	 */
	public void creatGyglCwxx(GyglQsglForm model, User user) throws Exception {

		// 宿舍信息表
		String tableName = "ssxxb";
		String[] colList = new String[] { "lddm", "cs", "qsh", "cws" };
		String query = " order by lddm,cs,qsh";
		List<HashMap<String, String>> qsList = getRsList(tableName, query,
				new String[] {}, colList, "");

		// 批量字段
		String[] arrzd = new String[] { "lddm", "cs", "qsh", "cwh" };

		// 楼栋
		ArrayList<String> ldList = new ArrayList<String>();
		// 层数
		ArrayList<String> csList = new ArrayList<String>();
		// 寝室号
		ArrayList<String> qshList = new ArrayList<String>();
		// 床位号
		ArrayList<String> cwhList = new ArrayList<String>();

		if (qsList != null && qsList.size() > 0) {

			for (int i = 0; i < qsList.size(); i++) {

				HashMap<String, String> map = qsList.get(i);
				// 楼栋代码
				String lddm = map.get("lddm");
				// 楼栋代码
				String cs = map.get("cs");
				// 楼栋代码
				String qsh = map.get("qsh");
				// 可住人床位数
				String cws = map.get("cws");

				if (!Base.isNull(cws)) {
					for (int j = 0; j < Integer.parseInt(cws); j++) {
						ldList.add(lddm);
						csList.add(cs);
						qshList.add(qsh);
						cwhList.add(String.valueOf(j+1));
					}
				}
			}

			model.setLddm(ldList.toArray(new String[] {}));
			model.setCs(csList.toArray(new String[] {}));
			model.setQsh(qshList.toArray(new String[] {}));
			model.setCwh(cwhList.toArray(new String[] {}));

			tableName = "xg_gygl_cwxxb";
			
			SaveForm saveForm = new SaveForm();
			saveForm.setTableName(tableName);
			saveForm.setPk("1");
			saveForm.setPkValue(new String[] { "1" });
			saveForm.setArrzd(arrzd);

			saveInfoToDb(saveForm, model, user);
		}
	}

	/**
	 * 获取未分配床
	 * 
	 * @param model
	 * @param user
	 * @param colList
	 * @param query
	 * @param inPutList
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getWfpcwList(GyglCwglForm myForm,User user)
			throws Exception {
		
		return dao.getWfpcwList(myForm,user);
	}
	
	public void getTjByFpdx(GyglCwglForm myForm){
		
		StringBuilder query = new StringBuilder();
		List<String>inputList=new ArrayList<String>();
		String pk="";
		String[] pkValue=myForm.getPrimarykey_checkVal();
		if("xy".equalsIgnoreCase(myForm.getFpdx())){
			pk="xydm";
			
		}else if("njxy".equalsIgnoreCase(myForm.getFpdx())){
			pk="nj||'!!@@!!'||xydm";
			
		}else if("njzy".equalsIgnoreCase(myForm.getFpdx())){
			pk="nj||'!!@@!!'||zydm";
			
		}else if("bj".equalsIgnoreCase(myForm.getFpdx())){
			pk="bjdm";
		}

		query.append(" and (");
		
			for(int i=0;i<pkValue.length;i++){
				if(i==0){
					query.append(pk);
					query.append("= ? ");
					inputList.add(pkValue[i]);
				}else{
					query.append(" or ");
					query.append(pk);
					query.append(" = ? ");
					inputList.add(pkValue[i]);
				}
			}
		
		query.append(" ) ");
		
		myForm.setQuery(query.toString());
		myForm.setColList(inputList);
	}
	
	public void getBmxxByFpdx(GyglCwglForm myForm){
		
		StringBuilder query = new StringBuilder();
		List<String>inputList=new ArrayList<String>();
		String pk="";
		String[] pkValue=myForm.getPrimarykey_checkVal();
		if("xy".equalsIgnoreCase(myForm.getFpdx())){
			pk="bmdm";
			
		}else if("njxy".equalsIgnoreCase(myForm.getFpdx())){
			pk="nj||'!!@@!!'||bmdm";
			
		}else if("njzy".equalsIgnoreCase(myForm.getFpdx())){
			pk="nj||'!!@@!!'||bmdm";
			
		}else if("bj".equalsIgnoreCase(myForm.getFpdx())){
			pk="bmdm";
		}

		query.append(" and (");
		
			for(int i=0;i<pkValue.length;i++){
				if(i==0){
					query.append(pk);
					query.append("= ? ");
					inputList.add(pkValue[i]);
				}else{
					query.append(" or ");
					query.append(pk);
					query.append(" = ? ");
					inputList.add(pkValue[i]);
				}
			}
		
		query.append(" ) ");
		
		myForm.setQuery(query.toString());
		myForm.setColList(inputList);
	}
	
	/**
	 * 床位自动分配
	 * @param myForm
	 * @return boolean
	 * @throws Exception
	 */
	public boolean cwzdfp(GyglCwglForm myForm,User user) throws Exception{
		
		GyglCwglModel model=new GyglCwglModel();
		//根据分配对象获取信息
		getBmxxByFpdx(myForm);
		//未分配床位列表
		List<HashMap<String,String>>wfpcwList=dao.getWfpcwList(myForm,user);
		//可混住
		model.setWfpcwList(wfpcwList);
		getKfhzcwList(model);
		List<HashMap<String,String>>khzcwList=model.getKhzcwList();
		List<HashMap<String,String>>bkhzcwList=model.getBkhzcwList();
		
		//获取部门未分配学生
		getTjByFpdx(myForm);
		List<HashMap<String,String>>wfpxsList=dao.getBmWfpxsList(myForm);
		List<HashMap<String,String>>cwffList=new ArrayList<HashMap<String,String>>();
		
		String fpdx=myForm.getFpdx();
		String rzrq=GetTime.getNowTime2();
		
		setBkhzcw(bkhzcwList,wfpxsList,rzrq,cwffList,fpdx);
		setBkhzcw(khzcwList,wfpxsList,rzrq,cwffList,fpdx);
		setKhzcwByTbm(khzcwList,wfpxsList,rzrq,cwffList,fpdx);
		
		return saveZdfpcw(cwffList,myForm);
	}
	
	/**
	 * 本部门学生可混住情况分配
	 * @param bkhzcwList
	 * @param wfpxsList
	 * @param rzrq
	 * @param cwffList
	 * @param fpdx
	 */
	public void setKhzcwByBbm(List<HashMap<String,String>>khzcwList,
			List<HashMap<String,String>>wfpxsList,String rzrq,
			List<HashMap<String,String>>cwffList,String fpdx){
		for(int k=wfpxsList.size()-1;k>=0;k--){
			HashMap<String,String>wfpxsMap=wfpxsList.get(k);
			HashMap<String,String>cwffMap=new HashMap<String,String>();
			//根据分配对象判断(学院)
			for(int j=0;j<khzcwList.size();j++){
				HashMap<String,String>khzcwMap=khzcwList.get(j);
				if("xy".equalsIgnoreCase(fpdx)){
					if(khzcwMap.get("xb").equalsIgnoreCase(wfpxsMap.get("xb"))
						&& khzcwMap.get("bmdm").equalsIgnoreCase(wfpxsMap.get("xydm"))
						&& Base.isNull(khzcwMap.get("yfp"))){
							cwffMap.put("xh", wfpxsMap.get("xh"));
							cwffMap.put("cwh", khzcwMap.get("cwh"));
							cwffMap.put("lddm", khzcwMap.get("lddm"));
							cwffMap.put("cs", khzcwMap.get("cs"));
							cwffMap.put("qsh", khzcwMap.get("qsh"));
							cwffMap.put("rzrq",rzrq);
							cwffMap.put("sjly", "自动");
							cwffList.add(cwffMap);
							khzcwMap.put("yfp", "yes");
							wfpxsList.remove(k);
							break;
					}	
				//根据分配对象判断(年级+学院)
				}else if("njxy".equalsIgnoreCase(fpdx)){
					if(khzcwMap.get("xb").equalsIgnoreCase(wfpxsMap.get("xb"))
						&& khzcwMap.get("bmdm").equalsIgnoreCase(wfpxsMap.get("xydm"))
						&& khzcwMap.get("nj").equalsIgnoreCase(wfpxsMap.get("nj"))
						&& Base.isNull(khzcwMap.get("yfp"))){
							cwffMap.put("xh", wfpxsMap.get("xh"));
							cwffMap.put("cwh", khzcwMap.get("cwh"));
							cwffMap.put("lddm", khzcwMap.get("lddm"));
							cwffMap.put("cs", khzcwMap.get("cs"));
							cwffMap.put("qsh", khzcwMap.get("qsh"));
							cwffMap.put("rzrq", rzrq);
							cwffMap.put("sjly", "自动");
							cwffList.add(cwffMap);
							khzcwMap.put("yfp", "yes");
							wfpxsList.remove(k);
							break;
					}	
				}else if("njzy".equalsIgnoreCase(fpdx)){
					if(khzcwMap.get("xb").equalsIgnoreCase(wfpxsMap.get("xb"))
						&& khzcwMap.get("bmdm").equalsIgnoreCase(wfpxsMap.get("zydm"))
						&& khzcwMap.get("nj").equalsIgnoreCase(wfpxsMap.get("nj"))
						&& Base.isNull(khzcwMap.get("yfp"))){
							cwffMap.put("xh", wfpxsMap.get("xh"));
							cwffMap.put("cwh", khzcwMap.get("cwh"));
							cwffMap.put("lddm", khzcwMap.get("lddm"));
							cwffMap.put("cs", khzcwMap.get("cs"));
							cwffMap.put("qsh", khzcwMap.get("qsh"));
							cwffMap.put("rzrq", rzrq);
							cwffMap.put("sjly", "自动");
							cwffList.add(cwffMap);
							khzcwMap.put("yfp", "yes");
							wfpxsList.remove(k);
							break;
					}	
				}else if("bj".equalsIgnoreCase(fpdx)){
					if( khzcwMap.get("xb").equalsIgnoreCase(wfpxsMap.get("xb"))
						&& khzcwMap.get("bmdm").equalsIgnoreCase(wfpxsMap.get("bjdm"))
						&& Base.isNull(khzcwMap.get("yfp"))){
							cwffMap.put("xh", wfpxsMap.get("xh"));
							cwffMap.put("cwh", khzcwMap.get("cwh"));
							cwffMap.put("lddm", khzcwMap.get("lddm"));
							cwffMap.put("cs", khzcwMap.get("cs"));
							cwffMap.put("qsh", khzcwMap.get("qsh"));
							cwffMap.put("rzrq",rzrq);
							cwffMap.put("sjly", "自动");
							cwffList.add(cwffMap);
							khzcwMap.put("yfp", "yes");
							wfpxsList.remove(k);
							break;
					}	
				}
			}
		}
	}
	
	/**
	 * 本部门学生可混住情况分配
	 * @param bkhzcwList
	 * @param wfpxsList
	 * @param rzrq
	 * @param cwffList
	 * @param fpdx
	 */
	public void setKhzcwByTbm(List<HashMap<String,String>>khzcwList,
			List<HashMap<String,String>>wfpxsList,String rzrq,
			List<HashMap<String,String>>cwffList,String fpdx){
		for(int k=wfpxsList.size()-1;k>=0;k--){
			HashMap<String,String>wfpxsMap=wfpxsList.get(k);
			HashMap<String,String>cwffMap=new HashMap<String,String>();
			//根据分配对象判断(学院)
			for(int j=0;j<khzcwList.size();j++){
				HashMap<String,String>khzcwMap=khzcwList.get(j);
				if("xy".equalsIgnoreCase(fpdx)){
					if(khzcwMap.get("xb").equalsIgnoreCase(wfpxsMap.get("xb"))
						&& Base.isNull(khzcwMap.get("yfp"))){
							cwffMap.put("xh", wfpxsMap.get("xh"));
							cwffMap.put("cwh", khzcwMap.get("cwh"));
							cwffMap.put("lddm", khzcwMap.get("lddm"));
							cwffMap.put("cs", khzcwMap.get("cs"));
							cwffMap.put("qsh", khzcwMap.get("qsh"));
							cwffMap.put("rzrq",rzrq);
							cwffMap.put("sjly", "自动");
							cwffList.add(cwffMap);
							khzcwMap.put("yfp", "yes");
							wfpxsList.remove(k);
							break;
					}	
				//根据分配对象判断(年级+学院)
				}else if("njxy".equalsIgnoreCase(fpdx)){
					if(khzcwMap.get("xb").equalsIgnoreCase(wfpxsMap.get("xb"))
						&& Base.isNull(khzcwMap.get("yfp"))){
							cwffMap.put("xh", wfpxsMap.get("xh"));
							cwffMap.put("cwh", khzcwMap.get("cwh"));
							cwffMap.put("lddm", khzcwMap.get("lddm"));
							cwffMap.put("cs", khzcwMap.get("cs"));
							cwffMap.put("qsh", khzcwMap.get("qsh"));
							cwffMap.put("rzrq", rzrq);
							cwffMap.put("sjly", "自动");
							cwffList.add(cwffMap);
							khzcwMap.put("yfp", "yes");
							wfpxsList.remove(k);
							break;
					}	
				}else if("njzy".equalsIgnoreCase(fpdx)){
					if(khzcwMap.get("xb").equalsIgnoreCase(wfpxsMap.get("xb"))
						&& Base.isNull(khzcwMap.get("yfp"))){
							cwffMap.put("xh", wfpxsMap.get("xh"));
							cwffMap.put("cwh", khzcwMap.get("cwh"));
							cwffMap.put("lddm", khzcwMap.get("lddm"));
							cwffMap.put("cs", khzcwMap.get("cs"));
							cwffMap.put("qsh", khzcwMap.get("qsh"));
							cwffMap.put("rzrq", rzrq);
							cwffMap.put("sjly", "自动");
							cwffList.add(cwffMap);
							khzcwMap.put("yfp", "yes");
							wfpxsList.remove(k);
							break;
					}	
				}else if("bj".equalsIgnoreCase(fpdx)){
					if( khzcwMap.get("xb").equalsIgnoreCase(wfpxsMap.get("xb"))
						&& Base.isNull(khzcwMap.get("yfp"))){
							cwffMap.put("xh", wfpxsMap.get("xh"));
							cwffMap.put("cwh", khzcwMap.get("cwh"));
							cwffMap.put("lddm", khzcwMap.get("lddm"));
							cwffMap.put("cs", khzcwMap.get("cs"));
							cwffMap.put("qsh", khzcwMap.get("qsh"));
							cwffMap.put("rzrq",rzrq);
							cwffMap.put("sjly", "自动");
							cwffList.add(cwffMap);
							khzcwMap.put("yfp", "yes");
							wfpxsList.remove(k);
							break;
					}	
				}
			}
			
		}
	}
	
	/**
	 * 可混住床位分配
	 * @param bkhzcwList
	 * @param wfpxsList
	 * @param rzrq
	 * @param cwffList
	 * @param fpdx
	 */
	public void setBkhzcw(List<HashMap<String,String>>bkhzcwList,
			List<HashMap<String,String>>wfpxsList,String rzrq,
			List<HashMap<String,String>>cwffList,String fpdx){
		for(int k=wfpxsList.size()-1;k>=0;k--){
			HashMap<String,String>wfpxsMap=wfpxsList.get(k);
			HashMap<String,String>cwffMap=new HashMap<String,String>();
			//根据分配对象判断(学院)
			for(int j=0;j<bkhzcwList.size();j++){
				HashMap<String,String>bkhzcwMap=bkhzcwList.get(j);
				if("xy".equalsIgnoreCase(fpdx)){
					if(bkhzcwMap.get("bmdm").equalsIgnoreCase(wfpxsMap.get("xydm"))
						&& bkhzcwMap.get("xb").equalsIgnoreCase(wfpxsMap.get("xb"))
						&& Base.isNull(bkhzcwMap.get("yfp"))){
							cwffMap.put("xh", wfpxsMap.get("xh"));
							cwffMap.put("cwh", bkhzcwMap.get("cwh"));
							cwffMap.put("lddm", bkhzcwMap.get("lddm"));
							cwffMap.put("cs", bkhzcwMap.get("cs"));
							cwffMap.put("qsh", bkhzcwMap.get("qsh"));
							cwffMap.put("rzrq",rzrq);
							cwffMap.put("sjly", "自动");
							cwffList.add(cwffMap);
							bkhzcwMap.put("yfp", "yes");
							wfpxsList.remove(k);
							break;
					}	
				//根据分配对象判断(年级+学院)
				}else if("njxy".equalsIgnoreCase(fpdx)){
					if(bkhzcwMap.get("bmdm").equalsIgnoreCase(wfpxsMap.get("xydm"))
						&& bkhzcwMap.get("nj").equalsIgnoreCase(wfpxsMap.get("nj"))
						&& bkhzcwMap.get("xb").equalsIgnoreCase(wfpxsMap.get("xb"))
						&& Base.isNull(bkhzcwMap.get("yfp"))){
							cwffMap.put("xh", wfpxsMap.get("xh"));
							cwffMap.put("cwh", bkhzcwMap.get("cwh"));
							cwffMap.put("lddm", bkhzcwMap.get("lddm"));
							cwffMap.put("cs", bkhzcwMap.get("cs"));
							cwffMap.put("qsh", bkhzcwMap.get("qsh"));
							cwffMap.put("rzrq", rzrq);
							cwffMap.put("sjly", "自动");
							cwffList.add(cwffMap);
							bkhzcwMap.put("yfp", "yes");
							wfpxsList.remove(k);
							break;
					}	
				}else if("njzy".equalsIgnoreCase(fpdx)){
					if(bkhzcwMap.get("bmdm").equalsIgnoreCase(wfpxsMap.get("zydm"))
						&& bkhzcwMap.get("nj").equalsIgnoreCase(wfpxsMap.get("nj"))
						&& bkhzcwMap.get("xb").equalsIgnoreCase(wfpxsMap.get("xb"))
						&& Base.isNull(bkhzcwMap.get("yfp"))){
							cwffMap.put("xh", wfpxsMap.get("xh"));
							cwffMap.put("cwh", bkhzcwMap.get("cwh"));
							cwffMap.put("lddm", bkhzcwMap.get("lddm"));
							cwffMap.put("cs", bkhzcwMap.get("cs"));
							cwffMap.put("qsh", bkhzcwMap.get("qsh"));
							cwffMap.put("rzrq", rzrq);
							cwffMap.put("sjly", "自动");
							cwffList.add(cwffMap);
							bkhzcwMap.put("yfp", "yes");
							wfpxsList.remove(k);
							break;
					}	
				}else if("bj".equalsIgnoreCase(fpdx)){
					if(bkhzcwMap.get("bmdm").equalsIgnoreCase(wfpxsMap.get("bjdm"))
						&& bkhzcwMap.get("xb").equalsIgnoreCase(wfpxsMap.get("xb"))
						&& Base.isNull(bkhzcwMap.get("yfp"))){
							cwffMap.put("xh", wfpxsMap.get("xh"));
							cwffMap.put("cwh", bkhzcwMap.get("cwh"));
							cwffMap.put("lddm", bkhzcwMap.get("lddm"));
							cwffMap.put("cs", bkhzcwMap.get("cs"));
							cwffMap.put("qsh", bkhzcwMap.get("qsh"));
							cwffMap.put("rzrq",rzrq);
							cwffMap.put("sjly", "自动");
							cwffList.add(cwffMap);
							bkhzcwMap.put("yfp", "yes");
							wfpxsList.remove(k);
							break;
					}	
				}
			}
			
		}
	}
	
	
	public void getKfhzcwList(GyglCwglModel model){
		List<HashMap<String,String>>wfpcwList=model.getWfpcwList();
		List<HashMap<String,String>>khzcwList=new ArrayList<HashMap<String,String>>();
		List<HashMap<String,String>>bkhzcwList=new ArrayList<HashMap<String,String>>();
		for(int i=0;i<wfpcwList.size();i++){
			HashMap<String,String>wfpcwMap=wfpcwList.get(i);
			if("可以".equalsIgnoreCase(wfpcwMap.get("kfhz"))){
				khzcwList.add(wfpcwMap);
			}else if("不可".equalsIgnoreCase(wfpcwMap.get("kfhz"))){
				bkhzcwList.add(wfpcwMap);
			}
		}
		model.setKhzcwList(khzcwList);
		model.setBkhzcwList(bkhzcwList);
	}
	
	
	public boolean saveZdfpcw(List<HashMap<String, String>>cwffList
			,GyglCwglForm myForm) throws Exception{
		GhxyNjzrwhService ghxyNjzrwhService = new GhxyNjzrwhService();
		GyglCwglModel model=new GyglCwglModel();
		// 进行数据操作 的表名
		String realTable = "xszsxxb";
		
		String[] arrzd = new String[] { "xh","lddm", "qsh","cs","cwh", "rzrq", "sjly","ssbh"};
		
		// 当前页的学号数组(先删后增将该页信息删除时)
		int len=cwffList.size();
		
		String[] pkValue=new String[len];
		String[] lddm = new String[len];
		String[] qsh = new String[len];
		String[] cs = new String[len];
		String[] cwh = new String[len];
		String[] xh = new String[len];
		String[] rzrq = new String[len];
		String[] sjly = new String[len];
		String[] ssbh = new String[len];
		for (int i = 0; i < len; i++) {
			HashMap<String,String>xszsxxMap=cwffList.get(i);
			pkValue[i] = "2";
			lddm[i]=xszsxxMap.get("lddm");
			qsh[i]=xszsxxMap.get("qsh");
			cs[i]=xszsxxMap.get("cs");
			cwh[i]=xszsxxMap.get("cwh");
			xh[i]=xszsxxMap.get("xh");
			rzrq[i]=xszsxxMap.get("rzrq");
			sjly[i]=xszsxxMap.get("sjly");
			ssbh[i]=xszsxxMap.get("lddm")+"-"+xszsxxMap.get("qsh");
		}
		
		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(realTable);
		saveForm.setPk("1");
		saveForm.setPkValue(pkValue);
		saveForm.setArrzd(arrzd);
		
		model.setSjly(sjly);
		model.setLddm(lddm);
		model.setQsh(qsh);
		model.setCs(cs);
		model.setCwh(cwh);
		model.setXh(xh);
		model.setRzrq(rzrq);
		model.setSsbh(ssbh);
			
		myForm.setMessage(String.valueOf(len));
		return  ghxyNjzrwhService.saveTyxx(saveForm, model);
	}
	
	/**
	 * 获取手动分配对象(寝室信息)
	 * 
	 * @param model
	 * @param user
	 * @param colList
	 * @param query
	 * @param inPutList
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getFpcwList(GyglCwglForm model, User user,
			String[] colList, String[] inPutList,HttpServletRequest request)
			throws Exception {
		model.getSearchModel().setPath("gygl_cwgl_sdfp.do&searchType="+model.getFpdx());
		return dao.getFpcwList(model, user, colList, inPutList,request);
	}
	
	/**
	 * 
	 * (过滤不符合选择床位的学生)
	 * @param model
	 * @param user
	 * @param colList
	 * @param query
	 * @param inPutList
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getWfpcwxsList(GyglCwglForm myForm, User user,
			String[] colList,  String[] inPutList,HttpServletRequest request)
			throws Exception {
		//床位详细信息
		HashMap<String,String>cwxxMap=getCwxxMap(myForm);
		//根据床位信息获取过滤条件
		String query=getWzsxsQuery(cwxxMap).toString();
		return dao.getWzsxsList(myForm, user,query, colList, inPutList, request);
	}
	
	/**
	 * 获取未分配学生列表(过滤不符合选择床位的学生)
	 * @param model
	 * @param user
	 * @param colList
	 * @param query
	 * @param inPutList
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getWzsxsList(GyglCwglForm myForm, User user,
			String[] colList,  String[] inPutList,HttpServletRequest request)
			throws Exception {
		//床位详细信息
		
		return dao.getWzsxsList(myForm, user,"", colList, inPutList, request);
	}
	
	
	/**
	 * 根据床位信息获取过滤条件
	 * @param cwxxMap
	 * @return  StringBuilder
	 */
	public StringBuilder getWzsxsQuery(HashMap<String,String>cwxxMap){
		StringBuilder query=new StringBuilder();
		//楼栋性别
		String xbxd=cwxxMap.get("xbxd");
		//分配对象
		String fpdx=cwxxMap.get("fpdx");
		//年级
		String nj=cwxxMap.get("nj");
		//部门代码
		String bmdm=cwxxMap.get("bmdm");
		//可否混住
		String kfhz=cwxxMap.get("kfhz");
		//寝室性别
		String xb=cwxxMap.get("xb");
		
		// ------------------根据分配对象过滤学生信息----------------------
		if("不可".equalsIgnoreCase(kfhz)){
			
			if("xy".equalsIgnoreCase(fpdx)){
				query.append(" and xydm= '"+bmdm+"' ");
			}else if("njxy".equalsIgnoreCase(fpdx)){
				query.append(" and nj= '"+nj+"' ");
				query.append(" and xydm= '"+bmdm+"' ");
			}else if("njzy".equalsIgnoreCase(fpdx)){
				query.append(" and nj= '"+nj+"' ");
				query.append(" and zydm= '"+bmdm+"' ");
			}else if("bj".equalsIgnoreCase(fpdx)){
				query.append(" and bjdm= '"+bmdm+"' ");
			}
		}
		
		// ------------------根据分配对象过滤学生信息 end----------------------
		
		// 无限制
		query.append(" and xb='"+xb+"' ");
		
		return query;
	}
	
	/**
	 * 获取床位详细信息
	 * @param myForm
	 * @return
	 */
	public HashMap<String,String>getCwxxMap(GyglCwglForm myForm){
		
		return dao.getCwxxMap(myForm);
	}
	
	public boolean saveXscw(GyglCwglForm myForm) throws Exception{
		
		DAO dao=DAO.getInstance();
		StringBuilder sql=new StringBuilder();
		
		sql.append("insert into xszsxxb(xh,ssbh,cwh,rzrq,lddm,qsh,cs,sjly)");
		sql.append(" select ? ,lddm||'-'||qsh ssbh,cwh,?,lddm,qsh,cs,'手动' ");
		sql.append("from  xg_gygl_cwxxb b where b.lddm || '-' || b.cs || '-' || b.qsh || '-' || b.cwh = ? ");

		String []inputVal={myForm.getXh(),GetTime.getNowTime2(),myForm.getPkValue()};  
		
		return dao.runUpdate(sql.toString(), inputVal);
	}
	
	/**
	 * 取消床位分配
	 * @param myForm
	 * @return
	 * @throws Exception
	 */
	public boolean delXszsxx(GyglCwglForm myForm) throws Exception{
		DAO dao=DAO.getInstance();
		String[]pkValue=myForm.getPkvArr();
		String[]delArr=new String[pkValue.length];
		for(int i=0;i<pkValue.length;i++){
			delArr[i]=" delete from xszsxxb where xh='"+pkValue[i]+"' ";
		}
		int[]result=dao.runBatch(delArr);
		for(int i=0;i<result.length;i++){
			if(result[i]==0){
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 获取需要分配床位的学生信息
	 * @param myForm
	 * @return List<HashMap<String,String>>
	 */
	public List<HashMap<String,String>>getFpcwXs(GyglCwglForm myForm){
		fpcwxsList=dao.getFpcwXs(myForm);
		return fpcwxsList;
	}
	
	/**
	 * 获取楼栋详细信息
	 * @param myForm
	 * @return List<HashMap<String,String>>
	 */
	public List<HashMap<String,String>>getLdxxxx(GyglCwglForm myForm){
		
		return dao.getLdxxxx(myForm);
	}
	
	/**
	 * 获取楼栋楼层统计信息
	 * @param myForm
	 * @return  List<HashMap<String,String>>
	 */
	public List<HashMap<String,String>>getLdcsList(String lddm){
		
		GyglGywhService gywhService=new GyglGywhService();
		GyglGywhForm myForm=new GyglGywhForm();
		myForm.setLddm(lddm);
		myForm.setFpbj("一般");
		return gywhService.getLdcsList(myForm);
	}
	
	/**
	 * 获取楼栋楼层统计信息
	 * @param myForm
	 * @return  List<HashMap<String,String>>
	 */
	public List<HashMap<String,String>>getKxssTjList(String lddm,String fpdx,
			String userStatus,String userDep){
		
		GyglGywhService gywhService=new GyglGywhService();
		GyglGywhForm myForm=new GyglGywhForm();
		User user=new User();
		myForm.setLddm(lddm);
		user.setUserStatus(userStatus);
		myForm.setUser(user);
		myForm.getUser().setUserDep(userDep);
		myForm.setFpdx(fpdx);
		myForm.setFpbj("一般");
		return gywhService.getKxssTjList(myForm);
	}
	
	/**
	 * 获取楼栋楼层入住信息
	 * @param myForm
	 * @return
	 */
	public List<HashMap<String,Object>>getSsxxList(GyglCwglForm myForm){
		
		GyglGywhForm gywhForm=new GyglGywhForm();
		List<HashMap<String,Object>>ssxxList=new ArrayList<HashMap<String,Object>>();
		List<HashMap<String,String>>ldxxList=getLdxxxx(myForm);
		for(int i=0;i<ldxxList.size();i++){
			HashMap<String,Object>ssxxMap=new HashMap<String,Object>();
			HashMap<String,String>ldxxMap=ldxxList.get(i);
			String lddm=ldxxMap.get("lddm");
			gywhForm.setLddm(lddm);
			//楼栋代码
			ssxxMap.put("xqmc", ldxxMap.get("xqmc"));
			//楼栋名称
			ssxxMap.put("yqmc", ldxxMap.get("yqmc"));
			//楼栋代码
			ssxxMap.put("lddm", ldxxMap.get("lddm"));
			//楼栋名称
			ssxxMap.put("ldmc", ldxxMap.get("ldmc"));
			//性别限制
			ssxxMap.put("xbxd", ldxxMap.get("xbxd"));
			//楼栋楼层信息
			List<HashMap<String,String>>ldlcList=dao.getLdlcxx(myForm);
			List<HashMap<String,String>>ldlcxxList=new ArrayList<HashMap<String,String>>();
			myForm.setLddm(lddm);
			List<HashMap<String,String>>csList=dao.getLdlcxx(myForm);
			myForm.setLddm(null);
			for(int k=0;k<csList.size();k++){
				HashMap<String,String>csMap=csList.get(k);
				HashMap<String,String>ldlcxxMap=new HashMap<String,String>();
				for(int j=0;j<ldlcList.size();j++){
					HashMap<String,String>ldlcMap=ldlcList.get(j);
					ldlcxxMap.put("qss", "0");
					ldlcxxMap.put("cs", csMap.get("cs"));
					ldlcxxMap.put("lddm", lddm);
					if(lddm.equalsIgnoreCase(ldlcMap.get("lddm"))
							&&  csMap.get("cs").equalsIgnoreCase(ldlcMap.get("cs"))){
						ldlcxxMap.putAll(ldlcMap);
						ldlcxxList.add(ldlcxxMap);
						break;
					}
				}
				
			}
			ssxxMap.put("ldlcxx", ldlcxxList);
			ssxxList.add(ssxxMap);
		}
		return ssxxList;
	}
	
	
	/**
	 * 获取楼栋楼层统计信息
	 * @param myForm
	 * @return  List<HashMap<String,String>>
	 */
	public List<HashMap<String,String>>getQsfpList(String lddm,String cs){
		
		GyglGywhService gywhService=new GyglGywhService();
		GyglGywhForm myForm=new GyglGywhForm();
		myForm.setLddm(lddm);
		myForm.setCs(cs);
		return gywhService.getQsfpList(myForm);
	}
	

	/**
	 * 寝室详细信息
	 * @param myForm
	 * @return List<HashMap<String,String>>getQsxx
	 */
	public List<HashMap<String,Object>>getQsxxList(String lddm,String fpdx,
			String userDep,String userStatus,String cs,String[]ydxh){
		GyglGywhService gywhService=new GyglGywhService();
		GyglGywhForm gywhForm=new GyglGywhForm();
		GyglCwglForm myForm=new GyglCwglForm();
		User user=new User();
		
		user.setUserStatus(userStatus);
		user.setUserDep(userDep);
		myForm.setLddm(lddm);
		myForm.setCs(cs);
		myForm.setYdxh(ydxh);
		gywhForm.setLddm(lddm);
		gywhForm.setCs(cs);
		gywhForm.setFpbj("一般");
		gywhForm.setFpdx(fpdx);
		gywhForm.setUser(user);
		List<HashMap<String,String>>cwxxList= dao.getCwxxxx(myForm,gywhForm);
		List<HashMap<String,String>>qsfpList= gywhService.getQsfpList(gywhForm);
		List<HashMap<String,Object>>xxxxList= new ArrayList<HashMap<String,Object>>();
		for(int j=0;j<qsfpList.size();j++){
			HashMap<String,String>qsfpMap=qsfpList.get(j);
			
			HashMap<String,Object>xxxxMap=new HashMap<String,Object>();
			xxxxMap.put("lddm", lddm);
			xxxxMap.put("cs", cs);
			xxxxMap.put("qsh", qsfpMap.get("qsh"));
			xxxxMap.put("cws", qsfpMap.get("cws"));
			xxxxMap.put("fpbm", qsfpMap.get("fpbm"));
			xxxxMap.put("rzrs", qsfpMap.get("rzrs"));
			xxxxMap.put("fpdx", qsfpMap.get("fpdx"));
			int cws=Integer.parseInt(qsfpMap.get("cws"));
			int n=0;
			List<HashMap<String,String>>cwxxArr=new ArrayList<HashMap<String,String>>();
				for(int i=0;i<cwxxList.size();i++){
				
					HashMap<String,String>cwxxMap=cwxxList.get(i);
					if(!"yes".equalsIgnoreCase(cwxxMap.get("sfadd"))
						&&cwxxMap.get("lddm").equalsIgnoreCase(qsfpMap.get("lddm"))
						&& cwxxMap.get("cs").equalsIgnoreCase(qsfpMap.get("cs"))
						&& cwxxMap.get("qsh").equalsIgnoreCase(qsfpMap.get("qsh"))){
						n++;
						cwxxMap.put("sfadd", "yes");
						cwxxArr.add(cwxxMap);
						if(cws==n){
							break;
						}
						
				}
				
			}
			xxxxMap.put("cwxxArr", cwxxArr);
			xxxxList.add(xxxxMap);
		}
		return xxxxList;
	}
	
	/**
	 * 保存床位手动分配
	 * @param cwffList
	 * @param myForm
	 * @return
	 * @throws Exception
	 */
	public boolean saveSdfpcw(GyglCwglForm myForm) throws Exception{
		GhxyNjzrwhService ghxyNjzrwhService = new GhxyNjzrwhService();
		GyglCwglModel model=new GyglCwglModel();
		// 进行数据操作 的表名
		String realTable = "xszsxxb";
		
		String[] arrzd = new String[] { "xh","lddm", "qsh","cs","cwh", "rzrq", "sjly","ssbh"};
		if(myForm.getCwhArr()!=null && myForm.getCwhArr().length>0){
		int len=myForm.getCwhArr().length;
		String rzsj=GetTime.getNowTime2();
		
		String[] pkValue=new String[len];
		String[] cwh=myForm.getCwhArr();
		String[] lddm = myForm.getLddmArr();
		String[] qsh = myForm.getQshArr();
		String[] cs = myForm.getCsArr();
		String[] xh = myForm.getXhArr();
		
		int n=0;
		
		for (int i = 0; i < len; i++) {
			pkValue[i]=lddm[i]+cs[i]+qsh[i]+cwh[i];
			if(!Base.isNull(xh[i])){
				n++;
			}
		}
		
		String[] cwhArr=new String[n];
		String[] lddmArr=new String[n];
		String[] qshArr=new String[n];
		String[] csArr=new String[n];
		String[] xhArr=new String[n];
		String[] rzrqArr=new String[n];
		String[] sjlyArr=new String[n];
		String[] ssbhArr=new String[n];
		int m=0;
		for (int i = 0; i < len; i++) {
			//主键
			if(!Base.isNull(xh[i])){
				
				cwhArr[m]=cwh[i];
				lddmArr[m]=lddm[i];
				csArr[m]=cs[i];
				qshArr[m]=qsh[i];
				xhArr[m]=xh[i];
				rzrqArr[m]=rzsj;
				sjlyArr[m]="手动";
				ssbhArr[m]=lddm[i]+qsh[i];
				m++;
			}
		}
		
		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(realTable);
		saveForm.setPk(" lddm||cs||qsh||cwh ");
		saveForm.setPkValue(pkValue);
		saveForm.setArrzd(arrzd);
		
		model.setSjly(sjlyArr);
		model.setLddm(lddmArr);
		model.setQsh(qshArr);
		model.setCs(csArr);
		model.setCwh(cwhArr);
		model.setXh(xhArr);
		model.setRzrq(rzrqArr);
		model.setSsbh(ssbhArr);
			
		myForm.setMessage(String.valueOf(len));
			return  ghxyNjzrwhService.saveTyxx(saveForm, model);
		}else {
			return true;
		}
	}
	
	/**
	 * 学生住宿信息
	 * @param myForm
	 * @param request
	 * @return List<String[]>
	 * @throws Exception
	 */
	public List<String[]>getXszsxxList(GyglCwglForm myForm,
			RequestForm rForm,User user,HttpServletRequest request) throws Exception{
		String fpdx = myForm.getFpdx();
		String path = myForm.getSearchModel().getPath() + "&searchType=" + fpdx;
		myForm.getSearchModel().setPath(path);
		return dao.getXszsxxList(myForm,rForm,user, request);
	}
	
	
	
	public List<String[]>getBmxxList(GyglCwglForm myForm,User user,String[] colList,HttpServletRequest request) throws Exception{
		return dao.getBmxxList(myForm,user,colList,request);
	}
	
	/**
	 * 获取需要分配床位的学生信息
	 * @param myForm
	 * @return List<HashMap<String,String>>
	 */
	public List<HashMap<String,String>>getFpcwXsxx(String xh){
		GyglCwglForm myForm =new GyglCwglForm();
		String[] pkValue={xh};
		myForm.setPkvArr(pkValue);
		fpcwxsList.addAll(dao.getFpcwXs(myForm));
		return fpcwxsList;
	}
	
	/**
	 * 获取需要分配床位的学生信息
	 * @param myForm
	 * @return List<HashMap<String,String>>
	 */
	public List<HashMap<String,String>>getFpcwXsByrz(String xh){
		for(int i=0;i<fpcwxsList.size();i++){
			HashMap<String,String>fpcwxsMap=fpcwxsList.get(i);
			if(xh.equalsIgnoreCase(fpcwxsMap.get("xh"))){
				fpcwxsList.remove(i);
				break;
			}
		}
		return fpcwxsList;
	}
	
	/**
	 * 获取部门学生列表
	 * @param myForm
	 * @return void
	 */
	public void getBmfpcwxs(GyglCwglForm myForm) {

		List<HashMap<String, String>> list = dao.getBmxsList(myForm);
		List<String> xsArr = new ArrayList<String>();
		for (int i = 0; i < list.size(); i++) {
			HashMap<String, String> xsMap = list.get(i);
			xsArr.add(xsMap.get("xh"));
		}

		myForm.setPkvArr(xsArr.toArray(new String[] {}));
	}
	
	/**
	 * 获取部门学生列表
	 * @param myForm
	 * @return void
	 */
	public int Bmxss(String[]bm,String fpdx) {
		GyglCwglForm myForm=new GyglCwglForm();
		myForm.setPrimarykey_checkVal(bm);
		myForm.setFpdx(fpdx);
		List<HashMap<String, String>> list = dao.getBmxsList(myForm);
		
		return list.size();
	}
	
	/**
	 * 筛选需要分配床位学生信息
	 * @param myForm
	 * @return List<String[]>
	 * @throws Exception 
	 */
	public List<String[]>getSxxsList(GyglCwglForm myForm,User user) throws Exception{
		
		return dao.getSxxsList(myForm,user);
		
	}
}