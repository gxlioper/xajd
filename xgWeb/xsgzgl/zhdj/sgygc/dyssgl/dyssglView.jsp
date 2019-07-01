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
    <script type="text/javascript" src="xsgzgl/zhdj/sgygc/dyssgl/js/dyssglEdit.js"></script>
    <script type="text/javascript">
        jQuery(function(){
            var lddm=jQuery("#lddm").val();
            var qsh=jQuery("#qsh").val();
            jQuery.post("zhdj_dyssgl.do?method=showQscy", {lddm:lddm,qsh:qsh}, function (dataList) {
                if(dataList != null && dataList.length>0 && dataList[0].xm != undefined){

                    var html = "";
                    for(var i = 0;i<dataList.length;i++){

                        var o = dataList[i];
                        if(i%2 ==0){
                            html += "<tr>";
                            html += "<th width='16%'>"+o.cwh+"号床</th>";

                            if(i == dataList.length-1 ){
                                html += "<td colspan='3'>"+o.xm+"</td>";
                            }else{
                                html += "<td width='34%'>"+o.xm+"</td>";
                            }

                        }else{
                            html += "<th>"+o.cwh+"号床</th>";
                            html += "<td>"+o.xm+"</td>";
                            html += "</tr>"

                        }
                    }
                    jQuery("#qscyTbody").append(html);

                }
            }, 'json');
        });
    </script>
    <style type="text/css">
        textarea{width: 98%}
    </style>
</head>
<body>
<html:form action="/zhdj_dyssgl" method="post"  styleId="form">
    <html:hidden property="dyssid" styleId="dyssid"  style="width:150px;" />
    <div style='width:100%; height:350px; overflow-y:auto;overflow-x:hidden'>
        <table width="100%" border="0" class="formlist">
            <tbody id="">
            <tr>
                <th colspan="4" style="text-align: left">党员信息</th>

            </tr>
            <tr style="">
                <th width="16%">
                    <span style="color: red">*</span>学号
                </th>
                <td  colspan="3">
                        ${map.dyxh}

                </td>
            </tr>
            <tr>
                <th width="16%">
                    姓名
                </th>
                <td  id="dyxm" width="34%">
                        ${map.xm}
                </td>
                <th width="16%">
                    性别
                </th>
                <td id="xb"  width="34%">
                        ${map.xb}
                </td>
            </tr>
            <tr>
                <th >
                    联系固话
                </th>
                <td  id="lxgh">
                        ${map.lxdh}
                </td>
                <th >
                    手机号码
                </th>
                <td id="sjhm" >
                        ${map.sjhm}
                </td>
            </tr>
            <tr>
                <th >
                    支部
                </th>
                <td  id="dzbmc" >
                        ${map.dzbmc}
                </td>
                <th >
                    支部书记
                </th>
                <td id="dzbsjxm">
                        ${map.dzbsjxm}
                </td>
            </tr>
            </tbody>
        </table>
        <table width="100%" border="0" class="formlist" style="border-top: none;">
            <tr>
                <th colspan="4" style="text-align: left">联系宿舍</th>

            </tr>
            <tr style="" id="qsTr">
                <th width="16%">
                    <span style="color: red">*</span> 宿舍
                </th>
                <td colspan="3">
                    ${map.qsxx}
                </td>
            </tr>
            <tbody id="qscyTbody">
            </tbody>
        </table>
        <table width="100%" border="0" class="formlist"  style="border-top: none !important;">
            <tbody>
            <tr>
                <th colspan="4" style="text-align: left">
                    宿舍情况自评
                </th>
            </tr>
            <tr>
                <th width="16%">
                    <span style="color: red">*</span>卫生情况
                </th>
                <td  colspan="3">
                        ${map.wsqkmc}
                </td>
            </tr>
            <tr>
                <th >
                    <span style="color: red">*</span>用电安全
                </th>
                <td  colspan="3">
                        ${map.ydaqmc}
                </td>
            </tr>
            <tr>
                <th >
                    <span style="color: red">*</span>宿舍学风
                </th>
                <td  colspan="3">
                        ${map.ssxfmc}
                </td>
            </tr>
            <tr>
                <th >
                    <span style="color: red">*</span>有无赌博酗酒现象
                </th>
                <td  colspan="3">
                        ${map.ywdbxjxxmc}
                </td>
            </tr>
            <tr>
                <th >
                    <span style="color: red">*</span>有无吸烟现象
                </th>
                <td  colspan="3">
                        ${map.ywxyxxmc}
                </td>
            </tr>
            <tr>
                <th >
                    <span style="color: red">*</span>所在宿舍与文明宿舍的差距
                </th>
                <td  colspan="3">
                        ${map.ywmsscj}
                </td>
            </tr>
            <tr>
                <th >
                    <span style="color: red">*</span>整改计划、措施
                </th>
                <td  colspan="3">
                        ${map.zgjhcs}
                </td>
            </tr>
            </tbody>


        </table>
    </div>
    <table width="100%" border="0" class="formlist">
        <tfoot>
        <tr>
            <td colspan="4">
                <div class="btn">
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

