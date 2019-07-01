package xsgzgl.wjcf.cfssgl;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.beanutils.BeanUtils;
import xgxt.comm.CommService;
import xgxt.comm.SearchRsModel;
import xgxt.comm.search.SearchModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;
import xsgzgl.wjcf.jcsz.WjcfJcszDao;

/**
 * 
* 
* �����ƣ�WjcfJcszServices 
* ��������Υ�ʹ������߹���Services
* �����ˣ�yijd 
* ����ʱ�䣺2012-6-19 ����09:20:00 
* �޸ı�ע��  
* @version 
*
 */
public class WjcfCfssglServices extends CommService {
	private WjcfCfssglDao dao=new WjcfCfssglDao();
	
	private WjcfJcszDao jcszdao = new WjcfJcszDao();
	/**
	 * Υ�ʹ��� �������߹���
	 * 
	 * @param model
	 * @param userType
	 * @return
	 * @throws Exception
	 */
	public List<String[]> cfssglCx(WjcfCfssglForm model,User user) throws Exception {
		if(model==null){
			return null;
		}
		String userType=user.getUserType();
		String uType="";
		if("stu".equals(userType)){
			uType="xs";
		}else if("xy".equals(userType) || "xx".equals(userType) || "admin".equals(userType)){
			uType="js";
		}else{
			//�û������쳣��ѯ���ݲ�������
			uType="bnss";
		}
		return dao.cfssglCx(model,uType,user);
	}
	
	/**
	 * Υ�ʹ��� �鿴������Ϣ tl
	 * 
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> cfssglCk(String pkValue) throws Exception {
		if(pkValue==null || "".equals(pkValue)){
			return null;
		}
		return dao.cfssglCk(pkValue);
	}
	
	/**
	 * �������� ����
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean cfssglGx(WjcfCfssglForm model) throws Exception{
		if(model==null){
			return false;
		}
		model.setSsjg("wsh");
		HashMap<String, String> cfssgl=dao.cfssCk(model.getCfid());
		InputStream is =model.getSsfj().getInputStream();
		String fjmc = StringUtils.isNotNull(model.getSsfjmc()) ? model.getSsfjmc()
				.substring(model.getSsfjmc().lastIndexOf("\\")+1,
						model.getSsfjmc().length()) : "";
		model.setSsfjmc(fjmc);
		
		if(cfssgl==null || cfssgl.isEmpty()){
			boolean flag = dao.cfssZj(model, is);
			//��ʼ��������˱�
			if(flag){
				flag = cfssshZj(model);
			}
			
			return flag;
		}else{
			return dao.cfssXg(model, is);
		}
		
	}
	
	//��ȡ����ע�ַ���
	public HashMap<String, String> getShlStr() {
		return dao.getMapNotOut("select * from xg_wjcf_ssjcsplb", new String[]{});
	}
	
	public HashMap<String, String> cxCfsswh(WjcfCfssglForm model) throws Exception{
		return dao.cfssCk(model.getCfid());
	}
	
	/**
	 * Υ�ʹ��� �鿴������Ϣ tl
	 * 
	 * @param model
	 * @param outputValue
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> ssshxxCk(String pkValue) throws Exception {
		if(pkValue==null || "".equals(pkValue)){
			return null;
		}
		return dao.ssshxxCk(pkValue);
	}
	
	/**
	 * Υ�ʹ��� �������� ����
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public boolean cfssSc(String pkValue) throws Exception{
		if(pkValue==null || "".equals(pkValue)){
			return false;
		}
		List<String[]> list=new ArrayList<String[]>();
		String[] pks=new String[]{pkValue};
		list.add(pks);
		//��������  ҲҪɾ��Υ�ʹ���  ���������Ϣ
		WjcfCfssglForm model=new WjcfCfssglForm();
		model.setCfid(pkValue);
		dao.cfssshSc(model);
		return dao.cfssSc(list);
	}
	
	/**
	 * ����ҳ��tableHtml
	 * @param rsModel
	 * @param model
	 * @param rsArrList
	 * @param user
	 * @return
	 */
	public String createTableHTML(SearchRsModel rsModel,
			WjcfCfssglForm model, List<String[]> rsArrList, User user) {
		// IE�汾
		String ie = rsModel.getIe();
		StringBuilder html = new StringBuilder();
		if (rsArrList != null && rsArrList.size() > 0) {
			for (int i = 0; i < rsArrList.size(); i++) {
				String[] rs = rsArrList.get(i);
				html.append("<tr onclick=\"rowMoreClick(this,'',event);\" style=\"cursor:hand\" >");
				html.append("<td nowrap=\"nowrap\" >");
				html.append("<input type=\"checkbox\" name=\"checkVal\" id=\"pkV\" value=\""+rs[10]+"\" />");
				html.append("</td>");
				html.append("<td nowrap=\"nowrap\" >");
				html.append(rs[0]);
				html.append("</td>");
				html.append("<td nowrap=\"nowrap\" >");
				html.append(rs[1]);
				html.append("</td>");
				html.append("<td nowrap=\"nowrap\" >");
				html.append(rs[2]);
				html.append("</td>");
				html.append("<td nowrap=\"nowrap\" >");
				html.append(rs[3]);
				html.append("</td>");
				html.append("<td nowrap=\"nowrap\" >");
				html.append(rs[4]);
				html.append("</td>");
				html.append("<td nowrap=\"nowrap\" >");
				html.append(rs[5]);
				html.append("</td>");
				html.append("<td nowrap=\"nowrap\" >");
				html.append(rs[6]);
				html.append("</td>");
				html.append("<td nowrap=\"nowrap\" >");
				html.append(rs[7]);
				html.append("</td>");
				html.append("<td nowrap=\"nowrap\" >");
				html.append(rs[9]);
				html.append("</td>");
				html.append("<td nowrap=\"nowrap\" >");
				if(rs[11]!=null && rs[11].equals("y")){
					if(rs[8]==null || "".equals(rs[8])){
						html.append("<a href=\"#\"  onclick=\"cfss('"+rs[10]+"');return false;\"><font color=\"blue\">����</font></a>");
					}else if("wsh".equals(rs[8])){
						html.append("<a href=\"#\" onclick=\"cfssxg('"+rs[10]+"');return false;\"><font color=\"blue\">�����޸�</font></a>");
						html.append("&nbsp;&nbsp;");
						html.append("<a href=\"#\" onclick=\"cfssSc('"+rs[10]+"');return false;\"><font color=\"blue\">����</font></a>");
					}else{
						html.append(" <a href=\"#\" onclick='return false;' disabled=\"true\" style=\"color: #cccccc;\" >����</a>");
					}
				}else{
					html.append(" <a href=\"#\" onclick='return false;' disabled=\"true\" style=\"color: #cccccc;\">����</a>");
				}
				html.append("</td>");
				html.append("</tr>");
			}
		}else{
//			html.append("<div class=\"con_overlfow\" style=\"text-align: center; color: red;\" >");
//			html.append("��ǰ������������ݡ�");
//			html.append("</div>");
		}

		return html.toString();
	}
	
