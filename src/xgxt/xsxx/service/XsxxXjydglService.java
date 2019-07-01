package xgxt.xsxx.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.action.Base;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.form.User;
import xgxt.studentInfo.model.StudentInfoForm;
import xgxt.studentInfo.service.XsxxglService;
import xgxt.utils.String.StringUtils;
import xgxt.xsxx.dao.XsxxXjydglDAO;
import xgxt.xtwh.common.dao.XtlcglDAO;
import xgxt.xtwh.common.service.XtlcglService;

import common.GlobalsVariable;


public class XsxxXjydglService {
	XsxxXjydglDAO xjydglDao = new XsxxXjydglDAO();
	
	/**
	 * 初始化异动信息
	 * */
	public HashMap<String, String> initYdxx(HashMap<String, String> map){
		XsxxglService xsxxglService = new XsxxglService();
		//异动序号取自序列
		map.put("ydxh", xsxxglService.getMaxYdxh());
		map.put("ydhxydm", map.get("xydm"));
		map.put("ydhzydm", map.get("zydm"));
		map.put("ydhbdm", map.get("bjdm"));
		map.put("ydhnj", map.get("nj"));
		map.put("ydhxz", map.get("xz"));
		map.put("ydhxjztm", map.get("xjztm"));
		map.put("ydhsfzx", map.get("sfzx"));
		
		map.put("save_ydhxydm", map.get("xydm"));
		map.put("save_ydhzydm", map.get("zydm"));
		map.put("save_ydhbdm", map.get("bjdm"));
		map.put("save_ydhnj", map.get("nj"));
		map.put("save_ydhxz", map.get("xz"));
		map.put("save_ydhxjztm", map.get("xjztm"));
		map.put("save_ydhsfzx", map.get("sfzx"));
		
		map.put("ydqxydm", map.get("xydm"));
		map.put("ydqxymc", map.get("xymc"));
		map.put("ydqzydm", map.get("zydm"));
		map.put("ydqzymc", map.get("zymc"));
		map.put("ydqbdm", map.get("bjdm"));
		map.put("ydqbjmc", map.get("bjmc"));
		map.put("ydqnj", map.get("nj"));
		map.put("ydqxz", map.get("xz"));
		map.put("ydqxjztm", map.get("xjztm"));
		map.put("ydqsfzx", map.get("sfzx"));		
		return map;
	}
	
	/**
	 * 初始化学籍异动审核记录
	 * */
	public boolean initXjydshjl(StudentInfoForm model,User user){
		boolean result = false;
		XtlcglDAO lcglDao = new XtlcglDAO();
		//审核流程中的所有岗位
		List<HashMap<String, String>> list = new ArrayList<HashMap<String,String>>();
		String shlcid = lcglDao.getGnlcxx(GlobalsVariable.GNDM_XSXX_XJYDSH+model.getYdlbdm()).get("lcid");
		list = lcglDao.getShlcgw(shlcid);
		
		xjydglDao.saveXjydshjl(model,list,user);		
		return result;
	}
	
	/**
	 * 获取审核时要查询的字段
	 * */
	public String[] getXjydshCol(){
		XtlcglService lcglService = new XtlcglService();
		String[] colList = getXjydcxColEn("sh");	
		//加入动态获取的审核信息字段
		String[] shCol = lcglService.getGnshgwmcxx(GlobalsVariable.GNDM_XSXX_XJYDSH);
		colList = StringUtils.joinStrArr(colList,shCol);
		return colList;
	}
	
	/**
	 * 获取学籍异动查询中显示的列(英文)
	 * */
	public String[] getXjydcxColEn(String lx){
		String[] colList = new String[]{};	
		if("cx".equalsIgnoreCase(lx)){
			colList = new String[]{"pkValue", "xh", "xm", "xb", "ydlbmc", "ydrq", "ydjzrq", "ydhnj",
					"ydhxymc", "ydhzymc", "ydhbjmc"};
			//, "ydqnj","ydqxymc", "ydqzymc", "ydqbjmc", "ydqxz", "ydhxz"
		}else if("sh".equalsIgnoreCase(lx)){
			colList = new String[]{"disabled", "bgcolor", "pkValue", "xh", "xm", "xb", "ydlbmc", "ydrq", 
					"ydjzrq", "ydhnj", "ydhxymc", "ydhzymc", "ydhbjmc"};
			//, "ydqnj", "ydqxymc", "ydqzymc", "ydqbjmc", "ydqxz", "ydhxz"
		}
		return colList;
	}
	
