<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者 屈朋辉-->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="js/xszz/xszzComm.js"></script>
		<script type='text/javascript'
			src='/xgxt/dwr/interface/getXszzInfo.js'></script>
		<script type="text/javascript" src="js/rcsw/rcswFunction.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/String.js"></script>
		<script type="text/javascript">
			function loadXmInfo(obj){
				//项目代码
				var xmdm = obj.value;
				
				if ("" != xmdm){
					getXszzInfo.getXszzInfoByXmdm(xmdm,function(data){
						
						//申请周期
						var sqzq = data.sqzq;
						//审核级别
						var shjb = data.shjb;
					
						$('sqzq').value = sqzq;
					
						if (sqzq == "学年"){
							$('xn').disabled=false;
							$('nd').disabled=true;
							$('xq').disabled=true;
						} else if (sqzq == "学期"){
							$('xn').disabled=false;
							$('nd').disabled=true;
							$('xq').disabled=false;
						} else if (sqzq == "年度"){
							$('xn').disabled=true;
							$('nd').disabled=false;
							$('xq').disabled=true;
						} else{
							$('xn').disabled=true;
							$('nd').disabled=true;
							$('xq').disabled=true;
						}
						
						
						//审核通过的记录才续办，
						//所以要根据审核级别把对应的审核字段置为通过作为查询条件
						if (shjb == "一级审核"){
							$('shzt1').value = "通过";
							$('shzt2').value = "";
							$('shzt3').value = "";
						} else if (shjb == "二级审核"){
							$('shzt1').value = "";
							$('shzt2').value = "通过";
							$('shzt3').value = "";
						} else if (shjb == "三级审核"){
							$('shzt1').value = "";
							$('shzt2').value = "";
							$('shzt3').value = "通过";
						}
					});	
				}
			}
			
			
			function plsb(){
			
				var url = '/xgxt/commXszz.do?method=zzxbManage&doType=zzxb';
				var sqzq = $('sqzq').value;
			
				if ("学年"==sqzq){
					saveUpdate(url,"select_xn");
				}
				
				if ("学期"==sqzq){
					saveUpdate(url,"select_xn-select_xq");
				}
				
				if ("年度"==sqzq){
					saveUpdate(url,"select_nd");
				}
				
			}
			
			function updateZzxb(doType){
				var url = '/xgxt/commXszz.do?method=zzxbUpdate';
				
				if (curr_row == null){
					alert("请选择您要修改的数据!");
					return false;
				} else {
					var isxb = curr_row.cells[curr_row.cells.length-1].innerText.trim();
					var pkValue = curr_row.cells[0].getElementsByTagName("input")[0].value;
					
					if ("新贷"==isxb && "update"==doType){
						alert("非续贷数据不能修改");
						return false;
					} else {
						url += "&pk="+pkValue;
						url += "&doType="+doType;
					}
					
				}
				showTopWin(url,800,600);
			}
			
			
			function delData(){
				var flg =true;
				if (isChecked("primarykey_checkVal")){
					for (var i = 0 ; i < array.length ; i++){
						var obj = array[i].parentNode.parentNode;
						var text = obj.cells[obj.cells.length-1].innerText.trim();
						if ("新贷"==text){
							alert("非续贷数据不能删除，请确认!");
							flg = false;
							break;
						}
					}
				}else {
					flg = false;
				}
				
				if (flg){
					sumitInfo('/xgxt/commXszz.do?method=zzxbManage','del');
				}
			}
			
			
			function plsbCheck(){
				
			
				var flg = true;
				var sqzq = $('sqzq').value;
				
				if (sqzq=="仅一次" || sqzq=="无周期"){
					alert("当前资助项目的申请周期为:"+sqzq+",不开放续办!建议您设置此项目的申请周期为学年、年度或学期!");
					return false;
				}
				
				sqzq = sqzq=="学期" ? "学年、学期" : sqzq;
			
				if (isChecked('primarykey_checkVal')){
				
					var text = array[0].parentNode.getElementsByTagName("input")[1].value;
					
					for (var i = 0 ; i<array.length ;i++){
						var tempText = array[i].parentNode.getElementsByTagName("input")[1].value;
						
						if (text != tempText){
							flg = false;
							array = new Array();
							alert("不是同一申请周期内的数据不能进行批量续办,建议您选择"+sqzq+"并查询后再进行批量续办!");
							break;
						}
					}
				
					if (flg){
						viewTempDiv('资助续办','zzxbDiv',500,280);
					}
					
				}
			}
			
		</script>
	</head>

	<body onload="loadXmInfo($('xmdm'))">
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		<!-- 标题 end-->
		<html:form action="/commXszz">
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- 只有审核通过的数据才能进行续办 -->
			<input type="hidden" name="shzt1" id="shzt1" />
			<input type="hidden" name="shzt2" id="shzt2" />
			<input type="hidden" name="shzt3" id="shzt3" />
			<input type="hidden" name="shzt3" id="shzt3" />
			<input type="hidden" name="sqzq" id="sqzq" />
			<!-- 隐藏域 end-->
			<div class="toolbox">
				<!-- 按钮 -->
				<logic:equal value="yes" name="writeAble">
					<div class="buttonbox">
						<ul>
							<li>
								<a href="#" onclick="plsbCheck();" class="btn_sr">批量续办</a>
							</li>
							<li>
								<a href="#"
									onclick="updateZzxb('update');"
									class="btn_xg">修改</a>
							</li>
							<li>
								<a href="#"
									onclick="delData()"
									class="btn_sc">删除</a>
							</li>
							<li>
								<a href="#" onclick="impInfo()" class="btn_dr">导入</a>
							</li>
							<li>
								<a href="#" onclick="expInfo()" class="btn_dc">导出</a>
							</li>
						</ul>
					</div>
				</logic:equal>
				<!-- 按钮 end-->
				<!-- 过滤条件 -->
				<div class="searchtab">
					<table width="100%" border="0">
						<tfoot>
							<tr>
								<td colspan="10">
									<div class="btn">
										<button type="button"  class="btn_cx" id="search_go"
											onclick="if($('xmdm').value==''){alert('请选择项目')}else{allNotEmpThenGo('/xgxt/commXszz.do?method=zzxbManage&doType=query')}">
											查 询
										</button>
										<button type="button"  class="btn_cz" id="btn_cz" onclick="">
											重 置
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
						<tbody>
							<!-- 第一行 -->
							<tr>
								<th>
									学年
								</th>
								<td>
									<html:select property="xn" style="width: 150px" styleId="xn">
										<html:options collection="xnList" property="xn"
											labelProperty="xn" />
									</html:select>
								</td>
								<th>
									年度
								</th>
								<td>
									<html:select property="nd" style="" styleId="nd"
										style="width: 150px">
										<html:options collection="ndList" property="nd"
											labelProperty="nd" />
									</html:select>
								</td>
								<th>
									学期
								</th>
								<td>
									<html:select property="xq" style="" styleId="xq"
										style="width: 150px">
										<html:option value=""></html:option>
										<html:options collection="xqList" property="xqdm"
											labelProperty="xqmc" />
									</html:select>
								</td>
							</tr>
							<!-- 第二行 -->
							<tr>
								<th>
									年级
								</th>
								<td>
									<html:select property="nj" style="width: 150px" styleId="nj"
										onchange="initZyList();initBjList()">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj"
											labelProperty="nj" />
									</html:select>
								</td>
								<th>
									学号
								</th>
								<td>
									<!-- 学生用户 -->
									<logic:equal name="userType" value="stu" scope="session">
										<html:text property="xh" styleId="xh" style="" value="${userName }" readonly="true" />
									</logic:equal>
									<!-- 学生用户 -->
									<logic:notEqual name="userType" value="stu" scope="session">
										<html:text property="xh" styleId="xh" style="" maxlength="20" />
									</logic:notEqual>
								</td>
								<th>
									姓名
								</th>
								<td>
									<html:text property="xm" styleId="xm" style="" maxlength="20" />
								</td>
							</tr>
							<!-- 第三行 -->
							<tr>
								<th>
									<bean:message key="lable.xsgzyxpzxy" />
								</th>
								<td>
									<!-- 是学院用户 -->
									<logic:equal name="userType" value="xy" scope="session">
										<html:hidden property="xydm" value="${userDep }" />
										<html:select property="xydm" style="width: 150px" styleId="xy"
											disabled="true">
											<html:option value=""></html:option>
											<html:options collection="xyList" property="xydm"
												labelProperty="xymc" />
										</html:select>
									</logic:equal>
									<!-- 是学院用户 end -->

									<!-- 非学院用户 -->
									<logic:notEqual name="userType" value="xy" scope="session">
										<html:select property="xydm" style="width: 150px" styleId="xy">
											<html:option value=""></html:option>
											<html:options collection="xyList" property="xydm"
												labelProperty="xymc" />
										</html:select>
									</logic:notEqual>
									<!-- 非学院用户 end -->
								</td>
								<th>
									专业
								</th>
								<td>
									<html:select property="zydm" style="width: 150px" styleId="zy"
										onchange="initBjList()">
										<html:option value=""></html:option>
										<html:options collection="zyList" property="zydm"
											labelProperty="zymc" />
									</html:select>
								</td>
								<th>
									班级
								</th>
								<td>
									<html:select property="bjdm" style="width: 150px" styleId="bj">
										<html:option value=""></html:option>
										<html:options collection="bjList" property="bjdm"
											labelProperty="bjmc" />
									</html:select>
								</td>
							</tr>
							<!-- 第四行 -->
							<tr>
								<th>
									项目
								</th>
								<td>
									<!-- onchange事件加载资助项目的申请周期 -->
									<html:select property="xmdm" style="width: 150px"
										styleId="xmdm" onchange="loadXmInfo(this)">
										<%--										<html:option value=""></html:option>--%>
										<%--										<html:options collection="xmList" property="xmdm" labelProperty="xmmc" />--%>
										<!-- 
												现在就浙江科技要求助学贷款有此功能，若有其它的再开放！
												国家助学贷款有个新做贷和续贷之分
											 -->
										<html:option value="5003">国家助学贷款</html:option>
									</html:select>
								</td>
								<th>
									申请开始日期
								</th>
								<td>
									<html:text property="kssj" styleId="kssj"
										onblur="dateFormatChg(this)" style="cursor:hand;"
										onclick="return showCalendar('kssj','y-mm-dd');" />
								</td>
								<th>
									申请结束日期
								</th>
								<td>
									<html:text property="jssj" styleId="jssj"
										onblur="dateFormatChg(this)" style="cursor:hand;"
										onclick="return showCalendar('jssj','y-mm-dd');" />
								</td>
							</tr>
							<!-- 话说这是第五行 -->
							<tr>
								<th>
									贷款状态
								</th>
								<td>
									<html:select property="isxb" style="width:150px" styleId="isxb">
										<html:option value=""></html:option>
										<html:option value="新贷">新贷</html:option>
										<html:option value="续贷">续贷</html:option>
									</html:select>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
				<!-- 过滤条件 end-->
				<!-- 查询结果-->
				<div class="formbox">
					<h3 class="datetitle_01">
						<span> 查询结果&nbsp;&nbsp; <logic:notEmpty name="rsList">
								<font color="blue">提示：单击表头可以排序；双击记录可查看详细信息.</font>
							</logic:notEmpty> <logic:empty name="rsList">
								<font color="red">未找到任何记录！</font>
							</logic:empty> </span>
					</h3>
					<logic:notEmpty name="rsList">
						<div class="con_overlfow">
							<table summary="" class="dateline tablenowrap" align="" width="100%">
								<!-- 表头 -->
								<thead>
									<tr align="center" style="cursor:hand">
										<td>