	/**
	 * ���Ӵ����������
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean cfssshZj(WjcfCfssglForm model) throws Exception{
		String[] shlcList=dao.shlcCx();
		List<WjcfCfssglForm> modelList=new ArrayList<WjcfCfssglForm>();
		WjcfCfssglForm cf=null;
		for (int i=0;i< shlcList.length;i++) {
			cf=new WjcfCfssglForm();
			cf.setCfid(model.getCfid());
			cf.setXtgwid(shlcList[i]);
			cf.setShzt("wsh");
			modelList.add(cf);
		}
		return dao.cfssshZj(modelList);
	}
	
	/**
	 * ������λ��ѯ   �����û���
	 * @param yhm
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> spgwCx(String yhm) throws Exception{
		if(yhm==null || "".equals(yhm)){
			return null;
		}
		return dao.spgwCx(yhm);
	}
	
	
	/**
	 * ������   ��ȡ�б�ͷ
	 * @param model
	 * @param user
	 * @return
	 */
	public List<HashMap<String, String>> getTopTr(
			WjcfCfssglForm model, User user,String dataType) {
		List<HashMap<String, String>> topTr=null;
		String[] en =null;
		String[] cn =null;
		if("sscx".equals(dataType)){
			en = new String[] {"pk", "xh", "xm", "xn","xq","cflbmc", "cfyymc", "sswh", "sssj", "ssjg","ssshzt" };
			cn = new String[] {"", "ѧ��", "����", "����ѧ��", "����ѧ��", "�������","����ԭ��","�����ĺ�","����ʱ��","���״̬","����" };
		}else if("ssshcx".equals(dataType)){
			en = new String[] {"pk", "xh", "xm", "xn","xq","cflbmc", "cfyymc", "sswh", "sssj", "sszt", "ssjg" };
			cn = new String[] {"", "ѧ��", "ѧ��", "ѧ��", "����", "�������","����ԭ��","�����ĺ�","����ʱ��","��˽��","���߽��"};
		}
		
		topTr = dao.arrayToList(en, cn);
		return topTr;
	}
	
	
	
