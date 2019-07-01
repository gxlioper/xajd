
package xgxt.pjpy.csmz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import xgxt.utils.ExcelMethods;
import xgxt.utils.String.StringUtils;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 长沙民政学院评奖评优Service</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 李涛</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-06-06</p>
 */
public class PjpyCsmzService {
	
	 PjpyCsmzDAO dao = null;//数据操作DAO
	
	/**
	 * 项目列表选择信息
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getSqXmList(int type) throws Exception {
		List<HashMap<String, String>> xmList = new ArrayList<HashMap<String,String>>();
		String[] en = null;
		String[] cn = null;
		if (type == 1) {
			en = new String[] {"shjxj" , "qtjxj"};
			cn = new String[] {"社会奖学金" ,"其它奖学金"};
		}//end if
		for (int i = 0; i < en.length; i++) {
			HashMap<String, String> tempMap = new HashMap<String, String>();
			tempMap.put("en", en[i]);
			tempMap.put("cn", cn[i]);
			xmList.add(tempMap);//奖学金首页选择申请信息
		}//end for
		return xmList;
	}
    
	/**
	 * 获取学生相关信息（班级，专业，学院，性别，年级,姓名，学号）
	 * @param xh
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getStuInfo(String xh) throws Exception {
		dao = new PjpyCsmzDAO();
		return dao.getStuInfo(xh);
	}
	
	/**
	 * 获取奖学金相关信息(奖学金代码，名称，金额，类别)
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getJxjInfo(HashMap<String, String> jxjMap) throws Exception {
		dao = new PjpyCsmzDAO();
		return dao.getJxjInfo(jxjMap);
	}
	
	/**
	 * 判断数据是否重复，重复返回TRUE，反之返回FALSE
	 * isdatacf ---- 数据是否重复
	 * @param xh
	 * @param jxjdm
	 * @param xn
	 * @return
	 * @throws Exception
	 */
	public boolean isDataCf(String xh, String jxjdm, String xn) throws Exception {
		dao = new PjpyCsmzDAO();
		return dao.isDataCf(xh, jxjdm, xn);
	}
	
	/**
	 * 保存社会奖学金，成功返回TRUE，反之返回FALSE
	 * issaveshjxj ---- 保存社会奖学金 
	 * @return
	 * @throws Exception
	 */
	public boolean isSaveShJxj(SaveShJxjModel shjxjModel, HttpServletRequest request) throws Exception {
		dao = new PjpyCsmzDAO();
		return dao.isSaveShJxj(shjxjModel, request);
	}
	
	/**
	 * 奖学金查询表头
	 * shjxjtit ---- 社会奖学金表头 
	 * @param iType 根据iType 来获取不同的表头
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getShJxjTit(int iType, String sUserType, String sIsFdy) throws Exception {
		dao = new PjpyCsmzDAO();
		List<HashMap<String, String>> topList = new ArrayList<HashMap<String,String>>();
		switch (iType){
		case 1 :{
			topList = dao.getShJxjTit();//社会奖学金表头 
			break;
		}
		case 2 :{
			topList = dao.getShJxjTit2(sUserType, sIsFdy);//社会奖学金审核查询表头 
			break;
		}
		}//end case
		return topList;
	}
	
	/**
	 * 社会奖学金查询结果
	 * shjxjres ----  社会奖学金结果
	 * @param shjxjModel(社会奖学金查询MODEL)
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getShJxjRes(QueryShJxjModel shjxjModel) throws Exception {
		dao = new PjpyCsmzDAO();
		List<String[]> resList = new ArrayList<String[]>();
		resList = dao.getShJxjRes(shjxjModel);
		return resList;
	}

	/**
	 * 通过主键获取社会奖学金信息
	 * shjxjbyPkval ---- 通过主键获取社会奖学金
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getShJxjByPkVal(String pkValue) throws Exception {
		dao = new PjpyCsmzDAO();
		return dao.getShJxjByPkVal(pkValue);
	}
	
	/**
	 * 公用方法：批量删除
	 * @param keys 主键
	 * @return
	 * @throws Exception
	 */
	public String delInfoByPk(String[] keys) throws Exception {
		dao = new PjpyCsmzDAO();
		return dao.delInfoByPk(keys);
	}

