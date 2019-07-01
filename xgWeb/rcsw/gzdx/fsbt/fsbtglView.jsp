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
						<thead>
							<tr>
								<th colspan="4"><span>��������</span></th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th>
									���
								</th>
								<td>
									${rs.nd }
								</td>
								<th>
									�·�
								</th>
								<td>
									${rs.yf }�·�
								</td>
							</tr>
							<tr>
								<th>
									������Ŀ
								</th>
								<td>
									${rs.mc }
								</td>
								<th>
									�������
								</th>
								<td>
									${rs.btje }
								</td>
							</tr>
							<tr>
								<th>
									������
								</th>
								<td>
									${rs.jsr }
								</td>
								<th>
									����ʱ��
								</th>
								<td>
									${rs.ffsj }
								</td>
							</tr>
						 </tbody>
						 
					     <thead>
							<tr>
								<th colspan="4"><span>����ѧ��</span></th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th>
									ѧ��
								</th>
								<td>
									${rs.xh }
								</td>
								<th>
									����
								</th>
								<td>
									${rs.xm }
								</td>
							</tr>
							<tr>
								<th>
									Ժϵ
								</th>
								<td>
									${rs.xymc }
								</td>
								<th>
									רҵ
								</th>
								<td>
									${rs.zymc }
								</td>
							</tr>
							<tr>
								<th>
									�༶
								</th>
								<td>
									${rs.bjmc }
								</td>
								<th></th>
								<td></td>
							</tr>
						</tbody>

						<tfoot>
					      <tr>
					        <td colspan="4">
					          <div class="btn">
								 <button type="button" id="buttonClose"
									onclick="window.close();return false;">
									�ر�
								</button>
					          </div></td>
					      </tr>
					   </tfoot>
				</table> 
			</div>
		</html:form>
	</body>
</html>
