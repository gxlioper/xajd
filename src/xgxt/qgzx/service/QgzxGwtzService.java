package xgxt.qgzx.service;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import common.Globals;

import xgxt.daoActionLogic.StandardOperation;
import xgxt.qgzx.dao.QgzxDao;
import xgxt.qgzx.form.QgzxForm;
import xgxt.utils.String.StringUtils;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 勤工助学学生岗位调整管理Service</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 李容</p>
 * <p>Version: 2.0</p>
 * <p>Time: 2009-12-30</p>
 */
public class QgzxGwtzService {
	QgzxDao dao = new QgzxDao();
	
	/**
	 * 岗位调整岗位替换 
	 * @param QgzxForm model
	 * @param HttpServletRequest request
	 * @return boolean 
	 * @throws Exception
	 * */
	public boolean gwtzGwth(QgzxForm model,HttpServletRequest request) throws Exception{
		//要对某个岗位的学生进行调整，将"之前学生"的调整后的岗位信息
		//变为"新学生"修改前的岗位信息，然后将新学生数据保存到数据库中
		//将新学生的数据保存（或更新到表”xsgwxxb“）,并且默认用人单位和学校的审核是通过的
		boolean result = true;
		boolean operFlag = true;
		boolean delFlag =  true;
		String xxdm = StandardOperation.getXxdm();
		String shjg = "通过";
		
		String oldXh = model.getOldXh();//被替换学生的学号
		String tzhgzxn = model.getTzhgzxn();
		String tzhgzxq = model.getTzhgzxq();
		String tzhgznd = model.getTzhgznd();
		String tzhgwdm = model.getTzhgw();
		String tzhgwsbsj = model.getTzhgwsbsj();
		String tzhkcjqgzxsj = model.getTzhkcjqgzxsj();
		String tzqgzxn = model.getTzqgzxn();
		String tzqgzxq = model.getTzqgzxq();
		String tzqgznd = model.getTzqgznd();
		String tzqkcjqgzxsj = model.getTzqkcjqgzxsj();
		String xh = model.getXh();//上岗学生的学生
		String tzqgw = model.getTzqgw();
		String tzqgwsbsj = model.getTzqgwsbsj();
		String xn = model.getXn();
		String xq = model.getXq();
		String tzyy = model.getTzyy();
		String tzsj = model.getTzsj();
		
		if(Globals.XXDM_GZDX.equalsIgnoreCase(xxdm)){
			//广州大学
			delFlag = false;
			if("xy".equalsIgnoreCase(model.getUserType())){//学院调整岗位需要学校审核通过
				operFlag = false;
				shjg = "未审核";
			}
		}
		
		//判断是否将调整的信息直接更新到岗位信息表中
		if(operFlag){//允许操作
			//将上岗的学生插入到学生岗位信息表中
			result  = StandardOperation.insert("xsgwxxb", new String[] { "xn", "xq","nd", "gwdm", "gwsbsj", "kcjqgzxsj","xh","xyyj","xxyj","sfyx"},
					new String[] {tzhgzxn, tzhgzxq, tzhgznd, tzhgwdm, tzhgwsbsj,tzhkcjqgzxsj,xh,"通过","通过","有效"}, request);	
			if(!result){
				result = StandardOperation.update("xsgwxxb", 
						new String[] { "xn", "xq", "nd", "gwdm", "gwsbsj", "kcjqgzxsj", "xyyj", "xxyj", "sfyx" }, 
						new String[] { tzhgzxn, tzhgzxq, tzhgznd, tzhgwdm, tzhgwsbsj, tzhkcjqgzxsj,"通过", "通过", "1" },//sfyx='1' 有效 
						new String[] { "xh", "gwdm", "gwsbsj" }, new String[] {xh, tzqgw, tzqgwsbsj }, request);
			}			
		}
		
		//判断是否可以删除数据，不可删除进行修改操作
		if(delFlag){
			//将被替换的学生岗位信息数据从岗位信息表中删除
			if(result) {			
				StandardOperation.delete("xsgwxxb","xh||gwdm||gwsbsj",oldXh + tzhgwdm + tzhgwsbsj,request);
			}
		}else{
			//将被替换的学生岗位信息修改为无效
			if(result){
				//将退岗信息插入到岗位调整信息表中
				String[] colList = { "xh", "xn", "xq", "tzyy", "tzsj", "tzqgzxn", "tzqgzxq", "tzqgznd", "tzqgw", 
						"tzqgwsbsj", "tzqkcjqgzxsj", "tzhgzxn", "tzhgzxq", "tzhgznd", "tzhgw", "tzhgwsbsj", 
						"tzhkcjqgzxsj","shjg" };
				String[] valList = new String[] {oldXh, xn, xq, tzyy, tzsj, tzqgzxn, tzqgzxq, tzqgznd, tzqgw,
						tzqgwsbsj, tzqkcjqgzxsj, "", "", "", "退岗", "", "", shjg };
				result = StandardOperation.insert("qgzx_gwtzb", colList, valList, request);
				
				if(!result){
					//岗位调整信息已经存在，修改岗位调整信息
					result = StandardOperation.update("qgzx_gwtzb", colList, new String[] {
							oldXh, xn, xq, tzyy, tzsj, tzqgzxn, tzqgzxq, tzqgznd, tzqgw,
							tzqgwsbsj, tzqkcjqgzxsj, "", "", "",
							"退岗", "", "",shjg }, "xh||xn||xq||tzqgw||tzsj", oldXh+xn+xq+tzqgw+tzsj, request);
				}	
//				StandardOperation.update("xsgwxxb", new String[]{"sfyx"}, new String[]{"无效"}, "xh||gwdm||gwsbsj", oldXh + tzhgwdm + tzhgwsbsj, request);
			}
		}
		
		//将岗位调整信息插入到岗位调整信息表中
		String[] colList = { "xh", "xn", "xq", "tzyy", "tzsj", "tzqgzxn", "tzqgzxq", "tzqgznd", "tzqgw", 
				"tzqgwsbsj", "tzqkcjqgzxsj", "tzhgzxn", "tzhgzxq", "tzhgznd", "tzhgw", "tzhgwsbsj", 
				"tzhkcjqgzxsj","shjg" };
		String[] valList = new String[] {xh, xn, xq, tzyy, tzsj, "", "", "", "无岗位",
				"", "", tzhgzxn, tzhgzxq, tzhgznd, tzhgwdm, tzhgwsbsj, tzhkcjqgzxsj, shjg };
		result = StandardOperation.insert("qgzx_gwtzb", colList, valList, request);
		
		if(!result){
			//岗位调整信息已经存在，修改岗位调整信息
			result = StandardOperation.update("qgzx_gwtzb", colList, new String[] {
					oldXh, xn, xq, tzyy, tzsj, tzqgzxn, tzqgzxq, tzqgznd, tzqgw,
					tzqgwsbsj, tzqkcjqgzxsj, "", "", "",
					"", "", "",shjg }, "xh||xn||xq||tzqgw||tzsj", oldXh+xn+xq+tzqgw+tzsj, request);
		}		
		return result;
	}
	
