<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>
<script language="javascript" src="pjpy/ahjg/ahjgjs/ahjgjs.js"></script>
<body onload="">
	<html:form action="/pjpyhzzywh" method="post">
	<div class="title">
		当前所在位置：评奖评优 - 审核 - 奖学金审核 - 批量签名
	</div>
	<fieldset>
				<legend>
					批量签名
				</legend>
				<input type="hidden" id="pks" name="pks" value="${pks}"/>
				<input type="hidden" id="tableName" name="tableName" value="${tableName}"/>
				<input type="hidden" id="act" name="act" value="save"/>
		<table width='98%' align='center' class='tbstyle' id="rsTable">
			<thead>
				<tr align='center'>
					<td colspan="2" align="center">该操作将对您选择的数据进行批量更新!</td>
				</tr>
			</thead>
			<tr>
				<td colspan='' align="right">
						<logic:equal value="true" name="isFdy">班主任</logic:equal><logic:notEqual value="true" name="isFdy"><logic:equal value="xy" name="userType">系(院)</logic:equal><logic:notEqual value="xy" name="userType"></logic:notEqual></logic:notEqual>签名:
					</td><td colspan='' align="left">
						<html:text property="qm" styleId="qm"></html:text>
					</td>
			</tr>
			<br/>
		</table>
		<div class="buttontool" align="center" >
			<logic:equal value="yes" name="writable">
				<button class="button2" style="" id="btn_save" 
						onclick="if (document.getElementById('qm').value=='' || document.getElementById('qm').value==null || document.getElementById('qm').value==' ') {alert('尚未输入任何数据!');} else {if (confirm('确认要将所选择的数据进行批量更新吗?')) {document.getElementById('btn_save').disabled=false;refreshForm('pjpy_hzzy_plqm.do');} return} " width="80px">
						保&nbsp;&nbsp;存
					</button>
					&nbsp;&nbsp;
					<button class="button2" onclick="window.close();return false;" width="80px">关&nbsp;&nbsp;闭
					</button>
			</logic:equal>
		</div>
	</fieldset>
	</html:form>
	<jsp:include flush="true" page="/syscommon/saveprompt.jsp"></jsp:include>
</body>
