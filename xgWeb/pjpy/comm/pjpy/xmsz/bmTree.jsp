<%@ page language="java" contentType="text/html; charset=GBK"%>
<script type='text/javascript' src='/xgxt/dwr/interface/pjpyCommService.js'></script>
<script language="javascript" defer="defer">
//点击分配对象生成树
function clickBm(lv,bmdm,djlx,nj,check_id){
	
	var fpdx = $("fpdx").value;
	var ul_id = "ul_"+lv+"_"+bmdm;
	
	var flag = true;
	
	if(fpdx == "bj" && djlx == "bj"){
		flag = false;
	}else if(fpdx == "zy" && djlx == "zy"){
		flag = false;
	}else if(fpdx == "xy" && djlx == "xy"){
		flag = false;
	}else if(fpdx == "nj" && djlx == "nj"){
		flag = false;
	}
	
	if($(ul_id) && flag){
		if($(ul_id).innerHTML != ""){
			if($(ul_id).style.display == "none"){
				$(ul_id).style.display = "";
			}else{
				$(ul_id).style.display = "none";
			}
		}else{
			setTimeout("creatNewBm('"+lv+"','"+bmdm+"','"+djlx+"','"+nj+"','"+check_id+"');",100);
			$(ul_id).style.display = "";
		}
	}
	
	//改变类名
	changeBmClass(djlx,bmdm);
}

//生成新部门
function creatNewBm(lv,bmdm,djlx,nj,check_id){

	var bmlx = "";
	var li_class = "";
	var a_class = "Open";
	
	if(djlx == "nj"){
		bmlx = "xy";
	}else if(djlx == "xy"){
		bmlx = "zy";
	}else if(djlx == "zy"){
		bmlx = "bj";
	}
	
	var fpdx = $("fpdx").value;
	
	if(fpdx == "bj" && bmlx == "bj"){
		li_class = "Child";
		a_class = "";
	}else if(fpdx == "zy" && bmlx == "zy"){
		li_class = "Child";
		a_class = "";
	}else if(fpdx == "xy" && bmlx == "xy"){
		li_class = "Child";
		a_class = "";
	}else if(fpdx == "nj" && bmlx == "nj"){
		li_class = "Child";
		a_class = "";
	}

	var userStatus = $("userStatus").value;
	var userName = $("userName").value;
	var userDep = $("userDep").value;
	var bmInfo=[bmlx,nj,bmdm];
	
	var ul_id = "ul_"+lv+"_"+bmdm;
	var nextLv = parseInt(lv)+1;

	dwr.engine.setAsync(false);

	var divHtml = "";
	
	pjpyCommService.getBmList(bmInfo,userStatus,userName,userDep,function(data){
		if(data !=null && data.length >0){
			for(var i=0;i<data.length;i++){
				divHtml += "<li class=\""+li_class+"\" id=\"li_"+bmlx+"_"+data[i].dm+"\">";
				divHtml += "<input type=\"checkbox\" name=\"params\" id=\""+check_id+"_"+i+"\" value=\""+data[i].dm+"\" onclick=\"check(this.id)\"/>";
				divHtml += "<a href=\"#\" title=\""+data[i].allmc+"\" class=\""+a_class+"\" id=\"a_"+bmlx+"_"+data[i].dm+"\""; 
				divHtml += "onclick=\"clickBm('"+nextLv+"','"+data[i].dm+"','"+data[i].bmlx+"','"+data[i].nj+"','"+check_id+"_"+i+"');return false;\">";
				divHtml += data[i].mc;
				divHtml += "</a>";
				divHtml += "<input type=\"hidden\" id=\"nj_"+bmlx+"_"+data[i].dm+"\" value=\""+data[i].nj+"\">";
				divHtml += "</li>";
				divHtml += "<ul id=\"ul_"+nextLv+"_"+data[i].dm+"\" style=\"display:none\">";											
				divHtml += "</ul>";
			}
		}
	});

	$(ul_id).innerHTML = divHtml;
	
	dwr.engine.setAsync(true);
}

//调换类名
function changeBmClass(djlx,bmdm){
	var li_id = "li_"+djlx+"_"+bmdm;
	var a_id = "a_"+djlx+"_"+bmdm;
	if($(a_id) && $(a_id).className != ""){
		if($(a_id).className == "Open"){
			$(a_id).className = "Close";
		}else{
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
		//设置选中的值
		setClickValue(djlx,bmdm);
	}else{
		$("clickBmjb").value = "";
		$("clickBmdm").value = "";
		$("clickBmmc").value = "";
		$("clickBmnj").value = "";
	}
}

//设置选中的值
function setClickValue(djlx,bmdm){

	if($("clickBmjb")){
		$("clickBmjb").value = djlx;
	}
	
	if($("clickBmdm")){
		$("clickBmdm").value = bmdm;
	}
	
	if($("clickBmmc")){
		var a_id = "a_"+djlx+"_"+bmdm;
		$("clickBmmc").value = $(a_id).title;
	}
	
	if($("clickBmnj")){
		var nj_id = "nj_"+djlx+"_"+bmdm;
		if($(nj_id)){
			$("clickBmnj").value = $(nj_id).value;
		}
	}
}

function check(id){
	var obj = $(id);
	if(obj.checked){
		for(var i=0;i<100000;i++){
			if($(obj.id+"_"+i)){
				$(obj.id+"_"+i).checked="checked";
				check(obj.id+"_"+i);
			}else{
				break;
			}
		}
	}else{
		for(var i=0;i<100000;i++){
			if($(obj.id+"_"+i)){
				$(obj.id+"_"+i).checked="";
				check(obj.id+"_"+i);
			}else{
				break;
			}
		}
	}
}
</script>

<!-- 侧边栏 -->						
	<div class="menulist">
		<div class="menutitle">
			<h3><span class="title">请选择</span></h3>
			<div class="CNLTreeMenu1" id="CNLTreeMenu1" style="width:96%;">
				<ul>
					<logic:notEmpty name="fpdxList">
						<logic:iterate name="fpdxList" id="lv1Fpdx" indexId="fpdx_index">
							<logic:equal name="lv1Fpdx" property="Lv" value="1">
								<logic:iterate name="lv1Fpdx" property="bmList" id="fpdx1" indexId="lv_index">
									<li class="${lv1Fpdx.liName }" id="li_${fpdx1.bmlx }_${fpdx1.dm }">
										<input type="checkbox" name="params" id="ur_${fpdx_index}_${lv_index}" value="${fpdx1.dm }"  onclick="check(this.id)"/>
										<a href="#" title="${fpdx1.allmc }" class="${lv1Fpdx.aName }" id="a_${fpdx1.bmlx }_${fpdx1.dm }"
											onclick="clickBm('1','${fpdx1.dm }','${fpdx1.bmlx }','${fpdx1.nj }','ur_${fpdx_index}_${lv_index}');return false;">
											${fpdx1.mc }
										</a>
										<ul id="ul_1_${fpdx1.dm }">
										
										</ul>
									</li>
								</logic:iterate>
							</logic:equal>
						</logic:iterate>
					</logic:notEmpty>
				</ul>
			</div>
		</div>

		<input type="hidden" id="fpdx" value="${szfw }"/>
		<input type="hidden" id="clickBmnj" value="" />
		<input type="hidden" id="clickBmjb" value="" />
		<input type="hidden" id="clickBmdm" value="" />
		<input type="hidden" id="clickBmmc" value="" />
	</div>
<!-- 侧边栏 end-->