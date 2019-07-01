<%@ page language="java" contentType="text/html; charset=GBK"%>
<jsp:directive.page import="java.util.*" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type='text/javascript' src="js/check.js"></script>
		<style type="text/css">
             .note{
                position:absolute;line-height:15px;padding:3px 5px;top:20px;
             }
		</style>
		<script type='text/javascript'>
		var sl;

		function yc(obj){
			var div = jQuery(obj).siblings("div").eq(0);
			jQuery(div).css("display","none");
		}

		function xs(obj){			
			var zhi = jQuery(obj).val();
			var div = jQuery(obj).siblings("div").eq(0);
			if(zhi == null || zhi == ""){
				jQuery(div).css("display","block");
			}else{
				jQuery(div).css("display","none");
			}
		}
		
		function saveFdgl(type) {
			var flag = false;
			var fdrq = "";
			var fdnr = "";
			var fdbz = "";
			var fdjssj = "";
			var fddd = "";
			var fdpj = "";
			var gs = "";
			var s = 0;
			var k = 0;
			jQuery("input:text[name=rq]").each(function(index){
				if(flag){
					fdrq += ",";
				}else{
					flag = true;
				}
				if(jQuery(this).val()!=""){
					s ++;
				}
				fdrq += jQuery(this).val();
			});
			flag = false;
			jQuery("input:text[name=nr]").each(function(index){
				if(flag){
					fdnr += ",";
				}else{
					flag = true;
				}
				if(jQuery(this).val()!=""){
					k++;
				}
				fdnr += jQuery(this).val();
			});
			flag = false;

			var flagg = true;

			jQuery("input:[name=fdjssjs]").each(function(){
				var sj = jQuery(this).val();
				if(sj.trim() == "" || sj.trim() == null){
					flagg = false;
					return;
				}else{
					fdjssj += ",";
					fdjssj += sj;
				}
			})
			
			if(!flagg){
				showAlertDivLayer("请将记录填写完整！");
				return false;
			}

			jQuery("input:[name=gss]").each(function(){
				var gss = jQuery(this).val();
				if(gss.trim() == "" || gss.trim() == null){
					flagg = false;
					return;
				}else{
					gs += ",";
					gs += gss;
				}
			})
			
			if(!flagg){
				showAlertDivLayer("请将记录填写完整！");
				return false;
			}

			jQuery("input:[name=fddds]").each(function(){
				var fdddd = jQuery(this).val();
				if(fdddd.trim() == "" || fdddd.trim() == null){
					flagg = false;
					return;
				}else{
					fddd += ",";
					fddd += fdddd;
				}
			})
			
			if(!flagg){
				showAlertDivLayer("请将记录填写完整！");
				return false;
			}

			jQuery("input:[name=fdpjs]").each(function(){
				var fdpjs = jQuery(this).val();
				if(fdpjs.trim() == "" || fdpjs.trim() == null){
					flagg = false;
					return;
				}else{
					fdpj += ",";
					fdpj += fdpjs;
				}
			})
			
			if(!flagg){
				showAlertDivLayer("请将记录填写完整！");
				return false;
			}
			
			jQuery("textarea:[name=bz]").each(function(index){
				if(flag){
					fdbz += ",";
				}else{
					flag = true;
				}
				fdbz += jQuery(this).val();
			});
			jQuery("#fdrq").val(fdrq);
			jQuery("#fdnr").val(fdnr);
			jQuery("#fdbz").val(fdbz);
			jQuery("#fdjssj").val(fdjssj.replace(",",""));
			jQuery("#fddd").val(fddd.replace(",",""));
			jQuery("#gs").val(gs.replace(",",""));
			jQuery("#fdpj").val(fdpj.replace(",",""));
			if(s!=k){
				showAlertDivLayer("请将记录填写完整！");
				return false;
			}
			if(fdrq.replace(",","")=="" || fdrq==null || fdnr.replace(",","")=="" || fdnr==null){
				showAlertDivLayer("请至少填写一条完整记录！");
				return false;
			}
			var url = "zzyrxmglfdgl.do?method=txFdgl&type="+type;
			ajaxSubFormWithFun("fdglForm", url, function(data) {
				if (data["message"] == "保存成功！") {
					showAlert(data["message"], {}, {
						"clkFun" : function() {
							if (parent.window) {
								refershParent();
							}
						}
					});
				} else {
					showAlert(data["message"]);
				}
			});
			
		}
		function delThis(obj){
			jQuery(obj).parents("tr[name='fdnr']").remove();
			var id=jQuery(obj).parents("tr[name='fdnr']").find("input[id='id']").val();
			if(id != ""){
			var url = "zzyrxmglfdgl.do?method=delFdjl";
				jQuery.post(url, {
					id : id
				}, function(data) {
					//
				}, 'json');
			}
		}

		function addConMoreUpdateTr(flszid) {
			sl++;
			var trHtml = '<tr name="fdnr"><td><input type="hidden" name="id" id="id" value=""/><input type="text" name="rq" maxlength="10" '+
				'onfocus="showCalendar(\'rq_'+sl+'\',\'yyyy-MM-dd HH:mm:ss\',true,\'jsrq_'+sl+'\')" style="width: 120px" id="rq_'+sl+'" onchange="jsgs(\''+sl+'\')"/></td>'+
				'<td><input type="text" name="fdjssjs"  onfocus="showCalendar(\'jsrq_'+sl+'\',\'yyyy-MM-dd HH:mm:ss\',false,\'rq_'+sl+'\')" maxlength="10" style="width: 120px" id="jsrq_'+sl+'" onchange="jsgs(\''+sl+'\')"/></td>'+
				'<td><input type="text" name="gss" maxlength="4" style="width: 50px" id="gs_'+sl+'" readonly="readonly"/></td>'+
				'<td><input type="text" name="fddds" maxlength="20" style="width: 120px"/></td>'+
				'<td><input type="text" name="nr"  maxlength="50" style="width: 120px"/></td>'+
				'<td><input type="text" name="fdpjs" maxlength="20" style="width: 80px"/></td>'+
				'<td><div style="position:relative;">'+
				'<textarea name="bz" rows="6" onblur="xs(this);checkLen(this,500);" onfocus="yc(this);" style="width: 150px"></textarea>'+
				'<div id="note_'+sl+'" class="note">'+
				'<font color="#777">1、本次辅导意见</font></br>'+
				'<font color="#777">2、对辅导学员今后的建议</font></br>'+
				'</div></div></td>'+
				'<td><button type="button" id="but" onclick="delThis(this);" style="width: 65px">删除</button></td></tr>';
			jQuery("#headTr").append(trHtml);
		}

		function formatFloat(src,pos){ 
			return Math.round(src*Math.pow(10, pos))/Math.pow(10, pos); 
		}

		function jsgs(position){
			var kssj = jQuery("#rq_"+position).val();
			var jssj = jQuery("#jsrq_"+position).val();
			if(kssj == null || jssj == null || kssj == '' || jssj == ''){
				return;
			}
			var kssj = jQuery("#rq_"+position).val();
			var tempStrs = kssj.split(" ");  //截取时间
			var dateStrs = tempStrs[0].split("-");  // 解析日期部分
			var timeStrs = tempStrs[1].split(":");  // 解析时间部分
			var kssjfinallyDate = new Date(parseInt(dateStrs[0], 10), 
                      (parseInt(dateStrs[1], 10) - 1), 
                       parseInt(dateStrs[2], 10), 
                       parseInt(timeStrs [0], 10), 
                       parseInt(timeStrs[1], 10), 
                       parseInt(timeStrs[2], 10));			
			var jssjStrs = jssj.split(" ");  //截取时间
			var jssjdateStrs = jssjStrs[0].split("-");  // 解析日期部分
			var jssjtimeStrs = jssjStrs[1].split(":");  // 解析时间部分
			var jssjfinallyDate = new Date(parseInt(jssjdateStrs[0], 10), 
                       (parseInt(jssjdateStrs[1], 10) - 1), 
                        parseInt(jssjdateStrs[2], 10), 
                        parseInt(jssjtimeStrs[0], 10), 
                        parseInt(jssjtimeStrs[1], 10), 
                        parseInt(jssjtimeStrs[2], 10));
            var Dtime = jssjfinallyDate - kssjfinallyDate; 
			var hours = Dtime/(3600*1000);
			hours = hours.toFixed(1);
			jQuery("#gs_"+position).val(hours);                
		}
				
