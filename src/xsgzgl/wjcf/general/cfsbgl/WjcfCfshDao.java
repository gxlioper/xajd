package xsgzgl.wjcf.general.cfsbgl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.GetTime;
import xsgzgl.wjcf.general.WjcfGeneralForm;

/**
 * 
 * 
 * 类名称：WjcfCfsbAction 
 * 类描述：违纪处分处分审核Dao 
 * 创建人：xucy 
 * 创建时间：2012-6-20 下午01:18:00 
 * 修改备注：
 * 
 * @version
 * 
 */
public class WjcfCfshDao extends DAO {

	DAO dao = DAO.getInstance();

	/**
	 * 违纪处分 审核查询
	 * 
	 * @param model
	 * @param outputValue
	 * @return
	 * @throws Exception
	 */
	public ArrayList<String[]> getWjcfList(WjcfGeneralForm myForm,
			WjcfCfshModel model, User user) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		
		HashMap<String, String> higherUpSpgw = getHigherUpSpMap(myForm, user);

		HashMap<String, String> higherDownSpgw = getHigherDownSpMap(myForm,
				user);

		// 权限过滤
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a","xydm", "bjdm");

		// 高级查询条件
		String searchTj = SearchService.getSearchTj(myForm.getSearchModel());
		String[] input = SearchService.getTjInput(myForm.getSearchModel());
		
		String query = " where 1 = 1 ";
		query += searchTj;

		StringBuilder sql = new StringBuilder();
		sql.append("select rownum r,a.* ");
		sql.append("from (select a.*, (select m.xm from view_yhxx m where a.sbb = m.yhm) sbrxm, (case when b.xjshzt is not null then 'disable' else '' end) xjsh,");
		sql.append("(case when a.sftj ='1' then 'disable' else '' end) sfyjtj ");
		sql.append("from (select * from ( ");
		sql.append("select a.cfid,a.xtgwid,c.xh,(select c.mc from xg_xtwh_spgw c where a.xtgwid = c.id) gwmc,");
		sql.append("a.shzt,(case when a.shzt='wsh' then '未审核' when a.shzt ='tg' then '通过' when a.shzt='btg' then '不通过'");
		sql.append(" when a.shzt='th' then '退回' else '需重审' end) shztmc,");
		sql.append("a.shsj, a.shyj,a.shr, a.sftj, a.cfsj, a.cfwh,b.spyh,c.cflbdm, c.xn,c.xq,c.sbb,");
		sql.append("(select b.xqmc from xqdzb b where c.xq = b.xqdm) xqmc,m.xm,m.xydm,m.zydm,m.bjdm,m.nj,");
		sql.append("(select d.cflbmc from xg_wjcf_cflbdmb d where c.cflbdm = d.cflbdm) cflbmc,c.wjsj,c.cfyydm,");
		sql.append("(select d.cfyymc from xg_wjcf_cfyydmb d where c.cfyydm = d.cfyydm) cfyymc ");
		sql.append("from xg_wjcf_wjcfshb a left join xg_wjcf_wjcfsbb c on a.cfid = c.cfid ");
		sql.append("left join view_xsxxb m on c.xh = m.xh, (select * from xg_xtwh_spgwyh where spyh = ?) b ");
		sql.append("where a.xtgwid = b.spgw and c.cflbdm=?  and a.xtgwid=? ");
		sql.append(") a ");

		String higherUpgw = higherUpSpgw.get("spgw");

		if (!Base.isNull(higherUpgw)) {// 上级审核岗位
			sql.append(" where exists( select * from (select a.cflbdm,c.xtgwid,c.shzt,b.xh ");
			sql.append(" from xg_wjcf_cflbdmb a,xg_wjcf_wjcfsbb b,xg_wjcf_wjcfshb c where a.cflbdm=b.cflbdm and b.cfid=c.cfid ) x ");
			sql.append("   where x.cflbdm ='"
							+ myForm.getCflbdm()
							+ "' and xtgwid='"
							+ higherUpgw
							+ "' and a.cflbdm=x.cflbdm  and a.xh=x.xh and x.shzt='tg' )");
		}

