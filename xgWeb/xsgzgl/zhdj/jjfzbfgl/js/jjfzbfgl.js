function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}


/**
 * 增加
 * @return
 */
function addBfjh(){
	url="sgygc_jjfzbfgl.do?method=add";
	showDialog("增加帮扶计划", 770, 550, url,{close:function(){
		jQuery("#dataTable").reloadGrid();
	}});
	
}

/**
 * 修改
 * @return
 */


function updateBfjh(){
    var rows = jQuery("#dataTable").getSeletRow();
    if (rows.length == 0) {
        showAlertDivLayer("请选择一个记录！");
        return false;
    }
     var  url="sgygc_jjfzbfgl.do?method=updateBfjh&bfid="+rows[0]["bfid"];
    var title = "修改帮扶计划";
    showDialog(title, 770, 552, url);
}



/**
 * 查看帮扶计划
 */
function ckBfjh(){
    var rows = jQuery("#dataTable").getSeletRow();
    if (rows.length == 0) {
        showAlertDivLayer("请选择一个记录！");
        return false;
    }
    var  url="sgygc_jjfzbfgl.do?method=ckBfjh&bfid="+rows[0]["bfid"];
    var title = "查看帮扶计划";
    showDialog(title, 770, 552, url);
}

/**
 * 维护实施情况
 */
function whSsqk(){
    var rows = jQuery("#dataTable").getSeletRow();
    if (rows.length == 0) {
        showAlertDivLayer("请选择一个记录！");
        return false;
    }
    var  url="sgygc_jjfzbfgl.do?method=whSsqk&bfid="+rows[0]["bfid"];
    var title = "维护实施情况";
    showDialog(title, 850, 650, url);
}

/**
 * 查看实施情况
 */

function ckSsqk(){
    var rows = jQuery("#dataTable").getSeletRow();
    if (rows.length == 0) {
        showAlertDivLayer("请选择一个记录！");
        return false;
    }
    var  url="sgygc_jjfzbfgl.do?method=ckSsqk&bfid="+rows[0]["bfid"];
    var title = "查看帮扶计划";
    showDialog(title, 850, 650, url);
}








/**
 * 删除
 * @return
 */
function delBfjh(){
	var row = jQuery("#dataTable").getSeletRow();
	var dels = new Array();
	if (row.length == 0){
		showAlertDivLayer("请选择您要删除的记录！");
		return false;
	}
    for (var i = 0; i < row.length; i++) {
        dels.push(row[i]["bfid"]);
    }
    var para = {
       dels:dels

    };
		showConfirmDivLayer("您确定要删除选择的记录吗？",{"okFun":function(){
			jQuery.post("sgygc_jjfzbfgl.do?method=delBfjh",para,function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});

}




function choseBfr() {
    var rows = jQuery("#dataTable").getSeletRow();

    if (rows.length == 0) {
        showAlertDivLayer("请选择一个学生！");
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
        showAlertDivLayer("请选择一个学生！");
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
        return showAlert("请将带<font color='red'>*</font>项目填写完整！");
    }
    var url = "sgygc_jjfzbfgl.do?method=saveJjfzbf";
    ajaxSubFormWithFun("JjfzbfForm", url, function(data) {
        if(data["message"]=="保存成功！"){
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
        return showAlert("请将带<font color='red'>*</font>项目填写完整！");
    }
    var url = "sgygc_jjfzbfgl.do?method=saveUpdateJjfzbf";
    ajaxSubFormWithFun("JjfzbfForm", url, function(data) {
        if(data["message"]=="保存成功！"){
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
    html+="<tbody></tbody><tr><th rowspan='4'width='10%'><font color=\"red\">*</font>"+a+"、帮扶&nbsp;&nbsp;&nbsp;&nbsp;<a href=\"javascript:void(0)\" onclick=dele1(this);>删除</a></th>";
    html+="<th width='10%'><font color=\"red\">*</font>时间</th>";
    html+="<td width='20%'><input type='text' name='sssj' id='sssj_1_"+a+"'  onfocus='showCalendar(\"sssj_1_"+a+"\",\"yyyy-MM-dd HH:mm\");' maxlength='20'></input></td>";
    html+="<th  width='10%'><font color=\"red\">*</font>地点</th>";
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
    html+="<tbody ><tr><th rowspan='4'width='10%'><font color=\"red\">*</font>"+a+"、汇报&nbsp;&nbsp;&nbsp;&nbsp;<a href=\"javascript:void(0)\" onclick=dele2(this);>删除</a></th>";
    html+="<th width='10%'><font color=\"red\">*</font>时间</th>";
    html+="<td width='20%'><input type='text' name='sssj' id='sssj_2_"+a+"' onfocus='showCalendar(\"sssj_2_"+a+"\",\"yyyy-MM-dd HH:mm\");' maxlength='20'></input></td>";
    html+="<th  width='10%'><font color=\"red\">*</font>地点</th>";
    html+="<td  width='20%'><input type='text' id='ssdd_2_"+a+"' /><input type=\"hidden\" id='sslx_2_"+a+"' value=\"2\"/></td>";
    html+="</tr>";
    html+="<tr rowspan='2'><td colspan='4' ><textarea  style=\"width: 100%;height: 85px\" rows=\"4\" cols=\"4\" id='ssnr_2_"+a+"' name=\"bfjhmb\"  onblur=\"checkLen(this,500);\" ";
    html+="maxlength=\"500\"></textarea></td>";
    html+=" </tr><tr><td colspan='4' >";
    html+="<font color='red'>*</font>汇报人：<input type='text' name='hbtqr'  id='hbtqr_"+a+"' style='width:120px;'>";
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
            return showAlert("请将带<font color='red'>*</font>项目填写完整！");
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
            return showAlert("请将带<font color='red'>*</font>项目填写完整！");
        }
        hbs.push(hb);
    }
    var para = {
        bfid:jQuery("#bfid").val(),
        hbs:hbs,
        zysss:zysss
    };
    jQuery.post("sgygc_jjfzbfgl.do?method=whSave",para,function(data){
        if(data["message"]=="保存成功！"){
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




//dcglbh,导出功能编号
var DCGLBH = "zhdj_sgygc_jjfzbfgl.do";

//自定义导出 功能
function exportConfig() {
    //DCCLBH导出功能编号,执行导出函数
    customExport(DCGLBH, exprotData);
}

//导出方法
function exprotData() {
    setSearchTj();//设置高级查询条件
    var url = "sgygc_jjfzbfgl.do?method=exportData&dcglbh=" + DCGLBH;//dcglbh,导出功能编号
    url = addSuperSearchParams(url);//设置高级查询参数
    jQuery("form").eq(0).attr("action", url);
    jQuery("form").eq(0).submit();
}















