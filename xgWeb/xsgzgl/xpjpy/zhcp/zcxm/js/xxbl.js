function setThead(){
	var zcxm = jQuery("font[name=zcxm]");
	
	jQuery.each(zcxm,function(i,n){
		
		if ("ejxm" == jQuery(n).attr("type")){
			jQuery("th[sortname="+jQuery(n).attr("xmbm")+"]").css("background-color","#9BDF70");
		}
	});
}

function initXxblTable(){
	var gridSetting = {
		caption:"详细比例设置列表 ",
		pager:"pager",
		multiselect:false,
		url:"xpj_zcxm.do?method=getXxblList"
	};
		
	var zcxm = jQuery("font[name=zcxm]");
	var zcxmjb = jQuery("#zcxmjb").val();
	var colList;
	
	if ("1" == zcxmjb){
		colList=[
		   {label:'年级',name:'bmdm', index: 'bmdm',key:true},
		   {label:'tjzt',name:'tjzt', index: 'tjzt',hidden:true}
		];
	} else {
		colList=[
		   {label:'院系',name:'xymc', index: 'bmdm',key:true},
		   {label:'bmdm',name:'bmdm', index: 'bmdm',hidden:true},
		   {label:'tjzt',name:'tjzt', index: 'tjzt',hidden:true}
		];
	}

	jQuery.each(zcxm,function(i,n){
		var xmbm = jQuery(n).attr("xmbm");
		var xmdm = jQuery(n).attr("xmdm");
		var qzbl = jQuery(n).attr("qzbl");
		
		var json = {
			label:jQuery(n).attr("xmmc"),
			name:xmbm,
			index:xmbm
		};
		
		json["formatter"] = function(cell,rowObject){
			
			if (rowObject["tjzt"] == "1"){
				return cell;
			}
			
			var html = "<input onblur=\"saveZcbl(this,'";
				html+= rowObject["bmdm"];
				html+= "','",
				html+= xmdm,
				html+= "')\" type='text'";
				html+= " style='width:50px;";
				
				if (cell != qzbl){
					html += "border:1px solid #FF0000;";
				}
				
				html+= " ' maxlength='10' value='";
				html+= cell==null ? "" : cell;
				html+= "' />%";
			return html;
		};
		
		colList.push(json);
	});
	gridSetting["colList"] = colList;
	jQuery("#dataTable").initGrid(gridSetting);
}

function saveZcbl(obj,bmdm,xmdm){
	
	var qzbl = obj.value;
	
	if (!/^\d+(?=\.{0,1}\d+$|$)/.test(qzbl)){
		showAlertDivLayer("非法数字！",{},{"clkFun":function(){
			obj.focus();
		}});
		return ;
	}
	
	if (qzbl > 100){
		showAlertDivLayer("权重比例限设置最大值 为100！");
		return ;
	}
	
	jQuery.post("xpj_zcxm.do?method=updateXxbl",{bmdm:bmdm,qzbl:qzbl,xmdm:xmdm},function(data){
	},"json");
	
}