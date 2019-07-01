<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>
<!-- 头文件 -->
<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
<script type="text/javascript" src="js/rcsw/rcswFunction.js"></script>
<script type='text/javascript' src='/xgxt/dwr/interface/getDtjsInfo.js'></script>
<script type='text/javascript' src='/xgxt/dwr/interface/pjpyService.js'></script>
<script type="text/javascript" src="js/check.js"></script>
<script type="text/javascript">
<!--
function print(){
	url = "/xgxt/commPrint.do?method=printJsp";
	
	document.forms[0].action = url;
	document.forms[0].target = "_blank";
	document.forms[0].submit();
	document.forms[0].target = "_self";
}
//-->
</script>
<body>
	<html:form action="/xszz_nbcs" method="post">
		<div class="title">
			<div class="title_img" id="title_m">
				当前所在位置：助学贷款个人信息详细
			</div>
		</div>
		<input type="hidden" id="url" name="url" value="/typj.do?method=jxjsq" />
		<input type="hidden" id="getStuInfo" name="getStuInfo"
			value="xm-xb-zymc-xymc-bjmc-mzmc-zzmmmc-csrq-lxdh" />
		<input type="hidden" name="message" id="message" value="${message }" />
		<input type="hidden" name="save_xq" value="无"/>
		<input type="hidden" name="save_sqsj" value="无" id="sqsj"/>
		<input type="hidden" id="xxdm" value="${xxdm }"/>
		<input type="hidden" name="now" value="${nowTime }" id="now"/>
		<input type="hidden" name="save_sqsj" id="sqsj" value="${rs.sqsj }" />
		<input type="hidden" name="save_xmdm" id="xmdm" value="${rs.xmdm }" />
		<input type="hidden" name="xmb" id="xmb" value="gjzxdkb"/>
		<input type="hidden" name="xh"  value="${rs.xh }"/>
		
		<fieldset>
			<legend>
				基本信息
			</legend>
			<table class="tbstyle" width="100%">
				<tr height="40px">
					<td align="right" width="16%">
						学号：
					</td>
					<td align="left" width="34%">
						${rs.xh }
					</td>
					<td width="16%" align="right">
							姓名：
					</td>
					<td width="34%">
						${rs.xm }
					</td>
				</tr>
				<tr height="40px">
					<td align="right">
						性别：
					</td>
					<td>
						${rs.xb }
					</td>
					<td align="right">
						年级：
					</td>
					<td>
						${rs.nj }
					</td>
					
				</tr>
				<tr height="40px">
					<td align="right">
						<bean:message key="lable.xsgzyxpzxy" />：
					</td>
					<td>
						${rs.xymc }
					</td>
					<td align="right">
						专业：
					</td>
					<td>
						${rs.zymc }
					</td>
					
				</tr>
				<tr height="40px">
					<td align="right">
						班级：
					</td>
					<td>
						${rs.bjmc }
					</td>
					<td align="right">
						民族：
					</td>
					<td>${rs.mzmc }</td>
				</tr>
				<tr height="40px">
					<td align="right">
						政治面貌：
					</td>
					<td>${rs.zzmmmc }</td>
					<td align="right">
						合同编号：
					</td>
					<td>
						<font color="red">
							${rs.hdbh }
						</font>
					</td>
				</tr>
				<tr height="40px">
					<td align="right">
						贷款总金额：
					</td>
					<td>
						<input type="text" name="save_zje" value="${rs.zje }" onblur="checkMoney(this)"/>
					</td>
					<td align="right">
						申请时间：
					</td>
					<td>${rs.sqsj }</td>
				</tr>
				<tr height="40px">
					<td align="right">
						身份证号：
					</td>
					<td>${rs.sfzh }</td>
					<td align="right">
						联系电话：
					</td>
					<td>${rs.lxdh }</td>
				</tr>
				<tr height="40px">
					<td align="right">
						<bean:message key="lable.xsgzyxpzxy" />审核：
					</td>
					<td>${rs.xysh }</td>
					<td align="right">
						学校审核：
					</td>
					<td>${rs.xxsh }</td>
				</tr>
				<tr height="80px">
					<td align="right">
						<bean:message key="lable.xsgzyxpzxy" />审核意见：
					</td>
					<td colspan="3">
						${rs.xyshyj }
					</td>
				</tr>
				<tr height="80px">
					<td align="right">
						学校审核意见：
					</td>
					<td colspan="3">
						${rs.xxshyj }
					</td>
				</tr>
			</table>
		</fieldset>
		
		<div class="buttontool" id="btn" style="position: absolute;width:100%">
			
			<button type="button" class="button2" onclick="print();" style="width:80px">
				打印报表
			</button>
			&nbsp;&nbsp;&nbsp;&nbsp;
			<button type="button" class="button2" id="buttonSave" onclick="Close();return false;">
					关&nbsp;&nbsp;&nbsp;&nbsp;闭
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
