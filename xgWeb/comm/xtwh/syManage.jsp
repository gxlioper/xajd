<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/comm/commFunction.js"></script>
	</head>

	<body onload="">
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a><bean:write name="title" /></a>
			</p>
		</div>
		<!-- ���� end-->
		<html:form action="/commXtwh">
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- ������ end-->
			<!-- ��ҳ���� -->
			<table class="formlist" border="0" align="center" style="width: 100%">
				<thead>
					<tr>
						<th colspan="2">
							<span>��ҳά��</span>
						</th>
					</tr>
				</thead>
				<tbody>		
					<tr style="height: 23px">
						<th align="right" width="20%">
							��ʾ��䣺
						</th>
						<td align="left" width="">
							<html:text name="rs" property="tsyj" maxlength="50" style="width: 80%"/>
						</td>
					</tr>
<%--					<tr style="height: 23px">--%>
<%--						<th align="right" colspan="2">--%>
<%--							<font color="blue">������ʾ��������ʾ�����Ҫ����tomcat��������,��ע��~^_^~</font>--%>
<%--						</th>--%>
<%--					</tr>--%>
				</tbody>
				<tfoot>
					<tr bgcolor="EEF4F9" align="center">
						<td colspan="2">
							<div class="btn">
								<button type="button" id="buttonSave" onclick="saveUpdate('/xgxt/commXtwh.do?method=syManage&doType=save','')">
									�� ��
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
			</table>
			</br>
			</br>
			</br>
			</br></br>
			</br>
			</br>
			</br></br>
			</br></br>
			</br>
			</br>
			</br></br>
			</br>
			</br></br>
			</br>
			</br>
		</html:form>
		<!-- ��ʾ��Ϣ -->
		<%@ include file="/comm/other/tsxx.jsp"%>
		<!-- ��ʾ��Ϣ end-->
	</body>
</html>
