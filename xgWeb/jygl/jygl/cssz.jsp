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
						alert("请选择两个审核人!");
						return false;
					}
				
					if (!(fdyshr && xyshr) && !(fdyshr && xxshr) && !(xyshr && xxshr)){
						alert("请选择两个审核人!");
						return false;
					}
					
					if (!xxshr){
						alert("两级审核最高级别必须为学校!");
						return false;
					}
				} else {
					if (!fdyshr && !xyshr && !xxshr){
						alert("请选择至少一个审核人!");
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
						<em>您的当前位置:</em><a>${title }</a>
					</p>
				</div>

				<table class="formlist" width="70%">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button id="saveButton"
										onclick="bzCssz();">
										保存
									</button>
									<button onclick="reset();loadShr();">
										重置
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<thead>
						<tr>
							<th colspan="2">
								<span>基本设置</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th style="width:30%">
								毕业生上报时间
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
							<th>毕业生信息确认时间</th>
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
							<th>毕业生信息审核时间</th>
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
								<span>流程设置</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>
								主要流程设置
							</th>
							<td>
								<html:radio property="save_lcbh" value="1" name="rs">
								毕业生信息确认 → 签署就业协议
								</html:radio><br/>
								<html:radio property="save_lcbh" value="2" name="rs">
								毕业生信息确认 → 领取就业协议 → 签署就业协议
								</html:radio>
							</td>
						</tr>
						<tr>
							<th>
								协议书补办审核级别
							</th>
							<td>
								<html:radio property="save_xysbbshjb" value="1" name="rs" >
								一级审核
								</html:radio> 
								<html:radio property="save_xysbbshjb" value="2" name="rs" styleId="ejsh">
								二级审核
								</html:radio>
							</td>
						</tr>
						<tr>
							<th>
								协议书补办审核人
							</th>
							<td>
								<html:checkbox property="shr" name="myForm" value="fdy" onclick="szShr(this)" styleId="fdyshr"/>辅导员
								<html:checkbox property="shr" name="myForm" value="xy"  onclick="szShr(this)" styleId="xyshr"/><bean:message key="lable.xb" />
								<html:checkbox property="shr" name="myForm" value="xx"  onclick="szShr(this)" styleId="xxshr"/>学校
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