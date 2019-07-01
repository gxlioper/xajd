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
	<script language="javascript">
</script>
	<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
	<body>
		<script language="javascript" src="js/function.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getSztzData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
	
		<html:form action="/csmz_sztz.do?method=tzxm_sh" method="post">
	    <input type="hidden" name="pkValue" id="pkValue" value="<bean:write name="pkValue" scope="request"/>">
	    <input type="hidden" name="userType" id="userType" value="<bean:write name="userType" scope="request"/>">
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：素质拓展 - 拓展项目审核 - 审核 - 单个审核
				</div>
			</div>
			<table width="100%" align="center" class="tbstyle">
				<thead>
					<tr style="height:22px">
						<td colspan="4" align="center">
							单个拓展项目申报审核
						</td>
					</tr>
				</thead>
				<tr style="height:22px">
					<td width="15%" align="right" nowrap >
						学年：
				    </td>
					<td align="left">
					<bean:write name="rs" property="xn"/>
					</td>
					<td align="right">
						学期：
					</td>
					<td align="left">
					<bean:write name="rs" property="xqmc"/>	
					</td>
				</tr>
				<tr style="height:22px">
					<td width="15%" align="right" nowrap >
						项目名称：
				    </td>
					<td align="left">
					<bean:write name="rs" property="xmmc"/>
					</td>
					<td align="right">
						所属科目：
					</td>
					<td align="left">
					<bean:write name="rs" property="kmm"/>	
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						项目级别：
					</td>
					<td align="left">
					<bean:write name="rs" property="xmjb"/>
					</td>
					<td align="right">
						申请部门：
					</td>
					<td align="left">
					<bean:write name="rs" property="bmmc"/>
					</td>
				 </tr>	
				 <tr style="height:22px">
					<td align="right">
						主办时间：
					</td>
					<td align="left">
					<bean:write name="rs" property="zbsj"/>
					</td>
					<td align="right">
						组织单位：
					</td>
					<td align="left">
					<bean:write name="rs" property="zzdw"/>
					</td>
				 </tr>			
			     <tr style="height:22px">
					<td align="right">
					项目申报人：
					</td>
					<td align="left">
					${rs.xmsbr}	
					</td>
					<td align="right">
						<font color="red"><bean:write name="shType" scope="request"/>审核：</font>
					</td>
					<td align="left">
					<logic:equal value="true" name="shLimit">
					   <bean:write name="shClin"/>
					</logic:equal>					
					<logic:equal value="false" name="shLimit">					
						<html:select name="rs" property="yesNo">
							<html:options collection="chkList" property="en"
								labelProperty="cn" />
						</html:select>					
					</logic:equal>	
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						项目描述：
					</td>
					<td align="left"colspan="3">
						<bean:write name="rs" property="xmms"/>
					</td>
				</tr>
     		</table>
     		<table width="100%" align="center"  class="tbstyle">
					<thead>
						<tr>						    
							<td align="center" >
								项目奖项
								<div  align="right" ><button id="xxbut" class="button2" onclick="showHide()" >隐藏</button></div>
							</td>
						</tr>
					</thead>
					<tr id="ly" >
						<td>
						<fieldset>
							<logic:empty name="rsJx">
								<br />
								<br />
								<p align="center">
									未找到任何记录！
								</p>
							</logic:empty>
							<logic:notEmpty name="rsJx">
								<legend>
									记录数：
									<bean:write name="reNum" />
								</legend>

								<table width="99%" id="rsTable" class="tbstyle">
									<thead>
										<tr align="center">											
											<td>
												奖项名
											</td>
											<td>
												学分
											</td>
										</tr>
									</thead>
									<logic:iterate name="rsJx" id="s">
										<tr style="cursor:hand">
											<td>
												<logic:iterate id="v" name="s" offset="0" length="1">
													<bean:write name="v" />
												</logic:iterate>
											</td>
											<logic:iterate id="v" name="s" offset="1">
												<td>
													<bean:write name="v" />
												</td>
											</logic:iterate>
										</tr>
									</logic:iterate>
								</table>
							</logic:notEmpty>
						</fieldset>
					 </td>
				  </tr>
				</table>
			<div class="buttontool" align="center">
			   	<logic:equal value="false" name="shLimit">	
				<button class="button2"
					onclick="tzcg_ShSave()"
					style="width:80px" id="buttonSave">
					保 存
				</button>
				&nbsp;&nbsp;&nbsp;&nbsp;
				</logic:equal>
				<button class="button2" onclick="Close();return false;" style="width:80px"
					id="buttonClose">
					关 闭
				</button>
			</div>
		</html:form>
		<logic:equal value="yes" name="done">
			<script language="javascript">
	         	alert("操作成功！");
	         	Close();
	         	window.dialogArguments.document.getElementById('search_go').click();
	         </script>
		</logic:equal>
	    <logic:equal value="no" name="done">
         	<script language="javascript">
	         	alert("操作失败！");
	         	Close();
	         	window.dialogArguments.document.getElementById('search_go').click();
	        </script>
	   </logic:equal>
	</body>
	<script type="text/javascript" >
		function showHide(){		
		   if(document.getElementById('ly').style.display==''){
		      document.getElementById('ly').style.display='none';
		      document.getElementById('xxbut').value='显示'
		   }else{
		      document.getElementById('ly').style.display='';
		      document.getElementById('xxbut').value='隐藏'
		   }		
		}
		function tzcg_ShSave(){
		  var pkV = $("pkValue").value;
		  if($("userType").value=="stu"||$("userType").value=="student"){
		    getSztzData.getInfoEx("csmz_tzxmb "," id ",pkV," (xysh='通过' or xxsh='通过') ",function(str){
		         if(str){		         	
		            alert("该拓展项目已通过上级审核\n或正在审核中，不能再操作！");		          
			        return false;
		         }else{
		            if(confirm("确定\'"+$("yesNo").value+"\'该拓展项目？")){
		             refreshForm('/xgxt/csmz_sztz.do?method=tzxm_sh&doType=save');
		               document.getElementById("buttonSave").disabled=true;
		            }else{
		               return false;
		            }
		        }
    	     });			    
		  }else if($("userType").value=="xy"){
		     getSztzData.getInfoEx("csmz_tzxmb ","id",pkV," xxsh='通过' ",function(str){
		         if(str){		         	
		            alert("该拓展项目已通过上级审核\n或正在审核中，不能再操作！");		          
			        return false;
		         }else{
		            if(confirm("确定\'"+$("yesNo").value+"\'该拓展项目？")){
		              refreshForm('/xgxt/csmz_sztz.do?method=tzxm_sh&doType=save');
		               document.getElementById("buttonSave").disabled=true;
		            }else{
		               return false;
		            }
		        }
    	     });
		  }else{
		      if(confirm("确定\'"+$("yesNo").value+"\'该拓展项目？")){
		            refreshForm('/xgxt/csmz_sztz.do?method=tzxm_sh&doType=save');
		            document.getElementById("buttonSave").disabled=true;
		        }else{
		            return false;
		       }
		  }  
		}
		</script>	
</html>
