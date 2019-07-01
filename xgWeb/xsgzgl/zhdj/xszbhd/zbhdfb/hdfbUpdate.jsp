
<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini"%>
    <script type="text/javascript" src="js/calendar/calendar.js"></script>
    <script language="javascript" src="js/check.js"></script>
    <script type="text/javascript" src="js/jquery/jquery.form.js"></script>
    <script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
    <script type="text/javascript" src="xsgzgl/comm/fileUpload/jmf.js"></script>
    <script type="text/javascript" src="xsgzgl/comm/fileUpload/f.js"></script>
    <script type="text/javascript" src="js/jquery/plugins/upload/ajaxfileupload.js"></script>
    <script type="text/javascript" src="xsgzgl/zhdj/xszbhd/zbhdfb/js/hdfbEdit.js"></script>

    <script type="text/javascript">
        jQuery(function(){
            var

        })
    </script>
    <style type="text/css">
        textarea{width: 98%}
    </style>
</head>
<body>
<html:form action="/xszbhd_hdfb" method="post"  styleId="form">
    <html:hidden property="hdid" styleId="hdid"  value="${model.hdid}"/>
    <div style='width:100%; height:420px; overflow-y:auto;overflow-x:hidden'>
        <table width="100%" border="0" class="formlist">
            <tbody id="">
            <tr style="">
                <th width="30%">
                    学年
                </th>
                <td width="70%">
                    ${model.xn}
                    <html:hidden property="xn" styleId="xn" />
                </td>


            </tr>
            <tr>
                <th >
                    学期
                </th>
                <td  class="xm">
                    ${xqmc}
                    <html:hidden property="xqdm" styleId="xqdm" />
                </td>

            </tr>
            <tr style="">
                <th >
                    <span style="color: red">*</span>主题
                </th>
                <td  class="">
                    <input type="text" id="hdzt" value="${model.hdzt}" name="hdzt" style="width: 80%"/>
                </td>
            </tr>
            <tr style="">
                <th >
                    <span style="color: red">*</span> 起始时间
                </th>
                <td >
                    <html:text property="kssj" styleId="kssj" value="${model.kssj}" style="width:150px;" onfocus="showCalendar('kssj','yyyy-MM-dd HH:mm:ss',true,'jzsj');" />
                    至
                    <html:text property="jzsj" styleId="jzsj" value="${model.jzsj}"  style="width:150px;" onfocus="showCalendar('jzsj','yyyy-MM-dd HH:mm:ss',false,'kssj');" />
                </td>
            </tr>
            <tr style="">
                <th>
                    <span style="color: red">*</span>内容<br>
                    <span style="color: red">(限500字)</span>
                </th>
                <td    class="">
                    <textarea rows="3" cols="3" id="hdnr"
                              name="hdnr" onblur="checkLen(this,500);" maxlength="500">${model.hdnr}</textarea>
                </td>
            </tr>
            <tr>
                <th>
                    附件
                </th>
                <td >
                    <html:hidden property="fjid" styleId="fjid" value="${model.fjid}"/>
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
                                elementid : 'fjid',

                                eid : 'filepath_f'
                            });
                        });
                    </script>
                </td>
            </tr>
            </tbody>


        </table>
    </div>
    <table width="100%" border="0" class="formlist">
        <tfoot>
        <tr>
            <td colspan="4">
                <div class="bz">
                    "<span class="red">*</span>"为必填项
                </div>
                <div class="btn">
                    <button id="buttonSave" onclick="save('update');return false;">
                        保 存
                    </button>
                    <button onclick="Close();return false;">
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

