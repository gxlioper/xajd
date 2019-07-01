package xgxt.qgzx.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.form.CommanForm;
import xgxt.qgzx.dao.QgzxDao;
import xgxt.qgzx.hngydx.dao.HngydxGwglDAO;
import xgxt.qgzx.xcxy.dao.QgzxXcxyDAO;
import xgxt.utils.ExcelMethods;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;

import common.Globals;


/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 勤工助学模块Service</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 李容</p>
 * <p>Version: 2.0</p>
 * <p>Time: 2008-06-05</p>
 */
public class QgzxService {	
	QgzxDao qgzxDao = new QgzxDao();
	
	/**
	 * 查询当前月岗位调整学生岗位调整前的岗位信息
	 * @param ffList
	 * @param pk
	 * @param pkValue
	 * @return ArrayList
	 * */
	public List<HashMap<String, String>> queryTzqxsgw(List<HashMap<String, String>> ffList,String pk,String pkValue){
		ffList = qgzxDao.getTzqgw(pkValue);
		return ffList;
	}
	
	
	/**
	 * 查询酬金发放年度月份岗位调整学生岗位调整前的岗位信息
	 * @param ffList
	 * @param pk
	 * @param pkValue
	 * @return ArrayList
	 * */
	public List<HashMap<String, String>> queryTzqxsgw(List<HashMap<String, String>> ffList,String pkValue,String nd,String yf){		
		ffList = qgzxDao.getTzqgw(pkValue,nd,yf);		
		return ffList;
	}
	
	/**
	 * 井冈山大学获取岗位要求信息
	 * @param pk
	 * @param pkValue
	 * @return String
	 * */
	public String getGwyqInfo(String pk, String pkValue){	
		String sDemand = "";
		String sTemPk = "gwdm||gwsbsj";
		pkValue = qgzxDao.getGwPk(pk,pkValue);
		
		String[] demand = qgzxDao.getSqtjString(sTemPk, pkValue);
		if(demand.length>0){
			sDemand = demand[0];
		}
		return sDemand;
	}
	
