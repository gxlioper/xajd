
package xgxt.pjpy.hzy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.utils.RowidToPk;
import xgxt.utils.String.StringUtils;

/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: ����ְԺ��������DAO</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: ����</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-08-19</p>
 */
public class PjpyHzzyDAO {
	
	DAO dao = DAO.getInstance();//���ݲ�������DAO
	
	ArrayList<String> values = new ArrayList<String>();//��ѯ������Ӧֵ 
	
	private float gyjbf = 80;//��Ԣ������
	
	/**
	 * ͨ�������У�Ӣ�Ļ�ȡ��ѯ��ͷ
	 * @param enList
	 * @param cnList
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getTableQryTitle(String[] enList, String[] cnList) throws Exception{
		List<HashMap<String, String>> topList = new ArrayList<HashMap<String, String>>();
		for (int i = 0; i < enList.length; i++) {
			HashMap<String, String> tmp = new HashMap<String, String>();
			tmp.put("en", enList[i]);
			tmp.put("cn", cnList[i]);
			topList.add(tmp);
		}
		return topList;
	}
	
	/**
	 * ��ѯ������ֵ
	 * @param cjbModel
	 * @return
	 * @throws Exception
	 */
	public StringBuffer getWhereSql(CjbModel cjbModel) throws Exception {
		StringBuffer whereSql = new StringBuffer();
		if (!StringUtils.isNull(cjbModel.getBjdm())) {
			whereSql.append(" and b.bjdm = ?");
			values.add(cjbModel.getBjdm());
		}
		if (!StringUtils.isNull(cjbModel.getNd())) {
			whereSql.append(" and a.nd = ?");
			values.add(cjbModel.getNd());
		}
		if (!StringUtils.isNull(cjbModel.getNj())) {
			whereSql.append(" and b.nj = ?");
			values.add(cjbModel.getNj());
		}
		if (!StringUtils.isNull(cjbModel.getXh())) {
			whereSql.append(" and a.xh = ?");
			values.add(cjbModel.getXh());
		}	
		if (!StringUtils.isNull(cjbModel.getXn())) {
			whereSql.append(" and a.xn = ?");
			values.add(cjbModel.getXn());
		}
		if (!StringUtils.isNull(cjbModel.getXq())) {
			whereSql.append(" and a.xq = ?");
			values.add(cjbModel.getXq());
		}
		if (!StringUtils.isNull(cjbModel.getXydm())) {
			whereSql.append(" and b.xydm = ?");
			values.add(cjbModel.getXydm());
		}
		if (!StringUtils.isNull(cjbModel.getZydm())) {
			whereSql.append(" and b.zydm = ?");
			values.add(cjbModel.getZydm());
		}
		return whereSql;
	}
	
	public StringBuffer getWhereSql(ZhszcpModel zhszcpModel) throws Exception {
		StringBuffer whereSql = new StringBuffer();
		if (!StringUtils.isNull(zhszcpModel.getBjdm())) {
			whereSql.append(" and bjdm = ?");
			values.add(zhszcpModel.getBjdm());
		}
		if (!StringUtils.isNull(zhszcpModel.getNd())) {
			whereSql.append(" and nd = ?");
			values.add(zhszcpModel.getNd());
		}
		if (!StringUtils.isNull(zhszcpModel.getNj())) {
			whereSql.append(" and nj = ?");
			values.add(zhszcpModel.getNj());
		}
		if (!StringUtils.isNull(zhszcpModel.getXh())) {
			whereSql.append(" and xh = ?");
			values.add(zhszcpModel.getXh());
		}
		if (!StringUtils.isNull(zhszcpModel.getXn())) {
			whereSql.append(" and xn = ?");
			values.add(zhszcpModel.getXn());
		}
		if (!StringUtils.isNull(zhszcpModel.getXq())) {
			whereSql.append(" and xq = ?");
			values.add(zhszcpModel.getXq());
		}
		if (!StringUtils.isNull(zhszcpModel.getZydm())) {
			whereSql.append(" and zydm = ?");
			values.add(zhszcpModel.getZydm());
		}
		if (!StringUtils.isNull(zhszcpModel.getXydm())) {
			whereSql.append(" and xydm = ?");
			values.add(zhszcpModel.getXydm());
		}
		return whereSql;
	}
	
