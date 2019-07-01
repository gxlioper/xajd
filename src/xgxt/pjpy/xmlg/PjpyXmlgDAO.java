package xgxt.pjpy.xmlg;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.MakeQuery;
import xgxt.utils.String.StringUtils;

public class PjpyXmlgDAO {

	DAO dao = DAO.getInstance();
	
	/**
	 * ��ѯ����б�
	 * @return
	 */
	public List<HashMap<String, String>> getShList() {
		return dao.getChkList(3);
	}
	
	/**
	 * �÷������ڲ�ѯ��ͷ�б�
	 * 
	 * @param en
	 * @param cn
	 * @return
	 */
	public List<HashMap<String, String>> getTitleList(String[] en, String[] cn) {
		return dao.arrayToList(en, cn);
	}
	
	/**
	 * �ۺ����ʲ�����ѯ���
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> queryZhszcpResult(PjpyXmlgModel model) throws Exception{
		HashMap<String, String> rs = getXyZhszcppmfs(model);
		
		String sql ="";
		if ("zydm".equalsIgnoreCase(rs.get("pmfs"))) {
			sql = "select pk,xh,xn,(select xqmc from xqdzb b where a.xq=b.xqdm) xq,dcj,zcj,tcj,xm,bjmc,xqzf,round((xnzf/2),2) xnzf,xqpm,xnpm,rownum r from (select a.xh||a.xn||a.xq pk,a.*,rank() over (partition by xn,xq,xydm,zydm order by xqzf desc nulls last) xqpm,dense_rank() over (partition by xn,xydm,zydm order by xnzf desc nulls last) xnpm  from (select xh,xm,xn,xq,xydm,zydm,bjdm,xb,nj,xymc,zymc,bjmc,dcj,zcj,tcj,dzscj+zzscj+tzscj xqzf,(sum(dzscj) over (partition by xh,xn) + sum(zzscj) over (partition by xh,xn) + sum(tzscj) over (partition by xh,xn)) xnzf from (select a.xh,a.xn,a.xq,a.dcj,a.zcj,a.tcj,b.xm,b.xb,b.xydm,b.zydm,b.bjdm,b.nj,b.xymc,b.zymc,b.bjmc,(dcj*(select dybl from zjcm_zhszcpblszb)/100) dzscj,(zcj*(select zybl from zjcm_zhszcpblszb)/100) zzscj,(tcj*(select tybl from zjcm_zhszcpblszb)/100) tzscj from zhszcp a left join view_xsjbxx b on a.xh=b.xh))a)a ";
		} else if ("bjdm".equalsIgnoreCase(rs.get("pmfs"))) {
			sql = "select pk,xh,xn,(select xqmc from xqdzb b where a.xq=b.xqdm) xq,dcj,zcj,tcj,xm,bjmc,xqzf,round((xnzf/2),2) xnzf,xqpm,xnpm,rownum r from (select a.xh||a.xn||a.xq pk,a.*,rank() over (partition by xn,xq,xydm,zydm,bjdm order by xqzf desc nulls last) xqpm,dense_rank() over (partition by xn,xydm,zydm,bjdm order by xnzf desc nulls last) xnpm  from (select xh,xm,xn,xq,xydm,zydm,bjdm,xb,nj,xymc,zymc,bjmc,dcj,zcj,tcj,dzscj+zzscj+tzscj xqzf,(sum(dzscj) over (partition by xh,xn) + sum(zzscj) over (partition by xh,xn) + sum(tzscj) over (partition by xh,xn)) xnzf from (select a.xh,a.xn,a.xq,a.dcj,a.zcj,a.tcj,b.xm,b.xb,b.xydm,b.zydm,b.bjdm,b.nj,b.xymc,b.zymc,b.bjmc,(dcj*(select dybl from zjcm_zhszcpblszb)/100) dzscj,(zcj*(select zybl from zjcm_zhszcpblszb)/100) zzscj,(tcj*(select tybl from zjcm_zhszcpblszb)/100) tzscj from zhszcp a left join view_xsjbxx b on a.xh=b.xh))a)a ";
		} else {
			sql = "select xh||xn||xq pk,rownum r,xh,xn,(select xqmc from xqdzb b where a.xq=b.xqdm) " +
					"xq,dcj,zcj,tcj,xm,bjmc,'' xqzf,'' xnzf,'' xqpm,'' xnpm from view_zhszcp a";
		}
		
		String[] queryList = new String[] { "nj", "xydm", "zydm", "bjdm", "xn", "xq"};
		String[] likeList = new String[]{"xh", "xm"};
		MakeQuery queryObject = new MakeQuery();
		queryObject.makeQuery(queryList, likeList, model);
		
		String[] colList = new String[] { "pk", "r", "xn","xq", "xh",
				"xm", "bjmc","dcj", "zcj", "tcj", "xqzf","xqpm", "xnzf","xnpm"};
		return CommonQueryDAO.commonQuery(sql, queryObject.getQueryString()
				, queryObject.getInputList(), colList, model);
	}
	
	/**
	 * ɾ���۲���Ϣ
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean deleteZhszcpxx(PjpyXmlgModel model) throws Exception{
		if (model.getKeys() == null) {
			return false;
		}
		String[] sqlArr = new String[model.getKeys().length];
		for (int i=0;i<sqlArr.length;i++) {
			StringBuffer sql = new StringBuffer("delete from zhszcp where xh||xn||xq='");
			sql.append(model.getKeys()[i]);
			sql.append("'");
			sqlArr[i] = sql.toString();
		}
		int[] res = dao.runBatch(sqlArr);
		return dao.checkBatch(res);
	}
	
	/**
	 * �����ۺ����ʲ����ɼ�
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean addZhszcpxx(PjpyXmlgModel model) throws Exception {
		return dao
				.runUpdate(
						"insert into zhszcp(xh,xn,xq,dcj,zcj,tcj,nd) values (?,?,?,?,?,?,?)",
						new String[] { model.getXh(), model.getXn(),
								model.getXq(), model.getDcj(), model.getZcj(),
								model.getTcj(),Base.currNd });
	}
	
	/**
	 * �޸��ۺ����ʲ����ɼ�
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean updateZhszcpxx(PjpyXmlgModel model) throws Exception{
		return dao
				.runUpdate(
						"update zhszcp set xn=?,xq=?,dcj=?,zcj=?,tcj=? where xh||xn||xq=?",
						new String[] { model.getXn(), model.getXq(),
								model.getDcj(), model.getZcj(), model.getTcj(),
								model.getPkValue() });
	}
	
	/**
	 * ��ѯ�����ۺ����ʲ�����Ϣ
	 * @param model
	 * @return
	 */
	public HashMap<String, String> queryZhszcpOnexx(PjpyXmlgModel model) {
		String xydm = dao.getOneRs("select xydm from view_zhszcp where xh||xn||xq=? ", new String[]{model.getPkValue()},"xydm");
		PjpyXmlgModel queryModel = new PjpyXmlgModel();
		queryModel.setXydm(xydm);
		HashMap<String, String> rs = getXyZhszcppmfs(queryModel);
		
		String sql ="";
		if ("zydm".equalsIgnoreCase(rs.get("pmfs"))) {
			sql = "select pk,xh,xn,xq,dcj,zcj,tcj,xm,bjmc,xb,xymc,zymc,nj,xqpm,xnpm,xqzf,round((xnzf/2),2) xnzf from (select a.xh||a.xn||a.xq pk,a.*,rank() over (partition by xn,xq,xydm,zydm order by xqzf desc nulls last) xqpm,dense_rank() over (partition by xn,xydm,zydm order by xnzf desc nulls last) xnpm  from (select xh,xm,xn,xq,xydm,zydm,bjdm,xb,nj,xymc,zymc,bjmc,dcj,zcj,tcj,dzscj+zzscj+tzscj xqzf,(sum(dzscj) over (partition by xh,xn) + sum(zzscj) over (partition by xh,xn) + sum(tzscj) over (partition by xh,xn)) xnzf from (select a.xh,a.xn,a.xq,a.dcj,a.zcj,a.tcj,b.xm,b.xb,b.xydm,b.zydm,b.bjdm,b.nj,b.xymc,b.zymc,b.bjmc,(dcj*(select dybl from zjcm_zhszcpblszb)/100) dzscj,(zcj*(select zybl from zjcm_zhszcpblszb)/100) zzscj,(tcj*(select tybl from zjcm_zhszcpblszb)/100) tzscj from zhszcp a left join view_xsjbxx b on a.xh=b.xh))a)a where xh||xn||xq=?";
		} else if ("bjdm".equalsIgnoreCase(rs.get("pmfs"))) {
			sql = "select pk,xh,xn,xq,dcj,zcj,tcj,xm,bjmc,xb,xymc,zymc,nj,xqpm,xnpm,xqzf,round((xnzf/2),2) xnzf from (select a.xh||a.xn||a.xq pk,a.*,rank() over (partition by xn,xq,xydm,zydm,bjdm order by xqzf desc nulls last) xqpm,dense_rank() over (partition by xn,xydm,zydm,bjdm order by xnzf desc nulls last) xnpm  from (select xh,xm,xn,xq,xydm,zydm,bjdm,xb,nj,xymc,zymc,bjmc,dcj,zcj,tcj,dzscj+zzscj+tzscj xqzf,(sum(dzscj) over (partition by xh,xn) + sum(zzscj) over (partition by xh,xn) + sum(tzscj) over (partition by xh,xn)) xnzf from (select a.xh,a.xn,a.xq,a.dcj,a.zcj,a.tcj,b.xm,b.xb,b.xydm,b.zydm,b.bjdm,b.nj,b.xymc,b.zymc,b.bjmc,(dcj*(select dybl from zjcm_zhszcpblszb)/100) dzscj,(zcj*(select zybl from zjcm_zhszcpblszb)/100) zzscj,(tcj*(select tybl from zjcm_zhszcpblszb)/100) tzscj from zhszcp a left join view_xsjbxx b on a.xh=b.xh))a)a where xh||xn||xq=?";
		} else {
			sql = "select xh,xn,xb,xm,xymc,zymc,bjmc,nj,dcj,zcj,tcj,xn,xq,'' xqpm,'' xnpm from view_zhszcp where xh||xn||xq=?";
		}
		return dao
				.getMapNotOut(
						sql,
						new String[] {model.getPkValue()});
	}
	
	/**
	 * ѧԺ�û������۲�������ʽ��Ϣ
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean saveZhszcpPmfs(PjpyXmlgModel model) throws Exception{
		boolean result = dao.runUpdate("delete from pjpy_xmlg_xyzhszpmb where xydm=?", new String[]{model.getXydm()});
		if (result) {
			return dao.runUpdate(
					"insert into pjpy_xmlg_xyzhszpmb(xydm,pmfs) values (?,?)",
					new String[] { model.getXydm(), model.getPmfs() });
		}
		return false;
	}
	
	/**
	 * ѧԺ�û���ѯ�۲�������ʽ
	 * @param model
	 * @return
	 */
	public HashMap<String, String> getXyZhszcppmfs(PjpyXmlgModel model) {
		return dao.getMapNotOut(
				"select xydm,pmfs from pjpy_xmlg_xyzhszpmb where xydm=?",
				new String[] { model.getXydm() });
	}
	
