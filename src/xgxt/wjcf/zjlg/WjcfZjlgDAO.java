package xgxt.wjcf.zjlg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.utils.String.StringUtils;
import xgxt.utils.date.DateUtils;
import xgxt.wjcf.zjcm.WjcfZjcmDAO;

import common.Globals;

public class WjcfZjlgDAO {

	private DAO dao = DAO.getInstance();
	
	private ArrayList<String> values = new ArrayList<String>();

//	private static String[] cflbList = {"通报批评", "警告", "严重警告"};
	
	public StringBuffer getWhereSql(WjcfZjlgModel model) {
		StringBuffer whereSql = new StringBuffer();
		values = new ArrayList<String>();
		if (model != null) {
			if (!StringUtils.isNull(model.getXh())) {
				whereSql.append(" and xh like '" + "%" + DealString.toGBK(model.getXh()) + "%'");
			}
			if (!StringUtils.isNull(model.getXn())) {
				whereSql.append(" and xn = ?");
				values.add(model.getXn());
			}
			if (!StringUtils.isNull(model.getNj())) {
				whereSql.append(" and nj = ?");
				values.add(model.getNj());
			}
			if (!StringUtils.isNull(model.getXydm())) {
				whereSql.append(" and xydm = ?");
				values.add(model.getXydm());
			}
			if (!StringUtils.isNull(model.getZydm())) {
				whereSql.append(" and zydm = ?");
				values.add(model.getZydm());
			}
			if (!StringUtils.isNull(model.getBjdm())) {
				whereSql.append(" and bjdm = ?");
				values.add(model.getBjdm());
			}
			if (!StringUtils.isNull(model.getXm())) {
				whereSql.append(" and xm like '" + "%" + DealString.toGBK(model.getXm()) + "%'");
			}
			if (!StringUtils.isNull(model.getNd())) {
				whereSql.append(" and nd = ?");
				values.add(model.getNd());
			}
			if (!StringUtils.isNull(model.getCflb())) {
				whereSql.append(" and cflb = ?");
				values.add(model.getCflb());
			}
			if (!StringUtils.isNull(model.getCfyy())) {
				whereSql.append(" and cfyy = ?");
				values.add(model.getCfyy());
			}
			if (!StringUtils.isNull(model.getLxcksj())) {
				whereSql.append(" and lxcksj like '%");
				whereSql.append(model.getLxcksj());
				whereSql.append("%'");
			}
		}
		return whereSql;
	}
	
	/**
	 * 处分审核查询表头
	 * @return
	 */
	public List<HashMap<String, String>> queryWjcfsbxxTitle() {
		String[] enList = new String[]{ "pk", "dis", "xh", "xm", "bjmc",
				"xn", "nd", "cflbmc", "cfyymc", "cfwh", "cfsj", "xxsh" };
		String[] cnList = new String[]{ "pk", "dis", "学号", "姓名", "班级",
				"学年", "年度", "处分类别", "处分原因", "处分文号", "处分时间" ,"审核"};
		return dao.arrayToList(enList, cnList);
	}
	
