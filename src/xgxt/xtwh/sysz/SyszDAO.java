package xgxt.xtwh.sysz;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.form.User;
import xgxt.pjpy.comm.pjpy.PjxtszModel;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.MakeQuery;
import xgxt.xtwh.XtwhDAO;

import common.Globals;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ϵͳά��_��ҳ����_DAO��
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author ΰ�����
 * @version 1.0
 */

public class SyszDAO extends XtwhDAO {

	/**
	 * ��õ���id
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	public String getDcid(SyszForm model, User user) {

		DAO dao = DAO.getInstance();

		String sql = "select dcid from (select t.dcid from "
				+ "xg_xtwh_sydcb t order by t.dcsj desc) where rownum=1 ";

		String dcid = dao.getOneRs(sql, new String[] {}, "dcid");

		return dcid;

	}

	/**
	 * ������������Ϊ�ر�
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	public Boolean setOtherDcClose(SyszForm model, User user) throws Exception {

		DAO dao = DAO.getInstance();

		// ����ID
		String dcid = model.getDcid();

		String sql = "update xg_xtwh_sydcb set sfqy = '��' where dcid <> ? ";

		boolean flag = dao.runUpdate(sql, new String[] { dcid });

		return flag;

	}
	
	/**
	 * ������ҳ�����Ƿ�����
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	public Boolean saveSydcSfqy(SyszForm model, User user) throws Exception {

		DAO dao = DAO.getInstance();

		// ����ID
		String dcid = model.getDcid();

		String[] sql = new String[2];
		
		sql[0] = "update xg_xtwh_sydcb set sfqy = '��' ";
		sql[1] = "update xg_xtwh_sydcb set sfqy = '��' where dcid = '"+dcid+"' ";
		
		boolean flag = dao.saveArrDate(sql, user);

		return flag;

	}

	/**
	 * �����ҳ����ͳ���б�
	 * @author ΰ�����
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getSydcTjList(SyszForm model, User user) {

		DAO dao = DAO.getInstance();

		// ����ID
		String dcid = model.getDcid();

		StringBuilder sql = new StringBuilder();
		sql.append(" select a.xxid,");
		sql.append(" (substr(a.xxnr,0,10)||case when length(a.xxnr)>10 then '...' end) xxinfo, ");
		sql.append(" a.xxnr, nvl(b.num, 0) num ");
		sql.append(" from (select xxid, xxnr from xg_xtwh_sydcxxb a ");
		sql.append(" where dcid = ? order by xxid) a ");
		sql.append(" left join (select xxid, count(1) num ");
		sql.append(" from xg_xtwh_sydcjgb b where b.dcid = ? ");
		sql.append(" group by xxid) b on a.xxid = b.xxid ");

		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] { dcid, dcid }, new String[] { "xxid", "xxnr","xxinfo",
						"num" });

		return list;
	}

	// =======================����made by ΰ�����=====================
	
	// =======================����made by ��������=====================
	
	/**
	 * ��ȡѧ��������Ϣ
	 * ����MKLX����
	 * author qlj
	 */
	public List<String[]> getXssqInfo(SyszForm myForm) throws Exception {
		
		DzdxSyszDAO dzdxDAO=new DzdxSyszDAO();
		
		List<String[]>dbsxList=new ArrayList<String[]>();
		User user=new User();
		user.setUserName(myForm.getUserName());
		user.setUserType(myForm.getUserType());
		user.setUserDep(myForm.getUserDep());
		user.setFdyQx(((Boolean)myForm.getFdyQx()).toString());
		user.setBzrQx(((Boolean)myForm.getBzrQx()).toString());
		
		String xxdm=Base.xxdm;
		
		//�ش󵥶�����
		if("ѧ������".equalsIgnoreCase(myForm.getMklx())
				&& Globals.XXDM_ZGDZDX.equalsIgnoreCase(xxdm)){
			//�й����ʴ�ѧ ����
			myForm.setGnmklx("zz");
			dbsxList.addAll(dzdxDAO.getXszzsqInfo(myForm));
		}else if("��������".equalsIgnoreCase(myForm.getMklx())
				&& Globals.XXDM_ZGDZDX.equalsIgnoreCase(xxdm)){
			//�й����ʴ�ѧ ����
			myForm.setGnmklx("pj");
			dbsxList.addAll(dzdxDAO.getXszzsqInfo(myForm));
		
		}else if("ѧ������".equalsIgnoreCase(myForm.getMklx())){
			//ѧ������ ѧ������(ͨ��)
			dbsxList.addAll(getXszzsqInfo(myForm));
		}else if("��������".equalsIgnoreCase(myForm.getMklx())){
			//ѧ������ ѧ������(ͨ��)
			dbsxList.addAll(getXspjsqInfo(myForm));
		}else if("Υ�ʹ���".equalsIgnoreCase(myForm.getMklx())){
			//Υ����Ϣ ѧ��(ͨ��)
			dbsxList.addAll(getXswjxxInfo(myForm));
		}
		
		
		return dbsxList;
		
	}
	
	
	/**
	 * ��ȡѧ��������Ϣ
	 * (��ʱֻ��ѧ��������Ϣ,����ģ��ȴ����)
	 * author ��������
	 * @param myForm
	 * @return List<String[]>
	 * @throws Exception
	 */
	public List<String[]> getXszzsqInfo(SyszForm myForm) throws Exception {

		StringBuilder sb = new StringBuilder();

		// ===========================ѧ������ ������====================================
		sb.append(" select * from ( select rownum r, b.xmdm,('��ѧ��������'||b.xmmc)xmmc, ");
		
		// ===========================����ʱ��==========================
		sb.append("(case when xn is null and xq is null and nd is null then sqsj");
		sb.append(" when xn is null and xq is null and nd is not null then nd||'���'");
		sb.append(" when nd is null and xq is null and xn is not null then xn||'ѧ��'");
		sb.append(" when nd is null and xq is not null and xn is not null then xn||'ѧ��,");
		sb.append(" '||(select xqmc from xqdzb where xqdm=xq)||'ѧ��' end )sqzqxx,");
		
		// ===========================������===========================
		sb.append(" (case when shjb='�������' and  shzt3='ͨ��' then '�������ͨ��' ");
		sb.append(" when shjb='�������' and shzt3='��ͨ��' then '������˲�ͨ��' ");
		sb.append(" when shjb='�������' and shzt2='ͨ��' then '�������ͨ��' ");
		sb.append(" when shjb='�������' and shzt2='��ͨ��' then '�������ͨ��' ");
		sb.append(" when shjb='�������' and shzt1='ͨ��' then 'һ�����ͨ��' ");
		sb.append(" when shjb='�������' and shzt1='��ͨ��' then 'һ����˲�ͨ��' ");
		sb.append(" when shjb='�������' and shzt1='δ���' then 'δ���'     ");

		sb.append(" when shjb = '�������' and shzt2 = 'ͨ��' then '�������ͨ��' ");
		sb.append(" when shjb = '�������' and shzt2 = '��ͨ��' then '�������ͨ��' ");
		sb.append(" when shjb = '�������' and shzt1 = 'ͨ��' then 'һ�����ͨ��' ");
		sb.append(" when shjb = '�������' and shzt1 = '��ͨ��' then  'һ����˲�ͨ��' ");
		sb.append(" when shjb = '�������' and shzt1 = 'δ���' then 'δ���' ");

		sb.append(" when shjb = 'һ�����' and shzt1 = 'ͨ��' then  'һ�����ͨ��' ");
		sb.append(" when shjb = 'һ�����' and shzt1 = '��ͨ��' then  'һ����˲�ͨ��' ");
		sb.append(" when shjb = 'һ�����' and shzt1 = 'δ���' then  'δ���' ");
		sb.append(" when shjb = '�������' then '������' end)shjg");
		sb.append(" from xszz_shztb a,xszz_zzxmb b where a.xmdm=b.xmdm and  xh='"
						+ myForm.getUserName() + "' ");
		if (!"".equalsIgnoreCase(myForm.getXn()) && null != myForm.getXn()) {
			sb.append(" and xn='" + myForm.getXn() + "' ");
		}
		if (!"".equalsIgnoreCase(myForm.getXq()) && null != myForm.getXq()) {
			sb.append(" and xq='" + myForm.getXq() + "'");
		}
		if (!"".equalsIgnoreCase(myForm.getNd()) && null != myForm.getNd()) {
			sb.append(" and nd='" + myForm.getNd() + "'");
		}
		if (!"".equalsIgnoreCase(myForm.getXmdm()) && null != myForm.getXmdm()) {
			sb.append(" and a.xmdm='" + myForm.getXmdm() + "'");
		}
		sb.append(" )order by xmdm ");
		// ===========================ѧ������ ������end====================================
	
		return CommonQueryDAO.commonQuery(sb.toString(), "", new String[] {},
				new String[] { "xmmc", "sqzqxx", "shjg" }, myForm);
	}