	/**
	 * 获取学籍异动查询中显示的列(中文)
	 * */
	public String[] getXjydcxColCn(String lx){
		String[] colList = new String[]{};	
		if("cx".equalsIgnoreCase(lx)){
			colList = new String[]{"主键", "学号", "姓名", "性别", "异动类别", "异动日期", "异动截止日期", "异动后年级", "异动后"+Base.YXPZXY_KEY, 
					"异动后专业", "异动后班级"};//, "异动前年级", "异动前学院", "异动前专业", "异动前班级", "异动前学制", "异动后学制"
		}else if("sh".equalsIgnoreCase(lx)){
			colList = new String[]{"是否可见", "背景色", "主键", "学号", "姓名", "性别", "异动类别", 
					"异动日期", "异动截止日期", "异动后年级", "异动后"+Base.YXPZXY_KEY, "异动后专业", "异动后班级"};
			//, "异动前年级", "异动前学院", "异动前专业", "异动前班级", "异动前学制", "异动后学制"
		}
		return colList;
	}
	
	/**
	 * 获取学籍异动审核查询表头信息
	 * */
	public List<HashMap<String, String>> getXjydshToptr(String ydlbdm){	
		XtlcglService lcglService = new XtlcglService();
		//动态获取的审核信息字段		
		String[] shCol = lcglService.getGnshgwmcxx(GlobalsVariable.GNDM_XSXX_XJYDSH+ydlbdm);
		//英文列
		String[] enCol = getXjydcxColEn("sh");
		//中文列
		String[] cnCol = getXjydcxColCn("sh");
		//列后加入动态的审核列
		enCol = StringUtils.joinStrArr(enCol, shCol);
		cnCol = StringUtils.joinStrArr(cnCol, shCol);
		
		return xjydglDao.arrayToList(enCol, cnCol);
	}
	
	/**
	 * 获取学籍异动审核流程查询表头信息
	 * */
	public List<HashMap<String, String>> getXjydshlcToptr(){	
		//英文列
		String[] enCol = {"ydlbm","ydlbmc","xjzt","sfzx","shlcmc","tjbcyfs","shgw"};
		//中文列
		String[] cnCol = {"异动类别代码","异动类别","学籍状态","是否在校","审核流程","同级别参与方式","审核岗位"};
		
		return xjydglDao.arrayToList(enCol, cnCol);
	}	
	
	/**
	 * 获取学籍异动审核岗位
	 * */
	public List<HashMap<String, String>> getXjydshgw(String yhlbm){
		XtlcglService lcglService = new XtlcglService();
		//动态获取的审核信息字段		
		String[] shColEn = lcglService.getGnshgwxx(GlobalsVariable.GNDM_XSXX_XJYDSH + yhlbm);
		String[] shColCn = lcglService.getGnshgwmcxx(GlobalsVariable.GNDM_XSXX_XJYDSH + yhlbm);
		
		List<HashMap<String, String>> rs = new ArrayList<HashMap<String, String>>();
		
		if(shColEn != null && shColEn.length>0 && StringUtils.isNotNull(shColEn[0])){
			rs = xjydglDao.arrayToList(shColEn,shColCn);
		}
		return rs;
	}
		
	/**
	 * 学籍异动审核默认要查询的字段信息
	 * */
	public String getXjydshDefColumns(User user){
		XtlcglService lcglService = new XtlcglService();
		String[] column = lcglService.getGnshgwmcxx(GlobalsVariable.GNDM_XSXX_XJYDSH);
		
		return StringUtils.joinStrByArray(column,",");
	}
			
	/**
	 * 获取批量审核信息
	 * */
	public HashMap<String, String> getXjydshxx(User user,String pkValue,String ydlbm){
		XtlcglService xtlcglService = new XtlcglService();
		HashMap<String, String> map = new HashMap<String, String>();
		//查询用户所有岗位
		List<HashMap<String, String>> yhgwList = xtlcglService.getYhgwList(GlobalsVariable.GNDM_XSXX_XJYDSH+ydlbm, 
																			user.getUserName());
		//查询用户审核意见
		map = xjydglDao.selectYhshyj(yhgwList,user,pkValue);
		return map;
	}
	
	/**
	 * 获取批量审核信息
	 * */
	public HashMap<String, String> getXjydshxx(String xtgwid,String pkValue){
		HashMap<String, String> map = new HashMap<String, String>();
		//查询用户审核意见
		map = xjydglDao.selectXjydshyj(xtgwid,pkValue);
		return map;
	}
	
	/**
	 * 学籍异动审核信息
	 * */
	public List<HashMap<String, String>> getXjydshxxOne(String pkValue){
		return xjydglDao.selectXjydshxx(pkValue);
	}
	
