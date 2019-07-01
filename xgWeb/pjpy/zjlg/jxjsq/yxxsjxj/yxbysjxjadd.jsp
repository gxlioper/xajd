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
<html:html>
<base target="_self" />
<head>
	<title><bean:message key="lable.title" />
	</title>
	<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
	<meta name="Copyright" content="������� zfsoft" />

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<%
			response.setHeader("Pragma", "no-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<link id="csss" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript" src="js/commanFunction.js"></script>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/xszzFunction.js"></script>
	<script language="javascript" src="js/calendar.js"></script>
	<script language="javascript" src="js/calendar-zh.js"></script>
	<script language="javascript" src="js/calendar-setup.js"></script>
	<script language="javascript" src="js/String.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
	<script language="javascript">
	 function isNumber(s){
			var p = /^(-|\+)?\d+$/;
			return p.test(s); 
		    } 
		function jxjSqSavett(){
			var xmdm = document.getElementById('jxjdm').value;
			var xh = document.getElementById('xh').value;
			var xxjl = document.getElementById('xxjl').value;
			var zysj = document.getElementById('zysj').value;
			var jfqk = document.getElementById('jfqk').value;
			var xyshyj = document.getElementById('xyshyj').value;
			var xxshyj = document.getElementById('xxshyj').value;
			var bz = document.getElementById('bz').value;
			var jytyj = document.getElementById('jytyj').value;
			var byqx = document.getElementById('byqx').value;
			var yhkh = document.getElementById('yhkh').value;

			if(!isNumber(yhkh)){
				alert("���п���ֻ��������!");
				return false;
			}
			if(xmdm == null || xmdm == ""){
				alert("��ѡ��Ҫ����Ľ���ѧ��!");
				return false;
			}
			if(xh == null || xh == ""){
				alert("ѧ�Ų���Ϊ��!");
				return false;
			}
			if(jfqk != null){
	         	if(jfqk.length > 400){	         
	          		 alert("��������ܴ���200���ַ���");
	          		 return false;
	       		 }
	       	}
			if(xxjl != null){
	         	if(xxjl.length > 800){	         
	          		 alert("�������벻�ܴ���400���ַ���");
	          		 return false;
	       		 }
	       	}
	       	if(xyshyj != null){
	         	if(xyshyj.length > 200){	         
	          		 alert("<bean:message key="lable.xsgzyxpzxy" />������ܴ���100���ַ���");
	          		 return false;
	       		 }
	       	}
	    	if(zysj != null){
	         	if(zysj.length > 200){	         
	          		 alert("��Ҫ�¼����ܴ���100���ַ���");
	          		 return false;
	       		 }
	       	}
			if(xxshyj != null){
	         	if(xxshyj.length > 200){	         
	          		 alert("ѧУ������ܴ���100���ַ���");
	          		 return false;
	       		 }
	       	}
			if(bz != null){
	         	if(bz.length > 200){	         
	          		 alert("��ע���ܴ���100���ַ���");
	          		 return false;
	       		 }
	       	}
			if(jytyj != null){
	         	if(jytyj.length > 200){	         
	          		 alert("������������ܴ���100���ַ���");
	          		 return false;
	       		 }
	       	}
			if(byqx != null){
	         	if(byqx.length > 200){	         
	          		 alert("��ҵȥ���ܴ���200���ַ���");
	          		 return false;
	       		 }
	       	}
			document.forms[0].action = "/xgxt/zjlgPjpy.do?method=jxjAdd&doType=save&jxjtype=yxbysjxj";
			document.forms[0].submit();
		}
		
		function chang(){
			
			alert('tt');
			return false;
			document.forms[0].action = "/xgxt/zgdzdx_xszz.do?method=jzxj_xmsq";
			document.forms[0].submit();
		}
		
		function toPrintOut(){
			var xmdm = document.getElementById('xmdm').value;
			if(xmdm == null || xmdm == ""){
				alert("��ѡ��Ҫ���صĽ���ѧ��!");
				return false;
			}
			document.forms[0].action = "/xgxt/zgdzdx_xszz.do?method=jzxj_xmmbxz&xmdm="+xmdm;
			document.forms[0].target = "_blank";
			document.forms[0].submit();
			document.forms[0].target = "_self";
		}
		function initjxjList(){
			var jxjdm = document.getElementById("jxjdm").value;

			GetListData.getJxjdm(jxjdm,function initTjList(data){
					if (data != null) {
						if(document.getElementById("jxjlb").value!=data  || document.getElementById("jxjlb").value=="ר�ѧ��"){
							document.getElementById("jxjlb").value=data;
							if(data=="ͻ�����׽�ѧ��"){
								document.forms[0].action = "/xgxt/zjlgPjpy.do?method=jxjAdd&jxjtype=tcgxjxj";
								document.forms[0].submit();
							}else if(data=="����ѧ����ѧ��"){
								document.forms[0].action = "/xgxt/zjlgPjpy.do?method=jxjAdd&jxjtype=yxxsjxj";
								document.forms[0].submit();
							}else if(data=="��Ṥ����ѧ��"){
								document.forms[0].action = "/xgxt/zjlgPjpy.do?method=jxjAdd&jxjtype=shgzjxj";
								document.forms[0].submit();
							}else if(data=="���д��½�ѧ��"){
								document.forms[0].action = "/xgxt/zjlgPjpy.do?method=jxjAdd&jxjtype=kycxjxj";
								document.forms[0].submit();
							}else if(data=="���ѧ��"){
								document.forms[0].action = "/xgxt/zjlgPjpy.do?method=jxjAdd&jxjtype=dxjxj";
								document.forms[0].submit();
							}else if(data=="�����ҵ����ѧ��"){
								document.forms[0].action = "/xgxt/zjlgPjpy.do?method=jxjAdd&jxjtype=yxbysjxj";
								document.forms[0].submit();
							}else if(data=="ר�ѧ��"){
								var jxjlb;
								if(jxjdm=="00071"){
									jxjlb="fzzgjxj";
								}else if(jxjdm=="00072"){
									jxjlb="gjjxj";
								}else if(jxjdm=="00073"){
									jxjlb="hdjxj";
								}else if(jxjdm=="00074"){
									jxjlb="smjxj";
								}else{
										alert("ѡ�����������ѡ��");
								}
								document.forms[0].action = "/xgxt/zjlgPjpy.do?method=jxjAdd&jxjtype="+jxjlb;
								document.forms[0].submit();
							}
						}else{
						}
					}else{
						showMsgWin("�д�����֣�Զ�����ݶ�ȡʧ�ܣ�");
					}
				});
		}
		function heckistype(){
			var userlx = document.getElementById("userlx").value;
			if(userlx=="stu"){
				document.getElementById("xylxyj1").style.display = "none";
				document.getElementById("xxlxyj1").style.display = "none";
				document.getElementById("xxlxyj2").style.display = "none";
				document.getElementById("xxlxyj3").style.display = "none";
			}else if(userlx=="xy"){
				document.getElementById("xxlxyj1").style.display = "none";
				document.getElementById("xxlxyj2").style.display = "none";
				document.getElementById("xxlxyj3").style.display = "none";
			}else if(userlx=="xx"){
				document.getElementById("xylxyj1").style.display = "none";
			}
		}
		function pjpyjxjprint(){
			var jxjdm = document.getElementById("jxjdm").value;
			if(jxjdm == ""){
				alert("��ѡ��ѧ������");
				return false;
			}
			var xh = document.getElementById("xh").value;
			window.open('/xgxt/zjlgPjpy.do?method=jxjReport&pkValue='+xh+'&jxjcxzj='+jxjdm)
		}
	</script>
</head>

<body onload="heckistype();">
	<div class="title">
		<div class="title_img" id="title_m">
			��ǰ����λ�ã��������� - ��ѧ������
		</div>
	</div>
	<logic:equal name="sfksq" value="-1">
		<center>
			<p>
				���ڲ�������ʱ���ڻ򲻷�����������������
			</p>
		</center>
	</logic:equal>
		<html:form action="/zjlgPjpy" method="post">
			
			<input type="hidden" id="url" name="url"
				value="/zjlgPjpy.do?method=yxxsjxjsq" />
			<input type="hidden" id="getStuInfo" name="getStuInfo"
				value="xm-xb-xymc-bjmc" />
			<input type="hidden" id="xn" name="xn"
				value="<bean:write name="rs" property="xn" />">
			<input type="hidden" id="xq" name="xq"
				value="<bean:write name="rs" property="xq" />">
			<input type="hidden" id="jxjmc" name="jxjmc"
				value="yxbysjxj">
				
				<input id="userlx" type="hidden" value="<bean:write name="userType" scope="session" />"/>
				
			<logic:present name="ok">
				<logic:match value="ok" name="ok">
					<script language="javascript">
	         	alert("����ɹ���");
	         	</script>
				</logic:match>
				<logic:match value="no" name="ok">
					<script language="javascript">
	         	alert("����ʧ�ܣ�");
	         	</script>
				</logic:match>
			</logic:present>
			<logic:present name="have">
				<logic:match value="have" name="have">
					<script language="javascript">
	         			alert("��ͨ����ˣ��������룡");
	         		</script>
				</logic:match>
			</logic:present>

			<table class="tbstyle" width="90%" >
				<tr>
					<td align="center" colspan="1" style="width: 120px">
						<font color="red">*</font>��ѧ������
					</td>
					<td colspan="2">
						<html:select property="jxjdm" style="width:180px"
							onchange="initjxjList();">
							<html:option value=""></html:option>
							<html:options collection="jzxjxmList" property="jxjdm"
								labelProperty="jxjmc" />
						</html:select>
					</td>
					<td colspan="1" scope="row">
						<div align="center">
							��ѧ�����
						</div>
					</td>
					<td colspan="4">
						<html:text property="jxjlb" readonly="true"></html:text>
					</td>
				</tr>
				<tr>
					<logic:equal name="userOnLine" value="teacher" scope="session">
						<td align="center" colspan="1">
							<font color="red">*</font>ѧ��
						</td>
						<td align="left" colspan="2">
							<html:text name='rs' property="xh" styleId="xh"
								onkeypress="autoFillStuInfo(event.keyCode,this)" readonly="true" />
							<button type="button" onclick="showTopWin('/xgxt/stu_info.do',750,550);"
								class="btn_01" id="buttonFindStu">
								ѡ��
							</button>
						</td>
					</logic:equal>
					<logic:equal name="userOnLine" value="student" scope="session">
						<td align="center" colspan="1">
							<font color="red">*</font>ѧ��
						</td>
						<td align="left" colspan="2">
							<input type="text" id="xh" name="xh"
								value="<bean:write name='rs' property="xh" />" readonly="true">
						</td>
					</logic:equal>
					<td width="11%" colspan="1">
						<div align="center">
							����
						</div>
					</td>
					<td colspan="4" scope="col">
						<html:text name="rs" property="xm" readonly="true"></html:text>
					</td>
				</tr>
				<tr>
					<td colspan="1" scope="row">
						<div align="center">
							<font color="red">*</font>���п���
						</div>
					</td>
					<td colspan="2">
						<html:text property="yhkh" ></html:text>
					</td>
					<td colspan="1">
						<div align="center">
							��������
						</div>
					</td>
					<td colspan="4">
						<html:text property="yhlx" ></html:text>
					</td>
				</tr>
				<tr>
					<td colspan="1" scope="row">
						<div align="center">
							ѧ��
						</div>
					</td>
					<td colspan="2">
						<html:text name="rs" property="xn" readonly="true"></html:text>
					</td>
					<td colspan="1">
						<div align="center">
							ѧ��
						</div>
					</td>
					<td colspan="4">
						<html:text name="rs" property="xq" readonly="true"></html:text>
					</td>
				</tr>
				<tr>
					<td colspan="1" scope="row">
						<div align="center">
							�Ա�
						</div>
					</td>
					<td colspan="2">
						<html:text name="rs" property="xb" readonly="true"></html:text>
					</td>
					<td colspan="1">
						<div align="center">
							��������
						</div>
					</td>
					<td colspan="4">
						<html:text name="rs" property="csrq" readonly="true"></html:text>
					</td>
				</tr>
				<tr>
					<td colspan="1" scope="row">
						<div align="center">
							<bean:message key="lable.xsgzyxpzxy" />
						</div>
					</td>
					<td colspan="2">
						<html:text name="rs" property="xymc" readonly="true"></html:text>
					</td>
					<td colspan="1">
						<div align="center">
							�༶
						</div>
					</td>
					<td colspan="4">
						<html:text name="rs" property="bjmc" readonly="true"></html:text>
					</td>
				</tr>
				<tr>
					<td colspan="1" scope="row">
						<div align="center">
							������ò
						</div>
					</td>
					<td colspan="2">
						<html:text name="rs" property="zzmm" readonly="true"></html:text>
					</td>
					<td>
						<div align="center">
							ְ��
						</div>
					</td>
					<td colspan="4">
						<html:text name="rs" property="zw" readonly="true"></html:text>
					</td>
				</tr>
				<tr>
					<td colspan="1" scope="row">
						<div align="center">
							�� ͥ �� ַ 
						</div>
					</td>
					<td colspan="2">
						<html:text name="rs" property="lxdz" readonly="true"></html:text>
					</td>
					<td>
						<div align="center">
							��ϵ�绰 
						</div>
					</td>
					<td colspan="4">
						<html:text name="rs" property="lxdh" readonly="true"></html:text>
					</td>
				</tr>
				<tr>
					<td colspan="1" scope="row">
						<div align="center">
							���� 
						</div>
					</td>
					<td colspan="2">
						<html:text name="rs" property="mz" readonly="true"></html:text>
					</td>
					<td>
						<div align="center">
							
						</div>
					</td>
					<td colspan="4">
					</td>
				</tr>
				<tr>
					<td colspan="1" scope="row">
						<div align="center">
							��Դ��
						</div>
					</td>
					<td colspan="2">
						<html:text name="rs" property="jg" readonly="true"></html:text>
					</td>
					<td>
						<div align="center">
							
						</div>
					</td>
					<td colspan="4">
<!--						<html:text name="rs" property="jgshi" readonly="true"></html:text>-->
					</td>
				</tr>
				<tr>
					<td colspan="1" rowspan="1">
						<div align="center">
							��<br>��<br>��<br>��
						</div>
					</td>
					<td colspan="8">
						<html:textarea property="xxjl" rows='6' style="width:100%"/>
					</td>
				</tr>
				<tr>
					<td colspan="1" rowspan="1">
						<div align="center">
							<br>��<br>Ҫ<br>��<br>��<br>
						</div>
					</td>
					<td colspan="8">
						<html:textarea property="zysj" rows='6' style="width:100%" />
					</td>
				</tr>
				<tr>
					<td colspan="1" rowspan="1">
						<div align="center">
							<br>��<br>��<br>��<br>��<br>
						</div>
					</td>
					<td colspan="8">
						<html:textarea property="jfqk" rows='6' style="width:100%" />
					</td>
				</tr>
				<tr id="xylxyj1">
					<td colspan="1" rowspan="1">
						<div align="center">
							<br>ѧ<br>Ժ<br>��<br>��<br>
						</div>
					</td>
					<td colspan="8">
						<html:textarea property="xyshyj" rows='6' style="width:100%" />
					</td>
				</tr>
				<tr id="xxlxyj1">
					<td colspan="1" rowspan="1">
						<div align="center">
							<br>ѧ<br>У<br>��<br>��<br>
						</div>
					</td>
					<td colspan="8">
						<html:textarea property="xxshyj" rows='6' style="width:100%" />
					</td>
				</tr>
				<tr id="xxlxyj2">
					<td colspan="1" rowspan="1">
						<div align="center">
							<br>��<br>��<br>��<br>��<br>��<br>
						</div>
					</td>
					<td colspan="8">
						<html:textarea property="jytyj" rows='6' style="width:100%" />
					</td>
				</tr>
				<tr>
					<td colspan="1" rowspan="1">
						<div align="center">
							<br>��<br>ҵ<br>ȥ<br>��<br>
						</div>
					</td>
					<td colspan="8">
						<html:textarea property="byqx" rows='6' style="width:100%" />
					</td>
				</tr>
				<tr id="xxlxyj3">
					<td colspan="1" rowspan="1">
						<div align="center">
							<br>��<br>ע<br>
						</div>
					</td>
					<td colspan="8">
					<html:textarea property="bz" rows='6' style="width:100%"  />
					</td>
				</tr>
			</table>
	<div class="buttontool" align="center">
				<button type="button" class="button2" id="buttonSave" onclick="jxjSqSavett();">
					�� �� �� ��
				</button>
				&nbsp;&nbsp;
				<button type="button" class="button2" onclick="pjpyjxjprint()">
						�� ӡ �� ��
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2" id="btn_cjprint" onclick="showOpenWindow('pjpy_shcbys_cjprint.do?xh='+document.getElementById('xh').value,700,600)">
						ѧ���ɼ���
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2" id="btn_cfqk" onclick="showOpenWindow('zjlgPjpy.do?method=jxjcfqk&xh='+document.getElementById('xh').value+'&xn='+document.getElementById('xn').value+'&xq='+document.getElementById('xq').value,700,600)">
						�������
					</button>
			</div>
			<logic:equal name="done" value="true">
				<script>
			          alert("����ɹ���");
			        </script>
			</logic:equal>
			<logic:equal name="done" value="false">
				<script>
			          alert("����ʧ�ܣ�");
			        </script>
			</logic:equal>
			<logic:equal name="isExist" value="no">
				<script>
			        alert("�ý�ѧ��������,����ͨ����ز������\n�����������,�����ٴ����룡");			    
			        </script>
			</logic:equal>
			<logic:equal name="zycjbf" value="zycjbf1">
			<script>
			        alert("�����ɼ���������������ý�ѧ���������");			    
			        </script>
			</logic:equal>
			<logic:equal name="zycjbf" value="zycjbf2">
			<script>
			        alert("�����ֲ���������ý�ѧ���������");			    
			        </script>
			</logic:equal>
			<logic:equal name="zycjbf" value="zycjbf3">
			<script>
			        alert("�����ֲ���������ý�ѧ���������");			    
			        </script>
			</logic:equal>
			<logic:equal name="zycjbf" value="zycjbf4">
			<script>
			        alert("��������������������ý�ѧ���������");			    
			        </script>
			</logic:equal>
			<logic:equal name="zycjbf" value="zycjbf5">
			<script>
			        alert("�����ɼ���������������ý�ѧ���������");			    
			        </script>
			</logic:equal>
			<logic:equal name="zycjbf" value="zycjbf6">
			<script>
			        alert("����ɼ�����������ý�ѧ���������");			    
			        </script>
			</logic:equal>
			<logic:equal name="zycjbf" value="zycjbf7">
			<script>
			        alert("�ۺ����ʷ���������������ý�ѧ���������");			    
			        </script>
			</logic:equal>
			<logic:equal name="zycjbf" value="zycjbf8">
			<script>
			        alert("�ۺ����ʷֲ���������ý�ѧ���������");			    
			        </script>
			</logic:equal>
			<logic:equal name="zycjbf" value="zycjbf9">
			<script>
			        alert("�㲻�Ǳ�ҵ������������ý�ѧ���������");			    
			        </script>
			</logic:equal>
			<logic:equal name="zycjbf" value="zycjbf9">
			<script>
			        alert("��ѧ���������ۺ����ʲ����֣�");			    
			        </script>
			</logic:equal>
			<logic:equal name="jxjcftj" value="yes">
			<script>
			        alert("�ý�ѧ��������");			    
			        </script>
			</logic:equal>
			<logic:equal name="zycjbf" value="jxjjd1">
			<script>
			        alert("��־���ѧ�������ѧ����ѧ���ܼ��");			    
			        </script>
			</logic:equal>
			<logic:equal name="isczcpf" value="isczcpf">
			<script>
			        //alert("�Ҳ�����ѧ�����ۺ����ʲ����ɼ�����������");			    
			        </script>
			</logic:equal>
			<logic:equal name="zycjbf" value="jxjjd2">
			<script>
			        alert("����ѧ����ѧ���ܼ��");			    
			        </script>
			</logic:equal>
			<logic:equal name="cpzisnull" value="cpzisnull">
			<script>
			        alert("��ѧ�����ڲ�����Ϊ�գ���������");			    
			        </script>
			</logic:equal>
		</html:form>
</body>
</html:html>
