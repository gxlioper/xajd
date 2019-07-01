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
<head>
	<base target="_self" />
	<title><bean:message key="lable.title" />
	</title>
	<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
	<meta name="Copyright" content="正方软件 zfsoft" />
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
	<script language="javascript">
		function checkStuInfo(){
			var xh = document.getElementById('xh').value;
			if(xh == ""){
				alert("学号不能为空!");
				return ;
			}
			refreshForm("whlg_xszz.do?method=gjzxdksq&doType=add");
		}
		
		function yzdx(obj,str){
			document.getElementById(str).value = obj.value;
		}
		
		function toPrintOut(){//输出相应的打印页面
			document.forms[0].action = "";
			document.forms[0].submit();
		}
		
		function noMarryElemReadonly(flag){
			var str = 'po_xm-po_lxdh-po_gzdw-po_dwdz';
			var strSplit = str.split("-");
			if(flag){
				for(var i=0;i<strSplit.length;i++){
					document.getElementById(strSplit[i]).readOnly = true;
				}
			}else{
				for(var i=0;i<strSplit.length;i++){
					document.getElementById(strSplit[i]).readOnly = false;
				}
			}	
		}
	</script>
</head>
<body>
	<div class="title">
		<div class="title_img" id="title_m"> 
			当前所在位置：学生资助 - 国家助学贷款申请 - 国家助学贷款申请 
		</div>
	</div>
	<logic:equal name="sfksq" value="-1">
		<center>
			<p>
				现在不在申请时间内！！！
			</p>
		</center>
	</logic:equal><%--
	<logic:equal name="sfksq" value="1">
		--%><html:form action="/whlg_xszz.do?method=gjzxdksq" method="post">
			<input type="hidden" id="url" name="url"
				value="/whlg_xszz.do?method=gjzxdksq" />
			<input type="hidden" id="getStuInfo" name="getStuInfo"
				value="xm-xb-xymc-bjmc-sfzh-zzmmmc-nj" />	
			<table class="tbstyle" width="100%">
			<tbody>
				<tr>
					<td rowspan="13">
						借<br>款<br>人<br>基<br>本<br>资<br>料<br>
					</td>
					<logic:equal name="userOnLine" value="teacher" scope="session">
						<td align="center" style="width:12%">
							<font color="red">*</font>学号
						</td>
						<td align="left" >
							<html:text name='rs' property="xh" styleId="xh"
								onkeypress="autoFillStuInfo(event.keyCode,this)" readonly="true" />
							<button type="button" onclick="showTopWin('/xgxt/stu_info.do',750,550);"
								class="btn_01" id="buttonFindStu">
								选择
							</button>
						</td>
					</logic:equal>
					<logic:equal name="userOnLine" value="student" scope="session">
						<td align="center" style="width:12%">
							<font color="red">*</font>学号
						</td>
						<td align="left">
							<input type="text" id="xh" name="xh"
								value="<bean:write name='rs' property="xh" />" readonly="readonly"/>
						</td>
					</logic:equal>
					<td>
						<div align="center">
							政治面貌
						</div>
					</td>
					<td>
						<input type="text" id="zzmmmc" name="zzmmmc"				
							value="<bean:write name='rs' property="zzmmmc" />" readonly="readonly"/>
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							性别
						</div>
					</td>
					<td>
						<input type="text" id="xb" name="xb" 
							value="<bean:write name='rs' property="xb" />" readonly="readonly"/>
					</td>
					<td>
						<div align="center">
							身份证号
						</div>
					</td>
					<td>
						<input type="text" id="sfzh" name="sfzh" 
							value="<bean:write name='rs' property="sfzh" />" readonly="readonly"/>
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							毕业<bean:message key="lable.xsgzyxpzxy" />
						</div>
					</td>
					<td>
						<input type="text" id="xymc" name="xymc"
							value="<bean:write name='rs' property="xymc" />" readonly="readonly"/>
					</td>
					<td scope="row">
						<div align="center">
							家庭电话
						</div>
					</td>
					<td>
						<input type="text" id="jtdh" name="jtdh"
							value="<bean:write name='rs' property="jtdh" />">
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							姓名
						</div>
					</td>
					<td  scope="col">
						<input type="text" id="xm" name="xm"
							value="<bean:write name='rs' property="xm" />" readonly="readonly"/>
					</td>
					<td>
						<div align="center">
							毕业去向
						</div>
					</td>
					<td>
						<input type="text" id="byqx" name="byqx"	
							value="<bean:write name='rs' property="byqx" />">
					</td>
				</tr>
				<tr>
					<td scope="row">
						<div align="center">
							班级
						</div>
					</td>
					<td>
						<input type="text" id="bjmc" name="bjmc"
							value="<bean:write name='rs' property="bjmc" />" readonly="readonly"/>
					</td>
					<td>
						<div align="center">
							是否报考研究生
						</div>
					</td>
					<td>
						<logic:notEmpty name="rs" property="sfdkyjs">
							<logic:match value="是" name="rs" property="sfdkyjs">
								<input type="radio" name="sfdkyjs" value="是" checked>&nbsp;&nbsp;是
							    <input type="radio" name="sfdkyjs" value="否">&nbsp;&nbsp;否
							</logic:match>
							<logic:notMatch value="是" name="rs" property="sfdkyjs">
								<input type="radio" name="sfdkyjs" value="是">&nbsp;&nbsp;是
							    <input type="radio" name="sfdkyjs" value="否" checked>&nbsp;&nbsp;否
							</logic:notMatch>
						</logic:notEmpty>
						<logic:empty name="rs" property="sfdkyjs">
								<input type="radio" name="sfdkyjs" value="是">&nbsp;&nbsp;是
							    <input type="radio" name="sfdkyjs" value="否" >&nbsp;&nbsp;否
						</logic:empty>
					</td>
				</tr>
				<tr>
					<td align="center">
						家庭住址
					</td>
					<td colspan="3">
							<input type="text" id="jtzz" name="jtzz"	style="width:80%"
							value="<bean:write name='rs' property="jtzz" />">
					</td>
				</tr>
				<tr>
					<td align="center">
						工作单位
					</td>
					<td>
							<input type="text" id="gzdw" name="gzdw"	style="width:80%"
							value="<bean:write name='rs' property="gzdw" />">
					</td>
					<td align="center">
						单位电话
					</td>
					<td>
							<input type="text" id="dwdh" name="dwdh"
							value="<bean:write name='rs' property="dwdh" />">
					</td>
				</tr>
				<tr>
					<td align="center">
						单位地址
					</td>
					<td>
							<input type="text" id="dwdz" name="dwdz"	style="width:80%"
							value="<bean:write name='rs' property="dwdz" />">
					</td>
					<td align="center">
						单位邮编
					</td>
					<td>
							<input type="text" id="dwyb" name="dwyb"
							value="<bean:write name='rs' property="dwyb" />">
					</td>
				</tr>
				<tr>
					<td align="center">
						移动电话
					</td>
					<td>
							<input type="text" id="yddh" name="yddh" 
							value="<bean:write name='rs' property="yddh" />">
					</td>
					<td align="center">
						E-mail
					</td>
					<td>
						<input type="text" id="email" name="email"
							value="<bean:write name='rs' property="email" />">
					</td>
				</tr>
				<tr>
					<td align="center">
						学历 
					</td>
					<td>
						<html:select property="xl" styleId="xl" name="rs">
							<logic:equal value="研究生" property="xl" name="rs">
								<option value="本科">本科</option>
								<option value="研究生" selected="selected">研究生</option>
							</logic:equal>
							<logic:notEqual value="研究生" property="xl" name="rs">
								<option value="本科" selected="selected">本科</option>
								<option value="研究生">研究生</option>
							</logic:notEqual>		
						</html:select>
						
					</td>
					<td align="center">
						婚姻状况
					</td>
					<td>
						<logic:notEmpty name="rs" property="hyzk">
							<logic:match value="是" name="rs" property="hyzk">
								<input type="radio" name="hyzk" value="是" checked>&nbsp;&nbsp;已婚
							    <input type="radio" name="hyzk" value="否" onclick="noMarryElemReadonly(true)">&nbsp;&nbsp;未婚
							</logic:match>
							<logic:notMatch value="是" name="rs" property="hyzk">
								<input type="radio" name="hyzk" value="是">&nbsp;&nbsp;已婚
							    <input type="radio" name="hyzk" value="否" checked>&nbsp;&nbsp;未婚
							</logic:notMatch>
						</logic:notEmpty>
						<logic:empty name="rs" property="hyzk">
								<input type="radio" name="hyzk" value="是" onclick="noMarryElemReadonly(false)">&nbsp;&nbsp;已婚
							    <input type="radio" name="hyzk" value="否" onclick="noMarryElemReadonly(true)">&nbsp;&nbsp;未婚
						</logic:empty>
						<input type="hidden" id="hyzk" value="<bean:write name="rs" property="hyzk"/>"/>
						<script type="text/javascript">
							var is_marry = document.getElementById("hyzk").value;
							if(is_marry == '否'){
								noMarryElemReadonly(true);
							}
						</script>
					</td>
				</tr>
				<tr>		
						<td align="center">
							配偶姓名
						</td>
						<td>
							<input type="text" id="po_xm" name="po_xm"
								value="<bean:write name='rs' property="po_xm" />">
						</td>
						<td align="center">
							联系电话
						</td>
						<td >
							<input type="text" id="po_lxdh" name="po_lxdh"
								value="<bean:write name='rs' property="po_lxdh" />">
						</td>
					</tr>
					<tr>
						<td align="center">
							配偶工作单位
						</td>
						<td >
							<input type="text" id="po_gzdw" name="po_gzdw" style="width:80%"
								value="<bean:write name='rs' property="po_gzdw" />">
						</td>
						<td align="center">
							配偶单位地址
						</td>
						<td>
							<input type="text" id="po_dwdz" name="po_dwdz" style="width:80%"
								value="<bean:write name='rs' property="po_dwdz" />">
						</td>
					</tr>
				</tbody>	

				
				<tbody>
					<tr>
					<td rowspan="5">
						<div align="center">联<br>系<br>人<br>基<br>本<br>资<br>料</div>
					</td>	
					<td align="center">
							联系人姓名
					</td>
					<td >
						<input type="text" id="lxr_xm" name="lxr_xm" value="<bean:write name='rs' property="lxr_xm" />">
					</td>
					<td align="center">
						性别
					</td>
					<td>
						<logic:notEmpty name="rs" property="lxr_xb">
							<logic:match value="男" name="rs" property="lxr_xb">
								<input type="radio" name="lxr_xb" value="男" checked>&nbsp;&nbsp;男
							    <input type="radio" name="lxr_xb" value="女">&nbsp;&nbsp;女
							</logic:match>
							<logic:notMatch value="男" name="rs" property="lxr_xb">
								<input type="radio" name="lxr_xb" value="男">&nbsp;&nbsp;男
							    <input type="radio" name="lxr_xb" value="女" checked>&nbsp;&nbsp;女
							</logic:notMatch>
						</logic:notEmpty>
						<logic:empty name="rs" property="lxr_xb">
								<input type="radio" name="lxr_xb" value="男">&nbsp;&nbsp;男
							    <input type="radio" name="lxr_xb" value="女">&nbsp;&nbsp;女
						</logic:empty>
					</td>
				</tr>
				<tr>
					<td align="center">
						与借款人关系
					</td>
					<td >
						<input type="text" id="lxr_yjkrgx" name="lxr_yjkrgx" 
							value="<bean:write name='rs' property="lxr_yjkrgx" />">
						</td>
						<td align="center">
							出生日期
						</td>
						<td>
							<html:text name='rs' property="lxr_csrq" styleId="lxr_csrq" 
								onblur="dateFormatChg(this)" style="cursor:hand;"
								onclick="return showCalendar('lxr_csrq','y-mm-dd');" />	
						</td>
				</tr>
				<tr>
						<td align="center">
							职务
						</td>
						<td >
							<input type="text" id="lxr_zw" name="lxr_zw" 
								value="<bean:write name='rs' property="lxr_zw" />">
						</td>
						<td align="center">
							家庭联系电话
						</td>
						<td>
							<input type="text" id="lxr_jtlxdh" name="lxr_jtlxdh" 
								value="<bean:write name='rs' property="lxr_jtlxdh" />">
						</td>
					</tr>
				<tr>
						<td align="center">
							身份证号码
						</td>
						<td >
							<input type="text" id="lxr_sfzh" name="lxr_sfzh" 
								value="<bean:write name='rs' property="lxr_sfzh" />">
						</td>
						<td align="center">
							单位联系电话
						</td>
						<td>
							<input type="text" id="lxr_dwlxdh" name="lxr_dwlxdh" 
								value="<bean:write name='rs' property="lxr_dwlxdh" />">
						</td>
					</tr>	
					<tr>
						<td align="center">
							工作单位/地址
						</td>
						<td >
							<input type="text" id="lxr_gzdw_dz" name="lxr_gzdw_dz" style="width:80%"
								value="<bean:write name='rs' property="lxr_gzdw_dz" />">
						</td>
						<td align="center">
							单位邮编
						</td>
						<td>
							<input type="text" id="lxr_dwyb" name="lxr_dwyb" 
								value="<bean:write name='rs' property="lxr_dwyb" />">
						</td>
				</tr>	
				</tbody>
				<tbody>
					<tr>
						<td rowspan="7">
							<div align="center">借<br>款<br>人<br>家<br>庭<br>基<br>本<br>情<br>况</div>
						</td>
						<td align="center">
							家庭详细住址
						</td>
						<td colspan="3">
							<input type="text" id="jkr_jtxxzz" name="jkr_jtxxzz" style="width:80%"
								value="<bean:write name='rs' property="jkr_jtxxzz" />">
						</td>
					</tr>
					<tr>
						<td align="center">
							邮编
						</td>
						<td >
							<input type="text" id="jkr_yb" name="jkr_yb"
								value="<bean:write name='rs' property="jkr_yb" />">
						</td>
						<td align="center">
							电话
						</td>
						<td>
							<input type="text" id="jkr_dh" name="jkr_dh"
								value="<bean:write name='rs' property="jkr_dh" />">
						</td>
				</tr>
				<tr>
						<td align="center">
							父亲姓名
						</td>
						<td >
							<input type="text" id="jkr_fq_xm" name="jkr_fq_xm"
								value="<bean:write name='rs' property="jkr_fq_xm" />">
						</td>
						<td align="center">
							父亲职业
						</td>
						<td>
							<input type="text" id="jkr_fq_zy" name="jkr_fq_zy"
								value="<bean:write name='rs' property="jkr_fq_zy" />">
						</td>
				</tr>
				<tr>
						<td align="center">
							父亲身份证号码
						</td>
						<td >
							<input type="text" id="jkr_fq_sfzh" name="jkr_fq_sfzh"
								value="<bean:write name='rs' property="jkr_fq_sfzh" />">
						</td>
						<td align="center">
							母亲姓名
						</td>
						<td>
							<input type="text" id="jkr_mq_xm" name="jkr_mq_xm"
								value="<bean:write name='rs' property="jkr_mq_xm" />">
						</td>
				</tr>
				<tr>
						<td align="center">
							母亲职业
						</td>
						<td >
							<input type="text" id="jkr_mq_zy" name="jkr_mq_zy"
								value="<bean:write name='rs' property="jkr_mq_zy" />">
						</td>
						<td align="center">
							母亲姓名
						</td>
						<td>
							<input type="text" id="jkr_mq_xm" name="jkr_mq_xm"
								value="<bean:write name='rs' property="jkr_mq_xm" />">
						</td>
				</tr>
				<tr>
						<td align="center">
							母亲身份证号码
						</td>
						<td >
							<input type="text" id="jkr_mq_sfzh" name="jkr_mq_sfzh"
								value="<bean:write name='rs' property="jkr_mq_sfzh" />">
						</td>
						<td></td>
						<td></td>
				</tr>
				<tr>
					<td style="height:50px" colspan="4">
						<div style="width:90%">
							本人在此承诺提供的所有文件、资料和凭证等书面材料均为准确、真实、完整和有效的，否则自愿承担一切相关责任。
							如有变动，本人承诺在变动后一周内将变动资料邮寄至中国银行
							<input type="text" id="yhmc" name="yhmc" size="20"
								value="<bean:write name='rs' property="yhmc" />">
							分/支行。
							如提供虚假资料或未能及时寄送变更资料，贵行有权认定本人违约，并可按照《中国银行国家助学贷款借款合同》
							中的相关约定追究本人的违约责任。
						</div>
					</td>
				</tr>
				</tbody>
				<tbody>
					<tr>
						<td align="center">
							<div align="center">备<br>注</div>
						</td>
						<td colspan="4"><span><font color="blue">限500个字符</font></span>
							<html:textarea name='rs' property='bz' style="width:99%" styleId="bz" rows='6' />
						</td>
					</tr>
				</tbody>	
			</table>	
			<div class="buttontool" id="btn" style="position: absolute;width:100%">
				<logic:notEqual value="view" name="rs" property="doType">
				<button type="button" class="button2" onClick="checkStuInfo();" style="width:80px">
					提交申请
				</button>&nbsp;&nbsp;&nbsp;&nbsp;
				<button type="button" class="button2" onClick="toPrintOut();" style="width:80px">
					打 印
				</button>&nbsp;&nbsp;&nbsp;&nbsp;
				</logic:notEqual>
				<logic:notEqual value="student" scope="session" name="userOnLine">
					<button type="button" class="button2" onClick="Close();" style="width:80px">
						关闭
					</button>
				</logic:notEqual>
			</div>	
		</html:form><%--
	</logic:equal>
--%></body>
	<logic:present name="ok">
		<logic:match value="ok" name="ok">
			<script language="javascript">
	   			alert("保存成功！");
	   			if(window.dialogArguments){
	   				dialogArgumentsQueryChick();
	   				Close();
	   			}
	   		
	        </script>
		</logic:match>
		<logic:match value="no" name="ok">
			<script language="javascript">
	        	alert("保存失败！");
	        </script>
		</logic:match>
	</logic:present>
	<logic:present name="have">
		<logic:match value="have" name="have">
			<script language="javascript">
	         	alert("已通过审核，不能申请！");
	         </script>
		</logic:match>
	</logic:present>
</html:html>
