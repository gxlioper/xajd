/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-9-9 ����12:07:04 
 */
package com.zfsoft.xgxt.rcsw.qjgl.qjsq;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.Arrays2;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.base.util.Constants;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������)
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2013-9-9 ����12:07:04
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class QjsqDao extends SuperDAOImpl<QjsqForm> {

	/*
	 * ����: @see
	 * com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object,
	 * xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(QjsqForm t, User user)
			throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(t.getSearchModel());

		StringBuffer sql = new StringBuffer();
		//�㽭����ְҵ���Ի����ж���ٿ�ʼʱ���Ƿ�ʱ��
		if("12866".equals(Base.xxdm)){
			sql.append("select a.*,case when a.shzt = '0' and to_char(sysdate, 'yyyy/mm/dd hh24:mi') > a.cssj then '1' when a.shzt = '5' and to_char(sysdate, 'yyyy/mm/dd hh24:mi') > a.cssj then '1' else '0' end sfcs from (");
		}else{			
			sql.append(" select a.* from (");
		}
		sql.append(" select a.*,(select xqmc from xqdzb where xqdm=a.xq) xqmc,c.xjzt,xsxx.xm,xsxx.xb,xsxx.nj,xsxx.xymc,xsxx.xydm,xsxx.zymc,xsxx.zydm,xsxx.bjmc,xsxx.bjdm,b.qjlxmc,decode(a.qjzt,'','�ݸ�','0','�ݸ�','1','���ύ')qjztmc,");
		sql.append(" decode(a.shzt,'0','δ�ύ','1','ͨ��','2','��ͨ��','3','���˻�','5','�����','','�������',a.shzt) shztmc,");
		sql.append(" decode(c.xjzt, '', 'δ����', '0', 'δ����', '1', '������') xjztmc");
		//�������⾭��ѧԺ
		if("12303".equals(Base.xxdm)){
			sql.append(" , cwxx.lddm,cwxx.ldmc,cwxx.qsh");
		}
		//�㽭����ְҵ���Ի����ж���ٿ�ʼʱ���Ƿ�ʱ��
		if("12866".equals(Base.xxdm)){
			sql.append(", case when instr(a.kssj,':') = '0' then  to_char((to_date(a.kssj,'yyyy/mm/dd HH24:mi') + 1),'yyyy/mm/dd HH24:mi') else  to_char((to_date(a.kssj,'yyyy/mm/dd HH24:mi')+1 / 24 / 60),'yyyy/mm/dd HH24:mi') end cssj");			
		}
		//���������Ϣ
		sql.append(" from xg_rcsw_qjgl_qjsq a left join xg_rcsw_qjgl_qjlx b on a.qjlxid=b.qjlxid ");
		//������Ϣ
		sql.append(" left join xg_rcsw_qjgl_qjjg c on a.qjsqid=c.qjsqid");
		//�������⾭��ѧԺ
		if("12303".equals(Base.xxdm)){
			sql.append(" left join view_xg_gygl_new_cwxx cwxx on a.xh = cwxx.xh");
		}
		//ѧ����Ϣ
		sql.append(" left join view_xsbfxx xsxx on a.xh=xsxx.xh ) a where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		return this.getPageList(t, sql.toString(), inputV);
	}
	/**
	 * @����:���δ�ύ��
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-9-18 ����03:53:14
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return Integer �������� 
	 * @throws
	 */
	public Integer getWtjts(QjsqForm t, User user) throws Exception{
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		//String searchTjByUser = SearchService.getSearchTjByUser(user, "a","xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		
		StringBuffer sql = new StringBuffer();
		sql.append("select qjsqid from xg_rcsw_qjgl_qjsq a where 1=1 ");
		//sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		sql.append(" and a.qjzt=? ");
		
		List<HashMap<String, String>> list=dao.getListNotOut(sql.toString(), Arrays2.addObject(inputV,QjsqService._SQZT_CGZT));
		return null==list||list.size()<=0?0:list.size();
	}
	/**
	 * 
	 * @����:�Ƿ����ɾ��
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-9-13 ����02:12:37
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param qjsqid
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean isCanDel(String qjsqid){
		StringBuffer sb=new StringBuffer();
		sb.append("select * from xg_rcsw_qjgl_qjsq where qjsqid=?");
		Map<String,String> map= dao.getMapNotOut(sb.toString(),new String[]{qjsqid});
		//String qjzt=map.get("qjzt");
		//���δ�ύ�ſ����ύ
		//return null==qjzt||qjzt.equals("0")?true:false;
		String shzt=map.get("shzt");
		//Ϊ������Ϊ��������� �Լ�ͬ��������� ���ܽ���ɾ��
		if(StringUtils.isNull(shzt)){
			return false;
		}
		return shzt.equals(Constants.YW_WTJ)?true:false;
	}
	/*
	 * ����: @see
	 * com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(QjsqForm t)
			throws Exception {
		return null;
	}
	/**
	 * 
	 * @����:�����������idɾ����Ӧ��ٽ������
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-9-14 ����06:45:13
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param id
	 * @return
	 * @throws Exception
	 * int �������� 
	 * @throws
	 */
	public int deletQjjgForQjsqid(String id) throws Exception{
		StringBuffer sql=new StringBuffer();
		sql.append("delete xg_rcsw_qjgl_qjjg where qjsqid=?");
		return dao.runDelete(sql.toString(), new String[]{id});
	}
	/**
	 * 
	 * @����:��������Ϣ
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-9-14 ����04:36:50
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param qjjgid
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String> getQjsq(String qjsqid){
		StringBuffer sb=new StringBuffer();
		sb.append("select a.*,(select xqmc from xqdzb where xqdm=a.xq) xqmc,b.xm, b.bjmc, b.zymc, b.sjhm from xg_rcsw_qjgl_qjsq a");
		sb.append(",view_xsxxb b where a.xh=b.xh and a.qjsqid=?");
		return dao.getMapNotOut(sb.toString(),new String[]{qjsqid});
	}
	/**
	 * 
	 * @��������ȡ��һ���������id
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-9-9 ����06:54:25
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return String ��������
	 * @throws
	 */
	public String getNextId() {
		String qjlxid = dao.getOneRs(
				"select max(qjlxid)qjlxid from xg_rcsw_qjgl_qjlx ",
				new String[] {}, "qjlxid");
		String newId = String.valueOf(Integer.parseInt(qjlxid) + 1);
		if (newId.length() == 1) {
			newId = "0" + newId;
		}
		return newId;
	}

	/**
	 * 
	 * @����:��֤������ͣ�����Ѿ���ӹ������ٽ������
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-9-9 ����07:18:11
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param qf
	 * @return boolean ��������
	 * @throws
	 */
	public boolean checkQjlx(QjsqForm qf) {
		// String
		// qjlxid=dao.getOneRs("select qjlxid from xg_rcsw_qjgl_qjgz where kssj<? and ?<=jssj",new
		// String[]{qf.getQjlxmc()},"qjlxid");
		// �����ȡ����Ӧ��id�����������
		return true;
	}
	public List<HashMap<String, String>> getQjlx(){
		StringBuffer sql=new StringBuffer();
		sql.append(" select qjlxid,qjlxmc from xg_rcsw_qjgl_qjlx where open = '1' order by qjlxid asc");
		return dao.getListNotOut(sql.toString(),new String[]{});
	}
	/**
	 * @throws Exception 
	 * 
	 * @����:��ȡѧ����ǰѧ��ѧ�ڵĿγ�)
	 * @���ߣ�xiaxia [���ţ�1104]
	 * @���ڣ�2014-11-18 ����02:51:21
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getQjkcList(QjsqForm model) throws Exception{
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		StringBuffer sql=new StringBuffer();
		sql.append(" select * from XG_RCSW_QJGL_QJKCB where xh='"+model.getXh()+"' and xn ='"+model.getXn()+"'and xq = '"+model.getXq()+"'");
		sql.append(" ");
		sql.append(searchTj);
		return this.getPageList(model, sql.toString(), inputV);
	}
	/**
	 * 
	 * @����:��ȡ��ٿγ���Ϣ
	 * @���ߣ�xiaixa [���ţ�1104]
	 * @���ڣ�2014-11-19 ����10:10:56
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getkcList(String[] kcbhs) throws Exception{
		
		StringBuffer sql=new StringBuffer();
		sql.append(" select distinct(kcbh) ,kcmc,rklsxm,rklslxfs from xg_rcsw_qjgl_qjkcb where kcbh");
		sql.append(" in(");
		for (int i = 0; i < kcbhs.length; i++) {
			if(i!=0){
				sql.append(","+"?");
			}
			else{
				sql.append("?");
			}
		}
		sql.append(")");
		return dao.getListNotOut(sql.toString(),kcbhs);
	}
	/*
	 * ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	@Override
	protected void setTableInfo() {
		this.setKey("qjsqid");
		this.setTableName("xg_rcsw_qjgl_qjsq");
		this.setClass(QjsqForm.class);
	}
	
	
	/**
	 * 
	 * @����: ɾ�������ϸ
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2014-11-25 ����01:47:08
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param id
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean delQjmx(String id) throws Exception{
		String sql = "delete from XG_RCSW_QJGL_QJSQMXB where qjid=?";
		return dao.runUpdate(sql, new String[]{id});
	}

	
	/**
	 * 
	 * @����: ���������ϸ
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2014-11-25 ����01:50:34
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param params
	 * @return
	 * @throws SQLException
	 * boolean �������� 
	 * @throws
	 */
	public boolean saveQjmx(List<String[]> params) throws SQLException{
		
		String sql = "insert into XG_RCSW_QJGL_QJSQMXB(qjid,qjrq,qjmx) values (?,?,?)";
		int[] result = dao.runBatch(sql, params);
		return dao.checkBatchResult(result);
	}
	
	
	/**
	 * 
	 * @����: ��ѯ�����ϸ
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2014-11-25 ����03:03:26
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param id
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getQjmxList(String id){
		
		String sql = "select * from XG_RCSW_QJGL_QJSQMXB where qjid=?";
		return dao.getListNotOut(sql, new String[]{id});
	}
	
	/**
	 * 
	 * @����:��ȡ��ٱ�ţ���ʦ��
	 * @���ߣ�xiaxia [���ţ�1104]
	 * @���ڣ�2014-11-25 ����03:53:40
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getQjbh(String sqsj){
		StringBuffer sql=new StringBuffer();
		sql.append("select (case when length(qjbh) < 2 then '0' ||qjbh else to_char(qjbh)");
		sql.append(" end) qjbh from (select (to_number(nvl(max(qjbh),0))+1) qjbh");
		sql.append(" from (select  to_char(to_date(sqsj,'yyyy-MM-dd hh24:mi:ss'),'yyyymmdd') sqsj, qjbh");
		sql.append(" from XG_RCSW_QJGL_QJJG union all select to_char(to_date(sqsj,'yyyy-MM-dd hh24:mi:ss'),'yyyymmdd') sqsj, qjbh from XG_RCSW_QJGL_QJSQ)");
		sql.append(" where sqsj=to_char(to_date(?,'yyyy-MM-dd hh24:mi:ss'),'yyyymmdd')) ");
		return dao.getOneRs(sql.toString(), new String[]{sqsj}, "qjbh");     
	}
	
	/**
	 * 
	 * @����:TODO(��֤�Ƿ��ظ�����)
	 * @���ߣ�����[���ţ�856]
	 * @���ڣ�2015-5-25 ����07:23:42
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param kssj
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean getDupStatusDao(QjsqForm model){
		StringBuilder sql = new StringBuilder();
		sql.append("select count(1) num from xg_rcsw_qjgl_qjsq where xh= ?");
		sql.append(" and jssj > ? and kssj< ? and shzt not in ('0','3') ");
		String num=dao.getOneRs(sql.toString(), new String[]{model.getXh(),model.getKssj(),model.getJssj()}, "num");
		return  Integer.valueOf(num) > 0 ? true:false;
	}
	
	
	
	/* (non-Javadoc)
	 * @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getModel(java.lang.Object)
	 */
	@Override
	public QjsqForm getModel(QjsqForm t) throws Exception {
		String sql = "select t1.*,t2.qjlxmc,decode(t1.sflx,'1','��','0','��','') sflxmc from xg_rcsw_qjgl_qjsq t1 left join xg_rcsw_qjgl_qjlx t2 on t1.qjlxid=t2.qjlxid where t1.qjsqid=?";
		return super.getModel(sql, new String[]{t.getQjsqid()});
	}
	
	/**
	 * 
	 * @����:�й�����ٹ���List
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-11-30 ����11:31:53
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getYlxQjgzList(){
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from xg_rcsw_qjgl_qjgz where qjlxid is not null and open = '1'");
		return dao.getListNotOut(sql.toString(), new String[]{});
	}
	
	/**
	 * 
	 * @����:�޹�����ٹ���List
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-11-30 ����11:36:44
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getWlxQjgzList(){
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from xg_rcsw_qjgl_qjgz where qjlxid is  null and open = '1'");
		return dao.getListNotOut(sql.toString(), new String[]{});
	}
	
	/**
	 * 
	 * @����: �ڼ���
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2017-1-18 ����02:49:02
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public boolean checkJjr(String kssj,String jssj){
		kssj = kssj.substring(0,10);
		jssj = jssj.substring(0,10);
		StringBuffer sql = new StringBuffer();
		sql.append(" select count(1) num");
		sql.append(" from (select to_char(to_date(kssj, 'yyyy-mm-dd hh24:mi:ss'), 'yyyy-mm-dd') kssj,");
		sql.append(" to_char(to_date(jssj, 'yyyy-mm-dd hh24:mi:ss'), 'yyyy-mm-dd') jssj");
		sql.append(" from XG_RCSW_QJGL_JJRB)");
		sql.append(" where ? >= kssj");
		sql.append(" and jssj > = ? ");
		String num = dao.getOneRs(sql.toString(), new String[]{kssj,jssj}, "num");
		return "1".equals(num) ? true : false;
	}
	
	/**
	 * 
	 * @����: ��֤kssj��jssj�Ƿ�Ϊ��ĩ
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2017-1-18 ����05:21:19
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param kssj
	 * @param jssj
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean checkWeekDay(String kssj,String jssj){
		StringBuffer sql = new StringBuffer();
		sql.append(" select to_char(to_date(?, 'yyyy-mm-dd hh24:mi:ss'), 'day') weekdaykssj,");
		sql.append(" to_char(to_date(?, 'yyyy-mm-dd hh24:mi:ss'), 'day') weekdayjssj");
		sql.append(" from dual");
		HashMap<String, String> dataMap = dao.getMapNotOut(sql.toString(), new String[]{kssj,jssj});
		String weekdaykssj = dataMap.get("weekdaykssj");
		String weekdayjssj = dataMap.get("weekdayjssj");
		HashMap<String, String> compareMap = new HashMap<String, String>();
		compareMap.put("������", "������");
		compareMap.put("������", "������");
		compareMap.put("������", "������");
		if((compareMap.containsKey(weekdaykssj)) && (compareMap.containsKey(weekdayjssj)) ){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 
	 * @����: �ж���ĩ���Ƿ��ڿ�ʼʱ��ͽ���ʱ����ͬһ��
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2017-1-22 ����10:10:36
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param kssj
	 * @param jssj
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean checkIsOneWeekend(String kssj,String jssj){
		StringBuffer sql = new StringBuffer();
		sql.append(" select (to_date(?,'yyyy-mm-dd hh24:mi:ss')-to_date(?,'yyyy-mm-dd hh24:mi:ss')) days from dual");
		String days = dao.getOneRs(sql.toString(), new String[]{jssj,kssj},"days" );
		return Float.parseFloat(days)< 3;
	}
	
	/** 
	 * @����:��ȡ��ͨ��������(���������ѧ)
	 * @���ߣ�lj[���ţ�1282]
	 * @���ڣ�2017-7-7 ����11:58:25
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * String �������� 
	 * @throws 
	 */
	public String getJtgjmc(String dm){
		String sql = "select mc from xg_rcsw_lxqxdj_dmwhb where dm = ?";
		return dao.getOneRs(sql, new String[]{dm}, "mc");
	}
	
	/**
	 * 
	 * @����: ����ҽҩ���Ի��ж�
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2018-1-12 ����10:36:05
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public String checkIfOverTime(String startDate,String endDate){
		HashMap<String, String> xzmap = getSjxz();
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1) cnt from (");
		sql.append(" select to_char(to_date(?,'yyyy-mm-dd hh24:mi:ss'),'hh24:mi') kssj,");
		sql.append(" to_char(to_date(?,'yyyy-mm-dd hh24:mi:ss'),'hh24:mi') jssj from dual) where kssj >=? and jssj <= ?");
		String cnt = dao.getOneRs(sql.toString(), new String[]{startDate,endDate,xzmap.get("ksxz"),xzmap.get("jsxz")}, "cnt");
		return "0".equals(cnt)?"���ʱ��������"+xzmap.get("ksxz")+"��"+xzmap.get("jsxz")+"֮��" : "";
	}
	
	/**
	 * 
	 * @����: ��ȡʱ����֤
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2018-1-12 ����10:41:37
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public HashMap<String,String> getSjxz(){
		StringBuilder sql = new StringBuilder();
		sql.append(" select xzkssjh || ':' || xzkssjm ksxz, xzjssjh || ':' || xzjssjm jsxz from XG_RCSW_QJSDCSSZB t");
		return dao.getMapNotOut(sql.toString(), new String[]{});
	}
}
