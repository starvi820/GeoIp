/*****
 * CONFIGURATION
 */

//Active ajax page loader
$.ajaxLoad = true;

//required when $.ajaxLoad = true
$.subPagesDirectory = 'sysmanagerOne/';
$.page404 = 'views/error/404.jsp';
$.mainContent = $('#ui-view');

//Main navigation
$.navigation = $('nav > ul.nav');

$.panelIconOpened = 'icon-arrow-up';
$.panelIconClosed = 'icon-arrow-down';

//Default colours
$.brandPrimary =  '#20a8d8';
$.brandSuccess =  '#4dbd74';
$.brandInfo =     '#63c2de';
$.brandWarning =  '#f8cb00';
$.brandDanger =   '#f86c6b';

$.grayDark =      '#2a2c36';
$.gray =          '#55595c';
$.grayLight =     '#818a91';
$.grayLighter =   '#d1d4d7';
$.grayLightest =  '#f8f9fa';

//공통 :: object
window.com = {};

/**
 * datepicker(년월일)
 * 
 * @return
 */
window.com.setDatepicker=function() {
	return {
		showOn: "button",
		buttonText:"<i class='icon-calendar'></i>",
		buttonImageOnly: false,
		dateFormat: 'yy-mm-dd',
		prevText: '이전 달',
		nextText: '다음 달',
		monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
		monthNamesShort: ['1','2','3','4','5','6','7','8','9','10','11','12'],
		dayNamesMin: [ '일', '월', '화', '수', '목', '금', '토'],
		showMonthAfterYear: true,
		yearSuffix: '년',
		changeMonth: false,
        changeYear: false,
        showButtonPanel: true,
        yearRange: 'c-99:c+99'       
	}
}

'use strict';

/*****
 * ASYNC LOAD
 * Load JS files and CSS files asynchronously in ajax mode
 */
function loadJS(jsFiles, pageScript) {

	const body = document.getElementsByTagName('body')[0];

	for(let i = 0; i<jsFiles.length; i++){
		appendScript(body, jsFiles[i])
	}

	if (pageScript) {
		appendScript(body, pageScript)
	}

	init();
}

function appendScript(element, src) {
	const async = (src.substring(0, 4) === 'http');
	let script = document.createElement('script');
	script.type = 'text/javascript';
	script.async = async;
	script.defer = async;
	script.src = src;
	async ? appendOnce(element, script) : element.appendChild(script);
}

function appendOnce(element, script) {
	let scripts = Array.from(document.querySelectorAll('script')).map(function(scr){return scr.src;});

	if (!scripts.includes(script.src)) {
		element.appendChild(script)
	}
}

function loadCSS(cssFile, end, callback) {

	var cssArray = {};

	if (!cssArray[cssFile]) {
		cssArray[cssFile] = true;

		if (end == 1) {

			var head = document.getElementsByTagName('head')[0];
			var s = document.createElement('link');
			s.setAttribute('rel', 'stylesheet');
			s.setAttribute('type', 'text/css');
			s.setAttribute('href', cssFile);

			s.onload = callback;
			head.appendChild(s);

		} else {

			var head = document.getElementsByTagName('head')[0];
			var style = document.getElementById('main-style');

			var s = document.createElement('link');
			s.setAttribute('rel', 'stylesheet');
			s.setAttribute('type', 'text/css');
			s.setAttribute('href', cssFile);

			s.onload = callback;
			head.insertBefore(s, style);

		}

	} else if (callback) {
		callback();
	}

}

/****
 * AJAX LOAD
 * Load pages asynchronously in ajax mode
 */

if ($.ajaxLoad) {

	var paceOptions = {
			elements: false,
			restartOnRequestAfter: false
	};

	$(document).on('click', '.nav a[href!="#"]', function(e) {
		if ( $(this).parent().parent().hasClass('nav-tabs') || $(this).parent().parent().hasClass('nav-pills') ) {
			e.preventDefault();
		} else if ( $(this).attr('target') == '_top' ) {
			e.preventDefault();
			var target = $(e.currentTarget);
			window.location = (target.attr('href'));
		} else if ( $(this).attr('target') == '_blank' ) {
			e.preventDefault();
			var target = $(e.currentTarget);
			window.open(target.attr('href'));
		} else {
			e.preventDefault();
			var target = $(e.currentTarget);
			//console.log(target.attr('href'));
			setUpUrl(target.attr('href'));
		}
	});

	$(document).on('click', 'a[href="#"]', function(e) {
		e.preventDefault();
	});

}

