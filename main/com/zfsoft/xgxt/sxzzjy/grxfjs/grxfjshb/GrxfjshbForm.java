package com.zfsoft.xgxt.sxzzjy.grxfjs.grxfjshb;

import com.zfsoft.xgxt.comm.export.model.ExportModel;
import org.apache.struts.action.ActionForm;
import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/**
 * @�๦������:
 * @���ߣ� lgx [����:1553]
 * @ʱ�䣺 2018-08-02 17:22
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class GrxfjshbForm extends ActionForm {
    private static final long serialVersionUID = 1L;

    private String sqid;
    private String jgid;//����ѧ�罨����id
    private String hblx;//�㱨���ͣ�nzhb���ڻ㱨��nzzj�����ܽ�
    private String zd1;//�㱨����Ϊ���ڻ㱨ʱ����ʾ��һѧ�ڿγ̳ɼ���Ϊ�����ܽ�ʱ�����ʾ��ѧ��γ̳ɼ�
    private String zd2;//�㱨����Ϊ���ڻ㱨ʱ����ʾ�����ܽᣬΪ�����ܽ�ʱ�����ʾ��ȸ��˽����ܽ�
    private String zd3;//�㱨����Ϊ���ڻ㱨ʱ����ʾ���˽���������������Ϊ�����ܽ�ʱ�����ʾ���˽�����̵����¼�
    private String zd4;//�㱨����Ϊ���ڻ㱨ʱ����ʾ���˽������ķ�����Ϊ�����ܽ�ʱ�����ʾ���˻���ϸ
    private String sqsj; //����ʱ��
    private String sqr; //������
    private String shzt; //���״̬'0','δ�ύ','1','ͨ��','2','��ͨ��', '3','�˻�','4','������','5','�����'
    private String splc; //��������

    private String pjxfj;// ƽ��ѧ�ֻ�
    private String pjxfjpm; // ƽ��ѧ�ֻ�����
    private String zhcpcj; //�ۺϲ����ɼ�
    private String zhcpcjpm;// �ۺϲ����ɼ�����
    private String njbfb; //�꼶�ٷֱ�
    private String qt; // ����


    private String xh; //ѧ��

    private String type;
    private ExportModel exportModel = new ExportModel();
    private Pages pages = new Pages();
    private SearchModel searchModel = new SearchModel();

    public String getXh() {
        return xh;
    }

    public void setXh(String xh) {
        this.xh = xh;
    }

    public String getSqid() {
        return sqid;
    }

    public void setSqid(String sqid) {
        this.sqid = sqid;
    }

    public String getJgid() {
        return jgid;
    }

    public void setJgid(String jgid) {
        this.jgid = jgid;
    }

    public String getHblx() {
        return hblx;
    }

    public void setHblx(String hblx) {
        this.hblx = hblx;
    }

    public String getZd1() {
        return zd1;
    }

    public void setZd1(String zd1) {
        this.zd1 = zd1;
    }

    public String getZd2() {
        return zd2;
    }

    public void setZd2(String zd2) {
        this.zd2 = zd2;
    }

    public String getZd3() {
        return zd3;
    }

    public void setZd3(String zd3) {
        this.zd3 = zd3;
    }

    public String getZd4() {
        return zd4;
    }

    public void setZd4(String zd4) {
        this.zd4 = zd4;
    }

    public String getSqsj() {
        return sqsj;
    }

    public void setSqsj(String sqsj) {
        this.sqsj = sqsj;
    }

    public String getSqr() {
        return sqr;
    }

    public void setSqr(String sqr) {
        this.sqr = sqr;
    }

    public String getShzt() {
        return shzt;
    }

    public void setShzt(String shzt) {
        this.shzt = shzt;
    }

    public String getSplc() {
        return splc;
    }

    public void setSplc(String splc) {
        this.splc = splc;
    }

    public String getPjxfj() {
        return pjxfj;
    }

    public void setPjxfj(String pjxfj) {
        this.pjxfj = pjxfj;
    }

    public String getPjxfjpm() {
        return pjxfjpm;
    }

    public void setPjxfjpm(String pjxfjpm) {
        this.pjxfjpm = pjxfjpm;
    }

    public String getZhcpcj() {
        return zhcpcj;
    }

    public void setZhcpcj(String zhcpcj) {
        this.zhcpcj = zhcpcj;
    }

    public String getZhcpcjpm() {
        return zhcpcjpm;
    }

    public void setZhcpcjpm(String zhcpcjpm) {
        this.zhcpcjpm = zhcpcjpm;
    }

    public String getNjbfb() {
        return njbfb;
    }

    public void setNjbfb(String njbfb) {
        this.njbfb = njbfb;
    }

    public String getQt() {
        return qt;
    }

    public void setQt(String qt) {
        this.qt = qt;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ExportModel getExportModel() {
        return exportModel;
    }

    public void setExportModel(ExportModel exportModel) {
        this.exportModel = exportModel;
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
}
