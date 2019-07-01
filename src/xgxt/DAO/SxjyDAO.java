package xgxt.DAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import common.Globals;

import oracle.sql.CLOB;

import xgxt.action.Base;
import xgxt.utils.GetTime;

public class SxjyDAO  {
	DAO dao = DAO.getInstance();
	
	public List<HashMap<String, String>> arrayToList(String[] arr1,
			String[] arr2){
		return dao.arrayToList(arr1, arr2);
	}
	
	public boolean creatView(String sql, String[] com) throws SQLException {
		return dao.creatView(sql, com);
	}
	
	public List<HashMap<String, String>> getBmList() {
		return dao.getBmList();
	}
	
	public String[] getColumnNameCN(String[] cName, String tabName) {
		return dao.getColumnNameCN(cName, tabName);
	}
	
	public List<HashMap<String, String>> getList(String sql,
			String[] inputValue, String[] outputValue) {
		return dao.getList(sql, inputValue, outputValue);
	}
	
	public CLOB getOneClob(String sql, String[] inputValue, String colName) {
		return dao.getOneClob(sql, inputValue, colName);
	}
	
	public  String[] getOneRs(String sql, String[] inputValue,
			String[] outputValue) {
		return dao.getOneRs(sql, inputValue, outputValue);
	}
	
	public ArrayList<String[]> rsToVator(String sql,
			String[] inputValue, String[] outputValue) {
		return dao.rsToVator(sql, inputValue, outputValue);
	}
	
	public boolean runUpdate(String sql, ArrayList<String> input)
	throws Exception {
		return dao.runUpdate(sql, input);
	}
	
	public boolean runUpdate(String sql, String[] input) throws Exception {
		return dao.runUpdate(sql, input);
	}
	
	public boolean runUpdate(Vector<HashMap<String, Object>> input) throws Exception {
		return dao.runUpdate(input);
	}
	
	public int[] runBatch(String[] sqlArr) throws SQLException {
		return dao.runBatch(sqlArr);
	}
	
	public boolean saveClobs(String sql, String clobSql, String sContent,
			String clobField) throws Exception {
		return dao.saveClobs(sql, clobSql, sContent, clobField);
	}
	
	public boolean runUpdateTab(String sql) throws Exception {
		return dao.runUpdateTab(sql);
	}
	
	public String getStringToSplit(String sql, String[] inputValue,
			String[] outputValue) {
		return dao.getStringToSplit(sql, inputValue, outputValue);
	}
	
	public HashMap<String, String> getMap(String sql, String[] inputValue,
			String[] outputValue) {
		return dao.getMap(sql, inputValue, outputValue);
	}
	
	public List<HashMap<String, String>> getModelList() {
		return dao.getModelList();
	}
	
	public List<HashMap<String, String>> getNdList() throws Exception {//
		return dao.getNdList();
	}
	
	public List<HashMap<String, String>> getXnndList() throws Exception {//
		return dao.getXnndList();
	}
	
	public String getUserType(String userDep) throws Exception {//
		return dao.getUserType(userDep);
	}
	
	public String listToString(List list, String[] cols) {
		return dao.listToString(list, cols);
	}
	
	public boolean updataClobs(String sql, String clobSql, String sContent,
			String clobField) throws Exception {
		return dao.updataClobs(sql, clobSql, sContent, clobField);
	}
	
	
	public List<HashMap<String, String>> getXxPlanList() {
		// 获取所属学校计划
		String sql = "select xxjhdm xxplandm,xxjhmc xxplanmc from sxjy_xxgzjh order by xxjhdm";
		return dao.getList(sql, new String[] {}, new String[] { "xxplandm", "xxplanmc" });
	}

	public List<HashMap<String, String>> getXyPlanList(String userDep) {
		// 获取所属学院计划
		String sql = "select xyjhdm xyplandm,xyjhmc xyplanmc from sxjy_xygzjh  where (xydm = ? or ? = ' ' ) order by xyjhdm";
		return dao.getList(sql, new String[] {userDep,userDep+" "}, new String[] { "xyplandm", "xyplanmc" });
	}

