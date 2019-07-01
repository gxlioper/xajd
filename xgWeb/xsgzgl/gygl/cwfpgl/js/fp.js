function njChange(){
    var nj = jQuery("#nj").val();
    jQuery.post("gygl_zsgl_cwgl.do?method=getXyListByNj",{nj:nj},function(data){
        jQuery("#xydm").empty();
        jQuery("#xydm").append(createOption(data));
        xyChange();
    },'json')
}
function xyChange(){
    var nj = jQuery("#nj").val();
    var xy = jQuery("#xydm").val();
    jQuery.post("gygl_zsgl_cwgl.do?method=getZyListByNjXy",{nj:nj,xy:xy},function(data){
        jQuery("#zydm").empty();
        jQuery("#zydm").append(createOption(data));
        zyChange();
    },'json')
}
function zyChange(){
    var nj = jQuery("#nj").val();
    var xy = jQuery("#xydm").val();
    var zy = jQuery("#zydm").val();
    jQuery.post("gygl_zsgl_cwgl.do?method=getBjListByNjXyZy",{nj:nj,xy:xy,zy:zy},function(data){
        jQuery("#bjdm").empty();
        jQuery("#bjdm").append(createOption(data));
        bjChange();
    },'json')
}
function bjChange(){
    var nj = jQuery("#nj").val();
    var xy = jQuery("#xydm").val();
    var zy = jQuery("#zydm").val();
    var bj = jQuery("#bjdm").val();

    getBjTj(nj,xy,zy,bj);
    getBjTjTable(nj,xy,zy,bj);
}
function getBjTj(nj,xy,zy,bj){
    jQuery.post("gygl_zsgl_cwgl.do?method=getBjFpxx1",{nj:nj,xy:xy,zy:zy,bj:bj},function(data){
        jQuery("#bjrs").html(data["rs"]);
        jQuery("#yfpcws").html(data["yfpcws"]);
        jQuery("#qss").html(data["yfpqss"]);
        jQuery("#lds").html(data["yfplds"]);
    },'json');
}
function getBjTjTable(nj,xy,zy,bj){
    jQuery.post("gygl_zsgl_cwgl.do?method=getBjFpxx2",{nj:nj,xy:xy,zy:zy,bj:bj},function(data){
        var str = "";
        jQuery("#bjfpxx").empty();
        if(data != null && data.length > 0){
            for(var i=0;i<data.length;i++){
                str+="<tr>";
                str+="<td>"+data[i]["ldmc"]+"</td>";
                str+="<td>"+data[i]["cws"]+"</td>";
                str+="<td>"+data[i]["yfpcws"]+"</td>";
                str+="<td>"+data[i]["kcws"]+"</td>";
                str+="<td>"+data[i]["bjcws"]+"</td>";
                str+="</tr>";
            }
        } else {
            str+="<tr><td>暂无分配信息</td></tr>";
        }
        jQuery("#bjfpxx").append(str);
    },'json');
}

function createQscwDiv(data,imgP){
    var str = "<div class=\"page-table checkbox\"><table><thead><tr>";
    str+="<th>寝室号</th>";
    str+="<th>床位号</th>";
    str+="<th>是否保留</th>";
    str+="<th>是否入住</th>";
    str+="<th>所属书院/学院</th>";
    str+="<th>年级</th>";
    str+="<th>专业</th>";
    str+="<th>班级</th>";
    str+="</tr>";
    str+="</thead>";
    str+="<tbody>";
    jQuery.each(data,function(key,values){
        for(var i=0;i<values.length;i++){
            str+="<tr>";
            if(i==0){
                str+="<td rowspan=\"4\" class=\"col\">";
                str+="<input type=\"checkbox\" id='checkbox-"+key+"' onchange='qsCheckBoxChange(this)' class='checkbox-frist' markLabel='"+values[0]["ch"]+"' name='qsKey' value='"+values[0]["ch"]+"@!!!"+values[0]["qsh"]+"'> " + values[0]["qsh"]+values[0]["qsxbmc"];
                str+="<label for='checkbox-"+key+"' ></label>";
                str+="</td>";
            }
            str+="<td>";
            if(values[i]["sfrz"] == "是"){
                str+="<input type=\"checkbox\" name='cwIds' value='"+values[0]["ch"]+"@!!!"+values[i]["qsh"]+"@!!!"+values[i]["cwh"]+"' disabled>"+values[i]["cwh"];
            } else {
                str+="<input type=\"checkbox\" name='cwIds' markLabel='"+values[0]["ch"]+"@!!!"+values[i]["qsh"]+"' value='"+values[0]["ch"]+"@!!!"+values[i]["qsh"]+"@!!!"+values[i]["cwh"]+"'>"+values[i]["cwh"];
            }
            str+="</td>";
            str+="<td>"+values[i]["sfblmc"]+"</td>";
            str+="<td>"+values[i]["sfrz"]+"</td>";
            str+="<td>"+nullToBlank(values[i]["qsssmc"])+"</td>";
            str+="<td>"+nullToBlank(values[i]["nj"])+"</td>";
            str+="<td>"+nullToBlank(values[i]["zymc"])+"</td>";
            str+="<td>"+nullToBlank(values[i]["bjmc"])+"</td>";
            str+="</tr>";
        }
    });
    str+="</tbody></table></div>";
    imgP.after(str);
}

function save(){
    var url = "gygl_zsgl_cwgl.do?method=cwfp&type=save";
    ajaxSubFormWithFun("demoForm", url, function(data) {
        if(data["message"]=="保存成功！"){
            showAlert(data["message"]);
            search();
        }else{
            showAlert(data["message"]);
        }
    });
}