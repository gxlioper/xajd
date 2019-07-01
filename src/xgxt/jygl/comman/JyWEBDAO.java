package xgxt.jygl.comman;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.utils.Pages;

public class JyWEBDAO extends DAO{

	DAO dao = DAO.getInstance();
	
	/**
	 * 就业网导航菜单
	 * @param userType
	 * @return
	 */
	public List<HashMap<String,String>> getJywebMenus(String userType){
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select * from (");
		sql.append("select a.dm,b.mc,b.mkly,a.yhqx,to_number(b.xssx) xssx ");
		sql.append("from dmk_jyweb_yhqxb a left join dmk_jyweb_gnmkdmb b ");
		sql.append("on a.dm=b.dm where a.dm like 'J__' ");
		
		
		if (!Base.isNull(userType)) {
			sql.append(" and (userType='");
			sql.append(userType);
			sql.append("' or userType=' ')");
		} else {
			sql.append("and a.userType = ' ' ");
		}
		sql.append(") order by xssx asc");
		
		return dao.getList(sql.toString(), new String[] {}, new String[] {"dm","mc","mkly","yhqx","xssx"});
	}


	/**
	 * 企业目录
	 * @return
	 * @throws SQLException 
	 */
	protected List<HashMap<String, String>> getDwList() throws SQLException {
		
		String sql = "select * from jygl_dwxxb where shzt='通过' order by zcsj desc";
		
		return dao.getList(sql, new String[] {}, new String[] {"yhm","dwmc"});
	}

	
	/**
	 * 就业网新闻
	 * DWR也要调用 的~
	 * @return
	 */
	public List<HashMap<String, String>> getNews(String wjlx){
		String sql = "select a.wjbt,a.tplj,to_char(to_date(a.fbsj,'yyyymmdd'),'yyyy-mm-dd') fbsj,rowid from jygl_zcwjb a where wjlx=? order by fbsj desc";
		
		return dao.getList(sql, new String[] {wjlx}, new String[] {"wjbt","fbsj","rowid","tplj"});
		
	}
	
	
	/**
	 * 联系方式
	 * @return
	 */
	protected HashMap<String, String> getLxfs(){
		
		String sql = "select * from jyweb_lxfsb where xxdm=(select xxdm from xtszb)";
		
		return dao.getMapNotOut(sql, new String[] {});
	}
	

	/**
	 * 人才推荐
	 * @return
	 */
	protected List<HashMap<String, String>> getRctjList(){
		String sql = "select * from VIEW_JYGL_JYTJB  order by lrsj desc";
		
		return dao.getList(sql, new String[] {}, new String[] {"xm","xb","xl","zymc"});
	}
	
	
	/**
	 * 单位用户登录验证
	 * @param userName
	 * @param password
	 * @return
	 */
	protected String[] dwLogin(String userName ,String password) {
		String sql = "select dwmc xm,''szbm,shzt from jygl_dwxxb where yhm=? and mm=? ";
		
		return dao.getOneRs(sql, new String[] {userName,password}, new String[] {"xm","szbm","shzt"});
	}
	
	
	/**
	 * 学院用户登录验证
	 * @param userName
	 * @param password
	 * @return
	 */
	protected String[] xyLogin(String userName,String password) {
		String sql = "select * from (select a.*,'' shzt,(select zmc from yhzb where zdm=a.zdm) zmc from yhb a ) where zmc!='就业网' and yhm=? and kl=?";
		
		return dao.getOneRs(sql, new String[] {userName,password}, new String[] {"xm","szbm","shzt"});
	}
	
	
	/**
	 * 学生用户登录验证
	 * @param userName
	 * @param password
	 * @return
	 */
	protected String[] stuLogin(String userName, String password) {
		String sql = "select xm,bmdm szbm,'' shzt from view_xsjbxx where xh=? and mm=?";
		
		return dao.getOneRs(sql, new String[] {userName,password}, new String[] {"xm","szbm","shzt"});
	}
	
	
	/**
	 * 就业网首页布局
	 * @return
	 */
	public List<HashMap<String, String>> getLayOut(){
		String sql = "select * from jyweb_layout";
		
		return dao.getList(sql, new String[] {}, new String[] {"mkmc","mkbh"});
		
	}
	
	
	/**
	 * 新闻详细信息
	 * @param rowid
	 * @return
	 */
	protected HashMap<String, String> getNewInfo(String pkValue) {
		String sql = "select * from jygl_zcwjb where rowid=?";
		
		return dao.getMap(sql, new String[] {pkValue}, new String[] {"wjbt","wjlx","fbr","fbsj","wjnr","readtimes","tplj"});
	}
	
	
	/**
	 * 新闻详细信息
	 * @param rowid
	 * @return
	 */
	protected HashMap<String, String> getNewInfoByWjbt(String pkValue) {
		String sql = "select * from jygl_zcwjb where  wjbt=? ";
		
		return dao.getMap(sql, new String[] {pkValue}, new String[] {"wjbt","wjlx","fbr","fbsj","wjnr","readtimes","tplj"});
	}
	
	
	/**
	 * 修改浏览次数
	 * @param pkValue
	 * @return
	 * @throws Exception 
	 */
	protected boolean setReadTimes(String pkValue) throws Exception {
		String sql = "update jygl_zcwjb set readtimes=readtimes+1 where rowid=?";
		return dao.runUpdate(sql, new String[] {pkValue});
	}
	
	
	/**
	 * 修改浏览次数
	 * @param pkValue
	 * @return
	 * @throws Exception 
	 */
	protected boolean setReadTimesByWjbt(String pkValue) throws Exception {
		String sql = "update jygl_zcwjb set readtimes=readtimes+1 where wjbt=? ";
		return dao.runUpdate(sql, new String[] {pkValue});
	}
	
	
	/**
	 * 图片链接
	 * @return
	 */
	protected List<HashMap<String, String>> getTplj(){
		String sql = "select * from jyweb_yqljb where tpdz >' ' order by xssx desc";
		
		return dao.getList(sql, new String[] {}, new String[] {"ljmc","ljwz","tpdz"});
	}
	
	
	/**
	 * 文字链接
	 * @return
	 */
	protected List<HashMap<String, String>> getWzlj(){
		String sql = "select * from jyweb_yqljb where tpdz is null order by xssx desc";
		
		return dao.getList(sql, new String[] {}, new String[] {"ljmc","ljwz"});
	}
	
	
	/**
	 * 下载专区
	 * @return
	 */
	protected List<HashMap<String, String>> getFiles(){
		String sql = "select * from jyweb_wjglb order by scsj desc";
		
		return dao.getList(sql, new String[] {}, new String[] {"guid","wjm"});
	}
	
	
	/**
	 * 文件记录保存
	 * @param form
	 * @return
	 * @throws Exception
	 */
	protected boolean saveWjsc(JyglForm form) throws Exception {
		String sql = "insert into jyweb_wjglb (guid,wjm,scr,wjlj,wjsm) values (?,?,?,?,?)";
		
		return dao.runUpdate(sql, new String[] {form.getGuid(),form.getWjm(),form.getScr(),form.getWjlj(),form.getWjsm()});
	}
	
	
	/**
	 * 文件浏览次数加1
	 * @param pkValue
	 * @throws Exception
	 */
	public boolean setFileLlcs(String pkValue) throws Exception {
		String sql = "update jyweb_wjglb set llcs=llcs+1 where guid=?";
		return dao.runUpdate(sql, new String[] {pkValue});
	}
	
	
	/**
	 * 文件下载次数加1
	 * @param pkValue
	 * @throws Exception
	 */
	public boolean setFileXzcs(String pkValue) throws Exception {
		String sql = "update jyweb_wjglb set xzcs=xzcs+1 where guid=?";
		return dao.runUpdate(sql, new String[] {pkValue});
	}
	
	
	/**
	 * 获取文件绝对路径
	 * @param pkValues
	 * @return
	 * @throws SQLException
	 */
	protected String[] getFiles(String[] pkValues) throws SQLException {

		StringBuilder sb = new StringBuilder();

		sb.append("select * from jyweb_wjglb where guid in (");
		for (int i = 0; i < pkValues.length; i++) {
			sb.append("'");
			sb.append(pkValues[i]);
			sb.append("'");

			if (i == pkValues.length - 1) {
				sb.append(")");
			} else {
				sb.append(",");
			}

		}

		return dao.getArray(sb.toString(), new String[] {}, "wjlj");

	}


	/**
	 * 才市预告
	 * @return
	 */
	protected List<HashMap<String, String>> getZphList(){
		String sql = "select * from (select zphbt||fbr||fbsj pkValue,zplxdm,zplx,zphbt,to_char(to_date(fbsj,'yyyymmdd'),'yyyy-mm-dd') fbsj from xg_view_jyweb_xyzph where shzt='通过') order by fbsj desc";
		return dao.getList(sql, new String[] {}, new String[] {"pkValue","zplx","zplxdm","zphbt","fbsj"});
	}
	
	
	/**
	 * 获取就业网title
	 * @param path
	 * @return
	 */
	protected String getTitle(String path) {
		String sql = "select * from dmk_jyweb_gnmkdmb where mkly=? and rownum=1";

		return dao.getOneRs(sql, new String[] { path }, "mc");
	}

	
	/**
	 * 获取招聘信息
	 * @return
	 */
	protected List<HashMap<String, String>> getZpxx(){
		String sql = "select distinct yhm,gsmc from (select zpzw||gsmc||fbsj pkValue,yhm,gsmc,to_char(to_date(fbsj,'yyyymmdd'),'yyyy-mm-dd') fbsj,zpzw from view_jygl_zpxx where xxsh='通过') ";
		return dao.getList(sql, new String[]{}, new String[]{"yhm","gsmc"} );
	}
	
	
	/**
	 * 下拉列表
	 * @param lx
	 * @return
	 */
	public List<HashMap<String, String>> getSelectList(String lx) {
		String[] dm = null;
		String[] mc = null;
		
		if ("shzt".equals(lx)) {
			dm = new String[] { "未审核", "通过","不通过","退回","需重审"};
			mc = dm;
		} else if ("shjg".equals(lx)){
			dm = new String[] { "通过", "不通过"};
			mc = dm;
		} else if ("jyweb".equals(lx)) {
			dm = new String[] { "1","2","3","4","5","6" };
			mc = new String[] { "就业新闻","图片新闻","通知公告","就业政策","生源信息","就业创业技巧指导" };
		} else if("xb".equals(lx)){
			dm = new String[] { "男女不限", "男", "女"};
			mc = dm;
		} else if ("gzxz".equals(lx)) {
			dm = new String[] {"全职","兼职"};
			mc = dm;
		} else if ("bjqnjyweb".equalsIgnoreCase(lx)){
			dm = new String[] { "1","2","3","4","6" };
			mc = new String[] { "新闻资讯","图片新闻", "通知公告","就业手续服务","就业指导服务" };
		}
		return dao.arrayToList(dm, mc);
	}
	
	
	/***
	 * 当前时间
	 * @return
	 */
	public String getNow() {
		
		String sql = "select to_char(sysdate,'yyyymmdd') now from dual";
		
		return dao.getOneRs(sql, new String[] {}, "now");
	}
	
	
	/**
	 * 管理员登录 
	 * @param userName
	 * @param password
	 * @return
	 */
	public String[] adminLogin(String userName,String password) {
		String sql = "select a.*,''shzt from (select a.*, (select zmc from yhzb where zdm=a.zdm) zmc from yhb a ) a where zmc='就业网' and yhm=? and kl=?";
		
		return dao.getOneRs(sql, new String[] {userName,password}, new String[] {"xm","szbm","shzt"});
	}
	
	
	/**
	 * 获取指定单位的招聘信息
	 * @param pkValue
	 * @return
	 */
	protected List<HashMap<String, String>> getZpxx(String pkValue) {
		String sql = "select * from view_jygl_zpxx where yxqx>=to_number(to_char(sysdate,'yyyymmdd')) and gsmc=(select dwmc from jygl_dwxxb where yhm=?) and xxsh='通过' order by fbsj desc";

		return dao.getList(sql, new String[] { pkValue }, new String[] { "zpzw","zpzwmc",
				"zprs", "wyyq", "gwxz", "xlyq", "fbsj","zpsj","zpdd" });
	}
	
	
	/**
	 * 保存友情链接
	 * @param values
	 * @return
	 * @throws Exception
	 */
	protected boolean saveYqlj(String[] values) throws Exception {
		String sql = "insert into jyweb_yqljb(ljmc,ljwz,tpdz,xssx) values(?,?,?,?)";
		
		return dao.runUpdate(sql, values);
	}
	
	
	/**
	 * 友情链接单个删除
	 * @param values
	 * @return
	 * @throws Exception
	 */
	protected boolean delYqlj(String pkValue) throws Exception {
		String sql = "delete from jyweb_yqljb where ljbh=?";
		
		return dao.runUpdate(sql, new String[] {pkValue});
	}
	
	
	/**
	 * 获取链接图片绝对路径
	 * @param pkValues
	 * @return
	 * @throws SQLException
	 */
	protected String[] getTps(String[] pkValues) throws SQLException {
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("select * from jyweb_yqljb where ljbh in (");
		 for (int i = 0 ; i<pkValues.length;i++) {
			 sb.append("'");
			 sb.append(pkValues[i]);
			 sb.append("'");
			 
			 if (i==pkValues.length-1) {
				 sb.append(")");
			 } else {
				 sb.append(",");
			 }
			 
		 }
		 
		 return dao.getArray(sb.toString(), new String[] {}, "tpdz");
		 
	}
	
	
	/**
	 * 获取所有的审核通过公司名称
	 * @return
	 */
	public List<HashMap<String, String>> getAllGsmc(){
		String sql = "select dwmc from jygl_dwxxb where shzt='通过'";
		return dao.getList(sql, new String[]{}, new String[]{"dwmc"});
	}
	
	
	/**
	 * 招聘会浏览次数加1 
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	protected boolean setZphLlcs(String pkValue) throws Exception {
		String sql = "update jyweb_xyzph set llcs=llcs+1 where zphbt||fbr||fbsj=?";
		
		return dao.runUpdate(sql, new String[] {pkValue});
	}
	
	
	/**
	 * 学生岗位收藏、浏览、投递简历
	 * @param pkValue
	 * @param xh
	 * @return
	 * @throws Exception
	 */
	public boolean saveStuJobs(String pkValue,String xh,String joblb) throws Exception {
		StringBuilder sql = new StringBuilder();
		
		sql.append("insert into jyweb_stujobs (xh,dwmc,gwmc,gwfbsj,joblb) select '");
		sql.append(xh);
		sql.append("',gsmc,zpzw,fbsj,'");
		sql.append(joblb);
		sql.append("' from jygl_zpxxb where zpzw||gsmc||fbsj='");
		sql.append(pkValue);
		sql.append("'");
		
		return dao.runUpdate(sql.toString(), new String[] {});
	}
	
	
	/**
	 * 删除浏览过的岗位
	 * @param pkValue
	 * @param xh
	 * @return
	 * @throws Exception
	 */
	public boolean deleteStuJobs(String pkValue,String xh) throws Exception {
		StringBuilder sql = new StringBuilder();
		
		sql.append("delete from jyweb_stujobs where xh||dwmc||gwmc||joblb||tdrq ");
		sql.append("= ?||(select gsmc from jygl_zpxxb where zpzw||gsmc||fbsj=?)");
		sql.append("|| (select zpzw from jygl_zpxxb where zpzw||gsmc||fbsj=?)");
		sql.append("||'浏览'||(select to_char(sysdate,'yyyymmdd') from dual)");

		return dao.runUpdate(sql.toString(), new String[] {xh,pkValue,pkValue});
	}
	
	
	/**
	 * 获取学生简历 个数
	 * @param xh
	 * @return
	 */
	public String getStuResume(String xh) {
		String sql = "select count(*) count from jygl_jytjb where xh=?";
		
		return dao.getOneRs(sql, new String[] {xh}, "count");
	}
	
	
	/**
	 * 根据用户类型获取操作菜单
	 * @param userType
	 * @return
	 */
	public List<HashMap<String,String>> getMenus(String userType){
		StringBuilder sql = new StringBuilder();
		
		sql.append("select * from (");
		sql.append("select a.dm,b.mc,b.mkly,a.yhqx,to_number(b.xssx) xssx ");
		sql.append("from dmk_jyweb_yhqxb a left join dmk_jyweb_gnmkdmb b ");
		sql.append("on a.dm=b.dm where a.dm like 'J____' and userType=?");
		sql.append(") order by xssx asc");
		
		return dao.getList(sql.toString(), new String[] {userType}, new String[] {"dm","mc","mkly","yhqx","xssx"});
	}
	
	
	/**
	 * 获取导出的全部列
	 * @param tableName
	 * @return
	 */
	protected String[] getColumn(String tableName) {
		String sql = "select * from "+tableName;
		
		return dao.getColumnName(sql);
	}
	
	
	/**
	 * 检测用户名或者单位名称是否存在
	 * @param fields
	 * @param value
	 * @return
	 */
	public boolean isUserExists(String fields, String value){
		String sql = "select count(*) count from jygl_dwxxb where " + fields + "=?";
		String[] rs = dao.getOneRs(sql, new String[]{value}, new String[]{"count"});

		int count = Integer.parseInt(rs[0]);
		boolean flag = false;
		
		if(count>0){
			flag = true;
		}
		
		return flag;
	}
	
	
	/**
	 * 单位相关统计
	 * @param form
	 * @return
	 */
	public List<String[]> getDwtj(JyglForm form){
		
		StringBuilder sql = new StringBuilder();
		Pages pages = form.getPages();
		
		sql.append("select * from (");
		sql.append("select a.* ,rownum r from (");
		sql.append("select dwxzmc,hyflmc,sum(dwzc) dwzc,sum(xxzc) xxzc,sum(dwzc+xxzc) zzc from (");
		sql.append("select dwxzmc,hyflmc,");
		sql.append(" case when dwlx='单位' then sum(count) else 0 end dwzc,");
		sql.append(" case when dwlx='学校' then sum(count) else 0 end xxzc ");
		sql.append("from (");
		sql.append("select dwxzmc,hyflmc,dwlx,count(*) count from view_jygl_dwxxb where shzt='通过'");
		
		if (!Base.isNull(form.getDwxz())) {
			sql.append(" and dwxz='");
			sql.append(form.getDwxz());
			sql.append("' ");
		}
		
		if (!Base.isNull(form.getHyfl())) {
			sql.append(" and hyfl='");
			sql.append(form.getHyfl());
			sql.append("' ");
		}
		
		if (!Base.isNull(form.getQuerygreaterequal_zcsj())) {
			sql.append(" and zcsj>='");
			sql.append(form.getQuerygreaterequal_zcsj());
			sql.append("' ");
		}
		
		if (!Base.isNull(form.getQuerylessequal_zcsj())) {
			sql.append("and zcsj<='");
			sql.append(form.getQuerylessequal_zcsj());
			sql.append("' ");
		}
		
		
		sql.append(" group by dwxzmc,hyflmc,dwlx ");
		sql.append(") group by dwlx,dwxzmc,hyflmc");
		sql.append(") group by dwxzmc,hyflmc");
		sql.append(") a");
		sql.append(") where r >  ");
		sql.append(pages.getStart());
		sql.append(" and r <= ");
		sql.append((pages.getStart() + pages.getPageSize()));
		
		List<String[]> result = dao.rsToVator(sql.toString(), new String[] {},
				new String[] { "dwxzmc", "hyflmc", "dwzc", "xxzc", "zzc" });
		pages.setMaxRecord(result.size());
		return result;
		
	}
	
	
	/**
	 * 招聘信息相关统计
	 * @param form
	 * @return
	 */
	public List<String[]> getZpxxTj(JyglForm form){
		StringBuilder sql = new StringBuilder();
		
		sql.append("select sum(dwfbs) dwfbs,sum(xxfbs) xxfbs,sum(fbzs) fbzs,");
		sql.append("sum(btjls) btjls,sum(blls) blls,sum(bscs) bscs,sum(hfjls) hfjls from(");
		sql.append("select a.gsmc,a.dwfbs,a.xxfbs,a.fbzs,b.btjls,b.blls,b.bscs,b.hfjls ");
		sql.append("from (select gsmc,sum(dwfbs) dwfbs,sum(xxfbs) xxfbs,sum(dwfbs+xxfbs) fbzs ");
		sql.append("from(select gsmc,");
		sql.append("case when fblx='单位' then sum(count) else 0 end dwfbs,");
		sql.append("case when fblx='学校' then sum(count) else 0 end xxfbs ");
		sql.append("from (");
		sql.append("select gsmc,fblx,count(*) count from  jygl_zpxxb a where xxsh='通过' ");
		
		if (!Base.isNull(form.getQuerygreaterequal_fbsj())) {
			sql.append(" and fbsj>='");
			sql.append(form.getQuerygreaterequal_fbsj());
			sql.append("' ");
		}
		
		if (!Base.isNull(form.getQuerylessequal_fbsj())) {
			sql.append("and fbsj<='");
			sql.append(form.getQuerylessequal_fbsj());
			sql.append("' ");
		}
		
		if (!Base.isNull(form.getDwxz())) {
			sql.append(" and exists (select 1 from jygl_dwxxb b where a.gsmc=b.dwmc and dwxz='");
			sql.append(form.getDwxz());
			sql.append("')");
		}
		
		if (!Base.isNull(form.getHyfl())) {
			sql.append(" and exists (select 1 from jygl_dwxxb b where a.gsmc=b.dwmc and hyfl='");
			sql.append(form.getHyfl());
			sql.append("')");
		}
		
		sql.append("group by gsmc,fblx) group by gsmc,fblx");
		sql.append(") group by gsmc) a  ");
		sql.append("left join ");
		sql.append("(select dwmc,sum(btjls) btjls,sum(blls) blls,sum(bscs) bscs,");
		sql.append("(select count(*) from jyweb_zpxxtjb where dwmc=a.dwmc and czlx='回复') hfjls ");
		sql.append("from (select dwmc,");
		sql.append("case when joblb='申请' then sum(count) else 0 end btjls,");
		sql.append("case when joblb='浏览' then sum(count) else 0 end blls,");
		sql.append("case when joblb='收藏' then sum(count) else 0 end bscs ");
		sql.append("from (");
		sql.append("select dwmc,joblb,count(*) count from jyweb_stujobs group by dwmc,joblb");
		sql.append(") a group by dwmc,joblb");
		sql.append(") a group by dwmc");
		sql.append(") b on a.gsmc=b.dwmc)");
		
		return dao.rsToVator(sql.toString(), new String[] {}, new String[] {
				"dwfbs", "xxfbs", "fbzs", "btjls", "blls", "bscs", "hfjls" });
	}
	
	
	/**
	 * 学生相关统计调用SQL
	 * @param from
	 * @return
	 */
	private String getStuTjSQL(JyglForm form) {
		
		StringBuilder sb = new StringBuilder();
		
		StringBuilder querySql = new StringBuilder();
		StringBuilder sjSql = new StringBuilder();
		
		if (!Base.isNull(form.getQuerygreaterequal_tdrq())) {
			querySql.append(" and tdrq>='");
			querySql.append(form.getQuerygreaterequal_tdrq());
			querySql.append("' ");
			
			sjSql.append(" and sj>='");
			sjSql.append(form.getQuerygreaterequal_tdrq());
			sjSql.append("' ");
		}
		
		if (!Base.isNull(form.getQuerylessequal_tdrq())) {
			querySql.append("and tdrq<='");
			querySql.append(form.getQuerylessequal_tdrq());
			querySql.append("' ");
			
			sjSql.append("and sj<='");
			sjSql.append(form.getQuerylessequal_tdrq());
			sjSql.append("' ");
		}
		
		sb.append("select a.*,nvl(b.bfws,0) bfws,nvl(b.bscs,0) bscs,nvl(b.bhfs,0) bhfs ");
		sb.append("from(select a.xh ,b.nj,b.xydm,b.zydm,b.bjdm,b.xymc,b.zymc,b.bjmc,");
		sb.append("(select count(*) from jyweb_stujobs where xh=a.xh and joblb='浏览' ");
		sb.append(querySql);
		sb.append(")  llgs,");
		sb.append("(select count(*) from jyweb_stujobs where xh=a.xh and joblb='收藏' ");
		sb.append(querySql);
		sb.append(")  scgs,");
		sb.append("(select count(*) from jyweb_stujobs where xh=a.xh and joblb='申请' ");
		sb.append(querySql);
		sb.append(")  tdgs ");
		
		sb.append("from jygl_jytjb a left join view_xsjbxx b on a.xh=b.xh");
		sb.append(") a left join (select xh, sum(bfws) bfws,sum(bscs) bscs,sum(bhfs) bhfs ");
		sb.append("from (select xh,");
		sb.append("case when czlx='浏览' then sum(count) else 0 end bfws,");
		sb.append("case when czlx='收藏' then sum(count) else 0 end bscs,");
		sb.append("case when czlx='回复' then sum(count) else 0 end bhfs ");
		sb.append("from (");
		sb.append("select xh,czlx,count(*) count from jyweb_zpxxtjb where 1=1");
		sb.append(sjSql);
		sb.append("group by xh,czlx)  group by xh,czlx");
		sb.append(") a group by xh");
		sb.append(") b on a.xh=b.xh ");
		
		return sb.toString();
	}
	
	
	/**
	 * 学生相关统计-按学院统计
	 * @param form
	 * @return
	 */
	public List<String[]> getStuTjByXy(JyglForm form){
		
		String tjSQl = getStuTjSQL(form);
		StringBuilder sql = new StringBuilder();
		Pages pages = form.getPages();
		
		sql.append("select * from (");
		sql.append("select a.*,rownum r from (");
		sql.append("select xymc, count(*) count,sum(llgs) llgs,sum(scgs) scgs,");
		sql.append("sum(tdgs) tdgs,sum(bfws) bfws,sum(bscs) bscs,sum(bhfs) bhfs ");
		sql.append("from (");
		sql.append(tjSQl);
		sql.append("where 1=1 ");
		
		if (!Base.isNull(form.getNj())) {
			sql.append(" and nj='");
			sql.append(form.getNj());
			sql.append("' ");
		}
		
		if (!Base.isNull(form.getXydm())) {
			sql.append(" and xydm='");
			sql.append(form.getXydm());
			sql.append("' ");
		}
		sql.append(") group by xymc ) a");
		sql.append(") where r >  ");
		sql.append(pages.getStart());
		sql.append(" and r <= ");
		sql.append((pages.getStart() + pages.getPageSize()));
		
		
		
		List<String[]> result = dao.rsToVator(sql.toString(), new String[] {},
				new String[] { "xymc", "count", "bfws","tdgs", "llgs", "scgs", 
						 "bhfs" });
		pages.setMaxRecord(result.size());
		return result;
	}
	
	
	/**
	 * 学生相关统计-按专业统计
	 * @param form
	 * @return
	 */
	public List<String[]> getStuTjByZy(JyglForm form){
		
		String tjSQl = getStuTjSQL(form);
		StringBuilder sql = new StringBuilder();
		Pages pages = form.getPages();
		
		sql.append("select * from (");
		sql.append("select a.*,rownum r from (");
		sql.append("select xymc,zymc, count(*) count,sum(llgs) llgs,sum(scgs) scgs,");
		sql.append("sum(tdgs) tdgs,sum(bfws) bfws,sum(bscs) bscs,sum(bhfs) bhfs ");
		sql.append("from (");
		sql.append(tjSQl);
		sql.append("where 1=1 ");
		
		if (!Base.isNull(form.getNj())) {
			sql.append(" and nj='");
			sql.append(form.getNj());
			sql.append("' ");
		}
		
		if (!Base.isNull(form.getXydm())) {
			sql.append(" and xydm='");
			sql.append(form.getXydm());
			sql.append("' ");
		}
		
		if (!Base.isNull(form.getZydm())) {
			sql.append(" and xydm='");
			sql.append(form.getZydm());
			sql.append("' ");
		}
		
		sql.append(") group by xymc,zymc) a");
		sql.append(") where r >  ");
		sql.append(pages.getStart());
		sql.append(" and r <= ");
		sql.append((pages.getStart() + pages.getPageSize()));
		
		List<String[]> result = dao.rsToVator(sql.toString(), new String[] {},
				new String[] { "xymc", "zymc","count", "bfws","tdgs", "llgs", "scgs", 
						 "bhfs" });
		
		pages.setMaxRecord(result.size());
		return result;
	}
	
	
	/**
	 * 学生相关统计-按班级统计
	 * @param form
	 * @return
	 */
	public List<String[]> getStuTjByBj(JyglForm form){
		
		String tjSQl = getStuTjSQL(form);
		StringBuilder sql = new StringBuilder();
		Pages pages = form.getPages();
		
		sql.append("select * from (");
		sql.append("select a.*,rownum r from (");
		sql.append("select xymc,zymc,bjmc, count(*) count,sum(llgs) llgs,sum(scgs) scgs,");
		sql.append("sum(tdgs) tdgs,sum(bfws) bfws,sum(bscs) bscs,sum(bhfs) bhfs ");
		sql.append("from (");
		sql.append(tjSQl);
		sql.append("where 1=1 ");
		
		if (!Base.isNull(form.getNj())) {
			sql.append(" and nj='");
			sql.append(form.getNj());
			sql.append("' ");
		}
		
		if (!Base.isNull(form.getXydm())) {
			sql.append(" and xydm='");
			sql.append(form.getXydm());
			sql.append("' ");
		}
		
		if (!Base.isNull(form.getZydm())) {
			sql.append(" and xydm='");
			sql.append(form.getZydm());
			sql.append("' ");
		}
		
		if (!Base.isNull(form.getBjdm())) {
			sql.append(" and xydm='");
			sql.append(form.getBjdm());
			sql.append("' ");
		}
		
		sql.append(") group by xymc,zymc,bjmc) a");
		sql.append(") where r >  ");
		sql.append(pages.getStart());
		sql.append(" and r <= ");
		sql.append((pages.getStart() + pages.getPageSize()));
		
		List<String[]> result = dao.rsToVator(sql.toString(), new String[] {}, new String[] {
				"xymc", "zymc", "bjmc", "count", "bfws", "tdgs", "llgs",
				"scgs", "bhfs" });
		
		pages.setMaxRecord(result.size());
		return result;
	}
	
	
	/**
	 * 学生相关统计-按学生统计
	 * @param form
	 * @return
	 */
	public List<String[]> getStuTjByStu(JyglForm form){
		
		String tjSQl = getStuTjSQL(form);
		StringBuilder sql = new StringBuilder();
		Pages pages = form.getPages();
		
		sql.append("select * from (");
		sql.append("select a.*,rownum r from (");
		sql.append("select a.*,(select xm from xsxxb where xh=a.xh) xm,");
		sql.append("(select sfzh from xsxxb where xh=a.xh) sfzh ");
		sql.append("from (");
		sql.append(tjSQl);
		sql.append(") a where 1=1 ");
		
		if (!Base.isNull(form.getNj())) {
			sql.append(" and nj='");
			sql.append(form.getNj());
			sql.append("' ");
		}
		
		if (!Base.isNull(form.getXydm())) {
			sql.append(" and xydm='");
			sql.append(form.getXydm());
			sql.append("' ");
		}
		
		if (!Base.isNull(form.getZydm())) {
			sql.append(" and zydm='");
			sql.append(form.getZydm());
			sql.append("' ");
		}
		
		if (!Base.isNull(form.getBjdm())) {
			sql.append(" and bjdm='");
			sql.append(form.getBjdm());
			sql.append("' ");
		}
		
		
		sql.append(") a where 1=1");
		
		if (!Base.isNull(form.getXm())) {
			sql.append(" and xm like'%");
			sql.append(form.getXm());
			sql.append("%' ");
		}
		
		if (!Base.isNull(form.getSfzh())) {
			sql.append(" and sfzh like'%");
			sql.append(form.getSfzh());
			sql.append("%' ");
		}
		
		sql.append(") where r >  ");
		sql.append(pages.getStart());
		sql.append(" and r <= ");
		sql.append((pages.getStart() + pages.getPageSize()));
		
		List<String[]> result = dao.rsToVator(sql.toString(), new String[] {}, new String[] {
				"xymc", "zymc", "bjmc","xm","sfzh","bfws","tdgs","llgs",
				"scgs", "bhfs" });
		
		pages.setMaxRecord(result.size());
		return result;
	}
	
	
	/**
	 * 批量回复---DWR调用的
	 * @param pkValues
	 * @param hfnr
	 * @return
	 * @throws SQLException
	 */
	public boolean saveHfxxMore(String[] pkValues ,String hfnr) throws SQLException {
		
		String[] sqlArr = new String[pkValues.length];
		
		for (int i = 0 ; i<pkValues.length;i++) {
			StringBuilder sql = new StringBuilder();
			sql.append("insert into jyweb_zpxxtjb (xh,dwmc,gwmc,gwfbsj,czlx,hfxx) select xh,dwmc,gwmc,gwfbsj,'回复','");
			sql.append(hfnr);
			sql.append("' from view_jyweb_stujobs where xh||dwmc||gwmc||joblb||tdrq||gwfbsj='");
			sql.append(pkValues[i]);
			sql.append("'");
			
			sqlArr[i] = sql.toString();
		}
		
		int[] result = dao.runBatch(sqlArr);
		
		return dao.checkBatch(result);
	}
	
	
	/**
	 * 保存新闻
	 */
	public boolean saveNews(JyglForm form) {

		String sql = "insert into jygl_zcwjb(wjbt,wjlx,wjnr,fbr,fbsj,tplj) values (?,?,?,?,?,?)";

		try {
			return dao.runUpdate(sql, new String[] { form.getWjbt(),
					form.getWjlx(), form.getWjnr(), form.getFbr(),
					form.getFbsj(), form.getFileName() });
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
	/**
	 * 修改新闻
	 * @param form
	 * @param pkValue
	 * @return
	 */
	public boolean updateNews(JyglForm form,String pkValue) {

		String sql = "update jygl_zcwjb set wjbt=?,wjlx=?,wjnr=?,fbr=?,fbsj=?,tplj=?  where wjbt=? ";

		try {
			return dao.runUpdate(sql, new String[] { form.getWjbt(),
					form.getWjlx(), form.getWjnr(), form.getFbr(),
					form.getFbsj(), form.getFileName(),pkValue});
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}



	/**
	 * 单位列表（岗位信息库使用）
	 * @param model
	 * @return
	 */
	public List<HashMap<String,String>> getDwList(JyglForm model){
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select distinct yhm,gsmc from(");
		sql.append("select * from view_jygl_zpxx  where xxsh='通过' and yxqx >= to_number(to_char(sysdate,'yyyymmdd'))");
		if (!Base.isNull(model.getZpzw())){
			sql.append(" and zpzw like '%")
			   .append(model.getZpzw())
			   .append("%'");
		}
		sql.append(")");
		
		if (!Base.isNull(model.getDwmc())){
			sql.append("where gsmc like '%")
			   .append(model.getDwmc())
			   .append("%'");
		}
		
		return dao.getList(sql.toString(), new String[]{}, new String[]{"yhm","gsmc"});
	}

	
	
	/**
	 * 岗位列表（岗位信息库使用）
	 * @param model
	 * @return
	 */
	public List<HashMap<String,String>> getGwList(JyglForm model){
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select * from view_jygl_zpxx where xxsh='通过' and yxqx >= to_number(to_char(sysdate,'yyyymmdd')) ");
		
		if (!Base.isNull(model.getZpzw())){
			sql.append(" and zpzwmc like '%")
			   .append(model.getZpzw())
			   .append("%'");
		}
		
		return dao.getList(sql.toString(), new String[]{}, new String[]{"gsmc","zpzw","fbsj","zpzwmc"});
	}
	
	
	
	/**
	 * 保存专题招聘单位、岗位关联信息
	 * @param id
	 * @param dwmc
	 * @param gwmc
	 * @return
	 * @throws SQLException
	 */
	public boolean saveZtzp(String id,String[] dwmc,List<String> gwmc) throws SQLException{
		
		String[] sqlArr = new String[dwmc.length+gwmc.size()+2];
		
		StringBuilder delDwmc = new StringBuilder();
		StringBuilder delGwmc = new StringBuilder();
		
		delDwmc.append("delete from xg_jyweb_ztzpgsb where ztid='").append(id).append("'");
		delGwmc.append("delete from xg_jyweb_ztzpgwb where ztid='").append(id).append("'");
		
		sqlArr[0] = delDwmc.toString();
		sqlArr[1] = delGwmc.toString();
		
		int n = 2 ;
		for (int i = 0 ; i < dwmc.length ; i++){
			StringBuilder sql = new StringBuilder();
			
			sql.append("insert into xg_jyweb_ztzpgsb values ('")
			   .append(id)
			   .append("','")
			   .append(dwmc[i])
			   .append("')");
			sqlArr[n] = sql.toString();
			n++;
		}
		
		for (int i = 0 ; i < gwmc.size() ; i++){
			StringBuilder sql = new StringBuilder();
			
			sql.append("insert into xg_jyweb_ztzpgwb values ('")
			   .append(id)
			   .append("','")
			   .append(gwmc.get(i))
			   .append("')");
			sqlArr[n++] = sql.toString();
		}
		
		int[] result = dao.runBatch(sqlArr);
		return dao.checkBatch(result);
	}

	
	/**
	 * 主题招聘岗位列表
	 * @param pkValue
	 * @return
	 */
	public List<HashMap<String,String>> getZtzpGwList(String pkValue){
		
		String sql = "select a.* from view_jygl_zpxx a where exists (select 1 from xg_jyweb_ztzpgwb b where b.gwid=a.zpzw||a.gsmc||a.fbsj and b.ztid=?)";
		
		return dao.getList(sql, new String[]{pkValue}, new String[]{"yhm","gsmc","zpzw","fbsj","zpzwmc"});
	}
	
	
	/**
	 * 主题招聘浏览次数加1
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public boolean setLlcs(String pkValue) throws Exception{
		String sql = "update xg_jyweb_ztzpb set llcs=llcs+1 where id=?";
		return dao.runUpdate(sql, new String[]{pkValue});
	}
	
	
	
	/**
	 * 获取主题招聘列表（供DWR调用）
	 * @param zpsj
	 * @return
	 */
	public List<HashMap<String,String>> getZtzpList(){
		String sql = "select id,zpzt,to_char(to_date(zpsj,'yyyymmdd'),'yyyy-mm-dd') zpsj,to_char(to_date(fbsj,'yyyymmdd'),'yyyy-mm-dd') fbsj from xg_jyweb_ztzpb where shzt='通过' order by zpsj desc";
		
		return dao.getList(sql, new String[]{}, new String[]{"id","zpzt","zpsj","fbsj"});
	}
	
	
	
	/**
	 * 删除与主题招聘关联数据
	 * @return
	 * @throws Exception 
	 */
	public boolean delZtzp(String[] pkValues) throws Exception {
		
		StringBuilder dwSql = new StringBuilder();
		StringBuilder gwSql = new StringBuilder();
		
		dwSql.append("delete from xg_jyweb_ztzpgsb where ztid in (");
		gwSql.append("delete from xg_jyweb_ztzpgwb where ztid in (");
		
		for (int i = 0 ; i < pkValues.length ; i++) {
			
			dwSql.append("?");
			gwSql.append("?");
			
			if (i != pkValues.length-1) {
				dwSql.append(",");
				gwSql.append(",");
			} else {
				dwSql.append(")");
				gwSql.append(")");
			}
		}
		
		return dao.runUpdate(dwSql.toString(), pkValues) 
			 && dao.runUpdate(gwSql.toString(), pkValues);
	}
	
	
	
	
	/**
	 * 根据单位名称获取用户名
	 * @param dwmc
	 * @return
	 */
	public String getYhm(String dwmc) {
		
		String sql = "select yhm from jygl_dwxxb where dwmc=?";
		
		return dao.getOneRs(sql, new String[] {dwmc}, "yhm");
	}

	
	
	/**
	 * 招聘类型
	 * @return
	 */
	public List<HashMap<String,String>> getZplxList(){
		String sql = "select * from xg_jyweb_zplxb order by xssx asc";
		
		return dao.getList(sql, new String[] {}, new String[] {"dm","mc"});
	}
	
	
	
	/**
	 * 修改单位回复的是否删除状态
	 * @param pkValues
	 * @return
	 * @throws SQLException
	 */
	public boolean updateSfsc(String[] pkValues) throws SQLException {
		String[] sqlArr = new String[pkValues.length];
		
		for (int i = 0 ; i < pkValues.length ; i++) {
			StringBuilder sql = new StringBuilder();
			
			sql.append("update jyweb_zpxxtjb set sfsc='是'  where xh||dwmc||gwmc||sj||gwfbsj=");
			sql.append("(select xh||dwmc||gwmc||tdrq||gwfbsj from ");
			sql.append("jyweb_stujobs where xh||dwmc||gwmc||joblb||tdrq||gwfbsj='");
			sql.append(pkValues[i]);
			sql.append("')");
			
			sqlArr[i] = sql.toString();
		}
		
		int[] result = dao.runBatch(sqlArr);
		return dao.checkBatch(result);
	}


	
	/**
	 * 中心介绍查询
	 * @param lx
	 * @return
	 */
	public String getZxjs(String lx){
		
		String sql = "select jsnr from xg_jyweb_zxjs where lx = ?";
		
		return dao.getOneRs(sql, new String[]{lx}, "jsnr");
	}
	
	
	
	/**
	 * 获取学生申请或收藏过的岗位记录
	 * @param xh
	 * @param type
	 * @param pkValue
	 * @return
	 */
	public List<HashMap<String,String>> getStuJobs(String xh ,String type ,String pkValue){
		
		String sql = "select * from jyweb_stujobs where xh = ? and joblb = ? and gwmc||dwmc||gwfbsj= ? ";
		
		return dao.getList(sql, new String[]{xh,type,pkValue}, new String[]{"xh","dwmc","gwmc"});
	}
	
	
}
