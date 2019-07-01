var action = "jtpjjg.do";
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}
function pjjtmc(cellValue, rowObject) {
	return "<a href='javascript:void(0);' onclick=\"ckxx('" + rowObject["jgid"]
			+ "')\" class='name'>" + cellValue + "</a>";
}
// �鿴��Ϣ
function ckxx(jgid) {
	var url = action + "?method=showView&jgid=" + jgid;
	showDialog("������Ϣ", 800, 500, url);
}

function jxpdzq(cellValue, rowObject) {
	var pdxq=rowObject["pdxqmc"];
	if(!pdxq){
		pdxq="";
	}
	return rowObject["pdxn"] + pdxq;
}
function sqzq(cellValue, rowObject) {
	return rowObject["sqxn"] + rowObject["sqxqmc"];
}
function sjly(cellValue, rowObject) {
	if(rowObject["sjly"]=="1"){
		return "��";
	}
	return "��";
}
// ����
function add() {
	var url = action + "?method=add";
	showDialog("������������", 800, 500, url);
}
/**
 * ���ؽ�����Ϣ
 * 
 * @return
 */
function loadJxxx() {
	var jxid = jQuery("#jxid").val();
	// ���Ϊ��
	if(jxid !=null && jxid != ""){
		jQuery.post("jtpjsz.do?method=loadJxxx", {
			jxid : jxid
		}, function(data) {
			var pdzq="";
			if(data.pdxn){
				pdzq=data.pdxn + " " + data.pdxqmc;
			}
			jQuery("#pdzq").text(pdzq);
			// ����
			jQuery("#pdxn").val(data.pdxn);
			jQuery("#pdxq").val(data.pdxq);
			jQuery("#jxsm").text(data.jxsm);			
			if (data.jtpjdw == "bj") {
				loadBjpjxx();
			} else if (data.jtpjdw == "xy") {
				loadXypjxx();
			} else if (data.jtpjdw == "qs") {
				loadQspjxx();
			}else {
				loadOther();
			}
			jQuery("#jtpjxx").show();
		}, 'json');
	}else{
		jQuery("#pdzq").text("");
		jQuery("#jxsm").text("");
		jQuery("#jtpjxx").hide();		
	}
	checkJx(jxid);
}
function loadUpdate() {
	jQuery.post("jtpjjg.do?method=loadUpdate", {
		jgid : jQuery("#jgid").val()
	}, function(data) {
		if (data) {
			autoSetParam(data, false);
		}
	}, 'json');
}
/**
 * ���ذ༶������Ϣ
 * 
 * @return
 */
function loadBjpjxx() {
	jQuery.ajaxSetup( {
		async : false
	});
	var bjdm = jQuery("#pjjtid").val();
	var type = jQuery("#type").val();
	var jgid = jQuery("#jgid").val();
	if (!bjdm) {
		bjdm = jQuery("#mrpjjtiid").val();
	}
	jQuery("#jtpjxx").load(
			"jtpjjg.do?method=loadBjpjxx&bjdm=" + bjdm + "&type=" + type
					+ "&jgid=" + jgid,function(){
				fomartForm();
			});
	setQsxxShow(bjdm);
	jQuery.ajaxSetup( {
		async : true
	});
	loadUpdate();
}
/**
 * ����������Ϣ��ʾ
 * 
 * @return
 */
function setQsxxShow(bjdm){
	var iswzdx=jQuery("#iswzdx").val();
	//��������ݴ�ѧ
	if(iswzdx=="1"){
		jQuery("#qswmxx").load("jtpjsq.do?method=qsxxlist&bjdm=" + bjdm);
	}
}

/**
 * ͨ������ѧ��ѧ��ȡ�ü��������б�
 * @return
 */