	public List<HashMap<String, String>> getJyMotifList() {
		// 获取所属教育主题
		String sql = "select ztdm jymotifdm,ztmc jymotifmc from sxjy_yjyzj order by ztdm";
		return dao.getList(sql, new String[] {}, new String[] { "jyMotifdm", "jyMotifmc" });
	}

	public List<HashMap<String, String>> getJyPlotList(String userDep) {
		// 获取所属教育活动策划
		String sql = "select chdm jyplotdm,chmc jyplotmc from sxjy_hdchb where (xydm = ? or ? = ' ' ) order by chdm";
		return dao.getList(sql, new String[] {userDep,userDep+" "}, new String[] { "jyplotdm", "jyplotmc" });
	}
	
	public List<HashMap<String, String>> getCommonList(String tableName,String dm,String mc,String query) {
		// 获取列表通用方法
		// query这个String 如果有内容传过来时要加" " 
		StringBuffer sqlBuffer = new StringBuffer("select ");
		sqlBuffer.append(dm);
		sqlBuffer.append(",");
		sqlBuffer.append(mc);
		sqlBuffer.append(" from ");
		sqlBuffer.append(tableName);
		sqlBuffer.append(query);
		return dao.getList(sqlBuffer.toString(), new String[] {}, new String[] { dm, mc });
	}
	
	public String [] getFdypxjlList(String zgh,String time) throws SQLException {
		// 获取所属教育活动策划
		String sql = "select xn||'学年'||pxqj||pxmc pxxx from view_fdyPx where  zgh = ? and lrrq < ? ";
		return dao.getArray(sql, new String[] {zgh,time},"pxxx" );
	}

	public List<HashMap<String, String>> getFdyList(String xydm) {
		// 获取职工列表
		if(xydm==null){
			xydm = "";
		}
		String sql = "select zgh,xm from view_fdyxx where bmdm = ? or ? = ' '  ";
		return dao.getList(sql, new String[] {xydm,xydm+" "}, new String[] { "zgh", "xm" });
	}
	
	
	
	public List<HashMap<String, String>> getFdyList(String xydm,String fgnj) {
		// 获取职工列表
		
		String query = "";
		if(fgnj!=null&&!fgnj.equalsIgnoreCase("")&&!fgnj.equalsIgnoreCase("no")){
			query = " and fgnj = "+fgnj;
		}else if(fgnj!=null&&fgnj.equalsIgnoreCase("no")){
			query = " and (fgnj is null or fgnj = '')";
		}
		
		if(xydm==null){
			xydm = "";
		}
		String sql = "select zgh,xm from view_fdyxx where bmdm = ? or ? = ' '  ";
		return dao.getList(sql+query, new String[] {xydm,xydm+" "}, new String[] { "zgh", "xm" });
	}
	
	public List<HashMap<String, String>> getFdyList2(String xydm,String zgh) {
		// 获取职工列表
		if(xydm==null){
			xydm = "";
		}
		String sql = "select zgh,xm from view_fdyxx where bmdm = ? or ? = ' ' and exists (select bmdm from view_fdyxx where zgh = ? or ? = ' ')";
		return dao.getList(sql, new String[] {xydm,xydm+" ",zgh,zgh+" "}, new String[] { "zgh", "xm" });
	}

	public List<HashMap<String, String>> getXxstuffList(){
		// 获取所属学习材料
		String sql = "select cldm xxstuffdm,clmc xxstuffmc from sxjy_zzxxcl order by cldm";
		return dao.getList(sql, new String[] {}, new String[] { "xxstuffdm", "xxstuffmc" });
	}

	public List<HashMap<String, String>> getPenuryHelpObjectList(String userDep) {
		// 获取所属帮困对象
		String sql = "select dxdm,dxmc from sxjy_shbkxxgl where (xydm = ? or ? = ' ' ) order by dxdm";
		return dao.getList(sql, new String[] {userDep,userDep+" "}, new String[] { "dxdm", "dxmc" });
	}
	