	/**
	 * ��ѧ��,�����ƺű������÷�ҳ�˵�
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getPageCard(String userType) throws Exception{
		String[] en = new String[]{"jxj", "rych"};
		String[] cn = new String[]{"��ѧ���������", "�����ƺű�������"};
		if("xy".equalsIgnoreCase(userType)){
			cn = new String[]{"��ѧ����������", "�����ƺ���������"};
		}
		return dao.arrayToList(en, cn);
	}
	
	/**
	 * ��ѯ�������ñ�ͷ���
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> queryBlszxx(PjpyXmlgModel model) throws Exception{
		String sql ="select a.*,rownum r from (select a.xn||a.dm||a.bmdm||a.nj||a.key pk,a.xn,a.nj,a.dm,(case when key='jxj' then (select mc from xmlg_jxjdmb b where a.dm=b.dm) else (select mc from xmlg_rychdmb b where a.dm=b.dm) end) " +
				"mc,a.bmlb,a.bmdm,(case when bmlb='xy' then a.bmdm when bmlb='zy' then (select xydm from view_njxyzybj b where a.bmdm=b.zydm and rownum=1) else (select xydm from view_njxyzybj b where a.bmdm=b.bjdm and rownum=1) end) xydm,((case when bmlb='zy' then a.bmdm when bmlb='bj' then (select zydm from view_njxyzybj b where a.bmdm=b.bjdm and rownum=1) else '' end)) zydm,(case when bmlb='bj' then a.bmdm else '' end) bjdm,(case when bmlb='xy' then (select xymc from view_njxyzybj b " +
				"where a.bmdm=b.xydm and rownum=1) when bmlb='zy' then (select zymc from view_njxyzybj" +
				" b where a.bmdm=b.zydm and rownum=1) else (select bjmc from view_njxyzybj b where a.bmdm=b.bjdm and rownum=1)" +
				" end) bmmc,bl,bmrs,(nvl(case when instr(to_char(((nvl(bmrs,0)*nvl(bl,0))/100)),'.',1,1)=1 then '0'||to_char(((nvl(bmrs,0)*nvl(bl,0))/100)) else to_char(((nvl(bmrs,0)*nvl(bl,0))/100)) end, '0')) tzrs,key from xmlg_csblszb a) a";
		
		String[] queryList = new String[] { "nj", "xydm", "zydm", "bjdm", "xn", "bmlb", "dm", "key"};
		String[] likeList = new String[]{};
		MakeQuery queryObject = new MakeQuery();
		queryObject.makeQuery(queryList, likeList, model);
		
		String[] colList = new String[] { "pk", "r", "xn","mc", "bmmc", "nj","bmrs", "bl", "tzrs"};
		return CommonQueryDAO.commonQuery(sql, queryObject.getQueryString()
				, queryObject.getInputList(), colList, model);
		
	}
	
	/**
	 * ��ѯ��ѧ���б�(�Ƿ�����Ϊ��)
	 * @param model
	 * @return
	 */
	public List<HashMap<String, String>> getJxjList(PjpyXmlgModel model) {
		String sql = "select dm,mc from xmlg_jxjdmb where sfqy=?";
		if (!StringUtils.isNull(model.getLbdm())) {
			sql += " and lbdm='";
			sql += model.getLbdm();
			sql += "'";
		}
		sql += " order by dm";
		return dao.getList(sql, new String[] { model.getSfqy() }, new String[] {
				"dm", "mc" });
	}
	/**
	 * ��ѯ��ѧ�������ƺ�����
	 * @param model
	 * @return
	 */
	public String getJxjRychMc(String type,PjpyXmlgModel model) {
		String dm = model.getJxjdm();
		String sql = "select mc from xmlg_jxjdmb where dm=?";
		if("rych".equalsIgnoreCase(type)){
			sql = "select mc from xmlg_rychdmb where dm=?";
			dm = model.rychdm;
		}
		return dao.getOneRs(sql, new String []{dm},"mc");
	}
	/**
	 * ��ѯ��ѧ���б�(�Ƿ�����Ϊ��) ����ط�����DWR�����õ�
	 * @param model
	 * @return
	 */
	public List<HashMap<String, String>> getJxjListByajax(String lbdm) {
		String sql = "select dm,mc from xmlg_jxjdmb where sfqy='��' and lbdm=? order by dm";
		return dao.getList(sql, new String[] {lbdm }, new String[] {
				"dm", "mc" });
	}
	
	/**
	 * ��ѯ�����ƺ��б�
	 * @return
	 */
	public List<HashMap<String, String>> getRychList() {
		return dao.getList("select dm,mc from xmlg_rychdmb order by dm", new String[] {},
				new String[] { "dm", "mc" });
	}
	
