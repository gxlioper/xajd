jQuery(function(){
    var qgxmSize = jQuery("#qgxmSize").val();
    if("0"==qgxmSize) {
        initQgmx();
    }
});

function initQgmx(){

    jQuery("#qgmxTbody").empty();

    for (var i = 0 ; i < "7"; i++){
        var nextDate = getNextDate(i);
        var rInput = jQuery("<input type='hidden' name='mxrq' value='"+nextDate+"'/>");
        var td = jQuery("<td align='center'>"+nextDate+"</td>");
        var tr = jQuery("<tr></tr>");
        td.append(rInput);
        tr.append(td);

        var qjxmCount = jQuery("#qgmxTr th").size()-1;

        for (var j = 0 ; j < qjxmCount ; j++){
            var cbox = jQuery("<input name='mxxm"+i+"' type='checkbox' value='"+jQuery("#qgmxTr th").eq(j+1).attr("xmdm")+"'/>");
            var td = jQuery("<td align='center'></td>");
            td.append(cbox);
            tr.append(td);
        }

        jQuery("#qgmxTbody").append(tr);
    }
};

function getNextDate(curDate){
    switch(curDate) {
        case 0:
            return "����һ";
            break;
        case 1:
            return "���ڶ�";
            break;
        case 2:
            return "������";
            break;
        case 3:
            return "������";
            break;
        case 4:
            return "������";
            break;
        case 5:
            return "������";
            break;
        case 6:
            return "������";
            break;
        default:
            break;
    }
}

function save(){
    var checkbox = jQuery("input:checkbox:checked:not(#qx)");
    if(checkbox.length == 0){
        showAlertDivLayer("��ѡ�����Ŀ���ʱ�䣡");
        return false;
    }
    var url = "qgzx_wycjqgzx.do?method=save";
    ajaxSubFormWithFun("demoForm",url,function(data){
        showAlertDivLayer(data["message"],{},{"clkFun":function(){
            window.location.reload()
        }});
    });
}
function qxbm(){

    var url = "qgzx_wycjqgzx.do?method=qxbm";
    ajaxSubFormWithFun("demoForm",url,function(data){
        showAlertDivLayer(data["message"],{},{"clkFun":function(){
            window.location.reload()
        }});
    });
}