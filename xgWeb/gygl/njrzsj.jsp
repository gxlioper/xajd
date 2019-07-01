<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�ΰ����� -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/gyglShareData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>		
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
		<script language="javascript" src="js/gygl/gyglFunction.js"></script>
		<script language="javascript" src="js/AjaxFunction.js"></script>		
			<script type="text/javascript">
	      function save(){
	      	var nj = document.getElementById('nj').value;
	      	if(nj==''){
	      		alert('�꼶����Ϊ�գ�');
	      		return false;
	      	}
	      	refreshForm('/xgxt/njrzsj.do?method=setNjrzsj&doType=save');
	      }
	      

	</script>
	</head>
	<body>
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>��Ԣ���� - ��Ϣά�� - �꼶��סʱ��</a>
			</p>
		</div>
		<!-- ���� end-->
		
		<html:form action="/njrzsj" method="post">
			<input type="hidden" name="zyV" id="zyV" />
			<input type="hidden" name="bjV" id="bjV" />		
			
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
							<li>
								<a href="#"
									onclick="save();return false;"
									class="btn_ccg">����</a>
							</li>
							<li>
								<a href="#"
									onclick="document.getElementById('rzsj').value='';"
									class="btn_sx">����¼������</a>
							</li>	
					</ul>
				</div>
				<!-- ��ť end-->	
				<!-- �������� -->
				<div class="searchtab">
					<table width="100%" border="0">
						<tbody>
							<!-- ��һ�� -->
							<tr>
								<th>
									�꼶								
								</th>
								<td>
									<html:select property="nj" style="width:90px"
										 styleId="nj" onchange="refreshForm('/xgxt/njrzsj.do?method=setNjrzsj')">										
										<html:option value="">--��ѡ��--</html:option>
										<html:options collection="njList" property="nj"
											labelProperty="nj" />
								</html:select>												
								</td>
								<th>
									��ס����
								</th>
								<td>
									<input name="rzsj" value="<bean:write name="rzsj"/>" id="rzsj" 
										onblur="dateFormatChg(this)" style="cursor:hand;"
										onclick="return showCalendar('rzsj','y-mm-dd','aa');" readonly="true"/>
								</td>								
							</tr>							
						</tbody>
					</table>
				</div>
				<!-- �������� end-->
				<!-- ��ѯ���-->
				<div class="formbox">
					<h3 class="datetitle_01">
						<span> ��ѯ���&nbsp;&nbsp; 
							<logic:notEmpty name="rs">
								<font color="blue">��ʾ��������ͷ��������.</font>
							</logic:notEmpty>
							<logic:empty name="rs">
								<font color="red">δ�ҵ��κμ�¼��</font>
							</logic:empty>
						</span>
					</h3>
					<logic:notEmpty name="rs">
						<table summary="" class="dateline" align="" width="100%">
							<thead>
							<tr align="center" style="cursor:hand" >
									<td onclick="tableSort(this)" id="nj">�꼶</td>
									<td onclick="tableSort(this)" id="sj">��סʱ��</td>
							</tr>
						</thead>
						<logic:iterate name="rs" id="s">
							<tr style="cursor:hand">
								<logic:iterate id="v" name="s" offset="0">
									<td nowrap>
										<bean:write name="v" />
									</td>
								</logic:iterate>
							</tr>
						</logic:iterate>
						</table>
					</logic:notEmpty>
				</div>
				<!-- ��ѯ��� end-->
			</div>	
			<logic:equal value="yes" name="result">
				<script type="text/javascript">
					alert('����ɹ���');
				</script>
			</logic:equal>
			<logic:equal value="no" name="result">
				<script type="text/javascript">
					alert('����ʧ�ܣ�');
				</script>
			</logic:equal>
		</html:form>
	</body>
</html>