	/**
	 * 处分审核学院查询结果记录数
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public int queryWjcfsbxxByXyNum(WjcfZjlgModel model)
			throws Exception {
		StringBuffer whereSql = getWhereSql(model);
		String[] colList = new String[] { "pk", "dis", "xh", "xm", "bjmc",
				"xn", "nd", "cflbmc", "cfyymc", "cfwh", "cfsj", "xysh" };
		String sql = "select xh||xn||nd||sbsj pk,(case when cflbmc='通报批评' or cflbmc='警告' or cflbmc='严重警告' then '' else 'disabled' end) dis,xh,xm,bjmc,xn,nd,cflbmc,cfyymc,cfwh,cfsj,xysh from view_wjcf where 1=1 ";
		return dao.rsToVator(sql + whereSql.toString(), values != null ? values
				.toArray(new String[0]) : null, colList).size();
	}
	
	/**
	 * 处分审核学院查询结果
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public ArrayList<String[]> queryWjcfsbxxByXy(WjcfZjlgModel model, WjcfZjlgActionForm form)
			throws Exception {
		StringBuffer whereSql = getWhereSql(model);
		String[] colList = new String[] { "pk", "dis", "xh", "xm", "bjmc",
				"xn", "nd", "cflbmc", "cfyymc", "cfwh", "cfsj", "xysh" };
		String sql = "select xh||xn||nd||sbsj pk,rownum r,(case when cflbmc='通报批评' or cflbmc='警告' or cflbmc='严重警告' then '' else 'disabled' end) dis,xh,xm,bjmc,xn,nd,cflbmc,cfyymc,cfwh,cfsj,xysh from view_wjcf where 1=1 ";
		return dao.rsToVator("select * from ("  + sql + whereSql.toString() + ") where r<="
				+ (form.getPages().getStart() + form
						.getPages().getPageSize()) + " and r> "
				+ form.getPages().getStart(), values != null ? values
				.toArray(new String[0]) : null, colList);
	}
	
	/**
	 * 处分审核学校查询结果记录数
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public int queryWjcfsbxxByXxNum(WjcfZjlgModel model)
			throws Exception {
		StringBuffer whereSql = getWhereSql(model);
		String[] colList = new String[] { "pk", "dis", "xh", "xm", "bjmc",
				"xn", "nd", "cflbmc", "cfyymc", "cfwh", "cfsj", "xxsh" };
		String sql = "select xh||xn||nd||sbsj pk,(case when cflbmc='通报批评' or cflbmc='警告' or cflbmc='严重警告' then 'disabled' else '' end) dis,xh,xm,bjmc,xn,nd,cflbmc,cfyymc,cfwh,cfsj,xxsh from view_wjcf where 1=1 ";
		return dao.rsToVator(sql + whereSql.toString(), values != null ? values
				.toArray(new String[0]) : null, colList).size();
	}
	
	/**
	 * 处分审核学校查询结果
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public ArrayList<String[]> queryWjcfsbxxByXx(WjcfZjlgModel model, WjcfZjlgActionForm form)
			throws Exception {
		StringBuffer whereSql = getWhereSql(model);
		String[] colList = new String[] { "pk", "dis", "xh", "xm", "bjmc",
				"xn", "nd", "cflbmc", "cfyymc", "cfwh", "cfsj", "xxsh" };
		String sql = "select xh||xn||nd||sbsj pk,rownum r,'' dis,xh,xm,bjmc,xn,nd,cflbmc,cfyymc,cfwh,cfsj,xxsh from view_wjcf where 1=1 ";
		return dao.rsToVator("select * from ("  + sql + whereSql.toString() + ") where r<="
				+ (form.getPages().getStart() + form
						.getPages().getPageSize()) + " and r> "
				+ form.getPages().getStart(), values != null ? values
				.toArray(new String[0]) : null, colList);
	}
	
	/**
	 * 处分单个审核显示详细信息
	 * @param pkValue
	 * @return
	 */
	public HashMap<String, String> queryWjcfsbxxOne(String pkValue) {
		String sql = "select xh,xn,nd,xm,xymc,zymc,bjmc,nj,xb,cflb,cfyy,cflbmc,cfyymc,jtwjsy,wjsj,xyyj,fjsclj,cfwh,cfsj,xyclyj,xxclyj,bz,sbsj,lxcksj,lswjjl,(select zzmmmc from view_xsxxb b where a.xh=b.xh) zzmmmc from view_wjcf a where xh||xn||nd||sbsj=?";
		return dao.getMapNotOut(sql, new String[]{pkValue});
	}
	
	/**
	 * 保存单个审核信息
	 * @param pkValue
	 * @param model
	 * @param userType
	 * @return
	 * @throws Exception
	 */
	public boolean saveCfwhxx(String pkValue, WjcfZjlgModel model,
			String userType) throws Exception {
		
		if ("xy".equalsIgnoreCase(userType)) {
			return dao
					.runUpdate(
							"update wjcfb set cfwh=?,cfsj=?,xyyj=?,lxcksj=?,xxsh='通过' where xh||xn||nd||sbsj=?",
							new String[] { DealString.toGBK(model.getCfwh()),
									model.getCfsj(),
									DealString.toGBK(model.getXyyj()),
									model.getLxcksj(), pkValue });
		} else {
			return dao
					.runUpdate(
							"update wjcfb set cfwh=?,cfsj=?,xxclyj=?,lxcksj=?,xxsh='通过' where xh||xn||nd||sbsj=?",
							new String[] { DealString.toGBK(model.getCfwh()),
									model.getCfsj(),
									DealString.toGBK(model.getXxclyj()),
									model.getLxcksj(), pkValue });
		}
	}
	