function setUpUrl(url) {

	$('nav .nav li .nav-link').removeClass('active');
	$('nav .nav li.nav-dropdown').removeClass('open');
	$('nav .nav li:has(a[href="' + url.split('?')[0] + '"])').addClass('open');
	$('nav .nav a[href="' + url.split('?')[0] + '"]').addClass('active');

	loadPage(url);
}

function loadPage(url) {

	$.ajax({
		type : 'GET' , 
		url : url
	}).done(function(data) {
		$('html, body').animate({ scrollTop: 0 }, 0);
		$(location).attr('href', url);
	});
}


/****
 * MAIN NAVIGATION
 */

$(document).ready(function($){

	// Add class .active to current link - AJAX Mode off
	$.navigation.find('a').each(function(){

		var cUrl = String(window.location).split('?')[0];

		if (cUrl.substr(cUrl.length - 1) == '#') {
			cUrl = cUrl.slice(0,-1);
		}

		if ($($(this))[0].href==cUrl) {
			$(this).addClass('active');
			//경로 및 main title
			if($(this).closest("ul").hasClass("nav-dropdown-items")){
				$("#mainTitle").text($(this).closest("ul").parent().find(".nav-dropdown-toggle").text());
				$("#mainTitle").next().html("<a href='"+$(this).attr("href")+"'>"+$(this).text()+'</a>');
				$("#headerTitle").html( $('.breadcrumb > li.breadcrumb-item:last').text() );//header title
			}
			else{
				$("#mainTitle").html("<a href='"+$(this).attr("href")+"'>"+$(this).text()+'</a>');
				$("#mainTitle").next().hide();
				$("#headerTitle").html( $('.breadcrumb > li.breadcrumb-item:last').text() );
			}
			$(this).parents('ul').add(this).each(function(){
				$(this).parent().addClass('open');
			});
		} else {
			$("#headerTitle").html( $('.breadcrumb > li.breadcrumb-item:last').text());
		}
	});

	// Dropdown Menu
	$.navigation.on('click', 'a', function(e){

		if ($.ajaxLoad) {
			e.preventDefault();
		}

		if ($(this).hasClass('nav-dropdown-toggle')) {
			$(this).parent().toggleClass('open');
			resizeBroadcast();
		}
	});


	function resizeBroadcast() {

		var timesRun = 0;
		var interval = setInterval(function(){
			timesRun += 1;
			if(timesRun === 5){
				clearInterval(interval);
			}
			if (navigator.userAgent.indexOf('MSIE') !== -1 || navigator.appVersion.indexOf('Trident/') > 0) {
				var evt = document.createEvent('UIEvents');
				evt.initUIEvent('resize', true, false, window, 0);
				window.dispatchEvent(evt);
			} else {
				window.dispatchEvent(new Event('resize'));
			}
		}, 62.5);
	}

	/* ---------- Main Menu Open/Close, Min/Full ---------- */
	$('.sidebar-toggler').click(function(){
		$('body').toggleClass('sidebar-minimized');
		$('body').toggleClass('brand-minimized');



		resizeBroadcast();
	});

	/*  $(".sidebar .nav-item, .navbar-brand").hover(function(){
  	  $('body').toggleClass('sidebar-minimized');
  	  $('body').toggleClass('brand-minimized');
  });*/

	$('.sidebar-minimizer').click(function(){
		$('body').toggleClass('sidebar-minimized');

		resizeBroadcast();
	});


	$('.brand-minimizer').click(function(){
		$('body').toggleClass('brand-minimized');
	});



	$('.aside-menu-toggler').click(function(){
		$('body').toggleClass('aside-menu-hidden');
		resizeBroadcast();
	});

	$('.mobile-sidebar-toggler').click(function(){
		$('body').toggleClass('sidebar-mobile-show');
		resizeBroadcast();
	});

	$('.sidebar-close').click(function(){
		$('body').toggleClass('sidebar-opened').parent().toggleClass('sidebar-opened');
	});

	/* ---------- Disable moving to top ---------- */
	$('a[href="#"][data-top!=true]').click(function(e){
		e.preventDefault();
	});
	
	// 검색 필터 이벤트
	schFilterEvent();
	
	// 화면 JS 호출
	init.initialize();
	
	// 장애 알림 호출
	//realTimeAlert();
});


