<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="xsxx/fbgl/fbgl/js/fb.js"></script>
		<script type="text/javascript" src="xsxx/fbgl/gzsd/gzyl.js"></script>
		<script type="text/javascript">
			jQuery(function($){
			});
		</script>
	</head>
	<body>
		<div>
			<html:form action="/fbglbbgl.do?method=tzbj&type=save">
			<input type="hidden" id="xh" value="${data.xh}"/>
			<input type="hidden" id="yxxs" value="${data.yxxs}"/>
			<input type="hidden" id="ids" value="${ids}"/>
				<table width="100%" border="0" class="formlist">
					<tr>
						<th align="right" width="25%">
							��ѡ��ѧ��
						</th>
						<td align="left" style="color: red;">
							${data.yxxs}
						</td>
					</tr>
					<tr>
						<th align="right">
							�꼶
						</th>
						<td align="left">
						   ${data.nj}
						</td>
					</tr>
					<tr>
						<th align="right">
							<bean:message key="lable.xb" />
						</th>
						<td align="left">
							${data.xymc}
						</td>
					</tr>
					<tr>
						<th align="right">
							רҵ
						</th>
						<td align="left">
							${data.zymc}
						</td>
					</tr>
					<tr>
						<th align="right">
							<font color='red'>*</font>�༶
						</th>
						<td align="left">
							<html:select property="bjdm" styleId="bjdm">
								<html:options collection="bjlist" property="bjdm"
									labelProperty="bjmc" />
							</html:select>						
						</td>
					</tr>
					
				</table>
			</html:form>
		</div>
		<table width="100%" border="0" class="formlist"
			style="position: fixed; _position: absolute; bottom: 0;">
			<tfoot>
				<tr>
					<td colspan="2">
						<div class="bz">
							"
							<span class="red">*</span>"Ϊ������
						</div>
						<div class="btn">
							<button id="buttonSave"
								onclick="saveTb();return false;"
								type="button">
								����
							</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button id="buttonClose" onclick="iFClose();" type="button">
								�� ��
							</button>
						</div>
					</td>
				</tr>
			</tfoot>
		</table>
	</body>
</html>
