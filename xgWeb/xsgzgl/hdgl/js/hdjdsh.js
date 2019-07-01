function searchRs() {
	var map = getSuperSearch();
	var shzt = jQuery("#shzt").val();
	if (null!=shzt&&shzt != "") {
		map["shzt"] = shzt;
	}else{
		map["shzt"] = "dsh";
	}
	jQuery("#dataTable").reloadGrid(map);
}

function fhjdshList() {
    window.location.href = 'hdgl_hdgl_hdjdsh.do';
}

function fhjdshcyList() {
    var hdid = jQuery("#hdid").val();
    var jdid = jQuery("#jdid").val();
    var bmlx = jQuery("#bmlx").val();
    var url = "hdgl_hdjdsh.do?method=hdjdsh&hdid=" + hdid +"&bmlx="+ bmlx + "&jdid=" + jdid;
    window.location.href = url;
}

function hdjdsh() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("请选择一条您要审核的记录");
		return false;
	}
	if(rows[0]["jdlx"] != 2){
        showAlertDivLayer("当前阶段类型不属于教师阶段");
        return false;
    }

    var url = "hdgl_hdjdsh.do?method=hdjdsh&hdid=" + rows[0]["hdid"] +"&bmlx="+ rows[0]["bmlx"];
    var jdmc = rows[0]["jdmc"];
	/*if(jdmc == "专家评审"){
        jQuery.post("hdgl_hdjdsh.do?method=isZjtcy",{hdid:rows[0]["hdid"]},function (data) {
            if(data.success ==  "true"){
                document.forms[0].action=url;
                document.forms[0].submit();
            }else {
                showAlertDivLayer(data.message);
            }
        },"json");
    }else {*/
        document.forms[0].action=url;
        document.forms[0].submit();
   /* }*/
}

var DCCLBH = "hdgl_hdbl_hdblsh.do";//dcclbh,导出功能编号

//自定义导出功能
function exportConfig() {
	//DCCLBH导出功能编号,执行导出函数 
	customExport(DCCLBH, hdblshExportData);
}

