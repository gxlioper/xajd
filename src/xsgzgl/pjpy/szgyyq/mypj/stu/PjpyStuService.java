package xsgzgl.pjpy.szgyyq.mypj.stu;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.action.Base;
import xgxt.comm.SearchRsModel;
import xgxt.form.SaveForm;
import xgxt.form.User;
import xsgzgl.pjpy.szgyyq.model.XsssModel;
import xsgzgl.pjpy.szgyyq.model.XstsModel;
import xsgzgl.pjpy.szgyyq.mypj.PjpyMypjService;
import xsgzgl.pjpy.szgyyq.mypj.pjxm.DshdService;
import xsgzgl.pjpy.szgyyq.mypj.pjxm.FiveSService;
import xsgzgl.pjpy.szgyyq.mypj.pjxm.IvtltService;
import xsgzgl.pjpy.szgyyq.mypj.pjxm.ShsjService;
import xsgzgl.pjpy.szgyyq.mypj.pjxm.WthdService;
import xsgzgl.pjpy.szgyyq.mypj.pjxm.YybdService;
import xsgzgl.pjpy.szgyyq.mypj.pjxm.ZhcpService;
import xsgzgl.pjpy.szgyyq.mypj.pjxm.ZznlService;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ��������_���ݹ�ҵ԰��_�ҵ�����(ѧ��)_Service��
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author ΰ�����
 * @version 1.0
 */

public class PjpyStuService extends PjpyMypjService {

	PjpyStuDAO dao = new PjpyStuDAO();

	/**
	 * �����Ŀ�б�(��������)
	 * 
	 * @author ΰ�����
	 */
	public List<HashMap<String, String>> getFssqXmList(PjpyStuForm model) {

		List<HashMap<String, String>> xmList = new ArrayList<HashMap<String, String>>();

		HashMap<String, String> map = new HashMap<String, String>();

		// ����
		map = new HashMap<String, String>();
		map.put("xmdm", "szyq_dshdjzb");
		map.put("xmmc", "����");
		xmList.add(map);

		// ���Ա��
		map = new HashMap<String, String>();
		map.put("xmdm", "szyq_yybdjzb");
		map.put("xmmc", "���Ա��");
		xmList.add(map);

		// IVT��̳
		map = new HashMap<String, String>();
		map.put("xmdm", "szyq_ivtltb");
		map.put("xmmc", "IVT��̳");
		xmList.add(map);

		// ����
		map = new HashMap<String, String>();
		map.put("xmdm", "szyq_xthddjb");
		map.put("xmmc", "����");
		xmList.add(map);

		// ��֯�
		map = new HashMap<String, String>();
		map.put("xmdm", "szyc_zznlfzb");
		map.put("xmmc", "��֯����");
		xmList.add(map);

		// ���ʵ��
		map = new HashMap<String, String>();
		map.put("xmdm", "szyc_shsjfzb");
		map.put("xmmc", "���ʵ��");
		xmList.add(map);

		return xmList;
	}	
	
