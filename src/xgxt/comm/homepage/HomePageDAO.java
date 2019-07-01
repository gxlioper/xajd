package xgxt.comm.homepage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.xtwh.yhsjfw.YhsjfwService;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xgxt.comm.xml.XMLReader;
import xgxt.form.User;
import xgxt.pjpy.comm.pjpy.PjpyCommDAO;
import xgxt.pjpy.comm.pjpy.PjpyCommService;
import xgxt.pjpy.comm.pjpy.PjxtszModel;
import xgxt.pjpy.comm.pjpy.model.PjpyXmszModel;

import common.Globals;

public class HomePageDAO {
	
	
	/**
	 * ��ȡ ѧ��������������(ͨ��)
	 * @param user 
	 * @return  List<HashMap<String,String>>
	 * author ��������
	 */
	public List<HashMap<String,String>>getXszzDbsx(User user,HomePageModel model){
		
		DAO dao = DAO.getInstance();
		//�û���
		String yhm = user.getUserName();
		//�û�����
		String type = user.getUserType();
		//�û�����
		String bmdm = user.getUserDep();
		//��ʾ��¼��
		int maxSize=model.getMaxSize();
		
		//����ԱȨ��
		boolean fdyqx = Boolean.parseBoolean(user.getFdyQx());
		//������Ȩ��
		boolean bzrqx = Boolean.parseBoolean(user.getBzrQx());
		
		boolean isXy = false;
		
		if ("xy".equalsIgnoreCase(type) && !fdyqx && !bzrqx) {
			// ѧԺ�û�
			isXy = true;
		}
		
		StringBuilder sb=new StringBuilder();
		
		
		//����ѧԺ�û���Ҫ����Ŀ
		//��Ŀ���롢ѧ�ꡢѧ�ڡ���ȡ�����ʱ��ǰ4λ
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
			sb.append(" and xysh='δ���'  and a.xh=c.xh  and (fdysh='ͨ��' or bzrsh='ͨ��' or xysh='δ���') ");
			sb.append(" and c.xydm='"+bmdm+"' ");
			//�������
			sb.append(" union all ");
			sb.append(" select xmdm, xn,xq,nd,sqsj from xszz_shztb a,view_xsjbxx c where xmdm in ");
			sb.append(" (select xmdm from xszz_zzxmb where shjb='�������' and xysh='��' ) ");
			sb.append(" and xysh='δ���'  and a.xh=c.xh  and (fdysh='ͨ��' or bzrsh='ͨ��') ");
			sb.append(" and c.xydm='"+bmdm+"' ");
			
		}
		
		//����Ա
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
		
		//������
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
		
		//����Ա��ѧУ
		if("admin".equalsIgnoreCase(type) || "xx".equalsIgnoreCase(type)){
			if(blog){
				sb.append("union all");
			}
			//һ�����
			sb.append(" select xmdm, xn,xq,nd,substr(sqsj,0,4)sqn from xszz_shztb where xmdm in ");	
			sb.append(" ( select xmdm from xszz_zzxmb where shjb='һ�����' and xxsh='��'  ) ");
			sb.append(" and xxsh='δ���' ");
			sb.append(" union all");
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
		
		String sql="select * from (";
		sql+="select * from (select rownum r,'ѧ������' mklx,'δ���' dblx, a.xmdm, a.xsh,a.xn,a.xq,a.nd,a.sqn,b.xmmc,b.sqzq from( select xmdm,count(1) xsh, xn,xq,nd,sqn from ("+sb.toString()+") group by xmdm,xn,xq,nd,sqn)a,xszz_zzxmb b where a.xmdm=b.xmdm)a where r<10 ";
		sql+=" and( (a.sqzq='ѧ��' and exists(select 1 from xtszb b where a.xn=b.dqxn)) ";
		sql+=" or (a.sqzq='ѧ��' and exists(select 1 from xtszb b where a.xn=b.dqxn and a.xq=b.dqxq)) ";
		sql+=" or (a.sqzq='���' and exists(select 1 from xtszb b where a.nd=b.dqnd)) or (a.sqzq='������' and exists(select 1 from xtszb b where a.sqn=b.dqnd))" ;
		sql+=" or (a.sqzq='��һ��' and exists(select 1 from xtszb b where a.sqn=b.dqnd)))";
		
		//����һ������Ŀ
		for(int i=0;i<getXszzxmList(user).size();i++){
			HashMap<String,String>hashMap=getXszzxmList(user).get(i);
			sql+=" or xmdm= '"+hashMap.get("xmdm")+"' ";
		}
		sql+=")where rownum <= "+maxSize;
	
		return  dao.getList(sql,new String[]{},new String[]{"mklx","xmdm","xmmc","xsh","dblx"});
	}
	
	/**
	 * ��ȡѧ��������Ŀ�б�
	 * @param user
	 * @return 
	 */
	public  List<HashMap<String, String>> getXszzxmList(User user){
		
		DAO dao=DAO.getInstance();
		
		String type = user.getUserType();
		
		boolean fdyqx = Boolean.parseBoolean(user.getFdyQx());
		boolean bzrqx = Boolean.parseBoolean(user.getBzrQx());
		
		boolean isXy = false;
		
		if ("xy".equalsIgnoreCase(type) && !fdyqx && !bzrqx) {
			// ѧԺ�û�
			isXy = true;
		}
		
		StringBuilder sql=new StringBuilder();
		boolean blog=false;
		if(isXy){
			blog=true;
			sql.append(" select xmdm,xmmc from xszz_zzxmb where (shjb='һ�����' and xysh='��') or (shjb='�������' and xysh='��') or (shjb='�������' and xysh='��') ");
		}
		
		if(fdyqx){
			if(blog){
				sql.append(" union ");
			}
			sql.append(" select xmdm,xmmc from xszz_zzxmb where (shjb='һ�����' and fdysh='��') or (shjb='�������' and fdysh='��') or (shjb='�������' and fdysh='��') ");
			blog=true;
		}
		
		if(bzrqx){
			if(blog){
				sql.append(" union ");
			}
			sql.append(" select xmdm,xmmc from xszz_zzxmb where (shjb='һ�����' and bzrsh='��') or (shjb='�������' and bzrsh='��') or (shjb='�������' and bzrsh='��') ");
			blog=true;
		}
		
		if("admin".equalsIgnoreCase(type) || "xx".equalsIgnoreCase(type)){
			if(blog){
				sql.append(" union ");
			}
			sql.append(" select xmdm,xmmc from xszz_zzxmb where (shjb='һ�����' and xxsh='��') or (shjb='�������' and xxsh='��') or (shjb='�������' and xxsh='��') ");
			blog=true;
		}
		return dao.getList(sql.toString(), new String[]{}, new String[]{"xmdm","xmmc"});
	}