	/**
	 * ��ѯ�����ƺ�����б�
	 * @param model
	 * @return
	 */
	public List<HashMap<String, String>> getJxjlbList() {
		return dao.getList("select dm,mc from xmlg_jxjlbdmb order by dm", new String[] {},
				new String[] { "dm", "mc" });
	}
	/**
	 * ��ʼ����ѧ�������ƺű�����������
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean initData(PjpyXmlgModel model) throws Exception {
		return dao.runProcuder("{call XMLG_INITBASEDATA (?,?)}", new String[] {
				model.getXn(), model.getKey() });
	}
	
	/**
	 * �������ý�ѧ�����
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean modiJxjblszxx(PjpyXmlgModel model) throws Exception {
		if (model.getXydmList() == null || model.getNjdmList() == null) {
			return false;
		}

		String[] nj = model.getNjdmList();

		//ѧԺ��������
		String[] sqlArr = new String[model.getXydmList().length];
		//רҵ�� �༶��������
		String[] sqlArrs = new String[model.getXydmList().length];
		
		for (int i = 0; i < model.getXydmList().length; i++) {
			StringBuffer sql = new StringBuffer("update xmlg_csblszb set bl='");
			sql.append(model.getBl());
			sql.append("' where bmlb='xy' and xn='");
			sql.append(model.getXn());
			sql.append("' and dm='");
			sql.append(model.getDm());
			sql.append("' and bmdm='");
			sql.append(model.getXydmList()[i]);
			sql.append("' and key='");
			sql.append(model.getKey());
			sql.append("' and nj in ('");
			for (int j = 0; j < nj.length; j++) {
				sql.append(nj[j]);
				sql.append("','");
			}
			sqlArr[i] = sql.toString()
					.substring(0, sql.toString().length() - 2)
					+ ")";
			
			sqlArrs[i] = appendZybjblszxx(model, model.getXydmList()[i]);
		}
		int[] result = dao.runBatch(sqlArr);//ִ��ѧԺ��ѧ��������ò���
		boolean res = dao.checkBatch(result);
		if (res) {
			dao.runBatch(sqlArrs);//ִ��רҵ���༶��ѧ��������ò���
		}
		return res;
	}
	
	public String appendZybjblszxx(PjpyXmlgModel model, String xydm) {
		if (model.getXydmList() == null || model.getNjdmList() == null) {
			return "";
		}

		String[] nj = model.getNjdmList();

			StringBuffer sql = new StringBuffer("update xmlg_csblszb set bl='");
			sql.append(model.getBl());
			sql.append("' where (bmlb='zy' and xn='");
			sql.append(model.getXn());
			sql.append("' and dm='");
			sql.append(model.getDm());
			sql.append("' and bmdm in (select distinct zydm from view_njxyzybj where xydm='");
			sql.append(xydm);
			sql.append("') and key='");
			sql.append(model.getKey());
			sql.append("' and nj in ('");
			for (int j = 0; j < nj.length; j++) {
				sql.append(nj[j]);
				if (j<nj.length-1) {
					sql.append("','");
				}
			}
			sql.append("')) or (bmlb='bj' and xn='");
			sql.append(model.getXn());
			sql.append("' and dm='");
			sql.append(model.getDm());
			sql.append("' and bmdm in (select distinct bjdm from view_njxyzybj where xydm='");
			sql.append(xydm);
			sql.append("') and key='");
			sql.append(model.getKey());
			sql.append("' and nj in ('");
			for (int j = 0; j < nj.length; j++) {
				sql.append(nj[j]);
				if (j<nj.length-1) {
					sql.append("','");
				}
			}
			sql.append("'))");
			return sql.toString();
	}
	
	/**
	 * ��ѯ�������õ�����Ϣ
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> queryBlszxxOne(PjpyXmlgModel model)
			throws Exception {
		return dao
				.getMapNotOut(
						"select xn,bmrs,bl,bmlb,key,((nvl(bmrs,0)*nvl(bl,0))/100) tzrs,bmdm,(case when bmlb='xy' then (select xymc from view_njxyzybj b "
								+ "where a.bmdm=b.xydm and rownum=1) when bmlb='zy' then (select zymc from view_njxyzybj"
								+ " b where a.bmdm=b.zydm and rownum=1) else (select bjmc from view_njxyzybj b where a.bmdm=b.bjdm and rownum=1)"
								+ " end) bmmc,nj,dm,(case when key='jxj' then (select mc from xmlg_jxjdmb b where a.dm=b.dm) else (select mc from xmlg_rychdmb b where a.dm=b.dm) end) mc from xmlg_csblszb a where xn||dm||bmdm||nj||key=?",
						new String[] {model.getPkValue()});
	}
	
	/**
	 * �޸ı������õ�����Ϣ
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean modiBlszxxOne(PjpyXmlgModel model) throws Exception {
		boolean result = dao.runUpdate(
				"update xmlg_csblszb set bl=? where xn||dm||bmdm||nj||key=?",
				new String[] { model.getBl(), model.getPkValue() });
		// ����ط�Ҫע�⣺����޸ĵ���ѧԺ����ô���ѧԺ�����רҵ�Ͱ༶�ı�����Ҫ�䡣����޸ĵ���רҵ����ô���רҵ��������а༶����ҲҪ�䡣
		if (result) {
			HashMap<String, String> rs = queryBlszxxOne(model);
			if ("xy".equalsIgnoreCase(rs.get("bmlb"))) {// �޸�ѧԺ�����רҵ���༶����
				dao
						.runUpdate(
								"update xmlg_csblszb a set bl=? where (bmlb='zy' and xn=? and dm=? and nj=? and key=? and bmdm in (select distinct zydm from view_njxyzybj b where xydm=?)) or (bmlb='bj' and xn=? and dm=? and nj=? and key=? and bmdm in (select distinct bjdm from view_njxyzybj b where xydm=?))",
								new String[] { model.getBl(), rs.get("xn"),
										rs.get("dm"), rs.get("nj"),
										rs.get("key"), rs.get("bmdm"),
										rs.get("xn"), rs.get("dm"),
										rs.get("nj"), rs.get("key"),
										rs.get("bmdm") });
			} else if ("zy".equalsIgnoreCase(rs.get("bmlb"))) {// �޸�רҵ����İ༶����
				dao
						.runUpdate(
								"update xmlg_csblszb a set bl=? where bmlb='bj' and xn=? and dm=? and nj=? and key=? and bmdm in (select distinct bjdm from view_njxyzybj b where zydm=?)",
								new String[] { model.getBl(), rs.get("xn"),
										rs.get("dm"), rs.get("nj"),
										rs.get("key"), rs.get("bmdm") });
			}
		}
		return result;
	}
	/**
	 * ��ѯ����ѧԺ���ñ�ͷ���
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> queryXyJxjRsSz(PjpyXmlgModel model) throws Exception{
		String sql ="select a.*,rownum r,(select je from xmlg_jxjdmb b where b.dm=a.dm)je from (select a.xn||a.dm||a.bmdm||a.nj||a.key pk,a.xn,a.nj,a.dm,(case when key='jxj' then (select mc from xmlg_jxjdmb b where a.dm=b.dm) else (select mc from xmlg_rychdmb b where a.dm=b.dm) end) " +
				"mc,a.bmlb,a.bmdm,(case when bmlb='xy' then a.bmdm when bmlb='zy' then (select xydm from view_njxyzybj b where a.bmdm=b.zydm and rownum=1) else (select xydm from view_njxyzybj b where a.bmdm=b.bjdm and rownum=1) end) xydm,((case when bmlb='zy' then a.bmdm when bmlb='bj' then (select zydm from view_njxyzybj b where a.bmdm=b.bjdm and rownum=1) else '' end)) zydm,(case when bmlb='bj' then a.bmdm else '' end) bjdm,(case when bmlb='xy' then (select xymc from view_njxyzybj b " +
				"where a.bmdm=b.xydm and rownum=1) when bmlb='zy' then (select zymc from view_njxyzybj" +
				" b where a.bmdm=b.zydm and rownum=1) else (select bjmc from view_njxyzybj b where a.bmdm=b.bjdm and rownum=1)" +
				" end) bmmc,bl,bmrs,(nvl(case when instr(to_char(((nvl(bmrs,0)*nvl(bl,0))/100)),'.',1,1)=1 then '0'||to_char(((nvl(bmrs,0)*nvl(bl,0))/100)) else to_char(((nvl(bmrs,0)*nvl(bl,0))/100)) end, '0')) jyrs,tzrs,key from xmlg_csblszb a) a";
		
		String[] queryList = new String[] { "nj", "xydm", "zydm", "bjdm", "xn", "bmlb", "dm", "key"};
		String[] likeList = new String[]{};
		MakeQuery queryObject = new MakeQuery();
		queryObject.makeQuery(queryList, likeList, model);
		String[] colList = new String[] { "pk", "r", "xn","mc","je","bmmc", "nj","bmrs", "bl", "jyrs","tzrs"};
		return CommonQueryDAO.commonQueryNotFy("", ""
				, queryObject.getInputList(), colList, sql+queryObject.getQueryString());
		
	}
	public boolean XyJxjRsSzSave(PjpyXmlgModel model) throws SQLException{
		String[]keys = model.getKeys();
		String[]tzrs = model.getTzrs();
		String[] updateSql    = new String[keys.length];
        for(int i=0;i<keys.length;i++){
        	updateSql[i] = "update xmlg_csblszb set tzrs='"+DealString.toGBK(tzrs[i])+"' where xn||dm||bmdm||nj||key='"+DealString.toGBK(keys[i])+"' ";
        }		
		int[] array = null;
		array = dao.runBatch(updateSql);
		boolean doFlag = dao.checkBatch(array);	
		return doFlag;
	}
	public boolean tzRsFsSzSave(PjpyXmlgModel model) throws SQLException{
		String bmlb = model.getBmlb();
		//String key  = model.getKey();
		String xydm = model.getXydm();
		String xn   = model.getXn();
		String[] updateSql    = new String[3];
        for(int i=0;i<updateSql.length;i++){
        	updateSql[i] = "delete from xyfpjxjfs where xn='"+xn+"' and bmdm='"+xydm+"' and fpfs='"+bmlb+"' ";
        	updateSql[i] = "update  xmlg_csblszb set tzrs='' where  xn='"+xn+"' and bmdm='"+xydm+"' and bmlb='"+bmlb+"' ";
        	updateSql[i] = "insert into xyfpjxjfs(xn,bmdm,fpfs) values('"+xn+"','"+xydm+"','"+bmlb+"') ";
        }		
		int[] array = null;
		array = dao.runBatch(updateSql);
		boolean doFlag = dao.checkBatch(array);	
		return doFlag;
	}
	/**
	 * 
	 * @param xzfs ���Ʒ�ʽ�� 1:��������,2:������� ��
	 * @param yxzje Ժϵ����ѧ���ܽ��
	 * @param pkValue ��ѧ���������������xn||dm||bmdm||nj||key��ֵ
	 * @param tzfs ������ʽ��רҵ��zy,�༶��bj��
	 * @param jyvalue ��������
	 * @param tzvalue ��������
	 * @return 
	 */
	public String[] retXyRsTzXzFs(String xzfs,String yxzje,String tzfs,String jyvalue,String tzvalue,String xydm,String xn,String jestr,String tzrsstr,String pkValues){
		String[] retValue = new String[]{"true","true"};						
		if("1".equalsIgnoreCase(xzfs)){
			float  jynum = Float.parseFloat(Base.isNull(jyvalue)?"0":jyvalue);
			float  tznum = Float.parseFloat(Base.isNull(tzvalue)?"0":tzvalue);
			if(tznum>Math.round(jynum)){
				retValue[0]="false";
			}
		}else{
			
//			String zjenumTem=getXyZje(xydm, xn);
			float zjenum= Float.parseFloat(Base.isNull(yxzje)?"0":yxzje);//��ѧԺ�ܽ��
			float zjesum=0;//ǰ̨�����������ϼ�
			String[]jesArarry = jestr.split("!!");
			String[]tzrsArarry = tzrsstr.split("!!");
		    for(int i=0;i<jesArarry.length;i++){
		    	zjesum+= Float.parseFloat(jesArarry[i])* Float.parseFloat(tzrsArarry[i]);
		    }
			StringBuffer sql = new StringBuffer();
			
			sql.append("select sum(nvl(tzrs,0)*je)jenum from ( ");
			sql.append("select a.*,(select nvl(je,0) from xmlg_jxjdmb b where b.dm=a.dm)je,(case when a.bmlb='xy' then a.bmdm when a.bmlb='zy'  ");
			sql.append("then (select xydm from view_njxyzybj where zydm=a.bmdm and rownum=1) when a.bmlb='bj' then (select xydm from view_njxyzybj where  ");
			sql.append("bjdm=a.bmdm and rownum=1) end) xydm,(case  when a.bmlb = 'zy' then a.bmdm  when a.bmlb='bj' then (select zydm from view_njxyzybj where bjdm = a.bmdm and rownum = 1)end)zydm from xmlg_csblszb a ) where xydm='"+xydm+"' and bmlb='"+tzfs+"' and xn='"+xn+"' and  ");
			sql.append("key='jxj' and xn||dm||bmdm||nj||key not in ("+pkValues+")  ");			
		    String qtjenum = dao.getOneRs(sql.toString(),new String[]{},"jenum");
			float jenum =  Float.parseFloat(Base.isNull(qtjenum)?"0":qtjenum);
//			float zjenum =  Float.parseFloat(Base.isNull(zjenumV)?"0":zjenumV);
			if((zjesum+jenum)>zjenum){
					retValue[1]="false";
			}
		}
		return retValue;
	}
	public String getXyZje(String xydm,String xn){
		StringBuffer sql = new StringBuffer();		
		sql.append(" select sum(nvl(round(round(bmrs*bl,6)/100,6)*je,0))zjenum from ( select a.*,(select nvl(je,0) from xmlg_jxjdmb b where b.dm=a.dm)je,(case when bmlb='xy' then a.bmdm when a.bmlb='zy' then (select xydm from view_njxyzybj ");
		sql.append(" where zydm=a.bmdm and rownum=1) else (select xydm from view_njxyzybj where bjdm=a.bmdm and rownum=1) end) xydm from xmlg_csblszb a ");
		sql.append(" ) where xydm='"+xydm+"' and bmlb='bj' and xn='"+xn+"' and key='jxj' ");
		return dao.getOneRs(sql.toString(), new String[]{}, "zjenum");
	}
	public boolean tzfsUpdateSave(PjpyXmlgModel model) throws Exception{
		String xydm =model.getXydm();
		String bmlb = model.getBmlb();
		String xn   = model.getXn();
		String[] sql=new String[3];
		sql[0] = " delete from xyfpjxjfs where bmdm='"+xydm+"' and xn='"+xn+"' ";
		sql[1] = " insert into xyfpjxjfs(xn,bmdm,fpfs) values('"+xn+"','"+xydm+"','"+bmlb+"')   ";
		sql[2] = " update xmlg_csblszb set tzrs='' where bmdm in(select distinct zydm dm from view_njxyzybj where xydm='"+xydm+"' union select distinct bjdm dm from view_njxyzybj where xydm='"+xydm+"') and bmlb<>'xy'  and xn='"+xn+"' ";
		int[] array = null;
		array = dao.runBatch(sql);
		boolean doFlag = dao.checkBatch(array);	
		return doFlag;
	}
	/**
	 * ��ѯ��ѧ��ѧԺ������ʽ
	 * @return
	 */
	public String  getXyTzFs(String xn,String xydm) {
		return dao.getOneRs("select fpfs from xyfpjxjfs where bmdm=? and xn=? ",new String[]{xydm,xn},"fpfs");
	}
	/**
	 * ��ѯ��ѧ��������Ʒ�ʽ
	 * @return
	 */
	public HashMap<String, String> getJxjrstzxzrs() {
		return dao.getMapNotOut("select xzfs from xmlg_xytzrsfsxzb",
				new String[] {});
	}
	
	/**
	 * ���潱ѧ�������������Ʒ�ʽ
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean saveJxjrstzrs(PjpyXmlgModel model) throws Exception {
		boolean result = dao.runUpdate("delete from xmlg_xytzrsfsxzb",
				new String[] {});
		if (result) {
			result = dao.runUpdate(
					"insert into xmlg_xytzrsfsxzb(xzfs) values (?)",
					new String[] { model.getXzfs() });
		}
		return result;
	}
	
	/**
	 * ��ѯѧ���ĳɼ���Ϣ
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getStuCjList(String xh, String xn) throws Exception {
		String sql = "select (case when kcxz like '%����%' then '#ffdead' else '' end) bgcolor,xn,(select b.xqmc from xqdzb b where a.xq=b.xqdm) xq,kcxz,kcmc,cj,bkcj,cxcj from cjb a where xn=? and xh=? order by xq,kcxz";
		return dao.rsToVator(sql, new String[] { xn, 
				xh }, new String[] {  "xq", "kcxz",
				"kcmc", "cj", "bkcj", "cxcj" });
	}
	
	/**
	 * ��ѯѧ��������Ϣ
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getStuWjcfList(String xh, String xn) throws Exception {
		String sql = "select xn,(select xqmc from xqdzb b where a.xq=b.xqdm) xq,cflbmc,cfyymc,cfsj,cfwh,wjsj,bz from view_wjcf a where cfsj is not null and cfwh is not null and xn=? and xh=?";
		return dao.rsToVator(sql, new String[] { xn, 
				xh }, new String[]{ "xq", "cflbmc", "cfyymc", "cfsj", "cfwh"});
	}
	
	/**
	 * ��ѯѧ���ۺ����ʲ�����Ϣ ͨ��ѧ�ţ�ѧ���ѯ��
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getStuZhszcpList(PjpyXmlgModel model)
			throws Exception {
		String sql = "select (select xqmc from xqdzb b where a.xq=b.xqdm) xq,dcj,zcj,tcj,xqpm,xnpm from view_xmlg_zhszcppm a where xn=? and xh=?";
		return dao.rsToVator(sql,
				new String[] { model.getXn(), model.getXh() }, new String[] {
						"xq", "dcj", "zcj", "tcj", "xqpm", "xnpm" });
	}
	
	/**
	 * ��ѯѧ�����뽱ѧ��ĸ���������Ϣ
	 * @param model
	 * @return
	 */
	public HashMap<String, String> getStuJxjsqfzxx(PjpyXmlgModel model) {
		return dao
				.getMapNotOut(
						"select xh,xn,drzw,wysp,jsjsp,jlqk,sqly from xsjxjsqfzxxb where xh=? and xn=?",
						new String[] { model.getXh(), model.getXn() });
	}
	
