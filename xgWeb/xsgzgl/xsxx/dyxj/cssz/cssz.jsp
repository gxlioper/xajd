<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/xsxx/dyxj/cssz/js/cssz.js"></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script language="javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript">
			function saveForm(){
				var splc = jQuery("#splc").val();
				var sqxn = jQuery("#sqxn").val();
				var sqxq = jQuery("#sqxq").val();
				if (splc == "" || sqxn == "" || sqxq == ""){
					showAlertDivLayer("�뽫��������д������");
					return false;
				}
				
				var id = jQuery("#id").val();
				var url = id == "" ? "xsxxDyxjCssz.do?method=save" : "xsxxDyxjCssz.do?method=update";
				ajaxSubFormWithFun("csszForm",url,function(data){
					showAlertDivLayer(data["message"],{},{"clkFun":function(){
						document.location.href = location.href;
					}});
				});
			}

			jQuery(function(){
				var gridSetting = {
					caption : "",
					pager : "pager",
					url : "xsxxDyxjCssz.do?method=cssz&type=query",
					colList : [ {
						label : 'key',
						name : 'id',
						index : 'id',
						key : true,
						hidden : true
					}, {
						label : 'ѧ��',
						name : 'xh',
						index : 'xh',
						width : '10%'
						//,formatter : xhLink
						
					}, {
						label : '����',
						name : 'xm',
						index : 'xm',
						width : '6%'
					}, {
						label : '�꼶',
						name : 'nj',
						index : 'nj',
						width : '6%'
					}, {
						label : 'ѧԺ',
						name : 'xymc',
						index : 'xydm',
						width : '10%'
					}, {
						label : 'רҵ',
						name : 'zymc',
						index : 'zydm',
						width : '15%'
					},{
						label : '�༶',
						name : 'bjmc',
						index : 'bjdm',
						width : '15%'
					}],
					sortname : "xn,xq",
					sortorder : "desc"
				}
				var map = getSuperSearch();
				map["sqxn"] = jQuery("#sqxn").val();
				map["sqxq"] = jQuery("#sqxq").val();
				gridSetting["params"] = map;
				jQuery("#dataTable").initGrid(gridSetting);
				jQuery("#sqxn").change(function(){
					searchRs();
				})
				jQuery("#sqxq").change(function(){
					searchRs();
				})
			})
			function searchRs() {
				var map = getSuperSearch();
				map["sqxn"] = jQuery("#sqxn").val();
				map["sqxq"] = jQuery("#sqxq").val();
				jQuery("#dataTable").reloadGrid(map);
            }
		</script>
	</head>
  	<body >
	<html:form action="/xsxxDyxjCssz" method="post" styleId="csszForm">
			<html:hidden property="id" styleId="id"/>
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>${title }</a>
				</p>
			</div>
			<div class="tab">
			<table width="100%" border="0" class="formlist">
				<thead>
					<tr>
						<th colspan="2"><span>��������</span></th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th width="40%"><span class="red">*</span>���뿪��</th>
						<td>
							<logic:present name="onOff">
								<logic:iterate id="o" name="onOff">
									<html:radio property="sqkg" value="${o.dm}">${o.mc}</html:radio>
								</logic:iterate>
							</logic:present>
						</td>
					</tr>
					<tr>
						<th>
							��ʼʱ��
						</th>
						<td>
							<html:text  property="sqkssj" styleId="sqkssj"
										onfocus="showCalendar('sqkssj','y-mm-dd',true,'sqjssj');" 
										readonly="true"></html:text>
								��
							<html:text  property="sqjssj" styleId="sqjssj"
										onfocus="showCalendar('sqjssj','y-mm-dd',false,'sqkssj');" 
								 		readonly="true"></html:text>
									
						</td>
					</tr>
					<tr>
						<th>
							<span class="red">*</span>�������
						</th>
						<td>
							<html:select property="splc" styleId="splc">
								<html:option value=""></html:option>
								<html:options collection="shlcList" property="splc" labelProperty="lcxx"/>
							</html:select>
						</td>
					</tr>
					<tr>
						<th><span class="red">*</span>����ѧ��</th>
						<td>
							<html:select property="sqxn" styleId="sqxn">
								<html:options collection="sqxnList" property="xn" labelProperty="xn"/>
							</html:select>
						</td>
					</tr>
					<tr>
						<th><span class="red">*</span>����ѧ��</th>
						<td>
							<html:select property="sqxq" styleId="sqxq">
								<html:option value=""></html:option>
								<html:options collection="sqxqList" property="xqdm" labelProperty="xqmc"/>
							</html:select>	
						</td>
					</tr>
				</tbody>
				<tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
			          <div class="btn">		
			          	<logic:equal name="writeAble" value="yes">		            
							<button type="button" class="button" onclick="saveForm();" >
								�� ��
							</button>
						</logic:equal>
			          </div>
			        </td>
			      </tr>
			    </tfoot>
			</table>
			</div>
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
						<li>
							<a href="javascript:void(0);" class="btn_zj" onclick="add();return false;"  >����</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="del();return false" class="btn_sc" >ɾ��</a>
						</li>
						</logic:equal>
						<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">����</a></li>	
					</ul>
				</div>
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
			
		</html:form>
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>�ʸ��ά���б�&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
		
	</body>
</html>
