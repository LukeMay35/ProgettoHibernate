/* JS Document */

/*******************************************************************************
 * 
 * [Table of Contents]
 * 
 * 1. Vars and Inits 
 * 2. Set Header 
 * 3. Init Menu
 ******************************************************************************/
function deleteProduct(product_id) {
	var xhr = new XMLHttpRequest();
	var url = "url";
	xhr.open("GET", "url?product=" + product_id, true);
	xhr.onreadystatechange = function() {
		if (xhr.readyState === 4 && xhr.status === 200) {
			var json = JSON.parse(xhr.responseText);
			var element = document.getElementById(product_id);
			element.parentNode.removeChild(element);
		}
	};
	xhr.send(null);

}
function addToCart() {
	var product_id;
	var span = document.getElementById('checkout_items');
	var text = span.textContent;
	var currentValue = Number(text);
	var xhr = new XMLHttpRequest();
	var url = "url";
	xhr.open("GET", "url?product=" + product_id, true);
	xhr.onreadystatechange = function() {
		if (xhr.readyState === 4 && xhr.status === 200) {
			var json = JSON.parse(xhr.responseText);
			document.getElementById("checkout_items").innerHTML = currentValue + 1;
		}
	};
	xhr.send(null);
}

jQuery(document)
		.ready(
				function($) {

					"use strict";

					/*
					 * 
					 * 1. Vars and Inits
					 * 
					 */

					var header = $('.header');
					var topNav = $('.top_nav')
					var mainSlider = $('.main_slider');
					var hamburger = $('.hamburger_container');
					var menu = $('.hamburger_menu');
					var menuActive = false;
					var hamburgerClose = $('.hamburger_close');
					var fsOverlay = $('.fs_menu_overlay');

					setHeader();

					$(window).on('resize', function() {
						setHeader();
					});

					$(document).on('scroll', function() {
						setHeader();
					});

					initMenu();
					initFixProductBorder();
					initIsotopeFiltering();
					initSlider();
					/*
					 * 
					 * 2. Set Header
					 * 
					 */

					function setHeader() {
						if (window.innerWidth < 992) {
							if ($(window).scrollTop() > 100) {
								header.css({
									'top' : "0"
								});
							} else {
								header.css({
									'top' : "0"
								});
							}
						} else {
							if ($(window).scrollTop() > 100) {
								header.css({
									'top' : "-50px"
								});
							} else {
								header.css({
									'top' : "0"
								});
							}
						}
						if (window.innerWidth > 991 && menuActive) {
							closeMenu();
						}
					}

					/*
					 * 
					 * 3. Init Menu
					 * 
					 */

					function initMenu() {
						if (hamburger.length) {
							hamburger.on('click', function() {
								if (!menuActive) {
									openMenu();
								}
							});
						}

						if (fsOverlay.length) {
							fsOverlay.on('click', function() {
								if (menuActive) {
									closeMenu();
								}
							});
						}

						if (hamburgerClose.length) {
							hamburgerClose.on('click', function() {
								if (menuActive) {
									closeMenu();
								}
							});
						}

						if ($('.menu_item').length) {
							var items = document
									.getElementsByClassName('menu_item');
							var i;

							for (i = 0; i < items.length; i++) {
								if (items[i].classList.contains("has-children")) {
									items[i].onclick = function() {
										this.classList.toggle("active");
										var panel = this.children[1];
										if (panel.style.maxHeight) {
											panel.style.maxHeight = null;
										} else {
											panel.style.maxHeight = panel.scrollHeight
													+ "px";
										}
									}
								}
							}
						}
					}

					function openMenu() {
						menu.addClass('active');
						// menu.css('right', "0");
						fsOverlay.css('pointer-events', "auto");
						menuActive = true;
					}

					function closeMenu() {
						menu.removeClass('active');
						fsOverlay.css('pointer-events', "none");
						menuActive = false;
					}
					/* 

					

					6. Init Fix Product Border

					 */

					function initFixProductBorder() {
						if ($('.product_filter').length) {
							var products = $('.product_filter:visible');
							var wdth = window.innerWidth;

							// reset border
							products.each(function() {
								$(this)
										.css('border-right',
												'solid 1px #e9e9e9');
							});

							// if window width is 991px or less

							if (wdth < 480) {
								for (var i = 0; i < products.length; i++) {
									var product = $(products[i]);
									product.css('border-right', 'none');
								}
							}

							else if (wdth < 576) {
								if (products.length < 5) {
									var product = $(products[products.length - 1]);
									product.css('border-right', 'none');
								}
								for (var i = 1; i < products.length; i += 2) {
									var product = $(products[i]);
									product.css('border-right', 'none');
								}
							}

							else if (wdth < 768) {
								if (products.length < 5) {
									var product = $(products[products.length - 1]);
									product.css('border-right', 'none');
								}
								for (var i = 2; i < products.length; i += 3) {
									var product = $(products[i]);
									product.css('border-right', 'none');
								}
							}

							else if (wdth < 992) {
								if (products.length < 5) {
									var product = $(products[products.length - 1]);
									product.css('border-right', 'none');
								}
								for (var i = 3; i < products.length; i += 4) {
									var product = $(products[i]);
									product.css('border-right', 'none');
								}
							}

							//if window width is larger than 991px
							else {
								if (products.length < 5) {
									var product = $(products[products.length - 1]);
									product.css('border-right', 'none');
								}
								for (var i = 4; i < products.length; i += 5) {
									var product = $(products[i]);
									product.css('border-right', 'none');
								}
							}
						}
					}

					/* 

					7. Init Isotope Filtering

					 */

					function initIsotopeFiltering() {
						if ($('.grid_sorting_button').length) {
							$('.grid_sorting_button').click(
									function() {
										// putting border fix inside of setTimeout because of the transition duration
										setTimeout(function() {
											initFixProductBorder();
										}, 500);

										$('.grid_sorting_button.active')
												.removeClass('active');
										$(this).addClass('active');

										var selector = $(this).attr(
												'data-filter');
										$('.product-grid').isotope({
											filter : selector,
											animationOptions : {
												duration : 750,
												easing : 'linear',
												queue : false
											}
										});

										return false;
									});
						}
					}

					/* 

					8. Init Slider

					 */

					function initSlider() {
						if ($('.product_slider').length) {
							var slider1 = $('.product_slider');

							slider1.owlCarousel({
								loop : false,
								dots : false,
								nav : false,
								responsive : {
									0 : {
										items : 1
									},
									480 : {
										items : 2
									},
									768 : {
										items : 3
									},
									991 : {
										items : 4
									},
									1280 : {
										items : 5
									},
									1440 : {
										items : 5
									}
								}
							});

							if ($('.product_slider_nav_left').length) {
								$('.product_slider_nav_left')
										.on(
												'click',
												function() {
													slider1
															.trigger('prev.owl.carousel');
												});
							}

							if ($('.product_slider_nav_right').length) {
								$('.product_slider_nav_right')
										.on(
												'click',
												function() {
													slider1
															.trigger('next.owl.carousel');
												});
							}
						}
					}
					function search() {
						var search = document.search.search.value;
						if (search === null || search === "") {
							return false;
						} else {
							var product = "";
							var xhr = new XMLHttpRequest();
							var url = "url";
							xhr.open("GET", "url?search=" + search, true);
							xhr.onreadystatechange = function() {
								if (xhr.readyState === 4 && xhr.status === 200) {
									var json = JSON.parse(xhr.responseText);
									document.getElementById("product-grid").innerHTML = "";
									for (i in json.products) {
										product = "";
										product += '<div class="product-item" id="'
												+ json.products[i].id
												+ '"><div class="product"><div class="product_image"><img src="'
												+ json.products[i].image
												+ '"alt="Imagine non Trovata!"></div><div class="product_info"><h6 class="product_name"><a onclick="$("#mostra_risorsa'
												+ json.products[i].id
												+ '").submit()">'
												+ json.products[i].title
												+ '</a></h6><div class="product_price">€'
												+ json.products[i].price
												+ '</div></div></div><div class="red_button add_to_cart_button"><a onclick="$("#aggiungi_al_carrello'
												+ json.products[i].id
												+ '").submit()">Aggiungi al Carrello</a></div><form method="get" action="profile" id="aggiungi_al_carrello'
												+ json.products[i].id
										'"><input type="hidden" value="aggiungi" name="type"><input type="hidden" name="id" value="'
												+ json.products[i].id
										'"><input type="hidden" id="quantity" name="quantity" value="1"></form></div><form  method="get" action="resource" id="mostra_risorsa'
												+ json.products[i].id
										'"><input type="hidden" name="type" value="mostra"><input type="hidden" name="id" value="'
												+ json.products[i].id
										'"></form></div>';
										document.getElementById("product-grid")
												.insertAdjacentHTML(
														'beforeend', product);
									}
								}
							};
							xhr.send(null);
						}
					}

					function addToCart() {
						var product_id;
						var span = document.getElementById('checkout_items');
						var text = span.textContent;
						var currentValue = Number(text);
						var xhr = new XMLHttpRequest();
						var url = "url";
						xhr.open("GET", "url?product=" + product_id, true);
						xhr.onreadystatechange = function() {
							if (xhr.readyState === 4 && xhr.status === 200) {
								var json = JSON.parse(xhr.responseText);
								document.getElementById("checkout_items").innerHTML = currentValue + 1;
							}
						};
						xhr.send(null);
					}
				});
