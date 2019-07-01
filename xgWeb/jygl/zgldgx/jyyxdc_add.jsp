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
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css" type="text/css" media="all" />
	<base target="_self">	
		
	<script language="javascript" src="js/function.js"></script>
	<script type="text/javascript" src="js/BatAlert.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
	<script type="text/javascript" src="js/BatAlert.js"></script>
	<script language="javascript">
	function add(){
		  var xymc = document.getElementById("xydm").value;   
	      var xh = document.getElementById("xh").value; 
	      var zymc = document.getElementById("zydm").value; 
	      var xm = document.getElementById("xm").value;
	      if(xymc=="--��ѡ��--"){
	      alert("<bean:message key="lable.xsgzyxpzxy" />���Ʋ���Ϊ�գ�");
	      return false;
	      }
	      if(xh==""){
	      alert("ѧ�Ų���Ϊ�գ�");
	      return false;
	      }
	      if(zymc==""){
	      alert("רҵ���Ʋ���Ϊ�գ�");
	      return false;
	      }
	      if(xm==""){
	      alert("��������Ϊ�գ�");
	      return false;
	      }
	      
	         BatAlert.showTips('�����ύ�����Ժ�...');
			 document.forms[0].action = "jyyxdcb.do?act=add&doType=add";
			 document.forms[0].submit();
    }
	function initZyList(){
		var xydm = "";
		var query = "";
		var userName = "";
		var isFdy = "false";
		if($("isFdy")){isFdy = $("isFdy").value};
		if($("xy")){xydm = $("xy").value};	
		if($("userName")){userName = $("userName").value};
			if(xydm == null || xydm == ""){
				xydm = "%";
			} else{
				xydm = "%" + xydm +"%";
			}
			query = xydm;	
			if($("isBzr")){isBzr = $("isBzr").value};
			GetListData.getZyList(query,userName,isFdy,isBzr,function initTjList(data){
				if (data != null && typeof data == 'object') {
					var objId = data[0].dm;
					if($(objId) && $(objId).tagName.toLowerCase() == "select"){
						DWRUtil.removeAllOptions(objId);			
						DWRUtil.addOptions(objId,data,"dm","mc");			
						$(objId).options[0].value = "";
						if(objId){
						if($(objId).value != "" && $(objId).value != null){
							alert($(objId).tagName);
							for(var i = 0;i < $(objId).options.length; i++){
								if($(objId).options[i].value == $(objId).value){
									$(objId).options[i].selected = true;
									return true;
								}
							}
						}
						}
					}
				}else{
					showMsgWin("�д�����֣�Զ�����ݶ�ȡʧ�ܣ�");
				}
			});
	}
	
	</script>

	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<body>
		<html:form action="/yxjzyjs.do" method="post">
			<table width="100%" class="tbstyle">
				
			<input id="isstu" name="isstu" value="<bean:write name="userType" scope="session"/>" type="hidden"/>
				<thead>
					<tr>
						<td align="center" colspan="4" height="">
							<b>��ҵ��������</b>
						</td>
					</tr>
				</thead>
				<tr>
					<td>
						<font color="red">*</font>ѧ�ţ�
					</td>
					<td >
						<html:text name="rs1" property="xh" readonly="true"/>
					</td>
					<td>
						<font color="red">*</font>������
					</td>
					<td >
						<html:text name="rs1" property="xm" readonly="true"/>
					</td>
				</tr>
				
				<logic:equal value="stu" name="userType" scope="session">
					<tr>
					<td>
						<font color="red">*</font>ϵ(Ժ)���ƣ�
					</td>
					<td>
						<html:text name="rs1" property="xymc" readonly="true"/>
						<html:select name="rs1" property="xydm" onchange="initZyList();" style="width:200px;display: none" styleId="xy" >
									<html:option value="--��ѡ��--"></html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
						</html:select>
					</td>
					<td>
						<font color="red">*</font>רҵ���ƣ�
					</td>
					<td>
							<html:text name="rs1" property="zymc" readonly="true"/>
						<html:select name="rs1" property="zydm" onchange="" style="width:150px;display: none" styleId="zy">
									<html:option value="--��ѡ��--"></html:option>
									<html:options collection="zyList" property="zydm"
										labelProperty="zymc" />
						</html:select>
					</td>
					</tr>
				</logic:equal>
				
				<logic:notEqual value="stu" name="userType" scope="session">
				<tr>
					<td>
						<font color="red">*</font>ϵ(Ժ)���ƣ�
					</td>
					<td>
						<html:select name="rs1" property="xydm" onchange="initZyList();" style="width:200px" styleId="xy" >
									<html:option value="--��ѡ��--"></html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
						</html:select>
					</td>
					<td>
						<font color="red">*</font>רҵ���ƣ�
					</td>
					<td>
						<html:select name="rs1" property="zydm" onchange="" style="width:150px" styleId="zy">
									<html:option value="--��ѡ��--"></html:option>
									<html:options collection="zyList" property="zydm"
										labelProperty="zymc" />
						</html:select>
					</td>
				</tr>
				</logic:notEqual>
				<tr>
					<td>
						�Ա�
					</td>
					<td>
					<html:select name="rs1" property="xb1" >
									<html:option value="��">��</html:option>
									<html:option value="Ů">Ů</html:option>
						</html:select>
					</td>
					<td>
						���У�
					</td>
					<td>
						<html:select name="rs1" property="ky" >
									<html:option value="��">��</html:option>
									<html:option value="��">��</html:option>
						</html:select>
					</td>
				</tr>
				<tr>
					<td>
						��������Ա��
					</td>
					<td>
					<html:select name="rs1" property="bkgwy" >
									<html:option value="������">������</html:option>
									<html:option value="���Ҽ�">���Ҽ�</html:option>
									<html:option value="������">������</html:option>
									<html:option value="�ط�ʡ��">�ط�ʡ��</html:option>
					</html:select>
					</td>
					<td>
						��֧һ����
					</td>
					<td>
						<html:select name="rs1" property="szyf" >
									<html:option value="��">��</html:option>
									<html:option value="��">��</html:option>
						</html:select>
					</td>
				</tr>
				<tr>
					<td>
						�����ƻ���
					</td>
					<td>
						<html:select name="rs1" property="xbjh" >
									<html:option value="��">��</html:option>
									<html:option value="��">��</html:option>
						</html:select>
					</td>
					<td>
						�ҹ�����
					</td>
					<td>
					<html:select name="rs1" property="zgz" >
									<html:option value="����">����</html:option>
									<html:option value="���">���</html:option>
									<html:option value="����">����</html:option>
					</html:select>
					</td>
				</tr>
				<tr>
					<td>
						������ҵ��
					</td>
					<td>
						<html:select name="rs1" property="zzcy" >
									<html:option value="��">��</html:option>
									<html:option value="��">��</html:option>
						</html:select>
					</td>
					<td>
						������רҵ�ʸ�֤�飺
					</td>
					<td>
					<html:select name="rs1" property="zgzs" >
									<html:option value="��">��</html:option>
									<html:option value="��">��</html:option>
						</html:select>
					</td>
				</tr>
			</table>
			<div align="center">
				<button class="button2" onclick="add();" style="width:80px">
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
                    </script>
			</logic:equal>
			<logic:equal name="save" value="no">
				<script>
                      alert("�ظ��ύ������ʧ��!");
                    </script>
			</logic:equal>
		</logic:notEmpty>
		<logic:notEmpty name="yjsave">
			<logic:equal name="yjsave" value="yjsave">
				    <script>
                      alert("���Ѿ��ύ�˾�ҵ������飡");
                    </script>
			</logic:equal>
		</logic:notEmpty>
	</body>
</html>

