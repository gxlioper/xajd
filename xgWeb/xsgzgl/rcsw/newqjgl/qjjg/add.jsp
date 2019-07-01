<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/newqjgl/qjjg/js/operation.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/browser/js/browser.js"></script>
		<script type="text/javascript">
		jQuery(function() {//黑龙江农垦个性化
		var xxdm = jQuery("#xxdm").val();
		if ("12688" == xxdm) {
			jQuery("#qjts").change(function() { 
			  var qjts = jQuery("#qjts").val();
			  if(Number(qjts)>1){
			    jQuery('input:checkbox').attr("checked",false);
			  	jQuery("#jcTr_12688").hide();
			  }else{
			  	jQuery("#jcTr_12688").show();
			  }
			}); 
		}
		if ("12727" == xxdm) {
			jQuery("#qjts").change(function() { 
			  var qjts = jQuery("#qjts").val();
			  if(Number(qjts)>=1){
			  	jQuery("#jcTr_12727").hide();
			  }else{
			  	jQuery("#jcTr_12727").show();
			  }
			}); 
			jQuery('input:checkbox').click(function() {
				var b = false;
				var qjjc = document.getElementsByName("mdd");
				for ( var i = 0; i < qjjc.length; i++) {
					if (qjjc[i].checked) {
						b = true;
					}
				}
				if (b == false) {
					jQuery("#qjts").attr("readonly", false);
				} else {
					jQuery("#qjts").val("0.5");
					jQuery("#qjts").attr("readonly", true);
				}
			});
		}
	});
			var _initQjmx = function(){
				
				var kssj = jQuery("#kssj").val();
				var jssj = jQuery("#jssj").val();
				
				if (kssj == "" || jssj == ""){
					return false;
				}
				
				jQuery("#qjmxTbody").empty();
				var days = dateDiff(jssj,kssj)+1;
				
				for (var i = 0 ; i < days; i++){
					var nextDate = getNextDate(kssj,i);
					var rInput = jQuery("<input type='hidden' name='mxrq' value='"+nextDate+"'/>");
					var td = jQuery("<td align='center'>"+nextDate+"</td>");
					var tr = jQuery("<tr></tr>");
					td.append(rInput);
					tr.append(td);
					
					var qjxmCount = jQuery("#qjmxTr th").size()-1;
					
					for (var j = 0 ; j < qjxmCount ; j++){
						var cbox = jQuery("<input checked='checked' name='mxxm"+i+"' type='checkbox' value='"+jQuery("#qjmxTr th").eq(j+1).attr("xmdm")+"'/>");
						var td = jQuery("<td align='center'></td>");
						td.append(cbox);
						tr.append(td);
					}
					
					jQuery("#qjmxTbody").append(tr);
				}
			};
			/*
			function dateDiff(sDate1, sDate2){ 
			    var aDate, oDate1, oDate2, iDays;
			    aDate = sDate1.split("-");
			    oDate1 = new Date(aDate[1] + '/' + aDate[2] + '/' + aDate[0]); //转换为12-18-2002格式
			    aDate = sDate2.split("-");
			    oDate2 = new Date(aDate[1] + '/' + aDate[2] + '/' + aDate[0]);
			    iDays = parseInt((oDate1 - oDate2) / 1000 / 60 / 60 /24); //把相差的毫秒数转换为天数
			    return iDays;
			}*/
			
			function getNextDate(curDate,n){
		        //var uom = new Date(new Date(curDate)-0+n*86400000);
		        var uom = new Date(Date.parse(curDate.replace(/-/g,   "/")));
		        uom.setDate(uom.getDate() + n);
		        uom = uom.getFullYear() + "-" +  (uom.getMonth()+1) + "-" + uom.getDate();
		        return uom;
			}
			
			function checkAll(obj){
				var checked = jQuery(obj).prop("checked");
				jQuery("#qjmxTbody :input").attr("checked",checked);
			}
			function showstore(){//fjbt   store
				var  myselect=document.getElementById("qjlxid");
				var index=myselect.selectedIndex ;
				//var store = $("#qjlxid option:selected").text();
				var store = myselect.options[index].text;
				if(store==("病假")){			
					jQuery("#fjbt").append("<font color='red' id = 'fjbtlx'>*</font>");
				}else{
					jQuery("#fjbtlx").remove();
				}
			}
		</script>
	</head>
	<body>
		<html:form method="post" styleId="form" action="/qjjg" enctype="multipart/form-data">
		<input type="hidden" id="xxdm" value="${xxdm}"/>
		<div style='tab;width:100%;height:450px;overflow-x:hidden;overflow-y:auto;'>
			<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>学生基本信息</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/rcsw/rcxwwh/comm/selectStudent.jsp" %>
				<thead>
						<tr>
							<th colspan="4">
								<span>请假信息
								<logic:equal value="10511" name="xxdm">
								&nbsp;&nbsp;
									<a onclick="selectQjkc();" 
									   href="javascript:void(0);">
									   <font color="blue"><u>选择请假课程</u></font>	
									</a>
								</logic:equal>
								</span>
							</th>
						</tr>
				</thead>
				<tbody>
					<tr>
						<th align="right" width="10%">
							学年
						</th>
						<td align="left">
							<html:select property="xn" styleId="xn" disabled="false" 
									style="width:125px;">
									<html:options collection="xnList" property="xn"
										labelProperty="xn" />
							</html:select>
						</td>
						<th align="right">
							学期
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
						<th align="right" width="10%">
							<span class="red">*</span>请假天数
						</th>
						<td align="left">
							<html:text property="qjts" styleId="qjts" style="width:120px;" maxlength="4" onkeyup="value=value.replace(/(?:\D*)?(\d*)?(\.)?(5)?(?:\d*)?/ig,'$1$2$3')"></html:text>&nbsp;&nbsp;天&nbsp;&nbsp;
						</td>
						<th align="right">
							<span class="red">*</span>请假类型
						</th>
						<td align="left">
							<logic:equal value="12872" name="xxdm">
								<html:select property="qjlxid" styleId="qjlxid" disabled="false" onchange="showstore()" 
									style="width:125px;">
									<html:options collection="qjlxList" property="qjlxid"
										labelProperty="qjlxmc" />
								</html:select>
							</logic:equal>
							<logic:notEqual value="12872" name="xxdm">
								<html:select property="qjlxid" styleId="qjlxid" disabled="false" 
									style="width:125px;">
									<html:options collection="qjlxList" property="qjlxid"
										labelProperty="qjlxmc" />
								</html:select>
							</logic:notEqual>
						</td>
					</tr>
					<logic:equal value="10351" name="xxdm">
					<tr>
						<th align="right" width="10%">
							<span class="red">*</span>请假节数
						</th>
						<td align="left" >
						   <html:text property="qjjs" styleId="qjjs" style="width:120px;" maxlength="5" onkeyup="checkInputData(this);"></html:text>&nbsp;&nbsp;节&nbsp;&nbsp;
						</td>
						<th align="right">
							<span class="red">*</span>是否离校
						</th>
						<td align="left">
							<html:select property="sflx" styleId="sflx" disabled="false" style="width:125px;">
								<html:options collection="isnotList" property="dm" labelProperty="mc" />
							</html:select>
						</td>
					</tr>
					</logic:equal>
					<tr>
						<th align="right" width="10%">
							<span class="red">*</span>请假开始时间
						</th>
						<logic:equal value="1" name="qjsjxsgz">
							<td align="left">
								<logic:equal value="true" name="qjmxEnable" >
									<html:text property="kssj" styleId="kssj" style="width:120px;"
									onfocus="return showCalendar(this.id,'yyyy-MM-dd HH:mm',true,'jssj','','',_initQjmx);" />
								</logic:equal>
								<logic:notEqual value="true" name="qjmxEnable">
									<html:text property="kssj" styleId="kssj" style="width:120px;"
									onfocus="return showCalendar(this.id,'yyyy-MM-dd HH:mm',true,'jssj');" />
								</logic:notEqual>
							</td>
							</logic:equal>
							<logic:notEqual value="1" name="qjsjxsgz">
							<td align="left">
								<logic:equal value="true" name="qjmxEnable" >
									<html:text property="kssj" styleId="kssj" style="width:120px;"
									onfocus="return showCalendar(this.id,'yyyy-MM-dd',true,'jssj','','',_initQjmx);" />
								</logic:equal>
								<logic:notEqual value="true" name="qjmxEnable">
									<html:text property="kssj" styleId="kssj" style="width:120px;"
									onfocus="return showCalendar(this.id,'yyyy-MM-dd',true,'jssj');" />
								</logic:notEqual>
							</td>
						</logic:notEqual>
						<th align="right">
							<span class="red">*</span>请假结束时间
						</th>
						<logic:equal value="1" name="qjsjxsgz">
							<td align="left">
								<logic:equal value="true" name="qjmxEnable" >
									<html:text property="jssj" styleId="jssj" style="width:120px;"
									onfocus="return showCalendar(this.id,'yyyy-MM-dd HH:mm',false,'kssj','','',_initQjmx);" />
								</logic:equal>
								<logic:notEqual value="true" name="qjmxEnable">
									<html:text property="jssj" styleId="jssj" style="width:120px;"
									onfocus="return showCalendar(this.id,'yyyy-MM-dd HH:mm',false,'kssj');" />
								</logic:notEqual>
							</td>
							</logic:equal>
							<logic:notEqual value="1" name="qjsjxsgz">
							<td align="left">
								<logic:equal value="true" name="qjmxEnable" >
									<html:text property="jssj" styleId="jssj" style="width:120px;"
									onfocus="return showCalendar(this.id,'yyyy-MM-dd',false,'kssj','','',_initQjmx);" />
								</logic:equal>
								<logic:notEqual value="true" name="qjmxEnable">
									<html:text property="jssj" styleId="jssj" style="width:120px;"
									onfocus="return showCalendar(this.id,'yyyy-MM-dd',false,'kssj');" />
								</logic:notEqual>
							</td>
						</logic:notEqual>
					</tr>
					<logic:equal name="xxdm" value="12727">
							<tr id="jcTr_12727">
								<th align="right" width="10%">
									请假节次
								</th>
								<td colspan="3">
									<input type="checkbox" name="mdd" value="第一节" >第一节</input>
									<input type="checkbox" name="mdd" value="第二节" >第二节</input>
									<input type="checkbox" name="mdd" value="第三节" >第三节</input>
									<input type="checkbox" name="mdd" value="第四节" >第四节</input>
									<input type="checkbox" name="mdd" value="第五节" >第五节</input>
									<br/>
									<input type="checkbox" name="mdd" value="第六节" >第六节</input>
									<input type="checkbox" name="mdd" value="第七节" >第七节</input>
									<input type="checkbox" name="mdd" value="第八节" >第八节</input>
									<input type="checkbox" name="mdd" value="第九节" >第九节</input>
									<input type="checkbox" name="mdd" value="第十节" >第十节</input>
								</td>
							</tr>
						</logic:equal>
						<logic:equal name="xxdm" value="12688">
							<tr id="jcTr_12688">
								<th align="right" width="10%">
									请假节次
								</th>
								<td colspan="3">
									<input type="checkbox" name="mdd" value="第一节" >第一节</input>
									<input type="checkbox" name="mdd" value="第二节" >第二节</input>
									<input type="checkbox" name="mdd" value="第三节" >第三节</input>
									<input type="checkbox" name="mdd" value="第四节" >第四节</input>
									<input type="checkbox" name="mdd" value="第五节" >第五节</input>
									<br/>
									<input type="checkbox" name="mdd" value="第六节" >第六节</input>
									<input type="checkbox" name="mdd" value="第七节" >第七节</input>
									<input type="checkbox" name="mdd" value="第八节" >第八节</input>
									<input type="checkbox" name="mdd" value="第九节" >第九节</input>
									<input type="checkbox" name="mdd" value="第十节" >第十节</input>
								</td>
							</tr>
						</logic:equal>
					<logic:equal name="xxdm" value="70002">
							<th align="right">
								<span class="red">*</span>校内校外
							</th>
							 <td align="left">
							 	<html:select property="xnxw" styleId="xnxw" style="width:125px">
							 		<html:option value=""></html:option>
							 		<html:option value="校内">校内</html:option>
							 		<html:option value="校外">校外</html:option>
							 	</html:select>
							 </td>
							 <th></th>
							 <td></td>
						</logic:equal>
					<logic:equal name="xxdm" value="12866">
						<tr>
							<th align="right" width="10%">
								<span class="red">*</span>家长电话&nbsp;
							</th>
							<td colspan="3">
								<html:text property="jzdh" styleId="jzdh" maxlength="20"></html:text>
							</td>
						</tr>
						<tr>
							<th align="right" width="10%">
								备注&nbsp;
								<br />
								<font color="red">(限500字)</font>
							</th>
							<td colspan="3">
								<html:textarea rows="4" property="bz" styleId="bz"
									style="width:97%" onblur="checkLen(this,500);"></html:textarea>
							</td>
						</tr>
						</logic:equal>
					<logic:equal value="10511" name="xxdm">
						<tr>
							<th>请假课程</th>
							<td colspan="3">
								<table width="100%">
									<thead>
										<tr>
											<td>课程名称</td>
											<td>任课老师姓名</td>
											<td>任课老师联系方式</td>
										</tr>
									</thead>
									<tbody id="qjkc"></tbody>
								</table>
							</td>
						</tr>
						</logic:equal>
						<logic:equal value="true" name="qjmxEnable" >
							</tbody>
							<thead>
								<tr>
									<th colspan="4">
										<span>请假明细情况</span>
										<label>
											<input type="checkbox" onclick="checkAll(this);" checked="checked"/>全选/取消全选
										</label>
									</th>
								</tr>
							</thead>
							<tbody>
							<tr>
								<td colspan="4">
									<table style="width:100%;">
										<thead >
											<tr id="qjmxTr">
												<th style="text-align: center;">日期</th>
												<logic:iterate id="q" name="qjxmList">
													<th style="text-align: center;" xmdm="${q.dm }">${q.mc }</th>
												</logic:iterate>
											</tr>
										</thead>
										<tbody id="qjmxTbody">
										
										</tbody>
									</table>
								</td>
							</tr>
						</logic:equal>
						<logic:equal name="xxdm" value="10695">
							<tr>
								<th align="right">
									<span class="red">*</span>监护人姓名
								</th>
								<td>
									<html:text property="jhrxm" styleId="jhrxm" maxlength="10"></html:text>
								</td>
								<th align="right">
									<span class="red">*</span>监护人联系方式
								</th>
								<td>
									<html:text property="jhrlxfs" styleId="jhrlxfs" maxlength="15"></html:text>
								</td>
							</tr>
							<tr>
								<th align="right">
									<span class="red">*</span>交通工具
								</th>
								<td>
									<html:select property="jtgj" styleId="jtgj">
										<html:options collection="dmList" labelProperty="mc" property="dm"/>
									</html:select>
								</td>
								<th align="right">
									<span class="red">*</span>目的地
								</th>
								<td>
									<html:text property="mdd" styleId="mdd" maxlength="20"></html:text>
								</td>
							</tr>
						</logic:equal>
					<tr>
						<th align="right" width="10%">
							<span class="red">*</span>请假事由&nbsp;
							<br />
							<font color="red">(限500字)</font>	
						</th>
						<td colspan="3">
							<html:textarea rows="4" property="qjsy" styleId="qjsy" style="width:97%" onblur="checkLen(this,500);"></html:textarea>
						</td>
					</tr>
					<tr>
						<th align="right" width="10%">
							<span id = "fjbt"></span>附件信息
						</th>
						<td colspan="3">
							<html:hidden property="filepath" styleId="filepath" />
							<%@ include file="/xsgzgl/comm/fileUpload/f.jsp"%>
							<script type="text/javascript">
										//调用附件 
										jQuery(function(){
											jQuery.MultiUploader({
												maxcount : 3,
												//后缀
												accept : 'png|gif|jpg|zip|rar|doc|docx',
												//最大文件大小 单位M
												maxsize: 10,
												//存放附件的隐藏域的id
												elementid : 'filepath'
												});
										});
									</script>
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
								"<span class="red">*</span>"为必填项
							</div>
							<div class="btn">
								<button type="button"  onclick="save('qjjg.do?method=add&type=save','xh-qjts-qjlxid-kssj-jssj-qjsy','false');return false;" id="buttonSave">
									保 存
								</button>
								<button type="button"  onclick="iFClose();" id="buttonClose">
									关 闭
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
