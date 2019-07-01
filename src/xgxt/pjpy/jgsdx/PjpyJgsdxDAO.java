
package xgxt.pjpy.jgsdx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.utils.String.StringUtils;

/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: ����ɽ��ѧ��������DAO</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: ����</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-06-026</p>
 */
public class PjpyJgsdxDAO {

	DAO dao = DAO.getInstance();//�������ݲ���DAO
	List<String> values = new ArrayList<String>();//�洢��ѯ����ֵ
	String rownumzd = "";//�Զ����ʱ���ֶ�
	
	/**
	 * ��ȡ��ѯ������ֵ
	 * getwheresql ---- ��ȡ��ѯ���� 
	 * @param xjbjQryModel
	 * @return
	 * @throws Exception
	 */
	public StringBuffer getWhereSql2(WmbjSqModel xjbjQryModel) throws Exception {
		StringBuffer whereSql = new StringBuffer();
		String xn = xjbjQryModel.getXn();
		String shxm = xjbjQryModel.getShxm();
		String xydm = xjbjQryModel.getXydm();
		String zydm = xjbjQryModel.getZydm();
		String bjdm = xjbjQryModel.getBjdm();
		String xq = xjbjQryModel.getXq();
		String rychdm = getRychdm("�����༶");//��ȡ�����ƺŴ���
		shxm = !StringUtils.isNull(shxm) && StringUtils.isEqual(shxm, "xjbj") ? rychdm : "";
		if (!StringUtils.isNull(xn)) {
			whereSql.append(" and xn = ?");
			values.add(xn);
		}//end if
		if (!StringUtils.isNull(xydm)) {
			whereSql.append(" and xydm = ?");
			values.add(xydm);
		}//end if
		if (!StringUtils.isNull(zydm)) {
			whereSql.append(" and zydm = ?");
			values.add(zydm);
		}//end if
		if (!StringUtils.isNull(bjdm)) {
			whereSql.append(" and bjdm = ?");
			values.add(bjdm);
		}//end if
		if (!StringUtils.isNull(shxm)) {
			whereSql.append(" and rychdm = ?");
			values.add(shxm);
		}//end if
		if (!StringUtils.isNull(xq)) {
			whereSql.append(" and xq = ?");
			values.add(xq);
		}//end if
		return whereSql;
	}
	
	/**
	 * ��ȡרҵ��ѧ���б�
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getZyjxjList() throws Exception {
		List<HashMap<String, String>> zyjxjList = new ArrayList<HashMap<String, String>>();
		String sql = "select jxjdm,jxjmc from zyjxjdmb";
		zyjxjList = dao.getList(sql, new String[]{}, new String[]{"jxjdm", "jxjmc"});
		return zyjxjList;
	}
	
	/**
	 * ͨ����ѧ������ȡ��ѧ���
	 * getJxjJeByJxjdm ---- ͨ����ѧ������ȡ��ѧ��� 
	 * @param jxjdm
	 * @return
	 * @throws Exception
	 */
	public String getJxjJeByJxjdm(String jxjdm) throws Exception {
		String jlje = "";
		String sql = "select jlje from zyjxjdmb where jxjdm = ?";
		String[] tmpList = dao.getOneRs(sql, new String[]{jxjdm}, new String[]{"jlje"});
		if (tmpList != null && tmpList.length > 0) {
			jlje = tmpList[0];
		}//end if
		return jlje;
	}
	
	/**
	 * ����Ա��ѯרҵ��ѧ���ͷ
	 * fdyZyjxjsbTitle ---- ����Ա��ѯרҵ��ѧ���ͷ
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> fdyZyjxjsbTitle() throws Exception {
		List<HashMap<String, String>> topList = new ArrayList<HashMap<String,String>>();
		String[] opCols = {"jxjdm","xh||xn||jxjdm", "xh", "xm", "xn","xymc", "zymc", "bjmc", "zhszpm", "xxcjpm", "jxjjb", "fdysh"};
		String[] cnCols = {"jxjdm","����", "ѧ��", "����","ѧ��", "ѧԺ����", "רҵ����", "�༶����", "�ۺ���������","ѧϰ�ɼ�����","��ѧ�𼶱�","����Ա���" };
		for (int i = 0; i < opCols.length; i++) {
			HashMap<String, String> temp = new HashMap<String, String>();
			temp.put("en", opCols[i]);
			temp.put("cn", cnCols[i]);
			topList.add(temp);
		}
		return topList;
	}
	
	/**
	 * ѧԺ��ѯרҵ��ѧ���ͷ
	 * XyZyjxjsbTitle ---- ѧԺ��ѯרҵ��ѧ���ͷ
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> xyZyjxjsbTitle() throws Exception {
		List<HashMap<String, String>> topList = new ArrayList<HashMap<String,String>>();
		String[] opCols = {"jxjdm","xh||xn||jxjdm", "xh", "xm", "xn", "xymc", "zymc", "bjmc", "zhszpm", "xxcjpm", "jxjjb", "xysh"};
		String[] cnCols = {"jxjdm","����", "ѧ��", "����", "ѧ��", "ѧԺ����", "רҵ����", "�༶����", "�ۺ���������","ѧϰ�ɼ�����","��ѧ�𼶱�","ѧԺ���" };
		for (int i = 0; i < opCols.length; i++) {
			HashMap<String, String> temp = new HashMap<String, String>();
			temp.put("en", opCols[i]);
			temp.put("cn", cnCols[i]);
			topList.add(temp);
		}
		return topList;
	}
	
	/**
	 * ѧУ��ѯרҵ��ѧ���ͷ
	 * XxZyjxjsbTitle ---- ѧУ��ѯרҵ��ѧ���ͷ
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> xxZyjxjsbTitle() throws Exception {
		List<HashMap<String, String>> topList = new ArrayList<HashMap<String,String>>();
		String[] opCols = {"jxjdm","xh||xn||jxjdm", "xh", "xm", "xn","xymc", "zymc", "bjmc", "zhszpm", "xxcjpm", "jxjjb", "xxsh"};
		String[] cnCols = {"jxjdm","����", "ѧ��", "����","ѧ��", "ѧԺ����", "רҵ����", "�༶����", "�ۺ���������","ѧϰ�ɼ�����","��ѧ�𼶱�","ѧУ���" };
		for (int i = 0; i < opCols.length; i++) {
			HashMap<String, String> temp = new HashMap<String, String>();
			temp.put("en", opCols[i]);
			temp.put("cn", cnCols[i]);
			topList.add(temp);
		}
		return topList;
	}
	
	/**
	 * ����Ա��ѯרҵ��ѧ����
	 * fdyZyjxjsbResult ---- ����Ա��ѯרҵ��ѧ���� 
	 * @param zyjxjModel
	 * @return
	 * @throws Exception
	 */
	public List<String[]> fdyZyjxjsbResult(ZyjxjQryModel zyjxjModel) throws Exception {
		List<String[]> resList = new ArrayList<String[]>();
		String[] opCols = {"jxjdm","key", "xh", "xm","xn", "xymc", "zymc", "bjmc", "zhszpm", "xxcjpm", "jxjjb", "fdysh"};
		String sql = "select '"+zyjxjModel.getJxjdm()+"' jxjdm,a.xh||b.xn||b.jxjdm key,a.xh,a.xm,'"+zyjxjModel.getXn()+"' xn,a.xymc,a.zymc,a.bjmc,b.zhszpm,b.xxcjpm,b.jxjjb,b.fdysh from view_xsjbxx a left join view_xszyjxj b on a.xh=b.xh and b.xn=? and b.jxjdm=? where 1=1 and a.xydm='"+zyjxjModel.getXydm()+"'";
		StringBuffer whereSql = getWhereSql(zyjxjModel);
		resList = dao.rsToVator(sql + whereSql, values != null ? values.toArray(new String[0]) : new String[]{}, opCols);
		System.out.println(values.size());
		System.out.println(sql+whereSql);
		return resList;
	}
	
	/**
	 * ѧԺ��ѯרҵ��ѧ����
	 * xyZyjxjsbResult ---- ѧԺ��ѯרҵ��ѧ���� 
	 * @param zyjxjModel
	 * @return
	 * @throws Exception
	 */
	public List<String[]> xyZyjxjsbResult(ZyjxjQryModel zyjxjModel) throws Exception {
		List<String[]> resList = new ArrayList<String[]>();
		String[] opCols = {"jxjdm","a.xh||a.xn||a.jxjdm", "xh", "xm","xn", "xymc", "zymc", "bjmc", "zhszpm", "xxcjpm", "jxjjb", "xysh"};
		String sql = "select a.jxjdm,a.xh||a.xn||a.jxjdm,a.xh,a.xm,a.xn,a.xymc,a.zymc,a.bjmc,a.zhszpm,a.xxcjpm,a.jxjjb,a.xysh from view_xszyjxj a where 1=1 and a.fdysh='ͨ��' and a.xn=? and a.jxjdm=? ";
		StringBuffer whereSql = getWhereSql(zyjxjModel);
		resList = dao.rsToVator(sql + whereSql, values != null ? values.toArray(new String[0]) : new String[]{}, opCols);
		System.out.println(sql+whereSql);
		return resList;
	}
	
	/**
	 * ѧУ��ѯרҵ��ѧ����
	 * xyZyjxjsbResult ---- ѧУ��ѯרҵ��ѧ���� 
	 * @param zyjxjModel
	 * @return
	 * @throws Exception
	 */
	public List<String[]> xxZyjxjsbResult(ZyjxjQryModel zyjxjModel) throws Exception {
		List<String[]> resList = new ArrayList<String[]>();
		String[] opCols = {"jxjdm","a.xh||a.xn||a.jxjdm", "xh", "xm", "xn","xymc", "zymc", "bjmc", "zhszpm", "xxcjpm", "jxjjb", "xxsh"};
		String sql = "select a.jxjdm,a.xh||a.xn||a.jxjdm,a.xh,a.xm,a.xn,a.xymc,a.zymc,a.bjmc,a.zhszpm,a.xxcjpm,a.jxjjb,a.xxsh from view_xszyjxj a where 1=1 and a.fdysh='ͨ��' and a.xysh='ͨ��' and a.xn=? and a.jxjdm=?";
		StringBuffer whereSql = getWhereSql(zyjxjModel);
		resList = dao.rsToVator(sql + whereSql, values != null ? values.toArray(new String[0]) : new String[]{}, opCols);
		return resList;
	}
	
