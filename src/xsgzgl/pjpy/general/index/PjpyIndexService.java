package xsgzgl.pjpy.general.index;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import common.Globals;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.form.User;
import xsgzgl.pjpy.general.PjpyGeneralForm;
import xsgzgl.pjpy.general.inter.PjpyIndexInterface;
import xsgzgl.pjpy.general.zhcp.PjpyZhcpDAO;
import xsgzgl.pjpy.general.zhcp.PjpyZhcpService;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ��������_��ҳ_ͨ��_Service��
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

public class PjpyIndexService extends CommService implements PjpyIndexInterface {

	PjpyIndexDAO dao = new PjpyIndexDAO();

	/**
	 * ��ʼ���Ѷ�����������
	 * 
	 * @author ΰ�����
	 * @throws IOException
	 */
	public void defaultCustomPjlc(PjpyIndexModel model, User user,
			HttpServletResponse response) throws IOException {

		response.setContentType("text/html;charset=gbk");

		// ���۵ȼ��б�
		List<HashMap<String, String>> pjdjList = dao.getPjdjList(model, user);
		// ���������б�
		List<HashMap<String, String>> pjlcList = dao.getCustomPjlcList(model,
				user);
		
		String stylePath = model.getStylePath();
		
		StringBuilder html = new StringBuilder();

		if(pjdjList!=null && pjdjList.size()>0){
			
			html.append("<div class=\"awards_process\">");
			
			html.append("<h3 class=\"awards_process_h3\">");
			html.append("����������������");
			html.append("</h3>");
			
			//---------------�_ʼ���u�� begin-------------
			HashMap<String, String> startMap = pjlcList.get(0);
			String method = startMap.get("method");// ����
			String lcdm = startMap.get("lcdm");// ���̴���
			String lcmc = startMap.get("lcmc");// ��������
			String lcdj = startMap.get("lcdj");// ���̵ȼ�
			String used = startMap.get("used");// �Ƿ�ʹ��
			String picname = startMap.get("picname");// ͼƬ��
			
			html.append("<div class=\"begin_bt_box\">");
			html.append("<button type=\"button\"  class=\"bt_blue\"");
			html.append("onclick=\"" + method + ";return false;\"");
			html.append(">");
			html.append(lcmc);
			html.append("</button>");
			html.append("</div>");
			
			pjlcList.remove(0);
			//---------------�_ʼ���u��end-------------
			
			//---------------�u������ begin-------------
			html.append("<div class=\"awards_process_inbox\">");
			html.append("<ul>");
			
			boolean the_end = false;
			
			for (int i = 1; i < pjdjList.size() - 1; i++) {
				
				String pjdj = pjdjList.get(i).get("lcdj");// �����ȼ�
				String sftj = pjdjList.get(i).get("sftj");// �Ƿ��ύ
				String kfcz = pjdjList.get(i).get("kfcz");// �ɷ����
				
				String div_class = "";
				
				if ("yes".equalsIgnoreCase(sftj)) {
					div_class = "processbox_submit processbox_submited";
				} else if ("no".equalsIgnoreCase(sftj)) {
					if ("yes".equalsIgnoreCase(kfcz)) {
						div_class = "processbox_submit processbox_submitting";
					} else {
						div_class = "processbox_submit processbox_unsubmit";
					}
				}
				
				html.append("<li>");
				html.append("<div class=\"" + div_class + "\">");
				html.append("<span>step" + i + "</span>");
				
				if (pjlcList != null && pjlcList.size() > 0) {
					
					for (int j = 0; j < pjlcList.size(); j++) {
						HashMap<String, String> map = pjlcList.get(j);
						lcdm = map.get("lcdm");// ���̴���
						lcmc = map.get("lcmc");// ��������
						lcdj = map.get("lcdj");// ���̵ȼ�
						method = map.get("method");// ����
						picname = map.get("picname");// ͼƬ��
						used = map.get("used");// �Ƿ�ʹ��
						String hjtj = map.get("sftj");// �Ƿ�ʹ��
						
						String imgage = "";
						
						if ("no".equalsIgnoreCase(sftj)
								&& "yes".equalsIgnoreCase(kfcz)) {
							imgage = picname + "_blue.png";
						} else {
							imgage = picname + "_gray.png";
						}
						
						if (lcdj.equalsIgnoreCase(pjdj)) {
							html.append("<a href=\"#\" ");
							html.append("onclick=\"");
							if ("no".equalsIgnoreCase(sftj)
									&& "yes".equalsIgnoreCase(kfcz)) {
								html.append(method);
							}
							html.append(";return false;\" ");
							html.append(">");
							html.append("<img src=\"" + stylePath + "images/"
									+ imgage + "\" ");
							html.append("wdith=\"24\" height=\"24\" />");
							html.append(lcmc);
							html.append("</a>");
						}
					}
				}
				
				html.append("<button type=\"button\"  id=\"a_"+pjdj+"\" ");
				
				if(!"processbox_submit processbox_unsubmit".equalsIgnoreCase(div_class)){
					html.append("onclick=\"checkSubmitPjlc(this);return false;\"");
				}
				if ("yes".equalsIgnoreCase(sftj)) {
					html.append("disabled=\"true\"");
				}else{
					
				}
				html.append(">");
				html.append("</button>");
				html.append("</div>");
				
				//------------��ͷbegin-------------
				if (i != pjdjList.size() - 2) {
					html.append("<div class=\"");
					if ("yes".equalsIgnoreCase(sftj)) {
						html.append("ico_list_submit ico_list_submited");
					} else if ("no".equalsIgnoreCase(sftj)) {
						if ("yes".equalsIgnoreCase(kfcz)) {
							html.append("ico_list_submit ico_list_submitting");
						} else {
							html.append("ico_list_submit ico_list_unsubmit");
						}
					}
					html.append("\">");
					html.append("</div>");
				}

				if ("yes".equalsIgnoreCase(sftj) && i == pjdjList.size() - 2) {
					the_end = true;
				}
				//------------��ͷend---------------
				
				html.append("</li>");
			}
			
			html.append("</ul>");
			html.append("</div>");
			//---------------�u������ end-------------
			
			// ---------------������������ begin-------------
			HashMap<String, String> endMap = pjlcList.get(pjlcList.size() - 1);
			method = endMap.get("method");// ����
			lcmc = endMap.get("lcmc");// ��������
			String sftj = endMap.get("sftj");// �Ƿ��ύ

			html.append("<div class=\"over_bt_box\">");
			html.append("<button  type=\"button\" "); 
			if ("no".equalsIgnoreCase(sftj) && the_end) {
				html.append("class=\"bt_blue\" ");
				html.append("onclick=\"" + method + ";return false;\"");
			} else {
				html.append("class=\"bt_blue bt_gray\" ");
				html.append("onclick=\"return false;\"");
			} 
			html.append(">");
			html.append(lcmc);
			html.append("</button>");
			html.append("</div>");
			//---------------������������end-------------
			
			html.append("</div>");
//			// ��һ�����־λ
//			boolean nextFlag = true;
//			
//			for(int i=0;i<pjdjList.size();i++){
//				
//				html.append("<table class=\"formlist\" align=\"center\" width=\"100%\">");
//				html.append("<thead>");
//				html.append("<tr>");
//				html.append("<td>");							
//				html.append("��"+(i+1)+"��");
//				html.append("</td>");
//				html.append("</tr>");
//				html.append("</thead>");
//				
//				String pjdj = pjdjList.get(i).get("lcdj");// �����ȼ�
//				String sftj = pjdjList.get(i).get("sftj");// �Ƿ��ύ
//				boolean not_start = true;// �ǿ�ʼ������
//
//				if (pjlcList != null && pjlcList.size() > 0) {
//					
//					html.append("<tbody>");
//					html.append("<tr>");
//					html.append("<td>");
//					
//					html.append("<span style=\"float:left\">");
//					for (int j = 0; j < pjlcList.size(); j++) {
//						HashMap<String, String> map = pjlcList.get(j);
//						String lcdm = map.get("lcdm");// ���̴���
//						String lcmc = map.get("lcmc");// ��������
//						String lcdj = map.get("lcdj");// ���̵ȼ�
//						String method = map.get("method");// ����
//						String used = map.get("used");// �Ƿ�ʹ��
//						
//						if(!"yes".equalsIgnoreCase(used)){
//							if(pjdj.equalsIgnoreCase(lcdj)){
//								html.append("<button  type=\"button\"  id=\"btn_" + lcdm + "\" onclick=\""+method+"\" style=\"width:130px\"");
//								//�ǿ�ʼ������
//								if (!"101".equalsIgnoreCase(lcdm)) {
//									// �������Ƿ��ύ
//									if ("yes".equalsIgnoreCase(sftj)) {
//										html.append("disabled=\"true\"");
//									} else if (!nextFlag) {
//										html.append("disabled=\"true\"");
//									}
//								}
//								html.append(">");
//								html.append(lcmc);
//								html.append("</button>");
//								pjlcList.get(j).put("used", "yes");
//								
//								if ("101".equalsIgnoreCase(lcdm)
//										|| "999".equalsIgnoreCase(lcdm)) {
//									not_start = false;
//								}
//							}
//						}
//					}
//					html.append("</span>");
//					
//					html.append("<span style=\"float:right\">");
//					
//					//�ж��Ƿ�������
//					if(not_start){
//						//�жϱ������Ƿ��ύ
//						if("no".equalsIgnoreCase(sftj)){
//							//�жϱ�����ɷ��ύ
//							if(nextFlag){
//								html.append("<a href=\"#\" id=\"a_"+pjdj+"\" onclick=\"checkSubmitPjlc(this);return false;\">");
//								html.append("<font color=\"blue\">");
//								html.append("�ύ");
//								html.append("</font>");
//								html.append("</a>");
//							}else{
//								html.append("<a href=\"#\" onclick=\"return false;\">");
//								html.append("<font color=\"#888888\">");
//								html.append("�ύ");
//								html.append("</font>");
//								html.append("</a>");
//							}
//						}else{
//							html.append("<a href=\"#\" onclick=\"return false;\">");
//							html.append("<font color=\"#888888\">");
//							html.append("���ύ");
//							html.append("</font>");
//							html.append("</a>");
//						}
//						
//						html.append("</span>");
//					}
//					
//					html.append("</td>");
//					html.append("</tr>");
//					html.append("</tbody>");
//				}
//				
//				nextFlag = "yes".equalsIgnoreCase(sftj) ? true : false;
//				
//				html.append("</table>");
//			}	
		}else{
			html.append("<table class=\"dateline\" align=\"center\" width=\"100%\">");
			html.append("<thead>");
			html.append("<tr>");
			html.append("<td>");							
			html.append("��1��");
			html.append("</td>");
			html.append("</tr>");
			html.append("</thead>");
			html.append("<tbody>");
			html.append("<tr>");
			html.append("<td>");
			html.append("<button  type=\"button\"  id=\"\" onclick=\"showLcdy();return false;\">");
			html.append("�������̶���");
			html.append("</button>");
			html.append("</td>");
			html.append("</tr>");
			html.append("</tbody>");
			html.append("</table>");
		}

		response.getWriter().print(html.toString());
	}
	
