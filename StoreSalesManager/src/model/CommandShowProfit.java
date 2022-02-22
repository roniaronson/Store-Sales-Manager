package model;

public class CommandShowProfit {
		private Store store;

		public CommandShowProfit(Store store) {
			
			this.store = store;
		}
		
		public String execute() {
			return store.showProfit();
		}
		
		
}
