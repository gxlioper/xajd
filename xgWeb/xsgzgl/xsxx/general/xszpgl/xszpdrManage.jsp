<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
    <%@ include file="/syscommon/pagehead_V4.ini"%>
    <script type="text/javascript">
		function zpdr(url){
			var fileValue = document.getElementById("file").value;
			if(fileValue == null || fileValue == ""){
				alert("����ѡ��Ҫ������ļ���");
				return false;
			}
			var fileType =".zip";
			if(-1==fileType.indexOf(fileValue.substr(fileValue.lastIndexOf('.')))){
				alert('�����ļ���ʽ����ȷ��������ѡ��');
				return false;
			}
		
			document.forms[0].action = url;
			document.forms[0].target = "_blank";
			document.forms[0].submit();
			document.forms[0].target = "_self";
		}
		</script>
  	</head>
  
  <body style="">
	<div class="tab">
  	<form action="/xtwhZpgl" method="post" enctype="multipart/form-data">
  	<input type="hidden" name="zpType" value="${zpType}"/>
    <table class="formlist" width="100%" align="center">
    	<thead>
			<tr>
				<th colspan="4">
					<span>��Ƭ��������</span>
				</th>
			</tr>
		</thead>
		<tbody>
		<tr>
			<td align="center">
			��Ƭ�������ͣ�<SELECT id="photoNameType" name="photoNameType" style="width:120px">
					 	 <option value="xh">ѧ��</option>
					 	 <option value="sfzh">���֤��</option>
					 	 <option value="ksh">������</option>
					 </SELECT>&nbsp;&nbsp;&nbsp;&nbsp;
			�ϴ��ļ���<input type="file" name="file" id="file" value="" contenteditable="false"/>
			</td>
		</tr>
		<tr><td>
			<div class="readme">
			  <h2>˵����</h2>
			  <div class="readcon">
			    <ul>
			      <li>ֻ�ܵ���zip��ʽ��ѹ���ļ�������ֱ��ʹ��rar�޸ĺ�׺Ϊzip����</li>
			      <li>��Ƭ������ѧ�š������Ż����֤��������</li>
			      <li>��Ƭ�ĸ�ʽ����Ϊ"jpg��gif��png��bmp"��</li>
			      <li>������Ƭ��С�벻Ҫ����100k,zip����С��Ҫ����50M��</li>
			      <li>���ѧ������Ƭ�Ѵ��ڣ��Ὣ��������Ƭ���ǡ�</li>
			    </ul>  
			
			
			  </div>
			</div>
			</td></tr>
		</tbody>
		<tfoot>
		<tr>
			<td align="right" id="dr">
			<button type="button"  class="button" onclick="zpdr('xsxx_xszpgl.do?method=xszpdrManage&doType=save');">����</button>
			<button type="button"  class="button" onclick="Close();return false;">�ر�</button>
			</td>
		</tr>
		</tfoot>
		
			
		
	</table>
	</form>
	</div>
	 
	
  </body>
</html>