	/**
	 * �����ࣺ�����б�ͷ��
	 * 
	 * @param model
	 *            ҵ��ģ��
	 * @return
	 */
	public String[] getTopTr(WjcfCfssglForm model) {
		String[] outTr = null;
		outTr = new String[] { "ѧ��", "����", "����ѧ��", "����ѧ��", "�������","����ԭ��","�����ĺ�","����ʱ��","����","���״̬" };
		return outTr;

	}

	/**
	 * �����ࣺ��ʾ��ҳ���б�����������Ҳ�����ݲ�ѯ��������ֵ
	 * 
	 * @param model
	 *            ҵ��ģ��
	 * @return
	 */
	public String[] getColnumName(WjcfCfssglForm model) {
		String[] colnumName = null;
		colnumName = new String[] { "xh", "xm", "xn","xq",
				"cflbmc", "cfyymc", "sswh", "sssj", "ssjg","ssshzt" };
		return colnumName;
	}
	
	/**
	 * ������   ��ʽ���ļ�·����s   
	 * @param fileName
	 * @return
	 */
	public String formatFileName(String url){
		if(url==null || "".equals(url)){
			return "";
		}
		String fileName=url;
		if(url.lastIndexOf("\\") > -1){
			fileName=url.substring(url.lastIndexOf("\\")+1, url.length());
		}
		return fileName;
	}
	
	/**
	 * ������  ȷ���ļ���ʽ��
	 * @param fileName
	 * @return
	 */
	public String isCriterionFile(String fileName){
		if(fileName==null || "".equals(fileName)){
			return "";
		}
		String[] suffix=new String[]{"doc","xls","rar","pdf","txt"};
		for (int i = 0; i < suffix.length; i++) {
			if(fileName.lastIndexOf(suffix[i]) == -1){
				//��ʽ���ϸ�
				return fileName;
			}
		}
		return "";
	}
	
	/**
	 * ��ʼ��ҳ������
	 * @param rForm
	 * @param model
	 * @param user
	 * @param request
	 * @throws Exception
	 */
	public void initPage(RequestForm rForm, WjcfCfssglForm model, User user,
			HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
		
		String tableName = "view_wjcf_sswh";
		rForm.setTableName(tableName);
		//====================��ʼ��ҳ���������=====================
		// �߼���ѯ
		SearchModel searchModel = new SearchModel();
		searchModel.setSearch_tj_xn(new String[] { });
		request.setAttribute("searchTj", searchModel);
		
		//ҳ��form
		request.setAttribute("rs", model);
		
	}
	
	
	/**
	 * ��ʾ���û���λ�л�ģʽҳ��
	 * @author xucy
	 * @throws IOException 
	 */
	public void showShgwDiv(User user,
			HttpServletResponse response) throws Exception {

		response.setContentType("text/html;charset=gbk");
		
		List<HashMap<String,String>> spgwList= spgwCx(user.getUserName());
		
		StringBuilder html = new StringBuilder();

		html.append("<div class=\"open_win01\">");

		html.append("<table class=\"formlist\">");
		html.append("<thead>");
		html.append("<tr>");
		html.append("<th colspan=\"2\">");
		html.append("<span>��˸�λѡ��</span>");
		html.append("</th>");
		html.append("</tr>");
		html.append("</thead>");

		html.append("<tbody>");
		html.append("<tr>");
		html.append("<th width='25%'>");
		html.append("��λѡ��");
		html.append("</th>");
		html.append("<td style='word-break:break-all;width:96%' >");
		
		//html.append(" <input type=\"hidden\" name=\"text_xmdm\" id=\"text_xmdm\" value=\""+cflbdm+"\" /> ");
		for (int i = 0; i < spgwList.size(); i++) {
			HashMap<String,String> spgwMap=spgwList.get(i);
			html.append(" <input type=\"radio\" name=\"spgws\" ");
			if(i==0){
				html.append("  checked=\"true\" ");
			}
			html.append(" id=\"spgw_"+i+"\" value=\""+spgwMap.get("spgw")+"\">");
			html.append(spgwMap.get("gwmc") );
			html.append("<br/>");
		}
		
		html.append("</td>");
		html.append("</tr>");
		html.append("</tbody>");

		html.append("<tfoot>");
		html.append("<tr>");
		html.append("<td  colspan=\"2\">");
		html.append("<div class=\"btn\">");
		html.append("<button type=\"button\"  id=\"btn_bc\" onclick=\"checkSpgw();return false;\">");
		html.append("ȷ ��");
		html.append("</button>");

		html.append("<button type=\"button\"  id=\"btn_gb\" onclick=\"closeWindown();return false;\">");
		html.append("�� ��");
		html.append("</button>");
		html.append("</div>");
		html.append("</td>");
		html.append("</tr>");
		html.append("</tfoot>");

		html.append("</table>");
		html.append("</div>");

		response.getWriter().print(html.toString());
	}
	
