function xqChange(){
    var v = jQuery("#xqdm").val();
    jQuery.post("gygl_gybz_wh.do?method=getLddxxList",{xqdm:v},function(data){
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
    jQuery.post("gygl_gybz_wh.do?method=getQsxxList",{lddm:v},function(data){
        jQuery("#qsh").empty();
        var option;
        for(var i=0;i<data.length;i++){
            option += "<option value='"+data[i]["qsh"]+"'>"+data[i]["qsh"]+"</option>";
        }
        jQuery("#qsh").append(option);

        qshChange();
    },'json');
}
function qshChange(){
    var v = jQuery("#lddm").val();
    var v1 = jQuery("#qsh").val();
    jQuery.post("gygl_gybz_wh.do?method=getQshXsxxList",{lddm:v,qsh:v1},function(data){
        jQuery("#shlccx_table").empty();
        var str;
        if(data.length > 0){
            str +="<tr><th>书院</th><th>姓名</th><th>行政班级</th><th>学号</th><th>联系电话</th><th>网址</th><th>电费分摊比例</th></tr>";
            for(var i=0;i<data.length;i++){
                str +="<tr>"
                str += "<td>"+data[i]["symc"]+"</td>";
                str += "<td>"+data[i]["xm"]+"</td>";
                str += "<td><input type='hidden' name='jfxx["+i+"].cwh' value='"+data[i]["cwh"]+"'>"+data[i]["bjmc"]+"</td>";
                str += "<td><input type='hidden' name='jfxx["+i+"].xh' value='"+data[i]["xh"]+"'>"+data[i]["xh"]+"</td>";
                str += "<td>"+nullToBlank(data[i]["lxdh"])+"</td>";
                str += "<td><input name='jfxx["+i+"].wz' style='width: 80px;'></td>";
                str += "<td><input name='jfxx["+i+"].ftbl' style='width: 60px;'></td>";
                str +="</tr>";
            }
            jQuery("#rs").val(data.length);
        } else {
            str += "<tr><td>暂无入住学生信息</td></tr>";
            jQuery("#rs").val("");
        }
        jQuery("#shlccx_table").append(str);
    },'json');
}
function nullToBlank(val){
    return val == null ? "":val;
}