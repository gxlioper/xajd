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

		<meta name="Copyright" content="正方软件 zfsoft" />
		
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
		var xh = document.getElementById("xh").value;
		var xm = document.getElementById("xm").value;
		var qysj = document.getElementById("qysj").value;
		var zyyx = document.getElementById("zyyx").value; 
		var sybz1 = document.getElementById("sybz1").value; 
		var sybz2 = document.getElementById("sybz2").value; 
		var sybz3 = document.getElementById("sybz3").value; 
		if(xh==""){
			alert("学号不能为空！");
			return false;
		}
		if(xm==""){
			alert("姓名不能为空！");
			return false;
		}
		if(qysj==""){
			alert("签约时间不能为空！");
			return false;
		}
		if(zyyx.length>800){
			alert("职业意向不能超过800个汉字！");
			return false;
		}
		if(sybz1.length>800){
			alert("就业地意向3不能超过800个汉字！");
			return false;
		}
		if(sybz2.length>800){
			alert("就业地意向3不能超过800个汉字！");
			return false;
		}
		if(sybz3.length>800){
			alert("就业地意向3不能超过800个汉字！");
			return false;
		}
		document.forms[0].action = "/xgxt/jyxxtjwh.do?act=save";
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
					当前位置：就业管理 - 就业信息 - 就业信息 统计维护
				</div>
			</div>
			<logic:empty name="rs">
				<br />
				<br />
				<p align="center">
					有错误发生！
				</p>
			</logic:empty>
			<logic:notEmpty name="rs">
				<logic:equal name="rs" property="stuExists" value="no">
					<script>
    alert("您输入的学号无效!");
    </script>
				</logic:equal>
				<html:hidden name="rs" property="nd" />

				<table width="100%" id="rsT" class="tbstyle">
					<thead>
					</thead>
					<tr style="height:22px">
							<td align="right" style="width=18%">
								<font color="red">*</font>学号：
							</td>
							<td align="left" style="width=32%">
								<html:text name='rs' property="xh" styleId="xsxh"
									style="width:210px"/>
								<logic:equal value="123466" name="xxdm" scope="session">
								<button onclick="showTopWin('/xgxt/jyxyTurnInfo.do',750,550);"
									class="btn_01" id="buttonFindStu">
									选择
								</button>
								</logic:equal>
								<logic:equal value="10338" name="xxdm" scope="session">
								<button onclick="showTopWin('/xgxt/jyxyTurnInfo.do',750,550);"
									class="btn_01" id="buttonFindStu">
									选择
								</button>
								</logic:equal>
							</td>
						<td align="right" style="width:20%">
							<font color="red">*</font>姓名：
						</td>
						<td align="left">
							<html:text name="rs" property="xm"  style="width: 210px"/>
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							院校代码：
						</td>
						<td align="left">
							<html:text name="rs" property="yxdm" 
								style="width:210px" />
						<td align="right">
							院校名称：
						</td>
						<td align="left">
							<html:text name="rs" property="yxmc" 
								style="width:210px" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							所在<bean:message key="lable.xsgzyxpzxy" />：
						</td>
						<td align="left">
							<html:select name="rs" property="szyx" style="width:150px">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xymc"
											labelProperty="xymc" />
								</html:select>
						</td>
						<td align="right">
							所在班级：
						</td>
						<td align="left">
							<html:text name="rs" property="szbj" style="width:210px" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							专业代码：
						</td>
						<td align="left">
							<html:text name="rs" property="zydm" style="width:210px" />
						</td>
						<td align="right">
							专业名称：
						</td>
						<td align="left">
							<html:text name="rs" property="zymc" style="width:210px" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							身份证号码：
						</td>
						<td align="left">
							<html:text name="rs" property="sfzhm" style="width:210px" />
						</td>
						<td align="right">
							性别代码：
						</td>
						<td align="left">
							<html:text name="rs" property="xbdm" style="width:210px" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							学历代码：
						</td>
						<td align="left">
							<html:text name="rs" property="xldm" style="width:210px" />
						</td>
						<td align="right">
							学位代码：
						</td>
						<td align="left">
							<html:text name="rs" property="xwdm" style="width:210px" />
						</td>
					</tr>

					<tr style="height:22px">
						<td align="right">
							生源所在地代码：
						</td>
						<td align="left">
							<html:text name="rs" property="syszddm" style="width:210px" />
						</td>
						<td align="right">
							生源所在地：
						</td>
						<td align="left">
							<html:text name="rs" property="syszd" style="width:210px"/>
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							民族代码：
						</td>
						<td align="left">
								<html:text name="rs" property="mzdm" style="width:210px" />
						</td>
						<td align="right">
							政治面貌代码：
						</td>
						<td align="left">
								<html:text name="rs" property="zzmmdm" style="width:210px" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							是否在职：
						</td>
						<td align="left">
								<html:text name="rs" property="sfzz" 
									style="width:210px" />
						</td>


						<td align="right">
							是否师范：
						</td>
						<td align="left">
								<html:text name="rs" property="sfsf" style="width:210px"  />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							是否独立：
						</td>
						<td align="left">
							<html:text name="rs" property="sfdl" style="width:210px"/>
						</td>
						<td align="right">
							学制：
						</td>
						<td align="left">
								<html:text name="rs" property="xz" style="width:210px"/>

						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							招生类别：
						</td>
						<td align="left">
								<html:text name="rs" property="zslb" style="width:210px"/>
						</td>
						<td align="right">
							培养方式代码：
						</td>
						<td align="left">
							<html:text name="rs" property="pyfsdm" style="width:210px"/>
						</td>

					</tr>
					<tr style="height:22px">
						<td align="right">
							定向或委培单位：
						</td>
						<td align="left">
								<html:text name="rs" property="dxhwpdw" style="width:210px"/>
						</td>
						<td align="right">
							入学时间：
						</td>
						<td align="left">
							<html:text name="rs" property="rxsj" style="width:210px" onclick="return showCalendar('rxsj','y-mm-dd');"/>

						</td>					
					</tr>
					<tr style="height:22px">
						<td align="right">
							毕业时间：
						</td>
						<td align="left">
							<html:text name="rs" property="bysj" style="width:210px" onclick="return showCalendar('bysj','y-mm-dd');"/>

						</td>
						<td align="right">
							毕业证书编号：
						</td>
						<td align="left">
							<html:text name="rs" property="byzsbh" style="width:210px"/>

						</td>
						
					</tr>
					<tr style="height:22px">
						<td align="right">
							外语语种代码：
						</td>
						<td align="left">
							<html:text name="rs" property="zxwyyzdm" style="width:210px"/>

						</td>
						<td align="right">
							外语等级：
						</td>
						<td align="left">
							<html:text name="rs" property="wydj" style="width:210px"/>

						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							计算机等级：
						</td>
						<td align="left">
							<html:text name="rs" property="jsjdj" style="width:210px"/>
						</td>
						<td align="right">
							手机号码：
						</td>
						<td align="left">
							<html:text name="rs" property="sjhm" style="width:210px"/>

						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							QQ号码：
						</td>
						<td align="left">
								<html:text name="rs" property="qqhm" style="width:210px" />
						</td>
						<td align="right">
							地址信息：
						</td>
						<td align="left">
								<html:text name="rs" property="dzxx" style="width:210px" />
						</td>
					</tr>
					<tr style="height:22px">
						
						<td align="right">
							联系电话：
						</td>
						<td align="left">
							<html:text name="rs" property="lxdh" style="width:210px"/>

						</td>
						<td align="right">
							联系地址：
						</td>
						<td align="left">
							<html:text name="rs" property="lxdz" style="width:210px"/>

						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							技能：
						</td>
						<td align="left">
							<html:text name="rs" property="jljn" style="width:210px"/>

						</td>
						<td align="right">
							<font color="red">*</font>签约时间：
						</td>
						<td align="left">
							<html:text name="rs" property="qysj" style="width:210px" onclick="return showCalendar('qysj','y-mm-dd');"/>
						</td>
					</tr>
					<logic:equal value="10338" name="xxdm" scope="session">
						<tr style="height:22px">
						<td align="right">
							职业意向：
						</td>
						<td align="left" colspan="3">
							<html:textarea property="zyyx" style="width:630px" rows="5"></html:textarea>
						</td>
					</tr>
					</logic:equal>
					<tr style="height:22px">
						<td align="right">
						<logic:equal value="10338" name="xxdm" scope="session">
							就业地意向1
						</logic:equal>
						<logic:notEqual value="10338" name="xxdm" scope="session">
							备注1：
						</logic:notEqual>
						</td>
						<td align="left" colspan="3">
						<logic:equal value="10338" name="xxdm" scope="session">
							<html:text property="sybz1" style="width:630px" maxlength="120"></html:text>
						</logic:equal>
						<logic:notEqual value="10338" name="xxdm" scope="session">
							<html:textarea property="sybz1" style="width:630px" rows="5"></html:textarea>
						</logic:notEqual>
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							<logic:equal value="10338" name="xxdm" scope="session">
							就业地意向2
						</logic:equal>
						<logic:notEqual value="10338" name="xxdm" scope="session">
							备注2：
						</logic:notEqual>
						</td>
						<td align="left" colspan="3">
						<logic:equal value="10338" name="xxdm" scope="session">
							<html:text property="sybz2" style="width:630px" maxlength="120"></html:text>
						</logic:equal>
						<logic:notEqual value="10338" name="xxdm" scope="session">
							<html:textarea property="sybz2" style="width:630px" rows="5"></html:textarea>
						</logic:notEqual>
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							<logic:equal value="10338" name="xxdm" scope="session">
							就业地意向3
						</logic:equal>
						<logic:notEqual value="10338" name="xxdm" scope="session">
							备注3：
						</logic:notEqual>
						</td>
						<td align="left" colspan="3">
						<logic:equal value="10338" name="xxdm" scope="session">
							<html:text property="sybz3" style="width:630px" maxlength="120"></html:text>
						</logic:equal>
						<logic:notEqual value="10338" name="xxdm" scope="session">
							<html:textarea property="sybz3" style="width:630px" rows="5" cols=""></html:textarea>
						</logic:notEqual>
						</td>
					</tr>
				</table>
				<div class="buttontool" align="center">
					<button class="button2" onclick="savejyxy()">
						提 交
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="button2" onclick="" type="reset">
						取 消 重 填
					</button>
					
				</div>
			</logic:notEmpty>
			<logic:notEmpty name="save">
				<logic:equal name="save" value="ok">
					<script>
					    alert("提交成功！");
					    </script>
				</logic:equal>
				<logic:equal name="save" value="no">
					<script>
    				alert("提交失败！请检查是否重复提交！");
   				 </script>
				</logic:equal>
			</logic:notEmpty>
			<logic:notEmpty name="iszc">
				<logic:equal name="iszc" value="iszc">
					<script>
					    alert("该用户已经存在了，不要重复提交同一个学生");
					    </script>
				</logic:equal>
			</logic:notEmpty>
		</html:form>
	</body>
</html>
