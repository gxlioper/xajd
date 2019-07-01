/**
 * ������2007-8-3����02:49:09
 */
package xgxt.sztz.dao;
import java.util.HashMap;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import xgxt.DAO.*;
import xgxt.action.Base;
import xgxt.utils.Fdypd;

/**
 * @author lp
 */
public class SztzDao {
	private  DataSource db = null;
	private  Connection conn = null;
	private  PreparedStatement stmt = null;
//	private  Statement stat = null;
//	private  CallableStatement cstmt = null;
	private  ResultSet rs = null;
//	private ResultSetMetaData rsmd = null;	
	
	public SztzDao() {
		// ���캯����ʼ���������ӡ�;
		this.db = DBPool.getPool(); ////////Configuration.connMgr;
	}
	public List<HashMap<String, String>> getCjTzxmJbList() {
		// ��ȡ�μ�������չ��Ŀ�����б�
		DAO dao = DAO.getInstance();
		String[] cjtzxmjb = new String[] { "���ʼ�", "���Ҽ�","ʡ��","Ժ��","ϵ��"};		
		return dao .arrayToList(cjtzxmjb, cjtzxmjb);
	}	
	public List<HashMap<String, String>> getTzxmCjSfList() {
		// ��ȡ������չ��Ŀ�μ�����б�
		DAO dao= DAO.getInstance();
		String[] tzxmcjsf = new String[] { "����","��֯"};
		return dao.arrayToList(tzxmcjsf, tzxmcjsf);
	}	
	/**
	 * ֻ�����ڷ���һά������
	 * */
	public String[] getRs(String sql,String[] input,String output)
	throws Exception {
		String [] result = null;
		try {
			conn = db.getConnection();
			stmt = conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			for(int i=0;i<input.length;i++){
				stmt.setString(i+1, input[i]);
			}
			rs = stmt.executeQuery();
			rs.first();
			int size = 0;
			if(rs.last()){
				size = rs.getRow();} 
			result = new String[size];
			rs.beforeFirst();
			for(int i=0;i<size;i++){
				if( rs.next() ){				
					result[i] = rs.getString(output);				
				}
			}
		} catch(Exception e){
			e.printStackTrace();
			if(stmt != null){
				rs.close();
				stmt.close();    			
			}
			conn.close();
		} finally{
			rs.close();
			stmt.close();
			conn.close();
		}   
		return result;
	}
	
