<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
		<script type="text/javascript" src="js/wjcfFuction.js"></script>
		<script type="text/javascript">
			function saveCxcf() {
				var cxsj = "";
				var cxwh = "";
				if($("jcsj")){
					cxsj = document.getElementById('jcsj').value;
				}
				
				if($("jcwh")){
					cxwh = document.getElementById('jcwh').value;
				}
				
				var blog=true;
				
				if ($("jcsj") &&(cxsj==null || cxsj=="") ) {
					alert("��\*���ֶα�����д��");
					return false;
				}
				
				if ($("jcwh") &&(cxwh==null || cxwh=="")) {
					alert("��\*���ֶα�����д��");
					return false;
				}
				refreshForm('wjcfcsmzwh.do?method=cxcfplsh&operType=save');
			}
		</script>
	</head>
	<body>
		<html:form action="/wjcfcsmzwh" method="post">
			<input type="hidden" name="userType" id="userType"
				value="${userType }" />
			<input type="hidden" name="failinfo" id="failinfo"
				value="${failinfo}" />
			<input type="hidden" name="pkString" id="pkString"
				value="${pkString }" />
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>Υ�ʹ��� - ������ֹ��� - �������</a>
				</p>
			</div>
			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="2">
								<span>�������</span>
							</th>
						</tr>
					</thead>
					<tfoot>
						<tr>
							<td colspan="2">
								<div class="btn">
									<button type="button" class="" id="btn_save"
										onclick="saveCxcf()">
										�� ��
									</button>
									&nbsp;&nbsp;&nbsp;&nbsp;
									<button type="button" class="" id="btn_close" onclick="window.close();return false;">
										�� ��
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<tbody>
						<tr style="height: 23px">
							<th align="right" style="width: 30%">
								<font color="red">*</font>���
							</th>
							<td align="left" width="65%">
								<html:select property="sh" style="width:100px" styleId="sh">
									<html:options collection="shList" property="en"
										labelProperty="cn" />
								</html:select>
							</td>
						</tr>
						<logic:notEqual value="xy" name="userType">
							<logic:notEqual value="true" name="fdyQx">
							<logic:notEqual value="true" name="bzrQx">
							<tr style="height: 23px">
								<th align="right">
									<font color="red">*</font>���ʱ��
								</th>
								<td align="left">
									<html:text property="jcsj" styleId="jcsj"
										onblur="dateFormatChg(this)" style="cursor:hand;"
										onclick="return showCalendar('jcsj','y-mm-dd');"></html:text>
								</td>
							</tr>
							<tr>
								<th align="right">
									<font color="red">*</font>����ĺ�
								</th>
								<td align="left">
									<html:text property="jcwh" styleId="jcwh" maxlength="30"></html:text>
								</td>
							</tr>
							<logic:equal value="10827" name="xxdm">
							<tr>
								<th align="right">
									������
								</th>
								<td align="left">
									<html:text property="cxjg" styleId="cxjg" maxlength="30"></html:text>
								</td>
							</tr>
							</logic:equal>
						</logic:notEqual>
						</logic:notEqual>
						</logic:notEqual>
						<tr>
							<th align="right">
								������
							</th>
							<td align="left">
								<html:textarea property="shyj" rows="4"
									style="width:95%;word-break:break-all;" styleId="shyj">
								</html:textarea>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<!-- �������ʾҳ�� -->
			<jsp:include flush="true" page="/syscommon/saveprompt.jsp"></jsp:include>
		</html:form>
	</body>
</html>
