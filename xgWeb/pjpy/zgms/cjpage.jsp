<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%><!-- 头文件 -->
<script type="text/javascript" src="pjpy/zjjd/zjjdjs/zjjdjs.js">
</script>
<script type="text/javascript" src="pjpy/xibeierminyuan/js/xbemyJs.js"></script>
<script type="text/javascript" src="js/BatAlert.js"></script>
<script type="text/javascript">
<!--
	function tb() {
		if (document.getElementById('xn').value=='') {
			alert('学年条件为必选!');
			return;
		} else { 
			if (confirm('该操作将以学年为单位获取教务学生成绩数据,可能耗费较长时间要继续吗?')) {
				refreshForm('pjpy_zgms_cjtb.do');
				BatAlert.showTips('正在操作，请等待...');
			}
			return;
		}
	}
//-->
</script>
<body onload="xyDisabled('xy');">
	<html:form action="/pjpyzgmswh" method="post">
		<div class="title">
       		<div class="title_img" id="title_m">
         		当前所在位置：评奖评优 - 信息维护 - 学生成绩维护
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
							<html:select property="nj" styleId="nj" styleClass="select"
							 onchange="initZyList();initBjList()" style="width:90px">
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
								学期：
								<html:select property="xq" 
								styleClass="select" style="width:90px;padding-left:80px" styleId="xq">
									<html:option value=""></html:option>
									<html:options collection="xqList" property="xqdm"
										labelProperty="xqmc" />
								</html:select>
								&nbsp;&nbsp;
								学号:
								<html:text property="xh" styleId="xh" styleClass="inputtext"
								 style="width:100px"></html:text>
								 &nbsp;&nbsp;
								 姓名:
								 <html:text property="xm" styleId="xm" styleClass="inputtext"
								 style="width:100px"></html:text>
							</td>
							<td width="10" rowspan="2" align="center" valign="middle">
								<input type="hidden" name="act" value="go" />
								<button class="button2" style="height:40px" id="search_go" 
									onclick="dataQryChk('pjpy_zgms_cjwh.do');">
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
								<logic:equal name = "xxdm" value="10355" scope="session">
								<logic:iterate name="rs" id="s">
									<tr onclick="rowOnClick(this)"
										style="cursor:hand;" ondblclick="modiAndDel('pjpy_zgms_cjview.do?act=view&pkValue=','modi','600','450');">
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
								</logic:equal>
								<logic:notEqual name = "xxdm" value="10355" scope="session">
								<logic:iterate name="rs" id="s">
									<tr onclick="rowOnClick(this)"
										style="cursor:hand;">
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
								</logic:notEqual>
							</table>
							<TABLE width="99%" id="rsTable1" class="tbstyle">
											<TR>
												<TD height=3></TD>
											</TR>
											<TR>
												<TD>
													<jsp:include flush="true"
														page="/sjcz/turnpage.jsp?form=pjpyZgmsActionForm"></jsp:include>
												</TD>
											</TR>
											<TR>
												<TD height=3></TD>
											</TR>
										</TABLE>
						</fieldset>
					</logic:notEmpty>
					<logic:equal value="yes" name="writeAble" scope="request">
	                	<div class="buttontool" align="center" id="btn" style="position:absolute;width:95%;top:100px">
							<button class="button2" id="btn_add" style="width:80px"
								onclick="tb()">
								成绩同步
							</button>
							&nbsp;&nbsp;&nbsp;
							<button class="button2" onclick="dataExport()" style="width:80px">
									导出数据
								</button>
						</div>
					</logic:equal>
					<div id="tmpdiv"></div>
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
							alert('' + document.getElementById('failinfo').value);
							document.getElementById('search_go').click();
						</script>
					</logic:equal>
					<logic:equal value="ok" name="deleted">
						<script>
							alert('操作成功!');
						</script>
					</logic:equal>
					<logic:equal value="nok" name="deleted">
						<script>
							alert('操作失败!');
						</script>
					</logic:equal>
				</logic:present>
	</html:form>
</body>