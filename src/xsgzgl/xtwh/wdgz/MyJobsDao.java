package xsgzgl.xtwh.wdgz;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.search.SearchModel;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.Pages;
import xgxt.utils.String.StringUtils;
import xsgzgl.qgzx.cjffsq.QgzxCjffsqDAO;
import xsgzgl.qgzx.cjffsq.QgzxCjffsqForm;
import xsgzgl.qgzx.gwglnew.QgzxGwglDAO;
import xsgzgl.qgzx.gwglnew.QgzxGwglForm;

import com.zfsoft.xgxt.qgzx.xsgw.WdgwsqDAO;
import com.zfsoft.xgxt.qgzx.xsgw.XsgwshDAO;
import com.zfsoft.xgxt.qgzx.xsgw.XsgwshForm;
import com.zfsoft.xgxt.szdw.thjl.zdgzxsk.SzdwZdgzxskDao;
import com.zfsoft.xgxt.szdw.thjl.zdgzxsk.SzdwZdgzxskForm;
import com.zfsoft.xgxt.xtwh.yhsjfw.YhsjfwService;

/**
 * 
 * <p>
 * 	我的工作DAO
 * <p>
 * @author Penghui.Qu 2012-1-7
 */
public class MyJobsDao {

	private DAO dao = DAO.getInstance();
	
	
	/**
	 * 插入我的申请
	 * @param job
	 * @return
	 * @throws SQLException
	 */
	public boolean insertWdsq(Job job) throws SQLException{
		
		
		StringBuilder sql = new StringBuilder();
		sql.append("insert into xg_xtwh_wdgz(ywid,username,");
		sql.append("usertype,gnmklx,gznr,gnmkpath,gzsj,dbjd)");
		sql.append(" values (?,?,'student',?,?,?,to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),?)");
		
		String[] params = new String[]{job.getSqid(),job.getSqr(),
				job.getGnmk(),job.getGznr(),job.getXsUrl(),job.getShjd()};
		
		return dao.insert(sql.toString(), params);
	}
	/**
	 * 查找我的申请
	 * @param job
	 * @return
	 * @throws SQLException
	 */
	public HashMap<String, String> selectWdsq(String ywid) {
		
		StringBuilder sql = new StringBuilder();
		sql.append("select * from xg_xtwh_wdgz where ywid=? and usertype='teacher' ");
		
		String[] params = new String[]{ywid};
		return dao.getMapNotOut(sql.toString(), params);
	}
	/**
	 * 查找我的申请(学生)
	 * 用于最后一级撤销获取代办信息
	 * @param job
	 * @return
	 * @throws SQLException
	 */
	public HashMap<String, String> selectWdsqForStudent(String ywid) {
		
		StringBuilder sql = new StringBuilder();
		sql.append("select * from xg_xtwh_wdgz where ywid=? and usertype='student' ");
		
		String[] params = new String[]{ywid};
		return dao.getMapNotOut(sql.toString(), params);
	}
	
	/**
	 * 批量插入我的工作
	 * @param job
	 * @return
	 * @throws SQLException
	 */
	public boolean batchInsertWdgz(Job job) throws SQLException{
		
		StringBuilder sql = new StringBuilder();
		sql.append("insert into xg_xtwh_wdgz(ywid,username,");
		sql.append("usertype,gnmklx,gznr,gnmkpath,gzsj,dbjd,jdmc)");
		sql.append(" values (?,?,'teacher',?,?,?,to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),?,?)");
		
		List<String[]> params = new ArrayList<String[]>();
		String[] shr = job.getShr();
		
		for (String str : shr){
			String[] param = new String[]{job.getSqid(),str,job.getGnmk(),
					job.getGznr(),job.getUrl(),job.getShjd(),job.getJdmc()};
			params.add(param);
		}
		
		int result[] = dao.runBatch(sql.toString(), params);
		return dao.checkBatchResult(result);
	}
	
	
	
	/**
	 * 根据业务中的主键删除从我的工作中移除已办理的工作
	 * @param job
	 * @return
	 * @throws SQLException
	 */
	public boolean deleteWdgz(Job job) throws SQLException{
		String sql = "delete from xg_xtwh_wdgz where ywid=? and usertype='teacher'";
		String[] input = new String[]{job.getSqid()};
		return dao.insert(sql, input);
	}
	
	
	
