package xgxt.jygl.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.jygl.form.JhzyForm;
import xgxt.jygl.model.JhzyModel;
import xgxt.szdw.utils.ModelToStrings;
import xgxt.utils.String.StringUtils;
import xgxt.xljk.zjlg.util.XljkUtil;



public class Dao_jhzy {
	DAO dao = DAO.getInstance();// 数据操作通用工具类

	public String getSysTime() throws SQLException {
		DAO dao = DAO.getInstance();
		String sql = "select substr(systime, 0, 4) || '年' || substr(systime, 5, 2) || '月' ||"
			+ " substr(systime, 7, 2) || '日' systime from (select to_char(sysdate, 'yyyymmdd') systime from dual)";
		String systime = dao.getOneRs(sql, new String[] {}, "systime");
		return systime;
	}
	public HashMap<String, String> dao_jysbsjsz() throws SQLException {
		DAO dao = DAO.getInstance();
		String sql = "select * from jhzy_jysbsjszb";
		HashMap<String, String> map = dao.getMap(sql, new String[]{}, new String[]{"jysbkssj","jysbjssj"});
		return map;
	}
	public boolean dao_savesjsz(JhzyModel model,HttpServletRequest request) throws Exception {
		DAO dao = DAO.getInstance();
		boolean result = false;
		StandardOperation.delete("jhzy_jysbsjszb", "1", "1", request);
//		StandardOperation.delete("delete from jhzy_jysbsjszb where 1=1", "jhzy_jysbsjszb", request);
		String sql = "insert into jhzy_jysbsjszb(jysbkssj,jysbjssj) values(?,?)";
		String[] input = new String[]{model.getJysbkssj(),model.getJysbjssj()};
		result = dao.insert(sql, input);
		return result;
	}
	public HashMap<String, String> dao_querysjsz(String sqsj) throws Exception {
		DAO dao = DAO.getInstance();
		HashMap<String, String> map = new HashMap<String, String>();
		String sql = "select jysbkssj,jysbjssj from jhzy_jysbsjszb a where '"+sqsj+"' between a.jysbkssj and  a.jysbjssj";
		map = dao.getMap(sql, new String[]{}, new String[]{"jysbkssj","jysbjssj"});
		return map;
	}
	public boolean dao_updatejysb(JhzyModel model,HttpServletRequest request) throws Exception {
//		DAO dao = DAO.getInstance();
		boolean result = false;
		String xymc = model.getXydm();
		String bynf = model.getBynf();
		String sbsjd = model.getSbsjd();
		String xl = model.getXl();
		String byrs = model.getByrs();
		String qyrs = model.getQyrs();
		String cghsxrs = model.getCghsxrs();
		String qyl = model.getQyl();
		String yprs = model.getYprs();
		String lhrs = model.getLhrs();
		String ypl = model.getYpl();
		String jyl = model.getJyl();
		String jyrs = model.getJyrs();
		String pkValue = model.getId();
		String[] input = new String[]{xymc,bynf,sbsjd,xl,byrs,qyrs,cghsxrs,qyl,yprs,lhrs,ypl,jyl,jyrs};
		String[] columns = new String[]{"xymc","bynf","sbsjd","xl","byrs","qyrs","cghsxrs","qyl","yprs","lhrs","ypl","jyl","jyrs"};
		result = StandardOperation.update("jhzy_jysbb", columns, input, "id", pkValue, request);
		return result;
	}
	public boolean dao_savejysb(JhzyModel model) throws Exception {
		DAO dao = DAO.getInstance();
		boolean result = false;
		String sql = "insert into jhzy_jysbb(xymc,bynf,sbsjd,xl,byrs,qyrs,cghsxrs,qyl,yprs,lhrs,ypl,jyl,jyrs,zymc) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		String xymc = model.getXydm();
		String bynf = model.getBynf();
		String sbsjd = model.getSbsjd();
		String xl = model.getXl();
		String byrs = model.getByrs();
		String qyrs = model.getQyrs();
		String cghsxrs = model.getCghsxrs();
		String qyl = model.getQyl();
		String yprs = model.getYprs();
		String lhrs = model.getLhrs();
		String ypl = model.getYpl();
		String jyl = model.getJyl();
		String jyrs = model.getJyrs();
		String zydm = model.getZydm();
		String[] input = new String[]{xymc,bynf,sbsjd,xl,byrs,qyrs,cghsxrs,qyl,yprs,lhrs,ypl,jyl,jyrs,zydm};
		result = dao.insert(sql, input);
		return result;
	}
	public boolean dao_issavejysb(JhzyModel model) throws Exception {
		DAO dao = DAO.getInstance();
		boolean result = false;
		String sql = "select * from jhzy_jysbb where zymc=? and bynf=? and xl=?";
		String zymc = model.getZydm();
		String bynf = model.getBynf();
		String xl = model.getXl();
		ArrayList<HashMap<String, String>> list = dao.getArrayList(sql, new String[]{zymc,bynf,xl}, new String[]{"id"});
		if(list !=null){
			if(list.size()>0){
				result=true;	
			}
		}
		return result;
	}
	public HashMap<String, String> dao_jysbview(String pk,String sqllx) throws SQLException {
		DAO dao = DAO.getInstance();
		String sql = "";
		if("update".equals(sqllx)){
			sql = "select id,xymc xydm,zymc zydm,bynf," 
				+"sbsjd,xl,byrs,qyrs,cghsxrs,qyl,yprs,lhrs,ypl,jyl,jyrs,rownum r from jhzy_jysbb a where id=?";
		}else{
			sql = "select id,(select distinct xymc from view_njxyzybj b where b.xydm=a.xymc) xydm," 
				+"(select distinct zymc from view_njxyzybj b where b.zydm=a.zymc) zydm,bynf," 
			    +"sbsjd,xl,byrs,qyrs,cghsxrs,qyl,yprs,lhrs,ypl,jyl,jyrs,rownum r from jhzy_jysbb a where id=?";
		}
		HashMap<String, String> map = dao.getMap(sql, new String[]{pk}, 
				new String[]{"id","xydm","zydm","bynf","sbsjd","xl","byrs","qyrs","cghsxrs","qyl","yprs","lhrs","ypl","jyl","jyrs"});
		return map;
	}
	public ArrayList<String[]> dao_jysbquery(JhzyForm myForm,JhzyModel model){
		DAO dao      = DAO.getInstance();
		ArrayList<String[]> result = new ArrayList<String[]>();
		
		String xymc = model.getXydm();
		String bynf = model.getBynf();
		String sbsjd = model.getSbsjd();
		String xl = model.getXl();
		String zymc = model.getZydm();
		
		StringBuffer querry = new StringBuffer(" where 1=1 ");
		querry.append(Base.isNull(xymc)?"":" and xymc='"+xymc.trim()+"' ");
		querry.append(Base.isNull(zymc)?"":" and zymc='"+zymc.trim()+"' ");
		querry.append(Base.isNull(bynf)?"":" and bynf='"+bynf.trim()+"' ");
		querry.append(Base.isNull(sbsjd)?"":" and sbsjd='"+sbsjd.trim()+"' ");
		querry.append(Base.isNull(xl)?"":" and xl like '%"+xl.trim()+"%' ");

		String[] colList = new String[]{"id","xymc","zymc","bynf","sbsjd","xl","byrs","qyrs","yprs","jyrs","lhrs","cghsxrs","qyl","ypl","jyl","xxsh"};
		String  sql = "select * from (select id,(select distinct xymc from view_njxyzybj b where b.xydm=a.xymc) " 
			+"xymc,(select distinct zymc from view_njxyzybj b where b.zydm=a.zymc) zymc,bynf,sbsjd,xl,byrs,qyrs,cghsxrs,qyl,yprs,lhrs,ypl,jyl,jyrs,xxsh,rownum r from jhzy_jysbb a "+querry+" order by zymc) " 
			+"where r<="
		+ (myForm.getPages().getStart() + myForm.getPages().getPageSize())
		+ " and r> "
		+ myForm.getPages().getStart();
		result = dao.rsToVator(sql, new String[]{},colList);
		sql = "select count(*) count from (select a.*,rownum r from jhzy_jysbb a )"+querry;
		myForm.getPages().setMaxRecord(
				Integer.parseInt(dao.getOneRs(sql, new String[] {},"count")));
		return result;
	}
	public void dao_jysbExpdata(HttpServletResponse response,JhzyModel model) throws IOException, Exception{
//		DAO dao      = DAO.getInstance();
//		ArrayList<String[]> result = new ArrayList<String[]>();
		
		String xymc = model.getXydm();
		String bynf = model.getBynf();
		String sbsjd = model.getSbsjd();
		String xl = model.getXl();
		String zydm = model.getZydm();
		
		StringBuffer querry = new StringBuffer(" where 1=1 ");
		querry.append(Base.isNull(xymc)?"":" and xymc='"+xymc.trim()+"' ");
		querry.append(Base.isNull(zydm)?"":" and zymc='"+zydm.trim()+"' ");
		querry.append(Base.isNull(bynf)?"":" and bynf='"+bynf.trim()+"' ");
		querry.append(Base.isNull(sbsjd)?"":" and sbsjd='"+sbsjd.trim()+"' ");
		querry.append(Base.isNull(xl)?"":" and xl like '%"+xl.trim()+"%' ");
		StringBuffer querry1 = new StringBuffer(" where 1=1 ");
		querry1.append(Base.isNull(xymc)?"":" and xymc='"+xymc.trim()+"' ");
		querry1.append(Base.isNull(bynf)?"":" and bynf='"+bynf.trim()+"' ");
		querry1.append(Base.isNull(xl)?"":" and xl like '%"+xl.trim()+"%' ");
//		querry.append(" and xxsh='通过'");
		String  sql ="";
		if(StringUtils.isNull(xymc) && StringUtils.isNull(zydm)){
			sql = "select * from (select xymc,zymc,(select distinct b.xymc from view_njxyzybj b " 
				+"where b.xydm=a.xymc) xydm,(select distinct b.zymc from view_njxyzybj b " 
				+"where b.zydm=a.zymc) zydm,bynf,sbsjd,xl,byrs,qyrs,cghsxrs,qyl,yprs,lhrs,"
				+"ypl,jyl,jyrs,xxsh from jhzy_jysbb a "+querry+") ";
		}else{
			sql = "select * from (select xymc,(select distinct xymc from view_njxyzybj b " +
					"where b.xydm=a.xymc) xydm,'' zymc,'' zydm,bynf,xl,sum(byrs) byrs," +
					"sum(qyrs) qyrs,sum(yprs) yprs,sum(jyrs) jyrs,sum(lhrs) lhrs,sum(cghsxrs) cghsxrs,"
					+"to_char(sum(qyl)/count(qyl),'999.9') qyl,to_char(sum(ypl)/count(ypl),'999.9') ypl,"
					+"to_char(sum(jyl)/count(jyl),'999.9') jyl from jhzy_jysbb a "+querry1+" " 
					+"group by xymc,bynf,xl union select xymc,(select distinct xymc "
					+"from view_njxyzybj b where b.xydm=a.xymc) xydm,zymc," 
					+"(select distinct b.zymc from view_njxyzybj b where a.zymc=b.zydm) zydm," 
					+"bynf,xl,sum(byrs) byrs,sum(qyrs) qyrs,sum(yprs) yprs,sum(jyrs) jyrs," 
					+"sum(lhrs) lhrs,sum(cghsxrs) cghsxrs,to_char(sum(qyl)/count(qyl),'999.9') qyl," 
					+"to_char(sum(ypl)/count(ypl),'999.9') ypl,to_char(sum(jyl)/count(jyl),'999.9') jyl " 
					+"from jhzy_jysbb a "+querry+" group by xymc,zymc,bynf,xl)  " 
					+"order by xymc,zymc desc";
		}
		response.reset();
		response.setContentType("application/vnd.ms-excel");
//		Excel2Oracle.exportData(sql, "jhzy_jysbb", response.getOutputStream());
		Vector<Object> vec = new Vector<Object>();
		WritableWorkbook wwb = Workbook.createWorkbook(response.getOutputStream());
		WritableSheet ws = wwb.createSheet("数据导出", 0);
		try {
//			String ColumnName[] = dao.getColumnName(sql);
			String ColumnName[] = {"xymc","zymc","bynf","xl","byrs","qyrs","cghsxrs","qyl","yprs","lhrs","ypl","jyl","jyrs"};
			String ColumnNameCN[] = dao.getColumnNameCN(ColumnName, "jhzy_jysbb");
			for (int m = 0; m < ColumnNameCN.length; m++) {
				ws.addCell(new Label(m, 0, ColumnNameCN[m]));
			}
			vec.addAll(dao.rsToVator(sql, new String[] {}, new String[]{"xydm","zydm","bynf","xl","byrs","qyrs","cghsxrs","qyl","yprs","lhrs","ypl","jyl","jyrs"}));
			int k = ColumnName.length;
			for (int i = 0; i < vec.size(); i++) {
				String[] tmp = (String[]) vec.toArray()[i];
				for (int j = 0; j < k; j++) {
					ws.addCell(new Label(j, i + 1, tmp[j]));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("数据导出失败!");
		} finally {
			wwb.write();
			wwb.close();
		}
	}
	public String dao_jysbdel(String pks)throws Exception {

		StringBuffer sb = new StringBuffer();
		String[] keys = pks.split("!!#!!");
		String[] pksql = new String[] {};
		String sql = "";
		for (int i = 0; i < keys.length; i++) {
			String pk = DealString.toGBK(keys[i]);// 得到主键
			sql = "delete from jhzy_jysbb where id = '" + pk + "'";
			// 把主键组合成sql语句
			sb.append(sql);
			sb.append("!!#!!");
		}
		// sql语句拆分成数组
		pksql = sb.toString().split("!!#!!");
		int[] judge = dao.runBatch(pksql);
		String whichpk = "";
		// 检查哪一条删除失败
		for (int i = 0; i < judge.length; i++) {
			if (judge[i] < 0) {
				whichpk = whichpk + " 第" + String.valueOf(i) + "条数据删除失败;\n";
			}
		}
		return whichpk;
	}
	public boolean dao_jysbdelAll()throws Exception {
		return dao.runUpdateTab("truncate table jhzy_jysbb");
	}
	public String dao_jysbXXsh(String pks,String xxsh,String xxshr)throws Exception {

		StringBuffer sb = new StringBuffer();
		String[] keys = pks.split("!!#!!");
		String[] pksql = new String[] {};
		String sql = "";
		String nowtime = (dao.getOneRs("select to_char(sysdate,'yyyy-mm-dd hh24:mi:ss') sdate from dual",
				new String[] {}, new String[] { "sdate" }))[0];
		for (int i = 0; i < keys.length; i++) {
			String pk = DealString.toGBK(keys[i]);// 得到主键
			if(StringUtils.isNotNull(pk)){
				sql = "update jhzy_jysbb set xxsh='"+xxsh+"',xxshshr='"+xxshr+"',xxshsj='"+nowtime+"' where id = '" + pk + "'";
				sb.append(sql);
				sb.append("!!#!!");
			}
		}
		pksql = sb.toString().split("!!#!!");
		int[] judge = dao.runBatch(pksql);
		String whichpk = "";
		for (int i = 0; i < judge.length; i++) {
			if (judge[i] < 0) {
				whichpk = whichpk + " 第" + String.valueOf(i) + "条数据更新失败;\n";
			}
		}
		return whichpk;
	}
	
	public ArrayList<String[]> getCphcs(String xydm, String nd) {
		StringBuffer query = new StringBuffer(" where 1=1 ");
		if(xydm!=null&&!xydm.equalsIgnoreCase("")){
			query.append(" and xydm = '");
			query.append(xydm);
			query.append("' ");
		}
		if(nd!=null&&!nd.equalsIgnoreCase("")){
			query.append(" and nd = '");
			query.append(nd);
			query.append("' ");
		}
		StringBuffer sql = new StringBuffer("select xydm||nd||lrsj pk,(select bmmc from zxbz_xxbmdm where a.xydm = bmdm) xymc,nd,substr(lrsj,0,10) lrsj,dxzphcs,dxzphdws,dxzphgws,xxzphcs,xxzphdws,xxzphgws,xjhcs,xjhdws,xjhgws from jhzy_zphcsb a ");
		sql.append(query);
		sql.append(" UNION select '' pk,'合计' xymc,'' nd,'' lrsj,to_char(sum(dxzphcs)) dxzphcs,to_char(sum(dxzphdws)) dxzphdws,to_char(sum(dxzphgws)) dxzphgws,");
        sql.append("to_char(sum(xxzphcs)) xxzphcs,to_char(sum(xxzphdws)) xxzphdws,to_char(sum(xxzphgws)) xxzphgws,to_char( sum(xjhcs)) xjhcs,");
        sql.append("to_char(sum(xjhdws)) xjhdws,to_char(sum(xjhgws)) xjhgws from jhzy_zphcsb");                        
        sql.append(query);
		return dao.rsToVator(sql.toString(), new String[]{}, new String[]{"pk","xymc","lrsj","dxzphcs","dxzphdws","dxzphgws","xxzphcs","xxzphdws","xxzphgws","xjhcs","xjhdws","xjhgws"});
	}
	
	/**
	 * 获取查询表头
	 * @param colListCN
	 * @return
	 */
	public ArrayList<HashMap<String, String>> dao_getSearchTitle(String[] colListCN ) {
		ArrayList<HashMap<String, String>> result = new ArrayList<HashMap<String, String>>();
		for (int i = 0; i < colListCN.length; i++) {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("cn", colListCN[i]);
			result.add(map);
			map = null;
		}
		return result;
	}
	
	/**
	 * 招聘会次数记录批量删除
	 * 
	 * @param
	 * @param
	 * @return
	 * @throws SQLException 
	 */
	public String zphcsPlDel(String pks, HttpServletRequest request) throws SQLException {
		StringBuffer sb = new StringBuffer();
		String[] keys = pks.split("!!#!!");
		String[] pksql = new String[] {};
		String sql = "";
		for (int i = 0; i < keys.length; i++) {
			String pk = DealString.toGBK(keys[i]);// 得到主键
			sql = "delete from jhzy_zphcsb where xydm||nd||lrsj = '" + pk + "'";
			// 把主键组合成sql语句
			sb.append(sql);
			sb.append("!!#!!");
		}
		// sql语句拆分成数组
		pksql = sb.toString().split("!!#!!");
		int[] judge = dao.runBatch(pksql);
		String whichpk = "";
		// 检查哪一条删除失败
		for (int i = 0; i < judge.length; i++) {
			if (judge[i] < 0) {
				whichpk = whichpk + " 第" + String.valueOf(i) + "条数据删除失败;\n";
			}
		}
		return whichpk;
	}
	
	public HashMap<String, String> getZphcsOne(String pk) {
		String tableName = "jhzy_zphcsb";
		String[] colList = new String[] {"dxzphcs","dxzphdws","dxzphgws","lrsj","nd","xjhcs","xjhdws","xjhgws","xxzphcs","xxzphdws","xxzphgws","xydm"};
		return commonQueryOne(tableName, colList, "xydm||nd||lrsj", pk);
	}
	public boolean zphcsUpdate(String pk, JhzyModel myModel, HttpServletRequest request) throws Exception {
		    String tableName = "jhzy_zphcsb";
			String pkComment = "xydm||nd||lrsj";
			String[] colList = new String[] {"dxzphcs","dxzphdws","dxzphgws","nd","xjhcs","xjhdws","xjhgws","xxzphcs","xxzphdws","xxzphgws","xydm"};
			String[] inputList = ModelToStrings.modelToStrings(colList, myModel);
			boolean updata = true;
			if (pk.equalsIgnoreCase("")) {
				updata = StandardOperation.insert(tableName, colList, inputList,
						request);
			} else {
				updata = StandardOperation.update(tableName, colList, inputList,
						pkComment, pk, request);
			}
		return updata;
	}
	
	public HashMap<String, String> commonQueryOne(String tableName,String [] colList,String pk,String pkValue) {
	    //    通过主键查询单个数据用 获得HashMap<String, String>形式的通用方法
			DAO dao = DAO.getInstance();
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
	
	//毕业生生源地名称列表
	public List<String[]> getXsSyd(String syddm,String xydm,String nd) throws Exception {
		String sql = "";
		String[] cols = null;
		String query = "";
		if(!Base.isNull(xydm)){
			query = " and b.xydm=? ";
		}
		if(Base.isNull(syddm)){
			sql = " select sydmc from syddmb a,(select distinct substr(jgshi,0,2)||'0000' dm from jygl_xsjbxxb a,view_xsjbxx b where a.xsxh=b.xh and " +
					"length(jgshi)>=6 and bynd=? "+query+") b where a.syddm=b.dm order by b.dm";
			if(!Base.isNull(xydm)){
				cols = new String[]{nd,xydm};
			}else{
				cols = new String[]{nd};
			}
		}else{
			sql = " select sydmc from syddmb a,(select distinct substr(jgshi,0,4)||'00' dm from jygl_xsjbxxb a,view_xsjbxx b where a.xsxh=b.xh and " +
					"length(jgshi)>=6 and substr(jgshi,0,2)||'0000'=? and bynd=? "+query+") b where a.syddm=b.dm order by b.dm";
			if(!Base.isNull(xydm)){
				cols = new String[]{syddm,nd,xydm};
			}else{
				cols = new String[]{syddm,nd};
			}
		}
		return dao.rsToVator(sql, cols, new String[]{"sydmc"});
	}
	
	//毕业生各生源地人数统计
	public List<String[]> getXsSydRs(String syddm,String xydm,String nd) throws Exception {
		String[] cols = null;
		StringBuffer sb = new StringBuffer();
		String query = "";
		if(!Base.isNull(xydm)){
			query = " and b.xydm=? ";
		}
		
		if(Base.isNull(syddm)){
			sb.append("select (select count(*) from jygl_xsjbxxb c,view_xsjbxx b where c.xsxh=b.xh and length(c.jgshi)>=6 and c.bynd=? and b.xydm=a.xydm and ");
			sb.append("b.zydm=a.zydm) zyrs,bmmc xymc,zymc,count,r from (select xydm,zydm,count(*) count,a.dqdm,b.r from (select b.xydm,b.zydm,substr(a.jgshi,0,2)||'0000' dqdm ");
			sb.append("from jygl_xsjbxxb a,view_xsjbxx b where a.xsxh=b.xh and length(jgshi)>=6 and a.bynd=? ");
			sb.append(query);
			sb.append(") a,( select rownum r,dm from ");
			sb.append(" (select dm from (select distinct substr(jgshi,0,2)||'0000' dm from jygl_xsjbxxb a,view_xsjbxx b where a.xsxh=b.xh and length(jgshi)>=6 and bynd=? ");
			sb.append(query);
			sb.append(") b order by b.dm)");
			sb.append(" ) b where a.dqdm=b.dm group by xydm,zydm,dqdm,b.r order by xydm,zydm) a,bks_zydm b,zxbz_xxbmdm c where a.xydm = c.bmdm and a.zydm = b.zydm ");
			if(!Base.isNull(xydm)){
				cols = new String[]{nd,nd,xydm,nd,xydm};
			}else{
				cols = new String[]{nd,nd,nd};
			}
		}else{
			sb.append("select (select count(*) from jygl_xsjbxxb c,view_xsjbxx b where c.xsxh=b.xh and length(c.jgshi)>=6 and c.bynd=? and substr(c.jgshi,0,2)||'0000'=? and b.xydm=a.xydm and ");
			sb.append("b.zydm=a.zydm) zyrs,bmmc xymc,zymc,count,r from (select xydm,zydm,count(*) count,a.dqdm,b.r from (select b.xydm,b.zydm,substr(a.jgshi,0,4)||'00' dqdm ");
			sb.append("from jygl_xsjbxxb a,view_xsjbxx b where a.xsxh=b.xh and length(jgshi)>=6 and a.bynd=? and substr(jgshi,0,2)||'0000'=? ");
			sb.append(query);
			sb.append(") a,( select rownum r,dm from ");
			sb.append(" (select dm from (select distinct substr(jgshi,0,4)||'00' dm from jygl_xsjbxxb a,view_xsjbxx b where a.xsxh=b.xh and length(jgshi)>=6 and bynd=? and substr(jgshi,0,2)||'0000'=?");
			sb.append(query);
			sb.append(") b order by b.dm)");
			sb.append(" ) b where a.dqdm=b.dm group by xydm,zydm,dqdm,b.r order by xydm,zydm) a,bks_zydm b,zxbz_xxbmdm c where a.xydm = c.bmdm and a.zydm = b.zydm ");
			if(!Base.isNull(xydm)){
				cols = new String[]{nd,syddm,nd,syddm,xydm,nd,syddm,xydm};
			}else{
				cols = new String[]{nd,syddm,nd,syddm,nd,syddm};
			}
		}
		return dao.rsToVator(sb.toString(), cols, new String[]{"zyrs","xymc","zymc","count","r"});
	}
	
	//毕业生各生源地人数统计
	public List<String[]> getXsSydHz(String syddm,String xydm,String nd) throws Exception {
		String[] cols = null;
		StringBuffer sb = new StringBuffer();
		String query = "";
		if(!Base.isNull(xydm)){
			query = " and b.xydm=? ";
		}
		if(Base.isNull(syddm)){
			sb.append("select count from (select count(*) count,jgshi from (select a.bynd,substr(a.jgshi,0,2)||'0000' jgshi from jygl_xsjbxxb a,view_xsjbxx b ");
			sb.append("where a.xsxh=b.xh and length(jgshi)>=6 and bynd=? ");
			sb.append(query);
			sb.append(") group by jgshi) a,(select dm from (select distinct substr(jgshi,0,2)||'0000' dm from jygl_xsjbxxb where length(jgshi)>=6 and bynd=? ) order by dm ) b where a.jgshi=b.dm");
			if(!Base.isNull(xydm)){
				query = " and xydm=? ";
				cols = new String[]{nd,xydm,nd};
			}else{
				cols = new String[]{nd,nd};
			}
		}else{
			sb.append("select count from (select count(*) count,jgshi from (select a.bynd,substr(a.jgshi,0,4)||'00' jgshi from jygl_xsjbxxb a,view_xsjbxx b ");
			sb.append("where a.xsxh=b.xh and length(jgshi)>=6 and bynd=? and substr(jgshi,0,2)||'0000'=? ");
			sb.append(query);
			sb.append(") group by jgshi) a,(select dm from (select distinct substr(jgshi,0,4)||'00' dm from jygl_xsjbxxb where length(jgshi)>=6 and bynd=? and substr(jgshi,0,2)||'0000'=?) order by dm ) b where a.jgshi=b.dm");
			if(!Base.isNull(xydm)){
				query = " and xydm=? ";
				cols = new String[]{nd,syddm,xydm,nd,syddm};
			}else{
				cols = new String[]{nd,syddm,nd,syddm};
			}
		}
		return dao.rsToVator(sb.toString(), cols, new String[]{"count"});
	}
	/**
	 * 获取代码列表
	 * @param tableName
	 * @param columns
	 * @return List
	 * */
	public List getDmList(String tableName, String[] columns){
		String sql = "select distinct ";
		for(int i=0; i<columns.length ; i++){
			if(i==columns.length-1){
				sql += columns[i];
			}else{
				sql += columns[i] + ",";
			}
		}
		sql += " from " + tableName;
		return dao.getList(sql, new String[]{}, columns);
	}
	public boolean dao_xmlgAdd(JhzyModel model) throws Exception {
		DAO dao = DAO.getInstance();
		boolean result = false;
		String[] insertList = {"sqly6","xm","zydm","xh","sysheng","syshi","syxian","yzbm","jtdz","lxfs","sqly","lxqx","xyyj","xxyj","sqly1","sqly2","sqly3","sqly4","sqly5","brqm"};
		String tableName = "xmlg_bysdalxsqb";
		String sql = "";
		String pk = model.getId();
		if (StringUtils.isNull(pk)) {
			sql = XljkUtil.insertSqlForModel(insertList, model, tableName);
		}else {
			sql = XljkUtil.updateSqlForModel(insertList, model, tableName, "id", pk);
		}
		result = dao.insert(sql, new String[]{});
		return result;
	}
	public ArrayList<String[]> dao_xmlgquery(JhzyForm myForm,JhzyModel model,String userType){
		DAO dao      = DAO.getInstance();
		ArrayList<String[]> result = new ArrayList<String[]>();
		
		String xh = myForm.getXh();
		String xm = myForm.getXm();
		String zymc = myForm.getZydm();
		String xymc = model.getXymc();
		
		StringBuffer querry = new StringBuffer(" where 1=1 ");
		querry.append(Base.isNull(xh)?"":" and xh='"+xh.trim()+"' ");
		querry.append(Base.isNull(zymc)?"":" and zydm='"+zymc.trim()+"' ");
		querry.append(Base.isNull(xm)?"":" and xm='"+xm.trim()+"' ");
		querry.append(Base.isNull(xymc)?"":" and exists (select 1 from view_njxyzybj a where a.zymc=b.zydm and xymc='"+xymc+"')");
		if("xx".equals(userType) || "admin".equals(userType)){
			querry.append(" and xysh='通过' ");
		}
		
		String[] colList = new String[]{"id","xm","zydm","xh","sysheng","yzbm","lxfs"};
		String  sql = "select * from xmlg_bysdalxsqb b " + querry;
		result = dao.rsToVator(sql, new String[]{},colList);
		return result;
	}
	public String dao_xmlgdel(String pks)throws Exception {

		StringBuffer sb = new StringBuffer();
		String[] keys = pks.split("!!#!!");
		String[] pksql = new String[] {};
		String sql = "";
		for (int i = 0; i < keys.length; i++) {
			String pk = DealString.toGBK(keys[i]);// 得到主键
			sql = "delete from xmlg_bysdalxsqb where id = '" + pk + "'";
			// 把主键组合成sql语句
			sb.append(sql);
			sb.append("!!#!!");
		}
		// sql语句拆分成数组
		pksql = sb.toString().split("!!#!!");
		int[] judge = dao.runBatch(pksql);
		String whichpk = "";
		// 检查哪一条删除失败
		for (int i = 0; i < judge.length; i++) {
			if (judge[i] < 0) {
				whichpk = whichpk + " 第" + String.valueOf(i) + "条数据删除失败;\n";
			}
		}
		return whichpk;
	}
	public HashMap<String, String> dao_xmlgupdate(JhzyModel model) throws Exception {
		HashMap<String, String> map = new HashMap<String, String>();
		String[] outputValue = {"sqly6","xm","zydm","xh","sysheng","syshi","syxian","yzbm",
				"jtdz","lxfs","sqly","lxqx","xyyj","xxyj","sqly1","sqly2","sqly3",
				"sqly4","sqly5","id","brqm","xysh","xxsh"};
		String sql = "select * from xmlg_bysdalxsqb where id=?";
		map = dao.getMap(sql, new String[]{model.getId()}, outputValue);
		return map;
	}
	public HashMap<String, String> dao_getXsInfo(String xh){
		DAO dao      = DAO.getInstance();
		String[] colList = dao.getColumnName("select * from view_xsxxb");
		for (int i = 0; i < colList.length; i++) {
			colList[i] = colList[i].toLowerCase();
		}
		HashMap<String, String> map = dao.getMap("select * from view_xsxxb where xh=?", new String[]{xh}, colList);
		map.put("zydm", map.get("zymc"));
		return map;
	}
	public boolean isexistsInfo(String xh){
		DAO dao      = DAO.getInstance();
		boolean isexists = false;
		HashMap<String, String> map = new HashMap<String, String>();
		map = dao.getMap("select id from xmlg_bysdalxsqb where xh=?", new String[]{xh}, new String[]{"id"});
		if (map != null && map.size()>0) {
			isexists = true;
		}
		return isexists;
	}
	public boolean dao_xmlgshAdd(JhzyModel model,String userType) throws Exception {
		DAO dao = DAO.getInstance();
		boolean result = false;
		String[] insertList = null;
		if("xy".equals(userType)){
			insertList = new String[]{"xyyj","xysh"};
		}else {
			insertList = new String[]{"xxyj","xxsh"};
		}
		
		String tableName = "xmlg_bysdalxsqb";
		String sql = "";
		String pk = model.getId();
		sql = XljkUtil.updateSqlForModel(insertList, model, tableName, "id", pk);
		result = dao.insert(sql, new String[]{});
		return result;
	}
}
