<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>

<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
<script type="text/javascript" src="js/wjcfFuction.js"></script>	
</head>

<body>
	<html:form action="/wjcfzjcmwh" method="post">
	<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>Υ�ʹ��� - ����ά�� - ����Υ����Ϣ</a>
			</p>
		</div>
		<div class="tab">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>�����У�쿴��ϸ��Ϣ</span>
							</th>
						</tr>
					</thead>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button type="button" class="button2" id="btn_close" onclick="window.close();return false;" style="width:80px">
											�� ��
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<tbody>
			<tr style="height:23px">
				<th align="right" width="15%">
					<font color="red">*</font>ѧ��
				</th>
				<td align="left" width="35%">
					${rs.xh }
				</td>
				<th align="right" width="15%">
					<font color="red">*</font>ѧ��
				</th>
				<td align="left" width="35%">
					${rs.xn }
				</td>
			</tr>
			<tr style="height:23px">
				<th align="right">
					����
				</th>
				<td align="left">
					${rs.xm }
				</td>
				<th align="right">
					<font color="red">*</font>���
				</th>
				<td align="left">
					${rs.nd }
				</td>
			</tr>
			<tr style="height:23px">
				<th align="right">
					�Ա�
				</th>
				<td align="left">
					${rs.xb }
				</td>
				<th align="right">
					�������
				</th>
				<td align="left">
					${rs.cflbmc }
				</td>
			</tr>
			<tr style="height:23px">
				<th align="right">
					�꼶
				</th>
				<td align="left">
					${rs.nj }
				</td>
				<th align="right">
					����ԭ��
				</th>
				<td align="left">
					${rs.cfyymc }
				</td>
			</tr>
			<tr style="height:23px">
				<th align="right">
					<bean:message key="lable.xsgzyxpzxy" />
				</th>
				<td align="left">
					${rs.xymc }
				</td>
				<th align="right">
					����ѧ��/���
				</th>
				<td align="left">
					${rs.cfxn }/${rs.cfnd }
				</td>
			</tr>
			<tr style="height:23px">
				<th align="right">
					רҵ
				</th>
				<td align="left">
					${rs.zymc }
				</td>
				<th align="right">
					�����ĺ�
				</th>
				<td align="left">
					${rs.cfwh }
				</td>
			</tr>
			<tr style="height:23px">
				<th align="right">
					�༶
				</th>
				<td align="left" colspan="">
					${rs.bjmc }
				</td>
				<th align="right">
					����ʱ��
				</th>
				<td align="left">
					${rs.cfsj }
				</td>
			</tr>
		<tr style="height:23px">
				<th align="right">
					������У�쿴ʱ��
				</th>
				<td align="left" colspan="">
					${rs.lxcksj }
				</td>
				<th align="right">
					
				</th>
				<td align="left">
					
				</td>
			</tr>
		<tr style="height:23px">
				<th align="right">
					��У�쿴�ڼ�ѧ������ǩ��
				</th>
				<td align="left" colspan="3">
					<html:textarea name="rs" property="xsbx" styleId="xsbx" rows="7" style="width:95%"></html:textarea>
				</td>
			</tr>
			<logic:equal value="11647" name="xxdm">
			<tr style="height:23px">
				<th align="right">
					������Ҫ��ǰ�����У�쿴��<br/>����Ҫ��ǰ�����ע�����ɣ�
				</th>
				<td align="left" colspan="3">
					<html:textarea name="rs" property="tqjcly" styleId="tqjcly" rows="5" style="width:95%"></html:textarea>
				</td>
		</tr>
		</logic:equal>
		<tr style="height:23px">
				<th align="right">
					�����У�쿴ʱ��
				</th>
				<td align="left" colspan="">
					${rs.jcsj }
				</td>
				<th align="right">
					�����У�쿴�ĺ�
				</th>
				<td align="left">
					${rs.jcwh }
				</td>
			</tr>
			<tr style="height:23px">
				<th align="right">
					�����У�쿴���
				</th>
				<td align="left" colspan="">
					${rs.jcjg }
				</td>
				<th align="right">
				
				</th>
				<td align="left">
				
				</td>
			</tr>
		</table>
	</html:form>
</body>
