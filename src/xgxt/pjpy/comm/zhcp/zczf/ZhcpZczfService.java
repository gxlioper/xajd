package xgxt.pjpy.comm.zhcp.zczf;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.xml.XMLReader;
import xgxt.form.User;
import xgxt.pjpy.comm.pjpy.PjpyCommService;
import xgxt.utils.date.DateUtils;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 综合素质测评_综测总分_Service类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author 伟大的骆
 * @version 1.0
 */

public class ZhcpZczfService extends PjpyCommService {
	
	ZhcpZczfDAO dao = new ZhcpZczfDAO();
	
	/**
	 * 获取综测总分表头字段
	 * @param model
	 * @param user
	 * @return List<HashMap<String, String>>
	 */
	public List<HashMap<String, String>> getTop(ZhcpZczfForm model, User user) {
		String zcpm=model.getZcpm();
		String zypm=model.getZypm();
		DAO commDao=DAO.getInstance();
		//获取配置表中配置的字段、字段名称
		List<HashMap<String,String>>colList=dao.getZczfTop(model, user);
		//字段代码（COLLIST）
		List<String>colArr=new ArrayList<String>();
		//字段名称（TOP）
		List<String>topArr=new ArrayList<String>();
		//获取扩展字段信息
		List<HashMap<String,String>>kzzdList=getKzzdList(user);
	
		colArr.add("pkValue");
		topArr.add("主键");
		
		colArr.add("xh");
		topArr.add("学号");
		
		colArr.add("xm");
		topArr.add("姓名");
		
		colArr.add("nj");
		topArr.add("年级");
		
		colArr.add("xymc");
		topArr.add(Base.YXPZXY_KEY);
		
		colArr.add("zymc");
		topArr.add("专业");
		
		colArr.add("bjmc");
		topArr.add("班级");
		
		for(int i=0;i<colList.size();i++){
			HashMap<String,String>outPutMap=colList.get(i);
			colArr.add(outPutMap.get("xmdm"));
			topArr.add(outPutMap.get("xmmc"));
		}
		
		for(int i=0;i<kzzdList.size();i++){
			HashMap<String,String>kzzdMap=kzzdList.get(i);
			colArr.add(kzzdMap.get("kzzd"));
			topArr.add(kzzdMap.get("xsmc"));
		}
		
		if("1".equalsIgnoreCase(zcpm)
				|| "5".equalsIgnoreCase(zcpm)
				|| "6".equalsIgnoreCase(zcpm)
				|| "7".equalsIgnoreCase(zcpm)){
			colArr.add("zcfnjxypm");
			topArr.add("年级"+Base.YXPZXY_KEY+"排名");
		}
		
		if("2".equalsIgnoreCase(zcpm)
				|| "4".equalsIgnoreCase(zcpm)
				|| "6".equalsIgnoreCase(zcpm)
				|| "7".equalsIgnoreCase(zcpm)){
			colArr.add("zcfnjzypm");
			topArr.add("年级专业排名");
		}
		
		if("3".equalsIgnoreCase(zcpm)
				|| "5".equalsIgnoreCase(zcpm)
				|| "4".equalsIgnoreCase(zcpm)
				|| "7".equalsIgnoreCase(zcpm)){
			colArr.add("zcfbjpm");
			topArr.add("班级排名");
		}
		
		if("1".equalsIgnoreCase(zypm)
				|| "5".equalsIgnoreCase(zypm)
				|| "6".equalsIgnoreCase(zypm)
				|| "7".equalsIgnoreCase(zypm)){
			colArr.add("zyfnjxypm");
			topArr.add("智育分年级"+Base.YXPZXY_KEY+"排名");
		}
		
		if("2".equalsIgnoreCase(zypm)
				|| "4".equalsIgnoreCase(zypm)
				|| "6".equalsIgnoreCase(zypm)
				|| "7".equalsIgnoreCase(zypm)){
			colArr.add("zyfnjzypm");
			topArr.add("智育分年级专业排名");
		}
		
		if("3".equalsIgnoreCase(zypm)
				|| "5".equalsIgnoreCase(zypm)
				|| "4".equalsIgnoreCase(zypm)
				|| "7".equalsIgnoreCase(zypm)){
			colArr.add("zyfbjpm");
			topArr.add("智育分班级排名");
		}
		return commDao.arrayToList(colArr.toArray(new String[]{}), topArr.toArray(new String[]{}));
	}
	
	
	/**
	 * 获取综测分模块列选字段
	 * @param model
	 * @param user
	 * @return List<HashMap<String, String>>
	 */
	public List<HashMap<String, String>> getKindChoose(ZhcpZczfForm model, User user) {
		
		String zcpm=model.getZcpm();
		String zypm=model.getZypm();
		DAO commDao=DAO.getInstance();
		//获取配置表中配置的字段、字段名称
		List<HashMap<String,String>>colList=dao.getZczfTop(model, user);
		//字段代码（COLLIST）
		List<String>colArr=new ArrayList<String>();
		//字段名称（TOP）
		List<String>topArr=new ArrayList<String>();
		//获取扩展字段信息
		List<HashMap<String,String>>kzzdList=getKzzdList(user);
		// ------------------------默认显示字段 begin------------------------------
		colArr.add("xh");
		topArr.add("学号");
		
		colArr.add("xm");
		topArr.add("姓名");
		
		colArr.add("nj");
		topArr.add("年级");
		
		colArr.add("xymc");
		topArr.add(Base.YXPZXY_KEY);
		
		colArr.add("zymc");
		topArr.add("专业");
		
		colArr.add("bjmc");
		topArr.add("班级");
		// ------------------------默认显示字段 end------------------------------
		
		for(int i=0;i<colList.size();i++){
			HashMap<String,String>outPutMap=colList.get(i);
			colArr.add(outPutMap.get("xmdm"));
			topArr.add(outPutMap.get("xmmc"));
		}
		
		for(int i=0;i<kzzdList.size();i++){
			HashMap<String,String>kzzdMap=kzzdList.get(i);
			colArr.add(kzzdMap.get("kzzd"));
			topArr.add(kzzdMap.get("xsmc"));
		}
		
		// ---------------------综测排名信息 根据flowControl.xml文件配置取 begin---------------------
		if("1".equalsIgnoreCase(zcpm)
				|| "5".equalsIgnoreCase(zcpm)
				|| "6".equalsIgnoreCase(zcpm)
				|| "7".equalsIgnoreCase(zcpm)){
			colArr.add("zcfnjxypm");
			topArr.add("年级"+Base.YXPZXY_KEY+"排名");
		}
		
		
		
		if("2".equalsIgnoreCase(zcpm)
				|| "4".equalsIgnoreCase(zcpm)
				|| "6".equalsIgnoreCase(zcpm)
				|| "7".equalsIgnoreCase(zcpm)){
			colArr.add("zcfnjzypm");
			topArr.add("年级专业排名");
		}
		
		if("3".equalsIgnoreCase(zcpm)
				|| "5".equalsIgnoreCase(zcpm)
				|| "4".equalsIgnoreCase(zcpm)
				|| "7".equalsIgnoreCase(zcpm)){
			colArr.add("zcfbjpm");
			topArr.add("班级排名");
		}
		
		if("1".equalsIgnoreCase(zypm)
				|| "5".equalsIgnoreCase(zypm)
				|| "6".equalsIgnoreCase(zypm)
				|| "7".equalsIgnoreCase(zypm)){
			colArr.add("zyfnjxypm");
			topArr.add("智育分年级"+Base.YXPZXY_KEY+"排名");
		}
		
		if("2".equalsIgnoreCase(zypm)
				|| "4".equalsIgnoreCase(zypm)
				|| "6".equalsIgnoreCase(zypm)
				|| "7".equalsIgnoreCase(zypm)){
			colArr.add("zyfnjzypm");
			topArr.add("智育分年级专业排名");
		}
		
		if("3".equalsIgnoreCase(zypm)
				|| "5".equalsIgnoreCase(zypm)
				|| "4".equalsIgnoreCase(zypm)
				|| "7".equalsIgnoreCase(zypm)){
			colArr.add("zyfbjpm");
			topArr.add("智育分班级排名");
		}
		// ---------------------综测排名信息 根据flowControl.xml文件配置取 end---------------------
		
		return commDao.arrayToList(colArr.toArray(new String[]{}), topArr.toArray(new String[]{}));
	}
	