	/**
	 * Υ�ʹ��� ����������˲�ѯ
	 * 
	 * @param model
	 * @param userType
	 * @return
	 * @throws Exception
	 */
	public List<String[]> cfssshCx(WjcfCfssglForm model,User user) throws Exception {
		if(model==null || user==null || model.getXtgwid()==null || "".equals(model.getXtgwid())){
			return null;
		}
		String userType=user.getUserType();
		String uType="";
		if("stu".equals(userType)){
			uType="xs";
		}else if("xy".equals(userType) || "xx".equals(userType) || "admin".equals(userType)){
			uType="js";
		}else{
			//�û������쳣��ѯ���ݲ�������
			uType="bnss";
		}
		//��ȡ�ϼ���������
		HashMap<String, String> sjCfsplc=dao.splcSjSpgw(model);
		if(sjCfsplc!=null && sjCfsplc.get("spgw")!=null && !"".equals(sjCfsplc.get("spgw"))
				&& !sjCfsplc.get("spgw").equals(model.getXtgwid())){
			model.setSjSpgw(sjCfsplc.get("spgw"));
		}
		//��ȡ�¼���������
		HashMap<String, String> xjCfsplc=dao.splcXjSpgw(model);
		if(xjCfsplc!=null && xjCfsplc.get("spgw")!=null && !"".equals(xjCfsplc.get("spgw"))){
			if(xjCfsplc.get("spgw").equals(model.getXtgwid())){
				model.setXjSpgw("");
			}else{
				model.setXjSpgw(xjCfsplc.get("spgw"));
			}
		}else{
			//�����ǿ�������  �Ƿ���Բ���   ����������ݲ����ٲ���
			return null;
//			model.setXjSpgw("");
		}
		return dao.cfssshCx(model,uType,user);
	}
	
	
	/**
	 * ����ҳ��tableHtml
	 * @param rsModel
	 * @param model
	 * @param rsArrList
	 * @param user
	 * @return
	 */
	public String createHTMLCfssshCx(SearchRsModel rsModel,
			WjcfCfssglForm model, List<String[]> rsArrList, User user) throws Exception {
		// IE�汾
		String ie = rsModel.getIe();
		String stylePath = rsModel.getStylePath();
		StringBuilder html = new StringBuilder();
		if (rsArrList != null && rsArrList.size() > 0) {
			for (int i = 0; i < rsArrList.size(); i++) {
				String[] rs = rsArrList.get(i);
				//ondblclick=\"wjcfssglCk();return false;\"
				html.append("<tr onclick=\"rowMoreClick(this,'',event);\" style=\"cursor:hand\" >");
				html.append("<td  nowrap=\"nowrap\" >");
				html.append("<input type=\"checkbox\" name=\"checkVal\" id=\"pkV\" ");
				if("disabled".equals(rs[11])){
					html.append("disabled=\"disabled\"");
				}
				html.append(" value=\"");
				html.append(rs[9]);
				html.append("\" />");
				html.append("</td>");
				html.append("<td  nowrap=\"nowrap\" >");
				html.append(rs[0]);
				html.append("</td>");
				html.append("<td  nowrap=\"nowrap\">");
				html.append(rs[1]);
				html.append("</td>");
				html.append("<td  nowrap=\"nowrap\">");
				html.append(rs[2]);
				html.append("</td>");
				html.append("<td  nowrap=\"nowrap\">");
				html.append(rs[3]);
				html.append("</td>");
				html.append("<td  nowrap=\"nowrap\">");
				html.append(rs[4]);
				html.append("</td>");
				html.append("<td  nowrap=\"nowrap\">");
				html.append(rs[5]);
				html.append("</td>");
				html.append("<td  nowrap=\"nowrap\">");
				html.append(rs[6]);
				html.append("</td>");
				html.append("<td  nowrap=\"nowrap\">");
				html.append(rs[7]);
				html.append("</td>");
				html.append("<td  nowrap=\"nowrap\">");
				html.append(getImg(rs[10],stylePath));
				html.append("</td>");
				html.append("<td>");
				if("wsh".equals(rs[8])){
					html.append("δ���");
				}else if("shz".equals(rs[8])){
					html.append("�����");
				}else if("wcycf".equals(rs[8])){
					html.append("ά��ԭ����");
				}else if("cxcf".equals(rs[8])){
					html.append("��������");
				}else if("ggcf".equals(rs[8])){
					html.append("���Ĵ���");
				}else{
					
				}
				html.append("</td>");
				html.append("</tr>");
			}
		}else{
//			html.append("<div class=\"con_overlfow\" style=\"text-align: center; color: red;\" >");
//			html.append("��ǰ������������ݡ�");
//			html.append("</div>");
		}

		return html.toString();
	}
	
