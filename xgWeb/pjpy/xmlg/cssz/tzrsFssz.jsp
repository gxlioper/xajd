<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>
<script language="javascript"
	src="/xgxt/pjpy/xibeierminyuan/js/xbemyJs.js"></script>
<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
<script language="javascript" src="pjpy/ynys/ynysJs/ynys.js"></script>
<script type="text/javascript">
<!--
		
//-->
</script>
<body onload="checkWinType();">
	<html:form action="/xmlgpjpy" method="post">
		<input type="hidden" name="pt" id="pt"/>
		<input type="hidden" name="pkValue" id="pkValue" value="${pkValue }"/>
		<div class="title">
			<div class="title_img" id="title_m">
				当前位置:评奖评优 - 参数设置 - 奖学金比例
			</div>
		</div>
		<table style="width:100%" class="tbstyle">
			<thead>
				<tr>
					<td colspan="2" align="left">
						调整人数限制方式设置
						<br/>
						<font color="red">说明：该操作将会对<bean:message key="lable.xsgzyxpzxy" />进行奖学金人数调整时起限制作用。<br/>
						      方式1：四舍五入限制，<bean:message key="lable.xsgzyxpzxy" />在调整奖学金人数时，<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;不能超过学校设置比例后生成建议人数的四舍五入范围.<br/>
						      方式2：金额限制，<bean:message key="lable.xsgzyxpzxy" />在调整奖学金人数时，<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;在不超过学校设置比例后生成的总金额的前提下，<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;可以根据实际情况进行人数调整. 
						 </font>
					</td>
				</tr>
			</thead>
			<tr style="width:22px">
				<td align="right" width="50%">
					<font color="red">*</font>限制方式：
				</td>
				<td align="left">
					<html:select property="xzfs" styleId="xzfs">
						<html:option value=""></html:option>
						<html:options collection="xzfsList" property="en" labelProperty="cn"/>
					</html:select>
				</td>
			</tr>
		</table>
		<div class="buttontool" align="center">
				<button type="button" class="button2" id="btn_save"
					onclick="saveinfo('pjpy_xmlg_tzrsFssz.do?operType=save','xzfs')"
					style="width:80px">
					保 存
				</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
			<button type="button" class="button2" id="btn_close" onclick="Close();return false;"
				style="width:80px" id="buttonClose">
				关 闭
			</button>
		</div>
		<jsp:include flush="true" page="/syscommon/saveprompt.jsp"></jsp:include>
	</html:form>
</body>