	/**
	 * ��ʼ����������
	 * 
	 * @author ΰ�����
	 * @throws IOException
	 */
	public void defaultFreePjlc(PjpyIndexModel model, User user,
			HttpServletResponse response) throws IOException {

		response.setContentType("text/html;charset=gbk");

		// ���������б�
		List<HashMap<String, String>> pjlcList = dao.getFreePjlcList(model,
				user);

		StringBuilder html = new StringBuilder();

		html.append("<table width=\"100%\" border=\"1\">");
		html.append("<thead onclick=\"\" style=\"\">");
		html.append("<tr>");
		html.append("<td bgcolor=\"#CCFFFF\">");
		html.append("���̲���");
		html.append("</td>");
		html.append("</tr>");
		html.append("</thead>");
		html.append("<tbody id=\"\">");
		html.append("<tr>");
		html.append("<td>");

		html.append("<a href=\"#\" onclick=\"addPjlc();return false;\">");
		html.append("<font color=\"blue\">");
		html.append("��������");
		html.append("</font>");
		html.append("</a>");

		html.append("&nbsp;&nbsp;&nbsp;&nbsp");

		html.append("<a href=\"#\" onclick=\"return false;\">");
		html.append("<font color=\"blue\">");
		html.append("ɾ������");
		html.append("</font>");
		html.append("</a>");

		html.append("</td>");
		html.append("</tr>");
		html.append("</tbody>");
		html.append("<thead onclick=\"\" style=\"\">");
		html.append("<tr>");
		html.append("<td bgcolor=\"#CCFFFF\">");
		html.append("��������");
		html.append("</td>");
		html.append("</tr>");
		html.append("</thead>");
		html.append("<tbody id=\"\">");
		if (pjlcList != null && pjlcList.size() > 0) {
			for (int i = 0; i < pjlcList.size(); i++) {
				HashMap<String, String> map = pjlcList.get(i);
				String lcdm = map.get("lcdm");// ���̴���
				String lcmc = map.get("lcmc");// ��������
				html.append("<tr id=\"tr_free_pjlc_" + lcdm
						+ "\" style=\"cursor:hand;\">");
				html.append("<td>");
				html.append("<span style=\"float:left\">");
				html.append(lcmc);
				html.append("</span>");

				html.append("<span style=\"float:right\">");

				html.append("<a href=\"#\" id=\"a_" + lcdm
						+ "_view\" onclick=\"addStep('" + lcdm + "','" + lcmc
						+ "');return false;\">");
				html.append("<font id=\"font_" + lcdm + "\" color=\"blue\">");
				html.append("���");
				html.append("</font>");
				html.append("</a>");

				html.append("<a href=\"#\" id=\"a_"
								+ lcdm
								+ "_none\" onclick=\"return false;\" style=\"display:none\">");
				html.append("<font id=\"font_" + lcdm
								+ "\" color=\"#888888\">");
				html.append("�����");
				html.append("</font>");
				html.append("</a>");

				html.append("</span>");

				html.append("</td>");
				html.append("</tr>");
			}
		}
		html.append("</tbody>");
		html.append("</table>");

		response.getWriter().print(html.toString());
	}

