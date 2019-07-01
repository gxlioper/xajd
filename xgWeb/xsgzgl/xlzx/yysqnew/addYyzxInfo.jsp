<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script language="javascript" src="js/check.js"></script>
			<script language="javascript" src="xsgzgl/xlzx/yysqnew/js/addYyzxInfo.js"></script>
			<script type="text/javascript">
				jQuery(function(){
                    jQuery("#jzxx_tb").hide();
					if('${pbfs}' == '2'){
						jQuery(":input").not("#xh").not("button").not("#yyfs").not("#yyfs").not("#jzxx_tb input").not("#jzxx_tb textarea").click(function(){
							if(jQuery("#xh").val() == ""){
								showAlert("����ѡ��ѧ����");
								return false;
							}
						})
					}
				});
				function changeYyfs() {
					var yyfs = jQuery("#yyfs").val();
					if(yyfs == "jz"){
						jQuery("#jzxx_tb").show();
					}else{
                        jQuery("#jzxx_tb").hide();
					}
                }
			</script>
		<style type="text/css">
			#jzxx_tb input{
				width: 100px;
			}
		</style>
	</head>
	<body>
		<input type="hidden" name="path" id="path" value="${path}" />
		<input type="hidden" name="sjhm" id="sjhm" value="${xsInfo.sjhm}" />
		<input type="hidden" name="pbfs" id="pbfs" value="${pbfs}"/>
		<html:form action="/xlzx_zxyyclnew" method="post" styleId="form">
			<div style='width:100%; height:450px; overflow-y:auto;overflow-x:hidden'>
				<table width="100%" border="0" class="formlist">
					<thead>
					<tr>
						<th colspan="2">
							<span>ԤԼ��ʽ��Ϣ</span>
						</th>
					</tr>
					</thead>
					<th width="16%">
						<span class="red">*</span>ԤԼ��ʽ
					</th>
					<td >
						<html:select property="yyfs" styleId="yyfs" style="width:100px;" onchange="changeYyfs();">
							<html:option value="xc" >�ֳ�ԤԼ</html:option>
							<html:option value="dh">�绰ԤԼ</html:option>
							<html:option value="jz">�ҳ�ԤԼ</html:option>
						</html:select>
					</td>
				</table>
				<table width="100%" border="0" class="formlist" id="jzxx_tb">
					<thead>
					<tr>
						<th colspan="4">
							<span>�ҳ���д��Ϣ</span>
						</th>
					</tr>
					</thead>
					<tbody>
					<tr>
						<th width="16%">
							<span class="red">*</span><span>�ҳ�����</span>
						</th>
						<td width="34">
							<input type="text" name="jzxm" id="jzxm" onblur="checkLen(this,10)"/>
						</td>
						<th width="16%">
							<span class="red">*</span><span>�Ա�</span>
						</th>
						<td width="34">
							<label><input type="radio" name="jzxb" value="1" style="width: 15px;"/>��</label>
							<label><input type="radio" name="jzxb" value="2" style="width: 15px;"/>Ů</label>
						</td>
					</tr>
					<tr>
						<th width="16%">
							<span class="red">*</span><span>��ϵ�绰</span>
						</th>
						<td width="34">
							<input type="text" name="jzlxdh" id="jzlxdh" maxlength="12"  onblur="checkLxdh();" />
						</td>
						<th width="16%">
							<span class="red">*</span><span>��ѧ����ϵ</span>
						</th>
						<td width="34">
							<input type="text" name="gx" id="gx" onblur="checkLen(this,10)"/>
						</td>
					</tr>
					<tr>
						<th width="16%">
							<span class="red">*</span><span>��������</span>
						</th>
						<td width="34">
							<input type="text" name="jzdzyx" id="jzdzyx"
								   maxlength="30"  onblur="checkEmail(this)" />
						</td>
						<th width="16%">
							<span>��ͥ��ȫ</span>
						</th>
						<td width="34">
							<input type="text" name="jtjq" id="jtjq" onblur="checkLen(this,10)"/>
						</td>

					</tr>
					<tr>
						<th width="16%">
							<span>����ְҵ</span>
						</th>
						<td width="34">
							<input type="text" name="fqzy" id="fqzy" onblur="checkLen(this,10)"/>
						</td>
						<th width="16%">
							<span>��ѧ��</span>
						</th>
						<td width="34">
							<input type="text" name="fxl" id="fxl" onblur="checkLen(this,10)"/>
						</td>

					</tr>
					<tr>
						<th width="16%">
							<span>ĸ��ְҵ</span>
						</th>
						<td width="34">
							<input type="text" name="mqzy" id="mqzy" onblur="checkLen(this,10)"/>
						</td>
						<th width="16%">
							<span>ĸѧ��</span>
						</th>
						<td width="34">
							<input type="text" name="mxl" id="mxl" onblur="checkLen(this,10)"/>
						</td>
					</tr>
					<tr>
						<th width="16%">
							<span class="red">*</span><span>��ͥסַ</span>
						</th>
						<td  colspan="3">
							<input type="text" name="jtdz" id="jtdz" style="width: 95%" onblur="checkLen(this,50)"/>
						</td>
					</tr>
					<tr>
						<th width="16%">
							<span class="red">*</span><span>ѧ���Ƿ�֪��</span>
						</th>
						<td width="34">
							<label><input type="radio" name="xssfzx" value="1" style="width: 15px;"/>��</label>
							<label><input type="radio" name="xssfzx" value="0" style="width: 15px;"/>��</label>
						</td>
						<th width="16%">
							<span class="red">*</span><span>����Ա�Ƿ�֪��</span>
						</th>
						<td width="34">
							<label><input type="radio" name="fdysfzx" value="1" style="width: 15px;"/>��</label>
							<label><input type="radio" name="fdysfzx" value="0" style="width: 15px;"/>��</label>
						</td>
					</tr>
					<tr>
						<th width="16%">
							<span class="red">*</span><span>����Ŀ��</span>
							<br/><font color="red">(��500��)</font>
						</th>
						<td  colspan="3">
							<textarea rows="4" cols="" style="width: 98%"
									  id="lfmd" name="lfmd" onblur="checkLen(this,500)"></textarea>
						</td>
					</tr>
					</tbody>

				</table>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>ѧ��������Ϣ</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/comm/bdpz/selectStudentAjaxAll.jsp" %>

					<thead>
						<tr >
							<th colspan="4">
								<span>��ѯʦ��Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody id="zxsInfo">
						<tr style="height:10px">
							<th width="16%">
								<logic:equal value="true" name="isZxsGly">
									<span class="red">*</span>
								</logic:equal>
								ְ����
							</th>
							<td width="34%">
								<logic:equal value="false" name="isZxsGly">
									${zxsInfo.zgh}
									<input type="hidden" id="zgh" class="zgh" name="zgh" style="width:120px;" value="${zxsInfo.zgh}"/>
								</logic:equal>
								<logic:equal value="true" name="isZxsGly">
									<input type="text" id="zgh" class="zgh" name="zgh" style="width:120px;" value="${zxsInfo.zgh}"/>
									<button class="btn_01" type="button"  
										onclick="showDialog('��ѡ��һ����ѯʦ',800,500,'xlzx_yysqnew.do?method=showZxs');">ѡ��
									</button>
								</logic:equal>
							</td>
							
							<th  width="16%">
								����
							</th>
							<td  width="34%" class="xm">
								${zxsInfo.xm}
							</td>
						</tr>
						<tr style="height:10px">
							<th  width="16%">
								�Ա�
							</th>
							<td  width="34%" class="xb">
								${zxsInfo.xb }
							</td>
							<th width="16%">
								����
							</th>
							<td  width="34%" class="nl">
								${zxsInfo.age}
							</td>
						</tr>
						<tr style="height:10px">
							<th width="16%">
								��ϵ�绰
							</th>
							<td  width="34%" class="lxdh">
								${zxsInfo.lxdh }
								
							</td>
							<th width="16%">
								���ڲ���
							</th>
							<td  width="34%" class="bmmc">
								${zxsInfo.bmmc }
								
							</td>
						</tr>						
					</tbody>			
							
					
					<thead>
						<tr>
							<th colspan="4">
								<span>��ѯ������Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody id="dealInfo">
					<tr style="height:10px" >
							<th>
								<span class="red">*</span>��ѯ��������
							</th>
							<td >
									<html:text property="zxrq" styleId="zxrq"   style="width:100px" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'${today}'})"  onchange="delValidate();"/>
							</td>
							<th  width="16%">
								<logic:equal name="pbfs" value="2"><span class="red">*</span></logic:equal>��ѯʱ��
							</th>
							<td  width="34%" >
								<logic:notEqual name="pbfs" value="2">
									<html:text property="zxqssj" styleId="zxqssj" style="width:30%"  value="" onfocus="WdatePicker({dateFmt:'HH:mm',maxDate:'#F{$dp.$D(\\'zxjssj\\')}'})" readonly="true"/>&nbsp;��&nbsp;
								    <html:text property="zxjssj" styleId="zxjssj" style="width:30%"  value="" onfocus="WdatePicker({dateFmt:'HH:mm',minDate:'#F{$dp.$D(\\'zxqssj\\')}'})" readonly="true"/>
								</logic:notEqual>
								<logic:equal name="pbfs" value="2">
									<select name="sjddm" id="sjddm" style="width:60%">
										<option></option>
									</select>
								</logic:equal>
							</td>
						</tr>
						<tr>

							<th>
								��ѯ��ϵ�绰
							</th>
							<td colspan="3">
								<html:text property="zxtell" styleId="zxtell"  value="${zxsInfo.lxdh}"  maxlength="11" onblur="checkInputData(this);"/>
							</td>

						</tr>
						<tr>
							<th>
								��ѯ��ַ
							</th>
							<td colspan="3">
								<html:text property="zxdz" styleId="zxdz" style="width:90%"  maxlength="50"  value="${zxsInfo.address }"  />
							</td>
						</tr>
						<tr style="height:10px" name="yyfkId">
							<th  width="16%">
								��ע<br/>
								<font color="red"><B>(��500��)</B></font>
							</th>
							<td  width="34%" colspan="3">
								<html:textarea  property='bz' styleId="bz" value="" style="word-break:break-all;width:99%" onblur="chLengs(this,500);" rows='4' />
							</td>
						</tr>
					</tbody>
				</table>

			</div>
			<table width="100%" border="0" class="formlist">
				<tfoot>
					<tr>
						<td colspan="4">
							<div class="bz">
								"<span class="red">*</span>"Ϊ������
							</div>
							<div class="btn">
								<button id="buttonSave" onclick="saveYysqInfo();return false;">
									�� ��
								</button>
								<button onclick="Close();return false;">
									�� ��
								</button>

							</div>
						</td>
					</tr>
				</tfoot>
			</table>

		</html:form>
	</body>
</html>