   //dwr�ͻ����жϱ���Ƿ��Ѿ�����
	public String getDataEx(String tableName, String column,
			String columnValue) {
		DAO dao= DAO.getInstance();
		String reVal = "";
		int result = 0;		
		String sql = "select count(*) num from " + tableName + " where "+ column + "=?";
		result = Integer.parseInt(dao.getOneRs(sql,new String[] { columnValue }, "num"));
		if (result > 0) {
			reVal = columnValue;
		}
		return reVal;
	}
    //dwr�ͻ����жϱ���Ƿ��Ѿ�����(������)
	public boolean getInfoEx(String tableName,String pk,String pkValue,String andCondition){
		DAO dao= DAO.getInstance();
		boolean flag=false;
		int result=0;
		String sql="select count(*) num from "+tableName+" where "+pk+"=? ";
		if(andCondition != ""){
			sql += " and ";
			sql += andCondition;
		}
		result=Integer.parseInt(dao.getOneRs(sql, new String[]{pkValue}, "num"));
		if(result>0){
			flag=true;
		}else{
			flag=false;
		}
		return flag;
	}
	public boolean getInfoEx2(String tableName,String querry){
		DAO dao= DAO.getInstance();
		boolean flag=false;
		int result=0;
		String sql="select count(*) num from "+tableName+" where "+querry;		
		result=Integer.parseInt(dao.getOneRs(sql, new String[]{}, "num"));
		if(result>0){
			flag=true;
		}else{
			flag=false;
		}
		return flag;
	}

	
	// ��ȡ������չ�༶�б�
	public List<HashMap<String, String>> getTzBjList(String xn, String xq) {
		DAO dao= DAO.getInstance();
		String sql = "";
		if ((xn == null) || xn.equalsIgnoreCase("")
				|| xn.equalsIgnoreCase("all")) {
			if ((xq == null) || xq.equalsIgnoreCase("")
					|| xq.equalsIgnoreCase("all")) {
				sql = "select distinct dm,mc from sztz_bjdmb";
			} else {
				sql = "select distinct dm,mc from sztz_bjdmb where xq='"
					+ xq + "'";
			}
		} else {
			if ((xq == null) || xq.equalsIgnoreCase("")
					|| xq.equalsIgnoreCase("all")) {
				sql = "select distinct dm,mc from sztz_bjdmb where xn='"+xn+"' order by xn";
			} else {
				sql = "select distinct dm,mc from sztz_bjdmb where xn='"+xn+"' and xq='"
					+ xq + "' order by dm";
			}
		}
		return dao.getList(sql, new String[] {}, new String[] { "dm", "mc" });
	}	
	//��չ��Ŀ�����б�
	public List<HashMap<String, String>> getTzXmJbList(int type) {
		// ��Ŀ���������
		DAO dao= DAO.getInstance();
		String[] chkList = null;
		if (type == 1) {
			chkList = new String[] { "�༶" };
		} else if (type == 2) {
			chkList = new String[] { "","�༶" ,"Ժϵ"};
		} else if (type == 3) {
			chkList = new String[] { "","ѧУ","����","ʡ��","����","����"};
		} else if (type == 4) {
			chkList = new String[] {"","Ժϵ", "ѧУ","����","ʡ��","����","����"};
		} else if (type ==5 ){
			chkList = new String[] {"","�༶","Ժϵ", "ѧУ","����","ʡ��","����","����"};
		} else if (type ==6){
			chkList = new String[] {"","���Ҽ�","ʡ����","�м�","Ժ��","��Ժ��"};
		}
		return dao.arrayToList(chkList, chkList);
	}
	//��ð�ɲ�����Ȩ��  ��ɳ����
	public String getBgbPower(String xh){
		DAO dao= DAO.getInstance();
		String power = "0";
		String str = "";
		String sql = "select count(a.xh) cou from sxjy_bjgbxxb a,csmz_sztzszb b where a.bjgbdm=b.bjgbdm and a.xh=?";
		str = dao.getOneRs(sql, new String[]{xh}, "cou");
		if(str!="0"&&!str.equalsIgnoreCase("0")){
			power = "1";
		}
		return power;
	}
	public String getBgbM(){
		DAO dao= DAO.getInstance();
		String sql = "select a.bjgbmc mc from sxjy_bjgbzlb a,csmz_sztzszb b where a.bjgbdm=b.bjgbdm ";
        return dao.getOneRs(sql,new String[]{},"mc");
	}
	public String[] getStuInfo(String xh){
		DAO dao= DAO.getInstance();
		String sql = "select xh,xb,xm,nj,xydm,zydm,bjdm,xymc,zymc,bjmc from view_xsjbxx where xh=? ";
        return dao.getOneRs(sql,new String[]{xh},new String[]{"xh","xm","nj","xydm","zydm","bjdm","xymc","zymc","bjmc"});
	}
	/**
	 * 
	 * @return pkValue����ֵ
	 */
	public String getyhbmmc(String pkValue){
		DAO dao = DAO.getInstance();		
		StringBuffer sql = new StringBuffer();
		StringBuffer querry = new StringBuffer("");
		querry.append(Base.isNull(pkValue)?" and 1=2 ":" and a.yhm='"+pkValue+"' ");
		sql.append(" select distinct b.bmdm, b.bmmc from yhb a,zxbz_xxbmdm b where a.szbm=b.bmdm ");
		return dao.getOneRs(sql+querry.toString(),new String[]{},"bmmc");   
	}
	// ��ȡ�б���Ϣ
	public void getListxx( HttpServletRequest request,String xydm, String zydm, String nj) {
		xydm = xydm == null ? "" : xydm;
		zydm = zydm == null ? "" : zydm;
		nj = nj == null ? "" : nj;
		final String bjKey = xydm + "!!" + zydm + "!!" + nj;
		request.setAttribute("njList", Base.getNjList());// �����꼶�б�
		request.setAttribute("xnList", Base.getXnndList());// ����ѧ���б�
		request.setAttribute("xqList", Base.getXqList());// ����ѧ���б�
		request.setAttribute("xyList", Base.getXyList());// ����ѧԺ�б�
		request.setAttribute("zyList", (Base.getZyMap()).get(xydm));// ����רҵ�б�
		request.setAttribute("bjList", (Base.getBjMap()).get(bjKey));// ���Ͱ༶�б�
	}
	public List<HashMap<String, String>> getTzxmSbList(String xh,String xydm) {
		DAO dao = DAO.getInstance();
		StringBuffer querry = new StringBuffer(" where 1=1");		
		querry.append(Base.isNull(xydm)?"":" and xydm='"+xydm+"' ");
		String sql = "";
		if(!Base.isNull(xh)){
			querry.append(Base.isNull(xh)?"":" and xh='"+xh+"' ");
			querry.append(" order by dm nulls first ");
		    sql = " select distinct bjdm dm,bjmc mc from view_xsjbxx ";
		}else{
			querry.append(" order by dm nulls first ");
			sql=  " select dm,mc,xydm from(select '' dm, '--��ѡ��--' mc,''xydm from dual union all(select distinct bjdm dm, bjmc mc , xydm from view_njxyzybj)) ";		
		}
		return dao.getList(sql+querry.toString(), new String[] {}, new String[] { "dm", "mc" });		
	}
	public List<HashMap<String, String>> getXsList(String bjdm) {
		DAO dao = DAO.getInstance();		
		String sql = "select a.xh dm,a.xh||'('||a.xm||'/'||bjgbmc||')' mc from view_xsjbxx a ,sxjy_bjgbxxb b,csmz_sztzszb c,sxjy_bjgbzlb d where a.bjdm=? and c.bjgbdm=b.bjgbdm and a.xh=b.xh and b.bjgbdm=d.bjgbdm ";	
		return dao.getList(sql, new String[] {bjdm}, new String[] { "dm", "mc" });		
	}
	public List<HashMap<String, String>> getYhList(String bmdm) {
		DAO dao = DAO.getInstance();
		StringBuffer querry = new StringBuffer("");
		querry.append(Base.isNull(bmdm)?"":" and a.szbm='"+bmdm+"' ");
		querry.append(" order by xm");
		String sql = "select a.yhm dm,a.xm||'('||a.yhm||')' mc,b.zmc from yhb a,yhzb b,ZXBZ_XXBMDM c,bks_dwdmb d where a.zdm=b.zdm and a.szbm=c.bmdm and a.dwdm=d.dwdm  ";
		return dao.getList(sql+querry.toString(), new String[]{},new String[] {"dm","mc"});
	}
	public String getTzxmDyCout(String xh,String xmid){
		DAO dao = DAO.getInstance();
		StringBuffer sql = new StringBuffer();
//		sql.append(" select count(xh) cout from (select  xh,xmid,xn,xq,kmdm,xmjb,cyjs,sfdy from ( ");
//		sql.append(" select xh,xmid,xn,xq,kmdm,xmjb,cyjs,sfdy from view_csmz_cgsbxx where xxsh<>'ͨ��'");
//		sql.append(" union (select xh,xmid,xn,xq,kmdm,xmjb,cyjs,sfdy from view_tzcgcjxx)");
//		sql.append(" ) a where xh=? and xn=(select xn from csmz_tzxmb b where id=?) and");
//		sql.append(" kmdm=(select kmdm from csmz_tzxmb b where id=? ) and sfdy='��' ) ");
		sql.append("select count(*)cout from view_cgtz a where kmdm=(select kmdm from csmz_tzxmb where id=?) ");
		sql.append(" and xh=? and xn=(select xn from csmz_tzxmb where id=?) and sfdy='��'");
		return dao.getOneRs(sql.toString(),new String[]{xmid,xh,xmid},"cout");
	}
	
