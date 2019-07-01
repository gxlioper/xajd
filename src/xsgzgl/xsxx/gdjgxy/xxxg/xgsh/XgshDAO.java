package xsgzgl.xsxx.gdjgxy.xxxg.xgsh;


import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 學生信息_信息修改_修改審核_通用_DAO类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author 伟大的骆
 * @version 1.0
 */

public class XgshDAO extends xsgzgl.xsxx.general.xxxg.xgsh.XgshDAO {
	

	/**
	 * 获得學生修改信息
	 * 
	 * @date 2013-01-24
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getXgxxList(String sqid,
			String[] colValue) {

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();

		sql.append("select a.xh,a.xm,a.csrq,a.xz,b.nj,b.xymc,b.zymc, ");
		sql.append("b.bjmc,a.xjztm,a.rxrq,a.sfzh, ");
		sql.append("decode(a.xb, '1', '男', '2', '女', a.xb) xb, ");
		sql
				.append("(select c.zzmmmc from zzmmdmb c where c.zzmmdm = a.zzmm) zzmmmc, ");
		sql.append("(select c.mzmc from mzdmb c where c.mzdm = a.mz) mzmc, ");
		// 籍貫
		sql.append("case when substr(a.jg, 3, 4) = '0000' then ");
		sql.append("(select c.qxmc from dmk_qx c where c.qxdm = a.jg) ");
		sql.append("when substr(a.jg, 5, 2) = '00' then ");
		sql.append("(select c.qxmc from dmk_qx c ");
		sql.append("where c.qxdm = substr(a.jg, 1, 2) || '0000') || ' ' || ");
		sql.append("(select c.qxmc from dmk_qx c where c.qxdm = a.jg) else ");
		sql.append("(select c.qxmc from dmk_qx c ");
		sql.append("where c.qxdm = substr(a.jg, 1, 2) || '0000') || ' ' || ");
		sql.append("(select c.qxmc from dmk_qx c ");
		sql.append("where c.qxdm = substr(a.jg, 1, 4) || '00') || ' ' || ");
		sql
				.append("(select c.qxmc from dmk_qx c where c.qxdm = a.jg) end jgmc, ");
		// 戶口所在地
		sql.append("case when substr(a.hkszd, 3, 4) = '0000' then ");
		sql.append("(select c.qxmc from dmk_qx c where c.qxdm = a.hkszd) ");
		sql.append("when substr(a.hkszd, 5, 2) = '00' then ");
		sql.append("(select c.qxmc from dmk_qx c ");
		sql
				.append("where c.qxdm = substr(a.hkszd, 1, 2) || '0000') || ' ' || ");
		sql.append("(select c.qxmc from dmk_qx c where c.qxdm = a.hkszd) ");
		sql.append("else (select c.qxmc from dmk_qx c ");
		sql
				.append("where c.qxdm = substr(a.hkszd, 1, 2) || '0000') || ' ' || ");
		sql.append("(select c.qxmc from dmk_qx c ");
		sql.append("where c.qxdm = substr(a.hkszd, 1, 4) || '00') || ' ' || ");
		sql
				.append("(select c.qxmc from dmk_qx c where c.qxdm = a.hkszd) end hkszdmc, ");
		// 生源地
		sql.append("case when substr(a.syd, 3, 4) = '0000' then ");
		sql.append("(select c.qxmc from dmk_qx c where c.qxdm = a.syd) ");
		sql.append("when substr(a.syd, 5, 2) = '00' then ");
		sql.append("(select c.qxmc from dmk_qx c ");
		sql.append("where c.qxdm = substr(a.syd, 1, 2) || '0000') || ' ' || ");
		sql.append("(select c.qxmc from dmk_qx c where c.qxdm = a.syd) ");
		sql.append("else (select c.qxmc from dmk_qx c ");
		sql.append("where c.qxdm = substr(a.syd, 1, 2) || '0000') || ' ' || ");
		sql.append("(select c.qxmc from dmk_qx c ");
		sql.append("where c.qxdm = substr(a.syd, 1, 4) || '00') || ' ' || ");
		sql
				.append("(select c.qxmc from dmk_qx c where c.qxdm = a.syd) end sydmc, ");
		// 聯繫方式
		sql.append("a.sjhm,a.dzyx,a.qqhm,a.jtdh,d.yb jtyb,d.jtszd, ");
		sql
				.append("d.jtcy1_xm,d.jtcy1_gx,d.jtcy1_gzdz,d.jtcy1_lxdh2,d.jtcy1_lxdh1, ");
		sql
				.append("d.jtcy2_xm,d.jtcy2_gx,d.jtcy2_gzdz,d.jtcy2_lxdh2,d.jtcy2_lxdh1, ");
		sql
				.append("d.jtcy3_xm,d.jtcy3_gx,d.jtcy3_gzdz,d.jtcy3_lxdh2,d.jtcy3_lxdh1, ");
		// 其他信息
		sql.append("a.xmpy,a.cym,a.sg,a.tz,a.tc,a.jkzk,a.sfzd,a.bz, a.zd1,a.zd2,a.grjl,a.zw,");
		sql
				.append("(select c.pyccmc from xg_xsxx_pyccdmb c where c.pyccdm = a.pycc) pyccmc, ");
		sql
				.append("(select c.kslbmc from xg_xsxx_kslbdmb c where c.kslbdm = a.kslb) kslbmc, ");
		sql
				.append("(select c.rxfsmc from xg_xsxx_rxfsdmb c where c.rxfsdm = a.rxfs) rxfsmc, ");
		sql
				.append("(select c.pyfsmc from xg_xsxx_pyfsdmb c where c.pyfsdm = a.pyfs) pyfsmc ");
		sql.append("from xsxxb a ");
		sql.append("left join view_njxyzybj_all b on a.bjdm = b.bjdm ");
		sql.append("left join xsfzxxb d on a.xh = d.xh ");
		sql.append("where a.xh = (select xh from xg_xsxx_xxxgsqb ");
		sql.append("where sqid = ?) ");

		sql.append(" union all ");

		String xb_after = "substr(a.xb, 1, instr(a.xb, 'LiTT') - 1)";
		String xb_now = "substr(a.xb, instr(a.xb, 'LiTT') + 4, length(a.xb))";

		String zzmm_after = "substr(a.zzmm, 1, instr(a.zzmm, 'LiTT') - 1)";
		String zzmm_now = "substr(a.zzmm, instr(a.zzmm, 'LiTT') + 4, length(a.zzmm))";

		String mz_after = "substr(a.mz, 1 , instr(a.mz, 'LiTT') - 1)";
		String mz_now = "substr(a.mz, instr(a.mz, 'LiTT') + 4, length(a.mz))";

		String jg_after = "substr(a.jg, 1 , instr(a.jg, 'LiTT') - 1)";
		String jg_now = "substr(a.jg, instr(a.jg, 'LiTT') + 4, length(a.jg))";

		String hkszd_after = "substr(a.hkszd, 1 , instr(a.hkszd, 'LiTT') - 1)";
		String hkszd_now = "substr(a.hkszd, instr(a.hkszd, 'LiTT') + 4, length(a.hkszd))";

		String syd_after = "substr(a.syd, 1 , instr(a.syd, 'LiTT') - 1)";
		String syd_now = "substr(a.syd, instr(a.syd, 'LiTT') + 4, length(a.syd))";

		String pycc_after = "substr(a.pycc, 1, instr(a.pycc, 'LiTT') - 1)";
		String pycc_now = "substr(a.pycc, instr(a.pycc, 'LiTT') + 4, length(a.pycc))";

		String kslb_after = "substr(a.kslb,1, instr(a.kslb, 'LiTT') - 1 )";
		String kslb_now = "substr(a.kslb, instr(a.kslb, 'LiTT') + 4, length(a.kslb))";

		String rxfs_after = "substr(a.rxfs,1, instr(a.rxfs, 'LiTT') - 1)";
		String rxfs_now = "substr(a.rxfs, instr(a.rxfs, 'LiTT') + 4, length(a.rxfs))";

		String pyfs_after = "substr(a.pyfs, 1 ,instr(a.pyfs, 'LiTT') - 1 )";
		String pyfs_now = "substr(a.pyfs, instr(a.pyfs, 'LiTT') + 4, length(a.pyfs))";

		sql.append("select a.xh,a.xm,a.csrq,a.xz,b.nj,b.xymc,b.zymc, ");
		sql.append("b.bjmc,a.xjztm,a.rxrq,a.sfzh, ");
		// 性別
		sql.append("decode(a.xb,null,'',");
		sql.append("decode(" + xb_after + ", '1', '男', '2', '女', " + xb_after
				+ ") ");
		sql.append("||'LiTT'||");
		sql.append("decode(" + xb_now + ", '1', '男', '2', '女', " + xb_now
				+ ")) xb, ");
		// 政治面貌
		sql.append("decode(a.zzmm,null,'',");
		sql.append("(select c.zzmmmc from zzmmdmb c where c.zzmmdm = "
				+ zzmm_after + ") ");
		sql.append("||'LiTT'||");
		sql.append("(select c.zzmmmc from zzmmdmb c where c.zzmmdm = "
				+ zzmm_now + ")) zzmmmc, ");
		// 民族
		sql.append("decode(a.mz,null,'',");
		sql.append("(select c.mzmc from mzdmb c where c.mzdm = " + mz_after
				+ ") ");
		sql.append("||'LiTT'||");
		sql.append("(select c.mzmc from mzdmb c where c.mzdm = " + mz_now
				+ ")) mzmc, ");
		// 籍貫
		sql.append("decode(a.jg,null,'',");
		sql.append("case when substr(" + jg_after + ", 3, 4) = '0000' then ");
		sql.append("(select c.qxmc from dmk_qx c where c.qxdm = " + jg_after
				+ ") ");
		sql.append("when substr(" + jg_after + ", 5, 2) = '00' then ");
		sql.append("(select c.qxmc from dmk_qx c ");
		sql.append("where c.qxdm = substr(" + jg_after
				+ ", 1, 2) || '0000') || ' ' || ");
		sql.append("(select c.qxmc from dmk_qx c where c.qxdm = " + jg_after
				+ ") else ");
		sql.append("(select c.qxmc from dmk_qx c ");
		sql.append("where c.qxdm = substr(" + jg_after
				+ ", 1, 2) || '0000') || ' ' || ");
		sql.append("(select c.qxmc from dmk_qx c ");
		sql.append("where c.qxdm = substr(" + jg_after
				+ ", 1, 4) || '00') || ' ' || ");
		sql.append("(select c.qxmc from dmk_qx c where c.qxdm = " + jg_after
				+ ") end ");
		sql.append("||'LiTT'||");
		sql.append("case when substr(" + jg_now + ", 3, 4) = '0000' then ");
		sql.append("(select c.qxmc from dmk_qx c where c.qxdm = " + jg_now
				+ ") ");
		sql.append("when substr(" + jg_now + ", 5, 2) = '00' then ");
		sql.append("(select c.qxmc from dmk_qx c ");
		sql.append("where c.qxdm = substr(" + jg_now
				+ ", 1, 2) || '0000') || ' ' || ");
		sql.append("(select c.qxmc from dmk_qx c where c.qxdm = " + jg_now
				+ ") else ");
		sql.append("(select c.qxmc from dmk_qx c ");
		sql.append("where c.qxdm = substr(" + jg_now
				+ ", 1, 2) || '0000') || ' ' || ");
		sql.append("(select c.qxmc from dmk_qx c ");
		sql.append("where c.qxdm = substr(" + jg_now
				+ ", 1, 4) || '00') || ' ' || ");
		sql.append("(select c.qxmc from dmk_qx c where c.qxdm = " + jg_now
				+ ") end) jgmc, ");
		// 戶口所在地
		sql.append("decode(a.hkszd,null,'',");
		sql
				.append("case when substr(" + hkszd_after
						+ ", 3, 4) = '0000' then ");
		sql.append("(select c.qxmc from dmk_qx c where c.qxdm = " + hkszd_after
				+ ") ");
		sql.append("when substr(" + hkszd_after + ", 5, 2) = '00' then ");
		sql.append("(select c.qxmc from dmk_qx c ");
		sql.append("where c.qxdm = substr(" + hkszd_after
				+ ", 1, 2) || '0000') || ' ' || ");
		sql.append("(select c.qxmc from dmk_qx c where c.qxdm = " + hkszd_after
				+ ") ");
		sql.append("else (select c.qxmc from dmk_qx c ");
		sql.append("where c.qxdm = substr(" + hkszd_after
				+ ", 1, 2) || '0000') || ' ' || ");
		sql.append("(select c.qxmc from dmk_qx c ");
		sql.append("where c.qxdm = substr(" + hkszd_after
				+ ", 1, 4) || '00') || ' ' || ");
		sql.append("(select c.qxmc from dmk_qx c where c.qxdm = " + hkszd_after
				+ ") end  ");
		sql.append("||'LiTT'||");
		sql.append("case when substr(" + hkszd_now + ", 3, 4) = '0000' then ");
		sql.append("(select c.qxmc from dmk_qx c where c.qxdm = " + hkszd_now
				+ ") ");
		sql.append("when substr(" + hkszd_now + ", 5, 2) = '00' then ");
		sql.append("(select c.qxmc from dmk_qx c ");
		sql.append("where c.qxdm = substr(" + hkszd_now
				+ ", 1, 2) || '0000') || ' ' || ");
		sql.append("(select c.qxmc from dmk_qx c where c.qxdm = " + hkszd_now
				+ ") ");
		sql.append("else (select c.qxmc from dmk_qx c ");
		sql.append("where c.qxdm = substr(" + hkszd_now
				+ ", 1, 2) || '0000') || ' ' || ");
		sql.append("(select c.qxmc from dmk_qx c ");
		sql.append("where c.qxdm = substr(" + hkszd_now
				+ ", 1, 4) || '00') || ' ' || ");
		sql.append("(select c.qxmc from dmk_qx c where c.qxdm = " + hkszd_now
				+ ") end) hkszdmc, ");
		// 生源地
		sql.append("decode(a.syd,null,'',");
		sql.append("case when substr(" + syd_after + ", 3, 4) = '0000' then ");
		sql.append("(select c.qxmc from dmk_qx c where c.qxdm = " + syd_after
				+ ") ");
		sql.append("when substr(" + syd_after + ", 5, 2) = '00' then ");
		sql.append("(select c.qxmc from dmk_qx c ");
		sql.append("where c.qxdm = substr(" + syd_after
				+ ", 1, 2) || '0000') || ' ' || ");
		sql.append("(select c.qxmc from dmk_qx c where c.qxdm = " + syd_after
				+ ") ");
		sql.append("else (select c.qxmc from dmk_qx c ");
		sql.append("where c.qxdm = substr(" + syd_after
				+ ", 1, 2) || '0000') || ' ' || ");
		sql.append("(select c.qxmc from dmk_qx c ");
		sql.append("where c.qxdm = substr(" + syd_after
				+ ", 1, 4) || '00') || ' ' || ");
		sql.append("(select c.qxmc from dmk_qx c where c.qxdm = " + syd_after
				+ ") end ");
		sql.append("||'LiTT'||");
		sql.append("case when substr(" + syd_now + ", 3, 4) = '0000' then ");
		sql.append("(select c.qxmc from dmk_qx c where c.qxdm = " + syd_now
				+ ") ");
		sql.append("when substr(" + syd_now + ", 5, 2) = '00' then ");
		sql.append("(select c.qxmc from dmk_qx c ");
		sql.append("where c.qxdm = substr(" + syd_now
				+ ", 1, 2) || '0000') || ' ' || ");
		sql.append("(select c.qxmc from dmk_qx c where c.qxdm = " + syd_now
				+ ") ");
		sql.append("else (select c.qxmc from dmk_qx c ");
		sql.append("where c.qxdm = substr(" + syd_now
				+ ", 1, 2) || '0000') || ' ' || ");
		sql.append("(select c.qxmc from dmk_qx c ");
		sql.append("where c.qxdm = substr(" + syd_now
				+ ", 1, 4) || '00') || ' ' || ");
		sql.append("(select c.qxmc from dmk_qx c where c.qxdm = " + syd_now
				+ ") end) sydmc, ");

		// 聯繫方式
		sql.append("a.sjhm,a.dzyx,a.qqhm,a.jtdh,a.jtyb,a.jtszd, ");
		sql
				.append("a.jtcy1_xm,a.jtcy1_gx,a.jtcy1_gzdz,a.jtcy1_lxdh2,a.jtcy1_lxdh1, ");
		sql
				.append("a.jtcy2_xm,a.jtcy2_gx,a.jtcy2_gzdz,a.jtcy2_lxdh2,a.jtcy2_lxdh1, ");
		sql
				.append("a.jtcy3_xm,a.jtcy3_gx,a.jtcy3_gzdz,a.jtcy3_lxdh2,a.jtcy3_lxdh1, ");
		// 其他信息
		sql.append("a.xmpy,a.cym,a.sg,a.tz,a.tc,a.jkzk,a.sfzd,a.bz,a.zd1,a.zd2,a.grjl,a.zw, ");
		// 培養層次
		sql.append("decode(a.pycc,null,'',");
		sql.append("(select c.pyccmc from xg_xsxx_pyccdmb c where c.pyccdm = "
				+ pycc_after + ") ");
		sql.append("||'LiTT'||");
		sql.append("(select c.pyccmc from xg_xsxx_pyccdmb c where c.pyccdm = "
				+ pycc_now + ")) pyccmc, ");
		// 考生類別
		sql.append("decode(a.kslb,null,'',");
		sql.append("(select c.kslbmc from xg_xsxx_kslbdmb c where c.kslbdm = "
				+ kslb_after + ") ");
		sql.append("||'LiTT'||");
		sql.append("(select c.kslbmc from xg_xsxx_kslbdmb c where c.kslbdm = "
				+ kslb_now + ")) kslbmc, ");
		// 入學方式
		sql.append("decode(a.rxfs,null,'',");
		sql.append("(select c.rxfsmc from xg_xsxx_rxfsdmb c where c.rxfsdm = "
				+ rxfs_after + ") ");
		sql.append("||'LiTT'||");
		sql.append("(select c.rxfsmc from xg_xsxx_rxfsdmb c where c.rxfsdm = "
				+ rxfs_now + ")) rxfsmc, ");
		// 培養方式
		sql.append("decode(a.pyfs,null,'',");
		sql.append("(select c.pyfsmc from xg_xsxx_pyfsdmb c where c.pyfsdm = "
				+ pyfs_after + ") ");
		sql.append("||'LiTT'||");
		sql.append("(select c.pyfsmc from xg_xsxx_pyfsdmb c where c.pyfsdm = "
				+ pyfs_now + ")) pyfsmc ");

		sql.append("from xg_xsxx_xxxgzdxgb a ");
		sql.append("left join view_njxyzybj_all b on a.bjdm = b.bjdm ");
		//sql.append("left join xsfzxxb d on a.xh = d.xh ");
		sql.append("where a.sqid = ? ");
		System.out.println(sql);
		return dao.getList(sql.toString(), new String[] { sqid, sqid },
				colValue);
	}

	
}
