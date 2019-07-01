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
<%@ include file="/syscommon/pagehead_V4.ini"%>
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
		<script>   
var mb = 1;
function colorOn(){
	for(i = 0;i<mbT.rows.length;i++){
		for(j = 0;j<mbT.rows[i].cells.length;j++){
			document.all.mbT.rows[i].cells[j].style.backgroundColor = "#FFFFFF";
			document.all.mbT.rows[i].cells[mb].style.backgroundColor = "#ffdead";
		}
	}
}
function printZS(mod){
    if (curr_row==null || curr_row=='') {
		alert("请选择要打印的学生！\n（单击相应的行）");
		return false;
    }
	if(mb >3 || mb <0){
		mb = 0;
		colorOn();
	}
        var xh = "";
        if(curr_row != null){
            xh = replaceChar(curr_row.cells[1].innerText," ","");
        }       
	    var rqvar = new Date();
	    var mon = rqvar.getMonth()+'';
	    var day = rqvar.getDay()+'';
	    mon = (mon.length==1)?"0"+mon:mon;
	    day = (day.length==1)?"0"+day:day;
		var rq =  rqvar.getYear()+"-"+mon+"-"+day;
		
		var xn = document.getElementById("xn").value;
		var fzrq = prompt(" 请输入颁证日期：(日期格式为2008-08-08)",rq);
		window.open("csmz_sztzprintOne.do?mb="+mb+"&fzrq="+fzrq+"&xh="+xh+"&xn="+xn);
}
</script>
	</head>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<style media="print">
