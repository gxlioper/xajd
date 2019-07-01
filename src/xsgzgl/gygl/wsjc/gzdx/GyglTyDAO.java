package xsgzgl.gygl.wsjc.gzdx;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommDAO;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.MakeQuery;

import common.Globals;

public class GyglTyDAO extends CommDAO {

	/**
	 * 获得公寓管理相关列表
	 * 
	 * @author luojw
	 */
	public ArrayList<String[]> getGyglList(String tableName, Object model,
			String[] colList, String other_query)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		String[] queryList = new String[] { "xqdm", "xydm", "zydm", "bjdm",
				"nj", "xn", "nd", "xq", "lddm", "cs", "qsh" };
		String[] queryLikeList = new String[] { "xh", "xm" };
		MakeQuery myQuery = new MakeQuery();
		myQuery.makeQuery(queryList, queryLikeList, model);
		String query = myQuery.getQueryString();

		
		
		other_query = Base.isNull(other_query) ? "" : other_query;
		if (!Base.isNull(query)) {
			query += " " + other_query;
		} else {
			query = other_query;
		}

		return CommonQueryDAO.commonQuery(tableName, query, myQuery
				.getInputList(), colList, "", model);
	}
	
	

	/**
	 * 获得公寓管理相关信息
	 * 
	 * @param tableName(表名)
	 * @param pk(主键)
	 * @param pkValue(主键值)
	 * @param colList(输出值)
	 * 
	 * @author luojw
	 */
	public HashMap<String, String> getGyglInfo(String tableName, String pk,
			String pkValue, String[] colList) {
		return commonQueryOne(tableName, colList, pk, pkValue);
	}

	/**
	 * 获得需维护下拉框值
	 * 
	 * @param tableName(表名)
	 * @param dm(代码)
	 * @param mc(名称)
	 * @param msg(显示信息)
	 * @param pk(主键)
	 * @param pkValue(主键值)
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getGyglList(String tableName,
			String dm, String mc, String msg, String pk, String pkValue) {

		DAO dao = DAO.getInstance();

		if (Base.isNull(msg)) {
			msg = "----请选择-----";
		}

		StringBuffer sql = new StringBuffer();
		sql.append("select '' dm, '" + msg + "'mc from dual union");
		sql.append(" select distinct(");
		sql.append(dm);
		sql.append(" ) dm,");
		sql.append(mc + " mc");
		sql.append(" from " + tableName);
		if (!Base.isNull(pk)) {
			sql.append(" where " + pk + "= '" + pkValue + "'");
		}
		sql.append(" order by dm nulls first");

		return dao.getList(sql.toString(), new String[] {}, new String[] {
				"dm", "mc" });
	}

	/**
	 * 获得校区楼栋列表
	 * 
	 * @param lx(类型:xq,ld)
	 * @param xqdm(校区代码)
	 * @param lddm(楼栋代码)
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getXqYqLdList(String lx, String xqdm,
			String yqdm, String lddm) {

		DAO dao = DAO.getInstance();

		xqdm = Base.isNull(xqdm) ? "%" : xqdm;
		yqdm = Base.isNull(yqdm) ? "%" : yqdm;
		lddm = Base.isNull(lddm) ? "%" : lddm;

		String dm = "";
		String mc = "";

		if ("ld".equalsIgnoreCase(lx)) {//楼栋
			dm = "lddm";
			mc = "ldmc";
		} else if ("xq".equalsIgnoreCase(lx)) {//校区
			dm = "xqdm";
			mc = "xqmc";
		} else if ("yq".equalsIgnoreCase(lx)) {//园区
			dm = "yqdm";
			mc = "yqmc";
		}

		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		StringBuffer sql = new StringBuffer();
		String[] col = new String[] { xqdm, yqdm, lddm };

		sql.append("select '' dm, '----请选择-----'mc from dual union");
		sql.append(" select distinct(");
		sql.append(dm);
		sql.append(" ) dm,");
		sql.append(mc + " mc");
		sql.append(" from view_bjlh_ldxx where ");
		sql.append(" xqdm like ? and yqdm like ? and lddm like ? ");
		sql.append(" order by dm nulls first");

		list = dao.getList(sql.toString(), col, new String[] { "dm", "mc" });
		// System.out.println(sql);
		return list;
	}
	
	/**
	 * 获得校区楼栋列表
	 * 
	 * @param lx(类型:xq,ld)
	 * @param xqdm(校区代码)
	 * @param lddm(楼栋代码)
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getXqLdList(String lx, String xqdm,
			String lddm, String userName) {

		DAO dao = DAO.getInstance();

		String xxdm = Base.xxdm;

		xqdm = Base.isNull(xqdm) ? "%" : xqdm;
		lddm = Base.isNull(lddm) ? "%" : lddm;

		String dm = "";
		String mc = "";

		if ("ld".equalsIgnoreCase(lx)) {// 楼栋
			dm = "lddm";
			mc = "ldmc";
			if (Globals.XXDM_ZGDZDX.equalsIgnoreCase(xxdm)) {// 中国地大
				dm = "lddm";
				mc = "yqmc||'/'||ldmc";
			}
		} else if ("xq".equalsIgnoreCase(lx)) {// 校区
			dm = "xqdm";
			mc = "xqmc";
		} else if ("yq".equalsIgnoreCase(lx)) {// 园区
			dm = "yqdm";
			mc = "yqmc";
		}

		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		StringBuffer sql = new StringBuffer();
		String[] col = new String[] { xqdm, lddm };

		sql.append("select '' dm, '----请选择-----'mc from dual union");
		sql.append(" select distinct(");
		sql.append(dm);
		sql.append(" ) dm,");
		sql.append(mc + " mc");
		sql.append(" from view_bjlh_ldxx a where ");
		sql.append(" xqdm like ? and lddm like ? ");
		if (isGyfdy(userName)) {
			sql.append(" and exists(select 1 from gygl_lzxxb b ");
			sql.append(" where a.xqdm = b.xqdm ");
			sql.append(" and a.lddm = b.lddm ");
			sql.append(" and b.yhm = '" + userName + "')");
		}
		sql.append(" order by dm nulls first");

		list = dao.getList(sql.toString(), col, new String[] { "dm", "mc" });
		//System.out.println(sql);
		return list;
	}

	/**
	 * 获得楼栋层数列表
	 * 
	 * @param lddm(楼栋代码)
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getCsList(String lddm) {
		DAO dao = DAO.getInstance();
		String sql = "select cs from view_ldxx where lddm = ?";
		String cs = dao.getOneRs(sql, new String[] { lddm }, "cs");

		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("dm", "");
		map.put("mc", "----请选择----");
		list.add(map);

		if (!Base.isNull(cs)) {
			for (int i = 1; i <= Integer.parseInt(cs); i++) {
				map = new HashMap<String, String>();
				map.put("dm", String.valueOf(i));
				map.put("mc", "第" + String.valueOf(i) + "层");
				list.add(map);
			}
		}

		return list;
	}

	/**
	 * 获得楼栋寝室列表
	 * 
	 * @param lddm(楼栋代码)
	 * @param cs(层数)
	 * @author luojw
	 */
	public List<HashMap<String, String>> getQsList(String lddm, String cs,
			String query) {

		DAO dao = DAO.getInstance();

		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		
		if (!Base.isNull(lddm)) {
			lddm = Base.isNull(lddm) ? "%" : lddm;
			cs = Base.isNull(cs) ? "%" : cs;

			StringBuffer sql = new StringBuffer();
			sql.append("select '' dm, '----请选择-----'mc from dual union ");
			sql.append("select qsh dm,qsh mc  from view_ssxx a ");
			sql.append("where lddm like ? and cs like ? ");

			if (!Base.isNull(query)) {
				sql.append(query);
			}

			sql.append("order by dm nulls first");

			list = dao.getList(sql.toString(), new String[] { lddm, cs },
					new String[] { "dm", "mc" });
		}else{
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("dm", "");
			map.put("mc", "----请选择----");
			list.add(map);
		}
		
		return list;
	}

	/**
	 * 获得无需维护下拉框值
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getSelectList(String lx) {

		DAO dao = DAO.getInstance();

		String[] dm = null;
		String[] mc = null;

		if ("sflx".equalsIgnoreCase(lx)) {
			dm = new String[] { "是", "否" };
			mc = new String[] { "是", "否" };
		} else if ("bblx".equalsIgnoreCase(lx)) {
			dm = new String[] { "优秀率", "良好率", "合格率", "不合格率" };
			mc = new String[] { "优秀率", "良好率", "合格率", "不合格率" };
		} else if ("shlx".equalsIgnoreCase(lx)) {// 审核类型
			dm = new String[] { "未审核", "通过", "不通过" };
			mc = new String[] { "未审核", "通过", "不通过" };
		} else if ("clsb".equalsIgnoreCase(lx)) {// 材料上报
			String xxdm = Base.xxdm;
			dm = getNrzd(xxdm, lx);
			mc = getNrzd(xxdm, lx);
		} else if ("bxtjfw_ss".equalsIgnoreCase(lx)) {// 报修统计范围(所属)
			dm = new String[] { "nj", "xy", "zy", "bj", "ld" };
			mc = new String[] { "年级", Base.YXPZXY_KEY, "专业", "班级", "楼栋" };
		} else if ("bxtjfw_cl".equalsIgnoreCase(lx)) {// 报修统计范围（材料）
			dm = new String[] { "cllx", "clmc" };
			mc = new String[] { "材料类型", "材料名称" };
		} else if ("bxtjfs".equalsIgnoreCase(lx)) {// 报修统计方式
			dm = new String[] { "bxtjfw_ss", "bxtjfw_cl" };
			mc = new String[] { "根据学生所属", "根据材料" };
		} else if ("xblx".equalsIgnoreCase(lx)) {// 性别
			dm = new String[] { "男", "女" };
			mc = new String[] { "男", "女" };
		}

		return dao.arrayToList(dm, mc);
	}

	/**
	 * 删除所上传文件
	 * 
	 * @author luo
	 * @throws Exception
	 */
	public boolean fileDel(String tableName, String dzzd, String pk,
			String pkValue) throws Exception {
		DAO dao = new DAO();

		boolean flag = true;
		String sql = "select " + dzzd + " scdz from " + tableName + " where "
				+ pk + " = ?";
		String wjlj = dao.getOneRs(sql, new String[] { pkValue }, "scdz");
		if (wjlj != null) {
			File file = new File(wjlj);
			if (file.exists()) {
				file.delete();
				sql = "update " + tableName + " set " + dzzd + "='' where "
						+ pk + " = ?";
				flag = dao.runUpdate(sql, new String[] { pkValue });
			}
		}
		return flag;
	}

	/**
	 * 获得周次列表
	 * 
	 * @throws SQLException
	 */
	public List<HashMap<String, String>> getZcList() throws SQLException {

		DAO dao = DAO.getInstance();

		String sql = "select xqzs from xtszb where rownum=1";
		String xqzs = dao.getOneRs(sql, new String[] {}, "xqzs");
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		if (!Base.isNull(xqzs)) {
			for (int i = 1; i <= Integer.parseInt(xqzs); i++) {
				HashMap<String, String> map = new HashMap<String, String>();
				if (i < 10) {
					map.put("dm", "0" + String.valueOf(i));
					map.put("mc", "第0" + String.valueOf(i) + "周");
				} else {
					map.put("dm", String.valueOf(i));
					map.put("mc", "第" + String.valueOf(i) + "周");
				}
				list.add(map);
			}
		}
		return list;
	}

	/**
	 * 获得系统当前时间
	 * 
	 * @author luojw
	 */
	public String getNowTime(String lx) {
		DAO dao = DAO.getInstance();
		String sql = "";
		if ("1".equalsIgnoreCase(lx)) {
			sql = "select to_char(sysdate, 'YYYY') || '年' || to_char(sysdate, 'MM') "
					+ "|| '月' || to_char(sysdate, 'DD') || '日 ' now from dual";
		} else if ("2".equalsIgnoreCase(lx)) {
			sql = "select to_char(sysdate, 'YYYY') ||  to_char(sysdate, 'MM') "
					+ "||  to_char(sysdate, 'DD') now from dual";
		}
		String now = dao.getOneRs(sql, new String[] {}, "now");
		return now;
	}

	/**
	 * 获得指定表的指定字段
	 * 
	 * @author luojw
	 */
	public String getOneValue(String tableName, String dm, String pk,
			String pkValue) {
		DAO dao = DAO.getInstance();
		StringBuffer sql = new StringBuffer();
		sql.append("select ");
		sql.append(dm);
		sql.append(" from ");
		sql.append(tableName);
		sql.append(" where ");
		sql.append(pk + "='" + pkValue + "'");
		sql.append(" and rownum = 1 ");

		String value = dao.getOneRs(sql.toString(), new String[] {}, dm);
		return value;
	}

	/**
	 * 根据值从小到大排列，返回列表
	 * 
	 * @param text(显示)
	 * @param valie(值)
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> orderByList(String[] value,
			String[] text) {

		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		if (value != null && value.length > 0) {
			
			for (int i = 0; i < value.length; i++) {
				
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("dm", value[i]);
				map.put("mc", text[i]);
				
				list.add(map);
			}
			
		}
		

		if (list != null && list.size() > 0) {
			
			for (int i = 0; i < list.size(); i++) {

				HashMap<String, String> map = list.get(i);
				String dm = map.get("dm");
				String[] ssInfo = dm.split("/");
				if (ssInfo != null && ssInfo.length == 3) {
					String ld = ssInfo[0];
					String cw = ssInfo[1];
					
					String[] ldInfo = ld.split("-");
					
					if (ldInfo != null && ldInfo.length == 2) {
						
						String lddm = ldInfo[0];
						String qsh = ldInfo[1];
						
						map.put("lddm", lddm);
						map.put("qsh", qsh);
					}
					
					if (ldInfo != null && ldInfo.length == 1) {

						String lddm = ldInfo[0];

						map.put("lddm", lddm);
					}

					map.put("cw", cw);
					System.out.println(dm);
				}
				
			}
		}
		
		Collections.sort(list, new MyCompare());
		
		return list;
	}
	
	/**
	 * 对list值得进行排序
	 * 
	 * @author luo
	 */
	static class MyCompare implements Comparator {

		public int compare(Object o1, Object o2) {
			if (o1 instanceof Map && o2 instanceof Map) {
				Map m1 = (Map) o1;
				Map m2 = (Map) o2;

				String lddm1 = (String) m1.get("lddm");
				String lddm2 = (String) m2.get("lddm");

				String qsh1 = (String) m1.get("qsh");
				String qsh2 = (String) m2.get("qsh");
				
				String cw1 = (String) m1.get("cw");
				String cw2 = (String) m2.get("cw");
				
				int value = 0;
				
				float ldbj1 = Float.parseFloat(lddm1 == null ? "0" : lddm1);
				float ldbj2 = Float.parseFloat(lddm2 == null ? "0" : lddm2);
				float qsbj1 = Float.parseFloat(qsh1 == null ? "0" : qsh1);
				float qsbj2 = Float.parseFloat(qsh2 == null ? "0" : qsh2);
				float cwbj1 = Float.parseFloat(cw1 == null ? "0" : cw1);
				float cwbj2 = Float.parseFloat(cw2 == null ? "0" : cw2);
				
				if (ldbj2 == ldbj1) {
					if (!Base.isNull(qsh1) && !Base.isNull(qsh2)) {
						if (qsbj2 == qsbj1) {
							value = cwbj2 > cwbj1 ? 0 : 1;
						}else{
							value = qsbj2 > qsbj1 ? 0 : 1;
						}
					}
				} else {
					value = ldbj2 > ldbj1 ? 0 : 1;
				}

				return value;
			}
			return 0;
		}
	}
	
	
	public synchronized List<HashMap<String,String>> getCwFpSsCwXxList(String xqdm,String xydm,String lddm,
			String xb,String cs,String cwfp,String nj,String bjdm,String[] sortValue,String[] sortText){
		StringBuffer sql = new StringBuffer();
		String xxdm = Base.xxdm;
		StringBuffer querry = new StringBuffer();
		if(!Base.isNull(cwfp)&&cwfp.equalsIgnoreCase("checked")){
			querry.append(Base.isNull(lddm)?" and 1=2 ":"  and b.lddm='" + lddm + "'");    //楼栋代码
			querry.append(Base.isNull(xb)?" and 1=1 ":"  and b.xbxd='" + xb + "' ");       //性别
			querry.append(Base.isNull(cs)?" and 1=1 ":"  and b.cs='" + cs + "' ");         // 层数
			//querry.append(Base.isNull(nj)?" and 1=1 ":" and a.nj='"+nj+"' ");
			querry.append(" order by dm");
			//querry.append(" order by to_number(Replace(Replace(dm,'-',''),'/',''))");
			sql.append( "select distinct(a.ssbh||'/'||c.cwh) dm,b.ldmc||b.qsh||'/'||b.cws||'/'||c.cwh mc from ssfpb a,view_ssxx b,cwxxb c where a.ssbh=b.ssbh and a.ssbh=c.ssbh and a.ssbh||'-'||c.cwh not in (select ssbh||'-'||cwh from xszsxxb)"+querry);
		}else{
			querry.append(Base.isNull(xydm)?" and 1=2 ":"  and a.xydm='" + xydm + "'");
			querry.append((Base.isNull(xqdm)||xqdm.equalsIgnoreCase("null"))?" and 1=2 ":" and b.xqdm='" + xqdm + "'");
			querry.append((Base.isNull(xb)||xb.equalsIgnoreCase("null"))?" and 1=1 ":"  and b.xbxd='" + xb + "' ");
			querry.append((Base.isNull(lddm)||lddm.equalsIgnoreCase("null"))?" and 1=1 ":"  and b.lddm='" + lddm + "'");
			querry.append((Base.isNull(cs)||cs.equalsIgnoreCase("null"))?" and 1=1 ":"  and b.cs='" + cs + "' ");
			querry.append((Base.isNull(nj))?" and 1=2 ":" and a.nj='"+nj+"' ");
			if (xxdm.equalsIgnoreCase(Globals.XXDM_HZZY)) {//杭州职业技术学院				
				querry.append((Base.isNull(bjdm)||bjdm.equalsIgnoreCase("null"))?" and 1=1 ":" and a.bjdm='"+bjdm+"' ");			
				querry.append(" order by dm ");	
				sql.append(" select a.ssbh||'/'||a.cwh dm,b.ldmc||b.qsh||'/'||b.cws||'/'||a.cwh mc from ssfpb a,view_ssxx b where ");
				sql.append("a.ssbh=b.ssbh and not exists (select * from xszsxxb b where a.ssbh=b.ssbh and a.cwh=b.cwh) "+querry );
			} else {				
				sql.append(" select dm,mc from(select a.ssbh||'/'||c.cwh dm,b.ldmc||b.qsh||'/'||b.cws||'/'||c.cwh||'/'||b.xbxd mc,");//名称增加性别限定
				sql.append(" to_number(b.cws) - to_number((select count(ssbh) from xszsxxb b where a.ssbh = b.ssbh)) leaveCws,");
				sql.append("  (select count(ssbh)from view_xszsxx b where a.ssbh = b.ssbh and a.nj=b.nj and a.xydm=b.xydm)rzs,");
				
				//辽宁机电职业技术学院 床位号存在中文，个性化修改
				String sb = "";
				if("12898".equals(Base.xxdm)){
					sb = "c.cwh";
				}else{
					sb = "to_number(c.cwh)";
				}
   				sql.append(" row_number() over( partition by a.ssbh order by a.ssbh,"+sb+") px,a.cws fps  from ssfpb a,view_ssxx b,");
   				sql.append(" cwxxb c where a.ssbh = b.ssbh and a.ssbh = c.ssbh");
   				sql.append(querry.toString());
   				sql.append(" and not exists (select 1 from (select distinct ssbh,cwh from xszsxxb) m where a.ssbh=m.ssbh and c.cwh=m.cwh) ");
   				sql.append(" and not exists (select 1 from wxs_xszsxxb n where a.ssbh=n.ssbh and c.cwh = n.cwh) ");
   				sql.append(")a where px <=fps-rzs");
   			
			}
		}
		DAO dao = DAO.getInstance();
		
		List<HashMap<String,String>> result = dao.getList(sql.toString(), new String[] {}, new String[] {"dm", "mc" });
		
		if (null != sortValue){
			for (int i = 0;i < sortValue.length; i++){
				HashMap<String,String> map = new HashMap<String,String>();
				map.put("dm", sortValue[i]);
				map.put("mc", sortText[i]);
			}
			Collections.sort(result, new MyCompare());
		}
		
		return result;
	}
	
	
	
	
	
	
	
	/**
	 * 获得内容字段
	 * 
	 * @author luojw
	 */
	private String[] getNrzd(String xxdm, String manu) {

		String[] title = null;

		if (Globals.XXDM_ZJCMXY.equalsIgnoreCase(xxdm)
				&& "clsb".equalsIgnoreCase(manu)) {
			title = new String[] { "", "材料类型", "材料名称", "数量", "单价", "总计" };
		} else {
			title = new String[] { "", "材料类型", "材料名称", "数量", "单价", "总计" };
		}

		return title;
	}
	
	/**
	 * 判断是否公寓管理员
	 * 
	 * @author luojw
	 */
	public Boolean isGyfdy(String userName) {

		DAO dao = DAO.getInstance();

		String sql = "select count(*) num from gygl_lzxxb  where yhm=? ";

		String num = dao.getOneRs(sql, new String[] { userName }, "num");

		boolean flag = false;

		if (!"0".equalsIgnoreCase(num)) {
			flag = true;
		}

		return flag;
	}
	// ====================以上made by 伟大的luo===============================
	
	/**
	 * 检测卫生检查分数是否达标
	 * @param HashMap<String, String> map
	 * @return boolean
	 * */
	public boolean wsjcfs(HashMap<String, String> map){
		DAO dao = DAO.getInstance();
		boolean message = false;
		String sql = "select nvl(wsjc,0) wsjc from gygl_csszb where xn=? and xq=?";
		//设置的卫生检查分最低标准
		String wsjcbz = dao.getOneRs(sql, new String[]{map.get("xn"), map.get("xq")}, 
				                     "wsjc");
		//查询学年学期卫生检查分低于设置标准的记录数
		sql = "select count(*)num from view_gywsjcxx where xn=? and xq=? and ssbh=? " +
			  "and to_number(nvl(fs,0)) < to_number(?)";
		String num = dao.getOneRs(sql, new String[]{map.get("xn"), 
				                                    map.get("xq"), 
				                                    map.get("ssbh"),wsjcbz}, 
				                  "num");
		message = Integer.parseInt(num) > 0 ? false : true;
		return message;
	}
	
	/**
	 * 查询寝室违纪人数
	 * @param HashMap<String, String> map
	 * @return int
	 * */
	public int qswjrs(HashMap<String, String> map){
		DAO dao = DAO.getInstance();
		String sql = "select count(*) num from view_wjcf a where xn=? and xq=? " +
				     "and exists(select 1 from view_xsjbxx b where a.xh=b.xh and b.ssbh=?)";
		String num = dao.getOneRs(sql, new String[]{map.get("xn"), map.get("xq"), 
				                                    map.get("ssbh")}, 
				                  "num");
		return Integer.parseInt(num);
	}
	
	/**
	 * 查询学期获得月度文明寝室的次数
	 * @param HashMap<String, String> map
	 * @return int
	 * */
	public int xqydwmqs(HashMap<String, String> map){
		DAO dao = DAO.getInstance();
		String sql = "select count(*) num from gygl_ydwmqssqb where xn=? " +
				     "and xq=? and ssbh=? and xxsh='通过'";
		String num = dao.getOneRs(sql, new String[]{map.get("xn"), map.get("xq"), 
				                                    map.get("ssbh")}, 
				                  "num");
		return Integer.parseInt(num);
	}
	
	/**
	 * 查询学年获得学期文明寝室的次数
	 * @param HashMap<String, String> map
	 * @return int
	 * */
	public int xnxqwmqs(HashMap<String, String> map){
		DAO dao = DAO.getInstance();
		String sql = "select count(*) num from gygl_xqwmqssqb where xn=? " +
				     "and ssbh=? and xxsh='通过'";
		String num = dao.getOneRs(sql, new String[]{map.get("xn"), map.get("ssbh")}, 
				                  "num");
		return Integer.parseInt(num);
	}
	
	  /**
	 * 判断是否值班人员
	 * 
	 * @author sjf
	 */
	public Boolean isZbry(String userName) {

		DAO dao = DAO.getInstance();

		String sql = "select count(*) num from zbrydmb  where yhm=? ";

		String num = dao.getOneRs(sql, new String[] { userName }, "num");

		boolean flag = false;

		if (!"0".equalsIgnoreCase(num)) {
			flag = true;
		}

		return flag;
	}
	
}
