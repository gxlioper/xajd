<%@ page language="java" pageEncoding="GBK"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	</head>
	<body>
		<html:form action="/sztz" method="post">
			<div class="tab">
				<table class="formlist">
					<tfoot>
						<tr>
							<td>
								<div class="btn">
									<button name="关 闭" onclick="window.close();return false;">
										关 闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<thead>
						<tr>
							<td>
								<span>
									《${rs.title }》审核流程
								</span>
							</td>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>
								审核流程:
								<logic:present name="spgwList">
									开始-->>
									<logic:iterate name="spgwList" id="s" indexId="i">
										${s.mc }
											-->>
									</logic:iterate>
									结束
								</logic:present>
							</td>
						</tr>
						<tr>
							<td>
								<div class="con_overlfow">
									<table class="dateline" width="100%">
										<thead>
											<tr>
												<td>序号</td>
												<td>流程名称</td>
												<td>处理人</td>
												<td>处理结果</td>
												<td>处理时间</td>
												<td>处理意见</td>
											</tr>
										</thead>
										<tbody>
											<logic:iterate name="shxx" id="r" indexId="i">
												<tr>
													<td>${i+1 }</td>
													<td>${r.mc }</td>
													<td>${r.xm }</td>
													<td>${r.shzt }</td>
													<td>${r.shsj }</td>
													<td style="word-break:break-all;">${r.shyj }</td>
												</tr>
											</logic:iterate>
										</tbody>
									</table>
								</div>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</html:form>
	</body>
</html>
