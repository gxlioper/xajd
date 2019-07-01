
function ckgz(cellValue, rowObject){
	 var pzgzid=rowObject["pzgzid"];
	 
	 return "<a href='javascript:void(0);' onclick=\"ckpzgz('" + pzgzid
		+ "')\" class='name'>" + cellValue + "</a>";
 }
function ckpzgz(pzgzid){
		var url ="fbglgzpztj.do?method=showView&pzgzid=" + pzgzid;
		var cktitle ="查看规则信息";
		showDialog(cktitle, 800, 500, url);
 }
var action="fbglbbgl.do";
var title="分班管理";
function searchRs() {
	var map = getSuperSearch();
	var bbzt = jQuery("#bbzt").val();
	if (bbzt != "") {
		map["bbzt"] = bbzt;
	}
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}
// 点击序号跳转
function dcmcLink(cellValue, rowObject) {
	var pk = rowObject["pk"];
	return "<a href='javascript:void(0);' onclick=\"ckxx('" + pk
			+ "')\" class='name'>" + cellValue + "</a>";
}
function zyjcFormatter(cellValue, rowObject){
	var html="<input name='zyjc' type='text' size='15px;'/>";
	return html;
}
function rssxFormatter(cellValue, rowObject){
	var html="<input name='rssx' type='text' size='10px;' onblur='checkInt(this)' maxlength='4'/>";
	return html;
}
function bjdmFormatter(cellValue, rowObject){
	var html="<font name='bjdm' color='red' size='3px;'>"+cellValue+"</font>";
	return html;
}
function bjgsFormatter(cellValue, rowObject){
	var html="<input name='bjgs' type='text' size='10px;' onblur='checkInt(this)' maxlength='4'/>";
	return html;
}

function lshFormatter(cellValue, rowObject){
	var html="<input name='lsh' type='text' size='10px;' onblur='checkInt(this)' maxlength='4'/>";
	return html;
}

// 查看信息
function ckxx() {
	var id=jQuery("#pzgzid").val();
	var url = "fbglgzpztj.do?method=showView&pzgzid=" + id;
	var cktitle =title+"信息";
	showDialog(cktitle, 680, 400, url);
}
//切换
function selectTab(obj, bbzt) {
	var map = getSuperSearch();
	if (bbzt == "wbb") {
		map["bbzt"]="wbb";
		gridSetting["params"] = map;
		jQuery("#dataTable").initGrid(gridSetting);
		 jQuery("#tzbb").hide();
		 jQuery("#scbj").hide();
		 jQuery("#qxbb").hide();
		 jQuery("#bb").show();
	} else {
		map["bbzt"]="ybb";
		gridSetting2["params"] = map;
		jQuery("#dataTable").initGrid(gridSetting2);
		jQuery("#tzbb").show();
		jQuery("#scbj").show();
		jQuery("#qxbb").show();
		jQuery("#bb").hide();
	}
	jQuery(".ha").removeClass("ha");
	jQuery(obj).parent().addClass("ha");
	//searchRs();
}
//编班
function bb(){
	//不存在对应编班规则信息
	if (!isHaveGzxx("BBGZ")) {
		return showAlertDivLayer("编班规则未设定或未启用！");
	}
	var ids = jQuery("#dataTable").getRowCount();
	if(ids<=0){
		return showAlertDivLayer("不存在学生信息！");
	}
	var url = action + "?method=add";
	var title = "编班";
	var ids = jQuery("#dataTable").getSeletIds();
	url+="&pk="+ids.toString();
	showDialog(title, 800, 500, url);
	jQuery("#dataTable").reloadGrid();
}
//执行保存
function save(url){
	var isok=true;
	jQuery("[name=bjgs]").each(function(){
		if(!jQuery(this).val()){
			isok=false;
			return;
		}
	});
	if(!isok){
		return showAlertDivLayer("请填写完整班级个数！");
	}
	if(!checkBjrssx()){
		return showAlertDivLayer("班级个数*自动分班人数上限已超过总人数！");
	}
	lock();
	var pzgzid=jQuery("#pzgzid").val();
	var obj=new Array();
	//获取传递参数
	jQuery("#dataTable").find("tr[rowindex]").each(function(i){
		var trobj=new Object();
		var list=new Array();
		jQuery(this).find(":text").each(function(j){
			var data=new Object();
			//alert(jQuery(this).attr("name")+":"+jQuery(this).val());
			data.name=jQuery(this).attr("name");
			data.value=jQuery(this).val();
			list[j]=data;
		});
		trobj.tr=list;
		trobj.pk=jQuery(this).find("td").first().html();
		obj[i]=trobj;
	});
	var json=JSON.stringify(obj);
	//进度条所需参数
	//总数据条数
	var all=0;
	jQuery("#dataTable").find("[name=bjgs]").each(function(){
		var bjgs=jQuery(this).val()==""?0:jQuery(this).val();
		all+=parseInt(bjgs);
	});
	jQuery.post(url, {
		pzgzid:pzgzid,
		json:json,
		all:all
	}, function(data) {
		if("编班完成"!=data["message"]){
			showAlert(data["message"],{},{"clkFun":function(){
				if (parent.window){
 					refershParent();
 				}
			}});
		}
		},"json");
 	
 	//开始进度条
 	loadBar("fbglbb"+pzgzid,function(data){
 		if(data.finish){
	 	   showAlert("编班完成！",{},{"clkFun":function(){
				if (parent.window){
 					refershParent();
 				}
			}});
 		}
 		return true;
 	});
}
/**
 * 加载待分班数据
 * @return
 */
