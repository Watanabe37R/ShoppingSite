/**
 * 
 */
const productId = document.getElementById("productId");
const productIdM = document.getElementById("productIdM");
const productName = document.getElementById("productName");
const productNameM = document.getElementById("productNameM");
const price = document.getElementById("price");
const priceM = document.getElementById("priceM");
const tax = document.getElementById("tax");
const taxM = document.getElementById("taxM");
const stock = document.getElementById("stock");
const stockM = document.getElementById("stockM");
const abstractText = document.getElementById("abstractText");
const abstractTextM = document.getElementById("abstractTextM");
const submitBtn = document.getElementById("submitBtn");

// =====================
// 正規表現
// =====================
const idRegex = /^[a-zA-Z0-9]{1,10}$/;
const intRegex = /^[0-9]+$/;
const nameRegex = /^.{1,32}$/;
const imageRegex = /^.{1,128}$/;
const noDangerousChars = /^[^<>"'&]+$/;
//エラー定義
function setError(el, msg) {
	if (!el) return;
	el.textContent = msg;
	el.style.color = "red";
}

function clearError(el) {
	if (!el) return;
	el.textContent = "";
	el.style.color = "";
}

function setupValidation(config) {
	const state = {};
	const touched = {};
	console.log(config);


	function updateSubmitState() {
		const allValid = Object.values(state).every(v => v === true);
		submitBtn.disabled = !allValid;
	}

	function initField(key, checkFn, elements) {
		const input = elements.input;
		const message = elements.message;

		state[key] = false;

		if (!input) return;

		const run = () => {
			checkFn(input, message, state, touched[key]);
			updateSubmitState();
		};

		// 入力されたらフラグON
		input.addEventListener("input", () => {
			touched[key] = true;
			run();
		});

		// フォーカス外れたらチェック
		input.addEventListener("blur", () => {
			touched[key] = true;
			run();
		});

		run(); // 初期チェック
	}
	// =========================
	// 各フィールド定義
	// =========================
	//IDチェック
	if (config.productId) {
		initField("productId", (el, msg, state, touched) => {
			const v = el.value.trim();
			if (v === "") {
				clearError(msg);
				state.productId = false;
			} else if (!idRegex.test(v)) {
				if (touched) setError(msg, "IDは1〜10文字の半角英数字で入力してください");
				state.productId = false;
			} else {
				clearError(msg);
				state.productId = true;
			}
		}, { input: productId, message: productIdM });
	}

	//Nameチェック
	if (config.productName) {
		initField("productName", (el, msg, state, touched) => {
			const v = el.value.trim();
			if (v === "") {
				clearError(msg);
				state.productName = false;
			} else if (!nameRegex.test(v) || !noDangerousChars.test(v)) {
				if (touched) setError(msg, "1〜32文字で入力してください。一部使用できない記号があります。");
				state.productName = false;
			} else {
				clearError(msg);
				state.productName = true;
			}
		}, { input: productName, message: productNameM });
	}

	//価格チェック
	if (config.price) {
		initField("price", (el, msg, state, touched) => {
			const v = el.value.trim();
			if (v === "") {
				clearError(msg);
				state.price = false;
			} else if (!intRegex.test(v)) {
				if (touched) setError(msg, "価格は数字で入力してください");
				state.price = false;

			} else {
				const num = Number(v);
				if (num < 0) {
					if (touched) setError(msg, "価格は正の数字で入力してください");
					state.price = false;
				} else if (num > 2147483647) {
					if (touched) setError(msg, "価格の上限は2,147,483,647です");
					state.price = false;
				} else {
					clearError(msg);
					state.price = true;
				}
			}
		}, { input: price, message: priceM });
	}

	//税チェック
	if (config.tax) {
		initField("tax", (el, msg, state, touched) => {
			const v = el.value.trim();
			if (v === "") {
				clearError(msg);
				state.tax = false;
			} else if (!intRegex.test(v)) {
				if (touched) setError(msg, "税率は数字で入力してください");
				state.tax = false;
			} else {
				const num = Number(v);
				if (num < 0) {
					// マイナスNG
					if (touched) setError(msg, "税率は正の数字で入力してください");
					state.tax = false;
				} else if (num > 127) {
					// 上限
					if (touched) setError(msg, "税率の上限は127です");
					state.tax = false;
				} else {
					clearError(msg);
					state.tax = true;
				}
			}
		}, { input: tax, message: taxM });
	}

	//在庫チェック
	if (config.stock) {
		initField("stock", (el, msg, state, touched) => {
			const v = el.value.trim();
			if (v === "") {
				clearError(msg);
				state.stock = false;
			} else if (!intRegex.test(v)) {
				if (touched) setError(msg, "在庫は数字で入力してください");
				state.stock = false;
			} else {
				const num = Number(v);
				if (num < 0) {
					// マイナスNG
					if (touched) setError(msg, "在庫は正の数字で入力してください");
					state.stock = false;
				} else if (num > 2147483647) {
					// 上限
					if (touched) setError(msg, "在庫の上限は2,147,483,647です");
					state.stock = false;
				} else {
					clearError(msg);
					state.stock = true;
				}
			}
		}, { input: stock, message: stockM });
	}

	//摘要チェック
	if (config.abstractText) {
		initField("abstractText", (el, msg, state, touched) => {
			const v = el.value.trim();
			console.log(v);
			console.log(noDangerousChars.test(v));
			if (v === "") {
				clearError(msg);
				state.abstractText = true;
			} else if (v.length > 1000) {
				if (touched) setError(msg, "1000文字以内で入力してください");
				state.abstractText = false;

			} else if (!noDangerousChars.test(v)) {
				if (touched) setError(msg, "使用できない記号が含まれています");
				state.abstractText = false;
			} else {
				clearError(msg);
				state.abstractText = true;
			}
		}, { input: abstractText, message: abstractTextM });
	}

	//重複チェック
	if (config.duplicate) {

		if (config.productId) state.productIdDuplicate = false;

		const checkDuplicate = async () => {
			const idVal = productId ? productId.value : "";

			const res = await fetch(
				`${contextPath}/duplicationCheckForProduct.action?id=${encodeURIComponent(idVal)}`
			);

			const data = await res.json();
			state.productIdDuplicate = !data.idExists;

			if (data.idExists) {
				if (touched.productId) setError(productIdM, "IDが重複しています。別のIDをお試しください。");
			} else {
				clearError(productIdM);
			}
			updateSubmitState();
		};

		if (productId) {
			productId.addEventListener("blur", () => {
				touched.productId = true;
				checkDuplicate();
			});
		}
	}
}

function setupValidationByMode(mode) {
	if (mode === "register") {
		setupValidation({
			productId: true,
			productName: true,
			price: true,
			tax: true,
			stock: true,
			abstractText: true,
			duplicate: true
		});

	} else if (mode === "edit") {
		setupValidation({
			productName: true,
			price: true,
			tax: true,
			stock: true,
			abstractText: true
		});
	}
}
