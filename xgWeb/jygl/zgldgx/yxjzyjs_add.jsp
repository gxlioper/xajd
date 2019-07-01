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
	      var xyjbqk = document.getElementById("xyjbqk").value; 
	      var zymc = document.getElementById("zydm").value; 
	      var pycc = document.getElementById("pycc").value;
	      var xz = document.getElementById("xz").value; 
	      var bmrs = document.getElementById("rs1").value;
	      if(xymc==""){
	      alert("<bean:message key="lable.xsgzyxpzxy" />���Ʋ���Ϊ�գ�");
	      document.getElementById("xydm").focus();
	      return false;
	      }
	      if(xyjbqk==""){
	      alert("<bean:message key="lable.xsgzyxpzxy" />�����������Ϊ�գ�");
	      document.getElementById("xyjbqk").focus();
	      return false;
	      }
	      if(zymc==""){
	      alert("רҵ���Ʋ���Ϊ�գ�");
	      document.getElementById("zydm").focus();
	      return false;
	      }
	      if(pycc==""){
	      alert("������β���Ϊ�գ�");
	      document.getElementById("pycc").focus();
	      return false;
	      }
	      if(xz != ""){
	      if(!isNumber(xz)){
		      alert("ѧ��ֻ�������֣�");
		      return false;
		      }
	      }
	      if(bmrs != ""){
		  if(!isNumber(bmrs)){
		      alert("����ֻ�������֣�");
		      return false;
		      }
	      }
	        BatAlert.showTips('�����ύ�����Ժ�...');
			 document.forms[0].action = "yxjzyjs.do?act=add&doType=add";
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
	 function isNumber(s){
			var p = /^(-|\+)?\d+$/;
			return p.test(s); 
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
				<thead>
					<tr>
						<td align="center" colspan="4" height="">
							<b>ϵ��Ժ����רҵ����¼��</b>
						</td>
					</tr>
				</thead>
				<tr>
					<td align="right">
						<font color="red">*</font>ϵ(Ժ)���ƣ�
					</td>
					<td colspan="3">
					<logic:equal value="xy" name="userType" scope="session">
							<bean:message key="lable.xsgzyxpzxy" />��
								<input name="xydndt" value="<bean:write name="bmxxmc"/>" disabled="disabled"/>
								<html:select name="rs1" property="xydm" onchange="initZyList();" style="width:160px;display: none" styleId="xy">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select>
							</logic:equal>
							<logic:notEqual value="xy" name="userType" scope="session">
							<logic:equal value="stu" name="userType" scope="session">
								<input name="xydndt" value="<bean:write name="bmxxmc"/>" disabled="disabled"/>
							<bean:message key="lable.xsgzyxpzxy" />��
								<html:select name="rs1" property="xydm" onchange="initZyList();" style="width:160px;display: none" styleId="xy">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select>
							</logic:equal>
							<logic:notEqual value="stu" name="userType" scope="session">
							<bean:message key="lable.xsgzyxpzxy" />��
								<html:select name="rs1" property="xydm" onchange="initZyList();" style="width:160px" styleId="xy">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select>
							</logic:notEqual>
							</logic:notEqual>
					</td>
				</tr>
				<tr>
					<td align="right">
						<font color="red">*</font>ϵ(Ժ)���������
					</td>
					<td align="right" colspan="3">
					</td>
				</tr>
				<tr>
					<td colspan="1"></td>
					<td colspan="3">
						<html:textarea name="rs1" property="xyjbqk" style="width: 100%" rows="8"></html:textarea>
					</td>
				</tr>
				<tr>
					<td align="right">
						<font color="red">*</font>רҵ���ƣ�
					</td>
					<td>
						<html:select name="rs1" property="zydm" onchange="" style="width:220px" styleId="zy">
									<html:option value=""></html:option>
									<html:options collection="zyList" property="zydm"
										labelProperty="zymc" />
						</html:select>
					</td>
					<td align="right">
						������
					</td>
					<td>
						<html:text name="rs1" property="rs1"/>��
					</td>
				</tr>
				<tr>
					<td align="right">
						<font color="red">*</font>������Σ�
					</td>
					<td>  
						<html:text name="rs1" property="pycc" style="width: 70%"/>
					</td>
					<td align="right">
						ѧ�ƣ�
					</td>
					<td>
						<html:text name="rs1" property="xz"/>��
					</td>
				</tr>
				<tr>
					<td align="right">
						ѧλ��
					</td>
					<td colspan="3">
						<html:text name="rs1" property="xw" style="width: 80%"/>
					</td>
					
				</tr>
				<tr>
					<td align="right">
						����Ŀ�꼰��ɫ��
					</td>
					<td align="right" colspan="3">
					</td>
				</tr>
				<tr>
					<td colspan="1"></td>
					<td colspan="3">
						<html:textarea name="rs1" property="pymb" style="width: 100%" rows="8"></html:textarea>
					</td>
				</tr>
				<tr>
					<td align="right">
						��Ҫ�γ̣�
					</td>
					<td align="right" colspan="3">
					</td>
				</tr>
				<tr>
					<td colspan="1"></td>
					<td colspan="3">
						<html:textarea name="rs1" property="zykc" style="width: 100%" rows="8"></html:textarea>
					</td>
				</tr>
				<tr>
					<td align="right">
						��ҵǰ��������
					</td>
					<td align="right" colspan="3">
					</td>
				</tr>
				<tr>
					<td colspan="1"></td>
					<td colspan="3">
						<html:textarea name="rs1" property="jyqj" style="width: 100%" rows="8"></html:textarea>
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
	</body>
</html>

