<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�ΰ����� -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	</head>
	<body >
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script type="text/javascript" src="js/sharedFunction.js"></script>
		<script type="text/javascript" src="js/jsFunction.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/interface/getBaseData.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
		<script type="text/javascript" src="/xgxt/js/webServiceFunction/wsFunction.js"></script>
		<script type="text/javascript" src="/xgxt/js/bjlhdx/bjlhdxBaseData.js"></script>
		<html:form action="/chgPass" method="post">
		    <div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a><bean:write name="title" /></a>
				</p>
			</div>
			<div class="tab">
			  <table width="100%"  border="0" class="formlist">
			    <thead>
			    	<tr>
			        	<th colspan="4"><span>�������ݳ�ʼ��</span></th>
			        </tr>
			    </thead>
				
				<tbody>
					<tr>
					<td  colspan="4" align="center">
					<font color="red">*</font>��ѡ��������ݱ�&nbsp;&nbsp;&nbsp;&nbsp;
					<html:select property="realTable" style="width:180px"
						styleId="realTable">
						<html:option value=""></html:option>
						<html:options collection="jbsjTableList" property="tablename"
							labelProperty="tablecomment" />
					</html:select>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<button name="Submit2" class="button2"
						onclick="if($('realTable').value == ''){alert('��ѡ��Ҫ�����ı�');return false;}impAndChkData();">
						���ݵ���
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<button name="Submit2" class="button2"
						onclick="if($('realTable').value == ''){alert('��ѡ��Ҫ�����ı�');return false;}dataStencilExport()">
						����ģ��
					</button>
					</td>
					</tr>
				</tbody>
				</table>
			<logic:present name="doresult">
      <logic:equal name="doresult" value="true">
      	<script type="text/javascript">
      		alert("����ɹ���");
      	</script>
      </logic:equal>
      <logic:equal name="doresult" value="false">
      	<script type="text/javascript">
      		alert("����ʧ�ܣ�");
      	</script>
      </logic:equal>
    </logic:present>
		</html:form>

		<ul style="color: red;list-style:none;">ע��

						    �������ݱ��е�<bean:message key="lable.xsgzyxpzxy" />���롢רҵ���롢�༶������ǰ��һ�¡�׼ȷ����
					    
						    </ul>
					    
	</body>
	<div id="showMes" name="showMes" style="display:none;">����ִ��ͬ��</div>
</html>
