package xgxt.wjcf.zjcm;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.MakeQuery;
import xgxt.utils.String.StringUtils;

public class WjcfZjcmDAO {

	DAO dao = DAO.getInstance();
	
	final StringBuffer QUERY_LXCKSJ = new StringBuffer("select sj from ")
					       .append("wjcf_lxcjsjb");
	
	final StringBuffer QUERY_CFBPRINT = new StringBuffer("select a.xh,a.xm,a.xb")
	             .append(",a.nj,a.xymc,a.zymc,a.bjmc,cfsj,cfwh,cflbmc,cfyymc,")
	             .append("b.sjhm,a.bz from view_wjcf a,view_xsjbxx b where ")
	             .append("a.xh=b.xh and a.xh||a.cflb||a.cfyy||a.xn||a.nd=?");
	
	final StringBuffer QUERY_CFBPRINT1 = new StringBuffer("select a.xh,a.xm,a.xb")
	             .append(",a.nj,a.xymc,a.zymc,a.bjmc,cfsj,cfwh,cflbmc,cfyymc,")
	             .append("b.sjhm,a.bz,a.xyclyj,a.xxclyj from view_wjcf a,view_xsjbxx b where ")
	             .append("a.xh=b.xh and a.xh||a.xn||a.sbsj=?");
	
	final StringBuffer QUERY_CFHZXX = new StringBuffer("select rownum r,xh,xm,")
	             .append("xb,xymc,zymc,bjmc,cfwh,cfsj,cflbmc,cfyymc,'院办' spbm")
	             .append(",'      ' bz from view_wjcf");
	
	final StringBuffer QUERY_JCLXXX = new StringBuffer("select xh,xn,nd,cflbmc")
	             .append(",cfyymc,cfsj,cfwh,xsbx,tqjcly,xm,xb,nj,xymc,zymc,bjmc")
	             .append(",xyyj,xxyj,fdyjdyj,(select sjhm from view_xsjbxx b ")
	             .append("where a.xh=b.xh) sjhm from view_wjcf_zjlg_lxck a ")
	             .append("where xh||cfxn||cfnd||cfsbsj=?");
	
	final StringBuffer QUERY_JCLXQXSQL = new StringBuffer("select count(*) num")
	    .append(" from yhqxb where yhm=? and gnmkdm like 'N0911%' and dxq='1'"); 
	/**
	 * 查询留校察看时间
	 * @return
	 */
	public String getSj() {
		return dao.getOneRs(getQUERY_LXCKSJ(), new String[]{}, "sj");
	}
	
	/**
	 * 删除留校察看时间
	 * @return
	 * @throws Exception
	 */
	public boolean deleteSj() throws Exception{
		return dao.runUpdate("delete from wjcf_lxcjsjb", new String[]{});
	}
	
	/**
	 * 保存留校察看时间
	 * @param sj
	 * @return
	 * @throws Exception
	 */
	public boolean addSj(String sj) throws Exception{
		return dao.runUpdate("insert into wjcf_lxcjsjb(sj) values (?)",
				new String[] { sj });
	}

	public String getQUERY_LXCKSJ() {
		return QUERY_LXCKSJ.toString();
	} 
	
	public String getLxckjcDjcxx(String sj, String xydm, String isFdy, String userName) {
		StringBuffer sql = new StringBuffer("select count(*) num from view_wjcf a where lxcksj")
		        .append(" is not null and xxsh='通过' and ((to_date(to_char(sysdate,'yyyy-mm-dd')")
		        .append(",'yyyy-mm-dd') - to_date(lxcksj,'yyyy-mm-dd')) <= ? ")
		        .append("or (to_date(lxcksj,'yyyy-mm-dd') - to_date(to_char(sysdate")
		        .append(",'yyyy-mm-dd'),'yyyy-mm-dd')) >= 0)")
		        .append(" and xxsh='通过' and not exists (select 1 from wjcf_zjlg_lxckb")
		        .append(" b where a.xh=b.xh and a.xn=b.cfxn and a.nd=b.cfnd and ")
		        .append("a.sbsj=b.cfsbsj) and cflbmc like '留校察看%'");
				
		if (StringUtils.isNull(xydm)) {
			sql.append(" and xydm = '");
			sql.append(xydm);
			sql.append("'");
		}
		if ("true".equalsIgnoreCase(isFdy)) {
			sql.append(" and exists (select 1 from fdybjb b where zgh like '");
			sql.append(userName);
			sql.append("')");
		}
		return dao.getOneRs(sql.toString(), new String[]{sj}, "num");
	}
	
