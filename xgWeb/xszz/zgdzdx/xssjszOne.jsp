<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
</head>
<!-- ͷ�ļ� -->
<body>
	<script type="text/javascript" src="js/xszz/xszzFunction.js"></script>
	<html:form action="/zgdzdx_xszz" method="post">
		<input type="hidden" name="pkValue" value="${pkValue }" />

		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>��ѧ���� - ��������ʱ������ - ��������</a>
			</p>
		</div>

		<div class="tab">
			  <table width="100%"  border="0" class="formlist">
			    <thead>
			    	<tr>
			        	<th colspan="4"><span>��������</span></th>
			        </tr>
			    </thead>
			     <tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
			          <div class="btn">
			          	<logic:notEqual value="view" name="doType">
			          		<button type="button" name="�ύ" onclick="saveUpdate('/xgxt/zgdzdx_xszz.do?method=xssjszOne&doType=save','save_kssj-save_jssj');">�� ��</button>
			          </logic:notEqual>
			            <button type="button" name="�ر�" onclick="window.close();return false;">�ر�</button>
			          </div></td>
			      </tr>
			    </tfoot>		    
				<tbody>
					<tr>
						<th align="right">
							<bean:message key="lable.xsgzyxpzxy" />��
						</th>
						<td>
							${rs.xymc }
							<html:hidden property="save_xydm" name="rs" />
						</td>
					</tr>
					<tr>
						<th align="right">
							<font color="red">*</font>��ʼʱ�䣺
						</th>
						<td>
							<html:text property="save_kssj" readonly="true" styleId="kssj"
								name="rs" onclick="showCalendar(this.id,'y-mm-dd');"
								onblur='dateFormatChg(this)'></html:text>
						</td>
					</tr>
					<tr>
						<th align="right">
							<font color="red">*</font>����ʱ�䣺
						</th>
						<td>
							<html:text property="save_jssj" readonly="true" styleId="jssj"
								name="rs" onclick="showCalendar(this.id,'y-mm-dd');"
								onblur='dateFormatChg(this)'></html:text>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
	</html:form>
	<logic:present name="result">
		<script>
			alert('${message}');
			if (window.dialogArguments) {
				window.close();
				if(window.dialogArguments.document.getElementById("isPage")){
						window.dialogArguments.document.getElementById("isPage").value="yes";
				}
				window.dialogArguments.document.getElementById('search_go').click();
			}
		</script>
	</logic:present>
</body>
</html>