	/**
	 * ���ӽ�ѧ��������Ϣ�����븨����Ϣ
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean addJxjsqxx(PjpyXmlgModel model) throws Exception {
		String[] sql = {
				"delete from xsjxjb where xh='" + model.getXh() + "' and xn='"
						+ model.getXn()
						+ "' and (xysh != 'ͨ��' or xxsh != 'ͨ��' or xysh is null or xxsh is null)",
				"insert into xsjxjb(xh,xn,nd,xq,jxjdm,je) values ('"
						+ model.getXh() + "','" + model.getXn() + "','"
						+ model.getNd() + "','" + model.getXq() + "','"
						+ model.getJxjdm() + "','" + model.getJe() + "')" };
// String count = dao
//				.getOneRs(
//						"select count(*) num from xsjxjb where xh||xn||jxjdm=?",
//						new String[] { model.getXh() + model.getXn()
//								+ model.getJxjdm() }, "num");
//		boolean result = false;
//		if (!StringUtils.isNull(count) && !"0".equalsIgnoreCase(count)) {
//			result = dao
//					.runUpdate(
//							"update xsjxjb set xh=?,xn=?,nd=?,xq=?,jxjdm=?,je=?,xysh='δ���',xxsh='δ���' where xh=? and xn=? and jxjdm=?",
//							new String[] { model.getXh(), model.getXn(),
//									model.getNd(), model.getXq(),
//									model.getJxjdm(), model.getJe() });
//		} else {
//			boolean result = dao
//					.runUpdate(
//							"insert into xsjxjb(xh,xn,nd,xq,jxjdm,je) values (?,?,?,?,?,?)",
//							new String[] { model.getXh(), model.getXn(),
//									model.getNd(), model.getXq(),
//									model.getJxjdm(), model.getJe() });
	//	}
		int[] result = dao.runBatch(sql);
		boolean flag = dao.checkBatch(result);
		//����ɹ������޸����븨����Ϣ
		if (flag) {
			flag = addJxjsqfzxx(model);
		}
		return flag;
	}
	
	/**
	 * ���ӽ�ѧ�����븨����Ϣ ��ɾ������
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean addJxjsqfzxx(PjpyXmlgModel model) throws Exception {

		StringBuffer insert = new StringBuffer(
				"insert into xsjxjsqfzxxb (xh,xn,wysp,jsjsp,drzw,jlqk,sqly) values ('");
		insert.append(model.getXh());
		insert.append("','");
		insert.append(model.getXn());
		insert.append("','");
		insert.append(StringUtils.isNull(model.getWysp()) ? "��" : model
				.getWysp());
		insert.append("','");
		insert.append(StringUtils.isNull(model.getJsjsp()) ? "��" : model
				.getJsjsp());
		insert.append("','");
		insert.append(StringUtils.isNull(model.getDrzw()) ? "��" : model
				.getDrzw());
		insert.append("','");
		insert.append(StringUtils.isNull(model.getJlqk()) ? "��" : model
				.getJlqk());
		insert.append("','");
		insert.append(StringUtils.isNull(model.getSqly()) ? "��" : model
				.getSqly());
		insert.append("')");
		String[] sqlArr = new String[] {
				"delete from xsjxjsqfzxxb where xh='" + model.getXh()
						+ "' and xn='" + model.getXn() + "'",
				insert.toString()};
		return dao.checkBatch(dao.runBatch(sqlArr)); 
	}
	
	/**
	 * ��������ǰ���ѧ���Ƿ����������ѧ��
	 * @param xh
	 * @param xn
	 * @return
	 * @throws Exception
	 */
	public String[] checkStusqExists(String xh, String xn) throws Exception {
		// ����Ƿ��������������ѧ��
		String[] result = new String[]{"true", "true", "true"};
		String num = dao.getOneRs(
				"select count(xh) num from xsjxjb where xh=? and xn=? and (xysh='ͨ��' or xxsh='ͨ��')",
				new String[] { xh, xn }, "num");
		int count = StringUtils.isNull(num) ? 0 : Integer.parseInt(num);
		
		result[0] = count > 0 ? "false" : "true";
		if (count <= 0) {
			// ����Ƿ񴦷���Ϣ
			
			String[] cjcfList = dao
					.getOneRs(
							"select (select count(*) from cjb where xh=? and xn=? and kcxz like '%����%' and cj<60) cj,(select count(xh) num2 from wjcfb where xn=? and xh=?) wj from dual",
							new String[] { xh,xn, xn, xh }, new String[]{ "cj", "wj"});
			
			if (cjcfList != null && !StringUtils.isNull(cjcfList[0])
					&& !"0".equalsIgnoreCase(cjcfList[0])) {
				result[1] = "false";
			}
			if (cjcfList != null && !StringUtils.isNull(cjcfList[1])
					&& !"0".equalsIgnoreCase(cjcfList[1])) {
				result[2] = "false";
			}
		}
		return result;
	} 
	
	/**
	 * ѧԺ��ѯ��ѧ��������
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> queryJxjsqxxByxy(PjpyXmlgModel model) throws Exception{
		String sql ="select a.*,rownum r,'' xnpm from (select xh||xn||jxjdm pk,(case when xxsh='ͨ��' then 'disabled' else '' end) dis,xh,xm,xb,bjmc,xn," +
		"jxjmc,jxjdm,xysh,xxsh,xydm,zydm,bjdm,lbdm,nj from view_xsjxjb a) a ";
		String[] queryList = new String[] { "nj", "xydm", "zydm", "bjdm", "xn", "lbdm", "jxjdm"};
		String[] likeList = new String[]{"xh", "xm"};
		MakeQuery queryObject = new MakeQuery();
		queryObject.makeQuery(queryList, likeList, model);
		
		String sqlApp = "";
		if (!StringUtils.isNull(queryObject.getQueryString())) {
			if (!StringUtils.isNull(model.getSh())) {
				sqlApp = " and xysh='";
				sqlApp += model.getSh();
				sqlApp += "'";
			}
		} else {
			if (!StringUtils.isNull(model.getSh())) {
				sqlApp = "where xysh='";
				sqlApp += model.getSh();
				sqlApp += "'";
			}
		}
		
		//����������SQL�������ٶ���,���Ե������۲�����
		List<String[]> zhcpList = queryZhszcppmByjxj(model);
		
		String[] colList = new String[] { "pk", "dis", "r", "xn", "xh", "xm", "bjmc", "jxjmc",  "xnpm","xysh", "xxsh"};
		List<String[]> rs = CommonQueryDAO.commonQuery(sql, queryObject.getQueryString() + sqlApp
				, queryObject.getInputList(), colList, model);
		//���۲�����д�뵽��ѯ�б���
		rs = overrideJxjList(rs, zhcpList);
		return rs;
		
	}
	
	//��ѯ�ۺϲ��������б�
	public List<String[]> queryZhszcppmByjxj(PjpyXmlgModel model)
			throws Exception {
		String sql = "select xh,xn,xnpm from view_xmlg_zhszcppm";
		String[] queryList = new String[] { "nj", "xydm", "zydm", "bjdm", "xn" };
		String[] likeList = new String[] { "xh", "xm" };
		MakeQuery queryObject = new MakeQuery();
		queryObject.makeQuery(queryList, likeList, model);
		String[] colList = new String[] { "xh", "xn", "xnpm" };
		return dao.rsToVator(sql + queryObject.getQueryString(), queryObject
				.getInputList(), colList);
	}
	
	//���ۺϲ���������Ϣ��д����ѯ�б���
	public List<String[]> overrideJxjList(List<String[]> jxjList,
			List<String[]> zcpmList) {
		if (zcpmList != null) {
			for (String[] s : zcpmList) {
				if (s != null && s.length == 3) {
					for (int i = 0; i < jxjList.size(); i++) {
						if (jxjList.get(i) != null
								&& jxjList.get(i).length == 11) {
							if (s[0] != null && s[1] != null
									&& s[0].equalsIgnoreCase(jxjList.get(i)[4])
									&& s[1].equalsIgnoreCase(jxjList.get(i)[3])) {
								jxjList.get(i)[8] = s[2];
							}
						}
					}
				}
			}
		}
		return jxjList;
	}

		/**
	 * ѧУ��ѯ��ѧ��������
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> queryJxjsqxxByxx(PjpyXmlgModel model, boolean xysh) throws Exception{
		String sql ="select a.*,rownum r,'' xnpm from (select xh||xn||jxjdm pk,'' dis,xh,xm,xb,bjmc,xn," +
				"jxjmc,jxjdm,xysh,xxsh,xydm,zydm,bjdm,lbdm,nj from view_xsjxjb a) a";
		
		String[] queryList = new String[] { "nj", "xydm", "zydm", "bjdm", "xn", "lbdm", "jxjdm"};
		String[] likeList = new String[]{"xh", "xm"};
		MakeQuery queryObject = new MakeQuery();
		queryObject.makeQuery(queryList, likeList, model);
		
		String sqlApp = "";
		if (!StringUtils.isNull(queryObject.getQueryString())) {
			if (!StringUtils.isNull(model.getSh())) {
				sqlApp = " and xxsh='";
				sqlApp += model.getSh();
				sqlApp += "'";
			}
		} else {
			if (!StringUtils.isNull(model.getSh())) {
				sqlApp = "where xxsh='";
				sqlApp += model.getSh();
				sqlApp += "'";
			}
		}
		if (xysh) {
			sqlApp += " and xysh='ͨ��'";
		}
		String[] colList = new String[] { "pk", "dis", "r", "xn", "xh", "xm", "bjmc", "jxjmc", "xnpm", "xysh", "xxsh"};
		
//		����������SQL�������ٶ���,���Ե������۲�����
		List<String[]> zhcpList = queryZhszcppmByjxj(model);
		List<String[]> rs = CommonQueryDAO.commonQuery(sql, queryObject.getQueryString() + sqlApp
				, queryObject.getInputList(), colList, model);
		rs = overrideJxjList(rs, zhcpList);
		return rs;
		
	}
	
	/**
	 * ɾ����ѧ����������
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean deleteJxjsqxx(PjpyXmlgModel model)throws Exception {
		if (model.getKeys() == null) {
			return false;
		}
		String[] sqlArr = new String[model.getKeys().length];
		String[] sqls = new String[model.getKeys().length];
		for (int i=0;i<model.getKeys().length;i++) {
			StringBuffer sql = new StringBuffer("delete xsjxjb where xh||xn||jxjdm='");
			sql.append(model.getKeys()[i]);
			sql.append("'");
			sqlArr[i] = sql.toString();
			StringBuffer sql1 = new StringBuffer("delete xsjxjsqfzxxb where xh||xn=(select xh||xn from xsjxjb where xh||xn||jxjdm='");
			sql1.append(model.getKeys()[i]);
			sql1.append("')");
			sqls[i] = sql1.toString();
		}
		String[] sqlList = dao.unionArray(sqlArr, sqls);
		int[] result = dao.runBatch(sqlList);
		return dao.checkBatch(result);
	}
	
	/**
	 * ��ѯ��ѧ����������Ϣ
	 * @param model
	 * @return
	 */
	public HashMap<String, String> viewJxjsqxx(PjpyXmlgModel model) {
		return dao
				.getMapNotOut(
						"select a.xh,a.xm,a.xb,a.nj,a.xymc,a.zymc,a.bjmc,(select lbdm from xmlg_jxjdmb b where a.jxjdm=b.dm) lbdm,a.jxjdm,(select je from xmlg_jxjdmb b where a.jxjdm=b.dm) je,a.xn,nd,b.wysp,b.jsjsp,b.drzw,b.jlqk,b.sqly,(select count(xh) from view_xsxxb b where a.bjdm=b.bjdm) bjrs from view_xsjxjb a left join xsjxjsqfzxxb b on a.xh=b.xh and a.xn=b.xn where a.xh||a.xn||jxjdm=?",
						new String[] { model.getPkValue() });
	}
	
