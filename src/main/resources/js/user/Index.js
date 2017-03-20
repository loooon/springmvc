/*

 ____ ___ _   _  ____    _    _   _ ____  _____ ____
 |  _ \_ _| \ | |/ ___|  / \  | \ | / ___|| ____/ ___|
 | |_) | ||  \| | |  _  / _ \ |  \| \___ \|  _|| |
 |  __/| || |\  | |_| |/ ___ \| |\  |___) | |__| |___
 |_|  |___|_| \_|\____/_/   \_\_| \_|____/|_____\____|


 */
function Map() {
    this.container = new Object
}
function SlideButton(t, e) {
    this.btn = t, this.content = e, this.mouseOn = !1;
    var i = this;
    this.btn.on({
        click: function () {
            i.content.toggle()
        }, mouseenter: function () {
            i.mouseOn = !0, i.content.show()
        }, mouseleave: function () {
            i.mouseOn = !1, i.hideContent()
        }
    }), this.content.on({
        mouseenter: function () {
            i.mouseOn = !0
        }, mouseleave: function () {
            i.mouseOn = !1, i.hideContent()
        }
    })
}
function SelectHandler(t, e, i) {
    this.items = t;
    var n = this;
    t.click(function (s) {
        if (s.preventDefault(), $(this).hasClass("current")) {
            if (i && 1 == t.filter(".current").length)return !1;
            $(this).removeClass("current")
        } else e || $(this).siblings().removeClass("current"), $(this).addClass("current");
        return n.selected($(this)), !0
    })
}
function initPlaceHolder() {
    $("[placeholder]").each(function () {
        var t = $(this), e = function (t, e) {
            e ? t.css("display", "none").attr("disabled", !0) : t.css("display", "").attr("disabled", !1)
        };
        if ("password" != t.attr("type")) "" == t.val() && t.val(t.attr("placeholder")), t.css("color", "silver").focus(function () {
            $.trim(t.val()) == t.attr("placeholder") && t.css("color", t.css("color", "")).val("")
        }).blur(function () {
            "" == $.trim(t.val()) && t.css("color", "silver").val(t.attr("placeholder"))
        }); else {
            var i = $(t[0].outerHTML.replace(/password/g, "text"));
            i.removeAttr("name").removeAttr("id"), i.css("color", "silver").val(t.attr("placeholder")), e(t, !0), t.parent().append(i), i.focus(function () {
                e(t, !1), e(i, !0), t.val("").focus()
            }), t.blur(function () {
                "" == $.trim(t.val()) && (e(i, !1), e(t, !0))
            })
        }
    })
}
function Popover(t, e) {
    this.obj = t, t.tip = e, this.isDefaultPip = !0, /left|right|top|bottom|innerLeft|innerRight/g.test(e) || 1 != $(e).length ? t.pop = $("<div class='form-msg'></div>") : (t.pop = $(e), this.isDefaultPip = !1)
}
function malert(t, e, i) {
    var n = .2, s = 9999;
    null != i && (n = null == i.opacity ? n : i.opacity, s = null == i["z-index"] ? s : i["z-index"]);
    var o = "<div id='malert_mask' style='position:absolute;display:none;z-index:" + s + ";top:0;left:0; width:100%; height:100%;background-color:black;filter:alpha(opacity=" + 100 * n + ");-moz-opacity:" + n + ";opacity:" + n + ";'></div>";
    0 != $("#malert_mask").length && $("#malert_mask").remove(), $("body").append(o);
    var r = $("#malert_mask"), a = $(window).height() - t.css({position: "absolute", "z-index": s + 1}).height();
    0 > a && (a = 100), t.css({
        top: a / 3 + "px",
        left: "50%",
        "margin-left": "-" + t.width() / 2 + "px"
    }), t.show(), r.show(), r.tap(function () {
        t.hide(), r.hide(), r.remove()
    }), e && e.tap(function () {
        t.hide(), r.hide(), r.remove()
    })
}
function FormItem(t, e) {
    this.obj = t, this.type = t.attr("type"), this.name = t.attr("name"), this.placeholder = t.attr("placeholder"), this.des = t.attr("des"), this.ajax = t.attr("ajax"), this.hastip = t.attr("hastip") ? t.attr("ajaxmsg") : "", this.ajaxMsg = t.attr("ajaxmsg") ? t.attr("ajaxmsg") : "验证失败", this.compare = t.attr("compare"), this.obj.blur = this.onblur(), this.errors = new Array, this.popover = new Popover(t, void 0 == t.attr("msgtip") ? "right" : t.attr("msgtip")), this.mpopover = e ? e : "", this.obj.keydown = this.onkeydown(), this.obj.FormItem = this
}
function mForm(t, e) {
    this.obj = t, this.isAjax = !1;
    var i = this;
    t.submit(function (e) {
        for (var n = 0; n < t.items.length; n++) {
            var s = t.items[n];
            if (s.validate(), s.errors.length > 0)return e.preventDefault(), !1
        }
        return i.beforeSubmit() ? i.isAjax ? ($.ajax({
                    data: i.obj.serialize(),
                    type: "POST",
                    timeout: 1e4,
                    dataType: "json",
                    url: i.obj.attr("action"),
                    success: function (t) {
                        i.afterSubmit(t)
                    },
                    error: function () {
                        i.submitError()
                    },
                    complete: function () {
                        i.completeSubmit()
                    },
                    beforeSend: function () {
                        i.loadDate()
                    }
                }), e.preventDefault(), !1) : !0 : (e.preventDefault(), !1)
    }), this.initItems = function () {
        t.items = new Array, t.find("[name]").each(function () {
            t.items.push(new FormItem($(this), e))
        })
    }, this.initItems()
}
function ContentUtil(t) {
    this.obj = t
}
function URLHandler(t) {
    this.url = t, this.uri = t, this.basePath = this.uri, this.absolutePath = this.uri, this.params = new Map, this.init()
}
function PageHandler(t) {
    this.init(t)
}
function SelectDiv(t, e) {
    this.items = t, this.doms = e, t.click(function (t) {
        $(t.currentTarget).index(), e.hide(), e.eq($(t.currentTarget).index()).show()
    })
}
function TipShow(t, e) {
    this.items = t, this.doms = e, this.init()
}
function SelectItem(t, e, i) {
    this.items = t, t.click(function (n) {
        if (n.preventDefault(), $(this).hasClass("current")) {
            if (i && 1 == t.filter(".current").length)return !1;
            $(this).removeClass("current")
        } else e || this.items.removeClass("current"), $(n.currentTarget).addClass("current")
    }.bind(this))
}
function SelectItemHref(t, e, i) {
    this.items = t, t.click(function (n) {
        if (n.preventDefault(), $(this).hasClass("current")) {
            if (i && 1 == t.filter(".current").length)return !1;
            $(this).removeClass("current")
        } else e || this.items.removeClass("current"), window.location.href = $(n.currentTarget).attr("hr"), $(n.currentTarget).addClass("current")
    }.bind(this))
}

