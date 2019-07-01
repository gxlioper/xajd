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
    <style>
        #shlccx_table th{text-align: center;}
        #shlccx_table tr{text-align: center;}
    </style>
    <script type="text/javascript">
        function save(){

            var url = "gygl_fygl_ldxxgl10698.do?method=qsscBc";
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


        /**
         * 性别单个radio改变对隐藏域设值
         */
        function singleXbRadio(target){
            jQuery(target.parentElement.firstElementChild).val(target.value);
        }

        /**
         * 改变寝室性别
         */
        function changeQsxb(){
            var val = jQuery("input[name='ldxb']:checked").val();
            if(val == "1"){
                jQuery(".qsxb-nan").attr("checked","checked");

                jQuery(".qsxb-nan").attr("disabled","disabled");
                jQuery(".qsxb-nv").attr("disabled","disabled");

                jQuery(".xbHiden").val("1");
            } else if (val == "2"){
                jQuery(".qsxb-nv").attr("checked","checked");

                jQuery(".qsxb-nan").attr("disabled","disabled");
                jQuery(".qsxb-nv").attr("disabled","disabled");

                jQuery(".xbHiden").val("2");
            } else {
                jQuery(".qsxb-nan").removeAttr("disabled");
                jQuery(".qsxb-nv").removeAttr("disabled");
            }
        }
        jQuery(function(){
            changeQsxb()
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
                    校区
                </th>
                <td>
                        ${xqmc}
                </td>
                <th>
                    楼栋名称
                </th>
                <td>
                    <html:hidden property="lddm"/>
                        ${ldmc}
                </td>
            </tr>
            <tr>
                <th>
                    楼栋性别
                </th>
                <td >
                    <input type="radio" name="ldxb" value="1" onchange="changeQsxb()" <logic:equal value="1" name="mmark">disabled</logic:equal> <logic:equal value="1" name="ldxb">checked</logic:equal>/>男
                    <input type="radio" name="ldxb" value="2" onchange="changeQsxb()" <logic:equal value="1" name="mmark">disabled</logic:equal> <logic:equal value="2" name="ldxb">checked</logic:equal>/>女
                    <input type="radio" name="ldxb" value="3" onchange="changeQsxb()" <logic:equal value="3" name="ldxb">checked</logic:equal>/>混住
                </td>
                <th>
                    楼栋层数
                </th>
                <td >
                    ${ldcs}
                </td>
            </tr>
            <tr>
                <th>
                    楼栋起始层号
                </th>
                <td >
                    ${qsch}
                </td>
                <th>
                    <span id="sfhlcTh">是否含0层</span>
                </th>
                <td id="sfhlcTd">
                    <logic:equal value="1" name="sfhlc">
                        是
                    </logic:equal>
                    <logic:notEqual value="0" name="sfhlc">
                        否
                    </logic:notEqual>
                </td>
            </tr>
            </tbody>
            <thead>
            <tr>
                <th colspan="4">
                    <span>寝室信息</span>
                </th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td colspan="4">
                    <div class="con_overlfow">
                        <table id="shlccx_table" width="100%" class="formlist" >
                            <tr>
                                <th>层号</th>
                                <th>生成寝室数</th>
                                <th>总床位数</th>
                                <th>寝室性别</th>
                            </tr>
                            <tbody id="lcTd">
                                <logic:iterate id="i" name="lcqsxx" indexId="index">
                                    <tr>
                                        <input type="hidden" value="${i.ch}" name="qsxx[<%=index%>].ch">
                                        <td>${i.ch}层</td>
                                        <td>${i.qss}</td>
                                        <td>${i.cws}</td>
                                        <td>
                                            <input type="hidden" class="xbHiden" name="qsxx[<%=index%>].qsxb" value="${i.qsxb}">
                                            <logic:notEqual value="0" name="i" property="mark" >
                                                <logic:equal value="1" name="i" property="qsxb">
                                                    男
                                                </logic:equal>
                                                <logic:equal value="2" name="i" property="qsxb">
                                                    女
                                                </logic:equal>
                                                <logic:equal value="3" name="i" property="qsxb">
                                                    混住
                                                </logic:equal>
                                            </logic:notEqual>
                                            <logic:equal value="0" name="i" property="mark" >
                                                <input type="radio" value="1" name="qsxx[<%=index%>].qsxb1"
                                                       onchange="singleXbRadio(this)" class='qsxb-nan'
                                                       <logic:equal value="1" name="i" property="qsxb">checked</logic:equal>
                                                >男
                                                <input type="radio" value="2" name="qsxx[<%=index%>].qsxb1"
                                                       onchange="singleXbRadio(this)" class='qsxb-nv'
                                                       <logic:equal value="2" name="i" property="qsxb">checked</logic:equal>
                                                >女
                                            </logic:equal>
                                        </td>
                                    </tr>
                                </logic:iterate>
                            </tbody>
                        </table>
                    </div>
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