	/**
	 * ���÷������ڻ�ȡ��ѯ����ֵ
	 * @param zyjxjModel
	 * @return
	 * @throws Exception
	 */
	public StringBuffer getWhereSql(ZyjxjQryModel zyjxjModel) throws Exception {
		StringBuffer whereSql = new StringBuffer();
		String xn = zyjxjModel.getXn();
		String nj = zyjxjModel.getNj();
		String jxjdm = zyjxjModel.getJxjdm();
		String bjdm = zyjxjModel.getBjdm();
		String xydm = zyjxjModel.getXydm();
		String zydm = zyjxjModel.getZydm();
		if (!StringUtils.isNull(xn)) {//ѧ���ѡ��
			//whereSql.append(" and xn = ?");
			values.add(xn);
		}
		if (!StringUtils.isNull(jxjdm)) {//ѧ���ѡ��
			//whereSql.append(" and jxjdm = ?");
			values.add(jxjdm);
		}
		if (!StringUtils.isNull(nj)) {
			whereSql.append(" and a.nj = ?");
			values.add(nj);
		}
		if (!StringUtils.isNull(bjdm)) {
			whereSql.append(" and a.bjdm = ?");
			values.add(bjdm);
		}
		if (!StringUtils.isNull(xydm)) {
			whereSql.append(" and a.xydm = ?");
			values.add(xydm);
		}
		if (!StringUtils.isNull(zydm)) {
			whereSql.append(" and a.zydm = ?");
			values.add(zydm);
		}
		return whereSql;
	}
	
	/**
	 * ͨ��������ȡ�ۺ����ʺ�ѧϰ�ɼ�(����������ѧ�ţ�Ҳ������ѧ�ż�ѧ��ӽ�ѧ�����)
	 * getZhszandXxcjByPk ---- ͨ��������ȡ�ۺ����ʺ�ѧϰ�ɼ�
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getZhszandXxcjByPk(String pkValue, String xh) throws Exception {
		HashMap<String, String> resMap = new HashMap<String, String>();
		String sql = "";
		//�������Ƿ������Ӧ��¼
		boolean[] bExists = dao.checkExists("view_xszyjxj", "xh||xn||jxjdm", new String[]{pkValue});
		if (bExists != null && bExists.length > 0) {
			if (bExists[0]) {//���ڸü�¼
				sql = "select xh,xm,zhszpm,xxcjpm,bz,sfsf,xn,xq,fdysh from view_xszyjxj where xh||xn||jxjdm=?";
				resMap = dao.getMapNotOut(sql, new String[]{pkValue});
			}else {//��������ֻȡѧ�ţ�����
				sql = "select xh,xm from view_xsjbxx where xh = ?";
				resMap = dao.getMapNotOut(sql, new String[]{xh});
			}
		}
		return resMap;
	}
	
	/**
	 * �ۺ����ʺ�ѧϰ�ɼ�����
	 * zhszandxxcjSave ---- �ۺ����ʺ�ѧϰ�ɼ�����
	 * @param zxsaveModel
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean zhszandxxcjSave(ZhszandXxcjSaveModel zxsaveModel, HttpServletRequest request) throws Exception {
		boolean bFlag = false;
		String zhszpm = DealString.toGBK(zxsaveModel.getZhszpm());
		String xxcjpm = DealString.toGBK(zxsaveModel.getXxcjpm());
		String bz = DealString.toGBK(zxsaveModel.getBz());
		String sfsf = DealString.toGBK(zxsaveModel.getSfsf());
		String xh = DealString.toGBK(zxsaveModel.getXh());
		String pkValue = DealString.toGBK(zxsaveModel.getPkValue());
		String xq = zxsaveModel.getXq();
		String xn = zxsaveModel.getXn();
		String jxjdm = zxsaveModel.getJxjdm();
		String shzt = DealString.toGBK(zxsaveModel.getShzt());
		boolean bDel = StandardOperation.delete("xszyjxjb", "xh||xn||jxjdm", pkValue, request);
		if (bDel) {
			bFlag = StandardOperation.insert("xszyjxjb", new String[] { "xh", "xn",
					"jxjdm", "xq", "zhszpm", "xxcjpm", "bz", "sfsf", "fdysh" },
					new String[]{xh,xn,jxjdm,xq,zhszpm,xxcjpm,bz,sfsf,shzt}, request);
		}
		return bFlag;
	}
	
	/**
	 * ����Ա���רҵ��ѧ��
	 * @param zyjxjModel
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean[] zyjxjShByFdy(ZyjxjShModel zyjxjModel, HttpServletRequest request) throws Exception {
		String[] cbv = zyjxjModel.getCbv();
		String res = zyjxjModel.getRes();
		String xn = zyjxjModel.getXn();
		String jxjdm = zyjxjModel.getJxjdm();
		res = StringUtils.isEqual(res, "tg") ? "ͨ��" : (StringUtils.isEqual(res, "btg") ? "��ͨ��" : "δ���");
		boolean[] result = new boolean[cbv.length];
		String tableName = "xszyjxjb";
		String pk = "xh||xn||jxjdm";
		String insertSql = "insert into " + tableName + " (xh,xn,jxjdm,fdysh) values (?,?,?,?)";
		String updateSql = "update " + tableName + " set fdysh = ? where " + pk + " = ?";
		boolean[] exists = dao.checkExists(tableName, pk, cbv);
		for (int i = 0 ; i < exists.length; i++) {
			if (exists[i]) {//���ڼ�¼�����޸�
				String[] input = new String[]{res, cbv[i]};
				result[i] = dao.runUpdate(updateSql, input);
			}else {//�����ڽ��в���
				String[] input = {cbv[i],xn,jxjdm,res};
				result[i]      = dao.runUpdate(insertSql, input);
			}
		}
		return result;
	}
	
	/**
	 * ѧԺ���רҵ��ѧ��
	 * @param zyjxjModel
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean[] zyjxjShByXy(ZyjxjShModel zyjxjModel, HttpServletRequest request) throws Exception {
		String[] cbv = zyjxjModel.getCbv();
		String res = zyjxjModel.getRes();
		res = StringUtils.isEqual(res, "tg") ? "ͨ��" : (StringUtils.isEqual(res, "btg") ? "��ͨ��" : "δ���");
		boolean[] result = new boolean[cbv.length];
		for (int i = 0; i < cbv.length; i++) {
			result[i] = StandardOperation.update("xszyjxjb", new String[]{"xysh"}, new String[]{res}, "xh||xn||jxjdm", cbv[i], request);
		}
		return result;
	}
	
	/**
	 * ѧУ���רҵ��ѧ��
	 * @param zyjxjModel
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean[] zyjxjShByXx(ZyjxjShModel zyjxjModel, HttpServletRequest request) throws Exception {
		String[] cbv = zyjxjModel.getCbv();
		String res = zyjxjModel.getRes();
		res = StringUtils.isEqual(res, "tg") ? "ͨ��" : (StringUtils.isEqual(res, "btg") ? "��ͨ��" : "δ���");
		boolean[] result = new boolean[cbv.length];
		for (int i = 0; i < cbv.length; i++) {
			result[i] = StandardOperation.update("xszyjxjb", new String[]{"xxsh"}, new String[]{res}, "xh||xn||jxjdm", cbv[i], request);
		}
		return result;
	}
	
	/**
	 * ��������б�
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public List getChkList(int type) throws Exception {
		return dao.getChkList(type);
	}
	
	/**
	 * רҵ��ѧ������ɾ��
	 * @param keys
	 * @return
	 * @throws Exception
	 */
	public String zyjxjblDel(String[] keys) throws Exception {
		StringBuffer pksql1 = new StringBuffer();
		String[] pksql = new String[] {};
		String sql = "";
		for (int i = 0; i < keys.length; i++) {
			String whichxh = DealString.toGBK(keys[i]);// �õ�����
			sql = "delete from xszyjxjb where xh||xn||jxjdm = '" + whichxh + "'";
			// ��������ϳ�sql���
			pksql1.append(sql);
			pksql1.append("!!#!!");
		}// end for
		// sql����ֳ�����
		pksql = pksql1.toString().split("!!#!!");
		int[] judge2 = dao.runBatch(pksql);
		String whichpk = "";
		// �����һ��ɾ��ʧ��
		for (int i = 0; i < judge2.length; i++) {
			if (judge2[i] > 0) {
				whichpk = whichpk + " ��" + String.valueOf(i) + "������ɾ��ʧ��;\n";
			}// end if
		}// end for
		return whichpk;
	}
	
	/**
	 * ��ȡ��ѯ�б�
	 * @param zyjxjModel
	 * @return
	 * @throws Exception
	 */
	public StringBuffer getWhereSql1(ZyjxjQryModel zyjxjModel) throws Exception {
		StringBuffer whereSql = new StringBuffer();
		String xn = zyjxjModel.getXn();
		String jxjdm = zyjxjModel.getJxjdm();
		String nj = zyjxjModel.getNj();
		String xydm = zyjxjModel.getXydm();
		String zydm = zyjxjModel.getZydm();
		String bjdm = zyjxjModel.getBjdm();
		if (!StringUtils.isNull(xn)) {
			whereSql.append(" and xn = ?");
			values.add(xn);
		}if (!StringUtils.isNull(nj)) {
			whereSql.append(" and nj = ?");
			values.add(nj);
		}if (!StringUtils.isNull(jxjdm)) {
			whereSql.append(" and jxjdm = ?");
			values.add(jxjdm);
		}if (!StringUtils.isNull(xydm)) {
			whereSql.append(" and xydm = ?");
			values.add(xydm);
		}if (!StringUtils.isNull(bjdm)) {
			whereSql.append(" and bjdm = ?");
			values.add(bjdm);
		}if (!StringUtils.isNull(zydm)) {
			whereSql.append(" and zydm = ?");
			values.add(zydm);
		}
		return whereSql;
	}
	
