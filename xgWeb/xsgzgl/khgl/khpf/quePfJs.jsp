<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>	
		<script type="text/javascript" src="xsgzgl/khgl/khpf/js/khpf.js"></script>	
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" >
			var gridSetting = {
					caption : "���˶����б�",
					pager : "pager",
					radioselect:true,
					url : "khglKhpf.do?method=quePf&type=query",
					params:{"xmid":jQuery("#xmid").val(),"khbid":jQuery("#khbid").val()},
					colList : [ 
					   {label : 'xmid',name : 'xmid',index : 'xmid' ,hidden:true}, 
					   {label : 'khbid',name : 'khbid',index : 'khbid' ,hidden:true},
					   {label : 'khdxr',name : 'khdxr',index : 'khdxr' ,hidden:true}, 
					   {label : 'xmszid',name : 'xmszid',index : 'xmszid' ,hidden:true},
					   {label : 'ְ����',name : 'zgh',index : 'zgh',width : '20%'}, 
					   {label : '����',name : 'xm',index : 'xm',width : '15%'}, 
					   {label : '�Ա�',name : 'xbmc',index : 'xbmc',width : '15%'}, 
					   {label : '���ڲ���',name : 'bmmc',index : 'bmmc',width : '20%'}
					   ],
					sortname : "zgh",
					sortorder : "asc"
				}

			var gridSetting1 = {
					caption : "���˶����б�",
					pager : "pager",
					radioselect:false,
					url : "khglKhpf.do?method=quePf&type=query",
					colList : [
					   {label : 'xmid',name : 'xmid',index : 'xmid' ,hidden:true}, 
					   {label : 'khbid',name : 'khbid',index : 'khbid' ,hidden:true},
					   {label : 'khdxr',name : 'khdxr',index : 'khdxr' ,hidden:true}, 
					   {label : 'xmszid',name : 'xmszid',index : 'xmszid' ,hidden:true},
					   {label : 'ְ����',name : 'zgh',index : 'zgh',width : '20%'}, 
					   {label : '����',name : 'xm',index : 'xm',width : '15%'}, 
					   {label : '�Ա�',name : 'xbmc',index : 'xbmc',width : '15%'}, 
					   {label : '���ڲ���',name : 'bmmc',index : 'bmmc',width : '20%'},
					   {label : '�ܷ�',name : 'zf',index : 'zf',width : '20%'}
					   ],
					sortname : "zgh",
					sortorder : "asc"
				}

			jQuery(function() {
				var sftj = jQuery("#sftj").val();
				var map = {xmid:jQuery("#xmid").val() ,khbid:jQuery("#khbid").val()};
				
				gridSetting["params"] = map;
				
				if(""==sftj||"2"==sftj){
					jQuery("#dataTable").initGrid(gridSetting);
				}else{
					jQuery("#dataTable").initGrid(gridSetting1);
				}
			});

			//�߼���ѯ
			function searchRs(){
				var sftj = jQuery("#sftj").val();
				var map = getSuperSearch();
				map["xmid"] = jQuery("#xmid").val();
				map["khbid"] = jQuery("#khbid").val();
				map["sftj"] = sftj;
				gridSetting["params"] = map;
				if(""==sftj||"2"==sftj){
					jQuery("#dataTable").initGrid(gridSetting);
				}else{
					jQuery("#dataTable").initGrid(gridSetting1);
				}
			}
						
		</script>
	</head>
	<body>
	<html:form action="/khglKhpf" method="post">
	<%@ include file="/comm/hiddenValue.jsp"%>
	<input type="hidden" name="xmid" id="xmid" value=${xmInfo.xmid} />
	<input type="hidden" name="khbid" id="khbid" value=${xmInfo.khbid} />
	<input type="hidden" name="sftj" id="sftj" value="2" />
	<logic:equal value="12309" name="xxdm">
		<input type="hidden" name="khlx" id="khlx" value=${xmInfo.khlx} />
	</logic:equal>
	<logic:equal value="33333" name="xxdm">
		<input type="hidden" name="khlx" id="khlx" value=${xmInfo.khlx} />
	</logic:equal>
	<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
					<logic:notEqual value="11527" name="xxdm">
						<li><a href="javascript:void(0);" onclick="document.location.href='khgl_khpf.do';" class="btn_fh">����</a></li>
					</logic:notEqual>
						<li id="li_pf"><a href="javascript:void(0);" onclick="addPf();" class="btn_xg">����</a></li>
						<li id="li_ck" style="display: none;"><a href="javascript:void(0);" onclick="viewPf();" class="btn_ck">�鿴</a></li>
						<logic:equal value="11527" name="xxdm">
						<li id="li_xz" style="display: none;"><a href="javascript:void(0);" onclick="getCpcjWord();" class="btn_xg">�����ɼ�����</a></li>
						</logic:equal>
						<logic:equal value="13022" name="xxdm">
							<li id="li_qx" style="display:none;"> 
								<a href="javascript:void(0);" onclick="cancelTjRecord();" class="btn_qxsh">ȡ���ύ</a>
							</li>
						</logic:equal>
					</ul>
				</div>
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
				<div class="comp_title" id="comp_title">
			      <ul style="width:90%" id="tabUl">
					<li class="ha"><a href="javascript:void(0);" onclick="selectTab(this,'2');"><span>������Ա</span></a></li>
			        <li><a href="javascript:void(0);" onclick="selectTab(this,'1');"><span>������Ա</span></a></li>
			      </ul>
			    </div>
			</div>
	</html:form>
			<div class="main_box">
					<h3 class=datetitle_01>
						<span>��Ŀ��<font style="color: blue"> ${xmInfo.xmmc}</font> 
						���˱�<font style="color: blue"> ${xmInfo.khbmc}</font></span>
					</h3>
					<div class="con_overlfow">
						<table id="dataTable" ></table>
						<div id="pager"></div>
					</div>
				</div>		
	</body>
</html>