<%--		function addConMoreUpdateTr(flszid) {--%>
<%--			var trHtml = '<tr name="fdnr"><td><input type="hidden" name="id" id="id" value=""/><input type="text" name="rq" maxlength="10" '+--%>
<%--				'onfocus="WdatePicker({dateFmt:\'yyyy-MM-dd HH:mm:ss:ssss\'})" /></td>'+--%>
<%--				'<td><input type="text" name="nr" maxlength="100" /></td>'+--%>
<%--				'<td><textarea name="bz" rows="4" onblur="checkLen(this,500);"></textarea></td>'+--%>
<%--				'<td><button type="button" id="but" onclick="delThis(this);">删除</button></td></tr>';--%>
<%--			jQuery("#headTr").append(trHtml);--%>
<%--		}--%>
			jQuery(function(){
				sl = jQuery("[name='fdnr']").length;			
			})


		</script>
	</head>
	<body style="width: 100%">
		<html:form action="/zzyrxmglfdgl" method="post" styleId="fdglForm" onsubmit="return false;">
			<html:hidden property="fdxxid"/>
			<input type="hidden" name="fdlx" value="0"/>
			<input type="hidden" name="fdrq" id="fdrq" value=""/>
			<input type="hidden" name="fdnr" id="fdnr" value=""/>
			<input type="hidden" name="fdbz" id="fdbz" value=""/>
			<input type="hidden" name="fdjssj" id="fdjssj" value="" />
			<input type="hidden" name="gs" id="gs" value="" />
			<input type="hidden" name="fddd" id="fddd" value="" /> 
			<input type="hidden" name="fdpj" id="fdpj" value="" />   
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>辅导信息</span>
							</th>
						</tr>
					</thead>
					<tr>
						<th width="20%">助教姓名</th>
						<td width="30%">${rs.fdrxm }</td>
						<th width="20%">助教联系电话</th>
						<td width="30%">${rs.fdrlxdh }</td>
					</tr>
					<tr>
						<th>学员姓名</th>
						<td>${rs.bfdrxm }</td>
						<th>学员联系电话</th>
						<td>${rs.bfdrlxdh }</td>
					</tr>
					<tr>
						<th>辅导科目</th>
						<td colspan="3">${rs.fdkm }</td>
					</tr>
					
					<thead>
						<tr>
							<th colspan="4">
								<span>辅导记录<a class='name' href='javascript:;' onclick="addConMoreUpdateTr();">增加一行</a></span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td colspan="4">														
								<table id="headTr">
									<tr>	
										<td style="width: 10%">
											<span class="red">*</span>辅导开始日期
										</td>
										<td style="width: 10%">
											<span class="red">*</span>辅导结束日期
										</td>
										<td style="width: 5%">
											<span class="red">*</span>工时
										</td>
										<td style="width: 15%">
											<span class="red">*</span>辅导地点
										</td>
										<td style="width: 20%">
											<span class="red">*</span>辅导内容
										</td>
										<td style="width: 10%">
											<span class="red">*</span>辅导评价
										</td>
										<td style="width: 25%">
											备注
										</td>
										<td style="width: 5%">
											操作
										</td>
									</tr>
									<logic:notEqual name="size" value="0">
										<logic:iterate name="fdjlList" id="f" indexId="i">
										<tr name="fdnr">
											<td>
												<input type="hidden" name="id" id="id" value="${f.id }"/>
												<input type="text" name="rq" id="rq_${i}" value="${f.fdrq }" maxlength="10" onfocus="showCalendar('rq_${i}','yyyy-MM-dd HH:mm:ss',true,'jsrq_${i}')" style="width: 120px"/>
											</td>
											<td>
												<input type="text" name="fdjssjs" id="jsrq_${i}" value="${f.fdjssj }" onchange="jsgs('${i}')" onfocus="showCalendar('jsrq_${i}','yyyy-MM-dd HH:mm:ss',false,'rq_${i}')" maxlength="10" style="width: 120px" />
											</td>
											<td>
												<input type="text" name="gss" value="${f.gs}" id="gs_${i}" maxlength="8" style="width: 50px" readonly="readonly"/>
											</td>
											<td>
												<input type="text" name="fddds" value="${f.fddd}" maxlength="20" style="width: 120px"/> 
											</td>
											<td><input type="text" name="nr" value="${f.fdnr }" maxlength="100" style="width: 120px"/></td>
											<td>
												<input type="text" name="fdpjs" value="${f.fdpj}" maxlength="20" style="width: 80px"/>
											</td>
											<td>
												<div style="position:relative;">
													<textarea name="bz"  rows="6" onfocus="yc(this);" onblur="xs(this);checkLen(this,500);" style="width: 150px">${f.fdbz }</textarea>
													<div id="note_${i}" class="note" style="display: none">
														<font color="#777">1、本次辅导意见</font></br>
														<font color="#777">2、对辅导学员今后的建议</font></br>
													</div>
												</div>
											</td>
											<td><button type="button" id="but" onclick="delThis(this);" style="width: 65px">删除</button></td>
							      		</tr>
										</logic:iterate>
									</logic:notEqual>
									<logic:equal name="size" value="0">
										<tr name="fdnr">
											<td>
												<input type="hidden" name="id" id="id" value=""/>
