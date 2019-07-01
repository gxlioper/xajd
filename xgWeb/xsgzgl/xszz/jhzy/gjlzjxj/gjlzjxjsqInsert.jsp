<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�ΰ����� -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	    <script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" defer="defer">
		function onShow(){ 

			var isKns = jQuery("#isKns").val();
			var xh = jQuery("#xh").val();
			var shzt = jQuery("#shzt").val();
	
			
			if(xh!="" && isKns=="false"){
				alertError("����Ϊ��ѧ��<font color='blue'>������</font>��ѧ�����ſ��Խ��й�����־��ѧ�����룬����ȷ��^_^||");
				jQuery("#buttonSave").attr("disabled","disabled");
				return false;
			}else if(xh!="" && (shzt == "shz" || shzt == 'tg' || shzt == 'btg')){
				alertError("��ѧ���������¼�Ѿ�������˽׶Σ��޷��ظ����룬�����رձ�ҳ�棬���<font color='blue'>���̸���</font>���в鿴Ŀǰ�Ľ�չ^_^||");
				jQuery("#buttonSave").attr("disabled","disabled");
				return false;
			}		
		}
		
		//�����������϶�
		function saveKnsrdSq(){
			var array = jQuery(".bcClass");
			var flag = true;
			jQuery(array).each(function (i,n) {
				if (jQuery(n).val()=="" || jQuery(n).val()==null) {
					flag = false;
					alertError("��*���ֶα�����д��");
					return false;	
				}
			});
			
			var bxkms = jQuery('#bxkms').val();
			var jgms = jQuery('#jgms').val();
			if (parseInt(bxkms) < parseInt(jgms)) {
				alertError("���޿�������д���󣬲���С�ڼ���������");
				flag = false;
				return false;	
			}
			
			if (flag) {
				refreshForm('jhzyGjlzjxj.do?method=gjlzjxjsqInsert&act=save');
			}
		}
		
		jQuery(function(){
			onShow();
		})
		</script>
	</head>
	<body  >
		<html:form action="/jhzyGjlzjxj" method="post">
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" id="url" name="url" value="jhzyGjlzjxj.do?method=gjlzjxjsqInsert"/>
			<input type="hidden" id="lx" name="lx" value="ѧ��"/>

			

			<div style="width:100%;height:630px;overflow-x:hidden;overflow-y:auto;">
			<table class="formlist" border="0" align="center" style="width: 100%">
				<tr style="height: 23px">
					<td align="center" colspan="4">
						<font size="5">
							${zzxn }ѧ�������־��ѧ������
						</font>
					</td>
				</tr>
			</table>
				<input type="hidden" name="isKns" id="isKns" value="${isKns }"/>
				<input type="hidden" name="shzt" id="shzt" value="${rs.shzt }"/>
				<table width="100%" border="0" class="formlist">
					<!-- ѧ��������Ϣ begin-->
					<thead>
						<tr>
							<th colspan="4">
								<span>ѧ��������Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr >
							<th align="right" width="20%">
								<font color="red">*</font>ѧ��
							</th>
							<td align="left" width="30%">
								<logic:equal name="userType" value="stu">
									<input type="hidden" id="xh" name="xh" value="${xh }"/>
									${xh }
								</logic:equal>
								<logic:notEqual name="userType" value="stu">
									<input type="text" id="xh" name="xh" class="bcClass" readonly="readonly" value="${rs.xh }"/>
									<button type="button" onclick="sendXx();return false;"
										class="btn_01" id="buttonFindStu">
										ѡ��
									</button>
								</logic:notEqual>	
							</td>
							<th align="right" width="20%">
								����
							</th>
							<td align="left" width="30%">
								${rs.xm }
							</td>
						</tr>
						<tr >
							<th align="right" width="">
								�Ա�
							</th>
							<td align="left" width="">
								${rs.xb }
							</td>
							<th align="right" width="">
								ѧ��
							</th>
							<td align="left" width="">
								${rs.xz }
							</td>
						</tr>
						<tr >
							<th align="right" width="">
								�꼶
							</th>
							<td align="left" width="">
								${rs.nj }
							</td>
							<th align="right" width="">
								<bean:message key="lable.xb" />
							</th>
							<td align="left" width="">
								${rs.xymc }
							</td>
						</tr>
						<tr >
							<th align="right" width="">
								רҵ
							</th>
							<td align="left" width="">
								${rs.zymc }
							</td>
							<th align="right" width="">
								�༶
							</th>
							<td align="left" width="">
								${rs.bjmc}
							</td>
						</tr>
						<tr >
							<th align="right" width="">
								����
							</th>
							<td align="left" width="">
								${rs.mzmc }
							</td>
							<th align="right" width="">
								������ò
							</th>
							<td align="left" width="">
								${rs.zzmmmc }
							</td>
						</tr>
						<tr >
							<th align="right" width="">
								���֤��
							</th>
							<td align="left" width="">
								${rs.sfzh }
							</td>
							<th align="right" width="">
								��������
							</th>
							<td align="left" width="">
								${rs.csrq }
							</td>
						</tr>
					</tbody>
					<!-- ѧ��������Ϣend -->
					
					<!-- ��������Ϣ begin-->
					<thead>
						<tr>
							<th colspan="4">
								<span>ѧϰ���</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
						<th align="right" width="20%">
								<font color="red">*</font>�ɼ�����<br/>������/��������
							</th>
							<td align="left" width="30%" >
								<html:text property="cjpm" styleClass="bcClass" styleId="cjpm" maxlength="4" value="${rs.cjpm}" style="width:55px" onkeyup="checkInputData(this)"></html:text>
								/${bjzrs }

							</td>
							<th align="right" width="20%">
								<font color="red">*</font>�������
							</th>
							<td align="left" width="30%" >
								���޿�&nbsp;&nbsp;&nbsp;<html:text property="bxkms" styleClass="bcClass" value="${rs.bxkms}" styleId="bxkms" maxlength="2" style="width:50px" onkeyup="checkInputData(this)"></html:text>���ţ�
								<br/>��������<html:text property="jgms" styleClass="bcClass" styleId="jgms" value="${rs.jgms}" maxlength="2" style="width:50px" onkeyup="checkInputData(this)"></html:text>���ţ�
							</td>
							
						</tr>
						<tr>
							<th align="right" width="20%">
								<font color="red">*</font>ʵ���ۺϿ�������
							</th>
							<td align="left" width="30%" >
								<input type="radio" name="sxzhkppm" id="sxzhkppm" class="bcClass" value="��" <logic:equal value="��" name="rs" property="sxzhkppm" >checked</logic:equal>/>��
								<input type="radio" name="sxzhkppm" id="sxzhkppm" class="bcClass" value="��" <logic:equal value="��" name="rs" property="sxzhkppm" >checked</logic:equal>/>��
							</td>
							<th align="right" width="20%">
								<font color="red">*</font>���ǣ�����<br/>������/��������
							</th>
							<td align="left" width="30%" >
								<html:text property="zhkppm" styleId="zhkppm" maxlength="4"  value="${rs.zhkppm}"
								styleClass="bcClass" style="width:55px" onkeyup="checkInputData(this)"></html:text>/${bjzrs }
				
							</td>
						</tr>
						</tbody>
						</table>
					
						<table width="100%" border="0" class="formlist">
						<!-- ѧ��������Ϣ begin-->
						<thead>
							<tr>
								<th colspan="3">
									<span>��ѧ�ڼ���Ҫ�����</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
							<th style="width:20%">
								<div align="center"><font color="red">*</font>������</div>
							</th>
							<th style="width:40%">
								<div align="center"><font color="red">*</font>������</div> 
							</th>
							<th style="width:40%">
								<div align="center"><font color="red">*</font>�佱��λ</div>
							</th>
							</tr>
							<tr>
								<td align="center">
									<html:text property="hjsj1" styleId="hjsj1" onclick="return showCalendar('hjsj1','y-mm-dd');" 
							onblur="dateFormatChg(this)" style="cursor:hand;width:90px" value="${rs.hjsj1}" styleClass="bcClass"></html:text>
								</td>
								<td align="center">
									<html:text property="hjmc1" styleId="hjmc1" style="width:200px" value="${rs.hjmc1}" styleClass="bcClass" maxlength="30"></html:text>
								</td>
								<td align="center">
									<html:text property="bjdw1" styleId="bjdw1" style="width:200px" value="${rs.bjdw1}" styleClass="bcClass" maxlength="30"></html:text>
								</td>
							</tr>
							<tr>
								<td align="center">
									<html:text property="hjsj2" styleId="hjsj2"  onclick="return showCalendar('hjsj2','y-mm-dd');" 
							onblur="dateFormatChg(this)" style="cursor:hand;width:90px" value="${rs.hjsj2}" styleClass="bcClass"></html:text>
								</td>
								<td align="center">
									<html:text property="hjmc2" styleId="hjmc2" style="width:200px" value="${rs.hjmc2}" styleClass="bcClass" maxlength="30"></html:text>
								</td>
								<td align="center">
									<html:text property="bjdw2" styleId="bjdw2" style="width:200px" value="${rs.bjdw2 }" styleClass="bcClass" maxlength="30"></html:text>
								</td>
							</tr>
							<tr>
								<td align="center">
									<html:text property="hjsj3" styleId="hjsj3"  onclick="return showCalendar('hjsj3','y-mm-dd');" 
							onblur="dateFormatChg(this)" style="cursor:hand;width:90px" styleClass="bcClass" value="${rs.hjsj3}"></html:text>
								</td>
								<td align="center">
									<html:text property="hjmc3" styleId="hjmc3" style="width:200px" styleClass="bcClass" maxlength="30" value="${rs.hjmc3}"></html:text>
								</td>
								<td align="center">
									<html:text property="bjdw3" styleId="bjdw3" style="width:200px" styleClass="bcClass" maxlength="30" value="${rs.bjdw3 }"></html:text>
								</td>
							</tr>
							<tr>
								<td align="center">
									<html:text property="hjsj4" styleId="hjsj4"  onclick="return showCalendar('hjsj4','y-mm-dd');" 
							onblur="dateFormatChg(this)" style="cursor:hand;width:90px" styleClass="bcClass" value="${rs.hjsj4}"></html:text>
								</td>
								<td align="center">
									<html:text property="hjmc4" styleId="hjmc4" style="width:200px" styleClass="bcClass" maxlength="30" value="${rs.hjmc4}"></html:text>
								</td>
								<td align="center">
									<html:text property="bjdw4" styleId="bjdw4" value="${rs.bjdw4}" style="width:200px" styleClass="bcClass" maxlength="30"></html:text>
								</td>
							</tr>
						</tbody>
						</table>
						
						<table width="100%" border="0" class="formlist">
						<tbody>
						<tr>
							<th align="right" width="20%" >
								<font color="red">*</font>��������
								<br/><font color="red">(��������200)</font>
							</th>
							<td align="left" width="" colspan="3">
								<textarea rows="3" id="sqly" cols="" name="sqly" class="bcClass"
									onblur="chLeng(this,200);"
									style="word-break:break-all;width:99%" >${rs.sqly }</textarea>
							</td>
						</tr>
						
					</tbody>
				
					<!-- ��������Ϣ end-->			
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"Ϊ������</div>
								<div class="btn">
									
									<button type="button" name="����" onclick="saveKnsrdSq();return false;" id="buttonSave">
										�� ��
									</button>
								
									<button type="button" name="�ر�" onclick="Close();return false;" id="buttonClose">�� ��</button>					           
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>