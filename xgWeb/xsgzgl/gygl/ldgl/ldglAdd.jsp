<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<jsp:directive.page import="java.util.HashMap" />
<jsp:directive.page import="xgxt.action.Base" />
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type='text/javascript' src="js/uicomm.js"></script>
		<script type='text/javascript' src="js/String.js"></script>
		
		<script type='text/javascript'
			src='dwr/interface/GetListData.js'></script>
		<script type='text/javascript' src='dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
		<script type="text/javascript" src="dwr/interface/gyglGywh.js"></script>	
		<script type='text/javascript' src='dwr/interface/getCommGygl.js'></script>
		<script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/qtsjFunction.js"></script>
		<script type="text/javascript" src="dwr/interface/systemFunction.js"></script>
		<script type="text/javascript">
		function checkExists(tableName, pk){
			var lddm = jQuery('#lddm').val();
			var pkV = lddm;
			
			dwr.engine.setAsync(false);
			
			systemFunction.checkExists(tableName,pk,pkV,function(data){
				if(data){
					jQuery('#span_qsh').show('normal')
					jQuery('#btn_bc').attr('disabled', 'disabled');
				}else{
					jQuery('#span_qsh').hide('normal')
					jQuery('#btn_bc').removeAttr('disabled');
				}
			});
			dwr.engine.setAsync(true);
		}
		
		function save(){
			if($("xqdm")&&$("xqdm").value==""){
				alertInfo("请选择校区！");
				return false;
			}
			if($("yqdm")&&$("yqdm").value==""){
				alertInfo("请选择"+jQuery("#yqmc").val()+"！");
				return false;
			}
			if($("lddm").value==""){
				alertInfo("楼栋代码不能为空!");
				return false;
			}
			if ($("lddm").value.match(/[\u4e00-\u9fa5]/g)) {
				alertInfo("楼栋代码,不能输入中文!");
				return false;
		 	}
			if($("ldmc").value==""){
				alertInfo("楼栋名称不能为空!");
				return false;
			}
			//验证楼栋性别
			var ldxb;
			var b=false;//楼栋性别
			var ldxb_radio_temp=document.getElementsByName("ldxb");
			for(var i=0;i<ldxb_radio_temp.length;i++){
				if(ldxb_radio_temp[i].checked){
					ldxb=ldxb_radio_temp[i].value;
					b=true;
					break;
				}
			}
			if(!b){
				alertInfo("请选择楼栋性别!");
				return false;
			}
			//验证生成寝室数
			b=false;
			var re_num = /^[\d]+$/;
			var scqss=document.getElementsByName("scqss");
			for(var i=0;i<scqss.length;i++){
				if(scqss[i].value.trim()==""){
					b=true;
					break;
				}else if(!re_num.test(scqss[i].value)){
					alertInfo("楼栋寝室数必须为数字!");
					return false;
				}
			}
			if(b){
				alertInfo("楼层寝室数不可以为空!");
				return false;
			}
			//验证生成寝室数
			b=false;
			var cws=document.getElementsByName("cws");
			for(var i=0;i<cws.length;i++){
				if(cws[i].value.trim()==""){
					b=true;
					break;
				}else if(!re_num.test(scqss[i].value)){
					alertInfo("床位数必须为数字!");
					return false;
				}
			}
			if(b){
				alertInfo("床位数不可以为空!");
				return false;
			}
			//验证收费标准
			b=false;
			var sfbz=document.getElementsByName("sfbz");
			for(var i=0;i<sfbz.length;i++){
				if(sfbz[i].value.trim()==""){
					b=true;
					break;
				}else if(!re_num.test(scqss[i].value)){
					alertInfo("收费标准必须为数字!");
					return false;
				}
			}
			if(b){
				alertInfo("收费标准不可以为空!");
				return false;
			}
			//验证寝室性别
			if("混住"==ldxb){
				var ldcs=$("ldcs").value;//楼栋层数
				var qsxb;
				for(var i=0;i<parseInt(ldcs);i++){
					qsxb=document.getElementsByName("qsxb"+i);
					if(!qsxb[0].checked&&!qsxb[1].checked){
						alertInfo("请选择寝室性别！");
						return false;	
					}
				}
			}
			
			
			var url="gyglnew_ldgl.do?method=ldxxwh&doType=save";
			refreshForm(url);
		}
		function modi(){
			var url="gyglnew_ldgl.do?method=ldxxwh&doType=modi";
			refreshForm(url);
		}
		//创建楼栋
		function createLd(){
			var ldxb="男";//楼栋性别
			var ldxb_radio_temp=document.getElementsByName("ldxb");
			for(var i=0;i<ldxb_radio_temp.length;i++){
				if(ldxb_radio_temp[i].checked){
					ldxb=ldxb_radio_temp[i].value;
					break;
				}
			}
			var ldcs=$("ldcs").value;//楼栋层数
			var qsch=parseInt($("qsch").value);//起始层号
			var sfhlc=$("sfhlc").value;//是否含零层
			if(ldxb==""||ldcs==""){
				return false;
			}
			
			var ld_table=$("ld_table");//楼栋table
			var ld_trs=ld_table.getElementsByTagName("TR");
			for(var i=ld_trs.length-1;i>1;i--){//移除原先创建的信息
				ld_table.deleteRow(i);
			}
			var b_mark;//负层B标记
			var ch_value;//层号值
			var ch_name;//层号名称
			var sfhlc_mark=(qsch>0||sfhlc=="是"?0:1);//是否含零层标记//含零层正常计算，不含零层需要增量
			var top_mark=true;//顶层标记
			for(var i=parseInt(ldcs)-1;i>-1;i--){//根据设置的参数创建楼栋var i=0;i<parseInt(ldcs);i++
				var r=ld_table.insertRow(parseInt(ldcs)-1-i+2);//ld_table.insertRow(i+2);
				if(i+qsch<0){
					b_mark="B";
					ch_value=i+qsch;
					ch_name=-(i+qsch);
				}else{
					b_mark="";
					ch_value=i+qsch+sfhlc_mark;
					ch_name=i+qsch+sfhlc_mark;
				}
				
				r.insertCell(0).innerHTML='<input type="hidden" name="ch" value="'+ch_value+'"/>'+b_mark+ch_name+"层/0";
				
				r.insertCell(1).innerHTML='<input type="text" style="width:100px" name="scqss" maxlength="2"'+(top_mark?' onkeyup="checkInputData(this);plsr(this);"':'')+'/>';
				r.insertCell(2).innerHTML='<input type="text" style="width:100px" name="cws" maxlength="2"'+(top_mark?' onkeyup="checkInputData(this);plsr(this);"':'')+'/>';
				r.insertCell(3).innerHTML='<input type="text" style="width:100px" name="sfbz" maxlength="4"'+(top_mark?' onkeyup="checkInputData(this);plsr(this);"':'')+'/>';
				if(ldxb=="男"){
					r.insertCell(4).innerHTML='<input type="radio" disabled="disabled" checked="checked" value="男"/>男<input type="radio" disabled="disabled" value="女"/>女';
				}else if(ldxb=="女"){
					r.insertCell(4).innerHTML='<input type="radio" disabled="disabled" display="none;" value="男"/>男<input type="radio" disabled="disabled" checked="checked" value="女"/>女';
				}else{
					r.insertCell(4).innerHTML='<input type="radio" name="qsxb'+i+'" checked="checked" value="男"'+(top_mark?' onclick="click_qsxb(this);"':'')+'/>男<input type="radio" name="qsxb'+i+'" value="女"'+(top_mark?' onclick="click_qsxb(this);"':'')+'/>女';
				}
				top_mark=false;
			}
		}
		//批量输入
		function plsr(obj){
			if($("plsr_checkbox").checked){
				var objs=document.getElementsByName(obj.name);
				for(var i=1;i<objs.length;i++){
					objs[i].value=obj.value;
				}
			}
		}
		//点击批量控制性别
		function click_qsxb(obj){
			if($("plsr_checkbox").checked){
				var ldcs=$("ldcs").value;//楼栋层数
				for(var i=1;i<ldcs;i++){
					if(obj.value=="男"){
						document.getElementsByName("qsxb"+i)[0].checked="checked";
					}else{
						document.getElementsByName("qsxb"+i)[1].checked="checked";
					}
				}
			}
		}

		function show_sfhlc(qsch){
			if(qsch>=0){
				if(qsch==0){
					$("sfhlc").value="是";
				}else{
					$("sfhlc").value="否";
				}
				$("sfhlc_lable").style.display="none";
				$("sfhlc_obj").style.display="none";
			}else{
				$("sfhlc_lable").style.display="";
				$("sfhlc_obj").style.display="";
			}
		}
		//根据校区加载园区相关信息
		function loadYqxx(){
			if(!$("yqdm")){
				return false;
			}
			jQuery.post('gyglnew_ldgl.do?method=loadYqxx',{xqdm:jQuery('#xqdm').val()},function(data){
				jQuery('#yqdm').empty();
				//jQuery('#yqdm').append("<option value=''>--请选择--</option>");
				jQuery('#yqdm').append(data);
			});
		}

		function showSzm(){
			if(jQuery("[name='qsh_sfhszm']").prop("checked")){
				jQuery("#qsh_szm").css("display","");
			}else{
				jQuery("#qsh_szm").css("display","none");
				//$("qsh_szm").style.display = "none";
			}
		}
		/**
		 * 只能输入半角字符
		 * @param obj
		 * @return
		 */
		function onlyCaseChar(obj) {
			var str = obj.value;
			var result = "";
			for ( var i = 0; i < str.length; i++) {
				if (str.charCodeAt(i) == 12288) {
					result += String.fromCharCode(str.charCodeAt(i) - 12256);
					continue;
				}
				if (str.charCodeAt(i) > 65280 && str.charCodeAt(i) < 65375)
					result += String.fromCharCode(str.charCodeAt(i) - 65248);
				else
					result += String.fromCharCode(str.charCodeAt(i));
			}
			obj.value = result;
		}
		
		
		</script>
	</head>
	<body>
		<html:form action="/gyglnew_ldgl" method="post">
			<logic:present name="rs">
			<script type="text/javascript">
			jQuery(function(){
				createLd();
			})
			</script>
			<input type="hidden" name="czxq" id="czxq" value="${czxq }"/>
			<input type="hidden" name="czyq" id="czyq" value="${czyq }"/>
			<input type="hidden" name="yqdmHid" id="yqdmHid" value="${rs.yqdm}"/>
			<input type="hidden" name="xqdmHid" id="xqdmHid" value="${rs.xqdm}"/>
			<input type="hidden" id="yqmc" value="<bean:message key="lable.yuanqu" />"/>
			<div class="prompt" id="span_qsh" style="display: none">
		       <h3><span>提示：</span></h3>
		       <p>该楼栋已存在！<br/></p>
		   	</div>
			<div class="tab" style="tab;height:460px;overflow-x:hidden;overflow-y:auto;">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span><bean:message key="lable.ld" />信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<logic:notEqual value="0" name="xqbj">
							<th style="width:10%">
								<font color="red">*</font>
								校区
							</th>
							<td  style="width:40%">
								<html:select property="xqdm" name="rs" styleId="xqdm" onchange="loadYqxx()">
									<html:option value=""></html:option>
									<html:optionsCollection name="xqlist" label="xqmc" value="dm"/>
								</html:select>
							</td>
							</logic:notEqual>
							<logic:notEqual value="0" name="yqbj">
							<th>
								<font color="red">*</font>
								<bean:message key="lable.yuanqu" />
							</th>
							<td>
								<html:select property="yqdm" name="rs" styleId="yqdm">
									<html:option value=""></html:option>
									<html:optionsCollection name="yqlist" label="yqmc" value="yqdm"/>
								</html:select>
							</td>
							</logic:notEqual>
						</tr>
						<tr>
							<th style="width:14%">
								<font color="red">*</font>
								楼栋代码
							</th>
							<td style="width:36%">
								<html:text property="lddm" styleId="lddm" name="rs" onblur="checkExists('xg_gygl_new_ldxxb','lddm');onlyCaseChar(this)" maxlength="20"></html:text>
							</td>
							<th style="width:14%">
								<font color="red">*</font>
								楼栋名称
							</th>
							<td style="width:36%">
								<html:text property="ldmc" styleId="ldmc" maxlength="100"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>
								<bean:message key="lable.ld" />性别
							</th>
							<td>
								<html:radio property="ldxb" value="男" name="rs" onclick="createLd();">男</html:radio>
								<html:radio property="ldxb" value="女" name="rs" onclick="createLd();">女</html:radio>
								<html:radio property="ldxb" value="混住" name="rs" onclick="createLd();">混住</html:radio>
							</td>
							<th>
								<font color="red">*</font>
								<bean:message key="lable.ld" />层数
							</th>
							<td>
								<html:select property="ldcs" name="rs" styleId="ldcs" onchange="createLd();">
									<html:optionsCollection name="ldcsList" label="mc" value="dm" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>
								<bean:message key="lable.ld" />起始层
							</th>
							<td>
								<html:select property="qsch" name="rs" styleId="qsch" onchange="createLd();show_sfhlc(this.value);">
									<html:option value="5">5</html:option>
									<html:option value="4">4</html:option>
									<html:option value="3">3</html:option>
									<html:option value="2">2</html:option>
									<html:option value="1">1</html:option>
									<html:option value="0">0</html:option>
									<html:option value="-1">-1</html:option>
									<html:option value="-2">-2</html:option>
									<html:option value="-3">-3</html:option>
									<html:option value="-4">-4</html:option>
								</html:select>
							</td>
							<th>
								<span id="sfhlc_lable" style="display: none;">是否含0层</span>
							</th>
							<td>
								<span id="sfhlc_obj" style="display: none;">
								<html:select property="sfhlc" name="rs" styleId="sfhlc" onchange="createLd();">
									<html:option value="否">否</html:option>
									<html:option value="是">是</html:option>
								</html:select>
								</span>
							</td>
						</tr>
						<tr>
							<th>
								<bean:message key="lable.ld" />备注<br/><font color="red">(限1000字)</font>
							</th>
							<td colspan="3">
								<html:textarea property="bz" name="rs" rows="3" style="word-break:break-all;width:95%" onblur="chLeng(this,1000);"></html:textarea>
							</td>
						</tr>
					</tbody>
				</table>	
				<div class="border_01 formbox">
					<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="5">
								<span>维护需要生成的寝室情况</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td colspan="5">
								寝室号生成规则：
								<input type="checkbox" name="qsh_sfhszm" value="是" onclick="showSzm();"/>首字母 
								<input type="text" name="qsh_szm" id="qsh_szm" maxlength="4" style="width: 50px;display: none"/>
								&nbsp;&nbsp;&nbsp;&nbsp;<strong>+</strong>&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="radio" name="qsh_ws" value="3" checked="checked"/>三位
								<input type="radio" name="qsh_ws" value="4"/>四位
							</td>
						</tr>
					</tbody>
					</table>
					<!--带有滚动条表单 标题end-->
					<div style="height: 250px;overflow: auto;width: 100%">
						<table width="100%" border="0" class="datelist tablenowrap" id="ld_table">
							<thead>
								<tr>
									<td colspan="5">
										<input type="checkbox" id="plsr_checkbox" checked="checked" />默认按起始层设置生成寝室数、床位数及收费标准
									</td>
								</tr>
								<tr>
									<th style="width:20%">
										层号/已有寝室数
									</th>
									<th style="width:20%">
										生成寝室数
									</th>
									<th style="width:20%">
										单个寝室床位数
									</th>
									<th style="width:20%">
										收费标准（元）
									</th>
									<th style="width:20%">
										寝室性别
									</th>
								</tr>
							</thead>
						</table>
					</div>
				</div>
			</div>
				<div>
				<table width="100%" border="0" class="formlist">					
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									<font color="blue">
										注：带"<font color="red">*</font>"的信息必须录入
									</font>
								</div>
								<div class="btn">
									<logic:equal name="doType" value="add">
										<button type="button" class="button2" id="btn_bc"  onclick="save();return false;">
											保 存
										</button>
									</logic:equal>
									<logic:equal name="doType" value="update">
										<button type="button" class="button2" id="btn_bc"  onclick="modi()">
											保 存
										</button>
									</logic:equal>
									<button type="button" class="button2"  onclick="Close();return false;">
										关 闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
				
			</div>

			</logic:present>
			<logic:notEmpty name="result">
				<logic:equal value="false" name="result">
					<script language="javascript">
					
					 showAlert("操作失败",{},{"clkFun":function(){
		    				if (parent.window){
		    					refershParent();
		    				}
		    			}});
					</script>
				</logic:equal>
				<logic:equal value="true" name="result">
					<script language="javascript">
					 showAlert("操作成功",{},{"clkFun":function(){
		    				if (parent.window){
		    					refershParent();
		    				}
		    			}});
					
					</script>
				</logic:equal>
			</logic:notEmpty>
		</html:form>
	</body>
</html>
