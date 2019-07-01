package xgxt.pjpy.comm.pjpy.rssz;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.form.User;
import xgxt.pjpy.comm.pjpy.PjpyCommService;
import xgxt.pjpy.comm.pjpy.PjxtszModel;
import xgxt.pjpy.comm.pjpy.model.PjpyXmszModel;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.MakeQuery;
import xgxt.utils.String.StringUtils;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ��������_��������_��������_Service��
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author sjf
 * @version 1.0
 */
public class PjpyRsszService extends PjpyCommService{
	
	private DAO dao = DAO.getInstance();
	
	/**
	 * ��ȡ��Ҫ�������õ�����������Ŀ:1.��Ҫ�������Ƶ���Ŀ��2.�Ƿ�����Ϊ�ǵ���Ŀ
	 * @return
	 * @author sjf
	 */
	public List<HashMap<String, String>> getXmForRssz(){
		String[] colList = new String[]{"xmdm", "xmmc", "kzfw"};
		Map<String,String> model = new HashMap<String, String>();
		
		// �����������õ���Ŀ�ǵ�ǰ����ʱ������Ҫ�������ò����õ�������Ŀ
		model.put("sfqy", "��");
		model.put("rssz", "��");
		model.put("pjxn", PjxtszModel.pjxtszModel.getPjxn());
		model.put("pjnd", PjxtszModel.pjxtszModel.getPjnd());
		model.put("pjxq", PjxtszModel.pjxtszModel.getPjxq());
		
		List<HashMap<String, String>> pjxmList = getPjxm(model, colList);
		
		return pjxmList;
	}
	
	/**
	 * ���ݲ�ͬ�����÷�Χ��ò�ѯ��ͷ
	 * @param szfw
	 * @return
	 */
	public List<HashMap<String, String>> getTopTr(String szfw){
		String[] cn = null;
		String[] en = null;
		
		if("xy".equalsIgnoreCase(szfw)){
			cn = new String[]{Base.YXPZXY_KEY+"����", Base.YXPZXY_KEY+"����", "���ñ�����%��", "���������", "Ĭ��ֵ", "ȷ������"};
			en = new String[]{"xymc", "xyrs", "szbl", "jshrs", "mrz", "qdz"};
		}else if("zy".equalsIgnoreCase(szfw)){
			cn = new String[]{"�꼶", Base.YXPZXY_KEY+"����", "רҵ����", "רҵ����", "���ñ�����%��", "���������", "Ĭ��ֵ", "ȷ������"};
			en = new String[]{"nj", "xymc", "zymc", "zyrs", "szbl", "jshrs", "mrz", "qdz"};
		}else if("bj".equalsIgnoreCase(szfw)){
			cn = new String[]{"�꼶", Base.YXPZXY_KEY+"����", "�༶����", "�༶����", "���ñ�����%��", "���������", "Ĭ��ֵ", "ȷ������"};
			en = new String[]{"nj", "xymc", "zymc", "bjmc", "bjrs", "szbl", "jshrs", "mrz", "qdz"};
		}else{
			cn = new String[]{"�꼶", "�꼶����", "���ñ�����%��", "���������", "Ĭ��ֵ", "ȷ������"};
			en = new String[]{"nj", "njrs", "szbl", "jshrs", "mrz", "qdz"};
		}
		
		return DAO.getInstance().arrayToList(en, cn);
	}
	
