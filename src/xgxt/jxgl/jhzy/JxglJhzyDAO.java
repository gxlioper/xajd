package xgxt.jxgl.jhzy;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.action.Base; 
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.utils.String.StringUtils;


public class JxglJhzyDAO {
	DAO dao = DAO.getInstance();// 数据操作通用工具类
	
	// 得到最大编制名称
	public HashMap<String, String> getMaxJz() {

		String sql = "select jzdm,jzmc from (select jzdm,jzmc from jxjzdj order by jzdm) where rownum = '1'";
		return dao
				.getMap(sql, new String[] {}, new String[] { "jzdm", "jzmc" });
	}

	/**
	 * @describe 得到已建制列表List
	 * @author luo
	 */
	public List<HashMap<String, String>> getLdList(String nj) {

		String maxJz = getMaxJz().get("jzdm");
		StringBuffer sql = new StringBuffer();
		sql.append("select '' bzdm, '---请选择---' bzmc, '' bzdj from dual");
		sql.append(" union select bzdm, (select getJxglbzss(bzdm, nj) sT from dual)");
		sql.append("|| bzmc bzmc, bzdj from jxbzdmb where bzdj = ?");
		sql.append(Base.isNull(nj)?"":" and nj ='"+nj+"'");
		sql.append(" order by bzdj nulls first");

		return dao.getList(sql.toString(), new String[] { maxJz},
				new String[] { "bzdm", "bzmc" });
	}

	/**
	 * @describe 获得职务列表
	 * @author luo
	 */
	public List<HashMap<String, String>> getLdZwList() {

		StringBuffer sql = new StringBuffer();
		sql.append("select zwdm,zwmc from jhzy_zwb");

		return dao.getList(sql.toString(), new String[] {}, new String[] {
				"zwdm", "zwmc" });
	}
	
	/**
	 * @describe 获得年级列表
	 * @author luo
	 */
	public List<HashMap<String, String>> getNjList() {

		StringBuffer sql = new StringBuffer();
		sql.append("select '' njdm, '---请选择---' njmc from dual union");
		sql.append(" select distinct (nj) njdm, nj njmc from view_xsjbxx order by njdm nulls first");

		return dao.getList(sql.toString(), new String[] {}, new String[] {
				"njdm", "njmc" });
	}
	
	/**
	 * 组织领导查询
	 */
	public ArrayList<String[]> getZzldList(ZzldModel model) {

		String nj = model.getNj();
		String fzld = model.getLddm();
		String zzmc = DealString.toGBK(model.getZzmc());
		zzmc = Base.isNull(zzmc) ? "%" : "%" + zzmc + "%";

		StringBuffer querry = new StringBuffer(" where 1=1 ");

		querry.append(Base.isNull(nj) ? "" : " and nj='" + nj + "' ");
		querry.append(Base.isNull(fzld) ? "" : " and fzld='" + fzld + "' ");
		querry.append(" and zzmc like ?");

		StringBuffer sql = new StringBuffer();
		sql.append("select * from (select a.fzld||a.zzmc pk,b.nj, b.bzmc,a.fzld, a.zzmc,");
		sql.append(" count(a.id) num from jhzy_zzld a, jxbzdmb b where a.fzld = b.bzdm");
		sql.append(" group by b.nj, b.bzmc, a.zzmc,a.fzld)");
		sql.append(querry.toString());

		String[] inputValue = { zzmc };
		String[] outputValue = { "pk", "nj", "bzmc", "zzmc", "num" };
		ArrayList<String[]> rs = dao.rsToVator(sql.toString(), inputValue,
				outputValue);
		return rs;
	}
	
	/**
	 * 获得组织领导详细信息
	 */
	public HashMap<String, String> getZzld(String pk) {

		StringBuffer sql = new StringBuffer();
		sql.append("select * from (select a.fzld||a.zzmc pk,b.nj, b.bzmc,a.fzld lddm, a.zzmc,");
		sql.append(" count(a.id) num from jhzy_zzld a, jxbzdmb b where a.fzld = b.bzdm");
		sql.append(" group by b.nj, b.bzmc, a.zzmc,a.fzld) where pk=?");

		String[] inputValue = { pk };
		String[] outputValue = { "pk", "nj", "bzmc", "zzmc","lddm" };
		return dao.getMap(sql.toString(), inputValue, outputValue);
	}
	
