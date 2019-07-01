<%@ page language="java" contentType="text/html; charset=GBK"%>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean"
	prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic"
	prefix="logic"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles"
	prefix="tiles"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-template"
	prefix="template"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-nested"
	prefix="nested"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
	<head>
		<title><bean:message key="lable.title" />
		</title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control"
			http-equiv="no-cache, must-revalidate" />
		<meta http-equiv="Expires" http-equiv="0" />

		<meta name="Copyright" content="������� zfsoft" />
		
	</head>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
		<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<base target="_self">
	<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script language="javascript">
	function savejyxy(){
		var pk = document.getElementById("xh").value;
		if(pk==""){
			alert("ѧ�Ų���Ϊ�գ�");
			return false;
		}
		document.forms[0].action = "/xgxt/jyxxtjwh.do?act=saveupdate&pk="+pk;
		document.forms[0].submit();
    }
    
   
    
    function refreshtheweb(){
    var byqxdm = document.getElementById("byqxdm").value;
    var dwdq = document.getElementById("dwdq").value;
    var xsxh = document.getElementById("xsxh").value;
    var zgbm = document.getElementById("zgbm").value;
    
                document.forms[0].action = "/xgxt/savejyxy.do?doType=go&byqxdm="+byqxdm+"&dwdq="+dwdq+"&xsxh="+xsxh+"&zgbm"+zgbm;
                document.forms[0].submit();       
    }
    
    function isNumber(s){
	var p = /^(-|\+)?\d+$/;
	return p.test(s); 
    }  
	
	function jyxyreinput(){
	            window.location.href="/xgxt/jyxy_input.do?act=first";  
	}
	</script>
	<body>
		<script language="javascript" src="js/function.js"></script>
		<html:form action="/jyxxtjwh.do" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰλ�ã���ҵ���� - ��ҵ��Ϣ - ��ҵ��Ϣ ͳ��ά��
				</div>
			</div>
			<logic:empty name="rs">
				<br />
				<br />
				<p align="center">
					�д�������
				</p>
			</logic:empty>
			<logic:notEmpty name="rs">
				<logic:equal name="rs" property="stuExists" value="no">
					<script>
    alert("�������ѧ����Ч!");
    </script>
				</logic:equal>
				<html:hidden name="rs" property="nd" />

				<table width="100%" id="rsT" class="tbstyle">
					<thead>
					</thead>
					<tr style="height:22px">
							<td align="right" style="width=18%">
								<font color="red">*</font>ѧ�ţ�
							</td>
							<td align="left" style="width=32%">
								<html:text name='rs' property="xh" styleId="xsxh"
									style="width:210px" readonly="true"/>
							<logic:equal value="123466" name="xxdm" scope="session">
								<button onclick="showTopWin('/xgxt/jyxyTurnInfo.do',750,550);"
									class="btn_01" id="buttonFindStu">
									ѡ��
								</button>
							</logic:equal>
							</td>
						<td align="right" style="width:20%">
							<font color="red">*</font>������
						</td>
						<td align="left">
							<html:text name="rs" property="xm"  style="width: 210px" readonly="true"/>
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							ԺУ���룺
						</td>
						<td align="left">
							<html:text name="rs" property="yxdm" 
								style="width:210px"  readonly="true"/>
						<td align="right">
							ԺУ���ƣ�
						</td>
						<td align="left">
							<html:text name="rs" property="yxmc" 
								style="width:210px"  readonly="true"/>
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							����<bean:message key="lable.xsgzyxpzxy" />��
						</td>
						<td align="left">
							<html:text name="rs" property="szyx" style="width:210px"  readonly="true"/>
						</td>
						<td align="right">
							���ڰ༶��
						</td>
						<td align="left">
							<html:text name="rs" property="szbj" style="width:210px"  readonly="true"/>
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							רҵ���룺
						</td>
						<td align="left">
							<html:text name="rs" property="zydm" style="width:210px"  readonly="true"/>
						</td>
						<td align="right">
							רҵ���ƣ�
						</td>
						<td align="left">
							<html:text name="rs" property="zymc" style="width:210px"  readonly="true"/>
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							���֤���룺
						</td>
						<td align="left">
							<html:text name="rs" property="sfzhm" style="width:210px" />
						</td>
						<td align="right">
							�Ա���룺
						</td>
						<td align="left">
							<html:text name="rs" property="xbdm" style="width:210px" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							ѧ�����룺
						</td>
						<td align="left">
							<html:text name="rs" property="xldm" style="width:210px" />
						</td>
						<td align="right">
							ѧλ���룺
						</td>
						<td align="left">
							<html:text name="rs" property="xwdm" style="width:210px" />
						</td>
					</tr>

					<tr style="height:22px">
						<td align="right">
							
							��Դ���ڵش��룺
						</td>
						<td align="left">
							<html:text name="rs" property="syszddm" style="width:210px" />
						</td>
						<td align="right">
							��Դ���ڵأ�
						</td>
						<td align="left">
							<html:text name="rs" property="syszd" style="width:210px"/>
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							������룺
						</td>
						<td align="left">
								<html:text name="rs" property="mzdm" style="width:210px" />
						</td>
						<td align="right">
							������ò���룺
						</td>
						<td align="left">
								<html:text name="rs" property="zzmmdm" style="width:210px" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							�Ƿ���ְ��
						</td>
						<td align="left">
								<html:text name="rs" property="sfzz" 
									style="width:210px" />
						</td>


						<td align="right">
							�Ƿ�ʦ����
						</td>
						<td align="left">
								<html:text name="rs" property="sfsf" style="width:210px"  />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							�Ƿ������
						</td>
						<td align="left">
							<html:text name="rs" property="sfdl" style="width:210px"/>
						</td>
						<td align="right">
							ѧ�ƣ�
						</td>
						<td align="left">
								<html:text name="rs" property="xz" style="width:210px"/>

						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							�������
						</td>
						<td align="left">
								<html:text name="rs" property="zslb" style="width:210px"/>
						</td>
						<td align="right">
							������ʽ���룺
						</td>
						<td align="left">
							<html:text name="rs" property="pyfsdm" style="width:210px"/>
						</td>

					</tr>
					<tr style="height:22px">
						<td align="right">
							�����ί�൥λ��
						</td>
						<td align="left">
								<html:text name="rs" property="dxhwpdw" style="width:210px"/>
						</td>
						<td align="right">
							��ѧʱ�䣺
						</td>
						<td align="left">
							<html:text name="rs" property="rxsj" style="width:210px" onclick="return showCalendar('rxsj','y-mm-dd');"/>

						</td>					
					</tr>
					<tr style="height:22px">
						<td align="right">
							��ҵʱ�䣺
						</td>
						<td align="left">
							<html:text name="rs" property="bysj" style="width:210px" onclick="return showCalendar('bysj','y-mm-dd');"/>

						</td>
						<td align="right">
							��ҵ֤���ţ�
						</td>
						<td align="left">
							<html:text name="rs" property="byzsbh" style="width:210px"/>

						</td>
						
					</tr>
					<tr style="height:22px">
						<td align="right">
							�������ִ��룺
						</td>
						<td align="left">
							<html:text name="rs" property="zxwyyzdm" style="width:210px"/>

						</td>
						<td align="right">
							����ȼ���
						</td>
						<td align="left">
							<html:text name="rs" property="wydj" style="width:210px"/>

						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							������ȼ���
						</td>
						<td align="left">
							<html:text name="rs" property="jsjdj" style="width:210px"/>
						</td>
						<td align="right">
							�ֻ����룺
						</td>
						<td align="left">
							<html:text name="rs" property="sjhm" style="width:210px"/>

						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							QQ���룺
						</td>
						<td align="left">
								<html:text name="rs" property="qqhm" style="width:210px" />
						</td>
						<td align="right">
							��ַ��Ϣ��
						</td>
						<td align="left">
								<html:text name="rs" property="dzxx" style="width:210px" />
						</td>
					</tr>
					<tr style="height:22px">
						
						<td align="right">
							��ϵ�绰��
						</td>
						<td align="left">
							<html:text name="rs" property="lxdh" style="width:210px"/>

						</td>
						<td align="right">
							��ϵ��ַ��
						</td>
						<td align="left">
							<html:text name="rs" property="lxdz" style="width:210px"/>

						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							���ܣ�
						</td>
						<td align="left">
							<html:text name="rs" property="jljn" style="width:210px"/>

						</td>
						<td align="right">
							<font color="red">*</font>ǩԼʱ�䣺
						</td>
						<td align="left">
							<html:text name="rs" property="qysj" style="width:210px" onclick="return showCalendar('qysj','y-mm-dd');"/>
						</td>
					</tr>
					<logic:equal value="10338" name="xxdm" scope="session">
						<tr style="height:22px">
						<td align="right">
							ְҵ����
						</td>
						<td align="left" colspan="3">
							<html:textarea name="rs" property="zyyx" style="width:630px" rows="5"></html:textarea>
						</td>
					</tr>
					</logic:equal>
					<tr style="height:22px">
						<td align="right">
						<logic:equal value="10338" name="xxdm" scope="session">
							��ҵ������1
						</logic:equal>
						<logic:notEqual value="10338" name="xxdm" scope="session">
							��ע1��
						</logic:notEqual>
						</td>
						<td align="left" colspan="3">
						<logic:equal value="10338" name="xxdm" scope="session">
							<html:text name="rs" property="sybz1" style="width:630px" maxlength="120"></html:text>
						</logic:equal>
						<logic:notEqual value="10338" name="xxdm" scope="session">
							<html:textarea property="sybz1" style="width:630px" rows="5"></html:textarea>
						</logic:notEqual>
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							<logic:equal value="10338" name="xxdm" scope="session">
							��ҵ������2
						</logic:equal>
						<logic:notEqual value="10338" name="xxdm" scope="session">
							��ע2��
						</logic:notEqual>
						</td>
						<td align="left" colspan="3">
						<logic:equal value="10338" name="xxdm" scope="session">
							<html:text name="rs" property="sybz2" style="width:630px" maxlength="120"></html:text>
						</logic:equal>
						<logic:notEqual value="10338" name="xxdm" scope="session">
							<html:textarea property="sybz2" style="width:630px" rows="5"></html:textarea>
						</logic:notEqual>
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							<logic:equal value="10338" name="xxdm" scope="session">
							��ҵ������3
						</logic:equal>
						<logic:notEqual value="10338" name="xxdm" scope="session">
							��ע3��
						</logic:notEqual>
						</td>
						<td align="left" colspan="3">
						<logic:equal value="10338" name="xxdm" scope="session">
							<html:text name="rs" property="sybz3" style="width:630px" maxlength="120"></html:text>
						</logic:equal>
						<logic:notEqual value="10338" name="xxdm" scope="session">
							<html:textarea property="sybz3" style="width:630px" rows="5" cols=""></html:textarea>
						</logic:notEqual>
						</td>
					</tr>
				</table>
				<div class="buttontool" align="center">
					<button class="button2" onclick="savejyxy()">
						�� ��
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="button2" onclick="" type="reset">
						ȡ �� �� ��
					</button>
					
				</div>
			</logic:notEmpty>
			<logic:notEmpty name="saveupdate">
				<logic:equal name="saveupdate" value="ok">
					<script>
					    alert("�ύ�ɹ���");
					    </script>
				</logic:equal>
				<logic:equal name="saveupdate" value="no">
					<script>
    				alert("�ύʧ�ܣ������Ƿ��ظ��ύ��");
   				 </script>
				</logic:equal>
			</logic:notEmpty>
			<logic:notEmpty name="iszc">
				<logic:equal name="iszc" value="iszc">
					<script>
					    alert("���û��Ѿ������ˣ���Ҫ�ظ��ύͬһ��ѧ��");
					    </script>
				</logic:equal>
			</logic:notEmpty>
		</html:form>
	</body>
</html>