	/**
	 * ��ȡ�ɼ����ѯ���
	 * @param cjbModel
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getCjbQryResult(CjbModel cjbModel) throws Exception {
		List<String[]> resList = new ArrayList<String[]>();
		StringBuffer whereSql = getWhereSql(cjbModel);
		String sql = "select rownum,a.xh,b.xm,a.xn,a.xq,b.bjmc,a.kcmc,a.cj,b.xydm,b.zydm,b.bjdm,b.nj from cjb a left join view_xsjbxx b on a.xh=b.xh where 1=1 ";
		String[] opList = new String[]{"rownum", "xh", "xm", "xn", "xq", "bjmc", "kcmc", "cj"};
		resList = dao.rsToVator(sql + whereSql.toString(),
				values != null ? values.toArray(new String[0])
						: new String[] {}, opList);
		return resList;
	}
	
	/**
	 * �ۺ����ʲ�����ѯ���
	 * @param zhszcpModel
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getZhszcpQryResult(ZhszcpModel zhszcpModel) throws Exception {
		List<String[]> resList = new ArrayList<String[]>();
		StringBuffer whereSql = getWhereSql(zhszcpModel);
		String sql = "select xh||xn||xq||nd pk,rownum,xh,xm,xn,nd,xq,bjmc,xydykpf,zcj,tcj,gzxxcx,zhszcpzf from view_zhszcp where 1=1 ";
		String[] opList = new String[]{"pk", "rownum", "xh", "xm", "xn", "nd","xq", "bjmc", "xydykpf","zcj","tcj" ,"gzxxcx","zhszcpzf"};
		resList = dao.rsToVator(sql + whereSql.toString(), values != null ? values.toArray(new String[0]) : new String[]{}, opList);
		return resList;
	}
	
	/**
	 * �����ڽ�ѧ������ѧ��ȵ��ۺ����ʷ�
	 * @param xh
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getStuXx(String xh) throws Exception {
		HashMap<String, String> resMap = new HashMap<String, String>();
		String[] jxjsqxnxqnd = dao.getOneRs(
				"select jxjsqxn,jxjsqxq,jxjsqnd from xtszb", new String[] {},
				new String[] { "jxjsqxn", "jxjsqxq", "jxjsqnd" });
		String[] opList = new String[]{"xh", "xm", "xb", "nj", "xymc", "zymc", "bjmc", "xn", "nd", "xq", "xydykpf", "gydykpf", "zcj", "tcj", "zhszcpzf", "zhszcpcjpm", "bz", "gzxxcx"}; 
		String sql = "select a.xh,a.xm,a.xb,a.nj,a.xymc,a.zymc,a.bjmc,b.xn,b.nd,b.xq,b.xydykpf,b.gydykpf,b.zcj,b.tcj,b.zhszcpzf,b.zhszcpcjpm,b.bz,b.gzxxcx from view_xsjbxx a left join (select xh,xydykpf,gydykpf,tcj,zcj,zhszcpzf,zhszcpcjpm,bz,xn,xq,nd,gzxxcx from view_zhszcp where xn=? and xq=? and nd=?) b on a.xh=b.xh where a.xh = ?";
		resMap = dao.getMap(sql, new String[] { jxjsqxnxqnd[0], jxjsqxnxqnd[1],
				jxjsqxnxqnd[2], xh }, opList);
		String sfzss = stuIsZss(xh);//�Ƿ���ס����
		if (!StringUtils.isNull(sfzss) && StringUtils.isEqual(sfzss, "��")) {
			if (StringUtils.isNull(resMap.get("gydykpf"))) {
				String gydykpf = countGydykpf(xh);
				String sBl = dao.getOneRs("select gydykpf from zhcpfblszb where sfzds = 0", new String[]{}, "gydykpf");
				float fDf = StringUtils.isNull(gydykpf) ? 0 : Float.parseFloat(gydykpf);
				float fBl = StringUtils.isNull(sBl) ? 0: Float.parseFloat(sBl);
				String sZf = (fDf*fBl/100) + "";
				if (!StringUtils.isNull(sZf) && StringUtils.isEqual(sZf, "0")) {
					sZf = "";
				}
				if (!StringUtils.isNull(sZf) && sZf.indexOf(".") > 0) {
					sZf = sZf.substring(0, sZf.indexOf(".") + 2);
				}
				resMap.put("gydykpf", gydykpf);//��Ԣ�����ܷ�
				resMap.put("zhszcpzf", sZf);//�ۺ����ʲ����ܷ�
			}
		}
		resMap.put("sfzss", sfzss);
		return resMap;
	}
	
	/**
	 * ��������ڽ�ѧ������ѧ��ȵĹ�Ԣ�����ܷ�
	 * @param xh
	 * @return
	 * @throws Exception
	 */
	public String countGydykpf(String xh) throws Exception {
		String sZf = "";//��Ԣ�ܷ�
		String sJf = "";//��Ԣ�ӷ�
		String sKf = "";//��Ԣ�۷�
		String[] jxjsqxnxq = dao.getOneRs(
				"select jxjsqxn,jxjsqxq from xtszb", new String[] {},
				new String[] { "jxjsqxn", "jxjsqxq" });
		if (jxjsqxnxq != null && jxjsqxnxq.length == 2) {
			sJf = getDyjf(xh, jxjsqxnxq);
			sKf = getDykf(xh, jxjsqxnxq);
		}
		float fJf = 0;
		float fKf = 0;
		if (!StringUtils.isNull(sJf)) {
			fJf = Float.parseFloat(sJf);
		}
		if (!StringUtils.isNull(sKf)) {
			fKf = Float.parseFloat(sKf);
		}
		sZf = (fJf - fKf + gyjbf) + "";
		return sZf;
	}
	
