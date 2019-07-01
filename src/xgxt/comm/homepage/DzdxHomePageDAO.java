package xgxt.comm.homepage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.form.User;
import xgxt.xszz.XszzDAO;

public class DzdxHomePageDAO {

	/**
	 * �й����ʴ�ѧ ��ȡ��������������Ŀ����Ϣ
	 * 
	 * @param user,mklx(ģ������)
	 * @return List<HashMap<String,String>> author ��������
	 */
	public List<HashMap<String, String>> getZzpjDbsx(User user, HomePageModel model) {

		DAO dao = DAO.getInstance();
		
		HomePageDAO hpDAO=new  HomePageDAO();
		// �û���
		String yhm = user.getUserName();
		// �û�����
		String type = user.getUserType();
		// �û�����
		String bmdm = user.getUserDep();
		//ģ������
		String mklx = model.getMklx();
		//��ʾ��
		int maxSize = model.getMaxSize();
		// ����ԱȨ��
		boolean fdyqx = Boolean.parseBoolean(user.getFdyQx());
		// ������Ȩ��
		boolean bzrqx = Boolean.parseBoolean(user.getBzrQx());

		boolean isXy = false;

		if ("xy".equalsIgnoreCase(type) && !fdyqx && !bzrqx) {
			// ѧԺ�û�
			isXy = true;
		}
		
		StringBuilder xmlb=getXmlbQuery(mklx);
		StringBuilder sb = new StringBuilder();
		
		String gnmkmc="";
		if("zz".equalsIgnoreCase(mklx)){
			gnmkmc="ѧ������";
		}else if("pj".equalsIgnoreCase(mklx)){
			gnmkmc="��������";
		}

		// ����ѧԺ�û���Ҫ����Ŀ
		// ��Ŀ���롢ѧ�ꡢѧ�ڡ���ȡ�����ʱ��ǰ4λ
		boolean blog = false;
		
		
		if (isXy) {
			blog = true;
			// һ�����
			sb.append(" select xmdm, xn,xq,nd,substr(sqsj,0,4)sqn from xszz_shztb a,view_xsjbxx c where xmdm in ");
			sb.append(" (select xmdm from xszz_zzxmb where shjb='һ�����' and xysh='��' "+xmlb.toString()+") ");
			sb.append(" and xysh='δ���'  and c.xydm='" + bmdm + "' ");
			// �������
			sb.append(" union all ");
			sb.append(" select xmdm, xn,xq,nd,substr(sqsj,0,4)sqn from xszz_shztb a,view_xsjbxx c where xmdm in ");
			sb.append(" (select xmdm from xszz_zzxmb where shjb='�������' and xysh='��' "+xmlb.toString()+") ");
			sb.append(" and xysh='δ���'  and a.xh=c.xh  and (fdysh='ͨ��' or bzrsh='ͨ��') ");
			sb.append(" and c.xydm='" + bmdm + "' ");
			// �������
			sb.append(" union all ");
			sb.append(" select xmdm, xn,xq,nd,sqsj from xszz_shztb a,view_xsjbxx c where xmdm in ");
			sb.append(" (select xmdm from xszz_zzxmb where shjb='�������' and xysh='��' "+xmlb.toString()+") ");
			sb.append(" and xysh='δ���'  and a.xh=c.xh  and (fdysh='ͨ��' or bzrsh='ͨ��') ");
			sb.append(" and c.xydm='" + bmdm + "' ");

		}

		// ����Ա
		if (fdyqx) {
			if (blog) {
				sb.append("union all");
			}
			// һ�����
			sb.append(" select xmdm, xn,xq,nd,substr(sqsj,0,4)sqn from xszz_shztb a,view_xsjbxx c where xmdm in ");
			sb.append(" (select xmdm from xszz_zzxmb where shjb='һ�����' and fdysh='��' "+xmlb.toString()+") ");
			sb.append(" and fdysh='δ���' and a.xh=c.xh ");
			sb.append(" and exists(select 1 from fdybjb b where c.bjdm=b.bjdm and b.zgh='"
							+ yhm + "')");
			blog = true;
		}

		// ������
		if (bzrqx) {
			if (blog) {
				sb.append("union all");
			}
			// һ�����
			sb.append(" select xmdm, xn,xq,nd,substr(sqsj,0,4)sqn from xszz_shztb a,view_xsjbxx c  where xmdm in ");
			sb.append(" (select xmdm from xszz_zzxmb where shjb='һ�����' and bzrsh='��' "+xmlb.toString()+") ");
			sb.append(" and bzrsh='δ���' and a.xh=c.xh ");
			sb.append("  and exists(select 1 from  bzrbbb b where c.bjdm=b.bjdm and b.zgh='"
							+ yhm + "') ");
			blog = true;
		}

		// ����Ա��ѧУ
		if ("admin".equalsIgnoreCase(type) || "xx".equalsIgnoreCase(type)) {
			if (blog) {
				sb.append("union all");
			}
			// һ�����
			sb.append(" select xmdm, xn,xq,nd,substr(sqsj,0,4)sqn from xszz_shztb where xmdm in ");
			sb.append(" ( select xmdm from xszz_zzxmb where shjb='һ�����' and xxsh='��'  "+xmlb.toString()+") ");
			sb.append(" and xxsh='δ���' ");
			sb.append(" union all");
			// �������
			sb.append(" select xmdm, xn,xq,nd,substr(sqsj,0,4)sqn from xszz_shztb b where xmdm in ");
			sb.append(" (select xmdm from xszz_zzxmb where shjb='�������' and (bzrsh='��' and b.bzrsh='ͨ��' or xysh='��' and b.xysh='ͨ��' or  fdysh='��' and b.fdysh='ͨ��' ) "+xmlb.toString()+" )");
			sb.append("  and xxsh='δ���' ");
			sb.append("union all");
			// �������
			sb.append(" select xmdm, xn,xq,nd,substr(sqsj,0,4)sqn from xszz_shztb b where xmdm in ");
			sb.append(" (select xmdm from xszz_zzxmb where shjb='�������' and xysh='��' and b.xysh='ͨ��'  "+xmlb.toString()+" ) ");
			sb.append("  and xxsh='δ���' ");

		}
		
		
		String sql = " select * from(select * from (select rownum r,'"+gnmkmc+"' mklx,'δ���' dblx, a.xmdm, a.xsh,a.xn,a.xq,a.nd,a.sqn,b.xmmc,b.sqzq from( select xmdm,count(1) xsh, xn,xq,nd,sqn from ("
				+ sb.toString()
				+ ") group by xmdm,xn,xq,nd,sqn)a,xszz_zzxmb b where a.xmdm=b.xmdm)a where r<10 ";
		sql += " and( (a.sqzq='ѧ��' and exists(select 1 from xtszb b where a.xn=b.dqxn)) ";
		sql += " or (a.sqzq='ѧ��' and exists(select 1 from xtszb b where a.xn=b.dqxn and a.xq=b.dqxq)) ";
		sql += " or (a.sqzq='���' and exists(select 1 from xtszb b where a.nd=b.dqnd)) or (a.sqzq='������' and exists(select 1 from xtszb b where a.sqn=b.dqnd))";
		sql += " or (a.sqzq='��һ��' and exists(select 1 from xtszb b where a.sqn=b.dqnd)))";

		// ����һ������Ŀ
		for (int i = 0; i < hpDAO.getXszzxmList(user).size(); i++) {
			HashMap<String, String> hashMap = hpDAO.getXszzxmList(user).get(i);
			sql += " or xmdm= '" + hashMap.get("xmdm") + "' ";
		}
		sql+=" ) where rownum<= "+maxSize;
		
		return dao.getList(sql, new String[] {}, new String[] { "mklx", "xmdm",
				"xmmc", "xsh", "dblx" });
	}
	