	/**
	 * 批量审核处分信息
	 * @param pkValue
	 * @param model
	 * @param userType
	 * @return
	 * @throws Exception
	 */
	public boolean savePlCfwhxx(String[] pkValue, WjcfZjlgModel model, String userType) throws Exception{
		if (pkValue == null) {
			return false;
		}
		String[] sqls = new String[pkValue.length];
		if ("xy".equalsIgnoreCase(userType)) {
			for (int i=0;i<pkValue.length;i++) {
				StringBuffer sql = new StringBuffer();
				sql.append("update wjcfb set xxsh='通过',xyyj='");
				sql.append(DealString.toGBK(model.getXyyj()));
				sql.append("',cfwh='");
				sql.append(DealString.toGBK(model.getCfwh()));
				sql.append("',cfsj='");
				sql.append(DealString.toGBK(model.getCfsj()));
				sql.append("' where xh||xn||nd||sbsj='");
				sql.append(pkValue[i]);
				sql.append("'");
				sqls[i] = sql.toString();
			}
		} else {
			for (int i=0;i<pkValue.length;i++) {
				StringBuffer sql = new StringBuffer();
				sql.append("update wjcfb set xxsh='通过',xxclyj='");
				sql.append(DealString.toGBK(model.getXxclyj()));
				sql.append("',cfwh='");
				sql.append(DealString.toGBK(model.getCfwh()));
				sql.append("',cfsj='");
				sql.append(DealString.toGBK(model.getCfsj()));
				sql.append("' where xh||xn||nd||sbsj='");
				sql.append(pkValue[i]);
				sql.append("'");
				sqls[i] = sql.toString();
			}
		}
		int[] rs = dao.runBatch(sqls);
		return dao.checkBatch(rs);
	}
	
	/**
	 * 查询留校察看表头
	 * @return
	 */
	public List<HashMap<String, String>> queryLxckcfxxTitle() {
		String[] enList = new String[]{ "pk", "xh", "xm",
				"xn", "nd", "cflbmc", "cfyymc", "cfwh", "cfsj", "lxcksj", "oper" };
		String[] cnList = new String[]{ "pk", "学号", "姓名",
				"学年", "年度", "处分类别", "处分原因", "处分文号", "处分时间", "留校察看解除时间", "操作" };
		return dao.arrayToList(enList, cnList);
	}
	
	/**
	 * 查询留校察看结果
	 * @param lxcksj
	 * @return
	 */
	public ArrayList<String[]> queryLxckxxResult(WjcfZjlgActionForm zjlgForm, String userType, String userName) {
		ArrayList<String[]> list = new ArrayList<String[]>();
		String sql = "select xh||xn||nd||sbsj pk,xh,xn,nd,xm,cflbmc,cfyymc,cfsj,cfwh,lxcksj,'' oper from view_wjcf a where xxsh='通过' and lxcksj is not null and not exists (select 1 from wjcf_zjlg_lxckb b where a.xh=b.xh and a.xn=b.cfxn and a.nd=b.cfnd and a.sbsj=b.cfsbsj) and cflbmc like '留校察看%'";
		
		if (Globals.XXDM_ZJCMXY.equalsIgnoreCase(Base.xxdm)) {
			sql += " and ((to_date(to_char(sysdate,'yyyy-mm-dd'),'yyyy-mm-dd') - to_date(lxcksj,'yyyy-mm-dd')) <= ";
		
			WjcfZjcmDAO zjcmDAO = new WjcfZjcmDAO();
			sql += StringUtils.isNull(zjlgForm.getLxcksj()) ? zjcmDAO.getSj() : zjlgForm.getLxcksj();
			sql += "or (to_date(lxcksj,'yyyy-mm-dd') - to_date(to_char(sysdate,'yyyy-mm-dd'),'yyyy-mm-dd')) >= 0)";
		} else {
			if (!StringUtils.isNull(zjlgForm.getLxcksj())) {
				sql += " and lxcksj like '%";
				sql += zjlgForm.getLxcksj();
				sql += "%'";
			}
		}
		
		if (!StringUtils.isNull(zjlgForm.getXydm())) {
			sql += " and xydm='";
			sql += zjlgForm.getXydm();
			sql += "'";
		}
		if (!StringUtils.isNull(zjlgForm.getXh())) {
			sql += " and xh like '%";
			sql += zjlgForm.getXh();
			sql += "%'";
		}
		if (!StringUtils.isNull(zjlgForm.getXm())) {
			sql += " and xm like '%";
			sql += zjlgForm.getXm();
			sql += "%'";
		}
		String FDYSql = "";
		if ("fdy".equalsIgnoreCase(userType)) {
			FDYSql = " and exists (select 1 from fdybjb b where zgh like '"
				+ userName + "' and a.bjdm = b.bjdm)";
		}
		String[] colList = new String[]{ "pk", "xh", "xm",
				"xn", "nd", "cflbmc", "cfyymc", "cfwh", "cfsj", "lxcksj", "oper" };
		list = dao.rsToVator(sql + FDYSql, new String[]{}, colList);
		return list;
	}
	
