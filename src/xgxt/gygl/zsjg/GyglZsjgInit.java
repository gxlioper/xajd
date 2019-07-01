package xgxt.gygl.zsjg;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.util.MessageResources;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommSetting;
import xgxt.form.RequestForm;
import xgxt.gygl.cwgl.GyglCwglForm;
import xgxt.utils.Pages;
import xgxt.utils.SearchUtils;
import xgxt.xtwh.sysz.SyszForm;

public class GyglZsjgInit {

	/**
	 * ��Ԣס����Ϣ_��ʼ������
	 * 
	 * @param request
	 * @author qlj
	 * 
	 */
	public void getLsxxRForm(RequestForm rForm, GyglZsjgForm model,
			HttpServletRequest request) {

		// ����ģ��
		String gnmk = "gygl";
		// ϵͳ�ֶ�����
		String menu = "zsxx";
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ����·��
		String path = "gygl_zsjg_lsxx.do";
		// ��
		String tableName = "xszslsxxb";
		// ����ֶ�
		String[] colList = { "guid", "xh", "xm", "nj", "bjmc", "zymc", "xymc",
				"xb", "ldmc", "qsh", "cwh" };
		// ��ͷ
		List<HashMap<String, String>> topTr = getTopTr(colList, "lsxx");
		// �Ƿ��ѯ����
		String search = Base.isNull(request.getParameter("go")) ? "false"
				: "true";
		// ��ҳ
		// Pages pages = model.getPages();

		// ===============ͨ������=================
		CommSetting commSetting = new CommSetting();

		// ���������
		String rsName = "rsArrList";
		commSetting.setRsName(rsName);

		// �Ƿ���Ҫcheckbox
		String isCheckBox = "yes";
		commSetting.setIsCheckBox(isCheckBox);

		// ��ʾ����ʼ��
		String startNum = "1";
		commSetting.setStartNum(startNum);
		
		//��ʾ����
		String showNum = "10";
		commSetting.setShowNum(showNum);
		
		// ===============ͨ������ end ============

		rForm.setDoType(doType);
		rForm.setGnmk(gnmk);
		rForm.setMenu(menu);
		rForm.setPath(path);
		rForm.setColList(colList);
		rForm.setTopTr(topTr);
		rForm.setSearch(search);
		rForm.setCommSetting(commSetting);

	}
	
	
	/**
	 * ��Ԣס����Ϣ_��ʼ������
	 * 
	 * @param request
	 * @author qlj
	 * 
	 */
	public void getZsxxRForm(RequestForm rForm, GyglZsjgForm model,
			HttpServletRequest request) {

		// ����ģ��
		String gnmk = "gygl";
		// ϵͳ�ֶ�����
		String menu = "zsxx";
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ����·��
		String path = "gygl_zsjg_zsxx.do";
		// ��
		String tableName = "xszsxxb";
		// ����ֶ�
		String[] colList = { "pkValue", "xh", "xm", "xb", "nj", "xymc", "zymc",
						"bjmc", "ssbh", "ldmc", "cs","qsh","cwh","rzrq" };
		// ��ͷ
		List<HashMap<String, String>> topTr = getTopTr(colList, "zsxx");
		// �Ƿ��ѯ����
		String search = Base.isNull(request.getParameter("go")) ? "false"
				: "true";
		// ��ҳ
		// Pages pages = model.getPages();

		// ===============ͨ������=================
		CommSetting commSetting = new CommSetting();

		// ���������
		String rsName = "rsArrList";
		commSetting.setRsName(rsName);

		// �Ƿ���Ҫcheckbox
		String isCheckBox = "yes";
		commSetting.setIsCheckBox(isCheckBox);

		// ��ʾ����ʼ��
		String startNum = "1";
		commSetting.setStartNum(startNum);
		
		//��ʾ����
		int showNum =colList.length-1;
		commSetting.setShowNum(String.valueOf(showNum));
		
		// ===============ͨ������ end ============

		rForm.setDoType(doType);
		rForm.setGnmk(gnmk);
		rForm.setMenu(menu);
		rForm.setPath(path);
		rForm.setColList(colList);
		rForm.setTopTr(topTr);
		rForm.setSearch(search);
		rForm.setCommSetting(commSetting);

	}
	
