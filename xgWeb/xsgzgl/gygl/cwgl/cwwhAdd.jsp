<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/head.ini"%>
	
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
	<script type='text/javascript' src="js/uicomm.js"></script>
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
			var obj = {lddm:jQuery('#lddm').val(),qsh:jQuery('#qsh').val(),random:Math.random()};
			
			jQuery.getJSON('gyglnew_cwgl.do?method=getCwhForQs', obj, function(data){
				if(data != null){
					maxcwh = data.maxcwh;
					if(maxcwh == null || maxcwh==""){
						maxcwh = 0;
					}
					if(maxcwh != ""){
						if("10698"==jQuery("#xxdm").val()){
							jQuery('#ts_cwh').html("当前寝室最大床位号为" + maxcwh);
						}else{
							jQuery('#cwh').val(parseInt(maxcwh)+1);
							jQuery('#ts_cwh').html("当前寝室最大床位号为" + maxcwh);
						}
					}
				}	
			});
		}
		
		jQuery(function(){
			loadLdxx();
		})		
	</script>
</head>
<body >
	<html:form action="/gyglnew_cwgl" method="post">
		<input type="hidden" name="xyV" id="xyV" value="" />
		<input type="hidden" name="zyV" id="zyV" value="" />
		<input type="hidden" name="bjV" id="bjV" value="" />
		<input type="hidden" name="njV" id="njV" value="" />
		<input type="hidden" id="xxdm" name="xxdm" value='${xxdm}'/>
		<%--<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>床位信息维护</a>
			</p>
		</div>		
		--%><div class="prompt" id="span_cwh" style="display: none">
	       <h3><span>提示：</span></h3>
	       <p>该床位在本楼栋本寝室下已存在！<br/></p>
	   	</div>
		
		<div class="tab">
		<table class="formlist" width="95%">
			<thead>
				<tr style="height:22px">
					<th colspan="4">
						<span>床位信息</span>
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
			<%--辽宁机电职业技术学院  床位号可以写中文个性化--%>
			<logic:notEqual name="xxdm" value="12898">
			<tr>
				<th width="16%">
					<font color="red">*</font>寝室号				
				</th>
				<td width="34%">
					<html:select property="qsh" styleId="qsh" onchange="getCwh();">
					</html:select>
				</td>
				<th>
					<font color="red">*</font>床位号
				</th>
				<td>
				<logic:equal value="10698" name="xxdm">
					<html:text property="cwh" styleId="cwh" maxlength="2" style="width: 87px" onkeyup="checkABC(this);" 
						onblur="checkExists('xg_gygl_new_cwxxb','lddm||qsh||cwh')"></html:text>
				</logic:equal>
				<logic:notEqual value="10698" name="xxdm">
					<html:text property="cwh" styleId="cwh" maxlength="2" style="width: 87px" onkeyup="checkInputData(this);" 
						onblur="checkExists('xg_gygl_new_cwxxb','lddm||qsh||cwh')"></html:text>
				</logic:notEqual>
					<span id="ts_cwh" style="color: blue"></span>
				</td>
			</tr>
			</logic:notEqual>
			<logic:equal name="xxdm" value="12898">
				<tr>
					<th width="16%">
						<font color="red">*</font>寝室号				
					</th>
					<td width="34%">
						<html:select property="qsh" styleId="qsh">
						</html:select>
					</td>
					<th>
						<font color="red">*</font>床位号
					</th>
					<td>
						<html:text property="cwh" styleId="cwh" maxlength="10" ></html:text>
					</td>
				</tr>
			</logic:equal>
			<tr>
				<th>床位是否保留</th>
				<td>
					<html:select property="sfbl">
						<html:option value="否">否</html:option>
						<html:option value="是">是</html:option>
					</html:select>
				</td>
				<th></th>
				<td></td>
			</tr>		
			<tr>
				<th>
					备注
					<br /><font color="red">(限制在500字内)</font>
				</th>
				<td colspan="3">
					<html:textarea property='bz' style="width:95%" styleId="bz" rows='7' value="${rs.bz}" onblur="chLeng(this,500);"/>
				</td>
			</tr>
			</tbody>
			<tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"为必填项</div>
			          <div class="btn">
			          	<button type="button" name="提交" id="buttonSave"  onclick="saveData('gyglnew_cwgl.do?method=cwwhAdd&doType=save','lddm-qsh-cwh');">保存</button>
			            <button type="button" name="关闭" id="buttonClose" onclick="Close();return false;">关闭</button>
			          </div></td>
			      </tr>
			    </tfoot>
		</table>
		</div>
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
