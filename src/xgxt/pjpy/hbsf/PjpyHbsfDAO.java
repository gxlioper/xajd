
package xgxt.pjpy.hbsf;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.pjpy.commonutils.PjpyCommonDAO;
import xgxt.pjpy.jgsdx.WhereSqlCommon;
import xgxt.pjpy.tablesdao.PjpyDAO;
import xgxt.pjpy.tablesmodel.PjpyModel;
import xgxt.utils.String.StringUtils;

/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: ����ʦ��ѧԺ��������DAO</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: ����</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-07-10</p>
 */
public class PjpyHbsfDAO extends PjpyDAO {

	DAO dao = DAO.getInstance();// ����ͨ����
	
	ArrayList<String> values = new ArrayList<String>();
	
	@Override
	public boolean insert(PjpyModel pjpyModel) throws Exception {
		//TODO �Զ����ɷ������
		return false;
	}

	@Override
	public boolean delete(String tableName, String pk, String pkValue, HttpServletRequest request) throws Exception {
		// TODO �Զ����ɷ������
		return false;
	}
	
	/**
	 * ��ȡ��ѧ�б�
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getJxjList() throws Exception {
		PjpyCommonDAO commonDao = new PjpyCommonDAO();
		return commonDao.getJxjList();
	}
	
	/**
	 * ͨ��ѧ�Ż�ȡ���������Ϣ(�������Ա����䣬ѧԺ��רҵ���༶��)
	 * @param xh
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getStuInfo(String xh) throws Exception {
		PjpyCommonDAO commonDao = new PjpyCommonDAO();
		return commonDao.getStuInfo(xh);
	}
	
	/**
	 * ͨ����ѧ������ȡ��ѧ�����
	 * @param jxjdm
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getJxjJelb(String jxjdm) throws Exception {
		HashMap<String, String> resMap = new HashMap<String, String>();
		String sql = "select jxjdm,jxjmc,jxjlb,jlje from jxjdmb where jxjdm=?";
		resMap = dao.getMapNotOut(sql, new String[]{jxjdm});
		return resMap;
	}
	
	/**
	 * ��ȡ��ѧ������ѧ�����
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getJxjSqxnnd() throws Exception {
		HashMap<String, String> resMap = new HashMap<String, String>();
		String sql = "select jxjsqxn,jxjsqnd from xtszb where rownum=1";
		resMap = dao.getMapNotOut(sql, new String[]{});
		return resMap;
	}
	
	/**
	 * ��ѧ�����뱣��
	 * @param jxjsqModel
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean jxjsqSave(JxjsqSaveModel jxjsqModel, HttpServletRequest request) throws Exception {
		boolean bFlag = false;
		String xh = jxjsqModel.getXh();
		String xn = jxjsqModel.getXn();
		String nd = jxjsqModel.getNd();
		String jxjdm = jxjsqModel.getJxjdm();
		String drzw = jxjsqModel.getDrzw();
		String sqly = jxjsqModel.getSqly();
		String xxjl = jxjsqModel.getXxjl();
		String kycg = jxjsqModel.getKyxm();
		String sjhm = jxjsqModel.getSjhm();
		String wysp = jxjsqModel.getWysp();
		String jxj1 = jxjsqModel.getJxj1();
		String shyg1 = jxjsqModel.getShyg1();
		String sxddf = jxjsqModel.getSxddf();
		String kxwhf = jxjsqModel.getKxwhf();
		String sxjkf = jxjsqModel.getSxjkf();
		String kcjqf = jxjsqModel.getKcjqf();
		String pm = jxjsqModel.getPm();
		String zhkpzf1 = jxjsqModel.getZhkpzf1();
		String zhkppm = jxjsqModel.getZhkppm();
		String bjghkms = jxjsqModel.getBjghkms();
		String cljz = jxjsqModel.getCljz();
		String tyhgbz1 = jxjsqModel.getTyhgbz1();
		String xq = Base.currXq;
		//Ϊ�����ظ�������ɾ��������
		boolean bDel = StandardOperation.delete("xsjxjb", "xh||xn||jxjdm", xh + xn + jxjdm, request);
		if (bDel) {
			String sql = "insert into xsjxjb (xh,xn,jxjdm,nd,sqsj,sqly,drzw,xxjl,kycg,xq) values (?,?,?,?,to_char(sysdate,'yyyymmddhh24miss'),?,?,?,?,?)";
			bFlag = dao.runUpdate(sql, new String[]{jxjsqModel.getXh(), xn, jxjdm, nd, sqly, drzw, xxjl, kycg, xq});
			if (bFlag) {
				StandardOperation.delete("xsjxjxxb", "xh||xn", xh + xn, request);//Ϊ�����ظ�������ɾ��������
				StandardOperation.insert("xsjxjxxb",
						new String[] { "xh", "xn", "jxj1", "shyg1", "sxddf",
								"kxwhf", "sxjkf", "kcjqf", "pm", "zhkpzf1",
								"zhkppm", "bjghkms", "cljz", "tyhgbz1" },
						new String[] { xh, xn, jxj1, shyg1, sxddf, kxwhf,
								sxjkf, kcjqf, pm, zhkpzf1, zhkppm, bjghkms,
								cljz, tyhgbz1 }, request);//��ѧ��������ϸ��Ϣ����
				sql = "select count(*) num from xsfzxxb where xh=?";
				String tmptmp = dao.getOneRs(sql,new String[]{xh},new String[]{"num"})[0];
				sql = "update xsfzxxb set sjhm=?,wysp=? where xh=?";
				if(!StringUtils.isNull(tmptmp) && tmptmp.equalsIgnoreCase("0")){
					sql = "insert into xsfzxxb(sjhm,wysp,xh) values(?,?,?)";		    
				}
				dao.runUpdate(sql,new String[]{sjhm,wysp,xh});//ѧ��������Ϣ�޸�
			}
		}
		return bFlag;
	}

	/**
	 * �ۺ����ʲ�ѯ��ͷ
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getZhszcpTitle() throws Exception {
		List<HashMap<String, String>> topList = new ArrayList<HashMap<String,String>>();
		String[] enList = new String[]{"xn||xh", "nd", "xn", "xq", "xh", "xm", "bjmc", "dcj", "zcj", "tcj"};
		String[] cnList = new String[]{"xn||xh", "���", "ѧ��" ,"ѧ��","ѧ��","����", "�༶����" ,"˼���������", "��ѧ�Ļ�����", "���Ľ�������"};
		for(int i = 0; i < enList.length; i++) {
			HashMap<String, String> tmpMap = new HashMap<String, String>();
			tmpMap.put("en", enList[i]);
			tmpMap.put("cn", cnList[i]);
			topList.add(tmpMap);
		}
		return topList;
	}
	
	/**
	 * �ۺ����ʲ�ѯ���
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getZhszcpResult(ZhszcpQryModel zhszcpModel) throws Exception {
		List<String[]> resList = new ArrayList<String[]>();
		String sql = "select xn||xh,nd,xn,xq,xh,xm,bjmc,dcj,zcj,tcj from view_zhszcp where 1=1 ";
		String[] opList = new String[]{"xn||xh", "nd", "xn", "xq", "xh", "xm", "bjmc", "dcj", "zcj", "tcj"};
		WhereSqlCommon common = new WhereSqlCommon();
		StringBuffer whereSql = common.getWhereSql2(zhszcpModel);//��ѯ����
		values = common.values;//����ֵ
		resList = dao.rsToVator(sql + whereSql, values != null ? values.toArray(new String[0]) : new String[]{}, opList);
		return resList;
	}
	
	/**
	 * �ۺ����ʲ�����Ϣ����
	 * @param zhszcpModel
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean zhszcpSave(ZhszcpSaveModel zhszcpModel, HttpServletRequest request) throws Exception {
		boolean bFlag = false;
		boolean bDel = StandardOperation.delete("zhszcp", "xh||xn||nd||xq",
				zhszcpModel.getXh() + zhszcpModel.getXn() + zhszcpModel.getNd()
						+ zhszcpModel.getXq(), request);
		if (bDel) {
			bFlag = StandardOperation.insert("zhszcp", new String[] { "xh", "xn", "nd",
					"xq", "dcj", "zcj", "tcj", "zhszcpzf", "zhcppm", "bjghkms",
					"zpf", "sxddbx", "zzllxx", "ssjsqk", "shsjhd", "gbrzbx",
					"qttcsj", "kcjqpfj", "kcjqpfjpm", "zytz", "yyjn", "jsjjn",
					"kxjs", "zyjn", "cxnl", "tydb", "tyhd", "tsqk", "stszzf",
					"xljkhd", "xlszzk", "xlszzf", "cfjz", "cpjg", "bz" },
					new String[] { zhszcpModel.getXh(), zhszcpModel.getXn(),
							zhszcpModel.getNd(), zhszcpModel.getXq(),
							zhszcpModel.getDcj(), zhszcpModel.getZcj(),
							zhszcpModel.getTcj(), zhszcpModel.getZhszcpzf(),
							zhszcpModel.getZhcppm(), zhszcpModel.getBjghkms(),
							zhszcpModel.getZpf(), zhszcpModel.getSxddbx(),
							zhszcpModel.getZzllxx(), zhszcpModel.getSsjsqk(),
							zhszcpModel.getShsjhd(), zhszcpModel.getGbrzbx(),
							zhszcpModel.getQttcsj(), zhszcpModel.getKcjqpfj(),
							zhszcpModel.getKcjqpfjpm(), zhszcpModel.getZytz(),
							zhszcpModel.getYyjn(), zhszcpModel.getJsjjn(),
							zhszcpModel.getKxjs(), zhszcpModel.getZyjn(),
							zhszcpModel.getCxnl(), zhszcpModel.getTydb(),
							zhszcpModel.getTyhd(), zhszcpModel.getTsqk(),
							zhszcpModel.getStszzf(), zhszcpModel.getXljkhd(),
							zhszcpModel.getXlszzk(), zhszcpModel.getXlszzf(),
							DealString.toGBK(zhszcpModel.getCfjz()), DealString.toGBK(zhszcpModel.getCpjg()),
							DealString.toGBK(zhszcpModel.getBz()) }, request);
		}
		return bFlag;
	}
	
	/**
	 * �ۺ����ʲ�������ɾ��
	 * @param keys
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String zhszcpDel(String[] keys, HttpServletRequest request) throws Exception {
		int del = 0;//ɾ����¼��
		for (int i = 0; i < keys.length; i++) {
			String whichpk = keys[i];// �õ�����
			boolean bFlag = StandardOperation.delete("zhszcp", "xn||xh", whichpk, request);
			if (bFlag){//ɾ���ɹ�
				del++;
			}
		}
		return String.format("%1$s ����¼�ɹ�ɾ��!", del);
	}
	
	/**
	 * ͨ��������ȡ�ۺ�������ϸ��Ϣ
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getZhszcpInfoByPk(String pkValue) throws Exception {
		HashMap<String, String> resMap = new HashMap<String, String>();
		String sql = "select  xh,xm,nj,xb,xymc,bjmc,zymc, xn, nd,xq, dcj, zcj, tcj, zhszcpzf, zhcppm, bjghkms,zpf, sxddbx, zzllxx, ssjsqk, shsjhd, gbrzbx,qttcsj, kcjqpfj, kcjqpfjpm, zytz, yyjn, jsjjn,kxjs, zyjn, cxnl, tydb, tyhd, tsqk, stszzf,xljkhd, xlszzk, xlszzf, cfjz, cpjg, bz from view_zhszcp where xn||xh = ?";
		resMap = dao.getMapNotOut(sql, new String[]{pkValue});
		return resMap;
	}
	
	/**
	 * ͨ���༶��ȡ�ۺ�������Ϣ
	 * @param xydm
	 * @param zydm
	 * @param bjdm
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getZhszcpInfoByBj(String xydm, String zydm, String bjdm, String xn) throws Exception {
		List<HashMap<String, String>> resList = new ArrayList<HashMap<String,String>>();
		String sql = "select xh,xm,zpf,sxddbx,zzllxx,ssjsqk,shsjhd,gbrzbx,qttcsj,dcj,kcjqpfj,kcjqpfjpm,zytz,yyjn,jsjjn,zyjn,kxjs,cxnl,zcj,tydb,tyhd,tsqk,stszzf,xljkhd,xlszzk,xlszzf,tcj,zhszcpzf,zhcppm,bjghkms,cfjz,cpjg,xymc,zymc,bjmc,xn from view_zhszcp where 1=1 ";
		StringBuffer whereSql = new StringBuffer();
		ArrayList<String> values = new ArrayList<String>();
		if (!StringUtils.isNull(xydm)) {
			whereSql.append(" and xydm = ?");
			values.add(xydm);
		}
		if (!StringUtils.isNull(zydm)) {
			whereSql.append(" and zydm = ?");
			values.add(zydm);
		}
		if (!StringUtils.isNull(bjdm)) {
			whereSql.append(" and bjdm = ?");
			values.add(bjdm);
		}
		if (!StringUtils.isNull(xn)) {
			whereSql.append(" and xn = ?");
			values.add(xn);
		}
		resList = dao.getList(sql + whereSql, values != null ? values.toArray(new String[0]) : new String[]{}, new String[]{"xh", "xm", "zpf","sxddbx", "zzllxx","ssjsqk","shsjhd","gbrzbx","qttcsj","dcj","kcjqpfj","kcjqpfjpm","zytz","yyjn","jsjjn","zyjn","kxjs","cxnl","zcj","tydb","tyhd","tsqk","stszzf","xljkhd","xlszzk","xlszzf","tcj","zhszcpzf","zhcppm","bjghkms","cfjz","cpjg","xymc","zymc","bjmc","xn"});
		return resList;
	}
}