	/**
	 * ������������
	 * 
	 * @author ΰ�����
	 */
	public Boolean savePjlc(PjpyIndexModel model, User user) {

		boolean flag = false;

		try {
			// ������������
			flag = dao.savePjlcb(model, user);

			// ɾ���������̵ȼ�
			if (flag) {
				flag = dao.delPjlcdjb(model, user);
			}

			// �����������̵ȼ�
			if (flag) {
				flag = dao.savePjlcdjb(model, user);
			}
		} catch (Exception e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}

		return flag;
	}

	/**
	 * ���濪ʼ������
	 * 
	 * @author ΰ�����
	 */
	public Boolean saveStart(PjpyIndexModel model, User user) {
		
		boolean flag = false;
		
		try {
			//��������ϵͳ����
			flag=dao.delXtszb(model, user);
			if(flag){
				flag = dao.saveXtszb(model, user);
			}
			
			//ִ�г�ʼ������
			if (flag) {
				dao.initPjlcdjb(model, user);
				dao.initPjlcb(model, user);
			}
			
			//ִ���ύ����
			if(flag){
				flag = submitPjlc("1", user);
			}
		} catch (Exception e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}
		
		//����ϵͳ����
		if(flag){
			PjpyGeneralForm.setJbszModel();
			initPjpy(model, user);
		}
		
		return flag;
	}

