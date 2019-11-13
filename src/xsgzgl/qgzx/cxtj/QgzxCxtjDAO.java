package xsgzgl.qgzx.cxtj;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.search.SearchModel;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.String.StringUtils;
import xsgzgl.qgzx.QgCommUtil.QgCommUtilf;
import xsgzgl.qgzx.cssz.QgzxCsszService;
import xsgzgl.qgzx.glygl.QgzxGlyglService;

import com.zfsoft.utils.StringUtil;

/**
 * 勤工助学-查询统计-酬金统计导出
 * 
 * @author yeyipin
 * @since 2012.9.19
 */
public class QgzxCxtjDAO extends SuperDAOImpl{

	/**
	 * 岗位信息查询
	 * 
	 * @param model
	 * @return
	 */
	public List<HashMap<String,String>> gwxxCx(QgzxCxtjForm model,User user) throws Exception {
		// 高级查询MODEL
		SearchModel searchModel = model.getSearchModel();
		// 高级查询条件
		String searchTj = SearchService.getSearchTj(searchModel);
		String[] inputV = SearchService.getTjInput(searchModel);

		StringBuilder sql = new StringBuilder();

		sql.append(" select * from (");
		sql.append(" select a.gwdm,a.xn,a.fbsj,a.gwmc,nvl(b.yrdwmc,c.bmmc) yrdwmc,a.gwxzdm,d.gwxzmc gwlbmc,a.gwlx,b.dwlb,a.xqrs,b.yhm,b.zgh ");
		sql.append(" ,nvl((select count(1) from xg_qgzx_new_xsgwxxb x where a.gwdm = x.gwdm and x.zgzt = 'zg'),'0') zgrs");
		sql.append(" ,nvl((select count(1) from xg_qgzx_new_xsgwxxb y where y.gwdm = a.gwdm and y.zgzt = 'tg'),'0') lzrs");
		sql.append(" from XG_QGZX_GWXXB a ");
		sql.append(" left join XG_QGZX_YRDWDMB b on a.yrdwid = b.id ");
		sql.append(" left join ZXBZ_XXBMDM c on b.xydm = c.bmdm ");
		sql.append(" left join XG_QGZX_GWXZDMB d on a.gwlb = d.gwxzdm ");
		sql.append(" where a.shzt = '1' ");
		sql.append(" ) t where 1=1 ");
		QgzxGlyglService qgzxGlyglService = new QgzxGlyglService();
		//如果不是勤工管理员
		if(!qgzxGlyglService.sfQggly(user.getUserName())){
			sql.append(" and (t.yhm ='"+user.getUserName()+"' or t.zgh = '"+user.getUserName()+"') ");
		}
		sql.append(searchTj);
		return getPageList(model,sql.toString(),inputV);
	}
	public List<HashMap<String, String>> getExportList(QgzxCxtjForm t, User user)
			throws Exception {
		// TODO 自动生成方法存根
		//生成高级查询相关条件、条件值
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputValue = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (");
		sql.append("select t.gwdm,t.gwmc,t.gwxzdm,case when t.gwxzdm = '0' then '临时' else '正式' end gzxzmc,t.xqrs,t.fbsj,t.xn ");
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
		sql.append(" left join fdyxxb t4 on t1.zgh = t4.zgh where t.shzt = '1'");
		sql.append(") a where 1=1 ");
		QgzxGlyglService qgzxGlyglService = new QgzxGlyglService();
		//如果不是勤工管理员
		if(!qgzxGlyglService.sfQggly(user.getUserName())){
			sql.append(" and (a.yhm ='"+user.getUserName()+"' or a.zgh = '"+user.getUserName()+"') ");
		}
		sql.append(searchTj);
		return dao.getListNotOut(sql.toString(),inputValue);
	}
	public List<HashMap<String,String>> getGwryList(String type,String gwdm){
		StringBuilder sql = new StringBuilder();
		sql.append("select a.xh,rownum r,b.xm,c.pyccmc,b.bjmc,a.sgsj,a.tgsj from xg_qgzx_new_xsgwxxb a ");
		sql.append(" left join view_xsjbxx b on a.xh = b.xh ");
		sql.append(" left join XG_XSXX_PYCCDMB c on b.pycc = c.pyccdm");
		sql.append(" where a.zgzt = ? and a.gwdm = ?");
		return dao.getListNotOut(sql.toString(),new String[]{type,gwdm});
	}
	/**
	 * 岗位信息查看
	 * 
	 * @param model
	 * @return
	 */
	public HashMap<String, String> gwxxCk(QgzxCxtjForm model) {
		String sql = "select * from view_xg_qgzx_gwxxb where gwdm =? ";
		String[] inputValue = { model.getPkValue() };
		return dao.getMapNotOut(sql, inputValue);
	}

	/**
	 * 根据岗位代码和岗位状态获得学生岗位信息
	 * 
	 * @param model
	 * @return
	 * @throws SQLException
	 */
	public String[] getRyxhList(QgzxCxtjForm model) throws SQLException {
		String sql = " select xh from xg_qgzx_xsgwxxb where gwdm = ? and zgzt = ? order by sgsj desc,xh ";
		String[] inputValue = { model.getPkValue(), model.getZgzt() };
		return dao.getArray(sql, inputValue, "xh");
	}

	/**
	 * 获得岗位学生信息
	 * 
	 * @param myForm
	 * @return
	 */
	public HashMap<String, String> getGwxsxx(QgzxCxtjForm model) {
		String sql = "select a.sgsj,a.tgsj,a.zgzt,b.*,(select count(1) from xg_qgzx_xsgwxxb where xh = ? and zgzt='zg' )qggws from xg_qgzx_xsgwxxb a left join view_xsbfxx b on a.xh = b.xh where a.xh = ? and a.gwdm = ? and a.zgzt = ? and rownum = 1";
		String[] inputValue = { model.getXh(), model.getXh(),
				model.getPkValue(), model.getZgzt() };
		return dao.getMapNotOut(sql, inputValue);
	}

	/**
	 * 学生岗位查询
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String,String>> xsgwCx(QgzxCxtjForm model,User user) throws Exception {
		// 高级查询MODEL
		SearchModel searchModel = model.getSearchModel();
		// 高级查询条件
		String searchTj = SearchService.getSearchTj(searchModel);
		String[] inputV = SearchService.getTjInput(searchModel);

		StringBuilder sql = new StringBuilder();
		sql.append("select * from (");
		sql.append("select c.xn,a.xh,b.xm,b.bjdm,b.bjmc,b.zybj,b.zybjmc,c.gwmc ");
		sql.append(",nvl(d.yrdwmc,e.bmmc) yrdwmc,a.sgsj,d.yhm,d.zgh,case when a.zgzt='zg' then '在岗' else '已离职' end zgzt ");
		sql.append(",b.nj,b.xydm,b.xymc,b.zydm,b.zymc,d.bgdd,nvl(d.xm,f.xm) fzrxm,d.lxdh,b.symc1 symc");
		sql.append(" from XG_QGZX_NEW_XSGWXXB a");
		sql.append(" left join XG_QGZX_GWXXB c on a.gwdm = c.gwdm ");
		sql.append(" left join view_xsjbxx b on a.xh = b.xh ");
		sql.append(" left join XG_QGZX_YRDWDMB d on c.yrdwid = d.id ");
		sql.append(" left join ZXBZ_XXBMDM e on d.xydm = e.bmdm ");
		sql.append(" left join fdyxxb f on d.zgh = f.zgh ");
		sql.append(" ) t where 1=1 ");
		QgzxGlyglService qgzxGlyglService = new QgzxGlyglService();
		//如果不是勤工管理员
		if(!qgzxGlyglService.sfQggly(user.getUserName())){
			sql.append(" and (t.yhm ='"+user.getUserName()+"' or t.zgh = '"+user.getUserName()+"') ");
		}
		sql.append(searchTj);
		return getPageList(model,sql.toString(),inputV);
	}
	public List<HashMap<String,String>> xsgzjlCx(QgzxCxtjForm model,User user) throws Exception {
		// 高级查询MODEL
		SearchModel searchModel = model.getSearchModel();
		// 高级查询条件
		String searchTj = SearchService.getSearchTj(searchModel);
		String[] inputV = SearchService.getTjInput(searchModel);

		StringBuilder sql = new StringBuilder();
		sql.append("select * from (");
		sql.append(" select a.xh,b.xm,b.xb,case when b.xb = '1' then '男' when b.xb = '2' then '女' else b.xb end xbmc ");
		sql.append(" ,b.pycc,c.pyccmc,b.nj,b.sydm1 sydm,b.symc1 symc,b.zydm,b.zymc,b.bjdm,b.bjmc,b.zybj,b.zybjmc");
		sql.append(" ,nvl((select count(gs) from XG_QGZX_MRKH_JGB group by xh having xh = a.xh),'0') gzzsc");
		sql.append(" from (select xh from XG_QGZX_NEW_XSGWXXB group by xh) a");
		sql.append(" left join view_xsjbxx b on a.xh = b.xh ");
		sql.append(" left join XG_XSXX_PYCCDMB c on b.pycc = c.pyccdm ");
		sql.append(" ) t where 1=1 ");
		QgzxGlyglService qgzxGlyglService = new QgzxGlyglService();

		sql.append(searchTj);
		return getPageList(model,sql.toString(),inputV);
	}
	public List<HashMap<String,String>> xsgzjlMxCx(QgzxCxtjForm model,User user) throws Exception {
		// 高级查询MODEL
		SearchModel searchModel = model.getSearchModel();
		// 高级查询条件
		String searchTj = SearchService.getSearchTj(searchModel);
		String[] inputV = SearchService.getTjInput(searchModel);

		StringBuilder sql = new StringBuilder();
		sql.append("select * from (");
		sql.append(" select nvl(c.yrdwmc,d.bmmc) yrdwmc,a.gwdm,b.gwmc,a1.sqsj,a.sgsj,a.zgzt,a.tgsj,c.yhm,c.zgh ");
		sql.append(" ,nvl((select count(gs) from XG_QGZX_MRKH_JGB where xh = a.xh and gwdm = a.gwdm ),'0') gzsc");
		sql.append(" ,case when a.zgzt='zg' then '在岗' else '已离职' end zgztmc");
		sql.append(" ,a.xh,e.xm,e.bjmc");
		sql.append(" from XG_QGZX_NEW_XSGWXXB a");
		sql.append(" left join XG_QGZX_XSGWSQB a1 on a.sqbh = a1.sqbh ");
		sql.append(" left join XG_QGZX_GWXXB b on a.gwdm = b.gwdm ");
		sql.append(" left join XG_QGZX_YRDWDMB c on b.yrdwid = c.id ");
		sql.append(" left join ZXBZ_XXBMDM d on c.xydm = d.bmdm ");
		sql.append(" left join view_xsjbxx e on a.xh = e.xh ");
		sql.append(" where a.xh = '"+model.getXh()+"' ");
		sql.append(" ) t where 1=1 ");
		QgzxGlyglService qgzxGlyglService = new QgzxGlyglService();
		//如果不是勤工管理员
		if(!qgzxGlyglService.sfQggly(user.getUserName())){
			sql.append(" and (t.yhm ='"+user.getUserName()+"' or t.zgh = '"+user.getUserName()+"') ");
		}
		sql.append(searchTj);
		return getPageList(model,sql.toString(),inputV);
	}
	public List<HashMap<String,String>> xsgzCx(QgzxCxtjForm model,User user) throws Exception {
		// 高级查询MODEL
		SearchModel searchModel = model.getSearchModel();
		// 高级查询条件
		String searchTj = SearchService.getSearchTj(searchModel);
		String[] inputV = SearchService.getTjInput(searchModel);

		StringBuilder sql = new StringBuilder();
		sql.append("select * from (");
		sql.append(" select a.xh,a.nf,a.yf,gs,je,yrdw,b.xm,b.yhkh,b.yhmc,b.bjdm,b.bjmc,b.zybj,b.zybjmc,b.xydm,b.xymc,b.zydm,b.zymc,b.sjhm,b.nj from (");
		sql.append(" select a.xh,substr(a.ffsj, 0, 4) nf,substr(a.ffsj, 6) yf, a.ffsj,sum(a.gs) gs,sum(a.je) je,wm_concat(nvl(b.yrdwmc,c.bmmc)) yrdw ");
		sql.append(" from (select xh,substr(ffsj, 0, 4) nf,substr(ffsj, 6) yf, ffsj,yrbm,sum(gs) gs,sum(je) je ");
		sql.append(" from XG_QGZX_CJFF  group by xh,ffsj,yrbm ) a ");
		sql.append("  left join xg_qgzx_yrdwdmb b on a.yrbm = b.yrdwdm ");
		sql.append(" left join zxbz_xxbmdm c on a.yrbm = c.bmdm group by a.xh,a.ffsj  ) a");
		sql.append(" left join (select a.*, (select yhmc from dmk_yh d where a.yhdm = d.yhdm) yhmc,c.bjmc zybjmc, b.sydm, b.symc,c.xymc from XSXXB a ");
		sql.append(" left join view_njsybj b on a.bjdm = b.bjdm left join view_njxyzybj_all c ");
		sql.append(" on a.zybj = c.bjdm )b on a.xh = b.xh ");
		sql.append(" ) t where 1=1 ");

		sql.append(searchTj);
		return getPageList(model,sql.toString(),inputV);
	}
	public List<HashMap<String,String>> xsgzffCx(String xh) throws Exception {

		StringBuilder sql = new StringBuilder();
		sql.append(" select rownum r,d.bmmc yrdwmc,b.gwmc,substr(a.ffsj,0,4) nf,substr(a.ffsj,6) yf,a.gs,a.je ");
		sql.append(" from XG_QGZX_CJFF a");
		sql.append(" left join XG_QGZX_GWXXB b on a.gwdm = b.gwdm ");
		sql.append(" left join XG_QGZX_YRDWDMB c on b.yrdwid = c.id ");
		sql.append(" left join ZXBZ_XXBMDM d on c.xydm = d.bmdm ");
		sql.append(" where a.xh=?");
		return dao.getListNotOut(sql.toString(), new String[]{xh});
	}
	public List<HashMap<String,String>> dwgzCx(QgzxCxtjForm model,User user) throws Exception {
		// 高级查询MODEL
		SearchModel searchModel = model.getSearchModel();
		// 高级查询条件
		String searchTj = SearchService.getSearchTj(searchModel);
		String[] inputV = SearchService.getTjInput(searchModel);

		StringBuilder sql = new StringBuilder();
		sql.append(" select a.*from (");
		if("ngz".equals(model.getShzt())){
			sql.append("select a.yrdwdm,a.yrdwmc,a.nd,sum(yjsrs) yjsrs,sum(bksrs) bksrs," +
					"sum(ffje) ffje,sum(zgs) zgs,a.dwlb from (");
		}
		sql.append(" select a.yrdwdm,b.bmmc yrdwmc,b.dwlb,");
		sql.append(" a.ffny,substr(a.ffny, 0, 4) nd,substr(a.ffny, 6, 2) yf,");
		sql.append("(select count(1)" +
				"                  from (select a.yf, b.xn, c.XYDM yrdwdm, a.xq" +
				"                          from xg_qgzx_jcffsqb a" +
				"                          left join xg_qgzx_gwxxb b" +
				"                            on a.gwdm = b.gwdm" +
				"                          left join xg_qgzx_yrdwdmb c" +
				"                            on b.yrdwid = c.id" +
				"                          left join view_xsjbxx e" +
				"                            on a.xh = e.xh" +
				"                         where e.pycc = '1'" +
				"                            or e.pycc = '2') d" +
				"                 where a.xn = d.xn" +
				"                   and a.yrdwdm = d.yrdwdm" +
				"                   and a.ffny = d.yf" +
				"                   and (a.xq is null or a.xq = d.xq)) yjsrs,");
		sql.append("(select count(1)" +
				"                  from (select a.yf, b.xn, c.XYDM yrdwdm, a.xq" +
				"                          from xg_qgzx_jcffsqb a" +
				"                          left join xg_qgzx_gwxxb b" +
				"                            on a.gwdm = b.gwdm" +
				"                          left join xg_qgzx_yrdwdmb c" +
				"                            on b.yrdwid = c.id" +
				"                          left join view_xsjbxx e" +
				"                            on a.xh = e.xh" +
				"                         where e.pycc = '3') d" +
				"                 where a.xn = d.xn" +
				"                   and a.yrdwdm = d.yrdwdm" +
				"                   and a.ffny = d.yf" +
				"                   and (a.xq is null or a.xq = d.xq)) bksrs,");
		sql.append("(select sum(je)" +
				"                  from (select a.yf, a.je, b.xn, c.xydm yrdwdm, a.xq" +
				"                          from xg_qgzx_jcffsqb a" +
				"                          left join xg_qgzx_gwxxb b" +
				"                            on a.gwdm = b.gwdm" +
				"                          left join xg_qgzx_yrdwdmb c" +
				"                            on b.yrdwid = c.id) e" +
				"                 where a.xn = e.xn" +
				"                   and a.yrdwdm = e.yrdwdm" +
				"                   and a.ffny = e.yf" +
				"                   and (a.xq is null or a.xq = e.xq)) ffje,");
		sql.append("(select sum(gs)" +
				"                  from (select a.yf, a.gs, b.xn, c.xydm yrdwdm, a.xq" +
				"                          from xg_qgzx_jcffsqb a" +
				"                          left join xg_qgzx_gwxxb b" +
				"                            on a.gwdm = b.gwdm" +
				"                          left join xg_qgzx_yrdwdmb c" +
				"                            on b.yrdwid = c.id) e" +
				"                 where a.xn = e.xn" +
				"                   and a.yrdwdm = e.yrdwdm" +
				"                   and a.ffny = e.yf" +
				"                   and (a.xq is null or a.xq = e.xq)) zgs");
		sql.append(" from xg_qgzx_gwffztb a");
		sql.append(" left join view_xg_qgzx_yrdwdmb b on a.yrdwdm = b.bmdm ");
		sql.append(" ) a where 1=1 ");
		if("ngz".equals(model.getShzt())){
			sql.append(" group by a.yrdwdm,a.yrdwmc,a.nd,a.dwlb");
			sql.append(" ) a where 1=1 ");
		}
		sql.append(searchTj);
		return getPageList(model,sql.toString(),inputV);
	}
	/**
	 * 学生岗位查看
	 * 
	 * @param model
	 * @return
	 */
	public HashMap<String, String> xsgwCk(QgzxCxtjForm model) {
		String sql = "select a.xh, b.xm, b.xb, b.nj, b.xymc, b.zymc, b.bjmc,a.tgsj,a.tgyy, c.gwmc, d.bmmc,c.gwxzmc, a.sgsj from xg_qgzx_xsgwxxb a left join view_xsbfxx b on a.xh = b.xh left join (select a.*,b.gwxzmc from xg_qgzx_gwxxb a left join xg_qgzx_gwxzdmb b on a.gwxzdm = b.gwxzdm ) c on a.gwdm = c.gwdm left join view_xg_qgzx_yrdwdmb d on c.yrdwdm = d.bmdm where a.gwdm = ? and a.xh = ? and a.sgsj = ? and rownum = 1";
		String[] inputValue = model.getPkValue().split("!!@@!!");
		String[] outputValue = { "xh", "xm", "xb", "nj", "xymc", "zymc",
				"bjmc", "tgsj", "tgyy", "gwmc", "bmmc", "gwxzmc", "sgsj" };
		return dao.getMap(sql, inputValue, outputValue);
	}
	/**
	 * 学生岗位信息
	 */
	public HashMap<String, String> getXsgwxx(QgzxCxtjForm model) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.xh, a.tgsj,a.tgyy,a.gwdm, c.gwmc, d.bmmc yrdwmc,c.gwxzmc, a.sgsj, e.sqly ");
		sql.append(" from xg_qgzx_xsgwxxb a ");
		sql.append(" left join ( ");
		sql.append(" select a.*,b.gwxzmc from xg_qgzx_gwxxb a left join xg_qgzx_gwxzdmb b on a.gwxzdm = b.gwxzdm  ");
		sql.append(" ) c on a.gwdm = c.gwdm ");
		sql.append(" left join view_xg_qgzx_yrdwdmb d on c.yrdwdm = d.bmdm ");
		sql.append(" left join xg_qgzx_xsgwsqb e on a.sqbh=e.sqbh ");
		sql.append(" where a.gwdm = ? and a.xh = ? and a.sgsj = ? and rownum = 1 ");
		
		String[] inputValue = model.getPkValue().split("!!@@!!");
		return dao.getMapNotOut(sql.toString(), inputValue);
	}
	
