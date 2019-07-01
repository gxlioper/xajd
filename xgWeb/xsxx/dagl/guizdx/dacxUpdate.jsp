<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="js/check.js"></script>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/dtjs/dtjsFunction.js"></script>
		<script type="text/javascript">
		function disInput(){
			$("sfbrtj").disabled="true";
			$("datddz").disabled="true";
			
		}
		</script>
	</head>
	<body onload="disInput()" >

		<html:form action="/guizdxDagl" method="post">
			<input type="hidden" id="doType" name="doType" value="${doType}" />
			<input type="hidden" name="xxdm" id="xxdm"
				value="<bean:write name="xxdm" />" />
			
			<div class="tab_cur" id="jd">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>${title}</a>
				</p>
			</div>

			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="6">
								<span>��Ŀ����</span>
							</th>
						</tr>
					</thead>

					<tbody>
						<tr>
							<th style="width:16%">
								<font color="red">*</font>ѧ��
							</th>
							<td style="width:34%">

								<input type="text" name="xh" id="xh" value="${rs.xh}"
									readonly="readonly" />
							</td>

							<th style="width:16%">
								����
							</th>
							<td style="width:34%">
								${rs.xm}
							</td>
						</tr>
						<tr>
							<th style="width:16%">
								�Ա�
							</th>
							<td style="width:34%">
								${rs.xb}
							</td>

							<th style="width:16%">
								�꼶
							</th>
							<td style="width:34%">
								${rs.nj}
							</td>
						</tr>
						<tr>
							<th style="width:16%">
								<bean:message key="lable.xb" />
							</th>
							<td style="width:34%">
								${rs.xymc}
							</td>

							<th style="width:16%">
								רҵ
							</th>
							<td style="width:34%">
								${rs.zymc}
							</td>
						</tr>
						<tr>
							<th style="width:16%">
								�༶
							</th>
							<td style="width:34%">
								${rs.bjmc}
							</td>

							<th style="width:16%">
								�Ƿ����ᵵ
							</th>
							<td style="width:34%">
								<html:select name="rs" property="sfbrtj" styleId="sfbrtj">
									<html:option value=""></html:option>
									<html:option value="��">��</html:option>
									<html:option value="��">��</html:option>
								</html:select>
							</td>
						</tr>
						<tr>
							<th style="width:16%">
								����Ͷ�ݵ�ַ
							</th>
							<td style="width:34%" colspan="3">
								<html:textarea name='rs' property='datddz' styleId="datddz"
									style="word-break:break-all;width:99%"
									onblur="chLeng(this,100);" rows='4' />
							</td>
						</tr>
					</tbody>
					
					<logic:notEmpty name="xsdadmInfo">
					<thead>
						<tr>
							<th colspan="4">
								<span>������������</span>
							</th>
						</tr>
					</thead>
					<logic:iterate name="xsdadmInfo" id="lx" indexId="index">
						<tr>
							
							<td colspan="4">
								${lx.mc }
							</td>
						</tr>
					</logic:iterate>
					</logic:notEmpty>
					
					<logic:notEmpty name="zxdadmInfo">
					<thead>
						<tr>
							<th colspan="4">
								<span>��У����������</span>
							</th>
						</tr>
					</thead>
					<logic:iterate name="zxdadmInfo" id="lx" indexId="index">
						<tr>
							
							<td colspan="4">
								${lx.mc }
							</td>
						</tr>
					</logic:iterate>
					</logic:notEmpty>
					
					<logic:notEmpty name="bydadmInfo">
					<thead>
						<tr>
							<th colspan="4">
								<span>��ҵ����������</span>
							</th>
						</tr>
					</thead>
					<logic:iterate name="bydadmInfo" id="lx" indexId="index">
						<tr>
							
							<td colspan="4">
								${lx.mc }
							</td>
						</tr>
					</logic:iterate>
					</logic:notEmpty>
					
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"
									<span class="red">*</span>"Ϊ������
								</div>
								<div class="btn">
									<button type="button" onclick="Close();return false;">
										�� ��
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
			<logic:present name="result">
				<script>
					alert('${message}');
					if (window.dialogArguments) {
						window.close();
						window.dialogArguments.document.getElementById('search_go').click();
					}
				</script>
			</logic:present>
		</html:form>
	</body>
</html>
