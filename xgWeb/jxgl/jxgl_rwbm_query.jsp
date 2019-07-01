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
		<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
		%>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control" http-equiv="no-cache" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="正方软件 zfsoft" />
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script language="javascript" src="js/jsFunction.js"></script>
	<script type="text/javascript" src="js/BatAlert.js"></script>
	<script language="javascript" src="js/function.js"></script>

	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/getTowdays.js'></script>
	<script type='text/javascript'
		src='/xgxt/dwr/interface/getScoreinfo.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='dwr/util.js'></script>
	<script language="javascript" src="js/AjaxFunction.js"></script>
	<script type="text/javascript">
	
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
      function delall(url){
	    var RowsStr="!!#!!";
    
	    for (i=0; i<document.getElementsByName("pk").length; i++){
    	   if(document.getElementsByName("pk")[i].checked){
    		RowsStr+=document.getElementsByName("pk")[i].value+"!!#!!";		
    	   }
	    }
	    
	    document.forms[0].pkstring.value = RowsStr;
	       if (RowsStr=="!!#!!"){
	         alert("请勾选需要批量删除的记录！");
		   return false;
    	}
	
	    if (!confirm("你确定要批量删除所选记录？")){
		   return false;
	    }
	    BatAlert.showTips('正在删除，请稍侯...');
	    document.forms[0].action=url;
        document.forms[0].submit();
     }
	
	
	    /*
	批量审核
	*/
      function shall(url){
	    var RowsStr="!!#!!";
    
	    for (i=0; i<document.getElementsByName("pk").length; i++){
    	   if(document.getElementsByName("pk")[i].checked){
    		RowsStr+=document.getElementsByName("pk")[i].value+"!!#!!";		
    	   }
	    }
	    
	    document.forms[0].pkstring.value = RowsStr;
	       if (RowsStr=="!!#!!"){
	         alert("请勾选需要批量审核的记录！");
		   return false;
    	}
	
	    if (!confirm("你确定要批量审核所选记录？")){
		   return false;
	    }
	    BatAlert.showTips('正在审核，请稍侯...');
	    document.forms[0].action=url;
        document.forms[0].submit();
     }
	
	
	
	
	
	
	function viewMoreinfo(doType){
		var url ="jxglrwbmmorequery.do?pkValue=";
		var pkValue ="";
		
		 if (doType == "view"){
		 pkValue = curr_row.getElementsByTagName("input")[0].value;
		 url += pkValue;
		 showTopWin(url, 1000, 800);
		 }
		}	
	
	function hzjy_xssqbDataExport() {
	       document.forms[0].action = "/xgxt/jxgljz_xbmz.do?method=wszbDataExport&realTable=jxgl_rwbmdjb";
	       document.forms[0].target = "_blank";
	       document.forms[0].submit();
	       document.forms[0].target = "_self";
        }	
        
     //单个删除   
    function deletetheinfo(doType){
        var url = "jxglrwbmquery.do?doType=query&doType2=del&act=go&pkValue=";
		var pkValue = "";
			
		if (doType == "del") {
		   if (curr_row == null) {
			alert("请选择要删除的数据！\n（单击相应的行）");
			return false;
		    } else {
			if (confirm("确定要删除该行数据吗？")) {
				pkValue = curr_row.getElementsByTagName("input")[0].value;
				url += pkValue;
				BatAlert.showTips('正在删除，请稍侯...');
				refreshForm(url);
				return true;
			} else {
				return false;
			}
		  }
		   return;
	      }
		}
		
	 function jxglxxsh(doType){
        var url = "jxglrwbmxxsh.do?pkValue=";
		var pkValue = "";
			
		if (doType == "xxsh") {
		   if (curr_row == null) {
			alert("请选择要审核的数据！\n（单击相应的行）");
			return false;
		    } else {
				pkValue = curr_row.getElementsByTagName("input")[0].value;
				url += pkValue;
				showTopWin(url, 1000, 800);
				return true;
			}
		  }
		   return;
	      }

	 function jxglzbxxsh(doType){
        var url = "jxglrwbmxxsh.do?pkValue=";
		var pkValue = "";
			
		if (doType == "zbxxsh") {
		   if (curr_row == null) {
			alert("请选择要审核的数据！\n（单击相应的行）");
			return false;
		    } else {
				pkValue = curr_row.getElementsByTagName("input")[0].value;
				url += pkValue;
				url +="&zb=zb";
				showTopWin(url, 800, 600);
				return true;
			}
		  }
		   return;
	      }
	      
	function bmxxPrint(){
		var url = "/xgxt/jxgljz_xbmz.do?method=bmxxPrint&pkValue=";
		var pkValue = "";
		
		if (curr_row == null) {
			alert("请选择要打印的数据！\n（单击相应的行）");
			return false;
		    } else {
			if (confirm("确定要打印该行数据吗？")) {
				pkValue = curr_row.getElementsByTagName("input")[0].value;
				url += pkValue;
			} else {
				return false;
			}
		  }
		window.open(url);
	}
	</script>
	


	<body onload="xyDisabled('xy')">
		<html:form action="/jxglrwbmquery" method="post">
			<logic:notEmpty name="piliang">
				<input type="hidden" name="whichpk" value="<bean:write name="piliang" />" />
				<script>
				   var whichpk = $("whichpk").value;
				   alert(whichpk);
				</script>
			</logic:notEmpty>
			<div class="title">
				<div class="title_img" id="title_m">
					当前位置：军训管理 - 网上征兵 - 入伍报名审核
				</div>
			</div>
			<input type="hidden" name="pkstring" value="" />
			<input type="hidden" name="towdays" value="" style="width:50px" />
			<input type="hidden" id="tableName" name="tableName"
				value="view_jxgl_rwbmdjb" />
			<input type="hidden" id="realTable" name="realTable"
				value="jxgl_rwbmdjb" />
			<input type="hidden" name="zyV" id="zyV" />
			<input type="hidden" name="bjV" id="bjV" />
			<input type="hidden" id="userType" name="userType" value="${userType }" />
			<fieldset>
				<legend>
					查询
				</legend>
				<table width="100%" class="tbstyle">
					<thead>
						<tr>
							<td align="left">
								学号：
								<html:text name="rs1" property="xh" style="width:157px" />
								&nbsp; 姓名：
								<html:text name="rs1" property="xm" style="width:65px" />
								&nbsp; 性别：
								<html:select name="rs1" property="xb">
									<html:option value=""></html:option>
									<html:option value="男">男</html:option>
									<html:option value="女">女</html:option>
								</html:select>
								&nbsp; 审核结果：
								<html:select name="rs1" property="shjg">
									<html:option value=""></html:option>
									<html:option value="未审核"></html:option>
									<html:option value="已通过√"></html:option>
									<html:option value="未通过X"></html:option>
								</html:select>
							</td>
							<td width="10" rowspan="2" align="center" valign="middle">
								<input type="hidden" name="go" value="a" />
								<button type="button" class="button2" style="height:40px" id="query_go"
									onclick="allNotEmpThenGo('jxglrwbmquery.do?act=go&doType=query')">
									查询
								</button>
							</td>
						</tr>
						<tr>
							<td align="left" nowrap>
								年级：
								<html:select name="rs1" property="nj" style="width:70px"
									onchange="refreshForm('jxglrwbmquery.do?doType=query')">
									<html:option value=""></html:option>
									<html:options collection="njList" property="nj"
										labelProperty="nj" />
								</html:select>
								&nbsp;&nbsp; <bean:message key="lable.xsgzyxpzxy" />：
								<logic:equal name="who" value="xx">
									<html:select name="rs1" property="xydm" style="width:150px"
										styleId="xy" onchange="initZyList();initBjList()">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select>
								</logic:equal>
								<logic:equal name="who" value="xy">
									<html:hidden property="xydm" value="${rs1.xydm}"/>
									<html:select name="rs1" property="xydm" style="width:150px"
										styleId="xy" disabled="true">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select>
								</logic:equal>
								&nbsp;&nbsp;专业：
								<html:select name="rs1" property="zydm" style="width:160px"
									styleId="zy" onchange="initBjList()">
									<html:option value=""></html:option>
									<html:options collection="zyList" property="zydm"
										labelProperty="zymc" />
								</html:select>
								&nbsp;&nbsp;班级：
								<html:select name="rs1" property="bjdm" style="width:160px"
									styleId="bj">
									<html:option value=""></html:option>
									<html:options collection="bjList" property="bjdm"
										labelProperty="bjmc" />
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
						<font color="blue">提示：双击一行可以查看详细信息；单击表头可以排序</font>
					</legend>
					<table width="100%" id="rsTable" class="tbstyle">
						<thead>
							<tr align="center" style="cursor:hand">
								<td>
									<input type="checkbox" name="qbxz" value="all"
										onclick="chec('qbxz')">
								</td>
								<logic:iterate id="tit" name="topTr" offset="0">
									<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)" nowrap>
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</tr>
						</thead>

						<logic:notEqual value="11407" name="xxdm">
						<logic:iterate name="rs" id="s">
							<tr onclick="rowOnClick(this)" style="cursor:hand"
								ondblclick="viewMoreinfo('view')">
								<td align="center">
								<input type="hidden" name="pkValue"
									value="<bean:write name="s" property="xh"/>" />
									<input type="checkbox" name="pk"
										value="<bean:write name="s" property="xh"/>" />
								</td>
								<td align="center">
									<bean:write name="s" property="行号" />
								</td>
								<td align="center">
									<bean:write name="s" property="xh" />
								</td>
								<td align="center">
									<bean:write name="s" property="xm" />
								</td>
								<td align="center">
									<bean:write name="s" property="xb" />
								</td>
								<td align="center">
									<bean:write name="s" property="rxsj" />
								</td>
								<td align="center">
									<bean:write name="s" property="xymc" />
								</td>
								<td align="center">
									<bean:write name="s" property="zymc" />
								</td>
								<td align="center">
									<bean:write name="s" property="djsj" />
								</td>
								<td align="center">
									<bean:write name="s" property="xxshimg" filter="false" />
								</td>
							</tr>
						</logic:iterate>
						</logic:notEqual>
						<logic:equal value="11407" name="xxdm">
						<logic:iterate name="rs" id="s">
							<tr onclick="rowOnClick(this)" style="cursor:hand"
								ondblclick="viewMoreinfo('view')">
								<td align="center">
								<input type="hidden" name="pkValue"
									value="<bean:write name="s" property="xh"/>" />
									<input type="checkbox" name="pk"
										value="<bean:write name="s" property="xh"/>" />
								</td>
								<td align="center">
									<bean:write name="s" property="行号" />
								</td>
								<td align="center">
									<bean:write name="s" property="xh" />
								</td>
								<td align="center">
									<bean:write name="s" property="xm" />
								</td>
								<td align="center">
									<bean:write name="s" property="xb" />
								</td>
								<td align="center">
									<bean:write name="s" property="rxsj" />
								</td>
								<td align="center">
									<bean:write name="s" property="xymc" />
								</td>
								<td align="center">
									<bean:write name="s" property="zymc" />
								</td>
								<td align="center">
									<bean:write name="s" property="djsj" />
								</td>
								<td align="center">
									<bean:write name="s" property="xxshimg" filter="false" />
								</td>
								<td align="center">
									<bean:write name="s" property="zbbgsshimg" filter="false" />
								</td>
							</tr>
						</logic:iterate>
						</logic:equal>
					</table>
				</fieldset>
			</logic:notEmpty>
			<center>
				<div class="buttontool" id="btn"
					style="position: absolute;left:0px;top:100px" width="98%">
					<logic:equal name="who" value="xx">
					<logic:notEqual value="11407" name="xxdm">
					&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2" onclick="jxglxxsh('xxsh')"
							style="width:80px">
							学校审核
					</button>
					&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2"
							onclick="shall('jxglrwbmquery.do?doType2=shall&act=go&doType=query&shjg=tg')"
							style="">
							批量审核通过
					</button>
					&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2"
							onclick="shall('jxglrwbmquery.do?doType2=shall&act=go&doType=query&shjg=btg')"
							style="">
							批量审核不通过
					</button>
					</logic:notEqual>
					</logic:equal>
					<logic:equal value="11407" name="xxdm">
						<logic:present name="num">
							<logic:notEqual name='num'value="0">
							&nbsp;&nbsp;&nbsp;
							<button type="button" class="button2" onclick="jxglxxsh('xxsh')"
							style="width:80px">
							学校审核
							</button>
							&nbsp;&nbsp;&nbsp;
							<button type="button" class="button2"
							onclick="shall('jxglrwbmquery.do?doType2=shall&act=go&doType=query')"
							style="width:85px">
							学校批量审核
							</button>
							</logic:notEqual>
						</logic:present>
						<logic:notPresent name="num">
							<logic:present name="yhm">
								<logic:notEqual name='yhm'value="0">
								&nbsp;&nbsp;&nbsp;
								<button type="button" class="button2" onclick="jxglzbxxsh('zbxxsh')"
								style="width:100px">
								征兵办公室审核
								</button>
								&nbsp;&nbsp;&nbsp;
								<button type="button" class="button2"
								onclick="shall('jxglrwbmquery.do?doType2=shall2&act=go&doType=query')"
								style="width:130px">
								批量审核
								</button>
								</logic:notEqual>
							</logic:present>
						</logic:notPresent>
					</logic:equal>
					&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2" onclick="deletetheinfo('del')"
						style="width:80px">
						删 除
					</button>
					&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2"
						onclick="delall('jxglrwbmquery.do?doType2=delall&act=go&doType=query')"
						style="width:80px">
						批量删除
					</button>
					&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2"
						onclick="impAndChkData();"
						style="width:80px">
						导入数据
					</button>
					&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2" onclick="dataExport()"
						style="width:80px">
						导出数据
					</button>
					<logic:equal value="11407" name="xxdm">
					&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2" onclick="bmxxPrint();">
						学生信息打印
					</button>
					</logic:equal>
				</div>
			</center>
		</html:form>
		<script language="javascript">
					document.getElementById("btn").style.pixelTop = document.body.clientHeight - 33;
					document.getElementById("btn").style.width = "98%";
					window.setInterval("initBTNTool('btn')",1);
		</script>
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
