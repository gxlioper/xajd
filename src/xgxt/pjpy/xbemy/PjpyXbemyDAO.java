package xgxt.pjpy.xbemy;

import java.util.ArrayList;
import java.util.HashMap;

import xgxt.DAO.DAO;
import xgxt.action.base.BaseServicesUtil;
import xgxt.utils.String.StringUtils;

public class PjpyXbemyDAO  {
	DAO dao = DAO.getInstance();
	private String jsName = "zf01";//����Ա
	/**
	 * @return ��ѧ���б�
	 */
	public ArrayList<HashMap<String, String>> getJxjList() {
		ArrayList<HashMap<String, String>> rs = new ArrayList<HashMap<String, String>>();
		String sql = "select jxjdm,jxjmc from jxjdmb order by jxjdm";
		rs = dao.getArrayList(sql, new String[] {},
				new String[] { "jxjdm", "jxjmc" });
		return rs;
	}

	/**
	 * �������ֻ�ǹ�ѧԺ�û��͹���Ա�Ĳ�ѯ ֱ�Ӳ�ѯ����Ӧ�༶��רҵ��ѧԺ��ѧ����Ϣ�����ѧ���Ѿ����ͨ��������ʾͨ�����
	 * 
	 * @param userName
	 *            ��ǰ�û����û���
	 * @param pjpyModel
	 *            model
	 * @return
	 */
	public ArrayList<String[]> getXysbjxjSearch(String userName,
			PjpyXbemyXysbjxjModel pjpyModel) {
		ArrayList<String[]> rs = new ArrayList<String[]>();
		String sql = "SELECT a.xh||b.xn||b.jxjdm key, "
				+ "(case nvl(b.xysh,'δ���') when 'ͨ��' then '#99FFCC' when 'δ���' then '#FFFFFF' end) bgcolor,"
				+ "a.xh,a.xm,a.zymc,a.bjmc,b.xysh FROM VIEW_XSJBXX A LEFT JOIN XSJXJB B ";
		StringBuffer whereSql = new StringBuffer(
				" ON A.XH=B.XH AND B.XN=? AND B.JXJDM=? WHERE 1=1 ");// ѧ���Ǳ�ѡ��
		ArrayList<String> values = new ArrayList<String>();
		String xydm = pjpyModel.getXydm();
		String zydm = pjpyModel.getZydm();
		String bjdm = pjpyModel.getBjdm();
		String nj = pjpyModel.getNj();
		String xn = pjpyModel.getXn();
		String xmdm = pjpyModel.getXmdm();// �����Ŀ���루��ѧ��Ĵ���������ƺŵĴ��룩
		// ����ı���
		String[] opCols = { "key", "bgcolor", "xh", "xm", "zymc", "bjmc",
				"xysh" };
		if (!StringUtils.isNull(xn)) {// ѧ���Ǳ�ѡ��
			values.add(xn);
		}
		if (!StringUtils.isNull(xmdm)) {// ��ѧ���Ǳ�ѡ��
			values.add(xmdm);
		}
		if (!StringUtils.isNull(xydm)) {
			whereSql.append(" and A.XYDM=? ");
			values.add(xydm);
		}
		if (!StringUtils.isNull(zydm)) {
			whereSql.append(" and A.ZYDM=? ");
			values.add(zydm);
		}
		if (!StringUtils.isNull(bjdm)) {
			whereSql.append(" and A.BJDM=? ");
			values.add(bjdm);
		}
		if (!StringUtils.isNull(nj)) {
			whereSql.append(" and A.NJ=? ");
			values.add(nj);
		}

		rs = dao.rsToVator(sql + whereSql, values.toArray(new String[0]), opCols);
		return rs;
	}

