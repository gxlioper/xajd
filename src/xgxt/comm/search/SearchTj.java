package xgxt.comm.search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CnToEnUtil;
import xgxt.comm.xml.XMLReader;
import xgxt.form.RequestForm;
import xgxt.form.User;

public class SearchTj {

	/**
	 * ����ͨ����������
	 * 
	 * @param request
	 * @author ΰ�����
	 * 
	 */
	public void setCommListList(SearchForm searchForm, RequestForm rForm,
			User user, String[] PY_BIG, HttpServletRequest request)
			throws Exception {

		DAO dao = DAO.getInstance();

		// ������ò
		String sql = "select distinct zzmmdm dm,zzmmmc mc from zzmmdmb where zzmmmc is not null order by zzmmdm";
		List<HashMap<String, String>> zzmmList = dao.getList(sql,
				new String[] {}, new String[] { "dm", "mc" });

		request.setAttribute("zzmmList", zzmmList);

		// ��������
		List<HashMap<String, Object>> commBmList = setNewBmListBy(PY_BIG);

		request.setAttribute("commBmList", commBmList);

		// ���״̬
		List<HashMap<String, String>> shztList = getCommSelectList("shzt");
		
		request.setAttribute("shztList", shztList);
		
		// ��˽��
		List<HashMap<String, String>> shjgList = getCommSelectList("shjg");
		
		request.setAttribute("shjgList", shjgList);
		
		
		// �Ƿ��б�
		List<HashMap<String, String>> sfList = getCommSelectList("sf");

		request.setAttribute("sfList", sfList);
	}

	/**
	 * ����ѧ����Ϣ��������
	 * 
	 * @param request
	 * @author ΰ�����
	 * 
	 */
	public void setXsxxListList(SearchForm searchForm, RequestForm rForm,
			User user, HttpServletRequest request) throws Exception {

		DAO dao = DAO.getInstance();
		// ѧ������
		String sql = "select distinct t.xz dm,t.xz mc from view_xsbfxx t where t.xz is not null order by t.xz";
		List<HashMap<String, String>> xzList = dao.getList(sql,
				new String[] {}, new String[] { "dm", "mc" });

		request.setAttribute("xsxxXzList", xzList);

		// ѧ��״̬
		//sql = "select distinct zxdmmc dm,zxdmmc mc from dm_zju_xjzt order by zxdmmc";
		sql = "select distinct xjztm dm,xjztm mc from view_xsbfxx order by xjztm";
		List<HashMap<String, String>> xjList = dao.getList(sql,
				new String[] {}, new String[] { "dm", "mc" });

		request.setAttribute("xsxxXjList", xjList);

		// �Ƿ���У
		List<HashMap<String, String>> sfzxList = getXsxxSelectList("sfzx");
		request.setAttribute("xsxxSfzxList", sfzxList);

		// ����
		sql = "select distinct mzdm dm,mzmc mc from mzdmb where mzdm <> '-8' order by mzdm";
		List<HashMap<String, String>> mzList = dao.getList(sql,
				new String[] {}, new String[] { "dm", "mc" });
		request.setAttribute("xsxxMzList", mzList);

		// �Ƿ�ɷ�
		List<HashMap<String, String>> sfjfList = getXsxxSelectList("sfjf");
		request.setAttribute("xsxxSfjfList", sfjfList);

		// ����״̬
		sql = "select distinct hkztdm dm,hkztmc mc from hkztdmb order by hkztdm";
		List<HashMap<String, String>> hkztList = dao.getList(sql,
				new String[] {}, new String[] { "dm", "mc" });
		request.setAttribute("xsxxHkztList", hkztList);

		// ת�����
		List<HashMap<String, String>> zdlbList = getXsxxSelectList("zdlb");
		request.setAttribute("xsxxZdlbList", zdlbList);

		// �춯���
		sql = "select distinct ydlbm dm,ydlbmc mc from dm_ydlb order by ydlbm";
		List<HashMap<String, String>> ydlbList = dao.getList(sql,
				new String[] {}, new String[] { "dm", "mc" });
		request.setAttribute("xsxxYdlbList", ydlbList);

		// ��Դʡ
		sql = "select distinct sydqdm dm,sydq mc from dmk_sydq order by sydqdm";
		List<HashMap<String, String>> provList = dao.getList(sql,
				new String[] {}, new String[] { "dm", "mc" });
		request.setAttribute("xsxxProvList", provList);

		// �������
		sql = "select distinct pyccdm dm,pyccmc mc from xg_xsxx_pyccdmb order by pyccdm";
		List<HashMap<String, String>> pyccList = dao.getList(sql,
				new String[] {}, new String[] { "dm", "mc" });
		request.setAttribute("xsxxPyccList", pyccList);
		
		// ѧ�����
		sql = "select distinct xjlbdm dm,xjlbmc mc from dm_xjlb order by xjlbdm";
		List<HashMap<String, String>> xjlbList = dao.getList(sql,
				new String[] {}, new String[] { "dm", "mc" });
		request.setAttribute("xjlbList", xjlbList);
		

		// ѧ�����
		sql = "select distinct xjlb dm,xjlb mc from view_xsxxb  where xjlb is not null order by xjlb";
		List<HashMap<String, String>> xjlbmcList = dao.getList(sql,
				new String[] {}, new String[] { "dm", "mc" });
		request.setAttribute("xjlbmcList", xjlbmcList);
		
	}

