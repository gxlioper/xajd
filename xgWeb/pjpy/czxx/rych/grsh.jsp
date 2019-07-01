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
<script type="text/javascript">	  
function tjjg(){
			var dd_html = "<div>";
			dd_html += "<center><br><table width='350px' class='tbstyle'>";
			dd_html += "<thead>";
			dd_html += "<tr height='30'>";
			dd_html += "<td align='center' colspan='2'>";
			dd_html += "请选择您要提交审核结果的班级";
			dd_html += "</td>";
			dd_html += "</tr>";
			dd_html += "</thead>";
			dd_html += "<tbody>";
			dd_html += "<tr height='30'>";
			dd_html += "<td align='center' width='80px'>";
			dd_html += "学年：";		
			dd_html += "</td><td>";
			dd_html += val('xn');
			dd_html += "</td></tr>";
			dd_html += "<tr height='30'>";
			dd_html += "<td align='center'>";
			dd_html += "学期：";		
			dd_html += "</td><td>";
			dd_html += selText('xq');
			dd_html += "</td></tr>";
			dd_html += "<tr height='30'>";
			dd_html += "<td align='center'>";
			dd_html += "班级：";		
			dd_html += "</td><td>";
			dd_html += "<select id='tjbjdm' name='tjbjdm'/>";
			dd_html += "</td></tr>";
			dd_html += "<tr height='30' bgcolor='EEF4F9'>";
			dd_html += "<td align='center' colspan='2'>";
			dd_html += "<button type='button' class='button2' onclick='commitSh()'>确定</button>&nbsp;&nbsp;";//szGroup()
			dd_html += "<button type='button' class='button2' onclick='closeAdd()'>关闭</button>";
			dd_html += "</td>";
			dd_html += "</tr>";
			dd_html += "<tbody>";
			dd_html += "</table></center>";
			dd_html += "</div>";
			showDiv(dd_html, 400, 170);
			copySelect('bj','tjbjdm');
			setVal('tjbjdm',val('bj'));
		}
		
		function commitSh(){
			//判断选择的班级是否已经提交过
			var xn = val('xn');
			var xq = val('xq');
			var bjdm = val('tjbjdm');
			if(bjdm == null || bjdm == "" || bjdm == undefined){
				alert('请选择您要提交审核结果的班级！');
				return false;
			}
			
			
			qgzxgzkh.checkExists('view_czxx_xsrychb','xn||xq||bjdm',xn+xq+bjdm,function(data){
				if (data != null && data == true) {
					qgzxgzkh.checkExists('pjpy_bmshtjb','zjz||dm||bm||tjzt||tjxm',xn+xq+bjdm+'bj1rych',function(data){
						if(data != null && data == true){
							alert('该班级荣誉称号评审结果已经提交！');
							return false;
						}else{//提交
							qgzxgzkh.checkExists('view_czxx_xsrychb','xn||xq||bjdm||fdysh',xn+xq+bjdm+'未审核',function(data){
								if(data){
									alert('该班级还有部分荣誉称号数据未审核,暂时不能提交结果！');
									return false;
								}else{
									refreshForm("pjpy_czxx_rychsh.do?act=tj&tjbjdm=" + bjdm);
								}
							});
							
						}
					});
				} else {
					alert("该班级尚未申请任何荣誉称号数据,不能进行提交!");
					return false;
				}
			});
		}
