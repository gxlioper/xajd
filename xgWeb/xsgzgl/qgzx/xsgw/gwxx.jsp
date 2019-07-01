<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic"
	prefix="logic"%>

<logic:empty name="userlx">
	<logic:notEqual  name="lx" value="tz">
	<tr>
		<td colspan="4"><button class="btn_01" onclick="wdgwxzCx();return false;" type="button">选择岗位</button><font color="red">${message}</font></td>
	</tr>
	</logic:notEqual>
</logic:empty>

<tr>
	<th width="16%">
		<%--<logic:equal name="cs" property="qgzq" value="xn">
			         学年
        </logic:equal>
		<logic:equal name="cs" property="qgzq" value="xq">
				学年学期
		</logic:equal>--%>
			学年
		<input type="hidden" id="gwdm" name="gwdm" value="${model.gwdm }">
	</th>
	<td width="34%">
		<%--<logic:equal name="cs" property="qgzq" value="xn">--%>
			<%--${model.xn }--%>
		<%--</logic:equal>--%>
		<%--<logic:equal name="cs" property="qgzq" value="xq">--%>
			<%--${model.xn }&nbsp;&nbsp;&nbsp;${model.xqmc }--%>
		<%--</logic:equal>--%>
			${model.xn }
	</td>
	<th width="16%">
		用人单位
	</th>
	<td width="34%">
		${model.yrdwmc }
	</td>
</tr>
<tr>
	<th width="16%">
		岗位名称
	</th>
	<td width="34%">
		${model.gwmc }
	</td>
	<th width="16%">
		工作性质
	</th>
	<td width="34%">
		<logic:equal name="model" property="gwxzdm" value="0"> 临时</logic:equal>
		<logic:equal name="model" property="gwxzdm" value="1">正式</logic:equal>
	</td>
</tr>
<tr>
	<th width="16%">
		招聘人数
	</th>
	<td width="34%">
		${model.xqrs}人
	</td>

	<th width="16%">
		岗位类型
	</th>
	<td>
		<logic:equal name="model" property="gwlx" value="0">临时</logic:equal>
		<logic:equal name="model" property="gwlx" value="1">长期</logic:equal>
	</td>
</tr>
<tr>
	<th>
		岗位类别
	</th>
	<td >
		${model.gwxzmc}
	</td>
	<th>
		岗位薪酬上限
	</th>
	<td>
		${model.gwcjsx}元
	</td>
</tr>
<tr>
	<th>
		工时上限
	</th>
	<td colspan="3">
		${model.gssx}小时
		<span id="label"></span>
	</td>
</tr>
<tr>
	<th>
		招聘开始时间
	</th>
	<td>
		${model.zpkssj}
	</td>
	<th>
		招聘结束时间
	</th>
	<td>
		<logic:equal name="model" property="cq" value="1">长期</logic:equal>
		<logic:notEqual name="model" property="cq" value="1">${model.zpjssj}</logic:notEqual>
	</td>
</tr>
<tr>
	<th>
		招聘要求
	</th>
	<td colspan="3">
		${model.gwryyq}
	</td>
</tr>
<%--<thead>
	<tr>
		<th colspan="4">
			<span>岗位协议书</span>
		</th>
	</tr>
</thead>--%>
<tr>
	<th>
		<span>岗位协议书</span>
	</th>
	<td colspan="3">
		<logic:equal value="stu" name="userType">
			<input id="xyscheck" type="checkbox">我已阅读并同意签订
		</logic:equal>
		<logic:notEqual value="stu" name="userType">
			<input id="xyscheck" type="checkbox" checked style="display: none">我已阅读并同意签订
		</logic:notEqual>
		<a href="javascript:void(0);" onclick="window.open('common_upload.do?method=asyncDownload&fid=${model.uploadid}');return false;" class="name" style="margin-left: 0px;">
			《勤工助学岗位协议书》
		</a>
	</td>
</tr>