	/**
	 * @return ѧ����Ϣ
	 * @author luo
	 */
	public String[] getXsInfo(String xh) {
		DAO dao = DAO.getInstance();
		String[] getPara = {  "xm","bjmc","lxdh","bjdm" };
		String sql = "select xm,bjmc,lxdh,bjdm from view_xsxxb where xh=?";
		String[] reVal = dao.getOneRs(sql, new String[] { xh }, getPara);
		if(reVal!=null){
			for(int i=0; i<reVal.length; i++){
				reVal[i] = reVal[i] == null ? "" : reVal[i];
			}
		}
		return reVal;
	}
	/**
	 * @param request
	 * @param myDao
	 *            ����ѧԺרҵ�༶��Ϣ
	 */
	public static void getXyZyBjxx(HttpServletRequest request) {
//		DAO dao = DAO.getInstance();
		String nj = request.getParameter("nj");
		String xydm = request.getParameter("xydm");
		String zydm = request.getParameter("zydm");
		String userDep = request.getSession().getAttribute("userDep").toString();
		String userType = request.getSession().getAttribute("userType").toString();
		if("xy".equalsIgnoreCase(userType)){
			xydm = userDep;
		}
		String userName = request.getSession().getAttribute("userName").toString();
		xydm = xydm == null ? "" : xydm;
		zydm = zydm == null ? "" : zydm;
		nj = nj == null ? "" : nj;
		String bjKey = xydm + "!!" + zydm + "!!" + nj;
		request.setAttribute("njList", Base.getNjList());
		request.setAttribute("xyList", Base.getXyList());
		request.setAttribute("zyList", (Base.getZyMap()).get(xydm));// ����רҵ�б�
		request.setAttribute("bjList", (Base.getBjMap()).get(bjKey));// ���Ͱ༶�б�
		if(Fdypd.isFdy(userName)){
			request.setAttribute("bjList",Fdypd.getFdybjList (userName));// ���Ͱ༶�б�				
		}
	}
	public static List<HashMap<String, String>> getKmList(){
		DAO dao = DAO.getInstance();
		return dao.getList("select kmdm,kmm from sztz_kmdmb",new String[]{},new String[]{"kmdm","kmm"});
	}
}
