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
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<body onload="xyDisabled('xy');">
		<script language="javascript" src="js/function.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script language="javascript" src="js/AjaxFunction.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
	<script language="javascript" src="/xgxt/pjpy/xibeierminyuan/js/xbemyJs.js"></script>
	<script language="" src="pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/chgJxjlist.js'></script>
	<script type="text/javascript">
		function bgdgl() {
			if (curr_row==null) {
				alert('请选择要操作的数据行!');
				return;
			} else {
				var count=0;
				var chklist =document.getElementsByName("cbv");
				for (var i=0;i<chklist.length;i++) {
					if (chklist[i].checked) {
						count = count+1;
					}
				}
				if (count>1) {
					alert('只能操作单条数据!');
					return;
				} else {
					//var xh = curr_row.cells[2].innerText;
					//chgJxjlist.chkStuIsBys(xh,function(data) {
						//if (data) {
							showTopWin('wjcf_shgc_bgdadd.do?tz=true&pk='+curr_row.cells[0].getElementsByTagName("input")[0].value,600,500)
					//	} else {
						//	alert('该生不符合不归档审批条件,只有毕业生才符合此条件,请重新核对!');
							//return;
					//	}
					//});
				}
			}
		}
	</script>
		<html:form action="/shgcwjcfwh" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：
					当前位置：违纪处分 - 数据维护 - 考试违纪数据维护
				</div>
			</div>
			<input type="hidden" id="userType" name="userType"
				value="<bean:write name="userType" scope="request"/>" />
			<input type="hidden" id="tableName" name="tableName" value="${tableName }"/>
			<input type="hidden"  id="realTable" name="realTable" value="${realTable }"/>
			<input type="hidden" id="failinfo" name="failinfo" value="${failinfo }"/>
			<input type="hidden" id="bmlb" name="bmlb" value="xy" />
			<input type="hidden" name="zyV" id="zyV" value=""/>
			<input type="hidden" name="bjV" id="bjV" value=""/>
			<input type="hidden" id="tzurl" name="tzurl" onclick="refreshForm('wjcf_shgc_kswjbgdqry.do');"/>
			<fieldset>
				<legend>
					查询条件
				</legend>
				<table width="100%" class="tbstyle">
					<thead>
						<tr>
							<td align="left">
								学年：
								<html:select property="xn" style="width:110px" 
									styleId="xn">
									<html:options collection="xnList" property="xn"
										labelProperty="xn" />
								</html:select>
								&nbsp;&nbsp;年度：
								<html:select property="nd" style="width:90px" 
									styleId="nd">
									<html:options collection="xnList" property="nd"
										labelProperty="nd" />
								</html:select>
								&nbsp;&nbsp;年级：
								<html:select property="nj" style="width:90px;padding-left:80px" onchange="initZyList();initBjList()">
									<html:option value=""></html:option>
									<html:options collection="njList" property="nj"
										labelProperty="nj" />
								</html:select>
								&nbsp;&nbsp;学号：
								<html:text property="xh" styleId="xh" style="width:90px;inputtext"></html:text>
								&nbsp;&nbsp;姓名：
								<html:text property="xm" styleId="xm" style="width:90px;inputtext"></html:text>
							</td>
							<td width="10" rowspan="3" align="center" valign="middle">
								<input type="hidden" name="go" value="a" />
								<button type="button" class="button2" style="height:40px" id="search_go"
									onclick="allNotEmpThenGo('wjcf_shgc_kswjqry.do')">
									查询
								</button>
							</td>
						</tr>
						<tr>
							<td align="left" nowrap>
								<bean:message key="lable.xsgzyxpzxy" />：
								<html:select property="xydm" style="width:220px" styleId="xy" onchange="initZyList();initBjList()">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select>
								&nbsp;&nbsp;专业：
								<html:select property="zydm" style="width:200px" styleId="zy" onchange="initBjList()">
									<html:option value=""></html:option>
									<html:options collection="zyList" property="zydm" 
									labelProperty="zymc"/>
								</html:select>
								&nbsp;&nbsp;班级：
								<html:select property="bjdm" styleId="bj" style="width:170px">
									<html:option value=""></html:option>
									<html:options collection="bjList" property="bjdm" labelProperty="bjmc"/>
								</html:select>
							</td>
						</tr>
						<Tr>
							<td align="left" nowrap>
								处分原因:
								<html:select property="cfyy" style="width:115px"
								styleId="cfyy">
								<html:option value=""></html:option>
								<html:options collection="kscfyyList" property="cfyydm"
									labelProperty="cfyymc" />
							</html:select>
							</td>
						</Tr>
					</thead>
				</table>
			</fieldset>
			<logic:empty name="rs">
						<p align="center">
							未找到任何记录！
						</p>
					</logic:empty>
					<logic:notEmpty name="rs">
						<fieldset>
							<legend>
								记录数：
								${rsNum}
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<font color="blue">提示：双击一行可以查看详细信息;单击表头可以进行排序;</font>
							</legend>
							<table width="99%" id="rsTable" class="tbstyle">
								<thead>
									<tr align="center" style="cursor:hand">
									    <td nowrap>
											<input type="checkbox" id="cb" name="cb" onclick="selectAll()" />
										</td>
										<logic:iterate id="tit" name="topTr" offset="1" scope="request">
											<td id="${tit.en}"
												onclick="tableSort(this)" nowrap>
												${tit.cn}
											</td>
										</logic:iterate>
									</tr>
								</thead>
								<logic:iterate name="rs" id="s">
									<tr onclick="rowOnClick(this)"
										style="cursor:hand;" ondblclick="modiAndDel('wjcf_shgc_kswjmodi.do?act=view&pkValue=','modi','700','620');">
										<td align=center><input type="checkbox" id="cbv" name="cbv"
										 value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>"/>
									    </td>
										<logic:iterate id="v" name="s" offset="1">
											<td align=center>
												<bean:write name="v" />
											</td>
										</logic:iterate>
									</tr>
								</logic:iterate>
							</table>
						</fieldset>
					</logic:notEmpty>
			<div class="buttontool" align="center" id="btn" style="position:absolute;width:95%;top:100px">
				<button type="button" class="button2" id="btn_add" style="width:80px"
								onclick="showTopWin('wjcf_shgc_kswjadd.do',700,620)">
								增加
							</button>
							&nbsp;&nbsp;&nbsp;
							<button type="button" class="button2" id="btn_modi" style="width:80px"
								onclick="modiAndDel('wjcf_shgc_kswjmodi.do?pkValue=','modi','700','620');">
								修改
							</button>
							&nbsp;&nbsp;&nbsp;
							<button type="button" class="button2" id="btn_del" style="width:80px"
								onclick="deldata('wjcf_shgc_kswjdel.do')">
								删除
							</button>
							&nbsp;&nbsp;&nbsp;
							<button type="button" class="button2"
								onclick="impAndChkData();"
								style="width:80px">
										导入数据
									</button>
							&nbsp;&nbsp;&nbsp;
							<button type="button" class="button2" onclick="dataExport()" style="width:80px">
									导出数据
								</button>
								
							&nbsp;&nbsp;&nbsp;
							<button type="button" class="button2" onclick="wjcfysbprint('kswjprint.do?pkVal=')" style="width:90px">
								打印/预览
							</button>
							&nbsp;&nbsp;&nbsp;
							<button type="button" class="button2" onclick="bgdgl()" title="进入考试违纪处分不归档增加页面" >
								处分不归档管理
							</button>
							&nbsp;&nbsp;&nbsp;
								<a href="xlsDown/shgc_kswjcfb.xls"
										target="_blank">模板下载</a>
					</div>
			<script type="text/javascript" src="js/bottomButton.js"></script>
			<logic:present name="deleted">
				<logic:equal value="yes" name="deleted">
					<script>
						alert('操作成功!');
						document.getElementById('search_go').click();
					</script>
				</logic:equal>
				<logic:equal value="no" name="deleted">
					<script>
						alert(''+document.getElementById('failinfo').value);
						document.getElementById('search_go').click();
					</script>
				</logic:equal>
			</logic:present>
			<script language="javascript">
		function wjcfysbprint(url){
			if (curr_row == null){
				window.open(url);
			}else {
				var pkVal = curr_row.cells[0].getElementsByTagName("input")[0].value;
				window.open(url+pkVal);
			}
			}
			document.getElementById("btn").style.pixelTop = document.body.clientHeight - 35;
			document.getElementById("btn").style.width = "96%";
			window.setInterval("initBTNTool('btn')",1);
		</script>
		</html:form>
		</body>
</html>
