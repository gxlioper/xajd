package xsgzgl.wjcf.general.cfjcgl;

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

public class WjcfCfjcshDao extends DAO {
	DAO dao = DAO.getInstance();
	
	/**
	 * 违纪处分 解除审核查询
	 * 
	 * @param model
	 * @param outputValue
	 * @return
	 * @throws Exception
	 */
	public ArrayList<String[]> getCfjcshList(WjcfGeneralForm myForm,
			WjcfCfjcshModel model, User user) throws IllegalArgumentException,
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
		sql.append("from (select a.*, (case  when b.xjshzt is not null then 'disable' else '' end) xjsh,");
		sql.append("(case when a.sftj ='1' then 'disable' else '' end) sfyjtj ");
		sql.append("from (select a.* from ( ");
		sql.append("select a.cfid,d.xn,d.xq,(select b.xqmc from xqdzb b where d.xq = b.xqdm) xqmc,(select c.mc from xg_xtwh_spgw c where a.xtgwid = c.id) gwmc,");
		sql.append("d.xh, m.xm,m.nj,m.xydm,m.zydm,m.bjdm, d.cflbmc, d.cfyymc, a.jcwh, a.jcsj,");
		sql.append("a.shzt,(case when a.shzt='wsh' then '未审核' when a.shzt ='tg' then '通过' when a.shzt='btg' then '不通过'");
		sql.append(" when a.shzt='th' then '退回' else '需重审' end) shztmc, a.sftj ");
		sql.append("from xg_wjcf_wjcfjcshb a left join xg_wjcf_wjcfjcsqb c on a.cfid = c.cfid ");
		sql.append("left join xg_wjcf_wjcfb d  on a.cfid = d.cfid left join view_xsxxb m on d.xh = m.xh, (select * from xg_xtwh_spgwyh where spyh = ?) b ");
		sql.append("where a.xtgwid = b.spgw  and a.xtgwid=? ");
		sql.append(") a ");

		String higherUpgw = higherUpSpgw.get("spgw");

		if (!Base.isNull(higherUpgw)) {// 上级审核岗位
			sql.append(" where exists( select 1 from (select c.xtgwid,c.shzt ,c.cfid");
			sql.append(" from xg_wjcf_wjcfjcsqb b,xg_wjcf_wjcfjcshb c where b.cfid=c.cfid ) x ");
			sql.append(" where xtgwid='"+higherUpgw+"' and x.shzt='tg' and x.cfid=a.cfid )");
		}

		String higherDowngw = higherDownSpgw.get("spgw");
		sql.append(" )a left join ( ");
		sql.append("  select b.cfid,b.shzt xjshzt from xg_wjcf_wjcfjcshb b ");
		sql.append("  where b.xtgwid ='" + higherDowngw + "'");
		sql.append("  and (b.shzt = 'tg' or b.shzt = 'btg')) b  ");
		sql.append("  on a.cfid = b.cfid) a ");
		sql.append(query);

		String[] yhlbsjqx=new String[]{};
		String[] outputValue = new String[] { "cfid", "xn", "xqmc", "xh", "xm",
				"cflbmc", "cfyymc", "jcwh", "jcsj", "shztmc", "sfyjtj","xjsh"};
		String[] inputValue=arrayAppendArray(new String[]{user.getUserName(),myForm.getSpgwId()},input);
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

		String spgw = model.getSpgwId();

		StringBuilder sql = new StringBuilder();

		sql.append("select * from xg_xtwh_spbz a ");
		sql.append(" where splc = (select b.splc ");
		sql.append("  from xg_wjcf_ssjcsplb a left join xg_xtwh_spbz b on a.jcspl = b.splc");
		sql.append(" where spgw =? ) ");
		sql.append(" and exists(select * from xg_xtwh_spbz  b ");
		sql.append(" where splc = (select b.splc ");
		sql.append(" from xg_wjcf_ssjcsplb a left join xg_xtwh_spbz b on a.jcspl = b.splc");
		sql.append(" where spgw = ? ) ");
		sql.append(" and b.spgw = ? ");
		sql.append(" and to_number(a.xh)+1 =  b.xh ) ");

