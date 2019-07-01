
package xgxt.xsgygl.csmzzyjsxy;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.utils.String.StringUtils;
import xgxt.wjcf.csmz.xsDormDSCHModel;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 长沙民政职业技术学院公寓管理DAO</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: LP</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-06-11</p> 
 */
public class GyglCsmzDAO{
	
   /**
    * 保存辅导员下寝信息
    * @param model
    * @return
    * @throws Exception 
    */
   public boolean saveFdyXqInfo(GyglCsmzModel model) throws Exception{
	   DAO dao = DAO.getInstance();
	   boolean czFlag = false;
	   String zgh  = model.getZgh();
	   String lddm = model.getLddm();
	   String qsh  = model.getQsh();
	   String xqsj = model.getSj();
	   String zywt = DealString.toGBK(model.getZywt());
	   String bz   = DealString.toGBK(model.getBz());
	   String xm   = DealString.toGBK(model.getXm());
	   String xn   = model.getXn();
	   String xq   = model.getXq();
	   String pk = "zgh||lddm||qsh||xqsj";
	   //删除相同记录
	   czFlag = dao.runUpdate("delete gygl_fdyxqxxb where "+pk+"=? ", new String[]{zgh+lddm+qsh+xqsj});
//	 增加记录
	   if(czFlag){
	      czFlag = dao.runUpdate("insert into gygl_fdyxqxxb(zgh,fdyxm,xqsj,lddm,qsh,zywt,bz,xn,xq)values(?,?,?,?,?,?,?,?,?) ", 
	    		   new String[]{zgh,xm,xqsj,lddm,qsh,zywt,bz,xn,xq});
	   }
	   return czFlag;
   }
   /**返回学生本宿舍本学年学期辅导员访问寝室情况*/
   public List<HashMap<String,String>> stu_fdyxqxxCx(String xh){
	   DAO dao = DAO.getInstance();
	   List<HashMap<String,String>> listTem = null;
	   String sql = "select zgh||a.lddm||a.qsh||xqsj pkV ,rownum r ,a.fdyxm,a.xqsj,b.ldmc,a.qsh,(case when a.zywt is not null then substr(a.zywt,1,5)||'...' else '' end)zywt," 
	   		        +"a.bz,a.xn,a.xq,(case when a.xsfk is not null then substr(a.xsfk,1,5)||'...' else '' end)xsfk from gygl_fdyxqxxb a, "	   		       
                    +"(select lddm,qsh,ldmc from  view_xszsxx where xh =?)b where a.lddm=b.lddm and " 
                    +"a.qsh=b.qsh and xn=? and xq=? order by xqsj desc ";
	   listTem    = dao.getList(sql, new String[]{xh,Base.currXn,Base.currXq},new String[]{"pkV","r","fdyxm","xqsj","ldmc","qsh","zywt","bz","xn","xq","xsfk"});
	   return listTem;
   }
   /**返回学生填写反馈信息时显示页面信息*/
   public HashMap<String,String> stu_fdyXqXxQer(String pkValue){
	   DAO dao = DAO.getInstance();
	   HashMap<String,String> map = null;
	   String pk = "zgh||lddm||qsh||xqsj";
	   String sql = "select fdyxm,zgh,xqsj,lddm,qsh,zywt,bz,xn,xq,xsfk "
		            +"from gygl_fdyxqxxb where "+pk+"=?";
	   map = dao.getMap(sql,new String[]{pkValue},new String[]{"fdyxm","zgh","xqsj","lddm","qsh","zywt","bz","xn","xq","xsfk"});
	   return map;
   }
   /**保存学生填写反馈信息*/
   public boolean saveStu_fkxx(String pkValue,GyglCsmzModel model) throws Exception {
	   DAO dao = DAO.getInstance();
	   boolean czFlag = false;
	   String xsFk = DealString.toGBK(model.getXsfk());
	   String pk = "zgh||lddm||qsh||xqsj";
	   czFlag = dao.runUpdate("update gygl_fdyxqxxb set xsfk=? where "+pk+"=? ", new String[]{xsFk,pkValue});
       return czFlag;
   }
   /**
	 * @return 辅导员下寝信息查询的表头
	 */
	public ArrayList<HashMap<String, String>> getFXSTitle() {
		ArrayList<HashMap<String, String>> result = new ArrayList<HashMap<String, String>>();
		// 必须与getFXSResult方法中的输出表列一致
		String[] opCols = { "zgh||lddm||qsh||xqsj 主键","xn", "xq", "fdyxm","xqsj","ldmc","qsh","zywt", "xsfk"};
		String[] cnCols = { "主键","学年","学期","辅导员姓名","下寝时间","楼栋名称","寝室号","主要问题",
				          "学生反馈"};
		for (int i = 0; i < opCols.length; i++) {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("en", opCols[i]);
			map.put("cn", cnCols[i]);
			result.add(map);
			map = null;
		}
		return result;
	}
   public  ArrayList<String[]> getFXSResult(GyglCsmzFdyXqCxModel gcModel){
	   DAO dao = DAO.getInstance();
	   ArrayList<String[]> rs = new ArrayList<String[]>();
	   String sql = "";
	   String xy  = gcModel.getXy();
	   String xn  = gcModel.getXn();
	   String xq  = gcModel.getXq();
	   String xm  =gcModel.getXm();
	   String zgh = gcModel.getZgh();
	   String sj  = gcModel.getSj();
	   String pk = "zgh||lddm||qsh||xqsj";
	   //输出的列表字段
	   String[] colT = {"主键", "xn", "xq", "fdyxm","xqsj","ldmc","qsh","zywt", "xsfk"};
	   //查询条件
	   StringBuffer qur = new StringBuffer();
	   qur.append(" where 1=1 ");
	   qur.append(StringUtils.isNull(xy)?"":" and bmdm='"+xy+"' ");
	   qur.append(StringUtils.isNull(zgh)?"":" and zgh='"+zgh+"' ");
	   qur.append(StringUtils.isNull(xn)?"":" and xn='"+xn+"' ");
	   qur.append(StringUtils.isNull(xq)?"":" and xq='"+xq+"' ");
	   qur.append(StringUtils.isNull(xm)?"":" and fdyxm like '%"+xm+"%'");
	   qur.append(StringUtils.isNull(sj)?"":" and sj='"+sj+"' ");
	   sql = "select "+pk+" 主键, xn, xq, fdyxm,xqsj,ldmc,qsh,(case when zywt is not null then substr(zywt,1,5)||'...' else '' end)zywt," 
	   		 +" (case when xsfk is not null then substr(xsfk,1,5)||'...' else '' end)xsfk from view_gygl_fdyxqxx";
	   rs = dao.rsToVator(sql + qur,new String[]{}, colT);
	   return rs;  
   }
   /**返回辅导员下寝信息Map*/
   public HashMap <String,String>getFdyXqxx(String pkValue){
	   DAO dao = DAO.getInstance();
	   String pk = "zgh||lddm||qsh||xqsj";
	   String sql = "select xn,xq,fdyxm xm,zgh,lddm,qsh,xqsj sj,zywt,xsfk,bz from view_gygl_fdyxqxx  where "
		            +pk+" =?";
	   return dao.getMap(sql, new String[]{pkValue},new String[]{"xn","xq","xm","zgh","lddm","qsh","sj","zywt","xsfk","bz"});
   }
   /**删除辅导员下寝信息
 * @throws Exception */
   public boolean delFdyXqXx(String pkValue) throws Exception{
	   DAO dao = DAO.getInstance();
	   //删除相同记录
	   String pk = "zgh||lddm||qsh||xqsj";
	   boolean czFlag = dao.runUpdate("delete gygl_fdyxqxxb where "+pk+"=? ", new String[]{pkValue});
	   return czFlag;
   }
   
