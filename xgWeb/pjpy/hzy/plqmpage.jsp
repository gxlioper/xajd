<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>
<script language="javascript" src="pjpy/ahjg/ahjgjs/ahjgjs.js"></script>
<body onload="">
	<html:form action="/pjpyhzzywh" method="post">
	<div class="title">
		��ǰ����λ�ã��������� - ��� - ��ѧ����� - ����ǩ��
	</div>
	<fieldset>
				<legend>
					����ǩ��
				</legend>
				<input type="hidden" id="pks" name="pks" value="${pks}"/>
				<input type="hidden" id="tableName" name="tableName" value="${tableName}"/>
				<input type="hidden" id="act" name="act" value="save"/>
		<table width='98%' align='center' class='tbstyle' id="rsTable">
			<thead>
				<tr align='center'>
					<td colspan="2" align="center">�ò���������ѡ������ݽ�����������!</td>
				</tr>
			</thead>
			<tr>
				<td colspan='' align="right">
						<logic:equal value="true" name="isFdy">������</logic:equal><logic:notEqual value="true" name="isFdy"><logic:equal value="xy" name="userType">ϵ(Ժ)</logic:equal><logic:notEqual value="xy" name="userType"></logic:notEqual></logic:notEqual>ǩ��:
					</td><td colspan='' align="left">
						<html:text property="qm" styleId="qm"></html:text>
					</td>
			</tr>
			<br/>
		</table>
		<div class="buttontool" align="center" >
			<logic:equal value="yes" name="writable">
				<button class="button2" style="" id="btn_save" 
						onclick="if (document.getElementById('qm').value=='' || document.getElementById('qm').value==null || document.getElementById('qm').value==' ') {alert('��δ�����κ�����!');} else {if (confirm('ȷ��Ҫ����ѡ������ݽ�������������?')) {document.getElementById('btn_save').disabled=false;refreshForm('pjpy_hzzy_plqm.do');} return} " width="80px">
						��&nbsp;&nbsp;��
					</button>
					&nbsp;&nbsp;
					<button class="button2" onclick="window.close();return false;" width="80px">��&nbsp;&nbsp;��
					</button>
			</logic:equal>
		</div>
	</fieldset>
	</html:form>
	<jsp:include flush="true" page="/syscommon/saveprompt.jsp"></jsp:include>
</body>