	/**
	 * 获取指定用户已设置显示列
	 * @param model
	 * @param user
	 * @return List<HashMap<String, String>>
	 */
	public List<HashMap<String, String>> getCheckKind(ZhcpZczfForm model, User user) {
		// 获取指定用户选中的列
		HashMap<String, String> kind = dao.getCheckKind(model, user);
		List<HashMap<String, String>> checkKind = new ArrayList<HashMap<String, String>>();
		// 需显示字段
		String xszd = kind.get("xxszd");
		String[] xszdArr = null;
		if (!Base.isNull(xszd)) {
			xszdArr = xszd.split(",");
			for (int i = 0; i < xszdArr.length; i++) {
				HashMap<String, String> kindMap = new HashMap<String, String>();
				kindMap.put("zd", xszdArr[i]);
				checkKind.add(kindMap);
			}
		}
		return checkKind;
	}
	
	
	/**
	 * 获取综测总分信息
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public List<String[]>getZhcpZczf(ZhcpZczfForm model,User user) throws Exception{
		
		String[]colList=severTop(getToptr(model,user),"en");
		
		return dao.getZhcpZczf(model, user, colList);
	}
	
	/**
	 * 分离top列表 根据获取类型获取（en,cn）
	 * 
	 * @param topList
	 * @param hqlx
	 * @return List<HashMap<String, String>>
	 */
	public String[] severTop(List<HashMap<String, String>> topList, String hqlx) {

		List<String> outList = new ArrayList<String>();
		for (int i = 0; i < topList.size(); i++) {
			HashMap<String, String> topMap = topList.get(i);
			outList.add(topMap.get(hqlx));
		}
		return outList.toArray(new String[] {});
	}
	
