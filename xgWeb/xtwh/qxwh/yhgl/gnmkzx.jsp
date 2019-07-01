<%@ page language="java" contentType="text/html; charset=GBK"%>
<script type='text/javascript' src='/xgxt/dwr/interface/yhgl.js'></script>
<script language="javascript" defer="defer">
function czBtn(){
	var btns = document.getElementsByName('cz_btn');
	
	var all = document.getElementsByTagName('input');
	var checs = new Array(); 
	var count = 0;
	
	for(var i=0; i<all.length; i++){
		if(all[i].type == 'checkbox' && all[i].disabled == false){
			checs[count++] = all[i]
		}
	}
	
	for (var i=0; i<checs.length; i++){
		checs[i].checked = false;
	}
	
	for (var i=0; i<btns.length; i++){
		$(btns[i].value).checked = true;
		parentCsh(btns[i].value);
	}
}

function init(){
	var btns = document.getElementsByName('cz_btn');
	
	for(var i=0; i<btns.length; i++){
		parentCsh(btns[i].value);
	}
}

function parentCsh(id){
	var ids = id.split('_');
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
	}else if(lv == 'lv4'){
		sjid = sjdm.substring(0,sjdm.length-2) + '_' + sjdm + '_lv3';
	}
	var sjobj = $(sjid);
	sjobj.checked = true;
	
	parentCsh(sjid);
}

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
	}
	
	$('qx').value = $('qx').value == 'no' ? 'yes' : 'no';
}

// 调换类名
function changeClass(obj){
	var as = getElementsByClass('current',document,'li');
	for(var i=0;i<as.length;i++){
		as[i].className = "";
	}
	
	obj.parentNode.className = "current";
}

// 更换模块
function changeMk(obj){
	var gnmkId = obj.id;
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
	
	if(ids[2] == 'lv4'){
		return;
	}
	
	// 自身代码
	var dm = ids[1];
	var chec = getElementsByJb(dm,'input',id);
	var checked = obj.checked;
	
	for(var i=0; i<chec.length; i++){
		if(!chec[i].disabled){
			chec[i].checked = checked;
		}
		
		if(chec[i].name != 'btns'){
			childrenChoice(chec[i]);
		}
	}
}

function getElementsByJb(searchClass,tag,id) {
    var ids = id.split('_');
    var classElements = new Array();
    var els;
    
    if(ids[2] == 'lv1'){	// 一级
    	els = $('tbody_'+ids[1]).getElementsByTagName(tag);
    }else if(ids[2] == 'lv2'){ // 二级
    	els = $('tbody_'+ids[0]).getElementsByTagName(tag);
    }else{
    	els = $(id).parentNode.parentNode.nextSibling.getElementsByTagName(tag);
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
	var ids = obj.id.split('_');
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
	}else if(lv == 'lv4'){
		sjid = sjdm.substring(0,sjdm.length-2) + '_' + sjdm + '_lv3';
	}
	var checked = obj.checked;
	var sjobj = $(sjid);
	
	var broArr = getElementsByJb(sjdm,'input', sjid);
	
	for(var i=0; i<broArr.length; i++){
		if(broArr[i].checked){
			checked = true;
			break;
		}
	}
	
	sjobj.checked = checked;
	parentsChoice(sjobj);
}

</script>

<div class="main_function" id="mkzx">
  <input type="hidden" id="qx" value="no" />
  <div class="function_list01">
    <h3><span>功能分配</span></h3>
    <ul>
		<logic:iterate name="allGnmkList" id="gnmkOne" indexId="index">
			  <li <logic:equal name="index" value="0">class="current"</logic:equal> >
			  	<input id="chec_${gnmkOne.gnmkdm }_lv1" value="${gnmkOne.gnmkdm }" type="checkbox" onclick="checkboxClick(this);"/>
			  	<a href="#" id="${gnmkOne.gnmkdm }" onclick="changeMk(this);return false;" >${gnmkOne.gnmkmc }</a>
			  </li>
		</logic:iterate>
    </ul>
  </div>
  
  <div class="function_list02" id="" style="overflow: scroll;overflow-x:hidden; height: ${height }px">
  	<table width="100%" border="0">
  		<logic:iterate name="allGnmkList" id="gnmkOne" indexId="index01">
  			<tbody id="tbody_${gnmkOne.gnmkdm }">
			<logic:iterate name="gnmkOne" property="childList" id="gnmkTwo" indexId="index02">
				<logic:iterate name="gnmkTwo" property="childList" id="gnmkThr" indexId="index03">
					<tr id="${gnmkOne.gnmkdm }_${index03}" class="tr_02" <logic:notEqual name="index01" value="0">style="display: none"</logic:notEqual> >
						<logic:equal name="index03" value="0">
							<td style="width:120px" rowspan="${gnmkTwo.childSize }" class="list_02">
					            <label>
					              <input type="checkbox" value="${gnmkTwo.gnmkdm }" id="${gnmkOne.gnmkdm }_${gnmkTwo.gnmkdm }_lv2" class="${gnmkOne.gnmkdm }" onclick="checkboxClick(this);"/>
					              	${gnmkTwo.gnmkmc }
					             </label>
					       </td>
						</logic:equal>
						
						<td style="width:150px" class="list_03">
							<label>
					    	<input type="checkbox" value="${gnmkThr.gnmkdm }" id="${gnmkTwo.gnmkdm }_${gnmkThr.gnmkdm }_lv3" class="${gnmkTwo.gnmkdm }" onclick="checkboxClick(this);" />
					        	${gnmkThr.gnmkmc }
					        </label>
					    </td>
					    
					    <td>
					    	<ul>
					    		<logic:iterate name="gnmkThr" property="btnList" id="btn">
						    		<li>
						    			<input type="checkbox" name="btns" value="${gnmkThr.gnmkdm }_${btn.btndm}" id="${gnmkThr.gnmkdm }_${btn.btndm}_lv4" onclick="checkboxClick(this);"
						    			 	<logic:equal name="btn" property="disabled" value="yes">disabled="disabled"</logic:equal>
						    			 	<logic:equal name="btn" property="checked" value="yes">checked="checked"</logic:equal>
						    			class="${gnmkThr.gnmkdm }"></input>${btn.btnmc }
						    			
						    			<logic:equal name="btn" property="checked" value="yes">
						    				<logic:notEqual name="btn" property="disabled" value="yes">
						    					<input type="hidden" name="cz_btn" value="${gnmkThr.gnmkdm }_${btn.btndm}_lv4"/>
						    				</logic:notEqual>
						    			</logic:equal>
						    		</li>
					    		</logic:iterate>
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
<script type="text/javascript" defer="defer">
	init();
</script>

