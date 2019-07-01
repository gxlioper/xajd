package xgxt.pjpy.comm.zhcp.zcjf;

import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import common.exception.SystemException;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xgxt.form.RequestForm;
import xgxt.form.SaveForm;
import xgxt.form.User;
import xgxt.gygl.qsgl.GyglQsglForm;
import xgxt.pjpy.comm.pjpy.PjpyCommService;
import xgxt.pjpy.comm.zhcp.jbsz.ZhcpJbszForm;
import xgxt.pjpy.comm.zhcp.jbsz.ZhcpJbszService;
import xgxt.pjpy.zjcm.ZjcmPjpyModel;
import xgxt.studentInfo.service.XsxxglService;
import xsgzgl.comm.BasicDAO;
import xsgzgl.comm.BasicModel;
import xsgzgl.pjpy.general.PjpyGeneralForm;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 综合素质测评_综测加分_Service类
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

public class ZhcpZcjfService extends PjpyCommService {
	
	ZhcpZcjfDAO dao = new ZhcpZcjfDAO();
	
	BasicDAO basicDao= new BasicDAO();
	
	/**
	 * 获得综测加分申请信息
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public HashMap<String, Object> getZcjfSqInfo(ZhcpZcjfForm model, User user)
			throws Exception {
		
		ZhcpJbszForm jbszModel = ZhcpJbszForm.zcjbszModel;
		// 人员库
		String ryk = model.getRyk();
		// 用户类型
		String userType = user.getUserType();
		// 申请者
		String xh = "stu".equalsIgnoreCase(userType) ? user.getUserName()
				: model.getXh();
		// 评奖学年
		String pjxn = jbszModel.getPjxn();
		// 评奖学期
		String pjxq = jbszModel.getPjxq();
		// 评奖年度
		String pjnd = jbszModel.getPjnd();

		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("czr", user.getUserName());
		
		//学生基本信息
		HashMap<String, String> stuInfo = new HashMap<String, String>();

		if (!Base.isNull(xh)) {
			
			if ("ss".equalsIgnoreCase(ryk)) {// 实时人员库
				XsxxglService stuService = new XsxxglService();
				stuInfo = stuService.selectStuinfo(xh);
			} else {
				HashMap<String,String>valueMap=new HashMap<String,String>();
				BasicModel basicModel=new BasicModel();
				basicModel.setTableName("xg_view_pjpy_pjryk");
				valueMap.put("query", " and xh=? ");
				basicModel.setQueryV(new String[]{xh});
				basicModel.setValueMap(valueMap);
				stuInfo = basicDao.getBasicData(basicModel);
			}

			map.putAll(stuInfo);

			ZhcpJbszService jbszService = new ZhcpJbszService();

			// 加分项目
			jbszModel.setXh(xh);
			List<HashMap<String, Object>> jfxmList = jbszService
					.getZcjfInfoList(jbszModel, user);

			map.put("zcxmList", jfxmList);

			// 加分申请信息
			String tableName = "xg_pjpy_zcjfsqb";
			String pk = "xn||xq||nd||xh";
			String pkValue = pjxn + pjxq + pjnd + xh;
			String[] colList = new String[] { "shzt1", "shr1", "shsj1",
					"shyj1", "sftj1" };

			HashMap<String, String> sqInfo = getRsInfo(tableName, pk, pkValue,
					colList);
			map.putAll(sqInfo);
		}
		
		return map;
	}
	
	/**
	 * 保存综测加分申请
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public Boolean saveZcjf(ZhcpZcjfForm model, User user,String lx,
			HttpServletRequest request) throws Exception {

		boolean flag = false;

		if ("sq".equalsIgnoreCase(lx)) {// 申请操作

			flag = saveZcjfsq(model, user, request);

			if (flag) {
				// 保存综测加分分数
				flag = saveZcjffs(model, user);
			}
		} else if ("sh".equalsIgnoreCase(lx)) {// 审核操作

			flag = updateZcjfsq(model, user);

			if (flag) {
				// 保存综测加分审核分数
				flag = updateZcjffs(model, user);
			}
		} else if ("tj".equalsIgnoreCase(lx)) {// 提交操作
			model.setSftj1("是");
			flag = updateZcjfsq(model, user);
			
//			if (flag) {
//				// 保存综测加分审核分数
//				flag = updateZcjffs(model, user);
//			}
			
			model.setCheckVal(null);
			model.setCheckVal(new String[]{model.getXh()});
			if(flag){
				//获取需要提交的分数
				List<HashMap<String,String>>tjzfList=dao.getTjZf(model, user);
				//更新数据
				flag=dao.zsjfCommit(tjzfList);
			}
		} else if ("pltj".equalsIgnoreCase(lx)) {// 批量提交
			model.setSftj1("是");
			flag = updateZcjfsqBatch(model, user);
			if(flag){
				//获取需要提交的分数
				List<HashMap<String,String>>tjzfList=dao.getTjZf(model, user);
				//更新数据
				flag=dao.zsjfCommit(tjzfList);
			}
		}

		return flag;
	}
	
	/**
	 * 保存综测加分申请
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public Boolean saveZcjfsq(ZhcpZcjfForm model, User user,
			HttpServletRequest request) throws Exception {

		ZhcpJbszForm jbszModel = ZhcpJbszForm.zcjbszModel;
		// 评奖学年
		String pjxn = jbszModel.getPjxn();
		// 评奖学期
		String pjxq = jbszModel.getPjxq();
		// 评奖年度
		String pjnd = jbszModel.getPjnd();

		// 用户类型
		String userType = user.getUserType();
		// 申请者
		String xh = "stu".equalsIgnoreCase(userType) ? user.getUserName()
				: model.getXh();

		// 寝室分配表
		String tableName = "xg_pjpy_zcjfsqb";
		// 主键
		String pk = "xn||xq||nd||xh";
		// 主键值
		String pkValue = pjxn + pjxq + pjnd + xh;
		// 单个字段
		String[] onezd = new String[] { "xn", "xq", "nd", "xh" };

		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(tableName);
		saveForm.setPk(pk);
		saveForm.setPkValue(new String[] { pkValue });
		saveForm.setOnezd(onezd);

		model.setXh(xh);
		model.setXn(pjxn);
		model.setXq(pjxq);
		model.setNd(pjnd);

		return saveInfoToDb(saveForm, model, request);
	}
	
	/**
	 * 修改综测加分申请
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public Boolean updateZcjfsq(ZhcpZcjfForm model, User user) throws Exception {

		PjpyGeneralForm jbszModel=PjpyGeneralForm.getJbszModel();
		// 评奖学年
		String pjxn = jbszModel.getPjxn();
		// 评奖学期
		String pjxq = jbszModel.getPjxq();
		// 评奖年度
		String pjnd = jbszModel.getPjnd();
		// 申请者
		String xh = model.getXh();
		// 审核人
		String shr = user.getUserName();
		// 审核人
		String shzt = "通过";
		// 审核时间
		String shsj = getNowTime("YYYYMMDD");

		// 寝室分配表
		String tableName = "xg_pjpy_zcjfsqb";
		// 主键
		String pk = "xn||xq||nd||xh";
		// 主键值
		String pkValue = pjxn + pjxq + pjnd + xh;
		// 单个字段
		String[] onezd = new String[] { "shzt1", "shr1", "shsj1", "sftj1" };

		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(tableName);
		saveForm.setPk(pk);
		saveForm.setPkValue(new String[] { pkValue });
		saveForm.setOnezd(onezd);

		model.setShzt1(shzt);
		model.setShr1(shr);
		model.setShsj1(shsj);

		return updateInfoInDb(saveForm, model, user);
	}
	
	/**
	 * 修改综测加分申请
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public Boolean updateZcjfsqBatch(ZhcpZcjfForm model, User user) throws Exception {

		ZhcpJbszForm jbszModel = ZhcpJbszForm.zcjbszModel;
		// 评奖学年
		String pjxn = jbszModel.getPjxn();
		// 评奖学期
		String pjxq = jbszModel.getPjxq();
		// 评奖年度
		String pjnd = jbszModel.getPjnd();
		// 审核人
		String shr = user.getUserName();
		// 审核人
		String shzt = "通过";
		// 审核时间
		String shsj = getNowTime("YYYYMMDD");
		// 复选框
		String[] checkVal = model.getCheckVal();

		// 寝室分配表
		String tableName = "xg_pjpy_zcjfsqb";
		// 主键
		String pk = "xn||xq||nd||xh";
		// 主键值
		ArrayList<String> pkValue = new ArrayList<String>();//pjxn + pjxq + pjnd + xh;
		// 单个字段
		String[] onezd = new String[] { "shzt1", "shr1", "shsj1", "sftj1" };

		if (checkVal != null && checkVal.length > 0) {
			for (int i = 0; i < checkVal.length; i++) {
				pkValue.add(pjxn + pjxq + pjnd + checkVal[i]);
			}
		}

		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(tableName);
		saveForm.setPk(pk);
		saveForm.setPkValue(pkValue.toArray(new String[] {}));
		saveForm.setOnezd(onezd);

		model.setShzt1(shzt);
		model.setShr1(shr);
		model.setShsj1(shsj);

		return updateInfoInDb(saveForm, model, user);
	}
	
	/**
	 * 保存综测加分分数
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public Boolean saveZcjffs(ZhcpZcjfForm model, User user) throws Exception {

		PjpyGeneralForm jbszModel=PjpyGeneralForm.getJbszModel();
		// 评奖学年
		String pjxn = jbszModel.getPjxn();
		// 评奖学期
		String pjxq = jbszModel.getPjxq();
		// 评奖年度
		String pjnd = jbszModel.getPjnd();

		// 用户类型
		String userType = user.getUserType();
		// 申请者
		String xh = "stu".equalsIgnoreCase(userType) ? user.getUserName()
				: model.getXh();

		// 寝室分配表
		String tableName = "xg_pjpy_zcjffsb";
		// 主键
		String pk = "xn||xq||nd||xh";
		// 主键值
		String pkValue = pjxn + pjxq + pjnd + xh;
		// 单个字段
		String[] onezd = new String[] { "xn", "xq", "nd", "xh" };
		// 批量字段
		String[] arrzd = new String[] { "xmdm", "jfdm", "sqfs", "sqly" };
		//		 非空字段
		String[] notnull = new String[] { "sqfs" };

		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(tableName);
		saveForm.setPk(pk);
		saveForm.setPkValue(new String[] { pkValue });
		saveForm.setArrzd(arrzd);
		saveForm.setOnezd(onezd);
		saveForm.setNotnull(notnull);

		model.setXh(xh);
		model.setXn(pjxn);
		model.setXq(pjxq);
		model.setNd(pjnd);

		return saveInfoToDb(saveForm, model, user);
	}
	
	/**
	 * 修改综测加分分数
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public Boolean updateZcjffs(ZhcpZcjfForm model, User user) throws Exception {

		return dao.updateZcjffs(model, user);
	}
	
	/**
	 * 获得加分审核列表
	 * 
	 * @author 伟大的骆
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public ArrayList<String[]> getJfshList(ZhcpZcjfForm model, User user,
			List<HashMap<String, String>> xmList)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		// 人员库
		String ryk = model.getRyk();

		ArrayList<String[]> rsLisr = new ArrayList<String[]>();

	
		List<HashMap<String, String>> list = dao.getJfshList(model, user,
				xmList);
		
		String nofy = model.getNofy();
		
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				ArrayList<String> value = new ArrayList<String>();
				HashMap<String, String> map = list.get(i);
				String xh = map.get("xh");//学号
				value.add(xh);
				String xm = map.get("xm");//姓名
				value.add(xm);
				String nj = map.get("nj");//年级
				value.add(nj);
				String bjmc = map.get("bjmc");//班级名称
				value.add(bjmc);
				
				if(xmList!=null && xmList.size()>0){
					for (int j = 0; j < xmList.size(); j++) {
						HashMap<String, String> xmInfo = xmList.get(j);
						String xmdm = xmInfo.get("xmdm");
						String sq = map.get(xmdm + "sq");// 申请分
						String sh = map.get(xmdm + "sh");// 审核分
						String fs = "申请分(" + sq + ")";
						if(Base.isNull(nofy)){
							fs += "</br>";
						}
						fs += "审核分(" + sh + ")";
						value.add(fs);
					}
				}
				
				String shr = map.get("shr");//班级名称
				value.add(shr);
				String sftj = map.get("sftj");//班级名称
				value.add(sftj);
				
				rsLisr.add(value.toArray(new String[]{}));
			}
		}
		
		return rsLisr;
	}
	
	/**
	 * 获得项目列表
	 * 
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getXmList() {

		ZhcpJbszForm model = new ZhcpJbszForm();
		ZhcpJbszService service = new ZhcpJbszService();

		// 二级项目列表
		model.setXmjb("2");
		List<HashMap<String, String>> list = service.getZctreeList(model);

		return list;
	}
	
	/**
	 * 打印综测加分申请
	 * 
	 * @author luo
	 * @throws Exception
	 */
	public void printZcjfsq(ZhcpZcjfForm model, RequestForm rForm, User user,
			List<HashMap<String, String>> xmList, OutputStream os)
			throws Exception {

		String title = "综测加分申请";
		
		List<HashMap<String, String>> topTr = rForm.getTopTr();
		model.setNofy("yes");
		ArrayList<String[]> list = getJfshList(model, user, xmList);
		
		expToExcel(title, topTr, list, os);
	}
	
	/**
	 * 判断审核分
	 * @param model
	 * @param xh
	 * @return
	 */
	public boolean checkShfs(ZhcpZcjfForm model,String xh){
		
		String num=dao.checkShfs(model, xh);
		
		if(Base.isNull(num)){
			
			return false;
		
		}else if("0".equalsIgnoreCase(num)){
			
			return false;
		}
		
		return true;
	}
	
	/**
	 * 判断审核分
	 * @param model
	 * @param xh
	 * @return
	 */
	public boolean checkShfIsModi(ZhcpZcjfForm model,HashMap<String,Object>map){
		
		String num=dao.checkShfIsModi(model, map);
		
		if(Base.isNull(num)){
			
			return false;
		
		}else if(!"0".equalsIgnoreCase(num)){
			
			return false;
		}
		
		return true;
	}
}