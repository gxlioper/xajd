package xsgzgl.xszz.jhzy.jtqkdz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.comm.CommDAO;
import xgxt.comm.search.SearchModel;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.date.DateUtils;
import xsgzgl.qgzx.glygl.QgzxGlyglService;

public class XszzJtqkdzDao extends CommDAO {
	
	private DAO dao = DAO.getInstance();

	/**
	 * ��ѯ��ͥ���������Ϣ
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public ArrayList<String[]> cxJtqkdzxx(XszzJtqkdzActionForm model) throws Exception{
		// �߼���ѯMODEL
		SearchModel searchModel = model.getSearchModel();
		// �û�����
		User user = model.getUser();
		// �߼���ѯ����
		String searchTj = SearchService.getSearchTj(searchModel);
		String[] inputV = SearchService.getTjInput(searchModel);
		
		// Ȩ�޹���
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
	
		StringBuilder sql = new StringBuilder();
		String[] colList = new String[] { "pk", "r", "xn", "xh", "xm", "nj","bjmc", "dzsj" };
		sql.append("select a.xh||a.xn pk,a.*,rownum r from (select a.* from view_xg_xszz_jhzy_jtqkdzb a where 1=1 ");
		// ====================SQLƴװ end================================
		return CommonQueryDAO.commonQuery(sql.toString(), searchTj+searchTjByUser+" order by dzsj desc,xydm,zydm,bjdm) a", inputV, colList, model);
	}
	
	/**
	 * �鿴������ͥ���������Ϣ
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> ckJtqkdzxx(XszzJtqkdzActionForm model) throws Exception{
		return dao.getMapNotOut("select xh,xn,dzsj,byxx,sjhm,tc,sfgc,sfdq,sflszn,sfdb,jthk,szssx," +
				"jtdz,jtdh,jtyb,jtrks,jtrjsr,jtnzsr,srly,yhzzqk,jtszqk,tfsjqk,cjnmqk,jtsyqk," +
				"jtqzqk,jtqtqk,xm,nj,xydm,xymc,zydm,zymc,bjdm,bjmc,xb,xz,sfzh,csrq,mzmc,zzmmmc," +
				"smc,simc,xmc from view_xg_xszz_jhzy_jtqkdzb where xh||xn= ?", new String[]{model.getPkStr()});
	}
	
	/**
	 * ɾ����ͥ���������Ϣ
	 * @param pkList
	 * @return
	 * @throws Exception
	 */
	public boolean scJtqkdzxx(List<String[]> pkList) throws Exception{ 
		return dao.checkBatchResult(dao.runBatch("delete from xg_xszz_jhzy_jtqkdzb where xh||xn = ?", pkList));
	}
	
	/**
	 * ɾ����ͥ��Ա��Ϣ
	 * @param xhList
	 * @return
	 * @throws Exception
	 */
	public boolean scJtcyxx(List<String[]> xhList) throws Exception {
		return dao.checkBatchResult(dao.runBatch("delete from xg_xszz_jhzy_jtcyqkb where xh= ?", xhList));
	}
	
	/**
	 * �����ͥ������Ϣ
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean bcJtqkdzxx(XszzJtqkdzActionForm model) throws Exception{
		return dao.runUpdate("insert into xg_xszz_jhzy_jtqkdzb (xh,xn,dzsj,sfgc,sfdq,sflszn,sfdb," +
				"jthk,szssx,jtdz,jtdh,jtyb,jtrks,jtrjsr,jtnzsr,srly,yhzzqk,jtszqk,tfsjqk,cjnmqk," +
				"jtsyqk,jtqzqk,jtqtqk,byxx,sjhm,tc) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", 
				new String[]{model.getXh(),model.getXn(),DateUtils.getYear()+DateUtils.getMonth()+DateUtils.getDayOfMonth(),model.getSfgc(),
				model.getSfdq(),model.getSflszn(),model.getSfdb(),model.getJthk(),model.getSzssx(),
				model.getJtdz(),model.getJtdh(),model.getJtyb(),model.getJtrks(),model.getJtrjsr(),model.getJtnzsr(),
				model.getSrly(),model.getYhzzqk(),model.getJtszqk(),model.getTfsjqk(),
				model.getCjnmqk(),model.getJtsyqk(),model.getJtqzqk(),model.getJtqtqk(),
				model.getByxx(),model.getSjhm(),model.getTc()});
	}
	
	/**
	 * ɾ����ͥ��Ա���
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean scJtcyxx(XszzJtqkdzActionForm model) throws Exception{
		return dao.runUpdate("delete from xg_xszz_jhzy_jtcyqkb where xh = ?", new String[]{model.getXh()});
	}
	
	/**
	 * �޸ļ�ͥ��Ա��Ϣ
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean xgJtqkdzxx(XszzJtqkdzActionForm model) throws Exception{
		return dao.runUpdate("update xg_xszz_jhzy_jtqkdzb set dzsj=?,sfgc=?,sfdq=?,sflszn=?,sfdb=?," +
				"jthk=?,szssx=?,jtdz=?,jtdh=?,jtyb=?,jtrks=?,jtrjsr=?,jtnzsr=?,srly=?,yhzzqk=?,jtszqk=?,tfsjqk=?,cjnmqk=?," +
				"jtsyqk=?,jtqzqk=?,jtqtqk=?,byxx=?,sjhm=?,tc=? where xh=? and xn = ?", new String[]{DateUtils.getYear()+DateUtils.getMonth()+DateUtils.getDayOfMonth(),model.getSfgc(),
				model.getSfdq(),model.getSflszn(),model.getSfdb(),model.getJthk(),model.getSzssx(),
				model.getJtdz(),model.getJtdh(),model.getJtyb(),model.getJtrks(),model.getJtrjsr(),model.getJtnzsr(),
				model.getSrly(),model.getYhzzqk(),model.getJtszqk(),model.getTfsjqk(),
				model.getCjnmqk(),model.getJtsyqk(),model.getJtqzqk(),model.getJtqtqk(),
				model.getByxx(),model.getSjhm(),model.getTc(),model.getXh(),model.getXn()});
	}
	
	/**
	 * ��ѯѧ����ͥ��Ա��Ϣ�б�
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> cxJtcyxxList(XszzJtqkdzActionForm model) throws Exception{
		return dao.getListNotOut("select * from xg_xszz_jhzy_jtcyqkb where xh = ?", new String[]{model.getXh()});
	}
}
