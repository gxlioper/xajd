package xgxt.szdw.bjlh.fdyzp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommDAO;
import xgxt.comm.search.SearchService;
import xgxt.utils.CommonQueryDAO;

public class BjlhFdyzpDAO extends CommDAO {
	DAO dao = DAO.getInstance();

	// ��ø���Ա������δ�е�ѧ��
	public List<HashMap<String, String>> getAddXnList() {

		List<HashMap<String, String>> allList = Base.getXnndList();
		List<HashMap<String, String>> list = dao.getList("select xn from xg_szdw_bjlh_fdyzpb", new String[] {},new String[] { "xn" });
		
		List<HashMap<String,String>> result = new ArrayList<HashMap<String,String>>();

		for (int i = 0; i < allList.size(); i++) {
			HashMap<String, String> allMap = allList.get(i);
			boolean res = true; 
			for (int j = 0; j < list.size(); j++) {
				if (allMap.get("xn").equalsIgnoreCase(list.get(j).get("xn"))) {
					res= false;
					break;
				}
			}
			if(res)
			{
				result.add(allMap);
			}
		}
		return result;
	}

	// ����Ա���������
	public List<String[]> getTableList(BjlhFdyzpForm myForm,
			HttpServletRequest request) throws Exception {

		// �߼���ѯ����
		String searchTj = SearchService.getSearchTj(myForm.getSearchModel());
		// �߼���ѯ����ֵ
		String[] inputV = SearchService.getTjInput(myForm.getSearchModel());

		StringBuffer sql = new StringBuffer(
				"select a.*,rownum r,case when b.num > 0 then '1' else '0' end kfxg from xg_szdw_bjlh_fdyzpb a "
						+ "left join (select c.zpbid,count(*) num from xg_szdw_bjlh_fdyzpxxb a "
						+ "left join xg_szdw_bjlh_fdyzpxmb b on a.xmid = b.xmid "
						+ "left join xg_szdw_bjlh_fdyzpb c on b.zpbid = c.zpbid group by c.zpbid) b on a.zpbid = b.zpbid where 1=1 ");
		String[] output = new String[] { "zpbid", "xn", "mc", "rq", "sfqy",	"kfxg" };
		return CommonQueryDAO.commonQuery(sql.toString(), searchTj, inputV,
				output, myForm);
	}
	
	//����Ա������Ϣ��ѯ
	public List<String[]> getQueryTableList(BjlhFdyzpForm myForm,
			HttpServletRequest request) throws Exception {
		// �߼���ѯ����
		String searchTj = SearchService.getSearchTj(myForm.getSearchModel());
		// �߼���ѯ����ֵ
		String[] inputV = SearchService.getTjInput(myForm.getSearchModel());
/*		String sql = "select rownum r, a.* from " +
				"(select distinct a.yhm fdy,a.sftj,(select szbm from yhb where a.yhm =xm) xydm," +
				"(select distinct xymc from view_njxyzybj where xydm =" +
				"(select szbm from yhb where a.yhm =xm)) xy,a.tjsj,b.zpbid,b.xn,b.mc " +
				"from XG_SZDW_BJLH_FDYZPXXB a left join XG_SZDW_BJLH_FDYZPB b on a.zpbid = b.zpbid) a where 1=1 ";*/
		String sql="select rownum r, a.* from (select a.*,d.tjsj,case when yhm is not null and sftj='��' then '��' else '��' end sftj " +
				"from (select * from (select distinct(a.zgh),b.xm,b.szbm bmdm,b.bmmc from fdybjb a left join view_fdyxx b on a.zgh = b.zgh) a,xg_szdw_bjlh_fdyzpb b) a " +
				"left join (select distinct (b.yhm),b.zpbid,b.sftj,b.tjsj from XG_SZDW_BJLH_FDYZPXXB b) d on a.zgh = d.yhm and a.zpbid = d.zpbid order by a.xn,a.zgh) a where 1=1 ";
		
		//Ȩ�޿���
		SearchService searchService=new SearchService();
		String searchTjQx = searchService.getSearchTjByUser(request, "","bmdm", null); 	//ѧԺ�û�
		HttpSession session = request.getSession();
		if("true".equalsIgnoreCase(session.getAttribute("fdyQx").toString())){			//����Ա�û�
			searchTjQx = " and zgh = '"+session.getAttribute("userName").toString()+"' ";
		}
		String[] output = new String[] { "zpbid","xn","zgh","xm","bmmc","sftj","tjsj" };
		return CommonQueryDAO
				.commonQuery(sql, searchTj+searchTjQx, inputV, output, myForm);
	}