	/**
	 * �޸Ľ�ѧ��������Ϣ,���븨����Ϣ
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean updateJxjsqxx(PjpyXmlgModel model) throws Exception {
		boolean result = dao.runUpdate(
				"update xsjxjb set xn=?,jxjdm=? where xh||xn||jxjdm=?",
				new String[] { model.getXn(), model.getJxjdm(),
						model.getPkValue() }); 
//		����ɹ������޸����븨����Ϣ
		if (result) {
			result = addJxjsqfzxx(model);
		}
		return result;
	}
	
	/**
	 * ѧ����ѯ��ѧ��������Ϣ
	 * @param model
	 * @return
	 */
	public List<String[]> queryStujxjsqxx(PjpyXmlgModel model) {
		return dao
				.rsToVator(
						"select xh||xn||jxjdm pk,(case when xysh='ͨ��' or xxsh='ͨ��' then 'disabled' else '' end) dis,rownum r,xn,jxjmc,(select je from xmlg_jxjdmb b where a.jxjdm=b.dm) je,xysh,xxsh from view_xsjxjb a where xh=?",
						new String[] { model.getXh() },
						new String[] { "pk", "dis", "r", "xn", "jxjmc", "je",
								"xysh", "xxsh" });
	}
	
	/**
	 * ��ѯ�������ñ�ͷ���
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> queryRychRssz(PjpyXmlgModel model) throws Exception{
		String sql ="select a.pk,a.xn,a.mc,a.bmmc,a.nj,a.bmrs,a.bl,nvl(case when instr(to_char(a.jyrs),'.',1,1)=1 then '0'||to_char(a.jyrs) else to_char(a.jyrs) end, '0') jyrs,a.tzrs,rownum r from (select a.xn||a.dm||a.bmdm||a.nj||a.key pk,a.xn,a.nj,a.dm,(case when key='jxj' then (select mc from xmlg_jxjdmb b where a.dm=b.dm) else (select mc from xmlg_rychdmb b where a.dm=b.dm) end) " +
				"mc,a.bmlb,a.bmdm,(case when bmlb='xy' then a.bmdm when bmlb='zy' then (select xydm from view_njxyzybj b where a.bmdm=b.zydm and rownum=1) else (select xydm from view_njxyzybj b where a.bmdm=b.bjdm and rownum=1) end) xydm,((case when bmlb='zy' then a.bmdm when bmlb='bj' then (select zydm from view_njxyzybj b where a.bmdm=b.bjdm and rownum=1) else '' end)) zydm,(case when bmlb='bj' then a.bmdm else '' end) bjdm,(case when bmlb='xy' then (select xymc from view_njxyzybj b " +
				"where a.bmdm=b.xydm and rownum=1) when bmlb='zy' then (select zymc from view_njxyzybj" +
				" b where a.bmdm=b.zydm and rownum=1) else (select bjmc from view_njxyzybj b where a.bmdm=b.bjdm and rownum=1)" +
				" end) bmmc,bl,bmrs,((nvl(bmrs,0)*nvl(bl,0))/100) jyrs,key,tzrs from xmlg_csblszb a) a";
		String[] queryList = new String[] { "nj", "xydm", "zydm", "bjdm", "xn", "bmlb", "dm", "key"};
		String[] likeList = new String[]{};
		MakeQuery queryObject = new MakeQuery();
		queryObject.makeQuery(queryList, likeList, model);
		String[] colList = new String[] { "pk", "r", "xn","mc", "bmmc", "nj","bmrs", "bl","jyrs","tzrs"};
		return CommonQueryDAO.commonQueryNotFy("", "", queryObject.getInputList(), colList, sql+queryObject.getQueryString());
		
		
	}
	
	/**
	 * ѧԺ��ѯ������ѧ�������Ϣ
	 * @param pkValue
	 * @return
	 */
	public HashMap<String, String> queryJxjshDgxxByxy(String pkValue) {
		return dao.getMapNotOut("select xh,xn,xb,xm,xymc,zymc,bjmc,nj,nd,xq,jxjmc,lbmc,je,wysp,drzw,jsjsp," +
				"jlqk,sqly,xysh sh,xxsh,xyshyj yj from view_xsjxjb where xh||xn||jxjdm=?", new String[]{pkValue});
	}
	
	/**
	 * ѧУ��ѯ������ѧ�������Ϣ
	 * @param pkValue
	 * @return
	 */
	public HashMap<String, String> queryJxjshDgxxByxx(String pkValue) {
		return dao.getMapNotOut("select xh,xn,xb,xm,xymc,zymc,bjmc,nj,nd,xq,jxjmc,lbmc,je,wysp,drzw,jsjsp," +
				"jlqk,sqly,xysh,xxsh sh,xyshyj,xxshyj yj from view_xsjxjb where xh||xn||jxjdm=?", new String[]{pkValue});
	}
	
	/**
	 * ѧԺ��˽�ѧ�������ƺ���Ϣ
	 * @param key ��ѧ�� jxj�������ƺ� rych
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean saveJxjshxxByxy(PjpyXmlgModel model, String key, boolean sh) throws Exception{
		
		if (model.getKeys() == null) {
			return false;
		}
		
		String[] sqlArr = new String[model.getKeys().length];
		if ("jxj".equalsIgnoreCase(key)) {
			for (int i = 0; i < model.getKeys().length; i++) {
				StringBuffer sql = new StringBuffer("update xsjxjb set xysh='");
				sql.append(model.getSh());
				sql.append("',xyshyj='");
				sql.append((StringUtils.isNull(model.getYj()) && "ͨ��"
						.equalsIgnoreCase(model.getSh())) ? "ͬ������"
						: (StringUtils.isNull(model.getYj()) ? "" : model
								.getYj()));
				sql.append("' where xh||xn||jxjdm='");
				sql.append(model.getKeys()[i]);
				sql.append("'");
				if (sh) {
					sql.append(" and xysh!='");
					sql.append(model.getSh());
					sql.append("'");
				}
				sqlArr[i] = sql.toString();
			}
		} else {
			for (int i = 0; i < model.getKeys().length; i++) {
				StringBuffer sql = new StringBuffer("update xsrychb set xysh='");
				sql.append(model.getSh());
				sql.append("',xyyj='");
				sql.append((StringUtils.isNull(model.getYj()) && "ͨ��"
						.equalsIgnoreCase(model.getSh())) ? "ͬ������"
						: (StringUtils.isNull(model.getYj()) ? "" : model
								.getYj()));
				sql.append("' where xh||xn||rychdm='");
				sql.append(model.getKeys()[i]);
				sql.append("'");
				if (sh) {
					sql.append(" and xysh!='");
					sql.append(model.getSh());
					sql.append("'");
				}
				sqlArr[i] = sql.toString();
			}
		}
		
		int[] result = dao.runBatch(sqlArr);
		return dao.checkBatch(result);
	}
	
	/** 
	 * ѧԺ��˽�ѧ�������ƺ���Ϣ  ��ͨ�����
	 * @param key ��ѧ�� jxj�������ƺ� rych
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean saveJxjshxxByxy(PjpyXmlgModel model, String key) throws Exception{
		
		if (model.getKeys() == null) {
			return false;
		}
		
		String[] sqlArr = new String[model.getKeys().length];
		if ("jxj".equalsIgnoreCase(key)) {
			for (int i = 0; i < model.getKeys().length; i++) {
				StringBuffer sql = new StringBuffer("update xsjxjb set xysh='");
				sql.append(model.getSh());
				sql.append("' where xh||xn||jxjdm='");
				sql.append(model.getKeys()[i]);
				sql.append("'");
				sqlArr[i] = sql.toString();
			}
		} else {
			for (int i = 0; i < model.getKeys().length; i++) {
				StringBuffer sql = new StringBuffer("update xsrychb set xysh='");
				sql.append(model.getSh());
				sql.append("' where xh||xn||rychdm='");
				sql.append(model.getKeys()[i]);
				sql.append("'");
				sqlArr[i] = sql.toString();
			}
		}
		
		int[] result = dao.runBatch(sqlArr);
		return dao.checkBatch(result);
	}
	
	/**
	 * ѧУ��˽�ѧ��,�����ƺ���Ϣ
	 * @param key ��ѧ�� jxj�������ƺ� rych
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean saveJxjshxxByxx(PjpyXmlgModel model, String key, boolean sh) throws Exception {
		if (model.getKeys() == null) {
			return false;
		}
		String[] sqlArr = new String[model.getKeys().length];
		if ("jxj".equalsIgnoreCase(key)) {
			for (int i = 0; i < model.getKeys().length; i++) {
				StringBuffer sql = new StringBuffer("update xsjxjb set xxsh='");
				sql.append(model.getSh());
				sql.append("',xxshyj='");
				sql.append((StringUtils.isNull(model.getYj()) && "ͨ��"
						.equalsIgnoreCase(model.getSh())) ? "ͬ������"
						: (StringUtils.isNull(model.getYj()) ? "" : model
								.getYj()));
				sql.append("' where xh||xn||jxjdm='");
				sql.append(model.getKeys()[i]);
				sql.append("'");
				if (sh) {
					sql.append(" and xxsh!='");
					sql.append(model.getSh());
					sql.append("'");
				}
				sqlArr[i] = sql.toString();
			}
		} else {
			for (int i = 0; i < model.getKeys().length; i++) {
				StringBuffer sql = new StringBuffer("update xsrychb set xxsh='");
				sql.append(model.getSh());
				sql.append("',xxyj='");
				sql.append((StringUtils.isNull(model.getYj()) && "ͨ��"
						.equalsIgnoreCase(model.getSh())) ? "ͬ������"
						: (StringUtils.isNull(model.getYj()) ? "" : model
								.getYj()));
				sql.append("' where xh||xn||rychdm='");
				sql.append(model.getKeys()[i]);
				sql.append("'");
				if (sh) {
					sql.append(" and xxsh!='");
					sql.append(model.getSh());
					sql.append("'");
				}
				sqlArr[i] = sql.toString();
			}
		}
		int[] result = dao.runBatch(sqlArr);
		return dao.checkBatch(result);
	}
	
	/**
	 * ѧУ��˽�ѧ��,�����ƺ���Ϣ ��ͨ�����
	 * @param key ��ѧ�� jxj�������ƺ� rych
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean saveJxjshxxByxx(PjpyXmlgModel model, String key) throws Exception {
		if (model.getKeys() == null) {
			return false;
		}
		String[] sqlArr = new String[model.getKeys().length];
		if ("jxj".equalsIgnoreCase(key)) {
			for (int i = 0; i < model.getKeys().length; i++) {
				StringBuffer sql = new StringBuffer("update xsjxjb set xxsh='");
				sql.append(model.getSh());
				sql.append("' where xh||xn||jxjdm='");
				sql.append(model.getKeys()[i]);
				sql.append("'");
				sqlArr[i] = sql.toString();
			}
		} else {
			for (int i = 0; i < model.getKeys().length; i++) {
				StringBuffer sql = new StringBuffer("update xsrychb set xxsh='");
				sql.append(model.getSh());
				sql.append("' where xh||xn||rychdm='");
				sql.append(model.getKeys()[i]);
				sql.append("'");
				sqlArr[i] = sql.toString();
			}
		}
		int[] result = dao.runBatch(sqlArr);
		return dao.checkBatch(result);
	}
	
	/**
	 * ������˽�ѧ��,�����ƺ�ʱ�ļ��ͨ�������Ƿ���
	 * 
	 * @param model
	 * @param pkValue ��ʽ xh||xn||jxjdm , xh||xn||rychdm
	 * @param key ��ѧ��jxj,�����ƺ�: rych ע����������Ĵ���
	 * @return 
	 */
	public boolean checkJxjshrs(String xn, String pkValue, String key) {
		//��ѧ��ļ��
		if ("jxj".equalsIgnoreCase(key)) {
//			 �Ȳ����������ѧԺ�ķ��䷽ʽ
			String sql = "select fpfs from xyfpjxjfs where xn=? and bmdm=(select xydm from view_xsjxjb where xh||xn||jxjdm=?)";
			String fpfs = dao.getOneRs(sql, new String[] { xn, pkValue }, "fpfs");
			int szrs = 0;
			int tgrs = 0;
			// �ҳ�רҵ,�༶���������������
			// �ҳ���������רҵ,�༶ͨ��������
			if ("zy".equalsIgnoreCase(fpfs)) {
				HashMap<String, String> rs = dao
						.getMapNotOut(
								"select (select sum(tzrs)tzrs from xmlg_csblszb where xn=? and bmdm=(select zydm "
										+ "from view_xsjxjb where xh||xn||jxjdm=?) and dm=(select jxjdm from xsjxjb where xh||xn||jxjdm=?)"
										+ " and bmlb='zy' and key='jxj') tzrs,(select count(*) num from view_xsjxjb a where xn=? and jxjdm=(select jxjdm from xsjxjb "
										+ "where xh||xn||jxjdm=?) and zydm=(select zydm from view_xsjxjb where xh||xn||jxjdm=?) and xysh='ͨ��' "
										+ "and xh||xn||jxjdm !=?) tgrs from dual",
								new String[] { xn, pkValue, pkValue, xn, pkValue,
										pkValue,pkValue });
				szrs = StringUtils.isNull(rs.get("tzrs")) ? -1 : Integer.parseInt(rs
						.get("tzrs"));// ��������
				tgrs = StringUtils.isNull(rs.get("tgrs")) ? 0 : Integer.parseInt(rs
						.get("tgrs"));// ���ͨ������
			} else if ("bj".equalsIgnoreCase(fpfs)) {
				HashMap<String, String> rs = dao
						.getMapNotOut(
								"select (select sum(tzrs)tzrs from xmlg_csblszb where xn=? and bmdm=(select bjdm "
										+ "from view_xsjxjb where xh||xn||jxjdm=?) and dm=(select jxjdm from xsjxjb where xh||xn||jxjdm=?)"
										+ " and bmlb='bj' and key='jxj') tzrs,(select count(*) num from view_xsjxjb a where xn=? and jxjdm=(select jxjdm from xsjxjb "
										+ "where xh||xn||jxjdm=?) and bjdm=(select bjdm from view_xsjxjb where xh||xn||jxjdm=?) and xysh='ͨ��' "
										+ "and xh||xn||jxjdm!=?) tgrs from dual",
								new String[] { xn, pkValue, pkValue, xn, pkValue,
										pkValue ,pkValue});
				szrs = StringUtils.isNull(rs.get("tzrs")) ?-1 : Integer.parseInt(rs
						.get("tzrs"));// ��������
				tgrs = StringUtils.isNull(rs.get("tgrs")) ? 0 : Integer.parseInt(rs
						.get("tgrs"));// ���ͨ������
			}
			if (tgrs >= szrs && szrs != 0) {// ���ﷵ�صĴ��ڣ�������������������������������ڴ���0�������
				return false;// �����������Ӵ
			}
		} else if ("rych".equalsIgnoreCase(key)) {//�����ƺŵļ��
//			 �Ȳ����������ѧԺ�ķ��䷽ʽ
			String sql = "select fpfs from xyfpjxjfs where xn=? and bmdm=(select xydm from view_xsrychb where xh||xn||rychdm=?)";
			String fpfs = dao.getOneRs(sql, new String[] { xn, pkValue }, "fpfs");
			int szrs = 0;
			int tgrs = 0;
			// �ҳ�רҵ,�༶���������������
			// �ҳ���������רҵ,�༶ͨ��������
			if ("zy".equalsIgnoreCase(fpfs)) {
				HashMap<String, String> rs = dao
						.getMapNotOut(
								"select (select sum(tzrs)tzrs from xmlg_csblszb where xn=? and bmdm=(select zydm "
										+ "from view_xsrychb where xh||xn||rychdm=?) and dm=(select rychdm from xsrychb where xh||xn||rychdm=?)"
										+ " and bmlb='zy' and key='rych') tzrs,(select count(*) num from view_xsrychb a where xn=? and rychdm=(select rychdm from xsrychb "
										+ "where xh||xn||rychdm=?) and zydm=(select zydm from view_xsrychb where xh||xn||rychdm=?) and xysh='ͨ��' "
										+ "and xh||xn||rychdm!=?) tgrs from dual",
								new String[] { xn, pkValue, pkValue, xn, pkValue,
										pkValue ,pkValue});
				szrs = StringUtils.isNull(rs.get("tzrs")) ? -1 : Integer.parseInt(rs
						.get("tzrs"));// ��������
				tgrs = StringUtils.isNull(rs.get("tgrs")) ? 0 : Integer.parseInt(rs
						.get("tgrs"));// ���ͨ������
			} else if ("bj".equalsIgnoreCase(fpfs)) {
				HashMap<String, String> rs = dao
						.getMapNotOut(
								"select (select sum(tzrs)tzrs from xmlg_csblszb where xn=? and bmdm=(select bjdm "
										+ "from view_xsrychb where xh||xn||rychdm=?) and dm=(select rychdm from xsrychb where xh||xn||rychdm=?)"
										+ " and bmlb='bj' and key='rych') tzrs,(select count(*) num from view_xsrychb a where xn=? and rychdm=(select rychdm from xsrychb "
										+ "where xh||xn||rychdm=?) and bjdm=(select bjdm from view_xsrychb where xh||xn||rychdm=?) and xysh='ͨ��' "
										+ "and xh||xn||rychdm!=?) tgrs from dual",
								new String[] { xn, pkValue, pkValue, xn, pkValue,
										pkValue,pkValue });
				szrs = StringUtils.isNull(rs.get("tzrs")) ? -1 : Integer
						.parseInt(rs.get("tzrs"));// ��������
				tgrs = StringUtils.isNull(rs.get("tgrs")) ? 0 : Integer
						.parseInt(rs.get("tgrs"));// ���ͨ������
			}
			if (tgrs >= szrs&&szrs!=-1) {// ���ﷵ�صĴ��ڣ�������������������������������ڴ���0�������
				return false;// �����������Ӵ
			}else if(szrs==-1){
				return true;
			}
		}
		return true;
	} 
	
