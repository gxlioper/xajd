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
				jQuery("#yxzcwxx").hide();
				jQuery("#yxzrzcwxx").hide();
				var zsfxs = jQuery("#zsfxs").val();
				if("0" == zsfxs) {
					jQuery("#zsfM").hide();
					jQuery("#zsf").hide();
					jQuery("#sfcwcshs").attr("colspan","3");
				}
				setTstz();
			});

			//ѡ��λ
			function selectCw(){
				var xh = jQuery("#xh").val();
				if(xh===''){
					showAlert('����ѡ��һ��ѧ����');
				}else{
					showDialog('��ѡ��һ����λ',800,500,'ydsq.do?method=selectCwxx&goto=${path}&xh='+xh);
				}
			}
			function setSfcwcsh(cellValue,rowObject){
				if(cellValue!=null&&cellValue!=""){
					jQuery("input:radio[name='sfcwcsh']:eq(1)").attr("checked","checked");
					jQuery("input:radio[name='sfcwcsh']").attr("disabled","disabled");
				}else{
					jQuery("input:radio[name='sfcwcsh']:eq(0)").attr("checked","checked");
					jQuery("input:radio[name='sfcwcsh']").removeAttr("disabled");
				}
				return cellValue;
			}
			function showCwxx(cwxx){
				var gridSetting = {
						caption:"��ѡ��λ��Ϣ",
						multiselect:false,
						rowNum:1,
						url:"ydsq.do?method=selectCwxx&type=query&cwxx="+cwxx,
						colList:[
						   {label:'��λ��Ϣid',name:'cwxx', index: 'cwxx',key:true,hidden:true},
						   {label:'¥������',name:'ldmc', index: 'ldmc'},
						   {label:'���Һ�',name:'qsh', index: 'qsh',width:'6%'},
						   {label:'��λ��',name:'cwh', index: 'cwh',width:'6%'},
						   {label:'��λ�Ա�',name:'qsxb', index: 'qsxb'},
						   {label:'�����꼶',name:'nj', index: 'nj'},
						   {label:'����<bean:message key="lable.xb" />',name:'xymc', index: 'xymc'},
						   {label:'ѧ��',name:'xh', index: 'xh',formatter:setSfcwcsh},
						   {label:'����',name:'xm', index: 'xm'}
						],
						sortname: "sfrz",
					 	sortorder: "desc"
					}
				jQuery("#cwxxTable").initGrid(gridSetting);
				jQuery("#yxzcwxx").show();
				jQuery("#cwxx").val(cwxx);
			}
		</script>
	</head>
	<body>
		<html:form method="post" styleId="form" action="/ydjg">
		<input type="hidden" name="xxdm" id="xxdm" value="${xxdm }"/>
		<div style='tab;width:100%;height:450px;overflow-x:hidden;overflow-y:auto;'>
		<html:hidden property="zsfxs" styleId="zsfxs" value="${zsfxs}"/>
			<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>ѧ��������Ϣ</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/gygl/comm/ssyd/selectStudentSsyd.jsp" %>
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
									<html:option value="00">����</html:option>
									<html:option value="01">�������</html:option>
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
									${dqxn} ${dqxq}
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
							<logic:equal value="12303" name = "xxdm">
								<html:text property="rzsssj" styleId="rzsssj" value ="${currDate}" disabled = "flase" onfocus="return showCalendar(this.id,'yyyy-MM-dd',true);" style="width:100px;"/>
							</logic:equal>
							<logic:notEqual value="12303" name = "xxdm">
								<html:text property="rzsssj" styleId="rzsssj"  onfocus="return showCalendar(this.id,'yyyy-MM-dd',true);" style="width:100px;"/>
							</logic:notEqual>
						</td>
					</tr>
					<tr>
						<th>ѡ��λ</th>
						<td align="left" width="10%" colspan="3">
							<button class="btn_01" type="button"  onclick="selectRzcw();return false;" <logic:equal name="userType" value="stu">disabled="disabled"</logic:equal>>ѡ��λ</button>
						</td>
					</tr>
					<tr id="yxzrzcwxx">
						<th>
							��ѡ��λ��Ϣ
						</th>
						<td colspan="3">
							<table id="rzcwxxTable"></table>
							<input type="hidden" name="rzcwxx" id="rzcwxx" />
						</td>
					</tr>
				</tbody>
				<tbody id="ts">
					<tr>
						<th align="right" width="10%">
							<span class="red">*</span>����ԭ��
						</th>
						<td align="left">
							<html:select property="tstzyy" styleId="tstzyy" disabled="false" >
									<html:options collection="tstzyyList" property="tsyydm"
										labelProperty="tsyymc" />
							</html:select>
						</td>
						<th align="right">
							<span class="red">*</span>����ʱ��
						</th>
						<td align="left">
							<logic:equal value="12303" name = "xxdm">
								<html:text property="tstzsj" styleId="tstzsj" value ="${currDate}" disabled = "flase" onfocus="return showCalendar(this.id,'yyyy-MM-dd',true);" style="width:100px;"/>
							</logic:equal>
							<logic:notEqual value="12303" name = "xxdm">
								<html:text property="tstzsj" styleId="tstzsj"  onfocus="return showCalendar(this.id,'yyyy-MM-dd',true);" style="width:100px;"/>
							</logic:notEqual>
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
							<span class="red">*</span>�������ʱ��
						</th>
						<td align="left">
							<logic:equal value="12303" name = "xxdm">
									<html:text property="tzsssj" styleId="tzsssj" value ="${currDate}" disabled = "flase" onfocus="return showCalendar(this.id,'yyyy-MM-dd',true);" style="width:100px;"/>
								</logic:equal>
								<logic:notEqual value="12303" name = "xxdm">
									<html:text property="tzsssj" styleId="tzsssj"  onfocus="return showCalendar(this.id,'yyyy-MM-dd',true);" style="width:100px;"/>
							</logic:notEqual>
							
						</td>
					</tr>
					<tr>
						<th><span class="red">*</span>ѡ��λ</th>
						<td align="left" width="10%" colspan="3">
							<button class="btn_01" type="button"  onclick="selectCw();return false;">ѡ��λ</button>
						</td>
					</tr>
					<tr id="yxzcwxx">
						<th>
							��ѡ��λ��Ϣ
						</th>
						<td colspan="3">
							<table id="cwxxTable"></table>
							<input type="hidden" name="cwxx" id="cwxx" />
						</td>
					</tr>
				</tbody>
					<tr id="showSfcwcsh">
						<th >
							<font color="red">*</font>ԭ��λ�Ƿ��ʼ��
						</th>
						<td id="sfcwcshs">
							<input type="radio" name="sfcwcsh" value="0">��
					    	<input type="radio" name="sfcwcsh" value="1" checked>��
						</td>
						
						<th align="right" width="10%" id="zsf">
							ס�޷�
						</th>
						<td align="left" id="zsfM">
							<html:select property="jflx" styleId="jflx" >
								<html:option value=""></html:option>
								<html:option value="01">����</html:option>
								<html:option value="02">�˻�</html:option>
							</html:select>
							<html:text size="15" property="zsfje" styleId="zsfje" maxlength="6" onkeyup="checkInputData(this);"></html:text> Ԫ
						</td>
					</tr>
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
								<button type="button" id="tssave"  onclick="save('ydjg.do?method=add&type=save','xh-xn-xq-tstzyy-tstzsj');return false;" id="buttonSave">
									�� ��
								</button>
								<button type="button" id="tzsave" onclick="save('ydjg.do?method=add&type=save','xh-xn-xq-tzssyy-tzsssj-cwxx');return false;" id="buttonSave">
									�� ��
								</button>
								<button type="button" id="rzsave" onclick="save('ydjg.do?method=add&type=save','xh-xn-xq-rzssyy-rzsssj');return false;" id="buttonSave">
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