	/**
	 * ����������Ϣ(��ʦ)
     * author ��������
     * (��ʱֻ��ѧ��������Ϣ,����ģ��ȴ����)
	 * @param myForm
	 * @return List<String[]>
	 * @throws Exception
	 */
	public List<String[]> getDbsxInfo(SyszForm myForm) throws Exception {
		
		DzdxSyszDAO dzdxDAO=new DzdxSyszDAO();
		SyszService service=new SyszService();
		List<String[]>dbsxList=new ArrayList<String[]>();
		User user=new User();
		user.setUserName(myForm.getUserName());
		user.setUserType(myForm.getUserType());
		user.setUserDep(myForm.getUserDep());
		user.setFdyQx(((Boolean)myForm.getFdyQx()).toString());
		user.setBzrQx(((Boolean)myForm.getBzrQx()).toString());
		
		String xxdm=Base.xxdm;
		
		if("ѧ������".equalsIgnoreCase(myForm.getMklx())
				&& Globals.XXDM_ZGDZDX.equalsIgnoreCase(xxdm)){
			//�й����ʴ�ѧ ����
			myForm.setGnmklx("zz");
			dbsxList.addAll(dzdxDAO.getZzpjDbsx(myForm));
		}else if("��������".equalsIgnoreCase(myForm.getMklx())
				&& Globals.XXDM_ZGDZDX.equalsIgnoreCase(xxdm)){
			//�й����ʴ�ѧ ����
			myForm.setGnmklx("pj");
			dbsxList.addAll(dzdxDAO.getZzpjDbsx(myForm));
		}else if("ѧ������".equalsIgnoreCase(myForm.getMklx())){
			//ѧ������ ��������(ͨ��)
			dbsxList.addAll(getXszzDbsx(myForm));
		}else if("�ļ�����".equalsIgnoreCase(myForm.getMklx())){
			//�ļ����� ��������(ͨ��)
			dbsxList.addAll(getGwglDbsx(myForm,user));
		}else if("��������".equalsIgnoreCase(myForm.getMklx())){
			//ѧ������ ��������(ͨ��)
			dbsxList.addAll(service.getPjpyDbsx(myForm));
		}
						
		return dbsxList;
	}
	