/**
 * 导航栏工具类
 */
function NavHandler(items){
    this.items = items;
    items = items || $("[data-nav]");
    for (var i=0; i<items.length; i++){
        var item = items.eq(i);
        item.removeClass('current');
        var re = new RegExp(item.attr("data-nav"));
        if(re.test(window.location.href)){
            item.addClass("current");
            return;
        }
    }
}
NavHandler.prototype.select = function (num){
    this.items.removeClass("current");
    this.items.eq(num).addClass("current");
};



var RSF = {
    regist: function (t, e) {
        e = e || function () {
            };
        for (var i = t.split("."), n = window, s = 0; s < i.length; s++) {
            if (s != i.length - 1) "undefined" == typeof n[i[s]] && (n[i[s]] = {}); else {
                if ("undefined" != typeof n[i[s]])throw t + "命名空间重复,请修改！";
                n[i[s]] = e
            }
            n = n[i[s]]
        }
    }, log: function (t) {
        "undefined" == typeof console || console.log(t)
    }, callIndex: 0, callBacks: [], addCallback: function (t) {
        var e = ++this.callIndex;
        return this.callBacks[e] = t, e
    }, excuteCallback: function (t) {
        this.callBacks[t] && this.callBacks[t]()
    }, get_json_cross_domain: function (t, e, i) {
        var n = this.addCallback(i);
        t += -1 == t.indexOf("?") ? "?" : "&";
        for (k in e)t += k + "=" + encodeURIComponent(e[k]) + "&";
        t += "callback=" + encodeURIComponent("RSF.callBacks[" + n + "]");
        var s = document.createElement("script");
        s.type = "text/javascript", s.async = !0, s.src = t, document.body.appendChild(s)
    }, getKeys: function (t) {
        var e = [];
        for (k in t)e.push(k);
        return e
    }, copyJson: function (t) {
        var e = {};
        for (k in t)e[k] = t[k];
        return e
    }, deepCopyJson: function (t, e) {
        if ("object" == typeof t) {
            if (t instanceof Array) {
                e || (e = new Array);
                for (var i = t.length, n = 0; i > n; n++)"object" != typeof t[n] ? e[n] = t[n] : (e[n] = t[n] instanceof Array ? new Array : new Object, this.deepCopyJson(t[n], e[n]))
            } else {
                e || (e = new Object);
                for (var s in t)"object" != typeof t[n] ? e[s] = t[s] : (e[s] = t[s] instanceof Array ? new Array : new Object, this.deepCopyJson(t[s], e[s]))
            }
            return e
        }
        return t
    }, getCookie: function (t) {
        for (var e = document.cookie.split("; "), i = 0; i < e.length; i++) {
            var n = e[i].split("=");
            if (n[0] == t)return n[1] || ""
        }
        return ""
    }, setCookie: function (t, e, i, n, s, o) {
        var r = new Date;
        r.setTime(r.getTime() + i), document.cookie = escape(t) + "=" + escape(e) + (r ? "; expires=" + r.toGMTString() : "") + (n ? "; path=" + n : "/") + (s ? "; domain=" + s : "") + (o ? "; secure" : "")
    }, getParent: function (t, e, i) {
        t.extend = e, t.extend.apply(t, i), t.extend = null, delete t.extend
    }
};
Function.prototype.bind || (Function.prototype.bind = function (t) {
    var e = this;
    return function () {
        return e.apply(t, arguments)
    }
}), Array.prototype.each = function (t) {
    for (var e = 0; e < this.length; e++)t(e, this[e])
}, Array.prototype.removeAtIndex = function (t) {
    return this.slice(t, 1)
}, String.prototype.reverse = function () {
    return this.split("").reverse().join("")
}, RSF.regist("CreditFrame"), CreditFrame = function () {
    this.init = function () {
    }
}, RSF.regist("Util"), Map.prototype = {
    put: function (t, e) {
        this.container[t] = e
    }, get: function (t) {
        return this.container[t]
    }, keySet: function () {
        var t = new Array, e = 0;
        for (var i in this.container)"extend" != i && (t[e] = i, e++);
        return t
    }, size: function () {
        var t = 0;
        for (var e in this.container)"extend" != e && t++;
        return t
    }, remove: function (t) {
        delete this.container[t]
    }
}, Date.prototype.format = function (t) {
    var e = {
        "M+": this.getMonth() + 1,
        "d+": this.getDate(),
        "h+": this.getHours(),
        "m+": this.getMinutes(),
        "s+": this.getSeconds(),
        "q+": Math.floor((this.getMonth() + 3) / 3),
        "S+": this.getMilliseconds()
    };
    /(y+)/.test(t) && (t = t.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length)));
    for (var i in e)if (new RegExp("(" + i + ")").test(t)) {
        for (var n = "", s = 1; s <= RegExp.$1.length; s++)n += "0";
        var o = "";
        if (1 == RegExp.$1.length) o = e[i]; else {
            n += e[i];
            var r = ("" + e[i]).length;
            n = n.substr(r), o = n
        }
        t = t.replace(RegExp.$1, o)
    }
    return t.replace(/NaN-aN-aN/g, "")
}, String.prototype.isUrl = function () {
    var t = "^((https|http|ftp|rtsp|mms)?://)?(([0-9a-z一-龥_!~*'().&=+$%-]+: )?[0-9a-z_!~*'().&=+$%-]+@)?(([0-9]{1,3}.){3}[0-9]{1,3}|([0-9a-z一-龥_!~*'()-]+.)*([0-9a-z一-龥][0-9a-z一-龥-]{0,61})?[0-9a-z一-龥].[a-z]{2,6})(:[0-9]{1,4})?((/?)|(/[0-9a-zA-Z_!~*'().;?:@&=+$,%#-]+)+/?)$", e = new RegExp(t);
    return e.test(this)
}, String.prototype.isEmail = function () {
    var t = /(^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$)/;
    return t.test(this)
}, String.prototype.isDomain = function () {
    for (var t = this, e = new RegExp(/^((([A-Za-z0-9_-\u4E00-\u9FA5])|(\*))+\.)?([A-Za-z0-9_-\u4E00-\u9FA5]{1,200})$/), i = new Array(".gov.mo", ".com.tw", ".com.mo", ".co.cc", ".ce.ms", ".osa.pl", ".c.la", ".com.hk", ".net.in", ".edu.tw", ".org.tw", ".bij.pl", ".ac.cn", ".ah.cn", ".bj.cn", ".com.cn", ".cq.cn", ".fj.cn", ".gd.cn", ".gov.cn", ".gs.cn", ".gx.cn", ".gz.cn", ".ha.cn", ".hb.cn", ".he.cn", ".hi.cn", ".hk.cn", ".hl.cn", ".hn.cn", ".jl.cn", ".js.cn", ".jx.cn", ".ln.cn", ".mo.cn", ".net.cn", ".nm.cn", ".nx.cn", ".org.cn", ".qh.cn", ".sc.cn", ".sd.cn", ".sh.cn", ".sn.cn", ".sx.cn", ".tj.cn", ".tw.cn", ".xj.cn", ".xz.cn", ".yn.cn", ".zj.cn", ".nl.ae", ".org.uk", ".org.nz", ".org.bz", ".org.au", ".com.nu", ".com.my", ".com.au", ".co.uk", ".co.kr", ".co.jp", ".nu.ae", ".nl.ae", ".com.au", ".cf.gs", ".com.cn", ".net.cn", ".org.cn", ".edu.cn", ".com", ".cn", ".mobi", ".tel", ".asia", ".net", ".org", ".name", ".me", ".info", ".cc", ".hk", ".biz", ".tv", ".la", ".fm", ".cm", ".am", ".sh", ".us", ".in", ".ro", ".ru", ".hu", ".tk", ".co", ".cx", ".at", ".tw", ".ws", ".vg", ".vc", ".uz", ".to", ".tl", ".th", ".tf", ".tc", ".st", ".so", ".sk", ".sg", ".sc", ".pl", ".pe", ".nu", ".nf", ".ne", ".my", ".mu", ".ms", ".mo", ".lv", ".lt", ".lc", ".jp", ".it", ".io", ".im", ".ie", ".gs", ".gp", ".gl", ".gg", ".gd", ".fr", ".fi", ".eu", ".edu", ".dk", ".de", ".cz", ".ch", ".ca", ".bi", ".be", ".au", ".ae", ".pw", ".ly", ".wang", ".ren", ".top", ".club"), n = !1, s = 0; s < i.length; s++) {
        var o = i[s], r = "(\\" + o + ")$", a = new RegExp(r);
        a.test(t.toLowerCase()) && (n = !0), t = t.replace(o, "")
    }
    return n ? e.test(t) : !1
}, String.prototype.isZipCode = function () {
    var t = /^[0-9]{6}$/;
    return t.test(this)
}, String.prototype.isPhone = function () {
    var t = /((^(\+86)|(86))?(((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$))/;
    return t.test(this)
}, String.prototype.isMobile = function () {
    // var t = /^(\+86)|(86)?(\d{11})$/;
    // var t = /^(\+86)|(86)?(^((1[3,5,8][0-9])|(14[5,7]))\d{8}$)/;
    var t = /(^0{0,1}1[3|4|5|6|7|8|9][0-9]{9}$)/;
    return t.test(this)
}, String.prototype.isNumber = function () {
    var t = /^[0-9]+$/;
    return t.test(this)
},String.prototype.isDate = function () {
    var t = /^(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29)$/;
    return t.test(this);
}, String.prototype.isId = function () {
    var t = /^(^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$)|(^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])((\d{4})|\d{3}[Xx])$)$/;
    return t.test(this)
}, String.prototype.format = function () {
    var t = arguments;
    return this.replace(/\{(\d+)\}/g, function (e, i) {
        return t[i]
    })
}, $.fn.longPress = function () {
    return
}, SlideButton.prototype.hideContent = function () {
    var t = this;
    window.setTimeout(function () {
        t.mouseOn || t.content.hide()
    }, 300)
}, SelectHandler.prototype = {
    clear: function () {
        this.items.removeClass("current")
    }, selected: function () {
    }
}, Util.MobilePopover = function (t) {
    this.obj = t ? t : $(".container-content"), this.isDefaultPip = !0, this.pop = $("<div class='feedback'><span></span></div>"), $(".feedback").length < 1 && this.obj.append(this.pop), this.timer = null
}, Util.MobilePopover.prototype = {
    show: function (t, e) {
        $(".feedback").is(":hidden") || clearTimeout(this.timer), this.pop.find("span").html(t), this.pop.show(), this.timer = setTimeout(function () {
            this.pop.hide(), e && e()
        }.bind(this), 2e3)
    }, hide: function () {
        this.pop.hide(), clearTimeout(this.timer)
    }
}, Popover.prototype = {
    show: function (t) {
        this.obj.pop.html(t).show(), this.isDefaultPip && this.position(this.obj.tip)
    }, hide: function () {
        this.obj.pop.hide()
    }, position: function (t) {
        var e = this.obj;
        e.parent().append(e.pop), e.pop.css({position: "absolute", padding: "10px", "white-space": "nowrap"});
        var i = e[0].offsetLeft, n = e[0].offsetTop, s = e[0].offsetWidth, o = e[0].offsetHeight, r = e.pop[0].offsetWidth, a = e.pop[0].offsetHeight;
        switch (t) {
            case"left":
                i -= r, n = n + o / 2 - a / 2;
                break;
            case"top":
                i += r / 2, n -= a;
                break;
            case"right":
                i += s, n = n + o / 2 - a / 2;
                break;
            case"bottom":
                i += r / 2, n += o;
                break;
            case"innerLeft":
                i = i + s - r, n = n + o / 2 - a / 2;
                break;
            case"innerRight":
                n = n + o / 2 - a / 2
        }
        e.pop.css({left: i + "px", top: n + "px"})
    }
}, FormItem.prototype = {
    onkeydown: function () {
        var t = this;
        t.obj.keydown(function (e) {
            var i = t.getLength();
            return i > Number(t.obj.attr("maxlength")) ? (e.preventDefault(), !1) : void 0
        })
    }, onblur: function () {
        var t = this;
        t.obj.blur(function () {
            t.validate()
        })
    }, validate: function () {
        return this.obj.val() != this.placeholder ? (this.removeError(), this.checkLength() ? this.checkType() ? this.checkNull() ? this.checkAjax() ? this.checkCompare() ? (this.removeError(), !0) : !1 : !1 : !1 : !1 : !1) : void 0
    }, checkType: function () {
        var t = !1, e = this.obj.val().replace(/\s*/g, "");
        if ("" == e && isNaN(Number(this.obj.attr("minlength"))))return !0;
        if (void 0 == this.type || "" == this.type)return !0;
        var i = new Array;
        -1 != this.type.indexOf(",") ? i = this.type.replace(/\s*/g, "").split(",") : i.push(this.type);
        for (var n = 0; n < i.length; n++) {
            if (type = i[n], type && "" != type)switch (type) {
                case"email":
                    t = e.isEmail();
                    break;
                case"number":
                    t = e.isNumber();
                    break;
                case"phone":
                    t = e.isPhone();
                    break;
                case"mobile":
                    t = e.isMobile();
                    break;
                case"isdate":
                    t = e.isDate();
                    break;
                case"zipcode":
                    t = e.isZipCode();
                    break;
                case"domain":
                    t = e.isDomain();
                    break;
                case"url":
                    t = e.isUrl();
                    break;
                case"id":
                    t = e.isId();
                    break;
                default:
                    t = !0
            }
            if (t)break
        }
        return t || this.addError(2), t
    }, getLength: function () {
        var t = this.obj.val().replace(/\s*/g, "").length;
        return t = /text|password/.test(this.type) ? this.obj.val().length : t
    }, checkLength: function () {
        var t = this.getLength();
        return !isNaN(Number(this.obj.attr("minlength"))) && t < Number(this.obj.attr("minlength")) ? (0 == t ? this.addError(1) : this.addError(2), !1) : !isNaN(Number(this.obj.attr("maxlength"))) && t > Number(this.obj.attr("maxlength")) ? (this.addError(2), !1) : !0
    }, checkNull: function () {
        var t = this.getLength();
        return "ture" == this.obj.attr("notnul") ? ((0 == t || "" == this.obj[0].value.replace(/\s*/g, "")) && this.addError(3), !1) : !0
    }, checkAjax: function () {
        var t = !0;
        if (this.ajax) {
            var e = this;
            $.ajax({
                url: this.ajax, async: !1, data: this.name + "=" + this.obj.val(), success: function (i) {
                    i || (e.addError(3), t = !1)
                }
            })
        }
        return t
    }, checkCompare: function () {
        if (!this.compare)return !0;
        var t = this.compare.split(","), e = t[0], i = "";
        switch (e) {
            case"==":
                i = "{0}和{1}不一致";
                for (var n = 1; n < t.length; n++) {
                    var s = $(t[n]);
                    if (this.obj.val() != s.val())return this.addError(i.format(this.des, s.attr("des"))), !1
                }
        }
        return !0
    }, addError: function (t) {
        var e = void 0 == this.obj.attr("des") ? "" : this.obj.attr("des"), i = "";
        if (isNaN(Number(t))) i = t, this.errors.push(0); else {
            switch (t) {
                case 1:
                    i = "请输入" + e;
                    break;
                case 2:
                    i = e + "格式不正确";
                    break;
                case 3:
                    i = this.ajaxMsg
            }
            this.errors.push(t)
        }
        this.hastip ? (this.mpopover.show(i), this.popover.show("")) : this.popover.show(i)
    }, removeError: function () {
        this.errors = new Array, this.popover.hide()
    }
}, mForm.prototype = {
    getItem: function (t) {
        for (var e = 0; e < this.obj.items.length; e++) {
            var i = this.obj.items[e];
            if (i.name == t)return i
        }
    }, beforeSubmit: function () {
        return !0
    }, submitError: function () {
        return !0
    }, completeSubmit: function () {
        return !0
    }, loadDate: function () {
        return !0
    }, afterSubmit: function () {
        return !0
    }
}, ContentUtil.prototype = {
    setTitle: function () {
        this.obj.each(function () {
            $(this).attr("title", $(this).html())
        })
    }, maxLength: function () {
        var t = this;
        this.obj.each(function () {
            var e = $(this).html(), i = $(this).attr("maxlength");
            void 0 != i && ($(this).data("html", e), e.length > i && ($(this).html(e.substring(0, i) + "<a more='on' href='javascript:void(0)'  style='color:#45a9ff;'>...更多</a>"), t.maxlengthfilter_bangding($(this).find("[more]"))))
        })
    }, maxlengthfilter_bangding: function (t) {
        var e = this;
        t.click(function () {
            var i = t.parent();
            "on" == t.attr("more") ? i.html(i.data("html") + "<a more='off' href='javascript:void(0)' style='color:#45a9ff;'>&nbsp收起</a>") : i.html(i.data("html").substring(0, i.attr("maxlength")) + "<a more='on' href='javascript:void(0)' style='color:#45a9ff;'>…更多</a>"), e.maxlengthfilter_bangding(i.find("[more]"))
        })
    }
}, URLHandler.prototype = {
    init: function () {
        var t = this.url.split("?");
        if (this.uri = t[0], this.url.split("/").length > 3) {
            var e = this.uri.split("/");
            this.basePath = e.splice(0, 3).join("/"), this.absolutePath = "/" + e[e.length - 1]
        }
        if (-1 == this.url.indexOf("?"))return !0;
        var i = t[1];
        i = -1 === i.indexOf("#") ? i : i.split("#")[0], i = -1 == i.indexOf("&") ? [i] : i.split("&");
        for (var n = 0; n < i.length; n++)if (-1 != i[n].indexOf("=")) {
            var s = i[n].split("=");
            this.params.put(s[0], decodeURIComponent(s[1]))
        } else this.params.put(i[n], i[n])
    }, createUrl: function () {
        for (var t = "", e = "", i = 0, n = this.params.keySet(), s = n.length; s > i; i++)t += e + n[i] + "=" + this.params.get(n[i]), e = "&";
        return "" != t ? this.uri + "?" + t : this.uri
    }, turn: function () {
        window.location.href = this.createUrl()
    }
}, PageHandler.prototype = {
    init: function (t) {
        this.content = t.content, this.pin = t.pin ? t.pin : 5, this.urlHandler = t.urlHandler, this.isAjax = t.isAjax ? t.isAjax : !0, this.pContent = $(this.content.attr("page")), this.npage = void 0 == t.npage ? 1 : t.npage, this.spage = void 0 == t.spage ? 1 : t.spage, this.fpage = void 0 == t.fpage ? !1 : t.fpage, this.lpage = void 0 == t.lpage ? !1 : t.lpage, this.djudage = void 0 == t.djudage ? !1 : t.djudage, this.dnone = void 0 == t.dnone ? $(".data-no-tip") : t.dnone, this.content.find("[model]") && (this.itemModel = this.content.find("[model]").clone().removeAttr("model").removeAttr("style")[0].outerHTML)
    }, createPage: function () {
        var t = this, e = this.npage - this.pin > 0 ? this.npage - this.pin : 1, i = e + 2 * this.pin > this.spage ? this.spage : e + 2 * this.pin;
        e = i - 2 * this.pin > 0 ? i - 2 * this.pin : e;
        for (var n = "", s = "<a href='javascript:void(0)' class='{0}' page='{1}'>{2}</a>", o = e; i >= o; o++) {
            var r = o == this.npage ? "current" : "";
            n += s.format(r, o, o)
        }
        this.npage > 1 && (n = s.format("", Number(this.npage) - 1, "上一页") + n, this.fpage && (n = s.format("", 1, "首页") + n)), this.npage < this.spage && (n += s.format("", Number(this.npage) + 1, "下一页"), this.fpage && (n += s.format("", Number(this.spage), "尾页"))), n = this.npage == this.spage && 1 == this.npage ? "" : n, this.pContent.find("p").html(n), this.pContent.find("a").click(function () {
            t.go($(this).attr("page"))
        })
    }, go: function (t) {
        t = Number(t), t > 0 && t <= this.spage && (this.npage = t, this.urlHandler.params.put("page", t), this.isAjax ? this.loadData() : this.urlHandler.turn())
    }, loadData: function () {
        var t = this;
        $.getJSON(this.urlHandler.createUrl(), {}, function (e) {
            this.dataBackNone(e), t.content.html("");
            for (var i = e.list, n = 0; n < i.length; n++) {
                var s = i[n];
                t.content.append(t.formateItem(n, t.itemModel, s))
            }
            t.spage = e.page_count, t.createPage()
        }.bind(this))
    }, dataBackNone: function (t) {
        this.djudage && (0 == t.list.length ? this.dnone.show() : this.dnone.hide())
    }, formateItem: function () {
    }
}, RSF.regist("Util.device"), Util.device = {
    isIphone: function () {
        return /(iPhone|iPad|iPod|iOS)/i.test(navigator.userAgent)
    }, isAndroid: function () {
        return /(Android)/i.test(navigator.userAgent)
    }, isMobile: function () {
        return this.isIphone() || this.isAndroid()
    },
}, Util.getSerial = function () {
    var t = parseInt((new Date).getTime() / 1e3) - DIS;
    t += "";
    for (var e = md5(t).split(""), i = 0; i < t.length; i++)e[2 * i] = t[i];
    return e.join("")
}, jQuery.extend({
    postJSON: function (t, e, i) {
        $.post(t, e, function (t) {
            i.call(this, t)
        })
    }
}), RSF.regist("Util.DatePicker"), Util.DatePicker = function () {
    this.maxYear = "", this.minYear = "", this.date = null, this.container = null
}, Util.DatePicker.prototype = {
    leftZero: function (t) {
        return t = parseInt(t), t >= 10 ? t : "0" + t
    }, getDateIndex: function () {
        return this.container.find(".picker_year").val() + this.leftZero(this.container.find(".picker_month").val()) + this.leftZero(this.container.find(".picker_day").val())
    }, getDateString: function () {
        return this.container.find(".picker_year").val() + "-" + this.leftZero(this.container.find(".picker_month").val()) + "-" + this.leftZero(this.container.find(".picker_day").val())
    }, setDateByNum: function (t, e, i) {
        var n = new Date;
        n.setFullYear(t), n.setMonth(e - 1), n.setDate(i), this.setDate(n)
    }, setDate: function (t) {
        this.curYear = t.getFullYear(), this.curMonth = t.getMonth() + 1, this.curDay = t.getDate(), this.container.find(".picker_year").val(this.curYear), this.container.find(".picker_month").val(this.curMonth), this.initDay()
    }, setDefault: function () {
        this.date = new Date, this.curYear = this.date.getFullYear(), this.curMonth = this.date.getMonth() + 1, this.curDay = this.date.getDate(), this.maxYear || (this.maxYear = 2023), this.minYear || (this.minYear = 1970), this.initYear(), this.initMonth(), this.initDay()
    }, init: function (t) {
        this.container = t, this.setDefault()
    }, initYear: function () {
        this.container.find(".picker_year")[0] || $('<select class="picker_year"></select>').appendTo(this.container);
        for (var t = 0, e = this.minYear; e <= this.maxYear; e++) {
            var i = new Option(e + "年", e);
            this.container.find(".picker_year")[0].options[t++] = i
        }
        this.container.find(".picker_year").val(this.curYear), this.container.find(".picker_year").unbind("change"), this.container.find(".picker_year").change(function () {
            this.curDay = this.container.find(".picker_day").val(), this.initDay()
        }.bind(this))
    }, initMonth: function () {
        this.container.find(".picker_month")[0] || $('<select class="picker_month"></select>').appendTo(this.container);
        for (var t = 0, e = 1; 12 >= e; e++) {
            var i = new Option(e + "月", e);
            this.container.find(".picker_month")[0].options[t++] = i
        }
        this.container.find(".picker_month").val(this.curMonth), this.container.find(".picker_month").unbind("change"), this.container.find(".picker_month").change(function () {
            this.curDay = this.container.find(".picker_day").val(), this.initDay()
        }.bind(this))
    }, getDaysByMonth: function (t, e) {
        if (t = parseInt(t), e = parseInt(e), !t || !e)return 31;
        var i = 0 == e % 400 || 0 == e % 4 && 0 != e % 100;
        if (2 == t)return i ? 29 : 28;
        switch (t) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                return 31;
            default:
                return 30
        }
    }, initDay: function () {
        this.container.find(".picker_day")[0] || $('<select class="picker_day"></select>').appendTo(this.container);
        var t = 0, e = this.getDaysByMonth(this.container.find(".picker_month").val(), this.container.find(".picker_year").val());
        this.container.find(".picker_day")[0].options.length = 0;
        for (var i = 1; e >= i; i++) {
            var n = new Option(i + "日", i);
            this.container.find(".picker_day")[0].options[t++] = n
        }
        this.curDay > e && (this.curDay = 1), this.container.find(".picker_day").val(this.curDay)
    }
}, RSF.regist("Util.getWeiboLength"), Util.getWeiboLength = function (t) {
    for (var e = 0, i = 0; i < t.length; i++)t.charCodeAt(i) > 255 ? e++ : e += .5;
    return Math.ceil(e)
}, RSF.regist("Util.InputControl"), Util.InputControl = function () {
    this.inputId = "", this.maxNum = "", this.overCallback = function () {
    }, this.lastValue = "", this.valueChangeCallback = function () {
    }
}, Util.InputControl.prototype = {
    init: function (t, e, i, n) {
        i = i || function () {
            }, n = n || function () {
            }, this.inputId = "#" + t, this.maxNum = e, this.overCallback = n, this.valueChangeCallback = i, this.bindEvent()
    }, bindEvent: function () {
        $(this.inputId).unbind("propertychange"), $(this.inputId).unbind("input"), $(this.inputId).bind("propertychange input", this.valueChange.bind(this))
    }, valueChange: function () {
        var t = Util.getWeiboLength($(this.inputId).val());
        t > this.maxNum ? ($(this.inputId).blur(), $(this.inputId).val(this.lastValue), $(this.inputId).focus(), this.overCallback()) : this.valueChangeCallback(t), this.lastValue = $(this.inputId).val()
    }
}, SelectDiv.prototype = {}, TipShow.prototype = {
    init: function () {
        this.move(), this.show(), this.hide()
    }, move: function () {
        this.items.mousemove(function (t) {
            var e = t.pageX + 15, i = t.pageY + 15;
            this.doms.css({top: i, left: e}), this.doms.html($(t.currentTarget).attr("tip"))
        }.bind(this))
    }, show: function () {
        this.items.mouseover(function () {
            this.doms.show()
        }.bind(this))
    }, hide: function () {
        this.items.mouseout(function () {
            this.doms.hide()
        }.bind(this))
    }
}, SelectItem.prototype = {}, SelectItemHref.prototype = {}, RSF.regist("Credit.User.Index"), Credit.User.Index = function () {
    this.myChartMap = echarts.init(document.getElementById("mchart")), this.option = {}, this.dataSeries = [], this.dataX = [], this.dateSelectTitle = "昨天"
}, Credit.User.Index.prototype = {
    init: function () {
        var t = new Date;
        t.setDate(t.getDate() - 1), t = this.formatDate(t, "yyyy-MM-dd hh:mm:ss");
        var e = new Date;
        e.setDate(e.getDate() - 365), e = this.formatDate(e, "yyyy-MM-dd hh:mm:ss"), new SelectHandler($(".all-time li"), !1, !0), $(".yesterday-date").on("click", function () {
            var t = new Date;
            t.setDate(t.getDate() - 1), this.getDateData(t)
        }.bind(this)), $(".before-date").on("click", function () {
            var t = new Date;
            t.setDate(t.getDate() - 2), this.getDateData(t)
        }.bind(this)), this.chartInit(), $("#datetimepicker").bind("click", function (i) {
            return $(".date-picker").toggle(), $("#datepickerStart").datetimepicker({
                format: "yyyy-mm-dd",
                startView: 2,
                endDate: t,
                startDate: e,
                minView: 2,
                viewSelect: 2,
                language: "zh-CN"
            }).on("changeDate.datepicker.amui", function (t) {
                $("#datepickerStart").datetimepicker("update", t.date), this.getDateData(t.date), $(".date-picker").hide()
            }.bind(this)), $("#datepickerStart").datetimepicker("show"), $(document).one("click", function () {
                $(".date-picker").hide()
            }), i.stopPropagation(), !1
        }.bind(this)), $(".date-picker").on("click", function (t) {
            t.stopPropagation()
        })
    }, getDateData: function (t) {
        var e = this.formatDate(t, "yyyyMMdd");
        this.dateSelectTitle = this.formatDate(t, "yyyy-MM-dd"), $.getJSON("", {
            action: "get_charts_data",
            time_index: e
        }, function (t) {
            this.dataSeries = t.data, this.dataX = t.title, this.chartSet()
        }.bind(this))
    }, formatDate: function (t, e) {
        var i = function (t) {
            return t += "", t.replace(/^(\d)$/, "0$1")
        }, n = {
            yyyy: t.getFullYear(),
            yy: t.getFullYear().toString().substring(2),
            M: t.getMonth() + 1,
            MM: i(t.getMonth() + 1),
            d: t.getDate(),
            dd: i(t.getDate()),
            hh: t.getHours(),
            mm: t.getMinutes(),
            ss: t.getSeconds()
        };
        return e || (e = "yyyy-MM-dd"), e.replace(/([a-z])(\1)*/gi, function (t) {
            return n[t]
        })
    }, chartInit: function () {
        this.option = {
            title: {text: "接口调用量", subtext: "昨天"},
            tooltip: {trigger: "axis"},
            legend: {data: ["次数"], selectedMode: !1},
            xAxis: [{data: ["查询", "命中"]}],
            yAxis: [{type: "value"}],
            series: [{name: "次数", type: "bar", data: [0, 0]}]
        }, this.myChartMap.setTheme(EcahrtTheme.bluetheme), this.myChartMap.setOption(this.option), this.chartSet()
    }, chartSet: function () {
        this.myChartMap.clear(), this.myChartMap.setOption(this.option), this.myChartMap.setOption({
            title: {subtext: this.dateSelectTitle},
            xAxis: [{data: this.dataX}],
            series: [{name: "次数", type: "bar", data: this.dataSeries}]
        })
    }, setChartData: function (t) {
        this.dataSeries = t.data, this.dataX = t.title
    }, setOverviewData: function () {
    }
}, RSF.regist("EcahrtTheme"), EcahrtTheme.bluetheme = {
    color: ["#1790cf", "#1bb2d8", "#99d2dd", "#88b0bb", "#1c7099", "#038cc4", "#75abd0", "#afd6dd"],
    title: {textStyle: {fontWeight: "normal", color: "#1790cf"}},
    tooltip: {
        backgroundColor: "rgba(0,0,0,0.5)",
        axisPointer: {
            type: "line",
            lineStyle: {color: "#1790cf", type: "dashed"},
            crossStyle: {color: "#1790cf"},
            shadowStyle: {color: "rgba(200,200,200,0.3)"}
        }
    },
    grid: {borderWidth: 0},
    categoryAxis: {axisLine: {lineStyle: {color: "#1790cf"}}, splitLine: {lineStyle: {color: ["#eee"]}}},
    valueAxis: {
        axisLine: {lineStyle: {color: "#1790cf"}},
        splitArea: {show: !0, areaStyle: {color: ["rgba(250,250,250,0.1)", "rgba(200,200,200,0.1)"]}},
        splitLine: {lineStyle: {color: ["#eee"]}}
    },
    timeline: {lineStyle: {color: "#1790cf"}, controlStyle: {normal: {color: "#1790cf"}, emphasis: {color: "#1790cf"}}},
    k: {
        itemStyle: {
            normal: {
                color: "#1bb2d8",
                color0: "#99d2dd",
                lineStyle: {width: 1, color: "#1c7099", color0: "#88b0bb"}
            }
        }
    },
    textStyle: {fontFamily: "微软雅黑, Arial, Verdana, sans-serif"}
}, EcahrtTheme.greentheme = {
    color: ["#0ec6c9", "#b7a1e0", "#99d2dd", "#88b0bb", "#1c7099", "#038cc4", "#75abd0", "#afd6dd"],
    title: {textStyle: {fontWeight: "normal", color: "#1790cf"}},
    tooltip: {
        backgroundColor: "rgba(0,0,0,0.5)",
        axisPointer: {
            type: "line",
            lineStyle: {color: "#1790cf", type: "dashed"},
            crossStyle: {color: "#1790cf"},
            shadowStyle: {color: "rgba(200,200,200,0.3)"}
        }
    },
    grid: {borderWidth: 0},
    categoryAxis: {axisLine: {lineStyle: {color: "#1790cf"}}, splitLine: {lineStyle: {color: ["#eee"]}}},
    valueAxis: {
        axisLine: {lineStyle: {color: "#1790cf"}},
        splitArea: {show: !0, areaStyle: {color: ["rgba(250,250,250,0.1)", "rgba(200,200,200,0.1)"]}},
        splitLine: {lineStyle: {color: ["#eee"]}}
    },
    timeline: {lineStyle: {color: "#1790cf"}, controlStyle: {normal: {color: "#1790cf"}, emphasis: {color: "#1790cf"}}},
    k: {
        itemStyle: {
            normal: {
                color: "#1bb2d8",
                color0: "#99d2dd",
                lineStyle: {width: 1, color: "#1c7099", color0: "#88b0bb"}
            }
        }
    },
    textStyle: {fontFamily: "微软雅黑, Arial, Verdana, sans-serif"}
}, $.fn.datetimepicker.dates["zh-CN"] = {
    days: ["星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六", "星期日"],
    daysShort: ["周日", "周一", "周二", "周三", "周四", "周五", "周六", "周日"],
    daysMin: ["日", "一", "二", "三", "四", "五", "六", "日"],
    months: ["一月", "二月--", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"],
    monthsShort: ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"],
    today: "今天",
    suffix: [],
    meridiem: ["上午", "下午"]
}, RSF.regist("Credit.Header"), Credit.Header = function () {
}, Credit.Header.prototype = {
    init: function () {
        new SlideButton($("#hover_user_toggle"), $("#hover_user_show"))
    }
};
var CreditHeader = new Credit.Header;
CreditHeader.init(), RSF.regist("Credit.Nav"), Credit.Nav = function () {
}, Credit.Nav.prototype = {
    init: function () {
        new NavHandler($(".nav-l"));
        $(".nav-l").click(function () {
            location.href = $(this).attr("hr");
        });
    }
};
/*var NavHeader = new Credit.Nav;
NavHeader.init();*/
