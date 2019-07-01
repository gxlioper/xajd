<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini"%>
    <script type='text/javascript' src="js/comm/message.js"></script>
    <script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
    <script type='text/javascript' src='/xgxt/dwr/util.js'></script>
    <script type="text/javascript" src="js/calendar/calendar.js"></script>
    <script type='text/javascript' src="js/uicomm.js"></script>
    <script type='text/javascript' src="js/String.js"></script>
    <script type='text/javascript' src="js/xgutil.js"></script>
    <script type="text/javascript" src="js/jquery/jquery.form.js"></script>
    <script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
    <script language="javascript" src="js/check.js"></script>
    <script language="javascript">

        function yrdwDivSave(){

            if(!checkNotNull("mm-qrmm")){
                showAlert("请将带<font color='red'>*</font>的项目填写完整!");
                return false;
            }
            var s = jQuery("#mm").val();

            if(checkPsw(s)){
                var qrmm = jQuery("#qrmm").val();
                if(qrmm != s){
                    showAlert("密码不一致！");
                    return false;
                }
                var url="qgzx_jcdmwh_ajax.do?method=mmcsh&type=update";
                ajaxSubFormWithFun("qgzxJcdmwhForm",url,function(data){
                    if (data["message"] == "保存成功！") {
                        showAlert(data["message"], {}, {
                            "clkFun" : function() {
                                if (parent.window) {
                                    refershParent();
                                }
                            }
                        });
                    } else {
                        showAlert(data["message"]);
                    }
                });
            }
        }
    </script>
</head>
<body >

<html:form styleId="qgzxJcdmwhForm" action="/qgzx_jcdmwh_ajax" method="post" onsubmit="return false;">
    <input type="hidden" value="${id}" name="id">
    <table align="center" class="formlist">
        <thead>
        <tr>
            <th colspan="4" style="width: 25%">
                <span>密码初始化</span>
            </th>
        </tr>
        </thead>
        <tbody>
        <tr >
            <th>
                <span class="red">*</span>新密码
            </th>
            <td>
                <input name="mm" id="mm">
            </td>
        </tr>
        <tr>
            <th>
                <span class="red">*</span>确认密码
            </th>
            <td>
                <input name="qrmm" id="qrmm">
            </td>
        </tr>
        </tbody>
        <tfoot>
        <tr>
            <td colspan="4">
                <div class="bz">
                    "<span class="red">*</span>"为必填项</br><span class="red">新密码长度为6~20位且不可为连续数字或相同字符</span>
                </div>
                <div class="btn">
                    <button type="button" name="保存" onclick="yrdwDivSave();return false;">
                        保 存
                    </button>
                    <button type="button" name="关闭" onclick="Close();return false;">
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