	/**
	 * 保存列选
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public boolean saveKindChoose(ZhcpZczfForm model,User user) throws Exception{
	
		DAO commDao=DAO.getInstance();
		String yhm=user.getUserName();
		String path="zhcp_zczf_zccx.do";
		String yhlx="tea";
		StringBuilder xszd=new StringBuilder();
		
		String []bxszd={"xh","xm"};
		String []xszdArr=null;
		if("stu".equalsIgnoreCase(user.getUserType())){
			yhlx="stu";
		}
		
		xszdArr=commDao.unionArray(bxszd, model.getXszdArr());
		for(int i=0;i<xszdArr.length;i++){
			if(i!=0){
				xszd.append(",");
			}
			xszd.append(xszdArr[i]);
		}
		
		return checkBoolean(dao.saveKindChoose(yhm, yhlx, xszd.toString(), path));
	}
	
	/**
	 * 判断批量操作是否成功
	 * @param returnV
	 * @return
	 */
	public boolean checkBoolean(int []returnV){
		boolean blog=true;
		for(int i=0;i<returnV.length;i++){
			if(returnV[i]==0){
				blog=false;
			}
		}
		return blog;	
	}
	
	/**
	 * 获取表头显示字段
	 * @param model
	 * @param user
	 * @return
	 */
	public List<HashMap<String, String>> getToptr(ZhcpZczfForm model, User user) {
		//获取指定用户已设置显示列
		List<HashMap<String, String>> kindList = getCheckKind(model, user);
		List<HashMap<String, String>> top=getTop(model,user);
		List<HashMap<String, String>> topTr=new ArrayList<HashMap<String,String>>();
		HashMap<String, String>map=new  HashMap<String, String>();
		map.put("en", "pkValue");
		map.put("cn", "主键");
		topTr.add(map);
		
		//取交集
		if (kindList != null && kindList.size() > 0) {
			for (int i = 0; i < kindList.size(); i++) {
				HashMap<String, String> kindMap = kindList.get(i);
				HashMap<String, String> topTrMap = new HashMap<String, String>();
				for (int j = 0; j < top.size(); j++) {
					HashMap<String, String> topMap = top.get(j);

					if (kindMap.get("zd").equalsIgnoreCase(topMap.get("en"))) {
						topTrMap.put("en", topMap.get("en"));
						topTrMap.put("cn", topMap.get("cn"));
						topTr.add(topTrMap);
						break;
					}
				}
				
			}
			
		}else{
			
			return top;
		}
		
		return topTr;
	}
	