	/**
	 * 查询留校察看结果
	 * @param lxcksj
	 * @return
	 */
	public ArrayList<String[]> queryLxckxxResultnotExis(
			WjcfZjlgActionForm zjlgForm, String userType, String userName) {
		ArrayList<String[]> list = new ArrayList<String[]>();
		String sql = "select xh||xn||nd||sbsj pk,xh,xn,nd,xm,cflbmc,cfyymc,cfsj,cfwh,lxcksj,'' oper from view_wjcf a where xxsh='通过' and cflbmc like '留校察看%'";
		if (!StringUtils.isNull(zjlgForm.getLxcksj())) {
			sql += " and lxcksj like '%";
			sql += zjlgForm.getLxcksj();
			sql += "%'";
		}
		if (!StringUtils.isNull(zjlgForm.getXydm())) {
			sql += " and xydm='";
			sql += zjlgForm.getXydm();
			sql += "'";
		}
		if (!StringUtils.isNull(zjlgForm.getXh())) {
			sql += " and xh like '%";
			sql += zjlgForm.getXh();
			sql += "%'";
		}
		if (!StringUtils.isNull(zjlgForm.getXm())) {
			sql += " and xm like '%";
			sql += zjlgForm.getXm();
			sql += "%'";
		}
		String FDYSql = "";
		if ("fdy".equalsIgnoreCase(userType)) {
			FDYSql = " and exists (select 1 from fdybjb b where zgh like '"
					+ userName + "' and a.bjdm = b.bjdm)";
		}
		sql += " and not exists (select 1 from wjcf_zjlg_lxckb b where a.xh=b.xh and a.xn=b.cfxn and a.nd=b.cfnd and a.sbsj=b.cfsbsj)";
		String[] colList = new String[] { "pk", "xh", "xm", "xn", "nd",
				"cflbmc", "cfyymc", "cfwh", "cfsj", "lxcksj", "oper" };
		list = dao.rsToVator(sql + FDYSql, new String[] {}, colList);
		return list;
	}
	
	/**
	 * 查询留校察看数据维护表头
	 * @return
	 */
	public List<HashMap<String, String>> queryLxckxxTitle() {
		String[] enList = new String[]{ "pk","dis", "xh", "xm",
				 "cfxn","cfnd","cflbmc", "cfyymc", "cfwh", "cfsj","jcjg" };
		String[] cnList = new String[]{ "pk", "dis","学号", "姓名",
				 "处分学年", "处分年度", "处分类别", "处分原因", "处分文号", "处分时间","解除留校察看结果" };
		return dao.arrayToList(enList, cnList);
	}
	
	/**
	 * 留校察看数据查询记录数
	 * @param model
	 * @return
	 */
	public int queryLxckxxResultNum(WjcfZjlgModel model) {
		StringBuffer whereSql = getWhereSql(model);
		String sql = "select xh||cfxn||cfnd||cfsbsj pk,'' dis,xh,xm,xn,nd,cfxn,cfnd,cflbmc,cfyymc,cfwh,cfsj,jcjg from view_wjcf_zjlg_lxck where 1=1";
		String[] colList = new String[]{ "pk","dis", "xh", "xm",
				 "cfxn","cfnd","cflbmc", "cfyymc", "cfwh", "cfsj","jcjg" };
		return dao.rsToVator(sql + whereSql.toString(), values != null ? values
				.toArray(new String[0]) : new String[] {}, colList).size();
	}
	