	/**
	 * ���������ƺ�������Ϣ
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean addRychsqxx(PjpyXmlgModel model) throws Exception {
		String flag = dao.returntag("select xh from xsrychb where xh=? and xn=? and rychdm=? ", new String[]{model.getXh(),model.getXn(),model.getRychdm()});
		boolean result =false;
		if("empty".equalsIgnoreCase(flag)){
			result= dao.runUpdate(	"insert into xsrychb(xh,xn,nd,rychdm,xq,sqly,jcqk) values (?,?,?,?,?,?,?)",
					new String[] { model.getXh(), model.getXn(), model.getNd(),model.getRychdm(),model.getXq(),
					model.getSqly(), model.getJlqk()});
		}else{
			result= dao.runUpdate(	"update xsrychb set xh=?,xn=?,nd=?,rychdm=?,xq=?,sqly=?,jcqk=?,xysh='δ���',xxsh='δ���' where xh||xn||rychdm=?",
					new String[] { model.getXh(), model.getXn(), model.getNd(),model.getRychdm(),model.getXq(),
					model.getSqly(), model.getJlqk(),model.getXh()+model.getXn()+model.getRychdm()});	
		}
		return result;
	}
	/**
	 * �����ƺű������ݼ��ѧ���Ƿ���������
	 * @param xh
	 * @param xn
	 * @return
	 * @throws Exception
	 */
	public String[] checkStuRychsq(String xh, String xn,String rychdm) throws Exception {
//		����Ƿ��������������ѧ��
		String[] result = new String[]{"true","true"};
		String num = dao.getOneRs("select count(xh) num from xsrychb where xh=? and xn=? and rychdm=? and (xysh='ͨ��' or xxsh='ͨ��') ",new String[] {xh,xn,rychdm},"num");
		int count = StringUtils.isNull(num) ? 0 : Integer.parseInt(num);
		result[0] = count > 0 ? "false" : "true";
		if("true".equalsIgnoreCase(result[0])){
			// �����޿��Ƿ��в����� ���Ƿ񴦷���Ϣ
			num = dao.getOneRs("select count(xh) num from (select xh from cjb where (kcxz like '%����%' and xn=? and xh = ? and cj <60) union all select xh from wjcfb where xn=? and xh=?)",
					new String[] { xn, xh, xn, xh }, "num");
			result[1]  = (StringUtils.isNull(num) ? 0 : Integer.parseInt(num)) > 0 ? "false" : "true";	
        }
		return result;
	} 
    /**
	 * ѧУ��ѯ�����ƺ�������
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> queryRychsqxx(PjpyXmlgModel model,String userType) throws Exception{
		
		String sql ="";
		String[] queryList = new String[] { "nj","xydm","zydm","bjdm","xn","rychdm"};
		String[] likeList = new String[]{"xh", "xm"};
		MakeQuery queryObject = new MakeQuery();
		queryObject.makeQuery(queryList, likeList, model);
		String querry=queryObject.getQueryString();
//		if(!"xy".equalsIgnoreCase(userType)){
			sql ="select  xh||xn||rychdm pk,rownum r,''dis,xn,xh,xm,xb,xymc,bjmc,rychmc,xysh,xxsh from view_xsrychb";
//			if(Base.isNull(querry)){
//			    querry +=" where xysh='ͨ��' ";
//			}else{
//				querry +=" and xysh='ͨ��' ";
//			}
//		}else{
//			sql ="select  xh||xn||rychdm pk,rownum r,(case when xxsh='ͨ��' then 'disabled' else '' end)dis,xn,xh,xm,xb,xymc,bjmc,rychmc,xysh,xxsh from view_xsrychb";
//		}
			if ("xy".equalsIgnoreCase(userType)) {
				sql ="select  xh||xn||rychdm pk,rownum r,(case when xxsh='ͨ��' then 'disabled' else '' end) dis,xn,xh,xm,xb,xymc,bjmc,rychmc,xysh,xxsh from view_xsrychb";
			}
		String[] colList = new String[] { "pk","dis","r","xn","xh","xm","xb","xymc","bjmc","rychmc","xysh","xxsh"};
		return CommonQueryDAO.commonQuery(sql,querry, queryObject.getInputList(), colList, model);
	}
	/**
	 * ��ѯ�����ƺŵ���������Ϣ
	 * @param model
	 * @return
	 */
	public HashMap<String, String> viewRychsqxx(PjpyXmlgModel model) {
		return dao	.getMapNotOut(
				"select * from view_xsrychb where xh||xn||rychdm=?",
				new String[] { model.getPkValue() });
	}
	
