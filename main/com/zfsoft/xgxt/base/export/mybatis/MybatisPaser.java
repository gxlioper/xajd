/**
 * @部门:学工产品事业部
 * @日期：2013-11-27 上午10:30:53 
 */
package com.zfsoft.xgxt.base.export.mybatis;

import java.util.HashMap;
import java.util.Map;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import xgxt.utils.String.StringUtils;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: mybaitis配置文件解析
 * @类功能描述: TODO(这里用一句话描述这个类的作用)
 * @作者： 张昌路[工号:982]
 * @时间： 2013-11-27 上午10:30:53
 * @deprecated
 * 解析mybatis xml 为sql，后续可以进行完善
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class MybatisPaser extends DefaultHandler {
	private MybatisXml mx;
	private Map<String, MybatisXml> mybatisSqlData;
	private String methodId;
	private String nowQName;

	/*
	 * 描述: @see org.xml.sax.helpers.DefaultHandler#startDocument()
	 */

	@Override
	public void startDocument() throws SAXException {
		mybatisSqlData = new HashMap<String, MybatisXml>();
	}

	/*
	 * 描述: @see
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
	 * 描述: @see org.xml.sax.helpers.DefaultHandler#characters(char[], int, int)
	 */

	@Override
	public void characters(char[] arg0, int arg1, int arg2) throws SAXException {
		if (StringUtils.isNotNull(nowQName)) {
			String context = new String(arg0, arg1, arg2);
			mx.setSql(context);
		}
	}

	/*
	 * 描述: @see org.xml.sax.helpers.DefaultHandler#endElement(java.lang.String,
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
	 * @param mybatisSqlData要设置的
	 *            mybatisSqlData
	 */
	public void setMybatisSqlData(Map<String, MybatisXml> mybatisSqlData) {
		this.mybatisSqlData = mybatisSqlData;
	};
}
