function xqChange(){
    var v = jQuery("#xqdm").val();
    jQuery.post("gygl_sfqscj_wh.do?method=getLddxxList",{xqdm:v},function(data){
        jQuery("#lddm").empty();
        var option;
        for(var i=0;i<data.length;i++){
            option += "<option value='"+data[i]["lddm"]+"'>"+data[i]["ldmc"]+"</option>";
        }
        jQuery("#lddm").append(option);

        ldChange();
    },'json');
}
function ldChange(){
    var v = jQuery("#lddm").val();
    jQuery.post("gygl_sfqscj_wh.do?method=getQsxxList",{lddm:v},function(data){
        jQuery("#qsh").empty();
        var option = "";
        if(data.length > 0){
            for(var i=0;i<data.length;i++){
                option += "<option value='"+data[i]["qsh"]+"'>"+data[i]["qsh"]+"</option>";
            }
            jQuery("#sssyTd").html(data[0].symc);
        }
        jQuery("#qsh").append(option);
        qshChange();
    },'json');
}
function qshChange(){
    var v = jQuery("#lddm").val();
    var v1 = jQuery("#qsh").val();
    jQuery.post("gygl_sfqscj_wh.do?method=getQshXsxxList",{lddm:v,qsh:v1},function(data){
        jQuery("#xmTr").empty();
        jQuery("#xhTr").empty();
        var xmStr;
        var xhStr;
        if(data.length > 0){
            xmStr +="<th>����</th>";
            xhStr +="<th>ѧ��</th>"
            for(var i=0;i<data.length;i++){
                xmStr += "<td>"+data[i]["xm"]+"</td>";
                xhStr += "<td><input type='hidden' name='qscyxx["+i+"].xh' value='"+data[i]["xh"]+"'>"+data[i]["xh"]+"</td>";
            }
            jQuery("#sssyTd").html(data[0].sssy);
        } else {
            xmStr = "<td>������סѧ����Ϣ</td>";
            // xhStr = "<td>������סѧ����Ϣ</td>";
        }
        jQuery("#xmTr").append(xmStr);
        jQuery("#xhTr").append(xhStr);
    },'json');
}
function nullToBlank(val){
    return val == null ? "":val;
}