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
	function querygo(){
		 	document.forms[0].action = "/xgxt/bysjbxxquerydododo.do?act=go&doType=query";
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
		var url = "/xgxt/JyglBysjbxxbDel.do?doType=del&doType2=query&pkValue=";
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
		var url ="/xgxt/JyglViewMoreinfo.do?doType=view&pkValue=";
		var pkValue ="";
		
		 if (doType == "view"){
		 pkValue = curr_row.getElementsByTagName("input")[0].value;
		 url += pkValue;
		 showTopWin(url, 700, 500);
		 }
		}
		function bysjbxxbupdate(doType){
		var url ="/xgxt/turnJyglInfoUpdate.do?doType=update&pkValue=";
		var pkValue ="";
		
		 if (doType == "update"){
		    if (curr_row == null) {
			alert("请选择要修改的数据！\n（单击相应的行）");
			return false;
			 } else {
			if (confirm("确定要修改该行数据吗？")) {
				 pkValue = curr_row.getElementsByTagName("input")[0].value;
		         url += pkValue;
		         showTopWin(url, 700, 600);
				return true;
			} else {
				return false;
			}
		   }
	      }		
		}
		
		
		
		function jyglxsjbxxbDataExport() {
	       document.forms[0].action = "/xgxt/jyglxsjbxxbDataExport.do?tableName=jygl_xsjbxxb";
	       document.forms[0].target = "_blank";
	       document.forms[0].submit();
	       document.forms[0].target = "_self";
        }
		
		</script>
	<body>
		<html:form action="/data_search" method="post">
			<input type="hidden" id="realTable" name="realTable"
				value="<bean:write name='realTable' scope="request" />" />
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
								<bean:message key="lable.xsgzyxpzxy" />：
								<html:select name="rs1" property="xymc" style="width:150px">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xymc"
											labelProperty="xymc" />
									</html:select>
								&nbsp;&nbsp;&nbsp;
								入学年度：
								<html:select name="rs1" property="nd" style="width:150px">
									<html:option value=""></html:option>
									<html:options collection="ndList" property="nj"
										labelProperty="nj" />
								</html:select>
								&nbsp;&nbsp;&nbsp;&nbsp;毕业年度：
								<html:select name="rs1" property="bynd" style="width:150px">
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
							<td width="10" rowspan="2" align="center" valign="middle">
								<button class="button2" style="height:40px" onclick="querygo()"
									id="query_go">
									查询
								</button>
							</td>
						</tr>
						<tr>
							<td>
								学号：
								<input type="text" name="xsxh"
									value="<bean:write name="rs1" property="xsxh"/>" />
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 姓 名：&nbsp;&nbsp;&nbsp;
								<input type="text" name="name"
									value="<bean:write name="rs1" property="name"/>" />
								&nbsp;&nbsp;&nbsp;&nbsp;性别：
								<html:select name="rs1" property="xb" style="width:80px">
									<html:option value=""></html:option>
									<html:option value="男">
										男
									</html:option>
									<html:option value="女">
										女
									</html:option>
								</html:select>
							</td>
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
										onclick="tableSort(this)" nowrap>
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
								<logic:iterate id="v" name="s" offset="1">
									<td align="center">
										<bean:write name="v" />
									</td>
								</logic:iterate>
							</tr>
						</logic:iterate>
					</table>
				</fieldset>
			</logic:notEmpty>



			<div class="buttontool" id="btn"
				style="position: absolute;left:0px;top:100px" width="100%">
				<button class="button2"
					onclick="del('/xgxt/JyglBysjbxxbDelall.do?doType2=query&doType=delall')"
					style="width:80px">
					批量删除
				</button>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button class="button2" onclick="bysjbxxbupdate('update')"
					style="width:80px">
					修 改
				</button>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button class="button2" onclick="bysjbxxbdel('del')"
					style="width:80px">
					删 除
				</button>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button class="button2"
						onclick="impAndChkData();"
						style="width:80px">
						导入数据
					</button>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button class="button2" onclick="jyglxsjbxxbDataExport()"
						style="width:80px">
						导出数据
					</button>
			</div>

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
			<logic:notEmpty name="delall">
				<logic:equal name="delall" value="ok">
					<script>
                      alert("批量删除成功!");
                    </script>
				</logic:equal>
				<logic:equal name="delall" value="no">
					<script>
                      alert("批量删除失败");
                    </script>
				</logic:equal>
			</logic:notEmpty>
		</html:form>
		<script type="text/javascript" src="js/bottomButton.js"></script>
	</body>
</html>
