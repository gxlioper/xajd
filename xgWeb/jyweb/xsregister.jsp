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
		<script type="text/javascript">
		function savethemessage(){
		 var xh =document.getElementById("xh").value;
	     var mm =document.getElementById("mm").value;
	     var mm2 =document.getElementById("mm2").value;
	     var tswt1 =document.getElementById("tswt1").value;
	     var da1 =document.getElementById("da1").value;
	     var tswt2 =document.getElementById("tswt2").value;
	     var da2 =document.getElementById("da2").value;
	     var email =document.getElementById("dzyx").value;
	     var yb =document.getElementById("yb").value;
	 
	     if(xh==""){
	      alert("������ѧ��!");
	     return false;
	     }
	     
	     if(onlyNumWords(xh)){
	      alert("ѧ��ֻ��Ϊ����!");
	     return false;
	     } 	   
	       
	       
	     if(mm==""){
	      alert("����������!");
	     return false;
	     }
	     
	     if(mm.length<6){
	      alert("���볤�Ȳ�������6λ!");
	     return false;
	     }   
	     
	     if(mm2==""){
	      alert("���ٴ���������!");
	     return false;
	     }  
	        
	     if(mm!=mm2){
	      alert("��������ǰ�󲻷���");
	     return false;
	     }
     
		 if(tswt1==""){
	      alert("��ѡ���һ����ʾ���⣡");
	     return false;
	     }
		 
		 if(tswt1!=""&&da1==""){
		  alert("����д��ʾ����1�Ĵ𰸣�");
	     return false;
		 }
		 
		 if(tswt2==""){
	      alert("��ѡ��ڶ�����ʾ���⣡");
	     return false;
	     }
		 
		 if(tswt2!=""&&da2==""){
		  alert("����д��ʾ����2�Ĵ𰸣�");
	     return false;
		 }
		 if(email!=""&&!isEmail(email)){
		  alert("���������ʽ����ȷ!");
	     return false;
		 }
		 
		 if(yb!=""&&!isNumber(yb )){
		  alert("�ʱ�ӦΪ���֣�");
		  return false;
		 }
		 
	     if(yb!=""&&yb.length!=6){
	      alert("�ʱ೤��ӦΪ6λ��");
	      return false;
	     }
		 
		 
		
		 document.forms[0].action = "xsregister.do?method=xsregister&doType=save&jytype=jyweb";
		 document.forms[0].submit();
		
		}
		
		/*
        *�ж��Ƿ����� 
        */
        function isNumber(s){
	      var p = /^(-|\+)?\d+$/;
	      return p.test(s); 
       } 
    
       /*
        *�ж��Ƿ�ֻ����ĸ������ 
        */
        function onlyNumWords(s){
         var p = /[^\da-zA-Z]/g;
         return p.test(s);
       }
      
      /*
        *�жϵ����ʼ���ַ��ȷ��
        */
      
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
		<logic:equal name="xxdm" value="10856" scope="session">
			<logic:equal name="yhm" value="" scope="session">
				<jsp:include flush="true" page="head_false.jsp"></jsp:include>
				<input type="hidden" name="lock" value="closed" />
			</logic:equal>
			<logic:notEqual name="yhm" value="" scope="session">
				<jsp:include flush="true" page="head.jsp"></jsp:include>
				<input type="hidden" name="lock" value="open" />
			</logic:notEqual>
		</logic:equal>
		<logic:notEqual name="xxdm" value="10856" scope="session">
			<jsp:include flush="true" page="head.jsp"></jsp:include>
			<input type="hidden" name="lock" value="open" />
		</logic:notEqual>
		<html:form action="/dwregister" method="post">
			<div class="mainframe">
				<div class="jy_midframe">
					<h1>
						ѧ��ע��
					</h1>
					<table width="97%" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<tr>
							<td height="25px" bgcolor="#F4F4F4">
								<div class="biaoti">
									������Ϣ
									<font style="color:#000; font-size:12px; font-weight:normal;">(<font
										color="red">*</font> Ϊ������)</font>
								</div>
							</td>
						</tr>
						<tr height="25">
							<td>
								<div class="dwxx">
									ѧ�ţ�
								</div>

								<div class="bdan">
									<html:text name="rs" property="xh"
										style="width:150px;height:20px;
border: 1px solid #ccc;
border-top:1px solid #000;
border-right:1px solid #ccc;
border-bottom:1px solid #ccc;
border-left:1px solid #000;"
										maxlength="15" />
									<font color="red">*</font><font color="black">(�15λ��Ӣ�ĺ�����)</font>
								</div>
							</td>
						</tr>
						<tr height="25">
							<td>
								<div class="dwxx">
									���룺
								</div>
								<div class="bdan">
									<html:password name="rs" property="mm"
										style="width:150px;height:20px;
