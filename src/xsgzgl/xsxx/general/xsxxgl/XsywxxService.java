package xsgzgl.xsxx.general.xsxxgl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import common.Globals;
import common.GlobalsVariable;
import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.xsxx.comm.xjyd.XsxxXjydForm;
import xgxt.xsxx.comm.xjyd.XsxxXjydService;
import xsgzgl.gygl.comm.GyglNewInter;
import xsgzgl.pjpy.general.PjpyGeneralService;
import xsgzgl.pjpy.general.inter.PjpyYwjkInterface;
import xsgzgl.qgzx.xsgw.QgzxXsgwcxService;
import xsgzgl.wjcf.cfsjwh.WjcfCfsjwhService;
import xsgzgl.xszz.general.XszzGeneralService;
import xsgzgl.xszz.general.inter.XszzYwjkInterface;

import com.zfsoft.xgxt.rcsw.cwsjcx.CwsjService;
import com.zfsoft.xgxt.rcsw.rcxwwh.rcxwjg.RcxwjgService;
import com.zfsoft.xgxt.xpjpy.wdpj.pjjg.PjjgService;
import com.zfsoft.xgxt.xpjpy.zhcp.zcfs.ZcfsDao;
import com.zfsoft.xgxt.xsxx.xjyd.xjydjg.XjydjgService;
import com.zfsoft.xgxt.xszz.knsjg.KnsjgDao;
import com.zfsoft.xgxt.xszz.zzxmjg.ZzxmjgDao;
import common.GlobalsVariable;

/**
 * ѧ��ҵ����Ϣ����SERVICE
 */
public class XsywxxService extends CommService {

	/**
	 * ͨ��ѧ�Ż�ȡѧ���ɼ��б�
	 * 
	 * @param xh
	 * @return
	 */
	public List<HashMap<String, Object>> getStuCjList(String xh) {
		List<HashMap<String, Object>> rs = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> map = new HashMap<String, Object>();

		map.put(GlobalsVariable.XSXX_KTEYS_GNMK, "ѧ���ɼ�");
		map.put(GlobalsVariable.XSXX_KTEYS_CXJG, getStuCjAllList(xh));

		rs.add(map);
		return rs;
	}

	/**
	 * ѧ���ɼ���ͷ
	 * 
	 * @return
	 */
	public List<String[]> getStuCjAllList(String xh) {
		XsxxtyglDao dao = new XsxxtyglDao();
		String[] title = { "ѧ��", "ѧ��", "�γ�����", "�γ�����", "�ɼ�", "�����ɼ�", "����", "ѧ��" };
		List<String[]> rs = new ArrayList<String[]>();
		rs.addAll(dao.getStuCjList(xh));
		rs.add(0, title);
		return rs;
	}
	
