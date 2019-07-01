
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
            var hdid = jQuery("#hdid").val();
            var url = "xszbhd_hdfb.do?method=hdjdDetail";
            jQuery.post(url, {type: "query",hdid:hdid}, function (dataList) {
                /*alert(JSON.stringify(dataList))*/
                if(dataList != null){
                    for(var i = 0;i < dataList.length;i++){
                        var html = "";
                        var o = dataList[i];
                        html += "<tr>";
                        html += "<td rowspan='" + o.listSize + "'>"+o.xymc+"</td>";

                        for(var j=0;j<o.rslist.length;j++){
                            var r=o.rslist[j];
                            if(j==0){

                                html += "<td>" + r.dzbmc + "</td>";
                                if(r.hdsbid != null){
                                    html += "<td><a href = 'javascript:void(0);' class='name' onclick=\"show('"+r.hdsbid+"')\">" + r.sbzt + "</a></td>";
                                }else{
                                    html += "<td style='padding-left: 15px;'>" + r.sbzt + "</td>";
                                }
                                html += "</tr>";
                            }else{
                               html += "<tr>";
                                html += "<td>" + r.dzbmc + "</td>";
                                if(r.hdsbid != null){
                                    html += "<td><a href='#' onclick=\"show('"+r.hdsbid+"')\">" + r.sbzt + "</a></td>";
                                }else{
                                    html += "<td style='padding-left: 15px;'>" + r.sbzt + "</td>";
                                }
                                html += "</tr>";
                            }
                        }
                        jQuery("#content").append(html);
                    }
                }

            },'json');

        });
        function show(hdsbid){
            var title = "查看上报信息";
            var url = "xszbhd_hdsb.do?method=view&type=xssb"+"&hdsbid="+hdsbid;
            showDialog(title, 600, 420, url);
        }
    </script>
    <style type="text/css">
        textarea{width: 98%}
    </style>
</head>
<body>
<html:form action="/xszbhd_hdfb" method="post"  styleId="form">
    <html:hidden property="hdid" styleId="hdid"  value="${hdid}"/>
    <div style='width:100%; height:375px; overflow-y:auto;overflow-x:hidden'>
        <table width="100%" border="0" class="formlist">
            <thead>
                <tr>
                    <td >学院</td>
                    <td>支部</td>
                    <td>情况</td>
                </tr>
            </thead>
            <tbody id="content">

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

