package xgxt.pjpy.comm.zhcp.jbsz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.xml.XMLReader;
import xgxt.form.SaveForm;
import xgxt.form.User;
import xgxt.pjpy.comm.pjpy.PjpyCommService;
import xgxt.pjpy.comm.pjpy.PjxtszModel;
import xgxt.szdw.xmlg.fdyyp.FdyypService;
import xsgzgl.pjpy.general.PjpyGeneralForm;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 综合素质测评_基本设置_Service类
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

public class ZhcpJbszService extends PjpyCommService {
	
	ZhcpJbszDAO dao = new ZhcpJbszDAO();
	
	/**
	 * 获得德育评分信息
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public HashMap<String, Object> getDypfInfo(ZhcpJbszForm model, User user)
			throws Exception {

		HashMap<String, Object> map = new HashMap<String, Object>();

		// 获得辅导员基本信息
		FdyypService fdyService = new FdyypService();
		String[] colList = new String[] { "zgh", "xm", "xb", "bmmc" };
		String zgh = user.getUserName();
		HashMap<String, String> fdyInfo = fdyService.getFdyxx(colList, zgh);

		// 获得辅导员所带班级信息
		List<HashMap<String, String>> list = dao.getFdyDbList(model, user);

		int defNum = 5;//默认行数
		
		if (list != null && list.size() < defNum) {
			
			int listSize = list.size();//列表行数
			
			for (int i = 0; i < defNum - listSize; i++) {
				HashMap<String, String> spaceMap = new HashMap<String, String>();
				list.add(spaceMap);
			}
		}

		map.putAll(fdyInfo);
		map.put("bjList", list);

		return map;
	}

	/**
	 * 保存德育评分开关
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public Boolean saveDypfKgzt(ZhcpJbszForm model, User user) throws Exception {
		
		ZhcpJbszForm jbszModel = ZhcpJbszForm.zcjbszModel;
		// 职工号
		String zgh = user.getUserName();
		// 评奖学年
		String pjxn = jbszModel.getPjxn();
		// 评奖学期
		String pjxq = jbszModel.getPjxq();
		// 评奖年度
		String pjnd = jbszModel.getPjnd();
		// 班级
		String[] bjdm = model.getBjdm();
		
		// 寝室分配表
		String tableName = "xg_pjpy_dypfszb";
		// 主键
		String pk = "xn||xq||nd||bjdm";
		// 主键值
		ArrayList<String> pkValueList = new ArrayList<String>();
		// 单个字段
		String[] onezd = new String[] { "xn", "xq", "nd","szr" };
		// 批量字段
		String[] arrzd = new String[] { "bjdm", "kgzt" };
				
		for (int i = 0; i < bjdm.length; i++) {
			pkValueList.add(pjxn + pjxq + pjnd + bjdm[i]);
		}
		
		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(tableName);
		saveForm.setPk(pk);
		saveForm.setPkValue(pkValueList.toArray(new String[] {}));
		saveForm.setArrzd(arrzd);
		saveForm.setOnezd(onezd);

		model.setXn(pjxn);
		model.setXq(pjxq);
		model.setNd(pjnd);
		model.setSzr(zgh);
		
		return saveInfoToDb(saveForm, model, user);
	}
	
	/**
	 * 获得综测加分信息列表
	 * 
	 * @author 伟大的骆
	 * @throws Exception //ZhcpJbszForm
	 */
	public List<HashMap<String, Object>> getZcjfInfoList(ZhcpJbszForm model,
			User user) throws Exception {

		List<HashMap<String, Object>> list = new ArrayList<HashMap<String,Object>>();
		
		// 二级项目列表
		model.setXmjb("2");
		List<HashMap<String, String>> lv2List = getZcmrxmList(model);
		
		// 加分项目列表
		List<HashMap<String, String>> jfxmList = dao.getJfxmList(model);
		
		if (lv2List != null && lv2List.size() > 0) {
			
			int count = 0;
			
			for (int i = 0; i < lv2List.size(); i++) {
				//二级项目信息
				HashMap<String, String> lv2Info = lv2List.get(i);
				String xmdm = lv2Info.get("xmdm");//项目代码
				String xmmc = lv2Info.get("xmmc");//项目名称
				
				List<HashMap<String, String>> xmList = new ArrayList<HashMap<String,String>>();
				
				if(jfxmList!=null && jfxmList.size()>0){
					
					int n = 1;

					for (int j = 0; j < jfxmList.size(); j++) {
						// 加分项目信息
						HashMap<String, String> jfxmInfo = jfxmList.get(j);
						String jfxmdm = jfxmInfo.get("xmdm");// 加分项目代码
						String sqfs = jfxmInfo.get("sqfs");// 申请分数
						
						if (xmdm.equalsIgnoreCase(jfxmdm)) {
							jfxmInfo.put("num", String.valueOf(n));
							xmList.add(jfxmInfo);

							if(!Base.isNull(sqfs)){
								count++;
							}
							n++;
						}
					}
				}
				
				//加分项目数
				int jfxmNum = 0;
				String sqly = "";
				if (xmList != null && xmList.size() > 0) {
					jfxmNum = xmList.size();
					sqly=xmList.get(0).get("sqly");
				}
				
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("xmdm", xmdm);
				map.put("xmmc", xmmc);
				map.put("jfxmNum", jfxmNum);
				map.put("count", count);
				map.put("sqly", sqly);
				map.put("xmList", xmList);
				list.add(map);
				
				count = 0;
			}
		}
		
		return list;
	}
	
