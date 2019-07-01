package xgxt.jygl.njjs;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.MakeQuery;
import xgxt.utils.String.StringUtils;

public class NjjsJyglDAO {
	/**
	 * 学生上报查询
	 * @param model
	 * @return
	 */
	public List<String[]> xssbQuery(NjjsJyglModel model){
		StringBuilder sql = new StringBuilder();
		List<String[]> list = null;
		
		String[] colLikeList = new String[]{"xh", "xm"};
		String[] colList = new String[]{"nj", "xydm", "zydm", "bjdm"};
		
		MakeQuery makeQuery = new MakeQuery();
		try {
			makeQuery.makeQuery(colList, colLikeList, model);
			
			String query = makeQuery.getQueryString();
			String[] inputs = makeQuery.getInputList();
			String[] outputs = new String[]{"xh", "xm", "xb", "nj", "xymc", "zymc", "bjmc"};
			
			sql.append("select rownum r,a.* from (select a.xh,a.xm,a.xb,a.nj,a.xz,a.xydm,a.zydm,a.bjdm,a.xymc,a.zymc,a.bjmc,'否' sfsb ")
				.append("from view_xsjbxx a where not exists(select 1 from xg_jygl_njjs_xsjbxxb b where b.xh=a.xh) ")
				.append("union all ")
				.append("select a.xh,a.xm,a.xb,a.nj,a.xz,a.xydm,a.zydm,a.bjdm,a.xymc,a.zymc,a.bjmc,'是' sfsb  from xg_jygl_njjs_xsjbxxb a) a ");
			
			list = CommonQueryDAO.commonQuery(sql.toString(), query+" and sfsb='否'"+model.getFdyQuery(), inputs, outputs, model);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return list;
	}
	
	/**
	 * 保存学生上报
	 * @param model
	 * @return
	 */
	public boolean saveXssb(NjjsJyglModel model){
		StringBuilder sql = new StringBuilder();
		DAO dao = DAO.getInstance();
		boolean flag = false;
		
		sql.append("insert into xg_jygl_njjs_xsjbxxb(xh,xm,xb,nj,bynd,xydm,xymc,zydm,zymc,bjdm,bjmc,")
			.append("csrq,sfzh,xl,xz,pyfs,sydq,jtszd,lxfs,jndj) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
		
		String[] inputs = new String[]{
				model.getXh(),model.getXm(),model.getXb(),model.getNj(),model.getBynd(),
				model.getXydm(),model.getXymc(),model.getZydm(),model.getZymc(),model.getBjdm(),model.getBjmc(),
				model.getCsrq(),model.getSfzh(),model.getXl(),model.getXz(),model.getPyfs(),model.getSydq(),
				model.getJtszd(),model.getLxfs(),model.getJndj()
		};
		
		try {
			flag = dao.runUpdate(sql.toString(), inputs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return flag;
	}
	
	/**
	 * 保存学生上报
	 * @param model
	 * @return
	 */
	public boolean updateXssb(NjjsJyglModel model){
		StringBuilder sql = new StringBuilder();
		DAO dao = DAO.getInstance();
		boolean flag = false;
		
		sql.append("update xg_jygl_njjs_xsjbxxb set sfzh=?,bynd=?,xl=?,sydq=?,pyfs=?,jtszd=?,lxfs=?,jndj=? where xh=?");
		
		String[] inputs = new String[]{
			model.getSfzh(), model.getBynd(), model.getXl(),model.getSydq(),model.getPyfs(), 
			model.getJtszd(), model.getLxfs(),model.getJndj(), model.getXh()
		};
		
		try {
			flag = dao.runUpdate(sql.toString(), inputs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return flag;
	}
	
	/**
	 * 保存学生实习
	 * @param model
	 * @return
	 */
	public boolean saveXssx(NjjsJyglModel model){
		StringBuilder sql = new StringBuilder();
		DAO dao = DAO.getInstance();
		boolean flag = false;
		
		sql.append("insert into xg_jygl_njjs_dxsxxxb(xh,sxjyfs,sxdw,sxdwxz,sxdwdz,sxbm,sxgw,ldbh,sstj,jtzk,sxdwbdqk,sjhm,lxdh) ")
			.append("values(?,?,?,?,?,?,?,?,?,?,?,?,?)");
			
		
		String[] inputs = new String[]{
				model.getXh(),model.getSxjyfs(),model.getSxdw(),model.getSxdwxz(),model.getSxdwdz(),
				model.getSxbm(),model.getSxgw(),model.getLdbh(),model.getSstj(),model.getJtzk(),
				model.getSxdwbdqk(),model.getSjhm(),model.getLxdh()
		};
		
		try {
			flag = dao.runUpdate(sql.toString(), inputs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return flag;
	}
	
	public boolean updateXssx(NjjsJyglModel model){
		StringBuilder sql = new StringBuilder();
		DAO dao = DAO.getInstance();
		boolean flag = false;
		
		sql.append("update xg_jygl_njjs_dxsxxxb set sxjyfs=?,sxdw=?,sxdwxz=?,sxdwdz=?,sxbm=?,sxgw=?,ldbh=?,")
			.append("sstj=?,jtzk=?,sxdwbdqk=?,sjhm=?,lxdh=? where xh=?");
		
		
		String[] inputs = new String[]{
				model.getSxjyfs(),model.getSxdw(),model.getSxdwxz(),model.getSxdwdz(),
				model.getSxbm(),model.getSxgw(),model.getLdbh(),model.getSstj(),model.getJtzk(),
				model.getSxdwbdqk(),model.getSjhm(),model.getLxdh(),model.getXh()
		};
		
		try {
			flag = dao.runUpdate(sql.toString(), inputs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return flag;
	}
	
	/**
	 * 保存毕业去向
	 * @param model
	 * @return
	 */
	public boolean saveByqx(NjjsJyglModel model){
		StringBuilder sql = new StringBuilder();
		DAO dao = DAO.getInstance();
		boolean flag = false;
		
		sql.append("update xg_jygl_njjs_xsjbxxb set byqx=?,lxdz=?,yb=?,lxdh=?,sjhm=?,email=?,jydw=? ")
			.append("where xh=?");
			
		
		String[] inputs = new String[]{
				model.getByqx(),model.getLxdz(),model.getYb(),model.getLxdh(),model.getSjhm(),
				model.getEmail(),model.getJydw(),model.getXh()
		};
		
		try {
			flag = dao.runUpdate(sql.toString(), inputs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return flag;
	}
		
	/**
	 * 获取学生信息
	 * @param xh
	 * @return
	 */
	public Map<String, String> getStuInfo(String xh){
		String sql = "select a.*,(select b.jtszd from xsfzxxb b where b.xh=a.xh) jtszd,c.xl from view_xsbfxx a left join XG_JYGL_NJJS_XSJBXXB c on a.xh = c.xh where a.xh=?";
		return DAO.getInstance().getMapNotOut(sql, new String[]{xh});
	}
	
	/**
	 * 获取学生信息
	 * @param xh
	 * @return
	 */
	public Map<String, String> getXssxInfo(String xh){
		StringBuilder sql = new StringBuilder();
		
		sql.append("select a.*,rownum r from (")
			.append("select a.*,b.sxjyfs,b.sxdw,b.sxdwxz,b.sxdwdz,b.sxbm,b.sxgw,b.ldbh,b.sstj,b.jtzk,b.sxdwbdqk,b.sjhm,b.lxdh ")
//			.append("from (select xh,xm,xymc,zymc,bjmc,nj from view_xsbfxx where xh=?) a left join xg_jygl_njjs_dxsxxxb b on a.xh=b.xh) a");
			.append("from (select a.xh,a.xm,a.xymc,a.zymc,a.bjmc,a.nj," +
					"b.jtszd ,c.lxdh||'/'||c.sjhm lxfs from view_xsbfxx a left join xsfzxxb b on a.xh=b.xh " +
					" left join xsxxb c on a.xh=c.xh where a.xh=?) a " +
					"left join xg_jygl_njjs_dxsxxxb b on a.xh=b.xh) a");
		
		return DAO.getInstance().getMapNotOut(sql.toString(), new String[]{xh});
	}
	
	/**
	 * 毕业去向汇总查询
	 * @param model
	 * @return
	 */
	public List<String[]> byqxhzQuery(NjjsJyglForm model){
		StringBuilder sql = new StringBuilder();
		
		List<String[]> list = null;
		String[] outputs = new String[]{"xsxh", "name", "xslb", "bynd", "xymc", "zymc", "bjmc", "byqx", "xxsh"};
		String[] colLikeList = new String[]{"xm", "xsxh"};
		String[] colList = new String[]{"xydm", "zydm", "bjdm"};
		
		MakeQuery makeQuery = new MakeQuery();
		
		try {
			makeQuery.makeQuery(colList, colLikeList, model);
		
			String query = makeQuery.getQueryString();
			String[] inputs = makeQuery.getInputList();
			
			sql.append("select rownum r,a.id,a.xsxh,a.name,a.xbdm,b.xslb,b.bynd,b.byqx,b.xxsh,c.xydm,c.xymc,c.zydm,c.zymc,c.bjdm,c.bjmc ")
				.append("from jygl_xsjbxxb a left join jygl_byqx b on a.xsxh=b.xsxh left join view_xsjbxx c on a.xsxh=c.xh ")
				.append(query)
				.append(" order by bynd,bjdm");
			
			
			list = CommonQueryDAO.commonQuery(sql.toString(), "", inputs, outputs, model);
		
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return list;
	}
	
	/**
	 * 获取学历List
	 * @return
	 */
	public List<HashMap<String, String>> getXlList(){
		String sql = "select xldm dm,xl mc from dmk_xl order by xldm";
		
		return DAO.getInstance().getListNotOut(sql, new String[]{});
	}
	
	/**
	 * 获取培养层次（技能等级）List
	 * @return
	 */
	public List<HashMap<String, String>> getPyccList(){
		String sql = "select xsccdm dm,xsccmc mc from dtjs_xsccb order by xsccdm";
		
		return DAO.getInstance().getListNotOut(sql, new String[]{});
	}
	
	/**
	 * 获取培养方式List
	 * @return
	 */
	public List<HashMap<String, String>> getPyfsList(){
		String sql = "select pyfsdm dm,pyfs mc from dmk_bzpyfs order by pyfsdm";
		
		return DAO.getInstance().getListNotOut(sql, new String[]{});
	}
	
	/**
	 * 获取毕业去向List
	 * @return
	 */
	public List<HashMap<String, String>> getByqxList(){
		String sql = "select byqxdm dm,byqx mc from dmk_byqx order by byqxdm";
		
		return DAO.getInstance().getListNotOut(sql, new String[]{});
	}
	
	/**
	 * 获取省List
	 * @return
	 */
	public List<HashMap<String, String>> getShenList(){
		String sql = "select qxdm dm,qxmc mc from dmk_qx where qxdm like '__0000' order by qxdm";
	
		return DAO.getInstance().getListNotOut(sql, new String[]{});
	}
	
	/**
	 * 获取市List
	 * @return
	 */
	public List<HashMap<String, String>> getShiList(String shen){
		String  sql = "";
		DAO dao = DAO.getInstance();
		List<HashMap<String, String>> list = new ArrayList<HashMap<String,String>>();
		
		if(StringUtils.isNull(shen)){
			sql = "select qxdm dm,qxmc mc from dmk_qx where qxdm like '____00' and qxdm not like '__0000' order by qxdm";
			list = dao.getListNotOut(sql, new String[]{});
		}else{
			String shi = shen.substring(0, 2)+ "__00";
			sql = "select qxdm dm,qxmc mc from dmk_qx where qxdm like ? and qxdm <> ? order by qxdm";
			list = dao.getListNotOut(sql, new String[]{shi, shen});
		}
		return list;
	}
	

	/**
	 * 获取县List
	 * @return
	 */
	public List<HashMap<String, String>> getXianList(String shen,String shi){

		DAO dao = DAO.getInstance();
		List<HashMap<String, String>> list = new ArrayList<HashMap<String,String>>();
		
		String sql = "";
		
		if(StringUtils.isNull(shen) && StringUtils.isNull(shi)){
			sql = "select qxdm dm,qxmc mc from dmk_qx where qxdm not like '____00' and qxdm not like '__0000' order by qxdm";
			list = dao.getListNotOut(sql, new String[]{});
		}else{	
			
			String self = "";
			String xian = "";
			
			if(!Base.isNull(shen)){
				xian = shen.substring(0, 2) + "____";
				self = shen;
			}else if(!Base.isNull(shi)){
				xian = shi.substring(0, 4) + "__";
				self = shi;
			}
			
			sql = "select qxdm dm,qxmc mc from dmk_qx where qxdm like ? and qxdm <> ? order by qxdm";
			list = dao.getListNotOut(sql, new String[] { xian, self });
		}
		return list;
	}
	
	/**
	 * 获取毕业年度List
	 * @return
	 */
	public List<HashMap<String, String>> getByndList(){
		String sql = "select max(bynd) bynd from xg_jygl_njjs_xsjbxxb";
		List<HashMap<String, String>> list = new ArrayList<HashMap<String,String>>();
		
		DAO dao = DAO.getInstance();
		
		int ksnd = 2008;
		String jsnd = dao.getOneRs(sql, new String[]{}, "bynd");
		
		if(StringUtils.isNull(jsnd) || Integer.parseInt(jsnd)<2012){
			jsnd = "2014";
		}
		
		int end = Integer.parseInt(jsnd);
		
		for(;ksnd<=end;ksnd++){
			HashMap<String, String> map =new HashMap<String, String>();
			map.put("dm", String.valueOf(ksnd));
			map.put("mc", String.valueOf(ksnd));
			list.add(map);
		}
		return list; 
	}
	
	/**
	 * 毕业生查询
	 * @param model
	 * @return
	 */
	public List<String[]> bysQuery(NjjsJyglModel model){
		StringBuilder sql = new StringBuilder();
		
		List<String[]> list = null;
		String[] outputs = new String[]{"xh", "xm", "xb", "bjmc", "bynd", "byqxmc", "sfsb" };
		String[] colLikeList = new String[]{"nj", "xm", "xh"};
		String[] colList = new String[]{"xydm", "zydm", "bjdm", "byqx", "sfsb", "bynd"};
		
		MakeQuery makeQuery = new MakeQuery();
		
		try {
			makeQuery.makeQuery(colList, colLikeList, model);
		
			String query = makeQuery.getQueryString();
			String[] inputs = makeQuery.getInputList();
			
//			sql.append("select rownum r,a.*,(select byqx from dmk_byqx b where b.byqxdm=a.byqx ) byqxmc ")
//				.append("from xg_jygl_njjs_xsjbxxb a ")
//				.append(query)
//				.append(" order by bynd,bjdm");
			
			sql.append("select rownum r,a.* from (")
				.append("select a.xh,a.xm,a.xb,a.nj,a.xz,a.xydm,a.zydm,a.bjdm,a.xymc,a.zymc,a.bjmc,'否' sfsb,'' bynd,'' byqx,'' byqxmc ")
				.append("from view_xsjbxx a where not exists(select 1 from xg_jygl_njjs_xsjbxxb b where b.xh=a.xh) ")
				.append("union all ")
				.append("select a.xh,a.xm,a.xb,a.nj,a.xz,a.xydm,a.zydm,a.bjdm,a.xymc,a.zymc,a.bjmc,'是' sfsb,a.bynd,")
				.append("a.byqx,(select byqx from dmk_byqx b where b.byqxdm=a.byqx ) byqxmc ")
				.append("from xg_jygl_njjs_xsjbxxb a) a ")
				.append(query);
			
			list = CommonQueryDAO.commonQuery(sql.toString(), model.getFdyQuery(), inputs, outputs, model);
		
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return list;
	}
	
	/**
	 * 上报学生查询
	 * @param model
	 * @return
	 */
	public List<String[]> sbxsQuery(NjjsJyglModel model){
		StringBuilder sql = new StringBuilder();
		
		List<String[]> list = null;
		String[] outputs = new String[]{"xh", "xm", "nj", "xymc", "zymc", "bjmc", "bynd" };
		String[] colLikeList = new String[]{"nj", "xm", "xh"};
		String[] colList = new String[]{"xydm", "zydm", "bjdm", "bynd"};
		
		MakeQuery makeQuery = new MakeQuery();
		
		try {
			makeQuery.makeQuery(colList, colLikeList, model);
		
			String query = makeQuery.getQueryString();
			String[] inputs = makeQuery.getInputList();
			
			sql.append("select rownum r,a.* ")
				.append("from xg_jygl_njjs_xsjbxxb a ")
				.append(query)
				.append(model.getFdyQuery())
				.append(" order by bynd,bjdm");
			
			list = CommonQueryDAO.commonQuery(sql.toString(), "", inputs, outputs, model);
		
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return list;
	}
	
	/**
	 * 删除上报学生
	 * @param xhArr
	 * @return
	 */
	public boolean delSbxs(List<String[]> params){
		boolean flag = false;
		DAO dao = DAO.getInstance();
		
		String sql  = "delete xg_jygl_njjs_xsjbxxb where xh=?";
		
		try {
			int[] rs = dao.runBatch(sql,params);
			flag = dao.checkBatchResult(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return flag;
	}
	
	/**
	 * 删除毕业去向学生
	 * @param xhArr
	 * @return
	 */
	public boolean delByqx(List<String[]> params){
		boolean flag = false;
		DAO dao = DAO.getInstance();
		String sql  = "update xg_jygl_njjs_xsjbxxb set byqx='',lxdz='',yb='',lxdh='',sjhm='',email='',jydw='' where xh=?";
		
		try {
			int[] rs = dao.runBatch(sql,params);
			flag = dao.checkBatchResult(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return flag;
	}
	
	/**
	 * 删除实习学生
	 * @param xhArr
	 * @return
	 */
	public boolean delXssx(List<String[]> params){
		boolean flag = false;
		DAO dao = DAO.getInstance();
		String sql  = "delete xg_jygl_njjs_dxsxxxb where xh=?";
		
		try {
			int[] rs = dao.runBatch(sql,params);
			flag = dao.checkBatchResult(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return flag;
	}
	
	/**
	 * 学生实习查询
	 * @param model
	 * @return
	 */
	public List<String[]> xssxQuery(NjjsJyglModel model){
		StringBuilder sql = new StringBuilder();
		
		List<String[]> list = null;
		String[] outputs = new String[]{"xh", "xm", "nj", "xymc", "zymc", "bjmc", "sfsb"};
		String[] colLikeList = new String[]{"nj", "xm", "xh"};
		String[] colList = new String[]{"xydm", "zydm", "bjdm", "sfsb"};
		
		MakeQuery makeQuery = new MakeQuery();
		
		try {
			makeQuery.makeQuery(colList, colLikeList, model);
		
			String query = makeQuery.getQueryString();
			String[] inputs = makeQuery.getInputList();
			sql.append("select a.*,rownum r from (")
				.append("select a.xh,a.xm,a.xb,a.nj,a.xydm,a.zydm,a.bjdm,a.xymc,a.zymc,a.bjmc,")
				.append("(case when b.xh is not null then '是' else '否' end) sfsb ")
				.append("from view_xsjbxx a left join xg_jygl_njjs_dxsxxxb b on a.xh=b.xh) a ")
				.append(query);
			
			list = CommonQueryDAO.commonQuery(sql.toString(), model.getFdyQuery(), inputs, outputs, model);
		
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return list;
	}
	
	/**
	 * 获取毕业生信息
	 * @param xh
	 * @return
	 */
	public Map<String, String> getBysInfo(String xh){
		String sql = "select a.*,(select pyfs from dmk_bzpyfs b where b.pyfsdm=a.pyfs) pyfsmc,"
				+ "(select xsccmc from dtjs_xsccb b where b.xsccdm=a.xl) xlmc from xg_jygl_njjs_xsjbxxb a where xh=?";
		return DAO.getInstance().getMapNotOut(sql, new String[]{xh});
	}
	
	/**
	 * 获取毕业去向导出信息
	 * @param model
	 * @return
	 */
	public List<HashMap<String, String>> getByqxDcInfo(NjjsJyglModel model){
		//	String bynd = model.getBynd();
		String bjdm = model.getBjdm();
		List<HashMap<String, String>> list = new ArrayList<HashMap<String,String>>();
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select rownum count,a.* from xg_jygl_njjs_xsjbxxb a where bjdm=?");
		
		DAO dao = DAO.getInstance();
		
		String[] outputs = new String[]{"count", "xm", "xb", "csrq", "jtszd", "jydw", "lxdh"};
		
		list = dao.getList(sql.toString(), new String[]{bjdm}, outputs);
		
		return list;
	}
	
	public HashMap<String,String> getBjxx(NjjsJyglModel model){
		String bjdm = model.getBjdm();
		return DAO.getInstance().getMap("select * from view_njxyzybj where bjdm = ?", 
				new String[]{bjdm}, new String[]{"xydm","xymc","bjdm","bjmc"});
	}
	
	/**
	 * 学生实习导出
	 * @param model
	 * @return
	 */
	public List<String[]> xssxExp(NjjsJyglModel model){
		StringBuilder sql = new StringBuilder();
		
		List<String[]> list = null;
		String[] outputs = new String[]{"r","xm", "sxdw", "sxjyfs", "sxdwdz", "sxdwxz",
				"sxbm","sxgw","ldbh","sstj","jtzk","xslxdh","sxdwbdqk"};
		String[] colLikeList = new String[]{};
		String[] colList = new String[]{"bjdm"};
		
		MakeQuery makeQuery = new MakeQuery();
		
		try {
			makeQuery.makeQuery(colList, colLikeList, model);
		
			String query = makeQuery.getQueryString();
			String[] inputs = makeQuery.getInputList();
			
			sql.append("select rownum r,a.xm,b.*,b.sjhm|| case when b.sjhm is not null and b.lxdh is not null " +
					"then '/'else '' end ||b.lxdh xslxdh from view_xsbfxx a left join xg_jygl_njjs_dxsxxxb b on a.xh=b.xh ")
				.append(query);
			
			list = DAO.getInstance().rsToVator(sql.toString(), inputs, outputs);
		
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return list;
	}
	
	/**
	 * 保存学生的家庭地址和联系电话
	 * @param xh
	 * @param jtdz
	 * @param lxdh
	 * @return
	 */
	public boolean saveXsJtdzAndLxdh(String xh,String jtdz,String lxdh){
		boolean b=false;
		try {
			DAO dao = DAO.getInstance();
			String sql=null;
			if(!Base.isNull(lxdh)){
				lxdh=lxdh.trim();
				if(lxdh.length()==11&&lxdh.indexOf("-")==0&&!lxdh.startsWith("0")){
					sql="update xsxxb set sjhm=? where xh=?";
				}else{
					sql="update xsxxb set lxdh=? where xh=?";
				}
				b=dao.runUpdate(sql, new String[]{lxdh,xh});
			}
			sql="select count(1) num from xsfzxxb where xh=?";
			String num=dao.getOneRs(sql, new String[]{xh}, "num");
			if("0".equals(num)){
				b=dao.runUpdate("insert into xsfzxxb (xh) values (?)", new String[]{xh});
			}
			if(Base.isNull(lxdh)){
				sql="update xsfzxxb set jtszd=? where xh=?";
				b=dao.runUpdate(sql, new String[]{jtdz,xh});
			}
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		return b;
	}

}
