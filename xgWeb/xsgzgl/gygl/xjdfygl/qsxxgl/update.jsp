<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini"%>
    <script type="text/javascript" src="js/jquery/jquery.form.js"></script>
    <script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
    <script type="text/javascript" src="js/calendar/calendar.js"></script>
    <script type="text/javascript" src="js/check.js"></script>
    <script type="text/javascript">
        function save(){
            var checkIds = "cws-sfbz";
            if(!checkNotNull(checkIds)){
                showAlert("请将必填项填写完整！");
                return false;
            }
            var url = "gygl_fygl_qsxxgl10698.do?method=update&type=save";
            ajaxSubFormWithFun("demoForm", url, function(data) {
                if(data["message"]=="保存成功！"){
                    showAlert(data["message"],{},{"clkFun":function(){
                        if (parent.window){
                            refershParent();
                        }
                    }});
                }else{
                    showAlert(data["message"]);
                }
            });
        }
    </script>
</head>
<body>
<html:form method="post" styleId="demoForm" action="/gygl_fygl_qsxxgl10698">
    <div style='width:100%;overflow-x:hidden;overflow-y:auto;'>
        <table width="100%" border="0" class="formlist">
            <thead>
            <tr>
                <th colspan="4">
                    <span>寝室信息</span>
                </th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <th>
                    <span class="red">*</span>校区
                </th>
                <td>
                    ${xqmc}
                </td>
                <th>
                    <span class="red">*</span>楼栋
                </th>
                <td >
                    ${ldmc}
                    <html:hidden property="lddm"></html:hidden>
                </td>
            </tr>
            <tr>
                <th>
                    <span class="red">*</span>所在楼层
                </th>
                <td >
                    ${ch}
                </td>
                <th>
                    <span class="red">*</span>寝室号
                </th>
                <td >
                    ${qsh}
                    <html:hidden property="qsh" />
                </td>
            </tr>
            <tr>
                <th>
                    <span class="red">*</span>床位数
                </th>
                <td >
                    <html:text property="cws" styleId="cws" maxlength="2" />
                </td>
                <th>
                    <span class="red">*</span>收费标准
                </th>
                <td >
                    <html:text property="sfbz" styleId="sfbz" maxlength="4" />
                </td>
            </tr>
            <tr>
                <th>
                    房间类型
                </th>
                <td >
                    <html:select property="fjlx" styleId="fjlx" style="width:100px;">
                        <html:option value="01">宿舍</html:option>
                        <html:option value="02">值班室</html:option>
                        <html:option value="03">厕所</html:option>
                    </html:select>
                </td>
                <th>
                    房间走向
                </th>
                <td>
                    <html:radio property="fjzx" value="1">东</html:radio>
                    <html:radio property="fjzx" value="2">南</html:radio>
                    <html:radio property="fjzx" value="3">西</html:radio>
                    <html:radio property="fjzx" value="4">北</html:radio>
                </td>
            </tr>
            <tr>
                <th>
                    <span class="red">*</span>寝室性别
                </th>
                <td >
                    <html:radio property="qsxb" value="1">男</html:radio>
                    <html:radio property="qsxb" value="2">女</html:radio>
                    <html:radio property="qsxb" value="3">混住</html:radio>
                </td>
                <th>
                    寝室电话
                </th>
                <td>
                    <html:text property="qsdh" styleId="qsdh" maxlength="20" />
                </td>
            </tr>
            <tr>
                <th>
                    <span class="red">*</span>是否有空调
                </th>
                <td >
                    <html:radio property="sfykt" value="1">是</html:radio>
                    <html:radio property="sfykt" value="0">否</html:radio>
                </td>
                <th>
                    <span class="red">*</span>是否有卫生间
                </th>
                <td>
                    <html:radio property="sfywsj" value="1">是</html:radio>
                    <html:radio property="sfywsj" value="0">否</html:radio>
                </td>
            </tr>
            <tr>
                <th>
                    <span class="red">*</span>卫生间位置
                </th>
                <td>
                    <html:radio property="wsjwz" value="01">房间内部</html:radio>
                    <html:radio property="wsjwz" value="02">阳台</html:radio>
                </td>
                <th>
                    卫生间走向
                </th>
                <td>
                    <html:radio property="wsjzx" value="1">西南角</html:radio>
                    <html:radio property="wsjzx" value="2">东南角</html:radio>
                    <html:radio property="wsjzx" value="3">西北角</html:radio>
                    <html:radio property="wsjzx" value="4">东北角</html:radio>
                </td>
            </tr>
            <tr>
                <th>
                    备注<br />
                    <span class="red">(限1000字)</span>
                </th>
                <td colspan="3">
                    <html:textarea property="bz" style="width:94%;height:80px"  styleId="bz" onblur="chLengs(this,1000);" ></html:textarea>
                </td>
            </tr>

            </tbody>
        </table>
    </div>
    <div style="height:30px;"></div>
    <div>
        <table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
            <tfoot>
            <tr>
                <td colspan="4">
                    <div class="bz">
                    </div>
                    <div class="btn">
                        <button type="button" onclick="save();" id="buttonSave">
                            保 存
                        </button>
                        <button type="button" onclick="iFClose();"  id="buttonClose">
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