function loadDfbsj(){
	var pzgzid=jQuery("#pzgzid").val();
	jQuery("#table").load("fbglbbgl.do?method=fbglbb&pzgzid="+pzgzid+"&tt="+new Date().getTime());
	preview();
}
/**
 * 生成班级个数
 * @return
 */
function gennerBjgs(){
	var mbrs=jQuery("#mbrs").val();
	if(!mbrs){
		return showAlertDivLayer("每班人数不能为空！");
	}
	mbrs=parseInt(mbrs);
	if(mbrs<=0){
		return showAlertDivLayer("每班人数最少一人！");
	}
	var rows=jQuery("#dataTable").getSeletAllRow();
	jQuery(".dateline>tbody").find("tr").each(function(i){
		var rs=rows[i]["rs"];
		//计算班级个数
		rs=parseInt(rs);
		var bjgs=rs/mbrs;
		var syrs=rs%mbrs;
		bjgs=Math.floor(bjgs);
		if(syrs>0){
			bjgs+=1;
		}
		jQuery(this).find("[name=bjgs]").val(bjgs);
	});
}
/**
 * 检查班级人数上限
 * @return
 */
function checkBjrssx(){
	var isok=true;
	var rows=jQuery("#dataTable").getSeletAllRow();
	jQuery(".dateline>tbody").find("tr").each(function(i){
		var rs=rows[i]["rs"];
		var bjgs=jQuery(this).find("[name=bjgs]").val();
		var rssx=jQuery(this).find("[name=rssx]").val();
		if(parseInt(bjgs)*parseInt(rssx)>parseInt(rs)){
			jQuery(this).css("background","none repeat scroll 0 0 #feeeed");
			isok= false;
		}
	});
	return isok;
}
/*************************已编班*************************/
//记录删除的行（非新增行）
var jlsch=0;
var bakHtml = "<tr>"
				+ "<td></td>"
				+ "<td style='word-break: break-all; display: none;'></td>"
				+ "<td style='word-break: break-all; display: none;'></td>"
				+ "<td></td>"
				+ "<td></td>"
				+ "<td><input type=\"text\" maxlength=\"4\" onblur=\"checkInt(this)\" value=\"8\" name=\"rssx\"></td>"
				+ "<td></td>"
				+ "</tr>";
