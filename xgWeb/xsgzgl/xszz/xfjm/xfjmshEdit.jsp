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
            var xh = jQuery("#xh").val();
            loadJtqk(xh);
            //加载下拉选项
            loadMkxxSelectOptions();
            //加载radio选项
            loadMkxxRadioOptions();
            //加载审核情况
            jQuery("#shlccx").load("comm_spl.do?method=lccx&sqid=${sqinfo.id}&tt="+new Date().getTime());
            jQuery("#shjgSpan").load("comm_spl.do?method=shth&lcid="+jQuery("#shlc").val()+"&shid="+jQuery("#shid").val());
        });

        //展开家庭情况时需要加载的内容
        function loadJtqk(xh){
            if (jQuery.trim(xh) != ""){
                //获取家庭情况信息
                jQuery("#div_jtqk").load("xszz_jtqkdc.do?method=jtqkInfo",{xh:xh});
            }
        }
        //显示家庭情况信息
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

        //提交审核信息
        function saveShxx(){
            var data = {};
            var shyj = jQuery("#shyj").val();
            if(shyj == null || shyj.trim().length==0){
                showAlertDivLayer("审核意见不能为空！");
                return false;
            }
            var xh = jQuery("#xh").val();
            if(xh == null || xh.trim().length == 0){
                showAlertDivLayer("学生信息有误！");
                return false;
            }
            data.xh = xh;
            var id = jQuery("#id").val();
            if(id != null && id.trim().length > 0){
                data.id = id;
            }
            data.shjg = jQuery("#shjg").val();
            var thgw = jQuery("#thgw").val();
            if(thgw != null && thgw.length > 0){
                data.thgw = thgw;
            }
            var xfjmje = jQuery("#xfjmje").val();
            if(xfjmje != null && xfjmje.length > 0){
                data.xfjmje = xfjmje;
            }
            data.shyj = shyj;
            data.xtgwid = jQuery("#xtgwid").val();
            data.shlc = jQuery("#shlc").val();
            data.shid = jQuery("#shid").val();
            data.xh = jQuery("#xh").val();
            data.id = jQuery("#id").val();
            showConfirmDivLayer("您确定审核该申请吗？",{"okFun":function(){
                var url = "xszz_new_xfjm.do?&method=shSave";
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
            }});
        }
    </script>
</head>
<body>
<input type="hidden" name="shzt" id="shzt" value="${sqinfo.shzt}" />
<input type="hidden" id="xh" value="${xsjbxx.xh}" />
<input type="hidden" id="id" value="${sqinfo.id}" />
<input type="hidden" id="shid" value="${shid}" />
<input type="hidden" id="shlc" value="${sqinfo.shlc}" />
<input type="hidden" id="xtgwid" value="${xtgwid}" />

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
            <tbody class="xfjm_tb">
            <tr>
                <th>学号</th>
                <td>${xsjbxx.xh}</td>
                <th>姓名</th>
                <td>${xsjbxx.xm}</td>
            </tr>
            <tr>
                <th>年级</th>
                <td>${xsjbxx.nj}</td>
                <th>学院</th>
                <td>${xsjbxx.xymc}</td>
            </tr>
            <tr>
                <th>专业</th>
                <td>${xsjbxx.zymc}</td>
                <th>书院</th>
                <td>${xsjbxx.symc}</td>
            </tr>
            <tr>
                <th>行政班级</th>
                <td>${xsjbxx.bjmc}</td>
                <th>专业班级</th>
                <td>${xsjbxx.zybjmc}</td>
            </tr>
            <tr>
                <th>政治面貌</th>
                <td>${xsjbxx.zzmmmc}</td>
                <th>证件类型</th>
                <td>${xsjbxx.zd1}</td>
            </tr>
            <tr>
                <th>民族</th>
                <td>${xsjbxx.mzmc}</td>
                <th>证件号码</th>
                <td>${xsjbxx.sfzh}</td>
            </tr>
            <tr>
                <th>专业班级排名</th>
                <td>${pminfo.zybjpm}</td>
                <th>专业排名</th>
                <td>${pminfo.zypm}</td>
            </tr>
            </tbody>
                <%-- <%@ include file="/xsgzgl/xszz/bdpz/selectStudentForKnsrd.jsp"%>--%>
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
            <logic:equal name="sfrdkns" value="1">
                <tr>
                    <th width="16%">学年</th>
                    <td>${knsrdjg.xn}</td>
                    <th width="34%">认定档次</th>
                    <td>${knsrdjg.dcmc}</td>
                </tr>
                <tr>
                    <th width="16%">认定理由</th>
                    <td width="84%" colspan="3">${knsrdjg.ylzd5}</td>
                </tr>
            </logic:equal>
            <logic:notEqual name="sfrdkns" value="1">
                <tr>
                    <th colspan="4">本学年暂无困难生认定结果记录！</th>
                </tr>
            </logic:notEqual>
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
                <th name="th_sqly">
                    申请理由<br> &lt;限150字&gt;
                </th>
                <td colspan="3" style="word-break:break-all;">${sqinfo.sqly}</td>
            </tr>

            <logic:equal name="shzt" value="1">
                <tr>
                    <th><span>减免金额(元)</span></th>
                    <td colspan="3">
                        <span>${sqinfo.xfjmje}</span>
                    </td>
                </tr>
            </logic:equal>
            </tbody>

            <thead>
                <tr>
                    <th colspan="4">
                        <span>审核信息</span>
                    </th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td colspan="4" id="shlccx"></td>
                </tr>
                <tr>
                    <th>
                        <span class="red">*</span>审核结果
                    </th>
                    <td colspan="3" id="shjgSpan"></td>
                </tr>
                <logic:equal name="islastshl" value="true">
                <tr>
                    <th><span>减免金额(元)</span></th>
                    <td colspan="3">
                        <html:text property="xfjmje" styleId="xfjmje" style="width:155px;" maxlength="5" styleClass="text_nor" onblur="checkInputNum(this)"></html:text>
                    </td>
                </tr>
                </logic:equal>
                <tr id="shyjtr">
                    <th>
                        <span class="red">*</span>审核意见<br/>
                        <span class="red">(限150字)</span>
                    </th>
                    <td colspan="3">
                        <textarea oninput="chCount(this,0,150)" rows="5" style="width:98%;" id="shyj" name="shyj" onblur="checkLenBtw(this,0,150);" >${shyj}</textarea>
                    </td>
                </tr>
            </tbody>
                <%--<%@ include file="/xsgzgl/xszz/bdpz/mkxxUpdate.jsp"%>--%>
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
                    <button type="button" id="shSave" type="button"
                            onclick="saveShxx();">
                        保存
                    </button>
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

