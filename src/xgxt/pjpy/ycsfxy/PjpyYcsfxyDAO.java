package xgxt.pjpy.ycsfxy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import common.Globals;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.utils.String.StringUtils;

public class PjpyYcsfxyDAO {

	DAO dao = DAO.getInstance();

	ArrayList<String> values = null;//��ѯ����ֵ�б�
	
	//private static PjpyYcsfxyDAO myDAO = new PjpyYcsfxyDAO();
	private static String FDY = "fdy";//����Ա��־
	private PjpyYcsfxyDAO(){
		
	}
	public static PjpyYcsfxyDAO getInstance() {
		return new PjpyYcsfxyDAO();
	}
	
	/**
	 * ƽʱ���׶ο��˳ɼ���ѯ��ͷ
	 * @return
	 */
	public List<HashMap<String, String>> getPsjdkhQueryTitle() {
		String[] enList = new String[]{"pk", "bgcolor", "dis","rownum", "xn", "xq", "xh", "xm", "bjmc", "pjkhcj", "jdkhcj", "xxsh"};
		String[] cnList = new String[]{"pk", "bgcolor", "dis", "�к�", "ѧ��", "ѧ��", "ѧ��", "����", "�༶", "ƽʱ���˳ɼ�", "�׶ο��˳ɼ�", "ѧУ���"};
		return dao.arrayToList(enList, cnList);
	}
	
	/**
	 * ��ѯ����ֵ
	 * @param model
	 * @return
	 */
	public StringBuffer getWhereSql(PjpyYcsfxyModel model) {
		StringBuffer whereSql = new StringBuffer(" where 1=1 ");
		values = new ArrayList<String>();
		if (model != null) {
			if (!StringUtils.isNull(model.getXh())) {
				whereSql.append(" and xh like '" + "%" + DealString.toGBK(model.getXh()) + "%'");
			}
			if (!StringUtils.isNull(model.getXn())) {
				whereSql.append(" and xn = ?");
				values.add(model.getXn());
			}
			if (!StringUtils.isNull(model.getXq())) {
				whereSql.append(" and xq = ?");
				values.add(model.getXq());
			}
			if (!StringUtils.isNull(model.getNj())) {
				whereSql.append(" and nj = ?");
				values.add(model.getNj());
			}
			if (!StringUtils.isNull(model.getXydm())) {
				whereSql.append(" and xydm = ?");
				values.add(model.getXydm());
			}
			if (!StringUtils.isNull(model.getZydm())) {
				whereSql.append(" and zydm = ?");
				values.add(model.getZydm());
			}
			if (!StringUtils.isNull(model.getBjdm())) {
				whereSql.append(" and bjdm = ?");
				values.add(model.getBjdm());
			}
			if (!StringUtils.isNull(model.getXm())) {
				whereSql.append(" and xm like '" + "%" + DealString.toGBK(model.getXm()) + "%'");
			}
			if (!StringUtils.isNull(model.getXxsh())) {
				whereSql.append(" and xxsh = '" +  DealString.toGBK(model.getXxsh()) + "'");
			}
			if (!StringUtils.isNull(model.getZhszcpzf())) {
				whereSql.append(" and zhszcpzf >= ?");
				values.add(model.getZhszcpzf());
			}
			if (!StringUtils.isNull(model.getDm())) {
				whereSql.append(" and dm = ?");
				values.add(model.getDm());
			}
			if (!StringUtils.isNull(model.getLb())) {
				whereSql.append(" and lb = ?");
				values.add(model.getLb());
			}
			if (!StringUtils.isNull(model.getPm())) {
				whereSql.append(" and pm <= (select count(*)*(?/100) from view_xsjbxx where bjdm=a.bjdm) ");
				values.add(model.getPm());
			}
			if (!StringUtils.isNull(model.getXyzfpm())) {
				whereSql.append(" and xyzfpm <= (select count(*)*(?/100) from view_xsjbxx where bjdm=a.bjdm) ");
				values.add(model.getXyzfpm());
			}
			if (!StringUtils.isNull(model.getJdcj())) {
				whereSql.append(" and jdkhcj >= ?");
				values.add(model.getJdcj());
			}
			if (!StringUtils.isNull(model.getXycj())) {
				//whereSql.append(" and  (exists (select 1 from (select min(cj) mincj,xh from view_cjb where (kcxz='����' or kcxz='ѡ��') and xn=? and xq=?  group by xh) where mincj>= ? and xh=a.xh) or not exists (select 1 from (select distinct xh from view_cjb where kcxz = 'ѡ��' and xn=? and xq=? ) where xh=a.xh)) ");
				whereSql.append(" and  (exists (select 1 from (select min(cj) mincj,xh from view_cjb where (kcxz = '����' or kcxz = 'ѡ��') and xn=? and xq=?  group by xh) where mincj>= ? and xh=a.xh) ) ");
				values.add(model.getXn());
				values.add(model.getXq());
				values.add(model.getXycj());
				//values.add(model.getXn());
				//values.add(model.getXq());
			}
			if (!StringUtils.isNull(model.getXxxcj())) {
				//whereSql.append(" and  (exists (select 1 from (select min(cj) mincj,xh from view_cjb where kcxz like '%Уѡ��%' and xn=? and xq=?  group by xh) where mincj>= ? and xh=a.xh) or not exists (select 1 from (select distinct xh from view_cjb where kcxz like '%Уѡ��%' and xn=? and xq=? ) where xh=a.xh)) ");
				whereSql.append(" and  (exists (select 1 from (select min(cj) mincj,xh from view_cjb where kcxz like '%Уѡ��%' and xn=? and xq=?  group by xh) where mincj>= ? and xh=a.xh) ) ");
				values.add(model.getXn());
				values.add(model.getXq());
				values.add(model.getXxxcj());
				//values.add(model.getXn());
				//values.add(model.getXq());
			}
		}
		return whereSql;
	}
	
	/**
	 * ƽʱ���׶ο��˳ɼ���ѯ���,�ӷ�ҳ
	 * @param model
	 * @param userType
	 * @param userName
	 * @return
	 */
	public ArrayList<String[]> getPsjdkhQueryResult(PjpyYcsfxyModel model,
			String userType, String userName,
			PjpyYcsfxyActionForm dataSearchForm) {
		ArrayList<String[]> rs = new ArrayList<String[]>();		
		if (FDY.equalsIgnoreCase(userType)) {
			model.setXydm("");
		}
		StringBuffer whereSql = getWhereSql(model);
		String appendSql = "";
		//ѧ�꣬ѧ������Ϊ��ѡ��
		if (!StringUtils.isNull(model.getXn())) {
			appendSql = " and b.xn='" + model.getXn() + "'";
		}
		if (!StringUtils.isNull(model.getXq())) {
			appendSql += " and b.xq='" + model.getXq() + "'";
		}
		
		String sql = "select pk,(case when xxsh!='δ���' then '#DDDDDD' end) bgcolor,(case when xxsh!='δ���' then 'disabled=disabled' else '' end) dis,rownum r,xh,xm,xydm,zydm,bjdm,nj,bjmc,xn,(select b.xqmc from xqdzb b where a.xq=b.xqdm) xq,pjkhcj,jdkhcj,xxsh from " +
				"(select a.xh||'@" + model.getXn() + "@'||'" + model.getXq() + "' pk,a.xh,a.xydm,a.nj,a.zydm,a.bjdm,a.xm,a.xb,a.bjmc,'"
				+ model.getXn()
				+ "' xn,'"
				+ model.getXq()
				+ "' xq,b.pjkhcj,"
				+ "b.jdkhcj,b.xxsh from view_xsjbxx a left join pjpy_ycsf_zhszcpb b on a.xh=b.xh "
				+ appendSql + ") a";
		String FDYSql = "";
		//����Ա�û�ֻ��ѯ������༶����
		if (FDY.equalsIgnoreCase(userType)) {
			FDYSql = " and exists (select 1 from fdybjb b where zgh like '"
				+ userName + "' and a.bjdm = b.bjdm)";
		}
		String[] enList = new String[]{"pk","bgcolor", "dis", "r", "xn", "xq", "xh", "xm", "bjmc", "pjkhcj", "jdkhcj", "xxsh"};
		rs = dao.rsToVator("select * from ("
				+ sql
				+ whereSql
				+ FDYSql
				+ ") where r<="
				+ (dataSearchForm.getPages().getStart() + dataSearchForm
						.getPages().getPageSize()) + " and r> "
				+ dataSearchForm.getPages().getStart(), values != null ? values
				.toArray(new String[0]) : new String[] {}, enList);
		return rs;
	}
	
	/**
	 * ƽʱ���׶ο��˳ɼ���ѯ���,���ܼ�¼��
	 * @param model
	 * @param userType
	 * @param userName
	 * @return
	 */
	public int getPsjdkhQueryResultCount(PjpyYcsfxyModel model,
			String userType, String userName) {
		ArrayList<String[]> rs = new ArrayList<String[]>();
		if (FDY.equalsIgnoreCase(userType)) {
			model.setXydm("");
		}
		StringBuffer whereSql = getWhereSql(model);
		
		String appendSql = "";
		//ѧ�꣬ѧ������Ϊ��ѡ��
		if (!StringUtils.isNull(model.getXn())) {
			appendSql = " and b.xn='" + model.getXn() + "'";
		}
		if (!StringUtils.isNull(model.getXq())) {
			appendSql += " and b.xq='" + model.getXq() + "'";
		}
		
		String sql = "select pk,rownum r,xh,xm,xydm,zydm,bjdm,nj,bjmc,xn,xq,pjkhcj,jdkhcj,xxsh from " +
				"(select a.xh||'" + model.getXn() + "'||'" + model.getXq() + "' pk,a.xh,a.xydm,a.nj,a.zydm,a.bjdm,a.xm,a.xb,a.bjmc,'"
				+ model.getXn()
				+ "' xn,'"
				+ model.getXq()
				+ "' xq,b.pjkhcj,"
				+ "b.jdkhcj,b.xxsh from view_xsjbxx a left join pjpy_ycsf_zhszcpb b on a.xh=b.xh "
				+ appendSql + ") a";
		String FDYSql = "";
		//����Ա�û�ֻ��ѯ�༶����
		if (FDY.equalsIgnoreCase(userType)) {
			FDYSql = " and exists (select 1 from fdybjb b where zgh like '"
				+ userName + "' and a.bjdm = b.bjdm)";
		}
		String[] enList = new String[]{"pk", "r", "xn", "xq", "xh", "xm", "bjmc", "pjkhcj", "jdkhcj"};
		rs = dao.rsToVator(sql
				+ whereSql
				+ FDYSql
				, values != null ? values
				.toArray(new String[0]) : new String[] {}, enList);
		return rs == null ? 0 : rs.size();
	}
	
