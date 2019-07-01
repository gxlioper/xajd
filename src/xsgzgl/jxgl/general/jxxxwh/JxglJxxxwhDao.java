package xsgzgl.jxgl.general.jxxxwh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.comm.search.SearchModel;
import xgxt.comm.search.SearchService;
import xgxt.utils.CommonQueryDAO;

/**
 * 军训管理-基础信息维护-军训信息维护
 * @author yeyipin
 * @since 2012.10.10
 */
public class JxglJxxxwhDao extends DAO{

	/**
	 * 军训信息查询
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public ArrayList<String[]> jxxxCx(JxglJxxxwhForm model) throws Exception{
		// 高级查询MODEL
		SearchModel searchModel = model.getSearchModel();
		String[] colList = new String[] { "jxid", "jxmc", "cjnj", "kssj", "jssj", "cxrs", "hxrs", "zt" };
		// 高级查询条件
		String searchTj = SearchService.getSearchTj(searchModel);
		String[] inputV = SearchService.getTjInput(searchModel);
		StringBuilder sql = new StringBuilder();
		sql.append("select a.*,rownum r from (select a.*,(select count(1) from xg_jxgl_jxcxmdb b where a.jxid = b.jxid and b.cxqk = 'cx') cxrs,");
		sql.append("(select count(1) from xg_jxgl_jxcxmdb b where a.jxid = b.jxid and b.cxqk = 'hx') hxrs,");
		sql.append("case when a.jxzt='start' then '运行' else '停止' end as zt from xg_jxgl_jxxxwhb a order by cjsj desc)a where 1=1");
		// ====================SQL拼装 end================================
		return CommonQueryDAO.commonQuery(sql.toString(), searchTj, inputV, colList, model);
	}

	/**
	 * 获得参加年级
	 * @return
	 */
	public List<HashMap<String, String>> getCjnj() {
		String sql = "select distinct nj cjnj from view_xsjbxx where nj is not null and nj is not null  order by nj desc";
		String[] inputValue = new String[]{};
		String[] outputValue = new String[]{"cjnj"};
		return getList(sql, inputValue, outputValue);
	}
	
	/**
	 * 获得建制等级
	 * @return
	 */
	public List<HashMap<String, String>> getJzdj() {
		String sql = "select dm,mc,dj,bm,sfqy,sfyzjd from xg_jxgl_new_jxjzdjb order by dm";
		String[] inputValue = new String[]{};
		String[] outputValue = new String[]{"dm","mc","dj","bm","sfqy","sfyzjd"};
		return getList(sql, inputValue, outputValue);
	}
	
	/**
	 * 更新建制等级
	 * @param sql
	 * @return
	 * @throws Exception
	 */
	public boolean dao_updateJzdj(String sql)throws Exception {
		DAO dao = DAO.getInstance();
		return dao.runUpdateTab(sql);
	}
	
	public int[] dao_batchUpdateJzdj(String[] sql)throws Exception {
		DAO dao = DAO.getInstance();
		return dao.runBatch(sql);
	}
	
	/**
	 * 获得重复项个数
	 * @param model
	 * @return
	 */
	public String getCount(JxglJxxxwhForm model) {
		//增加
		String sql = "select count(1) num from xg_jxgl_jxxxwhb where jxmc = ?";
		String[] inputValue = new String[]{model.getJxmc()};
		//修改
		if(model.getJxid()!=null && !"".equalsIgnoreCase(model.getJxid())){
			sql = "select count(1) num from xg_jxgl_jxxxwhb where jxmc = ? and jxid <> ?";
			inputValue = new String[]{model.getJxmc(),model.getJxid()};
		}
		return getOneRs(sql, inputValue, "num");
	}
	
	