	/**
	 * 获取学生岗位信息列表
	 */
	public List<HashMap<String, String>> getXsgwxxList(String xh) {
		StringBuffer sb = new StringBuffer();
		sb.append("select (select bmmc from view_xg_qgzx_yrdwdmb c where c.bmdm=b.yrdwdm) yrdwmc,b.gwmc,a.gwdm");
		sb.append(",(select gwxzmc from xg_qgzx_gwxzdmb c where b.gwxzdm=c.gwxzdm) gwxzmc,a.xh,a.sgsj");
		sb.append(",(case when a.zgzt='zg' then '在岗' else '退岗' end) zgzt,a.tgsj,a.tgyy from xg_qgzx_xsgwxxb a,xg_qgzx_gwxxb b ");
		sb.append("where a.gwdm=b.gwdm and xh=? ");
		sb.append("order by a.sgsj desc,b.yrdwdm,a.gwdm");
		String[] inputValue = { xh };
		return dao.getListNotOut(sb.toString(), inputValue);
	}

	/**
	 * 经费划拨查询
	 * 
	 * @param model
	 * @return
	 */
	public ArrayList<String[]> jfhbCx(QgzxCxtjForm model) throws Exception {
		// 高级查询MODEL
		SearchModel searchModel = model.getSearchModel();
		// 用户对象
		User user = model.getUser();
		// 高级查询条件
		String searchTj = SearchService.getSearchTj(searchModel);
		String[] inputV = SearchService.getTjInput(searchModel);
		// 权限控制
		String searchTjQx = "";
		QgzxGlyglService qgzxGlyglService = new QgzxGlyglService();
		// 如果不是勤工管理员
		if (!qgzxGlyglService.sfQggly(user.getUserName())) {
			searchTjQx += " and yrdwdm = '" + user.getUserDep() + "' ";
		}
		StringBuilder sql = new StringBuilder();
		String[] colList = new String[] { "pkValue", "r", "yf", "yrdwmc",
				"hbzje", "yffje", "syje" };
		sql.append(" select rownum r,a.* from (select yf||'!!@@!!'||yrdwdm pkValue,a.* from ");
		if("1".equals(new QgzxCsszService().getJfhbfs())){
			sql.append("view_xg_qgzx_jfhbb_yf");
		}else{
			sql.append("view_xg_qgzx_jfhbb_nd");
		}
		sql.append(" a order by yf,yrdwdm) a where 1 = 1");
		// ====================SQL拼装 end================================
		return CommonQueryDAO.commonQuery(sql.toString(),
				searchTj + searchTjQx, inputV, colList, model);
	}