	/**
	 * 岗位调整
	 * @param QgzxForm model
	 * @param HttpServletRequest request
	 * @return boolean 
	 * @throws Exception
	 * */
	public boolean gwtz(QgzxForm model,HttpServletRequest request) throws Exception{
		boolean result = false;
		boolean operFlag = true;
		String xxdm = StandardOperation.getXxdm();
		
		String pk = model.getPk();
		String pkValue = model.getPkValue();
		String realTable = "qgzx_gwtzb";
		String shjg = "通过";
		
		String xh = model.getXh();
		String xn = model.getXn();
		String xq = model.getXq();
		String tzqgzxn = model.getTzqgzxn();
		String tzqgznd = model.getTzqgznd();
		String tzqgzxq = model.getTzqgzxq();
		String tzqkcjqgzxsj = model.getTzqkcjqgzxsj();
		String tzqgw = model.getTzqgw();
		String tzqgwsbsj = model.getTzqgwsbsj();
		String tzsj = model.getTzsj();
		String tzyy = model.getTzyy();
		String tzhgzxn = model.getTzhgzxn();
		String tzhgznd = model.getTzhgznd();
		String tzhgzxq = model.getTzhgzxq();
		String tzhgwdm = model.getTzhgw();
		String tzhgwsbsj = model.getTzhgwsbsj();
		String tzhkcjqgzxsj = model.getTzhkcjqgzxsj();
		
		if(Globals.XXDM_GZDX.equalsIgnoreCase(xxdm)){
			//广州大学
			if("xy".equalsIgnoreCase(model.getUserType())){//学院用户调整岗位需要学校审核通过 
				operFlag = false;
				shjg = "未审核";
			}
		}
		
		//判断学生是否在调整前的岗位上
	    if(dao.checkExists("view_xsgwxx", "xh||gwdm||gwsbsj", xh+tzqgw+tzqgwsbsj)){
	    	//判断是否直接将调整信息插入到岗位信息表中
	    	if(operFlag){
		    	//修改学生岗位信息
	    		//先将已经存在的调整后的岗位删除
	    		result = StandardOperation.delete("xsgwxxb", "xh||gwdm||gwsbsj",xh+tzhgwdm+tzhgwsbsj, request);
	    		if(result){
			    	result = StandardOperation.update("xsgwxxb", 
			    			new String[] { "xn", "xq", "nd", "gwdm", "gwsbsj", "kcjqgzxsj","xyyj","xxyj","sfyx" }, 
			    			new String[] { tzhgzxn, tzhgzxq, tzhgznd, tzhgwdm, tzhgwsbsj, tzhkcjqgzxsj, "通过", "通过", "1" },//sfyx='1'有校
							new String[] { "xh", "gwdm", "gwsbsj" }, 
							new String[] { xh, tzqgw, tzqgwsbsj }, 
							request);
	    		}
	    	}
	    	
	    	//删除学生的岗位调整信息
			if (StringUtils.isNull(pkValue)) {
				result = StandardOperation.delete(realTable, "xh||xn||xq||tzqgw||tzsj",xh+xn+xq+tzqgw+tzsj, request);
			} else {
				result = StandardOperation.delete(realTable, pk, pkValue, request);
			}
	    	//将调整信息插入到岗位调整信息表中
			if(result){
				String[] colList = { "xh", "xn", "xq", "tzyy", "tzsj", "tzqgzxn", "tzqgzxq", "tzqgznd", 
						"tzqgw", "tzqgwsbsj", "tzqkcjqgzxsj", "tzhgzxn", "tzhgzxq", "tzhgznd", "tzhgw", 
						"tzhgwsbsj", "tzhkcjqgzxsj","shjg" };
				result = StandardOperation.insert(realTable, colList, 
						new String[] {xh, xn, xq, tzyy, tzsj, tzqgzxn, tzqgzxq, tzqgznd, tzqgw,
						tzqgwsbsj, tzqkcjqgzxsj, tzhgzxn, tzhgzxq, tzhgznd,tzhgwdm, tzhgwsbsj,
						tzhkcjqgzxsj,shjg }, request);
			}
	    }else{
	    	request.setAttribute("msg", "学生不在调整前的岗位上！");
	    	result = false;
	    }
		return result;
	}
	
