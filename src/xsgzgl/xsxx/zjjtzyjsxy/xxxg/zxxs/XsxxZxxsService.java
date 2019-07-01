package xsgzgl.xsxx.zjjtzyjsxy.xxxg.zxxs;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.comm.CommService;
import xgxt.comm.SearchRsModel;
import xgxt.form.User;
import xsgzgl.comm.BasicService;
import xsgzgl.xsxx.general.XsxxGeneralForm;
import xsgzgl.xsxx.general.inter.XsxxZxxsInterface;
import xsgzgl.xsxx.general.zxxs.XsxxZxxsModel;
import xsgzgl.xsxx.grxx.XsxxGrxxDAO;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ѧ����Ϣ_��Уѧ��_�㽭��ͨ��ʦ_Service��
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author ΰ�����
 * @version 1.0
 */

public class XsxxZxxsService extends CommService implements XsxxZxxsInterface {

	XsxxZxxsDAO dao = new XsxxZxxsDAO();

	/**
	 * ��ñ�ͷ�ļ�(��Уѧ��)
	 * 
	 * @author ΰ�����
	 */
	public List<HashMap<String, String>> getXsxxZxxsTop(XsxxZxxsModel model,
			User user) {
		DAO dao = DAO.getInstance();

		String[] en = new String[] { "pk", "xh", "xm", "xb", "nj", "bjmc",
				"xjztm" };
		String[] cn = new String[] { "", "ѧ��", "����", "�Ա�", "�꼶", "�༶", "ѧ��״̬" };

		List<HashMap<String, String>> topTr = dao.arrayToList(en, cn);

		return topTr;
	}

