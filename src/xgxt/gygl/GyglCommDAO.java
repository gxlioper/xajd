package xgxt.gygl;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommDAO;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 公寓管理_通用_DAO类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author 伟大的骆
 * @version 1.0
 */

public class GyglCommDAO extends CommDAO {
	
	/**
	 * 获得寝室信息(前台DWR)
	 * 
	 * @author 伟大的骆
	 * 
	 */
	public List<HashMap<String, String>> getQsInfoList(String[] gyInfo,
			String[] ssbh, String userStatus, String userName, String userDep) {
		
		// 楼栋代码
		String lddm = gyInfo[0];
		// 层数
		String cs = gyInfo[1];
		// 分配对象
		String fpdx = gyInfo[2];
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select a.*,(select b.ldmc from sslddmb b where a.lddm = b.lddm) ldmc from (");
		sql.append("select a.lddm,a.cs,a.qsh,a.cws,a.xb,a.kfhz,d.fpbm,d.fpdx,'' rzzt, ");
		sql.append("a.fpbj,nvl(b.bkzrcws, 0) bkzrcws,nvl(c.yzrs, 0) yzrs,d.bmdm,d.nj from");
		
		sql.append("(select a.lddm, a.cs, a.qsh, a.cws, a.xb, a.kfhz, a.fpbj ");
		sql.append("from ssxxb a where 1 = 1 ");
		sql.append("and a.lddm = ? ");
		sql.append("and a.cs = ? ");
		sql.append(") a ");
		
		sql.append("left join (select d.lddm, d.cs, d.qsh,d.fpbm,d.fpdx,d.bmdm,d.nj ");
		sql.append("from xg_view_gygl_qsfp d where 1 = 1 ");
		sql.append("and d.lddm = ? ");
		sql.append("and d.cs = ? ");
		sql.append("and d.fpdx = ? ");
		sql.append(") d on a.lddm = d.lddm and a.cs = d.cs and a.qsh = d.qsh ");
		
		sql.append("left join (select b.lddm, b.cs, b.qsh, count(1) bkzrcws ");
		sql.append("from xg_gygl_qtcwxxb b where b.cwbj <> '可分配' ");
		sql.append("group by b.lddm, b.cs, b.qsh) b on a.lddm = b.lddm ");
		sql.append("and a.cs = b.cs and a.qsh = b.qsh ");

		sql.append("left join (select c.lddm, c.cs, c.qsh, count(1) yzrs ");
		sql.append("from xszsxxb c group by c.lddm, c.cs, c.qsh) c on a.lddm = c.lddm ");
		sql.append("and a.cs = c.cs and a.qsh = c.qsh ");
		sql.append(") a ");
		sql.append("where 1 = 1 ");
		
		if (ssbh != null && ssbh.length > 0) {
			sql.append(" and ( ");
			for (int i = 0; i < ssbh.length; i++) {
				if (i != 0) {
					sql.append(" or ");
				}
				sql.append(" a.lddm||'-'||a.qsh||'_'||a.cs = '" + ssbh[i] + "' ");
			}
			sql.append(" ) ");
		}
		
		sql.append("order by a.lddm,a.cs,a.qsh ");
		
		DAO dao = DAO.getInstance();
		System.out.println(sql);
		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] { lddm, cs,lddm, cs,fpdx },
				new String[] { "lddm","ldmc", "cs", "qsh", "cws", "xb", "kfhz",
						"fpbm", "fpbj","fpdx", "bkzrcws", "yzrs","rzzt","bmdm","nj" });
		
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				HashMap<String, String> map = list.get(i);

				String cws = map.get("cws");
				// 不可住人床位数
				String bkzrcws = map.get("bkzrcws");
				// 已住人数
				String yzrs = map.get("yzrs");
				
				String rzzt = "";

				if ("0".equalsIgnoreCase(yzrs)) {// 已住人数为0
					rzzt = "空的";
				} else if (cws.equalsIgnoreCase(String
						.valueOf(Integer.parseInt(bkzrcws)
								+ Integer.parseInt(yzrs)))) {// 已住人数+不可住人=寝室床位
					rzzt = "满的";
				} else {// 其他（错误数据不管）
					rzzt = "闲的";
				}
				