	/**
	 * רҵ��ѧ���ӡ�б�
	 * @param zyjxjModel
	 * @return
	 * @throws Exception
	 */
	public List<String[]> zyjxjPrint(ZyjxjQryModel zyjxjModel) throws Exception {
		List<String[]> resList = new ArrayList<String[]>();
		String[] opList = new String[]{"xymc||zymc||bjmc","xh","xm","zhszpm","xxcjpm","jxjmc","jlje"};
		String sql = "select xymc||zymc||bjmc,xh,xm,zhszpm,xxcjpm,jxjmc,jlje from view_xszyjxj where 1=1 and fdysh='ͨ��' and xysh='ͨ��' and xxsh='ͨ��' ";
		StringBuffer whereSql = getWhereSql1(zyjxjModel);
		resList = dao.rsToVator(sql + whereSql, values != null ? values.toArray(new String[0]): new String[]{}, opList);
		return resList;
	}
	
	/**
	 * ͨ�������ƺ����ƻ�ȡ�����ƺŴ���
	 * getrychdm ---- ��ȡ�����ƺŴ��� 
	 * @param rychmc
	 * @return
	 * @throws Exception
	 */
	public String getRychdm (String rychmc) throws Exception {
		String rychdm ="";
		String sql = "select rychdm from pjpy_jtrydmb where rychmc=? and rownum=1";
		String[] rychList = dao.getOneRs(sql, new String[]{rychmc}, new String[]{"rychdm"});
		if (rychList != null && rychList.length > 0) {
			rychdm = rychList[0];
		}
		return rychdm;
	}
	
	/**
	 * ��֤�����Ƿ��ظ�
	 * chkDataByXjbj ---- ��֤�Ƚ��༶�����Ƿ��ظ� 
	 * @param xjbjSqModel
	 * @return
	 * @throws Exception
	 */
	public boolean chkDataByWmbj(WmbjSqModel wmbjsqModel) throws Exception {
		boolean bFlag = false;
		String xn = wmbjsqModel.getXn();
		String xq = wmbjsqModel.getXq();
		String bjdm = wmbjsqModel.getBjdm();
		String rychdm = getRychdm("�����༶");//��ȡ�����ƺŴ���;
		String sql = "select xn,xq,bjdm,rychdm from pjpy_xjbjandwmsqb where xn=? and xq=? and bjdm=? and rychdm=?";
		String[] temList = dao.getOneRs(sql, new String[]{xn, xq, bjdm, rychdm}, new String[]{"xn", "xq", "bjdm", "rychdm"});
		if (temList != null && temList.length > 0) {//����
			bFlag = true;
		}//end if
		return bFlag;
	}
	
	/**
	 * �����Ƚ��༶��Ϣ
	 * savexjbjinfo ---- �����Ƚ��༶��Ϣ 
	 * @param xjbjSqModel
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean saveWmBjInfo(WmbjSqModel xjbjSqModel, HttpServletRequest request) throws Exception {
		boolean bFlag = false;
		String xn = xjbjSqModel.getXn();
		String xq = xjbjSqModel.getXq();
		String bjdm = xjbjSqModel.getBjdm();
		String bzxm = DealString.toGBK(xjbjSqModel.getBzxm());
		String bzr = DealString.toGBK(xjbjSqModel.getBzr());
		String xsrs = xjbjSqModel.getXsrs();
		String zysj = DealString.toGBK(xjbjSqModel.getZysj());
		String[] inList = new String[]{"xn", "xq", "rychdm", "bjdm", "bzxm", "bzr", "xsrs", "zysj"};
		String rychdm = getRychdm("�����༶");//��ȡ�����ƺŴ���
		bFlag = StandardOperation.insert("pjpy_xjbjandwmsqb", inList, new String[]{xn, xq, rychdm, bjdm, bzxm, bzr, xsrs, zysj}, request);
		
		return bFlag;
	}
	
	/**
	 * �����༶�����ѯ��ͷ
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> wmbjCxJg() throws Exception {
		List<HashMap<String, String>> topList = new ArrayList<HashMap<String,String>>();
		String[] enList = new String[]{"xn||xq||bjdm||rychdm", "bgcolor", "rownum", "xn", "xymc", "zymc", "bjmc", "rychmc" , "xsrs", "bzr", "fdysh","xysh","xxsh"};
		String[] cnList = new String[]{"����", "bgcolor", "�к�", "ѧ��", "ѧԺ����", "רҵ����", "�༶����", "�����ƺ�����","ѧ������", "����Ա","����Ա���","ѧԺ���", "ѧУ���"};
		for (int i = 0; i < enList.length; i++) {
			HashMap<String, String> resTemp = new HashMap<String, String>();
			resTemp.put("en", enList[i]);
			resTemp.put("cn", cnList[i]);
			topList.add(resTemp);
		}//end for
		return topList;
	}
	
	/**
	 * �����༶�����ѯ���
	 * @param xjbjqryModel
	 * @return
	 * @throws Exception
	 */
	public List<String[]> wmbjsqCxJg(WmbjSqModel xjbjqryModel) throws Exception {
		List<String[]> resList = new ArrayList<String[]>();
		String sql = "select xn||xq||bjdm||rychdm,(case when(xxsh='δ���' and fdysh='δ���' and xysh='δ���') then '#DDDDDD' else '#FFFFFF' end) bgcolor,rownum,xn,xymc,zymc,bjmc,rychmc,xsrs,bzr,replace(fdysh,' ','') fdysh,replace(xysh,' ','') xysh,replace(xxsh,' ','') xxsh from view_pjpy_xjbjandwmsq where 1=1 ";
		StringBuffer whereSql = getWhereSql2(xjbjqryModel);
		String[] opList = new String[]{"xn||xq||bjdm||rychdm", "bgcolor", "rownum", "xn", "xymc", "zymc", "bjmc", "rychmc" , "xsrs", "bzr","fdysh","xysh", "xxsh"};
		resList = dao.rsToVator(sql + whereSql, values != null ? values.toArray(new String[0]) : new String[]{}, opList);
		return resList;
	}
	
	/**
	 * �����༶����ɾ��
	 * delxjbjxx ---- �Ƚ��༶��Ϣ����ɾ��
	 * @param keys
	 * @return
	 * @throws Exception
	 */
	public String delWmbjXx(String[] keys) throws Exception {
		StringBuffer pksql1 = new StringBuffer();
		String[] pksql = new String[] {};
		String sql = "";
		for (int i = 0; i < keys.length; i++) {
			String whichxh = DealString.toGBK(keys[i]);// �õ�����
			sql = "delete from pjpy_xjbjandwmsqb where xn||xq||bjdm||rychdm = '" + whichxh + "'";
			// ��������ϳ�sql���
			pksql1.append(sql);
			pksql1.append("!!#!!");
		}// end for
		// sql����ֳ�����
		pksql = pksql1.toString().split("!!#!!");
		int[] judge2 = dao.runBatch(pksql);
		String whichpk = "";
		// �����һ��ɾ��ʧ��
		for (int i = 0; i < judge2.length; i++) {
			if (judge2[i] > 0) {
				whichpk = whichpk + " ��" + String.valueOf(i) + "������ɾ��ʧ��;\n";
			}// end if
		}// end for
		return whichpk;
	}
	
	/**
	 * ͨ��������ȡ�����༶��Ϣ
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getWmbjByPk(String pkValue) throws Exception {
		HashMap<String, String> resMap = new HashMap<String, String>();
		String sql = "select a.xymc,a.zymc,a.bjmc,a.zysj,a.fdyyj,a.xyyj,a.xxyj,a.bjdm,b.nj from view_pjpy_xjbjandwmsq a left join view_njxyzybj b on a.bjdm=b.bjdm where a.xn||a.xq||a.bjdm||a.rychdm=?";
		resMap = dao.getMapNotOut(sql, new String[]{pkValue});
		return resMap;
	}
	
	/**
	 * ����Ա��ѯ�����༶��ͷ
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> fdyQryWmbjTitle() throws Exception {
		List<HashMap<String, String>> topList = new ArrayList<HashMap<String,String>>();
		String[] enList = new String[]{"xn||xq||bjdm||rychdm", "bgcolor", "rownum", "xn", "xymc", "zymc", "rychmc", "bzxm" , "xsrs", "bzr", "fdysh"};
		String[] cnList = new String[]{"����", "bgcolor", "�к�", "ѧ��", "ѧԺ����", "רҵ����", "�༶����", "�����ƺ�����", "ѧ������", "����Ա", "����Ա���"};
		for (int i = 0; i < enList.length; i++) {
			HashMap<String, String> resTemp = new HashMap<String, String>();
			resTemp.put("en", enList[i]);
			resTemp.put("cn", cnList[i]);
			topList.add(resTemp);
		}//end for
		return topList;
	}
	
	/**
	 * ����Ա��ѯ�����༶���
	 * @param wmbjModel
	 * @return
	 * @throws Exception
	 */
	public List<String[]> fdyQryWmbjResult(WmbjSqModel wmbjModel) throws Exception {
		List<String[]> resList = new ArrayList<String[]>();
		
		String sql = "select xn||xq||bjdm||rychdm,(case when(fdysh='δ���') then '#DDDDDD' else '#FFFFFF' end) bgcolor,rownum,xn,xymc,zymc,bjmc,rychmc,xsrs,bzr,fdysh from view_pjpy_xjbjandwmsq where 1=1 ";
		StringBuffer whereSql = getWhereSql2(wmbjModel);
		String[] opList = new String[]{"xn||xq||bjdm||rychdm", "bgcolor", "rownum", "xn", "xymc", "zymc", "bjmc", "rychmc" , "xsrs", "bzr", "fdysh"};
		resList = dao.rsToVator(sql + whereSql, values != null ? values.toArray(new String[0]) : new String[]{}, opList);
		return resList;
	}
	
