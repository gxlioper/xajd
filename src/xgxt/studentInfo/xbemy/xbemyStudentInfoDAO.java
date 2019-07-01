
package xgxt.studentInfo.xbemy;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.action.base.BaseServicesUtil;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;

public class xbemyStudentInfoDAO {
	DAO dao = DAO.getInstance();
	
	/**
	 * 数据保存
	 * */
	public boolean saveInfo(Object obj,String[] input,String[] pk,String tabName,HttpServletRequest request) throws Exception{				
		XbemyStudentInfoForm xbemy = (XbemyStudentInfoForm)obj;
		HashMap<String, String> map = new HashMap<String, String>(); 
		String[] value = new String[input.length]; 
		
		boolean flag = false;
		int num = 0;
		String sql = "";
		String pkValue = "";
		String pkStr = "";		
		
		String sqrq = xbemy.getSqrq();
		String xh = xbemy.getXh();
		String xb = DealString.toGBK(xbemy.getXb());
		String xm = DealString.toGBK(xbemy.getXm());
		String daqk = DealString.toGBK(xbemy.getDaqk());
		String bz = DealString.toGBK(xbemy.getBz());
		String pqdwmc = DealString.toGBK(xbemy.getPqdwmc());
		String ffsj = DealString.toGBK(xbemy.getFfsj());
		String pqzbh = DealString.toGBK(xbemy.getPqzbh());
		String qdrxm = DealString.toGBK(xbemy.getQdrxm());
		//转学
		String mzdm = DealString.toGBK(xbemy.getMzdm());
		String zkzh = DealString.toGBK(xbemy.getZkzh());		
		String rxsj = xbemy.getRxsj();
		String lxdh = DealString.toGBK(xbemy.getLxdh());
		String zcxxmc = DealString.toGBK(xbemy.getZcxxmc());
		String zczymc = DealString.toGBK(xbemy.getZczymc());
		String zcnj = xbemy.getZcnj();
		String zcxz = DealString.toGBK(xbemy.getZcxz());
		String zcxlcc = DealString.toGBK(xbemy.getZcxlcc());
		String zrxxmc = DealString.toGBK(xbemy.getZrxxmc());
		
		String sqly = DealString.toGBK(xbemy.getSqly());
		String zcxxyj = DealString.toGBK(xbemy.getZcxxyj());
		String zrxxyj = DealString.toGBK(xbemy.getZrxxyj());
		String zcsjytyj = DealString.toGBK(xbemy.getZcsjytyj());
		String zrsjytyj = DealString.toGBK(xbemy.getZrsjytyj());
		String lqzydm = xbemy.getLqzydm();		
		String zxlx = DealString.toGBK(xbemy.getZxlx());
		String zczyxyyj = DealString.toGBK(xbemy.getZczyyxyj());
		String zrzyxyyj = DealString.toGBK(xbemy.getZrzyyxyj());
		String zjcyj = DealString.toGBK(xbemy.getZjcyj());
		String cwcyj = DealString.toGBK(xbemy.getCwcyj());
		String xscyj = DealString.toGBK(xbemy.getXscyj());		
		//转入学生信息
		String zrxydm = xbemy.getZrxydm();
		String zrxymc = DealString.toGBK(xbemy.getZrxymc());
		String zrzydm = xbemy.getZrzydm();
		String zrzymc = DealString.toGBK(xbemy.getZrzymc());
		String zrbjdm = xbemy.getZrbjdm();
		String zrbjmc = DealString.toGBK(xbemy.getZrbjmc());
		String zrnj = xbemy.getZrnj();
		String zrxz = xbemy.getZrxz();
		String zrxlcc = DealString.toGBK(xbemy.getZrxlcc());
		String zylb = DealString.toGBK(xbemy.getZylb());
		String zyfx = DealString.toGBK(xbemy.getZyfx());
		String pyfx = DealString.toGBK(xbemy.getPyfx());
		String fxzy = xbemy.getFxzy();
		String fxzyfx = DealString.toGBK(xbemy.getFxzyfx());
		String bxlx = DealString.toGBK(xbemy.getBxlx());
		String bxxs = DealString.toGBK(xbemy.getBxxs());
		String xxxs = DealString.toGBK(xbemy.getXxxs());
		String zsjj = DealString.toGBK(xbemy.getZsjj());
		String xxszd = DealString.toGBK(xbemy.getXxszd());
		String xjztm = DealString.toGBK(xbemy.getXjztm());
		String cym = DealString.toGBK(xbemy.getCym());
		String ksh = DealString.toGBK(xbemy.getKsh());
		String jg = DealString.toGBK(xbemy.getJg());
		String csd = DealString.toGBK(xbemy.getCsd());
		String byrq = DealString.toGBK(xbemy.getByrq());
		String bjyjl = DealString.toGBK(xbemy.getBjyjl());
		String zsbh = DealString.toGBK(xbemy.getZsbh());
		String zsxlh = DealString.toGBK(xbemy.getZsxlh());
		String xw = DealString.toGBK(xbemy.getXw());
		String xwzsbh = DealString.toGBK(xbemy.getXwzsbh());
		String xwzsxlh = DealString.toGBK(xbemy.getXwzsxlh());
		String xzxm = DealString.toGBK(xbemy.getXzxm());
		String shbj = DealString.toGBK(xbemy.getShbj());
		String dybj = DealString.toGBK(xbemy.getDybj());
		String thbs = DealString.toGBK(xbemy.getThbs());
		String csrq = xbemy.getCsrq();
		String sfzh = xbemy.getSfzh();
		String syd = DealString.toGBK(xbemy.getSyd());
		String zzmm = xbemy.getZzmm();
		//转出学生信息
		String zcbjdm = xbemy.getZcbjdm();
		String zcxydm = xbemy.getZcxydm();
		String zczydm = xbemy.getZczydm();
		//学生档案
		String gdxxspyj = DealString.toGBK(xbemy.getGdxxspyj());		
		String zzqjytbayj = DealString.toGBK(xbemy.getZzqjytbayj());
		String sqsj = sqrq;
		String qssj = xbemy.getQssj();
		String jzsj = xbemy.getJzsj();
		String ydlbdm = xbemy.getYdlbdm();
		String jtdh = xbemy.getJtdh();
		String jtdz = DealString.toGBK(xbemy.getJtdz());
		String ydqnj = xbemy.getYdqnj();
		String ydqzydm = xbemy.getYdqzydm();
		String ydqbjdm = xbemy.getYdqbjdm();
		String ydhnj = xbemy.getYdhnj();
		String ydhzydm = xbemy.getYdhzydm();
		String ydhbjdm = xbemy.getYdhbjdm(); 
		String ddqkdm = xbemy.getDdqkdm();
		
		//转入学生信息
		map.put("zrxydm", zrxydm);
		map.put("zrxymc", zrxymc );
		map.put("zrbjdm", zrbjdm);
		map.put("zrbjmc", zrbjmc);
		map.put("zylb", zylb);
		map.put("zyfx", zyfx);
		map.put("pyfx", pyfx);
		map.put("fxzy", fxzy);
		map.put("fxzyfx", fxzyfx);
		map.put("bxlx", bxlx);
		map.put("bxxs", bxxs);
		map.put("xxxs", xxxs);
		map.put("zsjj", zsjj);
		map.put("xxszd", xxszd);
		map.put("xjztm", xjztm);
		map.put("cym", cym);
		map.put("ksh", ksh);
		map.put("jg", jg);
		map.put("csd", csd);
		map.put("byrq", byrq);
		map.put("bjyjl", bjyjl);
		map.put("zsbh", zsbh);
		map.put("zsxlh", zsxlh);
		map.put("xw", xw);
		map.put("xwzsbh", xwzsbh);
		map.put("xwzsxlh", xwzsxlh);
		map.put("xzxm", xzxm);
		map.put("shbj", shbj);
		map.put("dybj", dybj);
		map.put("thbs", thbs);
		map.put("sfzh", sfzh);
		map.put("csrq", csrq);
		map.put("syd", syd);
		map.put("zzmm", zzmm);
		//end 转入学生信息
		//转出学生信息
		map.put("zcxydm", zcxydm);
		map.put("zczydm", zczydm);
		map.put("zcbjdm", zcbjdm);
		//end 转出学生信息
		map.put("zxlx", zxlx);
		map.put("qssj", qssj);
		map.put("jzsj", jzsj);
		map.put("ydlbdm", ydlbdm);
		map.put("jtdh", jtdh);
		map.put("jtdz", jtdz);
		map.put("ydqnj", ydqnj);
		map.put("ydqzydm", ydqzydm);
		map.put("ydqbjdm", ydqbjdm);
		map.put("ydhnj", ydhnj);
		map.put("ydhzydm", ydhzydm);
		map.put("ydhbjdm", ydhbjdm);
		map.put("xh", xh);
		map.put("bz", bz);
		map.put("sqrq", sqrq);
		map.put("daqk", daqk);
		map.put("pqdwmc", pqdwmc);
		map.put("ffsj", ffsj);
		map.put("pqzbh", pqzbh);
		map.put("qdrxm", qdrxm);
		map.put("mzdm", mzdm);
		map.put("zkzh",zkzh );
		map.put("rxsj", rxsj);
		map.put("lxdh", lxdh);
		map.put("zcxxmc", zcxxmc);
		map.put("zczymc", zczymc);
		map.put("zcnj", zcnj);
		map.put("zcxz", zcxz);
		map.put("zcxlcc", zcxlcc);
		map.put("zrxxmc", zrxxmc);
		map.put("zrzymc", zrzymc);
		map.put("zrnj", zrnj);
		map.put("zrxz", zrxz);
		map.put("zrxlcc", zrxlcc);
		map.put("sqly", sqly);
		map.put("zcxxyj", zcxxyj);
		map.put("zrxxyj", zrxxyj);
		map.put("zcsjytyj",zcsjytyj);
		map.put("zrsjytyj",zrsjytyj);
		map.put("xm", xm);
		map.put("xb", xb);
		map.put("ddqkdm", ddqkdm);
		//学生转专业
		map.put("lqzydm", lqzydm);
		map.put("zrzydm", zrzydm);
		map.put("zczyxyyj", zczyxyyj);
		map.put("zrzyxyyj", zrzyxyyj);
		map.put("zjcyj", zjcyj);
		map.put("cwcyj", cwcyj);
		map.put("xscyj", xscyj);
		map.put("gdxxspyj", gdxxspyj);
		map.put("zzqjytbayj", zzqjytbayj);
		//学籍变动
		map.put("sqsj", sqsj);
		
		for(int i=0; i<pk.length; i++){
			if(i==pk.length-1){
				pkStr += pk[i];
			}else{				
				pkStr += pk[i] + "||";
			}
			pkValue += map.get(pk[i]);
		}
		
		for(int i=0; i<input.length; i++){
			value[i] = map.get(input[i]);
		}
		
		sql = "select count(*) num from " + tabName + " where " + pkStr + "=?";
		num = Integer.parseInt(dao.getOneRs(sql, new String[]{pkValue}, "num"));
		if(num>0){
			//update
			flag = StandardOperation.update(tabName, input, value, pkStr, pkValue, request);
		}else{
			//insert
			flag = StandardOperation.insert(tabName, input, value, request);
		}		
		
		return flag;
	}
	