				list.get(i).put("rzzt", rzzt);
			}
		}
		
		return list;
	}
	
	/**
	 * 获得寝室信息(前台DWR)
	 * 
	 * @author 伟大的骆
	 * 
	 */
	public List<HashMap<String, String>> getQsxxList(String[] gyInfo,
			 String userStatus, String userName, String userDep) {
		
		// 楼栋代码
		String lddm = gyInfo[0];
		// 层数
		String cs = gyInfo[1];
		// 分配对象
		String fpdx = gyInfo[2];
		// 分配方式
		String fpfs = gyInfo[3];
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select a.*,(select b.ldmc from sslddmb b where a.lddm = b.lddm) ldmc from (");
		sql.append("select a.lddm,a.cs,a.qsh,a.cws,a.xb,a.kfhz,d.fpbm,d.fpdx,'' rzzt, ");
		sql.append("a.fpbj,nvl(b.bkzrcws, 0) bkzrcws,nvl(c.yzrs, 0) yzrs,d.bmdm,d.nj from");
		
		sql.append("(select a.lddm, a.cs, a.qsh, a.cws, a.xb, a.kfhz, a.fpbj ");
		sql.append("from ssxxb a where 1 = 1 ");
		sql.append("and a.lddm = ? ");
		sql.append("and a.cs = ? ");
		sql.append(") a ");
		
		sql.append("left join (select d.lddm, d.cs, d.qsh,d.fpbm,d.fpdx,d.bmdm,d.nj ");
		sql.append("from xg_view_gygl_qsfp d where 1 = 1 ");
		sql.append("and d.lddm = ? ");
		sql.append("and d.cs = ? ");
		sql.append(") d on a.lddm = d.lddm and a.cs = d.cs and a.qsh = d.qsh ");
		
		sql.append("left join (select b.lddm, b.cs, b.qsh, count(1) bkzrcws ");
		sql.append("from xg_gygl_qtcwxxb b where b.cwbj <> '可分配' ");
		sql.append("group by b.lddm, b.cs, b.qsh) b on a.lddm = b.lddm ");
		sql.append("and a.cs = b.cs and a.qsh = b.qsh ");

		sql.append("left join (select c.lddm, c.cs, c.qsh, count(1) yzrs ");
		sql.append("from xszsxxb c group by c.lddm, c.cs, c.qsh) c on a.lddm = c.lddm ");
		sql.append("and a.cs = c.cs and a.qsh = c.qsh ");
		sql.append(") a ");
		sql.append("where 1 = 1 and fpbj='一般' ");
		
		// 分配方式为：学校->学院->班级
		if ("2".equalsIgnoreCase(fpfs)) {
			if ("xx".equalsIgnoreCase(userStatus)) {
				sql.append(" and fpbm is null ");
			} else {
				sql.append(" and bmdm ='"+userDep+"' ");
			}
		} else {
			sql.append(" and fpbm is null ");
		}
		
		
		sql.append("order by a.lddm,a.cs,a.qsh ");
		
		DAO dao = DAO.getInstance();
	
		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] { lddm, cs,lddm, cs },
				new String[] { "lddm","ldmc", "cs", "qsh", "cws", "xb", "kfhz",
						"fpbm", "fpbj","fpdx", "bkzrcws", "yzrs","rzzt","bmdm","nj" });
		
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				HashMap<String, String> map = list.get(i);

				String cws = map.get("cws");
				// 不可住人床位数
				String bkzrcws = map.get("bkzrcws");
				// 已住人数
				String yzrs = map.get("yzrs");
				
				String rzzt = "";

				if ("0".equalsIgnoreCase(yzrs)) {// 已住人数为0
					rzzt = "空的";
				} else if (cws.equalsIgnoreCase(String
						.valueOf(Integer.parseInt(bkzrcws)
								+ Integer.parseInt(yzrs)))) {// 已住人数+不可住人=寝室床位
					rzzt = "满的";
				} else {// 其他（错误数据不管）
					rzzt = "闲的";
				}
				
				list.get(i).put("rzzt", rzzt);
			}
		}
		
		return list;
	}
	
	
	/**
	 * 获得部门列表
	 * 
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getBmList(String[] bmInfo,
			String userStatus, String userName, String userDep) {

		String dm = "";
		String mc = "";
		String sjdm = "";
		
		String bmlx = bmInfo[0];
		String nj = bmInfo[1];
		String bmdm = bmInfo[2];
		
		if ("nj".equalsIgnoreCase(bmlx)) {
			dm = bmlx;
			mc = bmlx;
			sjdm = "nj";
		} else if ("xy".equalsIgnoreCase(bmlx)) {
			dm = bmlx + "dm";
			mc = bmlx + "mc";
			sjdm = "nj";
		} else if ("zy".equalsIgnoreCase(bmlx)) {
			dm = bmlx + "dm";
			mc = bmlx + "mc";
			sjdm = "xydm";
		} else if ("bj".equalsIgnoreCase(bmlx)) {
			dm = bmlx + "dm";
			mc = bmlx + "mc";
			sjdm = "zydm";
		}

		StringBuilder sql = new StringBuilder();
		sql.append("select distinct ");
		sql.append(dm);
		sql.append(" dm, ");
		sql.append("case when length("+mc+") > 6 then substr("+mc+", 0, 6) || '...' else  to_char("+mc+") end ");
		sql.append(" mc, ");
		sql.append(mc);
		sql.append(" allmc, ");
		sql.append(Base.isNull(bmdm) ? "''" : sjdm);
		sql.append(" sjdm, ");
		sql.append("'" + bmlx + "' ");
		sql.append(" bmlx,");
		sql.append(Base.isNull(bmdm) ? "'' nj " : "nj ");
		sql.append(" from view_njxyzybj a ");
		sql.append(" where 1 = 1 ");
		sql.append(Base.isNull(nj) ? "" : " and nj = '" + nj + "'");
		sql.append(Base.isNull(bmdm) ? "" : " and " + sjdm + " = '" + bmdm + "'");
		
		if ("xy".equalsIgnoreCase(userStatus)) {// 访问用户为学院
			sql.append("and a.xydm = '" + userDep + "' ");

		} else if ("jd".equalsIgnoreCase(userStatus)) {// 访问用户为辅导员兼班主任

			sql.append(" and (exists (select 1 from bzrbbb b where ");
			sql.append(" a.bjdm =  b.bjdm ");
			sql.append(" and b.zgh = '" + userName + "') ");

			sql.append(" or exists (select 1 from fdybjb b where ");
			sql.append(" a.bjdm =  b.bjdm ");
			sql.append(" and b.zgh = '" + userName + "')) ");

		} else if ("fdy".equalsIgnoreCase(userStatus)) {

			sql.append(" and exists (select 1 from fdybjb b where ");
			sql.append(" a.bjdm =  b.bjdm ");
			sql.append(" and b.zgh = '" + userName + "') ");

		} else if ("bzr".equalsIgnoreCase(userStatus)) {

			sql.append(" and exists (select 1 from bzrbbb b where ");
			sql.append(" a.bjdm =  b.bjdm ");
			sql.append(" and b.zgh = '" + userName + "') ");

		}
		sql.append(" order by dm,mc ");

		// 输出值
		String[] colList = new String[] { "dm", "mc", "allmc", "sjdm", "bmlx",
				"nj" };

		DAO dao = DAO.getInstance();

		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] {}, colList);

		return list;
	}
	
	/**
	 * 获得部门统计信息(寝室分配部门信息统计，前台DWR)
	 * 
	 * @author 伟大的骆
	 * 
	 */
	public List<HashMap<String, String>> getBmtjInfoForQsfp(String[] pk,
			String fpdx) {
		
		String[] nj = null;
		String[] xy = null;
		String[] zy = null;
		String[] bj = null;
		String[] outputValue = null;
		
		if (pk != null && pk.length > 0) {
			nj = new String[pk.length];
			xy = new String[pk.length];
			zy = new String[pk.length];
			bj = new String[pk.length];
			if ("xy".equalsIgnoreCase(fpdx)) {// 分配对象为学院
				xy = pk;
			} else if ("njxy".equalsIgnoreCase(fpdx)) {// 分配对象为年级+学院
				for (int i = 0; i < pk.length; i++) {
					String[] arr_value = pk[i].split("!!@@!!");
					nj[i] = arr_value[0];
					xy[i] = arr_value[1];
				}
			} else if ("njzy".equalsIgnoreCase(fpdx)) {// 分配对象为年级+专业
				for (int i = 0; i < pk.length; i++) {
					String[] arr_value = pk[i].split("!!@@!!");
					nj[i] = arr_value[0];
					zy[i] = arr_value[1];
				}
			} else {// 分配对象为班级
				bj = pk;
			}
		}
		
		StringBuilder sql = new StringBuilder();
		sql.append("select nvl(b.man,0) man,nvl(c.woman,0) woman, ");
		sql.append("nvl(d.manqs, 0) || '(' || nvl(d.mancws, 0) || ')' manqs, ");
		sql.append("nvl(e.womanqs, 0) || '(' || nvl(e.womancws, 0) || ')' womanqs");
		if ("xy".equalsIgnoreCase(fpdx)) {// 分配对象为学院
			sql.append(" ,xymc ");
			outputValue = new String[] { "xymc", "man", "woman", "manqs",
					"womanqs" };
		} else if ("njxy".equalsIgnoreCase(fpdx)) {// 分配对象为年级+学院
			sql.append(" ,a.nj,xymc ");
			outputValue = new String[] { "nj", "xymc", "man", "woman", "manqs",
					"womanqs" };
		} else if ("njzy".equalsIgnoreCase(fpdx)) {// 分配对象为年级+专业
			sql.append(" ,a.nj,zymc ");
			outputValue = new String[] { "nj", "zymc", "man", "woman", "manqs",
					"womanqs" };
		} else {// 分配对象为班级
			sql.append(" ,a.nj,bjmc ");
			outputValue = new String[] { "bjmc", "man", "woman", "manqs",
					"womanqs" };
		}
		sql.append(" from ");
		
		//======================部门信息===========================
		sql.append("(");
		sql.append("select distinct ");
		if ("xy".equalsIgnoreCase(fpdx)) {// 分配对象为学院
			sql.append(" xydm, xymc ");
		} else if ("njxy".equalsIgnoreCase(fpdx)) {// 分配对象为年级+学院
			sql.append(" nj,xydm, xymc ");
		} else if ("njzy".equalsIgnoreCase(fpdx)) {// 分配对象为年级+专业
			sql.append(" nj,zydm,zymc ");
		} else {// 分配对象为班级
			sql.append(" nj,bjdm,bjmc ");
		}
		sql.append("from view_njxyzybj ");
		sql.append("where 1 = 1 ");
		sql.append(" and ( ");
		if ("xy".equalsIgnoreCase(fpdx)) {// 分配对象为学院
			for(int i=0;i<xy.length;i++){
				if(i!=0){
					sql.append(" or ");
				}
				sql.append(" xydm = '"+xy[i]+"' ");
			}
		} else if ("njxy".equalsIgnoreCase(fpdx)) {// 分配对象为年级+学院
			for(int i=0;i<xy.length;i++){
				if(i!=0){
					sql.append(" or ");
				}
				sql.append(" (nj = '"+nj[i]+"' ");
				sql.append(" and xydm = '"+xy[i]+"') ");
			}
		} else if ("njzy".equalsIgnoreCase(fpdx)) {// 分配对象为年级+专业
			for(int i=0;i<zy.length;i++){
				if(i!=0){
					sql.append(" or ");
				}
				sql.append(" (nj = '"+nj[i]+"' ");
				sql.append(" and zydm = '"+zy[i]+"') ");
			}
		} else {// 分配对象为班级
			for(int i=0;i<bj.length;i++){
				if(i!=0){
					sql.append(" or ");
				}
				sql.append(" bjdm = '"+bj[i]+"' ");
			}
		}
		sql.append(" ) ");
		sql.append(") a ");
		// ======================部门人数===========================
		sql.append("left join ( ");
		sql.append("select ");
		if ("xy".equalsIgnoreCase(fpdx)) {// 分配对象为学院
			sql.append(" xydm, ");
		} else if ("njxy".equalsIgnoreCase(fpdx)) {// 分配对象为年级+学院
			sql.append(" nj,xydm, ");
		} else if ("njzy".equalsIgnoreCase(fpdx)) {// 分配对象为年级+专业
			sql.append(" nj,zydm, ");
		} else {// 分配对象为班级
			sql.append(" nj,bjdm, ");
		}
		sql.append("count(1) man from view_xsjbxx ");
		sql.append("where xb = '男' ");
		if ("xy".equalsIgnoreCase(fpdx)) {// 分配对象为学院
			sql.append(" and ( ");
			for(int i=0;i<xy.length;i++){
				if(i!=0){
					sql.append(" or ");
				}
				sql.append(" xydm = '"+xy[i]+"' ");
			}
			sql.append(" ) ");
			sql.append(" group by xydm) b ");
			sql.append(" on a.xydm =b.xydm ");
		} else if ("njxy".equalsIgnoreCase(fpdx)) {// 分配对象为年级+学院
			sql.append(" and ( ");
			for(int i=0;i<xy.length;i++){
				if(i!=0){
					sql.append(" or ");
				}
				sql.append(" (nj = '"+nj[i]+"' ");
				sql.append(" and xydm = '"+xy[i]+"') ");
			}
			sql.append(" ) ");
			sql.append(" group by nj,xydm) b ");
			sql.append(" on a.nj =b.nj ");
			sql.append(" and a.xydm =b.xydm ");
		} else if ("njzy".equalsIgnoreCase(fpdx)) {// 分配对象为年级+专业
			sql.append(" and ( ");
			for(int i=0;i<zy.length;i++){
				if(i!=0){
					sql.append(" or ");
				}
				sql.append(" (nj = '"+nj[i]+"' ");
				sql.append(" and zydm = '"+zy[i]+"') ");
			}
			sql.append(" ) ");
			sql.append(" group by nj,zydm) b ");
			sql.append(" on a.nj =b.nj ");
			sql.append(" and a.zydm =b.zydm ");
		} else {// 分配对象为班级
			sql.append(" and ( ");
			for(int i=0;i<bj.length;i++){
				if(i!=0){
					sql.append(" or ");
				}
				sql.append(" bjdm = '"+bj[i]+"' ");
			}
			sql.append(" ) ");
			sql.append(" group by nj,bjdm) b ");
			sql.append(" on a.bjdm =b.bjdm ");
		}
		
		sql.append("left join ( ");
		sql.append("select ");
		if ("xy".equalsIgnoreCase(fpdx)) {// 分配对象为学院
			sql.append(" xydm, ");
		} else if ("njxy".equalsIgnoreCase(fpdx)) {// 分配对象为年级+学院
			sql.append(" nj,xydm, ");
		} else if ("njzy".equalsIgnoreCase(fpdx)) {// 分配对象为年级+专业
			sql.append(" nj,zydm, ");
		} else {// 分配对象为班级
			sql.append(" nj,bjdm, ");
		}
		sql.append("count(1) woman from view_xsjbxx ");
		sql.append("where xb = '女' ");
		if ("xy".equalsIgnoreCase(fpdx)) {// 分配对象为学院
			sql.append(" and ( ");
			for(int i=0;i<xy.length;i++){
				if(i!=0){
					sql.append(" or ");
				}
				sql.append(" xydm = '"+xy[i]+"' ");
			}
			sql.append(" ) ");
			sql.append(" group by xydm) c ");
			sql.append(" on a.xydm =c.xydm ");
		} else if ("njxy".equalsIgnoreCase(fpdx)) {// 分配对象为年级+学院
			sql.append(" and ( ");
			for(int i=0;i<xy.length;i++){
				if(i!=0){
					sql.append(" or ");
				}
				sql.append(" (nj = '"+nj[i]+"' ");
				sql.append(" and xydm = '"+xy[i]+"') ");
			}
			sql.append(" ) ");
			sql.append(" group by nj,xydm) c ");
			sql.append(" on a.nj =c.nj ");
			sql.append(" and a.xydm =c.xydm ");
		} else if ("njzy".equalsIgnoreCase(fpdx)) {// 分配对象为年级+专业
			sql.append(" and ( ");
			for(int i=0;i<zy.length;i++){
				if(i!=0){
					sql.append(" or ");
				}
				sql.append(" (nj = '"+nj[i]+"' ");
				sql.append(" and zydm = '"+zy[i]+"') ");
			}
			sql.append(" ) ");
			sql.append(" group by nj,zydm) c ");
			sql.append(" on a.nj =c.nj ");
			sql.append(" and a.zydm =c.zydm ");
		} else {// 分配对象为班级
			sql.append(" and ( ");
			for(int i=0;i<bj.length;i++){
				if(i!=0){
					sql.append(" or ");
				}
				sql.append(" bjdm = '"+bj[i]+"' ");
			}
			sql.append(" ) ");
			sql.append(" group by nj,bjdm) c ");
			sql.append(" on a.bjdm =c.bjdm ");
		}
		
		// ======================已分配寝室数===========================
		sql.append("left join ( ");
		sql.append("select ");
		if ("xy".equalsIgnoreCase(fpdx)) {// 分配对象为学院
			sql.append(" b.bmdm, ");
		} else {
			sql.append(" b.nj,b.bmdm, ");
		}
		sql.append("count(1) manqs,sum(a.cws) mancws from ssxxb a, xg_gygl_qsfpb b ");
		sql.append("where a.lddm = b.lddm ");
		sql.append("and a.cs = b.cs ");
		sql.append("and a.qsh = b.qsh ");
		sql.append("and xb = '男' ");
		if ("xy".equalsIgnoreCase(fpdx)) {// 分配对象为学院
			sql.append(" and ( ");
			for(int i=0;i<xy.length;i++){
				if(i!=0){
					sql.append(" or ");
				}
				sql.append(" b.bmdm = '"+xy[i]+"' ");
			}
			sql.append(" ) ");
			sql.append(" group by b.bmdm) d ");
			sql.append(" on a.xydm =d.bmdm ");
		} else if ("njxy".equalsIgnoreCase(fpdx)) {// 分配对象为年级+学院
			sql.append(" and ( ");
			for(int i=0;i<xy.length;i++){
				if(i!=0){
					sql.append(" or ");
				}
				sql.append(" (b.bmdm = '"+xy[i]+"' ");
				sql.append(" and b.nj = '"+nj[i]+"') ");
			}
			sql.append(" ) ");
			sql.append(" group by b.bmdm,b.nj) d ");
			sql.append(" on a.xydm =d.bmdm ");
			sql.append(" and a.nj =d.nj ");
		} else if ("njzy".equalsIgnoreCase(fpdx)) {// 分配对象为年级+专业
			sql.append(" and ( ");
			for(int i=0;i<xy.length;i++){
				if(i!=0){
					sql.append(" or ");
				}
				sql.append(" (b.bmdm = '"+zy[i]+"' ");
				sql.append(" and b.nj = '"+nj[i]+"') ");
			}
			sql.append(" ) ");
			sql.append(" group by b.bmdm,b.nj) d ");
			sql.append(" on a.zydm =d.bmdm ");
			sql.append(" and a.nj =d.nj ");
		} else {// 分配对象为班级
			sql.append(" and ( ");
			for(int i=0;i<xy.length;i++){
				if(i!=0){
					sql.append(" or ");
				}
				sql.append(" b.bmdm = '"+bj[i]+"' ");
			}
			sql.append(" ) ");
			sql.append(" group by b.nj,b.bmdm) d ");
			sql.append(" on a.bjdm =d.bmdm ");
		}
		
		sql.append("left join ( ");
		sql.append("select ");
		if ("xy".equalsIgnoreCase(fpdx)) {// 分配对象为学院
			sql.append(" b.bmdm, ");
		} else {
			sql.append(" b.nj,b.bmdm, ");
		}
		sql.append("count(1) womanqs,sum(a.cws) womancws from ssxxb a, xg_gygl_qsfpb b ");
		sql.append("where a.lddm = b.lddm ");
		sql.append("and a.cs = b.cs ");
		sql.append("and a.qsh = b.qsh ");
		sql.append("and xb = '女' ");
		if ("xy".equalsIgnoreCase(fpdx)) {// 分配对象为学院
			sql.append(" and ( ");
			for(int i=0;i<xy.length;i++){
				if(i!=0){
					sql.append(" or ");
				}
				sql.append(" b.bmdm = '"+xy[i]+"' ");
			}
			sql.append(" ) ");
			sql.append(" group by b.bmdm) e ");
			sql.append(" on a.xydm =e.bmdm ");
		} else if ("njxy".equalsIgnoreCase(fpdx)) {// 分配对象为年级+学院
			sql.append(" and ( ");
			for(int i=0;i<xy.length;i++){
				if(i!=0){
					sql.append(" or ");
				}
				sql.append(" (b.bmdm = '"+xy[i]+"' ");
				sql.append(" and b.nj = '"+nj[i]+"') ");
			}
			sql.append(" ) ");
			sql.append(" group by b.bmdm,b.nj) e ");
			sql.append(" on a.xydm =e.bmdm ");
			sql.append(" and a.nj =e.nj ");
		} else if ("njzy".equalsIgnoreCase(fpdx)) {// 分配对象为年级+专业
			sql.append(" and ( ");
			for(int i=0;i<xy.length;i++){
				if(i!=0){
					sql.append(" or ");
				}
				sql.append(" (b.bmdm = '"+zy[i]+"' ");
				sql.append(" and b.nj = '"+nj[i]+"') ");
			}
			sql.append(" ) ");
			sql.append(" group by b.bmdm,b.nj) e ");
			sql.append(" on a.zydm =e.bmdm ");
			sql.append(" and a.nj =e.nj ");
		} else {// 分配对象为班级
			sql.append(" and ( ");
			for(int i=0;i<xy.length;i++){
				if(i!=0){
					sql.append(" or ");
				}
				sql.append(" b.bmdm = '"+bj[i]+"' ");
			}
			sql.append(" ) ");
			sql.append(" group by b.nj,b.bmdm) e ");
			sql.append(" on a.bjdm =e.bmdm ");
		}
		
		if ("xy".equalsIgnoreCase(fpdx)) {// 分配对象为学院
			sql.append(" order by a.xydm ");
		} else if ("njxy".equalsIgnoreCase(fpdx)) {// 分配对象为年级+学院
			sql.append(" order by a.nj,a.xydm ");
		} else if ("njzy".equalsIgnoreCase(fpdx)) {// 分配对象为年级+专业
			sql.append(" order by a.nj,a.zydm ");
		} else {// 分配对象为班级
			sql.append(" order by a.nj,a.bjdm ");
		}
		
		DAO dao = DAO.getInstance();
		
		List<HashMap<String,String>> list = dao.getList(sql.toString(), new String[]{}, outputValue);
		
		return list;
		
	}
	
	/**
	 * 判断输入值是否在表中的指定字段中存在
	 * @param tableName
	 * @param zdm
	 * @param zdz
	 * @author 今天中五百万
	 * @return boolean(存在返回false,不存在返回true)
	 */
	public boolean checkPkValue(String tableName, String zdm, String zdz) {

		DAO dao = DAO.getInstance();
		StringBuilder sql=new  StringBuilder();
		sql.append("(select count(1)num,"+zdm+" zdm from "+tableName+" group by "+zdm+" )");
		String num = dao.getOneValue(sql.toString(), "num", "zdm", zdz);

		if (!Base.isNull(num) && !"0".equalsIgnoreCase(num)) {

			return false;

		} else {

			return true;

		}
	}
}