function loadJtpjList(){	
	var sqxn = jQuery("#sqxn").val();
	var sqxq = jQuery("#sqxq").val();
	var jxid = jQuery("#jxid").val();
	if(sqxn !=null && sqxn !=""  && sqxq !=null && sqxq !="" ){
		// ���÷�������
		jQuery.post("jtpjsz.do?method=loadJtpjList",{sqxn:sqxn,sqxq:sqxq},function(data){
			jQuery("#jxid").empty();
			jQuery("#jxid").append("<option value=''>&nbsp;</option>");
			jQuery(data["jtpjList"]).each(function(){
				if(jxid == this.jxid){
					jQuery("#jxid").append(jQuery("<option/>").text(this.jxmc).attr("value",this.jxid).attr("selected","true"));
				}else{
					jQuery("#jxid").append(jQuery("<option/>").text(this.jxmc).attr("value",this.jxid));
				}
			});
		},'json');
	}else{
		jQuery("#jxid").empty();
	}
	loadJxxx();
}
/**
 * ����ѧԺ������Ϣ
 * 
 * @return
 */
function loadXypjxx() {
	jQuery.ajaxSetup( {
		async : false
	});
	var xydm = jQuery("#pjjtid").val();
	var type = jQuery("#type").val();
	var jgid = jQuery("#jgid").val();
	if (!xydm) {
		xydm = jQuery("#mrpjjtiid").val();
	}
	jQuery("#jtpjxx").load(
			"jtpjjg.do?method=loadXypjxx&xydm=" + xydm + "&type=" + type
					+ "&jgid=" + jgid,function(){
				fomartForm();
			});
	jQuery.ajaxSetup( {
		async : true
	});
	loadUpdate();
}


function loadOther() {
	jQuery.ajaxSetup( {
		async : false
	});
	var type = jQuery("#type").val();
	var jgid = jQuery("#jgid").val();
	jQuery("#jtpjxx").load(
			"jtpjjg.do?method=loadOtherPjxx&type=" + type + "&jgid=" + jgid,function(){
				fomartForm();
			});
	jQuery.ajaxSetup( {
		async : true
	});
	loadUpdate();
}
/**
 * �޸�
 * 
 * @return
 */
function update() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlert("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	} else {
		var sjly = rows[0]["sjly"];
		if ("1" == sjly) {
			showAlertDivLayer("�����޸��������ݣ�");
			return false;
		}
		var url = action + '?method=update&jgid=' + rows[0]["jgid"];
		var title = "�޸ļ�����������";
		showDialog(title, 800, 500, url);
	}
}
/**
 * ���ü�����������
 */
function setJtpjmc() {
	//if (!jQuery("#pjjtmc").val()) {
		var pjjtmc = jQuery("#pjjtid>option:selected").text();
		if(pjjtmc){
			jQuery("#pjjtmc").val(pjjtmc);
		}
	//}
}
/**
 * ����
 * 
 * @param url
 * @param checkId
 * @return
 */
function save(url,checkId) {
	fomartForm();
	setJtpjmc();	
	if (!checkNull(checkId)) {
		return false;
	}
	lock();
	jQuery("#form").ajaxSubmit( {
		url : url,
		type : "post",
		dataType : "json",
		success : function(data) {
		if(data["message"] == "����ɹ���"){
			showAlert(data["message"], {}, {
				"clkFun" : function() {
					if (parent.window) {
						refershParent();
					}
				}
			});
	  }else{
		 showAlert(data["message"]);
	   }
		},
		contentType : "application/x-www-form-urlencoded;charset=utf-8"
	});
	unlock();
}
/**
 * ȥ����Ƕ��form
 * 
 * @return
 */
function fomartForm() {
	var html = jQuery("#innerForm").html();
	if(html){
		jQuery("#jtpjxx").html(html);
	}
}
/**
 * ��֤�Ƿ���ڿ���
 * 
 * @param ids
 *            Ҫ��֤�Ŀؼ�id "-"�ָ�
 * @return
 */
