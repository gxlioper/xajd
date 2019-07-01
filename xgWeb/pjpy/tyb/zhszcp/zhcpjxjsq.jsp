<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/syscommon/pagehead_V4.ini"%>
<!-- ͷ�ļ� -->
<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
<script type="text/javascript" src="js/rcsw/rcswFunction.js"></script>
<script type='text/javascript' src='/xgxt/dwr/interface/getDtjsInfo.js'></script>
<script type='text/javascript' src='/xgxt/dwr/interface/pjpyService.js'></script>
<script type="text/javascript" src="js/check.js"></script>
<script type="text/javascript" src="js/pjpy/typj.js"></script>
</head>
<body onload="setSqsj();display();">
	<html:form action="/typj" method="post">
		<input type="hidden" id="url" name="url" value="/pjpyTybZhcpjxj.do?method=zhcpjxjSq" />
		<input type="hidden" id="getStuInfo" name="getStuInfo"
			value="xm-xb-zymc-xymc-bjmc-mzmc-zzmmmc-csrq-lxdh" />
		<input type="hidden" name="message" id="message" value="${message }" />
		<input type="hidden" name="yhInfo" id="yhInfo" value="${yhInfo }" />
		<input type="hidden" name="save_nd" value="${sqsjInfo.nd}"/>
		<input type="hidden" name="save_xq" value="${sqsjInfo.xq}"/>
		<input type="hidden" name="save_sqsj" value="${sqsjInfo.sqsj}" id="sqsj"/>
		<input type="hidden" id="xxdm" value="${xxdm }"/>
		<input type="hidden" name="now" value="${nowTime }" id="now"/>
		<input type="hidden" name="pkValue" value="${pkValue }" id="pkValue"/>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>${title }</a>
			</p>
		</div>
		<div class="tab">
		<fieldset>
			<legend>
				������Ϣ
			</legend>
			<table class="formlist" width="100%">
				<tbody>
				<tr>
					<th>
						<font color="red">*</font>ѧ��
					</th>
					<td>
						<html:text property="save_xh" styleId="xh" value="${rs.xh}" 
							onkeypress="autoFillStuInfo(event.keyCode,this)"
							onblur="chkIsStu(this,'view_xsjbxx','xh')"
						/>
						<logic:notEqual value="stu" name="userType">
							<button type="button" onclick="showTopWin('/xgxt/stu_info.do',800,600);"
								class="btn_01" id="buttonFindStu">
								ѡ��
							</button>
						</logic:notEqual>
					</td>
					<th>����</th>
					<td>
						${rs.xm }
					</td>
				</tr>
				<tr>
					<th>�Ա�</th>
					<td>
						${rs.xb }
					</td>
					<th>
						<bean:message key="lable.xsgzyxpzxy" />
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
					<th>�༶</th>
					<td>
						${rs.bjmc }
					</td>
				</tr>
				<tr>
					<th>����</th>
					<td>${rs.mzmc }</td>
					<th>������ò</th>
					<td>${rs.zzmmmc }</td>
				</tr>
				<tr>
					<th>��������</th>
					<td>${rs.csrq }</td>
					<th>��ϵ�绰</th>
					<td>${rs.lxdh }</td>
				</tr>
				<tr>
					<th><font color="red">*</font>ѧ��</th>
					<td>
						<html:text property="save_xn" readonly="true" value="${sqsjInfo.xn }" styleId="xn"></html:text>
					</td>
					<th><font color="red">*</font>��ѧ������</th>
					<td>
						<html:select property="save_jxjdm" styleId="jxjdm" onchange="setSqsj();">
							<html:options collection="jxjList" property="dm"
								labelProperty="mc" />
						</html:select>
					</td>
				</tr>
				</tbody>
			</table>
		</fieldset>
		
		<fieldset>
			<legend>
				�ۺ����ʲ���
			</legend>
			<logic:empty  name="zcpm">
				<p style="height:120px">û�м�¼��</p>
			</logic:empty>
			<logic:notEmpty name="zcpm">
			<table width="99%" id="rsTable" class="formlist">
				<thead>
					<tr>
						<th align="center">ѧ��</th>
						<th align="center">ѧ��</th>
						<th align="center">����</th>
						<th align="center">����</th>
						<th align="center">����</th>
					</tr>
				</thead>
				<tbody>
					<logic:iterate id="v" name="zcpm" offset="0" scope="request">
						<tr align="center" style="cursor:hand">
							<td>
								${v.xn }
							</td>
							<td>
								${v.xqmc }
							</td>
							<td>
								${v.mc }
							</td>
							<td>
								${v.fs }
							</td>
							<td>
								${v.pm }
							</td>
						</tr>
					</logic:iterate>
				</tbody>
			</table>
			</logic:notEmpty>
		</fieldset>
		<table width="100%" class="formlist">
			<tfoot>
		      <tr>
		        <td colspan="4"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
		          <div class="btn">
		            <button type="button" class="button2" style="width:80px" id="buttonSave"
						onclick="saveUpdate('/xgxt/pjpyTybZhcpjxj.do?method=zhcpjxjSq&doType=save','save_xh-save_xn-save_jxjdm');">
						��&nbsp;&nbsp;��
					</button>
		          </div>
		        </td>
		      </tr>
		    </tfoot>
		</table>
	</html:form>
	<logic:present name="yhInfo">
		<script>
				alert(''+$('yhInfo').value);
				if (window.dialogArguments) {
					window.close();
					window.dialogArguments.document.getElementById('search_go').click();
				}
			</script>
	</logic:present>
	<logic:present name="result">
		<script>
				alert(''+$('message').value);
				if (window.dialogArguments) {
					window.close();
					window.dialogArguments.document.getElementById('search_go').click();
				}
			</script>
	</logic:present>
</body>
</html>