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
				��ǰ����λ�ã���ѧ���������Ϣ��ϸ
			</div>
		</div>
		<input type="hidden" id="url" name="url" value="/typj.do?method=jxjsq" />
		<input type="hidden" id="getStuInfo" name="getStuInfo"
			value="xm-xb-zymc-xymc-bjmc-mzmc-zzmmmc-csrq-lxdh" />
		<input type="hidden" name="message" id="message" value="${message }" />
		<input type="hidden" name="save_xq" value="��"/>
		<input type="hidden" name="save_sqsj" value="��" id="sqsj"/>
		<input type="hidden" id="xxdm" value="${xxdm }"/>
		<input type="hidden" name="now" value="${nowTime }" id="now"/>
		<input type="hidden" name="save_sqsj" id="sqsj" value="${rs.sqsj }" />
		<input type="hidden" name="save_xmdm" id="xmdm" value="${rs.xmdm }" />
		<input type="hidden" name="xmb" id="xmb" value="gjzxdkb"/>
		<input type="hidden" name="xh"  value="${rs.xh }"/>
		
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
						${rs.xh }
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
						</font>
					</td>
				</tr>
				<tr height="40px">
					<td align="right">
						�����ܽ�
					</td>
					<td>
						<input type="text" name="save_zje" value="${rs.zje }" onblur="checkMoney(this)"/>
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
						<bean:message key="lable.xsgzyxpzxy" />��ˣ�
					</td>
					<td>${rs.xysh }</td>
					<td align="right">
						ѧУ��ˣ�
					</td>
					<td>${rs.xxsh }</td>
				</tr>
				<tr height="80px">
					<td align="right">
						<bean:message key="lable.xsgzyxpzxy" />��������
					</td>
					<td colspan="3">
						${rs.xyshyj }
					</td>
				</tr>
				<tr height="80px">
					<td align="right">
						ѧУ��������
					</td>
					<td colspan="3">
						${rs.xxshyj }
					</td>
				</tr>
			</table>
		</fieldset>
		
		<div class="buttontool" id="btn" style="position: absolute;width:100%">
			
			<button type="button" class="button2" onclick="print();" style="width:80px">
				��ӡ����
			</button>
			&nbsp;&nbsp;&nbsp;&nbsp;
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
