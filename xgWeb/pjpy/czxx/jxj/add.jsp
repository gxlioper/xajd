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
		<base target="_self" />
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
		response.setHeader("Pragma","No-cache");
		response.setHeader("Cache-Control","no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript" src="js/function.js"></script>
	<script type="text/javascript" src="pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/CzxxJxjDao.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
	<script type="text/javascript">
    	function check() {
    		var xh = document.getElementById('xh').value;
    		var xn = document.getElementById('xn').value;
			var xq = document.getElementById('xq').value;
			var jxjdm = document.getElementById('jxjdm').value;
			
			if (xh =='' || xn=='' || jxjdm=='') {
				alert("带*号字段为必填项！");
				return false;
			}
			
			//检测学生申请奖学金条件是否满足
			CzxxJxjDao.checkJxjSqtj(xh,xn,xq,jxjdm,'jxj', function (data) {
				if (data != null) {
					if (data[0] == 'true') {//违纪处分检测失败
						alert("申请失败，该生在评奖学年学期内出现违纪处分记录，不符合奖学金的申请条件！");
						return false;
					} else if (data[1] == 'true') {//综测排名检测失败
						alert("申请失败，" + data[2]);
						return false;
					} else {
						saveinfo('pjpy_czxx_jxjAdd.do?act=save','xh-xm-jxjdm');
					}
				}
			});
    	}
     
    </script>
	<body >
		<html:form action="/czxxPjpyJxj" method="post">
			<input type="hidden" id="disableEle" name="disableEle"
					value="xm-xb-xy-nj-zy-bj" />
			<input type="hidden" id="readonlyEle" name="readonlyEle" value="" />
			<input type="hidden" id="getStuInfo" name="getStuInfo"
					value="xm-xb-xymc-nj-zymc-bjmc" />
			<input type="hidden" id="url" name="url" value="/pjpy_czxx_jxjAdd.do" />
			<input type="hidden" name="save_xn" id="xn" value="${xn }"/>
			<input type="hidden" name="save_xq" id="xq" value="${xq }"/>
			<input type="hidden" name="save_nd" id="nd" value="${nd }"/>
			<input type="hidden" name="message" id="message" value="${message }"/>
						<input type="hidden" name="typ" id="act" value="${typ }"/>
		<div class="title"> 
				<div class="title_img" id="title_m">
					当前所在位置：评奖评优 - 奖学金申请 - 申请结果查询
				</div>
			</div>
			<table width="100%" class="tbstyle">
				<thead>
					<tr align="center">
						<td height="22" colspan="4">
							填写申请表
						</td>
					</tr>
				</thead>
				<tr>
					<td height="22" align="right" style="width:25%">
						<font color="red">*</font>学号：
					</td>
					<td height="22" align="left" style="width:25%">
					<logic:equal value="student" name="userType">
						<html:text name='rs' property="save_xh" styleId="xh"
							disabled="true" />
					</logic:equal>
					<logic:notEqual value="student" name="userType">
							<html:text name='rs' property="save_xh" styleId="xh"
							onkeypress="autoFillStuInfo(event.keyCode,this);checkXhExists('xm-xb-nj-xymc-zymc-bjmc')" />
						<button type="button" onclick="showTopWin('/xgxt/stu_info.do',750,550);"
							class="btn_01" id="buttonFindStu">
							选择
						</button>
					</logic:notEqual>
					</td>
					<td height="22" align="right">
						<bean:message key="lable.xsgzyxpzxy" />：
					</td>
					<td height="22" align="left">
						<html:text property="xymc" styleId="xymc" name="rs" disabled="true"></html:text>
					</td>
					
				</tr>
				<tr>
					<td height="22" align="right">
						<font color="red">*</font>姓名：
					</td>
					<td height="22" align="left">
						<html:text property="xm" styleId="xm" name="rs" disabled="true"></html:text>
					</td>
					<td height="22" align="right">
						专业：
					</td>
					<td height="22" align="left">
						<html:text property="zymc" styleId="zymc" name="rs" disabled="true"></html:text>
					</td>
					
				</tr>
				<tr>
				
					<td height="22" align="right">
						年级：
					</td>
					<td height="22" align="left">
						<html:text property="nj" styleId="nj" name="rs" disabled="true"></html:text>
					</td>
				<td height="22" align="right" style="width:25%">
						学年：
					</td>
					<td height="22" align="left" style="width:25%">
						${xn }
					</td>	
				</tr>
				<tr>
					
					<td height="22" align="right">
						班级：
					</td>
					<td height="22" align="left">
						<html:text property="bjmc" styleId="bjmc" name="rs" disabled="true"></html:text>
					</td>
					<td height="22" align="right">
						学期：
					</td>
					<td height="22" align="left">
						${xqmc }
					</td>
				</tr>
				<tr>
					<td height="22" align="right">
						德育成绩：
					</td>
					<td height="22" align="left">
						${rs.dcj }
					</td>
					<td height="22" align="right">
						智育成绩：
					</td>
					<td height="22" align="left">
						${rs.zcj}
					</td>
				</tr>
				<tr>
					<td height="22" align="right">
						体育成绩：
					</td>
					<td height="22" align="left">
						${rs.tcj }
					</td>
					<td height="22" align="right">
						综测总成绩：
					</td>
					<td height="22" align="left">
						${rs.zxf}
					</td>
				</tr>
				<tr>
					<td height="22" align="right">
						德育成绩班级排名：
					</td>
					<td height="22" align="left">
						${rs.dpm }
					</td>
					<td height="22" align="right">
						智育成绩班级排名：
					</td>
					<td height="22" align="left">
						${rs.zpm}
					</td>
				</tr>
				<tr>
					<td height="22" align="right">
						体育成绩班级排名：
					</td>
					<td height="22" align="left">
						${rs.tpm }
					</td>
					<td height="22" align="right">
						综测总成绩班级排名：
					</td>
					<td height="22" align="left">
						${rs.zfpm}
					</td>
				</tr>
				
				<tr>
					<td height="22" align="right">
					<font color="red">*</font>奖学金名称：
					</td>
					<td height="22" align="left">
						<html:select property="save_jxjdm" styleId="jxjdm">
							<html:options collection="jxjList" property="jxjdm" labelProperty="jxjmc"/>
						</html:select>
					</td>
					<td height="22" align="right">
						
					</td>
					<td height="22" align="left">
						
					</td>
				</tr>
				
				<tr>
				<td align="right" colspan="4">
					<table width="100%" border="1" class="tbstyle">
						<tr>
							<td bgcolor="#CCCCCC">
								<div id="main4" style="color:blue;cursor:hand"
									onclick="document.all.child4.style.display=(document.all.child4.style.display =='none')?'':'none';">
									<div align="center" class="style1 style3">
										<strong>违纪处分信息</strong>
									</div>
								</div>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td colspan="4">
					<div id="child4" style="display:none">
						<table width="100%" border="1" align="center" class="tbstyle">
						<thead>
							<tr>
								<td align="center" width="80px">
									学年
								</td>
								<td align="center" width="80px">
									学期
								</td>
								<td align="center" width="110px">
									处分类别
								</td>
								<td align="center" width="110px">
									处分原因
								</td>
								<td align="center" width="80px">
									处分时间
								</td>
								<td align="center" width="110px">
									处分文号
								</td>
							</tr>
							</thead>
							<logic:empty name="cfList">
								<tr>
								<td align="center" colspan="6">
									暂无记录！
								</td>
								</tr>
							</logic:empty>
							<logic:notEmpty name="cfList">
								<logic:iterate id="s" name="cfList" >
									<tr onclick="rowOnClick(this);" style="cursor:hand;" >
										<logic:iterate id="v" name="s" >
											<td align="center">
												<bean:write name="v" />
											</td>
										</logic:iterate>
									</tr>
								</logic:iterate>
							</logic:notEmpty>
						</table>
					</div>
				</td>
			</tr>
			
			<tr>
				<td align="right" colspan="4">
					<table width="100%" border="1" class="tbstyle">
						<tr>
							<td bgcolor="#CCCCCC">
								<div id="main5" style="color:blue;cursor:hand"
									onclick="document.all.child5.style.display=(document.all.child5.style.display =='none')?'':'none';">
									<div align="center" class="style1 style3">
										<strong>学生课程成绩信息</strong>
									</div>
								</div>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td colspan="4">
					<div id="child5" style="display:none">
						<table width="100%" border="1" align="center" class="tbstyle">
						<thead>
							<tr>
								<td align="center" width="80px">
									学年
								</td>
								<td align="center" width="80px">
									学期
								</td>
								<td align="center" width="110px">
									课程名称
								</td>
								<td align="center" width="110px">
									成绩
								</td>
								<td align="center" width="80px">
									课程性质
								</td>
								<td align="center" width="110px">
									补考成绩
								</td>
								<td align="center" width="110px">
									重修成绩
								</td>
							</tr>
							</thead>
							<logic:empty name="cjList">
								<tr>
								<td align="center" colspan="7">
									暂无记录！
								</td>
								</tr>
							</logic:empty>
							<logic:notEmpty name="cjList">
								<logic:iterate id="s" name="cjList" >
									<tr onclick="rowOnClick(this);" style="cursor:hand;" >
										<logic:iterate id="v" name="s" >
											<td align="center">
												<bean:write name="v" />
											</td>
										</logic:iterate>
									</tr>
								</logic:iterate>
							</logic:notEmpty>
						</table>
					</div>
				</td>
			</tr>
				
				<tr>
					<td height="22" align="right">
						主要事迹及获奖情况：
						<br/>
						<font color="red">1000字以内&nbsp;&nbsp;&nbsp;&nbsp;</font>
					</td>
					<td height="22" align="left" colspan="3">
						<html:textarea property="save_zysj" rows="5" styleId="zysj" rows="6" style="width:540px" onkeyup="checkLen(this,1000)">
						</html:textarea>
					</td>
					
				</tr>
			</table>
			<br />
			<div class="buttontool" id="button" align="center">
			<button type="button" class="button2" onclick="check();" style="width:80px"
					id="btn_save">
					保 存
				</button>
				<logic:notEqual value="add" name="typ">
				&nbsp;&nbsp;
				<button type="button" class="button2" onclick="window.close();return false;" style="width:80px"
					id="buttonClose">
					关 闭
				</button>
				</logic:notEqual>
			</div>
		</html:form>
		<logic:present name="result">
			<logic:equal value="true" name="result">
				<script>
					alert("操作成功!");
					Close();
					if(window.dialogArguments && window.dialogArguments.document.all("search_go")){
						window.dialogArguments.document.getElementById('search_go').click();	
					}
					
				</script>
			</logic:equal>
			<logic:equal value="false" name="result">
				<script>
					alert("操作失败!该生该奖学金已经申请,相关部门审核中,不能重复申请!");
				</script>
			</logic:equal>
		</logic:present>
	</body>
</html>