	/**
	 * ����������ϲ�Ϊһ������,˳��Ϊ��ɾ���,
	 * @param pkList
	 * @param pjkhcj
	 * @param jdkhcj
	 * @param dis ΪѧУ��˱�־
	 * @return
	 */
	public String[] batchSqlArray(String[] pkList, String[] pjkhcj, String[] jdkhcj, String[] dis) {
		if (pkList != null && pjkhcj != null && jdkhcj != null
				&& pkList.length == pjkhcj.length
				&& pkList.length == jdkhcj.length) {
			String[] delArray = new String[pjkhcj.length];
			String[] insertArray = new String[jdkhcj.length];
			for (int index = 0;index < pkList.length; index++) {
				//ѧУδ��˵����ݲſ��Խ��в���
				if (StringUtils.isNull(dis[index])) {
					StringBuffer delSql = new StringBuffer("delete from pjpy_ycsf_zhszcpb where xh||xn||xq='");
					String pk = pkList[index];
					pk = pk.replaceAll("@", "");
					delSql.append(pk);
					delSql.append("'");
					StringBuffer insertSql = new StringBuffer("insert into pjpy_ycsf_zhszcpb(xh,xn,xq,pjkhcj,jdkhcj) values ('");
					String[] xhxnxqArray = pkList[index] != null ? pkList[index].split("@") : null;
					if (xhxnxqArray != null && xhxnxqArray.length == 3) {
						insertSql.append(xhxnxqArray[0]);
						insertSql.append("','");
						insertSql.append(xhxnxqArray[1]);
						insertSql.append("','");
						insertSql.append(xhxnxqArray[2]);
						insertSql.append("','");
					}
					insertSql.append(pjkhcj[index]);
					insertSql.append("','");
					insertSql.append(jdkhcj[index]);
					insertSql.append("')");
					delArray[index] = delSql.toString();//ɾ��SQL����
					insertArray[index] = insertSql.toString();//����SQL����
				}
			}
			//���غϲ��������,˳��Ϊ��ɾ���
			return dao.unionArrayTo(delArray, insertArray);
		}
		return null;
	}
	
	/**
	 * ����ƽʱ,�׶ο��˳ɼ���Ϣ
	 * @param pkList
	 * @param pjkhcj
	 * @param jdkhcj
	 * @return
	 * @throws Exception
	 */
	public boolean savePjjdkhcjxx(String[] pkList, String[] pjkhcj, String[] jdkhcj, String[] dis) throws Exception {
		//�ϲ�SQL,��ɾ�������
		boolean doFlag = false;
		String[] batchSqlArray = batchSqlArray(pkList, pjkhcj, jdkhcj,dis);
		int[] array = null;
		//������ִ�����ݱ���
		array = dao.runBatch(batchSqlArray);
		doFlag = dao.checkBatch(array);
		return doFlag;
	}
	
	/**
	 * ɾ��ƽʱ�׶�,���˳ɼ���Ϣ,���ز���ʧ�ܵ�����
	 * @param pkList
	 * @return
	 * @throws Exception
	 */
	public String deletePjjdkhcjxx(String[] pkList) throws Exception{
		String result = "";
		String[] sqlArray = new String[pkList.length];
		for (int index=0;index<pkList.length;index++) {
			StringBuffer sql = new StringBuffer("delete from pjpy_ycsf_zhszcpb where xh||xn||xq='");
			sql.append(pkList[index].replaceAll("@", ""));
			sql.append("'");
			sqlArray[index] = sql.toString();
		}
		if (sqlArray != null) {
			int[] res = dao.runBatch(sqlArray);
			result = checkOperResult(res);
		}
		return result;
	}
	
	/**
	 * ִ������������Ƿ������ݲ���ʧ��
	 * @param result
	 * @return
	 */
	public String checkOperResult(int[] result) {
		String res = "";
		if (result != null) {
			for (int index=0;index<result.length;index++) {
				if (result[index] <= -1) {
					res += (index+1) + ",";
				} 
			}
		}
		return StringUtils.isNull(res) ? "" : res.substring(0, res.length() - 1);
	}
	
	public static void main(String...strings) {
		String sql = "123@123@234";
		System.out.println(sql.replace("@", ""));
	}
	
	//�ۺ����ʲ�����������
	public boolean setZhszcpBl_db(PjpyYcsfxyModel model) throws Exception {
		String sql = "delete from zjcm_zhszcpblszb";
		boolean flag = dao.runUpdate(sql, new String[] {});
		if (Base.xxdm.equalsIgnoreCase(Globals.XXDM_GZDX)) {
			if (flag) {
				sql = "insert into zjcm_zhszcpblszb (XYFBL,BXFBL,XWBXFBL,TCBXFBL) values(?,?,?,?)";
				flag = dao.runUpdate(sql, new String[] { model.getXyfbl(),
						model.getBxfbl(), model.getXwbxfbl(),
						model.getTcbxfbl() });
			}
		} else {
			if (flag) {
				sql = "insert into zjcm_zhszcpblszb (dybl,zybl,tybl) values(?,?,?)";
				flag = dao.runUpdate(sql, new String[] { model.getPskhcjbl(),
						model.getXykhcjbl(), model.getJdkhcjbl() });
				if (Base.xxdm.equalsIgnoreCase(Globals.XXDM_CZXXZYJSXY)) {
					flag = dao.runUpdate("delete from czxx_zhszcpfblszb", new String[]{});
					if (flag) {
						flag = dao
								.runUpdate(
										"insert into czxx_zhszcpfblszb (dyjcf,dyjcfbl,dyssgffbl,dyfjfbl,zykskmcjbl,zykckmcjbl,zyfjfbl,tycjbl,tykwtydlbl,tyfjfbl) values (?,?,?,?,?,?,?,?,?,?)",
										new String[] { model.getDyjcf(),
												model.getDyjcfbl(),
												model.getDyssgffbl(),
												model.getDyfjfbl(),
												model.getZykskmcjbl(),
												model.getZykckmcjbl(),
												model.getZyfjfbl(),
												model.getTycjbl(),
												model.getTykwtydlbl(),
												model.getTyfjfbl() });
					}
				}
			}
		}
		return flag;
	}
	
	//����ۺ����ʲ�������
	public PjpyYcsfxyModel getZhszcpBl_db(){
		PjpyYcsfxyModel model = new PjpyYcsfxyModel ();
		String sql = "select dybl,zybl,tybl,xyfbl,bxfbl,xwbxfbl,tcbxfbl from zjcm_zhszcpblszb";
		String[] bls = dao.getOneRs(sql, new String[] {},
				new String[] { "dybl", "zybl", "tybl", "xyfbl", "bxfbl",
						"xwbxfbl", "tcbxfbl" });
		if(bls != null && bls.length == 7){
			model.setPskhcjbl(bls[0]);
			model.setXykhcjbl(bls[1]);
			model.setJdkhcjbl(bls[2]);
			model.setXyfbl(bls[3]);
			model.setBxfbl(bls[4]);
			model.setXwbxfbl(bls[5]);
			model.setTcbxfbl(bls[6]);
		}
		return model;
	}
	
	//��ѯ�ۺ����ʲ�����سɼ�(��ҳ)
	public List<String[]> zhszcpQuery_db(PjpyYcsfxyActionForm form,PjpyYcsfxyModel model,String userName,String isFdy){
		String query = "";
		if("true".equals(isFdy)){
			query = " and exists (select 1 from fdybjb b where zgh like '"
				+ userName + "' and a.bjdm = b.bjdm)";
		}
		String sql = "select * from (select rownum r,xh||xn||xq pk,xh,xm,xn,xqmc,nj,bjmc,pjkhcj,xykhcj,jdkhcj,zhszcpzf,xyzfpm,pm,xxsh from " +
				"view_ycsf_zhszcp a"+getWhereSql(model)+query+") where r<="
				+ (form.getPages().getStart() + form
						.getPages().getPageSize()) + " and r> "
				+ form.getPages().getStart();
		String[] cols = new String[]{"pk","xh","xm","xn","xqmc","nj","bjmc","pjkhcj","jdkhcj","xykhcj","xyzfpm","zhszcpzf","pm","xxsh"};
		return dao.rsToVator2(sql,values != null ? values
				.toArray(new String[0]) : new String[] {},cols);
	}
	
	//��ѯ�ۺ����ʲ�������
	public int zhszcpQueryCount_db(PjpyYcsfxyModel model,String userName,String isFdy){
		String query = "";
		if("true".equals(isFdy)){
			query = " and exists (select 1 from fdybjb b where zgh like '"
				+ userName + "' and a.bjdm = b.bjdm)";
		}
		String sql = "select count(*) count from view_ycsf_zhszcp a "+getWhereSql(model)+query;
		return Integer.parseInt(dao.getOneRs(sql,values != null ? values
				.toArray(new String[0]) : new String[] {}, "count"));
	}
	
