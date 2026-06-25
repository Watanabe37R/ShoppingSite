/**
 * 
 */
(function () {
  const searchInput = document.getElementById("search");

  if (!searchInput) return;

  searchInput.addEventListener("input", () => {
    let v = searchInput.value;

    // 前後の空白削除
    v = v.trim();

    // 危険文字削除（XSS対策）
    v = v.replace(/[<>"'&]/g, "");

    // 文字数制限
    if (v.length > 100) {
      v = v.slice(0, 100);
    }

    // 反映
    searchInput.value = v;
  });
})();
