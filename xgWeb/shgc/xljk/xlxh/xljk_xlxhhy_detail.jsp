<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�ΰ����� -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<!-- ͷ -->
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	</head>
	<body>
		<script language="javascript" src="js/function.js"></script>

		<html:form action="/xljk_xlxhhy" method="post">
			<!-- ���� -->
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ�ã�</em><a>������ - ����Э�� - ��Ա������Ϣ - �鿴��Ա������Ϣ</a>
				</p>
			</div>
			<!-- ���� end-->
			<!-- ��Ա������Ϣ -->
			<table class="formlist" border="0" align="center" style="width: 100%">
				<thead>
					<tr>
						<th colspan="6">
							<span>��Ա������Ϣ</span>
						</th>
					</tr>
				</thead>
				<tbody>
				<tr style="height:22px">
					<th align="right" colspan="2">
						ѧ��
					</th>
					<td align="left">
						<html:text name="rs" property="hyxh" styleId="hyxh"
							readonly="true" />
					</td>
					<th align="right">
						����
					</th>
					<td align="left">
						<html:text name="rs" property="xm" styleId="hyxm"
							readonly="true" />
					</td>
				</tr>
				<tr style="height:22px">
					<th align="right" colspan="2">
						�Ա�
					</th>
					<td align="left">
						<html:text name="rs" property="xb" styleId="xb" readonly="true" />
					</td>
					<th align="right">
						��������
					</th>
					<td align="left">
						<html:text name="rs" property="csrq" styleId="csrq"
							readonly="true" />
					</td>
				</tr>
				<tr style="height:22px">
					<th align="right" colspan="2">
						<bean:message key="lable.xb" />
					</th>
					<td align="left">
						<html:text name="rs" property="xymc" styleId="xymc"
							readonly="true" />
					</td>
					<th align="right">
						�༶
					</th>
					<td align="left">
						<html:text name="rs" property="bjmc" styleId="bjmc"
							readonly="true" />
					</td>
				</tr>
				<tr style="height:22px">
					<th align="right" colspan="2" nowrap="nowrap">
						�ֻ�����
					</th>
					<td align="left">
						<html:text name="rs" property="sjhm" styleId="zy" readonly="true" />
					</td>
					<th align="right" nowrap="nowrap">
						���ҵ绰
					</th>
					<td align="left">
						<html:text name="rs" property="qsdh" readonly="true" />
					</td>
				</tr>
				<tr>
					<th align="right" colspan="2">
						��Ա���
					</th>
					<td align="left">
						<html:text name="rs" property="hybh" readonly="true" />
					</td>

					<th align="right">
						ְ��
					</th>
					<td align="left">
						<html:text name="rs" property="zw" readonly="true" />
					</td>
				</tr>
				<tr>
					<th align="right" colspan="2">
						��ע
					</th>
					<td colspan="4" align="left">
						<html:textarea rows="5" name="rs" style="width:98%" property="bz"
							styleId="a" readonly="true" />
					</td>
				</tr>
				</tbody>
				<tfoot>
					<tr bgcolor="EEF4F9" align="center">
						<td colspan="6"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
								&nbsp;&nbsp;
								<div class="btn">
								<button id="buttonClose" onclick="Close();return false;"
									style="width: 80px">
									��	��
								</button>
								</div>
							</div>
						</td>
					</tr>
				</tfoot>
			</table>
			<div id="tmpdiv"></div>
		</html:form>
	</body>
</html>