border: 1px solid #ccc;
border-top:1px solid #000;
border-right:1px solid #ccc;
border-bottom:1px solid #ccc;
border-left:1px solid #000;"
										maxlength="16" />
									<font color="red">*</font><font color="black">(���ȣ�6-16λ)</font>
								</div>
							</td>
						</tr>
						<tr height="25">
							<td>
								<div class="dwxx">
									�ظ����룺
								</div>
								<div class="bdan">
									<html:password name="rs" property="mm2"
										style="width:150px;height:20px;
border: 1px solid #ccc;
border-top:1px solid #000;
border-right:1px solid #ccc;
border-bottom:1px solid #ccc;
border-left:1px solid #000;"
										maxlength="16" />
									<font color="red">*</font><font color="black">(���ȣ�6-16λ)</font>
								</div>
							</td>
						</tr>
						<tr>
							<td height="25px" bgcolor="#F4F4F4">
								<div class="biaoti">
									�����һ���Ϣ
									<font style="color:#000; font-size:12px; font-weight:normal;">(<font
										color="red">*</font> Ϊ������)</font>
								</div>
							</td>

						</tr>

						<tr height="25">
							<td>
								<div class="dwxx">
									��ʾ����1��
								</div>
								<div class="bdan">
									<html:select name="rs" property="tswt1"
										style="width:150px;height:20px;
border: 1px solid #ccc;
border-top:1px solid #000;
border-right:1px solid #ccc;
border-bottom:1px solid #ccc;
border-left:1px solid #000;">
										<html:option value=""></html:option>
										<html:option value="����ϲ���ĵ�Ӱ��"></html:option>
										<html:option value="����ϲ�������ǣ�"></html:option>
										<html:option value="����ϲ���ĸ�����"></html:option>
										<html:option value="����ϲ����С˵��"></html:option>
										<html:option value="����ϲ������ɫ��"></html:option>
										<html:option value="����ϲ���ĳ��"></html:option>
										<html:option value="����ϲ����ʳ��"></html:option>
										<html:option value="����ϲ�������ϣ�"></html:option>
										<html:option value="����ϲ���Ĺ��ң�"></html:option>
										<html:option value="����ϲ�����˶�Ա��"></html:option>
										<html:option value="����ϲ������ʷ���"></html:option>
									</html:select>
									<font color="red">*</font>
								</div>
							</td>
						</tr>
						<tr height="25">
							<td>
								<div class="dwxx">
									��1��
								</div>
								<div class="bdan">
									<html:text name="rs" property="da1"
										style="width:150px;height:20px;
border: 1px solid #ccc;
border-top:1px solid #000;
border-right:1px solid #ccc;
border-bottom:1px solid #ccc;
border-left:1px solid #000;"
										maxlength="18" />
									<font color="red">*</font>
								</div>
							</td>
						</tr>
						<tr height="25">
							<td>
								<div class="dwxx">
									��ʾ����2��
								</div>
								<div class="bdan">
									<html:select name="rs" property="tswt2"
										style="width:150px;height:20px;
border: 1px solid #ccc;
border-top:1px solid #000;
border-right:1px solid #ccc;
border-bottom:1px solid #ccc;
border-left:1px solid #000;">
										<html:option value=""></html:option>
										<html:option value="��������ĵ�Ӱ��"></html:option>
										<html:option value="������������ǣ�"></html:option>
										<html:option value="��������ĸ�����"></html:option>
										<html:option value="���������С˵��"></html:option>
										<html:option value="�����������ɫ��"></html:option>
										<html:option value="��������ĳ��"></html:option>
										<html:option value="���������ʳ��"></html:option>
										<html:option value="������������ϣ�"></html:option>
										<html:option value="��������Ĺ��ң�"></html:option>
										<html:option value="����������˶�Ա��"></html:option>
										<html:option value="�����������ʷ���"></html:option>
									</html:select>
									<font color="red">*</font>
								</div>
							</td>
						</tr>
						<tr height="25">
							<td>
								<div class="dwxx">
									��2��
								</div>
								<div class="bdan">
									<html:text name="rs" property="da2"
										style="width:150px;height:20px;
border: 1px solid #ccc;
border-top:1px solid #000;
border-right:1px solid #ccc;
border-bottom:1px solid #ccc;
border-left:1px solid #000;"
										maxlength="18" />
									<font color="red">*</font>
								</div>
							</td>
						</tr>



						<tr height="25">
							<td bgcolor="#F4F4F4">
								<div class="biaoti">
									���˻�����Ϣ
									<font style="color:#000; font-size:12px; font-weight:normal;">(<font
										color="red">*</font> Ϊ������)</font>
								</div>
							</td>
						</tr>
						<tr height="25">
							<td>
								<div class="dwxx"> 
									������ 
								</div>
								<div class="bdan">
									<html:text name="rs" property="xm"
										style="width:150px;height:20px;