</script>
<body onload="xyDisabled('xy');">
	<html:form action="/czxxPjpyRych" method="post">
		<input type="hidden" name="zyV" id="zyV" />
		<input type="hidden" name="bjV" id="bjV" />
		
		<input type="hidden" name="queryequals_xn" id="queryequals_xn" value="${xn }"/>
		<input type="hidden" name="queryequals_xq" id="queryequals_xq" value="${xq }"/>
		
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
						<tr>
							<td align="left">
								学年：
								<html:select property="xn" styleId="xn" disabled="true">
									<html:options collection="xnList" property="xn"
										labelProperty="xn" />
								</html:select>
								&nbsp;学期：
								<html:select property="xq" styleId="xq" disabled="true">
									<html:option value=""></html:option>
									<html:options collection="xqList" property="xqdm"
										labelProperty="xqmc" />
								</html:select>
								&nbsp;年级：
								<html:select property="queryequals_nj" onchange="initZyList();initBjList();">
									<html:option value=""></html:option>
									<html:options collection="njList" property="nj"
										labelProperty="nj" />
								</html:select>
								
								<td width="10" rowspan="3" align="center" valign="middle">
								<button type="button" class="button2" style="height:40px" id="search_go"
									onclick="refreshForm('pjpy_czxx_rychsh.do?act=qry');">
									查询
								</button>
							</td>
							</tr>
							<tr>
							<td>
								荣誉称号：
								<html:select property="queryequals_rychdm" styleId="rychdm" >
									<html:option value=""></html:option>
									<html:options collection="rychList" property="rychdm" labelProperty="rychmc"/>
								</html:select>
								&nbsp;
								审核结果：
								<html:select property="queryequals_fdysh" styleId="fdysh" >
									<html:option value=""></html:option>
									<html:option value="未审核">未审核</html:option>
									<html:option value="通过">通过</html:option>
									<html:option value="不通过">不通过</html:option>
								</html:select>
								&nbsp;
								学号：
								<html:text property="querylike_xh"  styleId="xh" maxlength="19"></html:text>
								&nbsp;姓名：
								<html:text property="querylike_xm" styleId="xm" maxlength="10"></html:text>
							</td>
							</tr>
						<tr>
							<td align="left">
								<bean:message key="lable.xsgzyxpzxy" />：
								<html:select property="queryequals_xydm" styleId="xy"
									onchange="initZyList();initBjList()">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select>
								&nbsp;&nbsp;专业：
								<html:select property="queryequals_zydm" styleId="zy"
									onchange="initBjList()">
									<html:option value=""></html:option>
									<html:options collection="zyList" property="zydm"
										labelProperty="zymc" />
								</html:select>
								&nbsp;&nbsp;班级：
								<html:select property="queryequals_bjdm" styleId="bj">
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
						<font color="red"><logic:notEmpty name="tjzt">该班级数据提交状态: ${tjzt }</logic:notEmpty></font>
						
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
							<tr onclick="rowOnClick(this);" style="cursor:hand;" ondblclick="modiAndDel('pjpy_czxx_rychDggrsh.do?act=view&pkValue=','modi','750','700')">
								<td align="center">
									<input type="checkbox" id="cbv" name="primarykey_cbv" style="height:17.5px"
										value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>"
										<logic:iterate id="v" name="s" offset="1" length="1"><bean:write name="v"/></logic:iterate> />
								</td>
								<logic:iterate id="v" name="s" offset="2" length="3">
									<td align="center">
										<bean:write name="v" />
									</td>
								</logic:iterate>
								<logic:iterate id="v" name="s" offset="5" length="1">
									<td align="center">
										<a href="#" onclick="queryOne('<bean:write name="v" />')"><bean:write name="v" /></a>
									</td>
								</logic:iterate>
								<logic:iterate id="v" name="s" offset="6">
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
				<button type="button" class="button2" id="btn_dc" onclick="shdata('pjpy_czxx_rychsh.do?act=save&jg=tg')"
					style="width:80px">
					审核通过
				</button>
				&nbsp;&nbsp;&nbsp;
				<button type="button" class="button2" id="btn_dc" onclick="shdata('pjpy_czxx_rychsh.do?act=save&jg=btg')"
					style="width:80px">
					审核不通过
				</button>
				&nbsp;&nbsp;&nbsp;
				<button type="button" class="button2" id="btn_dc" onclick="modiAndDel('pjpy_czxx_rychDggrsh.do?pkValue=','modi','750','700')"
					style="width:80px">
					单个审核
				</button>
				&nbsp;&nbsp;&nbsp;
				<button type="button" class="button2" id="btn_dc" onclick="tjjg()"
					style="width:80px">
					提交结果
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