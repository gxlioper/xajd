<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="<%=stylePath%>js/lhgdialog/lhgdialog.min.js?self=true"></script>		
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script language="javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		
		<script	type="text/javascript">

		</script>
		
	</head>
	<body>
		
		<html:form action="/rcsw_xqybgl_bjxqybgl_bjxqybjggl" method="post" styleId="bjxqybjgForm">
			<div style='tab;width:100%;height:415px;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>班级月报信息</span>
							</th>
						</tr>
					</thead>
					
					<tbody>
					     <tr>
							<th align="right" width="20%">
									学年
							</th>
							<td align="left" width="30%" >
								${infoList.xn}
							</td>
							<th align="right" width="20%">
									学期
							</th>							
							<td align="left" width="30%">
								${infoList.xqmc}
							</td>
					    </tr>
					    
					     <tr>
					    	<th align="right" width="20%">
									填写人
							</th>
							<td align="left" width="30%">
								${infoList.xm}						
							</td>
							<th align="right" width="20%">
									月份
							</th>
							<td >									
								${infoList.yf}				    	
							</td>
					    </tr>
					     <tr>
							<th align="right" width="20%">
									学院
							</th>
							<td width="30%" >
								${infoList.xymc}	
							</td>
							
							<th align="right" width="20%">
									班级
							</th>
							<td width="80%" colspan="3">
								${infoList.bjmc}							
							</td>
					    </tr>
					    <tr>
							<th align="right" width="20%">
								本月工作开展情况
							</th>
							<td colspan="3">
								${infoList.bygzkzqk}
							</td>
			      		</tr>
			      		<tr>
							<th align="right" width="20%">
								学生关注热点 				
							</th>
							<td colspan="3">
								${infoList.xsgzrd}				
							</td>
			      		</tr>
			      		<tr>
							<th align="right" width="20%">
								学生思想动态 
								
							</th>
							<td colspan="3">
								${infoList.xssxdt}								
							</td>
			      		</tr>
			      		<tr>
							<th align="right" width="20%">
								学生诉求及工作建议 								
							</th>
							<td colspan="3">
								${infoList.xstsjgzjy}
							</td>
			      		</tr>					   
					</tbody>
					
				</table>
			</div>
				<table width="100%" border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button type="button" type="button" onclick="iFClose();">
										关 闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
		</html:form>
	</body>
</html>

