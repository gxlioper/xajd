<%@ page language="java" contentType="text/html; charset=GBK"%>
<!-- 学年列表 begin -->
<logic:present name="xnList">
	<select id="select_xn" style="display:none">
		<logic:iterate name="xnList" id="xnxx">
			<option value="${xnxx.xn}">${xnxx.xn}</option>
		</logic:iterate>
	</select>
</logic:present>
<!-- 学年列表 end -->


<!-- 学期列表 begin -->
<logic:present name="xqList">
	<select id="select_xq" style="display:none">
		<option value=""></option>
		<logic:iterate name="xqList" id="xqxx">
			<option value="${xqxx.xqdm}">${xqxx.xqmc}</option>
		</logic:iterate>
	</select>
</logic:present>
<!-- 学期列表 end -->