<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<style type="text/css">
			table{
				border-collapse:collapse;
			}
			
			table th{
				width:20%;
			}
			
			table td{
				width:30%;
			}
			
			table span{
				color:red;
			}
		</style>
	</head>
	<body>
		<html:form action="/rcsw_gzdx_fsbtgl" method="post">
			<input type="hidden" name="pkValue" value="${param.pkValue }"/>
			<input type="hidden" name="doType" value="${doType}"/>
				<div class="tab">
					<table width="100%" class="formlist">
				<logic:empty name="rs">
					<thead>
						<tr>
							<th colspan="4"><span>当前学生没有发放记录！</span></th>
						</tr>
					</thead>
				</logic:empty>
				<logic:notEmpty name="rs">
						<thead>
							<tr>
								<th colspan="4"><span>补贴发放</span></th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th>
									年度
								</th>
								<td>
									${rs.nd }
								</td>
								<th>
									月份
								</th>
								<td>
									${rs.yf }月份
								</td>
							</tr>
							<tr>
								<th>
									补贴项目
								</th>
								<td>
									${rs.mc }
								</td>
								<th>
									补贴金额
								</th>
								<td>
									${rs.btje }
								</td>
							</tr>
							<tr>
								<th>
									经手人
								</th>
								<td>
									${rs.jsr }
								</td>
								<th>
									发放时间
								</th>
								<td>
									${rs.ffsj }
								</td>
							</tr>
						 </tbody>
					     <thead>
							<tr>
								<th colspan="4"><span>发放学生</span></th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th>
									学号
								</th>
								<td>
									${rs.xh }
								</td>
								<th>
									姓名
								</th>
								<td>
									${rs.xm }
								</td>
							</tr>
							<tr>
								<th>
									院系
								</th>
								<td>
									${rs.xymc }
								</td>
								<th>
									专业
								</th>
								<td>
									${rs.zymc }
								</td>
							</tr>
							<tr>
								<th>
									班级
								</th>
								<td>
									${rs.bjmc }
								</td>
								<th></th>
								<td></td>
							</tr>
						</tbody>
					</logic:notEmpty>
						<tfoot>
					      <tr>
					        <td colspan="4">
					          <div class="btn">
								 <button type="button" id="buttonClose"
									onclick="window.close();return false;">
									关闭
								</button>
					          </div></td>
					      </tr>
					   </tfoot>
				</table> 
			</div>
		</html:form>
	</body>
</html>
