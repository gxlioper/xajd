<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/xthd/xmsz/js/xmsz.js"></script>
		<script type="text/javascript">
			jQuery(function(){
				//Ϊbuttonע���¼�
				jQuery("#but_save").click(function(){save("xmszXg","update")});
				onShow();
			});
		</script>
	</head>
	<body style="width:100%">
		<html:form action="/rcsw_txhd_xmszCx" method="post" styleId="demoForm">
		<% String xxdm = (String) request.getAttribute("xxdm"); %>
		<div class="prompt" style="display:none;">
			<h3>
				<span>��ʾ��</span>
			</h3>
			<p>
				<font color="red">
					��ǰ��Ŀ����ѧ�����룬����������޸�
				</font>
			</p>
			<a class="close" title="����" onclick="this.parentNode.style.display='none';"></a>
		</div>
		<html:hidden property="xn" styleId="xn" />
		<html:hidden property="xq" styleId="xq" />
		<html:hidden property="xmdm" styleId="xmdm" />
		<input type="hidden" name="spzt" id="spzt" value="${spzt }" />
		<div style='tab;width:100%;height:434px;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>�޸Ļ��Ŀ</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_jbxx">
						<tr>
							
							<th width="16%">
								<font color="red">*</font>��Ŀ����
							</th>
							<td width="34%">
								<html:text property="xmmc" styleId="xmmc" maxlength="20" styleClass="text_nor"></html:text>
							</td>
							<th width="18%">
								<font color="red">*</font>�ʱ��
							</th>
							<td width="31%" >
								<html:text property="hdkssj" styleId="hdkssj"  style="width: 80px;"  styleClass="text_nor" onclick="return showCalendar('hdkssj','yyyy-MM-dd',true,'hdjssj');" readonly="true" ></html:text>&nbsp;��
								<html:text property="hdjssj" styleId="hdjssj"  style="width: 80px;"  styleClass="text_nor" onclick="return showCalendar('hdjssj','yyyy-MM-dd',false,'hdkssj');" readonly="true" ></html:text>
							</td>
						</tr>
						<tr>
							<th width="16%">
								<font color="red">*</font>����
							</th>
							<td width="34%">
								<html:select property="lbdm" styleId="lbdm" style="width:155px">
								<html:options collection="lbList" property="lbdm"
									labelProperty="lbmc" />
								</html:select>
							</td>
							<th width="16%">
								<font color="red">*</font>��ص�
							</th>
							<td width="34%" >
								<html:text property="hddd"  styleId="hddd"  maxlength="20" styleClass="text_nor"></html:text>
							</td>
						</tr>
						<tr>
							<th width="16%">
								������������
							</th>
							<td width="34%" >
								<html:text property="sqrssx"  styleId="sqrssx"   maxlength="4" onkeyup="checkInputNum(this);" styleClass="text_nor"></html:text>&nbsp;��
							</td>
							<th width="16%">
								�����������
							</th>
							<td width="34%" >
								<html:text property="shrssx"  styleId="shrssx"   maxlength="4" onkeyup="checkInputNum(this);" styleClass="text_nor"></html:text>&nbsp;��
							</td>
							
						</tr>
						<tr>
					   <th align="right">
							����������
						</th>
						<td width="34%">
							<html:text  property="fzrxm" styleId="fzrxm"  maxlength="10" styleClass="text_nor"></html:text>
						</td>
						<th align="right" width="10%">
						           ��ϵ�绰
						</th>
						<td align="left">
							<html:text  property="lxdh" styleId="lxdh" onkeyup="checkInputLxfx(this);" maxlength="20" styleClass="text_nor"></html:text>
						</td>
					</tr>
					<tr>
						<th align="right" width="10%">
						        �а쵥λ
						</th>
						<td align="left" >
							<html:text  property="cbdw" styleId="cbdw"  style="width:97%" maxlength="50" styleClass="text_nor"></html:text>
						</td>
						<th align="right" width="10%">
							����
						</th>
						<td align="left">
							<html:select property="hdggdm"  styleId="hdggdm" style="width:158px;">
								<html:options property="dm" labelProperty="mc" collection="hdgglist"/>
							</html:select>
						</td>
					</tr>
					<tr>
						<th align="right" width="10%">�����</th>
						<td align="left" colspan="3" >
							<html:text  property="hdzt" styleId="hdzt"  style="width:97%" maxlength="100" styleClass="text_nor"></html:text>
						</td>
					</tr>
					<tr>
					<th align="right" width="10%">
							�Ŀ�ļ�����
							<br /><font color="red">(��200��)</font>
						</th>
						<td colspan="3">
							<html:textarea rows="4" property="hdmdyy" styleId="hdmdyy" style="width:97%" onblur="checkLen(this,200);"></html:textarea>
						</td>
					</tr>
					<tr>
					<th align="right" width="10%">
							�����
							<br /><font color="red">(��500��)</font>
						</th>
						<td colspan="3">
							<html:textarea rows="4" property="hdfa" styleId="hdfa" style="width:97%" onblur="checkLen(this,500);"></html:textarea>
						</td>
					</tr>
						<% if("13023".equals(xxdm)){ %>
						<tr>
							<th width="16%">
								����ѧ��
							</th>
							<td width="34%" >
								<html:text property="syxf"  styleId="syxf"  maxlength="5" onkeyup="checkInputNum(this);"></html:text>
							</td>
							<th width="16%">
							</th>
							<td width="34%" >
							</td>
						</tr>
						<% } %>
						<tr>
							<th width="16%">
								�˵��
								<br /><font color="red">(��1000��)</font>
							</th>
							<td width="34%" colspan="3">
								<html:textarea property="hdsm" styleId="hdsm"  style="width:97%;height: 100px;" onblur="checkLen(this,1000);"></html:textarea>
							</td>
						</tr>
						<tr style="display: none;">
							<th  width="20%">
								<font color="red">*</font>�������
							</th>
							<td  width="80%" colspan="3">
								<html:select property="shlc" styleId="shlc" >
								<html:option value=""></html:option>
								<html:options collection="shlcList" property="splc"
									labelProperty="lcxx" />
								</html:select>
							</td>
						</tr>
						<tr style="display: none;">
							<th>
								<html:hidden property="rskzjb" styleId="rskzjb" />
								<font color="red">*</font>�������Ƽ���
							</th>
							<td id="rskzjbTd" colspan="3">
							</td>				
						</tr>
					</tbody>
					</table>
			       </div>
			       <table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"Ϊ������</div>
								<div class="btn">
									<button type="button" type="button" id = "but_save" >
										�� ��
									</button>
									<button type="button" type="button" id= "but_close" onclick="iFClose();">
										�ر�
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
		</html:form>
	</body>
</html>

