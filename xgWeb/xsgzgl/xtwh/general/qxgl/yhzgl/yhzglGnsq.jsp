<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>

	<!--ztree相关-->
	<link rel="stylesheet" type="text/css" href="assets/plugins/ztree/css/tree.css" />
	<link rel="stylesheet" type="text/css" href="assets/plugins/ztree/css/zTreeStyle.css" />
	<style type="text/css">
		ul.ztree {
			width: 100%;
			height: 580px;
			overflow-y: auto;
			border-color: rgb(176, 203, 224);
		}

	</style>
	<script src="assets/plugins/ztree/js/jquery.ztree.all-min.js"></script>

	<script language="javascript" src="js/qtsjFunction.js"></script>
	<script type='text/javascript' src="js/comm/message.js"></script>
	<script type="text/javascript">
        var zTreeObj;
        jQuery(function(){
            //初始化菜单树
            jQuery.post('xtwh_qxgl_yhzgl.do?method=getAllDjGnmkList', {pkValue:'${rs.zdm }'}, function(zNodes) {
                var setting = {
                    view:{showIcon:false},
                    check: {enable: true},
                    data: {simpleData: {enable: true}},
                    callback: {
                        onCheck: zTreeOnCheck
                    }
                };

                // 生成
                zTreeObj = jQuery.fn.zTree.init(jQuery("#menuTree"), setting, zNodes);
                zTreeObj.expandAll(true);

                var nodes = zTreeObj.getNodesByFilter(function (node) {
                    return !node.isParent;
                });

                jQuery.each(nodes,function(i,item){
                    var id = item.tId;
                    var dxq = item.dxq;
                    jQuery("#"+id).append('&nbsp;&nbsp;<input type="radio" name="'+id+'" value="1" '+ (dxq == 1 ? "checked" : "") +' onchange="chmod(this)"/> 可写  ' +
                        '&nbsp;<input type="radio" name="'+id+'" value="0" '+ (dxq == 0 ? "checked" : "") +' onchange="chmod(this)"/> 只读');
                });

            },'JSON');
        });

        //用于捕获 checkbox / radio 被勾选 或 取消勾选的事件回调函数
        //如果是叶子节点 checkbox check时radio默认写 checkbox取消check时radio也取消check
        function zTreeOnCheck(event, treeId, treeNode) {

            treeNodeOncheck(treeNode);
        };

        function treeNodeOncheck(treeNode) {
            if(treeNode.isParent){
                children = treeNode.children;
                jQuery.each(children,function(i,item){
                    treeNodeOncheck(item);
                });
            }else{
                if(treeNode.checked){
                    jQuery("#"+treeNode.tId +" input[value=1]").prop("checked",true);
                    treeNode.dxq = '1';
                }else{
                    jQuery("#"+treeNode.tId +" input[value=0]").prop("checked",false);
                    jQuery("#"+treeNode.tId +" input[value=1]").prop("checked",false);
                }

            }
        }

        function chmod(v) {

            var dxq = jQuery(v).val();
            var node = zTreeObj.getNodeByTId(jQuery(v).parent().attr("id"));
            node.dxq = dxq;
            zTreeObj.checkNode(node,true,true);
        }

        //读写权
        function fun_dxq(dxqValue){

            var nodes = zTreeObj.getNodesByFilter(function (node) {
                return !node.isParent;
            });

            jQuery.each(nodes,function(i,item){
                item.dxq = dxqValue;
                zTreeObj.checkNode(item,true,true);
            });

            jQuery("input:radio").each(function (i,item) {
                if(jQuery(this).val() == dxqValue){
                    jQuery(this).prop("checked",true);
                }
            });
        }

	function viewYhxx(zdm){
		showTopWin("xtwh_qxgl_yhzgl.do?method=yhzglViewYhxx&zdm="+zdm,800,450);
	}

	function checkout(){

		var nodes = zTreeObj.getChangeCheckedNodes();

		if(nodes.length > 0){
			showConfirmDivLayer("您尚未保存权限修改，是否需要保存修改？",{
				"okFun":save,
				"cancelFun":function(){
					refreshForm('/xgxt/xtwh_qxgl_yhzglManage.do');
				}});
		}else{
			refreshForm('/xgxt/xtwh_qxgl_yhzglManage.do');
		}
	}

	function save(){
        BatAlert.showTips("正在保存，请稍后．．．");
		var checkedNodes = zTreeObj.getCheckedNodes(true);
		var yhzgnqxList = [];
		for(var i=0;i < checkedNodes.length;i++){
			yhzgnqxList[i] = {gnmkdm:checkedNodes[i]["id"],dxq:checkedNodes[i]["dxq"]};
		}

		jQuery.post('xtwh_qxgl_yhzgl.do?method=yhzGnsqSave', {pkValue:'${rs.zdm}',yhzgnqx:JSON.stringify(yhzgnqxList)}, function(data) {

			if(data["message"]=="保存成功！"){
                BatAlert.closeTips();
				showAlert(data["message"],{},{"clkFun":function(){
					refreshForm('/xgxt/xtwh_qxgl_yhzglManage.do');
				}});
			}else{
				showAlert(data["message"]);
			}
		},'JSON');
	}
	</script>
	</head>
	
	<body>
		<html:form action="/xtwh_qxgl_yhzgl">
			<input type="hidden" name="userName" id="userName" value="${userName }"/>
			<input type="hidden" name="pkValue" id="zdm" value="${rs.zdm}" />
			
			<div class="tab_cur" id="jd">
				<p class="location">
					<em>您的当前位置:</em><a>系统维护-权限维护-用户组授权</a>
				</p>
			</div>
			<div class="toolbox">
				<div class="buttonbox">	
					<ul>
						<li style="list-style: none"><a href="#" class="btn_fh" id="btn_fh" onclick="checkout();">返回</a></li>
						<li style="list-style: none"><a href="#" class="btn_ccg" id="btn_ccg" onclick="save();return false;">保存</a></li>
						<li style="list-style: none"><a href="#" class="btn_sh" id="btn_sh" onclick="fun_dxq('1');">全部可写</a></li>
						<li style="list-style: none"><a href="#" class="btn_yl" id="btn_yl" onclick="fun_dxq('0');">全部只读</a></li>
					</ul>
				</div>
			</div>		
		    
		 	<table style="" border="0" class="formlist">
				<thead>
					<tr>
						<th colspan="4">
							<span>组信息</span>
						</th>
					</tr>
				</thead>
				
				<tbody>
					<tr>
						<th width="15%">组名称</th>
						<td width="30%">
							${rs.zmc }
						</td>
						<th width="15%">用户数</th>
						<td>
							<a href="javascript:viewYhxx('${rs.zdm }')" style="color:blue;"><b>${rs.num }</b></a>
						</td>
					</tr>					
				</tbody>		
		</table>

		<div>
			<ul id="menuTree" class="ztree"></ul>
		</div>

		<div id="tmpdiv1"></div>
		</html:form>
		
	</body>
</html>
