<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
	<script language="javascript" src="js/String.js"></script>
	<script language="javascript" src="js/check.js"></script>
	<script language="javascript" src="js/qtsjFunction.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/interface/systemFunction.js"></script>
	<script	type="text/javascript">
		function getPynr(obj){
			var py = trim($('pynr').innerHTML) == "" ? obj.cells[0].innerHTML : "\n\r"+obj.cells[0].innerHTML;
			$('pynr').value = $('pynr').value + py;
		}
		
	</script>

	<style type="text/css">
		table{
			border-collapse:collapse;
		}
		table span{
			color:red;
		}
		textarea{
			width:97%;
			word-break:break-all;
		}
	</style>

</head>
<body>
	<html:form action="/msxldc" method="post">
		<input type="hidden" name="pkValue" value="${param.pkValue }"/>
		
		<table class="tbstyle" width="100%">
			<thead>
				<tr>
					<td colspan="4">
						ѧ����Ϣ
					</td>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td align="right" width="15%">ѧ�ţ�</td>
					<td align="left" width="35%">
						${rs.xh }
						<input type="hidden" name="xh" value="${rs.xh }"/>
					</td>
					<td align="right" width="15%">������</td>
					<td width="35%">
						${rs.xm }
					</td>
				</tr>
				<tr>
					<td align="right"><bean:message key="lable.xb" />��</td>
					<td>
						${rs.xymc }
					</td>
					<td align="right">רҵ��</td>
					<td>
						${rs.zymc }
					</td>
				</tr>
				<tr>
					<td align="right">�༶��</td>
					<td>
						${rs.bjmc }
					</td>
					<td align="right">�꼶��</td>
					<td>
						${rs.nj }
					</td>
				</tr>
				<tr>
					<td align="right">�ܷ֣�</td>
					<td>
						${rs.zf }
					</td>
					<td align="right"></td>
					<td></td>
				</tr>
				<tr>
					<td align="right">�������ݣ�</td>
					<td colspan="3">
						<html:textarea property="pynr" rows="4" cols="3"
							onblur="chLeng(this,1000);" styleId="pynr" value="${rs.pynr}"></html:textarea>
					</td>
				</tr>
			</tbody>
		</table>
		<br/>
		<div class="border_01 formbox">
			<!--���й������� ����start-->
			<!--���й������� ����end-->
			<div style="height: 150px;overflow: auto">
				<table width="100%" class="tbstyle">
					<thead>
						<tr>
							<td>
								�������ݣ�˫��������������������ݣ�
							</td>
						</tr>
					</thead>
					<tbody>
						<logic:iterate id="py" name="pyList">
							<tr class="alt" ondblclick="getPynr(this);">
									<td>${py.pynr }</td>
							</tr>
						</logic:iterate>
					</tbody>
				</table>
			</div>
		</div>
		
		<div class="buttontool" id="btn" style="position: absolute;width:100%">
			<button class="button2" onclick="saveData('msxldc.do?method=pywhUpdate&doType=save','');">
				����
			</button>
			<button class="button2" onclick="window.close();return false;">
				�ر�
			</button>
		</div>
	</html:form>
	<logic:present name="message">
		<input type="hidden" id="message" value="${message }" />
		<script type="text/javascript">
			jQuery(function (){
				alertInfo(jQuery('#message').val(), function(){
					window.close();
					if(window.dialogArguments && window.dialogArguments.document.all("search_go")){
						window.dialogArguments.document.getElementById('search_go').click();
					}
				});
			});
		</script>
	</logic:present>
</body>
</html>
