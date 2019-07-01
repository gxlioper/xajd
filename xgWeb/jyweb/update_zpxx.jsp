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
		<base target="_self">
		<link id="csss" rel="icon" href="images/icon.ico" type="image/x-icon" />
		<link href="jyweb/style/module.css" rel="stylesheet" type="text/css">
		<link href="jyweb/style/base.css" rel="stylesheet" type="text/css">
		<link href="jyweb/style/forms.css" rel="stylesheet" type="text/css">
		<link id="csssDate" rel="stylesheet" rev="stylesheet"
			href="js/calendar.css" type="text/css" media="all" />

		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/jsFunction.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script language="javascript" src="js/AjaxFunction.js"></script>
		<script language="javascript" src="js/jygl.js"></script>
		<script type="text/javascript">
		function updatezpxx(){
		   var pkValue= $("pk").value;
		   var email = document.getElementById("email").value;   
           var zpzw = document.getElementById("zpzw").value;   
           var gsmc = document.getElementById("gsmc").value;  
           var day = document.getElementById("day").value;
           var hour = document.getElementById("hour").value;
           var min = document.getElementById("min").value; 
     
          if(zpzw==""){
           alert("��Ƹְλ����Ϊ�գ�");
           return false;
          }
          if(gsmc==""){
           alert("��˾���Ʋ���Ϊ�գ�");
           return false;
          }      
          if((email != null) && (email != "") && (!isEmail(email))){
           alert("�������䲻�Ϸ���");
           return false;
          }
          if((day==""&&(hour!=""||min!=""))||(day!=""&&hour==""&&min!="")||(day!=""&&hour!=""&&min=="")){
           alert("����ʱ���������");
           return false;
          }
		          document.forms[0].action = "updatezpxx.do?method=updatezpxx&jytype=jyweb&doType=update&pkValue="+pkValue;
	              document.forms[0].submit();
		    
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
		<html:form action="/updatezpxx" method="post">
			<input type="hidden" name="pk"
				value="<bean:write name="rs" property="rid" />" />
			 <input type="hidden" value="${rs.sxzy}" id="viewzydm" name="viewzydm"/>
			<table align="center" width="600" >
				<tr height="25" class="btys">
					<td colspan="4" align="center">
						��Ƹ��Ϣ�޸�
					</td>
				</tr>
				<tr>
					<td colspan="4" align="center">
						<font color="red">ע�⣺��ͨ����˵���Ƹ��Ϣ�޷��޸ġ�</font>
					</td>
				</tr>
				<tr height="28" >
					<td align="right" width="15%">
						<font color="red">*</font>��Ƹְλ��
					</td>
					<td width="35%">
						<html:text name="rs" property="zpzw" maxlength="25"
							style="width:100%" />
					</td>
					<td align="right" width="15%">
						<font color="red">*</font>��˾���ƣ�
					</td>
					<td width="35%">
						<html:text name="rs" property="gsmc" readonly="true"
							style="width:100%" />
					</td>
				</tr>
				<tr height="28">
					<td align="right">
						�������䣺
					</td>
					<td>
						<html:text name="rs" property="email" maxlength="30"
							style="width:100%" />
					</td>
					<td align="right">
						��ϵ�绰��
					</td>
					<td>
						<html:text name="rs" property="lxdh" maxlength="15"
							style="width:100%" />
					</td>
				</tr>
				<tr height="28">
					<td align="right">
						�����ص㣺
					</td>
					<td>
						<html:text name="rs" property="gzdd" maxlength="25"
							style="width:100%" />
					</td>
					<td align="right">
						��Ƹ������
					</td>
					<td>
						<html:text name="rs" property="zprs" maxlength="3"
							style="width:100%" />
					</td>
				</tr>
				<tr height="28">
					<td align="right">
						��ҵ���ࣺ
					</td>
					<td>
						<html:select name="rs" property="hyfl" styleId="hyfl"
							style="width:100%">
							<html:option value=""></html:option>
							<html:options collection="hyflList" property="hyfl"
								labelProperty="hyfl" />
						</html:select>
					</td>
					<td align="right">
						����Ҫ��
					</td>
					<td>
						<html:text name="rs" property="wyyq" maxlength="10"
							style="width:100%" />
					</td>
				</tr>
				<tr height="28">
					<td align="right">
						������нˮ��
					</td>
					<td>
						<div align="left">
							<html:select name="rs" property="syxs">
								<html:option value=""></html:option>
								<html:option value="����">����</html:option>
								<html:option value="1000����">1000����</html:option>
								<html:option value="1000-1500">1000-1500</html:option>
								<html:option value="1500-2500">1500-2500</html:option>
								<html:option value="2500-3500">2500-3500</html:option>
								<html:option value="3500-5000">3500-5000</html:option>
								<html:option value="5000-7000">5000-7000</html:option>
								<html:option value="7000-10000">7000-10000</html:option>
								<html:option value="10000����">10000����</html:option>
							</html:select>
						</div>
					</td>
					<td align="right">
						ת����нˮ��
					</td>
					<td>
						<div align="left">
							<html:select name="rs" property="zzxs">
								<html:option value=""></html:option>
								<html:option value="����">����</html:option>
								<html:option value="1000����">1000����</html:option>
								<html:option value="1000-1500">1000-1500</html:option>
								<html:option value="1500-2500">1500-2500</html:option>
								<html:option value="2500-3500">2500-3500</html:option>
								<html:option value="3500-5000">3500-5000</html:option>
								<html:option value="5000-7000">5000-7000</html:option>
								<html:option value="7000-10000">7000-10000</html:option>
								<html:option value="10000����">10000����</html:option>
							</html:select>
						</div>
					</td>
				</tr>
				<tr height="28">
					<td align="right">
						�Ա�Ҫ��
					</td>
					<td>
						<div align="left">
							<html:select name="rs" property="xb" style="width:60px">
								<html:option value=""></html:option>
								<html:option value="��">��</html:option>
								<html:option value="Ů">Ů</html:option>
							</html:select>&nbsp;&nbsp;&nbsp;&nbsp;
						��λ���ʣ�
						<html:select name="rs" property="dwxz" style="width:60px">
							<html:option value=""></html:option>
							<html:option value="����">����</html:option>
							<html:option value="����">����</html:option>
							<html:option value="��Ӫ">��Ӫ</html:option>
							<html:option value="˽Ӫ">˽Ӫ</html:option>
							<html:option value="�ɷ�">�ɷ�</html:option>
							<html:option value="����">����</html:option>
							<html:option value="����">����</html:option>
						</html:select>
						</div>
					</td>
					<td align="right">
						����ʱ�䣺
					</td>
					<td>
						<html:text name="rs" style="cursor:hand; width:70px;"
							styleId="day" property="day"
							onclick="return showCalendar('day','y-mm-dd');" readonly="true" />
						<html:select name="rs" property="hour">
							<html:option value=""></html:option>
							<html:option value="06">06</html:option>
							<html:option value="07">07</html:option>
							<html:option value="08">08</html:option>
							<html:option value="09">09</html:option>
							<html:option value="10">10</html:option>
							<html:option value="11">11</html:option>
							<html:option value="12">12</html:option>
							<html:option value="13">13</html:option>
							<html:option value="14">14</html:option>
							<html:option value="15">15</html:option>
							<html:option value="16">16</html:option>
							<html:option value="17">17</html:option>
							<html:option value="18">18</html:option>
							<html:option value="19">19</html:option>
							<html:option value="20">20</html:option>
							<html:option value="21">21</html:option>
							<html:option value="22">22</html:option>
						</html:select>
						ʱ
						<html:select name="rs" property="min">
							<html:option value=""></html:option>
							<html:option value="00">00</html:option>
							<html:option value="10">10</html:option>
							<html:option value="20">20</html:option>
							<html:option value="30">30</html:option>
							<html:option value="40">40</html:option>
							<html:option value="50">50</html:option>
						</html:select>
						��
					</td>
				</tr>
				<tr height="28">
					<td align="right">
						ѧ��Ҫ��
					</td>
					<td>
						<div align="left">
							<html:select name="rs" property="xlyq" style="width:90px">
								<html:option value=""></html:option>
								<html:option value="ר��">ר��</html:option>
								<html:option value="����">����</html:option>
								<html:option value="˶ʿ">˶ʿ</html:option>
								<html:option value="��ʿ">��ʿ</html:option>
							</html:select>
						</div>
					</td>
					<td align="right">
						���Եص㣺
					</td>
					<td>
						<html:text name="rs" property="msdd" maxlength="30"
							style="width:100%" />
					</td>
				</tr>
				<tr height="28">
					<td align="right">
						����Я����
					</td>
					<td>
						<html:text name="rs" property="msxd" style="width:100%" />
					</td>
					<td align="right">
						��λ��ַ��
					</td>
					<td>
						<html:text name="rs" property="dwdz" maxlength="30"
							style="width:100%" />
					</td>

				</tr>
				<logic:equal value="10856" name="xxdm">
									<tr height="28">
										<td align="right">
											����רҵ��
										</td>
										<td align="left">
											<div style="overflow: auto;height:220px">
												<table class="tbstyle" id="zyinfo">
												<thead>
													<td><input type="checkBox" title="ȫѡ" onclick="selectall(this);"/></td>
													<td>רҵ����</td>
												</thead>
												
	 											<logic:iterate id="s" name="zyList">
	 											<tr>
	 												<td><input type="checkBox" value="<bean:write name="s" property="zydm"/>" onclick="viewzy()"/></td>
	 												<td><bean:write name="s" property="zymc"/></td>
												</tr>
	 											</logic:iterate>
	 											</table>
	 										</div>										
										</td>
										<td colspan="2">
											<html:textarea name="rs" property="zy" readonly="true" styleId="sxzy" cols="35" rows="15">רҵ����</html:textarea>
										</td>
									</tr>
									</logic:equal>
				<tr>
					<td align="right">
						��λְ��
					</td>
					<td colspan="3">
						<html:textarea name="rs" property="gwzz" rows="10"
							style="width:100%" />
					</td>
				</tr>
				<tr>
					<td align="right">
						ְλҪ��
					</td>
					<td colspan="3" >
						<html:textarea name="rs" property="zwyq" rows="10"
							style="width:100%" />
					</td>
				</tr>
				<tr>
					<td align="right">
						��˾��飺
					</td>
					<td colspan="3">
						<html:textarea name="rs" property="gsjj" rows="15"
							style="width:100%" />
					</td>
				</tr>
			</table>
			<div align="center">
				<button onclick="updatezpxx();" style="width:80px">
					�� ��
				</button>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<button type="reset" style="width:80px">
					�� ��
				</button>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<button
					onclick="Close();window.dialogArguments.document.getElementById('search_go').click();"
					style="width:80px">
					�� ��
				</button>
			</div>
		</html:form>
		<logic:notEmpty name="update">
			<logic:equal name="update" value="ok">
				<script>
                      alert("�޸ĳɹ���");
               </script>
			</logic:equal>
			<logic:equal name="update" value="no">
				<script>
                      alert("�޸�ʧ�ܣ�");
               </script>
			</logic:equal>
		</logic:notEmpty>
		<logic:notEmpty name="xxsh">
			<logic:equal name="xxsh" value="pass">
				<script>
                      alert("�Ѿ�ͨ����˵���Ƹ��Ϣ�޷��޸ģ�");
               </script>
			</logic:equal>
		</logic:notEmpty>
		<logic:equal value="10856" name="xxdm">
		<script type="text/javascript">
        	var viewzydm = document.getElementById('viewzydm');
        	var sxzy = document.getElementById('sxzy');
        	if(viewzydm.value == ''){
        		sxzy.innerText = 'רҵ����';
        	}else{
        		viewzydm.value = ','+viewzydm.value+',';
        		var tab = document.getElementById('zyinfo');
				var array = tab.getElementsByTagName('tr');			
				for(var i=0;i<array.length;i++){
					if(viewzydm.value.indexOf(','+array[i].cells[0].getElementsByTagName('input')[0].value+',')>-1){
						array[i].cells[0].getElementsByTagName('input')[0].checked = true;
					}
				}
        	}     
        </script>
        </logic:equal>
	</body>
</html>
