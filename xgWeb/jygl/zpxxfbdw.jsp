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
		<script language="javascript" src="js/AjaxFunction.js"></script>
	<script type="text/javascript"
		src="/xgxt/dwr/interface/getStuDetails.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/util.js"></script>


		<script language="javascript">
		function loadShi(){
		var shen = document.getElementById("jgshen").value;	
		getStuDetails.getShiList(shen,function(data){
			if (data.shiList != null) {
					var objId = "jgshi";				
					if($(objId) && $(objId).tagName.toLowerCase() == "select"){
						DWRUtil.removeAllOptions(objId);					
						DWRUtil.addOptions(objId,data.shiList,"shidm","shimc");
					}
				}else{
					showMsgWin("有错误出现：远程数据读取失败！");
				}	
			if (data.xianList !=null){
				var objId = "jgxian";
					if($(objId) && $(objId).tagName.toLowerCase() == "select"){
						DWRUtil.removeAllOptions(objId);					
						DWRUtil.addOptions(objId,data.xianList,"xiandm","xianmc");
					}
			}
		});
	}	
		
	    function byqxquerygo(){
		 	document.forms[0].action = "zpxxdw.do?act=query";
		 	document.forms[0].submit();
        }
        function sendMoreinfo(){
			var url ="dwxxInput.do?pkValue=";
		 	var pkValue ="";
			 pkValue = curr_row.getElementsByTagName("input")[0].value;
			 url += pkValue+"&act=go&sendgo=sendgo";
		 	 window.dialogArguments.document.forms[0].action = url;
		     window.dialogArguments.document.forms[0].submit();
		     window.close();
        }
		</script>
		<html:form action="/dwxxInput" method="post">
			<input type="hidden" name="pkstring" value="" />
			<input type="hidden" name="zyV" id="zyV" />
			<input type="hidden" name="bjV" id="bjV" />
            <input type="hidden" id="realTable" name="realTable"
				value="<bean:write name='realTable' scope="request" />" />
            <input type="hidden" id="querry" name="querry"
				value="<bean:write name='querry' scope="request" />" />
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：就业管理 - 模块维护 - 单位信息模块的维护
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

								单位名称：
								<html:text property="dwmc"/>
								
								&nbsp;主管部门：
								<html:select name="rs1" property="zgbm" styleId="zgbm"
									style="width:120px">
									<html:option value=""></html:option>
									<html:options collection="sydzgbmList" property="sydzgbm"
										labelProperty="sydzgbm" />
								</html:select>
								所属行业：
								<html:select name="rs1" property="hyfl" styleId="sshy" style="width:135px">
									<html:option value=""></html:option>
									<html:options collection="hyflList" property="hyfl" labelProperty="hyfl" />
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
							所在地区：
							<html:select property="szdqsh" onchange="loadShi()"
								styleId="jgshen">
								<html:option value="">--请选择--</html:option>
								<html:options collection="ssList" property="ssdm"
									labelProperty="ssmc" />
							</html:select>
							<html:select property="szdqsi" styleId="jgshi">
								<html:options collection="shiList" property="shidm"
									labelProperty="shimc" />
							</html:select>
							单位性质：
								<html:select name="rs1" property="dwxz" styleId="dwxz">
								<html:option value=""></html:option>
								<html:options collection="dwxxdm" property="dwxz"/>
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
										onclick="tableSort(this)" nowrap>
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<logic:iterate name="rs" id="s">
							<tr onclick="rowOnClick(this)" style="cursor:hand"
								ondblclick="sendMoreinfo();" align="center">
								<td>
								<input type="hidden" name="pk"
									value="<bean:write name="s" property="dwid" />" />
								<input type="checkbox" name="pk"
									value="<bean:write name="s" property="dwid"/>" />
								</td>
								<td>
									<bean:write name="s" property="dwmc" />
								</td>
								<td>
									<bean:write name="s" property="zgbm" />
								</td>
								<td>
									<bean:write name="s" property="clrq" />
								</td>
								<td>
									<bean:write name="s" property="lxbm" />
								</td>
								<td>
									<bean:write name="s" property="lxr" />
								</td>
								<td>
									<bean:write name="s" property="lxdh" />
								</td>
							</tr>
						</logic:iterate>
					</table>
					<TABLE width="100%" id="Table" class="tbstyle">
						<TR>
							<TD>
								<jsp:include flush="true"
									page="/sjcz/turnpage.jsp?form=commanForm"></jsp:include>
							</TD>
						</TR>
					</TABLE>

				</fieldset>
			</logic:notEmpty>



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
