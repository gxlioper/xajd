<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini"%>
    <script type="text/javascript" src="js/jquery/jquery.form.js"></script>
    <script type="text/javascript" src="js/check.js"></script>
    <script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
    <script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
    <script type="text/javascript" src="js/calendar/calendar.js"></script>
    <script type="text/javascript" src="js/jquery/plugins/upload/ajaxfileupload.js"></script>
    <script type="text/javascript" src="xsgzgl/comm/fileUpload/jmf.js"></script>
    <script type="text/javascript" src="xsgzgl/comm/fileUpload/f.js"></script>
    <script type="text/javascript">
        function saveBmd(){
            var checkId = 'ip-ms';
            if(!checkNotNull(checkId)){
                showAlertDivLayer("请将必填项填写完整！");
                return false;
            }
            save();
        }
        function save(){
            var url = "hdgl_bmdwh.do?method=addBmd&type=save";
            ajaxSubFormWithFun("demoForm",url,function(data){
                showAlertDivLayer(data["message"],{},{"clkFun":function(){
                    if (parent.window){
                        refershParent();
                    }
                    iFClose();
                }});
            });
        }

    </script>
</head>
<body style="width:100%">
<html:form action="/hdgl_bmdwh" method="post" styleId="demoForm">
    <div style='width:100%;height:300px;overflow-x:hidden;overflow-y:auto;'>
        <table width="100%" border="0" class="formlist">
            <thead>
            <tr>
                <th colspan="2">
                    <span>辅导室信息</span>
                </th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <th width="30%"><span class="red">*</span>ip地址</th>
                <td width="70%">
                    <input type="text" id="ip" name="ip" style="width: 70%"/>
                </td>
            </tr>
            <tr>
                <th width="30%"><span class="red">*</span>密码</th>
                <td width="70%">
                    <input type="password" id="password" name="password" style="width: 70%"/>
                </td>
            </tr>
            <tr>
                <th width="16%"><span class="red">*</span>描述</th>
                <td width="34%">
                    <input type="text" id="ms" name="ms" style="width: 70%"/>
                </td>
            </tr>
            </tbody>
        </table>
    </div>
    <div>
        <table width="100%" border="0" class="formlist">
            <tfoot>
            <tr>
                <td colspan="4" >
                    <div class="bz">"<span class="red">*</span>"为必填项</div>
                    <div class="btn">
                        <button type="button" type="button" onclick="saveBmd();return false;" >
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
    </div>
</html:form>
</body>
</html>

