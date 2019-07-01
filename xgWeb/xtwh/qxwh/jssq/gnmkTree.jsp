<%@ page language="java" contentType="text/html; charset=GBK"%>
<link rel="stylesheet" href="xgcss/tree.css" type="text/css" media="all" />
<script type='text/javascript' src='/xgxt/dwr/interface/qxwhDWR.js'></script>
<style>
<!--
.partial {
	background:url(/xgxt/xtwh/qxwh/jssq/checkboxes_bf.gif) 3px top no-repeat;
	width:18px;
	height:14px;
	margin-left: 2px;
	float:left;
	clear:both;

}
.unchecked {
	background-image: url(/xgxt/xtwh/qxwh/jssq/checkboxes.gif);
	background-position:-18px center;
	width:18px;
	height:14px;
	margin-left: 2px;
	float:left;
	clear:both;
}
.unchecked:hover {
	background-image: url(/xgxt/xtwh/qxwh/jssq/checkboxes.gif);
	background-position:-54px center;
	width:18px;
	height:14px;
	margin-left: 2px;
	float:left;
	clear:both;
}

.checked {
	background-image: url(/xgxt/xtwh/qxwh/jssq/checkboxes.gif);
	background-position:left center;
	width:18px;
	height:14px;
	margin-left: 2px;
	float:left;
	clear:both;
}
.checked:hover {
	background-image: url(/xgxt/xtwh/qxwh/jssq/checkboxes.gif);
	background-position:-36px center;
	width:18px;
	height:14px;
	margin-left: 2px;
	float:left;
	clear:both;
}
-->
</style>
<script language="javascript" defer="defer">


function clickParent(lv,dm){
	
	var ul_id = "ul_"+lv+"_"+dm;
	
	if($(ul_id) && 4>eval(lv)){
		if($(ul_id).innerHTML != ""){
			if($(ul_id).style.display == "none"){
				$(ul_id).style.display = "";
			}else{
				$(ul_id).style.display = "none";
				
				var uls = $(ul_id).getElementsByTagName('ul');
				for(var i=0;i<uls.length;i++){
					if(uls[i].innerHTML != ""){
						uls[i].style.display = "none";
					}
				}
			}
		}else{
			creatNewChild(lv,dm);
			$(ul_id).style.display = "";
		}
	}
	
	changeClass(lv, dm);	
}

function checkSpanClick(lv,dm){
	if(lv=="4"){
		var obj = $("ch_"+lv+"_"+dm+"bt");
		if($("sp_"+lv+"_"+dm).className=='checked'){
			obj.checked;
		}
	}
	
	childrenChoice(lv,dm);
	cancelChoice(lv,dm);
}

function childrenChoice(lv, dm){
	var checUl = "ul_"+lv+"_"+dm;
	var obj = $("sp_"+lv+"_"+dm);
	var styleClass = obj.className; 
	var changeClass = 'unchecked';
	if(styleClass=='partial'||styleClass=='unchecked'){
		changeClass= 'checked';
	}
	obj.className=changeClass;
	if($(checUl)){
		var checs = $(checUl).getElementsByTagName('span');	
		for(var i=0;i<checs.length;i++){
			if(!checs[i].disabled){
				checs[i].className=changeClass;
			}
		}
	}
}