function addfb() {
	//已增加的行数
	var index=jQuery("#dataTable").find("tr[isAdd=true]").size();
	//去掉非新增删除行
	index=index-jlsch;
	var pzgzid=jQuery("#pzgzid").val();
	var pk=jQuery("#pk").val();
	jQuery.ajax({
		url:"fbglbbgl.do?method=getNextCode",
		data:{pzgzid:pzgzid,pk:pk,index:index},
		type:"post",
		dataType:"json",
		async:false,
		success:function(data){
			var html="";
			//好恶心的判断，后续修复bug编写，为了尽量减少对源代码的影响，这里直接判断操作了。
			//当前没有班级
			if(jQuery("[name=tbxx]>tbody").find("tr").last().index()<0){
				var tr=jQuery(bakHtml);
				jQuery(tr).attr("rowindex",1);
				//序号
				jQuery(tr).find("td").eq(0).html(1);
				//pk
				jQuery(tr).find("td").eq(1).html(data.pk);
				//班级编号
				jQuery(tr).find("td").eq(2).html(data.bjbh);
				jQuery(tr).attr("isAdd","true");
				//班级代码
				html="<font name='bjdm' color='red' size='3px;'>"+data.bjdm+"</font>";
				jQuery(tr).find("td").eq(3).html(html);
				//班级名称
				html="<input name='bjmc' type='text' value="+data.bjmc+" />";
				jQuery(tr).find("td").eq(4).html(html);
				//学生数
				jQuery(tr).find("td").eq(6).html(0);
				jQuery("#dataTable").append(tr);
			}else{
				var tr=jQuery("#dataTable").find("tr").last().clone();
				jQuery(tr).attr("isAdd","true");
				var rowindex=jQuery(tr).attr("rowindex");
				jQuery(tr).attr("rowindex",parseInt(rowindex)+1);
				//序号
				html=jQuery(tr).find("td").eq(0).html();
				//编班规则无流水号则不加序号
				if(null!=html&&""!=html){
				html=parseInt(html)+1;
				jQuery(tr).find("td").eq(0).html(html);
				}
				//班级代码
				html="<font name='bjdm' color='red' size='3px;'>"+data.bjdm+"</font>";
				jQuery(tr).find("td").eq(3).html(html);
				//班级名称
				html="<input name='bjmc' type='text' value="+data.bjmc+" />";
				jQuery(tr).find("td").eq(4).html(html);
				//学生数
				jQuery(tr).find("td").eq(6).html(0);
				jQuery("#dataTable").append(tr);
			}
		}
	});	
}
/**
 * 删除分班
 * @return
 */
function delfb() {
	var lastTr=jQuery("[name=tbxx]>tbody").find("tr").last();
	//班级已有学生不允许删除
	var bjrs = jQuery(lastTr).find("td").last().text();
	var bjdm = jQuery(lastTr).find("td").eq(3).text();
	if("0"!=bjrs){
		showAlertDivLayer(bjdm+"已有学生，不允许删除！");
		return false;
	}
	var isadd=jQuery(lastTr).attr("isAdd");
	if(isadd!="true"){
		jlsch++;
	}
	
	lastTr.remove();
}
/**
 * 删除行
 * @param tr
 * @return
 */
function removeTr(tr){
	jQuery(tr).remove();
}
function bjmcFormatter(cellValue, rowObject){
	var html="<input name='bjmc' type='text' maxlength='50' value="+cellValue+" />";
	return html;
}
function rssxTbFormatter(cellValue, rowObject){
	if(!cellValue){
		cellValue="无上限";
	}
	var html="<input name='rssx' type='text' value="+cellValue+" onblur='checkN(this)' onfocus='fomartN(this)' maxlength='4'/>";
	return html;
}
function fomartN(obj){
	if(obj.value=="无上限"){
		jQuery(obj).val("");
	}
}
function checkN(obj){
	if(obj.value==""){
		jQuery(obj).val("无上限");
	}
	if(obj.value=="无上限"){
		return true;
	}else {
		return checkInt(obj);
	}
}
/**
 * 加载调班信息
 * @return
 */
