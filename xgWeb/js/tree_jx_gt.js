function tree (a_items, a_template) {

	this.a_tpl      = a_template;
	this.a_config   = a_items;
	this.o_root     = this;
	this.a_index    = [];
	this.o_selected = null;
	this.n_depth    = -1;
	
	var o_icone = new Image(),
		o_iconl = new Image();
	o_icone.src = a_template['icon_e'];
	o_iconl.src = a_template['icon_l'];
	a_template['im_e'] = o_icone;
	a_template['im_l'] = o_iconl;
	for (var i = 0; i < 64; i++)
		if (a_template['icon_' + i]) {
			var o_icon = new Image();
			a_template['im_' + i] = o_icon;
			o_icon.src = a_template['icon_' + i];
		}
	
	this.toggle = function (n_id) { var o_item = this.a_index[n_id];o_item.open(o_item.b_opened)};
	this.select = function (n_id) { return this.a_index[n_id].select(); };

	this.a_children = [];
	for (var i = 0; i < a_items.length; i++)
		new tree_item(this, i);

	this.n_id = trees.length;
	trees[this.n_id] = this;
	
	for (var i = 0; i < this.a_children.length; i++) {
		document.write(this.a_children[i].init());
		this.a_children[i].open(true);
	}
	this.a_children[0].open();
	this.a_children[0].select();
}
function tree_item (o_parent, n_order) {
	this.n_depth  = o_parent.n_depth + 1;
	this.a_config = o_parent.a_config[n_order + (this.n_depth ? 2 : 0)];
	if (!this.a_config) return;

	this.o_root    = o_parent.o_root;
	this.o_parent  = o_parent;
	this.n_order   = n_order;
	this.b_opened  = !this.n_depth;

	this.n_id = this.o_root.a_index.length;
	this.o_root.a_index[this.n_id] = this;
	o_parent.a_children[n_order] = this;

	this.a_children = [];
	for (var i = 0; i < this.a_config.length - 2; i++)
		new tree_item(this, i);

	this.get_icon = item_get_icon;
	this.open     = item_open;
	this.select   = item_select;
	this.init     = item_init;
	this.is_last  = function () { return this.n_order == this.o_parent.a_children.length - 1 };
}

function item_open (b_close) {
	var o_idiv = $('i_div' + this.o_root.n_id + '_' + this.n_id);
	if (!o_idiv) return;
	
	if (!o_idiv.innerHTML) {
		var a_children = [];
		for (var i = 0; i < this.a_children.length; i++)
			a_children[i]= this.a_children[i].init();
		o_idiv.innerHTML = a_children.join('');
	}
	o_idiv.style.display = (b_close ? 'none' : 'block');
	
	this.b_opened = !b_close;
	var o_jicon = document.images['j_img' + this.o_root.n_id + '_' + this.n_id],
		o_iicon = document.images['i_img' + this.o_root.n_id + '_' + this.n_id];
	if (o_jicon) o_jicon.src = this.get_icon(true);
	if (o_iicon) o_iicon.src = this.get_icon();
}

function item_select (b_deselect) {
	if (!b_deselect) {
		var o_olditem = this.o_root.o_selected;
		this.o_root.o_selected = this;
		if (o_olditem) o_olditem.select(true);
	}
	var o_iicon = document.images['i_img' + this.o_root.n_id + '_' + this.n_id];
	if (o_iicon) o_iicon.src = this.get_icon();
	$('i_txt' + this.o_root.n_id + '_' + this.n_id).style.fontWeight = b_deselect ? 'normal' : 'bold';
	return Boolean(this.a_config[1]);
}

function item_init () {
	var a_offset = [],
		o_current_item = this.o_parent;
	for (var i = this.n_depth; i > 1; i--) {
		a_offset[i] = '<img src="' + this.o_root.a_tpl[o_current_item.is_last() ? 'icon_e' : 'icon_l'] + '" border="0" align="absbottom">';
		o_current_item = o_current_item.o_parent;
	}
	return '<table cellpadding="0" cellspacing="0" style="border:0px"><tr><td nowrap style="border:0px">' + 
			(
				this.n_depth 
				? a_offset.join('') + 
									(
										this.a_children.length
										? '<a href="javascript: trees[' + this.o_root.n_id + '].toggle(' + this.n_id + ')"><img src="' + this.get_icon(true) + '" border="0" align="absbottom" name="j_img' + this.o_root.n_id + '_' + this.n_id + '"></a>'
										: '<img src="' + this.get_icon(true) + '" style="border:0px" align="absbottom">'
									) 
				: ''
			) + 
			'<a title="' + this.a_config[0]+'" href="javascript:jzOn(\'' + this.a_config[1] +'\');" onmousedown="trees[' + this.o_root.n_id + '].toggle(' + this.n_id + ');return trees[' + this.o_root.n_id + '].select(' + this.n_id + ');" target="'+this.o_root.a_tpl['target']+'"  class="t' + this.o_root.n_id + 'i" id="i_txt' + this.o_root.n_id + '_' + this.n_id + '"><img src="' + this.get_icon() + '" border="0" align="absbottom" name="i_img' + this.o_root.n_id + '_' + this.n_id + '" class="t' + this.o_root.n_id + 'im">' + this.a_config[0] + '</a>' +
			'</td></tr></table>' + 
			(
				this.a_children.length 
				? '<div id="i_div' + this.o_root.n_id + '_' + this.n_id + '" style="display:none"></div>' 
				: ''
			);
}

function item_get_icon (b_junction) {
//	alert('icon_' + ((this.n_depth ? 0 : 32) + (this.a_children.length ? 16 : 0) + (this.a_children.length && this.b_opened ? 8 : 0) + (!b_junction && this.o_root.o_selected == this ? 4 : 0) + (b_junction ? 2 : 0) + (b_junction && this.is_last() ? 1 : 0)));
	return this.o_root.a_tpl['icon_' + ((this.n_depth ? 0 : 32) + (this.a_children.length ? 16 : 0) + (this.a_children.length && this.b_opened ? 8 : 0) + (!b_junction && this.o_root.o_selected == this ? 4 : 0) + (b_junction ? 2 : 0) + (b_junction && this.is_last() ? 1 : 0))];
}

var trees = [];

function jzOn(cbVal){
	document.forms[0].cbVal.value = cbVal;
	var njS = document.forms[0].nj.value;
	getOtherData.getJxjzDate(cbVal,njS,function(data){
       if(data!=null){
        jxjzdm.innerText=data[0];
        document.getElementById("jxjzdmV").value=data[0];
       	jxjzmc.innerText=data[1];
//      jxjzdj.innerText=data[2];
       	jxjzdjmc.innerText=data[3];
       	jxzdy.innerText=data[4];
       	document.getElementById("jxzdyV").value=data[4];
       	jxjgmc.innerText=data[5];
       	document.getElementById("jxjgmcV").value=data[5];
       	jxssjz.innerText=data[6];
       	jxbz.innerText=data[7];
       	xb.innerText=data[8];
       	document.getElementById("xbV").value=data[8];
       	xn.innerText=data[9];
       }
    });
}