function cancelChoice(lv,dm){
    if($("sp_"+lv+"_"+dm)){
	var changeClass = $("sp_"+lv+"_"+dm).className;
	// 不选中时对上级的操作
	if(parseInt(lv)>1 && changeClass=='unchecked'){
	
		if(lv==4){
		 	parentUl = "ch_"+parentLv+"_"+parentDm;
		 	parentLv = parseInt(lv)-1;
			parentDm = ($("ch_"+lv+"_"+dm+"bt").value).substring(0,7);
			parentUl = "ul_"+parentLv+"_"+parentDm;
		}else{
			parentLv = parseInt(lv)-1;
			parentDm = dm.substring(0,dm.length-2);
			parentUl = "ul_"+parentLv+"_"+parentDm;
		}
		
		if($(parentUl)){
			var parentChecs = $(parentUl).getElementsByTagName('span');
			var flag = true;
			
			for(var i=0;i<parentChecs.length;i++){
				if(parentChecs[i].className!='unchecked'){
					flag = false;
					break;
				}	
			}
			
			if(flag){
				$('sp_'+parentLv+'_'+parentDm).className = 'unchecked';
				cancelChoice(parentLv,parentDm);
			}else{
				$('sp_'+parentLv+'_'+parentDm).className = 'partial';
				cancelChoice(parentLv,parentDm);
			}
		}
	}
	
	// 选中后对上一级进行的操作
	if(parseInt(lv)>1 && changeClass=='checked'){
		var parentLv;
		var parentDm;
		var parentUl;
		if(lv==4){
		 	parentUl = "ch_"+parentLv+"_"+parentDm;
		 	parentLv = parseInt(lv)-1;
			parentDm = ($("ch_"+lv+"_"+dm+"bt").value).substring(0,7);
			parentUl = "ul_"+parentLv+"_"+parentDm;
		}else{
			parentLv = parseInt(lv)-1;
			parentDm = dm.substring(0,dm.length-2);
			parentUl = "ul_"+parentLv+"_"+parentDm;
		}
		if($(parentUl)){
			
			var parentChecs = $(parentUl).getElementsByTagName('span');
			var flag = true;
			
			for(var i=0;i<parentChecs.length;i++){
				if(parentChecs[i].className!='checked'){
					flag = false;
					break;
				}	
			}
			if(flag){
				$('sp_'+parentLv+'_'+parentDm).className = 'checked';
				cancelChoice(parentLv,parentDm);
			}else{
				$('sp_'+parentLv+'_'+parentDm).className = 'partial';
				cancelChoice(parentLv,parentDm);
			}
		}
	}
	
	// 半选中时对上级的操作
	if(parseInt(lv)>1 && changeClass=='partial'){
				var parentLv = parseInt(lv)-1;
				var parentDm = dm.substring(0,dm.length-2);
				var parentUl = "ul_"+parentLv+"_"+parentDm;
				if($(parentUl)){
					$('sp_'+parentLv+'_'+parentDm).className = 'partial';
					cancelChoice(parentLv,parentDm);
				}
	}
	}
}

//加载子模块
function creatNewChild(lv,dm){
	var li_class = "";
	var a_class = "Open";

	var userName = $("userName").value;
	var jsdm = "";
	if($('jsdm')){
		jsdm = $('jsdm').value;
	}
	
	var ul_id = "ul_"+lv+"_"+dm;	// 子序列id
	var sq_id = "sp_"+lv+"_"+dm;	// 多选框id
	
	var nextLv = parseInt(lv)+1;
	var ischeck = "";
	
	
	if(4 == nextLv){
		li_class = "Child";
		a_class = "";
	}
	dwr.engine.setAsync(false);

	var divHtml = "";
	qxwhDWR.getGnmkList(lv,dm,userName,jsdm,function(data){
		if(data !=null && data.length >0){
			for(var i=0;i<data.length;i++){
				divHtml += "<li class=\""+li_class+"\" id=\"li_"+nextLv+"_"+data[i].dm+"\" style=\"float;left;clear:both;\">";
				if(data[i].mc != '（无）' && nextLv != 4){
					if($(sq_id).className=="checked"){
						divHtml += "<span type=\"span\" class=\"checked\" id=\"sp_"+nextLv+"_"+data[i].dm+"\""+ischeck+" onclick=\"checkSpanClick('"+nextLv+"','"+data[i].dm+"')\">";
					}else{
						divHtml += "<span type=\"span\" class="+data[i].styleclass+" id=\"sp_"+nextLv+"_"+data[i].dm+"\""+ischeck+" onclick=\"checkSpanClick('"+nextLv+"','"+data[i].dm+"')\">";
					}
					divHtml += "</span>";
				}else if(data[i].mc != '（无）' && nextLv == 4){
					var checked = ischeck;
					if(data[i].disabled == "disabled"){
						checked = "checked";
					}
					if($(sq_id).className=="checked"){
						divHtml += "<span type=\"span\" class=\"checked\" id=\"sp_"+nextLv+"_"+data[i].dm+"\""+ischeck+" onclick=\"checkSpanClick('"+nextLv+"','"+data[i].dm+"')\">";
					}else{
						divHtml += "<span type=\"span\" class="+data[i].styleclass+" id=\"sp_"+nextLv+"_"+data[i].dm+"\""+ischeck+" onclick=\"checkSpanClick('"+nextLv+"','"+data[i].dm+"')\">";
					}
					divHtml += "</span>";
					
					divHtml += "<input type=\"checkbox\" style=\"display: none;\" name=\"btns\" "+data[i].disabled+"  value=\""+dm+"_"+data[i].dm+"\" id=\"ch_"+nextLv+"_"+data[i].dm+"bt\""+checked+" onclick=\"checkboxClick('"+nextLv+"','"+dm+"bt')\">";
					divHtml += "</input>";
				}
				
				divHtml += "<a style=\"float:left;\" href=\"#\" title=\""+data[i].mc+"\" class=\""+a_class+"\" id=\"a_"+nextLv+"_"+data[i].dm+"\""; 
				divHtml += "onclick=\"clickParent('"+nextLv+"','"+data[i].dm+"');return false;\">";
				divHtml += data[i].jxmc;
				divHtml += "</a>";
				divHtml += "<ul id=\"ul_"+nextLv+"_"+data[i].dm+"\" style=\"display:none;clear:both;\">";											
				divHtml += "</ul>";
				divHtml += "</li>";
			}
		}
	});
	$(ul_id).innerHTML = divHtml;
	dwr.engine.setAsync(true);
}

