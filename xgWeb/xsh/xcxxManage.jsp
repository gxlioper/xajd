<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
		<script type="text/javascript" src="js/pjpy/pjpyFunction.js"></script>
		<script type="text/javascript" src="js/xtwh/bdsz.js"></script>
		<script type="text/javascript">
		<!--
		function showData(url,doType,w,h){
			if(doType == "update" || doType == "modi"){
				if(curr_row == null){
					alert('请选择要修改的数据！');
					return false;
				}
			}else if(doType == "sh"){
				if(curr_row == null){
					alert('请选择审核的数据！');
					return false;
				}
			}
			var pk = curr_row.getElementsByTagName('input')[0].value;
			url+="&doType="+doType;
			url+="&pk="+pk;
			showOpenWindow(url,w,h);
		}
		//-->
		</script>
	</head>
	<body onload="xyDisabled('xy');">
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>${title }</a>
			</p>
		</div>
	
		<html:form action="/xsh" method="post">
			<input type="hidden" id="userType" name="userType" value="${userType }>" />
			<input type="hidden" id="tableName" name="tableName" value="${ tableName}" />
			<input type="hidden" id="realTable" name="realTable"	value="${realTable }" />
			<input type="hidden" name="xyV" value=""/>
			<input type="hidden" name="zyV" value=""/>
			<input type="hidden" name="bjV" value=""/>
			<input type="hidden" id="cbVal" name="cbVal" value="" />
			
			<input type="hidden" id="likeCol" name="likeCol" value="xczt!!xcdd!!xckh" />
			
			<div class="toolbox">
				<!-- 按钮 -->
				<logic:equal value="yes" name="writeAble">
					<div class="buttonbox">
						<ul>
							<li>
								<a href="#"
									onclick="showTopWin('/xgxt/xsh.do?method=xcxxUpdate','600','500')"
									class="btn_zj"> 增加</a>
							</li>
							<li>
								<a href="#"
									onclick="showData('/xgxt/xsh.do?method=xcxxUpdate','update','600','500');"
									class="btn_xg">修改</a>
							</li>
							<li>
								<a href="#"
									onclick="sumitInfo('/xgxt/xsh.do?method=xcxxManage','del');"
									class="btn_sc"> 删除</a>
							</li>
							<li>
								<a href="#" onclick="impAndChkDataForZdy('ty_bdsz')"
									class="btn_dr">导入</a>
							</li>
							<li>
								<a href="#" onclick="expZdyData()"
									class="btn_dc"> 导出 </a>
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
											onclick="allNotEmpThenGo('/xgxt/xsh.do?method=xcxxManage&doType=query')">
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
									宣传主题
								</th>
								<td>
									<html:text property="xczt" maxlength="25"></html:text>
								</td>
								<th>
									宣传地点
								</th>
								<td>
									<html:text property="xcdd" maxlength="50"></html:text>
								</td>
								<th>
									宣传口号
								</th>
								<td>
									<html:text property="xckh" maxlength="20"></html:text>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
				<div class="formbox">
					<h3 class="datetitle_01">
						<span> 查询结果&nbsp;&nbsp; 
							<logic:empty name="rs">
								<font color="red">未找到任何记录！</font>
							</logic:empty> 
							<logic:notEmpty name="rs">
								<font color="blue">提示：双击一行可以查看详细；单击表头可以排序</font>
							</logic:notEmpty>
						</span>
					</h3>

					<logic:notEmpty name="rs">
						<table summary="" class="dateline" align="" width="100%">
							<thead>
								<tr align="center" style="cursor:hand">
									    <td nowrap>
											<input type="checkbox" id="cb" name="cb" disabled="true"/>
										</td>
										<logic:iterate id="tit" name="topTr" offset="1" scope="request">
											<td id="${tit.en}"
												onclick="tableSort(this)" nowrap>
												${tit.cn}
											</td>
										</logic:iterate>
									</tr>
							</thead>
							<tbody>
								<logic:iterate name="rs" id="s">
									<tr onclick="rowOnClick(this)"
										ondblclick="showData('/xgxt/xsh.do?method=xcxxUpdate','view','600','500');"
										style="cursor:hand;">
										<td>
											<input type="checkbox" id="pk" name="checkVal" 	value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>" />
											<logic:iterate id="v" name="s" offset="0" length="1">
												<input type="hidden" value="<bean:write name="v"/>" />
											</logic:iterate>
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
		</html:form>
		 <logic:present name="result">
			<logic:equal value="true" name="result">
				<script language="javascript">
	         	alert("操作成功！");
	         	document.getElementById('search_go').click();
	         	</script>
			</logic:equal>
			<logic:equal value="false" name="result">
				<script language="javascript">
	         	alert("操作成功！");
	         	</script>
			</logic:equal>
		</logic:present>
	</body>
