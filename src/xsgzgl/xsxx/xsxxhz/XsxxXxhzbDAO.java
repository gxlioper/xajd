package xsgzgl.xsxx.xsxxhz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;

public class XsxxXxhzbDAO {
	/**
	 * ��ȡѧ��Υ�ʹ�����Ϣ
	 * @param xh
	 * @return
	 */
	public List<HashMap<String,String>>getWjcfInfo(String xh){
		
		DAO dao=DAO.getInstance();
		
		StringBuilder sql=new StringBuilder();
		
		sql.append(" select '��' || substr(wjsj, 1, 4) || '��' || substr(wjsj, 5, 2) || '��' || ");
		sql.append(" substr(wjsj, 7, 2) || '������' || cfyymc || '�ܵ���' || cflbmc || '����' wjxx ");
		sql.append("  from wjcfb a left join cfyydmb b on a.cfyy = b.cfyydm ");
		sql.append(" left join cflbdmb c on a.cflb = c.cflbdm ");
		sql.append("  where xh = ?  and xxsh = 'ͨ��' ");
		
		return dao.getList(sql.toString(), new String[]{xh}, new String[]{"wjxx"});
		
	}
	
	/**
	 * ��ȡѧ������������Ϣ
	 * @param xh
	 * @return
	 */
	public List<HashMap<String,String>>getPjpyInfo(String xh){
		
		DAO dao=DAO.getInstance();
		
		StringBuilder sql=new StringBuilder();
		
		sql.append(" select (case when pjxn='��' then '' else pjxn end)");
		sql.append("||(case when pjxq='��' then '' else pjxq end)");
		sql.append("||(case when pjnd='��' then '' else pjnd end)");
		sql.append("||'�����' ");
		sql.append("||xmmc pjxx");
		
		sql.append(" from( ");
		sql.append(" select xh, pjxn, pjnd, pjxq, xmmc ");
		sql.append(" from xg_view_pjpy_xmsh a ");
		sql.append(" where shzt = 'ͨ��' ");
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
		sql.append(" and b.sfsh='��' and xh=? ");
		sql.append(" ) ");
		
		return dao.getList(sql.toString(), new String[]{xh,xh}, new String[]{"pjxx"});
		
	}
	
