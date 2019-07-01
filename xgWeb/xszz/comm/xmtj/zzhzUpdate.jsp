<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�ΰ����� -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/xszz/jtcy.js"></script>
		<script language="javascript" src="js/xszz/xszzComm.js"></script>
		<script language="javascript" src="js/xszz/xszzFunction.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getOtherData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getXszzInfo.js'></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script language="javascript" src="js/xszz/xszzInit.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/interface/getStuDetails.js"></script>
	</head>

	<body onload="">
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a><bean:write name="title" /></a>
			</p>
		</div>
		<!-- ���� end-->
		<html:form action="/commXszz">
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- ������ end-->
			<!-- ѧ��������� -->
			<table class="formlist" border="0" align="center" style="width: 100%">
				<thead>
					<tr>
						<th colspan="4">
							<span>ѧ���������</span>
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
						<th align="right"  width="20%">
							����
						</th>
						<td>
							${rs.xm }
						</td>
					</tr>
					<tr style="height: 23px">
						<th align="right">
							�Ա�
						</th>
						<td align="left">
							${rs.xb }
						</td>
						<th align="right">
							��ϵ�绰
						</th>
						<td>
							${rs.lxdh }
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
							Ժϵ
						</th>
						<td>
							${rs.xymc }
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
							�༶
						</th>
						<td>
							${rs.bjmc }
						</td>
					</tr>
				</tbody>
			</table>
			<!-- ѧ��������� end-->
			<!-- ���������� -->
			<table class="formlist" border="0" align="center" style="width: 100%">
				<thead>
					<tr>
						<th colspan="5">
							<span>��������������${kssj }����${jssj }��</span>
						</th>
					</tr>
				</thead>
				<tbody>		
					<tr style="height: 23px">
						<td align="center">
							��Ŀ����
						</td>
						<td align="center">
							��Ŀ���
						</td>
						<td align="center">
							����
						</td>
						<td align="center">
							���
						</td>
						<td align="center">
							����ʱ��
						</td>
					</tr>
					<logic:empty name="rsList">
						<tr>
							<td align="center" colspan="5">
								δ����κ�����
							</td>
						</tr>
					</logic:empty>
					<logic:notEmpty name="rsList">
						<logic:iterate name="rsList" id="xm" indexId="num">
							<logic:notEqual name="num" value="${rsNum - 1 }">
								<tr>
									<td align="center">
										${xm.xmmc }
									</td>
									<td align="center">
										${xm.xmlb }
									</td>
									<td align="center">
										${xm.xmzzjb }
									</td>
									<td align="center">
										${xm.xmzzje }
									</td>
									<td align="center">
										${xm.sqsj }
									</td>
								</tr>
							</logic:notEqual>
							<logic:equal name="num" value="${rsNum - 1}">
								<tr>
									<td align="center" colspan="3">
										�ܼ�
									</td>
									<td align="center" colspan="2">
										${xm.zje }
									</td>
								</tr>
							</logic:equal>
						</logic:iterate>
					</logic:notEmpty>
				</tbody>
			</table>
			<!-- ���������� end-->
			<table border="0" class="formlist" align="center" style="width: 100%">
				<tfoot>
					<tr>
						<td align="center">
							<div class="btn">
								<button type="button"  id="buttonSave" onclick="window.close();return false;" style="width: 80px">
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