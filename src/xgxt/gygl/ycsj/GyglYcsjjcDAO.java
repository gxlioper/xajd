package xgxt.gygl.ycsj;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.gygl.GyglCommForm;
import xgxt.utils.CommonQueryDAO;

public class GyglYcsjjcDAO extends DAO {

	/**
	 * ����סͬһ�Ŵ��쳣��������
	 * @return
	 * @throws SQLException 
	 */
	public int getCountByDrtcw() throws SQLException{
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select count(1) count ")
		   .append(" from (select lddm, qsh, cs, cwh, count(1) c")
		   .append(" from xszsxxb group by lddm, qsh, cs, cwh) b")
		   .append(" where b.c > 1 ");
		
		return getOneRsint(sql.toString());
	}
	
	
	/**
	 * ������ֵ 
	 * @param data
	 * @param topTr
	 * @return
	 */
	private HashMap<String,Object> getResult(List<String[]> data,String[] topTr){
		
		HashMap<String,Object> map = new HashMap<String,Object>();
		
		map.put("data", data);
		map.put("topTr", topTr);
		
		return map;
	}
	
	
	/**
	 * ����סͬһ�Ŵ����쳣����
	 * @return
	 */
	public HashMap<String,Object> getZsxxByDrtcw(GyglCommForm model){
		//����xszsxxb��ɾ���쳣���ݣ����� xh
		StringBuilder sql = new StringBuilder();
		String[] colList = new String[]{"pkValue","xh","xm","lddm","ldmc","qsh","cs","rzrq"};
		String[] topTr = new String[]{"pkValue","ѧ��","����","¥������","¥������","���Һ�","¥��","��ס����"};
		
		sql.append(" select rownum r,xh pkValue,a.xh,a.lddm,a.qsh,a.cs,a.rzrq,v.xm,s.ldmc from xszsxxb a ")
		   .append(" left join sslddmb s on a.lddm=s.lddm ")
		   .append(" left join view_xsjbxx v on a.xh=v.xh")
		   .append(" where  exists (select 1 from (select lddm, qsh, cs, cwh, count(1) c")
		   .append(" from xszsxxb group by lddm, qsh, cs, cwh) b where b.c > 1")
		   .append(" and a.lddm = b.lddm and a.qsh = b.qsh and a.cs = b.cs and a.cwh = b.cwh)");
		
		return getResult(CommonQueryDAO.commonQuery(model.getPages(), sql.toString(), new String[]{}, colList),topTr);
	}
	
	
	/**
	 * ��������ס����������������Ĵ�λ��������������
	 * @return
	 * @throws SQLException 
	 */
	public int getCountByZgcws () throws SQLException{
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select count(1) c from xszsxxb t where exists")
		   .append(" (select 1 from(select a.rzrs,a.lddm,a.qsh,a.cs,b.cws from")
		   .append(" (select count(1) rzrs,lddm,qsh,cs from xszsxxb group by lddm,qsh,cs) a ")
		   .append(" left join ssxxb b on a.lddm=b.lddm and a.qsh=b.qsh and a.cs=b.cs")
		   .append(" ) c where c.rzrs>c.cws and t.lddm=c.lddm and t.qsh=c.qsh and t.cs=c.cs)");
		
		return getOneRsint(sql.toString());
	}
	
	
	/**
	 * ��������ס����������������Ĵ�λ�����������е�ѧ��
	 * @return
	 */
	public HashMap<String,Object>  getZsxxByZgcws(GyglCommForm model){
		//�쳣���ݴ��ڱ� xszsxxb ������ xh
		StringBuilder sql = new StringBuilder();
		String[] colList = new String[]{"pkValue","xh","xm","lddm","ldmc","qsh","cs","rzrq"};
		String[] topTr = new String[]{"pkValue","ѧ��","����","¥������","¥������","���Һ�","¥��","��ס����"};
		
		sql.append(" select rownum r,t.xh pkValue,t.xh,c.xm,t.lddm,b.ldmc,t.qsh,t.cs,t.rzrq from xszsxxb t ")
		   .append(" left join sslddmb b on t.lddm=b.lddm")
		   .append(" left join view_xsjbxx c on t.xh=c.xh")
		   .append(" where exists (select 1 from(")
		   .append(" select a.rzrs,a.lddm,a.qsh,a.cs,b.cws from")
		   .append(" (select count(1) rzrs,lddm,qsh,cs from xszsxxb group by lddm,qsh,cs) a ")
		   .append(" left join ssxxb b on a.lddm=b.lddm and a.qsh=b.qsh and a.cs=b.cs")
		   .append(" ) c where c.rzrs>c.cws and t.lddm=c.lddm and t.qsh=c.qsh and t.cs=c.cs)");
		
		return getResult(CommonQueryDAO.commonQuery(model.getPages(), sql.toString(), new String[]{}, colList),topTr);
	}
	
	
	/**
	 * ������ı�����������
	 * @return
	 * @throws SQLException 
	 */
	public int getCountByFpblqs() throws SQLException{
		
		String sql = "select count(1) count from ssxxb a where a.fpbj='����' and exists " +
				"(select 1 from xg_gygl_qsfpb b where a.lddm=b.lddm and a.qsh=b.qsh and a.cs=b.cs)";
		
		return getOneRsint(sql);
	}
	
	
	/**
	 * ������Ϣ�еı�������
	 * @return
	 */
	public HashMap<String,Object> getFpxxByFpblqs(GyglCommForm model){
		
		//�쳣������ xg_gygl_qsfpb ������ lddm||cs||qsh
		StringBuilder sql = new StringBuilder();
		String[] colList = new String[]{"pkValue","lddm","ldmc","cs","qsh","fpdx"};
		String[] topTr = new String[]{"pkValue","¥������","¥������","¥��","���Һ�","�������"};
		
		sql.append(" select rownum r,t.lddm||t.cs||t.qsh pkValue,t.lddm,s.ldmc,t.cs,t.qsh,t.fpdx,")
		   .append(" t.bmdm from xg_gygl_qsfpb t left join sslddmb s on t.lddm=s.lddm")
		   .append(" where exists (select 1 from ssxxb a ")
		   .append(" where a.fpbj='����' and t.lddm=a.lddm and t.qsh=a.qsh and t.cs=a.cs)");
		
		return getResult(CommonQueryDAO.commonQuery(model.getPages(), sql.toString(), new String[]{}, colList),topTr);
	}
	