	public List<HashMap<String, String>> getComboList() {
		// 获取社团列表
		String sql = "select stdm,stmc from view_stsqdj where shzt = '通过'";
		return dao.getList(sql, new String[] {}, new String[] { "stdm", "stmc" });
	}
	
	public List<HashMap<String, String>> getComboPloyList(String stdm) {
		// 获取社团活动列表
		String sql = "select hdmc,pk from view_gdnz_sthd where (stdm = ? or ? = ' ')";
		return dao.getList(sql, new String[] {stdm,stdm+" "}, new String[] {  "pk","hdmc" });
	}

	public List<HashMap<String, String>> getResearchTestPageList() {
		// 获取调研问卷
		String sql = "select dywjdm,dywjmc from sxjy_dywjb order by dywjdm";
		return dao.getList(sql, new String[] {}, new String[] { "dywjdm", "dywjmc" });
	}

	public List<HashMap<String, String>> getTutorshippersonList(String userDep) {
		// 获取辅导员信息
		String sql = "select zgh,xm from fdyxxb where (bmdm = ? or ? = ' ') order by zgh";
		return dao.getList(sql, new String[] {userDep,userDep+" "}, new String[] { "zgh", "xm" });
	}

	public Object getClassDutyList() {
		//获取班干部职务种类
		String sql = "select bjgbdm,bjgbmc from sxjy_bjgbzlb order by bjgbdm";
		return dao.getList(sql, new String[] {}, new String[] { "bjgbdm", "bjgbmc" });
	}
	
	public List<HashMap<String, String>> getXshGbList() {
		//获取学生会职务种类
		String sql = "select xshgbdm,xshgbmc from sxjy_xshgbzlb order by xshgbdm";
		return dao.getList(sql, new String[] {}, new String[] { "xshgbdm","xshgbmc" });
	}
	
	public List<HashMap<String, String>> getXyXshGbList() {
		//获取学生会职务种类
		String sql = "select xshgbdm,xshgbmc from sxjy_xygbzlb order by xshgbdm";
		return dao.getList(sql, new String[] {}, new String[] { "xshgbdm","xshgbmc" });
	}
	
	public List<HashMap<String, String>> getXljkXshGbList() {
		//获取学生会职务种类
		String sql = "select xshgbdm,xshgbmc from sxjy_xljkgbzlb order by xshgbdm";
		return dao.getList(sql, new String[] {}, new String[] { "xshgbdm","xshgbmc" });
	}
	
	public Object getAsnnSortList() {
		//获取社团申请类别
		String sql = "select lbdm,lbmc from stsqlbb order by lbdm";
		return dao.getList(sql, new String[] {}, new String[] { "lbdm", "lbmc" });
	}
	
	public Object getAppraiseItemList() {
		//获取学生干部评比项目种类列表
		String sql = "select xmdm,xmmc from xsgbpbxmb order by xmdm";
		return dao.getList(sql, new String[] {}, new String[] { "xmdm", "xmmc" });
	}
	
	public Object getStAppraiseItemList() {
		//获取社团评比项目种类列表
		String sql = "select xmdm,xmmc from stpyxmb order by xmdm";
		return dao.getList(sql, new String[] {}, new String[] { "xmdm", "xmmc" });
	}
	
	public List getJhzlList() {
		//获取班干部计划总结种类列表
		String sql = "select jhzldm,jhzlmc from gzjhzldmb order by jhzldm";
		return dao.getList(sql, new String[] {}, new String[] { "jhzldm", "jhzlmc" });
	}
	
	public List<HashMap<String, String>> getChkList(int type) {
		// 审核结果下拉框
		String[] chkList = null;
		if (type == 1) {
			chkList = new String[] { "已审核", "未审核" };
		} else if (type == 2) {
			chkList = new String[] { "是", "否" };
		} else if (type == 3) {
			chkList = new String[] { "未审核", "通过", "不通过" };
		} else if(type ==4) {
			chkList = new String[] { "成功", "失败" };
		} else if(type ==5) {
			chkList = new String[] { "未审核", "特困", "一般困难","不通过" };
		} else if(type ==6) {
			chkList = new String[] { "1", "2", "3","4","5","6","7","8","9","10","11","12" };
		}else if(type ==7) {
			chkList = new String[] { "讲座", "座谈", "征文","演讲","参观","其他" };
		}else if(type ==8) {
			chkList = new String[] { "面对面交流", "他人转告", "电话联系","信函","电子邮件","手机短信" };
		}else if(type ==9) {
			chkList = new String[] { "不用提醒","1个月之内", "2个月之内", "3个月之内","半年之内","一年之内","三年之内" };
		}else if(type ==10) {
			chkList = new String[] { ">", "=","<" };
		}
		return dao.arrayToList(chkList, chkList);
	}

