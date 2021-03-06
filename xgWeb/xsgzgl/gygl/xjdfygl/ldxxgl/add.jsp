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
            var checkIds = "xqdm-lddm-ldmc-ldjc-ldcs-qsch-xslx";
            if(!checkNotNull(checkIds)){
                showAlert("请将必填项填写完整！");
                return false;
            }
            var url = "gygl_fygl_ldxxgl10698.do?method=add&type=save";
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
        function qschChange(){
            var v = jQuery("#qsch").val();
            if(Number(v) > 0){
                jQuery("#sfhlcTh").hide();
                jQuery("#sfhlcTd").hide();
                jQuery(".sfhlc_n").attr("checked","checked");
            } else if(Number(v) == 0){
                jQuery("#sfhlcTh").hide();
                jQuery("#sfhlcTd").hide();
                jQuery(".sfhlc_y").attr("checked","checked");
            } else {
                jQuery("#sfhlcTh").show();
                jQuery("#sfhlcTd").show();
                jQuery(".sfhlc_n").attr("checked","checked");
            }
        }
        jQuery(function(){
            qschChange();
        })
    </script>
</head>
<body>
<html:form method="post" styleId="demoForm" action="/gygl_fygl_ldxxgl10698">
    <div style='width:100%;overflow-x:hidden;overflow-y:auto;'>
        <table width="100%" border="0" class="formlist">
            <thead>
            <tr>
                <th colspan="4">
                    <span>楼栋信息</span>
                </th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <th>
                    <span class="red">*</span>校区
                </th>
                <td>
                    <html:select property="xqdm" styleId="xqdm" style="width:100px;">
                        <html:options collection="xqList" property="dm" labelProperty="xqmc" />
                    </html:select>
                </td>
                <th>
                    <span class="red">*</span>楼栋代码
                </th>
                <td >
                    <html:text property="lddm" styleId="lddm" maxlength="20" />
                </td>
            </tr>
            <tr>
                <th>
                    <span class="red">*</span>楼栋名称
                </th>
                <td >
                    <html:text property="ldmc" styleId="ldmc" maxlength="100" />
                </td>
                <th>
                    <span class="red">*</span>楼栋简称
                </th>
                <td >
                    <html:text property="ldjc" styleId="ldjc" maxlength="100" />
                </td>
            </tr>
            <tr>
                <th>
                    <span class="red">*</span>楼栋走向
                </th>
                <td >
                    <html:radio property="ldzx" value="1">东</html:radio>
                    <html:radio property="ldzx" value="2">南</html:radio>
                    <html:radio property="ldzx" value="3">西</html:radio>
                    <html:radio property="ldzx" value="4">北</html:radio>
                </td>
                <th>
                    <span class="red">*</span>楼栋性别
                </th>
                <td >
                    <html:radio property="ldxb" value="1">男</html:radio>
                    <html:radio property="ldxb" value="2">女</html:radio>
                    <html:radio property="ldxb" value="3">混住</html:radio>
                </td>
            </tr>
            <tr>
                <th>
                    <span class="red">*</span>楼栋层数
                </th>
                <td >
                    <html:select property="ldcs" styleId="ldcs" style="width:100px;">
                        <logic:iterate id="i" name="cshcs">
                            <html:option value="${i}">${i}层</html:option>
                        </logic:iterate>
                    </html:select>
                </td>
                <th>
                    <span class="red">*</span>楼栋起始层号
                </th>
                <td >
                    <html:select property="qsch" styleId="qsch" style="width:100px;" onchange="qschChange()">
                        <logic:iterate id="i" name="qschList">
                            <html:option value="${i}">${i}层</html:option>
                        </logic:iterate>
                    </html:select>
                </td>
            </tr>
            <tr>
                <th>
                    <span class="red">*</span>学生类型
                </th>
                <td >
                    <html:select property="xslx" styleId="xslx" style="width:100px;" >
                        <html:options collection="xslxList" property="pyccdm" labelProperty="pyccmc"></html:options>
                    </html:select>
                </td>
                <th>
                    <span id="sfhlcTh">是否含0层</span>
                </th>
                <td id="sfhlcTd">
                    <html:radio property="sfhlc" value="1" styleClass="sfhlc_y">是</html:radio>
                    <html:radio property="sfhlc" value="0" styleClass="sfhlc_n">否</html:radio>
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