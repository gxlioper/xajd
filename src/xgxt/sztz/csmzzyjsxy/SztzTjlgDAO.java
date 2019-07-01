package xgxt.sztz.csmzzyjsxy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.utils.CommonQueryDAO;

public class SztzTjlgDAO {

	public List<String[]> getTzcjTj(HashMap<String, String> stuInfo) {

		StringBuilder sql = new StringBuilder();
		String nj = stuInfo.get("nj");
		// 根据学年、学期统计

		sql.append("(select " );
		//对小数点数字处理
		sql.append("  nvl(case when instr(to_char(zf),'.',1,1)=1 then '0'||to_char(zf) else to_char(zf) end, '0')zf, ");
		sql.append("  nvl(case when instr(to_char(fs001),'.',1,1)=1 then '0'||to_char(fs001) else to_char(fs001) end, '0')fs001, ");
		sql.append("  nvl(case when instr(to_char(fs002),'.',1,1)=1 then '0'||to_char(fs002) else to_char(fs002) end, '0')fs002, ");
		sql.append("  nvl(case when instr(to_char(fs101),'.',1,1)=1 then '0'||to_char(fs101) else to_char(fs101) end, '0')fs101, ");
		sql.append("  nvl(case when instr(to_char(fs102),'.',1,1)=1 then '0'||to_char(fs102) else to_char(fs102) end, '0')fs102, ");
		sql.append("  nvl(case when instr(to_char(fs201),'.',1,1)=1 then '0'||to_char(fs201) else to_char(fs201) end, '0')fs201, ");
		sql.append("  nvl(case when instr(to_char(fs202),'.',1,1)=1 then '0'||to_char(fs202) else to_char(fs202) end, '0')fs202, ");
		sql.append("  nvl(case when instr(to_char(fs301),'.',1,1)=1 then '0'||to_char(fs301) else to_char(fs301) end, '0')fs301, ");
		sql.append("  nvl(case when instr(to_char(fs302),'.',1,1)=1 then '0'||to_char(fs302) else to_char(fs302) end, '0')fs302, ");
		sql.append(" kmm, ");
		sql.append(" xmmc ");
		sql.append("from (select kmm,xmmc,");
		sql.append(" (sum(fs001)+sum(fs002)+sum(fs101)+sum(fs102)+sum(fs201)+sum(fs202)+sum(fs301)+sum(fs302))zf, ");
		sql.append(" xh,sum(fs001)fs001,sum(fs002)fs002,sum(fs101)fs101,sum(fs102)fs102,sum(fs201)fs201,sum(fs202)fs202,sum(fs301)fs301,sum(fs302)fs302  ");
		sql.append(" from ( select kmm, xmmc,  kmdm, ");
		List<HashMap<String, String>> xnxqList = getXnXqList(nj);
		for (int i = 0; i < xnxqList.size(); i++) {
			HashMap<String, String> xnxqMap = xnxqList.get(i);
			sql.append(" case when xn = '" + xnxqMap.get("xn") + "' and xq = '"
					+ xnxqMap.get("oneXq") + "' then  xf else  0  end fs"
					+ i + xnxqMap.get("oneXq") + ", ");
			sql.append(" case when xn = '" + xnxqMap.get("xn") + "' and xq = '"
					+ xnxqMap.get("twoXq") + "' then  xf else  0  end fs"
					+ i + xnxqMap.get("twoXq") + ", ");
		}
		sql.append(" xh ");
		sql.append(" from (select distinct (a.kmm || a.xmmc || b.xh),a.kmm,a.xmmc,a.kmdm,b.xh,b.xn,b.xq,b.xqmc,b.xf");
		sql.append(" from (select kmm, xmmc, b.kmdm from (select xmmc, kmdm from csmz_tzxmb group by xmmc, kmdm) a,sztz_kmdmb b  where a.kmdm = b.kmdm) a ");
		sql.append("left join (select * from  (select * from( select xh,xn,xq,xqmc,xmmc,kmdm,sum(xf)xf from view_tzcgcjxx group by xn,xq,xqmc,xmmc,kmdm ,xh )) where xh = '"
				+ stuInfo.get("xh")
				+ "') b on a.kmdm = b.kmdm  and a.xmmc =b.xmmc");
		sql.append(" order by a.kmdm, a.xmmc) a)group by kmm,xmmc,kmdm,xh) order by kmm,xmmc) ");
		System.out.println(sql);
		return CommonQueryDAO.commonQueryNotFy(sql.toString(), "",
				new String[] {}, new String[] { "kmm", "xmmc", "zf", "fs001",
						"fs002", "fs101", "fs102", "fs201", "fs202", "fs301",
						"fs302" }, null);
	}
	
