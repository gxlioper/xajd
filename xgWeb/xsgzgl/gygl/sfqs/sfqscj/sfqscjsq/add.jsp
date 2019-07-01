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
    <script type="text/javascript" src="comm/editor/kindeditor-min.js"></script>
    <script type="text/javascript" src="comm/editor/zh_CN.js"></script>
    <script type="text/javascript" src="xsgzgl/gygl/sfqs/sfqscj/sfqscjsq/js/sfqscj.js"></script>
    <script type="text/javascript">
        function save(type){
            if(!isTelephone("lxfs")){
                showAlert("联系方式不正确！");
                return false;
            }
            var url = "gygl_sfqscj_wh.do?method=add&type="+type;
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
        jQuery(function(){
            //简单视图
            var simpleOption = {
                name:'simple',
                resizeType : 1,
                themeType : 'simple',//样式风格
                items : [
                    'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline',
                    'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', 'insertorderedlist',
                    'insertunorderedlist', '|', 'emoticons','link'
                ],
                newlineTag:'br',
                afterBlur:function(){this.sync();}
            };
            KindEditor.create('textarea[name="cjjh"]',simpleOption);

            //楼栋寝室下拉框初始化
            xqChange();
        })
    </script>
    <style type="text/css">
        #shlccx_table th{text-align: center;}
        #shlccx_table tr{text-align: center;}
    </style>
</head>
<body>
<html:form method="post" styleId="demoForm" action="/gygl_sfqscj_wh">
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
                    <span class="red">*</span>学年
                </th>
                <td>
                    <html:select property="xn" styleId="xn">
                        <html:options collection="xnList" property="xn" labelProperty="xn"></html:options>
                    </html:select>
                </td>
                <th>
                    <span class="red">*</span>校区
                </th>
                <td>
                    <html:select property="xqdm" styleId="xqdm" onchange="xqChange()">
                        <html:options collection="xqList" property="dm" labelProperty="xqmc"></html:options>
                    </html:select>
                </td>
            </tr>
            <tr>
                <th>
                    <span class="red">*</span>宿舍楼栋号
                </th>
                <td>
                    <html:select property="lddm" styleId="lddm" onchange="ldChange();">
                    </html:select>
                </td>
                <th><span class="red">*</span>寝室号</th>
                <td>
                    <html:select property="qsh" styleId="qsh" onchange="qshChange();">
                    </html:select>
                </td>
            </tr>
            <tr>
                <th>所属书院</th>
                <td id="sssyTd">

                </td>
                <th><span class="red">*</span>联系方式</th>
                <td>
                    <html:text styleId="lxfs" property="lxfs"/>
                </td>
            </tr>
            <tr>
                <th><span class="red">*</span>申报类型</th>
                <td colspan="3">
                    <html:radio property="sblx" value="学风示范">学风示范</html:radio>
                    <html:radio property="sblx" value="卫生示范">卫生示范</html:radio>
                    <html:radio property="sblx" value="党员示范">党员示范</html:radio>
                    <html:radio property="sblx" value="文明示范">文明示范</html:radio>
                    <br>
                    <span>（只可单选，若符合两项及以上条件的宿舍，可直接选择文明示范）</span>
                </td>
            </tr>
            <tr>
                <th>寝室成员</th>
                <td colspan="3">
                    <style type="text/css">
                        #shlccx_table th{text-align: center;}
                        #shlccx_table tr{text-align: center;}
                    </style>
                    <table id="shlccx_table" width="100%">
                        <tr id="xmTr">
                        </tr>
                        <tr id="xhTr">
                        </tr>
                    </table>
                </td>
            </tr>
            <tr>
                <th><span class="red">*</span>创建口号</th>
                <td colspan="3">
                    <html:textarea property="cjkh" style="width:98%"></html:textarea>
                </td>
            </tr>
            <tr>
                <th><span class="red">*</span>创建计划</th>
                <td colspan="3">
                    <textarea name="cjjh" id="cjjh" rows="8" style="width:98%"></textarea>
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
                        <button type="button" onclick="save('save');" id="buttonSave">
                            保存草稿
                        </button>
                        <button type="button" onclick="save('submit');" id="buttonSave">
                            提交申请
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