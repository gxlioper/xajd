<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript">
		function jd(){
			if($("jd")){
				$("jd").focus();
			}
		}
		</script>
	</head>
	<body onload="jd()">
	
		<div class="tab_cur" id="jd">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>${title }-�鿴�����</a>
			</p>
		</div>
	
		<html:form action="/xljk_xyxljkjyhd" method="post">
		
		<div class="tab">
				<table width="100%" border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="8">
								<div class="btn">
									<button onclick="window.close();return false;" >
										�� ��
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<tbody>
				<tr>
					<th colspan="2">
						<font color="red">*</font>�� ��
					</th>
					<td colspan="6" align="left">
						<html:text style="width:98%" property="zt" styleId="zt"
							readonly="true" />
					</td>
				</tr>
				<tr>
					<th colspan="2">
						<bean:message key="lable.xsgzyxpzxy" />
					</th>
					<td align="left" colspan="2">
						<html:text property="xymc" style="width:180px" styleId="xymc"
							readonly="true" />
					</td>
				</tr>

				<tr>
					<th colspan="2">
						<font color="red">*</font>�� �� �� ʽ
					</th>
					<td align="left" colspan="2">
						<html:text property="hdxs"styleId="hdxs"
							readonly="true" />
					</td>
					<th colspan="2">
						�� �� �� ʽ
					</th>
					<td align="left" colspan="2">
						<html:text property="qthdxs" styleId="qthdxs" readonly="true"
							readonly="true" />
					</td>
				</tr>
				<tr >
					<th colspan="2">
						<font color="red">*</font>�� ��
					</th>
					<td align="left" colspan="2">
						<html:text property="dd" styleId="dd" readonly="true" />
					</td>
					<th colspan="2" nowrap="nowrap">
						<font color="red">*</font>�� �� �� ��
					</th>
					<td colspan="2">
						<html:text styleId="dateF"
							property="rq" readonly="true" />
					</td>
				</tr>
				<tr>
					<th colspan="2">
						�� ʼ ʱ ��
					</th>
					<td align="left" colspan="2">
						<html:text property="kssj" styleId="kssj" readonly="true" />
					</td>
					<th colspan="2">
						�� �� ʱ ��
					</th>
					<td align="left" colspan="2">
						<html:text property="jssj" styleId="jssj" readonly="true" />
					</td>
				</tr>
				<tr >
					<th colspan="2">
						<font color="red">*</font>�� �� ��
					</th>
					<td align="left" colspan="2">
						<html:text property="zcr" styleId="zcr" readonly="true" />
					</td>
					<th colspan="2">
						<font color="red">*</font>ѧ ��
					</th>
					<td align="left" colspan="2">
						<html:text property="xs" readonly="true" />
					</td>
				</tr>
				<tr >
					<th colspan="2">
						<font color="red">*</font>�� �� ѧ ��
					</th>
					<td colspan="6" align="left">
						<html:text style="width:98%" property="cyxs" styleId="cyxs"
							readonly="true" />
					</td>
				</tr>
				<tr>
					<th colspan="2" nowrap="nowrap">
						<font color="red">*</font>�� �� ѧ �� �� ��
					</th>
					<td align="left" colspan="6">
						<html:text property="rs" styleId="rs" readonly="true" />
					</td>
				</tr>
				<!-- ����ѧԺ�ļ����� -->
						<logic:equal name="xxdm" value="10395">
						<tr>
							<th colspan="2" nowrap="nowrap">
								�ļ�����
							</th>
							<td align="left" title="�����ļ�����" colspan="6">
								<a href="downloadfilewj.do?len=&wjsclj=${wjdz }" target="_blank"><font color="red">����</font></a>
							</td>
						</tr>
						</logic:equal>
				<tr>
					<th colspan="2">
						<font color="red">*</font> �� �� �� �� �� ¼
					</th>
					<td colspan="6" align="left">
						<html:textarea rows="5" style="word-break:break-all;" cols="76"  property="hdjl"
							styleId="a" readonly="true" />
					</td>
				</tr>
				<tr>
					<th colspan="2">
						<font color="red">*</font>�� �� �� �� Ч ��
					</th>
					<td colspan="6" align="left">
						<html:textarea rows="5" style="word-break:break-all;" cols="76" property="hdxg"
							styleId="a" readonly="true" />
					</td>
				</tr>
				</tbody>
			</table>
			</div>
		</html:form>
	</body>
</html>
