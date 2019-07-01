<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/interface/pjpyZjsyzy.js"></script>
		<script type="text/javascript" src="js/rcsw/rcswFunction.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
			function bzCssz(){
				var ejsh = $('ejsh').checked;
				var fdyshr = $('fdyshr').checked;
				var xyshr = $('xyshr').checked;
				var xxshr = $('xxshr').checked;
				
				if (ejsh){
					if (fdyshr && xyshr && xxshr){
						alert("��ѡ�����������!");
						return false;
					}
				
					if (!(fdyshr && xyshr) && !(fdyshr && xxshr) && !(xyshr && xxshr)){
						alert("��ѡ�����������!");
						return false;
					}
					
					if (!xxshr){
						alert("���������߼������ΪѧУ!");
						return false;
					}
				} else {
					if (!fdyshr && !xyshr && !xxshr){
						alert("��ѡ������һ�������!");
						return false;
					}
				}
				
				
				saveUpdate('/xgxt/jygl.do?method=cssz&doType=save','');
			}
			
			
			function szShr(obj){
				
				if (obj.checked){
					$('xysbbshr').value+=','+obj.value;
				} else {
					$('xysbbshr').value=$('xysbbshr').value.replace(','+obj.value,'');
				}
				
			}
			
			function loadShr(){
				var shr = $('xysbbshr').value.split(',');
				
				for (var i = 0 ; i < shr.length ; i++){
				
					if ("fdy"==shr[i]){
						$('fdyshr').checked=true;
					} else if ("xy"==shr[i]){
						$('xyshr').checked=true;
					} else if ("xx"==shr[i]){
						$('xxshr').checked=true;
					}
				}
			}
		</script>
	</head>
	<body >
		<div class="tab">
			<html:form action="/jygl" method="post">
				<input type="hidden" id="userType" name="userType"
					value="${userType }" />
				<input type="hidden" id="userName" name="userName"
					value="${userName }" />
				<input type="hidden" id="message" value="${message }"/>
				<input type="hidden" name="pkValue" value="${xxdm }"/>
				<input type="hidden" name="njV" id="njV"/>
				<input type="hidden" name="xyV" id="xyV"/>
				<input type="hidden" name="zyV" id="zyV"/>
				<input type="hidden" name="bjV" id="bjV"/>
				
				<input type="hidden" name="save_xxdm" id="xxdm" value="${xxdm }"/>
				<input type="hidden" name="save_xysbbshr" id="xysbbshr" value="${rs.xysbbshr }"/>

				<div class="tab_cur">
					<p class="location">
						<em>���ĵ�ǰλ��:</em><a>${title }</a>
					</p>
				</div>

				<table class="formlist" width="70%">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button id="saveButton"
										onclick="bzCssz();">
										����
									</button>
									<button onclick="reset();loadShr();">
										����
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<thead>
						<tr>
							<th colspan="2">
								<span>��������</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th style="width:30%">
								��ҵ���ϱ�ʱ��
							</th>
							<td>
								<html:text property="save_byssbkssj"
										   onclick="showCalendar(this.id,'y-mm-dd')" 
										   onblur='dateFormatChg(this)'
										   styleId="byssbkssj"
										   name="rs"
										   ></html:text>	
								-							
								<html:text property="save_byssbjssj"
										   onclick="showCalendar(this.id,'y-mm-dd')" 
										   onblur='dateFormatChg(this)'
										   styleId="byssbjssj"
										   name="rs"
								></html:text>								
							</td>
						</tr>
						<tr>
							<th>��ҵ����Ϣȷ��ʱ��</th>
							<td>
								<html:text property="save_bysqrkssj"
										   onclick="showCalendar(this.id,'y-mm-dd')" 
										   onblur='dateFormatChg(this)'
										   styleId="bysqrkssj"
										   name="rs"
										   ></html:text>	
								-							
								<html:text property="save_bysqrjssj"
										   onclick="showCalendar(this.id,'y-mm-dd')" 
										   onblur='dateFormatChg(this)'
										   styleId="bysqrjssj"
										   name="rs"
								></html:text>
							</td>
						</tr>
						<tr>
							<th>��ҵ����Ϣ���ʱ��</th>
							<td>
								<html:text property="save_bysshkssj"
										   onclick="showCalendar(this.id,'y-mm-dd')" 
										   onblur='dateFormatChg(this)'
										   styleId="bysshkssj"
										   name="rs"
										   ></html:text>	
								-							
								<html:text property="save_bysshjssj"
										   onclick="showCalendar(this.id,'y-mm-dd')" 
										   onblur='dateFormatChg(this)'
										   styleId="bysshjssj"
										   name="rs"
								></html:text>
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="2">
								<span>��������</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>
								��Ҫ��������
							</th>
							<td>
								<html:radio property="save_lcbh" value="1" name="rs">
								��ҵ����Ϣȷ�� �� ǩ���ҵЭ��
								</html:radio><br/>
								<html:radio property="save_lcbh" value="2" name="rs">
								��ҵ����Ϣȷ�� �� ��ȡ��ҵЭ�� �� ǩ���ҵЭ��
								</html:radio>
							</td>
						</tr>
						<tr>
							<th>
								Э���鲹����˼���
							</th>
							<td>
								<html:radio property="save_xysbbshjb" value="1" name="rs" >
								һ�����
								</html:radio> 
								<html:radio property="save_xysbbshjb" value="2" name="rs" styleId="ejsh">
								�������
								</html:radio>
							</td>
						</tr>
						<tr>
							<th>
								Э���鲹�������
							</th>
							<td>
								<html:checkbox property="shr" name="myForm" value="fdy" onclick="szShr(this)" styleId="fdyshr"/>����Ա
								<html:checkbox property="shr" name="myForm" value="xy"  onclick="szShr(this)" styleId="xyshr"/><bean:message key="lable.xb" />
								<html:checkbox property="shr" name="myForm" value="xx"  onclick="szShr(this)" styleId="xxshr"/>ѧУ
							</td>
						</tr>
					</tbody>
					
				</table>
			</html:form>
			<script>
				var shr = $('xysbbshr').value.split(',');
				
				for (var i = 0 ; i < shr.length ; i++){
				
					if ("fdy"==shr[i]){
						$('fdyshr').checked=true;
					} else if ("xy"==shr[i]){
						$('xyshr').checked=true;
					} else if ("xx"==shr[i]){
						$('xxshr').checked=true;
					}
				}
			</script>
			<logic:present name="message">
				<script>
					alert("${message}");
				</script>
			</logic:present>
		</div>
	</body>
	</html>