	/**
	 * ��Ԣס����Ϣͳ��_��ʼ������
	 * @param request
	 * @author qlj
	 * 
	 */
	public void getZsxxTjRForm(RequestForm rForm, GyglZsjgForm model,
			HttpServletRequest request) {

		// ����ģ��
		String gnmk = "gygl";
		// ϵͳ�ֶ�����
		String menu = "zsxx";
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ��ѯ���
		String searchType=model.getFpdx();
		// ����·��
		String path = "gygl_zsjg_zsxxtj.do&searchType="+searchType;
		
		// ��
		String tableName = "xszsxxb";
		// ����ֶ�
		String[] colList = { "pkValue", "xh", "xm", "xb", "nj", "xymc", "zymc",
						"bjmc", "ssbh", "ldmc", "cs","qsh","cwh","rzrq" };
		// ��ͷ
		List<HashMap<String, String>> topTr = getTopTr(colList, "zsxx");
		// �Ƿ��ѯ����
		String search = Base.isNull(request.getParameter("go")) ? "false"
				: "true";
		// ��ҳ
		// Pages pages = model.getPages();

		// ===============ͨ������=================
		CommSetting commSetting = new CommSetting();

		// ���������
		String rsName = "rsArrList";
		commSetting.setRsName(rsName);

		// �Ƿ���Ҫcheckbox
		String isCheckBox = "yes";
		commSetting.setIsCheckBox(isCheckBox);

		// ��ʾ����ʼ��
		String startNum = "1";
		commSetting.setStartNum(startNum);
		
		//��ʾ����
		int showNum =colList.length-1;
		commSetting.setShowNum(String.valueOf(showNum));
		
		// ===============ͨ������ end ============

		rForm.setDoType(doType);
		rForm.setGnmk(gnmk);
		rForm.setMenu(menu);
		rForm.setPath(path);
		rForm.setColList(colList);
		rForm.setTopTr(topTr);
		rForm.setSearch(search);
		rForm.setCommSetting(commSetting);

	}
	
	/**
	 * ��Ԣס����Ϣ_��ʼ������
	 * 
	 * @param request
	 * @author qlj
	 * 
	 */
	public void getQsydRForm(RequestForm rForm, GyglZsjgForm model,
			HttpServletRequest request) {

		// ����ģ��
		String gnmk = "gygl";
		// ϵͳ�ֶ�����
		String menu = "ydgl";
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ����·��
		String path = "gygl_zsjg_ydgl.do";
		// ��
		String tableName = "xszsxxb";
		// ����ֶ�
		String[] colList = { "pkValue", "xh", "xm", "xb", "nj", "xymc", "zymc",
						"bjmc", "ssbh", "ldmc", "cs","qsh","cwh","rzrq" };
		// ��ͷ
		List<HashMap<String, String>> topTr = getTopTr(colList, "ydxx");
		// �Ƿ��ѯ����
		String search = Base.isNull(request.getParameter("go")) ? "false"
				: "true";
		// ��ҳ
		// Pages pages = model.getPages();

		// ===============ͨ������=================
		CommSetting commSetting = new CommSetting();

		// ���������
		String rsName = "rsArrList";
		commSetting.setRsName(rsName);

		// �Ƿ���Ҫcheckbox
		String isCheckBox = "yes";
		commSetting.setIsCheckBox(isCheckBox);

		// ��ʾ����ʼ��
		String startNum = "1";
		commSetting.setStartNum(startNum);
		
		//��ʾ����
		int showNum =colList.length-1;
		commSetting.setShowNum(String.valueOf(showNum));
		
		// ===============ͨ������ end ============

		rForm.setDoType(doType);
		rForm.setGnmk(gnmk);
		rForm.setMenu(menu);
		rForm.setPath(path);
		rForm.setColList(colList);
		rForm.setTopTr(topTr);
		rForm.setSearch(search);
		rForm.setCommSetting(commSetting);

	}
	
