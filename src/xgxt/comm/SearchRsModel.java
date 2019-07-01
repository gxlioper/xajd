package xgxt.comm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SearchRsModel {

	private List<HashMap<String, String>> topTr;// 标题

	private ArrayList<String[]> rsArrList;// 结果集

	private String spHtml;// 特殊构建语句

	private String checkBox;// 多选框有无

	private String checkBoxRs;// 多选框有无

	private String left;// 侧边栏有无

	private String ie;// ie版本

	private String rows;// 行数

	private String spaceRow;// 补空行

	private String noSpace;// 不补空行

	private String stylePath;// 样式路径

	private String arrange;// 是否需要排序

	private String showTitle; // 是否需要显示“查询结果”

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
