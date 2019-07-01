<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini" %>
	<script type="text/javascript" src="pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
	<script type="text/javascript" >	  
     function saveData() {
     	var lb = document.getElementById('lb').value;	
     	var dm = document.getElementById('dm').value;
     	var bfb = document.getElementById('bfb').value;
     	if (lb==null||lb==''||dm==null||dm==''||bfb==null||bfb=='') {
     		alert("请将类型，奖项名称，综测排名前百分分填写完整!");
     		return false;
     	}
     	refreshForm('pjpy_czxx_tjsz.do?act=save');
     }
	</script>
	<body>
		<html:form action="/czxxPjpyCssz" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：评奖评优 - 参数设置 - 综合排名百分比设置
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
									类别:
									<html:select property="lb" styleId="lb" style="width:100px" onchange="refreshForm('pjpy_czxx_tjsz.do');">
										<html:option value=""></html:option>
										<html:options collection="lbList" property="dm" labelProperty="mc"/>
									</html:select>
									&nbsp;
									奖项名称:
									<html:select property="dm" styleId="dm">
										<html:option value=""></html:option>
										<html:options collection="dmList" property="dm" labelProperty="mc"/>
									</html:select>
									&nbsp;
									--&gt;
									综测总成绩排名前百分比:
									<html:text property="bfb" styleId="bfb" maxlength="4" style="width:60px" onkeyup="checktype(this)">
									</html:text>
									<font color="red">(%)</font>
									&nbsp;
									<button type="button" class="button2" id="btn_save"
										onclick="saveData()">
										保存
									</button>
									&nbsp;
									<button type="button" class="button2" id="search_go"
										onclick="refreshForm('pjpy_czxx_tjsz.do?act=qry');">
										查询
									</button>
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
							
							<font color="blue">提示：双击一行可以查看详细信息；单击表头可以排序;</font>
						</legend>
						<table width="100%" id="rsTable" class="tbstyle">
							<thead>
								<tr align="center" style="cursor:hand">
									<td>
										<input type="checkbox" name="cb" 
											onclick="selectAll()">
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
								<tr onclick="rowOnClick(this);" style="cursor:hand;">
									<td align="center">
										<input type="checkbox" id="cbv" name="cbv"
											value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>" />
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
		<div id="toolTipLayer" style="position:absolute; visibility: hidden" /></div>
		<center>
			<logic:equal value="yes" name="writeAble">
				<div class="buttontool" id="btn"
					style="position: absolute;left:1%;top:100px" width="100%">
					<button type="button" class="button2" onclick="deldata('pjpy_czxx_tjsz.do?act=del')" style="width:80px">
						删 除
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
					Close();
					document.getElementById('search_go').click();
				</script>
			</logic:equal>
<%--			<logic:equal value="no" name="inserted">--%>
<%--				<script>--%>
<%--					alert("操作失败,原因可能是数据库中已存在相同记录!");--%>
<%--					Close();--%>
<%--					document.getElementById('search_go').click();--%>
<%--				</script>--%>
<%--			</logic:equal>--%>
		</logic:present>
	</body>
