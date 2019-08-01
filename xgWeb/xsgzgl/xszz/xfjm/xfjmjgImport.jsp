<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <!-- 头文件 -->
    <%@ include file="/syscommon/head.ini"%>
    <script type="text/javascript" src="js/BatAlert.js"></script>
    <script type='text/javascript' src="js/uicomm.js"></script>
    <script language="javascript" src="js/jsFunction.js"></script>
    <script type="text/javascript">
        //最终数据导入
        function importSubmit(){

            var filePath = document.getElementById('file').value;
            document.getElementById('filePath').value = filePath;
            var url = 'xszz_new_xfjm.do?method=importXfjmjg&doType=import';
            refreshForm(url);
            BatAlert.showTips('数据导入中，请稍等...');
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

        //导入模板
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
                    <span>学费减免结果导入</span>
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
                        <h3><span>提示：</span></h3>
                        <p>符合规范的数据可导入成功，学号/职工号已存在或有误的数据将会返回，请注意！
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
                <th>数据模板下载</th>
                <td>
                    <button type="button" onclick="downloadButton()" class="btn_01">下载</button>
                </td>
            </tr>
            <tr>
                <th>最终数据导入</th>
                <td>
                    <input type="file" id="file" name="file"/>
                    <button type="button" class="btn_01" onclick="if(checkFileFormat(document.getElementById('file'))) importSubmit();">
                        导入
                    </button>
                </td>
            </tr>
            </tbody>
            <tfoot>
            <tr>
                <td colspan="4">
                    <div class="btn">
                        <button type="button" id="btn_clo">
                            关 闭
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