	/**
	 * ����ģ�����ͻ�ȡ��Ŀ����б�
	 * @param mklx
	 * @return  List<HashMap<String, String>>
	 * time 2011.3.30
	 * author qlj
	 */
	public List<HashMap<String, String>> getXmlbList(String mklx){
		
		XszzDAO xszzDAO = new XszzDAO();
		
		List<HashMap<String, String>>xmlbList=new ArrayList<HashMap<String, String>>();
		// ��ȡ����������ģ�����Ŀ���
		if ("zz".equalsIgnoreCase(mklx)) {
			// ��ȡ ѧ��������Ŀ���
			xmlbList=xszzDAO.getSelectList("xmlb_zz");
		} else if ("pj".equalsIgnoreCase(mklx)) {
			// ��ȡ ����������Ŀ���
			xmlbList=xszzDAO.getSelectList("xmlb_pj");
		}
		
		return xmlbList;
	}
	
	public StringBuilder getXmlbQuery(String mklx){
		
		StringBuilder query=new StringBuilder();
		
		List<HashMap<String, String>>xmlbList=getXmlbList(mklx);
		
		//�ж��Ƿ�����Ŀ�б�,û�з��ؿ�
		if(xmlbList.size()>0){
			query.append(" and ( ");
			for(int i=0;i<xmlbList.size();i++){
				
				HashMap<String,String>xmlbMap=xmlbList.get(i);
				
				if(i==0){
					query.append(" xmlb='"+xmlbMap.get("en")+"' ");
				}else{
					query.append(" or  xmlb='"+xmlbMap.get("en")+"' ");
				}
			}
			query.append(" ) "); 
		}
		
		return query;
	}
}