	/**
	 * �޸������ƺ�������Ϣ,���븨����Ϣ
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean updateRychsqxx(PjpyXmlgModel model) throws Exception {
		boolean result = dao.runUpdate(
				"update xsrychb set xh=?,xn=?,rychdm=? where xh||xn||jxjdm=?",
				new String[] { model.getXn(), model.getJxjdm(),
						model.getPkValue() }); 
//		����ɹ������޸����븨����Ϣ
		if (result) {
			result = addJxjsqfzxx(model);
		}
		return result;
	}
	/**
	 * �޸������ƺ�������Ϣ
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean modiRychsqxx(PjpyXmlgModel model,String pkValue) throws Exception {
		boolean result =false;
		result= dao.runUpdate(	"update xsrychb set xh=?,xn=?,nd=?,rychdm=?,xq=?,sqly=?,jcqk=?,xysh='δ���',xxsh='δ���' where xh||xn||rychdm=?",
				new String[] { model.getXh(), model.getXn(), model.getNd(),model.getRychdm(),model.getXq(),
				model.getSqly(), model.getJlqk(),pkValue});	
		return result;
	}
	/**
	 * ɾ�������ƺ���������
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean deleteRychsqxx(PjpyXmlgModel model)throws Exception {
		if (model.getKeys() == null) {
			return false;
		}
		String[] sqlArr = new String[model.getKeys().length];
		for (int i=0;i<model.getKeys().length;i++) {
			StringBuffer sql = new StringBuffer("delete xsrychb where xh||xn||rychdm='");
			sql.append(model.getKeys()[i]);
			sql.append("'");
			sqlArr[i] = sql.toString();
		}
		int[] result = dao.runBatch(sqlArr);
		return dao.checkBatch(result);
	}
		
	/**
	 * ��ѯѧԺ����רҵ,�༶��ѧ��,�����ƺŵ�����
	 * 
	 * @param model
	 * @param key ��ѧ��:jxj �����ƺ�:rych  ����ǽ�ѧ���Ҫ��jxjdm��ֵ����֮rychdm��ֵ
	 * @return
	 */
	public String getXyJxtzrs(PjpyXmlgModel model, String key) {
		if ("jxj".equalsIgnoreCase(key)) {
			return dao
					.getOneRs(
							"select sum(nvl(tzrs,0)) num from xmlg_csblszb where xn=? and bmdm=? and key=? and dm=?",
							new String[] {model.getXn(), model.getBmdm(), key,model.getJxjdm()}, "num");
		} else {
			return dao
			.getOneRs(
					"select sum(nvl(tzrs,0)) num from xmlg_csblszb where xn=? and bmdm=? and key=? and dm=?",
					new String[] {model.getXn(), model.getBmdm(), key,model.getRychdm()}, "num");
		}
	}
	
	/**
	 * ���������˽�ѧ�������ƺ�ʱ�Ƿ���������
	 * @param fpfs רҵ zy���༶ bj
	 * @param szrs רҵ��༶���õĻ�����
	 * @param key  ��ѧ�� jxj, �����ƺ� rych
	 * @param dqshrs ��ǰҪ���ͨ��������
	 * @param dm   ��ѧ�����, �����ƺŴ��� 
	 * @param bmdm  רҵ��༶�Ĵ���
	 * @param pkList ��ǰҪ��˵����ݵ����� xh||xn||jxjdm, xh||xn||rychdm
	 * @return
	 */
	public String checkPlshxx(String fpfs,String szrs,String key,int dqshrs, String dm, String bmdm, String pkList) {
		String result = "";
		String tgrs = "";
		int intTgrs = 0;
		String[] keys = pkList != null ? pkList.split("!@") : null;
		if ("jxj".equalsIgnoreCase(key)) {
			StringBuffer sql = new StringBuffer();
			//�����רҵ���м��
			if ("zy".equalsIgnoreCase(fpfs)) {
				sql
						.append("select count(*) num from view_xsjxjb where xn=? and zydm=? and jxjdm=? and xysh='ͨ��' and xh||xn||jxjdm not in ('");
				for (int i = 0; i < keys.length; i++) {
					sql.append(keys[i]);
					if (i == keys.length - 1) {
						sql.append("')");
					} else {
						sql.append("','");
					}

				}
				//���ص��Ǹ�רҵ��ǰ�����ͨ��������
				tgrs = dao.getOneRs(sql.toString(), new String[] {
						Base.getJxjsqxn(), bmdm, dm }, "num");
			} else {
				sql
						.append("select count(*) num from view_xsjxjb where xn=? and bjdm=? and jxjdm=? and xysh='ͨ��' and xh||xn||jxjdm not in ('");
				for (int i = 0; i < keys.length; i++) {
					sql.append(keys[i]);
					if (i == keys.length - 1) {
						sql.append("')");
					} else {
						sql.append("','");
					}

				}
				// ���ص��Ǹ�רҵ��ǰ�����ͨ��������
				tgrs = dao.getOneRs(sql.toString(), new String[] {
						Base.getJxjsqxn(), bmdm, dm }, "num");
			}
		} else {

			StringBuffer sql = new StringBuffer();
			//�����רҵ���м��
			if ("zy".equalsIgnoreCase(fpfs)) {
				sql
						.append("select count(*) num from view_xsrychb where xn=? and zydm=? and rychdm=? and xysh='ͨ��' and xh||xn||rychdm not in ('");
				for (int i = 0; i < keys.length; i++) {
					sql.append(keys[i]);
					if (i == keys.length - 1) {
						sql.append("')");
					} else {
						sql.append("','");
					}

				}
				//���ص��Ǹ�רҵ��ǰ�����ͨ��������
				tgrs = dao.getOneRs(sql.toString(), new String[] {
						Base.getJxjsqxn(), bmdm, dm }, "num");
			} else {
				sql
						.append("select count(*) num from view_xsrychb where xn=? and bjdm=? and rychdm=? and xysh='ͨ��' and xh||xn||rychdm not in ('");
				for (int i = 0; i < keys.length; i++) {
					sql.append(keys[i]);
					if (i == keys.length - 1) {
						sql.append("')");
					} else {
						sql.append("','");
					}

				}
				// ���ص��Ǹ�רҵ��ǰ�����ͨ��������
				tgrs = dao.getOneRs(sql.toString(), new String[] {
						Base.getJxjsqxn(), bmdm, dm }, "num");
			}
		
		}
		int intSzrs = StringUtils.isNull(szrs) ? 0 : Integer.parseInt(szrs);
		intTgrs = StringUtils.isNull(tgrs) ? 0 : Integer.parseInt(tgrs);
		if ((intTgrs + dqshrs) > intSzrs) {
			result = "����ʧ�ܣ����ͨ�������������û�����.\n���û�����Ϊ��" + intSzrs + "�ˣ������ͨ����"
					+ intTgrs + "�ˣ���ǰ��ˣ�" + dqshrs + "��.";
		}
		return result;
	}
	
	/**
	 * ��ѧ�������ƺ�������ӡ
	 * @param pkValue
	 * @param key ��ѧ��:jxj, �����ƺ�:rych
	 * @return
	 */
	public HashMap<String, String> jxjPrint(String pkValue, String key) throws Exception {
		HashMap<String, String> rs = new HashMap<String, String>();
		if ("jxj".equalsIgnoreCase(key)) {
			String sql ="select a.* from (select xh,xm,xb,bjmc,xn,jlqk,sqly,wysp,jsjsp,xyshyj,xxshyj,csrq," +
			"jxjmc,jxjdm,xysh,xxsh,xymc,zymc,xydm,zydm,bjdm from view_xsjxjb a) a where xh||xn||jxjdm=?";
			rs = dao.getMapNotOut(sql, new String[]{pkValue});
			String count = dao
					.getOneRs(
							"select count(*) num from wjcfb where xh||xn=(select xh||xn from view_xsjxjb where xh||xn||jxjdm=?)",
							new String[] { pkValue }, "num");
			rs.put("cf", StringUtils.isNull(count)
					|| "0".equalsIgnoreCase(count) ? "��" : "��");
			PjpyXmlgModel model = new PjpyXmlgModel();
			model.setXh(rs.get("xh"));
			model.setXn(rs.get("xn"));
			//��ѯ�����۲�����
			List<String[]> zhpmList = getStuZhszcpList(model);
			if (zhpmList != null && zhpmList.size() > 0) {
				String[] pm = zhpmList.get(0);
				if (pm != null && pm.length==6) {
					rs.put("xnpm", pm[5]);
				}
			}
			return rs;
		} else {
			//����û��Ҫ��Ҫ��ӡ
			return null;
		}
	}
	
	 /**
	 * ѧУ��ѯ�����ƺ�������
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> queryRychShxx(PjpyXmlgModel model,String userType) throws Exception{
		
		StringBuffer sql =new StringBuffer();
		String[] queryList = new String[] { "nj","xydm","zydm","bjdm","xn","rychdm"};
		String[] likeList = new String[]{"xh", "xm"};
		MakeQuery queryObject = new MakeQuery();
		queryObject.makeQuery(queryList, likeList, model);
		String querry=queryObject.getQueryString();
		if(!"xy".equalsIgnoreCase(userType)){			
			if(Base.isNull(querry)){
			    querry +=" where xysh='ͨ��' ";			    
			}else{
				querry +=" and xysh='ͨ��' ";
			}
			sql.append("select a.*,rownum r,'' xnpm from (select xh||xn||rychdm pk,''dis,xh,xm,xb,xymc,zymc,bjmc,xn,");
			sql.append(" rychmc,rychdm,xysh,xxsh,xydm,zydm,bjdm,nj from view_xsrychb a ) a");
			querry += Base.isNull(model.getSh())?"":" and xxsh='"+model.getSh()+"'";
		}else{
			if(Base.isNull(querry)){
			    querry +=Base.isNull(model.getSh())?"":" where xysh='"+model.getSh()+"' ";			    
			}else{
				querry +=Base.isNull(model.getSh())?"":" and xysh='"+model.getSh()+"' ";
			}
			sql.append("select a.*,rownum r,'' xnpm from (select xh||xn||rychdm pk,(case when xxsh='ͨ��' then 'disabled' else '' end) dis,xh,xm,xb,xymc,zymc,bjmc,xn,");
			sql.append(" rychmc,rychdm,xysh,xxsh,xydm,zydm,bjdm,nj from view_xsrychb a) a");
		}
		String[] colList = new String[] { "pk","dis","r","xn","xh","xm","bjmc","rychmc","xnpm","xysh","xxsh"};
		
		List<String[]> rs = CommonQueryDAO.commonQuery(sql.toString(),querry, queryObject.getInputList(), colList, model); 
		List<String[]> zhcpList=queryZhszcppmByjxj(model); 
		rs = overrideJxjList(rs, zhcpList);
		
		return rs;
	}
	
	/**
	 * ��ѯ����ѧ�����������
	 * @param xn
	 * @return
	 * @throws Exception
	 */
	public String[] getJxjyhmcList(String xn) throws Exception {
		return dao.getRs("select distinct yhmc from view_xsjxjb where xn=? and xxsh='ͨ��' and yhmc is not null",
				new String[] { xn }, "yhmc");
	}
	