function loadTbxx(){
	var pk=jQuery("#pk").val();
	jQuery("#table").load("fbglbbgl.do?method=fbgltbxx&pk="+pk+"&tt="+new Date().getTime());
}
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}
/**
 * 调整班级
 * @return
 */
function tzbj(){
	//不存在对应编班规则信息
	if (!isHaveGzxx("BBGZ")) {
		return showAlertDivLayer("编班规则未设定或未启用,不能执行调班！");
	}
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0) {
		return showAlertDivLayer("请选择您要调整的"+jQuery("#xbmc").val()+"专业！");
	}
	var nowpk="";
	var row = jQuery("#dataTable").getSeletRow();
	var isok=true;
	var isHaveStu=false;
	for(var i=0;i<row.length;i++){
		if(nowpk==""||nowpk==row[i]["pk"]){
			nowpk=row[i]["pk"];
		}else{
			isok=false;
			break;
		}
		if("0"!=row[i]["xss"]){
			isHaveStu=true;
			break;
		}
		
	}
	if(!isok){
		showAlertDivLayer("只能对同一"+jQuery("#xbmc").val()+"、专业、年级、学制、层次的班级进行调班！");
	}else if(isHaveStu){
		showAlertDivLayer("所选班级已有学生，不允许修改编班信息，如需修改，请先清除相应分班信息！");
	}else{
		var url=action+"?method=fbgltb&pk="+nowpk;
		showDialog("编班调整", 800, 500, url);
	}
}
/**
 * 删除班级
 * @return
 */
function scbj(){
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0) {
		showAlertDivLayer("请选择您要操作的记录！");
	} else {
		var rows = jQuery("#dataTable").getSeletRow();
		showConfirmDivLayer("您确定要将选择的记录取消编班吗？", {
			"okFun" : function() {
				jQuery.post(action+"?method=del", {
					values : ids.toString()
				}, function(data) {
					var mes="";
				
					if(data["errorObject"]!="-1"){
						var list=data["errorObject"];
						for(var i=0;i<list.length;i++){
							var messageObj=list[i];
							mes+="<font color='red'>"+messageObj["bjmc"]+"</font>";
							mes+="的班级 存在学生不能删除!";
							mes+="</br>";
						}
					}else{
						mes="操作成功！";
					}
					showAlertDivLayer(mes);
					jQuery("#ybb").click();
				}, 'json');
			}
		});
	}
}
/**
 * 保存调班信息
 * @return
 */
function saveTbxx(){
	
	var pzgzid=jQuery("#pzgzid").val();
	var pk=jQuery("#pk").val();
	var rows=jQuery("#dataTable").getSeletAllRow();
	var list=new Array();
	var bjmcCheck = false;
	var sameBjmc="";
	checkMsg="";
	jQuery("#dataTable").find("tbody>tr").each(function(i){
		var data=new Object();
		data.pk=jQuery(this).find("td").eq(1).text();
		data.bjdm=jQuery(this).find("[name=bjdm]").text();
		data.bjmc=jQuery(this).find("[name=bjmc]").val();
		data.bjrssx=jQuery(this).find("[name=rssx]").val();
		list[i]=data;
		checkMsg=data.bjmc;
		if(sameBjmc.indexOf(checkMsg) > -1){
			bjmcCheck=true;
			return false;
		}else{
			sameBjmc+="&"+data.bjmc;
		}
	});
	if(bjmcCheck){
		showAlertDivLayer("班级名称不能重复，请修改！");
		return false;
	}
	var json=JSON.stringify(list);
 	jQuery.ajax({
		url:"fbglbbgl.do?method=saveTbxx",
		data:{pzgzid:pzgzid,json:json,pk:pk},
		type:"post",
		dataType:"json",
		async:false,
		success:function(data){
			   showAlert(data["message"],{},{"clkFun":function(){
					var api = frameElement.api;
					W = api.opener;
					W.jQuery("#ybb").click();
					iFClose();
  				}});
			   unlock();
		}
	});	
}
/*************************已编班end*************************/