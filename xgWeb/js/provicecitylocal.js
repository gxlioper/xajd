/**
 * 省市县联动下拉框通用js
 * 主函数proviceCiyyLocalMain
 * 参数：主函数传入参数：json对象，例如{type:"",id:"",flag:"",width:""}
 * type类型固定为：1.add 2.edit 3.view
 * id为td省市县业务字段id
 * flag：1.yxxdz(有详细地址) 2.wxxdz(无详细地址)
 * width:
 * @param 
 * @return
 */




/*传入需要保存的字段id,获取td单元格对象*/
function getBdzdParents(obj){
 if(obj.id == "" || obj.id == null){
	 showAlert("未传入省市县业务字段id！");
	 return false;
 }
 var parentTdObj = "";

 if(obj.flag == "wxxdz"){
	parentTdObj = jQuery("#"+obj.id).parent();
 }else{
    parentTdObj = jQuery("#"+obj.id);
 }
 if(parentTdObj.length == 0){
	showAlert("省市县业务字段id配置不正确！");
	return false;
 }
 return parentTdObj;
}

//得到省信息
function getProviceMap(){
	var url = "comm_provicectiylocal.do?method=getProviceMap";
	var data= {};
	var ProviceMap = null;
	jQuery.ajax({
		type:'post',
		url:url,
		dataType:'json',
		contentType:"application/x-www-form-urlencoded; charset=UTF-8",
		data:data,
		async: false,
		success:function(result){
		    //如果未从后台得到信息，返回false
			if(result!=null||result!="null" || result != "undifined" || result != ""){
				ProviceMap = result;
			}
		}
	});
	return ProviceMap;
}

//得到市信息
function getCityMap(provicedm){
	var url = "comm_provicectiylocal.do?method=getCtiyMap";
	var data= {provicedm:provicedm};
	var CityMap = null;
	jQuery.ajax({
		type:'post',
		url:url,
		dataType:'json',
		contentType:"application/x-www-form-urlencoded; charset=UTF-8",
		data:data,
		async: false,
		success:function(result){
		    //如果未从后台得到信息，返回false
			if(result!=null||result!="null" || result != "undifined" || result != ""){
				CityMap = result;
			}
		}
	});
	return CityMap;
}

//得到县区信息
function getLocalMap(provicedm,citydm){
	var url = "comm_provicectiylocal.do?method=getLocalMap";
	var data= {provicedm:provicedm,citydm:citydm};
	var LocalMap = null;
	jQuery.ajax({
		type:'post',
		url:url,
		dataType:'json',
		contentType:"application/x-www-form-urlencoded; charset=UTF-8",
		data:data,
		async: false,
		success:function(result){
		    //如果未从后台得到信息，返回false
			if(result!=null||result!="null" || result != "undifined" || result != ""){
				LocalMap = result;
			}
		}
	});
	return LocalMap;
	
}

function getView(provicedm){
	var url = "comm_provicectiylocal.do?method=getSsxQcName";
	var data= {provicedm:provicedm};
	var dqmc = null;
	jQuery.ajax({
		type:'post',
		url:url,
		dataType:'json',
		contentType:"application/x-www-form-urlencoded; charset=UTF-8",
		data:data,
		async: false,
		success:function(result){
		    //如果未从后台得到信息，返回false
			if(result!=null||result!="null" || result != "undifined" || result != ""){
				dqmc = result["qcname"];
			}
		}
	});
	return dqmc;
}

//页面初始化拼接html传入类型add或者edit或者view
function appendInitHtml(obj){
	var parentTdObj = getBdzdParents(obj);
	if(parentTdObj == false){
		return false;
	}
	var type = obj.type;
	addhtml(obj,parentTdObj);
	
}


//主函数需要传入object对象{type:"add"...}
function proviceCiyyLocalMain(obj){
	appendInitHtml(obj);
}