	/**
	 * ��������ΪѧԺ�ǲ�ѯ
	 * @return
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws SecurityException 
	 * @throws IllegalArgumentException 
	 */
	public List<String[]> searchForXy(PjpyRsszForm model) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{		
		StringBuilder sql = new StringBuilder();
		
		MakeQuery makeQuery = new MakeQuery();
		makeQuery.makeQuery(new String[]{"xydm","nj"}, new String[]{}, model);
		
		String wheresql = makeQuery.getQueryString();
		String[] queryInput = makeQuery.getInputList();
		
		sql
			.append("select rownum r,xmdm||szfw||bmdm pkValue,xydm,xymc,bmrs,")
			.append("szbl,jsrs,mrrs,qdrs from ")
			.append("(select xmdm,szfw,bmdm,bmrs,szbl,jsrs,mrrs,qdrs from xg_pjpy_rsszb where szfw='xy' and xmdm=?) b ")
			.append("left join ")
			.append("(select distinct xydm,xymc from xg_view_pjpy_njxyzybj ")
			.append("where pjxn=? and pjxq=? and pjnd=? ")
			.append("group by nj,xydm,xymc) a ")
			.append("on a.xydm=b.bmdm");
		
		wheresql += " order by a.xydm";
		String[] colList = new String[]{"pkValue","xymc","bmrs","szbl","jsrs","mrrs","qdrs"};
		String[] input = new String[]{model.getXmdm(), PjxtszModel.pjxtszModel.getPjxn(),
							 PjxtszModel.pjxtszModel.getPjxq(), PjxtszModel.pjxtszModel.getPjnd()};
		
		return CommonQueryDAO.commonQuery(sql.toString(),wheresql, dao.uniteArrays(input, queryInput), colList, model);
	}
	
	/**
	 * ��������Ϊרҵ�ǲ�ѯ
	 * @return
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws SecurityException 
	 * @throws IllegalArgumentException 
	 */
	public List<String[]> searchForZy(PjpyRsszForm model) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{		
		StringBuilder sql = new StringBuilder();
		
		MakeQuery makeQuery = new MakeQuery();
		makeQuery.makeQuery(new String[]{"xydm", "zydm","nj"}, new String[]{}, model);
		
		String wheresql = makeQuery.getQueryString();
		String[] queryInput = makeQuery.getInputList();
		
		sql
			.append("select rownum r,xmdm||szfw||bmdm pkValue,nj,xydm,xymc,zydm,zymc,bmrs,")
			.append("szbl,jsrs,mrrs,qdrs from ")
			.append("(select xmdm,szfw,bmdm,bmrs,szbl,jsrs,mrrs,qdrs from xg_pjpy_rsszb where szfw='zy' and xmdm=?) b ")
			.append("left join ")
			.append("(select nj,xydm,xymc,zydm,zymc from xg_view_pjpy_njxyzybj ")
			.append("where pjxn=? and pjxq=? and pjnd=? ")
			.append("group by nj,xydm,xymc,zydm,zymc order by nj,xydm,zydm) a ")
			.append("on a.nj||a.zydm=b.bmdm");
		 
		String[] colList = new String[]{"pkValue","nj","xymc","zymc","bmrs","szbl","jsrs","mrrs","qdrs"};
		String[] input = new String[]{model.getXmdm(), PjxtszModel.pjxtszModel.getPjxn(),
							  		  PjxtszModel.pjxtszModel.getPjxq(), PjxtszModel.pjxtszModel.getPjnd()};
		
		return CommonQueryDAO.commonQuery(sql.toString(), wheresql, dao.uniteArrays(input, queryInput), colList, model);
	}
	
	/**
	 * ��������Ϊ�༶
	 * @return
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws SecurityException 
	 * @throws IllegalArgumentException 
	 */
	public List<String[]> searchForBj(PjpyRsszForm model) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{		
		StringBuilder sql = new StringBuilder();
		
		MakeQuery makeQuery = new MakeQuery();
		makeQuery.makeQuery(new String[]{"xydm", "zydm", "bjdm","nj"}, new String[]{}, model);
		
		String wheresql = makeQuery.getQueryString();
		String[] queryInput = makeQuery.getInputList();
		
		sql
			.append("select rownum r,xmdm||szfw||bmdm pkValue,nj,xydm,xymc,zydm,zymc,bjdm,bjmc,bmrs,")
			.append("szbl,jsrs,mrrs,qdrs from ")
			.append("(select xmdm,szfw,bmdm,bmrs,szbl,jsrs,mrrs,qdrs from xg_pjpy_rsszb where szfw='bj' and xmdm=?) b ")
			.append("left join ")
			.append("(select nj,xydm,xymc,zydm,zymc,bjdm,bjmc from xg_view_pjpy_njxyzybj ")
			.append("where pjxn=? and pjxq=? and pjnd=? ")
			.append("group by nj,xydm,xymc,zydm,zymc,bjdm,bjmc order by xydm,zydm) a ")
			.append("on a.bjdm=b.bmdm");
		 
		wheresql += " order by a.nj,a.xydm,a.zydm,a.bjdm";
		String[] colList = new String[]{"pkValue","nj","xymc","bjmc","bmrs","szbl","jsrs","mrrs","qdrs"};
		String[] input = new String[]{model.getXmdm(), PjxtszModel.pjxtszModel.getPjxn(),
		  		  					  PjxtszModel.pjxtszModel.getPjxq(), PjxtszModel.pjxtszModel.getPjnd()};
		
		return CommonQueryDAO.commonQuery(sql.toString(), wheresql, dao.uniteArrays(input, queryInput), colList, model);
	}
	
