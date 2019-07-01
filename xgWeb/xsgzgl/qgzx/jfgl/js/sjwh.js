
//保存酬金发放表单
function saveForm(method){
	if(yzForm()){
		var ffsj=jQuery("#ffsj").val();
		var ffje=jQuery("#je").val();
		var yrdwdm=jQuery("#yrbm").val();
		var oldyrbm=jQuery("#oldyrbm").val();
		var oldje=jQuery("#oldje").val();
		//验证是否发放过参数
		var wbh=jQuery("#wbh").val();
		var xh=jQuery("#xh").val();
		var xn=jQuery("#xn").val();
		var yrdwdm=jQuery("#yrbm").val();
		var gwdm=jQuery("#gwdm").val();
		var ffsj=jQuery("#ffsj").val();
		if(!checkFfxx(wbh,xh,xn,yrdwdm,gwdm,ffsj)){
			return false;
		}
		if(!checkCjsx()){
			return false;
		}
		// if(checkJe(ffje,yrdwdm,ffsj,oldje,oldyrbm) == true){
				var url = "qgzx_jfcjgl_cjff.do?method="+method+"&type=save";
				removeDisabled();
				ajaxSubFormWithFun("demoForm",url,function(data){
					alertInfo(data["message"],function(ty){
						if(ty=="ok"){
							//window.parent.searchRs();
							var api = frameElement.api,W = api.opener;
							W.searchRs();
							btn_close();
						}
					});
					addDisabled();
				});
		// }
	}
}
function removeDisabled(){
	//提交时 disabled的值不提交 ，所以先去掉
	jQuery("#xn").removeAttr("disabled");
	jQuery("#yrbm").removeAttr("disabled");
	jQuery("#gwdm").removeAttr("disabled");
	jQuery("#ffsj").removeAttr("disabled");
}
function addDisabled(){
	//jQuery("#gwdm").removeAttr("disabled");
	//jQuery("#ffsj").removeAttr("disabled");
}
function checkFfxx(wbh,xh,xn,yrdwdm,gwdm,ffsj){
	var isok=false;
	jQuery.ajaxSetup({async:false});
	jQuery.post("qgzx_jfcjgl_cjff.do?method=checkFfxx",
			{wbh:wbh,xh:xh,xn:xn,yrdwdm:yrdwdm,gwdm:gwdm,ffsj:ffsj},
			function(result){
				if(result!="false"){
					var ts="此学生在<font color='blue'>"+xn+"</font>学年<font color='blue'>"+ffsj+"</font> 发放年月已存在同岗位发放信息，请检查！";
					alertInfo(ts,function(tag){
						if(tag=="ok"){
							isok=false;
						}
					});
				}else{
					isok=true;
				}
			}
		);
	jQuery.ajaxSetup({async:true});
	return isok;
}
//验证表单提交信息
function yzForm(){
	var result =false;
	var xh = jQuery("#xh").val();
	var xn = jQuery("#xn").val();
	var xm = jQuery("#xm").val();
	var gs = jQuery("#gs").val();
	var je = jQuery("#je").val();
	var gwdm = jQuery("#gwdm option:selected").val();
	var ffsj = jQuery("#ffsj").val();
	var bz = jQuery("#bz").val();
	if(xh==null||""==xh){
		alertInfo("学号不能空！");
	}else if(xn==null||""==xn){
		alertInfo("学年不能空！");
	}else if(gs==null||""==gs){
		alertInfo("工时不能空！");
	}else if(je==null||""==je){
		alertInfo("金额不能空！");
	}else if(gwdm==null||""==gwdm){
		alertInfo("请选择岗位名称！");
	}else if(ffsj==null||""==ffsj){
		alertInfo("请填写酬金发放时间！");
	}else if(isNaN(gs)){
		alertInfo("工时必须是数字！");
	}else if(isNaN(je)){
		alertInfo("金额必须是数字！");
	}else if(bz.length>2000){
		alertInfo("备注不能超过1000字！");
	}else{
		result =true;
	}
	return result;
}
function checkJe(ffje,yrdwdm,nd,oldje,oldyrbm){
	var isok=false;
	jQuery.ajaxSetup({async:false});
	jQuery.ajax({
		url:"qgzx_jfcjgl_cjff.do?method=checkJe",
		cache:false,
		async:false,
		data:{ffje:ffje,yrdwdm:yrdwdm,nd:nd,oldje:oldje,oldyrbm:oldyrbm},
		success:function(data){
			if(data["message"]!="true"){
				alertInfo(data["message"]);
				isok=false;
			}else{
				isok=true;
			}
		},
		dataType:"json"}
	);
	jQuery.ajaxSetup({async:true});
	return isok;
/*	jQuery.post("qgzx_jfcjgl_cjff.do?method=checkJe",{ffje:ffje,yrdwdm:yrdwdm,nd:nd,oldje:oldje},function(data){
		if(data["message"]!="true"){
			alertInfo(data["message"]);
			isok= false;
		}else{
			isok= true;
		}
	},"json");*/
}
function checkCjsx(){ 
	var cjsx=jQuery("#cjsx").text();
	if(cjsx==""||!cjsx){
		return true;
	}
	var je=jQuery("#je").val();
	if(parseInt(je)<=parseInt(cjsx)){
		return true;
	}
	alertInfo("金额<font color='blue'>"+je+"</font>不能超过工资上限<font color='blue'>"+cjsx+"</font>",function(tag){
		if(tag=="ok"){
			return false;
		}
	});
}
function showCjsx(){
	jQuery("#cjsxTr").show();
}
function hideCjsx(){
	jQuery("#cjsxTr").hide();
}
function getCjsx() {
	var xn = jQuery("#xn").val();
	var bmdm = jQuery("#yrbm").val();
	var gwdm = jQuery("#gwdm").val();
	// alert(xn+":"+bmdm+":"+gwdm);
	jQuery.ajax( {
		url : "qgzx_jfcjgl_cjff.do?method=getCjsxForGw",
		dataType : "json",
		data : {
			xn : xn,
			bmdm : bmdm,
			gwdm : gwdm
		},
		success : function(data) {
			if (data) {
				showCjsx();
			} else {
				hideCjsx();
			}
			jQuery("#cjsx").text(data);
		}
	});
    jQuery.ajax( {
        url : "qgzx_jfcjgl_cjff.do?method=getSxForGw",
        dataType : "json",
        data : {
            xn : xn,
            bmdm : bmdm,
            gwdm : gwdm
        },
        success : function(data) {
            jQuery("#sx").text(data);
            jQuery("#sx").val(data);

        }
    });
}
function xhLink(cellValue,row){
	return "<a href='javascript:void(0);' class='name' onClick='seeInfo(\""+row["wbh"]+"\")'>"+cellValue+"</a>";
}
function add(){
	
	showDialog('添加酬金发放信息', 760, 505, 'qgzx_jfcjgl_cjff.do?method=zjCjff');
}
function searchRs(){
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}
function seeInfo(wbh){
	//flag查看标示
	showDialog("查看酬金发放信息",650,430,"qgzx_jfcjgl_cjff.do?method=xgCjff&flag=see&wbh="+wbh,null);
	//showWindow("查看酬金发放信息",650,360,"qgzx_jfcjgl_cjff.do?method=xgCjff&flag=see&wbh="+wbh,null);
}
function btn_close(){
	frameElement.api.close();	
}
//加载查询结果
function query(){
	var map ={};
	var array = jQuery("#myForm").serializeArray();
		jQuery(array).each(function(index) {
			map[jQuery(this).attr("name")] = jQuery(this).val();
	 });
	jQuery("#dataTable").reloadGrid(map);
}
//修改一条记录
function update(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		alertInfo("请选择一条您要修改的记录！");
	} else {
		showDialog('修改酬金发放信息', 760, 505, 'qgzx_jfcjgl_cjff.do?method=xgCjff&wbh='+rows[0]["wbh"]);
		//refreshForm("qgzx_jfcjgl_cjff.do?method=xgCjff&wbh="+rows[0]["wbh"]);
	}
}
//删除记录
function deletes(){
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0){
		alertInfo("请选择您要删除的记录！");
	} else {
		var check = false;
		var rows = jQuery("#dataTable").getSeletRow();
		jQuery.each(rows,function(i){
			var sjbs = rows[i]['sjbs'];
			if(sjbs=='1'){
				alertInfo("您选择的第"+(parseInt(i)+1)+"条记录“酬金发放”提交的数据，不能删除！");
				check = false;
				return false;
			}else{
				check = true;
			}
		});
		if(!check){
			return false;
		}
		//alert(check);
		if(check){
			confirmInfo("您确定要删除"+ids.length +"条记录吗?",function(ty){
				//alert(ty);
				if(ty=="ok"){
					jQuery.post("qgzx_jfcjgl_cjff.do?method=scCjff",{values:ids.toString()},function(data){
						alertInfo(data["message"]);
						jQuery("#dataTable").reloadGrid();
					},'json');
				}
			});
		}
		
	}
}
function setSfkxg(){
	var sjly=jQuery("#sjly").val();
	if(sjly=="1"){
		jQuery("#xn").attr("disabled","disabled");
		jQuery("#yrbm").attr("disabled","disabled");
		jQuery("#gwdm").attr("disabled","disabled");
		jQuery("#ffsj").attr("disabled","disabled");
	}
}
//年度和用人部门 联动获取岗位名称
//年度和用人部门 联动获取岗位名称
function getGwmc(){
	var yrbm = jQuery("#yrbm  option:selected").val();
	var xn = jQuery("#xn option:selected").val();
	var xq = jQuery("#xq").val();
	xq = (!xq)?"":xq;
	var parameter = {yrbm:yrbm,xn:xn,xq:xq}
	jQuery("#gwdm").empty();
	var oldgwdm=jQuery("#oldgwdm").val();
	//alert(jQuery("#gwmc").html());
	jQuery.ajax({
		   type: "POST",
		   url: "qgzx_jfcjgl_cjff.do?method=getGwmc",
		   dataType:"json",
		   data: parameter,
		   success: function(result){
				var html="";
			   jQuery.each(result,function(i){
				   html+="<option value='"+result[i].gwdm+"'";
				  // alert(result[i].gwdm+":"+oldgwdm+":"+(result[i].gwdm==oldgwdm));
				   if(result[i].gwdm==oldgwdm){
					   html+=" selected='true'";
				   }
				   html+=">";
				   html+=result[i].gwmc;
				   html+="</option>";
		       });
			   jQuery("#gwdm").append(html);
			   getCjsx();
		   }
		});
}
function checkStudent(){
	showWindow('请选择一个学生',680,450,'xsxx_xsgl.do?method=showStudents&goto=qgzx_jfcjgl_cjff.do?method=zjCjff');
}

