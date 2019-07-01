package xgxt.xszz.comm.exp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import common.Globals;
import common.XszzXmdm;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.xszz.XszzTyForm;
import xgxt.xszz.comm.XszzCommDAO;

public class XszzExpDAO extends XszzCommDAO {
	
	/**
	 * ��õ������ݱ�ͷ
	 * 
	 * @param xh
	 * @return
	 */
	public List<HashMap<String, String>> getTopTr(String xxdm, String xmdm) {

		DAO dao = DAO.getInstance();

		String[] colListCN = null;
		String[] colListEN = null;

		if (Globals.XXDM_ZGDZDX.equalsIgnoreCase(xxdm)) {// �й��ش�
			colListCN = new String[] { "���", "��Ŀ����", "��Ŀ����", "ѧ��", "����", "�Ա�",
					"�꼶", Base.YXPZXY_KEY+"����", "רҵ����", "�༶����", "����", "������ò", "���֤��","����", "���",
					"ѧ��", "ѧ��", "���", "����ʱ��", "���״̬1", "���״̬2", "���״̬3",
					"����������", "�Ƿ�²�", "�Ƿ�ͱ�", "��ʿ��Ů", "�Ƿ���", "��ͥ����", "��ͥ��ַ",
					"��ͥ�绰", "��ͥ�ʱ�", "��ͥ����", "��ͥ�˾�����", "������Դ", "�ѻ��������",
					"��ͥǷծ���", "��ͥ�������", "�����ˮƽ", "����ˮƽ", "�ϰ�ѧ��ѧ�ּ���",
					"�°�ѧ��ѧ�ּ���", "�༶����", "�۲�����", "�������", "������1", "������1",
					"������2", "������2", "������3", "������3", "������4", "������4",
					"������" };
			colListEN = new String[] { "num", "xmdm", "xmmc", "xh", "xm", "xb",
					"nj", "xymc", "zymc", "bjmc", "mzmc", "zzmmmc", "xmzzjb",
					"xmzzje", "xn", "xq", "nd", "sqsj", "shzt1", "shzt2",
					"shzt3", "knsjb", "sfgc", "sfdq", "lszn", "sfdb", "jthk",
					"jtdz", "jtdh", "jtyb", "jtrs", "jtrjsr", "srly", "yhzzqk",
					"jtqzqk", "jtqtqk", "jsjsp", "wysp", "sbxqxfjd",
					"xbxqxfjd", "bjpm", "zcpm", "kyqk", "hjrq1", "hjmc1",
					"hjrq2", "hjmc2", "hjrq3", "hjmc3", "hjrq4", "hjmc4",
					"zjqk" };
		} else if (Globals.XXDM_WHSYFWXY.equalsIgnoreCase(xxdm)) {//�人��ҵ����
			if (XszzXmdm.XSZZ_GJZXJ.equalsIgnoreCase(xmdm)) {//������ѧ������
				colListCN = new String[] { "���", "��Ŀ����", "��Ŀ����", "ѧ��", "����", "�Ա�",
						"�꼶", Base.YXPZXY_KEY+"����", "רҵ����", "�༶����", "����", "������ò", "���֤��","��������", "��ѧ����", "����", "���",
						"ѧ��", "ѧ��", "���", "����ʱ��", "����","���״̬1", "���״̬2", "���״̬3",
						"����������", "�Ƿ�²�", "�Ƿ�ͱ�", "��ʿ��Ů", "�Ƿ���", "��ͥ����", "��ͥ��ַ",
						"��ͥ�绰", "��ͥ�ʱ�", "��ͥ����", "��ͥ�˾�����", "������Դ", "�ѻ��������",
						"��ͥǷծ���", "��ͥ�������", "�����ˮƽ", "����ˮƽ", "�ϰ�ѧ��ѧ�ּ���",
						"�°�ѧ��ѧ�ּ���", "�༶����", "�۲�����", "�������", "������1", "������1",
						"������2", "������2", "������3", "������3", "������4", "������4",
						"������" };
				colListEN = new String[] { "num", "xmdm", "xmmc", "xh", "xm", "xb",
						"nj", "xymc", "zymc", "bjmc", "mzmc", "zzmmmc","sfzh", "csrq", "rxrq", "xmzzjb",
						"xmzzje", "xn", "xq", "nd", "sqsj", "qy","shzt1", "shzt2",
						"shzt3", "knsjb", "sfgc", "sfdq", "lszn", "sfdb", "jthk",
						"jtdz", "jtdh", "jtyb", "jtrs", "jtrjsr", "srly", "yhzzqk",
						"jtqzqk", "jtqtqk", "jsjsp", "wysp", "sbxqxfjd",
						"xbxqxfjd", "bjpm", "zcpm", "kyqk", "hjrq1", "hjmc1",
						"hjrq2", "hjmc2", "hjrq3", "hjmc3", "hjrq4", "hjmc4",
						"zjqk" };
			} else {//����ͨ��
				colListCN = new String[] { "���", "��Ŀ����", "��Ŀ����", "ѧ��", "����", "�Ա�",
						"�꼶", Base.YXPZXY_KEY+"����", "רҵ����", "�༶����", "����", "������ò", "���֤��","��������", "��ѧ����", "����", "���",
						"ѧ��", "ѧ��", "���", "����ʱ��", "���״̬1", "���״̬2", "���״̬3",
						"����������", "�Ƿ�²�", "�Ƿ�ͱ�", "��ʿ��Ů", "�Ƿ���", "��ͥ����", "��ͥ��ַ",
						"��ͥ�绰", "��ͥ�ʱ�", "��ͥ����", "��ͥ�˾�����", "������Դ", "�ѻ��������",
						"��ͥǷծ���", "��ͥ�������", "�����ˮƽ", "����ˮƽ", "�ϰ�ѧ��ѧ�ּ���",
						"�°�ѧ��ѧ�ּ���", "�༶����", "�۲�����", "�������", "������1", "������1",
						"������2", "������2", "������3", "������3", "������4", "������4",
						"������" };
				colListEN = new String[] { "num", "xmdm", "xmmc", "xh", "xm", "xb",
						"nj", "xymc", "zymc", "bjmc", "mzmc", "zzmmmc","sfzh", "csrq", "rxrq", "xmzzjb",
						"xmzzje", "xn", "xq", "nd", "sqsj", "shzt1", "shzt2",
						"shzt3", "knsjb", "sfgc", "sfdq", "lszn", "sfdb", "jthk",
						"jtdz", "jtdh", "jtyb", "jtrs", "jtrjsr", "srly", "yhzzqk",
						"jtqzqk", "jtqtqk", "jsjsp", "wysp", "sbxqxfjd",
						"xbxqxfjd", "bjpm", "zcpm", "kyqk", "hjrq1", "hjmc1",
						"hjrq2", "hjmc2", "hjrq3", "hjmc3", "hjrq4", "hjmc4",
						"zjqk" };
			}
		}else if (Globals.XXDM_HZNYDX.equalsIgnoreCase(xxdm) && XszzXmdm.XSZZ_HZNY_LSTD.equalsIgnoreCase(xmdm)) {//������ѧ������
				
				colListCN = new String[] { "ѧԺ", "רҵ", "����", "ѧ��", "ס�޷�", "�ӷ�","Ƿ���ܶ�","��Դʡ","��ע" };
				colListEN = new String[] { "xymc","xymc","xm","kzzd1","kzzd2","kzzd3","kzzd4","sydq","bz" };
			
		}else if (Globals.XXDM_HZSFXY.equalsIgnoreCase(xxdm)) {//����ʦ��ѧԺ
			//��½����Ӣ��������ѧ��&��ӭ�����ˡ���ѧ��
			if (XszzXmdm.XSZZ_HZSF_LHYY.equalsIgnoreCase(xmdm) || XszzXmdm.XSZZ_HZSF_YNSR.equalsIgnoreCase(xmdm)) {
				colListCN = new String[] { "���", Base.YXPZXY_KEY, "����", "�Ա�","�༶", "��У�ڼ�����","�������" };
				colListEN = new String[] { "num","xymc","xm","xb","bjmc","bz","xmzzjb"};
			}else{
				colListCN = new String[] { "���", "��Ŀ����", "��Ŀ����", "ѧ��", "����", "�Ա�",
						"�꼶", Base.YXPZXY_KEY+"����", "רҵ����", "�༶����", "����", "������ò", "����", "���",
						"ѧ��", "ѧ��", "���", "����ʱ��", "���״̬1", "���״̬2", "���״̬3",
						"����������", "�Ƿ�²�", "�Ƿ�ͱ�", "��ʿ��Ů", "�Ƿ���", "��ͥ����", "��ͥ��ַ",
						"��ͥ�绰", "��ͥ�ʱ�", "��ͥ����", "��ͥ�˾�����", "������Դ", "�ѻ��������",
						"��ͥǷծ���", "��ͥ�������" };
				colListEN = new String[] { "num", "xmdm", "xmmc", "xh", "xm", "xb",
						"nj", "xymc", "zymc", "bjmc", "mzmc", "zzmmmc", "xmzzjb",
						"xmzzje", "xn", "xq", "nd", "sqsj", "shzt1", "shzt2",
						"shzt3", "knsjb", "sfgc", "sfdq", "lszn", "sfdb", "jthk",
						"jtdz", "jtdh", "jtyb", "jtrs", "jtrjsr", "srly", "yhzzqk",
						"jtqzqk", "jtqtqk" };
			}
		}else {
			colListCN = new String[] { "���", "��Ŀ����", "��Ŀ����", "ѧ��", "����", "�Ա�",
					"�꼶", Base.YXPZXY_KEY+"����", "רҵ����", "�༶����", "����", "������ò", "����", "���",
					"ѧ��", "ѧ��", "���", "����ʱ��", "���״̬1", "���״̬2", "���״̬3",
					"����������", "�Ƿ�²�", "�Ƿ�ͱ�", "��ʿ��Ů", "�Ƿ���", "��ͥ����", "��ͥ��ַ",
					"��ͥ�绰", "��ͥ�ʱ�", "��ͥ����", "��ͥ�˾�����", "������Դ", "�ѻ��������",
					"��ͥǷծ���", "��ͥ�������" };
			colListEN = new String[] { "num", "xmdm", "xmmc", "xh", "xm", "xb",
					"nj", "xymc", "zymc", "bjmc", "mzmc", "zzmmmc", "xmzzjb",
					"xmzzje", "xn", "xq", "nd", "sqsj", "shzt1", "shzt2",
					"shzt3", "knsjb", "sfgc", "sfdq", "lszn", "sfdb", "jthk",
					"jtdz", "jtdh", "jtyb", "jtrs", "jtrjsr", "srly", "yhzzqk",
					"jtqzqk", "jtqtqk" };
		}

		return dao.arrayToList(colListEN, colListCN);
	}

