<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/xsztz/xwtzxm/jg/js/xwtzxm.js"></script>
		<script type="text/javascript">
	
		</script>
		<style type = "text/css">
		</style>
	</head>
	<body>
		<html:form action="/xwtzgl_xmjg" method="post" styleId="XwTzXmJgForm">
			<input name="sqid" value="${hdmap.sqid}" type="hidden" />
			<input name="xh" value="${xmjbxx.xh}" type="hidden" />
			<div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom:0px;" >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>基本信息</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/comm/bdpz/viewStudent.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
								<span>项目信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>项目名称</th>
							<td>
								${hdmap.xmmc}
							</td>
							<th>项目级别</th>
							<td >
								${hdmap.xmjbmc}
                            </td>
						</tr>
						<tr>
							<th>学年</th>
							<td>
								${hdmap.xn}
							</td>
							<th>学期</th>
							<td>
								${hdmap.xqmc}
							</td>
						</tr>
						<tr>
							<th>所属科目</th>
							<td>
								${hdmap.sskmmc}
							</td>
							<th>项目开始时间</th>
							<td>
								${hdmap.xmkssj}
							</td>
						</tr>
						<tr>
							<th>参加地点</th>
							<td >
								${hdmap.cjdd}
							</td>
							<th>联系方式
							</th>
							<td>
								${hdmap.lxfs}
							</td>
						</tr>
						<tr>
							<th>获得奖项</th>
							<td >
								${hdmap.hdjx}
							</td>
							<th>获得学分</th>
							<td >
								${hdmap.zxf}
							</td>
						</tr>
							<th>负责人</th>
							<td >
								${hdmap.ylzd1}
							</td>
						<tr>
							<th>申请理由</th>
							<td colspan="3">
								${hdmap.sqly}
							</td>
						</tr>
					</tbody>
				</table>
				</div>	
				<div style="height:30px;"></div>
				<%--;height:520px --%>
			<div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"<span class="red">*</span>"为必填项
								</div>
								<div class="btn">
									<button type="button" onclick="iFClose();">
										关闭
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