	/**
	 * �����ճ�������������
	 * 
	 * @param request
	 * @author ΰ�����
	 * 
	 */
	public void setRcswListList(SearchForm searchForm, RequestForm rForm,
			User user, HttpServletRequest request) throws Exception {

		// �Ƿ���֪ͨ
		List<HashMap<String, String>> sftzList = getRcswSelectList("sftz");

		request.setAttribute("rcswSftzList", sftzList);
	}

	/**
	 * ���ù�Ԣ������������
	 * 
	 * @param request
	 * @author ΰ�����
	 * 
	 */
	public void setGyglListList(SearchForm searchForm, RequestForm rForm,
			User user, String[] PY_BIG, HttpServletRequest request)
			throws Exception {

		DAO dao = DAO.getInstance();

		// ��Ԣ����ԱȨ��
		String gyglyQx = user.getGyglyQx();
		// �û���
		String userName = user.getUserName();
		
		// У������
		String sql = "select distinct t.dm dm,t.xqmc mc from dm_zju_xq t ";
		if ("yes".equalsIgnoreCase(gyglyQx)) {
			sql += "where exists(select 1 from sslddmb a,xg_gygl_ldglb b ";
			sql += "where t.dm = a.xqdm and a.lddm =b.lddm and b.yhm='"+userName+"') ";
		}
		sql+=" order by t.dm";
		List<HashMap<String, String>> xqList = dao.getList(sql,
				new String[] {}, new String[] { "dm", "mc" });

		request.setAttribute("gyglXqList", xqList);

		// ԰������
		sql = "select distinct t.yqdm dm,t.yqmc mc from yqdmb t ";
		if ("yes".equalsIgnoreCase(gyglyQx)) {
			sql += "where exists(select 1 from sslddmb a,xg_gygl_ldglb b ";
			sql += "where t.yqdm = a.yqdm and a.lddm =b.lddm and b.yhm='"+userName+"') ";
		}
		sql+=" order by t.yqdm";
		List<HashMap<String, String>> yqList = dao.getList(sql,
				new String[] {}, new String[] { "dm", "mc" });

		request.setAttribute("gyglYqList", yqList);

		// ¥������
		sql = "select distinct t.lddm dm,t.ldmc mc from sslddmb t ";
		if ("yes".equalsIgnoreCase(gyglyQx)) {
			sql += "where exists(select 1 from xg_gygl_ldglb a ";
			sql += "where t.lddm = a.lddm and a.yhm='"+userName+"') ";
		}
		sql+=" order by t.lddm";
		List<HashMap<String, String>> ldList = dao.getList(sql,
				new String[] {}, new String[] { "dm", "mc" });

		request.setAttribute("gyglLdList", ldList);

		// ��������
		sql = "select distinct t.cs dm,t.cs mc from sslddmb t ";
		if ("yes".equalsIgnoreCase(gyglyQx)) {
			sql += "where exists(select 1 from xg_gygl_ldglb a ";
			sql += "where t.lddm = a.lddm and a.yhm='"+userName+"') ";
		}
		sql+=" order by to_number(cs)";
		
		List<HashMap<String, String>> csList = dao.getList(sql,
				new String[] {}, new String[] { "dm", "mc" });

		request.setAttribute("gyglCsList", csList);
		
		// ��������
		List<HashMap<String, Object>> gyglQshList = setNewQsListBy(user,PY_BIG);
		
		request.setAttribute("gyglQshList", gyglQshList);
		
		// �ɷ��ס
		List<HashMap<String, String>> gyglKfhzList = getGyglSelectList("kfhz");

		request.setAttribute("gyglKfhzList", gyglKfhzList);
		
		// ��ס״̬
		List<HashMap<String, String>> gyglRzztList = getGyglSelectList("rzzt");
		
		request.setAttribute("gyglRzztList", gyglRzztList);
		
		// ��λ����״̬
		List<HashMap<String, String>> gyglCwfpList = getGyglSelectList("cwfp");
		
		request.setAttribute("gyglCwfpList", gyglCwfpList);
		
		// �Ա��޶�
		List<HashMap<String, String>> gyglXbxdList = getGyglSelectList("xbxd");

		request.setAttribute("gyglXbxdList", gyglXbxdList);

		// ��ס���
		List<HashMap<String, String>> gyglRzqkList = getGyglSelectList("rzzt");
		
		request.setAttribute("gyglRzqkList", gyglRzqkList);
	}

