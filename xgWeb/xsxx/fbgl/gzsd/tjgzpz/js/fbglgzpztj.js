/** ********选项类型************* */
var defalut="0";// 默认
var text="1";// 文本框
var select="2";// 下拉框
var qzw="3";// 区间
/** ****************************** */

/** *******是否可复选************** */
var kfx="1";// 可复选
var bkfx="0";// 不可复选
/** ****************************** */

/** ********是否可修改************* */
var bkxg="0";// 不可修改
var kxg="1";// 可修改
/** ****************************** */

/** ********位数补零************* */
var bbl="0";// 不自动补零
var bl="1";// 自动补零
/** ****************************** */
var isfbcl=false;// 默认非分班处理

var action="fbglgzpztj.do";
var title="规则设定";
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}
// 点击序号跳转
function dcmcLink(cellValue, rowObject) {
	var pzgzid = rowObject["pzgzid"];
	return "<a href='javascript:void(0);' onclick=\"ckxx('" + pzgzid
			+ "')\" class='name'>" + cellValue + "</a>";
}
// 查看信息
function ckxx(id) {
	var url = action+"?method=showView&pzgzid=" + id;
	var cktitle =title+"信息";
	showDialog(cktitle, 750, 400, url);
}
function drxx(){
	toImportData("IMPORT_FBGL_XSXX");
	return false;
}
// 删除
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0) {
		showAlertDivLayer("请选择您要删除的记录！");
	} else {
		showConfirmDivLayer("您确定要删除选择的记录吗？", {
			"okFun" : function() {
				jQuery.post(action+"?method=del", {
					values : ids.toString()
				}, function(data) {
					var mes="成功删除了<font color='green'>&nbsp;"+data["successDel"]+"&nbsp;</font>条数据";
					mes+="</br>";
					if(data["errorObject"]!="-1"){
						var list=data["errorObject"];
						for(var i=0;i<list.length;i++){
							var messageObj=list[i];
							mes+="<font color='red'>"+messageObj["pzgzmc"]+"</font>";
							if(messageObj["qyzt"]=="1"){
								mes+="已经启用或被使用，不能删除！";
							}else if(messageObj["sfnz"]=="1"){
								mes+="是系统内置规则，不能删除！";
							}
							mes+="</br>";
						}
					}
					showAlertDivLayer(mes,{},{"clkFun":function(){
						jQuery("#dataTable").reloadGrid();
					}});
				}, 'json');
			}
		});

	}
}
// 申请
function add() {
		var url =action+"?method=add";
		var sqtitle =title+"增加";
		showDialog(sqtitle, 800, 500, url);
		// reload();
}
/**
 * 保存
 * 
 * @param url
 * @param checkId
 * @return
 */
function save(url,checkId){
	if(!check(checkId)){
		return showAlert("请将带<font color='red'>*</font>的项目填写完整！");
	}
	if(!checkother()){
		return showAlert("规则信息必须填写完整！");
	}
	var lshLen=jQuery("select:visible[name=tjpz][value='TJ_LSH']").length;
	var  gzdm = jQuery("#gzdm").val();
	if(lshLen<1&&"FBGZ"!=gzdm){
		return showAlert("请添加流水号！");
	}
	if(!checkQsz()){
		return showAlert("流水号起始值位数不能超过流水号位数！");
	}
	// 锁定按钮
	lock();
	var json;
	if(isfbcl){
		json=getFbglJson();
	}else{
		json=getDataJson();
	}
	json=encodeURI(JSON.stringify(json),"utf-8");
	var gzdm=jQuery("#gzdm").val();
	var sfqy=jQuery("input[name=qyzt]:checked").val();
	jQuery.post("fbglgzpztj.do?method=sfQy", {
		gzdm:gzdm
	}, function(data) {
		if(sfqy=="1"&&data["message"]){
			showAlert("此规则类型已有启用，只能设置为不启用！");
			unlock();
		}else{
			jQuery.ajax({
				   type: "POST",
				   url: url,
				   dataType:"json",
				   data:{json:json},
				   success: function(data){
					   if(data["message"]=="保存成功！"){
				    		 showAlert(data["message"],{},{"clkFun":function(){
									if (parent.window){
										refershParent();
									}
								}});
				    	 }else{
				    		 showAlert(data["message"]);
				    		 unlock();
				   }}
			});
		}
	}, 'json');
}
/**
 * 启用设置
 * 
 * @param qyzt
 * @return
 */
