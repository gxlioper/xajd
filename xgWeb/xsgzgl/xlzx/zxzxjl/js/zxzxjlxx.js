var delIds = [];
var zjs = [];
var xgs = [];
var count = 0;

function showqtxx(obj){
	var className = jQuery(obj).attr("class");
	var newClass = className == "up" ? "down" : "up";

	jQuery(obj).attr("class",newClass);
	jQuery("#qtxx").toggle();
}

function showjlxx(obj){
	var className = jQuery(obj).attr("class");
	var newClass = className == "up" ? "down" : "up";

	jQuery(obj).attr("class",newClass);
	jQuery("#xgTbody").toggle();
}

function addRow(){
	html="";
	html+="<tbody id='tr_"+count+"' name='zjTbody'>"
	html+="<tr name='tr_"+count+"'>";
	html+="<th width='25%'><span><font color='red'>*</font></span>编号</th>";
	html+="<td width='20%'><input type='text' name='bh' maxlength='10'></input></td>";
    html+="<th width='25%'><span><font color='red'>*</font></span>咨询师姓名</th>";
    html+="<td width='20%'><input type='hidden' name='zxsxm' maxlength='10'></input>"+jQuery('#userNameReal').val()+"</td>";
    html+="<td width='10%' style='text-align: center;'>操作</td>";
    html+="</tr>";
    html+="<tr name='tr_"+count+"'>";
    html+="<th><span><font color='red'>*</font></span>咨询时间</th>";
	html+="<td><input type='text' name='zxsj' id='zxsjj_"+count+"' onfocus='showCalendar(\"zxsjj_"+count+"\",\"yyyy-MM-dd HH:mm\");' maxlength='20'></input></td>";	
	html+="<th><span><font color='red'>*</font></span>咨询地点</th>";
	html+="<td><input type='text' name='zxdd' maxlength='20'></input></td>";
	html+="<td rowspan='5' style='text-align: center'><a href='javascript:void(0)' onclick='scZj(\"tr_"+count+"\")'>删除</a></td></tr>";
	html+="<tr name='tr_"+count+"'>";
	html+="<th><span><font color='red'>*</font></span>来访者对问题的陈述、咨询目的及咨询师的评估印象<br /><font color='red'>&lt;限500字&gt;</th>";
	html+="<td colspan='3'><textarea rows='4' style='width: 99%' onblur='checkLen(this,500);'></textarea></td></tr>";
	html+="<tr name='tr_"+count+"'>";
	html+="<th><span><font color='red'>*</font></span>咨询过程<br /><font color='red'>&lt;限2000字&gt;</th>";
	html+="<td colspan='3'><textarea rows='4' style='width: 99%' onblur='checkLen(this,2000);'></textarea></td></tr>";
	html+="<tr name='tr_"+count+"'>";
	html+="<th><span><font color='red'>*</font></span>咨询反馈<br /><font color='red'>&lt;限500字&gt;</th>";
	html+="<td colspan='3'><textarea rows='4' style='width: 99%' onblur='checkLen(this,500);'></textarea></td></tr>";
	html+="<tr name='tr_"+count+"'>";
	html+="<th>咨询体会<br /><font color='red'>&lt;限500字&gt;</th>";
	html+="<td colspan='3'><textarea rows='4' style='width: 99%' onblur='checkLen(this,500);'></textarea></td></tr>";
	html+="</tbody>";
	jQuery("#zxjl").append(html);
	count++;
}

function scZj(tr){
	if(jQuery("#"+tr).find("input[type='hidden']").length>1){
		var input = jQuery("#"+tr).find("input").eq(0);
		var delId = input.val();
		delIds.push(delId);
	}
	jQuery("#"+tr).remove();
}

//删除已存在的谈话记录
function scXg(tr){
	var input = jQuery("#"+tr).find("input").eq(0);
	var delId = input.val();
	delIds.push(delId);
	jQuery("#"+tr).remove();
}

