package com.zfsoft.xgxt.xlzx.xlwjga.xxlwjgy;

import com.zfsoft.xgxt.comm.export.model.ExportModel;
import org.apache.struts.action.ActionForm;
import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/**
 * @�๦������:
 * @���ߣ� lgx [����:1553]
 * @ʱ�䣺 2019-04-10 14:42
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class XlwjgyForm extends ActionForm {
    private static final long serialVersionUID = 1L;
    private String type;
    // ��ҳ
    Pages pages = new Pages();
    // �߼���ѯ
    private SearchModel searchModel = new SearchModel();
    //�Զ��嵼��
    private ExportModel exportModel = new ExportModel();

    private String id;
    private String xh;
    private String bgsj;//����ʱ��
    private String bgr;//������
    private String fxtj;//����;����fdy ����Ա��stu ѧ����tel �绰���ߡ�bbs BBs��zx ��ѯ��qq QQ��qt ����
    private String wjcd;//Σ���̶ȣ�fcjj �ǳ�������jj ������yb һ�㡢bj ����
    private String wjfzgc;//Σ����չ���̼�����
    private String wjgysj;//Σ����Ԥʱ��
    private String wjgyry;//Σ����Ԥ��Ա
    private String wjgyfs;//Σ����Ԥ��ʽ��xcgy �ֳ���Ԥ��zdxy ָ����Ժ/ѧԺ ��yjzx ����ԤԼ��ѯ��jyyl ����ҽ��
    private String xtbm;//Эͬ���ţ�sy ��Ժ/ѧԺ��gac ��������wlzx �������ġ�xyy УҽԺ��qt ����
    private String wjgyjg;//Σ����Ԥ�����sljj ˳�������ydgj �д�������jyyl ����ҽ�ơ�xx ��ѧ��tx ��ѧ��qt ����
    private String wjclgc;//Σ��������̼���ʩ
    private String lrr;//¼����
    private String lrsj;//¼��ʱ��

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public ExportModel getExportModel() {
        return exportModel;
    }

    public void setExportModel(ExportModel exportModel) {
        this.exportModel = exportModel;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getXh() {
        return xh;
    }

    public void setXh(String xh) {
        this.xh = xh;
    }

    public String getBgsj() {
        return bgsj;
    }

    public void setBgsj(String bgsj) {
        this.bgsj = bgsj;
    }

    public String getBgr() {
        return bgr;
    }

    public void setBgr(String bgr) {
        this.bgr = bgr;
    }

    public String getFxtj() {
        return fxtj;
    }

    public void setFxtj(String fxtj) {
        this.fxtj = fxtj;
    }

    public String getWjcd() {
        return wjcd;
    }

    public void setWjcd(String wjcd) {
        this.wjcd = wjcd;
    }

    public String getWjfzgc() {
        return wjfzgc;
    }

    public void setWjfzgc(String wjfzgc) {
        this.wjfzgc = wjfzgc;
    }

    public String getWjgysj() {
        return wjgysj;
    }

    public void setWjgysj(String wjgysj) {
        this.wjgysj = wjgysj;
    }

    public String getWjgyry() {
        return wjgyry;
    }

    public void setWjgyry(String wjgyry) {
        this.wjgyry = wjgyry;
    }

    public String getWjgyfs() {
        return wjgyfs;
    }

    public void setWjgyfs(String wjgyfs) {
        this.wjgyfs = wjgyfs;
    }

    public String getXtbm() {
        return xtbm;
    }

    public void setXtbm(String xtbm) {
        this.xtbm = xtbm;
    }

    public String getWjgyjg() {
        return wjgyjg;
    }

    public void setWjgyjg(String wjgyjg) {
        this.wjgyjg = wjgyjg;
    }

    public String getWjclgc() {
        return wjclgc;
    }

    public void setWjclgc(String wjclgc) {
        this.wjclgc = wjclgc;
    }

    public String getLrr() {
        return lrr;
    }

    public void setLrr(String lrr) {
        this.lrr = lrr;
    }

    public String getLrsj() {
        return lrsj;
    }

    public void setLrsj(String lrsj) {
        this.lrsj = lrsj;
    }
}
