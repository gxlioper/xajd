<%@ page language="java" contentType="text/html; charset=GBK"%>
<jsp:directive.page import="java.util.List"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="pjpy/xibeierminyuan/js/xbemyJs.js"></script>
		<script type="text/javascript" src="pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
		<script type="text/javascript" src="pjpy/czxx/czxx.js"></script>
		<script type="text/javascript">
		
			function sendData() {
				var flg = $('flg').value;
				var dm = curr_row.getElementsByTagName('input')[0].value;
				GetListData.getYrdw(dm,function(data){
					var obj = window.dialogArguments.document;
				
					if (obj.getElementById('dwxz') && null != data.dwxzdm){
						obj.getElementById('dwxz').value = data.dwxzdm ;
					}
				
					if ("ydw" == flg){
						if (obj.getElementById('ydw') && null != data.dm) {
							obj.getElementById('ydw').value = data.dm ;
						}
					
						if (obj.getElementById('ydwmc') && null != data.mc) {
							obj.getElementById('ydwmc').value = data.mc ;
						}
					} else {
						if (obj.getElementById('dwdm') && null != data.dm) {
							obj.getElementById('dwdm').value = data.dm ;
						}
					
						if (obj.getElementById('dwmc') && null != data.mc) {
							obj.getElementById('dwmc').value = data.mc ;
						}
					}
				
					if (obj.getElementById('yrdwszddm') && null != data.dwszd) {
						obj.getElementById('yrdwszddm').value = data.dwszd ;
					}
				
					if (obj.getElementById('yrdwszd') && null != data.dwszdmc) {
						obj.getElementById('yrdwszd').value = data.dwszdmc ;
					}
					
					if (obj.getElementById('dwlxr') && null != data.dwlxr){
						obj.getElementById('dwlxr').value = data.dwlxr ;
					}
					
					if (obj.getElementById('dwlxdh') && null != data.dwdh) {
						obj.getElementById('dwlxdh').value = data.dwdh ;
					}
					
					if (obj.getElementById('hyfl') && null != data.hyfldm) {
						obj.getElementById('hyfl').value = data.hyfldm ;
					}
					
					if (obj.getElementById('dwyb') && null != data.dwyb){
						obj.getElementById('dwyb').value = data.dwyb;
					}
					
					//浙江省-父页的就就业标志也要变动
					if (obj.setJybz){
						obj.setJybz(data.dwxzdm);
					}
					
					window.close();
				});
				
				
			}
		</script>
	</head>
	<body onload="xyDisabled('xy');">

		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>用人单位信息</a>
			</p>
		</div>


		<html:form action="/jygl" method="post">
			<input type="hidden" id="userName" name="userName"
				value="${userName }" />
			<input type="hidden" id="userType" name="userType"
				value="${userType }" />
			<input type="hidden" id="tableName" name="tableName"
				value="${ tableName}" />
			<input type="hidden" id="realTable" name="realTable"
				value="${realTable }" />
			<input type="hidden" name="message" id="message" value="${message }" />
			<input type="hidden" name="xyV" value="" />
			<input type="hidden" name="zyV" value="" />
			<input type="hidden" name="bjV" value="" />
			<input type="hidden" id="cbVal" name="cbVal" value="" />
			<input type="hidden" value="${flg }" id="flg" name="flg" />

			<div class="toolbox">
				<div class="buttonbox">
					<ul>
						<li>
							<a href="#"
								onclick="showTopWin('/xgxt/jygl.do?method=companyUpdate',500,400)"
								class="btn_zj"> 增加</a>
						</li>
					</ul>
				</div>
				<div class="searchtab">
					<table width="98%" border="0">
						<tfoot>
							<tr>
								<td colspan="4">
									<div class="btn">
										<button class="btn_cx" id="search_go"
											onclick="allNotEmpThenGo('/xgxt/jygl.do?method=compayData&doType=query')">
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
									单位代码
								</th>
								<td>
									<html:text property="querylike_dm"></html:text>
								</td>
								<th>
									单位名称
								</th>
								<td>
									<html:text property="querylike_mc"></html:text>
								</td>
							</tr>
							<tr>
								<th>
									单位性质
								</th>
								<td>
									<html:select property="queryequals_dwxzdm" style="width:180px">
										<html:options collection="dwxzList" property="dm"
											labelProperty="mc" />
									</html:select>
								</td>
								<th>
									行业分类
								</th>
								<td>
									<html:select property="queryequals_hyfldm" style="width:180px">
										<html:options collection="hyList" property="dm"
											labelProperty="mc" />
									</html:select>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
				<div class="formbox">
					<h3 class="datetitle_01">
						<span> 查询结果&nbsp;&nbsp; <logic:notEmpty name="rs">
								<font color="blue">提示：双击一行可以发送单位信息到父页面，单击表头可以排序！</font>
							</logic:notEmpty> </span>
					</h3>


					<table summary="" class="dateline" align="" width="100%">
						<thead>
							<tr align="center" style="cursor:hand">
								<logic:iterate id="tit" name="topTr" offset="0" scope="request">
									<td onclick="tableSort(this)" nowrap>
										${tit}
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<tbody>
							<logic:notEmpty name="rs">
								<logic:iterate name="rs" id="s">
									<tr onclick="rowOnClick(this)" ondblclick="sendData();"
										style="cursor:hand;">
										<logic:iterate id="v" name="s" offset="0" length="1">
											<td align=center>
												<bean:write name="v" />
												<input type="hidden" value="<bean:write name="v" />" />
											</td>
										</logic:iterate>
										<logic:iterate id="v" name="s" offset="1">
											<td align=center>
												<bean:write name="v" />
											</td>
										</logic:iterate>
									</tr>
								</logic:iterate>
									<%
										for (int i = 0 ; i < (Integer)request.getAttribute("maxNum") - ((List)request.getAttribute("rs")).size() ; i++){
									
									 %>
										<tr>
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
									for (int i = 0 ; i < (Integer)request.getAttribute("maxNum") ; i++){
								
								 %>
									<tr>
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
					<!--分页显示-->
					<jsp:include flush="true"
						page="/sjcz/turnpage.jsp?form=jyglActionForm"></jsp:include>
					<script type="text/javascript">
							$('choose').className="hide";
						</script>
					<!--分页显示-->
				</div>
			</div>
		</html:form>
		<logic:present name="message">
			<script defer="defer">
		 		alert("${message}");
		 	</script>
		</logic:present>
	</body>
</html>
