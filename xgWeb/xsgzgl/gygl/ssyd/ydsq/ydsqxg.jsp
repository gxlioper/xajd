<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="xsgzgl/gygl/ssyd/js/ydsq.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/browser/js/browser.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript">
			jQuery(function() {
				setTstz();
			});

		</script>
	</head>
	<body>
		<html:form method="post"
		 styleId="form" action="/ydsq">
		<html:hidden property="ssydsqid"/>		
		<html:hidden property="shzt" styleId="shzt"/>	
		<html:hidden property="tzqlddm"/>	
		<html:hidden property="tzqldmc"/>	
		<html:hidden property="tzqqsh"/>
		<html:hidden property="tzqcwh"/>
		<html:hidden property="splcid" styleId="splcid"/>
		<input type="hidden" id="oldSsydlx" value="${ydsqForm.ssydlx}"/>
		<input type="hidden" name="xxdm" id="xxdm" value="${xxdm }"/>
        <input type="hidden" id="xbmc" value="<bean:message key="lable.xb" />"/>
		<div style='tab;width:100%;height:450px;overflow-x:hidden;overflow-y:auto;'>
			<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>ѧ��������Ϣ</span>
							</th>
						</tr>
					</thead>
					<%-- <%@ include file="/xsgzgl/gygl/comm/ssyd/selectStudentSsyd.jsp" %> --%>
					<%@ include file="/xsgzgl/rcsw/rcxwwh/comm/viewStudent.jsp" %>
					<input type="hidden" id="xh" value="${jbxx.xh }"/>
				<logic:notEqual value="10466" name="xxdm">
					<thead>
						<tr>
							<th colspan="4">
								<span>��λ��Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th align="right" width="10%">
								¥������
							</th>
							<td align="left">
								${cwxxData.ldmc}
							</td>
							<th align="right">
								���Һ�
							</th>
							<td align="left">
								${cwxxData.qsh}
							</td>
						</tr>
						<tr>
							<th align="right" width="10%">
								��λ��
							</th>
							<td align="left" id="td_cwh">
								${cwxxData.cwh}
							</td>
							<th align="right">
								���ҵ绰
							</th>
							<td align="left">
								${cwxxData.qsdh}
							</td>
						</tr>
						<tr>
							<th align="right" width="10%">
								�շѱ�׼
							</th>
							<td align="left">
								${cwxxData.sfbz}
							</td>
							<th align="right">
								�����꼶
							</th>
							<td align="left">
								${cwxxData.nj}
							</td>
						</tr>
						<tr>
							<th align="right" width="10%">
								����<bean:message key="lable.xb" />
							</th>
							<td align="left">
								${cwxxData.xymc}
							</td>
							<th align="right">
								�����༶
							</th>
							<td align="left">
								${cwxxData.bjmc}
							</td>
						</tr>
					</tbody>
				</logic:notEqual>
				<logic:equal value="10466" name="xxdm">
					<logic:notEqual value="stu" name="userType">
						<thead>
							<tr>
								<th colspan="4">
									<span>��λ��Ϣ</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th align="right" width="10%">
									¥������
								</th>
								<td align="left">
									${cwxxData.ldmc}
								</td>
								<th align="right">
									���Һ�
								</th>
								<td align="left">
									${cwxxData.qsh}
								</td>
							</tr>
							<tr>
								<th align="right" width="10%">
									��λ��
								</th>
								<td align="left" id="td_cwh">
									${cwxxData.cwh}
								</td>
								<th align="right">
									���ҵ绰
								</th>
								<td align="left">
									${cwxxData.qsdh}
								</td>
							</tr>
							<tr>
								<th align="right" width="10%">
									�շѱ�׼
								</th>
								<td align="left">
									${cwxxData.sfbz}
								</td>
								<th align="right">
									�����꼶
								</th>
								<td align="left">
									${cwxxData.nj}
								</td>
							</tr>
							<tr>
								<th align="right" width="10%">
									����<bean:message key="lable.xb" />
								</th>
								<td align="left">
									${cwxxData.xymc}
								</td>
								<th align="right">
									�����༶
								</th>
								<td align="left">
									${cwxxData.bjmc}
								</td>
							</tr>
						</tbody>
					</logic:notEqual>				
				</logic:equal>
				<thead>
						<tr>
							<th colspan="4">
								<span>�����춯����</span>
							</th>
						</tr>
				</thead>
				<tbody>
					<tr>
						<th align="right" width="10%">
							<span class="red">*</span>�����춯����
						</th>
						<td align="left">
							<html:select property="ssydlx" styleId="ssydlx" disabled="false" >
								<logic:notEqual value="10466" name="xxdm">
										<html:option value="00">����</html:option>
										<html:option value="01">�������</html:option>
								</logic:notEqual>
								<logic:equal value="10466" name="xxdm">
									<logic:notEqual value="stu" name="userType">
										<html:option value="00">����</html:option>
										<html:option value="01">�������</html:option>
									</logic:notEqual>
								</logic:equal>
								<logic:equal name="xxdm" value="12686">
									<html:option value="02">ʵϰ����</html:option>
								</logic:equal>
									<html:option value="03">��ס</html:option>
							</html:select>
						</td>
						<th align="right">
							<span class="red">*</span>ѧ��/ѧ��
						</th>
						<td align="left">
							<logic:equal value="12303" name = "xxdm">
										${data.xn} ${data.xqmc}
									<html:hidden property="xn" styleId="xn" />	  
									<html:hidden property="xq" styleId="xq"  style="width:30px;"/>
							</logic:equal>
							<logic:notEqual value="12303" name = "xxdm">
							<html:select property="xn" styleId="xn" disabled="false" >
									<html:options collection="xnList" property="xn"
										labelProperty="xn" />
							</html:select>
							<html:select property="xq" styleId="xq" disabled="false" >
									<html:options collection="xqList" property="xqdm"
										labelProperty="xqmc" />
							</html:select>
							</logic:notEqual>
						</td>
					</tr>
				<tbody id="rz">
					<tr >
						<th align="right" width="10%">
							<span class="red">*</span>��סԭ��
						</th>
						<td align="left">
							<html:select property="rzssyy" styleId="rzssyy" disabled="false" >
									<html:options collection="rzyyList" property="rzyydm"
										labelProperty="rzyymc" />
							</html:select>
						</td>
						<th align="right">
							<span class="red">*</span>��סʱ��
						</th>
						<td align="left">
							<html:text property="rzsssj" styleId="rzsssj" onfocus="return showCalendar(this.id,'yyyy-MM-dd',true);" style="width:100px;"/>
						</td>
					</tr>
					<tr style="<logic:notEqual name="xxdm" value="10466"><logic:equal name="userType" value="stu">display: none;</logic:equal></logic:notEqual>">
						<th>ѡ��λ</th>
						<td align="left" width="10%" colspan="3">
							<button class="btn_01" type="button"  onclick="selectRzcw();return false;">ѡ��λ</button>
						</td>
					</tr>
					<tr id="yxzrzcwxx" style="<logic:equal name="userType" value="stu">display: none;</logic:equal>">
						<th>
							��ѡ��λ��Ϣ
						</th>
						<td colspan="3">
							<table id="rzcwxxTable"></table>
							
							<html:hidden property="rzcwxx" styleId="rzcwxx" />
						</td>
					</tr>
				</tbody>
				<tbody id="ts">
					<tr>
						<th align="right" width="10%">
							<span class="red">*</span>����ԭ��
						</th>
						<td align="left">
							<html:select property="tstzyy" styleId="tstzyy" disabled="false">
									<html:options collection="tstzyyList" property="tsyydm"
										labelProperty="tsyymc" />
							</html:select>
						</td>
						<th align="right">
							<span class="red">*</span>����ʱ��
						</th>
						<td align="left">
							<html:text property="tstzsj" styleId="tstzsj" onfocus="return showCalendar(this.id,'yyyy-MM-dd',true);" style="width:100px"/>
						</td>
					</tr>
				</tbody>
				<tbody id="tz">
					<tr >
						<th align="right" width="10%">
							<span class="red">*</span>�������ԭ��
						</th>
						<td align="left">
							<html:select property="tzssyy" styleId="tzssyy" disabled="false" >
									<html:options collection="tzyyList" property="tzyydm"
										labelProperty="tzyymc" />
							</html:select>
						</td>
						<th align="right">
							<span class="red">*</span><logic:equal value="11647" name="xxdm">����ʱ��</logic:equal><logic:notEqual value="11647" name="xxdm">�������ʱ��</logic:notEqual>
						</th>
						<td align="left">
							<html:text property="tzsssj" styleId="tzsssj" onfocus="return showCalendar(this.id,'yyyy-MM-dd',true);" style="width:100px"/>
						</td>
					</tr>
					<tr style="<logic:equal name="userType" value="stu">display: none;</logic:equal>">
						<th>ѡ��λ</th>
						<td align="left" width="10%" colspan="3">
							<button class="btn_01" type="button"  onclick="selectCw();return false;">ѡ��λ</button>
						</td>
					</tr>
					<tr id="yxzcwxx" style="<logic:equal name="userType" value="stu">display: none;</logic:equal>">
						<th>
							��ѡ��λ��Ϣ
						</th>
						<td colspan="3">
							<table id="cwxxTable"></table>
							
							<html:hidden property="cwxx" styleId="cwxx" />	
						</td>
					</tr>
				</tbody>
						<tr>
							<th align="right" style="width: 10%">
								������Ϣ
							</th>
							<td colspan="3">
								<html:hidden property="fjxx" styleId="fjxx"/>
								<%@ include file="/xsgzgl/comm/fileUpload/f.jsp"%>
								<script type="text/javascript">
									//���ø��� 
									jQuery(function(){
										jQuery.MultiUploader({
											maxcount : 3,
											//��׺
											accept : 'doc|docx|xls|xlsx|jpg|png|rar|zip|pdf|ppt',
											//����ļ���С ��λM
											maxsize: 10,
											//��Ÿ������������id
											elementid : 'fjxx'
											});
									});
								</script>  
							</td>
						</tr>
					<tr>
						<logic:notEqual value="12303" name = "xxdm">
							<th align="right" width="10%">
								��ע
							</th>
						</logic:notEqual>
						<logic:equal value="12303" name = "xxdm">
							<th align="right" width="10%">
								<span class="red">*</span>��������
							</th>
						</logic:equal>
						<td colspan="3">
							<html:textarea rows="4" property="bz" styleId="bz" style="width:97%" onblur="checkLen(this,500);"></html:textarea>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		<div>
			<table width="100%" border="0" class="formlist">
					<tfoot>
					<tr>
						<td colspan="4">
							<div class="bz">
								"<span class="red">*</span>"Ϊ������
							</div>
							<div class="btn">
								<button type="button" id="tssave"  onclick="save('ydsq.do?method=update&type=save','xh-tstzsj');return false;" id="buttonSave">
									����ݸ�
								</button>
								<button type="button" id="tzsave" onclick="save('ydsq.do?method=update&type=save','xh-tzsssj');return false;" id="buttonSave">
									����ݸ�
								</button>
								<button type="button" id="rzsave" onclick="save('ydsq.do?method=update&type=save','xh-xn-xq-rzssyy-rzsssj');return false;" id="buttonSave">
									����ݸ�
								</button>
								<button type="button" id="tssub"  onclick="save('ydsq.do?method=update&type=submit','xh-tstzsj');return false;" id="buttonSave">
									�ύ����
								</button>
								<button type="button" id="tzsub" onclick="save('ydsq.do?method=update&type=submit','xh-tzsssj');return false;" id="buttonSave">
									�ύ����
								</button>
								<button type="button" id="rzsub" onclick="save('ydsq.do?method=update&type=submit','xh-xn-xq-rzssyy-rzsssj');return false;" id="buttonSave">
									�ύ����
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
