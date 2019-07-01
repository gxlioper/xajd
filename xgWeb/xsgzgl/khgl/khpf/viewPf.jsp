<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>	
		<script type="text/javascript" src="xsgzgl/khgl/khpf/js/khpf.js"></script>	
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script language="javascript" >
			var gridSetting = {
					caption : "���˶����б�",
					pager : "pager",
					url : "khglKhpf.do?method=viewPf&type=query",
					multiselect:false,
					radioselect:true
				}

			var colList = [ 
					   {label : 'zbmxid',name : 'zbmxid',index : 'zbmxid',key:true,hidden:true},
					   {label : 'fzlx',name : 'fzlx',index : 'fzlx',hidden:true},
					   {label : 'pflx',name : 'pflx',index : 'pflx',hidden:true},
					   {label : 'fzqj',name : 'fzqj',index : 'fzqj',hidden:true},
					   {label : 'fzzgf',name : 'fzzgf',index : 'fzzgf',hidden:true},
					   {label : 'fzzdf',name : 'fzzdf',index : 'fzzdf',hidden:true},
                		{label : 'dxq',name : 'dxq',index : 'dxq',hidden:true},
					   <logic:notEqual name="xxdm" value="11527">
					   {label : '��������',name : 'khnr',index : 'khnr',width : '58%'}, 
					   </logic:notEqual>
					   {label : 'һ��ָ��',name : 'yjzb',index : 'yjzb',width : '16%'}, 
					   {label : '����ָ��',name : 'ejzb',index : 'ejzb',width : '16%'}, 
					   {label : '��ֵ',name : 'fzqj',index : 'fzqj',width : '8%'}, 
					   {label : '��������',name : 'pflxmc',index : 'pflxmc',width : '8%'},
						<logic:equal name="model" property="pflx" value="bzpf">
						{label : '������',name : 'zpf',index : 'zpf',width : '8%'},
						</logic:equal>
						<logic:equal name="model" property="pflx" value="bzrpf">
						{label : '������',name : 'zpf',index : 'zpf',width : '8%'},
						{label : '��������',name : 'bzpf',index : 'bzpf',width : '8%'},
						</logic:equal>
					   {label : '�÷�',name : 'fs',index : 'fs',width : '8%',formatter:fsLink},
					   {label : '��ע',name : 'bz',index : 'bz',width : '8%'}
					   
				   ];

			gridSetting["colList"] = colList;

			jQuery(function() {
				var map = {xmid:jQuery("#xmid").val(),khbid:jQuery("#khbid").val(),khdxr:jQuery("#khdxr").val(),xmszid:jQuery("#xmszid").val(),pfr:jQuery("#pfr").val(),pflx:jQuery("#pflx").val()};
				gridSetting["params"] = map;
				jQuery.ajaxSetup({async:false});
				jQuery("#dataTable").initGrid(gridSetting);
				viewTotal();
				autoRowSpan();
				jQuery.ajaxSetup({async:true});
			});

			function fsLink(cellValue,rowObject){
			if(null==cellValue||"null"==cellValue){
			cellValue="";
			}
				if( "2"==rowObject.pflx ){
					return "<span style='color: red' class='data-type' lx='"+rowObject.pflx+"' >"+cellValue+"</span>";
				}else{
					return "<span class='data-type' lx='"+rowObject.pflx+"' >"+cellValue+"</span>";
				}
			}
						
		</script>
	</head>
	<body>
	<html:form action="/khglKhpf" method="post">
	<%@ include file="/comm/hiddenValue.jsp"%>
	<input type="hidden" name="xmid" id="xmid" value=${xmInfo.xmid} />
	<input type="hidden" name="xmszid" id="xmszid" value=${model.xmszid} />
	<input type="hidden" name="khbid" id="khbid" value=${model.khbid} />
	<input type="hidden" name="pfr" id="pfr" value=${model.pfr} />
	<input type="hidden" name="khdxr" id="khdxr" value=${ryInfo.ry} />
	<input type="hidden" name="pflx" id="pflx" value=${model.pflx} />
	</html:form>
	<div class="main_box">
		<h3 class=datetitle_01>
			<span>
			��Ŀ:<font color="blue">${xmInfo.xmmc}</font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			������:<font color="blue">${ryInfo.xm}</font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			���ڲ���:<font color="blue">${ryInfo.xymc}</font>
			</span>
		</h3>
		<div class="con_overlfow">
			<table id="dataTable" ></table>
			<table width="100%" border="0" class="formlist">
			<!-- ���������Ի� -->
				<logic:equal value="12309" name="xxdm">
				<!-- ���˶���Ϊ��ʦ -->
					<logic:equal value="2" name="khlx">
						<!-- ��¼�û�Ϊѧ�� -->					
						<logic:equal value="stu" name="userStatus">
							<tr>					
								<th width="20%">
									�Ը���Ա����ͽ���								
								</th>
								<td colspan="3">
									${model.yjjy}
								</td>
							</tr>										
						</logic:equal>					
					</logic:equal>
				</logic:equal>
				
				<!-- �㽭��ҵְҵ����ѧԺ���Ի� -->
				<logic:equal value="33333" name="xxdm">
				<!-- ���˶���Ϊ��ʦ -->
					<logic:equal value="2" name="khlx">
						<!-- ��¼�û�Ϊѧ�� -->					
						<logic:equal value="1" name="model" property="pflx">
							<tr>					
								<th width="20%">
									�԰༶�������<br/>�Ľ�������								
								</th>
								<td colspan="3">
									${model.yjjy}
								</td>
							</tr>										
						</logic:equal>					
					</logic:equal>
				</logic:equal>
			</table>
		</div>
		<div style="height: 30px"></div>
		<div>
			<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
				<tfoot>
					<tr>
						<td colspan="4">
							<div class="bz" style="margin-left:30px;">
								<b> �÷ֺϼƣ�<span class="blue" id="total" >0</span></b>
							</div>
							<div class="btn">
								<button type="button"  onclick="iFClose();" id="buttonClose">
									�� ��
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
			</table>
		</div>
	</div>		
	</body>
</html>
