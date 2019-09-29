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
    <script type="text/javascript" src="comm/editor/kindeditor-min.js"></script>
    <script type="text/javascript" src="comm/editor/zh_CN.js"></script>
    <script type="text/javascript" src="js/check.js"></script>
    <script type="text/javascript">
        //保存
        var gsjjeditor;

        function yrdwDivSave(type){
            var checkIds = "gwmc-gwryyq";

            if(!checkNotNull(checkIds)){
                showAlert("请将带<font color='red'>*</font>的项目填写完整!");
                return false;
            }
            var url="qgzx_gwglnew.do?method=gwfbZj&type="+type;
            ajaxSubFormWithFun("qgzxGwglNewForm",url,function(data){
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
        function checkGz() {
            var gzsx = '${gsgz.cjsx}';
            var gwcjsx = jQuery("#gwcjsx").val();
            if(gwcjsx>gzsx||gwcjsx==''){
                var gwcjsx = jQuery("#gwcjsx").val(gzsx);
                jQuery("#gwcjsx").focus();
                return false;
            }
        }
        function checkGs() {
            var gssx = '${gsgz.gzscsx}';
            var gzscsx = jQuery("#gssx").val();
            if(gzscsx>gssx||gzscsx==''){
                var gzscsx = jQuery("#gssx").val(gssx);
                jQuery("#gssx").focus();
                return false;
            }
        }
        //岗位类别改变
       /* function gwlbChange(target){
            var v = jQuery(target).val();
            var url = "qgzx_gwglnew.do?method=gwlbChange";
            jQuery.post(url,{id:v},function(data){
                jQuery("#gwcjsx").val(data["gwxcsx"]);
                jQuery("#gssx").val(data["gssx"]);
                jQuery("#label").html(data["label"]);
            },'json');
        }*/
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
                afterBlur:function(){this.sync();}
            };
            gsjjeditor = KindEditor.create('textarea[name="gwryyq"]',simpleOption);

            jQuery("#gwlb").trigger('change');
        })
    </script>
</head>
<body >

<html:form styleId="qgzxGwglNewForm" action="/qgzx_gwglnew" method="post" onsubmit="return false;">
    <input type="hidden" name="yrdwid" value="${yrdw.yrdwid}">
    <table align="center" class="formlist">
        <thead>
        <tr>
            <th colspan="4" style="width: 25%">
                <span>岗位基本信息</span>
            </th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <th>
                学年学期
            </th>
            <td>
                ${xn}${xqmc}
            </td>
            <th>
                用人单位
            </th>
            <td>
                ${yrdw.yrdwmc}
            </td>
        </tr>
        </tbody>
        <tbody>
        <tr>
            <th>
                <span class="red">*</span>岗位名称
            </th>
            <td>
                <input name="gwmc" id="gwmc" value="${model.gwmc}" maxlength="50">
            </td>
            <th>工作性质</th>
            <td>
                <input type="radio" name="gwxzdm" value="0" checked="checked">临时
                <input type="radio" name="gwxzdm" value="1">正式
            </td>
        </tr>
        <tr>
            <th>
                招聘人数
            </th>
            <td>
                <input name="xqrs" id="xqrs" value="${model.xqrs}" onblur="checkMoney(this)" maxlength="3">
            </td>
            <th>
                岗位类型
            </th>
            <td>
                <input type="radio" name="gwlx" value="0" checked="checked">临时
                <input type="radio" name="gwlx" value="1">长期
            </td>
        </tr>
        <tr>
            <th>
                岗位类别
            </th>
            <td >
                <select name="gwlb" id="gwlb">
                    <logic:iterate id="i" name="gwlblist">
                        <option value="${i.gwxzdm}">${i.gwxzmc}</option>
                    </logic:iterate>
                </select>
            </td>
            <th>
                工资上限/月
            </th>
            <td>
                <input type="text" name="gwcjsx" id="gwcjsx" value="${gsgz.cjsx}" onblur="checkGz()" maxlength="7" placeholder="不得大于工资上限（${gsgz.cjsx}）">
            </td>
        </tr>
        <tr>
            <th>
                工时上限/月
            </th>
            <td colspan="3">
                <input type="text" name="gssx" id="gssx" value="${gsgz.gzscsx}" onblur="checkGs()" maxlength="4" placeholder="不得大于工时上限（${gsgz.gzscsx}）">&nbsp;&nbsp;
                <span id="label"></span>
            </td>
        </tr>
        <tr>
            <th>
                招聘开始时间
            </th>
            <td>
                <input name="zpkssj" id="zpkssj" readonly="true" style="width:100px;"
                           onfocus="showCalendar('zpkssj','y-mm-dd');" value="${defalutStart}">
                </input>
            </td>
            <th>
                招聘结束时间
            </th>
            <td>
                <input name="zpjssj" id="zpjssj" readonly="readonly" style="width:100px;"
                           onfocus="showCalendar('zpjssj','y-mm-dd');" value="${defalutEnd}">
                </input>
                <input type="checkbox" name="cq" value="1"/>长期
            </td>
        </tr>
       <%-- <tr>
            <th>
                是否置顶
            </th>
            <td>
                <input type="checkbox" name="sfzd" id="sfzd" value="1">
            </td>
            <th>
                是否显示工资
            </th>
            <td>
                <input type="checkbox" name="sfxsgz" id="sfxsgz" value="1">
            </td>
        </tr>--%>
        <tr>
            <th>协议书</th>
            <td colspan="3">
                <html:hidden property="fjid" styleId="fjid" />
                <%@ include file="/xsgzgl/comm/fileUpload/f.jsp"%>
                <script type="text/javascript">
                    //调用附件
                    jQuery(function(){
                        jQuery.MultiUploader({
                            maxcount : 1,
                            //后缀
                            accept : 'doc| docx| jpg| gif| png| bmp',
                            //最大文件大小 单位M
                            maxsize: 5,
                            //存放附件的隐藏域的id
                            elementid : 'fjid'
                        });
                    });
                </script>
            </td>
        </tr>
        <tr>
            <th>
                <span class="red">*</span>招聘要求</br><span class="red">（限制在1500字内）</span>
            </th>
            <td colspan="3">
                <textarea name="gwryyq" id="gwryyq" style="width:100%;height:270px" maxlength="2000"></textarea>
            </td>
        </tr>
        </tbody>
        <tfoot>
        <tr>
            <td colspan="4">
                <div class="bz">
                    <span style="font-size: larger; color: purple;">温情提示：</br>1.校内单位的岗位名称默认为</span>
                    <span style="font-size: larger; color: red;">劳务</span>
                    <span style="font-size: larger; color: purple;">或者</span>
                    <span style="font-size: larger; color: red;">助管</span>
                    <span style="font-size: larger; color: purple;">，请规范填写</br>2.若职位已过期仍没有招满，请自行延长截止日期</span>
                </div>
            </td>
        </tr>
        <tr>
            <td colspan="4">
                <div class="bz">
                    "<span class="red">*</span>"为必填项
                </div>
                <div class="btn">
                    <button type="button" name="保存草稿" onclick="yrdwDivSave('save');return false;">
                        保存草稿
                    </button>
                    <button type="button" name="提交申请" onclick="yrdwDivSave('submit');return false;">
                        提交申请
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
