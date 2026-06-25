/**
 * 
 */

function selectCategory(id, name) {
  // 親画面に値セット
  window.opener.document.getElementById("categoryId").value = id;
  window.opener.document.getElementById("categoryName").value = name;

  // 自分閉じる
  window.close();
}

function selectMaker(id, name) {
  // 親画面に値セット
  window.opener.document.getElementById("makerId").value = id;
  window.opener.document.getElementById("makerName").value = name;

  // 自分閉じる
  window.close();
}