	/**
	 * 经费划拨查看
	 * 
	 * @param model
	 * @return
	 */
	public HashMap<String, String> jfhbCk(QgzxCxtjForm model) {
		String sql = "select a.*,b.bmmc from xg_qgzx_jfhbb a left join view_xg_qgzx_yrdwdmb b on a.yrdwdm = b.bmdm where nd = ? and yrdwdm = ? and rownum = 1 ";
		String[] inputValue = model.getPkValue().split("!!@@!!");
		String[] outputValue = { "nd", "bmmc" };
		return dao.getMap(sql, inputValue, outputValue);
	}

	/**
	 * 经费划拨项目查看
	 * 
	 * @param model
	 * @return
	 */
	public List<HashMap<String, String>> jfhbxmList(QgzxCxtjForm model) {
		String sql = "select hbsj,hbje,bz from xg_qgzx_jfhbb where nd = ? and yrdwdm = ?";
		String[] inputValue = model.getPkValue().split("!!@@!!");
		String[] outputValue = { "hbsj", "hbje", "bz" };
		return dao.getList(sql, inputValue, outputValue);
	}

	/**
	 * 酬金发放查询
	 * 
	 * @param model
	 * @return
	 */
	public ArrayList<String[]> cjffCx(QgzxCxtjForm model) throws Exception {
		// 高级查询MODEL
		SearchModel searchModel = model.getSearchModel();
		// 用户对象
		User user = model.getUser();
		// 高级查询条件
		String searchTj = SearchService.getSearchTj(searchModel);
		String[] inputV = SearchService.getTjInput(searchModel);
		// 权限控制
		String searchTjQx = "";
		QgzxGlyglService qgzxGlyglService = new QgzxGlyglService();
		// 如果不是勤工管理员
		if (!qgzxGlyglService.sfQggly(user.getUserName())) {
			searchTjQx += SearchService.getSearchTjByUser(user, "a", "xydm",
					"bjdm");
			// searchTjQx+=" and yrdwdm = '"+user.getUserDep()+"' ";
		}
		StringBuilder sql = new StringBuilder();
		String[] colList=null;
		if("13855".equals(Base.xxdm)){
			colList = new String[] { "pkValue", "xh", "xm", "bjmc",
					"yrdwmc", "gwmc", "gwxzmc", "yhmc", "yhkh", "bz", "ffny", "je",
					"khdj" };
		}
		else{
		    colList = new String[] { "pkValue", "xh", "xm", "bjmc",
				"yrdwmc", "gwmc", "gwxzmc", "yhmc", "yhkh", "bz", "ffny", "je","zybjmc"
				 };
		}
		sql
				.append("select rownum r,a.* from (select xh || '!!@@!!' || a.gwdm || '!!@@!!' || a.ffny pkValue,a.* FROM view_xg_qgzx_cjffb_fast  a order by ffny desc,gwmc) a where 1=1  ");
		return CommonQueryDAO.commonQuery(sql.toString(),
				searchTj + searchTjQx, inputV, colList, model);
	}