.noprint{
	display:none;
}
.print{
	display:block;
}
</style>
	<body onload="colorOn()">
		<script language="javascript" src="js/function.js"></script>
		<script type='text/javascript'
			src='/xgxt/dwr/interface/GetListData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
		<script language="javascript" src="js/AjaxFunction.js"></script>
		<html:form action="/sztz_print" method="post">
			<input type="hidden" name="zyV" id="zyV" />
			<input type="hidden" name="bjV" id="bjV" />
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>${tips }</a>
				</p>
			</div>
			<div class="toolbox">
			 <!-- 按钮 -->
				 <div class="buttonbox">
			   		 <ul>
			     		 <li> <a href="#" onclick="printZS(0)" class="btn_dy"> 打印测试页 </a> </li>
						<li> <a href="#" onclick="printZS(1)" class="btn_dy"> 单个打印 </a> </li>
			   		 </ul>
		 		</div>
			<div class="searchtab">
			<table width="100%" border="0">
			      <tfoot>
			        <tr>
			          <td colspan="8">
			           	 <div class="btn">
					           <input type="hidden" name="go" id="go" value="a" />
					              <button class="btn_cx" id="search_go" 
					              	onclick="sztzlistPriseConf('/xgxt/sztz_print.do')">
					              	查 询
					              </button>
					              &nbsp;
					              <button class="btn_cz" id="btn_cz"  	onclick="searchReset();return false;">
					              	重 置
					              </button>
					           	 </div>
					          	</td>
					       	 	</tr>
					     	   </tfoot>
						  <tbody>
								<tr>
									<th align="left">
										年级
									</th>
									<td>
										<html:select property="nj" style="width:80px"
											onchange="initZyList();initBjList()"
											style="background-color:#FFFFFF">
											<html:option value=""></html:option>
											<html:options collection="njList" property="nj"
												labelProperty="nj" />
										</html:select>
									</td>
									<th>
										学年
									</th>
									<td>
										<html:select name="rsa" property="xn"
											style="width:120px;background-color:#FFFFFF" styleId="xn"
											disabled="true">
											<html:options collection="xnList" property="xn"
												labelProperty="xn" />
										</html:select>
									</td>
									<th>
										拓展科目
									</th>
									<td>
										<html:select property="kmdm"
											style="width:150px;background-color:#FFFFFF" styleId="kmdm">
											<html:option value=""></html:option>
											<html:options collection="kmList" property="kmdm"
												labelProperty="kmm" />
										</html:select>
									</td>
										<%--										<input type="hidden" value="a" id="nj" name="nj">--%>
										<input type="hidden" value="<bean:write name="sj"/>" id="sj"
											name="sj">
										<input type="checkbox" style="display:none" id="chgMode">
									<th>
										学号
									</th>
									<td>
										<html:text property="xh" styleId="xh" style="width:85px"></html:text>
									</td>
								</tr>
								<tr>
									<th align="left" nowrap>
										<bean:message key="lable.xsgzyxpzxy" />
									</th>
									<td>
										<html:select property="xydm" styleId="xy" style="width=150px"
											onchange="initZyList();initBjList()">
											<html:option value=""></html:option>
											<html:options collection="xyList" property="xydm"
												labelProperty="xymc" />
										</html:select>
									</td>
									<th>
										专业
									</th>
									<td>
										<html:select property="zydm" styleId="zy" style="width=150px"
											onchange="initBjList()">
											<html:option value=""></html:option>
											<html:options collection="zyList" property="zydm"
												labelProperty="zymc" />
										</html:select>
									</td>
									<th>
										班级
									</th>
									<td>
										<html:select property="bjdm" style="width=150px"
											style="width:150px;background-color:#FFFFFF" styleId="bj">
											<html:option value=""></html:option>
											<html:options collection="bjList" property="bjdm"
												labelProperty="bjmc" />
										</html:select>

									</td>
									<td>
									</td>
									<td>
									</td>
							</tr>
						</tbody>
					</table>
			</div>
			</div>
			<div class="noprint">
				<fieldset>
					<legend align="center">
						打印区----模板选择
					</legend>
					<table class="tbstyle" align="center" width="100%" height="150px"
						id="mbT">
						<tr>
							<!-- <td bgcolor="#FFFFFF" rowspan="2" width="5%" align="center" style="cursor:hand" onclick="mb=0;colorOn();"> </td> -->
							<td bgcolor="#FFFFFF" width="33%" align="center">
							</td>
							<td bgcolor="#FFFFFF" width="34%" align="center"
								style="cursor:hand" onclick="mb=1;colorOn();">
								<logic:equal value="13108" name="xxdm">
									<img src="zsdyjt/sztz1.jpg" height="150px" border="1" />
								</logic:equal>
								<logic:equal value="10827" name="xxdm">
									<img src="zsdyjt/sztz_csmz.jpg" height="150px" border="1" />
								</logic:equal>
							</td>
							<td bgcolor="#FFFFFF" width="33%" align="center">
							</td>
							<!-- <td bgcolor="#FFFFFF" rowspan="2" width="5%" align="center" style="cursor:hand" onclick="mb=2;colorOn();"> </td> -->
						</tr>
						<tr>
							<td bgcolor="#FFFFFF" align="center">
							</td>
							<td bgcolor="#FFFFFF" align="center" style="cursor:hand"
								onclick="mb=1;colorOn();">
								模板
							</td>
							<td bgcolor="#FFFFFF" align="center">
							</td>
						</tr>
					</table>
					</fieldset>
			</div>
			
			<div class="formbox">
			    <h3 class="datetitle_01">
			    <span>
			    	查询结果&nbsp;&nbsp;
			    	<logic:empty name="rs">
						<font color="red">未找到任何记录！</font> 
			 		 </logic:empty>
			 		 <logic:notEmpty name="rs">
			 		 	记录数：
						<bean:write name="rsNum" />
			 		 </logic:notEmpty>
			       </span>
			       </h3>
					<logic:notEmpty name="rs">
						 <table summary="" class="dateline" align="" width="100%">
								<thead>
									<tr align="center" style="cursor:hand">
										<logic:iterate id="tit" name="topTr">
											<td id="<bean:write name="tit" property="en"/>"
												onclick="tableSort(this)" nowrap>
												<bean:write name="tit" property="cn" />
											</td>
										</logic:iterate>
									</tr>
								</thead>
								<tbody>
								<logic:iterate name="rs" id="s">
									<tr onclick="rowOnClick(this)" style="cursor:hand;">
										<logic:iterate id="v" name="s">
											<td>
												<bean:write name="v" />
											</td>
										</logic:iterate>
									</tr>
								</logic:iterate>
								</tbody>
							</table>
							  <jsp:include flush="true" page="/sjcz/turnpage.jsp?form=sztzForm"></jsp:include>
							  <script type="text/javascript">
									$('choose').className="hide";
									</script>
					</logic:notEmpty>
			</div>
		</html:form>
	</body>
</html>
