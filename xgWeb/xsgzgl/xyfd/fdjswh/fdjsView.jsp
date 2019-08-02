
<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini"%>
    <script type="text/javascript" src="js/jquery/jquery.form.js"></script>
    <script type="text/javascript" src="js/check.js"></script>
    <script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
    <script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
    <script type="text/javascript" src="js/calendar/calendar.js"></script>
    <script type="text/javascript" src="js/jquery/plugins/upload/ajaxfileupload.js"></script>
    <script type="text/javascript" src="xsgzgl/comm/fileUpload/jmf.js"></script>
    <script type="text/javascript" src="xsgzgl/comm/fileUpload/f.js"></script>
    <script type="text/javascript">
        jQuery(function(){
            var mon = '${fdjsxx.mon}';
            var tues = '${fdjsxx.tues}';
            var wed = '${fdjsxx.wed}';
            var thur = '${fdjsxx.thur}';
            var fri = '${fdjsxx.fri}';
            var sat = '${fdjsxx.sat}';
            var sun = '${fdjsxx.sun}';
            var mond = mon.split(",");
            var tuesd = tues.split(",");
            var wedd = wed.split(",");
            var thurd = thur.split(",");
            var frid = fri.split(",");
            var satd = sat.split(",");
            var sund = sun.split(",");
            if(mond!=null){
                for(var i=0;i<mond.length;i++){
                    jQuery("input:checkbox[name='mond'][value='"+mond[i]+"']").prop("checked","checked");
                }
            }
            if(tuesd!=null){
                for(var i=0;i<tuesd.length;i++){
                    jQuery("input:checkbox[name='tuesd'][value='"+tuesd[i]+"']").prop("checked","checked");
                }
            }
            if(wedd!=null){
                for(var i=0;i<wedd.length;i++){
                    jQuery("input:checkbox[name='wedd'][value='"+wedd[i]+"']").attr("checked","checked");
                }
            }
            if(thurd!=null){
                for(var i=0;i<thurd.length;i++){
                    jQuery("input:checkbox[name='thurd'][value='"+thurd[i]+"']").prop("checked","checked");
                }
            }
            if(frid!=null){
                for(var i=0;i<frid.length;i++){
                    jQuery("input:checkbox[name='frid'][value='"+frid[i]+"']").prop("checked","checked");
                }
            }
            if(satd!=null){
                for(var i=0;i<satd.length;i++){
                    jQuery("input:checkbox:checkbox[name='satd'][value='"+satd[i]+"']").prop("checked","checked");
                }
            }
            if(sund!=null){
                for(var i=0;i<sund.length;i++){
                    jQuery("input:checkbox[name='sund'][value='"+sund[i]+"']").prop("checked","checked");
                }
            }
             jQuery.each(jQuery("input:checkbox"),function (element,index) {
                 jQuery(this).attr("disabled","disabled");
             })

        })

    </script>
</head>
<body style="width:100%">
<html:form action="/xyfd_fdjswh" method="post" styleId="demoForm">
    <div style='width:100%;height:500px;overflow-x:hidden;overflow-y:auto;'>
        <table width="100%" border="0" class="formlist">
            <thead>
            <tr>
                <th colspan="7">
                    <span>������ʦ��Ϣ</span>
                </th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <th width="10%">����</th>
                <td width="20%">
                    <input id="djh" name="djh" value="${fdjsxx.djh}" style="display: none" />
                    <input id="zgh" name="zgh" value="${jsxx.zgh}" style="display: none"/>
                    <label>${jsxx.xm}</label>
                </td>
                <th width="10%">�Ա�</th>
                <td width="20%" id="xb">
                    <label>${jsxx.xb}</label>
                </td>
                <th width="12%">ְ��/ְ��</th>
                <td width="20%" id="zc">
                    ${jsxx.zc}
                </td>
                <th rowspan="2">
                    <div align="center">
                        <img id="zhaopian" src="teaPic.jsp?zgh=${jsxx.zgh}" alt="" style="height: 133px;width: 100px;" border="0"/>
                    </div>
                </th>
            </tr>
            <tr>
                <th>���ڵ�λ</th>
                <td id="bmmc">
                    ${jsxx.bmmc}
                </td>
                <th>�ο�����</th>
                <td>
                    ${fdjsxx.kcmc}
                </td>
                <th>ѧ��/רҵ</th>
                <td>
                    ${fdjsxx.xkzy}
                </td>
            </tr>
            <tr>
                <th>��ϵ�绰</th>
                <td colspan="3">
                    ${fdjsxx.lxdh}
                </td>
                <th>E-mail</th>
                <td colspan="2">
                    ${fdjsxx.dzyx}
                </td>
            </tr>
            <tr>
                <th>������Ŀ</th>
                <td colspan="3">
                    ${fdjsxx.fdkm}
                </td>
                <th>������</th>
                <td colspan="2">
                    ${fdsxx.fdsmc}
                </td>
            </tr>
            <tr>
                <th rowspan="7">����ʱ��</th>
                <th colspan="2">��һ</th>
                <td colspan="4">
                    <div align="center">
                        <input type="checkbox" name="mond" value="16:30-17:30"/>16:30-17:30
                        <input type="checkbox" name="mond" value="18:30-19:30"/>18:30-19:30
                    </div>
                </td>
            </tr>
            <tr>
                <th colspan="2">�ܶ�</th>
                <td colspan="4">
                    <div align="center">
                        <input type="checkbox" name="tuesd" value="16:30-17:30"/>16:30-17:30
                        <input type="checkbox" name="tuesd" value="18:30-19:30"/>18:30-19:30
                    </div>
                </td>
            </tr>
            <tr>
                <th colspan="2">����</th>
                <td colspan="4">
                    <div align="center">
                        <input type="checkbox" name="wedd" value="16:30-17:30"/>16:30-17:30
                        <input type="checkbox" name="wedd" value="18:30-19:30"/>18:30-19:30
                    </div>
                </td>
            </tr>
            <tr>
                <th colspan="2">����</th>
                <td colspan="4">
                    <div align="center">
                        <input type="checkbox" name="thurd" value="16:30-17:30"/>16:30-17:30
                        <input type="checkbox" name="thurd" value="18:30-19:30"/>18:30-19:30
                    </div>
                </td>
            </tr>
            <tr>
                <th colspan="2">����</th>
                <td colspan="4">
                    <div align="center">
                        <input type="checkbox" name="frid" value="16:30-17:30"/>16:30-17:30
                        <input type="checkbox" name="frid" value="18:30-19:30"/>18:30-19:30
                    </div>
                </td>
            </tr>
            <tr>
                <th colspan="2">����</th>
                <td colspan="4">
                    <div align="center">
                        <input type="checkbox" name="satd" value="16:30-17:30"/>16:30-17:30
                        <input type="checkbox" name="satd" value="18:30-19:30"/>18:30-19:30
                    </div>
                </td>
            </tr>
            <tr>
                <th colspan="2">����</th>
                <td colspan="4">
                    <div align="center">
                        <input type="checkbox" name="sund" value="16:30-17:30"/>16:30-17:30
                        <input type="checkbox" name="sund" value="18:30-19:30"/>18:30-19:30
                    </div>
                </td>
            </tr>
            </tbody>
        </table>
    </div>
    <div style="position:fixed;bottom:0;width: 100%">
        <table width="100%" border="0" class="formlist">
            <tfoot>
            <tr>
                <td colspan="4" >
                    <div class="btn">
                        <button type="button" name="�� ��" onclick="iFClose();">
                            �� ��
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
