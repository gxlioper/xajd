var zclxOption="";
var zcxxOption="";
var i=0;
var table;
var timer;
function onShow(type){
    table = jQuery("#shlccx_table");
    //�������ʹ�����ʲ���Ϣ�����棬����ÿ��׷�������������ݣ�
    getZclxList();
    if("add" == type){
        xqChange();
    }

    if("update"==type){
        jQuery.post("gygl_zcgl_zcfpgl.do?method=getDataForUpdate",{lddm:jQuery("#lddm").val(),qsh:jQuery("#qsh").val()},function(data){
            if(data.length > 0){
                mark(data);
            }
        },'json');
    }
}
function mark(data){
    //ִ�е��˶δ���ʱ������zclxOption ��zcxxOption������δ��ɣ����Զ�ʱ��ѭ���ж�
    if(zclxOption!="" && zcxxOption!=""){
        for(var a=0;a<data.length;a++){
            addTr();
            jQuery("#lxdm"+a).val(data[a]["lxdm"]);
            jQuery("#zcid"+a).val(data[a]["zcid"]);
            jQuery("#sl"+a).val(data[a]["sl"]);
            jQuery("#bz"+a).val(data[a]["bz"]);
        }
        clearTimeout(timer);
    } else {
        timer = setTimeout(function(){mark(data)},200);
    }
}
//��ȡ�ʲ�����
function getZclxList(){
    jQuery.post("gygl_zcgl_zcfpgl.do?method=getZclxList",{},function(data){
        if(data != null){
            for(var i=0;i<data.length;i++){
                zclxOption += "<option value='"+data[i]["dm"]+"'>"+data[i]["mc"]+"</option>";
            }
            getZcxxList(data[0]["dm"]);
        }
    },'json');
}
function zclxChange(tar){
    var lxdm = jQuery(tar).val();
    jQuery.post("gygl_zcgl_zcfpgl.do?method=getZcxxList",{lxdm:lxdm},function(data){
        var nextTd = jQuery(tar).parent().parent().children("td").eq(1);
        var zcxxSelect = nextTd.children("select").eq(0);
        zcxxSelect.empty();
        if(data != null){
            var option="";
            for(var i=0;i<data.length;i++){
                option += "<option value='"+data[i]["id"]+"'>"+data[i]["mc"]+"</option>";
            }
            zcxxSelect.append(option);
        }
    },'json');
}
//��ȡ�ʲ���Ϣ
function getZcxxList(zclxdm){
    jQuery.post("gygl_zcgl_zcfpgl.do?method=getZcxxList",{lxdm:zclxdm},function(data){
        if(data != null){
            for(var i=0;i<data.length;i++){
                zcxxOption += "<option value='"+data[i]["id"]+"'>"+data[i]["mc"]+"</option>";
            }
        }
    },'json');
}
function addTr(){
    var tr;
    tr+="<tr>";
    tr+="<td><select name='fpFromList["+i+"].lxdm' id='lxdm"+i+"' onchange='zclxChange(this)'>"+zclxOption+"</select></td>";
    tr+="<td><select name='fpFromList["+i+"].zcid' id='zcid"+i+"'>"+zcxxOption+"</select></td>";
    tr+="<td><input name='fpFromList["+i+"].sl' id='sl"+i+"' onblur='checkInputData(this)'></td>";
    tr+="<td><input name='fpFromList["+i+"].bz' id='bz"+i+"'></td>";
    tr+="<td><a href='#' onclick='delTr(this)' class='name'>ɾ��</a></td>"
    table.append(tr);
    i++;
}
function delTr(td) {
    var tr = jQuery(td).parent().parent();
    tr.remove();
    i--;
}
function save(method){
    var url = "gygl_zcgl_zcfpgl.do?method="+method+"&type=save";
    ajaxSubFormWithFun("demoForm", url, function(data) {
        if(data["message"]=="����ɹ���"){
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
function xqChange(){
    var v = jQuery("#xqdm").val();
    jQuery.post("gygl_zcgl_zcfpgl.do?method=getLddxxList",{xqdm:v},function(data){
        jQuery("#lddm").empty();
        var option = "<option value=''>---��ѡ��---</option>";
        for(var i=0;i<data.length;i++){
            option += "<option value='"+data[i]["lddm"]+"'>"+data[i]["ldmc"]+"</option>";
        }
        jQuery("#lddm").append(option);

        ldChange();
    },'json');
}
function ldChange(){
    var v = jQuery("#lddm").val();
    jQuery.post("gygl_zcgl_zcfpgl.do?method=getLddcs",{lddm:v},function(data){
        jQuery("#ch").empty();
        var option = "<option value=''>---��ѡ��---</option>";
        for(var i=0;i<data.length;i++){
            option += "<option value='"+data[i]+"'>"+data[i]+"</option>";
        }
        jQuery("#ch").append(option);

        lcChange();
    },'json');
}
function lcChange(){
    var v = jQuery("#lddm").val();
    var ch = jQuery("#ch").val();
    jQuery.post("gygl_zcgl_zcfpgl.do?method=getQsxxList",{lddm:v,ch:ch},function(data){
        jQuery("#qsh").empty();
        var option = "<option value=''>---��ѡ��---</option>";
        for(var i=0;i<data.length;i++){
            option += "<option value='"+data[i]["qsh"]+"'>"+data[i]["qsh"]+"</option>";
        }
        jQuery("#qsh").append(option);

    },'json');
}
/*
function qsChange(){
    var v = jQuery("#qsh").val();
    if(v != "" && typeof v != "undefined"){
        jQuery("#qszcsTr").attr("style","display:none");
    } else {
        jQuery("#qszcsTr").removeAttr("style");
    }
}*/
