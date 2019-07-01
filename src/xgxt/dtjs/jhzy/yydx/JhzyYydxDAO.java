package xgxt.dtjs.jhzy.yydx;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.dtjs.jhzy.DtjsJhzyDAO;
import xgxt.szdw.utils.ModelToStrings;
import xgxt.utils.String.StringUtils;

import xgxt.base.DealString;

public class JhzyYydxDAO {
	
	DAO dao = DAO.getInstance();
	DtjsJhzyDAO myDAO = new DtjsJhzyDAO();
	
	public HashMap<String, String> getXsSqxx(String xh) {
		String tableName = "view_xsjbxx";
		String [] colList =new String []{"xh","xm","bjdm","zydm","nj","xydm","xb","bjmc","zymc","xymc"};
		return myDAO.commonQueryOne(tableName, colList, "xh", xh);
	}
	
	public String getDysqsj(String xh) {
		return dao.getOneRs("select max(djsqsj) djsqsj from rdsqb where xh = ?", new String[]{xh}, "djsqsj");
	}

	public boolean yydxUpdate(String pk, JhzyYydxModel myModel, HttpServletRequest request) throws Exception {
		String tableName = "jhzy_yydxsq";
		String pkComment = "xh||xn||xq||qj";
		boolean updata = true;
		if (pk.equalsIgnoreCase("")) {
			String[] colList = new String[] { "qj","sqly","xh","xn","xq"};
			String[] inputList = ModelToStrings.modelToStrings(colList, myModel);
			updata = StandardOperation.insert(tableName, colList, inputList,
					request);
		} else {
			String[] colList = new String[] { "cj","lrrq","sqly","xxsh","xysh"};
			String[] inputList = ModelToStrings.modelToStrings(colList, myModel);
			updata = StandardOperation.update(tableName, colList, inputList,
					pkComment, pk, request);
		}
		return updata;
	}

	/**
	 * 获取审核列表
	 */
	public List<HashMap<String, String>> getChkList() {
		DAO dao = DAO.getInstance();	
		return dao.getChkList(3);
	}

	public ArrayList<String[]> getYydxList(JhzyYydxModel model, String userType) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		ArrayList<String[]> result = new ArrayList<String[]>();
		String xh = model.getXh();
		String nj = model.getNj();
		String xydm = model.getXydm();
		String zydm = model.getZydm();
		String bjdm = model.getBjdm();
		String xm   = model.getXm();
		String yesNo = model.getYesNo();
		String xn = model.getXn();
		String xq = model.getXq();
		String qj = model.getQj();