	/**
	 * 保存综测加分设置
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public Boolean saveZcjfsz(ZhcpJbszForm model, User user) throws Exception {
		
		PjpyGeneralForm jbszModel=PjpyGeneralForm.getJbszModel();
		// 评奖学年
		String pjxn = jbszModel.getPjxn();
		// 评奖学期
		String pjxq = jbszModel.getPjxq();
		// 评奖年度
		String pjnd = jbszModel.getPjnd();
		
		// 寝室分配表
		String tableName = "xg_pjpy_zcxmjfb";
		// 主键
		String pk = "xn||xq||nd";
		// 主键值
		String pkValue = pjxn + pjxq + pjnd;
		// 单个字段
		String[] onezd = new String[] { "xn", "xq", "nd" };
		// 批量字段
		String[] arrzd = new String[] { "xmdm", "jfdm", "jfmc", "jjf", "xxf",
				"sxf" };
		
		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(tableName);
		saveForm.setPk(pk);
		saveForm.setPkValue(new String[] { pkValue });
		saveForm.setArrzd(arrzd);
		saveForm.setOnezd(onezd);

		model.setXn(pjxn);
		model.setXq(pjxq);
		model.setNd(pjnd);
		
		return saveInfoToDb(saveForm, model, user);
	}
	// ======================以上 made by 伟大的骆==============================
	
	// =================以下made by 今天中五百万======================
	/**
	 * 获取树型列表
	 * ZhcpJbszForm model
	 * return List<HashMap<String,String>>
	 */
	public List<HashMap<String,String>>getZctreeList(ZhcpJbszForm model){
		
		//综测项目列表
		List<HashMap<String,String>>zcxmList=dao.getZcxmList(model);

		return zcxmList;
	}
	
	/**
	 * 获取树型列表
	 * ZhcpJbszForm model
	 * return List<HashMap<String,String>>
	 */
	public List<HashMap<String,String>>getZcmrxmList(ZhcpJbszForm model){
		
		//综测项目列表
		List<HashMap<String,String>>zcxmList=dao.getZcmrxmList(model);

		return zcxmList;
	}
	
	/**
	 * 获取上级代码
	 * ZhcpJbszForm model
	 * return List<HashMap<String,String>>
	 */
	public List<HashMap<String,String>>getSjdmList(ZhcpJbszForm model){
		
		return dao.getSjdmList(model);
	}
	
	/**
	 * 获取综测比例类型列表
	 * ZhcpJbszForm model
	 * return HashMap<String,String>
	 */
	public List<HashMap<String,String>>getZcbllxList(ZhcpJbszForm model){
		
		return dao.getZcbllxList(model);
	}
	
	/**
	 * 获取综测项目信息列表
	 * ZhcpJbszForm model
	 * return HashMap<String,String>
	 */
	public List<HashMap<String,String>>getZcxmxxList(ZhcpJbszForm model){
		
		return dao.getZcxmxxList(model);
	}
	
