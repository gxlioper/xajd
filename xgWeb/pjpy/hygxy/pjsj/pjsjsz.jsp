<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>
<script type="text/javascript">
<!--
	function loadjxjxn() {
		var jxjsqxn = document.getElementById('jxjsqxn').value;
		if (jxjsqxn == null || jxjsqxn == '') {
			alert('评奖学年尚未设置,请先在参数设置里面设整学年!');
		}
	}
//-->
</script>
<body onload="loadjxjxn()">
	<html:form action="/pjpyhygxywh" method="post">
	<div class="title">
		当前位置：评奖评优 - 参数设置 - 思想品德评定时间
	</div>
	<fieldset>
				<legend>
					时间设置
				</legend>
		<table width='40%' align='center' class='tbstyle' id="rsTable">
			<tr>
				<td align="right">
					评奖学年:
				</td>
				<td align="left">
					${jxjsqxn }
					<input type="hidden" name="jxjsqxn" id="jxjsqxn" value="${jxjsqxn }"/>
				</td>
			</tr>
			<tr>
				<td align="right">
					评奖年度:
				</td>
				<td align="left">
					${jxjsqnd }
				</td>
			</tr>
			<tr>
				<td align="right">
					评奖学期:
				</td>
				<td align="left">
					${jxjsqxq }
				</td>
			</tr>
			<tr>
				<td align="right">
					奖学金评定时间:
				</td>
				<td align="left">
					<html:radio property="jxjpdsj" value="1"></html:radio>是
					<html:radio property="jxjpdsj" value="0"></html:radio>否
				</td>
			</tr>
			<tr>
				<td align="right">
					荣誉称号评定时间:
				</td>
				<td align="left">
					<html:radio property="rychpdsj" value="1"></html:radio>是
					<html:radio property="rychpdsj" value="0"></html:radio>否
				</td>
			</tr>
		</table>
		<div class="buttontool" align="center" >
			<logic:equal value="yes" name="writable">
				<button type="button" class="button2" style="width: 80px" id="btn_save" 
						onclick="refreshForm('savepjsj.do')">
						保&nbsp;&nbsp;存
					</button>
			</logic:equal>
		</div>
	</fieldset>
	</html:form>
	<logic:notEmpty name="inserted">
				<logic:equal value="no" name="inserted">
					<script language="javascript">
						alert("操作失败！");
					</script>
				</logic:equal>
				<logic:equal value="yes" name="inserted">
					<script language="javascript">
						alert("操作成功！");
					</script>
				</logic:equal>
			</logic:notEmpty>
	<script type="text/javascript">
	</script>
</body>