//调换类名
function changeClass(lv,dm){
	var li_id = "li_"+lv+"_"+dm;
	var a_id = "a_"+lv+"_"+dm;
	var ul_id = "ul_"+lv+"_"+dm;
	
	if($(a_id) && $(a_id).className != ""){
		if($(ul_id).innerHTML != ""){
			var as = $(ul_id).getElementsByTagName('a');
			
			for(var i=0;i<as.length;i++){
				if(as[i].className = "Colse" && as[i].id.charAt(2) != '4'){
					as[i].className = "Open";
				}
			}
		}
		
		if($(a_id).className == "Open"){
			$(a_id).className = "Close";
		}else if($(a_id).className == "Close"){
			$(a_id).className = "Open";
		}
	}
	
	var li_num = document.getElementsByTagName('li').length;
	
	for(var i=0;i<li_num;i++){
		var obj = document.getElementsByTagName('li')[i];
		if(obj.className == "Current"){
			obj.className = "Child"
		}
	}
	
	if($(li_id) && $(li_id).className == "Child"){
		$(li_id).className = "Current";
	}
}

 function showGnmk(dm){
		 	var gnmkdm = $('gnmkdm').value;		 	
		 	var obj = $('ul_gnmk').getElementsByTagName('li');
		 	
		 	if(gnmkdm != ""){
		 		var li = "li_1_"+gnmkdm;
		 		for(var i=0;i<obj.length;i++){
		 			var lv = obj[i].id.charAt(3);
		 			if(obj[i].id != li && lv=='1'){
		 				obj[i].style.display = "none";
		 			}else{
		 				obj[i].style.display = "";
		 			}
		 		}
		 	}else{
		 		for(var i=0;i<obj.length;i++){
		 			obj[i].style.display = "";
		 		}
		 	}
		 }
</script>

<!-- 侧边栏 -->
<div class="leftframe04">	
	<div class="menulist">
		<div class="menutitle">
			<h3><span class="title">功能菜单
			</span></h3>
			<div class="CNLTreeMenu1" id="CNLTreeMenu1" style="width:100%;height:490px; overflow:auto;">
				<p class="choose_select">
					<html:select styleId="gnmkdm" property="gnmkdm" onchange="showGnmk()">
						<html:option value="">全部功能</html:option>
						<html:options collection="gnmkList" property="dm" labelProperty="mc"/>
					</html:select>
				</p>
				<ul id="ul_gnmk">
					<logic:notEmpty name="fpdxList">
						<logic:iterate name="fpdxList" id="lv1Fpdx" indexId="fpdx_index">
							<logic:equal name="lv1Fpdx" property="Lv" value="1">
								<logic:iterate name="lv1Fpdx" property="gnmkList" id="fpdx1" indexId="lv_index">
									<li id="li_${lv1Fpdx.Lv}_${fpdx1.dm }" style="float;left;clear:both;">
										<span class="unchecked" id="sp_${lv1Fpdx.Lv}_${fpdx1.dm }" onclick="checkSpanClick('${lv1Fpdx.Lv}','${fpdx1.dm }')" > </span>
										<a style="float:left;" href="#" class="Open" id="a_${lv1Fpdx.Lv}_${fpdx1.dm }"
											onclick="clickParent('${lv1Fpdx.Lv}','${fpdx1.dm}');return false;" title="${fpdx1.mc }" >
											${fpdx1.mc }
										</a>
										<ul style="clear:both;" id="ul_${lv1Fpdx.Lv}_${fpdx1.dm }">
																					
										</ul>
									</li>
								</logic:iterate>
							</logic:equal>
						</logic:iterate>
					</logic:notEmpty>
				</ul>
			</div>
		</div>
	</div>
</div>
<!-- 侧边栏 end-->