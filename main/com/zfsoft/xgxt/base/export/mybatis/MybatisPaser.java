/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-11-27 ����10:30:53 
 */
package com.zfsoft.xgxt.base.export.mybatis;

import java.util.HashMap;
import java.util.Map;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import xgxt.utils.String.StringUtils;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: mybaitis�����ļ�����
 * @�๦������: TODO(������һ�仰��������������)
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2013-11-27 ����10:30:53
 * @deprecated
 * ����mybatis xml Ϊsql���������Խ�������
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class MybatisPaser extends DefaultHandler {
	private MybatisXml mx;
	private Map<String, MybatisXml> mybatisSqlData;
	private String methodId;
	private String nowQName;

	/*
	 * ����: @see org.xml.sax.helpers.DefaultHandler#startDocument()
	 */

	@Override
	public void startDocument() throws SAXException {
		mybatisSqlData = new HashMap<String, MybatisXml>();
	}

	/*
	 * ����: @see
	 * org.xml.sax.helpers.DefaultHandler#startElement(java.lang.String,
	 * java.lang.String, java.lang.String, org.xml.sax.Attributes)
	 */

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		if (qName.equals("select")) {
			mx = new MybatisXml();
			mx.setId(attributes.getValue("id"));
			mx.setParameterType(attributes.getValue("parameterType"));
			mx.setResultType(attributes.getValue("resultType"));
			mx.setSql(localName);
		}
		methodId = mx.getId();
		nowQName = qName;
	}

	/*
	 * ����: @see org.xml.sax.helpers.DefaultHandler#characters(char[], int, int)
	 */

	@Override
	public void characters(char[] arg0, int arg1, int arg2) throws SAXException {
		if (StringUtils.isNotNull(nowQName)) {
			String context = new String(arg0, arg1, arg2);
			mx.setSql(context);
		}
	}

	/*
	 * ����: @see org.xml.sax.helpers.DefaultHandler#endElement(java.lang.String,
	 * java.lang.String, java.lang.String)
	 */

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		mybatisSqlData.put(methodId, mx);

	}

	/**
	 * @return the mybatisSqlData
	 */
	public Map<String, MybatisXml> getMybatisSqlData() {
		return mybatisSqlData;
	}

	/**
	 * @param mybatisSqlDataҪ���õ�
	 *            mybatisSqlData
	 */
	public void setMybatisSqlData(Map<String, MybatisXml> mybatisSqlData) {
		this.mybatisSqlData = mybatisSqlData;
	};
}