	// ���ݸ���Ա������IDɾ������Ա����������
	public boolean deleteAllById(String zpbid) throws Exception {
		boolean flag = false;
		
		String sql = "delete from xg_szdw_bjlh_fdyzpb where zpbid = ?";		
		flag = dao.runUpdate(sql, new String[] { zpbid });
		if (flag){
			sql = "delete from xg_szdw_bjlh_fdyzpxmb where zpbid = ?";
			flag = dao.runUpdate(sql, new String[] { zpbid });
		}
		return flag;
	}

	// �ı丨��Ա����������״̬
	public boolean changeSfqy(String zpbid,String sfqy) throws Exception {
		boolean flag = false;
		String sql = "update xg_szdw_bjlh_fdyzpb set sfqy = ? where zpbid = ?";
		flag = dao.runUpdate(sql, new String[]{sfqy,zpbid});
		return flag;
	}

	// ����������ID�����Ŀ������
	public List<HashMap<String, String>> getXmListByZpdId(String zpbid)
			throws Exception {
		String sql = "select * from xg_szdw_bjlh_fdyzpxmb where zpbid = ?";
		String[] outputValue = new String[] { "zpbid", "xmid", "khxm", "xmlx",
				"bz" };
		return dao.getList(sql, new String[] { zpbid }, outputValue);
	}

	// ����������ID�����������Ϣ
	public HashMap<String, String> getFdyzpbById(String zpbid) throws Exception {
		String sql = "select * from xg_szdw_bjlh_fdyzpb where zpbid = ?";
		return dao.getMap(sql, new String[] { zpbid }, new String[] { "xn",
				"mc" });
	}

	// �������¸���Ա������ �����Ӧ��������Ŀ��
	public boolean saveFdyzpbAndFdyzpxmb(BjlhFdyzpForm myForm) throws Exception {
		boolean flag = false;
		String zpbid = myForm.getZpbid();
		String xn = myForm.getXn();
		String mc = myForm.getMc();
		String[] xmbz = myForm.getXmbz();
		String[] khxm = myForm.getKhxm();
		String[] xmlx = myForm.getXmlx();
		//���ӻ���ʱ zpbidΪnull��""
		if (zpbid == null || zpbid.equalsIgnoreCase("")) {
			String sql = "insert into xg_szdw_bjlh_fdyzpb (xn,mc,rq) values(?,?,to_char(sysdate,'yyyy-mm-dd'))";
			flag = dao.runUpdate(sql, new String[] { xn, mc });
			sql = "select zpbid from xg_szdw_bjlh_fdyzpb where xn = ?";
			zpbid = dao.getOneRs(sql, new String[] { xn }, "zpbid");
		} else {
			String sql = "update xg_szdw_bjlh_fdyzpb set mc = ?,rq = to_char(sysdate,'yyyy-mm-dd') where zpbid = ?";
			flag = dao.runUpdate(sql, new String[] { mc, zpbid });
		}
		String sql = "delete from xg_szdw_bjlh_fdyzpxmb where zpbid = ?";
		flag = dao.runUpdate(sql, new String[] { zpbid });
		sql = "insert into xg_szdw_bjlh_fdyzpxmb(zpbid,khxm,xmlx,bz) values(?,?,?,?)";
		if(khxm!=null){
			for (int i = 0; i < khxm.length; i++) {
				//������ĿΪ��ֵ�򲻴�
				if (!khxm[i].equalsIgnoreCase("")) {
					flag = dao.insert(sql, new String[] { zpbid, khxm[i], xmlx[i],
							xmbz[i] });
				}
			}
		}
		return flag;
	}