function qysz(qyzt){
	var qyztmc="不启用";
	if(qyzt=="1"){
		qyztmc="启用";
	}
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0) {
		showAlertDivLayer("请选择您要<font color='red'>["+qyztmc+"]</font>的记录！");
	} else {
			jQuery.post(action+"?method=updateQyzt", {
				values : ids.toString(),qyzt:qyzt
			}, function(data) {
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			}, 'json');
		}
}
/**
 * 复制
 * 
 * @return
 */
function copy(){
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0) {
		showAlertDivLayer("请选择您要<font color='red'>复制</font>的记录！");
	} else {
		jQuery.post(action+"?method=copy", {
			pzgzid : ids.toString()
		}, function(data) {
			showAlertDivLayer(data["message"]);
			jQuery("#dataTable").reloadGrid();
		}, 'json');
	}
}
function checkother(){
	var isok=true;
	jQuery("#gztj").find("input:visible[type=text]").each(function(){
		var textV=jQuery(this).val();
		if(!textV){
			isok=false;
			return false;
		}
	});
	return isok
}
/**
 * 验证是否存在空项
 * 
 * @param ids
 *            要验证的控件id "-"分隔
 * @return
 */
function check(ids){
	var id=ids.split("-");
	for(var i=0;i<id.length;i++){
		var lddm=jQuery("#"+id[i]).val();
		if(lddm==null||""==lddm){
			return false;
		}
	}
	return true;
}
// 修改
function update() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("请选择一条您要修改的记录！");
	} else {
		var sfnzmc=rows[0]["sfnzmc"];
		if(sfnzmc=="是"){
			showAlert("系统内置规则不能修改！");
			return false;
		}
		
		if(rows["sfysy"]=="已使用"){
			showAlert("该规则已使用，不能修改！");
			return false;
		}
		var pzgzid=rows[0]["pzgzid"];
		var gzdm=rows[0]["gzdm"];
		var url = action+'?method=update&pzgzid='+pzgzid+"&gzdm="+gzdm;
		showDialog("修改规则", 800, 500, url);
		// reload();
	}
}
/** ***********************按钮操作******************************* */
function addTjgz(obj){
	var table=jQuery(obj).parents("table");
	var tr=jQuery(table).find("tr").last().clone();
	tr.show();
	
	// 如果当前没有规则条件
	if(jQuery(table).find("tr").length<=2){
		loadTjgz(jQuery("#gzdm").val());  
		jQuery("select[name=tjpz]").change();
		//jQuery(table).children(".gztjTbody").append(jQuery("#template").find("tr[name=templateTr]").clone());
	}else{
		jQuery(table).find("tbody").append(tr);
		jQuery(tr).find("select").change();
	}
	initGzView(obj);
	
}
function delTjgz(obj){
	var table=jQuery(obj).parents("table");
	if(jQuery(table).find("input[name=tjcheckbox]:checked").length==0){
		showAlert('请选择您要删除的行！');
		return false;
	}
		var tr=jQuery(table).find("tr").last().clone();
	jQuery(table).find("input[name=tjcheckbox]:checked").each(function(){
		jQuery(obj).find("option").removeAttr("disabled");
		jQuery(this).parents("tr").remove();
	});
	if(jQuery(table).find("tr").length<=2){
		tr.attr("style","");
		tr.find("input[name=tjcheckbox]").attr("checked",false);
		jQuery(table).find("tbody").append(tr);
		jQuery(table).find("tr").last().hide();
	}
	initGzView(obj);
}
// 上移动
function upTjgz(obj){
	var table=jQuery(obj).parents("table");
	if(jQuery(table).find("input[name=tjcheckbox]:checked").length===0){
		showAlert('请选择您要上移的记录！');
		return false;
	}
	jQuery(table).find("input[name=tjcheckbox]:checked").each(function(){
		var _cur = jQuery(this).parents("tr");
		var _pre = _cur.prev();
		if(_cur.index()<=1){
			showAlert('已是顶行，无需上移！');
			return false;
		}else{
			_cur.insertBefore(_pre);
		}
	});
	initGzView(obj);
}
// 下移动
function downTjgz(obj){
	var table=jQuery(obj).parents("table");
	if(jQuery(table).find("input[name=tjcheckbox]:checked").length===0){
		showAlert('请选择您要下移的记录！');
		return false;
	}
	var allTr=jQuery(table).find("tr").last();
	var selectLastTr=jQuery(table).find("input[name=tjcheckbox]:checked").last().parents("tr");
	jQuery(table).find("input[name=tjcheckbox]:checked").each(function(){
		var _cur = jQuery(this).parents("tr");
		// var lastSelectTr=selectLast.parents("tr");
		var _next = _cur.next();
		var _next=getNextNotCheck(_next);
		if(selectLastTr.index()==allTr.index()){
			showAlert('已是末行，无需下移！');
			return false;
		}else{
			_cur.insertAfter(_next);
		}
	});
	initGzView(obj);
}
// 获取下一个
function getNextNotCheck(_next){
	var nextCheckbox=_next.find("input[name=tjcheckbox]");
	if(nextCheckbox.is(":checked")){
		_next=_next.next();
		_next=getNextNotCheck(_next);
	}
	return _next;
}
// 删除所有
function delAllTjgz(obj){
	var table=jQuery(obj).parents("table");
	jQuery(table).find("tr").each(function(){
		if(jQuery(this).index()>1){
			jQuery(this).remove();
		}
	});
}
/** *********************************************************** */
/**
 * 加载条件规则
 * 
 * @param gzdm
 * @return
 */