	/**
	 * ��ȡ�ϼ��������    ���ݵ�ǰ�������λ  ����id   �磺��ǰΪһ���򷵻�һ�����������Ϣ
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> splcSjsh(WjcfCfssglForm model) throws Exception{
		if(model==null){
			return null;
		}
		return dao.splcSjsh(model);
	}
	
	/**
	 * ��ȡ��ǰ�������̵� ��һ������������λ��
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> splcSjSpgw(WjcfCfssglForm model) throws Exception{
		if(model==null){
			return null;
		}
		return dao.splcSjSpgw(model);
	}
	
	
	/**
	 * ��ȡ�¼��������    ���ݵ�ǰ�������λ   ����id   �����ǰ�������Ϊ���һ�����򷵻ص�ǰ�Ǻ��������    ���Ż�
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> splcXjsh(WjcfCfssglForm model) throws Exception{
		if(model==null){
			return null;
		}
		return dao.splcXjsh(model);
	}
	
	
	/**
	 * ��ȡ��ǰ�������̵� ��һ������������λ��
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> splcXjSpgw(WjcfCfssglForm model)throws Exception{
		if(model==null){
			return null;
		}
		return dao.splcXjSpgw(model);
	}
	/**
	 * �������̶���     ���ݵ�ǰ����
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> spldjCx(WjcfCfssglForm model) throws Exception{
		if(model==null){
			return null;
		}
		return dao.spldjCx(model);
	}
	
	
	/**
	 * ��������һ��     ���ݵ�ǰ����
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> splyjCx(WjcfCfssglForm model) throws Exception{
		if(model==null){
			return null;
		}
		return dao.splyjCx(model);
	}
	
	/**
	 * ��������޸�   �������
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean ssshXg(WjcfCfssglForm model)throws Exception{
		if(model==null){
			return false;
		}
		//��ǰ���״̬Ϊ�˻�
		if("th".equals(model.getShzt())){
			HashMap<String, String> ssshSj=splcSjsh(model);
			WjcfCfssglForm sjModel=new WjcfCfssglForm();
			sjModel.setCfid(ssshSj.get("cfid"));
			sjModel.setXtgwid(ssshSj.get("spgw"));
			sjModel.setShzt("xcs");
			dao.ssshXgShzt(sjModel);
		}
		//��ǰ���״̬Ϊ�������
		if("tg".equals(model.getShzt())){
			HashMap<String, String> ssshXj=splcXjsh(model);
			if("th".equals(ssshXj.get("shzt"))){
				WjcfCfssglForm xjModel=new WjcfCfssglForm();
				xjModel.setCfid(ssshXj.get("cfid"));
				xjModel.setXtgwid(ssshXj.get("spgw"));
				xjModel.setShzt("wsh");
				dao.ssshXgShzt(xjModel);
			}
		}
		//�޸Ĵ���������Ϣ  Ϊ�����
		List<WjcfCfssglForm> modelList=new ArrayList<WjcfCfssglForm>();
		WjcfCfssglForm wcf=new WjcfCfssglForm();
		wcf.setCfid(model.getCfid());
		wcf.setSsjg("shz");
		wcf.setXtgwid(model.getXtgwid());
		modelList.add(wcf);
		cfssXgSsjg(modelList);
		
		return dao.ssshXg(model);
	}
	
	
	
	/**
	 * ��������޸�   �������
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean ssshPlxg(WjcfCfssglForm model,String pkValue)throws Exception{
		if(model==null || pkValue==null){
			return false;
		}
		String[] cfids=pkValue.split("@@");
		if(cfids.length==0){
			return false;
		}
		
		List<WjcfCfssglForm> modelList=new ArrayList<WjcfCfssglForm>();//��˱�����
		WjcfCfssglForm wcf=null;
		List<WjcfCfssglForm> ssList=new ArrayList<WjcfCfssglForm>();//���߱�����
		WjcfCfssglForm ssWcf=null;
		for (int i = 0; i < cfids.length; i++) {
			wcf=new WjcfCfssglForm();
			ssWcf=new WjcfCfssglForm();
			BeanUtils.copyProperties(wcf, model);
			BeanUtils.copyProperties(ssWcf, model);
			wcf.setCfid(cfids[i]);
			modelList.add(wcf);
			//���߱��޸�
			ssWcf.setCfid(cfids[i]);
			ssWcf.setSsjg("shz");
			ssList.add(ssWcf);
		}
		
		//��ǰ���״̬Ϊ�˻�
		if("th".equals(model.getShzt())){
			HashMap<String, String> ssshSj=splcSjSpgw(model);
			List<WjcfCfssglForm> sjModelList=new ArrayList<WjcfCfssglForm>();
			WjcfCfssglForm sjModel=null;
			for (int i = 0; i < cfids.length; i++) {
			 	sjModel=new WjcfCfssglForm();
			 	sjModel.setCfid(cfids[i]);
				sjModel.setXtgwid(ssshSj.get("spgw"));
				sjModel.setShzt("xcs");
				sjModelList.add(sjModel);
			}
			
			dao.ssshPlxgShzt(sjModelList);
		}
		//��ǰ���״̬Ϊ�������
		if("tg".equals(model.getShzt())){
			HashMap<String, String> ssshXj=splcXjSpgw(model);
			List<WjcfCfssglForm> xjModelList=new ArrayList<WjcfCfssglForm>();
			WjcfCfssglForm xjModel=null;
			for (int i = 0; i < cfids.length; i++) {
				xjModel = new WjcfCfssglForm();
				xjModel.setCfid(cfids[i]);
				xjModel.setXtgwid(ssshXj.get("spgw"));
				xjModel.setShzt("wsh");
				xjModelList.add(xjModel);
			}
			dao.ssshPlxgShzt(xjModelList);
		}
		
		cfssXgSsjg(ssList);//������Ϣ ���״̬�޸�
		return dao.ssshPlxg(modelList);
	}
	
	/**
	 * ��ѯ�������   ���ݴ���id
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> ssshCxGjCfid(WjcfCfssglForm model) throws Exception{
		if(model==null){
			return null;
		}
		return dao.ssshCxGjCfid(model);
	}
	
	/**
	 * �鿴������˸���   ����id  ��λ����
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> ssshCkGjCfidGwjb(WjcfCfssglForm model) throws Exception{
		if(model==null){
			return null;
		}
		return dao.ssshCkGjCfidGwjb(model);
	}
	
	/**
	 * �������ͳ��
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> ssshShjgTj() throws Exception{
		return dao.ssshTj();
	}
	
	/**
	 * ��������ύ Υ�ʹ���   �������ύ��
	 * @return
	 * @throws Exception
	 */
	public boolean ssshTjWjcf() throws Exception{
		List<HashMap<String, String>> dtjSh=dao.ssshCxXtj();
		List<WjcfCfssglForm> modelList=new ArrayList<WjcfCfssglForm>();
		WjcfCfssglForm model=null;
		for (int i = 0; i < dtjSh.size(); i++) {
			model=new WjcfCfssglForm();
			model.setCfid(dtjSh.get(i).get("cfid"));
			model.setSftj("1");
			model.setSssj(dtjSh.get(i).get("sssj"));
			model.setSswh(dtjSh.get(i).get("sswh"));
			model.setSsjg(dtjSh.get(i).get("shzt"));
			model.setCfggw(dtjSh.get(i).get("cfggw"));
			modelList.add(model);
		}
		//����״̬�޸��ύ
		dao.cfssXgSsjg(modelList);
		//��������޸��ύ
		dao.ssshXgTj(modelList);
		return dao.wjcfXgTj(modelList);
	}
	
