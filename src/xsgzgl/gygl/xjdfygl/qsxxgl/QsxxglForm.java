package xsgzgl.gygl.xjdfygl.qsxxgl;

import com.zfsoft.xgxt.comm.export.model.ExportModel;
import xgxt.comm.search.SearchModel;
import xgxt.form.BaseForm;
import xgxt.utils.Pages;

/**
 * @������TODO
 * @���ߣ�WANCHEN
 * @���ڣ�
 */
public class QsxxglForm extends BaseForm {
    private ExportModel exportModel = new ExportModel();
    private SearchModel searchModel = new SearchModel();
    private Pages pages = new Pages();
    private String type;
    /**
     * У������
     */
    private String xqdm;

    private String xqmc;

    /**
     * ¥������
     */
    private String lddm;

    /**
     * ¥������
     */
    private String ldmc;
    /**
     * ���Һ�
     */
    private String qsh;
    private String qss;//������
    /**
     * ���
     */
    private String ch;
    /**
     *��������
     */
    private String fjlx;
    /**
     * ��������
     */
    private String fjzx;
    /**
     * �����Ա�
     */
    private String qsxb;
    /**
     * ��λ��
     */
    private String cws;
    /**
     * �շѱ�׼
     */
    private String sfbz;
    /**
     * ���ҵ绰
     */
    private String qsdh;
    /**
     * �Ƿ��пյ�
     */
    private String sfykt;
    /**
     * �Ƿ���������
     */
    private String sfywsj;
    /**
     * ������λ�ã�01�����ڣ�02����̨
     */
    private String wsjwz;
    /**
     * ����������
     */
    private String wsjzx;
    /**
     * ����ѧԺ����
     */
    private String xydmId;
    /**
     * �����꼶
     */
    private String njdmId;
    /**
     * ��ע
     */
    private String bz;

    private String sfcshcw;

    private String pks;//��������������

    public String getSfcshcw() {
        return sfcshcw;
    }

    public void setSfcshcw(String sfcshcw) {
        this.sfcshcw = sfcshcw;
    }

    public String getPks() {
        return pks;
    }

    public void setPks(String pks) {
        this.pks = pks;
    }

    public String getQss() {
        return qss;
    }

    public void setQss(String qss) {
        this.qss = qss;
    }

    public String getXqmc() {
        return xqmc;
    }

    public void setXqmc(String xqmc) {
        this.xqmc = xqmc;
    }

    public ExportModel getExportModel() {
        return exportModel;
    }

    public void setExportModel(ExportModel exportModel) {
        this.exportModel = exportModel;
    }

    public SearchModel getSearchModel() {
        return searchModel;
    }

    public void setSearchModel(SearchModel searchModel) {
        this.searchModel = searchModel;
    }

    @Override
    public Pages getPages() {
        return pages;
    }

    @Override
    public void setPages(Pages pages) {
        this.pages = pages;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getXqdm() {
        return xqdm;
    }

    public void setXqdm(String xqdm) {
        this.xqdm = xqdm;
    }

    public String getLddm() {
        return lddm;
    }

    public void setLddm(String lddm) {
        this.lddm = lddm;
    }

    public String getLdmc() {
        return ldmc;
    }

    public void setLdmc(String ldmc) {
        this.ldmc = ldmc;
    }

    public String getQsh() {
        return qsh;
    }

    public void setQsh(String qsh) {
        this.qsh = qsh;
    }

    public String getCh() {
        return ch;
    }

    public void setCh(String ch) {
        this.ch = ch;
    }

    public String getFjlx() {
        return fjlx;
    }

    public void setFjlx(String fjlx) {
        this.fjlx = fjlx;
    }

    public String getFjzx() {
        return fjzx;
    }

    public void setFjzx(String fjzx) {
        this.fjzx = fjzx;
    }

    public String getQsxb() {
        return qsxb;
    }

    public void setQsxb(String qsxb) {
        this.qsxb = qsxb;
    }

    public String getCws() {
        return cws;
    }

    public void setCws(String cws) {
        this.cws = cws;
    }

    public String getSfbz() {
        return sfbz;
    }

    public void setSfbz(String sfbz) {
        this.sfbz = sfbz;
    }

    public String getQsdh() {
        return qsdh;
    }

    public void setQsdh(String qsdh) {
        this.qsdh = qsdh;
    }

    public String getSfykt() {
        return sfykt;
    }

    public void setSfykt(String sfykt) {
        this.sfykt = sfykt;
    }

    public String getSfywsj() {
        return sfywsj;
    }

    public void setSfywsj(String sfywsj) {
        this.sfywsj = sfywsj;
    }

    public String getWsjwz() {
        return wsjwz;
    }

    public void setWsjwz(String wsjwz) {
        this.wsjwz = wsjwz;
    }

    public String getWsjzx() {
        return wsjzx;
    }

    public void setWsjzx(String wsjzx) {
        this.wsjzx = wsjzx;
    }

    public String getXydmId() {
        return xydmId;
    }

    public void setXydmId(String xydmId) {
        this.xydmId = xydmId;
    }

    public String getNjdmId() {
        return njdmId;
    }

    public void setNjdmId(String njdmId) {
        this.njdmId = njdmId;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }
}
