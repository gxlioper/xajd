<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�ΰ����� -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" src="js/pclUtil.js"></script>
		<script language="javascript">

		</script> 
	</head>
	<body onload="">
		<html:form action="/xtwhSysz">
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- ������ end-->
			<div class="tab">		
			<!-- ��Ŀ������� -->
			<table class="formlist" border="0" align="center" style="width: 100%">
				<thead>
					<tr>
						<th colspan="4">
							<span>��ҳ����ͳ��
							���ѽ��ܵ��飺
							<logic:iterate name="rsList" id="rs" indexId="index">
								<logic:equal name="index" value="0">
									${rs.all }��
								</logic:equal>
							</logic:iterate>
							��
							
							</span>
						</th>
					</tr>
				</thead>
				<tbody>		
					<tr style="">
						<td align="left" colspan="2">
							<p title="${dcMap.dcnr}">�������ݣ�${dcMap.dcinfo }</p>
						</td>
					</tr>
					<logic:empty name="rsList">
						<tr style="">
							<td align="left">
								��ʱ����ͶƱ
							</td>
						</tr>
					</logic:empty>
					<logic:notEmpty name="rsList">
						<logic:iterate name="rsList" id="rs" indexId="index">
							<logic:notEqual name="index" value="0">
								<tr style="">
									<th width="35%">
										<p title="${rs.xxnr }">${rs.xxinfo }</p>	
									</th>
									<td width="">
										${rs.bl }%<img src="fdygl/fdygzdc/total.jpg" width="${rs.bl }px" height="10px"/>
									</td>
								</tr>
							</logic:notEqual>
						</logic:iterate>
					</logic:notEmpty>
				</tbody>
				<tfoot>
					<tr>
						<td colspan="4">
							<div class="btn">
								<button type="button" onclick="window.close();return false;" id="buttonClose">
									�� ��
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
			</table>
			</div>
			<!-- ��ʾ��Ϣ -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>