	//�����ӷ�
	public String getDyjf(String xh, String[] jxjsqxnxq) throws Exception {
		if (jxjsqxnxq != null && jxjsqxnxq.length == 2) {
			return dao.getOneRs(
					"select xh,xn,xq,trunc(sum(ryjf),1) jf from xsjlb where xn=? and xq=? and xh=? group by xh,xn,xq",
					new String[] {
							(jxjsqxnxq != null) && (jxjsqxnxq.length == 2) ? jxjsqxnxq[0]
									: "",
							(jxjsqxnxq != null) && (jxjsqxnxq.length == 2) ? jxjsqxnxq[1]
									: "", xh }, "jf");
		}
		return "";
	}
	
	//�����۷�
	public String getDykf(String xh, String[] jxjsqxnxq) throws Exception {
		if (jxjsqxnxq != null && jxjsqxnxq.length == 2) {
			return dao.getOneRs(
					"select xh,xn,xq,trunc(sum(rykf),1) kf from xscfb where xn=? and xq=? and xh=? group by xh,xn,xq",
					new String[] {
							(jxjsqxnxq != null) && (jxjsqxnxq.length == 2) ? jxjsqxnxq[0]
									: "",
							(jxjsqxnxq != null) && (jxjsqxnxq.length == 2) ? jxjsqxnxq[1]
									: "", xh }, "kf");
		}
		return "";
	}
	
	/**
	 * �жϸ����Ƿ���ס����
	 * @param xh
	 * @return true���߶�����false��ס����
	 * @throws Exception
	 */
	public String stuIsZss(String xh) throws Exception {
		String sFlag = "��";
		String sJg = dao.getOneRs("select count(*) num from xszsxxb where xh = ?", new String[]{xh}, "num");
		if (!StringUtils.isNull(sJg) && StringUtils.isEqual(sJg, "0")) {
			sFlag = "��";
		}
		return sFlag;
	}
	
