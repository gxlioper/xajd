package xgxt.jygl.comman;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.utils.Pages;

public class JyWEBDAO extends DAO{

	DAO dao = DAO.getInstance();
	
	/**
	 * ��ҵ�������˵�
	 * @param userType
	 * @return
	 */
	public List<HashMap<String,String>> getJywebMenus(String userType){
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select * from (");
		sql.append("select a.dm,b.mc,b.mkly,a.yhqx,to_number(b.xssx) xssx ");
		sql.append("from dmk_jyweb_yhqxb a left join dmk_jyweb_gnmkdmb b ");
		sql.append("on a.dm=b.dm where a.dm like 'J__' ");
		
		
		if (!Base.isNull(userType)) {
			sql.append(" and (userType='");
			sql.append(userType);
			sql.append("' or userType=' ')");
		} else {
			sql.append("and a.userType = ' ' ");
		}
		sql.append(") order by xssx asc");
		
		return dao.getList(sql.toString(), new String[] {}, new String[] {"dm","mc","mkly","yhqx","xssx"});
	}


	/**
	 * ��ҵĿ¼
	 * @return
	 * @throws SQLException 
	 */
	protected List<HashMap<String, String>> getDwList() throws SQLException {
		
		String sql = "select * from jygl_dwxxb where shzt='ͨ��' order by zcsj desc";
		
		return dao.getList(sql, new String[] {}, new String[] {"yhm","dwmc"});
	}

	
	/**
	 * ��ҵ������
	 * DWRҲҪ���� ��~
	 * @return
	 */
	public List<HashMap<String, String>> getNews(String wjlx){
		String sql = "select a.wjbt,a.tplj,to_char(to_date(a.fbsj,'yyyymmdd'),'yyyy-mm-dd') fbsj,rowid from jygl_zcwjb a where wjlx=? order by fbsj desc";
		
		return dao.getList(sql, new String[] {wjlx}, new String[] {"wjbt","fbsj","rowid","tplj"});
		
	}
	
	
	/**
	 * ��ϵ��ʽ
	 * @return
	 */
	protected HashMap<String, String> getLxfs(){
		
		String sql = "select * from jyweb_lxfsb where xxdm=(select xxdm from xtszb)";
		
		return dao.getMapNotOut(sql, new String[] {});
	}
	

	/**
	 * �˲��Ƽ�
	 * @return
	 */
	protected List<HashMap<String, String>> getRctjList(){
		String sql = "select * from VIEW_JYGL_JYTJB  order by lrsj desc";
		
		return dao.getList(sql, new String[] {}, new String[] {"xm","xb","xl","zymc"});
	}
	
	
	/**
	 * ��λ�û���¼��֤
	 * @param userName
	 * @param password
	 * @return
	 */
	protected String[] dwLogin(String userName ,String password) {
		String sql = "select dwmc xm,''szbm,shzt from jygl_dwxxb where yhm=? and mm=? ";
		
		return dao.getOneRs(sql, new String[] {userName,password}, new String[] {"xm","szbm","shzt"});
	}
	
	
	/**
	 * ѧԺ�û���¼��֤
	 * @param userName
	 * @param password
	 * @return
	 */
	protected String[] xyLogin(String userName,String password) {
		String sql = "select * from (select a.*,'' shzt,(select zmc from yhzb where zdm=a.zdm) zmc from yhb a ) where zmc!='��ҵ��' and yhm=? and kl=?";
		
		return dao.getOneRs(sql, new String[] {userName,password}, new String[] {"xm","szbm","shzt"});
	}
	
	
	/**
	 * ѧ���û���¼��֤
	 * @param userName
	 * @param password
	 * @return
	 */
	protected String[] stuLogin(String userName, String password) {
		String sql = "select xm,bmdm szbm,'' shzt from view_xsjbxx where xh=? and mm=?";
		
		return dao.getOneRs(sql, new String[] {userName,password}, new String[] {"xm","szbm","shzt"});
	}
	
	
	/**
	 * ��ҵ����ҳ����
	 * @return
	 */
	public List<HashMap<String, String>> getLayOut(){
		String sql = "select * from jyweb_layout";
		
		return dao.getList(sql, new String[] {}, new String[] {"mkmc","mkbh"});
		
	}
	
	
	/**
	 * ������ϸ��Ϣ
	 * @param rowid
	 * @return
	 */
	protected HashMap<String, String> getNewInfo(String pkValue) {
		String sql = "select * from jygl_zcwjb where rowid=?";
		
		return dao.getMap(sql, new String[] {pkValue}, new String[] {"wjbt","wjlx","fbr","fbsj","wjnr","readtimes","tplj"});
	}
	
	
	/**
	 * ������ϸ��Ϣ
	 * @param rowid
	 * @return
	 */
	protected HashMap<String, String> getNewInfoByWjbt(String pkValue) {
		String sql = "select * from jygl_zcwjb where  wjbt=? ";
		
		return dao.getMap(sql, new String[] {pkValue}, new String[] {"wjbt","wjlx","fbr","fbsj","wjnr","readtimes","tplj"});
	}
	
	
	/**
	 * �޸��������
	 * @param pkValue
	 * @return
	 * @throws Exception 
	 */
	protected boolean setReadTimes(String pkValue) throws Exception {
		String sql = "update jygl_zcwjb set readtimes=readtimes+1 where rowid=?";
		return dao.runUpdate(sql, new String[] {pkValue});
	}
	
	
	/**
	 * �޸��������
	 * @param pkValue
	 * @return
	 * @throws Exception 
	 */
	protected boolean setReadTimesByWjbt(String pkValue) throws Exception {
		String sql = "update jygl_zcwjb set readtimes=readtimes+1 where wjbt=? ";
		return dao.runUpdate(sql, new String[] {pkValue});
	}
	
	
	/**
	 * ͼƬ����
	 * @return
	 */
	protected List<HashMap<String, String>> getTplj(){
		String sql = "select * from jyweb_yqljb where tpdz >' ' order by xssx desc";
		
		return dao.getList(sql, new String[] {}, new String[] {"ljmc","ljwz","tpdz"});
	}
	
	
	/**
	 * ��������
	 * @return
	 */
	protected List<HashMap<String, String>> getWzlj(){
		String sql = "select * from jyweb_yqljb where tpdz is null order by xssx desc";
		
		return dao.getList(sql, new String[] {}, new String[] {"ljmc","ljwz"});
	}
	
	
	/**
	 * ����ר��
	 * @return
	 */
	protected List<HashMap<String, String>> getFiles(){
		String sql = "select * from jyweb_wjglb order by scsj desc";
		
		return dao.getList(sql, new String[] {}, new String[] {"guid","wjm"});
	}
	
	
	/**
	 * �ļ���¼����
	 * @param form
	 * @return
	 * @throws Exception
	 */
	protected boolean saveWjsc(JyglForm form) throws Exception {
		String sql = "insert into jyweb_wjglb (guid,wjm,scr,wjlj,wjsm) values (?,?,?,?,?)";
		
		return dao.runUpdate(sql, new String[] {form.getGuid(),form.getWjm(),form.getScr(),form.getWjlj(),form.getWjsm()});
	}
	
	
	/**
	 * �ļ����������1
	 * @param pkValue
	 * @throws Exception
	 */
	public boolean setFileLlcs(String pkValue) throws Exception {
		String sql = "update jyweb_wjglb set llcs=llcs+1 where guid=?";
		return dao.runUpdate(sql, new String[] {pkValue});
	}
	
	
	/**
	 * �ļ����ش�����1
	 * @param pkValue
	 * @throws Exception
	 */
	public boolean setFileXzcs(String pkValue) throws Exception {
		String sql = "update jyweb_wjglb set xzcs=xzcs+1 where guid=?";
		return dao.runUpdate(sql, new String[] {pkValue});
	}
	
	
	/**
	 * ��ȡ�ļ�����·��
	 * @param pkValues
	 * @return
	 * @throws SQLException
	 */
	protected String[] getFiles(String[] pkValues) throws SQLException {

		StringBuilder sb = new StringBuilder();

		sb.append("select * from jyweb_wjglb where guid in (");
		for (int i = 0; i < pkValues.length; i++) {
			sb.append("'");
			sb.append(pkValues[i]);
			sb.append("'");

			if (i == pkValues.length - 1) {
				sb.append(")");
			} else {
				sb.append(",");
			}

		}

		return dao.getArray(sb.toString(), new String[] {}, "wjlj");

	}


	/**
	 * ����Ԥ��
	 * @return
	 */
	protected List<HashMap<String, String>> getZphList(){
		String sql = "select * from (select zphbt||fbr||fbsj pkValue,zplxdm,zplx,zphbt,to_char(to_date(fbsj,'yyyymmdd'),'yyyy-mm-dd') fbsj from xg_view_jyweb_xyzph where shzt='ͨ��') order by fbsj desc";
		return dao.getList(sql, new String[] {}, new String[] {"pkValue","zplx","zplxdm","zphbt","fbsj"});
	}
	
	
	/**
	 * ��ȡ��ҵ��title
	 * @param path
	 * @return
	 */
	protected String getTitle(String path) {
		String sql = "select * from dmk_jyweb_gnmkdmb where mkly=? and rownum=1";

		return dao.getOneRs(sql, new String[] { path }, "mc");
	}

	
	/**
	 * ��ȡ��Ƹ��Ϣ
	 * @return
	 */
	protected List<HashMap<String, String>> getZpxx(){
		String sql = "select distinct yhm,gsmc from (select zpzw||gsmc||fbsj pkValue,yhm,gsmc,to_char(to_date(fbsj,'yyyymmdd'),'yyyy-mm-dd') fbsj,zpzw from view_jygl_zpxx where xxsh='ͨ��') ";
		return dao.getList(sql, new String[]{}, new String[]{"yhm","gsmc"} );
	}
	
	
	/**
	 * �����б�
	 * @param lx
	 * @return
	 */
	public List<HashMap<String, String>> getSelectList(String lx) {
		String[] dm = null;
		String[] mc = null;
		
		if ("shzt".equals(lx)) {
			dm = new String[] { "δ���", "ͨ��","��ͨ��","�˻�","������"};
			mc = dm;
		} else if ("shjg".equals(lx)){
			dm = new String[] { "ͨ��", "��ͨ��"};
			mc = dm;
		} else if ("jyweb".equals(lx)) {
			dm = new String[] { "1","2","3","4","5","6" };
			mc = new String[] { "��ҵ����","ͼƬ����","֪ͨ����","��ҵ����","��Դ��Ϣ","��ҵ��ҵ����ָ��" };
		} else if("xb".equals(lx)){
			dm = new String[] { "��Ů����", "��", "Ů"};
			mc = dm;
		} else if ("gzxz".equals(lx)) {
			dm = new String[] {"ȫְ","��ְ"};
			mc = dm;
		} else if ("bjqnjyweb".equalsIgnoreCase(lx)){
			dm = new String[] { "1","2","3","4","6" };
			mc = new String[] { "������Ѷ","ͼƬ����", "֪ͨ����","��ҵ��������","��ҵָ������" };
		}
		return dao.arrayToList(dm, mc);
	}
	
	
	/***
	 * ��ǰʱ��
	 * @return
	 */
	public String getNow() {
		
		String sql = "select to_char(sysdate,'yyyymmdd') now from dual";
		
		return dao.getOneRs(sql, new String[] {}, "now");
	}
	
	
	/**
	 * ����Ա��¼ 
	 * @param userName
	 * @param password
	 * @return
	 */
	public String[] adminLogin(String userName,String password) {
		String sql = "select a.*,''shzt from (select a.*, (select zmc from yhzb where zdm=a.zdm) zmc from yhb a ) a where zmc='��ҵ��' and yhm=? and kl=?";
		
		return dao.getOneRs(sql, new String[] {userName,password}, new String[] {"xm","szbm","shzt"});
	}
	
	
	/**
	 * ��ȡָ����λ����Ƹ��Ϣ
	 * @param pkValue
	 * @return
	 */
	protected List<HashMap<String, String>> getZpxx(String pkValue) {
		String sql = "select * from view_jygl_zpxx where yxqx>=to_number(to_char(sysdate,'yyyymmdd')) and gsmc=(select dwmc from jygl_dwxxb where yhm=?) and xxsh='ͨ��' order by fbsj desc";

		return dao.getList(sql, new String[] { pkValue }, new String[] { "zpzw","zpzwmc",
				"zprs", "wyyq", "gwxz", "xlyq", "fbsj","zpsj","zpdd" });
	}
	
	
	/**
	 * ������������
	 * @param values
	 * @return
	 * @throws Exception
	 */
	protected boolean saveYqlj(String[] values) throws Exception {
		String sql = "insert into jyweb_yqljb(ljmc,ljwz,tpdz,xssx) values(?,?,?,?)";
		
		return dao.runUpdate(sql, values);
	}
	
	
	/**
	 * �������ӵ���ɾ��
	 * @param values
	 * @return
	 * @throws Exception
	 */
	protected boolean delYqlj(String pkValue) throws Exception {
		String sql = "delete from jyweb_yqljb where ljbh=?";
		
		return dao.runUpdate(sql, new String[] {pkValue});
	}
	
	
	/**
	 * ��ȡ����ͼƬ����·��
	 * @param pkValues
	 * @return
	 * @throws SQLException
	 */
	protected String[] getTps(String[] pkValues) throws SQLException {
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("select * from jyweb_yqljb where ljbh in (");
		 for (int i = 0 ; i<pkValues.length;i++) {
			 sb.append("'");
			 sb.append(pkValues[i]);
			 sb.append("'");
			 
			 if (i==pkValues.length-1) {
				 sb.append(")");
			 } else {
				 sb.append(",");
			 }
			 
		 }
		 
		 return dao.getArray(sb.toString(), new String[] {}, "tpdz");
		 
	}
	
	
	/**
	 * ��ȡ���е����ͨ����˾����
	 * @return
	 */
	public List<HashMap<String, String>> getAllGsmc(){
		String sql = "select dwmc from jygl_dwxxb where shzt='ͨ��'";
		return dao.getList(sql, new String[]{}, new String[]{"dwmc"});
	}
	
	
	/**
	 * ��Ƹ�����������1 
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	protected boolean setZphLlcs(String pkValue) throws Exception {
		String sql = "update jyweb_xyzph set llcs=llcs+1 where zphbt||fbr||fbsj=?";
		
		return dao.runUpdate(sql, new String[] {pkValue});
	}
	
	
	/**
	 * ѧ����λ�ղء������Ͷ�ݼ���
	 * @param pkValue
	 * @param xh
	 * @return
	 * @throws Exception
	 */
	public boolean saveStuJobs(String pkValue,String xh,String joblb) throws Exception {
		StringBuilder sql = new StringBuilder();
		
		sql.append("insert into jyweb_stujobs (xh,dwmc,gwmc,gwfbsj,joblb) select '");
		sql.append(xh);
		sql.append("',gsmc,zpzw,fbsj,'");
		sql.append(joblb);
		sql.append("' from jygl_zpxxb where zpzw||gsmc||fbsj='");
		sql.append(pkValue);
		sql.append("'");
		
		return dao.runUpdate(sql.toString(), new String[] {});
	}
	
	
	/**
	 * ɾ��������ĸ�λ
	 * @param pkValue
	 * @param xh
	 * @return
	 * @throws Exception
	 */
	public boolean deleteStuJobs(String pkValue,String xh) throws Exception {
		StringBuilder sql = new StringBuilder();
		
		sql.append("delete from jyweb_stujobs where xh||dwmc||gwmc||joblb||tdrq ");
		sql.append("= ?||(select gsmc from jygl_zpxxb where zpzw||gsmc||fbsj=?)");
		sql.append("|| (select zpzw from jygl_zpxxb where zpzw||gsmc||fbsj=?)");
		sql.append("||'���'||(select to_char(sysdate,'yyyymmdd') from dual)");

		return dao.runUpdate(sql.toString(), new String[] {xh,pkValue,pkValue});
	}
	
	
	/**
	 * ��ȡѧ������ ����
	 * @param xh
	 * @return
	 */
	public String getStuResume(String xh) {
		String sql = "select count(*) count from jygl_jytjb where xh=?";
		
		return dao.getOneRs(sql, new String[] {xh}, "count");
	}
	
	
	/**
	 * �����û����ͻ�ȡ�����˵�
	 * @param userType
	 * @return
	 */
	public List<HashMap<String,String>> getMenus(String userType){
		StringBuilder sql = new StringBuilder();
		
		sql.append("select * from (");
		sql.append("select a.dm,b.mc,b.mkly,a.yhqx,to_number(b.xssx) xssx ");
		sql.append("from dmk_jyweb_yhqxb a left join dmk_jyweb_gnmkdmb b ");
		sql.append("on a.dm=b.dm where a.dm like 'J____' and userType=?");
		sql.append(") order by xssx asc");
		
		return dao.getList(sql.toString(), new String[] {userType}, new String[] {"dm","mc","mkly","yhqx","xssx"});
	}
	
	
	/**
	 * ��ȡ������ȫ����
	 * @param tableName
	 * @return
	 */
	protected String[] getColumn(String tableName) {
		String sql = "select * from "+tableName;
		
		return dao.getColumnName(sql);
	}
	
	
	/**
	 * ����û������ߵ�λ�����Ƿ����
	 * @param fields
	 * @param value
	 * @return
	 */
	public boolean isUserExists(String fields, String value){
		String sql = "select count(*) count from jygl_dwxxb where " + fields + "=?";
		String[] rs = dao.getOneRs(sql, new String[]{value}, new String[]{"count"});

		int count = Integer.parseInt(rs[0]);
		boolean flag = false;
		
		if(count>0){
			flag = true;
		}
		
		return flag;
	}
	
	
	/**
	 * ��λ���ͳ��
	 * @param form
	 * @return
	 */
	public List<String[]> getDwtj(JyglForm form){
		
		StringBuilder sql = new StringBuilder();
		Pages pages = form.getPages();
		
		sql.append("select * from (");
		sql.append("select a.* ,rownum r from (");
		sql.append("select dwxzmc,hyflmc,sum(dwzc) dwzc,sum(xxzc) xxzc,sum(dwzc+xxzc) zzc from (");
		sql.append("select dwxzmc,hyflmc,");
		sql.append(" case when dwlx='��λ' then sum(count) else 0 end dwzc,");
		sql.append(" case when dwlx='ѧУ' then sum(count) else 0 end xxzc ");
		sql.append("from (");
		sql.append("select dwxzmc,hyflmc,dwlx,count(*) count from view_jygl_dwxxb where shzt='ͨ��'");
		
		if (!Base.isNull(form.getDwxz())) {
			sql.append(" and dwxz='");
			sql.append(form.getDwxz());
			sql.append("' ");
		}
		
		if (!Base.isNull(form.getHyfl())) {
			sql.append(" and hyfl='");
			sql.append(form.getHyfl());
			sql.append("' ");
		}
		
		if (!Base.isNull(form.getQuerygreaterequal_zcsj())) {
			sql.append(" and zcsj>='");
			sql.append(form.getQuerygreaterequal_zcsj());
			sql.append("' ");
		}
		
		if (!Base.isNull(form.getQuerylessequal_zcsj())) {
			sql.append("and zcsj<='");
			sql.append(form.getQuerylessequal_zcsj());
			sql.append("' ");
		}
		
		
		sql.append(" group by dwxzmc,hyflmc,dwlx ");
		sql.append(") group by dwlx,dwxzmc,hyflmc");
		sql.append(") group by dwxzmc,hyflmc");
		sql.append(") a");
		sql.append(") where r >  ");
		sql.append(pages.getStart());
		sql.append(" and r <= ");
		sql.append((pages.getStart() + pages.getPageSize()));
		
		List<String[]> result = dao.rsToVator(sql.toString(), new String[] {},
				new String[] { "dwxzmc", "hyflmc", "dwzc", "xxzc", "zzc" });
		pages.setMaxRecord(result.size());
		return result;
		
	}
	
	
	/**
	 * ��Ƹ��Ϣ���ͳ��
	 * @param form
	 * @return
	 */
	public List<String[]> getZpxxTj(JyglForm form){
		StringBuilder sql = new StringBuilder();
		
		sql.append("select sum(dwfbs) dwfbs,sum(xxfbs) xxfbs,sum(fbzs) fbzs,");
		sql.append("sum(btjls) btjls,sum(blls) blls,sum(bscs) bscs,sum(hfjls) hfjls from(");
		sql.append("select a.gsmc,a.dwfbs,a.xxfbs,a.fbzs,b.btjls,b.blls,b.bscs,b.hfjls ");
		sql.append("from (select gsmc,sum(dwfbs) dwfbs,sum(xxfbs) xxfbs,sum(dwfbs+xxfbs) fbzs ");
		sql.append("from(select gsmc,");
		sql.append("case when fblx='��λ' then sum(count) else 0 end dwfbs,");
		sql.append("case when fblx='ѧУ' then sum(count) else 0 end xxfbs ");
		sql.append("from (");
		sql.append("select gsmc,fblx,count(*) count from  jygl_zpxxb a where xxsh='ͨ��' ");
		
		if (!Base.isNull(form.getQuerygreaterequal_fbsj())) {
			sql.append(" and fbsj>='");
			sql.append(form.getQuerygreaterequal_fbsj());
			sql.append("' ");
		}
		
		if (!Base.isNull(form.getQuerylessequal_fbsj())) {
			sql.append("and fbsj<='");
			sql.append(form.getQuerylessequal_fbsj());
			sql.append("' ");
		}
		
		if (!Base.isNull(form.getDwxz())) {
			sql.append(" and exists (select 1 from jygl_dwxxb b where a.gsmc=b.dwmc and dwxz='");
			sql.append(form.getDwxz());
			sql.append("')");
		}
		
		if (!Base.isNull(form.getHyfl())) {
			sql.append(" and exists (select 1 from jygl_dwxxb b where a.gsmc=b.dwmc and hyfl='");
			sql.append(form.getHyfl());
			sql.append("')");
		}
		
		sql.append("group by gsmc,fblx) group by gsmc,fblx");
		sql.append(") group by gsmc) a  ");
		sql.append("left join ");
		sql.append("(select dwmc,sum(btjls) btjls,sum(blls) blls,sum(bscs) bscs,");
		sql.append("(select count(*) from jyweb_zpxxtjb where dwmc=a.dwmc and czlx='�ظ�') hfjls ");
		sql.append("from (select dwmc,");
		sql.append("case when joblb='����' then sum(count) else 0 end btjls,");
		sql.append("case when joblb='���' then sum(count) else 0 end blls,");
		sql.append("case when joblb='�ղ�' then sum(count) else 0 end bscs ");
		sql.append("from (");
		sql.append("select dwmc,joblb,count(*) count from jyweb_stujobs group by dwmc,joblb");
		sql.append(") a group by dwmc,joblb");
		sql.append(") a group by dwmc");
		sql.append(") b on a.gsmc=b.dwmc)");
		
		return dao.rsToVator(sql.toString(), new String[] {}, new String[] {
				"dwfbs", "xxfbs", "fbzs", "btjls", "blls", "bscs", "hfjls" });
	}
	
	
	/**
	 * ѧ�����ͳ�Ƶ���SQL
	 * @param from
	 * @return
	 */
	private String getStuTjSQL(JyglForm form) {
		
		StringBuilder sb = new StringBuilder();
		
		StringBuilder querySql = new StringBuilder();
		StringBuilder sjSql = new StringBuilder();
		
		if (!Base.isNull(form.getQuerygreaterequal_tdrq())) {
			querySql.append(" and tdrq>='");
			querySql.append(form.getQuerygreaterequal_tdrq());
			querySql.append("' ");
			
			sjSql.append(" and sj>='");
			sjSql.append(form.getQuerygreaterequal_tdrq());
			sjSql.append("' ");
		}
		
		if (!Base.isNull(form.getQuerylessequal_tdrq())) {
			querySql.append("and tdrq<='");
			querySql.append(form.getQuerylessequal_tdrq());
			querySql.append("' ");
			
			sjSql.append("and sj<='");
			sjSql.append(form.getQuerylessequal_tdrq());
			sjSql.append("' ");
		}
		
		sb.append("select a.*,nvl(b.bfws,0) bfws,nvl(b.bscs,0) bscs,nvl(b.bhfs,0) bhfs ");
		sb.append("from(select a.xh ,b.nj,b.xydm,b.zydm,b.bjdm,b.xymc,b.zymc,b.bjmc,");
		sb.append("(select count(*) from jyweb_stujobs where xh=a.xh and joblb='���' ");
		sb.append(querySql);
		sb.append(")  llgs,");
		sb.append("(select count(*) from jyweb_stujobs where xh=a.xh and joblb='�ղ�' ");
		sb.append(querySql);
		sb.append(")  scgs,");
		sb.append("(select count(*) from jyweb_stujobs where xh=a.xh and joblb='����' ");
		sb.append(querySql);
		sb.append(")  tdgs ");
		
		sb.append("from jygl_jytjb a left join view_xsjbxx b on a.xh=b.xh");
		sb.append(") a left join (select xh, sum(bfws) bfws,sum(bscs) bscs,sum(bhfs) bhfs ");
		sb.append("from (select xh,");
		sb.append("case when czlx='���' then sum(count) else 0 end bfws,");
		sb.append("case when czlx='�ղ�' then sum(count) else 0 end bscs,");
		sb.append("case when czlx='�ظ�' then sum(count) else 0 end bhfs ");
		sb.append("from (");
		sb.append("select xh,czlx,count(*) count from jyweb_zpxxtjb where 1=1");
		sb.append(sjSql);
		sb.append("group by xh,czlx)  group by xh,czlx");
		sb.append(") a group by xh");
		sb.append(") b on a.xh=b.xh ");
		
		return sb.toString();
	}
	
	
	/**
	 * ѧ�����ͳ��-��ѧԺͳ��
	 * @param form
	 * @return
	 */
	public List<String[]> getStuTjByXy(JyglForm form){
		
		String tjSQl = getStuTjSQL(form);
		StringBuilder sql = new StringBuilder();
		Pages pages = form.getPages();
		
		sql.append("select * from (");
		sql.append("select a.*,rownum r from (");
		sql.append("select xymc, count(*) count,sum(llgs) llgs,sum(scgs) scgs,");
		sql.append("sum(tdgs) tdgs,sum(bfws) bfws,sum(bscs) bscs,sum(bhfs) bhfs ");
		sql.append("from (");
		sql.append(tjSQl);
		sql.append("where 1=1 ");
		
		if (!Base.isNull(form.getNj())) {
			sql.append(" and nj='");
			sql.append(form.getNj());
			sql.append("' ");
		}
		
		if (!Base.isNull(form.getXydm())) {
			sql.append(" and xydm='");
			sql.append(form.getXydm());
			sql.append("' ");
		}
		sql.append(") group by xymc ) a");
		sql.append(") where r >  ");
		sql.append(pages.getStart());
		sql.append(" and r <= ");
		sql.append((pages.getStart() + pages.getPageSize()));
		
		
		
		List<String[]> result = dao.rsToVator(sql.toString(), new String[] {},
				new String[] { "xymc", "count", "bfws","tdgs", "llgs", "scgs", 
						 "bhfs" });
		pages.setMaxRecord(result.size());
		return result;
	}
	
	
	/**
	 * ѧ�����ͳ��-��רҵͳ��
	 * @param form
	 * @return
	 */
	public List<String[]> getStuTjByZy(JyglForm form){
		
		String tjSQl = getStuTjSQL(form);
		StringBuilder sql = new StringBuilder();
		Pages pages = form.getPages();
		
		sql.append("select * from (");
		sql.append("select a.*,rownum r from (");
		sql.append("select xymc,zymc, count(*) count,sum(llgs) llgs,sum(scgs) scgs,");
		sql.append("sum(tdgs) tdgs,sum(bfws) bfws,sum(bscs) bscs,sum(bhfs) bhfs ");
		sql.append("from (");
		sql.append(tjSQl);
		sql.append("where 1=1 ");
		
		if (!Base.isNull(form.getNj())) {
			sql.append(" and nj='");
			sql.append(form.getNj());
			sql.append("' ");
		}
		
		if (!Base.isNull(form.getXydm())) {
			sql.append(" and xydm='");
			sql.append(form.getXydm());
			sql.append("' ");
		}
		
		if (!Base.isNull(form.getZydm())) {
			sql.append(" and xydm='");
			sql.append(form.getZydm());
			sql.append("' ");
		}
		
		sql.append(") group by xymc,zymc) a");
		sql.append(") where r >  ");
		sql.append(pages.getStart());
		sql.append(" and r <= ");
		sql.append((pages.getStart() + pages.getPageSize()));
		
		List<String[]> result = dao.rsToVator(sql.toString(), new String[] {},
				new String[] { "xymc", "zymc","count", "bfws","tdgs", "llgs", "scgs", 
						 "bhfs" });
		
		pages.setMaxRecord(result.size());
		return result;
	}
	
	
	/**
	 * ѧ�����ͳ��-���༶ͳ��
	 * @param form
	 * @return
	 */
	public List<String[]> getStuTjByBj(JyglForm form){
		
		String tjSQl = getStuTjSQL(form);
		StringBuilder sql = new StringBuilder();
		Pages pages = form.getPages();
		
		sql.append("select * from (");
		sql.append("select a.*,rownum r from (");
		sql.append("select xymc,zymc,bjmc, count(*) count,sum(llgs) llgs,sum(scgs) scgs,");
		sql.append("sum(tdgs) tdgs,sum(bfws) bfws,sum(bscs) bscs,sum(bhfs) bhfs ");
		sql.append("from (");
		sql.append(tjSQl);
		sql.append("where 1=1 ");
		
		if (!Base.isNull(form.getNj())) {
			sql.append(" and nj='");
			sql.append(form.getNj());
			sql.append("' ");
		}
		
		if (!Base.isNull(form.getXydm())) {
			sql.append(" and xydm='");
			sql.append(form.getXydm());
			sql.append("' ");
		}
		
		if (!Base.isNull(form.getZydm())) {
			sql.append(" and xydm='");
			sql.append(form.getZydm());
			sql.append("' ");
		}
		
		if (!Base.isNull(form.getBjdm())) {
			sql.append(" and xydm='");
			sql.append(form.getBjdm());
			sql.append("' ");
		}
		
		sql.append(") group by xymc,zymc,bjmc) a");
		sql.append(") where r >  ");
		sql.append(pages.getStart());
		sql.append(" and r <= ");
		sql.append((pages.getStart() + pages.getPageSize()));
		
		List<String[]> result = dao.rsToVator(sql.toString(), new String[] {}, new String[] {
				"xymc", "zymc", "bjmc", "count", "bfws", "tdgs", "llgs",
				"scgs", "bhfs" });
		
		pages.setMaxRecord(result.size());
		return result;
	}
	
	
	/**
	 * ѧ�����ͳ��-��ѧ��ͳ��
	 * @param form
	 * @return
	 */
	public List<String[]> getStuTjByStu(JyglForm form){
		
		String tjSQl = getStuTjSQL(form);
		StringBuilder sql = new StringBuilder();
		Pages pages = form.getPages();
		
		sql.append("select * from (");
		sql.append("select a.*,rownum r from (");
		sql.append("select a.*,(select xm from xsxxb where xh=a.xh) xm,");
		sql.append("(select sfzh from xsxxb where xh=a.xh) sfzh ");
		sql.append("from (");
		sql.append(tjSQl);
		sql.append(") a where 1=1 ");
		
		if (!Base.isNull(form.getNj())) {
			sql.append(" and nj='");
			sql.append(form.getNj());
			sql.append("' ");
		}
		
		if (!Base.isNull(form.getXydm())) {
			sql.append(" and xydm='");
			sql.append(form.getXydm());
			sql.append("' ");
		}
		
		if (!Base.isNull(form.getZydm())) {
			sql.append(" and zydm='");
			sql.append(form.getZydm());
			sql.append("' ");
		}
		
		if (!Base.isNull(form.getBjdm())) {
			sql.append(" and bjdm='");
			sql.append(form.getBjdm());
			sql.append("' ");
		}
		
		
		sql.append(") a where 1=1");
		
		if (!Base.isNull(form.getXm())) {
			sql.append(" and xm like'%");
			sql.append(form.getXm());
			sql.append("%' ");
		}
		
		if (!Base.isNull(form.getSfzh())) {
			sql.append(" and sfzh like'%");
			sql.append(form.getSfzh());
			sql.append("%' ");
		}
		
		sql.append(") where r >  ");
		sql.append(pages.getStart());
		sql.append(" and r <= ");
		sql.append((pages.getStart() + pages.getPageSize()));
		
		List<String[]> result = dao.rsToVator(sql.toString(), new String[] {}, new String[] {
				"xymc", "zymc", "bjmc","xm","sfzh","bfws","tdgs","llgs",
				"scgs", "bhfs" });
		
		pages.setMaxRecord(result.size());
		return result;
	}
	
	
	/**
	 * �����ظ�---DWR���õ�
	 * @param pkValues
	 * @param hfnr
	 * @return
	 * @throws SQLException
	 */
	public boolean saveHfxxMore(String[] pkValues ,String hfnr) throws SQLException {
		
		String[] sqlArr = new String[pkValues.length];
		
		for (int i = 0 ; i<pkValues.length;i++) {
			StringBuilder sql = new StringBuilder();
			sql.append("insert into jyweb_zpxxtjb (xh,dwmc,gwmc,gwfbsj,czlx,hfxx) select xh,dwmc,gwmc,gwfbsj,'�ظ�','");
			sql.append(hfnr);
			sql.append("' from view_jyweb_stujobs where xh||dwmc||gwmc||joblb||tdrq||gwfbsj='");
			sql.append(pkValues[i]);
			sql.append("'");
			
			sqlArr[i] = sql.toString();
		}
		
		int[] result = dao.runBatch(sqlArr);
		
		return dao.checkBatch(result);
	}
	
	
	/**
	 * ��������
	 */
	public boolean saveNews(JyglForm form) {

		String sql = "insert into jygl_zcwjb(wjbt,wjlx,wjnr,fbr,fbsj,tplj) values (?,?,?,?,?,?)";

		try {
			return dao.runUpdate(sql, new String[] { form.getWjbt(),
					form.getWjlx(), form.getWjnr(), form.getFbr(),
					form.getFbsj(), form.getFileName() });
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
	/**
	 * �޸�����
	 * @param form
	 * @param pkValue
	 * @return
	 */
	public boolean updateNews(JyglForm form,String pkValue) {

		String sql = "update jygl_zcwjb set wjbt=?,wjlx=?,wjnr=?,fbr=?,fbsj=?,tplj=?  where wjbt=? ";

		try {
			return dao.runUpdate(sql, new String[] { form.getWjbt(),
					form.getWjlx(), form.getWjnr(), form.getFbr(),
					form.getFbsj(), form.getFileName(),pkValue});
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}



	/**
	 * ��λ�б���λ��Ϣ��ʹ�ã�
	 * @param model
	 * @return
	 */
	public List<HashMap<String,String>> getDwList(JyglForm model){
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select distinct yhm,gsmc from(");
		sql.append("select * from view_jygl_zpxx  where xxsh='ͨ��' and yxqx >= to_number(to_char(sysdate,'yyyymmdd'))");
		if (!Base.isNull(model.getZpzw())){
			sql.append(" and zpzw like '%")
			   .append(model.getZpzw())
			   .append("%'");
		}
		sql.append(")");
		
		if (!Base.isNull(model.getDwmc())){
			sql.append("where gsmc like '%")
			   .append(model.getDwmc())
			   .append("%'");
		}
		
		return dao.getList(sql.toString(), new String[]{}, new String[]{"yhm","gsmc"});
	}

	
	
	/**
	 * ��λ�б���λ��Ϣ��ʹ�ã�
	 * @param model
	 * @return
	 */
	public List<HashMap<String,String>> getGwList(JyglForm model){
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select * from view_jygl_zpxx where xxsh='ͨ��' and yxqx >= to_number(to_char(sysdate,'yyyymmdd')) ");
		
		if (!Base.isNull(model.getZpzw())){
			sql.append(" and zpzwmc like '%")
			   .append(model.getZpzw())
			   .append("%'");
		}
		
		return dao.getList(sql.toString(), new String[]{}, new String[]{"gsmc","zpzw","fbsj","zpzwmc"});
	}
	
	
	
	/**
	 * ����ר����Ƹ��λ����λ������Ϣ
	 * @param id
	 * @param dwmc
	 * @param gwmc
	 * @return
	 * @throws SQLException
	 */
	public boolean saveZtzp(String id,String[] dwmc,List<String> gwmc) throws SQLException{
		
		String[] sqlArr = new String[dwmc.length+gwmc.size()+2];
		
		StringBuilder delDwmc = new StringBuilder();
		StringBuilder delGwmc = new StringBuilder();
		
		delDwmc.append("delete from xg_jyweb_ztzpgsb where ztid='").append(id).append("'");
		delGwmc.append("delete from xg_jyweb_ztzpgwb where ztid='").append(id).append("'");
		
		sqlArr[0] = delDwmc.toString();
		sqlArr[1] = delGwmc.toString();
		
		int n = 2 ;
		for (int i = 0 ; i < dwmc.length ; i++){
			StringBuilder sql = new StringBuilder();
			
			sql.append("insert into xg_jyweb_ztzpgsb values ('")
			   .append(id)
			   .append("','")
			   .append(dwmc[i])
			   .append("')");
			sqlArr[n] = sql.toString();
			n++;
		}
		
		for (int i = 0 ; i < gwmc.size() ; i++){
			StringBuilder sql = new StringBuilder();
			
			sql.append("insert into xg_jyweb_ztzpgwb values ('")
			   .append(id)
			   .append("','")
			   .append(gwmc.get(i))
			   .append("')");
			sqlArr[n++] = sql.toString();
		}
		
		int[] result = dao.runBatch(sqlArr);
		return dao.checkBatch(result);
	}

	
	/**
	 * ������Ƹ��λ�б�
	 * @param pkValue
	 * @return
	 */
	public List<HashMap<String,String>> getZtzpGwList(String pkValue){
		
		String sql = "select a.* from view_jygl_zpxx a where exists (select 1 from xg_jyweb_ztzpgwb b where b.gwid=a.zpzw||a.gsmc||a.fbsj and b.ztid=?)";
		
		return dao.getList(sql, new String[]{pkValue}, new String[]{"yhm","gsmc","zpzw","fbsj","zpzwmc"});
	}
	
	
	/**
	 * ������Ƹ���������1
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public boolean setLlcs(String pkValue) throws Exception{
		String sql = "update xg_jyweb_ztzpb set llcs=llcs+1 where id=?";
		return dao.runUpdate(sql, new String[]{pkValue});
	}
	
	
	
	/**
	 * ��ȡ������Ƹ�б���DWR���ã�
	 * @param zpsj
	 * @return
	 */
	public List<HashMap<String,String>> getZtzpList(){
		String sql = "select id,zpzt,to_char(to_date(zpsj,'yyyymmdd'),'yyyy-mm-dd') zpsj,to_char(to_date(fbsj,'yyyymmdd'),'yyyy-mm-dd') fbsj from xg_jyweb_ztzpb where shzt='ͨ��' order by zpsj desc";
		
		return dao.getList(sql, new String[]{}, new String[]{"id","zpzt","zpsj","fbsj"});
	}
	
	
	
	/**
	 * ɾ����������Ƹ��������
	 * @return
	 * @throws Exception 
	 */
	public boolean delZtzp(String[] pkValues) throws Exception {
		
		StringBuilder dwSql = new StringBuilder();
		StringBuilder gwSql = new StringBuilder();
		
		dwSql.append("delete from xg_jyweb_ztzpgsb where ztid in (");
		gwSql.append("delete from xg_jyweb_ztzpgwb where ztid in (");
		
		for (int i = 0 ; i < pkValues.length ; i++) {
			
			dwSql.append("?");
			gwSql.append("?");
			
			if (i != pkValues.length-1) {
				dwSql.append(",");
				gwSql.append(",");
			} else {
				dwSql.append(")");
				gwSql.append(")");
			}
		}
		
		return dao.runUpdate(dwSql.toString(), pkValues) 
			 && dao.runUpdate(gwSql.toString(), pkValues);
	}
	
	
	
	
	/**
	 * ���ݵ�λ���ƻ�ȡ�û���
	 * @param dwmc
	 * @return
	 */
	public String getYhm(String dwmc) {
		
		String sql = "select yhm from jygl_dwxxb where dwmc=?";
		
		return dao.getOneRs(sql, new String[] {dwmc}, "yhm");
	}

	
	
	/**
	 * ��Ƹ����
	 * @return
	 */
	public List<HashMap<String,String>> getZplxList(){
		String sql = "select * from xg_jyweb_zplxb order by xssx asc";
		
		return dao.getList(sql, new String[] {}, new String[] {"dm","mc"});
	}
	
	
	
	/**
	 * �޸ĵ�λ�ظ����Ƿ�ɾ��״̬
	 * @param pkValues
	 * @return
	 * @throws SQLException
	 */
	public boolean updateSfsc(String[] pkValues) throws SQLException {
		String[] sqlArr = new String[pkValues.length];
		
		for (int i = 0 ; i < pkValues.length ; i++) {
			StringBuilder sql = new StringBuilder();
			
			sql.append("update jyweb_zpxxtjb set sfsc='��'  where xh||dwmc||gwmc||sj||gwfbsj=");
			sql.append("(select xh||dwmc||gwmc||tdrq||gwfbsj from ");
			sql.append("jyweb_stujobs where xh||dwmc||gwmc||joblb||tdrq||gwfbsj='");
			sql.append(pkValues[i]);
			sql.append("')");
			
			sqlArr[i] = sql.toString();
		}
		
		int[] result = dao.runBatch(sqlArr);
		return dao.checkBatch(result);
	}


	
	/**
	 * ���Ľ��ܲ�ѯ
	 * @param lx
	 * @return
	 */
	public String getZxjs(String lx){
		
		String sql = "select jsnr from xg_jyweb_zxjs where lx = ?";
		
		return dao.getOneRs(sql, new String[]{lx}, "jsnr");
	}
	
	
	
	/**
	 * ��ȡѧ��������ղع��ĸ�λ��¼
	 * @param xh
	 * @param type
	 * @param pkValue
	 * @return
	 */
	public List<HashMap<String,String>> getStuJobs(String xh ,String type ,String pkValue){
		
		String sql = "select * from jyweb_stujobs where xh = ? and joblb = ? and gwmc||dwmc||gwfbsj= ? ";
		
		return dao.getList(sql, new String[]{xh,type,pkValue}, new String[]{"xh","dwmc","gwmc"});
	}
	
	
}