	/**
	 * 查询学生申请岗位附加信息
	 * @param map
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> addOtherInfo(HashMap<String, String> map){
		String xxdm = StandardOperation.getXxdm();
		String sTsxx = "";	//提示信息
		String xh = map.get("xh");
		String djsj = map.get("djsj");//是否懂计算机		
		String pkValue = "";
		
		if(xxdm.equalsIgnoreCase(Globals.XXDM_JGSDX)){
			//井冈山
			//查询学生不符合岗位提出的要求的项目
			pkValue = qgzxDao.getGwPk("xh||gwdm||sqsj",map.get("xh||gwdm||sqsj"),"-");
			//提示信息
			sTsxx = qgzxDao.isStudentCondOK(xh, pkValue, djsj);
			map.put("tsxx", sTsxx);
		}
		if(xxdm.equalsIgnoreCase(Globals.XXDM_WHLGDX)){
			//武汉理工大学 增加任职要求
			map.putAll(qgzxDao.getRzyqInfo(map));			
		}	
		return map;
	}
	
	/**
	 * 查询学生申请岗位附加信息
	 * @param map
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> addOtherInfo(HashMap<String, String> map,String type){
		String xxdm = StandardOperation.getXxdm();
		String sGwyq = "";		
		String pkValue = "";
		
		if(xxdm.equalsIgnoreCase(Globals.XXDM_JGSDX)){
			//井冈山
			//获取岗位审核条件 即岗位要求
			pkValue = map.get("xmdm");
			String[] gwyq = qgzxDao.getSqtjString("gwdm||'-'||gwsbsj", pkValue);
			if(gwyq!=null && gwyq.length>0){
				sGwyq = gwyq[0].equalsIgnoreCase("0") ? "" : gwyq[0];
			}
			map.put("gwyq", sGwyq);
		}
		
		return map;
	}
	
	/**
	 * 岗位批量审核
	 * @param CommanForm model
	 * @param String type
	 * @param String userType
	 * @param String userName
	 * @return boolean
	 * */
	public boolean postBatchAudi(CommanForm model,String type,String userType,String userName){
		boolean flag = false;
		String xxdm = StandardOperation.getXxdm();
		DAO dao = DAO.getInstance();
		HngydxGwglDAO gwDao = new HngydxGwglDAO();
		String sql = "";
		String zd = "";
		String yesNo = "";
		String[] value = model.getPkV();
		String[] sqlValue = null;
		
		if(xxdm.equalsIgnoreCase(Globals.XXDM_HNGYDX)
				||Globals.XXDM_GZDX.equalsIgnoreCase(xxdm)){
			//湖南工业和广州大学用人单位审核
			if(gwDao.checkIsYrdw(userName)){
				userType = "yrdw";
			}
		}
		//判断用户是学校还是用人单位
		if(userType.equalsIgnoreCase("xx")||userType.equalsIgnoreCase("admin")){
			zd = " shjg ";
		}
		if(userType.equalsIgnoreCase("yrdw")){
			zd = " yrdwsh ";
		}
		if(userType.equalsIgnoreCase("xy") 
				&& Globals.XXDM_GZDX.equalsIgnoreCase(xxdm)){
;			zd=	" yrdwsh ";
		}
		if(xxdm.equalsIgnoreCase(Globals.XXDM_HYGXY)){//淮阴工学院一级审核
			zd = " shjg ";
		}
		//判断审核结果是通过还是不通过
		if(type.equalsIgnoreCase("pass")){
			yesNo = "通过";
		}
		if(type.equalsIgnoreCase("nopass")){
			yesNo = "不通过";
		}
		if(xxdm.equalsIgnoreCase(Globals.XXDM_ZGDZDX)){//中国地质大学学校一级审核
			zd = "shjg";
		}
		//组合sql  
		for(int i=0; i<value.length; i++){
			if(zd!=null && !"".equalsIgnoreCase(zd)){
				sql += "update gwxxb set " + zd + "='" + yesNo +"',sqsyknss=syknss,sqsyrs=xyrs,spbcbz=jybcbz where gwdm||gwsbsj='" + value[i]+ "'";
				sql += "!!#!!";
			}
		}
		sqlValue = sql.split("!!#!!");
		try {
			//批量执行修改
			dao.runBatch(sqlValue);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag ;
	}
	
	/**
	 * 学生岗位申请审核人数限制判断
	 * @param pkValue
	 * @param type
	 * @param userType
	 * @param userName
	 * @return boolean 
	 * */
	public HashMap<String, String> postStuBatchAudi(String pkValue,String type,String userType,String userName){
		boolean flag = false;
		String xxdm = StandardOperation.getXxdm();
		DAO dao = DAO.getInstance();
		HngydxGwglDAO gwDao = new HngydxGwglDAO();
		HashMap<String, String> resultMap = new HashMap<String, String>();

		
		String sql = "";
		String sTempSql = "";
		String zd = "";
		String yesNo = "";
		String whereSql = "";
		String[] value = pkValue.split("!!SplitOneSplit!!");
		String[] sqlValue = null;	
		String[] tempValue = null;
		
				
		//辅导员
		if(xxdm.equalsIgnoreCase(Globals.XXDM_JSXX) || xxdm.equalsIgnoreCase(Globals.XXDM_YNYS)
				|| xxdm.equalsIgnoreCase(Globals.XXDM_GDBYXY)
				|| xxdm.equalsIgnoreCase(Globals.XXDM_GDNZZYJSXY)
				|| xxdm.equalsIgnoreCase(Globals.XXDM_XBEMY)
				|| xxdm.equalsIgnoreCase(Globals.XXDM_HBJTZYJSXY)
				|| xxdm.equalsIgnoreCase(Globals.XXDM_WHSYFWXY)){			
			String num = dao.getOneRs("select count(1) count from fdyxxb where zgh='" + userName + "'", new String[]{},"count");
			if(Integer.parseInt(num)>0){
				zd = "fdyyj";
			}			
		}		
		if(xxdm.equalsIgnoreCase(Globals.XXDM_HNGYDX) ||xxdm.equalsIgnoreCase(Globals.XXDM_ZGMSXY) || xxdm.equalsIgnoreCase(Globals.XXDM_NBLGXY)){
			//湖南工业 || 中国美术学院 ||宁波理工
			if(userType != null && "xy".equalsIgnoreCase(userType)){
				zd = "fdyyj";
			}
		}
		if(xxdm.equalsIgnoreCase(Globals.XXDM_NBTYZYJSXY)
				|| xxdm.equalsIgnoreCase(Globals.XXDM_SZWBZYJSXY)){
			//宁波天一职业技术学院
			if(userType != null && "xy".equalsIgnoreCase(userType)){
				zd = "xyyj";
			}
		}
		//学校审核
		if(!(xxdm.equalsIgnoreCase(Globals.XXDM_JSXX)
				|| xxdm.equalsIgnoreCase(Globals.XXDM_GDBYXY)
//				|| xxdm.equalsIgnoreCase(Globals.XXDM_GDNZZYJSXY)
				|| xxdm.equalsIgnoreCase(Globals.XXDM_XBEMY))){
			if(userType.equalsIgnoreCase("xx")||userType.equalsIgnoreCase("admin")){
				zd = " xxyj ";
			}			
		}
		
		//用人单位审核
		if(!xxdm.equalsIgnoreCase(Globals.XXDM_YNYS) 
				&& !xxdm.equalsIgnoreCase(Globals.XXDM_ZJJDZYJSXY) 
				&& !xxdm.equalsIgnoreCase(Globals.XXDM_NBLGXY)){
			if(gwDao.checkIsYrdw(userName)){
				zd = "xyyj";
			}
		}
		// ============= begin 骆嘉伟 2009/3/31 ================
		if (xxdm.equalsIgnoreCase(Globals.XXDM_NBLGXY)) {
			if (userType.equalsIgnoreCase("xy")) {
				whereSql = " and xxyj<>'通过'";
				zd = "xyyj";
			}
		}
		if(xxdm.equalsIgnoreCase(Globals.XXDM_HYGXY)//淮阴工学院
				|| xxdm.equalsIgnoreCase(Globals.XXDM_ZJJSZYJSXY)){//浙江建设职业技术学院（2011.12.5 qlj）
			zd = "xxyj";
		}
		// ============= end 骆嘉伟 2009/3/31 ================
		//判断审核结果是通过还是不通过
		if(type.equalsIgnoreCase("pass")){
			yesNo = "通过";
		}
		if(type.equalsIgnoreCase("nopass")){
			yesNo = "不通过";
		}		
		//组合sql  
		String msg = "";//操作结果信息
		for(int i=0; i<value.length; i++){
			if(xxdm.equalsIgnoreCase(Globals.XXDM_ZGDZDX)){
				//中国地质大学用人单位一级审核
				sql += "update xsgwxxb set xxyj='" + yesNo + "' where xh||gwdm||sqsj='" + value[i]+ "'";
				sql += "!!#!!";
			}else if(Globals.XXDM_ZJKJXY.equalsIgnoreCase(xxdm)){
				//浙江科技
//				if("xyyj".equalsIgnoreCase(zd) && "通过".equalsIgnoreCase(yesNo)){
//					//学院审核通过 判断是否已经有岗位审核通过了
//					boolean resultFlag = qgzxDao.checkYjbgwlq(value[i]);
//					if(resultFlag){
//						msg += "第" + (i+1) + "条数据操作失败，该学生已经有岗位审核通过了！ \n" ;
//						flag = false;
//						continue;
//					}
//				}
				if(StringUtils.isNotNull(zd)){
					sql="update xsgwxxb set " + zd + "='" + yesNo + "' where xh||gwdm||sqsj='" + value[i]+ "'";
					try{
						flag = dao.runUpdate(sql,new String[]{});
					}catch(Exception e){
						flag = false;
						msg += "第" + (i+1) + "条数据操作失败！ \n" ;
					}
				}				
			}else{
				if("通过".equalsIgnoreCase(yesNo)){
					//判断人数限制
					String[] result = qgzxDao.checkPostStuAudi(value[i], zd);
					if(result != null && result.length>0 && StringUtils.isNull(result[0])){
						if(zd!=null && !"".equalsIgnoreCase(zd))
							try {
								sql = "update xsgwxxb set " + zd + "='" + yesNo + "' where xh||gwdm||sqsj='" + value[i]+ "'" + whereSql;
								dao.runUpdate(sql, new String[]{});
								sql="";
							} catch (Exception e) {
								e.printStackTrace();
							}
					}else{
						for(String str : result){
							if(StringUtils.isNotNull(str)){
								msg += str;
							}
						}
					}
				}else{
					if(zd!=null && !"".equalsIgnoreCase(zd)){				
						sql += "update xsgwxxb set " + zd + "='" + yesNo + "' where xh||gwdm||sqsj='" + value[i]+ "'" + whereSql;
						sql += "!!#!!";
					}
				}
			}
		}
		sqlValue = sql.split("!!#!!");
		if(xxdm.equalsIgnoreCase(Globals.XXDM_SHGC) || xxdm.equalsIgnoreCase(Globals.XXDM_ZGDZDX)){
			//上海工程技术大学 
			tempValue = sTempSql.split("!!#!!");
		}
		try {
			//批量执行修改
			if(sqlValue!=null && !sqlValue.equals("")){
				dao.runBatch(sqlValue);
				if(xxdm.equalsIgnoreCase(Globals.XXDM_SHGC)){
					//上海工程技术大学 				
					dao.runBatch(tempValue);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		resultMap.put("flag", flag+"");
		resultMap.put("msg", msg);
		return resultMap ;
	}
	
	

	public HashMap<String, String> postStuBatchAudi(String pkValue,String type,String userType,String userName,String isFdy,String isBzr){
		boolean flag = false;
		String xxdm = StandardOperation.getXxdm();
		DAO dao = DAO.getInstance();
		HngydxGwglDAO gwDao = new HngydxGwglDAO();
		HashMap<String, String> resultMap = new HashMap<String, String>();

		
		String sql = "";
		String sTempSql = "";
		String zd = "";
		String yesNo = "";
		String whereSql = "";
		String[] value = pkValue.split("!!SplitOneSplit!!");
		String[] sqlValue = null;	
		String[] tempValue = null;
		
				
		//辅导员
		if(xxdm.equalsIgnoreCase(Globals.XXDM_JSXX) || xxdm.equalsIgnoreCase(Globals.XXDM_YNYS)
				|| xxdm.equalsIgnoreCase(Globals.XXDM_GDBYXY)
				|| xxdm.equalsIgnoreCase(Globals.XXDM_GDNZZYJSXY)
				|| xxdm.equalsIgnoreCase(Globals.XXDM_XBEMY)
				|| xxdm.equalsIgnoreCase(Globals.XXDM_HBJTZYJSXY)
				|| xxdm.equalsIgnoreCase(Globals.XXDM_WHSYFWXY)){			
			String num = dao.getOneRs("select count(1) count from fdyxxb where zgh='" + userName + "'", new String[]{},"count");
			if(Integer.parseInt(num)>0){
				zd = "fdyyj";
			}			
		}		
		if(xxdm.equalsIgnoreCase(Globals.XXDM_HNGYDX) ||xxdm.equalsIgnoreCase(Globals.XXDM_ZGMSXY) || xxdm.equalsIgnoreCase(Globals.XXDM_NBLGXY)){
			//湖南工业 || 中国美术学院 ||宁波理工
			if(userType != null && "xy".equalsIgnoreCase(userType)){
				zd = "fdyyj";
			}
		}
		if(xxdm.equalsIgnoreCase(Globals.XXDM_NBTYZYJSXY)
				|| xxdm.equalsIgnoreCase(Globals.XXDM_SZWBZYJSXY)){
			//宁波天一职业技术学院
			if(userType != null && "xy".equalsIgnoreCase(userType)){
				zd = "xyyj";
			}
		}
		//学校审核
		if(!(xxdm.equalsIgnoreCase(Globals.XXDM_JSXX)
				|| xxdm.equalsIgnoreCase(Globals.XXDM_GDBYXY)
//					|| xxdm.equalsIgnoreCase(Globals.XXDM_GDNZZYJSXY)
				|| xxdm.equalsIgnoreCase(Globals.XXDM_XBEMY))){
			if(userType.equalsIgnoreCase("xx")||userType.equalsIgnoreCase("admin")){
				zd = " xxyj ";
			}			
		}
		
		//用人单位审核
		if(!xxdm.equalsIgnoreCase(Globals.XXDM_YNYS) 
				&& !xxdm.equalsIgnoreCase(Globals.XXDM_ZJJDZYJSXY) 
				&& !xxdm.equalsIgnoreCase(Globals.XXDM_NBLGXY)){
			if(isFdy.equals("true")){
				zd="fdyyj";
			}else if(gwDao.checkIsYrdw(userName)){
				zd = "xyyj";
			}
		}
		// ============= begin 骆嘉伟 2009/3/31 ================
		if (xxdm.equalsIgnoreCase(Globals.XXDM_NBLGXY)) {
			if (userType.equalsIgnoreCase("xy")) {
				whereSql = " and xxyj<>'通过'";
				zd = "xyyj";
			}
		}
		if(xxdm.equalsIgnoreCase(Globals.XXDM_HYGXY)//淮阴工学院
				|| xxdm.equalsIgnoreCase(Globals.XXDM_ZJJSZYJSXY)){//浙江建设职业技术学院（2011.12.5 qlj）
			zd = "xxyj";
		}
		// ============= end 骆嘉伟 2009/3/31 ================
		//判断审核结果是通过还是不通过
		if(type.equalsIgnoreCase("pass")){
			yesNo = "通过";
		}
		if(type.equalsIgnoreCase("nopass")){
			yesNo = "不通过";
		}		
		//组合sql  
		String msg = "";//操作结果信息
		for(int i=0; i<value.length; i++){
			if(xxdm.equalsIgnoreCase(Globals.XXDM_ZGDZDX)){
				//中国地质大学用人单位一级审核
				sql += "update xsgwxxb set xxyj='" + yesNo + "' where xh||gwdm||sqsj='" + value[i]+ "'";
				sql += "!!#!!";
			}else if(Globals.XXDM_ZJKJXY.equalsIgnoreCase(xxdm)){
				//浙江科技
//					if("xyyj".equalsIgnoreCase(zd) && "通过".equalsIgnoreCase(yesNo)){
//						//学院审核通过 判断是否已经有岗位审核通过了
//						boolean resultFlag = qgzxDao.checkYjbgwlq(value[i]);
//						if(resultFlag){
//							msg += "第" + (i+1) + "条数据操作失败，该学生已经有岗位审核通过了！ \n" ;
//							flag = false;
//							continue;
//						}
//					}
				if(StringUtils.isNotNull(zd)){
					sql="update xsgwxxb set " + zd + "='" + yesNo + "' where xh||gwdm||sqsj='" + value[i]+ "'";
					try{
						flag = dao.runUpdate(sql,new String[]{});
					}catch(Exception e){
						flag = false;
						msg += "第" + (i+1) + "条数据操作失败！ \n" ;
					}
				}				
			}else{
				if("通过".equalsIgnoreCase(yesNo)){
					//判断人数限制
					String[] result = qgzxDao.checkPostStuAudi(value[i], zd);
					if(result != null && result.length>0 && StringUtils.isNull(result[0])){
						if(zd!=null && !"".equalsIgnoreCase(zd))
							try {
								sql = "update xsgwxxb set " + zd + "='" + yesNo + "' where xh||gwdm||sqsj='" + value[i]+ "'" + whereSql;
								dao.runUpdate(sql, new String[]{});
								sql="";
							} catch (Exception e) {
								e.printStackTrace();
							}
					}else{
						for(String str : result){
							if(StringUtils.isNotNull(str)){
								msg += str;
							}
						}
					}
				}else{
					if(zd!=null && !"".equalsIgnoreCase(zd)){				
						sql += "update xsgwxxb set " + zd + "='" + yesNo + "' where xh||gwdm||sqsj='" + value[i]+ "'" + whereSql;
						sql += "!!#!!";
					}
				}
			}
		}
		sqlValue = sql.split("!!#!!");
		if(xxdm.equalsIgnoreCase(Globals.XXDM_SHGC) || xxdm.equalsIgnoreCase(Globals.XXDM_ZGDZDX)){
			//上海工程技术大学 
			tempValue = sTempSql.split("!!#!!");
		}
		try {
			//批量执行修改
			if(sqlValue!=null && !sqlValue.equals("")){
				dao.runBatch(sqlValue);
				if(xxdm.equalsIgnoreCase(Globals.XXDM_SHGC)){
					//上海工程技术大学 				
					dao.runBatch(tempValue);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		resultMap.put("flag", flag+"");
		resultMap.put("msg", msg);
		return resultMap ;
	}

	/**
	 * 勤工助学中校区列表
	 * @return List
	 * */
	public List<HashMap<String, String>> getXiaoquList(){
		return qgzxDao.getXiaoquList();
	}
	
	/**
	 * 酬金发放
	 * @return List
	 * */
	public List<HashMap<String, String>> getGwxzCjList(){
		return qgzxDao.getGwxzCjList();
	}
	
	/**
	 * 勤工助学中岗位性质列表
	 * @return List
	 * */
	public List<HashMap<String, String>> getGwxzList(){
		return qgzxDao.getGwxzList();
	}
	
	/**
	 * 勤工助学中用人单位列表
	 * @return List
	 * */
	public List<HashMap<String, String>> getYrdwList(){
		return qgzxDao.getYrdwList();
	}
	/**
	 * 勤工助学中用人单位列表
	 * @return List
	 * */
	public List<HashMap<String, String>> getYrdwList(String userName){
		return qgzxDao.getYrdwList(userName);
	}
	
	/**
	 * 勤工助学中信用度列表
	 * @return List
	 * */
	public List<HashMap<String, String>> getXydList(){
		return qgzxDao.getXydList();
	}
	
	/**
	 * 获取勤工助学参数设定信息
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getSqsjInfo(){
		return qgzxDao.getSqsjInfo();
	}
	
	/**
	 * 中国美术学院学生工作考核获取详细时间
	 * */
	public HashMap<String, String> getDetailsDaytime(HashMap<String, String> map,String pkValue){
		double zsj = 0;
		for(int i=1; i<32; i++){			
			String values = map.get("day" + i);			
			String bValues = "";
			String aValues = "";
			if(values.indexOf(":")>0){
				aValues = values.substring(values.indexOf(":")+1,values.length());
				bValues = values.substring(0, values.indexOf(":"));
			}
			bValues = bValues == null || "".equalsIgnoreCase(bValues) ? "-" : bValues;
			aValues = aValues == null || "".equalsIgnoreCase(aValues) ? "-" : aValues;
			
			String ksxs = bValues.substring(0,bValues.indexOf("-"));
			String ksfz = bValues.substring(bValues.indexOf("-")+1,bValues.length());
			String jsxs = aValues.substring(0,aValues.indexOf("-"));
			String jsfz = aValues.substring(aValues.indexOf("-")+1,aValues.length());
			
			map.put("day"+i+"_ksxs",ksxs);
			map.put("day" + i + "_ksfz", ksfz);
			map.put("day" + i + "_jsxs", jsxs);
			map.put("day" + i + "_jsfz", jsfz);
			
			ksxs = ksxs == null || "".equalsIgnoreCase(ksxs) ? "0" : ksxs;
			ksfz = ksfz == null || "".equalsIgnoreCase(ksfz) ? "0" : ksfz;
			jsxs = jsxs == null || "".equalsIgnoreCase(jsxs) ? "0" : jsxs;
			jsfz = jsfz == null || "".equalsIgnoreCase(jsfz) ? "0" : jsfz;
			
			zsj += ((Integer.parseInt(jsxs)-Integer.parseInt(ksxs))*60 + (Integer.parseInt(jsfz)-Integer.parseInt(ksfz)))/60;			
		}			
		//计算月总时间和总酬金
		String bcbz = qgzxDao.getBcbzByStuPk(pkValue);
		String je = qgzxDao.getZbcje(bcbz, zsj);
		
		map.put("spbcbz", bcbz);
		map.put("zcj", je);
		map.put("zsj", String.valueOf(zsj));
		return map;
	}
	

	/**
	 * 广东女子勤工助学工资报表打印
	 * @param wwb
	 * @return void	 
	 * */
	@SuppressWarnings("unchecked")
	public void printPayRePortGdnzzyjsxy(WritableWorkbook wwb){		
		String xxmc = StandardOperation.getXxmc();
		String xxdm = StandardOperation.getXxdm();		
		String yf = qgzxDao.getCurrentYf();
		String nd = qgzxDao.getSqsjInfo().get("nd");
		String cjffsj = qgzxDao.getQgzxConf().get("cjffsj");
		
		if(cjffsj != null && !"".equalsIgnoreCase(cjffsj)){
			nd = cjffsj.substring(0,4);
			yf = cjffsj.substring(4,6);
		}
		if(xxdm.equalsIgnoreCase(Globals.XXDM_ZJJDZYJSXY)){
			//浙江机电
			HashMap<String, String> map = qgzxDao.getZjjdCjffsj();
			nd = map.get("nd");
			yf = map.get("yf");
		}
//		yf=""+Integer.parseInt(yf);
		String totalMoney = qgzxDao.getTotalMoney(nd,yf);
		
		List list = qgzxDao.getPayInfo(nd,yf);		
		try{
		 WritableSheet ws = wwb.getSheet(0);
		 WritableCellFormat wcfTytle = new WritableCellFormat();
		 Alignment alignMent = Alignment.CENTRE;
		 VerticalAlignment vag = VerticalAlignment.CENTRE;
		 
		 wcfTytle.setVerticalAlignment(vag);
		 wcfTytle.setAlignment(alignMent);
		 wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);
		 
		 WritableFont wfTytle = new WritableFont(WritableFont.ARIAL);
		 wfTytle.setBoldStyle(WritableFont.BOLD);
		 wfTytle.setPointSize(14);
		 wcfTytle.setFont(wfTytle);
		 
		 ws.addCell(new Label(0,0,xxmc + "学生勤工助学工资发放表" ,wcfTytle));	
		 
		 wcfTytle = new WritableCellFormat();
		 alignMent = Alignment.LEFT;
		 vag = VerticalAlignment.CENTRE;
		 
		 wcfTytle.setVerticalAlignment(vag);
		 wcfTytle.setAlignment(alignMent);
		 wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);
		 
		 wfTytle = new WritableFont(WritableFont.ARIAL);
		 wfTytle.setBoldStyle(WritableFont.BOLD);
		 wfTytle.setPointSize(12);
		 wcfTytle.setFont(wfTytle);
		 
		 ws.addCell(new Label(0,2,"时间：" + (StringUtils.isNull(nd) ? "" : nd )+ "年" + yf + "月份",wcfTytle));
		 
		 wcfTytle = new WritableCellFormat();
		 alignMent = Alignment.CENTRE;
		 vag = VerticalAlignment.CENTRE;
		 
		 wcfTytle.setVerticalAlignment(vag);
		 wcfTytle.setAlignment(alignMent);
		 wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);
		 
		 wfTytle = new WritableFont(WritableFont.ARIAL);
		 wfTytle.setPointSize(10);
		 wcfTytle.setFont(wfTytle);
		 
		 
		 ws.addCell(new Label(0, 3, "编号", wcfTytle));
		 ws.addCell(new Label(1, 3, "学号", wcfTytle));
		 ws.addCell(new Label(2, 3, "姓名", wcfTytle));
		 ws.addCell(new Label(3, 3, "工作岗位", wcfTytle));
		 ws.addCell(new Label(4, 3, "工作时间", wcfTytle));
		 ws.addCell(new Label(5, 3, "金额（元）", wcfTytle));
		 
		 for (int i = 0; i < list.size(); i++) {
				HashMap<String, String> map = new HashMap<String, String>();
				map = (HashMap<String, String>) list.get(i);
				ws.addCell(new Label(0, 4 + i, String.valueOf(i + 1),wcfTytle));
				ws.addCell(new Label(1, 4 + i, map.get("xh"), wcfTytle));
				ws.addCell(new Label(2, 4 + i, map.get("xm"), wcfTytle));
				ws.addCell(new Label(3, 4 + i, map.get("gwdm"), wcfTytle));
				ws.addCell(new Label(4, 4 + i, map.get("gzsj"), wcfTytle));
				ws.addCell(new Label(5, 4 + i, map.get("cjje"), wcfTytle));
				// totalMoney = map.get("totalMoney");
			}
		 totalMoney = totalMoney == null || "".equalsIgnoreCase(totalMoney)? "0" : totalMoney;
		 
		 wcfTytle = new WritableCellFormat();
		 alignMent = Alignment.CENTRE;
		 vag = VerticalAlignment.CENTRE;
		 wcfTytle.setVerticalAlignment(vag);
		 wcfTytle.setAlignment(alignMent);
		 wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);
		 
		 wfTytle = new WritableFont(WritableFont.ARIAL);
		 wfTytle.setBoldStyle(WritableFont.BOLD);
		 wfTytle.setPointSize(12);
		 wcfTytle.setFont(wfTytle);
		 
		 if (xxdm.equalsIgnoreCase(Globals.XXDM_ZJJDZYJSXY)) {
				ws.mergeCells(0, list.size() + 4, 6, list.size() + 4);
			} else {
				ws.mergeCells(0, list.size() + 4, 5, list.size() + 4);
		}
		 
		 ws.addCell(new Label(0,list.size()+4,"合计金额:" + totalMoney,wcfTytle));
		 
		 }catch(Exception e){
			 e.printStackTrace();
		 }
		 //向客户端输出
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	/**
	 * 勤工助学工资报表打印
	 * @param wwb
	 * @return void	 
	 * */
	@SuppressWarnings("unchecked")
	public void printPayReport(WritableWorkbook wwb){		
		String xxmc = StandardOperation.getXxmc();
		String xxdm = StandardOperation.getXxdm();		
		String yf = qgzxDao.getCurrentYf();
		String nd = qgzxDao.getSqsjInfo().get("nd");
		String cjffsj = qgzxDao.getQgzxConf().get("cjffsj");
		
		if(cjffsj != null && !"".equalsIgnoreCase(cjffsj)){
			nd = cjffsj.substring(0,4);
			yf = cjffsj.substring(4,6);
		}
		if(xxdm.equalsIgnoreCase(Globals.XXDM_ZJJDZYJSXY)){
			//浙江机电
			HashMap<String, String> map = qgzxDao.getZjjdCjffsj();
			nd = map.get("nd");
			yf = map.get("yf");
		}
//		yf=""+Integer.parseInt(yf);
		String totalMoney = qgzxDao.getTotalMoney(nd,yf);
		
		List list = qgzxDao.getPayInfo(nd,yf);		
		try{
		 WritableSheet ws = wwb.getSheet(0);
		 WritableCellFormat wcfTytle = new WritableCellFormat();
		 Alignment alignMent = Alignment.CENTRE;
		 VerticalAlignment vag = VerticalAlignment.CENTRE;
		 
		 wcfTytle.setVerticalAlignment(vag);
		 wcfTytle.setAlignment(alignMent);
		 wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);
		 
		 WritableFont wfTytle = new WritableFont(WritableFont.ARIAL);
		 wfTytle.setBoldStyle(WritableFont.BOLD);
		 wfTytle.setPointSize(14);
		 wcfTytle.setFont(wfTytle);
		 ws.mergeCells(0, 0, 6 ,1);
		 ws.addCell(new Label(0,0,xxmc + "学生勤工助学工资发放表" ,wcfTytle));	
		 
		 wcfTytle = new WritableCellFormat();
		 alignMent = Alignment.LEFT;
		 vag = VerticalAlignment.CENTRE;
		 
		 wcfTytle.setVerticalAlignment(vag);
		 wcfTytle.setAlignment(alignMent);
		 wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);
		 
		 wfTytle = new WritableFont(WritableFont.ARIAL);
		 wfTytle.setBoldStyle(WritableFont.BOLD);
		 wfTytle.setPointSize(12);
		 wcfTytle.setFont(wfTytle);
		 ws.mergeCells(0, 2, 6 ,2);
		 ws.addCell(new Label(0,2,"时间：" + (StringUtils.isNull(nd) ? "" : nd )+ "年" + yf + "月份",wcfTytle));
		 
		 wcfTytle = new WritableCellFormat();
		 alignMent = Alignment.CENTRE;
		 vag = VerticalAlignment.CENTRE;
		 
		 wcfTytle.setVerticalAlignment(vag);
		 wcfTytle.setAlignment(alignMent);
		 wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);
		 
		 wfTytle = new WritableFont(WritableFont.ARIAL);
		 wfTytle.setPointSize(10);
		 wcfTytle.setFont(wfTytle);
		 
		 
		 ws.addCell(new Label(0, 3, "编号", wcfTytle));
		 ws.addCell(new Label(1, 3, "学号", wcfTytle));
		 ws.addCell(new Label(2, 3, "姓名", wcfTytle));
		 ws.addCell(new Label(3, 3, "身份证号", wcfTytle));
		 ws.addCell(new Label(4, 3, "工作岗位", wcfTytle));
		 ws.addCell(new Label(5, 3, "工作时间", wcfTytle));
		 ws.addCell(new Label(6, 3, "金额（元）", wcfTytle));
		 
		 for (int i = 0; i < list.size(); i++) {
				HashMap<String, String> map = new HashMap<String, String>();
				map = (HashMap<String, String>) list.get(i);
				ws.addCell(new Label(0, 4 + i, String.valueOf(i + 1),wcfTytle));
				ws.addCell(new Label(1, 4 + i, map.get("xh"), wcfTytle));
				ws.addCell(new Label(2, 4 + i, map.get("xm"), wcfTytle));
				ws.addCell(new Label(3, 4 + i, map.get("sfzh"), wcfTytle));
				ws.addCell(new Label(4, 4 + i, map.get("gwdm"), wcfTytle));
				ws.addCell(new Label(5, 4 + i, map.get("gzsj"), wcfTytle));
				ws.addCell(new Label(6, 4 + i, map.get("cjje"), wcfTytle));
				// totalMoney = map.get("totalMoney");
			}
		 totalMoney = totalMoney == null || "".equalsIgnoreCase(totalMoney)? "0" : totalMoney;
		 
		 wcfTytle = new WritableCellFormat();
		 alignMent = Alignment.CENTRE;
		 vag = VerticalAlignment.CENTRE;
		 wcfTytle.setVerticalAlignment(vag);
		 wcfTytle.setAlignment(alignMent);
		 wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);
		 
		 wfTytle = new WritableFont(WritableFont.ARIAL);
		 wfTytle.setBoldStyle(WritableFont.BOLD);
		 wfTytle.setPointSize(12);
		 wcfTytle.setFont(wfTytle);
		 
		
		ws.mergeCells(0, list.size() + 4, 6, list.size() + 4);
		
		 
		 ws.addCell(new Label(0,list.size()+4,"合计金额:" + totalMoney,wcfTytle));
		 
		 }catch(Exception e){
			 e.printStackTrace();
		 }
		 //向客户端输出
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	/**
	 * 宁波天一职业技术学院勤工助学工资报表打印
	 * @param wwb
	 * @return void	 
	 * */
	public void printPayRePortNbtyzyjsxy(WritableWorkbook wwb,String userName){	
		String yf = qgzxDao.getCurrentYf();
		String nd = qgzxDao.getSqsjInfo().get("nd");
		String cjffsj = qgzxDao.getQgzxConf().get("cjffsj");
		String bmmc = qgzxDao.getYrdwmcByUser(userName);
		bmmc = StringUtils.isNull(bmmc) ? "全部" : bmmc;
		
		if(cjffsj != null && !"".equalsIgnoreCase(cjffsj)){
			nd = cjffsj.substring(0,4);
			yf = cjffsj.substring(4,6);
		}
		String totalMoney = qgzxDao.getTotalMoney(nd,yf);
		float totalTime = 0;
		List<HashMap<String, String>> list = qgzxDao.getPayInfoOfNbty(nd,yf,userName);		
		try{
		 WritableSheet ws = wwb.getSheet(0);
		 WritableCellFormat wcfTytle = new WritableCellFormat();
		 Alignment alignMent = Alignment.CENTRE;
		 VerticalAlignment vag = VerticalAlignment.CENTRE;
		 
		 wcfTytle.setVerticalAlignment(vag);
		 wcfTytle.setAlignment(alignMent);
//		 wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);
		 
		 WritableFont wfTytle = new WritableFont(WritableFont.ARIAL);
		 wfTytle.setBoldStyle(WritableFont.BOLD);
		 wcfTytle.setWrap(true);//自动换行
		 wfTytle.setPointSize(24);
		 wcfTytle.setFont(wfTytle);
		 
		 ws.addCell(new Label(0,1,nd + "年度" + yf + "月份学生勤工助学酬金发放表" ,wcfTytle));	
		 
		 wcfTytle = new WritableCellFormat();
		 alignMent = Alignment.LEFT;
		 vag = VerticalAlignment.CENTRE;
		 
		 wcfTytle.setVerticalAlignment(vag);
		 wcfTytle.setAlignment(alignMent);
//		 wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);
		 
		 wfTytle = new WritableFont(WritableFont.ARIAL);
//		 wfTytle.setBoldStyle(WritableFont.BOLD);
		 wfTytle.setPointSize(12);
		 wcfTytle.setFont(wfTytle);
		 
//		 ws.addCell(new Label(2,2,bmmc,wcfTytle));//宁波天一不需要显示
		 ws.addCell(new Label(9,2,GetTime.getNowYear() + "年" + GetTime.getNowMonth() + "月" + GetTime.getNowDay() + "日",wcfTytle));//时间
		 
		 wcfTytle = new WritableCellFormat();
		 alignMent = Alignment.CENTRE;
		 vag = VerticalAlignment.CENTRE;
		 
		 wcfTytle.setVerticalAlignment(vag);
		 wcfTytle.setAlignment(alignMent);
		 wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);
		 
		 wfTytle = new WritableFont(WritableFont.ARIAL);
		 wfTytle.setPointSize(10);
		 wcfTytle.setFont(wfTytle);
		 
		 for(int i=0; i<list.size(); i++){
			 HashMap<String, String> map = new HashMap<String, String>();
			 map = (HashMap<String, String>) list.get(i);
			 ws.addCell(new Label(0,5+i,String.valueOf(i+1),wcfTytle));
			 ws.addCell(new Label(1,5+i,map.get("xm"),wcfTytle));
			 ws.addCell(new Label(2,5+i,map.get("bjmc"),wcfTytle));
			 ws.addCell(new Label(3,5+i,map.get("gznr"),wcfTytle));
			 if("优".equalsIgnoreCase(map.get("gzbx"))){
				 ws.addCell(new Label(4,5+i,"√",wcfTytle));
				 ws.addCell(new Label(5,5+i,"",wcfTytle));
				 ws.addCell(new Label(6,5+i,"",wcfTytle));
				 ws.addCell(new Label(7,5+i,"",wcfTytle));
			 }else if("良".equalsIgnoreCase(map.get("gzbx"))){
				 ws.addCell(new Label(4,5+i,"",wcfTytle));
				 ws.addCell(new Label(5,5+i,"√",wcfTytle));
				 ws.addCell(new Label(6,5+i,"",wcfTytle));
				 ws.addCell(new Label(7,5+i,"",wcfTytle));
			 }else if("合格".equalsIgnoreCase(map.get("gzbx"))){
				 ws.addCell(new Label(4,5+i,"",wcfTytle));
				 ws.addCell(new Label(5,5+i,"",wcfTytle));
				 ws.addCell(new Label(6,5+i,"√",wcfTytle));
				 ws.addCell(new Label(7,5+i,"",wcfTytle));
			 }else if("差".equalsIgnoreCase(map.get("gzbx"))){
				 ws.addCell(new Label(4,5+i,"",wcfTytle));
				 ws.addCell(new Label(5,5+i,"",wcfTytle));
				 ws.addCell(new Label(6,5+i,"",wcfTytle));				 
				 ws.addCell(new Label(7,5+i,"√",wcfTytle)); 
			 }else{
				 ws.addCell(new Label(4,5+i,"",wcfTytle));
				 ws.addCell(new Label(5,5+i,"",wcfTytle));
				 ws.addCell(new Label(6,5+i,"",wcfTytle));				 
				 ws.addCell(new Label(7,5+i,"",wcfTytle)); 
			 }
			 ws.addCell(new Label(8,5+i,map.get("gzsj"),wcfTytle));
			 ws.addCell(new Label(9,5+i,"",wcfTytle));
			 ws.addCell(new Label(10,5+i,map.get("cjje"),wcfTytle));
			 ws.addCell(new Label(11,5+i,"",wcfTytle));
			 totalTime += Float.parseFloat(StringUtils.isNull(map.get("gzsj")) ? "0" : map.get("gzsj"));
		 }
		 totalMoney = totalMoney == null || "".equalsIgnoreCase(totalMoney)? "0" : totalMoney;
		 
		 wcfTytle = new WritableCellFormat();
		 alignMent = Alignment.CENTRE;
		 vag = VerticalAlignment.CENTRE;
		 wcfTytle.setVerticalAlignment(vag);
		 wcfTytle.setAlignment(alignMent);
		 wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);
		 
		 wfTytle = new WritableFont(WritableFont.ARIAL);
//		 wfTytle.setBoldStyle(WritableFont.BOLD);
		 wfTytle.setPointSize(12);
		 wcfTytle.setFont(wfTytle);
		 
		 ws.mergeCells(0, list.size()+5, 7, list.size()+5);
		 ws.addCell(new Label(0,list.size()+5,"合计",wcfTytle));
		 ws.addCell(new Label(8,list.size()+5,totalTime+"",wcfTytle));
		 ws.addCell(new Label(9,list.size()+5,"",wcfTytle));
		 ws.addCell(new Label(10,list.size()+5,totalMoney,wcfTytle));
		 ws.addCell(new Label(11,list.size()+5,"",wcfTytle));
		 
		 ws.mergeCells(0, list.size()+5+1, 7, list.size()+5+1);
		 ws.addCell(new Label(0,list.size()+5+1,"本月酬金合计大写",wcfTytle));
		 ws.mergeCells(8, list.size()+5+1, 11, list.size()+5+1);
		 ws.addCell(new Label(8,list.size()+5+1,qgzxDao.getDxje(totalMoney),wcfTytle));
		 
		 ws.mergeCells(0, list.size()+5+2, 1, list.size()+5+2);
		 ws.addCell(new Label(0,list.size()+5+2,"用工部门审查",wcfTytle));
		 ws.mergeCells(2, list.size()+5+2, 11, list.size()+5+2);
		 ws.addCell(new Label(2,list.size()+5+2,"签名:              年  月  日",wcfTytle));
		 
		 ws.mergeCells(0, list.size()+5+3, 1, list.size()+5+3);
		 ws.addCell(new Label(0,list.size()+5+3,"学生资助管理中心审核",wcfTytle));
		 ws.mergeCells(2, list.size()+5+3, 11, list.size()+5+3);
		 ws.addCell(new Label(2,list.size()+5+3,"签名:              年  月  日",wcfTytle));
		 
		 ws.mergeCells(0, list.size()+5+4, 1, list.size()+5+4);
		 ws.addCell(new Label(0,list.size()+5+4,"院领导审批",wcfTytle));
		 ws.mergeCells(2, list.size()+5+4, 11, list.size()+5+4);
		 ws.addCell(new Label(2,list.size()+5+4,"签名:              年  月  日",wcfTytle));
		 }catch(Exception e){
			 e.printStackTrace();
		 }
		 //向客户端输出
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	
	/**
	 * 常州信息职业技术学院勤工助学工资报表打印
	 * @param wwb
	 * @return void	 
	 * */
	public void printPayReportCzxx(WritableWorkbook wwb){		
//		String xxmc = StandardOperation.getXxmc();
		String yf = qgzxDao.getCurrentYf();
		String nd = qgzxDao.getSqsjInfo().get("nd");
		String cjffsj = qgzxDao.getQgzxConf().get("cjffsj");
		
		if(cjffsj != null && !"".equalsIgnoreCase(cjffsj)){
			nd = cjffsj.substring(0,4);
			yf = cjffsj.substring(4,6);
		}
		String totalMoney = qgzxDao.getTotalMoney(nd,yf);
		List<HashMap<String, String>> list = qgzxDao.getPayInfo(nd,yf);		
		try{
		 WritableSheet ws = wwb.getSheet(0);
		 WritableCellFormat wcfTytle = new WritableCellFormat();
		 Alignment alignMent = Alignment.CENTRE;
		 VerticalAlignment vag = VerticalAlignment.CENTRE;
		 
		 wcfTytle.setVerticalAlignment(vag);
		 wcfTytle.setAlignment(alignMent);
		 wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);
		 
		 WritableFont wfTytle = new WritableFont(WritableFont.ARIAL);
		 wfTytle.setBoldStyle(WritableFont.BOLD);
		 wfTytle.setPointSize(14);
		 wcfTytle.setFont(wfTytle);
		 
		 ws.addCell(new Label(0,0,nd + "年" + yf + "月勤工俭学津贴发放" ,wcfTytle));	
		 
		 wcfTytle = new WritableCellFormat();
		 alignMent = Alignment.CENTRE;
		 vag = VerticalAlignment.CENTRE;
		 
		 wcfTytle.setVerticalAlignment(vag);
		 wcfTytle.setAlignment(alignMent);
		 wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);
		 
