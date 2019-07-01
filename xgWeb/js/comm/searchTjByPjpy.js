


//点击项目名称其他
function clickPjxmQt(xmpy){
	var divid = "div_xm_"+xmpy;
	var qtid = "div_qt_xm_"+xmpy;
	var hiid = "hi_"+xmpy;
	
	var xmlx_click = getClickXmlx();//已选中项目类型
	var xmxz_click = getClickXmxz();//已选中项目性质
	
	var userStatus = $("userStatus").value;
	var userName = $("userName").value;
	var userDep = $("userDep").value;

	var divHtml ="<div class='search_advanced'><div class='prop-item'  style='border:none !important'>";
		divHtml+="<table width='80%' border='0' class='formlist open01'>";
		divHtml+="<tbody><tr><td colspan='4'>";
		divHtml+="<font color='orange' size='3'>"+xmpy+"字打头项目</font>";
		divHtml+="</td></tr>";
		
		divHtml+="<tr><td colspan='4'>";
		divHtml+="<div style=\"height:260px;overflow-x:hidden;overflow-y:auto;\"><table style=\"width:100%\">";

		//构造的tr数量
		var tr_num = "6";
		
		dwr.engine.setAsync(false);
		searchUtil.getXmInfoByTj(xmpy,xmlx_click,xmxz_click,function(data){
			if(data !=null && data.length >0){
				
				var size = data.length;
				
				//数据量过大，以实际数据为准
				if((size/2) > tr_num){
					tr_num = parseInt(size/2);
					
					//最后一行非满格的数量
					var sy_num = parseInt(size%2);
					if(sy_num != "0"){
						tr_num =parseInt(tr_num)+1;
					}
				}
				
				var n = size;
				for(var i=0;i<tr_num;i++){
					
					divHtml+="<tr style=\"width:100%;height:30px;\">";
					
					for(var j=0;j<2;j++){
					
						if(n <= size && n >= 0){
						
							n--;
							
							if(n!=-1){
							
								var xmid = "a_xm_"+data[n].xmdm;
								var xsid = "xm_mc_xs_"+data[n].xmdm;
								var ycid = "xm_mc_yc_"+data[n].xmdm;
							
								divHtml+="<td style=\"width:50%;\">";
								if(!$(xsid) && !$(ycid)){
									
									var xmmc = data[n].xmmc;
									if(xmmc.length > 20){
										xmmc = xmmc.substring(0,20)+"...";
									}
									divHtml+="<li><a href='#' class='bg_none' name='a_xmmc_mc'id=\""+ycid+"\" style=\"white-space: nowrap;\"";
									divHtml+="title=\""+data[n].xmmc+"\"";
									divHtml+="onclick=\"clickBj(this);";
									divHtml+="creatClickedTj('xmmc','项目名称','"+data[n].xmdm+"','"+data[n].xmmc+"',this);return false;\"";
									divHtml+=">"+xmmc+"</a></li>";
								}else{
									divHtml+="&nbsp;";
								}
								divHtml+="</td>";
							
							}else{
								divHtml+="<td style=\"width:50%;\">&nbsp;</td>";
							}	
						}else{
							divHtml+="<td style=\"width:50%;\">&nbsp;</td>";
						}
					}
					
					divHtml+="</tr>";
				}
			}
		});
				
		dwr.engine.setAsync(true);		
			
		divHtml+="</table></div>";
		divHtml+="</td></tr>";
		divHtml+="</tbody>";
		divHtml+="<tfoot><tr><td colspan='4'><div class='btn'>";
		divHtml+="<button onclick=\"hiddenZyDiv('"+divid+"');return false;\" id='buttonSave'>确定</button>";
		divHtml+="</div></td></tr></tfoot>";
		divHtml+="</table></div></div>";
		
	createOtherDiv("项目名称选择",divHtml,divid,"600","380");
}


//获得选中项目类型
function getClickXmlx(){

	//类型
	var xxmlx_num = document.getElementsByName("tj_xxmlx").length;
	var xxmlx_arr = new Array();
	var xxmlx_count = 0;
	
	for(var i=0;i<xxmlx_num;i++){
		var obj = document.getElementsByName("tj_xxmlx")[i];
		if(obj.className == "selectedValue"){
			var xxmlx_v = obj.id.replace("xxmlx_mc_xs_","");
			xxmlx_arr[xxmlx_count] = xxmlx_v;
			xxmlx_count++;
		}
	}
	
	return xxmlx_arr;
}

//获得选中项目性质
function getClickXmxz(){

	//性质
	var xxmxz_num = document.getElementsByName("tj_xxmxz").length;
	var xxmxz_arr = new Array();
	var xxmxz_count = 0;
	
	for(var i=0;i<xxmxz_num;i++){
		var obj = document.getElementsByName("tj_xxmxz")[i];
		if(obj.className == "selectedValue"){
			var xxmxz_v = obj.id.replace("xxmxz_mc_xs_","");
			xxmxz_arr[xxmxz_count] = xxmxz_v;
			xxmxz_count++;
		}
	}
	
	return xxmxz_arr;
}


//点击项目类型和性质
function clickPjxmmc(obj){
			
	var className = obj.className;
	
	if(className == "selectedValue"){
		obj.className = "";
	}else{
		obj.className = "selectedValue";
	}
	
	//清空评奖项目已选条件
	removeYxtjByLd("xmmc");
	
	
	//存在评奖项目条件
	if($("xmmc_ul")){
		//联动项目
		creatPjxmmcHtml("xmmc");
	}

}

//联动评奖项目
function creatPjxmmcHtml(ldlx){
	
	var ul_id = ldlx+"_ul";
	
	var xmlx_click = getClickXmlx();
	//性质id
	var xmxz_click = getClickXmxz();
	
	var v4Path = stylePath;

	var xzdm = "";

    if($("xzdm") != null){
        xzdm = $("xzdm").value;
    }

	//参数
 	var parameter = {
 		"xmlx":xmlx_click.join("!!@@!!"),
 		"xmxz":xmxz_click.join("!!@@!!"),
 		"ldlx":ldlx,
 		"stylePath":v4Path,
		"xzdm":xzdm
	};

	var url="seachTjManage.do?method=creatPjxmHtml";

	jQuery.ajaxSetup({async:false});
	
	if($(ul_id)){
		jQuery("#"+ul_id).load(url,parameter,function(){
		
			var xm_num_id = ldlx+"_num";
			var xm_more_id = ldlx+"_more";
			var num = $(xm_num_id).value;
			
			if(num < 12){
				if($(xm_more_id)){
					$(xm_more_id).style.display = "none";
				}
			}else{
				$(xm_more_id).style.display = "";
				$(xm_more_id).innerHTML = "更多";
				$(xm_more_id).className = "more_down";
			}
		});
	}
	
	jQuery.ajaxSetup({async:true});	
}