	/**
	 * 更新学生我的申请记录中的“审核进度”
	 * @param job
	 * @return
	 * @throws Exception
	 */
	public boolean updateSqjd(Job job) throws Exception{
		
		String sql = "update xg_xtwh_wdgz set dbjd=? where ywid=? and usertype='student'";
		String[] input = new String[]{job.getShjd(),job.getSqid()};
		
		return dao.runUpdate(sql, input);
	}
	
	
	
	/**
	 * 查询待办工作
	 * @param userName
	 * @return
	 */
	public List<HashMap<String,String>> getWdgzList(MyJobsForm model,User user){
		StringBuilder sql = new StringBuilder();
		ArrayList<String> input = new ArrayList<String>();
		String userStatus = user.getUserStatus();
		String shgwzByUser = SearchService.getShgwzByUser(user, "e",
				"xydm", "bjdm");
		sql.append(" select a.*,rownum r  from (");
		sql.append(" select * from ( select a.* from ( ");
		sql.append(" select distinct a.tzlj as gnmkpath,a.dbs,d.gnmkmc as gznr,'' as jdmc from ( ");//c.mc||'-'||
		sql.append(" select tzlj,gwz,count(*) as dbs from ( ");//lcid,
		sql.append(" select a.*,f.xydm,f.zydm,f.bjdm,f.nj,t1.gwz from XG_XTWH_SHZTB a "); 
		sql.append(" left join xg_xtwh_spgw t1 on a.gwid = t1.id");
		sql.append(" left join xsxxb e on a.sqrid=e.xh ");
		sql.append(" left join view_njxyzybj_all f on e.bjdm=f.bjdm ");
		sql.append(" where a.shzt in ('0','4') ");
		if("fdy".equals(user.getUserStatus()) || "sy".equals(user.getUserStatus())){
			sql.append(" and (t1.yhjs='"+user.getUserStatus()+"' or t1.yhjs is null)");
		}
//		if("xx".equals(userStatus)){
			sql.append(" and a.gwid in ( select b.spgw from XG_XTWH_SPGWYH b where b.spyh=? ) ");
//		} else {
//			sql.append(" select b.spgw from XG_XTWH_SPGWYH b where b.yhjs='"+userStatus+"' and b.spyh=? ) ");
//		}

		input.add(user.getUserName());
		sql.append(shgwzByUser);
		sql.append(" and ( ");
		
		String yhsjfwSql = new YhsjfwService().getYhsjfw(user, "f", "xydm", "bjdm");
		if(StringUtils.isNotNull(yhsjfwSql)){
			sql.append(yhsjfwSql);
		}
		
		//权限过滤
		if ("xy".equalsIgnoreCase(userStatus)) {
			if(StringUtils.isNull(yhsjfwSql)){
				sql.append(" f.xydm=? ");
				input.add(user.getUserDep());
			}
		}else if ("fdy".equalsIgnoreCase(userStatus)) {
			if(StringUtils.isNotNull(yhsjfwSql)){
				sql.append(" or ");
			}
			sql.append(" exists (select 1 from fdybjb x where e.bjdm=x.bjdm and x.zgh = ? ) ");
			input.add(user.getUserName());
		}else if ("bzr".equalsIgnoreCase(userStatus)) {
			if(StringUtils.isNotNull(yhsjfwSql)){
				sql.append(" or ");
			}
			sql.append(" exists (select 1 from bzrbbb x where e.bjdm=x.bjdm and x.zgh = ? ) ");
			input.add(user.getUserName());
		}else if ("jd".equalsIgnoreCase(userStatus)) {
			if(StringUtils.isNotNull(yhsjfwSql)){
				sql.append(" or ");
			}
			sql.append(" (exists (select 1 from bzrbbb x where e.bjdm=x.bjdm and x.zgh = ? ) or exists (select 1 from fdybjb z where e.bjdm = z.bjdm and z.zgh = ? )) ");
			input.add(user.getUserName());
			input.add(user.getUserName());
		}else if("stu".equalsIgnoreCase(user.getUserType())){
			sql.append(" e.xh=?  ");
			input.add(user.getUserName());
		} else {
			if(StringUtils.isNull(yhsjfwSql)){
				sql.append(" 1 = 1 ");
			}
		}
		//TODO 集体评奖审核 控制太过复杂 后期优化 20160620 
		//sql.append(" or (a.tzlj = 'jtpjshbase.do')) ");
		sql.append(" ) ");
		
		sql.append(" )  group by tzlj,gwz ) a "); //,lcid
		sql.append("  left join gnmkdmb d on a.tzlj=d.dyym ");//left join XG_XTWH_SPLC c on a.lcid=c.id
		//sql.append(" left join XG_XTWH_SPGW d  on a.gwid=d.id ");
		sql.append(" ) a ) a where 1=1 ");
		//勤工助学模块支持
		boolean isHaveQxfw = isHaveQxfw(user.getUserName());
		final String qgzx="qgzx_xsgwsh.do?method=xsgwshCx";//勤工助学代办审核路径
		if(isHaveQxfw){
			sql.append(" and gnmkpath <> '");
			sql.append(qgzx);
			sql.append("'");
		}
		//【重点关注学生库】模块支持
		boolean isHaveZdgzxsk = isHaveZdgzxsk();
		final String zdgzxskGnmkpath = "szdw_thjl_zdgzxsk.do"; //【重点关注学生库】模块路径
		if(isHaveZdgzxsk){
			sql.append(" and gnmkpath <> '");
			sql.append(zdgzxskGnmkpath);
			sql.append("'");
		}
		//【用人单位岗位审核】模块支持
		boolean isHaveYrdwgwsq = isHaveYrdwgwsq();
		final String yrdwgwsqGnmkpath = "qgzx_gwglnew_gwsh.do"; //【用人单位岗位审核】模块路径
		if(isHaveYrdwgwsq){
			sql.append(" and gnmkpath <> '");
			sql.append(yrdwgwsqGnmkpath);
			sql.append("'");
		}
		//【勤工助学酬金发放审核】模块支持
		boolean isHaveQgcjffsq = isHaveQgcjffsq();
		final String qgcjffsqGnmkpath = "qgzx_cjffsh_cjxxgl.do"; //【勤工助学酬金发放审核】模块路径
		if(isHaveQgcjffsq){
			sql.append(" and gnmkpath <> '");
			sql.append(qgcjffsqGnmkpath);
			sql.append("'");
		}
		//勤工助学酬金发放审核
		if(isHaveQgcjffsq){
			QgzxCjffsqDAO qgzxCjffsqDAO = new QgzxCjffsqDAO();
			QgzxCjffsqForm qgzxCjffsqForm = new QgzxCjffsqForm();
			qgzxCjffsqForm.setShzt("dsh");
			Pages pages = qgzxCjffsqForm.getPages();
			pages.setPageSize(Integer.MAX_VALUE);
			qgzxCjffsqForm.setPages(pages);
			List list=null;
			try {
				list = qgzxCjffsqDAO.getCjxxShList(qgzxCjffsqForm,user);
			} catch (Exception e) {
				throw new RuntimeException("获取勤工酬金发放审核数量错误！");
			}
			Integer dbs=null==list?0:list.size();
			//存在勤工代办数据
			if(dbs>0){
				sql.append(" union all ");
				sql.append("(select '"+qgcjffsqGnmkpath+"' as gnmkpath,");
				sql.append(dbs+" as dbs,");
				sql.append("(select gnmkmc from gnmkdmb where dyym='"+qgcjffsqGnmkpath+"') as gznr,");
				sql.append("'' as jdmc from dual)");
			}
		}
		//勤工助学模块支持
		if(isHaveQxfw){
			XsgwshDAO xd=new XsgwshDAO();
			XsgwshForm xf=new XsgwshForm();
			xf.setShlx("dsh");
			List list=null;
			try {
				list = xd.getAllList(xf,user);
			} catch (Exception e) {
				throw new RuntimeException("获取勤工代办数量错误！");
			}
			Integer dbs=null==list?0:list.size();
			//存在勤工代办数据
			if(dbs>0){
				sql.append(" union all ");
				sql.append("(select '"+qgzx+"' as gnmkpath,");
				sql.append(dbs+" as dbs,");
				sql.append("(select gnmkmc from gnmkdmb where dyym='"+qgzx+"') as gznr,");
				sql.append("'' as jdmc from dual)");
			}
		}
		//【重点关注学生库】模块支持
		// 除了辅导员兼班主任、班主任、辅导员、学院，
		// 其他用户，包括学校用户（管理员），不作显示
		if(isHaveZdgzxsk
				&& ("jd".equalsIgnoreCase(userStatus) 
					|| "bzr".equalsIgnoreCase(userStatus)
					|| "fdy".equalsIgnoreCase(userStatus) 
					|| "xy".equalsIgnoreCase(userStatus)
					)){
			SzdwZdgzxskDao zdgzxskDao = new SzdwZdgzxskDao();
			SzdwZdgzxskForm t = new SzdwZdgzxskForm();
			List list=null;
			try {
				list = zdgzxskDao.getAllList(t,user);
			} catch (Exception e) {
				 throw new RuntimeException("获取【重点关注学生库】代办数量错误！");
			}
			Integer dbs=null==list?0:list.size();
			//存在【重点关注学生库】代办数据
			if(dbs>0){
				sql.append(" union all ");
				sql.append("(select '"+zdgzxskGnmkpath+"' as gnmkpath,");
				sql.append(dbs+" as dbs,");
				sql.append("(select gnmkmc from gnmkdmb where dyym='"+zdgzxskGnmkpath+"') as gznr,");
				sql.append("'' as jdmc from dual)");
			}
		}
		//【用人单位岗位审核】模块支持
		if(isHaveYrdwgwsq){
			QgzxGwglDAO qgzxGwglDAO = new QgzxGwglDAO();
			QgzxGwglForm t = new QgzxGwglForm();
			t.setUser(user);
			t.setShzt("dsh");
			Pages pages = t.getPages();
			pages.setPageSize(Integer.MAX_VALUE);
			t.setPages(pages);
			SearchModel searchModel=new SearchModel();
			//searchModel.setSearch_tj_qgshzt(new String[]{"未审核"});
			searchModel.setPath("qgzx_gwglnew_gwsh.do");
			t.setSearchModel(searchModel);
			
			List list=null;
			try {
				list = qgzxGwglDAO.getGwshPageList(t,user);
			} catch (Exception e) {
				throw new RuntimeException("获取【用人单位岗位审核】代办数量错误！");
			}
			Integer dbs=null==list?0:list.size();
			//存在【用人单位岗位审核】代办数据
			if(dbs>0){
				sql.append(" union all ");
				sql.append("(select '"+yrdwgwsqGnmkpath+"' as gnmkpath,");
				sql.append(dbs+" as dbs,");
				sql.append("(select gnmkmc from gnmkdmb where dyym='"+yrdwgwsqGnmkpath+"') as gznr,");
				sql.append("'' as jdmc from dual)");
			}
		}
		sql.append(")a where 1=1 ");
//		sql.append("select * from ( select a.*,rownum r from (select gnmklx,gznr,gnmkpath,dbjd,jdmc, min(gzsj) gzsj ,count(1) dbs from xg_xtwh_wdgz  t1 ");
//		sql.append("where username=? and usertype='teacher' ");
//		sql.append("and exists (select 1 from xg_xtwh_wdgz t2 ");
//		sql.append("where t1.ywid=t2.ywid and usertype='student'  ");
//		
//		if (Boolean.valueOf(user.getFdyQx()) && Boolean.valueOf(user.getBzrQx())){
//			input = new String[]{user.getUserName(),user.getUserName(),user.getUserName()};
//			sql.append(" and exists (select 1 from xsxxb t3 where t3.xh=t2.username and t3.bjdm in ");
//			sql.append("(select bjdm from fdybjb where zgh=? union select bjdm from bzrbbb where zgh = ?))");
//		} else if (Boolean.valueOf(user.getFdyQx())){
//			input = new String[]{user.getUserName(),user.getUserName()};
//			sql.append(" and exists (select 1 from xsxxb t3 where t3.xh=t2.username and t3.bjdm in (select bjdm from fdybjb where zgh=?))");
//		} else if (Boolean.valueOf(user.getBzrQx())){
//			input = new String[]{user.getUserName(),user.getUserName()};
//			sql.append(" and exists (select 1 from xsxxb t3 where t3.xh=t2.username and t3.bjdm in (select bjdm from bzrbbb where zgh=?))");
//		} else if ("xy".equals(user.getUserType())){
//			input = new String[]{user.getUserName(),user.getUserDep()};
//			sql.append(" and exists (select 1 from xsxxb t3 where t3.xh=t2.username and t3.xydm=?)");
//		} else {
//			input = new String[]{user.getUserName()};
//		}
//		sql.append(")group by gnmklx,gznr,gnmkpath,dbjd,jdmc) a order by a.gzsj ) a where 1=1 ");
		
		return CommonQueryDAO.commonPageQueryForMap(model.getPages(), sql.toString(), input.toArray(new String[] {}));
	}
	/**
	 * 
	 * @描述:是否有勤工用人单位权限
	 * @作者：张昌路[工号：982]
	 * @日期：2013-12-23 下午05:09:42
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param userName
	 * @return
	 * boolean 返回类型 
	 */
	public boolean isHaveQxfw(String userName){
/*		WdgwsqDAO s=new WdgwsqDAO();
		HashMap<String,String> pz= s.getCsszb();
		String gwid=pz.get("qxfw");
		return null==s.getGwdm(userName, gwid)?false:true;*/
		WdgwsqDAO s=new WdgwsqDAO();
		HashMap<String,String> pz= s.getCsszb();
		if(null!=pz&&pz.size()>0&&!"10125".equals(Base.xxdm)){//非山西财经大学
			return true;
		}
		return false;
	}
	/**
	 * 
	 * @描述:是否有【重点关注学生库】权限
	 */
	public boolean isHaveZdgzxsk(){
		StringBuilder sql = new StringBuilder();
		sql.append("select count(1) num from gnmkdmb a where a.sfqy='是' and a.dyym = 'szdw_thjl_zdgzxsk.do' ");
		String num = dao.getOneRs(sql.toString(), new String[] {}, "num");
		boolean rs = Integer.valueOf(num) > 0;
		if(rs){
			sql = new StringBuilder();
			sql.append("select count(1) num from xg_szdw_thjl ");
			num = dao.getOneRs(sql.toString(), new String[] {}, "num");
			rs = Integer.valueOf(num) > 0;
		}
		return rs;
	}
	/**
	 * 
	 * @描述:是否有【用人单位岗位审核】权限
	 */
	public boolean isHaveYrdwgwsq(){
		StringBuilder sql = new StringBuilder();
		sql.append("select count(1) num from xg_qgzx_gwxxsqb ");
		String num = dao.getOneRs(sql.toString(), new String[] {}, "num");
		return Integer.valueOf(num) > 0;
	}	/**
	 * 
	 * @描述:是否有【勤工助学酬金发放审核】权限
	 */
	public boolean isHaveQgcjffsq(){
		StringBuilder sql = new StringBuilder();
		sql.append("select count(1) num from XG_QGZX_GWFFZTSQB ");
		String num = dao.getOneRs(sql.toString(), new String[] {}, "num");
		return Integer.valueOf(num) > 0;
	}
	/**
	 * 查询我的申请
	 * @param userName
	 * @return
	 */
	public List<HashMap<String,String>> getWdsqList(MyJobsForm model,String userName){
		
//		StringBuilder sql = new StringBuilder("select a.*,rownum r from (select gnmkpath,gznr,");
//		sql.append("to_char(to_date(gzsj,'yyyy-mm-dd hh24:mi:ss'),'yyyy-mm-dd') gzsj ");
//		sql.append("from xg_xtwh_wdgz where ");
//		sql.append("username=? and usertype='student' and gnmklx<>'处分上报' order by gzsj desc) a where 1=1 ");
		
		StringBuilder sql = new StringBuilder("select a.*, rownum r from (select a.tzljsq as gnmkpath, (select gnmkmc from gnmkdmb_dj d where a.tzljsq=d.dyym and rownum = 1) as gznr, decode(a.shzt, '1','审核已通过', '2', '审核不通过', '3', '已被退回', a.shzt) shztmc, shsj from ( select * from ( ");//c.mc||'-'||
		sql.append(" select tzljsq, row_number() over (partition by ywid,lcid order by ywid,shsj desc) as dd,shzt, shsj ");//lcid,
		sql.append(" from XG_XTWH_SHZTB  where sqrid = ? ) ");
		sql.append(" where dd=1 and shzt in ('1', '2', '3') order by shsj desc ) a ) a where 1 = 1  ");//left join XG_XTWH_SPLC c on a.lcid = c.id
		sql.append(" and gnmkpath not like '%wjcf_cfsbgl.do?method=cxCfsbList%'");
		return CommonQueryDAO.commonPageQueryForMap(model.getPages(), sql.toString(), new String[]{userName});
	}
	