//省下拉框change事件
function proviceChange(obj,ywobj){
	var provicedm = jQuery(obj).val();
	/*判断省代码是1也就是初值-"请选择"的时候赋值地市(city)和区县(local)框为初值-"请选择",
	   业务字段值赋值空
	否则赋值*/
	if(provicedm == '1'){
		jQuery("#"+ywobj.id).val("");
		jQuery("#city").html("<option value='1' selected='selected'>请选择</option>");
		jQuery("#local").html("<option value='1' selected='selected'>请选择</option>");
	}else{
		//否则后台获取地市信息,赋值给地市框,区县框赋给初值,业务字段赋给省代码
		jQuery("#"+ywobj.id).val(provicedm);
		var provicedm = provicedm.substr(0,2);
		var CityMap = getCityMap(provicedm);
		var CityObj = jQuery("#city");
	    var CityHtml ="<option value='1' selected='selected'>请选择</option>";
	    for(var i=0;i<CityMap.length;i++){
	    	CityHtml += "<option value='"+CityMap[i]['citydm']+"'>"+
	    	CityMap[i]['cityname']+"</option >"
	    }
	    jQuery(CityObj).html(CityHtml);
		jQuery("#local").html("<option value='1' selected='selected'>请选择</option>");
	}
	//省下拉框值改变，地市下拉框和县下拉框都应赋给初值-"请选择"
	jQuery("#city").val("1");
	jQuery("#local").val("1");
}



//市下拉框change事件
function cityChange(obj,ywobj){
	var citydm = jQuery(obj).val();
	if(citydm == '1'){
		jQuery("#"+ywobj.id).val(jQuery("#provice").val());
		jQuery("#local").html("<option value='1' selected='selected'>请选择</option>");
	}else{
		jQuery("#"+ywobj.id).val(citydm);
		var provicedm = citydm.substr(0,2);
		var citydm = citydm.substr(2,2);
		var LocalMap = getLocalMap(provicedm,citydm);
		var LocalObj = jQuery("#local");
	    var LocalHtml ="<option value='1' selected='selected'>请选择</option>";
	    for(var i=0;i<LocalMap.length;i++){
	    	LocalHtml += "<option value='"+LocalMap[i]['localdm']+"'>"+
	    	LocalMap[i]['localname']+"</option >"
	    }
	    jQuery(LocalObj).html(LocalHtml);
	}
	//地市下拉框值改变,县下拉框都应赋给初值-"请选择"
	jQuery("#local").val("1");
}


//区县下拉框change事件
function localChange(obj,ywobj){
	var localdm = jQuery(obj).val();
	if(localdm == '1'){
		jQuery("#"+ywobj.id).val(jQuery("#city").val());
	}else{
		jQuery("#"+ywobj.id).val(localdm);
	}
}