	/**
	 * ѧ����������������Ϣ(��ʦ)
     * author ��������
	 * @param myForm
	 * @return List<String[]>
	 * @throws Exception
	 */
	public List<String[]> getXszzDbsx(SyszForm myForm) throws Exception {
		StringBuilder sb = new StringBuilder();
		
		// ============��½��Ϣ================
		String yhm = myForm.getUserName();
		String type = myForm.getUserType();
		String bmdm = myForm.getUserDep();
		
		boolean fdyqx = myForm.getFdyQx();
		boolean bzrqx = myForm.getBzrQx();

		boolean isXy = false;
		
		//======================Ȩ�޿���============================
		if ("xy".equalsIgnoreCase(type)) {
			// ѧԺ�û�
			isXy = true;
		}
		
		boolean blog=false;
		if(isXy){
			blog=true;
			//һ�����
			sb.append(" select xmdm, xn,xq,nd,substr(sqsj,0,4)sqn from xszz_shztb a,view_xsjbxx c where xmdm in ");
			sb.append(" (select xmdm from xszz_zzxmb where shjb='һ�����' and xysh='��' ) ");
			sb.append(" and xysh='δ���'  and c.xydm='"+bmdm+"' ");
			//�������
			sb.append(" union all ");
			sb.append(" select xmdm, xn,xq,nd,substr(sqsj,0,4)sqn from xszz_shztb a,view_xsjbxx c where xmdm in ");
			sb.append(" (select xmdm from xszz_zzxmb where shjb='�������' and xysh='��' ) ");
			sb.append(" and xysh='δ���'  and a.xh=c.xh  and (fdysh='ͨ��' or bzrsh='ͨ��') ");
			sb.append(" and c.xydm='"+bmdm+"' ");
			//�������
			sb.append(" union all ");
			sb.append(" select xmdm, xn,xq,nd,substr(sqsj,0,4)sqn from xszz_shztb a,view_xsjbxx c where xmdm in ");
			sb.append(" (select xmdm from xszz_zzxmb where shjb='�������' and xysh='��' ) ");
			sb.append(" and xysh='δ���'  and a.xh=c.xh  and (fdysh='ͨ��' or bzrsh='ͨ��') ");
			sb.append(" and c.xydm='"+bmdm+"' ");
			
		}
		
		if(fdyqx ){
			if(blog){
				sb.append("union all");
			}
			//һ�����
			sb.append(" select xmdm, xn,xq,nd,substr(sqsj,0,4)sqn from xszz_shztb a,view_xsjbxx c where xmdm in ");	
			sb.append(" (select xmdm from xszz_zzxmb where shjb='һ�����' and fdysh='��' ) ");
			sb.append(" and fdysh='δ���' and a.xh=c.xh ");
			sb.append(" and exists(select 1 from fdybjb b where c.bjdm=b.bjdm and b.zgh='" + yhm + "')");
			blog=true;
		}
		
		if(bzrqx){
			if(blog){
				sb.append("union all");
			}
			//һ�����
			sb.append(" select xmdm, xn,xq,nd,substr(sqsj,0,4)sqn from xszz_shztb a,view_xsjbxx c  where xmdm in ");	
			sb.append(" (select xmdm from xszz_zzxmb where shjb='һ�����' and bzrsh='��' ) ");
			sb.append(" and bzrsh='δ���' and a.xh=c.xh ");
			sb.append("  and exists(select 1 from  bzrbbb b where c.bjdm=b.bjdm and b.zgh='" + yhm + "') ");
			blog=true;
		}
		
		if("admin".equalsIgnoreCase(type) || "xx".equalsIgnoreCase(type)){
			if(blog){
				sb.append("union all");
			}
			//һ�����
			sb.append(" select xmdm, xn,xq,nd,substr(sqsj,0,4)sqn from xszz_shztb where xmdm in ");	
			sb.append(" ( select xmdm from xszz_zzxmb where shjb='һ�����' and xxsh='��'  ) ");
			sb.append("  and xxsh='δ���' ");
			sb.append("union all");
			//�������
			sb.append(" select xmdm, xn,xq,nd,substr(sqsj,0,4)sqn from xszz_shztb b where xmdm in ");	
			sb.append(" (select xmdm from xszz_zzxmb where shjb='�������' and (bzrsh='��' and b.bzrsh='ͨ��' or xysh='��' and b.xysh='ͨ��' or  fdysh='��' and b.fdysh='ͨ��' ) )");
			sb.append("  and xxsh='δ���' ");
			sb.append("union all");
			//�������
			sb.append(" select xmdm, xn,xq,nd,substr(sqsj,0,4)sqn from xszz_shztb b where xmdm in ");	
			sb.append(" (select xmdm from xszz_zzxmb where shjb='�������' and xysh='��' and b.xysh='ͨ��'  ) ");
			sb.append("  and xxsh='δ���' ");
			
		}
		
		StringBuilder  sql=new StringBuilder();
		
	    sql.append("select r,xmdm,xsh,xn,xq,nd,sqn,xmmc,sqzq,case when sqn=dqnd then 'commXszz.do?method=xmshManage&widthType=dbsx&go=go&xmdm='||xmdm else '#' end ljdz from (");
		sql.append("select a.*,b.dqnd from (select rownum r, a.xmdm, a.xsh,a.xn,a.xq,a.nd,a.sqn,('��ѧ��������'||b.xmmc)xmmc,b.sqzq from( select xmdm,count(1) xsh, xn,xq,nd,sqn from ("+sb.toString()+") group by xmdm,xn,xq,nd,sqn)a,xszz_zzxmb b where a.xmdm=b.xmdm)a left join xtszb b on 1=1 )a");
		sql.append(" where 1=1 ");
		if(!"".equalsIgnoreCase(myForm.getXn()) && null!=myForm.getXn()){
			sql.append(" and xn='"+myForm.getXn()+"' ");
		}
		if(!"".equalsIgnoreCase(myForm.getXq()) && null!=myForm.getXq()){
			sql.append(" and xq='"+myForm.getXq()+"'");
		}
		if(!"".equalsIgnoreCase(myForm.getNd()) && null!=myForm.getNd()){
			sql.append(" and nd='"+myForm.getNd()+"'");
		}
		if(!"".equalsIgnoreCase(myForm.getXmdm()) && null!=myForm.getXmdm()){
			sql.append(" and a.xmdm='"+myForm.getXmdm()+"'");
		}
		
		
		return CommonQueryDAO.commonQuery(sql.toString(), "", new String[] {},
				new String[] { "xmmc", "xsh","ljdz"}, myForm);
	}
	
