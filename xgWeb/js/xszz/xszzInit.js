//学生资助初始化js
//若为某元素添加事件或属性请在这里添加
//注释一定要加！
var gjzxdk = "5003";//国家助学贷款
var xshjxf = "1003";//学生缓交学费
var jyfzjj = "3002";//教育发展基金
var jtqkdc = "5001";//家庭情况调查



//学生资助初始化
function xszzInit() {
	var xmdm = $('xmdm').value;
	var xxdm = $('xxdm').value;
	
	if (gjzxdk==xmdm) {//国家助学贷款
		
	} else if(xshjxf==xmdm) { //学生缓交学费
		if ("10395"==xxdm){//闽江学院
			setHjxf();
		}else{
		
		}
	}else if(jyfzjj == xmdm){
		if("10589" == xxdm) {// 海南大学
			setXxjd();
			setHjxfHndx('xxsheng','xxshi','xxxian');
		}
	}else if(jtqkdc == xmdm){ //家庭情况调查
		if("10589" == xxdm){ //海南大学
			if($("pkxjb")){
				if($('sfpkx').value!="是"){
					$("pkxjb").disabled = true;
				}
				setJtqkdc();
			}
		}
	}
}


//学生缓交学费
function setHjxf(){
	if ($('qjzfy')) {//欠交总费用设为只读
		$('qjzfy').readOnly=true;
	}
	
		//为欠缴总费用设置值
	if ($('qjzfy') && $('yjzfy') && $('sjzfy')) {
		$('yjzfy').attachEvent('onblur',function(){
			hjxf_qjzfy();
		});
		
		$('sjzfy').attachEvent('onblur',function(){
			hjxf_qjzfy();
		});
	}
}

//缓交学费--为欠缴总费用设置值
function hjxf_qjzfy(){	
	$('qjzfy').value = Number($('yjzfy').value) - Number($('sjzfy').value);
}

//海南大学 --省市区联动
function setHjxfHndx(sheng,shi,xian){
	//所在省,市，县
	if($(sheng) && $(shi) && $(xian)){
		var options = [{dm:"",mc:"--请选择--"}];
		
		DWRUtil.addOptions(shi,options,"dm","mc");
		DWRUtil.addOptions(xian,options,"dm","mc");
		
		$(sheng).attachEvent('onchange', function(){loadShi(sheng,shi,xian)});
		$(shi).attachEvent('onchange', function(){loadXian(shi,xian)});
	}
	
	//初始化省
	initsheng(sheng);
	if($("select_"+sheng).value != ""){
		$(sheng).value = $("select_" + sheng).value;

		// 初始化市县
		dwr.engine.setAsync(false);
		loadShi(sheng, shi, xian);
		dwr.engine.setAsync(true);
		
		$(shi).value = $("select_" + shi).value;
		$(xian).value = $("select_" + xian).value;
	}
}

//初始化省
function initsheng(sheng){
	if($(sheng)){
		var id = sheng;
		var tableName = "dmk_qx";
		var pk = "qxdm";
		var msg = "--请选择--"
		var pkValue = "__0000";
		var bsf = "like"
		var dm = "qxdm";
		var mc = "qxmc";
		
		dwr.engine.setAsync(false);
		getXszzInfo.getXszzLikeList(tableName, dm, mc, msg, pk, bsf, pkValue,function(data){
			if(data!=null){
				DWRUtil.removeAllOptions(id);
				DWRUtil.addOptions(id,data,"dm","mc");
			}
		});
		dwr.engine.setAsync(true);
	}
}

//海南大学--学习阶段
function setXxjd(){
	if($('xxjd')){
		var id = "xxjd";
		var options = [{dm:"",mc:"--请选择--"},{dm:"大学（含高职、大专）",mc:"大学（含高职、大专）"},{dm:"高中（含职高、中专、中技）",mc:"高中（含职高、中专、中技）"},
		               {dm:"初中",mc:"初中"},{dm:"小学",mc:"小学"}];
		DWRUtil.removeAllOptions(id);
		DWRUtil.addOptions(id,options,"dm","mc");
		$('xxjd').value = $('select_xxjd').value;
	}
}

//海南大学家庭情况调查
function setJtqkdc(){
	if($('sfpkx')){
		$('sfpkx').attachEvent('onchange', function(){
			var value = $('sfpkx').value;
			if(value=="是"){
				$('pkxjb').disabled="";
			}else{
				$('pkxjb').value="";
				$('pkxjb').disabled=true;
			}
		});
	}
}