	/**
	 * ��ý����(��Уѧ��)
	 * 
	 * @author ΰ�����
	 */
	public ArrayList<String[]> getXsxxZxxsList(XsxxGeneralForm myForm,
			XsxxZxxsModel model, User user) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		
		return dao.getXsxxZxxsList(myForm, user);
	}

	/**
	 * ���������(��Уѧ��)
	 * 
	 * @author ΰ�����
	 */
	public String createXsxxZxxsHTML(SearchRsModel rsModel,
			XsxxZxxsModel model, ArrayList<String[]> rsArrList, User user) {


		BasicService basicService=new BasicService();
		// IE�汾
		String ie = rsModel.getIe();

		StringBuilder html = new StringBuilder();

		if (rsArrList != null && rsArrList.size() > 0) {

			for (int i = 0; i < rsArrList.size(); i++) {

				String[] rs = rsArrList.get(i);

				String pk = rs[0];
				
				html.append("<tr onclick=\"rowOnClick(this);\" ");
				html.append("style=\"cursor:hand\" ");
				html.append("ondblclick=\"showXsxxDetail('"+pk+"')\">");
				html.append("<td align=\"center\" width=\"5px\"");
				html.append(">");
				html.append("<input type=\"checkbox\" name=\"primarykey_checkVal\" ");
				html.append("value=\"" + pk + "\"/>");
				html.append("</td>");
				
				for (int j = 1; j < rs.length; j++) {
					html.append("<td align=\"left\" nowrap=\"nowrap\" style=\"\" ");
					
					// IE8
					if ("5.8".equalsIgnoreCase(ie)) {
						html.append("height=\"28.5px\"");
					} else {
						html.append("height=\"29px\"");
					}
					html.append(">");

					html.append(basicService.replaceHtml(rs[j]));
					html.append("</td>");
				}

				html.append("</tr>");
			}
		}
		
		return html.toString();
	
	}
	
	/**
	 * ���ѧ��������Ϣ
	 * 
	 * @author ΰ�����
	 */
	public HashMap<String, String> getZxxsInfo(XsxxZxxsModel model, User user) {

		// ѧ��
		String xh = model.getXh();

		HashMap<String, String> map = dao.getZxxsInfo(xh, user);

		return map;
	}

	/**
	 * ����ѧ����Ϣ
	 * 
	 * @author ΰ�����
	 */
	public boolean saveXsxx(XsxxZxxsModel model, User user) {

		// �Ƿ���ѧ����Ϣ���д���
		boolean isXsxxExists = isExists("xsxxb", "xh", model.getXh());
		// �Ƿ���ѧ��������Ϣ���д���
		boolean isFzxxExists = isExists("xsfzxxb", "xh", model.getXh());

		boolean flag = true;

		XsxxGrxxDAO grxxDAO = new XsxxGrxxDAO();

		if (!isXsxxExists) {
			try {
				flag = grxxDAO.copyToXsxxb(model.getXh());
			} catch (Exception e) {
				// TODO �Զ����� catch ��
				e.printStackTrace();
			}
		}

		if (!isFzxxExists) {
			try {
				flag = grxxDAO.copyToFzxxb(model.getXh());
			} catch (Exception e) {
				// TODO �Զ����� catch ��
				e.printStackTrace();
			}
		}

		// ����ѧ����Ϣ
		if (flag) {
			flag = updateXsxx(model, user);
		}

		// ���¸�����Ϣ
		if (flag) {
			flag = updateFzxx(model, user);
		}

		return flag;
	}
	
	/**
	 * ����ѧ����Ϣ
	 * 
	 * @author ΰ�����
	 */
	public boolean updateXsxx(XsxxZxxsModel model, User user) {

		String xh = model.getXh();// ѧ��
		String xm = model.getXm();// ����
		String xb = model.getXb();// �Ա�
		String csrq = model.getCsrq();// ��������
		String mz = model.getMz();// ����
		String zzmm = model.getZzmm();// ������ò
		String sfzh = model.getSfzh();// ���֤��
		String pycc = model.getPycc();// �������
		String hkszd = model.getHkszd();// �������ڵ�
		String jg = model.getJg();// ����
		String syd = model.getSyd();// ��Դ��
		String bjdm = model.getBjdm();// ���ڲ���
		String xjztm = model.getXjztm();// ѧ��״̬
		String xz = model.getXz();// ѧ��
		String sfzc = model.getSfzc();// �Ƿ�ע��
		String sfzd = model.getSfzd();// �Ƿ��߶�
		String rxrq = model.getRxrq();// ��ѧ����
		String bysj = model.getBysj();// ��ҵʱ��
		String sfbys = model.getSfbys();// �Ƿ��ҵ��
		String sfyby = model.getSfyby();// �Ƿ��ѱ�ҵ
		String sfzx = model.getSfzx();// �Ƿ���У
		String lxdh = model.getLxdh();// ��ϵ�绰
		String sjhm = model.getSjhm();// �ֻ�����
		String qqhm = model.getQqhm();// QQ����
		String dzyx = model.getDzyx();// ��������
		String yhdm = model.getYhdm();// ����
		String yhkh = model.getYhkh();// ���п���
		String fdyxm = model.getFdyxm();// ����Ա����
		String kh = model.getKh();// һ��ͨ��
		String sg = model.getSg();// ���
		String tz = model.getTz();// ����
		String xmpy = model.getXmpy();// ����ƴ��
		String cym = model.getCym();// ������
		String tc = model.getTc();// �س�
		String kslb = model.getKslb();// �������
		String rxfs = model.getRxfs();// ��ѧ��ʽ
		String pyfs = model.getPyfs();// ������ʽ
		String jkzk = model.getJkzk();// ����״��

		boolean flag = true;

		// ����ѧ����Ϣ

		try {
			flag = dao.updateXsxxb(xh, xm, xb, csrq, mz, zzmm, sfzh, pycc,
					hkszd, jg, syd, bjdm, xjztm, xz, sfzc, sfzd, rxrq, bysj,
					sfbys, sfyby, sfzx, lxdh, sjhm, qqhm, dzyx, yhdm, yhkh,
					fdyxm, kh, sg, tz, xmpy, cym, tc, kslb, rxfs, pyfs, jkzk,
					user);
		} catch (Exception e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}

		return flag;
	}
	
	/**
	 * ���¸�����Ϣ
	 * 
	 * @author ΰ�����
	 */
	public boolean updateFzxx(XsxxZxxsModel model, User user) {

		String xh = model.getXh();// ѧ��
		String lxdh1 = model.getLxdh1();// ��ͥ�绰
		String jtszd = model.getJtszd();// ��ͥ��ַ
		String yb = model.getYb();// ��������
		String jjzk = model.getJjzk();// ��ͥ����״��
		String jtcy1_xm = model.getJtcy1_xm();// ��ͥ��Ա1_����
		String jtcy1_gx = model.getJtcy1_gx();// ��ͥ��Ա1_��ϵ
		String jtcy1_nl = model.getJtcy1_nl();// ��ͥ��Ա1_����
		String jtcy1_sfzh = model.getJtcy1_sfzh();// ��ͥ��Ա1_���֤��
		String jtcy1_zy = model.getJtcy1_zy();// ��ͥ��Ա1_ְҵ
		String jtcy1_zw = model.getJtcy1_zw();// ��ͥ��Ա1_ְ��
		String jtcy1_lxdh1 = model.getJtcy1_lxdh1();// ��ͥ��Ա1_�����绰
		String jtcy1_lxdh2 = model.getJtcy1_lxdh2();// ��ͥ��Ա1_���˵绰
		String jtcy1_gzdz = model.getJtcy1_gzdz();// ��ͥ��Ա1_������ַ
		String jtcy1_mz = model.getJtcy1_mz();// ��ͥ��Ա1_����
		String jtcy1_zzmm = model.getJtcy1_zzmm();// ��ͥ��Ա1_������ò
		String jtcy2_xm = model.getJtcy2_xm();// ��ͥ��Ա2_����
		String jtcy2_gx = model.getJtcy2_gx();// ��ͥ��Ա2_��ϵ
		String jtcy2_nl = model.getJtcy2_nl();// ��ͥ��Ա2_����
		String jtcy2_sfzh = model.getJtcy2_sfzh();// ��ͥ��Ա2_���֤��
		String jtcy2_zy = model.getJtcy2_zy();// ��ͥ��Ա2_ְҵ
		String jtcy2_zw = model.getJtcy2_zw();// ��ͥ��Ա2_ְ��
		String jtcy2_lxdh1 = model.getJtcy2_lxdh1();// ��ͥ��Ա2_�����绰
		String jtcy2_lxdh2 = model.getJtcy2_lxdh2();// ��ͥ��Ա2_���˵绰
		String jtcy2_gzdz = model.getJtcy2_gzdz();// ��ͥ��Ա2_������ַ
		String jtcy2_mz = model.getJtcy2_mz();// ��ͥ��Ա2_����
		String jtcy2_zzmm = model.getJtcy2_zzmm();// ��ͥ��Ա2_������ò
		String jtcy3_xm = model.getJtcy3_xm();// ��ͥ��Ա3_����
		String jtcy3_gx = model.getJtcy3_gx();// ��ͥ��Ա3_��ϵ
		String jtcy3_nl = model.getJtcy3_nl();// ��ͥ��Ա3_����
		String jtcy3_sfzh = model.getJtcy3_sfzh();// ��ͥ��Ա3_���֤��
		String jtcy3_zy = model.getJtcy3_zy();// ��ͥ��Ա3_ְҵ
		String jtcy3_zw = model.getJtcy3_zw();// ��ͥ��Ա3_ְ��
		String jtcy3_lxdh1 = model.getJtcy3_lxdh1();// ��ͥ��Ա3_�����绰
		String jtcy3_lxdh2 = model.getJtcy3_lxdh2();// ��ͥ��Ա3_���˵绰
		String jtcy3_gzdz = model.getJtcy3_gzdz();// ��ͥ��Ա3_������ַ
		String jtcy3_mz = model.getJtcy3_mz();// ��ͥ��Ա3_����
		String jtcy3_zzmm = model.getJtcy3_zzmm();// ��ͥ��Ա3_������ò

		boolean flag = true;

		// ���¸�����Ϣ
		try {
			flag = dao.updateFzxxb(xh, lxdh1, jtszd, yb, jjzk, jtcy1_xm,
					jtcy1_gx, jtcy1_nl, jtcy1_sfzh, jtcy1_zy, jtcy1_zw,
					jtcy1_lxdh1, jtcy1_lxdh2, jtcy1_gzdz, jtcy1_mz, jtcy1_zzmm,
					jtcy2_xm, jtcy2_gx, jtcy2_nl, jtcy2_sfzh, jtcy2_zy,
					jtcy2_zw, jtcy2_lxdh1, jtcy2_lxdh2, jtcy2_gzdz, jtcy2_mz,
					jtcy2_zzmm, jtcy3_xm, jtcy3_gx, jtcy3_nl, jtcy3_sfzh,
					jtcy3_zy, jtcy3_zw, jtcy3_lxdh1, jtcy3_lxdh2, jtcy3_gzdz,
					jtcy3_mz, jtcy3_zzmm, user);
		} catch (Exception e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}

		return flag;
	}

	/**
	 * �����ҵ����
	 * 
	 * @author ţB����
	 */
	public boolean saveBycl(XsxxZxxsModel model, User user) throws Exception {

		//��ʼ��ѧ����Ϣ����������
		boolean flag=xsxxInit(model, user);
		
		if(flag){
			
			flag=dao.saveBycl(model, user);
		}
		
		return flag;
	}
	
	/**
	 * ѧ����Ϣ��ʼ��
	 * 
	 * @author ţB����
	 */
	public boolean xsxxInit(XsxxZxxsModel model, User user) throws Exception{
		
		return dao.xsxxInit(model, user);
	}

	/**
	 * ��ȡѧ����Ϣ(��ʷѧ��) 2012.4.12 qlj
	 */
	public ArrayList<String[]> getXsxxLsxsList(XsxxGeneralForm myForm,
			XsxxZxxsModel model, User user) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

		return dao.getXsxxLsxsList(myForm, user);
	}
	
}