		String higherDowngw = higherDownSpgw.get("spgw");
		sql.append(" )a left join ( ");
		sql.append("  select b.cfid,b.shzt xjshzt from xg_wjcf_wjcfshb b ");
		sql.append("  where b.xtgwid ='" + higherDowngw + "'");
		sql.append("  and (b.shzt = 'tg' or b.shzt = 'btg')) b  ");
		sql.append("  on a.cfid = b.cfid) a ");
		sql.append(query);

		String[] yhlbsjqx=new String[]{};
		String[] outputValue = new String[] { "cfid", "xn", "xqmc", "xh", "xm",
				"cflbmc", "cfyymc", "cfsj",  "sbrxm", "shztmc","sfyjtj","xjsh"};
		String[] inputValue=arrayAppendArray(new String[]{user.getUserName(),myForm.getCflbdm(),myForm.getSpgwId()},input);
		inputValue=arrayAppendArray(inputValue,yhlbsjqx);
		sql.append(searchTjByUser);
		return (ArrayList<String[]>)CommonQueryDAO
		.commonPageQuery(myForm.getPages(), sql.toString(), inputValue,outputValue);
		
	}

	/**
	 * 
	 * 根据下级岗位获取上级审批岗位
	 * 
	 * @author xucy
	 */
	public HashMap<String, String> getHigherUpSpMap(WjcfGeneralForm model,
			User user) {

		// 处分类别
		String cflbdm = model.getCflbdm();

		String spgw = model.getSpgwId();

		StringBuilder sql = new StringBuilder();

		sql.append(" select * from xg_xtwh_spbz a ");
		sql.append(" where splc = (select spl ");
		sql.append(" from xg_wjcf_cflbdmb ");
		sql.append(" where cflbdm = ? ) ");
		sql.append(" and exists(select * from xg_xtwh_spbz  b ");
		sql.append(" where splc = (select spl ");
		sql.append(" from xg_wjcf_cflbdmb ");
		sql.append(" where cflbdm = ? ) ");
		sql.append(" and b.spgw= ?  ");
		sql.append(" and to_number(a.xh)+1 =  b.xh ) ");

		return dao.getMapNotOut(sql.toString(), new String[] { cflbdm, cflbdm,spgw });
	}

	/**
	 * 
	 * 根据上级岗位获取下级审批岗位
	 * 
	 * @author xucy
	 */
	public HashMap<String, String> getHigherDownSpMap(WjcfGeneralForm model,
			User user) {

		// 处分类别
		String cflbdm = model.getCflbdm();

		String spgw = model.getSpgwId();

		StringBuilder sql = new StringBuilder();

		sql.append(" select * from xg_xtwh_spbz a ");
		sql.append(" where splc = (select spl ");
		sql.append(" from xg_wjcf_cflbdmb ");
		sql.append(" where cflbdm = ? ) ");
		sql.append(" and exists(select * from xg_xtwh_spbz  b ");
		sql.append(" where splc = (select spl ");
		sql.append(" from xg_wjcf_cflbdmb ");
		sql.append(" where cflbdm = ? ) ");
		sql.append(" and b.spgw= ?  ");
		sql.append(" and to_number(a.xh)-1 =  b.xh ) ");

		return dao.getMapNotOut(sql.toString(), new String[] { cflbdm, cflbdm,spgw });
	}

	/**
	 * 
	 * 根据下级审核状态判断是否可以修改
	 * 
	 * @author xucy
	 */
	public boolean sfKyxg(WjcfGeneralForm model, User user) {
		boolean flag = false;
		StringBuilder sql = new StringBuilder();

		// 处分类别
		String cflbdm = model.getCflbdm();
		String spgw = model.getSpgwId();
		String cfId = model.getCfId();

		sql.append("select a.shzt from xg_wjcf_wjcfshb a,");
		sql.append(" (select * from xg_xtwh_spbz a ");
		sql.append(" where splc = (select spl ");
		sql.append(" from xg_wjcf_cflbdmb ");
		sql.append(" where cflbdm = ? ) ");
		sql.append(" and exists(select * from xg_xtwh_spbz  b ");
		sql.append(" where splc = (select spl ");
		sql.append(" from xg_wjcf_cflbdmb ");
		sql.append(" where cflbdm = ? ) ");
		sql.append(" and b.spgw= ?  ");
		sql.append(" and to_number(a.xh)-1 =  b.xh )) b");
		sql.append(" where a.xtgwid = b.spgw and cfid=?");
		HashMap<String, String> downSpgw = dao.getMapNotOut(sql.toString(),
				new String[] { cflbdm, cflbdm, spgw, cfId });
		// 如果没有下一级或者下一级的审核状态为未审核表示 可以修改
		if (downSpgw.size() == 0 || "th".equals(downSpgw.get("shzt"))
				|| "wsh".equals(downSpgw.get("shzt"))) {
			flag = true;
		}
		return flag;
	}

	/**
	 * 
	 * 获取最高级别审批岗位
	 * 
	 * @author xucy
	 */
	public HashMap<String, String> getMaxSpgw(WjcfGeneralForm model, User user)
			throws Exception {

		StringBuilder sql = new StringBuilder();

		sql.append(" select a.spgw from xg_xtwh_spbz a, ");
		sql.append(" (select splc,max(xh) xh from xg_xtwh_spbz  ");
		sql.append(" where splc = (select spl from xg_wjcf_cflbdmb where cflbdm = ? ) ");
		sql.append(" group by splc) b  ");
		sql.append(" where a.xh = b.xh and a.splc = b.splc ");

		return dao.getMapNotOut(sql.toString(), new String[] { model
				.getCflbdm() });
	}

	/**
	 * 查询一条处分申报数据
	 * 
	 * @param form
	 * @return
	 * @throws Exception
	 * @throws Exception
	 */
	public HashMap<String, String> getCfsb(String cfid) throws Exception {
		String sql = "select a.cfid,a.xh,a.xn,a.xq,(select b.xqmc from xqdzb b where a.xq = b.xqdm) xqmc,"
				+ "a.cflbdm,(select c.cflbmc from XG_WJCF_CFLBDMB c where a.cflbdm = c.cflbdm ) cflbmc, "
				+ "a.cfyydm,(select d.cfyymc from XG_WJCF_CFYYDMB d where a.cfyydm = d.cfyydm ) cfyymc,"
				+ "a.wjsj,a.sbb,a.sbsj,a.wjssjg,a.bz,a.sbjg from xg_wjcf_wjcfsbb a where a.cfid = ?";
		String[] inputValue = new String[] { cfid };
		return getMapNotOut(sql, inputValue);
	}

	/**
	 * 查询一条处分审核数据
	 * 
	 * @param form
	 * @return
	 * @throws Exception
	 * @throws Exception
	 */
	public HashMap<String, String> getOnesCfsh(String cfid, String spgw)
			throws Exception {
		String sql = "select a.cfid,a.xtgwid,a.shzt,a.shsj,a.cfwh,a.cfsj,a.shyj,a.shr,a.sftj from xg_wjcf_wjcfshb a where a.cfid = ? and a.xtgwid=?";
		String[] inputValue = new String[] { cfid, spgw };
		return getMapNotOut(sql, inputValue);
	}

	/**
	 * 查询学生已受违纪处分列表
	 * 
	 * @param form
	 * @return
	 * @throws Exception
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getYscfqk(String xh) {
		String sql = "select t.xn,t.xq,(select xqmc from xqdzb m where m.xqdm = t.xq) xqmc,t.cflbmc,t.cfyymc,t.cfsj,t.cfwh from xg_wjcf_wjcfb t where t.xh=?";
		return dao.getListNotOut(sql.toString(), new String[] {xh});
	}

	/**
	 * 查询处分审核信息
	 * 
	 * @param form
	 * @return
	 * @throws Exception
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getCfsh(String cfid) {
		String sql = "select a.shzt, (case when a.shzt='wsh' then'未审核' when a.shzt='th' then '退回' when  a.shzt='tg' then '审核通过' "
				+ "when a.shzt='xcs' then '需重审' else '审核不通过' end) shztmc,a.shsj,a.shyj,a.shr,a.sftj,a.cfsj,a.cfwh,(select WM_CONCAT(b.spyh) "
				+ "from xg_xtwh_spgwyh b where b.spgw=a.xtgwid) spyh "
				+ "from xg_wjcf_wjcfshb a where a.cfid=?";
		return dao.getListNotOut(sql.toString(), new String[] { cfid });
	}

	/**
	 * 查询其他级别处分审核信息
	 * 
	 * @param form
	 * @return
	 * @throws Exception
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getCfshxx(String cfid, String spgw) {
		String sql = "select t.cfid,t.xtgwid,t.shzt,t.shsj,t.shyj,t.shr,(select m.xm from yhb m where t.shr = m.yhm) shrxm,t.cfsj,t.cfwh,"
				+ "(select a.mc from xg_xtwh_spgw a where a.id = t.xtgwid) gwmc from xg_wjcf_wjcfshb t where t.cfid=? and t.shzt<>?";
		return dao.getListNotOut(sql.toString(), new String[] { cfid ,"wsh"});
	}

	/**
	 * 查询处分类别
	 * 
	 * @param form
	 * @return
	 * @throws Exception
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getCflbList(WjcfCfshModel model,
			User user) {
		StringBuilder sql = new StringBuilder();
		sql.append("select distinct c.cflbdm,");
		sql.append("(select d.cflbmc from xg_wjcf_cflbdmb d where c.cflbdm = d.cflbdm) cflbmc ");
		sql.append(" from xg_wjcf_wjcfshb a , xg_wjcf_wjcfsbb c ,");
		sql.append("(select * from xg_xtwh_spgwyh where spyh = ?) b where a.cfid = c.cfid and a.xtgwid = b.spgw");
		return dao.getList(sql.toString(), new String[] { user.getUserName() },
				new String[] { "cflbdm", "cflbmc" });
	}

	/**
	 * 查询处分审批岗位
	 * 
	 * @param form
	 * @return
	 * @throws Exception
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getSpgwList(String cflbdm, User user) {
		StringBuilder sql = new StringBuilder();
		sql.append("select a.spgw,a.gwmc from (select b.spgw,(select c.mc from xg_xtwh_spgw c where b.spgw = c.id) gwmc, b.xh ");
		sql.append(" from xg_wjcf_cflbdmb a, xg_xtwh_spbz b ");
		sql.append(" where a.cflbdm = ? ");
		sql.append("  and a.spl = b.splc) a ,(select * from xg_xtwh_spgwyh where spyh = ?) c ");
		sql.append("  where a.spgw = c.spgw order by a.xh ");
		return dao.getList(sql.toString(), new String[] { cflbdm,user.getUserName() }, new String[] { "spgw", "gwmc" });
	}


	/**
	 * 修改审核状态
	 * 
	 * @param form
	 * @return
	 * @throws Exception
	 * @throws Exception
	 */
	public boolean plXgshzt(String[] params, WjcfGeneralForm form, User user)
			throws Exception {
		StringBuilder sql = new StringBuilder();
		String shyj = form.getShyj();
		String cfsj = form.getCfsj();
		String shzt = form.getShzt();
		String cfwh = form.getCfwh();
		String xtgwId = form.getSpgwId();

		ArrayList<String> inputV = new ArrayList<String>();
		sql.append("update xg_wjcf_wjcfshb");
		sql.append(" set shyj =?,shzt=?,shsj =?,shr=?,cfsj=?,cfwh=?");
		sql.append(" where xtgwid=? and cfid in(");
		inputV.add(shyj);
		inputV.add(shzt);
		inputV.add(GetTime.getNowTime2());
		inputV.add(user.getUserName());
		inputV.add(cfsj);
		inputV.add(cfwh);
		inputV.add(xtgwId);
		for (int i = 0; i < params.length; i++) {
			if (i != 0) {
				sql.append(",");
			}
			sql.append("?");
			inputV.add(params[i]);
		}
		sql.append(" ) ");
		return dao.runUpdate(sql.toString(), inputV);
	}

	/**
	 * 修改上一级审核状态
	 * 
	 * @param form
	 * @return
	 * @throws Exception
	 * @throws Exception
	 */
	public boolean plXgUpshzt(String[] params, WjcfGeneralForm form, User user)
			throws Exception {
		StringBuilder sql = new StringBuilder();

		HashMap<String, String> higherUpSpgw = getHigherUpSpMap(form, user);// 获取下级岗位级别
		ArrayList<String> inputV = new ArrayList<String>();
		sql.append("update xg_wjcf_wjcfshb");
		sql.append(" set shzt=?");
		sql.append(" where xtgwid=? and cfid in(");
		inputV.add("xcs");
		inputV.add(higherUpSpgw.get("spgw"));
		for (int i = 0; i < params.length; i++) {
			if (i != 0) {
				sql.append(",");
			}
			sql.append("?");
			inputV.add(params[i]);
		}
		sql.append(" ) ");
		return dao.runUpdate(sql.toString(), inputV);
	}

	/**
	 * 修改下一级审核状态为退回的
	 * 
	 * @param form
	 * @return
	 * @throws Exception
	 * @throws Exception
	 */
	public boolean plXgDownshzt(String[] params, WjcfGeneralForm form, User user)
			throws Exception {
		StringBuilder sql = new StringBuilder();
		// 获取下级岗位级别
		HashMap<String, String> higherDownSpgw = getHigherDownSpMap(form, user);
		ArrayList<String> inputV = new ArrayList<String>();
		sql.append("update xg_wjcf_wjcfshb");
		sql.append(" set shzt=?");
		sql.append(" where xtgwid=?  and shzt='th' and cfid in(");
		inputV.add("wsh");
		inputV.add(higherDownSpgw.get("spgw"));
		for (int i = 0; i < params.length; i++) {
			if (i != 0) {
				sql.append(",");
			}
			sql.append("?");
			inputV.add(params[i]);
		}
		sql.append(" ) ");
		return dao.runUpdate(sql.toString(), inputV);
	}

	/**
	 * 修改申报表审核结果
	 * 
	 * @param form
	 * @return
	 * @throws Exception
	 * @throws Exception
	 */
	public boolean plXgshjg(String[] params, WjcfGeneralForm form, User user)
			throws Exception {

		HashMap<String, String> maxSpgwmap = getMaxSpgw(form, user);

		StringBuilder sql = new StringBuilder();

		ArrayList<String> inputV = new ArrayList<String>();
		sql.append("update xg_wjcf_wjcfsbb ");
		sql.append(" set sbjg =?");
		sql.append(" where cfid in(");
		// 如果审核状态为通过，并且岗位级别为最高，则申报状态为通过,否则为审核中
		if (form.getSpgwId().equals(maxSpgwmap.get("spgw"))) {
			if ("tg".equals(form.getShzt())) {
				inputV.add("tg");
			} else if ("btg".equals(form.getShzt())) {
				inputV.add("btg");
			} else {
				inputV.add("shz");
			}
		} else {
			inputV.add("shz");
		}

		for (int i = 0; i < params.length; i++) {
			if (i != 0) {
				sql.append(",");
			}
			sql.append("?");
			inputV.add(params[i]);
		}
		sql.append(" ) ");

		return dao.runUpdate(sql.toString(), inputV);
	}

	/**
	 * 统计查询
	 * 
	 * @param model
	 * @param outputValue
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> tongjiList() {
		StringBuilder sql = new StringBuilder();
		sql.append("select a.cflbmc ,");
		sql.append("sum(decode(shzt, 'wsh', gs, 0)) wshsl,");
		sql.append("sum(decode(shzt, 'tg', gs, 0)) tgsl,");
		sql.append("sum(decode(shzt, 'btg', gs, 0)) btgsl,");
		sql.append("sum(decode(shzt, 'th', gs, 0)) thsl ");
		sql.append(" from (select a.cflbdm,");
		sql.append(" (select b.cflbmc from xg_wjcf_cflbdmb b where a.cflbdm = b.cflbdm) cflbmc, a.shzt, a.gs");
		sql.append(" from (select b.cflbdm, a.shzt, count(a.shzt) gs  from xg_wjcf_wjcfshb a ");
		sql.append(" left join xg_wjcf_wjcfsbb b on a.cfid = b.cfid left join xg_wjcf_cflbdmb c  on b.cflbdm=c.cflbdm where exists ");
		sql.append("(select 1 from(select a.splc,a.spgw ");
		sql.append(" from xg_xtwh_spbz a,(select splc, max(xh) xh  from xg_xtwh_spbz where splc in ");
		sql.append(" (select spl from xg_wjcf_cflbdmb) group by splc) b ");
		sql.append(" where a.xh = b.xh and a.splc = b.splc) d where a.xtgwid=d.spgw and c.spl=d.splc) and a.sftj='0'" );
		sql.append(	" group by b.cflbdm, a.shzt) a) a group by a.cflbmc ");
		return dao.getListNotOut(sql.toString(), new String[] {});
	}

	/**
	 * 提交审核
	 * 
	 * @param model
	 * @param outputValue
	 * @return
	 * @throws Exception
	 */
	public boolean tjSh() throws Exception {
		StringBuilder sql = new StringBuilder();
		ArrayList<String> inputV = new ArrayList<String>();
		sql.append("insert into xg_wjcf_wjcfb(cfid,xh,xn,xq,cflbmc,cfyymc,cfsj,cfwh,wjsj,sbb,sbsj,wjssjg,bz,fj,fjmc)");
		sql.append(" select b.cfid,b.xh,b.xn,b.xq,");
		sql.append("a.cflbmc,");
		sql.append("(select n.cfyymc from xg_wjcf_cfyydmb n where b.cfyydm = n.cfyydm) cfyymc,");
		sql.append("a.cfsj,a.cfwh,b.wjsj, b.sbb,b.sbsj,b.wjssjg,b.bz,b.fj,b.fjmc ");
		sql.append(" from (select a.cfid,a.shsj,b.xh,b.xn,b.xq,c.cflbmc,a.cfsj,a.cfwh,b.sbb,b.sbsj,b.wjssjg,b.bz,b.fj,b.fjmc from xg_wjcf_wjcfshb a" );
		sql.append(" left join xg_wjcf_wjcfsbb b on a.cfid = b.cfid  left join xg_wjcf_cflbdmb c  on b.cflbdm = c.cflbdm where exists (select 1 ");
		sql.append("  from (select a.splc, a.spgw from xg_xtwh_spbz a,(select splc, max(xh) xh ");
		sql.append(" from xg_xtwh_spbz where splc in (select spl from xg_wjcf_cflbdmb)group by splc) b ");
		sql.append(" where a.xh = b.xh and a.splc = b.splc) d  where a.xtgwid = d.spgw  and c.spl = d.splc) and (a.shzt = 'tg') and a.sftj = '0') a ");
		sql.append(" left join xg_wjcf_wjcfsbb b on a.cfid = b.cfid");
		return dao.runUpdate(sql.toString(), inputV);
	}
	
	/**
	 * 提交审核(用于最高级别审核后直接提交)
	 * 
	 * @param model
	 * @param outputValue
	 * @return
	 * @throws Exception
	 */
	public boolean tjShsj(String[] params) throws Exception {
		StringBuilder sql = new StringBuilder();
		ArrayList<String> inputV = new ArrayList<String>();
		sql.append("insert into xg_wjcf_wjcfb(cfid,xh,xn,xq,cflbmc,cfyymc,cfsj,cfwh,wjsj,sbb,sbsj,wjssjg,bz,fj,fjmc)");
		sql.append(" select b.cfid,b.xh,b.xn,b.xq,");
		sql.append("a.cflbmc,");
		sql.append("(select n.cfyymc from xg_wjcf_cfyydmb n where b.cfyydm = n.cfyydm) cfyymc,");
		sql.append("a.cfsj,a.cfwh,b.wjsj, b.sbb,b.sbsj,b.wjssjg,b.bz,b.fj,b.fjmc ");
		sql.append(" from (select a.cfid,a.shsj,b.xh,b.xn,b.xq,c.cflbmc,a.cfsj,a.cfwh,b.sbb,b.sbsj,b.wjssjg,b.bz,b.fj,b.fjmc from xg_wjcf_wjcfshb a" );
		sql.append(" left join xg_wjcf_wjcfsbb b on a.cfid = b.cfid  left join xg_wjcf_cflbdmb c  on b.cflbdm = c.cflbdm where exists (select 1 ");
		sql.append("  from (select a.splc, a.spgw from xg_xtwh_spbz a,(select splc, max(xh) xh ");
		sql.append(" from xg_xtwh_spbz where splc in (select spl from xg_wjcf_cflbdmb)group by splc) b ");
		sql.append(" where a.xh = b.xh and a.splc = b.splc) d  where a.xtgwid = d.spgw  and c.spl = d.splc) and (a.shzt = 'tg') and a.sftj = '0') a ");
		sql.append(" left join xg_wjcf_wjcfsbb b on a.cfid = b.cfid where a.cfid in (");
		for (int i = 0; i < params.length; i++) {
			if (i != 0) {
				sql.append(",");
			}
			sql.append("?");
			inputV.add(params[i]);
		}
		sql.append(" ) ");
		return dao.runUpdate(sql.toString(), inputV);
	}

	/**
	 * 修改是否提交状态
	 * 
	 * @param model
	 * @param outputValue
	 * @return
	 * @throws Exception
	 */
	public boolean xgSftj() throws Exception {
		StringBuilder sql = new StringBuilder();
		ArrayList<String> inputV = new ArrayList<String>();
		sql.append("update xg_wjcf_wjcfshb t set t.sftj= '1' where cfid in");
		sql.append(" (select a.cfid from xg_wjcf_wjcfshb a ");
		sql.append("left join xg_wjcf_wjcfsbb b on a.cfid = b.cfid  left join xg_wjcf_cflbdmb c  on b.cflbdm = c.cflbdm ");
		sql.append(" where exists (select 1 from (select a.splc, a.spgw from xg_xtwh_spbz a, ");
		sql.append(" (select splc, max(xh) xh from xg_xtwh_spbz ");
		sql.append(" where splc in (select spl from xg_wjcf_cflbdmb) group by splc) b");
		sql.append(" where a.xh = b.xh and a.splc = b.splc) d where a.xtgwid = d.spgw  and c.spl = d.splc) and a.shzt = 'tg' and a.sftj = '0') ");
		return dao.runUpdate(sql.toString(), inputV);
	}
	
	/**
	 * 修改是否提交状态(用于最高级别审核后直接提交)
	 * 
	 * @param model
	 * @param outputValue
	 * @return
	 * @throws Exception
	 */
	public boolean xgTjzt(String[] params) throws Exception {
		StringBuilder sql = new StringBuilder();
		ArrayList<String> inputV = new ArrayList<String>();
		sql.append("update xg_wjcf_wjcfshb t set t.sftj= '1' where cfid in (");
		for (int i = 0; i < params.length; i++) {
			if (i != 0) {
				sql.append(",");
			}
			sql.append("?");
			inputV.add(params[i]);
		}
		sql.append(" ) ");
		return dao.runUpdate(sql.toString(), inputV);
	}
	
	/**
	 * 判断是否最高级用户
	 * 
	 * @param model
	 * @param outputValue
	 * @return
	 * @throws Exception
	 */
	public boolean isZgjyh(User user) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("select a.spgw,a.gwmc from ( select a.spgw, a.gwmc, c.spyh ");
		sql.append(" from ( select b.spgw,(select c.mc from xg_xtwh_spgw c where b.spgw = c.id) gwmc ");
		sql.append(" from xg_wjcf_cflbdmb a ,xg_xtwh_spbz b where a.spl = b.splc group by b.spgw ) a,");
		sql.append(" (select * from xg_xtwh_spgwyh where spyh = ?) c where a.spgw = c.spgw  and a.spgw in ");
		sql.append(" ( select a.spgw  from xg_xtwh_spbz a,(select splc, max(xh) xh  from xg_xtwh_spbz where splc in  ");
		sql.append(" (select spl from xg_wjcf_cflbdmb a,xg_wjcf_wjcfsbb b where a.cflbdm = b.cflbdm ) group by splc) b ");
		sql.append(" where a.xh = b.xh and a.splc = b.splc )) a ");
		List<HashMap<String, String>> map =  dao.getListNotOut(sql.toString(), new String[] {user.getUserName()});
		if(null!=map&&map.size()>0){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 工具类   用于合并数组
	 * @param beginArray
	 * @param endArray
	 * @return
	 */
	public String[] arrayAppendArray(String[] beginArray,String[] endArray){
		if(beginArray==null || endArray==null){
			return null;
		}
		String[] newArray=new String[(beginArray.length+endArray.length)];
		int p=0;
		for (int i = 0; i < beginArray.length; i++) {
			newArray[p]=beginArray[i];
			p++;
		}
		for (int i = 0; i < endArray.length; i++) {
			newArray[p]=endArray[i];
			p++;
		}
		return newArray;
	}
}