/***
 * 
 *  검색 필터 이벤트
 */
function schFilterEvent() {
	// 검색필터 삭제 
	$('.subfilter-disable-btn').click(function() {
		if($(this).closest('ul').find('li').length < 2) {
			$(this).closest('ul').prepend('<li><input type="text" placeholder="검색" readonly></li>');
		}
		$(this).closest('li').remove();
	});
	
	// 비즈니스 장비 검색필터 hover
	$('#deviceFilter > li').mouseenter(function() {
		$(this).find('span').addClass('on');
	}).mouseleave(function() {
		$(this).find('span').removeClass('on');
	});
}


/***
 * 
 *  팝업창
 */
var searchEl;

//검색창 버튼
$('.searchBtn').click(function(){
	$(".modal-content").each(function(i, e){
	    $(this).addClass("none");
	});
	searchEl = $(this).parent().find(".multiselect-list");
	$(".modal-dialog").find("input[type=checkbox]").prop('checked', false);
	
	var clickData = $(this).data("searchn");
	$(".modal-dialog ."+clickData).removeClass("none");
});

//검색 필터
$('#dropdownFilter').click(function(){
	$(this).closest('div').toggleClass('dropup');
	if(!$(this).closest('div').hasClass('dropup')) {
		$(this).removeClass('bg-white');
		$(this).addClass('btn-secondary');
		$('#filterSpace').css('display', 'none');
	} else {
		$(this).addClass('bg-white');
		$(this).removeClass('btn-secondary');
		$('#filterSpace').css('display', 'block');
	}
});

//검색조건 더보기
$('.sch-more').click(function() {
	$(this).toggleClass('open');
	if(!$(this).hasClass('open')) {
		$(this).next('#sch-more-view').css('display', 'none');
	} else {
		$(this).next('#sch-more-view').css('display', 'block');
	}
});

$(".modal-dialog .btn-search").click(function(){
	if($('.modal-dialog input:checked').length != 0 && searchEl.length > 0){
		$("input[name="+$(this).data("searchdata")+"Check]:checked").each(function() {
			var existFilter = [],
				selectFilter = [$.trim($(this).closest('label').text())],
				addFilter = [];

			searchEl.find('.filter-nm').each(function() {
				var name = $(this).html();
				existFilter.push(name);
			});
			
			// 검색 필터 중복 제거
			existFilter.filter(function(v, i) {
				selectFilter.filter(function(v2, i2) {
					if(v === v2) {
						addFilter.push(v2);
					}
				});
			});
			
			if(addFilter.length === 0) {
				searchEl.find("li input").parent().remove();
				searchEl.append('<li><span class="subfilter-enabled"><span class="filter-nm">'+ selectFilter +'</span><span class="subfilter-disable-btn"></span></span></li>');
			}
		});
		
		$("#myModal").modal("hide");
		schFilterEvent();
	} if($('.modal-dialog input:checked').length == 0 && searchEl.length > 0){
		alert('체크박스를 선택해주세요');
	}
});

function popupCheckAllBox(id, event){
	event.stopPropagation();
	if($('#' + id).prop('checked')) { 
		$('.modal-dialog  input[name=' + id + ']:checkbox').prop('checked', true); 
	} else {
		$('.modal-dialog input[name=' + id + ']:checkbox').prop('checked', false); 
	}
}


/***
 * 
 *  인쇄
 */
