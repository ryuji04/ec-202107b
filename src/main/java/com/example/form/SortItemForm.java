package com.example.form;
/**
 * 商品を並び替える為のフォーム.
 * 
 * @author adachiryuji
 *
 */
public class SortItemForm {
	/** 商品を並び替える際の変数名　*/
	private String arrangeItem;

	public String getArrangeItem() {
		return arrangeItem;
	}

	public void setArrangeItem(String arrangeItem) {
		this.arrangeItem = arrangeItem;
	}

	@Override
	public String toString() {
		return "SortItemForm [arrangeItem=" + arrangeItem + "]";
	}
	
}
