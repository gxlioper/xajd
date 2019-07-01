<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>

	<script language="javascript" src="js/qtsjFunction.js"></script>
	
	<script type="text/javascript">	
	 	 function autoFillTeaInfo(cod){
			if(cod == 13){
				var url = '/xgxt/yhwhManage.do?method=yhqxfpManage';
				document.forms[0].action = url;
				document.forms[0].submit();
			}
		}
		
		function checkOne(obj){
			var id = obj.id;
			var ids = id.split('_');
			
			if(ids[1] == 'yy'){
				var oid = ids[0] + "_gl";
				
				if($(oid)){
					$(oid).checked = "";	
				}
			}else{
				var oid = ids[0] + "_yy";
				if($(oid)){
					$(oid).checked = "";
				}
			}
		}
		 
	</script>
	</head>
	
	<body>
		<html:form action="/yhwhManage">
			<input type="hidden" name="userName" id="userName" value="${user.userName }"/>
			<input type="hidden" id="url" value="/xgxt/yhwhManage.do?method=xsqxfpManage"/>			
			
			<div class="tab_cur" id="jd">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>ϵͳά��-Ȩ��ά��-����ѧ����Ȩ</a>
				</p>
			</div>
			
			<div class="compTab" id="card" style="width: 100%">
				<div class="comp_title" id="div1">
					<ul id="ul1">
						<li id="li">
							<a href="#" onclick="refreshForm('/xgxt/yhwhManage.do?method=yhqxfpManage');return false;" id="">
								<span>�����û���Ȩ</span>
							</a>
						</li>
						<li id="li">
							<a href="#" onclick="refreshForm('/xgxt/yhwhManage.do?method=jsBatchManage');return false;" id="">
								<span>��ɫ������Ȩ���û�</span>
							</a>
						</li>
						<li id="li" class="ha">
							<a href="#" onclick="refreshForm('/xgxt/yhwhManage.do?method=xsqxfpManage');return false;" id="">
								<span>����ѧ����Ȩ</span>
							</a>
						</li>
						<li id="li">
							<a href="#" onclick="refreshForm('/xgxt/yhwhManage.do?method=xsBatchManage');return false;" id="">
								<span>��ɫ������Ȩ��ѧ��</span>
							</a>
						</li>
					</ul>				
				</div>
			</div>		
						
			<div class="toolbox" style="display: none">
			  <!-- ��ť -->
			  <div class="buttonbox">
			    <ul>
			      <li> <a id="add" class="btn_zj" onclick="return false;"> �� �� </a> </li>
			      <li> <a id="search" class="btn_cx" onclick="return false;"> �� ѯ </a> </li>
			      <li> <a id="all" onclick="showAllGrgz()" class="btn_qb" onclick="return false;"> ȫ �� </a> </li>
			      <li> <a id="delete" class="btn_sc" onclick="return false;"> ɾ �� </a> </li>
			    </ul>
			  </div>
			  <!-- ��ť -->
			  <p class="toolbox_fot"><em></em> </p>
			</div>
		    
			<div class="rightframe04"><!--�������Ŀ��������ʱ����rightframe04_hidden���class��-->
			 	<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>ѧ����Ϣ</span>
							</th>
						</tr>
					</thead>
					
					<tbody>
						<tr>
							<th width="15%">ѧ��</th>
							<td width="30%">
								<html:text property="yhm" styleId="yhm" value="${rs.yhm}"
									onkeypress="autoFillStuInfo(event.keyCode,this)"></html:text>
							<button type="button" onclick="showTopWin('/xgxt/stu_info.do',750,550);"
								class="btn_01" id="buttonFindStu">
								ѡ��
							</button>
							</td>
							<th width="15%">����</th>
							<td width="35%">
								${rs.xm }
							</td>
						</tr>
						<tr>
							<th>ѧ������</th>
							<td></td>
							<th>ѧ�����</th>
							<td></td>
						</tr>
						<tr>
							<th><bean:message key="lable.xb" /></th>
							<td>
							</td>
							<th>רҵ</th>
							<td>
								
							</td>
						</tr>
						<tr>
							<th>�༶</th>
							<td></td>
							<th></th>
							<td></td>
						</tr>
					</tbody>		
					<thead>
						<tr><th colspan="4"><span>��ɫ��Ϣ</span></th></tr>
					</thead>
					<tbody>
						<tr>
						<th>ӵ�н�ɫ</th>
						<td colspan="3">
							<logic:present name="jsList">
								<logic:iterate id="js" name="jsList" indexId="index">
									${js.jsmc }
									<%	
										int size = (Integer)request.getAttribute("size");
										if(index<size-1){ %>
										,
									<%} %>
								</logic:iterate>
							</logic:present>
						</td>
						</tr>
					</tbody>
					<tfoot>
					<tr>
						<td colspan="4" id="footId">
							<div align="right">
							<button type="button" name="����" class="" onclick="saveDataShowTips('/xgxt/yhwhManage.do?method=yhqxfpManage&doType=save','yhm','���ڱ�֤�����Ժ�');">
								�� ��
							</button>
							<button type="button" name="ȡ��" class="" onclick="window.close();return false;">
								�� ��
							</button>
							</div>
						</td>
					</tr>
				</tfoot>
			</table>
			</div>
			<div id="tmpdiv1"></div>
			<logic:present name="message">
				<input type="hidden" id="message" value="${message }"/>
					<script>
						alert($('message').value);
						dialogArgumentsQueryChick();
						window.close();
					</script>
			</logic:present>
		</html:form>
		<script language="javascript" src="js/bottomButton.js"></script>
	</body>
</html>
