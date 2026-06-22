// 要素を取得
const preview = document.getElementById("preview");
const fileInput = document.getElementById("fileInput");

if (preview && fileInput) {
	//画像クリック → ファイル選択
	preview.addEventListener("click", function() {
		fileInput.click();
	});

	//ファイル選択 → プレビュー更新
	fileInput.addEventListener("change", function() {
		const file = fileInput.files[0];
		if (file) {
			const reader = new FileReader();
			reader.onload = function(e) {
				preview.src = e.target.result;
			};
			reader.readAsDataURL(file);
		}
	});
}