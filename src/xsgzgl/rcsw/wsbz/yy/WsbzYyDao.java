/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-5-5 ����09:59:59 
 */  
package xsgzgl.rcsw.wsbz.yy;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;
import xsgzgl.rcsw.wsbz.dmwh.WsbzDmwhDao;
import xsgzgl.rcsw.wsbz.dmwh.WsbzDmwhForm;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2016-5-5 ����09:59:59 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class WsbzYyDao extends SuperDAOImpl<WsbzYyForm> {

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(WsbzYyForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(WsbzYyForm t, User user)
			throws Exception {
		// TODO �Զ����ɷ������
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (");
		sql.append(" select t.sqid,t.xh,t.fddm,decode(t2.hdpl,'1',t.yyrq,'2',t.yyzc) yyrq,t.sqsj,t.sqly,t.yyzc,t.yyrq yyrqday,  ");
		sql.append(" t1.xm, t1.bjdm, t1.bjmc, t1.xydm,t1.zydm,t1.zymc, t1.xymc, t1.nj, t1.xb,t1.sjhm,t2.fdmc,t2.hdpl,");
		sql.append(" decode(t2.hdpl,'1','��','2','��',t2.hdpl) hdplmc,t2.sj,t2.dd,t2.rs,t2.gzzz,t2.fwyq");
		sql.append(" from rcsw_wsbz_sq t");
		sql.append(" left join view_xsjbxx t1");
		sql.append(" on t.xh = t1.xh");
		sql.append(" left join rcsw_wsbz_dmwh t2");
		sql.append(" on t.fddm = t2.fddm");
		sql.append(" )t where 1= 1  ");
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
		this.setClass(WsbzYyForm.class);
		this.setTableName("rcsw_wsbz_sq");
		this.setKey("sqid");
	}
	
	public boolean AddUpdateYyTimeCheck(){
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1) num");
		sql.append(" from (select to_date(to_char(sysdate, 'yyyy-MM-dd hh24:mi:ss'),");
		sql.append(" 'yyyy-MM-dd hh24:mi:ss') nowtime");
		sql.append(" from dual) t,");
		sql.append(" (select to_date(to_char( trunc(sysdate, 'DD') - to_char(sysdate, 'D') + 4, 'yyyy-MM-dd') || '00:00:00',");
		sql.append(" 'yyyy-MM-dd hh24:mi:ss') beagintime");
		sql.append(" from dual)t1,");
		sql.append(" (select to_date(to_char( trunc(sysdate, 'DD') - to_char(sysdate, 'D') + 4, 'yyyy-MM-dd') || '12:00:00',");
		sql.append("  'yyyy-MM-dd hh24:mi:ss') endtime");
		sql.append("  from dual)t2");
		sql.append(" where t.nowtime between t1.beagintime and t2.endtime");
		sql.append(" ");
		sql.append(" ");
		sql.append(" ");
		String num =  dao.getOneRs(sql.toString(), new String[]{}, "num");
		if(num.equals("0")){
			return false;
		}else{
			return true;
		}
	}
	/**
	 * 
	 * @����:�жϰ���Ϊ�Ƶ�ʵĻ�����Ƿ�����޸�
	 * �ж�����ΪԤԼ����ǰһ���12��֮ǰ
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-6-21 ����01:46:33
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param sqsj
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean updateYyTimeCheck(String sqsj){
		StringBuilder sql = new StringBuilder();
		if(StringUtils.isNull(sqsj)){
			return false;
		}
//		if(sqsj.indexOf("��") != -1){
//			String[] sqsjs=sqsj.split("��");
//			sqsj = this.getPriviousDay(sqsjs[0]);
//		}
		sql.append(" select count(1) num");
		sql.append(" from(select to_char(sysdate,'yyyy-MM-dd hh24:mi:ss') beagintime,");
		sql.append(" to_char(to_date(?,'yyyy-mm-dd')-(select jzts from rcsw_wsbz_qjcswh),'yyyy-mm-dd') || ' '||(select jzsj from rcsw_wsbz_qjcswh) endtime from dual )");
		sql.append(" where beagintime < endtime ");
		sql.append(" ");
		String num =  dao.getOneRs(sql.toString(), new String[]{sqsj}, "num");
		if(num.equals("0")){
			return false;
		}else{
			return true;
		}
	}
	
	public String produceHdplDay(){
		StringBuilder sql = new StringBuilder();
		//�ж��Ƿ���ԤԼǰһ��12��֮ǰ
		String flag = this.getCheckDaytwf();
		sql.append(" select to_char(yyrq, 'yyyy-mm-dd') yyrq");
		sql.append("  from (select case");
		sql.append("  when t.day in ('����һ', '���ڶ�')   then");
		if("1".equals(flag)){
			sql.append("  trunc(sysdate, 'DD') - to_char(sysdate, 'D') + 4");
		}else{
			sql.append("    NEXT_DAY(sysdate+1, 4)");
		}
		sql.append("  else");
		sql.append(" NEXT_DAY(sysdate, 4)");
		sql.append(" end yyrq");
		sql.append(" from (select to_char(sysdate, 'day') day from dual) t) t1");
		return dao.getOneRs(sql.toString(), new String[]{}, "yyrq");
	}
	
	/**
	 * @����: ��ǰ�����������ܴ�������ǰ���������ܣ���ǰ��
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-6-21 ����03:23:20
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String> produceHdplfWeek(){
		StringBuilder sql = new StringBuilder();
		sql.append(" select");
		sql.append(" to_char(last_day(sysdate),'w') zcnum ,");
		sql.append(" TO_CHAR(SYSDATE,'WW') - TO_CHAR(TRUNC(SYSDATE,'MM'),'WW') + 1 AS weekofmon,");
		sql.append(" to_char(sysdate,'mm') dy");
		sql.append(" from dual");
		sql.append(" ");
		sql.append(" ");
		return dao.getMapNotOut(sql.toString(), new String[]{});
	}
	
	public boolean isNowDateHaveYyjl(String xh){
		StringBuilder sql = new StringBuilder();
		sql.append("  select count(1) num");
		sql.append("  from rcsw_wsbz_sq t");
		sql.append("  where to_char(to_date(t.sqsj,'yyyy-MM-dd hh24:mi:ss'),'yyyy-mm-dd') =");
		sql.append("  to_char(sysdate, 'yyyy-mm-dd') and xh = ?");
		String num  = dao.getOneRs(sql.toString(), new String[]{xh}, "num");
		if(num.equals("0")){
			return true;
		}else{
			return false;
		}
	}
	
	//��֤��������
	public boolean isEveryXhTwoRecode(String xh){
		StringBuilder sql = new StringBuilder();
		StringBuilder sql1 = new StringBuilder();
		sql.append(" select count(1) num from rcsw_wsbz_sq t where t.xh = ?");
		sql1.append("select bmcs num from rcsw_wsbz_qjcswh");
		String num  = dao.getOneRs(sql.toString(), new String[]{xh}, "num");
		String bmcs  = dao.getOneRs(sql1.toString(), new String[]{}, "num");
		if(Integer.parseInt(num) >= Integer.parseInt(bmcs)){
			return false;
		}else{
			return true;
		}
	}
	
	public boolean isNotOverRssx(String fddm,String rs,String yyrq,String hdpl){
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1) num");
		sql.append(" from rcsw_wsbz_sq t");
		sql.append(" where fddm = ?");
		if("1".equals(hdpl)){
			sql.append(" and t.yyrq = ?");
		}else{
			sql.append(" and t.yyzc = ?");
		}
		
		String num = dao.getOneRs(sql.toString(), new String[]{fddm,yyrq}, "num");
		if(Integer.parseInt(num) >= Integer.parseInt(rs)){
			return false;
		}else{
			return true;
		}
	}
	
	public List<HashMap<String, String>> getFdmcList(){
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from rcsw_wsbz_dmwh");
		return dao.getListNotOut(sql.toString(), new String[]{} );
	}
	
	public HashMap<String,String> getFdmcInfo(String fddm){
		StringBuilder sql = new StringBuilder();
		sql.append("select * from rcsw_wsbz_dmwh where fddm = ?");
		return dao.getMapNotOut(sql.toString(), new String []{fddm});
	}
	
	public String getSyrs(String fddm,String yyrq) throws Exception{
		StringBuilder sql = new StringBuilder();
		WsbzDmwhForm dmwhform = new WsbzDmwhDao().getModel(fddm);
		String yyrqstr = "";
		if(dmwhform.getHdpl().equals("1")){
			yyrqstr = "yyrq";
		}else{
			yyrqstr = "yyzc";
		}
		sql.append(" select (t.rs-t1.num) num from");
		sql.append(" (select rs from rcsw_wsbz_dmwh where fddm = ?)t,");
		sql.append(" (select count(1) num");
		sql.append(" from rcsw_wsbz_sq t");
		sql.append(" where fddm = ?");
		sql.append(" and t."+yyrqstr +" = ?)t1");
		return dao.getOneRs(sql.toString(), new String[]{fddm,fddm,yyrq}, "num");
	}
	
//	public boolean isNotOverRssx(String fddm,String rs,String yyrq,String xh){
//		StringBuilder sql = new StringBuilder();
//		sql.append(" select count(1) num");
//		sql.append(" from rcsw_wsbz_sq t");
//		sql.append(" where fddm = ?");
//		sql.append(" and t.yyrq = ?");
//		sql.append(" and t.xh != ?");
//		String num = dao.getOneRs(sql.toString(), new String[]{fddm,yyrq,xh}, "num");
//		if(Integer.parseInt(num) >= Integer.parseInt(rs)){
//			return false;
//		}else{
//			return true;
//		}
//	}
	
	//ͬһ��ԤԼԤԼ�����ڣ�ѧ����������������
	public boolean isNotSameTwo(String fddm,String yyrq,String xh) throws Exception{
		StringBuilder sql = new StringBuilder();
		WsbzDmwhForm dmwhform = new WsbzDmwhDao().getModel(fddm);
		sql.append(" select count(1) num");
		sql.append(" from rcsw_wsbz_sq t");
		sql.append(" where fddm = ?");
		if("1".equals(dmwhform.getHdpl())){
			sql.append(" and t.yyrq = ?");
		}else{
			sql.append(" and t.yyzc = ?");
		}
		sql.append(" and t.xh = ?");
		String num = dao.getOneRs(sql.toString(), new String[]{fddm,yyrq,xh}, "num");
		if(Integer.parseInt(num) >= 1){
			return false;
		}else{
			return true;
		}
	}
	
	/**
	 * 
	 * @����:
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-6-21 ����08:58:28
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param sqsj
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean updateZctimeCheck(String yyrq){
		StringBuilder sql = new StringBuilder();
		sql.append(" ");
		sql.append(" ");
		sql.append(" ");
		sql.append(" ");
		sql.append(" ");
		sql.append(" ");
		sql.append(" ");
		sql.append(" ");
//		String priday = this.getPriviousDay(yyrq);
//		StringBuilder sql = new StringBuilder();
//		sql.append(" select count(1) num");
//		sql.append(" from");
//		sql.append(" (select sysdate beagintime");
//		sql.append(" from dual)t1,");
//		sql.append(" (select to_date(? || '12:00:00',");
//		sql.append(" 'yyyy-MM-dd hh24:mi:ss') endtime");
//		sql.append(" from dual)t2");
//		sql.append(" where t1.beagintime < t2.endtime");
//		sql.append(" ");
//		sql.append(" ");
//		sql.append(" ");
//		String num =  dao.getOneRs(sql.toString(), new String[]{priday}, "num");
//		if(num.equals("0")){
//			return false;
//		}else{
//			return true;
//		}
		return true;
	}
	
	/**
	 * 
	 * @����:��ȡ��ǰ�����������ж����ܴ�,ǰ���������ܴ�
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-6-21 ����09:16:31
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * String �������� 
	 * @throws
	 */
	public HashMap<String, String> getDqrqZc(){
		StringBuilder sql = new StringBuilder();
		sql.append(" select to_char(last_day(sysdate),'w') zcnum");
		sql.append(" ,TO_CHAR(SYSDATE,'WW') - TO_CHAR(TRUNC(SYSDATE,'MM'),'WW') + 1 AS weekofmon ");
		sql.append(" from dual");
		return dao.getMapNotOut(sql.toString(), new String[]{});
	}
	
	
	/**
	 * 
	 * @����: ��ȡԤԼʱ��ǰһ��
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-6-21 ����11:27:12
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getPriviousDay(String yyrq){
		StringBuilder sql = new StringBuilder();
		sql.append("  select to_char(to_date(?,'yyyy-mm-dd')-2,'yyyy-MM-dd') priday from dual");
		return dao.getOneRs(sql.toString(), new String[]{yyrq}, "priday");
	}
	
	/**
	 * @����:�ж��Ƿ���������֮ǰ
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-6-21 ����03:31:34
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean checkPriviousFriday(){
		StringBuilder sql = new StringBuilder();
		sql.append("  select case");
		sql.append("  when t.day in ('����һ', '���ڶ�', '������','������','������','������') then");
		sql.append("  '0'");
		sql.append("  else");
		sql.append("  '1'");
		sql.append("  end sfxz");
		sql.append("  from (select to_char(sysdate, 'day') day from dual) t");
	    return ("1").equals(dao.getOneRs(sql.toString(), new String[]{}, "sfxz")) ? true : false;
	}
	
	/**
	 * 
	 * @����:�ж��Ƿ񳬹�ԤԼǰһ��12��
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-6-21 ����05:05:58
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getCheckDaytwf(){
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1) num from");
		sql.append(" (");
		sql.append(" select to_date(to_char(trunc(sysdate, 'DD') -");
		sql.append("  to_char(sysdate, 'D') + 3,");
		sql.append("  'yyyy-MM-dd') || '12:00:00',");
		sql.append("  'yyyy-MM-dd hh24:mi:ss') endtime,");
		sql.append("  to_date(to_char(sysdate, 'yyyy-MM-dd hh24:mi:ss'),");
		sql.append(" 'yyyy-MM-dd hh24:mi:ss') begintime");
		sql.append("  from dual t) where begintime < endtime");
		return dao.getOneRs(sql.toString(), new String[]{}, "num");
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @����:����yyrq�ֶ�Ϊ��
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-6-29 ����04:09:12
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param sqid
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean updateYyrqdaynull(String sqid) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" update rcsw_wsbz_sq set yyrq = '' where sqid = ?");
		return dao.runUpdate(sql.toString(), new String[]{sqid});
	}
	
	/**
	 * 
	 * @����:����yyzc�ֶ�Ϊ��
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-6-29 ����04:12:08
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param sqid
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean updateYyzcnull(String sqid) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" update rcsw_wsbz_sq set yyzc = '' where sqid = ?");
		return dao.runUpdate(sql.toString(), new String[]{sqid});
	}
	
	/**
	 * 
	 * @����: ��ȡԤԼ�ܴα�List
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-10-27 ����06:47:14
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getYyzcb(){
		StringBuffer sql = new StringBuffer();
		sql.append(" select yyzc from yyzcb");
		return dao.getListNotOut(sql.toString(), new String[]{});
	}
	
	/**
	 * 
	 * @����:��ȡԤԼ���ڱ�
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-10-27 ����06:52:16
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getYyrqb(){
		StringBuffer sql = new StringBuffer();
		sql.append(" select yyrq from yyrqb order by yyrq");
		return dao.getListNotOut(sql.toString(), new String[]{});
	}

	public boolean isExist(String yyrq) {
		String sql = "select count(1) num from yyrqb where yyrq = ?" ;
		String num = dao.getOneRs(sql, new String[]{yyrq}, "num");
		return Integer.valueOf(num)>0;
	}
	
}