	/**
	 * 社会奖学金审核查询结果
	 * shjxjres ---- 社会奖学金审核结果
	 * @param shjxjModel
	 * @param sUserType
	 * @param sIsFdy
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getShJxjRes2(QueryShJxjModel shjxjModel, String sUserType, String sIsFdy) throws Exception {
		dao = new PjpyCsmzDAO();
		return dao.getShJxjRes2(shjxjModel, sUserType, sIsFdy);
	}
	
	/**
	 * 社会奖学金审核 (辅导员，学院，学校)
	 * shjxjsh ---- 社会奖学金审核
	 * @param shjxjmodel 社会奖学金审核MODEL
	 * @param keys 主键
	 * sUserType 用户类型
	 * sIsFdy 是否是辅导员
	 * @return
	 * @throws Exception
	 */
	public boolean shjxjSh (ShShJxjModel shjxjModel, String[] keys, HttpServletRequest request) throws Exception {
		dao = new PjpyCsmzDAO();
		String sUserType = shjxjModel.getUserType();
		String sIsFdy = shjxjModel.getIsFdy();
		boolean bRes = false;
		if (StringUtils.isEqual(sIsFdy, "true")) {
			dao.shjxjByFdySh(shjxjModel, keys, request);
		}//end if 辅导员用户
		if (StringUtils.isEqual(sUserType, "xy") && !(StringUtils.isEqual(sIsFdy, "true"))) {
			dao.shjxjByXySh(shjxjModel, keys, request);
		}//end if 学院用户
		if (StringUtils.isEqual(sUserType, "xx") || StringUtils.isEqual(sUserType, "admin")) {
			dao.shjxjByXxSh(shjxjModel, keys, request);
		}//end if 学校用户
		return bRes;
	}
	
	/**
	 * 审核列表信息
	 * 
	 * @param iType
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getChkList(int iType) throws Exception {
		dao = new PjpyCsmzDAO();
		return dao.getChkList(iType);
	}
	
	/**
	 * 通过主键获取社会奖学金信息
	 * shjxjbyPkval ---- 通过主键获取社会奖学金
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getShJxjByPkVal2(String pkValue, String sUserType, String sIsFdy) throws Exception {
		dao = new PjpyCsmzDAO();
		String sql = "";
		if (StringUtils.isEqual(sIsFdy, "true")) {
			sql = "select xh,xm,xb,nj,xymc,zymc,bjmc,jxjmc,jxjdm,jxjlb,jlje,drzw,dcj,zcj,tcj,cjmc,zhpfmc,fdysh shjg,fdyyj shyj from view_xsjxjb where xh||jxjdm||xn = ?";
		}//end if 辅导员用户
		if (StringUtils.isEqual(sUserType, "xy") && !(StringUtils.isEqual(sIsFdy, "true"))) {
			sql = "select xh,xm,xb,nj,xymc,zymc,bjmc,jxjmc,jxjdm,jxjlb,jlje,drzw,dcj,zcj,tcj,cjmc,zhpfmc,xysh shjg,xyshyj shyj from view_xsjxjb where xh||jxjdm||xn = ?";
		}//end if 学院用户
		if (StringUtils.isEqual(sUserType, "xx") || StringUtils.isEqual(sUserType, "admin")) {
			sql = "select xh,xm,xb,nj,xymc,zymc,bjmc,jxjmc,jxjdm,jxjlb,jlje,drzw,dcj,zcj,tcj,cjmc,zhpfmc,xxsh shjg,xxshyj shyj from view_xsjxjb where xh||jxjdm||xn = ?";
		}// end if 学校用户,管理员
	    return dao.getShJxjByPkVal2(sql, pkValue);
	}
	
	/**
	 * 社会奖学金单个审核
	 * @param shjxjModel
	 * @param pkValue
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean saveshjxj(ShShJxjModel shjxjModel, String pkValue, HttpServletRequest request) throws Exception {
		dao = new PjpyCsmzDAO();
		String sUserType = shjxjModel.getUserType();
		String sIsFdy = shjxjModel.getIsFdy();
		boolean bFlag = false;
		if (StringUtils.isEqual(sIsFdy, "true")) {
			bFlag = dao.shjxjByFdySh1(shjxjModel, pkValue, request);
		}//end if 辅导员用户
		if (StringUtils.isEqual(sUserType, "xy") && !(StringUtils.isEqual(sIsFdy, "true"))) {
			bFlag = dao.shjxjByXySh1(shjxjModel, pkValue, request);
		}//end if 学院用户
		if (StringUtils.isEqual(sUserType, "xx") || StringUtils.isEqual(sUserType, "admin")) {
			bFlag = dao.shjxjByXxSh1(shjxjModel, pkValue, request);
		}// end if 学校用户,管理员
		return bFlag;
	}
	
	/**
	 * 奖学金类别列表
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getJxjLb() throws Exception {
		dao = new PjpyCsmzDAO();
		return dao.getJxjLb();
	}
	
	/**
	 * 保存前检查数据是否重复
	 * @param pk
	 * @param pkValue
	 * @param tableName
	 * @return
	 * @throws Exception
	 */
	public boolean chkDataExists(String pk, String pkValue, String tableName) throws Exception {
		dao = new PjpyCsmzDAO();
		return dao.chkDataExists(pk, pkValue, tableName);
	}
	