	/**
	 * ѧԺ��ѯ�����༶��ͷ
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> xyQryWmbjTitle() throws Exception {
		List<HashMap<String, String>> topList = new ArrayList<HashMap<String,String>>();
		String[] enList = new String[]{"xn||xq||bjdm||rychdm", "bgcolor", "rownum", "xn", "xymc", "zymc", "rychmc", "bzxm" , "xsrs", "bzr", "xysh"};
		String[] cnList = new String[]{"����", "bgcolor", "�к�", "ѧ��", "ѧԺ����", "רҵ����", "�༶����", "�����ƺ�����", "ѧ������", "����Ա", "ѧԺ���"};
		for (int i = 0; i < enList.length; i++) {
			HashMap<String, String> resTemp = new HashMap<String, String>();
			resTemp.put("en", enList[i]);
			resTemp.put("cn", cnList[i]);
			topList.add(resTemp);
		}//end for
		return topList;
	}
	
	/**
	 * ѧԺ��ѯ�����༶���
	 * @param wmbjModel
	 * @return
	 * @throws Exception
	 */
	public List<String[]> xyQryWmbjResult(WmbjSqModel wmbjModel) throws Exception {
		List<String[]> resList = new ArrayList<String[]>();
		
		String sql = "select xn||xq||bjdm||rychdm,(case when(xysh='δ���') then '#DDDDDD' else '#FFFFFF' end) bgcolor,rownum,xn,xymc,zymc,bjmc,rychmc,xsrs,bzr,xysh from view_pjpy_xjbjandwmsq where 1=1 and fdysh='ͨ��' ";
		StringBuffer whereSql = getWhereSql2(wmbjModel);
		String[] opList = new String[]{"xn||xq||bjdm||rychdm", "bgcolor", "rownum", "xn", "xymc", "zymc", "bjmc", "rychmc" , "xsrs", "bzr", "xysh"};
		resList = dao.rsToVator(sql + whereSql, values != null ? values.toArray(new String[0]) : new String[]{}, opList);
		return resList;
	}
	
	/**
	 * ѧУ��ѯ�����༶��ͷ
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> xxQryWmbjTitle() throws Exception {
		List<HashMap<String, String>> topList = new ArrayList<HashMap<String,String>>();
		String[] enList = new String[]{"xn||xq||bjdm||rychdm", "bgcolor", "rownum", "xn", "xymc", "zymc", "rychmc", "bzxm" , "xsrs", "bzr", "xxsh"};
		String[] cnList = new String[]{"����", "bgcolor", "�к�", "ѧ��", "ѧԺ����", "רҵ����", "�༶����", "�����ƺ�����", "ѧ������", "����Ա", "ѧУ���"};
		for (int i = 0; i < enList.length; i++) {
			HashMap<String, String> resTemp = new HashMap<String, String>();
			resTemp.put("en", enList[i]);
			resTemp.put("cn", cnList[i]);
			topList.add(resTemp);
		}//end for
		return topList;
	}
	
	/**
	 * ѧУ��ѯ�����༶���
	 * @param wmbjModel
	 * @return
	 * @throws Exception
	 */
	public List<String[]> xxQryWmbjResult(WmbjSqModel wmbjModel) throws Exception {
		List<String[]> resList = new ArrayList<String[]>();
		String sql = "select xn||xq||bjdm||rychdm,(case when(xxsh='δ���') then '#DDDDDD' else '#FFFFFF' end) bgcolor,rownum,xn,xymc,zymc,bjmc,rychmc,xsrs,bzr,xxsh from view_pjpy_xjbjandwmsq where 1=1 and fdysh='ͨ��' and xysh='ͨ��' ";
		StringBuffer whereSql = getWhereSql2(wmbjModel);
		String[] opList = new String[]{"xn||xq||bjdm||rychdm", "bgcolor", "rownum", "xn", "xymc", "zymc", "bjmc", "rychmc" , "xsrs", "bzr", "xxsh"};
		resList = dao.rsToVator(sql + whereSql, values != null ? values.toArray(new String[0]) : new String[]{}, opList);
		return resList;
	}
	
	/**
	 * ����Ա��ȡ�����༶���������ʾ��Ϣ
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getWmbjShViewByFdy(String pkValue) throws Exception {
		HashMap<String, String> resMap = new HashMap<String, String>();
		String sql = "select a.xn,a.xq,a.xymc,a.bjmc,a.zymc,a.rychmc,a.xsrs,a.bzr,a.zysj,a.fdysh sh,a.fdyyj shyj,(select count(*) from view_wjcf b where a.bjdm=b.bjdm and a.xn=b.xn and b.xxsh='ͨ��' and (b.cflbmc='��У�쿴' or b.cflbmc='����ѧ��')) bjcfrs from view_pjpy_xjbjandwmsq a where a.xn||a.xq||a.bjdm||a.rychdm=?";
		resMap = dao.getMapNotOut(sql, new String[]{pkValue});
		return resMap;
	}
	
	/**
	 * ѧԺ��ȡ�����༶���������ʾ��Ϣ
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getWmbjShViewByXy(String pkValue) throws Exception {
		HashMap<String, String> resMap = new HashMap<String, String>();
		String sql = "select a.xn,a.xq,a.xymc,a.bjmc,a.zymc,a.rychmc,a.xsrs,a.bzr,a.zysj,a.xysh sh,a.xyyj shyj,(select count(*) from view_wjcf b where a.bjdm=b.bjdm and a.xn=b.xn and b.xxsh='ͨ��' and (b.cflbmc='��У�쿴' or b.cflbmc='����ѧ��')) bjcfrs from view_pjpy_xjbjandwmsq a where a.xn||a.xq||a.bjdm||a.rychdm=?";
		resMap = dao.getMapNotOut(sql, new String[]{pkValue});
		return resMap;
	}
	
	/**
	 * ѧУ��ȡ�����༶���������ʾ��Ϣ
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getWmbjShViewByXx(String pkValue) throws Exception {
		HashMap<String, String> resMap = new HashMap<String, String>();
		String sql = "select a.xn,a.xq,a.xymc,a.bjmc,a.zymc,a.rychmc,a.xsrs,a.bzr,a.zysj,a.xxsh sh,a.xxyj shyj,(select count(*) from view_wjcf b where a.bjdm=b.bjdm and a.xn=b.xn and b.xxsh='ͨ��' and (b.cflbmc='��У�쿴' or b.cflbmc='����ѧ��')) bjcfrs from view_pjpy_xjbjandwmsq a where a.xn||a.xq||a.bjdm||a.rychdm=?";
		resMap = dao.getMapNotOut(sql, new String[]{pkValue});
		return resMap;
	}
	
	/**
	 * ����Ա��˵��������༶
	 * @param wmbjModel
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean fdyShWmbjByone(WmbjShModel wmbjModel, HttpServletRequest request) throws Exception {
		boolean bFlag = false;
		String pkValue = wmbjModel.getPkValue();
		String sh = DealString.toGBK(wmbjModel.getShxm());
		String shyj = DealString.toGBK(wmbjModel.getShyj());
		bFlag = StandardOperation.update("pjpy_xjbjandwmsqb", new String[]{"fdysh","fdyyj"}, new String[]{sh,shyj}, "xn||xq||bjdm||rychdm", pkValue, request);
		return bFlag;
	}
	
	/**
	 * ѧԺ��˵��������༶
	 * @param wmbjModel
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean xyShWmbjByone(WmbjShModel wmbjModel, HttpServletRequest request) throws Exception {
		boolean bFlag = false;
		String pkValue = wmbjModel.getPkValue();
		String sh = DealString.toGBK(wmbjModel.getShxm());
		String shyj = DealString.toGBK(wmbjModel.getShyj());
		bFlag = StandardOperation.update("pjpy_xjbjandwmsqb", new String[]{"xysh","xyyj"}, new String[]{sh,shyj}, "xn||xq||bjdm||rychdm", pkValue, request);
		return bFlag;
	}
	
	/**
	 * ѧУ��˵��������༶
	 * @param wmbjModel
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean xxShWmbjByone(WmbjShModel wmbjModel, HttpServletRequest request) throws Exception {
		boolean bFlag = false;
		String pkValue = wmbjModel.getPkValue();
		String sh = DealString.toGBK(wmbjModel.getShxm());
		String shyj = DealString.toGBK(wmbjModel.getShyj());
		bFlag = StandardOperation.update("pjpy_xjbjandwmsqb", new String[]{"xxsh","xxyj"}, new String[]{sh,shyj}, "xn||xq||bjdm||rychdm", pkValue, request);
		return bFlag;
	}
	
	/**
	 * ����Ա��˼��������༶
	 * @param keys
	 * @param res
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean[] fdyWmbjjtSh(String[] keys, String res, HttpServletRequest request) throws Exception {
		boolean[] result = new boolean[keys.length];
		String rss = "tg".equalsIgnoreCase(res) ? "ͨ��" : ("btg".equalsIgnoreCase(res) ? "��ͨ��" : "δ���");
		for (int i = 0; i < keys.length; i++) {
			result[i] = StandardOperation.update("pjpy_xjbjandwmsqb", new String[]{"fdysh"}, new String[]{rss}, "xn||xq||bjdm||rychdm", keys[i], request);
		}
		return result;
	}
	
	/**
	 * ѧԺ��˼��������༶
	 * @param keys
	 * @param res
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean[] xyWmbjjtSh(String[] keys, String res, HttpServletRequest request) throws Exception {
		boolean[] result = new boolean[keys.length];
		String rss = "tg".equalsIgnoreCase(res) ? "ͨ��" : ("btg".equalsIgnoreCase(res) ? "��ͨ��" : "δ���");
		for (int i = 0; i < keys.length; i++) {
			result[i] = StandardOperation.update("pjpy_xjbjandwmsqb", new String[]{"xysh"}, new String[]{rss}, "xn||xq||bjdm||rychdm", keys[i], request);
		}
		return result;
	}
	
	/**
	 * ѧУ��˼��������༶
	 * @param keys
	 * @param res
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean[] xxWmbjjtSh(String[] keys, String res, HttpServletRequest request) throws Exception {
		boolean[] result = new boolean[keys.length];
		String rss = "tg".equalsIgnoreCase(res) ? "ͨ��" : ("btg".equalsIgnoreCase(res) ? "��ͨ��" : "δ���");
		for (int i = 0; i < keys.length; i++) {
			result[i] = StandardOperation.update("pjpy_xjbjandwmsqb", new String[]{"xxsh"}, new String[]{rss}, "xn||xq||bjdm||rychdm", keys[i], request);
		}
		return result;
	}
	
	/**
	 * ��ȡ�����ƺ��б�
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getRychList() throws Exception {
		List<HashMap<String, String>> rychList = new ArrayList<HashMap<String,String>>();
		String sql = "select rychdm,rychmc from rychdmb";
		rychList = dao.getList(sql, new String[]{}, new String[]{"rychdm", "rychmc"});
		return rychList;
	}
	
	/**
	 * ��ȡ��ѧ������ѧ�����
	 * @return
	 * @throws Exception
	 */
	public String[] getJxjsqXnNd() throws Exception {
		String[] jxjsqxnndList = new String[]{};
		String sql = "select jxjsqxn,jxjsqnd from xtszb where rownum=1";
		jxjsqxnndList = dao.getOneRs(sql, new String[]{}, new String[]{"jxjsqxn", "jxjsqnd"});
		return jxjsqxnndList;
	}
	