//导出方法
function hdblshExportData() {
	setSearchTj();//设置高级查询条件
	var shzt = jQuery("#shzt").val();
	var url = "hdgl_hdblsh.do?method=exportData&shzt="+shzt+"&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
	url = addSuperSearchParams(url);//设置高级查询参数
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

function cyListReload(tableId,data,bmlx) {
    var sfdfhd = jQuery("#sfdfhd").val();
    var sfsldf = jQuery("#sfsldf").val();
	var html = "";
	if(bmlx == 1){//个人
		if(data != null && data.length > 0){
			for(var i=0;i<data.length;i++){
				var xh = data[i]["xh"]||"";
				var xm = data[i]["xm"]||"";
                var tjztmc = data[i]["tjztmc"]||"";
                var shztmc = data[i]["shztmc"]||"";
				// var hdid = data[i]["hdid"]||"";
				// var jdid = data[i]["jdid"]||"";
				var zf = data[i]["zf"]||"";
                var pjf = data[i]["pjf"]||"";
                var ydfzjs = data[i]["ydfzjs"]||"";
                var r = data[i]["r"]||"";
                var hdsqid = data[i]["sqid"]||"";
                var zrs = data[i]["zrs"]||"";
				html += "<tr>";
				html += "<td style='word-break:break-all;'><input name='ck' type='checkbox'></td>";
				html += "<td style='word-break:break-all;' name='xh'><a href='javascript:void(0)' onclick='jdshck(this)'>"+xh+"</a></td>";
				html += "<td style='word-break:break-all;' name='xm'>"+xm+"</td>";
                html += "<td style='word-break:break-all;' name='tjztmc'>"+tjztmc+"</td>";
                html += "<td style='word-break:break-all;' name='shztmc'>"+shztmc+"</td>";
                if(sfdfhd == "true"){
                    html += "<td style='word-break:break-all;' name='zf'>"+zf+"</td>";
                    html += "<td style='word-break:break-all;' name='pjf'>"+pjf+"</td>";
                    html += "<td style='word-break:break-all;' name='ydfzjs'>"+ydfzjs+"</td>";
                    if(sfsldf != "1"){
                        html += "<td style='word-break:break-all;' name='zrs'>"+zrs+"</td>";
                    }
                    html += "<td style='word-break:break-all;' name='r'>"+r+"</td>";
                    html += "<td style='display: none;' name='hdsqid'>"+hdsqid+"</td>";
                }

                html += "</tr>";
			}
		}else {
            if(sfdfhd == "true"){
                if(sfsldf != "1"){
                    html += "<tr><td colspan='10'>暂无数据</td></tr>";
                } else {
                    html += "<tr><td colspan='9'>暂无数据</td></tr>";
                }

            } else {
                html += "<tr><td colspan='5'>暂无数据</td></tr>";
            }
		}
	}else{//队伍
        if(data != null && data.length > 0){
            for(var i=0;i<data.length;i++){
            	var dwid = data[i]["dwid"]||"";
                var xh = data[i]["xh"]||"";
                var xm = data[i]["xm"]||"";
                var tjztmc = data[i]["tjztmc"]||"";
                var shztmc = data[i]["shztmc"]||"";
                var hdid = data[i]["hdid"]||"";
                var jdid = data[i]["jdid"]||"";
                var zf = data[i]["zf"]||"";
                var pjf = data[i]["pjf"]||"";
                var ydfzjs = data[i]["ydfzjs"]||"";
                var r = data[i]["r"]||"";
                var hdsqid = data[i]["sqid"]||"";
                var zrs = data[i]["zrs"]||"";
                html += "<tr>";
                html += "<td style='word-break:break-all;'><input name='ck' type='checkbox' value='"+dwid+"'></td>";
                html += "<td style='word-break:break-all;' name='dwid'><a href='javascript:void(0)' onclick='jdshck(this)'>"+dwid+"</a></td>";
                html += "<td style='word-break:break-all;' name='xh'>"+xh+"</td>";
                html += "<td style='word-break:break-all;' name='xm'>"+xm+"</td>";
                html += "<td style='word-break:break-all;' name='tjztmc'>"+tjztmc+"</td>";
                html += "<td style='word-break:break-all;' name='shztmc'>"+shztmc+"</td>";
                if(sfdfhd == "true"){
                    html += "<td style='word-break:break-all;' name='zf'>"+zf+"</td>";
                    html += "<td style='word-break:break-all;' name='pjf'>"+pjf+"</td>";
                    html += "<td style='word-break:break-all;' name='ydfzjs'>"+ydfzjs+"</td>";
                    if(sfsldf != "1"){
                        html += "<td style='word-break:break-all;' name='zrs'>"+zrs+"</td>";
                    }
                    html += "<td style='word-break:break-all;' name='r'>"+r+"</td>";
                    html += "<td style='display: none;' name='hdsqid'>"+hdsqid+"</td>";
                }
                html += "</tr>";
            }
        }else {
            if(sfdfhd == "true"){
                if(sfsldf != "1"){
                    html += "<tr><td colspan='11'>暂无数据</td></tr>";
                } else {
                    html += "<tr><td colspan='10'>暂无数据</td></tr>";
                }

            } else {
                html += "<tr><td colspan='6'>暂无数据</td></tr>";
            }
        }
	}

	jQuery("#"+tableId + " tbody").append(html);
}

function cyListInit(tableId,data,bmlx,pageSize) {
	cyListReload(tableId,data,bmlx);

    var totalPages;
    if(data != null && data.length > 0){
        totalPages = parseInt((data[0]["total"]-1)/pageSize) + 1;
    }else {
        totalPages = 0;
    }
    if(totalPages > 1){
        var options = {
            bootstrapMajorVersion:3,
            currentPage: 1,
            totalPages: totalPages,
            tooltipTitles: function (type, page, current) {
                switch (type) {
                    case "first":
                        return "首页";
                    case "prev":
                        return "上一页";
                    case "next":
                        return "下一页";
                    case "last":
                        return "末页";
                    case "page":
                        return "第" + page + "页";
                }
            },
            onPageChanged: function(e,oldPage,newPage){
                tableReload(tableId,pageSize,newPage);
            }
        };
        jQuery('#pageul').bootstrapPaginator(options);
    }
}

/**
 * 加载表格数据
 * @param id
 */
function tableLoad(tableId) {
    var url = "hdgl_hdjdsh.do?method=getHdjdshCyList";
    var hdid = jQuery("#hdid").val();
    var jdid = jQuery("#jdid").val();
    var bmlx = jQuery("#bmlx").val();
    var pageSize = 11;
    var param = {
        hdid:hdid,
        jdid:jdid,
		bmlx:bmlx,
        "pages.pageSize":pageSize
    };
    jQuery.post(url,param,function(data){
        cyListInit(tableId,data,bmlx,pageSize);
	},'json');
}

/**
 * 重新加载表格数据
 * @param id
 */
function tableReload(tableId,pageSize,newPage) {
    jQuery("#"+tableId + " tbody").empty();
    var url = "hdgl_hdjdsh.do?method=getHdjdshCyList";
    var hdid = jQuery("#hdid").val();
    var bmlx = jQuery("#bmlx").val();
    var jdid = jQuery("#jdid").val();
    var param = {
        hdid:hdid,
        bmlx:bmlx,
        jdid:jdid,
        "pages.pageSize":pageSize,
		"pages.currentPage":newPage
    };
    jQuery.post(url,param,function (data) {
        cyListReload(tableId,data,bmlx);
    },'json');
}

/**
 * 活动成员阶段审核
 */
function hdcyjdsh() {
    var ckTr = jQuery("input[name='ck']:checkbox:checked");
    if (jQuery(ckTr).length != 1) {
        showAlertDivLayer("请选择一条您要审核的记录");
        return false;
    }

    var tjztmc = ckTr.parents("tr").children("td[name='tjztmc']").text();
    if(tjztmc == "未提交"){
        showAlertDivLayer("只能审核当前阶段已提交的记录");
        return false;
    }

    var shztmc = ckTr.parents("tr").children("td[name='shztmc']").text();
    if(shztmc != "审核中"&&shztmc !=""){
        showAlertDivLayer("只能选择审核中或无审核状态的记录");
        return false;
    }


    var hdid = jQuery("#hdid").val();
    var bmlx = jQuery("#bmlx").val();
    var xh = ckTr.parents("tr").children("td[name='xh']").text();
    var dwid = ckTr.parents("tr").children("td[name='dwid']").text();
    jQuery("#hdxxForm").append("<input type='hidden' name='dwid' value='"+dwid+"' />");
    jQuery("#hdxxForm").append("<input type='hidden' name='xh' value='"+xh+"' />");
    var jdid = jQuery("#jdid").val();

    var url = "hdgl_hdjdsh.do?method=hdcyjdshPage";
    
//    var url = "hdgl_hdjdsh.do?method=hdcyjdshPage&hdid="
//        + hdid + "&bmlx=" + bmlx + "&xh=" + xh + "&dwid=" + dwid + "&jdid=" + jdid;
    if(jQuery("#sfdfhd").val() == "true"){
        if(jQuery("#sfsldf").val() == "1"){
            //禁止重复打分
            var hdsqid = ckTr.parents("tr").children("td[name='hdsqid']").text();
            jQuery.post("hdgl_hdjdsh.do?method=sfwdf",{hdsqid:hdsqid,jdid:jdid},function(data){
                if("true" == data["message"]){
                    document.forms[0].action=url;
                    document.forms[0].submit();
                } else {
                    showAlertDivLayer("您已打分，请勿重复操作！");
                    return false;
                }
            },'json');
        } else {
            document.forms[0].action=url;
            document.forms[0].submit();
        }
    } else {
        document.forms[0].action=url;
        document.forms[0].submit();
    }

}

/**
 * 阶段审核信息查看
 */
function jdshck(obj) {
    var hdid = jQuery("#hdid").val();
    var bmlx = jQuery("#bmlx").val();
    var jdid = jQuery("#jdid").val();
    // var xh = jQuery(obj).text();
    var xh = jQuery(obj).parents("tr").children("td[name='xh']").text();
    var dwid = jQuery(obj).parents("tr").children("td[name='dwid']").text();

    var url = "hdgl_hdjdsh.do?method=hdcyjdshView&hdid="
        + hdid + "&bmlx=" + bmlx + "&xh=" + xh + "&dwid=" + dwid + "&jdid=" + jdid;
    document.forms[0].action=url;
    document.forms[0].submit();
}

/**
 * 打分
 * @returns {boolean}
 */
function saveDf(){
    // var sqid = jQuery("#sqid").val();
    var hdid = jQuery("#hdid").val();
    var jdid = jQuery("#jdid").val();
    var bmlx = jQuery("#bmlx").val();
    var xh  = jQuery("#xh").val();
    var dwid = jQuery("#dwid").val();
    var df = jQuery("#df").val();
    if(df == null||df == ""){
        showAlertDivLayer("请给该学生打分");
        return false;
    }

    var url = "hdgl_hdjdsh.do?method=saveDf";
    jQuery.post(url,
        {hdid:hdid,jdid:jdid,df:df,bmlx:bmlx,xh:xh,dwid:dwid},
        function (data) {
            if(data.success == 'true'){
                showAlert(data.message,{},{"clkFun":function(){
                    var url = "hdgl_hdjdsh.do?method=hdjdsh&hdid=" + hdid +"&bmlx="+ bmlx + "&jdid=" + jdid;
                    window.location.href = url;
                }});
            }else {
                showAlertDivLayer(data.message);
            }
        },'json')
}
/**
 * 阶段审核通过
 */
function jdshtg() {
    var hdid = jQuery("#hdid").val();
    var jdid = jQuery("#jdid").val();
    var bmlx = jQuery("#bmlx").val();
    var xh  = jQuery("#xh").val();
    var dwid = jQuery("#dwid").val();
    var shyj = jQuery("#shyj").val();
    var psfs = jQuery("#psfs").val();
    var dqjdmc = jQuery("#dqjdmc").val();

    var jxid = jQuery("#jxid").val();
    var xf;
    var df;
    var nextjdid;
    /*if(dqjdmc == '专家评审'){
        if(psfs == null||psfs == ""){
            showAlertDivLayer("请填写评审分数");
            return false;
        }
    }*/

    /*if(jQuery("#jxid").length>0){
        jxid = jQuery("#jxid").val();
        if(jxid == null||jxid == ""){
            showAlertDivLayer("请选择奖项");
            return false;
        }
    }*/
    if(jQuery("#df").length>0){
        df = jQuery("#df").val();
        if(df == null||df == ""){
            showAlertDivLayer("请给该学生打分");
            return false;
        }
    }
    if(jQuery("#xf").length>0){
        xf = jQuery("#xf").val();
        if(xf == null||xf == ""){
            showAlertDivLayer("请填写学分");
            return false;
        }
    }

    if(jQuery("#nextjdid").length > 0){
        nextjdid = jQuery("#nextjdid").val();
        if(nextjdid == null || nextjdid == ""){
            showAlertDivLayer("请选择阶段");
            return false;
        }
    }
    if(shyj == null||shyj == ""){
        showAlertDivLayer("请填写审核意见");
        return false;
    }

    var url = "hdgl_hdjdsh.do?method=jdshtg";
    jQuery.post(url,
        {hdid:hdid,jdid:jdid,bmlx:bmlx,xh:xh,dwid:dwid,shyj:shyj,psfs:psfs,jxid:jxid,xf:xf,df:df,nextjdid:nextjdid},
        function (data) {
            if(data.success == 'true'){
                showAlert(data.message,{},{"clkFun":function(){
                    var url = "hdgl_hdjdsh.do?method=hdjdsh&hdid=" + hdid +"&bmlx="+ bmlx + "&jdid=" + jdid;
                    window.location.href = url;
                }});
            }else {
                showAlertDivLayer(data.message);
            }
    },'json')
}

/**
 * 阶段审核退回3 不通过2
 */
function jdshth(shzt) {
    var hdid = jQuery("#hdid").val();
    var jdid = jQuery("#jdid").val();
    var bmlx = jQuery("#bmlx").val();
    var xh  = jQuery("#xh").val();
    var dwid = jQuery("#dwid").val();
    var shyj = jQuery("#shyj").val();
	
	var jxid = jQuery("#jxid").val();

    if(shyj == null||shyj == ""){
        showAlertDivLayer("请填写审核意见");
        return false;
    }

    var url = "hdgl_hdjdsh.do?method=jdshth";
    jQuery.post(url,{hdid:hdid,jdid:jdid,bmlx:bmlx,xh:xh,dwid:dwid,shyj:shyj,shzt:shzt,jxid:jxid},
        function (data) {
            showAlertDivLayer(data.message,{},{"clkFun":function(){
                var url = "hdgl_hdjdsh.do?method=hdjdsh&hdid=" + hdid +"&bmlx="+ bmlx + "&jdid=" + jdid;
                window.location.href = url;
        }});
    },'json')
}

/**
 * 切换待审核阶段
 * @param obj
 */
function changeDcyjd(obj) {
    jQuery("#jdid").val(jQuery(obj).attr("jdid"));
    jQuery("span.number.xzjd").removeClass("xzjd");
    jQuery(obj).parents("div").parents("div").children("span.number").addClass("xzjd");

    //重新加载成员列表
    tableReload("hdcyTable",11,1);
}

//全选
function selectAll1(obj){
    var checkboxs = jQuery("#ryTbody input[type='checkbox']");
    if(obj.checked){
        jQuery(checkboxs).each(function(){
            if(jQuery(this).prop("checked") == false){
                jQuery(this).attr("checked",true);
            }
        })

    }else{
        jQuery(checkboxs).each(function(i,n){
            if(jQuery(n).prop("checked") == true){
                jQuery(n).attr("checked",false);
            }
        })
    }
}