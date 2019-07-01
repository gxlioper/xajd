<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript">
		function wjcfhz(){
			if(checkSjTj('ksnf','开始年份','jsnf','结束年份')){
				return true;
			}else{
				return  false;
			}
			var url="/xgxt/wjcfHztj.do?method=wjcfHztj";
			document.forms[0].action = url;
			document.forms[0].target = "_blank";
			document.forms[0].submit();
			document.forms[0].target = "_self";	
		}
		</script>
  </head>
  
  <body>
  <html:form action="/wjcfHztj" method="post">
    <div class="tab_cur" id="jd">
				<p class="location">
					<em>您的当前位置:</em><a>${title }</a>
				</p>
	</div>
	<div class="tab">
		<table width="100%"  border="0" class="formlist">
		 <thead>
			<tr>
   				<th colspan="6"><span>选择年份</span></th>
   			</tr>
		 </thead>
		  <tfoot>
		      <tr>
		        <td colspan="6"><div class="bz">"<span class="red">*</span>"为必填项</div>
		          <div class="btn">
					<button type="button"  onclick="wjcfhz();Close();" id="buttonSave">
						确 定
					</button>
					<button type="button"  onclick="Close();return false;"
						 id="buttonModi" >
						关 闭
					</button>					           
		          </div>
		          </td>
		      </tr>
		    </tfoot>
		    <tbody>
			 <tr>
			 	<th>选择年份</th>
			 	<td>
			 		<html:select property="ksnf" styleId="ksnf">
						<html:options collection="ndList" property="nd"
							labelProperty="nd" />
					</html:select>--
					<html:select property="jsnf" styleId="jsnf">
						<html:options collection="ndList" property="nd"
							labelProperty="nd" />
					</html:select>
			 	</td>
			 </tr>
			 </tbody>
			 </table>  				
	</html:form>
  </body>
</html>