	/**
	 * @return ѧԺ�ϱ���ѧ���������񴦣����񴦲�ѯ�ı�ͷ
	 */
	public ArrayList<HashMap<String, String>> getXysbjxjSearchTitle() {
		ArrayList<HashMap<String, String>> result = new ArrayList<HashMap<String, String>>();
		String[] opCols = { "xh", "xm", "zymc", "bjmc", "xysh" };
		String[] cnCols = { "ѧ��", "����", "רҵ����", "�༶����", "��˽��" };
		for (int i = 0; i < opCols.length; i++) {
			HashMap<String, String> temp = new HashMap<String, String>();
			temp.put("en", opCols[i]);
			temp.put("cn", cnCols[i]);
			result.add(temp);
		}
		return result;
	}

	/**
	 * ��˵Ĳ�ѯ����˲�������Ľ����ͨ������������ ֱ�Ӳ�ѯ����Ӧ�༶��רҵ��ѧԺ��ѧ����Ϣ�����ѧ���Ѿ����ͨ��������ʾͨ�����
	 * 
	 * @param userName
	 *            ��ǰ�û����û���
	 * @param pjpyModel
	 *            model
	 * @return
	 */
	public ArrayList<String[]> getShSearch(String userName, String userType,
			PjpyXbemyXysbjxjModel pjpyModel) {
		ArrayList<String[]> rs = new ArrayList<String[]>();
		String sql = "";
		String xydm = pjpyModel.getXydm();
		String zydm = pjpyModel.getZydm();
		String bjdm = pjpyModel.getBjdm();
		String nj = pjpyModel.getNj();
		String xn = pjpyModel.getXn();
		String xmdm = pjpyModel.getXmdm();// �����Ŀ���루��ѧ��Ĵ���������ƺŵĴ��룩
		// ����ı���
		String[] opCols = { "key", "bgcolor", "xh", "xm", "zymc", "bjmc", "sh" };
		// �����û������ж��û��Ľ�ɫ
		char js = BaseServicesUtil.checkUserToGroup(userName, "����") ? 'a'
				: (BaseServicesUtil.checkUserToGroup(userName, "����") ? 'b'
						: (BaseServicesUtil.checkUserToGroup(userName, "ѧ����") && !userName.equalsIgnoreCase(jsName)? 'c' //ѧ����
								: 'd')); //����Ա
		// �ֽ�ɫ����sql
		switch (js) {
		case 'a':// ����
		{
			sql = "SELECT a.xh||b.xn||b.jxjdm key,a.xh||b.xn||b.jxjdm key,(case nvl(b.jwcsh,'δ���') when 'ͨ��' then '#99FFCC' when 'δ���' then '#FFFFFF'  end) bgcolor,a.xh,a.xm,a.zymc,a.bjmc,b.jwcsh sh FROM VIEW_XSJBXX A LEFT JOIN XSJXJB B ";
			break;
		}
		case 'b':// ����
		{
			sql = "SELECT a.xh||b.xn||b.jxjdm key,(case nvl(b.cwcsh,'δ���') when 'ͨ��' then '#99FFCC' when 'δ���' then '#FFFFFF' end) bgcolor,a.xh,a.xm,a.zymc,a.bjmc,b.cwcsh sh FROM VIEW_XSJBXX A LEFT JOIN XSJXJB B ";
			break;
		}
		case 'c':// ѧ����
		{
			sql = "SELECT b.xh||b.xn||b.jxjdm key,decode(nvl(b.xscsh,'δ���'),'ͨ��','#99FFCC','#FFFFFF') bgcolor,a.xh,a.xm,a.zymc,a.bjmc,b.xscsh sh FROM VIEW_XSJBXX A LEFT JOIN XSJXJB B ";
			break;
		}
		case 'd':// ����Ա
		{
			opCols = new String[]{"key", "bgcolor", "xh", "xm", "zymc", "bjmc", "xysh", "jwcsh", "cwcsh",
			"xscsh", "zzjg" };
			sql = "SELECT a.xh||b.xn||b.jxjdm key,(case when (xysh = 'ͨ��' and xscsh = 'ͨ��' and jwcsh = 'ͨ��' and cwcsh = 'ͨ��') then '#99FFCC' else '#FFFFFF' end) bgcolor,a.xh,a.xm,a.zymc,a.bjmc,b.xysh,b.jwcsh,b.cwcsh,b.xscsh,(case when (xysh = 'ͨ��' and xscsh = 'ͨ��' and jwcsh = 'ͨ��' and cwcsh = 'ͨ��') then 'ͨ��' else '��ͨ��' end) zzjg FROM VIEW_XSJBXX A LEFT JOIN XSJXJB B ";
			//decode(nvl(b.jwcsh,'δ���'),'ͨ��',decode(nvl(b.cwcsh,'δ���'),'��ͨ��',decode(nvl(b.cwcsh,'δ���'),'��ͨ��','#99CCFF','#FFFFFF'),'#FFFFFF'),'#FFFFFF')
			break;
		}
		}
		StringBuffer whereSql = new StringBuffer(" ON 1=1 AND A.XH=B.XH AND  B.XN=? and B.JXJDM=? where 1=1 and b.xysh='ͨ��' ");//ѧԺ���ͨ�����������ſ������
		ArrayList<String> values = new ArrayList<String>();
		if (!StringUtils.isNull(xn)) {// ѧ���Ǳ�ѡ
			values.add(xn);
		}
		if (!StringUtils.isNull(xmdm)) {// ��ѧ���Ǳ�ѡ
			values.add(xmdm);
		}

		if (!StringUtils.isNull(xydm)) {
			whereSql.append("and A.XYDM=?");
			values.add(xydm);
		}
		if (!StringUtils.isNull(zydm)) {
			whereSql.append("and A.ZYDM=?");
			values.add(zydm);
		}
		if (!StringUtils.isNull(bjdm)) {
			whereSql.append("and A.BJDM=?");
			values.add(bjdm);
		}
		if (!StringUtils.isNull(nj)) {
			whereSql.append("and A.NJ=?");
			values.add(nj);
		}
		rs = dao.rsToVator(sql + whereSql, values.toArray(new String[0]), opCols);
		return rs;
	}

