<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- ͷ�ļ� -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>
</head>

<body>
	<html:form action="/xszc" method="post">
		<div class="tab">
		<table class="formlist" width="100%">
			<thead>
		    	<tr>
		        	<th colspan="4"><span>ѧ��ע����Ϣ</span></th>
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
					�꼶
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
					רҵ
				</th>
				<td>
					${rs.zymc }
				</td>
				<th>
					�༶
				</th>
				<td>
					${rs.bjmc }
				</td>
			</tr>
			<tr>
				<th>
					ע��״̬
				</th>
				<td>
					${rs.zczt }
				</td>
				<th>
					�������
				</th>
				<td>
					${rs.sfqfmc }
				</td>
			</tr>
			<tr>
				<th>
					δע��ԭ��
				</th>
				<td colspan="3">
					<html:textarea property="wzcyy" name="rs" cols="60" rows="8" readonly="true"></html:textarea>
				</td>
			</tr>
			<tr>
				<th>
					������
				</th>
				<td>
					${rs.czr }
				</td>
				<th>
					����ʱ��
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