	/**
	 * 岗位调整批量审核
	 * @param QgzxForm model
	 * @return boolean
	 * */
	public boolean adjustBatchAudi(QgzxForm model){
		boolean result = false;
		String shjg = model.getShjg();
		String[] pkV = model.getPkV();
		String[] gwtzArr = new String[pkV.length];
		String[] gwAddArr = new String[pkV.length];
		String[] gwDelArr = new String[pkV.length];
		String pk = "xn||xq||xh||tzqgw||tzsj";
		
		for(int i=0; i<pkV.length; i++){
			HashMap<String, String> map = dao.selectGwxzxxOne(pk,pkV[i]);
			String xh = map.get("xh") == null ? "" :  map.get("xh");
			String tzhgw = map.get("tzhgw") == null ? "" : map.get("tzhgw");
			String tzhgwsbsj = map.get("tzhgwsbsj") == null ? "" : map.get("tzhgwsbsj");
			String tzqgw = map.get("tzqgw") == null ? "" : map.get("tzqgw");
			String tzqgwsbsj = map.get("tzqgwsbsj") == null ? "" : map.get("tzqgwsbsj");
			String tzhxn = map.get("tzhgzxn") == null ? "" : map.get("tzhgzxn");
			String tzhnd = map.get("tzhgznd") == null ? "" : map.get("tzhgznd");
			String tzhxq = map.get("tzhgzxq") == null ? "" : map.get("tzhgzxq");
			String tzhkcjqgzxsj = map.get("tzhkcjqgzxsj") == null ? "" : map.get("tzhkcjqgzxsj");
			
			if("通过".equalsIgnoreCase(shjg)){
				//审核通过将学生的岗位信息更新到岗位信息表中
				if(StringUtils.isNull(tzhgwsbsj)){
					gwDelArr[i] = "delete from xsgwxxb where xh='" + xh + "' and gwdm='" + tzhgw +"' and gwsbsj is null" ;;
				}else{
					gwDelArr[i] = "delete from xsgwxxb where xh='" + xh + "' and gwdm='" + tzhgw +"' and gwsbsj='" + tzhgwsbsj + "'" ;;
				}
				if("退岗".equalsIgnoreCase(map.get("tzhgw"))){
					//被替换的学生
					gwAddArr[i] = "update xsgwxxb set sfyx='0' where xh='" + xh+ "' and gwdm='" + tzqgw + "' and gwsbsj='" + tzqgwsbsj+"'";
				}else{
					gwAddArr[i] = "insert into xsgwxxb(xn,xq,nd,gwdm,gwsbsj,kcjqgzxsj,xh,xyyj,xxyj,sfyx) values('" + tzhxn + "', '" + tzhxq + "'," +
							"'" + tzhnd + "', '" + tzhgw + "','" + tzhgwsbsj +"','" + tzhkcjqgzxsj + "','" + xh + "'," +
									"'通过','通过','1')";
				}
			}
			//修改岗位调整信息表中的审核结果
			gwtzArr[i] = "update qgzx_gwtzb set shjg='" + shjg +"' where " + pk + "='" + pkV[i]+"'";
		}
		
		try{
			dao.runBatch(gwDelArr);
			dao.runBatch(gwAddArr);
			dao.runBatch(gwtzArr);
			result = true;
		}catch(Exception e){
			result = false;
		}
		
		return result;
	}
	
	/**
	 * 根据主键查询学生岗位调整详细信息
	 * @param String pk
	 * @param String pkValue
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> queryGwtzxxxx(String pk, String pkValue){
		return dao.selectGwxzxxOne(pk,pkValue);
	}
	
}
