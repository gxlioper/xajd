<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- 头文件 -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>
</head>

<body>
	<html:form action="/xszc" method="post">
		<div class="tab">
		<table class="formlist" width="100%">
			<thead>
		    	<tr>
		        	<th colspan="4"><span>学生注册信息</span></th>
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
					年级
				</th>
				<td>
					${rs.nj }
				</td>
				<th>
					<bean:message key="lable.xb" />
				</th>
				<td>
					${rs.xymc }
				</td>
			</tr>
			<tr>
				<th>
					专业
				</th>
				<td>
					${rs.zymc }
				</td>
				<th>
					班级
				</th>
				<td>
					${rs.bjmc }
				</td>
			</tr>
			<tr>
				<th>
					注册状态
				</th>
				<td>
					${rs.zczt }
				</td>
				<th>
					交费情况
				</th>
				<td>
					${rs.sfqfmc }
				</td>
			</tr>
			<tr>
				<th>
					未注册原因
				</th>
				<td colspan="3">
					<html:textarea property="wzcyy" name="rs" cols="60" rows="8" readonly="true"></html:textarea>
				</td>
			</tr>
			<tr>
				<th>
					操作人
				</th>
				<td>
					${rs.czr }
				</td>
				<th>
					操作时间
				</th>
				<td>
					${rs.czsj }
				</td>
			</tr>
			</tbody>
			<tfoot>
		      <tr>
		        <td colspan="4">
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