	//����ѧҵ���˳ɼ����ۺ����ʲ����ɼ�
	public String computeZhszcp_db(String userType,String xydm,String userName) throws Exception{
		StringBuffer sb = new StringBuffer();
		PjpyYcsfxyModel mymodel = getZhszcpBl_db();
		String pskhcjbl = mymodel.getPskhcjbl();
		String jdkhcjbl = mymodel.getJdkhcjbl();
		String xykhcjbl = mymodel.getXykhcjbl();
		String query = "";
		if (pskhcjbl == null || jdkhcjbl == null || xykhcjbl == null) {
			return "nobl";
		}
		if(pskhcjbl == null){
			pskhcjbl = "0";
		}
		if(jdkhcjbl == null){
			jdkhcjbl = "0";
		}
		if(xykhcjbl == null){
			xykhcjbl = "0";
		}
		if("xy".equals(userType)){
			query = " and exists (select 1 from view_xsjbxx where xydm='"+xydm+"' and xh=a.xh)";
		}else if("fdy".equals(userType)){
			query = " and exists (select 1 from view_xsjbxx c where  exists (select 1 from fdybjb b where zgh like '"+ userName + "' and c.bjdm = b.bjdm) and xh=a.xh)";
		}
		sb.append("update pjpy_ycsf_zhszcpb a set xykhcj=nvl((select to_char(sum(nvl(cj,0)*nvl(xf,0))/sum(nvl(xf,0)),'99.99') from view_cjb where ");
		sb.append("(kcxz='ѡ��' or kcxz='����') and xn=a.xn and xq=a.xq and xh=a.xh group by xh),0),zhszcpzf=((nvl(a.pjkhcj,0)");
		sb.append("*(?/100)+nvl(a.jdkhcj,0)*(?/100)+nvl((select (sum(nvl(cj,0)*nvl(xf,0))/sum(nvl(xf,0))) from view_cjb ");
		sb.append(" where (kcxz='ѡ��' or kcxz='����') and xn=a.xn and xq=a.xq and xh=a.xh group by xh),0)*(?/100)) ");
		sb.append(") where xn=? and xq=? "+query);
		boolean flag = dao.runUpdate(sb.toString(), new String[]{pskhcjbl,jdkhcjbl,xykhcjbl,Base.getJxjsqxn(),Base.getJxjsqxq()});
		return flag == true ? "yes" : "no";
	}
	
	//�ۺ����ʲ���ȫ��ͨ��
	public String zhszcpShAll_db() throws Exception{
		String sql = "update pjpy_ycsf_zhszcpb set xxsh=? where xn=? and xq=?";
		boolean flag = dao.runUpdate(sql, new String[]{"ͨ��",Base.getJxjsqxn(),Base.getJxjsqxq()});
		return flag == true ? "yes" : "no";
	}
	
	//�ۺ����ʲ�������
	public String zhszcpShPart_db(String pks,String act) throws Exception{
		String xxsh = "";
		if("sh_part".equals(act)){
			xxsh = "ͨ��";
		}else if("sh_btg".equals(act)){
			xxsh = "��ͨ��";
		}else{
			xxsh = "δ���";
		}
		String sql = "update pjpy_ycsf_zhszcpb set xxsh=? where xn=? and xq=? and instr(?,'!@!'||xh||xn||xq||'!@!')>0";
		boolean flag = dao.runUpdate(sql, new String[]{xxsh,Base.getJxjsqxn(),Base.getJxjsqxq(),pks});
		return flag == true ? "yes" : "no";
	}
	
	
	//ѧ���ɼ�����

	public List<String[]> getCjList_db(String pk) throws Exception {
		String sql = "select xn,(select xqmc from xqdzb where xqdm=a.xq) xq,kcmc,kcxz,khfs,cj,xf,bkcj from view_cjb a where xh||xn||'0'||ltrim(to_char(xq),'0')=?";
		return dao.rsToVator(sql, new String[] { pk }, new String[] { "xn", "xq", "kcmc", "kcxz","khfs","cj","xf","bkcj" });
	}
	
	
	//ѧ���ɼ�ѧ��
	 
	public List<String[]> getCjListByxf_db(String pk) throws Exception {
		String sql = "select xn,(select xqmc from xqdzb where xqdm=a.xq) xq,kcmc,kclx,khfs,zpcj2,xf,bkcj from view_cjb a where xh||xn||'0'||ltrim(to_char(xq),'0')=?";
		return dao.rsToVator(sql, new String[] { pk }, new String[] { "xn", "xq", "kcmc", "kclx","khfs","zpcj2","xf","bkcj" });
	}
	
	//���ѧ��������Ϣ���ɼ�������Ϣ
	public HashMap<String,String> getXsxxAndCjhz_db(String pk) throws Exception{
		String sql = "";
		if(Globals.XXDM_AHZYJSXY.equals(Base.xxdm)){
			sql = "select * from view_ahzyjs_pjcjxx where xh||nvl(xn,'"+Base.getJxjsqxn()+"')||'0'||trim(nvl(xq,'"+Base.getJxjsqxq()+"'))=?";
		}else{
			sql = "select * from view_ycsf_zhszcp where xh||xn||xq=?";
		}	
		List<HashMap<String,String>> list = dao.getListNotOut(sql, new String[]{pk});
		if(list != null && list.size() > 0){
			return list.get(0);
		}else{
			return new HashMap<String,String>();
		}	
	}
	
	//���ѧ��������Ϣ��ƽʱ���׶γɼ���Ϣ
	public HashMap<String,String> getXsxxAndPsjdcj_db(String pk){
		int num = (Base.currXn+Base.currXq).length();
		String sql = "select * from view_xsjbxx a left join pjpy_ycsf_zhszcpb b on a.xh=b.xh and b.xh||b.xn||b.xq=? where a.xh=substr(?,0,length(?)-?)";
		List<HashMap<String,String>> list = dao.getListNotOut(sql, new String[]{pk,pk,pk,String.valueOf(num)});
		if(list != null && list.size() > 0){
			return list.get(0);
		}else{
			return new HashMap<String,String>();
		}
	}
	
	/**
	 * ѧ����ѯ�ۺ����ʲ�����Ϣ
	 * @param xh
	 * @return
	 */
	public List<String[]> stuQueryZhszcpxx(String xh) {
		String sql = "select xh||xn||xq pk,xn,xqmc,pjkhcj,xykhcj,jdkhcj,zhszcpzf,pm from view_ycsf_zhszcp where xh=?";
		return dao.rsToVator(sql, new String[] { xh }, new String[] { "pk",
				"xn", "xqmc", "pjkhcj", "xykhcj", "jdkhcj", "zhszcpzf", "pm" });
	} 
	
	/**
	 * ѧ���ۺ����ʲ�����ʾ��Ϣ
	 * @param pk
	 * @return
	 */
	public HashMap<String, String> stuZhszcpView(String pk) {
		return dao
				.getMapNotOut(
						"select xh,xn,xqmc,pjkhcj,xykhcj,jdkhcj,zhszcpzf,pm,xymc,zymc,xm,bjmc,nj from view_ycsf_zhszcp where xh||xn||xq=?",
						new String[] { pk });
	}
	
	/**
	 * ѧ���γ̳ɼ���Ϣ
	 * @param pk
	 * @return
	 */
	public List<String[]> stuCjInfo(String pk) {
		return dao.rsToVator(
				"select kcmc,kcxz,cj from cjb where xh||xn||xq=(select xh||xn||(case when length(xq)=2 then substr(xq,2,1) else xq end) from pjpy_ycsf_zhszcpb where xh||xn||xq=?)",
				new String[] { pk }, new String[] { "kcmc", "kcxz", "cj" });
	}
	
	//��ѧ�������ƺ����
	public List<String[]> getJxjOrRychQueryList_db(PjpyYcsfxyActionForm form,PjpyYcsfxyModel model){
		String[] cols = null;
		String sql="";
		model.setLb("");
		if("jxj".equals(form.getLb())){
			sql = "select * from (select rownum r,a.* from (select a.xh||a.xn||a.xq||a.jxjdm pk,a.xh,a.xm,a.xn,a.xq,a.xydm,a.zydm,a.bjdm,a.jxjdm dm,b.xqmc,a.nj,a.bjmc,b.jdkhcj,b.xykhcj,b.xyzfpm,b.zhszcpzf,b.pm,a.jxjmc,a.jlje,a.xxsh from " +
				  "view_xsjxjb a left join view_ycsf_zhszcp b on a.xh=b.xh and a.xq=b.xq and a.xn=b.xn order by a.jxjdm,a.nj,a.bjdm,b.pm) a "+getWhereSql(model)+") where r<="
				  + (form.getPages().getStart() + form.getPages().getPageSize()) + " and r> "+ form.getPages().getStart();
			cols = new String[]{"pk","dm","xh","xm","xn","xqmc","nj","bjmc","xykhcj","xyzfpm","zhszcpzf","pm","jxjmc","jlje","xxsh"};
		}else{
			sql = "select * from (select rownum r,a.* from (select a.xh||a.xn||a.xq||a.rychdm pk,a.xh,a.xm,a.xn,a.xq,a.xydm,a.zydm,a.bjdm,a.rychdm dm,b.xqmc,a.nj,a.bjmc,b.jdkhcj,b.xykhcj,b.xyzfpm,b.zhszcpzf,b.pm,a.rychmc,a.xxsh from " +
				  "view_xsrychb a left join view_ycsf_zhszcp b on a.xh=b.xh and a.xq=b.xq and a.xn=b.xn order by a.rychdm,a.nj,a.bjdm,b.pm) a "+getWhereSql(model)+") where r<="
				  + (form.getPages().getStart() + form.getPages().getPageSize()) + " and r> "+ form.getPages().getStart();
			cols = new String[]{"pk","dm","xh","xm","xn","xqmc","nj","bjmc","xykhcj","xyzfpm","zhszcpzf","pm","rychmc","xxsh"};
		}	
		return dao.rsToVator2(sql,values != null ? values.toArray(new String[0]) : new String[] {},cols);
	}
	