	/**
	 * ͨ��ѧ�Ų�ѯѧ���ɼ����ȼ�������Ϣ�б�
	 * @param xh
	 * @return
	 */
	public List<HashMap<String, Object>> getStuCjxxDjxxList(String xh) {
		XsxxtyglDao dao = new XsxxtyglDao();
		List<HashMap<String, Object>> rs = new ArrayList<HashMap<String, Object>>();
		//�ɼ� 
		HashMap<String, Object> map = new HashMap<String, Object>();
		List<String[]> list = new ArrayList<String[]>();
		
		if(Globals.XXDM_GLLGDX.equals(Base.xxdm)){
			list.add(new String[]{"ѧ��", "ѧ��", "�γ�����", "�γ�����", "�ɼ�", "�����ɼ�","���޳ɼ�","����", "ѧ��"});
			list.addAll(dao.getStugllgCjList(xh));
			map.put(GlobalsVariable.XSXX_KTEYS_GNMK, "�γ̿��Գɼ�");
			map.put(GlobalsVariable.XSXX_KTEYS_CXJG, list);
			rs.add(map);
		}else if(Globals.XXDM_CZZYJSXY.equals(Base.xxdm)){
			list.add(new String[]{"ѧ��", "ѧ��", "�γ�����", "�γ�����", "�ɼ�","��ĩ�ɼ�","����", "ѧ��"});
			list.addAll(dao.getStuczxyCjList(xh));
			map.put(GlobalsVariable.XSXX_KTEYS_GNMK, "�γ̿��Գɼ�");
			map.put(GlobalsVariable.XSXX_KTEYS_CXJG, list);
			rs.add(map);
		}else{
			list.add(new String[]{"ѧ��", "ѧ��", "�γ�����", "�γ�����", "�ɼ�", "�����ɼ�", "����", "ѧ��"});
			list.addAll(dao.getStuCjList(xh));
			map.put(GlobalsVariable.XSXX_KTEYS_GNMK, "�γ̿��Գɼ�");
			map.put(GlobalsVariable.XSXX_KTEYS_CXJG, list);
			rs.add(map);
		}
		
			//�ȼ�����
			map = new HashMap<String, Object>();
			list = new ArrayList<String[]>();
			list.add(new String[]{"ѧ��", "ѧ��", "�ȼ���������", "�ɼ�"});
			list.addAll(dao.getStuDjcjList(xh));
			map.put(GlobalsVariable.XSXX_KTEYS_GNMK, "�ȼ����Գɼ�");
			map.put(GlobalsVariable.XSXX_KTEYS_CXJG, list);
			rs.add(map);
		
		return rs;
		
	}

	/**
	 * ��ȡ��ҵ��ģ�����HTML
	 * 
	 * @param gnmk
	 * @return
	 */
	public String getYwmklbHtml(String gnmk, String xh) {
		gnmk = unicode2Gbk(gnmk);
		StringBuffer html = new StringBuffer("");
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		String type = "no";// �����ж��Ƿ��ж������ܵı�־
		if (GlobalsVariable.XTWH_GNMK_WJCF.equalsIgnoreCase(gnmk)) {// Υ�ʹ����б�ģ��Ĵ���
			WjcfCfsjwhService wjcfService = new WjcfCfsjwhService();
			list = wjcfService.getStuWjcfList(xh);
		} else if (GlobalsVariable.XTWH_GNMK_GYGL.equalsIgnoreCase(gnmk)) {// ��Ԣ�����б�ģ��Ĵ���
			GyglNewInter gyglInter = new GyglNewInter();
			list = gyglInter.getStuGyAllList(xh);
			//Ϋ��ѧԺ������Ʒά��������ѧУû�ж�������
			if(list.size()!=1){
				type = "yes";
			}
		} else if (GlobalsVariable.XTWH_GNMK_XSCJ.equalsIgnoreCase(gnmk)) {// ѧ���ɼ��б�ģ��Ĵ���
			//list = getStuCjList(xh);
			list = getStuCjxxDjxxList(xh);
			type = "yes";
		} else if (GlobalsVariable.XTWH_GNMK_QGZX.equalsIgnoreCase(gnmk)) {// �ڹ���ѧ�б�ģ��Ĵ���
			QgzxXsgwcxService qgzxService = new QgzxXsgwcxService();
			list = qgzxService.getStuGwxxCjxxList(xh);
			type = "yes";
		} else if (GlobalsVariable.XTWH_GNMK_PJPY.equalsIgnoreCase(gnmk)) {// ���������б�ģ��Ĵ���
			try {
				//list = getStuPjList(xh);
				PjjgService pjjgService = new PjjgService();
				list = pjjgService.getPjpyInfo(xh);
			} catch (Exception e) {
				e.printStackTrace();
			}
			type = "yes";
		} else if (GlobalsVariable.XTWH_GNMK_XSZZ.equalsIgnoreCase(gnmk)) {// ѧ�������б�ģ��Ĵ���
			try {
			//	list = getStuZzList(xh);
				list = getStuZzListNew(xh);
			} catch (Exception e) {
				e.printStackTrace();
			}
			type = "yes";
		} else if (GlobalsVariable.XTWH_GNMK_RCSW.equalsIgnoreCase(gnmk)) {// �ճ������б�ģ��Ĵ���
			try {
				
				list = getRcswInfo(xh);
				} catch (Exception e) {
					e.printStackTrace();
				}
				type = "yes";
			} else if (GlobalsVariable.XTWH_GNMK_XJYD.equalsIgnoreCase(gnmk)) {// ѧ���춯�б�ģ��Ĵ����人ְҵ����ѧԺ���Ի�
			try {
					list = getStuXjydList(xh);
				} catch (Exception e) {
					e.printStackTrace();
				}
				type = "yes";
		} else if (GlobalsVariable.XTWH_GNMK_CWSJ.equalsIgnoreCase(gnmk)){//��������
			try {
				list = getStuCwsjList(xh);
			} catch (Exception e) {
				e.printStackTrace();
			}
			type = "no";
		} else if (GlobalsVariable.XTWH_GNMK_XJYDXX.equalsIgnoreCase(gnmk)) {// ѧ���춯�б�ģ��[ͨ��]
			try {
				list = getXjydList(xh);
			} catch (Exception e) {
				e.printStackTrace();
			}
			type = "yes";
	}
		getHtmlByList(html, list, type);
		return html.toString();
	}
	