		return dao.getMapNotOut(sql.toString(), new String[] { spgw,spgw,spgw });
	}

	/**
	 * 
	 * 根据上级岗位获取下级审批岗位
	 * 
	 * @author xucy
	 */
	public HashMap<String, String> getHigherDownSpMap(WjcfGeneralForm model,
			User user) {

		String spgw = model.getSpgwId();

		StringBuilder sql = new StringBuilder();

		sql.append("select * from xg_xtwh_spbz a ");
		sql.append(" where splc = (select b.splc ");
		sql.append("  from xg_wjcf_ssjcsplb a left join xg_xtwh_spbz b on a.jcspl = b.splc");
		sql.append(" where spgw =? ) ");
		sql.append(" and exists(select * from xg_xtwh_spbz  b ");
		sql.append(" where splc = (select b.splc ");
		sql.append(" from xg_wjcf_ssjcsplb a left join xg_xtwh_spbz b on a.jcspl = b.splc");
		sql.append(" where spgw = ? ) ");
		sql.append(" and b.spgw = ? ");
		sql.append(" and to_number(a.xh)-1 =  b.xh ) ");

		return dao.getMapNotOut(sql.toString(), new String[] { spgw,spgw,spgw });
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
		sql.append(" where splc = (select b.splc from xg_wjcf_ssjcsplb a  ");
		sql.append(" left join xg_xtwh_spbz b on a.jcspl = b.splc where spgw =?) group by splc) b  ");
		sql.append(" where a.xh = b.xh and a.splc = b.splc ");

		return dao.getMapNotOut(sql.toString(), new String[] { model.getSpgwId() });
	}

	/**
	 * 查询一条处分信息
	 * 
	 * @param form
	 * @return
	 * @throws Exception
	 * @throws Exception
	 */
	public HashMap<String, String> getCfxx(String cfid) throws Exception {
		String sql = "select t.*,(select b.xqmc from xqdzb b where t.xq = b.xqdm) xqmc,b.fjmc ssfjmc,b.fj ssfj from xg_wjcf_wjcfb t left join xg_wjcf_wjcfssb b on t.cfid = b.cfid where t.cfid= ?";
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
	public HashMap<String, String> getOnesCfjcsh(String cfid, String spgw)
			throws Exception {
		String sql = "select a.* from xg_wjcf_wjcfjcshb a where a.cfid = ? and a.xtgwid=?";
		String[] inputValue = new String[] { cfid, spgw };
		return getMapNotOut(sql, inputValue);
	}

	/**
	 * 查询一条处分申述申请数据
	 * 
	 * @param form
	 * @return
	 * @throws Exception
	 * @throws Exception
	 */
	public HashMap<String, String> getOnesCfss(String cfid)
			throws Exception {
		String sql = "select a.* from xg_wjcf_wjcfssb a where a.cfid = ? ";
		String[] inputValue = new String[] { cfid };
		return getMapNotOut(sql, inputValue);
	}
	
	/**
	 * 查询一条处分解除申请数据
	 * 
	 * @param form
	 * @return
	 * @throws Exception
	 * @throws Exception
	 */
	public HashMap<String, String> getOnesCfjc(String cfid)
			throws Exception {
		String sql = "select a.* from xg_wjcf_wjcfjcsqb a where a.cfid = ? ";
		String[] inputValue = new String[] { cfid };
		return getMapNotOut(sql, inputValue);
	}

	/**
	 * 查询其他级别处分审核信息
	 * 
	 * @param form
	 * @return
	 * @throws Exception
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getCfjcshxx(String cfid, String spgw) {
		String sql = "select t.cfid,t.xtgwid,t.shzt,t.shsj,t.shyj,t.shr,(select m.xm from yhb m where t.shr = m.yhm) shrxm,t.jcsj,t.jcwh,"
				+ "(select a.mc from xg_xtwh_spgw a where a.id = t.xtgwid) gwmc from xg_wjcf_wjcfjcshb t where t.cfid=? and t.xtgwid <> ? and t.shzt<>?";
		return dao.getListNotOut(sql.toString(), new String[] { cfid, spgw ,"wsh"});
	}
	
	/**
	 * 查询其他级别处分审核信息
	 * 
	 * @param form
	 * @return
	 * @throws Exception
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getCfjcshxxList(String cfid) {
		String sql = "select t.cfid,t.xtgwid,t.shzt,t.shsj,t.shyj,t.shr,(select m.xm from yhb m where t.shr = m.yhm) shrxm,t.jcsj,t.jcwh,"
				+ "(select a.mc from xg_xtwh_spgw a where a.id = t.xtgwid) gwmc from xg_wjcf_wjcfjcshb t where t.cfid=? and t.shzt<>?";
		return dao.getListNotOut(sql.toString(), new String[] { cfid ,"wsh"});
	}


	/**
	 * 查询审批岗位
	 * 
	 * @param form
	 * @return
	 * @throws Exception
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getSpgwList( User user) {
		StringBuilder sql = new StringBuilder();
		sql.append("select * from  (select b.spgw, (select c.mc from xg_xtwh_spgw c where b.spgw = c.id) gwmc ");
		sql.append(" from xg_wjcf_ssjcsplb a  left join xg_xtwh_spbz b ");
		sql.append(" on a.jcspl = b.splc) a, ");
		sql.append(" (select * from xg_xtwh_spgwyh where spyh =?) c  ");
		sql.append("  where a.spgw = c.spgw");
		return dao.getList(sql.toString(), new String[] {user.getUserName() }, new String[] { "spgw", "gwmc" });
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
		String jcsj = form.getJcsj();
		String shzt = form.getShzt();
		String jcwh = form.getJcwh();
		String xtgwId = form.getSpgwId();

		ArrayList<String> inputV = new ArrayList<String>();
		sql.append("update xg_wjcf_wjcfjcshb");
		sql.append(" set shyj =?,shzt=?,shsj =?,shr=?,jcsj=?,jcwh=?");
		sql.append(" where xtgwid=? and cfid in(");
		inputV.add(shyj);
		inputV.add(shzt);
		inputV.add(GetTime.getNowTime2());
		inputV.add(user.getUserName());
		inputV.add(jcsj);
		inputV.add(jcwh);
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
		sql.append("update xg_wjcf_wjcfjcshb");
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
		sql.append("update xg_wjcf_wjcfjcshb");
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
	 * 修改申请表审核结果
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
		sql.append("update xg_wjcf_wjcfjcsqb ");
		sql.append(" set sqjg =?");
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
		sql.append("select ");
		sql.append("sum(decode(shzt, 'wsh', gs, 0)) wshsl,");
		sql.append("sum(decode(shzt, 'tg', gs, 0)) tgsl,");
		sql.append("sum(decode(shzt, 'btg', gs, 0)) btgsl,");
		sql.append("sum(decode(shzt, 'th', gs, 0)) thsl ");
		sql.append(" from (select ");
		sql.append(" a.shzt, a.gs");
		sql.append(" from (select a.shzt, count(a.shzt) gs  from xg_wjcf_wjcfjcshb a ");
		sql.append(" left join xg_wjcf_wjcfjcsqb b on a.cfid = b.cfid where a.xtgwid in ");
		sql.append(" (select a.spgw from xg_xtwh_spbz a,(select splc,max(xh) xh from xg_xtwh_spbz where splc in ");
		sql.append(" (select b.splc from xg_wjcf_ssjcsplb a  left join xg_xtwh_spbz b on a.jcspl = b.splc ) group by splc) b ");
		sql.append(" where a.xh = b.xh and a.splc = b.splc) and a.sftj='0' group by a.shzt) a) a ");
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
		boolean flag = true;
		StringBuilder sql = new StringBuilder();
		StringBuilder sql1 = new StringBuilder();
		sql.append("select a.cfid,a.shsj,a.shr,a.jcwh,a.jcsj from (select * from xg_wjcf_wjcfjcshb t ");
		sql.append("where t.xtgwid in (select a.spgw from xg_xtwh_spbz a,");
		sql.append("(select splc, max(xh) xh from xg_xtwh_spbz where splc in (select b.splc");
		sql.append(" from xg_wjcf_ssjcsplb a left join xg_xtwh_spbz b on a.jcspl = b.splc)");
		sql.append(" group by splc) b where a.xh = b.xh and a.splc = b.splc)) a ");
		sql.append(" where (a.shzt = 'tg' or a.shzt = 'btg') and a.sftj = '0'");
		List<HashMap<String, String>> jcsqList = dao.getListNotOut(sql.toString(), new String[] {});
		
		if(null!=jcsqList&&jcsqList.size()>0){
			sql1.append("update xg_wjcf_wjcfb set jcwh=?,jcsj=? where cfid=?");
			List<String[]> params=new ArrayList<String[]>();
			for(int i=0;i<jcsqList.size();i++){
				HashMap<String,String> map = jcsqList.get(i);
				 String[] param=null;
				 param=new String[]{map.get("jcwh"),map.get("jcsj"),map.get("cfid")};
				 params.add(param);
			}
			 int[] rows = dao.runBatch(sql1.toString(), params);
			 flag = dao.checkBatchResult(rows); 
		}
		
		return flag;
	}
	
	/**
	 * 提交审核(用于最高级别审核后直接提交)
	 * 
	 * @param model
	 * @param outputValue
	 * @return
	 * @throws Exception
	 */
	public boolean tjShsj(String[] params,WjcfGeneralForm form) throws Exception {
		StringBuilder sql = new StringBuilder();
		ArrayList<String> inputV = new ArrayList<String>();
		sql.append("update xg_wjcf_wjcfb set jcwh=?,jcsj=? where cfid in (");
		inputV.add(form.getJcwh());
		inputV.add(form.getJcsj());
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
		sql.append("update xg_wjcf_wjcfjcshb t set t.sftj= '1' where cfid in");
		sql.append(" (select a.cfid from (select * from xg_wjcf_wjcfjcshb t where t.xtgwid in (select a.spgw from xg_xtwh_spbz a, ");
		sql.append(" (select splc,max(xh) xh from xg_xtwh_spbz where splc in  ");
		sql.append(" (select b.splc from xg_wjcf_ssjcsplb a  left join xg_xtwh_spbz b on a.jcspl = b.splc ) group by splc) b  ");
		sql.append("  where a.xh = b.xh and a.splc = b.splc) ) a  where (a.shzt='tg' or a.shzt = 'btg') and a.sftj='0')");
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
		sql.append("update xg_wjcf_wjcfjcshb t set t.sftj= '1' where cfid in(");
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
		sql.append("select a.spgw, a.gwmc, c.spyh from (select b.spgw,");
		sql.append("(select c.mc from xg_xtwh_spgw c where b.spgw = c.id) gwmc ");
		sql.append(" from xg_wjcf_ssjcsplb a  left join xg_xtwh_spbz b on a.jcspl = b.splc) a,");
		sql.append(" (select * from xg_xtwh_spgwyh where spyh = ? ) c where a.spgw = c.spgw and a.spgw = ");
		sql.append(" (select spgw  from xg_wjcf_ssjcsplb a  left join xg_xtwh_spbz b on a.jcspl = b.splc where xh = (select a.xh ");
		sql.append(" from (select splc, max(xh) xh  from xg_xtwh_spbz  where splc in (select b.splc  from xg_wjcf_ssjcsplb a");
		sql.append(" left join xg_xtwh_spbz b on a.jcspl = b.splc) group by splc) a)) ");
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