	/**
	 * �ļ����� ��ȡ������(ֻ��ȡ δ���յ�)
	 * @return
	 */
	public List<HashMap<String, String>> getWjglSjr(User user){
		DAO dao=DAO.getInstance();
		StringBuilder sb=new StringBuilder();
		sb.append(" select wjh,wjm,jsr,wjsclj,wdrxm from wjsc_scwjxxb where  (jsr like '%,"+user.getUserName()+"' ");
		sb.append(" or jsr like '"+user.getUserName()+",%' or jsr like '%,"+user.getUserName()+",%') ");
		sb.append(" and( wdrxm like '%,"+user.getUserName()+"'  ");
		sb.append(" or wdrxm like '"+user.getUserName()+",%' or wdrxm like '%,"+user.getUserName()+",%') ");
		sb.append(" order by wjffsj desc ");
		return dao.getList(sb.toString(), new String[]{}, new String[]{"wjh","wjm","jsr","wjsclj","wdrxm"});
	}
	
	/**
	 * ��ҳ�������� �ļ���Ϣ
	 * @param user
	 * @return
	 */
	public List<HashMap<String,String>>getWjmcList(User user,HomePageModel model){
		//��ȡ������
		List<HashMap<String, String>> wjglList=getWjglSjr(user);
		List<HashMap<String,String>>wjList=new ArrayList<HashMap<String,String>>();
		int maxSize=model.getMaxSize();
		for(int i=0;i<wjglList.size();i++){
			// -------------�޸�ʱ�䣺2012.2.22      --------------
			// -------------�޸��ˣ�������           --------------
			// -------------�޸����ݣ���ʾ��¼���Ŀ���--------------
			if(maxSize>0){
				HashMap<String,String>wjjsrMap=wjglList.get(i);
				
				//������
				String[]jsr=wjjsrMap.get("jsr").split(",");
				//δ���ļ���
				String[]wdrxm=wjjsrMap.get("wdrxm").split(",");
				boolean blog=false;
				
				for(int j=0;j<jsr.length;j++){
					if(user.getUserName().equalsIgnoreCase(jsr[j])){
						blog=true;
						break;
					}
				}
				
				if(blog){
					// -------------�޸�ʱ�䣺2012.2.22--------------
					// -------------�޸��ˣ�������--------------
					// -------------�޸����ݣ�wdrxm.length����֮ǰ�������ƣ���Ҫɾ��--------------
					// -------------�߼������ˡ���������������������������������������--------------
					for(int j=0;j<wdrxm.length;j++){
						if(user.getUserName().equalsIgnoreCase(wdrxm[j])){
							HashMap<String,String>wjMap=new HashMap<String,String>();
							wjMap.put("wjh", wjjsrMap.get("wjh"));
							String shortSjmc = wjjsrMap.get("wjm").length() > 24 ? wjjsrMap
									.get("wjm").substring(0, 24)+"..."
									: wjjsrMap.get("wjm");
							wjMap.put("shortWjm", shortSjmc);
							wjMap.put("xmmc", wjjsrMap.get("wjm"));
							wjMap.put("wjsclj", wjjsrMap.get("wjsclj"));
							wjMap.put("dblx", "δ����");
							wjMap.put("mklx", "�ļ�����");
							wjList.add(wjMap);
							// -------------�޸�ʱ�䣺2012.2.22      --------------
							// -------------�޸��ˣ�������           --------------
							// -------------�޸����ݣ���¼���Լ�����¼������---------
							maxSize--;
							break;
						}
					}
				}
			}else{
				break;
			}
			
		}

		return wjList;
	}
	
	
	/**
	 * ��ȡ ѧ������ ѧ��������Ϣ
	 * @param user
	 * @return  List<HashMap<String,String>>
	 * author ��������
	 */
	public List<HashMap<String,String>>getXszzXssq(User user){
		DAO  xssqjg_dao = DAO.getInstance();
		
		String userName = user.getUserName();
		
		StringBuilder  xssqjg_sb=new StringBuilder();
		
		xssqjg_sb.append(" select * from ( select rownum r, b.xmdm,b.xmmc, ");
		
		//ƴ����ʾ�� ����ʱ��
		xssqjg_sb.append("(case when xn is null and xq is null and nd is null then sqsj");
		xssqjg_sb.append(" when xn is null and xq is null and nd is not null then nd||'���'");
		xssqjg_sb.append(" when nd is null and xq is null and xn is not null then xn||'ѧ��'");
		xssqjg_sb.append(" when nd is null and xq is not null and xn is not null then xn||'ѧ��,");
		xssqjg_sb.append(" '||(select xqmc from xqdzb where xqdm=xq)||'ѧ��' end )sqzqxx,");
		
		//��Ҫ������˵���Ŀ
		xssqjg_sb.append(" (case when shjb='�������' and  shzt3='ͨ��' then '�������ͨ��' ");
		xssqjg_sb.append("  when shjb='�������' and shzt3='��ͨ��' then '������˲�ͨ��' ");
		xssqjg_sb.append(" 	when shjb='�������' and shzt2='ͨ��' then '�������ͨ��' ");
		xssqjg_sb.append("  when shjb='�������' and shzt2='��ͨ��' then '�������ͨ��' ");
		xssqjg_sb.append("  when shjb='�������' and shzt1='ͨ��' then 'һ�����ͨ��' ");
		xssqjg_sb.append("  when shjb='�������' and shzt1='��ͨ��' then 'һ����˲�ͨ��' ");
		xssqjg_sb.append("  when shjb='�������' and shzt1='δ���' then 'δ���'     ");
		
		//��Ҫ������˵���Ŀ
		xssqjg_sb.append("  when shjb = '�������' and shzt2 = 'ͨ��' then '�������ͨ��' ");
        xssqjg_sb.append("  when shjb = '�������' and shzt2 = '��ͨ��' then '�������ͨ��' ");
		xssqjg_sb.append("  when shjb = '�������' and shzt1 = 'ͨ��' then 'һ�����ͨ��' ");
        xssqjg_sb.append("  when shjb = '�������' and shzt1 = '��ͨ��' then  'һ����˲�ͨ��' ");
		xssqjg_sb.append("  when shjb = '�������' and shzt1 = 'δ���' then 'δ���' ");
		
		//��Ҫһ����˵���Ŀ
 		xssqjg_sb.append("  when shjb = 'һ�����' and shzt1 = 'ͨ��' then  'һ�����ͨ��' ");
		xssqjg_sb.append("  when shjb = 'һ�����' and shzt1 = '��ͨ��' then  'һ����˲�ͨ��' ");
		xssqjg_sb.append("  when shjb = 'һ�����' and shzt1 = 'δ���' then  'δ���' ");
		xssqjg_sb.append("  when shjb = '�������' then '������' end)shjg");
		xssqjg_sb.append(" from xszz_shztb a,xszz_zzxmb b where a.xmdm=b.xmdm and  xh='"+userName+"'  ");
		
		//�ж���������
		//����Ϊѧ�����Ŀ ѧ���Ƿ�Ϊ��ǰѧ��
		xssqjg_sb.append(" and( (b.sqzq='ѧ��' and exists(select 1 from xtszb d where a.xn=d.dqxn)) ");
		//����Ϊѧ�ڵ���Ŀ ѧ���Ƿ�Ϊ��ǰѧ��
		xssqjg_sb.append(" or (b.sqzq='ѧ��' and exists(select 1 from xtszb d where a.xn=d.dqxn and a.xq=d.dqxq)) ");
		//����Ϊ��ȵ���Ŀ ѧ���Ƿ�Ϊ��ǰ���
		xssqjg_sb.append(" or (b.sqzq='���' and exists(select 1 from xtszb d where a.nd=d.dqnd))");
		//����Ϊ�����ڵ���Ŀ 	����ʱ���ǰ4λ�Ƿ��뵱ǰ������
		xssqjg_sb.append(" or (b.sqzq='������' and exists(select 1 from xtszb b where substr(a.sqsj,0,4)=b.dqnd))" );
		//����Ϊ��һ�ε���Ŀ 	����ʱ���ǰ4λ�Ƿ��뵱ǰ������
		xssqjg_sb.append(" or (b.sqzq='��һ��' and exists(select 1 from xtszb b where substr(a.sqsj,0,4)=b.dqnd)))");
		xssqjg_sb.append("order by xmdm) where r<8");
		return xssqjg_dao.getList(xssqjg_sb.toString(),new String[]{},new String[]{"xmmc","shjg","sqzqxx"});
	}
	
