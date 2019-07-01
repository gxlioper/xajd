/**
 * <p>Coyright (R) 2014 ��������ɷ����޹�˾��<p>
 */
package com.zfsoft.xgxt.twgl.tgb;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/**
 * @className	�� TgbDao
 * @description	�� TODO(��������������)
 * @author 		��lj��1282��
 * @date		�� 2018-5-15 ����03:26:23
 * @version 	V1.0 
 */

public class TgbDao extends SuperDAOImpl<TgbModel>{

	/**
	 * @description	�� �Ÿɲ�Dao
	 * @author 		��lj��1282��
	 * @date 		��2018-5-15 ����03:26:39
	 * @param t
	 * @return
	 * @throws Exception
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(TgbModel t)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @description	�� ��ѯҳ��
	 * @author 		��lj��1282��
	 * @date 		��2018-5-15 ����03:26:39
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(TgbModel t, User user) throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");	
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* from (select a.*,b.mc zwmc,c.nj,c.xydm,c.xymc,c.bjdm,c.bjmc,c.zydm,c.zymc,c.xb,c.xm,d.zzmc rzzzmc");
		sql.append(" from xg_tgbgl_tgbjgb a left join xg_tgb_zwdmb b on a.zwdm = b.dm");
		sql.append(" left join view_xsxxb c on a.xh = c.xh ");
		sql.append(" left join xg_zzgl_zzxxb d on a.rzzz = d.zzid");
//		sql.append(" select t1.*,t2.xm,t2.xb,t2.sfzh,t2.xymc,t2.xydm,t2.zydm,t2.zymc,t2.bjdm,t2.bjmc,t2.nj,t8.xqmc,t1.xn || t8.xqmc xnxq,t1.shzt shztx,");
//		sql.append(" decode(t1.shzt,'0','δ�ύ','1','ͨ��','2','��ͨ��','3','�˻�','5','�����',t1.shzt) shztmc");
//		sql.append(" from XG_GYGL_NEW_ZZDSQB t1 left join view_xsbfxx t2 on t1.xh = t2.xh");
//		sql.append(" left join xqdzb t8 on t1.xq = t8.xqdm");
		sql.append(" ) t where 1 = 1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}
	
	/**
	 * @description	�� ��ȡְ�����list
	 * @author 		�� lj��1282��
	 * @date 		��2018-5-15 ����05:24:47
	 * @return
	 */
	public List<HashMap<String,String>> getDmList(){
		String sql = "select * from xg_tgb_zwdmb";
		return dao.getList(sql, new String[]{}, new String[]{"dm","mc"});
	}

	/**
	 * @description	�� TODO
	 * @author 		��lj��1282��
	 * @date 		��2018-5-15 ����03:26:39
	 */
	
	@Override
	protected void setTableInfo() {
		super.setClass(TgbModel.class);
		super.setKey("jgid");
		super.setTableName("xg_tgbgl_tgbjgb");		
	}
	
	/**
	 * @description	�� ��ȡ��Ϣ
	 * @author 		�� lj��1282��
	 * @date 		��2018-5-16 ����11:44:15
	 * @param t
	 * @return
	 */
	public HashMap<String,String> getInfo(TgbModel t){
		String sql = "select a.*,b.zzmc rzzzmc,c.mc zwmc from xg_tgbgl_tgbjgb a left join xg_zzgl_zzxxb b on a.rzzz = b.zzid left join xg_tgb_zwdmb c on a.zwdm = c.dm where a.xh = ?";
		return dao.getMapNotOut(sql, new String[]{t.getXh()});
	}
	
	/**
	 * @description	�� �Ƿ����Ÿɲ�
	 * @author 		�� lj��1282��
	 * @date 		��2018-5-16 ����05:50:58
	 * @param zzids
	 * @return
	 */
	public int countTgb(String[] zzids){
		StringBuffer sb = new StringBuffer();
		sb.append("select count(1) rn from xg_tgbgl_tgbjgb where rzzz in (");
		for(int i = 0;i<zzids.length;i++){
			sb.append("?");
			if(i != zzids.length - 1){
				sb.append(",");
			}
		}
		sb.append(")");
		return Integer.valueOf(dao.getOneRs(sb.toString(), zzids, "rn"));
	}

}