	/**
	 * 获取最底级项目信息
	 * @param model
	 * @param user
	 * @return
	 */
	public List<HashMap<String,String>>getDjxmList(ZhcpZczfForm model,User user){
		return dao.getDjxmList(model, user);
	}
	
	/**
	 * 获取最底级项目计算
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception 
	 */
	public boolean djxmjs(ZhcpZczfForm model,User user) throws Exception{
		return dao.djxmjs(model, user);
	}
	
	/**
	 * 获取综测比例代码表中比列类型
	 * @param model
	 * @param user
	 * @return List<HashMap<String,String>>
	 */
	public List<HashMap<String,String>>getBldmList(ZhcpZczfForm model,User user){

		return dao.getBldmList(model, user);
	}
	
	/**
	 * 获取有子项目的项目列表
	 * @param model
	 * @param user
	 * @return List<HashMap<String,String>>
	 */
	public List<HashMap<String,String>>getXjsxmList(ZhcpZczfForm model,User user){

		return dao.getXjsxmList(model, user);
	}
	
	/**
	 * 获取综测项目详细配置信息
	 * @param model
	 * @param user
	 * @return
	 */
	public List<HashMap<String,String>>getZcxmInfo(ZhcpZczfForm model,User user){
		
		return dao.getZcxmInfo(model, user);
		
	}
	
	/**
	 * 综测项目分计算
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public boolean zcxmjs(ZhcpZczfForm model,User user) throws Exception {
		
		boolean blog=true;
		// ====================计算最低级别的项目分=============================
		//获取需要计算的最底级别项目
//		List<HashMap<String,String>>djxmList=getDjxmList(model,user);
//		boolean blog=true;
//		for(int i=0;i<djxmList.size();i++){
//			HashMap<String,String>djxmMap=djxmList.get(i);
//			try {
//				model.setXmdm(djxmMap.get("xmdm"));
//				model.setLyb(djxmMap.get("lyb"));
//				model.setZd(djxmMap.get("zd"));
//				model.setGlxn(djxmMap.get("glxn"));
//				model.setGlxq(djxmMap.get("glxq"));
//				model.setGlnd(djxmMap.get("glnd"));
//				blog=djxmjs(model,user);
//			} catch (Exception e) {
//				
//				return false;	
//			}
//		}
		// ====================计算最低级别的项目分 end=========================
		
		// =============有子项目的项目分计算===================
		List<HashMap<String,String>>zcjsList=dao.getZcjsSql(model, user);
		
		blog=zcxmfjs(zcjsList);
		// =============有子项目的项目分计算end===================
		return blog;
	}
	
	/**
	 * 有子项目的项目分计算
	 * @param zcjsList
	 * @return boolean
	 */
	public boolean zcxmfjs(List<HashMap<String,String>>zcjsList){
		
		List<HashMap<String, String>> xmjbList = dao.getXmjbList();
		
		boolean flag = false;
		
		for (int i = 0; i < xmjbList.size(); i++) {

			HashMap<String, String> xmjbMap = xmjbList.get(i);
			List<String> sql = new ArrayList<String>();
			for (int j = 0; j < zcjsList.size(); j++) {

				
				HashMap<String, String> zcjsMap = zcjsList.get(j);

				if (xmjbMap.get("xmjb").equalsIgnoreCase(zcjsMap.get("xmjb"))
						&& Base.isNull(zcjsMap.get("ytj"))) {
					zcjsMap.put("ytj", "yes");
					sql.add(zcjsMap.get("sql"));
				}
			}

			try {

				flag = dao.saveArrDate(sql.toArray(new String[] {}));

			} catch (Exception e) {

				return false;

			}

		}

		return flag;	
	}
	
