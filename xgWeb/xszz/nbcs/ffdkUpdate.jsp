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
function saveData(){
	var zje = Number($('zje').value);
	var fkje1 = Number($('fkje1').value);
	var fkje2 = Number($('fkje2').value);
	var fkje3 = Number($('fkje3').value);
	var fkje4 = Number($('fkje4').value);
	
	if (fkje1+fkje2+fkje3+fkje4>zje){
		alert('放款金额总额超出贷款总金额！');
		return false;
	}
	saveUpdate('/xgxt/xszz_nbcs.do?method=ffdkUpdate&doType=save','');
}
//-->
</script>
<body>
	<html:form action="/xszz_nbcs" method="post">
		<div class="title">
			<div class="title_img" id="title_m">
				当前所在位置：助学贷款发放
			</div>
		</div>
		<input type="hidden" id="url" name="url" value="/typj.do?method=jxjsq" />
		<input type="hidden" id="getStuInfo" name="getStuInfo"
			value="xm-xb-zymc-xymc-bjmc-mzmc-zzmmmc-csrq-lxdh" />
		<input type="hidden" name="message" id="message" value="${message }" />
		<input type="hidden" id="xxdm" value="${xxdm }"/>
		<input type="hidden" name="now" value="${nowTime }" id="now"/>
		<input type="hidden" name="userType" value="${userType }" id="userType"/>
		<input type="hidden" name="zje" value="${rs.zje }" id="zje"/>
		

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
						<html:text property="save_xh" value="${rs.xh }" readonly="true"/>
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
					</td>
				</tr>
				
				<tr height="40px">
					<td align="right">
						贷款总金额：
					</td>
					<td>
						${rs.zje }
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
						第一次发放金额：
					</td>
					<td>
						<input type="text" name="save_fkje1" id="fkje1" value="${rs.fkje1 }" onblur="checkMoney(this)">
					</td>
					<td align="right">
						第一次发放时间：
					</td>
					<td>
						<input type="text" name="save_fksj1" value="${rs.fksj1 }" id="fksj1" onblur='dateFormatChg(this)' onclick="showCalendar(this.id,'y-mm-dd')">
					</td>
				</tr>
				<tr height="40px">
					<td align="right">
						第二次发放金额：
					</td>
					<td>
						<input type="text" name="save_fkje2" id="fkje2" value="${rs.fkje2 }" onblur="checkMoney(this)">
					</td>
					<td align="right">
						第二次发放时间：
					</td>
					<td>
						<input type="text" name="save_fksj2" value="${rs.fksj2 }" id="fksj2" onblur='dateFormatChg(this)' onclick="showCalendar(this.id,'y-mm-dd')">
					</td>
				</tr>
				<tr height="40px">
					<td align="right">
						第三次发放金额：
					</td>
					<td>
						<input type="text" name="save_fkje3" id="fkje3" value="${rs.fkje3 }" onblur="checkMoney(this)">
					</td>
					<td align="right">
						第三次发放时间：
					</td>
					<td>
						<input type="text" name="save_fksj3" value="${rs.fksj3 }" id="fksj3" onblur='dateFormatChg(this)' onclick="showCalendar(this.id,'y-mm-dd')">
					</td>
				</tr>
				<tr height="40px">
					<td align="right">
						第四次发放金额：
					</td>
					<td>
						<input type="text" name="save_fkje4" id="fkje4" value="${rs.fkje4 }" onblur="checkMoney(this)">
					</td>
					<td align="right">
						第四次发放时间：
					</td>
					<td>
						<input type="text" name="save_fksj4" value="${rs.fksj4 }" id="fksj4" onblur='dateFormatChg(this)' onclick="showCalendar(this.id,'y-mm-dd')">
					</td>
				</tr>
				
			</table>
		</fieldset>
		
		<div class="buttontool" id="btn" style="position: absolute;width:100%">
		
		<logic:notEqual value="view" name="doType">
			<button type="button" class="button2" id="buttonSave" onclick="saveData();">
					保&nbsp;&nbsp;&nbsp;&nbsp;存
			</button>
			&nbsp;&nbsp;&nbsp;&nbsp;
		</logic:notEqual>	
			
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
