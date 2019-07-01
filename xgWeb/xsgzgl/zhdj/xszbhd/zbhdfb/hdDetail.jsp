
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
    <script type="text/javascript" src="xsgzgl/comm/fileUpload/f_q.js"></script>
    <script type="text/javascript" src="js/jquery/plugins/upload/ajaxfileupload.js"></script>
    <script type="text/javascript" src="xsgzgl/zhdj/xszbhd/zbhdfb/js/hdfbEdit.js"></script>

    <script type="text/javascript">
        jQuery(function(){
            var num = "${map.num}";
            var hdid =  jQuery("#hdid").val();
            if(num == 0){
                jQuery("#numTd").html("0");
            }else{
                var a = "<a href=\"javascript:void(0)\" class='name' onclick=\"showDialog('查看面向党支部' , 650,500 , 'xszbhd_hdfb.do?method=showDzb&doType=view&hdid=" + hdid + "');return false;\"> "+num+"</a>";
                jQuery("#numTd").html(a);
            }

        })
    </script>
    <style type="text/css">
        textarea{width: 98%}
    </style>
</head>
<body>
<html:form action="/xszbhd_hdfb" method="post"  styleId="form">
    <html:hidden property="hdid" styleId="hdid"  value="${map.hdid}"/>
    <div style='width:100%; height:350px; overflow-y:auto;overflow-x:hidden'>
        <table width="100%" border="0" class="formlist">
            <tbody id="">
            <tr style="">
                <th width="30%">
                    学年
                </th>
                <td width="70%">
                        ${map.xn}

                </td>


            </tr>
            <tr>
                <th >
                    学期
                </th>
                <td  class="xm">
                        ${map.xqmc}

                </td>

            </tr>
            <tr style="">
                <th >
                    主题
                </th>
                <td  class="">
                    ${map.hdzt}
                </td>
            </tr>
            <tr style="">
                <th >
                   起始时间
                </th>
                <td >
                        ${map.kssj}
                    至
                        ${map.jzsj}
                </td>
            </tr>
            <tr style="">
                <th >
                    已选择面向党支部个数
                </th>
                <td id="numTd">

                </td>
            </tr>
            <tr style="">
                <th>
                    内容

                </th>
                <td    class="">
                   ${map.hdnr}
                </td>
            </tr>
            <tr>
                <th>
                    附件
                </th>
                <td >
                    <div id="commonfileupload-list-0" style="padding: 5px;"></div>
                    <script type="text/javascript">
                        //调用附件
                        jQuery(function(){
                            var gid = "${map.fjid}";
                            jQuery.MultiUploader_q({
                                gid : gid,
                                targetEl : 'commonfileupload-list-0'
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
                <%--<div class="bz">
                    "<span class="red">*</span>"为必填项
                </div>--%>
                <div class="btn">
                    <%--<button id="buttonSave" onclick="save('update');return false;">
                        保 存
                    </button>--%>
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