	/**
	 * 酬金发放查看
	 * 
	 * @param model
	 * @return
	 */
	public HashMap<String, String> cjffCk(QgzxCxtjForm model) {
		StringBuilder sql = new StringBuilder();
		sql
				.append("select b.*,c.gwxzmc from view_xg_qgzx_cjffb b left join xg_qgzx_gwxxb a on b.gwdm = a.gwdm and b.xn = a.xn left join xg_qgzx_gwxzdmb c on a.gwxzdm = c.gwxzdm  where b.xh =? and b.gwdm = ? and b.ffny =?");
		String[] inputValue = model.getPkValue().split("!!@@!!");
		return dao.getMapNotOut(sql.toString(), inputValue);
	}

	/**
	 * 获得部门列表
	 * 
	 * @return
	 */
	public List<HashMap<String, String>> getBmList() {
		String sql = " select distinct bmdm,bmmc from view_xg_qgzx_yrdwdmb where bmmc<>'未确定' order by bmdm ";
		String[] outputValue = { "bmdm", "bmmc" };
		return dao.getList(sql, new String[] {}, outputValue);
	}

	/**
	 * 部门酬金发放统计（月份）
	 * 
	 * @param model
	 * @param searchTj
	 * @return
	 */
	public ArrayList<String[]> bmcjffTjByYf(QgzxCxtjForm model)
			throws Exception {
		StringBuilder sql = new StringBuilder();
		String[] colList = new String[] { "yrdwmc", "rs", "rc","gs", "je" };
		sql
				.append(" select yrdwmc,rs,rc,gs,je from view_xg_qgzx_bmcjfftj_yf b ");
		if (model.getBmdm() != null && "%".equals(model.getBmdm())) {
			sql.append(" where substr(ffyf,0,4)=? and substr(ffyf,6)=?   and 1=1 ");
		}else if("xj".equals(model.getBmdm())){
			sql.append("left join ZXBZ_XXBMDM c on b.yrdwdm = c.bmdm  where c.bmlb='1' and substr(ffyf,0,4)=? and substr(ffyf,6)=?  ");
		} else if("yj".equals(model.getBmdm())){
			sql.append("left join ZXBZ_XXBMDM c on b.yrdwdm = c.bmdm  where c.bmlb='5' and substr(ffyf,0,4)=? and substr(ffyf,6)=?  ");
		} else {
			sql.append(" where substr(ffyf,0,4)=? and substr(ffyf,6)=?   and yrdwdm ='" + model.getBmdm() + "' ");
		}
		return CommonQueryDAO.commonQueryNotFy(sql.toString(), "",
				new String[] { model.getNd(), model.getYf() }, colList, model);
	}
	/**
	 * @描述:岗位酬金发放统计（月份）
	 * @作者：CP[工号：1352]
	 * @日期：2017-8-18 上午10:38:08
	 * @param model
	 * @return
	 * @throws Exception
	 * ArrayList<String[]> 返回类型 
	 * @throws
	 */
	public ArrayList<String[]> gwcjffTjByYf(QgzxCxtjForm model)
	throws Exception {
			StringBuilder sql = new StringBuilder();
			String[] colList = new String[] { "yrdwmc","gwmc", "rs", "rc","gs", "je" };
			sql.append(" select yrdwmc,gwmc,rs,rc,gs,je from view_xg_qgzx_gwcjfftj_yf b ");
			if (model.getBmdm() != null && "%".equals(model.getBmdm())) {
				sql.append("  where substr(ffyf,0,4)=? and substr(ffyf,6)=?   and 1=1 ");
			}else if("xj".equals(model.getBmdm())){
				sql.append("left join ZXBZ_XXBMDM c on b.yrdwdm = c.bmdm  where c.bmlb='1' and substr(ffyf,0,4)=? and substr(ffyf,6)=? ");
			} else if("yj".equals(model.getBmdm())){
				sql.append("left join ZXBZ_XXBMDM c on b.yrdwdm = c.bmdm  where c.bmlb='5' and substr(ffyf,0,4)=? and substr(ffyf,6)=?  ");
			} else {
				sql.append("  where substr(ffyf,0,4)=? and substr(ffyf,6)=?   and yrdwdm ='" + model.getBmdm() + "' ");
			}
			if (model.getGwmc() != null && "%".equals(model.getGwmc())) {
				sql.append(" and 1=1 ");
			}else {
				sql.append(" and gwmc like '" + "%" + model.getGwmc()+ "%" + "' ");
			}
			return CommonQueryDAO.commonQueryNotFy(sql.toString(), "",
					new String[] { model.getNd(), model.getYf() }, colList, model);
}
	
	/**
	 * 部门酬金发放统计（年度）
	 * 
	 * @param model
	 * @param searchTj
	 * @return
	 */
	public ArrayList<String[]> bmcjffTjByNd(QgzxCxtjForm model)
			throws Exception {
		StringBuilder sql = new StringBuilder();
		if (model.getBmdm() != null && "%".equals(model.getBmdm())) {

		}
		String[] colList = new String[] { "yrdwmc", "gwa", "rsa", "jea", "gwb",
				"rsb", "jeb", "gwc", "rsc", "jec", "gwd", "rsd", "jed", "gwe",
				"rse", "jee", "gwf", "rsf", "jef", "gwg", "rsg", "jeg", "gwh",
				"rsh", "jeh", "gwi", "rsi", "jei", "gwj", "rsj", "jej", "gwk",
				"rsk", "jek", "gwl", "rsl", "jel", "gwz", "rsz", "jez" };
		sql.append(" select * from view_xg_qgzx_bmcjfftj_nd b  ");

		if (model.getBmdm() != null && "%".equals(model.getBmdm())) {
			sql.append("where 1=1 ");
		}else if("xj".equals(model.getBmdm())){
			sql.append("left join ZXBZ_XXBMDM c on b.yrdwdm = c.bmdm  where c.bmlb='1' ");
		} else if("yj".equals(model.getBmdm())){
			sql.append("left join ZXBZ_XXBMDM c on b.yrdwdm = c.bmdm  where c.bmlb='5' ");
		}else {
			sql.append("where b.yrdwdm ='" + model.getBmdm() + "' ");
		}
		sql.append("  and b.ffnd = ?  ");
		return CommonQueryDAO.commonQueryNotFy(sql.toString(), "",
				new String[] { model.getNd() }, colList, model);
	}

	/**
	 * 
	 * @描述:岗位酬金发放统计（年度）
	 * @作者：CP[工号：1352]
	 * @日期：2017-8-18 上午10:42:47
	 * @param model
	 * @return
	 * @throws Exception
	 * ArrayList<String[]> 返回类型 
	 * @throws
	 */
	public ArrayList<String[]> gwcjffTjByNd(QgzxCxtjForm model)
		throws Exception {
		StringBuilder sql = new StringBuilder();
		if (model.getBmdm() != null && "%".equals(model.getBmdm())) {

		}
		String[] colList = new String[] { "yrdwmc","gwmc", "rsa", "jea",
				"rsb", "jeb", "rsc", "jec",  "rsd", "jed",
				"rse", "jee", "rsf", "jef", "rsg", "jeg",
				"rsh", "jeh", "rsi", "jei", "rsj", "jej",
				"rsk", "jek",  "rsl", "jel",  "rsz", "jez" };
		sql.append(" select * from view_xg_qgzx_gwcjfftj_nd b  ");

		if (model.getBmdm() != null && "%".equals(model.getBmdm())) {
			sql.append(" where 1=1 ");
		}else if("xj".equals(model.getBmdm())){
			sql.append(" left join ZXBZ_XXBMDM c on b.yrdwdm = c.bmdm  where c.bmlb='1' ");
		} else if("yj".equals(model.getBmdm())){
			sql.append(" left join ZXBZ_XXBMDM c on b.yrdwdm = c.bmdm  where c.bmlb='5' ");
		} else {
			sql.append(" where b.yrdwdm ='" + model.getBmdm() + "' ");
		}
		if (model.getGwmc() != null && "%".equals(model.getGwmc())) {
			sql.append(" and 1=1 ");
		}else {
			sql.append(" and gwmc like '" + "%" + model.getGwmc()+ "%" + "' ");
		}
		sql.append("  and b.ffnd = ?  ");
		return CommonQueryDAO.commonQueryNotFy(sql.toString(), "",
				new String[] { model.getNd() }, colList, model);
	}

