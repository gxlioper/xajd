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
	function gnxxsh(doType){
			var url ="/xgxt/bysbm.do?act=Auditing&shenhe=auditing&pkValue=";
			var pkValue ="";
		   		if (curr_row == null) {
					alert("请选择要审核的数据！\n（单击相应的行）");
					return false;
		    	} else {	 
		   		 	pkValue = curr_row.getElementsByTagName("input")[0].value;
		    		url += pkValue;
		    		showTopWin(url, 550,400);
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

		if(document.getElementById("bmxm")){
		  var bmxmdm = document.getElementById("bmxm").value;
		  
	      if(bmxmdm=="--请选择--"||bmxmdm==""){
	      alert("报名项目为空，请选择报名项目");
	      return false;
	      }
		}
	      if(document.getElementById("bmbynd")){
	      var bynd = document.getElementById("bmbynd").value;
	      if(bynd=="--请选择--"||bynd==""){
		      alert("毕业年度为空，请选择毕业年度");
		      return false;
		   }
	      }
		 	document.forms[0].action = "/xgxt/bysbm.do?act=query&doType=query";
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
		var url = "/xgxt/bysbm.do?act=del&pkValue=";
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
		var url ="/xgxt/bysbm.do?act=view&pkValue=";
		var pkValue ="";
		 if (doType == "view"){
		 pkValue = curr_row.getElementsByTagName("input")[0].value;
		 url += pkValue;
		 showOpenWindow(url, 650, 350,false);
		 }
		}
		function bysjbxxbupdate(doType){
		var url ="/xgxt/bysbm.do?act=update&pkValue=";
		var pkValue ="";
		
		 if (doType == "update"){
		    if (curr_row == null) {
			alert("请选择要修改的数据！\n（单击相应的行）");
			return false;
			 } else {
			if (confirm("确定要修改该行数据吗？")) {
				 pkValue = curr_row.getElementsByTagName("input")[0].value;
		         url += pkValue;
		         showTopWin(url, 600, 370);
				return true;
			} else {
				return false;
			}
		   }
	      }		
		}
		
		
		
		function dataExport() {
	       document.forms[0].action = "/xgxt/bysbm.do?act=expdata";
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
			var userType = document.getElementById("userType").value;
			if("stu" != userType){
				alert("你不是学生用户，不能申请");
				return false;
			}
			 var url = "/xgxt/bysbm.do?act=add";
			 
			 showTopWin(url, 650, 350);
		}
		function expTabpt(the_table, tabTit, titSpan) {
			/*var HKEY_Root="HKEY_CURRENT_USER";
			var HKEY_Path="\\Software\\Microsoft\\Internet Explorer\\PageSetup\\";
			var Wsh=new ActiveXObject("WScript.Shell");
			var HKEY_Key="header";
			    Wsh.RegWrite(HKEY_Root+HKEY_Path+HKEY_Key,"");
			    HKEY_Key="footer";
			    Wsh.RegWrite(HKEY_Root+HKEY_Path+HKEY_Key,""); */  
			var table_title = (titSpan == null || titSpan == "") ? tabTit : document.getElementById(titSpan).outerHTML;	
			var the_content = "<style media='print'>\n";
			the_content += ".noPrin{\n";
			the_content += "display:none;}\n";
			the_content += "</style>\n";
			the_content += "<link rel='stylesheet' rev='stylesheet' href='skin1/style/main.css' type='text/css' media='all' />\n";
			the_content += "<object id=\"WebBrowser\" width=0 height=0 classid=\"CLSID:8856F961-340A-11D0-A96B-00C04FD705A2\"></object>\n";
			the_content += "<center><h3><b>";
			the_content += table_title;
			the_content += "</b></h3>";		
			the_content += document.all(the_table).outerHTML;			
			the_content = the_content.replace(/ style=\"[^\"]*\"/g, "");
			the_content = the_content.replace(/ onclick=\"[^\"]*\"/g, "");
			the_content = the_content.replace(/ mode=\"(false|true)"/g, "");
			the_content = the_content.replace(/ oBgc=\"[\w#\d]*\"/g, "");
			the_content = the_content.replace(/ oFc=\"[\w#\d]*\"/g, "");
			the_content = the_content.replace(/<span>(5|6)<\/span>/gi, "");
			the_content = the_content.replace(/<DIV contentEditable=false>(.*)<\/DIV>/ig, "$1");
			the_content += "\n<br><div class='noPrin'><input type='button' class='button2' value='页面设置' onclick=\"WebBrowser.ExecWB(8,1)\">";
			the_content += "<input type='button' class='button2' value='打印预览' onclick=\"WebBrowser.ExecWB(7,1)\">";
			the_content += "<input type='button' class='button2' value='直接打印' onclick=\"WebBrowser.ExecWB(6,6)\">";
			the_content += "<\/div>";
			//confirm(the_content);
			var newwin = window.open("about:blank", "_blank", "");
			newwin.document.open();
			newwin.document.write(the_content);
			newwin.document.close();
			newwin = null;
		}
		function prtlt(){
			if(!$('tjtitle')){
				alert("查询未找到数据，请查询出数据后进行打印");
				return false;
			}
			 var bmxmdm = document.getElementById("bmxm").value;
		      var bynd = document.getElementById("bmbynd").value;
		      if(bmxmdm=="--请选择--"||bmxmdm==""){
		      alert("请选择报名项目！");
		      return false;
		      }
		      if(bynd=="--请选择--"||bynd==""){
			      alert("请选择毕业年度！");
			      return false;
			      }
			var prtname = $('tjtitle').innerText;
			expTabpt('rsTable',prtname,'');
		}
		function ifyctj(){
			//var usertype = document.getElementById("isuser").value;
			//if(usertype=="stu"){
			//	document.getElementById("xydm").disabled=true;
			//	document.getElementById("zydm").disabled=true;
			//	document.getElementById("xh").disabled=true;
			//	document.getElementById("xm").disabled=true;
			//}
		}
		</script>
	<body onload="ifyctj();">
		<html:form action="/yxjzyjs.do" method="post">
			<input type="hidden" id="realTable" name="realTable"
				value="<bean:write name='realTable' scope="request" />" />
			<input type="hidden" name="pkstring" value="" />
			<input id="userType" name="userType" value="<bean:write name="userType" scope="session"/>" type="hidden"/>
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：就业管理 - 就业信息 - 报名
				</div>
			</div>
			<fieldset>
				<legend>
					查 询
				</legend>

				<table width="100%" class="tbstyle" >
					<thead>
						<tr style="cursor:hand">
						<logic:equal value="stu" name="userType" scope="session">
							<td>
								<bean:message key="lable.xsgzyxpzxy" />：
								<input id="xhdt" name="xmdt" value="${xymcdt}" disabled="disabled"/>
								<html:select name="rs1" property="xydm" onchange="initZyList();" style="width:160px;display: none" styleId="xy">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select>
								&nbsp;&nbsp;&nbsp;
								专业：
								<input id="xhdt" name="xmdt" value="${zymcdt }" disabled="disabled"/>
								<html:select name="rs1" property="zydm" onchange="" style="width:170px;display: none" styleId="zy">
									<html:option value=""></html:option>
									<html:options collection="zyList" property="zydm"
										labelProperty="zymc" />
								</html:select>
						</logic:equal>
						
						<logic:equal value="xy" name="userType" scope="session">
							<td>
								<bean:message key="lable.xsgzyxpzxy" />：
								<input id="xhdt" name="xmdt" value="${xydmdt}" disabled="disabled"/>
								<html:select name="rs1" property="xydm" onchange="initZyList();" style="width:160px;display: none" styleId="xy">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select>
								&nbsp;&nbsp;&nbsp;
								专业：
								
								<html:select name="rs1" property="zydm" onchange="" style="width:170px;display: " styleId="zy">
									<html:option value=""></html:option>
									<html:options collection="zyList" property="zydm"
										labelProperty="zymc" />
								</html:select>
						</logic:equal>
						
						<logic:notEqual value="stu" name="userType" scope="session">
							<td>
							<logic:notEqual value="xy" name="userType" scope="session">
								<bean:message key="lable.xsgzyxpzxy" />：
								<html:select name="rs1" property="xydm" onchange="initZyList();" style="width:160px" styleId="xy">
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
							&nbsp;&nbsp;&nbsp;
								毕业年度：
								<html:select name="rs1" property="bmbynd" >
									<html:option value=""></html:option>
									<html:option value="2003">2003</html:option>
									<html:option value="2004">2004</html:option>
									<html:option value="2005">2005</html:option>
									<html:option value="2006">2006</html:option>
									<html:option value="2007">2007</html:option>
									<html:option value="2008">2008</html:option>
									<html:option value="2009">2009</html:option>
									<html:option value="2010">2010</html:option>
									<html:option value="2011">2011</html:option>
									<html:option value="2012">2012</html:option>
									<html:option value="2013">2013</html:option>
									<html:option value="2014">2014</html:option>
									<html:option value="2015">2015</html:option>
								</html:select>
								&nbsp;&nbsp;&nbsp;
								报名项目：
								<html:select name="rs1" property="bmxm" >
									<html:option value=""></html:option>
									<html:option value="考研">考研</html:option>
									<html:option value="村官">村官</html:option>
									<html:option value="社区助理">社区助理</html:option>
								</html:select>
								</td>
						
						</logic:notEqual>
							<td width="10" rowspan="2" align="center" valign="middle">
								<button class="button2" style="height:40px" onclick="querygo()"
									id="search_go">
									查询
								</button>
							</td>
						</tr>
						<tr>
						<logic:equal value="stu" name="userType" scope="session">
							<td>
								学号：
								<input type="hidden" id="isuser" name="isuser" value="<bean:write name='usertype' scope="request" />" />
								<input id="xhdt" name="xmdt" value="${rs1.xh }" disabled="disabled"/>
								<html:text name="rs1" property="xh" style="display: none"></html:text>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 姓 名：&nbsp;&nbsp;&nbsp;
								<input id="xhdt" name="xmdt" value="${rs1.xm }" disabled="disabled"/>
								<html:text name="rs1" property="xm" style="display: none"></html:text>
								&nbsp;&nbsp;&nbsp;&nbsp;性别：
								<html:select name="rs1" property="xb" style="width:80px" disabled="true">
									<html:option value=""></html:option>
									<html:option value="男">
										男
									</html:option>
									<html:option value="女">
										女
									</html:option>
								</html:select>	
							</td>
							</logic:equal>
							
							<logic:notEqual value="stu" name="userType" scope="session">
						<td>
								学号：
								<input type="hidden" id="isuser" name="isuser" value="<bean:write name='usertype' scope="request" />" />
								<html:text name="rs1" property="xh" style="display: "></html:text>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 姓 名：&nbsp;&nbsp;&nbsp;
								<html:text name="rs1" property="xm" style="display: "></html:text>
								&nbsp;&nbsp;&nbsp;&nbsp;性别：
								<html:select name="rs1" property="xb1" style="width:80px">
									<html:option value=""></html:option>
									<html:option value="男">
										男
									</html:option>
									<html:option value="女">
										女
									</html:option>
								</html:select>	
							</td>
						</logic:notEqual>
						</tr>
						<tr>
							<td colspan="2">
								<font color="blue">提示：学号、姓名支持模糊查询。</font>
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
				<legend align="center">
						<font color="blue">
							<span id="tjtitle"></span>
						</font>
					</legend>
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
								ondblclick="viewMoreinfo('view')">
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
				<logic:notEqual value="stu" name="userType" scope="session">
				<script language="javascript">
					$('tjtitle').innerText = $('bmbynd').options[$('bmbynd').selectedIndex].text+'届毕业生'+$('bmxm').options[$('bmxm').selectedIndex].text+'报名统计表';
				</script>
				</logic:notEqual>
			</logic:notEmpty>



			<div class="buttontool" id="btn"
				style="position: absolute;left:0px;top:100px" width="98%">
				&nbsp;
				<button class="button2" onclick="yxjzyjsadd();"
					style="width:80px">
					增加
				</button>
				
				<logic:notEqual name="who" value="stu">
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
					onclick="del('/xgxt/bysbm.do?act=alldel')"
					style="width:80px">
					批量删除
				</button>
				&nbsp;
				<button class="button2" onclick="dataExport()"
						style="width:80px">
						导出数据
					</button>
					&nbsp;
				<button class="button2" onclick="prtlt();" style="width: 100px">
					打印
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
