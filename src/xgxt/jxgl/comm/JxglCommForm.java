package xgxt.jxgl.comm;

import java.util.HashMap;
import java.util.List;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

import xgxt.comm.search.SearchModel;
import xgxt.comm.xml.XMLReader;
import xgxt.jxgl.comm.jxbz.JxglJxbzForm;
import xgxt.jxgl.comm.jxbz.JxglJxbzService;
import xgxt.utils.Pages;

public class JxglCommForm extends ActionForm {

	private static final long serialVersionUID = 1L;

	// ��ҳ
	Pages pages = new Pages();

	// �߼���ѯ
	SearchModel searchModel = new SearchModel();

	FormFile uploadFile;// �ϴ��ļ�

	// ��ѡ��
	private String[] checkVal;

	// ����
	private String[] pk;

	private String xn;// ѧ��

	private String nj;// �꼶

	private String xydm;// Ժϵ

	private String zydm;// רҵ

	private String bjdm;// �༶

	private String maxBz;// ������

	private String minBz;// ��С����

	private String secBz;// �ڶ�����

	private String thiBz;// ��������

	private String fouBz;// ���ı���

	List<HashMap<String, String>> jxbzdjList;// �����б�

	public static JxglCommForm jxglCommForm;

	static {
		jxglCommForm = new JxglCommForm();
		JxglJxbzService service = new JxglJxbzService();

		jxglCommForm.setMaxBz(service.getBzdj("min"));
		jxglCommForm.setMinBz(service.getBzdj("max"));

		// ��ѵ���Ƶȼ��б�
		List<HashMap<String, String>> jxbzdjList = service.getWhList("jxjzdj",
				"jzdm", "jzmc", "", "", "");

		jxglCommForm.setJxbzdjList(jxbzdjList);

		if (jxbzdjList != null && jxbzdjList.size() > 0) {
			for (int i = 0; i < jxbzdjList.size(); i++) {
				String jzdm = jxbzdjList.get(i).get("dm");
				if (i == 2) {
					jxglCommForm.setSecBz(jzdm);
				} else if (i == 3) {
					jxglCommForm.setThiBz(jzdm);
				} else if (i == 4) {
					jxglCommForm.setFouBz(jzdm);
				}
			}
		}

	}

	public String[] getCheckVal() {
		return checkVal;
	}

	public void setCheckVal(String[] checkVal) {
		this.checkVal = checkVal;
	}

	public Pages getPages() {
		return pages;
	}

	public void setPages(Pages pages) {
		this.pages = pages;
	}

	public SearchModel getSearchModel() {
		return searchModel;
	}

	public void setSearchModel(SearchModel searchModel) {
		this.searchModel = searchModel;
	}

	public FormFile getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(FormFile uploadFile) {
		this.uploadFile = uploadFile;
	}

	public String getXn() {
		return xn;
	}

	public void setXn(String xn) {
		this.xn = xn;
	}

	public String getMaxBz() {
		return maxBz;
	}

	public void setMaxBz(String maxBz) {
		this.maxBz = maxBz;
	}

	public String getMinBz() {
		return minBz;
	}

	public void setMinBz(String minBz) {
		this.minBz = minBz;
	}

	public List<HashMap<String, String>> getJxbzdjList() {
		return jxbzdjList;
	}

	public void setJxbzdjList(List<HashMap<String, String>> jxbzdjList) {
		this.jxbzdjList = jxbzdjList;
	}

	public String getSecBz() {
		return secBz;
	}

	public void setSecBz(String secBz) {
		this.secBz = secBz;
	}

	public String getFouBz() {
		return fouBz;
	}

	public void setFouBz(String fouBz) {
		this.fouBz = fouBz;
	}

	public String getThiBz() {
		return thiBz;
	}

	public void setThiBz(String thiBz) {
		this.thiBz = thiBz;
	}

	public String getBjdm() {
		return bjdm;
	}

	public void setBjdm(String bjdm) {
		this.bjdm = bjdm;
	}

	public String getNj() {
		return nj;
	}

	public void setNj(String nj) {
		this.nj = nj;
	}

	public String getXydm() {
		return xydm;
	}

	public void setXydm(String xydm) {
		this.xydm = xydm;
	}

	public String getZydm() {
		return zydm;
	}

	public void setZydm(String zydm) {
		this.zydm = zydm;
	}

	public String[] getPk() {
		return pk;
	}

	public void setPk(String[] pk) {
		this.pk = pk;
	}

}