	/**
	 * @describe 组织领导增加
	 * 
	 * @throws SQLException
	 */
	public boolean addZzld(ZzldModel model, HttpServletRequest request)
			throws SQLException {
		boolean flg = false;

		String[] sqlList = new String[model.getCyxm().length + 1];
		StringBuffer sql = null;
		try {
			StringBuffer buff = new StringBuffer();
			buff.append(DealString.toGBK(model.getLddm()+model.getZzmc()));
			StandardOperation.delete("jhzy_zzld", "fzld||zzmc", buff.toString(),
					request);
		} catch (Exception e1) {

			e1.printStackTrace();
		}
		if (model.getCyxm() != null && model.getCyxm().length > 0) {
			for (int i = 0; i < model.getCyxm().length; i++) {
				sql = new StringBuffer();
				sql.append("insert into jhzy_zzld (cyxm,cyxb,zw,fzld,zzmc,lxdh) values('");
				sql.append(DealString.toGBK(model.getCyxm()[i]));
				sql.append("','");
				sql.append(DealString.toGBK(model.getCyxb()[i]));
				sql.append("','");
				sql.append(DealString.toGBK(model.getZw()[i]));
				sql.append("','");
				sql.append(DealString.toGBK(model.getLddm()));
				sql.append("','");
				sql.append(DealString.toGBK(model.getZzmc()));
				sql.append("','");
				sql.append(DealString.toGBK(model.getLxdh()[i]));
				sql.append("')");
				sqlList[i] = sql.toString();
			}
		}
		
		int[] res = dao.runBatch(sqlList);
		for (int i = 0; i < res.length; i++) {
			flg = (res[i] == Statement.EXECUTE_FAILED) ? false : true;
			if (!flg)
				break;
		}

		return flg;
	}
	
	/**
	 * @throws Exception
	 * @describe 组织领导删除
	 */
	public boolean delZzld(String pk, HttpServletRequest request)
			throws Exception {
		boolean flg = StandardOperation.delete("jhzy_zzld", "fzld||zzmc", pk,
				request);
		return flg;
	}
	
	/**
	 * @describe 获得组织领导信息
	 * @author luo
	 */
	public List<HashMap<String, String>> getZzlxXxList(String pk) {

		String sql = "select cyxm,cyxb,zw,lxdh from jhzy_zzld where fzld||zzmc = ?";

		return dao.getList(sql, new String[] { pk }, new String[] { "cyxm",
				"cyxb", "zw", "lxdh" });
	}
	
	/**
	 * @describe 获得需打印组织领导
	 * @author luo
	 */
	public List<HashMap<String, String>> getPrintZzldList(String nj,String bzdm,String zzmc) {

		StringBuffer sql = new StringBuffer();
		sql.append("select * from (select * from (select b.nj, b.bzmc,b.bzdm, a.zzmc,");
		sql.append(" c.zwmc, a.cyxm, a.cyxb, a.lxdh from jhzy_zzld a, jxbzdmb b, jhzy_zwb c");
		sql.append(" where a.fzld = b.bzdm and c.zwdm = a.zw) order by nj, zzmc)");
		sql.append(" where 1=1");
		sql.append(Base.isNull(nj)?"":" and nj='"+nj+"'");
		sql.append(Base.isNull(bzdm)?"":" and bzdm='"+bzdm+"'");
		sql.append(Base.isNull(zzmc)?"":" and zzmc='"+zzmc+"'");
		sql.append("  order by bzmc,zzmc");

		List<HashMap<String, String>> list = dao.getList(sql.toString(), new String[] {},
				new String[] { "nj", "bzdm","bzmc" ,"zzmc","zwmc","cyxm","cyxb","lxdh"});
		return list;
	}
	
