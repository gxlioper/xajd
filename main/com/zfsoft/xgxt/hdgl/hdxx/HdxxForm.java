/**
 * <p>Coyright (R) 2014 ��������ɷ����޹�˾��<p>
 */
package com.zfsoft.xgxt.hdgl.hdxx;

import org.apache.struts.action.ActionForm;

import org.apache.struts.upload.FormFile;
import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/**
 * @className	�� hdxxForm
 * @description	�� ���ϢFORM(��������������)
 * @author 		��������1282��
 * @date		�� 2018-1-30 ����05:14:54
 * @version 	V1.0 
 */

public class HdxxForm extends ActionForm{
	/**
	 * @fields ��serialVersionUID : TODO
	 */
	
	private static final long serialVersionUID = -7452747337277175219L;


	private String hdxs;
	private String fjpath;
	private String nlbq;//������ǩ
	private String jzlx;//�γ̼���
	private String zxkclx;//��ѡ�γ�����
	private String xsxxlx;//������������
	private String hdkclx;//��γ����ͣ��������׶Σ������൥�׶Σ�
	private String[] nlbqs;

	private String zbf;//���췽
	private String zjrxm;//����������
	private String zjrdw;//�����˵�λ
	private String zjrzc;//������ְ��
	private String zjrzw;//������ְ��
	private String zjrjs;//�����˽���
	private String jzjb;//��������

	private String sfdektxf;//�Ƿ��еڶ�����ѧ��
	private String qdkssj;//ǩ����ʼʱ��
	private String qdjssj;//ǩ������ʱ��
	private String qtkssj;//ǩ�˿�ʼʱ��
	private String qtjssj;//ǩ�˽���ʱ��

	
	private String hdid;
	private String hdmc;
	private String fbf;
	private String fbsj;
	private String hdkssj;
	private String hdjssj;
	private String hddd;
	private String hdlx;
	private String hb;
	private String nryq;
	private String bmkssj;
	private String bmjssj;
	private String bmdx;
	private String bmrs;
	private String bmlx;
	private String gzh;
	private String xh;
	private String xn;
	private String xq;
	private String lrsj;
	private String pxfs;
	private Pages pages = new Pages();
	private String hdbq;
	private String shzt;
	private String cxzd;
	private String[] ids;
	private String id;
	private String jdid;
	private String guid;
	private String xdth;
	private String fjid;
	private String plid;

	private String hdjxzt;
	private String hdzt;
	private String plnr;
	
	private SearchModel searchModel = new SearchModel();
	//�Զ��嵼��
	private ExportModel exportModel = new ExportModel();
	private String type;
	private String [] zghs;
	private String dwid;
	private String dwzw;
	private String hdsqid;
	private String hfbj;
	private String hfrid;
	private String hfrxm;
	private String hfplid;
	private String psfs;	//ר���������
	private String prejdid; //ǰһ�׶�id
	private String shyj;	//������
	private String jxid;	//����id
	private String xf;	//ѧ��
	private String df;//���
	private String zgh;	//ְ����
	private String hdbqs[];//���ǩ

	private String jdlxs[];//�׶�����
	private String jdmcs[];//�׶�����
	private String jdids[];//�׶�ids
	private String jdkssjs[];//�׶ο�ʼʱ��
	private String jdjssjs[]; //�׶ν���ʱ��
	private String hdxf;//�ѧ��
	private String[] sfsljxs;
	private String[] sfslxfs;
	private String[] sfsldfs;
	private String[] sftjs;//�Ƿ�׶���ת
	private String[] jxmcs;//��������
	private String[] jxsms;//����˵��
	private String ppnum;//��Ʊ����
	private String pptype;//��Ʊ���� 0�������Ʊ��1���ȵ��ȵã�2��ѡȡ���籨��xx�ˣ����������ȡ
	private String[] param;//�����ID {0:"00001",1:"00002"}
	private String bmzzrs;//������������
	private String bmsf;//�Ƿ���Ҫ����
	private String bmsfsh;//�����Ƿ���Ҫ���
	private String dwrs;//��������
	private FormFile stuPic;
	private String gzjl;//��������
	private String zyzjl;//־Ը�߾���
	private String yynl;//��������
	private String zwms;//��������
	private String zyjn;//ְҵ����
    private String bmfj;//��������
	private String[] btzd;
	private String sflx;//�Ƿ�����
	private String mc;
	private String jb;
	private String zddw;
	private String zdjs;
	private String nrjs;
	private String sqid;
	private String nextjdid;
	private String dzxm;
	private String hdpp; //���˻-�Ƿ���Ʊ
	private String zdhdpp; //��ӻ-�Ƿ���Ʊ
    private String hdbmshzt;//��������״̬
    private String zdhdbmshzt;//��ӻ�������״̬
	private String bmjbxxxs; //����������Ϣ��ʾ�����ڿ��ƻ�����Ϣ����ʾ���ݣ�

