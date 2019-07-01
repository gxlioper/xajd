/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-9-7 ����05:06:58 
 */  
package com.zfsoft.xgxt.zxdk.rwfbybc.sqsh;

import java.util.HashMap;
import java.util.List;

import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ�  ChenQ[����:856]
 * @ʱ�䣺 2015-9-7 ����05:06:58 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class RwfbysqshDao extends SuperDAOImpl<RwfbysqshModel> {
	private static final String YSH = "ysh";
	
	@Override
	public List<HashMap<String, String>> getPageList(RwfbysqshModel t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}


	@Override
	public List<HashMap<String, String>> getPageList(RwfbysqshModel t, User user)
			throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");		
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select * from (");
		sql.append("select t1.*,t2.xm,t2.xydm,t2.xymc,t2.zydm,");
		sql.append("decode(t1.shzt,'0','δ�ύ','1','ͨ��','2','��ͨ��','3','�˻�','5','�����',t1.shzt) shztmc, ");
		sql.append("t2.zymc,t2.bjdm,t2.bjmc,t2.nj,t2.zybj,t2.zybjmc,t2.sydm1 sydm,t2.symc1 symc ");
		sql.append("from XG_ZXDK_RWFBYDCSQB t1 left join view_xsjbxx t2 on t1.xh = t2.xh ");
		sql.append(") t where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		
		return getPageList(t, sql.toString(), inputV);
	}
	public List<HashMap<String, String>> getAudingList(RwfbysqshModel t, User user) throws Exception{
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t2","xydm", "bjdm");
		String shgwzByUser = SearchService.getShgwzByUser(user, "t2", "xydm","bjdm");
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append("select t2.* from (select t1.*,");
		sql.append("row_number() over(partition by id order by shsj desc) rn ");
		sql.append("from (select b.*,'[' || c.mc || ':' || ");
		sql.append("decode(b.lczt,'0','�����','1','ͨ��','2','��ͨ��','3','�˻�','4','������','5','�����',b.lczt) || ']' shztmc,");
		sql.append("c.gwz from (select t1.*,t2.xm,t2.xydm,t2.xymc,t2.zydm,");
		sql.append("t2.zymc,t2.bjdm,t2.bjmc,t2.nj,t2.zybj,t2.zybjmc,t2.sydm1 sydm,t2.symc1 symc,b.shzt as lczt,");
		sql.append("b.shsj,b.gwid as xtgwid,b.zd2 as rddc,b.guid as shid ");
		sql.append("from XG_ZXDK_RWFBYDCSQB t1 ");
		sql.append("left join view_xsjbxx t2 on t1.xh = t2.xh ");
		sql.append("left join xg_xtwh_shztb b on t1.id = b.ywid ");
		sql.append(") b left join xg_xtwh_spgw c on b.xtgwid = c.id where b.xtgwid in");
//		if("xx".equals(user.getUserStatus())){
			sql.append("(select spgw from xg_xtwh_spgwyh where spyh = '")
					.append(user.getUserName()).append("')  ");
//		} else {
//			sql.append("(select spgw from xg_xtwh_spgwyh where yhjs = '").append(user.getUserStatus())
//					.append("' and spyh = '")
//					.append(user.getUserName()).append("') ");
//		}

		if(!"xx".equals(user.getUserStatus())){
			sql.append(" and c.yhjs='"+user.getUserStatus()+"'");
		}
		if (YSH.equals(t.getShzt())) {
			sql.append("and b.lczt not in ('0', '4')) t1 ) t2 where rn = 1 ");
		} else {
			sql.append(" and b.lczt in ('0', '4')) t1) t2 where rn = 1 ");
		}

		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		sql.append(shgwzByUser);

		return super.getPageList(t, sql.toString(), inputV);
	}
	
	
	@Override
	public RwfbysqshModel getModel(String id) throws Exception {
        StringBuilder sql = new StringBuilder();
		sql.append("select * from (");
		sql.append("select t1.*,t3.mc yjxfmc,t10.yhmc ");
		sql.append("from XG_ZXDK_RWFBYDCSQB t1 ");
		sql.append(" left join xg_zxdk_xfdmb t3 on t1.yjxf=t3.dm ");
		sql.append(" left join dmk_yh t10 on t1.yhdm=t10.yhdm ) t where id=?");
		return super.getModel(sql.toString(),new String[]{id});
	}


	/**
	 * 
	 * @����:��֤�Ƿ�������
	 * @���ߣ�ChenQ[���ţ�856]
	 * @���ڣ�2015-9-7 ����06:16:47
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean isExists(RwfbysqshModel model) {
		String sql = "select count(1) count from XG_ZXDK_RWFBYDCSQB where xh = ?";
		String num = dao.getOneRs(sql, new String[]{model.getXh()}, "count");
		return Integer.parseInt(num) > 0?true:false;
	}
	
	@Override
	protected void setTableInfo() {
		super.setTableName("XG_ZXDK_RWFBYDCSQB");
		super.setKey("id");
		super.setClass(RwfbysqshModel.class);
	}


	/** 
	 * @����:��������ѧ��ѧ���ȡ��Դ�ش�����Ϣ(��������)
	 * @���ߣ�lgx[���ţ�1553]
	 * @���ڣ�2018-5-16 ����02:47:55
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws 
	 */
	public HashMap<String, String> getSyddkxx(String id) {
		StringBuilder sql = new StringBuilder();
		
		sql.append("select t2.*,t3.yhmc ");
		sql.append(" from XG_ZXDK_RWFBYDCSQB t1");
		sql.append(" left join  xg_zxdk_syddk t2  on t1.xh=t2.xh and t1.xn=t2.xn ");
		sql.append(" left join dmk_yh t3 on t2.dkyh = t3.yhdm ");
		sql.append("where t1.id = ?");
		return dao.getMapNotOut(sql.toString(), new String[]{id});
	}


	/** 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2018-5-16 ����02:54:57
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param id
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws 
	 */
	public HashMap<String, String> getXyddkxx(String id) {
		StringBuilder sql = new StringBuilder();
		
		sql.append("select * from (select t2.*,t3.yhmc ");
		sql.append(" from XG_ZXDK_RWFBYDCSQB t1 ");
		sql.append(" left join  xg_zdgxh_wxjk_jgb t2  on t1.xh=t2.xh and t1.xn=t2.xn ");
		sql.append(" left join dmk_yh t3 on t1.yhdm = t3.yhdm ");
		sql.append(" where t1.id = ? order by t2.xq) where rownum=1");
		return dao.getMapNotOut(sql.toString(), new String[]{id});
	}

}
