/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-6-6 ����05:03:33 
 */
package com.zfsoft.xgxt.sztz.zyszpj;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import xgxt.action.Base;
import xgxt.studentInfo.dao.XsxxglDAO;
import xgxt.utils.GetTime;
import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.FreeMarkerUtil;
import com.zfsoft.xgxt.base.util.HtmlUtil;
import com.zfsoft.xgxt.base.util.ZipUtil;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ְҵ��������
 * @�๦������: TODO(������һ�仰��������������)
 * @���ߣ� �Ų�· [���ţ�982]
 * @ʱ�䣺 2013-6-6 ����05:03:33
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class ZyszpjService extends SuperServiceImpl<ZyszpjForm, ZyszpjDao> {
	ZyszpjDao dao = new ZyszpjDao();
	XsxxglDAO xxdao = new XsxxglDAO();
	public static String _TX_QUERY = "txcx";// ͬѧ��ѯ
	public static String _BR_QUERY = "brcx";// ���˲�ѯ.
	public static String _LS_QUERY = "lscx";// ��ʦ��ѯ
	public static String _TXPJ = "tx";// ͬѧ����
	public static String _LSPJ = "ls";// ��ʦ����
	public static String _BRPJ = "br";// ��������
	public static String _PRINT_CLASSPATH="classpath://templates//sztz//zyszpj";
	public static String _PRINT_WORD_PATH="zyszpj.xml";
	public ZyszpjService() {
		super.setDao(dao);
	}

	public String getQueryStr(String query, ZyszpjForm myForm) {
		String queryStr = _LS_QUERY;
		if (query.equals(_TX_QUERY)) {
			// myForm.setXh("");
			queryStr = _TX_QUERY;
		} else if (query.equals(_BR_QUERY)) {
			queryStr = _BR_QUERY;
		} else {
			// myForm.setXh("");
		}
		return queryStr;
	}
	/**
	 * 
	 * @����:���ݲ�ѯ��ʽ��ȡ��ѯ��path
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-6-25 ����09:54:04
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param path
	 * @param query
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getPath(String path,String query){
		return path+"?method=list&query="+query;
	}
	/**
	 * @throws Exception
	 * 
	 * @����:��ȡ��ӡ������Ŀ��Ϣ
	 * @���ߣ��Ų�· [���ţ�982]
	 * @���ڣ�2013-6-13 ����06:23:12
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param myForm
	 * @return HashMap<String,HashMap<String,String>> �������� <����Ŀ����,<����Ŀ,��Ӧ��ϸ��Ϣ>>
	 * @throws
	 */
	public List<Map<String, String>> getPrinForZxm(ZyszpjForm myForm)
			throws Exception {
		List<Map<String, String>> hs = new LinkedList<Map<String, String>>();
		List<HashMap<String, String>> list = dao.getXmlbForZysz(myForm
				.getZyszid());
		int xsxh = 0;// ��ʾ���
		for (HashMap<String, String> has : list) {
			xsxh++;
			int i = 1;
			List<HashMap<String, String>> h = dao.getZxmDetailForXmlbAndZyszid(
					myForm.getZyszid(), has.get("xmlbid"));
			if (h.size() == 1) {
				HashMap<String, String> zxm = h.get(0);
				zxm.put("xsbz", "1");// ֻ��һ��
				zxm.put("xsxh", String.valueOf(xsxh));// ���
				hs.add(zxm);
				continue;
			}
			for (HashMap<String, String> hm : h) {
				hm.put("xsbz", "2");// �������ݵĵ�һ������
				hm.put("xsxh", String.valueOf(xsxh));// ���
				if (i != 1) {// ͬһ����Ŀ���ǵ�һ������
					hm.put("xsbz", "3");// ��ӡ����־ͨһ����Ŀ���������� freemarker��������ж�
				}
				hs.add(hm);
				i++;
			}
		}
		return hs;
	}

	/**
	 * @����:������ӡ
	 * @���ߣ��Ų�·
	 * @���ڣ�2013-6-9 ����04:51:36
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param pk
	 * @return
	 * @throws Exception
	 *             File ��������
	 * @throws
	 */
	public File getRyzsWord(List<ZyszpjForm> mydata) throws Exception {
		List<File> files = new ArrayList<File>();
		for (ZyszpjForm zf : mydata) {
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("xh", zf.getXh());
			data.put("xm", zf.getXsxx().get("xm"));
			data.put("bj", zf.getXsxx().get("bj"));
			data.put("zpxx", zf.getZpxx());
			data.put("zpr", zf.getXsxx().get("xm"));
			data.put("hpxx", zf.getHpxx());
			data.put("hpr", zf.getHpr());
			data.put("spxx", zf.getSpxx());
			data.put("hpr", zf.getHpr());
			data.put("zxm", zf.getZxmMap());
			data.put("spr", zf.getSpr());
			data.put("dj", zf.getPjdj());
			files.add(getWord(data));
		}
		File zipFile = ZipUtil.zip(files.toArray(new File[] {}));
		return zipFile;
	}

	/**
	 * 
	 * @����:��ȡ�ĵ�
	 * @���ߣ��Ų�·
	 * @���ڣ�2013-6-17 ����04:50:33
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param data
	 * @return File ��������
	 * @throws
	 */
	private File getWord(Map<String, Object> data) {
		File wordFile = FreeMarkerUtil.getWordFile(data,
				_PRINT_CLASSPATH, _PRINT_WORD_PATH);
		return wordFile;
	}
	/**
	 * 
	 * @����:��ӡ�����ĵ�
	 * @���ߣ��Ų�· [���ţ�982]
	 * @���ڣ�2013-6-17 ����04:56:22
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param zf
	 * @return
	 * File �������� 
	 * @throws
	 */
	public File printWord(ZyszpjForm zf) {
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("xh", zf.getXh());
		data.put("xm", zf.getXsxx().get("xm"));
		data.put("bjmc", zf.getXsxx().get("bjmc"));
		data.put("zpxx", HtmlUtil.xmlZy(zf.getZpxx()));
		data.put("zpr", zf.getXsxx().get("xm"));
		data.put("hpxx", HtmlUtil.xmlZy(zf.getHpxx()));
		data.put("hpr", zf.getHpr());
		data.put("spxx", HtmlUtil.xmlZy(zf.getSpxx()));
		data.put("hpr", zf.getHpr());
		data.put("zxm", zf.getZxmMap());
		data.put("spr", zf.getSpr());
		data.put("dj", zf.getPjdj());
		return getWord(data);
	}

	/**
	 * 
	 * @����:��������Ŀ��ʽ���ڴ�ӡ
	 * @���ߣ��Ų�·
	 * @���ڣ�2013-6-17 ����09:02:52
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param sjmap
	 * @return List<LinkedHashMap<String,String>> ��������
	 * @throws
	 */
	public List<LinkedHashMap<String, String>> zlZxmForPrint(
			HashMap<String, HashMap<String, String>> sjmap) {
		LinkedHashMap<String, String> printMc = new LinkedHashMap<String, String>();
		Iterator<Entry<String, HashMap<String, String>>> it = sjmap.entrySet()
				.iterator();
		int i = 1;
		while (it.hasNext()) {
			Entry<String, HashMap<String, String>> entry = it.next();
			printMc.put("xsxh", String.valueOf(i));// ��ʾ���
			Iterator<Entry<String, String>> colum = entry.getValue().entrySet()
					.iterator();
			while (colum.hasNext()) {
				Entry<String, String> value = colum.next();
				if (i == 1) {// ��һ�δ�Ŵ���Ŀ����
					printMc.put(entry.getKey(), value.getValue());
				} else {
					printMc.put(entry.getKey(), "");
				}
			}
			i++;
		}

		return null;
	}

	public List<String> aaa() {

		return null;
	}

	/**
	 * ��ѯѧ��������Ϣ
	 * 
	 * @param String
	 *            xh
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> selectStuinfo(String xh) {
		return xxdao.selectStuDetailGy(xh);
	}

	public List<HashMap<String, String>> getXmlb() throws Exception {
		return dao.getXmlbList();
	}

	/**
	 * 
	 * @����:TODO(������۵ȼ�)
	 * @���ߣ��Ų�· [���ţ�982]
	 * @���ڣ�2013-6-13 ����10:43:57
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return List<HashMap<String,String>> ��������
	 * @throws
	 */
	public List<HashMap<String, String>> getPjdj() {
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> hm = new HashMap<String, String>();
		hm.put("pjdj", "����");
		hm.put("pjdjmc", "����");
		list.add(hm);
		HashMap<String, String> hm1 = new HashMap<String, String>();
		hm1.put("pjdj", "����");
		hm1.put("pjdjmc", "����");
		list.add(hm1);
		HashMap<String, String> hm2 = new HashMap<String, String>();
		hm2.put("pjdj", "������");
		hm2.put("pjdjmc", "������");
		list.add(hm2);
		return list;
	}

	/**
	 * 
	 * @����:TODO(��ȡ�Ƿ���Բ���������Ϣ)
	 * @���ߣ��Ų�· [���ţ�982]
	 * @���ڣ�2013-6-8 ����07:00:56
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param zf
	 * @param czlx
	 *            �������� �ǻ�������ʦ��
	 * @return String �������� 1���Բ��� -1���ɲ���
	 * @throws
	 */
	public String isCanUpdate(ZyszpjForm zf, String czlx, String czr) {
		if (czlx.equalsIgnoreCase(ZyszpjService._TXPJ)) {// ����ǻ���
			if(czr.equals(zf.getXh())){//���˲��������Լ�
				return "0";
			}else if (null != zf.getHprid() && !zf.getHprid().equals(czr)
					&& !StringUtil.isNull(zf.getHpxx())) {// ����Ѿ��������Ҳ����ϴλ�����ͬѧ�������
				return "-3";
			}
		}
		// ������Ҫ�����ж�
		// else {
		/*
		 * String nj = dao.getBj(czr); String xxnj = dao.getBj(zf.getXh()); if
		 * (!nj.equals(xxnj)) {// ��������˲���ͬ��ͬѧ��ֻ�ܲ鿴 return "-2"; }
		 */
		// }
		if (czlx.equalsIgnoreCase(ZyszpjService._LSPJ)) {// �����ʦ������
			return "-1";
		}
		if (!StringUtil.isNull(zf.getSprid())) {
			return "-1";// �������޸Ļ�����Ϣ
		}
		return "1";// �����޸Ļ�����Ϣ
	}

	/**
	 * 
	 * @����:����ʦ���˺ͻ���������
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-6-14 ����06:57:28
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param zf
	 * @param iszl
	 *            �Ƿ�������ʦ��
	 * @return ZyszpjForm ��������
	 * @throws
	 */
	public ZyszpjForm zlHprAndSpr(ZyszpjForm zf, boolean zlhp, boolean zlsp) {
		zf.setHpr(dao.getName(zf.getHprid()));
		zf.setSpr(dao.getNameForTeac(zf.getSprid()));
		// ����ҳ����ʾ��Ϣ
		if (StringUtil.isNull(zf.getHprid()) && zlhp) {
			zf.setHpxx("��δ������");
		}
		if (StringUtil.isNull(zf.getSprid()) && zlsp) {
			zf.setSpxx("��δ��ʦ��");
		}
		return zf;
	}

	/**
	 * 
	 * @����:������������������ʦ��Ĭ��ֵ
	 * @���ߣ��Ų�· [���ţ�982]
	 * @���ڣ�2013-6-17 ����10:21:59
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param zf
	 * @param xglx
	 *            �Ƿ�����޸ķ���ֵ,���isCanUpdateʹ��
	 * @return ZyszpjForm ��������
	 * @throws
	 */
	public ZyszpjForm zlHpSp(ZyszpjForm zf, String xglx) {
		if (xglx.equals("-1")) {// ʦ��
			return zlHprAndSpr(zf, true, false);
		} else if (xglx.equals("1")) {// ����
			return zlHprAndSpr(zf, false, true);
		}
		return this.zlHprAndSpr(zf);
	}

	/**
	 * 
	 * @����:����ʦ���˺ͻ���������
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-6-14 ����06:57:28
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param iszl
	 *            �Ƿ�������ʦ��
	 * @return ZyszpjForm ��������
	 * @throws
	 */
	public ZyszpjForm zlHprAndSpr(ZyszpjForm zf) {
		zf.setHpr(dao.getName(zf.getHprid()));
		zf.setSpr(dao.getNameForTeac(zf.getSprid()));
		// ����ҳ����ʾ��Ϣ
		if (StringUtil.isNull(zf.getHprid())) {
			zf.setHpxx("��δ������");
		}
		if (StringUtil.isNull(zf.getSprid())) {
			zf.setSpxx("��δ��ʦ��");
		}
		return zf;
	}

	/**
	 * @throws Exception
	 * 
	 * @����:TODO(ɾ�� ����ְҵ���������Ϣ)
	 * @���ߣ��Ų�· [���ţ�982]
	 * @���ڣ�2013-6-7 ����07:04:36
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return boolean ��������
	 * @throws
	 */
	public boolean deleteZyszxxAll(String zyszId) throws Exception {
		return dao.deleteZysz(zyszId) && dao.deleteZxm(zyszId);
	}

	/**
	 * @throws Exception
	 * @throws Exception
	 * 
	 * @����:TODO(����ɾ�� ����ְҵ���������Ϣ)
	 * @���ߣ��Ų�· [���ţ�982]
	 * @���ڣ�2013-6-7 ����07:04:36
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return int �������� �ɹ�ɾ������
	 * @throws
	 */
	public int runDeleteZysz(String[] ids) throws Exception {
		int i = 0;
		for (String str : ids) {
			boolean a = deleteZyszxxAll(str);
			System.out.println(a);
			i = a ? i + 1 : i;
		}
		return i;
	}
	/**
	 * 
	 * @����:����ְҵ��������ѧ�ڣ�ԭ�汾��ֱ�ӱ�����������ڸ���Ϊ�����Ӧ���룩
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-6-24 ����02:19:57
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xqdm
	 * @return
	 * String �������� 
	 * @throws
	 */
	public ZyszpjForm formatZyszpjForXq(ZyszpjForm zf){
		zf.setXqmc(dao.getXqmc(zf.getXq()));
		return zf;
	}
	/**
	 * 
	 * @����:TODO(����ְҵ��������������Ϣ)
	 * @���ߣ��Ų�· [���ţ�982]
	 * @���ڣ�2013-6-8 ����03:29:15
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param form
	 * @return
	 * @throws Exception boolean ��������
	 * @throws
	 */
	public boolean updateZyszxxAll(ZyszpjForm form) throws Exception {
		form.setTxsj(GetTime.getNowTime());
		boolean flag = true;
		// ����ְҵ����
		flag = flag && dao.updateZysz(form);
		// ɾ����Ӧ����Ŀ��Ϣ
		flag = flag && dao.deleteZxm(form.getZyszid());
		// �����µ�����Ŀ��Ϣ
		List<ZxmForm> list = form.getZxm();
		flag = flag && dao.addZxm(list, form.getZyszid());
		return flag;
	}

	/**
	 * @throws Exception
	 * 
	 * @����:TODO(����ְҵ���������е�������Ϣ)
	 * @���ߣ��Ų�· [���ţ�982]
	 * @���ڣ�2013-6-8 ����03:29:15
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param form
	 * @return
	 * @throws Exception boolean ��������
	 * @throws
	 */
	public boolean updateZyszPjxx(ZyszpjForm form) throws Exception {
		// ����ְҵ����
		return dao.updateZysz(form);
	}
	/**
	 * 
	 * @����:TODO(����רҵ����)
	 * @���ߣ� �Ų�· [���ţ�982]
	 * @���ڣ�2013-6-7 ����02:06:21
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param form
	 * @return
	 * @throws SQLException boolean ��������
	 * @throws
	 */
	public boolean saveZysz(ZyszpjForm form) throws SQLException {
		form.setTxsj(GetTime.getNowTime());
		form.setXn(Base.currXn);
		form.setXq(Base.currXq);
		boolean backe = false;
		List<ZxmForm> list = form.getZxm();
		if (dao.saveZysz(form)) {
			backe = dao.addZxm(list, form.getZyszid());
		}
		return backe;
	}

	public String getXmlbMc(String xmid) {
		return dao.getXmlbMc(xmid);
	}

	public boolean isCanAdd(ZyszpjForm form) throws Exception {
		List<HashMap<String, String>> list = dao.isCanAddZysz(form);
		if (null != list && list.size() > 0) {
			return false;
		}
		return true;
	}

	/**
	 * @throws Exception 
	 * 
	 * @����:TODO(������������Ŀ��Ϣ)
	 * @���ߣ��Ų�· [���ţ�982]
	 * @���ڣ�2013-6-7 ����05:33:58
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param form
	 *            void ��������
	 * @throws
	 */
	public void zlXm(ZyszpjForm form) throws Exception {
		List<ZxmForm> zxm = new ArrayList<ZxmForm>();
		List<Map<String, String>> list=this.getPrinForZxm(form);
		for (Map<String, String> hm : list) {
			ZxmForm zf = new ZxmForm();
			zf.setZyszId(hm.get("zyszid"));
			zf.setZxmId(hm.get("zxmid"));
			zf.setXmlbId(hm.get("xmlbid"));
			zf.setSj(hm.get("sj"));
			zf.setHdnr(hm.get("hdnr"));
			zf.setDd(hm.get("dd"));
			zf.setCyjhjqk(hm.get("cyjhjqk"));
			zf.setXmlbmc(this.getXmlbMc(hm.get("xmlbid")));
			zxm.add(zf);
		}
		form.setZxm(zxm);
	}

	/**
	 * @throws Exception
	 * 
	 * @����:TODO(����form���� ����ӵĻ���̵���Ϣ���ӽ���)
	 * @���ߣ��Ų�· [���ţ�982]
	 * @���ڣ�2013-6-7 ����02:18:41
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param zf
	 * @param xmlbid
	 * @param sj
	 * @param dd
	 * @param hdnr
	 * @param cyjhjqk
	 * @return ZyszpjForm ��������
	 * @throws
	 */
	public void zlForm(ZyszpjForm zf, String[] xmlbid, String[] sj,
			String[] dd, String[] hdnr, String[] cyjhjqk) throws Exception {
		if (xmlbid.length != dd.length || xmlbid.length != hdnr.length
				|| xmlbid.length != cyjhjqk.length) {
			throw new Exception("ҳ�����ݴ��ݴ������飡");
		}

		List<ZxmForm> xmList = new ArrayList<ZxmForm>();
		for (int i = 0; i < xmlbid.length; i++) {
			ZxmForm xm = new ZxmForm();
			xm.setXmlbId(xmlbid[i]);
			xm.setSj(sj[i]);
			xm.setDd(dd[i]);
			xm.setHdnr(hdnr[i]);
			xm.setCyjhjqk(cyjhjqk[i]);
			xmList.add(xm);
			xm.setZyszId(zf.getZyszid());
		}
		zf.setZxm(xmList);
	}
}
