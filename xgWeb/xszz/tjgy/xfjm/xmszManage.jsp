<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="js/commit.js"></script>
		<script type="text/javascript">
			function doDel(checkboxName,url){
				var checkbox = jQuery('input[name='+checkboxName+']:checked');
				
				if(checkbox.length == 0){
					alertInfo('请选择您要删除的数据！');
					return false;
				} else {
					for (var i = 0 ; i < checkbox.length ; i++){
					 	if (jQuery(checkbox[i]).attr('alt')=="disabled"){
							alertInfo("您选中的记录中有学生申请过的项目不能删除，请确认！");
							return false;
						} 
					}
				}
				batchData(checkboxName,url,'del');	
			}
		</script>
	</head>

	<body >
		<html:form action="/tjgy_xfjm" method="post">
			
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>${title }</a>
				</p>
			</div>
			
			<div class="toolbox">
				<logic:equal name="writeAble" value="yes">
					<!-- 按钮 -->
					<div class="buttonbox">
						<ul>
							<li>
								<a href="#" class="btn_zj"
									onclick="showTopWin('tjgy_xfjm.do?method=xmsz',650,450);return false;"
									id="btn_qx">增加</a>
							</li>
							
							<li>
								<a href="#" class="btn_xg"
									onclick="showViewWindow('primarykey_cbv','tjgy_xfjm.do?method=xmsz','650','450',false,'请选择您要修改的记录！');return false;"
									id="btn_qx">修改</a>
							</li>
							
							<li>
								<a href="#" class="btn_sc"
									onclick="doDel('primarykey_cbv','tjgy_xfjm.do?method=delXmsz');return false;"
									id="btn_qx">删除</a>
							</li>
						</ul>
					</div>
				</logic:equal>
				<!-- 过滤条件 -->
				<div class="searchtab">
					<table width="100%" border="0" id="searchTab">
						<tbody>
							<tr>
								<th>项目名称</th>
								<td>
									<html:text property="xmmc"></html:text>
								</td>
								<td colspan="4">
									<div class="btn">
										<button type="button" class="btn_cx" id="search_go"
											onclick="allNotEmpThenGo('tjgy_xfjm.do?method=xmszManage')">
											查 询
										</button>
										<button type="button" class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
											重 置
										</button>
									</div>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div> 

			<div class="formbox">
				<h3 class="datetitle_01">
					<span> 查询结果&nbsp;&nbsp; 
						<logic:notEmpty name="rs">
							<font color="blue">提示：单击表头可以排序</font>
						</logic:notEmpty> 
					</span>
				</h3>

				<div class="con_overlfow">
					<table class="dateline tablenowrap" width="100%">
						<thead>
							<tr>
								<td width="17px">
									<input type="checkbox" disabled="disabled" alt=""/>
								</td>
								<td onclick="tableSort(this)">
									减免项目
								</td>
								<td onclick="tableSort(this)">
									本学年已申请人数
								</td>
								<td onclick="tableSort(this)">
									总申请人数
								</td>
								<td onclick="tableSort(this)">
									设置条件数
								</td>
								<td onclick="tableSort(this)">
									是否启用
								</td>
								<td onclick="tableSort(this)">
									申请开始时间
								</td>
								<td onclick="tableSort(this)">
									申请结束时间
								</td>
							</tr>
						</thead>
						<tbody>
							<logic:notEmpty name="rs">
								<logic:iterate name="rs" id="x">
									<tr onclick="rowOnClick(this)">
										<td>
											<input type="checkbox" name="primarykey_cbv" value="${x.xmid }"
												   alt="<logic:notEqual value="0" name="x" property="zsqs">disabled</logic:notEqual>"
											/>
										</td>
										<td>${x.xmmc }</td>
										<td>${x.bxnsqs }</td>
										<td>${x.zsqs }</td>
										<td>${x.tjs }</td>
										<td>${x.sfqy }</td>
										<td>${x.sqkssj }</td>
										<td>${x.sqjssj }</td>
									</tr>
								</logic:iterate>

								<%
											for (int i = 0; i < (Integer) request
											.getAttribute("maxNum")
											- ((List) request.getAttribute("rs")).size(); i++) {
								%>
								<tr>
									<td>
										<input type="checkbox" disabled="disabled" />
									</td>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
								</tr>
								<%
								}
								%>

							</logic:notEmpty>
							<logic:empty name="rs">
								<%
											for (int i = 0; i < (Integer) request
											.getAttribute("maxNum"); i++) {
								%>
								<tr>
									<td>
										<input type="checkbox" disabled="disabled" />
									</td>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
								</tr>
								<%
								}
								%>
							</logic:empty>
						</tbody>
					</table>
				</div>

				<jsp:include flush="true"
					page="/sjcz/turnpage.jsp?form=xfjmForm"></jsp:include>
				
			</div>
			
			<logic:present name="message">
				<script defer="defer">
					alertInfo('${message}');
				</script>
			</logic:present>
		</html:form>
	</body>
</html>