	public HashMap<String, String> getCfbPrintxx(String cfpk) {
		return dao
				.getMapNotOut(
						getQUERY_CFBPRINT(),
						new String[] { cfpk });
	}
	
	public HashMap<String, String> queryCfbPrintxx(String cfpk) {
		return dao
				.getMapNotOut(
						getQUERY_CFBPRINT1(),
						new String[] { cfpk });
	}
	
	/**
	 * 查询各学院学生处分汇总信息
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> queryXycfhzxx(WjcfZjcmModel model) throws Exception{
		String[] queryList = new String[] { "xydm", "xn", "xq"};
		MakeQuery queryObject = new MakeQuery();
		queryObject.makeQuery(queryList, new String[]{}, model);
		String[] colList = new String[] { "r", "cfwh", "cfsj", "xm", "xb",
				"xymc", "bjmc", "cfyymc", "cflbmc", "spbm", "bz" };
		
		String csSql = "";
		if (StringUtils.isNotNull(model.getCs())) {
			csSql = " and xh in (select xh from wjcfb where xxsh='通过' group by xh having count(xh) >= '"+model.getCs()+"')";
		}
		return CommonQueryDAO.commonQueryNotFy(getQUERY_CFHZXX(), queryObject
				.getQueryString() + " and xxsh='通过' and cfsj is not null and cfwh is not null" + csSql, queryObject.getInputList(), colList,
				model);
	}
	
	/**
	 * 查询解除留校察看打印报表信息
	 * @param pkValue
	 * @return
	 */
	public HashMap<String, String> queryJclxbbxx(String pkValue) {
		return dao.getMapNotOut(getQUERY_JCLXXX(), new String[]{pkValue});
	}
	
	/**
	 * 查询用户是否解除留校察看功能权限
	 * @param userName
	 * @return
	 */
	public String queryUserJclxqx(String userName) {
		return dao.getOneRs(getQUERY_JCLXQXSQL(), new String[]{userName}, "num");
	}
	
	public HashMap<String, String> queryStuLxclxx(String pkValue) {
		return dao.getMapNotOut("select xh,xm,xb,nj,xsbx,xymc,zymc,cfxn,lxcksj,cfnd,bjmc,xn,nd,cflbmc,cfyymc,cfsj,cfwh,jcsj,jcwh,jcjg,tqjcly from view_wjcf_zjlg_lxck where xh||cfxn||cfsbsj = ?", new String[]{pkValue});
	}

	/**
	 * 查询学生处分情况
	 * @param model  model里面,xh 参数必须要传过来,否则可能查不到数据
	 * @return 用,号隔开的处分名称
	 * @throws Exception
	 */
	public String queryStuLxckxx(WjcfZjcmModel model) throws Exception {
		String[] rs = dao
				.getArray(
						"select cflbmc from view_wjcf a where xh=? and not exists (select 1 from wjcf_zjlg_lxckb b where a.xh=b.xh and a.xn=b.cfxn and a.sbsj=b.cfsbsj)",
						new String[] { model.getXh()}, "cflbmc");
		String result = "";
		if (rs != null && rs.length > 0) {
			for (String s : rs) {
				result += s + ",";
			}
		}
		return StringUtils.isNotNull(result) ? result.substring(0, result
				.length() - 1) : "";
	}
	
	public List<String[]> queryStuCfxx(String xh) {
		return dao.rsToVator("select xn,xq,cflbmc,cfyymc,cfsj,cfwh from view_wjcf where xh=? and xxsh='通过' and cfsj is not null and cfwh is not null", new String[]{xh}, new String[]{"xn","xq","cflbmc", "cfyymc", "cfsj", "cfwh"});
	}
	
	
	/*********************提供给评奖评优模块的方法*********************/
	/**
	 * 查询学生处分信息  输入条件是学号数组,学年,学期,处分类别名称
	 * 符合条件后返回的是学号数组,如果不符合条件,则返回空数组
	 * @param arrXh  学号数组 该参数不能为空
	 * @param xn     学年    该参数为空,则查询所有学年的
	 * @param xq     学期    该参数为空,则查询所有学期的
	 * @param cflbmc 处分类别名称  该参数为空,则查询所有处分类别
	 * @return
	 * @throws SQLException
	 */
	public String[] queryWjcfxxByArrXh(String[] arrXh, String xn, String xq,
			String cflbmc) throws SQLException {
		StringBuilder sql = new StringBuilder("select xh from view_wjcf where xxsh='通过' and cfwh is not null and cfsj is not null");
		ArrayList<String> values = new ArrayList<String>(); 
		if (StringUtils.isNotNull(xn)) {
			sql.append(" and xn=?");
			values.add(xn);
		}
		if (StringUtils.isNotNull(xq)) {
			sql.append(" and xq = ?");
			values.add(xq);
		}
		if (StringUtils.isNotNull(cflbmc)) {
			sql.append(" and cflbmc like ?");
			values.add("%" + cflbmc + "%");
		}
		if (arrXh != null && arrXh.length > 0) {
			sql.append(" and xh in (");
			for (int i=0;i<arrXh.length;i++) {
				if (i == arrXh.length-1) {
					sql.append("?)");
				} else {
					sql.append("?,");
				}
				values.add(arrXh[i]);
			}
		}
		return dao.getArray(sql.toString(), values != null ? values
				.toArray(new String[0]) : new String[] {}, "xh");
	}
	
