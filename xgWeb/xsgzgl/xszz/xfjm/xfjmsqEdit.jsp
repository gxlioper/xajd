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
    <script type="text/javascript" src="xsgzgl/xszz/knsrd/js/knsrd.js"></script>
    <style type="text/css">
        .xfjm_tb tr th{
            width: 120px;
        }
        .xfjm_tb tr td{
            width: 252px;
        }
    </style>
    <script type="text/javascript" defer="defer">

        jQuery(function(){

            var xh = jQuery("#xh").val();
            loadJtqk(xh);

            jQuery("#xh").change(function(){
                whenXhChange(jQuery(this).val());
            });

            //加载下拉选项
            loadMkxxSelectOptions();
            //加载radio选项
            loadMkxxRadioOptions();

            var isopen = jQuery("#isopen").val();
            var shzt = jQuery("#shzt").val();
            if('3' != shzt && (isopen==null||isopen==''||"false" == isopen)){
                jQuery("#btn_submit").hide();
            }
            //加载审核情况
            if(xh != null && xh.length > 0 && "view" == "${status}"){
                jQuery("#shlccx").load("comm_spl.do?method=lccx&sqid=${sqinfo.id}&tt="+new Date().getTime());
            }
        });

        //展开家庭情况时需要加载的内容
        function loadJtqk(xh){
            if (jQuery.trim(xh) != ""){
                //获取家庭情况信息
                jQuery("#div_jtqk").load("xszz_jtqkdc.do?method=jtqkInfo",{xh:xh});
            }
        }


        //锁定【保存草稿】和【提交申请】按钮
        function lock(){
            jQuery(".btn").find("button").slice(0,2).each(function(){
                jQuery(this).attr("disabled","disabled");
                jQuery(this).css({color:"#cccccc"});
            });
        }

        //解锁【保存草稿】和【提交申请】按钮
        function unlock(){
            jQuery(".btn").find("button").slice(0,2).each(function(){
                jQuery(this).attr("disabled",false);
                jQuery(this).css({color:"#ffffff"});
            });
        }
        //提交申请信息
        function sqSave(type){
            var data = {};
            var sqly = jQuery("#sqly").val();
            if(sqly == null || sqly.trim().length==0){
                showAlertDivLayer("申请理由不能为空！");
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
            data.sqly = sqly;
            if(type=='notj'){
                data.shzt='0';
            }else{
                data.shzt='5';
            }
            var url = "xszz_new_xfjm.do?&method=sqSave";
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
<input type="hidden" id="xh" value="${xsjbxx.xh}" />
<input type="hidden" id="id" value="${sqinfo.id}" />

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
                    <span>学费减免申请</span>
                </th>
            </tr>
            </thead>

            <tbody>
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
            <logic:equal name="shzt" value="1">
                <tr>
                    <th><span>减免金额(元)</span></th>
                    <td colspan="3">
                        <span>${sqinfo.xfjmje}</span>
                    </td>
                </tr>
            </logic:equal>
            </tbody>

            <logic:equal name="status" value="view">
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
                </tbody>
            </logic:equal>
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
                    <logic:notEqual name="status" value="view">
                        <button type="button" id="btj" type="button"
                                onclick="sqSave('notj');">
                            保存草稿
                        </button>
                        <button type="button" id="tj" type="button"
                                onclick="sqSave('tj');">
                            提交申请
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

