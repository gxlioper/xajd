/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-11-22 ����10:31:30 
 */
package xsgzgl.xtwh.general.news;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;

import com.zfsoft.xgxt.base.util.SearchUtil;

import xgxt.comm.search.SearchModel;
import xgxt.utils.String.StringUtils;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ����Ȩ�޷�Χ
 * @�๦������: TODO(������һ�仰��������������)
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2013-11-22 ����10:31:30
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class NewQxfwForm extends ActionForm {
	private String qxfwid;
	private String newsid;
	private String mhcx_lx;
	private String searchTj;
	private String searchTjz;
	private String searchLx;
	
	private Map<String,List<String>> seletTj;
	public void setNewQxfwFormForRequest(HttpServletRequest request) {
		this.setMhcx_lx(request.getParameter("mhcx_lx"));
		this.setSearchTj(request.getParameter("searchTj"));
		this.setSearchTjz(request.getParameter("searchTjz"));
		this.setSearchLx(request.getParameter("searchLx"));
	}
	/**
	 * 
	 * @����:��ȡ��Ӧ��SearchModel
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-11-23 ����06:25:00
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param request
	 * @return
	 * SearchModel �������� 
	 */
	public SearchModel getSearchModel(){
		if(StringUtils.isNull(this.getNewsid())||StringUtils.isNull(this.getQxfwid())){
			return null;
		}
		SearchModel sm=SearchUtil.getSearchModel(null, this.mhcx_lx, this.searchLx, this.searchTj, this.searchTjz);
		sm.setPath("newselect.do");
		return sm;
	}
	/**
	 * @return the qxfwid
	 */
	public String getQxfwid() {
		return qxfwid;
	}

	/**
	 * @param qxfwidҪ���õ�
	 *            qxfwid
	 */
	public void setQxfwid(String qxfwid) {
		this.qxfwid = qxfwid;
	}

	/**
	 * @return the newsid
	 */
	public String getNewsid() {
		return newsid;
	}

	/**
	 * @param newsidҪ���õ�
	 *            newsid
	 */
	public void setNewsid(String newsid) {
		this.newsid = newsid;
	}

	/**
	 * @return the mhcx_lx
	 */
	public String getMhcx_lx() {
		return mhcx_lx;
	}

	/**
	 * @param mhcxLxҪ���õ�
	 *            mhcx_lx
	 */
	public void setMhcx_lx(String mhcxLx) {
		mhcx_lx = mhcxLx;
	}

	/**
	 * @return the searchTj
	 */
	public String getSearchTj() {
		return searchTj;
	}

	/**
	 * @param searchTjҪ���õ�
	 *            searchTj
	 */
	public void setSearchTj(String searchTj) {
		this.searchTj = searchTj;
	}

	/**
	 * @return the searchTjz
	 */
	public String getSearchTjz() {
		return searchTjz;
	}

	/**
	 * @param searchTjzҪ���õ�
	 *            searchTjz
	 */
	public void setSearchTjz(String searchTjz) {
		this.searchTjz = searchTjz;
	}

	/**
	 * @return the searchLx
	 */
	public String getSearchLx() {
		return searchLx;
	}

	/**
	 * @param searchLxҪ���õ�
	 *            searchLx
	 */
	public void setSearchLx(String searchLx) {
		this.searchLx = searchLx;
	}
	/**
	 * @return the seletTj
	 */
	public Map<String, List<String>> getSeletTj() {
		return seletTj;
	}
	/**
	 * @param seletTjҪ���õ� seletTj
	 */
	public void setSeletTj(Map<String,List<String>> seletTj) {
		this.seletTj = seletTj;
	}
	public boolean isNull(){
		if(StringUtils.isNull(this.getNewsid())||StringUtils.isNull(this.getQxfwid())){
			return true;
		}
		return false;
	}
}