	//��ѧ�������ƺ����(����ְҵ)
	public List<String[]> getJxjOrRychList_db(PjpyYcsfxyActionForm form,PjpyYcsfxyModel model,String userType){
		String[] cols = null;
		String sql="";
		model.setLb("");
		String query = " where 1=1 ";
		if("xx".equals(userType) || "admin".equals(userType)){
			query += " and xysh='ͨ��'";
		}else{
			query += " and fdysh='ͨ��'";
		}
		if("jxj".equals(form.getLb())){
			sql = "select * from (select rownum r,a.* from (select a.xh||a.xn||a.xq||a.jxjdm pk,a.xh,a.xm,a.xn,a.xq,a.xydm,a.zydm,a.bjdm,a.jxjdm dm,b.xqmc,a.nj,a.bjmc,b.pjf,b.pm,a.jxjmc,a.jlje,a.xysh,a.xxsh from " +
				  "view_xsjxjb a left join view_ahzyjs_pjcjxx b on a.xh=b.xh and a.xq=b.xq and a.xn=b.xn "+query+" order by a.jxjdm,a.nj,a.bjdm,b.pm) a "+getWhereSql(model)+") where r<="
				  + (form.getPages().getStart() + form.getPages().getPageSize()) + " and r> "+ form.getPages().getStart();
			cols = new String[]{"pk","dm","xh","xm","xn","xqmc","nj","bjmc","pjf","pm","jxjmc","jlje","xysh","xxsh"};
		}else{
			sql = "select * from (select rownum r,a.* from (select a.xh||a.xn||a.xq||a.rychdm pk,a.xh,a.xm,a.xn,a.xq,a.xydm,a.zydm,a.bjdm,a.rychdm dm,b.xqmc,a.nj,a.bjmc,b.pjf,b.pm,a.rychmc,a.xysh,a.xxsh from " +
				  "view_xsrychb a left join view_ahzyjs_pjcjxx b on a.xh=b.xh and a.xq=b.xq and a.xn=b.xn "+query+" order by a.rychdm,a.nj,a.bjdm,b.pm) a "+getWhereSql(model)+") where r<="
				  + (form.getPages().getStart() + form.getPages().getPageSize()) + " and r> "+ form.getPages().getStart();
			cols = new String[]{"pk","dm","xh","xm","xn","xqmc","nj","bjmc","pjf","pm","rychmc","xysh","xxsh"};
		}	
		return dao.rsToVator2(sql,values != null ? values.toArray(new String[0]) : new String[] {},cols);
	}
	
	//��ѧ�������ƺ���˼�¼��
	public int getJxjOrRychCount_db(PjpyYcsfxyModel model){
		String sql="";
		String lb = model.getLb();
		model.setLb("");
//		if("jxj".equals(lb)){
//			sql ="select count(*) count from (select jxjdm dm,a.* from view_xsjxjb a) a"+getWhereSql(model);
//		}else{
//			sql ="select count(*) count from (select rychdm dm,a.* from view_xsrychb a) a"+getWhereSql(model);
//		}
		if("jxj".equals(lb)){
			sql = "select count(*) count from (select rownum r,a.* from (select a.xh||a.xn||a.xq||a.jxjdm pk,a.xh,a.xm,a.xn,a.xq,a.xydm,a.zydm,a.bjdm,a.jxjdm dm,b.xqmc,a.nj,a.bjmc,b.jdkhcj,b.xykhcj,b.xyzfpm,b.zhszcpzf,b.pm,a.jxjmc,a.jlje,a.xxsh from " +
				  "view_xsjxjb a left join view_ycsf_zhszcp b on a.xh=b.xh and a.xq=b.xq and a.xn=b.xn order by a.jxjdm,a.nj,a.bjdm,b.pm) a "+getWhereSql(model)+")";
		}else{
			sql = "select count(*) count from (select rownum r,a.* from (select a.xh||a.xn||a.xq||a.rychdm pk,a.xh,a.xm,a.xn,a.xq,a.xydm,a.zydm,a.bjdm,a.rychdm dm,b.xqmc,a.nj,a.bjmc,b.jdkhcj,b.xykhcj,b.xyzfpm,b.zhszcpzf,b.pm,a.rychmc,a.xxsh from " +
				  "view_xsrychb a left join view_ycsf_zhszcp b on a.xh=b.xh and a.xq=b.xq and a.xn=b.xn order by a.rychdm,a.nj,a.bjdm,b.pm) a "+getWhereSql(model)+") ";
		}
		return Integer.parseInt(dao.getOneRs(sql,values != null ? values.toArray(new String[0]) : new String[] {}, "count"));
	}
	
	//��ѧ�������ƺ���˼�¼��(����ְҵ)
	public int getAhzyJxjOrRychCount_db(PjpyYcsfxyModel model,String userType){
		String sql="";
		String lb = model.getLb();
		model.setLb("");
		String query = " where 1=1 ";
		if("xx".equals(userType) || "admin".equals(userType)){
			query += " and xysh='ͨ��'";
		}else{
			query += " and fdysh='ͨ��'";
		}
		if("jxj".equals(lb)){
			sql ="select count(*) count from (select jxjdm dm,a.* from view_xsjxjb a "+query+") "+getWhereSql(model);
		}else{
			sql ="select count(*) count from (select rychdm dm,a.* from view_xsrychb a "+query+") "+getWhereSql(model);
		}	
		return Integer.parseInt(dao.getOneRs(sql,values != null ? values.toArray(new String[0]) : new String[] {}, "count"));
	}
	
	//���ȫ��ͨ��
	public String jxjOrRychShAll_db(String userType,String userDep) throws Exception{
		String sql = "";
		boolean flag = false;
		if(Globals.XXDM_AHZYJSXY.equals(Base.xxdm)){
			if("xx".equals(userType) || "admin".equals(userType)){
				sql = "update xsjxjb set xxsh=? where xn=? and xq=? and xysh='ͨ��'";
			}else{
				sql = "update xsjxjb a set xysh=? where xn=? and xq=? and fdysh='ͨ��' and exists (select 1 from view_xsjbxx where xydm ='"+userDep+"' and xh=a.xh )";
			}
			flag = dao.runUpdate(sql, new String[]{"ͨ��",Base.getJxjsqxn(),Base.getJxjsqxq()});
			if(flag){
				if("xx".equals(userType) || "admin".equals(userType)){
					sql = "update xsrychb set xxsh=? where xn=? and xq=? and xysh='ͨ��'";
				}else{
					sql = "update xsrychb a set xysh=? where xn=? and xq=? and fdysh='ͨ��' and exists (select 1 from view_xsjbxx where xydm ='"+userDep+"' and xh=a.xh )";
				}
			}
			flag = dao.runUpdate(sql, new String[]{"ͨ��",Base.getJxjsqxn(),Base.getJxjsqxq()});
		}else{
			if("xx".equals(userType) || "admin".equals(userType)){
				sql = "update xsjxjb set xxsh=? where xn=? and xq=?";
				flag = dao.runUpdate(sql, new String[]{"ͨ��",Base.getJxjsqxn(),Base.getJxjsqxq()});
				if(flag){
					sql = "update xsrychb set xxsh=? where xn=? and xq=?";
				}
				flag = dao.runUpdate(sql, new String[]{"ͨ��",Base.getJxjsqxn(),Base.getJxjsqxq()});
			}	
		}
		
		return flag == true ? "yes" : "no";
	}
	
	//�������
	public String jxjOrRychShPart_ser(String pks, String act,String lb,String userType,String userDep) throws Exception {
		String sql = "";
		String xxsh = "";
		boolean flag = false;
		if("sh_part".equals(act)){
			xxsh = "ͨ��";
		}else if("sh_btg".equals(act)){
			xxsh = "��ͨ��";
		}else{
			xxsh = "δ���";
		}
		if(Globals.XXDM_AHZYJSXY.equals(Base.xxdm)){
			if("xx".equals(userType) || "admin".equals(userType)){
				if("jxj".equals(lb)){
					sql = "update xsjxjb set xxsh=? where xn=? and xq=? and xysh='ͨ��' and instr(?,'!@!'||xh||xn||xq||jxjdm||'!@!')>0";
				}else{
					sql = "update xsrychb set xxsh=? where xn=? and xq=? and xysh='ͨ��' and instr(?,'!@!'||xh||xn||xq||rychdm||'!@!')>0";
				}
			}else{
				if("jxj".equals(lb)){
					sql = "update xsjxjb a set xysh=? where xn=? and xq=? and fdysh='ͨ��' and instr(?,'!@!'||xh||xn||xq||jxjdm||'!@!')>0 and exists (select 1 from view_xsjbxx where xydm ='"+userDep+"' and xh=a.xh )" ;
				}else{
					sql = "update xsrychb a set xysh=? where xn=? and xq=? and fdysh='ͨ��' and instr(?,'!@!'||xh||xn||xq||rychdm||'!@!')>0 and exists (select 1 from view_xsjbxx where xydm ='"+userDep+"' and xh=a.xh )";
				}
			}
		}else{
			if("xx".equals(userType) || "admin".equals(userType)){
				if("jxj".equals(lb)){
					sql = "update xsjxjb set xxsh=? where xn=? and xq=? and instr(?,'!@!'||xh||xn||xq||jxjdm||'!@!')>0";
				}else{
					sql = "update xsrychb set xxsh=? where xn=? and xq=? and instr(?,'!@!'||xh||xn||xq||rychdm||'!@!')>0";
				}
			}			
		}	
		flag = dao.runUpdate(sql, new String[] {xxsh, Base.getJxjsqxn(),
				Base.getJxjsqxq(), pks });
		return flag == true ? "yes" : "no";
	}
	/**
	 * ��ѯѧ����Ϣ�����ۺ����ʲ�����Ϣ
	 * @param xh
	 * @return
	 */
	public HashMap<String, String> getStuInfo(String xh,String xxdm) {
		String sql = "";
		String[] colVal = null;
		if(Globals.XXDM_AHZYJSXY.equals(Base.xxdm)){
			sql = "select a.xh,a.xm,a.xymc,a.zymc,a.bjmc,a.nj,(select pjf from view_ahzyjs_pjcjxx b where a.xh=b.xh and b.xn=? and b.xq=?) pjf,(select pm from view_ahzyjs_pjcjxx b where a.xh=b.xh and b.xn=? and b.xq=?) pm from view_xsjbxx a where xh=? ";
			colVal = new String[]{Base.getJxjsqxn(), Base.getJxjsqxq(),Base.getJxjsqxn(), Base.getJxjsqxq(),xh};
		}else{
			sql = "select a.xh,a.xm,a.xymc,a.zymc,a.bjmc,a.nj,(select pjkhcj from pjpy_ycsf_zhszcpb b where a.xh=b.xh and b.xn=? and b.xq=? and xxsh='ͨ��') pjkhcj,(select xykhcj from pjpy_ycsf_zhszcpb b where a.xh=b.xh and b.xn=? and b.xq=? and xxsh='ͨ��') xykhcj,(select jdkhcj from pjpy_ycsf_zhszcpb b where a.xh=b.xh and b.xn=? and b.xq=? and xxsh='ͨ��') jdkhcj,(select zhszcpzf from pjpy_ycsf_zhszcpb b where a.xh=b.xh and b.xn=? and b.xq=? and xxsh='ͨ��') zhszcpzf,(select pm from view_ycsf_zhszcp b where a.xh=b.xh and b.xn=? and b.xq=? and xxsh='ͨ��') pm from view_xsjbxx a where xh=?";
			colVal = new String[]{Base.getJxjsqxn(), Base.getJxjsqxq(),Base.getJxjsqxn(), Base.getJxjsqxq(),Base.getJxjsqxn(), Base.getJxjsqxq(),Base.getJxjsqxn(), Base.getJxjsqxq(),Base.getJxjsqxn(), Base.getJxjsqxq(),xh};
		}	
		return dao.getMapNotOut(sql, colVal);
	}
	