	//留校察看数据查询结果
	public ArrayList<String[]> queryLxckxxResult(WjcfZjlgModel model, WjcfZjlgActionForm zjlgForm) {
		StringBuffer whereSql = getWhereSql(model);
		String sql = "select xh||cfxn||cfnd||cfsbsj pk,(case when xxsh='通过' then 'disabled' else '' end) dis,rownum r,xh,xm,xn,nd,cfxn,cfnd,cflbmc,cfyymc,cfwh,cfsj,jcjg from view_wjcf_zjlg_lxck where 1=1";
		String[] colList = new String[]{ "pk","dis", "xh", "xm",
			 "cfxn","cfnd","cflbmc", "cfyymc", "cfwh", "cfsj","jcjg" };
		return dao.rsToVator("select * from (" + sql + whereSql.toString() + ") where r<="
				+ (zjlgForm.getPages().getStart() + zjlgForm
						.getPages().getPageSize()) + " and r> "
				+ zjlgForm.getPages().getStart(), values != null ? values
				.toArray(new String[0]) : new String[] {}, colList);
	}
	
	public int queryLxckxxResultNumByxy(WjcfZjlgModel model, String userType, String userName) {
		StringBuffer whereSql = getWhereSql(model);
		String sql = "select xh||cfxn||cfnd||cfsbsj pk,'' dis,xh,xm,xn,nd,cfxn,cfnd,cflbmc,cfyymc,cfwh,cfsj,jcjg from view_wjcf_zjlg_lxck a where 1=1";
		String[] colList = new String[]{ "pk", "dis","xh", "xm",
				 "cfxn","cfnd","cflbmc", "cfyymc", "cfwh", "cfsj","jcjg" };
		String FDYSql = "";
		if ("fdy".equalsIgnoreCase(userType)) {
			FDYSql = " and exists (select 1 from fdybjb b where zgh like '"
				+ userName + "' and a.bjdm = b.bjdm)";
		}
		return dao.rsToVator(sql + whereSql.toString() + FDYSql, values != null ? values
				.toArray(new String[0]) : new String[] {}, colList).size();
	}
	
	public ArrayList<String[]> queryLxckxxResultByxy(WjcfZjlgModel model, WjcfZjlgActionForm zjlgForm,String userType, String userName) {
		StringBuffer whereSql = getWhereSql(model);
		String sql = "select xh||cfxn||cfnd||cfsbsj pk,'' dis,rownum r,xh,xm,xn,nd,cfxn,cfnd,cflbmc,cfyymc,cfwh,cfsj,jcjg from view_wjcf_zjlg_lxck a where 1=1";
		String[] colList = new String[]{ "pk", "dis","xh", "xm",
			 "cfxn","cfnd","cflbmc", "cfyymc", "cfwh", "cfsj","jcjg" };
		String FDYSql = "";
		if ("fdy".equalsIgnoreCase(userType)) {
			FDYSql = " and exists (select 1 from fdybjb b where zgh like '"
				+ userName + "' and a.bjdm = b.bjdm)";
		}
		return dao.rsToVator("select * from (" + sql + whereSql.toString() + FDYSql + ") where r<="
				+ (zjlgForm.getPages().getStart() + zjlgForm
						.getPages().getPageSize()) + " and r> "
				+ zjlgForm.getPages().getStart(), values != null ? values
				.toArray(new String[0]) : new String[] {}, colList);
	}
	
	/**
	 * 留校察看信息单个保存
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean saveLxcksqxx(WjcfZjlgModel model) throws Exception {
		return dao.runUpdate("insert into wjcf_zjlg_lxckb(xh,xn,nd,cfxn,cfnd,cflb,cfyy,cfsbsj,xsbx,tqjcly) " +
				"values (?,?,?,?,?,?,?,?,?,?)", new String[] {
						model.getXh(), model.getXn(), model.getNd(),
						model.getCfxn(), model.getCfnd(), model.getCflb(),
						model.getCfyy(), model.getCfsbsj(), DealString.toGBK(model.getXsbx()),model.getTqjcly() });
	}
	
	/**
	 * 查看留校察看信息
	 * @param pkValue
	 * @return
	 */
	public HashMap<String, String> viewLxcksqxx(String pkValue) {
		return dao.getMapNotOut("select xh,xm,xb,nj,xymc,zymc,bjmc,cfxn,cfnd,cfwh,cfsj,cflbmc,cfyymc,xsbx,lxcksj,xn,nd,tqjcly from view_wjcf_zjlg_lxck where xh||cfxn||cfnd||cfsbsj=?", new String[]{pkValue});
	} 
	
