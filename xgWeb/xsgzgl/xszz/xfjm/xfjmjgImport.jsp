<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <!-- ͷ�ļ� -->
    <%@ include file="/syscommon/head.ini"%>
    <script type="text/javascript" src="js/BatAlert.js"></script>
    <script type='text/javascript' src="js/uicomm.js"></script>
    <script language="javascript" src="js/jsFunction.js"></script>
    <script type="text/javascript">
        //�������ݵ���
        function importSubmit(){

            var filePath = document.getElementById('file').value;
            document.getElementById('filePath').value = filePath;
            var url = 'xszz_new_xfjm.do?method=importXfjmjg&doType=import';
            refreshForm(url);
            BatAlert.showTips('���ݵ����У����Ե�...');
        }
        jQuery(function () {
            jQuery("#btn_clo").click(function () {
                refershParent();
                BatAlert.closeTips();
                Close();
            });
            jQuery(".ui_close").click(function () {
                refershParent();
                BatAlert.closeTips();
                Close();

            });
        });

        //����ģ��
        function downloadButton() {
            document.forms[0].action = "print/xszz/xszz_xfjm_import.xls";
            document.forms[0].target = "_blank";
            document.forms[0].submit();
            document.forms[0].target = "_self";
        }
    </script>
</head>
<body>
<html:form action="/xszz_new_xfjm.do" enctype="multipart/form-data" method="post">
    <input type="hidden" name="filePath" id="filePath" value="${filePath}"/>
    <input type="hidden" name="tableName" id="tableName" value="${tableName}"/>
    <div class="tab">
        <table class="formlist" border="0" align="center" style="width: 95%;">
            <thead>
            <tr>
                <th colspan="2">
                    <span>ѧ�Ѽ���������</span>
                </th>
            </tr>
            </thead>
        </table>
    </div>
    <div>
        <table border="0" align="center" style="width: 95%;">
            <tr>
                <td>
                    <div class="prompt">
                        <h3><span>��ʾ��</span></h3>
                        <p>���Ϲ淶�����ݿɵ���ɹ���ѧ��/ְ�����Ѵ��ڻ���������ݽ��᷵�أ���ע�⣡
                        </p>
                    </div>
                </td>
            </tr>
        </table>
    </div>
    <div class="tab">
        <table class="formlist" border="0" align="center" style="width: 95%;">
            <tbody>
            <tr>
                <th>����ģ������</th>
                <td>
                    <button type="button" onclick="downloadButton()" class="btn_01">����</button>
                </td>
            </tr>
            <tr>
                <th>�������ݵ���</th>
                <td>
                    <input type="file" id="file" name="file"/>
                    <button type="button" class="btn_01" onclick="if(checkFileFormat(document.getElementById('file'))) importSubmit();">
                        ����
                    </button>
                </td>
            </tr>
            </tbody>
            <tfoot>
            <tr>
                <td colspan="4">
                    <div class="btn">
                        <button type="button" id="btn_clo">
                            �� ��
                        </button>
                    </div>
                </td>
            </tr>
            </tfoot>
            <logic:present name="back">
                <script>
                    alertInfo('${back}');
                </script>
            </logic:present>
        </table>
    </div>

</html:form>
</body>
</html>