<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="js/commit.js"></script>
		<script>
			function showAddWindow(){
				var li = jQuery('li.Current')[0];
				var id = jQuery(li).attr('id');

				if (id != null && '' != id){
					showOpenWindow('tsbj.do?method=tsbjxssz&tsbjdm=${tsbjForm.tsbjdm }&tsbjmc=${tsbjForm.tsbjmc }','1100','600');
				} else {
					alert('请选择您要添加学生的特色班级!');
				}
			}
			
			function searchRs(){
				allNotEmpThenGo('tsbj.do?method=tsbjxsgl&doType=query&tsbjdm=${tsbjForm.tsbjdm }&tsbjmc=${tsbjForm.tsbjmc }');
			}
			
			function checkTsbj(text){
				var liArr = jQuery('li',jQuery('#tsbjList'));
				
				for (var i = 0 ; i < liArr.length ; i++){
					
					var temp_text = jQuery(jQuery('a',jQuery(liArr[i]))[0]).attr('innerHtml');
				
					if (temp_text.indexOf(text)>=0){
						liArr[i].style.display="";
					} else {
						liArr[i].style.display="none";
					}
				}
				
			}
			
			
		</script>
	</head>

	<body onload="window.parent.parent.loadFrameWindow()">
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/tsbj">
			<input type="hidden" id="tableName" name="tableName"
				value="xg_view_xsxx_tsbjxs" />
		<input type="hidden" id="realTable" name="realTable"
				value="xg_xsxx_tsbjxsb" />
			<input type="hidden" id="isFdy" name="isFdy" value="${fdyQx}" />
			<input type="hidden" id="userName" name="userName"
				value="${userName}" />
			<input type="hidden" id="userType" name="userType"
				value="${userType}" />
			<input type="hidden" id="userDep" name="userDep" value="${userDep}" />
			<input type="hidden" id="path" name="searchModel.path" value="tsbjxsgl.do" />
			
		
		<div class="toolbox">
			<!-- 按钮 -->
			<logic:equal value="yes" name="writeAble">
				<div class="buttonbox">
					<ul>
						<li>
							<a href="#" id="btn_zj"
								onclick="showAddWindow()"
								class="btn_zj"> 添加学生 </a>
						</li>
						<li>
							<a href="#" id="btn_sc" 
							   onclick="batchData('primarykey_cbv','tsbj.do?method=delTsbjXs','del')"
							  class="btn_sc"> 删除 </a>
						</li>
						<li>
							<a href="#" onclick="impAndChkData()" class="btn_dr" id="btn_dr"> 导入 </a>
						</li>
						<li>
							<a href="#"
								id="btn_dc"
								onclick="configureExportData();return false;"
								class="btn_dc"> 导出 </a>
						</li>
						<li>
							<a href="#" class="btn_qx" onclick="choiceFields();return false;" id="btn_qx">导出字段确认</a>
						</li>
					</ul>
				</div>
			</logic:equal>

			<div class="toolbox">
				<%@ include file="/comm/search/superSearchArea.jsp"%>
			</div>

			<div class="leftframe04">
				<div class="menulist">
					<!--层start-->
					<div class="menutitle">
						<h3>
							<span class="title">特色班级列表</span>
						</h3>
						<div class="CNLTreeMenu1" id="CNLTreeMenu1"
							style="height:325px; overflow:auto;">
							<p class="choose_select">
								<label>
									搜索
								</label>
								<input type="text" class="text_nor" style="width:80px" onkeyup="checkTsbj(this.value)"/>
							</p>
							<ul id="tsbjList">
								<logic:equal value="" property="tsbjdm" name="tsbjForm">
									<li class="Current" id="" >
								</logic:equal>
								<logic:notEqual value="" property="tsbjdm" name="tsbjForm">
									<li id="">
								</logic:notEqual>
									<a href="javascript:allNotEmpThenGo('tsbj.do?method=tsbjxsgl&doType=query')" class="Child">
										全部
									</a>
								</li>
								<logic:iterate id="t" name="tsbjList">
									<logic:equal value="${t.tsbjdm }" property="tsbjdm" name="tsbjForm">
										<li class="Current" id="${t.tsbjdm }">
									</logic:equal>
									<logic:notEqual value="${t.tsbjdm }" property="tsbjdm" name="tsbjForm">
										<li id="${t.tsbjdm }">
									</logic:notEqual>	
										<a href="javascript:allNotEmpThenGo('tsbj.do?method=tsbjxsgl&doType=query&tsbjdm=${t.tsbjdm }&tsbjmc=${t.tsbjmc }')" 
										   style="float:left;width:110px;text-overflow:ellipsis;white-space:nowrap;overflow:hidden;"
										   class="Child" >
											${t.tsbjmc}
										</a>
									</li>
								</logic:iterate>
							</ul>
						</div>
					</div>
				</div>
			</div>

			<div class="rightframe04" name="aaa">
				<div class="formbox">
				<!--当左边栏目导航隐藏时调用rightframe04_hidden这个class名-->
					<h3 class="datetitle_01">
						<span> 查询结果&nbsp;&nbsp; 
							<logic:notEmpty name="rs">
								<font color="blue">提示：单击表头可以排序</font>
							</logic:notEmpty> 
						</span>
					</h3>

				<div class="con_overlfow" >
					<table summary="" class="dateline tablenowrap"  width="100%">
						<thead>
							<tr>
								<td width="17px">
									<input type="checkbox" disabled="disabled"/>
								</td>
								<logic:iterate id="tit" name="topTr" offset="0" scope="request">
									<td onclick="tableSort(this)">
										${tit}
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<tbody>
							<logic:notEmpty name="rs">
								<logic:iterate name="rs" id="s">
									<tr>
										<td>
											<input type="checkbox" id="cbv" name="primarykey_cbv"
												value="<logic:iterate id="v" name="s" offset="0" length="1">${v }</logic:iterate>" />
										</td>
										<logic:iterate id="v" name="s" offset="1">
											<td>
												${v }
											</td>
										</logic:iterate>
									</tr>
								</logic:iterate>

								<%
										for (int i = 0; i < (Integer) request.getAttribute("maxNum")
										- ((List) request.getAttribute("rs")).size(); i++) {
								%>
								<tr>
									<td>
										<input type="checkbox" disabled="disabled" />
									</td>
									<logic:iterate id="t" name="topTr" offset="0" scope="request">
										<td>
											&nbsp;
										</td>
									</logic:iterate>
								</tr>
								<%
								}
								%>

							</logic:notEmpty>
							<logic:empty name="rs">
								<%
								for (int i = 0; i < (Integer) request.getAttribute("maxNum"); i++) {
								%>
								<tr>
									<td>
										<input type="checkbox" disabled="disabled" />
									</td>
									<logic:iterate id="t" name="topTr" offset="0" scope="request">
										<td>
											&nbsp;
										</td>
									</logic:iterate>
								</tr>
								<%
								}
								%>
							</logic:empty>
						</tbody>
					</table>
				</div>
			</div>
			</div>
				<!--分页显示-->
				<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=tsbjForm"></jsp:include>
				<!--分页显示-->
		</html:form>
	</body>
</html>