	/**
	 * 修改留校察看信息
	 * @param pkValue
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean modiLxcksqxx(String pkValue, WjcfZjlgModel model) throws Exception{
		return dao
				.runUpdate(
						"update wjcf_zjlg_lxckb set xn=?,nd=?,xsbx=?,tqjcly=? where xh||cfxn||cfnd||cfsbsj=?",
						new String[] {model.getXn(), model.getNd(),DealString.toGBK(model.getXsbx()),model.getTqjcly(), pkValue});
	}
	
	/**
	 * 删除留校察看信息
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public boolean delLxcksqxx(String[] pkValue) throws Exception {
		if (pkValue == null) {
			return false;
		} else {
			String[] sqlArr = new String[pkValue.length];
			for (int i=0;i<pkValue.length;i++) {
				StringBuffer sql = new StringBuffer("delete from wjcf_zjlg_lxckb where xh||cfxn||cfnd||cfsbsj='");
				sql.append(pkValue[i]);
				sql.append("'");
				sqlArr[i] = sql.toString();
			}
			int[] res = dao.runBatch(sqlArr);
			return dao.checkBatch(res);
		}
	}
	
	/**
	 * 留校察看信息查询表头
	 * @return
	 */
	public List<HashMap<String, String>> queryLxcksqshxxTitle() {
		String[] enList = new String[]{ "pk","dis", "xh", "xm",
				 "cfxn","cfnd","cflbmc", "cfyymc", "cfwh", "cfsj","jcjg", "xysh","xxsh" };
		String[] cnList = new String[]{ "pk", "dis","学号", "姓名",
				 "处分学年", "处分年度", "处分类别", "处分原因", "处分文号", "处分时间", Base.YXPZXY_KEY+"审核", "学校审核","解除留校察看结果" };
		return dao.arrayToList(enList, cnList);
	} 
	
	/**
	 * 学院留校察看审核信息查询结果记录数
	 * @param userType
	 * @param userName
	 * @param model
	 * @return
	 */
	public int queryLxckshxxResultByxyNum(String userType, String userName,
			WjcfZjlgModel model) {
		StringBuffer whereSql = getWhereSql(model);
		String sql = "select xh||cfxn||cfnd||cfsbsj pk,(case when xxsh='通过' then 'disabled' else '' end) dis,xh,xm,cfxn,cfnd,cflbmc,cfyymc,cfwh,cfsj,jcjg,xysh sh from view_wjcf_zjlg_lxck a where 1=1";
		String FDYSql = "";
		if ("fdy".equalsIgnoreCase(userType)) {
			FDYSql = " and exists (select 1 from fdybjb b where zgh like '"
					+ userName + "' and a.bjdm = b.bjdm)";
		}
		String[] enList = new String[] { "pk", "dis", "xh", "xm", "cfxn",
				"cfnd", "cflbmc", "cfyymc", "cfwh", "cfsj", "jcjg", "sh" };
		return dao.rsToVator(
				sql + whereSql.toString() + FDYSql,
				values != null ? values.toArray(new String[0])
						: new String[] {}, enList).size();
	}
	
	/**
	 * 学院留校察看审核查询结果
	 * @param userType
	 * @param form
	 * @param userName
	 * @param model
	 * @return
	 */
	public ArrayList<String[]> queryLxckshxxResultByxy(String userType, WjcfZjlgActionForm form,String userName,
			WjcfZjlgModel model) {
		StringBuffer whereSql = getWhereSql(model);
		String sql = "select xh||cfxn||cfnd||cfsbsj pk,(case when xxsh='通过' then 'disabled' else '' end) dis,rownum r,xh,xm,cfxn,cfnd,cflbmc,cfyymc,cfwh,cfsj,jcjg,xysh,xxsh from view_wjcf_zjlg_lxck a where 1=1";
		String FDYSql = "";
		if ("fdy".equalsIgnoreCase(userType)) {
			FDYSql = " and exists (select 1 from fdybjb b where zgh like '"
					+ userName + "' and a.bjdm = b.bjdm)";
		}
		String[] enList = new String[] { "pk", "dis", "xh", "xm", "cfxn",
				"cfnd", "cflbmc", "cfyymc", "cfwh", "cfsj", "xysh","xxsh", "jcjg" };
		return dao.rsToVator("select * from (" +
				sql + whereSql.toString() + FDYSql
				+ ") where r<="
				+ (form.getPages().getStart() + form
						.getPages().getPageSize()) + " and r> "
				+ form.getPages().getStart(),
				values != null ? values.toArray(new String[0])
						: new String[] {}, enList);
	}
	
