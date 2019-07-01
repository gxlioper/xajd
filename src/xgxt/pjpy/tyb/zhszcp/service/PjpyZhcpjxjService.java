package xgxt.pjpy.tyb.zhszcp.service;

import java.util.HashMap;
import java.util.List;

import common.GlobalsVariable;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.form.User;
import xgxt.pjpy.PjpyTyService;
import xgxt.pjpy.lsxy.LsxyPjpyDAO;
import xgxt.pjpy.tyb.zhszcp.action.PjpyZhcpjxjActionForm;
import xgxt.pjpy.tyb.zhszcp.action.PjpyZhszcpwhActionForm;
import xgxt.pjpy.tyb.zhszcp.dao.PjpyZhcpjxjDAO;
import xgxt.pjpy.tyb.zhszcp.dao.PjpyZhszcpDAO;
import xgxt.pjpy.zjjd.JxjpdxxModel;
import xgxt.pjpy.zjkjxy.PjpyZjkjxyDAO;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;


public class PjpyZhcpjxjService {
	PjpyZhcpjxjDAO zcjxjDao = new PjpyZhcpjxjDAO();
	
	/**
	 * 获取综合测评时间信息
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getZhcpSqsjMap(){
		HashMap<String, String> map = new HashMap<String, String>();
		PjpyTyService service = new PjpyTyService();
		String zczq = service.getZhcpSqzq();//评奖周期
		
		if("xq".equalsIgnoreCase(zczq)){
			//学期
			map.put("xn", Base.getJxjsqxn());
			map.put("xq", Base.getJxjsqxq());
			map.put("nd", "无");
			map.put("sqsj", "无");
		}else if("xn".equalsIgnoreCase(zczq)){
			//学年
			map.put("xn", Base.getJxjsqxn());
			map.put("xq", "无");
			map.put("nd", "无");
			map.put("sqsj", "无");
		}else if("nd".equalsIgnoreCase(zczq)){
			//年度
			map.put("xn", "无");
			map.put("xq", "无");
			map.put("nd", Base.getJxjsqnd());
			map.put("sqsj", "无");
		}else{
			//无周期
			map.put("xn", "无");
			map.put("xq", "无");
			map.put("nd", "无");
			map.put("sqsj", GetTime.getNowTime2());
		}		
		return map;
	}
	
	/**
	 * 奖学金审核
	 * @param pkValue
	 * @param shzd
	 * @return String
	 * */
	public String auditingJxj(HashMap<String, String[]> pkValue, 
			                  String shzd,
			                  String tableName){
		DAO dao = DAO.getInstance();
		String resultMsg = "";
		String[] pkV = pkValue.get("cbv");
		String key = GlobalsVariable.PJPY_ZHCPJXJ;
		//主键
		String pk = "xh||xn||nd||xq";
		
		for(int i=0; i<pkV.length; i++){
			//判断人数是否超限
			String msg = checkJxjrs(pk,pkV[i], shzd,key);
			if(StringUtils.isNotNull(msg)){
				if(pkV.length<2){
					resultMsg += "操作失败，" + msg + "\n";
				}else{
					resultMsg += "第" + (i+1) + "行操作失败，" + msg + "\n";
				}
				
			}else{
				try{
					dao.runUpdate("update " + tableName + " set " + shzd + "='通过' where " + pk + "=?", 
							      new String[]{pkV[i]});
				} catch (Exception e) {
					if(pkV.length<2){
						resultMsg += "操作失败！\n";
					}else{
						resultMsg += "第" + (i+1) + "行操作失败！\n";
					}                     
				}
			}
		}
		
		return resultMsg;
	}
	
	/**
	 * 判断奖学金人数是否超过限制
	 * @param pkV
	 * @param shzd
	 * @return String
	 * */
	public String checkJxjrs(String pk,String pkV,String shzd,String key){
		String result = "";
		PjpyZjkjxyDAO zjkjDao = new PjpyZjkjxyDAO();
		HashMap<String, String> rsfpMap = zcjxjDao.getRsfpMap(pk,pkV,shzd,key);
			
		//查询奖学金人数的主键值
		String zjz = rsfpMap.get("pkV");
		//已经审核通过的人数（在审核级别范围内审核通过的人数）
		int tgrs = Integer.parseInt(rsfpMap.get("tgrs"));
		
		//奖学金人数（分配人数）
		String jxjrs = zjkjDao.getJxjrs(zjz,shzd,key);
		
		if(StringUtils.isNull(jxjrs)){
			result = "";
		}else{
			int fprs = Integer.parseInt(jxjrs);
			if(fprs<=tgrs){
				result = "审核通过人数已经达到限制人数！";
			}
		}
		return result;
	}
	
