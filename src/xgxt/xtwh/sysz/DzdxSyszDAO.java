package xgxt.xtwh.sysz;

import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.comm.homepage.DzdxHomePageDAO;
import xgxt.utils.CommonQueryDAO;

public class DzdxSyszDAO {
	/**
	 * ѧ����������������Ϣ(��ʦ)
     * author qlj
	 * @param myForm
	 * @return List<String[]>
	 * @throws Exception
	 */
	public List<String[]> getZzpjDbsx(SyszForm myForm) throws Exception {
		
		
		DzdxHomePageDAO dzdxDAO=new DzdxHomePageDAO();
		
		String mklx=myForm.getGnmklx();
		
		StringBuilder xmlb=dzdxDAO.getXmlbQuery(mklx);
		
		String mkmc="";
		String ljdz="";
		if("zz".equalsIgnoreCase(mklx)){
			mkmc="ѧ������";
			ljdz="commXszz.do?method=xmshManage&mklx=zz&widthType=dbsx&go=go&xmdm=";
		}else if("pj".equalsIgnoreCase(mklx)){
			mkmc="��������";
			ljdz="commXszz.do?method=xmshManage&mklx=pj&widthType=dbsx&go=go&xmdm=";
		}
		
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
			sb.append(" (select xmdm from xszz_zzxmb where shjb='һ�����' and xysh='��' "+xmlb.toString()+") ");
			sb.append(" and xysh='δ���'  and c.xydm='"+bmdm+"' ");
			//�������
			sb.append(" union all ");
			sb.append(" select xmdm, xn,xq,nd,substr(sqsj,0,4)sqn from xszz_shztb a,view_xsjbxx c where xmdm in ");
			sb.append(" (select xmdm from xszz_zzxmb where shjb='�������' and xysh='��' "+xmlb.toString()+") ");
			sb.append(" and xysh='δ���'  and a.xh=c.xh  and (fdysh='ͨ��' or bzrsh='ͨ��') ");
			sb.append(" and c.xydm='"+bmdm+"' ");
			//�������
			sb.append(" union all ");
			sb.append(" select xmdm, xn,xq,nd,substr(sqsj,0,4)sqn from xszz_shztb a,view_xsjbxx c where xmdm in ");
			sb.append(" (select xmdm from xszz_zzxmb where shjb='�������' and xysh='��' "+xmlb.toString()+") ");
			sb.append(" and xysh='δ���'  and a.xh=c.xh  and (fdysh='ͨ��' or bzrsh='ͨ��') ");
			sb.append(" and c.xydm='"+bmdm+"' ");
			
		}
		
		if(fdyqx ){
			if(blog){
				sb.append("union all");
			}
			//һ�����
			sb.append(" select xmdm, xn,xq,nd,substr(sqsj,0,4)sqn from xszz_shztb a,view_xsjbxx c where xmdm in ");	
			sb.append(" (select xmdm from xszz_zzxmb where shjb='һ�����' and fdysh='��' "+xmlb.toString()+" ) ");
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
			sb.append(" (select xmdm from xszz_zzxmb where shjb='һ�����' and bzrsh='��' "+xmlb.toString()+" ) ");
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
			sb.append(" ( select xmdm from xszz_zzxmb where shjb='һ�����' and xxsh='��' "+xmlb.toString()+" ) ");
			sb.append("  and xxsh='δ���' ");
			sb.append("union all");
			//�������
			sb.append(" select xmdm, xn,xq,nd,substr(sqsj,0,4)sqn from xszz_shztb b where xmdm in ");	
			sb.append(" (select xmdm from xszz_zzxmb where shjb='�������' "+xmlb.toString()+" and (bzrsh='��' and b.bzrsh='ͨ��' or xysh='��' and b.xysh='ͨ��' or  fdysh='��' and b.fdysh='ͨ��' ) )");
			sb.append("  and xxsh='δ���' ");
			sb.append("union all");
			//�������
			sb.append(" select xmdm, xn,xq,nd,substr(sqsj,0,4)sqn from xszz_shztb b where xmdm in ");	
			sb.append(" (select xmdm from xszz_zzxmb where shjb='�������' and xysh='��' and b.xysh='ͨ��' "+xmlb.toString()+" ) ");
			sb.append("  and xxsh='δ���' ");
			
		}
		
		StringBuilder  sql=new StringBuilder();
		
