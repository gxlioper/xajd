<%@ page language="java"  pageEncoding="GBK"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/comm/commFunction.js"></script>
	</head>

	<body >
		<html:form action="/tjgy_xfjm" method="post">
			
			<button type="button" id="search_go" style="display:none" onclick="refreshForm('tjgy_xfjm.do?method=xmsqManage');"></button>
			
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>${title }</a>
				</p>
				<p class="help">
					<a href="#" onclick="return false;" onmousedown ="showHelpDiv();">使用帮助</a>
				</p>
			</div>			
			<!-- 标题 end-->
			<!-- 提示信息 START-->
			<div class="prompt" id="div_help" style="display: none">
				<h3>
					<span>提示：</span>
				</h3>
				<p>
					本学年您已经有学费减免项目经<bean:message key="lable.xb" />审核通过,不可再申请其它项目。请到结果查询页面查看详细信息！
				</p>
				<a class="close" title="隐藏"  onclick="this.parentNode.style.display='none';"></a>
			</div>
			<!-- 提示信息 end-->

			<div class="formbox">

				<div class="con_overlfow">
					<table class="dateline tablenowrap" width="100%">
						<thead>
							<tr>
								<td onclick="tableSort(this)">
									减免项目
								</td>
								<td onclick="tableSort(this)">
									申请开始时间
								</td>
								<td onclick="tableSort(this)">
									申请结束时间
								</td>
								<td>
									操作
								</td>
							</tr>
						</thead>
						<tbody>
							<logic:notEmpty name="xmsqList">
								<logic:iterate name="xmsqList" id="x">
									<tr onclick="rowOnClick(this)">
										<td>${x.xmmc }</td>
										<td>${x.sqkssj }</td>
										<td>${x.sqjssj }</td>
										<td>
											<logic:notPresent name="x" property="xysh">
												<logic:notEqual value="zsqsj" name="x" property="sfzsqsj">
													<font color="red">不在申请时间内</font>
												</logic:notEqual>
												<logic:equal value="zsqsj" name="x" property="sfzsqsj">
													<button type="button" class="btn_01" onclick="showTopWin('tjgy_xfjm.do?method=xmsq&xmid=${x.xmid }&xh=${x.xh }',600,500)">申请</button>
												</logic:equal>
											</logic:notPresent>
											<logic:present name="x" property="xysh">
												<logic:equal value="未审核" name="x" property="xysh">
													<logic:notEqual value="zsqsj" name="x" property="sfzsqsj">
														<font color="red">不在申请时间内</font>
													</logic:notEqual>
													<logic:equal value="zsqsj" name="x" property="sfzsqsj">
														<button type="button" class="btn_01" onclick="showTopWin('tjgy_xfjm.do?method=xmsq&xmid=${x.xmid }&xh=${x.xh }',600,500)">修改</button>
													</logic:equal>
												</logic:equal>
												<logic:notEqual value="未审核" name="x" property="xysh">
													<font color="blue"><bean:message key="lable.xb" />审核:${x.xysh }</font>
													<logic:equal value="通过" name="x" property="xysh">
														<script defer>
															jQuery('button').attr('disabled',true);
															jQuery('button').attr('class','disabled');
															jQuery('button').text('不可操作');
															jQuery('#prompt').css('display','');
														</script>
													</logic:equal>
												</logic:notEqual>
											</logic:present>
										</td>
									</tr>
								</logic:iterate>
							</logic:notEmpty>
						</tbody>
					</table>
				</div>
			</div>
			
			<logic:present name="message">
				<script defer="defer">
					alertInfo('${message}');
				</script>
			</logic:present>
		</html:form>
	</body>
</html>