	/**
	 * 根据主键查询一条记录显示到页面
	 * */	
	public HashMap<String, String> getInfoByPk(String tabName,String pk,String pkValue,String[] input,boolean sexNum){
		HashMap<String, String> map = new HashMap<String, String>();
		String xb = "";
		
		String sql = "select * from " + tabName + " where " + pk + "=?";
		map = dao.getMap(sql, new String[]{pkValue}, input);
		xb = map.get("xb");
		if(sexNum){
			if(xb !=null && !"".equalsIgnoreCase(xb) && xb.equalsIgnoreCase("男")){
				xb = "1";
			}else if(xb !=null && !"".equalsIgnoreCase(xb) && xb.equalsIgnoreCase("女")){
				xb = "2";
			}else{
				xb = (xb !=null && !"".equalsIgnoreCase(xb)) ? "" :xb ;
			}
		}
		map.put("xb", xb);
		return map;
	}
	
	/**
	 * 根据主键删除一条记录
	 * */
	public boolean delInfoByPk(String tabName,String pk,String pkValue,HttpServletRequest request) throws Exception{
		boolean flag = false;
		flag = StandardOperation.delete(tabName, pk, pkValue, request);
		return flag;
	}
	
	/**
	 * 根据主键更新一条记录
	 * */
	public boolean updateInfoByPk(String tabName,String pk[], String[] pkValue,String[] inputValue,Object obj,HttpServletRequest request) throws Exception{
		boolean flag = false;
		HashMap<String, String> map = new HashMap<String, String>();
		XbemyStudentInfoForm xbForm = (XbemyStudentInfoForm)obj;
		String[] outputValue = new String[inputValue.length];
		String primaryKey = "";
		String pkVal = "";
		
		String xxsh = DealString.toGBK(xbForm.getXxsh());
		String zcxxyj = DealString.toGBK(xbForm.getZcxxyj());
		String zrxxyj = DealString.toGBK(xbForm.getZrxxyj());
		String zcsjytyj = DealString.toGBK(xbForm.getZcsjytyj());
		String zrsjytyj = DealString.toGBK(xbForm.getZrsjytyj());
		String xsszysh = DealString.toGBK(xbForm.getXsszysh());
		String hqcsh = DealString.toGBK(xbForm.getHqcsh());
		String xldsh = DealString.toGBK(xbForm.getXldsh());
		
		//转专业
		String zczyxysh = DealString.toGBK(xbForm.getZczyxysh());
		String zrzyxysh = DealString.toGBK(xbForm.getZrzyxysh());
		String xscsh = DealString.toGBK(xbForm.getXscsh());
		String zjcsh = DealString.toGBK(xbForm.getZjcsh());
		String cwcsh = DealString.toGBK(xbForm.getCwcsh());
		String jwcsh = DealString.toGBK(xbForm.getJwcsh());
		String zczyyxyj = DealString.toGBK(xbForm.getZczyyxyj());
		String zrzyyxyj = DealString.toGBK(xbForm.getZrzyyxyj());
		String xscyj = DealString.toGBK(xbForm.getXscyj());
		String zjcyj = DealString.toGBK(xbForm.getZjcyj());
		String cwcyj = DealString.toGBK(xbForm.getCwcyj());
		String jwcyj = DealString.toGBK(xbForm.getJwcyj());
		String hqcyj = DealString.toGBK(xbForm.getHqcyj());
		String xsszyyj = DealString.toGBK(xbForm.getXsszyyj());
		String xldyj = DealString.toGBK(xbForm.getXldyj());
		
		
		map.put("zcxxyj", zcxxyj);
		map.put("zrxxyj", zrxxyj);
		map.put("zcsjytyj", zcsjytyj);
		map.put("zrsjytyj", zrsjytyj);
		map.put("xxsh", xxsh);
		map.put("zczyxysh", zczyxysh);
		map.put("zrzyxysh", zrzyxysh);
		map.put("xscsh", xscsh);
		map.put("zjcsh", zjcsh);
		map.put("cwcsh", cwcsh);
		map.put("jwcsh", jwcsh);
		map.put("zczyyxyj", zczyyxyj);
		map.put("zrzyyxyj", zrzyyxyj);
		map.put("xscyj", xscyj);
		map.put("zjcyj", zjcyj);
		map.put("cwcyj", cwcyj);
		map.put("jwcyj", jwcyj);
		map.put("xsszysh", xsszysh);
		map.put("xsszyyj", xsszyyj);
		map.put("hqcsh", hqcsh);
		map.put("hqcyj", hqcyj);
		map.put("xldsh", xldsh);
		map.put("xldyj", xldyj);
		
		//主键组合成字符串
		for(int i=0; i<pk.length; i++){
			if(i == pk.length-1){
				primaryKey += pk[i];
			}else{
				primaryKey += pk[i] + "||";
			}
		}
		
		//主键值组合成字符串
		for(int i=0; i<pkValue.length; i++){
			pkVal += pkValue[i];
		}
		
		//获取要修改的列的值
		for(int i=0; i<inputValue.length; i++){
			outputValue[i] = map.get(inputValue[i]);
		}
		
		flag = StandardOperation.update(tabName, inputValue, outputValue, primaryKey, pkVal, request);
		
		return flag;
	}
	