function loadTjgz(gzdm){
	
	isfbcl=false;
 	jQuery.ajax({
		url:"fbglgzdm.do?method=getTjgz",
		data:{gzdm:gzdm},
		type:"post",
		dataType:"json",
		async:false,
		success:function(data){
			var div=jQuery("#gztj");
			jQuery(div).html("");
			var template=jQuery("#template");
			for(var i=0;i<data.length;i++){
				obj=data[i];
				var bt="<font color='red'>*</font>&nbsp;";
				jQuery(template).find("span[name=tjgzmc]").html(bt+obj.tjgzmc);
				loadTjgzXx(obj.tjgzid);
				loadTemplate();
			}
		}
	});	
}
/**
 * 加载修改规则配置信息
 * 
 * @param pzgzid
 * @param tjgzid
 * @return
 */
function updateLoadGzpzTj(pzgzid,tjgzid){
	var bak;
 	jQuery.ajax({
		url:"fbglgzpztjxx.do?method=getGzpzTjxx",
		data:{pzgzid:pzgzid,tjgzid:tjgzid},
		type:"post",
		dataType:"json",
		async:false,
		success:function(data){
			bak=data;
		}
	});	
 	return bak;
}
/**
 * 加载模板数据为实际数据
 * 
 * @return
 */
function loadTemplate(){
	// 分班特殊处理，这里不做加载
	if(isfbcl){
		return false;
	}
	var div=jQuery("#gztj");
	//jQuery(div).find("tbody[class=gztjTbody]").append(jQuery("#template").find("tr[name=templateTr]").clone());
	jQuery(div).append(jQuery("#template").html());
	
	// 删除修改使用模板多余的行
	var firstTr=jQuery("#template").find("tr[name=updateTr]").first().clone();
	jQuery(template).find("tr[name=updateTr]").remove();
	// 修改显示会去掉模板行，显示完成后再增加回来。
	firstTr.find("td").html("");
	jQuery("#template>table").append(firstTr);
}
/**
 * 加载条件规则详细信息
 * 
 * @param tjgzid
 * @return
 */