function printReport(printDivId){
	reportFrameBtn('none');
	
	var contents = $("#"+printDivId).html();
	var pframe = $('<iframe />');
	pframe[0].name = "pframe";
	pframe.css({ "position": "absolute", "top": "-1000000px", "pointer-events": "none" });
	$("body").append(pframe);
	var frameDoc = pframe[0].contentWindow ? pframe[0].contentWindow : pframe[0].contentDocument.document ? pframe[0].contentDocument.document : pframe[0].contentDocument;
	frameDoc.document.open();
	// import style
	frameDoc.document.write('<link href="resources/vendors/css/simple-line-icons.min.css" rel="stylesheet">');
	frameDoc.document.write('<link href="resources/css/style.css" rel="stylesheet">');
	frameDoc.document.write('<link href="resources/vendors/css/gridstack/jquery-ui.css" rel="stylesheet">');
	frameDoc.document.write('<link href="resources/vendors/css/gridstack/gridstack.css" rel="stylesheet"/>');
	frameDoc.document.write('<link href="resources/css/common.css" rel="stylesheet">');
	frameDoc.document.write('<link rel="stylesheet" type="text/css" href="resources/vendors/css/gridstack/jquery-ui.css">');
	frameDoc.document.write('<link href="resources/css/style1.css" rel="stylesheet">');
	frameDoc.document.write('</head><body id="'+ printDivId + '">');
	frameDoc.document.write(contents);
	frameDoc.document.write('</body></html>');
	frameDoc.document.close();
	setTimeout(function () {
		duplicateCanvas(contents, frameDoc.document);
		window.frames["pframe"].focus();
		window.frames["pframe"].print();
		pframe.remove();
	}, 500);
	
	reportFrameBtn('inline-block');
}

function duplicateCanvas(origin, doc){
	var printDivId = 'report-body';
	var originCanvass = $("#"+printDivId).find('canvas');
	var newCanvas = $(doc).find("body").find('canvas');

	newCanvas.each(function(idx, canvas){
		var retCtx = canvas.getContext('2d');
		var originCanvas = originCanvass[idx];
		var w = originCanvas.width + 30;
		var h = originCanvas.height;
		canvas.width = w;
		canvas.height = h;
		retCtx.drawImage(originCanvas, 0, 0, w, h, 0, 0, w, h);
	})
}

function reportFrameBtn(style) {
	$('.card-actions').css('display', style);
	$('.chartType').css('display', style);
	$('.reset').css('display', style);
}


/***
 * 
 *  장애 알림 팝업
 */
function realTimeAlert(){
	var timer = setTimeout(function() {
		var el = $('#realTimeAlertTb').find('tbody'),
			alertHtml = '<tr class="cursorp">'
				+'<td class="align-middle text-center">2018-06-07 15:26:35</td>'
				+'<td class="align-middle text-center">'
				+'<a class="nav-label" href="#"><i class="fa fa-circle font-redorange"></i></a></td>'
				+'<td class="align-middle title-ellipsis" title="서울특별시 교육청">서울특별시 교육청</td>'
				+'<td class="align-middle title-ellipsis" title="10.10.40.2">10.10.40.2</td>'
				+'<td class="align-middle">Process /opt/java6/bin/java:/opt/raviro/bin/bootstrap.jarcom.neo.raviro.bootstrap.Bootstrapstart Count 1</td></tr>';
		
		el.empty();
		el.append(alertHtml);
		
		$(".modal-content").each(function(i, e){
			$(this).addClass("none");
		});
		$(".modal-dialog .realtime-alert").removeClass("none");
		$("#myModalProcess").modal("show");

		clearTimeout(timer);
		realTimeAlertEvent();
	}, 1000);
}

function realTimeAlertEvent(){
	var el = $('#realTimeAlertTb').find('tbody > tr');
	
	el.each(function(i, v) {
		var _this = $(this);
		
		_this.attr('data-toggle', 'dropdown');
		_this.attr('aria-haspopup', true);
		_this.attr('aria-expanded', false);
		
		_this.after('<div class="dropdown-menu custom" aria-labelledby="dropdownMenuLink">'
				+'<a class="dropdown-item" href="./equipmentView">장비</a>'
				+'<a class="dropdown-item" href="./processView">프로세스</a>'
				+'<a class="dropdown-item" href="./noticeView">알림내역</a>'
				+'<a class="dropdown-item" href="./eventView">이벤트내역</a>'
			+'</div>');
	});
}


