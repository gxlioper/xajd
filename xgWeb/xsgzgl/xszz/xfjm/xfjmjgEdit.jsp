<%@ page language="java" import="java.util.*"
         contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini"%>
    <script type="text/javascript" src="js/jquery/jquery.form.js"></script>
    <script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
    <script type="text/javascript"
            src="<%=stylePath%>js/lhgdialog/lhgdialog.min.js?self=true"></script>
    <script type="text/javascript" src="xsgzgl/xszz/bdpz/js/bdpz.js"></script>
    <script type="text/javascript" src="js/check.js"></script>
    <style type="text/css">
        .xfjm_tb tr th{
            width: 120px;
        }
        .xfjm_tb tr td{
            width: 252px;
        }
    </style>
    <script type="text/javascript">

        jQuery(function(){
            jQuery("#xh").change(function(){
                whenXhChange(jQuery(this).val());
            });
            var xh;
            if('${status}'=='update'){
                xh = '${sqinfo.xh}';
                jQuery("#xh").val(xh);
                showXsxxAjax(xh);
                jQuery(".xh").html(xh);
                jQuery("#xfjmje").val('${sqinfo.xfjmje}');
            }else{
                xh = jQuery("#xh").val();
            }
            loadJtqk(xh);
            console.log('学号:'+xh);
            //加载审核情况
            if(xh != null && xh.length > 0 && "view" == "${status}"){
                jQuery("#shlccx").load("comm_spl.do?method=lccx&sqid=${sqinfo.id}&tt="+new Date().getTime());
            }
            //加载下拉选项
            loadMkxxSelectOptions();
            //加载radio选项
           loadMkxxRadioOptions();

        });

        //展开家庭情况时需要加载的内容
        function loadJtqk(xh){
            if (jQuery.trim(xh) != ""){
                //获取家庭情况信息
                jQuery("#div_jtqk").load("xszz_jtqkdc.do?method=jtqkInfo",{xh:xh});
            }
        }
        //查看家庭情况信息
        function showJtqk(obj){
            var xh = jQuery("#xh").val();
            if (jQuery.trim(xh) != ""){
                var className = jQuery(obj).attr("class");
                var newClass = className == "up" ? "down" : "up";

                jQuery(obj).attr("class",newClass);
                jQuery("#t_jtqk").toggle();
            }else{
                showAlertDivLayer("请先选择学生！");
            }
        }

        function whenXhChange(xh){
            //验证是否重复申请
            jQuery.ajax({
                url:"xszz_new_xfjm.do?method=getknsrdInfo&xh="+xh,
                dataType: "JSON",
                type: "POST",
                success: function(res){
                    if(res.code == 0){
                        showAlertDivLayer("请先完成困难生认定");
                        jQuery("#tj").hide();
                        return false;
                    }else{
                        var data = res.data;
                        jQuery("#knsrdxn").html(data.xn);
                        jQuery("#knsrddc").html(data.dcmc);
                        jQuery("#knsrdly").html(data.ylzd5);
                        jQuery("#tj").show();
                    }
                }
            });

            loadJtqk(xh);
        }
        function showXsxxAjax(xh) {
            jQuery.ajax({
                url: "xsxx_xsgl.do?method=getXsjbxxMore&xh=" + xh,
                dataType: "JSON",
                type: "POST",
                success: function (data) {
                    //更新页面数据
                    jQuery("td").each(function (i) {
                        var td = jQuery(this);
                        var id = td.attr("class");
                        if (id == "xh") {
                            td.children("input").val(data[id]);
                        } else {
                            td.text(data[id]);
                        }
                    });

                    //触发change事件（因未知原因只能触发一次绑定函数，以下临时方案）
                    try {
                        whenXhChange(xh);
                    } catch (e) {

                    }
                }
            });
        }
        //学费减免结果保存
        function tjSave(){
            var data = {};
            var sqly = jQuery("#sqly").val();
            if(sqly == null || sqly.trim().length==0){
                showAlertDivLayer("申请理由不能为空！");
                return false;
            }
            var xh = null;
            var status = jQuery("#status").val();
            if(status == 'update'){
                xh = jQuery("#hxh").val();
            }else{
                xh = jQuery("#xh").val();
            }
            if(xh == null || xh.trim().length == 0){
                showAlertDivLayer("学生信息有误！");
                return false;
            }
            data.xh = xh;
            var id = jQuery("#id").val();
            if(id != null && id.trim().length > 0){
                data.id = id;
            }
            data.sqly = sqly;
            data.shzt='1';
            data.xfjmje = jQuery("#xfjmje").val();
            var url = "xszz_new_xfjm.do?&method=jgSave";
            jQuery.post(url,data,function(result){
                if (result.code == 1) {
                    showAlert(result.msg,{},{"clkFun":function(){
                            refershParent();
                        }});
                } else {
                    //存储失败
                    showAlert(result.msg);
                    return false;
                }
            },'json');

        }


    </script>
