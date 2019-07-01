<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/head.ini"%>
	<script type='text/javascript' src="js/uicomm.js"></script>
	<script type='text/javascript' src="js/String.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
	
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
			var pkV = lddm + qsh;
			
			dwr.engine.setAsync(false);
			
			systemFunction.checkExists(tableName,pk,pkV,function(data){
				if(data){
					jQuery('#span_qsh').show('normal')
					jQuery('#buttonSave').attr('disabled', 'disabled');
				}else{
					jQuery('#span_qsh').hide('normal')
					jQuery('#buttonSave').removeAttr('disabled');
				}
			});
			dwr.engine.setAsync(true);
		}

		function loadLdxx(){
			jQuery('#qsh').val('');
			jQuery('#cws').val('');
			jQuery('#ts_qsh').html('');
			jQuery('#ts_cws').html('');
			
			
			jQuery.post('gyglnew_qsgl.do?method=loadLdxx',{lddm:jQuery('#lddm').val()},function(data){
				var qsch = parseInt(data.qsch);
				var ldcs = parseInt(data.ldcs);
				var sfhlc = data.sfhlc;
				var ldxb = data.ldxb;
				
				$('ldxb').innerHTML = data.ldxb;
				$('qsch').innerHTML = qsch;
				$('ldcs').innerHTML = ldcs;
				$('sfhlc').innerHTML = sfhlc;
				
				if("混住" == ldxb){
					jQuery('#qsxb').attr('disabled','disabled');
					jQuery('input[type=radio][name=qsxb]').removeAttr('disabled');
				}else{
					jQuery('#qsxb').val(ldxb);
					jQuery('input[type=radio][name=qsxb][value='+ldxb+']').attr('checked','true');
					jQuery('input[type=radio][name=qsxb]').attr('disabled','disabled');
					jQuery('#qsxb').removeAttr('disabled');
				}
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
				
			},'json');
			
			checkExists('xg_gygl_new_qsxxb','lddm||qsh');
		}
		
		function loadQs(){
			var obj = new Object;
			obj.lddm = jQuery('#lddm').val();
			obj.ch = jQuery('#ch').val();
			obj.random=Math.random();
			
			jQuery.getJSON('gyglnew_qsgl.do?method=loadQsxx',obj,function(data){
				var maxQsh = data.maxqsh;
				var maxCws = data.maxcws;
				var qsxb = data.qsxb;
				jQuery("#qsxb").val(qsxb);
				jQuery('input[type=radio][value='+qsxb+']').attr('checked','true');
				jQuery('input[type=radio]').attr('disabled','disabled');
				jQuery('#qsxb').removeAttr('disabled');
				jQuery('input[name=ywkt]').removeAttr('disabled');
				if(maxQsh != null && maxQsh != ""){
					var ts = "当前层最大寝室号为" + maxQsh;
					jQuery('#ts_qsh').html(ts);
					
					var wh = "0" + (parseInt(maxQsh.substring(maxQsh.length - 2))+1);
					var wh = wh.length == 2 ? wh : wh.substring(1);
					var qsh = maxQsh.substring(0,maxQsh.length-2) + wh;
					jQuery('#qsh').val(qsh);
				}
				jQuery('#cws').val(maxCws);
				if(maxCws==null||maxCws==""){
					maxCws="0";
				}
				jQuery('#ts_cws').html('当前层最大床位数为'+maxCws);
			});
		}

		function checkChar(obj){
			if (obj.value.match(/[\u4e00-\u9fa5]/g)) {
				alertInfo("只能输入字符,不能输入中文!",function (){obj.value="";});
		
				//return false;
			 }	
		}
		jQuery(function(){
			loadLdxx();
		})

	</script>
