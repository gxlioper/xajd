package xgxt.comm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SearchRsModel {

	private List<HashMap<String, String>> topTr;// ����

	private ArrayList<String[]> rsArrList;// �����

	private String spHtml;// ���⹹�����

	private String checkBox;// ��ѡ������

	private String checkBoxRs;// ��ѡ������

	private String left;// ���������

	private String ie;// ie�汾

	private String rows;// ����

	private String spaceRow;// ������

	private String noSpace;// ��������

	private String stylePath;// ��ʽ·��

	private String arrange;// �Ƿ���Ҫ����

	private String showTitle; // �Ƿ���Ҫ��ʾ����ѯ�����

	public String getArrange() {
		return arrange;
	}

	public void setArrange(String arrange) {
		this.arrange = arrange;
	}

	public String getStylePath() {
		return stylePath;
	}

	public void setStylePath(String stylePath) {
		this.stylePath = stylePath;
	}

	public String getCheckBoxRs() {
		return checkBoxRs;
	}

	public void setCheckBoxRs(String checkBoxRs) {
		this.checkBoxRs = checkBoxRs;
	}

	public String getIe() {
		return ie;
	}

	public void setIe(String ie) {
		this.ie = ie;
	}

	public String getCheckBox() {
		return checkBox;
	}

	public void setCheckBox(String checkBox) {
		this.checkBox = checkBox;
	}

	public ArrayList<String[]> getRsArrList() {
		return rsArrList;
	}

	public void setRsArrList(ArrayList<String[]> rsArrList) {
		this.rsArrList = rsArrList;
	}

	public List<HashMap<String, String>> getTopTr() {
		return topTr;
	}

	public void setTopTr(List<HashMap<String, String>> topTr) {
		this.topTr = topTr;
	}

	public String getSpHtml() {
		return spHtml;
	}

	public void setSpHtml(String spHtml) {
		this.spHtml = spHtml;
	}

	public String getLeft() {
		return left;
	}

	public void setLeft(String left) {
		this.left = left;
	}

	public String getRows() {
		return rows;
	}

	public void setRows(String rows) {
		this.rows = rows;
	}

	public String getSpaceRow() {
		return spaceRow;
	}

	public void setSpaceRow(String spaceRow) {
		this.spaceRow = spaceRow;
	}

	public String getNoSpace() {
		return noSpace;
	}

	public void setNoSpace(String noSpace) {
		this.noSpace = noSpace;
	}

	public String getShowTitle() {
		return showTitle;
	}

	public void setShowTitle(String showTitle) {
		this.showTitle = showTitle;
	}

}