	/**
	 * 获得一个GUID
	 * @return
	 */
	public String getGuid() {
		String sql = "select sys_guid() guid from dual";
		return getOneRs(sql, new String[]{}, "guid");
	}
	
	
	/**
	 * 新增一条军训信息
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean insertJxxx(JxglJxxxwhForm model) throws Exception {
		String jxid = model.getJxid();
		String jxmc = model.getJxmc();
		String kssj = model.getKssj();
		String jssj = model.getJssj();
		String cjnj = model.getCjnj();
		String jxzt = model.getJxzt();
		String sql = "insert into xg_jxgl_jxxxwhb(jxid,jxmc,kssj,jssj,cjnj,jxzt)values(?,?,?,?,?,?)";
		String[] inputValue = new String[]{jxid,jxmc,kssj,jssj,cjnj,jxzt};
		boolean flag = insert(sql,inputValue);
		if(flag){
			sql = "select count(1) num from xg_jxgl_jxcjpzb where jxid <> 'comm'";
			inputValue = new String[]{};
			String num = getOneRs(sql, inputValue, "num");
			if(!"0".equalsIgnoreCase(num)){
				sql = "insert into xg_jxgl_jxcjpzb(jxid,zd,zdm,zdlx,source_table,option_dm,option_mc,xssx,cjsj)" +
						"select ?,zd,zdm,zdlx,source_table,option_dm,option_mc,xssx,to_char(sysdate,'yyyy-mm-dd hh24:mi:ss') from xg_jxgl_jxcjpzb where cjsj =" +
						"(select max(cjsj) from xg_jxgl_jxcjpzb where jxid <>'comm')";
				inputValue = new String[]{jxid};
				flag = insert(sql, inputValue);
			}
		}
		return flag;
	}
	
	/**
	 * 更新一条军训信息
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	public boolean updateJxxx(JxglJxxxwhForm model) throws Exception {
		String jxid = model.getJxid();
		String jxmc = model.getJxmc();
		String kssj = model.getKssj();
		String jssj = model.getJssj();
		String cjnj = model.getCjnj();
		String jxzt = model.getJxzt();
		String sql = "update xg_jxgl_jxxxwhb set jxmc=?,kssj=?,jssj=?,cjnj=?,jxzt=? where jxid=?";
		String[] inputValue = new String[]{jxmc,kssj,jssj,cjnj,jxzt,jxid};
		return runUpdate(sql, inputValue);
	}

	
	/**
	 * 更改其他军训信息状态为停止
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	public boolean stopJxxx(JxglJxxxwhForm model) throws Exception {
		String jxid = model.getJxid();
		String sql = "update xg_jxgl_jxxxwhb set jxzt='stop' where jxid<>?";
		String[] inputValue = new String[]{jxid};
		return runUpdate(sql, inputValue);
	}
	
	
	
	/**
	 * 获得军训信息
	 * @param model
	 * @return
	 */
	public HashMap<String, String> getJxxx(JxglJxxxwhForm model) {
		String sql = "select * from xg_jxgl_jxxxwhb where jxid = ?";
		String[] inputValue = new String[]{model.getJxid()};
		return getMapNotOut(sql, inputValue);
	}
	/**
	 * 
	 * @描述:获取启用的军训信息
	 * @作者：夏夏[工号：1104]
	 * @日期：2015-7-8 上午11:16:34
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getQyJxxx() {
		String sql = "select * from xg_jxgl_jxxxwhb where jxzt = 'start'";
		String[] inputValue = new String[]{};
		return getMapNotOut(sql, inputValue);
	}

	
	/**
	 * 获得重复项个数
	 * @param model
	 * @return
	 */
	public String getCountJz(JxglJxxxwhForm model) {
		String sql = "select count(1) num from xg_jxgl_jxjzwhb where SJID = ?";
		String[] inputValue = new String[]{model.getJxid()};
		return getOneRs(sql, inputValue, "num");
	}
	
	
	/**
	 * 批量删除军训信息
	 * @param params
	 * @return
	 * @throws Exception 
	 */
	public boolean jxxxSc(List<String[]> params) throws Exception {
		String sql = "delete from xg_jxgl_jxxxwhb where jxid=?";
		int[] result = runBatch(sql, params);
		boolean flag = checkBatchResult(result);
		//军训名单的数据
		if(flag){
			sql="delete from XG_JXGL_JXCXMDB where jxid = ?";
		}
		result = runBatch(sql,params);
		flag = checkBatchResult(result);
		//军训成绩配置中的数据
		if(flag){
			sql="delete from xg_jxgl_jxcjpzb where jxid = ?";
		}
		result = runBatch(sql,params);
		flag = checkBatchResult(result);
		return flag;
	}

	
	/**
	 * 军训信息操作（更改状态）
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	public boolean jxxxCz(JxglJxxxwhForm model) throws Exception {
		String sql = "update xg_jxgl_jxxxwhb set jxzt=? where jxid=? ";
		String[] inputValue = new String[]{model.getJxzt(),model.getJxid()};
		return runUpdate(sql, inputValue);
	}

	
	/**
	 * 军训名单查询
	 * @param model
	 * @param request 
	 * @return
	 */
	public ArrayList<String[]> jxmdCx(JxglJxxxwhForm model, HttpServletRequest request) throws Exception{
		// 高级查询MODEL
		SearchModel searchModel = model.getSearchModel();
		String[] colList = new String[] { "mdid", "xh", "xm", "nj", "xymc", "zymc", "bjmc", "cxqk" };
		// 高级查询条件
		String searchTj = SearchService.getSearchTj(searchModel);
		String[] inputV = SearchService.getTjInput(searchModel);
		//权限控制 	
		SearchService searchService=new SearchService();
		String searchTjQx = searchService.getSearchTjByUser(request, "","xydm", null); 	//学院用户;	
		StringBuilder sql = new StringBuilder();
		sql.append("select a.*,rownum r from (select a.mdid,a.jxid,case when a.cxqk='cx' then '参训' when a.cxqk='hx' then '缓训' when a.cxqk='mx' then '免训' end as cxqk,");
		sql.append("b.* from XG_JXGL_JXCXMDB a left join view_xsjbxx b on a.xh = b.xh where a.jxid='"+model.getJxid()+"' order by b.bjdm,b.xh )a where 1=1");
		// ====================SQL拼装 end================================
		return CommonQueryDAO.commonQuery(sql.toString(), searchTj+searchTjQx, inputV, colList, model);

	}

	
	
