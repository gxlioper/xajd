package xgxt.pjpy.nbzy.zhszcp;

import java.util.*;

import javax.servlet.http.HttpServletRequest;

import xgxt.utils.String.StringUtils;

public class ZhszcpService {

	private ZhszcpDAO dao = null;
	
	public String getRealTable(String szlx){
		szlx = StringUtils.isNull(szlx) ? "0" : szlx.trim();
		if ("0".equalsIgnoreCase(szlx)) {
			return "pjpy_zysycpb";
		} else if ("1".equalsIgnoreCase(szlx)) {
			return "pjpy_zyjnsycpb";
		} else if ("2".equalsIgnoreCase(szlx)) {
			return "pjpy_kcxfzsycpb";
		} else {
			return "nbzy_zhszcp";
		}
	} 
	
	public List<HashMap<String, String>> title (String szlx) throws Exception {
		dao = new ZhszcpDAO();
		szlx = StringUtils.isNull(szlx) ? "0" : szlx.trim();
		String[] enList = new String[]{"pk", "xn", "xq", "xm", "bjmc", "fs", "zt", "zf"};
		String[] cnList = null;
		if ("0".equalsIgnoreCase(szlx)) {
			cnList = new String[]{"pk", "ѧ��", "ѧ��", "����", "�༶", "����", "״̬", "ְҵ�����ܷ�(������80)"};
		} else if ("1".equalsIgnoreCase(szlx)) {
			cnList = new String[]{"pk", "ѧ��", "ѧ��", "����", "�༶", "����", "״̬", "ְҵ���������ܷ�(������60)"};
		} else if ("2".equalsIgnoreCase(szlx)) {
			cnList = new String[]{"pk", "ѧ��", "ѧ��", "����", "�༶", "����", "״̬", "�ɳ�����չ�����ܷ�"};
		} else {
			enList = new String[]{"pk", "xn", "xm", "bjmc", "zyszf", "zyjnf", "kcxfaf", "zhcpzf", "bjpm"};
			cnList = new String[]{"pk", "ѧ��", "����", "�༶", "ְҵ������", "ְҵ���ܷ�", "�ɳ�����չ��", "�ۺϲ����ܷ�", "�༶����"};
		}
		return dao.title(enList, cnList);
	}
	
	public List<String[]> result(ZhszcpModel model, String szlx) throws Exception {
		List<String[]> rsList = new ArrayList<String[]>();
		dao = new ZhszcpDAO();
		szlx = StringUtils.isNull(szlx) ? "0" : szlx.trim();
		if ("0".equalsIgnoreCase(szlx)) {
			rsList = dao.zyResult(model);
		} else if ("1".equalsIgnoreCase(szlx)) {
			rsList = dao.zyjnResult(model);
		} else if ("2".equalsIgnoreCase(szlx)) {
			rsList = dao.kcxfzResult(model);
		} else {
			rsList = dao.zhszResult(model);
		}
		return rsList;
	}
	
	public HashMap<String, String> getStuInfo(String xh) throws Exception {
		dao = new ZhszcpDAO();
		return dao.getStuInfo(xh);
	}
	
	public String getTitle(String act) {
		if (!StringUtils.isNull(act)) {
			if ("0".equalsIgnoreCase(act)) {
				return "ְҵ����������";
			} else if ("1".equalsIgnoreCase(act)) {
				return "ְҵ��������������";
			} else if ("2".equalsIgnoreCase(act)) {
				return "�ɳ�����չ����������";
			} else {
				return "";
			}
		} else {
			return "";
		}
	}
	
	public String getTname(String act) {
		if (!StringUtils.isNull(act)) {
			if ("0".equalsIgnoreCase(act)) {
				return "pjpy_zysycpb";
			} else if ("1".equalsIgnoreCase(act)) {
				return "pjpy_zyjnsycpb";
			} else if ("2".equalsIgnoreCase(act)) {
				return "pjpy_kcxfzsycpb";
			} else {
				return "";
			}
		} else {
			return "";
		}
	}
	
	public String getVname(String act) {
		if (!StringUtils.isNull(act)) {
			if ("0".equalsIgnoreCase(act)) {
				return "view_pjpy_zysycpb";
			} else if ("1".equalsIgnoreCase(act)) {
				return "view_pjpy_zyjnsycpb";
			} else if ("2".equalsIgnoreCase(act)) {
				return "view_pjpy_kcxfzsycpb";
			} else {
				return "";
			}
		} else {
			return "";
		}
	}
	
	public String szfSave(ZhszcpModel model, String act) throws Exception {
		String tname = getTname(act);
		dao = new ZhszcpDAO();
		return dao.zyszfSave(model, tname);
	}
	
	public HashMap<String, String> viewSzf(String pkValue, String act) throws Exception {
		HashMap<String, String> rs = new HashMap<String, String>();
		String tname = getVname(act);
		dao = new ZhszcpDAO();
		rs = dao.viewSzf(pkValue, tname);
		return rs;
	}
	
	public boolean szfModisave(String pkValue, String act, ZhszcpModel model) throws Exception {
		dao = new ZhszcpDAO();
		String tname = getTname(act);
		return dao.szfModisave(pkValue, tname, model);
	}
	
	public String zyfDel(String[] keys, String act, HttpServletRequest request) throws Exception {
		dao = new ZhszcpDAO();
		String tname = getTname(act);
		return dao.szfDel(tname, keys, request);
	}
	
	public boolean zhszcpCount(String xn, String xydm,String zydm, String bjdm, String nj) throws Exception {
		dao = new ZhszcpDAO();
		return dao.zhszcpCount(xn, xydm,zydm,bjdm,nj);
	}
}