	/**
	 * 保存综测项目名称
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	public boolean upateZcxmmc(ZhcpJbszForm model,User user) throws Exception{
		
		return  dao.updateZcxmmc(model, user);
		
	}
	
	/**
	 * 保存综测分比例
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public boolean saveZcbl(ZhcpJbszForm model,User user) throws Exception{
		
		ZhcpJbszModel jbszModel=new ZhcpJbszModel();
		// 进行数据操作 的表名
		String realTable = "xg_pjpy_zcblb";
		
		String[] arrzd = new String[] { "xmdm","bldm", "bl"};
		String[] onezd = new String[] { "xn","xq","nd"};
		
		int len=model.getXmdmArr().length;
		String[]xmdm=new String[len];
		String[]bldm=new String[len];
		String[]bl=new String[len];
		String[]pkValue=new String[len];
		
		String xn=model.getPjxn();
		String xq=model.getPjxq();
		String nd=model.getPjnd();
		
		xmdm=model.getXmdmArr();
		bldm=model.getBldmArr();
		bl=model.getBlArr();
		
		String pk="xn||xq||nd||xmdm||bldm";
		for(int i=0;i<len;i++){
			pkValue[i]=xn+xq+nd+xmdm[i]+bldm[i];
		}
		
		
		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(realTable);
		saveForm.setPk(pk);
		saveForm.setPkValue(pkValue);
		saveForm.setArrzd(arrzd);
		saveForm.setOnezd(onezd);
		
		jbszModel.setXmdm(xmdm);
		jbszModel.setBldm(bldm);
		jbszModel.setBl(bl);
		jbszModel.setXn(xn);
		jbszModel.setXq(xq);
		jbszModel.setNd(nd);
		
		return  this.saveInfoToDb(saveForm, jbszModel, user);
	}

	
	/**
	 * 综合测评初始化
	 * @param String sql
	 * @return boolean
	 */
	public boolean zhcpIni(ZhcpJbszForm model, User user) {
		DAO dao = DAO.getInstance();
		
		
		String xn = ZhcpJbszForm.zcjbszModel.getPjxn();
		String xq = ZhcpJbszForm.zcjbszModel.getPjxq();
		String nd = ZhcpJbszForm.zcjbszModel.getPjnd();

		String[] input = { xn, xq, nd, model.getRyk() };

		boolean blog = false;
		try {
			blog = dao.runProcuder("{call pro_xg_zhcp_csh(?,?,?,?)}", input);

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return blog;

	}
	
	/**
	 * 复制综测项目
	 * @param model
	 * @param user
	 * @return
	 */
	public boolean copyZcxm(ZhcpJbszForm model,User user){
		//综测周期
		String zczq = XMLReader.getFlowControl("pjpy", "zczq");
		
		//综测详细周期
		String xn=ZhcpJbszForm.zcjbszModel.getPjxn();
		String xq=ZhcpJbszForm.zcjbszModel.getPjxq();
		String nd=ZhcpJbszForm.zcjbszModel.getPjnd();
		//上一综测周期
		String upXn="无";
		String upXq="无";
		String upNd="无";
		if ("xn".equalsIgnoreCase(zczq)) {
			upXn=ZhcpJbszForm.zcjbszModel.getUpXn();
		} else if ("xq".equalsIgnoreCase(zczq)) {
			upXn=ZhcpJbszForm.zcjbszModel.getUpXn();
			upXq=ZhcpJbszForm.zcjbszModel.getUpXq();
		} else if ("nd".equalsIgnoreCase(zczq)) {
			//周期为年度时,获取上一年度信息
			upNd = ZhcpJbszForm.zcjbszModel.getUpNd();
		}
		
		//判断综测项目表中是否存在上一周期的信息
		if(checkZcByZq(upXn,upXq,upNd)){
			boolean blog=false;
			try {
				DAO dao=DAO.getInstance();
				String[]input={xn,xq,nd,upXn,upXq,upNd};
				blog= dao.runProcuder("{call pro_xg_zhcp_copysz(?,?,?,?,?,?)}",input);
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
			return blog;
		}else{
			
			return false;
		}
	}
	
	/**
	 * 上一综测学年
	 * @param xn
	 * @return
	 */
	public String getUpxn(String xn) {

		String upXn = "";
		String xnSta = xn.substring(0, 4);
		String xnEnd = xn.substring(5, 9);
		upXn = String.valueOf(Integer.parseInt(xnSta) - 1) + "-"
				+ String.valueOf(Integer.parseInt(xnEnd) - 1);
		return upXn;
	}
	
	/**
	 * 上一综测学期
	 * @return
	 */
	public HashMap<String,String> getUpxq() {
		
		HashMap<String,String>upXqMap=dao.getUpXqxx();
		
		return upXqMap;
	}
	
	/**
	 * 上一综测年度
	 * @return
	 */
	public String getUpnd(String nd) {

		String upNd = "";
		
		int ndInt=Integer.parseInt(nd);
		ndInt--;
		upNd=String.valueOf(ndInt);
		
		return upNd;
	}
	
	
	/**
	 * 判断特定周期是否存在综测信息
	 * @param model
	 * @param user
	 * @return
	 */
	public boolean checkZcByZq(String upXn, String upXq, String upNd) {
		
		String num = dao.checkZcByZq(upXn, upXq, upNd);
		
		if ("0".equalsIgnoreCase(num)) {
		
			return false;
		
		} else {
		
			return true;
		
		}
	}
	
	// =================以上made by 今天中五百万======================


	/**
	 * 清除当前评奖周期的综测项目
	 * author 鲁大
	 */
	public boolean removeZcxm(){
		DAO dao=DAO.getInstance();
		String sql = "delete from xg_pjpy_zcxmb where xn=?";
		
		try {
			return dao.runUpdate(sql, new String[]{PjxtszModel.pjxtszModel.getPjxn()});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}


}