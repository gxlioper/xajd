<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/xscxqyb/js/xscxqyb.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript"></script>
	</head>
	<body>
	<html:form action="/rcsw_xscxqybgl.do" styleId="xscxqybForm" method="post">
			<html:hidden property="xn"  styleId="xn" />
			<html:hidden property="xq"  styleId="xq" />
			<html:hidden property="txr"  styleId="txr" />
			<html:hidden property="jgid"  styleId="jgid" />
			<div style='tab;width:100%;height:418px;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>�±���Ϣ</span>
							</th>
						</tr>
					</thead>
				<tbody>
						<tr>
							<th width="18%">ѧ��</th>
							<td width="32%">
								${xn}
							</td>
							<th width="18%">ѧ��</th>
							<td width="32%">
								${xq}
							</td>
					    </tr>
					    <tr>
							<th width="18%">��д��</th>
							<td width="32%">
								${txr}
							</td>
							<th width="18%">
							<span class="red">*</span>�·�
							</th>
							<td>
								<html:text property="yf" styleId="yf" onfocus="return showCalendar(this.id,'yyyy-MM');" maxlength="20"></html:text>
							</td>
					    </tr>
					    <tr>
							<th align="right" width="10%">
								<span class="red">*</span>���¹�����չ���&nbsp;
								<br />
								<font color="red">(��500��)</font>
							</th>
							<td colspan="3">
								<html:textarea rows="4" property="bygzkzqk" styleId="bygzkzqk"
									style="width:97%" onblur="checkLen(this,500);"></html:textarea>
							</td>
						</tr>
						<tr>
							<th align="right" width="10%">
								<span class="red">*</span>ѧ����ע�ȵ�&nbsp;
								<br />
								<font color="red">(��500��)</font>
							</th>
							<td colspan="3">
								<html:textarea rows="4" property="xsgzrd" styleId="xsgzrd"
									style="width:97%" onblur="checkLen(this,500);"></html:textarea>
							</td>
						</tr>
						<tr>
							<th align="right" width="10%">
								<span class="red">*</span>ѧ��˼�붯̬&nbsp;
								<br />
								<font color="red">(��500��)</font>
							</th>
							<td colspan="3">
								<html:textarea rows="4" property="xssxdt" styleId="xssxdt"
									style="width:97%" onblur="checkLen(this,500);"></html:textarea>
							</td>
						</tr>
						<tr>
							<th align="right" width="10%">
								<span class="red">*</span>ѧ�����󼰹�������&nbsp;
								<br />
								<font color="red">(��500��)</font>
							</th>
							<td colspan="3">
								<html:textarea rows="4" property="xstsjgzjy" styleId="xstsjgzjy"
									style="width:97%" onblur="checkLen(this,500);"></html:textarea>
							</td>
						</tr>
						
				</tbody>
				</table>
			</div>
				<table width="100%" border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"Ϊ������</div>
								<div class="btn">
									<button type="button" type="button" onclick="saveUpdate();">����</button>
									<button type="button" type="button" onclick="iFClose();">�� ��</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
		</html:form>
	</body>
</html>