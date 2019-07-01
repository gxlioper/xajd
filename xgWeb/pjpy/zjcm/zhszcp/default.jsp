<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini" %><!-- 头文件 -->
<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
<script type="text/javascript" src="pjpy/ahjg/ahjgjs/ahjgjs.js"></script>
<script type="text/javascript" src="pjpy/ynys/ynysJs/ynys.js"></script>
<script type="text/javascript" src="pjpy/zjcm/zjcmjs/zjcjjs.js">
</script>
 
<script type="text/javascript" src="/xgxt/dwr/interface/chgZhcpbl.js"></script>
<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
<script type="text/javascript">
<!--
	function selectAlls(){
	var checkBoxArr = document.getElementsByName("cbv");
	var selall = document.getElementById('cb');
	if(selall.checked==true){
		for(var i=0;i<checkBoxArr.length;i++){
			if (checkBoxArr[i].disabled==true) {
				checkBoxArr[i].checked = false;	
			} else {
				checkBoxArr[i].checked = true;
			}
		}
	} else {
		for(var i=0;i<checkBoxArr.length;i++){
			checkBoxArr[i].checked = false;
		}
	}
}
//-->
</script>
<body onload="xyDisabled('xy');">
	<html:form action="/pjpyzjcmwh.do" method="post">
		<div class="title">
       		<div class="title_img" id="title_m">
         		当前所在位置：评奖评优 - 信息维护 - 综合素质测评 - ${titname }
       		</div>
    	</div>
    	<input type="hidden" id="zyV" name="zyV" value=""/>
    	<input type="hidden" id="bjV" name="bjV" value=""/>
    	<input type="hidden" id="userType" name="userType" value="${userType }"/>
    	<input type="hidden" id="tableName" name="tableName" value="${tableName}"/>
    	<input type="hidden" id="realTable" name="realTable" value="${realTable}"/>
    	<input type="hidden" id="tmp" name="tmp" value="${tmp }"/>
    	<!-- 批量删除信息提示 -->
    	<input type="hidden" id="failInfo" name="failInfo" value="${failinfo }"/>
    	<input type="hidden" name="userName" id="userName" value="${userName }"/>
    	<input type="hidden" name="isFdy" id="isFdy" value="${isFdy }"/>
    	<fieldset>
				<legend>
					维护项目
				</legend>
				<table width="100%" class="tbstyle">
					<thead>
						<tr>
							<td colspan="2" align="left">
								维护项目：
								<html:radio property="szlx" value="0" style="cursor:hand"
								onclick="refreshForm('pjpy_zjcm_zhszcpwh.do?act=szlx')"/>德育基本素质分
								<html:radio property="szlx" value="1" style="cursor:hand"
								onclick="refreshForm('pjpy_zjcm_zhszcpwh.do?act=szlx')"/>智育成绩分
								<html:radio property="szlx" value="2" style="cursor:hand"
								onclick="refreshForm('pjpy_zjcm_zhszcpwh.do?act=szlx')"/>体育成绩分
								<html:radio property="szlx" value="3" style="cursor:hand"
								onclick="refreshForm('pjpy_zjcm_zhszcpwh.do?act=szlx')"/>实践与创新能力分
								<html:radio property="szlx" value="4" style="cursor:hand"
								onclick="refreshForm('pjpy_zjcm_zhszcpwh.do?act=szlx')"/>综合素质测评分
							</td>
						</tr>
					</thead>
				</table>
		</fieldset>
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
								学期：
								<html:select property="xq" style="width:90px" styleClass="select"
									styleId="xq">
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
								<input type="hidden" name="go" value="a" />
								<button type="button" class="button2" style="height:40px" id="search_go"
									onclick="refreshForm('pjpy_zjcm_zhszcpwh.do')">
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
											<input type="checkbox" id="cb" name="cb" onclick="selectAlls()" />
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
										style="cursor:hand;" ondblclick="modiTab('view')">
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
							<!-- 通过分页 -->
							<TABLE width="99%" id="rsTable1" class="tbstyle">
											<TR>
												<TD height=3></TD>
											</TR>
											<TR>
												<TD>
													<jsp:include flush="true"
														page="/sjcz/turnpage.jsp?form=pjpyZjcmActionForm"></jsp:include>
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
							<logic:notEqual value="yes" name="zhsz">
								<button type="button" class="button2" id="btn_add" style="width:80px"
								onclick="addTab()">
								增加
								</button>
							&nbsp;&nbsp;&nbsp;
							</logic:notEqual>
							<logic:equal value="yes" name="zhsz">
								<button type="button" class="button2" id="btn_add" style="width:80px"
								onclick="showTopWin('pjpy_zjcm_zhszcpblsz.do',700,230)">
								设置比例
								</button>
								&nbsp;&nbsp;
								<button type="button" class="button2" id="btn_add" style="width:80px"
								onclick="autocount('pjpy_zjcm_zhszcpwh.do?act=autocount')">
								自动计算
							</button>
							&nbsp;&nbsp;&nbsp;
							</logic:equal>
							<button type="button" class="button2" id="btn_modi" style="width:80px"
								onclick="modiTab()">
								修改
							</button>
							&nbsp;&nbsp;&nbsp;
							<button type="button" class="button2" id="btn_del" style="width:80px"
								onclick="delTab('pjpy_zjcm_zhszcpwh.do')">
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
								<a href="xlsDown/zjcm_szcjb.xls"
										target="_blank">模板下载</a>
						</div>
					</logic:equal>
					<div id="tmpdiv"></div>
				</div>
			</div>
	</html:form>
	 <script type="text/javascript" src="js/bottomButton.js"></script>
	 <!-- 操作提示 -->
	 <jsp:include flush="true" page="/syscommon/deleteprompt.jsp"></jsp:include>
</body>