	public boolean SxjyInsertSetup(String[] updataData) throws Exception {
		String sql = "";
		if(updataData.length==3){
			sql = "update sxjy_jyhdcsszb set txrq= ?, tjsjd = ? where gnmkdm=?";
		}else if(updataData.length==4){
			sql = "update sxjy_jyhdcsszb set tjsjd= ?, sbntxrq = ?, xbntxrq = ? where gnmkdm=?";
		}
		boolean updata = dao.runUpdate(sql, updataData);
		return updata;
	}

	public Vector<Object> getSxjyAwoke(String userDep) {
		String sql = "";
		Vector<Object> rs = new Vector<Object>();
		Vector<Object> rs2 = new Vector<Object>();
		String realTable = "";
		String type = "";
		String [] tmp = null;
		String [] time = null;
		String startTime = "";
		String endTime = "";
		String modelName = "";
		GetTime getTime = new GetTime();
		sql = "select a.txzl,a.gnmkdm,b.gnmkmc,a.dybm,a.txrq,a.tjsjd,a.sbntxrq,a.xbntxrq from sxjy_jyhdcsszb a,gnmkdmb b where a.gnmkdm = b.gnmkdm order by gnmkdm";
		rs.addAll(dao.rsToVator(sql, new String[]{}, new String[]{"txzl","gnmkdm","gnmkmc","dybm","txrq","tjsjd","sbntxrq","xbntxrq"}));
		for(int i = 0;i<rs.size();i++){
			tmp = (String[])rs.get(i);
			realTable = tmp[3];
			type = tmp[0];
			modelName = tmp[2];
			if(type.equalsIgnoreCase("term")){
				time = getTime.getYearAwokeTimeByTj(tmp[5],tmp[6],tmp[7]);
				startTime = time[0];
				endTime = time[1];
				StringBuffer sqlBuffer = new StringBuffer("select distinct lstimedata, '");
				sqlBuffer.append(modelName);
				sqlBuffer.append("' modelName from (select count(month) sum,");
				sqlBuffer.append("(case when (substr(a.lstimedata,6,7))<'07' then substr(a.lstimedata,1,4)||'上半年' when (substr(a.lstimedata,6,7))>'06' then substr(a.lstimedata,1,4)||'下半年' end )");
				sqlBuffer.append(" lstimedata from sxjy_yfjjlsb a left join (select substr(lrrq,1,4)||'-'||substr(lrrq,6,7) month from ");
				sqlBuffer.append(realTable);
				sqlBuffer.append(" where xydm = ? ) b on a.lstimedata = b.month where a.lstimedata > ?  and a.lstimedata < ? ");
				sqlBuffer.append("  group by a.lstimedata order by a.lstimedata) where sum = 0");
				sql =sqlBuffer.toString();
			}else if(type.equalsIgnoreCase("month")){
				time = getTime.getMonthAwokeTimeByTj(tmp[4],tmp[5]);
				startTime = time[0];
				endTime = time[1];
				StringBuffer sqlBuffer = new StringBuffer("select lstimedata, '");
				sqlBuffer.append(modelName);
				sqlBuffer.append("' modelName from (select count(month) sum,a.lstimedata  from sxjy_yfjjlsb a ");
				sqlBuffer.append("left join (select substr(lrrq,1,4)||'-'||substr(lrrq,6,7) month from ");
				sqlBuffer.append(realTable);
				sqlBuffer.append(" where xydm = ? ) b on a.lstimedata = b.month where a.lstimedata > ?  and a.lstimedata < ? ");
				sqlBuffer.append("  group by a.lstimedata order by a.lstimedata) where sum = 0");
				sql =sqlBuffer.toString();
			}
			rs2.addAll(dao.rsToVator(sql, new String []{userDep,startTime,endTime}, new String []{"lstimedata","modelName"}));
		}
		return rs2;
	}
	public String[] getFdyBjList(String pk){
		String sql = "select bjmc from fdybjb a,view_njxyzybj b where a.bjdm = b.bjdm and a.zgh=? union all select bjmc from bzrbbb a,view_njxyzybj b where a.bjdm = b.bjdm and a.zgh=?";
		try {
			return dao.getArray(sql, new String[]{pk,pk}, "bjmc");
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public String[] getFdyBjListforAhjg(String pk){
		String sql = "select bjmc||'人数'||sum from fdybjb a,(select bjdm,bjmc,count(xh) sum from view_xsjbxx group by bjdm,bjmc) b where a.bjdm = b.bjdm and a.zgh=?";
		try {
			return dao.getArray(sql, new String[]{pk}, "bjmc||'人数'||sum");
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	public String[] getZyBjList(String pk){
		String sql = "select distinct zymc from fdybjb a,view_njxyzybj b where a.bjdm = b.bjdm and a.zgh=?";
		try {
			return dao.getArray(sql, new String[]{pk}, "zymc");
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	public String getJspjNum(String pk){
		String sql = "select count(distinct xh) a cout from xspfb where jszgh=? and a.xh=";
		try {
			return dao.getOneRs(sql, new String[]{pk}, "cout");
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	public ArrayList<String[]> getJspjFs(String pk){
		String sql = "select round(sum(a.PJFS*b.PFBL*c.fz)/(count(distinct a.xh)),2) pjfs, b.qtmc,count(distinct a.xh) sum from xspfb a," +
		"(select b.PFBL,c.yhm,c.zdm,b.qtmc from  yhzb a,pjqtb b,yhb c where a.zdm=b.qtdm and c.zdm = a.zdm) b," +
		"pjzbb c where a.jszgh=? and a.xh=b.yhm and c.xh=a.pjzbxh and a.xh != a.jszgh group by b.zdm,b.qtmc";
		try {	
			return dao.rsToVator(sql, new String[]{pk}, new String[]{"qtmc","pjfs","sum"});
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	public ArrayList<String[]> getJspjForstuFs(String pk){
		String sql = "select round(sum(a.PJFS*b.PFBL*c.fz)/(count(distinct a.xh)),2) pjfs,b.qtmc,count(distinct a.xh) sum from xspfb a," +
		"(select PFBL, qtmc from pjqtb where qtdm='6727') b,pjzbb c,view_xsjbxx d " +
		"where a.jszgh=? and c.xh=a.pjzbxh and a.xh=d.xh group by qtmc";
		try {	
			return dao.rsToVator(sql, new String[]{pk}, new String[]{"qtmc","pjfs","sum"});
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	public Object getJsZzmmList() {
		String[] tmpdm = new String[] { "共产党员", "其他党派","无党派" };
		String[] tmpmc = new String[] { "共产党员", "其他党派","无党派" };
		return dao.arrayToList(tmpdm, tmpmc);
	}

	public Object getJsXlList() {
		String[] tmpdm;
		String[] tmpmc;
		if((Base.xxdm).equalsIgnoreCase(Globals.XXDM_ZJLG)){
			tmpdm = new String[] { "本科","硕士","博士","博士后" };
			tmpmc = new String[] { "本科","硕士","博士","博士后" };
		}else{
			tmpdm = new String[] { "大专","本科","硕士","博士","博士后" };
			tmpmc = new String[] { "大专","本科","硕士","博士","博士后" };
		}
		return dao.arrayToList(tmpdm, tmpmc);
	}

	public Object getSfList() {
		String sql = "select sfdm,sfmc from sfdmdzb ";
		return dao.getList(sql,new String[]{},new String[]{"sfdm","sfmc"});
	}
	
	
	
	public ArrayList<String[]> sxjyQuery(String tableName,String query,String [] inPutList,String [] colList,String sql) {
		ArrayList<String[]> rs = null;
		//取到colList的长度
		int size = colList.length-1;
		if(sql.equalsIgnoreCase("")){
			StringBuffer sqlBuffer = new StringBuffer("select ");
			for(int i = 0; i<(size);i++){
				sqlBuffer.append(colList[i]);
				sqlBuffer.append(", ");
			}
			sqlBuffer.append(colList[size]);
			sqlBuffer.append(" from ");
			sqlBuffer.append(tableName); 
			sqlBuffer.append(" a ");
			sqlBuffer.append(query);
			rs = dao.rsToVator(sqlBuffer.toString(), inPutList, colList);
		}else{
			rs = dao.rsToVator(sql, inPutList, colList);
		}
		return rs;
	}
	
	public HashMap<String, String[]> sxjyQueryForHashMap(String tableName,String query,String [] inPutList,String [] colList,String sql) {
		HashMap<String, String[]> rs = new HashMap<String, String[]>();
		//取到colList的长度
		int size = colList.length-1;
		if(sql.equalsIgnoreCase("")){
			StringBuffer sqlBuffer = new StringBuffer("select ");
			for(int i = 0; i<(size);i++){
				sqlBuffer.append(colList[i]);
				sqlBuffer.append(", ");
			}
			sqlBuffer.append(colList[size]);
			sqlBuffer.append(" from ");
			sqlBuffer.append(tableName); 
			sqlBuffer.append(query);
			rs = dao.getHashMapList(sqlBuffer.toString(), inPutList, colList);
		}else{
			rs = dao.getHashMapList(sql, inPutList, colList);
		}
		return rs;
	}
	
	public List<HashMap<String, String>> sxjyQueryforList(String tableName,String query,String [] inPutList,String [] colList,String sql) {
		//取到colList的长度
		List<HashMap<String, String>> rs;
		int size = colList.length-1;
		if(sql.equalsIgnoreCase("")){
			StringBuffer sqlBuffer = new StringBuffer("select ");
			for(int i = 0; i<(size);i++){
				sqlBuffer.append(colList[i]);
				sqlBuffer.append(", ");
			}
			sqlBuffer.append(colList[size]);
			sqlBuffer.append(" from ");
			sqlBuffer.append(tableName); 
			sqlBuffer.append(query);
			 rs = dao.getList(sqlBuffer.toString(), inPutList, colList);
		}else{
			 rs = dao.getList(sql, inPutList, colList);
		}
		return rs;
	}
	
	public HashMap<String, String> sxjyQueryOne(String tableName,String [] colList,String pk,String pkValue) {
		HashMap<String, String> map = new HashMap<String, String>();
		int size = colList.length-1;
		if(pkValue.equalsIgnoreCase("")){
			for(int i=0;i<size;i++){
				map.put(colList[i], "");
			}
		}else{
		StringBuffer sqlBuffer = new StringBuffer("select ");
		for(int i = 0; i<size;i++){
			sqlBuffer.append(colList[i]);
			sqlBuffer.append(", ");
			}
			sqlBuffer.append(colList[size]);
			sqlBuffer.append(" from ");
			sqlBuffer.append(tableName); 
			sqlBuffer.append(" where ");
			sqlBuffer.append(pk);
			sqlBuffer.append("=?");
			String [] rsTmp = dao.getOneRs(sqlBuffer.toString(), new String []{ pkValue }, colList);
			for(int i=0;i<=size;i++){
				map.put(colList[i], (rsTmp!=null)? rsTmp[i]:"");
			}
		}
		return map;
	}
	
	public HashMap<String, String> sxjyQueryOne3(String tableName,String [] colList,String pk,String pkValue,HashMap<String, String> map,String sql) {
		int size = colList.length-1;
		if(sql.equalsIgnoreCase("")){
		if(pkValue.equalsIgnoreCase("")){
			for(int i=0;i<size;i++){
				map.put(colList[i], "");
			}
		}else{
		StringBuffer sqlBuffer = new StringBuffer("select ");
		for(int i = 0; i<size;i++){
			sqlBuffer.append(colList[i]);
			sqlBuffer.append(", ");
			}
			sqlBuffer.append(colList[size]);
			sqlBuffer.append(" from ");
			sqlBuffer.append(tableName); 
			sqlBuffer.append(" where ");
			sqlBuffer.append(pk);
			sqlBuffer.append("=?");
			String [] rsTmp = dao.getOneRs(sqlBuffer.toString(), new String []{ pkValue }, colList);
			for(int i=0;i<=size;i++){
				map.put(colList[i], (rsTmp!=null)? rsTmp[i]:"");
			}
		}
		}else{
			String [] rsTmp = dao.getOneRs(sql, new String []{ pkValue }, colList);
			for(int i=0;i<=size;i++){
				map.put(colList[i], (rsTmp!=null)? rsTmp[i]:"");
			}
		}
		return map;
	}
	
	public String[] sxjyQueryOne2(String tableName,String [] colList,String pk,String pkValue) {
//		String[] rs = null;
		int size = colList.length-1;
		StringBuffer sqlBuffer = new StringBuffer("select ");
		for(int i = 0; i<(size);i++){
			sqlBuffer.append(colList[i]);
			sqlBuffer.append(", ");
		}
		sqlBuffer.append(colList[size]);
		sqlBuffer.append(" from ");
		sqlBuffer.append(tableName); 
		sqlBuffer.append(" where ");
		sqlBuffer.append(pk);
		sqlBuffer.append("=?");
		String [] rsTmp = dao.getOneRs(sqlBuffer.toString(), new String []{ pkValue }, colList);
		return rsTmp;
	}
	
	public synchronized String[] getViewComm(String viewName) throws SQLException {
		// 得到视图的字段注释语句
		DAO dao = DAO.getInstance();
		String[] arr = null;
		String sql = "select 'comment on column '||table_name||'.'||column_name||' is '||chr(39)||comments||chr(39) com "
				+ "from user_col_comments where table_name=upper('"
				+ viewName
				+ "')";
		arr = dao.getArray(sql, new String[] {}, "com");
		return arr;
	}
	
	//判断是否是教师的方法
	public boolean getSffdy(String zgh) throws SQLException {
		// 得到视图的字段注释语句
		HashMap<String, String> map =sxjyQueryOne("fdyxxb", new String[]{"zgh"}, "zgh",zgh);
		if(map.get("zgh").equalsIgnoreCase("")){//如果是辅导员，则设置辅导员标志
			return false;
		} else {
			return true;
		}
	}
	/**==============================下面为武汉理工大学所有 辅导员信息 BEGIN===========================================*/
	/**返回学历列表*/
	public List<HashMap<String,String>> getXlListFor_Whlg(){
		String sql = "select xldm,xlmc from fdyxlwhb";
		return dao.getList(sql, new String[]{}, new String[]{"xldm","xlmc"});
	}
	
	/**返回学位列表*/
	public List<HashMap<String,String>> getXwListFor_Whlg(){
		String sql = "select xwdm,xwmc from fdyxwwhb";
		return dao.getList(sql, new String[]{}, new String[]{"xwdm","xwmc"});
	}
	
	/**返回最后学位所属学科列表*/
	public List<HashMap<String,String>> getXkListFor_Whlg(){
		String sql = "select xkdm,xkmc from fdyzhxlssxkb";
		return dao.getList(sql, new String[]{}, new String[]{"xkdm","xkmc"});
	}
	
	/**返回从事工作列表*/
	public List<HashMap<String,String>> getGzListFor_Whlg(){
		String sql = "select gzdm,gzmc from fdycsgzb";
		return dao.getList(sql, new String[]{}, new String[]{"gzdm","gzmc"});
	}
	
	/**返回职称列表*/
	public List<HashMap<String,String>> getZcListFor_Whlg(){
		String sql = "select zcdm,zcmc from fdyzcb";
		return dao.getList(sql, new String[]{}, new String[]{"zcdm","zcmc"});
	}
	
	/**返沪是否是B类列表*/
	public List<HashMap<String,String>> getSfBlListFor_Whlg(){
		String[] columns = new String[]{"n","y"};
		String[] values = new String[]{"否","是"};
		return dao.arrayToList(columns, values);
	}
	
	/**返回辅导员所带班级的总人数*/
	public String getFdyBjRsList(String pk){
		String sql = "select count(b.xh) count from fdybjb a,view_xsjbxx b "
			+"where a.bjdm = b.bjdm and a.zgh=?";
		try {
			return dao.getOneRs(sql, new String[]{pk}, "count");
		}catch(Exception e){
			e.printStackTrace();
			return "0";
		}
	}
//	/**返回职称列表*/
//	public List<HashMap<String,String>> getZcListFor_Whlg(){
//		String sql = "select zcdm,zcmc from fdyzcb";
//		return dao.getList(sql, new String[]{}, new String[]{"zcdm","zcmc"});
//	}
	
	/**返回级别列表*/
	public List<HashMap<String,String>> getJbListFor_Whlg(){
		String sql = "select jbdm,jbmc from fdyjbb";
		return dao.getList(sql, new String[]{}, new String[]{"jbdm","jbmc"});
	}
	/**==============================为武汉理工大学所有 辅导员信息 END===========================================*/
	@SuppressWarnings("unchecked")
	public List<HashMap<String,String>> getCpzList(){
		String sql = "select qtdm,(case(a.qtdm) when '999999' then a.qtmc else b.zmc end) qtmc from pjqtb a left join yhzb b on a.qtdm = b.zdm where sfcp='是' order by qtdm";
		List mxdxList = dao.getList(sql, new String[] {}, new String[] {
				"qtdm", "qtmc" });
		return mxdxList;
	}

	public Collection<? extends String[]> getJspjForZp(String pk) {
			String sql = "select round(sum(a.PJFS*b.PFBL*c.fz)/(count(distinct a.xh)),2) pjfs, b.qtmc,count(distinct a.xh) sum from xspfb a," +
			"(select b.PFBL,b.qtmc from  pjqtb b where b.qtdm ='999999') b," +
			"pjzbb c where a.jszgh=? and a.xh = a.jszgh and c.xh=to_char(a.pjzbxh) group by b.qtmc";
			try {	
				return dao.rsToVator(sql, new String[]{pk}, new String[]{"qtmc","pjfs","sum"});
			}catch(Exception e){
				e.printStackTrace();
				return null;
			}
	}
	
	/**
	 * 无锡商职 获得辅导员组列表
	 * 
	 * @return list
	 * @author luojw
	 */
	public List<HashMap<String,String>> getFdyzList() {
		String sql = "select dm,mc from wxsz_szdw_fdyz order by dm";
		return dao.getList(sql, new String[] {}, new String[] { "dm", "mc" });
	}
	
	/**
	 * 浙江理工 获得辅导员分工类型表
	 * 
	 * @return list
	 * @author luning 
	 */
	public List<HashMap<String,String>> getFglxList() {
		String sql = "select fglxdm,fglxmc from szdw_fglxdmb order by fglxdm";
		return dao.getList(sql, new String[] {}, new String[] { "fglxdm", "fglxmc" });
	}
	
	/**
	 * 无锡商职 获得辅导员统计列表
	 * 
	 * @return list
	 * @author luojw
	 */
	public List<HashMap<String, String>> getFdyTjList(String query) {
		String sql = "select * from view_wxsz_fdyxxtj "+query;
		return dao.getList(sql, new String[] {}, new String[] { "xm", "xb",
				"bmmc", "sfmc", "mzmc", "csrq", "zc", "zwmc", "zzmm", "bjmc",
				"bkbyyx", "bksxzy", "ssbyyx", "ssbyzy", "bsbyyx", "bsbyzy",
				"grhjqk", "yddh", "fblw" });
	}

	public Object getClassDutyTypeList() {
			//获取班干部职务种类
			String sql = "select jbdm,jbmc from bjgbzljbb order by jbdm";
			return dao.getList(sql, new String[] {}, new String[] { "jbdm", "jbmc" });
	}
}