	/**
	 * ��������ύ Υ�ʹ���   ������ֱ���ύ��
	 * @return
	 * @throws Exception
	 */
	public boolean ssshTj(String pk) throws Exception{
		boolean flag  = false;
		if(null!=pk){
			String params[] = pk.split("@@");
			List<HashMap<String, String>> dtjSh=dao.ssshXtj(params);
			List<WjcfCfssglForm> modelList=new ArrayList<WjcfCfssglForm>();
			WjcfCfssglForm model=null;
			for (int i = 0; i < dtjSh.size(); i++) {
				model=new WjcfCfssglForm();
				model.setCfid(dtjSh.get(i).get("cfid"));
				model.setSftj("1");
				model.setSssj(dtjSh.get(i).get("sssj"));
				model.setSswh(dtjSh.get(i).get("sswh"));
				model.setSsjg(dtjSh.get(i).get("shzt"));
				model.setCfggw(dtjSh.get(i).get("cfggw"));
				modelList.add(model);
			}
			//����״̬�޸��ύ
			dao.cfssXgSsjg(modelList);
			//��������޸��ύ
			dao.ssshXgTj(modelList);
			dao.wjcfXgTj(modelList);
		}
		return flag;
	}
	
	/**
	 * ���������޸�  ���߽��
	 * @param modelList
	 * @return
	 * @throws Exception
	 */
	public boolean cfssXgSsjg(List<WjcfCfssglForm> modelList)throws Exception{
		return dao.cfssXgSsjg(modelList);
	}
	