	public String getBmjbxxxs() {
		return bmjbxxxs;
	}

	public void setBmjbxxxs(String bmjbxxxs) {
		this.bmjbxxxs = bmjbxxxs;
	}

	public String getHdbmshzt() {
        return hdbmshzt;
    }

    public void setHdbmshzt(String hdbmshzt) {
        this.hdbmshzt = hdbmshzt;
    }

    public String getZdhdbmshzt() {
        return zdhdbmshzt;
    }

    public void setZdhdbmshzt(String zdhdbmshzt) {
        this.zdhdbmshzt = zdhdbmshzt;
    }

    public String getHdpp() {
        return hdpp;
    }

    public void setHdpp(String hdpp) {
        this.hdpp = hdpp;
    }

    public String getZdhdpp() {
        return zdhdpp;
    }

    public void setZdhdpp(String zdhdpp) {
        this.zdhdpp = zdhdpp;
    }

    public String getDzxm() {
		return dzxm;
	}

	public void setDzxm(String dzxm) {
		this.dzxm = dzxm;
	}

	public String getBmsf() {
		return bmsf;
	}

	public void setBmsf(String bmsf) {
		this.bmsf = bmsf;
	}

	public String getSfdektxf() {
		return sfdektxf;
	}

	public void setSfdektxf(String sfdektxf) {
		this.sfdektxf = sfdektxf;
	}

	public String getQdkssj() {
		return qdkssj;
	}

	public void setQdkssj(String qdkssj) {
		this.qdkssj = qdkssj;
	}

	public String getQdjssj() {
		return qdjssj;
	}

	public void setQdjssj(String qdjssj) {
		this.qdjssj = qdjssj;
	}

	public String getQtkssj() {
		return qtkssj;
	}

	public void setQtkssj(String qtkssj) {
		this.qtkssj = qtkssj;
	}

	public String getQtjssj() {
		return qtjssj;
	}

	public void setQtjssj(String qtjssj) {
		this.qtjssj = qtjssj;
	}

	public String getHdxs() {
		return hdxs;
	}

	public void setHdxs(String hdxs) {
		this.hdxs = hdxs;
	}

	public String getFjpath() {
		return fjpath;
	}

	public void setFjpath(String fjpath) {
		this.fjpath = fjpath;
	}

	public String getNlbq() {
		return nlbq;
	}

	public void setNlbq(String nlbq) {
		this.nlbq = nlbq;
	}

	public String getJzlx() {
		return jzlx;
	}

	public void setJzlx(String jzlx) {
		this.jzlx = jzlx;
	}

	public String getZxkclx() {
		return zxkclx;
	}

	public void setZxkclx(String zxkclx) {
		this.zxkclx = zxkclx;
	}

	public String getXsxxlx() {
		return xsxxlx;
	}

	public void setXsxxlx(String xsxxlx) {
		this.xsxxlx = xsxxlx;
	}

	public String getHdkclx() {
		return hdkclx;
	}

	public void setHdkclx(String hdkclx) {
		this.hdkclx = hdkclx;
	}

	public String[] getNlbqs() {
		return nlbqs;
	}

	public void setNlbqs(String[] nlbqs) {
		this.nlbqs = nlbqs;
	}

	public String getZbf() {
		return zbf;
	}

	public void setZbf(String zbf) {
		this.zbf = zbf;
	}

	public String getZjrxm() {
		return zjrxm;
	}

	public void setZjrxm(String zjrxm) {
		this.zjrxm = zjrxm;
	}

