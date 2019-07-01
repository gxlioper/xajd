<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<%@ include file="/syscommon/autocomplete.ini"%>
		<script type="text/javascript" src="<%=stylePath%>js/lhgdialog/lhgdialog.min.js?self=true"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/fyff/ffjg/js/fyffjg.js"></script>
		<script type="text/javascript" src="xsgzgl/xpjpy/wdpj/bdpz/js/bdpz.js"></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		
		<script type="text/javascript">
			jQuery(function($){
				if($("#ffsj").val().length>7){
					$("[name=fffs]").each(function(){
						if($(this).val()=="1"){
							$(this).attr("checked",true);
						}
					});
				} else {
					$("[name=fffs]").each(function(){
						if($(this).val()=="0"){
							$(this).attr("checked",true);
						}
					});
				}
								

				selectFfxm();
				selectFftj();
				
				if('0' == $("input[name='fffs']:checked").val()){
					jQuery("#bfyf").show();
				} else {
					jQuery("#bfyf").hide();
				}
				
			});

		</script>
		
	</head>
		
	<body>
		
		<html:form action="/rcsw_fyff_ffjg" method="post" styleId="FyffjgForm" onsubmit="return false;">
			<html:hidden property="ffxmdmkz" styleId="ffxmdmId"/>
			<html:hidden property="fftjdmkz" styleId="fftjdmId"/>
			<html:hidden property="ffsjkz" styleId="ffsjId"/>
			<html:hidden property="guid" styleId="guid"/>
			<input type="hidden" name="fffs" id="fffs" value=${fffs } />
			
			<div style='tab;width:100%;height:410px;overflow-x:hidden;overflow-y:auto;' >
				<table width="100%" border="0" class="formlist">
					<thead>
					<tr>
						<th colspan="4">
						<span>学生信息</span>
						</th>
					</tr>
					</thead>
						<%@ include file="/xsgzgl/rcsw/fyff/bdpz/updateStudent.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
							<span>费用发放信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
					    <tr>
							<th><span class="red">*</span>发放项目</th>
							<td>
								<html:select  property="ffxmdm" styleId="ffxmdm" style="width:130px" onchange="loadFfje(this);">
									<option ></option>
									<logic:iterate id="list" name="ffxmList" indexId="i">
										<option value="${list.ffxmdm}" mrffje="${list.mrffje}" fffs="${list.fffs}">${list.ffxmmc}</option>
									</logic:iterate>
								</html:select>
							</td>
							
							<th><span class="red">*</span>发放金额</th>
							<td>
								<html:text  property="sfje" styleId="sfje"  style="width:120px;" maxlength="5"  styleClass="text_nor" onkeyup="value=value.replace(/[^\d]/g,'')"></html:text>
							</td>
					    </tr>
					    <tr>
							<th><span class="red">*</span>发放时间</th>
							<td>
								<html:radio  property="fffs" styleId="fffs0" value="0" onclick="changeDate(this);changeMonthorDay(this)" ><label for="fffs0" style="cursor:pointer">按月</label></html:radio>
								<html:radio  property="fffs" styleId="fffs1" value="1" onclick="changeDate(this);changeMonthorDay(this)" ><label for="fffs1" style="cursor:pointer">按日</label></html:radio>
								<html:hidden property="ffsj" styleId="ffsj" />
								
								<input id="mm" type="text" size="10" 
									onclick="return showCalendar('mm','yyyy-MM');" readonly="true" style="display:none;"  />
								<input id="dd" type="text"  size="10"
									onclick="return showCalendar('dd','yyyy-MM-dd');" 
									 readonly="true" style="display:none;"  />
							</td>
							<th><span class="red">*</span>发放途径</th>
							<td>
								<html:select  property="fftjdm" styleId="fftjdm" style="width:80px" onchange="changeFfzh(this);">
									<option></option>
									<logic:iterate id="fftj" name="fftjList" indexId="i">
										<option value="${fftj.fftjdm}" ffzh="${fftj.ffzh }">${fftj.fftj}</option>
									</logic:iterate>
								</html:select>
								
								<html:text  property="ffzh" styleId="ffzh"  style="width:150px;display:none;" maxlength="20"  styleClass="text_nor" ></html:text>
							
							</td>
						</tr>
						<tr id="bfyf">
							<th>补发月份数</th>
							<td>
								<html:text  property="bfyfs" styleId="bfyfs"  style="width:120px;" maxlength="3"  styleClass="text_nor" onkeyup="value=value.replace(/[^\d]/g,'')"></html:text>
							</td>
					    
							<th>补发金额</th>
							<td>
								<html:text  property="bfje" styleId="bfje"  style="width:120px;" maxlength="5"  styleClass="text_nor" onkeyup="value=value.replace(/[^\d]/g,'')"></html:text>
							</td>
						</tr>
					    <tr>
							<th>
								备注
								<br /><font color="red">(限500字)</font>
							</th>
							<td colspan="3">
								<html:textarea property='bz' style="width:98%" styleId="bz" rows='4' onblur="checkLen(this,50);"/>
							</td>
			      </tr>
					</tbody>
				</table>
			</div>
			<div style="height: 15px"></div>
			<table width="100%" border="0" class="formlist"
				style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"为必填项</div>
								<div class="btn">
									<button type="button" type="button" onclick="saveForm('updateFyffjg','update');">
										保 存
									</button>
									<button type="button" type="button" onclick="iFClose();">
										关 闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
			</table>
		</html:form>
	</body>
</html>

