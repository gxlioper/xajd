/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: LP</p>
 * <p>Version: 1.0</p>
 * <p>Time:2008-12-5 下午03:22:41</p>
 */
package xgxt.xsgygl.zjjjzyjsxy;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import oracle.sql.CLOB;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.DealString;

public class GyglZjjjzyDao {
	public boolean dao_lzSave(LzModel model) throws Exception{
		DAO dao     = DAO.getInstance();
		String lddm = DealString.toGBK(model.getLddm());
		String ldmc = dao.getOneRs("select ldmc from sslddmb where lddm=?",new String[]{lddm}, "ldmc");
	    String lz   = DealString.toGBK(model.getLz());
	    String rzrq = DealString.toGBK(model.getRzrq());
	    String lxdh = DealString.toGBK(model.getLxdh());
	    String dzyx = DealString.toGBK(model.getDzyx());
	    String zz   = DealString.toGBK(model.getZz());
	    String bz  = DealString.toGBK(model.getBz());	   
	    String sql = "insert into lzxxb(lddm,ldmc,lz,rzrq,zz,lxdh,dzyx,bz)values(?,?,?,?,?,?,?,?) ";
	    return dao.runUpdate(sql,new String[]{lddm,ldmc,lz,rzrq,zz,lxdh,dzyx,bz});   
	}
	public HashMap<String,String>  dao_getLzInfo(String xh){
		DAO dao = DAO.getInstance();
		String sql = "select xm,xb,ldmc||qsh zz,ssbh,lddm from view_xszsxx where xh=?";
		return dao.getMap(sql,new String[]{xh},new String[]{"xm","xb","zz","ssbh","lddm"});
	}
	public ArrayList<HashMap<String, String>> dao_getLzMTitle() {
		ArrayList<HashMap<String, String>> result = new ArrayList<HashMap<String, String>>();
		String[] colList   = null;
		String[] colListCN = null;
		colList = new String[]{"prkey", "ldmc","lz","xm","xb","bjmc","lxdh","rzrq","sfzz"};//查询显示字段 
		colListCN = new String[]{ "主键","楼栋名称","楼长","姓名","性别","班级","联系电话","任职日期","是否在职" };
		for (int i = 0; i < colList.length; i++) {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("en", colList[i]);
			map.put("cn", colListCN[i]);
			result.add(map);
			map = null;
		}
		return result;
	}
	public ArrayList<String[]> dao_getLzMResult(LzModel model) {
		DAO dao = DAO.getInstance();
		ArrayList<String[]> rs = new ArrayList<String[]>();
		String pk   = "lddm||lz||rzrq";
		String sql    =  "";
		String[] colList = null;
		StringBuffer querry = new StringBuffer();
		//查询条件		
		String lddm = model.getLddm();
		String lz   = DealString.toGBK(model.getLz().trim());
		String sfzz = DealString.toGBK(model.getSfzz());
		String xm   = DealString.toGBK(model.getXm().trim());
		querry.append(" where 1=1 ");
		querry.append(Base.isNull(lddm)?"":" and lddm='"+lddm+"' ");
		querry.append(Base.isNull(lz)?"":" and lz = '"+lz+"' ");	
		querry.append(Base.isNull(sfzz)?"":" and sfzz = '"+sfzz+"' ");	
		querry.append(Base.isNull(xm)?"":" and xm like '%"+xm+"%' ");	
		colList = new String[]{"prkey", "ldmc","lz","xm","xb","bjmc","lxdh","rzrq","sfzz"};//查询显示字段 
		sql     = " select "+pk+" prkey,ldmc,lz,xm,xb,bjmc,lxdh,rzrq,sfzz from view_lzxx ";  
//		执行查询
		rs = dao.rsToVator(sql + querry.toString(), new String[]{},colList);
		return rs;
	}
	public HashMap<String,String> dao_lzXx(String pkValue) {
		DAO dao = DAO.getInstance();
		String pk   = "lddm||lz||rzrq";
		String sql = "select * from view_lzxx where "+pk+"=?";
		String[] colList = new String[]{ "lddm","ldmc","lz","xm","xb","zz","rzrq","lzrq","lxdh","dzyx","bz","sfzz"};
		return dao.getMap(sql,new String[]{pkValue},colList);
	}
	public boolean dao_lzModi(LzModel model,String pkValue) throws Exception{
		DAO dao     = DAO.getInstance();
		String pk   = "lddm||lz||rzrq";
	    String lxdh = DealString.toGBK(model.getLxdh());
	    String dzyx = DealString.toGBK(model.getDzyx());
	    String zz   = DealString.toGBK(model.getZz());
	    String bz   = DealString.toGBK(model.getBz());
	    String lzrq = DealString.toGBK(model.getLzrq());
	    String sfzz = DealString.toGBK(model.getSfzz());
	    
	    String sql = "update lzxxb set zz=?,lxdh=?,dzyx=?,bz=?,lzrq=?,sfzz=?  where  "+pk+"= ? ";
	    return dao.runUpdate(sql,new String[]{zz,lxdh,dzyx,bz,lzrq,sfzz,pkValue});   
	}
	public boolean dao_lzDel(String pkValue) throws Exception{
		DAO  dao    = DAO.getInstance();	    	
		String pk   = "lddm||lz||rzrq";
		boolean done = false;
		String sql    = " delete lzxxb where "+pk+"=? ";
		done    = dao.runUpdate(sql,new String[]{pkValue});	    	
		return done;    	
	}
	public HashMap<String,String>  dao_getCzInfo(String xh){
		DAO dao = DAO.getInstance();
		String sql = "select xm,xb ,ldmc||qsh zz,lddm,cs from view_xszsxx where xh=?";
		return dao.getMap(sql,new String[]{xh},new String[]{"xm","xb","zz","lddm","cs"});
	}
	public boolean dao_czSave(CzModel model) throws Exception{
		DAO dao     = DAO.getInstance();
		String lddm = DealString.toGBK(model.getLddm());
		String lc   = DealString.toGBK(model.getLc());
		String ldmc = dao.getOneRs("select ldmc from sslddmb where lddm=?",new String[]{lddm}, "ldmc");
	    String cz   = DealString.toGBK(model.getCz());
	    String rzrq = DealString.toGBK(model.getRzrq());
	    String lxdh = DealString.toGBK(model.getLxdh());
	    String dzyx = DealString.toGBK(model.getDzyx());
	    String zz   = DealString.toGBK(model.getZz());
	    String bz  = DealString.toGBK(model.getBz());	   
	    String sql = "insert into czxxb(lddm,ldmc,lc,cz,rzrq,zz,lxdh,dzyx,bz)values(?,?,?,?,?,?,?,?,?) ";
	    return dao.runUpdate(sql,new String[]{lddm,ldmc,lc,cz,rzrq,zz,lxdh,dzyx,bz});   
	}
	public ArrayList<HashMap<String, String>> dao_getCzMTitle() {
		ArrayList<HashMap<String, String>> result = new ArrayList<HashMap<String, String>>();
		String[] colList   = null;
		String[] colListCN = null;
		colList = new String[]{"prkey", "ldmc","lc","cz","xm","xb","bjmc","lxdh","rzrq","sfzz"};//查询显示字段 
		colListCN = new String[]{ "主键","楼栋名称","楼层","层长","姓名","性别","班级","联系电话","任职日期","是否在职" };
		for (int i = 0; i < colList.length; i++) {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("en", colList[i]);
			map.put("cn", colListCN[i]);
			result.add(map);
			map = null;
		}
		return result;
	}
	public ArrayList<String[]> dao_getCzMResult(CzModel model) {
		DAO dao = DAO.getInstance();
		ArrayList<String[]> rs = new ArrayList<String[]>();
		String pk   = "lddm||lc||cz||rzrq";
		String sql    =  "";
		String[] colList = null;
		StringBuffer querry = new StringBuffer();
		//查询条件		
		String lddm = model.getLddm();
		String cz   = DealString.toGBK(model.getCz().trim());
		String sfzz = DealString.toGBK(model.getSfzz());
		String xm   = DealString.toGBK(model.getXm().trim());
		querry.append(" where 1=1 ");
		querry.append(Base.isNull(lddm)?"":" and lddm='"+lddm+"' ");
		querry.append(Base.isNull(cz)?"":" and cz = '"+cz+"' ");	
		querry.append(Base.isNull(sfzz)?"":" and sfzz = '"+sfzz+"' ");	
		querry.append(Base.isNull(xm)?"":" and xm like '%"+xm+"%' ");	
		colList = new String[]{"prkey", "ldmc","lc","cz","xm","xb","bjmc","lxdh","rzrq","sfzz"};//查询显示字段 
		sql     = " select "+pk+" prkey,ldmc,lc,cz,xm,xb,bjmc,lxdh,rzrq,sfzz from view_czxx ";  
//		执行查询
		rs = dao.rsToVator(sql + querry.toString(), new String[]{},colList);
		return rs;
	}
	public boolean dao_czModi(CzModel model,String pkValue) throws Exception{
		DAO dao     = DAO.getInstance();
		String pk   = "lddm||lc||cz||rzrq";
	    String lxdh = DealString.toGBK(model.getLxdh());
	    String dzyx = DealString.toGBK(model.getDzyx());
	    String zz   = DealString.toGBK(model.getZz());
	    String bz   = DealString.toGBK(model.getBz());
	    String lzrq = DealString.toGBK(model.getLzrq());
	    String sfzz = DealString.toGBK(model.getSfzz());
	    
	    String sql = "update czxxb set zz=?,lxdh=?,dzyx=?,bz=?,lzrq=?,sfzz=?  where  "+pk+"= ? ";
	    return dao.runUpdate(sql,new String[]{zz,lxdh,dzyx,bz,lzrq,sfzz,pkValue});   
	}
	public HashMap<String,String> dao_czXx(String pkValue) {
		DAO dao = DAO.getInstance();
		String pk   = "lddm||lc||cz||rzrq";
		String sql = "select * from view_czxx where "+pk+"=?";
		String[] colList = new String[]{ "lddm","ldmc","lc","cz","xm","xb","zz","rzrq","lzrq","lxdh","dzyx","bz","sfzz"};
		return dao.getMap(sql,new String[]{pkValue},colList);
	}
	public boolean dao_czDel(String pkValue) throws Exception{
		DAO  dao    = DAO.getInstance();	    	
		String pk   = "lddm||lc||cz||rzrq";
		boolean done = false;
		String sql    = " delete czxxb where "+pk+"=? ";
		done    = dao.runUpdate(sql,new String[]{pkValue});	    	
		return done;    	
	}
    public boolean dao_aqjbSave(String title,String content,String userName,String isModi,String documentId) throws Exception{
    	DAO    dao = DAO.getInstance();
    	String sql = " ";   
    	boolean done = false;
    	if("yes".equalsIgnoreCase(isModi)){
    		sql  = " update gygl_mzjb set jbtitle=?,jbcontent=?,jbpubtime=(select to_char(sysdate,'yyyy-mm-dd hh24:mi:ss') from dual),xxsh='未审核' where jbid=? ";
    		done = dao.runUpdate(sql,new String[]{title,content,documentId});
    	}else{
    		String xm = dao.getOneRs("select xm from yhb where yhm=? ", new String[]{userName},"xm");
    		sql  = "insert into gygl_mzjb(jbid,jbtitle,jbcontent,jbpuber,jbpubername)values(gygl_mzjbid.nextval,?,?,?,?)";
    		done = dao.runUpdate(sql, new String[]{title,content,userName,xm});
    	}
    	return done;
    }
    public String dao_getJbTitle(String documentId){
    	DAO dao        = DAO.getInstance();
    	String sql     = "select jbtitle from gygl_mzjb where jbid=?";
    	String jbTitle = dao.getOneRs(sql, new String[] { documentId } , "jbtitle");
    	return jbTitle;
    }
    public String dao_getJbContent(String documentId) throws SQLException{
    	DAO dao    = DAO.getInstance();
    	String sql = "select jbcontent from gygl_mzjb where jbid=?";
    	CLOB clob = dao.getOneClob(sql, new String[] { documentId }, "jbcontent");    	
    	return clob.getSubString(1L, (int) clob.length());
    }
    public List dao_getJbMResult(String userName){
    	DAO dao    = DAO.getInstance();
    	String sql = " select jbid ,jbtitle,jbcontent,jbpubtime,jbpubername,xxsh,(case when xxsh ='通过' then 'yes' else 'no' end )sfpass from gygl_mzjb where jbpuber=? order by jbpubtime desc ";
    	return dao.getList(sql, new String[] {userName}, new String[]{"jbid","jbtitle","jbcontent","jbpubtime","jbpubername","xxsh","sfpass"});
	}
	public ArrayList<HashMap<String, String>> dao_getMzjbTitle() {
		ArrayList<HashMap<String, String>> result = new ArrayList<HashMap<String, String>>();		 
		String[] colList = new String[]{"jbid","jbtitle","jbpubtime","jbpubername","xxsh"};//查询显示字段 
		String[] colListCN = new String[]{ "主键","标题","发布时间","发布人","审核状态" };
		for (int i = 0; i < colList.length; i++) {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("en", colList[i]);
			map.put("cn", colListCN[i]);
			result.add(map);
			map = null;
		}
		return result;
	}
	public String[] dao_aqjbShow(String documentId) throws SQLException{
		DAO dao = DAO.getInstance();
		String[] showPar = new String[]{"","","","",""};
		String[] tit = new String[] {  "jbtitle","jbpubtime","jbpubername" };
		String sql = " select jbtitle,jbpubtime,jbpubername from gygl_mzjb where jbid=? ";		
		String[] rs = dao.getOneRs(sql, new String[] { documentId }, tit);		
		CLOB clob = dao.getOneClob("select jbcontent from gygl_mzjb where jbid=?", new String[] { documentId }, "jbcontent");
		String jbcontent = clob.getSubString(1L, (int) clob.length());
		if(rs!=null){
			showPar[0]=rs[0];
			showPar[1]=rs[1];
			showPar[2]=rs[2];
			showPar[3]=jbcontent;
		}
		return showPar;
	}
	public boolean dao_aqjbDel(String documentId) throws Exception{
		DAO dao       = DAO.getInstance();
		String sql    = " delete from gygl_mzjb where jbid=?";
		boolean done = dao.runUpdate(sql,new String[]{documentId});
		return done;
	}
	public ArrayList<String[]> dao_getMzjbShResult(String yesNo,String rzrq,String lzrq,String title) {
		DAO dao = DAO.getInstance();
		ArrayList<String[]> rs = new ArrayList<String[]>();
		StringBuffer querry = new StringBuffer();
		//查询条件			
		querry.append(" where 1=1 ");
		querry.append(Base.isNull(yesNo)?"":" and xxsh='"+yesNo+"' ");	
		querry.append(Base.isNull(title)?"":" and jbtitle like '%"+title+"%' ");
		if(!Base.isNull(rzrq)&&!Base.isNull(lzrq)){
			querry.append(" and jbpubtime between '"+rzrq+"' and '"+lzrq+"'");
		}else if(!Base.isNull(rzrq)){
			querry.append(" and jbpubtime like '"+rzrq+"%'");
		}else if(!Base.isNull(lzrq)){
			querry.append(" and jbpubtime like '"+lzrq+"%'");
		}
		String[] colList = new String[]{"jbid","jbtitle","jbpubtime","jbpubername","xxsh"};//查询显示字段 
		String sql     = " select jbid ,jbtitle,jbpubtime,jbpubername,xxsh from gygl_mzjb "+querry+" order by jbpubtime desc ";  
//		执行查询
		rs = dao.rsToVator(sql , new String[]{},colList);
		return rs;
	}
	public boolean dao_aqjbSh(String documentId,String shType) throws Exception{
		DAO dao = DAO.getInstance();
		String shV = "未审核";
		if("yes".equalsIgnoreCase(shType)){
			shV="通过";
		}else{
			shV="不通过";
		}
		String sql = " update gygl_mzjb set xxsh=? where jbid=? ";
		return dao.runUpdate(sql, new String[]{shV,documentId});
	}
	
}