	public String getZjrdw() {
		return zjrdw;
	}

	public void setZjrdw(String zjrdw) {
		this.zjrdw = zjrdw;
	}

	public String getZjrzc() {
		return zjrzc;
	}

	public void setZjrzc(String zjrzc) {
		this.zjrzc = zjrzc;
	}

	public String getZjrzw() {
		return zjrzw;
	}

	public void setZjrzw(String zjrzw) {
		this.zjrzw = zjrzw;
	}

	public String getZjrjs() {
		return zjrjs;
	}

	public void setZjrjs(String zjrjs) {
		this.zjrjs = zjrjs;
	}

	public String getJzjb() {
		return jzjb;
	}

	public void setJzjb(String jzjb) {
		this.jzjb = jzjb;
	}

	public String getNextjdid() {
		return nextjdid;
	}

	public void setNextjdid(String nextjdid) {
		this.nextjdid = nextjdid;
	}

	public String getSqid() {
		return sqid;
	}

	public void setSqid(String sqid) {
		this.sqid = sqid;
	}

	public String getJb() {
		return jb;
	}

	public void setJb(String jb) {
		this.jb = jb;
	}

	public String getMc() {
		return mc;
	}

	public void setMc(String mc) {
		this.mc = mc;
	}

	public String getZddw() {
		return zddw;
	}

	public void setZddw(String zddw) {
		this.zddw = zddw;
	}

	public String getZdjs() {
		return zdjs;
	}

	public void setZdjs(String zdjs) {
		this.zdjs = zdjs;
	}

	public String getNrjs() {
		return nrjs;
	}

	public void setNrjs(String nrjs) {
		this.nrjs = nrjs;
	}

	public String[] getSftjs() {
		return sftjs;
	}

	public void setSftjs(String[] sftjs) {
		this.sftjs = sftjs;
	}

	public String getSflx() {
		return sflx;
	}