	/**
	 * �ύ��������
	 * 
	 * @author ΰ�����
	 */
	public Boolean submitPjlc(String lcdj, User user) {

		boolean flag = false;

		try {
			//�ύ��������
			flag = dao.updatePjlcdjb(lcdj, user);
			
			if(flag){
				flag = dao.updatePjlcb(user);
			}
			// ��ʼ������ҵ��
			if (flag) {
				// ��ʼ��������ҵ��
				initThisPjlcInfo(lcdj, user);
				// ��ʼ����һ����ҵ��
				initNextPjlcInfo(lcdj, user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return flag;
	}
	
	/**
	 * ��ʼ������������Ϣ(������)
	 * 
	 * @author ΰ�����
	 */
	public void initThisPjlcInfo(String lcdj, User user) {

		// ��ñ������ȼ��б�
		List<HashMap<String, String>> pjdjList = dao.getPjlcList(lcdj, user);

		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();
		//�Ƿ���Ҫ������
		String cpz = jbszModel.getCpz();
		
		if (pjdjList != null && pjdjList.size() > 0) {
			for (int i = 0; i < pjdjList.size(); i++) {
				HashMap<String, String> map = pjdjList.get(i);
				String lcdm = map.get("lcdm");

				try {
					if ("103".equalsIgnoreCase(lcdm)) {// ����С������
						if("yes".equalsIgnoreCase(cpz)){
							dao.initCpxzRy(user);
						}
					} else if ("104".equalsIgnoreCase(lcdm)) {// �۲���Ŀά��

						PjpyZhcpDAO zhcpDAO = new PjpyZhcpDAO();

						// ����ѧ��
						String pjxn = jbszModel.getPjxn();
						// ����ѧ��
						String pjxq = jbszModel.getPjxq();
						// �������
						String pjnd = jbszModel.getPjnd();

						// �����ڵ��۲���Ŀ
						List<HashMap<String, String>> zcxmList = zhcpDAO
								.getZcxmList(pjxn, pjxq, pjnd);
						
						// �۲���չ�ֶ�
						List<HashMap<String, String>> kzzdList = zhcpDAO
						.getKzzdList(user);

						dao.initComments(zcxmList, user);
						dao.initDrb(zcxmList,kzzdList, user);
						dao.initDcb(zcxmList,kzzdList, user);
					} else if ("116".equalsIgnoreCase(lcdm)) {// �۲�ֽ��
						// �ۺϷּ���
						PjpyZhcpService zhcpService = new PjpyZhcpService();
						PjpyGeneralForm myForm = new PjpyGeneralForm();
						zhcpService.account(myForm, user);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * ��ʼ������������Ϣ(��һ����)
	 * 
	 * @author ΰ�����
	 */
	public void initNextPjlcInfo(String lcdj, User user) {

		// ��һ����
		String next_lcdj = String.valueOf(Integer.parseInt(lcdj) + 1);
		
		// �����һ�����ȼ��б�
		List<HashMap<String, String>> pjdjList = dao.getPjlcList(next_lcdj,
				user);

		if (pjdjList != null && pjdjList.size() > 0) {
			for (int i = 0; i < pjdjList.size(); i++) {
				HashMap<String, String> map = pjdjList.get(i);
				String lcdm = map.get("lcdm");

				try {
					if ("102".equalsIgnoreCase(lcdm)) {// ������Ա������
						// dao.initPjry(user);
					} else if ("112".equalsIgnoreCase(lcdm)) {// �ۺϲ���ά��
						dao.initZhcp(user);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * ��ʼ��ѡ����
	 * 
	 * @author ΰ�����
	 */
	private void initPjpy(PjpyIndexModel model, User user) {

		// ��ʼ������
		String start = model.getStart();

		try {
			if ("yes".equalsIgnoreCase(start)) {
				// ���ݼ�¼
				//dao.backUpTable(user);
				// ���������Ǩ����ʷ��Ϣ
				//dao.initLsxx(user);
				// ��ʼ��������Ա��
				dao.initPjry(user);
				// �����۲���Ŀ
				dao.initZcxm(user);
				// ����������Ŀ
				dao.initPjxm(user);
				// �����������С��
				dao.initCpxz(user);
				// ��������Ʒ�±��ֻ���
				if(Globals.XXDM_BJLHDX.equalsIgnoreCase(Base.xxdm)){
					dao.initPdbx(user);
				}
				// �����������
				// dao.initPjtj(user);
				// �����Ŀ���
				// dao.initXmjd(user);
			}
		} catch (Exception e) {

		}
	}

	// ======================������������=========================
	/**
	 * ��ȡ��������ͳ����Ϣ
	 * author qlj
	 */
	public List<HashMap<String, String>> getBcpjtjInfo(User user)
			throws Exception {

		return dao.getBcpjtjInfo(user);
	}
	
	/**
	 * ����������ת����ʷ��
	 * author qlj
	 */
	public void  theEnd(User user){
		
		try {
			//��������
			dao.backUpTable(user);
			//��ʼ����ʷ��
			dao.initLsxx(user);
			
		} catch (Exception e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}
		
		
	}
	
}
