function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}


/**
 * ����
 * @return
 */
function addBfjh(){
	url="sgygc_jjfzbfgl.do?method=add";
	showDialog("���Ӱ���ƻ�", 770, 550, url,{close:function(){
		jQuery("#dataTable").reloadGrid();
	}});
	
}

/**
 * �޸�
 * @return
 */


function updateBfjh(){
    var rows = jQuery("#dataTable").getSeletRow();
    if (rows.length == 0) {
        showAlertDivLayer("��ѡ��һ����¼��");
        return false;
    }
     var  url="sgygc_jjfzbfgl.do?method=updateBfjh&bfid="+rows[0]["bfid"];
    var title = "�޸İ���ƻ�";
    showDialog(title, 770, 552, url);
}



/**
 * �鿴����ƻ�
 */
function ckBfjh(){
    var rows = jQuery("#dataTable").getSeletRow();
    if (rows.length == 0) {
        showAlertDivLayer("��ѡ��һ����¼��");
        return false;
    }
    var  url="sgygc_jjfzbfgl.do?method=ckBfjh&bfid="+rows[0]["bfid"];
    var title = "�鿴����ƻ�";
    showDialog(title, 770, 552, url);
}

/**
 * ά��ʵʩ���
 */
function whSsqk(){
    var rows = jQuery("#dataTable").getSeletRow();
    if (rows.length == 0) {
        showAlertDivLayer("��ѡ��һ����¼��");
        return false;
    }
    var  url="sgygc_jjfzbfgl.do?method=whSsqk&bfid="+rows[0]["bfid"];
    var title = "ά��ʵʩ���";
    showDialog(title, 850, 650, url);
}

/**
 * �鿴ʵʩ���
 */

function ckSsqk(){
    var rows = jQuery("#dataTable").getSeletRow();
    if (rows.length == 0) {
        showAlertDivLayer("��ѡ��һ����¼��");
        return false;
    }
    var  url="sgygc_jjfzbfgl.do?method=ckSsqk&bfid="+rows[0]["bfid"];
    var title = "�鿴����ƻ�";
    showDialog(title, 850, 650, url);
}








/**
 * ɾ��
 * @return
 */
function delBfjh(){
	var row = jQuery("#dataTable").getSeletRow();
	var dels = new Array();
	if (row.length == 0){
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
		return false;
	}
    for (var i = 0; i < row.length; i++) {
        dels.push(row[i]["bfid"]);
    }
    var para = {
       dels:dels

    };
		showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��",{"okFun":function(){
			jQuery.post("sgygc_jjfzbfgl.do?method=delBfjh",para,function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});

}




function choseBfr() {
    var rows = jQuery("#dataTable").getSeletRow();

    if (rows.length == 0) {
        showAlertDivLayer("��ѡ��һ��ѧ����");
        return false;
    }
    var api = frameElement.api;
    var parentsW = api.get('parentDialog');
    parentsW.jQuery("#bfdxxh").val(rows[0]['xh']);
    parentsW.jQuery("#bfrxm").html(rows[0]['xm']);
    parentsW.jQuery("#bfrxb").html(rows[0]['xb']);
    parentsW.jQuery("#bfrcsrq").html(rows[0]['csrq']);
    parentsW.jQuery("#bfrlxdh").html(rows[0]['lxdh']);
    parentsW.jQuery("#bfrsjhm").html(rows[0]['sjhm']);
    parentsW.jQuery("#bfrqqhm").html(rows[0]['qqhm']);
    parentsW.jQuery("#bfrssld").html(rows[0]['ssld']);
    parentsW.jQuery("#bfrssbh").html(rows[0]['ssbh']);
    parentsW.jQuery("#bfrbzr").html(rows[0]['bzrxm']);
    closeDialog();
}



function choseJjfz() {
    var rows = jQuery("#dataTable").getSeletRow();

    if (rows.length == 0) {
        showAlertDivLayer("��ѡ��һ��ѧ����");
        return false;
    }
    var api = frameElement.api;
    var parentsW = api.get('parentDialog');
    parentsW.jQuery("#rdjjfzxh").val(rows[0]['xh']);
    parentsW.jQuery("#jjfzxm").html(rows[0]['xm']);
    parentsW.jQuery("#jjfzxb").html(rows[0]['xb']);
    parentsW.jQuery("#jjfzlxdh").html(rows[0]['lxdh']);
    parentsW.jQuery("#jjfzsjhm").html(rows[0]['sjhm']);
    parentsW.jQuery("#jjfzssld").html(rows[0]['ssld']);
    parentsW.jQuery("#jjfzssbh").html(rows[0]['ssbh']);
    closeDialog();
}