	/**
	 * ����Ա��ѯ�����ƺű�ͷ
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getRychQryTitleByFdy() throws Exception {
		List<HashMap<String, String>> topList = new ArrayList<HashMap<String,String>>();
		String[] enList = new String[]{"pk", "rownum", "xn", "nd", "xh", "xm", "bjmc", "rychmc", "fdysh"};//�ֶ��б�
		String[] cnList = new String[]{"����", "�к�", "ѧ��", "���", "ѧ��", "����", "�༶����", "�����ƺ�", "����Ա���" };//�����б�
		for (int i = 0; i < enList.length; i++) {
			HashMap<String, String> resTemp = new HashMap<String, String>();
			resTemp.put("en", enList[i]);
			resTemp.put("cn", cnList[i]);
			topList.add(resTemp);
		}//end for
		return topList;
	}
	
	/**
	 * ѧԺ��ѯ�����ƺű�ͷ
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getRychQryTitleByXy() throws Exception {
		List<HashMap<String, String>> topList = new ArrayList<HashMap<String,String>>();
		String[] enList = new String[]{"pk", "rownum", "xn", "nd", "xh", "xm", "bjmc", "rychmc", "xysh"};//�ֶ��б�
		String[] cnList = new String[]{"����", "�к�", "ѧ��", "���", "ѧ��", "����", "�༶����", "�����ƺ�", "ѧԺ���" };//�����б�
		for (int i = 0; i < enList.length; i++) {
			HashMap<String, String> resTemp = new HashMap<String, String>();
			resTemp.put("en", enList[i]);
			resTemp.put("cn", cnList[i]);
			topList.add(resTemp);
		}//end for
		return topList;
	}
	
	/**
	 * ѧУ��ѯ�����ƺű�ͷ
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getRychQryTitleByXx() throws Exception {
		List<HashMap<String, String>> topList = new ArrayList<HashMap<String,String>>();
		String[] enList = new String[]{"pk", "rownum", "xn", "nd", "xh", "xm", "bjmc", "rychmc", "xxsh"};//�ֶ��б�
		String[] cnList = new String[]{"����", "�к�", "ѧ��", "���", "ѧ��", "����", "�༶����", "�����ƺ�", "ѧУ���" };//�����б�
		for (int i = 0; i < enList.length; i++) {
			HashMap<String, String> resTemp = new HashMap<String, String>();
			resTemp.put("en", enList[i]);
			resTemp.put("cn", cnList[i]);
			topList.add(resTemp);
		}//end for
		return topList;
	}
	
	/**
	 * ����Ա��ѯ�����ƺŽ��
	 * @param rychModel
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getRychQryResultByFdy(RychQryModel rychModel) throws Exception {
		List<String[]> resList = new ArrayList<String[]>();
		String sql = "select xn||nd||xh||rychdm pk,rownum,xn,nd,xh,xm,bjmc,rychmc,fdysh from view_xsrychb where 1=1 ";
		String[] opList = new String[]{"pk", "rownum", "xn", "nd", "xh", "xm", "bjmc", "rychmc", "fdysh"};//�ֶ��б�
		WhereSqlCommon common = new WhereSqlCommon();
		StringBuffer whereSql = common.getWhereSqlByRych(rychModel);//��ѯ����
		values = common.values;//��ѯ����ֵ
		resList = dao.rsToVator(sql + whereSql, values != null ? values.toArray(new String[0]) : new String[]{}, opList);
		return resList;
	}
	
	/**
	 * ѧԺ��ѯ�����ƺŽ���������
	 * @param rychModel
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getRychQryResultByXy(RychQryModel rychModel) throws Exception {
		List<String[]> resList = new ArrayList<String[]>();
		String sql = "select xn||nd||xh||rychdm pk,rownum,xn,nd,xh,xm,bjmc,rychmc,xysh from view_xsrychb where 1=1 ";
		String[] opList = new String[]{"pk", "rownum", "xn", "nd", "xh", "xm", "bjmc", "rychmc", "xysh"};//�ֶ��б�
		WhereSqlCommon common = new WhereSqlCommon();
		StringBuffer whereSql = common.getWhereSqlByRych(rychModel);//��ѯ����
		values = common.values;//��ѯ����ֵ
		resList = dao.rsToVator(sql + whereSql, values != null ? values.toArray(new String[0]) : new String[]{}, opList);
		return resList;
	}
	
	/**
	 * ѧԺ��ѯ�����ƺŽ���������
	 * @param rychModel
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getRychQryResultByXy3(RychQryModel rychModel) throws Exception {
		List<String[]> resList = new ArrayList<String[]>();
		String sql = "select xn||nd||xh||rychdm pk,rownum,xn,nd,xh,xm,bjmc,rychmc,xysh from view_xsrychb where 1=1 and fdysh='ͨ��'";
		String[] opList = new String[]{"pk", "rownum", "xn", "nd", "xh", "xm", "bjmc", "rychmc", "xysh"};//�ֶ��б�
		WhereSqlCommon common = new WhereSqlCommon();
		StringBuffer whereSql = common.getWhereSqlByRych(rychModel);//��ѯ����
		values = common.values;//��ѯ����ֵ
		resList = dao.rsToVator(sql + whereSql, values != null ? values.toArray(new String[0]) : new String[]{}, opList);
		return resList;
	}
	
	/**
	 * ѧУ��ѯ�����ƺŽ���������
	 * @param rychModel
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getRychQryResultByXx(RychQryModel rychModel) throws Exception {
		List<String[]> resList = new ArrayList<String[]>();
		String sql = "select xn||nd||xh||rychdm pk,rownum,xn,nd,xh,xm,bjmc,rychmc,xxsh from view_xsrychb where 1=1 and xysh='ͨ��' ";
		String[] opList = new String[]{"pk", "rownum", "xn", "nd", "xh", "xm", "bjmc", "rychmc", "xxsh"};//�ֶ��б�
		WhereSqlCommon common = new WhereSqlCommon();
		StringBuffer whereSql = common.getWhereSqlByRych(rychModel);//��ѯ����
		values = common.values;//��ѯ����ֵ
		resList = dao.rsToVator(sql + whereSql, values != null ? values.toArray(new String[0]) : new String[]{}, opList);
		return resList;
	}
	
	/**
	 * ѧУ��ѯ�����ƺŽ���������
	 * @param rychModel
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getRychQryResultByXx3(RychQryModel rychModel) throws Exception {
		List<String[]> resList = new ArrayList<String[]>();
		String sql = "select xn||nd||xh||rychdm pk,rownum,xn,nd,xh,xm,bjmc,rychmc,xxsh from view_xsrychb where 1=1 and fdysh='ͨ��' and xysh='ͨ��' ";
		String[] opList = new String[]{"pk", "rownum", "xn", "nd", "xh", "xm", "bjmc", "rychmc", "xxsh"};//�ֶ��б�
		WhereSqlCommon common = new WhereSqlCommon();
		StringBuffer whereSql = common.getWhereSqlByRych(rychModel);//��ѯ����
		values = common.values;//��ѯ����ֵ
		resList = dao.rsToVator(sql + whereSql, values != null ? values.toArray(new String[0]) : new String[]{}, opList);
		return resList;
	}
	
	/**
	 * ��ȡ�����ƺ�����
	 * @param rychdm
	 * @return
	 * @throws Exception
	 */
	public String getRychmc(String rychdm) throws Exception {
		String sql = "select rychmc from rychdmb where rychdm=? and rownum=1";
		String[] resList = dao.getOneRs(sql, new String[]{rychdm}, new String[]{"rychmc"});
		if (resList != null && resList.length == 1) {
			return resList[0];
		}
		return "";
	}
	
