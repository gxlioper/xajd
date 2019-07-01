<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%><!-- 头文件 -->
<script type="text/javascript" src="pjpy/ahjg/ahjgjs/ahjgjs.js"></script>
<script language="javascript" src="/xgxt/pjpy/xibeierminyuan/js/xbemyJs.js"></script>
<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
<script type="text/javascript" src="pjpy/ynys/ynysJs/ynys.js">
</script>
<body onload="xyDisabled('xy');">
	<html:form action="/pjpyynyswh" method="post">
		<input type="hidden" name="userType" id="userType" value="${userType }"/>
		<input type="hidden" name="realTable" id="realTable" value="${realTable }"/>
		<input type="hidden" name="zyV" id="zyV"/>
		<input type="hidden" name="bjV" id="bjV"/>
		<input type="hidden" id="failInfo" name="failInfo" value="${failinfo }"/><!-- 批量审核信息提示 -->
		<div class="title">
			<div class="title_img" id="title_m">
				<bean:message key="pjpy_ynys_xjbjsh" bundle="pjpyynys"/>
			</div>
		</div>
		<fieldset>
				<legend>
					条件选择
				</legend>
				<table width="100%" class="tbstyle">
					<thead>
						<tr>
							<td align="left">
								学年：
								<html:select property="xn" style="width:120px" disabled="true" styleId="xn">
									<html:options collection="xnList" property="xn"
										labelProperty="xn" />
								</html:select>
								&nbsp;&nbsp;&nbsp;<bean:message key="lable.xsgzyxpzxy" />：
								<html:select property="xydm" style="width:180px" styleId="xy"
								onchange="initZyList();initBjList()">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select>
								&nbsp;&nbsp;&nbsp;年级：
								<html:select property="nj" style="width:90px;padding-left:80px"
								onchange="initZyList();initBjList()">
									<html:option value=""></html:option>
									<html:options collection="njList" property="nj"
										labelProperty="nj" />
								</html:select>
								&nbsp;&nbsp;&nbsp;
								学号:
								<html:text property="xh" styleId="xh"
								style="width:100px" styleClass="inputtext"></html:text>
							</td>
							<td width="10" rowspan="2" align="center" valign="middle">
								<input type="hidden" name="go" value="a" />
								<button type="button" class="button2" style="height:40px" id="search_go"
									onclick="if (document.getElementById('shxm').value=='')
									 {alert('请选择要审核的项目!');return}
									  else refreshForm('ynys_xjbjqry.do')">
									查询
								</button>
							</td>
						</tr>
						<tr>
							<td align="left" nowrap>
								专业：
								<html:select property="zydm" onchange="initBjList()" style="width:180px" styleId="zy">
									<html:option value=""></html:option>
									<html:options collection="zyList" property="zydm"
										labelProperty="zymc" />
								</html:select>
								&nbsp;&nbsp;&nbsp;
								班级:
								<html:select property="bjdm" styleId="bj" style="width:180px" styleClass="select">
									<html:option value=""></html:option>
									<html:options collection="bjList" property="bjdm" labelProperty="bjmc"/>
								</html:select>
								&nbsp;&nbsp;&nbsp;审核项目：
								<html:select property="shxm" style="width:180px" 
								styleId="shxm" onchange="refreshForm('ynys_xjbjsh.do?shxm='+document.getElementById('shxm').value);">
									<html:option value=""></html:option>
									<html:options collection="shList" property="en"
										labelProperty="cn" />
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
										style="cursor:hand;" ondblclick="modiAndDel('ynys_xjbjshone.do?pkValue=','modi','650','530')">
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
							<button type="button" class="button2" id="btn_shtg" style="width:80px"
								onclick="shandSubmit('ynys_xjbjshres.do','tg');">
								审核通过
							</button>
							&nbsp;&nbsp;
							<button type="button" class="button2" id="btn_shbtg" style="width:80px"
								onclick="shandSubmit('ynys_xjbjshres.do','btg');">
								审核不通过
							</button>
							&nbsp;&nbsp;
							<button type="button" class="button2" id="btn_print"
								onclick="window.open('ynys_printxjbj.do?bjdm=' + document.getElementById('bj').value)"
								style="width:80px">
								报表打印
							</button>	
						</div>
					</logic:equal>
				</div>
			</div>
	</html:form>
	<script type="text/javascript" src="js/bottomButton.js"></script>
	 <logic:present name="result">
	 	<logic:equal value="yes" name="result">
	 		<script>
	 			alert('操作成功！');
	 			document.getElementById('search_go').onclick();
	 		</script>
	 	</logic:equal>
	 	<logic:equal value="no" name="result">
	 		<script>
	 			alert('' + document.getElementById('failInfo').value());
	 			document.getElementById('search_go').onclick();
	 		</script>
	 	</logic:equal>
	 </logic:present> 
</body>