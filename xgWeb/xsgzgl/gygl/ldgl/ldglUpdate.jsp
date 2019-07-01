<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<jsp:directive.page import="java.util.HashMap" />
<jsp:directive.page import="xgxt.action.Base" />
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type='text/javascript'
			src='dwr/interface/GetListData.js'></script>
		<script type='text/javascript' src='dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
		<script language="javascript" src="js/check.js"></script>
		<script type="text/javascript" src="dwr/interface/gyglGywh.js"></script>	
		<script type='text/javascript' src='dwr/interface/getCommGygl.js'></script>
		<script type="text/javascript">
		function modi(){

			//验证生成寝室数
			b=false;
			var re_num = /^[\d]+$/;
			var scqss=document.getElementsByName("scqss");
			var ldmc=jQuery("[name='ldmc']").val();
			if(ldmc==""){
				alertInfo("楼栋名称不能为空!");
				return false;
			}
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
			
			var url="gyglnew_ldgl.do?method=ldxxwh&doType=modi";
			refreshForm(url);
		}

		//创建楼栋
		function createLd(){
			if($("newldmark").value=="yes"){
                if($("sfkzjlc")&&$("sfkzjlc").value=="1"){
                    var originalLdcs = $("originalLdcs").value;
                    var ldcs = $("ldcs").value;

                    if(parseInt(originalLdcs)>=parseInt(ldcs)){
                        alertInfo("只允许增加楼层！");
                        $("ldcs").value=originalLdcs;
                    }
					createLd_new_add();
                    return true;
                }
				createLd_new();
				return true;
			}

			var ldxb="男";//楼栋性别
			var ldxb_radio_temp=document.getElementsByName("ldxb");
			for(var i=0;i<ldxb_radio_temp.length;i++){
				if(ldxb_radio_temp[i].checked){
					ldxb=ldxb_radio_temp[i].value;
					break;
				}
			}

			var ld_table=$("ld_table");//楼栋table
			var ld_trs=ld_table.getElementsByTagName("TR");
			var td;
			for(var i=2;i<ld_trs.length;i++){//根据设置的参数创建楼栋
				td=ld_trs[i].cells[3];
				//alertInfo(ldxb+"    "+("混住"!=ldxb)+"  "+td.getElementsByTagName("span").length);
				if("混住"==ldxb){ 
					if(td.getElementsByTagName("span")[1]){
						td.getElementsByTagName("span")[0].style.display="none";
						td.getElementsByTagName("span")[1].style.display="";
					}
				}else{
					if(td.getElementsByTagName("span")[1]){
						td.getElementsByTagName("span")[0].style.display="";
						td.getElementsByTagName("span")[0].innerHTML=ldxb;
						td.getElementsByTagName("span")[1].style.display="none";
					}
				}
				//break;
			}
		}

	  function init(){
		var qsch=$("qsch").value;
		if(qsch>=0){
			$("sfhlc_lable").style.display="none";
			$("sfhlc_obj").style.display="none";
		}else{
			$("sfhlc_lable").style.display="";
			$("sfhlc_obj").style.display="";
		}
	  }

	//创建楼栋
		function createLd_new(){
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
			
			var ld_table=$("ld_table_new");//楼栋table
			var ld_trs=ld_table.getElementsByTagName("TR");
			for(var i=ld_trs.length-1;i>1;i--){//移除原先创建的信息
				ld_table.deleteRow(i);
			}
			var b_mark;//负层B标记
			var ch_value;//层号值
			var ch_name;//层号名称
			var sfhlc_mark=(qsch>0||sfhlc=="是"?0:1);//是否含零层标记//含零层正常计算，不含零层需要增量
			var top_mark=true;//顶层标记
			for(var i=parseInt(ldcs)-1,xbnum=0;i>-1;i--,xbnum++){//根据设置的参数创建楼栋var i=0;i<parseInt(ldcs);i++
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
					r.insertCell(4).innerHTML='<input type="radio" disabled="disabled" name="ldxb'+xbnum+'" checked="checked" value="男"/>男<input type="radio" disabled="disabled" name="ldxb'+xbnum+'" value="女"/>女';
				}else if(ldxb=="女"){
					r.insertCell(4).innerHTML='<input type="radio" disabled="disabled" display="none;" name="ldxb'+xbnum+'" value="男"/>男<input type="radio" disabled="disabled" name="ldxb'+xbnum+'" checked="checked" value="女"/>女';
				}else{
					r.insertCell(4).innerHTML='<input type="radio" name="qsxb'+xbnum+'" checked="checked" value="男"'+(top_mark?' onclick="click_qsxb(this);"':'')+'/>男<input type="radio" name="qsxb'+xbnum+'" value="女"'+(top_mark?' onclick="click_qsxb(this);"':'')+'/>女';
				}
				top_mark=false;
			}
		}

		// 创建楼栋For增加楼层
        function createLd_new_add(){
            var ldxb="男";//楼栋性别
            var ldxb_radio_temp=document.getElementsByName("ldxb");
            for(var i=0;i<ldxb_radio_temp.length;i++){
                if(ldxb_radio_temp[i].checked){
                    ldxb=ldxb_radio_temp[i].value;
                    break;
                }
            }
            var ldcs=$("ldcs").value;//楼栋层数
			var originalLdcs = $("originalLdcs").value;//原始楼栋层数
            var qsch=parseInt($("qsch").value);//起始层号
            var sfhlc=$("sfhlc").value;//是否含零层
            if(ldxb==""||ldcs==""){
                return false;
            }

            var ld_table=$("ld_table_new");//楼栋table
            var ld_trs=ld_table.getElementsByTagName("TR");
            for(var i=ld_trs.length-1;i>1;i--){//移除原先创建的信息
                ld_table.deleteRow(i);
            }
            var b_mark;//负层B标记
            var ch_value;//层号值
            var ch_name;//层号名称
            var sfhlc_mark=(qsch>0||sfhlc=="是"?0:1);//是否含零层标记//含零层正常计算，不含零层需要增量
            var top_mark=true;//顶层标记
            for(var i=parseInt(ldcs)-1,xbnum=0;i>parseInt(originalLdcs)-1;i--,xbnum++){//根据设置的参数创建楼栋var i=0;i<parseInt(ldcs);i++
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
                    r.insertCell(4).innerHTML='<input type="radio" disabled="disabled" name="ldxb'+xbnum+'" checked="checked" value="男"/>男<input type="radio" disabled="disabled" name="ldxb'+xbnum+'" value="女"/>女';
                }else if(ldxb=="女"){
                    r.insertCell(4).innerHTML='<input type="radio" disabled="disabled" display="none;" name="ldxb'+xbnum+'" value="男"/>男<input type="radio" disabled="disabled" name="ldxb'+xbnum+'" checked="checked" value="女"/>女';
                }else{
                    r.insertCell(4).innerHTML='<input type="radio" name="qsxb'+xbnum+'" checked="checked" value="男"'+(top_mark?' onclick="click_qsxb(this);"':'')+'/>男<input type="radio" name="qsxb'+xbnum+'" value="女"'+(top_mark?' onclick="click_qsxb(this);"':'')+'/>女';
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

		//控制是否含零层的显示问题
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

		var ld_table=document.createElement("table");
		//newSelectObj = selectObj.cloneNode(true);
		var ld_table_new=document.createElement("table");
		//重新创建楼栋
		function cxCreateLd(but){
			if(but.innerHTML=="重新生成"){
				but.innerHTML="取消生成"
				$("newldmark").value="yes";
				ld_table=$("ld_table").cloneNode(true);
				$("div_ld_table").removeChild($("ld_table"));
				if($("ld_table_new")){
					$("ld_table_new").style.display="";
				}else{
					$("div_ld_table").appendChild(ld_table_new);
				}
				$("qsh_gz").style.display="";
				$("ldcs").disabled=false;
				$("qsch").disabled=false;
				$("sfhlc").disabled=false;
				createLd_new();
				
			}else if((but.innerHTML=="取消生成")){
				but.innerHTML="重新生成"
				$("newldmark").value="no";
				ld_table_new=$("ld_table_new").cloneNode(true);
				$("div_ld_table").removeChild($("ld_table_new"));
				$("div_ld_table").appendChild(ld_table);

				var ldxb_radio_temp=document.getElementsByName("ldxb");
				for(var i=0;i<ldxb_radio_temp.length;i++){
					if(ldxb_radio_temp[i].value==$("hid_ldxb").value){
						ldxb_radio_temp[i].checked=true;
						break;
					}
				}
				$("qsh_gz").style.display="none";
				$("ldcs").value=$("hid_ldcs").value;
				$("qsch").value=$("hid_qsch").value;
				$("sfhlc").value=$("hid_sfhlc").value;
				$("ldcs").disabled=true;
				$("qsch").disabled=true;
				$("sfhlc").disabled=true;
				show_sfhlc($("qsch").value);
				createLd();
			}else if(but.innerHTML=="增加楼层"){
                but.innerHTML="取消增加";
                $("newldmark").value="yes";
                ld_table=$("ld_table").cloneNode(true);
                //$("div_ld_table").removeChild($("ld_table"));
                if($("ld_table_new")){
                    $("ld_table_new").style.display="";
                }else{
                    $("div_ld_table").prepend(ld_table_new);
                }

                $("qsh_gz").style.display="";
                $("ldcs").disabled=false;
                $("qsch").disabled=false;
                $("sfhlc").disabled=false;
//                createLd_new();
			}else{
                but.innerHTML="增加楼层";
                $("newldmark").value="no";
                ld_table_new=$("ld_table_new").cloneNode(true);
                $("div_ld_table").removeChild($("ld_table_new"));

                var ldxb_radio_temp=document.getElementsByName("ldxb");
                for(var i=0;i<ldxb_radio_temp.length;i++){
                    if(ldxb_radio_temp[i].value==$("hid_ldxb").value){
                        ldxb_radio_temp[i].checked=true;
                        break;
                    }
                }
                $("qsh_gz").style.display="none";
                $("ldcs").value=$("hid_ldcs").value;
                $("ldcs").disabled=true;
                show_sfhlc($("qsch").value);
			}
		}
		function showSzm(){
			if(jQuery("[name='qsh_sfhszm']").prop("checked")){
				jQuery("#qsh_szm").css("display","");
			}else{
				jQuery("#qsh_szm").css("display","none");
				//$("qsh_szm").style.display = "none";
			}
		}
		</script>
	</head>
	<body onload="init();">
		<html:form action="/gyglnew_ldgl" method="post">
			<logic:present name="rs">
			<input type="hidden" name="czxq" id="czxq" value="${czxq }"/>
			<input type="hidden" name="czyq" id="czyq" value="${czyq }"/>
			<input type="hidden" name="yqdmHid" id="yqdmHid" value="${rs.yqdm}"/>
			<input type="hidden" name="xqdmHid" id="xqdmHid" value="${rs.xqdm}"/>
			<input type="hidden" id="sfcjxld" value="${rs.mark}"/>
			<input type="hidden" name="newldmark" id="newldmark" value=""/>
			
			<input type="hidden" id="hid_ldxb" value="${rs.ldxb}"/>
			<input type="hidden" id="hid_ldcs" value="${rs.ldcs}"/>
			<input type="hidden" id="hid_qsch" value="${rs.qsch}"/>
			<input type="hidden" id="hid_sfhlc" value="${rs.sfhlc}"/>
			<%--<div class="tab_cur" >
				<p class="location">
					<em>您的当前位置:</em><a>${title }</a>
				</p>
			</div>
			--%><!-- 提示信息 仅在修改时提示 end-->
			<logic:equal name="doType" value="update">
			<div class="prompt">
				<h3>
					<span>提示：</span>
				</h3>
				<p>
					已有人入住的楼栋不可修改楼栋性别限制。<br/>	
				</p>
				<a class="close" title="隐藏"
				   onclick="this.parentNode.style.display='none';"></a>
			</div>
			</logic:equal>
			<!-- 提示信息 end-->		
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
								<html:select property="xqdm" name="rs" styleId="xqdm" disabled="true">
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
								<html:select property="yqdm" name="rs" styleId="yqdm" disabled="true">
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
							<td  style="width:36%">
								<html:hidden property="lddm" name="rs"/>
								<html:text property="lddm" name="rs" disabled="true"></html:text>
							</td>
							<th>
								<font color="red">*</font>
								楼栋名称
							</th>
							<td>
								<html:text property="ldmc" name="rs" maxlength="100"></html:text>
							</td>
						</tr>
						<tr>
							<th style="width:14%">
								<font color="red">*</font>
								<bean:message key="lable.ld" />性别
							</th>
							<td  style="width:36%">
								<logic:equal value="男" name="rs" property="ldxb">
									<logic:equal value="1" name="rs" property="mark">
										<input type="radio" name="ldxb" value="男" onclick="createLd();" checked="checked"/>男
										<input type="radio" name="ldxb" value="混住" onclick="createLd();" />混住
									</logic:equal>
									<logic:notEqual value="1" name="rs" property="mark">
										<input type="radio" name="ldxb" value="男" onclick="createLd();" checked="checked"/>男
										<input type="radio" name="ldxb" value="女" onclick="createLd();" />女
										<input type="radio" name="ldxb" value="混住" onclick="createLd();" />混住
									</logic:notEqual>
								</logic:equal>
								<logic:equal value="女" name="rs" property="ldxb">
									<logic:equal value="1" name="rs" property="mark">
										<input type="radio" name="ldxb" value="女" onclick="createLd();" checked="checked"/>女
										<input type="radio" name="ldxb" value="混住" onclick="createLd();" />混住
									</logic:equal>
									<logic:notEqual value="1" name="rs" property="mark">
										<input type="radio" name="ldxb" value="男" onclick="createLd();" />男
										<input type="radio" name="ldxb" value="女" onclick="createLd();" checked="checked"/>女
										<input type="radio" name="ldxb" value="混住"  onclick="createLd();"/>混住
									</logic:notEqual>
								</logic:equal>
								<logic:equal value="混住" name="rs" property="ldxb">
									<logic:equal value="1" name="rs" property="mark">
										<logic:notEqual value="1" name="rs" property="w_mark">
											<input type="radio" name="ldxb" value="男"  onclick="createLd();"/>男
										</logic:notEqual>
										<logic:notEqual value="1" name="rs" property="m_mark">
											<input type="radio" name="ldxb" value="女" onclick="createLd();"/>女
										</logic:notEqual>
										<input type="radio" name="ldxb" value="混住" onclick="createLd();" checked="checked"/>混住
									</logic:equal>
									<logic:notEqual value="1" name="rs" property="mark">
										<input type="radio" name="ldxb" value="男"  onclick="createLd();"/>男
										<input type="radio" name="ldxb" value="女"  onclick="createLd();"/>女
										<input type="radio" name="ldxb" value="混住"  onclick="createLd();" checked="checked"/>混住
									</logic:notEqual>
								</logic:equal>
<%--								<html:radio property="ldxb" value="男" name="rs" onclick="createLd();">男</html:radio>--%>
<%--								<html:radio property="ldxb" value="女" name="rs" onclick="createLd();">女</html:radio>--%>
<%--								<html:radio property="ldxb" value="混住" name="rs" onclick="createLd();">混住</html:radio>--%>
							</td>
							<th>
								<font color="red">*</font>
								<bean:message key="lable.ld" />层数
							</th>
							<td>
								<html:select property="ldcs" name="rs" styleId="ldcs" onchange="createLd();" disabled="true">
									<html:optionsCollection name="ldcsList" label="mc" value="dm" />
								</html:select>
								<input type="hidden" id="originalLdcs" name="originalLdcs" value="${rs.ldcs}"/>
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>
								<bean:message key="lable.ld" />起始层
							</th>
							<td>
								<html:select property="qsch" name="rs" styleId="qsch" onclick="createLd();show_sfhlc(this.value);"  disabled="true">
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
								<html:select property="sfhlc" name="rs" styleId="sfhlc" onclick="createLd();" disabled="true">
									<html:option value="否">否</html:option>
									<html:option value="是">是</html:option>
								</html:select>
								</span>
							</td>
						</tr>
						<tr>
							<th >
								<bean:message key="lable.ld" />备注<br/><font color="red">(限1000字)</font>
							</th>
							<td  colspan="3">
								<html:textarea property="bz" name="rs" rows="3" style="word-break:break-all;width:95%" onblur="chLeng(this,1000);"></html:textarea>
							</td>
						</tr>
					</tbody>
				</table>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="5">
								<span>维护需要生成的寝室情况</span>
								<logic:notEqual value="1" name="rs" property="mark">
									<button type="button"  onclick="cxCreateLd(this);">重新生成</button>
								</logic:notEqual>
								<logic:equal value="1" name="rs" property="mark">
									<logic:equal value="10530" name="xxdm">
										<input type="hidden" id="sfkzjlc" name="sfkzjlc" value="1"/>
										<button type="button" onclick="cxCreateLd(this);"   title="该楼栋在未入住且未分配的情况下可增加楼层！">增加楼层</button>
									</logic:equal>
									<logic:notEqual value="10530" name="xxdm">
										<button type="button"  disabled="disabled" title="该楼栋在未入住且未分配的情况下才可重新生成！">重新生成</button>
									</logic:notEqual>
								</logic:equal>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr id="qsh_gz" style="display: none">
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
				<div id="div_ld_table" style="overflow-x:hidden;overflow-y:auto;width: 100%">
						<%--	新创建楼栋new  start			--%>
					<table width="100%" border="0" class="datelist tablenowrap" id="ld_table_new" style="display:none;">
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
						<%--	新创建楼栋new  end			--%>

				<table width="98%" border="0" class="datelist tablenowrap" id="ld_table">					
					<thead>
						<%--<tr>
							<td colspan="5">
								<input type="checkbox" id="plsr_checkbox" checked="checked" />默认按底层设置生成寝室数、床位数及收费标准
							</td>
						</tr>--%>
						<tr>
							<th style="width:20%">
								层号
							</th>
							<th style="width:20%">
								寝室数
							</th>
							<th style="width:20%">
								总床位数
							</th>
							<th style="width:20%">
								寝室性别
							</th>
						</tr>
					<thead>
					<tbody>
						<logic:iterate id="rt" name="rstj">
							<tr>
								<td>
									<% String ch=((HashMap<String,String>)rt).get("ch");
										if(ch!=null&&Integer.parseInt(ch)<0){
											%>B<%=Math.abs(Integer.parseInt(ch)) %><%
										}else{
											%><%=ch%><%
										}
									%>
									层
									<input type="hidden" name="ch" value="${rt.ch }" />
								</td>
								<td>${rt.qss }</td>
								<td>${rt.cwzs }</td>
								<td>
									<logic:notEqual value="混住" name="rs" property="ldxb">
										<logic:equal value="0" name="rt" property="mark">
											<logic:equal value="男" name="rt" property="qsxb">
												<span>男</span>
												<span style="display: none;">
													<input type="radio" name="qsxb_${rt.ch}_${rt.qsxb}" value="男" checked="checked"/>男
													<input type="radio" name="qsxb_${rt.ch}_${rt.qsxb}" value="女" />女
												</span>
											</logic:equal>
											<logic:equal value="女" name="rt" property="qsxb">
												<span>女</span>
												<span style="display: none;">
													<input type="radio" name="qsxb_${rt.ch}_${rt.qsxb}" value="男" />男
													<input type="radio" name="qsxb_${rt.ch}_${rt.qsxb}" value="女" checked="checked"/>女
												</span>
											</logic:equal>
										</logic:equal>
										<logic:notEqual value="0" name="rt" property="mark">
											<span>${rt.qsxb}</span>
										</logic:notEqual>
									</logic:notEqual>
									<logic:equal value="混住" name="rs" property="ldxb">
										<logic:notEqual value="0" name="rt" property="mark">
											<span>${rt.qsxb}</span>
										</logic:notEqual>
										<logic:equal value="0" name="rt" property="mark">
											<span></span>
											<span>
											<logic:equal value="男" name="rt" property="qsxb">
												<input type="radio" name="qsxb_${rt.ch}_${rt.qsxb}" value="男" checked="checked"/>男
												<input type="radio" name="qsxb_${rt.ch}_${rt.qsxb}" value="女"/>女
											</logic:equal>
											<logic:equal value="女" name="rt" property="qsxb">
												<input type="radio" name="qsxb_${rt.ch}_${rt.qsxb}" value="男"/>男
												<input type="radio" name="qsxb_${rt.ch}_${rt.qsxb}" value="女" checked="checked"/>女
											</logic:equal>
											</span>
										</logic:equal>
									</logic:equal>
<%--								${rt.qsxb}<input type="hidden" name="qsxb_old" value="${rt.qsxb}"/>--%>
								</td>
							</tr>
						</logic:iterate>
					</tbody>
				</table>
				</div>
			</div>
			<div>
				<table width="100%" border="0" class="formlist">					
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									<font color="blue">
										注：带"<font color="red">*</font>"的信息必须录入，性别不可修改的楼层则说明该楼层的寝室或床位已分配或床位已入住
									</font>
								</div>
								<div class="btn">
									<button type="button" class="button2" id="btn_bc"  onclick="modi()">
										保 存
									</button>
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
					alertInfo("操作失败！");
					</script>
				</logic:equal>
				<logic:equal value="true" name="result">
					<script language="javascript">
					alertInfo("操作成功！");
					</script>
				</logic:equal>
				<script language="javascript">			
					if(window.dialogArguments){
						if(window.dialogArguments.document.getElementById("search_go")){
							dialogArgumentsQueryChick();
						}
						window.close();
					}
				</script>
			</logic:notEmpty>
		</html:form>
	</body>
</html>