	/**
	 * 个人酬金发放统计（月份）
	 * 
	 * @param model
	 * @param searchTj
	 * @return
	 */
	public ArrayList<String[]> grcjffTjByYf(QgzxCxtjForm model)
			throws Exception {
		StringBuilder sql = new StringBuilder();
		String[] colList = null;
		if(model.getBmdm() != null && "%".equals(model.getBmdm())) {
			
			colList = new String[] { "xh", "xm", "nj", "xymc", "bmmc", "gwmc", "zymc",
					"bjmc", "yhmc", "yhkh", "e" };	
		}else {
		
			colList = new String[] { "xh", "xm", "nj", "xymc", "gwmc", "zymc",
					"bjmc", "yhmc", "yhkh", "e" };
		}
		sql.append("select * from view_xg_qgzx_grcjfftj_yf x ");
		
		if(model.getBmdm() != null && "%".equals(model.getBmdm())) {
			sql.append(" left join view_xg_qgzx_yrdwdmb z on x.yrbm = z.bmdm ");
		}else if("xj".equals(model.getBmdm())){
			sql.append(" left join ZXBZ_XXBMDM d on x.yrbm= d.bmdm  ");
		} else if("yj".equals(model.getBmdm())){
			sql.append(" left join ZXBZ_XXBMDM d on x.yrbm = d.bmdm  ");
		}
		sql.append(" where substr(ffsj,0,4)=? and substr(ffsj,6)=?  ");
		if("12036".equals(Base.xxdm)){
			sql.append(" and e > 0 ");
		}
		if (model.getBmdm() != null && "%".equals(model.getBmdm())) {
			sql.append("and 1=1 ");
		}else if("xj".equals(model.getBmdm())){
			sql.append("and d.bmlb='1' ");
		} else if("yj".equals(model.getBmdm())){
			sql.append(" and d.bmlb='5' ");
		}else {
			sql.append(" and yrbm='" + model.getBmdm() + "' ");
		}
		if (model.getGwmc() != null && "%".equals(model.getGwmc())) {
			sql.append(" and 1=1 ");
		}else {
			sql.append(" and gwmc like '" + "%" + model.getGwmc()+ "%" + "' ");
		}
		StringBuilder sqlN = new StringBuilder();
		if(model.getBmdm() != null && "%".equals(model.getBmdm())) {
			sqlN
			.append("select a.xh,a.xm,a.nj,a.xymc,a.bmmc,a.gwmc,a.yrbm,a.zymc,a.bjmc,b.yhmc,b.yhkh,a.ffsj,a.e from (");
		}else {
			sqlN
					.append("select a.xh,a.xm,a.nj,a.xymc,a.gwmc,a.yrbm,a.zymc,a.bjmc,b.yhmc,b.yhkh,a.ffsj,a.e from (");
		}
		sqlN.append(sql);
		sqlN.append(")a left join view_xsxxb b on a.xh=b.xh");
		return CommonQueryDAO.commonQueryNotFy(sqlN.toString(), "",
				new String[] { model.getNd(), model.getYf() }, colList, model);
	}

	/**
	 * 浙江传媒--酬金发放（按月份）
	 * 
	 * @param model
	 * @return
	 */
	public List<String[]> zjcmCjffByYf(QgzxCxtjForm model) {

		List<String> input = new ArrayList<String>();
		input.add(model.getNd() + "-" + model.getYf());
		StringBuilder sql = new StringBuilder();

		sql
				.append("select rownum r,a.yhkh,a.yhmc,a.xh,a.xm,a.bmmc,")
				.append("a.xymc,a.gwxzmc,b.dcmc xmzzjb, ")
				.append(
						"(case when pyccmc is null then pycc else pyccmc end)xl,")
				.append("a.gj,a.gs,a.je from (")
				.append("select yhkh,yhmc,xh,xm,bmmc,xymc,gwxzmc,pyccmc,pycc, ")
				.append(
						"sum(je)/case when sum(gs)=0 then 1 else sum(gs) end gj, sum(gs) gs,sum(je) je,xn from (")
				.append("select d.yhkh,e.yhmc,a.xh,d.xm,c.bmmc,")
				.append(
						"v.xymc,f.gwxzmc,g.pyccmc,d.pycc,a.gs,a.je,b.xn from xg_qgzx_jcffb a ")
				.append("left join xg_qgzx_gwxxb b on a.gwdm = b.gwdm ")
				.append("left join xg_qgzx_gwxzdmb f on b.gwxzdm=f.gwxzdm ")
				.append("left join zxbz_xxbmdm c on b.yrdwdm=c.bmdm ").append(
						"left join xsxxb d on a.xh=d.xh ").append(
						"left join dmk_yh e on d.yhdm=e.yhdm ").append(
						"left join xg_xsxx_pyccdmb g on d.pycc=g.pyccdm ")
				.append("left join view_njxyzybj v on d.bjdm=v.bjdm ").append(
						"where exists ( select 1 from xg_qgzx_gwffztb m ")
				.append("where b.xn=m.xn and b.yrdwdm=m.yrdwdm ").append(
						"and a.yf=m.ffny and m.tjzt='1' and m.ffny=? ");

		if (!StringUtil.isNull(model.getBmdm())) {
			sql.append(" and m.yrdwdm=? ");
			input.add(model.getBmdm());
		}

		sql
				.append(
						")) group by yhkh,yhmc,xh,xm,bmmc,xymc,gwxzmc,pyccmc,pycc,xn ")
				.append(" order by bmmc) a left join  (select xh, dcmc, xn ")
				.append(" from xg_xszz_new_knsjgb t1 ")
				.append(" left join xg_xszz_new_kndcdmb t2 ")
				.append(" on t1.rddc = t2.dcdm ")
				.append(") b on a.xh=b.xh and a.xn=b.xn")
				.append("");

		return dao.rsToVator(sql.toString(), input.toArray(new String[] {}),
				new String[] { "r", "yhkh", "yhmc", "xh", "xm", "bmmc", "xymc",
						"gwxzmc", "xmzzjb", "xl", "gj", "gs", "je" });
	}