	/**
	 * 计算综测排名
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public boolean getPlace(ZhcpZczfForm model,User user) throws Exception{
		//根据所选择的排名计算方式计算排名
		String[]pmjs=model.getPmjs();
		boolean blog=false;
		for(int i=0;i<pmjs.length;i++){
			//年级学院排名
			if("njxy".equalsIgnoreCase(pmjs[i])){
				blog=xypmjs(model,user);
			}
			//年级专业排名
			else if("njzy".equalsIgnoreCase(pmjs[i])){
				blog=zypmjs(model,user);
			}
			//班级排名
			else if("bj".equalsIgnoreCase(pmjs[i])){
				blog=bjpmjs(model,user);
			}
		}
	
		return blog;
	}
	
	/**
	 * 计算智育排名
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public boolean getZyPlace(ZhcpZczfForm model,User user) throws Exception{

		//根据所选择的排名计算方式计算排名
		String[]zypmjs=model.getZypmjs();
		boolean blog=false;
		for(int i=0;i<zypmjs.length;i++){
			//年级学院排名
			if("njxy".equalsIgnoreCase(zypmjs[i])){
				blog=xyZypmjs(model,user);
			}
			//年级专业排名
			else if("njzy".equalsIgnoreCase(zypmjs[i])){
				blog=zyZypmjs(model,user);
			}
			//班级排名
			else if("bj".equalsIgnoreCase(zypmjs[i])){
				blog=bjZypmjs(model,user);
			}
		}
	
		return blog;
	}
	
	
	/**
	 * 班级排名计算
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public boolean bjpmjs(ZhcpZczfForm model, User user) throws Exception {

		// 将班级排名差入临时表
		boolean flag = dao.bjpmjs(model, user);
		if (flag) {
			flag = dao.updateBjpm(model, user);
		}
		return flag;
	}
	
	/**
	 * 年级学院排名计算
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public boolean xypmjs(ZhcpZczfForm model, User user) throws Exception {

		// 将班级排名差入临时表
		boolean flag = dao.njxypmjs(model, user);
		if (flag) {
			flag = dao.updateXypm(model, user);
		}
		return flag;
	}
	
	/**
	 * 年级专业排名计算
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public boolean zypmjs(ZhcpZczfForm model, User user) throws Exception {

		// 将班级排名差入临时表
		boolean flag = dao.njzypmjs(model, user);
		if (flag) {
			flag = dao.updateZypm(model, user);
		}
		return flag;
	}
	
	/**
	 * 年级学院排名计算
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public boolean xyZypmjs(ZhcpZczfForm model, User user) throws Exception {

		// 将班级排名差入临时表
		boolean flag = dao.njxyZypmjs(model, user);
		if (flag) {
			flag = dao.updateNjXyZypm(model, user);
		}
		return flag;
	}
	

	/**
	 * 班级排名计算
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public boolean bjZypmjs(ZhcpZczfForm model, User user) throws Exception {

		// 将班级排名差入临时表
		boolean flag = dao.bjZypmjs(model, user);
		if (flag) {
			flag = dao.updateBjZypm(model, user);
		}
		return flag;
	}
	
	
	/**
	 * 年级专业排名计算
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public boolean zyZypmjs(ZhcpZczfForm model, User user) throws Exception {

		// 将班级排名差入临时表
		boolean flag = dao.njzyZypmjs(model, user);
		if (flag) {
			flag = dao.updateNjZyZypm(model, user);
		}
		return flag;
	}
	
	/**
	 * 获取综测分相关信息
	 * 
	 * @param xh
	 * @return List<HashMap<String, String>>
	 */
	public List<HashMap<String, String>> getZcfInfoList(String xh) {
		return dao.getZcfInfoList(xh);
	}
	