function saveJjfzbf(){
    var ids = "rdjjfzxh"+"-"+"bfdxxh"+"-"+"bfjhnr"+"-"+"bfjhmb"+"-"+"bfjhcs";
    if(!checkNotNull(ids)){
        return showAlert("�뽫��<font color='red'>*</font>��Ŀ��д������");
    }
    var url = "sgygc_jjfzbfgl.do?method=saveJjfzbf";
    ajaxSubFormWithFun("JjfzbfForm", url, function(data) {
        if(data["message"]=="����ɹ���"){
            showAlert(data["message"],{},{"clkFun":function(){
                var api = frameElement.api,W = api.opener;
                W.jQuery("#dataTable").reloadGrid();
                closeDialog();
            }});
        }else{
            showAlert(data["message"]);
        }
    });
}






function saveUpdateJjfzbf(){
    var ids = "rdjjfzxh"+"-"+"bfdxxh"+"-"+"bfjhnr"+"-"+"bfjhmb"+"-"+"bfjhcs";
    if(!checkNotNull(ids)){
        return showAlert("�뽫��<font color='red'>*</font>��Ŀ��д������");
    }
    var url = "sgygc_jjfzbfgl.do?method=saveUpdateJjfzbf";
    ajaxSubFormWithFun("JjfzbfForm", url, function(data) {
        if(data["message"]=="����ɹ���"){
            showAlert(data["message"],{},{"clkFun":function(){
                var api = frameElement.api,W = api.opener;
                W.jQuery("#dataTable").reloadGrid();
                closeDialog();
            }});
        }else{
            showAlert(data["message"]);
        }
    });
}






function addBfRow() {
    var zyssNum = jQuery("#zyssNum").val();
    var a =parseInt(zyssNum) +1;
    html="";
    html+="<tbody></tbody><tr><th rowspan='4'width='10%'><font color=\"red\">*</font>"+a+"�����&nbsp;&nbsp;&nbsp;&nbsp;<a href=\"javascript:void(0)\" onclick=dele1(this);>ɾ��</a></th>";
    html+="<th width='10%'><font color=\"red\">*</font>ʱ��</th>";
    html+="<td width='20%'><input type='text' name='sssj' id='sssj_1_"+a+"'  onfocus='showCalendar(\"sssj_1_"+a+"\",\"yyyy-MM-dd HH:mm\");' maxlength='20'></input></td>";
    html+="<th  width='10%'><font color=\"red\">*</font>�ص�</th>";
    html+="<td  width='20%'><input type='text' id='ssdd_1_"+a+"' /><input type=\"hidden\" id='sslx_1_"+a+"' value=\"1\"/></td>";
    html+="</tr>";
    html+="<tr><td colspan='4' rowspan='3'><textarea  style=\"width: 100%;height: 100px\" rows=\"4\" cols=\"4\" id='ssnr_1_"+a+"' name=\"bfjhmb\"  onblur=\"checkLen(this,500);\" ";
    html+="maxlength=\"500\"></textarea></td>";
    html+=" </tr></tbody>";
    jQuery("#zyssNum").val(a);
    jQuery("#bfss").append(html);

}

function addHbRow() {
    var hbNum = jQuery("#hbNum").val();
    var a =parseInt(hbNum) +1;
    html="";
    html+="<tbody ><tr><th rowspan='4'width='10%'><font color=\"red\">*</font>"+a+"���㱨&nbsp;&nbsp;&nbsp;&nbsp;<a href=\"javascript:void(0)\" onclick=dele2(this);>ɾ��</a></th>";
    html+="<th width='10%'><font color=\"red\">*</font>ʱ��</th>";
    html+="<td width='20%'><input type='text' name='sssj' id='sssj_2_"+a+"' onfocus='showCalendar(\"sssj_2_"+a+"\",\"yyyy-MM-dd HH:mm\");' maxlength='20'></input></td>";
    html+="<th  width='10%'><font color=\"red\">*</font>�ص�</th>";
    html+="<td  width='20%'><input type='text' id='ssdd_2_"+a+"' /><input type=\"hidden\" id='sslx_2_"+a+"' value=\"2\"/></td>";
    html+="</tr>";
    html+="<tr rowspan='2'><td colspan='4' ><textarea  style=\"width: 100%;height: 85px\" rows=\"4\" cols=\"4\" id='ssnr_2_"+a+"' name=\"bfjhmb\"  onblur=\"checkLen(this,500);\" ";
    html+="maxlength=\"500\"></textarea></td>";
    html+=" </tr><tr><td colspan='4' >";
    html+="<font color='red'>*</font>�㱨�ˣ�<input type='text' name='hbtqr'  id='hbtqr_"+a+"' style='width:120px;'>";
    html+="</td></tr></tbody >";
    jQuery("#hbNum").val(a);
    jQuery("#hbss").append(html);

}