	/**
	 * 个人酬金发放统计（年度）
	 * 
	 * @param model
	 * @param searchTj
	 * @return
	 */
	public ArrayList<String[]> grcjffTjByNd(QgzxCxtjForm model)
			throws Exception {
		StringBuilder sql = new StringBuilder();
		String[] colList = null;
		
		if(model.getBmdm() != null && "%".equals(model.getBmdm())) {
			colList = new String[] { "xh", "xm", "xymc", "bmmc", "gwmc", "yhmc",
					"yhkh", "e01", "e02", "e03", "e04", "e05", "e06", "e07", "e08",
					"e09", "e10", "e11", "e12", "e" };		
		}else {
			colList = new String[] { "xh", "xm", "xymc", "gwmc", "yhmc",
					"yhkh", "e01", "e02", "e03", "e04", "e05", "e06", "e07", "e08",
					"e09", "e10", "e11", "e12", "e" };
		}
		sql
				.append(" select * from view_xg_qgzx_grcjfftj_nd c ");
		if(model.getBmdm() != null && "%".equals(model.getBmdm())) {
			sql.append(" left join view_xg_qgzx_yrdwdmb z on c.yrbm = z.bmdm ");
		}else if("xj".equals(model.getBmdm())){
			sql.append(" left join ZXBZ_XXBMDM d on c.yrbm= d.bmdm  ");
		} else if("yj".equals(model.getBmdm())){
			sql.append(" left join ZXBZ_XXBMDM d on c.yrbm = d.bmdm  ");
		}
		sql.append(" where c.ffsj =? ");
		if (model.getBmdm() != null && "%".equals(model.getBmdm())) {
			sql.append(" and 1=1 ");
		}else if("xj".equals(model.getBmdm())){
			sql.append("and d.bmlb='1' ");
		} else if("yj".equals(model.getBmdm())){
			sql.append(" and d.bmlb='5' ");
		}else {
			sql.append(" and c.yrbm = '" + model.getBmdm() + "' ");
		}
		if (model.getGwmc() != null && "%".equals(model.getGwmc())) {
			sql.append(" and 1=1 ");
		}else {
			sql.append(" and c.gwmc like '" + "%" + model.getGwmc()+ "%" + "' ");
		}
		StringBuilder sqlN = new StringBuilder();
		if(model.getBmdm() != null && "%".equals(model.getBmdm())) {
			sqlN
					.append("select a.xh,a.xm,a.xymc,a.bmmc,a.gwmc,b.yhmc,b.yhkh,a.e01,a.e02,a.e03,a.e04,a.e05,a.e06,a.e07,a.e08,a.e09,a.e10,a.e11,a.e12,a.e from (");
		}else {
			sqlN
					.append("select a.xh,a.xm,a.xymc,a.gwmc,b.yhmc,b.yhkh,a.e01,a.e02,a.e03,a.e04,a.e05,a.e06,a.e07,a.e08,a.e09,a.e10,a.e11,a.e12,a.e from (");
		}
		sqlN.append(sql);
		sqlN.append(")a left join view_xsxxb b on a.xh=b.xh");
		return CommonQueryDAO.commonQueryNotFy(sqlN.toString(), "",
				new String[] { model.getNd() }, colList, model);
	}

	/**
	 * 根据部门代码获得部门名称
	 * 
	 * @param bmdm
	 * @return
	 */
	public String getBmmc(String bmdm) {
		String sql = "select bmmc from view_xg_qgzx_yrdwdmb where bmdm = ?";
		return dao.getOneRs(sql, new String[] { bmdm }, "bmmc");
	}

	/**
	 * 岗位信息查询
	 * 
	 * @param model
	 * @return
	 */
	public List<HashMap<String, String>> gwxxCxExport(QgzxCxtjForm model,
			User user) throws Exception {
		// 高级查询MODEL
		SearchModel searchModel = model.getSearchModel();
		// 用户对象
		// User user = model.getUser();
		// 高级查询条件
		String searchTj = SearchService.getSearchTj(searchModel);
		String[] inputV = SearchService.getTjInput(searchModel);
		// 权限控制
		String searchTjQx = "";
		QgzxGlyglService qgzxGlyglService = new QgzxGlyglService();
		// 如果不是勤工管理员
		if (!qgzxGlyglService.sfQggly(user.getUserName())) {
			searchTjQx += " and yrdwdm = '" + user.getUserDep() + "' ";
		}
		StringBuilder sql = new StringBuilder();
		String[] colList = new String[] { "gwdm", "r", "xn", "yrdwmc", "gwmc",
				"gwxzmc", "xqrs", "zgrs", "tgrs" };
		sql.append(" select a.*,rownum r from view_xg_qgzx_gwxxb a where 1=1 ");

		// ====================SQL拼装 end================================
		return CommonQueryDAO.commonQueryforExportList(sql.toString(), searchTj
				+ searchTjQx, inputV, colList, model);
	}