	/**
	 * @throws Exception  
	 * @����: ѧ���춯�б�ģ��[ͨ��]
	 * @���ߣ�Qilm[���ţ�964]
	 * @���ڣ�2013-12-27 ����04:03:51
	 * @param xh
	 * @return
	 * List<HashMap<String,Object>> �������� 
	 * @throws 
	 */
	private List<HashMap<String, Object>> getXjydList(String xh) throws Exception {

		List<HashMap<String, Object>> rs = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> map = new HashMap<String, Object>();
		XjydjgService service = new XjydjgService();
		List<String[]> list = new ArrayList<String[]>();
		list.add(new String[]{"ѧ��","ѧ��","�춯���","ԭ�꼶","ԭ"+Base.YXPZXY_KEY,"ԭרҵ","ԭ�༶","ѧ���춯�ĺ�","�춯ʱ��"});
		list.addAll(service.getXsydListByXh(xh));		
		map.put(GlobalsVariable.XSXX_KTEYS_GNMK, "ѧ���춯��Ϣ");
		map.put(GlobalsVariable.XSXX_KTEYS_CXJG, list);
		rs.add(map);
		return rs;
	}

	/** 
	 * @����:(��ȡѧ����������)
	 * @���ߣ�cmj[���ţ�913]
	 * @���ڣ�2013-8-28 ����07:19:50
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * List<HashMap<String,Object>> �������� 
	 * @throws 
	 */
	private List<HashMap<String, Object>> getStuCwsjList(String xh) throws Exception{
		List<HashMap<String, Object>> rs = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> map = new HashMap<String, Object>();
		CwsjService service = new CwsjService();
		List<String[]> list = new ArrayList<String[]>();
		list.add(new String[]{"ѧ��","ѧ��","Ӧ�ɷ���","ʵ�ɷ���","Ƿ�ɷ���"});
		list.addAll(service.getStuCwsjList(xh));
		map.put(GlobalsVariable.XSXX_KTEYS_GNMK, "����������Ϣ");
		map.put(GlobalsVariable.XSXX_KTEYS_CXJG, list);
		rs.add(map);
		return rs;
	}