function loadTjgzXx(tjgzid){
 	jQuery.ajax({
		url:"fbgltjgz.do?method=getTjpzXx",
		data:{tjgzid:tjgzid},
		type:"post",
		dataType:"json",
		async:false,
		success:function(data){
			// 分班特殊处理
			if(obj.tjgzid=="FBGZ_PJFP"){
				setFbgzPjfpHtml(data);
				return false;
			}
			var template=jQuery("#template");
			var pzgzid=jQuery("#pzgzid").val();
			var i=0;
			if(pzgzid){
				// 修改加载保存的行数次数
				var updatedata=updateLoadGzpzTj(pzgzid,obj.tjgzid);
				var lastTr=jQuery(template).find("tr").last();
				for(var d in updatedata){
					var td=jQuery(lastTr).find("td");
					td.html("");
					jQuery(td).html(getTjHtml(tjgzid,data,i));
					// 记录修改使用的行
					var trclone=lastTr.clone();
					trclone.attr("name","updateTr");
					template.find("table").append(trclone);
					i++;
				}
				lastTr.remove();
			}else{
				
				var lastTr=jQuery(template).find("tr").last();
				var td=jQuery(lastTr).find("td");
				jQuery(td).html("");
				jQuery(td).html(getTjHtml(tjgzid,data,i));
			}
			
		}
	});	
}
/**
 * 分班html特殊处理
 * @param data
 * @param i
 * @return
 */
function setFbgzPjfpHtml(data){
	isfbcl=true;
	var template=jQuery("#template").clone();
	template.find("tbody").html("");
	var html="";
		html+="<tr>";
		html+="<td></td>";
		html+="<td style=\"color: blue;\">条件 （请勾选）</td>";
		html+="</tr>";
	for(var i=0;i<data.length;i++){
		var obj=data[i];
		// 获取当前选中的tjszzd
		var pzgzid=jQuery("#pzgzid").val();
		var selectV;
		if(pzgzid){
			selectObj=getSelectTjszzd(pzgzid,obj.tjgzid,obj.sx,"");
			selectV=selectObj.tjszzd;
		}
		
		html+="<tr name=\"templateTr\" >";
			html+="<th align=\"right\" >";
				html+="<input type=\"checkbox\" name=\"tjcheckbox\" ";
				if(selectV==obj.tjszzd){
					html+="checked='true'";
				}
				html+="/>";
			html+="</th>";
			html+="<td align=\"left\" >";
				html+="<input name=\"tjgzid\" type=\"hidden\" value='"+obj.tjgzid+"' />";
				html+="<input name=\"tjszzd\" type=\"hidden\" value='"+obj.tjszzd+"' />";
				html+="<input name=\"sx\" type=\"hidden\" value='"+obj.sx+"' />";
				html+=obj.tjszmc;
				html+="<span name='"+obj.tjgzid+"'>";
				html+="<input name='xxlx' type='hidden' value='"+obj.xxlx+"' />";
				html+=getFbgzHtml(obj);
				html+="</span>";
			html+="</td>";
		html+="</tr>";
	}
	template.find("tbody").append(html);
	var div=jQuery("#gztj");
	jQuery(div).append(jQuery(template).html());
}
// 获取分班规则html
function getFbgzHtml(obj){
	var xxlx=obj.xxlx;
	var xxz=obj.xxz;
	var html="";
	if(xxlx==select){
		html+="<select style='width: 100px;margin-left: 50px;'>";
		xxz=JSON.stringify(xxz);
		if(xxz){
			xxz=xxz.substring(2,xxz.length-2);
			xxz=xxz.replaceAll("\"","");
			var xxzs=xxz.split(",");
			for(var i in xxzs){
				var myxxz=xxzs[i].split(":");
			// alert(myxxz);
				html+="<option value='"+myxxz[0]+":"+myxxz[1]+"'";
				var pzgzid=jQuery("#pzgzid").val();
				var selectObj=new Object();
				if(pzgzid){
					selectObj=getSelectTjszzd(pzgzid, obj.tjgzid,obj.sx,obj.tjszzd);
				}
				if(selectObj.xxz==myxxz[0]+":"+myxxz[1]){
					html+=" selected='true' ";
				}
				html+=" >";
				html+=myxxz[1];
				html+="</option>";
			}
		}
		html+="</select>";
	}else{
		html+="<input type='hidden' value='-1' />";
	}
	// 加载是否可修改
		html+="<input name='sfxg' type='hidden' value='-1' />";
	// 加载是否补零
		html+="<input name='sfbl' type='hidden' value='-1' />";
		html+="<input name=\"ylz\" type=\"hidden\" value='"+obj.ylz+"' />";
	return html;
}
/**
 * 获取选中信息
 * 
 * @param pzgzid
 * @param tjgzid
 * @param sx
 * @return
 */