		 wfTytle = new WritableFont(WritableFont.ARIAL);
		 wfTytle.setPointSize(10);
		 wcfTytle.setFont(wfTytle);
		 
		 //写表头
		 WritableCellFormat wcf = new WritableCellFormat();
		 alignMent = Alignment.CENTRE;
		 vag = VerticalAlignment.CENTRE;
		 
		 wcf.setVerticalAlignment(vag);
		 wcf.setAlignment(alignMent);
		 wcf.setBorder(Border.ALL, BorderLineStyle.THIN);
		 
		 WritableFont font = new WritableFont(WritableFont.ARIAL);
		 font.setPointSize(10);
		 font.setBoldStyle(WritableFont.BOLD);
		 wcf.setFont(font);
		
		 int row = 0;
		 for(int i=0; i<list.size(); i++){
			 HashMap<String, String> map = new HashMap<String, String>();
			 map = (HashMap<String, String>) list.get(i);
			 if(i%43==0 && i!=0){
				 ws.addCell(new Label(0,2+row+i,"序号",wcf));
				 ws.addCell(new Label(1,2+row+i,"学号",wcf));
				 ws.addCell(new Label(2,2+row+i,"班级",wcf));
				 ws.addCell(new Label(3,2+row+i,"姓名",wcf));
				 ws.addCell(new Label(4,2+row+i,"岗位",wcf));
				 ws.addCell(new Label(5,2+row+i,"金额(元)",wcf));
				 row += 1; 				
			 }
			 ws.addCell(new Label(0,2+row+i,String.valueOf(i+1),wcfTytle));
			 ws.addCell(new Label(1,2+row+i,map.get("xh"),wcfTytle));
			 ws.addCell(new Label(2,2+row+i,map.get("bjmc"),wcfTytle));
			 ws.addCell(new Label(3,2+row+i,map.get("xm"),wcfTytle));
			 ws.addCell(new Label(4,2+row+i,map.get("gwdm"),wcfTytle));
			 ws.addCell(new Label(5,2+row+i,map.get("cjje"),wcfTytle));
		 }
		 totalMoney = totalMoney == null || "".equalsIgnoreCase(totalMoney)? "0" : totalMoney;
		 
