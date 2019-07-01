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
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css" type="text/css" media="all" />
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/sxjyFunction.js"></script>
	<script language="javascript">
	function FdyCjCk(){
	   if(document.getElementById("zxm").value == ""){
			alert("请选择教师(职工)！");
			return false;
		}
	   refreshForm('/xgxt/szdw_xxQuery.do')
	}
	function SzdwHandleData(dotype){
	   var zxm = document.getElementById('zxm').value;
	   var url = "/xgxt/";
	   var tabName = document.getElementById("tableName").value;
	   if(zxm == ""){
	         alert('请选择教师(职工)！');
	         return false;
	   }
	   if(dotype == "add"){ 
	       if(tabName == "view_szdw_gzxx"){
	         url +="gzxxOne.do?doType=";       	             
	      }else if(tabName == "view_szdw_jcxx"){	        
	         url +="jcxxOne.do?doType=";       	      	         
	      }else if(tabName == "view_szdw_ywpxxx"){	         
	         url +="ywpxxxOne.do?doType=";       	      	       
	      }
	      url +=dotype;
	      url +="&zxm=";
	      url += zxm;
	      showTopWin(url,800,600);
	      return true;
	   }
	   if(dotype == "modi"){	      	       
	       if(curr_row == null){
	           alert('请选择要修改的行!');
	           return false;
	       } else {
	           var pk=curr_row.getElementsByTagName('input')[0].value; 
	           if (tabName == "view_szdw_gzxx"){	             
	               url +="gzxxOne.do?doType=";	                       
	           }else if(tabName=="view_szdw_jcxx"){	               
	               url +="jcxxOne.do?doType=";	               
	           }else if(tabName=="view_szdw_ywpxxx"){	               
	               url +="ywpxxxOne.do?doType=";	               
	           }
	           url +=dotype;
	           url +="&pk=";
	           url += pk;
	           showTopWin(url,800,600);
	           return true;
	       }
	   }
	   if(dotype == "del"){
	      if(curr_row == null){
	           alert('请选择要修改的行!');
	           return false;
	      }else{
	         if (confirm("确定要删除此行吗？")){
	            var pk=curr_row.getElementsByTagName('input')[0].value;
	            if (tabName == "view_szdw_gzxx"){	              
	               url +="gzxxOne.do?doType=";	                      
	            }else if(tabName == "view_szdw_jcxx"){	              
	               url +="jcxxOne.do?doType=";	               
	            }else if(tabName == "view_szdw_ywpxxx"){
	               url +="ywpxxxOne.do?doType=";
	            }
	            url +=dotype;
	            url +="&pk=";
	            url += pk;
	            document.forms[0].go.value = "go";
		        refreshForm(url);
	            return true; 
	         }else{
	            return false;
	         }
	         
	      }
	   }
	   	
	} 
   function expTabCon(){
        var expTit = "思政队伍成员 - "
        expTit +=document.getElementById("zgxm").value;
        expTit +="-";
        expTit +=document.getElementById("tips").value;
        expTit +="列表";       
        //alert(document.all(rsTable).outerHTML);
        expTab('rsTable',expTit,'');
    } 
    </script>
	<body onload="xyDisabled('xy')">
		<html:form action="/szdw_xxQuery" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：辅导员管理 - 信息维护 -	思政队伍 -<bean:write name="tips" scope="request"/>
				</div>
			</div>
			<input type="hidden" id="tableName" name="tableName"
				value="<bean:write name="tableName" scope="request"/>" />
			<input type="hidden" id="realTable" name="realTable"
				value="<bean:write name="realTable" scope="request"/>" />
			<input type="hidden" id="userType" name="userType"
				value="<bean:write name="userType" scope="request"/>" />
			<input type="hidden" id="writeAble" name="writeAble"
				value="<bean:write name="writeAble" scope="request"/>" />
			<input type="hidden" id="tips" name="tips"
				value="<bean:write name="tips" scope="request"/>" />
			<input type="hidden" id="zgxm" name="zgxm"
				value="<bean:write name="zgxm" scope="request"/>" />		
			<table width="100%" class="tbstyle">
				<thead>
					<tr>
						<td align="left">
							部门：
							<html:select property="xydm" styleId="xy" onchange="refreshForm('szdw_xxQuery.do');getfocus('search_go')">
								<html:option value=""></html:option>
								<html:options collection="bmList" property="xydm"
									labelProperty="xymc" />
							</html:select>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;职工：
							<html:select property="zxm" style="width:120px" styleId="zxm" >
								<html:option value=""></html:option>
								<html:options collection="zgList" property="zgh"
									labelProperty="xm" />
							</html:select>
						</td>
						<td width="10" align="center" valign="middle">
							<input type="hidden" name="go" value="a" />
							<button type="button" class="button2" id="search_go"
								onclick="document.forms[0].go.value='go';FdyCjCk()">
								查 询
							</button>
						</td>
						<td width="10" align="center" valign="middle">
							<button type="button" class="button2" onclick="">
								&nbsp;&gt;&nbsp;&gt;&nbsp;
							</button>
						</td>
					</tr>
				</thead>
			</table>
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
								<logic:iterate id="tit" name="topTr" offset="1">
									<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)" nowrap >
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<logic:iterate name="rs" id="s">
							<tr onclick="rowOnClick(this)" style="cursor:hand"
								ondblclick="return SzdwHandleData('modi');">
								<td>
									<logic:iterate id="v" name="s" offset="0" length="1">
										<input type="hidden" value="<bean:write name="v"/>" />
									</logic:iterate>
									<logic:iterate id="v" name="s" offset="1" length="1">
										<bean:write name="v" />
									</logic:iterate>
								</td>
								<logic:iterate id="v" name="s" offset="2" >
									<td >
										<bean:write name="v" />
									</td>
								</logic:iterate>
							</tr>
						</logic:iterate>
					</table>
				</fieldset>
			</logic:notEmpty>
			<br/>
			<logic:equal value="yes" name="writeAble">	
			<div class="buttontool" id="btn"
					style="position: absolute;left:1%;top:100px" width="100%">					        
					<button type="button" class="button2"
						onclick="return SzdwHandleData('add');"
						style="width:80px">
						增 加
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2"
						onclick="return SzdwHandleData('modi');"
						style="width:80px">
						修 改
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2"
						onclick = "return SzdwHandleData('del');"
						style="width:80px">
						删 除
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2"
						onclick="impAndChkData();"
						style="width:80px">
						导入数据
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2" onclick="dataExport()" style="width:80px">
						导出数据
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2" onclick="expTabCon()" style="width:80px">
						打印列表
					</button>
				     </div>
				     <script language="javascript">		
                     document.getElementById("btn").style.pixelTop = document.body.clientHeight - 35;
                     document.getElementById("btn").style.width = "96%";
                     window.setInterval("initBTNTool('btn')",1);
         </script>
				</logic:equal>
		   </html:form>
		   <logic:present name="del">
			 <logic:equal value="ok" name="del">
				<script>
                alert("删除成功！");
                </script>
		     </logic:equal>
		     <logic:equal value="no" name="del">
				<script>
                 alert("删除失败！");
                </script>
			</logic:equal>
		  </logic:present>		 		
	</body>
</html>