	/**
	 * 代码保存
	 * @param tableName
	 * @param jxjdmModel
	 * @param rychdmModel
	 * @param jxjxdmModel
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean dmSave(String tableName, JxjdmModel jxjdmModel, RychdmModel rychdmModel, JxjxdmModel jxjxdmModel, HttpServletRequest request) throws Exception {
		dao = new PjpyCsmzDAO();
		boolean bFlag = false;
		//奖学金代码的增加
		if (!StringUtils.isNull(tableName) && StringUtils.isEqual(tableName, "jxjdmb")) {
			bFlag = dao.saveJxjdm(jxjdmModel, request);
		}
		//荣誉称号代码的增加
		if (!StringUtils.isNull(tableName) && StringUtils.isEqual(tableName, "rychdmb")) {
			bFlag = dao.saveRychdm(rychdmModel, request);
		}
		//军训代码的增加
		if (!StringUtils.isNull(tableName) && StringUtils.isEqual(tableName, "jxjxdmb")) {
			bFlag = dao.saveJxjxdm(jxjxdmModel, request);
		}
		return bFlag;
	}
	
	/**
	 * 代码删除
	 * @param tableName
	 * @param pkValue
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean dmDel(String tableName, String pkValue, HttpServletRequest request) throws Exception {
		dao = new PjpyCsmzDAO();
		boolean bFlag = false;
		if (!StringUtils.isNull(tableName) && StringUtils.isEqual(tableName, "jxjdmb")) {
			bFlag = dao.delJxjdm(pkValue, request);
		}
		if (!StringUtils.isNull(tableName) && StringUtils.isEqual(tableName, "rychdmb")) {
			bFlag = dao.delRychdm(pkValue, request);
		}
		if (!StringUtils.isNull(tableName) && StringUtils.isEqual(tableName, "jxjxdmb")) {
			bFlag = dao.delJxjxdm(pkValue, request);
		}
		return bFlag;
	}
	
	/**
	 * 代码全部删除
	 * @param realTable
	 * @param request
	 * @throws Exception
	 */
	public void dmAllDel(String realTable, HttpServletRequest request) throws Exception {
		dao = new PjpyCsmzDAO();
		dao.delDm(realTable, request);
	}
	
