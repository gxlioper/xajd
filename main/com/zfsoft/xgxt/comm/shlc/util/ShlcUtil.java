/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-6-14 ����03:50:28 
 */  
package com.zfsoft.xgxt.comm.shlc.util;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.shlc.dao.ShlcDao;

import xgxt.DAO.DAO;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ����ģ��
 * @�๦������: �����������ع��߷���
 * @���ߣ� Penghui.Qu [���ţ�445]
 * @ʱ�䣺 2013-6-14 ����03:50:28 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ShlcUtil {

	
	/**
	 * 
	 * @����: ��ѯĳ�������ָ����λǰ��������λ
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-6-14 ����03:53:25
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param shlc
	 * @param gwid
	 * @return
	 * List<HashMap<String,String>> ��������
	 */
	public static List<HashMap<String,String>> getBeforeSpgw(String shlc,String gwid){
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select spgw gwid,t2.mc gwmc from xg_xtwh_spbz t1 ");
		sql.append("left join xg_xtwh_spgw t2 on t1.spgw=t2.id ");
		sql.append("where t1.splc=? and t1.xh < (");
		sql.append("select xh from xg_xtwh_spbz where splc=? and spgw=? ");
		sql.append(") order by xh");
		
		return DAO.getInstance().getListNotOut(sql.toString(), new String[]{shlc,shlc,gwid});
	}
	
	
	/**
	 * 
	 * @����: ��ѯģ������������б� 
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-6-14 ����04:07:29
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param gnmk
	 * @return
	 * List<HashMap<String,String>> ��������
	 */
	public static List<HashMap<String,String>> getShlcByGnmk(String gnmk){
		
		String sql = "select * from xg_xtwh_splc where djlx=? ";
		
		return DAO.getInstance().getListNotOut(sql, new String[]{gnmk});
	}
	/**
	 * @����:����ҵ�������� ��ѯ����������Ϣ 
	 * @���ߣ�zhangjw
	 * @���ڣ�2013-6-18 ����10:03:12
	 * @�޸ļ�¼: 
	 * @param sqid
	 * @return
	 * List<HashMap<String,String>> ��������
	 */
	public static List<HashMap<String, String>> getShlcInfo(String sqid) {
		StringBuilder sql = new StringBuilder();
		//sql.append("  select b.mc,c.xm shr,a.shsj,a.shzt,a.shyj,a.gwid,a.guid from xg_xtwh_shztb a left join xg_xtwh_spgw b  on a.gwid = b.id left join yhb c on a.shr=c.yhm where a.ywid = ?  order by a.shsj  ");
		sql.append("  select b.mc,c.xm shr,a.* from xg_xtwh_shztb a left join xg_xtwh_spgw b  on a.gwid = b.id left join yhb c on a.shr=c.yhm where a.ywid = ?  order by a.shsj  ");
		return DAO.getInstance().getListNotOut(sql.toString(), new String[] { sqid});
	}
	/**
	 * @����:������������id��ѯ��������
	 * @���ߣ�zhangjw
	 * @���ڣ�2013-8-1 ����10:24:33
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param splc
	 * @return
	 * List<HashMap<String,String>> ��������
	 */
	public static List<HashMap<String, String>> getSpbzBySplc(String splc) {
		StringBuilder sql = new StringBuilder();
		sql.append("  select a.spgw,a.xh,b.mc,a.splc from xg_xtwh_spbz  a join xg_xtwh_spgw b on a.spgw = b.id  where a.splc = ? order by xh  ");
		return DAO.getInstance().getListNotOut(sql.toString(), new String[] { splc});
	}
	/**
	 * @����:��ѯ��һ����λid
	 * @���ߣ�zhangjw
	 * @���ڣ�2013-7-31 ����5:37:25
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param shlc
	 * @param gwid
	 * @return
	 * String ��������
	 */
	public static String getUpGwid(String shlc,String gwid){
		
		String sql = "select sjgw from (select t1.spgw,lag(t1.spgw,1,0) over (partition by t1.splc order by t1.xh )sjgw from xg_xtwh_spbz t1 where splc=?) where spgw=?";
		
		return DAO.getInstance().getOneRs(sql, new String[]{shlc,gwid}, "sjgw");
	}
	/**
	 * @����:��ȡ��ǰ������λ
	 * @���ߣ�zhangjw
	 * @���ڣ�2013-8-1 ����1:47:15
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ywid
	 * @return
	 * String ��������
	 */
	public static String getDqGw(String ywid){
		String sql = "select gwid from (select * from (select t.*,row_number() over (partition by t.ywid order by t.shsj ) as xjsj from XG_XTWH_SHZTB t where t.ywid = ?  ) order by xjsj desc ) where rownum =1";
		return DAO.getInstance().getOneRs(sql, new String[]{ywid}, "gwid");
	}
	/**
	 * @����:��ȡ��ǰ��λ����
	 * @���ߣ�zhangjw
	 * @���ڣ�2013-8-1 ����1:51:12
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param splc
	 * @param ywid
	 * @return
	 * HashMap<String,String> ��������
	 */
	public static HashMap<String,String> getDqGwbz(String splc,String ywid){
		StringBuffer sql = new StringBuffer();
		sql.append(" select decode(n.shzt, 0, 0, 1, 1, 2, 2, 4, 0, '') as shzt,m.xh,m.spgw from ( select * from (select t.*,row_number() over (partition by t.ywid order by t.shsj desc) as xjsj   from XG_XTWH_SHZTB t  ");
		sql.append("  where t.ywid = ? )where xjsj =1");
		sql.append("  )n left join xg_xtwh_spbz m  on n.gwid = m.spgw  and m.splc = ?");
		return DAO.getInstance().getMapNotOut(sql.toString(),  new String[]{ywid,splc});
	}
	/**
	 * @����:�������id ��ѯ�����Ϣ
	 * @���ߣ�zhangjw
	 * @���ڣ�2013-8-5 ����10:55:42
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param shid
	 * @return
	 * HashMap<String,String> ��������
	 */
	public static HashMap<String,String> getShxx(String shid){
		String sql = "select * from XG_XTWH_SHZTB b where b.guid = ? ";
		return DAO.getInstance().getMapNotOut(sql,  new String[]{shid});
	}

	/**
	 * 
	 * @����:ɾ����Ӧ������Ϣ
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-12-16 ����09:08:04
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ywid
	 * @return
	 * @throws Exception boolean ��������
	 */
	public static boolean deleteSpxx(String ywid){
		StringBuffer sb = new StringBuffer();
		sb.append("delete xg_xtwh_shztb where ywid=?");
		try {
			DAO.getInstance()
					.runDelete(sb.toString(), new String[] { ywid });
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	/**
	 * 
	 * @����: ����ϼ���˸�λ
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-12-18 ����10:52:45
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param gwid
	 * @param splc
	 * @return
	 * String �������� 
	 */
	public static String getSjspgw(String userName,String ywid,String gwid,String splc){
		String xh=getGwSpXh(splc, gwid);
		ShlcDao sd=new ShlcDao();
		String lastXh=sd.getLastGwid(splc);
		String lastShzt = sd.getLastShzt(userName, ywid, gwid);
		//��������һ����ˣ��������ͨ�� ��ֱ�ӷ������һ����λid
		if(xh.equals(lastXh)&&Constants.TG.equals(lastShzt)){
			return gwid;
		}else{
			//��ȡ�ϼ���˵ĸ�λid
			Integer xhI=Integer.parseInt(xh);
			xhI=xhI-1;
			StringBuffer sb=new StringBuffer();
			sb.append("select spgw from xg_xtwh_spbz where xh=? and splc=?");
			return DAO.getInstance().getOneRs(sb.toString(),new String[]{xhI.toString(),splc}, "spgw");
		}
	}
	/**
	 * 
	 * @����:��ȡ������λ�����
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-12-18 ����10:48:40
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param splc
	 * @param spgw
	 * @return
	 * String �������� 
	 */
	public static String getGwSpXh(String splc,String spgw){
		StringBuffer sb=new StringBuffer();
		sb.append("select xh from xg_xtwh_spbz where splc=? and spgw=?");
		return DAO.getInstance().getOneRs(sb.toString(), new String[]{splc,spgw}, "xh");
	}
	/**
	 * 
	 * @����: ��ȡ��ǰ��˸�λ�ϼ���λ
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-12-18 ����03:15:52
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param splc
	 * @param gwid
	 * @return
	 * String �������� 
	 */
	public static String getUpSpgw(String splc,String gwid){
		String xh=getGwSpXh(splc, gwid);
		Integer xhI=Integer.parseInt(xh)-1;
		StringBuffer sb=new StringBuffer();
		sb.append("select spgw from xg_xtwh_spbz where splc=? and xh=?");
		return DAO.getInstance().getOneRs(sb.toString(), new String[]{splc,xhI.toString()}, "spgw");
	}
	/**
	 * 
	 * @����: ����ɾ�������Ϣ
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-12-16 ����09:10:43
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ywids
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 */
	public static boolean deleteSpxx(String[] ywids) throws Exception {
		boolean isSucceed = false;
		for (String ywid : ywids) {
			isSucceed = deleteSpxx(ywid);
			if (!isSucceed) {
				break;
			}
		}
		return isSucceed;
	}
}