   public boolean dormDistSave(String newMappingItems,String oldMappingItems) throws Exception{
	   DAO dao            = DAO.getInstance();
	   boolean doFlag    = false;
	   String[] delSql    = null;
	   String[] inserSql  = null;
		   if(((oldMappingItems != null) && !oldMappingItems.equals(""))){
			   //删除语句拼接
			   String delItems1[]     = oldMappingItems.split(",");
			   int delNum             =  delItems1.length;
			   String delItems2[][]   = new String[delNum][];
			   delSql                 = new String[delNum];
			   for (int i = 0; i < delNum; i++) {
				   delItems2[i] = delItems1[i].split("/");
				   delSql[i]    = "delete from ssfpb where  xydm='"+delItems2[i][0]+"'"+" and ssbh= "+"'"+delItems2[i][1]+"' and nj='"+delItems2[i][3]+"' ";
			   }
		   }else{
			   delSql     = new String[1];
			   delSql[0]  = " delete from ssfpb where 1=2 ";
		   }
		   if (((newMappingItems != null) && !newMappingItems.equals(""))) {
			   //插入语句拼接
			   newMappingItems        = similarStrUnion(newMappingItems);//并宿舍划分中重复值
			   String inserItems1[]   = newMappingItems.split(",");
			   int  inNum            = inserItems1.length;
			   //int[] array           = new int[inNum];
			   String inserItems2[][] = new String[inNum][];		   
			   inserSql               = new String[inNum];
			   for(int i=0;i<inNum;i++){
				   inserItems2[i] = inserItems1[i].split("/");
				   inserSql[i]    = "insert into ssfpb(xydm,ssbh,cws,bjdm,cwh,nj) values('"+inserItems2[i][0]+"','"+inserItems2[i][1]+"','"+inserItems2[i][2]+"','0','0','"+inserItems2[i][3]+"') ";
			   }
		   }else{
			   inserSql = new String[1];
			   inserSql[0] =  "delete from ssfpb where 1=2 "; 
		   }
		   String[] exesql = dao.unionArray(delSql,inserSql);
		   int[] array     = dao.runBatch(exesql);
		   doFlag          = dao.checkBatch(array);
	   
	   return doFlag;
   }
   public boolean bedDistSave(String newMappingItems,String oldMappingItems) throws Exception{
	   DAO dao            = DAO.getInstance();
	   boolean doFlag    = false;
	   String[] delSql    = null;
	   String[] inserSql  = null;
	   if(((oldMappingItems != null) && !oldMappingItems.equals(""))){
		   //删除语句拼接
		   String delItems1[]     = oldMappingItems.split(",");
		   int delNum             =  delItems1.length;
		   String delItems2[][]   = new String[delNum][];
		   delSql                 = new String[delNum];
		   for (int i = 0; i < delNum; i++) {
			   delItems2[i] = delItems1[i].split("/");
			   delSql[i]    = " delete from xszsxxb where xh = '"+delItems2[i][0]+"' "; 
		   }
	   }else{
		   delSql     = new String[1];
		   delSql[0]  = " delete from xszsxxb where 1=2 ";
	   }
	   if ((newMappingItems != null) && !newMappingItems.equals("")) {	
		   String inserItems1[] = newMappingItems.split(",");
		   int num = inserItems1.length;
		   String inserItems2[][] = new String[num][];
		   inserSql                 = new String[num];
		   for(int i=0;i<num;i++){
			   inserItems2[i] = inserItems1[i].split("/");
			   inserSql[i]      = " insert into xszsxxb(xh,ssbh,cwh,rzrq) values('"+inserItems2[i][0]+"','"+inserItems2[i][1]+"','"+inserItems2[i][2]+"','"+inserItems2[i][3]+"') ";
		   }
	   }else{
		   inserSql = new String[1];
		   inserSql[0] =  "delete from xszsxxb where 1=2"; 
	   }
	   String[] exesql = dao.unionArray(delSql, inserSql);
	   int[] array = dao.runBatch(exesql);
	   doFlag = dao.checkBatch(array);
	   return doFlag;
   }
   /**
    * 合并宿舍划分中重复的年级、院系、寝室编号、分配床位数
    * @param strValue（合并前有字符串）
    * @return
    */
   public static String similarStrUnion(String strValue){
	   String temValue = "";
	   StringBuffer retStr   = new StringBuffer();
	   String[] strArr       = strValue.split(",");//strArr值排列方式：学院代码/宿舍编号/分配床位数/年级  
	   StringBuffer comparer = new StringBuffer();
	   for(int i=0;i<strArr.length;i++){
		   String[] arrTem     = strArr[i].split("/");
		   String strCompValue = arrTem[0]+"/"+arrTem[1];
    	   for(int j=0;j<strArr.length;j++){
    		   String[] arrTem2     = strArr[j].split("/");
    		   String strCompValue2 = arrTem2[0]+"/"+arrTem2[1];
    		  if(comparer.toString().indexOf(strCompValue)!=-1){
    			  continue;
    		  }else{
    			  if((strCompValue).equalsIgnoreCase(strCompValue2)){
    				  int m=0;
    				  for(int n=0;n<strArr.length;n++){
    					  String[] ArrTem3     = strArr[n].split("/");
    					  String strCompValue4 = ArrTem3[0]+"/"+ArrTem3[1];
    					  if(strCompValue.equalsIgnoreCase(strCompValue4)){
    						  m+=Integer.parseInt(ArrTem3[2]);
    					  }  	
    				  }
    				  comparer.append(strCompValue);
    				  comparer.append("!-!");
    				  retStr.append(strCompValue+"/"+m+"/"+arrTem[3]);
    				  retStr.append(",");
    				  continue;
    			  }else{
    				  int m=0;
    				  for(int n=0;n<strArr.length;n++){
    					  String[] ArrTem3     = strArr[n].split("/");
    					  String strCompValue4 = ArrTem3[0]+"/"+ArrTem3[1];
    					  if(strCompValue.equalsIgnoreCase(strCompValue4)){
    						  m+=Integer.parseInt(ArrTem3[2]);
    					  }  	
    				  }
    				  comparer.append(strCompValue);
    				  comparer.append("!-!");
    				  retStr.append(strCompValue+"/"+m+"/"+arrTem[3]);
    				  retStr.append(",");
    			  }
    		  }
    	   }
       }
	   temValue =retStr.toString().substring(0,retStr.toString().lastIndexOf(","));//去掉最后一个字符“,”
	   return temValue;
   }
   
