<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<%@ include file="/syscommon/autocomplete.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/xthd/xmjg/js/xmjg.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/browser/js/browser.js"></script>
		<script type="text/javascript">
			jQuery(function($) {
				$("#formfile").bind("change",function(){
					jQuery("#filepath").val("update");
				});
			});

<%--			��Ŀ���ƹ�����ѧ��ѧ���йأ���ʱ�ر�  --%>
<%--			jQuery(function(){																								--%>
<%--				//ȡ�����ݱ�xg_pjpy_new_pjxmb; �ֶΣ�xmmc																							--%>
<%--				var autoSetting = {--%>
<%--					dataTable:"xg_rcsw_txhd_xmwh",--%>
<%--					dataField:"xmmc",--%>
<%--					dataFieldKey:"lbdm",--%>
<%--					dataFieldKeyId:"lbdm",--%>
<%--					scrollHeight:135										--%>
<%--				}--%>
<%--				// ģ��������������Ŀ���ơ�--%>
<%--				jQuery("#xmmc").setAutocomplete(autoSetting);--%>
<%--			});--%>
		</script>
	</head>
	<body>
		<html:form method="post" styleId="form" action="/rcsw_txhd_xmjg"  enctype="multipart/form-data">
		<% String xxdm = (String) request.getAttribute("xxdm"); %>
		 <html:hidden property="guid"/>
		<div style='tab;width:100%;height:455px;overflow-x:hidden;overflow-y:auto;'>
			<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>ѧ��������Ϣ</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/rcsw/xthd/comm/viewStudent.jsp"%>
				<thead>
						<tr>
							<th colspan="4">
								<span>���Ϣ</span>
							</th>
						</tr>
				</thead>
				<tbody>
					<tr>
						<th align="right" width="10%">
							<span class="red">*</span>ѧ��
						</th>
						<td align="left">
							<html:select property="xn" styleId="xn" disabled="false" 
									style="width:125px;">
									<html:options collection="xnList" property="xn"
										labelProperty="xn" />
							</html:select>
						</td>
						<th align="right">
							<span class="red">*</span>ѧ��
						</th>
						<td align="left">
							<html:select property="xq" styleId="xq" disabled="false" 
									style="width:125px;">
									<html:options collection="xqList" property="xqdm"
										labelProperty="xqmc" />
							</html:select>
						</td>
					</tr>
					<tr>
						<th><span class="red">*</span>�����</th>
						<td >
							<html:text  property="xmmc" styleId="xmmc"   style="width:180px;" maxlength="20"  styleClass="text_nor"></html:text>
						</td>
						
						<th width="16%">
							<font color="red">*</font>�ʱ��
						</th>
						<td width="34%" >
							<html:text property="hdkssj" styleId="hdkssj"  style="width: 80px;"  onclick="return showCalendar('hdkssj','yyyy-MM-dd');" readonly="true" ></html:text>��
							<html:text property="hdjssj" styleId="hdjssj"  style="width: 80px;"  onclick="return showCalendar('hdjssj','yyyy-MM-dd');" readonly="true" ></html:text>
						</td>
					</tr>
					<tr>
					    <th align="right">
							<span class="red">*</span>����
						</th>
						<td width="34%">
							<html:select property="lbdm" styleId="lbdm" style="width:155px">
							<html:options collection="hdlbList" property="lbdm"
								labelProperty="lbmc" />
							</html:select>
						</td>
						<th align="right" width="10%">
						<span class="red">*</span>��ص�
						</th>
						<td align="left">
							<html:text  property="hddd" styleId="hddd"  style="width:120px;" styleClass="text_nor"></html:text>
						</td>
					</tr>
					<tr>
					   <th align="right">
							����������
						</th>
						<td width="34%">
							<html:text  property="fzrxm" styleId="fzrxm"  style="width:120px;" styleClass="text_nor"></html:text>
						</td>
						<th align="right" width="10%">
						         ��ϵ�绰
						</th>
						<td align="left">
							<html:text  property="lxdh" styleId="lxdh" onkeyup="value=value.replace(/[^\d]/g,'')"   style="width:120px;" styleClass="text_nor"></html:text>
						</td>
					</tr>
					<tr>
						<th align="right" width="10%">
						        �а쵥λ
						</th>
						<td align="left" colspan="3" >
							<html:text  property="cbdw" styleId="cbdw"  style="width:97%" styleClass="text_nor"></html:text>
						</td>
					</tr>
					<tr>
						<th align="right" width="10%">�����</th>
						<td align="left" colspan="3" >
							<html:text  property="hdzt" styleId="hdzt"  style="width:97%" styleClass="text_nor"></html:text>
						</td>
					</tr>
					<tr>
					<th align="right" width="10%">
							�Ŀ�ļ�����
						</th>
						<td colspan="3">
							<html:textarea rows="2" property="hdmdyy" styleId="hdmdyy" style="width:97%" onblur="checkLen(this,200);"></html:textarea>
						</td>
					</tr>
					<tr>
					<th align="right" width="10%">
							�����
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
						<th align="right" width="10%">�˵��</th>
						<td align="left" colspan="3" >
							<html:text  property="hdsm" styleId="hdsm"  style="width:97%" styleClass="text_nor"></html:text>
						</td>
					</tr>
					<tr>
						<th align="right" width="10%">
							<span class="red">*</span>��������&nbsp;
							<br />
							<font color="red">(��200��)</font>	
						</th>
						<td colspan="3">
							<html:textarea rows="4" property="sqly" styleId="sqly" style="width:97%" onblur="checkLen(this,200);"></html:textarea>
						</td>
					</tr>
				</tbody>
				<logic:equal value="12309" name="xxdm">
				<thead>
						<tr>
							<th colspan="4">
								<span>��������</span>
							</th>
						</tr>
				</thead>
				<tbody>
				<tr>
				<th align="right" width="10%">��������</th>
						<td align="left" colspan="3" >
							<html:text  property="sqwz" styleId="sqwz"  style="width:97%" styleClass="text_nor"></html:text>
						</td>
				</tr>
					<tr>
					   <th align="right">
							ʹ��ʱ��
						</th>
						<td width="34%">
							<html:text property="wzsysj" styleId="wzsysj"  onclick="return showCalendar('wzsysj','yyyy-MM-dd HH:mm');" readonly="true" ></html:text>
						</td>
						<th align="right" width="10%">
						          �黹ʱ��
						</th>
						<td align="left">
							<html:text  property="wzghsj" styleId="wzghsj"  onclick="return showCalendar('wzghsj','yyyy-MM-dd HH:mm');" readonly="true" ></html:text>
						</td>
					</tr>
				</tbody>
				<thead>
						<tr>
							<th colspan="4">
								<span>��������</span>
							</th>
						</tr>
				</thead>
				<tbody>
				<tr>
				<th align="right" width="10%">������ʽ</th>
						<td align="left">
							<html:text  property="xcfs" styleId="xcfs" style="width:180px" styleClass="text_nor"></html:text>
						</td>
				<th align="right" width="10%">���������ҵص㣩</th>
						<td align="left" colspan="3" >
							<html:text  property="xgdd" styleId="xgdd" style="width:180px" styleClass="text_nor"></html:text>
						</td>
				</tr>
					<tr>
					<th align="right" width="10%">��ֹʱ��</th>
					<td>
					   <html:text property="xckssj" styleId="xckssj"  style="width: 100px;"  onclick="return showCalendar('xckssj','yyyy-MM-dd HH');" readonly="true" ></html:text>��
							<html:text property="xcjssj" styleId="xcjssj"  style="width: 100px;"  onclick="return showCalendar('xcjssj','yyyy-MM-dd HH');" readonly="true" ></html:text>
					</td>
					<th align="right" width="10%">���������������</th>
					<td>
					   <html:text property="hbsl" maxlength="3" onkeyup="value=value.replace(/[^\d]/g,'')"   styleId="hbsl" styleClass="text_nor"></html:text>
						
					</td>
					</tr>
					<tr>
					<th align="right" width="10%">��������</th>
					<td colspan="3">
					   <html:text property="xcnr" styleId="xcnr" style="width:97%" styleClass="text_nor"></html:text>
					</td>
					</tr>
				</tbody>
				</logic:equal>
				</table>
		</div>
		<div>
			<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
					<tr>
						<td colspan="4">
							<div class="bz">
								"<span class="red">*</span>"Ϊ������
							</div>
							<div class="btn">
								<button type="button"  onclick="save('rcsw_txhd_xmjg.do?method=update&type=save','hdkssj-hdjssj-hddd-xmmc-sqly','false');return false;" id="buttonSave">
									�� ��
								</button>
								<button type="button"  onclick="iFClose();" id="buttonClose">
									�� ��
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