	/**
	 * 获取相关信息
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
	/**
	 * 军训时间范围设置保存
	 * @throws Exception 
	 */
	public boolean jxztrcszSave(JsSjSzFwModel model) throws Exception{
		DAO  dao = DAO.getInstance();
		String xn = model.getXn();
		String xq = model.getXq();
		String nd = model.getNd();
		String ksrq = model.getKsrq();
		String jsrq = model.getJsrq();
		String pkValue = xn+xq+nd;
		String flag = dao.returntag("select * from jxgl_ztsjfwb where xn||xq||nd=?",new String[]{pkValue});
		if("empty".equalsIgnoreCase(flag)){
			return dao.runUpdate(" insert into jxgl_ztsjfwb(xn,xq,nd,ksrq,jsrq)values(?,?,?,?,?) ",new String[]{xn,xq,nd,ksrq,jsrq});
		}else{
			return dao.runUpdate(" update jxgl_ztsjfwb set ksrq=?,jsrq=?  ",new String[]{ksrq,jsrq});
		}
	}
	/**
	 * 获取学期名称
	 * @param xqdm
	 * @return
	 * @throws SQLException 
	 */
	public String  getXqmc(String xqdm) throws SQLException{
		String sql = "select xqmc from xqdzb where xqdm=?";
		return  dao.getOneRs(sql, new String[]{xqdm}, "xqmc");
	}
	/**
	 * 获取学期名称
	 * @param xqdm
	 * @return
	 * @throws SQLException 
	 */
	public String  getXymc(String xydm) throws SQLException{
		String sql = "select bmmc from zxbz_xxbmdm where bmdm=?";
		return  dao.getOneRs(sql, new String[]{xydm}, "bmmc");
	}
	/**
	 * 整体安排日程信息保存
	 */
	public boolean jxrcZtAddSave(ZtApModel model, HttpServletRequest request)
			throws SQLException {
		boolean flg = false;
		dao = DAO.getInstance();
		String[] sqlList = new String[model.getNr().length + 1];
		StringBuffer sql = null;
		try {
			StringBuffer buff = new StringBuffer();
			buff.append(model.getXn()).append(
					model.getXq()).append(model.getNd());
			StandardOperation.delete("JXGL_ZTRCB", "xn||xq||nd", buff.toString(),
					request);
		} catch (Exception e1) {
			// TODO 自动生成 catch 块
			e1.printStackTrace();
		}
		for (int i = 0; i < model.getNr().length; i++) {
			sql = new StringBuffer();
			sql.append("insert into JXGL_ZTRCB(XN,XQ,ND,RQ,KSSJ,JSSJ,NR,DD,ZZRY)values('");
			sql.append(model.getXn());
			sql.append("','");
			sql.append(model.getXq());
			sql.append("','");
			sql.append(model.getNd());
			sql.append("','");
			sql.append(model.getRq()[i]);
			sql.append("','");
			sql.append((Base.isNull(model.getKssjH()[i])?"00":model.getKssjH()[i])+":"+(Base.isNull(model.getKssjM()[i])?"00":model.getKssjM()[i])+":"+(Base.isNull(model.getKssjS()[i])?"00":model.getKssjS()[i]));
			sql.append("','");
			sql.append((Base.isNull(model.getJssjH()[i])?"00":model.getJssjH()[i])+":"+(Base.isNull(model.getJssjM()[i])?"00":model.getJssjM()[i])+":"+(Base.isNull(model.getJssjS()[i])?"00":model.getJssjS()[i]));
			sql.append("','");
			sql.append(DealString.toGBK(model.getNr()[i]).replace("'","‘"));
			sql.append("','");
			sql.append(DealString.toGBK(model.getDd()[i]).replace("'","‘"));
			sql.append("','");
			sql.append(DealString.toGBK(model.getZzry()[i]).replace("'","‘"));
			sql.append("')");
			if (StringUtils.isNull(model.getNr()[i])) {// 为空则不记录
				sql = new StringBuffer("");
			}
			sqlList[i] = sql.toString();
		}		
		int[] res = dao.runBatch(sqlList);
		for (int i = 0; i < res.length; i++) {
			flg = (res[i] == Statement.EXECUTE_FAILED) ? false : true;
			if (!flg)
				break;
		}
		return flg;
	}
	/**
	 * 获得整体安排日程信息
	 * 
	 * @throws SQLException
	 */
	public List<HashMap<String, String>> dao_getJxrcZt(String pk) throws SQLException {
		String sql = "select xn,xq,nd,rq,nr,dd,zzry,substr(kssj,1,2)kssjH,substr(kssj,4,2)kssjM,substr(kssj,7,2)kssjS,substr(jssj,1,2)jssjH,substr(jssj,4,2)jssjM,substr(jssj,7,2)jssjS  from jxgl_ztrcb where xn||xq||nd=? order by rq,kssj asc";
		return dao.getList(sql, new String[] { pk }, new String[] { "rq","nr","dd","zzry","kssjH","kssjM","kssjS","jssjH","jssjM","jssjS"});
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
	 * 整体安排日程查询
	 */
	public ArrayList<String[]>JxrcZtSearch(ZtApModel model){
		String xn = model.getXn();
		String xq = model.getXq();
		String nd = model.getNd();
		StringBuffer querry = new StringBuffer(" where 1=1 ");
		querry.append(Base.isNull(xn)?"":" and xn='"+xn+"'");
		querry.append(Base.isNull(xq)?"":" and xq='"+xq+"'");
		querry.append(Base.isNull(nd)?"":" and nd='"+nd+"' ");
		StringBuffer sql = new StringBuffer();
		sql.append("select xn||xq||nd id,xn,(select xqmc from xqdzb b where b.xqdm=a.xq )xq,nd,sjfw from (select distinct a.xn,a.xq,a.nd,b.ksrq||'--'||b.jsrq sjfw from jxgl_ztrcb a left join jxgl_ztsjfwb b on a.xn=b.xn and a.xq=b.xq and a.nd=b.nd)a");
		sql.append(querry.toString()).append(" order by xn,xq");
		String[] outputValue = {"id","xn","xq","nd","sjfw"};
		ArrayList<String[]> rs = dao.rsToVator(sql.toString(), new String[]{}, outputValue);
		return rs;
	}
	
	/**
	 * 整体安排日程信息删除
	 */
	public boolean dao_jxrcZtDel(String delPk) throws Exception{
		DAO dao = DAO.getInstance();
		String[] pkValueA = delPk.split("!!");
		String[] delPkSql  = new String[pkValueA.length];
		for(int i=0;i<pkValueA.length;i++){
			delPkSql[i]="delete from jxgl_ztrcb where xn||xq||nd='"+pkValueA[i]+"' ";									
		}
	    boolean doFlag = false;     
        int[] array = dao.runBatch(delPkSql);
        doFlag = dao.checkBatch(array);           
		return doFlag;
	}
	/**
	 * 具体安排日程信息保存
	 */
	public boolean jxrcJtAddSave(JtApModel model, HttpServletRequest request)
			throws SQLException {
		boolean flg = false;
		dao = DAO.getInstance();
		String[] sqlList = new String[model.getNr().length + 1];
		StringBuffer sql = null;
		try {
			StringBuffer buff = new StringBuffer();
			buff.append(model.getXydm()+model.getXn()).append(
					model.getXq()).append(model.getNd());
			StandardOperation.delete("JXGL_JTRCB", "xydm||xn||xq||nd", buff.toString(),
					request);
		} catch (Exception e1) {
			// TODO 自动生成 catch 块
			e1.printStackTrace();
		}
		for (int i = 0; i < model.getNr().length; i++) {
			sql = new StringBuffer();
			sql.append("insert into JXGL_JTRCB(XYDM,XN,XQ,ND,RQ,KSSJ,JSSJ,NR,DD,ZZRY)values('");
			sql.append(model.getXydm());
			sql.append("','");
			sql.append(model.getXn());
			sql.append("','");
			sql.append(model.getXq());
			sql.append("','");
			sql.append(model.getNd());
			sql.append("','");
			sql.append(model.getRq()[i]);
			sql.append("','");
			sql.append((Base.isNull(model.getKssjH()[i])?"00":model.getKssjH()[i])+":"+(Base.isNull(model.getKssjM()[i])?"00":model.getKssjM()[i])+":"+(Base.isNull(model.getKssjS()[i])?"00":model.getKssjS()[i]));
			sql.append("','");
			sql.append((Base.isNull(model.getJssjH()[i])?"00":model.getJssjH()[i])+":"+(Base.isNull(model.getJssjM()[i])?"00":model.getJssjM()[i])+":"+(Base.isNull(model.getJssjS()[i])?"00":model.getJssjS()[i]));
			sql.append("','");
			sql.append(DealString.toGBK(model.getNr()[i]).replace("'","‘"));
			sql.append("','");
			sql.append(DealString.toGBK(model.getDd()[i]).replace("'","‘"));
			sql.append("','");
			sql.append(DealString.toGBK(model.getZzry()[i]).replace("'","‘"));
			sql.append("')");
			if (StringUtils.isNull(model.getNr()[i])) {// 为空则不记录
				sql = new StringBuffer("");
			}
			sqlList[i] = sql.toString();
		}		
		int[] res = dao.runBatch(sqlList);
		for (int i = 0; i < res.length; i++) {
			flg = (res[i] == Statement.EXECUTE_FAILED) ? false : true;
			if (!flg)
				break;
		}
		return flg;
	}
	/**
	 * 获得具体安排日程信息
	 * 
	 * @throws SQLException
	 */
	public List<HashMap<String, String>> dao_getJxrcJt(String pk) throws SQLException {
		String sql = "select xn,xq,nd,rq,nr,dd,zzry,substr(kssj,1,2)kssjH,substr(kssj,4,2)kssjM,substr(kssj,7,2)kssjS,substr(jssj,1,2)jssjH,substr(jssj,4,2)jssjM,substr(jssj,7,2)jssjS  from jxgl_jtrcb where xydm||xn||xq||nd=? order by rq,kssj asc";
		return dao.getList(sql, new String[] { pk }, new String[] { "rq","nr","dd","zzry","kssjH","kssjM","kssjS","jssjH","jssjM","jssjS"});
	}
	/**
	 * 整体安排日程查询
	 */
	public ArrayList<String[]>JxrcJtSearch(JtApModel model){
		String xn = model.getXn();
		String xq = model.getXq();
		String nd = model.getNd();
		String xydm = model.getXydm();
		StringBuffer querry = new StringBuffer(" where 1=1 ");
		querry.append(Base.isNull(xn)?"":" and xn='"+xn+"'");
		querry.append(Base.isNull(xq)?"":" and xq='"+xq+"'");
		querry.append(Base.isNull(nd)?"":" and nd='"+nd+"' ");
		querry.append(Base.isNull(xydm)?"":" and xydm='"+xydm+"' ");
		StringBuffer sql = new StringBuffer();
		sql.append("select xydm||xn||xq||nd id,(select bmmc from  zxbz_xxbmdm b where b.bmdm=a.xydm)xymc,xn,(select xqmc from xqdzb b where b.xqdm=a.xq )xq,nd,sjfw from (select distinct a.xydm,a.xn,a.xq,a.nd,b.ksrq||'--'||b.jsrq sjfw from jxgl_jtrcb a left join jxgl_ztsjfwb b on a.xn=b.xn and a.xq=b.xq and a.nd=b.nd)a");
		sql.append(querry.toString()).append(" order by xymc,xn,xq");
		String[] outputValue = {"id","xymc","xn","xq","nd","sjfw"};
		ArrayList<String[]> rs = dao.rsToVator(sql.toString(), new String[]{}, outputValue);
		return rs;
	}
	/**
	 * 具体安排中调用整体安排日程信息
	 */
	public List<HashMap<String, String>> dao_getZtApInfo(String pk) {
		String sql = " select rownum r,a.* from (select xn,(select xqmc from xqdzb b where b.xqdm=a.xq)xq,nd,nr,dd,zzry,rq,kssj,jssj  from jxgl_ztrcb  a where xn||xq||nd=? order by rq,kssj asc)a";
		return dao.getList(sql, new String[] { pk }, new String[] {"r","xn","xq","nd","nr","dd","zzry","rq","kssj","jssj"});
	}
	/**
	 * 具体安排日程信息删除
	 */
	public boolean dao_jxrcJtDel(String delPk) throws Exception{
		DAO dao = DAO.getInstance();
		String[] pkValueA = delPk.split("!!");
		String[] delPkSql  = new String[pkValueA.length];
		for(int i=0;i<pkValueA.length;i++){
			delPkSql[i]="delete from jxgl_jtrcb where xydm||xn||xq||nd='"+pkValueA[i]+"' ";									
		}
	    boolean doFlag = false;     
        int[] array = dao.runBatch(delPkSql);
        doFlag = dao.checkBatch(array);           
		return doFlag;
	}
	/**
	 * 校日程表
	 */
	public List<HashMap<String,String>>xxrcbPrint(String xn,String xq,String nd){
		String sql ="select (select count(rq) from  jxgl_ztrcb b where a.xn=b.xn and a.xq=b.xq and a.nd=b.nd and a.rq=b.rq group by xn,nd,xq,rq)cout, rq,sj,nr,dd,zzry from ";
		sql +="(select xn,xq,nd,rq,kssj||'--'||jssj sj, nr,dd,zzry  from jxgl_ztrcb  where xn||xq||nd=?  order by rq,kssj asc)a";
		return dao.getList(sql, new String[] { xn+xq+nd }, new String[] {"cout","rq","sj","nr","dd","zzry"});

	}
	/**
	 * 院系日程表
	 */
	public List<HashMap<String,String>>yxcbPrint(String xydm,String xn,String xq,String nd){
		String sql ="select (select count(rq) from  jxgl_jtrcb b where a.xydm=b.xydm and a.xn=b.xn and a.xq=b.xq and a.nd=b.nd and a.rq=b.rq group by xn,nd,xq,rq)cout, rq,sj,nr,dd,zzry from ";
		sql +="(select xydm,xn,xq,nd,rq,kssj||'--'||jssj sj, nr,dd,zzry  from jxgl_jtrcb  where xydm||xn||xq||nd=?  order by rq,kssj asc)a";
		return dao.getList(sql, new String[] { xydm+xn+xq+nd }, new String[] {"cout","rq","sj","nr","dd","zzry"});

	}
}