	/**
	 * @return ����Ա��˲鿴ʱ��������˲�ѯ�ı�ͷ
	 */
	public ArrayList<HashMap<String, String>> getAdminShTitle() {
		ArrayList<HashMap<String, String>> result = new ArrayList<HashMap<String, String>>();
		// �����뷽��getXysbjxjSearch�е��������һ��
		String[] opCols = { "xh", "xm", "zymc", "bjmc", "xysh", "jwcsh", "cwcsh",
				"xscsh", "zzjg" };
		String[] cnCols = { "ѧ��", "����", "רҵ����", "�༶����", "ѧԺ���", "�������", "�������",
				"ѧ�������" ,"���ս��"};
		for (int i = 0; i < opCols.length; i++) {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("en", opCols[i]);
			map.put("cn", cnCols[i]);
			result.add(map);
			map = null;
		}
		return result;
	}
	
	/**
	 * ѧԺ��˽��
	 * @param xn
	 * @param jxjdm
	 * @param keys
	 * @param shjg
	 * @return
	 */
	public boolean[] xyshResult(String xn, String jxjdm,
			String[] keys, String shjg) throws Exception {
		//keys�п��ܽ�����ѧ�ţ�Ҳ������xh||xn||jxjdm
		//����Ҫ���б���ǰ�ļ��
		boolean[] result = new boolean[keys.length];
		String tableName = "xsjxjb";
		String pk        = "xh||xn||jxjdm";
		String insertSql = "insert into xsjxjb(xh,xn,jxjdm,xysh) values(?,?,?,?)";
		String shjgTemp  = "tg".equalsIgnoreCase(shjg) ? "ͨ��" : ("btg".equalsIgnoreCase(shjg) ? "��ͨ��" : "δ���");
		String updateSql = "update xsjxjb set xysh=? where xh||xn||jxjdm = ?";
		boolean[] exists = dao.checkExists(tableName, pk, keys);
		for(int i=0;i<exists.length;i++){
			if(!exists[i]){//ѧ����ѧ����в�������Ӧ��¼
				String[] input = {keys[i],xn,jxjdm,shjgTemp};
				result[i]      = dao.runUpdate(insertSql, input);
			} else {//�Ѿ�������˼�¼���޸����״̬
				String[] input = {shjgTemp ,keys[i]};
				result[i]      = dao.runUpdate(updateSql, input);
			}
		}
		return result;
	}
	/**
	 * ѧ�������
	 * @param xn
	 * @param jxjdm
	 * @param keys
	 * @param shjg
	 * @return
	 */
	public boolean[] xscshResult(String xn, String jxjdm,
			String[] keys, String shjg) throws Exception {
        //keys�п��ܽ�����ѧ�ţ�Ҳ������xh||xn||jxjdm
		//����Ҫ���б���ǰ�ļ��
		boolean[] result = new boolean[keys.length];
		String tableName = "xsjxjb";
		String pk        = "xh||xn||jxjdm";
		String insertSql = "insert into xsjxjb(xh,xn,jxjdm,xscsh) values(?,?,?,?)";
		String shjgTemp  = "tg".equalsIgnoreCase(shjg) ? "ͨ��" : ("btg".equalsIgnoreCase(shjg) ? "��ͨ��" : "δ���");
		String updateSql = "update xsjxjb set xscsh=? where xh||xn||jxjdm = ?";
		boolean[] exists = dao.checkExists(tableName, pk, keys);
		for(int i=0;i<exists.length;i++){
			if(!exists[i]){//ѧ����ѧ����в�������Ӧ��¼
				String[] input = {keys[i],xn,jxjdm,shjgTemp};
				result[i]      = dao.runUpdate(insertSql, input);
			} else {//�Ѿ�������˼�¼���޸����״̬
				String[] input = {shjgTemp ,keys[i]};
				result[i]      = dao.runUpdate(updateSql, input);
			}
		}
		return result;
	}
	/**
	 * �������
	 * @param xn
	 * @param jxjdm
	 * @param keys
	 * @param shjg
	 * @return
	 */
	public boolean[] jwcshResult(String xn, String jxjdm,
			String[] keys, String shjg) throws Exception {
//		keys�п��ܽ�����ѧ�ţ�Ҳ������xh||xn||jxjdm
		//����Ҫ���б���ǰ�ļ��
		boolean[] result = new boolean[keys.length];
		String tableName = "xsjxjb";
		String pk        = "xh||xn||jxjdm";
		String insertSql = "insert into xsjxjb(xh,xn,jxjdm,jwcsh) values(?,?,?,?)";
		String shjgTemp  = "tg".equalsIgnoreCase(shjg) ? "ͨ��" : ("btg".equalsIgnoreCase(shjg) ? "��ͨ��" : "δ���");
		String updateSql = "update xsjxjb set jwcsh=? where xh||xn||jxjdm = ?";
		boolean[] exists = dao.checkExists(tableName, pk, keys);
		for(int i=0;i<exists.length;i++){
			if(!exists[i]){//ѧ����ѧ����в�������Ӧ��¼
				String[] input = {keys[i],xn,jxjdm,shjgTemp};
				result[i]      = dao.runUpdate(insertSql, input);
			} else {//�Ѿ�������˼�¼���޸����״̬
				String[] input = {shjgTemp ,keys[i]};
				result[i]      = dao.runUpdate(updateSql, input);
			}
		}
		return result;
	}
	/**
	 * �������
	 * @param xn
	 * @param jxjdm
	 * @param keys
	 * @param shjg
	 * @return
	 */
	public boolean[] cwcshResult(String xn, String jxjdm,
			String[] keys, String shjg) throws Exception {
//		keys�п��ܽ�����ѧ�ţ�Ҳ������xh||xn||jxjdm
		//����Ҫ���б���ǰ�ļ��
		boolean[] result = new boolean[keys.length];
		String tableName = "xsjxjb";
		String pk        = "xh||xn||jxjdm";
		String insertSql = "insert into xsjxjb(xh,xn,jxjdm,cwcsh) values(?,?,?,?)";
		String shjgTemp  = "tg".equalsIgnoreCase(shjg) ? "ͨ��" : ("btg".equalsIgnoreCase(shjg) ? "��ͨ��" : "δ���");
		String updateSql = "update xsjxjb set cwcsh=? where xh||xn||jxjdm = ?";
		boolean[] exists = dao.checkExists(tableName, pk, keys);
		for(int i=0;i<exists.length;i++){
			if(!exists[i]){//ѧ����ѧ����в�������Ӧ��¼
				String[] input = {keys[i],xn,jxjdm,shjgTemp};
				result[i]      = dao.runUpdate(insertSql, input);
			} else {//�Ѿ�������˼�¼���޸����״̬
				String[] input = {shjgTemp ,keys[i]};
				result[i]      = dao.runUpdate(updateSql, input);
			}
		}
		return result;
	}
	