	/**
	 * ������    ��ȡ�����״̬ͼƬ
	 * @param state  ���״̬
	 * @param stylePath  ϵͳ��Ŀ¼
	 * @return
	 * @throws Exception
	 */
	public String getImg(String state,String stylePath)throws Exception{
		StringBuffer html=new StringBuffer();
		if ("δ���".equalsIgnoreCase(state)) {
			html.append("<p><img src=\"");
			html.append(stylePath);
			html.append("images/ico_dsh.gif\" width=\"52\" height=\"18\" /></p>");
		} else if ("���ͨ��".equalsIgnoreCase(state)) {
			html.append("<p><img src=\"");
			html.append(stylePath);
			html.append("images/ico_shtg.gif\" width=\"52\" height=\"18\" /></p>");
		} else if ("��˲�ͨ��".equalsIgnoreCase(state)) {
			html.append("<p><img src=\"");
			html.append(stylePath);
			html.append("images/ico_shbtg.gif\" width=\"52\" height=\"18\" /></p>");
		} else if ("�˻�".equalsIgnoreCase(state)) {
			html.append("<p><img src=\"");
			html.append(stylePath);
			html.append("images/ico_shth.gif\" width=\"52\" height=\"18\" /></p>");
		} else if ("����".equalsIgnoreCase(state)) {
			html.append("<p><img src=\"");
			html.append(stylePath);
			html.append("images/ico_shxcs.gif\" width=\"52\" height=\"18\" /></p>");
		}else if ("wsh".equalsIgnoreCase(state)) {
			html.append("<p><img src=\"");
			html.append(stylePath);
			html.append("images/ico_dsh.gif\" width=\"52\" height=\"18\" /></p>");
		} else if ("tg".equalsIgnoreCase(state)) {
			html.append("<p><img src=\"");
			html.append(stylePath);
			html.append("images/ico_shtg.gif\" width=\"52\" height=\"18\" /></p>");
		} else if ("btg".equalsIgnoreCase(state)) {
			html.append("<p><img src=\"");
			html.append(stylePath);
			html.append("images/ico_shbtg.gif\" width=\"52\" height=\"18\" /></p>");
		} else if ("th".equalsIgnoreCase(state)) {
			html.append("<p><img src=\"");
			html.append(stylePath);
			html.append("images/ico_shth.gif\" width=\"52\" height=\"18\" /></p>");
		} else if ("xcs".equalsIgnoreCase(state)) {
			html.append("<p><img src=\"");
			html.append(stylePath);
			html.append("images/ico_shxcs.gif\" width=\"52\" height=\"18\" /></p>");
		}else if ("ggcf".equalsIgnoreCase(state)) {
			html.append("<p>���Ĵ���</p>");
		}else if ("wcycf".equalsIgnoreCase(state)) {
			html.append("<p>ά��ԭ����</p>");
		}else if ("cxcf".equalsIgnoreCase(state)) {
			html.append("<p>��������</p>");
		}
		return html.toString();
	}
	
	/**
	 * ������ ������λȨ��  
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> spgwQx(HashMap<String, String> sjSp,HashMap<String, String> xjSp
			,HashMap<String, String> yjSp,HashMap<String, String> djSp,HashMap<String, String> dqSp)throws Exception{
		HashMap<String, String> spgwqx=new HashMap<String, String>();
		if(sjSp==null || xjSp==null || yjSp==null || djSp==null){
			return null;
		}
		String yj=yjSp.get("spgw");//��һ��������λ
		String dj=djSp.get("spgw");//�ȼ�������λ
		String sj=sjSp.get("spgw");//�ϼ�������λ
		String xj=xjSp.get("spgw");//�¼�������λ
		String dq=dqSp.get("xtgwid");//��ǰ���������λ
		//��ǰ�����˻���   Ϊ��һ����������
		if(yj!=null && yj.equals(dq)){
			if("wsh".equals(xjSp.get("shzt")) || "th".equals(xjSp.get("shzt"))){
				spgwqx.put("tg", "");//��ͨ��Ȩ��
			}else{
				spgwqx.put("tg", "disabled=\"disabled\"");//û��ͨ��Ȩ��
			}
		}
		//��ǰ�����˻���   Ϊ�м价�ڵ�һ����������
		if(yj!=null && !yj.equals(dq)){
			//ͨ����ť
			if("tg".equals(sjSp.get("shzt"))){
				if("wsh".equals(xjSp.get("shzt")) || "th".equals(xjSp.get("shzt"))){
					spgwqx.put("tg", "");//��ͨ��Ȩ��
				}else{
					spgwqx.put("tg", "disabled=\"disabled\"");//û��ͨ��Ȩ��
				}
			}else{
				spgwqx.put("tg", "disabled=\"disabled\"");//û��ͨ��Ȩ��
			}
			//�˻ذ�ť
			if("tg".equals(sjSp.get("shzt"))){
				if("wsh".equals(xjSp.get("shzt")) || "th".equals(xjSp.get("shzt"))){
					spgwqx.put("th", "");//�а�ťȨ��
				}else{
					spgwqx.put("th", "disabled=\"disabled\"");//û�а�ťȨ��
				}
			}else{
				spgwqx.put("th", "disabled=\"disabled\"");//û�а�ťȨ��
			}
		}
		
		//��ǰ�����˻���   ���һ������
		if(dj!=null && dj.equals(dq)){
			//�˻ذ�ť
			if("tg".equals(sjSp.get("shzt"))){
				spgwqx.put("th", "");//�а�ťȨ��
			}else{
				spgwqx.put("th", "disabled=\"disabled\"");//û�а�ťȨ��
			}
		}
		
		return spgwqx;
	}
	
	
	/**
	 * ��ѯ����������Ϣ
	 * @param form
	 * @return
	 */
	public InputStream fjCx(WjcfCfssglForm form) throws Exception {
		Blob blob = dao.fjCx("select fj from xg_wjcf_wjcfssb where cfid = ?", new String[]{form.getCfid()}, "fj");
		return blob.getBinaryStream();
	}
	
