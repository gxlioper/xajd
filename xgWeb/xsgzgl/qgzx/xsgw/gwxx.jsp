<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic"
	prefix="logic"%>

<logic:empty name="userlx">
	<logic:notEqual  name="lx" value="tz">
	<tr>
		<td colspan="4"><button class="btn_01" onclick="wdgwxzCx();return false;" type="button">ѡ���λ</button><font color="red">${message}</font></td>
	</tr>
	</logic:notEqual>
</logic:empty>

<tr>
	<th width="16%">
		<%--<logic:equal name="cs" property="qgzq" value="xn">
			         ѧ��
        </logic:equal>
		<logic:equal name="cs" property="qgzq" value="xq">
				ѧ��ѧ��
		</logic:equal>--%>
			ѧ��
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
		���˵�λ
	</th>
	<td width="34%">
		${model.yrdwmc }
	</td>
</tr>
<tr>
	<th width="16%">
		��λ����
	</th>
	<td width="34%">
		${model.gwmc }
	</td>
	<th width="16%">
		��������
	</th>
	<td width="34%">
		<logic:equal name="model" property="gwxzdm" value="0"> ��ʱ</logic:equal>
		<logic:equal name="model" property="gwxzdm" value="1">��ʽ</logic:equal>
	</td>
</tr>
<tr>
	<th width="16%">
		��Ƹ����
	</th>
	<td width="34%">
		${model.xqrs}��
	</td>

	<th width="16%">
		��λ����
	</th>
	<td>
		<logic:equal name="model" property="gwlx" value="0">��ʱ</logic:equal>
		<logic:equal name="model" property="gwlx" value="1">����</logic:equal>
	</td>
</tr>
<tr>
	<th>
		��λ���
	</th>
	<td >
		${model.gwxzmc}
	</td>
	<th>
		��λн������
	</th>
	<td>
		${model.gwcjsx}Ԫ
	</td>
</tr>
<tr>
	<th>
		��ʱ����
	</th>
	<td colspan="3">
		${model.gssx}Сʱ
		<span id="label"></span>
	</td>
</tr>
<tr>
	<th>
		��Ƹ��ʼʱ��
	</th>
	<td>
		${model.zpkssj}
	</td>
	<th>
		��Ƹ����ʱ��
	</th>
	<td>
		<logic:equal name="model" property="cq" value="1">����</logic:equal>
		<logic:notEqual name="model" property="cq" value="1">${model.zpjssj}</logic:notEqual>
	</td>
</tr>
<tr>
	<th>
		��ƸҪ��
	</th>
	<td colspan="3">
		${model.gwryyq}
	</td>
</tr>
<%--<thead>
	<tr>
		<th colspan="4">
			<span>��λЭ����</span>
		</th>
	</tr>
</thead>--%>
<tr>
	<th>
		<span>��λЭ����</span>
	</th>
	<td colspan="3">
		<logic:equal value="stu" name="userType">
			<input id="xyscheck" type="checkbox">�����Ķ���ͬ��ǩ��
		</logic:equal>
		<logic:notEqual value="stu" name="userType">
			<input id="xyscheck" type="checkbox" checked style="display: none">�����Ķ���ͬ��ǩ��
		</logic:notEqual>
		<a href="javascript:void(0);" onclick="window.open('common_upload.do?method=asyncDownload&fid=${model.uploadid}');return false;" class="name" style="margin-left: 0px;">
			���ڹ���ѧ��λЭ���顷
		</a>
	</td>
</tr>

