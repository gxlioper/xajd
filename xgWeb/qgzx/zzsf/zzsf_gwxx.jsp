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
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css" type="text/css" media="all" />
	<base target="_self">
	<script language="javascript" src="js/function.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/getOtherData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/cqkjFunc.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='dwr/util.js'></script>
<script type='text/javascript' src='/xgxt/dwr/interface/zzsfQgzx.js'></script>
	<script language="javascript" src="js/qgzxFunction.js"></script>
	<script type="text/javascript">
	function getDw(){
		var pk = document.getElementById("xmdm").value;
		if(pk!=null && pk!=""){
		getOtherData.getDwmc(pk,function(data){
			document.getElementById("sqdw").value = data[0];
			document.getElementById("fzr").value = data[1];
			document.getElementById("gwlxdh").value = data[2];
		});
		}		
	}
	function getFzr(){
		var pk = document.getElementById("xmdm").value;
		if(pk!=null && pk!=""){
			getOtherData.getDwmc(pk,function(data){
				if($("fzr")){
					document.getElementById("fzr").value = data[1];
				}
				if($("gwlxdh")){
					document.getElementById("gwlxdh").value = data[2];
				}
				if($("gzsj")){
					document.getElementById("gzsj").innerText = data[3];
				}
				if($("gznr")){
					document.getElementById("gznr").innerText = data[4];
				}	
				if($("ryyq")){
					document.getElementById("ryyq").innerText = data[5];
				}			
			});
			
		}		
	}
	
	//页面验证
	function checkFiledSuccess(){
		var sqly = document.getElementById('sqly').value;
		var bz = document.getElementById('bz').value;
		if(sqly != ''){
			if(sqly.replace(/[^\u0000-\u00ff]/g, "**").length > 250){	         
	          alert("申请理由不能大于250个字符");
	          return false;
	        }
		}
		
		if(bz != ''){
			if(bz.replace(/[^\u0000-\u00ff]/g, "**").length > 60){	         
	          alert("备注不能大于60个字符");
	          return false;
	        }
		}
		return true;
	}
	
	function save(){
		//判断必填字段是否为空
		var filed = ["xh", "xmdm", "lxdh", "sqly"];
		var type = document.getElementById('type').value;
		var xh = document.getElementById('xh').value;
		var gwdm = document.getElementById('xmdm').value;
		for(var i=0; i<filed.length; i++){
			if(document.getElementById(filed[i]).value == ''){
				alert('请将带\*号的项目填写完整！');
				return false;
			}
		}
		if(checkFiledSuccess()){//判断文本框字段长度
			//判断是否有记录存在
			if(type == 'add'){
				zzsfQgzx.checkXsgwExists(xh,gwdm,function(data){
					if(data){
						alert('您要添加的记录已经存在!');
						return false;
					}else{
						refreshForm('qgzxZzsf.do?method=saveXsgwxx');
					}
				});
			}else{
				refreshForm('qgzxZzsf.do?method=saveXsgwxx');
			}
		}
	}
	</script>
	<body>
		<html:form action="/qgzxZzsf.do" method="post">
			<input type="hidden" name="xxdm" id="xxdm" value="<bean:write name='xxdm'/>" />
			<input type="hidden" name="type" id="type" value="${type}" />
			<input type="hidden" id="disableEle" name="disableEle" value="xm-xb-xy-nj-zy-bj" />
			<input type="hidden" id="getStuInfo" name="getStuInfo" value="xm-xb-xymc-nj-zymc-bjmc-kh-zzmmmc" />
			<input type="hidden" id="url" name="url" value="/qgzxZzsf.do?method=addXsgwxx&type=add" />			
			<div class="title">
				<logic:equal name="do" value="no">
					<div class="title_img" id="title_m">
						当前位置：勤工助学 - 岗位申请 - 填写申请表
					</div>
				</logic:equal>
	
				<logic:equal name="do" value="yes">
					<div class="title_img" id="title_m">
						当前位置：勤工助学 - 岗位申请 - 修改申请表
					</div>
				</logic:equal>
			</div>
			<logic:notEmpty name="rs">				
				<logic:equal name="rs" property="stuExists" value="no">
					<script>
    					alert("您输入的学号无效!");
    				</script>
				</logic:equal>

				
				<table width="100%" id="rsT" class="tbstyle">
					<thead>
						<tr style="height:22px">
							<td colspan="4" align="center">
								<logic:equal name="do" value="no">
									<b>填写申请表</b>
								</logic:equal>
								<logic:equal name="do" value="yes">
									<b>修改申请表</b>
								</logic:equal>
							</td>
						</tr>
					</thead>
					<tr style="height:22px">
						<td align="right">
							<font color="red">*</font>学号：
						</td>
						<td align="left">
							<html:text name='rs' property="xh" styleId="xh"
								onkeypress="autoFillStuInfo(event.keyCode,this)" />
							<button type="button" onclick="showTopWin('/xgxt/stu_info.do',750,550);"
								class="btn_01" id="buttonFindStu">
								选择
							</button>
						</td>
						
						<td align="right">
							<font color="red">*</font>岗位名称：
						</td>
						<logic:equal value="modi" name="doType">
							<td align="left">
								<input type="hidden" id="isModi" name="isModi" value="<bean:write name="doType" scope="request"/>" />
								<input type="hidden" name="xmdmmodi" id="xmdmmodi" value="<bean:write name='rs' property='xmdm'/>">
								<html:select name="rs" property="xmdm" styleId="xmdm"
									style="width:150px" disabled="true" onchange="">
									<html:option value=""></html:option>
									<html:options collection="gwList" property="gwdmgwsbsj"
										labelProperty="gwdm" />
								</html:select>
							</td>
						</logic:equal>
						<logic:notEqual value="modi" name="doType">
								<td align="left">
									<html:select name="rs" property="xmdm" styleId="xmdm"
										style="width:150px" onchange="getFzr();">
										<html:option value=""></html:option>
										<html:options collection="gwList" property="gwdmgwsbsj"
											labelProperty="gwdm" />
									</html:select>
								</td>
						</logic:notEqual>
					</tr>

					<tr style="height:22px">
						<td align="right">
							姓名：
						</td>
						<td align="left">
							<bean:write name="rs" property="xm" />
						</td>
						<td align="right">
							年度：
						</td>
						<td align="left">
							<html:text name="rs" property="nd" readonly="true" styleId="nd"></html:text>
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							性别：
						</td>
						<td align="left">
							<bean:write name="rs" property="xb" />
						</td>
						<td align="right">
							学年：
						</td>
						<td align="left">
							<html:text name="rs" property="xn" readonly="true" styleId="xn"></html:text>
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							年级：
						</td>
						<td align="left">
							<bean:write name="rs" property="nj" />
						</td>
						<td align="right">
							学期：
						</td>
						<td align="left">
							<html:text name="rs" property="xq" readonly="true" styleId="xq"></html:text>
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							<bean:message key="lable.xsgzyxpzxy" />：
						</td>
						<td align="left">
							<bean:write name="rs" property="xymc" />
						</td>
						<td align="right">
							<font color="red">*</font>联系电话：
						</td>
						<td align="left">
							<html:text name='rs' property="lxdh" styleId="lxdh" onkeyup="value=value.replace(/[^(\d|\-)]/g,'') " maxlength="20"/>
						</td>
					</tr>
					<input type="hidden" name="sfwh" value="sfwh" />
						<tr>
							<td align="right">
								专业：
							</td>
							<td>
								<bean:write name="rs" property="zymc" />
							</td>
							<td align="right">
								卡号：
							</td>
							<td>
								<bean:write name="rs" property="kh" />
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
								政治面貌：
							</td>
							<td>
								<bean:write name="rs" property="zzmmmc" />
							</td>
						</tr>
						<tr>
							<td align="right">
								工作经历：
							</td>
							<td colspan="3">
								<html:textarea name='rs' property='gzjl' styleId="gzjl"
									style="width:99%" rows='5' />
							</td>
						</tr>
						<tr align="left" style="height:22px">
							<td align="right">
								<font color="red">*</font>申请理由：
							</td>
							<td colspan="3">
								<html:textarea name='rs' property='xssq' styleId="sqly"
									style="width:99%" rows='5' />
							</td>
						</tr>
						<tr align="left" style="height:22px">
							<td align="right">
								备注：
							</td>
							<td colspan="3">
								<html:textarea name='rs' property='bz' styleId="bz"
									style="width:99%" rows='5' />
							</td>
						</tr>
					</table>
					<div class="buttontool" align="center">
						<button type="button" class="button2"
							onclick="save();return false;">
							保 存 
						</button>
						<button type="button" class="button2"
							onclick="Close();return false;">
							关 闭
						</button>	
					</div>
			</logic:notEmpty>		
		</html:form>
		<logic:present name="result">
			<logic:equal name="result" value="true">
				<script>	
					alert('操作成功！');
					Close();
					window.dialogArguments.document.getElementById('search_go').click();	
				</script>
			</logic:equal>
			<logic:equal name="result" value="true">
				<script>	
					alert('操作成功！');
					Close();
					window.dialogArguments.document.getElementById('search_go').click();	
				</script>
			</logic:equal>
		</logic:present>
	</body>
</html>
