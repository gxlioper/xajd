<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>
<!-- ͷ�ļ� -->
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
		alert('�ſ����ܶ�������ܽ�');
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
				��ǰ����λ�ã���ѧ�����
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
				������Ϣ
			</legend>
			<table class="tbstyle" width="100%">
				<tr height="40px">
					<td align="right" width="16%">
						ѧ�ţ�
					</td>
					<td align="left" width="34%">
						<html:text property="save_xh" value="${rs.xh }" readonly="true"/>
					</td>
					<td width="16%" align="right">
						������
					</td>
					<td width="34%">
						${rs.xm }
					</td>
				</tr>
				<tr height="40px">
					<td align="right">
						�Ա�
					</td>
					<td>
						${rs.xb }
					</td>
					<td align="right">
						�꼶��
					</td>
					<td>
						${rs.nj }
					</td>
					
				</tr>
				<tr height="40px">
					<td align="right">
						<bean:message key="lable.xsgzyxpzxy" />��
					</td>
					<td>
						${rs.xymc }
					</td>
					<td align="right">
						רҵ��
					</td>
					<td>
						${rs.zymc }
					</td>
					
				</tr>
				<tr height="40px">
					<td align="right">
						�༶��
					</td>
					<td>
						${rs.bjmc }
					</td>
					<td align="right">
						���壺
					</td>
					<td>${rs.mzmc }</td>
				</tr>
				<tr height="40px">
					<td align="right">
						������ò��
					</td>
					<td>${rs.zzmmmc }</td>
					<td align="right">
						��ͬ��ţ�
					</td>
					<td>
						<font color="red">
							${rs.hdbh }
					</td>
				</tr>
				
				<tr height="40px">
					<td align="right">
						�����ܽ�
					</td>
					<td>
						${rs.zje }
					</td>
					<td align="right">
						����ʱ�䣺
					</td>
					<td>${rs.sqsj }</td>
				</tr>
				
				<tr height="40px">
					<td align="right">
						���֤�ţ�
					</td>
					<td>${rs.sfzh }</td>
					<td align="right">
						��ϵ�绰��
					</td>
					<td>${rs.lxdh }</td>
				</tr>
				<tr height="40px">
					<td align="right">
						��һ�η��Ž�
					</td>
					<td>
						<input type="text" name="save_fkje1" id="fkje1" value="${rs.fkje1 }" onblur="checkMoney(this)">
					</td>
					<td align="right">
						��һ�η���ʱ�䣺
					</td>
					<td>
						<input type="text" name="save_fksj1" value="${rs.fksj1 }" id="fksj1" onblur='dateFormatChg(this)' onclick="showCalendar(this.id,'y-mm-dd')">
					</td>
				</tr>
				<tr height="40px">
					<td align="right">
						�ڶ��η��Ž�
					</td>
					<td>
						<input type="text" name="save_fkje2" id="fkje2" value="${rs.fkje2 }" onblur="checkMoney(this)">
					</td>
					<td align="right">
						�ڶ��η���ʱ�䣺
					</td>
					<td>
						<input type="text" name="save_fksj2" value="${rs.fksj2 }" id="fksj2" onblur='dateFormatChg(this)' onclick="showCalendar(this.id,'y-mm-dd')">
					</td>
				</tr>
				<tr height="40px">
					<td align="right">
						�����η��Ž�
					</td>
					<td>
						<input type="text" name="save_fkje3" id="fkje3" value="${rs.fkje3 }" onblur="checkMoney(this)">
					</td>
					<td align="right">
						�����η���ʱ�䣺
					</td>
					<td>
						<input type="text" name="save_fksj3" value="${rs.fksj3 }" id="fksj3" onblur='dateFormatChg(this)' onclick="showCalendar(this.id,'y-mm-dd')">
					</td>
				</tr>
				<tr height="40px">
					<td align="right">
						���Ĵη��Ž�
					</td>
					<td>
						<input type="text" name="save_fkje4" id="fkje4" value="${rs.fkje4 }" onblur="checkMoney(this)">
					</td>
					<td align="right">
						���Ĵη���ʱ�䣺
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
					��&nbsp;&nbsp;&nbsp;&nbsp;��
			</button>
			&nbsp;&nbsp;&nbsp;&nbsp;
		</logic:notEqual>	
			
			<button type="button" class="button2" id="buttonSave" onclick="Close();return false;">
					��&nbsp;&nbsp;&nbsp;&nbsp;��
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
