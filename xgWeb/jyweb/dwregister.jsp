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
		 var yzm =document.getElementById("yzm").value;
	     var yhm =document.getElementById("yhm").value;
	     var mm =document.getElementById("mm").value;
	     var mm2 =document.getElementById("mm2").value;
	     var tswt1 =document.getElementById("tswt1").value;
	     var da1 =document.getElementById("da1").value;
	     var tswt2 =document.getElementById("tswt2").value;
	     var da2 =document.getElementById("da2").value;
	     var dwmc =document.getElementById("dwmc").value;
	     var qyfr =document.getElementById("qyfr").value;
	     var jgdmzh =document.getElementById("jgdmzh").value;
	     var dwxz =document.getElementById("dwxz").value;
	     var hyfl =document.getElementById("hyfl").value;
	     var lxr =document.getElementById("lxr").value;
	     var lxdh =document.getElementById("lxdh").value;
	     var cz =document.getElementById("cz").value;
	     var email =document.getElementById("email").value;
	     var yb =document.getElementById("yb").value;
	     
	 
	     if(yhm==""){
	      alert("�������û���!");
	     return false;
	     }
	     
	     if(onlyNumWords(yhm)){
	      alert("�û���ֻ��Ϊ���ֻ���ĸ!");
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
     
	     if(yzm==""){
	      alert("��������֤��!");
	     return false;
	     }
	    
	     if(yzm.length!=4){
	      alert("��֤��λ������!");
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
		
		 
		 if(dwmc==""){
		  alert("����д��λ���ƣ�");
		  return false;
		 }
		 
		 
		 
		 if(qyfr==""){
		  alert("����д��ҵ���ˣ�");
	     return false;
		 }
		
		 if(isNumber(qyfr)){
		  alert("����ȷ������ҵ���ˣ�");
	     return false;
		 }
		 
		 if(jgdmzh==""){
		  alert("����д��������֤�ţ�");
	     return false;
		 }
		 
		 if(onlyNumWords(jgdmzh)){
		  alert("��������֤�Ÿ�ʽ����ȷ��");
	     return false;
		 }
		 
		 if(dwxz==""){
		  alert("����д��λ����!");
	     return false;
		 }
		 
		 if(hyfl==""){
		  alert("����д��ҵ����!");
	     return false;
		 }
		 
		 if(lxr==""){
		  alert("����д��ϵ��!");
	     return false;
		 }
		 
		 if(isNumber(lxr)){
		  alert("����ȷ��д��ϵ��!");
	     return false;
		 }
		 
		 if(lxdh==""){
		  alert("����д��ϵ�绰!");
	     return false;
		 }
		 
		 if(lxdh.lenght<7){
		  alert("��ϵ�绰���Ȳ�������7λ��");
		  return false;
		 }
		 
		 
		 if(cz!=""&&!isNumber(cz)){
		  alert("����ӦΪ����!");
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
		 
		 
		
		 document.forms[0].action = "dwregister.do?method=dwregister&doType=save&jytype=jyweb";
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
						��λע��
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
									�û�����
								</div>

								<div class="bdan">
									<html:text name="rs" property="yhm"
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
						<tr height="25">
							<td>
								<div class="dwxx">
									��������֤�룺
								</div>
								<div class="bdan">
									<html:text name="rs" property="yzm"
										style="width:150px;height:20px;
border: 1px solid #ccc;
border-top:1px solid #000;
border-right:1px solid #ccc;
border-bottom:1px solid #ccc;
border-left:1px solid #000;"
										maxlength="4" />
									<img src="yzm.jsp" border="0" align="absmiddle" />
									<font color="red">*</font><font color="black">(����Сд����)</font>
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
									��λ������Ϣ
									<font style="color:#000; font-size:12px; font-weight:normal;">(<font
										color="red">*</font> Ϊ������)</font>
								</div>
							</td>
						</tr>
						<tr height="25">
							<td>
								<div class="dwxx">
									��λ���ƣ�
								</div>
								<div class="bdan">
									<html:text name="rs" property="dwmc"
										style="width:150px;height:20px;
border: 1px solid #ccc;
border-top:1px solid #000;
border-right:1px solid #ccc;
border-bottom:1px solid #ccc;
border-left:1px solid #000;"
										maxlength="20" />
									<font color="red">*</font>
								</div>
							</td>
						</tr>
						<tr height="25">
							<td>
								<div class="dwxx">
									��ҵ���ˣ�
								</div>
								<div class="bdan">
									<html:text name="rs" property="qyfr"
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
									��������֤�ţ�
								</div>
								<div class="bdan">
									<html:text name="rs" property="jgdmzh"
										style="width:150px;height:20px;
border: 1px solid #ccc;
border-top:1px solid #000;
border-right:1px solid #ccc;
border-bottom:1px solid #ccc;
border-left:1px solid #000;"
										maxlength="15" />
									<font color="red">*</font>
								</div>
							</td>
						</tr>
						<tr height="25">
							<td>
								<div class="dwxx">
									��λ���ʣ�
								</div>
								<div class="bdan">
									<html:select name="rs" property="dwxz" styleId="dwxz"
										style="width:150px;height:20px;
border: 1px solid #ccc;
border-top:1px solid #000;
border-right:1px solid #ccc;
border-bottom:1px solid #ccc;
border-left:1px solid #000;">
										<html:option value=""></html:option>
										<html:options collection="dwxzdm2List" property="dwxz"
											labelProperty="dwxz" />
									</html:select>
									<font color="red">*</font>
								</div>
							</td>
						</tr>
						<tr height="25">
							<td>
								<div class="dwxx">
									��ҵ���ࣺ
								</div>
								<div class="bdan">
									<html:select name="rs" property="hyfl" styleId="hyfl"
										style="width:250px;height:20px;
border: 1px solid #ccc;
border-top:1px solid #000;
border-right:1px solid #ccc;
border-bottom:1px solid #ccc;
border-left:1px solid #000;">
										<html:option value=""></html:option>
										<html:options collection="hyflList" property="hyfl"
											labelProperty="hyfl" />
									</html:select>
									<font color="red">*</font>
								</div>
							</td>
						</tr>
						<tr height="25">
							<td>
								<div class="dwxx">
									��ϵ�ˣ�
								</div>
								<div class="bdan">
									<html:text name="rs" property="lxr"
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
						</tr>
						<tr height="25">
							<td>
								<div class="dwxx">
									�������䣺
								</div>
								<div class="bdan">
									<html:text name="rs" property="email"
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
						<tr height="25">
							<td>
								<div class="dwxx">
									��λ��飺
								</div>
								<div class="bdan">
									<html:textarea name="rs" property="dwjj"
										style="width:100%;height:200px;
border: 1px solid #ccc;
border-top:1px solid #000;
border-right:1px solid #ccc;
border-bottom:1px solid #ccc;
border-left:1px solid #000;"
										rows="15" />

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
							�� ��
						</button>
					</div>
				</div>
		</html:form>
		<h3>

		</h3>
		<div>
		</div>
		<jsp:include flush="true" page="foot.jsp"></jsp:include>

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