	/**
	 * 获取民族列表
	 * */
	public List getMzList(){
		List mzList = dao.getMzList();
		return mzList;
	}
	
	/**
	 * 判断是否是某个组的用户
	 * */
	@SuppressWarnings("static-access")
	public boolean getUserGroup(HttpServletRequest request,String groupName){
		boolean flag = false;		
		String userName = request.getSession().getAttribute("userName").toString();
		
		BaseServicesUtil util = new BaseServicesUtil();
		flag = util.checkUserToGroup(userName, groupName);
		
		return flag;
	}
	
	/**
	 * 判断用户是转出学院用户还是转入学院用户
	 * */
	public String[] getUserStanding(HttpServletRequest request,String pk,String pkValue){
		String sql = "";
		String userDep = request.getSession().getAttribute("userDep").toString();
		String userType = dao.getUserType(userDep);
		String[] rsXy = new String[2];
		sql = "select lqzydm,zrzydm from xbemy_xszzysqb where " + pk + " =?";
		String[] value = dao.getOneRs(sql, new String[]{pkValue},new String[]{"lqzydm","zrzydm"});
		String zcxydm = dao.getOneRs("select xydm from view_njxyzybj where zydm=?", new String[]{value[0]}, "xydm");
		String zrxydm = dao.getOneRs("select xydm from view_njxyzybj  where zydm=?", new String[]{value[1]}, "xydm");
		
		if(value!=null && value.length>0){
			if(userType.equalsIgnoreCase("xy")){			
				if(userDep.equalsIgnoreCase(zcxydm) && userDep.equalsIgnoreCase(zrxydm)){
					//转出学院 转入学院为同一学院
					rsXy[0] = "zcxy";
					rsXy[1] = "zrxy";
				}
				if(userDep.equalsIgnoreCase(zrxydm) && !userDep.equalsIgnoreCase(zcxydm)){
					//转入学院用户
					rsXy[0] = "";
					rsXy[1] = "zrxy";
				}
				if(!userDep.equalsIgnoreCase(zrxydm) && userDep.equalsIgnoreCase(zcxydm)){
					//转出学院
					rsXy[0] = "zcxy";
					rsXy[1] = "";
				}
			}
		}
		
		return rsXy;
	}
	