/****
 * CARDS ACTIONS
 */

$(document).on('click', '.card-actions a', function(e){
	e.preventDefault();

	if ($(this).hasClass('btn-close')) {
		$(this).parent().parent().parent().fadeOut();
	} else if ($(this).hasClass('btn-minimize')) {
		var $target = $(this).parent().parent().next('.card-body');
		if (!$(this).hasClass('collapsed')) {
			$('i',$(this)).removeClass($.panelIconOpened).addClass($.panelIconClosed);
		} else {
			$('i',$(this)).removeClass($.panelIconClosed).addClass($.panelIconOpened);
		}

	} else if ($(this).hasClass('btn-setting')) {
		$('#myModal').modal('show');
	}

});

function capitalizeFirstLetter(string) {
	return string.charAt(0).toUpperCase() + string.slice(1);
}

function init(url) {

	/* ---------- Tooltip ---------- */
	$('[rel="tooltip"],[data-rel="tooltip"]').tooltip({"placement":"bottom",delay: { show: 400, hide: 200 }});

	/* ---------- Popover ---------- */
	$('[rel="popover"],[data-rel="popover"],[data-toggle="popover"]').popover();


}

//Production steps of ECMA-262, Edition 6, 22.1.2.1
if (!Array.from) {
	Array.from = (function () {
		var toStr = Object.prototype.toString;
		var isCallable = function (fn) {
			return typeof fn === 'function' || toStr.call(fn) === '[object Function]';
		};
		var toInteger = function (value) {
			var number = Number(value);
			if (isNaN(number)) { return 0; }
			if (number === 0 || !isFinite(number)) { return number; }
			return (number > 0 ? 1 : -1) * Math.floor(Math.abs(number));
		};
		var maxSafeInteger = Math.pow(2, 53) - 1;
		var toLength = function (value) {
			var len = toInteger(value);
			return Math.min(Math.max(len, 0), maxSafeInteger);
		};

		// The length property of the from method is 1.
		return function from(arrayLike/*, mapFn, thisArg */) {
			// 1. Let C be the this value.
			var C = this;

			// 2. Let items be ToObject(arrayLike).
			var items = Object(arrayLike);

			// 3. ReturnIfAbrupt(items).
			if (arrayLike == null) {
				throw new TypeError('Array.from requires an array-like object - not null or undefined');
			}

			// 4. If mapfn is undefined, then let mapping be false.
			var mapFn = arguments.length > 1 ? arguments[1] : void undefined;
			var T;
			if (typeof mapFn !== 'undefined') {
				// 5. else
				// 5. a If IsCallable(mapfn) is false, throw a TypeError exception.
				if (!isCallable(mapFn)) {
					throw new TypeError('Array.from: when provided, the second argument must be a function');
				}

				// 5. b. If thisArg was supplied, let T be thisArg; else let T be undefined.
				if (arguments.length > 2) {
					T = arguments[2];
				}
			}

			// 10. Let lenValue be Get(items, "length").
			// 11. Let len be ToLength(lenValue).
			var len = toLength(items.length);

			// 13. If IsConstructor(C) is true, then
			// 13. a. Let A be the result of calling the [[Construct]] internal method
			// of C with an argument list containing the single item len.
			// 14. a. Else, Let A be ArrayCreate(len).
			var A = isCallable(C) ? Object(new C(len)) : new Array(len);

			// 16. Let k be 0.
			var k = 0;
			// 17. Repeat, while k < len… (also steps a - h)
			var kValue;
			while (k < len) {
				kValue = items[k];
				if (mapFn) {
					A[k] = typeof T === 'undefined' ? mapFn(kValue, k) : mapFn.call(T, kValue, k);
				} else {
					A[k] = kValue;
				}
				k += 1;
			}
			// 18. Let putStatus be Put(A, "length", len, true).
			A.length = len;
			// 20. Return A.
			return A;
		};
	}());
}