	/**
	 * ͨ���б�ƴ��HTML�ַ���
	 * 
	 * @param html
	 * @param cjList
	 */
	private StringBuffer getHtmlByList(StringBuffer htmls,
			List<HashMap<String, Object>> cjList, String type) {
		if (cjList != null && cjList.size() > 0) {
			if ("yes".equalsIgnoreCase(type)) {// �ж������ܵ�ƴ��
				for (HashMap<String, Object> map : cjList) {
					String mkmc = (String) map
							.get(GlobalsVariable.XSXX_KTEYS_GNMK);
					List<String[]> rs = map
							.get(GlobalsVariable.XSXX_KTEYS_CXJG) != null ? (List<String[]>) map
							.get(GlobalsVariable.XSXX_KTEYS_CXJG)
							: null;
					int cols = rs != null && rs.get(0) != null ? rs.get(0).length
							: 0;

					// ƴ�ӱ�ͷ����
					htmls.append("<table width='100%' id='tab_");
					htmls.append(mkmc);
					htmls
							.append("'><thead><tr onclick='' style='cursor: hand;'>");
					htmls.append("<th colspan='" + cols + "'><span>");
					htmls.append(mkmc);
					htmls.append("</span></th></tr></thead>");

					// ƴ�ӽ��������
					if (map.get(GlobalsVariable.XSXX_KTEYS_CXJG) != null) {
						List<String[]> list = (List<String[]>) map
								.get(GlobalsVariable.XSXX_KTEYS_CXJG);
						int size = list.size();
						for (int j = 0; j < list.size(); j++) {
							
							if (1 == size) {
								htmls.append("<tr><td colspan='"+cols+"'><div align='center'>�������ݣ�</div></td></tr>");
								break;
							}
							
							htmls.append("<tr>");
							String[] array = list.get(j);
							String width = array != null ? ""
									+ (100 / array.length) : "100";
							for (int m = 0; m < array.length; m++) {
								if (j == 0) {
									htmls.append("<th width='" + width
											+ "%'><div align='center'>");
									htmls.append(array[m]);
									htmls.append("</div></th>");
								} else {
									htmls.append("<td  align='center'>");
									htmls.append(array[m]);
									htmls.append("</td>");
								}
							}
							htmls.append("</tr>");
						}
						
					}
					htmls.append("</tbody></table>");
				}
			} else {
				htmls.append("<table width='100%' ><tbody>");
				HashMap<String, Object> map = cjList.get(0);
				if (map.get(GlobalsVariable.XSXX_KTEYS_CXJG) != null) {
					List<String[]> list = (List<String[]>) map
							.get(GlobalsVariable.XSXX_KTEYS_CXJG);
					int size = list.size();
					int cols = list.get(0) != null ? list.get(0).length : 1;
					for (int j = 0; j < list.size(); j++) {
						
						if (1 == size) {
							htmls.append("<tr><td colspan='"+cols+"'><div align='center'>�������ݣ�</div></td></tr>");
							break;
						}
						
						htmls.append("<tr>");
						String[] array = list.get(j);
						String width = array != null ? ""
								+ (100 / array.length) : "100";
						for (int m = 0; m < array.length; m++) {
							if (j == 0) {
								htmls.append("<th width='" + width
										+ "%'><div align='center'>");
								htmls.append(array[m]);
								htmls.append("</div></th>");
							} else {
								htmls.append("<td  align='center'>");
								htmls.append(array[m]);
								htmls.append("</td>");
							}
						}
						htmls.append("</tr>");
					}
					
				}
				htmls.append("</tbody></table>");
			}
		}
		return htmls;
	}

	/**
	 * ͨ��ѧ�Ż�ȡѧ���u���б�
	 * 
	 * @param xh
	 * @author ������
	 */
	public List<HashMap<String, Object>> getStuPjList(String xh)
			throws IllegalArgumentException, SecurityException,
			InstantiationException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

		PjpyGeneralService myService = new PjpyGeneralService();
		PjpyYwjkInterface service = myService.getPjpyYwjkService();

		List<HashMap<String, Object>> rs = service.getStuPjList(xh);