	/**
	 * ��ȡ��ͥ��Ա��Ϣ
	 * @param xh
	 * @return
	 */
	public HashMap<String,String>getJtcyInfo(String xh){
		
		DAO dao=DAO.getInstance();
		
		StringBuilder sql=new StringBuilder();
		
		sql.append(" select  ");
		
		sql.append(" lxdh1,jtszd,yb,jtcy1_xm,jtcy1_gx,jtcy1_gzdz||' '||jtcy1_zw  gzdwdz1,");
		sql.append(" jtcy2_xm,jtcy2_gx,jtcy2_gzdz||' '||jtcy2_zw  gzdwdz2,");
		sql.append(" jtcy3_xm,jtcy3_gx,jtcy3_gzdz||' '||jtcy3_zw  gzdwdz3,");
		sql.append(" jtcy4_xm,jtcy4_gx,jtcy4_gzdz||' '||jtcy4_zw  gzdwdz4,");
		sql.append(" jtcy5_xm,jtcy5_gx,jtcy5_gzdz||' '||jtcy5_zw  gzdwdz5,  ");
		
		// -------------------------��ͥ��Ա������ò--------------------------------
		sql.append(" (select zzmmmc from zzmmdmb b where a.jtcy1_zzmm=b.zzmmdm)zzmm1,  ");
		sql.append(" (select zzmmmc from zzmmdmb b where a.jtcy2_zzmm=b.zzmmdm)zzmm2,  ");
		sql.append(" (select zzmmmc from zzmmdmb b where a.jtcy3_zzmm=b.zzmmdm)zzmm3,  ");
		sql.append(" (select zzmmmc from zzmmdmb b where a.jtcy4_zzmm=b.zzmmdm)zzmm4,  ");
		sql.append(" (select zzmmmc from zzmmdmb b where a.jtcy5_zzmm=b.zzmmdm)zzmm5,  ");
		
		// -------------------------����--------------------------------
		sql.append(" (select mzmc from mzdmb b where a.jtcy1_mz=b.mzdm)mz1,  ");
		sql.append(" (select mzmc from mzdmb b where a.jtcy2_mz=b.mzdm)mz2,  ");
		sql.append(" (select mzmc from mzdmb b where a.jtcy3_mz=b.mzdm)mz3,  ");
		sql.append(" (select mzmc from mzdmb b where a.jtcy4_mz=b.mzdm)mz4,  ");
		sql.append(" (select mzmc from mzdmb b where a.jtcy5_mz=b.mzdm)mz5,  ");
		
		// -------------------------��ͥ��Ա����-------------------------------
		sql.append(" jtcy1_nl,jtcy2_nl,jtcy3_nl,jtcy4_nl,jtcy5_nl,");
		sql.append(" jtcy1_lxdh1,jtcy2_lxdh1,jtcy3_lxdh1,jtcy4_lxdh1,jtcy5_lxdh1");
		
		sql.append(" from xsfzxxb a where xh=? ");

		String[]jtcyArr={"lxdh1","jtszd","yb","jtcy1_xm","jtcy1_gx","gzdwdz1",
				"jtcy2_xm","jtcy2_gx","gzdwdz2",
				"jtcy3_xm","jtcy3_gx","gzdwdz3",
				"jtcy4_xm","jtcy4_gx","gzdwdz4",
				"jtcy5_xm","jtcy5_gx","gzdwdz5",
				"zzmm1","zzmm2","zzmm3","zzmm4","zzmm5",
				"mz1","mz2","mz3","mz4","mz5",
				"jtcy1_nl","jtcy2_nl","jtcy3_nl","jtcy4_nl","jtcy5_nl",
				"jtcy1_lxdh1","jtcy2_lxdh1","jtcy3_lxdh1","jtcy4_lxdh1","jtcy5_lxdh1"};
		
		return dao.getMap(sql.toString(), new String[]{xh},jtcyArr);
		
	}
	
	/**
	 * ��ȡ��ҵ������Ϣ
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
	 * ��ȡ������������Ϣ
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
	 * ��ȡѧ��ѧϰ������Ϣ
	 * @param xh
	 * @return
	 */
	public List<HashMap<String,String>>getXxjlInfo(String xh){
		
		DAO dao=DAO.getInstance();
		
		StringBuilder sql=new StringBuilder();
		
		sql.append(" select * from ( ");
		sql.append(" select substr(qssj, 1, 4) || '��' || substr(qssj, 5, 2) || '��' || ");
		sql.append(" substr(qssj, 7, 2) || '��  ��  ' || substr(zzsj, 1, 4) || '��' || ");
		sql.append(" substr(zzsj, 5, 2) || '��' || substr(zzsj, 7, 2) || '��' xxsj, ");
		sql.append(" dwmc  from xsxx_cslg_xxjlb where xh=?  order by qssj desc ");
		sql.append(" ) where rownum <=5 ");
		
		return dao.getList(sql.toString(), new String[]{xh},new String[]{"xxsj","dwmc"});
		
	}
	
	/**
	 * ��ȡѧ���춯��Ϣ
	 */
	public List<HashMap<String,String>>getXjydInfo(String xh,String length){
		
		DAO dao=DAO.getInstance();
		
		StringBuilder sql=new StringBuilder();
		
		List<String>inputV=new ArrayList<String>();
		
		sql.append(" select ydlbmc,ydrq,clwh, ydyy,ydsm, ");
		sql.append(" '��' || ydqxymc || ydqzymc || ydqbjmc ");
		sql.append(" || '�춯��' || ydhxymc ||ydhzymc || ydhbjmc bz  ");
		sql.append("  from (select a.*, b.xm, c.ydlbmc ");
		sql.append(" from bks_xjydxx a ");
		sql.append(" left join view_xsbfxx b on a.xh = b.xh ");
		sql.append(" left join dm_ydlb c on a.ydlbm = c.ydlbm) a ");
		sql.append("  where 1 = 1 and xh = ? and shzt = 'ͨ��' ");
		
		inputV.add(xh);
		
		if(!Base.isNull(length)){
			
			sql.append(" and rownum <= ? ");
			inputV.add(length);
		
		}
		
		sql.append(" order by sqsj desc ");
		
		return dao.getListNotOut(sql.toString(), inputV.toArray(new String[]{}));
	}
	
