<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script type="text/javascript">
			function dataSave(url,mustFill){
				var eles = mustFill.split("-");
				for (i = 0; i < eles.length; i++) {
					if (document.getElementById(eles[i]).value == "") {
						alertInfo("�����ֶ�δ��������");
						return false;
					}
				}
				$('buttonSave').disabled = "disabled";
				document.forms[0].action = url;
				document.forms[0].submit();
			}
			
		</script>
		
	</head>
	<body>
		<html:form action="/zjjj_rcsw_qjgl" method="post">
			<input type="hidden" name="pkValue" value="${param.pkValue }"/>
			<input type="hidden" name="doType" value="${doType}"/>
				<div class="tab">
					<table width="100%" class="formlist">
						<thead>
							<tr>
								<th colspan="4"><a>ѧ�������Ϣ</a></th>
							</tr>
						</thead>
						<tr>
							<th>
								<font color="red">*</font>ѧ��
							</th>
							<td>
								<input type="text" id="xh" name="xh" value="${rs.xh }" readonly="readonly" />
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
								ѧ��
							</th>
							<td>
								<input type="hidden" name="xn" value="${xn }" />
								${rs.xn }
							</td>
							
							<th>
								ѧ��
							</th>
							<td>
								<input type="hidden" name="xq" value="${xq }" />
								${xqmc }
							</td>
						</tr>
						<tr>
							<th>
								Ժϵ
							</th>
							<td>
								${rs.xymc }
							</td>
							<th>
								רҵ
							</th>
							<td>
								${rs.zymc }
							</td>
							
						</tr>
						<tr>
							<th>
								�༶
							</th>
							<td>
								${rs.bjmc }
							</td>
							<th>
								��ͥ��ַ
							</th>
							<td>
								${rs.jtdz }
							</td>
						</tr>
						<tr>
							<th>
								��ϵ��ʽ
							</th>
							<td>
								${rs.sjhm }
								<logic:notEqual value="" name="rs" property="lxdh">
									<logic:notEqual value="" name="rs" property="sjhm">
									/
									</logic:notEqual>
								</logic:notEqual>
								${rs.lxdh }
							</td>
							<th>�ҳ���ϵ��ʽ</th>
							<td>
								<html:text property="jzdh" maxlength="20" value="${rs.jzdh}"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								��ٿ�ʼʱ��
							</th>
							<td>
								<html:text property="qjkssj" styleId="qjkssj" onclick="" onkeydown="onlyBackSpace(this,event);" onblur="dateFormatChg(this)"
								onclick="return showCalendar('qjkssj','y-mm-dd');" style="cursor:hand " value="${rs.qjkssj}"></html:text>
							</td>
							<th>
								��ٽ���ʱ��
							</th>
							<td>
								<html:text property="qjjssj" styleId="qjjssj" onclick="" onkeydown="onlyBackSpace(this,event);" onblur="dateFormatChg(this)"
								onclick="return showCalendar('qjjssj','y-mm-dd');" style="cursor:hand " value="${rs.qjjssj}"></html:text>
							</td>
						</tr>
						
						<tr>
							<th>
								<font color="red">*</font>�������
							</th>
							<td>
								<html:text property="qjts" styleId="qjts" maxlength="2" value="${rs.qjts}" onblur="checkInputData(this);" />��
							</td>
							<th>
								����ڼ��Ƿ�סУ
							</th>
							<td>
								<html:select property="sfzx" value="${rs.sfzx }">
									<html:option value="��">��</html:option>
									<html:option value="��">��</html:option>
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								�������<br/>
								<font color="red">(����¼��200��)</font>
							</th>
							<td colspan="3">
								<html:textarea property="qjsy" style="width:99%" value="${rs.qjsy}" style="width: 95%;word-break:break-all;"
								 onblur="chLeng(this,200);" rows='5' />
							</td>
						</tr>
						<tr>
							<th>
								��ע<br/>
								<font color="red">(����¼��100��)</font>
							</th>
							<td colspan="3">
								<html:textarea property="bz" style="width:99%"  onblur="chLeng(this,100);" value="${rs.bz}" style="width: 95%;word-break:break-all;"
									rows='5' />
							</td>
						</tr>
						
						<tfoot>
					      <tr>
					        <td colspan="4"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
					          <div class="btn">
								  <button type="button" id="buttonSave"
									onclick="dataSave('/xgxt/cdty_rcsw_qjgl.do?method=qjxgUpdate&opera=update','qjkssj-qjjssj-qjts')">
									����
								</button>
								 <button type="button" id="buttonClose"
									onclick="window.close();return false;">
									�ر�
								</button>
					          </div></td>
					      </tr>
					   </tfoot>
				</table> 
			</div>
		</html:form>
		
		<logic:present name="message">
			<input type="hidden" id="message" value="${message }"/>
			<script type="text/javascript">
				alertInfo($('message').value, function(){
					Close();
					if(window.dialogArguments && window.dialogArguments.document.all("search_go")){
				   	window.dialogArguments.document.getElementById('search_go').click(); 
					}
				});
				
			</script>
		</logic:present>
	</body>
</html>