	/**
	 * ѧԺ��ѧ����˽����ѯ
	 * @param pjpyModel
	 * @param jg
	 * @return
	 */
	public ArrayList<String[]> getJxjShJgSearch(PjpyXbemyXysbjxjModel pjpyModel, String jg) {
		ArrayList<String[]> rs = new ArrayList<String[]>();
		String sql = "";
		String xydm = pjpyModel.getXydm();
		String zydm = pjpyModel.getZydm();
		String bjdm = pjpyModel.getBjdm();
		String nj = pjpyModel.getNj();
		String xn = pjpyModel.getXn();
		String xmdm = pjpyModel.getXmdm();// �����Ŀ���루��ѧ��Ĵ���������ƺŵĴ��룩
		String[] opCols = new String[]{"key", "bgcolor", "xh", "xm", "zymc", "bjmc", "xysh", "jwcsh", "cwcsh",
				"xscsh", "zzjg" };
		StringBuffer whereSql = null;//��ѯ����
		if (jg.equalsIgnoreCase("tg") && !jg.equalsIgnoreCase("")){//���ͨ��
			sql = "SELECT a.xh||b.xn||b.jxjdm key,(case when (xysh = 'ͨ��' and xscsh = 'ͨ��' and jwcsh = 'ͨ��' and cwcsh = 'ͨ��') then '#99FFCC' else '#FFFFFF' end) bgcolor,a.xh,a.xm,a.zymc,a.bjmc,b.xysh,b.jwcsh,b.cwcsh,b.xscsh,(case when (xysh = 'ͨ��' and xscsh = 'ͨ��' and jwcsh = 'ͨ��' and cwcsh = 'ͨ��') then 'ͨ��' else '��ͨ��' end) zzjg FROM VIEW_XSJBXX A LEFT JOIN XSJXJB B ";
			whereSql = new StringBuffer(" ON 1=1 AND A.XH=B.XH AND  B.XN=? and B.JXJDM=? where 1=1 and b.xysh='ͨ��' and b.xscsh='ͨ��' and jwcsh='ͨ��' and cwcsh='ͨ��'");
		}else if (jg.equalsIgnoreCase("btg") && !jg.equalsIgnoreCase("")){//δ���ͨ��
			sql = "SELECT a.xh||b.xn||b.jxjdm key,(case when (xysh = 'ͨ��' and xscsh = 'ͨ��' and jwcsh = 'ͨ��' and cwcsh = 'ͨ��') then '#99FFCC' else '#FFFFFF' end) bgcolor,a.xh,a.xm,a.zymc,a.bjmc,b.xysh,b.jwcsh,b.cwcsh,b.xscsh,(case when (xysh = 'ͨ��' and xscsh = 'ͨ��' and jwcsh = 'ͨ��' and cwcsh = 'ͨ��') then 'ͨ��' else '��ͨ��' end) zzjg FROM VIEW_XSJBXX A LEFT JOIN XSJXJB B ";
			whereSql = new StringBuffer(" ON 1=1 AND A.XH=B.XH AND  B.XN=? and B.JXJDM=? where 1=1 and b.xysh='ͨ��' and (((xscsh = '��ͨ��' or xscsh = 'δ���') or xscsh is null) or ((jwcsh = '��ͨ��' or jwcsh = 'δ���') or jwcsh is null) or ((cwcsh = '��ͨ��' or cwcsh = 'δ���') or cwcsh is null)) ");//���δͨ��
		}else{//ͨ����δͨ������ʾ
			sql = "SELECT a.xh||b.xn||b.jxjdm key,(case when (xysh = 'ͨ��' and xscsh = 'ͨ��' and jwcsh = 'ͨ��' and cwcsh = 'ͨ��') then '#99FFCC' else '#FFFFFF' end) bgcolor,a.xh,a.xm,a.zymc,a.bjmc,b.xysh,b.jwcsh,b.cwcsh,b.xscsh,(case when (xysh = 'ͨ��' and xscsh = 'ͨ��' and jwcsh = 'ͨ��' and cwcsh = 'ͨ��') then 'ͨ��' else '��ͨ��' end) zzjg FROM VIEW_XSJBXX A LEFT JOIN XSJXJB B ";
			whereSql = new StringBuffer(" ON 1=1 AND A.XH=B.XH AND  B.XN=? and B.JXJDM=? where 1=1 and b.xysh='ͨ��' ");
		}//end if
		ArrayList<String> values = new ArrayList<String>();
		if (!StringUtils.isNull(xn)) {// ѧ���Ǳ�ѡ
			values.add(xn);
		}//end if
		if (!StringUtils.isNull(xmdm)) {// ��ѧ���Ǳ�ѡ
			values.add(xmdm);
		}//end if
		if (!StringUtils.isNull(xydm)) {
			whereSql.append("and A.XYDM=?");
			values.add(xydm);
		}//end if
		if (!StringUtils.isNull(zydm)) {
			whereSql.append("and A.ZYDM=?");
			values.add(zydm);
		}//end if
		if (!StringUtils.isNull(bjdm)) {
			whereSql.append("and A.BJDM=?");
			values.add(bjdm);
		}//end if
		if (!StringUtils.isNull(nj)) {
			whereSql.append("and A.NJ=?");
			values.add(nj);
		}//end if
		rs = dao.rsToVator(sql + whereSql, values.toArray(new String[0]), opCols);
		return rs;
	}
	