function getSelectTjszzd(pzgzid,tjgzid,sx,tjszzd){
	var selectObj;
 	jQuery.ajax({
		url:"fbglgzpztjxx.do?method=getGzpzTjSelect",
		data:{pzgzid:pzgzid,tjgzid:tjgzid,tjszzd:tjszzd,sx:sx},
		type:"post",
		dataType:"json",
		async:false,
		success:function(data){
			selectObj=data;
		}
	});	
 	return selectObj;
}
/**
 * 获取条件规则html代码
 * 
 * @return
 */
function getTjHtml(tjgzid,data,sx){
	// 获取当前选中的tjszzd
	var pzgzid=jQuery("#pzgzid").val();
	var selectV;
	var mrylz="";//默认预留值
	if(pzgzid){
		selectObj=getSelectTjszzd(pzgzid,tjgzid,sx,"");
		selectV=selectObj.tjszzd;
	}
	
	// 条件类型
	var html="<input name=\"tjgzid\" type=\"hidden\" value='"+tjgzid+"'>";
	html+="<select name='tjpz' onclick='jldqz(this)' onchange=\"loadTjnr(this,'"+tjgzid+"')\">";
	for(var i=0;i<data.length;i++){
		jQuery("#gzylDiv").append("");
		var obj=data[i];
		html+="<option value='"+obj.tjszzd;
		if(selectV==obj.tjszzd){
			html+="' selected='true";
			mrylz=obj.ylz;
		}
		html+="'>";
		html+=obj.tjszmc;
		html+="</option>";
	}
	html+="</select>&nbsp;&nbsp;&nbsp;&nbsp;";
	html+="<span name='"+tjgzid+"'></span>";
	// 条件内容
	// html+=getTjnrHtml(data[0]);
	return html;
}

function getDataJson(){
	var qyzt=jQuery("input[name=qyzt]:checked").val();
	var gzdm=jQuery("#gzdm").val();
	var gzObject=new Object();
	var pzgzmc=jQuery("#pzgzmc").val();
	gzObject.pzgzmc=pzgzmc;
	gzObject.gzdm=gzdm;
	gzObject.qyzt=qyzt;
	gzObject.gzpzid=jQuery("#pzgzid").val();
	var gzArray=new Array();
	var xb=0;
	jQuery("#gztj").find("table").each(function(j){
		jQuery(this).find("input[name=tjgzid]").each(function(i){
			// 条件内容
			var gztjObject=new Object();
			gztjObject.tjgzid=jQuery(this).val();
			gztjObject.tjszzd=jQuery(this).next("select[name=tjpz]").val();
			gztjObject.sx=i;
			//
			var span=jQuery(this).nextAll("span[name='"+jQuery(this).val()+"']");
			// alert(span.length);
			var tjnr="";
			span.children("input,select").each(function(){
				tjnr+=jQuery(this).val()+",";
			});
			gztjObject.tjnr=tjnr;
			gzArray[xb]=gztjObject;
			xb++;
		});
	});

	gzObject.gztjObject=gzArray;
	return gzObject;
}
function getFbglJson(){
	var qyzt=jQuery("input[name=qyzt]:checked").val();
	var gzdm=jQuery("#gzdm").val();
	var gzObject=new Object();
	var pzgzmc=jQuery("#pzgzmc").val();
	gzObject.pzgzmc=pzgzmc;
	gzObject.gzdm=gzdm;
	gzObject.qyzt=qyzt;
	gzObject.gzpzid=jQuery("#pzgzid").val();
	var gzArray=new Array();
	jQuery("#gztj").find("input[name=tjcheckbox]:checked").parents("tr").find("input[name=tjgzid]").each(function(i){
		// 条件内容
		var gztjObject=new Object();
		gztjObject.tjgzid=jQuery(this).val();
		gztjObject.tjszzd=jQuery(this).next("input[name=tjszzd]").val();
		gztjObject.sx=jQuery(this).nextAll("input[name=sx]").val();
		var span=jQuery(this).nextAll("span[name='"+jQuery(this).val()+"']");
		// alert(span.length);
		var tjnr="";
		span.children().each(function(){
			tjnr+=jQuery(this).val()+",";
		});
		gztjObject.tjnr=tjnr;
		gzArray[i]=gztjObject;
	});
	gzObject.gztjObject=gzArray;
	return gzObject;
}
/**
 * 处理复选
 * 
 * @param obj
 *            条件选择对象
 * @param data
 *            对应详细配置数据
 * @return
 */