	/**
	 * ѧ���γ̳ɼ���Ϣ
	 * @param pk
	 * @return
	 */
	public List<String[]> stuCjInfo(String xh, String xn, String xq) {
		if(Globals.XXDM_AHZYJSXY.equals(Base.xxdm)){
			return dao.rsToVatorNotOut(
					"select xn,(select xqmc from xqdzb where xqdm=a.xq) xq,kcmc,kcxz,khfs,cj,xf,bkcj from view_cjb a where xh=? and xn=? and xq=?",
					new String[] { xh,xn,xq });
		}else{
			return dao.rsToVatorNotOut(
					"select xn,(select xqmc from xqdzb where xqdm=a.xq) xq,kcmc,kcxz,khfs,cj,xf,bkcj from (select xh,xn,(case when length(xq)=1 then '0'||xq else to_char(xq) end) xq,kcmc,kcxz,khfs,cj,xf,bkcj from view_cjb) a where xh||xn||xq=?",
					new String[] { xh+xn+xq });
		}
	}
	/**
	 * ���浥��¼�����Ϣ
	 * @param model
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public boolean saveAddHjmdxx(PjpyYcsfxyModel model,String userType, String type) throws Exception{
		String sh = "";
		String shyj = "";
		String sql = "";
		String shyjxx = "";
		if ("xy".equalsIgnoreCase(userType)) {
			sh = "xysh";
			if ("jxj".equalsIgnoreCase(type)) {
				shyj = "xyshyj";
			}else{
				shyj = "xyyj";
			}	
			shyjxx = model.getXyshyj();
		}else if("xx".equalsIgnoreCase(userType) || "admin".equalsIgnoreCase(userType)){
			sh = "xxsh";
			if ("jxj".equalsIgnoreCase(type)) {
				shyj = "xxshyj";
			}else{
				shyj = "xxyj";
			}	
			shyjxx = model.getXxshyj();
		}else{
			sh = "fdysh";
			shyj = "fdyyj";
			shyjxx = model.getFdyyj();
		}
		shyjxx = DealString.toGBK(shyjxx);
		if ("jxj".equalsIgnoreCase(type)) {
			sql = "insert into xsjxjb (xh,xn,xq,jxjdm,"+sh+","+shyj+") values (?,?,?,?,?,?)";
		} else {
			sql = "insert into xsrychb (xh,xn,xq,rychdm,"+sh+","+shyj+") values (?,?,?,?,?,?)";
		}
		return dao.runUpdate(sql, new String[] { model.getXh(), model.getXn(),
				model.getXq(), model.getDm(), "ͨ��", shyjxx });
	}
	
	/**
	 * ɾ����������Ϣ
	 * @param pkList
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public boolean deleteHjmdxx(String[] pkList, String type) throws Exception{
		if (pkList == null || type == null) {
			return false;
		}
		String[] sqlArray = new String[pkList.length];
		String tableName = "";
		if ("jxj".equalsIgnoreCase(type)) {
			tableName = "xsjxjb";
			for (int i=0;i<pkList.length;i++) {
				StringBuffer sql = new StringBuffer("delete from ");
				sql.append(tableName);
				sql.append(" where xh||xn||xq||jxjdm = '");
				sql.append(pkList[i]);
				sql.append("'");
				sqlArray[i] = sql.toString();
			}
		} else {
			tableName = "xsrychb";
			for (int i=0;i<pkList.length;i++) {
				StringBuffer sql = new StringBuffer("delete from ");
				sql.append(tableName);
				sql.append(" where xh||xn||xq||rychdm = '");
				sql.append(pkList[i]);
				sql.append("'");
				sqlArray[i] = sql.toString();
			}
		}
		
		int[] result = dao.runBatch(sqlArray);
		return dao.checkBatch(result);
	}
	
	
	/**
	 * ѧԺ�޸Ļ�����
	 * @param xm
	 * @param pkValue
	 * @return
	 */
	public HashMap<String, String> viewModifyHjmdxx(String xm, String pkValue,String userType) {
		if (StringUtils.isNull(xm) || StringUtils.isNull(pkValue)) {
			return null;
		}
		String sql = "";
		String sh = "";
		String shyj = "";
		String sjsh = "";
		if ("xy".equalsIgnoreCase(userType)) {
			sh = "xysh";
			if ("jxj".equalsIgnoreCase(xm)) {
				shyj = "xyshyj";
			}else{
				shyj = "xyyj xyshyj";
			}		
			sjsh = ",a.xxsh";
		}else if("xx".equalsIgnoreCase(userType) || "admin".equalsIgnoreCase(userType)){
			sh = "xxsh";
			if ("jxj".equalsIgnoreCase(xm)) {
				shyj = "xxshyj";
			}else{
				shyj = "xxyj xxshyj";
			}
		}else{
			sh = "fdysh";
			shyj = "fdyyj";
			sjsh = ",a.xysh";
		}
		if (Globals.XXDM_AHZYJSXY.equals(Base.xxdm)) {
			if ("jxj".equalsIgnoreCase(xm)) {
				 sql = "select a.xh,a.xn,(select xqmc from xqdzb b where a.xq=b.xqdm) xqmc,a.xq,a.jxjdm dm,a."+sh+",a."+shyj+",a.xm,a.nj,a.xymc," +
				 		"a.zymc,a.bjmc,b.pjf,b.pm"+sjsh+" from view_xsjxjb " +
				 		"a left join view_ahzyjs_pjcjxx b on a.xh=b.xh and a.xn=b.xn and a.xq=b.xq where a.xh||a.xn||a.xq||jxjdm=?";
			} else {
				sql = "select a.xh,a.xn,(select xqmc from xqdzb b where a.xq=b.xqdm) xqmc,a.xq,a.rychdm dm,a."+sh+",a."+shyj+",a.xm,a.nj,a.xymc,a.zymc," +
						"a.bjmc,b.pjf,b.pm"+sjsh+" from view_xsrychb a " +
						"left join view_ahzyjs_pjcjxx b on a.xh=b.xh and a.xn=b.xn and a.xq=b.xq where a.xh||a.xn||a.xq||rychdm=?";
			}
		}else{
			if ("jxj".equalsIgnoreCase(xm)) {
				 sql = "select a.xh,a.xn,(select xqmc from xqdzb b where a.xq=b.xqdm) xqmc,a.xq,a.jxjdm dm,a."+sh+",a."+shyj+",a.xm,a.nj,a.xymc," +
				 		"a.zymc,a.bjmc,b.pjkhcj,b.xykhcj,b.jdkhcj,b.zhszcpzf,b.pm"+sjsh+" from view_xsjxjb " +
				 		"a left join view_ycsf_zhszcp b on a.xh=b.xh and a.xn=b.xn and a.xq=b.xq where a.xh||a.xn||a.xq||jxjdm=?";
			} else {
				sql = "select a.xh,a.xn,(select xqmc from xqdzb b where a.xq=b.xqdm) xqmc,a.xq,a.rychdm dm,a."+sh+",a."+shyj+",a.xm,a.nj,a.xymc,a.zymc," +
						"a.bjmc,b.pjkhcj,b.xykhcj,b.jdkhcj,b.zhszcpzf,b.pm"+sjsh+" from view_xsrychb a " +
						"left join view_ycsf_zhszcp b on a.xh=b.xh and a.xn=b.xn and a.xq=b.xq where a.xh||a.xn||a.xq||rychdm=?";
			}
		}
		return dao.getMapNotOut(sql, new String[]{pkValue});
	}
	
