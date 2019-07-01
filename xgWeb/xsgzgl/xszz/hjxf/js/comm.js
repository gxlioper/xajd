/**
 * 检验字数长度
 * @return
 */
function checkzs(){
	if(jQuery("#sqyy").val().length > 500){
		showAlertDivLayer("家庭经济收入主要来源及困难情况控制在500字之内！");
		return false;
	}
}

/**
 * 验证是否存在空项
 * @param ids 要验证的控件id "-"分隔
 * @return
 */
function check(ids){
	var id=ids.split("-");
	for(var i=0;i<id.length;i++){
		var lddm=jQuery.trim(jQuery("#"+id[i]).val()) || jQuery.trim(jQuery("#"+id[i]).text());
		if(lddm==null||""==lddm){
			return false;
		}
	}
	return true;
}


//缓交学费获取等级名称以及重复验证
function selectXsYzFz(xh,xn,xq,flag){
	var rs = null;
	var data = {xh:xh,flag:flag,xn:xn,xq:xq}
	var url = "hjxf_sq.do?method=selectXsYzFz";
	jQuery.ajax({
	type:'post',
	url:url,
	dataType:'json',
	contentType:"application/x-www-form-urlencoded; charset=UTF-8",
	data:data,
	async: false,
	success:function(result){
			rs = result;
	 }
    });
	if(jQuery.trim(rs['djmc']) == ''){
		jQuery("#knsdj").text('家庭经济不困难');
		jQuery("#pkdj").val('家庭经济不困难');
	}else{
		jQuery("#knsdj").text(rs['djmc']);
		jQuery("#pkdj").val(rs['djmc']);
	}
	
	var datalist = rs['hjxx'];
	var html = ""
	  html += "<tr>"+ 
		"<th width='10%'>学年</th>"+
		"<th width='10%'>学期</th>"+	
	    "<th width='25%'>申请时间</th>"+
		"<th width='20%'>缓交金额(元)</th>"+
		"<th width='35%'>缴清截止时间</th>"+
	    "</tr>";
	if(datalist.length>0){
		for(var i = 0;i < datalist.length;i++){
			html += "<tr width='100%'>"+
				    "<td width='10%'>"+datalist[i]['xn']+"</td>"+
			        "<td width='10%'>"+datalist[i]['xq']+"</td>"+
			        "<td width='25%'>"+datalist[i]['sqsj']+"</td>"+
			        "<td width='20%'>"+datalist[i]['hjje']+"</td>"+
			        "<td width='35%'>"+datalist[i]['jqjzsj']+"</td>"+
			        "</tr>";
		}
	}
	jQuery("#tbody_lsxx").html(html);
	
		if(rs["cfbz"] == "ysj"){
			jQuery("#bccg").hide();
			jQuery("#tjsq").hide();
			showAlert("当前学年学期该学生已有申请记录，请勿重复填写申请！");
			return false;
		}else{
				jQuery("#bccg").show();
				jQuery("#tjsq").show();
				
		}
}

/**
 * 困难生认定申请界面编辑家庭情况
 * @return
 */
function editJtqk(){
	var xh = jQuery("#xh").val();
	showDialog('家庭情况调查',780,500,'xszz_jtqkdc.do?method=dcxxModify&writeAble=yes&type=update&xh='+xh,{
		close:function(){
			
		}
	});
}

/**
 * 加载家庭情况调查信息
 * @param obj
 * @return
 */
function showJtqk(obj,id){
	var className = jQuery(obj).attr("class");
	var newClass = className == "up" ? "down" : "up";

	jQuery(obj).attr("class",newClass);
	jQuery("#"+id).toggle();
}

function reloadWindow(){
	var xh = jQuery("#xh").val();
	document.location.href="xszz_knsrdbjpy.do?method=knssq&xh="+xh;
}