	/**
	 * 判断是否是学生所在院的用户
	 * */
	public boolean isOwnCollege(HttpServletRequest request,String pk,String pkValue){
		String sql = "";
		boolean flag = false;
		String userDep = request.getSession().getAttribute("userDep").toString();
		String userType = dao.getUserType(userDep);
		
		sql = "select xydm from view_njxyzybj where zydm=(select distinct ydqzydm from xbemy_xjbdsqb where " + pk + " =?)";
		String value = dao.getOneRs(sql, new String[]{pkValue},"xydm");
		
		if(userType.equalsIgnoreCase("xy")){
			if(value!=null && !"".equalsIgnoreCase(value) && value.equalsIgnoreCase(userDep)){
				flag= true;
			}
		}
		return flag;
	}
	
	/**
	 * 查询学生信息
	 * */
	public static HashMap<String, String> getStuInfo(String xh){
		DAO dao = DAO.getInstance();
		HashMap<String, String> map = new HashMap<String, String>();
		String[] output = {"xh","xm","xb","mzdm","mzmc","csrq","syd","ydqzydm","ydqnj","ydqbjdm","xz","pycc","bjdm","nj","zydm","zymc","bjmc","xymc"};
		
		String sql = "select xh,xm,xb,mz mzdm,mzmc,csrq,syd,zymc,zydm ydqzydm,zydm,nj ydqnj,nj,bjdm ydqbjdm,bjdm,bjmc,xz,pycc,xymc from view_xsxxb where xh=?";
		map = dao.getMap(sql, new String[]{xh}, output);
		return map;
	}
	
