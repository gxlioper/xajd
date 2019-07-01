<%@ include file="/syscommon/pagehead.ini"%>
<%@ page language="java" contentType="text/html; charset=GBK"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
	<head>
		<title><bean:message key="lable.title" />
		</title>
	</head>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<body>

		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script language="javascript" src="js/jsFunction.js"></script>
		<script type="text/javascript" src="js/BatAlert.js"></script>
		<script language="javascript" src="js/function.js"></script>
		<script type='text/javascript'
			src='/xgxt/dwr/interface/GetListData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getTowdays.js'></script>
		<script type='text/javascript'
			src='/xgxt/dwr/interface/getScoreinfo.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
		<script language="javascript" src="js/AjaxFunction.js"></script>



		<script language="javascript">
	    function byqxquerygo(){
		 	document.forms[0].action = "jhzyjysb.do?method=jysb&go=go";
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
      function delall(url){
	    var RowsStr="!!#!!";
    
	    for (i=0; i<document.getElementsByName("pk").length; i++){
    	   if(document.getElementsByName("pk")[i].checked){
    		RowsStr+=document.getElementsByName("pk")[i].value+"!!#!!";		
    	   }
	    }
	    document.forms[0].pkstring.value = RowsStr;
	       if (RowsStr=="!!#!!"){
	         alert("请勾选需要删除的记录！");
		   return false;
    	}
	
	    if (!confirm("你确定要删除所选记录？")){
		   return false;
	    }
	    BatAlert.showTips('正在删除，请稍侯...');
	    document.forms[0].action=url;
        document.forms[0].submit();
     }   
        
           /*
	全部清空
	*/
        
        function delallinfo(url){
          if(!confirm("你确定要清空所有记录？清空后所有数据都会被删除")){
             return false;
          }
          BatAlert.showTips('正在清空列表，请稍侯...');
	      document.forms[0].action=url;
          document.forms[0].submit();
        
        
        }
        
        
		function stuinfodelete(doType){
		var url = "stuxsjyxxquery.do?doType=del&act=query&pkValue=";
		var pkValue = "";
			
		if (doType == "del") {
		   if (curr_row == null) {
			alert("请选择要删除的数据！\n（单击相应的行）");
			return false;
		    } else {
			if (confirm("确定要删除该行数据吗？")) {
			    BatAlert.showTips('正在删除，请稍侯...');
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
		
		
		function viewMoreinfo(){
		 var url ="jhzyjysb.do?method=jysbView&pkValue=";
		 var pkValue ="";
		 pkValue = curr_row.getElementsByTagName("input")[0].value;
		 url += pkValue;
		 showTopWin(url, 600,350);
	
		}
		
		function addxsjyxx(){
		   var url ="jhzyjysb.do?method=jysbadd";
		   showTopWin(url, 600, 350);	 
		}
		
		
		
		function xsjyxxupdate(doType){
		var url ="jhzyjysb.do?method=jysbupdate&pkValue=";
		var pkValue ="";
		
		 if (doType == "update"){
		    if (curr_row == null) {
			alert("请选择要修改的数据！\n（单击相应的行）");
			return false;
			 } else {
			if (confirm("确定要修改该行数据吗？")) {
				 pkValue = curr_row.getElementsByTagName("input")[0].value;
		         url += pkValue;
		         showTopWin(url,600, 350);
				return true;
			} else {
				return false;
			}
		   }
	      }		
		}
		
	    function refreshtheweb()
		{
			document.forms[0].action = "stuxsjyxxquery.do";
            document.forms[0].submit();
		}
		
		
		 function  jyglDataExport(){
	       document.forms[0].action = "/xgxt/jhzyjysb.do?method=jysbExpdata&go=expdata";
	       document.forms[0].target = "_blank";
	       document.forms[0].submit();
	       document.forms[0].target = "_self";
        }
	      /*
			审核
			*/
		  function shenhe(type){
			    var RowsStr="!!#!!";
		    	var url = "jhzyjysb.do?method=jysbXXsh&go=go&xxsh="+type;
			    for (i=0; i<document.getElementsByName("pk").length; i++){
		    	   if(document.getElementsByName("pk")[i].checked){
		    		RowsStr+=document.getElementsByName("pk")[i].value+"!!#!!";		
		    	   }
			    }
			    document.forms[0].pkstring.value = RowsStr;
			       if (RowsStr=="!!#!!"){
			         alert("请勾选需要审核的记录！");
				   return false;
		    	}
				if(type == "yes"){
					if (!confirm("你确定要 通过 所选记录？")){
						   return false;
					    }
				}else{
					if (!confirm("你确定要 不通过 所选记录？")){
						   return false;
					    }
				}
			    BatAlert.showTips('正在审核，请稍侯...');
			    document.forms[0].action=url;
		        document.forms[0].submit();
		     }   
		</script>
		<html:form action="/jhzyjysb" method="post">
			<input type="hidden" name="pkstring" value="" />
			<input type="hidden" name="zyV" id="zyV" />
			<input type="hidden" name="bjV" id="bjV" />
            <input type="hidden" id="realTable" name="realTable" value="jhzy_jysbsjszb" />
            <input type="hidden" id="querry" name="querry"
				value="" />
				<input type="hidden" id="userType" name="userType" value="${userType}" />
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：就业管理 - 学生就业信息 - 就业上报
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
								<bean:message key="lable.xsgzyxpzxy" />：
								<logic:equal value="xy" name="userType">
								<input id="xydt" name="xydt" value="${bmmc }" disabled="disabled"/>
								<html:select property="xydm" style="display: none"
									styleId="xy" onchange="initZyList();initBjList();">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select>
								</logic:equal>
								<logic:notEqual value="xy" name="userType">
								<html:select property="xydm" style=""
									styleId="xy" onchange="initZyList();initBjList();">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select>
								</logic:notEqual>
								专业：
								<html:select property="zydm" onchange="initBjList()" 
								styleClass="select" styleId="zy">
									<html:option value=""></html:option>
									<html:options collection="zyList" property="zydm"
										labelProperty="zymc" />
								</html:select>
								
							</td>
							<td width="10" rowspan="2" align="center" valign="middle">
								<button class="button2" style="height:40px"
									onclick="byqxquerygo()" id="search_go">
									查询
								</button>
							</td>
						</tr>
						<tr>
							<td>
								毕业年份：
								<html:select property="bynf" style="width:130px"
												styleId="bynf" onchange="">
									<html:options collection="ndList" property="nd" labelProperty="nd" />
								</html:select>
								学历：
								<html:select property="xl">
									<html:option value=""></html:option>
									<html:option value="本科生">本科生</html:option>
									<html:option value="专科生">专科生</html:option>
									<html:option value="高职生">高职生</html:option>
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
								<logic:iterate id="tit" name="topTr" offset="1">
									<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)">
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<logic:iterate name="rs" id="s">
								<tr onclick="rowOnClick(this)" style="cursor: hand;"
									ondblclick="viewMoreinfo();">
								<td>
										<input type="checkbox" name="pk"
										value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>" />
								</td>
								<logic:iterate id="v" name="s" offset="1">
									<td align=center nowrap="nowrap">
										<bean:write name="v" />
									</td>
								</logic:iterate>
								</tr>
							</logic:iterate>
						
					</table>
					<TABLE width="100%" id="Table" class="tbstyle">
						<TR>
							<TD>
								<jsp:include flush="true"
									page="/sjcz/turnpage.jsp?form=jhzyForm"></jsp:include>
							</TD>
						</TR>
					</TABLE>

				</fieldset>
			</logic:notEmpty>



			<div class="buttontool" id="btn"
				style="position: absolute;left:0px;top:100px" width="100%">
				&nbsp;&nbsp;&nbsp;&nbsp;
				<logic:equal value="xx" name="userType">
				<button class="button2" id="buttonYes" onclick="shenhe('yes')"
						style="width: 80px">
						通  过
					</button> &nbsp;&nbsp;&nbsp;&nbsp;
					<button class="button2" id="buttonNo" onclick="shenhe('no')"
						style="width: 80px">
						不通过
					</button> &nbsp;&nbsp;&nbsp;&nbsp;
					</logic:equal>
				<button class="button2" onclick="addxsjyxx()" style="width:80px">
					增 加
				</button>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button class="button2" onclick="xsjyxxupdate('update')"
					style="width:80px">
					修 改
				</button>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button class="button2"
					onclick="delall('jhzyjysb.do?method=jysbDel&go=go');"
					style="width:80px">
					批量删除
				</button>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button class="button2"
					onclick="delallinfo('jhzyjysb.do?method=jysbDelAll&go=go');"
					style="width:80px">
					全部清空
				</button>
				&nbsp;&nbsp;&nbsp;&nbsp;
<%--				<button class="button2"--%>
<%--					onclick="sh('jhzyjysb.do?method=jysbDelAll&go=go');"--%>
<%--					style="width:80px">--%>
<%--					审核--%>
<%--				</button>--%>
				<logic:equal value="xx" name="userType">
					<button class="button2" onclick="jyglDataExport()"
						style="width:80px">
						导出数据
					</button>
				</logic:equal>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<logic:equal name="who" value="xx1">
					<button class="button2"
						onclick="impAndChkData();"
						style="width:80px">
						导入数据
					</button>
				</logic:equal>
			</div>
			<button onclick="refreshtheweb()" id="search_go" style="display: none" ></button>
				<logic:notEmpty name="delete">
					<logic:equal name="delete" value="ok">
						<script>
                      alert("删除成功!");
                    </script>
					</logic:equal>
					<logic:equal name="delete" value="no">
						<script>
                      alert("删除失败！");
                    </script>
					</logic:equal>
				</logic:notEmpty>
				<logic:notEmpty name="deleted">
					<logic:equal name="deleted" value="yes">
						<script>
                      alert("删除成功!");
                    </script>
					</logic:equal>
					<logic:equal name="deleted" value="no">
						<script>
                      alert("删除失败！");
                    </script>
					</logic:equal>
				</logic:notEmpty>
				<logic:notEmpty name="xxsh">
					<logic:equal name="xxsh" value="yes">
						<script>
                      alert("审核成功!");
                    </script>
					</logic:equal>
					<logic:equal name="xxsh" value="no">
						<script>
                      alert("审核失败！");
                    </script>
					</logic:equal>
				</logic:notEmpty>
				<logic:notEmpty name="delallinfo">
					<logic:equal name="delallinfo" value="ok">
						<script>
                      alert("全部内容已清空！");
                    </script>
					</logic:equal>
					<logic:equal name="delallinfo" value="no">
						<script>
                      alert("清空列表失败！");
                    </script>
					</logic:equal>
				</logic:notEmpty>
		</html:form>
		<script type="text/javascript" src="js/bottomButton.js"></script>

	</body>
</html>