// 改变前的值
var selectTj;
function jldqz(obj){
	selectTj=jQuery(obj).find("option:selected");
}
function clfx(obj,data){
	var table=jQuery(obj).parents("table");
	// 如果不可复选
	if(data.sfkfx==bkfx){
		jQuery(table).find("select[name=tjpz]").each(function(){
			jQuery(this).find("option").each(function(){
				if(jQuery(this).val()==jQuery(obj).val()){
					jQuery(this).attr("disabled","disabled");
					return false;
				}
			});
		});
		jQuery(obj).find("option").removeAttr("disabled");
	}else{
		jQuery(table).find("select[name=tjpz]").each(function(){
			jQuery(this).find("option").each(function(){
				// alert(jQuery(selectTj).val());
				if(jQuery(this).val()==jQuery(selectTj).val()){
					jQuery(this).removeAttr("disabled");
					return false;
				}
			});
		});
	}
}
/**
 * 加载条件配置内容
 */
function loadTjnr(obj,tjgzid){
	var selectV=jQuery(obj).val();
 	jQuery.ajax({
		url:"fbgltjgz.do?method=getTjNrpzXx",
		data:{tjgzid:tjgzid,tjszzd:selectV},
		type:"post",
		dataType:"json",
		async:false,
		success:function(data){
			var pzgzid=jQuery("#pzgzid").val();
			var selectObj=new Object();
			if(pzgzid){
				var i=jQuery(obj).parents("tr").index();
				selectObj=getSelectTjszzd(pzgzid, tjgzid, i-1,selectV);
			}
			var span=jQuery(obj).next();
			jQuery(span).html("");
			jQuery(span).html(getTjnrHtml(data,selectObj));
			// 复选处理
			//clfx(obj,data);
		}
	});	
 	initGzView(obj);
 	
}
/**
 * 获取条件内容
 * 
 * @param obj
 * @return
 */
