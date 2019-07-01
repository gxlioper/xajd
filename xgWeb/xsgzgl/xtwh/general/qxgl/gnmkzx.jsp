<%@ page language="java" contentType="text/html; charset=GBK"%>
<!-- 提示信息 -->

<script type='text/javascript' src='/xgxt/dwr/interface/yhgl.js'></script>
<script language="javascript" src="js/comm/commFunction.js"></script>
<script language="javascript" defer="defer">

var flg = false;

jQuery(function(){
	jQuery("input[type=checkbox]").bind("click",function(){
		flg = true;
	})

	jQuery("input[type=radio]").bind("click",function(){
		flg = true;
	})
})

jQuery("#divWaiting").css("display","");
jQuery("#divDisable").css("display","");

function init(){
	jQuery("#divWaiting").css("display","none");
	jQuery("#divDisable").css("display","none");
	jQuery("#mkzx").css("display","");	
}

//当前功能模块代码
var currGnmkdm;

// 全选
function selectAll(){
	var all = document.getElementsByTagName('input');
	var checs = new Array(); 
	var count = 0;
	var checked = $('qx').value == 'no' ? true : false;
	
	for(var i=0; i<all.length; i++){
		if(all[i].type == 'checkbox' && all[i].disabled == false){
			checs[count++] = all[i]
		}
	}
	
	for (var i=0; i<checs.length; i++){
		checs[i].checked = checked;
		var ids = checs[i].id.split("_");
		if(ids[2]=="lv3"){
			checkLv3(ids[1],checked);
		}
	}
	
	$('qx').value = $('qx').value == 'no' ? 'yes' : 'no';
}

// 调换类名
function changeClass(obj){
	var as = getElementsByClass('current',document,'li');
	for(var i=0;i<as.length;i++){
		as[i].className = "";
		as[i].children[0].disabled="disabled";
	}
	jQuery("#chec_"+obj.id+"_lv1").prop("disabled",false);
	obj.parentNode.className = "current";
}

// 更换模块
function changeMk(obj){
	var gnmkId = obj.id;
	currGnmkdm=gnmkId;
	var trs = getElementsByClass('tr_02',document,'tr');
	
	for(var i=0; i<trs.length; i++){
		var trId = trs[i].id.split("_");
		if(gnmkId == trId[0]){
			trs[i].style.display = "";
		}else{
			trs[i].style.display = "none";
		}
	}	
	changeClass(obj);
}

// 点击checkbox
function checkboxClick(obj){
	childrenChoice(obj);
	parentsChoice(obj);
}

function childrenChoice(obj){
	var id = obj.id;
	var ids = id.split('_');

	// 自身代码
	var dm = ids[1];
	var checked = obj.checked;
	
	if(ids[2] == 'lv3'){
		checkLv3(dm,checked);
		return;
	}
	
	var chec = getElementsByJb(dm,'input',id);
	
	for(var i=0; i<chec.length; i++){
		if(!chec[i].disabled){
			chec[i].checked = checked;
		}
		
		childrenChoice(chec[i]);
	}
}

function getElementsByJb(searchClass,tag,id) {
    var ids = id.split('_');
    var classElements = new Array();
    var els;
    
    if(ids[2] == 'lv1'){	// 一级
    	els = jQuery('#tbody_'+ids[1]).find(tag);
    }else if(ids[2] == 'lv2'){ // 二级
    	els = jQuery('#tbody_'+ids[0]).find(tag);
    }else{
    	els = jQuery("#"+id).parent().nextSibling().find(tag);
    }
    var elsLen = els.length;

    var pattern = new RegExp("(^|\\s)"+searchClass+"(\\s|$)");
    for (i = 0, j = 0; i < elsLen; i++) {
    	if ( pattern.test(els[i].className) ) {
        	classElements[j] = els[i];
            j++;
    	}
    }
    return classElements;
}

function parentsChoice(obj){
	var ids = jQuery(obj).attr("id").split('_');
	// 上级代码
	var sjdm = ids[0];
	var lv = ids[2];
	var sjid = '';
	
	if(lv == 'lv1'){
		return;
	}else if(lv == 'lv2'){
		sjid = 'chec_'+sjdm+'_lv1';	
	}else if(lv == 'lv3'){
		sjid = sjdm.substring(0,sjdm.length-2) + '_' + sjdm + '_lv2';
	}
	
	var checked = obj.checked;
	var sjobj = jQuery("#"+sjid);
	var broArr = getElementsByJb(sjdm,'input', sjid);
	
	for(var i=0; i<broArr.length; i++){
		if(broArr[i].checked){
			checked = true;
			break;
		}
	}
	jQuery(sjobj).prop("checked",checked);
	parentsChoice(sjobj);
}

function checkLv3(id,checked){
	if(checked){
		jQuery("#li_"+id).css("display","");
		chg(id,"1");
	}else{
		jQuery("#li_"+id).css("display","none");
		jQuery("#li_"+id).children()[0].checked = true;
		chg(id,"");
	}
}

//读写权
function fun_dxq(dxqValue){
	
	var trs = getElementsByClass('tr_02',document,'tr');
	for(var i=0; i<trs.length; i++){
		var trId = trs[i].id.split("_");
		if(currGnmkdm == trId[0]){
			var radios = getElementsByClass('class_radio_dxq',trs[i],'input');
			for(var j=0;j<radios.length;j++){
				var id = radios[j].name.split("_")[1];
				if(jQuery("#s_"+id).html() != ""){
					if(radios[j].value==dxqValue){
						radios[j].checked=true;						
						radios[j].onclick();
					}else{
						radios[j].checked=false;
					}
				}				
			}
		}
	}
}

