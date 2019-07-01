<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<link rel="stylesheet" type="text/css" href="js/jquery/themes/default/easyui.css" />
	<link rel="stylesheet" type="text/css" href="js/jquery/themes/icon.css" />
	
	<script language="javascript" src="js/String.js"></script>
	<script language="javascript" src="js/check.js"></script>
	<script language="javascript" src="js/qtsjFunction.js"></script>
	<script type="text/javascript" src="dwr/interface/systemFunction.js"></script>
	
	<script	type="text/javascript">
		function checkExists(tableName, pk){
			var lddm = jQuery('#lddm').val();
			var qsh = jQuery('#qsh').val();
			var cwh = jQuery('#cwh').val();
			var pkV = lddm + qsh + cwh;
			
			dwr.engine.setAsync(false);
			
			systemFunction.checkExists(tableName,pk,pkV,function(data){
				if(data){
					jQuery('#span_cwh').show('normal')
					jQuery('#buttonSave').attr('disabled', 'disabled');
				}else{
					jQuery('#span_cwh').hide('normal')
					jQuery('#buttonSave').removeAttr('disabled');
				}
			});
			dwr.engine.setAsync(true);
		}
	
		function getQsh(){
			jQuery('#cwh').val('');
			jQuery('#ts_cwh').html('');
			
			jQuery.getJSON('gyglnew_cwgl.do?method=getQshForLddm',{lddm:jQuery('#lddm').val(), ch:jQuery('#ch').val()},function(data){
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
			jQuery.getJSON('gyglnew_qsgl.do?method=loadLdxx',{lddm:jQuery('#lddm').val()},function(data){
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
			var obj = {lddm:jQuery('#lddm').val(),qsh:jQuery('#qsh').val()};
			
			jQuery.getJSON('gyglnew_cwgl.do?method=getCwhForQs', obj, function(data){
				if(data != null){
					maxcwh = data.maxcwh;
					if(maxcwh == null || maxcwh==""){
						maxcwh = 0;
					}
					
					if(maxcwh != ""){
						jQuery('#cwh').val(parseInt(maxcwh)+1);
						jQuery('#ts_cwh').html("当前寝室最大床位号为" + maxcwh);
					}
				}	
			});
		}
	</script>
</head>
<body onload="">
	<html:form action="/gzdxWmqs.do?method=qssqManage&doType=save" method="post">
		<input type="hidden" name="lddm" value="${rs.lddm}" />
		<input type="hidden" name="qsh" value="${rs.qsh}" />
		<%--<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>床位信息维护</a>
			</p>
		</div>		
		--%>
		
		<div class="tab">
		<table class="formlist" width="95%">
			<thead>
				<tr style="height:22px">
					<th colspan="4">
						<span>文明寝室申请信息</span>
					</th>
				</tr>
			</thead>
			<tbody>
			<tr>
				<th align="right" width="16%">
					<font color="red"></font>楼栋名称
				</th>
				<td align="left" width="30%" nowrap="nowrap">
					${rs.ldmc }
<%--					<html:select property="lddm" styleId="lddm" onchange="loadLdxx();">--%>
<%--						<html:options collection="ldList" labelProperty="ldmc" property="lddm"/>--%>
<%--					</html:select>--%>
				</td>
				<th width="16%">层号</th>
				<td width="38%">
				${rs.chmc }
<%--					<html:select property="ch" styleId="ch" onchange="getQsh();">--%>
<%--					</html:select>--%>
				</td>
			</tr>

			<tr>
				<th width="16%">
					<font color="red"></font>寝室号				
				</th>
				<td width="34%">
					${rs.qsh }
<%--					<html:select property="qsh" styleId="qsh" onchange="getCwh();">--%>
<%--					</html:select>--%>
				</td>
<%--				<th>--%>
<%--					<font color="red">*</font>寝室人数--%>
<%--				</th>--%>
<%--				<td>--%>
<%--					<html:text property="qsrs" name="rs" styleId="qsrs" onkeyup="checkInputData(this);" maxlength="4"></html:text>--%>
<%--						<bean:write name="rs" property="qsrs"/>--%>
<%--					<span id="ts_cwh" style="color: blue"></span>--%>
<%--				</td>--%>
				<th>
					<font color="red"></font>卫生平均分
				</th>
				<td>
						<bean:write name="rs" property="wspjf"/>
					<span id="ts_cwh" style="color: blue"></span>
				</td>
			</tr>
			<tr>
				<th>
					寝室学生
					<br /><font color="red"></font>
				</th>
				<td colspan="3">
					<table width="100%">
						<tr>
							<th>学号</th>
							<th>姓名</th>
							<th><bean:message key="lable.xb" /></th>
							<th>专业</th>
							<th>班级</th>
						</tr>
						<logic:present name="xsList">
						<logic:iterate id="xs" name="xsList">
						<tr>
							<td><bean:write name="xs" property="xh"/></td>
							<td><bean:write name="xs" property="xm"/></td>
							<td><bean:write name="xs" property="xymc"/></td>
							<td><bean:write name="xs" property="zymc"/></td>
							<td><bean:write name="xs" property="bjmc"/></td>
						</tr>
						</logic:iterate>
						</logic:present>
					</table>
				</td>
			</tr>
			<tr>
				<th>
					申请说明
					<br /><font color="red">(限制在500字内)</font>
				</th>
				<td colspan="3">
					<html:textarea property='sqsm' name="rs" style="width:615px;" styleId="sqsm" rows='7' value="${rs.sqsm}" onblur="chLeng(this,500);"/>
				</td>
			</tr>
			</tbody>
			<tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"为必填项</div>
			          <div class="btn">
			          	<button type="button" name="提交" id="buttonSave"  onclick="saveData('gzdxWmqs.do?method=qssqManage&doType=save','lddm-qsh-cwh');">保存</button>
<%--			            <button type="button" name="关闭" id="buttonClose" onclick="window.close();return false;">关闭</button>--%>
			          </div></td>
			      </tr>
			    </tfoot>
		</table>
		</div>
	</html:form>
	<logic:present name="back">
		<input type="hidden" id="message" value="${message }" />
		<script type="text/javascript">
			alertInfo("${back}");
		</script>
	</logic:present>
</body>
</html>
