<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript">
		function wjcfhz(){
			if(checkSjTj('ksnf','��ʼ���','jsnf','�������')){
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
					<em>���ĵ�ǰλ��:</em><a>${title }</a>
				</p>
	</div>
	<div class="tab">
		<table width="100%"  border="0" class="formlist">
		 <thead>
			<tr>
   				<th colspan="6"><span>ѡ�����</span></th>
   			</tr>
		 </thead>
		  <tfoot>
		      <tr>
		        <td colspan="6"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
		          <div class="btn">
					<button type="button"  onclick="wjcfhz();Close();" id="buttonSave">
						ȷ ��
					</button>
					<button type="button"  onclick="Close();return false;"
						 id="buttonModi" >
						�� ��
					</button>					           
		          </div>
		          </td>
		      </tr>
		    </tfoot>
		    <tbody>
			 <tr>
			 	<th>ѡ�����</th>
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
