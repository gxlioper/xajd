<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- 头文件 -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>
</head>

<body>
	<html:form action="/xszcgl" method="post">

		<div class="tab">
		<table class="formlist" width="100%">
			<thead>
		    	<tr>
		        	<th><span>学生详细注册情况</span></th>
		        </tr>
		    </thead>
			<tbody>
				<tr>
					<td>
						<table width="100%" class="dateline">
							<thead>
								<logic:iterate id="tit" name="topTr" scope="request">
									<td id="${tit.en}"
										onclick="tableSort(this)">
										${tit.cn}
									</td>
								</logic:iterate>
							</thead>
							<tbody>
								<logic:iterate name="rs" id="s">
									<tr onclick="rowOnClick(this)"
										style="cursor:hand;">
										<logic:iterate id="v" name="s" offset="2">
											<td>
												<bean:write name="v" />
											</td>
										</logic:iterate>
									</tr>
								</logic:iterate>
							</tbody>
						</table>
					</td>
				</tr>
			</tbody>
			<tfoot>
		      <tr>
		        <td>
		          <div class="btn">
					<button type="button" class="button2" id="buttonSave" onclick="Close();return false;">
						关&nbsp;&nbsp;闭
					</button>
		          </div>
		        </td>
		      </tr>
		    </tfoot>
		</table>
		</div>
	</html:form>
</body>
</html>