	/**
	 * ��ò�ͬ�������ı�ͷ
	 * 
	 * @author qlj
	 * 
	 */
	private List<HashMap<String, String>> getTopTr(String[] colList, String fpdx) {

		String[] colListCN = null;
		if ("lsxx".equalsIgnoreCase(fpdx)) {// ��ʷ��Ϣ

			colListCN = new String[] { "����", "ѧ��", "����", "�꼶", "�༶", "רҵ",
					Base.YXPZXY_KEY, "�Ա�", "¥��", "���Һ�", "��λ��" };
		} else if ("zsxx".equalsIgnoreCase(fpdx)) {// ס����Ϣ

			colListCN = new String[] { "����", "ѧ��", "����", "�Ա�", "�꼶", Base.YXPZXY_KEY,
					"רҵ", "�༶", "������", "¥��", "����", "���Һ�", "��λ��", "��ס����" };

		} else if ("ydxx".equalsIgnoreCase(fpdx)) {// �춯��Ϣ

			colListCN = new String[] { "����", "ѧ��", "����", "�Ա�", "�꼶", Base.YXPZXY_KEY,
					"רҵ", "�༶", "������", "¥��", "����", "���Һ�", "��λ��", "��ס����" };
		} else if ("qsrzqk".equalsIgnoreCase(fpdx)) {// ������ס���

			colListCN = new String[] { "¥��", "���Һ�", "�Ա�����", "�������༶" };
		}

		DAO dao = DAO.getInstance();

		List<HashMap<String, String>> topTr = dao.arrayToList(colList,
				colListCN);

		return topTr;
	}
	
	/**
	 * ��ѧ������
	 * 
	 * @param request
	 * @author qlj
	 * 
	 */
	public void iniZstjRForm(RequestForm rForm, GyglZsjgForm model,
			HttpServletRequest request) {

		// ����ģ��
		String gnmk = "gygl";
		// ϵͳ�ֶ�����
		String menu = "cwfp";
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ����·��
		String path = "gygl_zsjg_zstj.do";
		// ��ѯ����
		String searchType = model.getFpdx();
		// ����ֶ�
		
		String[] colList =null;
		if("xy".equalsIgnoreCase(searchType)){
			colList = new String[] {"pkValue","xymc","xyrs","rzbss","rzqtss"};
		}else if("njxy".equalsIgnoreCase(searchType)){
			colList = new String[] {"pkValue","nj","xymc","xyrs","rzbss","rzqtss"};
		}else if("njzy".equalsIgnoreCase(searchType)){
			colList = new String[] {"pkValue","nj","zymc","xyrs","rzbss","rzqtss"};
		}else if("bj".equalsIgnoreCase(searchType)){
			colList = new String[] {"pkValue","nj","xymc","zymc","bjmc","xyrs","rzbss","rzqtss"};
		}
		
		// ��ͷ
		List<HashMap<String, String>> topTr = getZstjTopTr(colList, searchType);
		// �Ƿ��ѯ����
		String search = !"go".equalsIgnoreCase(request.getParameter("go")) ? "false"
				: "true";

		// ===============ͨ������=================
		CommSetting commSetting = new CommSetting();

		// ���������
		String rsName = "rsArrList";
		commSetting.setRsName(rsName);

		// �Ƿ���Ҫcheckbox
		String isCheckBox = "no";
		commSetting.setIsCheckBox(isCheckBox);

		// ��ʾ����ʼ��
		String startNum = "0";
		commSetting.setStartNum(startNum);

		// ��ʾ����
		String showNum =String.valueOf(colList.length);
		commSetting.setShowNum(showNum);

		// ===============ͨ������ end ============

		String[] qtzd = new String[] { "xfprs" };
		String[] qtzdz = new String[] { String.valueOf(Integer
				.parseInt(showNum) + 1) };

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);