function getTjnrHtml(obj,selectObj){
	var mrylz_old=obj.mrylz;
	var html="<input name='xxlx' type='hidden' value='"+obj.xxlx+"' />";
	if(obj.xxlx==defalut){
		html+="<input type='hidden' value='-1' />";
	}else if(obj.xxlx==text){
		if(!obj.xxz){
			html+="<input type='text' style='width: 100px;' ";
		}else{
			var myObj=obj.xxz.split(":");
			html+="<input type='text' "+myObj[0]+"='"+myObj[1]+"' style='width: 100px;' ";
		}
		if(selectObj.xxz){
			obj.mrylz=selectObj.xxz;
			html+="value='"+selectObj.xxz+"'";
		}
		html+="onkeyup=\"getZdyzf(this)\"/>";
	}else if(obj.xxlx==select){
		html+="<select style='width: 100px;'";
		var xxz=JSON.stringify(obj.xxz);
		
		if(xxz){
			html+=" onchange='getXsws(this)'>";
			xxz=xxz.substring(2,xxz.length-2);
			xxz=xxz.replaceAll("\"","");
			var xxzs=xxz.split(",");
			var mrylz_old = obj.mrylz;
			if("BXHGZ_XHPX"!=obj.tjgzid){
				obj.mrylz=mrylz_old.substring(mrylz_old.length-parseInt(xxzs[0].split(":")[0]),mrylz_old.length);
			}
			for(var i in xxzs){
				var myxxz=xxzs[i].split(":");
				html+="<option value='"+myxxz[0]+"'";
				if(selectObj.xxz&&selectObj.xxz==myxxz[0]){
					html+=" selected='true' ";
					if("BXHGZ_XHPX"!=obj.tjgzid){
					obj.mrylz=mrylz_old.substring(mrylz_old.length-parseInt(myxxz[0]),mrylz_old.length);
					}
				}
				html+=" >";
				html+=myxxz[1];
				html+="</option>";
			}
			
		}else{
			html+=">";
		}
		html+="</select>";
		html+="&nbsp;&nbsp;";
	}else if(obj.xxlx==qzw){
		var qsw="0";//起始位
		var zzw=obj.mrylz.length;//终止位
		html+="起始位:<input type='text' style='width: 50px;' onkeyup=\"value=value.replace(/[^\\d]/g,'');getQsw(this)\"";
		if(selectObj.xxz){
			var xxz=selectObj.xxz;
			var xxzs=xxz.split("~");
			html+=" value='"+xxzs[0]+"'";
			qsw=xxzs[0];
		}else{
			html+=" value='0'";
		}
		html+="/>";
		html+="&nbsp;&nbsp;&nbsp;&nbsp;";
		html+="位数:<input type='text' style='width: 50px;' onkeyup=\"value=value.replace(/[^\\d]/g,'');getZzw(this)\"";
		if(selectObj.xxz){
			var xxz=selectObj.xxz;
			var xxzs=xxz.split("~");
			html+=" value='"+xxzs[1]+"'";
			zzw=xxzs[1];
		}
		obj.mrylz=obj.mrylz.substring(parseInt(qsw),parseInt(zzw));
		html+="/>";
		html+="&nbsp;&nbsp;";
		html+="<font color='red'>默认从0位开始</font>";
	}else{
		html+="<input type='hidden' value='-1' />";
	}
	// 加载是否可修改
	if(obj.sfkxg){
		html+="<select style='width: 100px;'>";
		if(selectObj.sfkxg){
			if(selectObj.sfkxg==kxg){
				html+="<option value='"+kxg+"' selected='true'>可修改</option>";
				html+="<option value='"+bkxg+"'>不可修改</option>";
			}else if(selectObj.sfkxg=bkxg){
				html+="<option value='"+kxg+"' >可修改</option>";
				html+="<option value='"+bkxg+"' selected='true'>不可修改</option>";
			}
		}else{
			if(obj.sfkxg==kxg){
				html+="<option selected='selected' value='"+kxg+"'>可修改</option>";
				html+="<option value='"+bkxg+"'>不可修改</option>";
			}else if(obj.sfkxg==bkxg){
				html+="<option value='"+kxg+"'>可修改</option>";
				html+="<option selected='selected' value='"+bkxg+"'>不可修改</option>";
			}
		}
		html+="</select>";
		html+="&nbsp;&nbsp;";
	}else{
		html+="<input type='hidden' value='-1' />";
	}
	// 加载是否补零
	if(obj.wsbl){
		html+="<select style='width: 100px;'>";
		if(selectObj.wsbl){
			if(selectObj.wsbl==bl){
				html+="<option selected='selected' value='"+bl+"'>位数不足自动补零</option>";
				html+="<option value='"+bbl+"'>位数不足不补零</option>";
			}else if(selectObj.wsbl=bbl){
				html+="<option value='"+bl+"'>位数不足自动补零</option>";
				html+="<option selected='selected' value='"+bbl+"'>位数不足不补零</option>";
			}
		}else{
			if(obj.wsbl==bl){
				html+="<option selected='selected' value='"+bl+"'>位数不足自动补零</option>";
				html+="<option value='"+bbl+"'>位数不足不补零</option>";
			}else if(obj.wsbl==bbl){
				html+="<option value='"+bl+"'>位数不足自动补零</option>";
				html+="<option selected='selected' value='"+bbl+"'>位数不足不补零</option>";
			}
		}
		html+="</select>";
		html+="&nbsp;&nbsp;";
	}else{
		html+="<input type='hidden' value='-1' />";
	}
	html+="<input name=\"mrylz\" type=\"hidden\" value='"+obj.mrylz+"' />";
	html+="<input name=\"ylz\" type=\"hidden\" value='"+obj.ylz+"' />";
	html+="<input name=\"mrylz_old\" type=\"hidden\" value='"+mrylz_old+"' />";
	
	//编学号流水号
	if("TJ_LSH"==obj.tjszzd&&"BXHGZ"==jQuery("#gzdm").val()){
		var qsz="";
		if(null!=selectObj.qsz){
			qsz=selectObj.qsz;
		}
		html+="起始值:<input name=\"qsz\" type='text' style='width: 50px;' value='"+qsz+"' />";
		html+="&nbsp;&nbsp;";
	}else{
		html+="<input name=\"qsz\" type=\"hidden\" value='-1' />";
	}
	return html;
}
// 设置预览
function szyl(){
	var pzgzid=jQuery("#pzgzid").val();
	jQuery("font[name=gzyl]").each(function(){
		var tjgzid=jQuery(this).next("input[name=tjgzid]").val();
		var html=gzylHtml(pzgzid,tjgzid);
		if(""==html){
			jQuery(this).parents("[name=yl]").hide();
		}
		jQuery(this).html(html);
	});
}

