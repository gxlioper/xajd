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
	<html:form action="/wjcfzjlgwh" method="post">
	<input type="hidden" name="userType" id="userType" value="${userType }"/>
		<input type="hidden" name="failinfo" id="failinfo" value="${failinfo}"/>
		<input type="hidden" name="pkString" id="pkString" value="${pkString }"/>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>Υ�ʹ��� - ������� - �����������</a>
			</p>
		</div>
		
		<div class="tab">
		<table class="formlist" width="99%">
			<thead>
				<tr style="height:23px">
					<th colspan="4">
						<span>�����������</span>
					</th>
				</tr>
			</thead>
			<tr style="height:23px">
				<th>
					<font color="red">*</font>�����ĺ�
				</th>
				<td align="left">
					<html:text property="cfwh" styleId="cfwh"></html:text>
				</td>
			</tr>
			<tr>
				<th>
					<font color="red">*</font>����ʱ��
				</th>
				<td align="left">
					<html:text property="cfsj" styleId="cfsj" onblur="dateFormatChg(this)" style="cursor:hand;"
								onclick="return showCalendar('cfsj','y-mm-dd');" readonly="true"></html:text>
				</td>
			</tr>
			<tr style="height:23px">
					<logic:equal value="xy" name="userType">
						<th>
						<bean:message key="lable.xsgzyxpzxy" />����<br/>���
						</th>
						<td align="left" colspan="">
							<html:textarea property="xyyj" rows="5" styleId="xyyj" style="width:98%" ></html:textarea>
						</td>
					</logic:equal>
					<logic:notEqual value="xy" name="userType">
						<th>
							ѧУ����<br/>���
						</th>
						<td align="left" colspan="">
							<html:textarea property="xxclyj" rows="5" styleId="xxclyj" style="width:98%" ></html:textarea>
						</td>
					</logic:notEqual>
			</tr>
			<tfoot>
		      <tr>
		        <td colspan="4"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
		          <div class="btn">
					<button type="button" id="btn_save" onclick="saveinfo('wjcf_zjlg_cfxxplsh.do?operType=save','cfwh-cfsj');" >
						����
					</button>
					<button type="button" id="btn_close" onclick="window.close();return false;" >
						�ر�
					</button>
		          </div></td>
		      </tr>
		    </tfoot>
		</table>
		</div>
				<!-- �������ʾҳ�� -->	
		<jsp:include flush="true" page="/syscommon/saveprompt.jsp"></jsp:include>
	</html:form>
</body>
</html>
