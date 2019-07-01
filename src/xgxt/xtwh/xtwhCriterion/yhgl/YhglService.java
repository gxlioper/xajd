package xgxt.xtwh.xtwhCriterion.yhgl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xgxt.DAO.DAO;
import xgxt.base.Encrypt;
import xgxt.utils.String.StringUtils;
import xgxt.xtwh.xtwhCriterion.CriterionService;
import xgxt.xtwh.xtwhCriterion.jsgl.JsglDAO;

public class YhglService extends CriterionService{
	private YhglDAO yhglDao = new YhglDAO();
	
	/**
	 * 保存用户
	 * @return
	 */
	public boolean saveYh(YhglForm model){
		return yhglDao.saveYh(model);
	}
	
	/**
	 * 修改用户
	 */
	public boolean modiYh(YhglForm model){
		return yhglDao.modiYh(model);
	}
	
	/**
	 * 初始化密码
	 * @return
	 */
	public boolean initMm(String[] pkValues){
		// 初始化密码
		String mm = new Encrypt().encrypt("888888");
		boolean flag = false;
		
		if(pkValues!=null && pkValues.length>0){ // 若没有选择用户，初始化所有用户 
			flag = yhglDao.initMm(mm, pkValues);
		}else{	// 选择了用户，初始化指定用户
			flag = yhglDao.initMm(mm);
		}
		
		return flag;
	}
	
	/**
	 * 初始化学生用户
	 * @param jsdm
	 * @return
	 */
	public Map<String, String> initXs(String jsdm){
		return null;
	}
	
	/**
	 * 获得用户查询
	 * @return
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws SecurityException 
	 * @throws IllegalArgumentException 
	 */
	public List<String[]> queryYhList(YhglForm model){
		String[] colList = new String[]{"szbm"};
		String[] colLikeList = new String[]{"yhm", "xm"};
		
		String jsdm = model.getJsdm();
		String sfksq = model.getSfksq();
		
		// 附加查询条件
		StringBuilder otherQuery = new StringBuilder();
		
		// 添加查询条件角色代码
		if(StringUtils.isNotNull(jsdm)){
			otherQuery.append("and exists(select 1 from xg_xtwh_yhjsb b where b.yhm=a.yhm and b.jsdm='")
						.append(jsdm)
						.append("') ");
		}
		
		// 添加查询条件是否可授权
		if(StringUtils.isNotNull(sfksq) && "是".equalsIgnoreCase(sfksq)){
			otherQuery.append("and exists(select 1 from xg_xtwh_yhjsb b where b.yhm=a.yhm and b.jsqx='是')");
		}else if(StringUtils.isNotNull(sfksq) && "否".equalsIgnoreCase(sfksq)){
			otherQuery.append("and not exists(select 1 from xg_xtwh_yhjsb b where b.yhm=a.yhm and b.jsqx='是')");
		}
		
		return yhglDao.queryYhList(model, colList, colLikeList, otherQuery.toString());
	}
	
	/**
	 * 获取表头
	 * @return
	 */
	public String[] getTopTr(String lx){
		String[] topTr = null;
		
		if("yhgl".equalsIgnoreCase(lx)){
			topTr = new String[]{"用户名", "姓名", "所在部门", "是否启用", "已分配角色数"};
		}else if("yhqxfp".equalsIgnoreCase(lx)){
			topTr = new String[]{"用户名", "姓名", "所在部门"};
		}else if("js".equalsIgnoreCase(lx)){
			topTr = new String[]{"角色名", "角色类型", "角色操作范围", "是否启用"};
		}else if("yhjsfp".equalsIgnoreCase(lx)){
			topTr = new String[]{"用户名", "姓名", "所在部门", "是否启用", "已分配角色数", "操作权限"};
		}else if("xsBatch".equalsIgnoreCase(lx)){
			topTr = new String[]{"学生类型", "学生类别", "已分配角色数"};
		}else if("yhjs".equalsIgnoreCase(lx)){
			topTr = new String[]{"角色名", "角色类型", "角色操作范围", "用户名", "用户部门"};
		}else if("jsqx".equalsIgnoreCase(lx)){
			topTr = new String[]{"角色名", "角色类型", "角色操作范围", "功能模块名", "拥有操作菜单"};
		}else if("yhqx".equalsIgnoreCase(lx)){
			topTr = new String[]{"用户名", "部门名称", "功能模块名", "拥有操作菜单"};
		}
		
		return topTr;
	}
	