function dele1(obj) {
    jQuery(obj).parent().parent().parent().empty();
    var zyssNum = jQuery("#zyssNum").val();
    var a =parseInt(zyssNum) -1;
    jQuery("#zyssNum").val(a);

}

function dele2(obj) {
    jQuery(obj).parent().parent().parent().empty();
    var hbNum = jQuery("#hbNum").val();
    var a =parseInt(hbNum) -1;
    jQuery("#hbNum").val(a);
}


function whSsqkSave() {
    var ids = "";
    var a = jQuery("#zyssNum").val();
    var zyssNum =parseInt(a);
    var zysss = new Array();
    for (var i =1 ;i<=zyssNum ; i++)
    {
        if(jQuery("#sssj_1_"+i+"").val()==null)
        {
            zyssNum=zyssNum+1;
            continue;
        }
        var sssj = jQuery("#sssj_1_"+i+"").val();
        var ssdd = jQuery("#ssdd_1_"+i+"").val();
        var ssnr = jQuery("#ssnr_1_"+i+"").val();
        var zyss = sssj+","+ssdd+","+ssnr;
        if(ids==""){
        ids=ids+"sssj_1_"+i+""+"-"+"ssdd_1_"+i+""+"-"+"ssnr_1_"+i+"";
        }
        else{
            ids=ids+"-"+"sssj_1_"+i+""+"-"+"ssdd_1_"+i+""+"-"+"ssnr_1_"+i+"";
        }
        if(!checkNotNull(ids)){
            return showAlert("�뽫��<font color='red'>*</font>��Ŀ��д������");
        }
        zysss.push(zyss);
    }
    var b = jQuery("#hbNum").val();
    var hbNum =parseInt(b);
    var hbs = new Array();
    for (var i =1 ;i<=hbNum ; i++)
    {
        if(jQuery("#sssj_2_"+i+"").val()==null)
        {
            hbNum=hbNum+1;
            continue;
        }
        var sssj = jQuery("#sssj_2_"+i+"").val();
        var ssdd = jQuery("#ssdd_2_"+i+"").val();
        var ssnr = jQuery("#ssnr_2_"+i+"").val();
        var hbtqr = jQuery("#hbtqr_"+i+"").val();
        var hb = sssj+","+ssdd+","+ssnr+","+hbtqr;
        if(ids==""){
            ids=ids+"sssj_2_"+i+""+"-"+"ssdd_2_"+i+""+"-"+"ssnr_2_"+i+""+"-"+"hbtqr_"+i+"";
        }
        else{
            ids=ids+"-"+"sssj_2_"+i+""+"-"+"ssdd_2_"+i+""+"-"+"ssnr_2_"+i+""+"-"+"hbtqr_"+i+"";
        }
        if(!checkNotNull(ids)){
            return showAlert("�뽫��<font color='red'>*</font>��Ŀ��д������");
        }
        hbs.push(hb);
    }
    var para = {
        bfid:jQuery("#bfid").val(),
        hbs:hbs,
        zysss:zysss
    };
    jQuery.post("sgygc_jjfzbfgl.do?method=whSave",para,function(data){
        if(data["message"]=="����ɹ���"){
            showAlert(data["message"],{},{"clkFun":function(){
                if (parent.window){
                    var api = frameElement.api,W = api.opener;
                    W.jQuery("#dataTable").reloadGrid();
                    closeDialog();
                }
            }});
        }else{
            showAlert(data["message"]);
        }
    },'json');
}




//dcglbh,�������ܱ��
var DCGLBH = "zhdj_sgygc_jjfzbfgl.do";

//�Զ��嵼�� ����
function exportConfig() {
    //DCCLBH�������ܱ��,ִ�е�������
    customExport(DCGLBH, exprotData);
}

//��������
function exprotData() {
    setSearchTj();//���ø߼���ѯ����
    var url = "sgygc_jjfzbfgl.do?method=exportData&dcglbh=" + DCGLBH;//dcglbh,�������ܱ��
    url = addSuperSearchParams(url);//���ø߼���ѯ����
    jQuery("form").eq(0).attr("action", url);
    jQuery("form").eq(0).submit();
}















