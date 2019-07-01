<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/head.ini"%>
	
	<script type="text/javascript" src="js/calendar/calendar.js"></script>
	<script type="text/javascript" src="dwr/interface/systemFunction.js"></script>
	<script type="text/javascript" src="xsgzgl/gygl/ydgl/js/ydxx.js"></script>
	<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
	<script	type="text/javascript">
		function getQsh(){
				jQuery.getJSON('ydXxgl.do?method=getQshForLddm',
						{lddm:jQuery('#lddm').val(), ch:jQuery('#ch').val()},function(data){
				jQuery('#qsh').empty();
				jQuery('#qsh').append("<option value=''>--请选择--</option>");
				if(data != null && data.length != 0){
					for(var i=0; i<data.length; i++){
						var option = "<option value=\"" + data[i].qsh + "\">" + data[i].qsh + "</option>";
						jQuery('#qsh').append(option);
					}
				}
			});
		}
		
		function loadLdxx(){
				jQuery.getJSON('ydXxgl.do?method=loadLdxx',
						{lddm:jQuery('#lddm').val()},function(data){
				var qsch = parseInt(data.qsch);
				var ldcs = parseInt(data.ldcs);
				var sfhlc = data.sfhlc;
				var ldxb = data.ldxb;
				var count = 0;
				
				jQuery('#ch').empty();
				jQuery('#ch').append("<option value=''>--请选择--</option>");
				while(count<ldcs){
					var chdm;
					var chmc;
					if('否' == sfhlc){
						if((qsch+count)>= 0){
							chdm = qsch>0 ? qsch+count:qsch+count+1;
							chmc = chdm + "层";
						}else{
							chdm = qsch+count;
							chmc = "B" + Math.abs(chdm) + "层";	
						}
					}else{
						chdm = qsch+count;
						chmc = chdm<0 ? "B" + Math.abs(chdm) + "层" : chdm + "层";
					}
					var option = "<option value=\"" + chdm + "\">" + chmc + "</option>"
					jQuery('#ch').append(option);
					count ++;
				}
				getQsh();
			});
		}
		
		function getCwh(){
			var obj = {lddm:jQuery('#lddm').val(),qsh:jQuery('#qsh').val(),random:Math.random()};
			
			jQuery.getJSON('ydXxgl.do?method=getCws', obj, function(data){
				if(data != null){
					cws = data['cws'];
					if(cws != ""){
						jQuery('#cws').html(cws);
					}
				}	
			});
			getcwxslist(jQuery('#lddm').val()+jQuery('#qsh').val());//床位信息联动
		}
		
		function getcwxslist(ldqsxx){
			jQuery("#xsList").empty();
			var html = "";
			jQuery.post("ydXxgl.do?method=getCwxx", {
				ldqsxx : ldqsxx
			}, function(data) {
				for(var i =0;i<data.length;i++){
					html+="<tr><td align='center'>"+data[i]["xh"]+"</td><td align='center'>"+data[i]["xm"]+"</td><td align='center'>"+data[i]["xsbjmc"]+"</td><td align='center'>"+data[i]["cwh"]+"</td></tr>";
				}
				jQuery("#xsList").append(html);
			}, 'json');
		}
		jQuery(function(){
			loadLdxx();
		})		
	</script>
</head>
<body >
	<html:form action="/ydXxgl" styleId="ydxxForm"  method="post">
	<div class="tab" style="overflow-x:hidden;overflow-y:auto;height:410px;margin-bottom: 0px;">
		<table class="formlist" width="95%">
			<thead>
				<tr style="height:22px">
					<th colspan="4">
						<span>电费使用情况</span>
					</th>
				</tr>
			</thead>
			<tbody>
			<tr>
				<th align="right" width="16%">
					<font color="red">*</font>楼栋名称
				</th>
				<td align="left" width="30%" nowrap="nowrap">
					<html:select property="lddm" styleId="lddm" onchange="loadLdxx();">
						<html:options collection="ldList" labelProperty="ldmc" property="lddm"/>
					</html:select>
				</td>
				<th width="16%">层号</th>
				<td width="38%">
					<html:select property="ch" styleId="ch" onchange="getQsh();">
					</html:select>
				</td>
			</tr>

			<tr>
				<th width="16%">
					<font color="red">*</font>寝室号				
				</th>
				<td width="34%">
					<html:select property="qsh" styleId="qsh" onchange="getCwh();">
					</html:select>
				</td>
				<th width="16%">
					寝室人数			
				</th>
				<td width="34%" id="cws"></td>
			</tr>
			<tr>
				<th width="16%">
					<font color="red">*</font>用电年月				
				</th>
				<td width="34%">
				<html:text property="ydyf" styleId="ydyf"  style="width:50%" onfocus="return showCalendar('ydyf','yyyy-MM');" />
							
				</td>
				<th width="16%">
					<font color="red">*</font>用电度数			
				</th>
				<td width="34%"><html:text property="ds" styleId="ds" onkeyup="checkNumBer(this)"
						style="width:87px" styleClass="text_nor"></html:text>
					</td>
			</tr>
			<tr>
				<th width="16%">
					<font color="red">*</font>电费				
				</th>
					<td width="34%"><html:text property="df" styleId="df"  onkeyup="checkNumBer(this)"
						style="width:87px" styleClass="text_nor"></html:text>
					</td>
				<th width="16%">
					<font color="red">*</font>电费余额		
				</th>
				<td width="34%"><html:text property="dfye" styleId="dfye" onkeyup="checkNumBer(this)"
						style="width:87px" styleClass="text_nor"></html:text>
					</td>
			</tr>
			<tr>
				<th width="16%" rowspan="4">
					备注
					<br><font color="red">（限500字）</font><br/>
				</th>
				<td width="34%" colspan="3" rowspan="4">
					<html:textarea property="bz" onblur="chLengs(this,500);" styleId="bz" rows="4" style="width:90%" ></html:textarea>
				</td>
			</tr>
			</tbody>
			</table>
			<h3 class="datetitle_01">
				<span>床位信息</span>
			</h3>
			<table>
		
			
			</table>
			<table class="formlist">
					<thead>
						<tr>
							<th>
								<div align="center" >学号</div>
							</th>
							<th>
								<div align="center" >姓名</div>
							</th>
							<th>
								<div align="center" >班级</div>
							</th>
							<th>
								<div align="center" >入住床位</div>
							</th>
						</tr>
					</thead>
					<tbody id="xsList">
						
					</tbody>
					
				</table>
				</div>
			<table class="formlist" width="97%">
			<tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"为必填项</div>
			          <div class="btn">
			          	<button type="button" name="提交" id="buttonSave"  onclick="save()">保存</button>
			            <button type="button" name="关闭" id="buttonClose" onclick="Close();return false;">关闭</button>
			          </div></td>
			      </tr>
			    </tfoot>
		</table>
	
	</html:form>
	<logic:present name="message">
		<input type="hidden" id="message" value="${message }" />
		<script type="text/javascript">
		 showAlert("操作成功",{},{"clkFun":function(){
				if (parent.window){
					refershParent();
				}
			}});
		</script>
	</logic:present>
</body>
</html>