	/**
	 * 学籍异动审核查询
	 * */
	public List<String[]> queryXjydsh(StudentInfoForm model,User user){
		XtlcglService lcglService = new XtlcglService();
		String[] colList = getXjydcxColEn("sh");
		String[] shgwArr = lcglService.getGnshgwxx(GlobalsVariable.GNDM_XSXX_XJYDSH+model.getYdlbdm());
			
		
		return xjydglDao.selectXjydsh(model,user,shgwArr,colList);
	}
	
	/**
	 * 学籍异动申请查询
	 * */
	public List<String[]> queryXjydsqxx(StudentInfoForm model,User user){
		XtlcglService lcglService = new XtlcglService();
		String[] colList = getXjydcxColEn("cx");
		String[] shgwArr = lcglService.getGnshgwxx(GlobalsVariable.GNDM_XSXX_XJYDSH+model.getYdlbdm());
		
		return xjydglDao.selectXjydsqxx(model,user,shgwArr,colList);
	}
	
	
	/**
	 * 学籍异动审核流程查询
	 * */
	public List<String[]> queryXjydlbshlc(StudentInfoForm model){
		return xjydglDao.selectXjydshlc(model);
	}
	
	/**
	 * 获取学籍异动信息
	 * */
	public HashMap<String, String> getXjydshlcxx(String ydlbm){
		HashMap<String, String> map = xjydglDao.selectXjydlbxx(ydlbm);
		map.put("save_shlcid", map.get("shlcid"));
		return map;
	}
	
	/**
	 * 学籍异动审核保存
	 * */
	public boolean modifyXsxjxx(StudentInfoForm model, String pkValue){
		boolean result = false;
		String shjg = model.getShjg();
		XtlcglService lcglService = new XtlcglService();
		
		if("通过".equalsIgnoreCase(shjg)){
			//原始值是需重核 且上级审核的状态是退回，则应将升级审核状态改为需重审
			boolean backFlag = xjydglDao.isBack(model,pkValue);			
			if(backFlag){
				xjydglDao.updateXjydsjshzt(model, pkValue);
			}
			result = xjydglDao.updateXjydsh(model,pkValue);				
			//如果当前用户是最后一级审核，则需修改学生的学籍信息
			boolean last = lcglService.sfzhyjsh(GlobalsVariable.GNDM_XSXX_XJYDSH+model.getYdlbdm(),
												model.getXtgwid(),
												"xg_xsxx_xjydxx_shb");
			if(result && last){
				//修改学籍信息
				xjydglDao.updateXjxx(pkValue);
			}
		}else if("退回".equalsIgnoreCase(shjg)){
			//修改当前用户的审核结果信息
			result = xjydglDao.updateXjydsh(model,pkValue);	
			//修改下一级的审核状态为需重审
//			if(result){
//				xjydglDao.updateXjydxjshzt(model, pkValue);
//			}
		}else{
			//修改当前用户的审核结果信息
			result = xjydglDao.updateXjydsh(model, pkValue);
		}
		return result;
	}
	
	/**
	 * 学籍异动批量审核
	 * */
	public boolean xjydshBatch(StudentInfoForm model){
		boolean result = false;
		String[] pkValue = model.getPrimarykey_cbv();
		
		for(int i=0; i< pkValue.length; i++){
			result = modifyXsxjxx(model, pkValue[i]);
			if (!result) break;
		}
		return result;
	}
	
	/**
	 * 学籍异动取消审核
	 * */
	public boolean xjydqxsh(StudentInfoForm model, User user){
		boolean result = false;
		String[] pkValue = model.getPrimarykey_cbv();
		try{
			for(int i=0; i< pkValue.length; i++){
				result = StandardOperation.update("xg_xsxx_xjydxx_shb", 
										new String[]{"shzt","shr","shsj","shyj"}, 
										new String[]{"未审核","","",""}, 
										"ydxh||xtgwid", 
										pkValue[i]+model.getXtgwid(), 
										user);
				if (!result) break;
			}
		}catch (Exception e) {
			e.printStackTrace();
			result = false;
		}
		return result;
	}
	
	/**
	 * 保存审核流程对应信息
	 * */
	public boolean saveShlcdyxxb(StudentInfoForm model, User user){
		return xjydglDao.saveShlcdyxxb(model,user);
	}
	
	/**
	 * 判断是否可修改学籍异动信息
	 * */
	public boolean sfkxgXjydxx(String ydxh){
		//判断是否有审核通过、不通过的记录
		int yshs = xjydglDao.getXjydyshjls(ydxh);//已审核记录数
		
		return yshs >0 ? false : true;
	}
}