function qgjgwhExportConfig() {
	//DCCLBH导出功能编号,执行导出函数 
	customExport("qgzx_jfcjgl_qgjgwh.do", qgjgwhExportData);
}
	

	
// 导出方法
function qgjgwhExportData() {
	setSearchTj();//设置高级查询条件
	var url = "qgzx_jfcjgl_cjff.do?method=qgjgwhExportData&dcclbh=" + "qgzx_jfcjgl_qgjgwh.do";//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

//计算酬金金额
function jsCjJe(){
	var gs=jQuery("#gs").val();
	var cjbz=jQuery("#cjbz").val();
	var jqJe=jQuery("#je");
	if(gs == null || gs == "" || cjbz == null || cjbz == ""){
		jqJe.val("0");
		return false;
	}
	jqJe.val(parseInt(gs)*parseInt(cjbz));
}

//北京林业大学 下载申报表
function cjffcxExportData_10022() {
	var ndNum = jQuery("a[name=a_name_nd]").length;
	if(ndNum != 1){
		showAlertDivLayer("请选择一个发放年度！");
		return false;
	}
	var yfs = jQuery("a[name=a_name_yf]");
	var yfNum = yfs.length;
	if(yfNum > 0){
		if(yfNum > 1){
			var yfNewArr = new Array();
			for(var i = 0; i < yfNum; i++){
				// 先替换a_id_得到"01","02"..."10","11","12"，再替换后得到"1","2"..."10","11","12"
				yfNewArr[i] = yfs.eq(i).attr("id").replace("a_id_","").replace(/0(\d)/g, "$1");
			}
			var count = 0; // 次数
			var yfNewStr = yfNewArr.join("!!@@!!") + "!!@@!!"; // 所有月份组成的字符串
			for(var i = 0; i < yfNewArr.length; i++){
				// 遍历时，把当前的月份加1，然后在【所有月份组成的字符串】中查找，没找到的话，次数加1
				if(yfNewStr.indexOf((parseInt(yfNewArr[i]) + 1) + "!!@@!!") < 0){
					count++;
				}
			}
			if(count != 1){
				showAlertDivLayer("请选择连续的发放月份！");
				return false;
			}
		}
	}else{
		showAlertDivLayer("请选择至少一个发放月份！");
		return false;
	}
	setSearchTj();//设置高级查询条件
	var url = "qgzx_jfcjgl_cjff.do?method=getCjffSbbDclist";
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

jQuery(function(){
    jQuery("#je").attr('readonly','readonly');
    console.log('loading');
    jQuery("#gs").change(function(){
		var sx = jQuery("#sx").val();
        console.log('sx:'+sx);
        console.log('gs:'+ this.value);
		var money = this.value * sx;
        jQuery("#je").val(money);
	});
});