	/**
	 * ��õ��������б�(�й��ش�)
	 * 
	 * @param xh
	 * @return
	 */
	public ArrayList<String[]> getZgddExpList(XszzTyForm model,
			HashMap<String, String> xmInfo) {

		DAO dao = DAO.getInstance();
		// ��������˼���
		String knshjb = dao.getOneValue("xszz_zzxmb", "shjb", "xmdm",
				XszzXmdm.XSZZ_KNS);
		// ��������������
		String knshzq = dao.getOneValue("xszz_zzxmb", "sqzq", "xmdm",
				XszzXmdm.XSZZ_KNS);
		// ��Ŀ��
		String xmb = xmInfo.get("xmb");
		// ��������
		String sqzq = xmInfo.get("sqzq");
		// ѧ��
		String xn = model.getXn();
		// ѧ��
		String xq = model.getXq();
		// ���
		String nd = model.getNd();
		// �꼶
		String nj = model.getNj();
		// ѧԺ
		String xydm = model.getXydm();
		// רҵ
		String zydm = model.getZydm();
		// �༶
		String bjdm = model.getBjdm();
		// ѧ��
		String xh = model.getXh();
		// ����
		String xm = model.getXm();
		// ��ʼʱ��
		String kssj = model.getKssj();
		// ����ʱ��
		String jssj = model.getJssj();
		// ��Ŀ����
		String xmdm = model.getXmdm();
		// ��Ŀ����
		String xmzzjb = model.getXmzzjb();
		// ���״̬1
		String shzt1 = model.getShzt1();
		// ���״̬2
		String shzt2 = model.getShzt2();
		// ���״̬3
		String shzt3 = model.getShzt3();
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select t.* from (");
		sql.append(" select distinct a.*,rownum num,nvl(g.xmzzjb,'�޼���') knsjb,f.sfgc,f.sfdq,f.lszn,f.sfdb,f.jthk,f.jtdz,");
		sql.append(" f.jtdh,f.jtyb,f.jtrs,f.jtrjsr,f.srly,f.yhzzqk,f.jtqzqk,");
		sql.append(" f.jtqtqk,d.jsjsp,d.wysp,d.sbxqxfjd,d.xbxqxfjd,");
		
		sql.append(" nvl(d.bjpm, 0) || '/' ||(select e.num from (select");
		sql.append(" count(1) num, bjdm from view_xsjbxx group by bjdm) e");
		sql.append(" where a.bjdm = e.bjdm) bjpm,");
		
		sql.append(" nvl(d.zcbjpm, 0) || '/' ||(select e.num from (select");
		sql.append(" count(1) num, bjdm from view_xsjbxx group by bjdm) e");
		sql.append(" where a.bjdm = e.bjdm) zcpm,");
		
		sql.append(" d.kyqk,d.hjrq1,d.hjmc1,d.hjrq2,d.hjmc2,d.hjrq3,");
		sql.append(" d.hjmc3,d.hjrq4,d.hjmc4,d.zjqk from (select a.xmdm,");
		
		sql.append(" a.sqsj,a.shzt1,a.shzt2,a.shzt3,");
		sql.append(" (select c.xmmc from xszz_zzxmb c where a.xmdm = c.xmdm) xmmc,");
		sql.append(" b.xm, a.xh, b.xb, b.nj, b.xydm, b.xymc, b.zydm,a.xn,a.xq,a.nd,");
		sql.append(" b.zymc, b.bjdm, b.bjmc,b.sfzh, b.mzmc, b.zzmmmc, a.xmzzjb,");
		sql.append(" a.xmzzje from ");
		sql.append(xmb);
		sql.append(" a,view_xsxxb b where a.xh = b.xh ");
		
		// ======================��ѯ����=============================
		sql.append(Base.isNull(xmdm) ? "" : " and a.xmdm = '" + xmdm + "' ");
		if ("xn".equalsIgnoreCase(sqzq)) {// ����ѧ��
			sql.append(Base.isNull(xn) ? "" : " and a.xn = '" + xn + "' ");
		} else if ("nd".equalsIgnoreCase(sqzq)) {// �������
			sql.append(Base.isNull(nd) ? "" : " and a.nd = '" + nd + "' ");
		} else if ("xq".equalsIgnoreCase(sqzq)) {// ����ѧ��
			sql.append(Base.isNull(xn) ? "" : " and a.xn = '" + xn + "' ");
			sql.append(Base.isNull(xq) ? "" : " and a.xq = '" + xq + "' ");
		}
		sql.append(Base.isNull(kssj) ? "" : " and a.sqsj >= '" + kssj + "'");
		sql.append(Base.isNull(jssj) ? "" : " and a.sqsj <= '" + jssj + "'");

		sql.append(Base.isNull(xmzzjb) ? "" : " and a.xmzzjb = '" + xmzzjb
				+ "' ");
		sql.append(Base.isNull(shzt1) ? "" : " and a.shzt1 = '" + shzt1 + "' ");
		sql.append(Base.isNull(shzt2) ? "" : " and a.shzt2 = '" + shzt2 + "' ");
		sql.append(Base.isNull(shzt3) ? "" : " and a.shzt3 = '" + shzt3 + "' ");
		
		sql.append(Base.isNull(nj) ? "" : " and b.nj = '" + nj + "' ");
		sql.append(Base.isNull(xydm) ? "" : " and b.xydm = '" + xydm + "' ");
		sql.append(Base.isNull(zydm) ? "" : " and b.zydm = '" + zydm + "' ");
		sql.append(Base.isNull(bjdm) ? "" : " and b.bjdm = '" + bjdm + "' ");
		
		sql.append(Base.isNull(xh) ? "" : " and b.xh like '%" + xh + "%' ");
		sql.append(Base.isNull(xm) ? "" : " and b.xm like '%" + xm + "%' ");
		// ======================end=============================
		sql.append(" ) a ");
		
		sql.append(" left join (select a.* from xsqtxxb a, (select xh, Max(sqsj) sqsj from xsqtxxb group by xh) b ");
		sql.append(" where a.sqsj = b.sqsj and a.xh = b.xh) d on a.xh = d.xh ");
//		sql.append(" left join xsqtxxb d on a.xh = d.xh ");
//		sql.append(" and d.sqsj=(select max(sqsj) from xsqtxxb where xh = '"+xh+"')");
		
		// ===========�������ڱ�����ͬ��������Ŀ��������Ϣ��=========
		/*sql.append(" and a.xn = d.xn ");
		sql.append(" and a.nd = d.nd ");
		sql.append(" and a.xn = d.xn ");
		sql.append(" and a.xq = d.xq ");*/
		// }
		// ======================end=============================
		
		// ======================��ͥ��Ϣȡ���µ�һ��=====================
		sql.append(" left join (select a.* from jtqkdcb a where exists (select 1");
		sql.append(" from (select xh, max(sqsj) sqsj from jtqkdcb group by xh) b");
		sql.append(" where a.xh = b.xh and a.sqsj = b.sqsj)) f on a.xh = f.xh");
		// ======================end=============================

		sql.append(" left join xszz_knsb g on a.xh = g.xh");
		// ===========�������ڱ�����ͬ��������Ŀ��������Ϣ��=========
		sql.append("ѧ��".equalsIgnoreCase(knshzq) ? " and g.xn = a.xn" : "");
		sql.append("���".equalsIgnoreCase(knshzq) ? " and g.nd = a.nd" : "");
		sql.append("ѧ��".equalsIgnoreCase(knshzq) ? " and g.xn = a.xn" : "");
		sql.append("ѧ��".equalsIgnoreCase(knshzq) ? " and g.xq = a.xq" : "");
		
		if ("һ�����".equalsIgnoreCase(knshjb)) {// ����ѧ��
			sql.append(" and g.shzt1 = 'ͨ��' ");
		} else if ("�������".equalsIgnoreCase(knshjb)) {// �������
			sql.append(" and g.shzt2 = 'ͨ��' ");
		} else if ("�������".equalsIgnoreCase(knshjb)) {// ����ѧ��
			sql.append(" and g.shzt3 = 'ͨ��' ");
		}
		// ======================end=============================
		
		sql.append(") t order by num");
		//System.out.println(sql.toString());
		String[] outputValue = new String[] { "num","xmdm", "xmmc", "xh", "xm", "xb",
				"nj", "xymc", "zymc", "bjmc", "mzmc", "zzmmmc","sfzh", "xmzzjb",
				"xmzzje", "xn", "xq", "nd", "sqsj", "shzt1", "shzt2", "shzt3",
				"knsjb", "sfgc", "sfdq", "lszn", "sfdb", "jthk", "jtdz",
				"jtdh", "jtyb", "jtrs", "jtrjsr", "srly", "yhzzqk", "jtqzqk",
				"jtqtqk", "jsjsp", "wysp", "sbxqxfjd", "xbxqxfjd", "bjpm",
				"zcpm", "kyqk", "hjrq1", "hjmc1", "hjrq2", "hjmc2", "hjrq3",
				"hjmc3", "hjrq4", "hjmc4", "zjqk" };
		ArrayList<String[]> list = dao.rsToVator(sql.toString(), new String[]{}, outputValue);
		
		return list;
	}
	
	/**
	 * ��õ��������б�(����ʦ��)
	 * 
	 * @param xh
	 * @return
	 */
	public ArrayList<String[]> getXysfExpList(XszzTyForm model,
			HashMap<String, String> xmInfo) {

		DAO dao = DAO.getInstance();
		// ��������˼���
		String knshjb = dao.getOneValue("xszz_zzxmb", "shjb", "xmdm",
				XszzXmdm.XSZZ_KNS);
		// ��������������
		String knshzq = dao.getOneValue("xszz_zzxmb", "sqzq", "xmdm",
				XszzXmdm.XSZZ_KNS);
		// ��Ŀ��
		String xmb = xmInfo.get("xmb");
		// ��������
		String sqzq = xmInfo.get("sqzq");
		// ѧ��
		String xn = model.getXn();
		// ѧ��
		String xq = model.getXq();
		// ���
		String nd = model.getNd();
		// �꼶
		String nj = model.getNj();
		// ѧԺ
		String xydm = model.getXydm();
		// רҵ
		String zydm = model.getZydm();
		// �༶
		String bjdm = model.getBjdm();
		// ѧ��
		String xh = model.getXh();
		// ����
		String xm = model.getXm();
		// ��ʼʱ��
		String kssj = model.getKssj();
		// ����ʱ��
		String jssj = model.getJssj();
		// ��Ŀ����
		String xmdm = model.getXmdm();
		// ��Ŀ����
		String xmzzjb = model.getXmzzjb();
		// ���״̬1
		String shzt1 = model.getShzt1();
		// ���״̬2
		String shzt2 = model.getShzt2();
		// ���״̬3
		String shzt3 = model.getShzt3();
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select t.* from (");
		sql.append(" select distinct a.*,rownum num,nvl(g.xmzzjb,'�޼���') knsjb,f.sfgc,f.sfdq,f.lszn,f.sfdb,f.jthk,f.jtdz,");
		sql.append(" f.jtdh,f.jtyb,f.jtrs,f.jtrjsr,f.srly,f.yhzzqk,f.jtqzqk,");
		sql.append(" f.jtqtqk from (select a.xmdm,a.sqsj,a.shzt1,a.shzt2,a.shzt3,");
		sql.append(" (select c.xmmc from xszz_zzxmb c where a.xmdm = c.xmdm) xmmc,");
		sql.append(" b.xm, a.xh, b.xb, b.nj, b.xydm, b.xymc, b.zydm,a.xn,a.xq,a.nd,");
		sql.append(" b.zymc, b.bjdm, b.bjmc, b.mzmc, b.zzmmmc, a.xmzzjb,");
		sql.append(" a.xmzzje from ");
		sql.append(xmb);
		sql.append(" a,view_xsxxb b where a.xh = b.xh ");
		
		// ======================��ѯ����=============================
		sql.append(Base.isNull(xmdm) ? "" : " and a.xmdm = '" + xmdm + "' ");
		if ("xn".equalsIgnoreCase(sqzq)) {// ����ѧ��
			sql.append(Base.isNull(xn) ? "" : " and a.xn = '" + xn + "' ");
		} else if ("nd".equalsIgnoreCase(sqzq)) {// �������
			sql.append(Base.isNull(nd) ? "" : " and a.nd = '" + nd + "' ");
		} else if ("xq".equalsIgnoreCase(sqzq)) {// ����ѧ��
			sql.append(Base.isNull(xn) ? "" : " and a.xn = '" + xn + "' ");
			sql.append(Base.isNull(xq) ? "" : " and a.xq = '" + xq + "' ");
		}
		sql.append(Base.isNull(kssj) ? "" : " and a.sqsj >= '" + kssj + "'");
		sql.append(Base.isNull(jssj) ? "" : " and a.sqsj <= '" + jssj + "'");

		sql.append(Base.isNull(xmzzjb) ? "" : " and a.xmzzjb = '" + xmzzjb
				+ "' ");
		sql.append(Base.isNull(shzt1) ? "" : " and a.shzt1 = '" + shzt1 + "' ");
		sql.append(Base.isNull(shzt2) ? "" : " and a.shzt2 = '" + shzt2 + "' ");
		sql.append(Base.isNull(shzt3) ? "" : " and a.shzt3 = '" + shzt3 + "' ");
		
		sql.append(Base.isNull(nj) ? "" : " and b.nj = '" + nj + "' ");
		sql.append(Base.isNull(xydm) ? "" : " and b.xydm = '" + xydm + "' ");
		sql.append(Base.isNull(zydm) ? "" : " and b.zydm = '" + zydm + "' ");
		sql.append(Base.isNull(bjdm) ? "" : " and b.bjdm = '" + bjdm + "' ");
		
		sql.append(Base.isNull(xh) ? "" : " and b.xh like '%" + xh + "%' ");
		sql.append(Base.isNull(xm) ? "" : " and b.xm like '%" + xm + "%' ");
		// ======================end=============================
		sql.append(" ) a ");
		
		// ======================��ͥ��Ϣȡ���µ�һ��=====================
		sql.append(" left join (select a.* from jtqkdcb a where exists (select 1");
		sql.append(" from (select xh, max(sqsj) sqsj from jtqkdcb group by xh) b");
		sql.append(" where a.xh = b.xh and a.sqsj = b.sqsj)) f on a.xh = f.xh");
		// ======================end=============================

		sql.append(" left join xszz_knsb g on a.xh = g.xh");
		// ===========�������ڱ�����ͬ��������Ŀ��������Ϣ��=========
		sql.append("ѧ��".equalsIgnoreCase(knshzq) ? " and g.xn = a.xn" : "");
		sql.append("���".equalsIgnoreCase(knshzq) ? " and g.nd = a.nd" : "");
		sql.append("ѧ��".equalsIgnoreCase(knshzq) ? " and g.xn = a.xn" : "");
		sql.append("ѧ��".equalsIgnoreCase(knshzq) ? " and g.xq = a.xq" : "");
		
		if ("һ�����".equalsIgnoreCase(knshjb)) {// ����ѧ��
			sql.append(" and g.shzt1 = 'ͨ��' ");
		} else if ("�������".equalsIgnoreCase(knshjb)) {// �������
			sql.append(" and g.shzt2 = 'ͨ��' ");
		} else if ("�������".equalsIgnoreCase(knshjb)) {// ����ѧ��
			sql.append(" and g.shzt3 = 'ͨ��' ");
		}
		// ======================end=============================
		
		sql.append(") t order by num");
		System.out.println(sql.toString());
		String[] outputValue = new String[] { "num","xmdm", "xmmc", "xh", "xm", "xb",
				"nj", "xymc", "zymc", "bjmc", "mzmc", "zzmmmc", "xmzzjb",
				"xmzzje", "xn", "xq", "nd", "sqsj", "shzt1", "shzt2", "shzt3",
				"knsjb", "sfgc", "sfdq", "lszn", "sfdb", "jthk", "jtdz",
				"jtdh", "jtyb", "jtrs", "jtrjsr", "srly", "yhzzqk", "jtqzqk",
				"jtqtqk"};
		ArrayList<String[]> list = dao.rsToVator(sql.toString(), new String[]{}, outputValue);
		
		return list;
	}
	
	/**
	 * ��õ��������б�(�人��ҵ����)
	 * 
	 * @param xh
	 * @return
	 */
	public ArrayList<String[]> getWhsyExpList(XszzTyForm model,
			HashMap<String, String> xmInfo) {

		DAO dao = DAO.getInstance();
		// ��������˼���
		String knshjb = dao.getOneValue("xszz_zzxmb", "shjb", "xmdm",
				XszzXmdm.XSZZ_KNS);
		// ��������������
		String knshzq = dao.getOneValue("xszz_zzxmb", "sqzq", "xmdm",
				XszzXmdm.XSZZ_KNS);
		// ��Ŀ��
		String xmb = xmInfo.get("xmb");
		// ��������
		String sqzq = xmInfo.get("sqzq");
		// ѧ��
		String xn = model.getXn();
		// ѧ��
		String xq = model.getXq();
		// ���
		String nd = model.getNd();
		// �꼶
		String nj = model.getNj();
		// ѧԺ
		String xydm = model.getXydm();
		// רҵ
		String zydm = model.getZydm();
		// �༶
		String bjdm = model.getBjdm();
		// ѧ��
		String xh = model.getXh();
		// ����
		String xm = model.getXm();
		// ��ʼʱ��
		String kssj = model.getKssj();
		// ����ʱ��
		String jssj = model.getJssj();
		// ��Ŀ����
		String xmdm = model.getXmdm();
		// ��Ŀ����
		String xmzzjb = model.getXmzzjb();
		// ���״̬1
		String shzt1 = model.getShzt1();
		// ���״̬2
		String shzt2 = model.getShzt2();
		// ���״̬3
		String shzt3 = model.getShzt3();
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select t.* from (");
		sql.append(" select distinct a.*,rownum num,nvl(g.xmzzjb,'�޼���') knsjb,f.sfgc,f.sfdq,f.lszn,f.sfdb,f.jthk,f.jtdz,");
		sql.append(" f.jtdh,f.jtyb,f.jtrs,f.jtrjsr,f.srly,f.yhzzqk,f.jtqzqk,");
		sql.append(" f.jtqtqk,d.jsjsp,d.wysp,d.sbxqxfjd,d.xbxqxfjd,");
		
		sql.append(" nvl(d.bjpm, 0) || '/' ||(select e.num from (select");
		sql.append(" count(1) num, bjdm from view_xsjbxx group by bjdm) e");
		sql.append(" where a.bjdm = e.bjdm) bjpm,");
		
		sql.append(" nvl(d.zcbjpm, 0) || '/' ||(select e.num from (select");
		sql.append(" count(1) num, bjdm from view_xsjbxx group by bjdm) e");
		sql.append(" where a.bjdm = e.bjdm) zcpm,");
		
		sql.append(" d.kyqk,d.hjrq1,d.hjmc1,d.hjrq2,d.hjmc2,d.hjrq3,");
		sql.append(" d.hjmc3,d.hjrq4,d.hjmc4,d.zjqk from (select a.xmdm,");
		
		sql.append(" a.sqsj,a.shzt1,a.shzt2,a.shzt3,");
		sql.append(" (select c.xmmc from xszz_zzxmb c where a.xmdm = c.xmdm) xmmc,");
		sql.append(" b.xm, a.xh, b.xb, b.nj, b.xydm, b.xymc, b.zydm,a.xn,a.xq,a.nd,b.csrq,b.rxrq,");
		sql.append(" b.zymc, b.bjdm, b.bjmc,b.sfzh, b.mzmc, b.zzmmmc, a.xmzzjb,");
		sql.append(" a.xmzzje from ");
		sql.append(xmb);
		sql.append(" a,view_xsxxb b where a.xh = b.xh ");
		
		// ======================��ѯ����=============================
		sql.append(Base.isNull(xmdm) ? "" : " and a.xmdm = '" + xmdm + "' ");
		if ("xn".equalsIgnoreCase(sqzq)) {// ����ѧ��
			sql.append(Base.isNull(xn) ? "" : " and a.xn = '" + xn + "' ");
		} else if ("nd".equalsIgnoreCase(sqzq)) {// �������
			sql.append(Base.isNull(nd) ? "" : " and a.nd = '" + nd + "' ");
		} else if ("xq".equalsIgnoreCase(sqzq)) {// ����ѧ��
			sql.append(Base.isNull(xn) ? "" : " and a.xn = '" + xn + "' ");
			sql.append(Base.isNull(xq) ? "" : " and a.xq = '" + xq + "' ");
		}
		sql.append(Base.isNull(kssj) ? "" : " and a.sqsj >= '" + kssj + "'");
		sql.append(Base.isNull(jssj) ? "" : " and a.sqsj <= '" + jssj + "'");

		sql.append(Base.isNull(xmzzjb) ? "" : " and a.xmzzjb = '" + xmzzjb
				+ "' ");
		sql.append(Base.isNull(shzt1) ? "" : " and a.shzt1 = '" + shzt1 + "' ");
		sql.append(Base.isNull(shzt2) ? "" : " and a.shzt2 = '" + shzt2 + "' ");
		sql.append(Base.isNull(shzt3) ? "" : " and a.shzt3 = '" + shzt3 + "' ");
		
		sql.append(Base.isNull(nj) ? "" : " and b.nj = '" + nj + "' ");
		sql.append(Base.isNull(xydm) ? "" : " and b.xydm = '" + xydm + "' ");
		sql.append(Base.isNull(zydm) ? "" : " and b.zydm = '" + zydm + "' ");
		sql.append(Base.isNull(bjdm) ? "" : " and b.bjdm = '" + bjdm + "' ");
		
		sql.append(Base.isNull(xh) ? "" : " and b.xh like '%" + xh + "%' ");
		sql.append(Base.isNull(xm) ? "" : " and b.xm like '%" + xm + "%' ");
		// ======================end=============================
		sql.append(" ) a ");
		
		sql.append(" left join (select a.* from xsqtxxb a, (select xh, Max(sqsj) sqsj from xsqtxxb group by xh) b ");
		sql.append(" where a.sqsj = b.sqsj and a.xh = b.xh) d on a.xh = d.xh ");
//		sql.append(" left join xsqtxxb d on a.xh = d.xh ");
//		sql.append(" and d.sqsj=(select max(sqsj) from xsqtxxb where xh = '"+xh+"')");
		
		// ===========�������ڱ�����ͬ��������Ŀ��������Ϣ��=========
		/*sql.append(" and a.xn = d.xn ");
		sql.append(" and a.nd = d.nd ");
		sql.append(" and a.xn = d.xn ");
		sql.append(" and a.xq = d.xq ");*/
		// }
		// ======================end=============================
		
		// ======================��ͥ��Ϣȡ���µ�һ��=====================
		sql.append(" left join (select a.* from jtqkdcb a where exists (select 1");
		sql.append(" from (select xh, max(sqsj) sqsj from jtqkdcb group by xh) b");
		sql.append(" where a.xh = b.xh and a.sqsj = b.sqsj)) f on a.xh = f.xh");
		// ======================end=============================

		sql.append(" left join xszz_knsb g on a.xh = g.xh");
		// ===========�������ڱ�����ͬ��������Ŀ��������Ϣ��=========
		sql.append("ѧ��".equalsIgnoreCase(knshzq) ? " and g.xn = a.xn" : "");
		sql.append("���".equalsIgnoreCase(knshzq) ? " and g.nd = a.nd" : "");
		sql.append("ѧ��".equalsIgnoreCase(knshzq) ? " and g.xn = a.xn" : "");
		sql.append("ѧ��".equalsIgnoreCase(knshzq) ? " and g.xq = a.xq" : "");
		
		if ("һ�����".equalsIgnoreCase(knshjb)) {// ����ѧ��
			sql.append(" and g.shzt1 = 'ͨ��' ");
		} else if ("�������".equalsIgnoreCase(knshjb)) {// �������
			sql.append(" and g.shzt2 = 'ͨ��' ");
		} else if ("�������".equalsIgnoreCase(knshjb)) {// ����ѧ��
			sql.append(" and g.shzt3 = 'ͨ��' ");
		}
		// ======================end=============================
		
		sql.append(") t order by num");
		//System.out.println(sql.toString());
		String[] outputValue = new String[] { "num","xmdm", "xmmc", "xh", "xm", "xb",
				"nj", "xymc", "zymc", "bjmc", "mzmc", "zzmmmc","sfzh","csrq", "rxrq", "xmzzjb",
				"xmzzje", "xn", "xq", "nd", "sqsj", "shzt1", "shzt2", "shzt3",
				"knsjb", "sfgc", "sfdq", "lszn", "sfdb", "jthk", "jtdz",
				"jtdh", "jtyb", "jtrs", "jtrjsr", "srly", "yhzzqk", "jtqzqk",
				"jtqtqk", "jsjsp", "wysp", "sbxqxfjd", "xbxqxfjd", "bjpm",
				"zcpm", "kyqk", "hjrq1", "hjmc1", "hjrq2", "hjmc2", "hjrq3",
				"hjmc3", "hjrq4", "hjmc4", "zjqk" };
		ArrayList<String[]> list = dao.rsToVator(sql.toString(), new String[]{}, outputValue);
		
		return list;
	}
	
	/**
	 * ��õ��������б�(�人��ҵ���� ������ѧ�����⵼��)
	 * 
	 * @param xh
	 * @return
	 */
	public ArrayList<String[]> getWhsyGjzxjExpList(XszzTyForm model,
			HashMap<String, String> xmInfo) {

		DAO dao = DAO.getInstance();
		// ��������˼���
		String knshjb = dao.getOneValue("xszz_zzxmb", "shjb", "xmdm",
				XszzXmdm.XSZZ_KNS);
		// ��������������
		String knshzq = dao.getOneValue("xszz_zzxmb", "sqzq", "xmdm",
				XszzXmdm.XSZZ_KNS);
		// ��Ŀ��
		String xmb = xmInfo.get("xmb");
		// ��������
		String sqzq = xmInfo.get("sqzq");
		// ѧ��
		String xn = model.getXn();
		// ѧ��
		String xq = model.getXq();
		// ���
		String nd = model.getNd();
		// �꼶
		String nj = model.getNj();
		// ѧԺ
		String xydm = model.getXydm();
		// רҵ
		String zydm = model.getZydm();
		// �༶
		String bjdm = model.getBjdm();
		// ѧ��
		String xh = model.getXh();
		// ����
		String xm = model.getXm();
		// ��ʼʱ��
		String kssj = model.getKssj();
		// ����ʱ��
		String jssj = model.getJssj();
		// ��Ŀ����
		String xmdm = model.getXmdm();
		// ��Ŀ����
		String xmzzjb = model.getXmzzjb();
		// ���״̬1
		String shzt1 = model.getShzt1();
		// ���״̬2
		String shzt2 = model.getShzt2();
		// ���״̬3
		String shzt3 = model.getShzt3();
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select t.* from (");
		sql.append(" select distinct a.*,rownum num,nvl(g.xmzzjb,'�޼���') knsjb,f.sfgc,f.sfdq,f.lszn,f.sfdb,f.jthk,f.jtdz,");
		sql.append(" f.jtdh,f.jtyb,f.jtrs,f.jtrjsr,f.srly,f.yhzzqk,f.jtqzqk,");
		sql.append(" f.jtqtqk,d.jsjsp,d.wysp,d.sbxqxfjd,d.xbxqxfjd,");
		
		sql.append(" nvl(d.bjpm, 0) || '/' ||(select e.num from (select");
		sql.append(" count(1) num, bjdm from view_xsjbxx group by bjdm) e");
		sql.append(" where a.bjdm = e.bjdm) bjpm,");
		
		sql.append(" nvl(d.zcbjpm, 0) || '/' ||(select e.num from (select");
		sql.append(" count(1) num, bjdm from view_xsjbxx group by bjdm) e");
		sql.append(" where a.bjdm = e.bjdm) zcpm,");
		
		sql.append(" d.kyqk,d.hjrq1,d.hjmc1,d.hjrq2,d.hjmc2,d.hjrq3,");
		sql.append(" d.hjmc3,d.hjrq4,d.hjmc4,d.zjqk from (select a.xmdm,");
		
		sql.append(" a.sqsj,a.shzt1,a.shzt2,a.shzt3,");
		sql.append(" (select c.xmmc from xszz_zzxmb c where a.xmdm = c.xmdm) xmmc,");
		sql.append(" b.xm, a.xh, b.xb, b.nj, b.xydm, b.xymc, b.zydm,a.xn,a.xq,a.nd,b.csrq,b.rxrq,");
		sql.append(" b.zymc, b.bjdm, b.bjmc,b.sfzh, b.mzmc, b.zzmmmc, a.xmzzjb,a.qy,");
		sql.append(" a.xmzzje from ");
		sql.append(xmb);
		sql.append(" a,view_xsxxb b where a.xh = b.xh ");
		
		// ======================��ѯ����=============================
		sql.append(Base.isNull(xmdm) ? "" : " and a.xmdm = '" + xmdm + "' ");
		if ("xn".equalsIgnoreCase(sqzq)) {// ����ѧ��
			sql.append(Base.isNull(xn) ? "" : " and a.xn = '" + xn + "' ");
		} else if ("nd".equalsIgnoreCase(sqzq)) {// �������
			sql.append(Base.isNull(nd) ? "" : " and a.nd = '" + nd + "' ");
		} else if ("xq".equalsIgnoreCase(sqzq)) {// ����ѧ��
			sql.append(Base.isNull(xn) ? "" : " and a.xn = '" + xn + "' ");
			sql.append(Base.isNull(xq) ? "" : " and a.xq = '" + xq + "' ");
		}
		sql.append(Base.isNull(kssj) ? "" : " and a.sqsj >= '" + kssj + "'");
		sql.append(Base.isNull(jssj) ? "" : " and a.sqsj <= '" + jssj + "'");

		sql.append(Base.isNull(xmzzjb) ? "" : " and a.xmzzjb = '" + xmzzjb
				+ "' ");
		sql.append(Base.isNull(shzt1) ? "" : " and a.shzt1 = '" + shzt1 + "' ");
		sql.append(Base.isNull(shzt2) ? "" : " and a.shzt2 = '" + shzt2 + "' ");
		sql.append(Base.isNull(shzt3) ? "" : " and a.shzt3 = '" + shzt3 + "' ");
		
		sql.append(Base.isNull(nj) ? "" : " and b.nj = '" + nj + "' ");
		sql.append(Base.isNull(xydm) ? "" : " and b.xydm = '" + xydm + "' ");
		sql.append(Base.isNull(zydm) ? "" : " and b.zydm = '" + zydm + "' ");
		sql.append(Base.isNull(bjdm) ? "" : " and b.bjdm = '" + bjdm + "' ");
		
		sql.append(Base.isNull(xh) ? "" : " and b.xh like '%" + xh + "%' ");
		sql.append(Base.isNull(xm) ? "" : " and b.xm like '%" + xm + "%' ");
		// ======================end=============================
		sql.append(" ) a ");
		
		sql.append(" left join (select a.* from xsqtxxb a, (select xh, Max(sqsj) sqsj from xsqtxxb group by xh) b ");
		sql.append(" where a.sqsj = b.sqsj and a.xh = b.xh) d on a.xh = d.xh ");
//		sql.append(" left join xsqtxxb d on a.xh = d.xh ");
//		sql.append(" and d.sqsj=(select max(sqsj) from xsqtxxb where xh = '"+xh+"')");
		
		// ===========�������ڱ�����ͬ��������Ŀ��������Ϣ��=========
		/*sql.append(" and a.xn = d.xn ");
		sql.append(" and a.nd = d.nd ");
		sql.append(" and a.xn = d.xn ");
		sql.append(" and a.xq = d.xq ");*/
		// }
		// ======================end=============================
		
		// ======================��ͥ��Ϣȡ���µ�һ��=====================
		sql.append(" left join (select a.* from jtqkdcb a where exists (select 1");
		sql.append(" from (select xh, max(sqsj) sqsj from jtqkdcb group by xh) b");
		sql.append(" where a.xh = b.xh and a.sqsj = b.sqsj)) f on a.xh = f.xh");
		// ======================end=============================

		sql.append(" left join xszz_knsb g on a.xh = g.xh");
		// ===========�������ڱ�����ͬ��������Ŀ��������Ϣ��=========
		sql.append("ѧ��".equalsIgnoreCase(knshzq) ? " and g.xn = a.xn" : "");
		sql.append("���".equalsIgnoreCase(knshzq) ? " and g.nd = a.nd" : "");
		sql.append("ѧ��".equalsIgnoreCase(knshzq) ? " and g.xn = a.xn" : "");
		sql.append("ѧ��".equalsIgnoreCase(knshzq) ? " and g.xq = a.xq" : "");
		
		if ("һ�����".equalsIgnoreCase(knshjb)) {// ����ѧ��
			sql.append(" and g.shzt1 = 'ͨ��' ");
		} else if ("�������".equalsIgnoreCase(knshjb)) {// �������
			sql.append(" and g.shzt2 = 'ͨ��' ");
		} else if ("�������".equalsIgnoreCase(knshjb)) {// ����ѧ��
			sql.append(" and g.shzt3 = 'ͨ��' ");
		}
		// ======================end=============================
		
		sql.append(") t order by num");
		//System.out.println(sql.toString());
		String[] outputValue = new String[] { "num","xmdm", "xmmc", "xh", "xm", "xb",
				"nj", "xymc", "zymc", "bjmc", "mzmc", "zzmmmc","sfzh","csrq", "rxrq", "xmzzjb",
				"xmzzje", "xn", "xq", "nd", "sqsj","qy", "shzt1", "shzt2", "shzt3",
				"knsjb", "sfgc", "sfdq", "lszn", "sfdb", "jthk", "jtdz",
				"jtdh", "jtyb", "jtrs", "jtrjsr", "srly", "yhzzqk", "jtqzqk",
				"jtqtqk", "jsjsp", "wysp", "sbxqxfjd", "xbxqxfjd", "bjpm",
				"zcpm", "kyqk", "hjrq1", "hjmc1", "hjrq2", "hjmc2", "hjrq3",
				"hjmc3", "hjrq4", "hjmc4", "zjqk" };
		ArrayList<String[]> list = dao.rsToVator(sql.toString(), new String[]{}, outputValue);
		
		return list;
	}
	
	/**
	 * ��ȡ����ũҵ��ɫͨ��������Ϣ
	 * @param xh
	 * @return
	 */
	public ArrayList<String[]> getHznyLstdExpList(XszzTyForm model,
			HashMap<String, String> xmInfo) {

		DAO dao = DAO.getInstance();
		// ��������˼���
		String knshjb = dao.getOneValue("xszz_zzxmb", "shjb", "xmdm",
				XszzXmdm.XSZZ_KNS);
		// ��������������
		String knshzq = dao.getOneValue("xszz_zzxmb", "sqzq", "xmdm",
				XszzXmdm.XSZZ_KNS);
		// ��Ŀ��
		String xmb = xmInfo.get("xmb");
		// ��������
		String sqzq = xmInfo.get("sqzq");
		// ѧ��
		String xn = model.getXn();
		// ѧ��
		String xq = model.getXq();
		// ���
		String nd = model.getNd();
		// �꼶
		String nj = model.getNj();
		// ѧԺ
		String xydm = model.getXydm();
		// רҵ
		String zydm = model.getZydm();
		// �༶
		String bjdm = model.getBjdm();
		// ѧ��
		String xh = model.getXh();
		// ����
		String xm = model.getXm();
		// ��ʼʱ��
		String kssj = model.getKssj();
		// ����ʱ��
		String jssj = model.getJssj();
		// ��Ŀ����
		String xmdm = model.getXmdm();
		// ��Ŀ����
		String xmzzjb = model.getXmzzjb();
		// ���״̬1
		String shzt1 = model.getShzt1();
		// ���״̬2
		String shzt2 = model.getShzt2();
		// ���״̬3
		String shzt3 = model.getShzt3();
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select a.* from (");
		sql.append(" select a.*,g.kzzd1,g.kzzd2,g.kzzd3,g.kzzd4,g.bz ");
		sql.append("  from view_xsbfxx a  ");
		sql.append(" ,xszz_comm_zzwsb g where a.xh=g.xh and g.xmdm='"+xmdm+"')a where 1=1 ");
		// ======================��ѯ����=============================
	
		if ("xn".equalsIgnoreCase(sqzq)) {// ����ѧ��
			sql.append(Base.isNull(xn) ? "" : " and a.xn = '" + xn + "' ");
		} else if ("nd".equalsIgnoreCase(sqzq)) {// �������
			sql.append(Base.isNull(nd) ? "" : " and a.nd = '" + nd + "' ");
		} else if ("xq".equalsIgnoreCase(sqzq)) {// ����ѧ��
			sql.append(Base.isNull(xn) ? "" : " and a.xn = '" + xn + "' ");
			sql.append(Base.isNull(xq) ? "" : " and a.xq = '" + xq + "' ");
		}
		sql.append(Base.isNull(kssj) ? "" : " and a.sqsj >= '" + kssj + "'");
		sql.append(Base.isNull(jssj) ? "" : " and a.sqsj <= '" + jssj + "'");

		sql.append(Base.isNull(xmzzjb) ? "" : " and a.xmzzjb = '" + xmzzjb
				+ "' ");
		sql.append(Base.isNull(shzt1) ? "" : " and a.shzt1 = '" + shzt1 + "' ");
		sql.append(Base.isNull(shzt2) ? "" : " and a.shzt2 = '" + shzt2 + "' ");
		sql.append(Base.isNull(shzt3) ? "" : " and a.shzt3 = '" + shzt3 + "' ");
		
		sql.append(Base.isNull(nj) ? "" : " and b.nj = '" + nj + "' ");
		sql.append(Base.isNull(xydm) ? "" : " and b.xydm = '" + xydm + "' ");
		sql.append(Base.isNull(zydm) ? "" : " and b.zydm = '" + zydm + "' ");
		sql.append(Base.isNull(bjdm) ? "" : " and b.bjdm = '" + bjdm + "' ");
		
		sql.append(Base.isNull(xh) ? "" : " and b.xh like '%" + xh + "%' ");
		sql.append(Base.isNull(xm) ? "" : " and b.xm like '%" + xm + "%' ");
		// ======================end=============================
	
	
		// ======================end=============================
		
		String[] outputValue = new String[] {"xymc","zymc","xm","kzzd1",
				"kzzd2","kzzd3","kzzd4","sydsmc","bz" };
		ArrayList<String[]> list = dao.rsToVator(sql.toString(), new String[]{}, outputValue);
		
		return list;
	}
	
	/**
	 * ��ȡ����ʦ����½����Ӣ�������͡�ӭ�����ˡ��ĵ�����Ϣ
	 * @param xh
	 * @return
	 * @author honglin
	 * @date 2013-01-21
	 */
	public ArrayList<String[]> getHzsfLhyyAndYnsrExpList(XszzTyForm model,
			HashMap<String, String> xmInfo) {

		DAO dao = DAO.getInstance();
		// ��������˼���
		String knshjb = dao.getOneValue("xszz_zzxmb", "shjb", "xmdm",
				XszzXmdm.XSZZ_KNS);
		// ��������������
		String knshzq = dao.getOneValue("xszz_zzxmb", "sqzq", "xmdm",
				XszzXmdm.XSZZ_KNS);
		// ��Ŀ��
		String xmb = xmInfo.get("xmb");
		// ��������
		String sqzq = xmInfo.get("sqzq");
		// ѧ��
		String xn = model.getXn();
		// ѧ��
		String xq = model.getXq();
		// ���
		String nd = model.getNd();
		// �꼶
		String nj = model.getNj();
		// ѧԺ
		String xydm = model.getXydm();
		// רҵ
		String zydm = model.getZydm();
		// �༶
		String bjdm = model.getBjdm();
		// ѧ��
		String xh = model.getXh();
		// ����
		String xm = model.getXm();
		// ��ʼʱ��
		String kssj = model.getKssj();
		// ����ʱ��
		String jssj = model.getJssj();
		// ��Ŀ����
		String xmdm = model.getXmdm();
		// ��Ŀ����
		String xmzzjb = model.getXmzzjb();
		// ���״̬1
		String shzt1 = model.getShzt1();
		// ���״̬2
		String shzt2 = model.getShzt2();
		// ���״̬3
		String shzt3 = model.getShzt3();
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select a.*,(select q.hjrq1||'���'||q.hjmc1||'�佱��λ'||q.bjdw1||'��'||q.hjrq2||'���'||q.hjmc2||'�佱��λ'||q.bjdw2||'��'||q.hjrq3||'���'||q.hjmc3||'�佱��λ'||q.bjdw3||'��'||q.hjrq4||'���'||q.hjmc4||'�佱��λ'||q.bjdw4 bz from (select t.* from xsqtxxb t order by t.sqsj desc) q where q.xmdm='5005' and q.xh=a.xh and rownum=1) bz,(select k.xmzzjb from (select n.* from xszz_knsb n order by n.sqsj desc) k where k.xh=a.xh and rownum=1) xmzzjb from (");
		sql.append(" select rownum num,a.* ");
		sql.append("from xszz_comm_zzwsb z ");
		sql.append("left join view_xsbfxx a on z.xh=a.xh where z.xmdm='"+xmdm+"') a where 1=1 ");
		// ======================��ѯ����=============================
	
		if ("xn".equalsIgnoreCase(sqzq)) {// ����ѧ��
			sql.append(Base.isNull(xn) ? "" : " and a.xn = '" + xn + "' ");
		} else if ("nd".equalsIgnoreCase(sqzq)) {// �������
			sql.append(Base.isNull(nd) ? "" : " and a.nd = '" + nd + "' ");
		} else if ("xq".equalsIgnoreCase(sqzq)) {// ����ѧ��
			sql.append(Base.isNull(xn) ? "" : " and a.xn = '" + xn + "' ");
			sql.append(Base.isNull(xq) ? "" : " and a.xq = '" + xq + "' ");
		}
		sql.append(Base.isNull(kssj) ? "" : " and a.sqsj >= '" + kssj + "'");
		sql.append(Base.isNull(jssj) ? "" : " and a.sqsj <= '" + jssj + "'");

		sql.append(Base.isNull(xmzzjb) ? "" : " and a.xmzzjb = '" + xmzzjb
				+ "' ");
		sql.append(Base.isNull(shzt1) ? "" : " and a.shzt1 = '" + shzt1 + "' ");
		sql.append(Base.isNull(shzt2) ? "" : " and a.shzt2 = '" + shzt2 + "' ");
		sql.append(Base.isNull(shzt3) ? "" : " and a.shzt3 = '" + shzt3 + "' ");
		
		sql.append(Base.isNull(nj) ? "" : " and b.nj = '" + nj + "' ");
		sql.append(Base.isNull(xydm) ? "" : " and b.xydm = '" + xydm + "' ");
		sql.append(Base.isNull(zydm) ? "" : " and b.zydm = '" + zydm + "' ");
		sql.append(Base.isNull(bjdm) ? "" : " and b.bjdm = '" + bjdm + "' ");
		
		sql.append(Base.isNull(xh) ? "" : " and b.xh like '%" + xh + "%' ");
		sql.append(Base.isNull(xm) ? "" : " and b.xm like '%" + xm + "%' ");
		// ======================end=============================
	
	
		// ======================end=============================
		
		String[] outputValue = new String[] {"num","xymc","xm","xb","bjmc","bz","xmzzjb" };
		ArrayList<String[]> list = dao.rsToVator(sql.toString(), new String[]{}, outputValue);
		
		return list;
	}
}