	/**
	 * ����Ա��˸��������ƺŽ��
	 * @param keys
	 * @param shjg
	 * @return
	 * @throws Exception
	 */
	public boolean[] fdyshResult(String[] keys, String shjg, HttpServletRequest request) throws Exception {
		boolean[] result = new boolean[keys.length];
		String res = !StringUtils.isNull(shjg) && StringUtils.isEqual(shjg, "tg") ? "ͨ��" : (!StringUtils.isNull(shjg) && StringUtils.isEqual(shjg, "btg") ? "��ͨ��" : "δ���");
		boolean[] exists = dao.checkExists("xsrychb", "xn||nd||xh||rychdm", keys);//��������Ƿ����
		for (int i = 0; i < exists.length; i++) {
			if (exists[i]) {//������ھ͸������״̬
				result[i] = StandardOperation.update("xsrychb", new String[]{"fdysh"}, new String[]{res}, "xn||nd||xh||rychdm", keys[i], request);
			}
		}
		return result;
	}
	
	/**
	 * ѧԺ��˸��������ƺŽ��
	 * @param keys
	 * @param shjg
	 * @return
	 * @throws Exception
	 */
	public boolean[] xyshResult(String[] keys, String shjg, HttpServletRequest request) throws Exception {
		boolean[] result = new boolean[keys.length];
		String res = !StringUtils.isNull(shjg) && StringUtils.isEqual(shjg, "tg") ? "ͨ��" : (!StringUtils.isNull(shjg) && StringUtils.isEqual(shjg, "btg") ? "��ͨ��" : "δ���");
		boolean[] exists = dao.checkExists("xsrychb", "xn||nd||xh||rychdm", keys);//��������Ƿ����
		for (int i = 0; i < exists.length; i++) {
			if (exists[i]) {//������ھ͸������״̬
				result[i] = StandardOperation.update("xsrychb", new String[]{"xysh"}, new String[]{res}, "xn||nd||xh||rychdm", keys[i], request);
			}
		}
		return result;
	}
	
	/**
	 * ѧУ��˸��������ƺŽ��
	 * @param keys
	 * @param shjg
	 * @return
	 * @throws Exception
	 */
	public boolean[] xxshResult(String[] keys, String shjg, HttpServletRequest request) throws Exception {
		boolean[] result = new boolean[keys.length];
		String res = !StringUtils.isNull(shjg) && StringUtils.isEqual(shjg, "tg") ? "ͨ��" : (!StringUtils.isNull(shjg) && StringUtils.isEqual(shjg, "btg") ? "��ͨ��" : "δ���");
		boolean[] exists = dao.checkExists("xsrychb", "xn||nd||xh||rychdm", keys);//��������Ƿ����
		for (int i = 0; i < exists.length; i++) {
			if (exists[i]) {//������ھ͸������״̬
				result[i] = StandardOperation.update("xsrychb", new String[]{"xxsh"}, new String[]{res}, "xn||nd||xh||rychdm", keys[i], request);
			}
		}
		return result;
	}
	
	/**
	 * ��ȡ�����ƺ������Ϣ
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getRychInfo(String pkValue) throws Exception {
		HashMap<String, String> resMap = new HashMap<String, String>();
		String sql = "select a.xh,a.xn,a.xm,a.xb,a.xymc,a.zymc,a.bjmc,a.nj,a.xn,a.nd,a.rychmc,a.fdyyj,a.xyyj,a.xxyj,(select round(months_between(to_date(to_char(sysdate,'yyyymmdd'),'yyyy-mm-dd'),to_date(b.csrq,'yyyy-mm-dd'))/12) from view_xsxxb b where a.xh=b.xh) nl,(select c.jg from view_xsxxb c where a.xh=c.xh) jg,(select e.zzmmmc from view_xsxxb e where a.xh=e.xh) zzmm,d.jlqk,d.drzw,d.zysj,d.hjqk from view_xsrychb a left join xsrychxxb d on a.xh=d.xh where a.xn||a.nd||a.xh||a.rychdm=?";
		resMap = dao.getMapNotOut(sql, new String[] { pkValue });
		return resMap;
	}
	
	/**
	 * ����Ա��˲�ѯ���
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getRychInfoByFdy(String pkValue) throws Exception {
		HashMap<String, String> resMap = new HashMap<String, String>();
		String sql = "select xh,xm,xb,nj,xymc,bjmc,zymc,xn,nd,dcj,zcj,tcj,rychmc,fdysh yesno,fdyyj shyj from view_xsrychb where xn||nd||xh||rychdm=?";
		resMap = dao.getMapNotOut(sql, new String[] { pkValue });
		return resMap;
	}
	
	/**
	 * ѧԺ��˲�ѯ���
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getRychInfoByXy(String pkValue) throws Exception {
		HashMap<String, String> resMap = new HashMap<String, String>();
		String sql = "select xh,xm,xb,nj,xymc,bjmc,zymc,xn,nd,dcj,zcj,tcj,rychmc,xysh yesno,xyyj shyj from view_xsrychb where xn||nd||xh||rychdm=?";
		resMap = dao.getMapNotOut(sql, new String[] { pkValue });
		return resMap;
	}
	
	/**
	 * ѧУ��˲�ѯ���
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getRychInfoByXx(String pkValue) throws Exception {
		HashMap<String, String> resMap = new HashMap<String, String>();
		String sql = "select xh,xm,xb,nj,xymc,bjmc,zymc,xn,nd,dcj,zcj,tcj,rychmc,xxsh yesno,xxyj shyj from view_xsrychb where xn||nd||xh||rychdm=?";
		resMap = dao.getMapNotOut(sql, new String[] { pkValue });
		return resMap;
	}
	
	/**
	 * ����Ա��˵��������ƺ�
	 * @param grrychModel
	 * @return
	 * @throws Exception
	 */
	public boolean rychShByFdy(GrrychModiModel grrychModel, HttpServletRequest request) throws Exception {
		boolean bFlag = false;
		String pkValue = grrychModel.getPkValue();
		String sh = grrychModel.getSh();
		String shyj = grrychModel.getShyj();
		bFlag = StandardOperation.update("xsrychb", new String[]{"fdysh", "fdyyj"}, new String[]{sh, shyj}, "xn||nd||xh||rychdm", pkValue, request);
		return bFlag;
	}
	
	/**
	 * ѧԺ��˵��������ƺ�
	 * @param grrychModel
	 * @return
	 * @throws Exception
	 */
	public boolean rychShByXy(GrrychModiModel grrychModel, HttpServletRequest request) throws Exception {
		boolean bFlag = false;
		String pkValue = grrychModel.getPkValue();
		String sh = grrychModel.getSh();
		String shyj = grrychModel.getShyj();
		bFlag = StandardOperation.update("xsrychb", new String[]{"xysh", "xyyj"}, new String[]{sh, shyj}, "xn||nd||xh||rychdm", pkValue, request);
		return bFlag;
	}
	
	/**
	 * ѧУ��˵��������ƺ�
	 * @param grrychModel
	 * @return
	 * @throws Exception
	 */
	public boolean rychShByXx(GrrychModiModel grrychModel, HttpServletRequest request) throws Exception {
		boolean bFlag = false;
		String pkValue = grrychModel.getPkValue();
		String sh = grrychModel.getSh();
		String shyj = grrychModel.getShyj();
		bFlag = StandardOperation.update("xsrychb", new String[]{"xxsh", "xxyj"}, new String[]{sh, shyj}, "xn||nd||xh||rychdm", pkValue, request);
		return bFlag;
	}
	
	/**
	 * ��ѯ��ͷ
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> qryRychTitle() throws Exception {
		List<HashMap<String, String>> topList = new ArrayList<HashMap<String,String>>();
		String[] enList = new String[]{"pk", "nd", "xn", "xh", "xm", "bjmc", "rychmc", "fdysh", "xysh", "xxsh"};//�ֶ��б�
		String[] cnList = new String[]{"����", "���", "ѧ��", "ѧ��", "����", "�༶����", "�����ƺ�", "����Ա���" , "ѧԺ���", "ѧУ���"};//�����б�
		for (int i = 0; i < enList.length; i++) {
			HashMap<String, String> resTemp = new HashMap<String, String>();
			resTemp.put("en", enList[i]);
			resTemp.put("cn", cnList[i]);
			topList.add(resTemp);
		}//end for
		return topList;
	}
	
	/**
	 * ��ѯ���
	 * @return
	 * @throws Exception
	 */
	public List<String[]> qryRychResult(RychSjQryModel rychModel) throws Exception {
		List<String[]> resList = new ArrayList<String[]>();
		WhereSqlCommon common = new WhereSqlCommon();
		StringBuffer whereSql = common.getWhereSql1(rychModel);//��ѯ����
		values = common.values;//��ѯ����ֵ
		String sql = "select xn||nd||xh||rychdm pk,nd,xn,xh,xm,bjmc,rychmc,fdysh,xysh,xxsh from view_xsrychb where 1=1 and (rychmc like '������ѧ��' or rychmc like '����ѧ�����' or rychmc like '����ѧ��' or rychmc like '����ѧ���ɲ�' or rychmc like '�����ҵ��') ";
		String[] opList = new String[]{"pk","nd","xn","xh","xm","bjmc","rychmc","fdysh","xysh","xxsh"};
		resList = dao.rsToVator(sql + whereSql, values != null ? values.toArray(new String[0]) : new String[]{}, opList);
		return resList;
	}
	