   public List<HashMap<String,String>> getXsDSearchTit(){
	   ArrayList<HashMap<String, String>> result = new ArrayList<HashMap<String, String>>();
	   // 必须与XsDSearch方法中的输出表列一致
	   String[] opCols = { "xh 主键","xh","nj","xm", "xb","xymc", "zymc","ldmc","qsh","cwh","rzrq"};
	   String[] cnCols = { "主键", "考生号","年级","姓名","性别","院系","专业","楼栋","寝室号","床位号","入住时间"};
	   for (int i = 0; i < opCols.length; i++) {
		   HashMap<String, String> map = new HashMap<String, String>();
		   map.put("en", opCols[i]);
		   map.put("cn", cnCols[i]);
		   result.add(map);
		   map = null;
	   }
	   return result;	  
   }
   public List<String[]> getXsDSearchRes( xsDormDSCHModel myModel) throws Exception {
	    DAO dao                = DAO.getInstance();
	    List<String[]> resList = new ArrayList<String[]>();
		String[] opCol         = new String[]{"xh","nj","xm","xb", "xymc", "zymc", "ldmc", "qsh", "cwh","rzrq"};		
		String sql             = "select xh,nj,xm,xb,xymc,zymc,ldmc,qsh,cwh,rzrq from view_newstuzsxx ";		
        String nj              = myModel.getNj();
        String xydm            = myModel.getXydm();
        String zydm            = myModel.getZydm();
        String xh              = myModel.getXh().trim();
        String xm              = myModel.getXm();
		
		StringBuffer whereSql  = new StringBuffer();
		
		whereSql.append(" where 1=1 ");
		whereSql.append(Base.isNull(nj)?"":" and nj='"+nj+"' ");
		whereSql.append(Base.isNull(zydm)?"":" and zydm='"+zydm+"' ");
		whereSql.append(Base.isNull(xydm)?"":" and xydm='"+xydm+"' ");
		whereSql.append(Base.isNull(xh)?"":" and xh='"+xh+"' ");
		whereSql.append(Base.isNull(xm)?"":" and xm like '%"+xm+"%' ");
		
		resList = dao.rsToVator(sql + whereSql.toString(),new String[]{},opCol);
		return resList;
	}
   public HashMap<String,String> getxsXX(String ksh){
	   DAO dao                    = DAO.getInstance();
	   HashMap<String,String> map = null;
	   String sql                 = "select ksh xh,xm,xb,nj,xydm,xymc,zydm,zymc from view_newstuinfo where ksh=?";
	   map                        = dao.getMap(sql,new String[]{ksh},new String[]{"xh","xm","xb","nj","xydm","xymc","zydm","zymc"});
	   return  map;
   }
   