border: 1px solid #ccc;
border-top:1px solid #000;
border-right:1px solid #ccc;
border-bottom:1px solid #ccc;
border-left:1px solid #000;"
										maxlength="10" />
									<font color="red">*</font>
								</div>
							</td>
						</tr>
						<tr height="25">
							<td>
								<div class="dwxx">
									��ϵ�绰��
								</div>
								<div class="bdan">
									<html:text name="rs" property="lxdh"
										style="width:150px;height:20px;
border: 1px solid #ccc;
border-top:1px solid #000;
border-right:1px solid #ccc;
border-bottom:1px solid #ccc;
border-left:1px solid #000;"
										maxlength="20" />
									<font color="red">*</font><font color="black">(��ص绰��������)</font>
								</div>
							</td>
						</tr>
						<tr height="25">
							<td>
								<div class="dwxx">
									���棺
								</div>
								<div class="bdan">
									<html:text name="rs" property="cz"
										style="width:150px;height:20px;
border: 1px solid #ccc;
border-top:1px solid #000;
border-right:1px solid #ccc;
border-bottom:1px solid #ccc;
border-left:1px solid #000;"
										maxlength="20" />
								</div>
							</td>
						<tr height="25">
							<td>
								<div class="dwxx">
									�ֻ����룺
								</div>
								<div class="bdan">
									<html:text name="rs" property="sjhm"
										style="width:150px;height:20px;
border: 1px solid #ccc;
border-top:1px solid #000;
border-right:1px solid #ccc;
border-bottom:1px solid #ccc;
border-left:1px solid #000;"
										maxlength="20" />
								</div>
							</td>
						</tr>
						<tr height="25">
							<td>
								<div class="dwxx">
									�������䣺
								</div>
								<div class="bdan">
									<html:text name="rs" property="dzyx"
										style="width:150px;height:20px;
border: 1px solid #ccc;
border-top:1px solid #000;
border-right:1px solid #ccc;
border-bottom:1px solid #ccc;
border-left:1px solid #000;"
										maxlength="25" />
								</div>
							</td>
						</tr>
						<tr height="25">
							<td>
								<div class="dwxx">
									��ַ��
								</div>
								<div class="bdan">
									<html:text name="rs" property="dz"
										style="width:150px;height:20px;
border: 1px solid #ccc;
border-top:1px solid #000;
border-right:1px solid #ccc;
border-bottom:1px solid #ccc;
border-left:1px solid #000;"
										maxlength="25" />
								</div>
							</td>
						</tr>
						<tr height="25">
							<td>
								<div class="dwxx">
									�ʱࣺ
								</div>
								<div class="bdan">
									<html:text name="rs" property="yb"
										style="width:150px;height:20px;
border: 1px solid #ccc;
border-top:1px solid #000;
border-right:1px solid #ccc;
border-bottom:1px solid #ccc;
border-left:1px solid #000;"
										maxlength="6" />
								</div>
							</td>
						</tr>

					</table>
					<div align="center">
						<button onclick="savethemessage();">
							�ύע����Ϣ
						</button>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<button type="reset">
							�ر�
						</button>
					</div>
				</div>
		</html:form>
		<h3>

		</h3>
		<div>
		</div>
		<jsp:include flush="true" page="foot.jsp"></jsp:include>
		<logic:notEmpty name="yhyzc">
			<logic:equal name="yhyzc" value="yhyzc">
				<script>
                      alert("�û���ע�ᣬ�����ظ�ע�ᣡ");
                    </script>
			</logic:equal>
		</logic:notEmpty>
		<logic:notEmpty name="xhbzq">
			<logic:equal name="xhbzq" value="xhbzq">
				<script>
                      alert("�㲻�Ǳ�Уѧ��������ע�ᣡ");
                    </script>
			</logic:equal>
		</logic:notEmpty>
		<logic:notEmpty name="add">
			<logic:equal name="add" value="no">
				<script>
                      alert("��֤�����");
                    </script>
			</logic:equal>
		</logic:notEmpty>
		<logic:notEmpty name="save">
			<logic:equal name="save" value="no">
				<script>
                      alert("ע��ʧ�ܣ��û�����λ�����ѱ�ʹ�ã�");
                    </script>
			</logic:equal>
		</logic:notEmpty>
		<logic:notEmpty name="dwmcchongfu">
			<logic:equal name="dwmcchongfu" value="dwmcchongfu">
				<script>
                      alert("�õ�λ�����ѱ�ע�ᣡ");
                    </script>
			</logic:equal>
		</logic:notEmpty>
	</body>
</html>