		rForm.setSearchType(searchType);
		rForm.setDoType(doType);
		rForm.setGnmk(gnmk);
		rForm.setMenu(menu);
		rForm.setPath(path);
		rForm.setColList(colList);
		rForm.setTopTr(topTr);
		rForm.setSearch(search);
		rForm.setCommSetting(commSetting);

	}
	
	/**
	 * ��ѧ������
	 * 
	 * @param request
	 * @author qlj
	 * 
	 */
	public void iniSsydTjRForm(RequestForm rForm, GyglZsjgForm model,
			HttpServletRequest request) {

		// ����ģ��
		String gnmk = "gygl";
		// ϵͳ�ֶ�����
		String menu = "cwfp";
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ����·��
		String path = "gygl_zsjg_ydtj.do";
		// ��ѯ����
		String searchType = model.getFpdx();
		// ����ֶ�
		
		String[] colList =null;
		if("xy".equalsIgnoreCase(searchType)){
			colList = new String[] {"pkValue","xymc","xyrs","rzbss","rzqtss"};
		}else if("njxy".equalsIgnoreCase(searchType)){
			colList = new String[] {"pkValue","nj","xymc","xyrs","rzbss","rzqtss"};
		}else if("njzy".equalsIgnoreCase(searchType)){
			colList = new String[] {"pkValue","nj","zymc","xyrs","rzbss","rzqtss"};
		}else if("bj".equalsIgnoreCase(searchType)){
			colList = new String[] {"pkValue","nj","xymc","zymc","bjmc","xyrs","rzbss","rzqtss"};
		}
		
		// ��ͷ
		List<HashMap<String, String>> topTr = getSsydtjTopTr(model, searchType);
		// �Ƿ��ѯ����
		String search = !"go".equalsIgnoreCase(request.getParameter("go")) ? "false"
				: "true";

		// ===============ͨ������=================
		CommSetting commSetting = new CommSetting();

		// ���������
		String rsName = "rsArrList";
		commSetting.setRsName(rsName);

		// �Ƿ���Ҫcheckbox
		String isCheckBox = "yes";
		commSetting.setIsCheckBox(isCheckBox);

		// ��ʾ����ʼ��
		String startNum = "0";
		commSetting.setStartNum(startNum);

		// ��ʾ����
		String showNum =String.valueOf(topTr.size()-1);
		commSetting.setShowNum(showNum);

		// ===============ͨ������ end ============

		String[] qtzd = new String[] { "xfprs" };
		String[] qtzdz = new String[] { String.valueOf(Integer
				.parseInt(showNum) + 1) };

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);

		rForm.setSearchType(searchType);
		rForm.setDoType(doType);
		rForm.setGnmk(gnmk);
		rForm.setMenu(menu);
		rForm.setPath(path);
		rForm.setColList(colList);
		rForm.setTopTr(topTr);
		rForm.setSearch(search);
		rForm.setCommSetting(commSetting);

	}
	
	/**
	 * ��ѧ������
	 * 
	 * @param request
	 * @author qlj
	 * 
	 */
	public void iniSsydCxRForm(RequestForm rForm, GyglZsjgForm model,
			HttpServletRequest request) {

		// ����ģ��
		String gnmk = "gygl";
		// ϵͳ�ֶ�����
		String menu = "zsjg";
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ����·��
		String path = "gygl_zsjg_ydcx.do";
		// ����ֶ�
		
		String[] colList =null;
		colList = new String[] {"xh","xm","xb","nj","xymc","zymc","bjmc","ydqldmc","ydqcs","ydqqsh","ydhldmc","ydhcs","ydhqsh"};
	
		// ��ͷ
		List<HashMap<String, String>> topTr = getSsydCxTopTr(colList);
		// �Ƿ��ѯ����
		String search = !"go".equalsIgnoreCase(request.getParameter("go")) ? "false"
				: "true";

		// ===============ͨ������=================
		CommSetting commSetting = new CommSetting();

		// ���������
		String rsName = "rsArrList";
		commSetting.setRsName(rsName);

		// �Ƿ���Ҫcheckbox
		String isCheckBox = "no";
		commSetting.setIsCheckBox(isCheckBox);

		// ��ʾ����ʼ��
		String startNum = "0";
		commSetting.setStartNum(startNum);

		// ��ʾ����
		String showNum =String.valueOf(topTr.size());
		commSetting.setShowNum(showNum);

		// ===============ͨ������ end ============

		String[] qtzd = new String[] { "xfprs" };
		String[] qtzdz = new String[] { String.valueOf(Integer
				.parseInt(showNum) + 1) };

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);

		rForm.setDoType(doType);
		rForm.setGnmk(gnmk);
		rForm.setMenu(menu);
		rForm.setPath(path);
		rForm.setColList(colList);
		rForm.setTopTr(topTr);
		rForm.setSearch(search);
		rForm.setCommSetting(commSetting);

	}
	
	/**
	 * ��ò�ͬ�������ı�ͷ
	 * 
	 * @author qlj
	 * 
	 */
	private List<HashMap<String, String>> getZstjTopTr(String[] colList, String fpdx) {

		String[] colListCN = null;
		
		if("xy".equalsIgnoreCase(fpdx)){
			colListCN = new String[] {"����",Base.YXPZXY_KEY,Base.YXPZXY_KEY+"����","��ס����������","��ס����������"};
		}else if("njxy".equalsIgnoreCase(fpdx)){
			colListCN = new String[] {"����","�꼶",Base.YXPZXY_KEY,Base.YXPZXY_KEY+"����","��ס����������","��ס����������"};
		}else if("njzy".equalsIgnoreCase(fpdx)){
			colListCN = new String[] {"����","�꼶","רҵ",Base.YXPZXY_KEY+"����","��ס����������","��ס����������"};
		}else if("bj".equalsIgnoreCase(fpdx)){
			colListCN = new String[] {"����","�꼶",Base.YXPZXY_KEY,"רҵ","�༶",Base.YXPZXY_KEY+"����","��ס����������","��ס����������"};
		}
		
		
		DAO dao = DAO.getInstance();

		List<HashMap<String, String>> topTr = dao.arrayToList(colList,
				colListCN);

		return topTr;
	}
	
	/**
	 * ��ò�ͬ�������ı�ͷ
	 * 
	 * @author qlj
	 * 
	 */
	private List<HashMap<String, String>> getSsydtjTopTr(GyglZsjgForm myForm, String fpdx) {

		String[] colListCN = null;
		GyglZsjgService service =new GyglZsjgService();
		List<String>ydxnList=service.getYdxnList(myForm);
		int len=ydxnList.size();
		if("xy".equalsIgnoreCase(fpdx)){
			colListCN = new String[2+2*len]; 
			colListCN[0]="����";
			colListCN[1]=Base.YXPZXY_KEY;
			for(int i=0;i<ydxnList.size();i++){
				colListCN[2*(i+1)]=ydxnList.get(i)+"(����)";
				colListCN[2*(i+1)+1]=ydxnList.get(i)+"(�˴�)";
			}
			
		}else if("njxy".equalsIgnoreCase(fpdx)){
			colListCN = new String[3+2*len]; 
			colListCN[0]="����";
			colListCN[1]="�꼶";
			colListCN[2]=Base.YXPZXY_KEY;
			for(int i=0;i<ydxnList.size();i++){
				colListCN[2*(i+1)+1]=ydxnList.get(i)+"(����)";
				colListCN[2*(i+1)+2]=ydxnList.get(i)+"(�˴�)";
			}
		}else if("njzy".equalsIgnoreCase(fpdx)){
			colListCN = new String[4+2*len]; 
			colListCN[0]="����";
			colListCN[1]="�꼶";
			colListCN[2]=Base.YXPZXY_KEY;
			colListCN[3]="רҵ";
			for(int i=0;i<ydxnList.size();i++){
				colListCN[2*(i+1)+2]=ydxnList.get(i)+"(����)";
				colListCN[2*(i+1)+3]=ydxnList.get(i)+"(�˴�)";
			}
		}else if("bj".equalsIgnoreCase(fpdx)){
			colListCN = new String[5+2*len]; 
			colListCN[0]="����";
			colListCN[1]="�꼶";
			colListCN[2]=Base.YXPZXY_KEY;
			colListCN[3]="רҵ";
			colListCN[4]="�༶";
			for(int i=0;i<ydxnList.size();i++){
				colListCN[2*(i+1)+3]=ydxnList.get(i)+"(����)";
				colListCN[2*(i+1)+4]=ydxnList.get(i)+"(�˴�)";
			}
		}
		
		
		DAO dao = DAO.getInstance();

		List<HashMap<String, String>> topTr = dao.arrayToList(colListCN,
				colListCN);

		return topTr;
	}
	
	/**
	 * ��ò�ͬ�������ı�ͷ
	 * 
	 * @author qlj
	 * 
	 */
	private List<HashMap<String, String>> getSsydCxTopTr(String[] colList) {

		String[] colListCN = null;
				
		colListCN = new String[] {"ѧ��","����","�Ա�","�꼶",Base.YXPZXY_KEY,"רҵ","�༶",
				"�춯ǰ¥��","�춯ǰ����","�춯ǰ����","�춯��¥��","�춯�����","�춯������"};

		DAO dao = DAO.getInstance();

		List<HashMap<String, String>> topTr = dao.arrayToList(colList,
				colListCN);

		return topTr;
	}
	
	/**
	 * ��������
	 * @param request
	 * @author qlj
	 * 
	 */
	public void iniKxssTjForm(RequestForm rForm, GyglZsjgForm model,
			HttpServletRequest request,MessageResources message) {

		// ����ģ��
		String gnmk = "gygl";
		// ϵͳ�ֶ�����
		String menu = "zsjg";
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ����·��
		String path = "gygl_zsjg_kxss.do";
		//����У��
		String czxq=model.getCzxq();
		//����԰��
		String czyq=model.getCzyq();
		
		// ����ֶ�
		
		String[] colList =null;
		
		
		colList = new String[] { "lddm", "xqmc", "yqmc","ldmc", "xbxd", "xqs", "kqs" };
		
	
		//��ͷ
		List<HashMap<String, String>> topTr = getKxssTjTopTr(model,colList, message);
		// �Ƿ��ѯ����
		String search = !"go".equalsIgnoreCase(request.getParameter("go")) ? "false"
				: "true";

		// ===============ͨ������=================
		CommSetting commSetting = new CommSetting();

		// ���������
		String rsName = "rs";
		commSetting.setRsName(rsName);

		// �Ƿ���Ҫcheckbox
		String isCheckBox = "no";
		commSetting.setIsCheckBox(isCheckBox);

		// ��ʾ����ʼ��
		String startNum = "0";
		commSetting.setStartNum(startNum);

		// ��ʾ����
		int n=topTr.size()-1;
		if(!"��".equalsIgnoreCase(model.getCzxq())){
			n--;
		}
		if(!"��".equalsIgnoreCase(model.getCzyq())){
			n--;
		}
		String showNum =String.valueOf(n);
		commSetting.setShowNum(showNum);

		// ===============ͨ������ end ============

		String[] qtzd = new String[] { "xfprs" };
		String[] qtzdz = new String[] { String.valueOf(Integer
				.parseInt(showNum) + 1) };

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);

		rForm.setDoType(doType);
		rForm.setGnmk(gnmk);
		rForm.setMenu(menu);
		rForm.setPath(path);
		rForm.setColList(colList);
		rForm.setTopTr(topTr);
		rForm.setSearch(search);
		rForm.setCommSetting(commSetting);

	}
	
	/**
	 * ��ÿ�������ͳ�Ʊ�ͷ
	 * @author qlj
	 */
	private List<HashMap<String, String>> getKxssTjTopTr(GyglZsjgForm model,
			String[] colList, MessageResources message) {

		String xqmc = message.getMessage("lable.xiaoqu") + "����";
		String yqmc = message.getMessage("lable.yuanqu") + "����";
 		String lddm = message.getMessage("lable.ld") + "����";
		String ldmc = message.getMessage("lable.ld") + "����";
		String[] colListCN = null;

		colListCN = new String[] { lddm,xqmc, yqmc, ldmc, "�Ա��޶�", "��������", "��������" };
		
		DAO dao = DAO.getInstance();

		List<HashMap<String, String>> topTr = dao.arrayToList(colList,
				colListCN);

		return topTr;
	}
	
	/**
	 * ���������ʼ��
	 * @param request
	 * @author qlj
	 * 
	 */
	public void iniKqsxxForm(RequestForm rForm, GyglZsjgForm model,
			HttpServletRequest request,MessageResources message) {

		// ����ģ��
		String gnmk = "gygl";
		// ϵͳ�ֶ�����
		String menu = "zsjg";
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ����·��
		String path = "gygl_zsjg_kxss.do";
		// ����ֶ�
		
		String[] colList ={"cs","qsh","cws","xbxd","bmmc"};
	
		//��ͷ
		List<HashMap<String, String>> topTr = getKxqsTopTr(model,colList, message,"kqs");
		// �Ƿ��ѯ����
		String search = !"go".equalsIgnoreCase(request.getParameter("go")) ? "false"
				: "true";

		// ===============ͨ������=================
		CommSetting commSetting = new CommSetting();

		// ���������
		String rsName = "rs";
		commSetting.setRsName(rsName);

		// �Ƿ���Ҫcheckbox
		String isCheckBox = "no";
		commSetting.setIsCheckBox(isCheckBox);

		// ��ʾ����ʼ��
		String startNum = "0";
		commSetting.setStartNum(startNum);

		// ��ʾ����
		String showNum =String.valueOf(topTr.size());
		commSetting.setShowNum(showNum);

		// ===============ͨ������ end ============

		String[] qtzd = new String[] { "xfprs" };
		String[] qtzdz = new String[] { String.valueOf(Integer
				.parseInt(showNum) + 1) };

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);

		rForm.setDoType(doType);
		rForm.setGnmk(gnmk);
		rForm.setMenu(menu);
		rForm.setPath(path);
		rForm.setColList(colList);
		rForm.setTopTr(topTr);
		rForm.setSearch(search);
		rForm.setCommSetting(commSetting);

	}
	
	/**
	 * ��ÿ�������ͳ�Ʊ�ͷ
	 * @author qlj
	 */
	private List<HashMap<String, String>> getKxqsTopTr(GyglZsjgForm model,
			String[] colList, MessageResources message,String mklx) {
		
		String cs = message.getMessage("lable.cs");
		String qs = message.getMessage("lable.qs") + "��";

		String[] colListCN = null;

		if ("kqs".equalsIgnoreCase(mklx)) {
			colListCN = new String[] { cs, qs, "��λ��", "�����Ա�", "���䲿��" };
		} else if ("xqs".equalsIgnoreCase(mklx)) {
			colListCN = new String[] { cs, qs, "��λ��", "�����Ա�", "�շѱ�׼", "���䲿��" };
		}

		DAO dao = DAO.getInstance();

		List<HashMap<String, String>> topTr = dao.arrayToList(colList,
				colListCN);

		return topTr;
	}
	
	/**
	 * ���������ʼ��
	 * @param request
	 * @author qlj
	 * 
	 */
	public void iniXqsxxForm(RequestForm rForm, GyglZsjgForm model,
			HttpServletRequest request,MessageResources message) {

		// ����ģ��
		String gnmk = "gygl";
		// ϵͳ�ֶ�����
		String menu = "zsjg";
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ����·��
		String path = "gygl_zsjg_kxss.do";
		//����У��
		String czxq=model.getCzxq();
		//����԰��
		String czyq=model.getCzyq();
		
		// ����ֶ�
		
		String[] colList ={"cs","qsh","cwh","xb","sfbz","bmmc"};
	
		//��ͷ
		List<HashMap<String, String>> topTr = getKxqsTopTr(model,colList, message,"xqs");
		// �Ƿ��ѯ����
		String search = !"go".equalsIgnoreCase(request.getParameter("go")) ? "false"
				: "true";

		// ===============ͨ������=================
		CommSetting commSetting = new CommSetting();

		// ���������
		String rsName = "rs";
		commSetting.setRsName(rsName);

		// �Ƿ���Ҫcheckbox
		String isCheckBox = "no";
		commSetting.setIsCheckBox(isCheckBox);

		// ��ʾ����ʼ��
		String startNum = "0";
		commSetting.setStartNum(startNum);

		// ��ʾ����
		String showNum =String.valueOf(topTr.size());
		commSetting.setShowNum(showNum);

		// ===============ͨ������ end ============

		String[] qtzd = new String[] { "xfprs" };
		String[] qtzdz = new String[] { String.valueOf(Integer
				.parseInt(showNum) + 1) };

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);

		rForm.setDoType(doType);
		rForm.setGnmk(gnmk);
		rForm.setMenu(menu);
		rForm.setPath(path);
		rForm.setColList(colList);
		rForm.setTopTr(topTr);
		rForm.setSearch(search);
		rForm.setCommSetting(commSetting);

	}
	
	
	/**
	 * ������ס���_��ʼ������
	 * 
	 * @param request
	 * @author qlj
	 * 
	 */
	public void getQsrzqkRForm(RequestForm rForm, GyglZsjgForm model,
			HttpServletRequest request) {

		GyglZsjgService service = new GyglZsjgService();

		// ����ģ��
		String gnmk = "gygl";
		// ϵͳ�ֶ�����
		String menu = "zsxx";
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ����·��
		String path = "gygl_zsjg_qsrzqk.do";
		// ��
		String tableName = "xszslsxxb";
		// ����ֶ�
		String[] colList = { "ldmc", "qsh", "xb", "xh" };
		// ��ͷ
		List<HashMap<String, String>> topTr = getTopTr(colList, "qsrzqk");

		// �Ƿ��ѯ����
		String search = !"go".equalsIgnoreCase((request.getParameter("go"))) ? "false"
				: "true";

		String[] qtzd = new String[] { "maxCws" };
		String[] qtzdz = new String[] { service.getMaxCws() };
		
		// ===============ͨ������=================
		CommSetting commSetting = new CommSetting();

		// ���������
		String rsName = "rsArrList";
		commSetting.setRsName(rsName);

		// �Ƿ���Ҫcheckbox
		String isCheckBox = "no";
		commSetting.setIsCheckBox(isCheckBox);

		// ��ʾ����ʼ��
		String startNum = "0";
		commSetting.setStartNum(startNum);
		
		//��ʾ����
		String showNum = "3";
		commSetting.setShowNum(showNum);
		
		// ===============ͨ������ end ============

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setDoType(doType);
		rForm.setGnmk(gnmk);
		rForm.setMenu(menu);
		rForm.setPath(path);
		rForm.setColList(colList);
		rForm.setTopTr(topTr);
		rForm.setSearch(search);
		rForm.setCommSetting(commSetting);

	}
}
