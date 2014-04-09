function Pagination(args){
	this._htmlTable = null;
	var _self = this;
	
	this.postCreate = function(){
		this._htmlTable = document.createElement("table");
		if(this.id) this._htmlTable.setAttribute("id",this.id); 
		this._htmlTable.cellSpacing = 0;
		this._htmlTable.cellPadding = 0;
//		if(dojo.config.isDebug){ //^^^
//			this._htmlTable.border = "1px";
//		}
		this._htmlTable.insertRow(0);
		this._htmlTable.className = this.classOfNode;
		this.calculateIndexes(0,this.totalResults);
		
		if(this.containerNode){
			this.containerNode.appendChild(this._htmlTable);
		}
		
		this.domNode = this._htmlTable;
	};
	this.startup = function(){
		this.render();
	};
	this._setValueAttr = function(newValue){ //inherit this method as a connection to attr("value",newValue)
		if(newValue){
			this._htmlTable.innerHTML = newValue;
			console.log("Pagination:_setValueAttr:"+newValue);
		}
		
	};
	/**
	* the maximum number of pages to show on the bar
	*/
	this.MAXIMUM_PAGES = 20;
	this.actionFunction = "browse";
	this.currentPageIndex = 0;
	/**
	 * the number of pages shown, (pages)
	 */
	this.numberOfPagesToDisplay = 5;//10

	/**
	* the current page
	*/
	this.currentPageNumber = 0;

	/**
	* how many items are being shown in the page when searching
	*/
	this.pageSize = 10; //10

	/**
	* the index of the first page.
	*/
	this.firstPageNumber = 0;

	/**
	* the first item index in the table at the specified currentPageNumber.
	* this index is calculated as follow: 
	* firstItemIndex = currentPageNumber*pageSize
	*/
	this.firstItemIndex = 0;

	/**
	* the index of the last item in the current page
	*/
	this.lastItemIndex = null;

	/**
	* contains the number of searches found.
	*/
	this.totalResults =  100;

	/**
	* the number of pages in a current search
	*/
	this.totalPages = 0;

	/**
	* the number of pages to step forward or backward when clicking 
	* the next block or previous block of pages.
	*/
	this.blockSize = 5; //10

	/**
	* the text shown on the label of the link for the next page.
	*/
	this.stepForwardText = "next";

	/**
	* the text shown on the label of the link for the next page.
	*/
	this.stepBackwordText = "prev";

	/**
	* the tooltip text shown when hovering above the link of the next 
	* block
	*/
	this.nextBlockToolTip = "Next Block";

	/**
	* the tooltip text shown when hovering above the link of the last 
	* block
	*/
	this.backBlockToolTip = "Next Block";

	/**
	* the value can be url for an image of a simple text.
	*/
	this.nextBlockText = "...";

	/**
	* the value can be url for an image of a simple text.
	*/
	this.lastBlockText = "...";

//	/**
//	* the direction of the control. default is right to left.
//	*/
//	dirAttribute: "ltr",

	/**
	* the url of the image for the ImageButton of last page.
	*/
	this.backwardImageUrl = null;

	/**
	* the url of the image for the ImageButton of next page.
	*/
	this.forwordImageUrl = null;

	/**
	* the name of the css class for the look and feel of the current 
	* selected label.
	*/
	this.classOfSelectedLabel = args.classOfSelectedLabel ? args.classOfSelectedLabel : null;

	/**
	* the css class for the labels that are not selected.
	*/
	this.classOfUnselectedLabel = args.classOfUnselectedLabel ? args.classOfUnselectedLabel : null;
	this.classOfNode = args.classOfNode ? args.classOfNode : null;
	
	this.onBrowse = function(offset, pageSize){
		console.log("onBrowse(" + offset + ", " + pageSize + ")");
	};
	
	this.calculateIndexes = function(pageIndex,totalResults)
	{
		if (totalResults < 0) totalResults = 0;
		if (pageIndex < 0) pageIndex = 0;
		
		this.totalResults = totalResults;
		var page = pageIndex;
//		this.currentPageNumber = page;
		this.firstItemIndex = this.currentPageNumber * this.pageSize;
		this.firstPageNumber = page - (page % this.numberOfPagesToDisplay);

		if ((this.firstItemIndex + this.pageSize) >= totalResults)
		{
			this.lastItemIndex = totalResults - 1;
		}
		else
		{
			this.lastItemIndex = this.firstItemIndex + this.pageSize - 1;
		}
		if ((totalResults % this.pageSize) == 0)
		{
			this.totalPages = parseInt(totalResults / this.pageSize);
		}
		else
		{
			this.totalPages = parseInt(totalResults / this.pageSize + 1);
		}
	};

	/**
	* the css of the links of the next page and last page.
	*/
	this.classNextBack = args.classNextBack ? args.classNextBack : null;
	this.setNumberOfPagesToDisplay = function(value)
	{
		if (value > 0 && value <= this.MAXIMUM_PAGES)
		{
			this.numberOfPagesToDisplay = value;
			this.calculateIndexes(this.currentPageNumber, this.totalResults);
		}
	};
	
	this.setPageSize = function(value){
		if (value > 0)
		{
			this.pageSize = value;
			this.calculateIndexes(this.currentPageNumber, this.totalResults);
		}
	};
	
	this.refresh = function(){
		this.calculateIndexes(this.currentPageNumber,this.totalResults);
		this.render();
	};
	
	this.pageToLastPage = function(){
		this.pageTo(pagination.totalPages-1);
	};
	
	this.pageTo = function(pageNumber){
//		console.log("pageTo " + pageNumber);
		this.currentPageNumber = pageNumber;
		this.refresh();
		this.onBrowse(this.firstItemIndex,this.pageSize);
	};
	
	this.onPageClick = function(e){
		var widget = e.target.pagination;
		widget.pageTo(parseInt(e.target.getAttribute("page")));
	};
	
	this.render = function(){
		this._htmlTable.deleteRow(0);
		var row = this._htmlTable.insertRow(0);
//		var row = this._htmlTable.rows[0];
//		while(row.cells.length > 0){
//			row.deleteCell(0);
//		}
		var cell;
		//--
        if(this.totalResults <= this.pageSize){
//        	console.log("DONT RENDER PAGINATION, one page or less, totalResults: " + this.totalResults + ", pageSize: " + this.pageSize);
//        	console.log(this._htmlTable.innerHTML + ", cells: " + _self._htmlTable.rows[0].cells.length);
	        
        	//for some reason, the table is not getting cleaned
        	//so we do it again here
        	setTimeout(function(){
	        	_self._htmlTable.deleteRow(0);
	    		row = _self._htmlTable.insertRow(0);
	    		
//	        	for(var i = 0 ; i < _self._htmlTable.rows.length ; i++){
//	        		console.log("row " + i + " " + _self._htmlTable.rows[i].cells.length);
//	        	}
	        },0);	
        	return;
        }
//        for (var i = 0; i < this.numberOfPagesToDisplay && i < limitOfPages; i++) {
        var cellsNumber = (this.numberOfPagesToDisplay + this.firstPageNumber) > this.totalPages ? 
        		(this.totalPages % this.numberOfPagesToDisplay) : this.numberOfPagesToDisplay;
        
//		console.log("Pagination.render numberOfPagesToDisplay: " + this.numberOfPagesToDisplay + 
//        		", firstPageNumber: " + this.firstPageNumber + ", totalPages: " + this.totalPages +", cellsNumber: " + cellsNumber);
        for (var i = 0; i < cellsNumber ; i++) {
//        	console.log("Pagination.render.iteration: " + i + ", " + this.firstPageNumber);
        	cell = row.insertCell(row.cells.length);
        	cell.style.paddingRight = "5px";
        	cell.style.paddingLeft = "5px";
			var page = this.firstPageNumber + i;
			cell.innerHTML = (page + 1) + "";

			cell.pagination = this;
			if (page >= 0 && page <= this.totalPages);
			{
				//when hovering on the label the mouse pointer will change 
				//from arrow to a hand
				//don't allow hyper-link on the selected item
				if ((this.firstPageNumber + i) != this.currentPageNumber)
				{
					cell.setAttribute("page",page);
					cell.style.cursor = "pointer";
					cell.className = this.classOfUnselectedLabel;
//					dojo.connect(cell,"click",this.onPageClick); //^^^
					cell.addEventListener('click',this.onPageClick,false);		
					
				}
				else{
					cell.className = this.classOfSelectedLabel;
				}
			}
		}
      //------------------------------------------------------------------

		//link of previous page
        cell = row.insertCell(0);
		cell.innerHTML = this.stepBackwordText;
		cell.pagination = this;
		cell.className = this.classNextBack;
		
		var prevPage = this.currentPageNumber - 1;
		if (prevPage >= 0)
		{ //do not send negative values to the server
			cell.style.cursor =  "pointer";
			cell.setAttribute("page",prevPage);
			cell.style.cursor = "pointer";
//			dojo.connect(cell,"click",this.onPageClick); //^^^
			cell.addEventListener('click',this.onPageClick,false);
		}
		
		//-----------------------------------------------------------------

		//the link for the next page
		cell = row.insertCell(row.cells.length);
		cell.innerHTML = this.stepForwardText;
		cell.pagination = this;
		cell.className = this.classNextBack;
		
		var nextPage = this.currentPageNumber + 1; //point to the next page
//		console.log("Pagination::::" + nextPage + ", " + this.totalPages);
		if (nextPage < this.totalPages)
		{
			cell.style.cursor = "pointer";
			
			cell.setAttribute("page",nextPage);
			cell.style.cursor = "pointer";
//			dojo.connect(cell,"click",this.onPageClick); //^^^
			cell.addEventListener('click',this.onPageClick,false);
		}
		
		//-----------------------------------------------------------------

		cell = row.insertCell(0);
		cell.innerHTML = this.lastBlockText;
		
		//-----------------------------------------------------------------

		cell = row.insertCell(row.cells.length);
		cell.innerHTML = this.nextBlockText;
		cell.setAttribute("title",this.nextBlockToolTip);
	};
};

