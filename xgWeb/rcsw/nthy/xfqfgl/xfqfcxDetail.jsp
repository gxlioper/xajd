<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- 头文件 -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script type="text/javascript" src="js/rcsw/rcswFunction.js"></script>
	<script language="javascript" src="js/xszzFunction.js"></script>
	<script language="javascript" src="js/String.js"></script>
	<script type="text/javascript" src="js/checkUtils.js"></script>
	<script type="text/javascript">
	</script>
</head>

<body>
	<html:form action="/rcsw_nthy_xfqfgl" method="post">

		<div class="tab">
		<table class="formlist" width="100%">
			<thead>
		    	<tr>
		        	<th colspan="4"><span>学生交费信息</span></th>
		        </tr>
		    </thead>
			<tbody>
			<tr>
				<th>学年</th>
				<td>
					${rs.xn }
				</td>
				<th>学号</th>
				<td>
					${rs.xh }
				</td>
			</tr>
			<tr>
				<th>姓名</th>
				<td>
					${rs.xm }
				</td>
				<th>性别</th>
				<td>${rs.xb }</td>
			</tr>
			<tr>
				<th>年级</th>
				<td>
					${rs.nj }
				</td>
				<th><bean:message key="lable.xsgzyxpzxy" /></th>
				<td>${rs.xymc }</td>
			</tr>
			<tr>
				<th>专业</th>
				<td>${rs.zymc }</td>
				<th>班级</th>
				<td>${rs.bjmc }</td>
			</tr>
			<tr>
				<th>交费情况</th>
				<td>${rs.jfzt }</td>
				<th>记录时间</th>
				<td>${rs.jlsj }</td>
			</tr>
			<tr>
				<th>备注</th>
				<td colspan="3">
					<html:textarea property="bz" cols="50" rows="6" onblur="checkLeng(this,500)" value="${rs.bz}" readonly="true"></html:textarea>
				</td>
			</tr>
			</tbody>
			<tfoot>
		      <tr>
		        <td colspan="4"><div class="bz">"<span class="red">*</span>"为必填项</div>
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