	/**
	 * ��������������������
	 * 
	 * @param request
	 * @author ΰ�����
	 * 
	 */
	public void setPjpyListList(SearchForm searchForm, RequestForm rForm,
			User user, HttpServletRequest request) throws Exception {
		// �Ա��޶�
		List<HashMap<String, String>> pjpySfqrList = getPjpySelectList("sfqr");

		request.setAttribute("pjpySfqrList", pjpySfqrList);

		// ��ס���
		List<HashMap<String, String>> pjpySfpfList = getPjpySelectList("sfpf");
		
		request.setAttribute("pjpySfpfList", pjpySfpfList);
	}
	
	/**
	 * ���������б���ƴ����
	 * 
	 * @param request
	 * @author ΰ�����
	 * 
	 */
	private List<HashMap<String, Object>> setNewQsListBy(User user,
			String[] PY_BIG) {
		List<HashMap<String, Object>> gyglQshList = new ArrayList<HashMap<String, Object>>();

		DAO dao = DAO.getInstance();

		// ��Ԣ����ԱȨ��
		String gyglyQx = user.getGyglyQx();
		// �û���
		String userName = user.getUserName();

		// ��������
		String sql = "select distinct t.qsh from ssxxb t ";
		if ("yes".equalsIgnoreCase(gyglyQx)) {
			sql += "where exists(select 1 from xg_gygl_ldglb a ";
			sql += "where t.lddm = a.lddm and a.yhm='" + userName + "') ";
		}
		sql += "order by qsh";

		List<HashMap<String, String>> qshList = dao.getList(sql,
				new String[] {}, new String[] { "qsh" });

		for (int i = 0; i < PY_BIG.length; i++) {

			String py = PY_BIG[i];

			List<HashMap<String, String>> qsList = new ArrayList<HashMap<String, String>>();

			for (int j = 0; j < qshList.size(); j++) {

				HashMap<String, String> map = qshList.get(j);

				String qsh = map.get("qsh");
				String qshpy = CnToEnUtil.getFirstLetter(qsh);

				if (py.equalsIgnoreCase(qshpy)) {

					HashMap<String, String> newQsh = new HashMap<String, String>();

					if (qsList.size() < 3) {

						newQsh.put("qshdm", qsh);
						newQsh.put("qshmc", qsh);

						qsList.add(newQsh);

					} else {
						newQsh.put("qshdm", "qsh_" + qshpy);
						newQsh.put("qshmc", "����");

						qsList.add(newQsh);

						break;
					}

				}
			}

			HashMap<String, Object> qsMap = new HashMap<String, Object>();
			qsMap.put("py", py);
			qsMap.put("qsList", qsList);

			gyglQshList.add(qsMap);
		}
		return gyglQshList;
	}
	