<%--												<input type="text" name="rq" id="rq_0" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss:ss'})" style="width: 120px" maxlength="10"/>--%>
													<input type="text" name="rq" id="rq_0" onchange="jsgs('0')" onfocus="showCalendar('rq_0','yyyy-MM-dd HH:mm:ss'),true,'jsrq_0'" style="width: 120px" maxlength="10"/>
											</td>
											<td>
<%--												<input type="text" name="fdjssj" id="jsrq_0" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss:ss'})" style="width: 120px" maxlength="10"/>--%>
													<input type="text" name="fdjssjs" id="jsrq_0" onchange="jsgs('0')" onfocus="showCalendar('jsrq_0','yyyy-MM-dd HH:mm:ss',false,'rq_0')" style="width: 120px" maxlength="10"/>
											</td>
											<td>
												<input type="text" name="gss" style="width: 50px" maxlength="4" id="gs_0" readonly="readonly"/>
											</td>
											<td>
												<input type="text" name="fddds" style="width: 120px" maxlength="20"/> 
											</td>
											<td>
												<input type="text" name="nr" style="width: 120px" maxlength="100"/>
											</td>
											<td>
												<input type="text" name="fdpjs" style="width: 80px" maxlength="20"/>
											</td>
											<td>
												<div style="position:relative;">												
													<textarea name="bz" rows="6" onblur="xs(this);checkLen(this,500);" style="width: 150px" onfocus="yc(this);"></textarea>
														<div id="note" class="note">
					            							<font color="#777">1、本次辅导意见</font></br>
					            							<font color="#777">2、对辅导学员今后的建议</font></br>
	        											</div>
												</div>
											</td>
											<td><button type="button" id="but" onclick="delThis(this);" style="width: 65px">删除</button></td>
							      		</tr>
									</logic:equal>
								</table>
							</td>							
						</tr>						
					</tbody>
				 </table>			
				</div>
			  <div style="height:35px"></div>   
			  <div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
				<tfoot>
					<tr>
						<td colspan="4">
							<div class="bz">
								"<span class="red">*</span>"为必填项
							</div>
							<div class="btn">
									<button type="button" onclick="saveFdgl('save');">
										保存
									</button>
									<button type="button" onclick="iFClose();">
										关闭
									</button>
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