	/**
	 * 检测学生同一处分文号，处分时间是否有受过处分
	 * @param xh
	 * @param cfsj
	 * @param cfwh
	 * @return
	 */
	public boolean checkStuWjcfIsExists(String xh, String cfsj, String cfwh) {
		String count = dao
				.getOneRs(
						"select count(*) cnt from wjcfb where xh=? and cfsj=? and cfwh=?",
						new String[] { xh, cfsj, cfwh }, "cnt");
		return StringUtils.isNull(count) || "0".equalsIgnoreCase(count) ? true
				: false;
	}
	
	public String checkStuWjcfIsExistsBypl(String pkStr, String cfsj, String cfwh) {
		String[] pkArr = StringUtils.isNull(pkStr) ? new String[]{} : pkStr.split("!@");
		String flag = "";
		if (pkArr != null && pkArr.length > 0) {
			StringBuilder sql = new StringBuilder();
			for (int i=0;i<pkArr.length;i++) {
				sql.append("select cfsj,cfwh from wjcfb where xh||xn||sbsj = '");
				sql.append(pkArr[i]);
				sql.append("'");
				if (i < pkArr.length -1) {
					sql.append(" union all ");
				}
			}
			List<HashMap<String, String>> rs = dao.getList(sql.toString(), new String[]{}, new String[]{"cfsj", "cfwh"});
			if (rs != null && rs.size() > 0) {
				for (HashMap<String, String> map : rs) {
					if (cfsj.equalsIgnoreCase(map.get("cfsj")) && cfwh.equalsIgnoreCase(map.get("cfwh"))) {
						flag += "1";
					} else {
						flag += "0";
					}
					flag += "!@";
				}
			}
		}
		
		return StringUtils.isNotNull(flag) ? flag.substring(0, flag.length() - 2) : flag;
	}
	
	/**
	 * 查询学生处分信息 - 成都体育
	 * @param cfpk
	 * @return
	 */
	public HashMap<String, String> queryXsCfxx(String cfpk) {
		return dao
				.getMapNotOut(
						"select a.*,(select csrq from view_xsxxb b where a.xh=b.xh) csrq,(select mzmc from view_xsxxb b where a.xh=b.xh) mzmc,(select jtszd from xsfzxxb b where a.xh=b.xh) jtdz from view_wjcf a where xh||cflb||cfyy||xn||nd = ?",
						new String[] { cfpk });
	}
	
	/**
	 * 成都体育违纪信息(xh||xn||sbsj)
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> printwjInfo(String pkValue) throws Exception {
		return dao
				.getMapNotOut(
						" select a.*,(select csrq from view_xsxxb b where a.xh=b.xh) csrq, "
								+ " (select mzmc from view_xsxxb b where a.xh=b.xh) mzmc,(select jtszd from xsfzxxb b where a.xh=b.xh) jtdz from view_wjcf a where a.xh||a.xn||a.sbsj=?",
						new String[] { pkValue });
	}
	
	
	public String getQUERY_CFBPRINT() {
		return QUERY_CFBPRINT.toString();
	}

	public String getQUERY_CFBPRINT1() {
		return QUERY_CFBPRINT1.toString();
	}

	public String getQUERY_CFHZXX() {
		return QUERY_CFHZXX.toString();
	}

	public String getQUERY_JCLXXX() {
		return QUERY_JCLXXX.toString();
	}

	public String getQUERY_JCLXQXSQL() {
		return QUERY_JCLXQXSQL.toString();
	}
}