	/**
	 * 学生岗位查询自定义导出
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> xsgwCxExport(QgzxCxtjForm model,
			User user) throws Exception {
		// 高级查询MODEL
		SearchModel searchModel = model.getSearchModel();
		// 用户对象
		// User user = model.getUser();
		// 高级查询条件
		String searchTj = SearchService.getSearchTj(searchModel);
		String[] inputV = SearchService.getTjInput(searchModel);
		// 权限控制
		String searchTjQx = "";
		QgzxGlyglService qgzxGlyglService = new QgzxGlyglService();
		// 如果不是勤工管理员
		if (!qgzxGlyglService.sfQggly(user.getUserName())) {
			searchTjQx += SearchService.getSearchTjByUser(user, "a", "xydm",
					"bjdm");
			// searchTjQx+=" and yrdwdm = '"+user.getUserDep()+"' ";
		}
		StringBuilder sql = new StringBuilder();
		String[] colList = new String[] { "pkValue", "r", "xn", "yrdwmc",
				"gwmc", "gwxzmc", "xh", "xm", "nj", "bjmc", "zgztmc" };
		sql
				.append(" select rownum r,a.* from (select a.gwdm||'!!@@!!'||a.xh||'!!@@!!'||a.sgsj pkValue,a.* from view_xg_qgzx_xsgwxxb a order by xn,yrdwdm,gwdm,zgzt,nj,bjdm,xh)a where 1=1 ");
		// ====================SQL拼装 end================================
		return CommonQueryDAO.commonQueryforExportList(sql.toString(), searchTj
				+ searchTjQx, inputV, colList, model);
	}

	/**
	 * 自定义导出配置经费划拨查询
	 * 
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> jfhbCxExport(QgzxCxtjForm model,
			User user) throws Exception {
		// 高级查询MODEL
		SearchModel searchModel = model.getSearchModel();
		// 用户对象
		// User user = model.getUser();
		// 高级查询条件
		String searchTj = SearchService.getSearchTj(searchModel);
		String[] inputV = SearchService.getTjInput(searchModel);
		// 权限控制
		String searchTjQx = "";
		QgzxGlyglService qgzxGlyglService = new QgzxGlyglService();
		// 如果不是勤工管理员
		if (!qgzxGlyglService.sfQggly(user.getUserName())) {
			searchTjQx += " and yrdwdm = '" + user.getUserDep() + "' ";
		}
		StringBuilder sql = new StringBuilder();
		String[] colList = new String[] { "pkValue", "r", "nd", "yrdwmc",
				"hbzje", "yffje", "syje" };
		sql.append(" select rownum r,a.* from (select nd||'!!@@!!'||yrdwdm pkValue,a.* from ");
		if("1".equals(new QgzxCsszService().getJfhbfs())){
			sql.append("view_xg_qgzx_jfhbb_yf");
		}else{
			sql.append("view_xg_qgzx_jfhbb_nd");
		}
		sql.append(" a order by nd,yrdwdm) a where 1 = 1");
		// ====================SQL拼装 end================================
		return CommonQueryDAO.commonQueryforExportList(sql.toString(), searchTj
				+ searchTjQx, inputV, colList, model);
	}

	/**
	 * 酬金发放查询自定义导出
	 * 
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> cjffCxExport(QgzxCxtjForm model,
			User user) throws Exception {
		// 高级查询MODEL
		SearchModel searchModel = model.getSearchModel();
		// 用户对象
		// User user = model.getUser();
		// 高级查询条件
		String searchTj = SearchService.getSearchTj(searchModel);
		String[] inputV = SearchService.getTjInput(searchModel);
		// 权限控制
		String searchTjQx = "";
		QgzxGlyglService qgzxGlyglService = new QgzxGlyglService();
		// 如果不是勤工管理员
		if (!qgzxGlyglService.sfQggly(user.getUserName())) {
			searchTjQx += SearchService.getSearchTjByUser(user, "a", "xydm",
					"bjdm");
			// searchTjQx+=" and yrdwdm = '"+user.getUserDep()+"' ";
		}
		StringBuilder sql = new StringBuilder();
		String[] colList = new String[] { "pkValue", "r", "xh", "xm", "bjmc",
				"yrdwmc", "gwmc", "gwxzmc", "ffny", "je", "khdj" };
		sql
				.append("select rownum r,a.* from (select a.xh || '!!@@!!' || a.gwdm || '!!@@!!' || a.ffny pkValue,a.* FROM view_xg_qgzx_cjffb a ")
				.append(" order by ffny desc,gwmc) a where 1=1  ");
		// sql.append("select rownum r , a.* from VIEW_NEW_DC_QGZX_CXTJ_CJFF a where 1=1 ");
		return CommonQueryDAO.commonQueryforExportList(sql.toString(), searchTj
				+ searchTjQx, inputV, colList, model);
	}
	
	/**
	 * @描述:北京林业大学下载申报表
	 * @作者：江水才[工号：1150]
	 * @日期：2014-11-19 上午09:30:56
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> cjffCxExportData_10022(QgzxCxtjForm model,
			User user) throws Exception {
		// 高级查询MODEL
		SearchModel searchModel = model.getSearchModel();
		// 用户对象
		// User user = model.getUser();
		// 高级查询条件
		String searchTj = SearchService.getSearchTj(searchModel);
		String[] inputV = SearchService.getTjInput(searchModel);
		// 权限控制
		String searchTjQx = "";
		QgzxGlyglService qgzxGlyglService = new QgzxGlyglService();
		// 如果不是勤工管理员
		if (!qgzxGlyglService.sfQggly(user.getUserName())) {
			searchTjQx += SearchService.getSearchTjByUser(user, "a", "xydm",
			"bjdm");
			// searchTjQx+=" and yrdwdm = '"+user.getUserDep()+"' ";
		}
		StringBuilder sql = new StringBuilder();
		String[] colList = new String[] { "pkValue", "r", "xh", "xm", "bjmc",
				"yrdwmc", "gwmc", "gwxzmc", "ffny", "je", "khdj", "qsdhsjhm", "yrdwdm", "gs" };
		sql.append("select rownum r,a.* from (select xh || '!!@@!!' || a.gwdm || '!!@@!!' || a.ffny pkValue,decode(qsdh,null,sjhm) qsdhsjhm,a.* FROM view_xg_qgzx_cjffb  a order by yrdwdm,ffny desc,gwmc) a where 1=1  ");
		return CommonQueryDAO.commonQueryforExportList(sql.toString(), searchTj
				+ searchTjQx, inputV, colList, model);
	}

	/**
	 * @描述:浙江传媒酬金发放个性化导出
	 * @作者：cq [工号：785]
	 * @日期：2013-12-26 下午05:30:03
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getCjffExpCm(QgzxCxtjForm model,
			User user) {

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();

		// 权限控制
		String searchTjQx = "";
		QgzxGlyglService qgzxGlyglService = new QgzxGlyglService();
		// 如果不是勤工管理员
		if (!qgzxGlyglService.sfQggly(user.getUserName())) {
			searchTjQx += SearchService.getSearchTjByUser(user, "a", "xydm",
					"bjdm");
			// searchTjQx+=" and yrdwdm = '"+user.getUserDep()+"' ";
		}

		sql
				.append("select rownum r,a.* from (select xh || '!!@@!!' || a.gwdm || '!!@@!!' || a.ffny pkValue,a.* FROM view_xg_qgzx_cjffb  a order by ffny desc,gwmc,xh) a where 1=1 ");

		if (model.getNjs() != null && model.getNjs().length > 0) {
			sql.append(" and nj in (");
			for (String s : model.getNjs()) {
				sql.append("'" + s + "',");
			}
			sql.append("'')");
		}
		if (model.getXydms() != null && model.getXydms().length > 0) {
			sql.append(" and xydm in (");
			for (String s : model.getXydms()) {
				sql.append("'" + s + "',");
			}
			sql.append("'')");
		}
		if (model.getZydms() != null && model.getZydms().length > 0) {
			sql.append(" and zydm in (");
			for (String s : model.getZydms()) {
				sql.append("'" + s + "',");
			}
			sql.append("'')");
		}
		if (model.getBjdms() != null && model.getBjdms().length > 0) {
			sql.append(" and bjdm in (");
			for (String s : model.getBjdms()) {
				sql.append("'" + s + "',");
			}
			sql.append("'')");
		}
		if (model.getFfnd() != null && model.getFfnd().length > 0) {
			sql.append(" and nd in (");
			for (String s : model.getFfnd()) {
				sql.append("'" + s + "',");
			}
			sql.append("'')");
		}
		if (model.getFfyf() != null && model.getFfyf().length > 0) {
			sql.append(" and yf in (");
			for (String s : model.getFfyf()) {
				sql.append("'" + s + "',");
			}
			sql.append("'')");
		}

		sql.append(searchTjQx);

		String[] colArr = new String[] { "r", "yhkh", "yhmc", "xh", "xm",
				"yrdwmc", "xymc", "gwxzmc", "pkdj", "xlmc", "mxsgj", "gs", "je" };

		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] {}, colArr);

		return list;
	}

	/**
	 * 
	 * @描述:获得当前用户信息
	 * @作者：cq [工号：785]
	 * @日期：2013-12-30 上午10:05:08
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public HashMap<String, String> getUserInfo(User user) {

		StringBuilder sql = new StringBuilder();

		sql
				.append(" select * from yhb a left join zxbz_xxbmdm b on a.szbm = b.bmdm where yhm = ? ");

		return dao.getMapNotOut(sql.toString(), new String[] { user
				.getUserName() });
	}

	/**
	 * 
	 * @描述:济南工程职业技术学院勤工助学考核个性化导出
	 * @作者：夏夏[工号：1104]
	 * @日期：2014-7-1 上午08:35:07
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getCjffExpJn(QgzxCxtjForm model,
			User user) throws Exception{

		DAO dao = DAO.getInstance();
		// 生成高级查询相关条件、条件值
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		StringBuilder sql = new StringBuilder();

		// 权限控制
		String searchTjQx = "";
		QgzxGlyglService qgzxGlyglService = new QgzxGlyglService();
		// 如果不是勤工管理员
		if (!qgzxGlyglService.sfQggly(user.getUserName())) {
			searchTjQx += SearchService.getSearchTjByUser(user, "a", "xydm",
					"bjdm");
			// searchTjQx+=" and yrdwdm = '"+user.getUserDep()+"' ";
		}

		sql
				.append("select rownum r,a.* from (select xh || '!!@@!!' || a.gwdm || '!!@@!!' || a.ffny pkValue,a.* FROM view_xg_qgzx_cjffb  a order by ffny desc,gwmc,xh) a where 1=1 ");

		if (model.getNjs() != null && model.getNjs().length > 0) {
			sql.append(" and nj in (");
			for (String s : model.getNjs()) {
				sql.append("'" + s + "',");
			}
			sql.append("'')");
		}
		if (model.getXydms() != null && model.getXydms().length > 0) {
			sql.append(" and xydm in (");
			for (String s : model.getXydms()) {
				sql.append("'" + s + "',");
			}
			sql.append("'')");
		}
		if (model.getZydms() != null && model.getZydms().length > 0) {
			sql.append(" and zydm in (");
			for (String s : model.getZydms()) {
				sql.append("'" + s + "',");
			}
			sql.append("'')");
		}
		if (model.getBjdms() != null && model.getBjdms().length > 0) {
			sql.append(" and bjdm in (");
			for (String s : model.getBjdms()) {
				sql.append("'" + s + "',");
			}
			sql.append("'')");
		}
		if (model.getFfnd() != null && model.getFfnd().length > 0) {
			sql.append(" and nd in (");
			for (String s : model.getFfnd()) {
				sql.append("'" + s + "',");
			}
			sql.append("'')");
		}
		if (model.getFfyf() != null && model.getFfyf().length > 0) {
			sql.append(" and yf in (");
			for (String s : model.getFfyf()) {
				sql.append("'" + s + "',");
			}
			sql.append("'')");
		}

		sql.append(searchTjQx);
		sql.append(searchTj);

		String[] colArr = new String[] { "r", "xh", "xm", "xymc", "bjmc",
				"gwmc", "gs", "khdj", "bz" };

		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				inputV, colArr);
		for (HashMap<String, String> map : list) {
			map.put("xb", getXsxx(map.get("xh")));

		}
		return list;
	}

	/**
	 * 
	 * @描述:嘉兴职业技术学院勤工助学工作统计及补贴发放清单
	 * @作者：夏夏[工号：1104]
	 * @日期：2014-7-24 下午04:53:22
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getCjffExpJzy(QgzxCxtjForm model,
			User user) throws Exception {

		// 生成高级查询相关条件、条件值
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		StringBuilder sql = new StringBuilder();
		
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		sql.append("select rownum r,a.* from view_xg_qgzx_cjffb a where 1=1 ");

		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);

		String[] colArr = new String[] { "r","nd","yf", "gwmc", "ffny", "gs", "je", "xm",
				"yhkh" };

		return dao.getList(sql.toString(), inputV, colArr);

	}
	/**
	 * 
	 * @描述:获取总金额和总工时（嘉兴职业技术学院）
	 * @作者：夏夏[工号：1104]
	 * @日期：2014-7-25 上午10:49:53
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * void 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getJeAndGs(String ffny) throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append("select sum(je) zje,sum(gs) zgs from view_xg_qgzx_cjffb where ffny=? ");
		return dao.getMapNotOut(sql.toString(), new String[] {ffny});
	}

	/**
	 * 
	 * @描述:TODO根据学号获取学生性别
	 * @作者：夏夏[工号：1104]
	 * @日期：2014-7-1 上午09:27:01
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return String 返回类型
	 * @throws
	 */
	public String getXsxx(String xh) {
		StringBuffer sql = new StringBuffer();
		sql.append("select xb from view_xsxxb where xh=?");
		return dao.getOneRs(sql.toString(), new String[] { xh }, "xb");

	}
	
	
	/**
	 * 获得岗位列表
	 * 
	 * @return
	 */
	public List<HashMap<String, String>> getGwList() {
		String sql = " select distinct gwmc from view_xg_qgzx_gwxxb order by gwmc ";
		String[] outputValue = { "gwmc" };
		return dao.getList(sql, new String[] {}, outputValue);
	}
	
