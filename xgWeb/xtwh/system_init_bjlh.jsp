<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	</head>
	<script language="javascript" src="js/check.js"></script>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/jsFunction.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
	<script type="text/javascript" src="js/jxglFunction.js"></script>
	<script type='text/javascript'
		src='/xgxt/dwr/interface/getXmlgSzdwDAO.js'></script>
	<script type="text/javascript">
	
	function queryxxdm(){
	  var xxmc = document.getElementById("xxmc").value;
	 document.forms[0].action = "/xgxt/system_init.do?doType=search&xxmc="+xxmc;
     document.forms[0].submit();
    }
	
	function savextsz(){
		var xxmc = document.getElementById("xxmc").value;
		var setxxdm = document.getElementById("setxxdm").value;
	
		if(xxmc==""){
		  alert("ѧУ���Ʋ���Ϊ�գ�");
		  return false;
		}
		if(setxxdm==""){
		  alert("ѧУ���벻��Ϊ�գ�");
		  return false;
		}
		if(checkXnNd("xn","nd")){
			document.forms[0].action = "/xgxt/system_init.do?act=save";
	     	document.forms[0].submit();
			}
	    }
	
	
	</script>
	<body>
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>ϵͳά�� - ϵͳ��ʼ�� - ϵͳ���� </a>
			</p>
		</div>
		<!-- ���� end-->
		<html:form action="/chgPass" method="post">
			<div class="tab">
				<table class="formlist" border="0" align="center"
					style="width: 100%">
					<thead>
						<tr>
							<th colspan="2">
								ϵͳ����
							</th>
						</tr>
					</thead>
					<tr>
						<td align="right">
							ѧУ���ƣ�
						</td>
						<td align="left">
							<input type="text" name="xxmc" value="<bean:write name="xxmc"/>" onkeypress="if(window.event.keyCode==43) queryxxdm()" />
						</td>
					</tr>
					<tr>
						<td align="right">
							ѧУ���룺
						</td>
						<td align="left">
							<input type="text" name="setxxdm" value="<bean:write name="setxxdm"/>" readonly="true"/>
						</td>
					</tr>									
					<tr>
						<td align="right">	
							��ǰѧ��ȣ�
						</td>
						<td align="left">
							<html:select property="xn">
								<html:options collection="xnList" property="xn"
									labelProperty="xn" />
							</html:select>
						</td>
					</tr>
					<tr>
						<td align="right">	
							��ǰ��ȣ�
						</td>
						<td align="left">
							<html:select property="nd">
								<html:options collection="xnList" property="nd"
									labelProperty="nd" />
							</html:select>
						</td>
					</tr>					
					<tr>
						<td align="right">
							��ǰѧ�ڣ�
						</td>
						<td align="left">
							<html:select property="xq">
								<html:options collection="xqList" property="xqdm"
									labelProperty="xqmc" />
							</html:select>
						</td>
					</tr>
					<tr>
						<td align="right">
							��ѧ��һ�����ڣ�
						</td>
						<td align="left">
							<input type="text" readonly
							onclick="return showCalendar('kxdyt','y-mm-dd');"
							value="<bean:write name="kxdyt" />" name="kxdyt"
							id="kxdyt" />
						</td>
					</tr>
					<tr>
						<td align="right">
							ѧ��������
						</td>
						<td align="left">
							<input type="text" name="xqzs" value="<bean:write name="xqzs"/>" maxlength="2"
							onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))" />
						</td>
					</tr>
					<tfoot>
						<tr bgcolor="EEF4F9" align="center">
							<td colspan="2">
								<div class="btn">
									<button type="button" name="Submit2" class="button2"
									onclick="savextsz();">
										�� ��
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
			<logic:notEmpty name="commanForm" property="changed" scope="request">
				<logic:equal value="yes" name="commanForm" property="changed">
					<script>alert("����ɹ���");</script>
				</logic:equal>
				<logic:equal value="no" name="commanForm" property="changed">
					<script>alert("����ʧ�ܣ�");</script>
				</logic:equal>
			</logic:notEmpty>
			<logic:equal name="nofind" value="nofind">
			     <script>alert("�޷��ڴ�����ҵ���ѧУ��Ӧ���룡");</script>
			</logic:equal>
			
		</html:form>
	</body>
</html>