	/**
	 * ���ò����б���ƴ����
	 * 
	 * @param request
	 * @author ΰ�����
	 * 
	 */
	public List<HashMap<String, Object>> setNewBmListBy(String[] PY_BIG) {
		
		List<HashMap<String, Object>> commBmList = new ArrayList<HashMap<String, Object>>();

		DAO dao = DAO.getInstance();
		// ��������
		String sql = "select distinct bmdm,bmmc from zxbz_xxbmdm where bmmc is not null order by bmdm";
		
		List<HashMap<String, String>> bmList = dao.getList(sql,
				new String[] {}, new String[] { "bmdm","bmmc" });

		for (int i = 0; i < PY_BIG.length; i++) {

			String py = PY_BIG[i];

			List<HashMap<String, String>> bmPyList = new ArrayList<HashMap<String, String>>();

			for (int j = 0; j < bmList.size(); j++) {

				HashMap<String, String> map = bmList.get(j);

				String bmdm = map.get("bmdm");
				String bmmc = map.get("bmmc");
				String bmpy = CnToEnUtil.getFirstLetter(bmmc);

				if (py.equalsIgnoreCase(bmpy)) {

					HashMap<String, String> newBm = new HashMap<String, String>();

					newBm.put("bmdm", bmdm);
					newBm.put("bmmc", bmmc);

					bmPyList.add(newBm);

				}
			}

			HashMap<String, Object> bmMap = new HashMap<String, Object>();
			bmMap.put("py", py);
			bmMap.put("bmList", bmPyList);

			commBmList.add(bmMap);
		}
		return commBmList;
	}
	
