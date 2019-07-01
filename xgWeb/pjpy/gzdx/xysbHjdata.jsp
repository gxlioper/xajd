<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>
<script language="javascript" src="/xgxt/pjpy/xibeierminyuan/js/xbemyJs.js"></script>
<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
<script type="text/javascript" src="pjpy/hbsf/hbsfjs.js"></script>
<script type="text/javascript" src="pjpy/pjpyjs/pjpy.js"></script>
<script type='text/javascript' src='/xgxt/dwr/interface/gzdxpjpy.js'></script>

<script type="text/javascript">
<!--
	function mdsb() {
		if (checkNotNull('dm-xy')) {
			var jyrs = 0;
			var sbrs = 0;
			var xn = document.getElementById('xn').value;
			var xydm = document.getElementById('xy').value;
			var lb = document.getElementById('lb').value;
			var dm = document.getElementById('dm').value;
			dwr.engine.setAsync(false);
				gzdxpjpy.getXyxzrs(xn, xydm, lb, dm, function(data){
					jyrs = data[0];
					sbrs = data[1]
				});
			dwr.engine.setAsync(true); 
		
			var cbv = document.getElementsByName("cbv");
			var cjtj = 0;
			var wjtj = 0;
			
			var checkLen = 0;
	
			//获取选中的记录条数
			if (cbv != null) {
				for (var i=0;i<cbv.length;i++) {
					if (cbv[i].checked) {
						checkLen++;
					}
				}
			}
			
			if (checkLen<=0) {
				alert("请选择要申报的数据!,单击行首的复选框即可.");
				return false;
			}
			//获取未选中，但审核通过的记录条数
			var trobj = document.getElementById("tobj");
			for (var j=0;j<trobj.rows.length;j++) {
				var chk = trobj.rows[j].cells[0].getElementsByTagName("input")[0].checked;
				//var xxsh = replaceBlankSpace(trobj.rows[j].cells[13].innerText);
				//alert(xxsh);
				var cj = replaceBlankSpace(trobj.rows[j].cells[9].innerText);
				var wj = replaceBlankSpace(trobj.rows[j].cells[11].innerText);
				//if (!chk && "通过" == xxsh) {
				//	checkLen++;
				//}
				//申报数据中是否有成绩挂科和处分的数据
				if (chk) {
					if (cj == '有') {
						cjtj++;
					}
					if (wj == '无') {
						wjtj++;
					}
				}
			}
			if (jyrs == 0) {
				alert("管理员尚未设置本<bean:message key="lable.xsgzyxpzxy" />该奖学金的获奖名额,不能进行申报!");
				return false;
			}
			if (jyrs != 0 && (parseInt(checkLen) + parseInt(sbrs)) > parseInt(jyrs)) {
				alert("当前申报的获奖数据已超设置的获奖人数,\n设置申报人数：" + jyrs + "人，当前申报人数：" + checkLen + "人，已申报人数：" + sbrs + "人.");
				return false;
			} else if (cjtj > 0) {
				alert("当前申报的获奖数据中有部分学生成绩不及格，不符合申报条件!");
				return false;
			} else if (wjtj > 0) {
				alert("当前申报的获奖数据中有部分学生受过处分且处分等级已超过学校设置的处分限制级别，不符合申报条件!");
				return false;				
			}
			if (confirm("确定要申报所选择的数据吗？")) {
					refreshForm("pjpy_gzdx_sbhjmd.do");
			} 
			
		} else {
			alert("请选择要申报的<bean:message key="lable.xsgzyxpzxy" />和奖项!");
			return false;
		}
	}
	function viewOne() {
		var lb = document.getElementById('lb').value;
		//var cj = curr_row.cells[9].innerText;
		//var wj = curr_row.cells[11].innerText;
		modiAndDel('pjpy_gzdx_viewHjsbxx.do?lb=' + lb +'&pkValue=','modi',650,500)
	}
	
	function searchBd() {
	
		if($('dm').value == ''){
			alert('请确定申报奖项！');
			return false;
		}
		if($('bjdm').value == ''){
			alert('需要确定其班级排名，请选择班级！');
			return false;
		}
		refreshForm('pjpy_gzdx_xysbHjdata.do?operType=query')
	}