	/**
	 * 获取代码信息
	 * @param pkValue
	 * @param tableName
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getDmInfo(String pkValue, String tableName) throws Exception {
		dao = new PjpyCsmzDAO();
		HashMap<String, String> resMap = new HashMap<String, String>();
		if (!StringUtils.isNull(tableName) && StringUtils.isEqual(tableName, "jxjdmb")) {
			resMap = dao.getJxjdmInfo(pkValue);
		}
		if (!StringUtils.isNull(tableName) && StringUtils.isEqual(tableName, "rychdmb")) {
			resMap = dao.getRychdmInfo(pkValue);
		}
		if (!StringUtils.isNull(tableName) && StringUtils.isEqual(tableName, "jxjxdmb")) {
			resMap = dao.getJxjxdmInfo(pkValue);
		}
		return resMap;
	}
	
	/**
	 * 获取学工部代码
	 * @return
	 * @throws Exception
	 */
	public String getXgbdm() throws Exception {
		dao = new PjpyCsmzDAO();
		return dao.getXgbdm();
	}
	
	/**
	 * 代码保存
	 * @param tableName
	 * @param jxjdmModel
	 * @param rychdmModel
	 * @param jxjxdmModel
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean dmSave1(String tableName, JxjdmModel jxjdmModel, RychdmModel rychdmModel, JxjxdmModel jxjxdmModel, HttpServletRequest request) throws Exception {
		dao = new PjpyCsmzDAO();
		boolean bFlag = false;
		//奖学金代码的增加
		if (!StringUtils.isNull(tableName) && StringUtils.isEqual(tableName, "jxjdmb")) {
			bFlag = dao.saveJxjdm1(jxjdmModel, request);
		}
		//荣誉称号代码的增加
		if (!StringUtils.isNull(tableName) && StringUtils.isEqual(tableName, "rychdmb")) {
			bFlag = dao.saveRychdm1(rychdmModel, request);
		}
		//军训代码的增加
		if (!StringUtils.isNull(tableName) && StringUtils.isEqual(tableName, "jxjxdmb")) {
			bFlag = dao.saveJxjxdm1(jxjxdmModel, request);
		}
		return bFlag;
	}
	
	/**
	 * 检查用户是否有权修改此信息
	 * @param xydm
	 * @param dm
	 * @param pkValue
	 * @param tableName
	 * @return
	 * @throws Exception
	 */
	public boolean chkUserWritable(String xydm, String dm, String pkValue, String tableName) throws Exception {
		dao = new PjpyCsmzDAO();
		return dao.chkUserWritable(xydm, dm, pkValue, tableName);
	}
	