//显示位数切换
function getXsws(obj){
	var parSpan = jQuery(obj).parents("span");
	var mrylz_old = parSpan.find("input[name=mrylz_old]").val();
	var mrylz_new=mrylz_old.substring(mrylz_old.length-parseInt(obj.value),mrylz_old.length);
	parSpan.find("input[name=mrylz]").val(mrylz_new);
	initGzView(obj);
}
//起止位截取
function getQsw(obj){
	var parSpan = jQuery(obj).parents("span");
	var mrylz_old = parSpan.find("input[name=mrylz_old]").val();
	var nextInput = jQuery(obj).next().val();
	if(""==nextInput||null==nextInput){
		nextInput=mrylz_old.length;
	}
	var mrylz_new = mrylz_old.substring(parseInt(obj.value),nextInput);
	parSpan.find("input[name=mrylz]").val(mrylz_new);
	initGzView(obj);
}

function getZzw(obj){
	var parSpan = jQuery(obj).parents("span");
	var mrylz_old = parSpan.find("input[name=mrylz_old]").val();
	var prevInput = jQuery(obj).prev().val();
	if(""==prevInput||null==prevInput){
		prevInput=0;
	}
	var mrylz_new = mrylz_old.substring(parseInt(prevInput),parseInt(obj.value));
	parSpan.find("input[name=mrylz]").val(mrylz_new);
	initGzView(obj);
	
}
function getZdyzf(obj){
	var parSpan = jQuery(obj).parents("span");
	var mrylz_old = parSpan.find("input[name=mrylz_old]").val();
	parSpan.find("input[name=mrylz]").val(obj.value);
	initGzView(obj);	
}
function checkQsz(){
	var isok=true;
	jQuery("input:visible[name=qsz]").each(function(){
		var lshws = jQuery(this).parents("span").find("select").val();
		var textV=jQuery(this).val();
		if(textV.length>parseInt(lshws)){
			isok=false;
			return false;
		}
	});
	return isok;
	
}