	/**
	 * �������ά��������ֵ��ͨ�ã�
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getCommSelectList(String lx) {

		DAO dao = DAO.getInstance();

		String[] dm = null;
		String[] mc = null;

		if ("shzt".equalsIgnoreCase(lx)) {// ���״̬
			dm = new String[] { "δ���", "ͨ��", "��ͨ��", "�˻�", "������" };
			mc = new String[] { "δ���", "ͨ��", "��ͨ��", "�˻�", "������" };
		} else if ("sf".equalsIgnoreCase(lx)) {// �Ƿ�
			dm = new String[] { "��", "��" };
			mc = new String[] { "��", "��" };
		} else if("shjg".equalsIgnoreCase(lx)){//��˽��
			dm = new String[] { "δ���", "�����", "ͨ��", "��ͨ��", "�˻�" };
			mc = new String[] { "δ���", "�����", "ͨ��", "��ͨ��", "�˻�" };
		}

		return dao.arrayToList(dm, mc);
	}
	
	/**
	 * �������ά��������ֵ��ѧ����Ϣ��
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getXsxxSelectList(String lx) {

		DAO dao = DAO.getInstance();

		String[] dm = null;
		String[] mc = null;

		if ("sfzx".equalsIgnoreCase(lx)) {// �Ƿ���У
			dm = new String[] { "��У", "����У" };
			mc = new String[] { "��У", "����У" };
		} else if ("sfjf".equalsIgnoreCase(lx)) {// �Ƿ�ɷ�
			dm = new String[] { "�ѽ�", "δ��" };
			mc = new String[] { "�ѽ�", "δ��" };
		} else if ("zdlb".equalsIgnoreCase(lx)) {// ת�����
			dm = new String[] { "��ҵ", "��ѧ", "תѧ", "��ѧ" };
			mc = new String[] { "��ҵ", "��ѧ", "תѧ", "��ѧ" };
		}

		return dao.arrayToList(dm, mc);
	}
	
	/**
	 * �������ά��������ֵ���ճ�����
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getRcswSelectList(String lx) {

		DAO dao = DAO.getInstance();

		String[] dm = null;
		String[] mc = null;

		if ("sftz".equalsIgnoreCase(lx)) {// �Ƿ�֪ͨ
			dm = new String[] { "��֪ͨ", "δ֪ͨ" };
			mc = new String[] { "��֪ͨ", "δ֪ͨ" };
		}

		return dao.arrayToList(dm, mc);
	}
	
	/**
	 * �������ά��������ֵ����Ԣ����
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getGyglSelectList(String lx) {

		DAO dao = DAO.getInstance();

		String[] dm = null;
		String[] mc = null;

		if ("kfhz".equalsIgnoreCase(lx)) {// �ɷ��ס
			dm = new String[] { "����", "����" };
			mc = new String[] { "����", "����" };
		} else if ("rzzt".equalsIgnoreCase(lx)) {// ��ס״̬
			dm = new String[] { "��Ա", "ȱ��", "ȫ��" };
			mc = new String[] { "��Ա", "ȱ��", "ȫ��" };
		} else if ("cwfp".equalsIgnoreCase(lx)) {// ��λ����״̬
			dm = new String[] { "����ס", "�ɷ���" };
			mc = new String[] { "����ס", "�ɷ���" };
		} else if ("xbxd".equalsIgnoreCase(lx)) {// �Ա��޶�
			dm = new String[] { "��", "Ů","���" };
			mc = new String[] { "��", "Ů","���" };
		} 

		return dao.arrayToList(dm, mc);
	}
	
	/**
	 * �������ά��������ֵ���������ţ�
	 * 
	 * @author qlj
	 */
	public List<HashMap<String, String>> getPjpySelectList(String lx) {

		DAO dao = DAO.getInstance();

		String[] dm = null;
		String[] mc = null;

		if ("sfpf".equalsIgnoreCase(lx)) {// �Ƿ�����
			dm = new String[] { "��", "��", "��������" };
			mc = new String[] { "��", "��", "��������"  };
		} else if ("sfqr".equalsIgnoreCase(lx)) {// �Ƿ�ȷ��
			dm = new String[] { "��", "��", "�˻�" };
			mc = new String[] { "��", "��", "�˻�" };
		} 
		
		return dao.arrayToList(dm, mc);
	}
	
	
	/********************************************************************************************************************************/
	/**************************************************��Ԣ���������棩�߼���ѯ����**************************************************/
	/********************************************************************************************************************************/
	/**
	 * ���ù�Ԣ������������
	 * @author zhangh	  
	 */
	public void setGyglListList_Third(SearchForm searchForm, RequestForm rForm,
			User user, String[] PY_BIG, HttpServletRequest request)
			throws Exception {

		DAO dao = DAO.getInstance();

		// ��Ԣ����ԱȨ��
		String gyglyQx = user.getGyglyQx();
		// �û���
		String userName = user.getUserName();
		
		String version = XMLReader.getFlowControl("gygl", "gyversion");
		request.setAttribute("gyversion", version);
		
		// У������_δ�޸�
		String sql = "select distinct t.dm dm,t.xqmc mc from dm_zju_xq t ";
		if ("yes".equalsIgnoreCase(gyglyQx)) {
			sql += "where exists(select 1 from sslddmb a,xg_gygl_ldglb b ";
			sql += "where t.dm = a.xqdm and a.lddm =b.lddm and b.yhm='"+userName+"') ";
		}
		sql+=" order by t.dm";
		List<HashMap<String, String>> xqList = dao.getList(sql,
				new String[] {}, new String[] { "dm", "mc" });

		request.setAttribute("gyglXqList", xqList);

		// ԰������_δ�޸�
		sql = "select distinct t.yqdm dm,t.yqmc mc from yqdmb t ";
		if ("yes".equalsIgnoreCase(gyglyQx)) {
			sql += "where exists(select 1 from sslddmb a,xg_gygl_ldglb b ";
			sql += "where t.yqdm = a.yqdm and a.lddm =b.lddm and b.yhm='"+userName+"') ";
		}
		sql+=" order by t.yqdm";
		List<HashMap<String, String>> yqList = dao.getList(sql,
				new String[] {}, new String[] { "dm", "mc" });

		request.setAttribute("gyglYqList", yqList);

		// ¥������_���޸�
		sql = "select distinct t.lddm dm,t.ldmc mc from xg_gygl_new_ldxxb t ";
		if ("yes".equalsIgnoreCase(gyglyQx)) {
			sql += "where exists(select 1 from xg_gygl_ldglb a ";
			sql += "where t.lddm = a.lddm and a.yhm='"+userName+"') ";
		}
		sql+=" order by t.lddm";
		List<HashMap<String, String>> ldList = dao.getList(sql,
				new String[] {}, new String[] { "dm", "mc" });

		request.setAttribute("gyglLdList", ldList);

		//�������_���޸�
		sql = "select distinct t.ch dm,t.chmc mc from view_xg_gygl_new_cwxx t ";
		if ("yes".equalsIgnoreCase(gyglyQx)) {
			sql += "where exists(select 1 from xg_gygl_ldglb a ";
			sql += "where t.lddm = a.lddm and a.yhm='"+userName+"') ";
		}
		sql+=" order by to_number(ch)";
		
		List<HashMap<String, String>> chList = dao.getList(sql,
				new String[] {}, new String[] { "dm", "mc" });

		request.setAttribute("gyglChList", chList);
		
		// ��������_���޸�
		sql = "select distinct t.ldcs dm,t.ldcs mc from xg_gygl_new_ldxxb t ";
		if ("yes".equalsIgnoreCase(gyglyQx)) {
			sql += "where exists(select 1 from xg_gygl_ldglb a ";
			sql += "where t.lddm = a.lddm and a.yhm='"+userName+"') ";
		}
		sql+=" order by to_number(ldcs)";
		
		List<HashMap<String, String>> csList = dao.getList(sql,
				new String[] {}, new String[] { "dm", "mc" });

		request.setAttribute("gyglCsList", csList);
		
		// ��������_���޸�
		List<HashMap<String, Object>> gyglQshList = setNewQsList_Third(user,PY_BIG);
		
		request.setAttribute("gyglQshList", gyglQshList);
		
		// �ɷ��ס
		List<HashMap<String, String>> gyglKfhzList = getGyglSelectList("kfhz");

		request.setAttribute("gyglKfhzList", gyglKfhzList);
		
		// ��ס״̬
		List<HashMap<String, String>> gyglRzztList = getGyglSelectList("rzzt");
		
		request.setAttribute("gyglRzztList", gyglRzztList);
		
		// ��λ����״̬
		List<HashMap<String, String>> gyglCwfpList = getGyglSelectList("cwfp");
		
		request.setAttribute("gyglCwfpList", gyglCwfpList);
		
		// �Ա��޶�
		List<HashMap<String, String>> gyglXbxdList = getGyglSelectList("xbxd");

		request.setAttribute("gyglXbxdList", gyglXbxdList);

		// ��ס���
		List<HashMap<String, String>> gyglRzqkList = getGyglSelectList("rzzt");
		
		request.setAttribute("gyglRzqkList", gyglRzqkList);
		
		// ����ԭ��
		sql = "select distinct tsyydm,tsyymc dm,tsyymc mc from xg_gygl_new_tsyydmb order by to_number(tsyydm)";
		List<HashMap<String, String>> tsyyList = dao.getList(sql,new String[] {}, new String[] { "dm", "mc" });
		
		request.setAttribute("gyglTsyyList", tsyyList);
		
		//�Ƿ���ס
		List<HashMap<String, String>> gyglSfrzList = getGyglSelectList("sfrz");
		
		request.setAttribute("gyglSfrzList", gyglSfrzList);
		
		//�Ƿ����
		List<HashMap<String, String>> gyglSffpList = getGyglSelectList("sffp");
		
		request.setAttribute("gyglSffpList", gyglSffpList);
		
	}
	
