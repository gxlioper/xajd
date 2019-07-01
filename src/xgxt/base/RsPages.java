/*
 * 创建日期 2006-8-1
 *
 *  要更改此生成的文件的模板，请转至
 * 窗口 － 首选项 － Java － 代码样式 － 代码模板
 */
package xgxt.base;

/**
 * @author bat_zzj
 * 
 *  要更改此生成的类型注释的模板，请转至 窗口 － 首选项 － Java － 代码样式 － 代码模板
 */
public class RsPages {
    private int rowCount; // 总的记录数

    private int pageSize; // 每页显示的记录数

    private int showPage; // 设置预显示的页码数

    private int pageCount; // 分页之后的总页数

    public RsPages() {
	rowCount = 1;
	pageSize = 50;
	showPage = 1;
	pageCount = 1;
    }

    public int getPageCount() {
	return pageCount;
    }

    public void setPageCount(int r, int p) {
	this.rowCount = r;
	this.pageSize = p;
	this.pageCount = (rowCount % pageSize) == 0 ? (rowCount / pageSize)
		: (rowCount / pageSize + 1);
	// 计算分页之后的总页数
    }

    public int getPageSize() {
	return pageSize;
    }

    public void setPageSize(int pageSize) {
	this.pageSize = pageSize;
    }

    public int getRowCount() {
	return rowCount;
    }

    public void setRowCount(int rowCount) {
	this.rowCount = rowCount;
    }

    public int getShowPage() {
	return showPage;
    }

    public void setShowPage(int showPage) {
	this.showPage = showPage;
    }

}
