<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" src="js/gygl/gyglTyFunction.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getGyglDAO.js'></script>
		<script language="javascript" src="js/gygl/wsjc.js"></script>
		<script language="javascript" src="js/xgutil.js"></script>
		<script language="javascript">
		</script>
	</head>

	<body onload="">
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>������� - �������</a>
			</p>
		</div>
		<!-- ���� end-->
		<html:form action="/zjcmGygl">
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- ������ end-->
			<!-- ���������� -->
			<table class="formlist" border="0" align="center" style="width: 100%">
				<thead>
					<tr>
						<th colspan="4">
							<span>����������</span>
						</th>
					</tr>
				</thead>
				<tbody>		
					<tr style="height: 23px">
						<th align="right" width="20%">
							ѧ��
						</th>
						<td align="left" width="30%">
							${rs.xh }
						</td>
						<th align="right" width="20%">
							����
						</th>
						<td align="left">
							${rs.xm }
						</td>
					</tr>
					<tr style="height: 23px">
						<th align="right">
							�꼶
						</th>
						<td align="left">
							${rs.nj }
						</td>
						<th align="right">
							У��
						</th>
						<td align="left">
							${rs.xqmc }
						</td>
					</tr>
					<tr style="height: 23px">
						<th align="right">
							<bean:message key="lable.xsgzyxpzxy" />
						</th>
						<td align="left">
							${rs.xymc }
						</td>
						<th align="right">
							¥��
						</th>
						<td align="left">
							${rs.ldmc }
						</td>
					</tr>
					<tr style="height: 23px">
						<th align="right">
							רҵ
						</th>
						<td align="left">
							${rs.zymc }
						</td>
						<th align="right">
							����
						</th>
						<td align="left">
							${rs.cs }
						</td>
					</tr>
					<tr style="height: 23px">
						<th align="right">
							�༶
						</th>
						<td align="left">
							${rs.bjmc }
						</td>
						<th align="right">
							���Һ�
						</th>
						<td align="left">
							${rs.qsh }
						</td>
					</tr>
					<tr style="height: 23px">
						<th align="right">
							������ò
						</th>
						<td align="left">
							${rs.zzmmmc }
						</td>
						<th align="right">
							��鲿��
						</th>
						<td align="left">
							${rs.bmmc }
						</td>
					</tr>
					<tr style="height: 23px">
						<th align="right">
							<logic:equal name="jczq" value="��">
								����ܴ�
							</logic:equal>
							<logic:equal name="jczq" value="��">
								���ʱ��
							</logic:equal>
						</th>
						<td align="left">
							<logic:equal name="jczq" value="��">
								��${rs.jczc }��
							</logic:equal>
							<logic:equal name="jczq" value="��">
								${rs.jcsj }
							</logic:equal>
						</td>
						<th align="right">
							<logic:equal name="lrxs" value="����">
								<logic:notEqual name="doType" value="view">
									<font color="red">*</font>
								</logic:notEqual>
								������
							</logic:equal>
							<logic:equal name="lrxs" value="�ȼ�">
								�����ȼ�
							</logic:equal>
						</th>
						<td align="left">
							<logic:equal name="doType" value="view">
								<logic:equal name="lrxs" value="����">
									${rs.wsffs }
								</logic:equal>
								<logic:equal name="lrxs" value="�ȼ�">
									${rs.wsfdj }
								</logic:equal>
							</logic:equal>
						</td>
					</tr>
					<tr style="height: 23px">
						<th align="right">
							��ע
						</th>
						<td align="left" colspan="3">						
							<html:textarea name="rs" property="jcbz" styleId="bz" rows="5"
								style="width: 500px" onblur="chLeng(this,250)" readonly="true"/>
						</td>
					</tr>
				</tbody>
				<tfoot>
					<tr bgcolor="EEF4F9" align="center">
						<td colspan="4">
							<div class="btn">
								<button id="buttonClose" onclick="Close();return false;"
									style="width: 80px">
									��	��
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
			</table>
			<!-- ��ʾ��Ϣ -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>
