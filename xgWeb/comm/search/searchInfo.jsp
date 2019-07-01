<%@ page language="java" contentType="text/html; charset=GBK"%>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean"
	prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic"
	prefix="logic"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles"
	prefix="tiles"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-template"
	prefix="template"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-nested"
	prefix="nested"%>
<%@ taglib uri="/WEB-INF/customTag.tld" prefix="customTag"%>
<div id="searchTjDiv">
	<logic:notEmpty name="searchTj">
		<!-- 学号 -->
		<logic:notEmpty name="searchTj" property="search_tj_xh">
			<logic:iterate name="searchTj" property="search_tj_xh" id="tj_xh">
				<input type="hidden" name="searchModel.search_tj_xh" value="${tj_xh }"/>
			</logic:iterate>
		</logic:notEmpty>
		<!-- 姓名 -->
		<logic:notEmpty name="searchTj" property="search_tj_xm">
			<logic:iterate name="searchTj" property="search_tj_xm" id="tj_xm">
				<input type="hidden" name="searchModel.search_tj_xm" value="${tj_xm }"/>
			</logic:iterate>
		</logic:notEmpty>
		<!-- 性别 -->
		<logic:notEmpty name="searchTj" property="search_tj_xb">
			<logic:iterate name="searchTj" property="search_tj_xb" id="tj_xb">
				<input type="hidden" name="searchModel.search_tj_xb" value="${tj_xb }"/>
			</logic:iterate>
		</logic:notEmpty>
		<!-- 年级 -->
		<logic:notEmpty name="searchTj" property="search_tj_nj">
			<logic:iterate name="searchTj" property="search_tj_nj" id="tj_nj">
				<input type="hidden" name="searchModel.search_tj_nj" value="${tj_nj }"/>
			</logic:iterate>
		</logic:notEmpty>	
		<!-- 学院 -->
		<logic:notEmpty name="searchTj" property="search_tj_xy">
			<logic:iterate name="searchTj" property="search_tj_xy" id="tj_xy">
				<input type="hidden" name="searchModel.search_tj_xy" value="${tj_xy }"/>
			</logic:iterate>
		</logic:notEmpty>
		<!-- 专业 -->
		<logic:notEmpty name="searchTj" property="search_tj_zy">
			<logic:iterate name="searchTj" property="search_tj_zy" id="tj_zy">
				<input type="hidden" name="searchModel.search_tj_zy" value="${tj_zy }"/>
			</logic:iterate>
		</logic:notEmpty>
		<!-- 班级 -->
		<logic:notEmpty name="searchTj" property="search_tj_bj">
			<logic:iterate name="searchTj" property="search_tj_bj" id="tj_bj">
				<input type="hidden" name="searchModel.search_tj_bj" value="${tj_bj }"/>
			</logic:iterate>
		</logic:notEmpty>
	</logic:notEmpty>
</div>