   public boolean dao_XsZSADSave(String ksh,String ssbh,String cwh,String rzrq) throws Exception{
	   DAO dao       = DAO.getInstance();
	   boolean done = false;
	   String sql    = " insert into xszsxxb(xh,ssbh,cwh,rzrq)values(?,?,?,?)"; 
	   if(!Base.isNull(ksh)&&!Base.isNull(ssbh)&&!Base.isNull(cwh)){
		   boolean  del = dao.runUpdate("delete from xszsxxb where xh=?",new String[]{ksh});
		   if(del){
			   done      = dao.runUpdate(sql,new String[]{ksh,ssbh,cwh,rzrq});
		   }	   
	   }
	   return done;
   }
   
   public boolean dao_xsZsxxPlDel(HttpServletRequest request) throws Exception{
	   DAO dao = DAO.getInstance();
	   //String tabName = ;
	   //String realTable = "xszsxxb";		
	   String delPk = request.getParameter("delPk");
	   //String pk = request.getParameter("pk");
	   String[] pkValueA = delPk.split("!!");
	   String[] delSql = new String[pkValueA.length];
	   for (int i = 0; i < pkValueA.length; i++) {
		   delSql[i] = "delete from xszsxxb where xh='"+pkValueA[i]+"'";
		   //StandardOperation.delete(realTable, pk, , request);							
	   }	  
	   int[] array = dao.runBatch(delSql);
	   boolean doFlag = dao.checkBatch(array);
	   return doFlag;
   }
   /**
    * 房间顺序获取该生所在学院划分内宿舍信息
    * @param ksh
    * @return
    */
   public String[] dao_getXsSsInfo(String ksh){
	   DAO dao = DAO.getInstance();
	   StringBuffer sql = new StringBuffer();	   
	   sql.append("select lddm,ssbh,qsh from( select a.ssbh,a.cws,b.lddm,");
	   sql.append("b.qsh from ssfpb a,view_ssxx b where nj=(select nj from view_newstuinfo where ksh=?)");
	   sql.append("and xydm=(select xydm from view_newstuinfo where ksh=? ) ");
	   sql.append("and (xbxd = (select xb from view_newstuinfo where ksh=? ) or xbxd='混合')");
	   sql.append("order by ssbh )a where  a.cws > (select count(xh) from xszsxxb b where a.ssbh=b.ssbh )and rownum=1  ");
	   return dao.getOneRs(sql.toString(),new String[]{ksh,ksh,ksh},new String[]{"lddm","ssbh","qsh"});
   }
   public String dao_xhKshSynchro(String pkValue) throws SQLException{
	   String execStr = "0";
	   int succeedCout = 0;
	   DAO dao = DAO.getInstance();
	   String[] pkValueA = pkValue.split("!!");
	   String[] delSql = new String[pkValueA.length];
	   String[] updSql = new String[pkValueA.length];
	   StringBuffer sqlStr = null;
//	   String cfksh = "";
	   String inValue = "'"+pkValue.replaceAll("!!","','");
	   inValue = inValue.substring(0,inValue.length()-2);
	   String sql = "select a.ksh,a.xh xsxxb_xh,b.xh bks_xsjbxx_xh from xsxxb a,bks_xsjbxx b where a.ksh=b.ksh and a.xh<>b.xh  and a.ksh in("+inValue+")";
	   List<HashMap<String,String>> list = dao.getList(sql,new String[]{},new String[]{"ksh","xsxxb_xh","bks_xsjbxx_xh"});
	   if(list.size()>1){
		   System.out.println("学生信息库考生号重复数据查询语句："+sql);
		   execStr = "所选学生所在学生信息库中考生号有重复,不能进行学号同步操作，请与系统管理员联系！";		   
	   }else{
		   for (int i = 0; i < pkValueA.length; i++) {	
			   //删除xszsxxb信息表中存在 扫描bks_xsjbxx表、xsxxb表是否存在考生号pkValueA[i]的学号的记录
			   sqlStr = new StringBuffer(); 		  
			   sqlStr.append("delete from xszsxxb where xh=(select xh from bks_xsjbxx b where b.ksh='"+pkValueA[i]+"' union select xh from xsxxb where ksh='"+pkValueA[i]+"') ");
			   delSql[i] = sqlStr.toString();

			   sqlStr = new StringBuffer(); 
			   //扫描bks_xsjbxx表、xsxxb表是否存在考生号pkValueA[i]的学号，存在即进行修改
			   sqlStr.append("update xszsxxb  set xh = (select nvl(xh,(select xh from bks_xsjbxx b where b.ksh='"+pkValueA[i]+"')) ");
			   sqlStr.append("xh from ( select xh from xsxxb where ksh='"+pkValueA[i]+"' ))where xh='"+pkValueA[i]+"' and ");
			   sqlStr.append("(select nvl(xh,(select xh from bks_xsjbxx b where b.ksh='"+pkValueA[i]+"')) xh from ( ");
			   sqlStr.append("select xh from xsxxb where ksh='"+pkValueA[i]+"' ))  is not null  ");
//			   sqlStr.append(" update xszsxxb  set xh = (select xh from bks_xsjbxx b where b.ksh='"+pkValueA[i]+"' union select xh from xsxxb where ksh='"+pkValueA[i]+"' ) where xh='"+pkValueA[i]+"'");
			   updSql[i] = sqlStr.toString();		   
		   }	  
		   String[] exesql = dao.unionArray(delSql, updSql);
		   int[] array = dao.runBatch(exesql);
		   boolean doFlag = false;
//		   String aa= "";
		   try{
			   doFlag =  dao.checkBatch(array);
		   }catch(Exception e){
			   System.out.print(e.getMessage());
		   }
		   if(doFlag){
			   succeedCout = checkBatch_ToST(array,pkValueA.length);  
		   }
		   execStr = "共同步"+pkValueA.length+"条考生号，其中"+succeedCout+"条同步成功！";
	   }
	   return execStr;
   }
	//返回字符串执行成功记录数
	public int checkBatch_ToST(int[] result,int pkVLength){
		int str = 0;
		for(int j=0;j<result.length;j++){
			if(j+1>pkVLength){//排除前pkVLength执行结果(即删除语句)
			  if(result[j]>=1){
				 str++;
			  }		
			}
		}
		return str;
	}
	/**宿舍划分情况统计*/
	public List dao_hFDeTailView(){
		DAO dao         = DAO.getInstance();
		List<ArrayList<String[]>> list = new ArrayList<ArrayList<String[]>>();
		ArrayList<String[]> rs = new ArrayList<String[]>();
		String        sql     = "select a.lddm,a.ldmc,(select xqmc from dm_zju_xq b where a.xqdm=b.dm)xqmc,a.xbxd from sslddmb a order by  lddm "; 
		List<HashMap<String,String>>  ldList = dao.getList(sql, new String[]{},new String[]{"ldmc","xqmc","xbxd","lddm"});
		if(ldList!=null){
			for(int i=0;i<ldList.size();i++){//遍历楼栋统计每个楼栋宿舍划分情况
				sql = "select nj,(select bmmc from zxbz_xxbmdm b where a.xydm=b.bmdm)xymc, sum(a.cws) sumrel from ssfpb a,ssxxb b where a.ssbh=b.ssbh and b.lddm=?  group by a.nj,a.xydm ";
				String[] tjxyResult = dao.getOneRs(sql, new String[]{ldList.get(i).get("lddm").toString()},new String[]{"nj","xymc","sumrel"});
				if(tjxyResult==null){
					tjxyResult = new String[3];
					tjxyResult[0]="无";
					tjxyResult[1]="无";
					tjxyResult[2]="无";
				}
				sql = "select nvl(sum(syscout),'0')  sycout from( select a.ssbh,a.lddm,nvl(rszs-nvl(rsfp,0),'0')syscout from ( select a.ssbh,a.lddm, count(b.ssbh) rszs from ssxxb a, cwxxb b where a.ssbh=b.ssbh  group by a.ssbh,a.lddm "
					+")a left join (select ssbh,sum(cws)rsfp from ssfpb group by ssbh ) b on a.ssbh=b.ssbh  where lddm=? )";
				String sySumCout = dao.getOneRs(sql,new String[]{ldList.get(i).get("lddm").toString()}, "sycout");
				String[] tjValue = {ldList.get(i).get("lddm").toString(), ldList.get(i).get("ldmc").toString(),tjxyResult[0],tjxyResult[1],tjxyResult[2],sySumCout}; 
				rs.add(tjValue);
			}
			list.add(rs);
		}		
		return list;
	}
	