function chg(id,tag){
	if(tag == '0')
		jQuery("#s_"+id).html("<font color='red'>(只读)</font>");
	else if(tag == '1')
		jQuery("#s_"+id).html("<font color='green'>(可写)</font>");
	else
		jQuery("#s_"+id).html("");
	
}

<logic:iterate name="allGnmkList" id="gnmkOne" indexId="index" length="1">
	currGnmkdm="${gnmkOne.gnmkdm}";
</logic:iterate>

</script>
<body onload="init()">
<div class="main_function" id="mkzx" style="display:none" >
  <input type="hidden" id="qx" value="no" />
  <div class="function_list01">
    <h3><span>功能分配</span></h3>
    <ul>
		<logic:iterate name="allGnmkList" id="gnmkOne" indexId="index">
			  <li <logic:equal name="index" value="0">class="current"</logic:equal> >
			  	<input class="inp_gnmkdmOne" id="chec_${gnmkOne.gnmkdm }_lv1" name="gnmkdm" value="${gnmkOne.gnmkdm}" type="checkbox" onclick="checkboxClick(this);"
			  		 ${gnmkOne.checked} <logic:notEqual name="index" value="0">disabled="disabled"</logic:notEqual> />
			  	<a href="#" id="${gnmkOne.gnmkdm }" onclick="changeMk(this);return false;" >${gnmkOne.gnmkmc}</a>
			  </li>
		</logic:iterate>
    </ul>
  </div>
  
  <div class="function_list02" id="ejcd" style="overflow: scroll;overflow-x:hidden; height: ${height }px">
  	<table width="100%" border="0">
  		<logic:iterate name="allGnmkList" id="gnmkOne" indexId="index01">
  			<tbody id="tbody_${gnmkOne.gnmkdm }">
			<logic:iterate name="gnmkOne" property="childList" id="gnmkTwo" indexId="index02">
				<logic:iterate name="gnmkTwo" property="childList" id="gnmkThr" indexId="index03">
					<tr id="${gnmkOne.gnmkdm }_${index03}" class="tr_02" <logic:notEqual name="index01" value="0">style="display: none"</logic:notEqual> >
						<logic:equal name="index03" value="0">
							<td style="width:130px" rowspan="${gnmkTwo.childSize }" class="list_02">
					        	<input type="checkbox" value="${gnmkTwo.gnmkdm }" name="gnmkdm" id="${gnmkOne.gnmkdm }_${gnmkTwo.gnmkdm }_lv2" 
					              	class="${gnmkOne.gnmkdm }" onclick="checkboxClick(this);" ${gnmkTwo.checked }/>
					              	${gnmkTwo.gnmkmc }
					       </td>
						</logic:equal>
						
						<td style="width:200px" class="list_03">
					    	<input type="checkbox" value="${gnmkThr.gnmkdm }" name="gnmkdm" id="${gnmkTwo.gnmkdm }_${gnmkThr.gnmkdm }_lv3" 
					    		class="${gnmkTwo.gnmkdm }" onclick="checkboxClick(this);" ${gnmkThr.checked}/>
					        	${gnmkThr.gnmkmc }
					        	<span id="s_${gnmkThr.gnmkdm }" class="class_text_dxq" >
					        		<logic:equal value="1" name="gnmkThr" property="dxq">
					        			<font color='green'>(可写)</font>
					        		</logic:equal>
					        		<logic:equal value="0" name="gnmkThr" property="dxq">
					        			<font color='red'>(只读)</font>
					        		</logic:equal>
					        	</span>
					    </td>
					    
					    <td>
					    	<ul >
				    			<li id="li_${gnmkThr.gnmkdm }" style="display:<logic:equal value="" name="gnmkThr" property="checked"> none</logic:equal>;">					    		
					    			<input type="radio" class="class_radio_dxq" name="dxq_${gnmkThr.gnmkdm }" value="1" 
					    				<logic:notEqual value="0" name="gnmkThr" property="dxq">checked="checked"</logic:notEqual> 
					    				onclick="chg('${gnmkThr.gnmkdm }','1')"/>可写
					    			<input type="radio" class="class_radio_dxq" name="dxq_${gnmkThr.gnmkdm }" value="0" 
					    				<logic:equal value="0" name="gnmkThr" property="dxq">checked="checked"</logic:equal> 
					    				onclick="chg('${gnmkThr.gnmkdm }','0')"/>只读
					    		<%--
					    		<logic:equal value="0" name="gnmkThr" property="dxq">
					    			<input type="radio" class="class_radio_dxq" name="dxq_${gnmkThr.gnmkdm }" value="1" onclick="chg('${gnmkThr.gnmkdm }','1')"/>可写
					    			<input type="radio" class="class_radio_dxq" name="dxq_${gnmkThr.gnmkdm }" value="0" checked="checked" onclick="chg('${gnmkThr.gnmkdm }','0')"/>只读
					    		</logic:equal>
					    		--%>
					    		</li>
					    	</ul>
					    </td>					    
					</tr>
				</logic:iterate>
			</logic:iterate>
			</tbody>
		</logic:iterate>
  	</table>
  </div>
  <div class="function_list03"></div>
</div>
</body>


