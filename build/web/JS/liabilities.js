/*
 *Author     : tetraPRO( )
 *                   : Software Wizard
 *                 : Grand-Master_Philip Caputo
 *                 : **Fullstack Engineer**
 */
var checkForm = function(e)
  {
    var form = (e.target) ? e.target : e.srcElement;
    if(form.bank.value === "") {
      alert("Please enter your balances for your liability accounts");
      form.bank.focus();
      e.preventDefault ? e.preventDefault() : e.returnValue = false;
      return;
    }
  };
  // Original JavaScript code by Chirp Internet: www.chirp.com.au
  // Please acknowledge use of this code by including this header.
  //Thank you Chirp!

  var modal_init = function() {

    var modalWrapper = document.getElementById("liability_wrapper");
    var modalWindow  = document.getElementById("liability_window");

    var openModal = function(e)
    {
      modalWrapper.className = "overlay";
      var overflow = modalWindow.offsetHeight - document.documentElement.clientHeight;
      if(overflow > 0) {
        modalWindow.style.maxHeight = (parseInt(window.getComputedStyle(modalWindow).height) - overflow) + "px";
      }
      modalWindow.style.marginTop = (-modalWindow.offsetHeight)/2 + "px";
      modalWindow.style.marginLeft = (-modalWindow.offsetWidth)/2 + "px";
      e.preventDefault ? e.preventDefault() : e.returnValue = false;
    };

    var closeModal = function(e)
    {
      modalWrapper.className = "";
      e.preventDefault ? e.preventDefault() : e.returnValue = false;
    };

    var clickHandler = function(e) {
      if(!e.target) e.target = e.srcElement;
      if(e.target.tagName === "DIV") {
        if(e.target.id !=="liability_window") closeModal(e);
      }
    };

    var keyHandler = function(e) {
      if(e.keyCode === 27) closeModal(e);
    };

    if(document.addEventListener) {
      document.getElementById("open_liabilities").addEventListener("click", openModal, false);
      document.getElementById("liability_close").addEventListener("click", closeModal, false);
      document.addEventListener("click", clickHandler, false);
      document.addEventListener("keydown", keyHandler, false);
    } else {
      document.getElementById("open_liabilities").attachEvent("onclick", openModal);
      document.getElementById("liability_close").attachEvent("onclick", closeModal);
      document.attachEvent("onclick", clickHandler);
      document.attachEvent("onkeydown", keyHandler);    
    }
  };
  if(document.addEventListener) {
    document.getElementById("liability_feedback").addEventListener("submit", checkForm, false);
    document.addEventListener("DOMContentLoaded", modal_init, false);
  } else {
    document.getElementById("liability_feedback").attachEvent("onsubmit", checkForm);
    window.attachEvent("onload", modal_init);
  }