	/**
	 * ��ȡ�۲���ϢBYѧ��ѧ��
	 */
	public List<HashMap<String,String>>getZcfInfo(String xh,String length){
		
		DAO dao=DAO.getInstance();
		
		StringBuilder sql=new StringBuilder();
		
		List<String>inputV=new ArrayList<String>();
		
		sql.append("  select xn, zd1, zcfbjpm, zcfnjzypm ");
		
		sql.append("   from xg_pjpy_zhcpb where xh = ? ");
	
		inputV.add(xh);
		
		if(!Base.isNull(length)){
			
			sql.append(" and rownum <= ? ");
			inputV.add(length);
		
		}
		
		sql.append("   order by xn desc ");
		
		return dao.getListNotOut(sql.toString(), inputV.toArray(new String[]{}));
	}
	
	/**
	 * ��Ա��Ϣ
	 */
	public HashMap<String,String>getDtInfo(String xh){
		
		DAO dao=DAO.getInstance();
		
		StringBuilder sql=new StringBuilder();
		
		sql.append(" select max(rt)rt,max(rdjjfz)rdjjfz,max(ybdy)ybdy");
		sql.append(" ,max(zsdy)zsdy from (  select  ");	
		sql.append("  case when jddm='02' then jssj else '' end rt, ");
		sql.append("  case when jddm='05' then jssj else '' end rdjjfz, ");
		sql.append("  case when jddm='07' then jssj else '' end ybdy, ");
		sql.append("  case when jddm='08' then jssj else '' end zsdy ");
		
		sql.append(" from ( select jddm,jssj  from xg_dtjs_xsdtxxjlb a ");
		sql.append(" where xh = ?  and exists  ");
		sql.append(" (select 1 from xg_dtjs_xsdtxxjlb b  where xh = ? ");
		sql.append("  and dqjdbj = '1' and a.jddm <= b.jddm))) ");
		
		return dao.getMapNotOut(sql.toString(), new String[]{xh,xh});
	}
	
	/**
	 * ��ȡ����������Ϣ
	 */
	public List<HashMap<String,String>>getPjInfo(String xh,String length){
		
		DAO dao=DAO.getInstance();
		
		StringBuilder sql=new StringBuilder();
		
		List<String>inputV=new ArrayList<String>();
		
		sql.append(" select a.*,case  when b.xmlx = '001' then ");
		sql.append(" '��ѧ��' else '�����ƺ�' end xmlx ");	
		sql.append(" from xg_view_pjpy_xstgjl a, xg_pjpy_pjxmwhb b ");
		sql.append(" where (b.sqzq = 'xn' and a.pjxn = b.pjxn or ");
		sql.append(" b.sqzq = 'xq' and a.pjxn = b.pjxn and a.pjxq = b.pjxq or ");
		sql.append(" b.sqzq = 'nd' and a.pjnd = b.pjnd) ");
		sql.append(" and a.xmdm = b.xmdm ");
		sql.append(" and xh = ?   ");
		
		inputV.add(xh);
		
		if(!Base.isNull(length)){
			
			sql.append(" and rownum <= ? ");
			inputV.add(length);
		
		}
		
		sql.append("   order by a.pjxn desc ");
		
		return dao.getListNotOut(sql.toString(), inputV.toArray(new String[]{}));
	}
	
	/**
	 * ��ȡΥ����Ϣ
	 */
	public List<HashMap<String,String>>getWjInfo(String xh,String length){
		
		DAO dao=DAO.getInstance();
		
		StringBuilder sql=new StringBuilder();
		
		List<String>inputV=new ArrayList<String>();
		
		sql.append(" select xn,cflbmc,cfyymc,cfsj,cfwh from view_wjcf  ");
		sql.append(" where xh=? and xxsh='ͨ��'  ");	
		
		
		inputV.add(xh);
		
		if(!Base.isNull(length)){
			
			sql.append(" and rownum <= ? ");
			inputV.add(length);
		
		}
		
		sql.append(" order by xn desc ");
		
		return dao.getListNotOut(sql.toString(), inputV.toArray(new String[]{}));
	}
	