	public ArrayList<String[]> getJxjShJgExp(PjpyXbemyXysbjxjModel pjpyModel, String jg) {
		ArrayList<String[]> rs = new ArrayList<String[]>();
		String sql = "";
		String xydm = pjpyModel.getXydm();
		String zydm = pjpyModel.getZydm();
		String bjdm = pjpyModel.getBjdm();
		String nj = pjpyModel.getNj();
		String xn = pjpyModel.getXn();
		String xmdm = pjpyModel.getXmdm();// �����Ŀ���루��ѧ��Ĵ���������ƺŵĴ��룩
		String[] opCols = new String[]{"xh", "xm", "zymc", "bjmc", "xysh", "jwcsh", "cwcsh",
				"xscsh", "zzjg" };
		StringBuffer whereSql = null;//��ѯ����
		if (jg.equalsIgnoreCase("tg") && !jg.equalsIgnoreCase("")){//���ͨ��
			sql = "SELECT a.xh||b.xn||b.jxjdm key,(case when (xysh = 'ͨ��' and xscsh = 'ͨ��' and jwcsh = 'ͨ��' and cwcsh = 'ͨ��') then '#99FFCC' else '#FFFFFF' end) bgcolor,a.xh,a.xm,a.zymc,a.bjmc,b.xysh,b.jwcsh,b.cwcsh,b.xscsh,(case when (xysh = 'ͨ��' and xscsh = 'ͨ��' and jwcsh = 'ͨ��' and cwcsh = 'ͨ��') then 'ͨ��' else '��ͨ��' end) zzjg FROM VIEW_XSJBXX A LEFT JOIN XSJXJB B ";
			whereSql = new StringBuffer(" ON 1=1 AND A.XH=B.XH AND  B.XN=? and B.JXJDM=? where 1=1 and b.xysh='ͨ��' and b.xscsh='ͨ��' and jwcsh='ͨ��' and cwcsh='ͨ��'");
		}else if (jg.equalsIgnoreCase("btg") && !jg.equalsIgnoreCase("")){//δ���ͨ��
			sql = "SELECT a.xh||b.xn||b.jxjdm key,(case when (xysh = 'ͨ��' and xscsh = 'ͨ��' and jwcsh = 'ͨ��' and cwcsh = 'ͨ��') then '#99FFCC' else '#FFFFFF' end) bgcolor,a.xh,a.xm,a.zymc,a.bjmc,b.xysh,b.jwcsh,b.cwcsh,b.xscsh,(case when (xysh = 'ͨ��' and xscsh = 'ͨ��' and jwcsh = 'ͨ��' and cwcsh = 'ͨ��') then 'ͨ��' else '��ͨ��' end) zzjg FROM VIEW_XSJBXX A LEFT JOIN XSJXJB B ";
			whereSql = new StringBuffer(" ON 1=1 AND A.XH=B.XH AND  B.XN=? and B.JXJDM=? where 1=1 and b.xysh='ͨ��' and (((xscsh = '��ͨ��' or xscsh = 'δ���') or xscsh is null) or ((jwcsh = '��ͨ��' or jwcsh = 'δ���') or jwcsh is null) or ((cwcsh = '��ͨ��' or cwcsh = 'δ���') or cwcsh is null)) ");//���δͨ��
		}else{//ͨ����δͨ������ʾ
			sql = "SELECT a.xh||b.xn||b.jxjdm key,(case when (xysh = 'ͨ��' and xscsh = 'ͨ��' and jwcsh = 'ͨ��' and cwcsh = 'ͨ��') then '#99FFCC' else '#FFFFFF' end) bgcolor,a.xh,a.xm,a.zymc,a.bjmc,b.xysh,b.jwcsh,b.cwcsh,b.xscsh,(case when (xysh = 'ͨ��' and xscsh = 'ͨ��' and jwcsh = 'ͨ��' and cwcsh = 'ͨ��') then 'ͨ��' else '��ͨ��' end) zzjg FROM VIEW_XSJBXX A LEFT JOIN XSJXJB B ";
			whereSql = new StringBuffer(" ON 1=1 AND A.XH=B.XH AND  B.XN=? and B.JXJDM=? where 1=1 and b.xysh='ͨ��' ");
		}//end if
		ArrayList<String> values = new ArrayList<String>();
		if (!StringUtils.isNull(xn)) {// ѧ���Ǳ�ѡ
			values.add(xn);
		}//end if
		if (!StringUtils.isNull(xmdm)) {// ��ѧ���Ǳ�ѡ
			values.add(xmdm);
		}//end if
		if (!StringUtils.isNull(xydm)) {
			whereSql.append("and A.XYDM=?");
			values.add(xydm);
		}//end if
		if (!StringUtils.isNull(zydm)) {
			whereSql.append("and A.ZYDM=?");
			values.add(zydm);
		}//end if
		if (!StringUtils.isNull(bjdm)) {
			whereSql.append("and A.BJDM=?");
			values.add(bjdm);
		}//end if
		if (!StringUtils.isNull(nj)) {
			whereSql.append("and A.NJ=?");
			values.add(nj);
		}//end if
		rs = dao.rsToVator(sql + whereSql, values.toArray(new String[0]), opCols);
		return rs;
	}
}
