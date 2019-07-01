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
		
		
		function delgrjl(){
		    if(confirm("ȷ��Ҫɾ�����˼�����")){
		       document.forms[0].action = "grjldj.do?method=grjldj&act=del&jytype=jyweb";
	           document.forms[0].submit();	
	        }else{
	         return false;
	        }
		}
		
		
		
		
		function addgrjl(){
          var xsxh = document.getElementById("xsxh").value;
	      var lxdh = document.getElementById("lxdh").value;
	      var yzbm = document.getElementById("yzbm").value;
	      var email = document.getElementById("email").value;
	      var lxdz =document.getElementById("lxdz").value;
	      
	      var hjqk = document.getElementById("hjqk").value;
	      var xxqk = document.getElementById("xxqk").value;
	      var xjysjl = document.getElementById("xjysjl").value;
	      var shsjqk = document.getElementById("shsjqk").value;
	      var gzjl = document.getElementById("gzjl").value;
	      var grtc = document.getElementById("grtc").value;
	      var zwtj = document.getElementById("zwtj").value;
	      var id = document.getElementById("id").value;
	      
	      if(hjqk.length>2000){
		   alert("��������ܴ���2000�ֽ�!");
		   return false;
		  }
	      if(xxqk.length>2000){
		   alert("ѧϰ������ܴ���2000�ֽ�!");
		   return false;
		  }
	      if(xjysjl.length>2000){
		   alert("У�����Ͻ������ܴ���2000�ֽ�!");
		   return false;
		  }
	      if(shsjqk.length>2000){
		   alert("���ʵ��������ܴ���2000�ֽ�!");
		   return false;
		  }
	      if(gzjl.length>2000){
		   alert("�����������ܴ���2000�ֽ�!");
		   return false;
		  }
	      if(grtc.length>2000){
		   alert("�����س����ܴ���2000�ֽ�!");
		   return false;
		  }
	      if(zwtj.length>2000){
		   alert("�����Ƽ����ܴ���2000�ֽ�!");
		   return false;
		  }
	      
	      
	      
	      
	      if(xsxh==""){
	      alert("ѧ�Ų���Ϊ�գ�");
	      return false;
	      }
	      if(lxdz!=""&&lxdz.length>25){
	      alert("��ϵ��ַ���ȹ�������ԣ�");
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
	      if(yzbm.length!=6&&yzbm!=""){
	      alert("�������볤��ӦΪ6λ");
	      return false;
	      }
	      if(!isNumber(yzbm)&&yzbm!=""){
	      alert("��������ӦΪ���֣�");
	      return false;
	      }
	      if(!isEmail(email)&&email!=""){
	      alert("�������䲻�Ϸ���");
	      return false;
	      }	
	      if(lxdz==""&&lxdh==""&&email==""){
	      alert("��������дһ����ϵ��ʽ��");
	      return false;		   
		  }
		  if(id!=""){
		       if(checkSfzh(id)){
		       document.forms[0].action = "grjldj.do?method=grjldj&doType=save&jytype=jyweb";
	           document.forms[0].submit();
	           }
	      }else{
	           document.forms[0].action = "grjldj.do?method=grjldj&doType=save&jytype=jyweb";
	           document.forms[0].submit();
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
		
		
		
		function checkSfzh(sfzh) {
        sfzh=sfzh.toUpperCase();
	    var OldID;
	    if(sfzh.length == 15){
		OldID = sfzh;
		return true;
	    }else if(sfzh.length == 18){
		OldID = sfzh.substring(0, 6) + sfzh.substring(8,sfzh.length-1);
	    }else{
		alert("��������ȷ�����֤���룡","",function(){
			obj.select();
			obj.focus();
		});
		return false;
	    }
	    var W = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1);
	    var A = new Array("1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2");
	    var i, j, S;
	    var NewID, ID, strNF;
	    NewID = OldID.substring(0, 6) + "19" + OldID.substring(6,OldID.length);
	    S = 0;
	    for( i = 0; i <= 16; i++){
		j = NewID.substring(i, i+1) * W[i];
		S = S + j;
	    }
	    S = S % 11;
	    if(sfzh != NewID + A[S]){
		alert("��������ȷ�����֤���룡","",function(){
			obj.select();
			obj.focus();
		});
		  return false;
	    }
	   return true;
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
		<html:form action="/grjldj" method="post">
			<html:hidden name="rs" property="pkValue" />
            <input type="hidden" name="webtype" value="grjldj" />
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
									<a href="index.do">��ҳ</a>ѡ�� ���˼����ǼǼ��޸�
								</h3>
							<h1>
								���˼����Ǽ�
							</h1>
							<logic:equal name="youjl" value="youjl">
								<font color="red">��ʾ�����ѵǼ��˸��˼��������²������ԶԼ������ݽ����޸ģ�</font>
							</logic:equal>
							<logic:equal name="nojl" value="nojl">
								<font color="red">��ʾ���㻹δ�ǼǸ��˼������뽫�������ݲ����������ύ��</font>
							</logic:equal>
							<table width="95%" align="center" class="tbborder">
								<thead>
									<tr>
										<td colspan="9" align="left">
											<div align="left">
												���֤�ţ�
												<html:text name="rs" property="id" style="width:112px" />
												<html:checkbox name="rs" property="idsee" value="no" />
												(����) &nbsp;&nbsp;&nbsp;&nbsp;��ѧ���:
												<html:text name="rs" property="rxnf" style="width:40px" />
												&nbsp;&nbsp;&nbsp;&nbsp;
												<font color="red">*</font>ѧ�ţ�
												<html:text property="xsxh" name="rs" styleId="xsxh"
													readonly="true" style="width:100px" />
												<br>
												<html:checkbox name="rs" property="hidden" value="yes" />
												ֻ��Ͷ�ݵ�λ����
											</div>
										</td>
									</tr>
								</thead>
								<tr>
									<td rowspan="3" align="center" width="30">
										<b>��<br>��<br>��<br>��</b>
									</td>
									<td align="right" width="70">
										������
									</td>
									<td width="150">
										<html:text name="rs" property="name" style="width:100%"
											readonly="true" />
									</td>
									<td align="right" width="70">
										�Ա�
									</td>
									<td width="150" colspan="2">
										<html:text name="rs" property="xb" style="width:100%"
											readonly="true" />
									</td>
									<td align="right" width="70">
										�������£�
									</td>
									<td width="150" colspan="2">
										<html:text name="rs" property="csny" style="width:100%" />
									</td>
								</tr>
								<tr>
									<td align="right">
										���壺
									</td>
									<td width="150">
										<html:select name="rs" property="mz" style="width:100%">
											<html:options collection="mzList" property="mzmc"
												labelProperty="mzmc" />
										</html:select>
									</td>
									<td align="right">
										ѧ����
									</td>
									<td colspan="2">
										<html:text name="rs" property="xl" style="width:100%" />
									</td>
									<td align="right">
										������ò��
									</td>
									<td colspan="2">
										<html:select name="rs" property="zzmm" style="width:150px">
											<html:option value="�޵���������ʿ">�޵���������ʿ</html:option>
											<html:options collection="zzmmList" property="zzmm"
												labelProperty="zzmm" />
										</html:select>
									</td>
								</tr>
								<tr>
									<td align="right">
										רҵ���ƣ�
									</td>
									<td>
										<html:text name="rs" property="zymc" style="width:100%"
											readonly="true" />
									</td>
									<td align="right">
										����רҵ��
									</td>
									<td colspan="2">
										<html:text name="rs" property="fxzymc" style="width:100%" />
									</td>
									<td align="right">
										��Դ������
									</td>
									<td colspan="2">
										<html:text name="rs" property="sydq" style="width:100%" />
									</td>
								</tr>
								<tr>
									<td rowspan="2" align="center">
										<b>��<br>ϵ<br>��<br>��</b>
									</td>
									<td align="right">
										��ϵ��ַ��
									</td>
									<td colspan="3">
										<html:textarea name="rs" property="lxdz" rows="2"
											style="width:100%" />
									</td>
									<td align="right">
										��ϵ�绰��
									</td>
									<td colspan="3">
										<html:textarea name="rs" property="lxdh" rows="2"
											style="width:100%" />
									</td>
								</tr>
								<tr>
									<td align="right">
										�������룺
									</td>
									<td colspan="3">
										<html:text name="rs" property="yzbm" style="width:100%"
											maxlength="6" />
									</td>
									<td align="right">
										�������䣺
									</td>
									<td colspan="3">
										<html:text name="rs" property="email" style="width:100%"
											maxlength="25" />
									</td>
								</tr>
								<tr>
									<td rowspan="5" align="center">
										<b>ѧ<br>��<br>��<br>��<br>��<br>��</b>
									</td>
									<td align="center">
										�����
									</td>
									<td colspan="7">
										<html:textarea name="rs" property="hjqk" rows="6"
											style="width:100%" />
									</td>
								</tr>
								<tr>
									<td align="center">
										ѧϰ���
									</td>
									<td colspan="7">
										<html:textarea name="rs" property="xxqk" rows="6"
											style="width:100%" />
									</td>
								</tr>
								<tr>
									<td align="center">
										У����
										<br>
										�Ͻ���
									</td>
									<td colspan="7">
										<html:textarea name="rs" property="xjysjl" rows="6"
											style="width:100%" />
									</td>
								</tr>
								<tr>
									<td align="center">
										���ʵ
										<br>
										�����
									</td>
									<td colspan="7">
										<html:textarea name="rs" property="shsjqk" rows="6"
											style="width:100%" />
									</td>
								</tr>
								<tr>
									<td align="center">
										��������
									</td>
									<td colspan="4">
										<html:textarea name="rs" property="gzjl" rows="8"
											style="width:100%" />
									</td>
									<td align="center">
										�����س�
									</td>
									<td colspan="2">
										<html:textarea name="rs" property="grtc" rows="8"
											style="width:100%" />
									</td>
								</tr>
								<tr>
									<td align="center">
										<b>��<br>��<br>��<br>��</b>
									</td>
									<td colspan="8">
										<html:textarea name="rs" property="zwtj" rows="25"
											style="width:100%" />
									</td>
								</tr>
							</table>
							<div align="center">
								<button onclick="addgrjl();">
									�ύ/�޸�
								</button>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<button type="reset">
									����
								</button>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<button onclick="delgrjl();">
									ɾ��
								</button>
							</div>
						</div>
						<h2></h2>
					</div>
				</div>
			</div>
























			<div>
				<h3>
				</h3>
			</div>
			<jsp:include flush="true" page="foot.jsp"></jsp:include>
		</html:form>
		<logic:notEmpty name="save">
			<logic:equal name="save" value="ok">
				<script>
                      alert("�����Ǽǳɹ���");
                    </script>
			</logic:equal>
			<logic:equal name="save" value="no">
				<script>
                      alert("�����Ǽ�ʧ�ܣ�");
                    </script>
			</logic:equal>
		</logic:notEmpty>
		<logic:notEmpty name="update">
			<logic:equal name="update" value="ok">
				<script>
                      alert("�����޸ĳɹ���");
                    </script>
			</logic:equal>
			<logic:equal name="update" value="no">
				<script>
                      alert("�����޸�ʧ�ܣ�");
                    </script>
			</logic:equal>
		</logic:notEmpty>
		<logic:notEmpty name="delete">
			<logic:equal name="delete" value="ok">
				<script>
                      alert("����ɾ���ɹ���");
                    </script>
			</logic:equal>
			<logic:equal name="delete" value="no">
				<script>
                      alert("����ɾ��ʧ�ܣ�");
                    </script>
			</logic:equal>
		</logic:notEmpty>
	</body>
</html>