	/**
	 * 查询学生转出信息
	 * */
	public static HashMap<String, String> getOutTransferStuInfo(String xh){
		DAO dao = DAO.getInstance();
		HashMap<String, String> map = new HashMap<String, String>();
		String sql = "";
		String[] output =  new String[]{"xh","xm","xb","mzdm","mzmc","rxrq","lxdh","zcxxmc","zcxymc","zczymc","zcnj","zcxz","zcxlcc","zxlx"};		
		
		sql = "select xh,xm,xb,mz mzdm,mzmc,rxrq,lxdh,xymc zcxymc,zymc zczymc,nj zcnj,xz zcxz,pycc zcxlcc,(select xxmc from xtszb)zcxxmc,'转出' zxlx from view_xsxxb where xh=?";
		map = dao.getMap(sql, new String[]{xh},output);		
		return map;
	}
			
	/**
	 * 根据代码查询名称
	 * */
	public String getNameByCode(String tableName,String[] codeInfo,String pkValue){
		String temp = "";
		String sql = "";
		sql = "select " + codeInfo[1] + " str from "+ tableName + " where " + codeInfo[0] + "=?" ;
		
		temp = dao.getOneRs(sql, new String[]{pkValue}, "str");
		
		return temp;
	}
	
	/**
	 * 获取到档情况列表
	 * */
	public List getDDqkList(){
		List ddqkList = null;
		String sql = "select distinct dddm,ddmc from xbemy_ddqkdmb";
		ddqkList = dao.getList(sql, new String[]{}, new String[]{"dddm", "ddmc"});		
		return ddqkList;		
	}

