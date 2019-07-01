/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-3-8 ����02:26:24 
 */  
package xsgzgl.szdw.jtff.jtff;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����Դ[����:1206]
 * @ʱ�䣺 2016-3-8 ����02:26:24 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class JtffDao extends SuperDAOImpl<JtffForm> {

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(JtffForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(JtffForm t, User user)
			throws Exception {
		// TODO �Զ����ɷ������
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchGzjlTjByUser(user, "t", "xydm", "");
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* from (");
		sql.append(" select t.zgh,");
		sql.append("  t.ffny,");
		sql.append("  t.jsje,");
		sql.append("  t.ffje,");
		sql.append("  t.bz,");
		sql.append("  t.id,");
		sql.append("  t.gw gw,");
		sql.append("  t.dbrs dbrs,");
		sql.append(" (case (t1.xb)");
		sql.append("  when '1' then");
		sql.append(" '��'");
		sql.append(" when '2' then");
		sql.append(" 'Ů'");
		sql.append(" else");
		sql.append(" t1.xb");
		sql.append(" end) xb,");
		sql.append(" t1.xm,");
		sql.append(" t2.bmmc,");
		sql.append(" t2.bmdm,");
		sql.append(" t2.bmdm xydm");
//		sql.append(" t3.dbrs,");
//		sql.append(" t4.gw");
		sql.append(" from XG_SZDW_NEW_JTFFMXB t");
		sql.append(" left join fdyxxb t1");
		sql.append(" on t.zgh = t1.zgh");
		sql.append(" left join zxbz_xxbmdm t2");
		sql.append(" on t1.bmdm = t2.bmdm ");
//		sql.append(" left join");
//		sql.append(" ( select nvl(sum(dbrs),0)  dbrs,zgh from");
//		sql.append(" ( select  count(xh) dbrs,zgh from xsxxb a left join");
//		sql.append(" (select bjdm, zgh");
//		sql.append("  from fdybjb");
//		sql.append(" union");
//		sql.append(" select bjdm, zgh");
//		sql.append(" from bzrbbb) b");
//		sql.append(" on a.bjdm = b.bjdm");
//		sql.append(" group by zgh) group by zgh) t3");
//		sql.append(" on t.zgh = t3.zgh");
//		sql.append(" left join XG_SZDW_NEW_JTFFRYB t4");
//		sql.append(" on t.zgh = t4.zgh");
		sql.append(" order by t.ffny desc,t1.bmdm asc,t.gw asc,t.zgh asc");
		sql.append(" ");
		sql.append(" ");
		sql.append(" )t  where 1=1");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		// TODO �Զ����ɷ������
		super.setClass(JtffForm.class);
		super.setKey("id");
		super.setTableName("XG_SZDW_NEW_JTFFMXB");
	}
	
	/**
	 * 
	 * @����:�����ɽ��������·ݽ������ݲ�ѯ
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2016-3-11 ����02:45:11
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ffny
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getJtfflist(String ffny){
		StringBuilder sql = new StringBuilder();
		sql.append(" select x.*, nvl(x.ffje, x.jsje) xsje");
		sql.append(" from (select y.zgh,");
		sql.append(" y.gw,");
		sql.append(" y.jtlb,");
		sql.append(" y.bz,");
		sql.append(" y.gdffje,");
		sql.append(" y.xb,");
		sql.append(" y.xm,");
		sql.append(" y.bmmc,");
		sql.append(" y.bmdm,");
		sql.append(" y.dbrs,");
		sql.append(" y.ffny,");
		sql.append(" y.ffje,");
		sql.append(" case ");
		sql.append(" when y.jtlb = 'gd' then");
		sql.append(" y.jsje");
		sql.append(" when y.jtlb = 'zc' then");
		sql.append(" case");
		sql.append(" when (y.gw = 'ѧԺרְ����Ա' or y.gw = '�������Ա') then");
		sql.append(" case");
		sql.append(" when y.dbrs > 0 then");
		sql.append(" to_char(280 + 180 +");
		sql.append(" decode(sign(y.dbrs - 90), -1, 0, y.dbrs - 90) * 2)");
		sql.append(" when y.dbrs = 0 then");
		sql.append(" to_char(280)");
		sql.append(" else");
		sql.append(" y.jsje");
		sql.append(" end");
		sql.append(" when (y.gw = '���ż�ְ����Ա') then");
		sql.append(" case");
		sql.append(" when y.dbrs > 0 then");
		//sql.append(" to_char(280 + 180)");
		sql.append(" to_char(190 + 180 + decode(sign(y.dbrs-40),-1,0,y.dbrs-40) * 2)");
		sql.append(" when y.dbrs = 0 then");
		//sql.append(" '280'");
		sql.append(" '180'");
		sql.append(" else");
		sql.append(" y.jsje");
		sql.append(" end");
		sql.append(" when (y.gw = 'ѧԺ�����') then");
		sql.append(" to_char(280 + 180 +");
		sql.append(" decode(sign(y.dbrs - 90), -1, 0, y.dbrs - 90) * 2)");
		sql.append(" when (y.gw = '������') then");
		sql.append(" to_char(100 + 90 +");
		sql.append(" decode(sign(y.dbrs - 40), -1, 0, y.dbrs - 40) * 2)");
		sql.append(" when (y.gw = '����ѧԺ��̨�۰İ�����') then");
		sql.append(" to_char(90 +");
		sql.append(" decode(sign(y.dbrs - 40), -1, 0, y.dbrs - 40) * 2)");
		sql.append(" when (y.gw = 'һ�ٶ��ٸ���Ա') then");
		sql.append(" '280' ");
		sql.append(" else");
		sql.append(" y.jsje");
		sql.append(" end");
		sql.append(" else");
		sql.append(" y.jsje");
		sql.append(" end jsje");
		sql.append(" from (select t.*,");
		sql.append(" (case (t1.xb)");
		sql.append(" when '1' then");
		sql.append(" '��'");
		sql.append(" when '2' then");
		sql.append(" 'Ů'");
		sql.append(" else");
		sql.append(" t1.xb");
		sql.append(" end) xb,");
		sql.append(" t1.xm,");
		sql.append(" t2.bmmc,");
		sql.append(" t2.bmdm,");
		sql.append(" t2.bmdm xydm,");
		sql.append(" t3.dbrs,");
		sql.append(" t4.ffny,");
		sql.append(" case t.jtlb");
		sql.append(" when 'zc' then");
		sql.append(" t4.ffje");
		sql.append(" when 'gd' then");
		sql.append(" t.gdffje");
		sql.append(" else");
		sql.append(" t4.ffje");
		sql.append(" end ffje,");
		sql.append(" case t.jtlb");
		sql.append(" when 'zc' then");
		sql.append(" t4.jsje");
		sql.append(" when 'gd' then");
		sql.append(" t.gdffje");
		sql.append(" else");
		sql.append(" t4.jsje");
		sql.append(" end jsje");
		sql.append(" from XG_SZDW_NEW_JTFFRYB t");
		sql.append(" left join fdyxxb t1");
		sql.append(" on t.zgh = t1.zgh");
		sql.append(" left join zxbz_xxbmdm t2");
		sql.append(" on t1.bmdm = t2.bmdm");
		sql.append(" left join (select nvl(sum(n.dbrs), 0) dbrs, m.zgh");
		sql.append(" from fdyxxb m left join  ");
		sql.append(" (select count(xh) dbrs, zgh");
		sql.append(" from view_xsjbxx a");
		sql.append(" left join (select bjdm, zgh");
		sql.append(" from fdybjb");
		sql.append(" union");
		sql.append(" select bjdm, zgh");
		sql.append(" from bzrbbb) b");
		sql.append(" on a.bjdm = b.bjdm");
		sql.append(" group by zgh) n on m.zgh = n.zgh");
		sql.append(" group by m.zgh) t3");
		sql.append(" on t.zgh = t3.zgh");
		sql.append(" left join XG_SZDW_NEW_JTFFMXB t4");
		sql.append(" on t.zgh = t4.zgh");
		sql.append(" where t4.ffny = ?");
		sql.append(" order by  t1.bmdm asc, t.gw asc, t.zgh asc) y) x");
		return dao.getListNotOut(sql.toString(), new String[]{ffny});
	}
	
	/**
	 * 
	 * @����:�����ɽ��������·ݽ������ݲ�ѯ
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2016-3-11 ����02:45:11
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ffny
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getWscJtfflist(String ffny){
		StringBuilder sql = new StringBuilder();
		sql.append("  select x.*,  x.jsje xsje,x.jsje ffje");
		sql.append("  from (select y.zgh,");
		sql.append("  y.gw,");
		sql.append("  y.jtlb,");
		sql.append("  y.bz,");
		sql.append("  y.gdffje,");
		sql.append("  y.xb,");
		sql.append("  y.xm,");
		sql.append("  y.bmmc,");
		sql.append("  y.bmdm,");
		sql.append("  y.dbrs,");
		sql.append("  y.ffny,");
		sql.append("  case");
		sql.append("  when y.jtlb = 'gd' then");
		sql.append("  y.gdffje");
		sql.append("   when y.jtlb = 'zc' then");
	    sql.append("   case");
		sql.append("  when (y.gw = 'ѧԺרְ����Ա' or y.gw = '�������Ա') then");
		sql.append("  case");
		sql.append("  when y.dbrs > 0 then");
		sql.append("  to_char(280 + 180 +");
		sql.append("  decode(sign(y.dbrs - 90), -1, 0, y.dbrs - 90) * 2)");
		sql.append("  when y.dbrs = 0 then");
		sql.append("  to_char(280)");
		sql.append("  else");
		sql.append("  to_char(280)");
		sql.append("  end");
		sql.append("  when (y.gw = '���ż�ְ����Ա') then");
		sql.append("  case");
		sql.append("  when y.dbrs > 0 then");
		sql.append("  to_char(190 + 180 + decode(sign(y.dbrs-40),-1,0,y.dbrs-40) * 2) ");
		//sql.append("  to_char(280 + 180)");
		sql.append("  when y.dbrs = 0 then");
		sql.append("  '180'");
		//sql.append("  '280'");
		sql.append("  else");
		sql.append(" '180'");
		//sql.append("  '280'");
		sql.append("  end");
	    sql.append("  when (y.gw = 'ѧԺ�����') then");
		sql.append("  to_char(280 + 180 +");
		sql.append("  decode(sign(y.dbrs - 90), -1, 0, y.dbrs - 90) * 2)");
		sql.append("  when (y.gw = '������') then");
		sql.append("  to_char(100 + 90 +");
		sql.append("  decode(sign(y.dbrs - 40), -1, 0, y.dbrs - 40) * 2)");
		sql.append("  when (y.gw = '����ѧԺ��̨�۰İ�����') then");
		sql.append("  to_char(90 +");
		sql.append("  decode(sign(y.dbrs - 40), -1, 0, y.dbrs - 40) * 2)");
		sql.append(" when (y.gw = 'һ�ٶ��ٸ���Ա') then");
		sql.append(" '280' ");
	    sql.append("  end");
		sql.append("  end jsje");
		sql.append("  from (select t.*,");
		sql.append("  (case (t1.xb)");
		sql.append("  when '1' then");
		sql.append("  '��'");
		sql.append("   when '2' then");
		sql.append("  'Ů'");
		sql.append("  else");
		sql.append("  t1.xb");
		sql.append("  end) xb,");
		sql.append("  t1.xm,");
		sql.append("  t2.bmmc,");
		sql.append("  t2.bmdm,");
		sql.append("  t2.bmdm xydm,");
		sql.append("  t3.dbrs,");
		sql.append("  ? ffny");
		sql.append("   from XG_SZDW_NEW_JTFFRYB t");
		sql.append("   left join fdyxxb t1");
		sql.append("   on t.zgh = t1.zgh");
		sql.append("   left join zxbz_xxbmdm t2");
		sql.append("   on t1.bmdm = t2.bmdm");
		sql.append("   left join (select nvl(sum(n.dbrs), 0) dbrs, m.zgh");
		sql.append("   from fdyxxb m left join  ");
		sql.append("   (select count(xh) dbrs, zgh");
		sql.append("   from view_xsjbxx a");
        sql.append("   left join (select bjdm, zgh");
	    sql.append("   from fdybjb");
		sql.append("   union");
		sql.append("   select bjdm, zgh");
		sql.append("   from bzrbbb) b");
		sql.append("   on a.bjdm = b.bjdm");
		sql.append("   group by zgh) n on m.zgh = n.zgh ");
		sql.append("   group by m.zgh) t3");
		sql.append("   on t.zgh = t3.zgh");
		sql.append("   order by t1.bmdm asc, t.gw asc, t.zgh asc) y) x");
		return dao.getListNotOut(sql.toString(), new String[]{ffny});
	}
	
	/**
	 * ��ȡ�������������
	 */
	public List<HashMap<String, String>> getLastThreeMonth(){
		StringBuilder sql = new StringBuilder();
		sql.append(" select to_char(add_months(trunc(sysdate),-2),'yyyymm') ny from dual");
		sql.append(" union");
		sql.append(" select to_char(add_months(trunc(sysdate),-1),'yyyymm') ny from dual");
		sql.append(" union");
		sql.append(" select to_char(trunc(sysdate),'yyyymm') ny from dual");
		return dao.getListNotOut(sql.toString(), new String[]{});
	}
	
	/**
	 *��֤�Ƿ�������
	 */
	public boolean isExist(String ffny){
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1) result from XG_SZDW_NEW_JTFFMXB where ffny = ?");
		sql.append(" ");
		sql.append(" ");
		int  result= Integer.parseInt(dao.getOneRs(sql.toString(), new String[]{ffny}, "result"));
		return result > 0 ? true:false;
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @����:����ǰ��ɾ��
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2016-3-11 ����04:52:50
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ffny
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean deleteRows(String ffny) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" delete from XG_SZDW_NEW_JTFFMXB where ffny = ?");
		return dao.runUpdate(sql.toString(),new String[]{ffny});
	}
}