	/**
	 * 获取分配对象list
	 * @param YhglForm
	 * @param user
	 * @return
	 */
	public List<HashMap<String, Object>> getFpdxList(YhglForm model,String userName) {
		List<HashMap<String, String>> gnmkList = yhglDao.getGnmkList(userName, 1);
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
	 * 获得单级功能模块菜单
	 * @param model
	 * @param user
	 * @return
	 */
	public List<HashMap<String, String>> getOneGnmkList(String userName, int lv){
		return yhglDao.getGnmkList(userName, lv);
	}
	
	/**
	 * 获得部门列表
	 * @param lv
	 * @param dm
	 * @param userName
	 * @return
	 */
	public List<HashMap<String, String>> getGnmkList(String lv,String dm, String userName, String yhm) {
		return yhglDao.getGnmkList(lv, dm, userName, yhm);
	}
	
	/**
	 * 获取用户
	 * @param yhm
	 * @return
	 */
	public Map<String, String> getYh(String yhm){
		return yhglDao.getYh(yhm);
	}
	
	/**
	 * 获取角色操作范围List
	 * @return
	 */
	public List<HashMap<String, String>> getJsczfwList() {
		return new JsglDAO().getJsczfwList();
	}
	
	/**
	 * 获取角色类型List
	 * @return
	 */
	public List<HashMap<String, String>> getJslxList(){
		return new JsglDAO().getJslxList();
	}
	
	/**
	 * 保存用户权限
	 * @return
	 */
	public boolean saveYhqx(String sqyh,String yhm, String[] btns){
		List<String[]> list = new ArrayList<String[]>();
		
		for(int i=0; i<btns.length; i++){	
			String[] btn = btns[i].split("_");
			list.add(new String[]{yhm,btn[0],btn[1],sqyh}); 
		}
		
		boolean flag = yhglDao.delYhqx(yhm);
		
		if(flag){
			flag = yhglDao.saveYhqx(list);
		}
		
		return flag;
	}
	
	/**
	 * 批量删除用户
	 * @param pkValues
	 * @return
	 */
	public boolean delYh(String[] pkValues){
		return yhglDao.delYh(pkValues);
	}
	
	/**
	 * 查询登陆用户拥有管理功能角色list
	 * @param model
	 * @return
	 */
	public List<String[]> queryJsList(YhglForm model, String userName){
		String otherQuery = "and exists(select 1 from xg_xtwh_yhjsb b where b.yhm='" + userName + "' and a.jsdm=b.jsdm) ";
		
		return yhglDao.queryJsList(model, otherQuery);
	}
	
	/**
	 * 角色批量授权用户
	 * @param yhs
	 * @param jsIds
	 * @return
	 */
	public boolean saveJsForYh(String[] yhs,String[] jsIds){
		String[][] params = new String[yhs.length * jsIds.length][];
		String[] pkValues = new String[yhs.length * jsIds.length];
		
		int count = 0;
		
		for(int i=0; i<yhs.length; i++){
			String[] strs = yhs[i].split("_");
			
			String yhm = strs[0];
			String jsqx = "yy".equalsIgnoreCase(strs[1]) ? "否" : "是";
			
			for(int j=0; j<jsIds.length; j++){
				pkValues[count] = jsIds[j] + yhm;
				params[count] = new String[]{jsIds[j], yhm, jsqx};
				count++;
			}
		}
		
		return yhglDao.batchSq(pkValues, params);
	}
	
	/**
	 * 查询学生类型List
	 * @param model
	 * @return
	 */
	public List<String[]> queryXslxList(YhglForm model){
		String[] colList = new String[]{"xslxlbdm"};
		String[] colLikeList = new String[]{"xslxmc"};
		
		return yhglDao.queryXslxList(model, colList, colLikeList);
	}
	
	/**
	 * 角色批量授权于学生
	 * @param yhs
	 * @param jsIds
	 * @return
	 */
	public boolean saveJsForXs(String[] xss,String[] jsIds, String kssj, String jssj){
		String[][] params = new String[xss.length * jsIds.length][];
		String[] pkValues = new String[xss.length * jsIds.length];
		
		int count = 0;
		
		for(int i=0; i<xss.length; i++){
			String xslxdm = xss[i];
			for(int j=0; j<jsIds.length; j++){
				pkValues[count] = xslxdm + jsIds[j];
				params[count] = new String[]{xslxdm, jsIds[j], kssj, jssj};
				count++;
			}
		}
		
		return yhglDao.batchSqForXs(pkValues, params);
	}
	
	/**
	 * 保存学生权限
	 * @return
	 */
	public boolean saveXsqx(String sqyh,String xh, String[] btns){
		List<String[]> list = new ArrayList<String[]>();
		
		String[] pkValues = new String[btns.length];
		String[][] params = new String[btns.length][];
		
		for(int i=0; i<btns.length; i++){	
			String[] btn = btns[i].split("_");
			params[i] = new String[]{xh,btn[0],btn[1],sqyh}; 

			pkValues[i] = xh+btn[0]+btn[1];
		}
		
		// 是否存在
		boolean[] isExists = DAO.getInstance().checkExists("xg_xtwh_xscdqxb", "yhm||gnmkdm||cdid", pkValues);
		
		for(int i=0; i<isExists.length; i++){
			if(!isExists[i]){
				list.add(params[i]);
			}
		}
		
		return yhglDao.saveXsqx(list);
	}
	
	/**
	 * 获取所有的功能模块
	 * @param userName
	 * @return
	 */
	public List<GnmkModel> getAllGnmkList(String userName, String yhm){
		List<GnmkModel> list = new ArrayList<GnmkModel>();
		
		// 一级菜单
		List<HashMap<String, String>> gnmkListOne = yhglDao.getGnmkList(userName, 1);
		// 二级菜单
		List<HashMap<String, String>> gnmkListTwo = yhglDao.getGnmkList(userName, 2);
		// 三级菜单
		List<HashMap<String, String>> gnmkListThr = yhglDao.getGnmkList(userName, 3);
		// 四级按钮
		List<HashMap<String, String>> gnmkListFou = yhglDao.getBtnList(yhm);
		
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
								btn.put("disabled", "yes");
								btn.put("checked", "yes");
							}else if("yes".equalsIgnoreCase(btn.get("yhyy"))){
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
	 * 获取登陆用户的管理角色
	 * @param userName
	 * @return
	 */
	public List<HashMap<String, String>> getJsListForUserName(String userName){
		return yhglDao.getJsListForUserName(userName);
	}
	
	
	/**
	 * 获取指定一批用户的角色
	 * @param yhms
	 * @return
	 */
	public Map<String, List<HashMap<String, String>>> getYhJsList(String[] yhms){
		Map<String, List<HashMap<String, String>>> map = new HashMap<String, List<HashMap<String,String>>>();
		
		List<HashMap<String, String>> list = yhglDao.getYhjsList(yhms);
		
		for(HashMap<String, String> yhjsMap : list){
			String yhm = yhjsMap.get("yhm");
			
			if(map.containsKey(yhm)){
				map.get(yhm).add(yhjsMap);
			}else{
				List<HashMap<String, String>> jsList = new ArrayList<HashMap<String,String>>();
				jsList.add(yhjsMap);
				map.put(yhm, jsList);
			}
		}
		return map;
	}
}
