<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="js/sharedFunction.js"></script>
		<script type="text/javascript"
			src="/xgxt/dwr/interface/getBaseData.js"></script>
		<script type="text/javascript"
			src="/xgxt/js/webServiceFunction/wsFunction.js"></script>
		<script type="text/javascript" src="/xgxt/js/bjlhdx/bjlhdxBaseData.js"></script>
		<script type="text/javascript">
		function sjdr(){
			var realTable = document.getElementById('realTable').value;
			if(realTable==""){
				alert('��ѡ����Ҫ����ı�!');
				return false;
			}else{
				impAndChkData();
			}
		}
		function webSerSjdr(){
			var realTable = document.getElementById('glsjTable').value;
				if(realTable==""){
					alert('��ѡ����Ҫ����ı�!');
					return false;
				}else{
					impAndChkData();
				}
		}
		function xyChange(){
		    var msg = "�ò��������xydzb������ݱ�����е�"+jQuery("#xbmc").val()+"������صı�\nȷ��Ҫ������?";
			if (confirm(msg)) {
				refreshForm('/xgxt/base_data_init.do?type=xyChange');
			}
		}
		function zyChange(){
		    var msg = "�ò��������zydzb������ݱ�����е�רҵ������صı�\nȷ��Ҫ������?";
			if (confirm(msg)) {
				refreshForm('/xgxt/base_data_init.do?type=zyChange');
			}
		}
		function bjChange(){
		    var msg = "�ò��������bjdzb������ݱ�����еİ༶������صı�\nȷ��Ҫ������?";
			if (confirm(msg)) {
				refreshForm('/xgxt/base_data_init.do?type=bjChange');
			}
		}
		</script>
	</head>
	<body>

		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>ϵͳά�� - ϵͳ��ʼ�� - �������ݳ�ʼ��</a>
			</p>
		</div>

		<html:form action="/chgPass" method="post">
		<input type="hidden" id="xbmc" value="<bean:message key="lable.xb" />"/>

			<logic:notEqual value="zfjw" name="webSerTb">
				<div class="tab">
					<table width="100%" border="0" class="formlist">
						<thead>
							<tr>
								<th colspan="4">
									<span>�������ݳ�ʼ��(ѧ��ά������)</span>
								</th>
							</tr>
						</thead>
						<tfoot>
							<tr>
								<td colspan="4">
									<div class="btn">
										<button type="button" name="�ύ" onclick="sjdr()">
											���ݵ���
										</button>
										<button type="button" name="Submit2" onclick="xyChange()">
											<bean:message key="lable.xb" />������
										</button>
										<button type="button" name="Submit2" onclick="zyChange()">
											רҵ������
										</button>
										<button type="button" name="Submit2" onclick="bjChange()">
											�༶������
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
						<tbody>
							<tr>
								<th width="20%">
									��ѡ��������ݱ�
								</th>
								<td width="80%">
									<html:select property="realTable" style="width:180px"
										styleId="realTable">
										<html:option value=""></html:option>
										<html:options collection="jbsjTableList" property="tablename"
											labelProperty="tablecomment" />
									</html:select>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</logic:notEqual>
			<%--	�����Ǳ������ϵ�����ͬ������		--%>
			<logic:equal value="true" name="webSerTb">
				<div class="tab" id="displayCols" name="displayCols" style="display:none">
					<table width="100%" border="0" class="formlist">
						<thead>
							<tr>
								<th colspan="4">
									<span>����������ͬ��</span>
								</th>
							</tr>
						</thead>
						<tfoot>
							<tr>
								<td colspan="4">
									<div class="btn">
										<button type="button" name="�ύ" onclick="checkInputTime()">
											��������
										</button>
										<button type="button" name="Submit2" onclick="synchronizeData('glsjTable')">
											�ֶ�ͬ��
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
						<tbody>
							<tr>
								<th width="20%">
									��ѡ��������ݱ�
								</th>
								<td width="80%">
									<html:select property="glsjTable" style="width:180px"
										onchange="selTable(this)" styleId="glsjTable">
										<html:option value=""></html:option>
										<html:options collection="glsjTableList" property="tablename"
											labelProperty="tablecomment" />
									</html:select>
								</td>
							</tr>
							<tr>
								<th>ͬ����ʼʱ��</th>
								<td>
									<input type="text" id="yearMonthDay" name="yearMonthDay"
													onblur="dateFormatChg(this)" style="cursor:hand;"
													onclick="return showCalendar('yearMonthDay','y-mm-dd');"
													value=""/>
									<input type="text" name="hh" id="hh" style="width:15%"
													maxlength="2" value="00"/>
												:
									<input type="text" name="mi" id="mi" style="width:15%"
													maxlength="2" value="00"/>
												:
									<input type="text" name="ss" id="ss" style="width:15%"
													maxlength="2" value="00"/>
								</td>
							</tr>
							<tr>
								<th>ͬ�����ʱ��</th>
								<td>
									<input type="text" name="day" id="day" style="width:10%"
													maxlength="3" value="000"/>
												��
												<input type="text" name="hour" id="hour" style="width:10%"
													maxlength="3" value="000"/>
												Сʱ
												<input type="text" name="minute" id="minute"
													style="width:10%" maxlength="3" value="000"/>
												����
								</td>
							</tr>
							<tr>
								<th></th>
								<td></td>
							</tr>
						</tbody>
					</table>
				</div>
			</logic:equal>
			<logic:equal value="zfjw" name="webSerTb">
				<input type="hidden" name="webSerTb" id="webSerTb"
					value="<bean:write name = "webSerTb"/>" />

				<div class="tab" id="displayCols" name="displayCols" >
					<table width="100%" border="0" class="formlist">
						<thead>
							<tr>
								<th colspan="4">
									<span>����������ͬ��(����webServiceȡ����)</span>
								</th>
							</tr>
						</thead>
						<tfoot>
							<tr>
								<td colspan="4">
									<div class="btn">
										<button type="button" name="�ύ" onclick="synchronizeData('glsjTable')">
											���ݵ���
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
						<tbody>
							<tr>
								<th width="20%">
									��ѡ��������ݱ�
								</th>
								<td width="80%">
									<html:select property="glsjTable" style="width:180px"
										styleId="glsjTable">
										<html:option value=""></html:option>
										<html:option value="xsxx">ѧ����Ϣ</html:option>
										<html:option value="xsqtxx">ѧ��������Ϣ</html:option>
										<html:option value="cjxx">ѧ���ɼ���Ϣ</html:option>
										<html:option value="xyxx">
											<bean:message key="lable.xsgzyxpzxy" />��Ϣ</html:option>
										<html:option value="bjxx">�༶��Ϣ</html:option>
										<html:option value="zyxx">רҵ��Ϣ</html:option>
										<html:option value="xjyd">ѧ���춯��Ϣ</html:option>
									</html:select>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</logic:equal>
			<logic:present name="doresult">
				<logic:equal name="doresult" value="true">
					<script type="text/javascript">
      		alert("����ɹ���");
      	</script>
				</logic:equal>
				<logic:equal name="doresult" value="false">
					<script type="text/javascript">
      		alert("����ʧ�ܣ�");
      	</script>
				</logic:equal>
			</logic:present>
			<logic:present name="update">
				<logic:equal name="update" value="true">
					<script type="text/javascript">
      		alert("���³ɹ���");
      	</script>
				</logic:equal>
				<logic:equal name="update" value="false">
					<script type="text/javascript">
      		alert("����ʧ�ܣ�");
      	</script>
				</logic:equal>
			</logic:present>
		</html:form>
		
		<div id="showMes" name="showMes" style="display:none;">
			����ִ��ͬ��
		</div>
	</body>
</html>