		StringBuffer querry = new StringBuffer(" where 1=1 ");
		if("xy".equalsIgnoreCase(userType)){
			querry.append(Base.isNull(yesNo)?"":" and xysh='"+yesNo+"' ");
		}else{
			querry.append(Base.isNull(yesNo)?"":" and xxsh='"+yesNo+"'");
			querry.append(" and xysh='通过' ");
		}		
		querry.append(Base.isNull(xh)?"":" and xh='"+xh.trim()+"' ");
		querry.append(Base.isNull(nj)?"":" and nj='"+nj+"' ");
		querry.append(Base.isNull(xydm)?"":" and xydm='"+xydm+"' ");
		querry.append(Base.isNull(zydm)?"":" and zydm='"+zydm+"' ");
		querry.append(Base.isNull(bjdm)?"":" and bjdm='"+bjdm+"' ");
		querry.append(Base.isNull(xm)?"":" and xm like '%"+xm.trim()+"%' ");
		querry.append(Base.isNull(qj)?"":" and qj='"+qj+"' ");
		querry.append(Base.isNull(xn)?"":" and xn='"+xn+"' ");
		querry.append(Base.isNull(xq)?"":" and xq='"+xq+"' ");
		String[] colList = new String[]{"pk","xn","xqmc","qj","xh","xm","xymc","bjmc","lrrq","xysh","xxsh"};
		result = myDAO.commonQueryNotFy("view_jhzy_yydxsq", querry.toString(), new String[]{}, colList, "");
		return result;
	}

	public List<HashMap<String, String>> getYydxTopTr() {
		String tableName = "view_jhzy_yydxsq";
		String[] colList = new String[]{"pk","xn","xqmc","qj","xh","xm","xymc","bjmc","lrrq","xysh","xxsh"};
		String[] colListCN = dao.getColumnNameCN(colList, tableName);
		List<HashMap<String, String>> topTr = dao.arrayToList(colList, colListCN);// 表头
		return topTr;
	}

	/**
	 * 获取相关信息
	 * @param table 表名
	 * @param querry 条件
	 * @return
	 */
	public HashMap<String,String> dao_getInfo(String table,String querry){
		DAO dao = DAO.getInstance();
		String[] colList =  dao.getColumnName("select * from "+table);
		for(int i=0;i<colList.length;i++){
			colList[i]=colList[i].toLowerCase();
		}
		String sql = "select * from "+table;
		return dao.getMap(sql+querry.toString(),new String[]{},colList);
	}

	public boolean yydxCk(String pkVStr, String userType, String check) throws SQLException {
		DAO dao = DAO.getInstance();	
		String pk = "xh||xn||xq||qj";
		String shType = "";
		shType = ("xy".equalsIgnoreCase(userType))?"xysh":"xxsh";
		String[] pkValueA = pkVStr.split("!!");		
		String[] updPkSql  = new String[pkValueA.length];		
		for (int i = 0; i < pkValueA.length; i++) {
			updPkSql[i] = Base.isNull(pkValueA[i])?"":"update jhzy_yydxsq set "+shType+"='"+check+"'  where "+pk+"= '"+pkValueA[i]+"'";							
		}              
		boolean doFlag = false;
		int[] array = dao.runBatch(updPkSql);
		doFlag = dao.checkBatch(array);   
		return doFlag;
	}

	public List<HashMap<String, String>> getYydxcjTopTr(String tableName) {
		String[] colList = new String[]{"pk","xn","xqmc","qj","xh","xm","xymc","bjmc","lrrq","cj"};
		String[] colListCN = dao.getColumnNameCN(colList, tableName);
		List<HashMap<String, String>> topTr = dao.arrayToList(colList,
				colListCN);// 表头
		return topTr;
	}

	public ArrayList<String[]> getYydxcjList(JhzyYydxModel model, String tableName) {
		String xh = model.getXh();
		String nj = model.getNj();
		String xydm = model.getXydm();
		String zydm = model.getZydm();
		String bjdm = model.getBjdm();
		String xm   = DealString.toGBK(model.getXm());
		String xn = model.getXn();
		String xq = model.getXq();
		String qj = model.getQj();

		StringBuffer querry = new StringBuffer(" where 1=1 ");
		querry.append(Base.isNull(xh)?"":" and xh='"+xh.trim()+"' ");
		querry.append(Base.isNull(nj)?"":" and nj='"+nj+"' ");
		querry.append(Base.isNull(xydm)?"":" and xydm='"+xydm+"' ");
		querry.append(Base.isNull(zydm)?"":" and zydm='"+zydm+"' ");
		querry.append(Base.isNull(bjdm)?"":" and bjdm='"+bjdm+"' ");
		querry.append(Base.isNull(xm)?"":" and xm like '%"+xm.trim()+"%' ");
		querry.append(Base.isNull(qj)?"":" and qj='"+qj+"' ");
		querry.append(Base.isNull(xn)?"":" and xn='"+xn+"' ");
		querry.append(Base.isNull(xq)?"":" and xq='"+xq+"' ");
		querry.append(Base.isNull(xq)?"":" and xxsh='通过' ");
		String[] colList = new String[]{"pk","xn","xqmc","qj","xh","xm","xymc","bjmc","lrrq","cj"};
		return myDAO.commonQueryNotFy("view_jhzy_yydxsq", querry.toString(), new String[]{}, colList, "");
	}

	public boolean yydxcjSave(JhzyYydxModel model) throws SQLException {
		String[] pks = model.getPks();
		String[] cjs = model.getCjs();
		String[] updPkSql  = new String[pks.length];		
		for (int i = 0; i < pks.length; i++) {
			updPkSql[i] = Base.isNull(pks[i])?"":"update jhzy_yydxsq set cj='"+cjs[i]+"'  where xh||xn||xq||qj= '"+pks[i]+"'";							
		}              
		boolean doFlag = false;
		int[] array = dao.runBatch(updPkSql);
		doFlag = dao.checkBatch(array);   
		return doFlag;
	}

	public ArrayList<String[]> getYydxCxList(JhzyYydxModel model, String tableName) {
		String xh = model.getXh();
		String nj = model.getNj();
		String xydm = model.getXydm();
		String zydm = model.getZydm();
		String bjdm = model.getBjdm();
		String xm   = DealString.toGBK(model.getXm());
		String xn = model.getXn();
		String xq = model.getXq();
		String qj = model.getQj();
		String xysh = DealString.toGBK(model.getXysh());
		String xxsh = DealString.toGBK(model.getXxsh());
		StringBuffer querry = new StringBuffer(" where 1=1 ");
		querry.append(Base.isNull(xh)?"":" and xh='"+xh.trim()+"' ");
		querry.append(Base.isNull(nj)?"":" and nj='"+nj+"' ");
		querry.append(Base.isNull(xydm)?"":" and xydm='"+xydm+"' ");
		querry.append(Base.isNull(zydm)?"":" and zydm='"+zydm+"' ");
		querry.append(Base.isNull(bjdm)?"":" and bjdm='"+bjdm+"' ");
		querry.append(Base.isNull(xm)?"":" and xm like '%"+xm.trim()+"%' ");
		querry.append(Base.isNull(qj)?"":" and qj='"+qj+"' ");
		querry.append(Base.isNull(xn)?"":" and xn='"+xn+"' ");
		querry.append(Base.isNull(xq)?"":" and xq='"+xq+"' ");
		querry.append(Base.isNull(xysh)?"":" and xysh='"+xysh+"' ");
		querry.append(Base.isNull(xxsh)?"":" and xxsh='"+xxsh+"' ");
		String[] colList = new String[]{"pk","xn","xqmc","qj","xh","xm","xymc","bjmc","lrrq","xysh","xxsh","cj"};
		return myDAO.commonQueryNotFy("view_jhzy_yydxsq", querry.toString(), new String[]{}, colList, "");
	}

	public List<HashMap<String, String>> getYydxCxTopTr(String tableName) {
		String[] colList = new String[]{"pk","xn","xqmc","qj","xh","xm","xymc","bjmc","lrrq","xysh","xxsh","cj"};
		String[] colListCN = dao.getColumnNameCN(colList, tableName);
		List<HashMap<String, String>> topTr = dao.arrayToList(colList,
				colListCN);// 表头
		return topTr;
	}

	public boolean delYydx(String pkVStr) throws SQLException {
		String pk = "xh||xn||xq||qj";
		String[] pkValueA = pkVStr.split("!!");		
		String[] updPkSql  = new String[pkValueA.length];		
		for (int i = 0; i < pkValueA.length; i++) {
			updPkSql[i] = Base.isNull(pkValueA[i])?"":"delete from jhzy_yydxsq where "+pk+"= '"+pkValueA[i]+"'";							
		}              
		boolean doFlag = false;
		int[] array = dao.runBatch(updPkSql);
		doFlag = dao.checkBatch(array);   
		return doFlag;
	}
	
	/**
	 * 日课程安排增加保存
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean dao_xkcapAdd(RckApModel model) throws Exception{
		DAO dao = DAO.getInstance();
		String xydm = model.getXydm();
		String xn = model.getXn();
		String xq = model.getXq();
		String dkjs = "第"+model.getDkjs()+"期";
		String kczy = model.getKczy();
		String kssj = model.getKssj();
		String kssjH = model.getKssjH();
		String kssjM = model.getKssjM();
		String kssjS = model.getKssjS();
		String jssj = model.getJssj();
		String jssjH = model.getJssjH();
		String jssjM = model.getJssjM();
		String jssjS = model.getJssjS();
		String jtap = model.getJtap();
		String bz   = model.getBz();
		String scdz = model.getScdz();
		kssj= kssj+" "+(Base.isNull(kssjH)?"00":kssjH)+":"+(Base.isNull(kssjM)?"00":kssjM)+":"+(Base.isNull(kssjS)?"00":kssjS);
	    jssj= jssj+" "+(Base.isNull(jssjH)?"00":jssjH)+":"+(Base.isNull(jssjM)?"00":jssjM)+":"+(Base.isNull(jssjS)?"00":jssjS);
		String sql = "insert into YYDXRCKB(XYDM,XN,XQ,DKJS,KCZY,KSSJ,JSSJ,JTAP,BZ,scdz)values(?,?,?,?,?,?,?,?,?,?)";
		return  dao.runUpdate(sql,new String[]{xydm,xn,xq,dkjs,kczy,kssj,jssj,jtap,bz,scdz});	
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
	 * 日课程安排查询
	 */
	public ArrayList<String[]> dao_kcapSearch(RckApModel model){
		DAO  dao = DAO.getInstance();
		String xydm = model.getXydm();
		String xn   = model.getXn();
		String xq   = model.getXq();
		String kssj = model.getKssj();
		String jssj = model.getJssj();
		StringBuffer querry = new StringBuffer(" where 1=1 ");
		querry.append(Base.isNull(xydm)?"":" and xydm='"+xydm+"' ");
		querry.append(Base.isNull(xn)?"":" and xn='"+xn+"' ");
		querry.append(Base.isNull(xq)?"":" and xq='"+xq+"' ");
		if(!Base.isNull(kssj)){
			querry.append(" and kssj like '"+kssj+"%' ");
		}else if(!Base.isNull(jssj)){
			querry.append(" and jssj like '"+jssj+"%' ");
		}else if(!Base.isNull(kssj)&&!Base.isNull(jssj)){
			querry.append(" and jssj like '"+jssj+"%' and jssj like '"+jssj+"%' ");
		}
		querry.append(" order by kssj desc");
//		String[] colListCN = new String[] { "主键","学年", "学期", "院系", "课程摘要","党课届次","开始时间","结束时间"};
		String[] colListEN = new String[] { "id","xn", "xqmc", "xymc", "kczy","dkjs","kssj","jssj","scdz"};
		String sql="select id,xn,(select xqmc from xqdzb b where b.xqdm=a.xq)xqmc,(select bmmc from zxbz_xxbmdm b where b.bmdm=a.xydm )xymc,kczy,dkjs,kssj,jssj,scdz from YYDXRCKB a ";
		return dao.rsToVator(sql+querry.toString(), new String[]{},colListEN);		 	
	}
	/**
	 * 日课程安排信息删除
	 * @throws SQLException 
	 */
	public boolean dao_kcapDel(String pks) throws SQLException{
		DAO dao = DAO.getInstance();
		StringBuffer sb = new StringBuffer();
		String[] keys = pks.split("!!");
		String[] pksql = new String[] {};
		String sql = "";
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < keys.length; i++) {
			String pk = DealString.toGBK(keys[i]);// 得到主键
			sql = "delete from YYDXRCKB where id = '" + pk + "'";
			// 把主键组合成sql语句
			sb.append(sql).append("!!");
			sql = "select scdz from YYDXRCKB where id = ?";
			String scdz = dao.getOneRs(sql, new String[]{pk}, "scdz");
			list.add(scdz);
		}
		// sql语句拆分成数组
		pksql = sb.toString().split("!!");	
		int[] array  = dao.runBatch(pksql);		
		boolean doFlag = dao.checkBatch(array);
		
		if (doFlag) {
			if (list != null && list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					if (!Base.isNull(list.get(i))) {
						File f = new File(list.get(i));
						if (f.exists()) {
							f.delete();
						}
					}
				}
			}
		}
		return doFlag;
	}
	/**
	 * 日课程安排增加修改
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean dao_xkcapUpdate(RckApModel model,String pkValue) throws Exception{
		DAO dao = DAO.getInstance();
//		String xydm = model.getXydm();
		String xn = model.getXn();
		String xq = model.getXq();
		String dkjs = "第"+model.getDkjs()+"期";
		String kczy = model.getKczy();
		String kssj = model.getKssj();
		String kssjH = model.getKssjH();
		String kssjM = model.getKssjM();
		String kssjS = model.getKssjS();
		String jssj = model.getJssj();
		String jssjH = model.getJssjH();
		String jssjM = model.getJssjM();
		String jssjS = model.getJssjS();
		String jtap = model.getJtap();
		String bz   = model.getBz();
		kssj= kssj+" "+kssjH+":"+kssjM+":"+kssjS;
	    jssj= jssj+" "+jssjH+":"+jssjM+":"+jssjS;
		String sql = "update YYDXRCKB set XN=?,XQ=?,DKJS=?,KCZY=?,KSSJ=?,JSSJ=?,JTAP=?,BZ=? where id=?";
		return  dao.runUpdate(sql,new String[]{xn,xq,dkjs,kczy,kssj,jssj,jtap,bz,pkValue});	
	}
	
	public boolean plzhSave(JhzyYydxModel model, String realTable, String zhsj) throws SQLException {
		//批量转换政治面貌 
		String [] pks = model.getPks();
		String xn = Base.currXn;
		String xq = Base.currXq;
		String nd = Base.currNd;
		String[] updPkSql  = new String[pks.length];
		zhsj = zhsj.replace("-", "");
		if(realTable.equalsIgnoreCase("rdjjfzxxb")){
		for (int i = 0; i < pks.length; i++) {
			String[] tmp = dao.getOneRs("select xh,xsccdm from rdjjfzxxb where xn||xq||xh = ?", new String []{pks[i]}, new String []{"xh","xsccdm"});
			String xh = tmp[0];
			String xsccdm = tmp[1];
			String sql = "select count(*) num from ybdyxxb where xh =?";
			String num = dao.getOneRs(sql, new String[]{xh},"num");
			if ("0".equalsIgnoreCase(num)) {
				sql = "insert into ybdyxxb (xn,xq,xh,nd,kssj,xsccdm) values('"+xn+"','"+xq+"','"+xh+"','"+nd+"','"+zhsj+"','"+xsccdm+"')";
				updPkSql[i]=sql;
			} 
			
			} 
		}else{
			for (int i = 0; i < pks.length; i++) {
				String[] tmp = dao.getOneRs("select xh,xsccdm,dfjnqk,cjzzxxqk,kssj,jssj from ybdyxxb where xn||xq||xh = ?", new String []{pks[i]}, new String []{"xh","xsccdm","dfjnqk","cjzzxxqk","kssj","jssj"});
				String xh = DealString.toGBK(tmp[0]);
				String xsccdm = StringUtils.isNull(tmp[1]) ? "" : tmp[1];
				String dfjnqk = StringUtils.isNull(tmp[2]) ? "" : tmp[2];
				String cjzzxxqk = StringUtils.isNull(tmp[3]) ? "" : tmp[3];
				String ybdykssj = StringUtils.isNull(tmp[4]) ? "" : tmp[4];
				String ybdyjssj = StringUtils.isNull(tmp[5]) ? "" : tmp[5];
				String sql = "select count(*) num from dyxxb where xh =?";
				String num = dao.getOneRs(sql, new String[]{xh},"num");
				if ("0".equalsIgnoreCase(num)) {
					sql = "insert into dyxxb (xn,xq,xh,nd,zzsj,rdsj,xsccdm,dfjnqk,zzxxqk,ybdykssj,ybdyjssj) values('"+xn+"','"+xq+"','"+xh+"','"+nd+"','"+zhsj+"','"+zhsj+"','"+xsccdm+"','"+dfjnqk+"','"+cjzzxxqk+"','"+ybdykssj+"','"+ybdyjssj+"')";
					updPkSql[i]=sql;
				} 
			}  
		}
		boolean doFlag = false;
		int[] array = dao.runBatch(updPkSql);
		doFlag = dao.checkBatch(array);
		if(array!=null){
			for (int i = 0; i < pks.length; i++) {
				String sql = "delete from "+realTable+" where xn||xq||xh = '"+pks[i]+"'";
				updPkSql[i]=sql;
			} 
		
		array = dao.runBatch(updPkSql);
		doFlag = dao.checkBatch(array);   
		}
		return doFlag;
	}
}