function check(ids) {
	var id = ids.split("-");
	for ( var i = 0; i < id.length; i++) {
		var lddm = jQuery("#" + id[i]).val();
		if (lddm == null || "" == lddm) {
			alert(id[i]);
			return false;
		}
	}
	return true;
}
// ɾ��
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0) {
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
	} else {

  var rows = jQuery("#dataTable").getSeletRow(); 
  for ( var i = 0; i <ids.length; i++) { 
	  if (rows[i]['sjly'] == '1') {
		  showAlertDivLayer("ֻ��ɾ�����������ݣ�");
		  return false; 
	  } 
	}
		showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��", {
			"okFun" : function() {
				jQuery.post(action + "?method=del", {
					values : ids.toString()
				}, function(data) {
					var mes = "�ɹ�ɾ����<font color='green'>&nbsp;"
							+ data["successDel"] + "&nbsp;</font>������";
					mes += "</br>";
					showAlertDivLayer(mes, {}, {
						"clkFun" : function() {
							jQuery("#dataTable").reloadGrid();
						}
					});
				}, 'json');
			}
		});

	}
}
// �Զ��嵼�� ����
function exportConfig() {
	// DCCLBH�������ܱ��,ִ�е�������
	customExport('jtpjjgbase.do',exportData);
}

// ��������
function exportData(dcclbh) {
	setSearchTj();// ���ø߼���ѯ����
	var url = "jtpjjg.do?method=exportData&dcclbh=" + dcclbh;// dcclbh,�������ܱ��
	url = addSuperSearchParams(url);// ���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}
function drxx(){
	toImportData("IMPORT_JTPJ_JTPJJG");
	return false;
}
//ɽ��������ҽְҵѧԺ�Ƚ��༯���Ƽ����ӡ
function getXjbjt_12947(){
	var rows = jQuery("#dataTable").getSeletRow();
	
	 if (rows.length == 0){
		showAlertDivLayer("��ѡ����Ҫ���صļ�¼��");
	 } else {
		var flag = false;
		 for(var i = 0; i < rows.length; i++){
			 if(rows[i]["jtpjdw"] != 'bj'){
				 flag = true;
			 }
		 }
		 if(flag){
			 showAlertDivLayer("��ѡ���԰༶Ϊ��λ�ļ�¼��");
			 return false;
		 }
		var ids = jQuery("#dataTable").getSeletIds();
		var url="jtpjjg.do?method=getSdxmWord_xjbjt&value="+ids;
		window.open(url);
	 } 
}

function getWordXjbjt(){
	var rows = jQuery("#dataTable").getSeletRow();
	
	 if (rows.length == 0){
		showAlertDivLayer("��ѡ����Ҫ���صļ�¼��");
	 } else if (rows.length > 1){
		var flag = false;
		 for(var i = 0; i < rows.length; i++){
			 if(rows[i]["jtpjdw"] != 'bj'){
				 flag = true;
			 }
		 }
		 if(flag){
			 showAlertDivLayer("��ѡ���԰༶Ϊ��λ�ļ�¼��");
			 return false;
		 }
		var ids = jQuery("#dataTable").getSeletIds();
		var url="jtpjjg.do?method=getDjbZip_12947&value="+ids;
		window.open(url);
	 } else {
		 if(rows[0]["jtpjdw"] != 'bj'){
			 showAlertDivLayer("��ѡ���԰༶Ϊ��λ�ļ�¼��");
			 return false;
		 }
		var url="jtpjjg.do?method=getDjbWord_12947&jgid="+rows[0]["jgid"];
		window.open(url);
    }
}

//��֤��������Ƽ���ѧ��
function checkJx(jxiddm) {
	if(jQuery("#xxdm").val() == '10704'){
		jQuery.ajaxSetup({async:false});
			jQuery.getJSON('jtpjjg.do?method=isBjjx',{jxid:jxiddm},function(data){
				if(data){
					jQuery("#rdfsLocation").empty();
					html = '<tr>';
					html+= '<th width="20%" style="border-top: 0px;">��������</th>';
					html+= '<td colspan="3" style="border-top: 0px;"><input type="text" id="rdfs" name="rdfs" value="" onblur="checkZs(this)"/></td>';
					html+= '</tr>'
					jQuery("#rdfsLocation").html(html);					
				}else{
					jQuery("#rdfsLocation").empty();
				}
			});
		jQuery.ajaxSetup({async:true});
		return;
	}else{
		return;
	}
}

/**
 * ��������������Ϣ
 */
function loadQspjxx() {
	jQuery.ajaxSetup( {
		async : false
	});
	var type=jQuery("#type").val();
	var jgid = jQuery("#jgid").val();
	jQuery("#jtpjxx").load("jtpjjg.do?method=loadld&type=" + type+"&jgid=" + jgid,function(){
				fomartForm();
			});
	loadLdxx();
	jQuery.ajaxSetup({async : true});
	loadUpdate();
}

function loadLdxx(){
	var pjjtid=jQuery("#mrpjjtiid").val();
	var first=jQuery("#first").val();
	if("1"==first&&""!=pjjtid&&null!=pjjtid){
		var pjjtids=pjjtid.split("@@");
		 jQuery('#lddm').val(pjjtids[0]);
	}
	jQuery('#ldxb').empty();
	jQuery('#ch').empty();
	jQuery('#qsh').empty();
	jQuery.post('gyglnew_qsgl.do?method=loadLdxx', { lddm : jQuery('#lddm').val() }, function(data) {
		var qsch = parseInt(data.qsch); //��ʼ���
		var ldcs = parseInt(data.ldcs); //¥������
		var count = 0;
		jQuery('#ldxb').text(data.ldxb);
		var option = "<option value=''></option>"
		for(var i=qsch;i<=ldcs;i++){
			option += "<option value='" +i+"'>"+i+"</option>";
		}
		jQuery('#ch').append(option);
	}, 'json');
	loadChxx();
}


function loadChxx() {
	jQuery('#qsh').empty();
	var lddm=jQuery('#lddm').val();
	if(!(lddm==null||lddm=="")){
		jQuery.getJSON('gyglnew_cwgl.do?method=getQshForLddm', {
			lddm : lddm, ch : jQuery('#ch').val() },function(data) {
					var option = "<option value=''></option>";
					if (data != null && data.length != 0) {
						for ( var i = 0; i < data.length; i++) {
							option +="<option value=\""+data[i].qsh+"\">"	+data[i].qsh+"</option>";
						}
					}
					jQuery('#qsh').append(option);
				});
	} else {
		var option = "<option value=''></option>";
		jQuery('#qsh').append(option);
	}
	loadQsxx();
}

function loadQsxx(){
	jQuery.ajaxSetup( {
		async : false
	});
	var pjjtid=jQuery("#mrpjjtiid").val();
	var first=jQuery("#first").val();
	if("1"==first&&""!=pjjtid&&null!=pjjtid){
		var pjjtids=pjjtid.split("@@");
		 jQuery('#qsh').val(pjjtids[1]);
	}
	jQuery("#qsxsxx").load("jtpjsq.do?method=qsxsxxList");
	var lddm=jQuery('#lddm').val();
	var qsh=jQuery('#qsh').val();
	if(qsh==null||qsh==""){
		jQuery("#pjjtid").val("");
		jQuery("#pjjtmc").val("");
	}else{
		jQuery("#pjjtid").val(jQuery('#lddm').val()+"@@"+jQuery('#qsh').val());
		jQuery("#pjjtmc").val(jQuery("#lddm option:selected").text()+"-"+jQuery('#qsh').val());
	}
	jQuery("#rowConut").val(jQuery("#rowConut").text());
	jQuery("#first").val("0");
	jQuery.ajaxSetup( {
		async : true
	});
}

