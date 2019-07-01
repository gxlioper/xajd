function searchRs(){
	var map = getSuperSearch();
	var jtlb = jQuery("#jtlb").val();
	if (null != jtlb && jtlb != "") {
		map["jtlb"] = jtlb;
	}else{
		map["jtlb"] = "zc";
	}
	jQuery("#dataTable").reloadGrid(map);
}

function scjt(){
	var url = "jtff_jtff.do?method=jtsc";
	var title = "津贴发放";
	showDialog(title, 770, 450, url);
}

function exportConfig(){
	
}

function savejtsc(){
	if(jQuery.trim(jQuery("#ffny").val()) == ""){
		showAlert("请将带<font color='red'>*</font>的项目填写完整！")
		return false;
	}
	if(!checkIsNotNull()){
		showAlert("请将带<font color='red'>*</font>的项目填写完整！")
		return false;
	}
	var url = "jtff_jtff.do?method=savejtsc";
	ajaxSubFormWithFun("JtffForm", url, function(data) {
		 if(data["message"]=="保存成功！"){
  		 showAlert(data["message"],{},{"clkFun":function(){
					if (parent.window){
						refershParent();
					}
				}});
  	 }else{
  		 showAlert(data["message"]);
  		}
		});
}

function getData(){
	var ffny = jQuery.trim(jQuery("#ffny").val());
	var exist = "";
	if(ffny != ""){
		var rs = null;
		var url = "jtff_jtff.do?method=getData";
		jQuery.ajax({
		type:'post',
		url:url,
		dataType:'json',
		contentType:"application/x-www-form-urlencoded; charset=UTF-8",
		data:'ffny='+ffny,
		async: false,
		success:function(result){
			if(result['zrs']=='0'){
				showAlert("无津贴名单维护信息!",{},{"clkFun":function(){
		    		jQuery("#jtsc").hide();
		    		jQuery("#zrs").text(result['zrs']);
		    		jQuery("#zje").text(result['zje']);
					return false;
				}});
				
			}else{
				jQuery("#jtsc").text("生成津贴");
				exist = result['exist'];
				var datalist = result['datalist'];
				var html = "";
				html +=	"<tr>";
				html +=	"<th>序号</th>";
			    html +=	"<th>职工号</th>";
			    html +=	"<th>姓名</th>";
			    html +=	"<th>性别</th>";
			    html +=	"<th>工作部门</th>";
			    html +=	"<th>岗位</th>";
			    html +=	"<th>所带学生数</th>";
			    html +=	"<th>发放月份</th>";
			    html +=	"<th><font color='red'>*</font>金额(元)</th>";
			    html +=	"</tr>";
				for(var i=0;i<datalist.length;i++){
					html +="<tr>";
					html +="<td>"+datalist[i]['rownum']+"</td>";
					html +="<td>"+datalist[i]['zgh'];
					html +=" <input type='hidden' name='zgh' value='"+datalist[i]['zgh']+"'/>";
					html +="</td>";
					html +="<td>"+datalist[i]['xm']+"</td>";
					html +="<td>"+datalist[i]['xb']+"</td>";
					html +="<td>"+datalist[i]['bmmc']+"</td>";
					html +="<td>"+datalist[i]['gw']+"</td>";
					html +="<td>"+datalist[i]['dbrs']+"<input name='dbrs' type='hidden' value ='"+datalist[i]['dbrs']+"' />"+"</td>";
					html +="<td>"+datalist[i]['ffny']+"</td>";
					html +="<td>";
					if(datalist[i]['jtlb'] == 'zc'){
						html +="<input type='text' style='width:80px;' name='ffje' value='"+datalist[i]['xsje']+"' onkeyup='checkMoneyBykeyUp(this);' maxlength='10'/>";
					}else{
						html +=datalist[i]['xsje'];
						html +="<input type='hidden' style='width:80px;' name='ffje' value='"+datalist[i]['xsje']+"'/>";
					}
					
					html +="<input name='gw' type='hidden' value ='"+datalist[i]['gw']+"' />";
					html +="<input name='jsje' type='hidden' value ='"+datalist[i]['jsje']+"' />";
					html +="</td>";
				    html +="</tr>";
				    jQuery("#thnr").html(html);
				    jQuery("#zrs").text(result['zrs']);
		    		jQuery("#zje").text(result['zje']);
				}
			}
		 }
	    });
		  
	}
	if(exist == '1'){
		showAlert("该月发放记录已生成，请注意!",{},{"clkFun":function(){
			jQuery("#jtsc").text("重新生成");
			return false;
		}});
	}
}

function checkIsNotNull(){
    var objs = jQuery("input[name='ffje']");
    var flag = true;
    for(var i=0;i<objs.length;i++){
    if(jQuery.trim(objs[i].value) == ""){
      flag = false;
     break;
     }
   }
   return flag;

}

//查看学生链接
function xhLink(cellValue, rowObject) {
	return "<a href='javascript:void(0);' class='name' onclick='jtscck(\""
			+ rowObject["id"] + "\",\"" + cellValue + "\");'>" + cellValue
			+ "</a>";
}

//正常津贴查看
function jtscck(id,zgh){
	showDialog("教师津贴信息查看", 650, 450, "jtff_jtff.do?method=jtscCk&id="
			+ id + "&zgh=" + zgh);
}


var DCCLBH = "szdw_jtff_jtff.do";
//自定义导出 功能
function exportConfig() {
	//DCCLBH导出功能编号,执行导出函数 
	customExport(DCCLBH,ExportData);
}

//导出方法
function ExportData() {
	setSearchTj();//设置高级查询条件
	var jtlb = "";
	var url = "jtff_jtff.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}



