/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-1-7 ����04:10:55 
 */  
package xsgzgl.qgzx.mrgzkh.khsh;

import java.util.HashMap;
import java.util.List;

import common.newp.StringUtil;
import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ÿ�չ������˹���ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� xiaxia [����:1104]
 * @ʱ�䣺 2015-1-7 ����04:10:55 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class GzkhKhshDao extends SuperDAOImpl<GzkhKhshForm>{


	
	@Override
	public List<HashMap<String, String>> getPageList(GzkhKhshForm t) throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	
	@Override
	public List<HashMap<String, String>> getPageList(GzkhKhshForm t, User user) throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
//		String shgwzByUser = SearchService.getShgwzByUser(user, "t", "xydm", "bjdm");
//		String qxfw = SearchService.getQxfw(user, "t.gwid", "t.qxfw", "t.yrdw",searchTjByUser);
		StringBuffer sql = new StringBuffer();
		sql.append("select t.* from (");
		sql.append("select t1.sqid,t1.qxfw,t1.yxgs,t1.xh,t1.xn,t1.sqsj,t1.yrdw,t1.gwdm,t1.gs,t1.gzrq,t1.gzkssj,t1.gzjssj,t1.gzdd,t1.gznr,t1.splc,t1.bz,t4.shzt,t2.xm,t2.xydm,t2.xymc,t2.zydm,t2.zymc,t2.bjdm,t2.bjmc,t2.zybj,t2.zybjmc,t2.nj,t3.gwmc,");
		sql.append("(t1.gzrq||' '||t1.gzkssj||'ʱ'||'-'||t1.gzjssj||'ʱ')gzsj,nvl(t33.yrdwmc,t34.bmmc) yrdwmc,t35.gwxzmc, ");
		sql.append("t4.guid shid,t4.gwid,t4.shr,t4.shyj,t6.mc || '[' ||");
		sql.append("decode(t4.shzt, '0', 'δ���', '1', 'ͨ��', '2', '��ͨ��', '3',  '�˻�', '4', '������', '5', '�����') || ']' shztmc,t6.gwz, ");
		sql.append(" row_number() over(partition by t1.sqid order by t4.shsj desc) rn ");
		sql.append("from XG_QGZX_MRKH_SQB t1 left join view_xsjbxx t2 on t1.xh = t2.xh left join xg_qgzx_gwxxb t3 on t1.gwdm=t3.gwdm ");
		sql.append(" left join xg_qgzx_yrdwdmb t33 on t3.yrdwid=t33.id");
		sql.append(" left join ZXBZ_XXBMDM t34 on t33.xydm = t34.bmdm");
		sql.append(" left join XG_QGZX_GWXZDMB t35 on t3.gwlb = t35.gwxzdm");
		sql.append(" left join xg_xtwh_shztb t4 on t1.sqid = t4.ywid");
		sql.append(" left join xg_xtwh_spgwyh t5 on  t4.gwid = t5.spgw left join xg_xtwh_spgw t6 on t4.gwid = t6.id where t5.spyh ='" + user.getUserName() + "' ");
		String shlx = t.getShzt();
		if (!shlx.equals("dsh")) {
			sql.append(" and (t4.shzt<>0 and  t4.shzt<>4)");
		} else {
			sql.append(" and (t4.shzt=0  or t4.shzt = 4 )");
		}
		sql.append(" ) t where 1=1 ");
		sql.append(" and  rn = 1 ");
		sql.append(searchTj);

		if(!isAdmin(user)){
			sql.append(" and t.yrdw in ( select xydm from XG_QGZX_YRDWDMB where zgh = '" + user.getUserName() + "')");
		}
//		sql.append(qxfw);
//		sql.append(shgwzByUser);
		return getPageList(t, sql.toString(), inputV);
		
	}

	public boolean isAdmin(User user) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append("select zdm from yhb where yhm = ? ");
		String qxdmStr = dao.getOneRs(sql.toString(),new String[]{user.getUserName()},"zdm");
		if(StringUtil.isNull(qxdmStr)){
			return false;
		}
		String[] qxdms = qxdmStr.split(",");
		for(int i=0;i<qxdms.length;i++){
			sql = new StringBuilder();
			sql.append("select zmc from yhzb where zdm = ? ");
			String zmc = dao.getOneRs(sql.toString(),new String[]{qxdms[i]},"zmc");
			if(zmc.equals("��������Ա")){
				return true;
			}
		}
		return false;
	}
	/**
	 * 
	 * @����:���������Ϣ
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-1-7 ����06:23:14
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String> getKhshInfo(GzkhKhshForm t) {
		StringBuilder sql = new StringBuilder();
		sql.append("select t1.*,t2.xm,t2.xydm,t2.xymc,t2.zydm,t2.zymc,t2.bjdm,t2.bjmc,t2.nj,t3.gwmc,nvl(t4.yrdwmc,t5.bmmc) yrdwmc, ");
		sql.append(" (case when t1.yxgs is not null then t1.yxgs else t1.gs end) shgs,");
		sql.append("(t1.gzrq||' '||t1.gzkssj||'ʱ'||'-'||t1.gzjssj||'ʱ')gzsj,");
		sql.append(" decode(t1.shzt,  '0', 'δ���', '1', 'ͨ��', '2', '��ͨ��', '3', ");
		sql.append(" '�˻�', '4', '������', '5', '�����', '', '�������', ");
		sql.append(" t1.shzt) shztmc from XG_QGZX_MRKH_SQB t1 ");
		sql.append(" left join view_xsbfxx t2 on t1.xh = t2.xh left join xg_qgzx_gwxxb t3 on t1.gwdm=t3.gwdm");
		sql.append(" left join xg_qgzx_yrdwdmb t4 on t3.yrdwid=t4.id");
		sql.append(" left join ZXBZ_XXBMDM t5 on t4.xydm = t5.bmdm");
		sql.append(" where t1.sqid = ? ");
		return dao.getMapNotOut(sql.toString(), new String[] { t.getSqid() });
	}
	/**
	 * 
	 * @����:���������¼
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-1-20 ����06:57:10
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ywid
	 * @param tzhxmdm
	 * @param shzt
	 * @param dqxmdm
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean updateSqjl(String ywid, String shzt,String yxgs) throws Exception{
		String sql = "update XG_QGZX_MRKH_SQB set shzt=? ,yxgs=? where sqid = ?";
		
		return dao.runUpdate(sql, new String[]{shzt,yxgs,ywid});
		
	}
	
	@Override
	protected void setTableInfo() {
		super.setClass(GzkhKhshForm.class);
		super.setKey("sqid");
		super.setTableName("XG_QGZX_MRKH_SQB");
		
	}

}