	/**
	 * 辅导员获取对应班级列表
	 * @param fdyName
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> fdyGetBjList(String fdyName) throws Exception {
		dao = new PjpyCsmzDAO();
		return dao.fdyGetBjList(fdyName);
	}
	
	/**
	 * 辅导员获取对应专业列表
	 * @param fdyName
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> fdyGetZyList(String fdyName) throws Exception {
		dao = new PjpyCsmzDAO();
		return dao.fdyGetZyList(fdyName);
	}
	
	/**
	 * 奖学金报表打印
	 * @param wwb
	 * @param xh
	 * @param jxjdm
	 * @throws Exception
	 */
	public void jxjPrint(WritableWorkbook wwb, String xh, String jxjdm) throws Exception {
		dao = new PjpyCsmzDAO();
		HashMap<String, String> resMap1 = dao.getJxjexpData(xh, jxjdm);
		List<String[]> resList = dao.getJxjexpData1(xh);
		WritableSheet ws = wwb.getSheet(0);//写入到第一个sheet
		
		WritableCellFormat tStyle = new WritableCellFormat();
 	    WritableCellFormat wcfStyle = new WritableCellFormat();
	    Alignment alignMent = Alignment.CENTRE;
	    wcfStyle.setAlignment(alignMent);//设置输出数据居中
	    tStyle.setAlignment(Alignment.LEFT);
	   // wcfStyle.setBorder(Border.ALL, BorderLineStyle.THIN);//设置边框及边框线条
	    tStyle.setBorder(Border.ALL, BorderLineStyle.THIN);
	    if (resMap1 != null) {
	    	ws.addCell(new Label(0, 0, String.format("长沙民政职业技术学院 %1$s 学年度奖学金评审登记表", resMap1.get("xn")), wcfStyle));//输出表头
	    	ws.addCell(new Label(1, 1, resMap1.get("xh"), wcfStyle));//输出
	    	ws.addCell(new Label(1, 2, resMap1.get("xm"), wcfStyle));
	    	ws.addCell(new Label(1, 3, resMap1.get("xb"), wcfStyle));
	    	ws.addCell(new Label(1, 4, resMap1.get("nj"), wcfStyle));
	    	ws.addCell(new Label(1, 5, resMap1.get("xymc"), wcfStyle));
	    	ws.addCell(new Label(1, 6, resMap1.get("zymc"), wcfStyle));
	    	ws.addCell(new Label(1, 7, resMap1.get("bjmc"), wcfStyle));
	    	ws.addCell(new Label(1, 8, resMap1.get("wysp"), wcfStyle));
	    	
	    	ws.addCell(new Label(4, 1, resMap1.get("nd"), wcfStyle));
	    	ws.addCell(new Label(4, 2, resMap1.get("xn"), wcfStyle));
	    	ws.addCell(new Label(4, 3, resMap1.get("jxjmc"), wcfStyle));
	    	ws.addCell(new Label(4, 4, resMap1.get("jxjlb"), wcfStyle));
	    	ws.addCell(new Label(4, 5, resMap1.get("jlje"), wcfStyle));
	    	ws.addCell(new Label(4, 6, resMap1.get("drzw"), wcfStyle));
	    	ws.addCell(new Label(4, 7, resMap1.get("dnshjxj"), wcfStyle));
	    	ws.addCell(new Label(4, 8, resMap1.get("sjhm"), wcfStyle));
	    	
	    	ws.addCell(new Label(1, 15, resMap1.get("xxjl"), wcfStyle));
	    	ws.addCell(new Label(1, 16, resMap1.get("kycg"), wcfStyle));
	    	ws.addCell(new Label(1, 17, resMap1.get("sqly"), wcfStyle));
	    }
	    
	    if (resList != null) {
	    	String[] tmpList = resList.get(0);
	    	for (int i=0;i<7;i++) {
	    		ws.addCell(new Label(i+1, 11, tmpList[i], wcfStyle));
	    	}
	    	for (int i=0;i<7;i++) {
	    		ws.addCell(new Label(i+1, 12, tmpList[i+7], wcfStyle));
	    	}
	    	for (int i=0;i<7;i++) {
	    		ws.addCell(new Label(i+1, 13, tmpList[i+14], wcfStyle));
	    	}
	    	for (int i=0;i<7;i++) {
	    		ws.addCell(new Label(i+1, 14, tmpList[i+21], wcfStyle));
	    	}
	    }
	    ExcelMethods.submitWritableWorkbookOperations(wwb);//输出到客户端
	}
	
	/**
	 * 自动获取奖学金，荣誉称号代码
	 * @return
	 * @throws Exception
	 */
	public String getAutoJxjId() throws Exception {
		dao = new PjpyCsmzDAO();
		return dao.getAutoJxjId();
	}
	
	/**
	 * 奖学金修改信息
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> viewJxjxx(String pkValue) throws Exception {
		dao = new PjpyCsmzDAO();
		return dao.viewJxjxx(pkValue);
	}
	
	public boolean stujxjDel(String pkValue, HttpServletRequest request) throws Exception {
		dao = new PjpyCsmzDAO();
		return dao.stujxjDel(pkValue, request);
	}
	
	public boolean sturychDel(String pkValue, HttpServletRequest request) throws Exception {
		dao = new PjpyCsmzDAO();
		return dao.sturychDel(pkValue, request);
	}
}