	/**
	 * ��ȡѧ��������Ŀ��Ϣ
	 * author ��������
     * (��ʱֻ��ѧ��������Ϣ,����ģ��ȴ����) 
	 * @param myForm
	 * @return List<String[]>
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getSqxxList(SyszForm myForm)
			throws Exception {

		List<HashMap<String, String>>sqxxlist=new ArrayList<HashMap<String, String>>();
		DzdxSyszDAO dzdxDAO=new DzdxSyszDAO();
		
		String xxdm=Base.xxdm;
		//ѧ������
		if(Globals.XXDM_ZGDZDX.equalsIgnoreCase(xxdm)){
			myForm.setGnmklx("zz");
			sqxxlist.addAll(dzdxDAO.getSqzzxxList(myForm));
			myForm.setGnmklx("pj");
			sqxxlist.addAll(dzdxDAO.getSqzzxxList(myForm));
		}else {
			sqxxlist.addAll(getSqzzxxList(myForm));
			sqxxlist.addAll(getSqpjxxList(myForm));
		}
		
		return sqxxlist;
	}
	
	/**
	 * ��ȡѧ������������Ŀ��Ϣ
	 * author ��������
     * (��ʱֻ��ѧ��������Ϣ,����ģ��ȴ����) 
	 * @param myForm
	 * @return List<String[]>
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getSqzzxxList(SyszForm myForm)
			throws Exception {

		StringBuilder sb = new StringBuilder();
		DAO dao = DAO.getInstance();
		
		sb.append("  select * from ( select  distinct(b.xmdm)xmdm,b.xmmc ,'xszz' mklx ");
		sb.append(" from xszz_shztb a,xszz_zzxmb b where a.xmdm=b.xmdm and  xh='"
						+ myForm.getUserName() + "') ");
		sb.append("order by xmdm  ");
		
		return dao.getList(sb.toString(), new String[] {}, new String[] {
				"mklx", "xmdm", "xmmc" });
	}
	
	/**
	 * ��ȡѧ������������Ŀ��Ϣ
	 * author qlj
	 * @param myForm
	 * @return List<String[]>
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getSqpjxxList(SyszForm myForm)
			throws Exception {

		StringBuilder sb = new StringBuilder();
		DAO dao = DAO.getInstance();
		
		sb.append("  select * from ( select  distinct(b.xmdm)xmdm,b.xmmc ,'xszz' mklx ");
		sb.append(" from xszz_shztb a,xszz_zzxmb b where a.xmdm=b.xmdm and  xh='"
						+ myForm.getUserName() + "') ");
		sb.append("order by xmdm  ");
		
		return dao.getList(sb.toString(), new String[] {}, new String[] {
				"mklx", "xmdm", "xmmc" });
	}
	
	
	/**
	 * ��ȡ����������Ŀ�б�	dbsxInfo.jsp(DWR)
     * (��ʱֻ��ѧ��������Ϣ,����ģ��ȴ����)  
	 * author ��������
	 * @param String yhm, String type,
			String bmdm, boolean fdyqx, boolean bzrqx, String mklx
	 * @return List<HashMap<String, String>>
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getDbsxXmList(SyszForm myForm)
			throws Exception {
		DzdxSyszDAO dzdxDAO=new DzdxSyszDAO();
		
		List<HashMap<String, String>>dbsxxmList=new ArrayList<HashMap<String, String>>();
		User user =new User();
		user.setUserName(myForm.getUserName());
		user.setUserType(myForm.getUserType());
		user.setUserDep(myForm.getUserDep());
		user.setFdyQx(((Boolean)myForm.getFdyQx()).toString());
		user.setBzrQx(((Boolean)myForm.getBzrQx()).toString());
		
		String xxdm=Base.xxdm;
		//ѧ����������������Ŀ�б�
		if(Globals.XXDM_ZGDZDX.equalsIgnoreCase(xxdm)){
			//�й����ʴ�ѧ ����
			myForm.setGnmklx("zz");
			dbsxxmList.addAll(dzdxDAO.getZzpjList(myForm));
			myForm.setGnmklx("pj");
			dbsxxmList.addAll(dzdxDAO.getZzpjList(myForm));
		}else {
			dbsxxmList.addAll(getXszzDbxm(myForm));
			dbsxxmList.addAll(getGwglDb());
			dbsxxmList.addAll(getPjpyDbxm(myForm));
		}
		return dbsxxmList;
	}
	
	/**
	 * ��ȡѧ����������������Ŀ�б�
     * (��ʱֻ��ѧ��������Ϣ,����ģ��ȴ����)  
	 * author ��������
	 * @param String yhm, String type,
			String bmdm, boolean fdyqx, boolean bzrqx, String mklx
	 * @return List<HashMap<String, String>>
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getXszzDbxm(SyszForm myForm)
			throws Exception {

		DAO dao = DAO.getInstance();

		boolean isXy = false;
		
		String yhm=myForm.getUserName();
		
		String type=myForm.getUserType();
		
		String bmdm=myForm.getUserDep();
		
		boolean fdyqx=myForm.getFdyQx();
		
		boolean bzrqx=myForm.getBzrQx();
		// =======================����Ȩ��ƴ��SQL==========================
		if ("xy".equalsIgnoreCase(type)) {
			isXy = true;
		}

		StringBuilder sb = new StringBuilder();
 
		boolean blog=false;
		if(isXy){
			blog=true;
			//һ�����
			sb.append(" select xmdm, xn,xq,nd,'ѧ������' mklx from xszz_shztb a,view_xsjbxx c where xmdm in ");
			sb.append(" (select xmdm from xszz_zzxmb where shjb='һ�����' and xysh='��' ) ");
			sb.append(" and xysh='δ���'  and c.xydm='"+bmdm+"' ");
			//�������
			sb.append(" union all ");
			sb.append(" select xmdm, xn,xq,nd,'ѧ������' mklx from xszz_shztb a,view_xsjbxx c where xmdm in ");
			sb.append(" (select xmdm from xszz_zzxmb where shjb='�������' and xysh='��' ) ");
			sb.append(" and xysh='δ���'  and a.xh=c.xh  and (fdysh='ͨ��' or bzrsh='ͨ��') ");
			sb.append(" and c.xydm='"+bmdm+"' ");
			//�������
			sb.append(" union all ");
			sb.append(" select xmdm, xn,xq,nd,'ѧ������' mklx from xszz_shztb a,view_xsjbxx c where xmdm in ");
			sb.append(" (select xmdm from xszz_zzxmb where shjb='�������' and xysh='��' ) ");
			sb.append(" and xysh='δ���'  and a.xh=c.xh  and (fdysh='ͨ��' or bzrsh='ͨ��') ");
			sb.append(" and c.xydm='"+bmdm+"' ");
			
		}
		
		if(fdyqx ){
			if(blog){
				sb.append("union all");
			}
			//һ�����
			sb.append(" select xmdm, xn,xq,nd,'ѧ������' mklx from xszz_shztb a,view_xsjbxx c where xmdm in ");	
			sb.append(" (select xmdm from xszz_zzxmb where shjb='һ�����' and fdysh='��' ) ");
			sb.append(" and fdysh='δ���' and a.xh=c.xh ");
			sb.append(" and exists(select 1 from fdybjb b where c.bjdm=b.bjdm and b.zgh='" + yhm + "')");
			blog=true;
		}
		
		if(bzrqx){
			if(blog){
				sb.append("union all");
			}
			//һ�����
			sb.append(" select xmdm, xn,xq,nd,'ѧ������' mklx from xszz_shztb a,view_xsjbxx c  where xmdm in ");	
			sb.append(" (select xmdm from xszz_zzxmb where shjb='һ�����' and bzrsh='��' ) ");
			sb.append(" and bzrsh='δ���' and a.xh=c.xh ");
			sb.append("  and exists(select 1 from  bzrbbb b where c.bjdm=b.bjdm and b.zgh='" + yhm + "') ");
			blog=true;
		}
		
		if("admin".equalsIgnoreCase(type) || "xx".equalsIgnoreCase(type)){
			if(blog){
				sb.append("union all");
			}
			//һ�����
			sb.append(" select xmdm, xn,xq,nd,'ѧ������' mklx from xszz_shztb where xmdm in ");	
			sb.append(" ( select xmdm from xszz_zzxmb where shjb='һ�����' and xxsh='��'  ) ");
			sb.append("  and xxsh='δ���' ");
			sb.append("union all");
			//�������
			sb.append(" select xmdm, xn,xq,nd,'ѧ������' mklx from xszz_shztb b where xmdm in ");	
			sb.append(" (select xmdm from xszz_zzxmb where shjb='�������' and (bzrsh='��' and b.bzrsh='ͨ��' or xysh='��' and b.xysh='ͨ��' or  fdysh='��' and b.fdysh='ͨ��' ) )");
			sb.append("  and xxsh='δ���' ");
			sb.append("union all");
			//�������
			sb.append(" select xmdm, xn,xq,nd,'ѧ������' mklx from xszz_shztb b where xmdm in ");	
			sb.append(" (select xmdm from xszz_zzxmb where shjb='�������' and xysh='��' and b.xysh='ͨ��'  ) ");
			sb.append("  and xxsh='δ���' ");
			
		}
		// =======================����Ȩ��ƴ��SQL  END==========================
		
		StringBuilder sql=new StringBuilder();
		sql.append(" select distinct(a.xmdm) dm,b.xmmc mc,'ѧ������'lb from ("+sb.toString()+")a,xszz_zzxmb b where a.xmdm=b.xmdm ");
		
		return  dao.getList(sql.toString(), new String[]{}, new String[]{"dm","mc","lb"});
	}
	
	/**
	 * �ļ����� ��ȡ������
	 * @return
	 */
	public List<HashMap<String, String>> getWjglSjr(User user){
		DAO dao=DAO.getInstance();
		StringBuilder sb=new StringBuilder();
		sb.append(" select wjh,wjm,jsr,wjsclj,wjffbm from wjsc_scwjxxb where wjjssj is  null and jsr like '%,"+user.getUserName()+"' ");
		sb.append(" or jsr like '"+user.getUserName()+",%' or jsr like '%,"+user.getUserName()+",%' ");
		return dao.getList(sb.toString(), new String[]{}, new String[]{"wjh","wjm","jsr","wjsclj","wjffbm"});
	}
	