//根据用户类型去构建初始化html对象，绑定change事件，直接拼接到当前操作页面的td中
function addhtml(obj,parentTdObj){
	var ywvalue = jQuery("#"+obj.id).val();
	if(obj.type == "edit" || obj.type == "add"){
		var ProviceMap = getProviceMap();
		if(ProviceMap == false){
			showAlert("数据库无省信息！");
			return false;
		}
		var ProviceHtml = "";
		ProviceHtml =  "省<select id='provice'>";
					if(obj.type == 'edit'){
						ProviceHtml += "<option value='1' >请选择</option>";
					}else{
						ProviceHtml += "<option value='1' selected='selected'>请选择</option>";
					}
		               
		               for(var i=0;i<ProviceMap.length;i++){
		            	   ProviceHtml = ProviceHtml+  "<option value='"+ProviceMap[i]['provicedm']+"'>"+
		            	   ProviceMap[i]['provicename']+"</option >"
		               }
		               ProviceHtml +="</select>";
		var ProviceObj = jQuery(ProviceHtml);
		var CityHtml = "";
		//判断是否为修改类型并且该值不为空，该值是数字
		if(obj.type == 'edit' &&　ywvalue != null && ywvalue != "" && !isNaN(parseInt(ywvalue))){
			var provicedm = ywvalue.substr(0,2);
			var CityMap = getCityMap(provicedm);
			if(CityMap == false){
				showAlert("数据库无地市信息！");
				return false;
			}
			CityHtml = "市<select id='city'>"+"<option value='1' >请选择</option>";
		    for(var i=0;i<CityMap.length;i++){
		    	CityHtml += "<option value='"+CityMap[i]['citydm']+"'>"+
		    	CityMap[i]['cityname']+"</option >"
		    }
			 CityHtml +="</select>";
		}else{
			CityHtml = "市<select id='city'>"+
	         "<option value='1' selected='selected'>请选择</option>"+
             "</select>";
		}
		var CityObj = jQuery(CityHtml);
		//var LocalMap = getLocalMap();
		var LocalHtml = "";
		//判断是否为修改类型并且该值不为空，该值是数字并且该值的中间两位不是'0'
		if(obj.type == 'edit' &&　ywvalue != null && ywvalue != "" && !isNaN(parseInt(ywvalue)) && ywvalue.substr(2,2) != '00'){
			var provicedm = ywvalue.substr(0,2);
			var citydm = ywvalue.substr(2,2);
			var LocalMap = getLocalMap(provicedm,citydm);
			LocalHtml ="县<select id='local'><option value='1' >请选择</option>";
		    for(var i=0;i<LocalMap.length;i++){
		    	LocalHtml += "<option value='"+LocalMap[i]['localdm']+"'>"+
		    	LocalMap[i]['localname']+"</option >"
		    }
		    LocalHtml += "</select>";
		}else{
			  LocalHtml = "县<select id='local'>"+
		         "<option value='1' selected='selected'>请选择</option>"+
		         "</select>";
		}
	  
		var LocalObj = jQuery(LocalHtml);
		jQuery(ProviceObj).change(function(){
			proviceChange(this,obj);
		});
		jQuery(CityObj).change(function(){
			cityChange(this,obj);
		});
		jQuery(LocalObj).change(function(){
			localChange(this,obj);
		});
		if(obj.type == "edit"){
			if(obj.type == 'edit' &&　ywvalue != null && ywvalue != "" && 
			 !isNaN(parseInt(ywvalue))){
				jQuery(ProviceObj).val(ywvalue.substr(0,2)+"0000");
				if(ywvalue.substr(2,2) != "00"){
					jQuery(CityObj).val(ywvalue.substr(0,4)+"00");
					if(ywvalue.substr(4,2) != "00"){
						jQuery(LocalObj).val(ywvalue);
					}
				}
			}
		}
		//如果传入了下拉框width,赋值width,否则直接赋值120px
		if(obj.width){
			jQuery(ProviceObj).css({width:obj.width});
			jQuery(CityObj).css({width:obj.width});
			jQuery(LocalObj).css({width:obj.width});
		}else{
			jQuery(ProviceObj).css({width:"120px"});
			jQuery(CityObj).css({width:"120px"});
			jQuery(LocalObj).css({width:"120px"});
		}
		if(obj.flag == "yxxdz"){
			jQuery(parentTdObj).after(ProviceObj,'',CityObj,'',LocalObj);
		}else{
			jQuery(parentTdObj).append(ProviceObj,'',CityObj,'',LocalObj);
		}
		
	}else if(obj.type == 'view'){
		if(ywvalue != null && ywvalue != "" && !isNaN(parseInt(ywvalue))){
			var dqmc = getView(ywvalue);
			if(obj.flag == "yxxdz"){
				jQuery(parentTdObj).after(dqmc)
			}else{
				jQuery(parentTdObj).append(dqmc);
			}
			
		}else{
//			if(ywvalue != null && ywvalue != ""){
//				jQuery(parentTdObj).append(ywvalue);
//			}else{
				return false;
//			}
			
		}
		
		
	}else{
		showAlert("省市县地区选择配置不正确！");
		return false;
	}
	
			
}