</head>
<body>
<input type="hidden" name="shzt" id="shzt" value="${sqinfo.shzt}" />
<input type="hidden" id="id" value="${sqinfo.id}" />
<input type="hidden" id="hxh" value="${sqinfo.xh}" />
<input type="hidden" id="status" value="${status}" />
<html:form action="/xszz_new_xfjm" method="post" styleId="xfjmForm" onsubmit="return false">
    <div style='tab;width:100%;height:480px;overflow-x:hidden;overflow-y:auto;'>
        <table width="100%" border="0" class="formlist">
            <thead>
            <tr>
                <th colspan="4">
                    <span>学生基本信息</span>
                </th>
            </tr>
            </thead>
               <%@ include file="/xsgzgl/xszz/bdpz/selectStudentForKnsrd.jsp"%>
            <thead>
            <tr>
                <th colspan="4">
                    <span>家庭情况
                            <a id="showJtqk" onclick="showJtqk(this);" class="up"
                               href="javascript:void(0);"> <font color="blue">点击展开/收起</font>
                            </a>
                           <%-- |
                            <a onclick="editJtqk();" class="btn_xg"
                               href="javascript:void(0);"> <font color="blue">编辑家庭情况</font>
                            </a>--%>
                    </span>
                </th>
            </tr>
            </thead>
            <tbody id="t_jtqk" style="display: none;">
            <tr>
                <td colspan="4">
                    <div id="div_jtqk">

                    </div>
                </td>
            </tr>
            </tbody>
            <thead>
            <tr>
                <th colspan="4">
                    <span>困难生认定结果</span>
                </th>

            </tr>
            </thead>
            <tbody class="xfjm_tb">
                <tr>
                    <th width="16%">学年</th>
                    <td id="knsrdxn">${knsrdjg.xn}</td>
                    <th width="34%">认定档次</th>
                    <td id="knsrddc">${knsrdjg.dcmc}</td>
                </tr>
                <tr>
                    <th width="16%">认定理由</th>
                    <td width="84%" colspan="3" id="knsrdly">${knsrdjg.ylzd5}</td>
                </tr>

            </tbody>
            <thead>
            <tr>
                <th colspan="4">
                    <span>学费减免信息</span>
                </th>
            </tr>
            </thead>
            <tbody>

                <tr>
                    <th><span>减免金额(元)</span></th>
                    <td colspan="3">
                        <logic:notEqual name="status" value="view">
                            <html:text property="xfjmje" styleId="xfjmje" style="width:155px;" maxlength="5" styleClass="text_nor" onblur="checkInputNum(this)"></html:text>
                        </logic:notEqual>
                        <logic:equal name="status" value="view">
                            ${sqinfo.sqly}
                        </logic:equal>
                    </td>
                </tr>
            <tr>
                <logic:notEqual name="status" value="view">
                    <th name="th_sqly">
                        <span class="red">*</span>申请理由
                        <br>
                        <span color="red">
                            &lt;限150字&gt;
                        </span>
                    </th>
                    <td colspan="3" style="word-break:break-all;">
                        <textarea oninput="chCount(this,0,150)" rows="5" style="width:98%;" id="sqly" name="sqly" onblur="checkLenBtw(this,0,30);" >${sqinfo.sqly}</textarea>
                    </td>
                </logic:notEqual>
                <logic:equal name="status" value="view">
                    <th name="th_sqly">
                        申请理由<br>
                    </th>
                    <td colspan="3" style="word-break:break-all;">${sqinfo.sqly}</td>
                </logic:equal>
            </tr>

            </tbody>
        </table>
    </div>
    <div style="height:35px;" ></div>
    <table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
        <tfoot>
        <tr>
            <td colspan="4">
                <div class="bz">
                    "<span class="red">*</span>"为必填项
                </div>
                <div class="btn">
                    <logic:notEqual name="status" value="view">

                        <button type="button" id="tj" type="button"
                                onclick="tjSave();">
                            保存
                        </button>
                    </logic:notEqual>
                    <button type="button" name="关 闭" onclick="iFClose();">
                        关 闭
                    </button>
                </div>
            </td>
        </tr>
        </tfoot>
    </table>
</html:form>
</body>
</html>