	/**
	 * 加载我的工作
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public boolean callWdgz(User user) throws Exception{
		
		String userType = null;
		
		if (Boolean.valueOf(user.getFdyQx()) && Boolean.valueOf(user.getBzrQx())){
			userType = "jd";
		} else if (Boolean.valueOf(user.getFdyQx())){
			userType = "fdy";
		} else if (Boolean.valueOf(user.getBzrQx())){
			userType = "bzr";
		} else {
			userType = user.getUserType();
		}
		
		return dao.runProcuder("{call pro_xg_xtwh_wdgz(?,?,?)}", 
				new String[]{user.getUserName(),userType,user.getUserDep()});
	}
	
	
	/**
	 * 根据岗位ID查找审批用户
	 * @param gwid
	 * @return
	 * @throws Exception
	 */
	public String[] getShrByGwid(String gwid) throws Exception{
		String sql = "select spyh from xg_xtwh_spgwyh where spgw=?";
		return dao.getRs(sql, new String[]{gwid}, "spyh");
	}
	
	
	
	/**
	 * 根据岗位ID查找岗位名称 
	 * @param gwid
	 * @return
	 */
	public String getGwmc(String gwid){
		String sql = "select mc from xg_xtwh_spgw where id=?";
		return dao.getOneRs(sql, new String[]{gwid}, "mc");
	}
	
	
	/**
	 * 查询我的工作相关信息
	 * @param sqid
	 * @param gnmk
	 * @return
	 */
	public HashMap<String,String>  getWdgzInfo(String sqid,String gnmk){
		
		String sql = "select * from xg_xtwh_wdgz where ywid=? and gnmklx=?  and rownum=1";
		
		return dao.getMapNotOut(sql, new String[]{sqid, gnmk});
	}
	
	
	/**
	 * 删除待办
	 * @param ywid
	 * @param gnmk
	 * @return
	 * @throws Exception
	 */
	public boolean delWdgz(String[] ywid, String gnmk) throws Exception{
		
		StringBuilder sql = new StringBuilder();
		List<String> input = new ArrayList<String>();
		
		sql.append("delete from xg_xtwh_wdgz where gnmklx=? and (");
		input.add(gnmk);
		
		for (int i = 0 ; i < ywid.length ; i++){
			
			sql.append("ywid=? ");
			input.add(ywid[i]);
			if (i != ywid.length -1){
				sql.append(" or ");
			}
		}
		
		sql.append(")");
		
		return dao.runUpdate(sql.toString(), input.toArray(new String[]{}));
	}
	
	
	/**
	 * 拷贝待办工作
	 * @param users
	 * @param gwid
	 * @return
	 * @throws Exception
	 */
	public boolean copyJobs(String[] users,String gwid) throws Exception{
		
		StringBuilder sql = new StringBuilder();
		
		// ------------------------2013-9-12 待办工作拷贝重复数据BUG 修正 - qilm------------START---------------------
		sql.append(" insert into xg_xtwh_wdgz(username,usertype,gnmklx,gznr,gnmkpath,gzsj,ywid,dbjd,jdmc) ");
		sql.append(" select * from ( ");
		sql.append(" select distinct ?,userType,gnmklx,gznr,gnmkpath,to_char(sysdate,'yyyy-mm-dd hh:mm:dd'),ywid,dbjd,jdmc ");
		sql.append(" from xg_xtwh_wdgz where dbjd=? and userType='teacher' ");
		sql.append(" ) ");
		// ------------------------2013-9-12 待办工作拷贝重复数据BUG 修正 - qilm------------END---------------------
		
		List<String[]> params = new ArrayList<String[]>();
		
		for (String user : users){
			String[] param = new String[]{user,gwid};
			params.add(param);
		}
		
		int[] result = dao.runBatch(sql.toString(), params);
		return dao.checkBatchResult(result);
	}
	
	
	/**
	 * 删除某岗位的待办用户组
	 * @param users
	 * @param gwid
	 * @return
	 * @throws Exception
	 */
	public boolean delJobs(String[] users,String gwid) throws Exception{
		
		StringBuilder sql = new StringBuilder();
		List<String> input = new ArrayList<String>();
		
		sql.append("delete from xg_xtwh_wdgz where dbjd=? and (");
		input.add(gwid);
		
		for (int i = 0 ; i < users.length ; i++){
			sql.append("username=?");
			input.add(users[i]);
			if (i != users.length-1){
				sql.append(" or ");
			}
		}
		sql.append(")");
		
		return dao.runUpdate(sql.toString(), input.toArray(new String[]{}));
	}
}