<%--											<input type="checkbox" id="selall" name="selall"--%>
<%--												onclick="selAll()" />--%>
										</td>
										<logic:iterate id="tit" name="topTr" offset="0">
											<td id="<bean:write name="tit" property="en"/>"
												onclick="tableSort(this)" nowrap>
												<bean:write name="tit" property="cn" />
											</td>
										</logic:iterate>
									</tr>
								</thead>
								<!-- 表头 end-->
								<!--内容 -->
								<logic:iterate name="rsList" id="rs" indexId="index">
									<tr onclick="rowOnClick(this);" style="cursor:hand"
										ondblclick="updateZzxb('view');">
										<td align="center">
											<input type="checkbox" id="checkVal"
												name="primarykey_checkVal" value="${rs.pk }" />
											<input type="hidden" name="zq" value="${rs.sqzq }"/>
										</td>
										<td align="center">
											${rs.xh }
										</td>
										<td align="center">
											${rs.xm }
										</td>
										<td align="center">
											${rs.xb }
										</td>
										<td align="center">
											${rs.nj }
										</td>
										<td align="center">
											${rs.xymc }
										</td>
										<td align="center">
											${rs.zymc }
										</td>
										<td align="center">
											${rs.bjmc }
										</td>
										<td align="center">
											${rs.sqsj }
										</td>
										<td align="center">
											${rs.isxb }
										</td>
									</tr>
								</logic:iterate>
								<!--内容 end -->
							</table>
						</div>
						<!--分页-->
						<jsp:include flush="true"
							page="/sjcz/turnpage.jsp?form=xszzTyForm"></jsp:include>
						<!--分页end-->
					</logic:notEmpty>
				</div>
				<!-- 查询结果 end-->




				<!-- 资助续办弹出层 -->
				<div class="open_win01" style="display:none;" id="zzxbDiv">
					<table width='300' class='formlist'>
						<thead>
							<tr>
								<td colspan="2">
									<span>请选择您要续办到的${xmInfo.sqzq }</span>
								</td>
							</tr>
						</thead>
						<tbody>
							<logic:present name="xmInfo">
								<logic:equal value="学年" name="xmInfo" property="sqzq">
									<tr>
										<th width="20%">
											<font color="red">*</font>学年
										</th>
										<td>
											<html:select property="save_xn" styleId="select_xn" style="width:150px">
												<html:options collection="xnList" property="xn"
													labelProperty="xn" />
											</html:select>
										</td>
									</tr>
								</logic:equal>
								<logic:equal value="学期" name="xmInfo" property="sqzq">
									<tr>
										<th  width="20%">
											<font color="red">*</font>学年
										</th>
										<td>
											<html:select property="save_xn" styleId="select_xn"  style="width:150px">
												<html:options collection="xnList" property="xn"
													labelProperty="xn" />
											</html:select>
										</td>
									</tr>
									<tr>
										<th  width="20%">
											<font color="red">*</font>学期
										</th>
										<td>
											<html:select property="save_xq" styleId="select_xq"  style="width:150px">
												<html:options collection="xqList" property="xqdm"
													labelProperty="xqmc" />
											</html:select>
										</td>
									</tr>
								</logic:equal>
								<logic:equal value="年度" name="xmInfo" property="sqzq">
									<tr>
										<th  width="20%">
											<font color="red">*</font>年度
										</th>
										<td>
											<html:select property="save_nd" styleId="select_nd"  style="width:150px">
												<html:options collection="ndList" property="nd"
													labelProperty="nd" />
											</html:select>
										</td>
									</tr>
								</logic:equal>
							</logic:present>
							<tr>
								<th  width="20%">备注<br/><font color="red"><限500字></font></th>
								<td>
									<html:textarea property="save_bz" style="width:85%" rows="5" onblur="checkLen(this,500)"></html:textarea>
								</td>
							</tr>
							
							
						<tbody>
						<tfoot>
							<tr>
								<td colspan="2">
									<div class="bz">
										"
										<span class="red">*</span>"为必填项
									</div>
									<div class='btn'>
										<button type="button"  onclick='plsb()'>
											保存
										</button>
										<button type="button"  onclick="hiddenMessage(true,true);return false;">
											关闭
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
		</html:form>
		<!-- 提示信息 -->
		<%@ include file="../other/tsxx.jsp"%>
		<!-- 提示信息 end-->

		<!-- 导出控制的层 -->
		<%@ include file="/comm/exp/commExp.jsp"%>
		<!-- 导出控制的层 end-->
	</body>
</html>