		 ws.mergeCells(1, list.size()+2+row, 5, list.size()+2+row);
		 ws.addCell(new Label(0,list.size()+2+row,"合计:",wcfTytle));
		 ws.addCell(new Label(1,list.size()+2+row,totalMoney+"元",wcfTytle));
		 
		 wcfTytle = new WritableCellFormat();
		 alignMent = Alignment.CENTRE;
		 vag = VerticalAlignment.CENTRE;
		 wcfTytle.setVerticalAlignment(vag);
		 wcfTytle.setAlignment(alignMent);
		 
		 wfTytle = new WritableFont(WritableFont.ARIAL);
		 wfTytle.setPointSize(12);
		 wcfTytle.setFont(wfTytle);
		 
		 ws.mergeCells(1, list.size()+4+row, 5, list.size()+4+row);
		 ws.addCell(new Label(1,list.size()+4+row,"批准：                审核：                  制表：白岚       ",wcfTytle));
		 
		 ws.mergeCells(4, list.size()+6+row, 5, list.size()+6+row);
		 ws.addCell(new Label(4,list.size()+6+row,"时间： " + GetTime.getNowYear() + "." + GetTime.getNowMonth()+"." + GetTime.getNowDay(),wcfTytle));
		 }catch(Exception e){
			 e.printStackTrace();
		 }
		 //向客户端输出
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	
	/**
	 * 浙江经济工资发放表打印
	 * @param wwb
	 * @return void
	 * */
	@SuppressWarnings("unchecked")
	public void printPayReportZjjj(WritableWorkbook wwb){	
		String yf = qgzxDao.getCurrentYf();
		String nd = Base.currNd;
		List bmList = qgzxDao.getYrdwList(nd, yf);		
		try{
			 WritableSheet ws = wwb.getSheet(0);
			 WritableCellFormat wcfTytle = new WritableCellFormat();
			 Alignment alignMent = Alignment.CENTRE;
			 VerticalAlignment vag = VerticalAlignment.CENTRE;
			 
			 wcfTytle.setVerticalAlignment(vag);
			 wcfTytle.setAlignment(alignMent);
			 wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);
			 
			 WritableFont wfTytle = new WritableFont(WritableFont.ARIAL);
			 wfTytle.setBoldStyle(WritableFont.BOLD);
			 wfTytle.setPointSize(14);
			 wcfTytle.setFont(wfTytle);
			 
			 
			 ws.addCell(new Label(0,0,yf + "月份学生勤工助学工资发放表" ,wcfTytle));
			 
			 wcfTytle = new WritableCellFormat();
			 alignMent = Alignment.CENTRE;
			 vag = VerticalAlignment.CENTRE;
			 
			 wcfTytle.setVerticalAlignment(vag);
			 wcfTytle.setAlignment(alignMent);
			 wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);
			 
			 wfTytle = new WritableFont(WritableFont.ARIAL);
			 wfTytle.setPointSize(10);
			 wcfTytle.setFont(wfTytle);
			 ws.removeRow(2);			 
			 ws.addCell(new Label(0,2,"部门" ,wcfTytle));
			 ws.addCell(new Label(1,2,"岗位" ,wcfTytle));
			 ws.addCell(new Label(2,2,"姓名" ,wcfTytle));
			 ws.addCell(new Label(3,2,"班级" ,wcfTytle));
			 ws.addCell(new Label(4,2,"金额" ,wcfTytle));
			 ws.addCell(new Label(5,2,"签收" ,wcfTytle));
			 
			 int bmLen = 3;  //部门位置
			 int gwLen = 3;  //岗位位置
			 int stuLen = 3; //
			 int len = 3;
			 for(int i=0; i<bmList.size(); i++){//某一用人单位的信息		 			
				 
				 //部门信息
				 HashMap<String, String> bmMap = (HashMap<String, String>)bmList.get(i);
				 String bmmc = bmMap.get("yrdwmc");
				 List gwList = qgzxDao.getGwList(nd , yf ,bmMap.get("yrdwdm"));
				 
				 for(int j=0; j<gwList.size(); j++){//某一岗位的信息
					 //岗位信息					 
					 HashMap<String, String> gwMap = (HashMap<String, String>)gwList.get(j);
					 String gwmc = gwMap.get("gwdm");
					 List stuList = qgzxDao.getStuOfPayinfo(nd,yf,gwMap.get("gwdm"),gwMap.get("gwsbsj"));	
					 
					 for(int k=0; k<stuList.size(); k++){//某一学生的信息
						 //学生信息
						 HashMap<String, String> stuMap = (HashMap<String, String>) stuList.get(k);	
						 List list = qgzxDao.getStuList(nd, yf, gwMap.get("gwdm"),gwMap.get("gwsbsj") ,stuMap.get("xh"));
						 for(int m=0; m<list.size(); m++){//某一学生的发放记录
							 HashMap<String, String> map = (HashMap<String, String>)list.get(m);
							 String je = map.get("cjje");
							 String bz = map.get("jebz");
							 bz = bz == null ? "" : bz;
							 //len += m;
							 if(!"".equalsIgnoreCase(bz)){
								 ws.addCell(new Label(4,len+m,je + "("+ bz + ")",wcfTytle));
							 }else{
								 ws.addCell(new Label(4,len+m,je,wcfTytle));
							 }							 
							 ws.addCell(new Label(5,len+m,"",wcfTytle));
							 
						 }
						 len += list.size()-1;
						 //姓名
						 ws.mergeCells(2, stuLen, 2, len);//垂直合并
						 ws.addCell(new Label(2,stuLen,stuMap.get("xm"),wcfTytle));
						 //班级
						 ws.mergeCells(3, stuLen, 3, len);//垂直合并
						 ws.addCell(new Label(3,stuLen ,stuMap.get("bjmc"),wcfTytle));
						 len += list.size()>=1?1:0;
						 stuLen = len;						 
					 }
					 
					 ws.mergeCells(1, gwLen, 1, len-1);//垂直合并
					 //岗位名称
					 ws.addCell(new Label(1,gwLen,gwmc,wcfTytle));
					 gwLen = len;
				 }
				 ws.mergeCells(0, bmLen, 0, len-1);//垂直合并
				 //用人单位名称
				 ws.addCell(new Label(0,bmLen ,bmmc,wcfTytle));
				 bmLen = len;
			 }
		}catch(Exception e){
			e.printStackTrace();
		}
		 //向客户端输出
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	/**
	 * 西昌学院勤工助学酬金发放报表打印
	 * @param wwb
	 * */
	@SuppressWarnings("unchecked")
	public void printPayReportXcxy(WritableWorkbook wwb){	
		String yf = qgzxDao.getCurrentYf();
		String nd = qgzxDao.getSqsjInfo().get("nd");
		String cjffsj = qgzxDao.getQgzxConf().get("cjffsj");
		QgzxXcxyDAO xcxyDao = new QgzxXcxyDAO();
		
		if(cjffsj != null && !"".equalsIgnoreCase(cjffsj)){
			nd = cjffsj.substring(0,4);
			yf = cjffsj.substring(4,6);
		}
		String totalMoney = qgzxDao.getTotalMoney(nd,yf);
		List list = xcxyDao.getPayInfoForXcxy(nd,yf);
		try{
		 WritableSheet ws = wwb.getSheet(0);
		 WritableCellFormat wcfTytle = new WritableCellFormat();
		 Alignment alignMent = Alignment.CENTRE;
		 VerticalAlignment vag = VerticalAlignment.CENTRE;
		 
		 wcfTytle.setVerticalAlignment(vag);
		 wcfTytle.setAlignment(alignMent);
		 wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);
		 
		 WritableFont wfTytle = new WritableFont(WritableFont.ARIAL);
		 wfTytle.setBoldStyle(WritableFont.BOLD);
		 wfTytle.setPointSize(14);
		 wcfTytle.setFont(wfTytle);
		 
		 ws.addCell(new Label(0,0,nd+"年" + yf + "月勤工助学工资发放表" ,wcfTytle));	
		 
		 wcfTytle = new WritableCellFormat();
		 alignMent = Alignment.CENTRE;
		 vag = VerticalAlignment.CENTRE;
		 
		 wcfTytle.setVerticalAlignment(vag);
		 wcfTytle.setAlignment(alignMent);
		 wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);
		 
		 wfTytle = new WritableFont(WritableFont.ARIAL);
		 wfTytle.setPointSize(10);
		 wcfTytle.setFont(wfTytle);
		 
		 for(int i=0; i<list.size(); i++){
			 HashMap<String, String> map = new HashMap<String, String>();
			 map = (HashMap<String, String>) list.get(i);
			 ws.addCell(new Label(0,3+i,map.get("xm"),wcfTytle));
			 ws.addCell(new Label(1,3+i,map.get("xymc")+map.get("zymc"),wcfTytle));
			 ws.addCell(new Label(2,3+i,map.get("jcbz"),wcfTytle));
			 ws.addCell(new Label(3,3+i,map.get("gzsj"),wcfTytle));
			 ws.addCell(new Label(4,3+i,map.get("cjje"),wcfTytle));
			 ws.addCell(new Label(5,3+i,map.get("kh"),wcfTytle));
			 ws.addCell(new Label(6,3+i,map.get("bz"),wcfTytle));
		 }
		 totalMoney = totalMoney == null || "".equalsIgnoreCase(totalMoney)? "0" : totalMoney;
		 
		 wcfTytle = new WritableCellFormat();
		 alignMent = Alignment.CENTRE;
		 vag = VerticalAlignment.CENTRE;
		 wcfTytle.setVerticalAlignment(vag);
		 wcfTytle.setAlignment(alignMent);
		 wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);
		 
		 }catch(Exception e){
			 e.printStackTrace();
		 }
		 //向客户端输出
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	/**
	 * 广州大学月工资表报打印
	 * @param WritableWorkbook wwb
	 * */
	public void printPayReportGzdx(WritableWorkbook wwb, 
			                       String userName, 
			                       String gwxzmc){
		String xxmc = StandardOperation.getXxmc();
		String yf = qgzxDao.getCurrentYf();
		String nd = qgzxDao.getSqsjInfo().get("nd");
		String cjffsj = qgzxDao.getQgzxConf().get("cjffsj");
		String bmmc = qgzxDao.getYrdwmcByUser(userName);
		bmmc = StringUtils.isNull(bmmc) ? "全部" : bmmc;
		
		if(cjffsj != null && !"".equalsIgnoreCase(cjffsj)){
			nd = cjffsj.substring(0,4);
			yf = cjffsj.substring(4,6);
		}
		String totalMoney = qgzxDao.getTotalMoney(nd, yf, gwxzmc);
		List<HashMap<String, String>> list = qgzxDao.getPayInfoByGzdx(nd,yf,userName,gwxzmc);		
		try{
		 WritableSheet ws = wwb.getSheet(0);
		 WritableCellFormat wcfTytle = new WritableCellFormat();
		 Alignment alignMent = Alignment.CENTRE;
		 VerticalAlignment vag = VerticalAlignment.CENTRE;
		 
		 wcfTytle.setVerticalAlignment(vag);
		 wcfTytle.setAlignment(alignMent);
		 
		 WritableFont wfTytle = new WritableFont(WritableFont.ARIAL);
		 wfTytle.setBoldStyle(WritableFont.BOLD);
		 wfTytle.setPointSize(14);
		 wcfTytle.setFont(wfTytle);
		 
		 ws.addCell(new Label(0,0,xxmc + "学生勤工助学" + yf +"月" + gwxzmc + "工资月报表" ,wcfTytle));	
		 
		 wcfTytle = new WritableCellFormat();
		 alignMent = Alignment.LEFT;
		 vag = VerticalAlignment.CENTRE;
		 
		 wcfTytle.setVerticalAlignment(vag);
		 wcfTytle.setAlignment(alignMent);
		 wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);
		 
		 wfTytle = new WritableFont(WritableFont.ARIAL);
		 wfTytle.setBoldStyle(WritableFont.BOLD);
		 wfTytle.setPointSize(12);
		 wcfTytle.setFont(wfTytle);
		 
		 ws.addCell(new Label(0,2,"用工部门：" + bmmc + "                                        填表时间：" + nd + "年" + yf + "月份",wcfTytle));
		 
		 wcfTytle = new WritableCellFormat();
		 alignMent = Alignment.CENTRE;
		 vag = VerticalAlignment.CENTRE;
		 
		 wcfTytle.setVerticalAlignment(vag);
		 wcfTytle.setAlignment(alignMent);
		 wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);
		 
		 wfTytle = new WritableFont(WritableFont.ARIAL);
		 wfTytle.setPointSize(10);
		 wcfTytle.setFont(wfTytle);
		 
		 for(int i=0; i<list.size(); i++){
			 HashMap<String, String> map = new HashMap<String, String>();
			 map = (HashMap<String, String>) list.get(i);
			 ws.addCell(new Label(0,4+i,""+i,wcfTytle));
			 ws.addCell(new Label(1,4+i,map.get("yrdwmc"),wcfTytle));
			 ws.addCell(new Label(2,4+i,map.get("xm"),wcfTytle));
			 ws.addCell(new Label(3,4+i,map.get("xh"),wcfTytle));
			 ws.addCell(new Label(4,4+i,map.get("cjje"),wcfTytle));
			 ws.addCell(new Label(5,4+i,map.get("bz"),wcfTytle));
		 }
		 totalMoney = totalMoney == null || "".equalsIgnoreCase(totalMoney)? "0" : totalMoney;
		 String dxje = qgzxDao.getDxje(totalMoney);
		 
		 wcfTytle = new WritableCellFormat();
		 alignMent = Alignment.LEFT;
		 vag = VerticalAlignment.CENTRE;
		 wcfTytle.setVerticalAlignment(vag);
		 wcfTytle.setAlignment(alignMent);
		 wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);
		 
		 wfTytle = new WritableFont(WritableFont.ARIAL);
		 wfTytle.setBoldStyle(WritableFont.BOLD);
		 wfTytle.setPointSize(12);
		 wcfTytle.setFont(wfTytle);
		 
		 ws.mergeCells(0, list.size()+4, 5, list.size()+4);
		 ws.addCell(new Label(0,list.size()+4,"合计(大写)金额:" + dxje + "	                                                                      ￥：" + totalMoney ,wcfTytle));
		 
		 wcfTytle = new WritableCellFormat();
		 alignMent = Alignment.LEFT;
		 vag = VerticalAlignment.CENTRE;
		 wcfTytle.setVerticalAlignment(vag);
		 wcfTytle.setAlignment(alignMent);