	/**
	 * 获得楼栋入住信息查询表头
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getTableTitle() throws Exception {
		List<HashMap<String, String>> topList = new ArrayList<HashMap<String, String>>();
		String[] enList = new String[]{"pk", "ldmc", "nj", "xymc", "sumrel", "sycout"};
		String[] cnList = new String[]{"pk", "楼栋名称", "年级", "入住生"+Base.YXPZXY_KEY, "应住人数", "实住人数"};
		for (int i=0; i<enList.length; i++) {
			HashMap<String, String> tmpMap = new HashMap<String, String>();
			tmpMap.put("en", enList[i]);
			tmpMap.put("cn", cnList[i]);
			topList.add(tmpMap);
		}
		return topList;
	}
	
	/**
	 * 单个宿舍入住详细信息
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getSxRzqk(String pkValue) throws Exception {
		DAO dao         = DAO.getInstance();
		List<String[]> resList = new ArrayList<String[]>();
		String[] opList = new String[]{"qsh", "cs", "cws", "rzrs"};
		String sql = "select a.ssbh,a.qsh,a.cs,a.cws,(select count(*) from view_xszsxx b where a.ssbh=b.ssbh) rzrs from ssxxb a where a.lddm = ? order by a.qsh asc";
		resList = dao.rsToVator(sql, new String[]{pkValue}, opList);
		return resList;
	}
	
	public List<HashMap<String, String>> getSxRxqkTitle() throws Exception {
		List<HashMap<String, String>> topList = new ArrayList<HashMap<String, String>>();
		String[] enList = new String[]{"qsh", "cs", "cws", "rzrs"};
		String[] cnList = new String[]{"寝室号", "所在层", "床位数", "入住人数"};
		for (int i=0; i<enList.length; i++) {
			HashMap<String, String> tmpMap = new HashMap<String, String>();
			tmpMap.put("en", enList[i]);
			tmpMap.put("cn", cnList[i]);
			topList.add(tmpMap);
		}
		return topList;
	}
	
	/**床位划分情况统计*/
	@SuppressWarnings("unchecked")
	public List dao_FpDeTailView( String userDep,String nj,String xydm,String zydm,String bjdm){
		DAO dao         = DAO.getInstance();
		List list       = new ArrayList();
		//nj = "";
//		xydm = "106";
//		zydm = "414";
//		bjdm = "2004414660401";//
		String userType = dao.getUserType(userDep);
		
		if(userType.equalsIgnoreCase("xy")){
			xydm=userDep;
		}
		String maxNj = dao.getOneRs("select to_char(sysdate,'yyyy')maxnj  from dual ", new String[]{}, "maxnj");
		
		StringBuffer querrT = new StringBuffer();
		querrT.append(Base.isNull(nj)?" nj>='"+(Integer.parseInt(maxNj)-6)+"' ":" nj='"+nj+"' ");
		querrT.append(Base.isNull(xydm)?"":" and xydm='"+xydm+"' ");
		querrT.append(Base.isNull(zydm)?"":" and zydm='"+zydm+"' ");
		querrT.append(Base.isNull(bjdm)?"":" and bjdm='"+bjdm+"' ");
		
		StringBuffer sql    = new StringBuffer();		
		sql.append("select a.xydm,a.xymc,xyzcout,nvl(xyyzcout,0)xyyzcout,xyzcout-(nvl(xyyzcout,0))xywzcout,round((xyzcout-(nvl(xyyzcout,0))) / xyzcout * 100,2) zwzbl," );
		sql.append("(select count(distinct bjdm) from view_xsjbxx b where a.xydm=b.xydm and "+querrT+" group by xydm)bjcout  ");
		sql.append("from (select xydm,xymc,count(xh)xyzcout from view_xsjbxx where "+querrT+" group by xydm,xymc order by xydm )a left join  ");
		sql.append("(select xydm,xymc, count(xh)xyyzcout from view_xszsxx where "+querrT+" group by xydm,xymc ) b on a.xydm=b.xydm  ");				
		List<HashMap<String,String>>  xyList = dao.getList(sql.toString(), new String[]{},new String[]{"xydm","xymc","xywzcout","xyzcout","xyyzcout","bjcout","zwzbl"});
//        int j=0;
        if(xyList!=null){
        	StringBuffer querry   = new StringBuffer();
    		querry.append(Base.isNull(nj)?" a.nj>='"+(Integer.parseInt(maxNj)-6)+"' ":" a.nj='"+nj+"' ");        	
        	querry.append(Base.isNull(zydm)?"":" and a.zydm='"+zydm+"' ");
        	querry.append(Base.isNull(bjdm)?"":" and a.bjdm='"+bjdm+"' "); 	
        	for(int i=0;i<xyList.size();i++){        		
        		StringBuffer sqlV    = new StringBuffer();
        		HashMap map = new HashMap();
        		map.put("xymc",xyList.get(i).get("xymc"));
        		map.put("xyzcout",xyList.get(i).get("xyzcout"));
        		map.put("xyyzcout",xyList.get(i).get("xyyzcout"));
        		map.put("xywzcout",xyList.get(i).get("xywzcout"));
        		map.put("bjcout",xyList.get(i).get("bjcout"));
        		map.put("zwzbl",xyList.get(i).get("zwzbl"));
        		        		        		          		
        		sqlV.append("select zcout,nj,bjmc,zymc,bjdm,zydm,xydm, nvl(yzcout, 0) yzcout,xydm,bjdm,nvl(zcout, 0) - nvl(yzcout, 0) wzcout,");
        		sqlV.append("round((nvl(zcout, 0) - nvl(yzcout, 0)) / nvl(zcout, 0) * 100, 2) wzbl from (  select zcout,a.nj,a.zymc,a.bjmc,");
        		sqlV.append("a.zydm,a.bjdm,b.xydm,yzcout from (select count(*) zcout, bjdm, xydm,nj,zydm,bjmc,zymc from view_xsjbxx group by ");
        		sqlV.append("bjdm, bjmc, xydm,nj,zydm,zymc ) a left join(select count(*) yzcout, bjdm, xydm,nj,zydm from view_xszsxx group by bjdm,");
        		sqlV.append("xydm,nj,zydm) b on a.bjdm = b.bjdm and a.xydm=b.xydm and a.zydm=b.zydm and a.nj=b.nj  where "+querry+" and a.xydm=?  order by zydm,bjdm)");
        		        		
//        		sqlV.append("select nj,xydm,zydm,zymc,bjmc,bjdm,zcout,yzcout,zcout-yzcout wzcout,round((zcout - yzcout) / zcout * 100,2) wzbl from(  ");
//        		sqlV.append("select zcout,(select distinct bjmc from view_xsjbxx b where b.bjdm=a.bjdm and rownum=1 )bjmc, (select zymc from view_njxyzybj c where c.bjdm = a.bjdm and rownum=1) zymc, ");
//        		sqlV.append("(select zydm from view_njxyzybj c where c.bjdm = a.bjdm and rownum=1 ) zydm,(select nj from view_njxyzybj c where c.bjdm = a.bjdm and rownum=1) nj,nvl(yzcout,0)yzcout,a.xydm,a.bjdm  from (   ");
//        		sqlV.append("(select count(*) zcout,bjdm,xydm from view_xsjbxx  where "+querrT+" group by bjdm,xydm )a  left join  ");
//        		sqlV.append("(select count(*) yzcout,bjdm,xydm from view_xszsxx where "+querrT+" group by bjdm,xydm )b on a.bjdm=b.bjdm ");
//        		sqlV.append(" )where  a.xydm=? order by xydm,bjdm desc ) where "+querrT);
        		List listTem = dao.getList(sqlV.toString(),new String[]{xyList.get(i).get("xydm")},new String[]{"nj","zymc","bjmc","zcout","yzcout","wzcout","wzbl"});//总人数、已住人数、未住人数、未住比例       		
        		
        		map.put("coutinfo",listTem);
        		list.add(map);          		       		
        	}       	
        }
//        String sqla = "select a.xydm,a.xymc,a.xyzcout,a.xyyzcout,a.xywzcout,a.bjcout,b.bjdm,b.bjmc,b.zcout,b.yzcout,b.wzcout,b.wzbl "
//        	 +"from (select a.xydm, a.xymc, xyzcout,nvl(xyyzcout, 0) xyyzcout,xyzcout - (nvl(xyyzcout, 0)) xywzcout, (select count(distinct bjdm) "
//        	 +"from view_xsjbxx b where a.xydm = b.xydm group by xydm) bjcout from (select xydm, xymc, count(xh) xyzcout "
//        	 +"from view_xsjbxx group by xydm, xymc order by xydm) a   left join (select xydm, xymc, count(xh) xyyzcout "
//        	 +"from view_xszsxx   group by xydm, xymc) b on a.xydm = b.xydm   where 1 = 1) a left join (select xydm, "
//        	 +" bjmc,bjdm, zcout,yzcout,zcout - yzcout wzcout,round((zcout - yzcout) / zcout * 100, 2) wzbl from (select zcout, "
//        	 +"(select distinct bjmc  from view_xsjbxx b  where b.bjdm = a.bjdm) bjmc,  nvl(yzcout, 0) yzcout,  a.xydm,a.bjdm "
//        	 +"from ((select count(*) zcout, bjdm, xydm from view_xsjbxx   group by bjdm, xydm) a left join (select count(*) yzcout, bjdm, xydm "
//        	 +"from view_xszsxx group by bjdm, xydm) b on a.bjdm = b.bjdm) order by xydm, bjdm desc)) b on a.xydm = b.xydm";
//        List listTem = dao.getList(sqla.toString(),new String[]{},new String[]{"nj","bjmc","zcout","yzcout","wzcout","wzbl"});//总人数、已住人数、未住人数、未住比例       		
//		map.put("coutinfo",listTem);
        return list;
	}
	public ArrayList<String[]> dao_wxsyzy_xsrzqk_Tj(GyglCsmzModel model){
		DAO  dao = DAO.getInstance();
		String nj= model.getNj();
		String xydm = model.getXydm();
		String zydm = model.getZydm();
		String bjdm = model.getBjdm();
		StringBuffer querry = new StringBuffer(" where 1=1 ");
		querry.append(Base.isNull(nj)?"":" and nj='"+nj+"'");
		querry.append(Base.isNull(xydm)?"":" and xydm='"+xydm+"'");
		querry.append(Base.isNull(bjdm)?"":" and bjdm='"+bjdm+"'");
		querry.append(Base.isNull(zydm)?"":" and zydm='"+zydm+"'");		
		String sql = "select xh,xm,xb,xymc,nj,bjmc,sfzh,ldmc,qsh,rzrq, jzrq,sfbz,xqdm,qsdh,bz from view_xszsxx "+querry;
		return dao.rsToVator(sql.toString(), new String[]{}, new String[]{"xh","xm","xb","xymc","nj","bjmc","sfzh","ldmc","qsh","rzrq","jzrq","sfbz","xqdm","qsdh","bz"});
	}
	
	
	
	
	public ArrayList<String[]> rzqktj(GyglCsmzActionForm model){
		
		DAO dao = DAO.getInstance();
		StringBuilder sql = new StringBuilder();
		
		sql.append("select a.*,");
		sql.append("(select xymc from view_njxyzybj where xydm=a.xydm and rownum=1) xymc,");
		sql.append("(select zymc from view_njxyzybj where zydm=a.zydm and rownum=1) zymc,");
		sql.append("(select bjmc from view_njxyzybj where bjdm=a.bjdm and rownum=1) bjmc ");
		sql.append(" from (");
		sql.append("select a.nj,a.xydm,a.zydm,a.bjdm,a.xb,a.rzrs,b.zrs,b.zrs-a.rzrs wrzrs,");
		sql.append("round((b.zrs-a.rzrs)/b.zrs,4)*100||'%' wrzbl from (");
		sql.append("select nj,xydm,zydm,bjdm,xb,count(*) rzrs from ");
		sql.append("(select * from view_xszsxx where 1=1 ");
		
		if (StringUtils.isNotNull(model.getNj())) {
			sql.append(" and nj='");
			sql.append(model.getNj());
			sql.append("'");
		}
		
		if (StringUtils.isNotNull(model.getXydm())) {
			sql.append(" and xydm='");
			sql.append(model.getXydm());
			sql.append("'");
		}
		
		if (StringUtils.isNotNull(model.getZydm())) {
			sql.append(" and zydm='");
			sql.append(model.getZydm());
			sql.append("'");
		}
		
		if (StringUtils.isNotNull(model.getBjdm())) {
			sql.append(" and bjdm='");
			sql.append(model.getBjdm());
			sql.append("'");
		}
		
		sql.append(")group by nj,xydm,zydm,bjdm,xb");
		sql.append(") a left join (");
		sql.append(" select nj,xydm,zydm,bjdm,xb,count(*) zrs from view_xsjbxx group by nj,xydm,zydm,bjdm,xb");
		sql.append(") b on a.nj=b.nj and a.xydm=b.xydm and a.zydm=b.zydm and a.bjdm=b.bjdm and a.xb=b.xb");
		sql.append(") a order by xymc,nj,zymc,bjmc,xb");
		
		
		return dao.rsToVator(sql.toString(), new String[] {}, new String[] {"xymc","nj","zymc","bjmc","xb","zrs","rzrs","wrzrs","wrzbl"});
	}
	
}
