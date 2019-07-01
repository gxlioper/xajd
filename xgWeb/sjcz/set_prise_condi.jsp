<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	</head>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<script language="javascript">
	function allFilleds(val) {
		var arrayList = val.split('-');
		for (var i=0; i<arrayList.length;i++) {
		if ($(arrayList[i])) {
			if (document.getElementById(arrayList[i]).value=='') {
				alert("所有输入项不得为空!");
				return false;
			}
		}
	    }
	 var val =  $("val");
	 var valV =val.value.replace(/[^(\d|\.)]/g,'');
	 if(!valV.match(/\d+/g)){		
		alert("条件值须为数字类型的数据！");
		$("val").focus()
		return false;
	 }
	 if($("zdm").value=="cpzfpm"){
	     if(val.value.indexOf(".")!=-1){
	       alert("输入的条件值有误！");
	       return false;
	     }
	 }
	 //chkInput(val);
	 var zdmc = document.forms[0].zdm.options[document.forms[0].zdm.selectedIndex].text;
     document.forms[0].go.value='go';refreshForm('/xgxt/prise_condition.do?zdmc='+zdmc);return true;
	}
	function zhcpClin(){
	    var tjVal = $("zdm").value;
	    if(tjVal=="cpzfpm"){
	       $("zhszcppm2").innerText = "  %";
	    }else{
	       $("zhszcppm1").innerText = "";
	       $("zhszcppm2").innerText = "";	       
	    }
	}
</script>
	<body>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/jsFunction.js"></script>
		<html:form action="/prise_condition" method="post">
		
			<div class="tab_cur">
					<p class="location">
						<em>当前所在位置：</em><a>评奖评优 - 参数设置 - 条件设置</a>
					</p>
				</div>	
<input type="hidden" name="search_go" id="search_go"
					onclick="refreshForm('/xgxt/prise_condition.do')" />
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
						<ul>
					<logic:notEqual value="10656" name="xxdm">
					<li><a class="btn_sc" onclick="delPriseCondi()">
						删除条件
					</a></li>
					<li>
					<a class="btn_hsz" onclick="delAllPriseCondi()">
						清空条件
					</a>
					</li>
			</logic:notEqual>
						</ul>
					</div>
			
				<div class="searchtab">
					<table width="100%" border="0">
					
						<tbody>
								<tr>
									<th>
										学年
										</th>
										<td>
											<html:select property="xn" styleId="xn">
									<html:options collection="xnList" property="xn"
										labelProperty="xn" />
								</html:select>
										</td>
										<logic:notEqual value="10656" name="xxdm">
								<th>奖学金</th>
								<td>
								<html:select property="xmdm" style="width:200px" styleId="jxjdm"
										onchange="refreshForm('/xgxt/prise_condition.do')">
										<html:option value=""></html:option>
										<html:options collection="jxjList" property="jxjdm"
											labelProperty="jxjmc" />
									</html:select>
									</td>
								</logic:notEqual>
									<td colspan="">
								<logic:equal name="xxdm" value="10656" scope="session">
								<logic:notEqual value="xy" name="userType">
									
										<button type="button" class="button2"
											onclick="if(!check('xn')) return false;showTop($('xn').value);">
											设定比例
										</button>
									
									</logic:notEqual>
								</logic:equal>
									</td>
								</tr>
								<tr>
									<logic:notEqual value="10656" name="xxdm">
									<th>条件</th>
									<td colspan="4"> 
								<logic:equal value="12865" name="xxdm">
										<html:select property="zdm" styleId="zdm" onchange="zhcpClin()">
											<html:option value=""></html:option>
											<html:options collection="zdList" property="en"
												labelProperty="cn" />
										</html:select>
										
								--&gt;
								<select name="ysf" id="ysf" style="width:120px">
											<option value=""></option>
											<option value="&gt;=">
												大于或等于
											</option>
										</select>
									</logic:equal>

									<logic:notEqual value="12865" name="xxdm">
										<html:select property="zdm" style="width:120px" styleId="zdm">
											<html:option value=""></html:option>
											<html:options collection="zdList" property="zd"
												labelProperty="zdsm" />
										</html:select>														
								--&gt;
								<select name="ysf" id="ysf" style="width:120px">
											<option value=""></option>
											<option value="&gt;=">
												大于或等于
											</option>