	public List<String[]>getGwglDbsx(SyszForm myForm,User user) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		//��ȡ������
		List<HashMap<String, String>> wjglList=getWjglSjr(user);
		List<HashMap<String,String>>wjList=new ArrayList<HashMap<String,String>>();
		MakeQuery mQuery=new MakeQuery();
		String[]colList={};
		String[]colLikeList={"wjh","wjm"};
		mQuery.makeQuery(colList, colLikeList, myForm);
		for(int i=0;i<wjglList.size();i++){
			//
			HashMap<String,String>wjjsrMap=wjglList.get(i);
			String[]jsr=wjjsrMap.get("jsr").split(",");
			for(int j=0;j<jsr.length;j++){
				if(user.getUserName().equalsIgnoreCase(jsr[j])
						&& myForm.getXmdm().equalsIgnoreCase(wjjsrMap.get("wjffbm"))){
					HashMap<String,String>wjMap=new HashMap<String,String>();
					wjMap.put("wjh", wjjsrMap.get("wjh"));
					wjList.add(wjMap);
					break;
				}
			}
		}
		
		StringBuilder sql=new StringBuilder();
		sql.append(" select rownum r,wjh,wjm,wjffsj from  wjsc_scwjxxb " );
		sql.append(mQuery.getQueryString());
		sql.append(" and  wjffbm='"+myForm.getXmdm()+"' ");
		for(int i=0;i<wjList.size();i++){
			HashMap<String,String>wjMap=wjList.get(i);
			if(i==0 && i==wjList.size()-1){
				sql.append(" and (wjh='"+wjMap.get("wjh")+"') ") ;
			}else if(i==0){
				sql.append(" and (wjh='"+wjMap.get("wjh")+"' ") ;
			}else if(i==wjList.size()-1){
				sql.append(" or wjh='"+wjMap.get("wjh")+"' )");
			}else {
				sql.append(" or wjh='"+wjMap.get("wjh")+"' ") ;
			}
			
		}
	