	/**
	 * 获得综测项目扩展字段信息（本评奖学期）
	 * 
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getKzzdList(User user) {

		return dao.getKzzdList(user);
	}
	
	
	/**
	 * 将打印数据转化为列表，原创李涛涛。
	 * @param xn
	 * @param xq
	 * @param bjdm
	 * @return
	 * @throws Exception
	 */
	public Object[]  getPrintData(String xn,String xq,String bjdm) throws Exception{
		
		Object[] data = new Object[3]; 
		
		String[] xhValue = dao.getStuByBjdm(bjdm);
		String[] kcvalue = dao.getBxkcArr(xn, xq, bjdm);
		List<String[]> cjList = dao.getBxcjList(xn, xq, bjdm);
		List<String[]> zhcpList = dao.getZhcpList(xn, xq, bjdm);
		List<HashMap<String,String>> rs = new ArrayList<HashMap<String,String>>();
		
		
		//将查询数据转化为列表
		if (cjList != null) {
			if (xhValue != null) {
				for (int j = 0; j < xhValue.length; j++) {
					HashMap<String, String> oneRs = new HashMap<String, String>();
					int index = 0;
					for (int k = 0; k < kcvalue.length; k++) {
						for (int l = 0; l < cjList.size(); l++) {
							String[] oneData = cjList.get(l);
							if (oneData != null && oneData.length == 6) {
								if (kcvalue[k].equalsIgnoreCase(oneData[2]) && xhValue[j].equalsIgnoreCase(oneData[0])) {

									oneRs.put("xh", oneData[0]);
									oneRs.put("xm", oneData[1]);
									oneRs.put("kccj" + (index + 1), oneData[3]);
									index++;

									if (zhcpList != null) {
										for (int m=0;m<zhcpList.size();m++) {
											String[] zhcpOne = zhcpList.get(m);
											if (zhcpOne != null && zhcpOne.length==7) {
												if (xhValue[j].equalsIgnoreCase(zhcpOne[0])) {
													oneRs.put("zcjzf", zhcpOne[1]);
													oneRs.put("tcjzf", zhcpOne[2]);
													oneRs.put("dcjzf", zhcpOne[3]);
													oneRs.put("kpf", zhcpOne[4]);
													oneRs.put("cxdj", zhcpOne[5]);
													oneRs.put("bjpm", zhcpOne[6]);
													break;
												}
											}
										}
									}
									break;

								}
							}
						}
					}
					if (!oneRs.isEmpty()){
						rs.add(oneRs);
					}
				}
			}
		}
		
		//表头班级日期信息
		HashMap<String,String> map = new HashMap<String,String>();
		if (cjList != null && cjList.size() > 0) {
			map.put("xymc", cjList.get(0) != null && cjList.get(0).length == 6 ? cjList.get(0)[4] : "");
			map.put("bjmc", cjList.get(0) != null && cjList.get(0).length == 6 ? cjList.get(0)[5] : "");
		}
		map.put("year", DateUtils.getYear()+" 年" + DateUtils.getMonth() + " 月" + DateUtils.getDayOfMonth() + " 日");
		map.put("kclen", kcvalue != null ? String.valueOf(kcvalue.length) : "1");
		map.put("len", kcvalue != null ? String.valueOf(kcvalue.length+1) : "1");
		map.put("trlen", kcvalue != null ? String.valueOf(kcvalue.length+1 + 12) : "1");
		
		//学生课程信息
		HashMap<String, String> rskc = new HashMap<String, String>();
		if(kcvalue == null){
			for(int i =0;i <13; i++){
				rskc.put("kc"+(i+1), "");
			}
		}else{
			for(int i =0;i <kcvalue.length; i++){
				rskc.put("kc"+(i+1), kcvalue[i]);
			}
		}
		
		data[0] = rs;
		data[1] = map;
		data[2] = rskc;
		return data;
	}
	
}