//-->
</script>
<body onload="xyDisabled('xy');">
	<html:form action="/gzdxPjpy.do" method="post">
		<input type="hidden" name="zyV" id="zyV" value="" />
		<input type="hidden" name="bjV" id="bjV" value="" />
	
		<input type="hidden" name="pt" id="pt"/>
		<input type="hidden" name="userType" id="userType"
			value="${userType }" />
		<div class="title">
			<div class="title_img" id="title_m">
				当前位置: 评奖评优 - 获奖信息维护 - 申报获奖数据
			</div>
		</div>
		<fieldset>
			<legend>
				条件选择
			</legend>
			<table width="100%" class="tbstyle">
				<thead>
					<tr>
						<td align="" nowrap="nowrap">
							学年：
							<html:select property="xn" styleId="xn" style="width:90px"
								styleClass="select" disabled="true">
								<html:options collection="xnList" property="xn"
									labelProperty="xn" />
							</html:select>
							&nbsp;&nbsp;
							学号：
							<html:text property="xh" styleId="xh" styleClass="inputtext"
								style="width:100px">
							</html:text>
							&nbsp;&nbsp;申报奖项类别：
							<html:select property="lb" styleId="lb" style="width:100px" onchange="refreshForm('pjpy_gzdx_xysbHjdata.do')">
								<html:option value="jxj">奖学金</html:option>
								<html:option value="rych">荣誉称号</html:option>
							</html:select>
							&nbsp;&nbsp;申报奖项：
							<html:select property="dm" styleId="dm" >
								<html:option value=""></html:option>
								<html:options collection="dmList" property="dm" labelProperty="mc"/>
							</html:select>
						</td>
						<td width="10" rowspan="2" align="center" valign="middle">
							<button class="button2" style="height:40px" id="search_go"
								onclick="searchBd()">
								查询
							</button>
						</td>
					</tr>
					<tr>
						<td align="left" nowrap>
							年级：
							<html:select property="nj" styleId="nj" style="width:80px"
								styleClass="select" onchange="initZyList();initBjList()">
								<html:option value=""></html:option>
								<html:options collection="njList" property="nj"
									labelProperty="nj" />
							</html:select>
							&nbsp;&nbsp;
							<bean:message key="lable.xsgzyxpzxy" />：
							<html:select property="xydm" style="width:175px"
								styleClass="select" styleId="xy"
								onchange="initZyList();initBjList()">
								<html:option value=""></html:option>
								<html:options collection="xyList" property="xydm"
									labelProperty="xymc" />
							</html:select>
							&nbsp;&nbsp; 专业：
							<html:select property="zydm" style="width:170px" styleId="zy"
								styleClass="select" onchange="initBjList()">
								<html:option value=""></html:option>
								<html:options collection="zyList" property="zydm"
									labelProperty="zymc" />
							</html:select>
							&nbsp;&nbsp; 班级：
							<logic:notEmpty name="bjList">
							<html:select property="bjdm" style="width:180px" styleId="bj"
								styleClass="select">
								<html:option value=""></html:option>
								<html:options collection="bjList" property="bjdm"
									labelProperty="bjmc" />
							</html:select>
							</logic:notEmpty>
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
						${rsNum}
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<font color="blue">提示：单击表头可以进行排序；双击一行可以查看详细信息；</font>
					</legend>
					<table width="100%" id="rsTable" class="tbstyle">
						<thead>
							<tr align="center" style="cursor:hand">
								<td nowrap>
									<input type="checkbox" id="cb" name="cb" onclick="selectAll()" />
								</td>
								<logic:iterate id="tit" name="topTr" offset="3">
									<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)" nowrap>
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<tbody id="tobj">
						<logic:iterate name="rs" id="s">
							<tr onclick="rowOnClick(this)" style="cursor:hand;" 
								align="center" ondblclick="viewOne()">
								<td>
									<input type="checkbox" id="cbv" name="cbv"
										value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>"/>								
								</td>
								<logic:iterate id="v" name="s" offset="3">
									<td>
										<bean:write name="v" />
									</td>
								</logic:iterate>
							</tr>
						</logic:iterate>
						</tbody>
					</table>
					<TABLE width="99%" id="rsTable1" class="tbstyle">
											<TR>
												<TD height=3></TD>
											</TR>
											<TR>
												<TD>
													<jsp:include flush="true"
														page="/sjcz/turnpage.jsp?form=pjpyGzdxActionForm"></jsp:include>
												</TD>
											</TR>
											<TR>
												<TD height=3></TD>
											</TR>
										</TABLE>
				</fieldset>
			</logic:notEmpty>
			<div id="toolTipLayer" style="position:absolute; visibility: hidden" /></div>
				<logic:equal value="yes" name="writeAble" scope="request">
					<center>
						<div class="buttontool" id="btn"
							style="position: absolute;left:1%;top:100px" width="100%">
							<button class="button2" id="btn_add"
								onclick="mdsb()"
								style="width:80px">
								批量申报
							</button>
							&nbsp;&nbsp;
							<!-- 
							<button class="button2" id="btn_del"
								onclick="deldata('pjpy_gzdx_deleteHjxx.do')"
								style="width:80px">
								取消申报
							</button>
							 -->
						</div>
					</center>
				</logic:equal>
			<div id="tmpdiv"></div>
	</html:form>
		<logic:equal value="yes" name="writeAble" scope="request">
		<script language="javascript">
			document.getElementById("btn").style.pixelTop = document.body.clientHeight - 35;
			document.getElementById("btn").style.width = "96%";
			window.setInterval("initBTNTool('btn')",1);
		</script>
		</logic:equal>
		<logic:present name="result">
			<logic:equal value="true" name="result">
				<script>
					alert('操作成功!');
					document.getElementById('search_go').onclick();
				</script>
			</logic:equal>
			<logic:equal value="false" name="result">
				<script>
					alert('操作失败!');
					document.getElementById('search_go').onclick();
				</script>
			</logic:equal>
		</logic:present>
</body>
