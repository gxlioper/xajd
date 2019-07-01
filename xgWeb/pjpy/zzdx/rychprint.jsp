<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script>   
var mb = 0;
function colorOn(){	
	for(i = 0;i<mbT.rows.length;i++){	
		for(j = 0;j<mbT.rows[i].cells.length;j++){
			document.all.mbT.rows[i].cells[j].style.backgroundColor = "#FFFFFF";			
			document.all.mbT.rows[i].cells[mb].style.backgroundColor = "#ffdead";
		}
	}
}
function printZS(mod){

	if(mb >3 || mb <0){
		mb = 0;
		colorOn();
	}
	if (mod==3){//单个打印
		var ul ='rychzsprint.do?xh='
		if (curr_row==null || curr_row==''){
			var hjxn = prompt("   请输入获奖学年度：(日期格式为:xxxx-xxxx)","");
			var hjxq = prompt("   请输入获奖学期：(格式例如:一、二)","");
			var hjxjdj = prompt("   请输入荣誉称号：(格式例如:优秀团干部)","");
			var hjny = prompt("   请输入发证年月：(日期格式为:xxxx-xx)","");
		}else{
			var hjxn = prompt("   请输入获奖学年度：(日期格式为:xxxx-xxxx)","");
			var hjxq = prompt("   请输入获奖学期：(格式例如:一、二)","");
			var hjxjdj = prompt("   请输入荣誉称号：(格式例如:优秀团干部)","");
			var hjny = prompt("   请输入发证年月：(日期格式为:xxxx-xx)","");
			ul += curr_row.cells[0].getElementsByTagName("input")[0].value;
			ul += '&xm='
			ul += curr_row.cells[0].getElementsByTagName("input")[1].value;
		}
		var ull = '&hjxn='+hjxn+'&hjxq='+hjxq+'&hjxjdj='+hjxjdj+'&hjny='+hjny;
			ul += ull;
		window.open(ul);
		return;
	}
	if (mod==4) {//打印测试页
			var hjxn = prompt("   请输入获奖学年度：(日期格式为:xxxx-xxxx)","");
			var hjxq = prompt("   请输入获奖学期：(格式例如:一、二)","");
			var hjxjdj = prompt("   请输入荣誉称号：(格式例如:优秀团干部)","");
			var hjny = prompt("   请输入发证年月：(日期格式为:xxxx-xx)","");
			var ull = '';
			if (curr_row!=null && curr_row!=''){
				ull = curr_row.cells[0].getElementsByTagName("input")[1].value;;
				}
			ull += '&hjxn='+hjxn+'&hjxq='+hjxq+'&hjxjdj='+hjxjdj+'&hjny='+hjny;
		window.open('rychzsprint.do?xm='+ull);
		return;
	}
	if (mod==5){//连打
		var hjxn = prompt("   请输入获奖学年度：(日期格式为:xxxx-xxxx)","");
			var hjxq = prompt("   请输入获奖学期：(格式例如:一、二)","");
			var hjxjdj = prompt("   请输入荣誉称号：(格式例如:优秀团干部)","");
			var hjny = prompt("   请输入发证年月：(日期格式为:xxxx-xx)","");
			var ull = '&hjxn='+hjxn+'&hjxq='+hjxq+'&hjxjmc='+hjxjdj+'&hjny='+hjny;
			if (confirm("确定要做此操作吗?")){
    	var xm;
		if($("tabPri").rows.length > 1){
			rowOnClick($("tabPri").rows[0]);
			xm=$("tabPri").rows[0].cells[2].innerText.trim();
			window.open("rychprintmore.do?xm="+xm+ull);
			BatAlert.closeTips();
		 } else{
		    BatAlert.MyAlert("没有可打印的数据！");
			return false;
		 }
		 return true;
	}
     else{
	     return false;
	}   
		return;
	}
	var url = "/xgxt/print/printCotener_";
	url += mb;
	url += ".html";
	document.getElementById("nj").value = mod;
	chgRight(url,'_blank');
	document.forms[0].target = "_self";
}
</script>
	</head>
	<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
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
	<body onload="">
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/sharedFunction.js"></script>
		<script language="javascript" src="js/String.js"></script>
		<script language="javascript" src="js/stuinfoFunction.js"></script>
		<script language="javascript" src="js/BatAlert.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script type="text/javascript" src="pjpy/zzdx/zzdxJs/zzdxjs.js"></script>
		<html:form action="/pjpyzzdxwh" method="post">
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>评奖评优 - 审核 - 荣誉称号证书打印</a>
				</p>
			</div>
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<!--读写权-->
						<logic:equal value="yes" name="writeAble">
							<div class="noprint">
				<!-- 中州大学荣誉证书打印 -->
						<logic:present name="showzzdx">
						<li>
							<a href="#" class='btn_dy' 
							onclick="printzzdxZS('test')">打印测试页</a>
							</li>
							<li>
						<a href="#" class='btn_dy' 
							onclick="printzzdxZS('one')">单个打印</a>
							</li>
							<li>
						<a href="#" class='btn_dy' 
							onclick="printzzdxZS('more')">证书连打</a>
							</li>
							
						</logic:present>
						<logic:notPresent name="showzzdx">
						<li>
							<a href="#" class='btn_dy' 
							onclick="printZS(4)">打印测试页</a>
							</li>
							<li>
						<a href="#" class='btn_dy' 
							onclick="printZS(3)">单个打印</a>
							</li>
							<li>
						<a href="#" class='btn_dy' 
							onclick="printZS(5)">证书连打</a>
							</li>
						</logic:notPresent>
			</div>

						</logic:equal>
					</ul>
				</div>
				<div class="searchtab">
					<table width="100%" border="0">
						<tfoot>
							<tr>
								<td>
									<!--操作开关提示-->
									<logic:notEmpty name="yhInfo">
										<font color="red">提示：${ yhInfo}</font>
									</logic:notEmpty>
									<!--end操作开关提示-->
								</td>
								<td colspan="6">
									<div class="btn">
										<input type="hidden" name="go" value="a" />
										<button type="button" class=""  id="search_go"
											onclick="refreshForm('pjpy_rychzsprint.do')">
											查询
										</button>
										&nbsp;&nbsp;&nbsp;&nbsp;
										<button type="button" class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
											重 置
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
						<tbody>

							<tr>
								<th>
									荣誉称号
								</th>
								<td>
									<html:select property="rychdm" style="width:200px"
											styleId="rychdm">
											<html:option value=""></html:option>
											<html:options collection="rychList" property="rychdm"
												labelProperty="rychmc" />
										</html:select>
								</td>
								<th>
									<input type="hidden" value="a" id="nj" name="nj"></input>
									<input type="hidden" value="${sj }" id="sj" name="sj" ></input>
										学年
								</th>
								<td>
									<html:select property="xn" styleId="xn" style="width:90px">
										<html:options collection="xnList" property="xn"
											labelProperty="xn" />
									</html:select>
								</td>
								<th>
									学期
								</th>
								<td>
									<html:select property="xq" styleId="xq" style="width:90px">
										<html:option value=""></html:option>
										<html:options collection="xqList" property="xqdm"
											labelProperty="xqmc" />
									</html:select>
									<input type="checkbox" style="display:none" id="chgMode" ></input>
								</td>
								
							</tr>
							<tr>
							<th>
									<bean:message key="lable.xsgzyxpzxy" />
								</th>
								<td>
									<html:select property="xydm" style="width:170px" styleId="xy"
											onchange="refreshForm('prise_print.do')">
											<html:option value=""></html:option>
											<html:options collection="xyList" property="xydm"
												labelProperty="xymc" />
										</html:select>
								</td>
								<th>
									专业
								</th>
								<td>
									<html:select property="zydm" style="width:170px" styleId="zy"
										onchange="refreshForm('prise_print.do')">
										<html:option value=""></html:option>
										<html:options collection="zyList" property="zydm"
											labelProperty="zymc" />
									</html:select>
								</td>
								<th>
									班级
								</th>
								<td>
									<html:select property="bjdm" style="width:170px" styleId="bjdm">
										<html:option value=""></html:option>
										<html:options collection="bjList" property="bjdm"
											labelProperty="bjmc" />
									</html:select>
								</td>
								
							</tr>
						</tbody>
					</table>
				</div>
			</div>

