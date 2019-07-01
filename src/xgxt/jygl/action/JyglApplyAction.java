package xgxt.jygl.action;

/*
 * �������� 2006-9-16
 *
 *  Ҫ���Ĵ����ɵ��ļ���ģ�壬��ת��
 * ���� �� ��ѡ�� �� Java �� ������ʽ �� ����ģ��
 */

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import oracle.sql.CLOB;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import xgxt.DAO.DAO;
import xgxt.DAO.ExcelMB;
import xgxt.DAO.SxjyDAO;
import xgxt.action.ApplyAction;
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.base.Excel2Oracle;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.dtjs.czxx.dyxx.DyxxDAO;
import xgxt.form.CommanForm;
import xgxt.jygl.dao.ByqxDao;
import xgxt.jygl.dao.BzgzzyDao;
import xgxt.jygl.dao.BzpyfsDao;
import xgxt.jygl.dao.DqlxDao;
import xgxt.jygl.dao.DwdqDao;
import xgxt.jygl.dao.Dwxz2Dao;
import xgxt.jygl.dao.DwxzDao;
import xgxt.jygl.dao.FileTool;
import xgxt.jygl.dao.HyflDao;
import xgxt.jygl.dao.JyglwjscDao;
import xgxt.jygl.dao.JzzbzDao;
import xgxt.jygl.dao.SydqDao;
import xgxt.jygl.dao.SydzgbmDao;
import xgxt.jygl.dao.XbDao;
import xgxt.jygl.dao.XlDao;
import xgxt.jygl.dao.YjspyfsDao;
import xgxt.jygl.dao.YjszyDao;
import xgxt.jygl.dao.ZgbmDao;
import xgxt.jygl.dao.ZzmmDao;
import xgxt.jygl.service.JyglService;
import xgxt.studentInfo.dao.StuInfoDAO;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.ExcelMethods;
import xgxt.utils.Fdypd;
import xgxt.utils.FormModleCommon;
import xgxt.utils.GetTime;
import xgxt.utils.Pages;
import xgxt.utils.RowidToPk;
import xgxt.utils.dealDate;
import xgxt.utils.String.StringUtils;
import xgxt.xljk.lrh_Util.util.lrh_commen_util;

import common.Globals;

/**
 * @author bat_zzj
 */

public class JyglApplyAction extends ApplyAction {

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CommanForm chkUser = (CommanForm) form;
		try {
			int i = Base.chkTimeOut(request.getSession());
			if (i <= 2) {
				chkUser.setErrMsg("��½��ʱ�������µ�½��");
				return new ActionForward("/login.jsp", false);
			}

			HttpSession session = request.getSession();
			String userType = "";
			if (session == null) {
				request.setAttribute("errMsg", "sadfsdf");
				return mapping.findForward("false");
			}
			userType = session.getAttribute("userOnLine").toString();

			ActionForward myActFwd = null;
			String myAct = mapping.getParameter();

			if (myAct.equalsIgnoreCase("grjlApply")) {
				myActFwd = grjlApply(mapping, form, request, response, userType); // ���ڸ��˼���¼�����
			} else if (myAct.equalsIgnoreCase("grjlQuerySh")) {
				myActFwd = grjlQuerySh(mapping, form, request, response,
						userType); // ���ڸ��˼�����ѯ���ҳ�� ��ѯ ����ͨ�������
			} else if (myAct.equalsIgnoreCase("grjlViewMoreinfo")) {
				myActFwd = grjlViewMoreinfo(mapping, form, request, response); // ���˼�����ϸ�鿴
			} else if (myAct.equalsIgnoreCase("jyglGrjlsh")) {
				myActFwd = jyglGrjlsh(mapping, form, request, response); // ���˼������ڵ��������
			} else if (myAct.equalsIgnoreCase("grjlUpdate")) {
				myActFwd = grjlUpdate(mapping, form, request, response); // ���˼����޸�
			} else if (myAct.equalsIgnoreCase("grjlResultQuery")) {
				myActFwd = grjlResultQuery(mapping, form, request, response,
						userType); // ���˼��������ѯ

			} else if (myAct.equalsIgnoreCase("zpxxfb")) {
				myActFwd = zpxxfb(mapping, form, request, response, userType); // ��Ƹ��Ϣ����
			} else if (myAct.equalsIgnoreCase("zpxxquery")) {
				myActFwd = zpxxquery(mapping, form, request, response, userType); // ��Ƹ��Ϣ��ѯ��ɾ��
			} else if (myAct.equalsIgnoreCase("zpxxViewMoreinfo")) {
				myActFwd = zpxxViewMoreinfo(mapping, form, request, response); // ��Ƹ��Ϣ��ϸ��ѯ
			} else if (myAct.equalsIgnoreCase("zpxxupdate")) {
				myActFwd = zpxxupdate(mapping, form, request, response); // ��Ƹ��Ϣ�޸�
			} else if (myAct.equalsIgnoreCase("zpxxSendGrjl")) {
				myActFwd = zpxxSendGrjl(mapping, form, request, response); // Ͷ������ҳ���,Ͷ��
			} else if (myAct.equalsIgnoreCase("zpxxResultQuery")) {
				myActFwd = zpxxResultQuery(mapping, form, request, response,
						userType); // ��ҵ�鿴���˼���ҳ���
			} else if (myAct.equalsIgnoreCase("zpxxViewMoreGrjlInfo")) {
				myActFwd = zpxxViewMoreGrjlInfo(mapping, form, request,
						response); // ��ҵ�鿴���˼���ϸ��Ϣ
			} else if (myAct.equalsIgnoreCase("zpxxReturnMess")) {
				myActFwd = zpxxReturnMess(mapping, form, request, response,
						userType); // ��ҵ�������
			} else if (myAct.equalsIgnoreCase("zpxxReturnMessQuery")) {
				myActFwd = zpxxReturnMessQuery(mapping, form, request,
						response, userType); // ��ҵ���������ѯ
			} else if (myAct.equalsIgnoreCase("zpxxReturnMessMoreQuery")) {
				myActFwd = zpxxReturnMessMoreQuery(mapping, form, request,
						response, userType); // ��ҵ���������ѯ(�鿴��ϸ��Ϣ)

			} else if (myAct.equalsIgnoreCase("turndmkqueryjsp")) {
				myActFwd = turndmkqueryjsp(mapping, form, request, response,
						userType); // ��ת������ѯ��
			} else if (myAct.equalsIgnoreCase("dmkquery")) {
				myActFwd = dmkquery(mapping, form, request, response); // ������ѯ
			} else if (myAct.equalsIgnoreCase("bysxxapply")) {

				if (Globals.XXDM_FJGCXY.equals(Base.xxdm)) {

				}

				myActFwd = bysxxapply(mapping, form, request, response,
						userType); // ѧ����Ϣҳ���
			} else if (myAct.equalsIgnoreCase("bysjbxxbSave")) {
				myActFwd = bysxxapply(mapping, form, request, response,
						userType); // ѧ����������Ϣ��¼��
			} else if (myAct.equalsIgnoreCase("bysxxplsb")) {
				myActFwd = bysxxplsb(mapping, form, request, response, userType); // ��ҵ����Ϣ������¼��
			} else if (myAct.equalsIgnoreCase("JyglDataSearch")) {
				myActFwd = JyglDataSearch(mapping, form, request, response,
						userType); // ѧ����������Ϣ��ѯ
			} else if (myAct.equalsIgnoreCase("JyglBysjbxxbDel")) {
				myActFwd = JyglBysjbxxbDel(mapping, form, request, response); // ѧ����������Ϣɾ��
			} else if (myAct.equalsIgnoreCase("JyglViewMoreinfo")) {
				myActFwd = JyglViewMoreinfo(mapping, form, request, response); // ѧ������ϸ��Ϣ��ѯ
			} else if (myAct.equalsIgnoreCase("JyglInfoUpdate")) {
				myActFwd = JyglInfoUpdate(mapping, form, request, response); // ѧ����Ϣ�޸�
			} else if (myAct.equalsIgnoreCase("jyxyInput")) {
				myActFwd = jyxyInput(mapping, form, request, response, userType); // ��ҵЭ����������Ϣ���뼰Э�鱣��
			} else if (myAct.equalsIgnoreCase("jyxyTurnInfo")) {
				myActFwd = jyxyTurnInfo(mapping, form, request, response,
						userType); // ͨ����ѯѧ����Ϣ��д��ҵЭ��
			} else if (myAct.equalsIgnoreCase("JyglQueryStuInfo")) {
				myActFwd = JyglQueryStuInfo(mapping, form, request, response); // ͨ������ѧ����ѯ����������дѧ���ϱ���Ϣ
			} else if (myAct.equalsIgnoreCase("JyxyDataSearch")) {
				myActFwd = JyxyDataSearch(mapping, form, request, response,
						userType); // ��ҵЭ��ģ���ѯ��ɾ����
			} else if (myAct.equalsIgnoreCase("JyglJyxyViewMoreinfo")) {
				myActFwd = JyglJyxyViewMoreinfo(mapping, form, request,
						response); // ��ҵЭ����ϸ��Ϣ�Ĳ�ѯ
			} else if (myAct.equalsIgnoreCase("jyglJyxyFdysh")) {
				myActFwd = jyglJyxyFdysh(mapping, form, request, response); // ��ҵЭ�鸨��Ա���
			} else if (myAct.equalsIgnoreCase("jyglJyxyXxsh")) {
				myActFwd = jyglJyxyXxsh(mapping, form, request, response); // ��ҵЭ��ѧУ���
			} else if (myAct.equalsIgnoreCase("jyglJyxyUpdate")) {
				myActFwd = jyglJyxyUpdate(mapping, form, request, response); // ��ҵЭ���޸�
			} else if (myAct.equalsIgnoreCase("jyglJyxyResultQuery")) {
				myActFwd = jyglJyxyResultQuery(mapping, form, request,
						response, userType); // ��ҵЭ����˽����ѯ
			} else if (myAct.equalsIgnoreCase("jyglJyxyLqdj")) {
				myActFwd = jyglJyxyLqdj(mapping, form, request, response); // ��ҵЭ����ȡ�Ǽ�
			} else if (myAct.equalsIgnoreCase("viewJyxyLqdjInfo")) {
				myActFwd = viewJyxyLqdjInfo(mapping, form, request, response); // ��ҵЭ����ȡ�Ǽ���ϸ��Ϣ�鿴
			} else if (myAct.equalsIgnoreCase("jyxyBlsq")) {
				myActFwd = jyxyBlsq(mapping, form, request, response); // ��ҵЭ�鲹������
			} else if (myAct.equalsIgnoreCase("jyxyBlsqQuery")) {
				myActFwd = jyxyBlsqQuery(mapping, form, request, response); // ��ҵЭ�鲹���������
			} else if (myAct.equalsIgnoreCase("jyxyBlsqViewMore")) {
				myActFwd = jyxyBlsqViewMore(mapping, form, request, response); // ��ҵЭ�鲹��������ϸ�鿴-���
			} else if (myAct.equalsIgnoreCase("dwxxmkwh")) {
				myActFwd = dwxxmkwhViewMore(mapping, form, request, response); // ��λ��Ϣά��--���ʴ�ѧ
			} else if (myAct.equalsIgnoreCase("dwlfxx")) {
				myActFwd = dwlfxxViewMore(mapping, form, request, response); // ��λ������Ϣ--���ʴ�ѧ
			} else if (myAct.equalsIgnoreCase("zpxxdw")) {
				myActFwd = zpxxdwViewMore(mapping, form, request, response); // ��Ƹ��Ϣˢ��λ
			} else if (myAct.equalsIgnoreCase("xsxxsh")) {
				myActFwd = xsxxsh(mapping, form, request, response, userType); // ��Ƹ��Ϣˢ��λ
			} else if (myAct.equalsIgnoreCase("zxzxshtz")) {
				myActFwd = zxzxshtz(mapping, form, request, response); // ��ѯԤԼ����ѧ���ظ���
			} else if (myAct.equalsIgnoreCase("zxzxshcx")) {
				myActFwd = zxzxshcx(mapping, form, request, response); // ��ѯԤԼ����ѧ���ظ���

			} else if (myAct.equalsIgnoreCase("jyzxTeaInput")) {
				myActFwd = jyzxTeaInput(mapping, form, request, response,
						userType); // ��ҵ��ѯ��ʦ�Ǽ�
			} else if (myAct.equalsIgnoreCase("jyzxYuyuequery")) {
				myActFwd = jyzxYuyuequery(mapping, form, request, response,
						userType); // ѧ����ѯԤԼ(��ѯ ɾ��)
			} else if (myAct.equalsIgnoreCase("jyzxViewMoreInfo")) {
				myActFwd = jyzxViewMoreInfo(mapping, form, request, response); // ѧ����ѯ������ϸ��Ϣ��
			} else if (myAct.equalsIgnoreCase("jyzxZxsUpdate")) {
				myActFwd = jyzxZxsUpdate(mapping, form, request, response); // ��ѯʦ��Ϣ�޸�
			} else if (myAct.equalsIgnoreCase("jyzxZxsYuyue")) {
				myActFwd = jyzxZxsYuyue(mapping, form, request, response,
						userType); // ��ѯʦԤԼ
			} else if (myAct.equalsIgnoreCase("jyzxZxyygl")) {
				myActFwd = jyzxZxyygl(mapping, form, request, response,
						userType); // ��ѯԤԼ����ҳ���,��ѯ��ɾ����
			} else if (myAct.equalsIgnoreCase("zxyyglViewMoreInfo")) {
				myActFwd = zxyyglViewMoreInfo(mapping, form, request, response); // ��ѯԤԼ��������ϸ��Ϣ��
			} else if (myAct.equalsIgnoreCase("answerYuyue")) {
				myActFwd = answerYuyue(mapping, form, request, response); // ��ѯԤԼ������ʦ�ظ���
			} else if (myAct.equalsIgnoreCase("updateYuyue")) {
				myActFwd = updateYuyue(mapping, form, request, response); // ��ѯԤԼ�����޸ģ�
			} else if (myAct.equalsIgnoreCase("jyzxResultQuery")) {
				myActFwd = jyzxResultQuery(mapping, form, request, response,
						userType); // ��ѯԤԼ���������ѯ��
			} else if (myAct.equalsIgnoreCase("answerYuyueStu")) {
				myActFwd = answerYuyueStu(mapping, form, request, response); // ��ѯԤԼ����ѧ���ظ���

			} else if (myAct.equalsIgnoreCase("byqxInput")) {
				myActFwd = byqxInput(mapping, form, request, response, userType); // ��ҵȥ��¼��
			} else if (myAct.equalsIgnoreCase("byqxQuery")) {
				myActFwd = byqxQuery(mapping, form, request, response, userType); // ��ҵȥ���ѯ(ҳ���,ɾ����
			} else if (myAct.equalsIgnoreCase("byqxViewMoreQuery")) {
				myActFwd = byqxViewMoreQuery(mapping, form, request, response); // ��ҵȥ����ϸ��Ϣ�鿴
			} else if (myAct.equalsIgnoreCase("jyglByqxUpdate")) {
				myActFwd = jyglByqxUpdate(mapping, form, request, response); // ��ҵȥ���޸�
			} else if (myAct.equalsIgnoreCase("jyglByqxSh")) {
				myActFwd = jyglByqxSh(mapping, form, request, response); // ��ҵȥ�����

			} else if (myAct.equalsIgnoreCase("jyglgetfile")) {
				myActFwd = jyglgetfile(mapping, form, request, response); // �ļ��ϴ�
			} else if (myAct.equalsIgnoreCase("jygldelfile")) {
				myActFwd = jygldelfile(mapping, form, request, response); // �ļ�ɾ��
			} else if (myAct.equalsIgnoreCase("jyglteafiledown")) {
				myActFwd = jyglteafiledown(mapping, form, request, response); // ��ʦ�������
			} else if (myAct.equalsIgnoreCase("jyglstufiledown")) {
				myActFwd = jyglstufiledown(mapping, form, request, response); // ѧ���������
			} else if (myAct.equalsIgnoreCase("showzcwjlist")) {
				myActFwd = showzcwjlist(mapping, form, request, response,
						userType); // ��ʾ�����ļ�����ҳ��
			} else if (myAct.equalsIgnoreCase("showmorezcwj")) {
				myActFwd = showmorezcwj(mapping, form, request, response); // ��ʾ�����ļ���ϸ�����Լ����������ļ�
			} else if (myAct.equalsIgnoreCase("zcwjupdate")) {
				myActFwd = zcwjupdate(mapping, form, request, response); // �����ļ��޸�
			} else if (myAct.equalsIgnoreCase("zcwjquery")) {
				myActFwd = zcwjquery(mapping, form, request, response); // �����ļ���ѯ
			} else if (myAct.equalsIgnoreCase("jyglexpData")) {
				myActFwd = jyglexpData(mapping, form, request, response); // ��ҵ�������ݵ���

			} else if (myAct.equalsIgnoreCase("jyzpWeiHu")) {
				myActFwd = jyzpWeiHu(mapping, form, request, response, userType); // ��ҵ��Ƹά��
			} else if (myAct.equalsIgnoreCase("jyxyWeiHu")) {
				myActFwd = jyxyWeiHu(mapping, form, request, response, userType); // ��ҵЭ��ά��
			} else if (myAct.equalsIgnoreCase("userczmx")) {
				myActFwd = userczmx(mapping, form, request, response); // �û�������ϸ����ѯ��ɾ����
			} else if (myAct.equalsIgnoreCase("onebutton")) {
				myActFwd = onebutton(mapping, form, request, response); // һ��ͳ��
			} else if (myAct.equalsIgnoreCase("stuinfo")) {
				myActFwd = stuinfo(mapping, form, request, response); // ѧ����ҵ��Ϣ
			} else if (myAct.equalsIgnoreCase("stuinfoquery")) {
				myActFwd = stuinfoquery(mapping, form, request, response); // ѧ��������Ϣ��ѯ���������ְҵ��
			} else if (myAct.equalsIgnoreCase("stuinfoInput")) {
				myActFwd = stuinfoInput(mapping, form, request, response); // ѧ����ҵ��Ϣ¼��
			} else if (myAct.equalsIgnoreCase("stuinfomorequery")) {
				myActFwd = stuinfomorequery(mapping, form, request, response); // ѧ����ҵ��Ϣ��ϸ�鿴
			} else if (myAct.equalsIgnoreCase("stuinfoUpdate")) {
				myActFwd = stuinfoUpdate(mapping, form, request, response); // ѧ����ҵ��Ϣ�޸�
			} else if (myAct.equalsIgnoreCase("xsjytj")) {
				myActFwd = xsjytj(mapping, form, request, response); // ѧ����ҵͳ��
			} else if (myAct.equalsIgnoreCase("dwxxInput")) {
				myActFwd = dwxxinfoInput(mapping, form, request, response); // ��λ��Ϣ¼��
			} else if (myAct.equalsIgnoreCase("dwxxUpdate")) {
				myActFwd = dwxxinfoUpdate(mapping, form, request, response); // ��λ��Ϣ¼��
			} else if (myAct.equalsIgnoreCase("jyqktj")) {
				myActFwd = jyqktj(mapping, form, request, response); // ��λ��Ϣ¼��
			} else if (myAct.equalsIgnoreCase("ahgc_jyglBysxxSb")) {
				myActFwd = ahgcjyglBysxxSb(mapping, form, request, response); // ���չ��̱�ҵ����Ϣ�ϱ�
			} else if (myAct.equalsIgnoreCase("zjlgjyxyexp")) {
				myActFwd = zjlgjyxyexp(mapping, form, request, response); // ���չ��̱�ҵ����Ϣ�ϱ�
			} else if (myAct.equalsIgnoreCase("jyxxlr")) {
				myActFwd = jyxxlr(mapping, form, request, response); // ���չ��̱�ҵ����Ϣ�ϱ�
			} else if (myAct.equalsIgnoreCase("jyxxcx")) {
				myActFwd = jyxxcx(mapping, form, request, response); // ���չ��̱�ҵ����Ϣ�ϱ�
			} else if (myAct.equalsIgnoreCase("jyxxtj")) {
				myActFwd = jyxxtj(mapping, form, request, response); // ���չ��̱�ҵ����Ϣ�ϱ�
			} else if (myAct.equalsIgnoreCase("ydtj")) {
				myActFwd = ydtj(mapping, form, request, response);
			}
			return myActFwd;
		} catch (Exception e) {
			e.printStackTrace();
			chkUser.setErrMsg("���������жϣ������µ�½��");
		}
		return new ActionForward("/login.jsp", false);
	}

	// ���˼���¼��
	private ActionForward grjlApply(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			String userType) throws Exception {
		DAO dao = DAO.getInstance();
		String xxdm = dao.getXxdm();
		HttpSession session = request.getSession();
		String sql = "";
		CommanForm applyForm = new CommanForm();
		HashMap<String, String> map = new HashMap<String, String>();
		String xh = applyForm.getXh();
		String act = request.getParameter("act");
		String xsxh = request.getParameter("xsxh");
		String[] rs = null;
		String doType = request.getParameter("doType");
		sql = "select zzmmdm,zzmm from dmk_zzmm"; // ������ò
		List zzmmList = dao.getList(sql, new String[] {}, new String[] {
				"zzmmdm", "zzmm" });
		request.setAttribute("zzmmList", zzmmList);
		String tableName = "jygl_grjlb";

		String xxdm1 = (String) request.getSession().getAttribute("xxdm"); // 2ѧУ����
		String xsxh1 = (String) request.getSession().getAttribute("userName");
		if ("12453".equals(xxdm1) && !"".equals(xsxh1)) {
			String[] str = dao.getOneRs(
					"select sfsh from jygl_xsjbxxb where xsxh=?",
					new String[] { xsxh1 }, new String[] { "sfsh" });
			@SuppressWarnings("unused")
			boolean bool = false;
			if (str != null) {
				if ("��ͨ����".equals(str[0])) {
					bool = true;
				} else {
					userType = "shwtg";
					act = "bnsq";
					request.setAttribute("exists", "exists");
				}
			}
		}

		if (userType.equalsIgnoreCase("student")) {
			xh = session.getAttribute("userName").toString();
			sql = "select * from jygl_xsjbxxb where xsxh=?";
			String[] colList = dao
					.getColumnName("select * from jygl_xsjbxxb where 1=2");
			rs = dao.getOneRs(sql, new String[] { xh }, colList);
			if (rs != null) {
				for (int i = 0; i < colList.length; i++) {
					map.put(colList[i].toLowerCase(), rs[i]);
				}
				// ͨ��ѧ�Ų�����
				sql = "select mzmc from mzdmb where mzdm=(select mzdm from bks_xsqtxx where xh=?)";
				rs = dao.getOneRs(sql, new String[] { xh },
						new String[] { "mzmc" });
				if (null != rs) {
					map.put("mz", rs[0]);
				}
				// ��Դ����
				String sydqdm = map.get("sydqdm");
				sql = "select * from dmk_sydq where sydqdm=?";
				rs = dao.getOneRs(sql, new String[] { sydqdm },
						new String[] { "sydq" });
				if (null != rs) {
					map.put("sydq", rs[0]);
				}
				// �������´����֤ת��
				if (map.get("id") != null) {
					if (map.get("id").length() == 18) {
						String csny = map.get("id").substring(6, 14);
						String year1 = csny.substring(0, 4);
						String mon1 = csny.substring(4, 6);
						String day1 = csny.substring(6, 8);
						csny = year1 + "��" + mon1 + "��" + day1 + "��";
						map.put("csny", csny);
					}
					if (map.get("id").length() == 15) {
						String csny = map.get("id").substring(6, 12);
						String year1 = csny.substring(0, 2);
						String mon1 = csny.substring(2, 4);
						String day1 = csny.substring(4, 6);
						csny = "19" + year1 + "��" + mon1 + "��" + day1 + "��";
						map.put("csny", csny);
					}
				}
				// �Ա����ת���Ա�
				if (!(xxdm.equals(Globals.XXDM_YNYS))) {
					if (map.get("xbdm").equals("1")) {
						map.put("xb", "��");
					} else if (map.get("xbdm").equals("2")) {
						map.put("xb", "Ů");
					}

					// ѧ������ת��
					String xldm = map.get("xldm");
					sql = "select * from dmk_xl where xldm=?";
					rs = dao.getOneRs(sql, new String[] { xldm },
							new String[] { "xl" });
					map.put("xl", StringUtils.isArrayNotNull(rs) ? rs[0] : "");
				}
			}
		}
		if ("go".equals(act)) {
			sql = "select * from  jygl_xsjbxxb where xsxh=?";
			String[] colList = dao
					.getColumnName("select * from jygl_xsjbxxb where 1=2");
			rs = dao.getOneRs(sql, new String[] { xsxh }, colList);
			if (rs != null) {
				for (int i = 0; i < colList.length; i++) {
					map.put(colList[i].toLowerCase(), rs[i]);
				}
				// ͨ��ѧ�Ų�����
				sql = "select mzmc from mzdmb where mzdm=(select mzdm from bks_xsqtxx where xh=?)";
				rs = dao.getOneRs(sql, new String[] { xsxh },
						new String[] { "mzmc" });
				if (null != rs) {
					map.put("mz", rs[0]);
				}
				// ��Դ����
				String sydqdm = map.get("sydqdm");
				sql = "select * from dmk_sydq where sydqdm=?";
				rs = dao.getOneRs(sql, new String[] { sydqdm },
						new String[] { "sydq" });
				if (null != rs) {
					map.put("sydq", rs[0]);
				}
				// �������´����֤ת��
				if (map.get("id") != null) {
					if (map.get("id").length() == 18) {
						String csny = map.get("id").substring(6, 14);
						String year1 = csny.substring(0, 4);
						String mon1 = csny.substring(4, 6);
						String day1 = csny.substring(6, 8);
						csny = year1 + "��" + mon1 + "��" + day1 + "��";
						map.put("csny", csny);
					}
					if (map.get("id").length() == 15) {
						String csny = map.get("id").substring(6, 12);
						String year1 = csny.substring(0, 2);
						String mon1 = csny.substring(2, 4);
						String day1 = csny.substring(4, 6);
						csny = "19" + year1 + "��" + mon1 + "��" + day1 + "��";
						map.put("csny", csny);
					}
				}
				// �Ա����ת���Ա�
				if (!(xxdm.equals(Globals.XXDM_YNYS))) {
					if ("1".equals(map.get("xbdm"))) {
						map.put("xb", "��");
					} else if ("2".equals(map.get("xbdm"))) {
						map.put("xb", "Ů");
					}
					// ѧ������ת��
					String xldm = map.get("xldm");
					sql = "select * from dmk_xl where xldm=?";
					rs = dao.getOneRs(sql, new String[] { xldm },
							new String[] { "xl" });
					map.put("xl", rs != null && rs.length > 0 ? rs[0] : "");
				}
			}
		}

		if ("save".equals(doType)) {
			String id = request.getParameter("id"); // ���֤����1
			String idsee = request.getParameter("idsee"); // ���֤�Ƿ���2
			xsxh = request.getParameter("xsxh"); // ѧ��3
			String name = DealString.toGBK(request.getParameter("name")); // ����4
			String xb = DealString.toGBK(request.getParameter("xb")); // �Ա�5
			String csny = DealString.toGBK(request.getParameter("csny")); // ��������6
			String mz = DealString.toGBK(request.getParameter("mz")); // ����7
			String xl = DealString.toGBK(request.getParameter("xl")); // ѧ��8
			String zzmm = DealString.toGBK(request.getParameter("zzmm")); // ������ò9
			String zymc = DealString.toGBK(request.getParameter("zymc")); // רҵ����10
			String fxzymc = DealString.toGBK(request.getParameter("fxzymc")); // ����רҵ����11
			String sydq = DealString.toGBK(request.getParameter("sydq")); // ��Դ����12
			String lxdz = DealString.toGBK(request.getParameter("lxdz")); // ��ϵ��ַ13
			String lxdh = request.getParameter("lxdh"); // ��ϵ�绰14
			String yzbm = request.getParameter("yzbm"); // ��������15
			String email = request.getParameter("email"); // ��������16
			String hjqk = DealString.toGBK(request.getParameter("hjqk")); // �����17
			String xxqk = DealString.toGBK(request.getParameter("xxqk")); // ѧϰ���18
			String xjysjl = DealString.toGBK(request.getParameter("xjysjl")); // У�����Ͻ���19
			String shsjqk = DealString.toGBK(request.getParameter("shsjqk")); // ���ʵ�����20
			String gzjl = DealString.toGBK(request.getParameter("gzjl")); // ��������21
			String grtc = DealString.toGBK(request.getParameter("grtc")); // �����س�22
			String zwtj = DealString.toGBK(request.getParameter("zwtj")); // �����Ƽ�23
			String xxtj = DealString.toGBK(request.getParameter("xxtj")); // ѧУ�Ƽ�24
			String fbsj = (dao
					.getOneRs(
							"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual", // ����ʱ��25
							// //
							// ȡ�����ݿ���ʱ��
							new String[] {}, new String[] { "sdate" }))[0];

			boolean judge = false;
			// if (!"teacher".equals(userType)) {
			@SuppressWarnings("unused")
			boolean bool = StandardOperation.delete("delete from " + tableName
					+ " where xsxh='" + xsxh + "'", tableName, request);
			judge = StandardOperation.insert(tableName, new String[] { "id",
					"idsee", "xsxh", "name", "xb", "csny", "mz", "xl", "zzmm",
					"zymc", "fxzymc", "sydq", "lxdz", "lxdh", "yzbm", "email",
					"hjqk", "xxqk", "xjysjl", "shsjqk", "gzjl", "grtc", "zwtj",
					"xxtj", "fbsj" }, new String[] { id, idsee, xsxh, name, xb,
					csny, mz, xl, zzmm, zymc, fxzymc, sydq, lxdz, lxdh, yzbm,
					email, hjqk, xxqk, xjysjl, shsjqk, gzjl, grtc, zwtj, xxtj,
					fbsj }, request);
			// }
			if (judge) {
				request.setAttribute("save", "ok");

				// ��Ӳ����ļ�¼
				if ("teacher".equals(userType)) {
					String userName = session.getAttribute("userName")
							.toString();
					String whensj = (dao
							.getOneRs(
									"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual",
									new String[] {}, new String[] { "sdate" }))[0]; // �ύʱ��

					StandardOperation.insert("JYGL_USERCZMXB", new String[] {
							"userid", "dowhat", "whichtable", "whichpk",
							"whensj" }, new String[] { userName, "����", "���˼�����",
							"ѧ��:" + xsxh, whensj }, request);
				}
			} else {
				request.setAttribute("save", "no");
			}
		}
		List<String[]> xscjList = new ArrayList<String[]>();
		List<String[]> xscjLists1 = new ArrayList<String[]>();
		String userName = request.getSession().getAttribute("userName")
				.toString();
		List<List<String[]>> xscjLists = new ArrayList<List<String[]>>();
		sql = "select * from cjb where xh=?";
		xscjList = dao.rsToVator(sql, new String[] { userName }, new String[] {
				"kcmc", "cj" });
		int j = 0;
		for (int i = 0; i < xscjList.size(); i++) {
			String[] lists = xscjList.get(i);
			if (j == 0 || (j == i && j != 0 && i != 1)) {
				j++;
				xscjLists1 = new ArrayList<String[]>();
				xscjLists1.add(lists);
			} else {
				j = 0;
				xscjLists1.add(lists);
				xscjLists.add(xscjLists1);
			}
		}
		request.setAttribute("xscjList", xscjLists);
		map.put("stuExists", "yes");
		map.put("userType", userType);
		request.setAttribute("rs", map);
		return mapping.findForward("success");
	}

	// ���˼�����ѯ���
	private ActionForward grjlQuerySh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			String userType) throws Exception {
		// ��ʼ��ҳ�棬���ز�ѯ��Ϣ
		DAO dao = DAO.getInstance();
		String usertyzljl = request.getSession().getAttribute("userType")
				.toString();
		if (usertyzljl.equalsIgnoreCase("stu")) {
			request.setAttribute("errMsg", "ѧ����Ȩ���ʸ�ҳ�棡");
			return new ActionForward("/errMsg.do", false);
		}
		if (usertyzljl.equalsIgnoreCase("xy")) {
			request.setAttribute("errMsg", Base.YXPZXY_KEY+"�û���Ȩ���ʸ�ҳ�棡");
			return new ActionForward("/errMsg.do", false);
		}

		ArrayList<Object> rs = new ArrayList<Object>();
		String[] colList = null;
		String[] colListCN = null;
		String tableName = "jygl_grjlb"; // ��ѯ����
		String doType = request.getParameter("doType");
		String doType2 = request.getParameter("doType2");
		String doType3 = request.getParameter("doType3");
		String act = request.getParameter("act");
		String sql = "";
		String pk = "xsxh"; // ����
		String querry = " where 1=1 "; // ������ʼ��
		String rsNum = "0"; // ���صļ�¼��
		HashMap<String, String> map = new HashMap<String, String>();

		String xsxh = request.getParameter("xsxh"); // ѧ��
		String name = DealString.toGBK(request.getParameter("name")); // ����
		String xb = DealString.toGBK(request.getParameter("xb")); // �Ա�
		String xymc = DealString.toGBK(request.getParameter("xymc")); // ѧԺ
		String nj = request.getParameter("nj"); // �꼶
		String xxsh = DealString.toGBK(request.getParameter("xxsh")); // ѧУ���
		// ���ڷ���ʱ��Ĵ��� ���ڲ�ѯ����
		String xjsj = DealString.toGBK(request.getParameter("xjsj")); // ���ʱ��
		String nowsj = (dao.getOneRs(
				"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual", // ����ʱ��
				// //
				// ȡ�����ݿ���ʱ��
				new String[] {}, new String[] { "sdate" }))[0];
		String shsj = (dao.getOneRs(
				"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual", // ѧУ���ʱ��
				// //
				// ȡ�����ݿ���ʱ��
				new String[] {}, new String[] { "sdate" }))[0];
		String sjyear = nowsj.substring(0, 4);
		String sjmonth = nowsj.substring(4, 6);
		String sjday = nowsj.substring(6, 8);
		String sjqt = nowsj.substring(8, 14);
		String nowsj1 = sjyear + "-" + sjmonth + "-" + sjday;
		dealDate dealdate = new dealDate();
		String beforesj = "";
		if (!"".equals(xjsj)) {
			beforesj = dealdate.getDate2(Integer.parseInt(xjsj), nowsj1);
			beforesj = beforesj.replaceAll("-", "") + sjqt;
		}
		HttpSession session = request.getSession();
		String userName = session.getAttribute("userName").toString();
		String xxdm = (String) session.getAttribute("xxdm");
		sql = "select xm from view_fdyxx where zgh=?";
		String[] fdysf = dao.getOneRs(sql, new String[] { userName },
				new String[] { "xm" });
		// �������ͨ��
		if ("pass".equalsIgnoreCase(doType3)) {
			String pkstring = request.getParameter("pkstring");
			String[] pkstringI = pkstring.split("!!#!!");

			for (int i = 1; i < pkstringI.length; i++) {
				String whichxh = pkstringI[i];
				if ("xy".equals(session.getAttribute("userType").toString())
						&& "12453".equals(xxdm)) {
					sql = "update jygl_grjlb set fdysh='��ͨ����',fdyshr='"
							+ userName + "',fdyshsj='" + shsj
							+ "' where xsxh=?";
				} else {
					sql = "update jygl_grjlb set xxsh='��ͨ����',shperson='"
							+ userName + "',shsj='" + shsj + "' where xsxh=?";
				}
				boolean judge = false;
				judge = dao.runUpdate(sql, new String[] { whichxh });
				if (judge) {
					request.setAttribute("allpass", "ok");
				} else {
					request.setAttribute("allpass", "no");
				}
			}
		}
		// ������˷��
		if ("notpass".equalsIgnoreCase(doType3)) {
			String pkstring = request.getParameter("pkstring");
			String[] pkstringI = pkstring.split("!!#!!");

			for (int i = 1; i < pkstringI.length; i++) {
				String whichxh = pkstringI[i];
				if ("xy".equals(session.getAttribute("userType").toString())
						&& "12453".equals(xxdm)) {
					sql = "update jygl_grjlb set fdysh='δͨ��X',fdyshr='"
							+ userName + "',fdyshsj='" + shsj
							+ "' where xsxh=?";
				} else {
					sql = "update jygl_grjlb set xxsh='δͨ��X',shperson='"
							+ userName + "',shsj='" + shsj + "' where xsxh=?";
				}
				boolean judge = false;
				judge = dao.runUpdate(sql, new String[] { whichxh });
				if (judge) {
					request.setAttribute("allnotpass", "ok");
				} else {
					request.setAttribute("allnotpass", "no");
				}
			}
		}

		// if ("teacher".equals(userType)) {
		// sql = "select usertype,xymc from JYGL_JYXYWHB where username=?";
		// String[] teainfo = dao.getOneRs(sql, new String[] { userName },
		// new String[] { "usertype", "xymc" });
		// String userty = "";
		// if (null != teainfo) {
		// userty = teainfo[0];
		// xymc = teainfo[1];
		// }
		// if ("����Ա".equals(userty)) {
		// map.put("xymc", xymc);
		// request.setAttribute("who", "fudaoyuan");
		// } else {
		// request.setAttribute("who", "teacher");
		// }
		// }

		if ("teacher".equals(userType)) {
			sql = "select usertype,xymc from JYGL_JYXYWHB where username=?";
			String[] teainfo = dao.getOneRs(sql, new String[] { userName },
					new String[] { "usertype", "xymc" });
			String userty = "";

			if (null != teainfo) {
				userty = teainfo[0];
				xymc = teainfo[1];
			}
			if ("����Ա".equals(userty)) {
				map.put("xymc", xymc);
				request.setAttribute("who", "fudaoyuan");
			} else if (StringUtils.isArrayNotNull(fdysf)) {
				request.setAttribute("who", "fudaoyuan");
			} else {
				request.setAttribute("who", "teacher");
			}
		}

		if ("query".equals(doType)) {
			map.put("xsxh", xsxh);
			map.put("name", name);
			map.put("xb", xb);
			map.put("xymc", xymc);
			map.put("nj", nj);
			map.put("xxsh", xxsh);
			map.put("xjsj", xjsj);
		}

		if ("del".equals(doType2)) {
			// ɾ������
			boolean judge = false;
			String pkValue = request.getParameter("pkValue");
			pkValue = pkValue.replaceAll(" ", "+");
			sql = "delete from " + tableName + " where xsxh='" + pkValue + "'";
			judge = dao.runUpdate(sql, new String[] {});
			if (judge) {
				request.setAttribute("delete", "ok");

				// ɾ�������ļ�¼
				String whensj = (dao
						.getOneRs(
								"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual",
								new String[] {}, new String[] { "sdate" }))[0]; // �ύʱ��

				StandardOperation.insert("JYGL_USERCZMXB",
						new String[] { "userid", "dowhat", "whichtable",
								"whichpk", "whensj" }, new String[] { userName,
								"ɾ��", "���˼�����", "ѧ��:" + pkValue, whensj },
						request);

			} else {
				request.setAttribute("delete", "no");
			}
		}

		if (xsxh == null) {
			xsxh = "";
		}
		if (name == null) {
			name = "";
		}
		if (xb == null) {
			xb = "";
		}
		if (xymc == null) {
			xymc = "";
		}
		if (nj == null) {
			nj = "";
		}
		if (xxsh == null) {
			xxsh = "";
		}
		if (xjsj == null) {
			xjsj = "";
		}
		if ((xsxh == null) || xsxh.equals("")) {
			querry += "and 1=1 ";
		} else {
			querry += "and xsxh like '%" + xsxh + "%' ";
		}
		if ((name == null) || name.equals("")) {
			querry += "and 1=1 ";
		} else {
			querry += "and name like '%" + name + "%' ";
		}
		if ((xb == null) || xb.equals("")) {
			querry += "and 1=1 ";
		} else {
			querry += "and xb = '" + xb + "'";
		}
		if ((xymc == null) || xymc.equals("")) {
			querry += "and 1=1 ";
		} else {
			querry += " and xsxh in (select xsxh from jygl_xsjbxxb where xsxh in (select xh from view_xsjbxx where xymc ='"
					+ xymc + "'))";
		}
		if ((nj == null) || nj.equals("")) {
			querry += "and 1=1 ";
		} else {
			querry += "and xsxh in (select xsxh from jygl_xsjbxxb where nd = '"
					+ nj + "')";
		}
		if ((xxsh == null) || xxsh.equals("")) {
			querry += "and 1=1 ";
		} else {
			querry += "and xxsh = '" + xxsh + "' ";
		}
		if ((xjsj == null) || xjsj.equals("")) {
			querry += "and 1=1 ";
		} else {
			querry += "and (fbsj between '" + beforesj + "' and '" + nowsj
					+ "') ";
		}

		String usertype = session.getAttribute("userType").toString();
		if ("12453".equals(xxdm)) {
			if ("xx".equals(usertype) || "admin".equals(usertype)) {
				sql = "select " + pk + " ����,rownum �к�,a.* from " + tableName
						+ " a " + querry + " and fdysh='��ͨ����'";
			} else {
				sql = "select " + pk + " ����,rownum �к�,a.* from " + tableName
						+ " a " + querry;
			}

		} else {
			sql = "select " + pk + " ����,rownum �к�,a.* from " + tableName
					+ " a " + querry;
		}
		colList = new String[] { "����", "�к�", "name", "xsxh", "xb", "xl",
				"zymc", "fbsj", "readtimes", "xxsh", "fdysh" };
		colListCN = dao.getColumnNameCN(colList, tableName);
		List topTr = dao.arrayToList(colList, colListCN);
		if ("go".equals(act)) {
			rs.addAll(dao.rsToVator(sql, new String[] {}, colList));
		}
		if (rs == null) {
			rsNum = "0";
		} else {
			rsNum = String.valueOf(rs.size());
		}
		if ("12453".equals(xxdm)) {
			usertype = request.getSession().getAttribute("userType").toString();
			String bbmc = (String) request.getSession().getAttribute("bmmc");
			if ("xx".equals(usertype) || "admin".equals(usertype)) {
				request.setAttribute("who", "teacher");
			} else if ("xy".equals(usertype)) {
				request.setAttribute("who", "fudaoyuan");
				map.put("xymc", bbmc);
			}
		}

		request.setAttribute("rs1", map); // ���Ͳ�ѯ���ݻ�ҳ��
		request.setAttribute("njList", dao.getNjList()); // ������ѧ����б�
		request.setAttribute("xyList", dao.getXyList());// ����ѧԺ�б�
		request.setAttribute("rs", rs);// �������ݼ�
		request.setAttribute("topTr", topTr);// ���ͱ�ͷ
		request.setAttribute("rsNum", rsNum);// ���ͼ�¼��
		return mapping.findForward("success");
	}

	// ����鿴���˼�����ϸ
	private ActionForward grjlViewMoreinfo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String pk = "xsxh";
		String sql = ""; // sql���
		DAO dao = DAO.getInstance();
		String realTable = "jygl_grjlb";
		String doType = request.getParameter("doType");
		String pkValue = request.getParameter("pkValue");
		HashMap<String, String> map = new HashMap<String, String>();

		if ((doType == null) || doType.equalsIgnoreCase("")) {
			// �����쳣
			return mapping.findForward("false");
		} else if (doType.equalsIgnoreCase("view")) {
			// ��ѯ����
			sql = "select * from " + realTable + " where " + pk + "='"
					+ pkValue + "'";
			String[] colList = dao
					.getColumnName("select * from jygl_grjlb where 1=2"); // ������������
			String[] stuinfo = dao.getOneRs(sql, new String[] {}, colList);
			sql = "select xymc from view_xsjbxx where xh=?";
			String[] xymc = dao.getOneRs(sql, new String[] { pkValue },
					new String[] { "xymc" });
			if (stuinfo != null) {
				for (int i = 0; i < colList.length; i++) {
					map.put(colList[i].toLowerCase(), stuinfo[i]); // ����¼ѭ������map��
				}
				map.put("xymc", xymc[0]);
				String fbsj = null;
				String shsj = null;
				String sjyear = null;
				String sjmon = null;
				String sjday = null;
				String sjhour = null;
				// ����ʱ��ת��
				String sj = map.get("fbsj").toString();
				sjyear = sj.substring(0, 4);
				sjmon = sj.substring(4, 6);
				sjday = sj.substring(6, 8);
				sjhour = sj.substring(8, 10);
				fbsj = sjyear + "��" + sjmon + "��" + sjday + "��" + sjhour + "ʱ";
				map.put("fbsj", fbsj);
				// ���ʱ��ת��
				if (null != map.get("shsj")) {
					sj = map.get("shsj").toString();
					sjyear = sj.substring(0, 4);
					sjmon = sj.substring(4, 6);
					sjday = sj.substring(6, 8);
					sjhour = sj.substring(8, 10);
					shsj = sjyear + "��" + sjmon + "��" + sjday + "��" + sjhour
							+ "ʱ";
					map.put("shsj", shsj);
				}
			}
		}
		request.setAttribute("rs", map);// �������ݼ�
		return mapping.findForward("success");
	}

	// ���˼���ѧУ���
	private ActionForward jyglGrjlsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String pk = "xsxh";
		String sql = ""; // sql���
		DAO dao = DAO.getInstance();
		String realTable = "jygl_grjlb";
		String doType = request.getParameter("doType");
		String pkValue = request.getParameter("pkValue");
		HashMap<String, String> map = new HashMap<String, String>();
		String xxsh = DealString.toGBK(request.getParameter("xxsh"));
		String btgyy = DealString.toGBK(request.getParameter("btgyy"));
		String xsxh = request.getParameter("xsxh");
		String act = request.getParameter("act");
		HttpSession session = request.getSession();
		String shperson = session.getAttribute("userName").toString();
		String xxtj = DealString.toGBK(request.getParameter("xxtj"));
		String userType = (String) session.getAttribute("userType");
		// String xxdm = (String) session.getAttribute("xxdm");
		String bbmctt = (String) session.getAttribute("bmmc");

		if ("xx".equals(userType) || "admin".equals(userType)) {
			request.setAttribute("ldgxusertype", "xx");
		} else if ("xy".equals(userType)) {
			request.setAttribute("ldgxusertype", "xy");
			map.put("xymc", bbmctt);
		}
		// String userName = session.getAttribute("userName").toString();
		sql = "select xm from view_fdyxx where zgh=?";
		// String[] isfdy = dao.getOneRs(sql, new String[] { userName },
		// new String[] { "xm" });

		if ("xy".equals(userType)) {
			if ("shenhe".equals(act)) {
				request.setAttribute("who", "fudaoyuan");
				String fdyshsj = (dao
						.getOneRs(
								"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual", // ѧУ���ʱ��
								// //
								// ȡ�����ݿ���ʱ��
								new String[] {}, new String[] { "sdate" }))[0];

				String fdysh = DealString.toGBK(request.getParameter("fdysh"));
				String fdyshyj = DealString.toGBK(request
						.getParameter("fdyshyj"));
				// String fdyshr;

				if ("��ͨ����".equals(fdysh)) {
					btgyy = "";
				}
				if ("δ���".equals(fdysh)) {
					fdyshyj = "";
					// fdyshr = "";
					fdyshsj = "";
					shperson = "";
				}
				sql = "update " + realTable + " set fdysh='" + fdysh
						+ "' , fdyshyj='" + fdyshyj + "' ,fdyshsj='" + fdyshsj
						+ "' ,fdyshr='" + shperson + "' where xsxh='" + xsxh
						+ "'";

				boolean judge = false;
				judge = dao.runUpdate(sql, new String[] {});
				if (judge) {
					request.setAttribute("shenhe", "ok");
				} else {
					request.setAttribute("shenhe", "no");
				}
				sql = "select * from jygl_grjlb where xsxh=?";
				String[] colList = dao
						.getColumnName("select * from jygl_grjlb where 1=2"); // ������������
				String[] stuinfo = dao.getOneRs(sql, new String[] { xsxh },
						colList);
				sql = "select xymc from view_xsjbxx where xh=?";
				String[] xymc = dao.getOneRs(sql, new String[] { xsxh },
						new String[] { "xymc" });
				if (stuinfo != null) {
					for (int i = 0; i < colList.length; i++) {
						map.put(colList[i].toLowerCase(), stuinfo[i]); // ����¼ѭ������map��
					}
					if (xymc != null) {
						map.put("xymc", xymc[0]);
					}
					fdyshsj = null;
					String sjyear = null;
					String sjmon = null;
					String sjday = null;
					String sjhour = null;
					if (null != map.get("fdyshsj")) {
						String sj = map.get("fdyshsj").toString();
						sjyear = sj.substring(0, 4);
						sjmon = sj.substring(4, 6);
						sjday = sj.substring(6, 8);
						sjhour = sj.substring(8, 10);
						fdyshsj = sjyear + "��" + sjmon + "��" + sjday + "��"
								+ sjhour + "ʱ";
						map.put("fdyshsj", fdyshsj);
					}
					if (null != map.get("shsj")) {
						String sj = map.get("shsj").toString();
						sjyear = sj.substring(0, 4);
						sjmon = sj.substring(4, 6);
						sjday = sj.substring(6, 8);
						sjhour = sj.substring(8, 10);
						fdyshsj = sjyear + "��" + sjmon + "��" + sjday + "��"
								+ sjhour + "ʱ";
						map.put("shsj", fdyshsj);
					}
				}
			}
		} else {
			if ("shenhe".equals(act)) {
				String shsj = (dao
						.getOneRs(
								"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual", // ѧУ���ʱ��
								// //
								// ȡ�����ݿ���ʱ��
								new String[] {}, new String[] { "sdate" }))[0];
				if ("��ͨ����".equals(xxsh)) {
					btgyy = "";
				}
				if ("δ���".equals(xxsh)) {
					btgyy = "";
					shperson = "";
					shsj = "";
				}
				sql = "update " + realTable + " set xxsh='" + xxsh
						+ "' , btgyy='" + btgyy + "' ,shsj='" + shsj
						+ "' ,shperson='" + shperson + "' ,xxtj='" + xxtj
						+ "' where xsxh='" + xsxh + "'";

				boolean judge = false;
				judge = dao.runUpdate(sql, new String[] {});
				if (judge) {
					request.setAttribute("shenhe", "ok");
				} else {
					request.setAttribute("shenhe", "no");
				}
				sql = "select * from jygl_grjlb where xsxh=?";
				String[] colList = dao
						.getColumnName("select * from jygl_grjlb where 1=2"); // ������������
				String[] stuinfo = dao.getOneRs(sql, new String[] { xsxh },
						colList);
				sql = "select xymc from view_xsjbxx where xh=?";
				String[] xymc = dao.getOneRs(sql, new String[] { xsxh },
						new String[] { "xymc" });
				if (stuinfo != null) {
					for (int i = 0; i < colList.length; i++) {
						map.put(colList[i].toLowerCase(), stuinfo[i]); // ����¼ѭ������map��
					}
					if (xymc != null) {
						map.put("xymc", xymc[0]);
					}
					shsj = null;
					String sjyear = null;
					String sjmon = null;
					String sjday = null;
					String sjhour = null;
					if (null != map.get("shsj")) {
						String sj = map.get("shsj").toString();
						sjyear = sj.substring(0, 4);
						sjmon = sj.substring(4, 6);
						sjday = sj.substring(6, 8);
						sjhour = sj.substring(8, 10);
						shsj = sjyear + "��" + sjmon + "��" + sjday + "��"
								+ sjhour + "ʱ";
						map.put("shsj", shsj);
					}
					if (null != map.get("fdyshsj")) {
						String sj = map.get("fdyshsj").toString();
						sjyear = sj.substring(0, 4);
						sjmon = sj.substring(4, 6);
						sjday = sj.substring(6, 8);
						sjhour = sj.substring(8, 10);
						shsj = sjyear + "��" + sjmon + "��" + sjday + "��"
								+ sjhour + "ʱ";
						map.put("fdyshsj", shsj);
					}
				}
			}
		}
		if ((doType == null) || doType.equalsIgnoreCase("")) {
			// �����쳣
			return mapping.findForward("false");
		} else if ("shenhe".equalsIgnoreCase(doType)) {
			// ��ѯ����
			sql = "select * from " + realTable + " where " + pk + "='"
					+ pkValue + "'";
			String[] colList = dao
					.getColumnName("select * from jygl_grjlb where 1=2"); // ������������
			String[] stuinfo = dao.getOneRs(sql, new String[] {}, colList);
			sql = "select xymc from view_xsjbxx where xh=?";
			String[] xymc = dao.getOneRs(sql, new String[] { pkValue },
					new String[] { "xymc" });
			if (stuinfo != null) {
				for (int i = 0; i < colList.length; i++) {
					map.put(colList[i].toLowerCase(), stuinfo[i]); // ����¼ѭ������map��
				}
				map.put("xymc", xymc[0]);
				String shsj = null;
				String fbsj = null;
				String sjyear = null;
				String sjmon = null;
				String sjday = null;
				String sjhour = null;
				if (null != map.get("shsj")) {
					String sj = map.get("shsj").toString();
					sjyear = sj.substring(0, 4);
					sjmon = sj.substring(4, 6);
					sjday = sj.substring(6, 8);
					sjhour = sj.substring(8, 10);
					shsj = sjyear + "��" + sjmon + "��" + sjday + "��" + sjhour
							+ "ʱ";
					map.put("shsj", shsj);
				}
				if (null != map.get("fbsj")) {
					String sj = map.get("fbsj").toString();
					sjyear = sj.substring(0, 4);
					sjmon = sj.substring(4, 6);
					sjday = sj.substring(6, 8);
					sjhour = sj.substring(8, 10);
					fbsj = sjyear + "��" + sjmon + "��" + sjday + "��" + sjhour
							+ "ʱ";
					map.put("fbsj", fbsj);
				}
				if (null != map.get("fdyshsj")) {
					String sj = map.get("fdyshsj").toString();
					sjyear = sj.substring(0, 4);
					sjmon = sj.substring(4, 6);
					sjday = sj.substring(6, 8);
					sjhour = sj.substring(8, 10);
					fbsj = sjyear + "��" + sjmon + "��" + sjday + "��" + sjhour
							+ "ʱ";
					map.put("fdyshsj", fbsj);
				}
			}
		}

		String usertype = request.getSession().getAttribute("userType")
				.toString();
		String bbmc = request.getSession().getAttribute("bmmc").toString();
		if ("xx".equals(usertype) || "admin".equals(usertype)) {
			request.setAttribute("ldgxusertype", "xx");
		} else if ("xy".equals(usertype)) {
			request.setAttribute("ldgxusertype", "xy");
			map.put("xymc", bbmc);
		}

		request.setAttribute("rs", map);// �������ݼ�
		return mapping.findForward("success");
	}

	// ���˼����޸�
	private ActionForward grjlUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		String pk = "xsxh";
		String pkValue = request.getParameter("pkValue");
		String tableName = "jygl_grjlb";
		String doType = request.getParameter("doType");
		String xsxh = request.getParameter("xsxh");
		String sql = "";
		HashMap<String, String> map = new HashMap<String, String>();

		sql = "select zzmmdm,zzmm from dmk_zzmm"; // ������ò
		List zzmmList = dao.getList(sql, new String[] {}, new String[] {
				"zzmmdm", "zzmm" });
		request.setAttribute("zzmmList", zzmmList);

		if ("update".equals(doType)) {
			String idsee = request.getParameter("idsee"); // ���֤�Ƿ���
			String zzmm = DealString.toGBK(request.getParameter("zzmm")); // ������ò9
			String fxzymc = DealString.toGBK(request.getParameter("fxzymc")); // ����רҵ����11
			String lxdz = DealString.toGBK(request.getParameter("lxdz")); // ��ϵ��ַ13
			String lxdh = request.getParameter("lxdh"); // ��ϵ�绰14
			String yzbm = request.getParameter("yzbm"); // ��������15
			String email = request.getParameter("email"); // ��������16
			String hjqk = DealString.toGBK(request.getParameter("hjqk")); // �����17
			String xxqk = DealString.toGBK(request.getParameter("xxqk")); // ѧϰ���18
			String xjysjl = DealString.toGBK(request.getParameter("xjysjl")); // У�����Ͻ���19
			String shsjqk = DealString.toGBK(request.getParameter("shsjqk")); // ���ʵ�����20
			String gzjl = DealString.toGBK(request.getParameter("gzjl")); // ��������21
			String grtc = DealString.toGBK(request.getParameter("grtc")); // �����س�22
			String zwtj = DealString.toGBK(request.getParameter("zwtj")); // �����Ƽ�23
			String xxtj = DealString.toGBK(request.getParameter("xxtj")); // ѧУ�Ƽ�24
			String fbsj = (dao
					.getOneRs(
							"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual", // ����ʱ��25
							// //
							// ȡ�����ݿ���ʱ��
							new String[] {}, new String[] { "sdate" }))[0];
			boolean judge = false;
			judge = StandardOperation.update(tableName, new String[] { "idsee",
					"zzmm", "fxzymc", "lxdz", "lxdh", "yzbm", "email", "hjqk",
					"xxqk", "xjysjl", "shsjqk", "gzjl", "grtc", "zwtj", "xxtj",
					"fbsj" }, new String[] { idsee, zzmm, fxzymc, lxdz, lxdh,
					yzbm, email, hjqk, xxqk, xjysjl, shsjqk, gzjl, grtc, zwtj,
					xxtj, fbsj }, pk, xsxh, request);
			if (judge) {
				request.setAttribute("update", "ok");

				// �޸Ĳ����ļ�¼
				HttpSession session = request.getSession();
				String userName = session.getAttribute("userName").toString();
				String whensj = (dao
						.getOneRs(
								"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual",
								new String[] {}, new String[] { "sdate" }))[0]; // �ύʱ��

				StandardOperation.insert("JYGL_USERCZMXB",
						new String[] { "userid", "dowhat", "whichtable",
								"whichpk", "whensj" }, new String[] { userName,
								"�޸�", "���˼�����", "ѧ��:" + xsxh, whensj }, request);
			} else {
				request.setAttribute("update", "no");
			}
		}

		if ("".equals(pkValue) || pkValue == null) {
			pkValue = xsxh;
		}
		sql = "select * from " + tableName + " where xsxh=?";
		String[] colulist = dao.getColumnName("select * from " + tableName
				+ " where 1=2");
		String[] grjlinfo = dao.getOneRs(sql, new String[] { pkValue },
				colulist);
		sql = "select xymc from view_xsjbxx where xh=?";
		String[] xymc = dao.getOneRs(sql, new String[] { pkValue },
				new String[] { "xymc" });
		if (grjlinfo != null) {
			for (int i = 0; i < colulist.length; i++) {
				map.put(colulist[i].toLowerCase(), grjlinfo[i]); // ����¼ѭ������map��
			}
			map.put("xymc", xymc[0]);
			String shsj = null;
			String fbsj = null;
			String sjyear = null;
			String sjmon = null;
			String sjday = null;
			String sjhour = null;
			if (null != map.get("shsj")) {
				String sj = map.get("shsj").toString();
				sjyear = sj.substring(0, 4);
				sjmon = sj.substring(4, 6);
				sjday = sj.substring(6, 8);
				sjhour = sj.substring(8, 10);
				shsj = sjyear + "��" + sjmon + "��" + sjday + "��" + sjhour + "ʱ";
				map.put("shsj", shsj);
			}
			if (null != map.get("fbsj")) {
				String sj = map.get("fbsj").toString();
				sjyear = sj.substring(0, 4);
				sjmon = sj.substring(4, 6);
				sjday = sj.substring(6, 8);
				sjhour = sj.substring(8, 10);
				fbsj = sjyear + "��" + sjmon + "��" + sjday + "��" + sjhour + "ʱ";
				map.put("fbsj", fbsj);
			}
		}

		request.setAttribute("rs", map);
		return mapping.findForward("success");
	}

	// ���˼��������ѯ
	private ActionForward grjlResultQuery(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response, String userType) throws Exception {
		DAO dao = DAO.getInstance();
		String sql = "";
		String pk = "xsxh";
		String[] colList = null;
		String[] colListCN = null;
		ArrayList<Object> rs = new ArrayList<Object>(); // ��ü�¼
		String rsNum = "0";
		String tableName = "jygl_grjlb";
		List topTr = null;
		HashMap<String, String> map = new HashMap<String, String>();
		HttpSession session = request.getSession();
		String userName = session.getAttribute("userName").toString();

		if (userType.equals("teacher")) {
			sql = "select " + pk + " ����,rownum �к�,a.* from " + tableName
					+ " a " + " where SHPERSON='" + userName + "'";
			colList = new String[] { "����", "�к�", "name", "xsxh", "xb", "xl",
					"zymc", "fbsj", "readtimes", "xxsh" };
			colListCN = dao.getColumnNameCN(colList, tableName);
			topTr = dao.arrayToList(colList, colListCN);
			if ((request.getParameter("act") != null)
					&& request.getParameter("act").equalsIgnoreCase("go")) {
				rs.addAll(dao.rsToVator(sql, new String[] {}, colList));
				if (rs == null) {
					rsNum = "0";
				} else {
					rsNum = String.valueOf(rs.size());
				}
			}
			request.setAttribute("ableToView", "teacher");
		}

		if (userType.equals("student")) {
			sql = "select " + pk + " ����,rownum �к�,a.* from " + tableName
					+ " a " + " where xsxh=" + userName;
			colList = new String[] { "����", "�к�", "name", "xsxh", "xb", "xl",
					"zymc", "fbsj", "xxsh" };
			colListCN = dao.getColumnNameCN(colList, tableName);
			topTr = dao.arrayToList(colList, colListCN);
			if ((request.getParameter("act") != null)
					&& request.getParameter("act").equalsIgnoreCase("go")) {
				rs.addAll(dao.rsToVator(sql, new String[] {}, colList));
				if (rs == null) {
					rsNum = "0";
				} else {
					rsNum = String.valueOf(rs.size());
				}
			}
			request.setAttribute("ableToView", "student");
		}

		map.put("userName", userName);
		request.setAttribute("rs1", map);
		if (rs == null || rs.size() == 0) {
			String msg = "�޵Ǽ����ݣ���ȷ�ϣ�";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
		request.setAttribute("rs", rs); // �������ݼ�
		request.setAttribute("topTr", topTr); // ���ͱ�ͷ
		request.setAttribute("rsNum", rsNum); // ���ͼ�¼��
		return mapping.findForward("success");
	}

	// ��Ƹ��Ϣ����
	private ActionForward zpxxfb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			String userType) throws Exception {

		String sql = "";
		DAO dao = DAO.getInstance();
		String tableName = "jygl_zpxxb";
		HashMap<String, String> map = new HashMap<String, String>();
		String act = request.getParameter("act");
		sql = "select hyfldm,hyfl from dmk_hyfl"; // ��ҵ����
		List hyflList = dao.getList(sql, new String[] {}, new String[] {
				"hyfldm", "hyfl" });
		request.setAttribute("hyflList", hyflList);

		if ("save".equals(act)) {
			String zpzw = DealString.toGBK(request.getParameter("zpzw"));
			String gsmc = DealString.toGBK(request.getParameter("gsmc"));
			String email = request.getParameter("email");
			String lxdh = request.getParameter("lxdh");
			String gzdd = DealString.toGBK(request.getParameter("gzdd")); // �����ص�
			String zprs = DealString.toGBK(request.getParameter("zprs"));
			String hyfl = DealString.toGBK(request.getParameter("hyfl"));
			String wyyq = DealString.toGBK(request.getParameter("wyyq"));
			String syxs = request.getParameter("syxs");
			String zzxs = DealString.toGBK(request.getParameter("zzxs"));
			String xlyq = DealString.toGBK(request.getParameter("xlyq"));
			String gwzz = DealString.toGBK(request.getParameter("gwzz"));
			String zwyq = DealString.toGBK(request.getParameter("zwyq"));
			String gsjj = DealString.toGBK(request.getParameter("gsjj"));
			String msxd = DealString.toGBK(request.getParameter("msxd"));
			String msdd = DealString.toGBK(request.getParameter("msdd"));
			String yddh = DealString.toGBK(request.getParameter("yddh"));
			String lxr = DealString.toGBK(request.getParameter("lxr"));
			String gswz = DealString.toGBK(request.getParameter("gswz"));
			String cz = DealString.toGBK(request.getParameter("cz"));
			String zphfs = DealString.toGBK(request.getParameter("zphfs"));
			String zpsj = DealString.toGBK(request.getParameter("zpsj"));
			String zpdd = DealString.toGBK(request.getParameter("zpdd"));
			String mssj = "";
			String day = DealString.toGBK(request.getParameter("day"));
			String hour = request.getParameter("hour");
			String min = request.getParameter("min");
			if (Base.isNull(day)) {
				mssj = "";
			} else if (Base.isNull(hour)) {
				mssj = day;
			} else if (Base.isNull(min)) {
				mssj = day + " " + hour;
			} else {
				mssj = day + " " + hour + ":" + min;
			}
			String fbsj = (dao
					.getOneRs(
							"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual", // ����ʱ��
							// //
							// ȡ�����ݿ���ʱ��
							new String[] {}, new String[] { "sdate" }))[0];

			boolean judge = false;

			judge = StandardOperation.insert(tableName, new String[] { "zpzw",
					"gsmc", "email", "lxdh", "gzdd", "zprs", "hyfl", "wyyq",
					"syxs", "zzxs", "xlyq", "mssj", "gwzz", "zwyq", "gsjj",
					"fbsj", "msxd", "msdd", "yddh", "lxr", "gswz", "cz",
					"zphfs", "zpsj", "zpdd" }, new String[] { zpzw, gsmc,
					email, lxdh, gzdd, zprs, hyfl, wyyq, syxs, zzxs, xlyq,
					mssj, gwzz, zwyq, gsjj, fbsj, msxd, msdd, yddh, lxr, gswz,
					cz, zphfs, zpsj, zpdd }, request);
			if (judge) {
				request.setAttribute("insert", "ok");

				// ���Ӳ����ļ�¼
				HttpSession session = request.getSession();
				String userName = session.getAttribute("userName").toString();
				String whensj = (dao
						.getOneRs(
								"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual",
								new String[] {}, new String[] { "sdate" }))[0]; // �ύʱ��

				StandardOperation.insert("JYGL_USERCZMXB",
						new String[] { "userid", "dowhat", "whichtable",
								"whichpk", "whensj" }, new String[] { userName,
								"����", "��Ƹ��Ϣ��",
								"��˾����:" + gsmc + " ��Ƹְλ:" + zpzw, whensj },
						request);
			} else {
				request.setAttribute("insert", "no");
			}

		}
		request.setAttribute("rs", map);
		return mapping.findForward("success");
	}

	// ��Ƹ��Ϣ��ѯ(ɾ������ѯ)

	private ActionForward zpxxquery(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			String userType) throws Exception {

		DAO dao = DAO.getInstance();
		String sql = "";
		ArrayList<Object> rs = new ArrayList<Object>();
		String[] colList = null;
		String[] colListCN = null;
		String tableName = "jygl_zpxxb";
		String doType = request.getParameter("doType");
		String doType2 = request.getParameter("doType2");

		sql = "select gsmc,zpzw from jygl_zpxxb where rowid=?";
		String pkval = request.getParameter("pkValue");
		String[] zpinfo = dao.getOneRs(sql, new String[] { pkval },
				new String[] { "gsmc", "zpzw" });

		if ("teacher".equals(userType)) {
			request.setAttribute("who", "teacher");
		}
		if ("del".equals(doType2)) {
			// ɾ������
			boolean judge = false;
			String pkValue = request.getParameter("pkValue");
			pkValue = pkValue.replaceAll(" ", "+");
			sql = "delete from " + tableName + " where rowid='" + pkValue + "'";
			judge = dao.runUpdate(sql, new String[] {});
			if (judge) {
				request.setAttribute("delete", "ok");

				// ɾ�������ļ�¼

				HttpSession session = request.getSession();
				String userName = session.getAttribute("userName").toString();
				String whensj = (dao
						.getOneRs(
								"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual",
								new String[] {}, new String[] { "sdate" }))[0]; // �ύʱ��

				judge = StandardOperation.insert("JYGL_USERCZMXB",
						new String[] { "userid", "dowhat", "whichtable",
								"whichpk", "whensj" }, new String[] { userName,
								"ɾ��", "��Ƹ��Ϣ��",
								"��˾����:" + zpinfo[0] + " ��Ƹְλ:" + zpinfo[1],
								whensj }, request);
			} else {
				request.setAttribute("delete", "no");
			}
		}

		sql = "select hyfldm,hyfl from dmk_hyfl"; // ��ҵ����
		List hyflList = dao.getList(sql, new String[] {}, new String[] {
				"hyfldm", "hyfl" });
		request.setAttribute("hyflList", hyflList);

		sql = "select dwdqdm,dwdq from dmk_dwdq"; // �����ص�
		List dwdqList = dao.getList(sql, new String[] {}, new String[] {
				"dwdqdm", "dwdq" });
		request.setAttribute("dwdqList", dwdqList);

		String rsNum = "0"; // ���صļ�¼��
		String zpzw = DealString.toGBK(request.getParameter("zpzw")); // ��Ƹְλ
		String gsmc = DealString.toGBK(request.getParameter("gsmc")); // ��˾����
		String gzdd = DealString.toGBK(request.getParameter("gzdd")); // �����ص�
		String hyfl = DealString.toGBK(request.getParameter("hyfl")); // ��ҵ����
		String zzxs = DealString.toGBK(request.getParameter("zzxs")); // ת��нˮ
		String xlyq = DealString.toGBK(request.getParameter("xlyq")); // ѧ��Ҫ��

		// ���ڷ���ʱ��Ĵ��� ���ڲ�ѯ����
		String xjsj = DealString.toGBK(request.getParameter("xjsj")); // ���ʱ��

		String nowsj = (dao.getOneRs(
				"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual", // ����ʱ��
				// //
				// ȡ�����ݿ���ʱ��
				new String[] {}, new String[] { "sdate" }))[0];
		String sjyear = nowsj.substring(0, 4);
		String sjmonth = nowsj.substring(4, 6);
		String sjday = nowsj.substring(6, 8);
		String sjqt = nowsj.substring(8, 14);
		String nowsj1 = sjyear + "-" + sjmonth + "-" + sjday;
		dealDate dealdate = new dealDate();
		String beforesj = "";
		if (!"".equals(xjsj)) {
			beforesj = dealdate.getDate2(Integer.parseInt(xjsj), nowsj1);
			beforesj = beforesj.replaceAll("-", "") + sjqt;
		}

		String querry = " where 1=1 ";
		HashMap<String, String> map = new HashMap<String, String>();

		if ("query".equals(doType)) {
			map.put("zpzw", zpzw);
			map.put("gsmc", gsmc);
			map.put("gzdd", gzdd);
			map.put("hyfl", hyfl);
			map.put("zzxs", zzxs);
			map.put("xlyq", xlyq);
			map.put("xjsj", xjsj);
		}

		if (zpzw == null) {
			zpzw = "";
		}
		if (gsmc == null) {
			gsmc = "";
		}
		if (gzdd == null) {
			gzdd = "";
		}
		if (hyfl == null) {
			hyfl = "";
		}
		if (zzxs == null) {
			zzxs = "";
		}
		if (xlyq == null) {
			xlyq = "";
		}
		if (xjsj == null) {
			xjsj = "";
		}
		if ((zpzw == null) || zpzw.equals("")) {
			querry += "and 1=1 ";
		} else {
			querry += "and zpzw like '%" + zpzw + "%' ";
		}
		if ((gsmc == null) || gsmc.equals("")) {
			querry += "and 1=1 ";
		} else {
			querry += "and gsmc like '%" + gsmc + "%' ";
		}
		if ((gzdd == null) || gzdd.equals("")) {
			querry += "and 1=1 ";
		} else {
			querry += "and gzdd like '%" + gzdd + "%' ";
		}
		if ((hyfl == null) || hyfl.equals("")) {
			querry += "and 1=1 ";
		} else {
			querry += "and hyfl = '" + hyfl + "' ";
		}
		if ((zzxs == null) || zzxs.equals("")) {
			querry += "and 1=1 ";
		} else {
			querry += "and zzxs = '" + zzxs + "' ";
		}
		if ((xlyq == null) || xlyq.equals("")) {
			querry += "and 1=1 ";
		} else {
			querry += "and xlyq = '" + xlyq + "' ";
		}
		if ((xjsj == null) || xjsj.equals("")) {
			querry += "and 1=1 ";
		} else {
			querry += "and (fbsj between '" + beforesj + "' and '" + nowsj
					+ "') ";
		}

		sql = "select  rownum �к�,a.* ,a.rowid from " + tableName + " a "
				+ querry;
		colList = new String[] { "�к�", "gsmc", "zpzw", "gzdd", "xlyq", "fbsj",
				"readtimes" };
		colListCN = dao.getColumnNameCN(colList, tableName);
		List topTr = dao.arrayToList(colList, colListCN);
		colList = new String[] { "rowid", "�к�", "gsmc", "zpzw", "gzdd", "xlyq",
				"fbsj", "readtimes" };
		if ((request.getParameter("act") != null)
				&& request.getParameter("act").equalsIgnoreCase("go")) {
			rs.addAll(dao.rsToVator(sql, new String[] {}, colList));
			if (rs == null) {
				rsNum = "0";
			} else {
				rsNum = String.valueOf(rs.size());
			}
		}

		request.setAttribute("rs", rs);// �������ݼ�
		request.setAttribute("topTr", topTr);// ���ͱ�ͷ
		request.setAttribute("rsNum", rsNum);// ���ͼ�¼��
		request.setAttribute("rs1", map);// �Ѳ�ѯ��������ȥ
		return mapping.findForward("success");
	}

	// ��Ƹ��Ϣ��ϸ�鿴
	private ActionForward zpxxViewMoreinfo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String pk = "rowid";
		String sql = ""; // sql���
		DAO dao = DAO.getInstance();
		String realTable = "jygl_zpxxb";
		String doType = request.getParameter("doType");
		String pkValue = request.getParameter("pkValue");
		pkValue = pkValue.replaceAll(" ", "+");
		HashMap<String, String> map = new HashMap<String, String>();

		if ((doType == null) || doType.equalsIgnoreCase("")) {
			// �����쳣
			return mapping.findForward("false");
		} else if (doType.equalsIgnoreCase("view")) {
			// ��ѯ����
			sql = "select * from " + realTable + " where " + pk + "='"
					+ pkValue + "'";
			String[] colList = dao
					.getColumnName("select * from jygl_zpxxb where 1=2"); // ������������
			String[] zpxxinfo = dao.getOneRs(sql, new String[] {}, colList);
			if (zpxxinfo != null) {
				for (int i = 0; i < colList.length; i++) {
					map.put(colList[i].toLowerCase(), zpxxinfo[i]); // ����¼ѭ������map��
				}
				String fbsj = null;
				String sjyear = null;
				String sjmon = null;
				String sjday = null;
				String sjhour = null;
				String sjmin = null;
				String sjsec = null;
				String sj = map.get("fbsj").toString();
				sjyear = sj.substring(0, 4);
				sjmon = sj.substring(4, 6);
				sjday = sj.substring(6, 8);
				sjhour = sj.substring(8, 10);
				sjmin = sj.substring(10, 12);
				sjsec = sj.substring(12, 14);

				fbsj = sjyear + "��" + sjmon + "��" + sjday + "��" + " " + sjhour
						+ ":" + sjmin + ":" + sjsec;
				map.put("fbsj", fbsj);
				String mssj = map.get("mssj");
				if (mssj != null) {
					if (mssj.length() < 13 && mssj.length() > 9) {
						mssj = mssj.substring(0, 10);
						map.put("mssj", mssj);
					} else if (mssj.length() < 9) {
						mssj = "";
						map.put("mssj", mssj);
					}
				}
				sql = "select readtimes from " + realTable + " where rowid=?";
				String[] readtimes = dao.getOneRs(sql,
						new String[] { pkValue }, new String[] { "readtimes" });
				int timeint = Integer.parseInt(readtimes[0]);
				timeint += 1;
				String tinestr = String.valueOf(timeint);
				StandardOperation.update(realTable,
						new String[] { "readtimes" }, new String[] { tinestr },
						pk, pkValue, request);
			}
		}
		request.setAttribute("rs", map);// �������ݼ�
		return mapping.findForward("success");
	}

	// ��Ƹ��Ϣ�޸�
	private ActionForward zpxxupdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DAO dao = DAO.getInstance();
		String tableName = "jygl_zpxxb";
		String rowid = request.getParameter("rowid");
		rowid = rowid.replaceAll(" ", "+");
		String sql = "";
		HashMap<String, String> map = new HashMap<String, String>();
		String act = request.getParameter("act");

		sql = "select * from jygl_zpxxb where rowid=?";
		String[] colList = dao
				.getColumnName("select * from jygl_zpxxb where 1=2"); // ������������
		String[] zpxxinfo = dao.getOneRs(sql, new String[] { rowid }, colList);
		if (zpxxinfo != null) {
			for (int i = 0; i < colList.length; i++) {
				map.put(colList[i].toLowerCase(), zpxxinfo[i]); // ����¼ѭ������map��
			}
			String fbsj = null;
			String sjyear = null;
			String sjmon = null;
			String sjday = null;
			String sjhour = null;
			String sjmin = null;
			String sjsec = null;
			String sj = map.get("fbsj").toString();
			sjyear = sj.substring(0, 4);
			sjmon = sj.substring(4, 6);
			sjday = sj.substring(6, 8);
			sjhour = sj.substring(8, 10);
			sjmin = sj.substring(10, 12);
			sjsec = sj.substring(12, 14);

			fbsj = sjyear + "��" + sjmon + "��" + sjday + "��" + " " + sjhour
					+ ":" + sjmin + ":" + sjsec;
			map.put("fbsj", fbsj);
			map.put("rowid", rowid);

			String mssj = map.get("mssj");
			if (mssj != null) {
				if (mssj.length() > 9) {
					String day = mssj.substring(0, 10);
					map.put("day", day);
				}
				if (mssj.length() < 9) {
					mssj = "";
					map.put("mssj", mssj);
				}
				if (mssj.length() > 12) {
					String hour = mssj.substring(11, 13);
					map.put("hour", hour);
					String min = mssj.substring(14, 16);
					map.put("min", min);
				}
			}
		}
		request.setAttribute("rs", map);

		sql = "select hyfldm,hyfl from dmk_hyfl"; // ��ҵ����
		List hyflList = dao.getList(sql, new String[] {}, new String[] {
				"hyfldm", "hyfl" });
		request.setAttribute("hyflList", hyflList);

		if ("update".equals(act)) {
			String zpzw = DealString.toGBK(request.getParameter("zpzw"));
			String gsmc = DealString.toGBK(request.getParameter("gsmc"));
			String email = request.getParameter("email");
			if ("null@null".equals(email)) {
				email = "";
			}
			String lxdh = request.getParameter("lxdh");
			String gzdd = DealString.toGBK(request.getParameter("gzdd")); // �����ص�
			String zprs = DealString.toGBK(request.getParameter("zprs"));
			String hyfl = DealString.toGBK(request.getParameter("hyfl"));
			String wyyq = DealString.toGBK(request.getParameter("wyyq"));
			String syxs = DealString.toGBK(request.getParameter("syxs"));
			String zzxs = DealString.toGBK(request.getParameter("zzxs"));
			String xlyq = DealString.toGBK(request.getParameter("xlyq"));
			String mssj = DealString.toGBK(request.getParameter("day")) + " "
					+ request.getParameter("hour") + ":"
					+ request.getParameter("min");
			String msxd = DealString.toGBK(request.getParameter("msxd"));
			String msdd = DealString.toGBK(request.getParameter("msdd"));
			String gwzz = DealString.toGBK(request.getParameter("gwzz"));
			String zwyq = DealString.toGBK(request.getParameter("zwyq"));
			String gsjj = DealString.toGBK(request.getParameter("gsjj"));
			boolean judge = false;
			judge = StandardOperation.update(tableName, new String[] { "zpzw",
					"gsmc", "email", "lxdh", "gzdd", "zprs", "hyfl", "wyyq",
					"syxs", "zzxs", "xlyq", "mssj", "msxd", "msdd", "gwzz",
					"zwyq", "gsjj" }, new String[] { zpzw, gsmc, email, lxdh,
					gzdd, zprs, hyfl, wyyq, syxs, zzxs, xlyq, mssj, msxd, msdd,
					gwzz, zwyq, gsjj }, "rowid", rowid, request);
			if (judge) {
				request.setAttribute("update", "ok");

				// �޸Ĳ����ļ�¼
				HttpSession session = request.getSession();
				String userName = session.getAttribute("userName").toString();
				String whensj = (dao
						.getOneRs(
								"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual",
								new String[] {}, new String[] { "sdate" }))[0]; // �ύʱ��

				StandardOperation.insert("JYGL_USERCZMXB",
						new String[] { "userid", "dowhat", "whichtable",
								"whichpk", "whensj" }, new String[] { userName,
								"�޸�", "��Ƹ��Ϣ��",
								"��˾����:" + gsmc + " ��Ƹְλ:" + zpzw, whensj },
						request);
			} else {
				request.setAttribute("update", "no");
			}

			sql = "select * from jygl_zpxxb where rowid=?";
			String[] colList1 = dao
					.getColumnName("select * from jygl_zpxxb where 1=2"); // ������������
			String[] zpxxinfo1 = dao.getOneRs(sql, new String[] { rowid },
					colList1);
			if (zpxxinfo1 != null) {
				for (int i = 0; i < colList1.length; i++) {
					map.put(colList1[i].toLowerCase(), zpxxinfo1[i]); // ����¼ѭ������map��
				}
				String fbsj = null;
				String sjyear = null;
				String sjmon = null;
				String sjday = null;
				String sjhour = null;
				String sjmin = null;
				String sjsec = null;
				String sj = map.get("fbsj").toString();
				sjyear = sj.substring(0, 4);
				sjmon = sj.substring(4, 6);
				sjday = sj.substring(6, 8);
				sjhour = sj.substring(8, 10);
				sjmin = sj.substring(10, 12);
				sjsec = sj.substring(12, 14);

				fbsj = sjyear + "��" + sjmon + "��" + sjday + "��" + " " + sjhour
						+ ":" + sjmin + ":" + sjsec;
				map.put("fbsj", fbsj);

				mssj = map.get("mssj");
				if (mssj != null) {
					if (mssj.length() > 9) {
						String day = mssj.substring(0, 10);
						map.put("day", day);
					}
					if (mssj.length() < 9) {
						mssj = "";
						map.put("mssj", mssj);
					}
					if (mssj.length() > 12) {
						String hour = mssj.substring(11, 13);
						map.put("hour", hour);
						String min = mssj.substring(14, 16);
						map.put("min", min);
					}
				}
			}
			request.setAttribute("rs", map);
		}
		return mapping.findForward("success");
	}

	// Ͷ�ͼ���
	private ActionForward zpxxSendGrjl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String sql = ""; // sql���
		DAO dao = DAO.getInstance();
		String realTable = "jygl_zpxxb";
		String tableName = "JYGL_ZPXXGLB";
		String doType = request.getParameter("doType");
		String gsmc = DealString.toGBK(request.getParameter("gsmc"));
		String zpzw = DealString.toGBK(request.getParameter("zpzw"));

		HashMap<String, String> map = new HashMap<String, String>();
		String act = request.getParameter("act");
		HttpSession session = request.getSession();
		String userName = session.getAttribute("userName").toString();

		if ("send".equals(act)) {

			sql = "select * from jygl_grjlb where xsxh=?";
			String[] stuinfo = dao.getOneRs(sql, new String[] { userName },
					new String[] { "xsxh" });
			if (null == stuinfo) {
				request.setAttribute("havegrjl", "no");
			} else {
				gsmc = DealString.toGBK(request.getParameter("gsmc"));
				zpzw = DealString.toGBK(request.getParameter("zpzw"));
				String tjsj = (dao
						.getOneRs(
								"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual", // ����ʱ��
								// ȡ�����ݿ���ʱ��
								new String[] {}, new String[] { "sdate" }))[0];

				sql = "select * from " + tableName
						+ " where gsmc=? and zpzw=? and xsxh=?";
				String[] zpinfo = dao.getOneRs(sql, new String[] { gsmc, zpzw,
						userName }, new String[] { "gsmc", "zpzw", "xsxh" });
				if (null != zpinfo) {
					request.setAttribute("insert", "no");
				} else {
					boolean judge = false;
					judge = StandardOperation.insert(tableName, new String[] {
							"gsmc", "zpzw", "xsxh", "tjsj" }, new String[] {
							gsmc, zpzw, userName, tjsj }, request);
					if (judge) {
						request.setAttribute("send", "ok");
					} else {
						request.setAttribute("send", "no");
					}
				}
			}
		}

		if ((doType == null) || doType.equalsIgnoreCase("")) {
			// �����쳣
			return mapping.findForward("false");
		} else if (doType.equalsIgnoreCase("view")) {
			// ��ѯ����
			sql = "select * from " + realTable + " where gsmc='" + gsmc
					+ "' and zpzw='" + zpzw + "'";
			String[] colList = dao
					.getColumnName("select * from jygl_zpxxb where 1=2"); // ������������
			String[] zpxxinfo = dao.getOneRs(sql, new String[] {}, colList);
			if (zpxxinfo != null) {
				for (int i = 0; i < colList.length; i++) {
					map.put(colList[i].toLowerCase(), zpxxinfo[i]); // ����¼ѭ������map��
				}
				String fbsj = null;
				String sjyear = null;
				String sjmon = null;
				String sjday = null;
				String sjhour = null;
				String sjmin = null;
				String sjsec = null;
				String sj = map.get("fbsj").toString();
				sjyear = sj.substring(0, 4);
				sjmon = sj.substring(4, 6);
				sjday = sj.substring(6, 8);
				sjhour = sj.substring(8, 10);
				sjmin = sj.substring(10, 12);
				sjsec = sj.substring(12, 14);

				fbsj = sjyear + "��" + sjmon + "��" + sjday + "��" + " " + sjhour
						+ ":" + sjmin + ":" + sjsec;
				map.put("fbsj", fbsj);
				String mssj;
				if (Base.isNull(map.get("mssj"))) {
					mssj = "";
				} else {
					mssj = map.get("mssj");
				}
				if (mssj.length() < 13 && mssj.length() > 9) {
					mssj = mssj.substring(0, 10);
					map.put("mssj", mssj);
				} else if (mssj.length() < 9) {
					mssj = "";
					map.put("mssj", mssj);
				}
				sql = "select xxsh from JYGL_GRJLB where xsxh=?";
				String[] stuinfo = dao.getOneRs(sql, new String[] { userName },
						new String[] { "xxsh" });
				if (stuinfo != null) {
					map.put("xxsh", stuinfo[0]);
				}
			}
		}
		request.setAttribute("rs", map);// �������ݼ�
		return mapping.findForward("success");
	}

	// ��ҵ�鿴���˼���ҳ���
	private ActionForward zpxxResultQuery(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response, String userType) throws Exception {
		DAO dao = DAO.getInstance();
		String sql = "";
		String[] colList = null;
		String[] colListCN = null;
		ArrayList<Object> rs = new ArrayList<Object>(); // ��ü�¼
		String rsNum = "0";
		String tableName = "JYGL_ZPXXGLB";
		List topTr = null;
		HashMap<String, String> map = new HashMap<String, String>();
		HttpSession session = request.getSession();
		String userName = session.getAttribute("userName").toString();
		String gsmc = "";
		String doType = request.getParameter("doType");

		if ("del".equals(doType)) {
			gsmc = DealString.toGBK(request.getParameter("gsmc"));
			String zpzw = DealString.toGBK(request.getParameter("zpzw"));
			String xsxh = request.getParameter("xsxh");

			boolean judge = false;
			sql = "delete from JYGL_ZPXXGLB where gsmc=? and zpzw=? and xsxh=?";
			judge = dao.runUpdate(sql, new String[] { gsmc, zpzw, xsxh });
			if (judge) {
				request.setAttribute("delete", "ok");

				// ɾ�������ļ�¼
				String whensj = (dao
						.getOneRs(
								"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual",
								new String[] {}, new String[] { "sdate" }))[0]; // �ύʱ��

				judge = StandardOperation.insert("JYGL_USERCZMXB",
						new String[] { "userid", "dowhat", "whichtable",
								"whichpk", "whensj" }, new String[] { userName,
								"ɾ��", "��Ƹ��Ϣ�����",
								"��˾����:" + gsmc + " ְλ:" + zpzw + " ѧ��:" + xsxh,
								whensj }, request);

			} else {
				request.setAttribute("delete", "no");
			}
		}

		if (userType.equals("teacher")) {
			sql = "select gsmc from jygl_jyzpwhb where username=?";
			String[] userinfo = dao.getOneRs(sql, new String[] { userName },
					new String[] { "gsmc" });
			if (userinfo != null) {
				gsmc = userinfo[0];
			}

			if (!"".equals(gsmc)) {
				sql = "select rownum �к�,a.* from " + tableName + " a "
						+ " where gsmc='" + gsmc + "'";
				colList = new String[] { "�к�", "gsmc", "zpzw", "xsxh", "tjsj" };
				colListCN = dao.getColumnNameCN(colList, tableName);
				topTr = dao.arrayToList(colList, colListCN);
				if ((request.getParameter("act") != null)
						&& request.getParameter("act").equalsIgnoreCase("go")) {
					rs.addAll(dao.rsToVator(sql, new String[] {}, colList));
					if (rs == null) {
						rsNum = "0";
					} else {
						rsNum = String.valueOf(rs.size());
					}
				}
			}
		}

		map.put("userName", userName);
		request.setAttribute("rs1", map);
		request.setAttribute("rs", rs); // �������ݼ�
		request.setAttribute("topTr", topTr); // ���ͱ�ͷ
		request.setAttribute("rsNum", rsNum); // ���ͼ�¼��
		return mapping.findForward("success");
	}

	// ��ҵ����鿴���˼�����ϸ��Ϣ
	private ActionForward zpxxViewMoreGrjlInfo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String pk = "xsxh";
		String sql = ""; // sql���
		DAO dao = DAO.getInstance();
		String realTable = "jygl_grjlb";
		String doType = request.getParameter("doType");
		String pkValue = request.getParameter("xsxh");
		HashMap<String, String> map = new HashMap<String, String>();

		if ((doType == null) || doType.equalsIgnoreCase("")) {
			// �����쳣
			return mapping.findForward("false");
		} else if (doType.equalsIgnoreCase("view")) {
			// ��ѯ����
			sql = "select * from " + realTable + " where " + pk + "='"
					+ pkValue + "'";
			String[] colList = dao
					.getColumnName("select * from jygl_grjlb where 1=2"); // ������������
			String[] stuinfo = dao.getOneRs(sql, new String[] {}, colList);
			sql = "select xymc from view_xsjbxx where xh=?";
			String[] xymc = dao.getOneRs(sql, new String[] { pkValue },
					new String[] { "xymc" });
			if (stuinfo != null) {
				for (int i = 0; i < colList.length; i++) {
					map.put(colList[i].toLowerCase(), stuinfo[i]); // ����¼ѭ������map��
				}
				map.put("xymc", xymc[0]);
				String fbsj = null;
				String shsj = null;
				String sjyear = null;
				String sjmon = null;
				String sjday = null;
				String sjhour = null;
				// ����ʱ��
				String sj = map.get("fbsj").toString();
				sjyear = sj.substring(0, 4);
				sjmon = sj.substring(4, 6);
				sjday = sj.substring(6, 8);
				sjhour = sj.substring(8, 10);
				fbsj = sjyear + "��" + sjmon + "��" + sjday + "��" + sjhour + "ʱ";
				map.put("fbsj", fbsj);
				// ���ʱ��
				if (null != map.get("shsj")) {
					sj = map.get("shsj").toString();
					sjyear = sj.substring(0, 4);
					sjmon = sj.substring(4, 6);
					sjday = sj.substring(6, 8);
					sjhour = sj.substring(8, 10);
					shsj = sjyear + "��" + sjmon + "��" + sjday + "��" + sjhour
							+ "ʱ";
					map.put("shsj", shsj);
				}
				if ("no".equals(map.get("idsee"))) {
					map.put("id", "");
				}
				if ("��ͨ��X".equals(map.get("xxsh"))
						|| "δ���".equals(map.get("xxsh"))) {
					HashMap<String, String> map1 = new HashMap<String, String>();
					map = map1;
					request.setAttribute("notice", "no");
				}
				sql = "select readtimes from " + realTable + " where xsxh=?";
				String[] readtimes = dao.getOneRs(sql,
						new String[] { pkValue }, new String[] { "readtimes" });
				int timeint = Integer.parseInt(readtimes[0]);
				timeint += 1;
				String tinestr = String.valueOf(timeint);
				StandardOperation.update(realTable,
						new String[] { "readtimes" }, new String[] { tinestr },
						pk, pkValue, request);
			}
		}
		request.setAttribute("rs", map);// �������ݼ�
		return mapping.findForward("success");
	}

	// ��ҵ�������-����
	private ActionForward zpxxReturnMess(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response, String userType) throws Exception {
		DAO dao = DAO.getInstance();
		String tableName = "jygl_qyyjfkb";
		HashMap<String, String> map = new HashMap<String, String>();
		String sql = "";
		String gsmc = "";
		String doType = request.getParameter("doType");

		HttpSession session = request.getSession();
		String userName = session.getAttribute("userName").toString();
		String user = session.getAttribute("userType").toString();
		sql = "select gsmc from jygl_jyzpwhb where username=?";
		String[] gsinfo = dao.getOneRs(sql, new String[] { userName },
				new String[] { "gsmc" });
		if (gsinfo != null) {
			gsmc = gsinfo[0];
			request.setAttribute("updategsmc", "no");
		} else if ("admin".equals(user)) {
			request.setAttribute("updategsmc", "ok");
		}
		map.put("gsmc", gsmc);

		if ("save".equals(doType)) {
			gsmc = DealString.toGBK(request.getParameter("gsmc"));
			String dwxz = DealString.toGBK(request.getParameter("dwxz")); // ��λ����
			String hyfl = DealString.toGBK(request.getParameter("hyfl")); // ��ҵ����
			String zyzs1 = DealString.toGBK(request.getParameter("zyzs1")); // רҵ֪ʶ1
			String zyzs2 = DealString.toGBK(request.getParameter("zyzs2")); // רҵ֪ʶ2
			String gzbx1 = DealString.toGBK(request.getParameter("gzbx1")); // ��������1
			String gzbx2 = DealString.toGBK(request.getParameter("gzbx2")); // ��������2
			String jnjq1 = DealString.toGBK(request.getParameter("jnjq1")); // ���ܼ���1
			String jnjq2 = DealString.toGBK(request.getParameter("jnjq2")); // ���ܼ���2
			String jnjq3 = DealString.toGBK(request.getParameter("jnjq3")); // ���ܼ���3
			String jnjq4 = DealString.toGBK(request.getParameter("jnjq4")); // ���ܼ���4
			String jnjq5 = DealString.toGBK(request.getParameter("jnjq5")); // ���ܼ���5
			String myd = DealString.toGBK(request.getParameter("myd")); // ���������
			String yjfkbt = DealString.toGBK(request.getParameter("yjfkbt"));// �����������
			String yjfknr = DealString.toGBK(request.getParameter("yjfknr"));// �����������
			String fksj = (dao
					.getOneRs(
							"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual",
							new String[] {}, new String[] { "sdate" }))[0]; // �ύʱ��
			String fknd = fksj.substring(0, 4); // �������

			boolean judge = false;
			judge = StandardOperation.insert(tableName, new String[] { "gsmc",
					"dwxz", "hyfl", "zyzs1", "zyzs2", "gzbx1", "gzbx2",
					"jnjq1", "jnjq2", "jnjq3", "jnjq4", "jnjq5", "myd", "fksj",
					"fknd", "yjfkbt", "yjfknr" }, new String[] { gsmc, dwxz,
					hyfl, zyzs1, zyzs2, gzbx1, gzbx2, jnjq1, jnjq2, jnjq3,
					jnjq4, jnjq5, myd, fksj, fknd, yjfkbt, yjfknr }, request);
			if (judge) {
				request.setAttribute("insert", "ok");

				// ���Ӳ����ļ�¼
				String whensj = (dao
						.getOneRs(
								"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual",
								new String[] {}, new String[] { "sdate" }))[0]; // �ύʱ��

				StandardOperation.insert("JYGL_USERCZMXB",
						new String[] { "userid", "dowhat", "whichtable",
								"whichpk", "whensj" }, new String[] { userName,
								"����", "��ҵ���������", "��˾����:" + gsmc, whensj },
						request);

			} else {
				request.setAttribute("insert", "no");
			}
		}

		sql = "select dwxzdm,dwxz from dmk_dwxz2"; // ��λ���ʴ���2
		List dwxzdm2List = dao.getList(sql, new String[] {}, new String[] {
				"dwxzdm", "dwxz" });
		request.setAttribute("dwxzdm2List", dwxzdm2List);

		sql = "select hyfldm,hyfl from dmk_hyfl"; // ��ҵ����
		List hyflList = dao.getList(sql, new String[] {}, new String[] {
				"hyfldm", "hyfl" });
		request.setAttribute("hyflList", hyflList);

		request.setAttribute("rs", map);
		return mapping.findForward("success");
	}

	// ��ҵ������Ϣ��ѯ
	private ActionForward zpxxReturnMessQuery(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response, String userType) throws Exception {

		DAO dao = DAO.getInstance();
		String sql = "";
		String rsNum = "0";
		ArrayList<Object> rs = new ArrayList<Object>();
		String[] colList = null;
		String[] colListCN = null;
		String tableName = "jygl_qyyjfkb";
		String gsmc = DealString.toGBK(request.getParameter("gsmc"));
		String myd = DealString.toGBK(request.getParameter("myd"));
		String dwxz = DealString.toGBK(request.getParameter("dwxz"));
		String hyfl = DealString.toGBK(request.getParameter("hyfl"));
		String fknd = DealString.toGBK(request.getParameter("fknd"));
		String doType = request.getParameter("doType");
		String doType2 = request.getParameter("doType2");

		sql = "select hyfldm,hyfl from dmk_hyfl"; // ��ҵ����
		List hyflList = dao.getList(sql, new String[] {}, new String[] {
				"hyfldm", "hyfl" });
		request.setAttribute("hyflList", hyflList);

		sql = "select dwxzdm,dwxz from dmk_dwxz2"; // ��λ����
		List dwxzList = dao.getList(sql, new String[] {}, new String[] {
				"dwxzdm", "dwxz" });
		request.setAttribute("dwxzList", dwxzList);

		String querry = " where 1=1 ";
		HashMap<String, String> map = new HashMap<String, String>();

		sql = "select gsmc from jygl_qyyjfkb where rowid=?";
		String pkval = request.getParameter("pkValue");
		String[] yjfkinfo = dao.getOneRs(sql, new String[] { pkval },
				new String[] { "gsmc" });

		if ("del".equals(doType2)) {
			// ɾ������
			boolean judge = false;
			String pkValue = request.getParameter("pkValue");
			pkValue = pkValue.replaceAll(" ", "+");
			sql = "delete from " + tableName + " where rowid='" + pkValue + "'";
			judge = dao.runUpdate(sql, new String[] {});
			if (judge) {
				request.setAttribute("delete", "ok");

				// ɾ�������ļ�¼

				HttpSession session = request.getSession();
				String userName = session.getAttribute("userName").toString();
				String whensj = (dao
						.getOneRs(
								"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual",
								new String[] {}, new String[] { "sdate" }))[0]; // �ύʱ��

				judge = StandardOperation.insert("JYGL_USERCZMXB",
						new String[] { "userid", "dowhat", "whichtable",
								"whichpk", "whensj" },
						new String[] { userName, "ɾ��", "��ҵ���������",
								"��˾����:" + yjfkinfo[0], whensj }, request);
			} else {
				request.setAttribute("delete", "no");
			}
		}

		if ("query".equals(doType)) {
			map.put("gsmc", gsmc);
			map.put("myd", myd);
			map.put("hyfl", hyfl);
			map.put("dwxz", dwxz);
			map.put("fknd", fknd);
		}

		if (gsmc == null) {
			gsmc = "";
		}
		if (myd == null) {
			myd = "";
		}
		if (hyfl == null) {
			hyfl = "";
		}
		if (dwxz == null) {
			dwxz = "";
		}
		if (fknd == null) {
			fknd = "";
		}
		if ((gsmc == null) || gsmc.equals("")) {
			querry += "and 1=1 ";
		} else {
			querry += "and gsmc like '%" + gsmc + "%' ";
		}
		if ((myd == null) || myd.equals("")) {
			querry += "and 1=1 ";
		} else {
			querry += "and myd = '" + myd + "' ";
		}
		if ((hyfl == null) || hyfl.equals("")) {
			querry += "and 1=1 ";
		} else {
			querry += "and hyfl = '" + hyfl + "' ";
		}
		if ((dwxz == null) || dwxz.equals("")) {
			querry += "and 1=1 ";
		} else {
			querry += "and dwxz = '" + dwxz + "' ";
		}
		if ((fknd == null) || fknd.equals("")) {
			querry += "and 1=1 ";
		} else {
			querry += "and fknd = '" + fknd + "' ";
		}
		sql = "select  rownum �к�,a.* ,a.rowid from " + tableName + " a "
				+ querry;
		colList = new String[] { "�к�", "fknd", "gsmc", "hyfl", "dwxz", "myd",
				"fksj" };
		colListCN = dao.getColumnNameCN(colList, tableName);
		List topTr = dao.arrayToList(colList, colListCN);
		colList = new String[] { "rowid", "�к�", "fknd", "gsmc", "hyfl", "dwxz",
				"myd", "fksj" };
		if ((request.getParameter("act") != null)
				&& request.getParameter("act").equalsIgnoreCase("go")) {
			rs.addAll(dao.rsToVator(sql, new String[] {}, colList));
			if (rs == null) {
				rsNum = "0";
			} else {
				rsNum = String.valueOf(rs.size());
			}
		}

		request.setAttribute("rs", rs);// �������ݼ�
		request.setAttribute("topTr", topTr);// ���ͱ�ͷ
		request.setAttribute("rsNum", rsNum);// ���ͼ�¼��
		request.setAttribute("rs1", map);// �Ѳ�ѯ��������ȥ
		return mapping.findForward("success");

	}

	// ��ҵ������Ϣ��ѯ-�鿴��ϸ��Ϣ
	private ActionForward zpxxReturnMessMoreQuery(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response, String userType) throws Exception {

		String pk = "rowid";
		String sql = ""; // sql���
		DAO dao = DAO.getInstance();
		String realTable = "jygl_qyyjfkb";
		String doType = request.getParameter("doType");
		String pkValue = request.getParameter("pkValue");
		pkValue = pkValue.replaceAll(" ", "+");
		HashMap<String, String> map = new HashMap<String, String>();

		if ((doType == null) || doType.equalsIgnoreCase("")) {
			// �����쳣
			return mapping.findForward("false");
		} else if (doType.equalsIgnoreCase("view")) {
			// ��ѯ����
			sql = "select * from " + realTable + " where " + pk + "='"
					+ pkValue + "'";
			String[] colList = dao
					.getColumnName("select * from jygl_qyyjfkb where 1=2"); // ������������
			String[] stuinfo = dao.getOneRs(sql, new String[] {}, colList);
			if (stuinfo != null) {
				for (int i = 0; i < colList.length; i++) {
					map.put(colList[i].toLowerCase(), stuinfo[i]); // ����¼ѭ������map��
				}
				String fksj = null;
				String sjyear = null;
				String sjmon = null;
				String sjday = null;
				// ����ʱ��ת��
				String sj = map.get("fksj").toString();
				sjyear = sj.substring(0, 4);
				sjmon = sj.substring(4, 6);
				sjday = sj.substring(6, 8);
				fksj = sjyear + "��" + sjmon + "��" + sjday + "��";
				map.put("fksj", fksj);
			}
		}
		request.setAttribute("rs", map);// �������ݼ�
		return mapping.findForward("success");
	}

	/*
	 * ����������ѯ��ҳ��
	 */
	private ActionForward turndmkqueryjsp(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response, String userType) {
		DAO dao = DAO.getInstance();
		HttpSession session = request.getSession();
		String sql = "";
		CommanForm turnForm = new CommanForm();
		HashMap<String, String> map = new HashMap<String, String>();
		String xh = turnForm.getXh();
		if (userType.equalsIgnoreCase("student")) {
			xh = session.getAttribute("userName").toString();
		}
		sql = "select a.*,b.lxdh lxdh1,b.sqyy,b.bz bz1 from view_xsjbxx a left join knssqb b on a.xh=b.xh where a.xh=?";
		String[] colList = dao
				.getColumnName("select a.*,b.lxdh lxdh1,b.sqyy,b.bz bz1 from view_xsjbxx a,knssqb b where a.xh=b.xh and 1=2");
		String[] rs = dao.getOneRs(sql, new String[] { xh }, colList);
		if (rs != null) {
			for (int i = 0; i < colList.length; i++) {
				map.put(colList[i].toLowerCase(), rs[i]);
			}
		}
		map.put("stuExists", "yes");
		map.put("userType", userType);
		request.setAttribute("rs", map);
		return mapping.findForward("success");
	}

	/*
	 * ������ѯ
	 */
	public ActionForward dmkquery(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String sjzd = (String) request.getParameter("sjzd");
		String dm = null;
		String mc = null;
		String quesType = (String) request.getParameter("myselect");
		if (!"".equals(request.getParameter("dm"))
				&& request.getParameter("dm") != null) {
			dm = request.getParameter("dm");
		}
		if (!"".equals(request.getParameter("mc"))
				&& request.getParameter("mc") != null) {
			mc = DealString.toGBK(request.getParameter("mc"));
		}
		ByqxDao byqxDao = new ByqxDao(); // ��ҵȥ��
		BzpyfsDao bzgzpyfsDao = new BzpyfsDao(); // ��ר������ʽ
		BzgzzyDao bzgzzyDao = new BzgzzyDao(); // ��ר��ְרҵ
		DwdqDao dwdqDao = new DwdqDao(); // ��λ����
		Dwxz2Dao dwxz2Dao = new Dwxz2Dao(); // ��λ����2
		DwxzDao dwxzDao = new DwxzDao(); // ��λ����
		HyflDao hyflDao = new HyflDao(); // ��ҵ����
		JzzbzDao jzzbzDao = new JzzbzDao(); // ��ס֤�������־λ
		SydqDao sydqDao = new SydqDao(); // ��Դ����
		SydzgbmDao sydzgdwDao = new SydzgbmDao(); // ��Դ�����ܲ���
		YjspyfsDao yjspyfsDao = new YjspyfsDao(); // �о���������ʽ
		YjszyDao yjszyDao = new YjszyDao(); // �о���רҵ
		ZgbmDao zgbmDao = new ZgbmDao(); // ���ܲ���
		XbDao xbDao = new XbDao(); // �Ա�
		XlDao xlDao = new XlDao(); // ѧ��
		ZzmmDao zzmmDao = new ZzmmDao(); // ������ò
		DqlxDao dqlxDao = new DqlxDao(); // ��������
		ArrayList list = new ArrayList();
		String dmkmc = null;
		String rsNum = "0";// ���صļ�¼��

		if (quesType.equals("2")) {
			if (sjzd.equals("yjszy")) {
				list = yjszyDao.getList(dm, mc);
				dmkmc = "�о���רҵ�����";
			} else if (sjzd.equals("bzgzzy")) {
				list = bzgzzyDao.getList(dm, mc);
				dmkmc = "��ר��ְרҵ�����";
			} else if (sjzd.equals("sydq")) {
				list = sydqDao.getList(dm, mc);
				dmkmc = "��Դ���������";
			} else if (sjzd.equals("yjspyfs")) {
				list = yjspyfsDao.getList(dm, mc);
				dmkmc = "�о���������ʽ�����";
			} else if (sjzd.equals("bzgzpyfs")) {
				list = bzgzpyfsDao.getList(dm, mc);
				dmkmc = "��ר������ʽ�����";
			} else if (sjzd.equals("dwdq")) {
				list = dwdqDao.getList(dm, mc);
				dmkmc = "��λ���������";
			} else if (sjzd.equals("byqx")) {
				list = byqxDao.getList(dm, mc);
				dmkmc = "��ҵȥ������";
			} else if (sjzd.equals("jzzbz")) {
				list = jzzbzDao.getList(dm, mc);
				dmkmc = "��ס֤�������־λ�����";
			} else if (sjzd.equals("sydzgdw")) {
				list = sydzgdwDao.getList(dm, mc);
				dmkmc = "��Դ�����ܲ��Ŵ����";
			} else if (sjzd.equals("dwxz")) {
				list = dwxzDao.getList(dm, mc);
				dmkmc = "��λ���ʴ����";
			} else if (sjzd.equals("dwxz2")) {
				list = dwxz2Dao.getList(dm, mc);
				dmkmc = "��λ����2�����";
			} else if (sjzd.equals("zgbm")) {
				list = zgbmDao.getList(dm, mc);
				dmkmc = "���ܲ��Ŵ����";
			} else if (sjzd.equals("hyfl")) {
				list = hyflDao.getList(dm, mc);
				dmkmc = "��ҵ��������";
			} else if (sjzd.equals("xb")) {
				list = xbDao.getList(dm, mc);
				dmkmc = "�Ա�����";
			} else if (sjzd.equals("xl")) {
				list = xlDao.getList(dm, mc);
				dmkmc = "ѧ�������";
			} else if (sjzd.equals("zzmm")) {
				list = zzmmDao.getList(dm, mc);
				dmkmc = "������ò�����";
			} else if (sjzd.equals("dqlx")) {
				list = dqlxDao.getList(dm, mc);
				dmkmc = "������������";
			}

		} else {
			if (sjzd.equals("yjszy")) {
				list = yjszyDao.getListJ(dm, mc);
			} else if (sjzd.equals("bzgzzy")) {
				list = bzgzzyDao.getListJ(dm, mc);
			} else if (sjzd.equals("sydq")) {
				list = sydqDao.getListJ(dm, mc);
			} else if (sjzd.equals("yjspyfs")) {
				list = yjspyfsDao.getListJ(dm, mc);
			} else if (sjzd.equals("bzgzpyfs")) {
				list = bzgzpyfsDao.getListJ(dm, mc);
			} else if (sjzd.equals("dwdq")) {
				list = dwdqDao.getListJ(dm, mc);
			} else if (sjzd.equals("byqx")) {
				list = byqxDao.getListJ(dm, mc);
			} else if (sjzd.equals("jzzbz")) {
				list = jzzbzDao.getListJ(dm, mc);
			} else if (sjzd.equals("sydzgdw")) {
				list = sydzgdwDao.getListJ(dm, mc);
			} else if (sjzd.equals("dwxz")) {
				list = dwxzDao.getListJ(dm, mc);
			} else if (sjzd.equals("dwxz2")) {
				list = dwxz2Dao.getListJ(dm, mc);
			} else if (sjzd.equals("zgbm")) {
				list = zgbmDao.getListJ(dm, mc);
			} else if (sjzd.equals("hyfl")) {
				list = hyflDao.getListJ(dm, mc);
			} else if (sjzd.equals("xb")) {
				list = xbDao.getListJ(dm, mc);
			} else if (sjzd.equals("xl")) {
				list = xlDao.getListJ(dm, mc);
			} else if (sjzd.equals("zzmm")) {
				list = zzmmDao.getListJ(dm, mc);
			} else if (sjzd.equals("dqlx")) {
				list = dqlxDao.getListJ(dm, mc);
			}
		}
		rsNum = String.valueOf(list.size());
		request.setAttribute("rsNum", rsNum);
		request.setAttribute("list", list);
		request.setAttribute("dmkmc", dmkmc);
		return mapping.findForward("success");
	}

	// Ϊѧ����Ϣ�ϱ���ѯ����
	private ActionForward JyglQueryStuInfo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// ���ݲ�ѯ
		CommanForm dataSearchForm = (CommanForm) form;
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userOnLine").toString();
		String userSpecType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		boolean disabled = true;
		if (userType.equalsIgnoreCase("student")) {
			String xh = session.getAttribute("userName").toString();
			return new ActionForward("/stu_info_details.do?xh=" + xh, false);
		}
		DAO dao = DAO.getInstance();
		String act = request.getParameter("act");
		ArrayList<Object> rs = new ArrayList<Object>();
		String[] colList = null;
		String[] colListCN = null;
		String sql = "";// sql���
		String querry = " where 1=1 ";// sql����
		String rsNum = "0";// ���صļ�¼��
		String nj = request.getParameter("nj");
		String xy = request.getParameter("xydm");
		String zy = request.getParameter("zydm");
		String bj = request.getParameter("bjdm");
		String xh = request.getParameter("xh");
		String xm = request.getParameter("xm");
		if (xy == null) {
			xy = "";
		}
		if (zy == null) {
			zy = "";
		}
		if ((nj == null) || nj.equalsIgnoreCase("")) {
			querry += "and 1=1 ";
		} else {
			querry += "and nj = '" + nj + "' ";
		}
		if ((xy == null) || xy.equalsIgnoreCase("")) {
			querry += "and 1=1 ";
		} else {
			querry += "and xydm = '" + xy + "' ";
		}
		if ((zy == null) || zy.equalsIgnoreCase("")) {
			querry += "and 1=1 ";
		} else {
			querry += "and zydm = '" + zy + "' ";
		}
		if ((bj == null) || bj.equalsIgnoreCase("")) {
			querry += "and 1=1 ";
		} else {
			querry += "and bjdm = '" + bj + "' ";
		}
		if ((xh == null) || xh.equalsIgnoreCase("")) {
			querry += "and 1=1 ";
		} else {
			querry += "and xh = '" + xh + "' ";
		}
		if ((xm == null) || xm.equalsIgnoreCase("")) {
			querry += "and 1=1 ";
		} else {
			querry += "and xm like '%" + DealString.toGBK(xm) + "%' ";
			dataSearchForm.setXm(DealString.toGBK(xm));
		}

		colList = new String[] { "�к�", "xh", "xm", "xb", "nj", "bjmc" };
		sql = "select rownum �к�,a.* from view_xsjbxx a" + querry;
		colListCN = dao.getColumnNameCN(colList, "view_xsjbxx");
		List topTr = dao.arrayToList(colList, colListCN);
		if ("go".equals(act)) {
			rs.addAll(dao.rsToVator(sql, new String[] {}, colList));
			if (rs == null) {
				rsNum = "0";
			} else {
				rsNum = String.valueOf(rs.size());
			}
		}
		if (userSpecType.equalsIgnoreCase("xy")
				&& (xy == null || xy.equalsIgnoreCase(""))) {
			xy = userDep;
			dataSearchForm.setXydm(xy);
			disabled = false;
		}
		request.setAttribute("disabled", disabled);
		request.setAttribute("njList", dao.getNjList());// �����꼶�б�
		request.setAttribute("xyList", dao.getXyList());// ����ѧԺ�б�
		request.setAttribute("zyList", dao.getZyList(xy));// ����רҵ�б�
		request.setAttribute("bjList", dao.getBjList(xy, zy));// ����רҵ�б�
		request.setAttribute("rs", rs);// �������ݼ�
		request.setAttribute("topTr", topTr);// ���ͱ�ͷ
		request.setAttribute("rsNum", rsNum);// ���ͼ�¼��
		return mapping.findForward("success");
	}

	// ��ҵ����Ϣ�ϱ�ҳ������ݻ�ò�����
	private ActionForward bysxxplsb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			String userType) throws Exception {
		return mapping.findForward("success");
	}

	public ActionForward ynysBysxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		String[] rs = null;
		HashMap<String, String> map = new HashMap<String, String>();
		String xh = request.getParameter("xh");
		String sql = "";
		StuInfoDAO stuDao = new StuInfoDAO();
		request.setAttribute("njList", dao.getNjList());// �����꼶�б�

		sql = "select zzmmdm,zzmm from dmk_zzmm"; // ������ò����
		List zzmmdmList = dao.getList(sql, new String[] {}, new String[] {
				"zzmmdm", "zzmm" });
		request.setAttribute("zzmmdmList", zzmmdmList);

		sql = "select sydqdm,sydq from dmk_sydq"; // ��Դ����
		List sydqdmList = dao.getList(sql, new String[] {}, new String[] {
				"sydqdm", "sydq" });
		request.setAttribute("sydqdmList", sydqdmList);

		sql = "select dwxzdm,dwxz from dmk_dwxz"; // ��ҵ����
		List dwxzdmList = dao.getList(sql, new String[] {}, new String[] {
				"dwxzdm", "dwxz" });
		request.setAttribute("dwxzdmList", dwxzdmList);

		sql = "select byqxdm,byqx from dmk_byqx"; // ��ҵ��ʽ
		List byqxdmList = dao.getList(sql, new String[] {}, new String[] {
				"byqxdm", "byqx" });
		request.setAttribute("byqxdmList", byqxdmList);

		sql = "select distinct zymc from view_njxyzybj";// רҵ����
		List zyList = dao
				.getList(sql, new String[] {}, new String[] { "zymc" });
		request.setAttribute("zyList", zyList);

		sql = "select distinct bjmc from view_njxyzybj";// �༶����
		List bjList = dao
				.getList(sql, new String[] {}, new String[] { "bjmc" });
		request.setAttribute("bjList", bjList);

		sql = "select mzmc,mzdm from mzdmb";// ����
		List mzList = dao.getList(sql, new String[] {}, new String[] { "mzmc",
				"mzdm" });
		request.setAttribute("mzList", mzList);

		sql = "select * from view_xsjbxx where XH=?"; // ��ѯ��ѧ�ŵ�������ݵ�sql���
		String[] colList = dao
				.getColumnName("select * from view_xsjbxx where 1=2"); // ������������
		rs = dao.getOneRs(sql, new String[] { xh }, colList); // ��ôӱ�ҵ��������Ϣ����ͼ���в�ѯ�ļ�¼��

		if (rs != null) {
			for (int i = 0; i < colList.length; i++) {
				map.put(colList[i].toLowerCase(), rs[i]); // ����¼ѭ������map��
			}
		}

		if ("save".equals(request.getParameter("doType"))) {
			String xsxh = request.getParameter("xh");
			String name = DealString.toGBK(request.getParameter("xm"));
			String xb = DealString.toGBK(request.getParameter("xb"));
			String nj = request.getParameter("nj");
			String zymc = DealString.toGBK(request.getParameter("zymc"));
			String sydq = request.getParameter("jgs");
			String jgshi = request.getParameter("jgshi");
			String jgx = request.getParameter("jgx");
			String csrq = DealString.toGBK(request.getParameter("csrq"));
			String mz = DealString.toGBK(request.getParameter("mz"));
			String zzmm = DealString.toGBK(request.getParameter("zzmm"));
			String lxdh = request.getParameter("lxdh");
			String nd = request.getParameter("nd");
			String bynd = request.getParameter("bynd");
			String qkqk = DealString.toGBK(request.getParameter("qkqk"));
			String jcqk = DealString.toGBK(request.getParameter("jcqk"));
			String jyyx = DealString.toGBK(request.getParameter("jyyx"));
			String jyfs = DealString.toGBK(request.getParameter("jyfs"));
			String sbsj = (dao
					.getOneRs(
							"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual",
							new String[] {}, new String[] { "sdate" }))[0]; // �ϱ�ʱ��

			boolean judge = false;
			judge = StandardOperation.insert("jygl_xsjbxxb", new String[] {
					"xsxh", "name", "xb", "nj", "zymc", "sydqdm", "jgshi",
					"jgx", "csrq", "mz", "zzmm", "lxdh", "nd", "bynd", "qkqk",
					"jcqk", "jyyx", "jyfs", "sbsj" }, new String[] { xsxh,
					name, xb, nj, zymc, sydq, jgshi, jgx, csrq, mz, zzmm, lxdh,
					nd, bynd, qkqk, jcqk, jyyx, jyfs, sbsj }, request);
			if (judge) {
				request.setAttribute("inserted", "ok");
			} else {
				request.setAttribute("inserted", "no");
			}
		}
		map.put("1", "1");
		request.setAttribute("rs", map);
		request.setAttribute("realTable", "jygl_xsjbxxb"); // ����ѧ��������Ϣ��

		request.setAttribute("shiList", stuDao.getShiList("").get("shiList"));
		request.setAttribute("xianList", stuDao.getShiList("").get("xianList"));
		request.setAttribute("ssList", stuDao.getSsList());
		return mapping.findForward("success2");
	}

	// ��ҵ����Ϣ�ϱ�ҳ������ݻ�ò�����
	private ActionForward bysxxapply(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			String userType) throws Exception {
		DAO dao = DAO.getInstance();
		String xxdm = Base.xxdm;
		String xxmc = Base.xxmc;
		HttpSession session = request.getSession(); // ��ûỰ
		String sql = ""; // ����һ����sql�ַ�������
		String xslb = ""; // ѧ�����
		String[] rs = null;
		HashMap<String, String> map = new HashMap<String, String>(); // ����hashmap��ʽ�����飬hashmap���и��ô����Ǽ�ֵ��Ӧ
		String xh = request.getParameter("xh");

		if (xxdm.equals(Globals.XXDM_YNYS)) {
			return ynysBysxx(mapping, form, request, response);
		}
		String zydm = DealString.toGBK(request.getParameter("zydm"));
		sql = "select sydqdm,sydq from dmk_sydq"; // ��Դ����
		List sydqdmList = dao.getList(sql, new String[] {}, new String[] {
				"sydqdm", "sydq" });
		request.setAttribute("sydqdmList", sydqdmList);
		// רҵ����
		String titName = request.getParameter("titName");
		// ��ͷ��
		String dotpye = request.getParameter("doType");
		String act = request.getParameter("act");
		// �Ƿ����save����
		String[] pages = null;
		String[] titNames = null;
		if (xxdm.equals(Globals.XXDM_ZJJJZYJSXY)) {
			pages = new String[] { "ר����" };
			titNames = new String[] { "zks" };
		} else {
			pages = new String[] { "ר����", "������", "�о���" };
			titNames = new String[] { "zks", "bks", "yjs" };
		}
		List pagesList = dao.arrayToList(titNames, pages); // �ѱ�ͷ��Ϣװ������
		request.setAttribute("pages", pagesList);
		if (titName == null)
			titName = titNames[0];

		request.setAttribute("titName", titName);

		if (titName == null || titName.equals("zks")) {
			sql = "select pyfsdm,pyfs from dmk_bzpyfs";
			List pyfsList = dao.getList(sql, new String[] {}, new String[] {
					"pyfsdm", "pyfs" });
			request.setAttribute("pyfsList", pyfsList);
		} else if (titName == null || titName.equals("bks")) {
			sql = "select pyfsdm,pyfs from dmk_bzpyfs";
			List pyfsList = dao.getList(sql, new String[] {}, new String[] {
					"pyfsdm", "pyfs" });
			request.setAttribute("pyfsList", pyfsList);
		} else if (titName.equals("yjs")) {
			sql = "select pyfsdm,pyfs from dmk_yjspyfs";
			List pyfsList = dao.getList(sql, new String[] {}, new String[] {
					"pyfsdm", "pyfs" });
			request.setAttribute("pyfsList", pyfsList);
		}

		if (userType.equalsIgnoreCase("student")) { // �˶��û����ͣ������ѧ����½��ѧ�ž�ֱ���Ǹ�ѧ����ѧ����
			xh = session.getAttribute("userName").toString();
		}

		if (!"cancle".equalsIgnoreCase(act)) {
			if ((Globals.XXDM_ZGDZDX).equals(xxdm)) {
				sql = "SELECT v.* FROM view_xsjbxx v WHERE v.xh=?"; // ��ѯ��ѧ�ŵ�������ݵ�sql���
				String[] colListdz2 = dao
						.getColumnName("select * from view_xsjbxx where 1=2"); // ������������
				String[] colListdz1 = { "xydm", "xymc", "bjmc", "zymc" };
				String[] colList1 = new String[colListdz1.length
						+ colListdz2.length];
				System.arraycopy(colListdz2, 0, colList1, 0, colListdz2.length);
				System.arraycopy(colListdz1, 0, colList1, colListdz2.length,
						colListdz1.length);
				rs = dao.getOneRs(sql, new String[] { xh }, colList1); // ��ôӱ�ҵ��������Ϣ����ͼ���в�ѯ�ļ�¼��

				if (rs != null) {
					for (int i = 0; i < colList1.length; i++) {
						map.put(colList1[i].toLowerCase(), rs[i]); // ����¼ѭ������map��
					}
					String nd = map.get("nj");
					map.put("nd", nd);
				}
				if (Globals.XXDM_ZGDZDX.equals(xxdm)) {
					if ("1".equals(map.get("xbm"))) {
						map.put("xbdm", "��");
					} else {
						map.put("xbdm", "Ů");
					}
				}
				zydm = map.get("zydm");
				sql = "select jygl_zydm,jygl_zymc from DMK_ZYDMDZB where jwc_zydm=?";
				String[] dminfo = dao.getOneRs(sql, new String[] { zydm },
						new String[] { "jygl_zydm", "jygl_zymc" });
				if (null != dminfo) {
					map.put("zydm", dminfo[0]);
					map.put("zymc", dminfo[1]);
				}
				if (null != rs) {
					request.setAttribute("nochange", "yes");
				}
				sql = "select mzmc from mzdmb";// ����
				List mzList = dao.getList(sql, new String[] {},
						new String[] { "mzmc" });
				request.setAttribute("mzList", mzList);
			} else {
				String[] colList1 = null; // ������������
				String isstu = (String) request.getSession().getAttribute(
						"userType");
				if (Globals.XXDM_ZGMSXY.equalsIgnoreCase(xxdm)
						&& "stu".equals(isstu)) {
					sql = "SELECT distinct * from jygl_xsjbxxb a LEFT JOIN bks_xsjbxx b ON a.xsxh=b.xh where xsxh=?"; // ��ѯ��ѧ�ŵ�������ݵ�sql���
					colList1 = dao
							.getColumnName("SELECT * from jygl_xsjbxxb,bks_xsjbxx where 1=2");
				} else {
					// sql = "select * from jygl_xsjbxxb where xsxh=?"; //
					// ��ѯ��ѧ�ŵ�������ݵ�sql���
					// colList1 =
					// dao.getColumnName("SELECT * from jygl_xsjbxxb,bks_xsjbxx
					// where 1=2");
					// sql =
					// "SELECT distinct * FROM bks_xsjbxx b left JOIN
					// jygl_xsjbxxb a ON a.xsxh=b.xh WHERE xh=?";
					// // ��ѯ��ѧ�ŵ�������ݵ�sql���
					sql = "select * from view_xsjbxx where xh=?";
					colList1 = dao
							.getColumnName("select * from view_xsjbxx where 1=2");
				}
				rs = dao.getOneRs(sql, new String[] { xh }, colList1); // ��ôӱ�ҵ��������Ϣ����ͼ���в�ѯ�ļ�¼��
				if (rs != null) {
					for (int i = 0; i < colList1.length; i++) {
						map.put(colList1[i].toLowerCase(), rs[i]); // ����¼ѭ������map��
					}
					if (map.get("nd") == null || "".equals(map.get("nd"))
							|| "null".equals(map.get("nd"))) {
						String nd = map.get("nj");
						map.put("nd", nd);
					}
					if (map.get("sfzh") == null || "".equals(map.get("sfzh"))) {
						String sfzh1 = map.get("id");
						map.put("sfzh", sfzh1);
					}
					if ("��".equals(map.get("xb"))) {
						map.put("xbm", "1");
					} else {
						map.put("xbm", "2");
					}
				}
				zydm = map.get("zydm");
				sql = "select jygl_zydm,jygl_zymc from DMK_ZYDMDZB where jwc_zydm=?";
				String[] dminfo = dao.getOneRs(sql, new String[] { zydm },
						new String[] { "jygl_zydm", "jygl_zymc" });
				if (null != dminfo) {
					map.put("zydm", dminfo[0]);
					map.put("zymc", dminfo[1]);
				}
				if (null != rs) {
					request.setAttribute("nochange", "yes");
				}
			}
		}
		if (null != rs && "query".equals(act)) {
			request.setAttribute("nochange", "yes");
		} else if (null == rs) {
			request.setAttribute("nochange", "no");
		}

		// �ж�ѧ�����ѡ�����ĸ�ѧ������
		if (titName.equals("zks")) {
			request.setAttribute("Listxl", "zks");
		}
		if (titName.equals("bks")) {
			request.setAttribute("Listxl", "bks");
		}
		if (titName.equals("yjs")) {
			request.setAttribute("Listxl", "yjs");
		}

		sql = "select sydqdm,sydq from dmk_sydq"; // ��Դ����
		List sydqList = dao.getList(sql, new String[] {}, new String[] {
				"sydqdm", "sydq" });
		request.setAttribute("sydqList", sydqList);

		map.put("stuExists", "yes");
		map.put("userType", userType);

		map.put("xxdm", xxdm);
		map.put("xxmc", xxmc);

		request.setAttribute("rs", map);
		StuInfoDAO stuDao = new StuInfoDAO();
		request.setAttribute("shiList", stuDao.getShiList("").get("shiList"));
		request.setAttribute("xianList", stuDao.getShiList("").get("xianList"));
		request.setAttribute("ssList", stuDao.getSsList());
		sql = "select zzmmdm,zzmm from dmk_zzmm"; // ������ò
		List zzmmList = dao.getList(sql, new String[] {}, new String[] {
				"zzmmdm", "zzmm" });
		request.setAttribute("zzmmList", zzmmList);
		if (Globals.XXDM_NBZYJSXY.equalsIgnoreCase(xxdm)) {
			sql = "select * from jygl_xsccb order by dm";
			request.setAttribute("xsccList", dao.getListNotOut(sql,
					new String[] {}));
		}

		if (dotpye == null) {
			if ((Globals.XXDM_ZGDZDX).equals(Base.xxdm)) {
				return mapping.findForward("success3");
			} else if (Globals.XXDM_FJGCXY.equals(Base.xxdm)) {
				return mapping.findForward("success4");
			} else {
				return mapping.findForward("success");
			}
		} else if (dotpye.equals("save")) {

			// ��ҵ����Ϣ�ϱ�
			if (Globals.XXDM_ZGMSXY.equals(xxdm)) {
				sql = "SELECT pycc FROM xsxxb WHERE xh=?";
				String[] pycc1 = dao.getOneRs(sql, new String[] { xh },
						new String[] { "pycc" });
				if (null != pycc1) {
					xslb = pycc1[0];
				}
				if (null == xslb || "".equals(pycc1)) {
					sql = "SELECT pycc FROM bks_xsjbxx WHERE xh=?";
					String[] pycc2 = dao.getOneRs(sql, new String[] { xh },
							new String[] { "pycc" });
					if (null != pycc1) {
						xslb = pycc2[0];
					}
				}
				if (null == xslb) {
					xslb = "";
				}
			} else {
				if (request.getParameter("titName").equals("yjs")) {
					xslb = "�о���";
				} else if (request.getParameter("titName").equals("bks")) {
					xslb = "������";
				} else if (request.getParameter("titName").equals("zks")) {
					xslb = "ר����";
				} // ѧ�����yjs�о�����bks������,zksר������
			}
			String id = request.getParameter("sfzh"); // ���֤����
			xxdm = request.getParameter("xxdm"); // ѧУ����
			xxmc = DealString.toGBK(request.getParameter("xxmc")); // ѧУ����
			String zydm1 = request.getParameter("zydm"); // רҵ����
			String zymc = DealString.toGBK(request.getParameter("zymc")); // רҵ����
			String xsxh = request.getParameter("xh"); // ѧ��
			String name = DealString.toGBK(request.getParameter("xm")); // ����
			String xbdm = request.getParameter("xbm"); // �Ա����
			String xldm = request.getParameter("xldm"); // ѧ������
			String xzdm = request.getParameter("xz"); // ѧ��
			String sydqdm = request.getParameter("sydqdm"); // ��Դ��������
			String pyfsdm = request.getParameter("pyfsdm"); // ������ʽ����
			String nd = request.getParameter("nd");// ��ѧ���
			String jgx = request.getParameter("jgx");
			String xscc = request.getParameter("xscc");
			String bynd = String.valueOf(Integer.parseInt(nd)
					+ Integer.parseInt(xzdm)); // ��ҵ���
			String memo = DealString.toGBK(request.getParameter("memo"));
			String sbsj = (dao
					.getOneRs(
							"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual",
							new String[] {}, new String[] { "sdate" }))[0]; // �ϱ�ʱ��
			// ���ʴ�ѧ����
			String jgshi = DealString.toGBK(request.getParameter("jgshi")); // ��Դ�����м�
			String xymc = DealString.toGBK(request.getParameter("xymc")); // ѧԺ����
			String xydm = request.getParameter("xydm"); // ѧԺ����
			String bjdm = request.getParameter("bjdm"); // �༶����
			String lxfs = DealString.toGBK(request.getParameter("lxfs")); // ��ϵ��ʽ
			String lxdzxx = DealString.toGBK(request.getParameter("lxdzxx")); // ��������
			String qq = DealString.toGBK(request.getParameter("qq")); // QQ
			String mz = DealString.toGBK(request.getParameter("mz")); // ����
			String zzmm = DealString.toGBK(request.getParameter("zzmm")); // ������ò
			String xysbh = DealString.toGBK(request.getParameter("xysbh")); // Э����
			jgshi = Base.isNull(jgshi) ? "" : jgshi;
			jgx = Base.isNull(jgx) ? "" : jgx;
			xscc = Base.isNull(xscc) ? "" : xscc;
			// �����ݿ���ʱ����
			sql = "select mzmc from mzdmb";// ����
			List mzList = dao.getList(sql, new String[] {},
					new String[] { "mzmc" });
			request.setAttribute("mzList", mzList);
			/*
			 * ����ͨ�÷����������ݣ����������� 1������ 2���ֶ������ɵ��ַ������� 3��ֵ���ɵ��ַ�������
			 */
			sql = "select * from jygl_xsjbxxb where xsxh=?";
			String[] rs1 = dao.getOneRs(sql, new String[] { xsxh },
					new String[] { "xsxh" });
			if ("12453".equals(xxdm)) {
				String sql2 = "select xydm,xymc from view_xsxxb where xh=?";
				String[] rs3 = dao.getOneRs(sql2, new String[] { xsxh },
						new String[] { "xydm", "xymc" });
				if (rs3 != null) {
					xydm = rs3[0];
					xymc = rs3[1];
				}

			}

			boolean judge = false;
			if (rs1 == null) {
				if ((Base.xxdm).equals("10491")) {
					judge = StandardOperation.insert("jygl_xsjbxxb",
							new String[] { "xslb", "id", "xxdm", "xxmc",
									"zydm", "zymc", "xsxh", "name", "xbdm",
									"xldm", "xzdm", "sydqdm", "pyfsdm", "sbsj",
									"nd", "bynd", "memo", "jgshi", "xymc",
									"xydm", "bjdm", "lxfs", "lxdzxx", "qq",
									"mz", "zzmm", "xysbh" }, new String[] {
									xslb, id, xxdm, xxmc, zydm1, zymc, xsxh,
									name, xbdm, xldm, xzdm, sydqdm, pyfsdm,
									sbsj, nd, bynd, memo, jgshi, xymc, xydm,
									bjdm, lxfs, lxdzxx, qq, mz, zzmm, xysbh },
							request);
				} else {
					judge = StandardOperation.insert("jygl_xsjbxxb",
							new String[] { "xslb", "id", "xxdm", "xxmc",
									"zydm", "zymc", "xsxh", "name", "xbdm",
									"xldm", "xzdm", "sydqdm", "pyfsdm", "sbsj",
									"nd", "bynd", "memo", "xydm", "xymc",
									"jgshi", "jgx", "xscc" }, new String[] {
									xslb, id, xxdm, xxmc, zydm1, zymc, xsxh,
									name, xbdm, xldm, xzdm, sydqdm, pyfsdm,
									sbsj, nd, bynd, memo, xydm, xymc, jgshi,
									jgx, xscc }, request);
				}
				// if (xslb.equals("") || id.equals("") || xxdm.equals("")
				// || xxmc.equals("") || zydm1.equals("")
				// || zymc.equals("") || xsxh.equals("")
				// || name.equals("") || xbdm.equals("")
				// || xldm.equals("") || xzdm.equals("")
				// || sydqdm.equals("") || pyfsdm.equals("")
				// || sbsj.equals("") || nd.equals("") || bynd.equals("")) {
				// request.setAttribute("inserted", "que");
				// } else if (judge) {
				if (judge) {
					request.setAttribute("inserted", "ok");

					// ��Ӳ����ļ�¼
					if ("teacher".equals(userType)) {

						String userName = session.getAttribute("userName")
								.toString();
						String whensj = (dao
								.getOneRs(
										"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual",
										new String[] {},
										new String[] { "sdate" }))[0]; // �ύʱ��

						StandardOperation.insert("JYGL_USERCZMXB",
								new String[] { "userid", "dowhat",
										"whichtable", "whichpk", "whensj" },
								new String[] { userName, "����", "ѧ��������Ϣ��",
										"ѧ��:" + xsxh, whensj }, request);
					}
				} else {
					request.setAttribute("inserted", "no");
				}
			} else {
				request.setAttribute("inserted", "isexist");
			}
			String isstu = (String) request.getSession().getAttribute(
					"userType");
			// System.out.println(Globals.XXDM_ZGMSXY.equalsIgnoreCase(xxdm) &&
			// "stu".equals(isstu) && rs1 != null);
			if (Globals.XXDM_ZGMSXY.equalsIgnoreCase(xxdm)
					&& "stu".equals(isstu) && rs1 != null) {

				String sql12 = "select * from jygl_xsjbxxb where xsxh=? and sftj='2'";
				String[] teainfo = dao.getOneRs(sql12, new String[] { xsxh },
						new String[] { "id" });
				if (teainfo == null) {
					String[] pycc1 = dao.getOneRs(
							"SELECT pycc FROM xsxxb WHERE xh=?",
							new String[] { xh }, new String[] { "pycc" });
					if (null != pycc1) {
						xslb = pycc1[0];
					}
					if (null == xslb || "".equals(pycc1)) {
						String[] pycc2 = dao.getOneRs(
								"SELECT pycc FROM bks_xsjbxx WHERE xh=?",
								new String[] { xh }, new String[] { "pycc" });
						if (null != pycc1) {
							xslb = pycc2[0];
						}
					}
					if (null == xslb) {
						xslb = "";
					}
					String sfzid = request.getParameter("sfzh");
					judge = StandardOperation.update("jygl_xsjbxxb",
							new String[] { "id", "xldm", "pyfsdm", "sydqdm",
									"memo", "sftj", "xslb" }, new String[] {
									sfzid, xldm, pyfsdm, sydqdm, memo, "2",
									xslb }, "xsxh", xsxh, request);
				} else {
					request.setAttribute("yjtj", "yjtj");
				}
			}
			dotpye = null;
		}
		request.setAttribute("realTable", "jygl_xsjbxxb"); // ����ѧ��������Ϣ��
		if (Globals.XXDM_ZGDZDX.equals(xxdm)) {
			return mapping.findForward("success3");
		} else if (Globals.XXDM_FJGCXY.equals(xxdm)) {
			return mapping.findForward("success4");
		} else {
			return mapping.findForward("success");
		}
	}

	// ѧ����Ϣ��ѯ
	private ActionForward JyglDataSearch(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response, String userType) throws Exception {
		CommanForm commanForm = (CommanForm) form;
		DAO dao = DAO.getInstance(); // ʵ����ͨ�÷�������������ݿ�����
		ArrayList<Object> rs = new ArrayList<Object>();
		String[] colList = null;
		String[] colListCN = null;
		String sql = ""; // sql���
		String querry = " where 1=1 "; // sql����
		String tableName = "jygl_xsjbxxb"; // ��ѯ��ϢԴ����Ϊ��ͼ����
		String rsNum = "0";// ���صļ�¼��
		String xxdm = dao.getXxdm();
		String dataType = request.getParameter("act"); // ������������
		String xsxh = request.getParameter("xsxh"); // ����ѧ��
		String name = DealString.toGBK(request.getParameter("name")); // ��������
		String xbdm = request.getParameter("xbdm"); // �����Ա����
		String xb = DealString.toGBK(request.getParameter("xb"));// �����Ա�
		String xslb = DealString.toGBK(request.getParameter("xslb")); // ����ѧ�����
		String xymc = DealString.toGBK(request.getParameter("xymc")); // ����ѧԺ����
		String nd = request.getParameter("nd"); // �������
		String bynd = request.getParameter("bynd"); // ��ҵ���
		String pk = "xsxh"; // ����
		HashMap<String, String> map = new HashMap<String, String>();
		String doType = request.getParameter("doType");

		String sfsh = request.getParameter("sfsh");
		HttpSession session = request.getSession();
		String userName = session.getAttribute("userName").toString();

		// ��������
		if (xxdm.equals(Globals.XXDM_YNYS)) {

			// ���ϴ��ύ��ֵ����ȥ
			if ("query".equals(doType)) {
				map.put("xsxh", xsxh);
				map.put("name", name);
				map.put("xb", xb);
				map.put("xymc", xymc);
				map.put("nd", nd);
				map.put("bynd", bynd);
			}

			if (xsxh == null) {
				xsxh = "";
			}
			if (name == null) {
				name = "";
			}
			if (xbdm == null) {
				xbdm = "";
			}
			if (xymc == null) {
				xymc = "";
			}
			if (nd == null) {
				nd = "";
			}
			if (bynd == null) {
				bynd = "";
			}
			if ((xsxh == null) || xsxh.equals("")) {
				querry += "and 1=1 ";
			} else {
				querry += "and xsxh like '%" + xsxh + "%' ";
			}
			if ((name == null) || name.equals("")) {
				querry += "and 1=1 ";
			} else {
				querry += "and name like '%" + name + "%' ";
			}
			if ((xbdm == null) || xbdm.equals("")) {
				querry += " and 1=1 ";
			} else {
				querry += " and xbdm='" + xbdm + "' ";
			}
			if ((xymc == null) || xymc.equals("")) {
				querry += " and 1=1 ";
			} else {
				querry += " and xsxh in (select xsxh from jygl_xsjbxxb where xsxh in (select xh from view_xsjbxx where xymc ='"
						+ xymc + "'))";
			}
			if ((nd == null) || nd.equals("")) {
				querry += " and 1=1 ";
			} else {
				querry += " and nd='" + nd + "' ";
			}
			if ((bynd == null) || bynd.equals("")) {
				querry += " and 1=1 ";
			} else {
				querry += " and bynd='" + bynd + "' ";
			}

			sql = "select " + pk + " ����,rownum �к�,a.* from " + tableName
					+ " a " + querry;
			colList = new String[] { "����", "�к�", "nd", "bynd", "name", "xsxh",
					"xb", "zymc", "sbsj" };
			colListCN = dao.getColumnNameCN(colList, tableName);
			List topTr = dao.arrayToList(colList, colListCN);
			if ((request.getParameter("act") != null)
					&& request.getParameter("act").equalsIgnoreCase("go")) {
				rs.addAll(dao.rsToVator(sql, new String[] {}, colList));
				if (rs == null) {
					rsNum = "0";
				} else {
					rsNum = String.valueOf(rs.size());
				}
			}
			request.setAttribute("realTable", "jygl_xsjbxxb"); // ����ѧ��������Ϣ��
			request.setAttribute("ndList", dao.getNjList()); // ������ѧ����б�
			request.setAttribute("xyList", dao.getXyList());// ����ѧԺ�б�
			request.setAttribute("act", dataType);// �������ݲ�ѯ����
			request.setAttribute("rs", rs);// �������ݼ�
			request.setAttribute("topTr", topTr);// ���ͱ�ͷ
			request.setAttribute("rsNum", rsNum);// ���ͼ�¼��
			request.setAttribute("rs1", map);
			return mapping.findForward("success2");

		}

		if (userType.equals("teacher")) {
			sql = "select * from jygl_jyxywhb where username=?";
			String[] teainfo = dao.getOneRs(sql, new String[] { userName },
					new String[] { "usertype", "xymc" });
			@SuppressWarnings("unused")
			String tea = "";
			if (null != teainfo) {
				tea = teainfo[0];
			}
			// if ("����Ա".equals(tea)) {
			// map.put("xymc", teainfo[1]);
			// request.setAttribute("who", "fudaoyuan");
			// } else {
			// request.setAttribute("who", "teacher");
			// }

		}

		// �Ϻ����̼�ͨ��
		// ���ϴ��ύ��ֵ����ȥ
		if ("query".equals(doType)) {
			map.put("xsxh", xsxh);
			map.put("name", name);
			map.put("xbdm", xbdm);
			map.put("xslb", xslb);
			map.put("xymc", xymc);
			map.put("nd", nd);
			map.put("bynd", bynd);
			map.put("sfsh", request.getParameter("sfsh"));
		}

		if (xsxh == null) {
			xsxh = "";
		}
		if (name == null) {
			name = "";
		}
		if (xbdm == null) {
			xbdm = "";
		}
		if (xslb == null) {
			xslb = "";
		}
		if (xymc == null) {
			xymc = "";
		}
		if (nd == null) {
			nd = "";
		}
		if (bynd == null) {
			bynd = "";
		}
		if ((xsxh == null) || xsxh.equals("")) {
			querry += "and 1=1 ";
		} else {
			querry += "and xsxh like '%" + xsxh + "%' ";
		}
		if ((name == null) || name.equals("")) {
			querry += "and 1=1 ";
		} else {
			querry += "and name like '%" + name + "%' ";
		}
		if ((xbdm == null) || xbdm.equals("")) {
			querry += " and 1=1 ";
		} else {
			querry += " and xbdm='" + xbdm + "' ";
		}
		if ((xslb == null) || xslb.equals("")) {
			querry += " and 1=1 ";
		} else {
			querry += " and xslb='" + xslb + "' ";
		}
		if ((xymc == null) || xymc.equals("")) {
			querry += " and 1=1 ";
		} else {
			querry += " and xsxh in (select xsxh from jygl_xsjbxxb where xsxh in (select xh from view_xsjbxx where xymc ='"
					+ xymc + "'))";
		}
		if ((nd == null) || nd.equals("")) {
			querry += " and 1=1 ";
		} else {
			querry += " and nd='" + nd + "' ";
		}
		if ((bynd == null) || bynd.equals("")) {
			querry += " and 1=1 ";
		} else {
			querry += " and bynd='" + bynd + "' ";
		}
		if (((sfsh == null) || sfsh.equals(""))
				|| !Globals.XXDM_ZGMSXY.equals(xxdm)) {
			querry += " and 1=1 ";
		} else {
			if (sfsh == "1" || "1".equals(sfsh)) {
				sfsh = "δ���";
			} else if (sfsh == "2" || "2".equals(sfsh)) {
				sfsh = "��ͨ����";
			} else {
				sfsh = "δͨ��X";
			}
			querry += " and sfsh='" + sfsh + "' ";
		}
		if ("12453".equals(xxdm)) {
			String usertype = (String) request.getSession().getAttribute(
					"userType");
			if ("xx".equals(usertype) || "admin".equals(usertype)) {
				sfsh = "��ͨ����";
				querry += " and fdysh='" + sfsh + "' ";
			}
			String usertuyep = (String) request.getSession().getAttribute(
					"userType");
			String bmmc = (String) request.getSession().getAttribute("bmmc");
			if ("xx".equals(usertuyep) || "admin".equals(usertuyep)) {
				request.setAttribute("who", "teacher");
			} else if ("xy".equals(usertuyep)) {
				request.setAttribute("who", "fudaoyuan");
				map.put("xymc", bmmc);
			}
		}
		sql = "select " + pk + " ����,rownum r,a.* from " + tableName + " a "
				+ querry;
		if (Globals.XXDM_ZGDZDX.equals(xxdm)
				|| Globals.XXDM_ZGMSXY.equals(xxdm)) {
			colList = new String[] { "����", "r", "nd", "bynd", "name", "xsxh",
					"xbdm", "xldm", "xslb", "xzdm", "zymc", "sbsj", "sfsh",
					"btgyy" };
		} else if ("12453".equals(xxdm)) {
			colList = new String[] { "����", "r", "nd", "bynd", "name", "xsxh",
					"xbdm", "xldm", "xslb", "xzdm", "zymc", "sbsj", "fdysh",
					"sfsh" };
		} else {
			colList = new String[] { "����", "r", "nd", "bynd", "name", "xsxh",
					"xslb", "xzdm", "zymc", "sbsj" };
		}
		colListCN = dao.getColumnNameCN(colList, tableName);
		List topTr = dao.arrayToList(colList, colListCN);
	
			rs.addAll(CommonQueryDAO.commonQuery(sql, "", new String[] {},
					colList, commanForm));
			if (rs == null) {
				rsNum = "0";
			} else {
				rsNum = String.valueOf(rs.size());
			}
	
		if ("12453".equals(xxdm)) {
			String utp = (String) request.getSession().getAttribute("userType");
			if ("xx".equals(utp) || "admin".equals(utp)) {
				request.setAttribute("userType", "xx");
			}
		}
		request.setAttribute("realTable", "jygl_xsjbxxb"); // ����ѧ��������Ϣ��
		request.setAttribute("ndList", dao.getNjList()); // ������ѧ����б�
		request.setAttribute("xyList", dao.getXyList());// ����ѧԺ�б�
		request.setAttribute("act", dataType);// �������ݲ�ѯ����
		request.setAttribute("rs", rs);// �������ݼ�
		request.setAttribute("topTr", topTr);// ���ͱ�ͷ
		request.setAttribute("rsNum", rsNum);// ���ͼ�¼��
		request.setAttribute("rs1", map);
		return mapping.findForward("success");
	}

	// ѧ����Ϣɾ��
	private ActionForward JyglBysjbxxbDel(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String sql = ""; // sql���
		DAO dao = DAO.getInstance();
		CommanForm commForm = (CommanForm) form;
		String xxdm = dao.getXxdm();
		ArrayList<Object> rs = new ArrayList<Object>();
		String[] colList = null;
		String[] colListCN = null;
		String realTable = "jygl_xsjbxxb"; // Ҫ��ѯ������Դ��
		String doType = request.getParameter("doType");
		String pk = "xsxh";
		String dataType = request.getParameter("act"); // ������������
		String querry = " where 1=1 "; // sql����
		String pkValue = request.getParameter("pkValue");
		String xsxh = request.getParameter("xsxh"); // ����ѧ��
		String name = DealString.toGBK(request.getParameter("name")); // ��������
		String xbdm = request.getParameter("xbdm"); // �����Ա����
		String xb = DealString.toGBK(request.getParameter("xb"));
		String xslb = DealString.toGBK(request.getParameter("xslb")); // ����ѧ�����
		String xymc = DealString.toGBK(request.getParameter("xymc")); // ����ѧԺ����
		String nd = request.getParameter("nd"); // �������
		String bynd = request.getParameter("bynd"); // ���ձ�ҵ���
		String rsNum = "0";// ���صļ�¼��
		HashMap<String, String> map = new HashMap<String, String>();
		String doType2 = request.getParameter("doType2");
		HttpSession session = request.getSession();

		// ����ɾ��
		if ("delall".equalsIgnoreCase(doType)) {
			String pkstring = request.getParameter("pkstring");
			String[] pkstringI = pkstring.split("!!#!!");

			// ================2010.10.15 edit by
			// luojw==========================
			if (pkstringI != null && pkstringI.length > 0) {
				String[] actSql = new String[pkstringI.length - 1];
				int n = 0;
				for (int i = 1; i < pkstringI.length; i++) {
					actSql[n] = "delete from jygl_xsjbxxb where xsxh = '"
							+ pkstringI[i] + "' ";
					n++;
				}
				boolean judge = dao.saveArrDate(actSql);
				if (judge) {
					request.setAttribute("delall", "ok");
				} else {
					request.setAttribute("delall", "no");
				}
			}
			// for (int i = 1; i < pkstringI.length; i++) {
			// String whichxh = pkstringI[i];
			// boolean judge = false;
			// judge = StandardOperation.delete(realTable, pk, whichxh,
			// request);
			// if (judge) {
			// request.setAttribute("delall", "ok");
			//
			// // ɾ�������ļ�¼
			//
			// String userName = session.getAttribute("userName")
			// .toString();
			// String whensj = (dao
			// .getOneRs(
			// "select to_char(sysdate,'yyyymmddhh24miss') sdate from dual",
			// new String[] {}, new String[] { "sdate" }))[0]; // �ύʱ��
			//
			// StandardOperation.insert("JYGL_USERCZMXB", new String[] {
			// "userid", "dowhat", "whichtable", "whichpk",
			// "whensj" }, new String[] { userName, "ɾ��",
			// "ѧ��������Ϣ��", "ѧ��:" + whichxh, whensj }, request);
			// } else {
			// request.setAttribute("delall", "no");
			// }
			// }
		}

		if (xxdm.equals(Globals.XXDM_YNYS)) {
			// ���ϴ��ύ��ֵ����ȥ
			if ("query".equals(doType2)) {
				map.put("xsxh", xsxh);
				map.put("name", name);
				map.put("xb", xb);
				map.put("xymc", xymc);
				map.put("nd", nd);
				map.put("bynd", bynd);
			}

			if (xsxh == null) {
				xsxh = "";
			}
			if (name == null) {
				name = "";
			}
			if (xbdm == null) {
				xbdm = "";
			}
			if (xymc == null) {
				xymc = "";
			}
			if (nd == null) {
				nd = "";
			}
			if (bynd == null) {
				bynd = "";
			}
			if ((xsxh == null) || xsxh.equals("")) {
				querry += "and 1=1 ";
			} else {
				querry += "and xsxh like '%" + xsxh + "%' ";
			}
			if ((name == null) || name.equals("")) {
				querry += "and 1=1 ";
			} else {
				querry += "and name like '%" + name + "%' ";
			}
			if ((xbdm == null) || xbdm.equals("")) {
				querry += " and 1=1 ";
			} else {
				querry += " and xbdm='" + xbdm + "' ";
			}
			if ((xymc == null) || xymc.equals("")) {
				querry += " and 1=1 ";
			} else {
				querry += " and xsxh in (select xsxh from jygl_xsjbxxb where xsxh in (select xh from view_xsjbxx where xymc ='"
						+ xymc + "'))";
			}
			if ((nd == null) || nd.equals("")) {
				querry += " and 1=1 ";
			} else {
				querry += " and nd='" + nd + "' ";
			}
			if ((bynd == null) || bynd.equals("")) {
				querry += " and 1=1 ";
			} else {
				querry += " and bynd='" + bynd + "' ";
			}

			if ((doType == null) || doType.equalsIgnoreCase("")) {
				// �����쳣
				return mapping.findForward("false");
			} else if (doType.equalsIgnoreCase("del")) {
				// ɾ������
				sql = "delete from " + realTable + " where " + pk + "='"
						+ pkValue + "'";
				boolean judge = false;
				judge = dao.runUpdate(sql, new String[] {});
				if (judge) {
					request.setAttribute("delete", "ok");

					// ɾ�������ļ�¼

					String userName = session.getAttribute("userName")
							.toString();
					String whensj = (dao
							.getOneRs(
									"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual",
									new String[] {}, new String[] { "sdate" }))[0]; // �ύʱ��

					StandardOperation.insert("JYGL_USERCZMXB", new String[] {
							"userid", "dowhat", "whichtable", "whichpk",
							"whensj" }, new String[] { userName, "ɾ��",
							"ѧ��������Ϣ��", "ѧ��:" + pkValue, whensj }, request);
				} else {
					request.setAttribute("delete", "no");
				}
			}

			sql = "select " + pk + " ����,rownum r,a.* from " + realTable + " a "
					+ querry;
			colList = new String[] { "����", "r", "nd", "bynd", "name", "xsxh",
					"xb", "zymc", "sbsj" };
			colListCN = dao.getColumnNameCN(colList, realTable);
			List topTr = dao.arrayToList(colList, colListCN);

			rs.addAll(CommonQueryDAO.commonQuery(sql, "", new String[] {},
					colList, commForm));
			if (rs == null) {
				rsNum = "0";
			} else {
				rsNum = String.valueOf(rs.size());
			}
			request.setAttribute("realTable", "jygl_xsjbxxb"); // ����ѧ��������Ϣ��
			request.setAttribute("ndList", dao.getNjList()); // ������ѧ����б�
			request.setAttribute("xyList", dao.getXyList());// ����ѧԺ�б�
			request.setAttribute("act", dataType);// �������ݲ�ѯ����
			request.setAttribute("rs", rs);// �������ݼ�
			request.setAttribute("topTr", topTr);// ���ͱ�ͷ
			request.setAttribute("rsNum", rsNum);// ���ͼ�¼��
			request.setAttribute("rs1", map);
			return mapping.findForward("success2");
		}

		// ���ϴ��ύ��ֵ����ȥ
		if ("query".equals(doType2)) {
			map.put("xsxh", xsxh);
			map.put("name", name);
			map.put("xbdm", xbdm);
			map.put("xslb", xslb);
			map.put("xymc", xymc);
			map.put("nd", nd);
			map.put("bynd", bynd);
		}

		if (xsxh == null) {
			xsxh = "";
		}
		if (name == null) {
			name = "";
		}
		if (xbdm == null) {
			xbdm = "";
		}
		if (xslb == null) {
			xslb = "";
		}
		if (xymc == null) {
			xymc = "";
		}
		if (nd == null) {
			nd = "";
		}
		if (bynd == null) {
			bynd = "";
		}
		if ((xsxh == null) || xsxh.equals("")) {
			querry += "and 1=1 ";
		} else {
			querry += "and xsxh = '" + xsxh + "' ";
		}
		if ((name == null) || name.equals("")) {
			querry += "and 1=1 ";
		} else {
			querry += "and name = '" + name + "' ";
		}
		if ((xbdm == null) || xbdm.equals("")) {
			querry += " and 1=1 ";
		} else {
			querry += " and xbdm='" + xbdm + "' ";
		}
		if ((xslb == null) || xslb.equals("")) {
			querry += " and 1=1 ";
		} else {
			querry += " and xslb='" + xslb + "' ";
		}
		if ((xymc == null) || xymc.equals("")) {
			querry += " and 1=1 ";
		} else {
			querry += " and xsxh in (select xsxh from jygl_xsjbxxb where xsxh in (select xh from view_xsjbxx where xymc ='"
					+ xymc + "'))";
		}
		if ((nd == null) || nd.equals("")) {
			querry += " and 1=1 ";
		} else {
			querry += " and nd='" + nd + "' ";
		}
		if ((bynd == null) || bynd.equals("")) {
			querry += " and 1=1 ";
		} else {
			querry += " and bynd='" + bynd + "' ";
		}

		if ((doType == null) || doType.equalsIgnoreCase("")) {
			// �����쳣
			return mapping.findForward("false");
		} else if (doType.equalsIgnoreCase("del")) {
			// ɾ������
			sql = "delete from " + realTable + " where " + pk + "='" + pkValue
					+ "'";
			boolean judge = false;
			judge = dao.runUpdate(sql, new String[] {});
			if (judge) {
				request.setAttribute("delete", "ok");

				// ɾ�������ļ�¼

				String userName = session.getAttribute("userName").toString();
				String whensj = (dao
						.getOneRs(
								"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual",
								new String[] {}, new String[] { "sdate" }))[0]; // �ύʱ��

				StandardOperation.insert("JYGL_USERCZMXB",
						new String[] { "userid", "dowhat", "whichtable",
								"whichpk", "whensj" }, new String[] { userName,
								"ɾ��", "ѧ��������Ϣ��", "ѧ��:" + pkValue, whensj },
						request);
			} else {
				request.setAttribute("delete", "no");
			}
		}

		sql = "select " + pk + " ����,rownum r,a.* from " + realTable + " a "
				+ querry;
		colList = new String[] { "����", "r", "nd", "bynd", "name", "xsxh", "xb",
				"zymc", "sbsj" };
		colListCN = dao.getColumnNameCN(colList, realTable);
		List topTr = dao.arrayToList(colList, colListCN);

		rs.addAll(CommonQueryDAO.commonQuery(sql, "", new String[] {}, colList,
				commForm));
		if (rs == null) {
			rsNum = "0";
		} else {
			rsNum = String.valueOf(rs.size());
		}

		String userType = session.getAttribute("userType").toString();
		if ("teacher".equals(userType) || "admin".equals(userType)) {
			request.setAttribute("who", "teacher");
		}

		request.setAttribute("realTable", realTable);// �ѱ�������ȥ
		request.setAttribute("rs1", map);
		request.setAttribute("ndList", dao.getNjList());// ��������б�
		request.setAttribute("xyList", dao.getXyList());// ����ѧԺ�б�
		request.setAttribute("rs", rs);// �������ݼ�
		request.setAttribute("topTr", topTr);// ���ͱ�ͷ
		request.setAttribute("rsNum", rsNum);// ���ͼ�¼��

		return mapping.findForward("success");
	}

	// ����鿴ѧ������ϸ��Ϣ
	private ActionForward JyglViewMoreinfo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String pk = "xsxh";
		String sql = ""; // sql���
		DAO dao = DAO.getInstance();
		String xxdm = dao.getXxdm();
		String realTable = "jygl_xsjbxxb";
		String doType = request.getParameter("doType");
		String shenhe = request.getParameter("shenhe");
		String pkValue = request.getParameter("pkValue");
		HashMap<String, String> map = new HashMap<String, String>();

		if (xxdm.equals(Globals.XXDM_YNYS)) {
			if ((doType == null) || doType.equalsIgnoreCase("")) {
				// �����쳣
				return mapping.findForward("false");
			} else if (doType.equalsIgnoreCase("view")) {
				// ��ѯ����
				sql = "select * from " + realTable + " where " + pk + "='"
						+ pkValue + "'";
				String[] colList = dao
						.getColumnName("select * from jygl_xsjbxxb where 1=2"); // ������������
				String[] stuinfo = dao.getOneRs(sql, new String[] {}, colList);
				sql = "select xymc from view_xsjbxx where xh=?";
				String[] xymc = dao.getOneRs(sql, new String[] { pkValue },
						new String[] { "xymc" });
				if (stuinfo != null) {
					for (int i = 0; i < colList.length; i++) {
						map.put(colList[i].toLowerCase(), stuinfo[i]); // ����¼ѭ������map��
					}
					sql = "select (select qxmc from dmk_qx where qxdm=?)||(select qxmc from dmk_qx where qxdm=?)||(select qxmc from dmk_qx where qxdm=?) sydq from dual";
					String sydqinfo = dao.getOneRs(sql,
							new String[] { map.get("sydqdm"), map.get("jgshi"),
									map.get("jgx") }, "sydq");
					map.put("sydq", sydqinfo);
					if (null != xymc) {
						map.put("xymc", xymc[0]);
					}
					String sbsj = null;
					String sjyear = null;
					String sjmon = null;
					String sjday = null;
					String sj = map.get("sbsj").toString();
					sjyear = sj.substring(0, 4);
					sjmon = sj.substring(4, 6);
					sjday = sj.substring(6, 8);
					sbsj = sjyear + "��" + sjmon + "��" + sjday + "��";
					map.put("sbsj", sbsj);
				}
			}
			request.setAttribute("rs", map);// �������ݼ�
			return mapping.findForward("success2");

		}

		if ((doType == null) || doType.equalsIgnoreCase("")) {
			// �����쳣
			return mapping.findForward("false");
		} else if (doType.equalsIgnoreCase("view")) {
			// ��ѯ����
			sql = "select * from " + realTable + " where " + pk + "='"
					+ pkValue + "'";
			String[] colList = dao
					.getColumnName("select * from jygl_xsjbxxb where 1=2"); // ������������
			String[] stuinfo = dao.getOneRs(sql, new String[] {}, colList);
			sql = "select xymc from view_xsjbxx where xh=?";
			String[] xymc = dao.getOneRs(sql, new String[] { pkValue },
					new String[] { "xymc" });
			if (stuinfo != null) {
				for (int i = 0; i < colList.length; i++) {
					map.put(colList[i].toLowerCase(), stuinfo[i]); // ����¼ѭ������map��
				}
				if (Globals.XXDM_ZGDZDX.equals(xxdm)
						|| Globals.XXDM_ZGMSXY.equals(xxdm)) {
					String shsj = null;
					// String fbsj = null;
					String sjyear = null;
					String sjmon = null;
					String sjday = null;
					String sjhour = null;
					if (null != (map.get("shsj"))) {
						String sj = map.get("shsj").toString();
						sjyear = sj.substring(0, 4);
						sjmon = sj.substring(4, 6);
						sjday = sj.substring(6, 8);
						sjhour = sj.substring(8, 10);
						shsj = sjyear + "��" + sjmon + "��" + sjday + "��"
								+ sjhour + "ʱ";
						map.put("shsj", shsj);
					}
				}
				// ��ʾ�������Ա�������
				if ("1".equals(map.get("xbdm"))) {
					map.put("xbdm", "��");
				} else if ("2".equals(map.get("xbdm"))) {
					map.put("xbdm", "Ů");
				}
				sql = "select xl from dmk_xl where xldm=?";
				String xldm = map.get("xldm");
				String xlinfo = dao.getOneRs(sql, new String[] { xldm }, "xl");
				map.put("xl", xlinfo);

				sql = "select sydq from dmk_sydq where sydqdm=?";
				String sydqdm = map.get("sydqdm");
				String sydqinfo = dao.getOneRs(sql, new String[] { sydqdm },
						"sydq");
				String qxdm = map.get("jgshi");
				String sydqinfo1 = dao.getOneRs(
						"select qxmc from dmk_qx WHERE qxdm = ?",
						new String[] { qxdm }, "qxmc");
				String jgx = map.get("jgx");
				String jgxmc = dao.getOneRs(
						"select qxmc from dmk_qx WHERE qxdm = ?",
						new String[] { jgx }, "qxmc");
				map.put("sydq", sydqinfo + sydqinfo1 + jgxmc);

				if ("ר����".equals(map.get("xslb"))
						|| "������".equals(map.get("xslb"))) {
					sql = "select pyfs from dmk_bzpyfs where pyfsdm=?";
					String pyfsdm = map.get("pyfsdm");
					String pyfsinfo = dao.getOneRs(sql,
							new String[] { pyfsdm }, "pyfs");
					map.put("pyfs", pyfsinfo);
				} else if ("�о���".equals(map.get("xslb"))) {
					sql = "select pyfs from dmk_yjspyfs where pyfsdm=?";
					String pyfsdm = map.get("pyfsdm");
					String pyfsinfo = dao.getOneRs(sql,
							new String[] { pyfsdm }, "pyfs");
					map.put("pyfs", pyfsinfo);
				}
				if (null != xymc) {
					map.put("xymc", xymc[0]);
				}
				String sbsj = null;
				String sjyear = null;
				String sjmon = null;
				String sjday = null;
				String sj = map.get("sbsj");
				if (!"".equalsIgnoreCase(sj) && null != sj) {
					sjyear = sj.substring(0, 4);
					sjmon = sj.substring(4, 6);
					sjday = sj.substring(6, 8);
					sbsj = sjyear + "��" + sjmon + "��" + sjday + "��";
					map.put("sbsj", sbsj);
				}
			}
		}
		request.setAttribute("rs", map);// �������ݼ�
		request.setAttribute("doType", doType);
		if ("shenhe".equals(shenhe)) {
			return mapping.findForward("successsh");
		} else {
			return mapping.findForward("success");
		}
	}

	// ��ҵ��������Ϣ��Ϣ�޸�
	private ActionForward JyglInfoUpdate(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String pk = "xsxh";
		String sql = ""; // sql���
		DAO dao = DAO.getInstance();
		String xxdm = dao.getXxdm();
		String realTable = "jygl_xsjbxxb";
		String doType = request.getParameter("doType");
		String doType2 = request.getParameter("doType2");
		String pkValue = request.getParameter("pkValue");
		String doNow = request.getParameter("doNow");

		if (xxdm.equals(Globals.XXDM_YNYS)) {
			StuInfoDAO stuDao = new StuInfoDAO();
			HashMap<String, String> map = new HashMap<String, String>();
			request.setAttribute("njList", dao.getNjList());// �����꼶�б�

			sql = "select zzmmdm,zzmm from dmk_zzmm"; // ������ò����
			List zzmmdmList = dao.getList(sql, new String[] {}, new String[] {
					"zzmmdm", "zzmm" });
			request.setAttribute("zzmmdmList", zzmmdmList);

			sql = "select sydqdm,sydq from dmk_sydq"; // ��Դ����
			List sydqdmList = dao.getList(sql, new String[] {}, new String[] {
					"sydqdm", "sydq" });
			request.setAttribute("sydqdmList", sydqdmList);

			sql = "select dwxzdm,dwxz from dmk_dwxz"; // ��ҵ����
			List dwxzdmList = dao.getList(sql, new String[] {}, new String[] {
					"dwxzdm", "dwxz" });
			request.setAttribute("dwxzdmList", dwxzdmList);

			sql = "select byqxdm,byqx from dmk_byqx"; // ��ҵ��ʽ
			List byqxdmList = dao.getList(sql, new String[] {}, new String[] {
					"byqxdm", "byqx" });
			request.setAttribute("byqxdmList", byqxdmList);

			sql = "select distinct zymc from view_njxyzybj";// רҵ����
			List zyList = dao.getList(sql, new String[] {},
					new String[] { "zymc" });
			request.setAttribute("zyList", zyList);

			sql = "select distinct bjmc from view_njxyzybj";// �༶����
			List bjList = dao.getList(sql, new String[] {},
					new String[] { "bjmc" });
			request.setAttribute("bjList", bjList);

			sql = "select mzmc from mzdmb";// ����
			List mzList = dao.getList(sql, new String[] {},
					new String[] { "mzmc" });
			request.setAttribute("mzList", mzList);

			if ((doType == null) || doType.equalsIgnoreCase("")) {
				// �����쳣
				return mapping.findForward("false");
			} else if (doType.equalsIgnoreCase("update") && pkValue != null) {
				// ��ѯ����
				sql = "select * from " + realTable + " where " + pk + "='"
						+ pkValue + "'";
				String[] colList = dao
						.getColumnName("select * from jygl_xsjbxxb where 1=2"); // ������������
				String[] stuinfo = dao.getOneRs(sql, new String[] {}, colList);
				sql = "select xymc from view_xsjbxx where xh=?";
				String[] xymc = dao.getOneRs(sql, new String[] { pkValue },
						new String[] { "xymc" });
				if (stuinfo != null) {
					for (int i = 0; i < colList.length; i++) {
						map.put(colList[i].toLowerCase(), stuinfo[i]); // ����¼ѭ������map��
					}
					map.put("xymc", xymc[0]);
					if (null != (map.get("sbsj"))) {
						String sbsj = null;
						String sjyear = null;
						String sjmon = null;
						String sjday = null;
						String sj = map.get("sbsj").toString();
						sjyear = sj.substring(0, 4);
						sjmon = sj.substring(4, 6);
						sjday = sj.substring(6, 8);
						sbsj = sjyear + "��" + sjmon + "��" + sjday + "��";
						map.put("sbsj", sbsj);
					}
				}
			}
			if ("update2".equals(doType2)) {
				String xsxh = request.getParameter("xsxh"); // ѧ��
				String name = DealString.toGBK(request.getParameter("name")); // ����
				String xb = DealString.toGBK(request.getParameter("xb")); // �Ա�
				String nj = request.getParameter("nj"); // �꼶
				String zymc = DealString.toGBK(request.getParameter("zymc"));// רҵ����
				String sydq = request.getParameter("sydq"); // ��ԭ����
				String jgshi = request.getParameter("jgshi"); // ��ԭ������
				String jgx = request.getParameter("jgx"); // ��ԭ������
				String csrq = DealString.toGBK(request.getParameter("csrq")); //
				String mz = DealString.toGBK(request.getParameter("mz")); // ����
				String zzmm = DealString.toGBK(request.getParameter("zzmm")); // ������ò
				String lxdh = request.getParameter("lxdh"); // ��ϵ�绰
				String nd = request.getParameter("nd"); // ���
				String bynd = request.getParameter("bynd"); // ��ҵ���
				String qkqk = DealString.toGBK(request.getParameter("qkqk"));// Ƿ�����
				String jcqk = DealString.toGBK(request.getParameter("jcqk"));// ���������
				String jyyx = DealString.toGBK(request.getParameter("jyyx")); // ��ҵ����
				String jyfs = DealString.toGBK(request.getParameter("jyfs")); // ��ҵ��ʽ

				boolean judge = false;
				judge = StandardOperation.update(realTable, new String[] {
						"name", "xb", "nj", "zymc", "sydq", "csrq", "mz",
						"zzmm", "lxdh", "nd", "bynd", "qkqk", "jcqk", "jyyx",
						"jyfs", "jgshi", "jgx" }, new String[] { name, xb, nj,
						zymc, sydq, csrq, mz, zzmm, lxdh, nd, bynd, qkqk, jcqk,
						jyyx, jyfs, jgshi, jgx }, pk, xsxh, request);

				if (judge) {
					request.setAttribute("updated", "ok");
					// �޸Ĳ����ļ�¼
					HttpSession session = request.getSession();
					String userName = session.getAttribute("userName")
							.toString();
					String whensj = (dao
							.getOneRs(
									"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual",
									new String[] {}, new String[] { "sdate" }))[0]; // �ύʱ��

					StandardOperation.insert("JYGL_USERCZMXB", new String[] {
							"userid", "dowhat", "whichtable", "whichpk",
							"whensj" }, new String[] { userName, "�޸�",
							"ѧ��������Ϣ��", "ѧ��:" + xsxh, whensj }, request);
				} else {
					request.setAttribute("updated", "no");
				}
				doType2 = null;
				sql = "select * from jygl_xsjbxxb where xsxh=?";
				String[] colList = dao
						.getColumnName("select * from jygl_xsjbxxb where 1=2");
				String[] stuinfo = dao.getOneRs(sql, new String[] { xsxh },
						colList);
				sql = "select xymc from view_xsjbxx where xh=?";
				String[] xymc = dao.getOneRs(sql, new String[] { xsxh },
						new String[] { "xymc" });

				if (stuinfo != null) {
					for (int i = 0; i < colList.length; i++) {
						map.put(colList[i].toLowerCase(), stuinfo[i]); // ����¼ѭ������map��
					}
					map.put("xymc", xymc[0]);
					if (null != (map.get("sbsj"))) {
						String sbsj = null;
						String sjyear = null;
						String sjmon = null;
						String sjday = null;
						String sj = map.get("sbsj").toString();
						sjyear = sj.substring(0, 4);
						sjmon = sj.substring(4, 6);
						sjday = sj.substring(6, 8);
						sbsj = sjyear + "��" + sjmon + "��" + sjday + "��";
						map.put("sbsj", sbsj);
					}
				}
			}
			request.setAttribute("shiList", stuDao.getShiList("")
					.get("shiList"));
			request.setAttribute("xianList", stuDao.getShiList("").get(
					"xianList"));
			request.setAttribute("rs", map);// �������ݼ�
			return mapping.findForward("success2");

		}

		if ("change".equals(doNow) || "update".equals(doType)
				&& null != doType2) {
			pkValue = request.getParameter("xsxh");
		}

		HashMap<String, String> map = new HashMap<String, String>();
		sql = "select xslb from jygl_xsjbxxb where xsxh=?";
		String[] xslbinfo = dao.getOneRs(sql, new String[] { pkValue },
				new String[] { "xslb" });

		if ("ר����".equals(xslbinfo[0]) || "������".equals(xslbinfo[0])) {
			sql = "select pyfsdm,pyfs from dmk_bzpyfs"; // ��ר��������ʽ
			List pyfsList = dao.getList(sql, new String[] {}, new String[] {
					"pyfsdm", "pyfs" });
			request.setAttribute("pyfsList", pyfsList);
		} else {
			sql = "select pyfsdm,pyfs from dmk_yjspyfs"; // �о���������ʽ
			List pyfsList = dao.getList(sql, new String[] {}, new String[] {
					"pyfsdm", "pyfs" });
			request.setAttribute("pyfsList", pyfsList);
		}

		sql = "select sydqdm,sydq from dmk_sydq"; // ��Դ����
		List sydqList = dao.getList(sql, new String[] {}, new String[] {
				"sydqdm", "sydq" });
		request.setAttribute("sydqList", sydqList);

		StuInfoDAO stuDao = new StuInfoDAO();
		request.setAttribute("shiList", stuDao.getShiList("").get("shiList"));
		request.setAttribute("xianList", stuDao.getShiList("").get("xianList"));
		request.setAttribute("ssList", stuDao.getSsList());

		sql = "select xldm,xl from dmk_xl"; // ѧ��
		List xldmList = dao.getList(sql, new String[] {}, new String[] {
				"xldm", "xl" });
		request.setAttribute("xldmList", xldmList);

		if ((doType == null) || doType.equalsIgnoreCase("")) {
			// �����쳣
			return mapping.findForward("false");
		} else if (doType.equalsIgnoreCase("update") && pkValue != null) {
			// ��ѯ����
			sql = "select * from " + realTable + " where " + pk + "='"
					+ pkValue + "'";
			String[] colList = dao
					.getColumnName("select * from jygl_xsjbxxb where 1=2"); // ������������
			String[] stuinfo = dao.getOneRs(sql, new String[] {}, colList);
			sql = "select xymc from view_xsjbxx where xh=?";
			String[] xymc = dao.getOneRs(sql, new String[] { pkValue },
					new String[] { "xymc" });
			if (stuinfo != null) {
				for (int i = 0; i < colList.length; i++) {
					map.put(colList[i].toLowerCase(), stuinfo[i]); // ����¼ѭ������map��
				}

				map.put("xymc", xymc[0]);
				String sbsj = null;
				String sjyear = null;
				String sjmon = null;
				String sjday = null;
				String sj = Base.isNull(map.get("sbsj")) ? "" : map.get("sbsj")
						.toString();
				sjyear = Base.isNull(map.get("sbsj")) ? "" : sj.substring(0, 4);
				sjmon = Base.isNull(map.get("sbsj")) ? "" : sj.substring(4, 6);
				sjday = Base.isNull(map.get("sbsj")) ? "" : sj.substring(6, 8);
				sbsj = sjyear + "��" + sjmon + "��" + sjday + "��";
				map.put("sbsj", sbsj);

			}
		}
		if ("update2".equals(doType2)) {
			String xsxh = request.getParameter("xsxh");
			String xslb = DealString.toGBK(request.getParameter("xslb"));
			String id = request.getParameter("id");
			String name = DealString.toGBK(request.getParameter("name"));
			String xbdm = request.getParameter("xbdm");
			String zydm = request.getParameter("zydm");
			String zymc = DealString.toGBK(request.getParameter("zymc"));
			String xldm = request.getParameter("xldm");
			String xzdm = request.getParameter("xzdm");
			String sydqdm = request.getParameter("sydqdm");
			String pyfsdm = request.getParameter("pyfsdm");
			String nd = request.getParameter("nd");
			String bynd = request.getParameter("bynd");
			String memo = DealString.toGBK(request.getParameter("memo"));
			String jgshi = request.getParameter("jgshi");
			String jgx = request.getParameter("jgx");
			jgshi = Base.isNull(jgshi) ? "" : jgshi;
			jgx = Base.isNull(jgx) ? "" : jgx;
			if (Globals.XXDM_ZGDZDX.equals(xxdm)) {
				String xydm = request.getParameter("xydm");
				String xymc = DealString.toGBK(request.getParameter("xymc"));
				String lxfs = DealString.toGBK(request.getParameter("lxfs"));
				String lxdzxx = DealString
						.toGBK(request.getParameter("lxdzxx"));
				String qq = DealString.toGBK(request.getParameter("qq"));
				String xysbh = DealString.toGBK(request.getParameter("xysbh"));
				String bjdm = DealString.toGBK(request.getParameter("bjdm"));
				sql = "update " + realTable + " set xslb='" + xslb + "',id='"
						+ id + "',name='" + name + "',xbdm='" + xbdm
						+ "',zydm='" + zydm + "',zymc='" + zymc + "',xldm='"
						+ xldm + "',xzdm='" + xzdm + "',sydqdm='" + sydqdm
						+ "',pyfsdm='" + pyfsdm + "',nd='" + nd + "',bynd='"
						+ bynd + "',memo='" + memo + "',xydm='" + xydm
						+ "',xymc='" + xymc + "',lxfs='" + lxfs + "',lxdzxx='"
						+ lxdzxx + "',qq='" + qq + "',xysbh='" + xysbh
						+ "',bjdm='" + bjdm + "',jgshi='" + jgshi
						+ "' where xsxh='" + xsxh + "'";
			} else {
				sql = "update " + realTable + " set xslb='" + xslb + "',id='"
						+ id + "',name='" + name + "',xbdm='" + xbdm
						+ "',zydm='" + zydm + "',zymc='" + zymc + "',xldm='"
						+ xldm + "',xzdm='" + xzdm + "',sydqdm='" + sydqdm
						+ "',pyfsdm='" + pyfsdm + "',nd='" + nd + "',bynd='"
						+ bynd + "',memo='" + memo + "',jgshi='" + jgshi
						+ "',jgx='" + jgx + "' where xsxh='" + xsxh + "'";
			}
			boolean judge = false;
			judge = dao.runUpdate(sql, new String[] {});
			if (xslb.equals("") || id.equals("") || name.equals("")
					|| xbdm.equals("") || xldm.equals("") || xzdm.equals("")
					|| sydqdm.equals("") || pyfsdm.equals("") || nd.equals("")
					|| zydm.equals("") || zymc.equals("") || bynd.equals("")) {
				request.setAttribute("updated1", "que");
			}
			if (judge) {
				request.setAttribute("updated", "ok");
				// �޸Ĳ����ļ�¼
				HttpSession session = request.getSession();
				String userName = session.getAttribute("userName").toString();
				String whensj = (dao
						.getOneRs(
								"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual",
								new String[] {}, new String[] { "sdate" }))[0]; // �ύʱ��

				StandardOperation.insert("JYGL_USERCZMXB",
						new String[] { "userid", "dowhat", "whichtable",
								"whichpk", "whensj" }, new String[] { userName,
								"�޸�", "ѧ��������Ϣ��", "ѧ��:" + xsxh, whensj },
						request);
			} else {
				request.setAttribute("updated", "no");
			}
			doType2 = null;
			sql = "select * from jygl_xsjbxxb where xsxh=?";
			String[] colList = dao
					.getColumnName("select * from jygl_xsjbxxb where 1=2");
			String[] stuinfo = dao
					.getOneRs(sql, new String[] { xsxh }, colList);
			sql = "select xymc from view_xsjbxx where xh=?";
			String[] xymc = dao.getOneRs(sql, new String[] { xsxh },
					new String[] { "xymc" });

			if (stuinfo != null) {
				for (int i = 0; i < colList.length; i++) {
					map.put(colList[i].toLowerCase(), stuinfo[i]); // ����¼ѭ������map��
				}
				map.put("xymc", xymc[0]);
				String sbsj = null;
				String sjyear = null;
				String sjmon = null;
				String sjday = null;
				String sj = map.get("sbsj").toString();
				sjyear = sj.substring(0, 4);
				sjmon = sj.substring(4, 6);
				sjday = sj.substring(6, 8);
				sbsj = sjyear + "��" + sjmon + "��" + sjday + "��";
				map.put("sbsj", sbsj);
			}
		}
		request.setAttribute("rs", map);// �������ݼ�
		return mapping.findForward("success");
	}

	// ��ҵЭ����Ϣ����
	private ActionForward jyxyInput(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			String userType) throws Exception {
		// TODO
		CommanForm dataSearchForm = (CommanForm) form;
		DAO dao = DAO.getInstance();
		HttpSession session = request.getSession();
		String sql = "";
		HashMap<String, String> map = new HashMap<String, String>();
		String doType = request.getParameter("doType");
		String act = request.getParameter("act");
		String xsxhao = request.getParameter("xsxh");
		String whichxxdm = StandardOperation.getXxdm();// ѧУ����
		request.setAttribute("xxdm", whichxxdm);
		StuInfoDAO stuDao = new StuInfoDAO();
		// ��������
		if (whichxxdm.equalsIgnoreCase(Globals.XXDM_YNYS)) {

			sql = "select dwxzdm,dwxz from dmk_dwxz2"; // ��λ���ʴ���2
			List dwxzdm2List = dao.getList(sql, new String[] {}, new String[] {
					"dwxzdm", "dwxz" });
			request.setAttribute("dwxzdm2List", dwxzdm2List);

			// ѧ��������Ϣ
			String xh = DealString.toGBK(request.getParameter("xh")); // ѧ��
			String xm = DealString.toGBK(request.getParameter("xm")); // ����
			String xb = DealString.toGBK(request.getParameter("xb")); // �Ա�
			String nl = DealString.toGBK(request.getParameter("nl")); // ����
			String mz = DealString.toGBK(request.getParameter("mz")); // ����
			String zzmm = DealString.toGBK(request.getParameter("zzmm")); // ������ò
			String pyfs = DealString.toGBK(request.getParameter("pyfs")); // ������ʽ
			String xl = DealString.toGBK(request.getParameter("xl")); // ѧ��
			String zymc = DealString.toGBK(request.getParameter("zymc")); // רҵ����
			String xz = DealString.toGBK(request.getParameter("xz")); // ѧ��
			String jtdz = DealString.toGBK(request.getParameter("jtdz")); // ��ͥ��ַ
			String lxdh = DealString.toGBK(request.getParameter("lxdh")); // ��ϵ�绰
			// ��λ������Ϣ
			String dwmc = DealString.toGBK(request.getParameter("dwmc")); // ��λ����
			String dwls = DealString.toGBK(request.getParameter("dwls")); // ��λ����
			String dwlxr = DealString.toGBK(request.getParameter("dwlxr")); // ��λ��ϵ��
			String dwlxdh = DealString.toGBK(request.getParameter("dwlxdh")); // ��λ��ϵ�绰
			String dwyb = DealString.toGBK(request.getParameter("dwyb")); // ��λ�ʱ�
			String dwdz = DealString.toGBK(request.getParameter("dwdz")); // ��λ��ַ
			String dwxz = DealString.toGBK(request.getParameter("dwxz")); // ��λ����
			String hyfl = DealString.toGBK(request.getParameter("hyfl")); // ��ҵ����
			String dayjdz = DealString.toGBK(request.getParameter("dayjdz")); // �����ʼĵ�ַ
			String fbsj = (dao
					.getOneRs(
							"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual",
							new String[] {}, new String[] { "sdate" }))[0]; // ����ʱ��

			if ("save".equals(doType)) {
				boolean judge = false;
				judge = StandardOperation.insert("ynys_jygl_jyxy",
						new String[] { "xh", "xm", "xb", "nl", "mz", "zzmm",
								"pyfs", "xl", "zymc", "xz", "jtdz", "lxdh",
								"dwmc", "dwls", "dwlxr", "dwlxdh", "dwyb",
								"dwdz", "dwxz", "hyfl", "dayjdz", "fbsj" },
						new String[] { xh, xm, xb, nl, mz, zzmm, pyfs, xl,
								zymc, xz, jtdz, lxdh, dwmc, dwls, dwlxr,
								dwlxdh, dwyb, dwdz, dwxz, hyfl, dayjdz, fbsj },
						request);

				if (judge) {
					request.setAttribute("save", "ok");
				} else {
					request.setAttribute("save", "no");
				}
			}
			// String usertype = session.getAttribute("userType").toString();
			String userName = session.getAttribute("userName").toString();
			sql = "select * from view_xsxxb where xh=?";
			String[] colList = { "xh", "xm", "xb", "mz", "zzmm", "zymc", "xz",
					"jtdz", "lxdh" };
			String[] stuinfo = dao.getOneRs(sql, new String[] { userName },
					new String[] { "xh", "xm", "xb", "mz", "zzmmmc", "zymc",
							"xz", "jtdz", "lxdh" });

			if (stuinfo != null) {
				for (int i = 0; i < colList.length; i++) {
					map.put(colList[i].toLowerCase(), stuinfo[i]); // ����¼ѭ������map��
				}
			}
			request.setAttribute("rs", map);
			return mapping.findForward("success2");
		}

		// �����������˽���

		ArrayList<HashMap<String, String>> ListA = new ArrayList<HashMap<String, String>>();
		String[] dwxzdmA = { "10", "15", "20", "21", "22", "23", "25", "29",
				"31", "32", "33", "35", "39", "40", "55", "56" };
		String[] dwxzdmA1 = { "��������", "�ؼ������µ������ء���ҵ��λ�����������֯*", "������Ƶ�λ",
				"�ߵȽ�����λ", "�еȡ����Ƚ�����λ", "ҽ��������λ", "�����ҵ��ҵ��λ*", "������ҵ��λ", "������ҵ",
				"������ҵ", "��С��ҵ*", "�����ҵ��ҵ*", "������ҵ", "����*", "ũ�彨�ƴ�*", "��������*" };
		for (int i = 0; i < 16; i++) {
			HashMap<String, String> mapA = new HashMap<String, String>();
			mapA.put("dwxzdm", dwxzdmA[i]);
			mapA.put("dwxz", dwxzdmA1[i]);
			ListA.add(mapA);
		}
		request.setAttribute("ListA", ListA);

		// b
		ArrayList<HashMap<String, String>> ListB = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> mapB = new HashMap<String, String>();
		mapB.put("dwxzdm", "80");
		ListB.add(mapB);
		request.setAttribute("ListB", ListB);

		// c
		ArrayList<HashMap<String, String>> ListC = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> mapC = new HashMap<String, String>();
		mapC.put("dwxzdm", "85");
		ListC.add(mapC);
		request.setAttribute("ListC", ListC);

		// d
		ArrayList<HashMap<String, String>> ListD = new ArrayList<HashMap<String, String>>();
		String[] dwxzdmD = { "75", "76", "77" };
		String[] dwxzdmD1 = { "������ҵ*", "����ְҵ", "��������ҵ" };
		for (int d = 0; d < 3; d++) {
			HashMap<String, String> mapD = new HashMap<String, String>();
			mapD.put("dwxzdm", dwxzdmD[d]);
			mapD.put("dwxz", dwxzdmD1[d]);
			ListD.add(mapD);
		}
		request.setAttribute("ListD", ListD);
		// e
		ArrayList<HashMap<String, String>> ListE = new ArrayList<HashMap<String, String>>();
		String[] dwxzdmE = { "50", "51" };
		for (int d = 0; d < 2; d++) {
			HashMap<String, String> mapE = new HashMap<String, String>();
			mapE.put("dwxzdm", dwxzdmE[d]);
			ListE.add(mapE);
		}
		request.setAttribute("ListE", "ListE");
		// F
		ArrayList<HashMap<String, String>> ListF = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> mapF = new HashMap<String, String>();
		mapF.put("dwxzdm", "70");
		ListF.add(mapF);
		request.setAttribute("ListF", ListF);

		// G
		ArrayList<HashMap<String, String>> ListG = new ArrayList<HashMap<String, String>>();
		String[] dwxzdmG = { "71", "72" };
		for (int g = 0; g < 2; g++) {
			HashMap<String, String> mapG = new HashMap<String, String>();
			mapG.put("dwxzdm", dwxzdmG[g]);
			ListG.add(mapG);
		}
		request.setAttribute("ListG", "ListG");
		// H
		ArrayList<HashMap<String, String>> ListH = new ArrayList<HashMap<String, String>>();
		String[] dwxzdmH = { "70", "71", "72" };
		for (int h = 0; h < 3; h++) {
			HashMap<String, String> mapH = new HashMap<String, String>();
			mapH.put("dwxzdm", dwxzdmH[h]);
			ListH.add(mapH);
		}
		request.setAttribute("ListH", "ListH");
		// I
		ArrayList<HashMap<String, String>> ListI = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> mapI = new HashMap<String, String>();
		mapI.put("dwxzdm", "");
		ListI.add(mapI);
		request.setAttribute("ListI", ListI);
		// J
		ArrayList<HashMap<String, String>> ListJ = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> mapJ = new HashMap<String, String>();
		mapJ.put("zgbm", "��ѧ");
		ListJ.add(mapJ);
		request.setAttribute("ListJ", ListJ);
		// K
		ArrayList<HashMap<String, String>> ListK = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> mapK = new HashMap<String, String>();
		mapK.put("zgbm", "��������ѧ���ӳ�");
		ListK.add(mapK);
		request.setAttribute("ListK", ListK);
		// L
		sql = "select zgbmdm,zgbm from dmk_zgbmL";
		List ListL = dao.getList(sql, new String[] {}, new String[] { "zgbmdm",
				"zgbm" });
		request.setAttribute("ListL", ListL);
		// M
		ArrayList<HashMap<String, String>> ListM = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> mapM = new HashMap<String, String>();
		mapM.put("zgbm", "");
		ListM.add(mapM);
		request.setAttribute("ListM", ListM);

		// ���ƽ����ĸ�logic��ǩ
		String byqxdm = request.getParameter("byqxdm");
		JyglService service = new JyglService();
		HashMap<String, String> flagmap = new HashMap<String, String>();
		if (!Base.isNull(byqxdm)) {
			flagmap = service.getRedFlag(byqxdm);
		}
		request.setAttribute("redflag", flagmap);
		if ("first".equals(act)) {
			request.setAttribute("whichlist", "H");
			request.setAttribute("whichzgbmlist", "X");
			request.setAttribute("danweiname", "X");
		}
		if ("01".equals(byqxdm) || "13".equals(byqxdm) || "15".equals(byqxdm)) {
			request.setAttribute("whichlist", "A");
		}
		if ("02".equals(byqxdm) || "03".equals(byqxdm) || "12".equals(byqxdm)) {
			request.setAttribute("whichlist", "B");
		}
		if ("04".equals(byqxdm)) {
			request.setAttribute("whichlist", "C");
			request.setAttribute("chuguo", "N");
		}
		if ("14".equals(byqxdm)) {
			request.setAttribute("whichlist", "D");
		}
		if ("17".equals(byqxdm)) {
			request.setAttribute("whichlist", "E");
		}
		if ("06".equals(byqxdm)) {
			request.setAttribute("whichlist", "F");
		}
		if ("07".equals(byqxdm)) {
			request.setAttribute("whichlist", "G");
		}
		if ("16".equals(byqxdm) || (null == byqxdm && (!"first".equals(act)))
				|| ("".equals(byqxdm) && (!"first".equals(act)))) {
			request.setAttribute("whichlist", "H");
		}
		if ("05".equals(byqxdm) || "08".equals(byqxdm) || "11".equals(byqxdm)) {
			request.setAttribute("whichlist", "I");
		}

		if ("02".equals(byqxdm) || "03".equals(byqxdm) || "12".equals(byqxdm)) {
			request.setAttribute("whichzgbmlist", "J");
		}
		if ("04".equals(byqxdm) || "05".equals(byqxdm) || "08".equals(byqxdm)
				|| "11".equals(byqxdm)) {
			request.setAttribute("whichzgbmlist", "K");
		}
		if ("01".equals(byqxdm) || "13".equals(byqxdm) || "14".equals(byqxdm)
				|| "15".equals(byqxdm) || "17".equals(byqxdm)) {
			request.setAttribute("whichzgbmlist", "L");
		}
		if ("06".equals(byqxdm) || "07".equals(byqxdm) || "16".equals(byqxdm)
				|| "".equals(byqxdm) || "09".equals(byqxdm)) {
			request.setAttribute("whichzgbmlist", "M");
		}
		// �͵�λ�����йص���ѡ��
		if ("01".equals(byqxdm) || "02".equals(byqxdm) || "03".equals(byqxdm)
				|| "12".equals(byqxdm) || "13".equals(byqxdm)
				|| "14".equals(byqxdm) || "15".equals(byqxdm)
				|| "17".equals(byqxdm)) {
			request.setAttribute("danweiname", "X");
		} else if ("04".equals(byqxdm)) {
			request.setAttribute("danweiname", "Y");
		} else {
			request.setAttribute("danweiname", "O");
		}

		// �ѵ�һ���ֵ���Ϣ���ݽ���
		if (userType.equalsIgnoreCase("student")) {
			act = "go";
		}

		if (Globals.XXDM_ZGDZDX.equals(whichxxdm)) {
			sql = "select pyfsdm,pyfs from dmk_bzpyfs";
			List pyfsList = dao.getList(sql, new String[] {}, new String[] {
					"pyfsdm", "pyfs" });
			request.setAttribute("pyfsList", pyfsList);

		}
		// �ж��Ƿ����Ϻ�����ѧ�� �Ϻ�����ѧ������ѡ����Դ�����ܲ���
		if ("310000".equals(map.get("sydqdm"))) {
			request.setAttribute("sydqdmIsSh", "kong");
		} else {
			request.setAttribute("sydqdmIsSh", "notkong");
		}
		// ͨ�õ�½����������Ϣ��ʾ
		sql = "select zzmmdm,zzmm from dmk_zzmm"; // ������ò����
		List zzmmdmList = dao.getList(sql, new String[] {}, new String[] {
				"zzmmdm", "zzmm" });
		request.setAttribute("zzmmdmList", zzmmdmList);
		if (userType.equals("teacher")) {
			sql = "select byqxdm,byqx from dmk_byqx";
		} else {
			sql = "select byqxdm,byqx from dmk_byqx where byqxdm not in('05','06','07','08','09','11')";
		}
		// ��ҵȥ�����
		List byqxdmList = dao.getList(sql, new String[] {}, new String[] {
				"byqxdm", "byqx" });
		request.setAttribute("byqxdmList", byqxdmList);

		sql = "select dwdqdm,dwdq from dmk_dwdq"; // ��λ����
		List dwdqList = dao.getList(sql, new String[] {}, new String[] {
				"dwdqdm", "dwdq" });
		request.setAttribute("dwdqList", dwdqList);

		sql = "select zgbmdm,zgbm from dmk_zgbm"; // ���ܲ��Ŵ���
		List zgbmList = dao.getList(sql, new String[] {}, new String[] {
				"zgbmdm", "zgbm" });
		request.setAttribute("zgbmList", zgbmList);

		sql = "select jzzhlbbzwdm,jzzhlbbzw from dmk_jzzhlbbzw"; // ��ס֤�������־λ
		List jzzhlbbzwdmList = dao.getList(sql, new String[] {}, new String[] {
				"jzzhlbbzwdm", "jzzhlbbzw" });
		request.setAttribute("jzzhlbbzwdmList", jzzhlbbzwdmList);

		sql = "select sydzgbmdm,sydzgbm from dmk_sydzgbm"; // ��Դ�����ܵ�λ����
		List sydzgbmList = dao.getList(sql, new String[] {}, new String[] {
				"sydzgbmdm", "sydzgbm" });
		request.setAttribute("sydzgbmList", sydzgbmList);

		sql = "select dwxzdm,dwxz from dmk_dwxz"; // ��λ���ʴ���
		List dwxzdmList = dao.getList(sql, new String[] {}, new String[] {
				"dwxzdm", "dwxz" });
		request.setAttribute("dwxzdmList", dwxzdmList);

		sql = "select dwxzdm,dwxz from dmk_dwxz2"; // ��λ���ʴ���2
		List dwxzdm2List = dao.getList(sql, new String[] {}, new String[] {
				"dwxzdm", "dwxz" });
		request.setAttribute("dwxzdm2List", dwxzdm2List);

		sql = "select dqlxdm,dqlx from dmk_dqlx"; // ��������
		List dqlxdmList = dao.getList(sql, new String[] {}, new String[] {
				"dqlxdm", "dqlx" });
		request.setAttribute("dqlxdmList", dqlxdmList);

		sql = "select hyfldm,hyfl from dmk_hyfl"; // ��ҵ����
		List hyflList = dao.getList(sql, new String[] {}, new String[] {
				"hyfldm", "hyfl" });
		request.setAttribute("hyflList", hyflList);

		String xxdm1 = (String) request.getSession().getAttribute("xxdm"); // 2ѧУ����
		String xsxh1 = DealString.toGBK(request.getParameter("xsxh"));
		if ("12453".equals(xxdm1) && !"".equals(xsxh1)) {
			String[] str = dao.getOneRs(
					"select sfsh from jygl_xsjbxxb where xsxh=?",
					new String[] { xsxh1 }, new String[] { "sfsh" });
			@SuppressWarnings("unused")
			boolean bool = false;
			if (str != null) {
				if ("��ͨ����".equals(str[0])) {
					bool = true;
				} else {
					doType = "shwtg";
					request.setAttribute("exists", "exists");
				}
			}
		}

		boolean isSave = false;

		if ("save".equals(doType)) {
			boolean save = false;
			String id = DealString.toGBK(request.getParameter("id")); // 1���֤��
			String xxdm = DealString.toGBK(request.getParameter("xxdm")); // 2ѧУ����
			String xx = DealString.toGBK(request.getParameter("xxmc")); // 3ѧУ����
			String zydm = DealString.toGBK(request.getParameter("zydm")); // 4רҵ����
			String zy = DealString.toGBK(request.getParameter("zymc")); // 5רҵ����
			String xsxh = DealString.toGBK(request.getParameter("xsxh")); // 6ѧ��
			String name = DealString.toGBK(request.getParameter("name")); // 7����
			String xbdm = DealString.toGBK(request.getParameter("xbdm")); // 8�Ա����
			String xldm = DealString.toGBK(request.getParameter("xldm")); // 9ѧ������
			String xzdm = DealString.toGBK(request.getParameter("xzdm")); // 10ѧ��
			String sydqdm = DealString.toGBK(request.getParameter("sydqdm")); // 11��Դ��������
			String pyfsdm = DealString.toGBK(request.getParameter("pyfsdm")); // 12������ʽ����
			String memo = DealString.toGBK(request.getParameter("memo")); // 13��ע
			String zzmmdm = DealString.toGBK(request.getParameter("zzmmdm")); // 14������ò����
			String sydq = DealString.toGBK(request.getParameter("sydq")); // 15��Դ��������
			String xxdjh = DealString.toGBK(request.getParameter("xxdjh")); // 16��Ϣ�ǼǺ�
			String zzjgdm = DealString.toGBK(request.getParameter("zzjgdm")); // 17��֯��������
			String dwmc = DealString.toGBK(request.getParameter("dwmc")); // 18��λ����
			String dwdqdm = DealString.toGBK(request.getParameter("dwdqdm")); // 19��λ��������
			String dwdq = DealString.toGBK(request.getParameter("dwdq")); // 20��λ��������
			String zgbm = DealString.toGBK(request.getParameter("zgbm")); // 21���ܲ�������
			byqxdm = DealString.toGBK(request.getParameter("byqxdm")); // 22��ҵȥ�����
			String blueno = DealString.toGBK(request.getParameter("blueno")); // 23������׼�ĺ�
			String wjybz = DealString.toGBK(request.getParameter("wjybz")); // 24δ��ҵ��־
			String wjyyy = DealString.toGBK(request.getParameter("wjyyy")); // 25δ��ҵԭ��
			String lxdz = DealString.toGBK(request.getParameter("lxdz")); // 26��ϵ��ַ
			String yb = DealString.toGBK(request.getParameter("yb")); // 27�ʱ�
			String dh = DealString.toGBK(request.getParameter("dh")); // 28�绰
			// String bgdabz = request.getParameter("bgdabz"); // 29���ܵ�����־
			String bz1 = DealString.toGBK(request.getParameter("bz1")); // 30�Զ���1
			String bz2 = DealString.toGBK(request.getParameter("bz2")); // 31�Զ���2
			String bz3 = DealString.toGBK(request.getParameter("bz3")); // 32�Զ���3
			String bz4 = DealString.toGBK(request.getParameter("bz4")); // 33�Զ���4
			String bz5 = DealString.toGBK(request.getParameter("bz5")); // 34�Զ���5
			String jzzhlbbzwdm = DealString.toGBK(request
					.getParameter("jzzhlbbzwdm")); // 35
			// ��ס֤�������־λ
			String sydzgbm = DealString.toGBK(request.getParameter("sydzgbm")); // 36��Դ�����ܵ�λ
			String dwxzdm = DealString.toGBK(request.getParameter("dwxzdm")); // 37��λ���ʴ���
			String zgbmdm = DealString.toGBK(request.getParameter("zgbmdm")); // 38���ܲ��Ŵ���
			String dwxzdm2 = DealString.toGBK(request.getParameter("dwxzdm2")); // 39��λ���ʴ���2
			String dwdz = DealString.toGBK(request.getParameter("dwdz")); // 40��λ��ַ
			String dwdh = DealString.toGBK(request.getParameter("dwdh")); // 41��λ�绰
			String dwyb = DealString.toGBK(request.getParameter("dwyb")); // 42��λ�ʱ�
			String dajsd = DealString.toGBK(request.getParameter("dajsd")); // 43�������ܵ�
			String dajsddz = DealString.toGBK(request.getParameter("dajsddz")); // 44�������ܵص�ַ
			String dajsdyb = DealString.toGBK(request.getParameter("dajsdyb")); // 45�������ܵ��ʱ�
			String dynypjgz = DealString
					.toGBK(request.getParameter("dynypjgz")); // 46��һ����ƽ������
			String wyj = DealString.toGBK(request.getParameter("wyj")); // 47ΥԼ��
			String dqlx = DealString.toGBK(request.getParameter("dqlx")); // 48��������
			String hyfl = DealString.toGBK(request.getParameter("hyfl")); // 49��ҵ����
			String zydk = DealString.toGBK(request.getParameter("zydk")); // 50רҵ�Կ�
			String xysbh = DealString.toGBK(request.getParameter("xysbh"));
			String dwlxr = request.getParameter("dwlxr");
			String dajsddwmc = request.getParameter("dajsddwmc");
			String dwgm = request.getParameter("dwgm");
			String dwzczj = request.getParameter("dwzczj");
			String bdkssj = request.getParameter("bdkssj");
			String bdjssj = request.getParameter("bdjssj");
			String bdzh = request.getParameter("bdzh");
			String tjsj = (dao
					.getOneRs(
							"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual", // 51�ύʱ��
							// //
							// ȡ�����ݿ���ʱ��
							new String[] {}, new String[] { "sdate" }))[0];
			String xslb = DealString.toGBK(request.getParameter("xslb")); // 52ѧ�����
			String bynd = DealString.toGBK(request.getParameter("bynd")); // 53��ҵ���
			String xymc = DealString.toGBK(request.getParameter("xymc")); // 59ѧԺ����
			String nd = DealString.toGBK(request.getParameter("nd")); // 60��ѧ���
			// 54���ʱ�� 55����ʱ�� 56��ͨ��ԭ�� 57����Ա��� 58ѧУ���
			if (Globals.XXDM_ZGDZDX.equals(whichxxdm)) {
				String jgshi = DealString.toGBK(request.getParameter("jgshi"));
				String zzmm = DealString.toGBK(request.getParameter("zzmm"));
				String mz = DealString.toGBK(request.getParameter("mz"));
				String zymc = DealString.toGBK(request.getParameter("zymc"));
				String qq = DealString.toGBK(request.getParameter("qq"));
				String xb = DealString.toGBK(request.getParameter("xb"));
				String wpdw = DealString.toGBK(request.getParameter("wpdw"));
				String zsmc = DealString.toGBK(request.getParameter("zsmc"));
				String zdcl = DealString.toGBK(request.getParameter("zdcl"));
				String bdzbz = DealString.toGBK(request.getParameter("bdzbz"));
				String sjdw = DealString.toGBK(request.getParameter("sjdw"));
				String pqdw = DealString.toGBK(request.getParameter("pqdw"));
				String pqdwid = DealString
						.toGBK(request.getParameter("pqdwid"));
				String dwxz1 = DealString.toGBK(request.getParameter("dwxz1"));
				String dwxz2 = DealString.toGBK(request.getParameter("dwxz2"));
				@SuppressWarnings("unused")
				String dwxzmc = DealString
						.toGBK(request.getParameter("dwxzmc"));
				String zgbm1 = DealString.toGBK(request.getParameter("zgbm1"));
				String zgbm2 = DealString.toGBK(request.getParameter("zgbm2"));
				String zgbmmc = DealString
						.toGBK(request.getParameter("zgbmmc"));
				String dwszd2 = DealString
						.toGBK(request.getParameter("dwszd2"));
				String dwszd3 = DealString
						.toGBK(request.getParameter("dwszd3"));
				String dwszd1 = DealString
						.toGBK(request.getParameter("dwszd1"));
				String sjszd1 = DealString
						.toGBK(request.getParameter("sjszd1"));
				String sjszd2 = DealString
						.toGBK(request.getParameter("sjszd2"));
				String sjszd3 = DealString
						.toGBK(request.getParameter("sjszd3"));
				String hkdz = DealString.toGBK(request.getParameter("hkdz"));
				String gprq = DealString.toGBK(request.getParameter("gprq"));
				String gpyy = DealString.toGBK(request.getParameter("gpyy"));
				String gpcs = DealString.toGBK(request.getParameter("gpcs"));
				String ydwmc = DealString.toGBK(request.getParameter("ydwmc"));
				String bdzbh = DealString.toGBK(request.getParameter("bdzbh"));
				String xxmc = DealString.toGBK(request.getParameter("xxmc"));
				String jyzk = DealString.toGBK(request.getParameter("jyzk"));
				String jgshi2 = DealString
						.toGBK(request.getParameter("jgshi2"));
				String jgshi3 = DealString
						.toGBK(request.getParameter("jgshi3"));
				save = StandardOperation.insert("jygl_jyxy", new String[] {
						"dwdq", "jgshi2", "jgshi3", "dajsd", "dajsdyb", "memo",
						"byqxdm", "pyfsdm", "tjsj", "nd", "xbdm", "xslb",
						"bynd", "xxdm", "xxmc", "jgshi", "sydqdm", "xzdm",
						"zzmm", "xldm", "xymc", "mz", "zymc", "qq", "xb", "id",
						"name", "xysbh", "xsxh", "wpdw", "zsmc", "zdcl",
						"bdzbz", "sjdw", "pqdw", "pqdwid", "dwxz1", "dwxz2",
						"dwxzdm2", "zgbm1", "zgbm2", "zgbmmc", "dwszd1",
						"dwszd2", "dwszd3", "sjszd1", "sjszd2", "sjszd3",
						"hkdz", "gprq", "gpyy", "gpcs", "ydwmc", "bdzbh",
						"wjybz", "wjyyy", "jyzk" }, new String[] { dwdq,
						jgshi2, jgshi3, dajsd, dajsdyb, memo, byqxdm, pyfsdm,
						tjsj, nd, xbdm, xslb, bynd, xxdm, xxmc, jgshi, sydqdm,
						xzdm, zzmm, xldm, xymc, mz, zymc, qq, xb, id, name,
						xysbh, xsxh, wpdw, zsmc, zdcl, bdzbz, sjdw, pqdw,
						pqdwid, dwxz1, dwxz2, dwxzdm2, zgbm1, zgbm2, zgbmmc,
						dwszd1, dwszd2, dwszd3, sjszd1, sjszd2, sjszd3, hkdz,
						gprq, gpyy, gpcs, ydwmc, bdzbh, wjybz, wjyyy, jyzk },
						request);
			} else if (Globals.XXDM_NGSXY.equalsIgnoreCase(xxdm)) {
				// �Ϲ���ѧԺ
				String filePath = null;
				String dir = request.getRealPath("/") + "upload";
				File f = new File(dir);
				if (!f.exists()) {
					f.mkdir();
				}
				String dateStr = "";
				Timestamp date = new Timestamp(System.currentTimeMillis());
				dateStr += date.toString().substring(0, 19);
				dateStr = dateStr.replaceAll("-", "").replaceAll(" ", "")
						.replaceAll(":", "");
				FormFile file = dataSearchForm.getUploadFile();
				if (file != null) {
					String fName = dateStr + file.getFileName();
					filePath = dir + "/" + fName;
				}
				save = StandardOperation.insert("jygl_jyxy", new String[] {
						"id", "xxdm", "xxmc", "zydm", "zymc", "xsxh", "name",
						"xbdm", "xldm", "xzdm", "sydqdm", "pyfsdm", "memo",
						"zzmmdm", "sydq", "xxdjh", "zzjgdm", "dwmc", "dwdqdm",
						"dwdq", "zgbm", "byqxdm", "blueno", "wjybz", "wjyyy",
						"lxdz", "yb", "dh", "bz1", "bz2", "bz3", "bz4", "bz5",
						"jzzhlbbzwdm", "sydzgbm", "dwxzdm", "zgbmdm",
						"dwxzdm2", "dwdz", "dwdh", "dwyb", "dajsd", "dajsddz",
						"dajsdyb", "dynypjgz", "wyj", "dqlx", "hyfl", "zydk",
						"tjsj", "xslb", "bynd", "xymc", "nd", "xysbh", "dwlxr",
						"dajsddwmc", "dwgm", "dwzczj", "bdkssj", "bdjssj",
						"bdzh", "wjdz" }, new String[] { id, xxdm, xx, zydm,
						zy, xsxh, name, xbdm, xldm, xzdm, sydqdm, pyfsdm, memo,
						zzmmdm, sydq, xxdjh, zzjgdm, dwmc, dwdqdm, dwdq, zgbm,
						byqxdm, blueno, wjybz, wjyyy, lxdz, yb, dh, bz1, bz2,
						bz3, bz4, bz5, jzzhlbbzwdm, sydzgbm, dwxzdm, zgbmdm,
						dwxzdm2, dwdz, dwdh, dwyb, dajsd, dajsddz, dajsdyb,
						dynypjgz, wyj, dqlx, hyfl, zydk, tjsj, xslb, bynd,
						xymc, nd, xysbh, dwlxr, dajsddwmc, dwgm, dwzczj,
						bdkssj, bdjssj, bdzh, filePath }, request);

				if (save) {
					// ����ɹ������ϴ�
					int size = file.getFileSize();
					if (size > 0) {// ���ļ��ϴ�
						InputStream in = file.getInputStream();
						OutputStream out = new FileOutputStream(filePath);
						int bytesRead = 0;
						byte[] buffer = new byte[size];
						while ((bytesRead = in.read(buffer, 0, size)) != -1) {
							out.write(buffer, 0, bytesRead);
						}
						out.close();
						in.close();
					}
				}

			} else {
				save = StandardOperation.insert("jygl_jyxy", new String[] {
						"id", "xxdm", "xxmc", "zydm", "zymc", "xsxh", "name",
						"xbdm", "xldm", "xzdm", "sydqdm", "pyfsdm", "memo",
						"zzmmdm", "sydq", "xxdjh", "zzjgdm", "dwmc", "dwdqdm",
						"dwdq", "zgbm", "byqxdm", "blueno", "wjybz", "wjyyy",
						"lxdz", "yb", "dh", "bz1", "bz2", "bz3", "bz4", "bz5",
						"jzzhlbbzwdm", "sydzgbm", "dwxzdm", "zgbmdm",
						"dwxzdm2", "dwdz", "dwdh", "dwyb", "dajsd", "dajsddz",
						"dajsdyb", "dynypjgz", "wyj", "dqlx", "hyfl", "zydk",
						"tjsj", "xslb", "bynd", "xymc", "nd", "xysbh", "dwlxr",
						"dajsddwmc", "dwgm", "dwzczj", "bdkssj", "bdjssj",
						"bdzh" }, new String[] { id, xxdm, xx, zydm, zy, xsxh,
						name, xbdm, xldm, xzdm, sydqdm, pyfsdm, memo, zzmmdm,
						sydq, xxdjh, zzjgdm, dwmc, dwdqdm, dwdq, zgbm, byqxdm,
						blueno, wjybz, wjyyy, lxdz, yb, dh, bz1, bz2, bz3, bz4,
						bz5, jzzhlbbzwdm, sydzgbm, dwxzdm, zgbmdm, dwxzdm2,
						dwdz, dwdh, dwyb, dajsd, dajsddz, dajsdyb, dynypjgz,
						wyj, dqlx, hyfl, zydk, tjsj, xslb, bynd, xymc, nd,
						xysbh, dwlxr, dajsddwmc, dwgm, dwzczj, bdkssj, bdjssj,
						bdzh }, request);
			}
			if (save) {
				request.setAttribute("save", "ok");
				// ��Ӳ����ļ�¼
				if ("teacher".equals(userType)) {
					String userName = session.getAttribute("userName")
							.toString();
					String whensj = (dao
							.getOneRs(
									"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual",
									new String[] {}, new String[] { "sdate" }))[0]; // �ύʱ��

					StandardOperation.insert("JYGL_USERCZMXB", new String[] {
							"userid", "dowhat", "whichtable", "whichpk",
							"whensj" }, new String[] { userName, "����", "��ҵЭ���",
							"ѧ��:" + xsxh, whensj }, request);
				}
			} else {
				request.setAttribute("save", "no");
			}

			// ----------2010/6/8 edit by luojw -------------

			String[] colList = new String[] { "id", "xxdm", "xxmc", "zydm",
					"zymc", "xsxh", "name", "xbdm", "xldm", "xzdm", "sydqdm",
					"pyfsdm", "memo", "zzmmdm", "sydq", "xxdjh", "zzjgdm",
					"dwmc", "dwdqdm", "dwdq", "zgbm", "byqxdm", "blueno",
					"wjybz", "wjyyy", "lxdz", "yb", "dh", "bz1", "bz2", "bz3",
					"bz4", "bz5", "jzzhlbbzwdm", "sydzgbm", "dwxzdm", "zgbmdm",
					"dwxzdm2", "dwdz", "dwdh", "dwyb", "dajsd", "dajsddz",
					"dajsdyb", "dynypjgz", "wyj", "dqlx", "hyfl", "zydk",
					"tjsj", "xslb", "bynd", "xymc", "nd", "xysbh", "dwlxr",
					"dajsddwmc", "dwgm", "dwzczj", "bdkssj", "bdjssj", "bdzh" };
			String[] valList = new String[] { id, xxdm, xx, zydm, zy, xsxh,
					name, xbdm, xldm, xzdm, sydqdm, pyfsdm, memo, zzmmdm, sydq,
					xxdjh, zzjgdm, dwmc, dwdqdm, dwdq, zgbm, byqxdm, blueno,
					wjybz, wjyyy, lxdz, yb, dh, bz1, bz2, bz3, bz4, bz5,
					jzzhlbbzwdm, sydzgbm, dwxzdm, zgbmdm, dwxzdm2, dwdz, dwdh,
					dwyb, dajsd, dajsddz, dajsdyb, dynypjgz, wyj, dqlx, hyfl,
					zydk, tjsj, xslb, bynd, xymc, nd, xysbh, dwlxr, dajsddwmc,
					dwgm, dwzczj, bdkssj, bdjssj, bdzh };

			getMapInfo(map, colList, valList);

			isSave = true;
			// ----------end-------------
		}

		if ("go".equalsIgnoreCase(act) || "go".equals(doType) || isSave) {
			// ��ˢ����ǰ��ֵ����ҳ��
			String xxdjh = DealString.toGBK(request.getParameter("xxdjh"));
			String zzjgdm = DealString.toGBK(request.getParameter("zzjgdm"));
			String dwmc = DealString.toGBK(request.getParameter("dwmc"));
			String zzmmdm = DealString.toGBK(request.getParameter("zzmmdm"));
			String lxdz = DealString.toGBK(request.getParameter("lxdz"));
			String yb = DealString.toGBK(request.getParameter("yb"));
			String dh = DealString.toGBK(request.getParameter("dh"));
			String jzzhlbbzwdm = DealString.toGBK(request
					.getParameter("jzzhlbbzwdm"));
			String memo = DealString.toGBK(request.getParameter("memo"));
			String sydzgbm = DealString.toGBK(request.getParameter("sydzgbm"));

			// �޸�
			String dwxzdm2 = DealString.toGBK(request.getParameter("dwxzdm2"));
			String dajsd = DealString.toGBK(request.getParameter("dajsd"));
			String dwdz = DealString.toGBK(request.getParameter("dwdz"));
			String dajsddz = DealString.toGBK(request.getParameter("dajsddz"));
			String dwdh = DealString.toGBK(request.getParameter("dwdh"));
			String dajsdyb = DealString.toGBK(request.getParameter("dajsdyb"));
			String dwyb = DealString.toGBK(request.getParameter("dwyb"));
			String dqlx = DealString.toGBK(request.getParameter("dqlx"));
			String wyj = DealString.toGBK(request.getParameter("wyj"));
			String hyfl = DealString.toGBK(request.getParameter("hyfl"));
			String dynypjgz = DealString
					.toGBK(request.getParameter("dynypjgz"));
			String zydk = DealString.toGBK(request.getParameter("zydk"));
			String wjyyy = DealString.toGBK(request.getParameter("wjyyy"));
			String wjybz = DealString.toGBK(request.getParameter("wjybz"));
			String bz1 = DealString.toGBK(request.getParameter("bz1"));
			String bz2 = DealString.toGBK(request.getParameter("bz2"));
			String bz3 = DealString.toGBK(request.getParameter("bz3"));
			String bz4 = DealString.toGBK(request.getParameter("bz4"));
			String bz5 = DealString.toGBK(request.getParameter("bz5"));
			// String bdkssj = request.getParameter("bdkssj");
			// String bdjssj = request.getParameter("bdjssj");
			// String bdzh = request.getParameter("bdzh");

			map.put("dwxzdm2", dwxzdm2);
			map.put("dajsd", dajsd);
			map.put("dwdz", dwdz);
			map.put("dajsddz", dajsddz);
			map.put("dwdh", dwdh);
			map.put("dajsdyb", dajsdyb);
			map.put("dwyb", dwyb);
			map.put("dqlx", dqlx);
			map.put("wyj", wyj);
			map.put("hyfl", hyfl);
			map.put("dynypjgz", dynypjgz);
			map.put("zydk", zydk);
			map.put("wjybz", wjybz);
			map.put("wjyyy", wjyyy);
			map.put("bz1", bz1);
			map.put("bz2", bz2);
			map.put("bz3", bz3);
			map.put("bz4", bz4);
			map.put("bz5", bz5);

			map.put("xxdjh", xxdjh);
			map.put("zzjgdm", zzjgdm);
			map.put("dwmc", dwmc);
			map.put("zzmmdm", zzmmdm);
			map.put("lxdz", lxdz);
			map.put("yb", yb);
			map.put("dh", dh);
			map.put("jzzhlbbzwdm", jzzhlbbzwdm);
			map.put("memo", memo);
			map.put("sydzgbm", sydzgbm);

			if ("go".equals(doType)) {
				xsxhao = request.getParameter("xsxh");

			}
			if (userType.equalsIgnoreCase("student")) {
				xsxhao = session.getAttribute("userName").toString();
			}
			sql = "select * from jygl_xsjbxxb where xsxh=?"; // ��ѯ��ѧ�ŵ�������ݵ�sql���
			String[] colList = dao
					.getColumnName("select * from jygl_xsjbxxb where 1=2"); // ������������
			String[] rs = dao.getOneRs(sql, new String[] { xsxhao }, colList); // ��ôӱ�ҵ��������Ϣ����ͼ���в�ѯ�ļ�¼��
			if (rs != null) {
				for (int i = 0; i < colList.length; i++) {
					map.put(colList[i].toLowerCase(), rs[i]); // ����¼ѭ������map��
				}
			}

			String zydm = map.get("zydm");
			sql = "select jygl_zydm,jygl_zymc from DMK_ZYDMDZB where jwc_zydm=?";
			String[] dminfo = dao.getOneRs(sql, new String[] { zydm },
					new String[] { "jygl_zydm", "jygl_zymc" });
			if (null != dminfo) {
				map.put("zydm", dminfo[0]);
				map.put("zymc", dminfo[1]);
			}
			if (Globals.XXDM_ZGDZDX.equals(whichxxdm)) {
				// ת�������Ա����
				if ("1".equals(map.get("xbdm"))) {
					map.put("xb", "��");
				} else {
					map.put("xb", "Ů");
				}
				// ѧ�ƴ���ת��
				// String xz = (map.get("xzdm")) + "��";
				// map.put("xzdm", xz);
				// ת��ѧ������
				// sql = "select xl from dmk_xl where xldm=?";
				// String xldm = map.get("xldm");
				// String xl = "";
				// String[] xlinfo = dao.getOneRs(sql, new String[] { xldm },
				// new String[] { "xl" });
				// if (null != xlinfo) {
				// xl = xlinfo[0];
				// }
				// map.put("xlmc", xl);
				// // ת��������ʽ����(��ר�����о���)
				// String xslb = map.get("xslb");
				// if ("ר����".equals(xslb) || "������".equals(xslb)) {
				// sql = "select pyfs from dmk_bzpyfs where pyfsdm=?";
				// String pyfsdm = map.get("pyfsdm");
				// String pyfs = "";
				// String[] pyfsinfo = dao.getOneRs(sql, new String[] { pyfsdm
				// },
				// new String[] { "pyfs" });
				// if (null != pyfsinfo) {
				// pyfs = pyfsinfo[0];
				// }
				// map.put("pyfsdm", pyfs);
				// } else if ("�о���".equals(xslb)) {
				// sql = "select pyfs from dmk_yjspyfs where pyfsdm=?";
				// String pyfsdm = map.get("pyfsdm");
				// String pyfs = "";
				// String[] pyfsinfo = dao.getOneRs(sql, new String[] { pyfsdm
				// },
				// new String[] { "pyfs" });
				// if (null != pyfsinfo) {
				// pyfs = pyfsinfo[0];
				// }
				// map.put("pyfsdm", pyfs);
				// }
			}

			String sydqdm = map.get("sydqdm");
			sql = "select sydq from dmk_sydq where sydqdm=?";
			String[] sydqmc = dao.getOneRs(sql, new String[] { sydqdm },
					new String[] { "sydq" });
			if (sydqmc != null) {
				map.put("sydq", sydqmc[0]);
			}
			if (!Base.isNull(dwmc)) {
				map.put("dwmc", dwmc);
			}
			sql = "select xymc from view_xsjbxx where xh=?";
			String[] xymc1 = dao.getOneRs(sql, new String[] { xsxhao },
					new String[] { "xymc" });
			if (xymc1 != null) {
				map.put("xymc", xymc1[0]);
				map.put("dwdq", DealString.toGBK(request.getParameter("dwdq")));
			}

			if ("go".equals(request.getParameter("doType"))
					|| "go".equals(request.getParameter("act"))) {
				String dwdq = DealString.toGBK(request.getParameter("dwdq"));
				sql = "select * from dmk_dwdq where dwdq=?";
				String[] rs1 = dao.getOneRs(sql, new String[] { dwdq },
						new String[] { "dwdqdm" });
				map.put("dwdqdm", "310100");
				if (rs1 != null) {
					map.put("dwdqdm", rs1[0]);
				}
				if ("04".equals(request.getParameter("byqxdm"))) {
					map.put("dwdqdm", "990000");
				}
			}
			map.put("zgbm", DealString.toGBK(request.getParameter("zgbm")));

			if ("01".equals(byqxdm) || "13".equals(byqxdm)
					|| "14".equals(byqxdm) || "15".equals(byqxdm)
					|| "17".equals(byqxdm)) {
				String zgbm = DealString.toGBK(request.getParameter("zgbm"));
				sql = "select * from dmk_zgbm where zgbm=?";
				String[] rs2 = dao.getOneRs(sql, new String[] { zgbm },
						new String[] { "zgbmdm" });
				if (rs2 != null) {
					map.put("zgbmdm", rs2[0]);
				}
			}
		}

		map.put("byqxdm", byqxdm);
		if ("save".equals(doType)) {
			map.put("byqxdm", "");
		}
		map.put("stuExists", "yes");
		map.put("userType", userType);

		String cgxys = request.getParameter("cgxys");
		if ("cgxys".equals(cgxys) || cgxys == "cgxys") {
			map.put("xh", map.get("xsxh"));
			map.put("xm", map.get("name"));
		}
		request.setAttribute("rs", map);
		// String dd = map.get("dwmc").toString();
		String xxdm = dao.getXxdm();

		if (Globals.XXDM_ZGDZDX.equals(xxdm)) {
			request.setAttribute("shiList", stuDao.getShiList("")
					.get("shiList"));
			request.setAttribute("xianList", stuDao.getShiList("").get(
					"xianList"));
			request.setAttribute("ssList", stuDao.getSsList());
			return mapping.findForward("successdz");
		} else if (Globals.XXDM_CSLGXY.equals(xxdm)) {// ������ѧԺ
			request.setAttribute("shiList", stuDao.getShiList("")
					.get("shiList"));
			request.setAttribute("xianList", stuDao.getShiList("").get(
					"xianList"));
			request.setAttribute("ssList", stuDao.getSsList());
			return mapping.findForward("success");
		} else if ("cgxys".equals(cgxys) || cgxys == "cgxys") {
			return mapping.findForward("success1");
		} else {
			return mapping.findForward("success");
		}
	}

	// �������ڲ�ѯѧ����Ϣ Ȼ�������1����ҵЭ��
	private ActionForward jyxyTurnInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			String userType) throws Exception {
		CommanForm commanForm = (CommanForm) form;
		DAO dao = DAO.getInstance(); // ʵ����ͨ�÷�������������ݿ�����
		ArrayList<Object> rs = new ArrayList<Object>();
		String[] colList = null;
		String[] colListCN = null;
		String sql = ""; // sql���
		String querry = " where 1=1 "; // sql����
		String tableName = "jygl_xsjbxxb"; // ��ѯ��ϢԴ����Ϊ��ͼ����
		String rsNum = "0";// ���صļ�¼��
		String dataType = DealString.toGBK(request.getParameter("act")); // ������������
		String xsxh = DealString.toGBK(request.getParameter("xsxh")); // ����ѧ��
		String name = DealString.toGBK(request.getParameter("name")); // ��������
		String xbdm = DealString.toGBK(request.getParameter("xbdm")); // �����Ա����
		String xslb = DealString.toGBK(request.getParameter("xslb")); // ����ѧ�����
		String xymc = DealString.toGBK(request.getParameter("xymc")); // ����ѧԺ����
		String nd = DealString.toGBK(request.getParameter("nd")); // �������
		String bynd = DealString.toGBK(request.getParameter("bynd")); // ��ҵ���
		String pk = "xsxh"; // ����
		HashMap<String, String> map = new HashMap<String, String>();
		String act = DealString.toGBK(request.getParameter("act"));
		String bysjytj = request.getParameter("bysjytjb");
		if (StringUtils.isNotNull(bysjytj)) {
			request.setAttribute("bysjytjb", bysjytj);
		}

		HttpSession session = request.getSession();
		String userName = session.getAttribute("userName").toString();
		if ("teacher".equals(userType)) {
			sql = "select usertype,xymc from JYGL_JYXYWHB where username=?";
			String[] teainfo = dao.getOneRs(sql, new String[] { userName },
					new String[] { "usertype", "xymc" });
			String userty = "";
			if (null != teainfo) {
				userty = teainfo[0];
				xymc = teainfo[1];
			}
			if ("����Ա".equals(userty)) {
				map.put("xymc", xymc);
				request.setAttribute("who", "fudaoyuan");
			} else {
				request.setAttribute("who", "teacher");
			}
		}

		if ("go".equals(act)) {
			map.put("xsxh", xsxh);
			map.put("name", name);
			map.put("xbdm", xbdm);
			map.put("xslb", xslb);
			map.put("xymc", xymc);
			map.put("nd", nd);
			map.put("bynd", bynd);
		}

		if (xsxh == null) {
			xsxh = "";
		}
		if (name == null) {
			name = "";
		}
		if (xbdm == null) {
			xbdm = "";
		}
		if (xslb == null) {
			xslb = "";
		}
		if (xymc == null) {
			xymc = "";
		}
		if (nd == null) {
			nd = "";
		}
		if (bynd == null) {
			bynd = "";
		}
		if ((xsxh == null) || xsxh.equals("")) {
			querry += "and 1=1 ";
		} else {
			querry += "and xsxh like '%" + xsxh + "%' ";
		}
		if ((name == null) || name.equals("")) {
			querry += "and 1=1 ";
		} else {
			querry += "and name like '%" + name + "%' ";
		}
		if ((xbdm == null) || xbdm.equals("")) {
			querry += " and 1=1 ";
		} else {
			querry += " and xbdm='" + xbdm + "' ";
		}
		if ((xslb == null) || xslb.equals("")) {
			querry += " and 1=1 ";
		} else {
			querry += " and xslb='" + xslb + "' ";
		}
		if ((xymc == null) || xymc.equals("")) {
			querry += " and 1=1 ";
		} else {
			querry += " and xsxh in (select xsxh from jygl_xsjbxxb where xsxh in (select xh from view_xsjbxx where xymc ='"
					+ xymc + "'))";
		}
		if ((nd == null) || nd.equals("")) {
			querry += " and 1=1 ";
		} else {
			querry += " and nd='" + nd + "' ";
		}
		if ((bynd == null) || bynd.equals("")) {
			querry += " and 1=1 ";
		} else {
			querry += " and bynd='" + bynd + "' ";
		}

		sql = "select " + pk + " ����,rownum r,a.nd,a.bynd,a.xsxh,a.name,a.xslb,a.zymc,a.sbsj," +
				"case when xbdm='1' then '��' else 'Ů' end xb from " + tableName + " a "
				+ querry;
		colList = new String[] { "����", "r", "nd", "bynd", "xsxh", "name",
				"xb", "xslb", "zymc", "sbsj" };
		colListCN = dao.getColumnNameCN(colList, tableName);
		List topTr = dao.arrayToList(colList, colListCN);
		if ((request.getParameter("act") != null)
				&& request.getParameter("act").equalsIgnoreCase("go")) {
			rs.addAll(CommonQueryDAO.commonQuery(sql, "", new String[] {},
					colList, commanForm));
			if (rs == null) {
				rsNum = "0";
			} else {
				rsNum = String.valueOf(rs.size());
			}
		}
		String cgxys = request.getParameter("cgxys");
		if (cgxys == "cgxys" || "cgxys".equals(cgxys)) {
			request.setAttribute("cgxys", cgxys);
		}
		request.setAttribute("ndList", dao.getNjList());// ��������б�
		request.setAttribute("xyList", dao.getXyList());// ����ѧԺ�б�
		request.setAttribute("act", dataType);// �������ݲ�ѯ����
		request.setAttribute("rs", rs);// �������ݼ�
		request.setAttribute("rs1", map);// ���Ͳ�ѯ��������
		request.setAttribute("topTr", topTr);// ���ͱ�ͷ
		request.setAttribute("rsNum", rsNum);// ���ͼ�¼��
		return mapping.findForward("success");
	}

	// ��ҵЭ���ѯ
	private ActionForward JyxyDataSearch(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response, String userType) throws Exception {
		CommanForm commanForm = (CommanForm) form;
		DAO dao = DAO.getInstance(); // ʵ����ͨ�÷�������������ݿ�����
		ArrayList<Object> rs = new ArrayList<Object>(); // ��ü�¼
		ArrayList<HashMap<String, String>> ynysrs = new ArrayList<HashMap<String, String>>();// �����������
		String[] colList = null; // ��������
		String[] colListCN = null; // ������������
		String sql = ""; // sql���
		String querry = " where 1=1 "; // sql����
		String tableName = "jygl_jyxy"; // ��ѯ��ϢԴ����Ϊ��ͼ����
		String rsNum = "0"; // ���صļ�¼��
		String dataType = DealString.toGBK(request.getParameter("act")); // ������������
		String xsxh = DealString.toGBK(request.getParameter("xsxh")); // ����ѧ��
		String name = DealString.toGBK(request.getParameter("name")); // ��������
		String xbdm = DealString.toGBK(request.getParameter("xbdm")); // �����Ա����
		String xslb = DealString.toGBK(request.getParameter("xslb")); // ����ѧ�����
		String xymc = DealString.toGBK(request.getParameter("xymc")); // ����ѧԺ����
		String bynd = DealString.toGBK(request.getParameter("bynd")); // ��ҵ���
		String nd = DealString.toGBK(request.getParameter("nd")); // ��ѧ���
		String wjybz = DealString.toGBK(request.getParameter("wjybz")); // δ��ҵ��־
		String fdysh = DealString.toGBK(request.getParameter("fdysh")); // ����Ա���
		String xxsh = DealString.toGBK(request.getParameter("xxsh")); // ѧУ���
		String pk = "xsxh"; // ����
		HashMap<String, String> map = new HashMap<String, String>();
		String doType = DealString.toGBK(request.getParameter("doType"));
		String doType2 = DealString.toGBK(request.getParameter("doType2"));
		String pkValue = DealString.toGBK(request.getParameter("pkValue"));

		HttpSession session = request.getSession();// ��session�л����Ϣ
		String userName = session.getAttribute("userName").toString();
		String ynys_userty = session.getAttribute("userType").toString();
		String ynys_userdep = session.getAttribute("userDep").toString();
		String xysbh = request.getParameter("xysbh");
		String userlx = "";

		String whichxx = StandardOperation.getXxdm();

		if (whichxx.equalsIgnoreCase(Globals.XXDM_YNYS)) {// ��������

			if (ynys_userty.equalsIgnoreCase("xx")
					|| ynys_userty.equalsIgnoreCase("admin")) {
				request.setAttribute("who", "xx");

			} else if (ynys_userty.equalsIgnoreCase("xy")) {
				request.setAttribute("who", "xy");
			}

			StringBuffer query = new StringBuffer();
			query.append(" where 1=1 ");
			String xh = DealString.toGBK(request.getParameter("xh"));
			String xm = DealString.toGBK(request.getParameter("xm"));
			String xb = DealString.toGBK(request.getParameter("xb"));
			String nj = DealString.toGBK(request.getParameter("nj"));
			String xydm = DealString.toGBK(request.getParameter("xydm"));
			String zydm = DealString.toGBK(request.getParameter("zydm"));
			String bjdm = DealString.toGBK(request.getParameter("bjdm"));

			if ("del".equals(doType2)) {
				boolean judge = false;
				sql = "delete from ynys_jygl_jyxy where xh=?";
				judge = StandardOperation.delete("ynys_jygl_jyxy", "xh",
						pkValue, request);
				if (judge) {
					request.setAttribute("delete", "ok");
				} else {
					request.setAttribute("delete", "no");
				}
			}
			map.put("xh", xh);
			map.put("xm", xm);
			map.put("xb", xb);
			map.put("nj", nj);
			map.put("xydm", xydm);
			map.put("zydm", zydm);
			map.put("bjdm", bjdm);

			if (ynys_userty.equalsIgnoreCase("xy")) {
				map.put("xydm", ynys_userdep);
			}

			if (!"".equalsIgnoreCase(xh) && null != xh) {
				query.append(" and xh like '%");
				query.append(xh);
				query.append("%' ");
			}
			if (!"".equalsIgnoreCase(xm) && null != xm) {
				query.append(" and xm like '%");
				query.append(xm);
				query.append("%' ");
			}
			if (!"".equalsIgnoreCase(xb) && null != xb) {
				query.append(" and xb like '%");
				query.append(xb);
				query.append("%' ");
			}

			if (!"".equalsIgnoreCase(xydm) && null != xydm) {
				query.append(" and xydm='");
				query.append(xydm);
				query.append("' ");
			}

			if (!"".equalsIgnoreCase(zydm) && null != zydm) {
				query.append(" and zydm='");
				query.append(zydm);
				query.append("' ");
			}

			if (!"".equalsIgnoreCase(bjdm) && null != bjdm) {
				query.append(" and bjdm='");
				query.append(bjdm);
				query.append("' ");
			}

			querry = query.toString();

			sql = "select rownum �к�,a.* from view_ynys_jygl_jyxy a " + querry;
			colList = new String[] { "�к�", "xh", "xm", "xb", "xymc", "zymc",
					"bjmc", "fbsj" };
			colListCN = dao.getColumnNameCN(colList, "view_ynys_jygl_jyxy");
			List topTr = dao.arrayToList(colList, colListCN);
			if ((request.getParameter("act") != null)
					&& request.getParameter("act").equalsIgnoreCase("go")) {
				ynysrs = dao.getArrayList2(sql, new String[] {}, colList);
				if (rs == null) {
					rsNum = "0";
				} else {
					rsNum = String.valueOf(ynysrs.size());
				}
			}

			request.setAttribute("njList", dao.getNjList()); // �����꼶�б�
			request.setAttribute("xyList", dao.getXyList());// ����ѧԺ�б�
			request.setAttribute("zyList", dao.getZyList(xydm));// ����רҵ�б�
			request.setAttribute("bjList", dao.getBjList(xydm, zydm, nj));// ���Ͱ༶�б�
			request.setAttribute("rs", ynysrs); // �������ݼ�
			request.setAttribute("topTr", topTr); // ���ͱ�ͷ
			request.setAttribute("rsNum", rsNum); // ���ͼ�¼��
			request.setAttribute("rs1", map); // �Ѳ�ѯ�����ٴη�װ���ͻ�ȥ
			return mapping.findForward("success2");

		}

		if ("teacher".equals(userType)) {
			sql = "select usertype,xymc from JYGL_JYXYWHB where username=?";
			String[] teainfo = dao.getOneRs(sql, new String[] { userName },
					new String[] { "usertype", "xymc" });
			String userty = "";
			if (null != teainfo) {
				userty = teainfo[0];
				xymc = teainfo[1];
			}
			if ("����Ա".equals(userty)) {
				map.put("xymc", xymc);
				request.setAttribute("who", "fudaoyuan");
			} else {
				request.setAttribute("who", "teacher");
			}
		}
		if (ynys_userty.equalsIgnoreCase("xx")
				|| ynys_userty.equalsIgnoreCase("admin")) {
			request.setAttribute("whos", "xx");

		} else if (ynys_userty.equalsIgnoreCase("xy")) {
			request.setAttribute("whos", "xy");
		}
		// ɾ����ҵЭ������
		if ("del".equals(doType2)) {
			DyxxDAO dyxxDao = new DyxxDAO();
			boolean judge = false;
			String xxdm = Base.xxdm;
			sql = "delete from " + tableName + " where " + pk + "='" + pkValue
					+ "'";
			if (Globals.XXDM_NGSXY.equalsIgnoreCase(xxdm)) {
				dyxxDao.fileDel(tableName, "wjdz", pk, pkValue);
			}
			judge = dao.runUpdate(sql, new String[] {});

			if (judge) {
				request.setAttribute("delete", "ok");

				// ɾ�������ļ�¼
				String whensj = (dao
						.getOneRs(
								"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual",
								new String[] {}, new String[] { "sdate" }))[0]; // �ύʱ��

				StandardOperation.insert("JYGL_USERCZMXB",
						new String[] { "userid", "dowhat", "whichtable",
								"whichpk", "whensj" }, new String[] { userName,
								"ɾ��", "��ҵЭ���", "ѧ��:" + pkValue, whensj },
						request);
			} else {
				request.setAttribute("delete", "no");
			}
		}

		// ���ϴ��ύ��ֵ����ȥ
		if ("query".equals(doType)) {
			map.put("xsxh", xsxh);
			map.put("name", name);
			map.put("xbdm", xbdm);
			map.put("xslb", xslb);
			map.put("xymc", xymc);
			map.put("bynd", bynd);
			map.put("wjybz", wjybz);
			map.put("fdysh", fdysh);
			map.put("xxsh", xxsh);
			map.put("nd", nd);
		}

		if (xsxh == null) {
			xsxh = "";
		}
		if (name == null) {
			name = "";
		}
		if (xbdm == null) {
			xbdm = "";
		}
		if (xslb == null) {
			xslb = "";
		}
		if (xymc == null) {
			xymc = "";
		}
		if (bynd == null) {
			bynd = "";
		}
		if (wjybz == null) {
			wjybz = "";
		}
		if (fdysh == null) {
			fdysh = "";
		}
		if (xxsh == null) {
			xxsh = "";
		}
		if (nd == null) {
			nd = "";
		}

		if ((xsxh == null) || xsxh.equals("")) {
			querry += "and 1=1 ";
		} else {
			querry += "and xsxh like '%" + xsxh + "%' ";
		}
		if ((name == null) || name.equals("")) {
			querry += "and 1=1 ";
		} else {
			querry += "and name like '%" + name + "%' ";
		}
		if ((xbdm == null) || xbdm.equals("")) {
			querry += " and 1=1 ";
		} else {
			querry += " and xbdm='" + xbdm + "' ";
		}
		if ((xslb == null) || xslb.equals("")) {
			querry += " and 1=1 ";
		} else {
			querry += " and xslb='" + xslb + "' ";
		}
		if ((xymc == null) || xymc.equals("")) {
			querry += " and 1=1 ";
		} else {
			querry += " and xymc='" + xymc + "' ";
		}
		if ((bynd == null) || bynd.equals("")) {
			querry += " and 1=1 ";
		} else {
			querry += " and bynd='" + bynd + "' ";
		}
		if ((wjybz == null) || wjybz.equals("")) {
			querry += " and 1=1 ";
		} else {
			querry += " and wjybz='" + wjybz + "' ";
		}
		if ((fdysh == null) || fdysh.equals("")) {
			querry += " and 1=1 ";
		} else {
			querry += " and fdysh='" + fdysh + "' ";
		}
		if ((xxsh == null) || xxsh.equals("")) {
			querry += " and 1=1 ";
		} else {
			querry += " and xxsh='" + xxsh + "' ";
		}
		if ((nd == null) || nd.equals("")) {
			querry += " and 1=1 ";
		} else {
			querry += " and nd='" + nd + "' ";
		}
		if ("12061".equals(whichxx) || "11122".equals(whichxx)) {
			if ("xx".equals(ynys_userty) || "admin".equals(ynys_userty)) {
				querry += " and fdysh='��ͨ����'";
			}
		}
		if (StringUtils.isNotNull(xysbh)) {
			querry += " and xysbh='" + xysbh + "' ";
		}

		sql = "select "
				+ pk
				+ " ����,rownum r,(select xb from dmk_xb b where a.xbdm=b.xbdm) xb,a.nd,a.bynd,a.name,a.xsxh,a.xslb,a.xymc,a.zymc,a.fdysh,a.xxsh from "
				+ tableName + " a " + querry;
		colList = new String[] { "����", "r", "nd", "bynd", "name", "xsxh", "xb",
				"xslb", "xymc", "zymc", "fdysh", "xxsh" };
		colListCN = dao.getColumnNameCN(colList, tableName);
		List topTr = dao.arrayToList(colList, colListCN);
		if ((request.getParameter("act") != null)
				&& request.getParameter("act").equalsIgnoreCase("go")) {
			rs.addAll(CommonQueryDAO.commonQuery(sql, "", new String[] {},
					colList, commanForm));
			if (rs == null) {
				rsNum = "0";
			} else {
				rsNum = String.valueOf(rs.size());
			}
		}
		if ("xx".equals(ynys_userty) || "admin".equals(ynys_userty)) {
			userlx = "xx";
		}
		if (ynys_userty.equalsIgnoreCase("xy")) {
			map.put("xymc", session.getAttribute("bmmc").toString());
			request.setAttribute("whos", "xy");
		}

		request.setAttribute("userlx", userlx);
		request.setAttribute("realTable", "jygl_jyxy"); // ����ѧ��������Ϣ��
		request.setAttribute("ndList", dao.getNjList()); // ������ѧ����б�
		request.setAttribute("xyList", dao.getXyList());// ����ѧԺ�б�
		request.setAttribute("act", dataType); // �������ݲ�ѯ����
		request.setAttribute("rs", rs); // �������ݼ�
		request.setAttribute("topTr", topTr); // ���ͱ�ͷ
		request.setAttribute("rsNum", rsNum); // ���ͼ�¼��
		request.setAttribute("rs1", map); // �Ѳ�ѯ�����ٴη�װ���ͻ�ȥ
		return mapping.findForward("success");
	}

	// ����鿴��ҵЭ�����ϸ��Ϣ
	private ActionForward JyglJyxyViewMoreinfo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String pk = "xsxh";
		String sql = ""; // sql���
		DAO dao = DAO.getInstance();
		String realTable = "jygl_jyxy";
		String doType = request.getParameter("doType");
		String pkValue = request.getParameter("pkValue");
		HashMap<String, String> map = new HashMap<String, String>();
		String whichxx = StandardOperation.getXxdm();

		if (whichxx.equalsIgnoreCase(Globals.XXDM_YNYS)) {// ��������
			if (doType.equalsIgnoreCase("view")) {
				sql = "select * from ynys_jygl_jyxy where xh='" + pkValue + "'";
				String[] colList = dao
						.getColumnName("select * from ynys_jygl_jyxy where 1=2"); // ������������
				String[] stuinfo = dao.getOneRs(sql, new String[] {}, colList);
				if (stuinfo != null) {
					for (int i = 0; i < colList.length; i++) {
						map.put(colList[i].toLowerCase(), stuinfo[i]); // ����¼ѭ������map��
					}

				}
			}
			request.setAttribute("rs", map);// �������ݼ�
			return mapping.findForward("success2");

		}

		if ((doType == null) || doType.equalsIgnoreCase("")) {
			// �����쳣
			return mapping.findForward("false");
		} else if (doType.equalsIgnoreCase("view")) {
			// ��ѯ����
			sql = "select * from " + realTable + " where " + pk + "='"
					+ pkValue + "'";
			String[] colList = dao
					.getColumnName("select * from jygl_jyxy where 1=2"); // ������������
			String[] stuinfo = dao.getOneRs(sql, new String[] {}, colList);
			if (stuinfo != null) {
				for (int i = 0; i < colList.length; i++) {
					map.put(colList[i].toLowerCase(), stuinfo[i]); // ����¼ѭ������map��
				}
				// ����Ա���ʱ��
				String fdyshsj = null;
				String sjyear = null;
				String sjmon = null;
				String sjday = null;
				String sjhour = null;
				String sjmin = null;
				String sjss = null;
				if (null != map.get("fdyshsj")) {
					String sj = map.get("fdyshsj").toString();
					sjyear = sj.substring(0, 4);
					sjmon = sj.substring(4, 6);
					sjday = sj.substring(6, 8);
					sjhour = sj.substring(8, 10);
					sjmin = sj.substring(10, 12);
					sjss = sj.substring(12, 14);
					fdyshsj = sjyear + "��" + sjmon + "��" + sjday + "�� "
							+ sjhour + ":" + sjmin + ":" + sjss;
					map.put("fdyshsj", fdyshsj);
				}
				// ѧУ���ʱ��
				String xxshsj = null;
				sjyear = null;
				sjmon = null;
				sjday = null;
				sjhour = null;
				sjmin = null;
				sjss = null;
				if (null != map.get("xxshsj")) {
					String sj1 = map.get("xxshsj").toString();
					sjyear = sj1.substring(0, 4);
					sjmon = sj1.substring(4, 6);
					sjday = sj1.substring(6, 8);
					sjhour = sj1.substring(8, 10);
					sjmin = sj1.substring(10, 12);
					sjss = sj1.substring(12, 14);
					xxshsj = sjyear + "��" + sjmon + "��" + sjday + "�� " + sjhour
							+ ":" + sjmin + ":" + sjss;
					map.put("xxshsj", xxshsj);
				}
				// ����ʱ��
				String sj;
				String tjsj = null;
				if (null == map.get("tjsj")) {
					sj = "";
				} else {
					sj = map.get("tjsj").toString();
					sjyear = sj.substring(0, 4);
					sjmon = sj.substring(4, 6);
					sjday = sj.substring(6, 8);
					sjhour = sj.substring(8, 10);
					tjsj = sjyear + "��" + sjmon + "��" + sjday + "��" + sjhour
							+ "ʱ";
				}
				map.put("tjsj", tjsj);
			}
			// ת�������Ա����
			if ("1".equals(map.get("xbdm"))) {
				map.put("xbdm", "��");
			} else {
				map.put("xbdm", "Ů");
			}
			// ѧ�ƴ���ת��
			String xz = (map.get("xzdm")) + "��";
			map.put("xzdm", xz);
			// ת��ѧ������
			sql = "select xl from dmk_xl where xldm=?";
			String xldm = map.get("xldm");
			String xl = "";
			String[] xlinfo = dao.getOneRs(sql, new String[] { xldm },
					new String[] { "xl" });
			if (null != xlinfo) {
				xl = xlinfo[0];
			}
			map.put("xldm", xl);
			// ת��������ʽ����(��ר�����о���)
			String xslb = map.get("xslb");
			if ("ר����".equals(xslb) || "������".equals(xslb)) {
				sql = "select pyfs from dmk_bzpyfs where pyfsdm=?";
				String pyfsdm = map.get("pyfsdm");
				String pyfs = "";
				String[] pyfsinfo = dao.getOneRs(sql, new String[] { pyfsdm },
						new String[] { "pyfs" });
				if (null != pyfsinfo) {
					pyfs = pyfsinfo[0];
				}
				map.put("pyfsdm", pyfs);
			} else if ("�о���".equals(xslb)) {
				sql = "select pyfs from dmk_yjspyfs where pyfsdm=?";
				String pyfsdm = map.get("pyfsdm");
				String pyfs = "";
				String[] pyfsinfo = dao.getOneRs(sql, new String[] { pyfsdm },
						new String[] { "pyfs" });
				if (null != pyfsinfo) {
					pyfs = pyfsinfo[0];
				}
				map.put("pyfsdm", pyfs);
			}
			// ת����ҵȥ�����
			sql = "select byqx from dmk_byqx where byqxdm=?";
			String byqxdm = map.get("byqxdm");
			String byqx = "";
			String[] byqxinfo = dao.getOneRs(sql, new String[] { byqxdm },
					new String[] { "byqx" });
			if (null != byqxinfo) {
				byqx = byqxinfo[0];
			}
			map.put("byqxdm", byqx);
			// ת��������ò����
			sql = "select zzmm from dmk_zzmm where zzmmdm=?";
			String zzmmdm = map.get("zzmmdm");
			String zzmm = "";
			String[] zzmminfo = dao.getOneRs(sql, new String[] { zzmmdm },
					new String[] { "zzmm" });
			if (null != zzmminfo) {
				zzmm = zzmminfo[0];
			}
			map.put("zzmmdm", zzmm);
			// ת��δ��ҵ��־
			if ("0".equals(map.get("wjybz"))) {
				map.put("wjybz", "��ҵ");
			} else if ("1".equals(map.get("wjybz"))) {
				map.put("wjybz", "δ��ҵ");
			}
			// ת����ס֤�������־λ
			sql = "select JZZHLBBZW from dmk_JZZHLBBZW where jzzhlbbzwdm=?";
			String jzzhlbbzwdm = map.get("jzzhlbbzwdm");
			String jzzhlbbzw = "";
			String[] jzzhlbbzwinfo = dao.getOneRs(sql,
					new String[] { jzzhlbbzwdm }, new String[] { "jzzhlbbzw" });
			if (null != jzzhlbbzwinfo) {
				jzzhlbbzw = jzzhlbbzwinfo[0];
			}
			map.put("jzzhlbbzwdm", jzzhlbbzw);
			// ת����λ���ʴ���
			sql = "select dwxz from dmk_dwxz where dwxzdm=?";
			String dwxzdm = map.get("dwxzdm");
			String dwxz = "";
			String[] dwxzinfo = dao.getOneRs(sql, new String[] { dwxzdm },
					new String[] { "dwxz" });
			if (null != dwxzinfo) {
				dwxz = dwxzinfo[0];
			}
			map.put("dwxzdm", dwxz);
			// ת����λ���ʴ���2
			sql = "select dwxz from dmk_dwxz2 where dwxzdm=?";
			String dwxzdm2 = map.get("dwxzdm2");
			String dwxz2 = "";
			String[] dwxz2info = dao.getOneRs(sql, new String[] { dwxzdm2 },
					new String[] { "dwxz" });
			if (null != dwxz2info) {
				dwxz2 = dwxz2info[0];
			}
			map.put("dwxzdm2", dwxz2);
			// ת��רҵ�Կڴ���
			if ("1".equals(map.get("zydk"))) {
				map.put("zydk", "��");
			} else if ("2".equals(map.get("zydk"))) {
				map.put("zydk", "��");
			}
			// ���ܵ�����־
			if ("1".equals(map.get("bgdabz"))) {
				map.put("bgdabz", "���浵��");
			} else if ("0".equals(map.get("bgdabz"))) {
				map.put("bgdabz", "�����浵��");
			}
			// ת��ΥԼ��͵�һ����ƽ������
			if (map.get("wyj") != "" && null != (map.get("wyj"))) {
				String wyj = map.get("wyj") + "Ԫ";
				map.put("wyj", wyj);
			}
			if (map.get("dynypjgz") != "" && null != (map.get("dynypjgz"))) {
				String dynypjgz = map.get("dynypjgz") + "Ԫ";
				map.put("dynypjgz", dynypjgz);
			}
		}
		if (!Base.isNull(map.get("wjdz"))
				&& !"".equalsIgnoreCase(map.get("wjdz"))) {
			request.setAttribute("youFj", "youFj");
		}
		request.setAttribute("rs", map);// �������ݼ�
		if (Globals.XXDM_ZGDZDX.equals(whichxx)) {
			// ת��ʡ����
			String sydqdm = map.get("sydqdm");
			sql = "select * from dmk_sydq where sydqdm=?";
			String[] rs1 = dao.getOneRs(sql, new String[] { sydqdm },
					new String[] { "sydq" });
			if (null != rs1) {
				map.put("sydqdm", rs1[0]);
			}
			// ת���д���
			String jgshi = map.get("jgshi");
			sql = "select qxmc from dmk_qx where qxdm=?";
			String[] rs2 = dao.getOneRs(sql, new String[] { jgshi },
					new String[] { "qxmc" });
			if (null != rs2) {
				map.put("jgshi", rs2[0]);
			}
			//
			// ת��ʡ����
			String sydqdm1 = map.get("dwszd1");
			sql = "select * from dmk_sydq where sydqdm=?";
			String[] rs3 = dao.getOneRs(sql, new String[] { sydqdm1 },
					new String[] { "sydq" });
			if (null != rs3) {
				map.put("dwszd1", rs3[0]);
			}
			// ת���д���
			String jgshi2 = map.get("jgshi2");
			sql = "select qxmc from dmk_qx where qxdm=?";
			String[] rs4 = dao.getOneRs(sql, new String[] { jgshi2 },
					new String[] { "qxmc" });
			if (null != rs4) {
				map.put("jgshi2", rs4[0]);
			}
			//
			// ת��ʡ����
			String dwszd3 = map.get("dwszd3");
			sql = "select * from dmk_sydq where sydqdm=?";
			String[] rs5 = dao.getOneRs(sql, new String[] { dwszd3 },
					new String[] { "sydq" });
			if (null != rs5) {
				map.put("dwszd3", rs5[0]);
			}
			// ת���д���
			String jgshi3 = map.get("jgshi3");
			sql = "select qxmc from dmk_qx where qxdm=?";
			String[] rs6 = dao.getOneRs(sql, new String[] { jgshi3 },
					new String[] { "qxmc" });
			if (null != rs6) {
				map.put("jgshi3", rs6[0]);
			}
			//
			if ("0".equals(map.get("jyzk"))) {
				map.put("jyzk", "��ҵ");
			} else if ("1".equals(map.get("jyzk"))) {
				map.put("jyzk", "δ��ҵ");
			}
			return mapping.findForward("successdz");
		} else {
			return mapping.findForward("success");
		}
	}

	// ��������Ա��˴��ڲ��������
	private ActionForward jyglJyxyFdysh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String pk = "xsxh";
		String sql = ""; // sql���
		DAO dao = DAO.getInstance();
		String realTable = "jygl_jyxy";
		String doType = request.getParameter("doType");
		String pkValue = request.getParameter("pkValue");
		HashMap<String, String> map = new HashMap<String, String>();
		String fdysh = DealString.toGBK(request.getParameter("fdysh"));
		String xxsh = DealString.toGBK(request.getParameter("xxsh"));
		String btgyy = DealString.toGBK(request.getParameter("btgyy"));
		String xsxh = request.getParameter("xsxh");
		String act = request.getParameter("act");
		HttpSession session = request.getSession();
		String fdyshren = session.getAttribute("userName").toString();

		if ("shenhe".equals(act)) {
			String fdyshsj = (dao
					.getOneRs(
							"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual", // ����Ա���ʱ��
							// //
							// ȡ�����ݿ���ʱ��
							new String[] {}, new String[] { "sdate" }))[0];
			if ("��ͨ����".equals(fdysh)) {
				btgyy = "";
			}
			if ("δ���".equals(fdysh) && "δ���".equals(xxsh)) {
				fdyshsj = "";
				fdyshren = "";
			}
			sql = "update " + realTable + " set fdysh='" + fdysh
					+ "' , btgyy='" + btgyy + "' ,fdyshsj='" + fdyshsj
					+ "' ,fdyshren='" + fdyshren + "' where xsxh='" + xsxh
					+ "'";

			boolean judge = false;
			judge = dao.runUpdate(sql, new String[] {});
			if (judge) {
				request.setAttribute("shenhe", "ok");
			} else {
				request.setAttribute("shenhe", "no");
			}

			sql = "select * from jygl_jyxy where xsxh=?";
			String[] colList = dao
					.getColumnName("select * from jygl_jyxy where 1=2"); // ������������
			String[] stuinfo = dao
					.getOneRs(sql, new String[] { xsxh }, colList);
			if (stuinfo != null) {
				for (int i = 0; i < colList.length; i++) {
					map.put(colList[i].toLowerCase(), stuinfo[i]); // ����¼ѭ������map��
				}
				fdyshsj = null;
				String sjyear = null;
				String sjmon = null;
				String sjday = null;
				String sjhour = null;
				if (null != map.get("fdyshsj")) {
					String sj = map.get("fdyshsj").toString();
					sjyear = sj.substring(0, 4);
					sjmon = sj.substring(4, 6);
					sjday = sj.substring(6, 8);
					sjhour = sj.substring(8, 10);
					fdyshsj = sjyear + "��" + sjmon + "��" + sjday + "��" + sjhour
							+ "ʱ";
					map.put("fdyshsj", fdyshsj);
				}
				// ����ʱ��
				String sj = map.get("tjsj").toString();
				sjyear = sj.substring(0, 4);
				sjmon = sj.substring(4, 6);
				sjday = sj.substring(6, 8);
				sjhour = sj.substring(8, 10);
				String tjsj = sjyear + "��" + sjmon + "��" + sjday + "��" + sjhour
						+ "ʱ";
				map.put("tjsj", tjsj);
				// //ת�������Ա����
				if ("1".equals(map.get("xbdm"))) {
					map.put("xbdm", "��");
				} else {
					map.put("xbdm", "Ů");
				}
				// ѧ�ƴ���ת��
				String xz = (map.get("xzdm")) + "��";
				map.put("xzdm", xz);
				// ת��ѧ������
				sql = "select xl from dmk_xl where xldm=?";
				String xldm = map.get("xldm");
				String xl = "";
				String[] xlinfo = dao.getOneRs(sql, new String[] { xldm },
						new String[] { "xl" });
				if (null != xlinfo) {
					xl = xlinfo[0];
				}
				map.put("xldm", xl);
				// ת��������ʽ����(��ר�����о���)
				String xslb = map.get("xslb");
				if ("ר����".equals(xslb) || "������".equals(xslb)) {
					sql = "select pyfs from dmk_bzpyfs where pyfsdm=?";
					String pyfsdm = map.get("pyfsdm");
					String pyfs = "";
					String[] pyfsinfo = dao.getOneRs(sql,
							new String[] { pyfsdm }, new String[] { "pyfs" });
					if (null != pyfsinfo) {
						pyfs = pyfsinfo[0];
					}
					map.put("pyfsdm", pyfs);
				} else if ("�о���".equals(xslb)) {
					sql = "select pyfs from dmk_yjspyfs where pyfsdm=?";
					String pyfsdm = map.get("pyfsdm");
					String pyfs = "";
					String[] pyfsinfo = dao.getOneRs(sql,
							new String[] { pyfsdm }, new String[] { "pyfs" });
					if (null != pyfsinfo) {
						pyfs = pyfsinfo[0];
					}
					map.put("pyfsdm", pyfs);
				}
				// ת����ҵȥ�����
				sql = "select byqx from dmk_byqx where byqxdm=?";
				String byqxdm = map.get("byqxdm");
				String byqx = "";
				String[] byqxinfo = dao.getOneRs(sql, new String[] { byqxdm },
						new String[] { "byqx" });
				if (null != byqxinfo) {
					byqx = byqxinfo[0];
				}
				map.put("byqxdm", byqx);
				// ת��������ò����
				sql = "select zzmm from dmk_zzmm where zzmmdm=?";
				String zzmmdm = map.get("zzmmdm");
				String zzmm = "";
				String[] zzmminfo = dao.getOneRs(sql, new String[] { zzmmdm },
						new String[] { "zzmm" });
				if (null != zzmminfo) {
					zzmm = zzmminfo[0];
				}
				map.put("zzmmdm", zzmm);
				// ת��δ��ҵ��־
				if ("0".equals(map.get("wjybz"))) {
					map.put("wjybz", "��ҵ");
				} else if ("1".equals(map.get("wjybz"))) {
					map.put("wjybz", "δ��ҵ");
				}
				// ת����ס֤�������־λ
				sql = "select JZZHLBBZW from dmk_JZZHLBBZW where jzzhlbbzwdm=?";
				String jzzhlbbzwdm = map.get("jzzhlbbzwdm");
				String jzzhlbbzw = "";
				String[] jzzhlbbzwinfo = dao.getOneRs(sql,
						new String[] { jzzhlbbzwdm },
						new String[] { "jzzhlbbzw" });
				if (null != jzzhlbbzwinfo) {
					jzzhlbbzw = jzzhlbbzwinfo[0];
				}
				map.put("jzzhlbbzwdm", jzzhlbbzw);
				// ת����λ���ʴ���
				sql = "select dwxz from dmk_dwxz where dwxzdm=?";
				String dwxzdm = map.get("dwxzdm");
				String dwxz = "";
				String[] dwxzinfo = dao.getOneRs(sql, new String[] { dwxzdm },
						new String[] { "dwxz" });
				if (null != dwxzinfo) {
					dwxz = dwxzinfo[0];
				}
				map.put("dwxzdm", dwxz);
				// ת����λ���ʴ���2
				sql = "select dwxz from dmk_dwxz2 where dwxzdm=?";
				String dwxzdm2 = map.get("dwxzdm2");
				String dwxz2 = "";
				String[] dwxz2info = dao.getOneRs(sql,
						new String[] { dwxzdm2 }, new String[] { "dwxz" });
				if (null != dwxz2info) {
					dwxz2 = dwxz2info[0];
				}
				map.put("dwxzdm2", dwxz2);
				// ת��רҵ�Կڴ���
				if ("1".equals(map.get("zydk"))) {
					map.put("zydk", "��");
				} else if ("2".equals(map.get("zydk"))) {
					map.put("zydk", "��");
				}
				// ���ܵ�����־
				if ("1".equals(map.get("bgdabz"))) {
					map.put("bgdabz", "���浵��");
				} else if ("0".equals(map.get("bgdabz"))) {
					map.put("bgdabz", "�����浵��");
				}
				// ת��ΥԼ��͵�һ����ƽ������
				if (map.get("wyj") != "" && null != (map.get("wyj"))) {
					String wyj = map.get("wyj") + "Ԫ";
					map.put("wyj", wyj);
				}
				if (map.get("dynypjgz") != "" && null != (map.get("dynypjgz"))) {
					String dynypjgz = map.get("dynypjgz") + "Ԫ";
					map.put("dynypjgz", dynypjgz);
				}
			}
		}

		if ((doType == null) || doType.equalsIgnoreCase("")) {
			// �����쳣
			return mapping.findForward("false");
		} else if ("shenhe".equalsIgnoreCase(doType)) {
			// ��ѯ����
			sql = "select * from " + realTable + " where " + pk + "='"
					+ pkValue + "'";
			String[] colList = dao
					.getColumnName("select * from jygl_jyxy where 1=2"); // ������������
			String[] stuinfo = dao.getOneRs(sql, new String[] {}, colList);
			if (stuinfo != null) {
				for (int i = 0; i < colList.length; i++) {
					map.put(colList[i].toLowerCase(), stuinfo[i]); // ����¼ѭ������map��
				}
				String fdyshsj = null;
				String sjyear = null;
				String sjmon = null;
				String sjday = null;
				String sjhour = null;
				if (null != map.get("fdyshsj")) {
					String sj = map.get("fdyshsj").toString();
					sjyear = sj.substring(0, 4);
					sjmon = sj.substring(4, 6);
					sjday = sj.substring(6, 8);
					sjhour = sj.substring(8, 10);
					fdyshsj = sjyear + "��" + sjmon + "��" + sjday + "��" + sjhour
							+ "ʱ";
					map.put("fdyshsj", fdyshsj);
				}
				// ����ʱ��
				String sj = map.get("tjsj").toString();
				sjyear = sj.substring(0, 4);
				sjmon = sj.substring(4, 6);
				sjday = sj.substring(6, 8);
				sjhour = sj.substring(8, 10);
				String tjsj = sjyear + "��" + sjmon + "��" + sjday + "��" + sjhour
						+ "ʱ";
				map.put("tjsj", tjsj);
				// //ת�������Ա����
				if ("1".equals(map.get("xbdm"))) {
					map.put("xbdm", "��");
				} else {
					map.put("xbdm", "Ů");
				}
				// ѧ�ƴ���ת��
				String xz = (map.get("xzdm")) + "��";
				map.put("xzdm", xz);
				// ת��ѧ������
				sql = "select xl from dmk_xl where xldm=?";
				String xldm = map.get("xldm");
				String xl = "";
				String[] xlinfo = dao.getOneRs(sql, new String[] { xldm },
						new String[] { "xl" });
				if (null != xlinfo) {
					xl = xlinfo[0];
				}
				map.put("xldm", xl);
				// ת��������ʽ����(��ר�����о���)
				String xslb = map.get("xslb");
				if ("ר����".equals(xslb) || "������".equals(xslb)) {
					sql = "select pyfs from dmk_bzpyfs where pyfsdm=?";
					String pyfsdm = map.get("pyfsdm");
					String pyfs = "";
					String[] pyfsinfo = dao.getOneRs(sql,
							new String[] { pyfsdm }, new String[] { "pyfs" });
					if (null != pyfsinfo) {
						pyfs = pyfsinfo[0];
					}
					map.put("pyfsdm", pyfs);
				} else if ("�о���".equals(xslb)) {
					sql = "select pyfs from dmk_yjspyfs where pyfsdm=?";
					String pyfsdm = map.get("pyfsdm");
					String pyfs = "";
					String[] pyfsinfo = dao.getOneRs(sql,
							new String[] { pyfsdm }, new String[] { "pyfs" });
					if (null != pyfsinfo) {
						pyfs = pyfsinfo[0];
					}
					map.put("pyfsdm", pyfs);
				}
				// ת����ҵȥ�����
				sql = "select byqx from dmk_byqx where byqxdm=?";
				String byqxdm = map.get("byqxdm");
				String byqx = "";
				String[] byqxinfo = dao.getOneRs(sql, new String[] { byqxdm },
						new String[] { "byqx" });
				if (null != byqxinfo) {
					byqx = byqxinfo[0];
				}
				map.put("byqxdm", byqx);
				// ת��������ò����
				sql = "select zzmm from dmk_zzmm where zzmmdm=?";
				String zzmmdm = map.get("zzmmdm");
				String zzmm = "";
				String[] zzmminfo = dao.getOneRs(sql, new String[] { zzmmdm },
						new String[] { "zzmm" });
				if (null != zzmminfo) {
					zzmm = zzmminfo[0];
				}
				map.put("zzmmdm", zzmm);
				// ת��δ��ҵ��־
				if ("0".equals(map.get("wjybz"))) {
					map.put("wjybz", "��ҵ");
				} else if ("1".equals(map.get("wjybz"))) {
					map.put("wjybz", "δ��ҵ");
				}
				// ת����ס֤�������־λ
				sql = "select JZZHLBBZW from dmk_JZZHLBBZW where jzzhlbbzwdm=?";
				String jzzhlbbzwdm = map.get("jzzhlbbzwdm");
				String jzzhlbbzw = "";
				String[] jzzhlbbzwinfo = dao.getOneRs(sql,
						new String[] { jzzhlbbzwdm },
						new String[] { "jzzhlbbzw" });
				if (null != jzzhlbbzwinfo) {
					jzzhlbbzw = jzzhlbbzwinfo[0];
				}
				map.put("jzzhlbbzwdm", jzzhlbbzw);
				// ת����λ���ʴ���
				sql = "select dwxz from dmk_dwxz where dwxzdm=?";
				String dwxzdm = map.get("dwxzdm");
				String dwxz = "";
				String[] dwxzinfo = dao.getOneRs(sql, new String[] { dwxzdm },
						new String[] { "dwxz" });
				if (null != dwxzinfo) {
					dwxz = dwxzinfo[0];
				}
				map.put("dwxzdm", dwxz);
				// ת����λ���ʴ���2
				sql = "select dwxz from dmk_dwxz2 where dwxzdm=?";
				String dwxzdm2 = map.get("dwxzdm2");
				String dwxz2 = "";
				String[] dwxz2info = dao.getOneRs(sql,
						new String[] { dwxzdm2 }, new String[] { "dwxz" });
				if (null != dwxz2info) {
					dwxz2 = dwxz2info[0];
				}
				map.put("dwxzdm2", dwxz2);
				// ת��רҵ�Կڴ���
				if ("1".equals(map.get("zydk"))) {
					map.put("zydk", "��");
				} else if ("2".equals(map.get("zydk"))) {
					map.put("zydk", "��");
				}
				// ���ܵ�����־
				if ("1".equals(map.get("bgdabz"))) {
					map.put("bgdabz", "���浵��");
				} else if ("0".equals(map.get("bgdabz"))) {
					map.put("bgdabz", "�����浵��");
				}
				// ת��ΥԼ��͵�һ����ƽ������
				if (map.get("wyj") != "" && null != (map.get("wyj"))) {
					String wyj = map.get("wyj") + "Ԫ";
					map.put("wyj", wyj);
				}
				if (map.get("dynypjgz") != "" && null != (map.get("dynypjgz"))) {
					String dynypjgz = map.get("dynypjgz") + "Ԫ";
					map.put("dynypjgz", dynypjgz);
				}
			}
		}
		request.setAttribute("rs", map);// �������ݼ�

		String xxdm = StandardOperation.getXxdm();
		if (Globals.XXDM_ZGDZDX.equals(xxdm)) {
			return mapping.findForward("successdz");
		} else {
			return mapping.findForward("success");
		}
	}

	// ����ѧУ��˴��ڲ��������
	private ActionForward jyglJyxyXxsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String pk = "xsxh";
		String sql = ""; // sql���
		DAO dao = DAO.getInstance();
		String realTable = "jygl_jyxy";
		String doType = request.getParameter("doType");
		String pkValue = request.getParameter("pkValue");
		HashMap<String, String> map = new HashMap<String, String>();
		String xxsh = DealString.toGBK(request.getParameter("xxsh"));
		String btgyy = DealString.toGBK(request.getParameter("btgyy"));
		String xsxh = request.getParameter("xsxh");
		String act = request.getParameter("act");
		String fdysh = DealString.toGBK(request.getParameter("fdysh"));
		HttpSession session = request.getSession();
		String xxshren = session.getAttribute("userName").toString();

		if ("shenhe".equals(act)) {
			String xxshsj = (dao
					.getOneRs(
							"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual", // ѧУ���ʱ��
							// //
							// ȡ�����ݿ���ʱ��
							new String[] {}, new String[] { "sdate" }))[0];
			if ("��ͨ����".equals(xxsh)) {
				btgyy = "";
			}
			if ("δ���".equals(xxsh)) {
				xxshren = "";
				xxshsj = "";
			}
			if ("δ���".equals(xxsh) && "��ͨ����".equals(fdysh)) {
				btgyy = "";
			}
			sql = "update " + realTable + " set xxsh='" + xxsh + "' , btgyy='"
					+ btgyy + "' ,xxshsj='" + xxshsj + "' ,xxshren='" + xxshren
					+ "' where xsxh='" + xsxh + "'";

			boolean judge = false;
			judge = dao.runUpdate(sql, new String[] {});
			if (judge) {
				request.setAttribute("shenhe", "ok");
			} else {
				request.setAttribute("shenhe", "no");
			}

			sql = "select * from jygl_jyxy where xsxh=?";
			String[] colList = dao
					.getColumnName("select * from jygl_jyxy where 1=2"); // ������������
			String[] stuinfo = dao
					.getOneRs(sql, new String[] { xsxh }, colList);
			if (stuinfo != null) {
				for (int i = 0; i < colList.length; i++) {
					map.put(colList[i].toLowerCase(), stuinfo[i]); // ����¼ѭ������map��
				}
				// ѧУ���ʱ��
				xxshsj = null;
				String sjyear = null;
				String sjmon = null;
				String sjday = null;
				String sjhour = null;
				if (null != map.get("xxshsj")) {
					String sj = map.get("xxshsj").toString();
					sjyear = sj.substring(0, 4);
					sjmon = sj.substring(4, 6);
					sjday = sj.substring(6, 8);
					sjhour = sj.substring(8, 10);
					xxshsj = sjyear + "��" + sjmon + "��" + sjday + "��" + sjhour
							+ "ʱ";
					map.put("xxshsj", xxshsj);
				}
				// ����Ա���ʱ��
				String fdyshsj = null;
				sjyear = null;
				sjmon = null;
				sjday = null;
				if (null != map.get("fdyshsj")) {
					String sj1 = map.get("fdyshsj").toString();
					sjyear = sj1.substring(0, 4);
					sjmon = sj1.substring(4, 6);
					sjday = sj1.substring(6, 8);
					sjhour = sj1.substring(8, 10);
					fdyshsj = sjyear + "��" + sjmon + "��" + sjday + "��" + sjhour
							+ "ʱ";
					map.put("fdyshsj", fdyshsj);
				}
				// ����ʱ��
				String sj;
				String tjsj = null;
				if (null != map.get("fdyshsj")) {
					sj = map.get("tjsj").toString();
					sjyear = sj.substring(0, 4);
					sjmon = sj.substring(4, 6);
					sjday = sj.substring(6, 8);
					sjhour = sj.substring(8, 10);
					tjsj = sjyear + "��" + sjmon + "��" + sjday + "��" + sjhour
							+ "ʱ";
				}
				map.put("tjsj", tjsj);
				// ת�������Ա����
				if ("1".equals(map.get("xbdm"))) {
					map.put("xbdm", "��");
				} else {
					map.put("xbdm", "Ů");
				}
				// ѧ�ƴ���ת��
				String xz = (map.get("xzdm")) + "��";
				map.put("xzdm", xz);
				// ת��ѧ������
				sql = "select xl from dmk_xl where xldm=?";
				String xldm = map.get("xldm");
				String xl = "";
				String[] xlinfo = dao.getOneRs(sql, new String[] { xldm },
						new String[] { "xl" });
				if (null != xlinfo) {
					xl = xlinfo[0];
				}
				map.put("xldm", xl);
				// ת��������ʽ����(��ר�����о���)
				String xslb = map.get("xslb");
				if ("ר����".equals(xslb) || "������".equals(xslb)) {
					sql = "select pyfs from dmk_bzpyfs where pyfsdm=?";
					String pyfsdm = map.get("pyfsdm");
					String pyfs = "";
					String[] pyfsinfo = dao.getOneRs(sql,
							new String[] { pyfsdm }, new String[] { "pyfs" });
					if (null != pyfsinfo) {
						pyfs = pyfsinfo[0];
					}
					map.put("pyfsdm", pyfs);
				} else if ("�о���".equals(xslb)) {
					sql = "select pyfs from dmk_yjspyfs where pyfsdm=?";
					String pyfsdm = map.get("pyfsdm");
					String pyfs = "";
					String[] pyfsinfo = dao.getOneRs(sql,
							new String[] { pyfsdm }, new String[] { "pyfs" });
					if (null != pyfsinfo) {
						pyfs = pyfsinfo[0];
					}
					map.put("pyfsdm", pyfs);
				}
				// ת����ҵȥ�����
				sql = "select byqx from dmk_byqx where byqxdm=?";
				String byqxdm = map.get("byqxdm");
				String byqx = "";
				String[] byqxinfo = dao.getOneRs(sql, new String[] { byqxdm },
						new String[] { "byqx" });
				if (null != byqxinfo) {
					byqx = byqxinfo[0];
				}
				map.put("byqxdm", byqx);
				// ת��������ò����
				sql = "select zzmm from dmk_zzmm where zzmmdm=?";
				String zzmmdm = map.get("zzmmdm");
				String zzmm = "";
				String[] zzmminfo = dao.getOneRs(sql, new String[] { zzmmdm },
						new String[] { "zzmm" });
				if (null != zzmminfo) {
					zzmm = zzmminfo[0];
				}
				map.put("zzmmdm", zzmm);
				// ת��δ��ҵ��־
				if ("0".equals(map.get("wjybz"))) {
					map.put("wjybz", "��ҵ");
				} else if ("1".equals(map.get("wjybz"))) {
					map.put("wjybz", "δ��ҵ");
				}
				// ת����ס֤�������־λ
				sql = "select JZZHLBBZW from dmk_JZZHLBBZW where jzzhlbbzwdm=?";
				String jzzhlbbzwdm = map.get("jzzhlbbzwdm");
				String jzzhlbbzw = "";
				String[] jzzhlbbzwinfo = dao.getOneRs(sql,
						new String[] { jzzhlbbzwdm },
						new String[] { "jzzhlbbzw" });
				if (null != jzzhlbbzwinfo) {
					jzzhlbbzw = jzzhlbbzwinfo[0];
				}
				map.put("jzzhlbbzwdm", jzzhlbbzw);
				// ת����λ���ʴ���
				sql = "select dwxz from dmk_dwxz where dwxzdm=?";
				String dwxzdm = map.get("dwxzdm");
				String dwxz = "";
				String[] dwxzinfo = dao.getOneRs(sql, new String[] { dwxzdm },
						new String[] { "dwxz" });
				if (null != dwxzinfo) {
					dwxz = dwxzinfo[0];
				}
				map.put("dwxzdm", dwxz);
				// ת����λ���ʴ���2
				sql = "select dwxz from dmk_dwxz2 where dwxzdm=?";
				String dwxzdm2 = map.get("dwxzdm2");
				String dwxz2 = "";
				String[] dwxz2info = dao.getOneRs(sql,
						new String[] { dwxzdm2 }, new String[] { "dwxz" });
				if (null != dwxz2info) {
					dwxz2 = dwxz2info[0];
				}
				map.put("dwxzdm2", dwxz2);
				// ת��רҵ�Կڴ���
				if ("1".equals(map.get("zydk"))) {
					map.put("zydk", "��");
				} else if ("2".equals(map.get("zydk"))) {
					map.put("zydk", "��");
				}
				// ���ܵ�����־
				if ("1".equals(map.get("bgdabz"))) {
					map.put("bgdabz", "���浵��");
				} else if ("0".equals(map.get("bgdabz"))) {
					map.put("bgdabz", "�����浵��");
				}
				// ת��ΥԼ��͵�һ����ƽ������
				if (map.get("wyj") != "" && null != (map.get("wyj"))) {
					String wyj = map.get("wyj") + "Ԫ";
					map.put("wyj", wyj);
				}
				if (map.get("dynypjgz") != "" && null != (map.get("dynypjgz"))) {
					String dynypjgz = map.get("dynypjgz") + "Ԫ";
					map.put("dynypjgz", dynypjgz);
				}
			}
		}

		if ((doType == null) || doType.equalsIgnoreCase("")) {
			// �����쳣
			return mapping.findForward("false");
		} else if ("shenhe".equalsIgnoreCase(doType)) {
			// ��ѯ����
			sql = "select * from " + realTable + " where " + pk + "='"
					+ pkValue + "'";
			String[] colList = dao
					.getColumnName("select * from jygl_jyxy where 1=2"); // ������������
			String[] stuinfo = dao.getOneRs(sql, new String[] {}, colList);
			if (stuinfo != null) {
				for (int i = 0; i < colList.length; i++) {
					map.put(colList[i].toLowerCase(), stuinfo[i]); // ����¼ѭ������map��
				}

				String xxshsj = null;
				String sjyear = null;
				String sjmon = null;
				String sjday = null;
				String sjhour = null;
				if (null != map.get("xxshsj")) {
					String sj = map.get("xxshsj").toString();
					sjyear = sj.substring(0, 4);
					sjmon = sj.substring(4, 6);
					sjday = sj.substring(6, 8);
					sjhour = sj.substring(8, 10);
					xxshsj = sjyear + "��" + sjmon + "��" + sjday + "��" + sjhour
							+ "ʱ";
					map.put("xxshsj", xxshsj);
				}
				// ����Ա���ʱ��
				String fdyshsj = null;
				sjyear = null;
				sjmon = null;
				sjday = null;
				if (null != map.get("fdyshsj")) {
					String sj1 = map.get("fdyshsj").toString();
					sjyear = sj1.substring(0, 4);
					sjmon = sj1.substring(4, 6);
					sjday = sj1.substring(6, 8);
					sjhour = sj1.substring(8, 10);
					fdyshsj = sjyear + "��" + sjmon + "��" + sjday + "��" + sjhour
							+ "ʱ";
					map.put("fdyshsj", fdyshsj);
				}
				// ����ʱ��
				String sj;
				String tjsj = null;
				if (null != map.get("tjsj")) {
					sj = map.get("tjsj").toString();
					sjyear = sj.substring(0, 4);
					sjmon = sj.substring(4, 6);
					sjday = sj.substring(6, 8);
					sjhour = sj.substring(8, 10);
					tjsj = sjyear + "��" + sjmon + "��" + sjday + "��" + sjhour
							+ "ʱ";
				}
				map.put("tjsj", tjsj);
				// //ת�������Ա����
				if ("1".equals(map.get("xbdm"))) {
					map.put("xbdm", "��");
				} else {
					map.put("xbdm", "Ů");
				}
				// ѧ�ƴ���ת��
				String xz = (map.get("xzdm")) + "��";
				map.put("xzdm", xz);
				// ת��ѧ������
				sql = "select xl from dmk_xl where xldm=?";
				String xldm = map.get("xldm");
				String xl = "";
				String[] xlinfo = dao.getOneRs(sql, new String[] { xldm },
						new String[] { "xl" });
				if (null != xlinfo) {
					xl = xlinfo[0];
				}
				map.put("xldm", xl);
				// ת��������ʽ����(��ר�����о���)
				String xslb = map.get("xslb");
				if ("ר����".equals(xslb) || "������".equals(xslb)) {
					sql = "select pyfs from dmk_bzpyfs where pyfsdm=?";
					String pyfsdm = map.get("pyfsdm");
					String pyfs = "";
					String[] pyfsinfo = dao.getOneRs(sql,
							new String[] { pyfsdm }, new String[] { "pyfs" });
					if (null != pyfsinfo) {
						pyfs = pyfsinfo[0];
					}
					map.put("pyfsdm", pyfs);
				} else if ("�о���".equals(xslb)) {
					sql = "select pyfs from dmk_yjspyfs where pyfsdm=?";
					String pyfsdm = map.get("pyfsdm");
					String pyfs = "";
					String[] pyfsinfo = dao.getOneRs(sql,
							new String[] { pyfsdm }, new String[] { "pyfs" });
					if (null != pyfsinfo) {
						pyfs = pyfsinfo[0];
					}
					map.put("pyfsdm", pyfs);
				}
				// ת����ҵȥ�����
				sql = "select byqx from dmk_byqx where byqxdm=?";
				String byqxdm = map.get("byqxdm");
				String byqx = "";
				String[] byqxinfo = dao.getOneRs(sql, new String[] { byqxdm },
						new String[] { "byqx" });
				if (null != byqxinfo) {
					byqx = byqxinfo[0];
				}
				map.put("byqxdm", byqx);
				// ת��������ò����
				sql = "select zzmm from dmk_zzmm where zzmmdm=?";
				String zzmmdm = map.get("zzmmdm");
				String zzmm = "";
				String[] zzmminfo = dao.getOneRs(sql, new String[] { zzmmdm },
						new String[] { "zzmm" });
				if (null != zzmminfo) {
					zzmm = zzmminfo[0];
				}
				map.put("zzmmdm", zzmm);
				// ת��δ��ҵ��־
				if ("0".equals(map.get("wjybz"))) {
					map.put("wjybz", "��ҵ");
				} else if ("1".equals(map.get("wjybz"))) {
					map.put("wjybz", "δ��ҵ");
				}
				// ת����ס֤�������־λ
				sql = "select JZZHLBBZW from dmk_JZZHLBBZW where jzzhlbbzwdm=?";
				String jzzhlbbzwdm = map.get("jzzhlbbzwdm");
				String jzzhlbbzw = "";
				String[] jzzhlbbzwinfo = dao.getOneRs(sql,
						new String[] { jzzhlbbzwdm },
						new String[] { "jzzhlbbzw" });
				if (null != jzzhlbbzwinfo) {
					jzzhlbbzw = jzzhlbbzwinfo[0];
				}
				map.put("jzzhlbbzwdm", jzzhlbbzw);
				// ת����λ���ʴ���
				sql = "select dwxz from dmk_dwxz where dwxzdm=?";
				String dwxzdm = map.get("dwxzdm");
				String dwxz = "";
				String[] dwxzinfo = dao.getOneRs(sql, new String[] { dwxzdm },
						new String[] { "dwxz" });
				if (null != dwxzinfo) {
					dwxz = dwxzinfo[0];
				}
				map.put("dwxzdm", dwxz);
				// ת����λ���ʴ���2
				sql = "select dwxz from dmk_dwxz2 where dwxzdm=?";
				String dwxzdm2 = map.get("dwxzdm2");
				String dwxz2 = "";
				String[] dwxz2info = dao.getOneRs(sql,
						new String[] { dwxzdm2 }, new String[] { "dwxz" });
				if (null != dwxz2info) {
					dwxz2 = dwxz2info[0];
				}
				map.put("dwxzdm2", dwxz2);
				// ת��רҵ�Կڴ���
				if ("1".equals(map.get("zydk"))) {
					map.put("zydk", "��");
				} else if ("2".equals(map.get("zydk"))) {
					map.put("zydk", "��");
				}
				// ���ܵ�����־
				if ("1".equals(map.get("bgdabz"))) {
					map.put("bgdabz", "���浵��");
				} else if ("0".equals(map.get("bgdabz"))) {
					map.put("bgdabz", "�����浵��");
				}
				// ת��ΥԼ��͵�һ����ƽ������
				if (map.get("wyj") != "" && null != (map.get("wyj"))) {
					String wyj = map.get("wyj") + "Ԫ";
					map.put("wyj", wyj);
				}
				if (map.get("dynypjgz") != "" && null != (map.get("dynypjgz"))) {
					String dynypjgz = map.get("dynypjgz") + "Ԫ";
					map.put("dynypjgz", dynypjgz);
				}
			}
		}
		request.setAttribute("rs", map);// �������ݼ�
		String xxdm = Base.xxdm;
		System.out.println(map.get("wjdz"));
		if (xxdm.equals(Globals.XXDM_NGSXY)
				&& !"".equalsIgnoreCase(map.get("wjdz"))
				&& map.get("wjdz") != null) {
			request.setAttribute("youFj", "youFj");
		}

		if (Globals.XXDM_ZGDZDX.equals(StandardOperation.getXxdm())) {
			if ("0".equals(map.get("jyzk"))) {
				map.put("jyzk", "��ҵ");
			} else if ("1".equals(map.get("jyzk"))) {
				map.put("jyzk", "δ��ҵ");
			}
			// ת��ʡ����
			String sydqdm = map.get("sydqdm");
			sql = "select * from dmk_sydq where sydqdm=?";
			String[] rs1 = dao.getOneRs(sql, new String[] { sydqdm },
					new String[] { "sydq" });
			if (null != rs1) {
				map.put("sydqdm", rs1[0]);
			}
			// ת���д���
			String jgshi = map.get("jgshi");
			sql = "select qxmc from dmk_qx where qxdm=?";
			String[] rs2 = dao.getOneRs(sql, new String[] { jgshi },
					new String[] { "qxmc" });
			if (null != rs2) {
				map.put("jgshi", rs2[0]);
			}
			//
			// ת��ʡ����
			String sydqdm1 = map.get("dwszd1");
			sql = "select * from dmk_sydq where sydqdm=?";
			String[] rs3 = dao.getOneRs(sql, new String[] { sydqdm1 },
					new String[] { "sydq" });
			if (null != rs3) {
				map.put("dwszd1", rs3[0]);
			}
			// ת���д���
			String jgshi2 = map.get("jgshi2");
			sql = "select qxmc from dmk_qx where qxdm=?";
			String[] rs4 = dao.getOneRs(sql, new String[] { jgshi2 },
					new String[] { "qxmc" });
			if (null != rs4) {
				map.put("jgshi2", rs4[0]);
			}
			//
			// ת��ʡ����
			String dwszd3 = map.get("dwszd3");
			sql = "select * from dmk_sydq where sydqdm=?";
			String[] rs5 = dao.getOneRs(sql, new String[] { dwszd3 },
					new String[] { "sydq" });
			if (null != rs5) {
				map.put("dwszd3", rs5[0]);
			}
			// ת���д���
			String jgshi3 = map.get("jgshi3");
			sql = "select qxmc from dmk_qx where qxdm=?";
			String[] rs6 = dao.getOneRs(sql, new String[] { jgshi3 },
					new String[] { "qxmc" });
			if (null != rs6) {
				map.put("jgshi3", rs6[0]);
			}

			return mapping.findForward("successdz");
		} else {
			return mapping.findForward("success");
		}
	}

	// ��ҵЭ�����
	private ActionForward jyglJyxyUpdate(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String pk = "xsxh";
		String sql = ""; // sql���
		DAO dao = DAO.getInstance();
		String realTable = "jygl_jyxy";
		String doType = request.getParameter("doType");
		String doType2 = request.getParameter("doType2");
		String pkValue = request.getParameter("pkValue");
		HashMap<String, String> map = new HashMap<String, String>();
		String xsxh = request.getParameter("xsxh");
		String act = request.getParameter("act");
		String whichArea = request.getParameter("whichArea");
		map.put("whichArea", whichArea);
		String whichxx = StandardOperation.getXxdm();

		if (whichxx.equalsIgnoreCase(Globals.XXDM_YNYS)) {// ��������

			sql = "select dwxzdm,dwxz from dmk_dwxz2"; // ��λ���ʴ���2
			List dwxzdm2List = dao.getList(sql, new String[] {}, new String[] {
					"dwxzdm", "dwxz" });
			request.setAttribute("dwxzdm2List", dwxzdm2List);

			String xh = DealString.toGBK(request.getParameter("xh"));

			if ("".equals(xh) || null == xh) {
				xh = pkValue;
			}

			if ("update".equalsIgnoreCase(doType)) {

				// ѧ��������Ϣ
				String jtdz = DealString.toGBK(request.getParameter("jtdz")); // ��ͥ��ַ
				String lxdh = DealString.toGBK(request.getParameter("lxdh")); // ��ϵ�绰
				// ��λ������Ϣ
				String dwmc = DealString.toGBK(request.getParameter("dwmc")); // ��λ����
				String dwls = DealString.toGBK(request.getParameter("dwls")); // ��λ����
				String dwlxr = DealString.toGBK(request.getParameter("dwlxr")); // ��λ��ϵ��
				String dwlxdh = DealString
						.toGBK(request.getParameter("dwlxdh")); // ��λ��ϵ�绰
				String dwyb = DealString.toGBK(request.getParameter("dwyb")); // ��λ�ʱ�
				String dwdz = DealString.toGBK(request.getParameter("dwdz")); // ��λ��ַ
				String dwxz = DealString.toGBK(request.getParameter("dwxz")); // ��λ����
				String hyfl = DealString.toGBK(request.getParameter("hyfl")); // ��ҵ����
				String dayjdz = DealString
						.toGBK(request.getParameter("dayjdz")); // �����ʼĵ�ַ
				String fbsj = (dao
						.getOneRs(
								"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual",
								new String[] {}, new String[] { "sdate" }))[0]; // ����ʱ��

				boolean judge = false;
				judge = StandardOperation.update("ynys_jygl_jyxy",
						new String[] { "jtdz", "lxdh", "dwmc", "dwls", "dwlxr",
								"dwlxdh", "dwyb", "dwdz", "dwxz", "hyfl",
								"dayjdz", "fbsj" }, new String[] { jtdz, lxdh,
								dwmc, dwls, dwlxr, dwlxdh, dwyb, dwdz, dwxz,
								hyfl, dayjdz, fbsj }, "xh", xh, request);

				if (judge) {
					request.setAttribute("update", "ok");
				} else {
					request.setAttribute("update", "no");
				}
			}

			sql = "select * from ynys_jygl_jyxy where xh=?"; // ��ѯ��ѧ�ŵ�������ݵ�sql���
			String[] colList = dao
					.getColumnName("select * from ynys_jygl_jyxy where 1=2"); // ������������
			String[] rs = dao.getOneRs(sql, new String[] { xh }, colList); // ��ôӱ�ҵ��������Ϣ����ͼ���в�ѯ�ļ�¼��
			if (rs != null) {
				for (int i = 0; i < colList.length; i++) {
					map.put(colList[i].toLowerCase(), rs[i]); // ����¼ѭ������map��
				}
			}

			request.setAttribute("rs", map);// �������ݼ�
			return mapping.findForward("success2");
		}

		if ("shenhe".equals(whichArea)) {
			request.setAttribute("whichArea", "shenhe");
		} else if ("result".equals(whichArea)) {
			request.setAttribute("whichArea", "result");
		}

		sql = "select zzmmdm,zzmm from dmk_zzmm"; // ������ò����
		List zzmmdmList = dao.getList(sql, new String[] {}, new String[] {
				"zzmmdm", "zzmm" });
		request.setAttribute("zzmmdmList", zzmmdmList);

		sql = "select byqxdm,byqx from dmk_byqx"; // ��ҵȥ�����
		List byqxdmList = dao.getList(sql, new String[] {}, new String[] {
				"byqxdm", "byqx" });
		request.setAttribute("byqxdmList", byqxdmList);

		sql = "select dwdqdm,dwdq from dmk_dwdq"; // ��λ����
		List dwdqList = dao.getList(sql, new String[] {}, new String[] {
				"dwdqdm", "dwdq" });
		request.setAttribute("dwdqList", dwdqList);

		sql = "select zgbmdm,zgbm from dmk_zgbm"; // ���ܲ���
		List zgbmList = dao.getList(sql, new String[] {}, new String[] {
				"zgbmdm", "zgbm" });
		request.setAttribute("zgbmList", zgbmList);

		sql = "select jzzhlbbzwdm,jzzhlbbzw from dmk_jzzhlbbzw"; // ��ס֤�������־λ
		List jzzhlbbzwdmList = dao.getList(sql, new String[] {}, new String[] {
				"jzzhlbbzwdm", "jzzhlbbzw" });
		request.setAttribute("jzzhlbbzwdmList", jzzhlbbzwdmList);

		sql = "select sydzgbmdm,sydzgbm from dmk_sydzgbm"; // ��Դ�����ܵ�λ����
		List sydzgbmList = dao.getList(sql, new String[] {}, new String[] {
				"sydzgbmdm", "sydzgbm" });
		request.setAttribute("sydzgbmList", sydzgbmList);

		sql = "select dwxzdm,dwxz from dmk_dwxz"; // ��λ���ʴ���
		List dwxzdmList = dao.getList(sql, new String[] {}, new String[] {
				"dwxzdm", "dwxz" });
		request.setAttribute("dwxzdmList", dwxzdmList);

		sql = "select dwxzdm,dwxz from dmk_dwxz2"; // ��λ���ʴ���2
		List dwxzdm2List = dao.getList(sql, new String[] {}, new String[] {
				"dwxzdm", "dwxz" });
		request.setAttribute("dwxzdm2List", dwxzdm2List);

		sql = "select dqlxdm,dqlx from dmk_dqlx"; // ��������
		List dqlxdmList = dao.getList(sql, new String[] {}, new String[] {
				"dqlxdm", "dqlx" });
		request.setAttribute("dqlxdmList", dqlxdmList);

		sql = "select hyfldm,hyfl from dmk_hyfl"; // ��ҵ����
		List hyflList = dao.getList(sql, new String[] {}, new String[] {
				"hyfldm", "hyfl" });
		request.setAttribute("hyflList", hyflList);

		if ("update".equals(act)) {
			xsxh = DealString.toGBK(request.getParameter("xsxh")); // ѧ��
			String memo = DealString.toGBK(request.getParameter("memo")); // 13��ע
			String zzmmdm = request.getParameter("zzmmdm"); // 14������ò����
			String xxdjh = request.getParameter("xxdjh"); // 16��Ϣ�ǼǺ�
			String zzjgdm = request.getParameter("zzjgdm"); // 17��֯��������
			String dwmc = DealString.toGBK(request.getParameter("dwmc")); // 18��λ����
			String dwdqdm = request.getParameter("dwdqdm"); // 19��λ��������
			String dwdq = DealString.toGBK(request.getParameter("dwdq")); // 20��λ��������
			String zgbm = DealString.toGBK(request.getParameter("zgbm")); // 21���ܲ�������
			String byqxdm = request.getParameter("byqxdm"); // 22��ҵȥ�����
			String blueno = request.getParameter("blueno"); // 23������׼�ĺ�
			String wjybz = DealString.toGBK(request.getParameter("wjybz")); // 24δ��ҵ��־
			String wjyyy = DealString.toGBK(request.getParameter("wjyyy")); // 25δ��ҵԭ��
			String lxdz = DealString.toGBK(request.getParameter("lxdz")); // 26��ϵ��ַ
			String yb = request.getParameter("yb"); // 27�ʱ�
			String dh = request.getParameter("dh"); // 28�绰
			String bgdabz = request.getParameter("bgdabz"); // 29���ܵ�����־
			String bz1 = DealString.toGBK(request.getParameter("bz1")); // 30�Զ���1
			String bz2 = DealString.toGBK(request.getParameter("bz2")); // 31�Զ���2
			String bz3 = DealString.toGBK(request.getParameter("bz3")); // 32�Զ���3
			String bz4 = DealString.toGBK(request.getParameter("bz4")); // 33�Զ���4
			String bz5 = DealString.toGBK(request.getParameter("bz5")); // 34�Զ���5
			String jzzhlbbzwdm = request.getParameter("jzzhlbbzwdm"); // 35
			// ��ס֤�������־λ
			String sydzgbm = DealString.toGBK(request.getParameter("sydzgbm")); // 36��Դ�����ܵ�λ
			String dwxzdm = request.getParameter("dwxzdm"); // 37��λ���ʴ���
			String zgbmdm = request.getParameter("zgbmdm"); // 38���ܲ��Ŵ���
			String dwxzdm2 = request.getParameter("dwxzdm2"); // 39��λ���ʴ���2
			String dwdz = DealString.toGBK(request.getParameter("dwdz")); // 40��λ��ַ
			String dwdh = request.getParameter("dwdh"); // 41��λ�绰
			String dwyb = request.getParameter("dwyb"); // 42��λ�ʱ�
			String dajsd = DealString.toGBK(request.getParameter("dajsd")); // 43�������ܵ�
			String dajsddz = DealString.toGBK(request.getParameter("dajsddz")); // 44�������ܵص�ַ
			String dajsdyb = request.getParameter("dajsdyb"); // 45�������ܵ��ʱ�
			String dynypjgz = request.getParameter("dynypjgz"); // 46��һ����ƽ������
			String wyj = request.getParameter("wyj"); // 47ΥԼ��
			String dqlx = DealString.toGBK(request.getParameter("dqlx")); // 48��������
			String hyfl = DealString.toGBK(request.getParameter("hyfl")); // 49��ҵ����
			String zydk = request.getParameter("zydk"); // 50רҵ�Կ�

			String fdysh = DealString.toGBK(request.getParameter("fdysh"));// ����Ա���
			String xxsh = DealString.toGBK(request.getParameter("xxsh"));// ѧУ���
			String xysbh = DealString.toGBK(request.getParameter("xysbh"));
			String dwlxr = request.getParameter("dwlxr");
			String dajsddwmc = request.getParameter("dwlxr");
			String dwgm = request.getParameter("dwlxr");
			String dwzczj = request.getParameter("dwlxr");

			// ��ͨ���� δͨ��X
			if ("δͨ��X".equals(fdysh)) {
				fdysh = "δ���";
			}
			if ("δͨ��X".equals(xxsh)) {
				xxsh = "δ���";
			}

			boolean judge = false;
			if (Globals.XXDM_ZGDZDX.equals(whichxx)) {
				String jgshi = DealString.toGBK(request.getParameter("jgshi"));
				String zzmm = DealString.toGBK(request.getParameter("zzmm"));
				String mz = DealString.toGBK(request.getParameter("mz"));
				String zymc = DealString.toGBK(request.getParameter("zymc"));
				String qq = DealString.toGBK(request.getParameter("qq"));
				// String xb = DealString.toGBK(request.getParameter("xb"));
				String wpdw = DealString.toGBK(request.getParameter("wpdw"));
				String zsmc = DealString.toGBK(request.getParameter("zsmc"));
				String zdcl = DealString.toGBK(request.getParameter("zdcl"));
				String bdzbz = DealString.toGBK(request.getParameter("bdzbz"));
				String sjdw = DealString.toGBK(request.getParameter("sjdw"));
				String pqdw = DealString.toGBK(request.getParameter("pqdw"));
				String pqdwid = DealString
						.toGBK(request.getParameter("pqdwid"));
				String dwxz1 = DealString.toGBK(request.getParameter("dwxz1"));
				String dwxz2 = DealString.toGBK(request.getParameter("dwxz2"));
				String zgbm1 = DealString.toGBK(request.getParameter("zgbm1"));
				String zgbm2 = DealString.toGBK(request.getParameter("zgbm2"));
				String zgbmmc = DealString
						.toGBK(request.getParameter("zgbmmc"));
				String dwszd2 = DealString
						.toGBK(request.getParameter("dwszd2"));
				String dwszd3 = DealString
						.toGBK(request.getParameter("dwszd3"));
				String dwszd1 = DealString
						.toGBK(request.getParameter("dwszd1"));
				String sjszd1 = DealString
						.toGBK(request.getParameter("sjszd1"));
				String sjszd2 = DealString
						.toGBK(request.getParameter("sjszd2"));
				String sjszd3 = DealString
						.toGBK(request.getParameter("sjszd3"));
				String hkdz = DealString.toGBK(request.getParameter("hkdz"));
				String gprq = DealString.toGBK(request.getParameter("gprq"));
				String gpyy = DealString.toGBK(request.getParameter("gpyy"));
				String gpcs = DealString.toGBK(request.getParameter("gpcs"));
				String ydwmc = DealString.toGBK(request.getParameter("ydwmc"));
				String bdzbh = DealString.toGBK(request.getParameter("bdzbh"));
				String jyzk = DealString.toGBK(request.getParameter("jyzk"));
				String jgshi2 = DealString
						.toGBK(request.getParameter("jgshi2"));
				String jgshi3 = DealString
						.toGBK(request.getParameter("jgshi3"));
				judge = StandardOperation.update(realTable, new String[] {
						"dwxzdm2", "dwdq", "byqxdm", "dajsd", "dajsdyb",
						"jgshi", "zzmm", "mz", "jgshi2", "jgshi3", "zymc",
						"qq", "xysbh", "xsxh", "wpdw", "zsmc", "zdcl", "bdzbz",
						"sjdw", "pqdw", "pqdwid", "dwxz1", "dwxz2", "zgbm1",
						"zgbm2", "zgbmmc", "dwszd1", "dwszd2", "dwszd3",
						"sjszd1", "sjszd2", "sjszd3", "hkdz", "gprq", "gpyy",
						"gpcs", "ydwmc", "bdzbh", "wjybz", "wjyyy", "jyzk" },
						new String[] { dwxzdm2, dwdq, byqxdm, dajsd, dajsdyb,
								jgshi, zzmm, mz, jgshi2, jgshi3, zymc, qq,
								xysbh, xsxh, wpdw, zsmc, zdcl, bdzbz, sjdw,
								pqdw, pqdwid, dwxz1, dwxz2, zgbm1, zgbm2,
								zgbmmc, dwszd1, dwszd2, dwszd3, sjszd1, sjszd2,
								sjszd3, hkdz, gprq, gpyy, gpcs, ydwmc, bdzbh,
								wjybz, wjyyy, jyzk }, "xsxh", xsxh, request);
			} else {
				judge = StandardOperation.update(realTable, new String[] {
						"memo", "zzmmdm", "xxdjh", "zzjgdm", "dwmc", "dwdqdm",
						"dwdq", "zgbm", "byqxdm", "blueno", "wjybz", "wjyyy",
						"lxdz", "yb", "dh", "bgdabz", "bz1", "bz2", "bz3",
						"bz4", "bz5", "jzzhlbbzwdm", "sydzgbm", "dwxzdm",
						"zgbmdm", "dwxzdm2", "dwdz", "dwdh", "dwyb", "dajsd",
						"dajsddz", "dajsdyb", "dynypjgz", "wyj", "dqlx",
						"hyfl", "zydk", "fdysh", "xxsh", "xysbh", "dwlxr",
						"dajsddwmc", "dwgm", "dwzczj" }, new String[] { memo,
						zzmmdm, xxdjh, zzjgdm, dwmc, dwdqdm, dwdq, zgbm,
						byqxdm, blueno, wjybz, wjyyy, lxdz, yb, dh, bgdabz,
						bz1, bz2, bz3, bz4, bz5, jzzhlbbzwdm, sydzgbm, dwxzdm,
						zgbmdm, dwxzdm2, dwdz, dwdh, dwyb, dajsd, dajsddz,
						dajsdyb, dynypjgz, wyj, dqlx, hyfl, zydk, fdysh, xxsh,
						xysbh, dwlxr, dajsddwmc, dwgm, dwzczj }, "xsxh", xsxh,
						request);
			}
			if (judge) {
				request.setAttribute("update", "ok");

				// �޸Ĳ����ļ�¼
				HttpSession session = request.getSession();
				String userName = session.getAttribute("userName").toString();
				String whensj = (dao
						.getOneRs(
								"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual",
								new String[] {}, new String[] { "sdate" }))[0]; // �ύʱ��

				StandardOperation.insert("JYGL_USERCZMXB",
						new String[] { "userid", "dowhat", "whichtable",
								"whichpk", "whensj" }, new String[] { userName,
								"�޸�", "��ҵЭ���", "ѧ��:" + xsxh, whensj }, request);
			} else {
				request.setAttribute("update", "no");
			}

			sql = "select * from jygl_jyxy where xsxh=?";
			String[] colList = dao
					.getColumnName("select * from jygl_jyxy where 1=2"); // ������������
			String[] stuinfo = dao
					.getOneRs(sql, new String[] { xsxh }, colList);
			if (stuinfo != null) {
				for (int i = 0; i < colList.length; i++) {
					map.put(colList[i].toLowerCase(), stuinfo[i]); // ����¼ѭ������map��
				}
				// ����Ա���ʱ��
				String fdyshsj = null;
				String sjyear = null;
				String sjmon = null;
				String sjday = null;
				String sjhour = null;
				String sjmin = null;
				String sjss = null;
				if (null != map.get("fdyshsj")) {
					String sj = map.get("fdyshsj").toString();
					sjyear = sj.substring(0, 4);
					sjmon = sj.substring(4, 6);
					sjday = sj.substring(6, 8);
					sjhour = sj.substring(8, 10);
					sjmin = sj.substring(10, 12);
					sjss = sj.substring(12, 14);
					fdyshsj = sjyear + "��" + sjmon + "��" + sjday + "�� "
							+ sjhour + ":" + sjmin + ":" + sjss;
					map.put("fdyshsj", fdyshsj);
				}
				// ѧУ���ʱ��
				String xxshsj = null;
				sjyear = null;
				sjmon = null;
				sjday = null;
				sjhour = null;
				sjmin = null;
				sjss = null;
				if (null != map.get("xxshsj")) {
					String sj1 = map.get("xxshsj").toString();
					sjyear = sj1.substring(0, 4);
					sjmon = sj1.substring(4, 6);
					sjday = sj1.substring(6, 8);
					sjhour = sj1.substring(8, 10);
					sjmin = sj1.substring(10, 12);
					sjss = sj1.substring(12, 14);
					xxshsj = sjyear + "��" + sjmon + "��" + sjday + "�� " + sjhour
							+ ":" + sjmin + ":" + sjss;
					map.put("xxshsj", xxshsj);
				}
				// ����ʱ��
				String sj = map.get("tjsj").toString();
				sjyear = sj.substring(0, 4);
				sjmon = sj.substring(4, 6);
				sjday = sj.substring(6, 8);
				sjhour = sj.substring(8, 10);
				String tjsj = sjyear + "��" + sjmon + "��" + sjday + "��" + sjhour
						+ "ʱ";
				map.put("tjsj", tjsj);
				// ת�������Ա����
				if ("1".equals(map.get("xbdm"))) {
					map.put("xbdm", "��");
				} else {
					map.put("xbdm", "Ů");
				}
				// ѧ�ƴ���ת��
				String xz = (map.get("xzdm")) + "��";
				map.put("xzdm", xz);
				// ת��ѧ������
				sql = "select xl from dmk_xl where xldm=?";
				String xldm = map.get("xldm");
				String xl = "";
				String[] xlinfo = dao.getOneRs(sql, new String[] { xldm },
						new String[] { "xl" });
				if (null != xlinfo) {
					xl = xlinfo[0];
				}
				map.put("xldm", xl);
				// ת��������ʽ����(��ר�����о���)
				String xslb = map.get("xslb");
				if ("ר����".equals(xslb) || "������".equals(xslb)) {
					sql = "select pyfs from dmk_bzpyfs where pyfsdm=?";
					String pyfsdm = map.get("pyfsdm");
					String pyfs = "";
					String[] pyfsinfo = dao.getOneRs(sql,
							new String[] { pyfsdm }, new String[] { "pyfs" });
					if (null != pyfsinfo) {
						pyfs = pyfsinfo[0];
					}
					map.put("pyfsdm", pyfs);
				} else if ("�о���".equals(xslb)) {
					sql = "select pyfs from dmk_yjspyfs where pyfsdm=?";
					String pyfsdm = map.get("pyfsdm");
					String pyfs = "";
					String[] pyfsinfo = dao.getOneRs(sql,
							new String[] { pyfsdm }, new String[] { "pyfs" });
					if (null != pyfsinfo) {
						pyfs = pyfsinfo[0];
					}
					map.put("pyfsdm", pyfs);
				}
			}
			request.setAttribute("whichArea", "shenhe");
		}

		if ("first".equalsIgnoreCase(doType)) {
			// ��ѯ����
			sql = "select * from " + realTable + " where " + pk + "='"
					+ pkValue + "'";
			String[] colList = dao
					.getColumnName("select * from jygl_jyxy where 1=2"); // ������������
			String[] stuinfo = dao.getOneRs(sql, new String[] {}, colList);
			if (stuinfo != null) {
				for (int i = 0; i < colList.length; i++) {
					map.put(colList[i].toLowerCase(), stuinfo[i]); // ����¼ѭ������map��
				}
				// ����Ա���ʱ��
				String fdyshsj = null;
				String sjyear = null;
				String sjmon = null;
				String sjday = null;
				String sjhour = null;
				String sjmin = null;
				String sjss = null;
				if (null != map.get("fdyshsj")) {
					String sj = map.get("fdyshsj").toString();
					sjyear = sj.substring(0, 4);
					sjmon = sj.substring(4, 6);
					sjday = sj.substring(6, 8);
					sjhour = sj.substring(8, 10);
					sjmin = sj.substring(10, 12);
					sjss = sj.substring(12, 14);
					fdyshsj = sjyear + "��" + sjmon + "��" + sjday + "�� "
							+ sjhour + ":" + sjmin + ":" + sjss;
					map.put("fdyshsj", fdyshsj);
				}
				// ѧУ���ʱ��
				String xxshsj = null;
				sjyear = null;
				sjmon = null;
				sjday = null;
				sjhour = null;
				sjmin = null;
				sjss = null;
				if (null != map.get("xxshsj")) {
					String sj1 = map.get("xxshsj").toString();
					sjyear = sj1.substring(0, 4);
					sjmon = sj1.substring(4, 6);
					sjday = sj1.substring(6, 8);
					sjhour = sj1.substring(8, 10);
					sjmin = sj1.substring(10, 12);
					sjss = sj1.substring(12, 14);
					xxshsj = sjyear + "��" + sjmon + "��" + sjday + "�� " + sjhour
							+ ":" + sjmin + ":" + sjss;
					map.put("xxshsj", xxshsj);
				}
				// ����ʱ��
				String sj;
				String tjsj = null;
				if (null != map.get("tjsj")) {
					sj = map.get("tjsj").toString();
					sjyear = sj.substring(0, 4);
					sjmon = sj.substring(4, 6);
					sjday = sj.substring(6, 8);
					sjhour = sj.substring(8, 10);
					tjsj = sjyear + "��" + sjmon + "��" + sjday + "��" + sjhour
							+ "ʱ";
				}
				map.put("tjsj", tjsj);
				// ת�������Ա����
				if ("1".equals(map.get("xbdm"))) {
					map.put("xbdm", "��");
				} else {
					map.put("xbdm", "Ů");
				}
				// ѧ�ƴ���ת��
				String xz = (map.get("xzdm")) + "��";
				map.put("xzdm", xz);
				// ת��ѧ������
				sql = "select xl from dmk_xl where xldm=?";
				String xldm = map.get("xldm");
				String xl = "";
				String[] xlinfo = dao.getOneRs(sql, new String[] { xldm },
						new String[] { "xl" });
				if (null != xlinfo) {
					xl = xlinfo[0];
				}
				map.put("xldm", xl);
				// ת��������ʽ����(��ר�����о���)
				String xslb = map.get("xslb");
				if ("ר����".equals(xslb) || "������".equals(xslb)) {
					sql = "select pyfs from dmk_bzpyfs where pyfsdm=?";
					String pyfsdm = map.get("pyfsdm");
					String pyfs = "";
					String[] pyfsinfo = dao.getOneRs(sql,
							new String[] { pyfsdm }, new String[] { "pyfs" });
					if (null != pyfsinfo) {
						pyfs = pyfsinfo[0];
					}
					map.put("pyfsdm", pyfs);
				} else if ("�о���".equals(xslb)) {
					sql = "select pyfs from dmk_yjspyfs where pyfsdm=?";
					String pyfsdm = map.get("pyfsdm");
					String pyfs = "";
					String[] pyfsinfo = dao.getOneRs(sql,
							new String[] { pyfsdm }, new String[] { "pyfs" });
					if (null != pyfsinfo) {
						pyfs = pyfsinfo[0];
					}
					map.put("pyfsdm", pyfs);
				}

			}
		}

		if ("refresh".equals(doType2)) {
			String dwdq = DealString.toGBK(request.getParameter("dwdq"));
			String zgbm = DealString.toGBK(request.getParameter("zgbm"));

			// ��λ����������װ��map
			sql = "select * from dmk_dwdq where dwdq=?";
			String[] dwdqinfo = dao.getOneRs(sql, new String[] { dwdq },
					new String[] { "dwdqdm" });
			String dwdqdm = "";
			if (null != dwdqinfo) {
				dwdqdm = dwdqinfo[0];
			}

			// ���ܲ��Ŵ�����װ��map
			sql = "select * from dmk_zgbm where zgbm=?";
			String[] zgbminfo = dao.getOneRs(sql, new String[] { zgbm },
					new String[] { "zgbmdm" });
			String zgbmdm = "";
			if (null != zgbminfo) {
				zgbmdm = zgbminfo[0];
			}

			// ��Ŀǰҳ�������е����ݴ�����װ��map�ٴ���ȥ

			String xslb = DealString.toGBK(request.getParameter("xslb"));
			String bynd = request.getParameter("bynd");
			String xymc = DealString.toGBK(request.getParameter("xymc"));

			String id = request.getParameter("id"); // 1���֤��
			String xxdm = request.getParameter("xxdm"); // 2ѧУ����
			String xxmc = DealString.toGBK(request.getParameter("xxmc")); // 3ѧУ����
			String zydm = request.getParameter("zydm"); // 4רҵ����
			String zymc = DealString.toGBK(request.getParameter("zymc")); // 5רҵ����
			xsxh = request.getParameter("xsxh"); // 6ѧ��
			String name = DealString.toGBK(request.getParameter("name")); // 7����
			String xbdm = DealString.toGBK(request.getParameter("xbdm")); // 8�Ա����
			String xldm = DealString.toGBK(request.getParameter("xldm")); // 9ѧ������
			String xzdm = DealString.toGBK(request.getParameter("xzdm")); // 10ѧ��
			String sydqdm = DealString.toGBK(request.getParameter("sydqdm")); // 11��Դ��������
			String pyfsdm = DealString.toGBK(request.getParameter("pyfsdm")); // 12������ʽ����
			String memo = DealString.toGBK(request.getParameter("memo")); // 13��ע
			String zzmmdm = request.getParameter("zzmmdm"); // 14������ò����
			String sydq = DealString.toGBK(request.getParameter("sydq")); // 15��Դ��������
			String xxdjh = request.getParameter("xxdjh"); // 16��Ϣ�ǼǺ�
			String zzjgdm = request.getParameter("zzjgdm"); // 17��֯��������
			String dwmc = DealString.toGBK(request.getParameter("dwmc")); // 18��λ����
			// dwdqdm = request.getParameter("dwdqdm"); // 19��λ��������
			// dwdq = DealString.toGBK(request.getParameter("dwdq")); //
			// 20��λ��������
			// zgbm = DealString.toGBK(request.getParameter("zgbm")); //
			// 21���ܲ�������
			// zgbmdm = request.getParameter("zgbmdm"); // 38���ܲ��Ŵ���
			String byqxdm = request.getParameter("byqxdm"); // 22��ҵȥ�����
			String blueno = request.getParameter("blueno"); // 23������׼�ĺ�
			String wjybz = request.getParameter("wjybz"); // 24δ��ҵ��־
			String wjyyy = DealString.toGBK(request.getParameter("wjyyy")); // 25δ��ҵԭ��
			String lxdz = DealString.toGBK(request.getParameter("lxdz")); // 26��ϵ��ַ
			String yb = request.getParameter("yb"); // 27�ʱ�
			String dh = request.getParameter("dh"); // 28�绰
			String bgdabz = request.getParameter("bgdabz"); // 29���ܵ�����־
			String bz1 = DealString.toGBK(request.getParameter("bz1")); // 30�Զ���1
			String bz2 = DealString.toGBK(request.getParameter("bz2")); // 31�Զ���2
			String bz3 = DealString.toGBK(request.getParameter("bz3")); // 32�Զ���3
			String bz4 = DealString.toGBK(request.getParameter("bz4")); // 33�Զ���4
			String bz5 = DealString.toGBK(request.getParameter("bz5")); // 34�Զ���5
			String jzzhlbbzwdm = request.getParameter("jzzhlbbzwdm"); // 35��ס֤�������־λ
			String sydzgbm = DealString.toGBK(request.getParameter("sydzgbm")); // 36��Դ�����ܵ�λ
			String dwxzdm = request.getParameter("dwxzdm"); // 37��λ���ʴ���
			String dwxzdm2 = request.getParameter("dwxzdm2"); // 39��λ���ʴ���2
			String dwdz = DealString.toGBK(request.getParameter("dwdz")); // 40��λ��ַ
			String dwdh = request.getParameter("dwdh"); // 41��λ�绰
			String dwyb = request.getParameter("dwyb"); // 42��λ�ʱ�
			String dajsd = DealString.toGBK(request.getParameter("dajsd")); // 43�������ܵ�
			String dajsddz = DealString.toGBK(request.getParameter("dajsddz")); // 44�������ܵص�ַ
			String dajsdyb = request.getParameter("dajsdyb"); // 45�������ܵ��ʱ�
			String dynypjgz = request.getParameter("dynypjgz"); // 46��һ����ƽ������
			String wyj = request.getParameter("wyj"); // 47ΥԼ��
			String dqlx = DealString.toGBK(request.getParameter("dqlx")); // 48��������
			String hyfl = DealString.toGBK(request.getParameter("hyfl")); // 49��ҵ����
			String zydk = request.getParameter("zydk"); // 50רҵ�Կ�

			String fdysh = DealString.toGBK(request.getParameter("fdysh"));// ����Ա���
			String xxsh = DealString.toGBK(request.getParameter("xxsh"));// ѧУ���
			String fdyshren = request.getParameter("fdyshren");// ����Ա�����
			String xxshren = request.getParameter("xxshren");// ѧУ�����
			String fdyshsj = DealString.toGBK(request.getParameter("fdyshsj"));// ����Ա���ʱ��
			String xxshsj = DealString.toGBK(request.getParameter("xxshsj"));// ѧУ���ʱ��

			// װ��map
			map.put("xslb", xslb);
			map.put("bynd", bynd);
			map.put("xymc", xymc);

			map.put("id", id);
			map.put("xxdm", xxdm);
			map.put("xxmc", xxmc);
			map.put("zydm", zydm);
			map.put("zymc", zymc);
			map.put("xsxh", xsxh);
			map.put("name", name);
			map.put("xbdm", xbdm);
			map.put("xldm", xldm);
			map.put("xzdm", xzdm);
			map.put("sydqdm", sydqdm);
			map.put("pyfsdm", pyfsdm);
			map.put("memo", memo);
			map.put("zzmmdm", zzmmdm);
			map.put("sydq", sydq);
			map.put("xxdjh", xxdjh);
			map.put("zzjgdm", zzjgdm);
			map.put("dwmc", dwmc);
			map.put("dwdqdm", dwdqdm);
			map.put("dwdq", dwdq);
			map.put("zgbm", zgbm);
			map.put("zgbmdm", zgbmdm);
			map.put("byqxdm", byqxdm);
			map.put("blueno", blueno);
			map.put("wjybz", wjybz);
			map.put("wjyyy", wjyyy);
			map.put("lxdz", lxdz);
			map.put("yb", yb);
			map.put("dh", dh);
			map.put("bgdabz", bgdabz);
			map.put("bz1", bz1);
			map.put("bz2", bz2);
			map.put("bz3", bz3);
			map.put("bz4", bz4);
			map.put("bz5", bz5);
			map.put("jzzhlbbzwdm", jzzhlbbzwdm);
			map.put("sydzgbm", sydzgbm);
			map.put("dwxzdm", dwxzdm);
			map.put("dwxzdm2", dwxzdm2);
			map.put("dwdz", dwdz);
			map.put("dwdh", dwdh);
			map.put("dwyb", dwyb);
			map.put("dajsd", dajsd);
			map.put("dajsddz", dajsddz);
			map.put("dajsdyb", dajsdyb);
			map.put("dynypjgz", dynypjgz);
			map.put("wyj", wyj);
			map.put("dqlx", dqlx);
			map.put("hyfl", hyfl);
			map.put("zydk", zydk);

			map.put("fdysh", fdysh);
			map.put("xxsh", xxsh);
			map.put("fdyshren", fdyshren);
			map.put("xxshren", xxshren);
			map.put("fdyshsj", fdyshsj);
			map.put("xxshsj", xxshsj);
			request.setAttribute("whichArea", "shenhe");
		}
		request.setAttribute("rs", map);// �������ݼ�
		StuInfoDAO stuDao = new StuInfoDAO();
		request.setAttribute("shiList", stuDao.getShiList("").get("shiList"));
		request.setAttribute("xianList", stuDao.getShiList("").get("xianList"));
		request.setAttribute("ssList", stuDao.getSsList());

		if (Globals.XXDM_ZGDZDX.equals(whichxx)) {
			sql = "select pyfsdm,pyfs from dmk_bzpyfs";
			List pyfsList = dao.getList(sql, new String[] {}, new String[] {
					"pyfsdm", "pyfs" });
			request.setAttribute("pyfsList", pyfsList);
			return mapping.findForward("successdz");
		} else {
			return mapping.findForward("success");
		}
	}

	// ��ҵЭ����˽����ѯ
	private ActionForward jyglJyxyResultQuery(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response, String userType) throws Exception {
		CommanForm commanForm = (CommanForm) form;
		DAO dao = DAO.getInstance();
		String sql = "";
		String pk = "xsxh";
		String[] colList = null;
		String[] colListCN = null;
		ArrayList<Object> rs = new ArrayList<Object>(); // ��ü�¼
		String rsNum = "0";
		String tableName = "jygl_jyxy";
		List topTr = null;
		HashMap<String, String> map = new HashMap<String, String>();
		HttpSession session = request.getSession();
		String userName = session.getAttribute("userName").toString();

		if (userType.equals("teacher")) {
			String admin = session.getAttribute("userType").toString();
			if ("xx".equalsIgnoreCase(admin) || "admin".equalsIgnoreCase(admin)) {
				// -----------2010/6/4 edit by luojw -----------
				sql = "select " + pk + " ����,rownum r,a.* from " + tableName
						+ " a ";
				// ----------end -----------
			} else {
				sql = "select " + pk + " ����,rownum r,a.* from " + tableName
						+ " a " + " where fdyshren='" + userName + "'";
			}
			colList = new String[] { "����", "r", "nd", "bynd", "name", "xsxh",
					"xbdm", "xslb", "xymc", "zymc", "fdysh", "xxsh" };
			colListCN = dao.getColumnNameCN(colList, tableName);
			topTr = dao.arrayToList(colList, colListCN);
			if ((request.getParameter("act") != null)
					&& request.getParameter("act").equalsIgnoreCase("go")) {
				rs.addAll(CommonQueryDAO.commonQuery(sql, "", new String[] {},
						colList, commanForm));
				if (rs == null) {
					rsNum = "0";
				} else {
					rsNum = String.valueOf(rs.size());
				}
			}
			request.setAttribute("ableToView", "teacher");
		}

		if (userType.equals("student")) {
			sql = "select " + pk + " ����,rownum r,a.* from " + tableName + " a "
					+ " where xsxh=" + userName;
			colList = new String[] { "����", "r", "nd", "bynd", "name", "xsxh",
					"xbdm", "xslb", "xymc", "zymc", "fdysh", "xxsh" };
			colListCN = dao.getColumnNameCN(colList, tableName);
			topTr = dao.arrayToList(colList, colListCN);
			if ((request.getParameter("act") != null)
					&& request.getParameter("act").equalsIgnoreCase("go")) {
				rs.addAll(CommonQueryDAO.commonQuery(sql, "", new String[] {},
						colList, commanForm));
				if (rs == null) {
					rsNum = "0";
				} else {
					rsNum = String.valueOf(rs.size());
				}
			}
			request.setAttribute("ableToView", "student");
		}

		map.put("userName", userName);
		request.setAttribute("rs1", map);
		request.setAttribute("rs", rs); // �������ݼ�
		request.setAttribute("topTr", topTr); // ���ͱ�ͷ
		request.setAttribute("rsNum", rsNum); // ���ͼ�¼��
		return mapping.findForward("success");
	}

	// ��ҵЭ����ȡ�Ǽ�
	private ActionForward jyglJyxyLqdj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DAO dao = DAO.getInstance();
		HashMap<String, String> map = new HashMap<String, String>();
		ArrayList<HashMap<String, String>> rs = new ArrayList<HashMap<String, String>>();
		String doType = request.getParameter("doType");
		String act = request.getParameter("act");
		String realTable = "jygl_jyxyslqdjb";
		String pk = "xh";
		String sql = "";
		// �ж��û�����
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		String xh = DealString.toGBK(request.getParameter("xh"));
		String xm = DealString.toGBK(request.getParameter("xm"));
		String xb = DealString.toGBK(request.getParameter("xb"));
		String jyxybh = DealString.toGBK(request.getParameter("jyxybh"));
		String nj = DealString.toGBK(request.getParameter("nj"));
		String xydm = DealString.toGBK(request.getParameter("xydm"));
		String zydm = DealString.toGBK(request.getParameter("zydm"));
		String bjdm = DealString.toGBK(request.getParameter("bjdm"));
		String lqqk = DealString.toGBK(request.getParameter("lqqk"));

		if ("admin".equalsIgnoreCase(userType)
				|| "xx".equalsIgnoreCase(userType)) {
			request.setAttribute("who", "xx");
		} else if ("xy".equalsIgnoreCase(userType)) {
			request.setAttribute("who", "xy");
			xydm = userDep;
		}

		if ("delall".equalsIgnoreCase(doType)) {
			String pkstring = request.getParameter("pkstring");
			String[] pkstringI = new String[] {};
			if (pkstring != null) {
				pkstringI = pkstring.split("!!#!!");
			}

			boolean judge = false;
			for (int i = 1; i < pkstringI.length; i++) {
				String whichxh = pkstringI[i];
				judge = StandardOperation.delete(realTable, pk, whichxh,
						request);
			}
			if (judge) {
				request.setAttribute("delall", "ok");
			} else {
				request.setAttribute("delall", "no");
			}
		}

		StringBuffer query = new StringBuffer();

		if (!("".equals(xh))) {
			query.append(" and xh like '%");
			query.append(xh);
			query.append("%'");
		}
		if (!("".equals(xm))) {
			query.append(" and xm like '%");
			query.append(xm);
			query.append("%'");
		}
		if (!("".equals(xb))) {
			query.append(" and xb like '%");
			query.append(xb);
			query.append("%'");
		}
		if (!("".equals(jyxybh))) {
			query.append(" and jyxybh like '%");
			query.append(jyxybh);
			query.append("%'");
		}
		if (!("".equals(nj))) {
			query.append(" and nj = '");
			query.append(nj);
			query.append("'");
		}
		if (!("".equals(xydm))) {
			query.append(" and xydm='");
			query.append(xydm);
			query.append("'");
		}
		if (!("".equals(zydm))) {
			query.append(" and zydm='");
			query.append(zydm);
			query.append("'");
		}
		if (!("".equals(bjdm))) {
			query.append(" and bjdm='");
			query.append(bjdm);
			query.append("'");
		}
		// ============begin �й�����ѧԺ 2009/6/4 luojw ===========
		String xxdm = StandardOperation.getXxdm();
		if (xxdm.equalsIgnoreCase(Globals.XXDM_ZGMSXY)) {
			if (!("".equals(lqqk))) {
				query.append(" and lqqk='");
				query.append(lqqk);
				query.append("'");
			}
		}
		// ============end �й�����ѧԺ 2009/6/4 luojw ===========

		String query1 = query.toString();

		CommanForm dataSearchForm = (CommanForm) form;
		sql = "select count(*) count from view_jygl_jyxyslqdjb a where 1=1 "
				+ query1;

		dataSearchForm.getPages().setMaxRecord(
				Integer.parseInt(dao.getOneRs(sql, new String[] {}, "count")));

		// ѧ��������Ϣ
		sql = "select a.r �к�,a.* from (select a.*,rownum r from (select distinct  a.xh,a.xm,a.xb,a.nj,a.xymc,a.zymc,a.bjmc,a.jyxybh from view_jygl_jyxyslqdjb a where 1=1 "
				+ query1
				+ " ) a ) a where a.r>"
				+ dataSearchForm.getPages().getStart()
				+ " and a.r<="
				+ (dataSearchForm.getPages().getStart() + dataSearchForm
						.getPages().getPageSize());

		String[] colList = new String[] { "�к�", "xh", "xm", "xb", "nj", "xymc",
				"zymc", "bjmc", "jyxybh" };
		if ("go".equalsIgnoreCase(act)) {

			// ============begin �й�����ѧԺ 2009/6/4 luojw ===========
			if (xxdm.equalsIgnoreCase(Globals.XXDM_ZGMSXY)) {
				sql = "select a.r �к�,a.* from (select a.*,rownum r from (select distinct  a.xh,a.xm,a.xb,a.nj,a.xymc,a.zymc,a.bjmc,a.jyxybh,a.lqqk from view_jygl_jyxyslqdjb a where 1=1 "
						+ query1
						+ " ) a ) a where a.r>"
						+ dataSearchForm.getPages().getStart()
						+ " and a.r<="
						+ (dataSearchForm.getPages().getStart() + dataSearchForm
								.getPages().getPageSize());

				colList = new String[] { "�к�", "xh", "xm", "xb", "nj", "xymc",
						"zymc", "bjmc", "jyxybh", "lqqk" };
			}
			// ============end �й�����ѧԺ 2009/6/4 luojw ===========

			rs = dao.getArrayList2(sql, new String[] {}, colList);
			map.put("xh", xh);
			map.put("xm", xm);
			map.put("xb", xb);
			map.put("nj", nj);
			map.put("jyxybh", jyxybh);
			map.put("xydm", xydm);
			map.put("zydm", zydm);
			map.put("bjdm", bjdm);
			map.put("lqqk", lqqk);
		}

		sql = "select count(*) from view_jygl_jyxyslqdjb where 1=1 " + query1;
		int rsNuminfo = dao.getOneRsint(sql);
		String rsNum = String.valueOf(rsNuminfo);
		String[] colListCN = dao.getColumnNameCN(colList,
				"view_jygl_jyxyslqdjb");
		List topTr = dao.arrayToList(colList, colListCN);

		request.setAttribute("querry", query1);
		request.setAttribute("rs1", map);
		request.setAttribute("realTable", "jygl_jyxyslqdjb");
		request.setAttribute("table", "view_jygl_jyxyslqdjb");
		request.setAttribute("rs", rs);
		request.setAttribute("rsNum", rsNum);
		request.setAttribute("topTr", topTr);
		request.setAttribute("njList", dao.getNjList());// ����ѧ���б�
		request.setAttribute("xyList", dao.getXyList());// ����ѧԺ�б�
		request.setAttribute("zyList", dao.getZyList(xydm));// ����רҵ�б�
		request.setAttribute("bjList", dao.getBjList(xydm, zydm, nj));// ����רҵ�б�
		return mapping.findForward("success");
	}

	// ��ҵ��ȡ�Ǽ���ϸ��Ϣ
	private ActionForward viewJyxyLqdjInfo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		HashMap<String, String> map = new HashMap<String, String>();
		DAO dao = DAO.getInstance();
		String sql = "";
		String pk = "xh";
		String pkValue = request.getParameter("pkValue");
		String tableName = "view_jygl_jyxyslqdjb";
		String act = request.getParameter("act");
		String userName = request.getSession().getAttribute("userName")
				.toString();
		String userType = request.getSession().getAttribute("userType")
				.toString();
		// String xxdm = StandardOperation.getXxdm();

		if ("view".equalsIgnoreCase(act)) {
			// ��ѯ����
			sql = "select * from " + tableName + " where " + pk + "='"
					+ pkValue + "'";
			String[] colList = dao.getColumnName("select * from " + tableName
					+ " where 1=2"); // ������������
			String[] stuinfo = dao.getOneRs(sql, new String[] {}, colList);
			if (stuinfo != null) {
				for (int i = 0; i < colList.length; i++) {
					map.put(colList[i].toLowerCase(), stuinfo[i]); // ����¼ѭ������map��
				}
			}
		}

		// ============begin �й�����ѧԺ 2009/6/4 luojw ===========
		String xxdm = StandardOperation.getXxdm();
		if (xxdm.equalsIgnoreCase(Globals.XXDM_ZGMSXY)
				|| xxdm.equalsIgnoreCase(Globals.XXDM_SJXY)) {
			if ("add".equalsIgnoreCase(act)) {
				request.setAttribute("type", "add");
				request.setAttribute("update", "no");
			}
			if ("edit".equalsIgnoreCase(act)) {
				String xh = DealString.toGBK(request.getParameter("xh")).trim();
				// ��ѯ����
				sql = "select * from " + tableName + " where " + pk + "='" + xh
						+ "'";
				String[] colList = dao.getColumnName("select * from "
						+ tableName + " where 1=2"); // ������������
				String[] stuinfo = dao.getOneRs(sql, new String[] {}, colList);
				if (stuinfo != null) {
					for (int i = 0; i < colList.length; i++) {
						map.put(colList[i].toLowerCase(), stuinfo[i]); // ����¼ѭ������map��
					}
				}

				request.setAttribute("type", "add");
				request.setAttribute("update", "yes");
			}
			if ("save".equalsIgnoreCase(act)) {
				CommanForm dataSearchForm = (CommanForm) form;

				String xh = dataSearchForm.getXh();
				String jyxybh = dataSearchForm.getJyxybh();
				String remark = DealString.toGBK(dataSearchForm.getRemark());
				String lqqk = DealString.toGBK(request.getParameter("lqqk"));
				boolean insert = StandardOperation.delete("jygl_jyxyslqdjb",
						"xh", xh, request);
				if (insert) {
					insert = StandardOperation.insert("jygl_jyxyslqdjb",
							new String[] { "xh", "jyxybh", "remark", "lqqk" },
							new String[] { xh, jyxybh, remark, lqqk }, request);
				}
				if (insert) {
					request.setAttribute("inserted", "ok");
				} else {
					request.setAttribute("inserted", "no");
				}
			}
			if ("allSave".equalsIgnoreCase(act)) {
				CommanForm dataSearchForm = (CommanForm) form;
				String[] checkVal = dataSearchForm.getPk1();
				// �ж��з�ѡ��
				if (checkVal != null) {
					sql = "";
					StringBuffer sb = new StringBuffer();
					String[] pksql = new String[] {};
					String lqsj = StandardOperation.getRightTime().substring(0,
							8);
					for (int i = 0; i < checkVal.length; i++) {
						String xh = DealString.toGBK(checkVal[i]);
						sql = "update jygl_jyxyslqdjb set lqqk = '����ȡ',lqsj='"
								+ lqsj + "' where xh = '" + xh + "'";
						sb.append(sql);
						sb.append("!!#!!");
					}
					pksql = sb.toString().split("!!#!!");
					dao.runBatch(pksql);
				}
				return new ActionForward("/jygl_xylqdj.do?act=go");
			}
		}
		// ============end �й�����ѧԺ 2009/6/4 luojw ===========
		if (true) {
			sql = "select NEWJYXYBH from view_jygl_jyxyblsqb where " + pk
					+ "='" + pkValue + "' and xxsh='��ͨ����'";
			String newjyxybh = dao.getOneRs(sql, new String[] {}, "NEWJYXYBH");
			map
					.put("newremark", "�¾�ҵЭ���ţ�"
							+ newjyxybh
							+ "\n"
							+ (Base.isNull(map.get("remark")) ? "" : map
									.get("remark")));
		}
		if ("stu".equalsIgnoreCase(userType)) {
			// ��ѯ����
			sql = "select * from view_xsxxb where xh ='" + userName + "'";
			String[] colList = dao
					.getColumnName("select * from view_xsxxb where 1=2"); // ������������
			String[] stuinfo = dao.getOneRs(sql, new String[] {}, colList);
			if (stuinfo != null) {
				for (int i = 0; i < colList.length; i++) {
					map.put(colList[i].toLowerCase(), stuinfo[i]); // ����¼ѭ������map��
				}
			}
		}
		request.setAttribute("rs", map);
		return mapping.findForward("success");
	}

	// ��ҵЭ�鲹��
	private ActionForward jyxyBlsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DAO dao = DAO.getInstance();
		HashMap<String, String> map = new HashMap<String, String>();
		HttpSession session = request.getSession();
		String userName = session.getAttribute("userName").toString();
		String userType = session.getAttribute("userType").toString();
		String doType = request.getParameter("doType");

		if ("save".equalsIgnoreCase(doType)) {
			String xh = DealString.toGBK(request.getParameter("xh")); // ѧ��
			String xl = DealString.toGBK(request.getParameter("xl")); // ѧ��
			String bysj = DealString.toGBK(request.getParameter("bysj")); // ��ҵʱ��
			String sqbg = DealString.toGBK(request.getParameter("sqbg")); // ���뱨��
			String sqsj = (dao
					.getOneRs(
							"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual",
							new String[] {}, new String[] { "sdate" }))[0]; // ����ʱ��

			boolean judge = false;

			judge = StandardOperation.insert("JYGL_JYXYBLSQB", new String[] {
					"xh", "xl", "bysj", "sqbg", "sqsj" }, new String[] { xh,
					xl, bysj, sqbg, sqsj }, request);
			if (judge) {
				request.setAttribute("save", "ok");
			} else {
				request.setAttribute("save", "no");
			}

		}

		if (!"stu".equalsIgnoreCase(userType)) {
			request.setAttribute("nopower", "nopower");
		} else {
			String[] stuinfo = dao.getOneRs(
					"select * from view_xsjbxx where xh=?",
					new String[] { userName }, new String[] { "xh", "xb", "xm",
							"xymc", "zymc" });
			String xysbhinfo = dao.getOneRs(
					"select jyxybh from view_jygl_jyxyslqdjb where xh=?",
					new String[] { userName }, "jyxybh");

			if (null == xysbhinfo || "".equalsIgnoreCase(xysbhinfo)) {
				request.setAttribute("nojyxy", "nojyxy");
			}
			if (null != stuinfo) {
				map.put("xh", stuinfo[0]);
				map.put("xb", stuinfo[1]);
				map.put("xm", stuinfo[2]);
				map.put("xymc", stuinfo[3]);
				map.put("zymc", stuinfo[4]);
				if (null != xysbhinfo) {
					map.put("jyxybh", xysbhinfo);
				}
			}

		}

		request.setAttribute("rs", map);

		return mapping.findForward("success");
	}

	// ��ҵЭ�鲹�����
	private ActionForward jyxyBlsqQuery(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DAO dao = DAO.getInstance();
		HashMap<String, String> map = new HashMap<String, String>();
		ArrayList<HashMap<String, String>> rs = new ArrayList<HashMap<String, String>>();
		String doType = request.getParameter("doType");
		String act = request.getParameter("act");
		String tableName = "view_jygl_jyxyblsqb";
		String realTable = "jygl_jyxyblsqb";
		String pk = "xh";
		String sql = "";
		// �ж��û�����
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		String xh = DealString.toGBK(request.getParameter("xh"));
		String xm = DealString.toGBK(request.getParameter("xm"));
		String xb = DealString.toGBK(request.getParameter("xb"));
		String nj = DealString.toGBK(request.getParameter("nj"));
		String xydm = DealString.toGBK(request.getParameter("xydm"));
		String zydm = DealString.toGBK(request.getParameter("zydm"));
		String bjdm = DealString.toGBK(request.getParameter("bjdm"));
		String xysh = DealString.toGBK(request.getParameter("xysh"));
		String xxsh = DealString.toGBK(request.getParameter("xxsh"));
		if ("admin".equalsIgnoreCase(userType)
				|| "xx".equalsIgnoreCase(userType)) {
			request.setAttribute("who", "xx");
		} else if ("xy".equalsIgnoreCase(userType)) {
			request.setAttribute("who", "xy");
			xydm = userDep;
		}

		if ("delall".equalsIgnoreCase(doType)) {
			String pkstring = request.getParameter("pkstring");
			String[] pkstringI = new String[] {};
			if (pkstring != null) {
				pkstringI = pkstring.split("!!#!!");
			}

			boolean judge = false;
			for (int i = 1; i < pkstringI.length; i++) {
				String whichxh = pkstringI[i];
				judge = StandardOperation.delete(realTable, pk, whichxh,
						request);
			}
			if (judge) {
				request.setAttribute("delall", "ok");
			} else {
				request.setAttribute("delall", "no");
			}
		}

		StringBuffer query = new StringBuffer();

		if (!("".equals(xh))) {
			query.append(" and xh like '%");
			query.append(xh);
			query.append("%'");
		}
		if (!("".equals(xm))) {
			query.append(" and xm like '%");
			query.append(xm);
			query.append("%'");
		}
		if (!("".equals(xb))) {
			query.append(" and xb like '%");
			query.append(xb);
			query.append("%'");
		}

		if (!("".equals(nj))) {
			query.append(" and dqszj = '%");
			query.append(nj);
			query.append("%'");
		}
		if (!("".equals(xydm))) {
			query.append(" and xydm='");
			query.append(xydm);
			query.append("'");
		}
		if (!("".equals(zydm))) {
			query.append(" and zydm='");
			query.append(zydm);
			query.append("'");
		}
		if (!("".equals(bjdm))) {
			query.append(" and bjdm='");
			query.append(bjdm);
			query.append("'");
		}
		if (!("".equals(xysh))) {
			query.append(" and xysh='");
			query.append(xysh);
			query.append("'");
		}
		if (!("".equals(xxsh))) {
			query.append(" and xxsh='");
			query.append(xxsh);
			query.append("'");
		}

		String query1 = query.toString();

		CommanForm dataSearchForm = (CommanForm) form;
		sql = "select count(*) count from view_jygl_jyxyblsqb a where 1=1 "
				+ query1;

		dataSearchForm.getPages().setMaxRecord(
				Integer.parseInt(dao.getOneRs(sql, new String[] {}, "count")));

		// ѧ��������Ϣ
		sql = "select a.r �к�,a.* from (select a.*,rownum r from (select distinct  a.xh,a.xm,a.xb,a.nj,a.xymc,a.zymc,a.bjmc,a.xyshimg,a.xxshimg from view_jygl_jyxyblsqb a where 1=1 "
				+ query1
				+ " ) a ) a where a.r>"
				+ dataSearchForm.getPages().getStart()
				+ " and a.r<="
				+ (dataSearchForm.getPages().getStart() + dataSearchForm
						.getPages().getPageSize());

		String[] colList = new String[] { "�к�", "xh", "xm", "xb", "nj", "xymc",
				"zymc", "bjmc", "xyshimg", "xxshimg" };
		if ("go".equalsIgnoreCase(act)) {
			rs = dao.getArrayList2(sql, new String[] {}, colList);
			map.put("xh", xh);
			map.put("xm", xm);
			map.put("xb", xb);
			map.put("nj", nj);
			map.put("xydm", xydm);
			map.put("zydm", zydm);
			map.put("bjdm", bjdm);
			map.put("xysh", xysh);
			map.put("xxsh", xxsh);
		}

		sql = "select count(*) from view_jygl_jyxyblsqb where 1=1 " + query1;
		int rsNuminfo = dao.getOneRsint(sql);
		String rsNum = String.valueOf(rsNuminfo);
		String[] colListCN = dao
				.getColumnNameCN(colList, "view_jygl_jyxyblsqb");
		List topTr = dao.arrayToList(colList, colListCN);

		request.setAttribute("querry", query1);
		request.setAttribute("rs1", map);
		request.setAttribute("realTable", "view_jygl_jyxyblsqb");
		request.setAttribute("table", "view_jygl_jyxyblsqb");
		request.setAttribute("rs", rs);
		request.setAttribute("rsNum", rsNum);
		request.setAttribute("topTr", topTr);
		request.setAttribute("tableName", tableName);
		request.setAttribute("njList", dao.getNjList());// �����꼶�б�
		request.setAttribute("xyList", dao.getXyList());// ����ѧԺ�б�
		request.setAttribute("zyList", dao.getZyList(xydm));// ����רҵ�б�
		request.setAttribute("bjList", dao.getBjList(xydm, zydm, nj));// ����רҵ�б�
		return mapping.findForward("success");
	}

	// ��ҵЭ�鲹����ϸ�鿴--���
	private ActionForward jyxyBlsqViewMore(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		HashMap<String, String> map = new HashMap<String, String>();
		DAO dao = DAO.getInstance();
		String sql = "";
		String pk = "xh";
		String pkValue = request.getParameter("pkValue");
		String tableName = "view_jygl_jyxyblsqb";
		String realTable = "jygl_jyxyblsqb";
		String act = request.getParameter("act");

		String doType = request.getParameter("doType");
		String doType2 = request.getParameter("doType2");

		boolean judge = false;

		if ("xysh".equalsIgnoreCase(doType2)) {
			String xysh = DealString.toGBK(request.getParameter("xysh"));
			String xyshsj = (dao
					.getOneRs(
							"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual",
							new String[] {}, new String[] { "sdate" }))[0]; // ʱ��
			judge = StandardOperation.update(realTable, new String[] { "xysh",
					"xyshsj" }, new String[] { xysh, xyshsj }, pk, pkValue,
					request);

			if (judge) {
				request.setAttribute("xysh", "ok");
			} else {
				request.setAttribute("xysh", "no");
			}
		}

		if ("xxsh".equalsIgnoreCase(doType2)) {
			String xxsh = DealString.toGBK(request.getParameter("xxsh"));
			String newjyxybh = DealString.toGBK(request
					.getParameter("newjyxybh"));
			String xxshsj = (dao
					.getOneRs(
							"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual",
							new String[] {}, new String[] { "sdate" }))[0]; // ʱ��
			judge = StandardOperation.update(realTable, new String[] { "xxsh",
					"xxshsj", "newjyxybh" }, new String[] { xxsh, xxshsj,
					newjyxybh }, pk, pkValue, request);

			if (judge) {
				request.setAttribute("xxsh", "ok");
			} else {
				request.setAttribute("xxsh", "no");
			}

		}

		if ("view".equalsIgnoreCase(act)) {
			// ��ѯ����
			sql = "select * from " + tableName + " where " + pk + "='"
					+ pkValue + "'";
			String[] colList = dao.getColumnName("select * from " + tableName
					+ " where 1=2"); // ������������
			String[] stuinfo = dao.getOneRs(sql, new String[] {}, colList);
			if (stuinfo != null) {
				for (int i = 0; i < colList.length; i++) {
					map.put(colList[i].toLowerCase(), stuinfo[i]); // ����¼ѭ������map��
				}
				String sqsj = map.get("sqsj");
				String xyshsj = map.get("xyshsj");
				String xxshsj = map.get("xxshsj");

				if (null != sqsj && !"".equalsIgnoreCase(sqsj)) {
					map.put("sqsj", dao.doForTime(sqsj));
				}
				if (null != xyshsj && !"".equalsIgnoreCase(xyshsj)) {
					map.put("xyshsj", dao.doForTime(xyshsj));
				}
				if (null != xxshsj && !"".equalsIgnoreCase(xxshsj)) {
					map.put("xxshsj", dao.doForTime(xxshsj));
				}

			}
		}

		request.setAttribute("pk", pkValue);
		request.setAttribute("rs", map);

		if ("xysh".equalsIgnoreCase(doType)) {
			return mapping.findForward("xysh");
		}
		if ("xxsh".equalsIgnoreCase(doType)) {
			return mapping.findForward("xxsh");
		}

		return mapping.findForward("success");
	}

	// ��ҵȥ��ҳ���
	private ActionForward byqxInput(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			String userType) throws Exception {
		DAO dao = DAO.getInstance();
		String xxdm = StandardOperation.getXxdm();
		String sql = "";
		String tableName = "jygl_byqx";
		HashMap<String, String> map = new HashMap<String, String>();
		HttpSession session = request.getSession();
		userType = session.getAttribute("userOnLine").toString();
		String xsxh = request.getParameter("xsxh");
		String act = request.getParameter("act");
		String doType = request.getParameter("doType");
		String bysjytj = request.getParameter("bysjytjb");

		if ("student".equals(userType)) {
			xsxh = session.getAttribute("userName").toString();
			act = "go";
		}

		sql = "select byqxdm,byqx from dmk_byqx";
		List byqxList = dao.getList(sql, new String[] {}, new String[] {
				"byqxdm", "byqx" });
		request.setAttribute("byqxList", byqxList);

		if (xxdm.equals(Globals.XXDM_ZZSF)) { // ����ʦ��
			if ("save".equals(doType)) {
				String bynd = request.getParameter("bynd"); // ��ҵ���
				String xymc = DealString.toGBK(request.getParameter("xymc"));// ѧԺ����
				xsxh = request.getParameter("xsxh");// ѧ��
				String name = DealString.toGBK(request.getParameter("name"));// ����
				String xb = DealString.toGBK(request.getParameter("xb")); // �Ա�
				String id = request.getParameter("id"); // ���֤��
				String sydq = DealString.toGBK(request.getParameter("sydq")); // ��Դ����
				String byqx = DealString.toGBK(request.getParameter("byqx")); // ��ҵȥ��
				String lxdz = DealString.toGBK(request.getParameter("lxdz")); // ��ϵ��ַ
				String lxdh = request.getParameter("lxdh"); // ��ϵ�绰
				String yzbm = request.getParameter("yzbm"); // ��������
				String yddh = request.getParameter("yddh"); // �ƶ��绰
				String email = request.getParameter("email"); // ��������
				String dayjdz = DealString
						.toGBK(request.getParameter("dayjdz")); // �����ʼĵ�ַ
				String dajydh = DealString
						.toGBK(request.getParameter("dajydh")); // ������Ҫ����
				String ydjycrq = DealString.toGBK(request
						.getParameter("ydjycrq")); // �ʵ���ʴ�����
				String bdzh = DealString.toGBK(request.getParameter("bdzh")); // ����֤��
				String xysbh = DealString.toGBK(request.getParameter("xysbh")); // Э������
				String xslb = DealString.toGBK(request.getParameter("xslb")); // ѧ�����

				String tjsj = (dao
						.getOneRs(
								"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual",
								new String[] {}, new String[] { "sdate" }))[0]; // �ύʱ��

				boolean judge = false;
				judge = StandardOperation.insert(tableName, new String[] {
						"bynd", "xymc", "xsxh", "name", "xb", "id", "sydq",
						"byqx", "lxdz", "lxdh", "yzbm", "yddh", "email",
						"tjsj", "dayjdz", "dajydh", "ydjycrq", "bdzh", "xysbh",
						"xslb" }, new String[] { bynd, xymc, xsxh, name, xb,
						id, sydq, byqx, lxdz, lxdh, yzbm, yddh, email, tjsj,
						dayjdz, dajydh, ydjycrq, bdzh, xysbh, xslb }, request);
				if (judge) {
					request.setAttribute("save", "ok");

					// ��Ӳ����ļ�¼
					if ("teacher".equals(userType)) {
						String userName = session.getAttribute("userName")
								.toString();
						String whensj = (dao
								.getOneRs(
										"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual",
										new String[] {},
										new String[] { "sdate" }))[0]; // �ύʱ��

						StandardOperation.insert("JYGL_USERCZMXB",
								new String[] { "userid", "dowhat",
										"whichtable", "whichpk", "whensj" },
								new String[] { userName, "����", "��ҵȥ���",
										"ѧ��:" + xsxh, whensj }, request);
					}
				} else {
					request.setAttribute("save", "no");
				}
			}
			map.put("stuExists", "yes");
			map.put("userType", userType);
			request.setAttribute("rs", map);
			return mapping.findForward("success2");// ����ʦ��

		} else if (xxdm.equals(Globals.XXDM_YNYS)) { // ��������

			if ("save".equals(doType)) {
				String bynd = request.getParameter("bynd"); // ��ҵ���
				String xymc = DealString.toGBK(request.getParameter("xymc"));// ѧԺ����
				xsxh = request.getParameter("xsxh");// ѧ��
				String name = DealString.toGBK(request.getParameter("name"));// ����
				String xb = DealString.toGBK(request.getParameter("xb")); // �Ա�
				String sydq = DealString.toGBK(request.getParameter("sydq")); // ��Դ����
				String byqx = DealString.toGBK(request.getParameter("byqx")); // ��ҵȥ��
				String lxdz = DealString.toGBK(request.getParameter("lxdz")); // ��ϵ��ַ
				String lxdh = request.getParameter("lxdh"); // ��ϵ�绰
				String yzbm = request.getParameter("yzbm"); // ��������
				String yddh = request.getParameter("yddh"); // �ƶ��绰
				String email = request.getParameter("email"); // ��������
				String xslb = DealString.toGBK(request.getParameter("xslb")); // ѧ�����
				String tjsj = (dao
						.getOneRs(
								"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual",
								new String[] {}, new String[] { "sdate" }))[0]; // �ύʱ��

				boolean judge = false;
				judge = StandardOperation.insert(tableName, new String[] {
						"bynd", "xymc", "xsxh", "name", "xb", "sydq", "byqx",
						"lxdz", "lxdh", "yzbm", "yddh", "email", "tjsj",
						"xslb", "xxsh" }, new String[] { bynd, xymc, xsxh,
						name, xb, sydq, byqx, lxdz, lxdh, yzbm, yddh, email,
						tjsj, xslb, "ֱ��ͨ��" }, request);
				if (judge) {
					request.setAttribute("save", "ok");

					// ��Ӳ����ļ�¼
					if ("teacher".equals(userType)) {
						String userName = session.getAttribute("userName")
								.toString();
						String whensj = (dao
								.getOneRs(
										"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual",
										new String[] {},
										new String[] { "sdate" }))[0]; // �ύʱ��

						StandardOperation.insert("JYGL_USERCZMXB",
								new String[] { "userid", "dowhat",
										"whichtable", "whichpk", "whensj" },
								new String[] { userName, "����", "��ҵȥ���",
										"ѧ��:" + xsxh, whensj }, request);
					}
				} else {
					request.setAttribute("save", "no");
				}
			}

			map.put("stuExists", "yes");
			map.put("userType", userType);
			request.setAttribute("rs", map);
			return mapping.findForward("success3");// ��������

		}

		// ------------2010/6/8 edit by luojw -----------
		boolean isSave = false;

		if ("save".equals(doType)) {

			xsxh = request.getParameter("xsxh");// ѧ��
			if ("12453".equals(xxdm)) {
				String[] str = dao.getOneRs(
						"select sfsh from jygl_xsjbxxb where xsxh=?",
						new String[] { xsxh }, new String[] { "sfsh" });
				boolean bool = false;
				if (str != null) {
					if ("��ͨ����".equals(str[0])) {
						bool = true;
					}
				}
				if (bool) {
					String bynd = request.getParameter("bynd"); // ��ҵ���
					String xymc = DealString
							.toGBK(request.getParameter("xymc"));// ѧԺ����
					String name = DealString
							.toGBK(request.getParameter("name"));// ����
					String xb = DealString.toGBK(request.getParameter("xb")); // �Ա�
					String id = request.getParameter("id"); // ���֤��
					String sydq = DealString
							.toGBK(request.getParameter("sydq")); // ��Դ����
					String byqx = DealString
							.toGBK(request.getParameter("byqx")); // ��ҵȥ��
					String lxdz = DealString
							.toGBK(request.getParameter("lxdz")); // ��ϵ��ַ
					String lxdh = request.getParameter("lxdh"); // ��ϵ�绰
					String yzbm = request.getParameter("yzbm"); // ��������
					String yddh = request.getParameter("yddh"); // �ƶ��绰
					String email = DealString.toGBK(request
							.getParameter("email")); // ��������
					String xslb = DealString
							.toGBK(request.getParameter("xslb")); // ѧ�����

					String tjsj = (dao
							.getOneRs(
									"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual",
									new String[] {}, new String[] { "sdate" }))[0]; // �ύʱ��

					boolean judge = false;
					judge = StandardOperation.insert(tableName, new String[] {
							"bynd", "xymc", "xsxh", "name", "xb", "id", "sydq",
							"byqx", "lxdz", "lxdh", "yzbm", "yddh", "email",
							"tjsj", "xslb" }, new String[] { bynd, xymc, xsxh,
							name, xb, id, sydq, byqx, lxdz, lxdh, yzbm, yddh,
							email, tjsj, xslb }, request);
					if (judge) {
						request.setAttribute("save", "ok");

						// ��Ӳ����ļ�¼
						if ("teacher".equals(userType)) {
							String userName = session.getAttribute("userName")
									.toString();
							String whensj = (dao
									.getOneRs(
											"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual",
											new String[] {},
											new String[] { "sdate" }))[0]; // �ύʱ��

							StandardOperation
									.insert("JYGL_USERCZMXB", new String[] {
											"userid", "dowhat", "whichtable",
											"whichpk", "whensj" },
											new String[] { userName, "����",
													"��ҵȥ���", "ѧ��:" + xsxh,
													whensj }, request);
						}
					} else {
						request.setAttribute("save", "no");
					}
				} else {
					request.setAttribute("exists", "exists");
				}
			} else {

				String bynd = request.getParameter("bynd"); // ��ҵ���
				String xymc = DealString.toGBK(request.getParameter("xymc"));// ѧԺ����
				xsxh = request.getParameter("xsxh");// ѧ��
				String name = DealString.toGBK(request.getParameter("name"));// ����
				String xb = DealString.toGBK(request.getParameter("xb")); // �Ա�
				String id = request.getParameter("id"); // ���֤��
				String sydq = DealString.toGBK(request.getParameter("sydq")); // ��Դ����
				String byqx = DealString.toGBK(request.getParameter("byqx")); // ��ҵȥ��
				String lxdz = DealString.toGBK(request.getParameter("lxdz")); // ��ϵ��ַ
				String lxdh = request.getParameter("lxdh"); // ��ϵ�绰
				String yzbm = request.getParameter("yzbm"); // ��������
				String yddh = request.getParameter("yddh"); // �ƶ��绰
				String email = DealString.toGBK(request.getParameter("email")); // ��������
				String xslb = DealString.toGBK(request.getParameter("xslb")); // ѧ�����
				String jydw = request.getParameter("jydw");
				String tjsj = (dao
						.getOneRs(
								"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual",
								new String[] {}, new String[] { "sdate" }))[0]; // �ύʱ��
				map.put("jydw", jydw);
				map.put("byqx", byqx);
				map.put("lxdz", lxdz);
				map.put("lxdh", lxdh);
				map.put("yzbm", yzbm);
				map.put("yddh", yddh);
				map.put("email", email);

				boolean judge = false;
				judge = StandardOperation.insert(tableName, new String[] {
						"bynd", "xymc", "xsxh", "name", "xb", "id", "sydq",
						"byqx", "lxdz", "lxdh", "yzbm", "yddh", "email",
						"tjsj", "xslb", "jydw" }, new String[] { bynd, xymc,
						xsxh, name, xb, id, sydq, byqx, lxdz, lxdh, yzbm, yddh,
						email, tjsj, xslb, jydw }, request);
				if (judge) {
					request.setAttribute("save", "ok");

					// ��Ӳ����ļ�¼
					if ("teacher".equals(userType)) {
						String userName = session.getAttribute("userName")
								.toString();
						String whensj = (dao
								.getOneRs(
										"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual",
										new String[] {},
										new String[] { "sdate" }))[0]; // �ύʱ��

						StandardOperation.insert("JYGL_USERCZMXB",
								new String[] { "userid", "dowhat",
										"whichtable", "whichpk", "whensj" },
								new String[] { userName, "����", "��ҵȥ���",
										"ѧ��:" + xsxh, whensj }, request);
					}
				} else {
					request.setAttribute("save", "no");
				}
				isSave = true;
				
			}
		}

		if ("go".equalsIgnoreCase(act) || isSave) {
			sql = "select * from jygl_xsjbxxb where xsxh=?"; // ��ѯ��ѧ�ŵ�������ݵ�sql���
			String[] colList = dao
					.getColumnName("select * from jygl_xsjbxxb where 1=2"); // ������������
			if (StringUtils.isNotNull(xsxh)) {
				xsxh = xsxh.trim();
			}
			String[] rs = dao.getOneRs(sql, new String[] { xsxh }, colList); // ��ôӱ�ҵ��������Ϣ����ͼ���в�ѯ�ļ�¼��
			if (rs != null) {
				for (int i = 0; i < colList.length; i++) {
					map.put(colList[i].toLowerCase(), rs[i]); // ����¼ѭ������map��
				}
			}
			if ("12453".equals(xxdm)) {
				if (null != rs) {
					if (null != map.get("xldm")) {
						String xlsql = "select * from xldmb where xldm=?";
						rs = dao.getOneRs(xlsql,
								new String[] { map.get("xldm") },
								new String[] { "xlmc" });
						if (rs != null) {
							map.put("xlmc", rs[0]);
						}
					}
					map.put("xz", map.get("xzdm"));
				}
				map.put("byxx", "�й��Ͷ���ϵѧԺ");
				sql = "select sydq from dmk_sydq where sydqdm=?";
				String[] stuinfo = dao.getOneRs(sql, new String[] { map
						.get("sydqdm") }, new String[] { "sydq" });
				if (null != stuinfo) {
					map.put("syd", stuinfo[0]);
				}
			}
			if (null != map) {
				String xbdm = map.get("xbdm");
				if ("1".equals(xbdm)) {
					map.put("xb", "��");
				} else if ("2".equals(xbdm)) {
					map.put("xb", "Ů");
				}
			}
			sql = "select xymc from view_xsjbxx where xh=?";
			String[] xymc = dao.getOneRs(sql, new String[] { xsxh },
					new String[] { "xymc" });
			if (xymc != null) {
				map.put("xymc", xymc[0]);
			}
			sql = "select sydq from dmk_sydq where sydqdm=?";
			String[] stuinfo = dao.getOneRs(sql, new String[] { map
					.get("sydqdm") }, new String[] { "sydq" });
			if (null != stuinfo) {
				map.put("sydq", stuinfo[0]);
			}
		}
		// ------------end -----------
		map.put("stuExists", "yes");
		map.put("userType", userType);
		request.setAttribute("rs", map);
		
		if("save".equalsIgnoreCase(doType)){
			String lxdh = request.getParameter("lxdh"); // ��ϵ�绰
			map.put("lxdh", lxdh);
		}

		if ("bysjytjb".equals(bysjytj)) {
			request.setAttribute("rs1", map);
			lrh_commen_util commen_util = new lrh_commen_util();
			request.setAttribute("xyList", commen_util.get_xyList());
			request.setAttribute("zyList", commen_util.get_zyList(""));
			request.setAttribute("bjList", commen_util.get_bjList("", ""));
			return mapping.findForward("bysjytj");
		} else {
			return mapping.findForward("success");
		}

	}

	// ��ҵȥ���ѯҳ��򿪡���ѯ��ɾ��
	private ActionForward byqxQuery(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			String userType) throws Exception {
		DAO dao = DAO.getInstance(); // ʵ����ͨ�÷�������������ݿ�����
		CommanForm commanForm = (CommanForm) form;
		ArrayList<Object> rs = new ArrayList<Object>();
		String[] colList = null;
		String[] colListCN = null;
		String sql = ""; // sql���
		String querry = " where 1=1 "; // sql����
		String tableName = "view_jygl_byqx"; // ��ѯ��ϢԴ����Ϊ��ͼ����
		String realTable = "jygl_byqx"; // ��ѯ��ϢԴ��
		String rsNum = "0";// ���صļ�¼��
		String xxdm = StandardOperation.getXxdm();
		String dataType = request.getParameter("act"); // ������������
		String xsxh = request.getParameter("xsxh"); // ����ѧ��
		String name = DealString.toGBK(request.getParameter("name")); // ��������
		String xb = DealString.toGBK(request.getParameter("xb")); // �����Ա�
		String nj = DealString.toGBK(request.getParameter("nj")); // �꼶
		String xymc = DealString.toGBK(request.getParameter("xymc")); // ѧԺ����
		String zymc = DealString.toGBK(request.getParameter("zymc")); // רҵ����
		String bjmc = DealString.toGBK(request.getParameter("bjmc")); // �༶����
		String bynd = request.getParameter("bynd"); // ��ҵ���
		String xxsh = DealString.toGBK(request.getParameter("xxsh")); // ���

		String pk = "xsxh"; // ����
		HashMap<String, String> map = new HashMap<String, String>();
		String doType = request.getParameter("doType");
		String doType2 = request.getParameter("doType2");
		String pkValue = request.getParameter("pkValue");
		HttpSession session = request.getSession();
		String userName = session.getAttribute("userName").toString();

		if ("del".equals(doType2)) {
			boolean judge = false;
			judge = StandardOperation.delete(realTable, pk, pkValue, request);
			if (judge) {
				request.setAttribute("delete", "ok");

				String whensj = (dao
						.getOneRs(
								"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual",
								new String[] {}, new String[] { "sdate" }))[0]; // �ύʱ��

				StandardOperation.insert("JYGL_USERCZMXB",
						new String[] { "userid", "dowhat", "whichtable",
								"whichpk", "whensj" }, new String[] { userName,
								"ɾ��", "��ҵȥ���", "ѧ��:" + pkValue, whensj },
						request);
			} else {
				request.setAttribute("delete", "no");
			}
		}

		if (userType.equals("teacher")) {
			request.setAttribute("whichtype", "allteacher");
			sql = "select * from jygl_jyxywhb where username=?";
			String[] teainfo = dao.getOneRs(sql, new String[] { userName },
					new String[] { "usertype", "xymc", "zymc", "bj" });
			String tea = "";
			if (null != teainfo) {
				tea = teainfo[0];

			}
			if ("����Ա".equals(tea)) {
				map.put("xymc", teainfo[1]);
				map.put("zymc", teainfo[2]);
				map.put("bjmc", teainfo[3]);
				request.setAttribute("who", "fudaoyuan");
			} else {
				request.setAttribute("who", "teacher");
			}
		} else if ("student".equals(userType)) {
			map.put("xsxh", userName);
			request.setAttribute("whichtype", "student");
		}

		if (xxdm.equals(Globals.XXDM_ZZSF)) { // ����ʦ��
			String xydm = "";
			String zydm = "";
			sql = "select distinct xydm from view_njxyzybj where xymc=?";
			String[] xyinfo = dao.getOneRs(sql, new String[] { xymc },
					new String[] { "xydm" });
			if (null != xyinfo) {
				xydm = xyinfo[0];
			}
			sql = "select distinct zydm from view_njxyzybj where zymc=?";
			String[] zyinfo = dao.getOneRs(sql, new String[] { zymc },
					new String[] { "zydm" });
			if (null != zyinfo) {
				zydm = zyinfo[0];
			}

			// ���ϴ��ύ��ֵ����ȥ
			if ("query".equals(doType)) {
				map.put("xsxh", xsxh);
				map.put("name", name);
				map.put("xb", xb);
				map.put("nj", nj);
				map.put("xymc", xymc);
				map.put("zymc", zymc);
				map.put("bjmc", bjmc);
				map.put("bynd", bynd);
				map.put("xxsh", xxsh);
			}

			if (xsxh == null) {
				xsxh = "";
			}
			if (name == null) {
				name = "";
			}
			if (xb == null) {
				xb = "";
			}
			if (nj == null) {
				nj = "";
			}

			if (xymc == null) {
				xymc = "";
			}
			if (zymc == null) {
				zymc = "";
			}
			if (bjmc == null) {
				bjmc = "";
			}

			if (bynd == null) {
				bynd = "";
			}
			if (xxsh == null) {
				xxsh = "";
			}
			if ((xsxh == null) || xsxh.equals("")) {
				querry += "and 1=1 ";
			} else {
				querry += "and xsxh like '%" + xsxh + "%' ";
			}
			if ((name == null) || name.equals("")) {
				querry += "and 1=1 ";
			} else {
				querry += "and name like '%" + name + "%' ";
			}
			if ((nj == null) || nj.equals("")) {
				querry += "and 1=1 ";
			} else {
				querry += "and nj = '" + nj + "' ";
			}

			if ((xb == null) || xb.equals("")) {
				querry += " and 1=1 ";
			} else {
				querry += " and xb='" + xb + "' ";
			}
			if ((xymc == null) || xymc.equals("")) {
				querry += " and 1=1 ";
			} else {
				querry += " and xymc='" + xymc + "' ";
			}
			if ((zymc == null) || zymc.equals("")) {
				querry += " and 1=1 ";
			} else {
				querry += " and zymc='" + zymc + "' ";
			}
			if ((bjmc == null) || bjmc.equals("")) {
				querry += " and 1=1 ";
			} else {
				querry += " and bjmc='" + bjmc + "' ";
			}

			if ((bynd == null) || bynd.equals("")) {
				querry += " and 1=1 ";
			} else {
				querry += " and bynd='" + bynd + "' ";
			}
			if ((xxsh == null) || xxsh.equals("")) {
				querry += " and 1=1 ";
			} else {
				querry += " and xxsh='" + xxsh + "' ";
			}
			sql = "select " + pk + " ����,rownum r,a.* from " + tableName + " a "
					+ querry;
			colList = new String[] { "����", "r", "bynd", "name", "xsxh", "xb",
					"lxdh", "yddh", "tjsj", "xxsh" };
			colListCN = dao.getColumnNameCN(colList, tableName);
			List topTr = dao.arrayToList(colList, colListCN);

			rs.addAll(CommonQueryDAO.commonQuery(sql, "", new String[] {},
					colList, commanForm));
			if (rs == null) {
				rsNum = "0";
			} else {
				rsNum = String.valueOf(rs.size());
			}

			request.setAttribute("realTable", "jygl_byqx"); // ����ѧ��������Ϣ��
			request.setAttribute("njList", dao.getNjList()); // �����꼶�б�
			request.setAttribute("xyList", dao.getXyList());// ����ѧԺ�б�
			request.setAttribute("zyList", dao.getZyList(xydm));// ����רҵ�б�
			request.setAttribute("bjList", dao.getBjList(xydm, zydm, nj));// ����ѧԺ�б�
			request.setAttribute("act", dataType);// �������ݲ�ѯ����
			request.setAttribute("rs", rs);// �������ݼ�
			request.setAttribute("topTr", topTr);// ���ͱ�ͷ
			request.setAttribute("rsNum", rsNum);// ���ͼ�¼��
			request.setAttribute("rs1", map);
			return mapping.findForward("success2");

		}

		// ���ϴ��ύ��ֵ����ȥ
		if ("query".equals(doType)) {
			map.put("xsxh", xsxh);
			map.put("name", name);
			map.put("xb", xb);
			map.put("xymc", xymc);
			map.put("bynd", bynd);
			map.put("xxsh", xxsh);
		}

		if (xsxh == null) {
			xsxh = "";
		}
		if (name == null) {
			name = "";
		}
		if (xb == null) {
			xb = "";
		}
		if (xymc == null) {
			xymc = "";
		}
		if (bynd == null) {
			bynd = "";
		}
		if (xxsh == null) {
			xxsh = "";
		}
		if ((xsxh == null) || xsxh.equals("")) {
			querry += "and 1=1 ";
		} else {
			querry += "and xsxh like '%" + xsxh + "%' ";
		}
		if ((name == null) || name.equals("")) {
			querry += "and 1=1 ";
		} else {
			querry += "and name like '%" + name + "%' ";
		}
		if ((xb == null) || xb.equals("")) {
			querry += " and 1=1 ";
		} else {
			querry += " and xb='" + xb + "' ";
		}
		if ((xymc == null) || xymc.equals("")) {
			querry += " and 1=1 ";
		} else {
			querry += " and xymc='" + xymc + "' ";
		}

		if ((bynd == null) || bynd.equals("")) {
			querry += " and 1=1 ";
		} else {
			querry += " and bynd='" + bynd + "' ";
		}
		if ((xxsh == null) || xxsh.equals("")) {
			querry += " and 1=1 ";
		} else {
			querry += " and xxsh='" + xxsh + "' ";
		}

		if (xxdm.equals(Globals.XXDM_YNYS)) {// ��������
			colList = new String[] { "����", "�к�", "bynd", "name", "xsxh", "xb",
					"lxdh", "yddh", "tjsj" };
			sql = "select " + pk + " ����,rownum �к�,a.* from " + realTable
					+ " a " + querry;
		} else {
			colList = new String[] { "����", "r", "bynd", "name", "xsxh", "xb",
					"xymc", "lxdh", "yddh", "tjsj", "xxsh", "fdysh" };
			sql = "select " + pk + " ����,rownum r,a.* from " + realTable + " a "
					+ querry;
		}
		colListCN = dao.getColumnNameCN(colList, realTable);
		List topTr = dao.arrayToList(colList, colListCN);
	

		if (xxdm.equals(Globals.XXDM_YNYS)) {
			rs.addAll(dao.rsToVator(sql, new String[] {}, colList));

		} else {
			rs.addAll(CommonQueryDAO.commonQuery(sql, "", new String[] {},
					colList, commanForm));
		}
		if (rs == null) {
			rsNum = "0";
		} else {
			rsNum = String.valueOf(rs.size());
		}
		

		String usertype = request.getSession().getAttribute("userType")
				.toString();
		String bbmc = (String) request.getSession().getAttribute("bmmc");
		if ("xx".equals(usertype) || "admin".equals(usertype)) {
			request.setAttribute("ldgxusertype", "xx");
			request.setAttribute("who", "teacher");
		} else if ("xy".equals(usertype)) {
			request.setAttribute("ldgxusertype", "xy");
			request.setAttribute("who", "fudaoyuan");
			map.put("xymc", bbmc);
		}

		request.setAttribute("realTable", "jygl_byqx"); // ����ѧ��������Ϣ��
		request.setAttribute("xyList", dao.getXyList());// ����ѧԺ�б�
		request.setAttribute("act", dataType);// �������ݲ�ѯ����
		request.setAttribute("rs", rs);// �������ݼ�
		request.setAttribute("topTr", topTr);// ���ͱ�ͷ
		request.setAttribute("rsNum", rsNum);// ���ͼ�¼��
		request.setAttribute("rs1", map);

		if (!xxdm.equals(Globals.XXDM_YNYS)) {
			request.setAttribute("view", "yes");
		}

		request.setAttribute("path", "openbyqxqueryweb.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("success");
	}

	// ����鿴��ҵȥ�����ϸ��Ϣ
	private ActionForward byqxViewMoreQuery(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String pk = "xsxh";
		String sql = ""; // sql���
		String xxdm = StandardOperation.getXxdm();
		DAO dao = DAO.getInstance();
		// String realTable = "jygl_byqx";
		String tableName = "view_jygl_byqx";
		String doType = request.getParameter("doType");
		String pkValue = request.getParameter("pkValue");
		HashMap<String, String> map = new HashMap<String, String>();

		if ((doType == null) || doType.equalsIgnoreCase("")) {
			// �����쳣
			return mapping.findForward("false");
		} else if (doType.equalsIgnoreCase("view")) {
			// ��ѯ����
			sql = "select * from " + tableName + " where " + pk + "='"
					+ pkValue + "'";
			String[] colList = dao
					.getColumnName("select * from view_jygl_byqx where 1=2"); // ������������
			String[] stuinfo = dao.getOneRs(sql, new String[] {}, colList);
			HashMap<String,String>jyqxMap=dao.getMap(" select * from jygl_byqx where "+pk+"=? ",new String[]{pkValue},new String[]{"xgyj"});
			if (stuinfo != null) {
				for (int i = 0; i < colList.length; i++) {
					map.put(colList[i].toLowerCase(), stuinfo[i]); // ����¼ѭ������map��
				}

				String tjsj = null;
				String sjyear = null;
				String sjmon = null;
				String sjday = null;
				String sjhour = null;
				String sj = map.get("tjsj").toString();
				sjyear = sj.substring(0, 4);
				sjmon = sj.substring(4, 6);
				sjday = sj.substring(6, 8);
				sjhour = sj.substring(8, 10);
				tjsj = sjyear + "��" + sjmon + "��" + sjday + "�� " + sjhour + "ʱ";
				map.put("tjsj", tjsj);
				if (map.get("shsj") != null && !"".equals(map.get("shsj"))) {
					String shsj = null;
					sjyear = null;
					sjmon = null;
					sjday = null;
					sjhour = null;
					sj = map.get("shsj").toString();
					sjyear = sj.substring(0, 4);
					sjmon = sj.substring(4, 6);
					sjday = sj.substring(6, 8);
					sjhour = sj.substring(8, 10);
					shsj = sjyear + "��" + sjmon + "��" + sjday + "�� " + sjhour
							+ "ʱ";
					map.put("shsj", shsj);
				}
				map.putAll(jyqxMap);
			}
		}
		if (xxdm.equals(Globals.XXDM_ZZSF)) {// ����ʦ��
			request.setAttribute("rs", map);// �������ݼ�
			return mapping.findForward("success2");
		} else if (xxdm.equals(Globals.XXDM_YNYS)) {// ��������
			request.setAttribute("rs", map);// �������ݼ�
			return mapping.findForward("success3");
		}
		request.setAttribute("rs", map);// �������ݼ�
		return mapping.findForward("success");
	}

	// ��ҵȥ���޸�
	private ActionForward jyglByqxUpdate(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String pk = "xsxh";
		String sql = ""; // sql���
		DAO dao = DAO.getInstance();
		String xxdm = StandardOperation.getXxdm();
		String realTable = "jygl_byqx";
		String doType = request.getParameter("doType");
		String pkValue = request.getParameter("pkValue");
		HashMap<String, String> map = new HashMap<String, String>();
		String xsxh = request.getParameter("xsxh");
		String act = request.getParameter("act");

		sql = "select byqxdm,byqx from dmk_byqx";
		List byqxList = dao.getList(sql, new String[] {}, new String[] {
				"byqxdm", "byqx" });
		request.setAttribute("byqxList", byqxList);

		sql = "select * from  jygl_byqx where xsxh=?";
		String[] byqxinfo = dao.getOneRs(sql, new String[] { xsxh },
				new String[] { "xxsh" });
		String byqxin = "";
		if (null != byqxinfo) {
			byqxin = byqxinfo[0];
		}

		if ((doType == null) || doType.equalsIgnoreCase("")) {
			// �����쳣
			return mapping.findForward("false");
		} else if ("update".equalsIgnoreCase(doType)) {
			// ��ѯ����
			sql = "select * from " + realTable + " where " + pk + "='"
					+ pkValue + "'";
			String[] colList = dao
					.getColumnName("select * from jygl_byqx where 1=2"); // ������������
			String[] stuinfo = dao.getOneRs(sql, new String[] {}, colList);
			if (stuinfo != null) {
				for (int i = 0; i < colList.length; i++) {
					map.put(colList[i].toLowerCase(), stuinfo[i]); // ����¼ѭ������map��
				}
				String tjsj = null;
				String sjyear = null;
				String sjmon = null;
				String sjday = null;
				String sjhour = null;
				String sj = map.get("tjsj").toString();
				sjyear = sj.substring(0, 4);
				sjmon = sj.substring(4, 6);
				sjday = sj.substring(6, 8);
				sjhour = sj.substring(8, 10);
				tjsj = sjyear + "��" + sjmon + "��" + sjday + "�� " + sjhour + "ʱ";
				map.put("tjsj", tjsj);
				if (map.get("shsj") != null && !"".equals(map.get("shsj"))) {
					String shsj = null;
					sjyear = null;
					sjmon = null;
					sjday = null;
					sjhour = null;
					sj = map.get("shsj").toString();
					sjyear = sj.substring(0, 4);
					sjmon = sj.substring(4, 6);
					sjday = sj.substring(6, 8);
					sjhour = sj.substring(8, 10);
					shsj = sjyear + "��" + sjmon + "��" + sjday + "�� " + sjhour
							+ "ʱ";
					map.put("shsj", shsj);
				}
			}
		}

		if (xxdm.equals(Globals.XXDM_ZZSF)) { // ����ʦ��
			if ("update".equals(act)) {
				String byqx = DealString.toGBK(request.getParameter("byqx")); // ��ҵȥ��
				String lxdz = DealString.toGBK(request.getParameter("lxdz")); // ��ϵ��ַ
				String lxdh = request.getParameter("lxdh"); // ��ϵ�绰
				String yzbm = request.getParameter("yzbm"); // ��������
				String yddh = request.getParameter("yddh"); // �ƶ��绰
				String email = request.getParameter("email"); // ��������
				String dayjdz = DealString
						.toGBK(request.getParameter("dayjdz")); // �����ʼĵ�ַ
				String dajydh = DealString
						.toGBK(request.getParameter("dajydh")); // ������Ҫ����
				String ydjycrq = DealString.toGBK(request
						.getParameter("ydjycrq")); // �ʵ���ʴ�����
				String bdzh = DealString.toGBK(request.getParameter("bdzh")); // ����֤��
				String xysbh = DealString.toGBK(request.getParameter("xysbh")); // Э������
				
				boolean judge = false;

				if ("δͨ��X".equals(byqxin) || "δ���".equals(byqxin)) {
					judge = StandardOperation.update(realTable, new String[] {
							"byqx", "lxdz", "lxdh", "yzbm", "yddh", "email",
							"xxsh", "dayjdz", "dajydh", "ydjycrq", "bdzh",
							"xysbh" }, new String[] { byqx, lxdz, lxdh, yzbm,
							yddh, email, "δ���", dayjdz, dajydh, ydjycrq, bdzh,
							xysbh }, "xsxh", xsxh, request);
				}
				if (judge) {
					request.setAttribute("update", "ok");

					// �޸Ĳ����ļ�¼
					HttpSession session = request.getSession();
					String userName = session.getAttribute("userName")
							.toString();
					String whensj = (dao
							.getOneRs(
									"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual",
									new String[] {}, new String[] { "sdate" }))[0]; // �ύʱ��

					StandardOperation.insert("JYGL_USERCZMXB", new String[] {
							"userid", "dowhat", "whichtable", "whichpk",
							"whensj" }, new String[] { userName, "�޸�", "��ҵȥ���",
							"ѧ��:" + xsxh, whensj }, request);
				} else {
					request.setAttribute("update", "no");
				}

				sql = "select * from view_jygl_byqx where xsxh=?";
				String[] colList = dao
						.getColumnName("select * from view_jygl_byqx where 1=2"); // ������������
				String[] stuinfo = dao.getOneRs(sql, new String[] { xsxh },
						colList);
				if (stuinfo != null) {
					for (int i = 0; i < colList.length; i++) {
						map.put(colList[i].toLowerCase(), stuinfo[i]); // ����¼ѭ������map��
					}
					String tjsj = null;
					String sjyear = null;
					String sjmon = null;
					String sjday = null;
					String sjhour = null;
					String sj = map.get("tjsj").toString();
					sjyear = sj.substring(0, 4);
					sjmon = sj.substring(4, 6);
					sjday = sj.substring(6, 8);
					sjhour = sj.substring(8, 10);
					tjsj = sjyear + "��" + sjmon + "��" + sjday + "�� " + sjhour
							+ "ʱ";
					map.put("tjsj", tjsj);
					if (map.get("shsj") != null && !"".equals(map.get("shsj"))) {
						String shsj = null;
						sjyear = null;
						sjmon = null;
						sjday = null;
						sjhour = null;
						sj = map.get("shsj").toString();
						sjyear = sj.substring(0, 4);
						sjmon = sj.substring(4, 6);
						sjday = sj.substring(6, 8);
						sjhour = sj.substring(8, 10);
						shsj = sjyear + "��" + sjmon + "��" + sjday + "�� "
								+ sjhour + "ʱ";
						map.put("shsj", shsj);
					}
				}
			}

			request.setAttribute("rs", map);// �������ݼ�
			return mapping.findForward("success2");
		}

		if ("update".equals(act)) {
			String byqx = DealString.toGBK(request.getParameter("byqx")); // ��ҵȥ��
			String lxdz = DealString.toGBK(request.getParameter("lxdz")); // ��ϵ��ַ
			String lxdh = request.getParameter("lxdh"); // ��ϵ�绰
			String yzbm = request.getParameter("yzbm"); // ��������
			String yddh = request.getParameter("yddh"); // �ƶ��绰
			String email = request.getParameter("email"); // ��������
			String jydw = request.getParameter("jydw"); // Э������	
			boolean judge = false;

			if ("δͨ��X".equals(byqxin) || "δ���".equals(byqxin)
					|| "ֱ��ͨ��".equals(byqxin)) {

				if (xxdm.equals(Globals.XXDM_YNYS)) {
					judge = StandardOperation
							.update(realTable, new String[] { "byqx", "lxdz",
									"lxdh", "yzbm", "yddh", "email" },
									new String[] { byqx, lxdz, lxdh, yzbm,
											yddh, email }, "xsxh", xsxh,
									request);
				} else {
					judge = StandardOperation.update(realTable, new String[] {
							"byqx", "lxdz", "lxdh", "yzbm", "yddh", "email",
							"xxsh","jydw" }, new String[] { byqx, lxdz, lxdh, yzbm,
							yddh, email, "δ���" ,jydw}, "xsxh", xsxh, request);
				}
			}
			if (judge) {
				request.setAttribute("update", "ok");

				// �޸Ĳ����ļ�¼
				HttpSession session = request.getSession();
				String userName = session.getAttribute("userName").toString();
				String whensj = (dao
						.getOneRs(
								"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual",
								new String[] {}, new String[] { "sdate" }))[0]; // �ύʱ��

				StandardOperation.insert("JYGL_USERCZMXB",
						new String[] { "userid", "dowhat", "whichtable",
								"whichpk", "whensj" }, new String[] { userName,
								"�޸�", "��ҵȥ���", "ѧ��:" + xsxh, whensj }, request);
			} else {
				request.setAttribute("update", "no");
			}

			sql = "select * from jygl_byqx where xsxh=?";
			String[] colList = dao
					.getColumnName("select * from jygl_byqx where 1=2"); // ������������
			String[] stuinfo = dao
					.getOneRs(sql, new String[] { xsxh }, colList);
			if (stuinfo != null) {
				for (int i = 0; i < colList.length; i++) {
					map.put(colList[i].toLowerCase(), stuinfo[i]); // ����¼ѭ������map��
				}
				String tjsj = null;
				String sjyear = null;
				String sjmon = null;
				String sjday = null;
				String sjhour = null;
				String sj = map.get("tjsj").toString();
				sjyear = sj.substring(0, 4);
				sjmon = sj.substring(4, 6);
				sjday = sj.substring(6, 8);
				sjhour = sj.substring(8, 10);
				tjsj = sjyear + "��" + sjmon + "��" + sjday + "�� " + sjhour + "ʱ";
				map.put("tjsj", tjsj);
				if (map.get("shsj") != null && !"".equals(map.get("shsj"))) {
					String shsj = null;
					sjyear = null;
					sjmon = null;
					sjday = null;
					sjhour = null;
					sj = map.get("shsj").toString();
					sjyear = sj.substring(0, 4);
					sjmon = sj.substring(4, 6);
					sjday = sj.substring(6, 8);
					sjhour = sj.substring(8, 10);
					shsj = sjyear + "��" + sjmon + "��" + sjday + "�� " + sjhour
							+ "ʱ";
					map.put("shsj", shsj);
				}
			}
		}

		request.setAttribute("rs", map);// �������ݼ�
		if (xxdm.equals(Globals.XXDM_YNYS)) {// ��������
			return mapping.findForward("success3");
		}
		return mapping.findForward("success");
	}

	// ������ҵ����ѧУ��˴��ڲ��������
	private ActionForward jyglByqxSh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String pk = "xsxh";
		HttpSession session = request.getSession();
		String xxdm = StandardOperation.getXxdm();
		String sql = ""; // sql���
		DAO dao = DAO.getInstance();
		String realTable = "jygl_byqx";
		String tableName = "view_jygl_byqx";
		String doType = request.getParameter("doType");
		String pkValue = request.getParameter("pkValue");
		HashMap<String, String> map = new HashMap<String, String>();
		String xxsh = DealString.toGBK(request.getParameter("xxsh"));
		String xgyj = DealString.toGBK(request.getParameter("xgyj"));
		String xsxh = request.getParameter("xsxh");
		String act = request.getParameter("act");
		String shperson = session.getAttribute("userName").toString();
		String usertype = (String) request.getSession()
				.getAttribute("userType");

		if ("xx".equals(usertype) || "admin".equals(usertype)) {
			request.setAttribute("ldgxusertype", "xx");
			request.setAttribute("who", "teacher");
		} else if ("xy".equals(usertype)) {
			request.setAttribute("ldgxusertype", "xy");
			request.setAttribute("who", "fudaoyuan");
		}

		String fdysh = DealString.toGBK(request.getParameter("fdysh"));
		// String fdyshr = DealString.toGBK(request.getParameter("fdyshr"));

		if ("shenhe".equals(act)) {
			if ("12453".equals(xxdm)) {
				if (usertype == "xx" || "xx".equals(usertype)
						|| "admin".equals(usertype)) {

					String shsj = (dao
							.getOneRs(
									"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual", // ����Ա���ʱ��
									// //
									// ȡ�����ݿ���ʱ��
									new String[] {}, new String[] { "sdate" }))[0];
					if ("��ͨ����".equals(xxsh)) {
						// xgyj = "";
					}
					if ("δ���".equals(xxsh)) {
						shsj = "";
						xgyj = "";
						shperson = "";
					}
					sql = "update " + realTable + " set xxsh='" + xxsh
							+ "' , xgyj='" + xgyj + "' ,shsj='" + shsj
							+ "' ,shperson='" + shperson + "' where xsxh='"
							+ xsxh + "'";

					boolean judge = false;
					judge = dao.runUpdate(sql, new String[] {});

					if (judge) {
						request.setAttribute("shenhe", "ok");
					} else {
						request.setAttribute("shenhe", "no");
					}

					sql = "select * from " + realTable + " where xsxh=?";
					String[] colList = dao
							.getColumnName("select * from jygl_byqx where 1=2"); // ������������
					String[] stuinfo = dao.getOneRs(sql, new String[] { xsxh },
							colList);
					if (stuinfo != null) {
						for (int i = 0; i < colList.length; i++) {
							map.put(colList[i].toLowerCase(), stuinfo[i]); // ����¼ѭ������map��
						}
						shsj = null;
						String sjyear = null;
						String sjmon = null;
						String sjday = null;
						String sjhour = null;
						if (null != map.get("shsj")) {
							String sj = map.get("shsj").toString();
							sjyear = sj.substring(0, 4);
							sjmon = sj.substring(4, 6);
							sjday = sj.substring(6, 8);
							sjhour = sj.substring(8, 10);
							shsj = sjyear + "��" + sjmon + "��" + sjday + "��"
									+ sjhour + "ʱ";
							map.put("shsj", shsj);
						}
						if (null != map.get("tjsj")) {
							String sj = map.get("tjsj").toString();
							sjyear = sj.substring(0, 4);
							sjmon = sj.substring(4, 6);
							sjday = sj.substring(6, 8);
							sjhour = sj.substring(8, 10);
							String tjsj = sjyear + "��" + sjmon + "��" + sjday
									+ "��" + sjhour + "ʱ";
							map.put("tjsj", tjsj);
						}
					}
				} else {
					String shsj = (dao
							.getOneRs(
									"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual", // ����Ա���ʱ��
									// //
									// ȡ�����ݿ���ʱ��
									new String[] {}, new String[] { "sdate" }))[0];
					if ("��ͨ����".equals(xxsh)) {
						xgyj = "";
					}
					if ("δ���".equals(xxsh)) {
						shsj = "";
						xgyj = "";
						shperson = "";
					}
					sql = "update " + realTable + " set fdysh='" + fdysh
							+ "' ,fdyshsj='" + shsj + "' ,fdyshr='" + shperson
							+ "' where xsxh='" + xsxh + "'";

					boolean judge = false;
					judge = dao.runUpdate(sql, new String[] {});

					if (judge) {
						request.setAttribute("shenhe", "ok");
					} else {
						request.setAttribute("shenhe", "no");
					}

					sql = "select * from " + realTable + " where xsxh=?";
					String[] colList = dao
							.getColumnName("select * from jygl_byqx where 1=2"); // ������������
					String[] stuinfo = dao.getOneRs(sql, new String[] { xsxh },
							colList);
					if (stuinfo != null) {
						for (int i = 0; i < colList.length; i++) {
							map.put(colList[i].toLowerCase(), stuinfo[i]); // ����¼ѭ������map��
						}
						shsj = null;
						String sjyear = null;
						String sjmon = null;
						String sjday = null;
						String sjhour = null;
						if (null != map.get("shsj")) {
							String sj = map.get("shsj").toString();
							sjyear = sj.substring(0, 4);
							sjmon = sj.substring(4, 6);
							sjday = sj.substring(6, 8);
							sjhour = sj.substring(8, 10);
							shsj = sjyear + "��" + sjmon + "��" + sjday + "��"
									+ sjhour + "ʱ";
							map.put("shsj", shsj);
						}
						if (null != map.get("fdyshsj")) {
							String sj = map.get("fdyshsj").toString();
							sjyear = sj.substring(0, 4);
							sjmon = sj.substring(4, 6);
							sjday = sj.substring(6, 8);
							sjhour = sj.substring(8, 10);
							shsj = sjyear + "��" + sjmon + "��" + sjday + "��"
									+ sjhour + "ʱ";
							map.put("fdyshsj", shsj);
						}
						if (null != map.get("tjsj")) {
							String sj = map.get("tjsj").toString();
							sjyear = sj.substring(0, 4);
							sjmon = sj.substring(4, 6);
							sjday = sj.substring(6, 8);
							sjhour = sj.substring(8, 10);
							String tjsj = sjyear + "��" + sjmon + "��" + sjday
									+ "��" + sjhour + "ʱ";
							map.put("tjsj", tjsj);
						}
					}
				}
			} else {

				String shsj = (dao
						.getOneRs(
								"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual", // ����Ա���ʱ��
								// //
								// ȡ�����ݿ���ʱ��
								new String[] {}, new String[] { "sdate" }))[0];
				if ("��ͨ����".equals(xxsh)) {
					xgyj = request.getParameter("xgyj");
				}
				if ("δ���".equals(xxsh)) {
					shsj = "";
					xgyj = "";
					shperson = "";
				}
				sql = "update " + realTable + " set xxsh='" + xxsh
						+ "' , xgyj='" + xgyj + "' ,shsj='" + shsj
						+ "' ,shperson='" + shperson + "' where xsxh='" + xsxh
						+ "'";

				boolean judge = false;
				judge = dao.runUpdate(sql, new String[] {});

				if (judge) {
					request.setAttribute("shenhe", "ok");
				} else {
					request.setAttribute("shenhe", "no");
				}

				sql = "select * from " + realTable + " where xsxh=?";
				String[] colList = dao
						.getColumnName("select * from jygl_byqx where 1=2"); // ������������
				String[] stuinfo = dao.getOneRs(sql, new String[] { xsxh },
						colList);
				if (stuinfo != null) {
					for (int i = 0; i < colList.length; i++) {
						map.put(colList[i].toLowerCase(), stuinfo[i]); // ����¼ѭ������map��
					}
					shsj = null;
					String sjyear = null;
					String sjmon = null;
					String sjday = null;
					String sjhour = null;
					if (null != map.get("shsj")) {
						String sj = map.get("shsj").toString();
						sjyear = sj.substring(0, 4);
						sjmon = sj.substring(4, 6);
						sjday = sj.substring(6, 8);
						sjhour = sj.substring(8, 10);
						shsj = sjyear + "��" + sjmon + "��" + sjday + "��"
								+ sjhour + "ʱ";
						map.put("shsj", shsj);
					}
					if (null != map.get("tjsj")) {
						String sj = map.get("tjsj").toString();
						sjyear = sj.substring(0, 4);
						sjmon = sj.substring(4, 6);
						sjday = sj.substring(6, 8);
						sjhour = sj.substring(8, 10);
						String tjsj = sjyear + "��" + sjmon + "��" + sjday + "��"
								+ sjhour + "ʱ";
						map.put("tjsj", tjsj);
					}
					if (null != map.get("fdyshsj")) {
						String sj = map.get("fdyshsj").toString();
						sjyear = sj.substring(0, 4);
						sjmon = sj.substring(4, 6);
						sjday = sj.substring(6, 8);
						sjhour = sj.substring(8, 10);
						String tjsj = sjyear + "��" + sjmon + "��" + sjday + "��"
								+ sjhour + "ʱ";
						map.put("fdyshsj", tjsj);
					}
				}

			}
		}

		if ((doType == null) || doType.equalsIgnoreCase("")) {
			// �����쳣
			return mapping.findForward("false");
		} else if ("shenhe".equalsIgnoreCase(doType)) {
			// ��ѯ����
			sql = "select * from " + tableName + " where " + pk + "='"
					+ pkValue + "'";
			String[] colList = dao
					.getColumnName("select * from view_jygl_byqx where 1=2"); // ������������
			String[] stuinfo = dao.getOneRs(sql, new String[] {}, colList);
			HashMap<String,String>jyqxMap=dao.getMap(" select * from jygl_byqx where "+pk+"=? ",new String[]{pkValue},new String[]{"xgyj","xslb"});
			
			if ("ר����".equals(jyqxMap.get("xslb"))
					|| "������".equals(jyqxMap.get("xslb"))) {
				String pyfsinfo = dao.getOneRs("select a.*,(select pyfs from dmk_bzpyfs " +
						"b where a.pyfsdm=b.pyfsdm)pyfs  from jygl_xsjbxxb a where xsxh=? ",
						new String[]{pkValue},"pyfs");
				map.put("pyfs", pyfsinfo);
			} else if ("�о���".equals(jyqxMap.get("xslb"))) {
				String pyfsinfo = dao.getOneRs("select a.*,(select pyfs from dmk_yjspyfs " +
						"b where a.pyfsdm=b.pyfsdm)pyfs  from jygl_xsjbxxb a where xsxh=? ",
						new String[]{pkValue},"pyfs");
				map.put("pyfs", pyfsinfo);
			}
		
			if (stuinfo != null) {
				for (int i = 0; i < colList.length; i++) {
					map.put(colList[i].toLowerCase(), stuinfo[i]); // ����¼ѭ������map��
				}
				String shsj = null;
				String sjyear = null;
				String sjmon = null;
				String sjday = null;
				String sjhour = null;
				if (null != map.get("shsj")) {
					String sj = map.get("shsj").toString();
					sjyear = sj.substring(0, 4);
					sjmon = sj.substring(4, 6);
					sjday = sj.substring(6, 8);
					sjhour = sj.substring(8, 10);
					shsj = sjyear + "��" + sjmon + "��" + sjday + "��" + sjhour
							+ "ʱ";
					map.put("shsj", shsj);
				}
				if (null != map.get("tjsj")) {
					String sj = map.get("tjsj").toString();
					sjyear = sj.substring(0, 4);
					sjmon = sj.substring(4, 6);
					sjday = sj.substring(6, 8);
					sjhour = sj.substring(8, 10);
					String tjsj = sjyear + "��" + sjmon + "��" + sjday + "��"
							+ sjhour + "ʱ";
					map.put("tjsj", tjsj);
				}
				if (null != map.get("fdyshsj")) {
					String sj = map.get("fdyshsj").toString();
					sjyear = sj.substring(0, 4);
					sjmon = sj.substring(4, 6);
					sjday = sj.substring(6, 8);
					sjhour = sj.substring(8, 10);
					String tjsj = sjyear + "��" + sjmon + "��" + sjday + "��"
							+ sjhour + "ʱ";
					map.put("fdyshsj", tjsj);
				}
				map.putAll(jyqxMap);
			}
		}
		if (xxdm.equals(Globals.XXDM_ZZSF)) {
			request.setAttribute("rs", map);// �������ݼ�
			return mapping.findForward("success2");

		}
		request.setAttribute("rs", map);// �������ݼ�
		return mapping.findForward("success");
	}

	// �ļ��ϴ�
	private ActionForward jyglgetfile(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		// String uploadPath = "C:\\upload\\"; // ���ڴ���ϴ��ļ���Ŀ¼
		// String tempPath = "C:\\upload\\tmp\\"; // ���ڴ����ʱ�ļ���Ŀ¼
		//
		// DiskFileUpload fu = new DiskFileUpload();
		// // ��������ļ��ߴ磬������4MB
		// fu.setSizeMax(4194304);
		// // ���û�������С��������4kb
		// fu.setSizeThreshold(4096);
		// // ������ʱĿ¼��
		// fu.setRepositoryPath(tempPath);
		//
		// // �õ����е��ļ���
		// List fileItems = fu.parseRequest(request);
		// Iterator i = fileItems.iterator();
		// // ���δ���ÿһ���ļ���
		// while (i.hasNext()) {
		// FileItem fi = (FileItem) i.next();
		// // ����ļ���������ļ�������·����
		// String fileName = fi.getName();
		// if (fileName != null) {
		// // ��������Լ�¼�û����ļ���Ϣ
		// // ...
		// // д���ļ�a.txt����Ҳ���Դ�fileName����ȡ�ļ�����
		// fi.write(new File(uploadPath + "a.txt"));
		// }
		// }
		//
		// DealString deal = new DealString();
		// JyglwjscDao dao = new JyglwjscDao();
		// String upFile = request.getParameter("act");
		// String upFile2 = request.getParameter("act2");
		// String dir = deal.toGBK(request.getParameter("dir"));
		// dir = dir.replace('\\', '#');
		// dir = dir.substring(dir.lastIndexOf("#") + 1, dir.length());
		// String dir2 = deal.toGBK(request.getParameter("dir2"));
		// CommanForm commanForm = (CommanForm) form;
		// if (null != (upFile) && upFile.equalsIgnoreCase("up")) {
		// FormFile file = commanForm.getFile();
		// InputStream is = null;
		// boolean res = false;
		// OutputStream ou = new FileOutputStream(new File("d:/file/file.ddd"));
		// byte[] b = new byte[is.available()];
		// is.read(b);
		// ou.write(b);
		// ou.flush();
		//
		// int pos = dir.lastIndexOf(" . ");
		// dir.substring(pos);
		//
		// try {
		// is = file.getInputStream();
		// } catch (IOException e) {
		// request.setAttribute("res", "�ļ���ȡ����" + e.toString());
		// return mapping.findForward("success");
		// }
		//
		// if (upFile.equalsIgnoreCase("up")) {
		// String fType = file.getFileName();
		// fType = fType.substring(fType.lastIndexOf(".") + 1, fType
		// .length());
		// res = dao.getMFile("", file.getFileName(), dir, String
		// .valueOf(file.getFileSize()));
		// if (file.getFileName().equals(null)
		// || file.getFileName().equals("")) {
		// request.setAttribute("res", "que");
		// } else if (res) {
		// request.setAttribute("res", "ok");
		// } else {
		// request.setAttribute("res", "no");
		// }
		// }
		// }
		//
		// if (null != (upFile2) && upFile2.equalsIgnoreCase("up")) {
		// FormFile file2 = commanForm.getFile2();
		// InputStream is = null;
		// boolean res = false;
		// try {
		// is = file2.getInputStream();
		// } catch (IOException e) {
		// request.setAttribute("res", "�ļ���ȡ����" + e.toString());
		// return mapping.findForward("success");
		// }
		//
		// if (upFile2.equalsIgnoreCase("up")) {
		// String fType = file2.getFileName();
		// fType = fType.substring(fType.lastIndexOf(".") + 1, fType
		// .length());
		// res = dao.getMFile2("", file2.getFileName(), dir2, String
		// .valueOf(file2.getFileSize()));
		// if (file2.getFileName().equals(null)
		// || file2.getFileName().equals("")) {
		// request.setAttribute("res", "que");
		// } else if (res) {
		// request.setAttribute("res", "ok");
		// } else {
		// request.setAttribute("res", "no");
		// }
		// }
		// }
		// response.reset();
		// List fjList = dao.getMyFile();
		// List fjList2 = dao.getMyFile2();
		// request.setAttribute("fjList", fjList);
		// request.setAttribute("fjList2", fjList2);

		// ����д
		// DealString deal = new DealString();
		// DAO dao = DAO.getInstance();
		//		
		// String path = "files"; //�ļ�Ŀ��Ŀ¼
		// String tempPath ="C:\\TEMP";
		// String sql="insert into jswjsc
		// (id,name,filesize,path,recordid,fjtype,addtime) values
		// (S_ACCESSORY_ID.NEXTVAL,?,?,?,?,?,to_char(sysdate,'YYYY-MM-DD/HH24:MI:ss'))";
		// HttpServletRequest fileuploadRequest = null; //��ȡ�ϴ�����
		// FileTool fileTool = new FileTool();
		// fileTool.mkdirs(tempPath);
		// CommanForm commanForm = (CommanForm) form;
		// FormFile file = commanForm.getFile();
		// String dir = deal.toGBK(request.getParameter("dir"));//���Դ�ļ�·��
		//        
		//        
		//        
		// Iterator iter = null; //����
		// DiskFileUpload fu = new DiskFileUpload();
		// List fileItems = fu.parseRequest(fileuploadRequest);
		// iter = fileItems.iterator();
		//        
		// fu.setSizeMax(new Long("104857600")); //�ļ���С������10M
		//		
		// FileItem item = (FileItem)iter.next();
		//        
		// String fileName = file.getFileName();
		// String filename1=fileName.substring(0, fileName.lastIndexOf("."));
		// //.ǰ����ļ���
		// String filetype =fileName.substring(fileName.lastIndexOf(".")+1,
		// fileName.length()); //�ļ�����
		// java.util.Date dt=new java.util.Date(); //�ϴ�����
		// long lg=dt.getTime();
		// Long ld=new Long(lg);
		// String filename2=ld.toString(); //���ϴ�������Ϊ�ļ�����һ����
		// //���Ͽ��Կ����ļ�������������� �ļ��� ϵͳʱ�� �ļ�����
		// String wjdx = String.valueOf(file.getFileSize());
		// //����õ�int���ļ���Сת����string��
		// item.write(new File(tempPath +"\\" + filename2+"."+filetype));
		// dao.runUpdate(sql, new String[]{filename1,wjdx,path+"/"+
		// filename2+"."+filetype,filetype});
		// //�ļ��� �ļ���С ·�� ��¼�� ����

		return mapping.findForward("success");
	}

	// �ļ�����
	// private ActionForward jygldownfile(ActionMapping mapping, ActionForm
	// form,
	// HttpServletRequest request, HttpServletResponse response)
	// throws Exception {
	// String val = request.getParameter("val");
	//
	// HashMap<String, String> map = new HashMap<String, String>();
	// map.put("xls", "application/vnd.ms-excel");
	// map.put("doc", "application/msword");
	// map.put("txt", "text/plain");
	// if (act.equalsIgnoreCase("upInfo.do")) {
	// String wjm = dao.getDFile(val);
	// String cType = "";
	// if (Base.isNull(wjm)) {
	// cType = "unknown";
	// } else {
	// cType = map.get(wjm);
	// }
	// BLOB blob = dao.getDFile1(val);
	// response.reset();
	// response.setContentType(cType);
	// OutputStream os = response.getOutputStream();
	// os.write(blob.getBytes(1L, (int) blob.length()));
	// }
	// return mapping.findForward("success");
	// }

	// �ļ�ɾ��
	private ActionForward jygldelfile(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JyglwjscDao dao = new JyglwjscDao();
		String act = request.getParameter("act");

		boolean judge = false;
		if (act.equals("del")) {
			String wjname = DealString.toGBK(request.getParameter("wjname"));
			judge = StandardOperation
					.delete("scwjb", "wjname", wjname, request);
			if (judge) {
				request.setAttribute("act", "ok");
			} else {
				request.setAttribute("act", "no");
			}
		} else if (act.equals("del2")) {
			String wjname = DealString.toGBK(request.getParameter("wjname"));
			judge = StandardOperation.delete("scwjb2", "wjname", wjname,
					request);
			if (judge) {
				request.setAttribute("act", "ok");
			} else {
				request.setAttribute("act", "no");
			}
		}

		List fjList = dao.getMyFile();
		List fjList2 = dao.getMyFile2();
		request.setAttribute("fjList", fjList);
		request.setAttribute("fjList2", fjList2);
		return mapping.findForward("success");
	}

	// ��ʦ�ļ�����
	private ActionForward jyglteafiledown(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		JyglwjscDao dao = new JyglwjscDao();

		List fjList = dao.getMyFile();
		request.setAttribute("fjList", fjList);
		String act = request.getParameter("act");
		if ("down".equals(act)) {
			String dir = DealString.toGBK(request.getParameter("dir"));
			String filename = request.getParameter("filename");
			FileTool fileTool = new FileTool();
			fileTool.downFile(response, dir, filename);
		}
		return mapping.findForward("success");
	}

	// ѧ���ļ�����
	private ActionForward jyglstufiledown(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		JyglwjscDao dao = new JyglwjscDao();

		List fjList2 = dao.getMyFile2();
		request.setAttribute("fjList2", fjList2);
		String act = request.getParameter("act");
		if ("down".equals(act)) {
			String dir = DealString.toGBK(request.getParameter("dir"));
			String filename = DealString
					.toGBK(request.getParameter("filename"));
			FileTool fileTool = new FileTool();
			fileTool.downFile(response, dir, filename);
		}
		return mapping.findForward("success");
	}

	// �����ļ�������ҳ����ʾ
	private ActionForward showzcwjlist(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			String userType) throws Exception {

		String tableName = "jygl_zcwjb";// ����Դ��-�����ļ���
		String[] colList = null;// �����ֶ�
		String[] colListCN = null; // ��Ӧ�����ֶ���
		// ��ѯ�������ݵ�sql���
		SxjyDAO dao = new SxjyDAO();
		HashMap<String, String> map = new HashMap<String, String>();
		String delone = DealString.toGBK(request.getParameter("delone"));
		delone = delone.replaceAll(":", "").replaceAll("-", "").replaceAll(" ",
				"");
		HttpSession session = request.getSession();
		String wjbt = DealString.toGBK(request.getParameter("wjbt")); // �ļ�����
		String wjnr = DealString.toGBK(request.getParameter("wjnr")); // �ļ�����
		String wjlx = DealString.toGBK(request.getParameter("wjlx")); // �ļ�����
		String fbr = (String) session.getAttribute("userName"); // ������
		String act = request.getParameter("act");
		String userName = (String) session.getAttribute("userName");

		String fbsj = (dao.getOneRs(
				"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual", // ����ʱ��
				// //
				// ȡ�����ݿ���ʱ��
				new String[] {}, new String[] { "sdate" }))[0];
		if ("save".equals(act)) {
			if (!"".equals(wjlx) && null != wjlx) {
				String sql = "insert into jygl_zcwjb(wjbt,wjlx,fbr,fbsj) values('"
						+ wjbt
						+ "','"
						+ wjlx
						+ "','"
						+ fbr
						+ "','"
						+ fbsj
						+ "')";
				// sqlclob = "update jygl_zcwjb set wjnr=?";
				boolean judge = false;
				judge = StandardOperation.insert(tableName, new String[] {
						"wjbt", "wjlx", "wjnr", "fbr", "fbsj" }, new String[] {
						wjbt, wjlx, wjnr, fbr, fbsj }, request);
				if (judge) {
					request.setAttribute("inserted", "ok");

					// ��Ӳ����ļ�¼
					if ("teacher".equals(userType)) {
						String whensj = (dao
								.getOneRs(
										"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual",
										new String[] {},
										new String[] { "sdate" }))[0]; // �ύʱ��

						StandardOperation.insert("JYGL_USERCZMXB",
								new String[] { "userid", "dowhat",
										"whichtable", "whichpk", "whensj" },
								new String[] { userName, "����", "�����ļ���",
										"�ļ�����:" + wjbt, whensj }, request);
					}

				} else {
					request.setAttribute("inserted", "no");
				}
			} else {
				request.setAttribute("inserted", "que");
			}
		}
		if ("del".equals(act)) {
			boolean judge = false;
			judge = StandardOperation
					.delete(tableName, "fbsj", delone, request);
			if (judge) {
				request.setAttribute("del", "ok");

				// ɾ�������ļ�¼

				String whensj = (dao
						.getOneRs(
								"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual",
								new String[] {}, new String[] { "sdate" }))[0]; // �ύʱ��

				judge = StandardOperation.insert("JYGL_USERCZMXB",
						new String[] { "userid", "dowhat", "whichtable",
								"whichpk", "whensj" }, new String[] { userName,
								"ɾ��", "�����ļ���", "����ʱ��:" + delone, whensj },
						request);
			} else {
				request.setAttribute("del", "no");
			}
		}

		List<HashMap<String, String>> wjlxList = dao.getList(
				"select * from dmk_zcwjlxb", new String[] {}, new String[] {
						"lxdm", "lxmc" });

		StringBuilder sql = new StringBuilder();
		sql
				.append("select a.wjbt,(select lxmc from dmk_zcwjlxb where lxdm=a.wjlx) lxmc,wjlx,a.fbr,a.readtimes,");
		sql
				.append("to_char(to_date(fbsj,'yyyymmddhh24miss'),'yyyy-mm-dd hh24:mi:ss') fbsj from jygl_zcwjb a ");
		sql
				.append("where exists (select 1 from dmk_zcwjlxb where lxdm=a.wjlx) order by lxmc,fbsj desc");

		colList = new String[] { "wjbt", "wjlx", "lxmc", "fbr", "readtimes",
				"fbsj" };
		colListCN = new String[] { "�ļ�����", "�ļ�����", "������", "�������", "����ʱ��" };
		List topTr = dao.arrayToList(colList, colListCN);
		List<HashMap<String, String>> rs = dao.getList(sql.toString(),
				new String[] {}, colList);

		int m = 0;
		List<HashMap<String, String>> temp = new ArrayList<HashMap<String, String>>();

		for (int i = 0; i < rs.size(); i++) {
			String currLxmc = rs.get(i).get("wjlx");
			String nextLxmc = i == rs.size() - 1 ? "" : rs.get(i + 1).get(
					"wjlx");

			if (currLxmc.equals(nextLxmc)) {
				m++;
			} else {
				m = 0;
			}
			if (m > 2) {
				temp.add(rs.get(i + 1));
			}
		}

		rs.removeAll(temp);

		request.setAttribute("wjlxList", wjlxList);
		request.setAttribute("topTr", topTr);
		request.setAttribute("rs", rs);

		/*
		 * // ���Ҽ��ļ���� sql = "select " + pk + " ����,rownum �к�,a.* from " +
		 * tableName + " a " + "where wjlx='���Ҽ��ļ�'"; colList = new String[] {
		 * "����", "�к�", "wjbt", "wjlx", "fbr", "readtimes", "fbsj" }; colListCN =
		 * dao.getColumnNameCN(colList, tableName); List topTr1 =
		 * dao.arrayToList(colList, colListCN); rs1.addAll(dao.rsToVator(sql,
		 * new String[] {}, colList));
		 *  // �м��ļ���� sql = "select " + pk + " ����,rownum �к�,a.* from " +
		 * tableName + " a " + "where wjlx='�м��ļ�'"; colList = new String[] {
		 * "����", "�к�", "wjbt", "wjlx", "fbr", "readtimes", "fbsj" }; colListCN =
		 * dao.getColumnNameCN(colList, tableName); List topTr2 =
		 * dao.arrayToList(colList, colListCN); rs2.addAll(dao.rsToVator(sql,
		 * new String[] {}, colList));
		 *  // ѧ���ļ���� sql = "select " + pk + " ����,rownum �к�,a.* from " +
		 * tableName + " a " + "where wjlx='ѧ���������ļ�' or wjlx='��ҵ����ҵָ�������ļ�'" + "
		 * or wjlx='����У���ļ�' or wjlx='У���ļ�'"; colList = new String[] { "����",
		 * "�к�", "wjbt", "wjlx", "fbr", "readtimes", "fbsj" }; colListCN =
		 * dao.getColumnNameCN(colList, tableName); List topTr3 =
		 * dao.arrayToList(colList, colListCN); rs3.addAll(dao.rsToVator(sql,
		 * new String[] {}, colList));
		 *  // ������ sql = "select " + pk + " ����,rownum �к�,a.* from " + tableName + "
		 * a " + "where wjlx='������'"; colList = new String[] { "����", "�к�",
		 * "wjbt", "wjlx", "fbr", "readtimes", "fbsj" }; colListCN =
		 * dao.getColumnNameCN(colList, tableName); List topTr4 =
		 * dao.arrayToList(colList, colListCN); rs4.addAll(dao.rsToVator(sql,
		 * new String[] {}, colList));
		 *  // У԰ר����Ƹ�� sql = "select " + pk + " ����,rownum �к�,a.* from " +
		 * tableName + " a " + "where wjlx='У԰ר����Ƹ��'"; colList = new String[] {
		 * "����", "�к�", "wjbt", "wjlx", "fbr", "readtimes", "fbsj" }; colListCN =
		 * dao.getColumnNameCN(colList, tableName); List topTr5 =
		 * dao.arrayToList(colList, colListCN); rs5.addAll(dao.rsToVator(sql,
		 * new String[] {}, colList));
		 *  // ��Դ���� sql = "select " + pk + " ����,rownum �к�,a.* from " + tableName + "
		 * a " + "where wjlx='��Դ����'"; colList = new String[] { "����", "�к�",
		 * "wjbt", "wjlx", "fbr", "readtimes", "fbsj" }; colListCN =
		 * dao.getColumnNameCN(colList, tableName); List topTr6 =
		 * dao.arrayToList(colList, colListCN); rs6.addAll(dao.rsToVator(sql,
		 * new String[] {}, colList));
		 * 
		 * request.setAttribute("topTr1", topTr1);// ���ͱ�ͷ(����)
		 * request.setAttribute("topTr2", topTr2);// ���ͱ�ͷ(��)
		 * request.setAttribute("topTr3", topTr3);// ���ͱ�ͷ(ѧУ)
		 * request.setAttribute("topTr4", topTr4);// ���ͱ�ͷ(������)
		 * request.setAttribute("topTr5", topTr5);// ���ͱ�ͷ(У԰ר����Ƹ��)
		 * request.setAttribute("topTr6", topTr6);// ���ͱ�ͷ(��Դ����) //
		 * request.setAttribute("rs1", rs1);// �������ݼ�(����)
		 * request.setAttribute("rs2", rs2);// �������ݼ�(��)
		 * request.setAttribute("rs3", rs3);// �������ݼ�(ѧУ)
		 * request.setAttribute("rs4", rs4);// �������ݼ�(������)
		 * request.setAttribute("rs5", rs5);// �������ݼ�(У԰ר����Ƹ��)
		 * request.setAttribute("rs6", rs6);// �������ݼ�(��Դ����)
		 */
		request.setAttribute("map", map);
		request.setAttribute("path", "zcwjfb.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("success");
	}

	// �����ļ�����ϸ��Ϣ
	private ActionForward showmorezcwj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DAO dao = DAO.getInstance();

		String sql = "";
		String[] tit = { "wjbt", "fbr", "fbsj" };
		String tableName = "JYGL_ZCWJB";
		String wjbt = DealString.toGBK(request.getParameter("wjbt"));
		HashMap<String, String> map = new HashMap<String, String>();

		sql = "select * from " + tableName + " where wjbt=?";
		String[] zcwj = dao.getOneRs(sql, new String[] { wjbt }, tit);
		if (zcwj != null) {
			for (int i = 0; i < tit.length; i++) {
				map.put(tit[i].toLowerCase(), zcwj[i]); // ����¼ѭ������map��
			}
		}
		CLOB clob = dao.getOneClob(sql, new String[] { wjbt }, "wjnr");
		if (null != clob) {
			map.put("wjnr", clob.getSubString(1L, (int) clob.length()));
		}
		sql = "select readtimes from " + tableName + " where wjbt=?";
		String[] readtimes = dao.getOneRs(sql, new String[] { wjbt },
				new String[] { "readtimes" });
		if (null != readtimes) {
			int timeint = Integer.parseInt(readtimes[0]);
			timeint += 1;
			String tinestr = String.valueOf(timeint);
			StandardOperation.update(tableName, new String[] { "readtimes" },
					new String[] { tinestr }, "wjbt", wjbt, request);
		}

		request.setAttribute("rs", map);
		return mapping.findForward("success");
	}

	// �����ļ��޸�
	private ActionForward zcwjupdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DAO dao = DAO.getInstance();
		String tableName = "jygl_zcwjb";
		String updateone = DealString.toGBK(request.getParameter("updateone"));
		updateone = updateone.replaceAll(":", "").replaceAll("-", "")
				.replaceAll(" ", "");
		String sql = "";
		HashMap<String, String> map = new HashMap<String, String>();
		String act = request.getParameter("act");

		sql = "select * from jygl_zcwjb where fbsj=?";
		String[] colList = dao
				.getColumnName("select * from jygl_zcwjb where 1=2"); // ������������
		String[] zcwjinfo = dao.getOneRs(sql, new String[] { updateone },
				colList);
		if (zcwjinfo != null) {
			for (int i = 0; i < colList.length; i++) {
				map.put(colList[i].toLowerCase(), zcwjinfo[i]); // ����¼ѭ������map��
			}
		}
		request.setAttribute("rs", map);

		if ("update".equals(act)) {
			String wjbt = DealString.toGBK(request.getParameter("wjbt"));
			String wjlx = DealString.toGBK(request.getParameter("wjlx"));
			String wjnr = DealString.toGBK(request.getParameter("wjnr"));
			boolean judge = false;
			judge = StandardOperation.update(tableName, new String[] { "wjlx",
					"wjnr" }, new String[] { wjlx, wjnr }, "wjbt", wjbt,
					request);
			if (judge) {
				request.setAttribute("update", "ok");

				// �޸Ĳ����ļ�¼
				HttpSession session = request.getSession();
				String userName = session.getAttribute("userName").toString();
				String whensj = (dao
						.getOneRs(
								"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual",
								new String[] {}, new String[] { "sdate" }))[0]; // �ύʱ��

				StandardOperation.insert("JYGL_USERCZMXB",
						new String[] { "userid", "dowhat", "whichtable",
								"whichpk", "whensj" }, new String[] { userName,
								"�޸�", "�����ļ���", "�ļ�����:" + wjbt, whensj },
						request);
			} else {
				request.setAttribute("update", "no");
			}

			sql = "select * from jygl_zcwjb where wjbt=?";
			String[] colList1 = dao
					.getColumnName("select * from jygl_zcwjb where 1=2"); // ������������
			String[] zcwjinfo1 = dao.getOneRs(sql, new String[] { wjbt },
					colList1);
			if (zcwjinfo1 != null) {
				for (int i = 0; i < colList1.length; i++) {
					map.put(colList1[i].toLowerCase(), zcwjinfo1[i]); // ����¼ѭ������map��
				}
			}

			request.setAttribute("rs", map);

		}
		List<HashMap<String, String>> wjlxList = dao.getList(
				"select * from dmk_zcwjlxb", new String[] {}, new String[] {
						"lxdm", "lxmc" });

		request.setAttribute("wjlxList", wjlxList);
		return mapping.findForward("success");
	}

	// �����ļ���ѯ
	private ActionForward zcwjquery(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws SQLException {

		DAO dao = DAO.getInstance();
		CommanForm myForm = (CommanForm) form;
		ArrayList<Object> rs = new ArrayList<Object>();
		String[] colList = null;
		String[] colListCN = null;
		String sql = "";
		String pk = "wjbt"; // ����
		String tableName = "jygl_zcwjb";
		String rsNum = "0"; // ���صļ�¼��
		String wjbt = DealString.toGBK(request.getParameter("wjbt")); // �ļ�����
		String wjlx = DealString.toGBK(request.getParameter("wjlx")); // �ļ�����
		String fbr = request.getParameter("fbr"); // ������

		// ���ڷ���ʱ��Ĵ��� ���ڲ�ѯ����
		String xjsj = DealString.toGBK(request.getParameter("xjsj")); // ���ʱ��

		String nowsj = (dao.getOneRs(
				"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual", // ����ʱ��
				// //
				// ȡ�����ݿ���ʱ��
				new String[] {}, new String[] { "sdate" }))[0];
		String sjyear = nowsj.substring(0, 4);
		String sjmonth = nowsj.substring(4, 6);
		String sjday = nowsj.substring(6, 8);
		String sjqt = nowsj.substring(8, 14);
		String nowsj1 = sjyear + "-" + sjmonth + "-" + sjday;
		dealDate dealdate = new dealDate();
		String beforesj = "";
		// Integer.valueOf(xjsj)
		if (!"".equals(xjsj)) {
			beforesj = dealdate.getDate2(Integer.parseInt(xjsj), nowsj1);
			beforesj = beforesj.replaceAll("-", "") + sjqt;
		}

		String querry = " where 1=1 ";
		HashMap<String, String> map = new HashMap<String, String>();
		String doType = request.getParameter("doType");

		if ("query".equals(doType)) {
			map.put("wjbt", wjbt);
			map.put("wjlx", wjlx);
			map.put("fbr", fbr);
			map.put("xjsj", xjsj);
		}

		if (wjbt == null) {
			wjbt = "";
		}
		if (wjlx == null) {
			wjlx = "";
		}
		if (fbr == null) {
			fbr = "";
		}
		if (xjsj == null) {
			xjsj = "";
		}
		if ((wjbt == null) || wjbt.equals("")) {
			querry += "and 1=1 ";
		} else {
			querry += "and wjbt like '%" + wjbt + "%' ";
		}
		if ((wjlx == null) || wjlx.equals("")) {
			querry += "and 1=1 ";
		} else {
			querry += "and wjlx = '" + wjlx + "' ";
		}
		if ((fbr == null) || fbr.equals("")) {
			querry += "and 1=1 ";
		} else {
			querry += "and fbr = '" + fbr + "' ";
		}
		if ((xjsj == null) || xjsj.equals("")) {
			querry += "and 1=1 ";
		} else {
			querry += "and (fbsj between '" + beforesj + "' and '" + nowsj
					+ "') ";
		}

		Pages page = myForm.getPages();
		page.setMaxRecord(Integer.parseInt(dao.getOneRs(
				"select count(*) count from jygl_zcwjb " + querry,
				new String[] {}, "count")));

		StringBuilder sb = new StringBuilder();

		sb.append("select * from (select wjbt ����,rownum r,a.*,");
		sb.append("(select lxmc from dmk_zcwjlxb where lxdm=a.wjlx) lxmc ");
		sb.append("from jygl_zcwjb a ");
		sb.append(querry);
		sb.append(") where r > ");
		sb.append(page.getStart());
		sb.append(" and r <= ");
		sb.append((page.getStart() + page.getPageSize()));
		sql = sb.toString();
		colList = new String[] { "����", "r", "wjbt", "lxmc", "fbr", "fbsj",
				"readtimes" };
		colListCN = new String[] { "����", "�к�", "�ļ�����", "�ļ�����", "������", "����ʱ��",
				"�������" };
		List topTr = dao.arrayToList(colList, colListCN);
		if ((request.getParameter("act") != null)
				&& request.getParameter("act").equalsIgnoreCase("go")) {
			rs.addAll(dao.rsToVator(sql, new String[] {}, colList));
			if (rs == null) {
				rsNum = "0";
			} else {
				rsNum = String.valueOf(rs.size());
			}
		}

		request.setAttribute("rs", rs);// �������ݼ�
		request.setAttribute("topTr", topTr);// ���ͱ�ͷ
		request.setAttribute("rsNum", rsNum);// ���ͼ�¼��
		request.setAttribute("rs1", map);// �Ѳ�ѯ��������ȥ

		// String sql = "";
		// String[] tit = { "wjbt", "fbr", "fbsj" };
		//
		// String wjbt = deal.toGBK(request.getParameter("wjbt"));
		// HashMap<String, String> map = new HashMap<String, String>();
		//
		// sql = "select * from jygl_zcwjb" + " where wjbt=?";
		// String[] zcwj = dao.getOneRs(sql, new String[] { wjbt }, tit);
		// if (zcwj != null) {
		// for (int i = 0; i < tit.length; i++) {
		// map.put(tit[i].toLowerCase(), zcwj[i]); // ����¼ѭ������map��
		// }
		// }
		// CLOB clob = dao.getOneClob(sql, new String[] { wjbt }, "wjnr");
		// if (null != clob) {
		// map.put("wjnr", clob.getSubString(1L, (int) clob.length()));
		// }
		// request.setAttribute("rs", map);
		List<HashMap<String, String>> wjlxList = dao.getList(
				"select * from dmk_zcwjlxb", new String[] {}, new String[] {
						"lxdm", "lxmc" });

		request.setAttribute("wjlxList", wjlxList);

		return mapping.findForward("success");
	}

	// ���ݵ���
	private ActionForward jyglexpData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String tableName = request.getParameter("tableName");
		String sql = "";
		String zd = "";
		String querry = " where 1=1";
		DAO dao = DAO.getInstance();
		String xxdm = dao.getXxdm();
		if ("dwlfxxdjb".equals(tableName)) {
			if (xxdm.equals(Globals.XXDM_ZGDZDX)) {
				zd = " * ";
			}
		}
		if ("jygl_xsjbxxb".equals(tableName)) {
			if (xxdm.equals(Globals.XXDM_YNYS)) {
				zd = " * ";
				String xsxh = request.getParameter("xsxh"); // ����ѧ��
				String name = DealString.toGBK(request.getParameter("name")); // ��������
				String xb = request.getParameter("xb"); // �����Ա����
				String xymc = DealString.toGBK(request.getParameter("xymc")); // ����ѧԺ����
				String nd = request.getParameter("nd"); // �������
				String bynd = request.getParameter("bynd"); // ��ҵ���

				if (xsxh == null) {
					xsxh = "";
				}
				if (name == null) {
					name = "";
				}
				if (xb == null) {
					xb = "";
				}
				if (xymc == null) {
					xymc = "";
				}
				if (nd == null) {
					nd = "";
				}
				if (bynd == null) {
					bynd = "";
				}
				if ((xsxh == null) || xsxh.equals("")) {
					querry += "and 1=1 ";
				} else {
					querry += "and xsxh like '%" + xsxh + "%' ";
				}
				if ((name == null) || name.equals("")) {
					querry += "and 1=1 ";
				} else {
					querry += "and name like '%" + name + "%' ";
				}
				if ((xb == null) || xb.equals("")) {
					querry += " and 1=1 ";
				} else {
					querry += " and xb='" + xb + "' ";
				}
				if ((xymc == null) || xymc.equals("")) {
					querry += " and 1=1 ";
				} else {
					querry += " and xsxh in (select xsxh from jygl_xsjbxxb where xsxh in (select xh from view_xsjbxx where xymc ='"
							+ xymc + "'))";
				}
				if ((nd == null) || nd.equals("")) {
					querry += " and 1=1 ";
				} else {
					querry += " and nd='" + nd + "' ";
				}
				if ((bynd == null) || bynd.equals("")) {
					querry += " and 1=1 ";
				} else {
					querry += " and bynd='" + bynd + "' ";
				}
			}

			zd = " * ";
			String xsxh = request.getParameter("xsxh"); // ����ѧ��
			String name = DealString.toGBK(request.getParameter("name")); // ��������
			String xbdm = request.getParameter("xbdm"); // �����Ա����
			String xslb = DealString.toGBK(request.getParameter("xslb")); // ����ѧ�����
			String xymc = DealString.toGBK(request.getParameter("xymc")); // ����ѧԺ����
			String nd = request.getParameter("nd"); // �������
			String bynd = request.getParameter("bynd"); // ��ҵ���
			String sfsh = request.getParameter("sfsh");
			if (xsxh == null) {
				xsxh = "";
			}
			if (name == null) {
				name = "";
			}
			if (xbdm == null) {
				xbdm = "";
			}
			if (xslb == null) {
				xslb = "";
			}
			if (xymc == null) {
				xymc = "";
			}
			if (nd == null) {
				nd = "";
			}
			if (bynd == null) {
				bynd = "";
			}

			if ((xsxh == null) || xsxh.equals("")) {
				querry += "and 1=1 ";
			} else {
				querry += "and xsxh like '%" + xsxh + "%' ";
			}
			if ((name == null) || name.equals("")) {
				querry += "and 1=1 ";
			} else {
				querry += "and name like '%" + name + "%' ";
			}
			if ((xbdm == null) || xbdm.equals("")) {
				querry += " and 1=1 ";
			} else {
				querry += " and xbdm='" + xbdm + "' ";
			}
			if ((xslb == null) || xslb.equals("")) {
				querry += " and 1=1 ";
			} else {
				querry += " and xslb='" + xslb + "' ";
			}
			if ((xymc == null) || xymc.equals("")) {
				querry += " and 1=1 ";
			} else {
				querry += " and xsxh in (select xsxh from jygl_xsjbxxb where xsxh in (select xh from view_xsjbxx where xymc ='"
						+ xymc + "'))";
			}
			if ((nd == null) || nd.equals("")) {
				querry += " and 1=1 ";
			} else {
				querry += " and nd='" + nd + "' ";
			}
			if ((bynd == null) || bynd.equals("")) {
				querry += " and 1=1 ";
			} else {
				querry += " and bynd='" + bynd + "' ";
			}
			if (sfsh == null || "".equals(sfsh)) {
				querry += " and 1=1 ";
			} else {
				if (sfsh == "1" || "1".equals(sfsh)) {
					sfsh = "δ���";
				} else if (sfsh == "2" || "2".equals(sfsh)) {
					sfsh = "��ͨ����";
				} else if (sfsh == "3" || "3".equals(sfsh)) {
					sfsh = "δͨ��X";
				}
				querry += "and sfsh='" + sfsh + "'";
			}
		}
		if ("jygl_jyxy".equals(tableName)) {
			zd = " * ";

			String xsxh = request.getParameter("xsxh"); // ����ѧ��
			String name = DealString.toGBK(request.getParameter("name")); // ��������
			String xbdm = request.getParameter("xbdm"); // �����Ա����
			String xslb = DealString.toGBK(request.getParameter("xslb")); // ����ѧ�����
			String xymc = DealString.toGBK(request.getParameter("xymc")); // ����ѧԺ����
			String bynd = request.getParameter("bynd"); // ��ҵ���
			String nd = request.getParameter("nd"); // ��ѧ���
			String wjybz = request.getParameter("wjybz"); // δ��ҵ��־
			String fdysh = DealString.toGBK(request.getParameter("fdysh")); // ����Ա���
			String xxsh = DealString.toGBK(request.getParameter("xxsh")); // ѧУ���

			if (xsxh == null) {
				xsxh = "";
			}
			if (name == null) {
				name = "";
			}
			if (xbdm == null) {
				xbdm = "";
			}
			if (xslb == null) {
				xslb = "";
			}
			if (xymc == null) {
				xymc = "";
			}
			if (bynd == null) {
				bynd = "";
			}
			if (wjybz == null) {
				wjybz = "";
			}
			if (fdysh == null) {
				fdysh = "";
			}
			if (xxsh == null) {
				xxsh = "";
			}
			if (nd == null) {
				nd = "";
			}

			if ((xsxh == null) || xsxh.equals("")) {
				querry += "and 1=1 ";
			} else {
				querry += "and xsxh like '%" + xsxh + "%' ";
			}
			if ((name == null) || name.equals("")) {
				querry += "and 1=1 ";
			} else {
				querry += "and name like '%" + name + "%' ";
			}
			if ((xbdm == null) || xbdm.equals("")) {
				querry += " and 1=1 ";
			} else {
				querry += " and xbdm='" + xbdm + "' ";
			}
			if ((xslb == null) || xslb.equals("")) {
				querry += " and 1=1 ";
			} else {
				querry += " and xslb='" + xslb + "' ";
			}
			if ((xymc == null) || xymc.equals("")) {
				querry += " and 1=1 ";
			} else {
				querry += " and xymc='" + xymc + "' ";
			}
			if ((bynd == null) || bynd.equals("")) {
				querry += " and 1=1 ";
			} else {
				querry += " and bynd='" + bynd + "' ";
			}
			if ((wjybz == null) || wjybz.equals("")) {
				querry += " and 1=1 ";
			} else {
				querry += " and wjybz='" + wjybz + "' ";
			}
			if ((fdysh == null) || fdysh.equals("")) {
				querry += " and 1=1 ";
			} else {
				querry += " and fdysh='" + fdysh + "' ";
			}
			if ((xxsh == null) || xxsh.equals("")) {
				querry += " and 1=1 ";
			} else {
				querry += " and xxsh='" + xxsh + "' ";
			}
			if ((nd == null) || nd.equals("")) {
				querry += " and 1=1 ";
			} else {
				querry += " and nd='" + nd + "' ";
			}
		}

		if ("jygl_byqx".equals(tableName)) {
			zd = " * ";

			String xsxh = request.getParameter("xsxh"); // ����ѧ��
			String name = DealString.toGBK(request.getParameter("name")); // ��������
			String xb = request.getParameter("xb"); // �����Ա�
			String xslb = DealString.toGBK(request.getParameter("xslb")); // ����ѧ�����
			String xymc = DealString.toGBK(request.getParameter("xymc")); // ����ѧԺ����
			String bynd = request.getParameter("bynd"); // ��ҵ���
			String xxsh = DealString.toGBK(request.getParameter("xxsh")); // ѧУ���

			if (xsxh == null) {
				xsxh = "";
			}
			if (name == null) {
				name = "";
			}
			if (xb == null) {
				xb = "";
			}
			if (xslb == null) {
				xslb = "";
			}
			if (xymc == null) {
				xymc = "";
			}
			if (bynd == null) {
				bynd = "";
			}
			if (xxsh == null) {
				xxsh = "";
			}

			if ((xsxh == null) || xsxh.equals("")) {
				querry += "and 1=1 ";
			} else {
				querry += "and xsxh like '%" + xsxh + "%' ";
			}
			if ((name == null) || name.equals("")) {
				querry += "and 1=1 ";
			} else {
				querry += "and name like '%" + name + "%' ";
			}
			if ((xb == null) || xb.equals("")) {
				querry += " and 1=1 ";
			} else {
				querry += " and xb='" + xb + "' ";
			}
			if ((xslb == null) || xslb.equals("")) {
				querry += " and 1=1 ";
			} else {
				querry += " and xslb='" + xslb + "' ";
			}
			if ((xymc == null) || xymc.equals("")) {
				querry += " and 1=1 ";
			} else {
				querry += " and xymc='" + xymc + "' ";
			}
			if ((bynd == null) || bynd.equals("")) {
				querry += " and 1=1 ";
			} else {
				querry += " and bynd='" + bynd + "' ";
			}
			if ((xxsh == null) || xxsh.equals("")) {
				querry += " and 1=1 ";
			} else {
				querry += " and xxsh='" + xxsh + "' ";
			}
		}

		if ("jygl_userczmxb".equals(tableName)) {
			zd = " * ";
			String userid = request.getParameter("userid");
			String dowhat = DealString.toGBK(request.getParameter("dowhat"));
			String whichtable = DealString.toGBK(request
					.getParameter("whichtable"));

			// ���ڷ���ʱ��Ĵ��� ���ڲ�ѯ����
			String xjsj = DealString.toGBK(request.getParameter("xjsj")); // ���ʱ��

			String nowsj = (dao
					.getOneRs(
							"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual", // ����ʱ��
							// //
							// ȡ�����ݿ���ʱ��
							new String[] {}, new String[] { "sdate" }))[0];
			String sjyear = nowsj.substring(0, 4);
			String sjmonth = nowsj.substring(4, 6);
			String sjday = nowsj.substring(6, 8);
			String sjqt = nowsj.substring(8, 14);
			String nowsj1 = sjyear + "-" + sjmonth + "-" + sjday;
			dealDate dealdate = new dealDate();
			String beforesj = "";
			if (!"".equals(xjsj)) {
				beforesj = dealdate.getDate2(Integer.parseInt(xjsj), nowsj1);
				beforesj = beforesj.replaceAll("-", "") + sjqt;
			}

			if (userid == null) {
				userid = "";
			}
			if (dowhat == null) {
				dowhat = "";
			}
			if (whichtable == null) {
				whichtable = "";
			}
			if (xjsj == null) {
				xjsj = "";
			}
			if ((userid == null) || userid.equals("")) {
				querry += "and 1=1 ";
			} else {
				querry += "and userid like '%" + userid + "%' ";
			}
			if ((dowhat == null) || dowhat.equals("")) {
				querry += "and 1=1 ";
			} else {
				querry += "and dowhat = '" + dowhat + "' ";
			}
			if ((whichtable == null) || whichtable.equals("")) {
				querry += " and 1=1 ";
			} else {
				querry += " and whichtable='" + whichtable + "' ";
			}
			if ((xjsj == null) || xjsj.equals("")) {
				querry += " and 1=1 ";
			} else {
				querry += "and (whensj between '" + beforesj + "' and '"
						+ nowsj + "') ";
			}
		}

		if ("view_jygl_jyxyslqdjb".equalsIgnoreCase(tableName)) {
			zd = " xh,xb,xm,zymc,jyxybh,remark  ";
			// querry += request.getParameter("querry");
			if (!Base.isNull(request.getParameter("xh"))) {
				querry += " and xh='" + request.getParameter("xh") + "' ";
			}
			if (!Base.isNull(request.getParameter("xm"))) {
				querry += " and xm='"
						+ DealString.toGBK(request.getParameter("xm")) + "' ";
			}
			if (!Base.isNull(request.getParameter("jyxybh"))) {
				querry += " and jyxybh='"
						+ DealString.toGBK(request.getParameter("jyxybh"))
						+ "' ";
			}
			if (!Base.isNull(request.getParameter("nj"))) {
				querry += " and nj='"
						+ DealString.toGBK(request.getParameter("nj")) + "' ";
			}
			if (!Base.isNull(request.getParameter("lqqk"))) {
				querry += " and lqqk='"
						+ DealString.toGBK(request.getParameter("lqqk")) + "' ";
			}
			if (!Base.isNull(request.getParameter("xydm"))) {
				querry += " and xydm='"
						+ DealString.toGBK(request.getParameter("xydm")) + "' ";
			}
			if (!Base.isNull(request.getParameter("zydm"))) {
				querry += " and zydm='"
						+ DealString.toGBK(request.getParameter("zydm")) + "' ";
			}
			if (!Base.isNull(request.getParameter("bjdm"))) {
				querry += " and bjdm='"
						+ DealString.toGBK(request.getParameter("bjdm")) + "' ";
			}
		}

		if ("view_jygl_xsjyqkb".equals(tableName)) {
			zd = " * ";
			String query = DealString.toGBK(request.getParameter("querry"));
			querry += query;
			sql = "select " + zd + " from " + tableName + querry;
		} else {

			sql = "select " + zd + " from " + tableName + querry;
		}

		if ("dwxxb".equals(tableName)) {
			zd = " * ";
			String query = DealString.toGBK(request.getParameter("querry"));
			querry += query;
			sql = "select " + zd + " from " + tableName + querry;
		}
		if ("bjjyqktjb".equalsIgnoreCase(tableName)) {

			String query = DealString.toGBK(request.getParameter("querry"));
			sql = "select rownum ���, a.xymc ѧԺ����,a.xydm ѧԺ����,a.zydm רҵ����,a.bjdm �༶����, a.bjmc �༶���� ,a.bjrs �༶���� ,'' ������,d.lsdwrs ��ʵ��λ���� ,b.qyrs ǩԼ���� ,(select to_char(c.jyrs/a.bjrs*100,'999.99')||'%' from dual) ��ҵ��,(select to_char(b.qyrs/a.bjrs*100,'999.99')||'%' from dual) ǩԼ�� from "
					+ "(select xymc,xydm,zydm,bjdm,bjmc,count(*) bjrs from view_xsjbxx group by bjdm,bjmc,xymc,xydm,zydm) a "
					+ "left join "
					+ "(select bjdm,count(*) qyrs from view_jygl_xsjyqkb where sfqy='��' group by bjdm) b "
					+ "on a.bjdm=b.bjdm "
					+ "left join "
					+ "(select bjdm,count(*) jyrs from view_jygl_xsjyqkb where sfjy='��' group by bjdm) c "
					+ "on a.bjdm=c.bjdm "
					+ "left join "
					+ "(select bjdm,count(*) lsdwrs from view_jygl_xsjyqkb where dwzm is not null group by bjdm) d "
					+ "on a.bjdm=d.bjdm " + query + "order by ѧԺ����";

		}

		if ("zyjyqktjb".equalsIgnoreCase(tableName)) {

			String query = DealString.toGBK(request.getParameter("querry"));
			sql = "select rownum ���, a.xymc ѧԺ����,a.xydm ѧԺ����,a.zymc רҵ����,a.zydm רҵ����,a.zyrs רҵ����,d.lsdwrs ��ʵ��λ����,b.qyrs ǩԼ����,replace((select to_char(c.jyrs/a.zyrs*100,'999.99')||'%' from dual),'    ','0') ��ҵ��,replace((select to_char(b.qyrs/a.zyrs*100,'999.99')||'%' from dual),'    ','0') ǩԼ�� from "
					+ "(select xymc,xydm,zydm,zymc,count(*) zyrs from view_xsjbxx group by zydm,zymc,xymc,xydm) a "
					+ "left join "
					+ "(select zydm,count(*) qyrs from view_jygl_xsjyqkb where sfqy='��' group by zydm) b "
					+ "on a.zydm=b.zydm "
					+ "left join "
					+ "(select zydm,count(*) jyrs from view_jygl_xsjyqkb where sfjy='��' group by zydm) c "
					+ "on a.zydm=c.zydm "
					+ "left join "
					+ "(select zydm,count(*) lsdwrs from view_jygl_xsjyqkb where dwzm='��' group by zydm) d "
					+ "on a.zydm=d.zydm " + query + " order by ѧԺ����";

		}

		if ("fyjyqktjb".equalsIgnoreCase(tableName)) {

			String query = DealString.toGBK(request.getParameter("querry"));
			sql = "select rownum ���, a.xymc ѧԺ����,a.xydm ѧԺ����,a.xyrs ѧԺ����,d.lsdwrs ��ʵ��λ����,b.qyrs ǩԼ����,replace((select to_char(c.jyrs/a.xyrs*100,'999.99')||'%' from dual),'    ','0') ��ҵ��,replace((select to_char(b.qyrs/a.xyrs*100,'999.99')||'%' from dual),'    ','0') ǩԼ�� from "
					+ "(select xymc,xydm,count(*) xyrs from view_xsjbxx group by xymc,xydm) a "
					+ "left join "
					+ "(select xydm,count(*) qyrs from view_jygl_xsjyqkb where sfqy='��' group by xydm) b "
					+ "on a.xydm=b.xydm "
					+ "left join "
					+ "(select xydm,count(*) jyrs from view_jygl_xsjyqkb where sfjy='��' group by xydm) c "
					+ "on a.xydm=c.xydm "
					+ "left join "
					+ "(select xydm,count(*) lsdwrs from view_jygl_xsjyqkb where dwzm='��' group by xydm) d "
					+ "on a.xydm=d.xydm " + query + " order by ѧԺ����";

		}

		if ("jyqktjb".equalsIgnoreCase(tableName)) {

			sql = "select a.zrs ������,d.lsdwrs ��ʵ��λ����,c.qyrs ǩԼ����, replace((select to_char(c.qyrs/a.zrs*100,'999.99')||'%' from dual),'    ','0') ǩԼ��, b.jyrs ��ҵ����,replace((select to_char(b.jyrs/a.zrs*100,'999.99')||'%' from dual),'    ','0') ��ҵ�� from "
					+ "(select count(*) zrs from view_xsjbxx ) a , "
					+ "(select count(*) jyrs from view_jygl_xsjyqkb where sfjy='��') b, "
					+ "(select count(*) qyrs from view_jygl_xsjyqkb where sfqy='��') c, "
					+ "(select count(*) lsdwrs from view_jygl_xsjyqkb where dwzm='��') d";

		}

		if ("fbjyqktjb".equalsIgnoreCase(tableName)) {

			String query = DealString.toGBK(request.getParameter("querry"));
			sql = "select rownum ���, a.xh ѧ��,a.xm ����,a.xb �Ա�,a.dwmc ��λ����,a.dwdz ��λ��ַ,a.dwdh ��λ�绰,a.sfjy �Ƿ��ҵ,a.sfqy �Ƿ�ǩԼ,a.djsj �Ǽ�ʱ�� from view_jygl_xsjyqkb a "
					+ query + " order by xh";

		}

		if ("zydkqktjb".equalsIgnoreCase(tableName)) {

			String query = DealString.toGBK(request.getParameter("querry"));
			sql = "select rownum ���, a.xymc ѧԺ����,a.xydm ѧԺ����,a.zymc רҵ����,a.zydm רҵ����,a.zyrs רҵ����,d.lsdwrs ��ʵ��λ����,b.zydkrs רҵ�Կ�����,replace((select to_char(b.zydkrs/a.zyrs*100,'999.99')||'%' from dual),'    ','0') �Կ��� from "
					+ "(select xymc,xydm,zymc,zydm,count(*) zyrs from view_xsjbxx group by xymc,xydm,zymc,zydm) a "
					+ "left join "
					+ "(select zydm,count(*) zydkrs from view_jygl_xsjyqkb where zydkqk='��ȫ�Կ�' or zydkqk='�����Կ�' group by zydm) b "
					+ "on a.zydm=b.zydm "
					+ "left join "
					+ "(select zydm,count(*) lsdwrs from view_jygl_xsjyqkb where dwzm='��' group by zydm) d "
					+ "on a.zydm=d.zydm " + query + " order by ѧԺ����";
			;

		}

		if ("gzqktjb".equalsIgnoreCase(tableName)) {

			sql = "select rownum ���,b.gzqk �������,c.lsdwrs ��ʵ��λ����,replace((select to_char(c.lsdwrs/a.zrs*100,'999.99')||'%' from dual),'    ','0') ���� from "
					+ "(select count(*) zrs from view_jygl_xsjyqkb ) a , "
					+ "(select gzqk ,count(*) zrs from view_jygl_xsjyqkb group by gzqk ) b "
					+ "left join "
					+ "(select gzqk ,count(*) lsdwrs from view_jygl_xsjyqkb where dwzm='��' group by gzqk) c "
					+ "on b.gzqk = c.gzqk ";

		}

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		if (tableName == null) {
			Excel2Oracle.exportData(sql, tableName, response.getOutputStream());
		} else {
			Excel2Oracle.exportData(sql, tableName, response.getOutputStream());
		}
		return mapping.findForward("success");
	}

	// ��ҵ��ѯ��ʦ�Ǽ�
	private ActionForward jyzxTeaInput(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			String userType) throws Exception {

		DAO dao = DAO.getInstance();
		String tableName = "jygl_zxjsxxb";
		HashMap<String, String> map = new HashMap<String, String>();
		String act = request.getParameter("act");
		if ("save".equals(act)) {
			String num = request.getParameter("num");
			String name = DealString.toGBK(request.getParameter("name"));
			String age = request.getParameter("age");
			String xb = DealString.toGBK(request.getParameter("xb"));
			String userid = DealString.toGBK(request.getParameter("userid"));
			String password = DealString
					.toGBK(request.getParameter("password"));
			String zxszg = DealString.toGBK(request.getParameter("zxszg"));
			String lxdh = request.getParameter("lxdh");
			String email = request.getParameter("email");
			String zxsjj = DealString.toGBK(request.getParameter("zxsjj"));

			boolean judge = false;
			judge = StandardOperation.insert(tableName, new String[] { "num",
					"name", "age", "xb", "userid", "password", "zxszg", "lxdh",
					"email", "zxsjj" }, new String[] { num, name, age, xb,
					userid, password, zxszg, lxdh, email, zxsjj }, request);
			if (judge) {
				request.setAttribute("save", "ok");

				// ��Ӳ����ļ�¼
				if ("teacher".equals(userType)) {
					HttpSession session = request.getSession(); // ��ûỰ
					String userName = session.getAttribute("userName")
							.toString();
					String whensj = (dao
							.getOneRs(
									"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual",
									new String[] {}, new String[] { "sdate" }))[0]; // �ύʱ��

					StandardOperation.insert("JYGL_USERCZMXB", new String[] {
							"userid", "dowhat", "whichtable", "whichpk",
							"whensj" }, new String[] { userName, "����",
							"��ѯ��ʦ��Ϣ��", "��ѯʦ���:" + num, whensj }, request);
				}
			} else {
				request.setAttribute("save", "no");
			}

			map = dao.getMap("select * from jygl_zxjsxxb where num=?",
					new String[] { num }, new String[] { "num", "name", "age",
							"xb", "userid", "password", "zxszg", "lxdh",
							"email", "zxsjj" });

		}
		request.setAttribute("rs", map);
		return mapping.findForward("success");
	}

	// ѧ����ѯԤԼ ��ѯ��ɾ��
	private ActionForward jyzxYuyuequery(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response, String userType) throws Exception {

		DAO dao = DAO.getInstance(); // ʵ����ͨ�÷�������������ݿ�����
		ArrayList<Object> rs = new ArrayList<Object>();
		String[] colList = null;
		String[] colListCN = null;
		String sql = ""; // sql���
		String querry = " where 1=1 "; // sql����
		String tableName = "jygl_zxjsxxb"; // ��ѯ��ϢԴ����Ϊ��ͼ����
		String rsNum = "0";// ���صļ�¼��
		String dataType = request.getParameter("act"); // ������������
		String num = request.getParameter("num"); // ���ձ��
		String name = DealString.toGBK(request.getParameter("name")); // ��������
		String xb = DealString.toGBK(request.getParameter("xb")); // �����Ա����
		String pk = "num"; // ����
		HashMap<String, String> map = new HashMap<String, String>();
		String doType = request.getParameter("doType");
		String doType2 = request.getParameter("doType2");
		String pkValue = request.getParameter("pkValue");
		String type = (String) request.getSession().getAttribute("userType");

		if (!"stu".equalsIgnoreCase(type)) {
			request.setAttribute("errMsg", "�Բ��𣬱�ҳ��ֻ��ѧ���û����Է���!");
			return new ActionForward("/errMsg.do", false);
		}

		if (userType.equals("student")) {
			request.setAttribute("isstudent", "ok");
		} else {
			request.setAttribute("isstudent", "no");
		}

		if ("del".equals(doType2)) {
			boolean judge = false;
			judge = StandardOperation.delete(tableName, pk, pkValue, request);
			if (judge) {
				request.setAttribute("delete", "ok");

				// ɾ�������ļ�¼
				HttpSession session = request.getSession();
				String userName = session.getAttribute("userName").toString();
				String whensj = (dao
						.getOneRs(
								"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual",
								new String[] {}, new String[] { "sdate" }))[0]; // �ύʱ��

				StandardOperation.insert("JYGL_USERCZMXB",
						new String[] { "userid", "dowhat", "whichtable",
								"whichpk", "whensj" }, new String[] { userName,
								"ɾ��", "��ѯ��ʦ��Ϣ��", "��ѯʦ���:" + pkValue, whensj },
						request);
			} else {
				request.setAttribute("delete", "no");
			}
		}

		// ���ϴ��ύ��ֵ����ȥ
		if ("query".equals(doType)) {
			map.put("num", num);
			map.put("name", name);
			map.put("xb", xb);
		}

		if (num == null) {
			num = "";
		}
		if (name == null) {
			name = "";
		}
		if (xb == null) {
			xb = "";
		}
		if ((num == null) || num.equals("")) {
			querry += "and 1=1 ";
		} else {
			querry += "and num like '%" + num + "%' ";
		}
		if ((name == null) || name.equals("")) {
			querry += "and 1=1 ";
		} else {
			querry += "and name like '%" + name + "%' ";
		}
		if ((xb == null) || xb.equals("")) {
			querry += " and 1=1 ";
		} else {
			querry += " and xb='" + xb + "' ";
		}

		sql = "select " + pk + " ����,rownum �к�,a.* from " + tableName + " a "
				+ querry;
		colList = new String[] { "����", "�к�", "num", "name", "xb", "age",
				"lxdh", "zxszg", "readtimes" };
		colListCN = dao.getColumnNameCN(colList, tableName);
		List topTr = dao.arrayToList(colList, colListCN);
		if ((request.getParameter("act") != null)
				&& request.getParameter("act").equalsIgnoreCase("go")) {
			rs.addAll(dao.rsToVator(sql, new String[] {}, colList));
			if (rs == null) {
				rsNum = "0";
			} else {
				rsNum = String.valueOf(rs.size());
			}
		}
		request.setAttribute("act", dataType);// �������ݲ�ѯ����
		request.setAttribute("rs", rs);// �������ݼ�
		request.setAttribute("topTr", topTr);// ���ͱ�ͷ
		request.setAttribute("rsNum", rsNum);// ���ͼ�¼��
		request.setAttribute("rs1", map); // ���صĲ�ѯ����
		return mapping.findForward("success");
	}

	// ѧ����ѯԤԼ ��ϸ��Ϣ
	private ActionForward jyzxViewMoreInfo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String pk = "num";
		String sql = ""; // sql���
		DAO dao = DAO.getInstance();
		String realTable = "jygl_zxjsxxb";
		String doType = request.getParameter("doType");
		String pkValue = request.getParameter("pkValue");
		HashMap<String, String> map = new HashMap<String, String>();

		if ((doType == null) || doType.equalsIgnoreCase("")) {
			// �����쳣
			return mapping.findForward("false");
		} else if (doType.equalsIgnoreCase("view")) {
			// ��ѯ����
			sql = "select * from " + realTable + " where " + pk + "='"
					+ pkValue + "'";
			String[] colList = dao.getColumnName("select * from " + realTable
					+ " where 1=2"); // ������������
			String[] zxsinfo = dao.getOneRs(sql, new String[] {}, colList);
			if (zxsinfo != null) {
				for (int i = 0; i < colList.length; i++) {
					map.put(colList[i].toLowerCase(), zxsinfo[i]); // ����¼ѭ������map��
				}
			}
			sql = "select readtimes from " + realTable + " where num=?";
			String[] readtimes = dao.getOneRs(sql, new String[] { pkValue },
					new String[] { "readtimes" });
			int timeint = Integer.parseInt(readtimes[0]);
			timeint += 1;
			String tinestr = String.valueOf(timeint);
			StandardOperation.update(realTable, new String[] { "readtimes" },
					new String[] { tinestr }, pk, pkValue, request);
		}
		request.setAttribute("rs", map);// �������ݼ�
		return mapping.findForward("success");
	}

	// ѧ����ѯԤԼ �޸���ѯʦ��Ϣ
	private ActionForward jyzxZxsUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String pk = "num";
		String sql = ""; // sql���
		DAO dao = DAO.getInstance();
		String realTable = "jygl_zxjsxxb";
		String doType = request.getParameter("doType");
		String pkValue = request.getParameter("pkValue");
		HashMap<String, String> map = new HashMap<String, String>();

		if ((doType == null) || doType.equalsIgnoreCase("")) {
			// �����쳣
			return mapping.findForward("false");
		} else if (doType.equalsIgnoreCase("view")) {
			// ��ѯ����
			sql = "select * from " + realTable + " where " + pk + "='"
					+ pkValue + "'";
			String[] colList = dao.getColumnName("select * from " + realTable
					+ " where 1=2"); // ������������
			String[] zxsinfo = dao.getOneRs(sql, new String[] {}, colList);
			if (zxsinfo != null) {
				for (int i = 0; i < colList.length; i++) {
					map.put(colList[i].toLowerCase(), zxsinfo[i]); // ����¼ѭ������map��
				}
			}
		}
		if ("update".equals(doType)) {
			String num = request.getParameter("num");
			String name = DealString.toGBK(request.getParameter("name"));
			String age = request.getParameter("age");
			String xb = DealString.toGBK(request.getParameter("xb"));
			String userid = DealString.toGBK(request.getParameter("userid"));
			String password = DealString
					.toGBK(request.getParameter("password"));
			String zxszg = DealString.toGBK(request.getParameter("zxszg"));
			String lxdh = request.getParameter("lxdh");
			String email = request.getParameter("email");
			String zxsjj = DealString.toGBK(request.getParameter("zxsjj"));

			boolean judge = false;
			judge = StandardOperation.update(realTable, new String[] { "name",
					"age", "xb", "userid", "password", "zxszg", "lxdh",
					"email", "zxsjj" }, new String[] { name, age, xb, userid,
					password, zxszg, lxdh, email, zxsjj }, pk, num, request);
			if (judge) {
				request.setAttribute("update", "ok");

				// �޸Ĳ����ļ�¼
				HttpSession session = request.getSession();
				String userName = session.getAttribute("userName").toString();
				String whensj = (dao
						.getOneRs(
								"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual",
								new String[] {}, new String[] { "sdate" }))[0]; // �ύʱ��

				StandardOperation.insert("JYGL_USERCZMXB",
						new String[] { "userid", "dowhat", "whichtable",
								"whichpk", "whensj" }, new String[] { userName,
								"�޸�", "��ѯ��ʦ��Ϣ��", "��ѯʦ���:" + num, whensj },
						request);
			} else {
				request.setAttribute("update", "no");
			}

			sql = "select * from " + realTable + " where " + pk + "='" + num
					+ "'";
			String[] colList = dao.getColumnName("select * from " + realTable
					+ " where 1=2"); // ������������
			String[] zxsinfo = dao.getOneRs(sql, new String[] {}, colList);
			if (zxsinfo != null) {
				for (int i = 0; i < colList.length; i++) {
					map.put(colList[i].toLowerCase(), zxsinfo[i]); // ����¼ѭ������map��
				}
			}
		}

		request.setAttribute("rs", map);// �������ݼ�
		return mapping.findForward("success");
	}

	// ��ѯʦԤԼ
	private ActionForward jyzxZxsYuyue(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			String userType) throws Exception {
		String pk = "num";
		String sql = ""; // sql���
		DAO dao = DAO.getInstance();
		String realTable = "jygl_zxjsxxb"; // ��ѯ��ʦ��Ϣ��
		String tableName = "jygl_zxyyglb"; // ��ѯԤԼ�����
		String doType = request.getParameter("doType");
		String pkValue = DealString.toGBK(request.getParameter("pkValue"));
		HashMap<String, String> map = new HashMap<String, String>();
		HttpSession session = request.getSession();
		String userName = session.getAttribute("userName").toString();

		if ((doType == null) || doType.equalsIgnoreCase("")) {
			// �����쳣
			return mapping.findForward("false");
		} else if (doType.equalsIgnoreCase("view")) {
			// ��ѯ����
			sql = "select * from " + realTable + " where " + pk + "='"
					+ pkValue + "'";
			String[] colList = dao.getColumnName("select * from " + realTable
					+ " where 1=2"); // ������������
			String[] zxsinfo = dao.getOneRs(sql, new String[] {}, colList);
			if (zxsinfo != null) {
				for (int i = 0; i < colList.length; i++) {
					map.put(colList[i].toLowerCase(), zxsinfo[i]); // ����¼ѭ������map��
				}
			}
		}

		if ("save".equals(doType)) {
			String num = request.getParameter("num"); // ��ѯʦ���
			String meet = DealString.toGBK(request.getParameter("meet")); // �Ƿ�Ҫ�����
			String qwyjtime = request.getParameter("qwyjtime"); // ����Լ��ʱ��
			String zxwtjs = DealString.toGBK(request.getParameter("zxwtjs")); // ��ѯ�������
			String tjsj = GetTime.getSystemTime();
			boolean judge = false;
			judge = StandardOperation
					.insert(tableName, new String[] { "num", "xsxh", "meet",
							"qwyjtime", "zxwtjs", "tjsj" }, new String[] { num,
							userName, meet, qwyjtime, zxwtjs, tjsj }, request);
			if (judge) {
				request.setAttribute("save", "ok");
			} else {
				request.setAttribute("save", "no");
			}
			/*
			 * // �ٴ���ʾ����ѯʦ��Ϣ sql = "select * from " + realTable + " where num='" +
			 * num + "'"; String[] colList = dao.getColumnName("select * from " +
			 * realTable + " where 1=2"); // ������������ String[] zxsinfo =
			 * dao.getOneRs(sql, new String[] {}, colList); if (zxsinfo != null) {
			 * for (int i = 0; i < colList.length; i++) {
			 * map.put(colList[i].toLowerCase(), zxsinfo[i]); // ����¼ѭ������map�� } }
			 */
		}

		request.setAttribute("rs", map);// �������ݼ�
		return mapping.findForward("success");
	}

	// ��ѯʦԤԼ����ҳ���,��ѯ��ɾ����
	private ActionForward jyzxZxyygl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			String userType) throws Exception {
		DAO dao = DAO.getInstance(); // ʵ����ͨ�÷�������������ݿ�����
		ArrayList<Object> rs = new ArrayList<Object>();
		String[] colList = null;
		String[] colListCN = null;
		String sql = ""; // sql���
		String querry = " where 1=1 "; // sql����
		String tableName = "JYGL_ZXYYGLB"; // ��ѯ��ϢԴ����Ϊ��ͼ����
		String rsNum = "0";// ���صļ�¼��
		String dataType = request.getParameter("act"); // ������������
		String num = request.getParameter("num"); // ������ѯʦ���
		String xsxh = request.getParameter("xsxh"); // ����ѧ��ѧ��
		String meet = DealString.toGBK(request.getParameter("meet")); // �����Ƿ�Ҫ�����
		String xsqr = DealString.toGBK(request.getParameter("xsqr")); // ����ѧ��ȷ��
		String zxsqr = DealString.toGBK(request.getParameter("zxsqr")); // ������ѯʦȷ��
		String pk = "num||xsxh||tjsj"; // ����
		HashMap<String, String> map = new HashMap<String, String>();
		String doType = request.getParameter("doType");
		String doType2 = request.getParameter("doType2");
		String pkValue = request.getParameter("pkValue");
		HttpSession session = request.getSession();
		String userName = (String) session.getAttribute("userName");
		String type = (String) session.getAttribute("userType");

		if ("stu".equalsIgnoreCase(type)) {
			request.setAttribute("errMsg", "�Բ�������Ȩ���ʴ�ҳ!");
			return new ActionForward("/errMsg.do", false);

		}
		// ���ڷ���ʱ��Ĵ��� ���ڲ�ѯ����
		String xjsj = DealString.toGBK(request.getParameter("xjsj")); // ���ʱ��

		String nowsj = (dao.getOneRs(
				"select to_char(sysdate,'yyyymmdd') sdate from dual", // ����ʱ��
				// //
				// ȡ�����ݿ���ʱ��
				new String[] {}, new String[] { "sdate" }))[0];
		String sjyear = nowsj.substring(0, 4);
		String sjmonth = nowsj.substring(4, 6);
		String sjday = nowsj.substring(6, 8);
		String nowsj1 = sjyear + "-" + sjmonth + "-" + sjday;
		dealDate dealdate = new dealDate();
		String beforesj = "";
		if (!"".equals(xjsj)) {
			beforesj = dealdate.getDate2(Integer.parseInt(xjsj), nowsj1);
			beforesj = beforesj.replaceAll("-", "");
		}

		// ɾ��
		if ("del".equals(doType2)) {
			boolean judge = false;
			judge = StandardOperation.delete(tableName, pk, pkValue, request);
			if (judge) {
				request.setAttribute("delete", "ok");

				// ɾ�������ļ�¼
				String whensj = (dao
						.getOneRs(
								"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual",
								new String[] {}, new String[] { "sdate" }))[0]; // �ύʱ��

				StandardOperation.insert("JYGL_USERCZMXB",
						new String[] { "userid", "dowhat", "whichtable",
								"whichpk", "whensj" }, new String[] { userName,
								"ɾ��", "��ѯԤԼ�����", "ѧ��:" + pkValue, whensj },
						request);
			} else {
				request.setAttribute("delete", "no");
			}
		}

		// ���ϴ��ύ��ֵ����ȥ
		if ("query".equals(doType)) {
			map.put("num", num);
			map.put("xsxh", xsxh);
			map.put("xjsj", xjsj);
			map.put("meet", meet);
			map.put("xsqr", xsqr);
			map.put("zxsqr", zxsqr);
		}

		if (num == null) {
			num = "";
		}
		if (xsxh == null) {
			xsxh = "";
		}
		if (xjsj == null) {
			xjsj = "";
		}
		if (meet == null) {
			meet = "";
		}
		if (xsqr == null) {
			xsqr = "";
		}
		if (zxsqr == null) {
			zxsqr = "";
		}

		if ((num == null) || num.equals("")) {
			querry += "and 1=1 ";
		} else {
			querry += "and num like '%" + num + "%' ";
		}
		if ((xsxh == null) || xsxh.equals("")) {
			querry += "and 1=1 ";
		} else {
			querry += "and xsxh like '%" + xsxh + "%' ";
		}
		if ((xjsj == null) || xjsj.equals("")) {
			querry += "and 1=1 ";
		} else {
			querry += "and (replace(tjsj,'-','') between '" + beforesj
					+ "' and '" + nowsj + "') ";
		}
		if ((meet == null) || meet.equals("")) {
			querry += " and 1=1 ";
		} else {
			querry += " and meet='" + meet + "' ";
		}
		if ((xsqr == null) || xsqr.equals("")) {
			querry += " and 1=1 ";
		} else {
			querry += " and xsqr='" + xsqr + "' ";
		}
		if ((zxsqr == null) || zxsqr.equals("")) {
			querry += " and 1=1 ";
		} else {
			querry += " and zxsqr='" + zxsqr + "' ";
		}

		sql = "select " + pk + " ����,rownum �к�,a.* from " + tableName + " a "
				+ querry;
		colList = new String[] { "����", "�к�", "num", "xsxh", "tjsj", "qwyjtime",
				"xsqr", "zxsqr", "meet" };
		colListCN = dao.getColumnNameCN(colList, tableName);
		List topTr = dao.arrayToList(colList, colListCN);
		if ((request.getParameter("act") != null)
				&& request.getParameter("act").equalsIgnoreCase("go")) {
			rs.addAll(dao.rsToVator(sql, new String[] {}, colList));
			if (rs == null) {
				rsNum = "0";
			} else {
				rsNum = String.valueOf(rs.size());
			}
		}
		request.setAttribute("act", dataType);// �������ݲ�ѯ����
		request.setAttribute("rs", rs);// �������ݼ�
		request.setAttribute("topTr", topTr);// ���ͱ�ͷ
		request.setAttribute("rsNum", rsNum);// ���ͼ�¼��
		request.setAttribute("rs1", map); // ���صĲ�ѯ����
		return mapping.findForward("success");
	}

	// ��ѯԤԼ���� ��ϸ��Ϣ
	private ActionForward zxyyglViewMoreInfo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String pk = "num||xsxh||tjsj";
		String sql = ""; // sql���
		DAO dao = DAO.getInstance();
		String realTable = "JYGL_ZXYYGLB";
		String doType = request.getParameter("doType");
		String pkValue = request.getParameter("pkValue");
		HashMap<String, String> map = new HashMap<String, String>();

		if ((doType == null) || doType.equalsIgnoreCase("")) {
			// �����쳣
			return mapping.findForward("false");
		} else if (doType.equalsIgnoreCase("view")) {
			// ��ѯ����
			sql = "select * from " + realTable + " where " + pk + "='"
					+ pkValue + "'";
			String[] colList = dao.getColumnName("select * from " + realTable
					+ " where 1=2"); // ������������
			String[] zxyyglinfo = dao.getOneRs(sql, new String[] {}, colList);
			if (zxyyglinfo != null) {
				for (int i = 0; i < colList.length; i++) {
					map.put(colList[i].toLowerCase(), zxyyglinfo[i]); // ����¼ѭ������map��
				}
			}
			// ��ȡ��ѯʦ����
			String num = map.get("num");
			sql = "select name from jygl_zxjsxxb where num=?";
			String[] zxsinfo = dao.getOneRs(sql, new String[] { num },
					new String[] { "name" });
			map.put("zxsname",
					null == zxsinfo || Base.isNull(zxsinfo[0]) ? "��ѯʦ�����ڻ��߱�ɾ��"
							: zxsinfo[0]);
			// ��ȡѧ��������Ϣ
			String xsxh = map.get("xsxh");
			sql = "select * from view_xsjbxx where xh=?";
			String[] stuinfo = dao.getOneRs(sql, new String[] { xsxh },
					new String[] { "xm", "xb", "nj", "xymc", "zymc", });
			if (stuinfo != null) {
				map.put("name", stuinfo[0]);
				map.put("xb", stuinfo[1]);
				map.put("nj", stuinfo[2]);
				map.put("xymc", stuinfo[3]);
				map.put("zymc", stuinfo[4]);
			}
			/*
			 * // ��ʱ��ת������ String tjsj = null; String sjyear = null; String sjmon =
			 * null; String sjday = null; String sjhour = null; String sjmin =
			 * null; String sjss = null; // ����ѯʦȷ��ʱ��ת������ String zxsqrsj = null; //
			 * ��ѧ��ȷ��ʱ��ת������ String xsqrsj = null;
			 */
		}
		request.setAttribute("rs", map);// �������ݼ�
		return mapping.findForward("success");
	}

	// ��ѯ�ظ�
	private ActionForward answerYuyue(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String pk = "num||xsxh||tjsj";
		String sql = ""; // sql���
		DAO dao = DAO.getInstance();
		String realTable = "JYGL_ZXYYGLB";
		String doType = request.getParameter("doType");
		String act = request.getParameter("act");
		String pkValue = request.getParameter("pkValue");
		HashMap<String, String> map = new HashMap<String, String>();

		if ("answer".equals(act)) {
			String zxwthf = DealString.toGBK(request.getParameter("zxwthf"));
			String zxsqr = DealString.toGBK(request.getParameter("zxsqr"));
			String yjdd = DealString.toGBK(request.getParameter("yjdd"));
			String yjsj = DealString.toGBK(request.getParameter("yjsj"));
			String zxsqrsj = (dao
					.getOneRs(
							"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual", // ����ʱ��
							// //
							// ȡ�����ݿ���ʱ��
							new String[] {}, new String[] { "sdate" }))[0];
			boolean judge = false;
			judge = StandardOperation.update(realTable, new String[] {
					"zxwthf", "zxsqr", "yjdd", "yjsj", "zxsqrsj" },
					new String[] { zxwthf, zxsqr, yjdd, yjsj, zxsqrsj }, pk,
					pkValue, request);
			if (judge) {
				request.setAttribute("answer", "ok");
			} else {
				request.setAttribute("answer", "no");
			}
		}

		if ((doType == null) || doType.equalsIgnoreCase("")) {
			// �����쳣
			return mapping.findForward("false");
		} else if (doType.equalsIgnoreCase("view")) {
			// ��ѯ����
			if ("".equals(pkValue)) {
				pkValue = request.getParameter("xsxh");
			}
			sql = "select * from " + realTable + " where " + pk + "='"
					+ pkValue + "'";
			String[] colList = dao.getColumnName("select * from " + realTable
					+ " where 1=2"); // ������������
			String[] zxyyglinfo = dao.getOneRs(sql, new String[] {}, colList);
			if (zxyyglinfo != null) {
				for (int i = 0; i < colList.length; i++) {
					map.put(colList[i].toLowerCase(), zxyyglinfo[i]); // ����¼ѭ������map��
				}
			}
			// ��ȡ��ѯʦ����
			String num = map.get("num");
			sql = "select name from jygl_zxjsxxb where num=?";
			String[] zxsinfo = dao.getOneRs(sql, new String[] { num },
					new String[] { "name" });
			if (zxsinfo != null) {
				map.put("zxsname", zxsinfo[0]);
			}
			// ��ȡѧ��������Ϣ
			String xsxh = map.get("xsxh");
			sql = "select * from view_xsjbxx where xh=?";
			String[] stuinfo = dao.getOneRs(sql, new String[] { xsxh },
					new String[] { "xm", "xb", "nj", "xymc", "zymc", });
			if (stuinfo != null) {
				map.put("name", stuinfo[0]);
				map.put("xb", stuinfo[1]);
				map.put("nj", stuinfo[2]);
				map.put("xymc", stuinfo[3]);
				map.put("zymc", stuinfo[4]);
			}
			// ���ύʱ��ת������
			/*
			 * String tjsj = null; String sjyear = null; String sjmon = null;
			 * String sjday = null; String sjhour = null; String sjmin = null;
			 * String sjss = null;
			 */
			/*
			 * if (null != map.get("tjsj")) { String sj =
			 * map.get("tjsj").toString(); sjyear = sj.substring(0, 4); sjmon =
			 * sj.substring(4, 6); sjday = sj.substring(6, 8); sjhour =
			 * sj.substring(8, 10); sjmin = sj.substring(10, 12); sjss =
			 * sj.substring(12, 14); tjsj = sjyear + "��" + sjmon + "��" + sjday +
			 * "�� " + sjhour + ":" + sjmin + ":" + sjss; map.put("tjsj", tjsj); } //
			 * ����ѯʦȷ��ʱ��ת������ String zxsqrsj = null; if (null !=
			 * map.get("zxsqrsj")) { String sj = map.get("zxsqrsj").toString();
			 * sjyear = sj.substring(0, 4); sjmon = sj.substring(4, 6); sjday =
			 * sj.substring(6, 8); sjhour = sj.substring(8, 10); sjmin =
			 * sj.substring(10, 12); sjss = sj.substring(12, 14); zxsqrsj =
			 * sjyear + "��" + sjmon + "��" + sjday + "�� " + sjhour + ":" + sjmin +
			 * ":" + sjss; map.put("zxsqrsj", zxsqrsj); } // ��ѧ��ȷ��ʱ��ת������ String
			 * xsqrsj = null; if (null != map.get("xsqrsj")) { String sj =
			 * map.get("xsqrsj").toString(); sjyear = sj.substring(0, 4); sjmon =
			 * sj.substring(4, 6); sjday = sj.substring(6, 8); sjhour =
			 * sj.substring(8, 10); sjmin = sj.substring(10, 12); sjss =
			 * sj.substring(12, 14); xsqrsj = sjyear + "��" + sjmon + "��" + sjday +
			 * "�� " + sjhour + ":" + sjmin + ":" + sjss; map.put("xsqrsj",
			 * xsqrsj); }
			 */
		}
		request.setAttribute("rs", map);// �������ݼ�
		return mapping.findForward("success");
	}

	// 
	private ActionForward zjlgjyxyexp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		StringBuffer querry = new StringBuffer(" where a.xsxh is not null ");
		String xsxh = request.getParameter("xsxh"); // ѧ��
		String name = DealString.toGBK(request.getParameter("name")); // ����
		String xb = DealString.toGBK(request.getParameter("xb")); // �Ա�
		String xymc = DealString.toGBK(request.getParameter("xymc")); // ѧԺ
		String nj = request.getParameter("nj"); // �꼶
		String xxsh = DealString.toGBK(request.getParameter("xxsh")); // ѧУ���
		// ���ڷ���ʱ��Ĵ��� ���ڲ�ѯ����
		String xjsj = DealString.toGBK(request.getParameter("xjsj")); // ���ʱ��
		String nowsj = (dao.getOneRs(
				"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual", // ����ʱ��
				// //
				// ȡ�����ݿ���ʱ��
				new String[] {}, new String[] { "sdate" }))[0];
		// String shsj = (dao.getOneRs(
		// "select to_char(sysdate,'yyyymmddhh24miss') sdate from dual", //
		// ѧУ���ʱ��
		// //
		// ȡ�����ݿ���ʱ��
		// new String[] {}, new String[] { "sdate" }))[0];
		String sjyear = nowsj.substring(0, 4);
		String sjmonth = nowsj.substring(4, 6);
		String sjday = nowsj.substring(6, 8);
		String sjqt = nowsj.substring(8, 14);
		String nowsj1 = sjyear + "-" + sjmonth + "-" + sjday;
		dealDate dealdate = new dealDate();
		String beforesj = "";
		if (!"".equals(xjsj)) {
			beforesj = dealdate.getDate2(Integer.parseInt(xjsj), nowsj1);
			beforesj = beforesj.replaceAll("-", "") + sjqt;
		}
		querry.append(StringUtils.isNull(xsxh) ? " and 1=1" : " and xsxh='"
				+ xsxh + "'");
		querry.append(StringUtils.isNull(xxsh) ? " and 1=1" : " and xxsh='"
				+ xxsh + "'");
		querry.append(StringUtils.isNull(name) ? " and 1=1" : " and xm like '%"
				+ name + "%'");
		querry.append(StringUtils.isNull(xb) ? " and 1=1" : " and xb = '" + xb
				+ "'");
		querry
				.append(StringUtils.isNull(xymc) ? " and 1=1"
						: "  and xymc in (select xsxh from jygl_xsjbxxb where xsxh in (select xh from view_xsjbxx where xymc ='"
								+ xymc + "'))");
		querry.append(StringUtils.isNull(nj) ? " and 1=1"
				: " and xsxh in (select xsxh from jygl_xsjbxxb where nd = '"
						+ nj + "')");
		querry
				.append(StringUtils.isNull(xjsj) ? " and 1=1"
						: " and (fbsj between '" + beforesj + "' and '" + nowsj
								+ "') ");

		String sql = "select (select xxmc from xtszb) xxmc,(select xxdm from xtszb) xxdm,b.yxmc,a.zymc,b.zydm,b.szbj,b.xm,a.xsxh,b.sfzhm,b.xbdm,c.csrq,b.mzdm,b.zzmmdm,b.syszd,a.xxsh,b.syszddm,b.xldm,b.xz,b.sfzz,b.sfsf,b.sfdl,b.zslb,b.pyfsdm,b.dxhwpdw,b.rxsj,b.bysj,a.lxdh,b.sjhm,b.qqhm,a.lxdz,b.dzxx,a.yzbm,b.yxdm,b.zydm,b.zydm,a.shsjqk,a.hjqk,b.wydj,b.jsjdj,a.grtc,b.zyyx,b.sybz1,b.sybz2,b.sybz3 from jygl_grjlb a left join zjgsjyxytj b on a.xsxh=b.xh left join view_xsxxb c on a.xsxh=c.xh"
				+ querry.toString();
		Vector<Object> vec = new Vector<Object>();
		WritableWorkbook wwb = Workbook.createWorkbook(response
				.getOutputStream());
		WritableSheet ws = wwb.createSheet("���ݵ���", 0);
		try {
			String ColumnName[] = new String[] { "xxmc", "xxdm", "yxmc",
					"zymc", "zydm", "szbj", "xm", "xsxh", "sfzhm", "xbdm",
					"csrq", "mzdm", "zzmmdm", "syszd", "syszddm", "xldm", "xz",
					"sfzz", "sfsf", "sfdl", "zslb", "pyfsdm", "dxhwpdw",
					"rxsj", "bysj", "lxdh", "sjhm", "qqhm", "lxdz", "dzxx",
					"yzbm", "yxdm", "zydm", "zydm", "shsjqk", "hjqk", "wydj",
					"jsjdj", "grtc", "zyyx", "sybz1", "sybz2", "sybz3" };
			String ColumnNameCN[] = new String[] { "ѧУ����", "ѧУ����", "Ժϵ����",
					"רҵ����", "רҵ����", "���ڰ༶", "ѧ������", "ѧ��", "���֤��", "�Ա����",
					"��������", "�������", "������ò", "��Դ��", "��Դ�ش���", "ѧ�����", "ѧ��",
					"�Ƿ���ְ", "�Ƿ�ʦ��", "�Ƿ����", "���������", "������ʽ����", "�����ί�൥λ",
					"��ѧ���", "��ҵ���", "��ϵ�绰", "�ֻ�����", "QQ����", "��ϵ��ַ", "��������",
					"�ʱ�", "Ժϵ���", "רҵ���", "�༶���", "������Ṥ�����", "�����", "Ӣ��ˮƽ",
					"�����ˮƽ", "��Ȥ�س�", "ְҵ����", "��ҵ������1", "��ҵ������2", "��ҵ������3" };
			for (int m = 0; m < ColumnNameCN.length; m++) {
				ws.addCell(new Label(m, 0, ColumnNameCN[m]));
			}
			vec.addAll(dao.rsToVator(sql, new String[] {}, ColumnName));
			int k = ColumnName.length;
			for (int i = 0; i < vec.size(); i++) {
				String[] tmp = (String[]) vec.toArray()[i];
				for (int j = 0; j < k; j++) {
					ws.addCell(new Label(j, i + 1, tmp[j]));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("���ݵ���ʧ��!");
		} finally {
			wwb.write();
			wwb.close();
		}
		return mapping.findForward("");
	}

	// ԤԼ�޸�
	private ActionForward updateYuyue(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DAO dao = DAO.getInstance();
		String act = request.getParameter("act");
		String doType = request.getParameter("doType");
		String doType2 = request.getParameter("doType2");
		String xsxh = request.getParameter("xsxh");
		String meet = request.getParameter("meet");
		String tableName = "JYGL_ZXYYGLB";
		String pk = "num||xsxh||tjsj"; // ����
		String sql = "";
		String pkValue = request.getParameter("pkValue");
		HashMap<String, String> map = new HashMap<String, String>();

		if ("".equals(pkValue) || null == pkValue) {
			pkValue = xsxh;
		}
		if ("update".equals(doType)) {
			String qwyjtime = request.getParameter("qwyjtime");
			String zxwtjs = DealString.toGBK(request.getParameter("zxwtjs"));

			boolean judge = false;
			judge = StandardOperation.update(tableName, new String[] { "meet",
					"qwyjtime", "zxwtjs" }, new String[] { meet, qwyjtime,
					zxwtjs }, pk, pkValue, request);
			if (judge) {
				request.setAttribute("update", "ok");

				// �޸Ĳ����ļ�¼
				HttpSession session = request.getSession();
				String userName = session.getAttribute("userName").toString();
				String whensj = (dao
						.getOneRs(
								"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual",
								new String[] {}, new String[] { "sdate" }))[0]; // �ύʱ��

				StandardOperation.insert("JYGL_USERCZMXB",
						new String[] { "userid", "dowhat", "whichtable",
								"whichpk", "whensj" }, new String[] { userName,
								"�޸�", "��ѯԤԼ�����", "ѧ��:" + xsxh, whensj },
						request);
			} else {
				request.setAttribute("update", "no");
			}
		}

		if ("go".equals(act)) {
			sql = "select * from " + tableName + " where num||xsxh||tjsj=?";
			String[] colList = dao.getColumnName("select * from " + tableName
					+ " where 1=2"); // ������������
			String[] zxyuyueinfo = dao.getOneRs(sql, new String[] { pkValue },
					colList);
			if (zxyuyueinfo != null) {
				for (int i = 0; i < colList.length; i++) {
					map.put(colList[i].toLowerCase(), zxyuyueinfo[i]); // ����¼ѭ������map��
				}
				// ��ȡ��ѯʦ����
				String num = map.get("num");
				sql = "select name from jygl_zxjsxxb where num=?";
				String[] zxsinfo = dao.getOneRs(sql, new String[] { num },
						new String[] { "name" });
				if (zxsinfo != null) {
					map.put("zxsname", zxsinfo[0]);
				}
				// ��ȡѧ��������Ϣ
				xsxh = map.get("xsxh");
				sql = "select * from view_xsjbxx where xh=?";
				String[] stuinfo = dao.getOneRs(sql, new String[] { xsxh },
						new String[] { "xm", "xb", "nj", "xymc", "zymc", });
				if (stuinfo != null) {
					map.put("name", stuinfo[0]);
					map.put("xb", stuinfo[1]);
					map.put("nj", stuinfo[2]);
					map.put("xymc", stuinfo[3]);
					map.put("zymc", stuinfo[4]);
				}
				if ("meetonchange".equals(doType2)) {
					map.put("meet", meet);
				}
				if ("��".equals(map.get("meet"))) {
					request.setAttribute("mt", "yes");
				} else {
					request.setAttribute("mt", "no");
				}
			}
		}

		request.setAttribute("rs", map);
		request.setAttribute("pkValue", pkValue);
		return mapping.findForward("success");
	}

	// ��ѯԤԼ�����ѯ
	private ActionForward jyzxResultQuery(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response, String userType) throws Exception {
		DAO dao = DAO.getInstance();
		String sql = "";
		String pk = "num||xsxh||tjsj";
		String[] colList = null;
		String[] colListCN = null;
		ArrayList<Object> rs = new ArrayList<Object>(); // ��ü�¼
		String rsNum = "0";
		String tableName = "jygl_zxyyglb";
		List topTr = null;
		// HashMap<String, String> map = new HashMap<String, String>();
		HttpSession session = request.getSession();
		String userName = session.getAttribute("userName").toString();
		String userlb = session.getAttribute("userType").toString();

		if (userType.equals("teacher")) {
			request.setAttribute("isteacher", "ok");
		} else {
			request.setAttribute("isteacher", "no");
		}

		if (userType.equals("teacher")) {
			if (userlb.equals("admin")) {
				sql = "select " + pk + " ����,rownum �к�,a.* from " + tableName
						+ " a ";
			} else {
				sql = "select num from JYGL_ZXJSXXB where userid=?";
				String[] teainfo = dao.getOneRs(sql, new String[] { userName },
						new String[] { "num" });
				String num = "!!00";
				if (teainfo != null && teainfo.length > 0) {
					num = teainfo[0];
				}
				sql = "select " + pk + " ����,rownum �к�,a.* from " + tableName
						+ " a " + " where num='" + num + "'";
			}
			colList = new String[] { "����", "�к�", "num", "xsxh", "tjsj",
					"qwyjtime", "xsqr", "zxsqr", "meet" };
			colListCN = dao.getColumnNameCN(colList, tableName);
			topTr = dao.arrayToList(colList, colListCN);
			if ((request.getParameter("act") != null)
					&& request.getParameter("act").equalsIgnoreCase("go")) {
				rs.addAll(dao.rsToVator(sql, new String[] {}, colList));
				if (rs == null) {
					rsNum = "0";
				} else {
					rsNum = String.valueOf(rs.size());
				}
			}
			request.setAttribute("who", "teacher");
		}

		if (userType.equals("student")) {
			sql = "select " + pk + " ����,rownum �к�,a.* from " + tableName
					+ " a " + " where xsxh='" + userName + "'";
			;
			colList = new String[] { "����", "�к�", "num", "xsxh", "tjsj",
					"qwyjtime", "xsqr", "zxsqr", "meet" };
			colListCN = dao.getColumnNameCN(colList, tableName);
			topTr = dao.arrayToList(colList, colListCN);
			if ((request.getParameter("act") != null)
					&& request.getParameter("act").equalsIgnoreCase("go")) {
				rs.addAll(dao.rsToVator(sql, new String[] {}, colList));
				if (rs == null) {
					rsNum = "0";
				} else {
					rsNum = String.valueOf(rs.size());
				}
			}
			request.setAttribute("who", "student");
		}

		request.setAttribute("rs", rs); // �������ݼ�
		request.setAttribute("topTr", topTr); // ���ͱ�ͷ
		request.setAttribute("rsNum", rsNum); // ���ͼ�¼��
		return mapping.findForward("success");
	}

	// ѧ����ѯ�ظ�
	private ActionForward answerYuyueStu(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String pk = "num||xsxh||tjsj";
		String sql = ""; // sql���9
		DAO dao = DAO.getInstance();
		String realTable = "JYGL_ZXYYGLB";
		String doType = request.getParameter("doType");
		String act = request.getParameter("act");
		String pkValue = request.getParameter("pkValue");
		HashMap<String, String> map = new HashMap<String, String>();

		if ("answer".equals(act)) {
			String xsqr = DealString.toGBK(request.getParameter("xsqr"));
			String xsqrsj = (dao
					.getOneRs(
							"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual", // ����ʱ��
							// //
							// ȡ�����ݿ���ʱ��
							new String[] {}, new String[] { "sdate" }))[0];
			boolean judge = false;
			judge = StandardOperation.update(realTable, new String[] { "xsqr",
					"xsqrsj" }, new String[] { xsqr, xsqrsj }, pk, pkValue,
					request);
			if (judge) {
				request.setAttribute("answer", "ok");
			} else {
				request.setAttribute("answer", "no");
			}
		}

		if ((doType == null) || doType.equalsIgnoreCase("")) {
			// �����쳣
			return mapping.findForward("false");
		} else if (doType.equalsIgnoreCase("view")) {
			// ��ѯ����
			if ("".equals(pkValue)) {
				pkValue = request.getParameter("xsxh");
			}
			sql = "select * from " + realTable + " where " + pk + "='"
					+ pkValue + "'";
			String[] colList = dao.getColumnName("select * from " + realTable
					+ " where 1=2"); // ������������
			String[] zxyyglinfo = dao.getOneRs(sql, new String[] {}, colList);
			if (zxyyglinfo != null) {
				for (int i = 0; i < colList.length; i++) {
					map.put(colList[i].toLowerCase(), zxyyglinfo[i]); // ����¼ѭ������map��
				}
			}
			if ("����ԼX".equals(map.get("xsqr"))
					|| "��ȷ�ϡ�".equals(map.get("xsqr"))) {
				request.setAttribute("xsqr", "no");
			} else {
				request.setAttribute("xsqr", "ok");
			}
			// ��ȡ��ѯʦ����
			String num = map.get("num");
			sql = "select name from jygl_zxjsxxb where num=?";
			String[] zxsinfo = dao.getOneRs(sql, new String[] { num },
					new String[] { "name" });
			if (zxsinfo != null) {
				map.put("zxsname", zxsinfo[0]);
			}
			// ��ȡѧ��������Ϣ
			String xsxh = map.get("xsxh");
			sql = "select * from view_xsjbxx where xh=?";
			String[] stuinfo = dao.getOneRs(sql, new String[] { xsxh },
					new String[] { "xm", "xb", "nj", "xymc", "zymc", });
			if (stuinfo != null) {
				map.put("name", stuinfo[0]);
				map.put("xb", stuinfo[1]);
				map.put("nj", stuinfo[2]);
				map.put("xymc", stuinfo[3]);
				map.put("zymc", stuinfo[4]);
			}
			// ���ύʱ��ת������
			String sjyear = null;
			String sjmon = null;
			String sjday = null;
			String sjhour = null;
			String sjmin = null;
			String sjss = null;
			/*
			 * if (null != map.get("tjsj")) { String sj =
			 * map.get("tjsj").toString(); sjyear = sj.substring(0, 4); sjmon =
			 * sj.substring(4, 6); sjday = sj.substring(6, 8); sjhour =
			 * sj.substring(8, 10); sjmin = sj.substring(10, 12); sjss =
			 * sj.substring(12, 14); tjsj = sjyear + "��" + sjmon + "��" + sjday +
			 * "�� " + sjhour + ":" + sjmin + ":" + sjss; map.put("tjsj", tjsj); }
			 */
			// ����ѯʦȷ��ʱ��ת������
			String zxsqrsj = null;
			if (null != map.get("zxsqrsj")) {
				String sj = map.get("zxsqrsj").toString();
				sjyear = sj.substring(0, 4);
				sjmon = sj.substring(4, 6);
				sjday = sj.substring(6, 8);
				sjhour = sj.substring(8, 10);
				sjmin = sj.substring(10, 12);
				sjss = sj.substring(12, 14);
				zxsqrsj = sjyear + "��" + sjmon + "��" + sjday + "�� " + sjhour
						+ ":" + sjmin + ":" + sjss;
				map.put("zxsqrsj", zxsqrsj);
			}
			// ��ѧ��ȷ��ʱ��ת������
			String xsqrsj = null;
			if (null != map.get("xsqrsj")) {
				String sj = map.get("xsqrsj").toString();
				sjyear = sj.substring(0, 4);
				sjmon = sj.substring(4, 6);
				sjday = sj.substring(6, 8);
				sjhour = sj.substring(8, 10);
				sjmin = sj.substring(10, 12);
				sjss = sj.substring(12, 14);
				xsqrsj = sjyear + "��" + sjmon + "��" + sjday + "�� " + sjhour
						+ ":" + sjmin + ":" + sjss;
				map.put("xsqrsj", xsqrsj);
			}
		}
		request.setAttribute("rs", map);// �������ݼ�
		return mapping.findForward("success");
	}

	// ��ҵ��Ƹά��
	private ActionForward jyzpWeiHu(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			String userType) throws Exception {
		DAO dao = DAO.getInstance();
		String sql = "";
		HashMap<String, String> map = new HashMap<String, String>();
		ArrayList<Object> rs = new ArrayList<Object>(); // ��ü�¼
		String pk = "username";
		String tableName = "JYGL_JYZPWHB";
		String rsNum = "";
		String doType = request.getParameter("doType");
		String username = request.getParameter("username");
		String gsmc = DealString.toGBK(request.getParameter("gsmc"));
		HttpSession session = request.getSession();
		String userName = session.getAttribute("userName").toString();

		String xxdm = StandardOperation.getXxdm();
		List gsmcList = null;
		if (Globals.XXDM_ZGDZDX.equals(xxdm)) {
			sql = "select distinct dwmc from dwxxb";
			gsmcList = dao.getList(sql, new String[] {},
					new String[] { "dwmc" });
		} else {
			sql = "select distinct gsmc from jygl_zpxxb";
			gsmcList = dao.getList(sql, new String[] {},
					new String[] { "gsmc" });
		}
		request.setAttribute("gsmcList", gsmcList);

		if ("add".equals(doType)) {
			boolean judge = false;
			judge = StandardOperation.insert(tableName, new String[] {
					"username", "gsmc" }, new String[] { username, gsmc },
					request);
			if (judge) {
				request.setAttribute("add", "ok");

				// ���Ӳ����ļ�¼
				if ("teacher".equals(userType)) {
					String whensj = (dao
							.getOneRs(
									"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual",
									new String[] {}, new String[] { "sdate" }))[0]; // �ύʱ��

					StandardOperation.insert("JYGL_USERCZMXB", new String[] {
							"userid", "dowhat", "whichtable", "whichpk",
							"whensj" }, new String[] { userName, "����",
							"��ҵ��Ƹά����", "�û���:" + username + " ��˾����:" + gsmc,
							whensj }, request);
				}
			} else {
				request.setAttribute("add", "no");
			}
		}
		if ("del".equals(doType)) {
			boolean judge = false;
			judge = StandardOperation.delete(tableName, pk, username, request);
			if (judge) {
				request.setAttribute("delete", "ok");

				// ɾ�������ļ�¼

				String whensj = (dao
						.getOneRs(
								"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual",
								new String[] {}, new String[] { "sdate" }))[0]; // �ύʱ��

				judge = StandardOperation.insert("JYGL_USERCZMXB",
						new String[] { "userid", "dowhat", "whichtable",
								"whichpk", "whensj" }, new String[] { userName,
								"ɾ��", "��ҵ��Ƹά����", "�û���:" + username, whensj },
						request);
			} else {
				request.setAttribute("delete", "no");
			}
		}

		sql = "select " + pk + " ����,rownum �к�,a.* from " + tableName + " a ";
		String[] colList = new String[] { "����", "�к�", "username", "gsmc" };
		String[] colListCN = dao.getColumnNameCN(colList, tableName);
		List topTr = dao.arrayToList(colList, colListCN);
		if ((request.getParameter("act") != null)
				&& request.getParameter("act").equalsIgnoreCase("go")) {
			rs.addAll(dao.rsToVator(sql, new String[] {}, colList));
			if (rs == null) {
				rsNum = "0";
			} else {
				rsNum = String.valueOf(rs.size());
			}
		}

		request.setAttribute("rsNum", rsNum);
		request.setAttribute("rs", rs);
		request.setAttribute("topTr", topTr); // ���ͱ�ͷ
		request.setAttribute("rs1", map);
		return mapping.findForward("success");
	}

	// ��ҵЭ��ά��
	private ActionForward jyxyWeiHu(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			String userType) throws Exception {
		DAO dao = DAO.getInstance();
		String sql = "";
		HashMap<String, String> map = new HashMap<String, String>();
		ArrayList<Object> rs = new ArrayList<Object>(); // ��ü�¼
		String pk = "username";
		String tableName = "JYGL_JYXYWHB";
		String rsNum = "";
		String doType = request.getParameter("doType");
		String username = request.getParameter("username");
		String usertype = DealString.toGBK(request.getParameter("usertype"));
		String nj = request.getParameter("nj");
		String xymc = DealString.toGBK(request.getParameter("xymc"));
		String zymc = DealString.toGBK(request.getParameter("zymc"));
		String bj = DealString.toGBK(request.getParameter("bj"));
		HttpSession session = request.getSession();
		String userName = session.getAttribute("userName").toString();

		if ("refresh".equals(doType)) {
			map.put("username", username);
			map.put("usertype", usertype);
			map.put("nj", nj);
			map.put("xymc", xymc);
			map.put("zymc", zymc);
			map.put("bj", bj);
		}

		// sql = "select distinct gsmc from jygl_zpxxb";
		// List gsmcList = dao.getList(sql, new String[] {},
		// new String[] { "gsmc" });
		// request.setAttribute("gsmcList", gsmcList);

		if ("add".equals(doType)) {
			boolean judge = false;
			judge = StandardOperation.insert(tableName, new String[] {
					"username", "usertype", "nj", "xymc", "zymc", "bj" },
					new String[] { username, usertype, nj, xymc, zymc, bj },
					request);
			if (judge) {
				request.setAttribute("add", "ok");

				// ���Ӳ����ļ�¼
				if ("teacher".equals(userType)) {
					String whensj = (dao
							.getOneRs(
									"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual",
									new String[] {}, new String[] { "sdate" }))[0]; // �ύʱ��

					StandardOperation.insert("JYGL_USERCZMXB", new String[] {
							"userid", "dowhat", "whichtable", "whichpk",
							"whensj" }, new String[] { userName, "����",
							"��ҵЭ��ά����",
							"�û���:" + username + "  �û�����:" + usertype, whensj },
							request);
				}
			} else {
				request.setAttribute("add", "no");
			}
		}
		if ("del".equals(doType)) {
			boolean judge = false;
			judge = StandardOperation.delete(tableName, pk, username, request);
			if (judge) {
				request.setAttribute("delete", "ok");

				// ɾ�������ļ�¼

				String whensj = (dao
						.getOneRs(
								"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual",
								new String[] {}, new String[] { "sdate" }))[0]; // �ύʱ��

				judge = StandardOperation.insert("JYGL_USERCZMXB",
						new String[] { "userid", "dowhat", "whichtable",
								"whichpk", "whensj" }, new String[] { userName,
								"ɾ��", "��ҵЭ��ά����", "�û���:" + username, whensj },
						request);
			} else {
				request.setAttribute("delete", "no");
			}
		}

		sql = "select " + pk + " ����,rownum �к�,a.* from " + tableName + " a ";
		String[] colList = new String[] { "����", "�к�", "username", "usertype",
				"xymc", "zymc", "bj" };
		String[] colListCN = dao.getColumnNameCN(colList, tableName);
		List topTr = dao.arrayToList(colList, colListCN);
		if ((request.getParameter("act") != null)
				&& request.getParameter("act").equalsIgnoreCase("go")) {
			rs.addAll(dao.rsToVator(sql, new String[] {}, colList));
			if (rs == null) {
				rsNum = "0";
			} else {
				rsNum = String.valueOf(rs.size());
			}
		}
		String xydm = "";
		sql = "select xydm from view_njxyzybj where xymc=?";
		String[] xyinfo = dao.getOneRs(sql, new String[] { xymc },
				new String[] { "xydm" });
		if (null != xyinfo) {
			xydm = xyinfo[0];
		}

		String zydm = "";
		sql = "select zydm from view_njxyzybj where zymc=?";
		String[] zyinfo = dao.getOneRs(sql, new String[] { zymc },
				new String[] { "zydm" });
		if (null != zyinfo) {
			zydm = zyinfo[0];
		}

		request.setAttribute("njList", dao.getNjList()); // �����꼶�б�
		request.setAttribute("xyList", dao.getXyList()); // ����ѧԺ�б�
		request.setAttribute("zyList", dao.getZyList(xydm));// ����רҵ�б�
		request.setAttribute("bjList", dao.getBjList(xydm, zydm, nj));// ���Ͱ༶�б�
		request.setAttribute("rsNum", rsNum);
		request.setAttribute("rs", rs);
		request.setAttribute("topTr", topTr); // ���ͱ�ͷ
		request.setAttribute("rs1", map);
		return mapping.findForward("success");
	}

	// �û�������ϸ
	private ActionForward userczmx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DAO dao = DAO.getInstance(); // ʵ����ͨ�÷�������������ݿ�����
		ArrayList<Object> rs = new ArrayList<Object>();
		String[] colList = null;
		String[] colListCN = null;
		String sql = ""; // sql���
		String querry = " where 1=1 "; // sql����
		String tableName = "JYGL_USERCZMXB"; // ��ѯ��ϢԴ����Ϊ��ͼ����
		String rsNum = "0";// ���صļ�¼��
		HashMap<String, String> map = new HashMap<String, String>();
		String doType = request.getParameter("doType");
		String doType2 = request.getParameter("doType2");
		String pk = "rowid";
		String pkValue = request.getParameter("pkValue");

		if ("del".equals(doType2)) {
			boolean judge = false;
			judge = StandardOperation.delete(tableName, pk, pkValue, request);
			if (judge) {
				request.setAttribute("delete", "ok");
			} else {
				request.setAttribute("delete", "no");
			}
		}
		String userid = request.getParameter("userid");
		String dowhat = DealString.toGBK(request.getParameter("dowhat"));
		String whichtable = DealString
				.toGBK(request.getParameter("whichtable"));

		// ���ڷ���ʱ��Ĵ��� ���ڲ�ѯ����
		String xjsj = DealString.toGBK(request.getParameter("xjsj")); // ���ʱ��

		String nowsj = (dao.getOneRs(
				"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual", // ����ʱ��
				// //
				// ȡ�����ݿ���ʱ��
				new String[] {}, new String[] { "sdate" }))[0];
		String sjyear = nowsj.substring(0, 4);
		String sjmonth = nowsj.substring(4, 6);
		String sjday = nowsj.substring(6, 8);
		String sjqt = nowsj.substring(8, 14);
		String nowsj1 = sjyear + "-" + sjmonth + "-" + sjday;
		dealDate dealdate = new dealDate();
		String beforesj = "";
		if (!"".equals(xjsj) && "-1".equals(xjsj)) {
			beforesj=nowsj.substring(0,8)+"000000";
			nowsj=nowsj.substring(0,8)+"235959";
		}else if(!"".equals(xjsj) ){
			beforesj = dealdate.getDate2(Integer.parseInt(xjsj), nowsj1);
			beforesj = beforesj.replaceAll("-", "") + sjqt;
		}

		// ���ϴ��ύ��ֵ����ȥ
		if ("query".equals(doType)) {
			map.put("userid", userid);
			map.put("dowhat", dowhat);
			map.put("whichtable", whichtable);
			map.put("xjsj", xjsj);
		}

		if (userid == null) {
			userid = "";
		}
		if (dowhat == null) {
			dowhat = "";
		}
		if (whichtable == null) {
			whichtable = "";
		}
		if (xjsj == null) {
			xjsj = "";
		}
		if ((userid == null) || userid.equals("")) {
			querry += "and 1=1 ";
		} else {
			querry += "and userid like '%" + userid + "%' ";
		}
		if ((dowhat == null) || dowhat.equals("")) {
			querry += "and 1=1 ";
		} else {
			querry += "and dowhat = '" + dowhat + "' ";
		}
		if ((whichtable == null) || whichtable.equals("")) {
			querry += " and 1=1 ";
		} else {
			querry += " and whichtable='" + whichtable + "' ";
		}
		if ((xjsj == null) || xjsj.equals("")) {
			querry += " and 1=1 ";
		} else {
			querry += "and (whensj between '" + beforesj + "' and '" + nowsj
					+ "') ";
		}

		if ("delall".equals(doType2)) {
			boolean judge = false;
			sql = "delete from " + tableName + " " + querry;
			judge = dao.runUpdate(sql, new String[] {});
			if (judge) {
				request.setAttribute("deleteall", "ok");
			} else {
				request.setAttribute("deleteall", "on");
			}
		}

		sql = "select  rownum �к�,a.rowid,a.* from " + tableName + " a "
				+ querry;
		colList = new String[] { "�к�", "userid", "dowhat", "whichtable",
				"whichpk", "whensj" };
		colListCN = dao.getColumnNameCN(colList, tableName);
		List topTr = dao.arrayToList(colList, colListCN);
		colList = new String[] { "rowid", "�к�", "userid", "dowhat",
				"whichtable", "whichpk", "whensj" };
		if ((request.getParameter("act") != null)
				&& request.getParameter("act").equalsIgnoreCase("go")) {
			rs.addAll(dao.rsToVator(sql, new String[] {}, colList));
			if (rs == null) {
				rsNum = "0";
			} else {
				rsNum = String.valueOf(rs.size());
			}
		}

		request.setAttribute("rs", rs);// �������ݼ�
		request.setAttribute("topTr", topTr);// ���ͱ�ͷ
		request.setAttribute("rsNum", rsNum);// ���ͼ�¼��
		request.setAttribute("rs1", map); // ���صĲ�ѯ����
		return mapping.findForward("success");
	}

	// һ��ͳ��
	private ActionForward onebutton(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DAO dao = DAO.getInstance();
		String doType = request.getParameter("doType");
		String sql = "";
		HashMap<String, String> map = new HashMap<String, String>();
		int allxybyrsnum1 = 0; // 1�������ѧԺ�ı�ҵ����
		int allxyqyrsnum1 = 0; // 1�������ѧԺ��ǩԼ����
		int allxyjyrsnum1 = 0; // 1�������ѧԺ�ľ�ҵ����
		int allxybyrsnum2 = 0; // 2�������ѧԺ�ı�ҵ����
		int allxyqyrsnum2 = 0; // 2�������ѧԺ��ǩԼ����
		int allxyjyrsnum2 = 0; // 2�������ѧԺ�ľ�ҵ����
		String allxyqyl1 = ""; // ��һ�������ѧԺǩԼ��
		String allxyqyl2 = ""; // �ڶ��������ѧԺǩԼ��
		String allxyjyl1 = ""; // ��һ�������ѧԺ��ҵ��
		String allxyjyl2 = ""; // �ڶ��������ѧԺ��ҵ��
		String bynd1 = request.getParameter("bynd1");
		String bynd2 = request.getParameter("bynd2");
		// �ҳ����е�ѧԺ�����Ƽ��������string�͵�����
		List<HashMap<String, String>> xylist = dao.getXyList();
		String xymcstring = "";
		String xydmstring = "";
		String mark = "%NNNNN%";
		HashMap<String, String> xymap = new HashMap<String, String>();
		List<ArrayList<String[]>> list = new ArrayList<ArrayList<String[]>>();

		if ("ok".equals(doType)) {

			map.put("bynd1", bynd1);
			map.put("bynd2", bynd2);

			for (int i = 0; i < xylist.size(); i++) {
				xymap = xylist.get(i);
				String xymcpoint = xymap.get("xymc");
				String xydmpoint = xymap.get("xydm");
				xymcstring += xymcpoint + mark;
				xydmstring += xydmpoint + mark;
			}
			String[] xymc = xymcstring.split(mark);
			String[] xydm = xydmstring.split(mark);
			String xymingcheng = "";
			String xydaima = "";

			// ����ͷ
			String[] topTr = { "ѧԺ", "רҵ", bynd1 + "��ȱ�ҵ����", bynd1 + "���ǩԼ����",
					bynd1 + "��Ⱦ�ҵ����", bynd1 + "���ǩԼ��", bynd2 + "���ǩԼ��",
					bynd1 + "��Ⱦ�ҵ��", bynd2 + "��Ⱦ�ҵ��" };
			ArrayList<HashMap<String, String>> top = new ArrayList<HashMap<String, String>>();
			for (int k = 0; k < topTr.length; k++) {
				HashMap<String, String> maptop = new HashMap<String, String>();
				maptop.put("top", topTr[k]);
				top.add(maptop); // ��map�е�ֵ�Ž���ͷ����
			}
			// ѭ��ȡ��ѧԺ�б�
			for (int j = 0; j < xymc.length; j++) {
				xymingcheng = xymc[j];
				xydaima = xydm[j];

				// ȡ����ѧԺ��רҵ�����������
				HashMap<String, String> zymap = new HashMap<String, String>();
				List<HashMap<String, String>> zylist = dao.getZyList(xydaima);
				String zymcstring = "";
				String zydmstring = "";
				for (int k = 0; k < zylist.size(); k++) {
					zymap = zylist.get(k);
					String zymcpoint = zymap.get("zymc");
					String zydmpoint = zymap.get("zydm");
					zymcstring += zymcpoint + mark;
					zydmstring += zydmpoint + mark;
				}
				String[] zymcall = zymcstring.split(mark);
				String zymc = "";
				ArrayList<String[]> rs = new ArrayList<String[]>();
				// ѭ��ȡ��רҵ�б�
				for (int i = 0; i < zymcall.length; i++) {
					if (zymcall != null) {
						zymc = zymcall[i];
					}
					ArrayList<Object> byrsrs1 = new ArrayList<Object>();
					ArrayList<Object> qyrsrs1 = new ArrayList<Object>();
					ArrayList<Object> jyrsrs1 = new ArrayList<Object>();
					// ���1��ҵ����
					sql = "select b.xsxh from jygl_xsjbxxb b where  exists(select a.xh from view_xsjbxx a where a.zymc=? and a.xh=b.xsxh) and b.bynd=?";
					byrsrs1.addAll(dao.rsToVator(sql, new String[] { zymc,
							bynd1 }, new String[] { "xsxh" }));
					String byrsnum1 = "";
					if (null != byrsrs1) {
						byrsnum1 = String.valueOf(byrsrs1.size());
					}

					// ���1ǩԼ����
					sql = "select b.xsxh from jygl_jyxy b where exists(select a.xh from view_xsjbxx a where a.zymc=? and a.xh=b.xsxh) and b.bynd=?";
					qyrsrs1.addAll(dao.rsToVator(sql, new String[] { zymc,
							bynd1 }, new String[] { "xsxh" }));
					String qyrsnum1 = "";
					if (null != qyrsrs1) {
						qyrsnum1 = String.valueOf(qyrsrs1.size());
					}

					// ���1ǩԼ��
					int inqyrsnum = Integer.valueOf(qyrsnum1);// ǩԼ����
					int inbyrsnum = Integer.valueOf(byrsnum1);// ��ҵ����
					String qyl1 = "";
					if (inbyrsnum != 0) {
						qyl1 = (dao.getOneRs("select to_char(" + inqyrsnum
								+ "/" + inbyrsnum
								+ "*100,'999.99')||'%' aa from dual",
								new String[] {}, new String[] { "aa" }))[0];// ǩԼ��
						if ("    .00%".equals(qyl1)) {
							qyl1 = "0.00%";
						}
					}

					ArrayList<Object> byrsrs2 = new ArrayList<Object>();
					ArrayList<Object> qyrsrs2 = new ArrayList<Object>();
					ArrayList<Object> jyrsrs2 = new ArrayList<Object>();
					// ���2��ҵ����
					sql = "select b.xsxh from jygl_xsjbxxb b where exists(select xh from view_xsjbxx a where zymc=? and a.xh=b.xsxh) and b.bynd=?";
					byrsrs2.addAll(dao.rsToVator(sql, new String[] { zymc,
							bynd2 }, new String[] { "xsxh" }));
					String byrsnum2 = "";
					if (null != byrsrs2) {
						byrsnum2 = String.valueOf(byrsrs2.size());
					}
					map.put("byrs2", byrsnum2);

					// ���2ǩԼ����
					sql = "select b.xsxh from jygl_jyxy b where exists(select a.xh from view_xsjbxx a where a.zymc=? and a.xh=b.xsxh) and b.bynd=?";
					qyrsrs2.addAll(dao.rsToVator(sql, new String[] { zymc,
							bynd2 }, new String[] { "xsxh" }));
					String qyrsnum2 = "";
					if (null != qyrsrs2) {
						qyrsnum2 = String.valueOf(qyrsrs2.size());
					}
					map.put("qyrs", qyrsnum2);

					// ���2ǩԼ��
					inqyrsnum = Integer.valueOf(qyrsnum2);// ǩԼ����
					inbyrsnum = Integer.valueOf(byrsnum2);// ��ҵ����
					String qyl2 = "";
					if (inbyrsnum != 0) {
						qyl2 = (dao.getOneRs("select to_char(" + inqyrsnum
								+ "/" + inbyrsnum
								+ "*100,'999.99')||'%' aa from dual",
								new String[] {}, new String[] { "aa" }))[0];// ǩԼ��
						if ("    .00%".equals(qyl2)) {
							qyl2 = "0.00%";
						}
					}
					// ���1��ҵ����
					sql = "select b.xsxh from jygl_jyxy b where exists(select a.xh from view_xsjbxx a where a.zymc=? and a.xh=b.xsxh) and b.bynd=? and b.wjybz='0'";
					jyrsrs1.addAll(dao.rsToVator(sql, new String[] { zymc,
							bynd1 }, new String[] { "xsxh" }));
					String jyrsnum1 = "";
					if (null != jyrsrs1) {
						jyrsnum1 = String.valueOf(jyrsrs1.size());
					}
					// ���2��ҵ����
					sql = "select b.xsxh from jygl_jyxy b where  exists(select a.xh from view_xsjbxx a where a.zymc=? and a.xh=b.xsxh) and b.bynd=? and b.wjybz='0'";
					jyrsrs2.addAll(dao.rsToVator(sql, new String[] { zymc,
							bynd2 }, new String[] { "xsxh" }));
					String jyrsnum2 = "";
					if (null != jyrsrs1) {
						jyrsnum2 = String.valueOf(jyrsrs2.size());
					}
					// ���1��ҵ��
					int injyrsnum = Integer.valueOf(jyrsnum1);// ��ҵ����
					inbyrsnum = Integer.valueOf(byrsnum1);// ��ҵ����
					String jyl1 = "";
					if (inbyrsnum != 0) {
						jyl1 = (dao.getOneRs("select to_char(" + injyrsnum
								+ "/" + inbyrsnum
								+ "*100,'999.99')||'%' aa from dual",
								new String[] {}, new String[] { "aa" }))[0];// ��ҵ��
						if ("    .00%".equals(jyl1)) {
							jyl1 = "0.00%";
						}
					}
					// ���2��ҵ��
					injyrsnum = Integer.valueOf(jyrsnum2);// ��ҵ����
					inbyrsnum = Integer.valueOf(byrsnum2);// ��ҵ����
					String jyl2 = "";
					if (inbyrsnum != 0) {
						jyl2 = (dao.getOneRs("select to_char(" + injyrsnum
								+ "/" + inbyrsnum
								+ "*100,'999.99')||'%' aa from dual",
								new String[] {}, new String[] { "aa" }))[0];// ��ҵ��
						if ("    .00%".equals(jyl2)) {
							jyl2 = "0.00%";
						}
					}

					String[] tjvalues = { xymingcheng, zymc, byrsnum1,
							qyrsnum1, jyrsnum1, qyl1, qyl2, jyl1, jyl2 }; // ͳ�Ƴ�����һ��רҵ����

					rs.add(tjvalues);// ����һ��רҵ��ͳ������ �����¼��
					if (i == ((zymcall.length) - 1)) {
						// 1���ѧԺ��ҵ������
						sql = "select b.xsxh from jygl_xsjbxxb b where exists(select a.xh from view_xsjbxx a where a.xymc=? and a.xh=b.xsxh) and b.bynd=?";
						ArrayList<Object> xybyrsrs1 = new ArrayList<Object>();
						xybyrsrs1.addAll(dao.rsToVator(sql, new String[] {
								xymingcheng, bynd1 }, new String[] { "xsxh" }));
						String xybyrsnum1 = "";
						if (null != xybyrsrs1) {
							xybyrsnum1 = String.valueOf(xybyrsrs1.size());
						}
						// 1���ѧԺǩԼ������
						sql = "select xsxh from jygl_jyxy where xymc=? and bynd=?";
						ArrayList<Object> xyqyrsrs1 = new ArrayList<Object>();
						xyqyrsrs1.addAll(dao.rsToVator(sql, new String[] {
								xymingcheng, bynd1 }, new String[] { "xsxh" }));
						String xyqyrsnum1 = "";
						if (null != xyqyrsrs1) {
							xyqyrsnum1 = String.valueOf(xyqyrsrs1.size());
						}
						// 1���ѧԺǩԼ��
						int inxyqyrsnum = Integer.valueOf(xyqyrsnum1);// ǩԼ����
						int inxybyrsnum = Integer.valueOf(xybyrsnum1);// ��ҵ����
						String xyqyl1 = "";
						if (inxybyrsnum != 0) {
							xyqyl1 = (dao.getOneRs("select to_char("
									+ inxyqyrsnum + "/" + inxybyrsnum
									+ "*100,'999.99')||'%' aa from dual",
									new String[] {}, new String[] { "aa" }))[0];// 1���ѧԺǩԼ��
							if ("    .00%".equals(xyqyl1)) {
								xyqyl1 = "0.00%";
							}
						}

						// 2���ѧԺ��ҵ������
						sql = "select b.xsxh from jygl_xsjbxxb b where exists(select a.xh from view_xsjbxx a where a.xymc=? and a.xh=b.xsxh) and b.bynd=?";
						ArrayList<Object> xybyrsrs2 = new ArrayList<Object>();
						xybyrsrs2.addAll(dao.rsToVator(sql, new String[] {
								xymingcheng, bynd2 }, new String[] { "xsxh" }));
						String xybyrsnum2 = "";
						if (null != xybyrsrs2) {
							xybyrsnum2 = String.valueOf(xybyrsrs2.size());
						}
						// 2���ѧԺǩԼ������
						sql = "select b.xsxh from jygl_xsjbxxb b where exists(select a.xh from view_xsjbxx a where a.xymc=? and a.xh=b.xsxh) and b.bynd=?";
						ArrayList<Object> xyqyrsrs2 = new ArrayList<Object>();
						xyqyrsrs2.addAll(dao.rsToVator(sql, new String[] {
								xymingcheng, bynd2 }, new String[] { "xsxh" }));
						String xyqyrsnum2 = "";
						if (null != xyqyrsrs2) {
							xyqyrsnum2 = String.valueOf(xyqyrsrs2.size());
						}
						// 2���ѧԺǩԼ��
						inxyqyrsnum = Integer.valueOf(xyqyrsnum2);// ǩԼ����
						inxybyrsnum = Integer.valueOf(xybyrsnum2);// ��ҵ����
						String xyqyl2 = "";
						if (inxybyrsnum != 0) {
							xyqyl2 = (dao.getOneRs("select to_char("
									+ inxyqyrsnum + "/" + inxybyrsnum
									+ "*100,'999.99')||'%' aa from dual",
									new String[] {}, new String[] { "aa" }))[0];// 2���ѧԺǩԼ��
							if ("    .00%".equals(xyqyl2)) {
								xyqyl2 = "0.00%";
							}
						}
						// 1���ѧԺ��ҵ������
						sql = "select xsxh from jygl_jyxy where xymc=? and bynd=? and wjybz='0'";
						ArrayList<Object> xyjyrsrs1 = new ArrayList<Object>();
						xyjyrsrs1.addAll(dao.rsToVator(sql, new String[] {
								xymingcheng, bynd1 }, new String[] { "xsxh" }));
						String xyjyrsnum1 = "";
						if (null != xyjyrsrs1) {
							xyjyrsnum1 = String.valueOf(xyjyrsrs1.size());
						}
						// 1���ѧԺ��ҵ��
						int inxyjyrsnum = Integer.valueOf(xyjyrsnum1);// ǩԼ����
						inxybyrsnum = Integer.valueOf(xybyrsnum1);// ��ҵ����
						String xyjyl1 = "";
						if (inxybyrsnum != 0) {
							xyjyl1 = (dao.getOneRs("select to_char("
									+ inxyjyrsnum + "/" + inxybyrsnum
									+ "*100,'999.99')||'%' aa from dual",
									new String[] {}, new String[] { "aa" }))[0];// 1���ѧԺ��ҵ��
							if ("    .00%".equals(xyjyl1)) {
								xyjyl1 = "0.00%";
							}
						}
						// 2���ѧԺ��ҵ������
						sql = "select xsxh from jygl_jyxy where xymc=? and bynd=? and wjybz='0'";
						ArrayList<Object> xyjyrsrs2 = new ArrayList<Object>();
						xyjyrsrs2.addAll(dao.rsToVator(sql, new String[] {
								xymingcheng, bynd2 }, new String[] { "xsxh" }));
						String xyjyrsnum2 = "";
						if (null != xyjyrsrs2) {
							xyjyrsnum2 = String.valueOf(xyjyrsrs2.size());
						}
						// 2���ѧԺ��ҵ��
						inxyjyrsnum = Integer.valueOf(xyjyrsnum2);// ��ҵ����
						inxybyrsnum = Integer.valueOf(xybyrsnum2);// ��ҵ����
						String xyjyl2 = "";
						if (inxybyrsnum != 0) {
							xyjyl2 = (dao.getOneRs("select to_char("
									+ inxyjyrsnum + "/" + inxybyrsnum
									+ "*100,'999.99')||'%' aa from dual",
									new String[] {}, new String[] { "aa" }))[0];// 1���ѧԺ��ҵ��
							if ("    .00%".equals(xyjyl2)) {
								xyjyl2 = "0.00%";
							}
						}

						String[] xytjvalues = { "", "С��", xybyrsnum1,
								xyqyrsnum1, xyjyrsnum1, xyqyl1, xyqyl2, xyjyl1,
								xyjyl2 };
						rs.add(xytjvalues);
						allxybyrsnum1 += Integer.valueOf(xybyrsnum1);
						allxyqyrsnum1 += Integer.valueOf(xyqyrsnum1);
						allxyjyrsnum1 += Integer.valueOf(xyjyrsnum1);
						allxybyrsnum2 += Integer.valueOf(xybyrsnum2);
						allxyqyrsnum2 += Integer.valueOf(xyqyrsnum2);
						allxyjyrsnum2 += Integer.valueOf(xyjyrsnum2);
						// �����ܽ�,�ŵ����һ��ѭ�������
						if (j == ((xymc.length) - 1)) {
							// ��һ�������ѧԺǩԼ��
							if (allxybyrsnum1 != 0) {
								allxyqyl1 = (dao.getOneRs("select to_char("
										+ allxyqyrsnum1 + "/" + allxybyrsnum1
										+ "*100,'999.99')||'%' aa from dual",
										new String[] {}, new String[] { "aa" }))[0];// 1���ȫѧԺǩԼ��
								if ("    .00%".equals(allxyqyl1)) {
									allxyqyl1 = "0.00%";
								}
							}
							// �ڶ��������ѧԺǩԼ��
							if (allxybyrsnum2 != 0) {
								allxyqyl2 = (dao.getOneRs("select to_char("
										+ allxyqyrsnum2 + "/" + allxybyrsnum2
										+ "*100,'999.99')||'%' aa from dual",
										new String[] {}, new String[] { "aa" }))[0];// 2���ȫѧԺǩԼ��
								if ("    .00%".equals(allxyqyl2)) {
									allxyqyl2 = "0.00%";
								}
							}
							// ��һ�������ѧԺ��ҵ��
							if (allxybyrsnum1 != 0) {
								allxyjyl1 = (dao.getOneRs("select to_char("
										+ allxyjyrsnum1 + "/" + allxybyrsnum1
										+ "*100,'999.99')||'%' aa from dual",
										new String[] {}, new String[] { "aa" }))[0];// 1���ȫѧԺ��ҵ��
								if ("    .00%".equals(allxyjyl1)) {
									allxyjyl1 = "0.00%";
								}
							}
							// �ڶ��������ѧԺ��ҵ��
							if (allxybyrsnum2 != 0) {
								allxyjyl2 = (dao.getOneRs("select to_char("
										+ allxyjyrsnum2 + "/" + allxybyrsnum2
										+ "*100,'999.99')||'%' aa from dual",
										new String[] {}, new String[] { "aa" }))[0];// 2���ȫѧԺ��ҵ��
								if ("    .00%".equals(allxyjyl2)) {
									allxyjyl2 = "0.00%";
								}
							}

							String strallxybyrsnum1 = String
									.valueOf(allxybyrsnum1);
							String strallxyqyrsnum1 = String
									.valueOf(allxyqyrsnum1);
							String strallxyjyrsnum1 = String
									.valueOf(allxyjyrsnum1);
							String[] allxytjvalues = { "", "�ܼ�",
									strallxybyrsnum1, strallxyqyrsnum1,
									strallxyjyrsnum1, allxyqyl1, allxyqyl2,
									allxyjyl1, allxyjyl2 };
							rs.add(allxytjvalues);

						}
					}
				}
				list.add(rs);
			}
			request.setAttribute("top", top);// ���ͱ�ͷ
			request.setAttribute("list", list);
		}
		request.setAttribute("rs1", map);
		return mapping.findForward("success");
	}

	// ѧ����ҵ��Ϣ
	private ActionForward stuinfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String sql = "";
		DAO dao = DAO.getInstance();
		String[] colList = null;
		String[] colListCN = null;
		String rsNum = "0";
		String act = request.getParameter("act");
		String doType = request.getParameter("doType");
		String doType2 = request.getParameter("doType2");
		String pkValue = request.getParameter("pkValue");
		HashMap<String, String> map = new HashMap<String, String>();

		ArrayList<HashMap<String, String>> rs = new ArrayList<HashMap<String, String>>();
		HttpSession session = request.getSession();

		String userType = session.getAttribute("userType").toString();
		if ("admin".equalsIgnoreCase(userType)
				|| "xx".equalsIgnoreCase(userType)) {
			request.setAttribute("who", "xx");
		} else if ("xy".equalsIgnoreCase(userType)) {
			request.setAttribute("who", "xy");
		}

		// ����ɾ��
		if (null != pkValue) {
			pkValue = pkValue.replace(" ", "+");
		}

		if ("del".equalsIgnoreCase(doType)) {
			boolean judge = false;
			judge = StandardOperation.delete("jygl_xsjyqkb", "rowid", pkValue,
					request);
			if (judge) {
				request.setAttribute("delete", "ok");
			} else {
				request.setAttribute("delete", "no");
			}
		}

		// ����ɾ��
		if ("delall".equalsIgnoreCase(doType2)) {
			String pkstring = request.getParameter("pkstring");
			String[] pkstringI = new String[] {};
			if (pkstring != null) {
				pkstringI = pkstring.split("!!#!!");
			}

			for (int i = 1; i < pkstringI.length; i++) {
				String whichrid = pkstringI[i];
				boolean judge = false;
				judge = StandardOperation.delete("jygl_xsjyqkb", "rowid",
						whichrid, request);
				if (judge) {
					request.setAttribute("delall", "ok");
				} else {
					request.setAttribute("delall", "no");
				}
			}
		}

		// ȫ�����
		if ("delallinfo".equalsIgnoreCase(doType2)) {

			boolean judge = false;

			judge = dao.runUpdateTab("truncate table jygl_xsjyqkb");

			if (judge) {
				request.setAttribute("delallinfo", "ok");
			} else {
				request.setAttribute("delallinfo", "no");
			}

		}

		String xh = DealString.toGBK(request.getParameter("xh")); // ѧ��
		String xm = DealString.toGBK(request.getParameter("xm")); // ����
		String xn = DealString.toGBK(request.getParameter("xn")); // ѧ��
		String nj = DealString.toGBK(request.getParameter("nj")); // �꼶
		String xydm = DealString.toGBK(request.getParameter("xydm")); // ѧԺ����
		String zydm = DealString.toGBK(request.getParameter("zydm")); // רҵ����
		String bjdm = DealString.toGBK(request.getParameter("bjdm")); // �༶����
		String dwmc = DealString.toGBK(request.getParameter("dwmc")); // ��λ����
		String gwxz = DealString.toGBK(request.getParameter("gwxz")); // ��λ����
		String sfjy = DealString.toGBK(request.getParameter("sfjy")); // �Ƿ��ҵ
		String sfqy = DealString.toGBK(request.getParameter("sfqy")); // �Ƿ�ǩԼ
		if ("query".equals(act)) {
			map.put("xh", xh);
			map.put("xm", xm);
			map.put("xn", xn);
			map.put("nj", nj);
			map.put("xydm", xydm);
			map.put("zydm", zydm);
			map.put("bjdm", bjdm);
			map.put("dwmc", dwmc);
			map.put("gwxz", gwxz);
			map.put("sfjy", sfjy);
			map.put("sfqy", sfqy);
		}

		StringBuffer query = new StringBuffer();

		if (!("".equals(xh))) {
			query.append(" and xh like '%");
			query.append(xh);
			query.append("%'");
		}
		if (!("".equals(xm))) {
			query.append(" and xm like '%");
			query.append(xm);
			query.append("%'");
		}
		if (!("".equals(xn))) {
			query.append(" and xn= '");
			query.append(xn);
			query.append("'");
		}
		if (!("".equals(nj))) {
			query.append(" and nj='");
			query.append(nj);
			query.append("'");
		}
		if (!("".equals(xydm))) {
			query.append(" and xydm= '");
			query.append(xydm);
			query.append("'");
		}
		if (!("".equals(zydm))) {
			query.append(" and zydm='");
			query.append(zydm);
			query.append("'");
		}
		if (!("".equals(bjdm))) {
			query.append(" and bjdm='");
			query.append(bjdm);
			query.append("'");
		}
		if (!("".equals(dwmc))) {
			query.append(" and dwmc like '%");
			query.append(dwmc);
			query.append("%'");
		}
		if (!("".equals(gwxz))) {
			query.append(" and gwxz='");
			query.append(gwxz);
			query.append("'");
		}
		if (!("".equals(sfjy))) {
			query.append(" and sfjy='");
			query.append(sfjy);
			query.append("'");
		}
		if (!("".equals(sfqy))) {
			query.append(" and sfqy='");
			query.append(sfqy);
			query.append("'");
		}

		String query1 = query.toString();

		CommanForm dataSearchForm = (CommanForm) form;
		sql = "select count(*) count from view_jygl_xsjyqkb a where 1=1 "
				+ query1;

		dataSearchForm.getPages().setMaxRecord(
				Integer.parseInt(dao.getOneRs(sql, new String[] {}, "count")));

		// ��ҵ��Ϣ
		sql = "select * from (select a.*,rownum r from (select distinct a.rid,a.xh,a.xm,a.xb,a.dwmc,a.sfjy,a.sfqy,a.djsj from view_jygl_xsjyqkb a where 1=1 "
				+ query1
				+ " order by djsj desc) a ) a where a.r>"
				+ dataSearchForm.getPages().getStart()
				+ " and a.r<="
				+ (dataSearchForm.getPages().getStart() + dataSearchForm
						.getPages().getPageSize()) + " order by djsj desc";

		colList = new String[] { "rid", "xh", "xm", "xb", "dwmc", "sfjy",
				"sfqy", "djsj" };

		if ("query".equals(act)) {
			rs = dao.getArrayList2(sql, new String[] {}, colList);
		}
		sql = "select count(*) from view_jygl_xsjyqkb where 1=1 " + query1;
		int rsNuminfo = dao.getOneRsint(sql);
		rsNum = String.valueOf(rsNuminfo);
		request.setAttribute("rsNum", rsNum);
		colListCN = dao.getColumnNameCN(colList, "view_jygl_xsjyqkb");
		List topTr = dao.arrayToList(colList, colListCN);

		request.setAttribute("rs", rs);
		request.setAttribute("rs1", map);
		request.setAttribute("topTr", topTr);
		request.setAttribute("realTable", "jygl_xsjyqkb");
		request.setAttribute("querry", query1);
		request.setAttribute("njList", dao.getNjList());// �����꼶�б�
		request.setAttribute("xyList", dao.getXyList());// ����ѧԺ�б�
		request.setAttribute("zyList", dao.getZyList(xydm));// ����רҵ�б�
		request.setAttribute("bjList", dao.getBjList(xydm, zydm, nj));// ����רҵ�б�
		return mapping.findForward("success");

	}

	// ѧ��������Ϣ��ѯ���������ְҵ��
	private ActionForward stuinfoquery(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HashMap<String, String> map = new HashMap<String, String>();
		ArrayList<HashMap<String, String>> rs = new ArrayList<HashMap<String, String>>();
		DAO dao = DAO.getInstance();
		HttpSession session = request.getSession();
		String[] colList = null;
		String[] colListCN = null;
		String rsNum = "0";
		String sql = "";
		String act = request.getParameter("act");
		String xxdm = session.getAttribute("xxdm").toString();

		String userType = session.getAttribute("userType").toString();
		if ("admin".equalsIgnoreCase(userType)
				|| "xx".equalsIgnoreCase(userType)) {
			request.setAttribute("who", "xx");
		} else if ("xy".equalsIgnoreCase(userType)) {
			request.setAttribute("who", "xy");
		}

		String xh = DealString.toGBK(request.getParameter("xh")); // ѧ��
		String xm = DealString.toGBK(request.getParameter("xm")); // ����
		String xn = DealString.toGBK(request.getParameter("xn")); // ѧ��
		String xq = DealString.toGBK(request.getParameter("xq")); // ѧ��
		String xydm = DealString.toGBK(request.getParameter("xydm")); // ѧԺ����
		String zydm = DealString.toGBK(request.getParameter("zydm")); // רҵ����
		String bjdm = DealString.toGBK(request.getParameter("bjdm")); // �༶����

		if ("query".equals(act)) {
			map.put("xh", xh);
			map.put("xm", xm);
			map.put("xn", xn);
			map.put("xq", xq);
			map.put("xydm", xydm);
			map.put("zydm", zydm);
			map.put("bjdm", bjdm);

		}

		StringBuffer query = new StringBuffer();

		if (!("".equals(xh))) {
			query.append(" and xh like '%");
			query.append(xh);
			query.append("%'");
		}
		if (!("".equals(xm))) {
			query.append(" and xm like '%");
			query.append(xm);
			query.append("%'");
		}
		if (!("".equals(xn))) {
			query.append(" and xn= '");
			query.append(xn);
			query.append("'");
		}
		if (!("".equals(xq))) {
			query.append(" and xq= '");
			query.append(xq);
			query.append("'");
		}
		if (!("".equals(xydm))) {
			query.append(" and xydm= '");
			query.append(xydm);
			query.append("'");
		}
		if (!("".equals(zydm))) {
			query.append(" and zydm= '");
			query.append(zydm);
			query.append("'");
		}
		if (!("".equals(bjdm))) {
			query.append(" and bjdm= '");
			query.append(bjdm);
			query.append("'");
		}
		String query1 = query.toString();

		CommanForm dataSearchForm = (CommanForm) form;
		if ("10863".equals(xxdm)) {
			sql = "select count(*) count from view_dgsxxssxjl a where 1=1 "
					+ query1;
		} else {
			sql = "select count(*) count from view_xsxxb a where 1=1 " + query1;
		}

		dataSearchForm.getPages().setMaxRecord(
				Integer.parseInt(dao.getOneRs(sql, new String[] {}, "count")));

		// ѧ����Ϣ
		if ("10863".equals(xxdm)) {
			sql = "select * from (select a.*,rownum r from (select distinct a.rowid rid,a.xh,a.xm,a.xn,a.xq,a.sfdlh from view_dgsxxssxjl a where 1=1 "
					+ query1
					+ " order by xh ) a ) a where a.r>"
					+ dataSearchForm.getPages().getStart()
					+ " and a.r<="
					+ (dataSearchForm.getPages().getStart() + dataSearchForm
							.getPages().getPageSize()) + " order by xh";
		} else {
			sql = "select * from (select a.*,rownum r from (select distinct a.xh,a.xm,xb,nj,(select distinct xymc from view_njxyzybj b where a.xydm=b.xydm) xydm,(select distinct zymc from view_njxyzybj b where a.zydm=b.zydm) zydm,(select distinct bjmc from view_njxyzybj b where a.bjdm=b.bjdm) bjdm from view_xsxxb a where 1=1 "
					+ query1
					+ " order by xh ) a ) a where a.r>"
					+ dataSearchForm.getPages().getStart()
					+ " and a.r<="
					+ (dataSearchForm.getPages().getStart() + dataSearchForm
							.getPages().getPageSize()) + " order by xh";
		}
		if ("10863".equals(xxdm)) {
			colList = new String[] { "rid", "xh", "xm", "xn", "xq", "sfdlh" };
		} else {
			colList = new String[] { "xh", "xm", "xb", "nj", "xydm", "zydm",
					"bjdm" };
		}

		if ("query".equals(act)) {
			rs = dao.getArrayList2(sql, new String[] {}, colList);
		}
		sql = "select count(*) from view_dgsxxssxjl where 1=1 " + query1;
		int rsNuminfo = dao.getOneRsint(sql);
		rsNum = String.valueOf(rsNuminfo);
		request.setAttribute("rsNum", rsNum);
		List topTr;
		// String[] colListEN;
		if ("10863".equals(xxdm)) {
			colListCN = dao.getColumnNameCN(colList, "view_dgsxxssxjl");
		} else {
			colListCN = new String[] { "ѧ��", "����", "�Ա�", "�꼶", "ѧԺ����", "רҵ����",
					"�༶����" };
			colList = new String[] { "xh", "xm", "xb", "nj", "xydm", "zydm",
					"bjdm" };
		}
		topTr = dao.arrayToList(colList, colListCN);

		request.setAttribute("rs1", map);
		request.setAttribute("rs", rs);
		request.setAttribute("topTr", topTr);
		request.setAttribute("xnList", dao.getXnndList());
		request.setAttribute("xqList", dao.getXqList());
		request.setAttribute("xyList", dao.getXyList());// ����ѧԺ�б�
		request.setAttribute("zyList", dao.getZyList(xydm));// ����רҵ�б�
		request.setAttribute("bjList", dao.getBjList(xydm, zydm));// ����רҵ�б�
		return mapping.findForward("success");

	}

	// ��λ��Ϣ����
	private ActionForward dwxxinfoInput(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HashMap<String, String> map = new HashMap<String, String>();
		request.setAttribute("rs", map);
		String act = request.getParameter("act");
		String dwupdate = request.getParameter("dwupdate");
		String sendgo = request.getParameter("sendgo");
		DAO dao = DAO.getInstance();

		String dwxzcx = "select dwxzdm,dwxz from dmk_dwxz2"; // ��λ���ʴ���2
		List dwxzdm2List = dao.getList(dwxzcx, new String[] {}, new String[] {
				"dwxzdm", "dwxz" });
		request.setAttribute("dwxxdm", dwxzdm2List);

		String zgdwmc = "select zgbmdm,zgbm from dmk_zgbm"; // ��Դ�����ܵ�λ����
		List sydzgbmList = dao.getList(zgdwmc, new String[] {}, new String[] {
				"zgbmdm", "zgbm" });
		request.setAttribute("sydzgbmList", sydzgbmList);

		String hyfl = "select hyfldm,hyfl from dmk_hyfl"; // ��ҵ����
		List hyflList = dao.getList(hyfl, new String[] {}, new String[] {
				"hyfldm", "hyfl" });
		request.setAttribute("hyflList", hyflList);

		StuInfoDAO stuDao = new StuInfoDAO();
		request.setAttribute("shiList", stuDao.getShiList("").get("shiList"));
		request.setAttribute("xianList", stuDao.getShiList("").get("xianList"));
		request.setAttribute("ssList", stuDao.getSsList());

		if ("go".equalsIgnoreCase(act)) {
			String pkValue = DealString.toGBK(request.getParameter("pkValue"));
			String sql = "select * from dwxxb where dwid='" + pkValue + "'";
			String[] colList = dao
					.getColumnName("select * from dwxxb a where 1=2"); // ������������
			String[] stuinfo = dao.getOneRs(sql, new String[] {}, colList);
			if (stuinfo != null) {
				for (int i = 0; i < colList.length; i++) {
					map.put(colList[i].toLowerCase(), stuinfo[i]); // ����¼ѭ������map��
				}
				if (!"dwupdate".equalsIgnoreCase(dwupdate)) {
					String shen = map.get("szdqsh");
					String shi = map.get("szdqsi");
					// ת��ʡ����
					String sqldzsh = "select * from dmk_sydq where sydqdm=?";
					String[] rs5 = dao.getOneRs(sqldzsh, new String[] { shen },
							new String[] { "sydq" });
					if (null != rs5) {
						map.put("szdqsh", rs5[0]);
					}
					// ת���д���
					String sqlshidw = "select qxmc from dmk_qx where qxdm=?";
					String[] rs6 = dao.getOneRs(sqlshidw, new String[] { shi },
							new String[] { "qxmc" });
					if (null != rs6) {
						map.put("szdqsi", rs6[0]);
					}
				}
				map.put("gsmc", map.get("dwmc"));
				map.put("gswz", map.get("dwzy"));
			}
			// if (null != map.get("xh") && !"".equalsIgnoreCase(map.get("xh")))
			// {
			// String xh = map.get("xh");
			// sql =
			// "select xm,xb,nj,xymc,zymc,bjmc from view_xsjbxx where xh=?";
			// String[] stuinfo2 = dao
			// .getOneRs(sql, new String[] { xh }, new String[] {
			// "xm", "xb", "nj", "xymc", "zymc", "bjmc" });
			// if (null != stuinfo2) {
			// map.put("xm", stuinfo2[0]);
			// map.put("xb", stuinfo2[1]);
			// map.put("nj", stuinfo2[2]);
			// map.put("xymc", stuinfo2[3]);
			// map.put("zymc", stuinfo2[4]);
			// map.put("bjmc", stuinfo2[5]);
			// }
			// }
			request.setAttribute("rs", map);
			if ("dwupdate".equalsIgnoreCase(dwupdate)) {
				return mapping.findForward("successupdate");
			} else if ("sendgo".equalsIgnoreCase(sendgo)) {
				return mapping.findForward("sendgo");
			} else {
				return mapping.findForward("successgo");
			}
		}

		if ("save".equalsIgnoreCase(act)) {
			String dwid = request.getParameter("dwid");
			String dwmc = DealString.toGBK(request.getParameter("dwmc"));// ��λ����
			String dwxz = DealString.toGBK(request.getParameter("dwxz"));// ��λ����
			String dwlb = DealString.toGBK(request.getParameter("dwlb"));// ��λ���
			String zgbm = DealString.toGBK(request.getParameter("zgbm"));// ���ܲ���
			String sshy = DealString.toGBK(request.getParameter("hyfl"));// ������ҵ
			String szdqsh = DealString.toGBK(request.getParameter("szdqsh"));// ���ڵ���ʡ
			String szdqsi = DealString.toGBK(request.getParameter("szdqsi"));// ���ڵ�����
			String zcsj = DealString.toGBK(request.getParameter("clrq"));// ��������
			String dz = DealString.toGBK(request.getParameter("dz"));// ͨѶ��ַ
			String yb = DealString.toGBK(request.getParameter("yb"));// ��������
			String lxbm = DealString.toGBK(request.getParameter("lxbm"));// ��ϵ����
			String lxr = DealString.toGBK(request.getParameter("lxr"));// ��ϵ��
			String lxdh = DealString.toGBK(request.getParameter("lxdh"));// ��ϵ�绰
			String cz = DealString.toGBK(request.getParameter("cz"));// ��λ����
			String email = DealString.toGBK(request.getParameter("email"));// ����
			String dwzy = DealString.toGBK(request.getParameter("dwzy"));// ��λ��ҳ
			String dazjdz = DealString.toGBK(request.getParameter("dazjdz"));// ����ת�ĵ�ַ
			String dayzbm = DealString.toGBK(request.getParameter("dayzbm"));// ������������
			String dwjj = DealString.toGBK(request.getParameter("dwjj"));// ��λ����

			// String djsj = (dao
			// .getOneRs(
			// "select to_char(sysdate,'yyyymmddhh24miss') sdate from dual", //
			// �Ǽ�ʱ��
			// // //
			// // ȡ�����ݿ���ʱ��
			// new String[] {}, new String[] { "sdate" }))[0];

			boolean judge = false;
			if ("dwupdate".equalsIgnoreCase(dwupdate)) {
				judge = StandardOperation.update("dwxxb", new String[] {
						"dwmc", "dwxz", "dwlb", "zgbm", "hyfl", "szdqsh",
						"szdqsi", "clrq", "dz", "yb", "lxbm", "lxr", "lxdh",
						"cz", "email", "dwzy", "dazjdz", "dayzbm", "dwjj" },
						new String[] { dwmc, dwxz, dwlb, zgbm, sshy, szdqsh,
								szdqsi, zcsj, dz, yb, lxbm, lxr, lxdh, cz,
								email, dwzy, dazjdz, dayzbm, dwjj }, "dwid",
						dwid, request);
				if (judge) {
					request.setAttribute("update", "ok");
				} else {
					request.setAttribute("update", "no");
				}
				return mapping.findForward("successupdate");
			} else {
				judge = StandardOperation.insert("dwxxb", new String[] {
						"dwmc", "dwxz", "dwlb", "zgbm", "hyfl", "szdqsh",
						"szdqsi", "clrq", "dz", "yb", "lxbm", "lxr", "lxdh",
						"cz", "email", "dwzy", "dazjdz", "dayzbm", "dwjj" },
						new String[] { dwmc, dwxz, dwlb, zgbm, sshy, szdqsh,
								szdqsi, zcsj, dz, yb, lxbm, lxr, lxdh, cz,
								email, dwzy, dazjdz, dayzbm, dwjj }, request);

				if (judge) {
					request.setAttribute("save", "ok");
				} else {
					request.setAttribute("save", "no");
				}
			}
		}
		request.setAttribute("rs", map);
		return mapping.findForward("success");
	}

	// ѧ����ҵ��Ϣ����
	private ActionForward stuinfoInput(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HashMap<String, String> map = new HashMap<String, String>();
		request.setAttribute("rs", map);
		String act = request.getParameter("act");
		DAO dao = DAO.getInstance();
		String xxdm = request.getSession().getAttribute("xxdm").toString();
		String sql = "";
		String[] colList = null;

		if ("go".equalsIgnoreCase(act)) {
			String pkValue = DealString.toGBK(request.getParameter("pkValue"));
			if ("10863".equals(xxdm)) {
				sql = "select * from view_dgsxxssxjl where rownum='" + pkValue
						+ "'";
				colList = dao
						.getColumnName("select * from view_dgsxxssxjl a where 1=2"); // ������������
			} else {
				sql = "select distinct a.xh,a.xm,xb,nj,(select distinct xymc from view_njxyzybj b where a.xydm=b.xydm) xymc,(select distinct zymc from view_njxyzybj b where a.zydm=b.zydm) zymc,(select distinct bjmc from view_njxyzybj b where a.bjdm=b.bjdm) bjmc from view_xsxxb a where xh='"
						+ pkValue + "'";
				colList = new String[] { "xh", "xm", "xb", "nj", "xymc",
						"zymc", "bjmc" };
			}
			String[] stuinfo = dao.getOneRs(sql, new String[] {}, colList);
			if (stuinfo != null) {
				for (int i = 0; i < colList.length; i++) {
					map.put(colList[i].toLowerCase(), stuinfo[i]); // ����¼ѭ������map��
				}
				map.put("dwdh", map.get("dwlxdh"));
				map.put("gwxz", map.get("xssxgwxz"));
				map.put("gwmc", map.get("xssxgw"));
			}
			if (null != map.get("xh") && !"".equalsIgnoreCase(map.get("xh"))) {
				String xh = map.get("xh");
				sql = "select xm,xb,nj,xymc,zymc,bjmc from view_xsjbxx where xh=?";
				String[] stuinfo2 = dao
						.getOneRs(sql, new String[] { xh }, new String[] {
								"xm", "xb", "nj", "xymc", "zymc", "bjmc" });
				if (null != stuinfo2) {
					map.put("xm", stuinfo2[0]);
					map.put("xb", stuinfo2[1]);
					map.put("nj", stuinfo2[2]);
					map.put("xymc", stuinfo2[3]);
					map.put("zymc", stuinfo2[4]);
					map.put("bjmc", stuinfo2[5]);
				}
			}
		}

		if ("save".equalsIgnoreCase(act)) {
			String xh = DealString.toGBK(request.getParameter("xh")); // ѧ��
			String dwzm = DealString.toGBK(request.getParameter("dwzm")); // ��λ֤��
			String zydkqk = DealString.toGBK(request.getParameter("zydkqk")); // רҵ�Կ����
			String jybm = DealString.toGBK(request.getParameter("jybm")); // ��ҵ����
			String gwxz = DealString.toGBK(request.getParameter("gwxz")); // ��λ����
			String gwmc = DealString.toGBK(request.getParameter("gwmc")); // ��λ����
			String gzqk = DealString.toGBK(request.getParameter("gzqk")); // �������
			String gzd = DealString.toGBK(request.getParameter("gzd")); // ������
			String sfjy = DealString.toGBK(request.getParameter("sfjy")); // �Ƿ��ҵ
			String sfqy = DealString.toGBK(request.getParameter("sfqy")); // �Ƿ�ǩԼ
			String dwmc = DealString.toGBK(request.getParameter("dwmc")); // ��λ����
			String dwxz = DealString.toGBK(request.getParameter("dwxz")); // ��λ����
			String dwdz = DealString.toGBK(request.getParameter("dwdz")); // ��λ��ַ
			String dwlxr = DealString.toGBK(request.getParameter("dwlxr")); // ��λ��ϵ��
			String dwdh = DealString.toGBK(request.getParameter("dwdh")); // ��λ�绰
			String djsj = (dao
					.getOneRs(
							"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual", // �Ǽ�ʱ��
							// //
							// ȡ�����ݿ���ʱ��
							new String[] {}, new String[] { "sdate" }))[0];

			boolean judge = false;

			judge = StandardOperation.insert("jygl_xsjyqkb", new String[] {
					"xh", "dwzm", "zydkqk", "jybm", "gwxz", "gwmc", "gzqk",
					"gzd", "sfjy", "sfqy", "dwmc", "dwxz", "dwdz", "dwlxr",
					"dwdh", "djsj" }, new String[] { xh, dwzm, zydkqk, jybm,
					gwxz, gwmc, gzqk, gzd, sfjy, sfqy, dwmc, dwxz, dwdz, dwlxr,
					dwdh, djsj }, request);

			if (judge) {
				request.setAttribute("save", "ok");
			} else {
				request.setAttribute("save", "no");
			}

		}

		request.setAttribute("rs", map);

		return mapping.findForward("success");

	}

	// ѧ����ҵ��Ϣ�޸�
	private ActionForward stuinfoUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HashMap<String, String> map = new HashMap<String, String>();
		String pkValue = DealString.toGBK(request.getParameter("pkValue"));
		DAO dao = DAO.getInstance();
		String doType = request.getParameter("doType");
		String sql = "";

		sql = "select dm,mc from dm_gb_zhrmghgxzqhdm"; // ��λ����
		List gzdList = dao.getList(sql, new String[] {}, new String[] { "dm",
				"mc" });
		request.setAttribute("gzdList", gzdList);

		if (null != pkValue && !"".equalsIgnoreCase(pkValue)) {
			pkValue = pkValue.replace(" ", "+");
		}

		String dwmc = DealString.toGBK(request.getParameter("dwmc")); // ��λ����
		String dwxz = DealString.toGBK(request.getParameter("dwxz")); // ��λ����
		String dwdz = DealString.toGBK(request.getParameter("dwdz")); // ��λ��ַ
		String dwlxr = DealString.toGBK(request.getParameter("dwlxr")); // ��λ��ϵ��
		String dwdh = DealString.toGBK(request.getParameter("dwdh")); // ��λ�绰
		String dwzm = DealString.toGBK(request.getParameter("dwzm")); // ��λ֤��
		String zydkqk = DealString.toGBK(request.getParameter("zydkqk")); // רҵ�Կ����
		String jybm = DealString.toGBK(request.getParameter("jybm")); // ��ҵ����
		String gwxz = DealString.toGBK(request.getParameter("gwxz")); // ��λ����
		String gwmc = DealString.toGBK(request.getParameter("gwmc")); // ��λ����
		String gzqk = DealString.toGBK(request.getParameter("gzqk")); // �������
		String gzd = DealString.toGBK(request.getParameter("gzd")); // ������
		String sfjy = DealString.toGBK(request.getParameter("sfjy")); // �Ƿ��ҵ
		String sfqy = DealString.toGBK(request.getParameter("sfqy")); // �Ƿ�ǩԼ
		String djsj = (dao.getOneRs(
				"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual", // �Ǽ�ʱ��
				// //
				// ȡ�����ݿ���ʱ��
				new String[] {}, new String[] { "sdate" }))[0];

		if ("update".equalsIgnoreCase(doType)) {

			boolean judge = false;
			judge = StandardOperation.update("jygl_xsjyqkb", new String[] {
					"dwmc", "dwxz", "dwdz", "dwlxr", "dwdh", "dwzm", "zydkqk",
					"jybm", "gwxz", "gwmc", "gzqk", "gzd", "sfjy", "sfqy",
					"djsj" },
					new String[] { dwmc, dwxz, dwdz, dwlxr, dwdh, dwzm, zydkqk,
							jybm, gwxz, gwmc, gzqk, gzd, sfjy, sfqy, djsj },
					"rowid", pkValue, request);
			if (judge) {
				request.setAttribute("update", "ok");
			} else {
				request.setAttribute("update", "no");
			}

		}

		sql = "select * from view_jygl_xsjyqkb where rid='" + pkValue + "'";
		String[] colList = dao
				.getColumnName("select * from view_jygl_xsjyqkb where 1=2"); // ������������
		String[] stuinfo = dao.getOneRs(sql, new String[] {}, colList);
		if (stuinfo != null) {
			for (int i = 0; i < colList.length; i++) {
				map.put(colList[i].toLowerCase(), stuinfo[i]); // ����¼ѭ������map��
			}
			djsj = map.get("djsj");
			if (null != djsj && !"".equalsIgnoreCase(djsj)) {
				map.put("djsj", dao.doForTime(djsj));
			}
		}

		request.setAttribute("rs", map);

		return mapping.findForward("success");

	}

	// ѧ����ҵ��Ϣ��ϸ�鿴
	private ActionForward stuinfomorequery(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HashMap<String, String> map = new HashMap<String, String>();
		String pkValue = DealString.toGBK(request.getParameter("pkValue"));
		DAO dao = DAO.getInstance();

		if (null != pkValue && !"".equalsIgnoreCase(pkValue)) {
			pkValue = pkValue.replace(" ", "+");
		}

		String sql = "select * from view_jygl_xsjyqkb where rid='" + pkValue
				+ "'";
		String[] colList = dao
				.getColumnName("select * from view_jygl_xsjyqkb where 1=2"); // ������������
		String[] stuinfo = dao.getOneRs(sql, new String[] {}, colList);
		if (stuinfo != null) {
			for (int i = 0; i < colList.length; i++) {
				map.put(colList[i].toLowerCase(), stuinfo[i]); // ����¼ѭ������map��
			}
			String djsj = map.get("djsj");
			if (null != djsj && !"".equalsIgnoreCase(djsj)) {
				map.put("djsj", dao.doForTime(djsj));
			}
		}
		request.setAttribute("rs", map);
		return mapping.findForward("success");

	}

	// ѧ����ҵͳ��
	private ActionForward xsjytj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		String xydm = "";
		String zydm = "";
		String bjdm = "";
		String rsNum = "";
		String sql = "";
		String act = request.getParameter("act");

		ArrayList<HashMap<String, String>> rs = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> rs1 = new HashMap<String, String>();
		String tjlx = DealString.toGBK(request.getParameter("tjlx"));

		if ("�༶��ҵ�������ͳ�Ʊ�".equalsIgnoreCase(tjlx)) {
			xydm = request.getParameter("xydm");
			zydm = request.getParameter("zydm");
			bjdm = request.getParameter("bjdm");
			request.setAttribute("which", "�༶��ҵ�������ͳ�Ʊ�");

			StringBuffer query = new StringBuffer();

			query.append(" where 1=1 ");

			if (!("".equals(xydm))) {
				query.append(" and xydm = '");
				query.append(xydm);
				query.append("'");
			}
			if (!("".equals(zydm))) {
				query.append(" and zydm = '");
				query.append(zydm);
				query.append("'");
			}
			if (!("".equals(bjdm))) {
				query.append(" and bjdm = '");
				query.append(bjdm);
				query.append("'");
			}

			String querry = query.toString();

			sql = "select rownum , a.xymc,a.xydm,a.zydm,a.bjdm, a.bjmc ,a.bjrs ,(select b.zgh from fdybjb b where a.bjdm=b.bjdm) bzr,d.lsdwrs ,b.qyrs ,replace((select to_char(c.jyrs/a.bjrs*100,'999.99')||'%' from dual),'    ','0') jyl,replace((select to_char(b.qyrs/a.bjrs*100,'999.99')||'%' from dual),'    ','0') qyl from "
					+ "(select xymc,xydm,zydm,bjdm,bjmc,count(*) bjrs from view_xsjbxx "
					+ querry
					+ " group by bjdm,bjmc,xymc,xydm,zydm) a "
					+ "left join "
					+ "(select bjdm,count(*) qyrs from view_jygl_xsjyqkb where sfqy='��' group by bjdm) b "
					+ "on a.bjdm=b.bjdm "
					+ "left join "
					+ "(select bjdm,count(*) jyrs from view_jygl_xsjyqkb where sfjy='��' group by bjdm) c "
					+ "on a.bjdm=c.bjdm "
					+ "left join "
					+ "(select bjdm,count(*) lsdwrs from view_jygl_xsjyqkb where dwzm='��' group by bjdm) d "
					+ "on a.bjdm=d.bjdm ";

			String[] colList = { "rownum", "xymc", "bjmc", "bjrs", "bzr",
					"lsdwrs", "qyrs", "jyl", "qyl" }; // ��ͷӢ���ֶ�
			String[] colListCN = { "���", "ѧԺ", "�༶", "�༶����", "������", "��ʵ��λ����",
					"ǩԼ����", "��ҵ��", "ǩԼ��" };// ��ͷ�����ֶ�
			ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
			int len = (colList.length > colListCN.length) ? colListCN.length
					: colList.length;
			HashMap<String, String> map = null;
			for (int i = 0; i < len; i++) {
				map = new HashMap<String, String>();
				map.put("en", colList[i]);
				map.put("cn", colListCN[i]);
				list.add(map);
			}

			if ("go".equalsIgnoreCase(act)) {
				rs = dao.getArrayList2(sql, new String[] {}, colList);
				int rsNuminfo = rs.size();
				rsNum = String.valueOf(rsNuminfo);
				request.setAttribute("rsNum", rsNum);
				rs1.put("xydm", xydm);
				rs1.put("zydm", zydm);
				rs1.put("bjdm", bjdm);

			}
			request.setAttribute("topTr", list);
			request.setAttribute("querry", querry);

		}
		if ("רҵ��ҵ�������ͳ�Ʊ�".equalsIgnoreCase(tjlx)) {
			xydm = request.getParameter("xydm");
			zydm = request.getParameter("zydm");
			request.setAttribute("which", "רҵ��ҵ�������ͳ�Ʊ�");

			StringBuffer query = new StringBuffer();

			query.append(" where 1=1 ");

			if (!("".equals(xydm))) {
				query.append(" and xydm = '");
				query.append(xydm);
				query.append("'");
			}
			if (!("".equals(zydm))) {
				query.append(" and zydm = '");
				query.append(zydm);
				query.append("'");
			}

			String querry = query.toString();

			sql = "select rownum, a.xymc,a.xydm,a.zymc,a.zydm,a.zyrs,d.lsdwrs,b.qyrs,replace((select to_char(c.jyrs/a.zyrs*100,'999.99')||'%' from dual),'    ','0') jyl,replace((select to_char(b.qyrs/a.zyrs*100,'999.99')||'%' from dual),'    ','0') qyl from "
					+ "(select xymc,xydm,zydm,zymc,count(*) zyrs from view_xsjbxx group by zydm,zymc,xymc,xydm) a "
					+ "left join "
					+ "(select zydm,count(*) qyrs from view_jygl_xsjyqkb where sfqy='��' group by zydm) b "
					+ "on a.zydm=b.zydm "
					+ "left join "
					+ "(select zydm,count(*) jyrs from view_jygl_xsjyqkb where sfjy='��' group by zydm) c "
					+ "on a.zydm=c.zydm "
					+ "left join "
					+ "(select zydm,count(*) lsdwrs from view_jygl_xsjyqkb where dwzm='��' group by zydm) d "
					+ "on a.zydm=d.zydm " + querry;

			String[] colList = { "rownum", "xymc", "zymc", "zyrs", "lsdwrs",
					"qyrs", "jyl", "qyl" }; // ��ͷӢ���ֶ�
			String[] colListCN = { "���", "ѧԺ", "רҵ", "רҵ����", "��ʵ��λ����", "ǩԼ����",
					"��ҵ��", "ǩԼ��" };// ��ͷ�����ֶ�
			ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
			int len = (colList.length > colListCN.length) ? colListCN.length
					: colList.length;
			HashMap<String, String> map = null;
			for (int i = 0; i < len; i++) {
				map = new HashMap<String, String>();
				map.put("en", colList[i]);
				map.put("cn", colListCN[i]);
				list.add(map);
			}

			if ("go".equalsIgnoreCase(act)) {
				rs = dao.getArrayList2(sql, new String[] {}, colList);
				int rsNuminfo = rs.size();
				rsNum = String.valueOf(rsNuminfo);
				request.setAttribute("rsNum", rsNum);
				rs1.put("xydm", xydm);
				rs1.put("zydm", zydm);
			}
			request.setAttribute("topTr", list);
			request.setAttribute("querry", querry);
		}
		if ("��Ժ��ҵ�������ͳ�Ʊ�".equalsIgnoreCase(tjlx)) {
			xydm = request.getParameter("xydm");
			request.setAttribute("which", "��Ժ��ҵ�������ͳ�Ʊ�");

			StringBuffer query = new StringBuffer();

			query.append(" where 1=1 ");

			if (!("".equals(xydm))) {
				query.append(" and xydm = '");
				query.append(xydm);
				query.append("'");
			}

			String querry = query.toString();

			sql = "select rownum, a.xymc,a.xydm,a.xyrs,d.lsdwrs,b.qyrs,replace((select to_char(c.jyrs/a.xyrs*100,'999.99')||'%' from dual),'    ','0') jyl,replace((select to_char(b.qyrs/a.xyrs*100,'999.99')||'%' from dual),'    ','0') qyl from "
					+ "(select xymc,xydm,count(*) xyrs from view_xsjbxx group by xymc,xydm) a "
					+ "left join "
					+ "(select xydm,count(*) qyrs from view_jygl_xsjyqkb where sfqy='��' group by xydm) b "
					+ "on a.xydm=b.xydm "
					+ "left join "
					+ "(select xydm,count(*) jyrs from view_jygl_xsjyqkb where sfjy='��' group by xydm) c "
					+ "on a.xydm=c.xydm "
					+ "left join "
					+ "(select xydm,count(*) lsdwrs from view_jygl_xsjyqkb where dwzm='��' group by xydm) d "
					+ "on a.xydm=d.xydm " + querry;

			String[] colList = { "rownum", "xymc", "xyrs", "lsdwrs", "qyrs",
					"jyl", "qyl" }; // ��ͷӢ���ֶ�
			String[] colListCN = { "���", "ѧԺ", "ѧԺ����", "��ʵ��λ����", "ǩԼ����", "��ҵ��",
					"ǩԼ��" };// ��ͷ�����ֶ�
			ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
			int len = (colList.length > colListCN.length) ? colListCN.length
					: colList.length;
			HashMap<String, String> map = null;
			for (int i = 0; i < len; i++) {
				map = new HashMap<String, String>();
				map.put("en", colList[i]);
				map.put("cn", colListCN[i]);
				list.add(map);
			}

			if ("go".equalsIgnoreCase(act)) {
				rs = dao.getArrayList2(sql, new String[] {}, colList);
				int rsNuminfo = rs.size();
				rsNum = String.valueOf(rsNuminfo);
				request.setAttribute("rsNum", rsNum);
				rs1.put("xydm", xydm);
			}
			request.setAttribute("topTr", list);
			request.setAttribute("querry", querry);
		}
		if ("��ҵ�������ͳ���ܱ�".equalsIgnoreCase(tjlx)) {
			request.setAttribute("which", "��ҵ�������ͳ���ܱ�");

			sql = "select a.zrs,d.lsdwrs,c.qyrs, replace((select to_char(c.qyrs/a.zrs*100,'999.99')||'%' from dual),'    ','0') qyl, b.jyrs,replace((select to_char(b.jyrs/a.zrs*100,'999.99')||'%' from dual),'    ','0') jyl from "
					+ "(select count(*) zrs from view_xsjbxx ) a , "
					+ "(select count(*) jyrs from view_jygl_xsjyqkb where sfjy='��') b, "
					+ "(select count(*) qyrs from view_jygl_xsjyqkb where sfqy='��') c, "
					+ "(select count(*) lsdwrs from view_jygl_xsjyqkb where dwzm='��') d";

			String[] colList = { "zrs", "lsdwrs", "qyrs", "qyl", "jyrs", "jyl" }; // ��ͷӢ���ֶ�
			String[] colListCN = { "������", "��ʵ��λ����", "ǩԼ����", "ǩԼ��", "��ҵ����",
					"��ҵ��" };// ��ͷ�����ֶ�
			ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
			int len = (colList.length > colListCN.length) ? colListCN.length
					: colList.length;
			HashMap<String, String> map = null;
			for (int i = 0; i < len; i++) {
				map = new HashMap<String, String>();
				map.put("en", colList[i]);
				map.put("cn", colListCN[i]);
				list.add(map);
			}

			if ("go".equalsIgnoreCase(act)) {
				rs = dao.getArrayList2(sql, new String[] {}, colList);
				int rsNuminfo = rs.size();
				rsNum = String.valueOf(rsNuminfo);
				request.setAttribute("rsNum", rsNum);
			}
			request.setAttribute("topTr", list);

		}
		if ("�ְ��ҵ�����ϸ��".equalsIgnoreCase(tjlx)) {
			xydm = request.getParameter("xydm");
			zydm = request.getParameter("zydm");
			bjdm = request.getParameter("bjdm");
			request.setAttribute("which", "�ְ��ҵ�����ϸ��");

			StringBuffer query = new StringBuffer();

			query.append(" where 1=1 ");

			if (!("".equals(xydm))) {
				query.append(" and xydm = '");
				query.append(xydm);
				query.append("'");
			}
			if (!("".equals(zydm))) {
				query.append(" and zydm = '");
				query.append(zydm);
				query.append("'");
			}
			if (!("".equals(bjdm))) {
				query.append(" and bjdm = '");
				query.append(bjdm);
				query.append("'");
			}

			String querry = query.toString();

			sql = "select rownum, a.xh,a.xm,a.xb,a.dwmc,a.dwdz,a.dwdh,a.sfjy,a.sfqy,a.djsj from view_jygl_xsjyqkb a "
					+ querry;

			String[] colList = { "rownum", "xh", "xm", "xb", "dwmc", "dwdz",
					"dwdh", "sfjy", "sfqy", "djsj" }; // ��ͷӢ���ֶ�
			String[] colListCN = { "���", "ѧ��", "����", "�Ա�", "��λ����", "��λ��ַ",
					"��λ�绰", "�Ƿ��ҵ", "�Ƿ�ǩԼ", "�Ǽ�ʱ��" };// ��ͷ�����ֶ�
			ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
			int len = (colList.length > colListCN.length) ? colListCN.length
					: colList.length;
			HashMap<String, String> map = null;
			for (int i = 0; i < len; i++) {
				map = new HashMap<String, String>();
				map.put("en", colList[i]);
				map.put("cn", colListCN[i]);
				list.add(map);
			}

			if ("go".equalsIgnoreCase(act)) {
				rs = dao.getArrayList2(sql, new String[] {}, colList);
				int rsNuminfo = rs.size();
				rsNum = String.valueOf(rsNuminfo);
				request.setAttribute("rsNum", rsNum);
				rs1.put("xydm", xydm);
				rs1.put("zydm", zydm);
				rs1.put("bjdm", bjdm);

			}
			request.setAttribute("topTr", list);
			request.setAttribute("querry", querry);

		}
		if ("רҵ�Կ����ͳ�Ʊ�".equalsIgnoreCase(tjlx)) {
			xydm = request.getParameter("xydm");
			zydm = request.getParameter("zydm");
			request.setAttribute("which", "רҵ�Կ����ͳ�Ʊ�");

			StringBuffer query = new StringBuffer();

			query.append(" where 1=1 ");

			if (!("".equals(xydm))) {
				query.append(" and xydm = '");
				query.append(xydm);
				query.append("'");
			}
			if (!("".equals(zydm))) {
				query.append(" and zydm = '");
				query.append(zydm);
				query.append("'");
			}

			String querry = query.toString();

			sql = "select rownum, a.xymc,a.xydm,a.zymc,a.zydm,a.zyrs,d.lsdwrs,b.zydkrs,replace((select to_char(b.zydkrs/a.zyrs*100,'999.99')||'%' from dual),'    ','0') dkl from "
					+ "(select xymc,xydm,zymc,zydm,count(*) zyrs from view_xsjbxx group by xymc,xydm,zymc,zydm) a "
					+ "left join "
					+ "(select zydm,count(*) zydkrs from view_jygl_xsjyqkb where zydkqk='��ȫ�Կ�' or zydkqk='�����Կ�' group by zydm) b "
					+ "on a.zydm=b.zydm "
					+ "left join "
					+ "(select zydm,count(*) lsdwrs from view_jygl_xsjyqkb where dwzm='��' group by zydm) d "
					+ "on a.zydm=d.zydm " + querry;

			String[] colList = { "rownum", "xymc", "zymc", "zyrs", "lsdwrs",
					"zydkrs", "dkl" }; // ��ͷӢ���ֶ�
			String[] colListCN = { "���", "ѧԺ", "רҵ", "רҵ����", "��ʵ��λ����",
					"רҵ�Կ�����", "�Կ���" };// ��ͷ�����ֶ�
			ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
			int len = (colList.length > colListCN.length) ? colListCN.length
					: colList.length;
			HashMap<String, String> map = null;
			for (int i = 0; i < len; i++) {
				map = new HashMap<String, String>();
				map.put("en", colList[i]);
				map.put("cn", colListCN[i]);
				list.add(map);
			}

			if ("go".equalsIgnoreCase(act)) {
				rs = dao.getArrayList2(sql, new String[] {}, colList);
				int rsNuminfo = rs.size();
				rsNum = String.valueOf(rsNuminfo);
				request.setAttribute("rsNum", rsNum);
				rs1.put("xydm", xydm);
				rs1.put("zydm", zydm);
			}
			request.setAttribute("topTr", list);
			request.setAttribute("querry", querry);

		}
		if ("��ҵ����״��ͳ�Ʊ�".equalsIgnoreCase(tjlx)) {
			request.setAttribute("which", "��ҵ����״��ͳ�Ʊ�");

			sql = "select rownum ,b.gzqk,c.lsdwrs,replace((select to_char(c.lsdwrs/a.zrs*100,'999.99')||'%' from dual),'    ','0') bl from "
					+ "(select count(*) zrs from view_jygl_xsjyqkb ) a , "
					+ "(select gzqk ,count(*) zrs from view_jygl_xsjyqkb group by gzqk ) b "
					+ "left join "
					+ "(select gzqk ,count(*) lsdwrs from view_jygl_xsjyqkb where dwzm='��' group by gzqk) c "
					+ "on b.gzqk = c.gzqk ";

			String[] colList = { "rownum", "gzqk", "lsdwrs", "bl" }; // ��ͷӢ���ֶ�
			String[] colListCN = { "���", "����", "��ʵ��λ����", "����" };// ��ͷ�����ֶ�
			ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
			int len = (colList.length > colListCN.length) ? colListCN.length
					: colList.length;
			HashMap<String, String> map = null;
			for (int i = 0; i < len; i++) {
				map = new HashMap<String, String>();
				map.put("en", colList[i]);
				map.put("cn", colListCN[i]);
				list.add(map);
			}

			if ("go".equalsIgnoreCase(act)) {
				rs = dao.getArrayList2(sql, new String[] {}, colList);
				int rsNuminfo = rs.size();
				rsNum = String.valueOf(rsNuminfo);
				request.setAttribute("rsNum", rsNum);
			}
			request.setAttribute("topTr", list);
		}

		rs1.put("tjlx", tjlx);
		request.setAttribute("rs", rs);
		request.setAttribute("rs1", rs1);
		request.setAttribute("tjlx", tjlx);
		request.setAttribute("xyList", dao.getXyList());// ����ѧԺ�б�
		request.setAttribute("zyList", dao.getZyList(xydm));// ����רҵ�б�
		request.setAttribute("bjList", dao.getBjList(xydm, zydm));// ����רҵ�б�
		return mapping.findForward("success");
	}

	// ��λ��Ϣ����--���ʴ�ѧ
	private ActionForward dwxxmkwhViewMore(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		CommanForm dataSearchForm = (CommanForm) form;
		String sql = "";
		DAO dao = DAO.getInstance();
		String[] colList = null;
		String[] colListCN = null;
		String rsNum = "0";
		String act = request.getParameter("act");
		String doType = request.getParameter("doType");
		String doType2 = request.getParameter("doType2");
		String pkValue = request.getParameter("pkValue");
		String queryType = request.getParameter("queryType");
		request.setAttribute("qt", queryType);
		HashMap<String, String> map = new HashMap<String, String>();

		ArrayList<HashMap<String, String>> rs = new ArrayList<HashMap<String, String>>();
		HttpSession session = request.getSession();

		String dwxzcx = "select dwxzdm,dwxz from dmk_dwxz2"; // ��λ���ʴ���2
		List dwxzdm2List = dao.getList(dwxzcx, new String[] {}, new String[] {
				"dwxzdm", "dwxz" });
		request.setAttribute("dwxxdm", dwxzdm2List);

		String hyfl = "select hyfldm,hyfl from dmk_hyfl"; // ��ҵ����
		List hyflList = dao.getList(hyfl, new String[] {}, new String[] {
				"hyfldm", "hyfl" });
		request.setAttribute("hyflList", hyflList);

		String userType = session.getAttribute("userType").toString();
		if ("admin".equalsIgnoreCase(userType)
				|| "xx".equalsIgnoreCase(userType)) {
			request.setAttribute("who", "xx");
		} else if ("xy".equalsIgnoreCase(userType)) {
			request.setAttribute("who", "xy");
		}

		// ����ɾ��
		if (null != pkValue) {
			pkValue = pkValue.replace(" ", "+");
		}

		if ("del".equalsIgnoreCase(doType)) {
			boolean judge = false;
			judge = StandardOperation.delete("dwxxb", "dwid", pkValue, request);
			if (judge) {
				request.setAttribute("delete", "ok");
			} else {
				request.setAttribute("delete", "no");
			}
		}

		// ����ɾ��
		if ("delall".equalsIgnoreCase(doType2)) {
			String pkstring = request.getParameter("pkstring");
			String[] pkstringI = new String[] {};
			if (pkstring != null) {
				pkstringI = pkstring.split("!!#!!");
			}

			for (int i = 1; i < pkstringI.length; i++) {
				String whichrid = pkstringI[i];
				boolean judge = false;
				judge = StandardOperation.delete("dwxxb", "dwid", whichrid,
						request);
				if (judge) {
					request.setAttribute("delall", "ok");
				} else {
					request.setAttribute("delall", "no");
				}
			}
		}

		// ȫ�����
		if ("delallinfo".equalsIgnoreCase(doType2)) {

			boolean judge = false;

			judge = dao.runUpdateTab("truncate table dwxxb");

			if (judge) {
				request.setAttribute("delallinfo", "ok");
			} else {
				request.setAttribute("delallinfo", "no");
			}

		}

		String szdqsi = DealString.toGBK(request.getParameter("szdqsi")); // רҵ����
		String dwxz = DealString.toGBK(request.getParameter("dwxz")); // �༶����

		String szdqsh = DealString.toGBK(request.getParameter("szdqsh")); // ��λ����
		String sshy = DealString.toGBK(request.getParameter("hyfl")); // ������ҵ
		String zgbm = DealString.toGBK(request.getParameter("zgbm")); // ���ܲ���

		String dwmc = DealString.toGBK(request.getParameter("dwmc")); // ��λ����
		dataSearchForm.setDwmc(dwmc);
		if ("query".equals(act)) {
			map.put("zgbm", zgbm);
			map.put("hyfl", sshy);
			map.put("dwxz", dwxz);
		}

		StringBuffer query = new StringBuffer();

		if (!("".equals(dwmc))) {
			query.append(" and dwmc like '%");
			query.append(dwmc);
			query.append("%'");
		}
		if (!("".equals(zgbm))) {
			query.append(" and zgbm like '%");
			query.append(zgbm);
			query.append("%'");
		}
		if (!("".equals(sshy))) {
			query.append(" and hyfl= '");
			query.append(sshy);
			query.append("'");
		}
		if (!("".equals(szdqsh))) {
			query.append(" and szdqsh='");
			query.append(szdqsh);
			query.append("'");
		}
		if (!("".equals(szdqsi))) {
			query.append(" and szdqsi= '");
			query.append(szdqsi);
			query.append("'");
		}
		if (!("".equals(dwxz))) {
			query.append(" and dwxz='");
			query.append(dwxz);
			query.append("'");
		}

		String query1 = query.toString();

		sql = "select count(*) count from dwxxb a where 1=1 " + query1;

		dataSearchForm.getPages().setMaxRecord(
				Integer.parseInt(dao.getOneRs(sql, new String[] {}, "count")));

		// ��ҵ��Ϣ
		sql = "select * from (select a.*,rownum r from (select distinct a.dwid,a.dwmc,a.zgbm,a.clrq,a.lxbm,a.lxr,a.lxdh from dwxxb a where 1=1 "
				+ query1
				+ " order by dwmc desc) a ) a where a.r>"
				+ dataSearchForm.getPages().getStart()
				+ " and a.r<="
				+ (dataSearchForm.getPages().getStart() + dataSearchForm
						.getPages().getPageSize()) + " order by dwmc desc";

		colList = new String[] { "dwid", "dwmc", "zgbm", "clrq", "lxbm", "lxr",
				"lxdh" };

		if ("query".equals(act)) {
			rs = dao.getArrayList2(sql, new String[] {}, colList);
		}
		sql = "select count(*) from dwxxb where 1=1 " + query1;
		int rsNuminfo = dao.getOneRsint(sql);
		rsNum = String.valueOf(rsNuminfo);
		request.setAttribute("rsNum", rsNum);
		colListCN = dao.getColumnNameCN(colList, "dwxxb");
		List topTr = dao.arrayToList(colList, colListCN);

		String zgdwmc = "select zgbmdm,zgbm from dmk_zgbm"; // ��Դ�����ܵ�λ����
		List sydzgbmList = dao.getList(zgdwmc, new String[] {}, new String[] {
				"zgbmdm", "zgbm" });
		request.setAttribute("sydzgbmList", sydzgbmList);

		StuInfoDAO stuDao = new StuInfoDAO();
		request.setAttribute("shiList", stuDao.getShiList(szdqsh)
				.get("shiList"));
		request.setAttribute("xianList", stuDao.getShiList("").get("xianList"));
		request.setAttribute("ssList", stuDao.getSsList());

		request.setAttribute("rs", rs);
		request.setAttribute("rs1", map);
		request.setAttribute("topTr", topTr);
		request.setAttribute("realTable", "dwxxb");
		request.setAttribute("querry", query1);
		// request.setAttribute("njList", dao.getNjList());// �����꼶�б�
		// request.setAttribute("xyList", dao.getXyList());// ����ѧԺ�б�
		// request.setAttribute("zyList", dao.getZyList(xydm));// ����רҵ�б�
		// request.setAttribute("bjList", dao.getBjList(xydm, zydm, nj));//
		// ����רҵ�б�
		return mapping.findForward("success");
	}

	// ��λ������Ϣ--���ʴ�ѧ
	private ActionForward dwlfxxViewMore(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String doType = request.getParameter("doType");
		String act = request.getParameter("act");
		DAO dao = DAO.getInstance();
		ArrayList<HashMap<String, String>> rs = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> map = new HashMap<String, String>();
		String pk = request.getParameter("pkValue");
		StringBuffer query1 = new StringBuffer();
		String sql = "";
		String[] colList;
		String rsNum = "0"; // ���صļ�¼��

		if ("save".equals(doType)) {
			String dwbh = DealString.toGBK(request.getParameter("dwbh")); // ���
			String dwmc = DealString.toGBK(request.getParameter("dwmc")); // ��λ����
			String dwdh = DealString.toGBK(request.getParameter("dwdh")); // ��λ�绰
			String dwcz = DealString.toGBK(request.getParameter("dwcz")); // ��λ����
			String dwyx = DealString.toGBK(request.getParameter("dwyx")); // ��λ����
			String dwdz = DealString.toGBK(request.getParameter("dwdz")); // ��λ��ַ
			String dwyb = DealString.toGBK(request.getParameter("dwyb")); // ��λ�ʱ�
			String sj = DealString.toGBK(request.getParameter("sj")); // ʱ��
			String xm = DealString.toGBK(request.getParameter("xm")); // ����
			String xb = DealString.toGBK(request.getParameter("xb")); // �Ա�
			String bm = DealString.toGBK(request.getParameter("bm")); // ����
			String zw = DealString.toGBK(request.getParameter("zw")); // ְ��
			String gddh = DealString.toGBK(request.getParameter("gddh")); // �̶��绰
			String yddh = DealString.toGBK(request.getParameter("yddh")); // �ƶ��绰
			String dzyx = DealString.toGBK(request.getParameter("dzyx")); // �����ʼ�
			String lfsy = DealString.toGBK(request.getParameter("lfsy")); // ��������
			String ytdd = DealString.toGBK(request.getParameter("ytdd")); // ��̸�ص�
			String rs1 = DealString.toGBK(request.getParameter("rs")); // ����
			String jdr = DealString.toGBK(request.getParameter("jdr")); // �Ӵ���
			String jddd = DealString.toGBK(request.getParameter("jddd")); // �Ӵ��ص�
			String jdcyr1 = DealString.toGBK(request.getParameter("jdcyr1")); // �Ӵ�������1
			String jdcyr2 = DealString.toGBK(request.getParameter("jdcyr2")); // �Ӵ�������2
			String jdcyr3 = DealString.toGBK(request.getParameter("jdcyr3")); // �Ӵ�������3
			String jdcyr4 = DealString.toGBK(request.getParameter("jdcyr4")); // �Ӵ�������4
			String bz = DealString.toGBK(request.getParameter("bz")); // ��ע
			String savetype = request.getParameter("savetype");
			boolean judge = false;
			if ("updatedw".equals(savetype)) {
				String pkid = request.getParameter("dwid");
				judge = StandardOperation.delete("dwlfxxdjb", "dwid", pkid,
						request);
				if (judge) {
					StandardOperation.insert("dwlfxxdjb", new String[] {
							"dwbh", "dwmc", "dwdh", "dwcz", "dwyx", "dwdz",
							"dwyb", "sj", "xm", "xb", "bm", "zw", "gddh",
							"yddh", "dzyx", "lfsy", "ytdd", "rs", "jdr",
							"jddd", "jdcyr1", "jdcyr2", "jdcyr3", "jdcyr4",
							"bz", }, new String[] { dwbh, dwmc, dwdh, dwcz,
							dwyx, dwdz, dwyb, sj, xm, xb, bm, zw, gddh, yddh,
							dzyx, lfsy, ytdd, rs1, jdr, jddd, jdcyr1, jdcyr2,
							jdcyr3, jdcyr4, bz, }, request);
				}
				if (judge) {
					request.setAttribute("update", "ok");

					// ��Ӳ����ļ�¼
				} else {
					request.setAttribute("update", "no");
				}
			} else {
				judge = StandardOperation.insert("dwlfxxdjb", new String[] {
						"dwbh", "dwmc", "dwdh", "dwcz", "dwyx", "dwdz", "dwyb",
						"sj", "xm", "xb", "bm", "zw", "gddh", "yddh", "dzyx",
						"lfsy", "ytdd", "rs", "jdr", "jddd", "jdcyr1",
						"jdcyr2", "jdcyr3", "jdcyr4", "bz", }, new String[] {
						dwbh, dwmc, dwdh, dwcz, dwyx, dwdz, dwyb, sj, xm, xb,
						bm, zw, gddh, yddh, dzyx, lfsy, ytdd, rs1, jdr, jddd,
						jdcyr1, jdcyr2, jdcyr3, jdcyr4, bz, }, request);
				if (judge) {
					request.setAttribute("insert", "ok");

					// ��Ӳ����ļ�¼
				} else {
					request.setAttribute("insert", "no");
				}
			}
		}
		if ("sect".equals(doType)) {
			if ("query".equals(act)) {
				String dwbh = DealString.toGBK(request.getParameter("dwbh")); // ���
				String dwmc = DealString.toGBK(request.getParameter("dwmc")); // ��λ����
				String dwdh = request.getParameter("dwdh"); // ��λ�绰
				String xm = DealString.toGBK(request.getParameter("xm")); // ����
				if (!("".equals(dwbh))) {
					query1.append(" and dwbh like '%");
					query1.append(dwbh);
					query1.append("%'");
				}
				if (!("".equals(dwmc))) {
					query1.append(" and dwmc like '%");
					query1.append(dwmc);
					query1.append("%'");
				}
				if (!("".equals(dwdh))) {
					query1.append(" and dwdh like '%");
					query1.append(dwdh);
					query1.append("%'");
				}
				if (!("".equals(xm))) {
					query1.append(" and xm like '%");
					query1.append(xm);
					query1.append("%'");
				}
				CommanForm dataSearchForm = (CommanForm) form;
				sql = "select count(*) count from dwlfxxdjb a where 1=1 "
						+ query1;

				dataSearchForm.getPages().setMaxRecord(
						Integer.parseInt(dao.getOneRs(sql, new String[] {},
								"count")));

				sql = "select * from (select a.*,rownum r from (select distinct a.dwid,a.dwbh,a.dwmc,a.xm,a.dwdh,a.bm from dwlfxxdjb a where 1=1 "
						+ query1
						+ " order by dwbh desc) a ) a where a.r>"
						+ dataSearchForm.getPages().getStart()
						+ " and a.r<="
						+ (dataSearchForm.getPages().getStart() + dataSearchForm
								.getPages().getPageSize())
						+ " order by dwbh desc";

				colList = new String[] { "dwid", "dwbh", "dwmc", "xm", "dwdh",
						"bm" };

				if ("query".equals(act)) {
					rs = dao.getArrayList2(sql, new String[] {}, colList);
				}
				sql = "select count(*) from dwlfxxdjb where 1=1 " + query1;
				int rsNuminfo = dao.getOneRsint(sql);
				rsNum = String.valueOf(rsNuminfo);
				String[] colListCN = dao.getColumnNameCN(colList, "dwlfxxdjb");
				List topTr = dao.arrayToList(colList, colListCN);
				request.setAttribute("rs", rs);// �������ݼ�
				request.setAttribute("topTr", topTr);// ���ͱ�ͷ
				request.setAttribute("rsNum", rsNum);// ���ͼ�¼��
			}
			return mapping.findForward("sect");
		} else if ("update".equals(doType)) {
			sql = "select d.* from dwlfxxdjb d where dwid =?";

			String[] colListdw = new String[] { "dwid", "dwbh", "dwmc", "dwdh",
					"dwcz", "dwyx", "dwdz", "dwyb", "sj", "xm", "xb", "bm",
					"zw", "gddh", "yddh", "dzyx", "lfsy", "ytdd", "rs", "jdr",
					"jddd", "jdcyr1", "jdcyr2", "jdcyr3", "jdcyr4", "bz" }; // ������������
			String[] zpxxinfo = dao.getOneRs(sql, new String[] { pk },
					colListdw);
			if (zpxxinfo != null) {
				for (int i = 0; i < colListdw.length; i++) {
					map.put(colListdw[i].toLowerCase(), zpxxinfo[i]); // ����¼ѭ������map��
				}

				request.setAttribute("rs2", map);// �������ݼ�
				request.setAttribute("dwtype", "add");
				request.setAttribute("savetype", "updatedw");
				return mapping.findForward("success");
			}
		} else if ("dnset".equals(doType)) {
			sql = "select d.* from dwlfxxdjb d where dwid =?";

			String[] colListdw = new String[] { "dwbh", "dwmc", "dwdh", "dwcz",
					"dwyx", "dwdz", "dwyb", "sj", "xm", "xb", "bm", "zw",
					"gddh", "yddh", "dzyx", "lfsy", "ytdd", "rs", "jdr",
					"jddd", "jdcyr1", "jdcyr2", "jdcyr3", "jdcyr4", "bz" }; // ������������
			String[] zpxxinfo = dao.getOneRs(sql, new String[] { pk },
					colListdw);
			if (zpxxinfo != null) {
				for (int i = 0; i < colListdw.length; i++) {
					map.put(colListdw[i].toLowerCase(), Base
							.isNull(zpxxinfo[i]) ? "" : zpxxinfo[i].trim()); // ����¼ѭ������map��
				}

				request.setAttribute("rs2", map);// �������ݼ�
				request.setAttribute("dwtype", "dnset");
				return mapping.findForward("success");
			}
		}
		// ����ɾ��
		if ("del".equalsIgnoreCase(doType)) {
			boolean judge = false;
			judge = StandardOperation.delete("dwlfxxdjb", "dwid", pk, request);
			if (judge) {
				request.setAttribute("delete", "ok");
			} else {
				request.setAttribute("delete", "no");
			}
			return mapping.findForward("sect");
		}

		// ����ɾ��
		if ("delall".equalsIgnoreCase(doType)) {
			String pkstring = request.getParameter("pkstring");
			String[] pkstringI = new String[] {};
			if (pkstring != null) {
				pkstringI = pkstring.split("!!#!!");
			}

			for (int i = 1; i < pkstringI.length; i++) {
				String whichrid = pkstringI[i];
				boolean judge = false;
				judge = StandardOperation.delete("dwlfxxdjb", "dwid", whichrid,
						request);
				if (judge) {
					request.setAttribute("delall", "ok");
				} else {
					request.setAttribute("delall", "no");
				}
			}
			return mapping.findForward("sect");
		}

		request.setAttribute("rs2", map);// �������ݼ�
		request.setAttribute("dwtype", "add");
		return mapping.findForward("success");
	}

	// ��λ��Ϣ�޸�
	private ActionForward dwxxinfoUpdate(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HashMap<String, String> map = new HashMap<String, String>();
		String pkValue = DealString.toGBK(request.getParameter("pkValue"));
		DAO dao = DAO.getInstance();
		String doType = request.getParameter("doType");
		String sql = "";

		sql = "select dm,mc from dm_gb_zhrmghgxzqhdm"; // ��λ����
		List gzdList = dao.getList(sql, new String[] {}, new String[] { "dm",
				"mc" });
		request.setAttribute("gzdList", gzdList);

		if (null != pkValue && !"".equalsIgnoreCase(pkValue)) {
			pkValue = pkValue.replace(" ", "+");
		}

		String dwmc = DealString.toGBK(request.getParameter("dwmc")); // ��λ����
		String dwxz = DealString.toGBK(request.getParameter("dwxz")); // ��λ����
		String dwdz = DealString.toGBK(request.getParameter("dwdz")); // ��λ��ַ
		String dwlxr = DealString.toGBK(request.getParameter("dwlxr")); // ��λ��ϵ��
		String dwdh = DealString.toGBK(request.getParameter("dwdh")); // ��λ�绰
		String dwzm = DealString.toGBK(request.getParameter("dwzm")); // ��λ֤��
		String zydkqk = DealString.toGBK(request.getParameter("zydkqk")); // רҵ�Կ����
		String jybm = DealString.toGBK(request.getParameter("jybm")); // ��ҵ����
		String gwxz = DealString.toGBK(request.getParameter("gwxz")); // ��λ����
		String gwmc = DealString.toGBK(request.getParameter("gwmc")); // ��λ����
		String gzqk = DealString.toGBK(request.getParameter("gzqk")); // �������
		String gzd = DealString.toGBK(request.getParameter("gzd")); // ������
		String sfjy = DealString.toGBK(request.getParameter("sfjy")); // �Ƿ��ҵ
		String sfqy = DealString.toGBK(request.getParameter("sfqy")); // �Ƿ�ǩԼ
		String djsj = (dao.getOneRs(
				"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual", // �Ǽ�ʱ��
				// //
				// ȡ�����ݿ���ʱ��
				new String[] {}, new String[] { "sdate" }))[0];

		if ("update".equalsIgnoreCase(doType)) {

			boolean judge = false;
			judge = StandardOperation.update("jygl_xsjyqkb", new String[] {
					"dwmc", "dwxz", "dwdz", "dwlxr", "dwdh", "dwzm", "zydkqk",
					"jybm", "gwxz", "gwmc", "gzqk", "gzd", "sfjy", "sfqy",
					"djsj" },
					new String[] { dwmc, dwxz, dwdz, dwlxr, dwdh, dwzm, zydkqk,
							jybm, gwxz, gwmc, gzqk, gzd, sfjy, sfqy, djsj },
					"rowid", pkValue, request);
			if (judge) {
				request.setAttribute("update", "ok");
			} else {
				request.setAttribute("update", "no");
			}

		}

		sql = "select * from view_jygl_xsjyqkb where rid='" + pkValue + "'";
		String[] colList = dao
				.getColumnName("select * from view_jygl_xsjyqkb where 1=2"); // ������������
		String[] stuinfo = dao.getOneRs(sql, new String[] {}, colList);
		if (stuinfo != null) {
			for (int i = 0; i < colList.length; i++) {
				map.put(colList[i].toLowerCase(), stuinfo[i]); // ����¼ѭ������map��
			}
			djsj = map.get("djsj");
			if (null != djsj && !"".equalsIgnoreCase(djsj)) {
				map.put("djsj", dao.doForTime(djsj));
			}
		}

		request.setAttribute("rs", map);

		return mapping.findForward("success");

	}

	// ��Ƹ��Ϣ����ˢ��λ
	private ActionForward zpxxdwViewMore(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String sql = "";
		DAO dao = DAO.getInstance();
		String[] colList = null;
		String[] colListCN = null;
		String rsNum = "0";
		String act = request.getParameter("act");
		String doType = request.getParameter("doType");
		String doType2 = request.getParameter("doType2");
		String pkValue = request.getParameter("pkValue");
		String queryType = request.getParameter("queryType");
		request.setAttribute("qt", queryType);
		HashMap<String, String> map = new HashMap<String, String>();

		ArrayList<HashMap<String, String>> rs = new ArrayList<HashMap<String, String>>();
		HttpSession session = request.getSession();

		String dwxzcx = "select dwxzdm,dwxz from dmk_dwxz2"; // ��λ���ʴ���2
		List dwxzdm2List = dao.getList(dwxzcx, new String[] {}, new String[] {
				"dwxzdm", "dwxz" });
		request.setAttribute("dwxxdm", dwxzdm2List);

		String zgdwmc = "select sydzgbmdm,sydzgbm from dmk_sydzgbm"; // ��Դ�����ܵ�λ����
		List sydzgbmList = dao.getList(zgdwmc, new String[] {}, new String[] {
				"sydzgbmdm", "sydzgbm" });
		request.setAttribute("sydzgbmList", sydzgbmList);

		String hyfl = "select hyfldm,hyfl from dmk_hyfl"; // ��ҵ����
		List hyflList = dao.getList(hyfl, new String[] {}, new String[] {
				"hyfldm", "hyfl" });
		request.setAttribute("hyflList", hyflList);

		StuInfoDAO stuDao = new StuInfoDAO();
		request.setAttribute("shiList", stuDao.getShiList("").get("shiList"));
		request.setAttribute("xianList", stuDao.getShiList("").get("xianList"));
		request.setAttribute("ssList", stuDao.getSsList());

		String userType = session.getAttribute("userType").toString();
		if ("admin".equalsIgnoreCase(userType)
				|| "xx".equalsIgnoreCase(userType)) {
			request.setAttribute("who", "xx");
		} else if ("xy".equalsIgnoreCase(userType)) {
			request.setAttribute("who", "xy");
		}

		// ����ɾ��
		if (null != pkValue) {
			pkValue = pkValue.replace(" ", "+");
		}

		if ("del".equalsIgnoreCase(doType)) {
			boolean judge = false;
			judge = StandardOperation.delete("dwxxb", "dwid", pkValue, request);
			if (judge) {
				request.setAttribute("delete", "ok");
			} else {
				request.setAttribute("delete", "no");
			}
		}

		// ����ɾ��
		if ("delall".equalsIgnoreCase(doType2)) {
			String pkstring = request.getParameter("pkstring");
			String[] pkstringI = new String[] {};
			if (pkstring != null) {
				pkstringI = pkstring.split("!!#!!");
			}

			for (int i = 1; i < pkstringI.length; i++) {
				String whichrid = pkstringI[i];
				boolean judge = false;
				judge = StandardOperation.delete("dwxxb", "dwid", whichrid,
						request);
				if (judge) {
					request.setAttribute("delall", "ok");
				} else {
					request.setAttribute("delall", "no");
				}
			}
		}

		// ȫ�����
		if ("delallinfo".equalsIgnoreCase(doType2)) {

			boolean judge = false;

			judge = dao.runUpdateTab("truncate table dwxxb");

			if (judge) {
				request.setAttribute("delallinfo", "ok");
			} else {
				request.setAttribute("delallinfo", "no");
			}

		}

		String szdqsi = DealString.toGBK(request.getParameter("szdqsi")); // רҵ����
		String dwxz = DealString.toGBK(request.getParameter("dwxz")); // �༶����

		String szdqsh = DealString.toGBK(request.getParameter("szdqsh")); // ��λ����
		String sshy = DealString.toGBK(request.getParameter("hyfl")); // ������ҵ
		String zgbm = DealString.toGBK(request.getParameter("zgbm")); // ���ܲ���

		String dwmc = DealString.toGBK(request.getParameter("dwmc")); // ��λ����
		if ("query".equals(act)) {
			map.put("zgbm", zgbm);
			map.put("hyfl", sshy);
			map.put("dwxz", dwxz);
		}

		StringBuffer query = new StringBuffer();

		if (!("".equals(dwmc))) {
			query.append(" and dwmc like '%");
			query.append(dwmc);
			query.append("%'");
		}
		if (!("".equals(zgbm))) {
			query.append(" and zgbm like '%");
			query.append(zgbm);
			query.append("%'");
		}
		if (!("".equals(sshy))) {
			query.append(" and hyfl= '");
			query.append(sshy);
			query.append("'");
		}
		if (!("".equals(szdqsh))) {
			query.append(" and szdqsh='");
			query.append(szdqsh);
			query.append("'");
		}
		if (!("".equals(szdqsi))) {
			query.append(" and szdqsi= '");
			query.append(szdqsi);
			query.append("'");
		}
		if (!("".equals(dwxz))) {
			query.append(" and dwxz='");
			query.append(dwxz);
			query.append("'");
		}

		String query1 = query.toString();

		CommanForm dataSearchForm = (CommanForm) form;
		sql = "select count(*) count from dwxxb a where 1=1 " + query1;

		dataSearchForm.getPages().setMaxRecord(
				Integer.parseInt(dao.getOneRs(sql, new String[] {}, "count")));

		// ��ҵ��Ϣ
		sql = "select * from (select a.*,rownum r from (select distinct a.dwid,a.dwmc,a.zgbm,a.zcsj,a.lxbm,a.lxr,a.lxdh from dwxxb a where 1=1 "
				+ query1
				+ " order by dwmc desc) a ) a where a.r>"
				+ dataSearchForm.getPages().getStart()
				+ " and a.r<="
				+ (dataSearchForm.getPages().getStart() + dataSearchForm
						.getPages().getPageSize()) + " order by dwmc desc";

		colList = new String[] { "dwid", "dwmc", "zgbm", "zcsj", "lxbm", "lxr",
				"lxdh" };

		if ("query".equals(act)) {
			rs = dao.getArrayList2(sql, new String[] {}, colList);
		}
		sql = "select count(*) from dwxxb where 1=1 " + query1;
		int rsNuminfo = dao.getOneRsint(sql);
		rsNum = String.valueOf(rsNuminfo);
		request.setAttribute("rsNum", rsNum);
		colListCN = dao.getColumnNameCN(colList, "dwxxb");
		List topTr = dao.arrayToList(colList, colListCN);

		request.setAttribute("rs", rs);
		request.setAttribute("rs1", map);
		request.setAttribute("topTr", topTr);
		request.setAttribute("realTable", "dwxxb");
		request.setAttribute("querry", query1);
		return mapping.findForward("success");
	}

	// ѧ����ϢѧУ���
	private ActionForward xsxxsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			String userType) throws Exception {
		String pk = "xsxh";
		String sql = ""; // sql���
		DAO dao = DAO.getInstance();
		String realTable = "jygl_xsjbxxb";
		// String doType = request.getParameter("doType");
		// String pkValue = request.getParameter("pkValue");
		HashMap<String, String> map = new HashMap<String, String>();
		String sfsh = DealString.toGBK(request.getParameter("sfsh"));
		String btgyy = DealString.toGBK(request.getParameter("btgyy"));
		String xsxh = request.getParameter("xsxh");
		String act = request.getParameter("act");
		HttpSession session = request.getSession();
		String shperson = session.getAttribute("userName").toString();
		String userName = session.getAttribute("userName").toString();
		// String xxtj = DealString.toGBK(request.getParameter("xxtj"));
		ArrayList<Object> rs = new ArrayList<Object>();
		String[] colList = null;
		String[] colListCN = null;
		// String doType2 = request.getParameter("doType2");
		String doType3 = request.getParameter("doType3");
		String querry = " where 1=1 "; // ������ʼ��
		String rsNum = "0"; // ���صļ�¼��
		String tableName = "jygl_xsjbxxb"; // ��ѯ����
		String usertype = (String) request.getSession()
				.getAttribute("userType");

		if ("shenhe".equals(act)) {
			String xxdm = (String) request.getSession().getAttribute("xxdm");
			if ("12453".equals(xxdm)) {
				if (usertype == "xx" || "xx".equals(usertype)
						|| "admin".equals(usertype)) {
					String shsj = (dao
							.getOneRs(
									"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual", // ѧУ���ʱ��
									// //
									// ȡ�����ݿ���ʱ��
									new String[] {}, new String[] { "sdate" }))[0];
					if ("��ͨ����".equals(sfsh)) {

					}
					if ("δ���".equals(sfsh)) {
						btgyy = "";
						shperson = "";
						shsj = "";
					}
					sql = "update " + realTable + " set sfsh='" + sfsh + "' ,"
							+ "shsj='" + shsj + "' ,shperson='" + shperson
							+ "' ,btgyy='" + btgyy + "' where xsxh='" + xsxh
							+ "'";

					boolean judge = false;
					judge = dao.runUpdate(sql, new String[] {});
					if (judge) {
						request.setAttribute("shenhe", "ok");
					} else {
						request.setAttribute("shenhe", "no");
					}
				} else {
					String shsj = (dao
							.getOneRs(
									"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual", // ѧУ���ʱ��
									// //
									// ȡ�����ݿ���ʱ��
									new String[] {}, new String[] { "sdate" }))[0];
					if ("��ͨ����".equals(sfsh)) {

					}
					if ("δ���".equals(sfsh)) {
						btgyy = "";
						shperson = "";
						shsj = "";
					}
					sql = "update " + realTable + " set fdysh='" + sfsh + "' ,"
							+ "fdyshsj='" + shsj + "' ,fdyshr='" + shperson
							+ "' where xsxh='" + xsxh + "'";

					boolean judge = false;
					judge = dao.runUpdate(sql, new String[] {});
					if (judge) {
						request.setAttribute("shenhe", "ok");
					} else {
						request.setAttribute("shenhe", "no");
					}
				}
			} else {

				String shsj = (dao
						.getOneRs(
								"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual", // ѧУ���ʱ��
								// //
								// ȡ�����ݿ���ʱ��
								new String[] {}, new String[] { "sdate" }))[0];
				if ("��ͨ����".equals(sfsh)) {

				}
				if ("δ���".equals(sfsh)) {
					btgyy = "";
					shperson = "";
					shsj = "";
				}
				sql = "update " + realTable + " set sfsh='" + sfsh + "' ,"
						+ "shsj='" + shsj + "' ,shperson='" + shperson
						+ "' ,btgyy='" + btgyy + "' where xsxh='" + xsxh + "'";

				boolean judge = false;
				judge = dao.runUpdate(sql, new String[] {});
				if (judge) {
					request.setAttribute("shenhe", "ok");
				} else {
					request.setAttribute("shenhe", "no");
				}

			}
		}
		// ���ڷ���ʱ��Ĵ��� ���ڲ�ѯ����
		String xjsj = DealString.toGBK(request.getParameter("xjsj")); // ���ʱ��

		String nowsj = (dao.getOneRs(
				"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual", // ����ʱ��
				// //
				// ȡ�����ݿ���ʱ��
				new String[] {}, new String[] { "sdate" }))[0];
		String shsj = (dao.getOneRs(
				"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual", // ѧУ���ʱ��
				// //
				// ȡ�����ݿ���ʱ��
				new String[] {}, new String[] { "sdate" }))[0];
		String sjyear = nowsj.substring(0, 4);
		String sjmonth = nowsj.substring(4, 6);
		String sjday = nowsj.substring(6, 8);
		String sjqt = nowsj.substring(8, 14);
		String nowsj1 = sjyear + "-" + sjmonth + "-" + sjday;
		dealDate dealdate = new dealDate();
		String beforesj = "";
		if (!"".equals(xjsj)) {
			beforesj = dealdate.getDate2(Integer.parseInt(xjsj), nowsj1);
			beforesj = beforesj.replaceAll("-", "") + sjqt;
		}
		String name = DealString.toGBK(request.getParameter("name")); // ����
		// String xb = DealString.toGBK(request.getParameter("xb")); // �Ա�
		String xymc = DealString.toGBK(request.getParameter("xymc")); // ѧԺ
		// String nj = request.getParameter("nj"); // �꼶
		// String xxsh = DealString.toGBK(request.getParameter("xxsh")); // ѧУ���
		// ���ڷ���ʱ��Ĵ��� ���ڲ�ѯ����

		// �������ͨ��
		if ("pass".equalsIgnoreCase(doType3)) {
			String pkstring = request.getParameter("pkstring");
			String[] pkstringI = pkstring.split("!!#!!");

			for (int i = 1; i < pkstringI.length; i++) {
				String whichxh = pkstringI[i];
				sql = "update jygl_xsjbxxb set sfsh='��ͨ����',shperson='"
						+ shperson + "',shsj='" + shsj + "' where xsxh=?";
				boolean judge = false;
				judge = dao.runUpdate(sql, new String[] { whichxh });
				if (judge) {
					request.setAttribute("allpass", "ok");
				} else {
					request.setAttribute("allpass", "no");
				}
			}
		}
		// ������˷��
		if ("notpass".equalsIgnoreCase(doType3)) {
			String pkstring = request.getParameter("pkstring");
			String[] pkstringI = pkstring.split("!!#!!");

			for (int i = 1; i < pkstringI.length; i++) {
				String whichxh = pkstringI[i];
				sql = "update jygl_xsjbxxb set sfsh='δͨ��X',shperson='"
						+ shperson + "',shsj='" + shsj + "' where xsxh=?";
				boolean judge = false;
				judge = dao.runUpdate(sql, new String[] { whichxh });
				if (judge) {
					request.setAttribute("allnotpass", "ok");
				} else {
					request.setAttribute("allnotpass", "no");
				}
			}
		}
		String xxdm = dao.getXxdm();
		String dataType = request.getParameter("act"); // ������������
		String xbdm = request.getParameter("xbdm"); // �����Ա����
		String xslb = DealString.toGBK(request.getParameter("xslb")); // ����ѧ�����
		String nd = request.getParameter("nd"); // �������
		String bynd = request.getParameter("bynd"); // ��ҵ���

		if (userType.equals("teacher")) {
			sql = "select * from jygl_jyxywhb where username=?";
			String[] teainfo = dao.getOneRs(sql, new String[] { userName },
					new String[] { "usertype", "xymc" });
			String tea = "";
			if (null != teainfo) {
				tea = teainfo[0];
			}
			if ("����Ա".equals(tea)) {
				map.put("xymc", teainfo[1]);
				request.setAttribute("who", "fudaoyuan");
			} else {
				request.setAttribute("who", "teacher");
			}
		}

		map.put("xsxh", xsxh);
		map.put("name", name);
		map.put("xbdm", xbdm);
		map.put("xslb", xslb);
		map.put("xymc", xymc);
		map.put("nd", nd);
		map.put("bynd", bynd);

		if (xsxh == null) {
			xsxh = "";
		}
		if (name == null) {
			name = "";
		}
		if (xbdm == null) {
			xbdm = "";
		}
		if (xslb == null) {
			xslb = "";
		}
		if (xymc == null) {
			xymc = "";
		}
		if (nd == null) {
			nd = "";
		}
		if (bynd == null) {
			bynd = "";
		}
		if ((xsxh == null) || xsxh.equals("")) {
			querry += "and 1=1 ";
		} else {
			querry += "and xsxh like '%" + xsxh + "%' ";
		}
		if ((name == null) || name.equals("")) {
			querry += "and 1=1 ";
		} else {
			querry += "and name like '%" + name + "%' ";
		}
		if ((xbdm == null) || xbdm.equals("")) {
			querry += " and 1=1 ";
		} else {
			querry += " and xbdm='" + xbdm + "' ";
		}
		if ((xslb == null) || xslb.equals("")) {
			querry += " and 1=1 ";
		} else {
			querry += " and xslb='" + xslb + "' ";
		}
		if ((xymc == null) || xymc.equals("")) {
			querry += " and 1=1 ";
		} else {
			querry += " and xsxh in (select xsxh from jygl_xsjbxxb where xsxh in (select xh from view_xsjbxx where xymc ='"
					+ xymc + "'))";
		}
		if ((nd == null) || nd.equals("")) {
			querry += " and 1=1 ";
		} else {
			querry += " and nd='" + nd + "' ";
		}
		if ((bynd == null) || bynd.equals("")) {
			querry += " and 1=1 ";
		} else {
			querry += " and bynd='" + bynd + "' ";
		}

		sql = "select " + pk + " ����,rownum �к�,a.* from " + tableName + " a "
				+ querry;
		colList = new String[] { "����", "�к�", "nd", "bynd", "name", "xsxh",
				"xbdm", "xldm", "xslb", "xzdm", "zymc", "sbsj", "sfsh" };
		colListCN = dao.getColumnNameCN(colList, tableName);
		List topTr = dao.arrayToList(colList, colListCN);
		if ((request.getParameter("act") != null)
				&& request.getParameter("act").equalsIgnoreCase("go")) {
			rs.addAll(dao.rsToVator(sql, new String[] {}, colList));
			if (rs == null) {
				rsNum = "0";
			} else {
				rsNum = String.valueOf(rs.size());
			}
		}
		request.setAttribute("realTable", "jygl_xsjbxxb"); // ����ѧ��������Ϣ��
		request.setAttribute("ndList", dao.getNjList()); // ������ѧ����б�
		request.setAttribute("xyList", dao.getXyList());// ����ѧԺ�б�
		request.setAttribute("act", dataType);// �������ݲ�ѯ����
		request.setAttribute("rs", rs);// �������ݼ�
		request.setAttribute("topTr", topTr);// ���ͱ�ͷ
		request.setAttribute("rsNum", rsNum);// ���ͼ�¼��
		request.setAttribute("rs1", map);

		String view = request.getParameter("view");
		if ("view".equalsIgnoreCase(view)) {
			// ��ѯ����
			sql = "select * from " + realTable + " where " + pk + "='" + xsxh
					+ "'";
			colList = dao.getColumnName("select * from jygl_xsjbxxb where 1=2"); // ������������
			String[] stuinfo = dao.getOneRs(sql, new String[] {}, colList);
			sql = "select xymc from view_xsjbxx where xh=?";
			String[] xymc1 = dao.getOneRs(sql, new String[] { xsxh },
					new String[] { "xymc" });
			if (stuinfo != null) {
				for (int i = 0; i < colList.length; i++) {
					map.put(colList[i].toLowerCase(), stuinfo[i]); // ����¼ѭ������map��
				}
				if (Globals.XXDM_ZGDZDX.equals(xxdm)) {
					shsj = null;
					// String fbsj = null;
					sjyear = null;
					String sjmon = null;
					sjday = null;
					String sjhour = null;
					if (null != (map.get("shsj"))) {
						String sj = map.get("shsj").toString();
						sjyear = sj.substring(0, 4);
						sjmon = sj.substring(4, 6);
						sjday = sj.substring(6, 8);
						sjhour = sj.substring(8, 10);
						shsj = sjyear + "��" + sjmon + "��" + sjday + "��"
								+ sjhour + "ʱ";
						map.put("shsj", shsj);
					}
				}
				// ��ʾ�������Ա�������
				if ("1".equals(map.get("xbdm"))) {
					map.put("xbdm", "��");
				} else if ("2".equals(map.get("xbdm"))) {
					map.put("xbdm", "Ů");
				}
				sql = "select xl from dmk_xl where xldm=?";
				String xldm = map.get("xldm");
				String xlinfo = dao.getOneRs(sql, new String[] { xldm }, "xl");
				map.put("xl", xlinfo);

				sql = "select sydq from dmk_sydq where sydqdm=?";
				String sydqdm = map.get("sydqdm");
				String sydqinfo = dao.getOneRs(sql, new String[] { sydqdm },
						"sydq");
				String qxdm = map.get("jgshi");
				String sydqinfo1 = dao.getOneRs(
						"select qxmc from dmk_qx WHERE qxdm = ?",
						new String[] { qxdm }, "qxmc");
				map.put("sydq", sydqinfo + sydqinfo1);

				if ("ר����".equals(map.get("xslb"))
						|| "������".equals(map.get("xslb"))) {
					sql = "select pyfs from dmk_bzpyfs where pyfsdm=?";
					String pyfsdm = map.get("pyfsdm");
					String pyfsinfo = dao.getOneRs(sql,
							new String[] { pyfsdm }, "pyfs");
					map.put("pyfs", pyfsinfo);
				} else if ("�о���".equals(map.get("xslb"))) {
					sql = "select pyfs from dmk_yjspyfs where pyfsdm=?";
					String pyfsdm = map.get("pyfsdm");
					String pyfsinfo = dao.getOneRs(sql,
							new String[] { pyfsdm }, "pyfs");
					map.put("pyfs", pyfsinfo);
				}
				if (null != xymc) {
					map.put("xymc", xymc1[0]);
				}
				String sbsj = null;
				sjyear = null;
				String sjmon = null;
				sjday = null;
				String sj = map.get("sbsj").toString();
				sjyear = sj.substring(0, 4);
				sjmon = sj.substring(4, 6);
				sjday = sj.substring(6, 8);
				sbsj = sjyear + "��" + sjmon + "��" + sjday + "��";
				map.put("sbsj", sbsj);
			}
			request.setAttribute("rs", map);
		}

		if ("shenhe".equals(act)) {
			return mapping.findForward("success");
		} else {
			return mapping.findForward("successdz");
		}
	}

	// ��ҵ���ͳ��
	@SuppressWarnings("unchecked")
	private ActionForward jyqktj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String tjxm = request.getParameter("tjxm");
		String bynd = request.getParameter("nd");
		String type = request.getParameter("type");
		List<HashMap<String, Object>> rs = new ArrayList<HashMap<String, Object>>();
		JyglService service = new JyglService();
		String[] keys = null;
		String[] values = null;
		if (!Base.isNull(tjxm)) {
			if (tjxm.equals("xjsp")) {
				if ("tj".equals(type)) {
					keys = new String[] { "xy", "zy", "cyrs", "xjspfb", "xj1",
							"xj2", "xj3", "xj4", "xj5", "xj6", "pjgz" };
					values = new String[] { "ѧԺ", "רҵ", "��������", "н��ˮƽ�ֲ���������",
							"0-1000", "1000-1500", "1500-2000", "2000-2500",
							"2500-3000", "3000����", "ƽ������" };
					rs = service.getXjspList_ser(bynd);
					HashMap totalmap = service.getXjspTotalMap(rs);
					request.setAttribute("totalmap", totalmap);
				} else {
					String modelPath = servlet.getServletContext().getRealPath(
							"")
							+ "/print/shgc_xjtjb.xls";
					response.reset();
					response.setContentType("application/vnd.ms-excel");
					WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(
							new File(modelPath), response.getOutputStream());
					service.printByxj(wwb, bynd);
					return mapping.findForward("");
				}

			} else if (tjxm.equals("zydkl")) {
				if ("tj".equals(type)) {
					keys = new String[] { "xy", "zy", "cyrs", "byqx", "bzy",
							"kzy", "zydkl" };
					values = new String[] { "ѧԺ", "רҵ", "��������", "��ҵȥ��", "��רҵ",
							"��רҵ", "רҵ�Կ���" };
					rs = service.getZydklList_ser(bynd);
					HashMap totalmap = service.getZydkTotalMap(rs);
					request.setAttribute("totalmap", totalmap);
				} else {
					String modelPath = servlet.getServletContext().getRealPath(
							"")
							+ "/print/shgc_jyzydkl.xls";
					response.reset();
					response.setContentType("application/vnd.ms-excel");
					WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(
							new File(modelPath), response.getOutputStream());
					service.printZydkl(wwb, bynd);
					return mapping.findForward("");
				}
			} else if (tjxm.equals("dyfb")) {
				if ("tj".equals(type)) {
					keys = new String[] { "xy", "byrs", "qyrs", "tjjswqyrs",
							"dwszdq", "sq", "jq", "wd" };
					values = new String[] { "ѧԺ", "��ҵ����", "ǩԼ����", "ͳ�ƻ���ΪǩԼ����",
							"��λ���ڵ���", "����", "����", "���" };
					rs = (List<HashMap<String, Object>>) service
							.getDyfbList(bynd);
				} else {
					String modelPath = servlet.getServletContext().getRealPath(
							"")
							+ "/print/shgc_byjyfb.xls";
					response.reset();
					response.setContentType("application/vnd.ms-excel");
					WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(
							new File(modelPath), response.getOutputStream());
					service.printDyfb(wwb, bynd);
					return mapping.findForward("");
				}
			} else if (tjxm.equals("dwxzqk")) {
				if ("tj".equals(type)) {
					keys = new String[] { "xy", "byrs", "qyrs", "tjjswqyrs",
							"dwxz", "gy", "jt", "my", "sy", "gf", "hz", "dz" };
					values = new String[] { "ѧԺ", "��ҵ����", "ǩԼ����", "ͳ�ƻ���ΪǩԼ����",
							"��λ����", "����", "����", "��Ӫ", "˽Ӫ", "�ɷ�", "����", "����" };
					rs = (List<HashMap<String, Object>>) service
							.getDwxzqkList(bynd);
				} else {
					String modelPath = servlet.getServletContext().getRealPath(
							"")
							+ "/print/shgc_jydwxz.xls";
					response.reset();
					response.setContentType("application/vnd.ms-excel");
					WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(
							new File(modelPath), response.getOutputStream());
					service.printXzqk(wwb, bynd);
					return mapping.findForward("");
				}

			} else if (tjxm.equals("hyfb")) {
				if ("tj".equals(type)) {
					keys = new String[] { "xy", "byrs", "qyrs", "tjjswqyrs",
							"dwszhy", "zzy", "fwy", "ydtxjsjxxy", "jrbxfdc",
							"jykywgtyfl", "pflsmycy", "jzy", "jtyscc",
							"gjdzjgshtt", "qt" };
					values = new String[] { "ѧԺ", "��ҵ����", "ǩԼ����", "ͳ�ƻ���ΪǩԼ����",
							"��λ������ҵ", "����ҵ", "������ҵ", "�ʵ�ͨ�ż������Ϣҵ",
							"���ڡ����ռ����ز�ҵ", "�������С��Ĺ㡢������������ḣ��ҵ", "��������ó�ס�����ҵ",
							"����ҵ", "��ͨ���䡢�ִ�ҵ", "���ҡ��������غ��������", "����" };
					rs = (List<HashMap<String, Object>>) service
							.getHyfbList(bynd);
				} else {
					String modelPath = servlet.getServletContext().getRealPath(
							"")
							+ "/print/shgc_jyhyfb.xls";
					response.reset();
					response.setContentType("application/vnd.ms-excel");
					WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(
							new File(modelPath), response.getOutputStream());
					service.printHyfb(wwb, bynd);
					return mapping.findForward("");
				}

			} else if (tjxm.equals("jyphb")) {
				if ("tj".equals(type)) {
					keys = new String[] { "xy", "zy", "byrs", "qyrs", "qyl",
							"qylpx", "jyrs", "jyl", "jylpx" };
					values = new String[] { "ѧԺ", "רҵ", "��ҵ����", "ǩԼ����", "ǩԼ��",
							"ǩԼ������", "��ҵ����", "��ҵ��", "��ҵ������" };
					rs = service.getJyphbList_ser(bynd);
					HashMap totalmap = service.getJyphTotalMap(rs);
					request.setAttribute("totalmap", totalmap);
				} else {
					String modelPath = servlet.getServletContext().getRealPath(
							"")
							+ "/print/shgc_jyphb.xls";
					response.reset();
					response.setContentType("application/vnd.ms-excel");
					WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(
							new File(modelPath), response.getOutputStream());
					service.printJyphb(wwb, bynd);
					return mapping.findForward("");
				}
			}
			request.setAttribute("tjxm", tjxm);
			request.setAttribute("rs", rs);
			request.setAttribute("tabtop", service.getTabTopMap(keys, values));
		}
		request.setAttribute("ndList", Base.getXnndList());
		return mapping.findForward("success");
	}

	private boolean isNull(String str) {
		return ((str == null) || str.equalsIgnoreCase("") || str
				.equalsIgnoreCase("all"));
	}

	// ѧ����ѯ�ظ�
	private ActionForward zxzxshtz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// CommanForm dataSearchForm = (CommanForm) form;
		String pk = "xsxh";
		String sql = ""; // sql���
		DAO dao = DAO.getInstance();
		String tableName = "zxzyzxsqb";
		String doType = request.getParameter("doType");
		String act = request.getParameter("act");
		String pkValue = request.getParameter("pkValue");
		HashMap<String, String> mapsavesq = new HashMap<String, String>();
		HashMap<String, String> map = new HashMap<String, String>();

		if ("savesq".equals(act)) {
			String xh = request.getParameter("xh");
			String xsxm = DealString.toGBK(request.getParameter("xsxm"));
			String age = request.getParameter("age");
			String xb = DealString.toGBK(request.getParameter("xb"));
			String zymc = DealString.toGBK(request.getParameter("zymc"));
			String email = request.getParameter("email");
			String lxdh = DealString.toGBK(request.getParameter("lxdh"));
			String zxnr = DealString.toGBK(request.getParameter("zxnr"));
			String zxjshf = DealString.toGBK(request.getParameter("zxjshf"));
			String hfrxm = DealString.toGBK(request.getParameter("hfrxm"));
			String hfsj = request.getParameter("hfsj");

			mapsavesq.put("xh", xh);
			mapsavesq.put("xsxm", xsxm);
			mapsavesq.put("age", age);
			mapsavesq.put("xb", xb);
			mapsavesq.put("zymc", zymc);
			mapsavesq.put("email", email);
			mapsavesq.put("lxdh", lxdh);
			mapsavesq.put("zxnr", zxnr);
			mapsavesq.put("zxjshf", zxjshf);
			mapsavesq.put("hfrxm", hfrxm);
			mapsavesq.put("hfsj", hfsj);

			String zxsj = (dao
					.getOneRs(
							"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual", // ����ʱ��
							// //
							// ȡ�����ݿ���ʱ��
							new String[] {}, new String[] { "sdate" }))[0];

			boolean judge = false;
			judge = StandardOperation.insert(tableName, new String[] { "xh",
					"xsxm", "age", "xb", "zymc", "email", "lxdh", "zxnr",
					"zxsj", "zxjshf", "hfrxm", "hfsj" }, new String[] { xh,
					xsxm, age, xb, zymc, email, lxdh, zxnr, zxsj, zxjshf,
					hfrxm, hfsj }, request);
			if (judge) {
				request.setAttribute("save", "ok");
			} else {
				request.setAttribute("save", "no");
			}
			request.setAttribute("rs", mapsavesq);
			return mapping.findForward("success");
		}

		if ("answer".equals(act)) {
			String xsqr = DealString.toGBK(request.getParameter("xsqr"));
			String xsqrsj = (dao
					.getOneRs(
							"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual", // ����ʱ��
							// //
							// ȡ�����ݿ���ʱ��
							new String[] {}, new String[] { "sdate" }))[0];
			boolean judge = false;
			judge = StandardOperation.update(tableName, new String[] { "xsqr",
					"xsqrsj" }, new String[] { xsqr, xsqrsj }, pk, pkValue,
					request);
			if (judge) {
				request.setAttribute("answer", "ok");
			} else {
				request.setAttribute("answer", "no");
			}
		}

		if ("view".equalsIgnoreCase(doType)) {
			// ��ѯ����
			if ("".equals(pkValue)) {
				pkValue = request.getParameter("xsxh");
			}
			sql = "select * from " + tableName + " where " + pk + "='"
					+ pkValue + "'";
			String[] colList = dao.getColumnName("select * from " + tableName
					+ " where 1=2"); // ������������
			String[] zxyyglinfo = dao.getOneRs(sql, new String[] {}, colList);
			if (zxyyglinfo != null) {
				for (int i = 0; i < colList.length; i++) {
					map.put(colList[i].toLowerCase(), zxyyglinfo[i]); // ����¼ѭ������map��
				}
			}
			if ("����ԼX".equals(map.get("xsqr"))
					|| "��ȷ�ϡ�".equals(map.get("xsqr"))) {
				request.setAttribute("xsqr", "no");
			} else {
				request.setAttribute("xsqr", "ok");
			}
			// ��ȡ��ѯʦ����
			String num = map.get("num");
			sql = "select name from jygl_zxjsxxb where num=?";
			String[] zxsinfo = dao.getOneRs(sql, new String[] { num },
					new String[] { "name" });
			if (zxsinfo != null) {
				map.put("zxsname", zxsinfo[0]);
			}
			// ��ȡѧ��������Ϣ
			String xsxh = map.get("xsxh");
			sql = "select * from view_xsjbxx where xh=?";
			String[] stuinfo = dao.getOneRs(sql, new String[] { xsxh },
					new String[] { "xm", "xb", "nj", "xymc", "zymc", });
			if (stuinfo != null) {
				map.put("name", stuinfo[0]);
				map.put("xb", stuinfo[1]);
				map.put("nj", stuinfo[2]);
				map.put("xymc", stuinfo[3]);
				map.put("zymc", stuinfo[4]);
			}
			// ���ύʱ��ת������
			String tjsj = null;
			String sjyear = null;
			String sjmon = null;
			String sjday = null;
			String sjhour = null;
			String sjmin = null;
			String sjss = null;
			if (null != map.get("tjsj")) {
				String sj = map.get("tjsj").toString();
				sjyear = sj.substring(0, 4);
				sjmon = sj.substring(4, 6);
				sjday = sj.substring(6, 8);
				sjhour = sj.substring(8, 10);
				sjmin = sj.substring(10, 12);
				sjss = sj.substring(12, 14);
				tjsj = sjyear + "��" + sjmon + "��" + sjday + "�� " + sjhour + ":"
						+ sjmin + ":" + sjss;
				map.put("tjsj", tjsj);
			}
			// ����ѯʦȷ��ʱ��ת������
			String zxsqrsj = null;
			if (null != map.get("zxsqrsj")) {
				String sj = map.get("zxsqrsj").toString();
				sjyear = sj.substring(0, 4);
				sjmon = sj.substring(4, 6);
				sjday = sj.substring(6, 8);
				sjhour = sj.substring(8, 10);
				sjmin = sj.substring(10, 12);
				sjss = sj.substring(12, 14);
				zxsqrsj = sjyear + "��" + sjmon + "��" + sjday + "�� " + sjhour
						+ ":" + sjmin + ":" + sjss;
				map.put("zxsqrsj", zxsqrsj);
			}
			// ��ѧ��ȷ��ʱ��ת������
			String xsqrsj = null;
			if (null != map.get("xsqrsj")) {
				String sj = map.get("xsqrsj").toString();
				sjyear = sj.substring(0, 4);
				sjmon = sj.substring(4, 6);
				sjday = sj.substring(6, 8);
				sjhour = sj.substring(8, 10);
				sjmin = sj.substring(10, 12);
				sjss = sj.substring(12, 14);
				xsqrsj = sjyear + "��" + sjmon + "��" + sjday + "�� " + sjhour
						+ ":" + sjmin + ":" + sjss;
				map.put("xsqrsj", xsqrsj);
			}
		}
		String stutype = (String) request.getSession().getAttribute("userType");
		if ("stu".equals(stutype)) {
			String stuname = (String) request.getSession().getAttribute(
					"userName");
			String[] cxstu = dao.getOneRs(
					"SELECT xh,xm,xb,zymc FROM view_xsjbxx where xh=?",
					new String[] { stuname }, new String[] { "xh", "xm", "xb",
							"zymc" });
			// String xsqrsj;
			if (cxstu != null) {
				map.put("xh", cxstu[0]);
				map.put("xsxm", cxstu[1]);
				map.put("xb", cxstu[2]);
				map.put("zymc", cxstu[3]);
			}

		}
		request.setAttribute("rs", map);// �������ݼ�
		return mapping.findForward("success");
	}

	// ѧ����ѯ��ѯ
	private ActionForward zxzxshcx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CommanForm dataSearchForm = (CommanForm) form;
		// String pk = "xsxh";
		String sql = ""; // sql���
		DAO dao = DAO.getInstance();
		String tableName = "zxzyzxsqb";
		// String doType = request.getParameter("doType");
		String act = request.getParameter("act");
		// String pkValue = request.getParameter("pkValue");
		HashMap<String, String> mapsavesq = new HashMap<String, String>();
		HashMap<String, String> mapsq = new HashMap<String, String>();
		HashMap<String, String> map = new HashMap<String, String>();
		String querry = " where 1=1 ";

		String[] colList = null;
		String[] colListCN = null;
		ArrayList<Object> rs = new ArrayList<Object>(); // ��ü�¼
		String rsNum = "0";
		List topTr = null;
		// HashMap<String, String> map = new HashMap<String, String>();
		HttpSession session = request.getSession();
		String userName = session.getAttribute("userName").toString();
		String userType = session.getAttribute("userType").toString();

		if (userType.equals("xy")) {
			request.setAttribute("isteacher", "ok");
		} else {
			request.setAttribute("isteacher", "no");
		}

		if ("teaview".equals(act)) {
			String pk12 = request.getParameter("rid");
			String pk1 = RowidToPk.rowidToPK(pk12);

			colList = new String[] { "rid", "xh", "xsxm", "age", "xb", "zymc",
					"email", "lxdh", "zxnr", "zxsj", "zxjshf", "hfrxm", "hfsj" };
			sql = "select ROWID rid,a.* from zxzyzxsqb a where rowid='" + pk1
					+ "'";
			map = dao.getRSArray(sql, colList);
			request.setAttribute("rs", map);
			request.setAttribute("who", "teacher");
			return mapping.findForward("success1");
		}

		if ("stuview".equals(act)) {
			String pk12 = request.getParameter("rid");
			String pk1 = RowidToPk.rowidToPK(pk12);
			colList = new String[] { "rid", "xh", "xsxm", "age", "xb", "zymc",
					"email", "lxdh", "zxnr", "zxsj", "zxjshf", "hfrxm", "hfsj" };
			sql = "select ROWID rid,a.* from zxzyzxsqb a where rowid='" + pk1
					+ "'";
			map = dao.getRSArray(sql, colList);
			// ���ύʱ��ת������
			String tjsj = null;
			String sjyear = null;
			String sjmon = null;
			String sjday = null;
			String sjhour = null;
			String sjmin = null;
			String sjss = null;
			String hfsj1 = map.get("hfsj");
			if (hfsj1 == null) {
				hfsj1 = "";
			}
			if ("".equals(hfsj1)) {
				String sj = map.get("hfsj").toString();
				sjyear = sj.substring(0, 4);
				sjmon = sj.substring(4, 6);
				sjday = sj.substring(6, 8);
				sjhour = sj.substring(8, 10);
				sjmin = sj.substring(10, 12);
				sjss = sj.substring(12, 14);
				tjsj = sjyear + "��" + sjmon + "��" + sjday + "�� " + sjhour + ":"
						+ sjmin + ":" + sjss;
				map.put("hfsj", tjsj);
			}
			request.setAttribute("rs", map);
			request.setAttribute("who", "stu");
			return mapping.findForward("success2");
		}

		if (userType.equals("stu")) {
			sql = "select rowid ����,rownum �к�,a.* from " + tableName + " a "
					+ " where xh='" + userName + "'";
			colList = new String[] { "����", "�к�", "xh", "xsxm", "zymc", "age",
					"xb", "lxdh", "zxsj", "zxjshf", "hfrxm", "hfsj" };
			colListCN = dao.getColumnNameCN(colList, tableName);
			topTr = dao.arrayToList(colList, colListCN);
			rs.addAll(dao.rsToVator(sql, new String[] {}, colList));
			if (rs == null) {
				rsNum = "0";
			} else {
				rsNum = String.valueOf(rs.size());
			}
			request.setAttribute("who", "student");

			request.setAttribute("rsp", mapsq);
			request.setAttribute("rs", rs); // �������ݼ�
			request.setAttribute("topTr", topTr); // ���ͱ�ͷ
			request.setAttribute("rsNum", rsNum); // ���ͼ�¼��
			return mapping.findForward("success");
		}

		if ("go".equals(act)) {
			String xm = DealString.toGBK(request.getParameter("xm"));
			String xb = DealString.toGBK(request.getParameter("xb"));
			String xh = DealString.toGBK(request.getParameter("xh"));
			String lxdh = DealString.toGBK(request.getParameter("lxdh"));
			mapsq.put("xm", xm);
			mapsq.put("xb", xb);
			mapsq.put("xh", xh);
			mapsq.put("lxdh", lxdh);
			request.setAttribute("rsp", mapsq);
			if (xm == null) {
				xm = "";
			}
			if (xb == null) {
				xb = "";
			}
			if (xh == null) {
				xh = "";
			}
			if (lxdh == null) {
				lxdh = "";
			}

			if (xm.equals("")) {
				querry += "and 1=1 ";
			} else {
				querry += "and xsxm ='" + xm + "' ";
			}
			if (xb.equals("")) {
				querry += "and 1=1 ";
			} else {
				querry += "and xb ='" + xb + "' ";
			}
			if (xh.equals("")) {
				querry += "and 1=1 ";
			} else {
				querry += "and xh ='" + xh + "' ";
			}
			if (lxdh.equals("")) {
				querry += "and 1=1 ";
			} else {
				querry += "and lxdh ='" + lxdh + "' ";
			}
			sql = "select count(*) count from zxzyzxsqb a " + querry;

			dataSearchForm.getPages().setMaxRecord(
					Integer.parseInt(dao
							.getOneRs(sql, new String[] {}, "count")));

			sql = "select * from (select ROWID rid,rownum r,a.*  from (SELECT xh,xsxm,zymc,age,xb,lxdh,zxsj,zxjshf,hfrxm,hfsj FROM zxzyzxsqb a "
					+ querry
					+ " order by zxsj desc) a ) a where a.r>"
					+ dataSearchForm.getPages().getStart()
					+ " and a.r<="
					+ (dataSearchForm.getPages().getStart() + dataSearchForm
							.getPages().getPageSize()) + " order by zxsj desc";

			colList = new String[] { "rid", "xh", "xsxm", "zymc", "age", "xb",
					"lxdh", "zxsj", "zxjshf", "hfrxm", "hfsj" };
			if ("go".equals(act)) {
				// rs = dao.getArrayList2(sql, new String[] {}, colList);
				rs.addAll(dao.rsToVator(sql, new String[] {}, colList));
			}
			sql = "select count(*) from zxzyzxsqb " + querry;
			int rsNuminfo = dao.getOneRsint(sql);
			rsNum = String.valueOf(rsNuminfo);
			colListCN = dao.getColumnNameCN(colList, "zxzyzxsqb");
			topTr = dao.arrayToList(colList, colListCN);
			request.setAttribute("rs", rs);// �������ݼ�
			request.setAttribute("topTr", topTr);// ���ͱ�ͷ
			request.setAttribute("rsNum", rsNum);// ���ͼ�¼��
			return mapping.findForward("success");

		}
		if ("answer".equals(act)) {
			String zxjshf = DealString.toGBK(request.getParameter("zxjshf"));
			String pk12 = request.getParameter("rid");
			String pk1 = RowidToPk.rowidToPK(pk12);
			String hfsj = (dao
					.getOneRs(
							"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual", // ����ʱ��
							// //
							// ȡ�����ݿ���ʱ��
							new String[] {}, new String[] { "sdate" }))[0];
			String[] yhxmts = dao.getOneRs(
					"SELECT * FROM jygl_zxjsxxb WHERE userid=?",
					new String[] { userName }, new String[] { "name" });
			String yhxm;
			if (yhxmts != null) {
				yhxm = yhxmts[0];
			} else {
				yhxm = "";
			}
			String ishf = (dao.getOneRs(
					"select ROWID rid,a.* from zxzyzxsqb a where rowid=?",
					new String[] { pk1 }, new String[] { "hfsj" }))[0];
			boolean judge = false;
			if (yhxm == null || "".equals(yhxm)) {
				request.setAttribute("notea", "notea");
			} else if (ishf != null) {
				request.setAttribute("ishf", "ishf");
				String rid = request.getParameter("rid");
				String xh = request.getParameter("xh");
				String xsxm = DealString.toGBK(request.getParameter("xsxm"));
				String age = request.getParameter("age");
				String xb = DealString.toGBK(request.getParameter("xb"));
				String zymc = DealString.toGBK(request.getParameter("zymc"));
				String email = request.getParameter("email");
				String lxdh = DealString.toGBK(request.getParameter("lxdh"));
				String zxnr = DealString.toGBK(request.getParameter("zxnr"));
				String zxjshf1 = DealString.toGBK(request
						.getParameter("zxjshf"));

				mapsavesq.put("rid", rid);
				mapsavesq.put("xh", xh);
				mapsavesq.put("xsxm", xsxm);
				mapsavesq.put("age", age);
				mapsavesq.put("xb", xb);
				mapsavesq.put("zymc", zymc);
				mapsavesq.put("email", email);
				mapsavesq.put("lxdh", lxdh);
				mapsavesq.put("zxnr", zxnr);
				mapsavesq.put("zxjshf", zxjshf1);
			} else {
				judge = StandardOperation.update(tableName, new String[] {
						"zxjshf", "hfrxm", "hfsj" }, new String[] { zxjshf,
						yhxm, hfsj }, "rowid", pk1, request);
				if (judge) {
					request.setAttribute("answer", "ok");
				} else {
					request.setAttribute("answer", "no");
				}
				String rid = request.getParameter("rid");
				String xh = request.getParameter("xh");
				String xsxm = DealString.toGBK(request.getParameter("xsxm"));
				String age = request.getParameter("age");
				String xb = DealString.toGBK(request.getParameter("xb"));
				String zymc = DealString.toGBK(request.getParameter("zymc"));
				String email = request.getParameter("email");
				String lxdh = DealString.toGBK(request.getParameter("lxdh"));
				String zxnr = DealString.toGBK(request.getParameter("zxnr"));
				String zxjshf1 = DealString.toGBK(request
						.getParameter("zxjshf"));

				mapsavesq.put("rid", rid);
				mapsavesq.put("xh", xh);
				mapsavesq.put("xsxm", xsxm);
				mapsavesq.put("age", age);
				mapsavesq.put("xb", xb);
				mapsavesq.put("zymc", zymc);
				mapsavesq.put("email", email);
				mapsavesq.put("lxdh", lxdh);
				mapsavesq.put("zxnr", zxnr);
				mapsavesq.put("zxjshf", zxjshf1);
			}
			request.setAttribute("rs", mapsavesq);
			return mapping.findForward("success1");
		}
		request.setAttribute("rsp", mapsq);
		request.setAttribute("rs", map);// �������ݼ�
		return mapping.findForward("success");
	}

	// ���չ��̱�ҵ����Ϣ�ϱ�
	private ActionForward ahgcjyglBysxxSb(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		CommanForm actionForm = (CommanForm) form;
		DAO dao = DAO.getInstance();
		HttpSession session = request.getSession();
		List<Object> rs = new ArrayList<Object>();
		HashMap<String, String> map = new HashMap<String, String>();
		String[] colList = null;
		String[] colListCN = null;
		String sql = "";// sql���
		StringBuffer querry = new StringBuffer(" where 1=1 ");// sql����
		StringBuffer querry1 = new StringBuffer("");
		String rsNum = "0";// ���صļ�¼��
		String userDep = session.getAttribute("userDep").toString();
		String userType = dao.getUserType(userDep);
		String xy = Base.chgNull(request.getParameter("xydm"), "", 1);
		String xh = Base.chgNull(request.getParameter("xh"), "", 1);
		String xm = Base.chgNull(request.getParameter("xm"), "", 1);
		String go = request.getParameter("go");
		sql = "select * from xtszb where rownum=1";
		String[] result = dao.getOneRs(sql, new String[] {},
				new String[] { "dqxn" });
		String resnd = "";
		if (!Base.isNull(result[0])) {
			resnd = result[0].substring(5);
		}

		if ("imp".equalsIgnoreCase(go)) {
			String cbVal = Base.chgNull(request.getParameter("cbVal"), "", 1);
			String xxdm = StandardOperation.getXxdm();
			String xxmc = StandardOperation.getXxmc();
			String[] cbValT = cbVal.split("!!splitOne!!");
			String[] sqlT = new String[cbValT.length];

			for (int i = 1; i < cbValT.length; i++) {
				String pkT = cbValT[i];
				sqlT[i] = "insert into jygl_xsjbxxb(xsxh,ID,NAME,xbdm,zydm,zymc,xzdm,nd,xxdm,xxmc,sbsj,bynd) (SELECT xh,sfzh,xm,(CASE WHEN xb LIKE '%��%' THEN '1' WHEN xb LIKE '%Ů%' THEN '2' ELSE '0' END) xb,zydm,zymc,xz,dqszj,'"
						+ xxdm
						+ "','"
						+ xxmc
						+ "',(select to_char(sysdate,'yyyymmddhh24miss') sdate from dual),"
						+ resnd + " FROM view_xsjbxx where xh='" + pkT + "')";
			}
			dao.runBatch(sqlT);
			go = "go";
		}

		if (userType.equalsIgnoreCase("xy")
				&& (xy == null || xy.trim().equals(""))) {
			xy = userDep;
		}
		String zy = Base.chgNull(request.getParameter("zydm"), "", 1);
		String bj = Base.chgNull(request.getParameter("bjdm"), "", 1);
		String nj = Base.chgNull(request.getParameter("nj"), "", 1);

		if (!isNull(nj)) {
			querry.append(" and nj='");
			querry.append(nj);
			querry.append("' ");
		} else {

			String resnd1 = "";
			if (!Base.isNull(result[0])) {
				resnd1 = result[0].substring(5);
			} else {
				resnd1 = "";
			}
			querry.append(" and nj='");
			querry.append((Integer.parseInt(resnd1) - 4));
			querry.append("' ");
		}
		if (!isNull(xy)) {
			querry.append(" and xydm='");
			querry.append(xy);
			querry.append("' ");
		}
		if (!isNull(zy)) {
			querry.append(" and zydm='");
			querry.append(zy);
			querry.append("' ");
		}
		if (!isNull(bj)) {
			querry.append(" and bjdm='");
			querry.append(bj);
			querry.append("' ");
		}
		if (!isNull(xh)) {
			querry.append(" and xh='");
			querry.append(xh);
			querry.append("' ");
		}
		if (!isNull(xm)) {
			querry.append(" and xm='");
			querry.append(xm);
			querry.append("' ");
		}
		querry.append(querry1.toString());
		colList = new String[] { "pk", "xh", "xm", "xb", "xymc", "zymc",
				"bjmc", "bynd" };
		sql = "select pk,xh,xm,xb,xymc,zymc,bjmc,bynd from (select * from (select xh pk,rownum r,xh,xm,xb,xymc,zymc,bjmc,nj+xz bynd from view_xsjbxx a"
				+ querry.toString()
				+ "and (nj+xz)="
				+ resnd
				+ " and not exists(select 1 from jygl_xsjbxxb b WHERE a.xh=b.xsxh)) where r<="
				+ (actionForm.getPages().getStart() + actionForm.getPages()
						.getPageSize())
				+ ") where r>"
				+ actionForm.getPages().getStart();
		String sqlt = "select count(*) num from view_xsjbxx a"
				+ querry.toString()
				+ " and (nj+xz)="
				+ resnd
				+ " and not exists(select 1 from jygl_xsjbxxb b WHERE a.xh=b.xsxh)";
		colListCN = dao.getColumnNameCN(colList, "view_xsjbxx");
		List topTr = dao.arrayToList(colList, colListCN);
		if ((go != null) && go.equalsIgnoreCase("go")) {
			rs.addAll(dao.rsToVator(sql, new String[] {}, colList));
			if (rs == null) {
				rsNum = "0";
			} else {
				rsNum = String.valueOf(rs.size());
			}
		}

		map.put("xydm", xy);
		map.put("zydm", zy);
		map.put("bjdm", bj);
		map.put("xm", xm);
		map.put("nj", nj);
		map.put("xh", xh);
		xy = xy == null ? "" : xy;
		zy = zy == null ? "" : zy;
		nj = nj == null ? "" : nj;
		String bjKey = xy + "!!" + zy + "!!" + nj;
		actionForm.getPages().setMaxRecord(
				Integer.parseInt(dao.getOneRs(sqlt, new String[] {}, "num")));
		request.setAttribute("rs1", map);
		request.setAttribute("njList", Base.getNjList());// �����꼶�б�
		request.setAttribute("xyList", Base.getXyList());// ����ѧԺ�б�
		request.setAttribute("zyList", Base.getZyMap().get(xy));// ����רҵ�б�
		request.setAttribute("bjList", Base.getBjMap().get(bjKey));// ���Ͱ༶�б�
		request.setAttribute("rs", rs);// �������ݼ�
		request.setAttribute("topTr", topTr);// ���ͱ�ͷ
		request.setAttribute("rsNum", rsNum);// ���ͼ�¼��
		request.setAttribute("userType", userType);
		return mapping.findForward("success");

	}

	// ��ҵ��Ϣ¼��
	private ActionForward jyxxlr(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		CommanForm actionForm = (CommanForm) form;
		HttpSession session = request.getSession();
		String userName = session.getAttribute("userName").toString();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		String doType = request.getParameter("doType");
		String xydm = request.getParameter("xydm");
		String zydm = request.getParameter("zydm");
		String bjdm = request.getParameter("bjdm");
		String nj = request.getParameter("nj");
		String xh = request.getParameter("xh");
		String nd = request.getParameter("nd");
		String xm = request.getParameter("xm");
		String sfjy = request.getParameter("sfjy");
		String sfqy = request.getParameter("sfqy");
		String pkStr = request.getParameter("pkStr");
		String pk = request.getParameter("pk");
		String edit = request.getParameter("edit");
		String sql = "";
		String yhm = "";
		String fdy = "";
		boolean flag = false;
		StringBuffer sb = new StringBuffer(" where 1=1 ");
		if ("xy".equals(userType)) {
			xydm = userDep;
			actionForm.setXydm(userDep);
		}
		if (!Base.isNull(xydm)) {
			sb.append(" and xydm='");
			sb.append(xydm);
			sb.append("'");
		}
		if (!Base.isNull(zydm)) {
			sb.append(" and zydm='");
			sb.append(zydm);
			sb.append("'");
		}
		if (!Base.isNull(bjdm)) {
			sb.append(" and bjdm='");
			sb.append(bjdm);
			sb.append("'");
		}
		if (!Base.isNull(nj)) {
			sb.append(" and nj='");
			sb.append(nj);
			sb.append("'");
		}
		if (!Base.isNull(nd)) {
			sb.append(" and bynd='");
			sb.append(nd);
			sb.append("'");
		}
		if (!Base.isNull(xh)) {
			sb.append(" and xh='");
			sb.append(xh);
			sb.append("'");
		}
		if (!Base.isNull(xm)) {
			sb.append(" and xm like '%");
			sb.append(xm);
			sb.append("%'");
		}
		if (!Base.isNull(sfjy)) {
			sb.append(" and sfjy='");
			sb.append(sfjy);
			sb.append("'");
		}

		if (!Base.isNull(sfqy)) {
			if ("��".equals(sfqy)) {
				sb.append(" and (sfqy ='��' or sfqy is null)");
			} else {
				sb.append(" and sfqy='");
				sb.append(sfqy);
				sb.append("'");
			}
		}

		if (Fdypd.isFdy(userName)) {
			if (Fdypd.isFdy(userName)) {
				sb
						.append(" and exists (select 1 from fdybjb b where zgh like '"
								+ userName + "' and a.bjdm = b.bjdm)");
				fdy = "yes";
			}
		}
		if ("delete".equals(doType)) {
			sql = "delete from jygl_xsjbxxb where instr(?,'!@!'||xsxh||'!@!')>0";
			flag = dao.runUpdate(sql, new String[] { pkStr });
			doType = "query";
			request.setAttribute("result", flag);
		}
		if ("save".equals(doType)) {
			String dwmc = request.getParameter("dwmc");
			String dwdz = request.getParameter("dwdz");
			String zydk = request.getParameter("zydk");
			String qybz = request.getParameter("qybz");
			String lxr = request.getParameter("lxr");
			String lxdh = request.getParameter("lxdh");
			String jyqy = request.getParameter("jyqy");
			String syshi = request.getParameter("syshi");
			String syxian = request.getParameter("syxian");
			sql = "delete from jygl_xsjyxx where xh=?";
			flag = dao.runUpdate(sql, new String[] { xh });
			if (flag) {
				nd = Base.isNull(nd) ? Base.currNd : nd;
				sql = "select yhm from yhb where zdm in(select zdm from yhzb where zmc like '%��ҵ��%')";
				yhm = dao.getOneRs(sql, new String[] {}, "yhm");
				String[] vals = null;
				if (!Base.isNull(yhm) || "admin".equals(userType)) {
					sql = "insert into jygl_xsjyxx (xh,nd,dwmc,dwdz,zydk,sfjy,qybz,sfqy,jyqy,lxr,lxdh,syshi,syxian) values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
					vals = new String[] { xh, nd, dwmc, dwdz, zydk, sfjy, qybz,
							sfqy, jyqy, lxr, lxdh, syshi, syxian };
				} else {
					sql = "insert into jygl_xsjyxx (xh,nd,dwmc,dwdz,zydk,sfjy,qybz) values(?,?,?,?,?,?,?)";
					vals = new String[] { xh, nd, dwmc, dwdz, zydk, sfjy, qybz };
				}
				flag = dao.runUpdate(sql, vals);

			}
			doType = "update";
			request.setAttribute("result", flag);
		}
		if ("update".equals(doType) || "view".equals(doType)) {
			sql = "select * from view_xsjyxx where xh=?";
			HashMap<String, String> map = dao.getMapNotOut(sql,
					new String[] { pk });
			if (!"stu".equals(userType)) {
				request.setAttribute("edit", "yes");
			} else {
				request.setAttribute("edit", edit);
			}
			sql = "select byqxdm dm,byqx mc from dmk_byqx";
			request.setAttribute("byqxList", dao.getListNotOut(sql,
					new String[] {}));
			sql = "select sydqdm dm,sydq mc from dmk_sydq";
			request.setAttribute("sydqList", dao.getListNotOut(sql,
					new String[] {}));
			request.setAttribute("rs", map);
			request.setAttribute("lx", doType);
			StuInfoDAO stuDao = new StuInfoDAO();
			request.setAttribute("ssList", stuDao.getSsList());
			request.setAttribute("syshiList", stuDao
					.getShiList(map.get("jyqy")).get("shiList"));
			request.setAttribute("syxianList", stuDao.getShiList(
					map.get("syshi")).get("xianList"));
			return mapping.findForward("operationOne");
		}

		if ("query".equals(doType)) {
			sql = "select * from (select a.*,nvl(nd,'" + nd
					+ "') bynd from view_xsjyxx a) a " + sb;
			List<HashMap<String, String>> rs = dao.getListNotOut(sql,
					new String[] {});
			String[] en = new String[] { "pk", "xh", "xm", "bynd", "nj",
					"xymc", "zymc", "bjmc", "dwmc", "lxdh", "sfjy", "sfqy" };
			String[] cn = new String[] { "pk", "ѧ��", "����", "���", "�꼶", "ѧԺ����",
					"רҵ����", "�༶����", "��λ����", "��λ��ϵ�绰", "�Ƿ��ҵ", "�Ƿ�ǩԼ" };
			List topTr = dao.arrayToList(en, cn);
			request.setAttribute("topTr", topTr);
			request.setAttribute("rs", rs);
			request.setAttribute("rsNum", rs.size());

		}
		if (nd == null) {
			actionForm.setNd(Base.currNd);
		}
		request.setAttribute("realTable", "jygl_xsjbxxb");
		request.setAttribute("tableName", "view_xsjyxx");
		request.setAttribute("userName ", userName);
		request.setAttribute("ndList", dao.getNdList());
		request.setAttribute("njList", Base.getNjList());// �����꼶�б�
		request.setAttribute("xyList", Base.getXyList());// ����ѧԺ�б�
		request.setAttribute("zyList", dao.getZyList(actionForm.getXydm()));// ����רҵ�б�
		request.setAttribute("bjList", dao.getBjList(actionForm.getXydm(),
				actionForm.getZydm(), actionForm.getNj()));// ���Ͱ༶�б�
		if ("yes".equals(fdy)) {
			request.setAttribute("bjList", Fdypd.getFdybjList(userName));
			request.setAttribute("zyList", Fdypd.getFdyZyList(userName));
			request.setAttribute("xyList", Fdypd.getFdyXyList(userName));
			request.setAttribute("userName ", userName);
			request.setAttribute("fdy", fdy);

		}
		return mapping.findForward("success");
	}

	// ��ҵ��Ϣ¼��
	private ActionForward jyxxcx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		CommanForm actionForm = (CommanForm) form;
		HttpSession session = request.getSession();
		// String userName = session.getAttribute("userName").toString();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		String doType = request.getParameter("doType");
		String xydm = request.getParameter("xydm");
		String zydm = request.getParameter("zydm");
		String bjdm = request.getParameter("bjdm");
		String nj = request.getParameter("nj");
		String xh = request.getParameter("xh");
		String nd = request.getParameter("nd");
		String xm = request.getParameter("xm");
		String sfjy = request.getParameter("sfjy");
		String sfqy = request.getParameter("sfqy");
		String sql = "";
		StringBuffer sb = new StringBuffer(" where 1=1 ");
		if ("xy".equals(userType)) {
			xydm = userDep;
			actionForm.setXydm(userDep);
		}
		if (!Base.isNull(xydm)) {
			sb.append(" and xydm='");
			sb.append(xydm);
			sb.append("'");
		}
		if (!Base.isNull(zydm)) {
			sb.append(" and zydm='");
			sb.append(zydm);
			sb.append("'");
		}
		if (!Base.isNull(bjdm)) {
			sb.append(" and bjdm='");
			sb.append(bjdm);
			sb.append("'");
		}
		if (!Base.isNull(nj)) {
			sb.append(" and nj='");
			sb.append(nj);
			sb.append("'");
		}
		if (!Base.isNull(nd)) {
			sb.append(" and bynd='");
			sb.append(nd);
			sb.append("'");
		}
		if (!Base.isNull(xh)) {
			sb.append(" and xh='");
			sb.append(xh);
			sb.append("'");
		}
		if (!Base.isNull(xm)) {
			sb.append(" and xm like '%");
			sb.append(xm);
			sb.append("%'");
		}
		if (!Base.isNull(sfjy)) {
			sb.append(" and sfjy='");
			sb.append(sfjy);
			sb.append("'");
		}
		if (!Base.isNull(sfqy)) {
			sb.append(" and sfqy='");
			sb.append(sfqy);
			sb.append("'");
		}
		if ("query".equals(doType)) {
			sql = "select * from (select a.*,nvl(nd,'" + nd
					+ "') bynd from view_xsjyxx a) " + sb;
			List<HashMap<String, String>> rs = dao.getListNotOut(sql,
					new String[] {});
			String[] en = new String[] { "pk", "xh", "xm", "xymc", "zymc",
					"bjmc", "dwmc", "lxdh", "sfjy", "sfqy" };
			String[] cn = new String[] { "pk", "ѧ��", "����", "ѧԺ����", "רҵ����",
					"�༶����", "��λ����", "��λ��ϵ�绰", "�Ƿ��ҵ", "�Ƿ�ǩԼ" };
			List topTr = dao.arrayToList(en, cn);
			request.setAttribute("topTr", topTr);
			request.setAttribute("rs", rs);
			request.setAttribute("rsNum", rs.size());
		}
		if (nd == null) {
			actionForm.setNd(Base.currNd);
		}
		request.setAttribute("realTable", "jygl_xsjyxx");
		request.setAttribute("tableName", "view_xsjyxx");
		request.setAttribute("ndList", dao.getNdList());
		request.setAttribute("njList", Base.getNjList());// �����꼶�б�
		request.setAttribute("xyList", Base.getXyList());// ����ѧԺ�б�
		request.setAttribute("zyList", dao.getZyList(actionForm.getXydm()));// ����רҵ�б�
		request.setAttribute("bjList", dao.getBjList(actionForm.getXydm(),
				actionForm.getZydm(), actionForm.getNj()));// ���Ͱ༶�б�
		return mapping.findForward("success");
	}

	// ��ҵ��Ϣͳ��
	private ActionForward jyxxtj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		CommanForm actionForm = (CommanForm) form;
		HttpSession session = request.getSession();
		// String userName = session.getAttribute("userName").toString();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		String doType = request.getParameter("doType");
		String xydm = request.getParameter("xydm");
		String zydm = request.getParameter("zydm");
		String bjdm = request.getParameter("bjdm");
		String nj = request.getParameter("nj");
		String nd = request.getParameter("nd");
		String lb = request.getParameter("lb");
		String sql = "";
		StringBuffer sb = new StringBuffer(" where 1=1 ");
		String[] en = null;
		String[] cn = null;
		String tjlb = "";
		if ("xy".equals(userType)) {
			xydm = userDep;
			actionForm.setXydm(userDep);
		}
		if (!Base.isNull(xydm)) {
			sb.append(" and xydm='");
			sb.append(xydm);
			sb.append("'");
		}
		if (!Base.isNull(zydm)) {
			sb.append(" and zydm='");
			sb.append(zydm);
			sb.append("'");
		}
		if (!Base.isNull(bjdm)) {
			sb.append(" and bjdm='");
			sb.append(bjdm);
			sb.append("'");
		}
		if (!Base.isNull(nj)) {
			sb.append(" and nj='");
			sb.append(nj);
			sb.append("'");
		}
		if (!Base.isNull(nd)) {
			sb.append(" and nd='");
			sb.append(nd);
			sb.append("'");
		}

		if ("tj".equals(doType) || "tjdc".equals(doType)) {
			if ("xydm".equals(lb)) {
				en = new String[] { "xymc", "zrs", "jyrs", "jyl", "qyrs", "qyl" };
				cn = new String[] { "ѧԺ����", "������", "��ҵ����", "��ҵ��", "ǩԼ����", "ǩԼ��" };
				sql = "select a.xymc,zrs,jyrs,round(jyrs*10000/zrs)/100||'%' jyl,nvl(b.qyrs,0)qyrs,round(nvl(b.qyrs,0)*10000/zrs)/100||'%' "
						+ "qyl from (select a.*,nvl(b.jyrs,0)jyrs from (select count(xh) zrs,xydm,xymc from view_xsjyxx "
						+ sb
						+ " group by xydm,xymc) a left join (select count(xh) "
						+ "jyrs,xydm from view_xsjyxx "
						+ sb
						+ " and sfjy='��' group by xydm) b on a.xydm=b.xydm) a left join (select count(xh) qyrs,xydm from "
						+ "view_xsjyxx "
						+ sb
						+ " and sfqy='��' group by xydm) b on a.xydm=b.xydm";
			} else if ("zydm".equals(lb)) {
				en = new String[] { "xymc", "zymc", "zrs", "jyrs", "jyl",
						"qyrs", "qyl" };
				cn = new String[] { "ѧԺ����", "רҵ����", "������", "��ҵ����", "��ҵ��",
						"ǩԼ����", "ǩԼ��" };
				sql = "select a.xymc,a.zymc,zrs,jyrs,round(jyrs*10000/zrs)/100||'%' jyl,nvl(b.qyrs,0) qyrs,round(nvl(b.qyrs,0)*10000/zrs)/100||'%' "
						+ "qyl from (select a.*,nvl(b.jyrs,0)jyrs from (select count(xh) zrs,xymc,zydm,zymc from view_xsjyxx "
						+ sb
						+ " group by xydm,xymc,zydm,zymc) a left join (select count(xh) "
						+ "jyrs,zydm from view_xsjyxx "
						+ sb
						+ " and sfjy='��' group by xydm,zydm) b on a.zydm=b.zydm) a left join (select count(xh) qyrs,zydm from "
						+ "view_xsjyxx  "
						+ sb
						+ " and sfqy='��' group by xydm,zydm) b on a.zydm=b.zydm";
			} else if ("bjdm".equals(lb)) {
				en = new String[] { "xymc", "zymc", "bjmc", "zrs", "jyrs",
						"jyl", "qyrs", "qyl" };
				cn = new String[] { "ѧԺ����", "רҵ����", "�༶����", "������", "��ҵ����",
						"��ҵ��", "ǩԼ����", "ǩԼ��" };
				sql = "select a.xymc,a.zymc,a.bjmc,zrs,jyrs,round(jyrs*10000/zrs)/100||'%' jyl,nvl(b.qyrs,0)qyrs,round(nvl(b.qyrs,0)*10000/zrs)/100||'%' "
						+ "qyl from (select a.*,nvl(b.jyrs,0)jyrs from (select count(xh) zrs,xymc,zymc,bjdm,bjmc from view_xsjyxx "
						+ sb
						+ " group by xydm,xymc,zydm,zymc,bjdm,bjmc) a left join (select count(xh) "
						+ "jyrs,zydm,bjdm from view_xsjyxx "
						+ sb
						+ " and sfjy='��' group by xydm,zydm,bjdm) b on a.bjdm=b.bjdm) a left join (select count(xh) qyrs,zydm,bjdm from "
						+ "view_xsjyxx  "
						+ sb
						+ " and sfqy='��' group by xydm,zydm,bjdm) b on a.bjdm=b.bjdm";
			} else {
				if (!Base.isNull(bjdm)) {
					tjlb = "bjdm";
				} else if (!Base.isNull(zydm)) {
					tjlb = "zydm";
				} else {
					tjlb = "xydm";
				}
				if ("jyqy".equals(lb)) {
					if ("bjdm".equals(tjlb)) {
						en = new String[] { "xymc", "zymc", "bjmc", "jyqy",
								"zrs", "jyrs", "jyl", "qyrs", "qyl" };
						cn = new String[] { "ѧԺ����", "רҵ����", "�༶����", "��ҵ����",
								"������", "��ҵ����", "��ҵ��", "ǩԼ����", "ǩԼ��" };
						sql = "select a.xymc,a.zymc,a.bjmc,a.jyqymc,zrs,jyrs,round(jyrs*10000/zrs)/100||'%' jyl,nvl(b.qyrs,0)qyrs,round(nvl(b.qyrs,0)*10000/zrs)/100||'%' "
								+ "qyl from (select a.*,nvl(b.jyrs,0)jyrs from (select count(xh) zrs,xymc,zymc,bjdm,bjmc,jyqy,jyqymc from view_xsjyxx "
								+ sb
								+ " group by xydm,xymc,zydm,zymc,bjdm,bjmc,jyqy,jyqymc) a left join (select count(xh) "
								+ "jyrs,zydm,bjdm,jyqy from view_xsjyxx "
								+ sb
								+ " and sfjy='��' group by xydm,zydm,bjdm,jyqy) b on a.bjdm=b.bjdm and a.jyqy=b.jyqy)  a left join (select count(xh) qyrs,zydm,bjdm,jyqy from "
								+ "view_xsjyxx  "
								+ sb
								+ " and sfqy='��' group by xydm,zydm,bjdm,jyqy) b on a.bjdm=b.bjdm and a.jyqy=b.jyqy ";
					} else if ("zydm".equals(tjlb)) {
						en = new String[] { "xymc", "zymc", "jyqy", "zrs",
								"jyrs", "jyl", "qyrs", "qyl" };
						cn = new String[] { "ѧԺ����", "רҵ����", "��ҵ����", "������",
								"��ҵ����", "��ҵ��", "ǩԼ����", "ǩԼ��" };
						sql = "select a.xymc,a.zymc,a.jyqymc,zrs,jyrs,round(jyrs*10000/zrs)/100||'%' jyl,nvl(b.qyrs,0)qyrs,round(nvl(b.qyrs,0)*10000/zrs)/100||'%' "
								+ "qyl from (select a.*,nvl(b.jyrs,0)jyrs from (select count(xh) zrs,xymc,zydm,zymc,jyqy,jyqymc from view_xsjyxx "
								+ sb
								+ " group by xydm,xymc,zydm,zymc,jyqy,jyqymc) a left join (select count(xh) "
								+ "jyrs,zydm,jyqy from view_xsjyxx "
								+ sb
								+ " and sfjy='��' group by xydm,zydm,jyqy) b on a.zydm=b.zydm and a.jyqy=b.jyqy) a left join (select count(xh) qyrs,zydm,jyqy from "
								+ "view_xsjyxx  "
								+ sb
								+ " and sfqy='��' group by xydm,zydm,jyqy) b on a.zydm=b.zydm and a.jyqy=b.jyqy ";
					} else {
						en = new String[] { "xymc", "jyqy", "zrs", "jyrs",
								"jyl", "qyrs", "qyl" };
						cn = new String[] { "ѧԺ����", "��ҵ����", "������", "��ҵ����",
								"��ҵ��", "ǩԼ����", "ǩԼ��" };
						sql = "select a.xymc,a.jyqymc,zrs,jyrs,round(jyrs*10000/zrs)/100||'%' jyl,nvl(b.qyrs,0)qyrs,round(nvl(b.qyrs,0)*10000/zrs)/100||'%' "
								+ "qyl from (select a.*,nvl(b.jyrs,0)jyrs from (select count(xh) zrs,xydm,xymc,jyqy,jyqymc from view_xsjyxx "
								+ sb
								+ " group by xydm,xymc,jyqy,jyqymc) a left join (select count(xh) "
								+ "jyrs,xydm,jyqy from view_xsjyxx "
								+ sb
								+ " and sfjy='��' group by xydm,jyqy) b on a.xydm=b.xydm and a.jyqy=b.jyqy) a left join (select count(xh) qyrs,xydm,jyqy from "
								+ "view_xsjyxx  "
								+ sb
								+ " and sfqy='��' group by xydm,jyqy) b on a.xydm=b.xydm  and a.jyqy=b.jyqy ";
					}
				} else if ("sydqdm".equals(lb)) {
					if ("bjdm".equals(tjlb)) {
						en = new String[] { "xymc", "zymc", "bjmc", "sydq",
								"zrs", "jyrs", "jyl", "qyrs", "qyl" };
						cn = new String[] { "ѧԺ����", "רҵ����", "�༶����", "��Դ����",
								"������", "��ҵ����", "��ҵ��", "ǩԼ����", "ǩԼ��" };
						sql = "select a.xymc,a.zymc,a.bjmc,a.sydqmc,zrs,jyrs,round(jyrs*10000/zrs)/100||'%' jyl,nvl(b.qyrs,0)qyrs,round(nvl(b.qyrs,0)*10000/zrs)/100||'%' "
								+ "qyl from (select a.*,nvl(b.jyrs,0)jyrs from (select count(xh) zrs,xymc,zymc,bjdm,bjmc,sydqdm,sydqmc from view_xsjyxx "
								+ sb
								+ " group by xydm,xymc,zydm,zymc,bjdm,bjmc,sydqdm,sydqmc) a left join (select count(xh) "
								+ "jyrs,zydm,bjdm,sydqdm from view_xsjyxx "
								+ sb
								+ " and sfjy='��' group by xydm,zydm,bjdm,sydqdm) b on a.bjdm=b.bjdm and a.sydqdm=b.sydqdm) a left join (select count(xh) qyrs,zydm,bjdm,sydqdm from "
								+ "view_xsjyxx  "
								+ sb
								+ " and sfqy='��' group by xydm,zydm,bjdm,sydqdm) b on a.bjdm=b.bjdm  and a.sydqdm=b.sydqdm ";
					} else if ("zydm".equals(tjlb)) {
						en = new String[] { "xymc", "zymc", "sydq", "zrs",
								"jyrs", "jyl", "qyrs", "qyl" };
						cn = new String[] { "ѧԺ����", "רҵ����", "��Դ����", "������",
								"��ҵ����", "��ҵ��", "ǩԼ����", "ǩԼ��" };
						sql = "select a.xymc,a.zymc,a.sydqmc,zrs,jyrs,round(jyrs*10000/zrs)/100||'%' jyl,nvl(b.qyrs,0)qyrs,round(nvl(b.qyrs,0)*10000/zrs)/100||'%' "
								+ "qyl from (select a.*,nvl(b.jyrs,0)jyrs from (select count(xh) zrs,xymc,zydm,zymc,sydqdm,sydqmc from view_xsjyxx "
								+ sb
								+ " group by xydm,xymc,zydm,zymc,sydqdm,sydqmc) a left join(select count(xh) "
								+ "jyrs,zydm,sydqdm from view_xsjyxx "
								+ sb
								+ " and sfjy='��' group by xydm,zydm,sydqdm) b on a.zydm=b.zydm and a.sydqdm=b.sydqdm) a left join (select count(xh) qyrs,zydm,sydqdm from "
								+ "view_xsjyxx  "
								+ sb
								+ " and sfqy='��' group by xydm,zydm,sydqdm) b on a.zydm=b.zydm and a.sydqdm=b.sydqdm ";
					} else {
						en = new String[] { "xymc", "sydq", "zrs", "jyrs",
								"jyl", "qyrs", "qyl" };
						cn = new String[] { "ѧԺ����", "��Դ����", "������", "��ҵ����",
								"��ҵ��", "ǩԼ����", "ǩԼ��" };
						sql = "select a.xymc,a.sydqmc,zrs,jyrs,round(jyrs*10000/zrs)/100||'%' jyl,nvl(b.qyrs,0)qyrs,round(nvl(b.qyrs,0)*10000/zrs)/100||'%' "
								+ "qyl from (select a.*,nvl(b.jyrs,0)jyrs from (select count(xh) zrs,xydm,xymc,sydqdm,sydqmc from view_xsjyxx "
								+ sb
								+ " group by xydm,xymc,sydqdm,sydqmc) a left join (select count(xh) "
								+ "jyrs,xydm,sydqdm from view_xsjyxx "
								+ sb
								+ " and sfjy='��' group by xydm,sydqdm) b on a.xydm=b.xydm and a.sydqdm=b.sydqdm) a left join (select count(xh) qyrs,xydm,sydqdm from "
								+ "view_xsjyxx  "
								+ sb
								+ " and sfqy='��' group by xydm,sydqdm) b on a.xydm=b.xydm and a.sydqdm=b.sydqdm";
					}
				} else if ("qybz".equals(lb)) {
					if ("bjdm".equals(tjlb)) {
						en = new String[] { "xymc", "zymc", "bjmc", "qybz",
								"zrs", "jyrs", "jyl", "qyrs", "qyl" };
						cn = new String[] { "ѧԺ����", "רҵ����", "�༶����", "ǩԼ��־",
								"������", "��ҵ����", "��ҵ��", "ǩԼ����", "ǩԼ��" };
						sql = "select a.xymc,a.zymc,a.bjmc,a.qybzmc,zrs,jyrs,round(jyrs*10000/zrs)/100||'%' jyl,nvl(b.qyrs,0)qyrs,round(nvl(b.qyrs,0)*10000/zrs)/100||'%' "
								+ "qyl from (select a.*,nvl(b.jyrs,0)jyrs from (select count(xh) zrs,xymc,zymc,bjdm,bjmc,qybz,qybzmc from view_xsjyxx "
								+ sb
								+ " group by xydm,xymc,zydm,zymc,bjdm,bjmc,qybz,qybzmc) a left join (select count(xh) "
								+ "jyrs,zydm,bjdm,qybz from view_xsjyxx "
								+ sb
								+ " and sfjy='��' group by xydm,zydm,bjdm,qybz) b on a.bjdm=b.bjdm and a.qybz=b.qybz) a left join (select count(xh) qyrs,zydm,bjdm,qybz from "
								+ "view_xsjyxx  "
								+ sb
								+ " and sfqy='��' group by xydm,zydm,bjdm,qybz) b on a.bjdm=b.bjdm  and a.qybz=b.qybz ";
					} else if ("zydm".equals(tjlb)) {
						en = new String[] { "xymc", "zymc", "qybz", "zrs",
								"jyrs", "jyl", "qyrs", "qyl" };
						cn = new String[] { "ѧԺ����", "רҵ����", "ǩԼ��־", "������",
								"��ҵ����", "��ҵ��", "ǩԼ����", "ǩԼ��" };
						sql = "select a.xymc,a.zymc,a.qybzmc,zrs,jyrs,round(jyrs*10000/zrs)/100||'%' jyl,nvl(b.qyrs,0)qyrs,round(nvl(b.qyrs,0)*10000/zrs)/100||'%' "
								+ "qyl from (select a.*,nvl(b.jyrs,0)jyrs from (select count(xh) zrs,xymc,zydm,zymc,qybz,qybzmc from view_xsjyxx "
								+ sb
								+ " group by xydm,xymc,zydm,zymc,qybz,qybzmc) a left join (select count(xh) "
								+ "jyrs,zydm,qybz from view_xsjyxx "
								+ sb
								+ " and sfjy='��' group by xydm,zydm,qybz) b on a.zydm=b.zydm and a.qybz=b.qybz) a left join (select count(xh) qyrs,zydm,qybz from "
								+ "view_xsjyxx  "
								+ sb
								+ " and sfqy='��' group by xydm,zydm,qybz) b on a.zydm=b.zydm and a.qybz=b.qybz ";
					} else {
						en = new String[] { "xymc", "qybz", "zrs", "jyrs",
								"jyl", "qyrs", "qyl" };
						cn = new String[] { "ѧԺ����", "ǩԼ��־", "������", "��ҵ����",
								"��ҵ��", "ǩԼ����", "ǩԼ��" };
						sql = "select a.xymc,a.qybzmc,zrs,jyrs,round(jyrs*10000/zrs)/100||'%' jyl,nvl(b.qyrs,0)qyrs,round(nvl(b.qyrs,0)*10000/zrs)/100||'%' "
								+ "qyl from (select a.*,nvl(b.jyrs,0)jyrs from (select count(xh) zrs,xydm,xymc,qybz,qybzmc from view_xsjyxx "
								+ sb
								+ " group by xydm,xymc,qybz,qybzmc) a left join (select count(xh) "
								+ "jyrs,xydm,qybz from view_xsjyxx "
								+ sb
								+ " and sfjy='��' group by xydm,qybz) b on a.xydm=b.xydm and a.qybz=b.qybz) a left join (select count(xh) qyrs,xydm,qybz from "
								+ "view_xsjyxx  "
								+ sb
								+ " and sfqy='��' group by xydm,qybz) b on a.xydm=b.xydm  and a.qybz=b.qybz ";
					}
				} else if ("xscc".equals(lb)) {
					if ("bjdm".equals(tjlb)) {
						en = new String[] { "xymc", "zymc", "bjmc", "xscc",
								"zrs", "jyrs", "jyl", "qyrs", "qyl" };
						cn = new String[] { "ѧԺ����", "רҵ����", "�༶����", "ѧ�����",
								"������", "��ҵ����", "��ҵ��", "ǩԼ����", "ǩԼ��" };
						sql = "select a.xymc,a.zymc,a.bjmc,a.xsccmc,zrs,jyrs,round(jyrs*10000/zrs)/100||'%' jyl,nvl(b.qyrs,0)qyrs,round(nvl(b.qyrs,0)*10000/zrs)/100||'%' "
								+ "qyl from (select a.*,nvl(b.jyrs,0)jyrs from (select count(xh) zrs,xymc,zymc,bjdm,bjmc,xscc,xsccmc from view_xsjyxx "
								+ sb
								+ " group by xydm,xymc,zydm,zymc,bjdm,bjmc,xscc,xsccmc) a left join (select count(xh) "
								+ "jyrs,zydm,bjdm,xscc from view_xsjyxx "
								+ sb
								+ " and sfjy='��' group by xydm,zydm,bjdm,xscc) b on a.bjdm=b.bjdm and a.xscc=b.xscc) a left join (select count(xh) qyrs,zydm,bjdm,xscc from "
								+ "view_xsjyxx  "
								+ sb
								+ " and sfqy='��' group by xydm,zydm,bjdm,xscc) b on a.bjdm=b.bjdm  and a.xscc=b.xscc ";
					} else if ("zydm".equals(tjlb)) {
						en = new String[] { "xymc", "zymc", "xscc", "zrs",
								"jyrs", "jyl", "qyrs", "qyl" };
						cn = new String[] { "ѧԺ����", "רҵ����", "ѧ�����", "������",
								"��ҵ����", "��ҵ��", "ǩԼ����", "ǩԼ��" };
						sql = "select a.xymc,a.zymc,a.xsccmc,zrs,jyrs,round(jyrs*10000/zrs)/100||'%' jyl,nvl(b.qyrs,0)qyrs,round(nvl(b.qyrs,0)*10000/zrs)/100||'%' "
								+ "qyl from (select a.*,nvl(b.jyrs,0)jyrs from (select count(xh) zrs,xymc,zydm,zymc,xscc,xsccmc from view_xsjyxx "
								+ sb
								+ " group by xydm,xymc,zydm,zymc,xscc,xsccmc) a left join (select count(xh) "
								+ "jyrs,zydm,xscc from view_xsjyxx "
								+ sb
								+ " and sfjy='��' group by xydm,zydm,xscc) b on a.zydm=b.zydm and a.xscc=b.xscc) a left join (select count(xh) qyrs,zydm,xscc from "
								+ "view_xsjyxx  "
								+ sb
								+ " and sfqy='��' group by xydm,zydm,xscc) b on a.zydm=b.zydm and a.xscc=b.xscc ";
					} else {
						en = new String[] { "xymc", "xscc", "zrs", "jyrs",
								"jyl", "qyrs", "qyl" };
						cn = new String[] { "ѧԺ����", "ѧ�����", "������", "��ҵ����",
								"��ҵ��", "ǩԼ����", "ǩԼ��" };
						sql = "select a.xymc,a.xsccmc,zrs,jyrs,round(jyrs*10000/zrs)/100||'%' jyl,nvl(b.qyrs,0)qyrs,round(nvl(b.qyrs,0)*10000/zrs)/100||'%' "
								+ "qyl from (select a.*,nvl(b.jyrs,0)jyrs from (select count(xh) zrs,xydm,xymc,xscc,xsccmc from view_xsjyxx "
								+ sb
								+ " group by xydm,xymc,xscc,xsccmc) a left join (select count(xh) "
								+ "jyrs,xydm,xscc from view_xsjyxx "
								+ sb
								+ " and sfjy='��' group by xydm,xscc) b on a.xydm=b.xydm and a.xscc=b.xscc) a left join (select count(xh) qyrs,xydm,xscc from "
								+ "view_xsjyxx  "
								+ sb
								+ " and sfqy='��' group by xydm,xscc) b on a.xydm=b.xydm and a.xscc=b.xscc";
					}
				}
			}
			Vector<String[]> rs = dao.rsToVatorNotOut(sql, new String[] {});

			List topTr = dao.arrayToList(en, cn);
			request.setAttribute("topTr", topTr);
			if ("tjdc".equals(doType)) {
				response.reset();
				response.setContentType("application/vnd.ms-excel");
				ExcelMB ex = new ExcelMB();
				WritableWorkbook wwb = Workbook.createWorkbook(response
						.getOutputStream());
				WritableSheet ws = wwb.createSheet("��ҵ��ǩԼ��ͳ�Ʊ�", 0);
				WritableCellFormat wcf2 = new WritableCellFormat();
				wcf2 = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false,
						Alignment.CENTRE, VerticalAlignment.CENTRE, true,
						Border.ALL);
				for (int i = 0; i < cn.length; i++) {
					ws.addCell(new Label(i, 0, cn[i], wcf2));
					ex.printToOneCell_multy(ws, cn[i], i, 0, 10, true,
							Alignment.CENTRE, VerticalAlignment.CENTRE, false,
							650, Border.ALL);
				}
				for (int i = 0; i < rs.size(); i++) {
					String[] array = rs.get(i);
					for (int j = 0; j < array.length; j++) {
						ws.addCell(new Label(j, i + 1, array[j], wcf2));
					}
				}
				ExcelMethods.submitWritableWorkbookOperations(wwb);
				return mapping.findForward("");
			}
			request.setAttribute("rs", rs);
			request.setAttribute("rsNum", rs.size());
		}
		if (nd == null) {
			actionForm.setNd(Base.currNd);
		}
		request.setAttribute("realTable", "jygl_xsjyxx");
		request.setAttribute("tableName", "view_xsjyxx");
		request.setAttribute("ndList", dao.getNdList());
		request.setAttribute("njList", Base.getNjList());// �����꼶�б�
		request.setAttribute("xyList", Base.getXyList());// ����ѧԺ�б�
		request.setAttribute("zyList", dao.getZyList(actionForm.getXydm()));// ����רҵ�б�
		request.setAttribute("bjList", dao.getBjList(actionForm.getXydm(),
				actionForm.getZydm(), actionForm.getNj()));// ���Ͱ༶�б�
		return mapping.findForward("success");
	}

	public HashMap<String, String> getMapInfo(HashMap<String, String> map,
			String[] colList, String[] valList) {
		if (colList != null && valList != null
				&& colList.length == valList.length) {
			for (int i = 0; i < colList.length; i++) {
				map.put(colList[i], valList[i]);
			}
		}
		return map;
	}

	/**
	 * ��ҵ�¶�ͳ��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward ydtj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();

		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();

		DAO dao = DAO.getInstance();
		CommanForm commanForm = (CommanForm) form;
		String doType = request.getParameter("doType");
		String tjfs = request.getParameter("tjfs");
		String[] topTr = null;
		String[] colList = null;
		String sql = "";
		StringBuilder sb = new StringBuilder();
		sb.append(" where 1=1 ");

		if ("xy".equals(userType)) {
			commanForm.setXydm(userDep);
		}

		if (!Base.isNull(commanForm.getNd())) {
			sb.append(" and nd='");
			sb.append(commanForm.getNd());
			sb.append("'");
		}

		if (!Base.isNull(commanForm.getNj())) {
			sb.append(" and nj='");
			sb.append(commanForm.getNj());
			sb.append("'");
		}

		if (!Base.isNull(commanForm.getXydm())) {
			sb.append(" and xydm='");
			sb.append(commanForm.getXydm());
			sb.append("'");
		}

		if (!Base.isNull(commanForm.getZydm())) {
			sb.append(" and zydm='");
			sb.append(commanForm.getZydm());
			sb.append("'");
		}

		if (!Base.isNull(commanForm.getBjdm())) {
			sb.append(" and bjdm='");
			sb.append(commanForm.getBjdm());
			sb.append("'");
		}

		if (!Base.isNull(doType)) {
			if ("nj".equals(tjfs)) {
				sql = "select * from (select a.*,'"
						+ commanForm.getMonth()
						+ "' yf,"
						+ "case when a.zrs>0 then (trunc(a.jyrs/a.zrs,2))*100||'%' else '0%' end jyl,"
						+ " case when a.zrs>0 then (trunc(a.qyrs/a.zrs,2))*100||'%' else '0%' end qyl"
						+ " from (  select a.*,nvl(b.qyrs,0) qyrs from ( select a.* ,nvl(b.jyrs,0) jyrs from "
						+ "(select count(a.xh) zrs,a.nd,a.nj from view_xsjyxx a group by a.nj, a.nd ) a left join "
						+ "(select count(xh) jyrs,nj,nd from view_xsjyxx where sfjy = '��' and substr(lrsj, 5, 2)="
						+ commanForm.getMonth()
						+ " group by nj,nd) b"
						+ " on a.nj=b.nj and a.nd=b.nd ) a left join "
						+ "(select count(xh) qyrs,nj,nd from view_xsjyxx where sfqy = '��' and substr(lrsj, 5, 2)="
						+ commanForm.getMonth() + " group by nj,nd) b"
						+ " on a.nj=b.nj and a.nd=b.nd ) a )" + sb.toString();
				topTr = new String[] { "�꼶", "���", "��ҵ������", "�·�", "��ҵ����",
						"ǩԼ����", "��ҵ��", "ǩԼ��" };
				colList = new String[] { "nj", "nd", "zrs", "yf", "jyrs",
						"qyrs", "jyl", "qyl" };
			} else if ("xy".equals(tjfs)) {
				sql = "select * from (select a.*,'"
						+ commanForm.getMonth()
						+ "' yf,"
						+ "(select xymc from view_njxyzybj where xydm=a.xydm and rownum=1) xymc,"
						+ "case when a.zrs>0 then (trunc(a.jyrs/a.zrs,2))*100||'%' else '0%' end jyl,"
						+ " case when a.zrs>0 then (trunc(a.qyrs/a.zrs,2))*100||'%' else '0%' end qyl"
						+ " from (  select a.*,nvl(b.qyrs,0) qyrs from ( select a.* ,nvl(b.jyrs,0) jyrs from "
						+ "(select count(a.xh) zrs,a.nd,a.xydm from view_xsjyxx a group by a.xydm, a.nd ) a left join "
						+ "(select count(xh) jyrs,xydm,nd from view_xsjyxx where sfjy = '��' and substr(lrsj, 5, 2)="
						+ commanForm.getMonth()
						+ " group by xydm,nd) b"
						+ " on a.xydm=b.xydm and a.nd=b.nd ) a left join "
						+ "(select count(xh) qyrs,xydm,nd from view_xsjyxx where sfqy = '��' and substr(lrsj, 5, 2)="
						+ commanForm.getMonth() + " group by xydm,nd) b"
						+ " on a.xydm=b.xydm and a.nd=b.nd ) a )"
						+ sb.toString();
				topTr = new String[] { "ѧԺ", "���", "��ҵ������", "�·�", "��ҵ����",
						"ǩԼ����", "��ҵ��", "ǩԼ��" };
				colList = new String[] { "xymc", "nd", "zrs", "yf", "jyrs",
						"qyrs", "jyl", "qyl" };
			} else if ("zy".equals(tjfs)) {
				sql = "select * from (select a.*,'"
						+ commanForm.getMonth()
						+ "' yf,"
						+ "(select xymc from view_njxyzybj where zydm=a.zydm and rownum=1) xymc,"
						+ "(select xydm from view_njxyzybj where zydm=a.zydm and rownum=1) xydm,"
						+ "(select zymc from view_njxyzybj where zydm=a.zydm and rownum=1) zymc,"
						+ "case when a.zrs>0 then (trunc(a.jyrs/a.zrs,2))*100||'%' else '0%' end jyl,"
						+ " case when a.zrs>0 then (trunc(a.qyrs/a.zrs,2))*100||'%' else '0%' end qyl"
						+ " from (  select a.*,nvl(b.qyrs,0) qyrs from ( select a.* ,nvl(b.jyrs,0) jyrs from "
						+ "(select count(a.xh) zrs,a.nd,a.zydm from view_xsjyxx a group by a.zydm, a.nd ) a left join "
						+ "(select count(xh) jyrs,zydm,nd from view_xsjyxx where sfjy = '��' and substr(lrsj, 5, 2)="
						+ commanForm.getMonth()
						+ " group by zydm,nd) b"
						+ " on a.zydm=b.zydm and a.nd=b.nd ) a left join "
						+ "(select count(xh) qyrs,zydm,nd from view_xsjyxx where sfqy = '��' and substr(lrsj, 5, 2)="
						+ commanForm.getMonth() + " group by zydm,nd) b"
						+ " on a.zydm=b.zydm and a.nd=b.nd ) a )"
						+ sb.toString();
				topTr = new String[] { "ѧԺ", "רҵ", "���", "��ҵ������", "�·�", "��ҵ����",
						"ǩԼ����", "��ҵ��", "ǩԼ��" };
				colList = new String[] { "xymc", "zymc", "nd", "zrs", "yf",
						"jyrs", "qyrs", "jyl", "qyl" };
			} else {
				sql = "select * from (select a.*,'"
						+ commanForm.getMonth()
						+ "' yf,"
						+ "(select nj from view_njxyzybj where bjdm=a.bjdm and rownum=1) nj,"
						+ "(select bjmc from view_njxyzybj where bjdm=a.bjdm and rownum=1) bjmc,"
						+ "(select xymc from view_njxyzybj where bjdm=a.bjdm and rownum=1) xymc,"
						+ "(select xydm from view_njxyzybj where bjdm=a.bjdm and rownum=1) xydm,"
						+ "(select zymc from view_njxyzybj where bjdm=a.bjdm and rownum=1) zymc,"
						+ "case when a.zrs>0 then (trunc(a.jyrs/a.zrs,2))*100||'%' else '0%' end jyl,"
						+ " case when a.zrs>0 then (trunc(a.qyrs/a.zrs,2))*100||'%' else '0%' end qyl"
						+ " from (  select a.*,nvl(b.qyrs,0) qyrs from ( select a.* ,nvl(b.jyrs,0) jyrs from "
						+ "(select count(a.xh) zrs,a.nd,a.bjdm from view_xsjyxx a group by a.bjdm, a.nd ) a left join "
						+ "(select count(xh) jyrs,bjdm,nd from view_xsjyxx where sfjy = '��' and substr(lrsj, 5, 2)="
						+ commanForm.getMonth()
						+ " group by bjdm,nd) b"
						+ " on a.bjdm=b.bjdm and a.nd=b.nd ) a left join "
						+ "(select count(xh) qyrs,bjdm,nd from view_xsjyxx where sfqy = '��' and substr(lrsj, 5, 2)="
						+ commanForm.getMonth() + " group by bjdm,nd) b"
						+ " on a.bjdm=b.bjdm and a.nd=b.nd ) a )"
						+ sb.toString();
				topTr = new String[] { "�꼶", "ѧԺ", "רҵ", "�༶", "���", "��ҵ������",
						"�·�", "��ҵ����", "ǩԼ����", "��ҵ��", "ǩԼ��" };
				colList = new String[] { "nj", "xymc", "zymc", "bjmc", "nd",
						"zrs", "yf", "jyrs", "qyrs", "jyl", "qyl" };
			}

			List<String[]> rs = dao.rsToVator(sql, new String[] {}, colList);
			if (null != rs && "query".equals(doType)) {
				request.setAttribute("rsNum", rs.size());
				request.setAttribute("rs", rs);
			}

			if (null != rs && "expData".equals(doType)) {
				response.reset();
				response.setContentType("application/vnd.ms-excel");
				ExcelMB ex = new ExcelMB();
				WritableWorkbook wwb = Workbook.createWorkbook(response
						.getOutputStream());
				WritableSheet ws = wwb.createSheet("�¶Ⱦ�ҵͳ�Ʊ�", 0);
				WritableCellFormat wcf2 = new WritableCellFormat();
				wcf2 = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false,
						Alignment.CENTRE, VerticalAlignment.CENTRE, true,
						Border.ALL);
				for (int i = 0; i < topTr.length; i++) {
					ws.addCell(new Label(i, 0, topTr[i], wcf2));
					ex.printToOneCell_multy(ws, topTr[i], i, 0, 10, true,
							Alignment.CENTRE, VerticalAlignment.CENTRE, false,
							650, Border.ALL);
				}
				for (int i = 0; i < rs.size(); i++) {
					String[] array = rs.get(i);
					for (int j = 0; j < array.length; j++) {
						ws.addCell(new Label(j, i + 1, array[j], wcf2));
					}
				}
				ExcelMethods.submitWritableWorkbookOperations(wwb);
				return mapping.findForward("");
			}
		}

		request.setAttribute("topTr", topTr);
		request.setAttribute("monthList", getMonthList());
		request.setAttribute("path", "jyydbb.do");
		FormModleCommon.commonRequestSet(request);
		FormModleCommon.setNdXnXqList(request);
		FormModleCommon.setNjXyZyBjList(request);
		return mapping.findForward("ydtj");
	}

	/**
	 * �·��б�
	 * 
	 * @return
	 */
	public List<HashMap<String, String>> getMonthList() {
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		for (int i = 1; i < 10; i++) {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("en", "0" + i);
			list.add(map);
		}
		for (int i = 10; i < 13; i++) {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("en", String.valueOf(i));
			list.add(map);
		}

		return list;
	}
}