	/**
	 * ��ȡ�������ӡ���ϵ��ʽ(��ҳ��ʾ��Ŀ)
	 * @return List<HashMap<String,String>>
	 * author ��������
	 */
	public List<HashMap<String,String>>getSyxsxmList(){
		
		DAO dao=DAO.getInstance();
		StringBuilder sb=new StringBuilder();
		sb.append(" select xmmc,xmnr,xsfs from xg_sysz_xsxmb where  sfxs='��' order by xssx asc ");
		String[] outPut={"xmmc","xmnr","xsfs"};
		return dao.getList(sb.toString(),new String[]{},outPut);
	}
	
	
	public List<HashMap<String,String>>getSwblXssq(User user){
		DAO dao = DAO.getInstance();
		//��ȡ��Ӧģ���������Ϣ
		StringBuilder sql=new StringBuilder();
		sql.append(getMyProceeding("rcsw","yfpmd"));
		return dao.getList(sql.toString(), new String[]{user.getUserName()}, new String[]{"sqzqxx","xmmc","shjg"});
	}
	
	public HashMap<String, String> getPzxxMap(String mkmc) {
		DAO dao = DAO.getInstance();
		String sql = "select * from xg_xtwh_xssqszb where mkmc =?  ";
		return dao.getMap(sql, new String[] { mkmc }, new String[] { "mkmc",
				"tablename", "tj", "gx", "tjz", "ljfs", "ljb", "ljtj" });
	}
	
	public List<HashMap<String, String>> getTjjsbList(String mkmc) {
		DAO dao = DAO.getInstance();
		String sql = "select * from xg_xtwh_sqtjjsb where mkmc =?  ";
		return dao.getList(sql, new String[] { mkmc }, new String[] { "mkmc",
				"jszd","jszdpz" });
	}
	
    public  String getMyProceeding(String mkmc,String gnmc) {
		
		String sql= XMLReader.getMyProceeding(mkmc, gnmc);

		return sql;
	}
	