	/**
	 * ��������ס�˶�����
	 * @return
	 * @throws SQLException 
	 */
	public int getCountByBlqszr() throws SQLException{
		
		String sql = "select count(1) count from xszsxxb t where exists " +
				"(select 1 from ssxxb a where a.fpbj='����' and t.lddm=a.lddm and t.qsh=a.qsh and t.cs=a.cs)";
		
		return getOneRsint(sql);
	}
	
	
	/**
	 * ס�ڱ��������е���
	 * @return
	 */
	public HashMap<String,Object> getZsxxByBlqszr(GyglCommForm model){
		//�쳣���ݴ��ڱ� xszsxxb������ xh
		StringBuilder sql = new StringBuilder();
		String[] colList = new String[]{"pkValue","xh","xm","lddm","ldmc","cs","qsh","cwh"};
		String[] topTr = new String[]{"pkValue","ѧ��","����","¥������","¥������","¥��","���Һ�","��λ��"};
		
		sql.append(" select rownum r,t.xh pkValue,t.xh,v.xm,t.lddm,s.ldmc,t.qsh,t.cs,t.cwh from xszsxxb t ")
		   .append(" left join sslddmb s on t.lddm=s.lddm")
		   .append(" left join view_xsjbxx v on t.xh=v.xh where exists ")
		   .append(" (select 1 from ssxxb a where a.fpbj='����' and ")
		   .append(" t.lddm=a.lddm and t.qsh=a.qsh and t.cs=a.cs)");
		
		return getResult(CommonQueryDAO.commonQuery(model.getPages(), sql.toString(), new String[]{}, colList),topTr);
	}
	
	
	/**
	 * �����˱����������λ����
	 * @return
	 * @throws SQLException 
	 */
	public int getCountByXlcwfp() throws SQLException{
		
		String sql = "select count(1) count from xszsxxb t where exists (select 1 from " +
				"xg_gygl_qtcwxxb b where b.cwbj='���λ' and t.lddm=b.lddm and t.qsh=b.qsh " +
				"and t.cs=b.cs and t.cwh=b.cwh)";
		
		return getOneRsint(sql);
	}
	
	
	/**
	 * ����ס�˵����λ
	 * @return
	 */
	public HashMap<String,Object> getZsxxByXlcwfp(GyglCommForm model){
		
		//�쳣�����ڱ� xszsxxb������ xh
		StringBuilder sql = new StringBuilder();
		String[] colList = new String[]{"pkValue","xh","xm","lddm","ldmc","cs","qsh","cwh"};
		String[] topTr = new String[]{"pkValue","ѧ��","����","¥������","¥������","¥��","���Һ�","��λ��"};
		
		sql.append(" select rownum r,t.xh pkValue,t.xh,v.xm,t.lddm,s.ldmc,t.qsh,t.cs,t.cwh from xszsxxb t ")
		   .append(" left join sslddmb s on t.lddm=s.lddm")
		   .append(" left join view_xsjbxx v on t.xh=v.xh")
		   .append(" where exists (select 1 from xg_gygl_qtcwxxb b where b.cwbj='���λ' ")
		   .append(" and t.lddm=b.lddm and t.qsh=b.qsh and t.cs=b.cs and t.cwh=b.cwh)");
		
		return getResult(CommonQueryDAO.commonQuery(model.getPages(), sql.toString(), new String[]{}, colList),topTr);
	}
	
	
	/**
	 * ���Ա𲻷��ִ�������ж�����
	 * @return
	 * @throws SQLException 
	 */
	public int getCountByXbyw() throws SQLException{
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select count(1) count from (")
		   .append("select a.lddm,a.qsh,a.cs,a.xh,b.xb from xszsxxb a left join view_xsjbxx b on a.xh=b.xh")
		   .append(") a where exists (select 1 from ssxxb b where a.lddm=b.lddm and a.qsh=b.qsh and a.cs=b.cs and a.xb<>b.xb)");
		
		return getOneRsint(sql.toString());
	}
	
	
	/**
	 * ���Ա𲻷��ִ��������
	 * @return
	 */
	public HashMap<String,Object> getZsxxByXbyw(GyglCommForm model){
		
		//�쳣�����ڱ� xszsxxb������ xh
		StringBuilder sql = new StringBuilder();
		String[] colList = new String[]{"pkValue","xh","xm","xb","lddm","ldmc","qsh","cs"};
		String[] topTr = new String[]{"pkValue","ѧ��","����","�Ա�","¥������","¥������","���Һ�","¥��"};
		
		sql.append(" select rownum r,a.xh pkValue,a.lddm,a.qsh,a.cs,a.xh,a.xb,c.ldmc,v.xm from(")
		   .append(" select a.lddm,a.qsh,a.cs,a.xh,")
		   .append(" b.xb from xszsxxb a left join view_xsjbxx b on a.xh=b.xh")
		   .append(" ) a left join sslddmb c on a.lddm=c.lddm left join view_xsjbxx v on a.xh=v.xh")
		   .append(" where exists (select 1 from ssxxb b")
		   .append(" where a.lddm=b.lddm and a.qsh=b.qsh and a.cs=b.cs and a.xb<>b.xb)");
		
		return getResult(CommonQueryDAO.commonQuery(model.getPages(), sql.toString(), new String[]{}, colList),topTr);
	}
	
	
	/**
	 * ������¥��Ҫ���Ա����������
	 * @return
	 * @throws SQLException 
	 */
	public int getCountByLdxbbf() throws SQLException{
		
		String sql = "select count(1) count from ssxxb a where exists " +
				"(select 1 from sslddmb b where a.lddm=b.lddm and a.xb<>b.xbxd and b.xbxd<>'���')";
		
		return getOneRsint(sql.toString());
	}
	
	
	/**
	 * ������¥��Ҫ���Ա������
	 * @return
	 */
	public HashMap<String,Object> getSsxxByLdxbbf(GyglCommForm model){
		
		//�쳣���ݴ��ڱ� ssxxb������ lddm||cs||qsh
		StringBuilder sql = new StringBuilder();
		String[] colList = new String[]{"pkValue","lddm","ldmc","qsh","cs","cws","xb"};
		String[] topTr = new String[]{"pkValue","¥������","¥������","���Һ�","¥��","��λ��","�Ա�"};
		
		sql.append(" select rownum r,a.lddm||a.cs||a.qsh pkValue,a.lddm,")
		   .append(" c.ldmc,a.qsh,a.cs,a.cws,a.xb from ssxxb a ")
		   .append(" left join sslddmb c on a.lddm=c.lddm")
		   .append(" where exists (select 1 from sslddmb b ")
		   .append(" where a.lddm=b.lddm and a.xb<>b.xbxd and b.xbxd<>'���')");
		
		return getResult(CommonQueryDAO.commonQuery(model.getPages(), sql.toString(), new String[]{}, colList),topTr);
	}
	
	
	/**
	 * ��������¥����߲����������ж��ٸ�
	 * @return
	 * @throws SQLException 
	 */
	public int getCountByCgzgcs() throws SQLException{
	
		StringBuilder sql = new StringBuilder();
		
		sql.append("select count(1) count from ssxxb t where exists (select 1 from (select a.lddm,max(to_number(a.cs)) cs,")
		   .append("(select b.cs from sslddmb b where a.lddm=b.lddm) ldcs ")
		   .append(" from ssxxb a group by lddm) c where c.cs>c.ldcs and t.lddm=c.lddm and t.cs=c.cs)");
		
		return getOneRsint(sql.toString());
	}
	
	
	/**
	 * ��������¥����߲�����������Ϣ
	 * @return
	 */
	public HashMap<String,Object> getSsxxByCgzgcs(GyglCommForm model){
		
		//�쳣�����ڱ� ssxxb������ lddm||cs||qsh
		StringBuilder sql = new StringBuilder();
		String[] colList = new String[]{"pkValue","lddm","ldmc","cs","zgcs","qsh","cws"};
		String[] topTr = new String[]{"pkValue","¥������","¥������","¥��","���¥��","���Һ�","��λ��"};
		
		sql.append(" select rownum r,t.lddm||t.cs||t.qsh pkValue,t.lddm,s.ldmc,")
		   .append(" t.cs,s.cs zgcs,t.qsh,t.cws from ssxxb t ")
		   .append(" left join sslddmb s on t.lddm=s.lddm")
		   .append(" where exists (select 1 from (select a.lddm,max(to_number(a.cs)) cs,")
		   .append(" (select b.cs from sslddmb b where a.lddm=b.lddm) ldcs")
		   .append(" from ssxxb a group by lddm) c where c.cs>c.ldcs and t.lddm=c.lddm and t.cs=c.cs)");
		
		return getResult(CommonQueryDAO.commonQuery(model.getPages(), sql.toString(), new String[]{}, colList),topTr);
	}
	
	
	/**
	 * ����¥����߲����Ĵ�λ��Ϣ����
	 * @return
	 * @throws SQLException
	 */
	public int getCountByCgzgcscw() throws SQLException{
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select count(1) count from xg_gygl_cwxxb t where ")
		   .append(" exists (select 1 from (select a.lddm,max(to_number(a.cs)) cs,")
		   .append("(select b.cs from sslddmb b where a.lddm=b.lddm) ldcs ")
		   .append("from xg_gygl_cwxxb a group by lddm) c where c.cs>c.ldcs and t.lddm=c.lddm and t.cs=c.cs)");
	
		return getOneRsint(sql.toString());
	}
	
	
	/**
	 * ��������¥����߲����Ĵ�λ��Ϣ
	 * @return
	 */
	public HashMap<String,Object> getCwxxByCgzgcs(GyglCommForm model){
		
		//�쳣�������ڱ� xg_gygl_cwxxb ������ lddm||cs||qsh||cwh
		StringBuilder sql = new StringBuilder();
		String[] colList = new String[]{"pkValue","lddm","ldmc","qsh","cwh","cs","zgcs"};
		String[] topTr = new String[]{"pkValue","¥������","¥������","���Һ�","��λ��","¥��","���¥��"};
		
		sql.append(" select rownum r,t.lddm||t.cs||t.qsh||t.cwh pkValue,t.lddm,t.cs,t.qsh, ")
		   .append(" s.ldmc,s.cs zgcs,t.cwh from xg_gygl_cwxxb t")
		   .append(" left join sslddmb s on t.lddm=s.lddm")
		   .append(" where exists (select 1 from (select a.lddm,max(to_number(a.cs)) cs,")
		   .append(" (select b.cs from sslddmb b where a.lddm=b.lddm) ldcs")
		   .append(" from xg_gygl_cwxxb a group by lddm) c ")
		   .append(" where c.cs>c.ldcs and t.lddm=c.lddm and t.cs=c.cs)");
		
		return getResult(CommonQueryDAO.commonQuery(model.getPages(), sql.toString(), new String[]{}, colList),topTr);
	}
	
	
	/**
	 * ����¥����߲�����������λ��Ϣ����
	 * @return
	 * @throws SQLException
	 */
	public int getCountByCgzgcsqtcw() throws SQLException{
		
		StringBuilder sql = new StringBuilder();
		sql.append("select count(1) count from xg_gygl_qtcwxxb t where")
		   .append(" exists (select 1 from (select a.lddm,max(to_number(a.cs)) cs,")
		   .append("(select b.cs from sslddmb b where a.lddm=b.lddm) ldcs ")
		   .append(" from xg_gygl_qtcwxxb a group by lddm) c where c.cs>c.ldcs and t.lddm=c.lddm and t.cs=c.cs)");
		
		return getOneRsint(sql.toString());
	}
	
	
	/**
	 * ��������¥����߲�����������λ��Ϣ
	 * @return
	 */
	public HashMap<String,Object> getQtcwxxByCgzgcs(GyglCommForm model){
		
		//�쳣�������ڱ� xg_gygl_qtcwxxb ������ lddm||cs||qsh||cwh
		StringBuilder sql = new StringBuilder();
		String[] colList = new String[]{"pkValue","lddm","ldmc","cs","zgcs","qsh","cwh"};
		String[] topTr = new String[]{"pkValue","¥������","¥������","¥��","���¥��","���Һ�","��λ��"};
		
		sql.append(" select rownum r,t.lddm||t.cs||t.qsh||t.cwh pkValue,t.lddm,t.cs,")
		   .append(" t.qsh,t.cwh,s.ldmc,s.cs zgcs from xg_gygl_qtcwxxb t ")
		   .append(" left join sslddmb s on t.lddm=s.lddm")
		   .append(" where exists (select 1 from (select a.lddm,max(to_number(a.cs)) cs,")
		   .append(" (select b.cs from sslddmb b where a.lddm=b.lddm) ldcs ")
		   .append(" from xg_gygl_qtcwxxb a group by lddm) c ")
		   .append(" where c.cs>c.ldcs and t.lddm=c.lddm and t.cs=c.cs)");
		return getResult(CommonQueryDAO.commonQuery(model.getPages(), sql.toString(), new String[]{}, colList),topTr);
	}
	
	
	/**
	 * ���Ų��ɻ�ס�ִ������������
	 * @return
	 * @throws SQLException 
	 */
	public int getCountBybmbkhz() throws SQLException{
		
		String fpdx = getOneRs("select fpdx from xg_gygl_jbszb", new String[]{}, "fpdx");
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select count(1) c from(select a.*,b.kfhz from xg_gygl_qsfpb a ")
		   .append(" left join ssxxb b on a.lddm=b.lddm and a.qsh=b.qsh and a.cs=b.cs")
		   .append(" ) c where c.kfhz='����' and ")
		   .append(" exists (select 1 from (select a.lddm,a.qsh,a.cs,b.xydm,b.zydm,")
		   .append(" b.bjdm,b.nj from xszsxxb a left join view_xsjbxx b on a.xh=b.xh) d ")
		   .append(" where d.lddm=c.lddm and d.qsh=c.qsh and d.cs=c.cs ");
		
		if ("xy".equals(fpdx)){
			sql.append(" and d.xydm<>c.bmdm");
		} else if ("njxy".equals(fpdx)){
			sql.append(" and d.nj||d.xydm<>c.bmdm");
		} else if ("njzy".equals(fpdx)){
			sql.append(" and d.nj||d.zydm<>c.bmdm");
		} else if ("bj".equals(fpdx)){
			sql.append(" and d.bjdm<>c.bmdm");
		}
		sql.append(")");
		
		return getOneRsint(sql.toString());
	}
	
	
	/**
	 * ���Ų��ɻ�ס������������ѧ��
	 * @return
	 */
	public HashMap<String,Object> getSsxxBybmbkhz(GyglCommForm model){
		
		String fpdx = getOneRs("select fpdx from xg_gygl_jbszb", new String[]{}, "fpdx");
		
		StringBuilder sql = new StringBuilder();
		String[] colList = new String[]{"pkValue","xh","xm","lddm","ldmc","qsh","cs"};
		String[] topTr = new String[]{"pkValue","ѧ��","����","¥������","¥������","���Һ�","¥��"};
		
		sql.append(" select rownum r,a.xh pkValue,a.xh,a.xm,a.lddm,s.ldmc,a.qsh,a.cs from (")
		   .append(" select a.xh,b.xm,a.lddm,a.qsh,a.cs,b.xydm,b.zydm,b.bjdm,b.nj from xszsxxb a")
		   .append(" left join view_xsjbxx b on a.xh=b.xh) a ")
		   .append(" left join sslddmb s on a.lddm=a.lddm")
		   .append(" where exists (select 1 from (select a.*,b.kfhz from xg_gygl_qsfpb a ")
		   .append(" left join ssxxb b on a.lddm=b.lddm and a.qsh=b.qsh and a.cs=b.cs")
		   .append(" ) c where c.kfhz='����' and a.lddm=c.lddm and a.qsh=c.qsh and a.cs=c.cs");
		
		if ("xy".equals(fpdx)){
			sql.append(" and a.xydm<>c.bmdm");
		} else if ("njxy".equals(fpdx)){
			sql.append(" and a.nj||a.xydm<>c.bmdm");
		} else if ("njzy".equals(fpdx)){
			sql.append(" and a.nj||a.zydm<>c.bmdm");
		} else if ("bj".equals(fpdx)){
			sql.append(" and a.bjdm<>c.bmdm");
		}
		
		sql.append(" )");
		return getResult(CommonQueryDAO.commonQuery(model.getPages(), sql.toString(), new String[]{}, colList),topTr);
	}
	
	
	/**
	 * ������Ϣ�����ϻ��������з��䷽ʽ����������
	 * @return
	 * @throws SQLException 
	 */
	public int getCountByJbszbf() throws SQLException{
		
		String sql = "select count(1) count from xg_gygl_qsfpb a where a.fpdx <>(select fpdx from xg_gygl_jbszb)";
		
		return getOneRsint(sql.toString());
	}
	
	
	/**
	 * ������Ϣ�����ϻ��������з��䷽ʽ������
	 * @return
	 */
	public HashMap<String,Object> getFbxxByJbszbf(GyglCommForm model){
		
		//�쳣�������ڱ� xg_gygl_qsfpb ������ lddm||cs||qsh
		StringBuilder sql = new StringBuilder();
		String[] colList = new String[]{"pkValue","lddm","ldmc","cs","qsh"};
		String[] topTr = new String[]{"pkValue","¥������","¥������","¥��","���Һ�"};
		
		sql.append(" select rownum r,a.lddm||a.cs||a.qsh pkValue,a.lddm,a.cs,a.qsh,")
		   .append(" a.fpdx,b.ldmc from xg_gygl_qsfpb a ")
		   .append(" left join sslddmb b on a.lddm=b.lddm")
		   .append(" where a.fpdx <>(select fpdx from xg_gygl_jbszb)");
		
		return getResult(CommonQueryDAO.commonQuery(model.getPages(), sql.toString(), new String[]{}, colList),topTr);
	}
	
	
	
	public boolean delYcsj(String tableName, String pkKey, String[] pkValues) throws Exception {
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" delete from ")
		   .append(tableName)
		   .append(" where ")
		   .append(pkKey)
		   .append(" in (");
		
		for (int i = 0 ; i < pkValues.length ; i++){
			sql.append("'")
			   .append(pkValues[i])
			   .append("'");
			   
			if(i != pkValues.length-1){
				sql.append(",");
			}
		}
		sql.append(")");
		return runUpdate(sql.toString(), new String[]{});
	}
}
