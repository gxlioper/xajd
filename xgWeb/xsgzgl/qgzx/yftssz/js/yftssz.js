var year = 0;
var months=[];
var days=[];

function checkDays(obj){
	var days = jQuery(obj).val();
	if(parseInt(days)>31){
		alertInfo("天数不能大于31天!",function(tag){
			if(tag=="ok"){
				return false;
			}
		});
		return false;
	}
}
function changeyf() {
	var sel = document.getElementById("xn");
	var xn = sel.options[sel.selectedIndex].value;
	var parameter = {};
	parameter["xn"]=xn;	
	var url = 'qgzx_yftssz.do?method=yftssz';
	jQuery.ajaxSetup({async:false});
	jQuery.post(url,parameter,function(data){
		for(var i=0;i<12;i++){
			jQuery("#yf-"+(i+1)).val(data[i]);
		}
	},'json');
	jQuery.ajaxSetup({async:true});
}

function bcYftssz(){
	year = jQuery("#xn").val();
	for(var i=1;i<13;i++){
		months.push(i);
		days.push(jQuery("#yf-"+i).val());
	}
	var parameter = {};
	parameter["xn"]=year;	
	parameter["months"]=months;	
	parameter["days"]=days;	
	var url = 'qgzx_yftssz_ajax.do?method=bcYftssz';
	jQuery.ajaxSetup({async:false});
	jQuery.post(url,parameter,function(data){
		if(data["message"]=="保存成功！"){
   		 showAlert(data["message"],{},{"clkFun":function(){
   		 	if(parent.window){
   		 		refershParent();
				}
		 }});
   	 	}else{
   		 showAlert(data["message"]);
   		}
	},'json');
	jQuery.ajaxSetup({async:true});
	
}