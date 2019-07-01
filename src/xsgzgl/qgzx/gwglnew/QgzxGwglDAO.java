package xsgzgl.qgzx.gwglnew;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.jdbc.core.support.SqlLobValue;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.search.SearchModel;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.String.StringUtils;
import xsgzgl.qgzx.glygl.QgzxGlyglService;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.wjcf.cfsh.CfshForm;
import com.zfsoft.xgxt.xlzx.zxzxjl.ZxzxjlxxModel;

import common.Globals;
import common.XszzXmdm;
import freemarker.template.SimpleDate;
/**
 * 勤工助学-勤工岗位管理-岗位信息管理
 */
public class QgzxGwglDAO extends SuperDAOImpl<QgzxGwglForm> {
	
	private static final String SQZQ = MessageUtil.getText("xszz.knsrd.sqzq");
	
	DAO dao = DAO.getInstance();
	/*
	    描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	*/
	
	@Override
	public List<HashMap<String, String>> getPageList(QgzxGwglForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}
	/*
	    描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	*/
	
	@Override
	public List<HashMap<String, String>> getPageList(QgzxGwglForm t, User user)
			throws Exception {
		// TODO 自动生成方法存根
		//生成高级查询相关条件、条件值
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputValue = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (");
		sql.append("select t.sjly,t.gwdm,t.gwmc,t.gwxzdm,t.gwlx,case when t.gwxzdm = '0' then '临时' else '正式' end gzxzmc,t.xqrs,t.fbsj,t.xn ");
		sql.append(" ,case when t.cq = '1' then '长期有效' else t.zpjssj end zpjssj");
		sql.append(" ,nvl(t1.yrdwmc,t2.bmmc) yrdwmc,t1.dwlb,t.gwlb,t3.gwxzmc gwlbmc ");
		sql.append(" ,t1.yhm,t1.zgh,t.shzt,t.splc,t.zpkssj");
		sql.append(" ,decode(t.shzt,  '0', '未提交', '1', '通过', '2', '不通过', '3', ");
		sql.append(" '退回', '4', '需重审', '5', '审核中', '', '无需审核', t.shzt) shztmc");
		sql.append(" ,(select count(1) from xg_qgzx_new_xsgwxxb a where a.gwdm = t.gwdm and a.zgzt = 'zg') zgrs");
		sql.append(" ,(select count(1) from xg_qgzx_new_xsgwxxb a where a.gwdm = t.gwdm and a.zgzt = 'tg') lzrs");
		sql.append(" from XG_QGZX_GWXXB t ");
		sql.append(" left join XG_QGZX_YRDWDMB t1 on t.yrdwid = t1.id ");
		sql.append(" left join ZXBZ_XXBMDM t2 on t1.xydm = t2.bmdm ");
		sql.append(" left join XG_QGZX_GWXZDMB t3 on t.gwlb = t3.gwxzdm");
		sql.append(") a where 1=1 ");
		QgzxGlyglService qgzxGlyglService = new QgzxGlyglService();
		//如果不是勤工管理员
		if(!qgzxGlyglService.sfQggly(user.getUserName())){
			sql.append(" and (a.yhm ='"+user.getUserName()+"' or a.zgh = '"+user.getUserName()+"') ");
		}
		sql.append(searchTj);
		return getPageList(t,sql.toString(),inputValue);
	}

	public List<HashMap<String, String>> getJgPageList(QgzxGwglForm t, User user)
			throws Exception {
		// TODO 自动生成方法存根
		//生成高级查询相关条件、条件值
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputValue = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (");
		sql.append("select t.sjly,t.gwdm,t.gwmc,t.gwxzdm,t.gwlx,case when t.gwxzdm = '0' then '临时' else '正式' end gzxzmc,t.xqrs,t.fbsj,t.xn ");
		sql.append(" ,case when t.cq = '1' then '长期有效' else t.zpjssj end zpjssj");
		sql.append(" ,nvl(t1.yrdwmc,t2.bmmc) yrdwmc,t1.dwlb,t.gwlb,t3.gwxzmc gwlbmc ");
		sql.append(" ,t1.yhm,t1.zgh,t.shzt,t.splc,t.zpkssj");
		sql.append(" ,decode(t.shzt,  '0', '未提交', '1', '通过', '2', '不通过', '3', ");
		sql.append(" '退回', '4', '需重审', '5', '审核中', '', '无需审核', t.shzt) shztmc");
		sql.append(" ,(select count(1) from xg_qgzx_new_xsgwxxb a where a.gwdm = t.gwdm and a.zgzt = 'zg') zgrs");
		sql.append(" ,(select count(1) from xg_qgzx_new_xsgwxxb a where a.gwdm = t.gwdm and a.zgzt = 'tg') lzrs");
		sql.append(" from XG_QGZX_GWXXB t ");
		sql.append(" left join XG_QGZX_YRDWDMB t1 on t.yrdwid = t1.id ");
		sql.append(" left join ZXBZ_XXBMDM t2 on t1.xydm = t2.bmdm ");
		sql.append(" left join XG_QGZX_GWXZDMB t3 on t.gwlb = t3.gwxzdm");
		sql.append(" where t.shzt = '1'");
		sql.append(") a where 1=1 ");
		QgzxGlyglService qgzxGlyglService = new QgzxGlyglService();
		//如果不是勤工管理员
		if(!qgzxGlyglService.sfQggly(user.getUserName())){
			sql.append(" and (a.yhm ='"+user.getUserName()+"' or a.zgh = '"+user.getUserName()+"') ");
		}
		sql.append(searchTj);
		return getPageList(t,sql.toString(),inputValue);
	}

	public List<HashMap<String, String>> getExportList(QgzxGwglForm t, User user)
			throws Exception {
		// TODO 自动生成方法存根
		//生成高级查询相关条件、条件值
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputValue = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (");
		sql.append("select t.sjly,t.gwdm,t.gwmc,t.gwxzdm,case when t.gwxzdm = '0' then '临时' else '正式' end gzxzmc,t.xqrs,t.fbsj,t.xn ");
		sql.append(" ,case when t.cq = '1' then '长期有效' else t.zpjssj end zpjssj");
		sql.append(" ,nvl(t1.yrdwmc,t2.bmmc) yrdwmc,t1.dwlb,t3.gwxzmc gwlbmc ");
		sql.append(" ,t1.yhm,t1.zgh,t.shzt,t.splc,t.zpkssj,t1.bgdd ");
		sql.append(" ,decode(t.shzt,  '0', '未提交', '1', '通过', '2', '不通过', '3', ");
		sql.append(" '退回', '4', '需重审', '5', '审核中', '', '无需审核', t.shzt) shztmc");
		sql.append(" ,(select count(1) from xg_qgzx_new_xsgwxxb a where a.gwdm = t.gwdm and a.zgzt = 'zg') zgrs");
		sql.append(" ,(select count(1) from xg_qgzx_new_xsgwxxb a where a.gwdm = t.gwdm and a.zgzt = 'tg') lzrs");
		sql.append(" ,(select wm_concat(b.xm) from xg_qgzx_new_xsgwxxb a left join view_xsjbxx b on a.xh = b.xh where a.gwdm = t.gwdm group by a.gwdm ) zgxsxm");
		sql.append(" ,nvl(t1.xm,t4.xm) xm,t1.lxdh");
		sql.append(" from XG_QGZX_GWXXB t ");
		sql.append(" left join XG_QGZX_YRDWDMB t1 on t.yrdwid = t1.id ");
		sql.append(" left join ZXBZ_XXBMDM t2 on t1.xydm = t2.bmdm ");
		sql.append(" left join XG_QGZX_GWXZDMB t3 on t.gwlb = t3.gwxzdm");
		sql.append(" left join fdyxxb t4 on t1.zgh = t4.zgh ");
		sql.append(") a where 1=1 ");
		QgzxGlyglService qgzxGlyglService = new QgzxGlyglService();
		//如果不是勤工管理员
		if(!qgzxGlyglService.sfQggly(user.getUserName())){
			sql.append(" and (a.yhm ='"+user.getUserName()+"' or a.zgh = '"+user.getUserName()+"') ");
		}
		sql.append(searchTj);
		return dao.getListNotOut(sql.toString(),inputValue);
	}
	public List<HashMap<String, String>> getYrdwList(QgzxGwglForm t, User user)
			throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (");
		sql.append(" select a.id,nvl(yrdwmc,b.bmmc) yrdwmc,nvl(a.xm,c.xm) xm,a.dwlb");
		sql.append(" from xg_qgzx_yrdwdmb a");
		sql.append(" left join ZXBZ_XXBMDM b on a.xydm = b.bmdm");
		sql.append(" left join yhb c on a.zgh = c.yhm");
		sql.append(" ) t where 1=1 ");
		sql.append(" and not exists (select 1 from xg_qgzx_hmdglb where dwid = t.id)");
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}
	public boolean insertGwxx(QgzxGwglForm t)throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("insert into XG_QGZX_GWXXB(gwdm,gwmc,xn,gwxzdm,xqrs,gwlx,gwlb,gwcjsx,gssx,zpkssj,zpjssj,cq,sfzd,sfxsgz,gwryyq,shzt,fbsj,splc,yrdwid,fjid,xq)");
		sql.append(" values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
		String[] input = {t.getGwdm(),t.getGwmc(),t.getXn(),t.getGwxzdm(),t.getXqrs(),t.getGwlx(),t.getGwlb(),t.getGwcjsx(),
				t.getGssx(),t.getZpkssj(),t.getZpjssj(),t.getCq(),t.getSfzd(),t.getSfxsgz(),t.getGwryyq(),
				t.getShzt(),t.getFbsj(),t.getSplc(),t.getYrdwid(),t.getFjid(),t.getXq()};
		return dao.runUpdate(sql.toString(),input);
	}
	public boolean updateGwxx(QgzxGwglForm t)throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("update XG_QGZX_GWXXB set gwmc=?, gwxzdm=?, xqrs=?, gwlx=?,");
		sql.append(" gwlb=?, gwcjsx=?, gssx=?, zpkssj=?, zpjssj=?, cq=?, sfzd=?,");
		sql.append(" sfxsgz=?, gwryyq=?, shzt=?, fbsj=?, fjid=?");
		sql.append(" where gwdm =?");
		String[] input = {t.getGwmc(),t.getGwxzdm(),t.getXqrs(),t.getGwlx(),t.getGwlb(),t.getGwcjsx(),
				t.getGssx(),t.getZpkssj(),t.getZpjssj(),t.getCq(),t.getSfzd(),t.getSfxsgz(),t.getGwryyq(),
				t.getShzt(),t.getFbsj(),t.getFjid(),t.getGwdm()};
		return dao.runUpdate(sql.toString(),input);
	}
	public List<HashMap<String, String>> getGwfbshList(QgzxGwglForm t, User user)throws Exception {
		//生成高级查询相关条件、条件值
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
//		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
//		String shgwzByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		String[] inputValue = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (");
		sql.append("select t.gwdm,t.gwmc,nvl(t1.yrdwmc,t2.bmmc) yrdwmc,t1.id yrdwid,t1.dwlb,t3.gwxzmc gwlbmc ");
		sql.append(" ,t.gwxzdm,t.xqrs,t.fbsj,t4.shzt,t4.guid shid,t4.gwid,t.splc ");
		sql.append(" ,case when t.cq = '1' then '长期有效' else t.zpjssj end zpjssj");
		sql.append(" ,t6.mc || '[' || ");
		sql.append(" decode(t4.shzt, '0', '未审核', '1', '通过', '2', '不通过', '3',  '退回', '4', '需重审', '5', '审核中') || ']' shztmc,");
		sql.append(" row_number() over(partition by t.gwdm order by t4.shsj desc) rn ");
		sql.append(" from XG_QGZX_GWXXB t");
		sql.append(" left join XG_QGZX_YRDWDMB t1 on t.yrdwid = t1.id ");
		sql.append(" left join ZXBZ_XXBMDM t2 on t1.xydm = t2.bmdm ");
		sql.append(" left join XG_QGZX_GWXZDMB t3 on t.gwlb = t3.gwxzdm");
		sql.append(" left join xg_xtwh_shztb t4 on t.gwdm = t4.ywid ");
		sql.append(" left join xg_xtwh_spgwyh t5 on t4.gwid = t5.spgw ");
		sql.append(" left join xg_xtwh_spgw t6 on t4.gwid = t6.id ");
		sql.append(" where t5.spyh = '"+user.getUserName()+"'");
		String shlx = t.getShzt();
		if (!shlx.equals("dsh")) {
			sql.append(" and (t4.shzt<>0 and  t4.shzt<>4)");
		} else {
			sql.append(" and (t4.shzt=0  or t4.shzt = 4 )");
		}
		sql.append(" ) t where 1=1 and rn = 1 ");
//		sql.append(searchTjByUser);
		sql.append(searchTj);
//		sql.append(shgwzByUser);
		return getPageList(t,sql.toString(),inputValue);
	}

	public List<HashMap<String,String>> getGwlbList(){
		return dao.getListNotOut("select * from XG_QGZX_GWXZDMB order by GWXZDM",new String[]{});
	}

	public HashMap<String,String> getGwlbById(String id){
		String[] out = {"gwxcsx","gssx","label"};
		return dao.getMap("select * from XG_QGZX_GWXZDMB where GWXZDM=?",new String[]{id},out);
	}

	public HashMap<String,String> getGwxxByGwdm(String gwdm){
		StringBuilder sql = new StringBuilder();
		sql.append("select t.*,t3.fid uploadid,t1.gwxzmc,nvl(t2.yrdwmc,t3.bmmc) yrdwmc,t4.xqmc from XG_QGZX_GWXXB t left join XG_QGZX_GWXZDMB t1 on t.gwlb = t1.gwxzdm ");
		sql.append(" left join xg_comm_fileupload_data t3 on t.fjid=t3.gid ");
		sql.append(" left join XG_QGZX_YRDWDMB t2 on t.yrdwid = t2.id ");
		sql.append(" left join ZXBZ_XXBMDM t3 on t2.xydm = t3.bmdm");
		sql.append(" left join xqdzb t4 on t.xq = t4.xqdm");
		sql.append(" where t.gwdm = ?");
		return dao.getMapNotOut(sql.toString(),new String[]{gwdm});
	}

	public boolean updateGwfbSq(QgzxGwglForm model) throws Exception {
		String[] inputV = new String[3];
		StringBuilder sql = new StringBuilder();
		sql.append(" update XG_QGZX_GWXXB ");
		sql.append(" set shzt = ? ,splc = ? where gwdm = ?");
		inputV[0] = model.getShzt();
		inputV[1] = model.getSplc();
		inputV[2] = model.getGwdm();
		return dao.update(sql.toString(),inputV)>0 ? true:false;
	}
	public boolean updateGwfbSqxx(QgzxGwglForm model) throws Exception {
		String[] inputV = new String[4];
		StringBuilder sql = new StringBuilder();
		sql.append(" update XG_QGZX_GWXXB ");
		sql.append(" set shzt = ?,sfzd = ?,sfxsgz=? where gwdm = ?");
		inputV[0] = model.getShzt();
		inputV[1] = model.getSfzd();
		inputV[2] = model.getSfxsgz();
		inputV[3] = model.getGwdm();
		return dao.update(sql.toString(),inputV)>0 ? true:false;
	}
	public HashMap<String, String> getYrdwByUser(String yhm){
		StringBuilder sql = new StringBuilder();
		sql.append("select nvl(yrdwmc,t1.bmmc) yrdwmc, t.id yrdwid from XG_QGZX_YRDWDMB t ");
		sql.append(" left join ZXBZ_XXBMDM t1 on t.xydm = t1.bmdm ");
		sql.append(" where yhm = ? or zgh = ?");
		return dao.getMap(sql.toString(),new String[]{yhm,yhm},new String[]{"yrdwmc","yrdwid"});
	}

	public String getDwlb(String yhm){
		StringBuilder sql = new StringBuilder();
		sql.append("select dwlb from XG_QGZX_YRDWDMB where yhm = ? or zgh = ?");
		return dao.getOneRs(sql.toString(),new String[]{yhm,yhm},"dwlb");
	}
	/*
	    描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	*/
	
	@Override
	protected void setTableInfo() {
		// TODO 自动生成方法存根
		this.setTableName("XG_QGZX_GWXXSQB");
		this.setKey("sqid");
		this.setClass(QgzxGwglForm.class);
	}
	
	public List<HashMap<String, String>> getGwxxPageList(QgzxGwglForm t, User user)
		throws Exception {
		//生成高级查询相关条件、条件值 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputValue = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		//权限控制 	
		String searchTjQx = "";
		QgzxGlyglService qgzxGlyglService = new QgzxGlyglService();
		//如果不是勤工管理员
		if(!qgzxGlyglService.sfQggly(user.getUserName())){
			searchTjQx+=" and yrdwdm = '"+user.getUserDep()+"' ";
		}
		sql.append(" select a.*,decode(b.bmlb, '1', '校级', '5', '院级') bmlb,rownum r from view_xg_qgzx_gwxxb a " +
				"left join ZXBZ_XXBMDM b on b.bmdm=a.yrdwdm  where 1=1 ");
		sql.append(searchTj);
		sql.append(searchTjQx);
		return getPageList(t, sql.toString(), inputValue);
	}
	public List<HashMap<String, String>> getGwsqPageList(QgzxGwglForm t, User user)
		throws Exception {
		//生成高级查询相关条件、条件值 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputValue = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		//权限控制 	
		String searchTjQx = "";
//		QgzxGlyglService qgzxGlyglService = new QgzxGlyglService();
		//如果不是勤工管理员
/*		if(!qgzxGlyglService.sfQggly(user.getUserName())){
			searchTjQx+=" and yrdwdm = '"+user.getUserDep()+"' ";
		}*/

//		sql.append(" select a.*,rownum r,case when a.shzt='通过'or a.shzt='不通过' then 'disabled' end as dis from view_xg_qgzx_gwxxsqb a where 1=1 ");

		sql.append("select * from (");
		sql.append("select t.sqid,t.xh,t2.xm,t2.pycc,t2.zybjmc bjmc,t2.zybj bjdm");
		sql.append(" ,t.gwmc,t.yrdwdm,nvl(t1.yrdwmc,t3.bmmc) yrdwmc,t.sqsj");
		sql.append(" ,decode(t.shzt,  '0', '未提交', '1', '通过', '2', '不通过', '3', ");
		sql.append(" '退回', '4', '需重审', '5', '审核中', '', '无需审核', t.shzt) shztmc");
		sql.append(" ,t.splcid ");
		sql.append(" from XG_QGZX_GWXXSQB t");
		sql.append(" left join XG_QGZX_YRDWDMB t1 on t.yrdwdm = t1.id");
		sql.append(" left join ZXBZ_XXBMDM t3 on t1.xydm = t3.bmdm");
		sql.append(" left join view_xsjbxx t2 on t.xh = t2.xh");
		sql.append(" ) a where 1=1 ");
		sql.append(searchTj);
		sql.append(searchTjQx);
		return getPageList(t, sql.toString(), inputValue);
	}
	public List<HashMap<String, String>> getGwshPageList(QgzxGwglForm t, User user)
		throws Exception {
		//生成高级查询相关条件、条件值 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputValue = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		//权限控制 	
		String searchTjQx = "";
		QgzxGlyglService qgzxGlyglService = new QgzxGlyglService();
		//如果不是勤工管理员
		if(!qgzxGlyglService.sfQggly(user.getUserName())){
			searchTjQx+=" and a.yrdwdm = '"+user.getUserDep()+"' ";
		}
		sql.append(" select * from (");
		sql.append(" select a.*,rownum r,case when (select count(1) from xg_qgzx_gwxxb b where a.gwdm = b.gwdm) <> '0' or");
		sql.append(" a.shzt = '2' then 'disabled' end as dis,");
		sql.append(" t4.guid shid,t4.gwid,t6.mc || '[' || decode(t4.shzt, '0', '未审核', '1', '通过', '2', '不通过', '3',  '退回', '4', '需重审', '5', '审核中') || ']' shztmcx,t6.gwz,t4.shzt shztx,");
		sql.append(" row_number() over(partition by a.gwdm order by t4.shsj desc) rn");
		sql.append(" from view_xg_qgzx_gwxxsqb a");
		sql.append(" left join xg_xtwh_shztb t4 ");
		sql.append(" on  a.gwdm = t4.ywid");
		sql.append(" left join xg_xtwh_spgwyh t5");
		sql.append(" on  t4.gwid = t5.spgw");
		sql.append(" left join xg_xtwh_spgw t6");
		sql.append(" on t4.gwid = t6.id");
		sql.append(" left join xg_xtwh_spbz t7 on t7.splc =t4.lcid and t7.spgw = t4.gwid ");
		sql.append(" where t5.spyh ='" + user.getUserName() + "' ");
		String shlx = t.getShzt();
		if (!shlx.equals("dsh")) {
			sql.append(" and (t4.shzt<>0 and  t4.shzt<>4)");
		} else {
			sql.append(" and (t4.shzt=0  or t4.shzt = 4 )");
		}
		//浙江中医药个性化判断
		if(Base.xxdm.equals(Globals.XXDM_ZJZYYDX)){
			sql.append(" and ((t7.xh = 1 and a.ssbm = '"+user.getUserDep()+"') or (t7.xh=2 and a.ssxq  in (select xq from xg_zjzyy_xqdzb where zgh = '" + user.getUserName() + "')) or t7.xh > 2 ) ");
		}
		if(!"10344".equals(Base.xxdm)){
			sql.append(searchTjQx);
		}
		sql.append(" )t where 1= 1 ");
		sql.append(" and  rn = 1  ");
		sql.append(" ");
		sql.append(" ");
		sql.append(" ");
		sql.append(" ");
		sql.append(searchTj);
	
		return getPageList(t, sql.toString(), inputValue);
	}
	
	/**
	 * 获得岗位信息List
	 * @param myForm
	 * @return
	 * @throws Exception
	 */
	public ArrayList<String[]> getGwsqList(QgzxGwglForm myForm) throws Exception {
		// 高级查询MODEL
		SearchModel searchModel = myForm.getSearchModel();
		// 用户对象
		User user = myForm.getUser();
		// 高级查询条件
		String searchTj = SearchService.getSearchTj(searchModel);
		String[] inputV = SearchService.getTjInput(searchModel);
		//权限控制 	
		String searchTjQx = "";
		QgzxGlyglService qgzxGlyglService = new QgzxGlyglService();
		//如果不是勤工管理员
		if(!qgzxGlyglService.sfQggly(user.getUserName())){
			searchTjQx+=" and yrdwdm = '"+user.getUserDep()+"' ";
		}
		StringBuilder sql = new StringBuilder();
		String[] colList = new String[] { "gwdm","r","xn", "yrdwmc", "gwmc","gwxzmc", "xqrs", "knsrs", "sqsj", "shzt" };
		sql.append(" select a.*,rownum r,case when a.shzt='通过'or a.shzt='不通过' then 'disabled' end as dis from view_xg_qgzx_gwxxsqb a where 1=1 ");
		//	
		
		// ====================SQL拼装 end================================
		return CommonQueryDAO.commonQuery(sql.toString(), searchTj+searchTjQx, inputV, colList, myForm);
	}
	
	
	/**
	 * 获得岗位信息List
	 * @param myForm
	 * @return
	 * @throws Exception
	 */
	public ArrayList<String[]> getGwxxList(QgzxGwglForm myForm) throws Exception {
		// 高级查询MODEL
		SearchModel searchModel = myForm.getSearchModel();
		// 用户对象
		User user = myForm.getUser();
		// 高级查询条件
		String searchTj = SearchService.getSearchTj(searchModel);
		String[] inputV = SearchService.getTjInput(searchModel);
		//权限控制 	
		String searchTjQx = "";
		QgzxGlyglService qgzxGlyglService = new QgzxGlyglService();
		//如果不是勤工管理员
		if(!qgzxGlyglService.sfQggly(user.getUserName())){
			searchTjQx+=" and yrdwdm = '"+user.getUserDep()+"' ";
		}
		StringBuilder sql = new StringBuilder();
		String[] colList=null;
		if("10052".equals(Base.xxdm)){
			 colList = new String[] { "gwdm", "r", "xn", "yrdwmc", "gwxh","gwmc", "gwxzmc","xqrs", "zgrs","tgrs" };
		}else{
			 colList = new String[] { "gwdm", "r", "xn", "yrdwmc","gwmc", "gwxzmc","xqrs", "zgrs","tgrs" };
		}
		
		sql.append(" select a.*,rownum r from view_xg_qgzx_gwxxb a where 1=1 ");
		// ====================SQL拼装 end================================
		return CommonQueryDAO.commonQuery(sql.toString(), searchTj+searchTjQx, inputV, colList, myForm);
	}
	
	
	/**
	 * 获得岗位信息List
	 * @param myForm
	 * @return
	 * @throws Exception
	 */
	public ArrayList<String[]> getGwshList(QgzxGwglForm myForm) throws Exception {
		// 高级查询MODEL
		SearchModel searchModel = myForm.getSearchModel();
		// 用户对象
		User user = myForm.getUser();
		// 高级查询条件
		String searchTj = SearchService.getSearchTj(searchModel);
		String[] inputV = SearchService.getTjInput(searchModel);
		//权限控制 	
		String searchTjQx = "";
		QgzxGlyglService qgzxGlyglService = new QgzxGlyglService();
		//如果不是勤工管理员
		if(!qgzxGlyglService.sfQggly(user.getUserName())){
			searchTjQx+=" and yrdwdm = '"+user.getUserDep()+"' ";
		}
		StringBuilder sql = new StringBuilder();
		String[] colList = new String[] { "gwdm","dis", "r", "xn", "yrdwmc","gwmc","gwxzmc", "xqrs", "knsrs", "sqsj", "shzt" };
		sql.append(" select a.*,rownum r,case when (select count(1) from xg_qgzx_gwxxb b where a.gwdm = b.gwdm)<>'0' or a.shzt = '不通过'then'disabled' end as dis from view_xg_qgzx_gwxxsqb a where 1=1 ");
		// ====================SQL拼装 end================================
		return CommonQueryDAO.commonQuery(sql.toString(), searchTj+searchTjQx, inputV, colList, myForm);
	}
	
	
	/**
	 * 学生信息列表
	 * @param myForm
	 * @param searchTj2 
	 * @return
	 * @throws Exception
	 */
	public ArrayList<String[]> getStuList(QgzxGwglForm myForm,HttpServletRequest request) throws Exception{
		// 高级查询MODEL
		SearchModel searchModel = myForm.getSearchModel();
		// 高级查询条件
		String searchTj = SearchService.getSearchTj(searchModel);
		String[] inputV = SearchService.getTjInput(searchModel);
		//权限控制 	
		SearchService searchService=new SearchService();
		String searchTjQx = searchService.getSearchTjByUser(request, "a","xydm", "bjdm");
		
		StringBuilder sql = new StringBuilder();
		String[] colList = new String[] { "xh", "r", "xh", "xm", "xb", "nj", "xymc", "zymc", "bjmc","sfkns" };
		
		// 困难生项目代码
		String xmdm = XszzXmdm.XSZZ_KNS;
		HashMap<String, String> xmInfo = dao.getMapNotOut("select * from view_xszz_comm_xmwh where pk=? ", new String[]{xmdm});
		// 困难生评奖周期
		String sqzq = xmInfo.get("sqzq");
		// 困难生审核级别
		String shjb = xmInfo.get("shjb");
		String xn = Base.currXn;
		String xq = Base.currXq;
		String nd = Base.currNd;
		sql.append(" select a.*,rownum r from (select a.*,  decode(b.xh ,null,'否','是') sfkns from view_xsjbxx a left join  ");
		sql.append(" (select b.xh,max(b.sqsj) from xg_xszz_new_knsjgb b ");
		

		//加入学年查询条件， 使勤工岗位人员只能是当前岗位学院的困难生
		sql.append(" where b.xn='");
		sql.append(myForm.getXn());
		
		sql.append("' group by b.xh ");
		if("yes".equals(request.getParameter("sfxyzgsc"))){
			sql.append(") b on a.xh = b.xh where not exists(select 1 from xg_qgzx_xsgwxxb c where a.xh = c.xh and zgzt='zg' and gwdm = '"+myForm.getPkValue()+"') and a.xh in(select xh from xg_qgzx_qgzxxsb)) a where 1=1");	
		}
		else{
			sql.append(") b on a.xh = b.xh where not exists(select 1 from xg_qgzx_xsgwxxb c where a.xh = c.xh and zgzt='zg' and gwdm = '"+myForm.getPkValue()+"') order by a.nj,a.xydm,a.zydm,a.bjdm,a.xh) a where 1=1");
		}
				
		// ====================SQL拼装 end================================
		return CommonQueryDAO.commonQuery(sql.toString(), searchTj+searchTjQx, inputV, colList, myForm);
	}
	public List<HashMap<String, String>> getStuPageList(QgzxGwglForm t, User user, String sfxyzgsc)
		throws Exception {
		//生成高级查询相关条件、条件值 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputValue = SearchService.getTjInput(t.getSearchModel());
		//权限控制 	
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");
		
		// 困难生项目代码
//		String xmdm = XszzXmdm.XSZZ_KNS;
//		HashMap<String, String> xmInfo = dao.getMapNotOut("select * from view_xszz_comm_xmwh where pk=? ", new String[]{xmdm});
		// 困难生评奖周期
//		String sqzq = xmInfo.get("sqzq");
		// 困难生审核级别
//		String shjb = xmInfo.get("shjb");
//		String xn = Base.currXn;
//		String xq = Base.currXq;
//		String nd = Base.currNd;
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.*,rownum r from (select a.*,  decode(b.xh ,null,'否','是') sfkns from view_xsjbxx a left join  ");
		if("10335".equals(Base.xxdm)){
			sql.append(" (select b.xh,max(b.sqsj) from view_knsjgb_fqxrd b ");
		}else{
			sql.append(" (select b.xh,max(b.sqsj) from xg_xszz_new_knsjgb b ");
			//加入学年查询条件， 使勤工岗位人员只能是当前岗位学院的困难生
			sql.append(" where b.xn='" + Base.currXn + "' ");
			if ("xq".equals(SQZQ)) {
				sql.append(" and b.xq = '"+Base.currXq+"' ");
			}
		}
		sql.append(" group by b.xh ");
		
		if("10125".equals(Base.xxdm)) {
			if("yes".equals(sfxyzgsc)){
				sql.append(") b on a.xh = b.xh where not exists(select 1 from xg_qgzx_xsgwxxb c where a.xh = c.xh and zgzt='zg' and gwdm = '"+t.getPkValue()+"') and exists (select 1 from xg_qgzx_xsgwsqb t1 where a.xh = t1.xh and shzt = '1') and a.xh in(select xh from xg_qgzx_qgzxxsb)) a where 1=1 ");	
			}else{
				sql.append(") b on a.xh = b.xh where not exists(select 1 from xg_qgzx_xsgwxxb c where a.xh = c.xh and zgzt='zg' and gwdm = '"+t.getPkValue()+"') and exists (select 1 from xg_qgzx_xsgwsqb t1 where a.xh = t1.xh and shzt = '1') order by a.nj,a.xydm,a.zydm,a.bjdm,a.xh) a where 1=1 ");
			}
			
		}else {
			if("yes".equals(sfxyzgsc)){
				sql.append(") b on a.xh = b.xh where not exists(select 1 from xg_qgzx_xsgwxxb c where a.xh = c.xh and zgzt='zg' and gwdm = '"+t.getPkValue()+"') and a.xh in(select xh from xg_qgzx_qgzxxsb)) a where 1=1 ");	
			}else{
				sql.append(") b on a.xh = b.xh where not exists(select 1 from xg_qgzx_xsgwxxb c where a.xh = c.xh and zgzt='zg' and gwdm = '"+t.getPkValue()+"') order by a.nj,a.xydm,a.zydm,a.bjdm,a.xh) a where 1=1 ");
			}
		}
		if(Base.xxdm.equals("10704")){//西安科技大学个性化（退岗一年之内不得再次申请）
			sql.append(" and not exists (select 1 from (select xh from xg_qgzx_new_xsgwxxb where zgzt = 'tg' and to_date(to_char(add_months(to_date(tgsj,'yyyy-MM-dd'),12),'yyyy-MM-dd'),'yyyy-MM-dd') > to_date(to_char(sysdate,'yyyy-MM-dd'),'yyyy-MM-dd')) d where a.xh = d.xh)");
		}
		sql.append(searchTjByUser);
		sql.append(searchTj);
		
		return getPageList(t, sql.toString(), inputValue);
	}
	
	/**
	 * 获得岗位信息Map
	 * @param myForm
	 * @return
	 */
	public HashMap<String,String> getGwxxMap(QgzxGwglForm myForm){
		StringBuilder sql = new StringBuilder();
		if("10511".equals(Base.xxdm)){
			sql.append("  select t.*,t1.zjlymc,t2.zxdwlbmc from view_xg_qgzx_gwxxb t");
			sql.append(" left join ZXBZ_ZJLY t1");
			sql.append(" on t.zjly = t1.zjlydm");
			sql.append(" left join ZXBZ_ZXDWLB t2");
			sql.append(" on t.zxdwlb = t2.zxdwlbdm");
			sql.append(" where t.gwdm =?");
		}else if("10344".equals(Base.xxdm)){
			sql.append(" select t.*, t2.bmmc ssbmmc,t1.lxdh,t1.lsbgs,t1.gzdd,t1.gzsj,t1.gznr,t3.xm fzlsxm,t4.xqmc ssxqmc ");
			sql.append(" from view_xg_qgzx_gwxxb t");
			sql.append(" left join xg_qgzx_gwxxb t1");
			sql.append(" on t.gwdm = t1.gwdm");
			sql.append(" left join zxbz_xxbmdm t2");
			sql.append(" on t1.ssbm = t2.bmdm");
			sql.append(" left join fdyxxb t3 on t1.fzls = t3.zgh");
			sql.append(" left join dm_zju_xq t4 on t1.ssxq = t4.dm");
			sql.append(" where t.gwdm =?");
			sql.append(" ");
		}else if("10351".equals(Base.xxdm)){//温州大学个性化
			sql.append(" select a.* from");
			sql.append(" (select t.xn,t.xq,t.gwxh,t.gwdm,t.gwmc,t.yrdwdm,t.yrdwmc,t.gwxzdm,t.gwxzmc,t.xqrs,");
			sql.append(" t.knsrs,t.zgrs,t.gwcjsx,t.yxssz,t.sfsgwsqsxz,t.gwkssj,t.gwjssj,t.sfyxgw,t.gwms,t.gwryyq,");
			sql.append(" t.gwyqryxg,t.bz,t.yxsszmc,t.sfsgwsqsxzmc,t.gwyxs,");
			sql.append("  t2.lxr,t2.lxdh,a.sqxyms");
			sql.append(" from view_xg_qgzx_gwxxb t");
			sql.append(" left join (select a.gwdm, ");
			sql.append("            replace(wm_concat(b.bmmc), ';', ',') sqxyms ");
			sql.append("            from xg_qgzx_gwxxsqxyglb a ");
			sql.append("            left join zxbz_xxbmdm b ");
			sql.append("            on a.xydm = b.bmdm ");
			sql.append("            group by a.gwdm ) a ");
			sql.append(" on a.gwdm = t.gwdm ");
			sql.append(" left join xg_qgzx_gwxxb t2");
			sql.append(" on t.gwdm = t2.gwdm");
			sql.append(" )a");
			sql.append(" where a.gwdm = ?");			
		}else if("11488".equals(Base.xxdm)){
			sql.append(" select t.*,t1.gwlx,t1.lxdh from view_xg_qgzx_gwxxb t");
			sql.append(" left join xg_qgzx_gwxxb t1");
			sql.append(" on t.gwdm = t1.gwdm");
			sql.append(" where t.gwdm = ?");
		}else{
			sql.append("select * from view_xg_qgzx_gwxxb where gwdm =? ");
		}
		
		String[] inputValue = {myForm.getPkValue()};
		return dao.getMapNotOut(sql.toString(), inputValue);
	}
	
	/**
	 * 获得岗位申请Map
	 * @param myForm
	 * @return
	 */
	public HashMap<String,String> getGwsqMap(QgzxGwglForm myForm){
		StringBuilder sql = new StringBuilder();
		if("10511".equals(Base.xxdm)){
			sql.append("  select t.*,t1.zjlymc,t2.zxdwlbmc from view_xg_qgzx_gwxxsqb t");
			sql.append(" left join ZXBZ_ZJLY t1");
			sql.append(" on t.zjly = t1.zjlydm");
			sql.append(" left join ZXBZ_ZXDWLB t2");
			sql.append(" on t.zxdwlb = t2.zxdwlbdm");
			sql.append(" where t.gwdm =?");
		}else if("10344".equals(Base.xxdm)){
			sql.append(" select t.*, t2.bmmc ssbmmc,t1.lxdh,t1.lsbgs,t1.fzls,t1.gzdd,t1.gzsj,t1.gznr,t3.xm fzlsxm,t4.xqmc ssxqmc  ");
			sql.append(" from view_xg_qgzx_gwxxsqb t");
			sql.append(" left join xg_qgzx_gwxxsqb t1");
			sql.append(" on t.gwdm = t1.gwdm");
			sql.append(" left join zxbz_xxbmdm t2");
			sql.append(" on t1.ssbm = t2.bmdm");
			sql.append(" left join fdyxxb t3 on t1.fzls = t3.zgh");
			sql.append(" left join dm_zju_xq t4 on t1.ssxq = t4.dm");
			sql.append(" where t.gwdm =?");
			sql.append(" ");
		}else if("10351".equals(Base.xxdm)){//温州大学个性化
			sql.append(" select a.* from");
			sql.append(" (select t1.lxr,t1.lxdh,t.splcid,t.xn,t.xq,t.gwxh,t.gwdm,t.yrdwdm,t.yrdwmc,t.gwmc,t.gwxzdm,t.gwxzmc," );
			sql.append(" t.xqrs,t.knsrs,t.gwms,t.gwryyq,t.bz,t.gwcjsx,t.shzt,t.sqr,t.sqsj,t.shr,t.shsj,t.shyj,t.yxsszmc,");
			sql.append(" t.sfsgwsqsxzmc,t.gwyxs,");
			sql.append(" t.gwyqryxg,t.yxssz,t.sfsgwsqsxz,t.gwkssj,t.gwjssj,t.sfyxgw,t.gwcjbz,t.jfhb,t.zc,a.sqxyms");
			sql.append(" from view_xg_qgzx_gwxxsqb t");
			sql.append(" left join (select a.gwdm, ");
			sql.append("            replace(wm_concat(b.bmmc), ';', ',') sqxyms ");
			sql.append("            from xg_qgzx_gwxxsqxyglb a ");
			sql.append("            left join zxbz_xxbmdm b ");
			sql.append("            on a.xydm = b.bmdm ");
			sql.append("            group by a.gwdm ) a ");
			sql.append(" on a.gwdm = t.gwdm ");
			sql.append(" left join xg_qgzx_gwxxsqb t1");
			sql.append(" on t.gwdm = t1.gwdm");
			sql.append(" )a");
			sql.append(" where a.gwdm = ?");			
		}else if("11488".equals(Base.xxdm)){
			sql.append(" select t.*,t1.gwlx,t1.lxdh,t1.fzls from view_xg_qgzx_gwxxsqb t");
			sql.append(" left join xg_qgzx_gwxxsqb t1");
			sql.append(" on t.gwdm = t1.gwdm");
			sql.append(" where t.gwdm = ?");
		}else{
			sql.append("select * from view_xg_qgzx_gwxxsqb where gwdm =? ");
		}
		
		String[] inputValue = {myForm.getPkValue()};
		return dao.getMapNotOut(sql.toString(), inputValue);
	}
	/** 
	 * @描述:(获取审批流程岗位)
	 * @作者：陈敏杰[工号：913]
	 * @日期：2013-10-28 下午07:05:16
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * ArrayList<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public ArrayList<HashMap<String, String>> getSplcgw(QgzxGwglForm model) {
		String sql="select * from xg_xtwh_spbz where splc=? order by xh ";
		return (ArrayList<HashMap<String, String>>) dao.getListNotOut(sql, new String[]{model.getSplcid()});
	}
	
	/**
	 * 保存岗位信息
	 * @param myForm
	 * @return
	 * @throws Exception 
	 */
	public boolean gwxxBc(QgzxGwglForm myForm) throws Exception {
		//String gwdm = myForm.getGwdm();
		String xn = myForm.getXn();
		String yrbm = myForm.getYrbm();
		String gwmc = myForm.getGwmc();
		String gwxzdm = myForm.getGwxzdm();
		String xqrs = myForm.getXqrs();
		String knsrs = myForm.getKnsrs();
		String yxssz = myForm.getYxssz();
		String sfsgwsqsxz = myForm.getSfsgwsqsxz();
		String gwkssj = myForm.getGwkssj();
		String gwjssj = myForm.getGwjssj();
		String gwms = myForm.getGwms();
		String gwryyq = myForm.getGwryyq();
		String gwyqryxg = myForm.getGwyqryxg();
		String bz = myForm.getBz();
		String gwcjsx=myForm.getGwcjsx();
		String zjly =myForm.getZjly();
		String zxdwlb=myForm.getZxdwlb();
		String ssbm = myForm.getSsbm();
		String fzls = myForm.getFzls();
		String lxdh = myForm.getLxdh();
		String lsbgs = myForm.getLsbgs();
		String gzdd = myForm.getGzdd();
		String gzsj = myForm.getGzsj();
		String gznr = myForm.getGznr();
		String xq = myForm.getXq();
		String gwcjbz = myForm.getGwcjbz();
		String jfhb = myForm.getJfhb();
		String zc = myForm.getZc();
		String ssxq = myForm.getSsxq();
		String gwshr="";
		String gwshrxm="";
		if("12867".equals(Base.xxdm)){
			 gwshr = myForm.getGwshr();
			 gwshrxm = myForm.getGwshrxm();
		}else {
			 gwshr="";
			 gwshrxm="";
		}
		String sql = "";
		if("10511".equals(Base.xxdm)){
			  sql = "insert into xg_qgzx_gwxxb (gwxh,xn,yrdwdm,gwmc,gwxzdm,xqrs,knsrs,yxssz,sfsgwsqsxz,gwkssj,gwjssj,gwms,gwryyq,gwyqryxg,bz,gwcjsx,zxdwlb,zjly,xq) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			  String[] input = { myForm.getGwxh(),xn, yrbm, gwmc, gwxzdm, xqrs, knsrs, yxssz,sfsgwsqsxz,gwkssj,gwjssj, gwms, gwryyq,gwyqryxg, bz,gwcjsx,zxdwlb,zjly,xq};
			  return dao.runUpdate(sql,input);
		}else if("10344".equals(Base.xxdm)){
			 sql = "insert into xg_qgzx_gwxxb (gwxh,xn,yrdwdm,gwmc,gwxzdm,xqrs,knsrs,yxssz,sfsgwsqsxz,gwkssj,gwjssj,gwms,gwryyq,gwyqryxg,bz,gwcjsx,ssbm,fzls,lxdh,lsbgs,gzdd,gzsj,gznr,xq,ssxq) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				String[] input = { myForm.getGwxh(),xn, yrbm, gwmc, gwxzdm, xqrs, knsrs, yxssz,sfsgwsqsxz,gwkssj,gwjssj, gwms, gwryyq,gwyqryxg, bz,gwcjsx,ssbm,fzls,lxdh,lsbgs,gzdd,gzsj,gznr,xq,ssxq};
				return dao.runUpdate(sql,input);
		}else if("12309".equals(Base.xxdm)){
			 sql = "insert into xg_qgzx_gwxxb (gwxh,xn,yrdwdm,gwmc,gwxzdm,xqrs,knsrs,yxssz,sfsgwsqsxz,gwkssj,gwjssj,gwms,gwryyq,gwyqryxg,bz,gwcjsx,ssbm,lxdh,xq,gwcjbz,jfhb,zc) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				String[] input = { myForm.getGwxh(),xn, yrbm, gwmc, gwxzdm, xqrs, knsrs, yxssz,sfsgwsqsxz,gwkssj,gwjssj, gwms, gwryyq,gwyqryxg, bz,gwcjsx,ssbm,lxdh,xq,gwcjbz,jfhb,zc};
				return dao.runUpdate(sql,input);
		}else if("10351".equals(Base.xxdm)){//温州大学个性化
			sql = "insert into xg_qgzx_gwxxb (gwdm,gwxh,xn,yrdwdm,gwmc,gwxzdm,xqrs,knsrs,yxssz,sfsgwsqsxz,gwkssj,gwjssj,gwms,gwryyq,gwyqryxg,bz,gwcjsx,xq) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			String[] input = { myForm.getGwdm(),myForm.getGwxh(),xn, yrbm, gwmc, gwxzdm, xqrs, knsrs, yxssz,sfsgwsqsxz,gwkssj,gwjssj, gwms, gwryyq,gwyqryxg, bz,gwcjsx,xq};
			return dao.runUpdate(sql,input);
		}else if("12867".equals(Base.xxdm)){//浙江旅游个性化
			sql = "insert into xg_qgzx_gwxxb (gwxh,xn,yrdwdm,gwmc,gwxzdm,xqrs,knsrs,yxssz,sfsgwsqsxz,gwkssj,gwjssj,gwms,gwryyq,gwyqryxg,bz,gwcjsx,gwshr,gwshrxm) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			String[] input = { myForm.getGwxh(),xn, yrbm, gwmc, gwxzdm, xqrs, knsrs, yxssz,sfsgwsqsxz,gwkssj,gwjssj, gwms, gwryyq,gwyqryxg, bz,gwcjsx,gwshr,gwshrxm};
			return dao.runUpdate(sql,input);
		}else{
			 sql = "insert into xg_qgzx_gwxxb (gwxh,xn,yrdwdm,gwmc,gwxzdm,xqrs,knsrs,yxssz,sfsgwsqsxz,gwkssj,gwjssj,gwms,gwryyq,gwyqryxg,bz,gwcjsx,xq) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				String[] input = { myForm.getGwxh(),xn, yrbm, gwmc, gwxzdm, xqrs, knsrs, yxssz,sfsgwsqsxz,gwkssj,gwjssj, gwms, gwryyq,gwyqryxg, bz,gwcjsx,xq};
				return dao.runUpdate(sql,input);
		}
	  
		
	}
	
	
	/**
	 * 同一学年,同一部门是否存在相同岗位名称的岗位信息
	 * 如果学期不为空，加上学期的判断
	 * @param model
	 * @return
	 */
	public boolean isExist(QgzxGwglForm model) {
		String pkValue = Base.isNull(model.getPkValue())?" ":model.getPkValue();
		ArrayList<String> intputValueArry = new ArrayList<String>();
		StringBuffer sql = new StringBuffer();
		String xn = model.getXn();
		String xq = model.getXq();
		String yrbm = model.getYrbm();
		String gwmc = model.getGwmc();
		sql.append(" select count(1) num from xg_qgzx_gwxxb where gwdm<>? and xn=?");
		intputValueArry.add(pkValue);
		intputValueArry.add(xn);
		if(StringUtils.isNotNull(xq)){
			sql.append(" and xq = ?");
			intputValueArry.add(xq);
		}
		sql.append(" and yrdwdm=? and gwmc=? ");
		intputValueArry.add(yrbm);
		intputValueArry.add(gwmc);
		String[] inputValue = intputValueArry.toArray(new String[]{});
		String num = dao.getOneRs(sql.toString(), inputValue, "num");
		return "0".equalsIgnoreCase(num)?false:true;
	}
	
	/**
	 * 同一部门有效岗位名称重复
	 * @param model
	 * @return
	 */
	public boolean isExistSj(QgzxGwglForm model) {
		String pkValue = Base.isNull(model.getPkValue())?" ":model.getPkValue();
//		String yxssz = model.getYxssz();
		String gwkssj = model.getGwkssj();
		String gwjssj = Base.isNull(model.getGwjssj()) ? "99999999" : model.getGwjssj();
		String yrbm = model.getYrbm();
		String gwmc = model.getGwmc();
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1) num from ( ");
		sql.append("   select  ");
		sql.append("     case when gwkssj='' or gwkssj is null then '00000000' else gwkssj end gwkssj, ");
		sql.append("     case when gwjssj='' or gwjssj is null then '99999999' else gwjssj end gwjssj, ");
		sql.append("     gwdm,yrdwdm,gwmc ");
		sql.append("   from xg_qgzx_gwxxb  ");
		sql.append(" ) a where not (a.gwkssj>? or a.gwjssj<?) and gwdm<>? and yrdwdm=? and gwmc=? ");
		String[] inputValue = new String[]{gwjssj,gwkssj,pkValue,yrbm,gwmc};
		String num = dao.getOneRs(sql.toString(), inputValue, "num");
		return "0".equalsIgnoreCase(num)?false:true;
	}
	
	
	/**
	 * 同一学年,同一部门是否存在相同岗位名称的岗位信息
	 * @param model
	 * @return
	 */
	public boolean checkZjGwsqInfoExist(QgzxGwglForm model) {
		String pkValue = Base.isNull(model.getPkValue())?" ":model.getPkValue();
		String xn = model.getXn();
		String yrbm = model.getYrbm();
		String gwmc = model.getGwmc();
		String sql ="select count(1) num from xg_qgzx_gwxxsqb where gwdm<>? and xn=? and yrdwdm=? and gwmc=? ";
		String[] inputValue = new String[]{pkValue,xn,yrbm,gwmc};
		String num = dao.getOneRs(sql, inputValue, "num");
		return "0".equalsIgnoreCase(num)?false:true;
	}
	
	/**
	 * 同一部门有效岗位名称重复
	 * @param model
	 * @return
	 */
	public boolean checkZjGwsqInfoExistSj(QgzxGwglForm model) {
		String pkValue = Base.isNull(model.getPkValue())?" ":model.getPkValue();
//		String yxssz = model.getYxssz();
		String gwkssj = model.getGwkssj();
		String gwjssj = Base.isNull(model.getGwjssj()) ? "99999999" : model.getGwjssj();
		String yrbm = model.getYrbm();
		String gwmc = model.getGwmc();
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1) num from ( ");
		sql.append("   select  ");
		sql.append("     case when gwkssj='' or gwkssj is null then '00000000' else gwkssj end gwkssj, ");
		sql.append("     case when gwjssj='' or gwjssj is null then '99999999' else gwjssj end gwjssj, ");
		sql.append("     gwdm,yrdwdm,gwmc ");
		sql.append("   from xg_qgzx_gwxxsqb  ");
		sql.append(" ) a where not (a.gwkssj>? or a.gwjssj<?) and gwdm<>? and yrdwdm=? and gwmc=? ");
		String[] inputValue = new String[]{gwjssj,gwkssj,pkValue,yrbm,gwmc};
		String num = dao.getOneRs(sql.toString(), inputValue, "num");
		return "0".equalsIgnoreCase(num)?false:true;
	}
	
	/**
	 * 判断岗位是否已被使用
	 * @param pkValue
	 * @return
	 */
	public boolean isUsed(String pkValue) {
		String sql="select count(1) num from xg_qgzx_xsgwxxb where gwdm = ? ";
		String num = dao.getOneRs(sql, new String[]{pkValue}, "num");
		return "0".equalsIgnoreCase(num)?false:true;
	}
	
	
	/**
	 * 修改岗位信息
	 * @param myForm
	 * @return
	 * @throws Exception
	 */
	public boolean gwxxXg(QgzxGwglForm myForm) throws Exception {
		String pkValue = myForm.getPkValue();
		String gwmc = myForm.getGwmc();
		String gwxzdm = myForm.getGwxzdm();
		String xqrs = myForm.getXqrs();
		String knsrs = myForm.getKnsrs();
		String yxssz = myForm.getYxssz();
		String sfsgwsqsxz = myForm.getSfsgwsqsxz();
		String gwkssj = myForm.getGwkssj();
		String gwjssj = myForm.getGwjssj();
		String gwms = myForm.getGwms();
		String gwryyq = myForm.getGwryyq();
		String gwyqryxg = myForm.getGwyqryxg();
		String zjly = myForm.getZjly();
		String zxdwlb=myForm.getZxdwlb();
		String bz = myForm.getBz();
		String gwcjsx=myForm.getGwcjsx();
		String fzls = myForm.getFzls();
		String lxdh = myForm.getLxdh();
		String lsbgs = myForm.getLsbgs();
		String gzdd = myForm.getGzdd();
		String gzsj = myForm.getGzsj();
		String gznr = myForm.getGznr();
		String xq = myForm.getXq();
		String gwcjbz = myForm.getGwcjbz();
		String jfhb = myForm.getJfhb();
		String zc = myForm.getZc();
		String gwshr="";
		String gwshrxm="";
		if("12867".equals(Base.xxdm)){
			 gwshr = myForm.getGwshr();
			 gwshrxm = myForm.getGwshrxm();
		}else {
			 gwshr="";
			 gwshrxm="";
		}
		String sql = "";
		if("10511".equals(Base.xxdm)){
			sql = "update xg_qgzx_gwxxb set gwmc=?,gwxzdm=?,xqrs=?,knsrs=?,yxssz=?,sfsgwsqsxz=?,gwkssj=?,gwjssj=?,gwms=?,gwryyq=?,gwyqryxg=?,bz=?,gwcjsx=?,zxdwlb=?,zjly=?,xq=? where gwdm=?";
			String[] input = {gwmc,gwxzdm,xqrs, knsrs, yxssz,sfsgwsqsxz,gwkssj,gwjssj, gwms, gwryyq, gwyqryxg,bz,gwcjsx,zxdwlb,zjly,xq,pkValue};
			return dao.runUpdate(sql,input);
		}else if("10344".equals(Base.xxdm)){
			sql = "update xg_qgzx_gwxxb set gwmc=?,gwxzdm=?,xqrs=?,knsrs=?,yxssz=?,sfsgwsqsxz=?,gwkssj=?,gwjssj=?,gwms=?,gwryyq=?,gwyqryxg=?,bz=?,gwcjsx=?,fzls=?,lxdh=?,lsbgs=?,gzdd=?,gzsj=?,gznr=?,xq=? where gwdm=?";
			String[] input = {gwmc,gwxzdm,xqrs, knsrs, yxssz,sfsgwsqsxz,gwkssj,gwjssj, gwms, gwryyq, gwyqryxg,bz,gwcjsx,fzls,lxdh,lsbgs,gzdd,gzsj,gznr,xq,pkValue};
			return dao.runUpdate(sql,input);
		}else if("12309".equals(Base.xxdm)){
			sql = "update xg_qgzx_gwxxb set gwmc=?,gwxzdm=?,xqrs=?,knsrs=?,yxssz=?,sfsgwsqsxz=?,gwkssj=?,gwjssj=?,gwms=?,gwryyq=?,gwyqryxg=?,bz=?,gwcjsx=?,lxdh=?,xq=?,gwcjbz=?,jfhb=?,zc=? where gwdm=?";
			String[] input = {gwmc,gwxzdm,xqrs, knsrs, yxssz,sfsgwsqsxz,gwkssj,gwjssj, gwms, gwryyq, gwyqryxg,bz,gwcjsx,lxdh,xq,gwcjbz,jfhb,zc,pkValue};
			return dao.runUpdate(sql,input);
		}else if("12867".equals(Base.xxdm)){
			sql = "update xg_qgzx_gwxxb set gwmc=?,gwxzdm=?,xqrs=?,knsrs=?,yxssz=?,sfsgwsqsxz=?,gwkssj=?,gwjssj=?,gwms=?,gwryyq=?,gwyqryxg=?,bz=?,gwcjsx=?,gwshr=?,gwshrxm=? where gwdm=?";
			String[] input = {gwmc,gwxzdm,xqrs, knsrs, yxssz,sfsgwsqsxz,gwkssj,gwjssj, gwms, gwryyq, gwyqryxg,bz,gwcjsx,gwshr,gwshrxm,pkValue};
			return dao.runUpdate(sql,input);
		}else{
			sql = "update xg_qgzx_gwxxb set gwmc=?,gwxzdm=?,xqrs=?,knsrs=?,yxssz=?,sfsgwsqsxz=?,gwkssj=?,gwjssj=?,gwms=?,gwryyq=?,gwyqryxg=?,bz=?,gwcjsx=?,xq=? where gwdm=?";
			String[] input = {gwmc,gwxzdm,xqrs, knsrs, yxssz,sfsgwsqsxz,gwkssj,gwjssj, gwms, gwryyq, gwyqryxg,bz,gwcjsx,xq,pkValue};
			return dao.runUpdate(sql,input);
		}
		
	}
	
	
	/**
	 * 修改岗位信息(废弃)
	 * @param myForm
	 * @return
	 * @throws Exception
	*/
	public boolean bcXgGwsq(QgzxGwglForm myForm) throws Exception {
		String pkValue = myForm.getPkValue();
		String gwmc = myForm.getGwmc();
		String gwxzdm = myForm.getGwxzdm();
		String xqrs = myForm.getXqrs();
		String knsrs = myForm.getKnsrs();
		String yxssz = myForm.getYxssz();
		String sfsgwsqsxz = myForm.getSfsgwsqsxz();
		String gwkssj = myForm.getGwkssj();
		String gwjssj = myForm.getGwjssj();
		String gwms = myForm.getGwms();
		String gwryyq = myForm.getGwryyq();
		String gwyqryxg = myForm.getGwyqryxg();
		String bz = myForm.getBz();
		String gwcjsx=myForm.getGwcjsx();
		String zxdwlb = myForm.getZxdwlb();
		String zjly = myForm.getZjly();
		String ssbm = myForm.getSsbm();
		String fzls = myForm.getFzls();
		String lxdh = myForm.getLxdh();
		String lsbgs = myForm.getLsbgs();
		String gzdd = myForm.getGzdd();
		String gzsj = myForm.getGzsj();
		String gznr = myForm.getGznr();
		String xq = myForm.getXq();
		String gwcjbz = myForm.getGwcjbz();
		String jfhb = myForm.getJfhb();
		String zc = myForm.getZc();
		String ssxq = myForm.getSsxq();
		String shzt = myForm.getShzt();
		String splcid = myForm.getSplcid();
		String gwshr="";
		String gwshrxm="";
		String sqsj="";
		if("12867".equals(Base.xxdm)){
			 gwshr = myForm.getGwshr();
			 gwshrxm = myForm.getGwshrxm();
			 sqsj = myForm.getSqsj();
		}
		String sql = "";
		if("10511".equals(Base.xxdm)){
			 sql = "update xg_qgzx_gwxxsqb set gwmc=?,gwxzdm=?,xqrs=?,knsrs=?,yxssz=?,sfsgwsqsxz=?,gwkssj=?,gwjssj=?,gwms=?,gwryyq=?,gwyqryxg=?,bz=?,sqsj=to_char(sysdate,'yyyy-MM-dd hh:mi:ss'),gwcjsx=?,shzt=?,zxdwlb=?,zjly=?,xq=?,splcid = ? where gwdm=?";
			 String[] input = {gwmc,gwxzdm,xqrs, knsrs,yxssz,sfsgwsqsxz,gwkssj,gwjssj, gwms, gwryyq, gwyqryxg, bz, gwcjsx,shzt,zxdwlb,zjly,xq,splcid,pkValue};
			 return dao.runUpdate(sql,input);
		}else if("10344".equals(Base.xxdm)){
			 sql = "update xg_qgzx_gwxxsqb set gwmc=?,gwxzdm=?,xqrs=?,knsrs=?,yxssz=?,sfsgwsqsxz=?,gwkssj=?,gwjssj=?,gwms=?,gwryyq=?,gwyqryxg=?,bz=?,sqsj=to_char(sysdate,'yyyy-MM-dd hh:mi:ss'),gwcjsx=?,ssbm=?,fzls=?,lxdh=?,lsbgs=?,gzdd=?,gzsj=?,gznr=?,shzt=?,xq=?,ssxq=?,splcid=? where gwdm=?";
			 String[] input = {gwmc,gwxzdm,xqrs, knsrs,yxssz,sfsgwsqsxz,gwkssj,gwjssj, gwms, gwryyq, gwyqryxg, bz, gwcjsx,ssbm,fzls,lxdh,lsbgs,gzdd,gzsj,gznr,shzt,xq,ssxq,splcid,pkValue};
			 return dao.runUpdate(sql,input);
		}else if("12309".equals(Base.xxdm)){
			 sql = "update xg_qgzx_gwxxsqb set gwmc=?,gwxzdm=?,xqrs=?,knsrs=?,yxssz=?,sfsgwsqsxz=?,gwkssj=?,gwjssj=?,gwms=?,gwryyq=?,gwyqryxg=?,bz=?,sqsj=to_char(sysdate,'yyyy-MM-dd hh:mi:ss'),gwcjsx=?,ssbm=?,fzls=?,lxdh=?,lsbgs=?,gzdd=?,gzsj=?,gznr=?,shzt=?,xq=?,gwcjbz=?,jfhb=?,zc=?,splcid=? where gwdm=?";
			 String[] input = {gwmc,gwxzdm,xqrs, knsrs,yxssz,sfsgwsqsxz,gwkssj,gwjssj, gwms, gwryyq, gwyqryxg, bz, gwcjsx,ssbm,fzls,lxdh,lsbgs,gzdd,gzsj,gznr,shzt,xq,gwcjbz,jfhb,zc,splcid,pkValue};
			 return dao.runUpdate(sql,input);
		}else if("12867".equals(Base.xxdm)){
			 sql = "update xg_qgzx_gwxxsqb set gwmc=?,gwxzdm=?,xqrs=?,knsrs=?,yxssz=?,sfsgwsqsxz=?,gwkssj=?,gwjssj=?,gwms=?,gwryyq=?,gwyqryxg=?,bz=?,sqsj=to_char(sysdate,'yyyy-MM-dd hh:mi:ss'),gwcjsx=?,shzt=?,gwshr=?,gwshrxm=?,splcid=? where gwdm=?";
			 String[] input = {gwmc,gwxzdm,xqrs, knsrs,yxssz,sfsgwsqsxz,gwkssj,gwjssj, gwms, gwryyq, gwyqryxg, bz, gwcjsx,shzt,gwshr,gwshrxm,splcid,pkValue};
			 return dao.runUpdate(sql,input);
		}else{
			 sql = "update xg_qgzx_gwxxsqb set gwmc=?,gwxzdm=?,xqrs=?,knsrs=?,yxssz=?,sfsgwsqsxz=?,gwkssj=?,gwjssj=?,gwms=?,gwryyq=?,gwyqryxg=?,bz=?,sqsj=to_char(sysdate,'yyyy-MM-dd hh:mi:ss'),gwcjsx=?,shzt=?,xq=?,splcid=? where gwdm=?";
			 String[] input = {gwmc,gwxzdm,xqrs, knsrs,yxssz,sfsgwsqsxz,gwkssj,gwjssj, gwms, gwryyq, gwyqryxg, bz, gwcjsx,shzt,xq,splcid,pkValue};
			 return dao.runUpdate(sql,input);
		}
	}
	 
	/**
	 * @描述：保存审核时岗位修改 温州大学个性化
	 * @作者：卓耐[工号:1391]
	 * @日期：2017年1月12日 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param myForm
	 * @return
	 * @throws Exception
	 * boolean 返回类型
	 */
	public boolean bcXgGwsh(QgzxGwglForm myForm) throws Exception {
		String pkValue = myForm.getPkValue();
		String gwxzdm = myForm.getGwxzdm();
		String knsrs = myForm.getKnsrs();
		String yxssz = myForm.getYxssz();
		String sfsgwsqsxz = myForm.getSfsgwsqsxz();
		String gwkssj = myForm.getGwkssj();
		String gwjssj = myForm.getGwjssj();
		String sql = "update xg_qgzx_gwxxsqb set gwxzdm=?,knsrs=?,yxssz=?,sfsgwsqsxz=?,gwkssj=?,gwjssj=?,shzt='0' where gwdm=?";
		String[] input = {gwxzdm, knsrs,yxssz,sfsgwsqsxz,gwkssj,gwjssj, pkValue};
		return dao.runUpdate(sql,input);
	}
	
	/**
	 * 修改岗位信息（【用人单位岗位申请】的记录审核【通过】后，在【岗位人员维护】中进行信息修改， 【用人单位岗位申请】中相应的信息也被修改）
	 */
	public boolean bcXgGwsqByJg(QgzxGwglForm myForm) throws Exception {
		String pkValue = myForm.getPkValue();
		String gwmc = myForm.getGwmc();
		String gwxzdm = myForm.getGwxzdm();
		String xqrs = myForm.getXqrs();
		String knsrs = myForm.getKnsrs();
		String yxssz = myForm.getYxssz();
		String sfsgwsqsxz = myForm.getSfsgwsqsxz();
		String gwkssj = myForm.getGwkssj();
		String gwjssj = myForm.getGwjssj();
		String gwms = myForm.getGwms();
		String gwryyq = myForm.getGwryyq();
		String bz = myForm.getBz();
		String fzls = myForm.getFzls();
		String lxdh = myForm.getLxdh();
		String lsbgs = myForm.getLsbgs();
		String gzdd = myForm.getGzdd();
		String gzsj = myForm.getGzsj();
		String gznr = myForm.getGznr();
		String gwcjsx=myForm.getGwcjsx();
		String gwcjbz = myForm.getGwcjbz();
		String jfhb = myForm.getJfhb();
		String zc = myForm.getZc();
		String ssxq = myForm.getSsxq();
		String sql = "";
		if("10344".equals(Base.xxdm)){
			 sql = "update xg_qgzx_gwxxsqb set gwmc=?,gwxzdm=?,xqrs=?,knsrs=?,yxssz=?,sfsgwsqsxz=?,gwkssj=?,gwjssj=?,gwms=?,gwryyq=?,bz=?,fzls=?,lxdh=?,lsbgs=?,gzdd=?,gzsj=?,gznr=?,ssxq=? where gwdm=?";
			 String[] input = {gwmc,gwxzdm,xqrs, knsrs,yxssz,sfsgwsqsxz,gwkssj,gwjssj, gwms, gwryyq, bz,fzls,lxdh,lsbgs,gzdd,gzsj,gznr,ssxq,pkValue};
				return dao.runUpdate(sql,input);
		}else if ("12309".equals(Base.xxdm)) {
			 sql = "update xg_qgzx_gwxxsqb set gwmc=?,gwxzdm=?,xqrs=?,knsrs=?,yxssz=?,sfsgwsqsxz=?,gwkssj=?,gwjssj=?,gwms=?,gwryyq=?,bz=?,lxdh=?,gwcjbz=?,jfhb=?,zc=? where gwdm=?";
			 String[] input = {gwmc,gwxzdm,xqrs,knsrs,yxssz,sfsgwsqsxz,gwkssj,gwjssj,gwms,gwryyq,bz,lxdh,gwcjbz,jfhb,zc,pkValue};
				return dao.runUpdate(sql,input);
		}else{
			sql = "update xg_qgzx_gwxxsqb set gwmc=?,gwxzdm=?,xqrs=?,knsrs=?,yxssz=?,sfsgwsqsxz=?,gwkssj=?,gwjssj=?,gwms=?,gwryyq=?,bz=? where gwdm=?";
			String[] input = {gwmc,gwxzdm,xqrs, knsrs,yxssz,sfsgwsqsxz,gwkssj,gwjssj, gwms, gwryyq, bz,pkValue};
			return dao.runUpdate(sql,input);
		}
		
	}
	
	
	/**
	 * 获得用人部门List
	 * @return
	 */
	public List<HashMap<String, String>> getYrbm(QgzxGwglForm myForm) {
		String sql = " select distinct bmdm,bmmc from view_xg_qgzx_yrdwdmb a where bmmc<>'未确定' order by bmdm ";
		String[] outputValue = {"bmdm","bmmc"};
		return dao.getList(sql, new String[]{}, outputValue);
	}
	
	
	/**
	 * 获得最大的岗位代码
	 * @return
	 */
	public String getMaxGwdm() {
		String sql = " select max(to_number(substr(gwdm,10,4 ))) num from xg_qgzx_gwxxb ";
		return dao.getOneRs(sql, new String[]{}, "num");
	}
	
	
	/**
	 * 批量删除岗位信息
	 * @param pkValue
	 * @return
	 * @throws SQLException 
	 */
	public boolean gwxxSc(List<String[]> params) throws SQLException {
		String sql = " delete from xg_qgzx_gwxxb where gwdm=? ";
		int[] result = dao.runBatch(sql, params);
		return dao.checkBatchResult(result);
	}
	
	
	/**
	 * 批量删除岗位信息
	 * @param pkValue
	 * @return
	 * @throws SQLException 
	 */
	public boolean gwsqSc(List<String[]> params) throws SQLException {
		String sql = " delete from xg_qgzx_gwxxsqb where gwdm=? ";
		int[] result = dao.runBatch(sql, params);
		return dao.checkBatchResult(result);
	}
	
	
	/**
	 * 批量保存岗位信息
	 * @param params
	 * @return
	 * @throws SQLException 
	 */
	public boolean gwxxSave(List<String[]> params) throws SQLException {
		String sql = null;
		if("12309".equals(Base.xxdm)){
			sql = "insert into xg_qgzx_gwxxb(gwxh,xn,yxssz,sfsgwsqsxz,gwkssj,gwjssj,yrdwdm,gwmc,gwxzdm,xqrs,knsrs,gwms,gwryyq,gwyqryxg,bz,gwcjsx,xq,gwcjbz,jfhb,zc) select gwxh,? xn ,? yxssz,sfsgwsqsxz,? gwkssj,? gwjssj,yrdwdm,gwmc,gwxzdm,xqrs,knsrs,gwms,gwryyq,gwyqryxg,bz,gwcjsx,?,gwcjbz,jfhb,zc from xg_qgzx_gwxxb where gwdm = ?";
		} else {
			sql = "insert into xg_qgzx_gwxxb(gwxh,xn,yxssz,sfsgwsqsxz,gwkssj,gwjssj,yrdwdm,gwmc,gwxzdm,xqrs,knsrs,gwms,gwryyq,gwyqryxg,bz,gwcjsx,xq) select gwxh,? xn ,? yxssz,sfsgwsqsxz,? gwkssj,? gwjssj,yrdwdm,gwmc,gwxzdm,xqrs,knsrs,gwms,gwryyq,gwyqryxg,bz,gwcjsx,? from xg_qgzx_gwxxb where gwdm = ?";
		}
		int[] result = dao.runBatch(sql,params);
		return dao.checkBatchResult(result);
	}
	
	
	/**
	 * 在岗人员信息List
	 * @param model
	 * @return
	 * @throws SQLException 
	 */
	public String[] getRyxhList(QgzxGwglForm model) throws SQLException {
		String sql = " select xh||'!!@@!!'||sgsj||'!!@@!!'||sjly pkValue from xg_qgzx_xsgwxxb where gwdm = ? and zgzt = ? order by sgsj desc,xh ";
		String[] inputValue = {model.getPkValue(),model.getZgzt()};
		return dao.getArray(sql, inputValue, "pkValue");
	}
	
	
	/**
	 * 获得岗位学生信息
	 * @param myForm
	 * @return
	 */
	public HashMap<String, String> getGwxsxx(QgzxGwglForm model) {
		String sql = "select a.sgsj,a.tgsj,a.zgzt,a.sjly,a.sqbh,b.*,(select count(1) from xg_qgzx_xsgwxxb where xh = ? and zgzt='zg' )qggws from xg_qgzx_xsgwxxb a left join view_xsbfxx b on a.xh = b.xh where a.xh = ? and a.gwdm = ? and a.zgzt = ? and rownum = 1";
		String[] inputValue = {model.getXh(),model.getXh(),model.getPkValue(),model.getZgzt()};
		return dao.getMapNotOut(sql, inputValue);
	}

	
	/**
	 * 获得学生信息
	 * @param model
	 * @return
	 */
	public HashMap<String, String> getXsxx(QgzxGwglForm model) {
		String sql = "select a.*,(select count(1) from xg_qgzx_xsgwxxb where xh = ? and zgzt='zg' )qggws from view_xsbfxx a where a.xh = ? and rownum = 1";
		String[] inputValue = {model.getXh(),model.getXh()};
		String[] outputValue = {"xh","xm","bjmc","qggws","xb","nj","xymc","zymc","lxdh","sfzx"};
		return dao.getMap(sql, inputValue, outputValue);
	}

	
	/**
	 * 批量保存人员信息
	 * @param params
	 * @return
	 * @throws SQLException 
	 */
	public boolean bcZjryxx(List<String[]> params) throws SQLException {
		String sql ="";
		sql = " insert into xg_qgzx_new_xsgwxxb(gwdm,xh,sgsj,zgzt)values(?,?,to_char(sysdate,'yyyyMMdd'),'zg')";
		//浙江交通维护上岗月份
		if(Globals.XXDM_ZJJTZYJSXY.equals(Base.xxdm)){
			sql="insert into xg_qgzx_new_xsgwxxb(gwdm,xh,sgsj,zgzt)values(?,?,?,'zg')";
		}
		int[] result = dao.runBatch(sql, params);
		return dao.checkBatchResult(result);
	}
	
	public boolean gwryUpdate(List<String[]> params) throws SQLException {
		String sql = " update xg_qgzx_new_xsgwxxb set sgsj =? where gwdm=? and xh=?";
		int[] result = dao.runBatch(sql, params);
		return dao.checkBatchResult(result);
	}
	/**
	 * 批量删除人员信息
	 * @param params
	 * @return
	 * @throws SQLException 
	 */
	public boolean scRyxx(List<String[]> params) throws SQLException {
		String sql = " delete from xg_qgzx_new_xsgwxxb where gwdm = ? and xh = ? and zgzt = 'zg'";
		int[] result = dao.runBatch(sql, params);
		return dao.checkBatchResult(result);
	}
	
	/**
	 * 批量保存退岗人员信息
	 * @param params
	 * @return
	 * @throws SQLException 
	 */
	public boolean bcTgryxx(List<String[]> params) throws SQLException {
		String sql = " update xg_qgzx_new_xsgwxxb set zgzt='tg',tgsj=to_char(sysdate,'yyyyMMdd'),tgyy=? where gwdm=? and xh = ? and sgsj = ?";
		int[] result = dao.runBatch(sql, params);
		return dao.checkBatchResult(result);
	}
	/**
	 * 保存岗位批量退岗
	 */
	public boolean bcTggwxx(List<String[]> paramsSq, List<String[]> params) throws SQLException {
		// 批量设置申请数据为初始化数据（未审核通过）
		String sql = " update XG_QGZX_XSGWSQB set shgw='-1' where sqbh in (select sqbh from xg_qgzx_new_xsgwxxb where zgzt='zg' and gwdm =?)";
		int[] result = dao.runBatch(sql, paramsSq);
		boolean rs = dao.checkBatchResult(result);
		if(rs){
			// 保存岗位批量退岗
			sql = " update xg_qgzx_new_xsgwxxb set zgzt='tg',tgsj=to_char(sysdate,'yyyyMMdd'),tgyy=? where zgzt='zg' and gwdm =? ";
			result = dao.runBatch(sql, params);
			rs = dao.checkBatchResult(result);
		}
		return rs;
	}
	/**
	 * 
	 * @描述: 批量设置数据为初始化数据（未审核通过）
	 * @作者：张昌路[工号：982]
	 * @日期：2013-12-30 下午02:21:11
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param params
	 * @return
	 * @throws SQLException
	 * boolean 返回类型 
	 */
	public boolean hySqsj(List<String[]> params) throws SQLException {
		String sql = " update XG_QGZX_XSGWSQB set shgw='-1' where sqbh=?";
		int[] result = dao.runBatch(sql, params);
		return dao.checkBatchResult(result);
	}

	/**
	 * 根据学号和在岗状态获得岗位信息
	 * @param model
	 * @return
	 */
	public List<HashMap<String, String>> getGwxx(QgzxGwglForm model) {
		String sql = "select a.*,rownum r from (select a.sgsj,b.*,c.bmmc,d.gwxzmc from xg_qgzx_xsgwxxb a " +
				"left join xg_qgzx_gwxxb b on a.gwdm = b.gwdm " +
				"left join view_xg_qgzx_yrdwdmb c on b.yrdwdm = c.bmdm " +
				"left join xg_qgzx_gwxzdmb d on b.gwxzdm = d.gwxzdm where a.xh = ? and a.zgzt = ? order by a.sgsj desc,b.xn,b.yrdwdm,b.gwdm,b.gwxzdm) a";
		String[] inputValue = {model.getXh(),model.getZgzt()};
		String[] outputValue = {"r","xn","bmmc","gwmc","gwxzmc","sgsj"};
		return dao.getList(sql, inputValue, outputValue);
	}


	/**
	 * 获得最后退岗时间
	 * @param model
	 * @return
	 */
	public String getZhtgsj(String[] inputValue) {
		String sql = " select max(tgsj) tgsj from xg_qgzx_xsgwxxb where gwdm = ? and xh = ? ";
		return dao.getOneRs(sql, inputValue, "tgsj");
	}

	

	/**
	 * 获得岗位学生学号
	 * @param xn
	 * @param xsgws
	 * @return
	 * @throws SQLException 
	 */
	public String[] getGwxsxh(String xn, String xsgws) throws SQLException {
		String sql = "select xh from (select xh,count(1) num from xg_qgzx_xsgwxxb where substr(gwdm,0,9) = ? and zgzt = 'zg' group by xh) where num >=to_number(?)";
		return dao.getArray(sql, new String[]{xn,xsgws}, "xh");
	}


	/**
	 * 获得岗位数
	 * @param gwdm
	 * @param xh
	 * @return
	 */
	public String getGws(String gwdm, String xh) {
		String sql = "select count(1) num from xg_qgzx_xsgwxxb c where exists" +
				"(select 1 from xg_qgzx_gwxxb a where exists" +
				"(select 1 from xg_qgzx_gwxxb b where b.gwdm = ? " +
				"and a.xn = b.xn) and a.sfsgwsqsxz='1' and a.gwdm = c.gwdm	) and zgzt = 'zg' and xh = ?  ";
		return dao.getOneRs(sql, new String[]{gwdm,xh}, "num");
	}


	/**
	 * 人员是否被发放过酬金
	 * @param gwdm
	 * @param xh
	 * @return
	 */
	public boolean isUsed(String gwdm, String xh) {
		String sql = "select count(1) num from xg_qgzx_jcffb where gwdm = ? and xh = ? ";
		return "0".equalsIgnoreCase(dao.getOneRs(sql, new String[]{gwdm,xh}, "num"))?false:true;
	}


	/**
	 * 获得用人单位名称
	 * @param yrdwdm
	 * @return
	 */
	public String getYrdwmc(String yrdwdm) {
		String sql = "select bmmc from view_xg_qgzx_yrdwdmb where bmdm = ? ";
		return dao.getOneRs(sql, new String[]{yrdwdm}, "bmmc");
	}


	/**
	 * 保存增加岗位申请
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	public boolean bcZjGwsq(QgzxGwglForm model) throws Exception {
		String xn = model.getXn();
		String yrbm = model.getUser().getUserDep();
		String gwmc = model.getGwmc();
		String gwxzdm = model.getGwxzdm();
		String xqrs = model.getXqrs();
		String knsrs = model.getKnsrs();
		String yxssz = model.getYxssz();
		String sfsgwsqsxz = model.getSfsgwsqsxz();
		String gwkssj = model.getGwkssj();
		String gwjssj = model.getGwjssj();
		String gwms = model.getGwms();
		String gwryyq = model.getGwryyq();
		String gwyqryxg = model.getGwyqryxg();
		String bz = model.getBz();
		String sqr = model.getUser().getUserName();
		String gwcjsx=model.getGwcjsx();
		String zxdwlb = model.getZxdwlb();
		String zjly = model.getZjly();
		String ssbm = model.getSsbm();
		String fzls = model.getFzls();
		String lxdh = model.getLxdh();
		String lsbgs = model.getLsbgs();
		String gzdd = model.getGzdd();
		String gzsj = model.getGzsj();
		String gznr = model.getGznr();
		String gwcjbz = model.getGwcjbz();
		String jfhb = model.getJfhb();
		String zc = model.getZc();
		String xq = model.getXq();
		String ssxq = model.getSsxq();
		String shzt = model.getShzt();
		String splcid = model.getSplcid();
		String gwdm = model.getGwdm();
		String sql = "";
		if("10511".equals(Base.xxdm)){
			 sql = "insert into xg_qgzx_gwxxsqb (gwdm,xn,yrdwdm,gwmc,gwxzdm,xqrs,knsrs,yxssz,sfsgwsqsxz,gwkssj,gwjssj,gwms,gwryyq,gwyqryxg,bz,sqsj,sqr,gwcjsx,zxdwlb,zjly,xq,shzt,splcid) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,to_char(sysdate,'yyyy-MM-dd hh24:mi:ss'),?,?,?,?,?,?,?)";
				String[] input = {gwdm,xn, yrbm, gwmc, gwxzdm, xqrs, knsrs,yxssz,sfsgwsqsxz,gwkssj,gwjssj, gwms, gwryyq,gwyqryxg, bz, sqr,gwcjsx,zxdwlb,zjly,xq,shzt,splcid};
				return dao.runUpdate(sql,input);
		}else if("10344".equals(Base.xxdm)){
			sql = "insert into xg_qgzx_gwxxsqb (gwdm,xn,yrdwdm,gwmc,gwxzdm,xqrs,knsrs,yxssz,sfsgwsqsxz,gwkssj,gwjssj,gwms,gwryyq,gwyqryxg,bz,sqsj,sqr,gwcjsx,ssbm,fzls,lxdh,lsbgs,gzdd,gzsj,gznr,xq,ssxq,shzt,splcid) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,to_char(sysdate,'yyyy-MM-dd hh24:mi:ss'),?,?,?,?,?,?,?,?,?,?,?,?,?)";
			String[] input = {gwdm,xn, yrbm, gwmc, gwxzdm, xqrs, knsrs,yxssz,sfsgwsqsxz,gwkssj,gwjssj, gwms, gwryyq,gwyqryxg, bz, sqr,gwcjsx,ssbm,fzls,lxdh,lsbgs,gzdd,gzsj,gznr,xq,ssxq,shzt,splcid};
			return dao.runUpdate(sql,input);
		}else if("12309".equals(Base.xxdm)){
			sql = "insert into xg_qgzx_gwxxsqb (gwdm,xn,yrdwdm,gwmc,gwxzdm,xqrs,knsrs,yxssz,sfsgwsqsxz,gwkssj,gwjssj,gwms,gwryyq,gwyqryxg,bz,sqsj,sqr,gwcjsx,ssbm,lxdh,xq,gwcjbz,jfhb,zc,shzt,splcid) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,to_char(sysdate,'yyyy-MM-dd hh24:mi:ss'),?,?,?,?,?,?,?,?,?,?)";
			String[] input = {gwdm,xn, yrbm, gwmc, gwxzdm, xqrs, knsrs,yxssz,sfsgwsqsxz,gwkssj,gwjssj, gwms, gwryyq,gwyqryxg, bz, sqr,gwcjsx,ssbm,lxdh,xq,gwcjbz,jfhb,zc,shzt,splcid};
			return dao.runUpdate(sql,input);
		}else if("10351".equalsIgnoreCase(Base.xxdm)) {//温州大学个性化
			sql = "insert into xg_qgzx_gwxxsqb (gwdm,xn,yrdwdm,gwmc,gwxzdm,xqrs,knsrs,yxssz,sfsgwsqsxz,gwkssj,gwjssj,gwms,gwryyq,gwyqryxg,bz,sqsj,sqr,gwcjsx,xq,shzt,splcid) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,to_char(sysdate,'yyyy-MM-dd hh24:mi:ss'),?,?,?,?,?)";
			String[] input = {model.getGwdm(),xn, yrbm, gwmc, gwxzdm, xqrs, knsrs,yxssz,sfsgwsqsxz,gwkssj,gwjssj, gwms, gwryyq,gwyqryxg, bz, sqr,gwcjsx,xq,shzt,splcid};
			return dao.runUpdate(sql,input);
		}else{
			 sql = "insert into xg_qgzx_gwxxsqb (gwdm,xn,yrdwdm,gwmc,gwxzdm,xqrs,knsrs,yxssz,sfsgwsqsxz,gwkssj,gwjssj,gwms,gwryyq,gwyqryxg,bz,sqsj,sqr,gwcjsx,xq,shzt,splcid) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,to_char(sysdate,'yyyy-MM-dd hh24:mi:ss'),?,?,?,?,?)";
				String[] input = {gwdm,xn, yrbm, gwmc, gwxzdm, xqrs, knsrs,yxssz,sfsgwsqsxz,gwkssj,gwjssj, gwms, gwryyq,gwyqryxg, bz, sqr,gwcjsx,xq,shzt,splcid};
				return dao.runUpdate(sql,input);
		}
		
		
	}
	
	
	/**
	 * 
	 */
	public boolean insertGwsqForZjlyzy(List<QgzxGwglForm> gwsqrList,List<QgzxGwglForm> gwsqList,User user) throws Exception {
		List<String[]> insertPara = new ArrayList<String[]>();
		String[] insertArr = null;
		int[] insertNum = null;
		String lxr = "";
		String lxphone = "";
		if(null != gwsqrList && gwsqrList.size()>0){
			for(QgzxGwglForm model :gwsqrList){
				lxr = model.getLxr();
				lxphone = model.getLxphone();
			}
		}
		if(null != gwsqList && gwsqList.size()>0){
			for(QgzxGwglForm model :gwsqList){
				insertArr = new String[24];
				String gwdm = model.getGwdm();
				String xn = model.getXn();
				String yrdwdm = model.getUser().getUserDep();
				String gwmc = model.getGwmc();
				//TODO 判断岗位类型，固定岗代码009，临时岗代码012
				String gwxzdm = "";
				if(model.getGwlx().equals("固定岗")){
					gwxzdm = "001";
				}else{
					gwxzdm = "002";
				}
				String xqrs = model.getXqrs();
				String knsrs = model.getKnsrs();
				String yxssz = model.getYxssz();
				String sfsgwsqsxz = model.getSfsgwsqsxz();
				//获取申请时间，修改成前台显示相同格式的时间格式
				DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
				DateFormat df = new SimpleDateFormat("yyyyMMdd");
				Date date1 = df1.parse(model.getSqsj());
				String gwkssj = df.format(date1);
				
				String gwjssj = model.getGwjssj();
				String gwms = model.getGznr();
				String gwryyq = model.getGwryyq();
				String gwyqryxg = model.getGwyqryxg();
				String bz = model.getBz();
				String sqsj = model.getSqsj();
				String sqr = model.getSqr();
				String gwcjsx = model.getGwcjsx();
				String gwshr = model.getGwshr();
				String gwshrxm = model.getGwshrxm();
				String shzt = model.getShzt();
				String splcid = model.getSplcid();
				
				insertArr[0] = gwdm;
				insertArr[1] = xn;
				insertArr[2] = yrdwdm;
				insertArr[3] = gwmc;
				insertArr[4] = gwxzdm;
				insertArr[5] = xqrs;
				insertArr[6] = knsrs;
				insertArr[7] = yxssz;
				insertArr[8] = sfsgwsqsxz;
				insertArr[9] = gwkssj;
				insertArr[10] = gwjssj;
				insertArr[11] = gwms;
				insertArr[12] = gwryyq;
				insertArr[13] = gwyqryxg;
				insertArr[14] = bz;
				insertArr[15] = sqsj;
				insertArr[16] = sqr;
				insertArr[17] = gwcjsx;
				insertArr[18] = gwshr;
				insertArr[19] = gwshrxm;
				insertArr[20] = shzt;
				insertArr[21] = splcid;
				insertArr[22] = lxr;
				insertArr[23] = lxphone;
				
				insertPara.add(insertArr);
			}
			StringBuilder sb = new StringBuilder();
			sb.append("insert into xg_qgzx_gwxxsqb (gwdm,xn,yrdwdm,gwmc,gwxzdm,xqrs,knsrs,yxssz,sfsgwsqsxz,gwkssj,gwjssj,gwms,gwryyq,gwyqryxg,bz,sqsj,sqr,gwcjsx,gwshr,gwshrxm,shzt,splcid,lxr,lxphone) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			insertNum = dao.runBatch(sb.toString(),insertPara);
			insertPara.clear();
			insertArr = null;
		}
		return insertNum.length >0;
		
	}
	
	/**
	 * 岗位信息审核保存
	 * @param params
	 * @return
	 * @throws SQLException
	 */
	public boolean gwxxshBc(List<String[]> params) throws SQLException {
		String sql = "";
		if("12867".equalsIgnoreCase(Base.xxdm)) {
			sql = " update xg_qgzx_gwxxsqb set shyj=?,shzt=?,shsj=to_char(sysdate,'yyyy-MM-dd hh24:mi:ss'),shr=? where gwdm=?";
		}else {
			sql = " update xg_qgzx_gwxxsqb set shyj=?,shzt=?,shsj=to_char(sysdate,'yyyy-MM-dd hh24:mi:ss'),shr=?,gwcjbz=?,jfhb=?,zc=? where gwdm=?";
		}
		int[] result = dao.runBatch(sql, params);
		return dao.checkBatchResult(result);
	}


	/**
	 * 插入正式表
	 * @param paramsByZs
	 * @return
	 * @throws SQLException
	 */
	public boolean insertByZs(List<String[]> paramsByZs) throws SQLException {
		String sql = "";
		if("12867".equals(Base.xxdm)){
			 sql = " insert into xg_qgzx_gwxxb(gwxh,gwdm,xn,yrdwdm,gwmc,gwxzdm,xqrs,knsrs,yxssz,sfsgwsqsxz,gwkssj,gwjssj,gwms,gwryyq,bz,gwcjsx,gwyqryxg,zxdwlb,zjly,ssbm,fzls,lxdh,lsbgs,gzdd,gzsj,gznr,gwshr,gwshrxm)" +
				"select ?,gwdm,xn,yrdwdm,gwmc,gwxzdm,xqrs,knsrs,yxssz,sfsgwsqsxz,gwkssj,gwjssj,gwms,gwryyq,bz,gwcjsx,gwyqryxg,zxdwlb,zjly,ssbm,fzls,lxdh,lsbgs,gzdd,gzsj,gznr,gwshr,gwshrxm from xg_qgzx_gwxxsqb a where a.gwdm = ? and not exists (select 1 from xg_qgzx_gwxxb b where b.gwdm=a.gwdm) ";
		}else {
			sql = " insert into xg_qgzx_gwxxb(gwxh,gwdm,xn,yrdwdm,gwmc,gwxzdm,xqrs,knsrs,yxssz,sfsgwsqsxz,gwkssj,gwjssj,gwms,gwryyq,bz,gwcjsx,gwyqryxg,zxdwlb,zjly,ssbm,fzls,lxdh,lsbgs,gzdd,gzsj,gznr,xq,gwcjbz,jfhb,zc)" +
				"select ?,gwdm,xn,yrdwdm,gwmc,gwxzdm,xqrs,knsrs,yxssz,sfsgwsqsxz,gwkssj,gwjssj,gwms,gwryyq,bz,gwcjsx,gwyqryxg,zxdwlb,zjly,ssbm,fzls,lxdh,lsbgs,gzdd,gzsj,gznr,xq,gwcjbz,jfhb,zc from xg_qgzx_gwxxsqb a where a.gwdm = ? and not exists (select 1 from xg_qgzx_gwxxb b where b.gwdm=a.gwdm) ";
	
		}
		
		int[] result = dao.runBatch(sql, paramsByZs);
		return dao.checkBatchResult(result);
	}
	
	/**
	 * 
	 * @描述:获取岗位序号
	 * @作者：xiaxia [工号：1104]
	 * @日期：2014-10-21 下午04:49:42
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * @throws Exception
	 * String 返回类型 
	 * @throws
	 */
	public String GetGwxh(String yrdwdm,String xn) throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append("select ( case when length(gwxh)=1 then '00'||gwxh when "); 
		sql.append(" length(gwxh)=2 then '0'||gwxh else to_char(gwxh) end) gwxh");
		sql.append(" from (select (to_number(nvl(max(to_number(gwxh)),0))+1) gwxh from xg_qgzx_gwxxb");
		sql.append(" where yrdwdm=? and xn=?)");
		return dao.getOneRs(sql.toString(), new String[]{yrdwdm,xn}, "gwxh");
}

	public List<HashMap<String, String>> getYddwLbList() throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from ZXBZ_ZXDWLB");
		return dao.getListNotOut(sql.toString(), new String[]{});
	}
	
	public List<HashMap<String, String>> getZjlyList() throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from ZXBZ_ZJLY");
		return dao.getListNotOut(sql.toString(), new String[]{});
	}
	
	/**
	 * 
	 * @描述: 批量保存岗位申请信息
	 * @作者：yxy[工号：1206]
	 * @日期：2017-1-22 下午05:15:32
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param params
	 * @return
	 * @throws SQLException
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean gwsqSave(List<String[]> params) throws SQLException {
		String sql = null;
			 sql = "insert into XG_QGZX_GWXXSQB(gwxh,xn,yxssz,sfsgwsqsxz,gwkssj,gwjssj,yrdwdm,gwmc,gwxzdm,xqrs,knsrs,gwms,gwryyq,gwyqryxg,bz,gwcjsx,sqr,shzt,xq) select gwxh,? xn ,? yxssz,sfsgwsqsxz,? gwkssj,? gwjssj,yrdwdm,gwmc,gwxzdm,xqrs,knsrs,gwms,gwryyq,gwyqryxg,bz,gwcjsx,?,'0',? from XG_QGZX_GWXXSQB where gwdm = ?";
		int[] result = dao.runBatch(sql,params);
		return dao.checkBatchResult(result);
	}
	
	/**
	 * 同一学年,同一部门是否存在相同岗位名称的岗位信息(申请)
	 * 学期不为空的话，加上学期的判断
	 * @param model
	 * @return
	 */
	public boolean isExistSq(QgzxGwglForm model) {
		String pkValue = Base.isNull(model.getPkValue())?" ":model.getPkValue();
		ArrayList<String> intputValueArry = new ArrayList<String>();
		StringBuffer sql = new StringBuffer();
		String xn = model.getXn();
		String yrbm = model.getYrbm();
		String gwmc = model.getGwmc();
		String xq = model.getXq();
		sql.append(" select count(1) num from xg_qgzx_gwxxsqb where gwdm<>? and xn=? ");
		intputValueArry.add(pkValue);
		intputValueArry.add(xn);
		if(StringUtils.isNotNull(xq)){
			sql.append(" and xq = ?");
			intputValueArry.add(xq);
		}
		sql.append(" ");
		sql.append(" and yrdwdm=? and gwmc=? ");
		intputValueArry.add(yrbm);
		intputValueArry.add(gwmc);
		String[] inputValue = intputValueArry.toArray(new String[]{});
		String num = dao.getOneRs(sql.toString(), inputValue, "num");
		return "0".equalsIgnoreCase(num)?false:true;
	}
	
	/**
	 * 同一部门有效岗位名称重复(申请)
	 * @param model
	 * @return
	 */
	public boolean isExistSjSq(QgzxGwglForm model) {
		String pkValue = Base.isNull(model.getPkValue())?" ":model.getPkValue();
//		String yxssz = model.getYxssz();
		String gwkssj = model.getGwkssj();
		String gwjssj = Base.isNull(model.getGwjssj()) ? "99999999" : model.getGwjssj();
		String yrbm = model.getYrbm();
		String gwmc = model.getGwmc();
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1) num from ( ");
		sql.append("   select  ");
		sql.append("     case when gwkssj='' or gwkssj is null then '00000000' else gwkssj end gwkssj, ");
		sql.append("     case when gwjssj='' or gwjssj is null then '99999999' else gwjssj end gwjssj, ");
		sql.append("     gwdm,yrdwdm,gwmc ");
		sql.append("   from xg_qgzx_gwxxsqb  ");
		sql.append(" ) a where not (a.gwkssj>? or a.gwjssj<?) and gwdm<>? and yrdwdm=? and gwmc=? ");
		String[] inputValue = new String[]{gwjssj,gwkssj,pkValue,yrbm,gwmc};
		String num = dao.getOneRs(sql.toString(), inputValue, "num");
		return "0".equalsIgnoreCase(num)?false:true;
	}
	
	/**
	 * @throws Exception  
	 * @描述:根据岗位id删除申请范围关联学院(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-4-14 下午01:53:21
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param gwid
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean delSqglXy(String gwdm) throws Exception{
		String sql = "delete from xg_qgzx_gwxxsqxyglb where gwdm = ?";
		return dao.runUpdate(sql, new String[]{gwdm});
	}
	
	/** 
	 * @描述:批量插入申请范围学院(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-4-14 下午01:58:08
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean plInsertGlXy(QgzxGwglForm model) throws Exception {
		String[] sqxy = model.getSqxy();
		List<String[]> list = new ArrayList<String[]>();
		for(int i = 0;i<sqxy.length;i++){
			String[] str = new String[2];
			str[0] = model.getGwdm();
			str[1] = sqxy[i];
			list.add(str);
		}
		String sql = "insert into xg_qgzx_gwxxsqxyglb values(?,?)";
		return dao.runBatch(sql, list).length>0;
	}
	
	/** 
	 * @描述:获取关联学院代码(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-4-14 下午03:32:49
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String,String>> getGlxydm(String gwdm) {
		String str = "select xydm from xg_qgzx_gwxxsqxyglb where gwdm = ?";
		return dao.getListNotOut(str, new String[]{gwdm});
	}
	
	/** 
	 * @描述:判断是否为院系部门(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-4-17 下午02:05:16
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean isYxbm(String bmdm){
		String sql = "select bmlb from zxbz_xxbmdm where bmdm = ?";
		String rs = dao.getOneRs(sql, new String[]{bmdm}, "bmlb");
		return rs.equalsIgnoreCase("5")?true:false;
	}
	
	/**
	 * @throws Exception  
	 * @描述:删除关联学院范围（温州大学）(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-4-17 下午05:08:55
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean delXygl(List<String> list) throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append("delete from xg_qgzx_gwxxsqxyglb where gwdm in (");
		String[] input = new String[list.size()];
		for(int i = 0;i<list.size();i++){
			if(i==list.size()-1){
				sb.append("?)");
			}else{				
				sb.append("?,");
			}
			input[i] = list.get(i);
		}
		return dao.runDelete(sb.toString(), input)>0;
	}

	/**
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：张昌路[工号：982]
	 * @日期：2017-6-2 上午10:11:01
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getFdyPageList(QgzxGwglForm model) throws Exception {
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.*, rownum r  ");
		sql.append(" from (select lxdh,zgh,xm,xb,bmdm,zc,(select zcmc from zcb f where a.zc=f.zcdm)zcmc,(select bmmc from zxbz_xxbmdm e where a.bmdm=e.bmdm)bmmc,(case xb when '1' then '男' when '男' then '男' when '2' then '女' when  '女' then '女' else '' end) xbmc");
		sql.append(" from fdyxxb a) a where 1 = 1");
		sql.append(searchTj);
		return getPageList(model, sql.toString(), inputV);
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述:
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-9-8 下午05:19:16
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean deleteJgbData(QgzxGwglForm model) throws Exception{
		StringBuilder sql = new StringBuilder();
		List<String> paraList = new ArrayList<String>();
		sql.append(" delete from XG_QGZX_GWXXB where xn = ? and yrdwdm = ? and gwmc = ? ");
		paraList.add(model.getXn());
		paraList.add(model.getYrdwdm());
		paraList.add(model.getGwmc());
		if(StringUtils.isNotNull(model.getXq())){
			sql.append(" and xq = ?");
			paraList.add(model.getXq());
		}
		return dao.runUpdateNotCommit(sql.toString(), paraList.toArray(new String[]{}));
	}

	public boolean isHmdYh(User user){
		StringBuilder sb = new StringBuilder();
		sb.append("select count(1) num");
		sb.append(" from xg_qgzx_hmdglb t ");
		sb.append(" left join xg_qgzx_yrdwdmb t1 on t.dwid = t1.id ");
		sb.append(" where t1.qyzt = '1' and t1.yhm = ? or t1.zgh = ?");
		String num = dao.getOneRs(sb.toString(),new String[]{user.getUserName(),user.getUserName()},"num");
		return !"0".equals(num);
	}
	//是否有单位信息
	public boolean haveDw(User user){
		StringBuilder sb = new StringBuilder();
		sb.append("select count(1) num from ");
		sb.append(" xg_qgzx_yrdwdmb t");
		sb.append(" where t.qyzt = '1' and t.yhm = ? or t.zgh = ?");
		String num = dao.getOneRs(sb.toString(),new String[]{user.getUserName(),user.getUserName()},"num");
		return !"0".equals(num);
	}

	public boolean deleteGw(String[] gwdms) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("delete from XG_QGZX_GWXXB where (");
		for(int i=0;i<gwdms.length;i++){
			sql.append(" gwdm = ? ");
			if(i < gwdms.length-1){
				sql.append(" or ");
			}
		}
		sql.append(" )");
		return dao.runUpdate(sql.toString(),gwdms);
	}

	public boolean checkIsUsed(String[] gwdms){
		StringBuilder sql = new StringBuilder();
		sql.append("select count(1) num from XG_QGZX_XSGWSQB where (");
		for(int i=0;i<gwdms.length;i++){
			sql.append(" gwdm = ? ");
			if(i < gwdms.length-1){
				sql.append(" or ");
			}
		}
		sql.append(" )");
		String num = dao.getOneRs(sql.toString(),gwdms,"num");
		return !"0".equals(num);
	}

	public boolean insertJg(QgzxGwglForm t)throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("insert into XG_QGZX_GWXXB(gwdm,gwmc,xn,gwxzdm,xqrs,gwlx,gwlb,gwcjsx,gssx,zpkssj,zpjssj,cq,sfzd,sfxsgz,gwryyq,shzt,fbsj,splc,yrdwid,fjid,sjly,xq)");
		sql.append(" values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
		String[] input = {t.getGwdm(),t.getGwmc(),t.getXn(),t.getGwxzdm(),t.getXqrs(),t.getGwlx(),t.getGwlb(),t.getGwcjsx(),
				t.getGssx(),t.getZpkssj(),t.getZpjssj(),t.getCq(),t.getSfzd(),t.getSfxsgz(),t.getGwryyq(),
				t.getShzt(),t.getFbsj(),t.getSplc(),t.getYrdwid(),t.getFjid(),t.getSjly(),t.getXq()};
		return dao.runUpdate(sql.toString(),input);
	}

	public boolean updatejg(QgzxGwglForm t)throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("update XG_QGZX_GWXXB set gwmc=?, gwxzdm=?, xqrs=?, gwlx=?,");
		sql.append(" gwlb=?, gwcjsx=?, gssx=?, zpkssj=?, zpjssj=?, cq=?, sfzd=?,");
		sql.append(" sfxsgz=?, gwryyq=?, fbsj=?, fjid=?");
		sql.append(" where gwdm =?");
		String[] input = {t.getGwmc(),t.getGwxzdm(),t.getXqrs(),t.getGwlx(),t.getGwlb(),t.getGwcjsx(),
				t.getGssx(),t.getZpkssj(),t.getZpjssj(),t.getCq(),t.getSfzd(),t.getSfxsgz(),t.getGwryyq(),
				t.getFbsj(),t.getFjid(),t.getGwdm()};
		return dao.runUpdate(sql.toString(),input);
	}
}