	/**
	 * ѧУ�û��޸Ļ�������Ϣ
	 * 
	 * @param xm
	 * @param pkValue
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean saveModiHjmdxx(String userType,String xm, String pkValue,
			PjpyYcsfxyModel model) throws Exception {
		if (StringUtils.isNull(pkValue)) {
			return false;
		}
		String sh = "";
		String shyj = "";
		String shyjxx = "";
		if ("xy".equalsIgnoreCase(userType)) {
			sh = "xysh";
			if ("jxj".equalsIgnoreCase(xm)) {
				shyj = "xyshyj";
			}else{
				shyj = "xyyj";
			}
			shyjxx = DealString.toGBK(model.getXyshyj());
		}else if("xx".equalsIgnoreCase(userType)|| "admin".equalsIgnoreCase(userType)){
			sh = "xxsh";
			if ("jxj".equalsIgnoreCase(xm)) {
				shyj = "xxshyj";
			}else{
				shyj = "xxyj";
			}
			shyjxx = DealString.toGBK(model.getXxshyj());
		}else{
			sh = "fdysh";
			shyj = "fdyyj";
			shyjxx = DealString.toGBK(model.getFdyyj());
		}
		if ("jxj".equalsIgnoreCase(xm)) {
			return dao.runUpdate("update xsjxjb set jxjdm=?,"+sh+"=?,"+shyj+"=? where xh||xn||xq||jxjdm=?",
							new String[] { model.getDm(), "ͨ��",shyjxx,pkValue });
		} else {
			return dao.runUpdate("update xsrychb set rychdm=?,"+sh+"=?,"+shyj+"=? where xh||xn||xq||rychdm=?",
							new String[] { model.getDm(), "ͨ��",shyjxx,pkValue });
		}
	}
	
	/**
	 * �������ϱ���ѯ��ͷ
	 * 
	 * @return
	 */
	public List<HashMap<String, String>> queryHjmdxxTitle() {
		String[] enList = null;
		String[] cnList = null;
		if(Base.xxdm.equals(Globals.XXDM_AHZYJSXY)){
			enList = new String[] { "pk", "bgcolor", "dis", "rownum",
					"xh", "xm", "xn", "xq", "pjf", "pm","dm" ,"xysh"};
			cnList = new String[] { "pk", "bgcolor", "dis", "�к�", "ѧ��",
					"����", "ѧ��", "ѧ��", "�ܿ�ƽ����", "�༶����", "����","ѧԺ���" };
		}else{
			enList = new String[] { "pk", "bgcolor", "dis", "rownum",
					"xh", "xm", "xn", "xq", "pjkhcj", "jdkhcj", "xykhcj", "xyzfpm", "zhszcpzf", "pm",
					"dm" ,"xxsh"};
			cnList = new String[] { "pk", "bgcolor", "dis", "�к�", "ѧ��",
					"����", "ѧ��", "ѧ��", "ƽʱ�ɼ�", "�׶γɼ�", "ѧҵ�ɼ�","ѧҵ�༶����", "�۲��ܷ�", "�۲�༶����", "����","ѧУ���" };
		}
		
		return dao.arrayToList(enList, cnList);
	}
	
	/**
	 * ��ѯ��������Ϣ
	 * @param model
	 * @param userType
	 * @param userName
	 * @param dataSearchForm
	 * @return
	 */
	public ArrayList<String[]> queryHjmdxxResult(PjpyYcsfxyModel model,
			String userType, String userName, PjpyYcsfxyActionForm dataSearchForm) {
		StringBuffer whereSql = getWhereSql(model);
		ArrayList<String[]> rs = new ArrayList<String[]>();
		String appSql = "";
		String sql = "";
		String[] enList = null;
		if (!StringUtils.isNull(model.getXn())) {
			appSql += " and b.xn='" + model.getXn() + "'";
		}
		if (!StringUtils.isNull(model.getXq())) {
			appSql += " and b.xq='" + model.getXq() + "'";
		}
		//��ѧ�������ѯ
		if(Globals.XXDM_AHZYJSXY.equals(Base.xxdm)){
			sql = "select pk,xh,xm,xn,(select b.xqmc from xqdzb b where a.xq=b.xqdm) xq,pjf,pm,jxjmc dm,xysh,rownum r,(case when xysh!='δ���' then 'disabled=disabled' else '' end) dis, " +
				"(case when xysh!='δ���' then '#DDDDDD' else '' end) bgcolor from (select a.*,b.pjf,b.pm from (select a.xh||'"+model.getXn()+"'||'"+model.getXq()+"'||b.jxjdm pk,a.xh,a.xm,b.jxjdm dm,a.xydm,a.zydm,a.bjdm,a.nj" +
				",(select jxjmc from jxjdmb c where c.jxjdm=b.jxjdm) jxjmc,b.xysh,'"+model.getXn()+"' xn,'"+model.getXq()+"' xq,'jxj' lb " +
				"from view_xsjbxx a left join xsjxjb b on a.xh=b.xh" 
				+ appSql
				+ ") a left join view_ahzyjs_pjcjxx b on a.xh=b.xh  "+appSql+") a";
			if ("rych".equalsIgnoreCase(model.getLb())) {//�����ƺŹ�����ѯ
				sql = "select pk,xh,xm,xn,(select b.xqmc from xqdzb b where a.xq=b.xqdm) xq,pjf,pm,rychmc dm,xysh,rownum r,(case when xysh!='δ���' then 'disabled=disabled' else '' end) dis, " +
				"(case when xysh!='δ���' then '#DDDDDD' else '' end) bgcolor from (select a.*,b.pjf,b.pm from (select a.xh||'"+model.getXn()+"'||'"+model.getXq()+"'||b.rychdm pk,a.xh,a.xm,b.rychdm dm,a.xydm,a.zydm,a.bjdm,a.nj," +
				"(select rychmc from rychdmb c where c.rychdm=b.rychdm) rychmc,b.xysh,'"+model.getXn()+"' xn,'"+model.getXq()+"' xq,'rych' lb " +
				" from view_xsjbxx a left join xsrychb b on a.xh=b.xh" 
				+ appSql
				+ ") a left join view_ahzyjs_pjcjxx b on a.xh=b.xh "+appSql+") a";
			}
			enList = new String[] { "pk", "bgcolor", "dis", "r",
				"xh", "xm", "xn", "xq", "pjf", "pm",
				"dm" ,"xysh"};
		}else{
			sql = "select pk,xh,xm,xn,(select b.xqmc from xqdzb b where a.xq=b.xqdm) xq,pjkhcj,xykhcj,xyzfpm,jdkhcj,zhszcpzf,pm,jxjmc dm,xxsh,rownum r,(case when xxsh!='δ���' then 'disabled=disabled' else '' end) dis, " +
				"(case when xxsh!='δ���' then '#DDDDDD' else '' end) bgcolor from (select a.*,b.pjkhcj,b.xykhcj,b.xyzfpm,b.jdkhcj,b.zhszcpzf,b.pm from (select a.xh||'"+model.getXn()+"'||'"+model.getXq()+"'||b.jxjdm pk,a.xh,a.xm,b.jxjdm dm,a.xydm,a.zydm,a.bjdm,a.nj" +
				",(select jxjmc from jxjdmb c where c.jxjdm=b.jxjdm) jxjmc,b.xxsh,'"+model.getXn()+"' xn,'"+model.getXq()+"' xq,'jxj' lb " +
				"from view_xsjbxx a left join xsjxjb b on a.xh=b.xh" 
				+ appSql
				+ ") a left join view_ycsf_zhszcp b on a.xh=b.xh and b.xxsh='ͨ��' "+appSql+") a";
			if ("rych".equalsIgnoreCase(model.getLb())) {//�����ƺŹ�����ѯ
				sql = "select pk,xh,xm,xn,(select b.xqmc from xqdzb b where a.xq=b.xqdm) xq,pjkhcj,xykhcj,xyzfpm,jdkhcj,zhszcpzf,pm,rychmc dm,xxsh,rownum r,(case when xxsh!='δ���' then 'disabled=disabled' else '' end) dis, " +
				"(case when xxsh!='δ���' then '#DDDDDD' else '' end) bgcolor from (select a.*,b.pjkhcj,b.xykhcj,b.xyzfpm,b.jdkhcj,b.zhszcpzf,b.pm from (select a.xh||'"+model.getXn()+"'||'"+model.getXq()+"'||b.rychdm pk,a.xh,a.xm,b.rychdm dm,a.xydm,a.zydm,a.bjdm,a.nj," +
				"(select rychmc from rychdmb c where c.rychdm=b.rychdm) rychmc,b.xxsh,'"+model.getXn()+"' xn,'"+model.getXq()+"' xq,'rych' lb " +
				" from view_xsjbxx a left join xsrychb b on a.xh=b.xh" 
				+ appSql
				+ ") a left join view_ycsf_zhszcp b on a.xh=b.xh and b.xxsh='ͨ��' "+appSql+") a";
			}
			enList = new String[] { "pk", "bgcolor", "dis", "r",
				"xh", "xm", "xn", "xq", "pjkhcj", "jdkhcj", "xykhcj","xyzfpm", "zhszcpzf", "pm",
				"dm" ,"xxsh"};
		}
		String FDYSql = "";
		//����Ա�û�ֻ��ѯ�༶����
		if (FDY.equalsIgnoreCase(userType)) {
			FDYSql = " and exists (select 1 from fdybjb b where zgh like '"
				+ userName + "' and a.bjdm = b.bjdm)";
		}
		sql = "select * from (" + sql + whereSql.toString() + FDYSql + ") where r<=";
		rs = dao.rsToVator(sql+ (dataSearchForm.getPages().getStart() + dataSearchForm
				.getPages().getPageSize()) + " and r> "
				+ dataSearchForm.getPages().getStart(), values != null ? values
				.toArray(new String[0]) : new String[] {}, enList);
		return rs;
	}
	