	//=================����made by ΰ�����================================
	/**
	 * ��ȡ��½�û�������ѧ������
	 * 
	 * @param user
	 * @return
	 */
	public String[] getXsrs(User user) {

		DAO dao_tj = DAO.getInstance();
		// �û���
		String userName = user.getUserName();
		// �û�����
		String userType = user.getUserType();
		// �û����ڲ���
		String userDep = user.getUserDep();

		// ���ؽ��
		String[] rs = new String[20];

		// ����ԱȨ��
		boolean fdyqx = Boolean.parseBoolean(user.getFdyQx());
		// ������Ȩ��
		boolean bzrqx = Boolean.parseBoolean(user.getBzrQx());

		boolean isXy = false;

		if ("xy".equalsIgnoreCase(userType) && !fdyqx && !bzrqx) {
			// ѧԺ�û�
			isXy = true;
		}

		String man = "0";
		String woman = "0";
		int all = 0;
		int wzxb = 0;
		String sql = "";
		String fzxsSqlnew="";

		String[] input = new String[] {};
		String xxdm = Base.xxdm;
		String zxsQuery = " and (sfzx = '��У' or sfzx is null)";
		if (Globals.XXDM_ZGDZDX.equals(xxdm)){//�й��ش���Ҫ��У��
			zxsQuery = "";
		}else if(Globals.XXDM_XBMZDX.equals(xxdm)){//�������ֻ��ע��ѧ��
			zxsQuery = " and (xjztm='��ѧ��' or xjztm is null)";
		}else if(Globals.XXDM_SXSFDX.equals(xxdm)){//����ʦ����ѧ �������� �������ѧԺ�������Ŵ���18
			zxsQuery = " and (xydm <> '18') and (sfzx = '��У' or sfzx is null) and xjztm='��ѧ��' ";
		}else if("11080".equals(xxdm)){//��������ѧԺֻ��ע��ѧ��
			zxsQuery =  " and xjztm='��ѧ��' ";
		}
		YhsjfwService yhsjfwService = new YhsjfwService();
		String yhsjfwSql = null;
		
		if (isXy) {
			yhsjfwSql = yhsjfwService.getYhsjfw(userName, "xy","a", "xydm", "bjdm");
			if(yhsjfwSql != null && !yhsjfwSql.equals("")){
				sql = "select xb,count(1) num from (select a.xh,a.xb from view_xsbfxx a where "+yhsjfwSql+" "
					+ zxsQuery
					+ ") group by xb order by xb ";
			}else{
				sql = "select xb,count(1) num from view_xsbfxx where xydm = ? "
					+ zxsQuery
					+ "group by xb order by xb ";
				input = new String[] { userDep };
			}

			fzxsSqlnew = "select xb,count(*) num from view_xsxxb where sfzx='����У' and (sfyby = '��' or sfyby is null) and xydm = '"+userDep+"' group  by xb";
		} else if("sy".equalsIgnoreCase(userType) && !fdyqx && !bzrqx){
			sql = "select xb,count(1) num from (select distinct a.* from view_xsbfxx a left join XG_XTWH_SYBJGLB b on a.bjdm = b.bjdm left join FDYXXB c on b.sydm = c.sydm where c.zgh = ? "+zxsQuery+" ) "
					 + "group by xb order by xb ";
			input = new String[] { userName };

			fzxsSqlnew = "select xb,count(*) num from (select distinct a.* from view_xsbfxx a left join XG_XTWH_SYBJGLB b on a.bjdm = b.bjdm left join FDYXXB c on b.sydm = c.sydm where c.zgh = '"+userName+"') where sfzx='����У' and (sfyby = '��' or sfyby is null) group  by xb";
		} else if (fdyqx && bzrqx) {
			yhsjfwSql = yhsjfwService.getYhsjfw(userName, "jd","a", "xydm", "bjdm");
			sql = "select xb,count(1) num from (select * from view_xsbfxx a where ";
			if(yhsjfwSql != null && !yhsjfwSql.equals("")){
				sql += yhsjfwSql + " or ";
			}
			sql += " exists (select 1 "
					+ "from bzrbbb b where a.zybj = b.bjdm and b.zgh = ?) "
					+ "or exists (select 1 from fdybjb b where a.bjdm = b.bjdm "
					+ "and b.zgh = ?)) where 1=1 "
					+ zxsQuery
					+ "group by xb order by xb";

			input = new String[] { userName, userName };

			fzxsSqlnew = "select xb,count(*) num from (select distinct a.* from view_xsbfxx a where sfzx='����У' and (sfyby = '��' or sfyby is null) ";
			fzxsSqlnew += " and exists (select 1 "
					+ "from bzrbbb b where a.zybj = b.bjdm and b.zgh = '"+userName+"') "
					+ "or exists (select 1 from fdybjb b where a.bjdm = b.bjdm "
					+ "and b.zgh = '"+userName+"')) where 1=1 ";
			fzxsSqlnew += " group by xb order by xb ";
		} else if (fdyqx) {
			yhsjfwSql = yhsjfwService.getYhsjfw(userName, "fdy","a", "xydm", "bjdm");
			sql = "select xb, count(1) num from view_xsbfxx a where ";
			if(yhsjfwSql != null && !yhsjfwSql.equals("")){
				sql += yhsjfwSql + " or ";
			}
			sql += " exists (select 1 from fdybjb b "
					+ " where a.bjdm = b.bjdm and b.zgh = ?) "
					+ zxsQuery
					+ "group by xb order by xb";

			input = new String[] { userName };
			fzxsSqlnew = "select xb,count(*) num from (select distinct a.* from view_xsbfxx a where sfzx='����У' and (sfyby = '��' or sfyby is null) ";
			fzxsSqlnew += " and exists (select 1 from fdybjb b where a.bjdm = b.bjdm "
					+ "and b.zgh = '"+userName+"')) where 1=1 ";
			fzxsSqlnew += " group by xb order by xb ";
		} else if (bzrqx) {
			yhsjfwSql = yhsjfwService.getYhsjfw(userName, "bzr","a", "xydm", "bjdm");
			sql = "select xb, count(1) num from view_xsbfxx a where ";
			if(yhsjfwSql != null && !yhsjfwSql.equals("")){
				sql += yhsjfwSql + " or ";
			}
			sql += " exists (select 1 from bzrbbb b "
					+ "where a.bjdm = b.bjdm and b.zgh = ?) "
					+ zxsQuery
					+ "group by xb order by xb";

			input = new String[] { userName };
			fzxsSqlnew = "select xb,count(*) num from (select distinct a.* from view_xsbfxx a where sfzx='����У' and (sfyby = '��' or sfyby is null) ";
			fzxsSqlnew += "and exists (select 1 "
					+ "from bzrbbb b where a.zybj = b.bjdm and b.zgh = '"+userName+"')) where 1=1 ";
			fzxsSqlnew += " group by xb order by xb ";
		} else {
			yhsjfwSql = yhsjfwService.getYhsjfw(userName, "xx","a", "xydm", "bjdm");
			if(yhsjfwSql != null && !yhsjfwSql.equals("")){
				sql = "select xb,count(1) num from (select a.xh,a.xb from view_xsbfxx a where "+yhsjfwSql+" "
				+ zxsQuery + ") group by xb order by xb ";
			}else{
				sql = "select xb,count(1) num from view_xsbfxx " + "where 1 = 1 "
				+ zxsQuery + "group by xb order by xb ";
			}
			fzxsSqlnew = "select xb,count(*) num from view_xsxxb where sfzx='����У' and (sfyby = '��' or sfyby is null) group  by xb";
		}

		List<HashMap<String, String>> list = dao_tj.getList(sql, input,
				new String[] { "xb", "num" });

		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				if ("��".equalsIgnoreCase(list.get(i).get("xb"))) {
					man = list.get(i).get("num");
				} else if ("Ů".equalsIgnoreCase(list.get(i).get("xb"))) {
					woman = list.get(i).get("num");
				} else {
					wzxb += Integer.parseInt(list.get(i).get("num"));
				}
				
				all += Integer.parseInt(list.get(i).get("num"));
			}
		}
		// ����ʦ����ѧ
		String where_10718 = "";
		if("10718".equals(xxdm)){
			where_10718 = SearchService.getSearchTjByUser(user, "t","xydm", "bjdm");
		}

		//��ѯ��У������
		String zxs = dao_tj.getOneRs("select count(*) cnt from view_xsjbxx t where 1=1 " + where_10718, new String[]{}, "cnt");
		// ����У��
		String fzxsSql = "select count(*) cnt from view_xsxxb where sfzx='����У' and (sfyby = '��' or sfyby is null)";
		if("10143".equals(xxdm)){ // ����ѧ�����ķ���У��
			fzxsSql = "select count(*) cnt from view_xsbfxx where sfzx='����У' and xjztm='��ѧ��' ";
		}else if("10718".equals(xxdm)){ // �������� �������ѧԺ�������Ŵ���18
			fzxsSql = "select count(*) cnt from view_xsbfxx t where t.xjztm='��ѧ��' and t.sfzx='����У' and t.xydm <> '18' " + where_10718;
		}else if("11080".equals(xxdm)){
			fzxsSql = " select count(*) cnt from view_xsbfxx where sfzx='����У' and xjztm='��ѧ��' ";//����������ѧԺ������У��
		}
		String fzxs = dao_tj.getOneRs(fzxsSql, new String[]{}, "cnt");
		//��ѧѧ����
		String xsxss = dao_tj.getOneRs("select count(*) cnt from view_xsbfxx t where t.xjlbdm ='02' " + where_10718, new String[]{}, "cnt");
		//��ѧѧ����
		String txxss = dao_tj.getOneRs("select count(*) cnt from view_xsbfxx t where t.xjlbdm ='03' " + where_10718, new String[]{}, "cnt");
		//��ѧ��ѧ����
		String yxjxssSql = "select count(*) cnt from view_xsxxb where xjlb in (select xjlbdm from dm_xjlb where sfyxj='0')";
		if("10143".equals(xxdm)){ // ����ѧ������ѧ��
			yxjxssSql = "select count(*) cnt from view_xsbfxx where xjztm='��ѧ��' ";
		}else if("10718".equals(xxdm)){ // �������� �������ѧԺ�������Ŵ���18
			yxjxssSql = "select count(*) cnt from view_xsbfxx t where t.xjztm='��ѧ��' and (t.sfzx = '��У' or t.sfzx is null) and t.xydm <> '18' " + where_10718;
		}else if("11080".equals(xxdm)){
			yxjxssSql = " select count(*) cnt from view_xsbfxx where sfzx='��У' and xjztm='��ѧ��' ";
		}
		String yxjxss = dao_tj.getOneRs(yxjxssSql, new String[]{}, "cnt");
		
		// ʦ������
		String sfs = "";
		// ��ʦ������
		String fsfs = "";
		// ��Ա
		String dy = "";
		// ��������
		String ssmz = "";
		if("10718".equals(xxdm)){ // �������� �������ѧԺ�������Ŵ���18
			sfs = dao_tj.getOneRs("select count(*) cnt from view_xsbfxx t where t.sfsfs='ʦ����' and t.xjztm='��ѧ��' and (t.sfzx = '��У' or t.sfzx is null) and t.xydm <> '18' " + where_10718, new String[]{}, "cnt");
			fsfs = dao_tj.getOneRs("select count(*) cnt from view_xsbfxx t where t.sfsfs='��ʦ����' and t.xjztm='��ѧ��' and (t.sfzx = '��У' or t.sfzx is null) and t.xydm <> '18' " + where_10718, new String[]{}, "cnt");
			dy = dao_tj.getOneRs("select count(*) cnt from view_xsbfxx t where t.zzmm in ('01','02') and t.xjztm='��ѧ��' and (t.sfzx = '��У' or t.sfzx is null) and t.xydm <> '18' " + where_10718, new String[]{}, "cnt");
			ssmz = dao_tj.getOneRs("select count(*) cnt from view_xsbfxx t where t.mz not in ('-8','98','97','01') and t.xjztm='��ѧ��' and (t.sfzx = '��У' or t.sfzx is null) and t.xydm <> '18' " + where_10718, new String[]{}, "cnt");
		}
		
		//��ѧ��ѧ����
		String wxjxss = dao_tj.getOneRs("select count(*) cnt from view_xsbfxx t where (t.xjlb not in (select xjlbdm from dm_xjlb where sfyxj='0') or t.xjlb is null) " + where_10718, new String[]{}, "cnt");

		List<HashMap<String, String>> fzxlist = dao_tj.getList(fzxsSqlnew, new String[]{},
				new String[] { "xb", "num" });
		String fzxMan="0";
		String fzxWoman="0";
		if (fzxlist != null && fzxlist.size() > 0) {
			for (int i = 0; i < fzxlist.size(); i++) {
				if ("��".equalsIgnoreCase(fzxlist.get(i).get("xb"))) {
					fzxMan = fzxlist.get(i).get("num");
				} else if ("Ů".equalsIgnoreCase(fzxlist.get(i).get("xb"))) {
					fzxWoman = fzxlist.get(i).get("num");
				}
			}
		}
		rs[0] = man;
		rs[1] = woman;
		rs[2] = String.valueOf(all);
		rs[3] = String.valueOf(wzxb);
		rs[4] = zxs;
		rs[5] = fzxs;
		rs[6] = xsxss;
		rs[7] = txxss;
		rs[8] = yxjxss;
		rs[9] = wxjxss;
		rs[10] = sfs;
		rs[11] = fsfs;
		rs[12] = dy;
		rs[13] = ssmz;
		rs[14] = fzxMan;
		rs[15] = fzxWoman;
		return rs;
	}
	
	/**
	 * ��ȡ��½�û�������ѧ��������У��ͳ�ƣ�
	 * @param user
	 * @return
	 */
	public List<HashMap<String, String>> getXsrsXqtj(User user) {

		DAO dao_tj = DAO.getInstance();
		// �û���
		String userName = user.getUserName();
		// �û�����
		String userType = user.getUserType();
		// �û����ڲ���
		String userDep = user.getUserDep();

		// ����ԱȨ��
		boolean fdyqx = Boolean.parseBoolean(user.getFdyQx());
		// ������Ȩ��
		boolean bzrqx = Boolean.parseBoolean(user.getBzrQx());

		boolean isXy = false;

		if ("xy".equalsIgnoreCase(userType) && !fdyqx && !bzrqx) {
			// ѧԺ�û�
			isXy = true;
		}

		String sql = "";

		String[] input = new String[] {};
		String xxdm = Base.xxdm;
		
		// �й��ش���Ҫ��У��
		String zxsQuery = Globals.XXDM_ZGDZDX.equalsIgnoreCase(xxdm) ? ""
				: " and (sfzx = '��У' or sfzx is null)";
		
		// �������ֻ��ע��ѧ��
		zxsQuery = Globals.XXDM_XBMZDX.equalsIgnoreCase(xxdm) ? " and (xjztm='��ѧ��' or xjztm is null)" : zxsQuery;
		
		// ����У��ͳ��
		String xqdm = " yxdm, ";
		
		YhsjfwService yhsjfwService = new YhsjfwService();
		String yhsjfwSql = null;
		
		if (isXy) {			
			yhsjfwSql = yhsjfwService.getYhsjfw(userName, "xy","a", "xydm", "bjdm");
			if(yhsjfwSql != null && !yhsjfwSql.equals("")){
				sql = "select "+xqdm+" xb,count(1) num from (select "+xqdm+" a.xh,a.xb from view_xsbfxx a where "+yhsjfwSql+" "
					+ zxsQuery
					+ ") group by "+xqdm+" xb order by "+xqdm+" xb ";
			}else{
				sql = "select "+xqdm+" xb,count(1) num from view_xsbfxx where xydm = ? "
					+ zxsQuery
					+ "group by "+xqdm+" xb order by "+xqdm+" xb ";
				input = new String[] { userDep };
			}			
		} else if (fdyqx && bzrqx) {
			yhsjfwSql = yhsjfwService.getYhsjfw(userName, "jd","a", "xydm", "bjdm");
			sql = "select "+xqdm+" xb,count(1) num from (select * from view_xsbfxx a where ";
			if(yhsjfwSql != null && !yhsjfwSql.equals("")){
				sql += yhsjfwSql + " or ";
			}
			sql += " exists (select 1 "
					+ "from bzrbbb b where a.bjdm = b.bjdm and b.zgh = ?) "
					+ "or exists (select 1 from fdybjb b where a.bjdm = b.bjdm "
					+ "and b.zgh = ?)) where 1=1 "
					+ zxsQuery
					+ "group by "+xqdm+" xb order by "+xqdm+" xb";

			input = new String[] { userName, userName };

		} else if (fdyqx) {
			yhsjfwSql = yhsjfwService.getYhsjfw(userName, "fdy","a", "xydm", "bjdm");
			sql = "select "+xqdm+" xb, count(1) num from view_xsbfxx a where ";
			if(yhsjfwSql != null && !yhsjfwSql.equals("")){
				sql += yhsjfwSql + " or ";
			}
			sql += " exists (select 1 from fdybjb b "
					+ " where a.bjdm = b.bjdm and b.zgh = ?) "
					+ zxsQuery
					+ "group by "+xqdm+" xb order by "+xqdm+" xb";

			input = new String[] { userName };

		} else if (bzrqx) {
			yhsjfwSql = yhsjfwService.getYhsjfw(userName, "bzr","a", "xydm", "bjdm");
			sql = "select "+xqdm+" xb, count(1) num from view_xsbfxx a where ";
			if(yhsjfwSql != null && !yhsjfwSql.equals("")){
				sql += yhsjfwSql + " or ";
			}
			sql += " exists (select 1 from bzrbbb b "
					+ "where a.bjdm = b.bjdm and b.zgh = ?) "
					+ zxsQuery
					+ "group by "+xqdm+" xb order by "+xqdm+" xb";

			input = new String[] { userName };
		} else {
			yhsjfwSql = yhsjfwService.getYhsjfw(userName, "xx","a", "xydm", "bjdm");
			if(yhsjfwSql != null && !yhsjfwSql.equals("")){
				sql = "select "+xqdm+" xb,count(1) num from (select "+xqdm+" a.xh,a.xb from view_xsbfxx a where "+yhsjfwSql+" "
				+ zxsQuery + ") group by "+xqdm+" xb order by "+xqdm+" xb ";
			}else{
				sql = "select "+xqdm+" xb,count(1) num from view_xsbfxx " + "where 1 = 1 "
				+ zxsQuery + "group by "+xqdm+" xb order by "+xqdm+" xb ";
			}	
		}

		StringBuilder rsSql = new StringBuilder();
		rsSql.append(" select a.*,(a.mannum + a.womannum + a.wzxbnum) totalnum from ( ");
		rsSql.append(" select (select xqmc from dm_zju_xq c where yxdm = c.dm) zjuxqmc, ");
		rsSql.append(" sum(mannum) mannum, ");
		rsSql.append(" sum(womannum) womannum, ");
		rsSql.append(" sum(wzxbnum) wzxbnum ");
		rsSql.append(" from ( ");
		rsSql.append(" select a.yxdm, ");
		rsSql.append(" case when a.xb='��' then a.num else 0 end mannum, ");
		rsSql.append(" case when a.xb='Ů' then a.num else 0 end womannum, ");
		rsSql.append(" case when a.xb is null then a.num else 0 end wzxbnum ");
		rsSql.append(" from ( ");
		rsSql.append(sql);
		rsSql.append(" ) a group by a.yxdm,a.xb,a.num ");
		rsSql.append(" ) a group by a.yxdm order by a.yxdm ");
		rsSql.append(" ) a ");
		
		return dao_tj.getListNotOut(rsSql.toString(), input);
	}
	
	/**
	 * @�������и�ְ����
	 * @���ߣ�׿��[����:1391]
	 * @���ڣ�2017��2��15�� 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param user
	 * @return
	 * List<HashMap<String,String>> ��������
	 */
	public List<HashMap<String, String>> getZgzrsList(User user) {

		DAO dao_tj = DAO.getInstance();
		
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		
		String sql="select * from  VIEW_XSBFXX t where (sfzx = '��У' or sfzx is null) "+searchTjByUser;
		
		StringBuilder rsSql = new StringBuilder();
		rsSql.append(" select a.*,(a.man+a.wom) sum from (select * from(select sum(man) man,sum(wom) wom,pyccmc ");
		rsSql.append(" from(select pyccmc,case when a.xb='��' then a.rs else 0 end man,case when a.xb='Ů' then a.rs else 0 end wom ");
		rsSql.append(" from( select sum(1) rs,xb,pyccmc from ");
		rsSql.append(" (select * from ("+sql+") t1 left join XG_XSXX_PYCCDMB t2 on t1.pycc=t2.pyccdm) ");
		rsSql.append(" group by xb,pyccmc ");
		rsSql.append(" )a group by a.xb,a.pyccmc,a.rs ");
		rsSql.append(" )a group by pyccmc)a where (a.pyccmc like '%��ְ%' or a.pyccmc like '%��ְ%' or a.pyccmc like '%����һ����%' or a.pyccmc like '%���˴�ר%'))a ");
		
		return dao_tj.getListNotOut(rsSql.toString(), new String[] {});
	}

	public List<HashMap<String,String>> getNjXsList(){
		StringBuilder sql = new StringBuilder();
		sql.append("select value,decode(name,'','����',name)name from(select count(*) value ,nj name from (select * from view_xsxxb where nvl(sfzx,'��У')='��У' ) group by nj order by nj)");
		return DAO.getInstance().getList(sql.toString(), new String[]{},new String[]{"value","name"});
	}

	public List<HashMap<String,String>> getXsZzmmList(){
		StringBuilder sql = new StringBuilder();
		sql.append("select case when length(name) > 8 then substr(name, 0, 8)||'...' else name  end name, value from (select nvl(zzmmmc,'δȷ��') name ,zzmm,count(*) value from ");
		sql.append("(select * from view_xsxxb where nvl(sfzx,'��У')='��У') group by zzmmmc,zzmm order by to_number(zzmm) asc) ");
		return DAO.getInstance().getList(sql.toString(), new String[]{},new String[]{"value","name"});
	}

	/**
	 * ��ȡ��ҳ��������
	 * 
	 * @param user
	 * @return
	 */
	public String[] getSydcnr(User user) {

		DAO dao = DAO.getInstance();
		
		String sql = "select dcid,substr(dcnr,0,19)dcnr,dcnr dcnrxx from xg_xtwh_sydcb where sfqy='��'";
		
		String[] rs = dao.getOneRs(sql, new String[] { }, new String[] { "dcid","dcnr","dcnrxx" });
		
		return rs;
		
	}
	
	/**
	 * ��ȡ��ҳ��������ѡ��
	 * 
	 * @param user
	 * @return
	 */
	public List<HashMap<String, String>> getSydcList(User user, String dcid) {

		DAO dao = DAO.getInstance();

		String sql = "select xxid,substr(xxnr,0,18)xxnr,xxnr xxnrxx from xg_xtwh_sydcxxb where dcid=? order by xxid";

		List<HashMap<String, String>> list = dao.getList(sql,
				new String[] { dcid }, new String[] { "xxid", "xxnr","xxnrxx" });

		return list;

	}
	
	/**
	 * ��ȡ �������Ŵ�������(ͨ��)
	 * @param user 
	 * @return  List<HashMap<String,String>>
	 * @author qlj
	 */
	public List<HashMap<String,String>>getPjpyDbsx(User user){
		
		DAO dao = DAO.getInstance();
		//�û���
		String yhm = user.getUserName();

		StringBuilder sql=new StringBuilder();
		
//		 ����ѧ��
		String pjxn = PjxtszModel.pjxtszModel.getPjxn();
		// ����ѧ��
		String pjxq = PjxtszModel.pjxtszModel.getPjxq();
		// �������
		String pjnd = PjxtszModel.pjxtszModel.getPjnd();
		// �û���
		String userName = user.getUserName();

		//sql.append("( ");
//		sql.append(" select '��������' mklx, 'δ���' dblx,xmdm,xmmc,");
//		sql.append(" to_char(WM_CONCAT(gwmc||'('||xsh||')')) shxx,to_char(WM_CONCAT(xtgwid)) gwxx,to_char(WM_CONCAT(gwjb)) jbxx from( ");
//		sql.append(" select xmdm,xmmc,pjxn,pjxq,pjnd,xsh,xtgwid,gwmc,xh gwjb from ");
//		sql.append(" ( select b.xmdm,b.xmmc, pjxn, pjxq, pjnd,count(1)xsh,xtgwid,gwmc,lcid ");
//		sql.append(" from (select * from xg_xtwh_spgwyh where spyh = '"+userName+"') a, ");
//		sql.append(" (select a.*, b.mc gwmc,c.xmmc,c.lcid ");
//		sql.append(" from xg_pjpy_pjxmshb a ");
//		sql.append(" left join xg_xtwh_spgw b on a.xtgwid = b.id ");
//		sql.append(" left join xg_pjpy_pjxmwhb c on a.xmdm=c.xmdm ");
//		sql.append(" where shzt = 'δ���') b ");
//		sql.append(" where a.spgw = b.xtgwid  ");
//		sql.append(" group by xmdm,xmmc, pjxn, pjxq, pjnd,xtgwid,gwmc,lcid)a ,");
//		sql.append(" xg_xtwh_spbz b where a.xtgwid= b.spgw and a.lcid=b.splc) ");
//		sql.append(" group by xmdm,xmmc, pjxn, pjxq, pjnd ");
		
		
		sql.append(" select '��������' mklx, 'δ���' dblx,lcid,pjxn, pjxq, pjnd, xmdm, xmmc, '����['||count(1)||']�������' shxx");
		sql.append(" from (select a.*,b.spgw, b.xmmc");
		sql.append(" from (select xmdm, pjxn, pjxq, pjnd, xh, min(shjb) shjb,lcid");
		sql.append(" from xg_view_pjpy_xmsh a");
		sql.append(" where (shzt = 'δ���' or shzt = '������')");
		sql.append(" and (exists (select 1");
		sql.append(" from xg_view_pjpy_xmsh b");
		sql.append(" where shzt = 'ͨ��'");
		sql.append(" and to_number(b.shjb) = a.shjb - 1");
		sql.append(" and b.xmdm = a.xmdm");
		sql.append(" and b.pjxn = a.pjxn");
		sql.append(" and b.pjxq = a.pjxq");
		sql.append(" and b.pjnd = a.pjnd");
		sql.append(" and b.xh = a.xh) or a.shjb = 1)");
		sql.append(" group by xmdm, pjxn, pjxq, pjnd, xh,lcid) a");
		sql.append(" left join xg_view_pjpy_xmsh b on a.xmdm = b.xmdm");
		sql.append(" and a.pjxn = b.pjxn");
		sql.append(" and a.pjxq = b.pjxq");
		sql.append(" and a.pjnd = b.pjnd");
		sql.append(" and a.xh = b.xh");
		sql.append(" and a.shjb = b.shjb");
		sql.append(" where exists (select 1");
		sql.append(" from xg_xtwh_spgwyh c");
		sql.append(" where b.spgw = c.spgw");
		sql.append(" and c.spyh = '"+userName+"'))");
		sql.append(" group by pjxn, pjxq, pjnd, xmdm, xmmc,lcid");
		
		System.out.println(sql);
		return  dao.getList(sql.toString(),new String[]{},new String[]{"mklx","xmdm","xmmc","dblx","shxx"});
	}
	
	/**
	 * ��ȡ ��������ѧ����������(ͨ��)
	 * @param user 
	 * @return  List<HashMap<String,String>>
	 * @author qlj
	 */
	public List<HashMap<String,String>>getPjpyXssq(User user){
		DAO dao=DAO.getInstance();
		// ����ѧ��
		String pjxn = PjxtszModel.pjxtszModel.getPjxn();
		// ����ѧ��
		String pjxq = PjxtszModel.pjxtszModel.getPjxq();
		// �������
		String pjnd = PjxtszModel.pjxtszModel.getPjnd();
		// �û���
		String userName = user.getUserName();

		StringBuilder sql = new StringBuilder();
		sql.append(" select a.*,shzt shjg from ( ");
		sql.append(" select sqzqxx,a.xmdm,a.xmmc,a.lcid,max(shjb) shjb, ");
		sql.append(" to_char(WM_CONCAT(a.gwmc || '(' || a.shzt || ')')) shzt  ");
		sql.append(" from (select a.xnxx||a.xqxx||a.ndxx sqzqxx,a.* from( ");
		sql.append(" select ");
		sql.append(" case when a.pjxn<>'��' then a.pjxn||'ѧ��' else '' end xnxx, ");
		sql.append(" case when a.pjxq<>'��' then a.pjxq||'ѧ��' else '' end xqxx, ");
		sql.append(" case when a.pjnd<>'��' then a.pjnd||'���' else '' end ndxx, ");
		sql.append(" a.xmdm, a.xmmc, a.shjb, a.mc gwmc, a.shzt,a.lcid from xg_view_pjpy_xmsh a  ");
		sql.append(" where xh = '"+userName+"' and (a.pjxn = '"+pjxn+"' or  ");
		sql.append("(a.pjxn = '"+pjxn+"' and a.pjxq = '"+pjxq+"') or a.pjnd = '"+pjnd+"') ");
		sql.append("order by xmdm, to_number(shjb)) a ");
		sql.append(") a group by a.sqzqxx,a.xmdm,a.xmmc,a.lcid) a ");


		return  dao.getList(sql.toString(),new String[]{},new String[]{"xmmc","shjg","sqzqxx"});
	}
	
	public List<HashMap<String,String>>getShlcJb(User user,String xmdm){
		ArrayList<HashMap<String, String>> rsList = new ArrayList<HashMap<String, String>>();
		PjpyCommDAO commDAO=new PjpyCommDAO();
		PjpyCommService service = new PjpyCommService();

		String pjxn = PjxtszModel.pjxtszModel.getPjxn();
		String pjxq = PjxtszModel.pjxtszModel.getPjxq();
		String pjnd = PjxtszModel.pjxtszModel.getPjnd();
		String pkValue = pjxn + pjxq + pjnd + xmdm;

		PjpyXmszModel xmszModel = service.getXmszForXmdm(pkValue);

		// ����ID
		String lcid = xmszModel.getLcid();
		String userName=user.getUserName();
		if (!Base.isNull(lcid)) {
			// ������Ϣ
			List<HashMap<String, String>> lcInfoList = commDAO.getLcInfo(lcid);
			// ��λ��Ϣ
			List<HashMap<String, String>> gwInfoList = commDAO.getGwInfo(userName,lcid);

			if (lcInfoList != null && lcInfoList.size() > 0) {
				for (int j = 0; j < lcInfoList.size(); j++) {
					String spgw = lcInfoList.get(j).get("spgw");
					if (gwInfoList != null && gwInfoList.size() > 0) {
						for (int k = 0; k < gwInfoList.size(); k++) {
							String yhgw = gwInfoList.get(k).get("spgw");
							if (spgw.equalsIgnoreCase(yhgw)) {
								rsList.add(gwInfoList.get(k));
							}
						}
					}

				}
			}
		}

		return rsList;
	}
	
	/**
	 * ��ȡѧ������������Ϣ
	 * author ��������
	 * @param myForm
	 * @return List<String[]>
	 * @throws Exception
	 */
	public List<HashMap<String,String>> getXswjxxInfo(User user) throws Exception {
		DAO dao=DAO.getInstance();
		StringBuilder sql = new StringBuilder();
		String xh=user.getUserName();

		// ===========================���� ������====================================
		sql.append(" select 'Υ�ʹ���' mklx,  ");
		sql.append(" 'Υ����Ϣ' xmmc, ");
		sql.append(" xh||xn||nd||sbsj pkValue, ");
		sql.append(" to_char(to_date(wjsj, 'yyyymmdd'), 'yyyy') || '��' ||  ");
		sql.append(" to_char(to_date(wjsj, 'yyyymmdd'), 'mm') ||'��' ||  ");
		sql.append(" to_char(to_date(wjsj, 'yyyymmdd'), 'dd') || '��,' sqzqxx,  ");
		sql.append(" '����'||cfyymc||'�ܵ�'||cflbmc||'����' shjg ");
		sql.append("  from view_wjcf a   where xh = '"+xh+"'  ");
		//sql.append(" and xn = (select dqxn from xtszb where rownum = 1) ");
		sql.append(" and sfqs = '��' ");
		// ===========================���� ������end====================================
		
		return dao.getList(sql.toString(), new String[] {},
				new String[] {  "mklx","xmmc", "sqzqxx", "shjg","pkValue" });
	}
}
