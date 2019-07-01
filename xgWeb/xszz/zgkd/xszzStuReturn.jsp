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
	    function hkxxquerygo(){
		 	document.forms[0].action = "zgkydx_xszz_hkgl.do?act=go&method=hkgl";
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
          if(!confirm("你确定要清空所有记录？")){
             return false;
          }
          BatAlert.showTips('正在清空列表，请稍侯...');
	      document.forms[0].action=url;
          document.forms[0].submit();
        
        
        }
        
        
		function stuinfodelete(doType){
		var url = "zgkydx_xszz_hkgl.do?act=go&method=hkgl&doType=del&pkValue=";
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
		 var url ="zgkydx_xszz_hkgl.do?method=hkglviewmore&pkValue=";
		 var pkValue ="";
		 pkValue = curr_row.getElementsByTagName("input")[0].value;
		 url += pkValue;
		 showTopWin(url, 600,430);
	
		}
		
		
		function hkglupdate(doType){
		var url ="zgkydx_xszz_hkgl.do?method=hkglupdate&pkValue=";
		var pkValue ="";
		
		 if (doType == "update"){
		    if (curr_row == null) {
			alert("请选择要修改的数据！\n（单击相应的行）");
			return false;
			 } else {
			if (confirm("确定要修改该行数据吗？")) {
				 pkValue = curr_row.getElementsByTagName("input")[0].value;
		         url += pkValue;
		         showTopWin(url, 670, 480);
				return true;
			} else {
				return false;
			}
		   }
	      }		
		}
		
	    function refreshtheweb()
		{
			document.forms[0].action = "zgkydx_xszz_hkgl.do?act=go&method=hkgl";
            document.forms[0].submit();
		}
		
		
		 function  hkglDataExport(){
	       document.forms[0].action = "zgkydx_xszz_hkgl.do?method=hkglexpData&tableName=view_xszz_zgkd_xshkxxb";
	       document.forms[0].target = "_blank";
	       document.forms[0].submit();
	       document.forms[0].target = "_self";
        }
		</script>
		<html:form action="/zgkydx_xszz_hkgl" method="post">
			<input type="hidden" name="pkstring" value="" />
			<input type="hidden" name="zyV" id="zyV" />
			<input type="hidden" name="bjV" id="bjV" />
			<input type="hidden" id="realTable" name="realTable"
				value="<bean:write name='realTable' scope="request" />" />
			<input type="hidden" id="realTable2" name="realTable2"
				value="<bean:write name='realTable2'  scope="request" />" />
			<input type="hidden" id="querry" name="querry"
				value="<bean:write name='querry' scope="request" />" />
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：学生资助 - 学生还款管理 - 学生还款信息维护
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
								&nbsp;&nbsp;&nbsp;姓名：
								<input type="text" name="xm"
									value="<bean:write name="rs1" property="xm"/>"
									style="width:100px" />
								&nbsp;&nbsp;&nbsp;合同号：
								<input type="text" name="hth"
									value="<bean:write name="rs1" property="hth"/>"
									style="width:100px" />
								&nbsp;&nbsp;&nbsp;年级：
								<html:select name="rs1" property="nj" style="width:70px"
									onchange="refreshForm('zgkydx_xszz_hkgl.do?method=hkgl')">
									<html:option value=""></html:option>
									<html:options collection="njList" property="nj"
										labelProperty="nj" />
								</html:select>
							</td>
							<td width="10" rowspan="2" align="center" valign="middle">
								<button type="button" class="button2" style="height:40px"
									onclick="hkxxquerygo()" id="search_go">
									查询
								</button>
							</td>
						</tr>
						<tr>
							<td>
								<bean:message key="lable.xsgzyxpzxy" />：
								<logic:equal name="who" value="xx">
									<html:select name="rs1" property="xydm" style="width:130px"
										styleId="xy" onchange="initZyList();initBjList();">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select>
								</logic:equal>
								<logic:equal name="who" value="xy">
									<html:select name="rs1" property="xydm" style="width:130px"
										styleId="xy" disabled="true">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select>
								</logic:equal>
								&nbsp;&nbsp;专业：
								<html:select name="rs1" property="zydm" style="width:130px"
									styleId="zy" onchange="initBjList()">
									<html:option value=""></html:option>
									<html:options collection="zyList" property="zydm"
										labelProperty="zymc" />
								</html:select>
								&nbsp;&nbsp;班级：
								<html:select name="rs1" property="bjdm" style="width:130px"
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
						<logic:iterate name="rs" id="s">
							<tr onclick="rowOnClick(this)" style="cursor:hand"
								ondblclick="viewMoreinfo();" align="center">
								<td>
								<input type="hidden" name="pk"
									value="<bean:write name="s"  property="xh"/>" />
									<input type="checkbox" name="pk"
										value="<bean:write name="s" property="xh"/>" />
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
									<bean:write name="s" property="hth" />
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



			<div class="buttontool" id="btn"
				style="position: absolute;left:0px;top:100px" width="100%">
				<button type="button" class="button2" onclick="hkglupdate('update')"
					style="width:80px">
					修 改
				</button>
				&nbsp;&nbsp;
				<button type="button" class="button2" onclick="stuinfodelete('del')"
					style="width:80px">
					删 除
				</button>
				&nbsp;&nbsp;
				<button type="button" class="button2"
					onclick="delall('zgkydx_xszz_hkgl.do?act=go&method=hkgl&doType=delall');"
					style="width:80px">
					批量删除
				</button>
				&nbsp;&nbsp;
				<button type="button" class="button2"
					onclick="delallinfo('zgkydx_xszz_hkgl.do?act=go&doType=delallhtinfo&method=hkgl');"
					style="width:80px">
					清空合同表
				</button>
				&nbsp;&nbsp;
				<button type="button" class="button2"
					onclick="delallinfo('zgkydx_xszz_hkgl.do?act=go&doType=delallhkinfo&method=hkgl');"
					style="width:95px">
					清空还款信息表
				</button>
				&nbsp;&nbsp;
				<logic:equal name="who" value="xx">
					<button type="button" class="button2"
						onclick="impAndChkData();"
						style="width:100px">
						合同信息表导入
					</button>
					&nbsp;&nbsp;
					<button type="button" class="button2"
						onclick="showTopWin('/xgxt/data_import.do?act=realTable2',600,480)"
						style="width:100px">
						还款信息表导入
					</button>
					&nbsp;&nbsp;
					<button type="button" class="button2" onclick="hkglDataExport()"
						style="width:80px">
						导出数据
					</button>
				</logic:equal>
			</div>
			<button type="button" onclick="refreshtheweb()" id="search_go"
				style="display: none">
			</button>
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
				<logic:notEmpty name="delall">
					<logic:equal name="delall" value="ok">
						<script>
                      alert("批量删除成功!");
                    </script>
					</logic:equal>
					<logic:equal name="delall" value="no">
						<script>
                      alert("批量删除失败！");
                    </script>
					</logic:equal>
				</logic:notEmpty>
				<logic:notEmpty name="delallhtinfo">
					<logic:equal name="delallhtinfo" value="ok">
						<script>
                      alert("合同信息表全部内容已清空！");
                    </script>
					</logic:equal>
					<logic:equal name="delallhtinfo" value="no">
						<script>
                      alert("合同信息表清空列表失败！");
                    </script>
					</logic:equal>
				</logic:notEmpty>
				<logic:notEmpty name="delallhkinfo">
					<logic:equal name="delallhkinfo" value="ok">
						<script>
                      alert("还款信息表全部内容已清空！");
                    </script>
					</logic:equal>
					<logic:equal name="delallinfo" value="no">
						<script>
                      alert("还款信息表清空列表失败！");
                    </script>
					</logic:equal>
				</logic:notEmpty>
		</html:form>
		<script type="text/javascript" src="js/bottomButton.js"></script>

	</body>
</html>