	/**
	 * ��������Ϊ�꼶
	 * @return
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws SecurityException 
	 * @throws IllegalArgumentException 
	 */
	public List<String[]> searchForNj(PjpyRsszForm model) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{		
		StringBuilder sql = new StringBuilder();

		sql
			.append("select rownum r,xmdm||szfw||bmdm pkValue,bmdm,bmrs,szbl,jsrs,mrrs,qdrs from xg_pjpy_rsszb where szfw='nj' and xmdm='")
			.append(model.getXmdm())
			.append("' ");
		 
		String[] colList = new String[]{"pkValue","bmdm","bmrs","szbl","jsrs","mrrs","qdrs"};
		
		return CommonQueryDAO.commonQuery(sql.toString(), "", new String[]{}, colList, model);
	}
	
	/**
	 * ���ô洢���̣���ʼ����������
	 * @return
	 */
	public boolean initRssz(){
		PjxtszModel model = PjxtszModel.pjxtszModel;
		try {
			return dao.runProcuder("{call pjpy_rssz_init(?,?,?)}", new String[] {model.getPjxn(),model.getPjxq(),model.getPjnd()});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
	
	/**
	 * ����������Ŀ�����ʼ����������
	 * @param xmdm
	 * @return
	 */
	public boolean initRsszByXmdm(String xmdm){
		PjxtszModel model = PjxtszModel.pjxtszModel;
		try {
			return dao.runProcuder("{call pjpy_rssz_initbyxmdm(?,?,?,?)}", 
					new String[] {model.getPjxn(),model.getPjxq(),model.getPjnd(),xmdm});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
	
	/**
	 * ������е�ѧԺ
	 * @param model
	 * @return
	 */
	public List<HashMap<String, String>> getAllXy(PjpyRsszForm model){
		StringBuilder sql = new StringBuilder();
		
		sql.append("select a.bmdm xydm,(select bmmc from zxbz_xxbmdm b where b.bmdm=a.bmdm) xymc from xg_pjpy_rsszb a where a.xmdm=? and szfw='xy'");
		return dao.getList(sql.toString(), new String[]{model.getXmdm()}, new String[]{"xydm", "xymc"});
	}
	
	/**
	 * ������е��꼶
	 * @param model
	 * @return
	 */
	public List<HashMap<String, String>> getAllNj(PjpyRsszForm model){
		StringBuilder sql = new StringBuilder();
		
		sql.append("select a.bmdm nj from xg_pjpy_rsszb a where a.xmdm=? and a.szfw='nj'");
		return dao.getList(sql.toString(), new String[]{model.getXmdm()}, new String[]{"nj"});
	}
	
	
	/**
	 * ������������
	 * @param model
	 * @return
	 */
	public boolean saveRssz(String[] pkValues,PjpyRsszForm model){
		//String fpfs = model.getFpfs();
		
		if (null != pkValues && pkValues.length > 0){
			return saveRsszForBl(pkValues,model);
		}
		
		return false;
	}
	
	/**
	 * ������䷽ʽ�Ա�������
	 * @param model
	 * @return
	 */
	private boolean saveRsszForBl(String[] pkValues,PjpyRsszForm model){
		String bl = model.getBl();
		int blxs = Integer.valueOf(getCssz());
		
		List<String[]> values = new ArrayList<String[]>();
		for(int i=0; i<pkValues.length; i++){
			values.add(new String[]{bl,bl,bl,pkValues[i]});
		}
		
		StringBuilder jsrs = new StringBuilder();
		jsrs.append("round((bmrs*0.01*?),").append(blxs).append(")");
		
		StringBuilder sql = new StringBuilder(); 
		sql.append("update xg_pjpy_rsszb set szbl=?,jsrs=(bmrs*0.01*?)")
		   .append(",mrrs=");
		
		if (3==blxs){
			sql.append("round(round(round(").append(jsrs).append(",2),1))");
		} else if (2==blxs){
			sql.append("round(round(").append(jsrs).append(",1))");
		} else if (1==blxs){
			sql.append("round(").append(jsrs).append(")");
		} else if (0==blxs){
			sql.append(jsrs);
		}
		
		sql.append(" where xmdm||szfw||bmdm=?");
		
		try {
			int[] result = dao.runBatch(sql.toString(), values);
			return dao.checkBatchResult(result);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * ������䷽ʽ����������
	 * @param model
	 * @return
	 */
	private boolean saveRsszForRs(PjpyRsszForm model){
		String rs = model.getRs();
		String szfw = model.getSzfw();
		String blxs = getCssz();
		String xmdm = model.getXmdm();
		String[] params = model.getParams();
		
		StringBuilder blsql = new StringBuilder();
		blsql.append("select sum(bmrs) zrs from xg_pjpy_rsszb where xmdm='")
			 .append(xmdm)
			 .append("' and (");
		
		for(int i=0; i<params.length; i++){
			if(i==0){
				blsql.append("bmdm=? ");
			}else{
				blsql.append("or bmdm=? ");
			}
		}
		
		blsql.append(")");
		
		Map<String, String> map = dao.getMapNotOut(blsql.toString(), params);
		
		String sql = "update xg_pjpy_rsszb set szbl=?, jsrs = (bmrs*0.01*?) where xmdm||szfw||bmdm=?";
	
		return false;
	}
	
	/**
	 * �����������
	 * @param model
	 * @return
	 */
	public boolean saveCssz(PjpyRsszForm model){
		String blxs = model.getBlxs();
		String[] sqlArr = new String[2];
		
		sqlArr[0] = "delete from xg_pjpy_rsszcsb";
		sqlArr[1] = "insert into xg_pjpy_rsszcsb(blxs) values('" + blxs + "')";		
		boolean flag = false;
		
		try {
			dao.runBatch(sqlArr);
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return flag;
	}
	
	/**
	 * ��ò�������
	 * @return
	 */
	public String getCssz(){
		String sql = "select blxs from xg_pjpy_rsszcsb where rownum=1";
		Map<String, String> map = dao.getMapNotOut(sql, new String[]{});
		
		return StringUtils.isNull(map.get("blxs")) ? "1" : map.get("blxs") ;
	}
	
	/**
	 * ����ȷ������
	 * @return
	 */
	public boolean saveQdrs(PjpyRsszForm model){
		String[] qdrs = model.getQdrs();
		String[] pks = model.getPks();
		boolean flag = false;
		String sql = "update xg_pjpy_rsszb set qdrs=? where xmdm||szfw||bmdm=?";
	
		List<String[]> list = new ArrayList<String[]>();
		
		for(int i=0; i<qdrs.length; i++){
			list.add(new String[]{qdrs[i], pks[i]});
		}
		
		try {
			dao.runBatch(sql, list);
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	/**
	 * ����Ĭ������
	 * @return
	 */
	public boolean saveQdrsFromMrrs(String[] pkValues){
		boolean flag = false;
		String sql = "update xg_pjpy_rsszb set qdrs=mrrs where xmdm||szfw||bmdm=?";
	
		List<String[]> list = new ArrayList<String[]>();
		
		for(int i=0; i<pkValues.length; i++){
			list.add(new String[]{pkValues[i]});
		}
		
		try {
			dao.runBatch(sql, list);
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	/**
	 * ���ʣ������
	 * @param model
	 * @param szfw
	 * @return -1��������������
	 */ 
	public Integer getSyrs(PjpyXmszModel model, String szfw){
		String xmdm = model.getXmdm();
		String rssx = model.getRssx();
		Integer syrs = -1;
		
		if(StringUtils.isNotNull(rssx)){
			String sql = "select nvl(sum(qdrs),0) count from xg_pjpy_rsszb where szfw||xmdm=?";
			Map<String, String> map = dao.getMapNotOut(sql, new String[]{szfw+xmdm});
			syrs = Integer.parseInt(rssx) - Integer.parseInt(map.get("count"));
			
			return syrs;
		}else{
			return syrs;
		}
	}
	
	/**
	 * ��ȡ�������list
	 * @param model
	 * @param jbszModel
	 * @param user
	 * @return
	 */
	public List<HashMap<String, Object>> getFpdxList(PjpyRsszForm jbszModel,User user) {

		// �������
		String szfw = jbszModel.getSzfw();
		
		String userStatus = user.getUserStatus(); 
		String userName= user.getUserName(); 
		String userDep= user.getUserDep(); 

		// �꼶�б�
		List<HashMap<String, String>> njList = getBmList(new String[]{"nj","",""}, userStatus,
				userName, userDep);

		// ѧԺ�б�
		List<HashMap<String, String>> xyList = getBmList(new String[]{"xy","",""}, userStatus,
				userName, userDep);

		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();

		// �������xy��ѧԺ��
		if ("xy".equalsIgnoreCase(szfw)) {
			// �꼶
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("Lv", "1");
			map.put("liName", "Child");
			map.put("bmList", xyList);
			list.add(map);
		}
		else if("nj".equalsIgnoreCase(szfw)){
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("Lv", "1");
			map.put("liName", "Child");
			map.put("bmList", njList);
			list.add(map);
		}
		// �������njzy���꼶+רҵ��
		else if ("zy".equalsIgnoreCase(szfw)) {
			// �꼶
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("Lv", "1");
			map.put("aName", "Open");
			map.put("bmList", njList);
			list.add(map);
		}
		// �������bj���༶��
		else if ("bj".equalsIgnoreCase(szfw)) {
			// �꼶
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("Lv", "1");
			map.put("aName", "Open");
			map.put("bmList", njList);
			list.add(map);
		}

		return list;
	}
	
	/**
	 * �Ƿ�ͬ����Աȷ��
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	public Boolean isTbRyqd() {

		PjpyRsszDAO dao = new PjpyRsszDAO();

		List<HashMap<String, String>> list = dao.getRyqdList();

		boolean flag = false;

		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				String num = list.get(i).get("num");
				if (!Base.isNull(num) && !"0".equalsIgnoreCase(num)) {
					flag =true;
				}else{
					flag = false;
					break;
				}
			}
		}

		return flag;

	}


	/**
	 * �ж��Ƿ���δ�����������ó�ʼ������Ŀ
	 * @return
	 */
	public boolean isRsszCsh(){
		
		String pjxn = PjxtszModel.pjxtszModel.getPjxn();
		String pjxq = PjxtszModel.pjxtszModel.getPjxq();
		String pjnd = PjxtszModel.pjxtszModel.getPjnd();
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select count(1) count from xg_pjpy_pjxmwhb a ")
		   .append("where not exists (select 1 from xg_pjpy_rsszb b ")
		   .append("where a.xmdm=b.xmdm) and rssz='��' and sfqy='��' and pjxn=? ")
		   .append("and pjxq=? and pjnd=?");
		
		String count = dao.getOneRs(sql.toString(), new String[]{pjxn,pjxq,pjnd}, "count");
		
		return !"0".equals(count);
	}

}
