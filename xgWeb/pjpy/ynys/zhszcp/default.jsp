<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini" %>
<script language="javascript" src="/xgxt/pjpy/xibeierminyuan/js/xbemyJs.js"></script>
<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
<script type="text/javascript" src="pjpy/ahjg/ahjgjs/ahjgjs.js"></script>
<script type="text/javascript" src="pjpy/ynys/ynysJs/ynys.js"></script>
<script type="text/javascript" src="js/BatAlert.js"></script>
<body onload="xyDisabled('xy');">
	<html:form action="/pjpyynyswh" method="post">
		<div class="title">
       		<div class="title_img" id="title_m">
         		<bean:message bundle="pjpyynys" key="pjpy_ynys_zhszcp" />
       		</div>
    	</div>
    	<input type="hidden" id="zyV" name="zyV" value=""/>
    	<input type="hidden" id="bjV" name="bjV" value=""/>
    	<input type="hidden" id="userType" name="userType" value="${userType }"/>
    	<input type="hidden" id="tableName" name="tableName" value="${tableName}"/>
    	<input type="hidden" id="realTable" name="realTable" value="${realTable}"/>
    	<input type="hidden" id="failInfo" name="failInfo" value="${failinfo }"/><!-- 批量删除信息提示 -->
    	<fieldset>
				<legend>
					条件选择
				</legend>
				<table width="100%" class="tbstyle">
					<thead>
						<tr>
						
							<td align="left">
							年级：
							<html:select property="nj" styleId="nj" style="width:90px"
							onchange="initZyList();initBjList()" styleClass="select">
								<html:option value=""></html:option>
								<html:options collection="njList" property="nj" labelProperty="nj"/>
							</html:select>
							&nbsp;&nbsp;
								学年：
								<html:select property="xn" style="width:90px" styleClass="select"
									styleId="xn">
									<html:options collection="xnList" property="xn"
										labelProperty="xn" />
								</html:select>
								&nbsp;&nbsp;
								<logic:equal name = "userType" value="stu" scope="session">
								学号:
								<input type="text" name = "xh" value="<bean:write name="userName" scope="session"/>" readonly="true" style="width:100px" class="inputtext"/>
								</logic:equal>
								<logic:notEqual name = "userType" value="stu" scope="session">
								学号:
								<html:text property="xh" styleId="xh" styleClass="inputtext"
								 style="width:100px"></html:text>
								 </logic:notEqual>
								&nbsp;&nbsp;
								姓名:
								<html:text property="xm" styleId="xm" styleClass="inputtext"
								 style="width:100px"></html:text>
							</td>
							<td width="10" rowspan="2" align="center" valign="middle">
								<input type="hidden" name="go" value="a" />
								<button type="button" class="button2" style="height:40px" id="search_go"
									onclick="dataQryChk('ynys_zhszcpqry.do');">
									查询
								</button>
							</td>
						</tr>
						<tr>
							<td align="left" nowrap>
								<bean:message key="lable.xsgzyxpzxy" />：
								<html:select property="xydm" onchange="initZyList();initBjList()"
								styleClass="select" style="width:180px" styleId="xy">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select>
								专业：
								<html:select property="zydm" onchange="initBjList()" style="width:180px" 
								styleClass="select" styleId="zy">
									<html:option value=""></html:option>
									<html:options collection="zyList" property="zydm"
										labelProperty="zymc" />
								</html:select>
								班级：
								<html:select property="bjdm" style="width:180px" 
								styleClass="select" styleId="bj">
									<html:option value=""></html:option>
									<html:options collection="bjList" property="bjdm"
										labelProperty="bjmc" />
								</html:select>
							</td>
						</tr>
					</thead>
				</table>
			</fieldset>
			<div id="result">
				<div class="searchcontent">
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
								<font color="blue">提示：单击表头可以进行排序;</font>
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
										style="cursor:hand;" ondblclick="">
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
					<logic:equal value="yes" name="writeAble" scope="request">
	                	<div class="buttontool" align="center" id="btn" style="position:absolute;width:95%;top:100px">
							<logic:notEqual value="xy" name="userType">
								<button type="button" class="button2" id="btn_shtg" style="width:80px"
								onclick="shandSubmit('ynys_zhszcpshres.do','tg');">
								审核通过
							</button><%--
							&nbsp;&nbsp;&nbsp;
							<button type="button" class="button2" id="btn_shbtg" style="width:80px"
								onclick="shandSubmit('ynys_zhszcpshres.do','btg');">
								审核不通过
							</button>--%>
							&nbsp;&nbsp;&nbsp;
							</logic:notEqual>
							<button type="button" class="button2" id="btn_add" style="width:80px"
								onclick="acount('ynys_zhszcpautoacount.do');">
								自动计算
							</button>
							&nbsp;&nbsp;&nbsp;
							<button type="button" class="button2" id="btn_add" style="width:80px"
								onclick="showTopWin('ynys_zhszcpadd.do','600','420')">
								增加
							</button>
							&nbsp;&nbsp;&nbsp;
							<button type="button" class="button2" id="btn_modi" style="width:80px"
								onclick="modiAndDel('ynys_zhszcpview.do?pkValue=','modi','600','420')">
								修改
							</button>
							&nbsp;&nbsp;&nbsp;
							<button type="button" class="button2" id="btn_del" style="width:80px"
								onclick="deldata('ynys_zhszcpdel.do')">
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
								<a href="xlsDown/ynys_zhszcpb.xls"
										target="_blank">模板下载</a>
						</div>
					</logic:equal>
					<div id="tmpdiv"></div>
				</div>
			</div>
	</html:form>
	 <script type="text/javascript" src="js/bottomButton.js"></script>
	 <script type="text/javascript">
	 	function acount(url) {
	 		if (document.getElementById('userType').value=='xx' ||
	 		 document.getElementById('userType').value=='admin') {
	 			if (document.getElementById('xn').value=='' || document.getElementById('xy').value=='') {
	 				alert('请选择要自动计算的学年,<bean:message key="lable.xsgzyxpzxy" />!');
	 				return;
	 			}
	 		}
	 		if (document.getElementById('xn').value=='') {
	 			alert('请选择要自动计算的学年!');
	 			return;
	 		}
	 		if (confirm('该次操作将以学年,<bean:message key="lable.xsgzyxpzxy" />为单位自动计算学生综合素质测评分,可能耗费较长时间,要继续吗?')) {
	 			refreshForm(url);
	 			BatAlert.showTips('正在操作，请等待...');
	 		}
	 		return;
	 	}
	 </script>
	 <logic:present name="deleted">
	 	<logic:equal value="yes" name="deleted">
	 		<script>
	 			alert('操作成功！');
	 			document.getElementById('search_go').onclick();
	 		</script>
	 	</logic:equal>
	 	<logic:equal value="no" name="deleted">
	 		<script>
	 			alert('' + document.getElementById('failInfo').value());
	 			document.getElementById('search_go').onclick();
	 		</script>
	 	</logic:equal>
	 </logic:present> 
	  <logic:present name="inserted">
	 	<logic:equal value="yes" name="inserted">
	 		<script>
	 			alert('操作成功！');
	 			document.getElementById('search_go').onclick();
	 		</script>
	 	</logic:equal>
	 	<logic:equal value="no" name="inserted">
	 		<script>
	 			alert('操作失败!');
	 			document.getElementById('search_go').onclick();
	 		</script>
	 	</logic:equal>
	 </logic:present> 
</body>