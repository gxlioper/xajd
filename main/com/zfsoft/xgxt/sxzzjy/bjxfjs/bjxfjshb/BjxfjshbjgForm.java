package com.zfsoft.xgxt.sxzzjy.bjxfjs.bjxfjshb;

import com.zfsoft.xgxt.comm.export.model.ExportModel;
import org.apache.struts.action.ActionForm;
import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/**
 * @�๦������:�༶ѧ�罨��㱨���
 * @���ߣ� lgx [����:1553]
 * @ʱ�䣺 2018-07-30 14:47
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class BjxfjshbjgForm extends ActionForm {
    private static final long serialVersionUID = 1L;

    private String id;
    private String jgid;//�༶ѧ�罨����id
    private String hblx;//�㱨���ͣ�nzhb���ڻ㱨��nzzj�����ܽ�
    private String zd1;//�㱨����Ϊ���ڻ㱨ʱ����ʾ�����ܽᣬΪ�����ܽ�ʱ�����ʾ��Ȱ༶�����ܽ�
    private String zd2;//�㱨����Ϊ���ڻ㱨ʱ����ʾ�༶����������������Ϊ�����ܽ�ʱ�����ʾ�༶������̵����¼�
    private String zd3;//�㱨����Ϊ���ڻ㱨ʱ����ʾ�༶�������ķ�����Ϊ�����ܽ�ʱ�����ʾ�༶����͸��˻���ϸ
    private String lrsj;//¼��ʱ��
    private String lrr;//¼����
    private String sjly;//������Դ 0��������� 1�����������
    private String lcywid;//����ҵ��id

    private String  pjxfj; //�༶ƽ��ѧ�ֻ�
    private String njzy; //�꼶��רҵ��
    private String  zyxbgs; //�꼶��רҵ��С�����
    private String pjxfjpm; //�༶ƽ��ѧ�ֻ���С������
    private String sjtgl; //Ӣ���ļ�ͨ����
    private String njxbgs; //�꼶С�����
    private String sjtglpm; //Ӣ���ļ�ͨ������С������
    private String bjgmc; //�������Ŵ�
    private String bjgrs; //����������
    private String bjgrc; //�������˴�
    private String bgbqwrs; //��ɲ�ѧϰ�ɼ���ǰ��������
    private String bgbqsrs; //��ɲ�ѧϰ�ɼ���ǰʮ������
    private String hjxsrs; //��ѧ������
    private String hjtjgs; //���影����
    private String sshjcs; //����񽱴���
    private String shsjhjrc; //���ʵ�����˴�
    private String kjxshjrc; //�Ƽ�ѧ�����˴�
    private String qbjthdcs; //ȫ�༯������
    private String cjxyhdcs; //�μ�УԺ�����
    private String jjtxrs; //����ͬѧ����
    private String sdxsrs; //�Զ�ѧ������
    private String txrs; //��ѧ����


    private String type;
    private ExportModel exportModel = new ExportModel();
    private Pages pages = new Pages();
    private SearchModel searchModel = new SearchModel();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getLrsj() {
        return lrsj;
    }

    public void setLrsj(String lrsj) {
        this.lrsj = lrsj;
    }

    public String getLrr() {
        return lrr;
    }

    public void setLrr(String lrr) {
        this.lrr = lrr;
    }

    public String getSjly() {
        return sjly;
    }

    public void setSjly(String sjly) {
        this.sjly = sjly;
    }

    public String getPjxfj() {
        return pjxfj;
    }

    public void setPjxfj(String pjxfj) {
        this.pjxfj = pjxfj;
    }

    public String getNjzy() {
        return njzy;
    }

    public void setNjzy(String njzy) {
        this.njzy = njzy;
    }

    public String getZyxbgs() {
        return zyxbgs;
    }

    public void setZyxbgs(String zyxbgs) {
        this.zyxbgs = zyxbgs;
    }

    public String getPjxfjpm() {
        return pjxfjpm;
    }

    public void setPjxfjpm(String pjxfjpm) {
        this.pjxfjpm = pjxfjpm;
    }

    public String getSjtgl() {
        return sjtgl;
    }

    public void setSjtgl(String sjtgl) {
        this.sjtgl = sjtgl;
    }

    public String getNjxbgs() {
        return njxbgs;
    }

    public void setNjxbgs(String njxbgs) {
        this.njxbgs = njxbgs;
    }

    public String getSjtglpm() {
        return sjtglpm;
    }

    public void setSjtglpm(String sjtglpm) {
        this.sjtglpm = sjtglpm;
    }

    public String getBjgmc() {
        return bjgmc;
    }

    public void setBjgmc(String bjgmc) {
        this.bjgmc = bjgmc;
    }

    public String getBjgrs() {
        return bjgrs;
    }

    public void setBjgrs(String bjgrs) {
        this.bjgrs = bjgrs;
    }

    public String getBjgrc() {
        return bjgrc;
    }

    public void setBjgrc(String bjgrc) {
        this.bjgrc = bjgrc;
    }

    public String getBgbqwrs() {
        return bgbqwrs;
    }

    public void setBgbqwrs(String bgbqwrs) {
        this.bgbqwrs = bgbqwrs;
    }

    public String getBgbqsrs() {
        return bgbqsrs;
    }

    public void setBgbqsrs(String bgbqsrs) {
        this.bgbqsrs = bgbqsrs;
    }

    public String getHjxsrs() {
        return hjxsrs;
    }

    public void setHjxsrs(String hjxsrs) {
        this.hjxsrs = hjxsrs;
    }

    public String getHjtjgs() {
        return hjtjgs;
    }

    public void setHjtjgs(String hjtjgs) {
        this.hjtjgs = hjtjgs;
    }

    public String getSshjcs() {
        return sshjcs;
    }

    public void setSshjcs(String sshjcs) {
        this.sshjcs = sshjcs;
    }

    public String getShsjhjrc() {
        return shsjhjrc;
    }

    public void setShsjhjrc(String shsjhjrc) {
        this.shsjhjrc = shsjhjrc;
    }

    public String getKjxshjrc() {
        return kjxshjrc;
    }

    public void setKjxshjrc(String kjxshjrc) {
        this.kjxshjrc = kjxshjrc;
    }

    public String getQbjthdcs() {
        return qbjthdcs;
    }

    public void setQbjthdcs(String qbjthdcs) {
        this.qbjthdcs = qbjthdcs;
    }

    public String getCjxyhdcs() {
        return cjxyhdcs;
    }

    public void setCjxyhdcs(String cjxyhdcs) {
        this.cjxyhdcs = cjxyhdcs;
    }

    public String getJjtxrs() {
        return jjtxrs;
    }

    public void setJjtxrs(String jjtxrs) {
        this.jjtxrs = jjtxrs;
    }

    public String getSdxsrs() {
        return sdxsrs;
    }

    public void setSdxsrs(String sdxsrs) {
        this.sdxsrs = sdxsrs;
    }

    public String getTxrs() {
        return txrs;
    }

    public void setTxrs(String txrs) {
        this.txrs = txrs;
    }

    public String getLcywid() {
        return lcywid;
    }

    public void setLcywid(String lcywid) {
        this.lcywid = lcywid;
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