	/**
	 * 学校留校察看审核信息查询结果记录数
	 * @param userType
	 * @param userName
	 * @param model
	 * @return
	 */
	public int queryLxckshxxResultByxxNum(
			WjcfZjlgModel model) {
		StringBuffer whereSql = getWhereSql(model);
		String sql = "select xh||cfxn||cfnd||cfsbsj pk,'' dis,xh,xm,cfxn,cfnd,cflbmc,cfyymc,cfwh,cfsj,jcjg,xxsh,xysh from view_wjcf_zjlg_lxck a where 1=1";
		String[] enList = new String[] { "pk", "dis", "xh", "xm", "cfxn",
				"cfnd", "cflbmc", "cfyymc", "cfwh", "cfsj", "jcjg", "xysh","xxsh" };
		return dao.rsToVator(
				sql + whereSql.toString() ,
				values != null ? values.toArray(new String[0])
						: new String[] {}, enList).size();
	}
	
	/**
	 * 学校留校察看审核查询结果
	 * @param userType
	 * @param form
	 * @param userName
	 * @param model
	 * @return
	 */
	public ArrayList<String[]> queryLxckshxxResultByxx(WjcfZjlgActionForm form,
			WjcfZjlgModel model) {
		StringBuffer whereSql = getWhereSql(model);
		String sql = "select xh||cfxn||cfnd||cfsbsj pk,'' dis,rownum r,xh,xm,cfxn,cfnd,cflbmc,cfyymc,cfwh,cfsj,jcjg,xxsh,xysh from view_wjcf_zjlg_lxck a where xysh='通过'";
		String[] enList = new String[] { "pk", "dis", "xh", "xm", "cfxn",
				"cfnd", "cflbmc", "cfyymc", "cfwh", "cfsj", "xysh","xxsh", "jcjg" };
		return dao.rsToVator("select * from (" +
				sql + whereSql.toString() 
				+ ") where r<="
				+ (form.getPages().getStart() + form
						.getPages().getPageSize()) + " and r> "
				+ form.getPages().getStart(),
				values != null ? values.toArray(new String[0])
						: new String[] {}, enList);
	}
	
	/**
	 * 学院查看留校查看信息
	 * @param pkValue
	 * @return
	 */
	public HashMap<String, String> viewLxckshByxy(String pkValue) {
		return dao.getMapNotOut("select xh,xm,xb,nj,xymc,zymc,bjmc,cflbmc,cfyymc,xn," +
				"nd,cfxn,cfnd,cfwh,cfsj,lxcksj,jcjg,xysh sh,xyyj yj,xxsh,xxyj,jtwjsy,jcsj,jcwh," +
				"xsbx,sbsj,cfsbsj,tqjcly,fdyjdyj from view_wjcf_zjlg_lxck where xh||cfxn||cfnd||cfsbsj=?", new String[]{pkValue});
	}
	
	/**
	 * 学校查看留校查看信息
	 * @param pkValue
	 * @return
	 */
	public HashMap<String, String> viewLxckshByxx(String pkValue) {
		return dao.getMapNotOut("select xh,xm,xb,nj,xymc,zymc,bjmc,cflbmc,cfyymc,xn," +
				"nd,cfxn,cfnd,cfwh,cfsj,lxcksj,jcjg,xxsh sh,xyyj,xxyj yj,jtwjsy,jcsj,jcwh," +
				"xsbx,sbsj,cfsbsj,tqjcly from view_wjcf_zjlg_lxck where xh||cfxn||cfnd||cfsbsj=?", new String[]{pkValue});
	}
	