	/**
	 * ���������б���ƴ����
	 * 
	 * @param request
	 * @author ΰ�����
	 * 
	 */
	private List<HashMap<String, Object>> setNewQsList_Third(User user,String[] PY_BIG) {
		List<HashMap<String, Object>> gyglQshList = new ArrayList<HashMap<String, Object>>();

		DAO dao = DAO.getInstance();
		
//		 ��Ԣ����ԱȨ��
		String gyglyQx = user.getGyglyQx();
		// �û���
		String userName = user.getUserName();
		
		// ��������
		String sql = "select qsh from view_xg_gygl_new_qsxx t ";
		if ("yes".equalsIgnoreCase(gyglyQx)) {
			sql += "where exists(select 1 from xg_gygl_ldglb a ";
			sql += "where t.lddm = a.lddm and a.yhm='"+userName+"') ";
		}
		sql += "order by qsh";
		
		List<HashMap<String, String>> qshList = dao.getList(sql,
				new String[] {}, new String[] { "qsh" });

		for (int i = 0; i < PY_BIG.length; i++) {

			String py = PY_BIG[i];

			List<HashMap<String, String>> qsList = new ArrayList<HashMap<String, String>>();

			for (int j = 0; j < qshList.size(); j++) {

				HashMap<String, String> map = qshList.get(j);

				String qsh = map.get("qsh");
				String qshpy = CnToEnUtil.getFirstLetter(qsh);

				if (py.equalsIgnoreCase(qshpy)) {

					HashMap<String, String> newQsh = new HashMap<String, String>();

					if (qsList.size() < 3) {

						newQsh.put("qshdm", qsh);
						newQsh.put("qshmc", qsh);

						qsList.add(newQsh);

					} else {
						newQsh.put("qshdm", "qsh_" + qshpy);
						newQsh.put("qshmc", "����");

						qsList.add(newQsh);

						break;
					}

				}
			}

			HashMap<String, Object> qsMap = new HashMap<String, Object>();
			qsMap.put("py", py);
			qsMap.put("qsList", qsList);

			gyglQshList.add(qsMap);
		}
		return gyglQshList;
	}

}