	/**
	 * 部门酬金发放查询
	 */
	public ArrayList<String[]> bmcjffCx(QgzxCxtjForm model) throws Exception {
		StringBuilder sql = new StringBuilder();
		String[] colList = new String[] {
					"yrdwmc",
					"ffzt" + model.getYf()
				};
		if (null == model.getYf() || "".equalsIgnoreCase(model.getYf())) {
			colList = new String[] { 
					"yrdwmc",
					"ffzt01","ffzt02","ffzt03","ffzt04","ffzt05","ffzt06","ffzt07","ffzt08","ffzt09","ffzt10","ffzt11","ffzt12"
				};
		}
		sql.append(" select a.* from xg_view_qgzx_bmcjffcx a where a.nd=? ");
		if (model.getBmdm() != null && !"".equalsIgnoreCase(model.getBmdm())) {
			sql.append(" and a.yrdwdm ='" + model.getBmdm() + "' ");
		}
		return CommonQueryDAO.commonQueryNotFy(sql.toString(), "",
				new String[] { model.getNd() }, colList, model);
	}
	
	/**
	 * 
	 *
	 */
	public List<HashMap<String, String>> getCjffSbbDclist(QgzxCxtjForm t,User user)
	throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t1", "xydm", "bjdm");
		StringBuilder sql = new StringBuilder();
		StringBuilder sql1 = new StringBuilder();
		sql.append(" select rownum r, a.*");
		sql.append(" from (select");
		sql.append(" decode(sjhm, null, qsdh,sjhm) qsdhsjhm,");
		sql.append(" a.*");
		sql.append(" FROM view_xg_qgzx_cjffb a");
		sql.append(" order by yrdwdm, ffny desc, gwmc) a");
		sql.append("  where 1 = 1");
		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		
		List<HashMap<String, String>> list =  dao.getListNotOut(sql.toString(), inputV);
		
		// TODO 自动生成方法存根
		return list;
	}
	
	public List<HashMap<String, String>> getFzlist(QgzxCxtjForm t,User user)
	throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t1", "xydm", "bjdm");
		StringBuilder sql = new StringBuilder();
		StringBuilder sql1 = new StringBuilder();
		sql.append("select distinct yrdwdm dm,yrdwmc mc from (");
		sql.append(" select  a.*");
		sql.append(" from (select");
		sql.append(" decode(sjhm, null, qsdh,sjhm) qsdhsjhm,");
		sql.append(" a.*");
		sql.append(" FROM view_xg_qgzx_cjffb a");
		sql.append(" order by yrdwdm, ffny desc, gwmc) a");
		sql.append("  where 1 = 1");
		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		sql.append(" )");
		
		List<HashMap<String, String>> list =  dao.getListNotOut(sql.toString(), inputV);
		
		// TODO 自动生成方法存根
		return list;
	}
	
	/**
	 * @描述：按月份导出个人费用发放表
	 * @作者：卓耐[工号:1391]
	 * @日期：2017年5月22日 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * List<HashMap<String,String>> 返回类型
	 */
	public List<HashMap<String, String>> getjffTjByYf(QgzxCxtjForm model) throws Exception {
		StringBuilder sql = new StringBuilder();
		List<String> params = new ArrayList<String>();
		params.add(model.getNd());
		params.add(model.getYf());
		sql.append("select t.*,rownum R from (select a.xh, b.xm,to_char(b.nj) nj,z.gwmc, b.xymc,b.zymc,b.bjmc,a.ffsj,a.yrbm, sum(je)je,a.gs,c.cjbz,d.bmmc bmmc ");
		sql.append("from xg_qgzx_cjff a ");
		sql.append("left join xg_qgzx_gwxxb z on a.gwdm = z.gwdm ");
		sql.append("left join view_xsbfxx b on a.xh = b.xh ");
		sql.append("left join ZXBZ_XXBMDM d on a.yrbm=d.bmdm ");
		sql.append("left join Xg_Qgzx_Csszb c on 1=1 ");
		sql.append("where substr(a.ffsj,0,4)=? and substr(a.ffsj,6)=? ");
		if(StringUtils.isNotNull(model.getBmdm())){
			sql.append(" and a.yrbm=? ");
			params.add(model.getBmdm());
		}
		if(StringUtils.isNotNull(model.getGwmc())){
			sql.append(" and z.gwmc like '%'||?||'%' ");
			params.add(model.getGwmc());
		}
		sql.append("group by a.xh, b.xm,b.nj, b.xymc,b.zymc,b.bjmc,a.ffsj,a.yrbm,z.gwmc,a.gs,c.cjbz,d.bmmc)t ");
		return dao.getListNotOut(sql.toString(), params.toArray(new String[]{}));
	}
	
	/**
	 * 
	 * @描述: 获取个人酬金发放导出数据(浙江交通职业技术学院)
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2018-4-19 下午04:11:09
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getGrcjffDcList(QgzxCxtjForm model){
		StringBuilder sql = new StringBuilder();
		List<String> paraList = new ArrayList<String>();
		sql.append(" select m.xh,m.xm,m.nj,m.xymc,m.yrbm,m.zymc,m.bjmc,m.ffsj,m.gwmc,");
		sql.append(" sum(m.je) e,sum(m.gs) gs,m.yhkh,m.gwxzmc");
		sql.append(" from (");
		sql.append(" select a.xh, b.xm,to_char(b.nj) nj,z.gwmc, b.xymc,b.zymc,b.bjmc,a.ffsj,a.yrbm, sum(je)je,sum(gs) gs,b.YHKH,z.gwxzmc");
		sql.append(" from xg_qgzx_cjff a");
		sql.append(" left join view_xg_qgzx_gwxxb z");
		sql.append(" on a.gwdm = z.gwdm");
		sql.append(" left join view_xsbfxx b");
		sql.append(" on a.xh = b.xh");
		sql.append(" where substr(ffsj,0,4)=? and substr(ffsj,6)=? ");
		paraList.add(model.getNd());
		paraList.add(model.getYf());
		if(!StringUtils.isNull(model.getBmdm())){
			sql.append("  and a.yrbm = ? ");
			paraList.add(model.getBmdm());
		}

		/**
		 * 岗位名称不为空
		 */
		if(StringUtils.isNotNull(model.getGwmc())){
			sql.append(" and z.gwmc like ? ");
			paraList.add("%"+model.getGwmc()+"%");
		}
		sql.append(" group by a.xh, b.xm,b.nj, b.xymc,b.zymc,b.bjmc,a.ffsj,a.yrbm,z.gwmc,b.YHKH,z.gwxzmc");
		sql.append(" )m ");
		if("12036".equals(Base.xxdm)){
			sql.append(" where gs > 0 ");
		}
		sql.append(" group by m.xh,m.xm,m.nj,m.xymc,m.zymc,m.bjmc ,m.yrbm,m.ffsj,m.gwmc,m.yhkh,m.gwxzmc ");
		return dao.getListNotOut(sql.toString(), paraList.toArray(new String[]{}));
	}
	
	/**
	 * 
	 * @描述:获取部门名称
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-7-13 下午03:20:24
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param bmdm
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public String getBmmc2(String bmdm){
		StringBuilder sql = new StringBuilder();
		sql.append(" select bmmc from zxbz_xxbmdm where bmdm = ?");
		sql.append(" union  ");
		sql.append(" select yrdwmc bmmc from view_xg_qgzx_yrdwdmb where bmdm = ?");
		return dao.getOneRs(sql.toString(), new String[]{bmdm,bmdm}, "bmmc");
	}

	@Override
	protected void setTableInfo() {

	}

	@Override
	public List<HashMap<String, String>> getPageList(Object o) throws Exception {
		return null;
	}

	@Override
	public List<HashMap<String, String>> getPageList(Object o, User user) throws Exception {
		return null;
	}
}
