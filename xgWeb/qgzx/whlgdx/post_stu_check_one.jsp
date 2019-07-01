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
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<body onload="checkWinType();document.all('buttonClose').focus()">
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/studetailFunction.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getStuDetails.js'></script>
		<script type='text/javascript'src='/xgxt/dwr/interface/cqkjFunc.js'></script>
		<script type="text/javascript">
		function commitAuditing(){
			var xb = document.getElementById("xb").value;
			var nj = document.getElementById("nj").value;
			var zymc = document.getElementById("zymc").value;
			var rzyq_xb = document.getElementById("rzyq_xb").value;
			var rzyq_nj = document.getElementById("rzyq_nj").value;
			var rzyq_zy = document.getElementById("rzyq_zy").value;
			var yesNo = document.getElementById("yesNo").value;
			var userName = document.getElementById("userName").value;
			var msg = "";
			var sfpks = document.getElementById("sfpks").value;
			if(sfpks=="否" || sfpks=="不是"){
				alert("不是贫困生，不能通过！");
				return false;
			}
			if(rzyq_xb!=null && rzyq_xb!=""){
				if(rzyq_xb!=xb){
					msg += " 性别";
				}
			}
			if(rzyq_nj!=null && rzyq_nj!=""){
				if(rzyq_nj!=nj){
					msg += " 年级"
				}
			}
			if(rzyq_zy!=null && rzyq_zy!=""){
				if(rzyq_zy!=zymc){
					msg += " 专业";
				}
			}			
			if(yesNo=="通过"){
			if(msg!="" && yesNo=="通过"){
					if(cofirm(msg+"与任职要求不符，确定要通过吗？")){
						var pkString = document.getElementById("pkVal").value + "!!SplitOneSplit!!";
						cqkjFunc.checkPostStuAudi(pkString,userName,function(data){
						if(data!=null && data.length>0){
							var message = "";
							for (i=0; i<data.length; i++){
								if(data[i]!=null && data[i]!=""){
									message = message+"\n" + data[i];
								}
							}
							if(message!=""){
								alert("无法审核通过："+message);
								return false;
							}else{
								refreshForm('/xgxt/postStuChkOne.do?act=save');
							}						
						}
					});
					}
			}
			}
			refreshForm('/xgxt/postStuChkOne.do?act=save');
		}
		</script>
		<html:form action="/data_search" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：勤工助学 - 审核 - 学生申请审核 - 单个审核
				</div>
			</div>
			<input type="hidden" id="userName" name="userName"
				value="<bean:write name="userName"/>" />
			<input type="hidden" name="pkVal"
				value="<bean:write name="rs" property="xh||gwdm||sqsj"/>" />
			<input type="hidden" name="gwdm"
				value="<bean:write name="rs" property="gwdm"/>" />
			<input type="hidden" name="xh"
				value="<bean:write name="rs" property="xh"/>" />
			<table width="100%" align="center" class="tbstyle">
				<thead>
					<tr style="height:22px">
						<td colspan="4" align="center">
							单个学生申请审核
						</td>
					</tr>
				</thead>
				<tr style="height:22px">
					<td width="24%" align="right">
						学号：
					</td>
					<td width="20%" align="left">
						<bean:write name="rs" property="xh" />
						<input type="hidden" id="xh" value="<bean:write name="rs" property="xh" />" />
						<input type="hidden" id="xxdm" value="<bean:write name="xxdm"/>" />
					</td>
					<td width="31%" align="right">
						年度：
					</td>
					<td width="25%" align="left">
						<bean:write name="rs" property="nd" />
					</td>
				</tr>

				<tr style="height:22px">
					<td align="right">
						姓名：
					</td>
					<td align="left">
						<bean:write name="rs" property="xm" />
					</td>
					<td align="right">
						学年：
					</td>
					<td align="left">
						<bean:write name="rs" property="xn" />
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						性别：
					</td>
					<td align="left">
						<bean:write name="rs" property="xb" />
						<input type="hidden" id="xb" name="xb" value="<bean:write name="rs" property="xb" />"/>
					</td>
					<td align="right">
						岗位名称：
					</td>
					<td align="left">
						<bean:write name="rs" property="gwdm" />
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						年级：
					</td>
					<td align="left">
						<bean:write name="rs" property="nj" />
						<input type="hidden" id="nj" name="nj" value="<bean:write name="rs" property="nj" />"/>
					</td>
					<td align="right">
						申请时间：
					</td>
					<td align="left">
						<bean:write name="rs" property="sqsj" />
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						<bean:message key="lable.xsgzyxpzxy" />：
					</td>
					<td align="left">
						<bean:write name="rs" property="xymc" />
					</td>
					<td align="right">任职要求（年级）：
					</td>
					<td align="left">
						<bean:write name="rs" property="rzyq_nj" />
						<input type="hidden" id="rzyq_nj" name="rzyq_nj" value="<bean:write name="rs" property="rzyq_nj" />"/>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						专业：
					</td>
					<td align="left">
						<bean:write name="rs" property="zymc" />
						<input type="hidden" id="zymc" name="zymc" value="<bean:write name="rs" property="zymc" />"/>
					</td>
					<td align="right">任职要求（年级性别）：
					</td>
					<td align="left">
						<bean:write name="rs" property="rzyq_xb" />
						<input type="hidden" id="rzyq_xb" name="rzyq_xb" value="<bean:write name="rs" property="rzyq_xb" />"/>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						班级：
					</td>
					<td align="left">
						<bean:write name="rs" property="bjmc" />
					</td>
					<td align="right">任职要求（专业）：
					</td>
					<td>
						<bean:write name="rs" property="rzyq_zy" />
						<input type="hidden" id="rzyq_zy" name="rzyq_zy" value="<bean:write name="rs" property="rzyq_zy" />"/>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						籍贯：
					</td>
					<td align="left">
						<bean:write name="rs" property="jg" />
					</td>
				    <td align="left"><div align="right">任职要求（外语要求）：</div></td>
				    <td align="left">
            			<bean:write name="rs" property="rzyq_wyyq" />            			
				    </td>
				</tr>
				<tr style="height:22px">
				  <td align="right">政治面貌：</td>
				  <td align="left">
				  	<bean:write name='rs' property="zzmmmc" />
				  </td>
			      <td align="left"><div align="right">任职要求（工作技能）：</div></td>
			      <td align="left">
			      	<bean:write name="rs" property="rzyq_gzjn" />
			      </td>
			  </tr>
				<tr style="height:22px">
					<td align="right">
						是否困难生：</td>
					<td align="left">
						<bean:write name="rs" property="sfpks" />
						<input type="hidden" id="sfpks" name="sfpks" value="<bean:write name="rs" property="sfpks" />"/>
					</td>
				    <td align="left"><div align="right">任职要求（其它）：</div></td>
				    <td align="left">
				    	<bean:write name="rs" property="rzyq_qt" />
				    </td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						联系电话：</td>
					<td align="left" colspan="3">
						<bean:write name="rs" property="lxdh" />
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						申请理由：
					</td>
					<td align="left" colspan="3">
						<bean:write name="rs" property="sqly" />
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						备注：
					</td>
					<td align="left" colspan="3">
						<bean:write name="rs" property="bz" />
					</td>
				</tr>
				<tr>
					<td align="right">
						审核：
					</td>
					<td align="left" colspan="3">
						<html:select name="rs" property="yesNo" styleId="yesNo">
							<html:options collection="chkList" property="en" labelProperty="cn" />
						</html:select>
					</td>
				</tr>
			</table>
			<div class="buttontool" align="center">
				<button type="button" class="button2"
					onclick="commitAuditing();"
					style="width:80px" id="buttonSave">
					保 存
				</button>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button type="button" class="button2" onclick="Close();return false;" style="width:80px"
					id="buttonClose">
					关 闭
				</button>
			</div>
		</html:form>
		<logic:notEmpty name="flag">
			<logic:equal value="true" name="flag">
				<script language="javascript">
				alert("操作成功！");
				Close();
				window.dialogArguments.document.getElementById('search_go').click();
				</script>
			</logic:equal>
			<logic:notEqual value="true" name="flag">
				<script language="javascript">
					alert("操作失败！");
				</script>
			</logic:notEqual>
		</logic:notEmpty>
		<logic:equal value="view" name="result">
			<logic:present name="hasSQ">
				<logic:match value="have" name="hasSQ">
					<script language="javascript">
	         	alert("该学生已有申请通过审核！");
	         	Close();
				window.dialogArguments.document.getElementById('search_go').click();
	         	</script>
				</logic:match>
				<logic:match value="notHave" name="hasSQ">
					<script language="javascript">
	         	alert("操作成功！");
				Close();
				window.dialogArguments.document.getElementById('search_go').click();
	         	</script>
				</logic:match>
			</logic:present>
		</logic:equal>
		<logic:equal value="full" name="result">
			<script>
				alert("人数已满！");
			</script>
		</logic:equal>
		<logic:equal value="knsfull" name="result">
			<script>
				alert("困难生人数已满！");
			</script>
		</logic:equal>
	</body>
</html>