	/**
	 * �����ۺ����ʲ����ܷ�
	 * @param sfzss
	 * @param df
	 * @return
	 * @throws Exception
	 */
	public String countZhszcpzfByXydyf(String sfzss, String df1,String df2, String df3, String df4, String df5, String type) throws Exception {
		String bl1 = "";
		String bl2 = "";
		String bl3 = "";
		String bl4 = "";
		String bl5 = "";
		//ѧԺ��
			if (!StringUtils.isNull(sfzss) && StringUtils.isEqual(sfzss, "��")) {
				bl1 = dao.getOneRs("select xydykpf from zhcpfblszb where sfzds = 0", new String[]{}, "xydykpf");
			} else {
				bl1 = dao.getOneRs("select xydykpf from zhcpfblszb where sfzds = 1", new String[]{}, "xydykpf");
			}
		
		//��Ԣ��
			if (!StringUtils.isNull(sfzss) && StringUtils.isEqual(sfzss, "��")) {
				bl2 = dao.getOneRs("select gydykpf from zhcpfblszb where sfzds = 0", new String[]{}, "gydykpf");
			} else {
				bl2 = dao.getOneRs("select gydykpf from zhcpfblszb where sfzds = 1", new String[]{}, "gydykpf");
			}
		
		//������
			if (!StringUtils.isNull(sfzss) && StringUtils.isEqual(sfzss, "��")) {
				bl3 = dao.getOneRs("select zycj from zhcpfblszb where sfzds = 0", new String[]{}, "zycj");
			} else {
				bl3 = dao.getOneRs("select zycj from zhcpfblszb where sfzds = 1", new String[]{}, "zycj");
			}
		
		//������
			if (!StringUtils.isNull(sfzss) && StringUtils.isEqual(sfzss, "��")) {
				bl4 = dao.getOneRs("select tycj from zhcpfblszb where sfzds = 0", new String[]{}, "tycj");
			} else {
				bl4 = dao.getOneRs("select tycj from zhcpfblszb where sfzds = 1", new String[]{}, "tycj");
			}
		
		//����ѧϰ��
			if (!StringUtils.isNull(sfzss) && StringUtils.isEqual(sfzss, "��")) {
				bl5 = dao.getOneRs("select gzxxcx from zhcpfblszb where sfzds = 0", new String[]{}, "gzxxcx");
			} else {
				bl5 = dao.getOneRs("select gzxxcx from zhcpfblszb where sfzds = 1", new String[]{}, "gzxxcx");
			}
		
		float fBl1 = StringUtils.isNull(bl1) ? 0 : Float.parseFloat(bl1);//ѧԺ��
		float fBl2 = StringUtils.isNull(bl2) ? 0 : Float.parseFloat(bl2);//��Ԣ��
		float fBl3 = StringUtils.isNull(bl3) ? 0 : Float.parseFloat(bl3);//������
		float fBl4 = StringUtils.isNull(bl4) ? 0 : Float.parseFloat(bl4);//������
		float fBl5 = StringUtils.isNull(bl5) ? 0 : Float.parseFloat(bl5);//����ѧϰ��
		float fDf1 = StringUtils.isNull(df1) ? 0 : Float.parseFloat(df1);//ѧԺ��
		float fDf2 = StringUtils.isNull(df2) ? 0 : Float.parseFloat(df2);//������
		float fDf3 = StringUtils.isNull(df3) ? 0 : Float.parseFloat(df3);//������
		float fDf4 = StringUtils.isNull(df4) ? 0 : Float.parseFloat(df4);//��Ԣ��
		float fDf5 = StringUtils.isNull(df5) ? 0 : Float.parseFloat(df5);//����ѧϰ��
		//�ۺ����ʲ����ܷ�
		String zf = "" + ((fDf1*fBl1/100) +(fDf2*fBl2/100) +(fDf2*fBl3/100) +(fDf3*fBl4/100) +(fDf4*fBl2/100) + (fDf5*fBl5/100)); 
		if (StringUtils.isNull(zf)) {
			return "";
		} 
		if (!StringUtils.isNull(zf) && StringUtils.isEqual(zf, "0")) {
			return "";
		}
		if (!StringUtils.isNull(zf) && zf.indexOf(".") > 0) {
			return zf.substring(0, zf.indexOf(".") + 2);
		}
		return zf;
	}
	