	/**
	 * 获得军训人数
	 * @param model
	 * @return
	 */
	public HashMap<String, String> getJxrs(JxglJxxxwhForm model) {
		String[] cj = model.getCjnj().split("!!@@!!");
		String cjnj = "";
		for(int i = 0; i < cj.length;i++){
			cjnj +=" or nj = '"+cj[i]+"' ";
		}
		//未参训人数（包括参训，缓训，免训）
		String sql  = "select count(1) njrs from view_xsjbxx a where not exists " +
				"(select 1 from xg_jxgl_jxcxmdb b where a.xh = b.xh) and (nj = null "+cjnj+")";
		String njrs = getOneRs(sql, new String[]{}, "njrs");
		//往届缓训人数
		String hxrs = "0";
		if("yes".equalsIgnoreCase(model.getSfhx())){
			sql = "select count(1) hxrs from (select distinct xh from xg_jxgl_jxcxmdb a where jxid <> ? " +
					"and cxqk = 'hx' and not exists (select 1 from xg_jxgl_jxcxmdb b where a.xh =b.xh and b.cxqk = 'cx'))";
			hxrs = getOneRs(sql, new String[]{model.getJxid()}, "hxrs");
		}
		//往届免训人数
		String mxrs = "0";
		if("yes".equalsIgnoreCase(model.getSfmx())){
			sql = "select count(1) mxrs from (select distinct xh from xg_jxgl_jxcxmdb a where jxid <> ? " +
					"and cxqk = 'mx' and not exists (select 1 from xg_jxgl_jxcxmdb b where a.xh =b.xh and b.cxqk = 'cx'))";
			mxrs = getOneRs(sql, new String[]{model.getJxid()}, "mxrs");
		}
		//参训人数（包括参训，缓训，免训）
		sql = "select count(1) cjrs from xg_jxgl_jxcxmdb where cxqk = 'cx' and jxid = ? ";
		String cjrs = getOneRs(sql, new String[]{model.getJxid()}, "cjrs");
		HashMap<String,String > rs = new HashMap<String,String>();
		rs.put("njrs", njrs);
		rs.put("hxrs", hxrs);
		rs.put("mxrs", mxrs);
		rs.put("cjrs", cjrs);
		return rs;
	}
	
	
	/**
	 * 获得重复项个数
	 * @param model
	 * @return
	 */
	public String getCountMd(JxglJxxxwhForm model) {
		String sql = "select count(1) num from xg_jxgl_jxjzmdb a where exists(select 1 from xg_jxgl_jxcxmdb b where mdid = ? and a.xh = b.xh ) ";
		String[] inputValue = new String[]{model.getPkValue()};
		return getOneRs(sql, inputValue, "num");
	}

	
	
