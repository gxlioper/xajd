<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>
<script type="text/javascript" src="pjpy/xibeierminyuan/js/xbemyJs.js"></script>
<script type="text/javascript" src="pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
<script type="text/javascript" src="pjpy/czxx/czxx.js"></script>
<script language="javascript" src="/xgxt/js/xgutil.js"></script>
<script language="javascript" src="/xgxt/js/stuinfoFunction.js.js"></script>
<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/util.js'></script>	
	<script language="javascript" src="/xgxt/js/AjaxFunction.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/qgzxgzkh.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/CzxxJxjDao.js'></script>
<script type="text/javascript">	  
		function tjjg(){
			var xn = val('xn');
			var xq = val('xq');
			var xydm = val('xy');
			qgzxgzkh.checkExists('view_czxx_xsrychb','xn||xq||xydm',xn+xq+xydm,function(data){
				if (data != null && data == true) {
					qgzxgzkh.checkExists('pjpy_bmshtjb','zjz||dm||bm||tjzt||tjxm',xn+xq+xydm+'xy1rych',function(data){
						if(data != null && data == true){
							alert('该<bean:message key="lable.xsgzyxpzxy" />荣誉称号评审结果已经提交！');
							return false;
						}else{//提交
							qgzxgzkh.checkExists('view_czxx_xsrychb','xn||xq||xydm||xysh||fdysh',xn+xq+xydm+'未审核通过',function(data){
								if(data){
									alert('该<bean:message key="lable.xsgzyxpzxy" />还有部分奖学金数据未审核,暂时不能提交结果！');
									return false;
								}else{
									if (confirm('确定要提交所有审核的数据吗?')) {
										refreshForm("pjpy_czxx_rychBmsh.do?act=tj&tjxydm=" + xydm);										
									}
									return false;
								}
							});
							
						}
					});
				} else {
					alert("该<bean:message key="lable.xsgzyxpzxy" />尚未申请任何荣誉称号数据,不能进行提交!");
					return false;
				}
			});
		}
		
		function jxjsh(type) {
			var checkBoxArr = document.getElementsByName("cbv");
			var flag = false;
			for(var i=0;i<checkBoxArr.length;i++){
				if(checkBoxArr[i].checked==true){
					flag = true;
				}
			}
			if (flag){
				var dd_html = "<div>";
				dd_html += "<center><br><table width='350px' class='tbstyle'>";
				dd_html += "<thead>";
				dd_html += "<tr height='30'>";
				dd_html += "<td align='center' colspan='2'>";
				dd_html += "请输入批量审核意见";
				dd_html += "</td>";
				dd_html += "</tr>";
				dd_html += "</thead>";
				dd_html += "<tbody>";
				dd_html += "<tr height='30'>";
				dd_html += "<td align='center' width='80px'>";
				dd_html += "审核意见：";		
				dd_html += "</td><td>";
				dd_html += "<textarea name='yj' id='yj' rows='5' width='330px' onkeyup='checkLen(this,500)'></textarea>";
				dd_html += "</td></tr>";
				dd_html += "<tr height='30' bgcolor='EEF4F9'>";
				dd_html += "<td align='center' colspan='2'>";
				dd_html += "<button type='button' class='button2' onclick='datasub(\""+type+"\")'>确定</button>&nbsp;&nbsp;";//szGroup()
				dd_html += "<button type='button' class='button2' onclick='closeAdd()'>关闭</button>";
				dd_html += "</td>";
				dd_html += "</tr>";
				dd_html += "<tbody>";
				dd_html += "</table></center>";
				dd_html += "</div>";
				showDiv(dd_html, 400, 170);
			}else{
				alert("没有选择相应记录，请选择之后再进行操作！！");
			}
		}
		function datasub(type) {
			refreshForm("pjpy_czxx_rychBmsh.do?act=save&jg="+type + "&yj="+document.getElementById('yj').value);
		}
		
		//批量返回
	function fhshdata(url) {
	var userType = document.getElementById('userType').value;
	var checkBoxArr = document.getElementsByName("cbv");
	var flag = false;
	var pk = "";
	for(var i=0;i<checkBoxArr.length;i++){
		if(checkBoxArr[i].checked==true){
			flag = true;
			pk += checkBoxArr[i].value;
			pk += "!@";
		}
	}
	if (flag){
		//检测是否有数据审核通过
		CzxxJxjDao.queryShjgByrych(pk,userType,function (data) {
			if (data) {
				alert("当前选择的返回重审的数据中，有部分数据已经审核通过，\n如要继续操作，请先将该数据审核状态更改为不通过!");
				return false;
			} else {
				if (confirm('确定要返回重审所选择的数据吗？')){
					document.forms[0].action = url;
					document.forms[0].submit();
					if ($("pt")) {
						BatAlert.showTips('正在操作，请等待...');
					}
				}		
			}
		});
	
	}else{
		alert("没有选择相应记录，请选择之后再进行操作！！");
	}
}
</script>
<body onload="xyDisabled('xy');">
	<html:form action="/czxxPjpyRych" method="post">
		<input type="hidden" name="zyV" id="zyV" />
		<input type="hidden" name="bjV" id="bjV" />
		<input type="hidden" name="userType" id="userType" value="${userType}" />
		<input type="hidden" name="tableName" id="tableName"
			value="${tableName }" />
		<input type="hidden" name="realTable" id="realTable"
			value="${realTable }" />
		<input type="hidden" name="userName" id="userName"
			value="${userName }" />
		<input type="hidden" name="isFdy" id="isFdy" value="${isFdy }" />
		<input type="hidden" id="pt" value="pt"/>
		<div class="title">
			<div class="title_img" id="title_m">
				当前所在位置：评奖评优 - 审核 - 荣誉称号审核
			</div>
		</div>
		<div class="rightcontent">
			<fieldset>
				<legend>
					查 询
				</legend>
				<table width="100%" class="tbstyle">
					<thead>
						<logic:equal value="xy" name="userType">
							<tr>
						
							<td align="left">
								学年：
								<html:select property="xn" styleId="xn" disabled='true'>
									<html:options collection="xnList" property="xn"
										labelProperty="xn" />
								</html:select>
								&nbsp;学期：
								<html:select property="xq" styleId="xq" disabled='true'>
									<html:option value=""></html:option>
									<html:options collection="xqList" property="xqdm"
										labelProperty="xqmc" />
								</html:select>
								&nbsp;年级：
								<html:select property="nj" onchange="initZyList();initBjList();">
									<html:option value=""></html:option>
									<html:options collection="njList" property="nj"
										labelProperty="nj" />
								</html:select>
								&nbsp;
								荣誉称号：
								<html:select property="rychdm" styleId="rychdm" >
									<html:option value=""></html:option>
									<html:options collection="rychList" property="rychdm" labelProperty="rychmc"/>
								</html:select>
								&nbsp;
								<logic:equal value="xy" name="userType">
								审核结果：
								<html:select property="xysh" styleId="xysh" >
									<html:option value=""></html:option>
									<html:option value="未审核">未审核</html:option>
									<html:option value="通过">通过</html:option>
									<html:option value="不通过">不通过</html:option>
								</html:select>
								</logic:equal>
								<logic:equal value="xy" name="uesrType">
									审核结果：
								<html:select property="xxsh" styleId="xxsh" >
									<html:option value=""></html:option>
									<html:option value="未审核">未审核</html:option>
									<html:option value="通过">通过</html:option>
									<html:option value="不通过">不通过</html:option>
								</html:select>
								</logic:equal>
								
								<td width="10" rowspan="2" align="center" valign="middle">
								<button type="button" class="button2" style="height:40px" id="search_go"
									onclick="refreshForm('pjpy_czxx_rychBmsh.do?act=qry');">
									查询
								</button>
							</td>
							</tr>
						<tr>
							<td align="left">
								<bean:message key="lable.xsgzyxpzxy" />：
								<html:select property="xydm" styleId="xy"
									onchange="initZyList();initBjList()">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select>
								&nbsp;&nbsp;专业：
								<html:select property="zydm" styleId="zy"
									onchange="initBjList()">
									<html:option value=""></html:option>
									<html:options collection="zyList" property="zydm"
										labelProperty="zymc" />
								</html:select>
								&nbsp;&nbsp;班级：
								<html:select property="bjdm" styleId="bj">
									<html:option value=""></html:option>
									<html:options collection="bjList" property="bjdm"
										labelProperty="bjmc" />
								</html:select>
							</td>
						</tr>
						</logic:equal>
					
						<logic:notEqual value="xy" name="userType">
						<tr>
							<td align="left">
								学年：
								<html:select property="xn" styleId="xn" disabled='true'>
									<html:options collection="xnList" property="xn"
										labelProperty="xn" />
								</html:select>
								&nbsp;学期：
								<html:select property="xq" styleId="xq" disabled='true'>
									<html:option value=""></html:option>
									<html:options collection="xqList" property="xqdm"
										labelProperty="xqmc" />
								</html:select>
								&nbsp;
								<bean:message key="lable.xsgzyxpzxy" />：
								<html:select property="xydm" styleId="xy"
									onchange="initZyList();initBjList()">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select>
								&nbsp;
									审核结果：
								<html:select property="xxsh" styleId="xxsh" >
									<html:option value=""></html:option>
									<html:option value="未审核">未审核</html:option>
									<html:option value="通过">通过</html:option>
									<html:option value="不通过">不通过</html:option>
								</html:select>
								<td width="10" rowspan="" align="center" valign="middle">
								<button type="button" class="button2" style="height:40px" id="search_go"
									onclick="refreshForm('pjpy_czxx_rychBmsh.do?act=qry');">
									查询
								</button>
							</td>
							</tr>
						</logic:notEqual>
						
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
						记录数： ${rsNum } &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<font color="blue">提示：单击表头可以排序;
						</font>
						<logic:equal value="xy" name="userType">
						<font color="red"><logic:notEmpty name="tjzt">该<bean:message key="lable.xsgzyxpzxy" />数据提交状态: ${tjzt }</logic:notEmpty></font>
						</logic:equal>
					</legend>
					<table width="100%" id="rsTable" class="tbstyle">
						<thead>
							<tr align="center" style="cursor:hand">
								<td>
									<input type="checkbox" name="cb" onclick="selectAll()" style="height:17.5px">
								</td>
								<logic:iterate id="tit" name="topTr" offset="2">
									<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)" nowrap>
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<logic:iterate name="rs" id="s">
							<tr onclick="rowOnClick(this);" style="cursor:hand;" ondblclick="modiAndDel('pjpy_czxx_xyrychshView.do?act=view&pkValue=','modi','800','550')">
								<td align="center">
									<input type="checkbox" id="cbv" name="cbv" style="height:17.5px"
										value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>"
										<logic:iterate id="v" name="s" offset="1" length="1"><bean:write name="v"/></logic:iterate> />
								</td>
								<logic:iterate id="v" name="s" offset="2">
									<td align="center">
										<bean:write name="v" />
									</td>
								</logic:iterate>
							</tr>
						</logic:iterate>
					</table>
				</fieldset>
				<TABLE width="99%" id="rsTable" class="tbstyle">
					<TR>
						<TD height=3></TD>
					</TR>
					<TR>
						<TD>
							<jsp:include flush="true"
								page="/sjcz/turnpage.jsp?form=pjpyCzxxActionForm"></jsp:include>
						</TD>
					</TR>
					<TR>
						<TD height=3></TD>
					</TR>
				</TABLE>
			</logic:notEmpty>
		</div>
	</html:form>
	<div id="tmpdiv"></div>
	<div id="toolTipLayer" style="position:absolute; visibility: hidden" /></div>
	<center>
		<logic:equal value="yes" name="writeAble">
			<div class="buttontool" id="btn"
				style="position: absolute;left:1%;top:100px" width="100%">
				<button type="button" class="button2" id="btn_dc" onclick="jxjsh('tg')"
					style="width:80px">
					审核通过
				</button>
				&nbsp;&nbsp;&nbsp;
				<button type="button" class="button2" id="btn_dc" onclick="jxjsh('btg')"
					style="width:80px">
					审核不通过
				</button>
				<logic:equal value="xy" name="userType">
				&nbsp;&nbsp;&nbsp;
				<button type="button" class="button2" id="btn_dc" onclick="tjjg()"
					style="width:80px">
					提交结果
				</button>
				</logic:equal>
				&nbsp;&nbsp;&nbsp;
				<button type="button" class="button2" id="btn_dc" onclick="fhshdata('pjpy_czxx_rychBmsh.do?act=fh')"
					style="width:80px">
					返回重审
				</button>
			</div>
			<script language="javascript">
                  document.getElementById("btn").style.pixelTop = document.body.clientHeight - 35;
                  document.getElementById("btn").style.width = "96%";
                  window.setInterval("initBTNTool('btn')",1);
                </script>
		</logic:equal>
	</center>
	<logic:present name="inserted">
			<logic:equal value="yes" name="inserted">
				<script>
					alert("操作成功!");
					document.getElementById('search_go').click();
				</script>
			</logic:equal>
			<logic:equal value="no" name="inserted">
				<script>
					alert("操作失败!");				
				</script>
			</logic:equal>
		</logic:present>
</body>