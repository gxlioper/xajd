/*
 * �������� 2006-8-1
 *
 *  Ҫ���Ĵ����ɵ��ļ���ģ�壬��ת��
 * ���� �� ��ѡ�� �� Java �� ������ʽ �� ����ģ��
 */
package xgxt.base;

/**
 * @author bat_zzj
 * 
 *  Ҫ���Ĵ����ɵ�����ע�͵�ģ�壬��ת�� ���� �� ��ѡ�� �� Java �� ������ʽ �� ����ģ��
 */
public class RsPages {
    private int rowCount; // �ܵļ�¼��

    private int pageSize; // ÿҳ��ʾ�ļ�¼��

    private int showPage; // ����Ԥ��ʾ��ҳ����

    private int pageCount; // ��ҳ֮�����ҳ��

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
	// �����ҳ֮�����ҳ��
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