	/**
	 * 增加军训名单
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	public boolean insertJxmd(JxglJxxxwhForm model) throws Exception {
		String[] nj = model.getCjnj().split("!!@@!!");
		StringBuilder cjnj = new StringBuilder(); 
		for(int i=0;i < nj.length;i++){
			cjnj.append("or nj='");
			cjnj.append(nj[i]);
			cjnj.append("' ");
		}
		String sql = "insert into xg_jxgl_jxcxmdb (jxid,xh,cxqk) select '"+model.getJxid()+
				"' jxid,xh,'cx'cxqk from view_xsjbxx a where not exists (select 1 from xg_jxgl_jxcxmdb b " +
				"where a.xh = b.xh ) and (a.nj = null "+cjnj.toString()+")";
		return insert(sql, new String[]{});
	}
	

	/**
	 * 复制缓训(免训)人员为参训
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	public boolean copyJxmd(JxglJxxxwhForm model) throws Exception {
		String sql = "insert into xg_jxgl_jxcxmdb (jxid,xh,cxqk) select distinct ? jxid,xh,'cx' cxqk from xg_jxgl_jxcxmdb a where jxid <> ? and cxqk = ? and not exists (select 1 from xg_jxgl_jxcxmdb b where a.xh =b.xh and b.cxqk = 'cx')";
		return insert(sql, new String[]{model.getJxid(),model.getJxid(),model.getCxqk()});
	}

	
	/**
	 * 批量删除军训名单
	 * @param params
	 * @return
	 * @throws Exception 
	 */
	public boolean jxmdSc(List<String[]> params) throws Exception {
		String sql = "delete from xg_jxgl_jxcxmdb where mdid=?";
		int[] result = runBatch(sql, params);
		return checkBatchResult(result);
	}

	
	/**
	 * 参训情况操作
	 * @param params
	 * @return
	 */
	public boolean cxqkCz(List<String[]> params) throws Exception {
		String sql = "update xg_jxgl_jxcxmdb set cxqk=?, ly='', fj='' where mdid=?";
		int[] result = runBatch(sql, params);
		return checkBatchResult(result);
	}
	
	/**
	 * 获取军训信息维护model  yjd
	 * @param model
	 * @return
	 */
	public HashMap<String, String> getJxxxwhModel(JxglJxxxwhForm model){
		DAO dao = DAO.getInstance();
		StringBuffer sql=new StringBuffer();
		sql.append(" select jxid, jxmc, to_char(to_date(kssj,'YYYY-MM-DD'),'YYYY-MM-DD')kssj, to_char(to_date(jssj,'YYYY-MM-DD'),'YYYY-MM-DD')jssj, cjnj, jxzt, cjsj ");
		sql.append(" from xg_jxgl_jxxxwhb ");
		sql.append(" where jxzt = ? ");
		
		String[] inputValue = new String[] { model.getJxzt() };
		return dao.getMapNotOut(sql.toString(), inputValue);
	}

