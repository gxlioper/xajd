<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script type="text/javascript" src="js/check.js"></script>
	<script language="javascript" src="js/function.js"></script>
	<script type="text/javascript">
		
		function save(){
			refreshForm("/xgxt/newsPigUpdate.do?method=newsPigUpdate&doType=save");
		}
	</script>
	</head>
	<body>
		<html:form action="/newsPigUpdate" method="post">
			<input type="hidden" name ="picid" id ="picid" value="${rs.pkValue}"/>
			<div class="tab_cur" id="jd">
				<p class="location">
					<em>您的当前位置:</em><a>${title}</a>
				</p>
			</div>
					
						<div class="tab">
							<table width="100%"  border="0" class="formlist">
							 <thead>
			    				<tr>
			        				<th colspan="6"><span>项目设置</span></th>
			        			</tr>
			   				 </thead>
			   				
			   				 <tbody>
							<tr>
								<th style="width:16%">
									新闻ID
								</th>
								<td>
									${rs.newsid }
								</td>
							</tr>
							<tr>
								<th align="right">
									新闻标题
								</th>
								<td colspan="3">
									${rs.newstitle }
								</td>
							</tr>
							<tr>
								
								<th align="right">
									发布时间
								</th>
								<td colspan="3">
									${rs.pubtime }
								</td>
							</tr>
							<tr>
								<th align="right">
									显示顺序
								</th>
								<td colspan="3">
									<input type="text" name="xssx" id="xssx" value="${rs.xssx}"/>
								</td>
							</tr>
							</tbody>
							<tfoot>
								<tr>
									 <td colspan="4"><div class="bz">"<span class="red">*</span>"为必填项</div>
										<div class="btn">
											<button onclick="Close();return false;">关 闭</button>
										</div>
									</td>
								</tr>
							</tfoot>
						</table>
						</div>
						<logic:present name="result">
							<logic:equal name="result" value="true"><script>
								alert('操作成功!');
								if (window.dialogArguments) {
									window.close();
									window.dialogArguments.document.getElementById('search_go').click();
								}
								</script>
							</logic:equal>
							<logic:equal name="result" value="false">
								<script>
								alert('操作失败!');
								if (window.dialogArguments) {
									window.close();
									window.dialogArguments.document.getElementById('search_go').click();
								}
								</script>
							</logic:equal>
						</logic:present>
		</html:form>
	</body>
</html>
