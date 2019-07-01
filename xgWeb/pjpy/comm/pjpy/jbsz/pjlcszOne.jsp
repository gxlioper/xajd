<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�ΰ����� -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="js/comm/commFunction.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script language="javascript">
			//������������
			function saveLcsz(){
				var data = $('data').value;
				var data1 = $('data1').value;
				var jssj = $('jssj').value;
				var kssj = $('kssj').value;

				if(data != ""){
					$('kssj').value = data + $('hour').value + $('minute').value + $('second').value;
				}

				if(data1 != ""){
					$('jssj').value = data1 + $('hour1').value + $('minute1').value + $('second1').value;
				}

				if(jssj != "" && kssj != "" && jssj.length == 14 && kssj.length == 14){
					if(eval(jssj) < eval(kssj)){
						alert('��ʼʱ�����ʱ���Ⱥ�˳����ȷ��');
						return;
					}
				}
				
				if((kssj == "" && jssj == "") || (kssj != "" && jssj != "")){
					saveUpdate("/xgxt/pjpyLcsz.do?method=pjlcszOne&doType=save","lcmc-lcdj");
				} else {
					alert('��ʼʱ������ʱ��δ����������');
					return;
				}
			}

			function validateTime(obj){
				if(obj.value.length == 1){
					obj.value = "0" + obj.value;
				}

				if(obj.value == ""){
					obj.value = "00";
				}
			}
			
			function checkHour(obj){
				var hour = eval(obj.value);
				if(hour>23){
					obj.value = 23;
				}

				validateTime(obj);
			}

			function checkMinute(obj){
				var minute = eval(obj.value);
				if(minute>59){
					obj.value = 59;
				}

				validateTime(obj);
			}

			function checkSecond(obj){
				var second = eval(obj.value);
				if(second>59){
					obj.value = 59;
				} 

				validateTime(obj);
			}

			function initTime(data,hour,minute,second){
				if($(data).value != ""){
					$(hour).value = "00";
					$(minute).value = "00";
					$(second).value = "00";
	
					$(hour).disabled = "";
					$(minute).disabled = "";
					$(second).disabled = "";
				}else {
					$(hour).value = "";
					$(minute).value = "";
					$(second).value = "";
	
					$(hour).disabled = "disabled";
					$(minute).disabled = "disabled";
					$(second).disabled = "disabled";
				}
			}

			function revealTime(){
				var kssj = $('kssj').value;
				if(kssj != "" && kssj.length == 14){
					$('data').value = kssj.substring(0,8);
					$('hour').value = kssj.substring(8,10);
					$('minute').value = kssj.substring(10,12);
					$('second').value = kssj.substring(12,14);

					$('hour').disabled = "";
					$('minute').disabled = "";
					$('second').disabled = "";
				}

				var jssj = $('jssj').value;
				if(jssj != "" && jssj.length == 14){
					$('data1').value = jssj.substring(0,8);
					$('hour1').value = jssj.substring(8,10);
					$('minute1').value = jssj.substring(10,12);
					$('second1').value = jssj.substring(12,14);

					$('hour1').disabled = "";
					$('minute1').disabled = "";
					$('second1').disabled = "";
				}
			}
							
		</script>
	</head>
	<body onload="revealTime()">
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a><bean:write name="title" /></a>
			</p>
		</div>
		<!-- ���� end-->
		<html:form action="/pjpyLcsz">
			<!-- ������ -->
			<input type="hidden" name="kssj" id="kssj" value="${rs.kssj }"/>
			<input type="hidden" name="jssj" id="jssj" value="${rs.jssj }"/>
			<input type="hidden" name="pkValue" id="pkValue" value="${param.pkValue }"/>
			
			<div class="tab">		
				<table class="formlist" border="0" align="center" style="width: 100%">
					<thead>
						<tr>
							<th colspan="4">
								<span>������������</span>
							</th>
						</tr>
					</thead>
					<!-- ������Ϣ -->
					<tbody>		
						<tr>
							<th align="right" width="20%">
								<font color="red">*</font>��������
							</th>
							<td align="left" width="30%">
								<html:text property="lcmc" styleId="lcmc" value="${rs.lcmc }"/>
							</td>
							<td colspan="2">
								<font color="blue">
									<span onmousemove="$('ts2').style.display = ''"
										onmouseout="$('ts2').style.display = 'none'">��ʾ��</span>
									<span id="ts2" style="display: none">
										��������ͼ�и����̵�����
									</span>
								</font>
							</td>
						</tr>
						<tr>
							<th align="right" width="20%">
								<font color="red">*</font>���̵ȼ�
							</th>
							<td align="left" width="30%">
								<html:select property="lcdj" value="${rs.lcdj }">
									<logic:iterate id="lcdj" name="lcdjList">
										<html:option value="${lcdj}">${lcdj}</html:option>
									</logic:iterate>
								</html:select>
							</td>
							<td colspan="2">
								<font color="blue">
									<span onmousemove="$('ts1').style.display = ''"
										onmouseout="$('ts1').style.display = 'none'">��ʾ��</span>
									<span id="ts1" style="display: none">
										���������������������������ڵ�λ��
									</span>
								</font>
							</td>
						</tr>
						<tr>
							<th align="right" width="">
								��ʼʱ��
							</th>
							<td align="left" colspan="3">
								<input type="text" id="data" style="width: 25%"
									onblur="dateFormatChg(this);initTime('data','hour','minute','second');" onclick="return showCalendar('data','y-mm-dd');"/>
								&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="text" id="hour" maxlength="2" disabled="disabled" onkeyup="checkInputData(this);" onblur="checkHour(this);" style="width: 5%"/> ʱ&nbsp;&nbsp;
								<input type="text" id="minute" maxlength="2" disabled="disabled" onkeyup="checkInputData(this);" onblur="checkMinute(this);" style="width: 5%"/> ��&nbsp;&nbsp;
								<input type="text" id="second" maxlength="2" disabled="disabled" onkeyup="checkInputData(this);" onblur="checkSecond(this);" style="width: 5%"/> ��&nbsp;&nbsp;
							</td>
						</tr>
						<tr>
							<th align="right" width="">
								����ʱ��
							</th>
							<td align="left" colspan="3">
								<input type="text" id="data1"
									onblur="dateFormatChg(this);initTime('data1','hour1','minute1','second1');" style="width: 25%"
									onclick="return showCalendar('data1','y-mm-dd');"/>
								&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="text" id="hour1" maxlength="2" onkeyup="checkInputData(this);" onblur="checkHour(this);" style="width: 5%"/> ʱ&nbsp;&nbsp;
								<input type="text" id="minute1" maxlength="2" onkeyup="checkInputData(this);" onblur="checkMinute(this);" style="width: 5%"/> ��&nbsp;&nbsp;
								<input type="text" id="second1" maxlength="2" onkeyup="checkInputData(this);" onblur="checkSecond(this);" style="width: 5%"/> ��&nbsp;&nbsp;
							</td>
						</tr>
						<tr>
							<th align="right" width="">
								����˵��
							</th>
							<td align="left" width="" colspan="3">
								<html:textarea property="lcsm" styleId="lcsm" rows="5" value="${rs.lcsm}" style="width:99%"></html:textarea>
							</td>							
						</tr>
					</tbody>
					<!-- ������ת -->
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"<span class="red">*</span>"Ϊ������
								</div>
								<div class="btn">
									<button type="button" onclick="saveLcsz()" id="buttonSave">
										����
									</button>
									<button type="button" onclick="Close();return false;" id="buttonClose">
										�ر�
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>			
			<!-- ��ʾ��Ϣ -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>