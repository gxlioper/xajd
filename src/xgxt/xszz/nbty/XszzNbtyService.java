package xgxt.xszz.nbty;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import xgxt.action.Base;
import xgxt.pjpy.tginterface.PjpyCommonInterface;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.String.NullStringException;
import xgxt.utils.String.StringUtils;
import xgxt.xszz.commonN05.XszzCommonN05Service;

/**
 * Title: 学生工作管理系统
 * Description:宁波天一资助Service
 * Copyright: Copyright (c) 2010
 * Company: zfsoft
 * Author: sjf
 * Version: 1.0
 * Time: 2010-6-25
 */
public class XszzNbtyService {
	
	private final String tableName = "nbty_gjzxdksqb";
	private final String viewName = "view_nbty_gjzxdksq";
	/**
	 * 通过学号获得学生的家庭信息和学生信息
	 * @param xh
	 * @return
	 */
	public Map<String, String> getStuInfo(String xh){
		// 学生信息，key为表中字段，value为对应表中的值
		Map<String,String> stuInf = new HashMap<String,String>();
		
		if (!Base.isNull(xh)) {
			// 需要获取的学生信息字段
			String[] colList1 = new String[] { "xh", "xm", "xb", "csrq", "nj",
					 "xydm", "xymc", "zydm", "zymc", "bjdm", "bjmc", "sfzh",
					 "byny", "lxdh", "xz", "ssbh", "qsdh"};
			// 需要获取的家庭信息字段
			String[] colList2 = new String[]{"jtszd","jtyb", "lxdh1", "jtcy1_xm", "jtcy1_gx", "jtcy1_gzdz", 
					"jtcy1_lxdh1", "jtcy2_xm", "jtcy2_gx", "jtcy2_gzdz", "jtcy2_lxdh1",
					"jtcy3_xm", "jtcy3_gx","jtcy3_gzdz","jtcy3_lxdh1"};
			
			// 查询学生是否有违纪处分信息
			PjpyCommonInterface pjInterface = new PjpyCommonInterface();
			HashMap<String, String> rs = new HashMap<String, String>();
			rs.put("xh", xh);
			// 统一放到学生信息Map中
	        stuInf.put("ywwjcf", pjInterface.queryStuCfxx(rs).size()>0 ? "有" : "无");
			stuInf.putAll(CommonQueryDAO.commonQueryOne("view_xsxxb", colList1, "xh",xh));
			stuInf.putAll(CommonQueryDAO.commonQueryOne("view_xsjtxx",colList2,"xh",xh));
		}
		
		return stuInf;
	}
	
	/**
	 * 判断是否正在被审核，通过判断辅导员审核字段，辅导员已经审核了，即被审核
	 * @param tableName
	 * @param fields
	 * @param pkVal
	 * @return
	 */
	public boolean isAuditing(String xh, String xn){
		boolean isFlag = false;
		
		// 只需判断辅导员是否审核，学院和学校审核通过，辅导员一定已经通过
		String[] colList = new String[]{"fdysh"};
		String querry = " where xh='"+xh+"' and "+ "xn='"+ xn+"'";
		
		// 获取辅导员审核字段
		Map<String, String> map = CommonQueryDAO.dao_getInfo(tableName, colList, querry);
		
		// 判断是否审核了
		if(map!=null){
			if("通过".equals( map.get("fdysh"))){
				isFlag = true;
			}
		}
		return isFlag;
	}
	
	/**
	 * 把userType 分成四类，其他，辅导员，学院，学校
	 * @param session
	 * @return
	 */
	public String getUserType(HttpSession session){
		boolean isFdy = Boolean.valueOf(session.getAttribute("isFdy").toString());
		String userType = (String)session.getAttribute("userType");
		
		if(isFdy){
			userType = "fdy";
		}else if("xy".equalsIgnoreCase(userType)){
			userType = "xy";
		}else if("admin".equalsIgnoreCase(userType) || ("xx".equalsIgnoreCase(userType))){
			userType = "xx";
		}
		return userType;
	}
	
	/** 获得某学生的所有国家助学贷款信息
	 * @param xh
	 * @return
	 */
	public List<String[]> getXsgjzxdkxx(String xh){
		String query = " where xh=?";
		String[] colList = new String[]{"xh","xn","xm","xb","bjmc",
				"dkje","hkfs","fdysh","xysh","xxsh"};
		
		List<String[]> list = CommonQueryDAO.commonQueryNotFy(viewName, query, new String[]{xh}, colList, "");
		return list;
	}
	