	public List<String[]>getZfList(HashMap<String, String> stuInfo){
		StringBuilder sql = new StringBuilder();
		String nj = stuInfo.get("nj");
		// 根据学年、学期统计
		sql.append("(select " );
		//对小数点数字处理
		sql.append("  nvl(case when instr(to_char(zf001),'.',1,1)=1 then '0'||to_char(zf001) else to_char(zf001) end, '0')zf001, ");
		sql.append("  nvl(case when instr(to_char(zf002),'.',1,1)=1 then '0'||to_char(zf002) else to_char(zf002) end, '0')zf002, ");
		sql.append("  nvl(case when instr(to_char(zf101),'.',1,1)=1 then '0'||to_char(zf101) else to_char(zf101) end, '0')zf101, ");
		sql.append("  nvl(case when instr(to_char(zf102),'.',1,1)=1 then '0'||to_char(zf102) else to_char(zf102) end, '0')zf102, ");
		sql.append("  nvl(case when instr(to_char(zf201),'.',1,1)=1 then '0'||to_char(zf201) else to_char(zf201) end, '0')zf201, ");
		sql.append("  nvl(case when instr(to_char(zf202),'.',1,1)=1 then '0'||to_char(zf202) else to_char(zf202) end, '0')zf202, ");
		sql.append("  nvl(case when instr(to_char(zf301),'.',1,1)=1 then '0'||to_char(zf301) else to_char(zf301) end, '0')zf301, ");
		sql.append("  nvl(case when instr(to_char(zf302),'.',1,1)=1 then '0'||to_char(zf302) else to_char(zf302) end, '0')zf302, ");
		sql.append("  nvl(case when instr(to_char(zf),'.',1,1)=1 then '0'||to_char(zf) else to_char(zf) end, '0')zf ");
		sql.append("from ( select sum(zf)zf,sum(fs001)zf001,sum(fs002)zf002,sum(fs101)zf101,sum(fs102)zf102,sum(fs201)zf201,sum(fs202)zf202,sum(fs301)zf301,sum(fs302)zf302 from  ");
		sql.append(" (select kmm,xmmc,");
		sql.append(" (sum(fs001)+sum(fs002)+sum(fs101)+sum(fs102)+sum(fs201)+sum(fs202)+sum(fs301)+sum(fs302))zf, ");
		sql.append(" xh,sum(fs001)fs001,sum(fs002)fs002,sum(fs101)fs101,sum(fs102)fs102,sum(fs201)fs201,sum(fs202)fs202,sum(fs301)fs301,sum(fs302)fs302  ");
		sql.append(" from ( select kmm, xmmc,  kmdm, ");
		List<HashMap<String, String>> xnxqList = getXnXqList(nj);
		for (int i = 0; i < xnxqList.size(); i++) {
			HashMap<String, String> xnxqMap = xnxqList.get(i);
			sql.append(" case when xn = '" + xnxqMap.get("xn") + "' and xq = '"
					+ xnxqMap.get("oneXq") + "' then  xf else  0  end fs"
					+ i + xnxqMap.get("oneXq") + ", ");
			sql.append(" case when xn = '" + xnxqMap.get("xn") + "' and xq = '"
					+ xnxqMap.get("twoXq") + "' then  xf else  0  end fs"
					+ i + xnxqMap.get("twoXq") + ", ");
		}
		sql.append(" xh ");
		sql.append(" from (select distinct (a.kmm || a.xmmc || b.xh),a.kmm,a.xmmc,a.kmdm,b.xh,b.xn,b.xq,b.xqmc,b.xf");
		sql.append(" from (select kmm, xmmc, b.kmdm from (select xmmc, kmdm from csmz_tzxmb group by xmmc, kmdm) a,sztz_kmdmb b  where a.kmdm = b.kmdm) a ");
		sql.append("left join (select * from  (select * from( select xh,xn,xq,xqmc,xmmc,kmdm,sum(xf)xf from view_tzcgcjxx group by xn,xq,xqmc,xmmc,kmdm ,xh )) where xh = '"
				+ stuInfo.get("xh")
				+ "') b on a.kmdm = b.kmdm  and a.xmmc =b.xmmc");
		sql.append(" order by a.kmdm, a.xmmc) a)group by kmm,xmmc,kmdm,xh)))");

		return CommonQueryDAO.commonQueryNotFy(sql.toString(), "",
				new String[] {}, new String[] {"zf", "zf001",
						"zf002", "zf101", "zf102", "zf201", "zf202", "zf301",
						"zf302" }, null);
	}
	
	/**
	 * 学年学期行获取
	 * @param nj
	 * @return List<HashMap<String, String>> 
	 */
	public List<HashMap<String, String>> getXnXqList(String nj) {
		int xzInt = 4;
		int njInt=Integer.parseInt(nj);
		List<HashMap<String, String>> xnxqList = new ArrayList<HashMap<String, String>>();
		for (int i = 0; i < xzInt; i++) {

			HashMap<String, String> xnxqMap = new HashMap<String, String>();
			xnxqMap.put("xn", "" + njInt + "-" + (njInt + 1));
			xnxqMap.put("oneXq", "01");
			xnxqMap.put("twoXq", "02");
			xnxqList.add(xnxqMap);
			njInt++;
		}
		return xnxqList;
	}
	
	/**
	 * 根据PK值获取学号
	 * @param pk
	 * @return HashMap<String,String>
	 */
	public HashMap<String,String>getTzxsxx(String pk){
		DAO dao =DAO.getInstance();
		String sql=" select * from view_tzcgcjxx where pk=? ";
		return dao.getMap(sql, new String[]{pk}, new String[]{"xh"});
	}
}
