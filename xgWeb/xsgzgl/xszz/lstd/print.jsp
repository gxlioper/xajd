<%@ taglib prefix="logic" uri="http://jakarta.apache.org/struts/tags-logic" %>
<%@ taglib prefix="bean" uri="http://jakarta.apache.org/struts/tags-bean" %>
<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/jquery-1.11.1_migrate.ini"%>
    <link href="assets/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="assets/css/style.css" rel="stylesheet">
    <style>
        tbody td {
            border: 1px solid #ddd;
        }
        thead th {
            border: 1px solid #ddd;
        }
        .fs-12{
            font-size: 12px;
        }
        .PageNext{page-break-after: always;}
    </style>
    <style media="print">
        .noprint{display:none;}
        .print{display:block;}
    </style>
</head>
<body>
<p align="center">
    <button type="button" class="btn btn-primary noprint to_print" >��ӡ</button>
    <button type="button" class="btn btn-default noprint to_close" >�ر�</button>
</p>
<div class="print">
    <logic:iterate id="l" name="printModels">
    <div class=" PageNext">
        <div style="text-align: center;font-size: 24px;"><b>������ͨ��ѧѧ������ѧ�������</b></div>
        <table class="table table-1 fs-12" style="height: 700px;">
            <tbody>
            <tr>
                <td style="width: 60px;" align="center">����</td>
                <td>${l.xsjbxx.xm }</td>
                <td align="center">�Ա�</td>
                <td>${l.xsjbxx.xb }</td>
                <td align="center"><nobr>��������</nobr></td>
                <td>${l.xsjbxx.csrq }</td>
                <td align="center">����</td>
                <td>${l.xsjbxx.mzmc }</td>
            </tr>
            <tr>
                <td align="center">��Ժ</td>
                <td>${l.xsjbxx.symc }</td>
                <td align="center">רҵ</td>
                <td>${l.xsjbxx.zymc }</td>
                <td align="center">�༶</td>
                <td>${l.xsjbxx.bjmc }</td>
                <td align="center">ѧ��</td>
                <td>${l.xsjbxx.xh }</td>
            </tr>
            <tr>
                <td align="center">�绰</td>
                <td>${l.xsjbxx.sjhm }</td>
                <td align="center">QQ</td>
                <td>${l.xsjbxx.qqhm }</td>
                <td align="center">��������</td>
                <td colspan="3">${l.xsjbxx.dzyx }</td>
            </tr>
            <tr>
                <td colspan="2" align="center">�����</td>
                <td colspan="2">${l.xsjbxx.ssbh }</td>
                <td colspan="2" align="center">��Դ��</td>
                <td colspan="2">${l.xsjbxx.syd }</td>
            </tr>
            <tr>
                <td colspan="2" rowspan="2">¼ȡ����ܹ������������������</td>
                <td colspan="6">�Ƿ�������Դ����ѧ����:
                    <logic:equal value="01" name="lstdForm" property="hjfs">
                        ��
                    </logic:equal>
                    <logic:notEqual value="01" name="lstdForm" property="hjfs">
                        ��
                    </logic:notEqual>
                    </br>������:<u>${l.lstdForm.dkje}</u>����λ��Ԫ��
                </td>
            </tr>
            <tr>
                <td colspan="6">����������</td>
            </tr>
            <tr>
                <td colspan="2">��ͨ����</td>
                <td colspan="6"><u>${l.lstdForm.jtfyje}</u>����λ��Ԫ��</td>
            </tr>
            <tr>
                <td rowspan="3" align="center"><div style="text-align: center;line-height: 28px;">��ͥ</br>���</div></td>
                <td>��ͥ����</td>
                <td style="width:100px;">

                </td>
                <td>��ͥ���˿���</td>
                <td>${l.xsjbxx.jtrs}</td>
                <td><nobr>��ͥ�˾�������</nobr></td>
                <td colspan="2">${l.xsjbxx.jtrjysr }����λ��Ԫ��</td>
            </tr>
            <tr>
                <td>��ͥ������Դ</td>
                <td colspan="4">${l.xsjbxx.jtsrly }</td>
                <td><nobr>��ͥ��ϵ�绰</nobr></td>
                <td>${l.xsjbxx.jtdh }</td>
            </tr>
            <tr>
                <td>��ͥ��ַ</td>
                <td colspan="4">${l.xsjbxx.jtdz }</td>
                <td>��������</td>
                <td>${l.xsjbxx.jtyb }</td>
            </tr>
            <tr>
                <td rowspan="${l.size}" style="text-align: center;">
                    ��ͥ</br>��Ա</br>���
                </td>
                <td>����</td>
                <td>����</td>
                <td>�뱾�˹�ϵ</td>
                <td colspan="4">��������ѧϰ��λ��</td>
            </tr>
            <logic:iterate id="i" name="l" property="jtcyList" >
                <tr>
                    <td>${i.cyxm}</td>
                    <td>${i.cynl}</td>
                    <td>${i.cygxmc}</td>
                    <td colspan="4">${i.cyxxdw}</td>
                </tr>
            </logic:iterate>
            <tr>
                <td rowspan="2" align="center" style="text-align: center;line-height: 32px;">��</br>��</br>��</br>��</br>��</br>��</td>
                <td colspan="7">�������뻺�����ɣ�</br>${l.lstdForm.sqly}</td>
            </tr>
            <tr>
                <td colspan="7">
                    ��ϸ˵��������ȡ���ַ�ʽ�����Ƿ������:</br>
                    <input type="checkbox" id="optionsCheckbox3" name="optionsCheckbox" value="01" <logic:equal value="01" name="lstdForm" property="hjfs">checked="checked"</logic:equal> disabled>
                    <span>��Դ�ع�����ѧ����</span></br>
                    <input type="checkbox" id="optionsCheckbox2" name="optionsCheckbox" value="02" <logic:equal value="02" name="lstdForm" property="hjfs">checked="checked"</logic:equal> disabled>
                    <span>У԰�ع�����ѧ����(������:8000Ԫ)</span></br>
                    <input type="checkbox" id="optionsCheckbox1" name="optionsCheckbox" value="03" <logic:equal value="03" name="lstdForm" property="hjfs">checked="checked"</logic:equal> disabled>
                    <span>ѧУ�ڹ���ѧ</span></br>
                    <input type="checkbox" id="optionsCheckbox4" name="optionsCheckbox"value="04" <logic:equal value="04" name="lstdForm" property="hjfs">checked="checked"</logic:equal> disabled>
                    <span>������ѧ��</span></br>
                    <input type="checkbox" id="optionsCheckbox5" name="optionsCheckbox" value="05" <logic:equal value="05" name="lstdForm" property="hjfs">checked="checked"</logic:equal> disabled>
                    <span>��ͥ�������</span></br>
                    <input type="checkbox" id="optionsCheckbox6" name="optionsCheckbox" value="06" <logic:equal value="06" name="lstdForm" property="hjfs">checked="checked"</logic:equal> disabled>
                    <span>����______________________________</span>
                </td>
            </tr>
            <tr>
                <td colspan="2">�������</td>
                <td colspan="6"><u>${l.lstdForm.sqhjje}</u>Ԫѧ��+ס�ޡ�8000Ԫ������8000Ԫ�����ڱ������Ѵ����ɲ��ַ��ɰ�����������</td>
            </tr>
            <tr>
                <td colspan="2">������ֹʱ��</td>
                <td colspan="6"></td>
            </tr>
            <tr>
                <td colspan="8">
                    <span>���˳�ŵ���ҳ�ŵ������Ϣ��ʵ�ɿ�������ŵ�ڻ���ʱ��ǰ������ط��á�</span>
                    <div style="text-align: right;">
                        <span>����ǩ�֣�<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u></span></br>
                        <span>____��____��_____��</span>
                    </div>
                </td>
            </tr>
            <tr>
                <td>��Ժ</br>���</br>���</td>
                <td colspan="7">
                    ${l.lstdForm.syshyj}
                    <div style="text-align: right">
                        <span>��Ժ�쵼ǩ�֣����£���___________</span></br>
                        <span>_____��____��_____��</span>
                    </div>
                </td>
            </tr>
            <tr>
                <td>ѧУ</br>���</td>
                <td colspan="7">
                    ${l.lstdForm.xxshyj}
                    <div style="text-align: right">
                        <span>���ܲ����쵼ǩ�֣����£���___________</span></br>
                        <span>_____��____��_____��</span>
                    </div>
                </td>
            </tr>
            </tbody>
        </table>
    </div>
    </logic:iterate>
</div>
<script type="text/javascript">
    jQuery(function($){
        $(document).off("click touchend", ".btn.to_print").on("click touchend", ".btn.to_print", function(e) {
            //����ҳ���ӡ��ť
            window.print();
        }).off("click touchend", ".btn.to_close").on("click touchend", ".btn.to_close", function(e) {
            window.location.href="about:blank";
            //����ҳ��رհ�ť
            window.close();
        });
    });
</script>
</body>
</html>