	 	return CommonQueryDAO.commonQuery(sql.toString(), "", mQuery.getInputList(),
				new String[] {"wjh" , "wjm", "wjffsj"}, myForm);
	}
	
	public List<HashMap<String,String>>getWjmcList(User user){
		//��ȡ������
		List<HashMap<String, String>> wjglList=getWjglSjr(user);
		List<HashMap<String,String>>wjList=new ArrayList<HashMap<String,String>>();
		for(int i=0;i<wjglList.size();i++){
			//
			HashMap<String,String>wjjsrMap=wjglList.get(i);
			String[]jsr=wjjsrMap.get("jsr").split(",");
			for(int j=0;j<jsr.length;j++){
				if(user.getUserName().equalsIgnoreCase(jsr[j])){
					HashMap<String,String>wjMap=new HashMap<String,String>();
					wjMap.put("wjh", wjjsrMap.get("wjh"));
					wjMap.put("xmmc", wjjsrMap.get("wjm"));
					wjMap.put("wjsclj", wjjsrMap.get("wjsclj"));
					wjMap.put("dblx", "δ����");
					wjMap.put("mklx", "�ļ�����");
					wjList.add(wjMap);
					break;
				}
			}
		}
		return wjList;
	}
	
	
	public List<HashMap<String,String>>getGwglDb(){
		DAO dao=DAO.getInstance();
		String sql=" select a.bmdm dm,a.bmmc mc,'�ļ�����'lb from zxbz_xxbmdm  a where a.bmlb='5' and a.bmfdm is null union";
		sql+=" select a.bmdm dm,a.bmmc mc,'�ļ�����'lb from zxbz_xxbmdm a ,yhb b  where a.bmdm=b.szbm ";
		return dao.getList(sql, new String[]{}, new String[]{"dm","mc","lb"});

	}
	
	/**
	 * ѧ��������Ŀ�б�
	 * (��ʱֻ��ѧ��������Ϣ,����ģ��ȴ����)  
	 * author ��������
	 * @param mklx
	 * @param userName
	 * @return List<HashMap<String, String>> 
	 */
	public List<HashMap<String, String>> getXmList(String mklx, String userName) {

		List<HashMap<String, String>> xmList = new ArrayList<HashMap<String, String>>();
		if ("ѧ������".equalsIgnoreCase(mklx)) {
			xmList.addAll(getZzXmList(mklx, userName));
		}
		
		return xmList;
	}
	
	/**
	 * ѧ������ ѧ��������Ŀ�б�
	 * (��ʱֻ��ѧ��������Ϣ,����ģ��ȴ����)  
	 * author ��������
	 * @param mklx
	 * @param userName
	 * @return List<HashMap<String, String>> 
	 */
	public List<HashMap<String, String>> getZzXmList(String mklx, String userName) {

		StringBuilder sb = new StringBuilder();
		DAO dao = DAO.getInstance();

		sb.append("select xmdm dm,xmmc mc,mklx from ( select * from ( select  distinct(b.xmdm)xmdm,b.xmmc ,'ѧ������' mklx ");
		sb.append(" from xszz_shztb a,xszz_zzxmb b where a.xmdm=b.xmdm and  xh='"
						+ userName + "' ");
		sb.append(" )order by xmdm ) ");
		
		//�ж�ģ�����ͣ������������ȣ�
		if (!"".equalsIgnoreCase(mklx)) {
			sb.append(" where  mklx='" + mklx + "'  ");
		}
	
		return dao.getList(sb.toString(), new String[] {}, new String[] {
				"mklx", "dm", "mc" });
	}
	
	/**
	 * ��ʾ��Ŀ���Ƿ���ʾ���� �������湦�� 
	 * author ��������
	 * @param myForm
	 * @return boolean
	 * @throws Exception
	 */
	public boolean updateSfxs(SyszForm myForm) throws Exception {

		DAO dao = DAO.getInstance();

		boolean flag = false;
		// ��Ŀ����
		String[] xmdm = myForm.getXmdmArr();
		// ��ʾ����
		String[] sfxs = myForm.getSfxsArr();

		StringBuffer sql = new StringBuffer();

		// �ж��Ƿ�����Ŀ ��Ҫ����
		if (xmdm != null && xmdm.length > 0) {

			String[] arr_sql = new String[xmdm.length];

			for (int i = 0; i < xmdm.length; i++) {
				sql = new StringBuffer();
				// ��ҳ���� ��ʾ��Ŀ��
				sql.append("update xg_sysz_xsxmb set ");
				sql.append("sfxs = '" + sfxs[i] + "' ");
				sql.append("where xmdm = '" + xmdm[i] + "' ");

				arr_sql[i] = sql.toString();
			}

			flag = dao.saveArrDate(arr_sql);
		}

		return flag;
	}

	/**
	 * ��ȡģ�����
	 * @param user
	 * @return List<HashMap<String,String>>
	 */
	public List<HashMap<String,String>>getMklb(String userName){
		DAO dao=DAO.getInstance();
		StringBuilder sb=new StringBuilder();
		sb.append(" select mkmc dm,mkmc mc from (select * from yhqxb b ");
		sb.append(" where yhm = ? and exists (select gnmkdm, dyym  from (select gnmkdm, dyym ");
		sb.append("  from gnmkdmb b where exists  (select 1 from xg_sysz_dbsxnrb a where a.dyym = b.dyym))a ");
		sb.append(" where a.gnmkdm=b.gnmkdm))a,xg_sysz_dbsxnrb b where a.gnmkdm=b.mkdm order by xssx ");
		return dao.getList(sb.toString(), new String[]{userName}, new String[]{"dm","mc"});
	}
	
	/** ��ȡģ�����
	 * @param user
	 * @return List<HashMap<String,String>>
	 */
	public List<HashMap<String,String>>getXssqMklb(){
		DAO dao=DAO.getInstance();
		StringBuilder sb=new StringBuilder();
		sb.append(" select mkmc dm,mkmc mc from (select * from yhzqxb b  where zdm='6727'  ");
		sb.append("  and exists (select gnmkdm, dyym  from (select gnmkdm, dyym ");
		sb.append("  from gnmkdmb b where exists  (select 1 from xg_sysz_wdsqnrb a where a.dyym = b.dyym))a ");
		sb.append(" where a.gnmkdm=b.gnmkdm))a,xg_sysz_wdsqnrb b where a.gnmkdm=b.mkdm order by xssx ");
		return dao.getList(sb.toString(), new String[]{}, new String[]{"dm","mc"});
	}
	
	public boolean deleteFile(String filePath) throws Exception{
		DAO dao=DAO.getInstance();
		String sql=" delete from xg_xtwh_xzzqb where filepath=? ";
		return dao.runUpdate(sql,new String[]{filePath});
	}
	
	/**
	 * ��ȡ �������Ŵ�������(ͨ��)
	 * @param user 
	 * @return  List<HashMap<String,String>>
	 * @author qlj
	 */
	public List<HashMap<String,String>>getPjpyDbxm(SyszForm myForm){
		
		DAO dao = DAO.getInstance();
		//�û���
		StringBuilder sql=new StringBuilder();
		
//		 ����ѧ��
		String pjxn = PjxtszModel.pjxtszModel.getPjxn();
		// ����ѧ��
		String pjxq = PjxtszModel.pjxtszModel.getPjxq();
		// �������
		String pjnd = PjxtszModel.pjxtszModel.getPjnd();
		// �û���
		String userName = myForm.getUserName();

		sql.append(" select a.xmdm dm,xmmc mc,'��������' lb ");
		sql.append(" from xg_pjpy_pjxmshb a ");
		sql.append(" left join xg_xtwh_spgw b on a.xtgwid = b.id ");
		sql.append(" left join xg_pjpy_pjxmwhb c on a.xmdm=c.xmdm ");
		sql.append(" left join xg_xtwh_spgwyh d on b.id=d.spgw ");
		sql.append(" where shzt = 'δ���' and d.spyh='"+userName+"' group by a.xmdm,c.xmmc ");

		return  dao.getList(sql.toString(),new String[]{},new String[]{"lb","dm","mc"});
	}
	
	/**
	 * ��ȡ �������Ŵ�������(ͨ��)
	 * @param user 
	 * @return  List<HashMap<String,String>>
	 * @author qlj
	 * @throws Exception 
	 */
	public List<String[]>getPjpyDbsx(SyszForm myForm) throws Exception{
		
		DAO dao = DAO.getInstance();

		StringBuilder sql=new StringBuilder();
		
//		 ����ѧ��
		String pjxn = PjxtszModel.pjxtszModel.getPjxn();
		// ����ѧ��
		String pjxq = PjxtszModel.pjxtszModel.getPjxq();
		// �������
		String pjnd = PjxtszModel.pjxtszModel.getPjnd();
		// �û���
		String userName = myForm.getUserName();

		//sql.append("( ");
		sql.append(" select  rownum r ,a.* from (select '��������' mklx, 'δ���' dblx,xmdm,xmmc,");
		sql.append(" to_char(WM_CONCAT(gwmc||'('||xsh||')')) shxx,to_char(WM_CONCAT(xtgwid)) gwxx,to_char(WM_CONCAT(gwjb)) jbxx from( ");
		sql.append(" select xmdm,xmmc,pjxn,pjxq,pjnd,xsh,xtgwid,gwmc,xh gwjb from ");
		sql.append(" ( select b.xmdm,b.xmmc, pjxn, pjxq, pjnd,count(1)xsh,xtgwid,gwmc,lcid ");
		sql.append(" from (select * from xg_xtwh_spgwyh where spyh = '"+userName+"') a, ");
		sql.append(" (select a.*, b.mc gwmc,c.xmmc,c.lcid ");
		sql.append(" from xg_pjpy_pjxmshb a ");
		sql.append(" left join xg_xtwh_spgw b on a.xtgwid = b.id ");
		sql.append(" left join xg_pjpy_pjxmwhb c on a.xmdm=c.xmdm ");
		sql.append(" where shzt = 'δ���') b ");
		sql.append(" where a.spgw = b.xtgwid  ");
		sql.append(" group by xmdm,xmmc, pjxn, pjxq, pjnd,xtgwid,gwmc,lcid)a ,");
		sql.append(" xg_xtwh_spbz b where a.xtgwid= b.spgw and a.lcid=b.splc)where 1=1  ");
		if(!Base.isNull(myForm.getXn())){
			sql.append(" and pjxn='"+myForm.getXn()+"' ");
		}
		if(!Base.isNull(myForm.getXq())){
			sql.append(" and pjxq='"+myForm.getXq()+"'");
		}
		if(!Base.isNull(myForm.getNd())){
			sql.append(" and pjnd='"+myForm.getNd()+"'");
		}
		if(!Base.isNull(myForm.getXmdm())){
			sql.append(" and xmdm='"+myForm.getXmdm()+"'");
		}
		sql.append(" group by xmdm,xmmc, pjxn, pjxq, pjnd )a ");

		return CommonQueryDAO.commonQuery(sql.toString(), "", new String[] {},
				new String[] { "mklx", "xmdm", "xmmc", "dblx", "shxx", "gwxx",
			"jbxx"  }, myForm);
		
	}
	
	/**
	 * ��ȡѧ������������Ϣ
	 * author ��������
	 * @param myForm
	 * @return List<String[]>
	 * @throws Exception
	 */
	public List<String[]> getXspjsqInfo(SyszForm myForm) throws Exception {

		StringBuilder sql = new StringBuilder();
		String xh=myForm.getUserName();
		// ����ѧ��
		String pjxn = PjxtszModel.pjxtszModel.getPjxn();
		// ����ѧ��
		String pjxq = PjxtszModel.pjxtszModel.getPjxq();
		// �������
		String pjnd = PjxtszModel.pjxtszModel.getPjnd();

		// ===========================���� ������====================================
		sql.append(" select rownum r,a.*,shzt shjg from ( ");
		sql.append(" select sqzqxx,a.xmdm,a.xmmc,a.lcid,max(shjb) shjb, ");
		sql.append(" to_char(WM_CONCAT(a.gwmc || '(' || a.shzt || ')')) shzt  ");
		sql.append(" from (select a.xnxx||a.xqxx||a.ndxx sqzqxx,a.* from( ");
		sql.append(" select ");
		sql.append(" case when a.pjxn<>'��' then a.pjxn||'ѧ��' else '' end xnxx, ");
		sql.append(" case when a.pjxq<>'��' then a.pjxq||'ѧ��' else '' end xqxx, ");
		sql.append(" case when a.pjnd<>'��' then a.pjnd||'���' else '' end ndxx, ");
		sql.append(" a.xmdm, a.xmmc, a.shjb, a.mc gwmc, a.shzt,a.lcid from xg_view_pjpy_xmsh a  ");
		sql.append(" where xh = '"+xh+"' and (a.pjxn = '"+pjxn+"' or  ");
		sql.append(" (a.pjxn = '"+pjxn+"' and a.pjxq = '"+pjxq+"') or a.pjnd = '"+pjnd+"') ");
		sql.append(" order by xmdm, to_number(shjb)) a ");
		sql.append(" ) a group by a.sqzqxx,a.xmdm,a.xmmc,a.lcid) a ");
		// ===========================���� ������end====================================
		
		return CommonQueryDAO.commonQuery(sql.toString(), "", new String[] {},
				new String[] { "xmmc", "sqzqxx", "shjg" }, myForm);
	}
	
	/**
	 * ��ȡѧ��Υ����Ϣ
	 * author ��������
	 * @param myForm
	 * @return List<String[]>
	 * @throws Exception
	 */
	public List<String[]> getXswjxxInfo(SyszForm myForm) throws Exception {

		StringBuilder sql = new StringBuilder();
		String xh=myForm.getUserName();

		// ===========================���� ������====================================
		sql.append(" select rownum r,xmmc,sqzqxx, shjg,pkValue from (  ");
		sql.append(" select 'Υ�ʹ���' mklx,  ");
		sql.append(" 'Υ����Ϣ' xmmc, ");
		sql.append(" xh||xn||nd||sbsj pkValue, ");
		sql.append(" to_char(to_date(wjsj, 'yyyymmdd'), 'yyyy') || '��' ||  ");
		sql.append(" to_char(to_date(wjsj, 'yyyymmdd'), 'mm') ||'��' ||  ");
		sql.append(" to_char(to_date(wjsj, 'yyyymmdd'), 'dd') || '��' sqzqxx,  ");
		sql.append(" '����'||cfyymc||'�ܵ�'||cflbmc||'����' shjg ");
		sql.append("  from view_wjcf a   where xh = '"+xh+"'  ");
		//sql.append(" and xn = (select dqxn from xtszb where rownum = 1) ");
		sql.append("  and sfqs = '��'  ) a ");
		// ===========================���� ������end====================================
		
		return CommonQueryDAO.commonQuery(sql.toString(), "", new String[] {},
				new String[] { "xmmc", "sqzqxx", "shjg","pkValue" }, myForm);
	}
	
	/**
	 * ������ҳ����ID��ȡ��������
	 * @param myForm
	 * @return
	 * @throws Exception
	 */
	public HashMap<String,String>getSydcInfo(SyszForm myForm) throws Exception {
		DAO dao=DAO.getInstance();
		StringBuilder sql = new StringBuilder();
		sql.append(" select dcnr,(substr(a.dcnr,0,25)|| ");
		sql.append(" case when length(a.dcnr)>25 then '...' end) ");
		sql.append(" dcinfo from xg_xtwh_sydcb a where dcid=? ");
		return dao.getMap(sql.toString(), new String[]{myForm.getDcid()}, new String[]{"dcnr","dcinfo"});
	}
	

	// =======================����made by ��������=====================
}
