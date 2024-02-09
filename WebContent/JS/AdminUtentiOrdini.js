  document.getElementById("filterButton").addEventListener("click", function () {
	   const startDate = new Date(document.getElementById("startDate").value);
	   const endDate = new Date(document.getElementById("endDate").value);

	   const items = document.getElementsByClassName("tabellaOrdini");
	   console.log(isNaN(startDate));
		if(!isNaN(startDate)&& !isNaN(endDate)){
	   		for (let i = 0; i < items.length; i++) {
		     	const item = items[i];
	     		const itemDate = new Date(item.getAttribute("data-date"));
	     		if (itemDate >= startDate && itemDate <= endDate) {
	       			item.style.display = "table";
	     		} else {
	       			item.style.display = "none";
	     		}
	   		}
		}
	 });