	/**
	 * ��ѯ�������Ͽ��ŵ����л񽱽����Ϣ
	 * @param model
	 * @param yhmc
	 * @return
	 */
	public List<String[]> getJxjjeByYhmc(PjpyXmlgModel model, String yhmc) {
		StringBuffer sql = new StringBuffer("select jxjmc,rownum r,xm,yhkh,je from (select jxjmc,xm,yhkh,je from view_xsjxjb where xn=? and xxsh='ͨ��'");
		
		if (!StringUtils.isNull(yhmc)) {
			sql.append(" and yhmc='");
			sql.append(yhmc);
			sql.append("'");
		}
		if (!StringUtils.isNull(model.getXydm())) {
			sql.append(" and xydm='");
			sql.append(model.getXydm());
			sql.append("'");
		}
		if (model.getKeys() != null) {
			sql.append(" and jxjdm in ('");
			for (int i=0;i<model.getKeys().length;i++) {
				sql.append(model.getKeys()[i]);
				if (i==model.getKeys().length-1) {
					sql.append("')");
				} else {
					sql.append("','");
				}
			}
		}
		sql.append(" order by jxjdm,xm)");
		return dao.rsToVator(sql.toString(), new String[] { model.getXn() },
				new String[] { "jxjmc","r", "xm", "yhkh", "je" });
	}
	public List<HashMap<String,String>> getJxjRychYxList(String type,PjpyXmlgModel model) throws Exception{
		String xydm = model.getXydm();
		String jxjdm = model.getJxjdm();
		String yychdm = model.getRychdm();
		String querry = "where 1=1 ";
		querry+=Base.isNull(xydm)?"":" and xydm='"+xydm+"'";
		querry+=" and xxsh='ͨ��'";
		List<HashMap<String,String>> list = null;
		String sql = "";
		if("jxj".equalsIgnoreCase(type)){
			querry+=Base.isNull(jxjdm)?"":" and jxjdm='"+jxjdm+"'";
			sql="select nvl(xymc,'')xymc,count(xh)cout   from view_xsjxjb  "; 
		}else{
			querry+=Base.isNull(yychdm)?"":" and rychdm='"+yychdm+"'";
			sql="select  nvl(xymc,'')xymc,count(xh)cout  from view_xsrychb  "; 
		}
		querry+=" group by xymc order by xymc";
		list = dao.getList(sql+querry,new String[]{},new String[]{"xymc","cout"});
		return list;
	}
	public List<HashMap<String,String>> getJxjRychXsList(String type,PjpyXmlgModel model){
		String xydm = model.getXydm();
		String jxjdm = model.getJxjdm();
		String yychdm = model.getRychdm();
		String querry = "where 1=1 ";
		querry+=Base.isNull(xydm)?"":" and xydm='"+xydm+"'";
		querry+=" and xxsh='ͨ��'";
		List<HashMap<String,String>> list = null;
		String sql = "";
		if("jxj".equalsIgnoreCase(type)){
			querry+=Base.isNull(jxjdm)?"":" and jxjdm='"+jxjdm+"'";
			sql="select nvl(xymc,'')xymc,nvl(bjmc,'')bjmc,nvl(xm,'')xm from view_xsjxjb  "; 
		}else{
			querry+=Base.isNull(yychdm)?"":" and rychdm='"+yychdm+"'";
			sql="select nvl(xymc,'')xymc,nvl(bjmc,'')bjmc,nvl(xm,'')xm  from view_xsrychb  "; 
		}
		querry+=" order by xymc,xh ";
		list = dao.getList(sql+querry,new String[]{},new String[]{"xymc","bjmc","xm"});
		return list;
	}
		
	/**
	 * ���ʵ�����Ľ�ѧ������
	 * @param jxjdm
	 * @return
	 * @throws Exception
	 */
	public String getJxjscmc(String[] jxjdm) throws Exception {
		String[] result = null;
		String mc = "";
		if (jxjdm != null) {
			StringBuffer sql = new StringBuffer(
					"select distinct mc from xmlg_jxjdmb where dm in ('");
			for (int i = 0; i < jxjdm.length; i++) {
				sql.append(jxjdm[i]);
				if (i == jxjdm.length - 1) {
					sql.append("')");
				} else {
					sql.append("','");
				}
			}
			result = dao.getRs(sql.toString(), new String[] {}, "mc");
		}
		if (result != null) {
			for (String s : result) {
				mc += s + ",";
			}
			return StringUtils.isNull(mc) ? "" : mc.substring(0,
					mc.length() - 1);
		}
		return mc;
	}
	
	/**
	 * ����DAO����������������ϲ�����,ע��һ���������ǰ��,�ڶ���������ں���
	 * @param array1
	 * @param array2
	 * @return
	 */
	public String[] getArray(String[] array1, String[] array2) {
		return dao.unionArray(array1, array2);
	}
	public List<HashMap<String,String>> pytjb(PjpyXmlgModel model){
		String xydm = model.getXydm();
		String zydm = model.getZydm();
		String bjdm = model.getBjdm();
		String xn   = model.getXn();
		String querry = "where 1=1 ";
		querry += Base.isNull(xydm)?"":" and xydm='"+xydm+"'";
		querry += Base.isNull(zydm)?"":" and zydm='"+zydm+"'";
		querry += Base.isNull(bjdm)?"":" and bjdm='"+bjdm+"'";
		querry += " and xyyj is not null";
		StringBuffer sql = new StringBuffer();
		sql.append("select rownum r,a.nj,a.xydm,a.zydm,a.bjdm,a.bjmc,a.xh,a.xm,g.xnpm,g.zh1,g.zh2,''xypm,b.bkcs,b.cxcs,d.cflbmc ,(select jlqk from xsjxjsqfzxxb e where a.xh=e.xh and a.xn=e.xn) jlqk ,((select drzw from xsjxjsqfzxxb e where a.xh=e.xh and a.xn=e.xn)) drzw ,c.xyyj,a.xn  from ");
		sql.append("(select xh,xm,xymc,xydm,nj,zymc,zydm,bjmc,bjdm,'"+xn+"'xn from view_xsjbxx order by xh) a left join (select xh,xm,xn,max(zh1) zh1,max(zh2) zh2,xnpm from( ");
		sql.append("select xh,xm,xn,decode(xq,'01',xqpm,'') zh1,decode(xq,'02',xqpm,'') zh2,max(xnpm) over (partition by xh) xnpm from view_xmlg_zhszcppm ) ");
		sql.append("group by xh,xm,xn,xnpm) g on a.xh=g.xh and a.xn=g.xn left join (select distinct xn,xh,count(bkcj) over (partition by xn,xh) bkcs,count(cxcj) over  ");
		sql.append("(partition by xn,xh) cxcs from cjb where (bkcj is not null and bkcj <> '0') or (cxcj is not null and cxcj <> '0') ) b  on a.xh = b.xh and a.xn=b.xn ");
		sql.append("left join(select xh,xn,max(ltrim(sys_connect_by_path(mc,','),',')) xyyj from (select xh,xn,mc,row_number() over (partition by xh,xn order by mc) rno, ");
		sql.append("row_number() over (partition by xh,xn order by mc)-1 sno from(select a.xh,a.xn,(select mc from xmlg_jxjdmb c where a.jxjdm=c.dm) mc from xsjxjb a  ");
		sql.append("union all select b.xh,b.xn,(select mc from xmlg_rychdmb c where b.rychdm=c.dm) mc from xsrychb b ) where mc is not null ");
		sql.append(") connect by prior sno = rno and prior xh=xh and prior xn=xn group by xh,xn) c on a.xh = c.xh and a.xn = c.xn left join  ");
		sql.append("(select xh,max(ltrim(sys_connect_by_path(cflbmc,','),',')) cflbmc from (select xh,cflbmc,row_number() over (partition by xh order by cflbmc) rno,row_number() over (partition by xh order by cflbmc)-1 sno from ");
		sql.append("(select xh,b.cflbmc from wjcfb a,cflbdmb b where a.cflb=b.cflbdm and a.xxsh='ͨ��') a) connect by prior sno = rno and prior xh=xh group by xh) d ");
		sql.append("on a.xh = d.xh ");
        return dao.getList(sql+querry,new String[]{},new String[]{"r","bjmc","xh","xm","xnpm","zh1","zh2","xypm","bkcs","cxcs","cflbmc","jlqk","drzw","xyyj"});
	}
	public String getXsCout(String querry){
		DAO  dao = DAO.getInstance();
		String sql = "select count(xh)cout from view_xsjbxx ";
		return dao.getOneRs(sql+querry,new String[]{},"cout");
	}
		
	/**
	 * 
	 * @param model
	 * @param fpfs
	 * @return
	 */
	public String getXytzje(PjpyXmlgModel model, String fpfs) {
		String sql = "";
		if ("zy".equalsIgnoreCase(fpfs)) {
			sql = "select nvl(sum(tzrs*je),0) je from (select a.xn,a.tzrs,a.dm,(select je" +
					" from xmlg_jxjdmb b where a.dm=b.dm) je from xmlg_csblszb a" +
					" where bmlb='zy' and bmdm in (select distinct zydm from " +
					"view_njxyzybj where xydm=?) and xn=? " +
					"and key='jxj')";
		} else if ("bj".equalsIgnoreCase(fpfs)) {
			sql = "select nvl(sum(tzrs*je),0) je from (select a.xn,a.tzrs,a.dm,(select je" +
			" from xmlg_jxjdmb b where a.dm=b.dm) je from xmlg_csblszb a" +
			" where bmlb='bj' and bmdm in (select distinct bjdm from " +
			"view_njxyzybj where xydm=?) and xn=? " +
			"and key='jxj')";
		}
		String je = StringUtils.isNull(sql) ? "" 
				: dao.getOneRs(sql, 
						new String[]{model.getXydm(),model.getXn()}, "je"); 
		return je;
	}
	
	/**
	 * ��ѯ���������ݽ��
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> queryJxjjehzData(PjpyXmlgModel model) throws Exception{
		//���Ƚ������ݵĻ���
		boolean result = dao.runProcuder("{call PROC_XMLG_JXJJEHZ(?)}", new String[]{model.getXn()});
		List<String[]> rs = new ArrayList<String[]>();
		//Ȼ���ѯ����������
		String sql = "select a.*,(select xymc from view_njxyzybj b where " +
				"a.xydm=b.xydm and rownum=1) xymc,(select sum(je) from " +
				"xyjxjjefpb b where a.xydm = b.xydm) zje,(select sum(sjje) " +
				"from xyjxjjefpb b where a.xydm = b.xydm) jsje from (select " +
				"xydm,dm, bmrs,mc from (select xydm,(ltrim(sys_connect_by_path(dm," +
				" ','), ',')) dm,(ltrim(sys_connect_by_path(rs, ','), ',')) bmrs" +
				",(ltrim(sys_connect_by_path(mc, ','), ',')) mc from (select xydm,dm,(select mc from xmlg_jxjdmb b where a.dm=b.dm) mc,decode(bmrs, null, 0, bmrs) || '/' " +
				"||decode(tzrs, null, 0, tzrs) rs,row_number() over(partition " +
				"by xydm order by dm) pno,row_number() over(partition by xydm " +
				"order by dm) - 1 sno from xyjxjjefpb a) a connect by prior xydm " +
				"= xydm and prior sno = pno) a where exists (select 1 from " +
				"view_xyjxjjefpb b where a.xydm=b.xydm and length(a.dm)=b.maxdm " +
				"and length(a.bmrs)=b.maxbmrs)) a ";
		if (result) {
			rs = dao.rsToVator(sql, new String[]{}, new String[]{ "xymc", "mc","bmrs", "zje", "jsje"});
		}
		
		//List<String[]> dataList = parseQueryJxjjeResult(rs);
		return rs;
	}
	
	/**
	 * ��ѯѧ��������Ϣ
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getStuWjcfxx(String xh, String xn) throws Exception {
		String sql = "select xn,(select xqmc from xqdzb b where a.xq=b.xqdm) xq,cflbmc,cfyymc,cfsj,cfwh,wjsj,bz from view_wjcf a where cfwh is not null and cfsj is not null and xh=?";
		if (!StringUtils.isNull(xn)) {
			sql += " and xn='";
			sql += xn;
			sql += "'";
		}
		
		return dao.rsToVator(sql, new String[] { 
				xh }, new String[]{ "xn", "cflbmc", "cfyymc", "cfsj", "cfwh", "wjsj", "bz"});
	}
}