	/**
	 * ��ѯ������Ϣ
	 * @param form
	 * @return
	 */
	public InputStream cffjCx(WjcfCfssglForm form) throws Exception {
		Blob blob = dao.fjCx("select fj from xg_wjcf_wjcfb where cfid = ?", new String[]{form.getCfid()}, "fj");
		return blob.getBinaryStream();
	}
	

	/**
	 * �ж��Ƿ���߼��û�
	 * 
	 * @param model
	 * @param outputValue
	 * @return
	 * @throws Exception
	 */
	public boolean isZgjyh(User user) throws Exception {
		
		return dao.isZgjyh(user);
	}
	
	
	/**
	 * ��ʾ�������ҳ��
	 * @author xucy
	 * @throws IOException 
	 */
	public void showCflbDiv(HttpServletResponse response) throws Exception {

		response.setContentType("text/html;charset=gbk");
		
		List<HashMap<String,String>> cflbList= jcszdao.cflbdmCx();
		
		StringBuilder html = new StringBuilder();

		html.append("<div class=\"open_win01\">");

		html.append("<table class=\"formlist\">");
		html.append("<thead>");
		html.append("<tr>");
		html.append("<th colspan=\"2\">");
		html.append("<span>�������ѡ��</span>");
		html.append("</th>");
		html.append("</tr>");
		html.append("</thead>");

		html.append("<tbody>");
		html.append("<tr>");
		html.append("<th width='25%'>");
		html.append("�������ѡ��");
		html.append("</th>");
		html.append("<td style='word-break:break-all;width:96%' >");
		

		html.append(" <select name=\"cflbmcs\" id=\"cflbmcs\" style=\"width:150px\" ");
		for (int i = 0; i < cflbList.size(); i++) {
			HashMap<String,String> cflbMap=cflbList.get(i);
			html.append(" <option value=\""+cflbMap.get("mc")+"\" selected=\"selected\">"+cflbMap.get("mc")+"</option> ");
		}
		html.append("</select>");
		html.append("</td>");
		html.append("</tr>");
		html.append("</tbody>");

		html.append("<tfoot>");
		html.append("<tr>");
		html.append("<td  colspan=\"2\">");
		html.append("<div class=\"btn\">");
		html.append("<button type=\"button\"  id=\"btn_bc\" onclick=\"checkCflb();return false;\">");
		html.append("ȷ ��");
		html.append("</button>");

		html.append("<button type=\"button\"  id=\"btn_gb\" onclick=\"closeWindown();return false;\">");
		html.append("�� ��");
		html.append("</button>");
		html.append("</div>");
		html.append("</td>");
		html.append("</tr>");
		html.append("</tfoot>");

		html.append("</table>");
		html.append("</div>");

		response.getWriter().print(html.toString());
	}
	
	
	public String[] getSsshSpgw(){
		try {
			return dao.shlcCx();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public String[] getSsjcSpgw(){
		
		try {
			return dao.getSsjcSpgw();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	/**
	 *  ��������ά�� �Զ��嵼��
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> cfssglCxExport(WjcfCfssglForm model,User user) throws Exception {
		if(model==null){
			return null;
		}
		String userType=user.getUserType();
		String uType="";
		if("stu".equals(userType)){
			uType="xs";
		}else if("xy".equals(userType) || "xx".equals(userType) || "admin".equals(userType)){
			uType="js";
		}else{
			//�û������쳣��ѯ���ݲ�������
			uType="bnss";
		}
		return dao.cfssglCxExport(model,uType,user);
	}
}
