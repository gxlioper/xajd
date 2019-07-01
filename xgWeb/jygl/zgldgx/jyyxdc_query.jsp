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
	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
	<script language="javascript">
	function querygo(){
		 	document.forms[0].action = "/xgxt/jyyxdcb.do?act=query&doType=query";
		 	document.forms[0].submit();
    }
    /*
	全部选中
	*/    
      function chec(){
         for(i=0;i<document.getElementsByName("pk").length;i++){
      	    document.getElementsByName("pk")[i].checked=document.getElementsByName("qbxz")[0].checked;
         }
      }		
      /*
	批量删除
	*/
      function del(url){
	    var RowsStr="!!#!!";
	
	    for (i=0; i<document.getElementsByName("pk").length; i++){
    	   if(document.getElementsByName("pk")[i].checked){
    		RowsStr+=document.getElementsByName("pk")[i].value+"!!#!!";
    	   }
	    }
	    document.forms[0].pkstring.value = RowsStr;
	       if (RowsStr=="!!#!!"){
	         alert("请选择要批量删除的记录！");
		   return false;
    	}
	
	    if (!confirm("确定要删除所选记录？")){
		   return false;
	    }
	    document.forms[0].action=url;
        document.forms[0].submit();
     }
	
		function jyglxsjbxxbDataExport() {
	       document.forms[0].action = "/xgxt/jyyxdcb.do?tableName=jygl_xsjbxxb";
	       document.forms[0].target = "_blank";
	       document.forms[0].submit();
	       document.forms[0].target = "_self";
        }

		function initZyList(){
			var xydm = "";
			var query = "";
			var userName = "";
			var isFdy = "false";
			if($("isFdy")){isFdy = $("isFdy").value};
			if($("xy")){xydm = $("xy").value};	
			if($("userName")){userName = $("userName").value};
				if(xydm == null || xydm == ""){
					xydm = "%";
				} else{
					xydm = "%" + xydm +"%";
				}
				query = xydm;	
				if($("isBzr")){isBzr = $("isBzr").value};
			GetListData.getZyList(query,userName,isFdy,isBzr,function initTjList(data){
					if (data != null && typeof data == 'object') {
						var objId = data[0].dm;
						if($(objId) && $(objId).tagName.toLowerCase() == "select"){
							DWRUtil.removeAllOptions(objId);			
							DWRUtil.addOptions(objId,data,"dm","mc");			
							$(objId).options[0].value = "";
							if(objId){
							if($(objId).value != "" && $(objId).value != null){
								alert($(objId).tagName);
								for(var i = 0;i < $(objId).options.length; i++){
									if($(objId).options[i].value == $(objId).value){
										$(objId).options[i].selected = true;
										return true;
									}
								}
							}
							}
						}
					}else{
						showMsgWin("有错误出现：远程数据读取失败！");
					}
				});
		}
		function yxjzyjsadd(){
			 var url = "/xgxt/jyyxdcb.do?act=add";
			 showTopWin(url, 650, 350);
		}
		function tjsj(){
			var zymc = document.getElementById("zydm").value; 
		      var bynd = document.getElementById("bynd").value;
		      if(zymc=="--请选择--"||zymc==""){
		      alert("请选择专业名称！");
		      document.getElementById("zydm").focus();
		      return false;
		      }
		      if(bynd=="--请选择--"||bynd==""){
			      alert("请选择毕业年度！");
			      document.getElementById("bynd").focus();
			      return false;
			      }
			 document.forms[0].action = "/xgxt/jyyxdcb.do?act=query&doType=query&tj=tj";
		     document.forms[0].submit();
		}			
		function ifyctj(){
			var usertype = document.getElementById("isuser").value;
			//if(usertype=="stu"){
			//	document.getElementById("xydm").disabled=true;
			//	document.getElementById("zydm").disabled=true;
			//}
			//if(usertype=="xy"){
			//	document.getElementById("xydm").disabled=true;
			//}
		}
		</script>
	<body onload="ifyctj();">
		<html:form action="/yxjzyjs.do" method="post">
			<input type="hidden" id="realTable" name="realTable"
				value="<bean:write name='realTable' scope="request" />" />
				<input type="hidden" id="isuser" name="isuser"
				value="<bean:write name='usertype' scope="request" />" />
			<input type="hidden" name="pkstring" value="" />
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：就业管理 - 就业协议方案 - 就业意向调查
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
							<logic:equal value="xy" name="userType" scope="session">
								<bean:message key="lable.xsgzyxpzxy" />：
								<input id="xydmdt" name="xydmdt" value="${xydmdt }" disabled="true"/>
								<html:select name="rs1" property="xydm" onchange="initZyList();" style="width:160px;display: none" styleId="xy">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select>

								&nbsp;&nbsp;&nbsp;
								专业：
								<html:select name="rs1" property="zydm" onchange="" style="width:170px" styleId="zy">
									<html:option value=""></html:option>
									<html:options collection="zyList" property="zydm"
										labelProperty="zymc" />
								</html:select>
								</logic:equal>
								
								
									<logic:equal value="stu" name="userType" scope="session">
								<bean:message key="lable.xsgzyxpzxy" />：
								<html:hidden property="xh" value="${stuname}"></html:hidden>
								<input id="xydmdt" name="xydmdt" value="${xydmdt}" disabled="disabled"/>
								<html:select name="rs1" property="xydm" onchange="initZyList();" style="width:160px;display: none" styleId="xy">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select>

								&nbsp;&nbsp;&nbsp;
								专业：
								<input id="zydmdt" name="zydmdt" value="${zydmdt }" disabled="disabled"/>
								<html:select name="rs1" property="zydm" onchange="" style="width:170px;display: none" styleId="zy">
									<html:option value=""></html:option>
									<html:options collection="zyList" property="zydm"
										labelProperty="zymc" />
								</html:select>
								</logic:equal>
								<logic:notEqual value="stu" name="userType" scope="session">
								<logic:notEqual value="xy" name="userType" scope="session">
									<bean:message key="lable.xsgzyxpzxy" />：
								<html:select name="rs1" property="xydm" onchange="initZyList();" style="width:160px;" styleId="xy">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select>

								&nbsp;&nbsp;&nbsp;
								专业：
								<html:select name="rs1" property="zydm" onchange="" style="width:170px" styleId="zy">
									<html:option value=""></html:option>
									<html:options collection="zyList" property="zydm"
										labelProperty="zymc" />
								</html:select>
								</logic:notEqual>
								</logic:notEqual>
								<div style="display: none">
								<logic:equal name="who" value="xy">
								<bean:message key="lable.xsgzyxpzxy" />：
								<html:text name="rs1" property="xymc" style="width:150px"
										readonly="true" />
										
								&nbsp;&nbsp;&nbsp;
								</logic:equal>
								</div>
								
							</td>
							<td width="10" rowspan="2" align="center" valign="middle">
								<button class="button2" style="height:40px" onclick="querygo()"
									id="search_go">
									查询
								</button>
							</td>
						</tr>
							<tr>
							<td>
								毕业年度：
								<html:select name="rs1" property="bynd" style="width:120px">
									<html:option value=""></html:option>
									<html:option value="2006">
										2006
									</html:option>
									<html:option value="2007">
										2007
									</html:option>
									<html:option value="2008">
										2008
									</html:option>
									<html:option value="2009">
										2009
									</html:option>
									<html:option value="2010">
										2010
									</html:option>
									<html:option value="2011">
										2011
									</html:option>
									<html:option value="2012">
										2012
									</html:option>
									<html:option value="2013">
										2013
									</html:option>
									<html:option value="2014">
										2014
									</html:option>
									<html:option value="2015">
										2015
									</html:option>
								</html:select>
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
						<font color="blue">提示：单击表头可以排序</font>
					</legend>
					<table width="100%" id="rsTable" class="tbstyle">
						<thead>
							<tr align="center" style="cursor:hand">
								<td>
									<input type="checkbox" name="qbxz" value="all"
										onclick="chec('qbxz')">
								</td>
								<logic:iterate id="tit" name="topTr" offset="1" length="12">
									<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)" nowrap="nowrap">
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<logic:iterate name="rs" id="s">
							<tr onclick="rowOnClick(this)" style="cursor:hand"
								ondblclick="">
								<logic:iterate id="v" name="s" offset="0" length="1">
									<input type="hidden" value="<bean:write name="v"/>" />
								</logic:iterate>
								<td align="center">
									<logic:iterate id="v" name="s" offset="0" length="1">
										<input type="checkbox" name="pk"
											value="<bean:write name="v"/>">
									</logic:iterate>
								</td>
								<logic:iterate id="v" name="s" offset="1" length="12">
									<td align="center">
										<bean:write name="v" />
									</td>
								</logic:iterate>
							</tr>
						</logic:iterate>
					</table>
					<TABLE width="99%" id="rsTable1" class="tbstyle">
									<TR>
										<TD height="30px"></TD>
									</TR>
									<TR>
										<TD>
											<jsp:include flush="true"
												page="/sjcz/turnpage.jsp?form=commanForm"></jsp:include>
										</TD>
									</TR>
									<TR>
										<TD height="30px"></TD>
									</TR>
								</TABLE>
				</fieldset>
			</logic:notEmpty>



			<div class="buttontool" id="btn"
				style="position: absolute;left:0px;top:100px" width="98%">
				&nbsp;
				<button class="button2" onclick="yxjzyjsadd();"
					style="width:80px">
					增加
				</button>
				<logic:notEqual value="stu" name="userType" scope="session">
				&nbsp;
				<button class="button2"
					onclick="del('/xgxt/jyyxdcb.do?act=alldel')"
					style="width:80px">
					批量删除
				</button>
				<button class="button2"
					onclick="tjsj();"
					style="width:80px">
					统计
				</button>
				</logic:notEqual>
			</div>

			<logic:notEmpty name="delete">
				<logic:equal name="delete" value="ok">
					<script>
                      alert("删除成功!");
                      document.getElementById('search_go').click();
                    </script>
				</logic:equal>
				<logic:equal name="delete" value="no">
					<script>
                      alert("删除失败");
                    </script>
				</logic:equal>
			</logic:notEmpty>
			<logic:notEmpty name="delall">
				<logic:equal name="delall" value="ok">
					<script>
                      alert("批量删除成功!");
                      document.getElementById('search_go').click();
                    </script>
				</logic:equal>
				<logic:notEqual name="delall" value="ok">
					<script type="text/javascript">
                      alert("批量删除失败!");
                      document.getElementById('search_go').click();
                    </script>
				<</logic:notEqual>
			</logic:notEmpty>
				<logic:notEmpty name="allpass">
				<logic:equal name="allpass" value="ok">
					<script>
                       alert("批量审核成功！");
                       document.getElementById('search_go').click();
                    </script>
				</logic:equal>
				<logic:equal name="allpass" value="no">
					<script>
                      alert("批量审核失败！");
                      document.getElementById('search_go').click();
                    </script>
				</logic:equal>
			</logic:notEmpty>
		</html:form>
		<script type="text/javascript" src="js/bottomButton.js"></script>
	</body>
</html>
