<%@ page language="java" contentType="text/html; charset=GBK"%>
<script type='text/javascript' src='/xgxt/dwr/interface/yhgl.js'></script>
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

function checkboxClick(lv,dm){
	childrenChoice(lv,dm);
	cancelChoice(lv,dm);
}

function childrenChoice(lv, dm){
	var checUl = "ul_"+lv+"_"+dm;
	var obj = $("ch_"+lv+"_"+dm);
	var ischecked = obj.checked;
	
	if($(checUl)){
		var checs = $(checUl).getElementsByTagName('input');	
		for(var i=0;i<checs.length;i++){
			if(!checs[i].disabled){
				checs[i].checked = ischecked;
			}
		}
	}
}

function cancelChoice(lv,dm){
	var obj = $("ch_"+lv+"_"+dm);
	var ischecked = obj.checked;

	// 次级全部为非选，上级去掉选择
	if(parseInt(lv)>1 && !ischecked){
		var parentLv = parseInt(lv)-1;
		var parentDm = dm.substring(0,dm.length-2);
		var parentUl = "ul_"+parentLv+"_"+parentDm;
		
		if($(parentUl)){
			var parentChecs = $(parentUl).getElementsByTagName('input');
			var flag = true;
			
			for(var i=0;i<parentChecs.length;i++){
				if(parentChecs[i].checked){
					flag = false;
					break;
				}	
			}
			
			if(flag){
				$('ch_'+parentLv+'_'+parentDm).checked = false;
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
	var yhm = "";
	
	if($('yhm')){
		yhm = $('yhm').value;
	}
	
	var ul_id = "ul_"+lv+"_"+dm;	// 子序列id
	var ch_id = "ch_"+lv+"_"+dm;	// 多选框id
	
	var nextLv = parseInt(lv)+1;
	var ischeck = "";
	
	
	if($(ch_id)){
		ischeck = $(ch_id).checked ? "checked" : "";
	}
	
	if(4 == nextLv){
		li_class = "Child";
		a_class = "";
	}

	dwr.engine.setAsync(false);

	var divHtml = "";
	
	yhgl.getGnmkList(lv,dm,userName,yhm,function(data){
		if(data !=null && data.length >0){
			for(var i=0;i<data.length;i++){
				divHtml += "<li class=\""+li_class+"\" id=\"li_"+nextLv+"_"+data[i].dm+"\">";
				if(data[i].mc != '（无）' && nextLv != 4){
					divHtml += "<input type=\"checkbox\" id=\"ch_"+nextLv+"_"+data[i].dm+"\""+ischeck+" onclick=\"checkboxClick('"+nextLv+"','"+data[i].dm+"')\">";
					divHtml += "</input>";
				}else if(data[i].mc != '（无）' && nextLv == 4){
					var checked = ischeck;
					var disabled = "";
					if(data[i].jsyy == "yes"){
						checked = "checked";
						disabled = "disabled";
					}else if(data[i].yhyy == "yes"){
						checked = "checked";
					}
					
					divHtml += "<input type=\"checkbox\" name=\"btns\" "+disabled+" value=\""+dm+"_"+data[i].dm+"\" id=\"ch_"+nextLv+"_"+dm+"bt\""+checked+" onclick=\"checkboxClick('"+nextLv+"','"+dm+"bt')\">";
	
					divHtml += "</input>";
				}
				
				divHtml += "<a href=\"#\" title=\""+data[i].mc+"\" class=\""+a_class+"\" id=\"a_"+nextLv+"_"+data[i].dm+"\""; 
				divHtml += "onclick=\"clickParent('"+nextLv+"','"+data[i].dm+"');return false;\">";
				divHtml += data[i].jxmc;
				divHtml += "</a>";
				divHtml += "<ul id=\"ul_"+nextLv+"_"+data[i].dm+"\" style=\"display:none\">";											
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
									<li id="li_${lv1Fpdx.Lv}_${fpdx1.dm }">
										<input type="checkbox"   name="chec_${lv1Fpdx.Lv}" value="${fpdx1.dm }" id="ch_${lv1Fpdx.Lv}_${fpdx1.dm }" onclick="checkboxClick('${lv1Fpdx.Lv}','${fpdx1.dm }')"/>
										<a href="#" class="Open" id="a_${lv1Fpdx.Lv}_${fpdx1.dm }"
											onclick="clickParent('${lv1Fpdx.Lv}','${fpdx1.dm}');return false;" title="${fpdx1.mc }" >
											${fpdx1.mc }
										</a>
										<ul id="ul_${lv1Fpdx.Lv}_${fpdx1.dm }">
																					
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