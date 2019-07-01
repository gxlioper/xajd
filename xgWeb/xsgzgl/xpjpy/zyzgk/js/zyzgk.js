//获取主干课列表
function getZgkList(){	  
  var pjxn = jQuery("#pjxn").val();
  var xydm = jQuery("#xydm").val();
  var zyCond = jQuery("#zyCond").val();
  if(pjxn == "" || pjxn == null){
	  showAlertDivLayer("请选择学年!");
		return false;
  }else{
	  jQuery("#zykxx").empty();
	  jQuery.ajaxSetup({async:false});
	  jQuery.post("xpj_zyzgk.do?method=getZyzgkList",{pjxn:pjxn,xydm:xydm,zyCond:zyCond},function(data){
		  	var html = "<tr align='center' bgcolor='#E8F0FB'>";
		    html += "<td width='20%'>专业</td>";
			html += "<td width='10%'>操作</td>";
			html += "<td width='70%'>课程</td>";
			html += "</tr>";
			html += "<tr>";
		    var zydmmcs = [];
		    var zydms = [];
		    var dmStr = "";
			jQuery.each(data,function(i,n){
				if(i==0){
					zydms.push(n["zydm"]);
					zydmmcs.push(n["zydm"]+"_"+n["zymc"]);
					dmStr+=n["zydm"];
				}
				
				if(dmStr.indexOf(n["zydm"]) == -1){
					zydms.push(n["zydm"]);
					zydmmcs.push(n["zydm"]+"_"+n["zymc"]);
					dmStr+=","+n["zydm"];
				}														
			});
			
			jQuery.each(zydmmcs,function(i,n){
				var dmmcs = n.split("_");
				var dm = dmmcs[0];
				var mc = dmmcs[1];
				html+="<tr>";
				html+="<td>"+mc+"</td>";
				html+="<td><input type='checkbox' name='qx' onclick='quanxuan(this);'>全选</input>&nbsp;<input type='checkbox' name='fx'  onclick='fanxuan(this);'>反选</input></td>";
				html+="<td><table width='100%' class='zgktable'>";
				var h = 0;  //同一专业名称课数计数
				jQuery.each(data,function(i,n){
					if(n["zydm"] == dm){
						if(h%4 == 0){
							html+="<tr>";										
						}
						if(n["kcmc"] != null && n["kcmc"] != ''){										
							html+="<td width='20%'><input type='checkbox' name='zgks' value='"+dm+"_"+n["kcmc"]+"'/>"+n["kcmc"]+"</td>";
						}
						if(h%4 == 3){
							html+="</tr>";
						}
						h++;
					}else{
						if(h>0 && h%4 != 3){
							var j = 4-(h%4);
							for(var k = 1;k<=j;k++){
								html+="<td width='20%'></td>";
								if(k==j){
									html+="</tr>";
								}
							}
							return false;
						}else{										
							h = 0;									
						}
					}
					if(i == data.length-1){
						var j = 4-(h%4);
						for(var k = 1;k<=j;k++){
							html+="<td width='20%'></td>";
							if(k==j){
								html+="</tr>";
							}
						}
					}
				});
				html+="</table></td>";							
			})
			jQuery("#zykxx").html(html);
		},'json');
	  jQuery.ajaxSetup({async:true});
  }
  getYszZgk(pjxn);
}

function changeXy(){
	getZgkList();
	jQuery.getJSON('xpj_zyzgk.do?method=getZy',{xydm:jQuery('#xydm').val()},function(data){
		jQuery('#zyCond').empty();
		jQuery('#zyCond').append("<option value=''></option>");
		if(data != null && data.length != 0){
			for(var i=0; i<data.length; i++){
				var option = "<option value=\"" + data[i].zydm + "\">" + data[i].zymc + "</option>";
				jQuery('#zyCond').append(option);
			}
		}
	});
}

//保存表单
function saveForm(){
	var pjxn = jQuery("#pjxn").val();
    if(pjxn == "" || pjxn == null){
	   showAlertDivLayer("请选择学年!");
	   return false;
	}
    var checkboxs = jQuery("input[name='zgks']");
    var num = 0;
    jQuery.each(checkboxs,function(i,n){
    	if(jQuery(n).prop("checked")==true){
    		num++;
    	}
    });
    if(num<1){
       showAlertDivLayer("请至少选择一门主干课程!");
 	   return false;
    }
	var url = "xpj_zyzgk.do?method=saveZyzgk";
	ajaxSubFormWithFun("ZyzgkModel",url,function(data){
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

//获取已设置主干课列表
function getYszZgk(xn){
	jQuery.ajaxSetup({async:false});
	jQuery.post("xpj_zyzgk.do?method=getYszZyzgkList",{pjxn:xn},function(data){
		if(data == null || data == ""){
			return false;
		}else{
			var checkboxs = jQuery(".zgktable input[type='checkbox']");
			jQuery.each(checkboxs,function(i,n){
				if(data.indexOf(jQuery(n).val()) != -1){
					jQuery(n).attr("checked","checked");
				}
			})
		}
	},'text');
	jQuery.ajaxSetup({async:true});	
}

//全选
function quanxuan(obj){
	if(jQuery(obj).prop("checked") != false){
		var table = jQuery(obj).parent("td").next("td").find("table");
		var checkboxs = jQuery(table).find("td input[type='checkbox']");
		jQuery.each(checkboxs,function(i,n){
			jQuery(n).attr("checked","checked");
		})		
	}
}

//反选
function fanxuan(obj){
	if(jQuery(obj).prop("checked") != false){
		var table = jQuery(obj).parent("td").next("td").find("table");
		var checkboxs = jQuery(table).find("td input[type='checkbox']");
		jQuery.each(checkboxs,function(i,n){
			if(jQuery(n).prop("checked") == true){
				jQuery(n).attr("checked",false);
			}else{
				jQuery(n).attr("checked",true);
			}
		})
	}
}