	/**
	 * ��ȡ������Ϣ
	 */
	public List<HashMap<String,String>>getZzInfo(String xh,String length){
		
		DAO dao=DAO.getInstance();
		
		StringBuilder sql=new StringBuilder();
		
		List<String>inputV=new ArrayList<String>();
		
		sql.append(" select a.xn,b.xmmc,a.xmzzjb,a.xmzzje ");
		sql.append(" from xszz_shztb a, xszz_zzxmb b  ");	
		sql.append(" where xh = ? and a.xmdm = b.xmdm  ");	
		sql.append(" and (shjb = '�������' or shjb = 'һ�����' and a.shzt1 = 'ͨ��' or  ");	
		sql.append(" shjb = '�������' and a.shzt2 = 'ͨ��' or ");	
		sql.append(" shjb = '�������' and a.shzt3 = 'ͨ��') ");	
		
		inputV.add(xh);
		
		if(!Base.isNull(length)){
			
			sql.append(" and rownum <= ? ");
			inputV.add(length);
		
		}
		
		sql.append(" order by xn desc ");
		
		return dao.getListNotOut(sql.toString(), inputV.toArray(new String[]{}));
	}
	

	/**
	 * ��ȡ�ڹ���ѧ��Ϣ
	 */
	public List<HashMap<String,String>>getQgzxInfo(String xh,String length){
		
		DAO dao=DAO.getInstance();
		
		StringBuilder sql=new StringBuilder();
		
		List<String>inputV=new ArrayList<String>();
		
		sql.append(" select * from(select xn, gwdm, sum(gzsj)gzsj,sqsj ");
		sql.append(" from view_xscjff  ");	
		sql.append("  where xh = ?  ");	
		sql.append("  and xxsh = 'ͨ��' ");	
		sql.append(" group by gwdm,sqsj,xn)where 1=1  ");
		
		inputV.add(xh);
		
		if(!Base.isNull(length)){
			
			sql.append(" and rownum <= ? ");
			inputV.add(length);
		
		}
		
		sql.append("  order by xn desc ");
		
		return dao.getListNotOut(sql.toString(), inputV.toArray(new String[]{}));
	}
	
	/**
	 * ��ȡ�ڹ���ѧ��Ϣ
	 */
	public List<HashMap<String,String>>getZsInfo(String xh,String length){
		
		DAO dao=DAO.getInstance();
		
		StringBuilder sql=new StringBuilder();
		
		List<String>inputV=new ArrayList<String>();
		
		sql.append(" select * from( select ");
		sql.append(" (select ldmc from xg_gygl_new_ldxxb b where a.lddm=b.lddm)ldmc,qsh,cwh,rzsj ");
		sql.append(" from xg_gygl_new_cwxxb a where xh=? ");
		sql.append(" union ");
		sql.append(" select ldmc,qsh,cwh,rzsj from xg_gygl_new_tsxxb  where xh=? ");
		sql.append(" ) where 1=1 ");	
		
		inputV.add(xh);
		inputV.add(xh);
		
		if(!Base.isNull(length)){
			
			sql.append(" and rownum <= ? ");
			inputV.add(length);
		
		}
		
		sql.append(" order by rzsj desc ");
		
		return dao.getListNotOut(sql.toString(), inputV.toArray(new String[]{}));
	}
	
	/**
	 * ��ȡ���������Ϣ
	 */
	public HashMap<String,String>getPyccInfo(String pycc){
		
		DAO dao=DAO.getInstance();
		
		StringBuilder sql=new StringBuilder();
		
		List<String>inputV=new ArrayList<String>();
		
		sql.append("  select * from xg_xsxx_pyccdmb where pyccdm=? ");	
		
		inputV.add(pycc);
		
		return dao.getMapNotOut(sql.toString(), inputV.toArray(new String[]{}));
	}
}