//		 wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);
		 
		 wfTytle = new WritableFont(WritableFont.ARIAL);
		 wfTytle.setBoldStyle(WritableFont.BOLD);
		 wfTytle.setPointSize(10);
		 wcfTytle.setFont(wfTytle);
		 ws.addCell(new Label(0,list.size()+6,"部门主管：",wcfTytle));
		 ws.addCell(new Label(2,list.size()+6,"审核：",wcfTytle));
		 ws.addCell(new Label(4,list.size()+6,"制表人：",wcfTytle));
		 
		 ws.addCell(new Label(0,list.size()+10,"备注：",wcfTytle));
		 ws.mergeCells(0, list.size()+11, 5, list.size()+11);
		 ws.addCell(new Label(0,list.size()+11,"1、	用工部门管理人员要如实登记学生的工作时间，上报时数必须与《学生勤工簿》相对应。",wcfTytle));
		 ws.mergeCells(0, list.size()+12, 5, list.size()+12);
		 ws.addCell(new Label(0,list.size()+12,"2、	此表务必在每月的25~28日交到学生服务大厅（图书馆副楼）。",wcfTytle));
		 ws.mergeCells(0, list.size()+13, 5, list.size()+13);
		 ws.addCell(new Label(0,list.size()+13,"3、	学生参加勤工助学是按时计酬（个别岗位除外），每小时10元，每月最高时数不超过40小时。",wcfTytle));
		 
		 }catch(Exception e){
			 e.printStackTrace();
		 }
		 //向客户端输出
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	/**
	 * 勤工助学工资报表打印(浙江传媒学院)
	 * @param wwb
	 * @return void	 
	 * */
	public void printPayReportZjcmxy(WritableWorkbook wwb){		
		String xxmc = StandardOperation.getXxmc();
		String yf = qgzxDao.getCurrentYf();
		String nd = qgzxDao.getSqsjInfo().get("nd");
		String cjffsj = qgzxDao.getQgzxConf().get("cjffsj");
		
		if(cjffsj != null && !"".equalsIgnoreCase(cjffsj)){
			nd = cjffsj.substring(0,4);
			yf = cjffsj.substring(4,6);
		}
		String totalMoney = qgzxDao.getTotalMoneyForZjcmxy(nd, yf);
		List<HashMap<String, String>> list = qgzxDao.getPayInfoByZjcmxy(nd,yf);		
		try{
		 WritableSheet ws = wwb.getSheet(0);
		 WritableCellFormat wcfTytle = new WritableCellFormat();
		 Alignment alignMent = Alignment.CENTRE;
		 VerticalAlignment vag = VerticalAlignment.CENTRE;
		 
		 wcfTytle.setVerticalAlignment(vag);
		 wcfTytle.setAlignment(alignMent);
		 wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);
		 
		 WritableFont wfTytle = new WritableFont(WritableFont.ARIAL);
		 wfTytle.setBoldStyle(WritableFont.BOLD);
		 wfTytle.setPointSize(14);
		 wcfTytle.setFont(wfTytle);
		 
		 ws.addCell(new Label(0,0,xxmc + "学生勤工助学工资发放表" ,wcfTytle));	
		 
		 wcfTytle = new WritableCellFormat();
		 alignMent = Alignment.LEFT;
		 vag = VerticalAlignment.CENTRE;
		 
		 wcfTytle.setVerticalAlignment(vag);
		 wcfTytle.setAlignment(alignMent);
		 wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);
		 
		 wfTytle = new WritableFont(WritableFont.ARIAL);
		 wfTytle.setBoldStyle(WritableFont.BOLD);
		 wfTytle.setPointSize(12);
		 wcfTytle.setFont(wfTytle);
		 
		 ws.addCell(new Label(0,2,"时间：" + nd + "年" + yf + "月份",wcfTytle));
		 
		 wcfTytle = new WritableCellFormat();
		 alignMent = Alignment.CENTRE;
		 vag = VerticalAlignment.CENTRE;
		 
		 wcfTytle.setVerticalAlignment(vag);
		 wcfTytle.setAlignment(alignMent);
		 wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);
		 
		 wfTytle = new WritableFont(WritableFont.ARIAL);
		 wfTytle.setPointSize(10);
		 wcfTytle.setFont(wfTytle);
		 
		 ws.addCell(new Label(0,3,"序号",wcfTytle));
		 ws.addCell(new Label(1,3,"银行卡号",wcfTytle));
		 ws.addCell(new Label(2,3,"学生姓名",wcfTytle));
		 ws.addCell(new Label(3,3,"用工部门",wcfTytle));
		 ws.addCell(new Label(4,3,"所属"+Base.YXPZXY_KEY,wcfTytle));
		 ws.addCell(new Label(5,3,"贫困/特困/灾区/非困",wcfTytle));
		 ws.addCell(new Label(6,3,"银行",wcfTytle));
		 ws.addCell(new Label(7,3,"发放金额",wcfTytle));
		 
		 for(int i=0; i<list.size(); i++){
			 HashMap<String, String> map = new HashMap<String, String>();
			 map = (HashMap<String, String>) list.get(i);
			 ws.addCell(new Label(0,4+i,String.valueOf(i+1),wcfTytle));
			 ws.addCell(new Label(1,4+i,map.get("kh"),wcfTytle));
			 ws.addCell(new Label(2,4+i,map.get("xm"),wcfTytle));
			 ws.addCell(new Label(3,4+i,map.get("yrdwmc"),wcfTytle));
			 ws.addCell(new Label(4,4+i,map.get("xymc"),wcfTytle));
			 ws.addCell(new Label(5,4+i,qgzxDao.getKnslx(map.get("xh")),wcfTytle));
			 ws.addCell(new Label(6,4+i,map.get("yhmc"),wcfTytle));
			 ws.addCell(new Label(7,4+i,map.get("cjje"),wcfTytle));
			// totalMoney = map.get("totalMoney");
		 }
		 totalMoney = totalMoney == null || "".equalsIgnoreCase(totalMoney)? "0" : totalMoney;
		 
		 wcfTytle = new WritableCellFormat();
		 alignMent = Alignment.CENTRE;
		 vag = VerticalAlignment.CENTRE;
		 wcfTytle.setVerticalAlignment(vag);
		 wcfTytle.setAlignment(alignMent);
		 wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);
		 
		 wfTytle = new WritableFont(WritableFont.ARIAL);
		 wfTytle.setBoldStyle(WritableFont.BOLD);
		 wfTytle.setPointSize(12);
		 wcfTytle.setFont(wfTytle);
		 
		 ws.mergeCells(0, list.size()+4, 7, list.size()+4);
		 ws.addCell(new Label(0,list.size()+4,"合计金额:" + totalMoney,wcfTytle));
		 
		 }catch(Exception e){
			 e.printStackTrace();
		 }
		 //向客户端输出
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	/**
	 * 删除岗位信息
	 * */
	public boolean delePost(String pk,HttpServletRequest request) throws Exception{
		boolean flag = false;
		String[] pkValues = pk.split("!!");
		String tableName = "gwxxb";
		String primaryKey = "gwdm||gwsbsj";
		String mes = "";
		
		for(int i=0; i<pkValues.length; i++){
			flag = StandardOperation.delete(tableName, primaryKey, pkValues[i], request);//删除岗位
			if(flag){
				flag = StandardOperation.delete("xsgwxxb", primaryKey, pkValues[i], request);//删除学生申请岗位信息
			}
			if(flag==false){
				mes += "第" + i + "条数据删除失败!";
			}
		}
		request.setAttribute("mes", mes);
		return flag;
	}
	
	/**
	 * 删除学生岗位信息
	 * */
	public boolean deleStuPost(String pk,HttpServletRequest request) throws Exception{
		boolean flag = false;
		String[] pkValues = pk.split("!!");
		String tableName = "xsgwxxb";
		String primaryKey = "xh||gwdm||sqsj";
		String mes = "";
		
		for(int i=1; i<pkValues.length; i++){
			flag = StandardOperation.delete(tableName, primaryKey, pkValues[i], request);//删除学生岗位信息 			
			if(flag==false){
				mes += "第" + i + "条数据删除失败!";
			}
		}
		request.setAttribute("mes", mes);
		return flag;
	}
	
	/**
	 * 删除学生岗位信息
	 * */
	public boolean modiStuPost(String pk,HttpServletRequest request) throws Exception{
		boolean flag = false;
		String[] pkValues = pk.split("!!");
		String tableName = "xsgwxxb";
		String primaryKey = "xh||gwdm||sqsj";
		String mes = "";
		
		for(int i=1; i<pkValues.length; i++){
			flag = StandardOperation.update(tableName, new String[]{"sfyx"}, new String[]{"0"}, primaryKey, pkValues[i], request); 	//sfyx='0' 无效		
			if(flag==false){
				mes += "第" + i + "条数据操作失败!";
			}
		}
		request.setAttribute("mes", mes);
		return flag;
	}
	
	/**
	 * 判断是否在勤工助学申请时间内
	 * @return String
	 * @throws Exception 
	 * */
	public String getSqsjFlag() throws Exception{
		return qgzxDao.getSqsjFlag();
	}
	
	/**
	 * 获取勤工助学申请结果查询表头
	 * @return List
	 * */
	public List<HashMap<String, String>> getXsgwxxToptr(){
		String xxdm = StandardOperation.getXxdm();
		String[] colList = null;
		
		if (xxdm.equalsIgnoreCase(Globals.XXDM_SHGC)) {
			//上海工程技术大学
			colList = new String[] { "主键", "xn", "nd", "xqmc", "xh", "xm",
					"bjmc", "sfpks", "gwdm", "sqsj", "xyyj", "xxyj", "kh" };
		} else if (xxdm.equalsIgnoreCase(Globals.XXDM_YNYS)) {
			// 云南艺术
			colList = new String[] { "主键", "xn", "nd", "xqmc", "xh", "xm",
					                 "bjmc", "gwdm", "sqsj", "sfpks", "xyyj", 
					                 "xxyj", "kh", "bh", "gh" };
		}else if(xxdm.equalsIgnoreCase(Globals.XXDM_ZJJDZYJSXY)){
			//浙江机电职业技术学院
			colList = new String[] { "主键", "xn", "nd", "xqmc", "xh", "xm",
					"bjmc", "sfpks", "gwdm", "sqsj", "xxyj" };
		}else if(xxdm.equalsIgnoreCase(Globals.XXDM_ZGMSXY)){
			//中国美术学院
			colList = new String[]{"主键", "xn", "nd", "xqmc", "xh", "xm",
					"bjmc", "sfpks", "gwdm", "sqsj", "fdyyj","xyyj", "xxyj"};
		}else if(xxdm.equalsIgnoreCase(Globals.XXDM_HNGYDX)){
			//湖南工业大学
			colList = new String[] { "主键", "xn", "nd", "xqmc", "xh", "xm",
					"bjmc", "sfpks", "gwdm", "sqsj", "fdyyj", "xxyj", "xyyj"};
		}else if(xxdm.equalsIgnoreCase(Globals.XXDM_ZGDZDX)){
			//中国地质大学
			colList = new String[] { "主键", "xn", "nd", "xqmc", "xh", "xm",
					"bjmc", "sfpks", "gwdm", "sqsj", "lxdh", "xxyj" };
		}else if(xxdm.equalsIgnoreCase(Globals.XXDM_NBLGXY)){
			//宁波理工学院
			//============begin 骆嘉伟 2009/4/1 =========
			colList = new String[] { "主键", "xn", "nd", "xqmc", "xh", "xm",
					"bjmc", "sfpks", "gwdm", "sqsj", "xyyj", "xxyj" };
			//============end 骆嘉伟 2009/4/1 =========
		}else if(Globals.XXDM_GZDX.equalsIgnoreCase(xxdm)){
			//广州大学
			colList = new String[] { "主键", "xn", "nd", "xqmc", "xh", "xm",
					"bjmc", "sfpks", "gwdm", "sqsj", "sfyx", "xyyj", "xxyj" };
		}else if(Globals.XXDM_ZJKJXY.equalsIgnoreCase(xxdm)){
			//浙江科技学院
			colList = new String[] { "主键", "xn", "nd", "xqmc", "xh", "xm",
					"bjmc", "sfpks", "yrdwmc", "gwdm", "sqsj", "lxdh", "xyyj", "xxyj", "已录取岗位" };
		}else if(Globals.XXDM_HBJTZYJSXY.equals(xxdm)){
			//湖北交通
			colList = new String[] { "主键", "xn", "nd", "xqmc", "xh", "xm",
					"bjmc", "sfpks", "gwdm", "sqsj", "fdyyj","xyyj", "xxyj" };
		}else if(xxdm.equalsIgnoreCase(Globals.XXDM_WHSYFWXY)){
			//武汉商业
			colList = new String[] { "主键", "xn", "nd", "xqmc", "xh", "xm",
					"bjmc", "sfpks", "gwdm", "sqsj", "fdyyj","xyyj", "xxyj" };
		}else if(xxdm.equalsIgnoreCase(Globals.XXDM_ZJJSZYJSXY)){
			//浙江建设
			colList = new String[] { "主键", "xn", "nd", "xqmc", "xh", "xm",
					"bjmc", "sfpks", "gwdm", "sqsj","xxyj" };
		}else if(xxdm.equalsIgnoreCase(Globals.XXDM_ZJJTZYJSXY)){
			//浙江交通职业技术学院
			colList = new String[] { "主键", "xn", "nd", "xqmc", "xh", "xm",
					"bjmc", "sfpks", "gwdm", "sqsj","fdyyj","xyyj", "xxyj" };
		} else {
			colList = new String[] { "主键", "xn", "nd", "xqmc", "xh", "xm",
					"bjmc", "sfpks", "gwdm", "sqsj","xyyj", "xxyj" };
		}
		String[] colCN = qgzxDao.getColumnNameCN(colList, "view_xsgwxx");
		
		return qgzxDao.arrayToList(colList, colCN);
	}
	
	/**
	 * 查询岗位详细信息
	 * @param pkValue
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getPostDetail(String pkValue){
		QgzxDao dao = new QgzxDao();
		HashMap<String, String> map = new HashMap<String, String>();
		map = dao.getPostDetail(pkValue);
		return map;
	}
	
	/**
	 * 勤工助学申请结果查询
	 * @param model
	 * @return List
	 * */
	public List<String[]> searchXsgwxx(CommanForm model){		
		return qgzxDao.querryXsgwxx(model);
	}
	
	/**
	 * 获取设定的岗位困难生使用比例
	 * @return Double
	 * */
	public Double getKnsbl(){
		return qgzxDao.getKnsbl();		
	}
	
	/**
	 * 获取岗位的空闲时间
	 * @param pkVal
	 * @return List
	 * */
	public List<HashMap<String, String>> getGzsjKxbz(String pkVal){
		return qgzxDao.getGzsjKxbz(pkVal);	
	}
	
	/**
	 * 组合页面表格数据
	 * @return List	 
	 * */
	public List<HashMap<String, String>> getKxList(){
		List<HashMap<String, String>> kxList = new ArrayList<HashMap<String,String>>();
		String[] sj = {"第1-2节", "第3-4节", "午休", "第5-6节", "第7-8节", "晚上" };
		for (int i = 0; i < 6; i++) {
			HashMap<String, String> map2 = new HashMap<String, String>();
			map2.put("sj", sj[i]);
			map2.put("sjIndex", String.valueOf(i));
			kxList.add(map2);
		}
		
		return kxList;
	}
	
	/**
	 * 获取月份信息
	 * @return List
	 * */
	public List<HashMap<String, String>> getYfList(){
		QgzxDao dao = new QgzxDao();
		return dao.getYfList();
	}
	
	/**
	 * 获取日期信息
	 * @return List<HashMap<String, String>>
	 * */
	public List<HashMap<String, String>> getRqList(){
		List<HashMap<String, String>> rs = new ArrayList<HashMap<String,String>>();		
		for(int i=0; i<31; i++){
			HashMap< String, String> map = new HashMap<String, String>();
			map.put("rq", (i+1)+"");
			rs.add(map);
		}		
		return rs;
	}
	
	
	/**
	 * 获取岗位的详细信息
	 * @param model
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getWorkInfo(CommanForm model){
		QgzxDao dao = new QgzxDao();
		HashMap<String, String> tmpMap = new HashMap<String, String>();
		tmpMap = dao.getWorkInfo(model);//岗位的相关信息
		tmpMap.put("syjf", dao.getSyjf(model));//剩余经费
		return tmpMap;
	}
	
	/**
	 * 获取某岗位下的学生
	 * @param model
	 * @return List
	 * */
	public List<HashMap<String, String>> getStuByPost(CommanForm model){
		QgzxDao dao = new QgzxDao();
		return dao.getStuByPost(model);//获取某岗位下的学生
	}
	
	/**
	 * 工资补发保存
	 * @param model
	 * @param request
	 * @return boolean
	 * */
	public boolean saveReWorkPay(CommanForm model, HttpServletRequest request){		
		boolean flag = false;		
		int len = 0;
		String mes = "";
		String count = model.getCount();
		count = count == null || "".equalsIgnoreCase(count) ? "0" : count;	
		String tableName = "xscjffb";
		String[] columns = {"xh", "gwdm", "sqsj", "nd", "yf", "xn", "cjje", "gzsj","bz"};	//申请时间即岗位申报时间	
		String pk = "xh||gwdm||sqsj||nd||yf";
		len = Integer.parseInt(count);
		String xn = model.getXn();
		String nd = model.getNd();
		String yf = model.getYf();
		HashMap<String, String> pMap = new HashMap<String, String>();
		HashMap<String, String> sMap = new HashMap<String, String>();
		pMap = qgzxDao.getWorkInfo(model);
		
		
		String gwdm = pMap.get("gwdm");
		String sqsj = pMap.get("gwsbsj");
		for(int i=0; i<len; i++){
			String xh = request.getParameter("xh" + i);
			String cjje = request.getParameter("cjje" + i);
			String gzsj = request.getParameter("gzsj" + i);
			String bz = request.getParameter("bz" + i);
			String[] values = {xh,gwdm,sqsj,nd,yf,xn,cjje,gzsj,bz};
			if(cjje != null && !"".equalsIgnoreCase(cjje)){//酬金金额不为空				
				String pkValue = xh + gwdm + sqsj + nd + yf;
				if(qgzxDao.checkExists(tableName, pk, pkValue)){
					sMap = qgzxDao.getStuInfo(xh);
					
					mes += sMap.get("xm") + "在" + nd + "年" + yf + "月已经发放过酬金，补发失败，请核查！\n"; 
				}else{
					flag  = StandardOperation.insert(tableName, columns, values, request);
				}
			}
		}
		request.setAttribute("mes", mes);
		return flag;
	}
	
	public String checkPostAudi(String pkValue,String xhValue,String type,String userType,String userName){
		String message = "";
		HngydxGwglDAO gwDao = new HngydxGwglDAO();	
		String[] value = xhValue.split("!!SplitOneSplit!!");
		String zd = "";
		
		if(userType.equalsIgnoreCase("xx")||userType.equalsIgnoreCase("admin")){
			zd = " xxyj ";
		}
	
		//用人单位审核
		if(gwDao.checkIsYrdw(userName)){
			zd = "xyyj";
		}
		if(type != null && "pass".equalsIgnoreCase(type)){
			for(int i=0; i<value.length; i++){
				if(qgzxDao.getDataCount("xsgwxxb", "xh||"+zd.trim(), value[i].trim()+"通过")>0){
					message += value[i]+"已经有岗位审核通过,审核操作失败!\n";
				}
			}
		}
		if("".equalsIgnoreCase(message)){
			postStuBatchAudi(pkValue,type,userType,userName);
		}
		return message;
	}
	
	public String checkPostCount(String pkValue,String type){
		String message = "";
		String[] value = pkValue.split("!!SplitOneSplit!!");
		String[] gwmc = new String[value.length];
		String[] tmpGw = new String[value.length];
		String pk = "xh||gwdm||sqsj";
		boolean flag = false;

		if(type != null && "pass".equalsIgnoreCase(type)){
			for(int i=0; i<value.length; i++){
				flag = false;
				String gw = qgzxDao.getGwmc(pk, value[i]);				
				for(int j=0; j<gwmc.length; j++){
					if(gwmc[j] != null && gwmc[j].equalsIgnoreCase(gw)){
						flag = true;
					}
				}
				gwmc[i] = gw;
				if(flag == false){
					tmpGw[i] = gw;
				}
			}
		}
		for(int i=0; i<tmpGw.length; i++){
			if(tmpGw != null && !"".equalsIgnoreCase(tmpGw[i])){
				int gwsyrs = qgzxDao.getGwtgrs(tmpGw[i]);
				int gwyshrs = 0;
				for(int j=0; j<gwmc.length; j++){
					if(DealString.toString(gwmc[j]).equalsIgnoreCase(tmpGw[i])){
						gwyshrs += 1;
					}
				}
				if(gwyshrs>gwsyrs){
					message += qgzxDao.getGwdm(tmpGw[i]) + "还剩" + gwsyrs + "个名额，请按此人数进行提交!\n";
				}
			}
		}
		return message;
	}
	
	
	/**
	 * 中国地质大学检测审核时间
	 * @return boolean
	 * */
	public boolean checkAudiTime(){
		DAO dao = DAO.getInstance();
		boolean flag = false;
		String sql = "select shkssj, shjssj, to_char(sysdate,'YYYYMMDDHH24MISS')nowtime from gwsqsjb";
		HashMap<String, String> map = new HashMap<String, String>();
		map = dao.getMap(sql, new String[]{}, new String[]{"shkssj", "shjssj", "nowtime"});
		
		String shkssj = map.get("shkssj");
		String shjssj = map.get("shjssj");
		String nowtime = map.get("nowtime");
		
		shkssj = shkssj == null ? "" : shkssj;
		shjssj = shjssj == null ? "" : shjssj;
		
		if("".equalsIgnoreCase(shkssj) &&(!"".equalsIgnoreCase(shjssj) && Double.parseDouble(shjssj)<Double.parseDouble(nowtime)) ){
			flag = false;
		}else if("".equalsIgnoreCase(shjssj) && (!"".equalsIgnoreCase(shjssj) && Double.parseDouble(shkssj)>Double.parseDouble(nowtime))){
			flag = false;
		}else if("".equalsIgnoreCase(shkssj) && "".equalsIgnoreCase(shjssj)){
			flag = true;
		}else if((Double.parseDouble(shkssj)>Double.parseDouble(nowtime)) || Double.parseDouble(shjssj)<Double.parseDouble(nowtime)){
			flag = false;
		}else{
			flag = true;
		}
		return flag;
	}
	
	/**
	 * 判断学生所申请岗位的剩余岗位人数
	 * @param pk
	 * @param pkValue
	 * @return int
	 * */
	public int getSygwrs(String pk, String pkValue){
		QgzxDao dao = new QgzxDao();
		return dao.getSygwrs(pk, pkValue);
	}
	
	/**
	 * 根据主键获取岗位信息
	 * @param pk
	 * @param pkValue
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getGwInfo(String pk, String pkValue){
		QgzxDao dao = new QgzxDao();		
		return dao.getGwInfo(pk,pkValue);
	}
	
	/**
	 * 判断记录是否存在 
	 * @param tableName
	 * @param pk
	 * @param pkValue
	 * @return boolean
	 * */
	public boolean checkExists(String tableName, String pk, String pkValue){
		return qgzxDao.checkExists(tableName, pk, pkValue);
	}
	
	/**
	 * 判断是否在上报的时间之内
	 * @param yrdwdm
	 * @return boolean
	 * */
	public boolean checkShsbsj(String yrdwdm){
		return qgzxDao.checkShsbsj(yrdwdm);
	}
	
	/**
	 * 根据用人单位获取考核上报时间信息 
	 * @param yrdwdm
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getKhsbsjByYrdw(String yrdwdm){
		return qgzxDao.getKhsbsjByYrdw(yrdwdm);
	}
	
	/**
	 * 获取导出查询字段的语句
	 * @param tableName
	 * @return String 
	 * */
	public String getExpColumn(String tableName){
		String xxdm = StandardOperation.getXxdm();
		String sql = "";
		if(qgzxDao.checkExists("dcb", "xxdm||zdssb", xxdm+tableName)){
			String zd = qgzxDao.getExpColumn(xxdm, tableName);
			sql = "select " + zd + " from " + tableName + " a ";
		}else{
			sql = "select * from " + tableName + " a ";
		}
		
		return sql;
	}
	
	/**
	 * 获取导入查询字段的语句
	 * @param tableName
	 * @return String 
	 * */
	public String getImpColumn(String tableName){
		String xxdm = StandardOperation.getXxdm();
		String sql = "";
		if(qgzxDao.checkExists("drb", "xxdm||zdssb", xxdm+tableName)){
			String zd = qgzxDao.getImpColumn(xxdm, tableName);
			sql = "select " + zd + " from " + tableName;
		}else{
			sql = "select * from " + tableName;
		}		
		return sql;
	}
	
	/**
	 * 获取辅导员下的专业
	 * @param zgh
	 * */
	public List<HashMap<String, String>> getZyListByFdy(String zgh){
		return qgzxDao.getZyByFdy(zgh);		
	}
	
	/**
	 * 获取辅导员下的班级
	 * @param zgh
	 * */
	public List<HashMap<String, String>> getBjListByFdy(String zgh){
		return qgzxDao.getBjByFdy(zgh);
	}
	
	/**
	 * 判断学生是否是困难生
	 * @param xh
	 * */
	public boolean isKns(String xh){
//		return true;
		return qgzxDao.isKns(xh);
	}
	
	/**
	 * 是否贫困生字段从数据库中取改为通过程序判断
	 * @param List<String[]> rs
	 * @return List<String[]> 
	 * */
	public List<String[]> changeRsValue(List<String[]> rs){
		List<String[]> tempRs = new ArrayList<String[]>();
		for(int i=0; i<rs.size(); i++){
			String[] values = rs.get(i);
			String xh = values.length>6 ? values[6] : "";
			if(Globals.XXDM_ZJJTZYJSXY.equalsIgnoreCase(Base.xxdm)){
				values[10] = isKns(xh) == true ? "是" : "否";
				tempRs.add(values);
			}else{
				values[9] = isKns(xh) == true ? "是" : "否";
				tempRs.add(values);
			}
		}
		rs = tempRs;	
		return rs;
	}
	
	/**
	 * 查询审核通过岗位名称列表
	 * @param String userName
	 * @return List<HashMap<String, String>>
	 * */
	public List<HashMap<String, String>> getGwmcList(String userName){
		return qgzxDao.selectGwmcList(userName);
	}
	
	/**
	 * 根据是否审核通过条件查询岗位名称列表
	 * @param String userName
	 * @return List<HashMap<String, String>>
	 * */
	public List<HashMap<String, String>> getGwmcList(String userName,boolean isPass){
		return qgzxDao.selectGwmcList(userName,isPass);
	}
	
	/**
	 * 查询用人单位岗位名称列表
	 * @param String sqdwdm
	 * @param boolean shFlag
	 * @return List<HashMap<String, String>>
	 * */
	public List<HashMap<String, String>> getYrdwGwList(String userName,String sqdwdm, String gwxzdm,boolean shFlag){
		return qgzxDao.getYrdwGwList(userName, sqdwdm, gwxzdm, shFlag);
	}
	
	/**
	 * 查询用人单位岗位名称列表
	 * @param String sqdwdm
	 * @param boolean shFlag
	 * @return List<HashMap<String, String>>
	 * */
	public List<HashMap<String, String>> getNotUserGwList(String userName,String sqdwdm, String gwxzdm,boolean shFlag){
		return qgzxDao.getNotUserGwList(userName, sqdwdm, gwxzdm, shFlag);
	}
	
	/**
	 * 获取列的中文名称
	 * @param String[] colList
	 * @param String tableName
	 * @return String[]
	 * */
	public String[] getColumnNameCN(String[] colList,String tableName){
		return qgzxDao.getColumnNameCN(colList, tableName);
	}
	
	/**
	 * 查询岗位详细信息
	 * @param String pk
	 * @param String pkValue
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> queryGwxx(String pk, String pkValue){
		return qgzxDao.selectGwxx(pk,pkValue);
	}
	
	/**
	 * 查询岗位详细信息
	 * @param String pk
	 * @param String pkValue
	 * @param String userDep
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> queryGwxx(String pk, String pkValue,String userDep){
		return qgzxDao.selectGwxx(pk,pkValue,userDep);
	}
	
	/**
	 * 查询参加岗位的学生列表
	 * @param String pk
	 * @param String pkValue
	 * @return List<String[]>
	 * */
	public List<HashMap<String, String>>  getCjgwxsList(String pk, String pkValue){
		return qgzxDao.selectCjgwxs(pk,pkValue);
	}	
	
	/**
	 * 判断用户是否是用人单位用户
	 * @param String userName
	 * @return boolean
	 * */
	public boolean isYrdwUser(String userName){
		return qgzxDao.isYrdwUser(userName);
	}
	
	/**
	 * 获取用人单位用户的用人单位代码
	 * @param String userName
	 * @return boolean
	 * */
	public String getYrdwUser(String userName){
		HngydxGwglDAO dao=new HngydxGwglDAO();
		return dao.getYrdwdmByUser(userName);
	}
	
	/**
	 * 查询岗位列表
	 * */
	public List<HashMap<String, String>> getGwList(CommanForm model){
		return qgzxDao.selectGwList(model);
	}
	
	/**
	 * 查询学生岗位列表
	 * */
	public List<HashMap<String, String>> getStuGwList(CommanForm model){
		return qgzxDao.selectStuGwList(model);
	}
	
	/**
	 * 查询班级抽查记录中不同用户类型的记录是否已经处理过
	 * @param pkValue
	 * @return boolean
	 * */
	public HashMap<String, String> queryXsgwxxByPk(String pkValue){
		return qgzxDao.selectXsgwxxbByPk(pkValue);
	}
	
	/**
	 * 查询计酬方式代码列表
	 * @param boolean oneNull
	 * @return List<HashMap<String, String>>
	 * */
	public List<HashMap<String, String>> queryJcfsList(boolean oneNull){
		return qgzxDao.selectJcfsdmbList(oneNull);
	}
	
	/**
	 * 查询岗位名称信息列表
	 * @param paramter
	 * @return List<HashMap<String, String>>
	 * */
	public List<HashMap<String, String>> getGwmcxxList(HashMap<String, String> paramter,String isLsgw){
		return qgzxDao.getGwmcxxList(paramter,isLsgw);
	}
	
	
	/**
	 * 查询非岗位发布人发布的岗位名称信息列表
	 * @param paramter
	 * @return List<HashMap<String, String>>
	 * */
	public List<HashMap<String, String>> getFgwfbrGwmcxxList(HashMap<String, String> paramter,String isLsgw){
		return qgzxDao.getFgwfbrGwmcxxList(paramter,isLsgw);
	}
	
	/**
	 * 获取列表
	 * @param type
	 * */
	public List<HashMap<String, String>> getChkList(int type){
		return qgzxDao.getChkList(type);
	}
	
	/**
	 * 获取职位性质代码列表
	 * @return List<HashMap<String, String>>
	 * */
	public List<HashMap<String, String>> getZwxzdmList(){
		return qgzxDao.getZwxzdmList();
	}
	
	/**
	 * 获取岗位信息表
	 */
	public HashMap<String,String>getGwxx(String pkValue){
		return qgzxDao.getGwxx(pkValue);
	}
	
	/**
	 * 返回学生岗位审核通过的条件
	 * @return String
	 * */
	public String appendTggwtj(){
		String sql = "";
		if(Globals.XXDM_ZJKJXY.equalsIgnoreCase(Base.xxdm)){
			//浙江科技学院
			sql = " and xxyj='通过'";
		}
		
		return sql;
	}
	
	/**
	 * 判断是否可申请多个岗位
	 * @return boolean
	 * */
	public boolean checkSqdg(){
		boolean flag = false;
		if(Globals.XXDM_ZGDZDX.equalsIgnoreCase(Base.xxdm)//中国地质大学
				|| Globals.XXDM_ZJKJXY.equalsIgnoreCase(Base.xxdm)//浙江科技
				|| Globals.XXDM_MJXY.equalsIgnoreCase(Base.xxdm)){//闽江学院
			flag = true;
		}
		return flag;
	}
	
	/**
	 * 查询申请岗位的学生列表
	 * @param String pk
	 * @param String pkValue
	 * @return List<String[]>
	 * */
	public List<HashMap<String, String>>  getSqgwxsList(String pk, String pkValue){
		return qgzxDao.selectSqgwxs(pk,pkValue);
	}
	
	public boolean saveYrdwshkz(CommanForm model) throws Exception{
		
		return qgzxDao.saveYrdwshkz(model);
	}
}