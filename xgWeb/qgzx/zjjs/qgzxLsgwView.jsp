<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
		function saveSyddk(){
			var url="zgmsxy_xszz.do?method=syddkUpdate&doType=save";
			
			if($("xh") && $("xh").value==""){
				alertInfo("学号不能为空!");
				return false;
			}
			showTips("保存中,请稍候...");
			refreshForm(url);
		}
		
		function updateSyddk(){
			var url="zgmsxy_xszz.do?method=syddkOne&doType=save";
			showTips("保存中,请稍候...");
			refreshForm(url);
		}
		
		function setTextRed(){
			if($("doType") && $("doType").value=="view"){
			 jQuery('input[type=text]').attr('readonly',true);
			}
		}
		</script>
	</head>
	<body onload="setTextRed()">
		<html:form action="/qgzxLsgwView" method="post">
			<!-- 隐藏域 -->
			<input type="hidden" name="url" id="url"
				value="/xgxt/zgmsxy_xszz.do?method=syddkUpdate" />
			<input type="hidden" name="doType" id="doType" value='${doType}' />
			<input type="hidden" name="tableName" id="tableName"
				value='view_xsjbxx' />
			<input type="hidden" name="pkValue" id="pkValue"
				value='${rs.pkValue}' />
			<input type="hidden" name="message" id="message" value='${message}' />
			<!-- 隐藏域 -->

			<div class="tab_cur" id="jd">
				<p class="location">
					<em>您的当前位置:</em><a>${title }</a>
				</p>
			</div>

			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>基本信息</span>
							</th>
						</tr>
					</thead>

					<tbody>
						<tr>
							<th style="width:16%">
								学号
							</th>
							<td style="width:34%">
								${rs.xh}
							</td>
							<th style="width:16%">
								姓名
							</th>
							<td style="width:34%">
								${rs.xm}
							</td>
						</tr>
						<tr>
							<th style="width:16%">
								性别
							</th>
							<td style="width:34%">
								${rs.xb}
							</td>
							<th style="width:16%">
								年级
							</th>
							<td style="width:34%">
								${rs.nj}
							</td>
						</tr>

						<tr>

							<th style="width:16%">
								<bean:message key="lable.xb" />
							</th>
							<td style="width:34%">
								${rs.xymc}
							</td>
							<th style="width:16%">
								专业
							</th>
							<td style="width:34%">
								${rs.zymc}
							</td>
						</tr>
						<tr>
							<th style="width:16%">
								班级
							</th>
							<td style="width:34%">
								${rs.bjmc}
							</td>
							<th style="width:16%">
								岗位
							</th>
							<td style="width:34%">
								${rs.gwmc}
							</td>
						</tr>
						<logic:notEqual value="12862" name="xxdm">
						<tr>
							<th style="width:16%">
								工作开始时间
							</th>
							<td style="width:34%">
								${rs.gzkssj}
							</td>
							<th style="width:16%">
								工作结束时间
							</th>
							<td style="width:34%">
								${rs.gzjssj}
							</td>
						</tr>
						</logic:notEqual>
						<tr>
							<th style="width:16%">
								酬金
							</th>
							<td style="width:34%">
								${rs.cj}元
							</td>
							<logic:equal value="12862" name="xxdm">
							<th style="width:16%">
								工作总时间
							</th>
							<td style="width:34%">
								${rs.gzzsj }
							</td>
							</logic:equal>
							<logic:notEqual value="12862" name="xxdm">
							<th style="width:16%">
								
							</th>
							<td style="width:34%">
								
							</td>
							</logic:notEqual>
						</tr>
						<tr>
							<th style="width:16%">
								工作地点
							</th>
							<td style="width:84%" colspan="3">
								<html:textarea name='rs' property='gzdz' styleId="gzdz"
									style="word-break:break-all;width:99%" rows='4' disabled="true" />
							</td>
						</tr>
						<tr>
							<th style="width:16%">
								工作内容
							</th>
							<td style="width:34%" colspan="3">
								<html:textarea name='rs' property='gznr' styleId="gznr"
									style="word-break:break-all;width:99%" rows='5' disabled="true" />
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="4">
								
								<div class="btn">

									<button type="button" name="关闭" onclick="Close();return false;" id="buttonClose">
										关 闭
									</button>

								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>


			<%@ include file="/comm/other/tsxx.jsp"%>

		</html:form>
	</body>
</html>
