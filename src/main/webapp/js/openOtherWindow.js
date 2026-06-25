/**
 * 
 */
function openCategory() {
  window.open(
    `${contextPath}/ManagerCategoryList.action?mode=reference`,
    "categoryWindow",
    "width=600,height=500"
  );
}

function openMaker() {
  window.open(
    `${contextPath}/ManagerMakerList.action?mode=reference`,
    "makerWindow",
    "width=600,height=500"
  );
}
