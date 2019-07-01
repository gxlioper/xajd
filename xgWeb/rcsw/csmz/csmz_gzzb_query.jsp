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
		<meta http-equiv="Cache-Control" http-equiv="no-cache" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="正方软件 zfsoft" />
		<link rel="icon" href="images/icon.ico" type="image/x-icon" />
		<link id="csss" rel="stylesheet" rev="stylesheet"
			href="style/main.css" type="text/css" media="all" />
	</head>
	<base target="_self">
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>

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
	<script language="javascript">
	function querygo(){
		 	document.forms[0].action = "csmz_gzzb_query.do?act=go&doType=query";
		 	document.forms[0].submit();
    }
   
   	function viewmore(){
		var url ="/xgxt/viewmoregzzb.do?pkValue=";
		  var pkValue =curr_row.getElementsByTagName("input")[0].value;;
		  url += pkValue;
		   showTopWin(url, 790, 510);
		}
	//function refreshtheweb()
	//	{
	//		document.forms[0].action = "hzjyXtyglDjxsQuery.do?act=go";
     //       document.forms[0].submit();
	//	}
		
    function addgzzb(){
            var url ="addGzzb.do";
		    showTopWin(url, 750, 500);
    
    }
	 //function  hzjyDataExport(){
	 //      var realTable = $("realTable").value;
	  //     document.forms[0].action = "/xgxt/hzjy_xssqbDataExport.do?realTable="+realTable;
	  //     document.forms[0].target = "_blank";
	  //     document.forms[0].submit();
	  //     document.forms[0].target = "_self";
     //   }
        
    function updategzzb(){
        var url = "updateGzzb.do?pkValue=";
		var pkValue = "";
			

	    if (curr_row == null) {
			alert("请选择要修改的数据！\n（单击相应的行）");
			return false;
		    } else {
				pkValue = curr_row.getElementsByTagName("input")[0].value;
				url += pkValue;
				showTopWin(url, 750, 500);
				return true;
		   }
    
    }
        
        
        
    	function gzzbdel(doType){
		var url = "csmz_gzzb_query.do?act=go&doType=del&pkValue=";
		var pkValue = "";
			
		if (doType == "del") {
		   if (curr_row == null) {
			alert("请选择要删除的数据！\n（单击相应的行）");
			return false;
		    } else {
			if (confirm("确定要删除该行数据吗？")) {
			    BatAlert.showTips('正在删除，请稍候...');
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
	</script>
	<base target="_self">
	<body>
		<html:form action="/csmz_gzzb_query" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：日常事务 - 日常考核及工作周报 - 工作周报
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
								标题：
								<html:text name="rs1" property="bt" />
								&nbsp;&nbsp;&nbsp;
							</td>
							<td width="10" rowspan="2" align="center" valign="middle">
								<button type="button" class="button2" style="height:40px" id="search_go"
									onclick="querygo()">
									查询
								</button>
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
						<font color="blue">提示：双击一行可查看详细信息</font>
					</legend>
					<table width="100%" id="rsTable" class="tbstyle">
						<thead>
							<tr align="center" style="cursor:hand">
								<td>
									行号
								</td>
								<td>
									标题
								</td>
								<td>
									发布人姓名
								</td>
								<td>
									部门
								</td>
								<td>
									发布时间
								</td>
							</tr>
						</thead>
						<logic:iterate name="rs" id="s">
							<tr onclick="rowOnClick(this)" style="cursor:hand"
								ondblclick="viewmore()" align="center">
								<td>
								<input type="hidden"
									value="<bean:write name="s" property="rid"/>" />
									<bean:write name='s' property='行号' />
								</td>
								<td>
									<bean:write name='s' property='bt' />
								</td>
								<td>
									<bean:write name='s' property='fbrxm' />
								</td>
								<td>
									<bean:write name='s' property='bmmc' />
								</td>
								<td>
									<bean:write name='s' property='fbsj' />
								</td>
							</tr>
						</logic:iterate>
					</table>
					<table width="100%" id="Table" class="tbstyle">
						<TR>
							<TD>
								<jsp:include flush="true"
									page="/sjcz/turnpage.jsp?form=commanForm"></jsp:include>
							</TD>
						</TR>
					</table>
				</fieldset>
			</logic:notEmpty>
			<logic:equal name="xx" value="xx">
				<div class="buttontool" id="btn"
					style="position: absolute;left:0px;top:100px" width="100%">
					<button type="button" class="button2" onclick="addgzzb()" style="width:100px">
						增加工作周报
					</button>
					&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2" onclick="updategzzb()" style="width:100px">
						修改工作周报
					</button>
					&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2" onclick="gzzbdel('del');"
						style="width:100px">
						删除
					</button>
				</div>
			</logic:equal>
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
		</html:form>
		<script type="text/javascript" src="js/bottomButton.js"></script>
	</body>
</html>