	/**
	 * ���������
	 * 
	 * @author ΰ�����
	 * 
	 * @throws IOException
	 */
	public String getSqxmInfo(PjpyStuForm model) {

		// ѧ��
		String xh = model.getXh();
		// ��Ŀ����
		String xmdm = model.getXmdm();
		// ѧ��
		String xn = Base.currXn;
		// ѧ��
		String xq = Base.currXq;
		
		List<HashMap<String,String>> list = dao.getSqxmInfo(model);
		
		StringBuilder html = new StringBuilder();
		
		if (list != null && list.size() > 0) {
			
			HashMap<String, String> map = list.get(0);
			// �����
			String sqf = map.get("sqf");
			// ��˷�
			String shf = map.get("shf");
			// ������
			String jcf = map.get("jcf");
			// �ܷ�
			String zf = map.get("zf");
			
			html.append("<span class=\"formbox\">");
			html.append("����Ŀ��Ϣ��");
			
			html.append("������(");
			html.append(jcf);
			html.append("��) ");
			
			html.append("�ܷ�(");
			html.append(zf);
			html.append("��)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
			
			html.append("������������");
			html.append("�������(");
			if("0".equalsIgnoreCase(sqf)){
				html.append(sqf);
			}else{
				html.append("<a href=\"#\" onclick=\"showSqxxDetail('"+xn+"','"+xq+"','"+xh+"','"+xmdm+"','edit');return false;\">");
				html.append("<font color=\"blue\">");
				html.append(sqf);
				html.append("</font>");
				html.append("</a>");
			}
			html.append("��) ");
			
			html.append("������˷�(");
			html.append(shf);
			html.append("��) ");
			
			html.append("</span>");

		}

		return html.toString();
	}
	
	/**
	 * ���������Ϣ�б�
	 * 
	 * @author ΰ�����
	 */
	public List<HashMap<String, String>> getSqxxList(PjpyStuForm model) {

		List<HashMap<String, String>> xmList = new ArrayList<HashMap<String, String>>();

		// ��Ŀ����
		String xmdm = model.getXmdm();

		if ("szyq_dshdjzb".equalsIgnoreCase(xmdm)) {// ����
			DshdService service = new DshdService();
			xmList = service.getDshdList(model);
		} else if ("szyq_yybdjzb".equalsIgnoreCase(xmdm)) {// ���Ա��
			YybdService service =new YybdService();
			xmList = service.getYybdList(model);
		} else if ("szyq_ivtltb".equalsIgnoreCase(xmdm)) {// IVT��̳
			IvtltService service =new IvtltService();
			xmList = service.getIvtltList(model);
		} else if ("szyq_xthddjb".equalsIgnoreCase(xmdm)) {// ����
			WthdService service =new WthdService();
			xmList = service.getWthdList(model);
		} else if ("szyc_zznlfzb".equalsIgnoreCase(xmdm)) {// ��֯�
			ZznlService service =new ZznlService();
			xmList = service.getZznlList(model);
		} else if ("szyc_shsjfzb".equalsIgnoreCase(xmdm)) {// ���ʵ��
			ShsjService service =new ShsjService();
			xmList = service.getShsjList(model);
		} else if ("szyc_5sb".equalsIgnoreCase(xmdm)) {// 5s
			FiveSService service =new FiveSService();
			xmList = service.getFiveSList(model);
		} else if ("szgy_zhszcphzlsb".equalsIgnoreCase(xmdm)) {// �ۺ����ʷ�
			ZhcpService service =new ZhcpService();
			xmList = service.getZhcpList(model);
		}

		int rownum = 10;
		int rsnum = (xmList != null && xmList.size() > 0) ? xmList.size() : 0;

		// ����
		if ((rownum - rsnum) > 0) {
			for (int i = 0; i < (rownum - rsnum); i++) {
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("sqf", "&nbsp;");

				xmList.add(map);
			}
		}

		return xmList;
	}
	
	/**
	 * ����ѧ������
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	public Boolean saveXsss(PjpyStuForm model, User user,HttpServletRequest request){

		XsssModel xsssModel = model.getXsssModel();
		
		// ѧ��
		String xh = xsssModel.getXh();
		// ��ĿID
		String xmid = xsssModel.getXmid();
		
		// ������Ŀ��˱�
		String tableName = "xg_pjpy_szgyyq_xsssb";
		// ����
		String pk = "xh||xmid";
		// ����ֵ
		String[] pkValue = new String[] { xh + xmid };
		// �޸��ֶ�
		String[] onezd = new String[] { "xn", "xq", "xh", "xmlx", "xmid",
				"ssnr" };

		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(tableName);
		saveForm.setPk(pk);
		saveForm.setPkValue(pkValue);
		saveForm.setOnezd(onezd);

		boolean flag = false;
		
		try {
			flag = saveInfoToDb(saveForm, xsssModel, request);
		} catch (Exception e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}
		
		return flag;
	}
	
	/**
	 * ����ѧ��Ͷ��
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	public Boolean saveXsts(PjpyStuForm model, User user,HttpServletRequest request){

		XstsModel xstsModel = model.getXstsModel();
		
		// ѧ��
		String xn = xstsModel.getXn();
		// ѧ��
		String xq = xstsModel.getXq();
		// ѧ��
		String xh = xstsModel.getXh();
		// ��Ͷ����
		String btsr = xstsModel.getBtsr();
		// ��Ŀ����
		String xmlx = xstsModel.getXmlx();

		// ������Ŀ��˱�
		String tableName = "xg_pjpy_szgyyq_xstsb";
		// ����
		String pk = "xn||xq||xh||btsr||xmlx";
		// ����ֵ
		String[] pkValue = new String[] { xn + xq + xh + btsr + xmlx };
		// �޸��ֶ�
		String[] onezd = new String[] { "xn", "xq", "xh", "btsr", "xmlx",
				"tsnr" };

		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(tableName);
		saveForm.setPk(pk);
		saveForm.setPkValue(pkValue);
		saveForm.setOnezd(onezd);

		boolean flag = false;
		
		try {
			flag = saveInfoToDb(saveForm, xstsModel, request);
		} catch (Exception e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}
		
		return flag;
	}
	
	/**
	 * �����Ŀ�б�(�����ѯ)
	 * 
	 * @author ΰ�����
	 */
	public List<HashMap<String, String>> getJgcxXmList(PjpyStuForm model) {

		List<HashMap<String, String>> xmList = new ArrayList<HashMap<String, String>>();

		HashMap<String, String> map = new HashMap<String, String>();

		// ����
		map = new HashMap<String, String>();
		map.put("xmdm", "szyq_dshdjzb");
		map.put("xmmc", "����");
		xmList.add(map);

		// ���Ա��
		map = new HashMap<String, String>();
		map.put("xmdm", "szyq_yybdjzb");
		map.put("xmmc", "���Ա��");
		xmList.add(map);

		// IVT��̳
		map = new HashMap<String, String>();
		map.put("xmdm", "szyq_ivtltb");
		map.put("xmmc", "IVT��̳");
		xmList.add(map);

		// ����
		map = new HashMap<String, String>();
		map.put("xmdm", "szyq_xthddjb");
		map.put("xmmc", "����");
		xmList.add(map);

		// ��֯�
		map = new HashMap<String, String>();
		map.put("xmdm", "szyc_zznlfzb");
		map.put("xmmc", "��֯����");
		xmList.add(map);

		// ���ʵ��
		map = new HashMap<String, String>();
		map.put("xmdm", "szyc_shsjfzb");
		map.put("xmmc", "���ʵ��");
		xmList.add(map);

		// 5S��
		map = new HashMap<String, String>();
		map.put("xmdm", "szyc_5sb");
		map.put("xmmc", "5S");
		xmList.add(map);

		// �ۺ����ʲ��
		map = new HashMap<String, String>();
		map.put("xmdm", "szgy_zhszcphzlsb");
		map.put("xmmc", "�ۺ����ʷ�");
		xmList.add(map);
		
		return xmList;
	}
	
	/**
	 * ��ø�����Ŀ�Ľ����ѯ�б�
	 * 
	 * @author ΰ�����
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public ArrayList<String[]> getJgcxList(PjpyStuForm model, User user)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		//��Ŀ����
		String xmdm = model.getXmdm();
		ArrayList<String[]> list = new ArrayList<String[]>();
		
		if ("szyq_dshdjzb".equalsIgnoreCase(xmdm)) {// ����
			DshdService service = new DshdService();
			list = service.getDshdList(model, user);
		} else if ("szyq_yybdjzb".equalsIgnoreCase(xmdm)) {// ���Ա��
			YybdService service = new YybdService();
			list = service.getYybdList(model, user);
		} else if ("szyq_ivtltb".equalsIgnoreCase(xmdm)) {// IVT��̳
			IvtltService service = new IvtltService();
			list = service.getIvtltList(model, user);
		} else if ("szyq_xthddjb".equalsIgnoreCase(xmdm)) {// ����
			WthdService service = new WthdService();
			list = service.getWthdList(model, user);
		} else if ("szyc_zznlfzb".equalsIgnoreCase(xmdm)) {// ��֯�
			ZznlService service = new ZznlService();
			list = service.getZznlList(model, user);
		} else if ("szyc_shsjfzb".equalsIgnoreCase(xmdm)) {// ���ʵ��
			ShsjService service = new ShsjService();
			list = service.getShsjList(model, user);
		} else if ("szyc_5sb".equalsIgnoreCase(xmdm)) {// 5s
			FiveSService service = new FiveSService();
			list = service.getFiveSList(model, user);
		} else if ("szgy_zhszcphzlsb".equalsIgnoreCase(xmdm)) {// �۲��ܷ�
			ZhcpService service = new ZhcpService();
			list = service.getZhcpList(model, user);
		}
		
		return list;
	}
	
	/**
	 * ��ý����ѯHtml
	 * 
	 * @author ΰ�����
	 */
	public String getJgcxHtml(SearchRsModel rsModel, PjpyStuForm model,
			ArrayList<String[]> rsArrList,User user) {
		
		//��Ŀ����
		String xmdm = model.getXmdm();
		String html = "";
		
		if ("szyq_dshdjzb".equalsIgnoreCase(xmdm)) {// ����
			DshdService service = new DshdService();
			html = service.getDshdHtml(rsModel, model, rsArrList,user);
		} else if ("szyq_yybdjzb".equalsIgnoreCase(xmdm)) {// ���Ա��
			YybdService service = new YybdService();
			html = service.getYybdHtml(rsModel, model, rsArrList,user);
		} else if ("szyq_ivtltb".equalsIgnoreCase(xmdm)) {// IVT��̳
			IvtltService service = new IvtltService();
			html = service.getIvtltHtml(rsModel, model, rsArrList,user);
		} else if ("szyq_xthddjb".equalsIgnoreCase(xmdm)) {// ����
			WthdService service = new WthdService();
			html = service.getWthdHtml(rsModel, model, rsArrList,user);
		} else if ("szyc_zznlfzb".equalsIgnoreCase(xmdm)) {// ��֯�
			ZznlService service = new ZznlService();
			html = service.getZznlHtml(rsModel, model, rsArrList,user);
		} else if ("szyc_shsjfzb".equalsIgnoreCase(xmdm)) {// ���ʵ��
			ShsjService service = new ShsjService();
			html = service.getShsjHtml(rsModel, model, rsArrList,user);
		} else if ("szyc_5sb".equalsIgnoreCase(xmdm)) {// 5s
			FiveSService service = new FiveSService();
			html = service.getFiveSHtml(rsModel, model, rsArrList,user);
		} else if ("szgy_zhszcphzlsb".equalsIgnoreCase(xmdm)) {// 5s
			ZhcpService service = new ZhcpService();
			html = service.getZhcpHtml(rsModel, model, rsArrList,user);
		}

		return html;
	}
	
	/**
	 * ����ҵ�������Ϣ�б�
	 * 
	 * @author ΰ�����
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public ArrayList<String[]> getMyssList(PjpyStuForm model, User user)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		ArrayList<String[]> list = dao.getMyssList(model, user);

		return list;
	}
	
	/**
	 * ����ҵ�����Html
	 * 
	 * @author ΰ�����
	 */
	public String getMyssHtml(SearchRsModel rsModel, PjpyStuForm model,
			ArrayList<String[]> rsArrList,User user) {

		// ������Ŀ
		String czxm = model.getCzxm();
		// IE�汾
		String ie = rsModel.getIe();
		// ѧ��
		String xn = model.getXn();
		// ѧ��
		String xq = model.getXq();
		// ��Ŀ
		String xmdm = model.getXmdm();
		
		StringBuilder spHtml = new StringBuilder();

		if (rsArrList != null && rsArrList.size() > 0) {

			for (int i = 0; i < rsArrList.size(); i++) {

				String[] rs = rsArrList.get(i);
				
				spHtml.append("<tr onclick=\"rowOnClick(this);\" ondblclick=\"\">");

				for (int j = 0; j < rs.length-8; j++) {
					spHtml.append("<td align=\"left\" nowrap=\"nowrap\" style=\"width:20%;\" ");
					
					// IE8
					if ("5.8".equalsIgnoreCase(ie)) {
						spHtml.append("height=\"28.5px\"");
					} else {
						spHtml.append("height=\"29px\"");
					}
					
					if(j == 1){
						String ssnr = rs[rs.length-3];
						spHtml.append(" title=\""+ssnr+"\" ");
					}
					spHtml.append(">");
					
					spHtml.append(rs[j]);
					spHtml.append("</td>");
				}
				
				// ����״̬
				String lx = rs[rs.length-1];
	
				spHtml.append("<td align=\"left\" nowrap=\"nowrap\" style=\"width:20%;\" > ");
				if("δ����".equalsIgnoreCase(lx)){
					spHtml.append("δ����");
				}else{
					
					String ssnr = rs[rs.length-3];
					String clyj = rs[rs.length-2];
					String sssj = rs[rs.length-11];
					String clr = rs[rs.length-9];
					String clsj = rs[rs.length-8];
					
					spHtml.append("<a href\"#\" onclick=\"showXsssDiv('"+ssnr+"','"+sssj+"','"+clr+"','"+clyj+"','"+clsj+"');return false;\" style=\"cursor:hand\">");
					spHtml.append("<font color=\"blue\">" + lx + "</font>");
					spHtml.append("</a>");
				}	
				spHtml.append("</td>");
				
				spHtml.append("</tr>");
			}
		}

		return spHtml.toString();
	}
	
	/**
	 * ����ҵ�Ͷ����Ϣ�б�
	 * 
	 * @author ΰ�����
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public ArrayList<String[]> getMytsList(PjpyStuForm model, User user)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		ArrayList<String[]> list = dao.getMytsList(model, user);

		return list;
	}
	
	/**
	 * ����ҵ�Ͷ��Html
	 * 
	 * @author ΰ�����
	 */
	public String getMytsHtml(SearchRsModel rsModel, PjpyStuForm model,
			ArrayList<String[]> rsArrList,User user) {

		// ������Ŀ
		String czxm = model.getCzxm();
		// IE�汾
		String ie = rsModel.getIe();
		// ѧ��
		String xn = model.getXn();
		// ѧ��
		String xq = model.getXq();
		// ��Ŀ
		String xmdm = model.getXmdm();
		
		StringBuilder spHtml = new StringBuilder();

		if (rsArrList != null && rsArrList.size() > 0) {

			for (int i = 0; i < rsArrList.size(); i++) {

				String[] rs = rsArrList.get(i);
				
				spHtml.append("<tr onclick=\"rowOnClick(this);\" ondblclick=\"\">");

				for (int j = 0; j < rs.length-7; j++) {
					spHtml.append("<td align=\"left\" nowrap=\"nowrap\" style=\"width:20%;\" ");
					
					// IE8
					if ("5.8".equalsIgnoreCase(ie)) {
						spHtml.append("height=\"28.5px\"");
					} else {
						spHtml.append("height=\"29px\"");
					}
					
					if(j == 3){
						String tsnr = rs[rs.length-3];
						spHtml.append(" title=\""+tsnr+"\" ");
					}
				
					spHtml.append(">");
					
					spHtml.append(rs[j]);
					spHtml.append("</td>");
				}
				
				// ����״̬
				String lx = rs[rs.length-1];
	
				spHtml.append("<td align=\"left\" nowrap=\"nowrap\" style=\"width:20%;\" > ");
				if("δ����".equalsIgnoreCase(lx)){
					spHtml.append("δ����");
				}else{
					
					String tsnr = rs[rs.length-3];
					String clyj = rs[rs.length-2];
					
					String tssj = rs[rs.length-9];
					String clr = rs[rs.length-8];
					String clsj = rs[rs.length-6];
					
					spHtml.append("<a href\"#\" onclick=\"showXstsDiv('"+tsnr+"','"+tssj+"','"+clr+"','"+clyj+"','"+clsj+"');return false;\" style=\"cursor:hand\">");
					spHtml.append("<font color=\"blue\">" + lx + "</font>");
					spHtml.append("</a>");
				}	
				spHtml.append("</td>");
				
				spHtml.append("</tr>");
			}
		}

		return spHtml.toString();
	}
	
	/**
	 * ����ɷ����
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	public Boolean checkKfcl(PjpyStuForm model) {

		// ����ID
		String sqid = model.getSqid();

		// ��ĿID
		String xmdm = model.getXmdm();

		String pk = "id||bzrsh";
		String pkValue = sqid + "δ���";

		boolean flag = isExists(xmdm, pk, pkValue);

		return flag;

	}
	
	/**
	 *  ɾ�������¼
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	public Boolean deleteSqjl(PjpyStuForm model,User user) {

		// ����ID
		String sqid = model.getSqid();

		// ��ĿID
		String xmdm = model.getXmdm();

		boolean flag = false;
		
		// ������Ŀ��˱�
		String tableName = xmdm;
		// ����
		String pk = "id";
		// ����ֵ
		String[] pkValue = new String[] { sqid };

		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(tableName);
		saveForm.setPk(pk);
		saveForm.setPkValue(pkValue);
		
		try {
			flag = delInfoInDb(saveForm, model, user);
		} catch (Exception e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}

		return flag;

	}
}