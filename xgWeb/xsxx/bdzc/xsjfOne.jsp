<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%><!-- ͷ�ļ� -->
<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
<script type="text/javascript" src="/xgxt/dwr/interface/pjpyZjsyzy.js"></script>
<script type="text/javascript" src="js/rcsw/rcswFunction.js"></script>
<script type="text/javascript" src="js/checkUtils.js"></script>

<body>
	<html:form action="/bdzcgl" method="post">
		<div class="title">
				<div class="title_img" id="title_m">
					��ǰ����λ�ã�${title }
				</div>
		</div>
		<input type="hidden" name="message" id="message" value="${message }"/>
		<input type="hidden" name="pkValue" id="pkValue" value="${pkValue }"/>
		
		<table class="tbstyle" width="100%">
			<tr>
				<td align="right" width="16%">
					<font color="red">*</font>ѧ�ţ�
				</td>
				<td align="left" width="34%">
					<html:text  property="save_xh" styleId="xh" readonly="true" value="${rs.xh}"/>
				</td>
				<td width="16%">
					<div align="right">
						������
					</div>
				</td>
				<td width="34%">
					<html:text  property="save_xm" styleId="xh"  value="${rs.xm}"/>
				</td>
			</tr>
			<tr>
				<td align="right">
					�꼶��
				</td>
				<td>
					${rs.nj }
				</td>
				<td align="right">
					<bean:message key="lable.xsgzyxpzxy" />��
				</td>
				<td>
					${rs.xymc }
				</td>			
			</tr>
			<tr>
				<td align="right">
					רҵ��
				</td>
				<td>
					${rs.zymc }
				</td>
				<td align="right">
					�༶��
				</td>
				<td>
					${rs.bjmc }
				</td>			
			</tr>
			<tr>
				<td align="right">
					<font color="red">*</font>�շ���ȣ�
				</td>
				<td>
					<html:select property="save_sfqjdm" value="${rs.sfqjdm }">
						<html:options collection="xnList" property="xn" labelProperty="xn"/>
					</html:select>
				</td>
				<td align="right">
					<font color="red">*</font>�շ���Ŀ���룺
				</td>
				<td>
					<html:text property="save_sfxmdm" maxlength="10" value="${rs.sfxmdm }"></html:text>
				</td>
			</tr>
			<tr>
				<td align="right">
					Ӧ�ս�
				</td>
				<td>
					<html:text property="save_ysje" maxlength="10" value="${rs.ysje }" onblur="checkMoney(this);" style="width:40px"/><font color="red">(����20.00��20.0��20)</font>
				</td>
				<td align="right">
					ʵ�ս�
				</td>
				<td>
					<html:text property="save_ssje" maxlength="10" value="${rs.ssje }" onblur="checkMoney(this);" style="width:40px"></html:text>
				</td>
			</tr>
			<tr>
				<td align="right">
					�շ�ʱ�䣺
				</td>
				<td>
					<html:text property="save_sfsj" styleId="sfsj" onclick="return showCalendar('sfsj','y-mm-dd');" onblur="dateFormatChg(this)" readonly="true"  value="${rs.sfsj }"></html:text>
				</td>
				<td></td>
				<td></td>
			</tr>
		</table>
		<div class="buttontool" id="btn" style="position: absolute;width:100%">
				<logic:equal value="modi" name="doType">
					<button type="button" class="button2" id="buttonSave" onclick="saveUpdate('/xgxt/bdzcgl.do?method=xsjfOne&doType=save','save_xh-save_sfqjdm-save_sfxmdm');">
						��&nbsp;&nbsp;��
					</button>
				</logic:equal>
				<logic:equal value="view" name="doType">
					<button type="button" class="button2" id="buttonSave" onclick="window.close();return false;">
						��&nbsp;&nbsp;��
					</button>
				</logic:equal>
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
