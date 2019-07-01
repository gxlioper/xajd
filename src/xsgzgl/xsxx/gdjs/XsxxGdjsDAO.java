package xsgzgl.xsxx.gdjs;

import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xsgzgl.comm.BasicDAO;

public class XsxxGdjsDAO extends BasicDAO{
	
	
	/**
	 * 获取学生违纪处分信息
	 * @param xh
	 * @return
	 */
	public List<HashMap<String,String>>getWjcfInfo(String xh){
		
		DAO dao=DAO.getInstance();
		
		StringBuilder sql=new StringBuilder();
		
		sql.append(" select '于' || substr(wjsj, 1, 4) || '年' || substr(wjsj, 5, 2) || '月' || ");
		sql.append(" substr(wjsj, 7, 2) || '日由于' || cfyymc || '受到了' || cflbmc || '处分' wjxx ");
		sql.append("  from wjcfb a left join cfyydmb b on a.cfyy = b.cfyydm ");
		sql.append(" left join cflbdmb c on a.cflb = c.cflbdm ");
		sql.append("  where xh = ?  and xxsh = '通过' ");
		
		return dao.getList(sql.toString(), new String[]{xh}, new String[]{"wjxx"});
		
	}
	
	/**
	 * 获取学生评奖评优信息
	 * @param xh
	 * @return
	 */
	public List<HashMap<String,String>>getPjpyInfo(String xh){
		
		DAO dao=DAO.getInstance();
		
		StringBuilder sql=new StringBuilder();
		
		sql.append(" select (case when pjxn='无' then '' else pjxn end)");
		sql.append("||(case when pjxq='无' then '' else pjxq end)");
		sql.append("||(case when pjnd='无' then '' else pjnd end)");
		sql.append("||'获得了' ");
		sql.append("||xmmc pjxx");
		
		sql.append(" from( ");
		sql.append(" select xh, pjxn, pjnd, pjxq, xmmc ");
		sql.append(" from xg_view_pjpy_xmsh a ");
		sql.append(" where shzt = '通过' ");
		sql.append(" and shjb = (select max(xh) ");
		sql.append(" from xg_xtwh_spbz b ");
		sql.append(" where a.lcid = b.splc ");
		sql.append(" group by splc) ");
		sql.append(" and xh=? ");
		
		sql.append(" union ");
		
		sql.append(" select a.xh, a.pjxn, a.pjnd, a.pjxq, b.xmmc ");
		sql.append(" from xg_pjpy_pjxmsqb a,xg_pjpy_pjxmwhb b  ");
		sql.append(" where b.xmdm=a.xmdm ");
		sql.append(" and ((b.sqzq = 'xn' and a.pjxn=b.pjxn)  ");
		sql.append(" or (b.sqzq = 'xq' and a.pjxn=b.pjxn and a.pjxq=b.pjxq) ");
		sql.append(" or (b.sqzq = 'nd' and a.pjnd=b.pjnd))  ");
		sql.append(" and b.sfsh='否' and xh=? ");
		sql.append(" ) ");
		
		return dao.getList(sql.toString(), new String[]{xh,xh}, new String[]{"pjxx"});
		
	}
	
	/**
	 * 获取家庭成员信息
	 * @param xh
	 * @return
	 */
	public HashMap<String,String>getJtcyInfo(String xh){
		
		DAO dao=DAO.getInstance();
		
		StringBuilder sql=new StringBuilder();
		
		sql.append(" select jtcy1_xm,jtcy1_gx,jtcy1_gzdz||' '||jtcy1_zw gzdwdz1, ");
		sql.append(" jtcy2_xm,jtcy2_gx,jtcy2_gzdz||' '||jtcy2_zw  gzdwdz2,");
		sql.append(" jtcy3_xm,jtcy3_gx,jtcy3_gzdz||' '||jtcy3_zw  gzdwdz3,");
		sql.append(" jtcy4_xm,jtcy4_gx,jtcy4_gzdz||' '||jtcy4_zw  gzdwdz4,");
		sql.append(" jtcy5_xm,jtcy5_gx,jtcy5_gzdz||' '||jtcy5_zw  gzdwdz5,  ");
		sql.append(" (select zzmmmc from zzmmdmb b where a.jtcy1_zzmm=b.zzmmdm)zzmm1,  ");
		sql.append(" (select zzmmmc from zzmmdmb b where a.jtcy2_zzmm=b.zzmmdm)zzmm2,  ");
		sql.append(" (select zzmmmc from zzmmdmb b where a.jtcy3_zzmm=b.zzmmdm)zzmm3,  ");
		sql.append(" (select zzmmmc from zzmmdmb b where a.jtcy4_zzmm=b.zzmmdm)zzmm4,  ");
		sql.append(" (select zzmmmc from zzmmdmb b where a.jtcy5_zzmm=b.zzmmdm)zzmm5  ");
		sql.append(" from xsfzxxb a where xh=? ");

		String[]jtcyArr={"jtcy1_xm","jtcy1_gx","gzdwdz1",
				"jtcy2_xm","jtcy2_gx","gzdwdz2",
				"jtcy3_xm","jtcy3_gx","gzdwdz3",
				"jtcy4_xm","jtcy4_gx","gzdwdz4",
				"jtcy5_xm","jtcy5_gx","gzdwdz5",
				"zzmm1","zzmm2","zzmm3","zzmm4","zzmm5"};
		
		return dao.getMap(sql.toString(), new String[]{xh},jtcyArr);
		
	}
	
	/**
	 * 获取毕业鉴定信息
	 * @param xh
	 * @return
	 */
	public HashMap<String,String>getByjdInfo(String xh){
		
		DAO dao=DAO.getInstance();
		
		StringBuilder sql=new StringBuilder();
		
		sql.append(" select byjd from xg_xsxx_byjdb where xh=? ");
		
		return dao.getMap(sql.toString(), new String[]{xh},new String[]{"byjd"});
		
	}
	
	/**
	 * 获取班主任评议信息
	 * @param xh
	 * @return
	 */
	public List<HashMap<String,String>>getBzrpyInfo(String xh){
		
		DAO dao=DAO.getInstance();
		
		StringBuilder sql=new StringBuilder();
		
		sql.append(" select xn, xh, pysj,pyyj ");
		sql.append(" ,(select xm from yhb b where a.pyr=b.yhm)pyrxm ");
		sql.append(" from xg_xsxx_bzrpyb a where xh = ? order by xn");
		
		return dao.getList(sql.toString(), new String[]{xh},new String[]{"xn","xh","pysj","pyyj","pyrxm"});
		
	}
	
	/**
	 * 获取学生学习经历信息
	 * @param xh
	 * @return
	 */
	public List<HashMap<String,String>>getXxjlInfo(String xh){
		
		DAO dao=DAO.getInstance();
		
		StringBuilder sql=new StringBuilder();
		
		sql.append(" select * from ( ");
		sql.append(" select substr(qssj, 1, 4) || substr(qssj, 5, 2)|| ");
		sql.append(" substr(qssj, 7, 2) ||' 至  '|| substr(zzsj, 1, 4)|| ");
		sql.append(" substr(zzsj, 5, 2)  || substr(zzsj, 7, 2) xxsj, ");
		sql.append(" dwmc  from xsxx_cslg_xxjlb where xh=?  order by qssj desc ");
		sql.append(" ) where rownum <=5 ");
		
		return dao.getList(sql.toString(), new String[]{xh},new String[]{"xxsj","dwmc"});
		
	}
	 
}