	/**
	 * �ۺ����ʲ�������
	 * @param zhszcpModel
	 * @return
	 * @throws Exception
	 */
	public boolean zhszcpSave(ZhszcpModel zhszcpModel, HttpServletRequest request) throws Exception {
		boolean bFlag = false;
		bFlag = StandardOperation.delete("zhszcp", "xh||xn||xq||nd",
				zhszcpModel.getXh() + zhszcpModel.getXn() + zhszcpModel.getXq()
						+ zhszcpModel.getNd(), request);
		if (bFlag) {
			bFlag = StandardOperation
					.insert("zhszcp", new String[] { "xh", "xn", "xq", "nd",
							"xydykpf", "zcj", "tcj", "gzxxcx", "gydykpf",
							"zhszcpzf", "zhszcpcjpm", "bz" }, new String[] {
							zhszcpModel.getXh(), zhszcpModel.getXn(),
							zhszcpModel.getXq(), zhszcpModel.getNd(),
							zhszcpModel.getXydykpf(), zhszcpModel.getZcj(),
							zhszcpModel.getTcj(), zhszcpModel.getGzxxcx(),
							zhszcpModel.getGydykpf(),
							zhszcpModel.getZhszcpzf(),
							zhszcpModel.getZhszcpcjpm(), DealString.toGBK(zhszcpModel.getBz()) },
							request);
			/*if (bFlag) {
				String sPm = zhszcpModel.getZhszcpcjpm();
				if (StringUtils.isNull(sPm)) {//�Զ���������
					sPm = dao
							.getOneRs(
									"select xh,xn,nd,xq,pm from (select xh,xn,nd,xq,bjdm,zhszcpzf,(dense_rank() over (partition by bjdm,xn,nd,xq order by to_number(zhszcpzf) desc nulls last)) pm from view_zhszcp) where xh=? and xn=? and xq=? and nd=?",
									new String[] { zhszcpModel.getXh()
											+ zhszcpModel.getXn() + zhszcpModel.getXq()
											+ zhszcpModel.getNd() }, "pm");
					StandardOperation.update("zhszcp",
							new String[] { "zhszcpcjpm" },
							new String[] { sPm }, "xh||xn||xq||nd", zhszcpModel
									.getXh()
									+ zhszcpModel.getXn()
									+ zhszcpModel.getXq()
									+ zhszcpModel.getNd(), request);
				}
			}*/
		}
		return bFlag;
	}
	
	/**
	 * �ۺ����ʲ�������ɾ��
	 * @param keys
	 * @param request
	 * @return NULLɾ���ɹ���NOT NULL ɾ��ʧ��
	 * @throws Exception
	 */
	public String zhszcpDel(String[] keys, HttpServletRequest request) throws Exception {
		String sFlag = "";
		for (int i = 0; i < keys.length; i++) {
			boolean bFlag = StandardOperation.delete("zhszcp", "xh||xn||xq||nd", keys[i], request);
			if (!bFlag) {//ʧ�ܺ��¼��������
				sFlag += (i+1);
				sFlag += ",";
			}
		}
		return StringUtils.isNull(sFlag) ? "" : sFlag.substring(0, sFlag.length() - 1);
	}
	
