<%@ page language="java" contentType="text/html; charset=gb2312"%>
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
		<title>��ҵ������Ϣϵͳ</title>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<meta http-equiv="Content-Language" content="GBK" />
		<meta name="Copyright" content="������� zfsoft" />
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<base target="_self">	
		
		
	<script language="javascript" src="js/function.js"></script>
	<script type="text/javascript" src="js/jsFunction.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script type="text/javascript" src="js/BatAlert.js"></script>
	<script language="javascript">
	function add(){
      var xh = document.getElementById("xh").value;   
      var dwzm = document.getElementById("dwzm").value; 
      var zydkqk = document.getElementById("zydkqk").value; 
      var gwxz = document.getElementById("gwxz").value;
      var gzqk = document.getElementById("gzqk").value;
      var sfjy = document.getElementById("sfjy").value;
      var sfqy = document.getElementById("sfqy").value;
     
      if(xh==""){
      alert("ѧ�Ų���Ϊ�գ�");
      return false;
      }
      if(dwzm==""){
      alert("����д��λ֤��ѡ�");
      return false;
      }
      if(zydkqk==""){
      alert("����дרҵ�Կ�ѡ�");
      return false;
      }
      if(gwxz==""){
      alert("����д��λ����ѡ�");
      return false;
      }
      if(gzqk==""){
      alert("����д�������ѡ�");
      return false;
      }
      if(sfjy==""){
      alert("����д�Ƿ��ҵѡ�");
      return false;
      }
      if(sfqy==""){
      alert("����д�Ƿ�ǩԼѡ�");
      return false;
      }
      
         BatAlert.showTips('�����ύ�����Ժ�...');
		 document.forms[0].action = "stuxsjyxxinput.do?act=save";
		 document.forms[0].submit();
        
    }
    
	</script>

	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<body>
		<html:form action="/stuxsjyxxinput" method="post">
			<table width="100%" class="tbstyle">
				<thead>
					<tr>
						<td align="center" colspan="4" height="">
							<b>ѧ����ҵ��Ϣ¼��</b>
						</td>
					</tr>
				</thead>
				<tr>
					<td align="center" colspan="4">
					    ѧ����ҵ�������
					</td>
				</tr>
				<tr>
					<td colspan="4">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ѧ�ţ�
						<html:text name="rs" property="xh" readonly="true"></html:text>
						<button onclick="showTopWin('stuinfoquery.do',780,500);"
							class="btn_01" id="buttonFindStu">
							ѡ��
						</button>
					</td>
				</tr>
				<tr>
					<td align="right">
						������
					</td>
					<td>
						<html:text name="rs" property="xm" readonly="true"/>
					</td>
					<td align="right">
						�Ա�
					</td>
					<td>
						<html:text name="rs" property="xb" readonly="true"/>
					</td>
				</tr>
				<tr>
					<td align="right">
						�꼶��
					</td>
					<td>
						<html:text name="rs" property="nj" readonly="true"/>
					</td>
					<td align="right">
						<bean:message key="lable.xsgzyxpzxy" />��
					</td>
					<td>
						<html:text name="rs" property="xymc" readonly="true"/>
					</td>
				</tr>
				<tr>
					<td align="right">
						רҵ��
					</td>
					<td>
						<html:text name="rs" property="zymc" readonly="true"/>
					</td>
					<td align="right">
						�༶��
					</td>
					<td>
						<html:text name="rs" property="bjmc" readonly="true"/>
					</td>
				</tr>
				<tr>
					<td align="right">
						��λ֤����
					</td>
					<td colspan="3">
						<html:select name="rs" property="dwzm">
						    <html:option value="">--��ѡ��--</html:option>
							<html:option value="��">��</html:option>
							<html:option value="��">��</html:option>
						</html:select>
					</td>
				</tr>
				<tr>
					<td align="right">
						רҵ�Կ������
					</td>
					<td>
						<html:select name="rs" property="zydkqk">
						<html:option value="">--��ѡ��--</html:option>
							<html:option value="�޵�λ">�޵�λ</html:option>
							<html:option value="��ȫ�Կ�">��ȫ�Կ�</html:option>
							<html:option value="�����Կ�">�����Կ�</html:option>
							<html:option value="���Կ�">���Կ�</html:option>
						</html:select>
					</td>
					<td align="right">
						��ҵ���ţ�
					</td>
					<td>
						<html:text name="rs" property="jybm" />
					</td>
				</tr>
				<tr>
					<td align="right">
						��λ���ʣ�
					</td>
					<td>
						<html:select name="rs" property="gwxz">
						<html:option value="">---��ѡ��---</html:option>
						   <html:option value="�޵�λ">�޵�λ</html:option>
						   <html:option value="ҵ����Ա">ҵ����Ա</html:option>
						   <html:option value="������Ա">������Ա</html:option>
						   <html:option value="������Ա">������Ա</html:option>
						   <html:option value="������Ա">������Ա</html:option>
						   <html:option value="����������">����������</html:option>
						   <html:option value="����">����</html:option>
						</html:select>
					</td>
					<td align="right">
						��λ���ƣ�
					</td>
					<td>
						<html:text name="rs" property="gwmc" />
					</td>
				</tr>
				<tr>
					<td align="right">
						���������
					</td>
					<td>
						<html:select name="rs" property="gzqk" >
						<html:option value="">----��ѡ��----</html:option>
						<html:option value="�޵�λ">�޵�λ</html:option>
						<html:option value="1000Ԫ����">1000Ԫ����</html:option>
						<html:option value="1000-1500Ԫ">1000-1500Ԫ</html:option>
						<html:option value="1500-2000Ԫ">1500-2000Ԫ</html:option>
						<html:option value="2000-4000Ԫ">2000-4000Ԫ</html:option>
						<html:option value="4000Ԫ����">4000Ԫ����</html:option>
						</html:select>
					</td>
					<td align="right">
						�����أ�
					</td>
					<td>
						<html:text name="rs" property="gzd" />
					</td>
				</tr>
				<tr>
					<td align="right">
						�Ƿ��ҵ��
					</td>
					<td>
						<html:select name="rs" property="sfjy">
							<html:option value="">--��ѡ��--</html:option>
							<html:option value="��">��</html:option>
							<html:option value="��">��</html:option>
						</html:select>
					</td>
					<td align="right">
						�Ƿ�ǩԼ��
					</td>
					<td>
						<html:select name="rs" property="sfqy">
							<html:option value="">--��ѡ��--</html:option>
							<html:option value="��">��</html:option>
							<html:option value="��">��</html:option>
						</html:select>
					</td>
				</tr>
				<tr>
					<td colspan="4" align="center">
						��ҵ��λ�������
					</td>
				</tr>
				<tr>
					<td align="right">
						��λ���ƣ�
					</td>
					<td>
						<html:text name="rs" property="dwmc" />
					</td>
					<td align="right">
						��λ���ʣ�
					</td>
					<td>
						<html:select name="rs" property="dwxz">
							<html:option value="">--��ѡ��--</html:option>
							<html:option value="��˽">��˽</html:option>
							<html:option value="������ҵ">������ҵ</html:option>
							<html:option value="������ҵ">������ҵ</html:option>
							<html:option value="������ҵ">������ҵ</html:option>
							<html:option value="�ɷ�����ҵ">�ɷ�����ҵ</html:option>
							<html:option value="������ҵ">������ҵ</html:option>
							<html:option value="������ҵ">������ҵ</html:option>
						</html:select>
					</td>
				</tr>
				<tr>
					<td align="right">
						��λ��ַ��
					</td>
					<td>
						<html:text name="rs" property="dwdz" />
					</td>
					<td align="right">
						��λ��ϵ�ˣ�
					</td>
					<td>
						<html:text name="rs" property="dwlxr" />
					</td>
				</tr>
				<tr>
					<td align="right">
						��λ�绰��
					</td>
					<td colspan="3">
						<html:text name="rs" property="dwdh" />
					</td>
				</tr>

			</table>
			<div align="center">
				<button class="button2" onclick="add()" style="width:80px">
					�� ��
				</button>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<button class="button2" type="reset" style="width:80px">
					�� ��
				</button>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<button class="button2" onclick="Close();window.dialogArguments.document.getElementById('search_go').click();" style="width:80px">
					�� ��
				</button>
			</div>
		</html:form>

		<logic:notEmpty name="save">
			<logic:equal name="save" value="ok">
				    <script>
                      alert("�ύ�ɹ���");
                      Close();
                      window.dialogArguments.document.getElementById('search_go').click();
                    </script>
			</logic:equal>
			<logic:equal name="save" value="no">
				<script>
                      alert("�ظ��ύ������ʧ��!");
                      Close();
                       window.dialogArguments.document.getElementById('search_go').click();
                    </script>
			</logic:equal>
		</logic:notEmpty>
	</body>
</html>