	    sql.append("select rownum r,xmdm,xsh,xn,xq,nd,sqn,xmmc,sqzq,case when sqn=dqnd then '"+ljdz+"'||xmdm else '#' end ljdz from (");
		sql.append("select a.*,b.dqnd from (select  a.xmdm, a.xsh,a.xn,a.xq,a.nd,a.sqn,('��"+mkmc+"��'||b.xmmc)xmmc,b.sqzq from( select xmdm,count(1) xsh, xn,xq,nd,sqn from ("+sb.toString()+") group by xmdm,xn,xq,nd,sqn)a,xszz_zzxmb b where a.xmdm=b.xmdm)a left join xtszb b on 1=1 )a");
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
	 * ѧ�������������������б�(��ʦ)
     * author qlj
	 * @param myForm
	 * @return List<String[]>
	 * @throws Exception
	 */
	public List<HashMap<String,String>> getZzpjList(SyszForm myForm) throws Exception {
		
		
		DzdxHomePageDAO dzdxDAO=new DzdxHomePageDAO();
		
		DAO dao=DAO.getInstance();
		
		String mklx=myForm.getGnmklx();
		
		StringBuilder xmlb=dzdxDAO.getXmlbQuery(mklx);
		
		String mkmc="";
		
		String bmdm=myForm.getUserDep();
		
		String type=myForm.getUserType();
		
		String yhm=myForm.getUserName();
		
		boolean fdyQx=myForm.getFdyQx();
		
		boolean bzrQx=myForm.getBzrQx();
		
		if("zz".equalsIgnoreCase(mklx)){
			mkmc="ѧ������";
		}else if("pj".equalsIgnoreCase(mklx)){
			mkmc="��������";
		}
		

		boolean isXy = false;

		// =======================����Ȩ��ƴ��SQL==========================
		if ("xy".equalsIgnoreCase(myForm.getUserType())) {
			isXy = true;
		}

		StringBuilder sb = new StringBuilder();
 
		boolean blog=false;
		if(isXy){
			blog=true;
			//һ�����
			sb.append(" select xmdm, xn,xq,nd,'"+mkmc+"' mklx from xszz_shztb a,view_xsjbxx c where xmdm in ");
			sb.append(" (select xmdm from xszz_zzxmb where shjb='һ�����' and xysh='��' "+xmlb.toString()+") ");
			sb.append(" and xysh='δ���'  and c.xydm='"+bmdm+"' ");
			//�������
			sb.append(" union all ");
			sb.append(" select xmdm, xn,xq,nd,'"+mkmc+"' mklx from xszz_shztb a,view_xsjbxx c where xmdm in ");
			sb.append(" (select xmdm from xszz_zzxmb where shjb='�������' and xysh='��' "+xmlb.toString()+") ");
			sb.append(" and xysh='δ���'  and a.xh=c.xh  and (fdysh='ͨ��' or bzrsh='ͨ��') ");
			sb.append(" and c.xydm='"+bmdm+"' ");
			//�������
			sb.append(" union all ");
			sb.append(" select xmdm, xn,xq,nd,'"+mkmc+"' mklx from xszz_shztb a,view_xsjbxx c where xmdm in ");
			sb.append(" (select xmdm from xszz_zzxmb where shjb='�������' and xysh='��' "+xmlb.toString()+") ");
			sb.append(" and xysh='δ���'  and a.xh=c.xh  and (fdysh='ͨ��' or bzrsh='ͨ��') ");
			sb.append(" and c.xydm='"+bmdm+"' ");
			
		}
		
		if(fdyQx){
			if(blog){
				sb.append("union all");
			}
			//һ�����
			sb.append(" select xmdm, xn,xq,nd,'"+mkmc+"' mklx from xszz_shztb a,view_xsjbxx c where xmdm in ");	
			sb.append(" (select xmdm from xszz_zzxmb where shjb='һ�����' and fdysh='��' "+xmlb.toString()+") ");
			sb.append(" and fdysh='δ���' and a.xh=c.xh ");
			sb.append(" and exists(select 1 from fdybjb b where c.bjdm=b.bjdm and b.zgh='" + yhm + "')");
			blog=true;
		}
		
		if(bzrQx){
			if(blog){
				sb.append("union all");
			}
			//һ�����
			sb.append(" select xmdm, xn,xq,nd,'"+mkmc+"' mklx from xszz_shztb a,view_xsjbxx c  where xmdm in ");	
			sb.append(" (select xmdm from xszz_zzxmb where shjb='һ�����' and bzrsh='��' "+xmlb.toString()+") ");
			sb.append(" and bzrsh='δ���' and a.xh=c.xh ");
			sb.append("  and exists(select 1 from  bzrbbb b where c.bjdm=b.bjdm and b.zgh='" + yhm + "') ");
			blog=true;
		}
		
		if("admin".equalsIgnoreCase(type) || "xx".equalsIgnoreCase(type)){
			if(blog){
				sb.append("union all");
			}
			//һ�����
			sb.append(" select xmdm, xn,xq,nd,'"+mkmc+"' mklx from xszz_shztb where xmdm in ");	
			sb.append(" ( select xmdm from xszz_zzxmb where shjb='һ�����' and xxsh='��' "+xmlb.toString()+" ) ");
			sb.append("  and xxsh='δ���' ");
			sb.append("union all");
			//�������
			sb.append(" select xmdm, xn,xq,nd,'"+mkmc+"' mklx from xszz_shztb b where xmdm in ");	
			sb.append(" (select xmdm from xszz_zzxmb where shjb='�������'  "+xmlb.toString()+" and  (bzrsh='��' and b.bzrsh='ͨ��' or xysh='��' and b.xysh='ͨ��' or  fdysh='��' and b.fdysh='ͨ��' ) )");
			sb.append("  and xxsh='δ���' ");
			sb.append("union all");
			//�������
			sb.append(" select xmdm, xn,xq,nd,'"+mkmc+"' mklx from xszz_shztb b where xmdm in ");	
			sb.append(" (select xmdm from xszz_zzxmb where shjb='�������' and xysh='��' and b.xysh='ͨ��'  "+xmlb.toString()+") ");
			sb.append("  and xxsh='δ���' ");
			
		}
		// =======================����Ȩ��ƴ��SQL  END==========================
		
		StringBuilder sql=new StringBuilder();
		sql.append(" select distinct(a.xmdm) dm,b.xmmc mc,a.mklx lb from ("+sb.toString()+")a,xszz_zzxmb b where a.xmdm=b.xmdm ");
		return dao.getList(sql.toString(), new String[]{}, new String[]{"dm","mc","lb"});
	}