	/**
	 * 获取综合素质测评奖学金导出数据的表头列信息
	 * @param pjzq
	 * @param jb
	 * @param xmdmMap
	 * @return HashMap<String, String[]>
	 * */
	public HashMap<String, String[]> getZhcpjxjsbTitle(String pjzq,
			String jb, HashMap<String, String> map, boolean isQuery,
			PjpyZhcpjxjActionForm model) throws Exception {
		HashMap<String, String[]> rs = new HashMap<String, String[]>();
		PjpyZhszcpDAO dao = new PjpyZhszcpDAO();
		String viewName = "view_pjpy_zhcpjxjsb";//视图
		String[] en = {"xh","xm","nj","xymc","zymc","bjmc"};//默认要导出的与学生个人信息相关的字段
		
		if (isQuery) {
			en = new String[]{"pk", "xh","xm","bjmc"};
		}
		
		String[] cn = {};//列的中文名称
		
		//评奖周期
		if("xn".equalsIgnoreCase(pjzq)){
			en = StringUtils.joinStrArr(en, new String[]{"xn"});
		}else if("xq".equalsIgnoreCase(pjzq)){
			en = StringUtils.joinStrArr(en, new String[]{"xn", "xqmc"});
		}else if("nd".equalsIgnoreCase(pjzq)){
			en = StringUtils.joinStrArr(en, new String[]{"nd"});
		}
		en = StringUtils.joinStrArr(en,new String[]{"jxjmc","xysh","xxsh","平均学分绩点"});
		//获取默认要导出列的中文名称
		cn = dao.getColumnNameCN(en, viewName);
		
		//要导出的项目
		if(!"1".equalsIgnoreCase(jb)){//非一级
			String xmdm = map.get(jb);//项目代码
			String[] xmdmArray = new String[]{};
			if ("2".equalsIgnoreCase(jb)) {
				if (StringUtils.isNotNull(model.getQueryequals_ejdm())) {
					xmdm = map.get("2");
					xmdmArray = new String[]{xmdm};
				}
				
			} else if ("3".equalsIgnoreCase(jb)) {
				if (StringUtils.isNotNull(model.getQueryequals_sjdm())) {
					xmdm = map.get("3");
					xmdmArray = new String[]{xmdm};
				} else if (StringUtils.isNotNull(model.getQueryequals_ejdm())) {
					xmdm = map.get("2");
					xmdmArray = dao
							.getArray(
									"select dm from pjpy_zctjszb where fdm = ? and dmjb='3'",
									new String[] { xmdm }, "dm");
				}
				
			} else if ("4".equalsIgnoreCase(jb)) {
				if (StringUtils.isNotNull(model.getQueryequals_sidm())) {
					xmdm = map.get("4");	
					xmdmArray = new String[]{xmdm};
				} else if (StringUtils.isNotNull(model.getQueryequals_sjdm())) {
					xmdm = map.get("3");
					xmdmArray = dao
							.getArray(
									"select dm from pjpy_zctjszb where fdm = ? and dmjb='4'",
									new String[] { xmdm }, "dm");
				} else if (StringUtils.isNotNull(model.getQueryequals_ejdm())) {
					xmdm = map.get("2");
					xmdmArray = dao
							.getArray(
									"select dm from pjpy_zctjszb a where exists (select 1 from pjpy_zctjszb b where b.fdm = ? and b.dmjb='3' and a.fdm=b.dm)",
									new String[] { xmdm }, "dm");
				}
			}
			
			cn = StringUtils.joinStrArr(cn,  dao.getZhcpxmMc(jb,xmdmArray));
		}
		//默认导出总分字段
		cn = StringUtils.joinStrArr(cn, new String[]{"总分","总分排名"});//总分
		
		//单独增加行号
//		if (en != null && cn != null && en.length == cn.length) {
//			String[] addEn = new String[en.length + 1];
//			String[] addCn = new String[cn.length + 1];
//			addEn[0] = "r";
//			addCn[0] = "行号";
//			for (int i=0;i<en.length;i++) {
//				addEn[i + 1] = en[i];
//				addCn[i + 1] = cn[i];
//			}
//		}
		rs.put("en", en);
		rs.put("cn", cn);
		
		return rs;
	}
	
	/**
	 * 查询综合测评总分信息
	 * @param pjzq
	 * @param jb
	 * @param map
	 * @param model
	 * @return List<String[]>
	 * @throws Exception 
	 * */
	public List<String[]> queryZhcpjxjsb(String pjzq, String jb,
			HashMap<String, String> map, String[] output,
			PjpyZhcpjxjActionForm model, boolean isPage) throws Exception {		
		return zcjxjDao.queryZhcpjxjsb(pjzq, jb, map, output, model, isPage);
	}
	
	/**
	 * 保存综合测评奖学金上报信息
	 * @param pkValues
	 * @param jxjdm
	 * @param user
	 * @return boolean
	 * */
	public boolean zhcpjxjSb(String[] pkValues, String jxjdm, User user){
		return zcjxjDao.saveZhcpjxjsb(pkValues, jxjdm, user);
	}
	
	/**
	 * 取消学院综合测评奖学金上报
	 * @param pkValues
	 * @param user
	 * @return boolean
	 * */
	public boolean zhcpjxjQxsb(String[] pkValues, User user){
		return zcjxjDao.delZhcpjxjsb(pkValues, user);
	}
	
	/**
	 * 获取条件信息
	 * @param model
	 * @param xmlx
	 * @return List<HashMap<String, String>>
	 * **/
	public List<HashMap<String, String>> getTjList(JxjpdxxModel model, String xmlx){
		LsxyPjpyDAO dao = new LsxyPjpyDAO();
		return dao.getTjList(model, xmlx);
	}
	
	/**
	 * 获取条件信息
	 * @param model
	 * @param xmlx
	 * @return List<HashMap<String, String>>
	 * */
	public List<HashMap<String, String>> getTjxxList(PjpyZhcpjxjActionForm model,String xmlx){
		return zcjxjDao.selectTjxxList(model,xmlx);
	}
}