//保存谈话记录信息
function saveJlxx(){
	var flg = true;
	var tbodys = jQuery("tbody[name='xgTbody']");
	var zjtbodys = jQuery("tbody[name='zjTbody']");
	if(tbodys.length > 0){
		jQuery.each(tbodys,function(i,n){
			var inputs = jQuery(n).find("input");
			var xgId = jQuery(inputs).eq(0).val();
			var bh = jQuery(inputs).eq(1).val();
			var zxsxm = jQuery(inputs).eq(2).val();
			var zxsj = jQuery(inputs).eq(3).val();
			var zxdd = jQuery(inputs).eq(4).val();
			var textareas = jQuery(n).find("textarea");
			var zxpg = jQuery(textareas).eq(0).val();
			var zxgc = jQuery(textareas).eq(1).val();
			var zxfk = jQuery(textareas).eq(2).val();
			var zxth = jQuery(textareas).eq(3).val();
			var flgg = checkXx(bh.trim(),zxsj.trim(),zxdd.trim(),zxpg.trim(),zxgc.trim(),zxfk.trim());
			if(!flgg){
				flg = false;
				return false;
			}else{	
				var xgxx = xgId+','+bh+','+zxsxm+','+zxsj+','+zxdd+','+zxpg+','+zxgc+','+zxfk+',';
				if(zxth == null || zxth == ''){
					xgxx+='blank';
				}else{
					xgxx+=zxth;
				}
				xgs.push(xgxx);
			}
		})
		if(!flg){
			showAlertDivLayer("请填写带*必填项！");
			return false;
		}
	}
	if(zjtbodys.length>0){
		jQuery.each(zjtbodys,function(i,n){
			var inputs = jQuery(n).find("input");
			var bh = jQuery(inputs).eq(0).val();
			var zxsxm = jQuery(inputs).eq(1).val();
			var zxsj = jQuery(inputs).eq(2).val();
			var zxdd = jQuery(inputs).eq(3).val();
			var textareas = jQuery(n).find("textarea");
			var zxpg = jQuery(textareas).eq(0).val();
			var zxgc = jQuery(textareas).eq(1).val();
			var zxfk = jQuery(textareas).eq(2).val();
			var zxth = jQuery(textareas).eq(3).val();
			var flgg = checkXx(bh.trim(),zxsj.trim(),zxdd.trim(),zxpg.trim(),zxgc.trim(),zxfk.trim());
			if(!flgg){
				flg = false;
				return false;
			}else{			
				var zjxx = bh+','+zxsxm+','+zxsj+','+zxdd+','+zxpg+','+zxgc+','+zxfk+',';
				if(zxth == null || zxth == ''){
					zjxx+='blank';
				}else{
					zjxx+=zxth
				}
				zjs.push(zjxx);
			}
		})
		if(!flg){
			showAlertDivLayer("请填写带*必填项！");
			return false;
		}
	}
	var url = 'zxzx_zxzxjl.do?method=whbcZxzxjl';
	var params = {};
	if(delIds.length>0){
		params["delIds"] = delIds;
	}
	if(zjs.length>0){
		params["zjs"] = zjs;
	}
	if(xgs.length>0){
		params["xgs"] = xgs;
	}

	params["xh"] = jQuery("#xh").val();
	
	jQuery.ajaxSetup({async:false});
	jQuery.post(url,params,function(data){
		if(data["message"]=="保存成功！"){
   		 showAlert(data["message"],{},{"clkFun":function(){
//   		 	if(parent.window){
//   		 		refershParent();
//				}
   			 var zjbodys = jQuery("tbody[name='zjTbody']");
   			 if(zjbodys.length > 0){
   				 jQuery(zjbodys).each(function(i,n){
   					jQuery(zjbodys[i]).attr("name","xgTbody");
   				 })
   			 }
   			 zjs = [];
   			 xgs = [];
   			 delIds = [];
		 }});
   	 	}else{
   		 showAlert(data["message"]);
   		}
	},'json');
	jQuery.ajaxSetup({async:true});
	
	jQuery.ajaxSetup({async:false});
		jQuery.getJSON('zxzx_zxzxjl.do?method=getIds',{xh:jQuery("#xh").val()},function(data){
			if(data != null){
				var xgTbodys = jQuery("#zxjl").find("tbody");
				for(var j = 0; j<data.length; j++){
					jQuery(xgTbodys).each(function(i,n){
						if((jQuery(n).find("input[type='hidden']")).length < 2){
							var inputs = jQuery(n).find("input");
							if(jQuery(inputs).eq(0).val() == data[j]["bh"] && jQuery(inputs).eq(2).val() == data[j]["zxsj"]){
								jQuery(n).find("tr").eq(0).before("<input type='hidden' value='"+data[j]["id"]+"'/>")
							}
						}
					})
				}
			}
		})
	jQuery.ajaxSetup({async:true});
}

//验证必填项
function checkXx(bh,zxsj,zxdd,zxpg,zxgc,zxfk){
	if(bh == null || bh == '' || zxsj == null || zxsj == '' || zxdd == null || zxdd == '' || zxpg == null || zxpg == '' || zxgc == null || zxgc == '' || zxfk == null || zxfk == ''){
		return false;
	}else{
		return true;
	}
}

function reSearch(){
	if(parent.window){
		refershParent();
	}
}
