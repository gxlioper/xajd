/** ********ѡ������************* */
var defalut="0";// Ĭ��
var text="1";// �ı���
var select="2";// ������
var qzw="3";// ����
/** ****************************** */

/** *******�Ƿ�ɸ�ѡ************** */
var kfx="1";// �ɸ�ѡ
var bkfx="0";// ���ɸ�ѡ
/** ****************************** */

/** ********�Ƿ���޸�************* */
var bkxg="0";// �����޸�
var kxg="1";// ���޸�
/** ****************************** */

/** ********λ������************* */
var bbl="0";// ���Զ�����
var bl="1";// �Զ�����
/** ****************************** */
var isfbcl=false;// Ĭ�ϷǷְദ��

var action="fbglgzpztj.do";
var title="�����趨";
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}
// ��������ת
function dcmcLink(cellValue, rowObject) {
	var pzgzid = rowObject["pzgzid"];
	return "<a href='javascript:void(0);' onclick=\"ckxx('" + pzgzid
			+ "')\" class='name'>" + cellValue + "</a>";
}
// �鿴��Ϣ
function ckxx(id) {
	var url = action+"?method=showView&pzgzid=" + id;
	var cktitle =title+"��Ϣ";
	showDialog(cktitle, 750, 400, url);
}
function drxx(){
	toImportData("IMPORT_FBGL_XSXX");
	return false;
}
// ɾ��
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0) {
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
	} else {
		showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��", {
			"okFun" : function() {
				jQuery.post(action+"?method=del", {
					values : ids.toString()
				}, function(data) {
					var mes="�ɹ�ɾ����<font color='green'>&nbsp;"+data["successDel"]+"&nbsp;</font>������";
					mes+="</br>";
					if(data["errorObject"]!="-1"){
						var list=data["errorObject"];
						for(var i=0;i<list.length;i++){
							var messageObj=list[i];
							mes+="<font color='red'>"+messageObj["pzgzmc"]+"</font>";
							if(messageObj["qyzt"]=="1"){
								mes+="�Ѿ����û�ʹ�ã�����ɾ����";
							}else if(messageObj["sfnz"]=="1"){
								mes+="��ϵͳ���ù��򣬲���ɾ����";
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
// ����
function add() {
		var url =action+"?method=add";
		var sqtitle =title+"����";
		showDialog(sqtitle, 800, 500, url);
		// reload();
}
/**
 * ����
 * 
 * @param url
 * @param checkId
 * @return
 */
function save(url,checkId){
	if(!check(checkId)){
		return showAlert("�뽫��<font color='red'>*</font>����Ŀ��д������");
	}
	if(!checkother()){
		return showAlert("������Ϣ������д������");
	}
	var lshLen=jQuery("select:visible[name=tjpz][value='TJ_LSH']").length;
	var  gzdm = jQuery("#gzdm").val();
	if(lshLen<1&&"FBGZ"!=gzdm){
		return showAlert("�������ˮ�ţ�");
	}
	if(!checkQsz()){
		return showAlert("��ˮ����ʼֵλ�����ܳ�����ˮ��λ����");
	}
	// ������ť
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
			showAlert("�˹��������������ã�ֻ������Ϊ�����ã�");
			unlock();
		}else{
			jQuery.ajax({
				   type: "POST",
				   url: url,
				   dataType:"json",
				   data:{json:json},
				   success: function(data){
					   if(data["message"]=="����ɹ���"){
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
 * ��������
 * 
 * @param qyzt
 * @return
 */
function qysz(qyzt){
	var qyztmc="������";
	if(qyzt=="1"){
		qyztmc="����";
	}
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0) {
		showAlertDivLayer("��ѡ����Ҫ<font color='red'>["+qyztmc+"]</font>�ļ�¼��");
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
 * ����
 * 
 * @return
 */
function copy(){
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0) {
		showAlertDivLayer("��ѡ����Ҫ<font color='red'>����</font>�ļ�¼��");
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
 * ��֤�Ƿ���ڿ���
 * 
 * @param ids
 *            Ҫ��֤�Ŀؼ�id "-"�ָ�
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
// �޸�
function update() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	} else {
		var sfnzmc=rows[0]["sfnzmc"];
		if(sfnzmc=="��"){
			showAlert("ϵͳ���ù������޸ģ�");
			return false;
		}
		
		if(rows["sfysy"]=="��ʹ��"){
			showAlert("�ù�����ʹ�ã������޸ģ�");
			return false;
		}
		var pzgzid=rows[0]["pzgzid"];
		var gzdm=rows[0]["gzdm"];
		var url = action+'?method=update&pzgzid='+pzgzid+"&gzdm="+gzdm;
		showDialog("�޸Ĺ���", 800, 500, url);
		// reload();
	}
}
/** ***********************��ť����******************************* */
function addTjgz(obj){
	var table=jQuery(obj).parents("table");
	var tr=jQuery(table).find("tr").last().clone();
	tr.show();
	
	// �����ǰû�й�������
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
		showAlert('��ѡ����Ҫɾ�����У�');
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
// ���ƶ�
function upTjgz(obj){
	var table=jQuery(obj).parents("table");
	if(jQuery(table).find("input[name=tjcheckbox]:checked").length===0){
		showAlert('��ѡ����Ҫ���Ƶļ�¼��');
		return false;
	}
	jQuery(table).find("input[name=tjcheckbox]:checked").each(function(){
		var _cur = jQuery(this).parents("tr");
		var _pre = _cur.prev();
		if(_cur.index()<=1){
			showAlert('���Ƕ��У��������ƣ�');
			return false;
		}else{
			_cur.insertBefore(_pre);
		}
	});
	initGzView(obj);
}
// ���ƶ�
function downTjgz(obj){
	var table=jQuery(obj).parents("table");
	if(jQuery(table).find("input[name=tjcheckbox]:checked").length===0){
		showAlert('��ѡ����Ҫ���Ƶļ�¼��');
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
			showAlert('����ĩ�У��������ƣ�');
			return false;
		}else{
			_cur.insertAfter(_next);
		}
	});
	initGzView(obj);
}
// ��ȡ��һ��
function getNextNotCheck(_next){
	var nextCheckbox=_next.find("input[name=tjcheckbox]");
	if(nextCheckbox.is(":checked")){
		_next=_next.next();
		_next=getNextNotCheck(_next);
	}
	return _next;
}
// ɾ������
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
 * ������������
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
 * �����޸Ĺ���������Ϣ
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
 * ����ģ������Ϊʵ������
 * 
 * @return
 */
function loadTemplate(){
	// �ְ����⴦�����ﲻ������
	if(isfbcl){
		return false;
	}
	var div=jQuery("#gztj");
	//jQuery(div).find("tbody[class=gztjTbody]").append(jQuery("#template").find("tr[name=templateTr]").clone());
	jQuery(div).append(jQuery("#template").html());
	
	// ɾ���޸�ʹ��ģ��������
	var firstTr=jQuery("#template").find("tr[name=updateTr]").first().clone();
	jQuery(template).find("tr[name=updateTr]").remove();
	// �޸���ʾ��ȥ��ģ���У���ʾ��ɺ������ӻ�����
	firstTr.find("td").html("");
	jQuery("#template>table").append(firstTr);
}
/**
 * ��������������ϸ��Ϣ
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
			// �ְ����⴦��
			if(obj.tjgzid=="FBGZ_PJFP"){
				setFbgzPjfpHtml(data);
				return false;
			}
			var template=jQuery("#template");
			var pzgzid=jQuery("#pzgzid").val();
			var i=0;
			if(pzgzid){
				// �޸ļ��ر������������
				var updatedata=updateLoadGzpzTj(pzgzid,obj.tjgzid);
				var lastTr=jQuery(template).find("tr").last();
				for(var d in updatedata){
					var td=jQuery(lastTr).find("td");
					td.html("");
					jQuery(td).html(getTjHtml(tjgzid,data,i));
					// ��¼�޸�ʹ�õ���
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
 * �ְ�html���⴦��
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
		html+="<td style=\"color: blue;\">���� ���빴ѡ��</td>";
		html+="</tr>";
	for(var i=0;i<data.length;i++){
		var obj=data[i];
		// ��ȡ��ǰѡ�е�tjszzd
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
// ��ȡ�ְ����html
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
	// �����Ƿ���޸�
		html+="<input name='sfxg' type='hidden' value='-1' />";
	// �����Ƿ���
		html+="<input name='sfbl' type='hidden' value='-1' />";
		html+="<input name=\"ylz\" type=\"hidden\" value='"+obj.ylz+"' />";
	return html;
}
/**
 * ��ȡѡ����Ϣ
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
 * ��ȡ��������html����
 * 
 * @return
 */
function getTjHtml(tjgzid,data,sx){
	// ��ȡ��ǰѡ�е�tjszzd
	var pzgzid=jQuery("#pzgzid").val();
	var selectV;
	var mrylz="";//Ĭ��Ԥ��ֵ
	if(pzgzid){
		selectObj=getSelectTjszzd(pzgzid,tjgzid,sx,"");
		selectV=selectObj.tjszzd;
	}
	
	// ��������
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
	// ��������
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
			// ��������
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
		// ��������
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
 * ����ѡ
 * 
 * @param obj
 *            ����ѡ�����
 * @param data
 *            ��Ӧ��ϸ��������
 * @return
 */
// �ı�ǰ��ֵ
var selectTj;
function jldqz(obj){
	selectTj=jQuery(obj).find("option:selected");
}
function clfx(obj,data){
	var table=jQuery(obj).parents("table");
	// ������ɸ�ѡ
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
 * ����������������
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
			// ��ѡ����
			//clfx(obj,data);
		}
	});	
 	initGzView(obj);
 	
}
/**
 * ��ȡ��������
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
		var qsw="0";//��ʼλ
		var zzw=obj.mrylz.length;//��ֹλ
		html+="��ʼλ:<input type='text' style='width: 50px;' onkeyup=\"value=value.replace(/[^\\d]/g,'');getQsw(this)\"";
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
		html+="λ��:<input type='text' style='width: 50px;' onkeyup=\"value=value.replace(/[^\\d]/g,'');getZzw(this)\"";
		if(selectObj.xxz){
			var xxz=selectObj.xxz;
			var xxzs=xxz.split("~");
			html+=" value='"+xxzs[1]+"'";
			zzw=xxzs[1];
		}
		obj.mrylz=obj.mrylz.substring(parseInt(qsw),parseInt(zzw));
		html+="/>";
		html+="&nbsp;&nbsp;";
		html+="<font color='red'>Ĭ�ϴ�0λ��ʼ</font>";
	}else{
		html+="<input type='hidden' value='-1' />";
	}
	// �����Ƿ���޸�
	if(obj.sfkxg){
		html+="<select style='width: 100px;'>";
		if(selectObj.sfkxg){
			if(selectObj.sfkxg==kxg){
				html+="<option value='"+kxg+"' selected='true'>���޸�</option>";
				html+="<option value='"+bkxg+"'>�����޸�</option>";
			}else if(selectObj.sfkxg=bkxg){
				html+="<option value='"+kxg+"' >���޸�</option>";
				html+="<option value='"+bkxg+"' selected='true'>�����޸�</option>";
			}
		}else{
			if(obj.sfkxg==kxg){
				html+="<option selected='selected' value='"+kxg+"'>���޸�</option>";
				html+="<option value='"+bkxg+"'>�����޸�</option>";
			}else if(obj.sfkxg==bkxg){
				html+="<option value='"+kxg+"'>���޸�</option>";
				html+="<option selected='selected' value='"+bkxg+"'>�����޸�</option>";
			}
		}
		html+="</select>";
		html+="&nbsp;&nbsp;";
	}else{
		html+="<input type='hidden' value='-1' />";
	}
	// �����Ƿ���
	if(obj.wsbl){
		html+="<select style='width: 100px;'>";
		if(selectObj.wsbl){
			if(selectObj.wsbl==bl){
				html+="<option selected='selected' value='"+bl+"'>λ�������Զ�����</option>";
				html+="<option value='"+bbl+"'>λ�����㲻����</option>";
			}else if(selectObj.wsbl=bbl){
				html+="<option value='"+bl+"'>λ�������Զ�����</option>";
				html+="<option selected='selected' value='"+bbl+"'>λ�����㲻����</option>";
			}
		}else{
			if(obj.wsbl==bl){
				html+="<option selected='selected' value='"+bl+"'>λ�������Զ�����</option>";
				html+="<option value='"+bbl+"'>λ�����㲻����</option>";
			}else if(obj.wsbl==bbl){
				html+="<option value='"+bl+"'>λ�������Զ�����</option>";
				html+="<option selected='selected' value='"+bbl+"'>λ�����㲻����</option>";
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
	
	//��ѧ����ˮ��
	if("TJ_LSH"==obj.tjszzd&&"BXHGZ"==jQuery("#gzdm").val()){
		var qsz="";
		if(null!=selectObj.qsz){
			qsz=selectObj.qsz;
		}
		html+="��ʼֵ:<input name=\"qsz\" type='text' style='width: 50px;' value='"+qsz+"' />";
		html+="&nbsp;&nbsp;";
	}else{
		html+="<input name=\"qsz\" type=\"hidden\" value='-1' />";
	}
	return html;
}
// ����Ԥ��
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

//��ʾλ���л�
function getXsws(obj){
	var parSpan = jQuery(obj).parents("span");
	var mrylz_old = parSpan.find("input[name=mrylz_old]").val();
	var mrylz_new=mrylz_old.substring(mrylz_old.length-parseInt(obj.value),mrylz_old.length);
	parSpan.find("input[name=mrylz]").val(mrylz_new);
	initGzView(obj);
}
//��ֹλ��ȡ
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

