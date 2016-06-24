
$(function() {
  $( "#search" ).focus(function() {
    $(".navbar").addClass("h_navbar_extended");
    console.log("search");
  });
  $( "#search" ).focusout(function() {
    $(".navbar").removeClass("h_navbar_extended");
    console.log("search");
  });
})