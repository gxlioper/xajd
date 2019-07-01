var jfcygxList;
var i = 0;
var table;
function onShow(type){
    table = jQuery("#shlccx_table");
    jQuery.post("szdw_jfxx.do?method=jfcygxList",{},function(data){
        jfcygxList = data;
    },'json');
    if("update"==type){
        jQuery.post("szdw_jfxx.do?method=getCyxxForUpdate",{jgid:jQuery("#jgid").val()},function(data){
            if(data.length > 0){
                for(var a=0;a<data.length;a++){
                    addTr();
                    jQuery("input[name='jfcyxx["+a+"].xm']").val(data[a].xm);
                    jQuery("select[name='jfcyxx["+a+"].gx']").val(data[a].gx);
                    jQuery("input[name='jfcyxx["+a+"].lxdh']").val(data[a].lxdh);
                    jQuery("input[name='jfcyxx["+a+"].bz']").val(data[a].bz);
                }
            }
        },'json');
    }
}
function addTr(){
    var tr;
    tr += "<tr>";
    tr += "<td><input name='jfcyxx["+i+"].xm' style='width: 60px'></td>";
    tr += "<td>";
    tr += "<select name='jfcyxx["+i+"].gx'>";
    for(var j=0;j<jfcygxList.length;j++){
        tr += "<option value='"+jfcygxList[j].dm+"'>"+jfcygxList[j].mc+"</option>";
    }
    tr += "</select></td>";
    tr += "<td><input name='jfcyxx["+i+"].lxdh' style='width: 90px'></td>";
    tr += "<td><input name='jfcyxx["+i+"].bz'></td>";
    tr += "<td><a href='javascript:void(0);' onclick='delTr(this);return false;'>É¾³ý</a></td>";
    tr += "</tr>";
    table.append(tr);
    i++;
}

function delTr(td) {
    jQuery(td).parent().parent().remove();
    i--;
}