//https://tc39.github.io/ecma262/#sec-array.prototype.includes
if (!Array.prototype.includes) {
	Object.defineProperty(Array.prototype, 'includes', {
		value: function(searchElement, fromIndex) {

			if (this == null) {
				throw new TypeError('"this" is null or not defined');
			}

			// 1. Let O be ? ToObject(this value).
			var o = Object(this);

			// 2. Let len be ? ToLength(? Get(O, "length")).
			var len = o.length >>> 0;

			// 3. If len is 0, return false.
			if (len === 0) {
				return false;
			}

			// 4. Let n be ? ToInteger(fromIndex).
			//    (If fromIndex is undefined, this step produces the value 0.)
			var n = fromIndex | 0;

			// 5. If n ≥ 0, then
			//  a. Let k be n.
			// 6. Else n < 0,
			//  a. Let k be len + n.
			//  b. If k < 0, let k be 0.
			var k = Math.max(n >= 0 ? n : len - Math.abs(n), 0);

			function sameValueZero(x, y) {
				return x === y || (typeof x === 'number' && typeof y === 'number' && isNaN(x) && isNaN(y));
			}

			// 7. Repeat, while k < len
			while (k < len) {
				// a. Let elementK be the result of ? Get(O, ! ToString(k)).
				// b. If SameValueZero(searchElement, elementK) is true, return true.
				if (sameValueZero(o[k], searchElement)) {
					return true;
				}
				// c. Increase k by 1.
				k++;
			}

			// 8. Return false
			return false;
		}
	});
}

// 멀티박스 이벤트
$(".multileftBtn").click(function () {
    var selectedItem = $("#"+$(this).parent().parent().data("multinm")+"right option:selected");
    $("#"+$(this).parent().parent().data("multinm")+"left").append(selectedItem);
});

$(".multirightBtn").click(function () {
    var selectedItem = $("#"+$(this).parent().parent().data("multinm")+"left option:selected");
    $("#"+$(this).parent().parent().data("multinm")+"right").append(selectedItem);
});

// 무한 스크롤
function InfiniteScroll(scrollTarget, exeFunc){
    this._el = scrollTarget;
    this.$el = $(scrollTarget);
    this.$window = $(window);
    this.$document = $(document);
    this.currentScrollHeight = 0;
    this._exeFunc = exeFunc;
    this.init();
}

function getActualHeight() {
    var actualHeight = window.innerHeight ||
                      document.documentElement.clientHeight ||
                      document.body.clientHeight ||
                      document.body.offsetHeight;

    return actualHeight;
}

InfiniteScroll.prototype.init=function(){
	$("body").append("<button class='lbtn_top'><span>TOP</span></button>");
	$('.lbtn_top').hide();
    this.currentScrollHeight = 0;
    this.bindScrollEvent();
}

InfiniteScroll.prototype.bindScrollEvent=function() {
	var that = this;
	this.unbindScrollEvent();
	this.$el.on('scroll.infiniteScroll', function(e) {
		if( e.target.scrollTop == 0 ) {return;}

		if((e.target.offsetHeight + e.target.scrollTop) >= e.target.scrollHeight-getActualHeight()) {
			var scrollHeight = e.target.scrollHeight;
			if(that.currentScrollHeight == scrollHeight) {
				that._exeFunc();
				return;
			}
			else {
				if ( that._exeFunc ) {
					that.currentScrollHeight = scrollHeight;
					that._exeFunc();
				}
			}
		}
	});
}

InfiniteScroll.prototype.unbindScrollEvent=function() {
    this.$el.off('scroll.infiniteScroll');
    
    // scroll top
    this.$el.on('scroll.infiniteScroll', function(e) {
    	if( e.target.scrollTop > 10 ) {
    		$('.lbtn_top').click(function (e) {
    			e.stopImmediatePropagation();
    			$(this).addClass('on');
    			$('#container').animate({
    				scrollTop: 0
    			}, 200);
    		});
    		
    		$('.lbtn_top').fadeIn();
    	} else {
    		$('.lbtn_top').removeClass('on');
    		$('.lbtn_top').fadeOut();
    	}
    });
}