	/**
	 * 获取军训信息维护model  yjd
	 * @param model
	 * @return
	 */
	public List<HashMap<String, String>> getJxxxList(){
		DAO dao = DAO.getInstance();
		StringBuffer sql = new StringBuffer();
		
		sql.append(" select jxid, jxmc, kssj, jssj, cjnj, jxzt, cjsj ");
		sql.append(" from xg_jxgl_jxxxwhb ");
		sql.append(" order by cjnj desc ");
		
		String[] inputValue = new String[] {  };
		String[] outputValue = new String[] { "jxid","jxmc","kssj","jssj","cjnj","jxzt","cjsj" };
		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				inputValue, outputValue);
		return list;
	}
	
	/**
	 * 获取军训信息维护根据军训id  model  yjd
	 * @param model
	 * @return
	 */
	public HashMap<String, String> getJxxxwhByJxidModel(JxglJxxxwhForm model){
		DAO dao = DAO.getInstance();
		StringBuffer sql=new StringBuffer();
		sql.append(" select jxid, jxmc, kssj, jssj, cjnj, jxzt, cjsj ");
		sql.append(" from xg_jxgl_jxxxwhb ");
		sql.append(" where jxid = ? ");
		
		String[] inputValue = new String[] { model.getJxid() };
		String[] outputValue = new String[] { "jxid","jxmc","kssj","jssj","cjnj","jxzt","cjsj" };
		HashMap<String, String> rs=dao.getMap(sql.toString(), inputValue, outputValue);
		return rs;
	}
	
	
	/**
	 * 军训名单查询
	 * @param model
	 * @param request 
	 * @return
	 */
	public List<HashMap<String,String>> getAlljxmdCx(JxglJxxxwhForm model, HttpServletRequest request) throws Exception{
		// 高级查询MODEL
		SearchModel searchModel = model.getSearchModel();
		String[] colList = new String[] { "mdid", "xh", "xm", "nj", "xymc", "zymc", "bjmc", "cxqk" };
		// 高级查询条件
		String searchTj = SearchService.getSearchTj(searchModel);
		String[] inputV = SearchService.getTjInput(searchModel);
		//权限控制 	
		SearchService searchService=new SearchService();
		String searchTjQx = searchService.getSearchTjByUser(request, "","xydm", null); 	//学院用户;	
		StringBuilder sql = new StringBuilder();
		sql.append("select a.*,rownum r from (select a.mdid,a.jxid,case when a.cxqk='cx' then '参训' when a.cxqk='hx' then '缓训' when a.cxqk='mx' then '免训' end as cxqk,");
		sql.append("b.* from XG_JXGL_JXCXMDB a left join view_xsjbxx b on a.xh = b.xh where a.jxid='"+model.getJxid()+"' order by b.bjdm,b.xh )a where 1=1");
		// ====================SQL拼装 end================================
		return CommonQueryDAO.commonQueryforExportList(sql.toString(), searchTj+searchTjQx, inputV, colList, model);
	}
	
	
	/**
	 * 
	 * @描述: 更新状态（缓训，免训）
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-8-29 下午05:56:56
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean upJxxx(JxglJxxxwhForm model) throws Exception {
		String sql = "update xg_jxgl_jxcxmdb set cxqk=?,ly=?,fj=? where jxid=? and xh=? ";
		String[] inputValue = new String[]{model.getCxqk(),model.getLy(),model.getFj(),model.getJxid(),model.getXh()};
		return runUpdate(sql, inputValue);
	}
	
	/**
	 * 
	 * @描述: 取消军训资格
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-8-29 下午05:57:21
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * int 返回类型 
	 * @throws
	 */
	public int scJxxx(JxglJxxxwhForm model) throws Exception {
		String sql = "delete from xg_jxgl_jxcxmdb where jxid=? and xh=? ";
		String[] inputValue = new String[]{model.getJxid(),model.getXh()};
		return runDelete(sql, inputValue);
	}
	
	/**
	 * 
	 * @描述: 被取消的人员插入新表
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-8-29 下午05:57:55
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean insqxJxxx(JxglJxxxwhForm model) throws Exception {
		String sql = "insert into xg_jxgl_qxjxzgb (jxid,xh,ly,fj) values(?,?,?,?)";
		return insert(sql, new String[]{model.getJxid(),model.getXh(),model.getLy(),model.getFj()});
	}
	
	/**
	 * 
	 * @描述: 取理由附件
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-8-30 上午10:28:04
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getJxxxView(JxglJxxxwhForm model) {
		String sql = "select ly,fj from xg_jxgl_jxcxmdb where jxid = ? and xh = ?";
		String[] inputValue = new String[]{model.getJxid(),model.getXh()};
		return getMapNotOut(sql, inputValue);
	}
	
	/*
	 * 申请表取值
	 */
	public HashMap<String, String> getJxxh(JxglJxxxwhForm model) {
		String sql = "select xh from xg_jxgl_jxcxmdb where mdid = ?";
		String[] inputValue = new String[]{model.getPkValue()};
		return getMapNotOut(sql, inputValue);
	}
	
	/*
	 * 申请表取值
	 */
	public HashMap<String, String> getJxly(JxglJxxxwhForm model) {
		String sql = "select ly from xg_jxgl_jxcxmdb where mdid = ?";
		String[] inputValue = new String[]{model.getPkValue()};
		return getMapNotOut(sql, inputValue);
	}

}