	// ���Ƹ���Ա������
	public boolean copyFdyzpById(BjlhFdyzpForm myForm, String zpbid)
			throws Exception {
		boolean flag = false;
		String sql = "select * from xg_szdw_bjlh_fdyzpxmb where zpbid =? ";
		List<String[]> xmList = dao.rsToVator(sql, new String[] { zpbid },
				new String[] { "khxm", "xmlx", "bz" });

		String[] khxm = new String[xmList.size()];
		String[] xmlx = new String[xmList.size()];
		String[] xmbz = new String[xmList.size()];
		for (int i = 0; i < xmList.size(); i++) {
			khxm[i] = xmList.get(i)[0];
			xmlx[i] = xmList.get(i)[1];
			xmbz[i] = xmList.get(i)[2];
		}
		myForm.setKhxm(khxm);
		myForm.setXmlx(xmlx);
		myForm.setXmbz(xmbz);
		myForm.setZpbid("");
		flag = saveFdyzpbAndFdyzpxmb(myForm);
		return flag;
	}

	// ���渨��Ա������Ϣ��
	public boolean saveFdyzpxxb(BjlhFdyzpForm myForm,String sftj) throws Exception {
		boolean flag = false;
		String yhm = myForm.getYhm();
		String[] xmid = myForm.getXmid();
		String[] wcqk = myForm.getWcqk();
		String zpbid = myForm.getZpbid();
		if (zpbid != null) {
			String sql = "delete from XG_SZDW_BJLH_FDYZPXXB where zpbid = ? and yhm = ?";
			flag = dao.runUpdate(sql, new String[] { zpbid, yhm});
		}
		for (int i = 0; i < xmid.length; i++) {
			String sql = "insert into XG_SZDW_BJLH_FDYZPXXB(xmid,zpbid,yhm,wcqk,sftj,tjsj) values(?,?,?,?,?,to_char(sysdate,'yyyy-mm-dd'))";
			flag = dao.runUpdate(sql, new String[] { xmid[i], zpbid, yhm,
					wcqk[i],sftj });
		}
		return flag;
	}

	// ��ò������ñ��е�Ĭ������
	public HashMap<String, String> getMrsz() {
		String sql = "select a.*,(select zpbid from XG_SZDW_BJLH_FDYZPB where xn = a.xn and sfqy = '��') zpbid from XG_SZDW_BJLH_CSSZB a ";
		return dao.getMap(sql, new String[] {}, new String[]{"zpbid","xn","khsfqd","khkssj","khjssj"});
	}

	// ��ø�ѧ��ĸ���Ա������Ϣ��
	public List<HashMap<String, String>> getFdyzpxxb(String zpbid,String yhm) {
		String sql = "select * from (select a.xmid,a.zpbid,a.khxm," +
				"decode(a.xmlx, '0', '��ѡ��', '1', '��ѡ��', '2', '�ӷ���') xmlx, a.bz, b.yhm, b.wcqk, b.sftj " +
				"from XG_SZDW_BJLH_FDYZPXMB a left join XG_SZDW_BJLH_FDYZPXXB b on a.xmid = b.xmid " +
				"and b.yhm = ?) where zpbid = ? order by xmid ";
		
		String[] outputValue = new String[] { "xmid", "zpbid", "khxm", "xmlx",
				"wcqk", "bz", "sftj" };
		return dao.getList(sql, new String[] { yhm,zpbid }, outputValue);
	}
	
	public HashMap<String,String> getFdyxx(String yhm,String zpbid){
		String sql = "select a.zgh zgh,a.xm,a.szbm bmdm,a.bmmc,b.xn from view_fdyxx a,xg_szdw_bjlh_fdyzpb b where a.zgh = ? and zpbid =? ";
		return dao.getMap(sql, new String[] { yhm,zpbid }, new String[]{"zgh","xm","bmdm","bmmc","xn"});
	}

}
