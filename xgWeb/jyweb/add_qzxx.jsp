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

<html>
	<head>
		<title><%=session.getAttribute("xxmc")%>��ҵ��</title>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<meta http-equiv="Content-Language" content="zh-CN" />
		<meta content="all" name="robots" />
		<meta name="author" content="�����������ӹ������޹�˾ hzzfsoft@126.com" />
		<meta name="Copyright" content="Copyright" />
		<meta name="description" content="description" />
		<meta name="keywords" content="description" />

		<link id="csss" rel="icon" href="images/icon.ico" type="image/x-icon" />
		<link href="jyweb/style/module.css" rel="stylesheet" type="text/css">
		<link href="jyweb/style/base.css" rel="stylesheet" type="text/css">
		<link href="jyweb/style/forms.css" rel="stylesheet" type="text/css">

		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/jsFunction.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script type="text/javascript" src="js/BatAlert.js"></script>
		<script type="text/javascript">
		function addqzxx(){
          var xh = document.getElementById("xh").value;
	      var lxdh = document.getElementById("lxdh").value;
	      var email = document.getElementById("email").value;
	      var qzyx = document.getElementById("qzyx").value;
	      var grjs = document.getElementById("grjs").value;
	      
	      if(xh==""){
	      alert("ѧ�Ų���Ϊ�գ�");
	      return false;
	      }
	     
	      
	      if(lxdh.length<7&&lxdh!=""){
	      alert("�绰���볤�Ȳ���Ҫ��");
	      return false;
	      }
	      if(lxdh.length>13&&lxdh!=""){
	      alert("�绰���볤�Ȳ���Ҫ��");
	      return false;
	      }  
	      if(!isEmail(email)&&email!=""){
	      alert("�������䲻�Ϸ���");
	      return false;
	      }	
	      if(lxdh==""&&email==""){
	      alert("��������дһ����ϵ��ʽ��");
	      return false;		   
		  }
		  
		  if(qzyx==""){
		  alert("����д��ְ����");
		  return false;
		  }
		  
		  if(grjs.length>3000){
		   alert("���˼������ܴ���3000�ֽ�!");
		   return false;
		  }
		 document.forms[0].action = "addqzxx.do?method=qzxx&jytype=jyweb&doType=save";
	     document.forms[0].submit();
		}
		
		function delqzxx(){
		   if(confirm("ȷ��Ҫɾ����ְ��Ϣ��")){
		   document.forms[0].action = "addqzxx.do?method=qzxx&jytype=jyweb&act=del";
	       document.forms[0].submit();
	       }else{
	         return false;
	       }
		}
		
		
		
       function isNumber(s){
	    var p = /^(-|\+)?\d+$/;
	    return p.test(s); 
        } 
        
          //exclude left and right space; 
	    function trim(s){
 	    	return rtrim(ltrim(s)); 
	    }
	    //exclude left space; 
	    function ltrim(s){
 	    	return s.replace( /^\s*/, ""); 
	    } 
	    //exclude right space; 
	    function rtrim(s){ 
 	    	return s.replace( /\s*$/, ""); 
	    }
        
        function isEmail(s){
	      s = trim(s); 
 	      var p = /^[_\.0-9a-z-]+@([0-9a-z][0-9a-z-]+\.){1,4}[a-z]{2,3}$/i; 
 	      return p.test(s);
        }
		
		</script>

	</head>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>

	<body>
		<jsp:include flush="true" page="head.jsp"></jsp:include>
		<html:form action="/addqzxx" method="post">
			<html:hidden name="rs" property="xh" />
			<input type="hidden" name="webtype" value="qzxxfb" />		
			<div class="mainframe">
				<div class="ny_midframe">
					<div class="leftframe">
						<jsp:include flush="true" page="stucontrl.jsp"></jsp:include>
						<div class="rdxw">
							<h1></h1>
						</div>
						<div class="yqlj">
							<h1></h1>
							<span></span>
						</div>
					</div>
					<div class="ny_rightframe">
						<div class="ny_con">
								<h3>
									��ǰλ�ã�
									<a href="index.do">��ҳ</a>ѡ�� ������ְ��Ϣ
								</h3>
						<logic:equal name="youxx" value="youxx">
							<font color="red">��ʾ�����ѷ�������ְ��Ϣ�����²������Զ����ݽ����޸ģ�</font>
						</logic:equal>
						<logic:equal name="noxx" value="noxx">
							<font color="red">��ʾ���㻹δ������ְ��Ϣ���뽫�������ݲ����������ύ��</font>
						</logic:equal>
					<table width="94%" align="center" class="tbborder">
						<tr>
							<td width="15%">
								������
							</td>
							<td>
								<div align="left">
									<html:text name="rs" property="xm" readonly="true"/>
								</div>
							</td>
							<td>
								�Ա�
							</td>
							<td>
								<div align="left">
									<html:text name="rs" property="xb" readonly="true"/>
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<bean:message key="lable.xsgzyxpzxy" />��
							</td>
							<td>
								<div align="left">
									<html:text name="rs" property="xymc" readonly="true"/>
								</div>
							</td>
							<td>
								רҵ��
							</td>
							<td>
								<div align="left">
									<html:text name="rs" property="zymc" readonly="true"/>
								</div>
							</td>
						</tr>
						<tr>
							<td>
								��ϵ�绰��
							</td>
							<td>
								<div align="left">
									<html:text name="rs" property="lxdh" />
								</div>
							</td>
							<td>
								�������䣺
							</td>
							<td>
								<div align="left">
									<html:text name="rs" property="email" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								��ְ��ҵ��
							</td>
							<td colspan="3">
								<div align="left">
									<html:select name="rs" property="qzhy" styleId="qzhy"
										>
										<html:option value=""></html:option>
										<html:options collection="hyflList" property="hyfl"
											labelProperty="hyfl" />
									</html:select>
								</div>
							</td>
						</tr>
						<tr>
							<td>
								��ְ����
							</td>
							<td colspan="3">
								<div align="left">
									<html:text name="rs" property="qzyx" />
								</div>
							</td>
						</tr>
						<tr>
							<td>
								���˼�����<br>
								<font color="red">(������3000�ֽ�)</font>
							</td>
							<td colspan="3" >
								<html:textarea name="rs" property="grjs" rows="5" style="width:100%"/>
							</td>
						</tr>
						<tr height="25">
							<td>
								����ʱ�䣺
							</td>
							<td colspan="3">
								&nbsp;
								<div align="left">
								<bean:write name="rs" property="fbsj" />
								</div>
								&nbsp;
							</td>
						</tr>
					</table>
					<div align="center">
						<button onclick="addqzxx();">
							�ύ/�޸�
						</button>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<button type="reset">
							����
						</button>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<button onclick="delqzxx();">
							ɾ��
						</button>
					</div>
					<h2></h2>
				</div>
			</div>
					<jsp:include flush="true" page="foot.jsp"></jsp:include>
				</div>
			</div>
		</html:form>
		<logic:notEmpty name="save">
			<logic:equal name="save" value="ok">
				<script>
                      alert("��ְ��Ϣ�Ǽǳɹ���");
                    </script>
			</logic:equal>
			<logic:equal name="save" value="no">
				<script>
                      alert("��ְ��Ϣ�Ǽ�ʧ�ܣ�");
                    </script>
			</logic:equal>
		</logic:notEmpty>
		<logic:notEmpty name="update">
			<logic:equal name="update" value="ok">
				<script>
                      alert("��ְ��Ϣ�޸ĳɹ���");
                    </script>
			</logic:equal>
			<logic:equal name="update" value="no">
				<script>
                      alert("��ְ��Ϣ�޸�ʧ�ܣ�");
                    </script>
			</logic:equal>
		</logic:notEmpty>
		<logic:notEmpty name="delete">
			<logic:equal name="delete" value="ok">
				<script>
                      alert("��ְ��Ϣɾ���ɹ���");
                    </script>
			</logic:equal>
			<logic:equal name="delete" value="no">
				<script>
                      alert("��ְ��Ϣɾ��ʧ�ܣ�");
                    </script>
			</logic:equal>
		</logic:notEmpty>
	</body>
</html>
