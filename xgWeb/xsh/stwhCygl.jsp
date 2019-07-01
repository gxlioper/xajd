<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="pjpy/xibeierminyuan/js/xbemyJs.js"></script>
		<script type="text/javascript" src="pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
		<script type="text/javascript" src="pjpy/czxx/czxx.js"></script>
		<script type="text/javascript">
			function showStu() {
				if (null == curr_row) {
					alert('请选择一行');
				} else {
					var xh = curr_row.getElementsByTagName('input')[0].value;
					var url = '/xgxt/stu_info_details.do?xh='+xh;
					showTopWin(url,'820','600');
				}
			}
			
			function saveData(url) {
				var checkBoxArr = document.getElementsByName("primarykey_cbv");
				var flag = false;
				for(var i=0;i<checkBoxArr.length;i++){
					if(checkBoxArr[i].checked==true){
						flag = true;
					}
				}
				if (flag){
					if (confirm('确定要保存所选择的数据吗？')){
						document.forms[0].action = url;
						document.forms[0].submit();
						if ($("pt")) {
							BatAlert.showTips('正在操作，请等待...');
						}
					}
				}else{
					alert("没有选择相应记录，请选择之后再进行操作！！");
				}
			}
			
			// 社团注册
			function stzc(url){
			
				var xxdm='${xxdm}';
				
				if(xxdm=="10653"){
					showStzc();
				}else{
					saveData(url);
				}
			}
			
			function showStzc(){
				tipsWindown("系统提示","id:div_stzc","350","150","true","","true","id");
			}
		</script>
	</head>
	<body onload="xyDisabled('xy');">
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>${title }</a>
			</p>
		</div>


		<html:form action="/xsh" method="post">
			<input type="hidden" name="flg" value="${flg }" />
			<input type="hidden" id="userType" name="userType"
				value="<bean:write name="userType" scope="session"/>" />
			<input type="hidden" id="userName" name="userName"
				value="<bean:write name="userName" scope="session"/>" />
			<input type="hidden" id="tableName" name="tableName"
				value="${ tableName}" />
			<input type="hidden" id="realTable" name="realTable"
				value="${realTable }" />
			<input type="hidden" name="message" id="message" value="${message }" />
			<input type="hidden" name="xyV" value="" />
			<input type="hidden" name="zyV" value="" />
			<input type="hidden" name="bjV" value="" />
			<input type="hidden" id="cbVal" name="cbVal" value="" />
			<input type="hidden" name="isFdy" value="${isFdy }" />
			<logic:equal value="xy" name="userType">
				<input type="hidden" name="queryequals_xydm" value="${userDep }" />
			</logic:equal>


			<div class="toolbox">
				<!-- 按钮 -->
				<logic:equal value="yes" name="writeAble">
					<div class="buttonbox">
						<ul>
							<li>
								<a href="#"
									onclick="stzc('/xgxt/xsh.do?method=stwhCygl&doType=save')"
									class="btn_ccg"> 批量保存</a>
							</li>
						</ul>
					</div>
				</logic:equal>
				<div class="searchtab">
					<table width="100%" border="0">
						<tfoot>
							<tr>
								<td colspan="6">
									<div class="btn">
										<input name="go" value="go" type="hidden" />
										<button class="btn_cx" id="search_go"
											onclick="if(''==$('stmc').value){alert('请选择社团');return false;}allNotEmpThenGo('/xgxt/xsh.do?method=stwhCygl&doType=query')">
											查 询
										</button>
										<button class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
											重 置
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
						<tbody>
							<tr>
								<th>
									年级
								</th>
								<td>
									<html:select property="queryequals_nj"
										onchange="initZyList();initBjList()" styleId="nj">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj"
											labelProperty="nj" />
									</html:select>
								</td>
								<th>
									学号
								</th>
								<td>
									<logic:equal value="stu" name="userType" scope="session">
										<logic:present name="stcyddcl">
											<html:text property="querylike_xh" maxlength="20"
												style="width:80px"  ></html:text>
										</logic:present>
										<logic:notPresent name="stcyddcl">
											<html:text property="querylike_xh" maxlength="20"
												style="width:80px" value="${userName }" readonly="true"></html:text>
										</logic:notPresent>

									</logic:equal>
									<logic:notEqual value="stu" name="userType" scope="session">
										<html:text property="querylike_xh" maxlength="20"
											style="width:80px"></html:text>
									</logic:notEqual>

								</td>
								<th>
									姓名
								</th>
								<td>
									<html:text property="querylike_xm" maxlength="20"
										style="width:80px"></html:text>
									<logic:equal value="xsh" name="flg">
										<html:hidden property="stmc" styleId="stmc" value="学生会学校无" />
									</logic:equal>
								</td>
							</tr>
							<tr>
								<th>
									<bean:message key="lable.xsgzyxpzxy" />
								</th>
								<td>
									<html:select property="queryequals_xydm"
										onchange="initZyList();initBjList()" styleId="xy"
										style="width:180px">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select>
								</td>
								<th>
									专业
								</th>
								<td>
									<html:select property="queryequals_zydm"
										onchange="initBjList()" styleId="zy" style="width:180px">
										<html:option value=""></html:option>
										<html:options collection="zyList" property="zydm"
											labelProperty="zymc" />
									</html:select>
								</td>
								<th>
									班级
								</th>
								<td>
									<html:select property="queryequals_bjdm" styleId="bj"
										style="width:180px">
										<html:option value=""></html:option>
										<html:options collection="bjList" property="bjdm"
											labelProperty="bjmc" />
									</html:select>
								</td>
							</tr>
							<logic:notEqual value="xsh" name="flg">
								<tr>
									<th>
										社团名称
									</th>
									<td>
										<html:select property="stmc" styleId="stmc">
											<html:option value=""></html:option>
											<html:options collection="stList" property="pk"
												labelProperty="stmc" />
										</html:select>
									</td>
								</tr>
							</logic:notEqual>
						</tbody>
					</table>
				</div>
				<div class="formbox">
					<h3 class="datetitle_01">
						<span> 查询结果&nbsp;&nbsp; <logic:empty name="rs">
								<font color="red">未找到任何记录！</font>
							</logic:empty> <logic:notEmpty name="rs">
								<font color="blue">提示：双击一行可以查看详细；单击表头可以排序</font>
							</logic:notEmpty> </span>
					</h3>

					<logic:notEmpty name="rs">
						<table summary="" class="dateline" align="" width="100%">
							<thead>
								<tr align="center" style="cursor:hand">
									<td nowrap>
										<input type="checkbox" name="cb" onclick="selectAll()"
											disabled="true" />
									</td>
									<logic:iterate id="tit" name="topTr" offset="1" scope="request">
										<td id="${tit.en}" onclick="tableSort(this)" nowrap>
											${tit.cn}
										</td>
									</logic:iterate>
								</tr>
							</thead>
							<tbody>
								<logic:iterate name="rs" id="s">
									<tr onclick="rowOnClick(this)" ondblclick="showStu();"
										style="cursor:hand;">
										<td>
											<input type="checkbox" id="cbv" name="primarykey_cbv"
												value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>" />
											<input type="hidden" value="<bean:write name="v" />" />
										</td>
										<logic:iterate id="v" name="s" offset="1">
											<td>
												<bean:write name="v" />
											</td>
										</logic:iterate>
									</tr>
								</logic:iterate>
							</tbody>
						</table>
						<!--分页显示-->
						<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=xshForm"></jsp:include>
						<!--分页显示-->
					</logic:notEmpty>
				</div>
				
			<!-- 社团注册年度选择 -->
			<div id="div_stzc" style="display:none">
				<div class="open_win01">
					<table align="center" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span>社团注册</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th>
									注册年度
								</th>
								<td>
									<html:select property="nd" styleId="nd" style="width:150px" >
										<html:options collection="ndList" property="nd"
											labelProperty="nd" />
									</html:select>
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="2">
									<div class="bz">
									</div>
									<div class="btn">
										<button name="" onclick="saveData('/xgxt/xsh.do?method=stwhCygl&doType=save')">
											确 认
										</button>
										<button name="取 消" onclick="closeWindown();return false;">
											取 消
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
			</div>
		</html:form>
		<logic:present name="result">
			<logic:equal value="true" name="result">
				<script language="javascript">
	         	alert("操作成功！");
	         	document.getElementById('search_go').click();
	         	</script>
			</logic:equal>
			<logic:equal value="false" name="result">
				<script>
	       	 	 alert("操作失败！");
				document.getElementById('search_go').click();
	         	</script>
			</logic:equal>
		</logic:present>
	</body>
</html>