	/**
	 * 保存留校察看单个审核信息
	 * @param pkValue
	 * @param userType
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean saveLxckDgshxx(String pkValue, String userType, WjcfZjlgModel model) throws Exception{
		if ("xy".equalsIgnoreCase(userType)) {
			return dao
					.runUpdate(
							"update wjcf_zjlg_lxckb set xysh=?,xyyj=?,fdyjdyj=? where xh||cfxn||cfnd||cfsbsj=?",
							new String[] { model.getSh(), model.getYj(),model.getFdyjdyj(),
									pkValue });
		} else {
			boolean result = dao
					.runUpdate(
							"update wjcf_zjlg_lxckb set xxsh=?,xxyj=?,jcjg=?,jcsj=?,jcwh=? where xh||cfxn||cfnd||cfsbsj=?",
							new String[] { model.getSh(), model.getYj(),
									model.getJcjg(), model.getJcsj(),
									model.getJcwh(),pkValue });
//			更新违纪处分表信息
			if (result) {
				dao.runUpdate("update wjcfb set cxjg=?,cxclsj=?,cxclwh=? where xh||xn||nd||sbsj=?", new String[]{model.getJcjg(),model.getJcsj(),model.getJcwh(),pkValue});
			}
			return result;
		}
	}
	
	//审核列表
	public List<HashMap<String, String>> loadShlist() {
		return dao.getChkList(3);
	}
	
	//解除处分列表
	public List<HashMap<String, String>> loadJcList() {
		List<HashMap<String, String>> list = new ArrayList<HashMap<String,String>>();
		String[] cn = {"解除留校察看","维护原处分"};
		for (String s : cn) {
			HashMap<String, String> oneDataMap = new HashMap<String, String>();
			oneDataMap.put("en", s);
			oneDataMap.put("cn", s);
			list.add(oneDataMap);
		}
		return list;
	}
	
	/**
	 * 批量保存留校察看信息
	 * @param userType
	 * @param pkValues
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean savePlshLxckxx(String userType, String[] pkValues,
			WjcfZjlgModel model) throws Exception {
		if (pkValues == null) {
			return false;
		}
		String[] sqlArr = new String[pkValues.length];
		String[] updateSql = new String[pkValues.length];
		if ("xy".equalsIgnoreCase(userType)) {
			for (int i = 0; i < pkValues.length; i++) {
				StringBuffer whereSql = new StringBuffer(
						"update wjcf_zjlg_lxckb set xysh='");
				whereSql.append(model.getSh());
				whereSql.append("',xyyj='");
				whereSql.append(model.getYj());
				whereSql.append("' where xh||cfxn||cfnd||cfsbsj='");
				whereSql.append(pkValues[i]);
				whereSql.append("'");
				sqlArr[i] = whereSql.toString();
			}
		} else {
			for (int i = 0; i < pkValues.length; i++) {
				StringBuffer whereSql = new StringBuffer(
						"update wjcf_zjlg_lxckb set xxsh='");
				whereSql.append(model.getSh());
				whereSql.append("',xxyj='");
				whereSql.append(model.getYj());
				whereSql.append("',jcsj='");
				whereSql.append(model.getJcsj());
				whereSql.append("',jcwh='");
				whereSql.append(model.getJcwh());
				whereSql.append("',jcjg='");
				whereSql.append(model.getJcjg());
				whereSql.append("' where xh||cfxn||cfnd||cfsbsj='");
				whereSql.append(pkValues[i]);
				whereSql.append("'");
				sqlArr[i] = whereSql.toString();
				StringBuffer updaSql = new StringBuffer("update wjcfb set cxjg='");
				updaSql.append(model.getJcjg());
				updaSql.append("',cxclsj='");
				updaSql.append(model.getJcsj());
				updaSql.append("',cxclwh='");
				updaSql.append(model.getJcwh());
				updaSql.append("' where xh||xn||nd||sbsj='");
				updaSql.append(pkValues[i]);
				updaSql.append("'");
				updateSql[i] = updaSql.toString();
			}
		}
		int[] flag = dao.runBatch(sqlArr);
		//更新违纪处分表信息
		dao.runBatch(updateSql);
		return dao.checkBatch(flag);
	}
	
	//学生处分表打信息
	public HashMap<String, String> getCfxx(String pkValue) throws Exception{
		HashMap<String, String> rs = dao.getMapNotOut("select xh,xm,cflbmc,cfyymc from view_wjcf where xh||xn||nd||sbsj=?", new String[]{pkValue});
		rs.put("year", DateUtils.getYear());
		rs.put("mon", DateUtils.getMonth());
		rs.put("date", DateUtils.getDayOfMonth());
		return rs;
	}
}
