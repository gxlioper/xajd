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
		<base target="_self">
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
	function jyxyxxsh(doType){
			var url ="/xgxt/JyglViewMoreinfo.do?doType=view&shenhe=shenhe&pkValue=";
			var pkValue ="";
		
			if (doType == "shenhe") {
		   		if (curr_row == null) {
					alert("请选择要审核的数据！\n（单击相应的行）");
					return false;
		    	} else {	 
		   		 	pkValue = curr_row.getElementsByTagName("input")[0].value;
		    		url += pkValue;
		    		showTopWin(url, 550,400);
		    		return true;
		    	}
		 	}else{
		    	return true;
		 	}
		}
		
	/*
	批量审核通过
	*/
	 function pass(url){
	   var RowsStr="!!#!!";
	
	   for (i=0; i<document.getElementsByName("pk").length; i++){
		 if(document.getElementsByName("pk")[i].checked){
    		RowsStr+=document.getElementsByName("pk")[i].value+"!!#!!";
    	 }
	   }
	   document.forms[0].pkstring.value = RowsStr;
	
	   if (RowsStr=="!!#!!"){
		  alert("请选择要批量审核的记录！");
		  return false;
	   }
	
	   if (!confirm("确定要通过所选记录？")){
		  return false;
	   }
	   document.forms[0].action=url;
       document.forms[0].submit();
    }
    
    /*
	批量审核否决
	*/
    function notpass(url){
	var RowsStr="!!#!!";
	
	  for (i=0; i<document.getElementsByName("pk").length; i++){
    	if(document.getElementsByName("pk")[i].checked){
    		RowsStr+=document.getElementsByName("pk")[i].value+"!!#!!";
    	  }
	  }
	  document.forms[0].pkstring.value = RowsStr;
	
	  if (RowsStr=="!!#!!"){
		alert("请选择要批量审核的记录！");
		return false;
	  }
	
	  if (!confirm("确定要否决所选记录？")){
		return false;
	  }
	document.forms[0].action=url;
    document.forms[0].submit();
   }
    
    
	function querygo(){
		 	document.forms[0].action = "/xgxt/jyxxtjwh.do?act=go&doType=query";
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
	</script>
	<script language="javascript">
		function bysjbxxbdel(doType){
		var url = "/xgxt/jyxxtjwh.do?act=del&pk=";
		var pkValue = "";
			
		if (doType == "del") {
		   if (curr_row == null) {
			alert("请选择要删除的数据！\n（单击相应的行）");
			return false;
		    } else {
			if (confirm("确定要删除该行数据吗？")) {
				pkValue = curr_row.getElementsByTagName("input")[0].value;
				url += pkValue;
				refreshForm(url);
				return true;
			} else {
				return false;
			}
		  }
		   return;
	      }
		}
		
		
		function viewMoreinfo(doType){
		var url ="/xgxt/jyxxtjwh.do?act=view&pk=";
		var pkValue ="";
		
		 if (doType == "view"){
		 pkValue = curr_row.getElementsByTagName("input")[0].value;
		 url += pkValue;
		 showTopWin(url, 800, 650);
		 }
		}
		function bysjbxxbupdate(doType){
		var url ="/xgxt/jyxxtjwh.do?act=update&pk=";
		
		 if (doType == "update"){
		    if (curr_row == null) {
			alert("请选择要修改的数据！\n（单击相应的行）");
			return false;
			 } else {
			if (confirm("确定要修改该行数据吗？")) {
				 pkValue = curr_row.getElementsByTagName("input")[0].value;
		         url += pkValue;
		         showTopWin(url, 800, 650);
				return true;
			} else {
				return false;
			}
		   }
	      }		
		}
		
		function add() {
	       var url = "/xgxt/jyxxtjwh.do?act=add";
	      	showTopWin(url, 850,650);
        }
		
		function jyglxsjbxxbDataExport() {
	       document.forms[0].action = "/xgxt/jyglxsjbxxbDataExport.do?tableName=zjgsjyxytj";
	       document.forms[0].target = "_blank";
	       document.forms[0].submit();
	       document.forms[0].target = "_self";
        }
		
		</script>
	<body>
		<html:form action="/jyxxtjwh" method="post">
			<input type="hidden" id="realTable" name="realTable"
				value="zjgsjyxytj" />
			<input type="hidden" id="tableName" name="tableName"
				value="zjgsjyxytj" />
			<input type="hidden" name="pkstring" value="" />
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：就业管理 - 学生信息 - 学生信息查询
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
								<html:text name="rsq" property="xh" style="width:85px"></html:text>
								&nbsp;&nbsp;姓名：
								<html:text name="rsq" property="xm" style="width:85px"></html:text>
								&nbsp;&nbsp;<bean:message key="lable.xsgzyxpzxy" />：
								<html:select name="rsq" property="xymc" style="width:150px">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xymc"
											labelProperty="xymc" />
								</html:select>
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
								显示记录数：
								<bean:write name="rsNum" />
								&nbsp;
								<font color="blue"><logic:present name="qssj">(<bean:write
											name="qssj" />--</logic:present> <logic:present name="zzsj">
										<bean:write name="zzsj" /> 违纪名单)</logic:present>
									提示：双击一行可以查看详细信息；单击表头可以排序;按住Ctrl可以多选</font>
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
										onclick="tableSort(this)" nowrap>
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<logic:iterate name="rs" id="s">
							<tr onclick="rowOnClick(this)" style="cursor:hand"
								ondblclick="viewMoreinfo('view')">
								<logic:iterate id="v" name="s" offset="1" length="1">
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
										<TD height=3></TD>
									</TR>
									<TR>
										<TD>
											<jsp:include flush="true"
												page="/sjcz/turnpage.jsp?form=zjgxJyxxForm"></jsp:include>
										</TD>
									</TR>
									<TR>
										<TD height=3></TD>
									</TR>
								</TABLE>
								<br />
								<br />
						</fieldset>
							
					</logic:notEmpty>

			<div class="buttontool" id="btn"
				style="position: absolute;left:0px;top:100px" width="98%">
				<logic:equal name="who" value="teacher">
				<logic:equal value="104911" name="xxdm">
					<button class="button2" onclick="jyxyxxsh('shenhe')"
						style="width:80px">
						审核
					</button>
				&nbsp;
				<button class="button2"
					onclick="pass('/xgxt/xsxxsh.do?doType3=pass')"
					style="width:80px">
					批量通过
				</button>
				&nbsp;
				<button class="button2"
					onclick="notpass('/xgxt/xsxxsh.do?doType3=notpass')"
					style="width:80px">
					批量否决
				</button>
				</logic:equal>
				</logic:equal>
				<logic:equal name="who" value="teacher">
				<logic:equal value="103551" name="xxdm">
					<button class="button2" onclick="jyxyxxsh('shenhe')"
						style="width:80px">
						审核
					</button>
				&nbsp;
				<button class="button2"
					onclick="pass('/xgxt/xsxxsh.do?doType3=pass')"
					style="width:80px">
					批量通过
				</button>
				&nbsp;
				<button class="button2"
					onclick="notpass('/xgxt/xsxxsh.do?doType3=notpass')"
					style="width:80px">
					批量否决
				</button>
				&nbsp;
				<button class="button2"
					onclick="del('/xgxt/JyglBysjbxxbDelall.do?doType2=query&doType=delall')"
					style="width:80px">
					批量删除
				</button>
				&nbsp;
				<button class="button2" onclick="jyglxsjbxxbDataExport()"
						style="width:80px">
						导出数据
					</button>
				</logic:equal>
				</logic:equal>
				&nbsp;
				<button class="button2"
					onclick="add();"
					style="width:80px">
					增加
				</button>
				&nbsp;
				<button class="button2" onclick="bysjbxxbupdate('update')"
					style="width:80px">
					修 改
				</button>
				&nbsp;
				<button class="button2" onclick="bysjbxxbdel('del')"
					style="width:80px">
					删 除
				</button>
				&nbsp;
				<button class="button2"
						onclick="impAndChkData();"
						style="width:80px">
						导入数据
					</button>
				
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
                      document.getElementById('search_go').click();
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
				<logic:equal name="delall" value="no">
					<script>
                      alert("批量删除失败");
                      document.getElementById('search_go').click();
                    </script>
				</logic:equal>
			</logic:notEmpty>
				<logic:notEmpty name="allpass">
				<logic:equal name="allpass" value="ok">
					<script>
                       alert("批量通过成功！");
                       document.getElementById('search_go').click();
                    </script>
				</logic:equal>
				<logic:equal name="allpass" value="no">
					<script>
                      alert("批量通过失败！");
                      document.getElementById('query_go').click();
                    </script>
				</logic:equal>
			</logic:notEmpty>
			
			<logic:notEmpty name="allnotpass">
				<logic:equal name="allnotpass" value="ok">
					<script>
                       alert("批量否决成功！");
                       document.getElementById('query_go').click();
                    </script>
				</logic:equal>
				<logic:equal name="allnotpass" value="no">
					<script>
                      alert("批量否决失败！");
                      document.getElementById('query_go').click();
                    </script>
				</logic:equal>
			</logic:notEmpty>
		</html:form>
		<script type="text/javascript" src="js/bottomButton.js"></script>
	</body>
</html>
