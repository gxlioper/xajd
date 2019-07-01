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
    <script type="text/javascript" src="js/jquery/plugins/upload/ajaxfileupload.js"></script>
    <script type="text/javascript" src="xsgzgl/comm/fileUpload/jmf.js"></script>
    <script type="text/javascript" src="xsgzgl/comm/fileUpload/f.js"></script>
    <script type="text/javascript" src="xsgzgl/gygl/sfqs/sfqskh/sfqskhsq/js/sfqskh.js"></script>
    <script type="text/javascript">
        function save(type){
            if(!isTelephone("lxfs")){
                showAlert("联系方式不正确！");
                return false;
            }
            var url = "gygl_sfqskh_wh.do?method=add&type="+type;
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
            KindEditor.create('textarea[name="zysj"]',simpleOption);

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
<html:form method="post" styleId="demoForm" action="/gygl_sfqskh_wh">
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
                <th><span class="red">*</span>主要事迹</th>
                <td colspan="3">
                    <textarea name="zysj" id="zysj" rows="6" style="width:98%"></textarea>
                </td>
            </tr>
            <tr>
                <th><span class="red">*</span>获奖情况</th>
                <td colspan="3">
                    <html:textarea property="hjqk" rows="6" style="width:98%"></html:textarea>
                </td>
            </tr>
            <tr>
                <th><span class="red">*</span>附件</th>
                <td colspan="3">
                    <html:hidden property="filepath" styleId="filepath" />
                    <input type="file" id="filepath_f" name="filepath" />
                    <script type="text/javascript">
                        //调用附件
                        jQuery(function(){
                            jQuery('#filepath_f').multiUploader({
                                maxcount : 3,
                                //后缀
                                accept : 'png|gif|jpg|zip|rar|doc|docx',
                                //最大文件大小 单位M
                                maxsize: 10,
                                //存放附件的隐藏域的id
                                elementid : 'filepath',
                                eid : 'filepath_f'
                            });
                        });
                    </script>
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