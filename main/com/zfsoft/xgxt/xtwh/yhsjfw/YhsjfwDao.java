/**
 * @部门:学工产品事业部
 * @日期：2013-4-18 下午02:42:37 
 */
package com.zfsoft.xgxt.xtwh.yhsjfw;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 系统管理
 * @类功能描述: 用户数据范围
 * @作者： ligl
 * @时间： 2013-4-18 下午02:42:37
 * @版本： V1.0
 * @修改记录:
 */
public class YhsjfwDao extends SuperDAOImpl<YhModel> {

	/**
	 * 
	 * @描述:普通查询方法
	 * @作者：ligl
	 * @日期：2013-4-18 下午02:42:55
	 * @修改记录: void 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getPageList(YhModel model)
			throws Exception {
		List<String> params = new ArrayList<String>();
		StringBuffer sb = new StringBuffer();
		sb
				.append("select a.*, b.zmc,c.bmmc,d.fdynum,e.bzrnum,f.sjfwdm,f.sjfwmc");
		sb.append(" from yhb a");
		sb.append(" left join (select distinct yhm,zmc from view_yhz_yhxxb) b  on a.yhm = b.yhm");
		sb.append(" left join zxbz_xxbmdm c on a.szbm = c.bmdm");
		sb.append(" left join  (select c.zgh, count(1) fdynum  from fdybjb c");
		sb
				.append(" where exists (select 1 from view_njxyzybj a  where a.bjdm = c.bjdm)  group by c.zgh) d");
		sb.append(" on a.yhm = d.zgh");
		sb.append("  left join (select d.zgh, count(1) bzrnum from bzrbbb d ");
		sb
				.append(" where exists (select 1 from view_njxyzybj a where a.bjdm = d.bjdm)  group by d.zgh) e");
		sb.append("  on a.yhm = e.zgh");
		sb.append(" left join zfxg_xtgl_yhsjfwb f on a.yhm=f.yhm     ");
		sb.append(" where 1=1 ");
		if (model.getYhm() != null && !model.getYhm().equals("")) {
			params.add(model.getYhm());
			sb.append(" and a.yhm like '%'||?||'%'");
		}
		if (model.getXm() != null && !model.getXm().equals("")) {
			params.add(model.getXm());
			sb.append(" and a.xm like '%'||?||'%'");
		}
		if (model.getZdm() != null && !model.getZdm().equals("")) {
			params.add(model.getZdm());
			sb.append(" and a.zdm like '%'||?||'%'");
		}
		if (model.getSzbm() != null && !model.getSzbm().equals("")) {
			params.add(model.getSzbm());
			sb.append(" and  a.szbm=? ");
		}
		if (model.getSffdy() != null && !model.getSffdy().equals("")) {
			if (model.getSffdy().equals("1")) {
				sb.append(" and  d.fdynum is not null ");
			} else {
				sb.append(" and  d.fdynum is null ");
			}
		}
		if (model.getSfbzr() != null && !model.getSfbzr().equals("")) {
			if (model.getSfbzr().equals("1")) {
				sb.append(" and  e.bzrnum is not null ");
			} else {
				sb.append(" and  e.bzrnum is null ");
			}
		}
		List<HashMap<String, String>> pageList = getPageList(model, sb
				.toString(), params.toArray(new String[] {}));
		return pageList;
	}

	/**
	 * 获取用户组列表，不包含学生组
	 */
	public List<HashMap<String, String>> getYhzForSzdwList() throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append("select zdm dm,zmc mc from yhzb a");
		sb.append(" where zdm!='6727'");
		sb.append(" order by zdm");
		return dao.getListNotOut(sb.toString(), null);
	}

	/**
	 * 获取获得部门代码，名称列表
	 */
	public List<HashMap<String, String>> getYjbmList() throws Exception {
		return dao.getYjbmList();
	}

	/**
	 * 获取年级学院专业列表
	 */
	public List<HashMap<String, String>> getNjxyzyList() throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append("select distinct nj,xydm,xymc,zydm,zymc  ");
		sb.append(" from view_njxyzybj_all ");
		sb.append(" order by nj,xydm,zydm");
		return dao.getListNotOut(sb.toString(), null);
	}	

	/**
	 * 
	 * @描述:根据用户名获取数据范围代码、名称
	 * @作者：ligl
	 * @日期：2014-2-27 上午10:13:46
	 * @修改记录:
	 * @param yhm
	 * @return
	 * @throws Exception
	 *             HashMap<String,String> 返回类型
	 * @throws
	 */
	public HashMap<String, String> getDataById(String yhm) throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append(" select sjfwdm,sjfwmc ");
		sb.append(" from  zfxg_xtgl_yhsjfwb ");
		sb.append(" where yhm=?");
		String[] inputValue = { yhm };
		return dao.getMapNotOut(sb.toString(), inputValue);
	}

	/**
	 * 
	 * @描述:数据范围授权保存
	 * @作者：ligl
	 * @日期：2014-2-27 下午04:22:11
	 * @修改记录: 
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean runDeal(YhsjfwModel model) throws Exception {
		String ids = model.getIds();
		String sjfwdm = model.getSjfwdm();
		String sjfwmc = model.getSjfwmc();
		int[] result = null;
		String[] idArr = ids.split(",");
		List<String> sqlList = new ArrayList<String>();
		String sql = null;
		String idsStr = "'" + ids.replaceAll(",", "','") + "'";
		sql = "delete from zfxg_xtgl_yhsjfwb ";
		sql += " where yhm in(" + idsStr + ")";

		sqlList.add(sql);
		String yhm = null;
		for (int i = 0; i < idArr.length; i++) {
			yhm = idArr[i];
			sql = "insert into zfxg_xtgl_yhsjfwb";
			sql += "(yhm,sjfwdm,sjfwmc)";
			sql += " values('" + yhm + "','" + sjfwdm + "','" + sjfwmc + "')";
			sqlList.add(sql);
		}
		String[] sqls = new String[sqlList.size()];
		for (int i = 0; i < sqlList.size(); i++) {
			sqls[i] = sqlList.get(i);
		}
		result = dao.runBatch(sqls);
		return dao.checkBatch(result);
	}

	protected void setTableInfo() {
		super.setTableName("");
		super.setKey("");
	}

	@Override
	public List<HashMap<String, String>> getPageList(YhModel t, User user)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

}
