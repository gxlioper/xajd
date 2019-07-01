<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
   <head>
	  <%@ include file="/syscommon/head.ini"%>
	  <script type="text/javascript" src="js/jquery/jquery.form.js"></script>
	  <script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
	  <script type="text/javascript" src="xsgzgl/szdw/thjl/js/thlx.js"></script>
	   <script type="text/javascript" src="comm/editor/kindeditor-min.js"></script>
	   <script type="text/javascript" src="comm/editor/zh_CN.js"></script>
	  <script type="text/javascript">
          jQuery(function () {
              //简单视图
              var simpleOption = {
                  name:'simple',
                  resizeType : 1,
                  themeType : 'simple',//样式风格
                  items : [
                      'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline',
                      'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', 'insertorderedlist',
                      'insertunorderedlist', '|', 'emoticons','link'
                  ],
                  afterBlur:function(){this.sync();}
              };
              gsjjeditor = KindEditor.create('textarea[name="wttg"]',simpleOption);
          });
	  </script>
  </head>
  
  <body>
  <input type="hidden" id="xxdm" name="xxdm" value="${xxdm}"/>
    <html:form action="/szdw_thlx" method="post" styleId="thlxForm">
		<html:hidden property="type" styleId="type" value="${type}"/>
    	<table width="100%" border="0" class="formlist">
			<thead>
				<tr>
					<th colspan="2">
						<span>谈话类型</span>
					</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<th>
						<span class="red">*</span> 代码
					</th>
					<td>
						<html:text property="lxdm" styleId="lxdm" styleClass="text_nor" readonly="true" maxlength="10"/>
					</td>
				</tr>
				<tr>
					<th>
						<span class="red">*</span> 名称
					</th>
					<td>
						<html:text property="lxmc" styleId="lxmc" styleClass="text_nor" maxlength="50"/>
					</td>
				</tr>
				<tr>
					<th>
						<span class="red">*</span>问题提纲
					</th>
					<td>
						<textarea name="wttg" id="wttg" style="width:98%">${wttg}</textarea>
					</td>
				</tr>
			</tbody>
			<tfoot>
				<tr>
					<td colspan="2">
						<div class="bz">"<span class="red">*</span>"为必填项</div>
						<div class="btn">
							<button id="submit_button" type="button"  onclick="updateThlxAction();">
								保 存
							</button>
							<button type="button" name="关 闭" onclick="iFClose();">
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
