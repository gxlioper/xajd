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
		<title><bean:message key="lable.title" /></title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control" http-equiv="no-cache" />
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

	<script language="javascript" src="js/function.js"></script>
	<script language="javascript">
	     function refreshtheweb(){   
	        document.forms[0].action = "JyxyDataSearch.do";
		 	document.forms[0].submit();
	     }
	     function querrytheinfo(){   
	        document.forms[0].action = "JyxyDataSearch.do?act=go";
		 	document.forms[0].submit();
	     }
	     
	     function viewMoreinfo(doType){
		    var url ="JyglJyxyViewMoreinfo.do?doType=view&pkValue=";
		    var pkValue ="";
		
		    if (doType == "view"){
		       pkValue = $("pk").value;
		       url += pkValue;
		       showTopWin(url, 680, 400);
		 }
		}
		
		 function deltheinfo(){  
		     var pkValue = $("pk").value; 
		     if (curr_row == null) {
			      alert("请选择要删除的数据！\n（单击相应的行）");
			      return false;
		    } else {
			   if (confirm("确定要删除该行数据吗？")) {
                      document.forms[0].action = "JyxyDataSearch.do?doType2=del&act=go&pkValue="+pkValue;
		 	          document.forms[0].submit();
				return true;
			} else {
				return false;
			}   
			}
       
	     }
	     
	     function jyxyupdate(doType){
		    var url ="savejyxyupdate.do?pkValue=";
		    var pkValue ="";
		
		 if (doType == "update"){
		    if (curr_row == null) {
			alert("请选择要修改的数据！\n（单击相应的行）");
			return false;
			 } else {
			if (confirm("确定要修改该行数据吗？")) {
				 pkValue = $("pk").value;
		         url += pkValue;
		         showTopWin(url, 760, 480);
				return true;
			} else {
				return false;
			}
		   }
	      }		
		}

	</script>
	<body>
		<html:form action="/JyxyDataSearch" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：就业管理 - 就业协议方案 - 就业协议查询
				</div>
			</div>
			<fieldset>
				<legend>
					查 询
				</legend>

				<table width="100%" class="tbstyle">
					<thead>
						<tr style="cursor:hand">
							<td>
								学号：
								<input type="text" name="xh"
									value="<bean:write name="rs1" property="xh"/>" />
								&nbsp;&nbsp;&nbsp;&nbsp;姓名：
								<input type="text" name="xm"
									value="<bean:write name="rs1" property="xm"/>"
									style="width:95px" />
								&nbsp;&nbsp;&nbsp;&nbsp;性别：
								<html:select name="rs1" property="xb" style="width:60px">
									<html:option value=""></html:option>
									<html:option value="男">
										男
									</html:option>
									<html:option value="女">
										女
									</html:option>
								</html:select>
								&nbsp;&nbsp;&nbsp;&nbsp;年级：
								<html:select name="rs1" property="nj" style="width:150px"
									onchange="refreshtheweb();">
									<html:option value=""></html:option>
									<html:options collection="njList" property="nj"
										labelProperty="nj" />
								</html:select>
							</td>
							<td width="10" rowspan="2" align="center" valign="middle">
								<button class="button2" style="height:40px" id="query_go"
									onclick="querrytheinfo();">
									查询
								</button>
							</td>
						</tr>
						<tr>
							<td>
								<logic:equal name="who" value="xx">
								<bean:message key="lable.xsgzyxpzxy" />：
								<html:select name="rs1" property="xydm" style="width:150px"
										onchange="refreshtheweb();">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select>
								</logic:equal>
								<logic:equal name="who" value="xy">
							    <bean:message key="lable.xsgzyxpzxy" />：
						        <html:select name="rs1" property="xydm"
										style="width:150px" disabled="true"
										onchange="refreshtheweb();">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select>
									<html:hidden name="rs1" property="xydm" />
								</logic:equal>
								&nbsp;&nbsp;&nbsp; 专业：
								<html:select name="rs1" property="zydm" style="width:150px"
									onchange="refreshtheweb();">
									<html:option value=""></html:option>
									<html:options collection="zyList" property="zydm"
										labelProperty="zymc" />
								</html:select>
								&nbsp;&nbsp;&nbsp; 班级：
								<html:select name="rs1" property="bjdm" style="width:150px">
									<html:option value=""></html:option>
									<html:options collection="bjList" property="bjdm"
										labelProperty="bjmc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<font color="blue">提示：双击一行查看详细，单击表头可以排序。</font>
							</td>
						</tr>
					</thead>
				</table>
			</fieldset>
			<logic:empty name="rs">
				<br />
				<br />
				<p align="center">
					未找到任何记录！
				</p>
			</logic:empty>
			<logic:notEmpty name="rs">
				<fieldset>
					<legend>
						记录数：
						<bean:write name="rsNum" />
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<font color="blue">提示：双击一行可以查看详细信息；单击表头可以排序</font>
					</legend>
					<table width="100%" id="rsTable" class="tbstyle">
						<thead>
							<tr align="center" style="cursor:hand">
								<logic:iterate id="tit" name="topTr" offset="0">
									<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)" nowrap>
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<logic:iterate name="rs" id="s">
							<tr onclick="rowOnClick(this)" style="cursor:hand"
								ondblclick="viewMoreinfo('view')" align="center">
								<td>
								<input type="hidden" name="pk"
									value="<bean:write name="s" property="xh" />" />
									<bean:write name="s" property="行号" />
								</td>
								<td>
									<bean:write name="s" property="xh" />
								</td>
								<td>
									<bean:write name="s" property="xm" />
								</td>
								<td>
									<bean:write name="s" property="xb" />
								</td>
								<td>
									<bean:write name="s" property="xymc" />
								</td>
								<td>
									<bean:write name="s" property="zymc" />
								</td>
								<td>
									<bean:write name="s" property="bjmc" />
								</td>
								<td>
									<bean:write name="s" property="fbsj" />
								</td>
							</tr>
						</logic:iterate>
					</table>
				</fieldset>
			</logic:notEmpty>



			<div class="buttontool" id="btn"
				style="position: absolute;left:0px;top:100px" width="100%">
				<button class="button2" onclick="jyxyupdate('update')"
					style="width:80px">
					修 改
				</button>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button class="button2" onclick="deltheinfo();" style="width:80px">
					删 除
				</button>
			</div>
		</html:form>
		<script type="text/javascript" src="js/bottomButton.js"></script>
		<logic:notEmpty name="delete">
			<logic:equal name="delete" value="ok">
				<script>
                      alert("删除成功!");
                    </script>
			</logic:equal>
			<logic:equal name="delete" value="no">
				<script>
                      alert("删除失败");
                    </script>
			</logic:equal>
		</logic:notEmpty>
	</body>
</html>

