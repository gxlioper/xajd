package com.zfsoft.xgxt.xszz.knsrd;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.xpjpy.wdpj.sqsh.SqshDao;
import com.zfsoft.xgxt.xszz.knsjcsz.JcszDao;
import com.zfsoft.xgxt.xszz.knsjcsz.JcszForm;

/**
 * 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ѧ����������ģ��
 * @�๦������: ѧ������2013��--�������϶� 
 * @���ߣ� Penghui.Qu 
 * @ʱ�䣺 2013-4-22 ����09:03:12 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class KnsrdDao extends SuperDAOImpl<KnsrdForm> {

	private static final String DSH = "dsh";
	private static final String YSH = "ysh";
	
	public List<HashMap<String, String>> getPageList(KnsrdForm t)
			throws Exception {
		return null;
	}
	public List<HashMap<String, String>> getPageList(KnsrdForm t, User user)
			throws Exception {
		
		//���ɸ߼���ѯ�������������ֵ 
		if("xn".equals(MessageUtil.getText("xszz.knsrd.sqzq"))){
			t.getSearchModel().setSearch_tj_xq(null);
		}
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t1", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		
		StringBuilder sql = new StringBuilder();
		/*sql.append("select t1.* from (");
		sql.append("select t1.guid,t1.xh,t1.shlc,t2.xm,t2.xb,t2.nj,t2.xymc,t2.zymc,");
		sql.append("t2.bjmc,t2.zzmmmc,t2.mzmc,t2.yhmc,t2.yhkh,t2.sfzh,t1.xn,");
		sql.append("t3.xqmc,t1.sqsj,t4.dcmc,t1.sqly,");
		sql.append("t2.xydm,t2.zydm,t2.bjdm,t4.dcdm,t1.xq,t1.shzt,");
		sql.append("decode(t1.shzt,'0','δ�ύ','1','ͨ��','2','��ͨ��',");
		sql.append("'3','�˻�','3','���˻�','5','�����',t1.shzt) shztmc ");
		sql.append(" from xg_xszz_new_knssqb t1 ");
		sql.append(" left join view_xsxxb t2 on t1.xh=t2.xh ");
		sql.append(" left join xqdzb t3 on t1.xq=t3.xqdm ");
		sql.append(" left join xg_xszz_new_kndcdmb t4 on t1.rddc=t4.dcdm ");
		sql.append(" ) t1 where 1=1");*/
		
		//������ͼ��ʽ
		//��ͼ��VIEW_NEW_DC_XSZZ_KNSRDSQ
		if(("10335").equals(Base.xxdm)){
			sql.append(" select * from view_xszz_new_knsrdsq t1 where 1=1 ");
		} else if(("11998").equals(Base.xxdm)){
			sql.append(" select * from VIEW_NEW_DC_XSZZ_KNSRDSQ_XZGC t1 where 1=1 ");
		} else if("10346".equals(Base.xxdm)){
			sql.append(" select * from view_new_dc_xszz_knsrdsq_10346 t1 where 1=1 ");
		}else {
			sql.append(" select t1.*,t3.sydm,t3.symc from VIEW_NEW_DC_XSZZ_KNSRDSQ t1 ");
			sql.append(" left join XG_XTWH_SYBJGLB t2 on t2.bjdm=t1.bjdm ");
			sql.append(" left join XG_XTWH_SYDMB t3 on t3.sydm = t2.sydm ");
			sql.append(" where 1=1 ");
		}
		sql.append(searchTjByUser);
		sql.append(searchTj);
		sql.append(" order by sqsj desc");
		return getPageList(t, sql.toString(), inputV);
	}

	protected void setTableInfo() {
		super.setKey("guid");
		super.setTableName("xg_xszz_new_knssqb");
		super.setClass(KnsrdForm.class);
	}

	
	
	public KnsrdForm getModel(KnsrdForm t ,String[] params) throws Exception {
		
		String sql = "select * from xg_xszz_new_knssqb where xh=? and xn=? and xq=? ";
		
		return super.getModel(t, sql, params);
	}
	public KnsrdForm getModelOfXn(KnsrdForm t ,String[] params) throws Exception {
		
		String sql = "select * from xg_xszz_new_knssqb where xh=? and xn=?";
		
		return super.getModel(t, sql, params);
	}
	@Override
	public KnsrdForm getModel(KnsrdForm t) throws Exception {
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select t1.*,t2.dcmc sqdcmc,t3.xqmc,t4.yymc");
		//����ʦ����ѧ���Ի�(���Ӽ�ͥ�����������ƺ͸ߵ�����Ʒ��������)
		if(Base.xxdm.equals("10346")){
			sql.append(",t5.mc as jtknlxmc,t6.mc as gdxfplxmc ");
		}
		sql.append(" from xg_xszz_new_knssqb t1");
		sql.append(" left join xg_xszz_new_kndcdmb t2 on t1.ylzd1 = t2.dcdm ");
		sql.append(" left join xqdzb t3 on t1.xq = t3.xqdm left join xg_xszz_new_knyydmb t4 on t1.ylzd5=t4.yydm");
		if(Base.xxdm.equals("10346")){
			sql.append(" left join xg_knsrd_jtknlx t5 on t1.ylzd5 = t5.dm");
			sql.append(" left join xg_knsrd_gdxfplx t6 on t1.ylzd9 = t6.dm");
		}
		sql.append(" where t1.guid = ?");
		
//		String sql="select a.*,(select dcmc from xg_xszz_new_kndcdmb t where a.ylzd1=to_char(t.dcdm))sqdcmc from xg_xszz_new_knssqb a where guid=?";
//		HashMap<String, String> map=dao.getMapNotOut(sql, new String[]{t.getGuid()});
//		KnsrdForm model=new KnsrdForm();
//		BeanUtils.copyProperties(model, map);
		return super.getModel(sql.toString(), new String[]{t.getGuid()});
	}

	
	
	/**
	 * 
	 * @����:�������״̬
	 * @���ߣ�Penghui.Qu
	 * @���ڣ�2013-4-22 ����09:59:26
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param splc
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean insertShzt(KnsrdForm t ,String splc) throws Exception{
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("insert into xg_xszz_new_knsshb(xh,xn,xq,xtgwid,shzt,guid) ");
		sql.append("select ?,?,?,spgw,?,? from xg_xtwh_spbz where splc=? ");
		
		return dao.runUpdate(sql.toString(), new String[]{t.getXh(),t.getXn(),t.getXq(),t.getShzt(),t.getGuid(),splc});
	}
		
	
	/**
	 * 
	 * @����:ɾ�����������루ֻ��ɾ��δ��˵ļ�¼��
	 * @���ߣ�Penghui.Qu
	 * @���ڣ�2013-4-24 ����06:41:37
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param values
	 * @return
	 * @throws Exception
	 * int �������� 
	 * @throws
	 */
	public int delKnssq(String[] values) throws Exception {
		
		if (values == null || values.length == 0){
			logger.error("ɾ���������ܽ���!");
			throw new NullPointerException();
		}
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("delete from xg_xszz_new_knssqb");
		sql.append(" where (shzt='0' or shzt='3') and ");
		sql.append("(");
		
		for (int i = 0 , n = values.length ; i < n ; i++){
			sql.append("guid=?");
			
			if (i != n-1){
				sql.append(" or ");
			}
		}
		
		sql.append(")");
		int delNum = dao.runDelete(sql.toString(), values);
		if(0!=delNum){
			//ɾ����˼�¼
			SqshDao sqshDao = new SqshDao();
			sqshDao.delShzt(values);
		}
		return delNum;
	}
	
	
	
	/**
	 * 
	 * @����:ɾ�����״̬����Ӧ�������¼ֻ����δ��˵ļ�¼��
	 * @���ߣ�Penghui.Qu
	 * @���ڣ�2013-4-22 ����01:12:50
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param values
	 * @return
	 * @throws Exception
	 * int �������� 
	 * @throws
	 */
	public int delShzt(String[] values) throws Exception {
		
		if (values == null || values.length == 0){
			logger.error("ɾ���������ܽ���!");
			throw new NullPointerException();
		}
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("delete from xg_xszz_new_knsshb t1");
		sql.append(" where exists (select 1 from xg_xszz_new_knssqb t2 ");
		sql.append(" where t1.guid=t2.guid and t2.shzt='0') and (");
		
		for (int i = 0 , n = values.length ; i < n ; i++){
			sql.append("guid=?");
			
			if (i != n-1){
				sql.append(" or ");
			}
		}
		
		sql.append(")");
		return dao.runDelete(sql.toString(), values);
	}
	
	
	
	/**
	 * 
	 * @����:��ȡ�����¼����Ӧ�����������Ϣ�����̸��٣�
	 * @���ߣ�Penghui.Qu
	 * @���ڣ�2013-4-22 ����01:50:37
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>>  getSplcInfo(KnsrdForm t){
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select t3.xh spxh,t1.xtgwid,t4.mc gwmc,t1.rddc,t6.dcmc,t5.yhm,t5.xm shr,");
		sql.append("decode(t1.shzt,'0','δ���','1','ͨ��','2',");
		sql.append("'��ͨ��','3','�˻�','4','������',t1.shzt) shzt,");
		sql.append("t1.shsj,t1.shyj,t1.xn from xg_xszz_new_knsshb t1 ");
		sql.append("left join xg_xszz_new_knssqb t2 on t1.guid = t2.guid ");
		sql.append("left join xg_xtwh_spbz t3 on t2.shlc=t3.splc and t1.xtgwid=t3.spgw ");
		sql.append("left join xg_xtwh_spgw t4 on t3.spgw=t4.id ");
		sql.append("left join yhb t5 on t1.shr=t5.yhm  ");
		sql.append("left join xg_xszz_new_kndcdmb t6 on t1.rddc=t6.dcdm ");
		sql.append("where t1.guid=? order by spxh");
		
		return dao.getListNotOut(sql.toString(), new String[]{t.getGuid()});
	}
	
	
	
	/**
	 * 
	 * @����:�������϶�--��˲�ѯ
	 * @���ߣ�Penghui.Qu
	 * @���ڣ�2013-4-22 ����04:18:10
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getShjlList(KnsrdForm t, User user)
			throws Exception {

		// ���ɸ߼���ѯ�������������ֵ
		if("xn".equals(MessageUtil.getText("xszz.knsrd.sqzq"))){
			t.getSearchModel().setSearch_tj_xq(null);
		}
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t1", "xydm", "bjdm");
		String shgwzByUser = SearchService.getShgwzByUser(user, "t1","xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(t.getSearchModel());

		StringBuilder sql = new StringBuilder();

		sql.append(" select * from (  select t2.zzmmmc,t1.mzmc,x.yhkh,t3.yhmc,x.sfzh,e.jtdz,e.jtrjsr,x.csrq,d.xqmc,'[' || c.mc || ':' ||decode(b.shzt,'0','�����','1','ͨ��','2','��ͨ��','3','�˻�','4','������','5','�����',b.shzt) || ']' shztmc,");
		//�й�����ѧԺ���Ի�
		if("10355".equals(Base.xxdm)){
			sql.append(" (select c.qxmc from dmk_qx c where c.qxdm = substr(x.jg, 0, 2) || '0000') || (select d.qxmc from dmk_qx d where d.qxdm = substr(x.jg, 0, 4) || '00' and x.jg <> substr(x.jg, 0, 2) || '0000') || (select e.qxmc from dmk_qx e where e.qxdm = x.jg and x.jg <> substr(x.jg, 0, 2) || '0000' and x.jg <> substr(x.jg, 0, 4) || '00') jgmc,c.gwz,a.xh,nvl(f.dcmc,' ') sqdcmc,a.ylzd2,a.ylzd9,a.xn,a.xq,a.sqsj,a.guid,a.jtrjnsr,b.zd6 dczzje,a.shlc,a.sqly,m.nj,x.mz,x.zd5, ");
		}else{
			sql.append(" (select c.qxmc from dmk_qx c where c.qxdm = substr(x.jg, 0, 2) || '0000') || (select d.qxmc from dmk_qx d where d.qxdm = substr(x.jg, 0, 4) || '00' and x.jg <> substr(x.jg, 0, 2) || '0000') || (select e.qxmc from dmk_qx e where e.qxdm = x.jg and x.jg <> substr(x.jg, 0, 2) || '0000' and x.jg <> substr(x.jg, 0, 4) || '00') jgmc,c.gwz,a.xh,nvl(f.dcmc,' ') sqdcmc,a.ylzd2,a.ylzd9,a.xn,a.xq,a.sqsj,a.guid,b.zd6 dczzje,a.shlc,a.sqly,m.nj,x.mz,x.zd5, ");
		}
		if("10742".equals(Base.xxdm)) {
			sql.append(" a.sqlydm, ");
		}
		if("10026".equals(Base.xxdm)) {
			sql.append(" a.ylzd5, ");
		}
		sql.append(" decode(x.xb,'1','��','2','Ů',x.xb) as xb, x.xm,m.xydm,m.zydm,m.bjdm,m1.bjdm zybj,b.zd3 as dcmc,");
		sql.append(" m.xymc,m.zymc,m.bjmc,m1.bjmc zybjmc,b.shzt,b.shsj,b.gwid as xtgwid,b.zd2 as rddc,b.guid as shid,");
		sql.append(" lead(b.zd6, 1, null) over(partition by b.ywid order by b.ywid,b.shsj desc) as wczzje,");
		sql.append(" lead(b.zd3, 1, null) over(partition by b.ywid order by b.ywid,b.shsj desc) as sjdcmc, ");
		sql.append(" lead(b.zd2, 1, null) over(partition by b.ywid order by b.ywid,b.shsj desc) as sjdc,");
		sql.append(" lead(b.zd9, 1, a.ylzd9) over(partition by b.ywid order by b.ywid,b.shsj desc) as sqxz,");
		sql.append(" row_number() over(partition by a.guid order by b.shsj desc) rn ");
		if("10657".equals(Base.xxdm)){
			sql.append(",e.jtrjysr");
		}
		if("11998".equals(Base.xxdm)){
			sql.append(",t5.zf");
		}
		//�����ʵ��ѧ���Ի�
		if("13627".equals(Base.xxdm)){
			sql.append(",fdy.xm fdyxm ");
		}
		sql.append(" from xg_xszz_new_knssqb a left join xsxxb x on a.xh=x.xh left join view_njxyzybj_all m on x.bjdm = m.bjdm ");
		sql.append(" left join view_njxyzybj_all m1 on x.zybj = m.bjdm ");
		sql.append(" left join xg_xtwh_shztb b on a.guid = b.ywid ");
		sql.append(" left join xg_xtwh_spgw c on b.gwid = c.id ");
		sql.append(" left join xqdzb d on a.xq = d.xqdm  ");
		sql.append(" left join xg_xszz_new_jtqkdcb e on a.xh=e.xh");
		sql.append(" left join xg_xszz_new_kndcdmb f on a.ylzd1=f.dcdm left join mzdmb t1 on x.mz = t1.mzdm left join zzmmdmb t2 on t2.zzmmdm = x.zzmm left join dmk_yh t3 on t3.yhdm = x.yhdm");
		if("11998".equals(Base.xxdm)){
			sql.append("  left join xg_xszz_knsdcjgwhb t5  on substr(a.xn,0,4)  = t5.nd and a.xh  = t5.xh ");
		}
		//�����ʵ��ѧ���Ի�
		if("13627".equals(Base.xxdm)){
			sql.append(" left join view_xg_fdyxx fdy on x.bjdm = fdy.bjdm ");
		}
		if (YSH.equalsIgnoreCase(t.getShzt())){
			sql.append(" where exists ( select 1 from xg_xtwh_spgwyh a where spyh = '"+user.getUserName()+"' and b.gwid = a.spgw ) ");
			sql.append("  and (b.shzt <> '0' and b.shzt <>'4')");
			sql.append(" ) t1 where 1=1 and rn=1");
		}
		
		if (DSH.equalsIgnoreCase(t.getShzt())){
			sql.append(" ) t1 where 1=1 and rn=1");
			sql.append(" and exists ( select 1 from xg_xtwh_spgwyh a where a.spyh = '"+ user.getUserName() +"' and t1.xtgwid = a.spgw ) ");
			sql.append(" and (shzt = '0' or shzt = '4') ");
		}
		
		sql.append(searchTjByUser);
		sql.append(searchTj);
		sql.append(shgwzByUser);
		
//		if (DSH.equalsIgnoreCase(t.getShzt())){
//			sql.append("and (t1.spxh='1' or (t1.spxh <> '1' and t1.sjzt='1')) and (t1.shzt='0' or t1.shzt='4') ");
//		} else {
//			sql.append("and (t1.shzt='1' or t1.shzt='2' or t1.shzt='3') ");
//			sql.append("and t1.shr='");
//			sql.append(user.getUserName());
//			sql.append("' ");
//		}
//		
//		sql.append("and exists ( select 1 from (select * from xg_xtwh_spgwyh where spyh=?) t4 where t1.xtgwid=t4.spgw");
//		sql.append(") ");
		sql.append(" order by sqsj desc");
		return getPageList(t, sql.toString(), StringUtils.joinStrArr(inputV,new String[]{}));
	}
	
	
	
	/**
	 * 
	 * @����:ִ����˲���������������ǰһ�� Ϊ��ͨ����������Ϊ��δ��ˡ� or �������󡱣�
	 * @���ߣ�Penghui.Qu
	 * @���ڣ�2013-4-23 ����03:26:16
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return
	 * @throws Exception
	 * int �������� 
	 * @throws
	 */
	public int runAuditing(KnsrdForm t,User user) throws Exception{
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("update xg_xszz_new_knsshb t1 set shzt=?,shyj=?,shr=?,rddc=?,");
		sql.append("shsj=to_char(sysdate,'yyyy-mm-dd hh24:mi:ss') where guid=? ");
		sql.append("and xtgwid=? and (shzt='0' or shzt='4') and exists (");
		sql.append("select 1 from ( ");
		sql.append("select t1.*,t2.shzt bjzt,nvl(t3.shzt,'1') sjzt from (");
		sql.append("select t1.xh,t1.spgw,t1.splc,");
		sql.append("lag(t1.xh,1,0) over (order by t1.xh) as sjxh,");
		sql.append("lag(t1.spgw,1) over (order by t1.xh) as sjgw ");
		sql.append("from xg_xtwh_spbz t1 where splc=(");
		sql.append("select shlc from xg_xszz_new_knssqb where guid=? ");
		sql.append(")) t1 left join xg_xszz_new_knsshb t2 on t1.spgw=t2.xtgwid and t2.guid=? ");
		sql.append("left join xg_xszz_new_knsshb t3 on t1.sjgw=t3.xtgwid and t3.guid=? ");
		sql.append(") t2 where t1.xtgwid=t2.spgw and t2.sjzt='1' and (bjzt='0' or bjzt='4') ");
		sql.append(")");
		
		
		String guid = t.getGuid();
		
		return dao.update(sql.toString(), new String[]{t.getShzt(),t.getShyj(),user.getUserName(),
													   t.getRddc(),guid,t.getXtgwid(),guid,guid,guid});
	}
	
	
	
	/**
	 * 
	 * @����:������ˣ�����������
	 * 			�� ����������ͨ�� �� ��һ����δ��ˡ� or ��������;
	 * 			�� ���������Ĳ�ͨ��;
	 * 			�� �����������˻�,ǰһ��"������"
	 * ������ �� �� �� �� ���߹�ϵ
	 * @���ߣ�Penghui.Qu
	 * @���ڣ�2013-4-23 ����03:41:38
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return
	 * @throws Exception
	 * int �������� 
	 * @throws
	 */
	public int cancelAuditing(KnsrdForm t) throws Exception{
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("update xg_xszz_new_knsshb t1 set shzt='0',shsj='',shr='',shyj='',rddc='' ");
		sql.append("where guid=? and xtgwid=? and exists (select 1 from (");
		sql.append("select t1.xh spxh,t1.spgw,t4.shzt shzt,t1.xjxh,");
		sql.append("t1.xjgw,t2.shzt xjzt,t1.sjxh,t1.sjgw,t3.shzt sjzt from (");
		sql.append("select t1.xh,t1.spgw,");
		sql.append("lag(t1.xh,1,0) over (order by t1.xh) as sjxh,");
		sql.append("lag(t1.spgw,1) over (order by t1.xh) as sjgw,");
		sql.append("lead(t1.xh,1) over (order by t1.xh) as xjxh,");
		sql.append("lead(t1.spgw,1) over (order by t1.xh) as xjgw ");
		sql.append("from xg_xtwh_spbz t1 where splc=( ");
		sql.append("select shlc from xg_xszz_new_knssqb where guid=? ");
		sql.append(")) t1 left join xg_xszz_new_knsshb t2 on t1.xjgw=t2.xtgwid and t2.guid=? ");
		sql.append("left join xg_xszz_new_knsshb t3 on t1.sjgw=t3.xtgwid and t3.guid=? ");
		sql.append("left join xg_xszz_new_knsshb t4 on t1.spgw=t4.xtgwid and t4.guid=? ");
		sql.append(") t2 where t1.xtgwid=t2.spgw and (t2.shzt='2' or (t2.shzt='3' and (sjzt='4' or sjzt is null)) ");
		sql.append("or (t2.shzt='1' and (xjzt='0' or xjzt='4' or xjzt is null)))) ");
		
		String guid = t.getGuid();
		
		return dao.update(sql.toString(), new String[]{guid,t.getXtgwid(),guid,guid,guid,guid});
	}
	
	
	
	/**
	 * 
	 * @����:��ȡ��һ��������Ϣ
	 * @���ߣ�Penghui.Qu
	 * @���ڣ�2013-4-23 ����04:24:02
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return
	 * String �������� 
	 * @throws
	 */
	public HashMap<String,String> getNextSpxx(KnsrdForm t){
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select * from ( ");
		sql.append("select t1.xh,t1.spgw,");
		sql.append("lead(t1.xh,1) over (order by t1.xh) as xjxh,");
		sql.append("lead(t1.spgw,1) over (order by t1.xh) as xjgw ");
		sql.append("from xg_xtwh_spbz t1 ");
		sql.append("where splc=(");
		sql.append("select shlc from xg_xszz_new_knssqb where guid=? ");
		sql.append(") ) where spgw=? ");
		
		return dao.getMapNotOut(sql.toString(), new String[]{t.getGuid(),t.getXtgwid()});
	}
	
	
	
	/**
	 * 
	 * @����:��ȡǰһ��������Ϣ
	 * @���ߣ�Penghui.Qu
	 * @���ڣ�2013-4-23 ����04:24:02
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return
	 * String �������� 
	 * @throws
	 */
	public HashMap<String,String> getBeforeSpxx(KnsrdForm t){
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select * from ( ");
		sql.append("select t1.xh,t1.spgw,");
		sql.append("lag(t1.xh,1) over (order by t1.xh) as sjxh,");
		sql.append("lag(t1.spgw,1) over (order by t1.xh) as sjgw ");
		sql.append("from xg_xtwh_spbz t1 ");
		sql.append("where splc=(");
		sql.append("select shlc from xg_xszz_new_knssqb where guid=? ");
		sql.append(") ) where spgw=? ");
		
		return dao.getMapNotOut(sql.toString(), new String[]{t.getGuid(),t.getXtgwid()});
	}
	
	
	
	/**
	 * 
	 * @����:��������ID����˸�λID��ȡ��ǰ��λ�����״̬
	 * @���ߣ�Penghui.Qu
	 * @���ڣ�2013-4-23 ����05:00:55
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getCurrunShzt(KnsrdForm t){
		
		String sql = "select shzt from xg_xszz_new_knsshb where guid=? and xtgwid=?";
		
		return dao.getOneRs(sql, new String[]{t.getGuid(),t.getXtgwid()}, "shzt");
	}
	
	
	
	/**
	 * 
	 * @����:�������¼ID��������λID�������״̬
	 * @���ߣ�Penghui.Qu
	 * @���ڣ�2013-4-24 ����08:43:56
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param guid
	 * @param gwid
	 * @param shzt
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean setShzt(String guid,String gwid,String shzt) throws Exception{
		
		String sql = "update xg_xszz_new_knsshb set shzt=? where guid=? and xtgwid=? ";
		
		return dao.runUpdate(sql, new String[]{shzt,guid,gwid});
	}
	
	
	
	/**
	 * 
	 * @����:����˵ļ�¼����
	 * @���ߣ�Penghui.Qu
	 * @���ڣ�2013-4-24 ����04:02:35
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * @throws SQLException
	 * int �������� 
	 * @throws
	 */
	public int getDshCount() throws SQLException{
		
		String sql = "select count(1) num from xg_xszz_knsrd_zbsqb where shzt='5' ";
		
		return dao.getOneRsint(sql);
	}
	
	
	
	/**
	 * 
	 * @����:���������������ɾ��������������Ϣ
	 * @���ߣ�Penghui.Qu
	 * @���ڣ�2013-5-6 ����01:42:18
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param values
	 * @return
	 * int �������� 
	 * @throws
	 */
	public int delKnssqFromKnsjg(String[] values) throws Exception{
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("delete from xg_xszz_new_knssqb t1 where  ");
		sql.append(" (");

		for (int i = 0 , n = values.length ; i < n ; i++){
			sql.append("guid=?");
			
			if (i != n -1){
				sql.append(" or ");
			}
		}
		
		sql.append(")");
		
		return dao.runDelete(sql.toString(), values);
	}
	
	
	
	/**
	 * 
	 * @����:���������������ɾ�������������Ӧ����˼�¼
	 * @���ߣ�Penghui.Qu
	 * @���ڣ�2013-5-6 ����01:47:46
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param values
	 * @return
	 * @throws Exception
	 * int �������� 
	 * @throws
	 */
	public int delKnsshFromKnsjg(String[] values) throws Exception{
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("delete from xg_xszz_new_knsshb t1 where ");
		sql.append(" (");

		for (int i = 0 , n = values.length ; i < n ; i++){
			sql.append("guid=?");
			
			if (i != n -1){
				sql.append(" or ");
			}
		}
		
		sql.append(")");
		
		return dao.runDelete(sql.toString(), values);
	}
	
	
	
	/**
	 * 
	 * @����:��ѯ��������в����ڵ�ID
	 * @���ߣ�Penghui.Qu
	 * @���ڣ�2013-5-8 ����07:33:53
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ids
	 * @return
	 * @throws SQLException
	 * String[] �������� 
	 * @throws
	 */
	public String[] getExistsId(String[] ids) throws SQLException{
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select guid from xg_xszz_new_knssqb where (");
		
		for (int i = 0 , n = ids.length ; i < n ; i++){
			sql.append("guid=?");
			if (i != n-1){
				sql.append(" or ");
			}
		}
		
		sql.append(")");
		
		return dao.getArray(sql.toString(), ids, "guid");
	}





	/**
	 * 
	 * @����: ������ͳ��
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-6-3 ����01:59:26
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param user
	 * @param xn
	 * @param xq
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getShqkTjxx(User user,String xn,String xq){
		
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t2", "xydm", "bjdm");		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select t1.xtgwid,t3.mc gwmc,t1.btg,t1.tg,t2.spxh,");
		sql.append("t1.dsh - (lag(t1.dsh,1,0) over (order by t2.spxh) + lag(t1.btg,1,0) over (order by t2.spxh)) dsh ");
		sql.append("from (select sum(t1.dsh) dsh, sum(t1.tg) tg,");
		sql.append("sum(t1.btg) btg, xtgwid  from (select t1.xh,t1.xn,t1.xq,");
		sql.append("case when t1.shzt = '0' or t1.shzt = '4' then  1  else 0 end dsh,");
		sql.append("case when t1.shzt = '1' then  1  else  0 end tg,");
		sql.append("case when t1.shzt = '2' then  1  else  0 end btg,");
		sql.append("t1.xtgwid from xg_xszz_new_knsshb t1 where t1.xn = ? and t1.xq = ? ");
		sql.append("and exists (select 1 from xsxxb t2 where t1.xh=t2.xh ");
		sql.append(searchTjByUser);
		sql.append(")) t1 group by t1.xtgwid ) t1 left join (select spgw,xh as spxh from xg_xtwh_spbz ");
		sql.append("where splc=(select splc from xg_xszz_new_knsjbszb where rownum=1) ) t2 on t1.xtgwid=t2.spgw ");
		sql.append("left join xg_xtwh_spgw t3 on t1.xtgwid=t3.id  order by t2.spxh");
		
		return dao.getListNotOut(sql.toString(), new String[]{xn,xq});
	}





	/**
	 * 
	 * @����: ����������������
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-6-3 ����02:29:37
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param user
	 * @param xn
	 * @param xq
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getSqrs(User user,String xn,String xq){
		
		StringBuilder sql = new StringBuilder();
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t2", "xydm", "bjdm");		
		
		sql.append("select count(1) zrs from xg_xszz_new_knssqb t1 where t1.xn=? and t1.xq=? ");
		sql.append("and exists (select 1 from xsxxb t2 where t1.xh=t2.xh ");
		sql.append(searchTjByUser);
		sql.append(")");
		
		return dao.getOneRs(sql.toString(), new String[]{xn,xq}, "zrs");
	}


	/**
	 * 
	 * @����: �����ͳ�Ʋ�ѯ����ѧ��
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-6-3 ����04:23:03
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param user
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getStudentsFromShtj(KnsrdForm t, User user) throws Exception {

		//���ɸ߼���ѯ�������������ֵ 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t1", "xydm", "bjdm");		
		String[] input = SearchService.getTjInput(t.getSearchModel());
		
		StringBuilder sql = new StringBuilder();
		sql.append("select t1.* from (");
		sql.append("select t1.xh,t2.xm,t2.nj,t2.xymc,t2.xydm,t2.xb,");
		sql.append("t2.zymc,t2.zydm,t2.bjmc,t2.bjdm,");
		sql.append("nvl(t3.shsj,' ') shsj,nvl(t3.shyj,' ') shyj,nvl(t3.shr,' ') shr from xg_xszz_new_knssqb t1 ");
		sql.append("left join view_xsjbxx t2 on t1.xh=t2.xh ");
		sql.append("left join (select * from xg_xszz_new_knsshb where xtgwid=?) t3 on t1.guid = t3.guid ");
		sql.append("where t1.xn=? and t1.xq=? and t1.guid in (select guid from (");
		
		sql.append("select t1.shzt,t1.xtgwid,t1.guid,t2.xh as spxh,");
		sql.append("case when t2.xh=1 then '1' else lag(t1.shzt, 1,1) over(order by t1.guid, t2.xh) end sjzt ");
		sql.append("from xg_xszz_new_knsshb t1 ");
		sql.append("left join xg_xtwh_spbz t2 on t1.xtgwid=t2.spgw ");
		sql.append("where t2.splc=(select splc from xg_xszz_new_knsjbszb where rownum=1) ");
		
		sql.append(") t2 where (t2.shzt=?  ");
		if (Constants.YW_WTJ.equals(t.getShzt())){
			sql.append("or t2.shzt='4'");
		}
		sql.append(")");
		sql.append("and t2.xtgwid=? and t2.sjzt='1' ) ) t1 where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		
		return getPageList(t, sql.toString(), StringUtils.joinStrArr(new String[]{t.getXtgwid(),t.getXn(),t.getXq(),t.getShzt(),t.getXtgwid()},input));
	}
	
	
	/** 
	 * @����:�����������䷽ʽ��ͳ�ƶ�Ӧ��λ������
	 * @���ߣ�������
	 * @���ڣ�2013-12-10 ����02:20:36
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getTgrs(JcszForm model) {
		StringBuilder sql = new StringBuilder();
		String rskzfw=model.getRskzfw();
		String rddcsql="";
		if(new JcszDao().getKnsrsszfs().equals("1")){
			rddcsql=" and t1.rddc = '"+model.getRddc()+"'";
		}
		if (Constants.RSKZFW_NJXY.equals(rskzfw)){//�꼶ѧԺ���䷽ʽ
			sql.append("select count(1) tgrs,nj,xydm from (");
			sql.append("select t1.xh,t1.shzt,t2.nj,t2.xydm,t2.zydm,t2.bjdm from xg_xszz_new_knssqb t1 ");
			sql.append("left join xsxxb t2 on t1.xh=t2.xh ");
			sql.append(" where t1.xn=? and t1.xq=? and shzt='1' ");
			sql.append(rddcsql);
			sql.append(") group by nj,xydm ");
		} else if (Constants.RSKZFW_NJZY.equals(rskzfw)){//�꼶רҵ���䷽ʽ
			sql.append("select count(1) tgrs,nj,zydm from (");
			sql.append("select t1.xh,t1.shzt,t2.nj,t2.xydm,t2.zydm,t2.bjdm from xg_xszz_new_knssqb t1 ");
			sql.append("left join xsxxb t2 on t1.xh=t2.xh ");
			sql.append(" where t1.xn=? and t1.xq=? and shzt='1' ");
			sql.append(rddcsql);
			sql.append(") group by nj,zydm ");
		} else if (Constants.RSKZFW_XY.equals(rskzfw)){//ѧԺ���䷽ʽ
			sql.append("select count(1) tgrs,xydm from (");
			sql.append("select t1.xh,t1.shzt,t2.nj,t2.xydm,t2.zydm,t2.bjdm from xg_xszz_new_knssqb t1 ");
			sql.append("left join xsxxb t2 on t1.xh=t2.xh ");
			sql.append(" where t1.xn=? and t1.xq=? and shzt='1' ");
			sql.append(rddcsql);
			sql.append(") group by xydm ");
		} else if (Constants.RSKZFW_ZY.equals(rskzfw)){//רҵ���䷽ʽ
			sql.append("select count(1) tgrs,zydm from (");
			sql.append("select t1.xh,t1.shzt,t2.nj,t2.xydm,t2.zydm,t2.bjdm from xg_xszz_new_knssqb t1 ");
			sql.append("left join xsxxb t2 on t1.xh=t2.xh ");
			sql.append(" where t1.xn=? and t1.xq=? and shzt='1' ");
			sql.append(rddcsql);
			sql.append(") group by zydm ");
		} else {//�༶���䷽ʽ
			sql.append("select count(1) tgrs,bjdm from (");
			sql.append("select t1.xh,t1.shzt,t2.nj,t2.xydm,t2.zydm,t2.bjdm from xg_xszz_new_knssqb t1 ");
			sql.append("left join xsxxb t2 on t1.xh=t2.xh ");
			sql.append(" where t1.xn=? and t1.xq=? and shzt='1' ");
			sql.append(rddcsql);
			sql.append(") group by bjdm ");
		}
		return dao.getListNotOut(sql.toString(), new String[]{model.getXn(),model.getXq()});
	}
	
	
	/**
	 * 
	 * @����:�ύ������������������Ʒ�Χ����
	 * @���ߣ�taogj[���ţ�1075]
	 * @���ڣ�2017-4-7 ����01:34:01
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param sqids
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getXzrs(String sqids){
		StringBuilder sql = new StringBuilder();
		sql.append(" select c.rskzfw,g.xh kzjb, ");
		sql.append(" decode(c.rskzfw,'bj',d.bjdm,'njxy',d.nj||d.xydm,'njzy',d.nj||d.zydm,'xy',d.xydm,'zy',d.zydm) as kzbm, ");
		sql.append(" i.xh dqjb,count(*) xzrs from xg_xszz_new_knssqb a ");
		sql.append(" left join ( select * from ( ");
		sql.append(" select c.*,row_number() over(partition by c.ywid order by c.shsj desc) as rn  from xg_xtwh_shztb c "); 
		sql.append(" where c.shzt in ('0','4') ) where rn =1 ) b on a.guid=b.ywid ");
		sql.append(" left join xg_xszz_new_knsjbszb c on 1=1 ");
		sql.append(" left join view_xsjbxx d on a.xh=d.xh ");
		sql.append(" left join xg_xtwh_splc f on c.splc=f.id ");
		sql.append(" left join xg_xtwh_spbz g on f.id=g.splc and c.rskzjb=g.spgw ");
		sql.append(" left join xg_xtwh_splc h on c.splc=h.id ");
		sql.append(" left join xg_xtwh_spbz i on h.id=i.splc and b.gwid=i.spgw ");
		sql.append(" where a.guid in ("+sqids+") and a.xn = c.rdxn and a.xq = nvl(c.rdxq,'on') ");
		sql.append(" group by c.rskzfw,g.xh,"); 
		sql.append(" decode(c.rskzfw,'bj',d.bjdm,'njxy',d.nj||d.xydm,'njzy',d.nj||d.zydm,'xy',d.xydm,'zy',d.zydm),i.xh ");
		return dao.getListNotOut(sql.toString(), new String[]{});
	}
	
	
	/**
	 * 
	 * @����:ͨ�����Ƽ����������Ʒ�Χ�õ��������¿��Ƶ������Լ����Ƽ���֮�󣨺���ͨ��������
	 * @���ߣ�taogj[���ţ�1075]
	 * @���ڣ�2017-4-7 ����01:52:12
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param kzjb
	 * @param kzbm
	 * @param kzfs
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String> getKzrsTgrs(String kzjb,String kzbm,String kzfs,String rddc) {
		StringBuilder sb = new StringBuilder();
		sb.append(" select a.zzrs zzme,nvl(b.ytggs,0) ytggs from ( ");
		sb.append(" select a.* from xg_xszz_new_knsrsszb a left join xg_xszz_new_knsjbszb b on a.xn=b.rdxn and a.xq=nvl(b.rdxq,'on') ");
		sb.append(" where zzrs is not null ");
		if(new JcszDao().getKnsrsszfs().equals("1")){
			sb.append(" and rddc='"+rddc+"' ");
		}
		
		if (Constants.RSKZFW_NJXY.equals(kzfs)){ 		//�꼶ѧԺ
			sb.append(" and a.nj||a.bmdm=? ) a ");
		} else if (Constants.RSKZFW_NJZY.equals(kzfs)){	//�꼶רҵ
			sb.append(" and a.nj||a.bmdm=? ) a ");
		} else if (Constants.RSKZFW_XY.equals(kzfs)){	//ѧԺ
			sb.append(" and a.bmdm=? ) a ");
		} else if (Constants.RSKZFW_ZY.equals(kzfs)){	//רҵ
			sb.append(" and a.bmdm=? ) a ");
		} else if (Constants.RSKZFW_BJ.equals(kzfs)) {	//�༶
			sb.append(" and a.bmdm=? ) a ");
		}
		
		sb.append(" left join ( select count(1) ytggs from ( ");
		sb.append(" select t3.xh,t2.shzt,row_number()over(partition by t1.guid order by t2.shsj desc) rn ");
		sb.append(" from xg_xszz_new_knssqb t1 ");
		sb.append(" left join xg_xtwh_shztb t2 on t1.guid=t2.ywid ");
		sb.append(" left join xg_xtwh_spbz t3 on t2.gwid=t3.spgw and t2.lcid=t3.splc ");
		sb.append(" left join view_xsbfxx t4 on t1.xh=t4.xh ");
		sb.append(" left join xg_xszz_new_knsjbszb t5 on 1=1 ");
		sb.append(" where t1.xn=t5.rdxn and t1.xq=nvl(t5.rdxq,'on') and t1.shzt in (?,?) ");
		if (Constants.RSKZFW_NJXY.equals(kzfs)){ 		//�꼶ѧԺ
			sb.append(" and t4.nj||t4.xydm=? ");
		} else if (Constants.RSKZFW_NJZY.equals(kzfs)){	//�꼶רҵ
			sb.append(" and t4.nj||t4.zydm=? ");
		} else if (Constants.RSKZFW_XY.equals(kzfs)){	//ѧԺ
			sb.append(" and t4.xydm=?  ");
		} else if (Constants.RSKZFW_ZY.equals(kzfs)) {	//רҵ
			sb.append(" and t4.zydm=? ");
		} else if (Constants.RSKZFW_BJ.equals(kzfs)) {	//�༶
			sb.append(" and t4.bjdm=?  ");
		}
		
		sb.append(" ) where rn =1 and ((shzt <> ? and to_number(xh) = to_number(?)) or to_number(xh) > to_number(?)) ) b on 1=1 ");
		String[] inputValue = { kzbm,Constants.YW_TG,Constants.YW_SHZ,kzbm,Constants.SH_DSH,kzjb,kzjb};
		return dao.getMapNotOut(sb.toString(), inputValue);
	}
	
	
	/** 
	 * @����:���꼶��ѧԺͳ����ͨ������
	 * @���ߣ�cmj 
	 * @���ڣ�2013-12-10 ����05:13:36
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param rsszMap
	 * @return
	 * String �������� 
	 * @throws 
	 */
	public String getTgrsByNjxy(KnsrdForm t, HashMap<String, String> rsszMap) {
		StringBuilder sql = new StringBuilder();
		String rddcsql="";
		if(new JcszDao().getKnsrsszfs().equals("1")){
			rddcsql=" and shrddc = '"+t.getRddc()+"' ";
		}
		sql.append("select count(1) tgrs from (select t1.guid, ");
		sql.append("  t2.shzt,t2.gwid,t1.xn,t1.xq,t3.xydm,t3.nj,t1.shrddc, ");
		sql.append("  row_number() over(partition by t1.guid, t2.gwid order by t2.shsj desc) lvl");
		sql.append("  from Xg_Xszz_New_Knssqb t1 left join xg_xtwh_shztb t2");
		sql.append("  on t1.guid = t2.ywid left join view_xsjbxx t3 on t1.xh=t3.xh )");
		sql.append(" where lvl = 1 and shzt = '1' and xn = ? and xq = ?");
		sql.append(rddcsql);
		sql.append("   and gwid = ? and nj||xydm = (select nj||xydm from view_xsjbxx where xh = ?)");
		
		return dao.getOneRs(sql.toString(), new String[]{
			rsszMap.get("xn"),rsszMap.get("xq"),t.getXtgwid(),t.getXh()}, "tgrs");
	}
	/** 
	 * @����:���꼶��רҵͳ����ͨ������
	 * @���ߣ�������
	 * @���ڣ�2013-12-10 ����05:17:35
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param rsszMap
	 * @return
	 * String �������� 
	 * @throws 
	 */
	public String getTgrsByNjzy(KnsrdForm t, HashMap<String, String> rsszMap) {
		StringBuilder sql = new StringBuilder();
		String rddcsql="";
		if(new JcszDao().getKnsrsszfs().equals("1")){
			rddcsql=" and shrddc = '"+t.getRddc()+"' ";
		}
		sql.append("select count(1) tgrs from (select t1.guid, ");
		sql.append("  t2.shzt,t2.gwid,t1.xn,t1.xq,t3.zydm,t3.nj,t1.shrddc, ");
		sql.append("  row_number() over(partition by t1.guid, t2.gwid order by t2.shsj desc) lvl");
		sql.append("  from Xg_Xszz_New_Knssqb t1 left join xg_xtwh_shztb t2");
		sql.append("  on t1.guid = t2.ywid left join view_xsjbxx t3 on t1.xh=t3.xh )");
		sql.append(" where lvl = 1 and shzt = '1' and xn = ? and xq = ?");
		sql.append(rddcsql);
		sql.append("   and gwid = ? and nj||zydm = (select nj||zydm from view_xsjbxx where xh = ?)");
		
		return dao.getOneRs(sql.toString(), new String[]{
			rsszMap.get("xn"),rsszMap.get("xq"),t.getXtgwid(),t.getXh()}, "tgrs");
	}
	/** 
	 * @����:��ѧԺͳ����ͨ������
	 * @���ߣ�������
	 * @���ڣ�2013-12-10 ����05:18:26
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param rsszMap
	 * @return
	 * String �������� 
	 * @throws 
	 */
	public String getTgrsByXy(KnsrdForm t, HashMap<String, String> rsszMap) {
		StringBuilder sql = new StringBuilder();
		String rddcsql="";
		if(new JcszDao().getKnsrsszfs().equals("1")){
			rddcsql=" and shrddc = '"+t.getRddc()+"' ";
		}
		sql.append("select count(1) tgrs from (select t1.guid, ");
		sql.append("  t2.shzt,t2.gwid,t1.xn,t1.xq,t3.xydm,t1.shrddc, ");
		sql.append("  row_number() over(partition by t1.guid, t2.gwid order by t2.shsj desc) lvl");
		sql.append("  from Xg_Xszz_New_Knssqb t1 left join xg_xtwh_shztb t2");
		sql.append("  on t1.guid = t2.ywid left join view_xsjbxx t3 on t1.xh=t3.xh )");
		sql.append(" where lvl = 1 and shzt = '1' and xn = ? and xq = ?");
		sql.append(rddcsql);
		sql.append("   and gwid = ? and xydm = (select xydm from view_xsjbxx where xh = ?)");
		
		return dao.getOneRs(sql.toString(), new String[]{
			rsszMap.get("xn"),rsszMap.get("xq"),t.getXtgwid(),t.getXh()}, "tgrs");
	}
	/** 
	 * @����:��רҵͳ����ͨ������
	 * @���ߣ�������
	 * @���ڣ�2013-12-10 ����05:19:44
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param rsszMap
	 * @return
	 * String �������� 
	 * @throws 
	 */
	public String getTgrsByZy(KnsrdForm t, HashMap<String, String> rsszMap) {
		StringBuilder sql = new StringBuilder();
		String rddcsql="";
		if(new JcszDao().getKnsrsszfs().equals("1")){
			rddcsql=" and shrddc = '"+t.getRddc()+"' ";
		}
		sql.append("select count(1) tgrs from (select t1.guid, ");
		sql.append("  t2.shzt,t2.gwid,t1.xn,t1.xq,t3.zydm,t1.shrddc, ");
		sql.append("  row_number() over(partition by t1.guid, t2.gwid order by t2.shsj desc) lvl");
		sql.append("  from Xg_Xszz_New_Knssqb t1 left join xg_xtwh_shztb t2");
		sql.append("  on t1.guid = t2.ywid left join view_xsjbxx t3 on t1.xh=t3.xh )");
		sql.append(" where lvl = 1 and shzt = '1' and xn = ? and xq = ?");
		sql.append(rddcsql);
		sql.append("   and gwid = ? and zydm = (select zydm from view_xsjbxx where xh = ?)");
		
		return dao.getOneRs(sql.toString(), new String[]{
			rsszMap.get("xn"),rsszMap.get("xq"),t.getXtgwid(),t.getXh()}, "tgrs");
	}
	/** 
	 * @����:���༶ͳ����ͨ������
	 * @���ߣ�������
	 * @���ڣ�2013-12-10 ����05:20:54
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param rsszMap
	 * @return
	 * String �������� 
	 * @throws 	
	 */
	public String getTgrsByBj(KnsrdForm t, HashMap<String, String> rsszMap) {
		StringBuilder sql = new StringBuilder();
		String rddcsql="";
		if(new JcszDao().getKnsrsszfs().equals("1")){
			rddcsql=" and shrddc = '"+t.getRddc()+"' ";
		}
		sql.append("select count(1) tgrs from (select t1.guid, ");
		sql.append("  t2.shzt,t2.gwid,t1.xn,t1.xq,t3.bjdm,t3.nj,t1.shrddc, ");
		sql.append("  row_number() over(partition by t1.guid, t2.gwid order by t2.shsj desc) lvl");
		sql.append("  from Xg_Xszz_New_Knssqb t1 left join xg_xtwh_shztb t2");
		sql.append("  on t1.guid = t2.ywid left join view_xsjbxx t3 on t1.xh=t3.xh )");
		sql.append(" where lvl = 1 and shzt = '1' and xn = ? and xq = ?");
		sql.append(rddcsql);
		sql.append("   and gwid = ? and bjdm = (select bjdm from view_xsjbxx where xh = ?)");
		
		return dao.getOneRs(sql.toString(), new String[]{
			rsszMap.get("xn"),rsszMap.get("xq"),t.getXtgwid(),t.getXh()}, "tgrs");
	}
	
	
	/** 
	 * @����:����UGID��ѯ������������Ϣ
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-1-27 ����04:02:18
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param guid
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws 
	 */
	public HashMap<String, String> getKnsqInfo(String guid) {
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select * from VIEW_NEW_DC_XSZZ_KNSRDSQ where guid = ?");
		
		return dao.getMapNotOut(sql.toString(), new String[]{guid});
	}
	
	/** 
	 * @����:����UGID��ѯ��������������Ϣ
	 * @���ߣ�HongLin[���ţ�707]
	 * @���ڣ�2014-2-18 ����04:16:37
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param guid
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws 
	 */
	public HashMap<String, String> getKnsqjgInfo(String guid) {
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select * from VIEW_NEW_DC_XSZZ_KNSRDCX where guid = ?");
		
		return dao.getMapNotOut(sql.toString(), new String[]{guid});
	}
	/**
	 * 
	 * @����:��ȡ�����Ϣ
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2015-6-16 ����03:00:47
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param guid
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String> getSpxxInfo(String guid) {
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.*, b.shyj ejshyj,b.shzt ejshzt,b.zd2 ejtjdcdm ,b.zd3 ertjdcmc, c.shyj sjshyj,c.shzt sjshzt,c.zd2 sjtjdcdm,c.zd3 sjtjdcmc from(select a.* from(select a.ywid,a.shyj yjshyj,a.shzt yjshzt,a.zd2 yjtjdc,row_number() over(partition by a.ywid order by a.shsj desc) rn from xg_xtwh_shztb a   where a.ywid=?)a where a.rn=3)a left join");
		sql.append("  (select b.* ,row_number() over(partition by b.ywid order by b.shsj desc) rn from xg_xtwh_shztb b  where b.ywid=?)b on a.ywid=b.ywid and b.rn=2");
		sql.append(" left join (select  c.*,row_number() over(partition by c.ywid order by c.shsj desc) rn from xg_xtwh_shztb c   where c.ywid=?)c on a.ywid=c.ywid and c.rn=1 ");
		return dao.getMapNotOut(sql.toString(), new String[]{guid,guid,guid});
	}
	
	/** 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-3-18 ����03:27:01
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws 
	 */
	public HashMap<String,String> getNewKnsdc(KnsrdForm model){
		
		String sql = "select * from xg_xtwh_shztb where ywid = ? and zd2 is not null and rownum = 1 order by shsj desc ";
		
		return dao.getMapNotOut(sql, new String[]{model.getGuid()});
	}
	
	
	/**
	 * 
	 * @����:��˻�ȡ����zd5ֵ
	 * @���ߣ�tgj[���ţ�1075]
	 * @���ڣ�2017-8-14 ����02:49:24
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String,String> getNewBjpjjg(KnsrdForm model){
		
		String sql = "select * from xg_xtwh_shztb where ywid = ? and zd5 is not null and rownum = 1 order by shsj desc ";
		
		return dao.getMapNotOut(sql, new String[]{model.getGuid()});
	}
	
	public HashMap<String,String> getSqxz(String rddc,String xh,String xn,String xq){
		StringBuffer sql =new StringBuffer();
		sql.append("select b.*,case when b.rddc=? then '�����϶�' when b.rddc!=? and b.rddc is not null then '���ε���' else '������' end sqxz from(select b.*,row_number()over(partition by b.xh order by b.xn desc,b.xq,sqsj desc) rn");
        sql.append(" from  xg_xszz_new_knsjgb_lssj b  where b.xh=? and  ((b.xn=? and b.xq<?) or (b.xn<?)) )b where rn = 1");
		
        return dao.getMapNotOut(sql.toString(), new String[]{rddc,rddc,xh,xn,xq,xn});
	}
	
	public HashMap<String,String> getNewKnyy(KnsrdForm model){
		
		String sql = "select * from xg_xtwh_shztb where ywid = ? and zd10 is not null and rownum = 1 order by shsj desc ";
		
		return dao.getMapNotOut(sql, new String[]{model.getGuid()});
	}
	
	/**
	 * 
	 * @����: �����������ƽ����
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-6-21 ����03:40:07
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getSqlymcList() {
		String sql = "select sqlydm,sqlymc from xg_xszz_xbmz_dmwh order by sqlydm ";
		return dao.getList(sql, new String[] {}, new String[] { "sqlydm",
				"sqlymc" });
	}
	/**
	 * @����:�㽭��ѧ���Ի�_��������һ�����ͨ�������������������ı�������
	 * @���ߣ�Meng.Wei[���ţ�1186]
	 * @���ڣ�2016-10-19 ����10:54:33
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getXyKnsfp(KnsrdForm model) throws Exception{
		StringBuffer sql = new StringBuffer();
		List<String> params = new ArrayList<String>();
		sql.append(" select * from ( ");
		sql.append(" select t1.xymc,t1.kn,t1.tk,round((t1.tk/t1.kn),2)*100||'%' bl from ( ");
		sql.append(" select t1.xymc,sum(case when t.zd2='1' then 1 else 0 end) kn,sum(case when t.zd2='2' then 1 else 0 end) tk from ( ");
		sql.append(" select a.xh,b.shsj,b.zd2,b.zd3,b.shzt,row_number()over(partition by a.xh order by b.shsj desc) rn  ");
		sql.append(" from (select * from xg_xszz_new_knssqb where xn = (select rdxn from XG_XSZZ_NEW_KNSJBSZB ) )a left join xg_xtwh_shztb b on a.guid=b.ywid ");
		sql.append(" left join xg_xtwh_spbz c on b.gwid=c.spgw where c.xh='1' ) t left join view_xsbfxx t1 on t.xh=t1.xh where t.rn = 1 and t.shzt ='1' ");
		sql.append(" group by t1.xymc order by xymc ) t1 ");
		sql.append(" ) where 1 = 1 ");
		if(StringUtils.isNotNull(model.getYlzd9())){
			sql.append(" and xymc like '%'||?||'%' ");
			params.add(model.getYlzd9());
		}
		return getPageList(model, sql.toString(),params.toArray(new String[]{}));
	}
	
	public String getSplcXh(String spgw,String splc) {
		String sql=" select * from XG_XTWH_SPBZ t where spgw=? and splc=? ";
		return dao.getMapNotOut(sql, new String[]{spgw,splc}).get("xh");
	}
	
	/**
	 * 
	 * @����: ��ȡѧԺ����
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-12-18 ����10:20:08
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xydm
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getXymc(String xydm){
		StringBuilder sql = new StringBuilder();
		sql.append(" select distinct xymc  from view_njxyzybj where xydm = ? ");
		return dao.getOneRs(sql.toString(), new String[]{xydm}, "xymc");
	}
	
	/**
	 * @description	�� ��ͥ��������list
	 * @author 		�� ������1282��
	 * @date 		��2017-12-13 ����04:20:24
	 * @return
	 */
	public List<HashMap<String, String>> getJtknlxList(){
		String sql = "select dm,mc from xg_knsrd_jtknlx";
		return dao.getListNotOut(sql, new String[]{});
	}
	
	/**
	 * @description	�� �ߵ�����Ʒlist
	 * @author 		�� ������1282��
	 * @date 		��2017-12-13 ����04:22:13
	 * @return
	 */
	public List<HashMap<String,String>> getGdxfpLxList(){
		String sql = "select dm,mc from xg_knsrd_gdxfplx";
		return dao.getListNotOut(sql, new String[]{});
	}

	public List<HashMap<String,String>> getSqlyList() {
		StringBuffer sql = new StringBuffer();
		sql.append("select dm,mc from XG_XSZZ_KNSSQLYB where 1=1 ");
		sql.append(" order by xh");
		return dao.getListNotOut(sql.toString(), new String[]{});
	}
	public String[] getSqlyListByDms(String[] ids) throws SQLException {
		StringBuffer sql = new StringBuffer();
		sql.append("select mc from XG_XSZZ_KNSSQLYB where 1=1 ");
		if(ids.length>0){
			sql.append(" and dm in ( ");
			for(int i = 0;i<ids.length-1;i++){
				sql.append("'"+ids[i]+"',");
			}
			sql.append("'"+ids[ids.length-1]+"') ");
		}else{
			sql.append(" and 1<>1 ");
		}
		sql.append(" order by xh");

		return dao.getArray(sql.toString(), new String[]{},"mc");
	}
}