<%--											<option value="=">--%>
<%--												等于--%>
<%--											</option>--%>
<%--											<option value="&gt;">--%>
<%--												大于--%>
<%--											</option>--%>
<%--											<option value="&lt;">--%>
<%--												小于--%>
<%--											</option>--%>
<%--											<option value="&lt;=">--%>
<%--												小于或等于--%>
<%--											</option>--%>
										</select>
									</logic:notEqual>								
								--&gt;
							   <span id="zhszcppm1"></span>
								<input type="text" name="val" value="" style="width:120px;ime-mode:disabled" maxlength="5" onkeypress='return sztzNumInputValue(this,6,event)' />
							   <span id="zhszcppm2"></span>	
									<input type="hidden" name="go" value="a" />
									<button type="button" class=""
										onclick="allFilleds('xn-jxjdm-zdm-val-ysf-zdcz')">
										添加条件
									</button>
								</logic:notEqual>
								&nbsp;&nbsp;&nbsp;&nbsp;
										<button type="button" class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
											重 置
										</button>
								</td>
								</tr>
						</tbody>
					</table>
				</div>
			</div>	
			
			<div class="formbox">
				<h3 class="datetitle_01">
					<span> 现有条件
						</span>
				</h3>

				<logic:notEmpty name="rs">
					<table summary="" class="dateline" align="" width="100%">
						<thead>
							<tr>
								<logic:iterate id="tit" name="topTr" offset="1">
									<td align="center" id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)">
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							
<%--								<td onclick="tableSort(this)">--%>
<%--									序号--%>
<%--								</td>--%>
<%--								<td onclick="tableSort(this)">--%>
<%--									奖学金--%>
<%--								</td>--%>
<%--								<td onclick="tableSort(this)">--%>
<%--									条件字段--%>
<%--								</td>--%>
<%--								<td onclick="tableSort(this)">--%>
<%--									条件操作--%>
<%--								</td>--%>
<%--								<td onclick="tableSort(this)">--%>
<%--									条件--%>
<%--								</td>--%>
							</tr>
						</thead>
						<tbody>
							<logic:iterate name="rs" id="s">
							<tr align="left" onclick="rowOnClick(this)" style="cursor:hand">
<%--								<input type="hidden" value="${rs.pk }" />--%>
<%--								<td>--%>
<%--									<bean:write name="rs" property="rownum" />--%>
<%--								</td>--%>
<%--								<td>--%>
<%--									<bean:write name="rs" property="jxjmc" />--%>
<%--								</td>--%>
<%--								<td>--%>
<%--									<bean:write name="rs" property="tjzdm" />--%>
<%--								</td>--%>
<%--								<td>--%>
<%--									<bean:write name="rs" property="zdcz" />--%>
<%--								</td>--%>
<%--								<td>--%>
<%--									<bean:write name="rs" property="tj" />--%>
<%--								</td>--%>                             
								<td>
									<logic:iterate id="v" name="s" offset="0" length="1">
										<input type="hidden" value="<bean:write name="v"/>" />
									</logic:iterate>
									<logic:iterate id="v" name="s" offset="1" length="1">
										<bean:write name="v" />
									</logic:iterate>
								</td>
								<logic:iterate id="v" name="s" offset="2" >
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
				
				
				<logic:notEmpty name="res">
					<table summary="" class="dateline" align="" width="100%">
						<thead>
							<tr align="center">
							<td onclick="tableSort(this)">
								行号
							</td>
							<td onclick="tableSort(this)">
								学年
							</td>
							<td onclick="tableSort(this)">
								成绩所占比例(%)
							</td>
							<td onclick="tableSort(this)">
								综合素质所占比例(%)
							</td>
						</tr>
						</thead>
						<tbody>
							<logic:iterate id="r" name="res" scope="request">
						<tr align="center" onclick="rowOnClick(this)" style="cursor:hand">
							<logic:iterate id="v" name="r" offset="0">
								<td align=center>
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

			<logic:equal value="true" name="flag">
				<script>
					alert('操作成功！');
				</script>
			</logic:equal>
			<logic:equal value="false" name="flag">
				<script>
					alert('操作失败！');
				</script>
			</logic:equal>
			<logic:present name="add">
			<logic:equal name="add" value = "yes">
				<script language="javascript">
					alert("增加条件成功");
				</script>
			</logic:equal>
			<logic:equal name="add" value = "no">
				<script language="javascript">
					alert("增加条件失败，请确认没有该类型条件后再增加");
				</script>
			</logic:equal>
			</logic:present>
		</html:form>

	</body>
</html>