</head>
<body>
	<html:form action="/gyglnew_qsgl" method="post">		
		<div class="prompt" id="span_qsh" style="display: none">
	       <h3><span>提示：</span></h3>
	       <p>该寝室号在本楼栋下已存在！<br/></p>
	   	</div>
		
		<div class="tab" style="overflow-x:hidden;overflow-y:auto;height:410px;margin-bottom: 0px;">
		<table class="formlist" width="95%">
			<thead>
				<tr style="height:22px">
					<th colspan="4">
						<span>楼栋信息</span>
					</th>
				</tr>
			</thead>
			<tbody>
			<tr>
				<th align="right" width="16%">
					<font color="red">*</font>楼栋名称
				</th>
				<td align="left" width="34%" nowrap="nowrap">
					<html:select property="lddm" styleId="lddm" onchange="loadLdxx();">
						<html:options collection="ldList" labelProperty="ldmc" property="lddm"/>
					</html:select>
				</td>
				<th width="16%">
					楼栋性别
				</th>
				<td width="34%" id="ldxb">
				</td>
			</tr>
			<tr>
				<th>楼栋层数</th>
				<td id="ldcs"></td>
				<th>楼栋起始层</th>
				<td id="qsch"></td>
			</tr>
			<tr>
				<th>是否含0层</th>
				<td id="sfhlc"></td>
				<th></th>
				<td></td>
			</tr>
			</tbody>
			
			<thead>
				<tr style="height:22px">
					<th colspan="4">
						<span>寝室信息</span>
					</th>
				</tr>
			</thead>
			
			<tbody>
			<tr>
				<th><font color="red">*</font>层号</th>
				<td>
					<html:select property="ch" styleId="ch" onchange="loadQs();">
					</html:select>
				</td>
				<th>
					<font color="red">*</font>寝室号				
				</th>
				<td>
					<html:text property="qsh" styleId="qsh" maxlength="8" styleClass="text_nor"
					onblur="checkChar(this);checkExists('xg_gygl_new_qsxxb','lddm||qsh');" style="width:87px"></html:text>
					<span id="ts_qsh" style="color:blue"></span>
				</td>
			</tr>
			<tr>
				<th><font color="red">*</font>床位数</th>
				<td>
					<html:text property="cws" styleId="cws" maxlength="2" onkeyup="checkInputData(this);"
						style="width:87px" styleClass="text_nor"></html:text>
					<span id="ts_cws" style="color:blue"></span>
				</td>	
				<th>寝室性别</th>
				<td>
					<input type="hidden" id="qsxb" name="qsxb" value=""/>
					<html:radio disabled="true" property="qsxb" value="男">男</html:radio>
					&nbsp;&nbsp;
					<html:radio disabled="true" property="qsxb" value="女">女</html:radio>
				</td>
			</tr>
			<tr>
				<th><font color="red">*</font>收费标准</th>
				<td>
					<html:text property="sfbz" styleId="sfbz" maxlength="5" 
						styleClass="text_nor" onkeyup="checkInputData(this);"></html:text>
				</td>
				<th>寝室电话</th>
				<td>
					<html:text property="qsdh" styleId="qsdh" maxlength="20" onblur="checkPhone(this);" styleClass="text_nor"></html:text>
				</td>
			</tr>
			<tr>
				<th>有无空调</th>
				<td>
					<div>
						<html:radio property="ywkt" value="无" styleId="ywkt" >无</html:radio>
						<html:radio property="ywkt" value="有" styleId="ywkt">有</html:radio>
					</div>
				</td>
			</tr>
			<tr>
				<th>
					备注
					<br/><font color="red">(限制在1000字内)</font>
				</th>
				<td colspan="3">
					<html:textarea property='bz' style="width:95%" styleId="bz" rows='7' onblur="chLeng(this,1000);"/>
				</td>
			</tr>
			</tbody>
		</table>
		</div>
		<table class="formlist" width="95%">
					<tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"为必填项</div>
			          <div class="btn">
			          	<button type="button" name="提交" id="buttonSave" onclick="saveData('gyglnew_qsgl.do?method=qswhAdd&doType=save','qsh-cws-ch-sfbz');return false;">保存</button>
			            <button type="button" name="关闭" id="buttonClose" onclick="Close();return false;">关闭</button>
			          </div></td>
			      </tr>
			    </tfoot>
		</table>
	</html:form>
	<logic:present name="message">
		<input type="hidden" id="message" value="${message }" />
		<script type="text/javascript">				
			 showAlert("保存成功",{},{"clkFun":function(){
 				if (parent.window){
 					refershParent();
 				}
 			}});
			
		</script>
	</logic:present>
</body>
</html>
