<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/axcs/wpsz/js/wpsz.js"></script>
		
	</head>
	<body>
	   <html:form action="axcsWpsz.do">	
		<input type="hidden" name="currDate" id="currDate" value="${currDate}"/>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
			<p class="help">
				<a href="#" onclick="return false;"	onmousedown ="showHelpDiv();">ʹ�ð���</a>
			</p>
		</div>
		<!-- ��ʾ��Ϣ end-->
		<div  id="div_help" class="prompt" style="display: none">
			<h3>
				<span>��ʾ��</span>
			</h3>
			
			<a class="close" title="����"
			   onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- ��ʾ��Ϣ end-->
		<div class="toolbox">
			<!-- ��ť -->
			<logic:equal name="writeAble" value="yes">	
			<div class="buttonbox">
				<ul>
					<li><a href="javascript:void(0);" onclick="add();" class="btn_zj">����</a></li>
					<li><a href="javascript:void(0);" onclick="update();" class="btn_xg">�޸�</a></li>
					<li><a href="javascript:void(0);" onclick="del();" class="btn_sc">ɾ��</a></li>						
					<li><a href="javascript:void(0);" onclick="jbsz();" class="btn_sz">��������</a></li>						
					<li><a href="javascript:void(0);" onclick="tjsz();" class="btn_sz">��������</a></li>						
					<li><a href="javascript:void(0);" onclick="wpfz();" class="btn_sz">ר����Ʒ����</a></li>	
				</ul>
			</div>
			</logic:equal>
			<!-- �������� -->
			<div class="searchtab">
				<table width="100%" border="0">
					<tr>
						<th width="10%">ѧ��</th>
						<td>
							<html:select property="xn" styleId="xn">
							<html:options collection="xnList" property="xn" labelProperty="xn" />
							</html:select>
						</td>
						<th width="10%">��Ŀ����</th>
						<td>
							<input type="text" id="xmmc" name="xmmc" maxleng="50"/>
						</td>
						<td>
							<div class="btn">
								<button type="button" class="btn_cx" id="search_go" onclick="query()">
									�� ѯ
								</button>
							</div>
						</td>
					</tr>
				</table>
			</div>
		</div>
		<div class="formbox">
			<!--����start-->
			<h3 class="datetitle_01">
				<span> <font color="blue">${currXnXqmc}&nbsp;&nbsp;</font>��Ŀ�б� </span>
			</h3>

			<table id="dataTable" ></table>
			<div id="pager"></div>

		</div>
		</html:form>
	</body>
</html>
