<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%><!-- 头文件 -->
<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
<script type="text/javascript" src="/xgxt/dwr/interface/pjpyZjsyzy.js"></script>
<script type="text/javascript" src="js/rcsw/rcswFunction.js"></script>
<script type="text/javascript" src="js/String.js"></script>
<body>
	<html:form action="/bxxx" method="post">
		<input type="hidden" id="userType" name="userType" value="${userType }" />
		<input type="hidden" id="userName" name="userName"value="${userName }" />
		<input type="hidden" id="message" value="${message }">
		<input type="hidden" name="pkValue" value="${pkValue }">
		<input type="hidden" name="njV" id="njV">
		<input type="hidden" name="xyV" id="xyV">
		<input type="hidden" name="zyV" id="zyV">
		<input type="hidden" name="bjV" id="bjV">
		<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：${title }
				</div>
		</div>
		
		<fieldset>
			<legend>
				代码维护
			</legend>
		<table class="tbstyle" width="100%">
			<tr>
				<td align="right">
					<font color="red">*</font>项目代码：
				</td>
				<td>
					<logic:equal value="update" name="doType">
						<html:text property="save_dm" onkeyup="value=value.replace(/[^\d]/g,'')" maxlength="20"  value="${rs.dm }" readonly="true"></html:text>
					</logic:equal>
					<logic:notEqual value="update" name="doType">
						<html:text property="save_dm" onkeyup="value=value.replace(/[^\d]/g,'')" maxlength="20"  value="${rs.dm }"></html:text>
					</logic:notEqual>
					
				</td>
			</tr>
			<tr>
				<td align="right">
					<font color="red">*</font>项目名称：
				</td>
				<td>
					<html:text property="save_mc" maxlength="25" value="${rs.mc }" onblur="this.value=this.value.trim()"></html:text>
				</td>
			</tr>
		</table>
		</fieldset>
		<div>
			<br/><br/>
		</div>
		<div class="buttontool" id="btn" style="position: absolute;width:100%">
			<logic:notEqual value="view" name="doType">
					<logic:notEqual value="update" name="doType">
						<button class="button2" id="buttonSave" onClick="saveUpdate('/xgxt/bxxx.do?method=lpxmUpdate&doType=save','save_dm-save_mc');">
							保&nbsp;&nbsp;存
						</button>
					</logic:notEqual>
			</logic:notEqual>
			<logic:equal value="update" name="doType">
				<button class="button2" id="buttonSave" onClick="saveUpdate('/xgxt/bxxx.do?method=lpxmUpdate&doType=modify','save_dm-save_mc');">
					保&nbsp;&nbsp;存
				</button>
			</logic:equal>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button class="button2" id="buttonSave" onClick="window.close();">
					关&nbsp;&nbsp;闭
				</button>
		</div>
	</html:form>
	<logic:present name="result">
			<script>
				alert(''+$('message').value);
				if (window.dialogArguments) {
					window.close();
					window.dialogArguments.document.getElementById('search_go').click();
				}
			</script>
	</logic:present>
</body>