<%--			<div class="noprint">--%>
<%--				<fieldset>--%>
<%--					<legend align="center">--%>
<%--						打印区----模板选择--%>
<%--					</legend>--%>
<%--					<table class="tbstyle" align="center" width="100%" height="150px"--%>
<%--						id="mbT">--%>
<%--						<tr>--%>
<%--							<!-- <td bgcolor="#FFFFFF" rowspan="2" width="5%" align="center" style="cursor:hand" onclick="mb=0;colorOn();"> </td> -->--%>
<%--							<td bgcolor="#FFFFFF" width="33%" align="center"--%>
<%--								style="cursor:hand" onclick="mb=0;colorOn();">--%>
<%--								<font color="red">学校提供模板</font>--%>
<%--								<img src="<logic:equal value="yes" name="showzzdx">print/zzdx/zzdx_zs1.jpg</logic:equal>" height="150px" border="1" />--%>
<%--								--%>
<%--							</td>--%>
<%--							<td bgcolor="#FFFFFF" width="34%" align="center"--%>
<%--								style="cursor:hand" onclick="mb=1;colorOn();">--%>
<%--								<font color="red">学校提供模板</font>--%>
<%--								<img src="<logic:equal value="yes" name="showzzdx">print/zzdx/zzdx_zf2.jpg</logic:equal>" height="150px" border="1" />--%>
<%--							</td>--%>
<%--							<td bgcolor="#FFFFFF" width="33%" align="center"--%>
<%--								style="cursor:hand" onclick="mb=2;colorOn();">--%>
<%--								<font color="red">学校提供模板</font>--%>
<%--								<img src="<logic:equal value="yes" name="showzzdx">print/zzdx/zzdx_zf3.jpg</logic:equal>" height="150px" border="1" />--%>
<%--							</td>--%>
<%--							<!-- <td bgcolor="#FFFFFF" rowspan="2" width="5%" align="center" style="cursor:hand" onclick="mb=2;colorOn();"> </td> -->--%>
<%--						</tr>--%>
<%--						<tr>--%>
<%--							<td bgcolor="#FFFFFF" align="center" style="cursor:hand"--%>
<%--								onclick="mb=0;colorOn();">--%>
<%--								模板一--%>
<%--							</td>--%>
<%--							<td bgcolor="#FFFFFF" align="center" style="cursor:hand"--%>
<%--								onclick="mb=1;colorOn();">--%>
<%--								模板二--%>
<%--							</td>--%>
<%--							<td bgcolor="#FFFFFF" align="center" style="cursor:hand"--%>
<%--								onclick="mb=2;colorOn();">--%>
<%--								模板三--%>
<%--							</td>--%>
<%--						</tr>--%>
<%--					</table>--%>
<%--				</fieldset>--%>
<%--			</div>--%>

				<div class="formbox">
				<h3 class="datetitle_01">
				    <span>
				    	查询结果&nbsp;&nbsp;
				    	<logic:empty name="rs">
							<font color="red">未找到任何记录！</font> 
				 		 </logic:empty>
				 		 <logic:notEmpty name="rs">
				 		 <font color="blue"></font>
				 		 </logic:notEmpty>
				    </span>
				    </h3>
				<logic:notEmpty name="rs">

						<table summary="" class="dateline" width="100%"
							id="rsTable">
							<thead>
									<tr align="center" style="cursor:hand">
										<logic:iterate id="tit" name="topTr" offset="1">
											<td id="<bean:write name="tit" property="en"/>"
												onclick="tableSort(this)" nowrap>
												<bean:write name="tit" property="cn" />
											</td>
										</logic:iterate>
									</tr>
								</thead>
							<tbody id="tabPri">
								<logic:iterate name="rs" id="s">
									<tr onclick="rowOnClick(this)" style="cursor:hand;" align="center">
									<td>
										<input type="hidden" value="<logic:iterate id="v" offset="0" length="1" name="s"><bean:write name="v" /></logic:iterate>"/>
										<input type="hidden" value="<logic:iterate id="v" offset="3" length="1" name="s"><bean:write name="v" /></logic:iterate>"/>
										<logic:iterate id="v" offset="1" length="1" name="s"><bean:write name="v" /></logic:iterate>
										
									</td>
										<logic:iterate id="v" name="s" offset="2">
											<td>
												<bean:write name="v" />
											</td>
										</logic:iterate>
									</tr>
								</logic:iterate>
							</tbody>
						</table>
					<!--分页显示-->

					<!--分页显示-->
				</logic:notEmpty>
			</div>
		</html:form>
	</body>
</html>