	/***
	 * 根据学院代码获取专业列表
	 * */	
	public List getZyListByXydm(String xydm){
		List zyList = null;
		String sql = "select distinct zydm,zymc from view_njxyzybj where xydm=?";
		zyList = dao.getList(sql, new String[]{xydm}, new String[]{"zydm","zymc"});
		return zyList;
	}
	
	/***
	 * 根据学院代码获取班级列表
	 * */
	public List getBjListByXydm(String xydm){
		List zyList = null;
		String sql = "select distinct bjdm,bjmc from view_njxyzybj where xydm=?";
		zyList = dao.getList(sql, new String[]{xydm}, new String[]{"bjdm","bjmc"});
		return zyList;
	}
	
	/**
	 * 获取学生的转学类型
	 * @param model
	 * @return String
	 * */
	public String getZxlx(TransferSchModel model){
		String zxlx = "";
		String xh = model.getXh();
		String sqrq = model.getSqrq();
		
		String sql = "select zxlx from xbemy_xszxsqb where xh=? and sqrq=?";
		zxlx = dao.getOneRs(sql, new String[]{xh,sqrq}, "zxlx");
		return zxlx;
	}
	/**
	 * 从它校转入本校的学生的学生信息
	 * @param model
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getZrInfo(TransferSchModel model){
		HashMap<String, String> map = new HashMap<String, String>();
		String xh = model.getXh();
		String sqrq = model.getSqrq();
		String[] outputValue={ "xh","xm","xb","mzmc","rxsj","sqrq","zkzh","lxdh","zcxxmc","zczymc","zcnj","zcxz","zcxlcc",
				               "zrxxmc","zrxymc","zrzymc","zrbjmc", "zrnj","zrxz","zrxlcc","xxsh","zcxxyj","zrxxyj",
				               "zcsjytyj","zrsjytyj","xjztm","cym","zyfx","pyfx","xxszd","dqszj","fxzyfx","fxzy","zylb",
				               "bxlx","bxxs","xxxs","zsjj","ksh","syd","rxfs","sfzh","jg","csd","xzxm","byrq","bjyjl",
				               "zsbh","zsxlh","xw","xwzsbh","xwzsxlh","thbs","dybj","shbj","bz","zxlx","zzmmmc"};
		String sql = "select xh,xm,xb,(select distinct b.mzmc from mzdmb b where b.mzdm= a.mzdm) mzmc,rxsj,sqrq,zkzh" +
					 ",lxdh,zcxxmc,zczymc,zcnj,zcxz,zcxlcc,zrxxmc,(select distinct xymc from view_njxyzybj b where a.zrxydm=b.xydm) zrxymc," +
					 "(select distinct zymc from view_njxyzybj b where b.zydm=a.zrzydm) zrzymc,(select distinct bjmc from view_njxyzybj b where b.zydm=a.zrbjdm) zrbjmc," + 
					 "zrnj,zrxz,zrxlcc,xxsh,zcxxyj,zrxxyj,zcsjytyj,zrsjytyj,xjztm,cym,zyfx,pyfx,xxszd,zrnj dqszj,fxzyfx,fxzy,zylb,bxlx," +
					 "bxxs,xxxs,zsjj,ksh,syd,rxfs,sfzh,jg,csd,xzxm,byrq,bjyjl,zsbh,zsxlh,xw,xwzsbh,xwzsxlh,thbs,dybj,shbj,bz," +
					 "(select zzmmmc from zzmmdmb b where b.zzmmdm=a.zzmm)zzmmmc,zxlx from xbemy_xszxsqb a where xh=? and sqrq=?";
		map = dao.getMap(sql, new String[]{xh,sqrq}, outputValue);
		return map;
	}
	
	/**
	 * 从本校转出的学生信息
	 * @param model
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getZcInfo(TransferSchModel model){
		HashMap<String, String> map = new HashMap<String, String>();
		String xh = model.getXh();
		String sqrq = model.getSqrq();
		String[] outputValue={"xh","xm","xb","mzmc","rxsj","sqrq","zkzh","lxdh","zcxxmc","zcxymc","zczymc","zcbjmc",
				              "zcnj","zcxz","zcxlcc","zrxxmc","zrzymc","zrnj","zrxz","zrxlcc","xxsh","zcxxyj",
				              "zrxxyj","zcsjytyj","zrsjytyj","zxlx"};
		String sql = "select a.xh,a.xm,a.xb,(select distinct b.mzmc from mzdmb b where b.mzdm= a.mzdm) mzmc,a.rxsj,a.sqrq,a.zkzh" +
					 ",a.lxdh,a.zcxxmc,(select distinct xymc from view_njxyzybj b where a.zcxydm=b.xydm)zcxymc,(select distinct zymc from view_njxyzybj b where a.zczydm=b.zydm)zczymc," +
					 "(select distinct bjmc from view_njxyzybj b where a.zcbjdm=b.bjdm)zcbjmc,a.zcnj,a.zcxz,a.zcxlcc,a.zrxxmc, a.zrzymc," +
					 "a.zrnj,a.zrxz,a.zrxlcc,a.xxsh,a.zcxxyj,a.zrxxyj,a.zcsjytyj,a.zrsjytyj,a.zxlx from xbemy_xszxsqb a where xh=? and sqrq=?";
		map = dao.getMap(sql, new String[]{xh,sqrq}, outputValue);
		return map;
	}

	/**
	 * 检测输入学号是否存在
	 * @param xh
	 * @return boolean
	 * */
	public boolean isStuExists(String xh){
		DAO dao=DAO.getInstance();
		boolean flag=false;
		int result=0;
		String sql="select count(xh) num from xsxxb where xh=? ";
		result=Integer.parseInt(dao.getOneRs(sql, new String[]{xh}, "num"));
		flag=(result>0)?true:false;
		return flag;
	}
}