	/**
	 * ͨ��������ȡѧ���ۺ����ʲ����ɼ�
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getZhszcpXx(String pkValue) throws Exception {
		String[] opList = new String[] { "xh", "xm", "xb", "nj", "xymc",
				"zymc", "bjmc", "xn", "nd", "xq", "xydykpf", "zcj", "tcj",
				"gzxxcx", "gydykpf", "zhszcpzf", "zhszcpcjpm", "bz", "sfzss" };
		return dao
				.getMap(
						"select xh,xm,xb,nj,xymc,zymc,bjmc,xn,nd,xq,xydykpf,zcj,tcj,gydykpf,gzxxcx,zhszcpzf,zhszcpcjpm,bz,(select count(*) num from xszsxxb where xszsxxb.xh=view_zhszcp.xh) sfzss from view_zhszcp where xh||xn||xq||nd = ?",
						new String[] { pkValue }, opList);
	}
	
	/**
	 * �ۺ����ʲ����ɼ��޸ı���
	 * @param zhszcpModel
	 * @param pkValue
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean zhszcpModi(ZhszcpModel zhszcpModel, String pkValue, HttpServletRequest request) throws Exception {
		boolean bFlag = false;
		boolean bDel = false;
		if (StringUtils.isEqual(pkValue, zhszcpModel.getXh()
				+ zhszcpModel.getXn() + zhszcpModel.getXq()
				+ zhszcpModel.getNd())) {//δ�޸�����
			bDel = StandardOperation.delete("zhszcp", "xh||xn||xq||nd", pkValue, request);
		} else {//�޸�����
			bDel = StandardOperation.delete("zhszcp", "xh||xn||xq||nd", pkValue, request);
			bDel = StandardOperation.delete("zhszcp", "xh||xn||xq||nd", zhszcpModel.getXh()
					+ zhszcpModel.getXn() + zhszcpModel.getXq()
					+ zhszcpModel.getNd() , request);
			
		}
		if (bDel) {
			bFlag = StandardOperation
			.insert("zhszcp", new String[] { "xh", "xn", "xq", "nd",
					"xydykpf", "zcj", "tcj", "gzxxcx", "gydykpf",
					"zhszcpzf", "zhszcpcjpm", "bz" }, new String[] {
					zhszcpModel.getXh(), zhszcpModel.getXn(),
					zhszcpModel.getXq(), zhszcpModel.getNd(),
					zhszcpModel.getXydykpf(), zhszcpModel.getZcj(),
					zhszcpModel.getTcj(), zhszcpModel.getGzxxcx(),
					zhszcpModel.getGydykpf(),
					zhszcpModel.getZhszcpzf(),
					zhszcpModel.getZhszcpcjpm(), DealString.toGBK(zhszcpModel.getBz()) },
					request);
			/*if (bFlag) {
				String sPm = zhszcpModel.getZhszcpcjpm();
				if (StringUtils.isNull(sPm)) {//�Զ���������
					sPm = dao
							.getOneRs(
									"select xh,xn,nd,xq,pm from (select xh,xn,nd,xq,bjdm,zhszcpzf,(dense_rank() over (partition by bjdm,xn,nd,xq order by to_number(zhszcpzf) desc nulls last)) pm from view_zhszcp) where xh=? and xn=? and xq=? and nd=?",
									new String[] { zhszcpModel.getXh()
											+ zhszcpModel.getXn() + zhszcpModel.getXq()
											+ zhszcpModel.getNd() }, "pm");
					StandardOperation.update("zhszcp",
							new String[] { "zhszcpcjpm" },
							new String[] { sPm }, "xh||xn||xq||nd", zhszcpModel
									.getXh()
									+ zhszcpModel.getXn()
									+ zhszcpModel.getXq()
									+ zhszcpModel.getNd(), request);
				}
			}*/
		}
		return bFlag;
	}
	
	/**
	 * �����ƺŴ�ӡ
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> rychPrint(String pkValue) throws Exception {
		HashMap<String, String> resList = new HashMap<String,String>();
		String[] opList = new String[]{"xn", "xq", "zymc","bjmc", "xm", "xb", "rychmc", "drzw", "zzmm", "mz", "zhpfmc", "cjmc", "jtdz", "sjhm", "zysj", "fdyyj", "xymc","xyyj", "fkyj", "fdyshsj", "xyshsj", "xxshsj", "fdyqm", "xyqm"};
		resList = dao.getMap("select a.fdyqm,a.bjmc,a.xyqm,a.xymc,a.xn,a.xq,a.zymc,a.xm,a.xb,a.rychmc,c.cjmc,a.fdyyj,a.xyyj,a.fkyj,a.fdyshsj,a.xyshsj,a.xxshsj,c.drzw,c.zhpfmc,c.zzmm,c.jtdz,a.zysj,(select sjhm from xsfzxxb b where a.xh=b.xh) sjhm,(select mzmc from view_xsxxb d where a.xh=d.xh) mz from view_xsrychb a left join xsrychxxb c on a.xh=c.xh where a.xn||a.nd||a.xh||a.rychdm=?", new String[]{pkValue}, opList);
		if (resList == null || StringUtils.isNull(resList.get("xm"))) {
			resList = dao.getMap("select a.fdyqm,a.bjmc,a.xymc,a.xyqm,a.xn,a.xq,a.zymc,a.xm,a.xb,a.rychmc,c.cjmc,a.fdyyj,a.xyyj,a.fkyj,a.fdyshsj,a.xyshsj,a.xxshsj,c.drzw,c.zhpfmc,c.zzmm,c.jtdz,a.zysj,(select sjhm from xsfzxxb b where a.xh=b.xh) sjhm,(select mzmc from view_xsxxb d where a.xh=d.xh) mz from view_xsrychb a left join xsrychxxb c on a.xh=c.xh where a.xn||a.nd||a.rychdm||a.xh=?", new String[]{pkValue}, opList);
		}
		return resList;
	}
	
	public boolean updateQm(String qm, String keys, String userType, String isFdy, String tableName) throws Exception {
		String pk = "";
		if (!StringUtils.isNull(tableName) && "xsjxjb".equalsIgnoreCase(tableName)) {
			pk = "xn||nd||xh||jxjdm";
		} else {
			pk = "xn||nd||xh||rychdm";
		}
		if (!StringUtils.isNull(keys)) {
			String[] key = keys.split("@");
			StringBuffer sql = new StringBuffer();
			String[] sqls = new String[key.length];
			for (int i=0;i<key.length;i++) {
				sql = new StringBuffer();
				if ("true".equalsIgnoreCase(isFdy)) {
					sql.append(" update ");
					sql.append(tableName);
					sql.append(" set fdyqm='");
					sql.append(DealString.toGBK(qm));
					sql.append("' where ");
					sql.append(pk);
					sql.append(" ='");
					sql.append(key[i]);
					sql.append("'");
				} else {
					sql.append(" update ");
					sql.append(tableName);
					sql.append(" set xyqm='");
					//sql.append(" update "+tableName+" set xyqm='");
					sql.append(DealString.toGBK(qm));
					sql.append("' where ");
					sql.append(pk);
					sql.append(" ='");
					sql.append(key[i]);
					sql.append("'");
				}
				sqls[i] = sql.toString();
			}
			dao.runBatch(sqls);
		}
		return true;
	}
	/**
	 * ����ְҵ�Ƚ��ƺ��������ʱ�¼�δͨ���ж�
	 * @param tableName
	 * @param strValue
	 * @return
	 */
	public boolean getNoPassIsNo(String tableName,String userType,String strValue){
		DAO dao= DAO.getInstance();
		boolean flag=false;
		int result=0;
		String[] queryValue =  strValue.split("!!");
		String pkVArray = "'";
		for(int i=0;i<queryValue.length;i++){
			if(!Base.isNull(queryValue[i])){
			   pkVArray+=RowidToPk.rowidToPK(queryValue[i])+"','";
			}
		}
		pkVArray = pkVArray.substring(0,pkVArray.length()-2);
		String sql="";	
		if("xx".equalsIgnoreCase(userType)||"admin".equalsIgnoreCase(userType)){
			sql = "select count(*) num from "+tableName+" where rowid in ("+pkVArray+") and xysh<>'ͨ��' ";
		}else if("xy".equalsIgnoreCase(userType)){
			sql = "select count(*) num from "+tableName+" where rowid in ("+pkVArray+") and fdysh<>'ͨ��' ";
		}
		result=Integer.parseInt(dao.getOneRs(sql, new String[]{}, "num"));
		if(result>0){
		    flag=true;
		}else{
		    flag=false;
		}		
		return flag;
	}
}
