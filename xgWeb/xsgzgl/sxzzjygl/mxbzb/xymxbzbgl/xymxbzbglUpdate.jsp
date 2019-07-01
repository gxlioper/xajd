<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini"%>
    <script type="text/javascript" src="js/jquery/jquery.form.js"></script>
    <script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
    <script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
    <script type='text/javascript' src='/xgxt/dwr/util.js'></script>
    <script language="javascript" src="js/function.js"></script>
    <script language="javascript" src="js/Basic.js"></script>
    <script language="javascript" src="js/comm/commFunction.js"></script>
    <script language="javascript" src="comm/editor/kindeditor.js"></script>
    <script language="javascript" src="comm/editor/zh_CN.js"></script>
    <script language="javascript" src="comm/editor/editor.js"></script>
    <script type="text/javascript" src="xsgzgl/sxzzjygl/mxbzb/xymxbzbgl/js/xymxbzbglEdit.js"></script>
    <link rel="stylesheet" href="<%= stylePath%>/css/public.css" type="text/css" media="all" />
    <link rel="stylesheet" href="<%=stylePath %>/css/module.css" type="text/css" media="all" />
    <script type="text/javascript">
        jQuery(function(){


        })
    </script>
    <style type="text/css">
        *{font-size: 14px;}
        textarea{width: 98%}
    </style>
</head>
<body>
<html:form action="/sxzzjy_xymxbzbgl" method="post"  styleId="form">
    <html:hidden property="newsid" styleId="newsid"/>
    <input type="hidden" name="test" id="test"/>
    <div style='width:100%; height:470px; overflow-y:auto;overflow-x:hidden'>
        <table width="100%" border="0" class="formlist">
            <tbody id="">

            <tr style="">
                <th >
                    <span style="color: red">*</span>标 题
                </th>
                <td colspan="3">
                    <html:text property="newstitle" styleId="newstitle" style="width:80%;" />
                </td>
            </tr>
            <tr style="">
                <th >
                    <span style="color: red">*</span>类别
                </th>
                <td colspan="3">
                    <html:select property="newstype" styleId="newstype" style="width:200px" styleClass="select">
                        <html:option value=""></html:option>
                        <html:options collection="lbList" property="typedm"
                                      labelProperty="typemc" />
                    </html:select>
                </td>

            </tr>
            <tr style="">
                <th>
                    <span style="color: red">*</span>编辑内容
                </th>
                <td   colspan="3">
                    <html:textarea property="newscont" styleId="editorid" rows="10" style="width:720px;height:320px"></html:textarea>
                </td>
            </tr>
            <tr style="">
                <th>
                    <span style="color: red">*</span>是否直接发布
                </th>
                <td >
                    <html:radio property="sffb" value="是">是</html:radio>
                    <html:radio property="sffb" value="否">否</html:radio>
                </td>
                <th>
                    <span style="color: red">*</span>是否置顶
                </th>
                <td >
                    <html:radio property="sfzd" value="是">是</html:radio>
                    <html:radio property="sfzd" value="否">否</html:radio>
                </td>
            </tr>
            </tbody>


        </table>
    </div>
</html:form>
    <table width="100%" border="0" class="formlist">
        <tfoot>
        <tr>
            <td colspan="4">
                <div class="bz">
                    "<span class="red">*</span>"为必填项
                </div>
                <div class="btn">

                    <button type="button" type="button" onclick="save('update');">
                        保 存
                    </button>

                    <button type="button" type="button" onclick="iFClose();">
                        关 闭
                    </button>
                </div>
            </td>
        </tr>
        </tfoot>
    </table>
</body>
</html>

