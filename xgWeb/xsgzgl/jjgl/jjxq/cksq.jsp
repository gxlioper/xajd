<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
	<%@ include file="/syscommon/head.ini"%>
	<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
	<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
	<script type="text/javascript">
	</script>
  </head>
  
  <body>
		<html:form action="/jjgl_jjxq" method="post" styleId="jjxqForm">
			<div class='tab' style='tab;width:100%;height:300px;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="5">
								<span>家教信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
					    <tr>
					    	<th>子女姓名</th>
					    	<td colspan="3">${xqModelMap.znxm }</td>
					    </tr>
					    <tr>
					    	<th width="20%">发布人</th>
					    	<td width="30%">${xqModelMap.sqr }</td>
					    	<th width="20%">发布时间</th>
					    	<td width="30%">${xqModelMap.sqsj }</td>
					    </tr>
					    <tr>
					    	<th>家教学科</th>
					    	<td>${xqModelMap.jjxkmc }</td>
					    	<th>学科年级</th>
					    	<td>${xqModelMap.jjnjmc }</td>
					    </tr>
					    <tr>
					    	<th>家教时间</th>
					    	<td>${xqModelMap.jjsj }</td>
					    	<th>家教地点</th>
					    	<td>${xqModelMap.jjdd }</td>
					    </tr>
					    <tr>
					    	<th>家教老师要求</th>
					    	<td colspan="3">${xqModelMap.jjlsyq }</td>
					    </tr>
					    <tr>
					    	<th>备注</th>
					    	<td colspan="3">${xqModelMap.bz }</td>
					    </tr>
					    <tr>
					    	<th>开始时间</th>
					    	<td>${jjxx.kssj }</td>
					    	<th>结束时间</th>
					    	<td>${jjxx.jssj }</td>
					    </tr>
					    <tr>
					    	<th>总时长</th>
					    	<td>${jjxx.zsc }（小时）</td>
					    	<th>总费用</th>
					    	<td>${jjxx.zfy }（￥）</td>
					    </tr>
					    <tr>
					    	<th>家教心得</th>
					    	<td colspan="3">${jjxx.jjxd }</td>
					    </tr>
					    <tr>
					    	<th>家长评价</th>
					    	<td colspan="3">${jjxx.jzpj }</td>
					    </tr>
					</tbody>
				</table>
			</div>
			<div>
				<table width="100%" border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button type="button" name="关闭" id="buttonClose" onclick="Close();return false;">
										关 闭
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
