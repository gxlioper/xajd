<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>
<script language="javascript" src="pjpy/ahjg/ahjgjs/ahjgjs.js"></script>
<body onload="">
	<html:form action="/pjpyhygxywh" method="post">
	<div class="title">
		当前所在位置：系统维护 - 代码维护 - 评奖评优
	</div>
	<fieldset>
				<legend>
					基础代码维护
				</legend>
		<table width='98%' align='center' class='tbstyle' id="rsTable">
			<thead>
				<tr align='center'>
					<td colspan='2'>
						* 添 加 代 码 *
					</td>
				</tr>
			</thead>
						<tr align="" style="width:35px">
						<td align="right"> 
							<font  color="red">*</font>奖学金类别代码：
						</td>
						<td align="left">
							<html:text property="jxjlbdm" styleId="jxjlbdm" styleClass="inputtext" maxlength="10" ></html:text>
						</td>
						</tr>
						<tr align="" style="width:35px">
						<td align="right"> 
							<font  color="red">*</font>奖学金类别名称：
						</td>
						<td align="left">
							<html:text property="jxjlbmc" styleId="jxjlbmc" styleClass="inputtext"></html:text>
						</td>
						</tr>
						<tr align="" style="width:35px">
						<td align="right"> 
							<font  color="red">*</font>同类奖学金是否兼得：
						</td>
						<td align="left">
							<html:radio property="jxjjd" value="0"></html:radio>否
							<html:radio property="jxjjd" value="1"></html:radio>是
						</td>
						</tr>
		</table>
		<div class="buttontool" align="center" >
			<logic:equal value="yes" name="writable">
				<button type="button" class="button2" style="" id="btn_save" 
						onclick="savedm('hygxyjxjlbdmsave.do');">
						&nbsp;&nbsp;保&nbsp;&nbsp;存&nbsp;&nbsp;
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2" onclick="window.close();return false;" >
						&nbsp;&nbsp;关&nbsp;&nbsp;闭&nbsp;&nbsp;
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
				<script language="javascript">
					Close();
					var tN = window.dialogArguments.document.forms[0].tname.value;
					window.dialogArguments.document.getElementById(tN).click();
		</script>
			</logic:notEmpty>
	<script type="text/javascript">
		function savedm(url) {
			if (document.getElementById('jxjlbdm').value=='' || document.getElementById('jxjlbdm').value==null 
				|| document.getElementById('jxjlbmc').value=='' || document.getElementById('jxjlbmc').value==null) 
				{
					alert('带*号为必填，请确认！');
					return;
				}
			refreshForm(url);
			document.getElementById('btn_save').disabled = true;
			//window.close();
		}
		function onlynum() {
			if (event.keyCode>=48 && event.keyCode<=57) {
				alert('只能输入数字!');
				document.getElementById('jxjjb').value='';
				return;
			}
		}
	</script>
</body>
