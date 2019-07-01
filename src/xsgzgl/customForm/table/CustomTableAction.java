package xsgzgl.customForm.table;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.comm.SearchRsModel;
import xgxt.comm.search.SearchModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.Pages;
import xsgzgl.customForm.model.TableContentModel;
import xsgzgl.customForm.model.TableModel;
import xsgzgl.customForm.model.TablePkContentModel;
import xsgzgl.customForm.model.TableSearchContentModel;

import com.zfsoft.basic.BasicAction;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: �Զ�����_Action��
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

public class CustomTableAction extends BasicAction {

	/**
	 * ��������_�ҵ�����_����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward customTableManage(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		CustomTableForm myForm = (CustomTableForm) form;
		CustomTableService service = new CustomTableService();
		CustomTableInit init = new CustomTableInit();
		User user = getUser(request);// �û�����
		HttpSession session = request.getSession();

		// ============= ��ʼ����������ֵ ============
		List<HashMap<String, String>> gnmkList = service.getGnmkList();
		request.setAttribute("gnmkList", gnmkList);
		// =================== end ===================

		return mapping.findForward("customTableManage");
	}

	/**
	 * ��������_�ҵ�����_��ϸ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward customTableDetail(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		CustomTableForm myForm = (CustomTableForm) form;
		CustomTableService service = new CustomTableService();
		CustomTableInit init = new CustomTableInit();
		User user = getUser(request);// �û�����
		HashMap<String, String> rs = new HashMap<String, String>();

		// ============= ��ʼ����������ֵ ============
		// ID
		String id = request.getParameter("id");
		myForm.setId(id);
		// =================== end ===================

		// ============= �鿴��ά���õı� ============
		if (!Base.isNull(id)) {
			rs = service.getGnmkInfo(myForm, user);
		}
		// =================== end ===================

		request.setAttribute("rs", rs);

		return mapping.findForward("customTableDetail");
	}

	/**
	 * ��������_�ҵ�����_ά��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward customTableUpdate(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		CustomTableForm myForm = (CustomTableForm) form;
		CustomTableService service = new CustomTableService();
		CustomTableInit init = new CustomTableInit();
		User user = getUser(request);// �û�����
		HttpSession session = request.getSession();

		// ============= ��ʼ����������ֵ ============
		String gnmk_id = request.getParameter("id");
		request.setAttribute("gnmk_id", gnmk_id);
		// =================== end ===================

		// ============= �����û������ж���ת ============

		// =================== end ===================

		return mapping.findForward("customTableUpdate");
	}

	/**
	 * �Զ����_����_����1
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward customSettingStep1(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		CustomTableForm myForm = (CustomTableForm) form;
		CustomTableService service = new CustomTableService();
		CustomTableInit init = new CustomTableInit();
		User user = getUser(request);// �û�����
		HttpSession session = request.getSession();

		// ============= ��ʼ����������ֵ ============
		String gnmk_id = request.getParameter("id");
		request.setAttribute("gnmk_id", gnmk_id);
		// =================== end ===================

		// ============= �����û������ж���ת ============

		// =================== end ===================

		return mapping.findForward("customSettingStep1");
	}

	/**
	 * �Զ����_����_����2
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward customSettingStep2(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		CustomTableForm myForm = (CustomTableForm) form;
		CustomTableService service = new CustomTableService();
		CustomTableInit init = new CustomTableInit();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		String gnmk_id = request.getParameter("gnmk_id");
		request.setAttribute("gnmk_id", gnmk_id);
		// =================== end ===================

		// ============= �����û������ж���ת ============

		// =================== end ===================

		return mapping.findForward("customSettingStep2");
	}

	// =======================���·���==============================

	// ===================��ѯ================================

	/**
	 * ��ù���ģ������ѯ�б�
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getCustomGnmkList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		CustomTableForm myForm = (CustomTableForm) form;
		CustomTableService service = new CustomTableService();
		CustomTableInit init = new CustomTableInit();
		User user = getUser(request);// �û�����
		SearchRsModel rsModel = new SearchRsModel();
		RequestForm rForm = new RequestForm();

		// ============= ��ʼ����������ֵ ============
		// �������ʾ�ֶ�
		String[] otherValue = request.getParameter("otherValue")
				.split("!!@@!!");

		// ����ģ�����
		String gnmkdm = otherValue[0];
		myForm.setGnmkdm(gnmkdm);

		// ����ģ������
		String gnmkmc = otherValue[1];
		myForm.setGnmkmc(gnmkmc);

		// ���״̬
		String shzt = otherValue[2];
		myForm.setShzt(shzt);
		// =================== end ===================

		// ==================��ҳ���========================
		Pages pages = service.setPages("", request);
		myForm.setPages(pages);
		// ==================��ҳ��� end========================

		// ==================�߼���ѯ���========================
		SearchModel searchModel = service.setSearchModel(rForm, request);
		myForm.setSearchModel(searchModel);
		// ==================�߼���ѯ��� end========================

		// ==================��ʾ����========================
		List<HashMap<String, String>> topTr = service.getCustomTableTop(myForm,
				user);// ����
		ArrayList<String[]> rsArrList = service.getCustomGnmkList(myForm, user);
		String spHtml = service.getCustomGnmkHtml(rsModel, myForm, rsArrList,
				user);
		// ==================��ʾ���� end========================

		// ==================����ǰ̨ҳ��========================
		rsModel.setTopTr(topTr);
		rsModel.setRsArrList(rsArrList);
		rsModel.setSpHtml(spHtml);
		rsModel.setCheckBox("no");

		service.createRs(rsModel, pages, response);
		// ==================����ǰ̨ҳ�� end========================

		return null;
	}

	// ===================��ѯ end================================

	// ===================����ģ����ϸ ================================
	/**
	 * ���湦��ģ��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveGnmk(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		CustomTableForm myForm = (CustomTableForm) form;
		CustomTableService service = new CustomTableService();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		// ����ģ�����
		String gnmkdm = request.getParameter("gnmkdm");
		myForm.setGnmkdm(gnmkdm);

		// ����ģ������
		String gnmkmc = request.getParameter("gnmkmc");
		if (!Base.isNull(gnmkmc)) {
			gnmkmc = service.unicode2Gbk(gnmkmc);
		}
		myForm.setGnmkmc(gnmkmc);

		// ��Ӧҳ��
		String dyym = request.getParameter("dyym");
		myForm.setDyym(dyym);

		// ���״̬
		String shzt = request.getParameter("shzt");
		if (!Base.isNull(shzt)) {
			shzt = service.unicode2Gbk(shzt);
		}
		myForm.setShzt(shzt);
		
		// ����
		String tablename = request.getParameter("tablename");
		myForm.setTablename(tablename);
		// ============= end ============

		// ============= ���湦��ģ�� ============
		boolean flag = service.saveGnmk(myForm, user, request);
		String message = flag ? "����ɹ�" : "����ʧ�ܣ�����ϵ�����Ա";
		request.setAttribute("message", message);
		// ============= end ============

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}

	// ===================����ģ����ϸ end================================

	/**
	 * ����ҵ�����б�
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward showTableDetail(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		CustomTableForm myForm = (CustomTableForm) form;
		CustomTableService service = new CustomTableService();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		// ����ID
		String gnmk_id = request.getParameter("gnmk_id");
		// ============= ��ʼ����������ֵ end============

		// ==================����ǰ̨ҳ��========================
		service.createTable(myForm, gnmk_id, response);
		// ==================����ǰ̨ҳ�� end========================

		return null;
	}

	// ===================��ά��================================

	/**
	 * ����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getListBySource(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		CustomTableForm myForm = (CustomTableForm) form;
		CustomTableService service = new CustomTableService();

		// ��
		String tablename = request.getParameter("tablename");
		// ����
		String dm = request.getParameter("dm");
		// ����
		String mc = request.getParameter("mc");

		boolean flag = service.checkTableName(tablename, dm, mc, myForm);

		if (flag) {
			List<HashMap<String, String>> list = service.getListBySource(
					tablename, dm, mc, myForm);

			if (list != null && list.size() > 0) {

			} else {

				HashMap<String, String> map = new HashMap<String, String>();
				map.put("dm", "");
				map.put("mc", "");

				list.add(map);
			}
			
			response.setContentType("text/html;charset=gbk");

			response.getWriter().print(JSONArray.fromObject(list));
		} else {
			response.setContentType("text/html;charset=gbk");

			response.getWriter().print(flag);
		}

		return null;
	}

	/**
	 * �����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveTable(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		CustomTableForm myForm = (CustomTableForm) form;
		CustomTableService service = new CustomTableService();
		TableModel tableModel = new TableModel();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		String gnmk_id = request.getParameter("gnmk_id");
		tableModel.setGnmk_id(gnmk_id);

		// ID
		String[] id = request.getParameter("id").split("!!@@!!");
		tableModel.setId(id);

		// ����
		String[] title = request.getParameter("title").split("!!@@!!");
		tableModel.setTitle(title);

		// ������
		String[] row_all = request.getParameter("row_all").split("!!@@!!");
		tableModel.setRow_all(row_all);

		myForm.setTableModel(tableModel);
		// ============= end ============

		// ============= ����� ============
		boolean flag = service.saveTable(myForm, user);
		String message = flag ? "����ɹ�" : "����ʧ�ܣ�����ϵ�����Ա";
		// ============= end ============

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}

	/**
	 * ��ȡ��ID
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getTableId(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		CustomTableService service = new CustomTableService();

		List<HashMap<String, String>> list = service.getTableIdList();

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(JSONArray.fromObject(list));

		return null;
	}

	/**
	 * ���������
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveTableContent(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		CustomTableForm myForm = (CustomTableForm) form;
		CustomTableService service = new CustomTableService();
		TableContentModel tableContentModel = new TableContentModel();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		// OLD ID
		String[] old_id = request.getParameter("old_id").split("!!@@!!");
		tableContentModel.setOld_id(old_id);
		// TABLE ID
		String[] table_id = request.getParameter("table_id").split("!!@@!!");
		tableContentModel.setTable_id(table_id);
		// ��
		String[] row_num = request.getParameter("row_num").split("!!@@!!");
		tableContentModel.setRow_num(row_num);
		// ��
		String[] column_num = request.getParameter("column_num")
				.split("!!@@!!");
		tableContentModel.setColumn_num(column_num);
		// ����
		String[] content = request.getParameter("content").split("!!@@!!");
		tableContentModel.setContent(content);
		// ���
		String[] width = request.getParameter("width").split("!!@@!!");
		tableContentModel.setWidth(width);
		// �ı�������
		String[] textarea_rows = request.getParameter("textarea_rows").split(
				"!!@@!!");
		tableContentModel.setTextarea_rows(textarea_rows);
		// ��׺
		String[] postfix = request.getParameter("postfix").split("!!@@!!");
		tableContentModel.setPostfix(postfix);
		// ���ݱ�
		String[] source_table = request.getParameter("source_table").split(
				"!!@@!!");
		tableContentModel.setSource_table(source_table);
		// ����
		String[] select_dm = request.getParameter("select_dm").split("!!@@!!");
		tableContentModel.setSelect_dm(select_dm);
		// ����
		String[] select_mc = request.getParameter("select_mc").split("!!@@!!");
		tableContentModel.setSelect_mc(select_mc);
		// ��֤
		String[] checked = request.getParameter("checked").split("!!@@!!");
		tableContentModel.setChecked(checked);
		
		myForm.setTableContentModel(tableContentModel);
		// ============= end ============

		// ============= ����� ============
		boolean flag = service.saveTableContent(myForm, user);
		String message = flag ? "����ɹ�" : "����ʧ�ܣ�����ϵ�����Ա";
		// ============= end ============

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}

	// ===================����ģ������================================

	/**
	 * ��ʾ�ֶ�����Div
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward showZdxzDiv(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		CustomTableForm myForm = (CustomTableForm) form;
		CustomTableService service = new CustomTableService();

		// ============= ��ʼ����������ֵ ============
		// ����ģ��ID
		String gnmk_id = request.getParameter("gnmk_id");
		myForm.setId(gnmk_id);
		// =================== end ===================

		// ==================����ǰ̨ҳ��========================
		service.showZdxzDiv(myForm, response);
		// ==================����ǰ̨ҳ�� end========================

		return null;
	}

	/**
	 * ��ʾ�ֶ�����Div
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward showZjqrDiv(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		CustomTableForm myForm = (CustomTableForm) form;
		CustomTableService service = new CustomTableService();

		// ============= ��ʼ����������ֵ ============
		// ����ģ��ID
		String gnmk_id = request.getParameter("gnmk_id");
		myForm.setId(gnmk_id);
		// =================== end ===================

		// ==================����ǰ̨ҳ��========================
		service.showZjqrDiv(myForm, response);
		// ==================����ǰ̨ҳ�� end========================

		return null;
	}

	/**
	 * �����ѯ����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveTableSearchContent(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		CustomTableForm myForm = (CustomTableForm) form;
		CustomTableService service = new CustomTableService();
		TableSearchContentModel tableSearchContentModel = new TableSearchContentModel();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		// ����ģ��ID
		String gnmk_id = request.getParameter("gnmk_id");
		tableSearchContentModel.setGnmk_id(gnmk_id);

		// ����ID
		String[] content_id = request.getParameter("content_id")
				.split("!!@@!!");
		tableSearchContentModel.setContent_id(content_id);

		// ��ʾ˳��
		String[] xssx = request.getParameter("xssx").split("!!@@!!");
		tableSearchContentModel.setXssx(xssx);

		myForm.setTableSearchContentModel(tableSearchContentModel);
		// ============= end ============

		// ============= ����� ============
		boolean flag = service.saveTableSearchContent(myForm, user);
		String message = flag ? "����ɹ�" : "����ʧ�ܣ�����ϵ�����Ա";
		// ============= end ============

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}

	/**
	 * ������������
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveTablePkContent(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		CustomTableForm myForm = (CustomTableForm) form;
		CustomTableService service = new CustomTableService();
		TablePkContentModel tablePkContentModel = new TablePkContentModel();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		// ����ģ��ID
		String gnmk_id = request.getParameter("gnmk_id");
		tablePkContentModel.setGnmk_id(gnmk_id);

		// ����ID
		String[] pk_id = request.getParameter("pk_id").split("!!@@!!");
		tablePkContentModel.setPk_id(pk_id);

		// ��ʾ˳��
		String[] xssx = request.getParameter("xssx").split("!!@@!!");
		tablePkContentModel.setXssx(xssx);

		myForm.setTablePkContentModel(tablePkContentModel);
		// ============= end ============

		// ============= ����� ============
		boolean flag = service.saveTablePkContent(myForm, user);
		String message = flag ? "����ɹ�" : "����ʧ�ܣ�����ϵ�����Ա";
		// ============= end ============

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}

	// ===================����ģ������ end============================

}