	/**
	 * ��ȡ�����ƺ���Ϣ
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> rychInfoByPk(String pkValue) throws Exception {
		HashMap<String, String> resMap = new HashMap<String, String>();
		String sql = "select a.xh,a.xm,a.xb,a.nj,a.xymc,a.zymc,a.bjmc,a.nd,a.xn,a.rychdm,(select b.drzw from xsrychxxb b where a.xh=b.xh) drzw from view_xsrychb a where a.xn||a.nd||a.xh||a.rychdm=?";
		resMap = dao.getMapNotOut(sql, new String[]{pkValue});
		return resMap;
	}
	
	/**
	 * �����ƺ������޸�
	 * @param pkValue
	 * @param rychdm
	 * @param drzw
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean rychInfoModi(String pkValue, String rychdm, String drzw, String xh, HttpServletRequest request) throws Exception {
		boolean bFlag = false;
		bFlag = StandardOperation.update("xsrychb", new String[]{"rychdm"}, new String[]{rychdm}, "xn||nd||xh||rychdm", pkValue, request);
		if (bFlag) {
			StandardOperation.update("xsrychxxb", new String[]{"drzw"}, new String[]{drzw}, "xh", xh, request);
		}
		return bFlag;
	}
	
	/**
	 * �����ƺ���Ϣ����ɾ��
	 * @param keys
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String rychInfoDel(String[] keys, HttpServletRequest request) throws Exception {
		int del = 0;//ɾ����¼��
		for (int i = 0; i < keys.length; i++) {
			String whichpk = keys[i];// �õ�����
			boolean bFlag = StandardOperation.delete("xsrychb", "xn||nd||xh||rychdm", whichpk, request);
			if (bFlag){//ɾ���ɹ�
				del++;
			}
		}
		return String.format("%1$s ����¼�ɹ�ɾ��!", del);
	}
	
	/**
	 * ��ȡ��ѧ���б�
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getJxjList() throws Exception {
		DAO dao = DAO.getInstance();//�������ݲ���DAO
		List<HashMap<String, String>> jxjList = new ArrayList<HashMap<String,String>>();
		String sql = "select jxjdm,jxjmc from jxjdmb";
		jxjList = dao.getList(sql, new String[]{}, new String[]{"jxjdm", "jxjmc"});
		return jxjList;
	}
	
	/**
	 * ��ȡ��ѧ���б�
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getJxjList1(List<HashMap<String, String>> jxjList) throws Exception {
		List<HashMap<String, String>> jxjList1 = new ArrayList<HashMap<String,String>>();
		String sql = "select jxjdm,jxjmc from zyjxjdmb";
		jxjList1 = dao.getList(sql, new String[]{}, new String[]{"jxjdm", "jxjmc"});
		for (int i=0;i<jxjList1.size();i++) {
			jxjList.add(jxjList1.get(i));
		}
		return jxjList;
	}
	
	/**
	 * ��ȡ�ֶ��б�
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getZdList() throws Exception {
		List<HashMap<String, String>> zdList = new ArrayList<HashMap<String,String>>();
		/*String[] enList = new String[]{"zhszcpzf", "zhszcppm" ,"bxkdkcj" ,"bxkpjcj", "dcj", "zcj", "tcj"};
		String[] cnList = new String[]{"�ۺ����ʲ����ܷ�", "�ۺ����ʲ�������", "���޿ε��Ƴɼ�", "���޿�ƽ���ɼ�", "�³ɼ�", "�ǳɼ�" ,"��ɼ�"};*/
		String[] enList = new String[]{ "rownum" ,"cj" ,"pjf"};
		String[] cnList = new String[]{ "�ۺ����ʲ�������(��)", "�γ̵��Ƴɼ�", "�γ�ƽ���ɼ�"};
		for (int i=0; i<enList.length; i++) {
			HashMap<String, String> tmp = new HashMap<String, String>();
			tmp.put("en", enList[i]);
			tmp.put("cn", cnList[i]);
			zdList.add(tmp);
		}
		return zdList;
	}
	
	/**
	 * ��ѧ��������ѯ ��ͷ
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getJxjsztjTitle() throws Exception {
		List<HashMap<String, String>> topList = new ArrayList<HashMap<String,String>>();
		String[] enList = new String[]{"pk", "jxjdm", "rownum", "xn","tjzdm", "tj", };
		String[] cnList = new String[]{"pk", "jxjdm", "�к�","ѧ��", "�����ֶ���", "����"};
		for (int i=0; i<enList.length; i++) {
			HashMap<String, String> tmp = new HashMap<String, String>();
			tmp.put("en", enList[i]);
			tmp.put("cn", cnList[i]);
			topList.add(tmp);
		}
		return topList;
	}
	
	/**
	 * ��ѧ���Ӧ����
	 * @param xn
	 * @param jxjdm
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getJxjsztj(String xn, String jxjdm) throws Exception {
		List<String[]> jxjsztjList = new ArrayList<String[]>();
		String sql = "select xn||jxjdm||tjzdm pk,jxjdm,rownum,xn,(case when tjzdm='zhszcpzf' then '�ۺ����ʲ����ܷ�' when tjzdm='rownum' then '�ۺ����ʲ�������(��)' when tjzdm='cj' then '�γ̵��Ƴɼ�' when tjzdm='pjf' then '�γ�ƽ���ɼ�' when tjzdm='dcj' then '�³ɼ�' when tjzdm='zcj' then '�ǳɼ�' when tjzdm='tcj' then '��ɼ�' end) tjzdm,tj from pjpy_jxjtjszb where 1=1 ";
		StringBuffer whereSql = new StringBuffer();
		if (!StringUtils.isNull(xn)) {
			whereSql.append(" and xn='");
			whereSql.append(xn);
			whereSql.append("'");
		}
		if (!StringUtils.isNull(jxjdm)) {
			whereSql.append(" and jxjdm='");
			whereSql.append(jxjdm);
			whereSql.append("'");
		}
		jxjsztjList = dao.rsToVator(sql + whereSql, new String[]{}, new String[]{"pk", "jxjdm" , "rownum","xn", "tjzdm", "tj"});
		return jxjsztjList;
	}
	
	/**
	 * ��ѧ����������ɾ��
	 * @param pkValue
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean jxjsztjDel(String pkValue, HttpServletRequest request) throws Exception {
		return StandardOperation.delete("pjpy_jxjtjszb", "xn||jxjdm||tjzdm", pkValue, request);
	}
	
	/**
	 * ��ѧ���������ñ���
	 * @param tjszModel
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean jxjsztjSave(JxjtjszSaveModel tjszModel, HttpServletRequest request) throws Exception {
		boolean bFlag = false;
		boolean bDel = StandardOperation.delete("pjpy_jxjtjszb",
				"xn||jxjdm||tjzdm", tjszModel.getXn() + tjszModel.getJxjdm()
						+ tjszModel.getZdm(), request);
		if (bDel) {
			bFlag = StandardOperation.insert("pjpy_jxjtjszb", new String[] {
					"xn", "jxjdm", "tjzdm", "tj"}, new String[] {
					tjszModel.getXn(), tjszModel.getJxjdm(),
					tjszModel.getZdm(),
					tjszModel.getYsf() + "'" +tjszModel.getVal() + "'",
					}, request);
			StandardOperation.delete("pjpy_jxjtjwjszb", "xn||jxjdm", tjszModel.getXn() + tjszModel.getJxjdm(), request);
			StandardOperation.insert("pjpy_jxjtjwjszb", new String[] { "xn",
					"jxjdm", "sfwj" }, new String[] { tjszModel.getXn(),
					tjszModel.getJxjdm(), tjszModel.getSfwj() }, request);
		}
		return bFlag;
	}
	
	/**
	 * �������õ�����ϸ��Ϣ
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getTjInfo(String pkValue, String jxjdm) throws Exception {
		HashMap<String, String> tjMap = new HashMap<String, String>();
		String sql = "select a.*,b.jxjmc from pjpy_jxjtjszb a left join jxjdmb b on a.jxjdm=b.jxjdm where a.xn||a.jxjdm||a.tjzdm=?";
		tjMap = dao.getMapNotOut(sql, new String[]{pkValue});
		if (StringUtils.isNull(tjMap.get("jxjmc"))) {
			sql = "select jxjmc from zyjxjdmb where jxjdm = ?";
			String jxjmc = dao.getOneRs(sql, new String[]{jxjdm}, "jxjmc");
			tjMap.put("jxjmc", jxjmc);
		}
		return tjMap;
	}
	
	/**
	 * �������õ����޸�
	 * @param pkValue
	 * @param tjszModel
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean tjEditResult(String pkValue, JxjtjszSaveModel tjszModel,
			HttpServletRequest request) throws Exception {
		return StandardOperation.update("pjpy_jxjtjszb", new String[] {
				"tj"}, new String[] {tjszModel.getYsf() + "'" + tjszModel.getVal() + "'"},
				"xn||jxjdm||tjzdm", pkValue, request);
	} 
	
	/**
	 * ��ȡ��ѧ���Ƿ�Υ������
	 * @param xn
	 * @param jxjdm
	 * @return
	 * @throws Exception
	 */
	public String getJxjTjsz(String xn ,String jxjdm) throws Exception {
		return dao.getOneRs(
				"select sfwj from pjpy_jxjtjwjszb where xn=? and jxjdm=?",
				new String[] { xn, jxjdm }, "sfwj");
	}
	
	/**
	 * ��ȡ��ѧ�����ѧ��ѧ��
	 * @return
	 * @throws Exception
	 */
	public String[] getJxjSqxnxq() throws Exception {
		String[] jxjsqxnxq = new String[2];
		String sql = "select jxjsqxn,jxjsqxq from xtszb where rownum=1";
		jxjsqxnxq = dao.getOneRs(sql, new String[]{}, new String[]{"jxjsqxn", "jxjsqxq"});
		return jxjsqxnxq;
	}
	
	/**
	 * ��ѧ���Զ����
	 * @param zyjxjModel
	 * @param request
	 * @throws Exception
	 */
	public void zyjxjAutoSh(ZyjxjAutoshModel zyjxjModel, HttpServletRequest request) throws Exception {
		String xn = zyjxjModel.getXn();
		String xq = zyjxjModel.getXq();
		String jxjdm = zyjxjModel.getJxjdm();
		String sql = "delete from cjlsb where xn = ?";
		StringBuffer strsql = null;
		boolean bDel = dao.runUpdate(sql, new String[]{xn});
		if (bDel) {//����ɼ��ظ���ɾ����ѧ��ѧ���ɼ�
			sql = "insert into cjlsb (xn,xq,xh,xkkh,kcdm,kcmc,kcxz,xf,cj,cjlx)" +
			" select a.xn,a.xq,a.xh,a.xkkh,a.kcdm,a.kcmc,a.kcxz,a.xf," +
			"(case when (select to_char(b.dycj) from cjdzb@dblink_jw b" +
			" where to_char(a.cj)=to_char(b.cj)) is not null then " +
			"(select to_char(b.dycj) from cjdzb@dblink_jw b " +
			" where to_char(a.cj)=to_char(b.cj)) else a.cj end) cj," +
			"'����' from cjb@dblink_jw a where a.bkcj is null and" +
			" a.cxcj is null and a.kcxz like '%���޿�%' and (a.cxbj is null or a.cxbj='0')" +
			" and xn=?";
			dao.runUpdate(sql, new String[]{xn});//��ȡѧ���������Ƴɼ�
			sql = "delete from xspjpylsb where xn = ?";
			bDel = dao.runUpdate(sql, new String[]{xn});
			String[] jxjtj = new String[3];
			if (bDel) {//��ȡѧ�����Ƴɼ���ƽ���֣�����
				List<String[]> jxjtjList = dao
						.rsToVator(
								"select tjzdm,tj from pjpy_jxjtjszb where xn=? and jxjdm=?",
								new String[] {xn, jxjdm}, new String[] {"tjzdm", "tj"});
				if (jxjtjList != null && jxjtjList.size() > 0) {
					String[] tmp = new String[]{};
					for (int i = 0; i < jxjtjList.size(); i++) {
						 tmp = jxjtjList.get(i);
						 String zdmandtj = "";
						 for (int j = 0; j < tmp.length; j++) {
							 zdmandtj += tmp[j];
						 }
						 jxjtj[i] = zdmandtj;
					}
				}
				jxjtj = getClearResult(jxjtj);//�����������ĵ�����
				String sfwj = dao
						.getOneRs(
								"select sfwj from pjpy_jxjtjwjszb where xn=? and jxjdm=?",
								new String[] { xn, jxjdm }, "sfwj");//�������Ƿ����Υ�ʹ���
				if (!StringUtils.isNull(sfwj) && StringUtils.isEqual(sfwj, "0")) {//������
					strsql = new StringBuffer("insert into xspjpylsb (xn,xq,xh,cjpjf,zhcpzf,zhpm) " +
					"select distinct '"+ xn +"','"+ xq +"',a.xh,a.pjf,''," +
					"(select l.zhszpm from xszyjxjb l where l.xh=a.xh " +
					"and l.xq='"+ xq +"' and l.xn='"+ xn +"') zhpm from " +
					"(select xh,round(avg(cj)) pjf from cjlsb where " +
					"xn='"+ xn +"'  group by xh ) a," +
					"(select xh,min(to_number(cj)) cj from cjlsb " +
					"where xn=? group by xh ) " +
					"b where a.xh=b.xh and ");
					if (jxjtj != null && jxjtj.length > 0) {
						for (int i = 0 ; i < jxjtj.length;i++) {
							strsql.append(jxjtj[i]);
							strsql.append(" and ");
						}
					}
					strsql.append(" a.xh not in (select xh from view_wjcf) ");
					
				} else {//����
					strsql = new StringBuffer("insert into xspjpylsb (xn,xq,xh,cjpjf,zhcpzf,zhpm) " +
							"select distinct '"+ xn +"','"+ xq +"',a.xh,a.pjf,''," +
							"(select l.zhszpm from xszyjxjb l where l.xh=a.xh " +
							"and l.xq='"+ xq +"' and l.xn='"+ xn +"') zhpm from " +
							"(select xh,round(avg(cj)) pjf from cjlsb where " +
							"xn='"+ xn +"' group by xh ) a," +
							"(select xh,min(to_number(cj)) cj from cjlsb " +
							"where xn=? group by xh ) " +
							"b where a.xh=b.xh and ");
							if (jxjtj != null && jxjtj.length > 0) {
								for (int i = 0 ; i < jxjtj.length;i++) {
									strsql.append(jxjtj[i]);
									strsql.append(" and ");
								}
							}
					strsql.append("1=1 ");
				}
				dao.runUpdate(strsql.toString(), new String[]{xn});//�Զ�����������Υ�ͣ�ƽ���֣����ƣ�
				strsql = new StringBuffer("update xszyjxjb set xysh='ͨ��'," +
						"xxsh='ͨ��',fdyyj='�Զ����'||to_char(SYSDATE,'yyyymmddhh24miss')," +
						"xyyj='�Զ����'||to_char(SYSDATE,'yyyymmddhh24miss')," +
						"xxyj='�Զ����'||to_char(SYSDATE,'yyyymmddhh24miss') " +
						"where exists (select xh from (select xh from (select a.xh from view_xsjbxx a," +
						"xspjpylsb b where a.xh=b.xh and bjdm in (");
				String[] bjList = getBjList(zyjxjModel);
				if (bjList != null && bjList.length > 0) {
					for (int i = 0; i < bjList.length; i++) {
						strsql.append("'");
						strsql.append(bjList[i]);
						strsql.append("',");
					}
					strsql = new StringBuffer(strsql.substring(0, strsql.length() - 1));
					strsql.append(") ");
				} else {
					strsql.append("'') ");
				}
				strsql.append(" order by to_number(case when zhpm is null then '200' else zhpm end) asc) where "+ rownumzd +") c where " +
						" c.xh=xszyjxjb.xh and xszyjxjb.xn='");
				strsql.append(xn);
				strsql.append("' and xszyjxjb.xq='");
				strsql.append(xq);
				strsql.append("' and xszyjxjb.jxjdm='");
				strsql.append(jxjdm);
				strsql.append("' and  fdysh='ͨ��')");
				dao.runUpdate(strsql.toString(), new String[]{});//ִ���Զ����
			}
		}
	}
	
	/**
	 * �����������ĵ�����
	 * @param jxjtj
	 * @return
	 * @throws Exception
	 */
	public String[] getClearResult(String[] jxjtj) throws Exception {
		String[] tjtmp = new String[jxjtj.length]; 
		if (jxjtj != null && jxjtj.length > 0) {
			for (int i = 0; i < jxjtj.length; i++) {
				String tmp = jxjtj[i];
				//�����������ĵ�����
				tmp = !StringUtils.isNull(tmp) ? tmp.replaceAll("'", "") : "";
				if (!StringUtils.isNull(tmp) && tmp.indexOf("ownum") > 0) {
					rownumzd = tmp;
					tmp = " 1=1 ";
				}
				tjtmp[i] = tmp;
			}
		}
		return tjtmp;
	}
	
	/**
	 * ��ȡ�༶�б�
	 * @param zyjxjModel
	 * @return
	 * @throws Exception
	 */
	public String[] getBjList(ZyjxjAutoshModel zyjxjModel) throws Exception {
		String[] bjList = null;
		String sql = "";
		String xydm = zyjxjModel.getXydm();
		String zydm = zyjxjModel.getZydm();
		String bjdm = zyjxjModel.getBjdm();
		if (!StringUtils.isNull(xydm)) {//ѧԺ�ǿ�ʱ
			if (!StringUtils.isNull(bjdm)) {
				bjList = new String[1];
				bjList[0] = bjdm;
			} else if (!StringUtils.isNull(zydm)) {//רҵ�ǿ�ʱ
				if (!StringUtils.isNull(bjdm)) {//�༶�ǿ�ʱ
					bjList = new String[1];
					bjList[0] = bjdm;
				} else {//רҵ����Ӧ�İ༶
					bjList = new String[]{};
					sql = "select distinct bjdm from view_njxyzybj where zydm = ?";
					String[] zydybjList= dao.getRs(sql, new String[]{zydm}, "bjdm");
					bjList = zydybjList;
				}
			} else {//רҵΪ�գ��༶Ϊ��
				bjList = new String[]{};
				sql = "select distinct zydm from view_njxyzybj where xydm = ?";
				String[] zyList =  dao.getRs(sql, new String[]{xydm}, "zydm");
				if (zyList != null && zyList.length > 0) {
					StringBuffer strsql = null;
					strsql = new StringBuffer("select distinct bjdm from view_njxyzybj where zydm in (");
					for (int i = 0; i < zyList.length; i++) {
						strsql.append("'");
						strsql.append(zyList[i]);
						strsql.append("',");
					}
					sql = strsql.substring(0, strsql.length() - 1);
					sql += ") ";
					bjList = dao.getRs(sql, new String[]{}, "bjdm");
				}
			}
		}
		if (!StringUtils.isNull(zydm)) {//רҵ�ǿ�ʱ
			if (!StringUtils.isNull(bjdm)) {//�༶�ǿ�ʱ
				bjList = new String[1];
				bjList[0] = bjdm;
			} else {//רҵ����Ӧ�İ༶
				bjList = new String[]{};
				sql = "select distinct bjdm from view_njxyzybj where zydm = ?";
				String[] zydybjList= dao.getRs(sql, new String[]{zydm}, "bjdm");
				bjList = zydybjList;
			}
		}
		if (!StringUtils.isNull(bjdm)) {//�༶�ǿ�ʱ
			bjList = new String[1];
			bjList[0] = bjdm;
		}
		return bjList;
	}
	
	public HashMap<String, String> getStuInfo(String xh) throws Exception {
		return dao
				.getMapNotOut(
						"select xh,xm,xb,nj,xydm,xymc,zydm,zymc,bjdm,bjmc from view_xsjbxx where xh=?",
						new String[] { xh });
	}
}