		return rs;
	}

	/**
	 * ͨ��ѧ�Ż�ȡѧ�������б�
	 * 
	 * @param xh
	 * @author ������
	 */
	public List<HashMap<String, Object>> getStuZzList(String xh)
			throws IllegalArgumentException, SecurityException,
			InstantiationException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

		XszzGeneralService myService = new XszzGeneralService();
		XszzYwjkInterface service = myService.getXszzYwjkService();

		List<HashMap<String, Object>> rs = service.getStuZzList(xh);

		return rs;
	}
	
	
	public List<HashMap<String, Object>> getStuZzListNew(String xh)
	throws IllegalArgumentException, SecurityException,
		InstantiationException, IllegalAccessException,
		InvocationTargetException, NoSuchMethodException {
		List<HashMap<String, Object>> rs = new ArrayList<HashMap<String, Object>>();

		//�������϶���Ϣ
		KnsjgDao knsjgDao = new KnsjgDao();
		HashMap<String, Object> map = new HashMap<String, Object>();
		List<String[]> list = new ArrayList<String[]>();
		
		list.add(new String[]{"ѧ��", "ѧ��", "�϶�����", "�϶�ʱ��"});
		list.addAll(knsjgDao.getKnsjgList(xh));
		map.put(GlobalsVariable.XSXX_KTEYS_GNMK, "�������϶���Ϣ");
		map.put(GlobalsVariable.XSXX_KTEYS_CXJG, list);
		rs.add(map);
		
		//������Ŀ��Ϣ
		ZzxmjgDao zzxmjgDao = new ZzxmjgDao();
		map = new HashMap<String, Object>();
		list = new ArrayList<String[]>();
		list.add(new String[]{"ѧ��", "ѧ��", "��Ŀ����", "���", "����ʱ��"});
		list.addAll(zzxmjgDao.getZzxmjgList(xh));
		map.put(GlobalsVariable.XSXX_KTEYS_GNMK, "������Ŀ��Ϣ");
		map.put(GlobalsVariable.XSXX_KTEYS_CXJG, list);
		rs.add(map);
		return rs;
	}
	
	/** 
	 * @����: ͨ��ѧ�Ż�ȡѧ��ѧ���춯�б�
	 * @���ߣ�HongLin
	 * @���ڣ�2013-5-20 ����11:51:36
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * @throws Exception
	 * List<HashMap<String,Object>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, Object>> getStuXjydList(String xh)
	throws Exception {

		List<HashMap<String, Object>> rs = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> map = new HashMap<String, Object>();
		XsxxXjydService service = new XsxxXjydService();
		List<String[]> list = new ArrayList<String[]>();
		list.add(new String[]{"ѧ��","ѧ��","�춯���","ת��"+Base.YXPZXY_KEY,"ת���༶","ת��"+Base.YXPZXY_KEY,"ת��༶","�춯ʱ��"});
		XsxxXjydForm model = new XsxxXjydForm(); 
		model.setXh(xh);
		list.addAll(service.getStuXjydList(model));
		map.put(GlobalsVariable.XSXX_KTEYS_GNMK, "ѧ���춯��Ϣ");
		map.put(GlobalsVariable.XSXX_KTEYS_CXJG, list);
		rs.add(map);
		return rs;
	}
	
	
	/**
	 * 
	 * �ճ�������Ϣ
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2013-10-16 ����10:25:47
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * @throws Exception
	 * List<HashMap<String,Object>> �������� 
	 * @throws
	 */
	public List<HashMap<String, Object>> getRcswInfo(String xh) throws Exception{
		
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		RcxwjgService  rcxwjgService = new RcxwjgService();
		String[] title = { "��Ϊ��¼ѧ��", "��Ϊ��¼ѧ��", "��Ϊ��¼����", "��Ϊ��¼���", "��Ϊ��¼ʱ��","��Ϊ����ֵ" };
		List<String[]> rs = new ArrayList<String[]>();
		rs.add(title);
		rs.addAll(rcxwjgService.getStuRcswList(xh));
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put(GlobalsVariable.XSXX_KTEYS_GNMK, "�ճ���Ϊ��¼");
		map.put(GlobalsVariable.XSXX_KTEYS_CXJG, rs);
		list.add(map);
		
		return list;
	}
}