	/** ��ȡѧ������������Ŀ��Ϣ
	 * author qlj
     * (��ʱֻ��ѧ��������Ϣ,����ģ��ȴ����) 
	 * @param myForm
	 * @return List<String[]>
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getSqzzxxList(SyszForm myForm)
			throws Exception {

		StringBuilder sb = new StringBuilder();
		DzdxHomePageDAO dzdxDAO=new DzdxHomePageDAO();
		StringBuilder xmlb=dzdxDAO.getXmlbQuery(myForm.getGnmklx());
		DAO dao = DAO.getInstance();
		String mkmc="";
		String mklx=myForm.getGnmklx();
		if("zz".equalsIgnoreCase(mklx)){
			mkmc="ѧ������";
		}else if("pj".equalsIgnoreCase(mklx)){
			mkmc="��������";
		}
		sb.append("  select dm,mc,lb,xmlb from ( select  distinct(b.xmdm)dm,b.xmmc mc ,'"+mkmc+"' lb,xmlb ");
		sb.append(" from xszz_shztb a,xszz_zzxmb b where a.xmdm=b.xmdm and  xh='"
						+ myForm.getUserName() + "' )where 1=1 "+xmlb);
		sb.append("order by dm  ");
		return dao.getList(sb.toString(), new String[] {}, new String[] {
				"lb", "dm", "mc" });
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
		
		DzdxHomePageDAO dzdxDAO=new DzdxHomePageDAO();
		StringBuilder sb = new StringBuilder();
		String mkmc="";
		
		StringBuilder xmlb=dzdxDAO.getXmlbQuery(myForm.getGnmklx());
		String mklx=myForm.getGnmklx();
		if("zz".equalsIgnoreCase(mklx)){
			mkmc="ѧ������";
		}else if("pj".equalsIgnoreCase(mklx)){
			mkmc="��������";
		}
		
		// ===========================ѧ������ ������====================================
		sb.append(" select * from ( select rownum r, b.xmdm,b.xmlb,('��"+mkmc+"��'||b.xmmc)xmmc, ");
		
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
		sb.append(" ) where 1=1 "+xmlb+" order by xmdm ");
		// ===========================ѧ������ ������end====================================
		System.out.println(sb);
		return CommonQueryDAO.commonQuery(sb.toString(), "", new String[] {},
				new String[] { "xmmc", "sqzqxx", "shjg" }, myForm);
	}
}