	/**
	 * ��ѯ����Ϣ�ܼ�¼��
	 * @param model
	 * @param userType
	 * @param userName
	 * @param dataSearchForm
	 * @return
	 */
	public int queryHjmdxxResultCount(PjpyYcsfxyModel model,
			String userType, String userName, PjpyYcsfxyActionForm dataSearchForm) {
		StringBuffer whereSql = getWhereSql(model);
		List<String[]> rs = new ArrayList<String[]>();
		String appSql = "";
		String sql = "";
		if (!StringUtils.isNull(model.getXn())) {
			appSql += " and b.xn='" + model.getXn() + "'";
		}
		if (!StringUtils.isNull(model.getXq())) {
			appSql += " and b.xq='" + model.getXq() + "'";
		}
		//��ѧ�������ѯ
		if(Globals.XXDM_AHZYJSXY.equals(Base.xxdm)){
			sql = "select pk,xh,xm,xn,(select b.xqmc from xqdzb b where a.xq=b.xqdm) xq,pjf,pm,jxjmc dm,xysh,rownum r,(case when xysh!='δ���' then 'disabled=disabled' else '' end) dis, " +
				"(case when xysh!='δ���' then '#DDDDDD' else '' end) bgcolor from (select a.*,b.pjf,b.pm from (select a.xh||'"+model.getXn()+"'||'"+model.getXq()+"'||b.jxjdm pk,a.xh,a.xm,b.jxjdm dm,a.xydm,a.zydm,a.bjdm,a.nj" +
				",(select jxjmc from jxjdmb c where c.jxjdm=b.jxjdm) jxjmc,b.xysh,'"+model.getXn()+"' xn,'"+model.getXq()+"' xq,'jxj' lb " +
				"from view_xsjbxx a left join xsjxjb b on a.xh=b.xh" 
				+ appSql
				+ ") a left join view_ahzyjs_pjcjxx b on a.xh=b.xh  "+appSql+") a";
			if ("rych".equalsIgnoreCase(model.getLb())) {//�����ƺŹ�����ѯ
				sql = "select pk,xh,xm,xn,(select b.xqmc from xqdzb b where a.xq=b.xqdm) xq,pjf,pm,rychmc dm,xysh,rownum r,(case when xysh!='δ���' then 'disabled=disabled' else '' end) dis, " +
				"(case when xysh!='δ���' then '#DDDDDD' else '' end) bgcolor from (select a.*,b.pjf,b.pm from (select a.xh||'"+model.getXn()+"'||'"+model.getXq()+"'||b.rychdm pk,a.xh,a.xm,b.rychdm dm,a.xydm,a.zydm,a.bjdm,a.nj," +
				"(select rychmc from rychdmb c where c.rychdm=b.rychdm) rychmc,b.xysh,'"+model.getXn()+"' xn,'"+model.getXq()+"' xq,'rych' lb " +
				" from view_xsjbxx a left join xsrychb b on a.xh=b.xh" 
				+ appSql
				+ ") a left join view_ahzyjs_pjcjxx b on a.xh=b.xh "+appSql+") a";
			}
		}else{
			sql = "select pk,xh,xm,xn,(select b.xqmc from xqdzb b where a.xq=b.xqdm) xq,pjkhcj,xykhcj,xyzfpm,jdkhcj,zhszcpzf,pm,jxjmc dm,xxsh,rownum r,(case when xxsh!='δ���' then 'disabled=disabled' else '' end) dis, " +
				"(case when xxsh!='δ���' then '#DDDDDD' else '' end) bgcolor from (select a.*,b.pjkhcj,b.xykhcj,b.xyzfpm,b.jdkhcj,b.zhszcpzf,b.pm from (select a.xh||'"+model.getXn()+"'||'"+model.getXq()+"'||b.jxjdm pk,a.xh,a.xm,b.jxjdm dm,a.xydm,a.zydm,a.bjdm,a.nj" +
				",(select jxjmc from jxjdmb c where c.jxjdm=b.jxjdm) jxjmc,b.xxsh,'"+model.getXn()+"' xn,'"+model.getXq()+"' xq,'jxj' lb " +
				"from view_xsjbxx a left join xsjxjb b on a.xh=b.xh" 
				+ appSql
				+ ") a left join view_ycsf_zhszcp b on a.xh=b.xh and b.xxsh='ͨ��' "+appSql+") a";
			if ("rych".equalsIgnoreCase(model.getLb())) {//�����ƺŹ�����ѯ
				sql = "select pk,xh,xm,xn,(select b.xqmc from xqdzb b where a.xq=b.xqdm) xq,pjkhcj,xykhcj,xyzfpm,jdkhcj,zhszcpzf,pm,rychmc dm,xxsh,rownum r,(case when xxsh!='δ���' then 'disabled=disabled' else '' end) dis, " +
				"(case when xxsh!='δ���' then '#DDDDDD' else '' end) bgcolor from (select a.*,b.pjkhcj,b.xykhcj,b.xyzfpm,b.jdkhcj,b.zhszcpzf,b.pm from (select a.xh||'"+model.getXn()+"'||'"+model.getXq()+"'||b.rychdm pk,a.xh,a.xm,b.rychdm dm,a.xydm,a.zydm,a.bjdm,a.nj," +
				"(select rychmc from rychdmb c where c.rychdm=b.rychdm) rychmc,b.xxsh,'"+model.getXn()+"' xn,'"+model.getXq()+"' xq,'rych' lb " +
				" from view_xsjbxx a left join xsrychb b on a.xh=b.xh" 
				+ appSql
				+ ") a left join view_ycsf_zhszcp b on a.xh=b.xh and b.xxsh='ͨ��' "+appSql+") a";
			}
		}
		String FDYSql = "";
		//����Ա�û�ֻ��ѯ�༶����
		if (FDY.equalsIgnoreCase(userType)) {
			FDYSql = " and exists (select 1 from fdybjb b where zgh like '"
				+ userName + "' and a.bjdm = b.bjdm)";
		}
		String[] enList = new String[] { "xh"};
		sql = sql + whereSql.toString() + FDYSql;
		rs = dao.rsToVator(sql, values != null ? values
				.toArray(new String[0]) : new String[] {}, enList);
		return rs.size();
	}
	
	/**
	 * ѧԺ�û������ϱ�������
	 * @param xhList
	 * @param model
	 * @return
	 */
	public boolean hjmdPlsb_db(String userType,String[] xhList, PjpyYcsfxyModel model) throws Exception{
		boolean result = false;
		String[] sqlArray = new String[xhList.length];
		if (xhList == null || model == null) {
			return false;
		}
		String sh = "";
		String shyj = "";
		String shyjxx = "";
		if ("xy".equalsIgnoreCase(userType)) {
			sh = "xysh";
			if ("jxj".equalsIgnoreCase(model.getLb())) {
				shyj = "xyshyj";
			}else{
				shyj = "xyyj";
			}
			shyjxx = model.getXyshyj();
		}else if("xx".equalsIgnoreCase(userType) || "admin".equalsIgnoreCase(userType)){
			sh = "xxsh";
			if ("jxj".equalsIgnoreCase(model.getLb())) {
				shyj = "xxshyj";
			}else{
				shyj = "xxyj";
			}
			shyjxx = model.getXxshyj();
		}else{
			sh = "fdysh";
			shyj = "fdyyj";
			shyjxx = model.getFdyyj();
		}
		shyjxx = DealString.toGBK(shyjxx);
		if ("jxj".equalsIgnoreCase(model.getLb())) {
			for (int i=0;i<xhList.length;i++) {
				if (!StringUtils.isNull(xhList[i])) {
					StringBuffer sql = new StringBuffer("insert into xsjxjb (xh,xn,xq,jxjdm,"+shyj+","+sh+") values ('");
					sql.append(xhList[i]);
					sql.append("','");
					sql.append(model.getXn());
					sql.append("','");
					sql.append(model.getXq());
					sql.append("','");
					sql.append(model.getDm());
					sql.append("','");
					sql.append(shyjxx);
					sql.append("','ͨ��')");
					sqlArray[i] = sql.toString();
				}
			}
		} else {
			for (int i=0;i<xhList.length;i++) {
				if (!StringUtils.isNull(xhList[i])) {
					StringBuffer sql = new StringBuffer("insert into xsrychb (xh,xn,xq,rychdm,"+shyj+","+sh+") values ('");
					sql.append(xhList[i]);
					sql.append("','");
					sql.append(model.getXn());
					sql.append("','");
					sql.append(model.getXq());
					sql.append("','");
					sql.append(model.getDm());
					sql.append("','");
					sql.append(shyjxx);
					sql.append("','ͨ��')");
					sqlArray[i] = sql.toString();
				}
			}
		}
		int[] res = dao.runBatch(sqlArray);
		result = dao.checkBatch(res);
		return result;
	}
	
	/**
	 * ��ѯ��������Ϣ
	 * @param model
	 * @return
	 */
	public HashMap<String, String> viewHjmdxx(PjpyYcsfxyModel model) {
		String sql = "";
		if (Globals.XXDM_AHZYJSXY.equals(Base.xxdm)) {
			sql = "select a.xh,a.xm,a.xb,a.xymc,a.zymc,a.bjmc,a.nj,b.pjf,b.pm,b.xn,(select c.xqmc from xqdzb c where b.xq=c.xqdm) xq" +
			" from view_xsjbxx a left join view_ahzyjs_pjcjxx " +
			"b on a.xh=b.xh and b.xn=? and b.xq=? where a.xh=?";
		}else{
			sql = "select a.xh,a.xm,a.xb,a.xymc,a.zymc,a.bjmc,a.nj,b.pjkhcj,b.xykhcj,b.xn,(select c.xqmc from xqdzb c where b.xq=c.xqdm) xq" +
			",b.jdkhcj,b.zhszcpzf,b.pm from view_xsjbxx a left join view_ycsf_zhszcp " +
			"b on a.xh=b.xh and b.xn=? and b.xq=? where a.xh=?";
		}	
		return dao.getMapNotOut(sql, new String[]{model.getXn(),model.getXq(),model.getXh()});
	}
	
	//���������
	public HashMap<String, String> getJxjOrRychShxx_db(String lb,String pk){
		String sql = "";
		HashMap<String, String> map = new HashMap<String, String>();
		
		if("jxj".equals(lb)){
			sql = "select xxsh,jxjdm dm,xxshyj,xysh,xyshyj from view_xsjxjb where xh||xn||xq||jxjdm=?";
		}else{
			sql = "select xxsh,rychdm dm,xxyj xxshyj,xysh,xyshyj from view_xsrychb where xh||xn||xq||rychdm=?";
		}
		List<HashMap<String, String>> list = dao.getList(sql, new String[]{pk},new String[]{"xxsh","dm","xxshyj","xysh","xyshyj"});
		if(list !=null && list.size() > 0){
			map = list.get(0);
		}
		return map;
	}
	
