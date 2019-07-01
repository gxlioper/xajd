<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript">
			var gridSetting = {
				caption:"�ҽ������б�",
				pager:"pager",
				multiselect:false,
				rowNum:10,
				url:"jjgl_jjxq.do?method=getJjxqList",
				colList:[
				   {label:'�ҽ̱��',name:'xqid', index: 'xqid',key:true},
				   {label:'sqzt',name:'sqzt', index: 'sqzt',hidden:true},
				   {label:'�꼶',name:'jjnj', index: 'jjnj'},
				   {label:'�ҽ̿�Ŀ',name:'jjxk', index: 'jjxk'},
				   {label:'�ҽ̵ص�',name:'jjdd', index: 'jjdd'},
				   {label:'�ҽ�Ҫ��',name:'jjlsyq', index: 'jjlsyq'},
				   {label:'����״̬',name:'sqztmc', index: 'sqztmc',formatter:function(v,r){
					   if (v == "δ����" || v == "�˻�"){
                           return "<a class='btn_common' title='���������' onclick='jjsq(\""+r["xqid"]+"\")'>δ����</a>";
					   }else{
                           return "<a class='btn_common disabled'>"+v+"</a>";
					   }

				   }}
				]
			};

			function jjsq(xqid){

                var title = "����ҽ�";
                var url = "jjgl_jjxq.do?method=jjaqxys&xqid="+xqid;
                showDialog(title, 700, 480, url);
			}
			
			jQuery(function(){
				jQuery("#dataTable").initGrid(gridSetting);
			});
	
			function searchRs(){
				var map = {};
                map["jjxk"] = jQuery("#jjxk").val();
                map["jjnj"] = jQuery("#jjnj").val();
				jQuery("#dataTable").reloadGrid(map);
			}
		</script>
	</head>

	<body>
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
		<!-- ���� end-->
	
		<div class="toolbox">
			<!-- �������� -->
			<div class="searchtab">
				<html:form action="/jjgl_jjxq" method="post" >
					<table width="100%" border="0">
						<tr>
							<th width="10%">�ҽ̿�Ŀ</th>
							<td>
								<html:select property="jjxk" styleId="jjxk" style="width:173px">
					    			<html:option value=""></html:option>
					    			<html:options collection="jjxkList" property="jjxk" labelProperty="jjxk"/>
					    		</html:select>
							</td>
							<th width="10%">�꼶</th>
							<td>
								<html:select property="jjnj" styleId="jjnj" style="width:173px">
					    			<html:option value=""></html:option>
					    			<html:options collection="jjnjList" property="jjnj" labelProperty="jjnj"/>
					    		</html:select>
							</td>
							<td>
								<div class="btn">
									<button type="button" class="btn_cx" id="search_go" onclick="searchRs()">
										�� ѯ
									</button>
								</div>
							</td>
						</tr>					
					</table>
				</html:form>
			</div>
		</div>
	
		<div>
			<h3 class="datetitle_01">
				<span>�ҽ������б�</span>
			</h3>
		</div>
		<div class="formbox" style="width:100%;height:330px;overflow-x:hidden;overflow-y:auto;">
			<table id="dataTable" ></table>
		</div>
		<div id="pager"></div>
	</body>
</html>