	/**
	 * 得到国家助学贷款信息
	 * @param pkValue
	 * @return
	 */
	public Map<String, String> getGjzxdkxx(String pkValue){
		Map<String, String> map = new HashMap<String, String>();
		if(!StringUtils.isNull(pkValue)){
			// 需要获得的字段
			String[] colList = new String[]{"xh", "xn", "jzr1", "jzr2", "sfzh1", "sfzh2", "xxqkztpj",
					"bjgbxkms", "sxnpddd", "ywblxyjl", "dkje", "hkfs", "fdysh", "xysh", "xxsh", "fdypy",
					"xyshyj", "xxshyj", "xm", "xb", "nj", "csrq", "xz", "sfzh", "xymc", "zymc", "bjmc",
					"sjhm", "ssbh", "qsdh","lxdh", "lxdh1", "jtszd", "jtyb", "byny", "jtcy1_xm", "jtcy1_gx",
					"jtcy1_gzdz", "jtcy1_lxdh1", "jtcy2_xm", "jtcy2_gx", "jtcy2_gzdz", "jtcy2_lxdh1",
					"jtcy3_xm","jtcy3_gx","jtcy3_gzdz","jtcy3_lxdh1","fdyshsj", "sqsj", "xyshsj", "xxshsj"};
			
			// 获取主键值为pkValue的贷款信息
			map = CommonQueryDAO.commonQueryOne(viewName, colList, "xh||xn", pkValue);
			// 添加处分信息
			PjpyCommonInterface pjInterface = new PjpyCommonInterface();
			HashMap<String, String> rs = new HashMap<String, String>();
			rs.put("xh", map.get("xh"));
			map.put("ywwjcf", pjInterface.queryStuCfxx(rs).size()>0 ? "有" : "无");
		}
		return map;
	}
	
	/**
	 * 把map里面的家庭成员1，和家庭成员2,家庭成员3 转换为父母亲
	 * @param stuInf
	 * @throws NullStringException 
	 */
	public void changeRelationToParents(Map<String, String> stuInf) throws NullStringException{
		String[] relation = new String[]{stuInf.get("jtcy1_gx"), stuInf.get("jtcy2_gx"), stuInf.get("jtcy3_gx")};
		for(int i=0;i<relation.length;i++){
			int temp = i+1;
			String xm = "jtcy" + temp + "_xm";
			String gzdz = "jtcy" + temp + "_gzdz";
			String lxdh = "jtcy" + temp + "_lxdh1";
			
			// 判断家庭成员1是否为父亲
			if(relation[i] != null && (relation[i].contains("父") || relation[i].contains("爸"))){
				// stuInf中放入父亲信息
				stuInf.put("fqxm", stuInf.get(xm));
				stuInf.put("fqdw", stuInf.get(gzdz));
				stuInf.put("fqsj", stuInf.get(lxdh));
			}
			
			// stuInf中放入母亲信息
			if(relation[i] != null && (relation[i].contains("母") || relation[i].contains("妈"))){
				// stuInf中放入母亲信息
				stuInf.put("mqxm", stuInf.get(xm));
				stuInf.put("mqdw", stuInf.get(gzdz));
				stuInf.put("mqsj", stuInf.get(lxdh));
			}
			
		}
	
	}
	
	
	/**
	 * 或得学生每一学年贷款的详细信息
	 * @param xh
	 */
	public Map<String, Map<String, String>> getMxndkxx(String xh){ 
		// 获得该学生的所有学年
		XszzCommonN05Service service = new XszzCommonN05Service();
		List<HashMap<String, String>> list = service.getStuZxxn(xh);
		
		// 用于计算贷款总额
		double amount = 0;
		
		// 存放每学年的详细信息，key为第几学年，value为该学年的详细信息,
		Map<String, Map<String, String>> dkxx = new HashMap<String, Map<String, String>>();
		// 你每个学年拿详细信息
		for(int i=0; i<list.size(); i++){
			
			String key = "xn" + (i+1);
			// 没学年的详细信息
			Map<String, String> map = null;
			String xn = list.get(i).get("xn");
			String[] colList = new String[]{"dkje","xxsh"};
			if(StringUtils.isNotNull(xn)){
				String querry = " where xh='"+xh+"' and "+ "xn='"+ xn+"'";
				map = CommonQueryDAO.dao_getInfo(tableName, colList, querry);
				
				// 三种情况,申请通过，正在申请，未申请
				if(map!=null && !map.isEmpty()){
					amount += Double.parseDouble(map.get("dkje"));
					if("通过".equalsIgnoreCase(map.get("xxsh"))){
						map.put("sqzk", "已申请成功");
					}else{
						map.put("sqzk", "正在申请中");
					}
				}else{
					map = new HashMap<String, String>();
					map.put("sqzk", "未申请");
					map.put("dkje", "0");
				}
			}
			
			dkxx.put(key, map);
		}
		
		// 放入贷款总额
		Map<String, String> dkze = new HashMap<String, String>();
		dkze.put("dkze", String.valueOf(amount));
		dkxx.put("dkze",dkze);
		return dkxx;
	}
}
