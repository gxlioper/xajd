package xsgzgl.pjpy.szgyyq.mypj.pjxm;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommDAO;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xsgzgl.pjpy.szgyyq.mypj.stu.PjpyStuForm;

public class ZhcpDAO extends CommDAO{
	/**
	 * 获取语言表达列表
	 * 
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getZhcpList(PjpyStuForm model) {

		// 学年
		String xn = model.getXn();
		// 学期
		String xq = model.getXq();
		// 学号
		String xh = model.getXh();
		
		StringBuilder sql = new StringBuilder();

		
		  
		sql.append(" select '读书活动'mkmc, sum(sqf)sqf,sum(bzrshf)bzrshf,sum(xyshf)xyshf, ");
		sql.append(" sum(xxshf)xxshf,(select b.dshdf from szgy_zhszcphzlsb b where a.xh = b.xh and a.xn = b.xn and a.xq = b.xq )mkf from  (select xh,xn,xq,jjf,sqf, ");
		sql.append(" case when bzrsh='通过' then bzrshf else '' end bzrshf, ");
		sql.append(" case when xysh='通过' then xyshf else '' end xyshf,case when xxsh='通过' ");
		sql.append(" then xxshf else '' end xxshf from szyq_dshdjzb ) a ");
		sql.append(" where a.xn = '"+xn+"'  ");
		sql.append(" and a.xq = '"+xq+"'   and a.xh = '"+xh+"'  group by a.xn,a.xq,a.xh  ");
		 
		sql.append("   union all ");
		sql.append("   select '语言表达'mkmc, sum(sqf),sum(bzrshf),sum(xyshf),sum(xxshf), ");
		sql.append("   (select b.yybdf from szgy_zhszcphzlsb b where a.xh = b.xh and a.xn = b.xn and a.xq = b.xq )mkf from  (select xh,xn,xq,jjf,sqf, ");
		sql.append("   case when bzrsh='通过' then bzrshf else '' end bzrshf, ");
		sql.append("   case when xysh='通过' then xyshf else '' end xyshf,case when xxsh='通过' ");
		sql.append("   then xxshf else '' end xxshf from  szyq_yybdjzb)  a ");
		sql.append("   left join szgy_zhszcphzlsb b on a.xh=b.xh where a.xn = '"+xn+"'   ");
		sql.append("   and a.xq = '"+xq+"'   and a.xh = '"+xh+"'  group by a.xn,a.xq,a.xh  ");
		 
		sql.append("   union all ");
		sql.append("   select 'IVT论坛'mkmc, sum(sqf),sum(bzrshf),sum(xyshf),sum(xxshf), ");
		sql.append("   (select b.ivtlt from szgy_zhszcphzlsb b where a.xh = b.xh and a.xn = b.xn and a.xq = b.xq )mkf from (select xh,xn,xq,jjf,sqf, ");
		sql.append("   case when bzrsh='通过' then bzrshf else '' end bzrshf, ");
		sql.append("   case when xysh='通过' then xyshf else '' end xyshf,case when xxsh='通过'  ");
		sql.append("   then xxshf else '' end xxshf from  szyq_ivtltb ) a ");
		sql.append("   left join szgy_zhszcphzlsb b on a.xh=b.xh where a.xn = '"+xn+"'   ");
		sql.append("   and a.xq = '"+xq+"'   and a.xh = '"+xh+"'  group by a.xn,a.xq,a.xh  ");
		 
		sql.append("  union all ");
		sql.append("   select '文体活动'mkmc, sum(sqf),sum(bzrshf),sum(xyshf),sum(xxshf),(select b.wthd from szgy_zhszcphzlsb b where a.xh = b.xh and a.xn = b.xn and a.xq = b.xq )mkf  ");
		sql.append("   from  (select xh,xn,xq,jjf,sqf, ");
		sql.append("   case when bzrsh='通过' then bzrshf else '' end bzrshf, ");
		sql.append("  case when xysh='通过' then xyshf else '' end xyshf,case when xxsh='通过' ");
		sql.append("   then xxshf else '' end xxshf from szyq_xthddjb)  a ");
		sql.append("   left join szgy_zhszcphzlsb b on a.xh=b.xh where a.xn = '"+xn+"'  ");
		sql.append("   and a.xq = '"+xq+"'   and a.xh = '"+xh+"'  group by a.xn,a.xq,a.xh  ");
		  
		sql.append("   union all ");
		sql.append("   select '组织能力'mkmc, sum(sqf),sum(bzrshf),sum(xyshf),sum(xxshf),(select b.zznl from szgy_zhszcphzlsb b where a.xh = b.xh and a.xn = b.xn and a.xq = b.xq )mkf  ");
		sql.append("   from  (select xh,xn,xq,jjf,sqf, ");
		sql.append("   case when bzrsh='通过' then bzrshf else '' end bzrshf, ");
		sql.append("   case when xysh='通过' then xyshf else '' end xyshf,case when xxsh='通过'  ");
		sql.append("  then xxshf else '' end xxshf from szyc_zznlfzb)   a ");
		sql.append("  left join szgy_zhszcphzlsb b on a.xh=b.xh where a.xn = '"+xn+"'   ");
		sql.append("  and a.xq = '"+xq+"'   and a.xh = '"+xh+"'  group by a.xn,a.xq,a.xh  ");
		  
		sql.append("   union all ");
		sql.append("   select '社会实践'mkmc, sum(sqf),sum(bzrshf),sum(xyshf),sum(xxshf),(select b.shsj from szgy_zhszcphzlsb b where a.xh = b.xh and a.xn = b.xn and a.xq = b.xq )mkf from  ( ");
		sql.append("   select xh,xn,xq,jjf,sqf, ");
		sql.append("   case when bzrsh='通过' then bzrshf else '' end bzrshf, ");
		sql.append("   case when xysh='通过' then xyshf else '' end xyshf, ");
		sql.append("   case when xxsh='通过' then xxshf else '' end xxshf from szyc_shsjfzb)   a ");
		sql.append("   left join szgy_zhszcphzlsb b on a.xh=b.xh where a.xn = '"+xn+"'   ");
		sql.append("   and a.xq = '"+xq+"'   and a.xh = '"+xh+"'  group by a.xn,a.xq,a.xh  ");
		 
		  
		sql.append("   union all ");
		sql.append("   select '5S模块分'mkmc , sum(a.sqf),sum(''),sum(a.xyshf),sum(a.xxshf),(select b.wsmkf from szgy_zhszcphzlsb b where a.xh = b.xh and a.xn = b.xn and a.xq = b.xq )mkf from  ( ");
		sql.append("   select xh,xn,xq,case when jjf='减分' and xyshf is not null then '-'||xyshf else xyshf end xyshf, ");
		sql.append("   case when jjf='减分' and xxshf is not null then '-'||xxshf else xxshf end xxshf, ");
		sql.append("   case when jjf='减分' and sqf is not null then '-'||sqf else sqf end sqf from ( ");
		sql.append("   select xh,xn,xq,jjf,sqf,case when xysh='通过' then xyshf else '' end xyshf, ");
		sql.append("  case when xxsh='通过' then xxshf else '' end xxshf,fz from szyc_5sb))a ");
		sql.append("   left join szgy_zhszcphzlsb b on a.xh=b.xh and a.xn=b.xn and a.xq=b.xq  ");
		sql.append("   where a.xn = '"+xn+"'  and a.xq = '"+xq+"'   and a.xh = '"+xh+"'  group by a.xn,a.xq,a.xh  ");
		  
		  
		
		DAO dao = DAO.getInstance();
		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] {}, new String[] { "mkmc", "sqf",
						"bzrshf", "xyshf","xxshf","mkf"});

	
		return list;
	}
	
	
	/**
	 * 获得社会实践信息列表
	 * @author qlj
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public ArrayList<String[]> getZhcpList(PjpyStuForm model, User user)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		CommService commService = new CommService();
		SearchModel searchModel = model.getSearchModel();

		// 学号
		String xh = model.getXh();
		// 班级
		String bjdm = getOneValue("view_xsjbxx", "bjdm", "xh", xh);
		// 学年
		String xn = searchModel.getSearch_tj_xn()[0];
		// 学期
		String xq = searchModel.getSearch_tj_xq()[0];
		// 操作
		String[] cz = searchModel.getSearch_tj_cz();
		// 模糊查询
		String input_mhcx = searchModel.getInput_mhcx();
		// 查询类型
		String mhcx_lx = searchModel.getMhcx_lx();
		// 用户类型
		String yhlx = model.getYhlx();
		
		StringBuilder tableSql = new StringBuilder();

		tableSql.append("( select xh||'!!@@!!'||xn||'!!@@!!'||xq pkValue, a.xh,a.xm,a.xn,a.xq,a.zhszf,a.zhszfpm,cz from ");
		tableSql.append("( ");
		tableSql.append("select a.xh,a.xm,b.xn,b.xq,b.zhszf,b.zhszfpm, ");
		tableSql.append("a.nj,a.xydm,a.zydm,a.bjdm, ");
		tableSql.append("case when a.xh = '" + xh + "' then '查看' ");
		tableSql.append(" else '不可查看' end cz ");
		tableSql.append("from (select a.xh,a.xm,a.nj,a.xydm,a.zydm,a.bjdm from view_xsjbxx a where 1=1 ");
		tableSql.append("bz".equalsIgnoreCase(yhlx)||"stu".equalsIgnoreCase(yhlx) ? " and a.bjdm = '" + bjdm + "'" : "");
		tableSql.append(") a ");
		
		tableSql.append("left join ( ");
		tableSql.append(" select xh,xn,xq,zhszf,zhszfpm ");
		tableSql.append(" from szgy_zhszcphzlsb a  ");
		tableSql.append(" where xn = '"+xn+"' ");
		tableSql.append(" and xq = '"+xq+"'  ");
		tableSql.append(" ) b on a.xh = b.xh ");
	
		tableSql.append("order by zhszfpm,xh ");
		tableSql.append(") a ");
		tableSql.append("where 1 = 1 ");

		String[] inputV = new String[]{};
		
		if ("stu".equalsIgnoreCase(yhlx) || "bz".equalsIgnoreCase(yhlx)) {//班长
			// 操作
			if (cz != null && cz.length > 0) {
				tableSql.append("and ( ");
				for (int i = 0; i < cz.length; i++) {
					if (i != 0) {
						tableSql.append(" or ");
					}
					tableSql.append("a.cz = '" + commService.unicode2Gbk(cz[i])
							+ "'");
				}
				tableSql.append(") ");
			}

			// 模糊查询
			if (!Base.isNull(input_mhcx.trim())) {

				String[] mhcx = input_mhcx.split(" ");

				if (!"all".equalsIgnoreCase(mhcx_lx)) {

					tableSql.append("and ( ");
					for (int i = 0; i < mhcx.length; i++) {
						if (i != 0) {
							tableSql.append(" or ");
						}
						tableSql.append("a." + mhcx_lx + " like '%" + mhcx[i]
								+ "%'");
					}
					tableSql.append(") ");

				} else {

					tableSql.append("and ( ");
					for (int i = 0; i < mhcx.length; i++) {
						if (i != 0) {
							tableSql.append(" or ");
						}
						tableSql.append("(a.xh like '%" + mhcx[i] + "%' ");
						tableSql.append("or ");
						tableSql.append("a.xm like '%" + mhcx[i] + "%') ");
					}
					tableSql.append(") ");
				}
			}
		}else{
			user.setUserStatus(yhlx);
			String searchTj = SearchService.getSearchTj(model.getSearchModel());
			String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
					"xydm", "bjdm");

			tableSql.append(searchTj);
			tableSql.append(searchTjByUser);

			inputV = SearchService.getTjInput(model.getSearchModel());
		}

		tableSql.append(")");

		String query = " order by zhszfpm ";

		return getRsArrList(tableSql.toString(), query, inputV, new String[] {
				"xh", "xm", "zhszf", "zhszfpm", "cz" }, "", model);

	}
}