	public void setSflx(String sflx) {
		this.sflx = sflx;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String[] getBtzd() {
		return btzd;
	}

	public void setBtzd(String[] btzd) {
		this.btzd = btzd;
	}

	public String getGzjl() {
		return gzjl;
	}

	public void setGzjl(String gzjl) {
		this.gzjl = gzjl;
	}

	public String getZyzjl() {
		return zyzjl;
	}

	public void setZyzjl(String zyzjl) {
		this.zyzjl = zyzjl;
	}

	public String getYynl() {
		return yynl;
	}

	public void setYynl(String yynl) {
		this.yynl = yynl;
	}

	public String getZwms() {
		return zwms;
	}

	public void setZwms(String zwms) {
		this.zwms = zwms;
	}

	public String getZyjn() {
		return zyjn;
	}

	public void setZyjn(String zyjn) {
		this.zyjn = zyjn;
	}

    public String getBmfj() {
        return bmfj;
    }

    public void setBmfj(String bmfj) {
        this.bmfj = bmfj;
    }

    public FormFile getStuPic() {
		return stuPic;
	}

	public void setStuPic(FormFile stuPic) {
		this.stuPic = stuPic;
	}

	public String getDwrs() {
		return dwrs;
	}

	public void setDwrs(String dwrs) {
		this.dwrs = dwrs;
	}

	public String getBmsfsh() {
		return bmsfsh;
	}

	public void setBmsfsh(String bmsfsh) {
		this.bmsfsh = bmsfsh;
	}

	public String getDf() {
		return df;
	}

	public void setDf(String df) {
		this.df = df;
	}

	public String[] getSfsldfs() {
		return sfsldfs;
	}

	public void setSfsldfs(String[] sfsldfs) {
		this.sfsldfs = sfsldfs;
	}

	public String getBmzzrs() {
		return bmzzrs;
	}

	public void setBmzzrs(String bmzzrs) {
		this.bmzzrs = bmzzrs;
	}

	public String[] getParam() {
        return param;
    }

    public void setParam(String[] param) {
        this.param = param;
    }

    public String getPpnum() {
        return ppnum;
    }

    public void setPpnum(String ppnum) {
        this.ppnum = ppnum;
    }

    public String getPptype() {
        return pptype;
    }

    public void setPptype(String pptype) {
        this.pptype = pptype;
    }

    /**
	 * @description	�� TODO
	 * @author 		�� ������1282��
	 * @date		�� 2018-1-30 ����05:20:59 
	 * @return		: the hdid
	 */
	public String getHdid() {
		return hdid;
	}
	/**
	 * @description	��  TODO
	 * @author 		�� ������1282��
	 * @date		�� 2018-1-30 ����05:20:59 
	 * @param 		��hdid the hdid to set
	 */
	public void setHdid(String hdid) {
		this.hdid = hdid;
	}
	/**
	 * @description	�� TODO
	 * @author 		�� ������1282��
	 * @date		�� 2018-1-30 ����05:20:59 
	 * @return		: the hdmc
	 */
	public String getHdmc() {
		return hdmc;
	}
	/**
	 * @description	��  TODO
	 * @author 		�� ������1282��
	 * @date		�� 2018-1-30 ����05:20:59 
	 * @param 		��hdmc the hdmc to set
	 */
	public void setHdmc(String hdmc) {
		this.hdmc = hdmc;
	}
	/**
	 * @description	�� TODO
	 * @author 		�� ������1282��
	 * @date		�� 2018-1-30 ����05:20:59 
	 * @return		: the fbf
	 */
	public String getFbf() {
		return fbf;
	}
	/**
	 * @description	��  TODO
	 * @author 		�� ������1282��
	 * @date		�� 2018-1-30 ����05:20:59 
	 * @param 		��fbf the fbf to set
	 */
	public void setFbf(String fbf) {
		this.fbf = fbf;
	}
	/**
	 * @description	�� TODO
	 * @author 		�� ������1282��
	 * @date		�� 2018-1-30 ����05:20:59 
	 * @return		: the fbsj
	 */
	public String getFbsj() {
		return fbsj;
	}
	/**
	 * @description	��  TODO
	 * @author 		�� ������1282��
	 * @date		�� 2018-1-30 ����05:20:59 
	 * @param 		��fbsj the fbsj to set
	 */
	public void setFbsj(String fbsj) {
		this.fbsj = fbsj;
	}
	/**
	 * @description	�� TODO
	 * @author 		�� ������1282��
	 * @date		�� 2018-1-30 ����05:20:59 
	 * @return		: the hdkssj
	 */
	public String getHdkssj() {
		return hdkssj;
	}
	/**
	 * @description	��  TODO
	 * @author 		�� ������1282��
	 * @date		�� 2018-1-30 ����05:20:59 
	 * @param 		��hdkssj the hdkssj to set
	 */
	public void setHdkssj(String hdkssj) {
		this.hdkssj = hdkssj;
	}
	/**
	 * @description	�� TODO
	 * @author 		�� ������1282��
	 * @date		�� 2018-1-30 ����05:20:59 
	 * @return		: the hdjssj
	 */
	public String getHdjssj() {
		return hdjssj;
	}
	/**
	 * @description	��  TODO
	 * @author 		�� ������1282��
	 * @date		�� 2018-1-30 ����05:20:59 
	 * @param 		��hdjssj the hdjssj to set
	 */
	public void setHdjssj(String hdjssj) {
		this.hdjssj = hdjssj;
	}
	/**
	 * @description	�� TODO
	 * @author 		�� ������1282��
	 * @date		�� 2018-1-30 ����05:20:59 
	 * @return		: the hddd
	 */
	public String getHddd() {
		return hddd;
	}
	/**
	 * @description	��  TODO
	 * @author 		�� ������1282��
	 * @date		�� 2018-1-30 ����05:20:59 
	 * @param 		��hddd the hddd to set
	 */
	public void setHddd(String hddd) {
		this.hddd = hddd;
	}
	/**
	 * @description	�� TODO
	 * @author 		�� ������1282��
	 * @date		�� 2018-1-30 ����05:20:59 
	 * @return		: the hdlx
	 */
	public String getHdlx() {
		return hdlx;
	}
	/**
	 * @description	��  TODO
	 * @author 		�� ������1282��
	 * @date		�� 2018-1-30 ����05:20:59 
	 * @param 		��hdlx the hdlx to set
	 */
	public void setHdlx(String hdlx) {
		this.hdlx = hdlx;
	}
	/**
	 * @description	�� TODO
	 * @author 		�� ������1282��
	 * @date		�� 2018-1-30 ����05:20:59 
	 * @return		: the hb
	 */
	public String getHb() {
		return hb;
	}
	/**
	 * @description	��  TODO
	 * @author 		�� ������1282��
	 * @date		�� 2018-1-30 ����05:20:59 
	 * @param 		��hb the hb to set
	 */
	public void setHb(String hb) {
		this.hb = hb;
	}
	/**
	 * @description	�� TODO
	 * @author 		�� ������1282��
	 * @date		�� 2018-1-30 ����05:20:59 
	 * @return		: the nryq
	 */
	public String getNryq() {
		return nryq;
	}
	/**
	 * @description	��  TODO
	 * @author 		�� ������1282��
	 * @date		�� 2018-1-30 ����05:20:59 
	 * @param 		��nryq the nryq to set
	 */
	public void setNryq(String nryq) {
		this.nryq = nryq;
	}
	/**
	 * @description	�� TODO
	 * @author 		�� ������1282��
	 * @date		�� 2018-1-30 ����05:20:59 
	 * @return		: the bmkssj
	 */
	public String getBmkssj() {
		return bmkssj;
	}
	/**
	 * @description	��  TODO
	 * @author 		�� ������1282��
	 * @date		�� 2018-1-30 ����05:20:59 
	 * @param 		��bmkssj the bmkssj to set
	 */
	public void setBmkssj(String bmkssj) {
		this.bmkssj = bmkssj;
	}
	/**
	 * @description	�� TODO
	 * @author 		�� ������1282��
	 * @date		�� 2018-1-30 ����05:20:59 
	 * @return		: the bmjssj
	 */
	public String getBmjssj() {
		return bmjssj;
	}
	/**
	 * @description	��  TODO
	 * @author 		�� ������1282��
	 * @date		�� 2018-1-30 ����05:20:59 
	 * @param 		��bmjssj the bmjssj to set
	 */
	public void setBmjssj(String bmjssj) {
		this.bmjssj = bmjssj;
	}
	/**
	 * @description	�� TODO
	 * @author 		�� ������1282��
	 * @date		�� 2018-1-30 ����05:20:59 
	 * @return		: the bmdx
	 */
	public String getBmdx() {
		return bmdx;
	}
	/**
	 * @description	��  TODO
	 * @author 		�� ������1282��
	 * @date		�� 2018-1-30 ����05:20:59 
	 * @param 		��bmdx the bmdx to set
	 */
	public void setBmdx(String bmdx) {
		this.bmdx = bmdx;
	}
	/**
	 * @description	�� TODO
	 * @author 		�� ������1282��
	 * @date		�� 2018-1-30 ����05:20:59 
	 * @return		: the bmrs
	 */
	public String getBmrs() {
		return bmrs;
	}
	/**
	 * @description	��  TODO
	 * @author 		�� ������1282��
	 * @date		�� 2018-1-30 ����05:20:59 
	 * @param 		��bmrs the bmrs to set
	 */
	public void setBmrs(String bmrs) {
		this.bmrs = bmrs;
	}
	/**
	 * @description	�� TODO
	 * @author 		�� ������1282��
	 * @date		�� 2018-1-30 ����05:20:59 
	 * @return		: the bmlx
	 */
	public String getBmlx() {
		return bmlx;
	}
	/**
	 * @description	��  TODO
	 * @author 		�� ������1282��
	 * @date		�� 2018-1-30 ����05:20:59 
	 * @param 		��bmlx the bmlx to set
	 */
	public void setBmlx(String bmlx) {
		this.bmlx = bmlx;
	}
	/**
	 * @description	�� TODO
	 * @author 		�� ������1282��
	 * @date		�� 2018-1-30 ����05:20:59 
	 * @return		: the gzh
	 */
	public String getGzh() {
		return gzh;
	}
	/**
	 * @description	��  TODO
	 * @author 		�� ������1282��
	 * @date		�� 2018-1-30 ����05:20:59 
	 * @param 		��gzh the gzh to set
	 */
	public void setGzh(String gzh) {
		this.gzh = gzh;
	}
	/**
	 * @description	�� TODO
	 * @author 		�� ������1282��
	 * @date		�� 2018-1-30 ����05:22:35 
	 * @return		: the searchModel
	 */
	public SearchModel getSearchModel() {
		return searchModel;
	}
	/**
	 * @description	��  TODO
	 * @author 		�� ������1282��
	 * @date		�� 2018-1-30 ����05:22:35 
	 * @param 		��searchModel the searchModel to set
	 */
	public void setSearchModel(SearchModel searchModel) {
		this.searchModel = searchModel;
	}
	/**
	 * @description	�� TODO
	 * @author 		�� ������1282��
	 * @date		�� 2018-1-30 ����05:22:35 
	 * @return		: the exportModel
	 */
	public ExportModel getExportModel() {
		return exportModel;
	}
	/**
	 * @description	��  TODO
	 * @author 		�� ������1282��
	 * @date		�� 2018-1-30 ����05:22:35 
	 * @param 		��exportModel the exportModel to set
	 */
	public void setExportModel(ExportModel exportModel) {
		this.exportModel = exportModel;
	}
	/**
	 * @description	�� TODO
	 * @author 		�� ������1282��
	 * @date		�� 2018-1-30 ����05:22:35 
	 * @return		: the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @description	��  TODO
	 * @author 		�� ������1282��
	 * @date		�� 2018-1-30 ����05:22:35 
	 * @param 		��type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @description	�� TODO
	 * @author 		�� ������1282��
	 * @date		�� 2018-1-30 ����05:32:15 
	 * @return		: the xh
	 */
	public String getXh() {
		return xh;
	}
	/**
	 * @description	��  TODO
	 * @author 		�� ������1282��
	 * @date		�� 2018-1-30 ����05:32:15 
	 * @param 		��xh the xh to set
	 */
	public void setXh(String xh) {
		this.xh = xh;
	}
	/**
	 * @description	�� TODO
	 * @author 		�� ������1282��
	 * @date		�� 2018-1-31 ����10:31:20 
	 * @return		: the xn
	 */
	public String getXn() {
		return xn;
	}
	/**
	 * @description	��  TODO
	 * @author 		�� ������1282��
	 * @date		�� 2018-1-31 ����10:31:20 
	 * @param 		��xn the xn to set
	 */
	public void setXn(String xn) {
		this.xn = xn;
	}
	/**
	 * @description	�� TODO
	 * @author 		�� ������1282��
	 * @date		�� 2018-1-31 ����10:31:20 
	 * @return		: the xq
	 */
	public String getXq() {
		return xq;
	}
	/**
	 * @description	��  TODO
	 * @author 		�� ������1282��
	 * @date		�� 2018-1-31 ����10:31:20 
	 * @param 		��xq the xq to set
	 */
	public void setXq(String xq) {
		this.xq = xq;
	}
	/**
	 * @description	�� TODO
	 * @author 		�� ������1282��
	 * @date		�� 2018-1-31 ����10:38:46 
	 * @return		: the lrsj
	 */
	public String getLrsj() {
		return lrsj;
	}
	/**
	 * @description	��  TODO
	 * @author 		�� ������1282��
	 * @date		�� 2018-1-31 ����10:38:46 
	 * @param 		��lrsj the lrsj to set
	 */
	public void setLrsj(String lrsj) {
		this.lrsj = lrsj;
	}
	/**
	 * @description	�� TODO
	 * @author 		�� ������1282��
	 * @date		�� 2018-1-31 ����10:45:32 
	 * @return		: the pxfs
	 */
	public String getPxfs() {
		return pxfs;
	}
	/**
	 * @description	��  TODO
	 * @author 		�� ������1282��
	 * @date		�� 2018-1-31 ����10:45:32 
	 * @param 		��pxfs the pxfs to set
	 */
	public void setPxfs(String pxfs) {
		this.pxfs = pxfs;
	}
	/**
	 * @description	�� TODO
	 * @author 		�� ������1282��
	 * @date		�� 2018-1-31 ����03:59:45 
	 * @return		: the pages
	 */
	public Pages getPages() {
		return pages;
	}
	/**
	 * @description	��  TODO
	 * @author 		�� ������1282��
	 * @date		�� 2018-1-31 ����03:59:45 
	 * @param 		��pages the pages to set
	 */
	public void setPages(Pages pages) {
		this.pages = pages;
	}
	/**
	 * @description	�� TODO
	 * @author 		�� ������1282��
	 * @date		�� 2018-2-1 ����11:08:14 
	 * @return		: the hdbq
	 */
	public String getHdbq() {
		return hdbq;
	}
	/**
	 * @description	��  TODO
	 * @author 		�� ������1282��
	 * @date		�� 2018-2-1 ����11:08:14 
	 * @param 		��hdbq the hdbq to set
	 */
	public void setHdbq(String hdbq) {
		this.hdbq = hdbq;
	}
	/**
	 * @description	�� TODO
	 * @author 		�� ������1282��
	 * @date		�� 2018-2-5 ����09:26:05 
	 * @return		: the shzt
	 */
	public String getShzt() {
		return shzt;
	}
	/**
	 * @description	��  TODO
	 * @author 		�� ������1282��
	 * @date		�� 2018-2-5 ����09:26:05 
	 * @param 		��shzt the shzt to set
	 */
	public void setShzt(String shzt) {
		this.shzt = shzt;
	}
	/**
	 * @description	�� TODO
	 * @author 		�� ������1282��
	 * @date		�� 2018-2-5 ����09:27:01 
	 * @return		: the cxzd
	 */
	public String getCxzd() {
		return cxzd;
	}
	/**
	 * @description	��  TODO
	 * @author 		�� ������1282��
	 * @date		�� 2018-2-5 ����09:27:01 
	 * @param 		��cxzd the cxzd to set
	 */
	public void setCxzd(String cxzd) {
		this.cxzd = cxzd;
	}
	/**
	 * @description	�� TODO
	 * @author 		�� ������1282��
	 * @date		�� 2018-2-5 ����10:04:05 
	 * @return		: the ids
	 */
	public String[] getIds() {
		return ids;
	}
	/**
	 * @description	��  TODO
	 * @author 		�� ������1282��
	 * @date		�� 2018-2-5 ����10:04:05 
	 * @param 		��ids the ids to set
	 */
	public void setIds(String[] ids) {
		this.ids = ids;
	}

	public String getHdjxzt() {
		return hdjxzt;
	}

	public void setHdjxzt(String hdjxzt) {
		this.hdjxzt = hdjxzt;
	}

	public String getHdzt() {
		return hdzt;
	}

	public void setHdzt(String hdzt) {
		this.hdzt = hdzt;
	}
	/**
	 * @description	�� TODO
	 * @author 		�� ������1282��
	 * @date		�� 2018-2-6 ����04:33:44 
	 * @return		: the jdid
	 */
	public String getJdid() {
		return jdid;
	}
	/**
	 * @description	��  TODO
	 * @author 		�� ������1282��
	 * @date		�� 2018-2-6 ����04:33:44 
	 * @param 		��jdid the jdid to set
	 */
	public void setJdid(String jdid) {
		this.jdid = jdid;
	}
	/**
	 * @description	�� TODO
	 * @author 		�� ������1282��
	 * @date		�� 2018-2-6 ����04:33:44 
	 * @return		: the guid
	 */
	public String getGuid() {
		return guid;
	}
	/**
	 * @description	��  TODO
	 * @author 		�� ������1282��
	 * @date		�� 2018-2-6 ����04:33:44 
	 * @param 		��guid the guid to set
	 */
	public void setGuid(String guid) {
		this.guid = guid;
	}
	/**
	 * @description	�� TODO
	 * @author 		�� ������1282��
	 * @date		�� 2018-2-6 ����04:46:09 
	 * @return		: the xdth
	 */
	public String getXdth() {
		return xdth;
	}
	/**
	 * @description	��  TODO
	 * @author 		�� ������1282��
	 * @date		�� 2018-2-6 ����04:46:09 
	 * @param 		��xdth the xdth to set
	 */
	public void setXdth(String xdth) {
		this.xdth = xdth;
	}
	/**
	 * @description	�� TODO
	 * @author 		�� ������1282��
	 * @date		�� 2018-2-7 ����09:32:26 
	 * @return		: the fjid
	 */
	public String getFjid() {
		return fjid;
	}
	/**
	 * @description	��  TODO
	 * @author 		�� ������1282��
	 * @date		�� 2018-2-7 ����09:32:26 
	 * @param 		��fjid the fjid to set
	 */
	public void setFjid(String fjid) {
		this.fjid = fjid;
	}

	public String[] getZghs() {
		return zghs;
	}

	public void setZghs(String[] zghs) {
		this.zghs = zghs;
	}
	public String getDwid() {
		return dwid;
	}
	public void setDwid(String dwid) {
		this.dwid = dwid;
	}
	public String getDwzw() {
		return dwzw;
	}
	public void setDwzw(String dwzw) {
		this.dwzw = dwzw;
	}
	public String getHdsqid() {
		return hdsqid;
	}
	public void setHdsqid(String hdsqid) {
		this.hdsqid = hdsqid;
	}
	public String getPlnr() {
		return plnr;
	}
	public void setPlnr(String plnr) {
		this.plnr = plnr;
	}
	public String getPlid() {
		return plid;
	}
	public void setPlid(String plid) {
		this.plid = plid;
	}
	public String getHfbj() {
		return hfbj;
	}
	public void setHfbj(String hfbj) {
		this.hfbj = hfbj;
	}
	public String getHfrid() {
		return hfrid;
	}
	public void setHfrid(String hfrid) {
		this.hfrid = hfrid;
	}
	public String getHfrxm() {
		return hfrxm;
	}
	public void setHfrxm(String hfrxm) {
		this.hfrxm = hfrxm;
	}
	public String getHfplid() {
		return hfplid;
	}
	public void setHfplid(String hfplid) {
		this.hfplid = hfplid;
	}

	public String getPsfs() {
		return psfs;
	}

	public void setPsfs(String psfs) {
		this.psfs = psfs;
	}
	public String getPrejdid() {
		return prejdid;
	}
	public void setPrejdid(String prejdid) {
		this.prejdid = prejdid;
	}
	
	

	public String getShyj() {
		return shyj;
	}

	public void setShyj(String shyj) {
		this.shyj = shyj;
	}

	public String getJxid() {
		return jxid;
	}

	public void setJxid(String jxid) {
		this.jxid = jxid;
	}

	public String getXf() {
		return xf;
	}

	public void setXf(String xf) {
		this.xf = xf;
	}

	public String getZgh() {
		return zgh;
	}

	public void setZgh(String zgh) {
		this.zgh = zgh;
	}
	public String[] getHdbqs() {
		return hdbqs;
	}
	public void setHdbqs(String[] hdbqs) {
		this.hdbqs = hdbqs;
	}
	public String[] getJdlxs() {
		return jdlxs;
	}
	public void setJdlxs(String[] jdlxs) {
		this.jdlxs = jdlxs;
	}
	public String[] getJdmcs() {
		return jdmcs;
	}
	public void setJdmcs(String[] jdmcs) {
		this.jdmcs = jdmcs;
	}
	public String[] getJdids() {
		return jdids;
	}
	public void setJdids(String[] jdids) {
		this.jdids = jdids;
	}
	public String[] getJdkssjs() {
		return jdkssjs;
	}
	public void setJdkssjs(String[] jdkssjs) {
		this.jdkssjs = jdkssjs;
	}
	public String[] getJdjssjs() {
		return jdjssjs;
	}
	public void setJdjssjs(String[] jdjssjs) {
		this.jdjssjs = jdjssjs;
	}
	public String getHdxf() {
		return hdxf;
	}
	public void setHdxf(String hdxf) {
		this.hdxf = hdxf;
	}
	public String[] getSfsljxs() {
		return sfsljxs;
	}
	public void setSfsljxs(String[] sfsljxs) {
		this.sfsljxs = sfsljxs;
	}
	public String[] getSfslxfs() {
		return sfslxfs;
	}
	public void setSfslxfs(String[] sfslxfs) {
		this.sfslxfs = sfslxfs;
	}
	public String[] getJxmcs() {
		return jxmcs;
	}
	public void setJxmcs(String[] jxmcs) {
		this.jxmcs = jxmcs;
	}
	public String[] getJxsms() {
		return jxsms;
	}
	public void setJxsms(String[] jxsms) {
		this.jxsms = jxsms;
	}
	
	
}
