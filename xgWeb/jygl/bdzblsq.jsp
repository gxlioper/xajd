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
		<title><bean:message key="lable.title" />
		</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="Copyright" content="正方软件 zfsoft">
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css" type="text/css" media="all" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="js/calendar.css" type="text/css" media="all" />
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	
	<script language="javascript" src="js/stuinfoFunction.js"></script>
	<script language="javascript" src="js/commanFunction.js"></script>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/xgutil.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/bdzblgl.js'></script>
	<script>
		var num=0;
		var val1 ='';
		var val2 ='';
		function save(){
			var pkValue = val('xh');
			if(filedNotNull('xh-bdzlx-bdzkwdwmc-dajwdwmc','-')){
				refreshForm('bdzbl.do?method=saveBdzblsq');
			} else {
				alert ('请将带\*号的项目填写完整！');
				return false;
			}
		}
		
		function loadDz(bdzlx){
			ele('bm').style.display= 'none';
			ele('dajwdwbm').style.display = 'none';
			var xh = val('xh');
			if(xh!= null && xh != ""){
				if("回原籍报到证" == bdzlx){
					setVal('bdzkwdwmc',"");
					setVal('dajwdwmc',"");
					ele('bdzkwdwmc').readOnly = false;				
					ele('dajwdwmc').readOnly = false;
					bdzblgl.getSyd(xh,function(data){
						if(data.syd != null && data.syd != ""){
							setVal('bdzkwdwmc',(data.syd)+"人事局");
							setVal('dajwdwmc',(data.syd)+"人事局");
						}else{
							alert('生源地信息没有填写！');
						}
					});
					num++;
				}
				if("到用人单位报到证" == bdzlx){
					setVal('bdzkwdwmc',"");
					setVal('dajwdwmc',"");
					ele('bdzkwdwmc').readOnly = true;				
					ele('dajwdwmc').readOnly = true;
					bdzblgl.getBdzkwdwmc(xh,function(data){
						if(data != null){
							if(data.mc != null && data.mc !=""){
								setVal('bdzkwdwmc',data.mc);
								setVal('dajwdwmc',data.mc);
								alert('如果报到证的单位名称或档案接收单位名称不正确，请查询就业协议书信息审核中的内容！');
							}else{
								alert('请进入‘就业协议书信息审核’填写就业协议书内容！');
								return false;
							}
						}
					});
					num++;
				}
				if("个人挂靠人才市场报到证" == bdzlx){
					if(num!=0){
						setVal('bdzkwdwmc',val1);
						setVal('dajwdwmc',val2);
						num++;
					}else{
						val1 = val('bdzkwdwmc');
						val2 = val('dajwdwmc');
					}
					ele('bdzkwdwmc').readOnly = false;				
					ele('dajwdwmc').readOnly = false;
					ele('bm').style.display='';
					ele('dajwdwbm').style.display = '';				
				}
			}
		}
		
		function fileDa(value){
			var bdzlx = val('bdzlx');
			if("个人挂靠人才市场报到证" == bdzlx){
				setVal('dajwdwmc',value);				
			}
		}
		
	</script>
	<base target="_self">
	<body onload="if(ele('bdzlx')){if(val('bdzlx')=='个人挂靠人才市场报到证'){loadDz(val('bdzlx'))}}">
		<html:form action="/bdzbl.do">
			<input type="hidden" name="url" id="url" value="/bdzbl.do?method=bdzblsq">
			<input type="hidden" value="xh-xm-xb-nj-xymc-zymc-bjmc" id="getStuInfo" name="getStuInfo" />
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：就业管理 - 报到证办理管理 - 报到证办理申请
				</div>
			</div>
			<logic:present name="exists">
				<logic:equal value="true" name="exists">
					<br/><br/>
					<center>申请已经开始审核，暂时不能再次申请！</center>
				</logic:equal>
			</logic:present> 
			<logic:notEqual value="true" name="exists">
				<table width="100%" class="tbstyle">
					<thead align="center">
						<tr>
							<td align="center" colspan="4">
								报到证办理申请信息
							</td>
						</tr>
					</thead>

					<tr>
						<td align="right">
							<font color="red">*</font>学号：
						</td>
						<td>
							<logic:equal value="student" name="userType">
								<html:text name="rs" property="xh" styleId="xh" readonly="true"/>
							</logic:equal>
							<logic:notEqual value="student" name="userType">
								<html:text name="rs" property="xh" styleId="xh" onkeypress="if(event.keyCode == 13) autoFillStuInfo2(this);" />
								<button class="button2"
									onclick="showTopWin('/xgxt/stu_info.do',750,550);"
									style="width:20px" id="buttonFindStu">
									选择
								</button>
							</logic:notEqual>
							
						</td>
						<td>
							<div align="right">
								年度：
							</div>
						</td>
						<td>
							<html:text name="rs" property="nd" styleId="nd" readonly="true"/>
						</td>
					</tr>
					<tr>
						<td align="right">
							姓名：
						</td>
						<td>
							<bean:write name="rs" property="xm" />
						</td>
						<td>
							<div align="right">
								<font color="red">*</font>报到证类型：
							</div>
						</td>
						<td>
							<html:select property="bdzlx" styleId="bdzlx" name="rs" onchange="loadDz(this.value)">
								<html:option value=""></html:option>
								<html:option value="回原籍报到证">回原籍报到证</html:option>
								<html:option value="到用人单位报到证">到用人单位报到证</html:option>
								<html:option value="个人挂靠人才市场报到证">个人挂靠人才市场报到证</html:option>
							</html:select>
						</td>						
					</tr>
					<tr>
						<td align="right">
							性别：
						</td>
						<td>
							<bean:write name="rs" property="xb" />
						</td>
						
						<td>
							<div align="right">
								<font color="red">*</font>报到证开至：
							</div>
						</td>
						<td>
							<html:text name="rs" property="bdzkwdwmc" maxlength="200" onchange="fileDa(this.value)"/>
						</td>						
					</tr>
					<tr>
						<td align="right" nowrap="nowrap">
							年级：
						</td>
						<td>
							<bean:write name="rs" property="nj" />
						</td>
						
						<td align="right">
							<font color="red">*</font>档案寄至：
						</td>
						<td nowrap="nowrap">
							<html:text property="dajwdwmc" name="rs" maxlength="200"/>
							<span style="display:none" id='bm'>部门：</span>
							<html:text property="dajwdwbm" name="rs" maxlength="200" style="display:none"/>
						</td>						
					</tr>
					<tr>
						<td align="right">
							<bean:message key="lable.xsgzyxpzxy" />：
						</td>
						<td>
							<bean:write name="rs" property="xymc" />
						</td>
						
						<td align="right" >
							本人联系地址：
						</td>
						<td> 
							<html:text property="lxdz" name="rs" maxlength="200"/>
						</td>					
					</tr>
					<tr>
						<td align="right">
							专业：
						</td>
						<td>
							<bean:write name="rs" property="zymc" />
						</td>
						<td align="right" nowrap="nowrap">
							本人联系邮编：
						</td>
						<td>
							<html:text property="lxyb" name="rs" maxlength="6" onkeyup="value=value.replace(/[^\d]/g,'') "/>
						</td>						
					</tr>
					<tr>
						<td align="right">
							班级：
						</td>
						<td>
							<bean:write name="rs" property="bjmc" />
						</td>
						<td align="right">
							本人长期联系方式：
						</td>
						<td>
							<html:text property="lxfs" name="rs" maxlength="150"/>
						</td>	
					</tr>
					<tr>		
						<td align="right">
							学制：
						</td>
						<td>
							<bean:write name="rs" property="xz" />
						</td>				
						<td align="right">
							手机号码：
						</td>
						<td>
							<html:text property="sjhm" name="rs" maxlength="11" onkeyup="value=value.replace(/[^\d]/g,'') "/>
						</td>						
					</tr>
				</table>
				
				<center>
					<div class="buttontool" id="btn">
						<button class="button2"
							onclick="save();return false;"
							style="width:80px">
							保 存
						</button>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<button class="button2" onclick="window.close();return false;"
							style="width:80px">
							关 闭
						</button>
					</div>
				</center>
				
			</logic:notEqual>
			 <logic:notEmpty name="result">
				<logic:equal value="true" name="result">
					<script>
					alert("操作成功！");
					Close();
					if(window.dialogArguments){
						window.dialogArguments.document.getElementById('search_go').click();
					}		
				</script>
				</logic:equal>
				<logic:equal value="false" name="result">
					<script>
					alert("操作失败！");
					Close();
				</script>
				</logic:equal>
			</logic:notEmpty>
		</html:form>
	</body>
</html>