	//�������
	public String jxjOrRychDgsh_db(String pk,PjpyYcsfxyModel model,String userType) throws Exception{
		String sql = "";
		String[] colVal = null;
		if(Globals.XXDM_AHZYJSXY.equals(Base.xxdm)){
			if("xy".equals(userType)){
				if("jxj".equals(model.getLb())){
					sql = "update xsjxjb set xysh=?,xyshyj=?,jxjdm=? where xh||xn||xq||jxjdm=?";
				}else{
					sql = "update xsrychb set xysh=?,xyyj=?,rychdm=? where xh||xn||xq||rychdm=?";
				}
			}
			colVal = new String[]{DealString.toGBK(model.getXysh()),DealString.toGBK(model.getXyshyj()),model.getDm(),pk};
		}
		if("xx".equals(userType) || "admin".equals(userType)){
			if("jxj".equals(model.getLb())){
				sql = "update xsjxjb set xxsh=?,xxshyj=?,jxjdm=? where xh||xn||xq||jxjdm=?";
			}else{
				sql = "update xsrychb set xxsh=?,xxyj=?,rychdm=? where xh||xn||xq||rychdm=?";
			}
			colVal = new String[]{DealString.toGBK(model.getXxsh()),DealString.toGBK(model.getXxshyj()),model.getDm(),pk};
		}	
		boolean flag = dao.runUpdate(sql, colVal);
		return flag == true ? "yes" : "no";
	}
	
	//��ѯ��˽��(��ҳ)
	public List<String[]> queryShResult_db(PjpyYcsfxyActionForm form,PjpyYcsfxyModel model,String userName,String isFdy){
		String query = "";
		String sql = "";
		String[] cols = null;
		model.setLb("");
		if("true".equals(isFdy)){
			query = " and exists (select 1 from fdybjb b where zgh like '"
				+ userName + "' and a.bjdm = b.bjdm)";
			model.setXydm("");
		}
		if(Globals.XXDM_AHZYJSXY.equals(Base.xxdm)){
			if("jxj".equals(form.getLb())){
				sql = "select * from (select rownum r,a.* from (select a.xh||a.xn||a.xq||a.jxjdm pk,a.xh,a.xm,a.xn,a.xq,a.xydm,a.zydm,a.bjdm,a.jxjdm dm,b.xqmc,a.nj,a.bjmc,b.pjf,b.pm,a.jxjmc,a.jlje,a.xysh,a.xxsh from " +
					  "view_xsjxjb a left join view_ahzyjs_pjcjxx b on a.xh=b.xh and a.xq=b.xq and a.xn=b.xn order by a.jxjdm,a.nj,a.bjdm,b.pm) a "+getWhereSql(model)+query+") where r<="
					  + (form.getPages().getStart() + form.getPages().getPageSize()) + " and r> "+ form.getPages().getStart();
				cols = new String[]{"pk","dm","xh","xm","xn","xqmc","nj","bjmc","pjf","pm","jxjmc","jlje","xysh","xxsh"};
			}else{
				sql = "select * from (select rownum r,a.* from (select a.xh||a.xn||a.xq||a.rychdm pk,a.xh,a.xm,a.xn,a.xq,a.xydm,a.zydm,a.bjdm,a.rychdm dm,b.xqmc,a.nj,a.bjmc,b.pjf,b.pm,a.rychmc,a.xysh,a.xxsh from " +
					  "view_xsrychb a left join view_ahzyjs_pjcjxx b on a.xh=b.xh and a.xq=b.xq and a.xn=b.xn order by a.rychdm,a.nj,a.bjdm,b.pm) a "+getWhereSql(model)+query+") where r<="
					  + (form.getPages().getStart() + form.getPages().getPageSize()) + " and r> "+ form.getPages().getStart();
				cols = new String[]{"pk","dm","xh","xm","xn","xqmc","nj","bjmc","pjf","pm","rychmc","xysh","xxsh"};
			}
		}else{
			if("jxj".equals(form.getLb())){
				sql = "select * from (select rownum r,a.* from (select a.xh||a.xn||a.xq||a.jxjdm pk,a.xh,a.xm,a.xn,a.xq,a.xydm,a.zydm,a.bjdm,a.jxjdm dm,b.xqmc,a.nj,a.bjmc,b.zhszcpzf,b.pm,b.xykhcj,b.xyzfpm,a.jxjmc,a.jlje,a.xxsh from " +
					  "view_xsjxjb a left join view_ycsf_zhszcp b on a.xh=b.xh and a.xq=b.xq and a.xn=b.xn order by a.jxjdm,a.nj,a.bjdm,b.pm) a "+getWhereSql(model)+query+") where r<="
					  + (form.getPages().getStart() + form.getPages().getPageSize()) + " and r> "+ form.getPages().getStart();
				cols = new String[]{"pk","dm","xh","xm","xn","xqmc","nj","bjmc","xykhcj", "xyzfpm", "zhszcpzf","pm","jxjmc","jlje","xxsh"};
			}else{
				sql = "select * from (select rownum r,a.* from (select a.xh||a.xn||a.xq||a.rychdm pk,a.xh,a.xm,a.xn,a.xq,a.xydm,a.zydm,a.bjdm,a.rychdm dm,b.xqmc,a.nj,a.bjmc,b.zhszcpzf,b.pm,b.xykhcj,b.xyzfpm,a.rychmc,a.xxsh from " +
					  "view_xsrychb a left join view_ycsf_zhszcp b on a.xh=b.xh and a.xq=b.xq and a.xn=b.xn order by a.rychdm,a.nj,a.bjdm,b.pm) a "+getWhereSql(model)+query+") where r<="
					  + (form.getPages().getStart() + form.getPages().getPageSize()) + " and r> "+ form.getPages().getStart();
				cols = new String[]{"pk","dm","xh","xm","xn","xqmc","nj","bjmc","xykhcj", "xyzfpm","zhszcpzf","pm","rychmc","xxsh"};
			}
		}	
		return dao.rsToVator2(sql,values != null ? values
				.toArray(new String[0]) : new String[] {},cols);
	}
	
	//��ѯ��˽������
	public int queryShResultCount_db(PjpyYcsfxyModel model,String userName,String isFdy){
		String query = "";
		String sql = "";
		String lb = model.getLb();
		model.setLb("");
		if("true".equals(isFdy)){
			query = " and exists (select 1 from fdybjb b where zgh like '"
				+ userName + "' and a.bjdm = b.bjdm)";
			model.setXydm("");
		}
		if("jxj".equals(lb)){
			sql = "select count(*) count from (select jxjdm dm,a.* from view_xsjxjb a) a "+getWhereSql(model)+query;
		}else{
			sql = "select count(*) count from (select rychdm dm,a.* from view_xsrychb a) a "+getWhereSql(model)+query;
		}
		return Integer.parseInt(dao.getOneRs(sql,values != null ? values
				.toArray(new String[0]) : new String[] {}, "count"));
	}
	
	//ѧ����ѧ������
	public List<String[]> queryXsJxj_db(String userName){
		String sql = "select xh||xn||xq||jxjdm pk,jxjdm dm,'jxj' lb,xn,(select xqmc from xqdzb where xqdm=a.xq) xq,jxjmc mc,jlje,xxsh from view_xsjxjb a where xh=? order by xn,xq";
		return dao.rsToVatorNotOut(sql, new String[]{userName});
	}
	
	//ѧ�������ƺŻ����
	public List<String[]> queryXsRych_db(String userName){
		String sql = "select xh||xn||xq||rychdm pk,rychdm dm,'rych' lb,xn,rychmc mc,xxsh from view_xsrychb a where xh=? order by xn";
		return dao.rsToVatorNotOut(sql, new String[]{userName});
	}
	
	//ĳ�༶ѧ�����пγ�
	public List<String[]> queryBjxsKcmc_db(PjpyYcsfxyModel model){
		String sql = "select distinct kcmc||'/'||kcxz||'/'||xf kcpk from view_cjb a where exists (select 1 from view_xsjbxx where bjdm=? " +
				"and xh=a.xh) and xn=? and xq=? order by kcmc||'/'||kcxz||'/'||xf";
		return dao.rsToVatorNotOut(sql, new String[]{model.getBjdm(),model.getXn(),model.getXq()});
	}
	
	//���ĳ�༶ѧ��
	public List<String[]> getBjxs_db(String bjdm){
		String sql = "select xh from view_xsjbxx where bjdm=?";
		return dao.rsToVatorNotOut(sql, new String[]{bjdm});
	}
	
	//���ĳѧ�����гɼ�
	public List<HashMap<String,String>> getOneXscj_db(PjpyYcsfxyModel model){
		String sql = "select cj,b.r from view_cjb a ,(select kcpk,rownum r from (select distinct kcmc||'/'||kcxz||'/'||xf kcpk from view_cjb " +
				"a where exists (select 1 from view_xsjbxx where bjdm=? and xh=a.xh) and xn=? and xq=? order by " +
				"kcmc||'/'||kcxz||'/'||xf)) b where a.kcmc||'/'||a.kcxz||'/'||a.xf = b.kcpk and xn=? and xq=? and xh=?";
		return dao.getListNotOut(sql, new String[]{model.getBjdm(),model.getXn(),model.getXq(),model.getXn(),model.getXq(),model.getXh()});
	}
	//���ĳѧ�����ܳɼ�
	public HashMap<String,String> getOneXshzcj_db(PjpyYcsfxyModel model){
		String sql = "select a.xh,a.xm,b.pjkhcj,jdkhcj,xykhcj,xyzfpm,zhszcpzf,pm from view_xsjbxx a left join view_ycsf_zhszcp b on a.xh=b.xh and b.xn=? and b.xq=? where a.xh=? ";
		List<HashMap<String,String>> list = dao.getListNotOut(sql, new String[]{model.getXn(),model.getXq(),model.getXh()});
		if(list.size()>0){
			return list.get(0);
		}else{
			return new HashMap<String,String>();
		}
	}
	//���ѧ��
	public String getXqmc_db(String xq){
		String sql = "select xqmc from xqdzb where xqdm=?";
		return dao.getOneRs(sql, new String[]{xq}, "xqmc");
	}
	
	public HashMap<String, String> queryZhszcpxmBlxx() {
		return dao.getMapNotOut("select dyjcf,dyjcfbl,dyssgffbl,dyfjfbl,zykskmcjbl,zykckmcjbl,zyfjfbl,tycjbl,tykwtydlbl,tyfjfbl from czxx_zhszcpfblszb", new String[]{});
	}
}
