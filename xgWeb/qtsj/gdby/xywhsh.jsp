<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>
<html>
	<script language="javascript" src="js/jsFunction.js"></script>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/qtsjFunction.js"></script>
	<script language="javascript" src="js/check.js"></script>
	<script type="text/javascript" src="js/commit.js"></script>
	
	<script language="javascript">
		function modi(url){
			if(curr_row != null){
				showTopWin(url + '&pkValue='+curr_row.getElementsByTagName('input')[0].value,700,500);
				return true;
			}else{
				alert('请选择要修改的数据行！');
				return false;
			}
		}
		
		function choiceDisabled(usertype,ele) {
   			if($("userType")){
				if ($("userType").value == usertype) {
					var tmp = ele.split("-");
					for (i = 0; i < tmp.length; i++) {
						if(document.getElementById(tmp[i])){
			  		 		document.getElementById(tmp[i]).disabled = true;
						}
					}
  		 		}  
			}
		}
	</script>
	<body>
		<html:form action="/gdby_xywhgl.do?method=xywhsh" method="post">
			<input type="hidden" name="userName" value="${userName }"/>
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：${title }
				</div>
			</div>
			<fieldset>
				<legend>
					查询条件
				</legend>
				<table width="100%" class="tbstyle">
					<thead>
						<tr>
							<td>
								校园文化活动名称：
								<html:select property="queryequals_hddm" style="width: 200px">
									<html:option value=""></html:option>
									<logic:iterate id="map" name="whxxList">
										<html:option value="${map.hddm }">${map.hdmc }</html:option>
									</logic:iterate>
								</html:select>
							</td>
							<td width="10" align="center" valign="middle" rowspan="3">
								<input type="hidden" name="go" value="a" />
								<button class="button2" id="search_go" style="height:40px"
									onclick="allNotEmpThenGo('/xgxt/gdby_xywhgl.do?method=xywhsh&go=go')">
									查 询
								</button>
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
					<table width="99%" id="rsTable" class="tbstyle">
						<thead>
							<tr align="center" style="cursor:hand">
								<td>
									<input type="checkbox" name="all" value="all" onclick="chec()">
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
							<tr onclick="rowMoreClick(this,'',event);"
								ondblclick="modi('gdby_xywhgl.do?method=xywhsqViewAndModi&doType=view')"
								align="center"
								style="cursor:hand">
								<td>
									<input type="checkbox" name="primarykey_cbv" id="pkV"
										value="<logic:iterate id="v" name="s" offset="0" length="1">${v }</logic:iterate>" >
								</td>
								<td>
									<logic:iterate id="v" name="s" offset="0" length="1">
										<input type="hidden" value="${v }" />
									</logic:iterate>
									<logic:iterate id="v" name="s" offset="1" length="1">
										${v }
									</logic:iterate>
								</td>
								<logic:iterate id="v" name="s" offset="2">
									<td>
										${v }
									</td>
								</logic:iterate>
							</tr>
						</logic:iterate>
					</table>
				</fieldset>
				<TABLE width="99%" id="Table" class="tbstyle">
					<TR>
						<TD height=3></TD>
					</TR>
					<TR>
						<TD>
							<jsp:include flush="true"
								page="/sjcz/turnpage.jsp?form=xywhglForm"></jsp:include>
						</TD>
					</TR>
					<TR>
						<TD height=3></TD>
					</TR>
					</TABLE>
			</logic:notEmpty>
			<div class="buttontool" id="btn"
				style="position: absolute;left:1%;top:100px" width="100%">
				<logic:equal value="yes" name="writeAble" scope="request">
					<button class="button2"
						onclick="batchData('primarykey_cbv','gdby_xywhgl.do?method=xywhsh&shjg=tg&doType=sh&go=go','sh')"
						style="width:80px">
						通过
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="button2"
						onclick="batchData('primarykey_cbv','gdby_xywhgl.do?method=xywhsh&shjg=btg&doType=sh&go=go','sh')"
						style="width:80px">
						不通过
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="button2"
						onclick="modi('gdby_xywhgl.do?method=xywhshone&doType=shone')"
						style="width:80px">
						单个审核
					</button>
				</logic:equal>
			</div>

			<script language="javascript">
						document.getElementById("btn").style.pixelTop = document.body.clientHeight - 35;
						document.getElementById("btn").style.width = "96%";
						window.setInterval("initBTNTool('btn')",1);
			</script>
		</html:form>
		<logic:present name="result">
			<input type="hidden" id="message" value="${message }" />
			<script type="text/javascript">
				alert(document.getElementById('message').value);
			</script>
		</logic:present>
	</body>
</html>
