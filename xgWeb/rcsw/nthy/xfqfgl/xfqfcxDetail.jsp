<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- ͷ�ļ� -->
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
		        	<th colspan="4"><span>ѧ��������Ϣ</span></th>
		        </tr>
		    </thead>
			<tbody>
			<tr>
				<th>ѧ��</th>
				<td>
					${rs.xn }
				</td>
				<th>ѧ��</th>
				<td>
					${rs.xh }
				</td>
			</tr>
			<tr>
				<th>����</th>
				<td>
					${rs.xm }
				</td>
				<th>�Ա�</th>
				<td>${rs.xb }</td>
			</tr>
			<tr>
				<th>�꼶</th>
				<td>
					${rs.nj }
				</td>
				<th><bean:message key="lable.xsgzyxpzxy" /></th>
				<td>${rs.xymc }</td>
			</tr>
			<tr>
				<th>רҵ</th>
				<td>${rs.zymc }</td>
				<th>�༶</th>
				<td>${rs.bjmc }</td>
			</tr>
			<tr>
				<th>�������</th>
				<td>${rs.jfzt }</td>
				<th>��¼ʱ��</th>
				<td>${rs.jlsj }</td>
			</tr>
			<tr>
				<th>��ע</th>
				<td colspan="3">
					<html:textarea property="bz" cols="50" rows="6" onblur="checkLeng(this,500)" value="${rs.bz}" readonly="true"></html:textarea>
				</td>
			</tr>
			</tbody>
			<tfoot>
		      <tr>
		        <td colspan="4"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
		          <div class="btn">
					<button type="button" class="button2" id="buttonSave" onclick="Close();return false;">
						��&nbsp;&nbsp;��
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
